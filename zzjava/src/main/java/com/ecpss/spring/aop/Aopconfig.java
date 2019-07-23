package com.ecpss.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by xc on 2019/7/21.
 */
@EnableAspectJAutoProxy
@Configuration
public class Aopconfig {
    @Bean("c")
    public Cacu cacu(){
        return new Cacu();
    }
    @Bean
    public Aop aop(){
        return new Aop();
    }
}
