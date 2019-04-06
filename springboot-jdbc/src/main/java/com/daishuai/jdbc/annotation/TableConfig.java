package com.daishuai.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/14 16:31
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableConfig {
    String value();
}
