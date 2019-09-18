package com.security.demoapp.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.security.demoapp.common.util.IPUtils;
import com.security.demoapp.common.util.SpringContextUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Log4j2
public class AutoLogAspect {
    @Pointcut("@annotation(com.security.demoapp.common.aspect.annotation.AutoLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        MethodSignature signature = (MethodSignature) point.getSignature();
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的参数
        Object[] args = point.getArgs();
        //获取request
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        //获取请求的IP地址
        String ip= IPUtils.getIpAddr(request);
        //获取请求URL
        String url = request.getRequestURL().toString();
        //获取http请求方式
        String type = request.getMethod();

        log.info("【请求方式】"+"IP:"+ip+"    url:"+url+"    httptype:"+type);
        log.info("【请求方法】"+className+"."+methodName+""+getParams(signature,args));
        log.info("【返回结果】"+JSONObject.toJSONString(result));
        log.info("【请求耗时】"+time+"ms");

        return result;
    }

    /**
     * 获取请求方法参数类型、参数名称、参数值
     * @param signature
     * @param args
     * @return
     */
    public String getParams(MethodSignature signature,Object[] args){
        String paramsNames[]=signature.getParameterNames();
        Class paramsTypes[]=signature.getParameterTypes();
        String params="  (";
        for(int i=0;i<args.length;i++){
            String typeName=paramsTypes[i].getName();
            String paramName=paramsNames[i];
            String param= JSONObject.toJSONString(args[i]);
            params+=typeName+" "+paramName+ " "+param+",";
        }
        params=params.substring(0,params.length() - 1);
        return params+")";
    }
}
