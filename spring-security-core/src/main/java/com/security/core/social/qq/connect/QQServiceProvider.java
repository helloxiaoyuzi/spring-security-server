package com.security.core.social.qq.connect;

import com.security.core.social.qq.api.QQ;
import com.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author liyu
 * @date 2019/9/17 15:45
 * TODO
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

    private String appId;

    //获取Authorization Code的URL
    private static final String URL_AUTHORIZE="https://graph.qq.com/oauth2.0/authorize";
    //通过Authorization Code获取Access Token的URL
    private static final String URL_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new OAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
