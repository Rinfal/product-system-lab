package com.example.schedule.job;

import com.example.schedule.feignClients.OrderFeignClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderJob implements Job {

    @Autowired
    private OrderFeignClient orderFeignClient; // 注意：不能直接自动注入，详见后面说明

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String orderId = context.getMergedJobDataMap().getString("orderId");

        // 执行取消订单逻辑
        System.out.println("正在取消订单：" + orderId);

        orderFeignClient.cancelOrder(orderId);
    }
}
