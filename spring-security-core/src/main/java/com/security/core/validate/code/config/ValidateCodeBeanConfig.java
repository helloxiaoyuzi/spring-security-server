package com.security.core.validate.code.config;

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.ValidateCodeGenerator;
import com.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liyu
 * @date 2019/9/7 16:41
 * TODO
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="imageValidateCodeGenerator")//如果IOC容器中没有imageCodeGenerator对象，则使用该方法创建该对象
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageValidateCodeGenerator validateCodeGenerator=new ImageValidateCodeGenerator();
        validateCodeGenerator.setSecurityProperties(securityProperties);
        return validateCodeGenerator;
    }

    /**
     * 注入短信发送接口
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender SmsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
