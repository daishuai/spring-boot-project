package com.daishuai.cache.service;

import com.daishuai.cache.entity.Department;
import com.daishuai.cache.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/26 21:19
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Cacheable
    public Department getDeptById(Integer id){
        return deptRepository.findOne(id);
    }
}
