package com.ecpss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xc on 2019/8/24.
 */
//@Controller
@Slf4j
public class DefaultController {
    
    @RequestMapping("/index")
    public String index(){
    
//        String n=null;
//        if (n == null) {
//            throw new RuntimeException("找不到");
//        }
        return "index";
    }
    
    public static void aa(){
        throw new RuntimeException("异常出现了");
    }
    
    public static void bb(){
        try {
            aa();
        } catch (Exception e) {
            System.out.println(e.getMessage()+"---"+e.getStackTrace());
            System.out.println(e.getStackTrace()+"---");
        }
    }
    public static void main(String[] args) {
    bb();
    }
}
