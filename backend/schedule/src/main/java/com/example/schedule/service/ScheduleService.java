package com.example.schedule.service;

import java.time.Duration;

public interface ScheduleService {
    void addJob(String orderId, Duration delay);
}
