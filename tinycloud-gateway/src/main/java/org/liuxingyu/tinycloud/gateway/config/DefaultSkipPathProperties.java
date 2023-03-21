package org.liuxingyu.tinycloud.gateway.config;

import java.util.ArrayList;
import java.util.List;


/**
 * 默认跳过的url配置
 *
 * @author liuxingyu01
 * @since 2021-4-25 16:29:08
 **/
public class DefaultSkipPathProperties {
    private static final List<String> DEFAULT_SKIP_AUTH_PATH = new ArrayList<>();

    private static final List<String> DEFAULT_SKIP_PERMISSION_PATH = new ArrayList<>();

    static {
        DEFAULT_SKIP_AUTH_PATH.add("/gateway/auth-server/login");
        DEFAULT_SKIP_AUTH_PATH.add("/gateway/auth-server/getCode");
        DEFAULT_SKIP_AUTH_PATH.add("/gateway/auth-server/anon/**");
        DEFAULT_SKIP_AUTH_PATH.add("/gateway/user-server/anon/**");
        DEFAULT_SKIP_AUTH_PATH.add("/gateway/ability-server/anon/**");
        DEFAULT_SKIP_AUTH_PATH.add("/gateway/message-server/anon/**");
    }

    /**
     * 默认无需会话的API
     */
    public static List<String> getDefaultSkipAuthPath() {
        return DEFAULT_SKIP_AUTH_PATH;
    }

    /**
     * 默认无需鉴权的API
     */
    public static List<String> getDefaultSkipPermissionPath() {
        return DEFAULT_SKIP_PERMISSION_PATH;
    }
}
