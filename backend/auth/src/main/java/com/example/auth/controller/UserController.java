package com.example.auth.controller;

import com.example.auth.pojo.dto.UserDto;
import com.example.auth.service.IUserService;
import com.example.common.pojo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//接口方法返回对象，对象转换成json文本
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    //BUG：这名字要改成register
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody UserDto userDto) {
        return ResponseMessage.success(userService.add(userDto));
    }

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody UserDto userDto) {
        String token = userService.login(userDto);
        return ResponseMessage.success(token);
    }

    @GetMapping("/getUserName")
    public ResponseMessage getUserName(@RequestHeader("X-User-Id") String userId) {
        String UserName = userService.getUserById(userId).getUserName();
        return ResponseMessage.success(UserName);
    }


}
