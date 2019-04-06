package com.daishuai.jdbc.dao;

import com.daishuai.jdbc.common.SqlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/14 16:10
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Component
public class BaseDao {

    private final Logger log = LoggerFactory.getLogger(BaseDao.class);

    private JdbcTemplate jdbcTemplate;

    public void saveList(final List list, Class clazz) {
        jdbcTemplate.batchUpdate(getInsertSql(clazz), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {

            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public String getInsertSql(Class clazz) {
        String tableName = SqlHelper.getTableName(clazz);
        StringBuffer sql = new StringBuffer("insert into ").append(tableName).append(" (");
        StringBuffer param = new StringBuffer(" values (");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            sql.append(field.getName()).append(",");
            param.append("?,");
        }
        param.deleteCharAt(param.length() - 1).append(")");
        sql.deleteCharAt(sql.length() - 1).append(")").append(param);
        return sql.toString();
    }

    public void setValues(PreparedStatement preparedStatement, Object bean) {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field field = null;
        for (int i=0; i<fields.length; i++) {
            field = fields[i];
            field.setAccessible(true);
            try {
                preparedStatement.setObject(i + 1, field.get(bean));
            } catch (Exception e) {
                log.info("PreparedStatement setValue error:{}", field.getName());
            }
        }
    }
}
