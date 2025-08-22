package com.example.order.controller;


import com.example.order.service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//接口方法返回对象，对象转换成json文本
@RequestMapping("/internal/order")// localhost:8088/user/**
public class InternalOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PutMapping("/cancel")
    public void cancelOrder(@RequestParam("orderId") String orderId){
        orderService.cancelOrder(orderId);
    }


}
