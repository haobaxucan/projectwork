package com.ecpss.controller;

import com.ecpss.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
    public String toTest(Session session)throws Exception{
        WebSocketServer ws=new WebSocketServer();
//        ws.onMessage("xuucuau",session);
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
