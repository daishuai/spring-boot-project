package com.daishuai.quartz.controller;

import com.daishuai.quartz.cache.CacheTemplate;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/6 15:25
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheTemplate cacheTemplate;

    @RequestMapping("/get")
    public Object get(@RequestParam("time") String time) {
        try {
            Date end = DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss");
            Date start = DateUtils.addYears(end, -10);
            return cacheTemplate.zRangeByScore("jobConfig", start.getTime(), end.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
