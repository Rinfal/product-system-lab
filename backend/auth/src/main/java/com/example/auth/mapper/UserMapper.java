package com.example.auth.mapper;

import com.example.auth.pojo.dto.UserDto;
import com.example.auth.pojo.po.User;

public interface UserMapper{

    void insert(UserDto userDto);

    User getUserPassword(String userName);

    User getUserById(String userId);

    User getUserByName(String userName);

    User getUserByPassword(String userPassword);
}
