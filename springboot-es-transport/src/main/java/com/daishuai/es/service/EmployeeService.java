package com.daishuai.es.service;

import com.daishuai.es.entity.Employee;
import org.elasticsearch.search.SearchHits;

import java.util.concurrent.ExecutionException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 16:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public interface EmployeeService {

    /**
     * 新增雇员信息
     * @param index (database)
     * @param type (table)
     * @param id
     * @param employee
     */
    void insertEmployee(String index, String type, String id, Employee employee);

    /**
     * 查询雇员信息
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    SearchHits getEmployees() throws ExecutionException, InterruptedException;
}
