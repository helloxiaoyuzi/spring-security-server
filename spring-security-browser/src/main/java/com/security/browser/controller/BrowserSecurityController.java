package com.security.browser.controller;

import com.security.common.api.CommonResult;
import com.security.core.properties.SecurityProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liyu
 * @date 2019/9/6 15:51
 * TODO
 */
@Log4j2
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityCoreProperties;

    /**
     * 当需要身份认证时跳到这里
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public CommonResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到缓存的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:{}", targetUrl);
            //是否是HTML引发的跳转，是跳转到登陆页面，不是返回401状态码和错误信息
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                //跳转到自定义的登陆页面
                String loginPage=securityCoreProperties.getBrowser().getLoginPage();
                log.info("跳转登陆页面：{}",loginPage);
                redirectStrategy.sendRedirect(request, response, loginPage);
            }
        }
        return CommonResult.unauthorized("访问的页面需要身份认证，请引导用户到登陆页面");
    }
}
