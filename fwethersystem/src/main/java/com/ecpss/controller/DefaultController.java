package com.ecpss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xc on 2019/7/11.
 */
@Controller
public class DefaultController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/uploadFile1")
    public String uploadFile() {
        return "ajaxupload/upload";
    }
}
