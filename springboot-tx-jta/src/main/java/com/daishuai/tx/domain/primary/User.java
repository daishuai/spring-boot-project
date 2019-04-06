package com.daishuai.tx.domain.primary;

/**
 * @Description: MySQL数据库中的User表
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:50
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class User {

    private Integer id;

    private String username;

    private String password;

    private String mobile;

    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
