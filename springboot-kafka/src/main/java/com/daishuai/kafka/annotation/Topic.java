package com.daishuai.kafka.annotation;

import java.lang.annotation.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/5/5 21:21
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Topic {

    String value() default "";
}
