package com.security.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author liyu
 * @date 2019/9/18 21:51
 * OAUTH2 认证服务器配置
 */
@Order(1)
@Configuration
@EnableAuthorizationServer//开启OAUTH2 认证服务器
public class AuthorizationServerConfig {

}
