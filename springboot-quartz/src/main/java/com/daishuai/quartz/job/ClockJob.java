package com.daishuai.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/17 18:40
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class ClockJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        String string = dateTime.toString().replace("T", " ");
        log.info("current time is {}", string.lastIndexOf(".") == -1 ? string : string.substring(0, string.lastIndexOf(".")));
        log.info("current date is {}", date.toString());
    }
}
