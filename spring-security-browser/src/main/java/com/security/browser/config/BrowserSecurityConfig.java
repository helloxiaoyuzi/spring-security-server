package com.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //对所有请求都不作认证拦截
//        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
        //基于浏览器表单登录验证
        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //基于httpBasic登录验证
//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 配置一个userDetailsService Bean不在生成默认security.user用户
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }


}
