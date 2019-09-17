package com.security.core.validate.code.sms;

/**
 * @author liyu
 * @date 2019/9/10 10:49
 * TODO
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
