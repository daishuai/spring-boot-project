package com.daishuai.quartz.job;

import com.daishuai.quartz.repository.JobConfigRepository;
import com.daishuai.quartz.scheduler.QuartzManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/6 11:36
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class JobCheckJob implements Job {

    @Autowired
    private JobConfigRepository jobConfigRepository;

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //定时更新任务

        //定时检查禁用任务
    }
}
