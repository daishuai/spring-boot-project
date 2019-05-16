package com.daishuai.quartz.starter;

import com.daishuai.quartz.cache.CacheTemplate;
import com.daishuai.quartz.entity.JobConfigEntity;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    @Autowired
    private CacheTemplate cacheTemplate;

    @Override
    public void run(String... args) throws Exception {
        JobConfigEntity jobConfig = new JobConfigEntity();
        jobConfig.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig.setStartAt(DateUtils.parseDate("2019-02-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig.getUuid(), jobConfig.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig.getUuid(), jobConfig);

        JobConfigEntity jobConfig1 = new JobConfigEntity();
        jobConfig1.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig1.setStartAt(DateUtils.parseDate("2018-03-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig1.getUuid(), jobConfig1.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig1.getUuid(), jobConfig1);

        JobConfigEntity jobConfig2 = new JobConfigEntity();
        jobConfig2.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig2.setStartAt(DateUtils.parseDate("2019-04-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig2.getUuid(), jobConfig2.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig2.getUuid(), jobConfig2);

        JobConfigEntity jobConfig3 = new JobConfigEntity();
        jobConfig3.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig3.setStartAt(DateUtils.parseDate("2017-05-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig3.getUuid(), jobConfig3.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig3.getUuid(), jobConfig3);

        JobConfigEntity jobConfig4 = new JobConfigEntity();
        jobConfig4.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig4.setStartAt(DateUtils.parseDate("2019-06-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig4.getUuid(), jobConfig4.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig4.getUuid(), jobConfig4);

        JobConfigEntity jobConfig5 = new JobConfigEntity();
        jobConfig5.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig5.setStartAt(DateUtils.parseDate("2019-05-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig5.getUuid(), jobConfig5.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig5.getUuid(), jobConfig5);

        JobConfigEntity jobConfig6 = new JobConfigEntity();
        jobConfig6.setUuid(UUID.randomUUID().toString().replace("-",""));
        jobConfig6.setStartAt(DateUtils.parseDate("2019-04-01 12:23:45","yyyy-MM-dd HH:mm:ss"));
        cacheTemplate.zSet("jobConfig", jobConfig6.getUuid(), jobConfig6.getStartAt().getTime());
        cacheTemplate.hashSet("jobConfigs", jobConfig6.getUuid(), jobConfig6);

        System.out.println(scheduler.isStarted() );
        scheduler.start();
    }
}
