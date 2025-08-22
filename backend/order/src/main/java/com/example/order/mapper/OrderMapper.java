package com.example.order.mapper;

import com.example.order.pojo.dto.OrderDto;
import com.example.order.pojo.po.Order;

public interface OrderMapper {

    void createOrder(OrderDto orderDto);

    void cancelOrder(String orderId);

    Order getUnpaidOrder(String userId, String productId);

    Order getOrderById(String orderId);
}
