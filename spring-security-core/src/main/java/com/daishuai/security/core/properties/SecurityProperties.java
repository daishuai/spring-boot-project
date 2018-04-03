package com.daishuai.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/3/28 20:15
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@ConfigurationProperties(prefix = "daishuai.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
