package com.ecpss.spring.dependcy.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/7/25.
 */
@Component
public class instanitiation implements InstantiationAwareBeanPostProcessor{
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanName.equals("testAware"))
            System.out.println("bean 实例化前");
        return null;
    }
    
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(beanName.equals("testAware"))
            System.out.println("bean 实例化后");
        return false;
    }
}
