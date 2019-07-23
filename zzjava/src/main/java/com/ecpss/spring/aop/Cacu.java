package com.ecpss.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/7/21.
 */
@Component
public class Cacu {
    public int add(int a,int b){
        System.out.println("输出add");
        return a+b;
    }
}
