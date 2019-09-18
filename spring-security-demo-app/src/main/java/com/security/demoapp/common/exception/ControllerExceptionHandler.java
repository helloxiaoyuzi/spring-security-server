package com.security.demoapp.common.exception;

import com.security.common.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    public CommonResult handlerUserNotExistException(UserNotExistException ex){
        return CommonResult.success(ex.getId(),ex.getMessage());
    }
}
