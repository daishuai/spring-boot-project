package com.daishuai.es.controller;

import com.daishuai.es.service.EmployeeService;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 17:08
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private EmployeeService esService;


    @RequestMapping("/get")
    public Object getEmployees() throws ExecutionException, InterruptedException {
        List result = new ArrayList<>();
        SearchHits searchHits = esService.getEmployees();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            result.add(hit.getSourceAsMap());
        }
        return result;
    }

}
