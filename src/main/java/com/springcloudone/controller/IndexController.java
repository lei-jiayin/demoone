package com.springcloudone.controller;

import com.springcloudone.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xw
 * @date 2019/9/19 11:03
 */
@Controller
@RequestMapping("test")
public class IndexController {

    //@CrossOrigin("http://127.0.0.1:62001")
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(User user){
        System.out.println("--------user值=" + user + "," + "当前类=IndexController.login()");
        Map<String, Object> map = new HashMap<>();
        if("123".equals(user.getPassword()) && "abc".equals(user.getUsername())){
            map.put("code","1");
            map.put("message","登录成功");
        }else {
            map.put("code","0");
            map.put("message","登录失败");
        }

        return map;
    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("进入index");
        return "/index";
    }
}
