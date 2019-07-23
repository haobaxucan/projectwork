package com.ecpss.controller.resttemplate;

import com.ecpss.test.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xc on 2019/7/15.
 */
@Controller
public class RestTemplateController {
    @Autowired
    
    private RestTemplate restTemplate;
    @RequestMapping("/rest")
    @ResponseBody
    public  Object responseJson(){
        return new User("bb",1,"ss","de");
    }
    
    @RequestMapping(value = "/getIds", method = RequestMethod.GET)
    public  String getIds(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        
        return param1 + param2;
    }
    
    @RequestMapping("/rr")
    @ResponseBody
    public String rr(){
        String result = restTemplate.getForObject("http://localhost:8081/getIds?param1={1}&param2={2}", String.class, "hello", "world");
        System.out.println(result);
        return result;
        
    }

}
