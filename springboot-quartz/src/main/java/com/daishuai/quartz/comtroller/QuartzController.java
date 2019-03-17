package com.daishuai.quartz.comtroller;

import com.daishuai.quartz.job.ClockJob;
import com.daishuai.quartz.scheduler.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/17 17:05
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
@RequestMapping("/job")
public class QuartzController {

    @Autowired
    private QuartzManager quartzManager;

    @GetMapping("/add")
    public void addJob() {
        quartzManager.addJob("clockJob", "group1", "trigger1", "group1", "0/5 * * * * ?", ClockJob.class);
    }

    @GetMapping("/remove")
    public void removeJob() {
        quartzManager.removeJob("clockJob", "group1", "trigger1", "group1");
    }
}
