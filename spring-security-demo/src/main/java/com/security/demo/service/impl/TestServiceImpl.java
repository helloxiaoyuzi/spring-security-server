package com.security.demo.service.impl;


import com.security.browser.service.BrowserService;
import com.security.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    BrowserService browserService;

    @Override
    @Async
    public void asyncServer() {
        browserService.test();
        log.info("异步线程执行异步任务start：{}",Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("异步线程执行异步任务end：{}",Thread.currentThread().getName());
    }
}
