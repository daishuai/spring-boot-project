package com.daishuai.quartz.scheduler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.*;
import static org.quartz.JobKey.*;
/**TriggerKey
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/17 18:23
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Setter
@Slf4j
public class QuartzManager {

    private SchedulerFactoryBean schedulerFactoryBean;

    private Scheduler scheduler;

    public void init() throws Exception {
        if (schedulerFactoryBean != null) {
            scheduler = schedulerFactoryBean.getScheduler();
            //启动定时任务
            scheduler.start();
            return;
        }
        log.info("schedulerFactoryBean is null");
        throw new Exception("schedulerFactoryBean is null");
    }

    /**
     * 新增定时任务
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     * @param cron
     * @param clazz
     * @param taskData
     */
    public void addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String cron, Class<? extends Job> clazz, Map<String, Object> taskData) {
        JobDataMap jobDataMap = new JobDataMap(taskData);
        JobDetail jobDetail = newJob(clazz).withIdentity(jobName, jobGroup).usingJobData(jobDataMap).build();
        CronScheduleBuilder schedule = cronSchedule(cron);
        Trigger trigger = newTrigger().withIdentity(triggerName, triggerGroup).startNow().withSchedule(schedule).build();
        try {
            if (scheduler.checkExists(jobKey(jobName, jobGroup))) {
                log.info("job already exist, job's name : {}, job's group : {}", jobName, jobGroup);
                return;
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("scheduleJob occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 新增定时任务
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     * @param cron
     * @param clazz
     */
    public void addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String cron, Class<? extends Job> clazz) {
        this.addJob(jobName, jobGroup, triggerName, triggerGroup, cron, clazz, null);
    }

    /**
     * 更新定时任务的触发规则：(先删除旧的，再新增)
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     * @param newCron
     */
    public void updateJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String newCron) {
        //方法二
        JobKey jobKey = jobKey(jobName, jobGroup);
        try {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            Class<? extends Job> clazz = jobDetail.getJobClass();
            //先删除旧的job
            removeJob(jobName, jobGroup, triggerName, triggerGroup);
            //再新增job
            addJob(jobName, jobGroup, triggerName, triggerGroup, newCron,clazz);
        } catch (SchedulerException e) {
            log.info("update job occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 更新定时任务的触发规则：(使用rescheduleJob()方法)
     * @param triggerName
     * @param triggerGroup
     * @param newCron
     */
    public void updateJob(String triggerName, String triggerGroup, String newCron) {
        //方法一
        TriggerKey triggerKey = triggerKey(triggerName, triggerGroup);
        try {
            CronTrigger oldTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            String oldCron = oldTrigger.getCronExpression();
            if (!oldCron.equalsIgnoreCase(newCron)) {
                CronScheduleBuilder schedule = cronSchedule(newCron);
                CronTrigger newTrigger = newTrigger().withIdentity(triggerName,triggerGroup)
                        .startNow().withSchedule(schedule).build();
                scheduler.rescheduleJob(triggerKey, newTrigger);
            }
        } catch (SchedulerException e) {
            log.info("update job occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 移除定时任务
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     */
    public void removeJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        TriggerKey triggerKey = triggerKey(triggerName, triggerGroup);
        JobKey jobKey = jobKey(jobName, jobGroup);
        try {
            //停止触发器
            scheduler.pauseTrigger(triggerKey);
            //移除触发器
            scheduler.unscheduleJob(triggerKey);
            //删除任务
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.info("remove job occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdown() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            log.info("shutdown jobs occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
