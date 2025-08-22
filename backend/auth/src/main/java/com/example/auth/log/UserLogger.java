package com.example.auth.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserLogger {


    private static final Logger log = LoggerFactory.getLogger(UserLogger.class);

    // 切入点：拦截所有 controller 层的方法
    @Pointcut("execution(* com.example.auth.controller..*(..))")
    public void controllerMethods() {}

    // 环绕通知
    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 方法名
        String methodName = joinPoint.getSignature().toShortString();
        // 参数
        Object[] args = joinPoint.getArgs();
        log.info("进入方法：{}，参数：{}", methodName, Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed(); // 执行方法
        } catch (Exception e) {
            log.error("方法异常：{}", methodName);
            throw e;
        }

        long duration = System.currentTimeMillis() - start;
        log.info("方法返回：{}，耗时：{}ms", methodName, duration);
        return result;
    }
}
