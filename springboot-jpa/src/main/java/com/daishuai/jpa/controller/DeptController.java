package com.daishuai.jpa.controller;

import com.daishuai.jpa.entity.Department;
import com.daishuai.jpa.repository.DeptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/15 12:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptRepository repository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @RequestMapping("/saves")
    public List<Department> addList(){
        List<Department> departments = new ArrayList<>();
        for (int i=0; i < 3;i ++){
            Department department = new Department();
            department.setDepartmentId(1000+i);
            department.setDepartmentName("测试部" + i + 1);
            department.setLocationId(1230 + i);
            department.setManagerId(2340 + i);
            departments.add(department);
        }
        logger.info("事务管理器1111111111111：{}", transactionManager.getClass().getName());
        //List<Department> save = repository.save(departments);
        return departments;
    }
}
