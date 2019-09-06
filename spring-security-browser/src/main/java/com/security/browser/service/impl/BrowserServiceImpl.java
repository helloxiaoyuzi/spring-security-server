package com.security.browser.service.impl;

import com.security.browser.service.BrowserService;
import org.springframework.stereotype.Service;

@Service
public class BrowserServiceImpl implements BrowserService {
    @Override
    public void test() {
        System.out.println("这是一个测试");
    }
}
