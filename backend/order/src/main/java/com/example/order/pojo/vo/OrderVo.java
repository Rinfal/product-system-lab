package com.example.order.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderVo {
    private String orderId;
    //0，1，2，3:待支付，已支付，已取消，已完成
    private String orderStatusView;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expireTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime orderTime;
    //数量目前阶段默认为1
    private Integer orderAmount;
    private String userName;
    private String productName;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

}
