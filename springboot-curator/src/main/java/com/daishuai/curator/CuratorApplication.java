package com.daishuai.curator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/20 15:03
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@SpringBootApplication
@RestController
public class CuratorApplication {

    private ZookeeperDistributedLock distributedLock = ZookeeperDistributedLock.getInstance();

    @Autowired
    private ZookeeperLock zookeeperLock;

    @Autowired
    private CuratorDistributedLock curatorDistributedLock;

    private static final Logger logger = LoggerFactory.getLogger(CuratorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CuratorApplication.class, args);
    }

    @RequestMapping("/hello/{id:\\d+}")
    public String hello(@PathVariable Long id) {
        zookeeperLock.lock("hello", "".getBytes());
        try {
            logger.info("execute business, please wait ...");
            reentry();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            zookeeperLock.releaseLock();
        }
        return "Hello World";
    }


    private void reentry() {
        curatorDistributedLock.lock("/curator", 0);
        logger.info("execute second method that use the same lock, please waiting ...");
        thirdEntry();
        curatorDistributedLock.releaseLock();
    }

    private void thirdEntry() {
        curatorDistributedLock.lock("/curator", 0);
        logger.info("execute third method that use the same lock, please waiting ...");
        curatorDistributedLock.releaseLock();
    }


    @RequestMapping("/curator")
    public String curatorLock() {
        curatorDistributedLock.lock("/curator", 0);
        try {
            logger.info("execute business, please wait ...");
            reentry();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            curatorDistributedLock.releaseLock();
        }
        return "CuratorDistributedLock";
    }
}
