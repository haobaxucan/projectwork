package com.ecpss.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by xc on 2019/7/9.
 */

@Controller
public class DefaultController {
    @Bean
    public InternalResourceViewResolver getInstance(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @RequestMapping("/")
    public String index(){
        System.out.println("aa");
        return "index";
    }
}
