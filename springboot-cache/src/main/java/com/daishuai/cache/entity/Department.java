package com.daishuai.cache.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/6/26 21:12
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue
    private Integer departmentId;

    private String departmentName;

    private Integer managerId;

    private Integer locationId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
