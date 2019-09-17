package com.security.core.properties;

/**
 * @author liyu
 * @date 2019/9/7 15:15
 * 图形验证码
 */
public class ImageCodeProperties extends SmsCodeProperties{
    /**
     * 图形验证码宽度
     */
    private int width=67;
    /**
     * 图形验证码高度
     */
    private int height =23;

    public ImageCodeProperties() {
        setLength(4);//图形验证码默认长度为4
    }

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

}
