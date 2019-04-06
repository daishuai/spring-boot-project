package com.daishuai.curator;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 原生的Zookeeper实现分布式锁
 * @Author: daishuai
 * @CreateDate: 2019/2/20 15:06
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class ZookeeperDistributedLock {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperDistributedLock.class);

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    private ZookeeperDistributedLock() {
        //连接Zookeeper Server是异步创建会话的
        //通过一个监听器 CountDownLatch，来确认真正建立了zk server的连接
        try {
            this.zooKeeper = new ZooKeeper("172.20.10.8:2181", 50000, new ZookeeperWatcher());
        } catch (IOException e) {
            logger.info("connect Zookeeper Server occurred exception：{}", e.getMessage());
            e.printStackTrace();
        }
        //打印即时状态：验证是不是异步？
        logger.info(String.valueOf(zooKeeper.getState()));
        try {
            //等待zk server的连接
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void init() {
        getInstance();
    }

    /**
     * 获取单例
     * @return
     */
    public static ZookeeperDistributedLock getInstance() {
        return Singleton.getInstance();
    }


    /**
     * 获取锁
     * @param id
     * @param isBlock 是否阻塞获取锁
     */
    public void lock(Long id, boolean isBlock) {
        String path = "/ad-lock-" + id;

        try {
            zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.info("success to acquire lock for id = {}", id);
        } catch (Exception e) {
            // 如果id对应的锁node已经存在，就是已经北别人加锁，那么这里就会报错
            int count = 0;
            while (isBlock) {
                try {
                    Thread.sleep(1000);
                    zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e1) {
                    count ++;
                    logger.info("the {} times try to acquire lock for id = {}", count, id);
                    continue;
                }
                logger.info("success to acquire lock for id = {} , after {} times try ...", id, count);
                break;
            }
        }
    }

    /**
     * 释放锁
     * @param id
     */
    public void release(Long id) {
        String path = "/ad-lock-" + id;
        try {
            zooKeeper.delete(path, -1);
            logger.info("success release the lock for id = {}", id);
        } catch (Exception e) {
            logger.info("release lock occurred exception：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 静态内部类实现单例
     */
    private static class Singleton {
        private static ZookeeperDistributedLock distributedLock;

        static {
            distributedLock = new ZookeeperDistributedLock();
        }

        public static ZookeeperDistributedLock getInstance() {
            return distributedLock;
        }
    }


    /**
     * 建立zk session的watcher观察者
     * @author daishuai
     */
    private class ZookeeperWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                logger.info("success connect zookeeper server");
                connectedSemaphore.countDown();
            }
        }
    }
}
