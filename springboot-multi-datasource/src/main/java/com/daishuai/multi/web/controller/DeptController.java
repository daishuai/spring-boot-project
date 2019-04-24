package com.daishuai.multi.web.controller;

import com.daishuai.multi.web.common.ResponseMessage;
import com.daishuai.multi.web.entity.oracle.Department;
import com.daishuai.multi.web.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/24 21:47
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@RequestMapping(value = "/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping(value = "/{id}")
    public ResponseMessage getDeptById(@PathVariable("id") Integer id) {
        Department dept = deptService.getDeptByDeptId(id);
        return ResponseMessage.ok(dept);
    }
}
