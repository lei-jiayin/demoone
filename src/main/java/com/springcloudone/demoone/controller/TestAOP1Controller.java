package com.springcloudone.demoone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试aop 1.0
 * @author xw
 * @date 2019/7/18 10:33
 */
@RestController
@RequestMapping("/aopA")
public class TestAOP1Controller {

    @GetMapping("/sayFuck")
    public String sayHello(String name){
            return "fuck" + name;
    }
}
