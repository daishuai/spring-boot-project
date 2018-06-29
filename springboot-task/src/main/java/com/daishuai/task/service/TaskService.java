package com.daishuai.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/29 14:09
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Service
public class TaskService {

    private static Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Async
    public void asyncGet(){
        try {
            Thread.sleep(5000);
            logger.info(">>>>>>>>>>>>>>>>>>>>开启异步执行！！！！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * second(秒) , minute(分), hour(时), day of month(天), month(月), day of week(周几)
     * "0 * * * * MON-FRI"
     *  , 枚举
     *  - 区间
     *  * 任意
     *  / 步长
     *  ? 日/星期冲突匹配
     *  L 最后
     *  W 工作日
     *  C 和Calender联系后计算过的值
     *  # 星期，4#2 第2个星期四
     */
    //@Scheduled(cron = "0/5 * * * * MON-FRI") 每周一到周五，每5秒执行一次
    @Scheduled(cron = "0 8 15 29 6 5")
    public void scheduledTask(){
        logger.info("定时任务：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
    }
}
