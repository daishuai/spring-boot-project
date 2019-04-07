package com.daishuai.quartz.controller;

import com.daishuai.quartz.scheduler.QuartzManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/17 17:05
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class QuartzController {

    @Autowired
    private QuartzManager quartzManager;

    @GetMapping("/add")
    public void addJob(@RequestParam("jobName") String jobName,
                       @RequestParam("jobGroup") String jobGroup,
                       @RequestParam("triggerName") String triggerName,
                       @RequestParam("triggerGroup") String triggerGroup,
                       @RequestParam("cron") String cron,
                       @RequestParam("className") String className) {
        try {
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(className);
            quartzManager.addJob(jobName, jobGroup, triggerName, triggerGroup, cron, clazz, new Date());
        } catch (ClassNotFoundException e) {
            log.info("{} is not exist, please check", className);
            e.printStackTrace();
        } catch (Exception e) {
            log.info("{} must be the org.quartz.Job Implementing Classes");
            e.printStackTrace();
        }
    }

    @GetMapping("/remove")
    public void removeJob(@RequestParam("jobName") String jobName,
                          @RequestParam("jobGroup") String jobGroup,
                          @RequestParam("triggerName") String triggerName,
                          @RequestParam("triggerGroup") String triggerGroup) {
        quartzManager.removeJob(jobName, jobGroup, triggerName, triggerGroup);
    }

    @GetMapping("/update")
    public void updateJob(@RequestParam("jobName") String jobName,
                          @RequestParam("jobGroup") String jobGroup,
                          @RequestParam("triggerName") String triggerName,
                          @RequestParam("triggerGroup") String triggerGroup,
                          @RequestParam("cron") String cron) {
        quartzManager.updateJob(triggerName, triggerGroup, cron);
    }
}
