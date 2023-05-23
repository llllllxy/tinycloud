package org.liuxingyu.tinycloud.message.controller;

import org.liuxingyu.tinycloud.api.UserApiClient;
import org.liuxingyu.tinycloud.bean.vo.UcUserVo;
import org.liuxingyu.tinycloud.common.model.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class MessageTestController {

    @Autowired
    private UserApiClient userApiClient;

    @GetMapping("/detail")
    public ApiResult<UcUserVo> detail(@RequestParam("userId") String userId) {
        return userApiClient.detail(userId);
    }
}
