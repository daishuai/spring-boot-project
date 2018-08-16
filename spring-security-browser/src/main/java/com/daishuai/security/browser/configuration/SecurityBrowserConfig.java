package com.daishuai.security.browser.configuration;

import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/27 16:19
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
public class SecurityBrowserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(securityProperties.getBrowser().getLoginPage())
            //.loginPage("/index.html")
            .loginProcessingUrl("/authenticate/required")
            .and()
            .authorizeRequests()
            .antMatchers(securityProperties.getBrowser().getLoginPage(),"/authenticate/required")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }
}
