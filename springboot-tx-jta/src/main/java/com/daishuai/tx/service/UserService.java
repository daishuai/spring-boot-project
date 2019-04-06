package com.daishuai.tx.service;

import com.daishuai.tx.dao.UserDao;
import com.daishuai.tx.domain.primary.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:57
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public User getUserById(Integer id) {
        return userDao.findById(id);
    }

    @Transactional(value = "primaryTransactionManager", rollbackFor = Exception.class)
    public int saveUser(User user) {
        return userDao.saveUser(user);
    }

}
