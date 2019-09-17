package com.security.core.properties;

/**
 * @author liyu
 * @date 2019/9/17 16:44
 * TODO
 */
public class QQProperties{

    private String appId;

    private String appSecret;

    private String providerId="qq";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
