package com.ecpss.spring.dependcy;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by xc on 2019/7/25.
 */
@Component
public class Person {
    @Autowired
    Car car;
//    @Autowired
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
    public  void method(){
        car.aa();
    }
    
    
}
