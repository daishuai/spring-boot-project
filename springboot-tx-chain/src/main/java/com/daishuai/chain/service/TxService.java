package com.daishuai.chain.service;

import com.daishuai.chain.dao.DeptDao;
import com.daishuai.chain.dao.UserDao;
import com.daishuai.chain.domain.primary.User;
import com.daishuai.chain.domain.secondary.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 20:18
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Service
public class TxService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DeptDao deptDao;

    @Transactional(rollbackFor = Exception.class)
    public void save(Map map) {
        User user = (User) map.get("user");
        Department department = (Department) map.get("department");
        userDao.saveUser(user);
        deptDao.saveDepartment(department);
        int i = 1/0;
    }

}
