package com.ecpss.resttest;

import com.ecpss.test.User;
import com.ecpss.util.HttpUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by xc on 2019/7/15.
 */
public class Restt {
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public  void  test1(){
    
    }
    @Test
    public  void  test2(){
        String url="http://localhost:8081/rest";
    
        Map<String, Object> map = HttpUtils.doGet(url);
        
        System.out.println(map.get("age")+"map"+map);
    }
    
}
