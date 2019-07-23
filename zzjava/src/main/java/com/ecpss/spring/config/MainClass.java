package com.ecpss.spring.config;

import com.ecpss.spring.aop.Aopconfig;
import com.ecpss.spring.aop.Cacu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xc on 2019/7/21.
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Aopconfig.class);
        Cacu cacu = (Cacu)ctx.getBean("cacu");
        int add = cacu.add(1, 3);
    
        System.out.println(add);
        ctx.close();
    
    }
}
