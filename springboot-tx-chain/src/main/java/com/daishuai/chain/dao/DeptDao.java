package com.daishuai.chain.dao;

import com.daishuai.chain.domain.secondary.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 19:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class DeptDao {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Department findByDeptId(Integer deptId) {
        Department department = jdbcTemplate.queryForObject("select * from department where department_id = ?", new Object[]{deptId}, new BeanPropertyRowMapper<Department>(Department.class));
        return department;
    }

    public int saveDepartment(Department department) {
        String sql = "insert into department (department_id, department_name, manager_id, location_id) values (?,?,?,?)";
        Object[] params = new Object[]{department.getDepartmentId(),department.getDepartmentName(),department.getManagerId(),department.getLocationId()};
        int update = jdbcTemplate.update(sql, params);
        return update;
    }
}
