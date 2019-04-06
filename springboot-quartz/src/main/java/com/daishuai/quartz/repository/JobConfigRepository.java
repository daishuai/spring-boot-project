package com.daishuai.quartz.repository;

import com.daishuai.quartz.entity.JobConfigEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/5 18:56
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public interface JobConfigRepository extends Repository<JobConfigEntity, String> {

    /**
     * 根据启用标志查询
     * @param flag
     * @return
     */
    List<JobConfigEntity> findJobConfigEntitiesByFlag(Integer flag);
}
