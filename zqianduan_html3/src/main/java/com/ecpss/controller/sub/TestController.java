package com.ecpss.controller.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xc on 2019/7/10.
 */
@Slf4j
@Controller
public class TestController {
    
    @RequestMapping("/ajax")
    @ResponseBody
    public String testAjax(@RequestParam("name")String name){
        log.info("name={}",name);
        
        return "success";
    }

}
