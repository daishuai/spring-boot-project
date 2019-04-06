package com.daishuai.chain.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/1/11 20:41
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class BeanUtils {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public static void copyProperties(Map map, Object bean) {
        Class<?> cls = bean.getClass();
        //cls.getFields();//获取所有public修饰的属性，包括父类的public修饰的属性
        //cls.getDeclaredFields();//获取所有属性，包括public,protected,private修饰的，但不包括父类属性
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                field.set(bean, map.get(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.info("赋值出错，field:{},value:{}", field.getName(), map.get(field.getName()));
            }
        }
    }
}
