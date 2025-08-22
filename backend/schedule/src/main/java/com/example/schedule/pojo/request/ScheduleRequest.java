package com.example.schedule.pojo.request;

import lombok.Data;

import java.time.Duration;

@Data
public class ScheduleRequest {
    private String orderId;
    private Long delay;
}
