package com.daishuai.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Curator实现分布式锁
 * @Author: daishuai
 * @CreateDate: 2019/2/22 10:37
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
*/
public class CuratorDistributedLock implements DisposableBean {

    private static final String CONNECT_STRING = "172.20.10.8:2181";

    private static final int CONNECTION_TIMEOUT_MILLIS = 5000;

    private static final int SESSION_TIMEOUT_MILLIS = 5000;

    private ThreadLocal<InterProcessMutex> locks = new ThreadLocal<InterProcessMutex>();


    private static final Logger logger = LoggerFactory.getLogger(CuratorDistributedLock.class);

    private CuratorFramework client;

    public void init() {
        client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .connectionTimeoutMs(CONNECTION_TIMEOUT_MILLIS)
                .sessionTimeoutMs(SESSION_TIMEOUT_MILLIS)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
    }

    public CuratorDistributedLock () {
        init();
    }

    public void lock (String path, long timeout) {
        InterProcessMutex lock = locks.get();
        if (lock == null) {
            lock = new InterProcessMutex(client, path);
        }
        try {
            if (timeout > 0) {
                lock.acquire(timeout, TimeUnit.MILLISECONDS);
                boolean acquire = lock.acquire(timeout, TimeUnit.MILLISECONDS);
                if (acquire) {
                    locks.set(lock);
                    logger.info("lockName = {}, success acquire lock", path);
                }
            } else {
                lock.acquire();
                locks.set(lock);
                logger.info("lockName = {}, success acquire lock", path);
            }
        } catch (Exception e) {
            logger.info("lock (lockName = {} ) occurred exception: {}", path, e.getMessage());
            e.printStackTrace();
        }
    }

    public void releaseLock() {
        InterProcessMutex lock = locks.get();
        try {
            lock.release();
            logger.info("success release lock");
        } catch (Exception e) {
            logger.info("release lock occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() throws Exception {
        if (client != null) {
            client.close();
        }
    }
}
