package org.liuxingyu.tinycloud.common.consts;

/**
 * <p>
 * 通用全局常量
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public final class GlobalConstant {

    /**
     * 系统包名前缀(用于扫描包的配置)
     */
    public static final String BASE_PACKAGE_PREFIX = "com.liuxingyu.tinycloud";

    /**
     * 前端请求头的的请求客户端
     */
    public static final String HEADER_CLIENT_TYPE = "client-type";

    /**
     * 已删除标记
     */
    public static final Integer DELETED = 1;

    /**
     * 未删除标记
     */
    public static final Integer NOT_DELETED = 0;

    /**
     * 统一接口限流 redis-key
     */
    public static final String LIMIT_KEY = "tinycloud:limit";
}
