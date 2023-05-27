package org.liuxingyu.tinycloud.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author liuxingyu
 * @since  2022-4-13
 * <p>
 * API访问日志全局拦截器，作用所有的微服务
 * <p>
 * 1. 对请求的API调用过滤，记录接口的请求时间，方便日志审计、告警、分析等运维操作
 * 2. 后期可以扩展对接其他日志系统
 * <p>
 */
@Component
public class ApiLoggingFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(ApiLoggingFilter.class);


    private static final String START_TIME = "startTime";

    // nginx需要配置
    private static final String X_REAL_IP = "X-Real-IP";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (log.isInfoEnabled()) {
            String info = String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}",
                    exchange.getRequest().getMethod().name(), exchange.getRequest().getURI().getHost(),
                    exchange.getRequest().getURI().getPath(), exchange.getRequest().getQueryParams());
            log.info(info);
        }
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                Long executeTime = (System.currentTimeMillis() - startTime);
                List<String> ips = exchange.getRequest().getHeaders().get(X_REAL_IP);
                String ip = ips != null ? ips.get(0) : null;
                String api = exchange.getRequest().getURI().getRawPath();

                int code = 500;
                if (exchange.getResponse().getStatusCode() != null) {
                    code = exchange.getResponse().getStatusCode().value();
                }
                // 当前仅记录日志，后续可以添加日志消息队列，来统计过滤请求慢的接口
                if (log.isInfoEnabled()) {
                    log.info("来自IP地址：{}的请求，接口：{}，响应状态码：{}，请求耗时：{}ms", ip, api, code, executeTime);
                }
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
