package com.security.core.properties;

/**
 * @author liyu
 * @date 2019/9/7 15:15
 * TODO
 */
public class ImageCodeProperties {
    /**
     * 图形验证码宽度
     */
    private int width=67;
    /**
     * 图形验证码高度
     */
    private int height =23;
    /**
     * 图形验证码长度
     */
    private int length=4;
    /**
     * 图形验证码过期时间
     */
    private int expireIn=60;
    /**
     * 需要图形验证码校验的URL，多个url以逗号隔开
     */
    private String url;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
