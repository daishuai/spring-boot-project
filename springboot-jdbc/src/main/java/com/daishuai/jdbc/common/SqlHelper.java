package com.daishuai.jdbc.common;

import com.daishuai.jdbc.annotation.TableConfig;

import java.lang.annotation.Annotation;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/14 16:35
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class SqlHelper {

    /**
     * 获取表名
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        if (clazz.isAnnotationPresent(TableConfig.class)) {
            TableConfig tableConfig = (TableConfig) clazz.getAnnotation(TableConfig.class);
            return tableConfig.value();
        }
        return null;
    }
}
