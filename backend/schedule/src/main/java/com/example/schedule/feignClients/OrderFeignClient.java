package com.example.schedule.feignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "order-server",path ="/internal/order")
public interface OrderFeignClient {
    @PutMapping("/cancel")
    public void cancelOrder(@RequestParam("orderId") String orderId);
}
