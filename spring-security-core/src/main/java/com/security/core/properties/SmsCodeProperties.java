package com.security.core.properties;

/**
 * @author liyu
 * @date 2019/9/10 11:08
 * TODO
 */
public class SmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 验证码过期时间
     */
    private int expireIn = 60;
    /**
     * 需要验证码校验的URL，多个url以逗号隔开
     */
    private String url;

    public SmsCodeProperties() {
    }

    public SmsCodeProperties(int length, int expireIn) {
        this.length = length;
        this.expireIn = expireIn;
    }

    public SmsCodeProperties(int length, int expireIn, String url) {
        this.length = length;
        this.expireIn = expireIn;
        this.url = url;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int lenght) {
        this.length = lenght;
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
