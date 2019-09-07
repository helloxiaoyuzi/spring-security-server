package com.security.demo.common.config;

import com.security.core.validate.code.ValidateCodeGenerator;
import com.security.core.validate.code.image.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

///**
// * @author liyu
// * @date 2019/9/7 16:54
// * 自定义图形验证码生成器
// */
//@Component(value = "imageCodeGenerator")
//public class MyCodeImageGenerator implements ValidateCodeGenerator {
//    @Override
//    public ImageCode generate(ServletWebRequest request) {
//        //TODO
//        System.out.println("自定义图形验证码生成器");
//        return null;
//    }
//}
