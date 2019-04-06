package com.daishuai.redis.annotation;

import java.lang.annotation.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/18 20:31
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {
    /**
     * 锁的资源，redis的key
     * @return
     */
    String value() default "default";

    /**
     * 锁的有效期，单位毫秒
     * @return
     */
    long expireMillis() default 30000;

    /**
     * 重试的间隔时间，设置GIVEUP忽略此项
     * @return
     */
    long sleepMillis() default 200;

    /**
     * 重试次数
     * @return
     */
    int retryTimes() default 5;

    /**
     * 获取锁失败时，继续or放弃
     * @return
     */
    LockFailAction action() default LockFailAction.CONTINUE;

    /**
     * @author daishuai
     */
    public enum LockFailAction {
        /**
         * 放弃
         */
        GIVEUP,
        /**
         * 继续
         */
        CONTINUE;
    }
}
