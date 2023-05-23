package org.liuxingyu.tinycloud.user.service;

import org.liuxingyu.tinycloud.bean.vo.UcUserVo;

/**
 * <p>
 * 系统用户信息表 服务类
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-05-17 11:49:24
 */
public interface UcUserService {

    UcUserVo detail(String userId);
}
