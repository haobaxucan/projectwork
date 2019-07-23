package com.ecpss.spring.domain.instance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xc on 2019/7/21.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        /**
         * 创建ioc 容器
         */
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(Mainconfig.class);
        boolean user = applicationContext.containsBean("user");
        boolean a = applicationContext.containsBean("getUser");
    
        System.out.println("user="+user);
 
        System.out.println("a="+a);
    
//        instance1 bean = applicationContext.getBean(instance1.class);//直接从缓存中去拿
        applicationContext.close();
        
    }
}
