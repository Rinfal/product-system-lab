package com.example.auth.controller;

import com.example.auth.pojo.dto.UserDto;
import com.example.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//接口方法返回对象，对象转换成json文本
@RequestMapping("/internal/user")
public class InternalUserController {
    @Autowired
    IUserService userService;

    @GetMapping("getUserNameById/{id}")
    public String getUserNameById(@PathVariable("id") String usertId){
        return userService.getUserById(usertId).getUserName();
    }

}
