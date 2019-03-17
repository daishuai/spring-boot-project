package com.daishuai.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/17 17:03
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        log.info("job name is:{}, job group is:{}", jobKey.getName(), jobKey.getGroup());
        log.info("执行调度任务，MyJob.......");
    }
}
