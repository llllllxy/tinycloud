package org.tinycloud.common.model;

import org.tinycloud.common.consts.ResultCode;
import org.tinycloud.common.consts.SystemCode;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * <p>
 * 该类为全局系统通用码
 * 业务侧响应码需要业务侧自己定义
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -1491499610241557029L;

    private Integer code = ResultCode.SUCCESS.getCode();

    private T data;

    private String msg = ResultCode.SUCCESS.getDesc();

    private String traceId;

    private long time;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 空构造方法
     */
    public ApiResult() {
        this.traceId = MDC.get("traceId");
        this.time = System.currentTimeMillis();
    }

    /**
     * 带参构造方法
     *
     * @param code 编码
     * @param data 自定义data
     * @param msg  自定义消息
     */
    public ApiResult(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.traceId = MDC.get("traceId");
        this.time = System.currentTimeMillis();
    }

    /**
     * 返回成功
     *
     * @param <T> 泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> success() {
        return success(null);
    }

    /**
     * 返回成功
     *
     * @param data 自定义data
     * @param <T>  泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> success(T data) {
        return success(data, ResultCode.SUCCESS.getDesc());
    }

    /**
     * 返回成功
     *
     * @param data 自定义data
     * @param msg  自定义消息
     * @param <T>  泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> success(T data, String msg) {
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), data, msg);
    }

    /**
     * 返回失败
     *
     * @param msg 自定义消息
     * @param <T> 泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> fail(String msg) {
        return new ApiResult<>(ResultCode.UNKNOWN_ERROR.getCode(), null, msg);
    }

    /**
     * 返回失败
     *
     * @param code 自定义错误码
     * @param msg  自定义消息
     * @param <T>  泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> fail(Integer code, String msg) {
        return new ApiResult<>(code, null, msg);
    }

    /**
     * 返回失败（标记系统）
     *
     * @param systemCode 自定义系统码
     * @param code       自定义错误码
     * @param msg        自定义消息
     * @param <T>        泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> fail(SystemCode systemCode, Integer code, String msg) {
        return new ApiResult<>(systemCode.getCode() + code, null, msg);
    }

    /**
     * 返回失败（标记系统）
     *
     * @param systemCode 自定义系统码
     * @param error      自定义错误码
     * @return ApiResult<T>
     */
    public static ApiResult<?> fail(SystemCode systemCode, ResultCode error) {
        return new ApiResult<>(systemCode.getCode() + error.getCode(), null, error.getDesc());
    }

    /**
     * 返回失败
     *
     * @param systemCode 自定义系统码
     * @param error      自定义错误码
     * @param data       自定义消息
     * @param <T>        泛型
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> fail(SystemCode systemCode, ResultCode error, T data) {
        return new ApiResult<>(systemCode.getCode() + error.getCode(), data, error.getDesc());
    }

}
