package com.daishuai.curator;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/21 14:01
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class ZookeeperLock {

    private static final String connectString = "172.20.10.8:2181";
    //private static final String connectString = "192.168.31.194:2181";

    private static final int sessionTimeout = 5000;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ThreadLocal<String> nodeId = new ThreadLocal<String>();

    private ThreadLocal<Map> info = new ThreadLocal<Map>();

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    private String root = "/locks";

    private ZooKeeper zooKeeper;

    public ZookeeperLock() {
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        logger.info("success connect zookeeper server");
                        try {
                            Stat stat = zooKeeper.exists(root, false);
                            if (stat == null) {
                                zooKeeper.create(root, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        connectedSemaphore.countDown();
                    }
                }
            });
        } catch (IOException e) {
            logger.info("connect zookeeper server occurred exception:{}", e.getMessage());
            e.printStackTrace();
        }
        try {
            logger.info("connecting zookeeper server ....");
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            logger.info("connect zookeeper server was interrupted:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void lock(String lockName, byte[] data) {
        Map infos = this.info.get();
        if (infos != null) {
            Integer count = (Integer) infos.get("count");
            count ++;
            infos.put("count", count);
            info.set(infos);
            return;
        }
        //新建临时顺序节点
        String currentNode;
        try {
            currentNode = zooKeeper.create(root + "/" + lockName, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            List<String> children = zooKeeper.getChildren(root, false);
            TreeSet<String> nodes = new TreeSet<String>();
            for (String child : children) {
                nodes.add(root + "/" + child);
            }
            String minNode = nodes.first();
            //当前节点是最小的节点获取锁
            if (currentNode.equals(minNode)) {
                this.nodeId.set(currentNode);
                infos = new HashMap();
                infos.put("count", 1);
                info.set(infos);
                logger.info("lockName = {} success acquire lock ", currentNode);
                return;
            }
            CountDownLatch latch = new CountDownLatch(1);
            //当前节点不是最小的节点，获取比其小的节点
            String preNode = nodes.lower(currentNode);
            Stat stat = zooKeeper.exists(preNode, new LatchWatch(latch));
            if (stat != null) {
                latch.await();
            }
            //手动置空，等待回收
            latch = null;
            this.nodeId.set(currentNode);
            infos = new HashMap();
            infos.put("count", 1);
            info.set(infos);
            logger.info("lockName = {} success acquire lock ", currentNode);
        } catch (Exception e) {
            logger.info("lockName = {} acquire lock occurred exception: {}", lockName, e.getMessage());
            e.printStackTrace();
        }

    }

    public void releaseLock() {
        Map infos = this.info.get();
        Integer count = (Integer) infos.get("count");
        if (count > 0) {
            count --;
            if (count == 0) {
                try {
                    zooKeeper.delete(this.nodeId.get(), -1);
                    logger.info("lockName = {} success release lock ", this.nodeId.get());
                    this.nodeId.remove();
                    this.info.remove();
                    return;
                } catch (Exception e) {
                    logger.info("lockName = {} release lock occurred exception: {}", this.nodeId.get(), e.getMessage());
                    e.printStackTrace();
                }
            }
            infos.put("count", count);
            this.info.set(infos);
        }

    }

    /**
     * @author daishuai
     */
    private class LatchWatch implements Watcher {

        private CountDownLatch latch;

        public LatchWatch(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            //监听到节点被删时
            if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                latch.countDown();
            }
        }
    }
}
