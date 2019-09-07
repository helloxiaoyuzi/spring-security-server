package com.security.core.validate.code.Exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author liyu
 * @date 2019/9/7 11:46
 * 验证码验证失败
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
