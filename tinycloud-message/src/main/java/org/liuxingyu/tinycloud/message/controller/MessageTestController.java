package org.liuxingyu.tinycloud.message.controller;

import org.liuxingyu.tinycloud.api.RemoteUserService;
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
    private RemoteUserService remoteUserService;

    @RequestMapping("/testopenfeign")
    public Result testopenfeign() {
        return remoteUserService.getTestUserInfo("张三");
    }
}
