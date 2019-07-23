package com.ecpss.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 收单---渠道--分账-网关
 * Created by xc on 2019/7/15.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class Redist {
    
    @Autowired
    RedisTemplate redisTemplate;
    
    @Test
    public void t(){
        redisTemplate.opsForValue().set("numdemo","123");
        redisTemplate.opsForValue().get("num") ; //输出结果为123
        System.out.println(redisTemplate.opsForValue().get("num1"));
    
    
    }
}
