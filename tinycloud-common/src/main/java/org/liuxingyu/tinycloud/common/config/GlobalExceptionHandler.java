package org.liuxingyu.tinycloud.common.config;

import org.liuxingyu.tinycloud.common.consts.ResultCode;
import org.liuxingyu.tinycloud.common.exception.BusinessException;
import org.liuxingyu.tinycloud.common.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * <p>
 * 全局统一异常处理
 * </p>
 *
 * @author liuxingyu01
 * @since 2023/1/4 14:49
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获404异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult<?> handle404Error(Throwable e) {
        return buildResponseEntity(ResultCode.RESOURCE_NOT_FOUND.getCode(), ResultCode.RESOURCE_NOT_FOUND.getDesc());
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ApiResult<?> paramBind(BindException e) {
        // 对校验结果统一输出
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            sb.append(error.getField()).append("：").append(error.getDefaultMessage()).append(", ");
        }
        return buildResponseEntity(ResultCode.PARAM_ERROR.getCode(), sb.toString());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<?> exception(Throwable throwable) {
        log.error("系统处理异常!", throwable);
        // 参数格式转换异常
        if (throwable instanceof IllegalArgumentException
                || throwable instanceof HttpMessageConversionException
                || throwable instanceof MethodArgumentTypeMismatchException) {
            return buildResponseEntity(ResultCode.PARAM_ERROR.getCode(), ResultCode.PARAM_ERROR.getDesc());
        }
        if (throwable instanceof HttpRequestMethodNotSupportedException) {
            return buildResponseEntity(ResultCode.RESOURCE_METHOD_NOT_SUPPORT.getCode(), ResultCode.RESOURCE_METHOD_NOT_SUPPORT.getDesc());
        }
        // 业务异常
        if (throwable instanceof BusinessException) {
            return buildResponseEntity(((BusinessException) throwable).getCode(), throwable.getMessage());
        }
        // 其他异常
        return buildResponseEntity(ResultCode.UNKNOWN_ERROR.getCode(), ResultCode.UNKNOWN_ERROR.getDesc());
    }

    private static ApiResult<?> buildResponseEntity(Integer code, String msg) {
        return ApiResult.fail(code, msg);
    }
}