package org.tinycloud.common.exception;

import org.tinycloud.common.consts.ResultCode;
import org.tinycloud.common.consts.SystemCode;

/**
 * <p>
 * 统一业务异常封装
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class BusinessException extends RuntimeException {

    private final Integer code;

    private final String message;

    private final Object errT;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getErrT() {
        return errT;
    }

    private BusinessException(Integer code, String message, Object errT) {
        super(message);
        this.code = code;
        this.message = message;
        this.errT = errT;
    }

    private BusinessException(Integer code, String message) {
        this(code, message, null);
    }

    /**
     * 带系统码的异常，用来区分微服务系统
     * @param systemCode 系统编码枚举
     * @param code 错误码
     * @param message 错误信息
     */
    public BusinessException(SystemCode systemCode, Integer code, String message) {
        this(systemCode.getCode() + code, message, null);
    }

    /**
     * 带系统码的异常，用来区分微服务系统
     * @param systemCode 系统编码枚举
     * @param code 错误码
     */
    public BusinessException(SystemCode systemCode, ResultCode code) {
        this(systemCode.getCode() + code.getCode(), code.getDesc());
    }

    /**
     *  带系统码的异常，用来区分微服务系统
     * @param systemCode 系统编码枚举
     * @param code 错误码
     * @param errT 错误明细
     */
    public BusinessException(SystemCode systemCode, ResultCode code, Object errT) {
        this(systemCode.getCode() + code.getCode(), code.getDesc(), errT);
    }
}
