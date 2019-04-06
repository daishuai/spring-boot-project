package com.daishuai.chain.web;

import com.daishuai.chain.domain.secondary.Department;
import com.daishuai.chain.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 19:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/{deptId:\\d+}")
    public Department getDepartment(@PathVariable("deptId") Integer deptId) {
        return deptService.getDeptByDeptId(deptId);
    }
}
