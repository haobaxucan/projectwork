package com.ecpss.websocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
    @Autowired
    WebSocketServer webSocketServer;
    @RequestMapping("/wb")
    public String wb(){
        try {
            webSocketServer.onMessage("推送9条消息" );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "wb";
    }

}
