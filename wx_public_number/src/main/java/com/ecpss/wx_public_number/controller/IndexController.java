package com.ecpss.wx_public_number.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.00
 * @date 2019/10/10
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "test";
    }
}
