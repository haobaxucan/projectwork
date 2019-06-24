package com.ecpss.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xc on 2019/6/16.
 */
@Controller
public class TestController {
    @RequestMapping("/")
//    @ResponseBody
    public String string(){
        return "index";
    }
    
    
}
