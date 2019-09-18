package com.security.core.social;

import com.security.core.properties.SecurityProperties;
import com.security.core.social.qq.config.QQSpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author liyu
 * @date 2019/9/17 15:14
 * TODO
 */
@Configuration
@ConditionalOnProperty(prefix = "project.security.social.qq",name = "app-id")//属性配置了app-id改配置才生效
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
        return new QQSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
    }
}
