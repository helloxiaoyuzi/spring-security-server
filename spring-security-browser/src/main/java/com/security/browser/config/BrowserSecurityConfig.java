package com.security.browser.config;

import com.security.browser.authentication.AuthenctiationFailureHandler;
import com.security.browser.authentication.AuthenticationSuccessHandler;
import com.security.core.properties.SecurityProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Log4j2
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenctiationFailureHandler authenctiationFailureHandler;
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //对所有请求都不作认证拦截
//        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
        //基于浏览器表单登录验证
//        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //基于httpBasic登录验证
//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
        log.info("跳转登陆页面1：{}",securityProperties.getBrowser().getLoginPage());
        http.csrf().disable()
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)//请求成功处理器
                .failureHandler(authenctiationFailureHandler)//请求失败处理器
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest()
                .authenticated();
    }



}
