package com.daishuai.multi.web.dao.mysql;

import com.daishuai.multi.web.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 16:53
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public interface UserDao extends JpaRepository<User, Integer> {

}
