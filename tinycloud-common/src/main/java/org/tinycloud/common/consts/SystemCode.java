package org.tinycloud.common.consts;

/**
 * <p>
 * 系统编码常量
 * </p>
 *
 * @author liuxingyu01
 * @since 2023/1/4 14:49
 **/

public enum SystemCode {
    USER_CENTER("1000", "用户中心"),
    AUTH_CENTER("2000", "会话中心"),
    ABILITY_CENTER("3000", "能力中心"),
    MESSAGE_CENTER("4000", "消息中心"),
    JOB_CENTER("5000", "调度中心"),
    OSS_CENTER("6000", "文件中心"),
    GATEWAY("7000", "网关"),

    SYSTEM("9000", "系统管理服务"),
    ;

    private String code;

    private String desc;

    private SystemCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

