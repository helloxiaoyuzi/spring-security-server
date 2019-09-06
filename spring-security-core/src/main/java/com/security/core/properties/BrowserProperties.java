package com.security.core.properties;

/**
 * @author liyu
 * @date 2019/9/6 16:17
 * Browser自定义配置
 */
public class BrowserProperties {
    /**
     * 默认登陆页面
     */
    private String loginPage="/signIn.html";

    private LoginResponseType loginType = LoginResponseType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }
}
