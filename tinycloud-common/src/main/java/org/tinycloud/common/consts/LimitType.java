package org.tinycloud.common.consts;

/**
 * <p>
 * 限流类型
 * </p>
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public enum LimitType {
    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP
}