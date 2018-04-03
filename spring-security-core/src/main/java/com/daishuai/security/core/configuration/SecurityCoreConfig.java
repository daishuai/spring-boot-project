package com.daishuai.security.core.configuration;

import com.daishuai.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/28 20:25
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
