package com.springcloudone.controller;

import com.springcloudone.entity.User;
import com.springcloudone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xw
 * @date 2020/6/4 11:26
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/queryAll")
    public List<User> queryAll(){
        List<User> lists = userService.queryAll();
        return lists;
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateUser(){
        User user = new User();
        user.setId(2);
        user.setUsername("cat");
        user.setPassword("miaomiao");
        user.setName("张三");

        int result = userService.updateUser(user);

        if(result != 0){
            return "update user success";
        }

        return "fail";
    }

    @ResponseBody
    @RequestMapping("/findUserById")
    public Map<String, Object> findUserById(@RequestParam int id){
        User user = userService.findUserById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("userName", user.getUsername());
        result.put("pass", user.getPassword());
        result.put("name", user.getName());
        return result;
    }
    @ResponseBody
    @RequestMapping("/deleteUserById")
    public String deleteUserById(@RequestParam int id){
        int result = userService.deleteUserById(id);
        if(result != 0){
            return "delete success";
        }
        return "delete fail";
    }
}
