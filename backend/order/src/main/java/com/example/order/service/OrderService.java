package com.example.order.service;

import com.example.order.pojo.dto.OrderDto;

public interface OrderService {

    //查询库存
    Integer getStockById(String productId);


    void cancelOrder(String orderId);

    void reduceStock(String productId, Integer orderAmount);

    void releaseStock(String productId, Integer orderAmount);

    OrderDto createOrder(OrderDto orderDto);
}
