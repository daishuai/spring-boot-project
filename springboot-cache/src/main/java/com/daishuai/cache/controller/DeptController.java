package com.daishuai.cache.controller;

import com.daishuai.cache.entity.Department;
import com.daishuai.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDeptById(id);
    }

    @PostMapping("/dept1")
    public Department saveDept(Department department){
        return deptService.saveDept(department);
    }

    @PostMapping("/dept2")
    public Department update(Department department){
        return deptService.updateDept(department);
    }

}
