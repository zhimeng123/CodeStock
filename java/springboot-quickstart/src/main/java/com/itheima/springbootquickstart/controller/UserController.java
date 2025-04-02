package com.itheima.springbootquickstart.controller;

import com.itheima.springbootquickstart.pojo.User;
import com.itheima.springbootquickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findById")
    public User findById(Integer id){
        return userService.findById(id);
    }
}
