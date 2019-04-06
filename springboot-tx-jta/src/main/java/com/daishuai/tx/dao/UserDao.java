package com.daishuai.tx.dao;

import com.daishuai.tx.domain.primary.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class UserDao {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    public User findById(Integer id) {
        User user = primaryJdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        return user;
    }

    public int saveUser(User user) {
        String sql = "insert into user (id, username, password, mobile, role) values (?,?,?,?,?)";
        Object[] params = new Object[]{user.getId(),user.getUsername(),user.getPassword(),user.getMobile(),user.getRole()};
        int update = primaryJdbcTemplate.update(sql, params);
        return update;
    }
}
