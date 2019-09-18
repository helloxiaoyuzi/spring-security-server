package com.security.browser.config;


import com.security.core.authentication.AbstractChannelSecurityConfig;
import com.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.security.core.properties.SecurityConstants;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.ValidateCodeSecurityConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Log4j2
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;


    @Bean
    protected PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
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

        //加载父类的配置
        applyPasswordAuthenticationConfig(http);

        log.info("跳转登陆页面URL：{}", securityProperties.getBrowser().getLoginPage());
        http.csrf().disable()
                    .apply(validateCodeSecurityConfig)
                .and()
                    .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                    .apply(springSocialConfigurer)//第三方社交登陆拦截配置
                .and()
                .rememberMe()//记住我
                    .tokenRepository(tokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRemeberMeSeconds())//配置记住我过期时长
                    .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()//session配置项
                    .invalidSessionStrategy(invalidSessionStrategy)//session过期处理策略
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())//同一个用户在系统中的最大session数，默认1
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())//达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)//session并发处理策略
                .and()
                .and()
                    .authorizeRequests()
                .antMatchers(//配置不需要验证的URL
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html"
                    )
                .permitAll()
                .anyRequest()
                .authenticated();
    }


}
