package com.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author liyu
 * @date 2019/9/7 11:06
 * 校验码对象
 */
public class ImageCode {
    /**
     * 校验码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    /**
     * 图形验证码
     */
    private BufferedImage image;

    public ImageCode(BufferedImage image,String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image,String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
        this.image = image;
    }

    /**
     * 判断校验码是否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
