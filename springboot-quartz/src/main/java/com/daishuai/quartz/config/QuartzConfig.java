package com.daishuai.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/17 16:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Configuration
public class QuartzConfig {


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        //是否自动启动
        schedulerFactory.setAutoStartup(false);
        //设置延时启动
        return schedulerFactory;
    }



}
