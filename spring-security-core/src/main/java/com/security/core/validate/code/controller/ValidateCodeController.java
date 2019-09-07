package com.security.core.validate.code.controller;

import com.security.core.validate.code.ValidateCodeGenerator;
import com.security.core.validate.code.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author liyu
 * @date 2019/9/7 11:15
 * 生成图形验证码
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //图形验证码生成器生成验证码
        ImageCode imageCode = validateCodeGenerator.generate(new ServletWebRequest(request));
        //将图形验证码存入session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        //将图形验证码返回
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
