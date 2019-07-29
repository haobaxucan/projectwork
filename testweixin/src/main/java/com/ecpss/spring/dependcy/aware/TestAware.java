package com.ecpss.spring.dependcy.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xc on 2019/7/25.
 */
@Component
public class TestAware implements BeanFactoryAware{
    BeanFactory beanFactory;
    @PostConstruct
    public void init(){
        System.out.println("初始化方法");
    }
    
    public TestAware() {
        System.out.println("实例化方法1");
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("实例化方法2");
        this.beanFactory=beanFactory;
    
    }
    public void tt(){
        P person = (P) beanFactory.getBean("p");
        person.aa();
    }
}
