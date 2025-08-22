package com.example.schedule.controller;


import com.example.schedule.pojo.request.ScheduleRequest;
import com.example.schedule.service.serviceImpl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/internal/schedule")
public class InternalScheduleController {


    @Autowired
    private ScheduleServiceImpl scheduleService;

    @PostMapping("/cancel-order")
    public void scheduleCancelOrder(@RequestParam("orderId")  String orderId,@RequestParam("delay") Long delay) {
        scheduleService.addJob(orderId, Duration.ofSeconds(delay));
    }
}
