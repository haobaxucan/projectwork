package com.ecpss.controller;

import com.ecpss.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xc on 2019/7/9.
 */
@Controller
public class DefaultController {
    @RequestMapping("/")
    public String index(){
      return "index";
    }
    @RequestMapping("/toTest")
    public String toTest(){
        return "/test/test";
    }
    @RequestMapping("/aa/start")
    public String aa(){
        return "index";
    }
    @RequestMapping("/aa/end")
    public String end(){
        return "index";
    }
    
    @RequestMapping("/aa/send")
    public String send(){
//        WebSocketServer.sendInfo();

        System.out.println("xcccc");
    return "index";
    }
    
}
