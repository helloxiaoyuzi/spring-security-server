package com.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liyu
 * @date 2019/9/6 16:15
 *  自定义配置
 */
@ConfigurationProperties(prefix = "project.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
