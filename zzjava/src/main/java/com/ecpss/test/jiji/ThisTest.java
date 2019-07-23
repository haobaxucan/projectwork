package com.ecpss.test.jiji;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xc on 2019/7/19.
 */

@Controller
public class ThisTest {
    
    @ResponseBody
    @RequestMapping("/res")
    public String str(){
        aa();
        return "aa";
    }
    
    public void aa(){
        System.out.println("-----cc");
    }
}
