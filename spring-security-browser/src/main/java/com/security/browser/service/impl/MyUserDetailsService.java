package com.security.browser.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 处理用户信息获取逻辑
 */
@Log4j2
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户校验逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆用户名：{}",username);
        String databasePassword=passwordEncoder.encode("123456");
        log.info("数据库真实密码:{}",databasePassword);
        //根据用户名查找用户信息
        return new User(username,databasePassword, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
