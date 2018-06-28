package com.daishuai.elasticsearch.repository;

import com.daishuai.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/28 20:11
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
}
