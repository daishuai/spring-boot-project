package com.daishuai.quartz.config;

import com.daishuai.quartz.scheduler.QuartzManager;
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
        return schedulerFactory;
    }

    @Bean(initMethod = "init")
    public QuartzManager quartzManager() {
        QuartzManager quartzManager = new QuartzManager();
        quartzManager.setSchedulerFactoryBean(schedulerFactoryBean());
        return quartzManager;
    }

}
