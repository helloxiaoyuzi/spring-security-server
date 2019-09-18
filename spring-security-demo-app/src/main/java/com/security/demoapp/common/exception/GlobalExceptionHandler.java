package com.security.demoapp.common.exception;

import com.security.common.api.CommonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * 拦截所有异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e, HttpServletRequest request){
        log.error(e);
        request.setAttribute("exception",e);
        //处理运行时异常
        if(e instanceof RuntimeException){
            return CommonResult.failed(e.getMessage(),"服务器内部错误");
        //处理@Valid 参数校验异常
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException=(MethodArgumentNotValidException)e;
            List<ObjectError> errors=methodArgumentNotValidException.getBindingResult().getAllErrors();
            List<String> messages=errors.stream().map(a->a.getDefaultMessage()).collect(Collectors.toList());
            return  CommonResult.failed(messages,"请求参数不合法");
        }
        return CommonResult.failed(e.getMessage(),"请求失败请重试");
    }
}
