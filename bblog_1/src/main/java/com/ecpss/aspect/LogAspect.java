package com.ecpss.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xc on 2019/8/24.
 */
@Aspect
@Component
    public class LogAspect {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Pointcut("execution(* com.ecpss.controller..*.*(..))")
    public void log(){}
    
    @Before("log()")
    public void dobefore(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        logger.info("Request : {}", url);
    }
    @After("log()")
    public void doAfter() {}
    
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }
    
    
}
