package com.security.core.properties;

/**
 * @author liyu
 * @date 2019/9/17 16:48
 * TODO
 */
public class SocialProperties {

    private String filterProcessesUrl="auth";

    private QQProperties qq=new QQProperties();

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
