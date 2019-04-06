package com.daishuai.quartz.starter;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/5 18:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class JobCommandLineRunner implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(scheduler.isStarted() );
        scheduler.start();
    }
}
