package com.example.product.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


    @FeignClient(name = "auth-server",path ="/internal/user")
    public interface UserFeignClient {

        //先这么写吧以后再改
        @GetMapping("getUserNameById/{id}")
        String getUserNameById(@PathVariable("id") String productId);
    }
