package org.liuxingyu.tinycloud.user.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.liuxingyu.tinycloud.bean.entity.UcUser;
import org.liuxingyu.tinycloud.bean.vo.UcUserVo;
import org.liuxingyu.tinycloud.common.model.ApiResult;
import org.liuxingyu.tinycloud.user.service.UcUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;


@Api(tags = "用户中心-系统用户服务接口", value = "用户中心-系统用户服务接口")
@RestController
@RequestMapping("/api/user")
public class UcUserProvider {
    public static final Logger logger = LoggerFactory.getLogger(UcUserProvider.class);

    @Autowired
    private UcUserService ucUserService;

    @ApiOperation(value = "系统用户获取", notes = "系统用户获取")
    @GetMapping(value = "/detail")
    public ApiResult<UcUserVo> detail(@RequestParam("userId") String userId, HttpServletRequest request) {
        // 打印请求头的信息
        Enumeration<String> headerNames = request.getHeaderNames();
        if (Objects.nonNull(headerNames)) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                logger.info("请求头 {} : {}", name, value);
            }
        }

        // 调用service层
        UcUserVo ucUserVo = ucUserService.detail(userId);
        if (ucUserVo != null) {
            return ApiResult.success(ucUserVo, "查询成功!");
        } else {
            return ApiResult.fail("查询失败，用户不存在!");
        }
    }
}
