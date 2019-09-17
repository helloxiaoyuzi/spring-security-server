package com.security.core.validate.code.sms;

import com.security.core.properties.SecurityConstants;
import com.security.core.validate.code.ValidateCode;
import com.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liyu
 * @date 2019/9/10 15:46
 * 短信验证码处理器
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        //获取电话参数名
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        //从request获取电话值
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        //发送验证码
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
