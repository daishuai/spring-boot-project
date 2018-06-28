package com.daishuai.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/28 20:09
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Document(indexName = "news", type = "sports")
public class Book {

    private Integer id;
    private String bookName;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
