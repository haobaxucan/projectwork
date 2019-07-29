package com.ecpss.spring.dependcy.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/7/25.
 */
@Component
public class PostBeanprocess implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("testAware")) {
            System.out.println("bean 前置处理--------------");
        }
    
        return null;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("testAware"))
        System.out.println("bean 后置处理--------------");
        
        return null;
    }
}
