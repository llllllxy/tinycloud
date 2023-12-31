package org.tinycloud.common.consts;

/**
 * <p>
 * 该类为全局系统通用码
 * 业务侧响应码需要业务侧自己定义
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public enum ResultCode {
    SUCCESS("0", "成功"),
    PARAM_ERROR("400", "参数校验失败"),
    UNAUTHORIZED("401", "会话不存在或已失效"),
    NOT_EXIST("402", "不存在"),
    FORBIDDEN("403", "访问受限，无权限"),
    RESOURCE_NOT_FOUND("404", "资源未找到"),
    RESOURCE_METHOD_NOT_SUPPORT("405", "请求方法不支持"),
    RESOURCE_CONFLICT("409", "资源冲突"),
    DUPLICATE_SUBMISSIONS("410", "重复提交"),
    REQUEST_PARAM_ERROR("412", "参数错误"),
    PRECONDITION_FAILED("428", "要求先决条件"),
    NOT_SUPPORT("429", "不支持的请求"),
    ALREADY_EXECUTING("410", "程序正在执行，请稍后再试"),
    UNKNOWN_ERROR("500", "系统未知错误"),
    NOT_IMPLEMENTED("501", "接口暂未实现");

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

     ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

     ResultCode() {
    }
}
