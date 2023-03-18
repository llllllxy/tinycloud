package org.liuxingyu.tinycloud.gateway.config;


import com.alibaba.nacos.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.liuxingyu.tinycloud.gateway.utils.JsonUtils;
import org.liuxingyu.tinycloud.gateway.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 鉴权过滤器
 *
 * @author liuxingyu01
 * @since 2021-4-25 16:29:08
 **/
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 系统登录用户token-key（或者cookie-key）
     */
    private static final String TOKEN_KEY = "Authorization";

    /**
     * 缓存用户会话redis的key
     */
    public final static String AUTH_TOKEN_CACHE = "tinycloud:cache:token";

    /**
     * 刷新redis里缓存的时间阈值
     */
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 毫秒
     */
    protected static final long MILLIS_SECOND = 1000;

    /**
     * 用户会话时长，默认1800秒
     */
    protected static final long AUTH_TIMEOUT = 1800;

    @Autowired
    private AuthUrlProperties authUrlProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 不需要登录校验的话直接跳过后面的步骤
        if (isSkip(path)) {
            // 设置用户信息到请求头，传递到下游微服务
            ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                    .header("USER_ID", "")
                    .header("USERNAME", "")
                    .header("GATEWAY_REQUEST_URL", path)
                    .build();
            ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
            return chain.filter(mutableExchange);
        }

        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst(TOKEN_KEY);
        String paramToken = exchange.getRequest().getQueryParams().getFirst(TOKEN_KEY);
        // 第1步：校验请求方有没有带token过来
        if (StringUtils.isAllBlank(headerToken, paramToken)) {
            return unAuth(resp);
        }
        String token = StringUtils.isBlank(headerToken) ? paramToken : headerToken;

        // 第2步：校验token的格式是否正确
        if (token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        } else { // token不是以TOKEN_PREFIX开头的，不合格
            return unAuth(resp);
        }

        // 第3步：校验token是不是伪造的（能否parseJwt成功，不报异常就是真的）
        Claims claims;
        try {
            claims = JwtUtils.parseJwt(token);
        } catch (Exception e) {
            return unAuth(resp);
        }
        // 从jwt的Payload里获取redis-key，这相当于是真正的会话token，里面存着用户信息，包括userId，username等
        String auth_token = (String) claims.get("auth_token");
        // 第4步：校验auth_token在redis里是否存在
        String authString = stringRedisTemplate.opsForValue().get(AUTH_TOKEN_CACHE + ":" + auth_token);
        if (StringUtils.isBlank(authString)) {
            return unAuth(resp);
        }
        Map<String, Object> authMap;
        try {
            authMap = (Map<String, Object>) JsonUtils.parseMap(authString);
        } catch (Exception e) {
            return unAuth(resp);
        }
        // 第5步：会话校验合格，取出会话信息，并向下游服务传递（后续的权限和角色值可能也会放在authMap里，同时校验）
        String userId = (String) authMap.get("userId");
        String username = (String) authMap.get("username");
        long expireTime = Long.parseLong(authMap.get("expireTime").toString());
        long currentTime = System.currentTimeMillis();

        // 验证token有效期，相差不足10分钟时，自动刷新缓存，这样设计可以减少对redis的访问
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            authMap.put("loginTime", System.currentTimeMillis());
            authMap.put("expireTime", System.currentTimeMillis() + AUTH_TIMEOUT * MILLIS_SECOND);
            stringRedisTemplate.opsForValue().set(AUTH_TOKEN_CACHE + ":" + auth_token,
                    JsonUtils.toJsonString(authMap), AUTH_TIMEOUT, TimeUnit.SECONDS);
        }

        // 设置用户信息到请求头，传递到下游微服务
        ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                .header("USER_ID", userId)
                .header("USERNAME", username)
                .header("GATEWAY_REQUEST_URL", path)
                .build();

        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }


    /**
     * 会话校验失败统一处理
     * @param resp ServerHttpResponse
     * @return Mono
     */
    private Mono<Void> unAuth(ServerHttpResponse resp) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = "";
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", HttpStatus.UNAUTHORIZED.value());// 编码401
        map.put("msg", "会话已失效或不存在！");
        map.put("data", null);
        map.put("time", System.currentTimeMillis());
        // 转成json返回
        result = JsonUtils.toJsonString(map);
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }


    /**
     * 根据上下文路径判断是否跳过鉴权
     *
     * @param path 上下文路径
     * @return true or false
     */
    private boolean isSkip(String path) {
        return DefaultUrlProperties.getDefaultSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path))
                || authUrlProperties.getSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
    }


    @Override
    public int getOrder() {
        // 值越小越先执行
        return -100;
    }
}
