package com.daishuai.jdbc.controller;

import com.daishuai.jdbc.common.SqlHelper;
import com.daishuai.jdbc.dao.BaseDao;
import com.daishuai.jdbc.domain.RespResult;
import com.daishuai.jdbc.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/14 16:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
public class TestController {

    @Autowired
    private BaseDao baseDao;

    @RequestMapping("/test.json")
    public RespResult test() {

        return RespResult.success(baseDao.getInsertSql(Student.class));
    }
}
