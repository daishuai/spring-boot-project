package com.daishuai.thymeleaf;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/5/13 22:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("locale");
        Locale locale = null;
        if(l != null){
            String[] ls = l.split("_");
            locale = new Locale(ls[0],ls[1]);
        }else{
            locale = Locale.getDefault();
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
