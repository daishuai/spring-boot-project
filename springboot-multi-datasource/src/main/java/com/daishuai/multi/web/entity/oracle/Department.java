package com.daishuai.multi.web.entity.oracle;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: Oracle数据库Departments表
 * @Author: daishuai
 * @CreateDate: 2019/1/11 19:43
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    private Integer departmentId;

    private String departmentName;

    private Integer managerId;

    private Integer locationId;

}
