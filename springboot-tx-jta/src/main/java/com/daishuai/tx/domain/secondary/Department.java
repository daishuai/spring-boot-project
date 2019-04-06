package com.daishuai.tx.domain.secondary;

/**
 * @Description: Oracle数据库Departments表
 * @Author: daishuai
 * @CreateDate: 2019/1/11 19:43
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Department {

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
