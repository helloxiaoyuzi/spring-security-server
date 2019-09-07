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

    private ValidateCodeProperties code=new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
