package com.ecpss.controller;

import com.ecpss.controller.demovo.vo.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xc on 2019/7/10.
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/buy")
    public String buy(String name){
        String url="http://PROVIDER1/ticket";
        String str = restTemplate.getForObject(url, String.class);
        
        
//        restTemplate.post
        return name+"购买了"+str;
        
    }
    @GetMapping("/weather")
    public Object weather(String name){
        String url="http://PROVIDER1/weather/cityName";
        log.info("调用远程服务");
        return restTemplate.getForEntity(url, Weather.class);
        
    }
    
    @GetMapping("/redis")
    public Object stock_(){
        String url="http://SERVER1/s";
        String s = restTemplate.getForObject(url, String.class);
        System.out.println(s);
        return "a";
    }
    
    
}
