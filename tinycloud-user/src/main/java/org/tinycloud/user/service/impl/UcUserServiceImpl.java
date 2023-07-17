package org.tinycloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.tinycloud.bean.entity.UcUser;
import org.tinycloud.bean.param.UcUserPageQuery;
import org.tinycloud.bean.vo.UcUserVo;
import org.tinycloud.common.consts.GlobalConstant;
import org.tinycloud.common.model.PageModel;
import org.tinycloud.common.utils.LambdaUtils;
import org.tinycloud.common.utils.StringUtils;
import org.tinycloud.common.utils.bean.BeanUtils;
import org.tinycloud.user.mapper.UcUserMapper;
import org.tinycloud.user.service.UcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;


@Service
public class UcUserServiceImpl implements UcUserService {

    @Autowired
    private UcUserMapper ucUserMapper;

    /**
     * 根据id查询详情
     * @param userId
     * @return
     */
    @Override
    public UcUserVo detail(String userId) {
        UcUser ucUser = this.ucUserMapper.selectOne(Wrappers.<UcUser>lambdaQuery()
                .eq(UcUser::getUserId, userId)
                .eq(UcUser::getDelFlag, GlobalConstant.NOT_DELETED));
        if (ucUser != null) {
            return BeanUtils.transformBean(ucUser, UcUserVo.class);
        }
        return null;
    }


    /**
     * 第二种动态排序的方法
     * @param pageQuery UcUserPageQuery
     * @return PageModel<UcUserVo>
     */
    @Override
    public PageModel<UcUserVo> query(UcUserPageQuery pageQuery) {
        PageModel<UcUserVo> responsePage = new PageModel<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        LambdaQueryWrapper<UcUser> wrapper = new LambdaQueryWrapper<>();

        boolean isAsc = false;
        if (GlobalConstant.ASC.equalsIgnoreCase(pageQuery.getSortType())) {
            isAsc = true;
        }

        wrapper.like(StringUtils.isNotEmpty(pageQuery.getUsername()), UcUser::getUsername, pageQuery.getUsername());
        wrapper.like(StringUtils.isNotEmpty(pageQuery.getNickname()), UcUser::getNickname, pageQuery.getNickname());
        wrapper.orderBy(StringUtils.isNotEmpty(pageQuery.getSortType()) && StringUtils.isNotEmpty(pageQuery.getSortFiled()),
                isAsc,
                LambdaUtils.getLambdaGetter(UcUser.class, pageQuery.getSortFiled()));

        Page<UcUser> page = this.ucUserMapper.selectPage(new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize()), wrapper);
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            responsePage.setTotalPage((int) page.getPages());
            responsePage.setTotalCount((int) page.getTotal());

            responsePage.setRecords(page.getRecords().stream().map(x -> {
                UcUserVo ucUserVo = new UcUserVo();
                BeanUtils.copyProperties(x, ucUserVo);
                return ucUserVo;
            }).collect(Collectors.toList()));
        }
        return responsePage;
    }

    /**
     * 第二种动态排序的方法
     * @param pageQuery UcUserPageQuery
     * @return PageModel<UcUserVo>
     */
    @Override
    public PageModel<UcUserVo> query2(UcUserPageQuery pageQuery) {
        PageModel<UcUserVo> responsePage = new PageModel<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        QueryWrapper<UcUser> wrapper = new QueryWrapper<>();

        boolean isAsc = false;
        if (GlobalConstant.ASC.equalsIgnoreCase(pageQuery.getSortType())) {
            isAsc = true;
        }

        wrapper.lambda().like(StringUtils.isNotEmpty(pageQuery.getUsername()), UcUser::getUsername, pageQuery.getUsername());
        wrapper.lambda().like(StringUtils.isNotEmpty(pageQuery.getNickname()), UcUser::getNickname, pageQuery.getNickname());
        wrapper.orderBy(StringUtils.isNotEmpty(pageQuery.getSortType()) && StringUtils.isNotEmpty(pageQuery.getSortFiled()),
                isAsc, StringUtils.humpToLine(pageQuery.getSortFiled()));

        Page<UcUser> page = this.ucUserMapper.selectPage(new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize()), wrapper);
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            responsePage.setTotalPage((int) page.getPages());
            responsePage.setTotalCount((int) page.getTotal());

            responsePage.setRecords(page.getRecords().stream().map(x -> {
                UcUserVo ucUserVo = new UcUserVo();
                BeanUtils.copyProperties(x, ucUserVo);
                return ucUserVo;
            }).collect(Collectors.toList()));
        }
        return responsePage;
    }
}
