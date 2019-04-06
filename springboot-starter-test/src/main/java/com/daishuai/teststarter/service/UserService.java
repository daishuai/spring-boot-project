package com.daishuai.teststarter.service;

import com.daishuai.redis.annotation.RedisLock;
import com.daishuai.redis.concurrent.lock.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/19 14:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Service
public class UserService {

    @Autowired
    private DistributedLock distributedLock;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    //@RedisLock(value = "hello world", sleepMillis = 500)
    public Double drawMoney(Integer id) {
        //加锁
        boolean lock = distributedLock.lock(String.valueOf(id), 10000, 5, 500);
        if (lock) {
            try {
                log.info("正在执行业务逻辑，请等待。。。。。。。。");
                Thread.sleep(30000);
                log.info("任务结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //释放锁
            distributedLock.releaseLock(String.valueOf(id));
            return 100.0;
        }
        return 0.0;
    }

}
