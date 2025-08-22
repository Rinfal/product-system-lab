package com.example.schedule.service.serviceImpl;

import com.example.schedule.job.CancelOrderJob;
import com.example.schedule.service.ScheduleService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(String orderId, Duration delay) {
        JobDetail jobDetail = JobBuilder.newJob(CancelOrderJob.class)
                .withIdentity(orderId, "order-cancel")
                .usingJobData("orderId", orderId)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startAt(Date.from(Instant.now().plus(delay)))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
