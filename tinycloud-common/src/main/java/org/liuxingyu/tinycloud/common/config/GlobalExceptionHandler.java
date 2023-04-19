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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * <p>
 *  全局统一异常处理
 * </p>
 *
 * @author liuxingyu01
 * @since 2023/3/4 14:49
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获404异常
     *
     * @param e NoHandlerFoundException
     * @return  ApiResult
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult<?> handle404Error(Throwable e) {
        return buildResponseEntity(ResultCode.RESOURCE_NOT_FOUND.getCode(), ResultCode.RESOURCE_NOT_FOUND.getDesc());
    }

    /**
     * 参数校验异常
     * 使用hibernate-validator检验参数时，会抛出此异常
     *
     * @param e BindException
     * @return ApiResult
     */
    @ExceptionHandler(BindException.class)
    public ApiResult<?> paramBind(BindException e) {
        // 对校验结果统一输出
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            sb.append(error.getField()).append("：").append(error.getDefaultMessage()).append(", ");
        }
        String message = sb.toString();
        if (message != null && message.length() > 2) {
            message = message.substring(0, message.length() - 2);
        }
        return buildResponseEntity(ResultCode.PARAM_ERROR.getCode(), message);
    }


    /**
     * 参数校验异常1
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 对校验结果统一输出
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            sb.append(error.getField()).append("：").append(error.getDefaultMessage()).append(", ");
        }
        String message = sb.toString();
        if (message != null && message.length() > 2) {
            message = message.substring(0, message.length() - 2);
        }
        return buildResponseEntity(ResultCode.PARAM_ERROR.getCode(), message);
    }


    /**
     * 其他异常统一处理
     *
     * @param throwable Throwable
     * @return ApiResult
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<?> exception(Throwable throwable) {
        log.error("系统处理异常：", throwable);
        // 参数格式转换异常
        if (throwable instanceof IllegalArgumentException
                || throwable instanceof HttpMessageConversionException
                || throwable instanceof MethodArgumentTypeMismatchException) {
            return buildResponseEntity(ResultCode.PARAM_ERROR.getCode(), ResultCode.PARAM_ERROR.getDesc());
        }
        // 请求方式method不支持异常
        if (throwable instanceof HttpRequestMethodNotSupportedException) {
            return buildResponseEntity(ResultCode.RESOURCE_METHOD_NOT_SUPPORT.getCode(), ResultCode.RESOURCE_METHOD_NOT_SUPPORT.getDesc());
        }
        // 自定义业务异常
        if (throwable instanceof BusinessException) {
            return buildResponseEntity(((BusinessException) throwable).getCode(), throwable.getMessage());
        }
        // 其他异常（统一返回500）
        return buildResponseEntity(ResultCode.UNKNOWN_ERROR.getCode(), ResultCode.UNKNOWN_ERROR.getDesc());
    }

    private static ApiResult<?> buildResponseEntity(Integer code, String msg) {
        return ApiResult.fail(code, msg);
    }
}
