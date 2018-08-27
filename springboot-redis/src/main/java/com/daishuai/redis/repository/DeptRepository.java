package com.daishuai.redis.repository;

import com.daishuai.redis.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/15 12:32
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface DeptRepository extends JpaRepository<Department, Integer> {
}
