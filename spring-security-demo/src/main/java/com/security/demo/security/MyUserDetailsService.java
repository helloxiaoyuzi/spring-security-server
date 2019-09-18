package com.security.demo.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 处理用户信息获取逻辑
 */
@Log4j2
@Component
public class MyUserDetailsService implements UserDetailsService,SocialUserDetailsService {

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
        log.info("表单登陆用户名：{}",username);
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("社交登陆用户的userId：{}",userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String user){
        //根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否冻结过期等等
        String databasePassword=passwordEncoder.encode("123456");
        log.info("数据库真实密码:{}",databasePassword);
        //根据用户名查找用户信息
        return new SocialUser(user,databasePassword, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    private boolean isAccountNonExpired(){
        return true;
    }

    private boolean isAccountNonLocked(){
        return true;
    }
    boolean isCredentialsNonExpired(){
        return true;
    }

    boolean isEnabled(){
        return true;
    }

}
