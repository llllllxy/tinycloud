package org.liuxingyu.tinycloud.common.consts;


public enum SystemCode {
    X1_USER_CENTER(10000, "用户中心"),
    X1_ABILITY_CENTER(20000, "能力中心"),
    X1_MESSAGE_CENTER(30000, "消息中心"),
    X1_JOB_CENTER(40000, "调度中心"),
    X1_GATEWAY(50000, "网关服务"),
    X1_SYSTEM(90000, "系统管理服务"),
    ;

    private Integer code;

    private String desc;

    private SystemCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

