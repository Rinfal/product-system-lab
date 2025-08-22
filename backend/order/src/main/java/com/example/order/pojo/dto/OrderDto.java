package com.example.order.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private String orderId;
    //0，1，2，3:待支付，已支付，已取消，已完成
    private Integer orderStatus;
    private LocalDateTime orderTime;
    //数量目前阶段默认为1
    private Integer orderAmount;
    private String userId;
    private String userName;
    private String productId;
    private String productName;
    private LocalDateTime expireTime;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;


}
