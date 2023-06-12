package org.xlw.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xlw.common.nacos.example.ExampleConfig;
import org.xlw.common.nacos.example.ExampleConfig2;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/8 15:10
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String test() {
        return ExampleConfig.getInstance().toString();
    }

    @RequestMapping("/config")
    public String test2() {
        return ExampleConfig2.getInstance().toString();
    }

}
