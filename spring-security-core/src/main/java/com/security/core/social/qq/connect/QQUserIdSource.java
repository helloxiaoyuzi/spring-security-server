package com.security.core.social.qq.connect;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialUserDetails;

/**
 * @author liyu
 * @date 2019/9/17 17:35
 * TODO
 */
public class QQUserIdSource implements UserIdSource{
    @Override
    public String getUserId() {
        SecurityContext context= SecurityContextHolder.getContext();
        Authentication authentication=context.getAuthentication();
        if(authentication!=null){
            if(authentication.getPrincipal() instanceof SocialUserDetails){
                return ((SocialUserDetails)authentication.getPrincipal()).getUserId();
            }
        }
        return null;
    }
}
