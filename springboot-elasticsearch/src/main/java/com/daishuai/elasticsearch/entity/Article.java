package com.daishuai.elasticsearch.entity;

import io.searchbox.annotations.JestId;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/28 17:23
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Article {

    @JestId
    private Integer id;
    private String title;
    private String author;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
