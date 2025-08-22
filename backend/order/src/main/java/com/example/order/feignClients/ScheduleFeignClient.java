package com.example.order.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
@FeignClient(name = "schedule-server",path ="/internal/schedule")
public interface ScheduleFeignClient {

    @PostMapping("/cancel-order")
    public void scheduleCancelOrder(@RequestParam("orderId")  String orderId,@RequestParam("delay") Long delay);
}
