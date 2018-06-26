package com.daishuai.cache.controller;

import com.daishuai.cache.entity.Department;
import com.daishuai.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/26 21:18
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDeptById(id);
    }
}
