package com.daishuai.multi.web.service;

import com.daishuai.multi.web.dao.oracle.DeptDao;
import com.daishuai.multi.web.entity.oracle.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 19:49
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Service
public class DeptService {

    @Autowired
    private DeptDao deptDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Department getDeptByDeptId(Integer deptId) {
        return deptDao.findOne(deptId);
    }

    /**
     * 多数据源需要指定事务管理器
     * @param department
     * @return
     */
    @Transactional(value = "oracleTransactionManager", rollbackFor = Exception.class)
    public Department saveDepartment(Department department) {
        return deptDao.save(department);
    }
}
