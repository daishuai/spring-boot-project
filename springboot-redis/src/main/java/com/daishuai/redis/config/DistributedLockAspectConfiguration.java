package com.daishuai.redis.config;

import com.daishuai.redis.annotation.RedisLock;
import com.daishuai.redis.concurrent.lock.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description: 分布式锁切面
 * @Author: daishuai
 * @CreateDate: 2019/2/18 20:41
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Aspect
@Configuration
@ConditionalOnClass(DistributedLock.class)
@AutoConfigureAfter(DistributedLockAutoConfiguration.class)
public class DistributedLockAspectConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);

    @Autowired
    private DistributedLock distributedLock;

    /**
     * 切点：标有@RedisLock注解的方法
     */
    @Pointcut("@annotation(com.daishuai.redis.annotation.RedisLock)")
    public void lockPoint(){}

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if (!StringUtils.isEmpty(key)) {
            Object[] args = joinPoint.getArgs();
            key = Arrays.toString(args);
        }
        int retryTimes = redisLock.action().equals(RedisLock.LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;
        boolean lock = distributedLock.lock(key, redisLock.expireMillis(), retryTimes, redisLock.sleepMillis());
        if (!lock) {
            log.debug("get lock failed:{}", key);
            return null;
        }
        //得到锁，执行方法，释放锁
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("execute locked method occurred exception :{}", e.getMessage());
            e.printStackTrace();
        } finally {
            boolean releaseResult = distributedLock.releaseLock(key);
            log.debug("release lock :{} {}", key, releaseResult ? "success" : "failed");
        }
        return null;

    }
}
