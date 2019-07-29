package com.ecpss.spring.dependcy;

import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/7/25.
 */
@Component
public class Car implements Car1{
    @Override
    public void dd() {
        System.out.println("cc");
    }
    
    public void aa(){
        System.out.println("car is d");
    }
}
