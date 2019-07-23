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
@RequestMapping("/aopC")
public class TestAOPController {

    @GetMapping("/sayHello")
    public String sayHello(String name){
        return "hello" + name;
    }
}
