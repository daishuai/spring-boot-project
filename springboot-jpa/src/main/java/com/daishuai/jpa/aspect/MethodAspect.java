package com.daishuai.jpa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/23 16:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Aspect
@Component
public class MethodAspect {

    private static Logger logger = LoggerFactory.getLogger(MethodAspect.class);


    @Pointcut("execution(* com.daishuai.jpa..*.* (..))")
    private void pointCutMethod(){}


    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //logger.info("调用方法：{}，执行耗时：{}", joinPoint.getSignature().getName(), (end - start));
        //logger.info("调用方法：{}，执行耗时：{}", joinPoint.getSignature().toLongString(), (end - start));
        logger.info("调用方法：{}，执行耗时：{}", joinPoint.getSignature().toString(), (end - start));
        //logger.info("调用方法：{}，执行耗时：{}", joinPoint.getSignature().toShortString(), (end - start));
        //logger.info("调用方法：{}，执行耗时：{}", joinPoint.getSignature().getDeclaringTypeName(), (end - start));
        return object;
    }
}
