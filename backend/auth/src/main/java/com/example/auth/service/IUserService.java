package com.example.auth.service;

import com.example.auth.pojo.dto.UserDto;

public interface IUserService {
    String add(UserDto userDto);

    String login(UserDto userDto);

    UserDto getUserById(String userId);

    UserDto getUserByName(String userName);

    UserDto getUserByPassword(String userPassword);
}
