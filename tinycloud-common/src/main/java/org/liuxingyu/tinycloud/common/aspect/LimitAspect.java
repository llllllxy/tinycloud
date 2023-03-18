package org.liuxingyu.tinycloud.common.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.liuxingyu.tinycloud.common.annotation.Limit;
import org.liuxingyu.tinycloud.common.consts.LimitType;
import org.liuxingyu.tinycloud.common.utils.IpUtils;
import org.liuxingyu.tinycloud.common.utils.web.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


/**
 * 接口限流处理切面处理类
 *
 * @author liuxingyu01
 * @since  2022-03-13-13:47
 **/
@Aspect
@Component
public class LimitAspect {
    final static Logger log = LoggerFactory.getLogger(LimitAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 前置通知，判断是否超出限流次数
     *
     * @param point
     */
    @Before("@annotation(limit)")
    public void doBefore(JoinPoint point, Limit limit) {
        try {
            // 拼接key
            String key = getCombineKey(limit, point);
            // 判断是否超出限流次数
            if (!redisLimit(key, limit.count(), limit.time())) {
                throw new RuntimeException("访问过于频繁，请稍候再试");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("接口限流异常，请稍候再试");
        }
    }


    /**
     * 根据限流类型拼接key
     */
    public String getCombineKey(Limit limit, JoinPoint point) {
        StringBuilder sb = new StringBuilder(limit.prefix() + ":");
        // 按照IP限流
        if (limit.type() == LimitType.IP) {
            sb.append(IpUtils.getIpAddr(ServletUtils.getRequest())).append(":");
        }
        // 拼接类名和方法名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        sb.append(targetClass.getName()).append("-").append(method.getName());
        return sb.toString();
    }


    /**
     * 限流
     *
     * @param key   键
     * @param count 限流次数
     * @param times 限流时间
     * @return
     */
    public boolean redisLimit(String key, int count, int times) {
        try {
            String script = "local lockKey = KEYS[1]\n" +
                    "local lockCount = KEYS[2]\n" +
                    "local lockExpire = KEYS[3]\n" +
                    "local currentCount = tonumber(redis.call('get', lockKey) or \"0\")\n" +
                    "if currentCount < tonumber(lockCount)\n" +
                    "then\n" +
                    "    redis.call(\"INCRBY\", lockKey, \"1\")\n" +
                    "    redis.call(\"expire\", lockKey, lockExpire)\n" +
                    "    return true\n" +
                    "else\n" +
                    "    return false\n" +
                    "end";
            RedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
            List<String> keys = Arrays.asList(key, String.valueOf(count), String.valueOf(times));
            return redisTemplate.execute(redisScript, keys);
        } catch (Exception e) {
            log.error("限流脚本执行失败：{e}", e);
        }
        return false;
    }
}
