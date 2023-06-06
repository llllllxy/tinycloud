package org.tinycloud.common.annotation;


import org.tinycloud.common.consts.GlobalConstant;
import org.tinycloud.common.consts.LimitType;

import java.lang.annotation.*;

/**
 * 限流注解
 * 使用方法：
 *  1、@Limit(type = LimitType.DEFAULT, time = 10, count = 2)  代表的是默认限流方式，10秒内只能访问两次
 *  1、@Limit(type = LimitType.IP, time = 10, count = 2)  代表的是ip限流方式，同一ip10秒内只能访问两次
 * @author liuxingyu01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {
    /**
     * 限流key前缀
     */
    String prefix() default GlobalConstant.LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 10;

    /**
     * 限流类型
     */
    LimitType type() default LimitType.DEFAULT;
}
