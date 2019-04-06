package com.daishuai.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/15 12:28
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Entity
@Table(name = "departments")
@Data
public class Department implements Serializable {

    @Id
    private Integer departmentId;

    private String departmentName;

    private Integer managerId;

    private Integer locationId;


}
