package com.ecpss.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/7/21.
 */

@Aspect
public class Aop {
    @Pointcut("execution(public * com.ecpss.spring.aop.Cacu.add(int,int))")
    public void test1(){
    
    }
    
    @Before("test1()")
    public void before(){
        System.out.println("before");
    }
    @After("test1()")
    public void after(){
        System.out.println("after");
    }
}
