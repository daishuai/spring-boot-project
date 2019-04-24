package com.daishuai.multi.web.dao.oracle;

import com.daishuai.multi.web.entity.oracle.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 19:45
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public interface DeptDao extends JpaRepository<Department, Integer> {

}
