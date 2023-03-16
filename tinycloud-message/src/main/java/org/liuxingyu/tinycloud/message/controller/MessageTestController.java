package org.liuxingyu.tinycloud.message.controller;

import org.liuxingyu.tinycloud.api.UserApiClient;
import org.liuxingyu.tinycloud.common.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxingyu01
 * @date 2022-12-08 14:35
 * @description
 **/
@RestController
@RequestMapping("/test")
public class MessageTestController {

    @Autowired
    private UserApiClient userApiClient;

    @RequestMapping("/testopenfeign")
    public Result testopenfeign() {
        return userApiClient.getTestUserInfo("张三");
    }
}
