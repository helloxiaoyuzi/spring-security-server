package com.security.demoapp.common.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

/**
 * 时间过过滤器
 */
@Log4j2
public class TimeFilter implements Filter{
    /*
    用于完成Filter的初始化
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TimeFilter init");
    }
    /*

     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime=System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("TimeFilter complete time {}",System.currentTimeMillis()-startTime);
    }
    /*
    用于Filter销毁前，完成某些资源的回收
     */
    @Override
    public void destroy() {
        log.info("TimeFilter destroy");
    }
}
