package org.liuxingyu.tinycloud.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.liuxingyu.tinycloud.bean.entity.UcUser;
import org.liuxingyu.tinycloud.bean.vo.UcUserVo;
import org.liuxingyu.tinycloud.common.consts.GlobalConstant;
import org.liuxingyu.tinycloud.common.utils.bean.BeanUtils;
import org.liuxingyu.tinycloud.user.mapper.UcUserMapper;
import org.liuxingyu.tinycloud.user.service.UcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UcUserServiceImpl implements UcUserService {

    @Autowired
    private UcUserMapper ucUserMapper;

    @Override
    public UcUserVo detail(String userId) {
        UcUser ucUser = this.ucUserMapper.selectOne(Wrappers.<UcUser>lambdaQuery()
                .eq(UcUser::getUserId, userId)
                .eq(UcUser::getDelFlag, GlobalConstant.NOT_DELETED));

        return BeanUtils.transformBean(ucUser, UcUserVo.class);
    }

}
