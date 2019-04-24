package com.daishuai.multi.web.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: MySQL数据库中的User表
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:50
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

    private String mobile;

    private String role;

}
