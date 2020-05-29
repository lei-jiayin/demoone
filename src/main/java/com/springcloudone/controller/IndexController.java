package com.springcloudone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xw
 * @date 2019/9/19 11:03
 */
@Controller
@RequestMapping("test")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("进入index");
        return "/index";
    }
}
