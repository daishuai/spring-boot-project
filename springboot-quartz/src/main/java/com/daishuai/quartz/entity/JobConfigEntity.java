package com.daishuai.quartz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/5 18:54
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Data
@Entity
@Table(name = "job_config")
public class JobConfigEntity {

    @Id
    private String uuid;

    private String jobName;

    private String jobGroup;

    private String triggerName;

    private String triggerGroup;

    private String cron;

    private String jobPath;

    private Integer flag;

    private Date startAt;
}
