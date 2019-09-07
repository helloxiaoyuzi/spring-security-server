package com.security.browser.config;

import com.security.browser.authentication.AuthenctiationFailureHandler;
import com.security.browser.authentication.AuthenticationSuccessHandler;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.filter.ValidateCodeFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private DataSource dataSource;
    @Bean
    protected PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //对所有请求都不作认证拦截
//        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
        //基于浏览器表单登录验证
//        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //基于httpBasic登录验证
//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
        ValidateCodeFilter validateCodeFilter =new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenctiationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        log.info("跳转登陆页面URL：{}",securityProperties.getBrowser().getLoginPage());
        http.csrf().disable()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//在登录过滤器前，加验证码校验器
                .formLogin()//表单登录
                    .loginPage("/authentication/require")//登录验证请求页
                    .loginProcessingUrl("/authentication/form")//登录表单提交URL
                    .successHandler(authenticationSuccessHandler)//请求成功处理器
                    .failureHandler(authenctiationFailureHandler)//请求失败处理器
                    .and()
                .rememberMe()//记住我
                    .tokenRepository(tokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRemeberMeSeconds())//配置记住我过期时长
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/code/image")
                .permitAll()
                .anyRequest()
                .authenticated();
    }



}
