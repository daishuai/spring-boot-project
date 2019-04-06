package com.daishuai.chain.web;

import com.daishuai.chain.common.BeanUtils;
import com.daishuai.chain.domain.primary.User;
import com.daishuai.chain.domain.secondary.Department;
import com.daishuai.chain.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 20:22
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
@RequestMapping("/tx")
public class TxController {

    @Autowired
    private TxService txService;

    @RequestMapping("/save")
    public String save(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        Department department = new Department();
        BeanUtils.copyProperties((Map) map.get("user"), user);
        BeanUtils.copyProperties((Map) map.get("department"), department);
        Map params = new HashMap();
        params.put("user", user);
        params.put("department", department);
        txService.save(params);
        return "SUCCESS";
    }
}
