package com.security.core.social.qq.connect;

import com.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


/**
 * @author liyu
 * @date 2019/9/17 16:10
 * TODO
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{
    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
