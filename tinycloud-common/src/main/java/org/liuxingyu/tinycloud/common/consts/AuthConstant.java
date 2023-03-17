package org.liuxingyu.tinycloud.common.consts;

/**
 * <p>
 * 系统会话相关全局常量
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class AuthConstant {

    /**
     * 系统登录用户token-key（或者cookie-key）
     */
    public static final String AUTH_TOKEN_KEY = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String AUTH_TOKEN_PREFIX = "Bearer ";

    /**
     * 图片验证码 redis-key
     */
    public static final String AUTH_CAPTCHA_CODE_REDIS_KEY = "coolcars-mms:captchacodes";

    /**
     * 缓存用户会话redis的key
     */
    public final static String AUTH_TOKEN_CACHE = "coolcars:cache:token";

    // 登录密码的盐
    public final static String AUTH_SALT = "ymp8R3Vg7Kv5$y5fM3*xl&ins7SZcTEY";

    // 缓存同一账号登录会话数
    public final static String AUTH_USER_SESSION_NUMS_REDIS_KEY = "coolcars:user-session-nums";

    // 缓存尝试登录次数
    public final static String AUTH_LOGIN_ATTEMPT_TIMES_REDIS_KEY = "coolcars:login-attempt-times";

    // 缓存用户信息
    public final static String AUTH_USERINFO_CACHE_REDIS_KEY = "coolcars:cache:userinfo";

    // 缓存权限信息
    public final static String AUTH_PERMISSIONS_CACHE_REDIS_KEY = "coolcars:cache:permissions";

    // 缓存角色信息
    public final static String AUTH_ROLES_CACHE_REDIS_KEY = "coolcars:cache:roles";
}
