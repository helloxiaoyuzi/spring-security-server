package com.security.core.validate.code.filter;

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.Exception.ValidateCodeException;
import com.security.core.validate.code.image.ImageCode;
import com.security.core.validate.code.controller.ValidateCodeController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liyu
 * @date 2019/9/7 11:38
 * 验证码过滤器
 * 继承OncePerRequestFilter，确保在一次请求中只通过一次ValidateCodeFilter
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher=new AntPathMatcher();

    private Set<String> urls=new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if(StringUtils.isNotBlank(securityProperties.getCode().getImage().getUrl())){
            String [] configUrls= StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
            Arrays.stream(configUrls).forEach(configUrl->urls.add(configUrl));
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action=false;
        //查找当前的URL，是否与配置的需要验证码验证的URL匹配
        for(String url:urls){
           if(pathMatcher.match(url,request.getRequestURI())){
               action=true;
           }
        }
        //匹配上有需要验证的URL，则进行验证码校验
        if (action) {
           try{
               validate(new ServletWebRequest(request));
           }catch (ValidateCodeException e){
               //验证码校验失败，交由自定义失败处理器处理返回结果
               authenticationFailureHandler.onAuthenticationFailure(request,response,e);
               //验证码验证不通过直接返回
               return;
           }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //获取存放在session中的图形验证码
        ImageCode codeInSession=(ImageCode)sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
        //获取用户请求填写的验证码
        String codeInRequest= ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if(StringUtils.isEmpty(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if(codeInSession==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if(!codeInRequest.equals(codeInSession.getCode())){
            throw new ValidateCodeException("验证码不匹配");
        }
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
