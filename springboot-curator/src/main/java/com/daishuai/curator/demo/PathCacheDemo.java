package com.daishuai.curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Description: Path Cache用来监控一个ZNode的子节点. 当一个子节点增加，
 * 更新，删除时， Path Cache会改变它的状态， 会包含最新的子节点，
 * 子节点的数据和状态，而状态的更变将通过PathChildrenCacheListener通知。
 * 实际使用时会涉及到四个类：
 *  --{@link PathChildrenCache}
 *  --{@link PathChildrenCacheListener}
 *  --{@link org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent}
 *  --{@link ChildData}
 * @Author: daishuai
 * @CreateDate: 2019/2/21 21:18
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class PathCacheDemo {

    private static final String PATH = "/example/pathCache";

    private static final String CONNECT_STRING = "172.20.10.8:2181";

    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_STRING, new ExponentialBackoffRetry(1000, 3));
        client.start();
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        cache.start();
        PathChildrenCacheListener cacheListener = (client1, event) -> {
            System.out.println("事件类型：" + event.getType());
            if (null != event.getData()) {
                System.out.println("节点数据：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
            }
        };
        cache.getListenable().addListener(cacheListener);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test01", "01".getBytes());
        Thread.sleep(10);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test02", "02".getBytes());
        Thread.sleep(10);
        client.setData().forPath("/example/pathCache/test01", "01_V2".getBytes());
        Thread.sleep(10);
        for (ChildData data : cache.getCurrentData()) {
            System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
        }
        client.delete().forPath("/example/pathCache/test01");
        Thread.sleep(10);
        client.delete().forPath("/example/pathCache/test02");
        Thread.sleep(1000 * 5);
        cache.close();
        client.close();
        System.out.println("OK!");
    }
}
