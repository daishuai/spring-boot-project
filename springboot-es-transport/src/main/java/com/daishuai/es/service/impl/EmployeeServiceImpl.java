package com.daishuai.es.service.impl;

import com.daishuai.es.entity.Employee;
import com.daishuai.es.service.EmployeeService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 17:22
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private TransportClient client;


    @Override
    public void insertEmployee(String index, String type, String id, Employee employee) {
        IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(employee).get();
    }


    @Override
    public SearchHits getEmployees() throws ExecutionException, InterruptedException {
        SearchRequest request = new SearchRequest();
        //查询的索引范围，对应数据库
        String[] indices = new String[]{"megacorp"};
        request.indices(indices);
        //查询的种类范围，对应数据库表
        String[] types = new String[]{"employee"};
        request.types(types);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //SortBuilder
        //termQuery()   TermQueryBuilder分词精确查询
        //rangeQuery()  RangeQueryBuilder范围查询
        //existQuery()  ExistsQueryBuilder查询字段不为空
        //prefixQuery() PrefixQueryBuilder匹配分词前缀，如果字段没分词，就匹配整个字段前缀
        //wildcardQuery()   WildcardQueryBuilder通配符查询，支持*任意字符串; ? 任意一个字符
        //regexpQuery() RegexpQueryBuilder正则表达式匹配分词
        //fuzzyQuery()  FuzzyQueryBuilder分词模糊查询
        QueryBuilder termQuery = QueryBuilders.termQuery("firstName", "zhangsan");
        searchSourceBuilder.from(0).size(2).sort("firstName");
        searchSourceBuilder.query(termQuery);
        SearchResponse response = client.search(request).get();
        SearchHits hits = response.getHits();
        return hits;
    }
}
