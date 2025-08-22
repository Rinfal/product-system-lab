package com.example.order.controller;

import com.example.common.pojo.ResponseMessage;
import com.example.order.feignClients.ProductFeignClient;
import com.example.order.mapper.OrderMapper;
import com.example.order.pojo.PojoConverter;
import com.example.order.pojo.dto.OrderDto;
import com.example.order.pojo.vo.OrderVo;
import com.example.order.service.OrderService;
import com.example.order.service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.common.security.JwtUtil.getUserIdByToken;

@RestController//接口方法返回对象，对象转换成json文本
@RequestMapping("/order")// localhost:8088/user/**
public class OrderController {


    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private PojoConverter pojoConverter;

    @Autowired
    private ProductFeignClient productFeignClient;


    @PostMapping("/create")
    public ResponseMessage<OrderVo> createOrder(@RequestBody OrderDto orderDto, @RequestHeader("X-User-Id") String userId)
    {
        //在controller层解读token得到userid
         orderDto.setUserId(userId);
         System.out.println(orderDto.getOrderTime());
        OrderVo orderVo = pojoConverter.Dto2Vo(orderService.createOrder(orderDto));

        return ResponseMessage.success(orderVo);
    }

}
