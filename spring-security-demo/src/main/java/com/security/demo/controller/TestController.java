package com.security.demo.controller;


import com.security.demo.service.TestService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@Log4j2
@RestController("/test")
@Api(value = "RESTful 接口优化",description = "async、deferredResult、callable")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * async异步调用
     * @return
     */
    @GetMapping("/async")
    public String asyncTest(){
        log.info("主线程start：{}",Thread.currentThread().getName());
        //调用异步服务
        testService.asyncServer();
        log.info("主线程end：{}",Thread.currentThread().getName());
        return "success";
    }

    @GetMapping("/callable")
    public Callable<String> callableTest() {
        log.info("主线程start：{}",Thread.currentThread().getName());
        Callable <String> callable = new Callable <String>() {
            @Override
            public String call() throws Exception {
                log.info("异步线程执行异步任务start：{}",Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("异步线程执行异步任务end：{}",Thread.currentThread().getName());
                return "success";
            }
        };
        log.info("主线程end：{}",Thread.currentThread().getName());
        return callable;
    }

    @GetMapping("/order")
    public DeferredResult<String> order() throws Exception {
        log.info("主线程start：{}",Thread.currentThread().getName());

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        log.info("主线程end：{}",Thread.currentThread().getName());
        return result;

    }

}
