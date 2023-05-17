package org.liuxingyu.tinycloud.user.service;

import org.liuxingyu.tinycloud.bean.entity.UcUser;

/**
 * <p>
 * 系统用户信息表 服务类
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-05-17 11:49:24
 */
public interface UcUserService {

    UcUser detail(String userId);
}
