package org.tinycloud.user.service;

import org.tinycloud.bean.param.UcUserPageQuery;
import org.tinycloud.bean.vo.UcUserVo;
import org.tinycloud.common.model.PageModel;

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

    PageModel<UcUserVo> query(UcUserPageQuery pageQuery);

    PageModel<UcUserVo> query2(UcUserPageQuery pageQuery);
}
