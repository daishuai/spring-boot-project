package com.daishuai.curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/22 10:09
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class LeaderLatchDemo {

    protected static String PATH = "/francis/leader";
    private static final int CLIENT_QTY = 10;
    private static final String CONNECT_STRING = "172.20.10.8:2181";

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client
                        = CuratorFrameworkFactory.newClient(CONNECT_STRING, new ExponentialBackoffRetry(20000, 3));
                clients.add(client);
                LeaderLatch latch = new LeaderLatch(client, PATH, "Client #" + i);
                latch.addListener(new LeaderLatchListener() {

                    @Override
                    public void isLeader() {
                        // TODO Auto-generated method stub
                        System.out.println("I am Leader");
                    }

                    @Override
                    public void notLeader() {
                        // TODO Auto-generated method stub
                        System.out.println("I am not Leader");
                    }
                });
                examples.add(latch);
                client.start();
                latch.start();
            }
            Thread.sleep(10000);
            LeaderLatch currentLeader = null;
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
            currentLeader.close();

            Thread.sleep(5000);

            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
        } finally {
            for (LeaderLatch latch : examples) {
                if (LeaderLatch.State.STARTED == latch.getState()) {
                    CloseableUtils.closeQuietly(latch);
                }
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}
