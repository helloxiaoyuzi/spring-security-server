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
    /**
     * 登录后返回类型，默认JSON
     */
    private LoginResponseType loginType = LoginResponseType.JSON;
    /**
     * 浏览器，记住我默认时间一周
     */
    private int remeberMeSeconds= 3600*24*7;

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

    public int getRemeberMeSeconds() {
        return remeberMeSeconds;
    }

    public void setRemeberMeSeconds(int remeberMeSeconds) {
        this.remeberMeSeconds = remeberMeSeconds;
    }
}
