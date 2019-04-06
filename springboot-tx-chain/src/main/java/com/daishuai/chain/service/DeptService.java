package com.daishuai.chain.service;

import com.daishuai.chain.dao.DeptDao;
import com.daishuai.chain.domain.secondary.Department;
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
        return deptDao.findByDeptId(deptId);
    }

    /**
     * 多数据源需要指定事务管理器
     * @param department
     * @return
     */
    @Transactional(value = "secondaryTransactionManager", rollbackFor = Exception.class)
    public int saveDepartment(Department department) {
        return deptDao.saveDepartment(department);
    }
}
