package com.ecpss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xc on 2019/8/12.
 */
@Controller
public class TestController {

   
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
