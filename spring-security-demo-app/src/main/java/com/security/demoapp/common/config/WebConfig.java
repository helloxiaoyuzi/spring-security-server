package com.security.demoapp.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

//    /**
//     * 注册拦截器
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TimeInterceptor());
//    }
//
//    /**
//     * 注册过滤器
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean timeFilter(){
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//        //注册所需的过滤器
//        filterRegistrationBean.setFilter(new TimeFilter());
//        //过滤器拦截的URL
//        List<String> filterUrls=new ArrayList <>();
//        //过滤所有地址
//        filterUrls.add("/*");
//        filterRegistrationBean.setUrlPatterns(filterUrls);
//        return filterRegistrationBean;
//    }


}
