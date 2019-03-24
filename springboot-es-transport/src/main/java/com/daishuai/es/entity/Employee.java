package com.daishuai.es.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 17:15
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Data
public class Employee {

    private String firstName;

    private String lastName;

    private Integer age;

    private String about;

    private List<String> interests;
}
