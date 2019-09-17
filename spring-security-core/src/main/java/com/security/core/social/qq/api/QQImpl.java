package com.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author liyu
 * @date 2019/9/17 15:17
 * TODO
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{
    //获取用户qq的openId的URL
    private static final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";
    //获取用户qq的信息的URL
    private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper=new ObjectMapper();


    public QQImpl(String accessToken,String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId=appId;

        String getOpenIdUrl=String.format(URL_GET_OPENID,accessToken);
        String result=getRestTemplate().getForObject(getOpenIdUrl,String.class);
        this.openId= StringUtils.substringBetween(result,"\"openid\":","}");

    }

    @Override
    public QQUserInfo getUserInfo(){

        String getUserInfoUrl=String.format(URL_GET_USERINFO,appId,openId);
        String result=getRestTemplate().getForObject(getUserInfoUrl,String.class);
        try {
            return objectMapper.readValue(result,QQUserInfo.class);
        } catch (Exception e) {
           throw new RuntimeException("获取QQ用户信息失败");
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
