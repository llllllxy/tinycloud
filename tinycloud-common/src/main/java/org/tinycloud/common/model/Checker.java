package org.tinycloud.common.model;


import org.tinycloud.common.consts.ResultCode;
import org.tinycloud.common.consts.SystemCode;
import org.tinycloud.common.exception.BusinessException;

/**
 * <p>
 * 参数校验工具，方便使用
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public abstract class Checker {
    /**
     * 检查参数,不满足抛出异常,可用来简化代码
     *
     * @param matched    是否满足逻辑
     * @param systemCode 系统码
     * @param code       错误信息,尽量友好
     * @param msg        文案
     * @throws BusinessException matched = false则抛出异常
     */
    public static void checkArgument(boolean matched, SystemCode systemCode, Integer code, String msg) {
        if (!matched) {
            throw new BusinessException(systemCode, code, msg);
        }
    }

    /**
     * 检查参数,不满足抛出异常,可用来简化代码
     *
     * @param matched    是否满足逻辑
     * @param systemCode 系统码
     * @param code       枚举
     * @throws BusinessException matched = false则抛出异常
     */
    public static void checkArgument(boolean matched, SystemCode systemCode, ResultCode code) {
        if (!matched) {
            throw new BusinessException(systemCode, code);
        }
    }
}
