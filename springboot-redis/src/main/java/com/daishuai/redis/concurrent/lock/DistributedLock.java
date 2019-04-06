package com.daishuai.redis.concurrent.lock;

/**
 * @Description: 分布式锁接口
 * @Author: daishuai
 * @CreateDate: 2019/2/18 16:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public interface DistributedLock {
    /**
     * 锁超时时间
     */
    public static final long TIMEOUT_MILLS = 30000;

    /**
     * 重试次数
     */
    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    /**
     * 重试间隔时间，默认500毫秒
     */
    public static final long SLEEP_MILLIS = 500;

    /**
     * 设置锁
     * @param key
     * @return
     */
    public boolean lock(String key);

    /**
     * 设置锁，重试次数
     * @param key
     * @param retryTimes
     * @return
     */
    public boolean lock(String key, int retryTimes);

    /**
     * 设置锁，重试次数，重试间隔
     * @param key
     * @param retryTimes
     * @param sleepMillis
     * @return
     */
    public boolean lock(String key, int retryTimes, long sleepMillis);

    /**
     * 设置锁，过期时间
     * @param key
     * @param expire
     * @return
     */
    public boolean lock(String key, long expire);

    /**
     * 设置锁，有效期，重试次数
     * @param key
     * @param expire
     * @param retryTimes
     * @return
     */
    public boolean lock(String key, long expire, int retryTimes);

    /**
     * 设置锁，有效期，重试次数，重试间隔
     * @param key
     * @param expire
     * @param retryTimes
     * @param sleepMillis
     * @return
     */
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    /**
     * 释放锁
     * @param key
     * @return
     */
    public boolean releaseLock(String key);

    /**
     * 设置key, value
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value);

}
