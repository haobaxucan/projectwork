package com.ecpss.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1 不需要被拦截的不加注解
 * 2 添加此注解，需要被拦截  loginSuccess=false  购物过期
 * 3 添加此注解，需要被拦截  loginSuccess=true  登录成功也能被访问
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface LoginRequired {
    
    boolean loginSuccess() default true;
}
