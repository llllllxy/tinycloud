package org.tinycloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.tinycloud.bean.entity.UcUser;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统用户信息表 Mapper 接口
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-05-17 11:49:24
 */
@Repository
public interface UcUserMapper extends BaseMapper<UcUser> {
}
