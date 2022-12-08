package org.liuxingyu.tinycloud.user.controller;

import org.liuxingyu.tinycloud.common.base.Result;
import org.liuxingyu.tinycloud.user.config.TinyCloudUserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxingyu01
 * @date 2022-12-08 10:40
 * @description
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TinyCloudUserConfig tinyCloudUserConfig;


    @RequestMapping("/testuser")
    public Result testuser() {
        return Result.ok("调用成功", tinyCloudUserConfig.getName());
    }

}
