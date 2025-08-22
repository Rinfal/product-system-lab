package com.example.auth.pojo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    private String userId;

    private String userName;

    private String userPassword;
}
