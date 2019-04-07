package com.daishuai.quartz.cache;

import java.util.Set;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/6 14:48
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public interface CacheTemplate {

    /**
     * 向hash表中新增数据
     * @param key
     * @param hashKey
     * @param value
     */
    void hashSet(String key, String hashKey, Object value);

    /**
     * 从hash表中获取数据
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T hashGet(String key, String hashKey, Class<T> clazz);

    /**
     * 向Sorted Set中新增数据
     * @param key
     * @param value
     * @param score
     */
    void zSet(String key, String value, double score);

    /**
     * 返回Sorted Set中指定分数区间内的成员
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<String> zRangeByScore(String key, double min, double max);

}
