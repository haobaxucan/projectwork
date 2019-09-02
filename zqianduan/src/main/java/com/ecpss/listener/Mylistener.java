package com.ecpss.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xc on 2019/8/12.
 */
public class Mylistener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init");
    
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("destory");
    }
}
