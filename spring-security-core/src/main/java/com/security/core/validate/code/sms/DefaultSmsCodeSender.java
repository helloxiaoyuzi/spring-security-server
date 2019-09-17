package com.security.core.validate.code.sms;

/**
 * @author liyu
 * @date 2019/9/10 10:50
 * TODO
 */
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信"+code);
    }
}
