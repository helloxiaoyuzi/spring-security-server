package com.security.core.social.qq.config;

import com.security.core.properties.SecurityProperties;
import com.security.core.social.qq.connect.QQConnectionFactory;
import com.security.core.social.qq.connect.QQUserIdSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

/**
 * @author liyu
 * @date 2019/9/17 16:50
 * TODO
 */
@Configuration
@ConditionalOnProperty(prefix = "project.security.social.qq",name = "app-id")//属性配置了app-id改配置才生效
public class QQAutoConfig extends SocialConfigurerAdapter{
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        super.addConnectionFactories(connectionFactoryConfigurer, environment);
        connectionFactoryConfigurer.addConnectionFactory(new QQConnectionFactory(securityProperties.getSocial().getQq().getProviderId(),securityProperties.getSocial().getQq().getAppId(),securityProperties.getSocial().getQq().getAppSecret()));

    }

    @Override
    public UserIdSource getUserIdSource() {
        return new QQUserIdSource();
    }
}
