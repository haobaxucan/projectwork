package com.ecpss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xc on 2019/7/10.
 */
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/buy")
    public String buy(String name){
        String str = restTemplate.getForObject("http://PROVIDER1/ticket", String.class);
//        restTemplate.post
        return name+"购买了"+str;
        
    }
}
