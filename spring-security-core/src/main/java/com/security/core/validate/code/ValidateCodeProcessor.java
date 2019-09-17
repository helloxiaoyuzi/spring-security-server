package com.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liyu
 * @date 2019/9/10 15:02
 * 校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor {
    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX="SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证校验码
     * @param request
     */
    void validate(ServletWebRequest request);
}
