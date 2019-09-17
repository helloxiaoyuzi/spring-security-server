package com.security.core.validate.code;

import com.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author liyu
 * @date 2019/9/7 16:35
 * 验证码生成接口
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
