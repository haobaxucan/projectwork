package com.ecpss.spring.dependcy;

import com.ecpss.spring.dependcy.aware.TestAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xc on 2019/7/25.
 */
public class MainC {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext c=new AnnotationConfigApplicationContext(Config.class);
        TestAware testAware =(TestAware) c.getBean("testAware");
        testAware.tt();
//        Person bean = c.getBean(Person.class);
//        bean.method();
//        c.close();
    }
}
