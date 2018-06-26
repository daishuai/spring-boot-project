package com.daishuai.cache.repository;

import com.daishuai.cache.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/26 21:17
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface DeptRepository extends JpaRepository<Department,Integer> {
}
