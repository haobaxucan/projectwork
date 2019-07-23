package com.ecpss.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by xc on 2019/7/22.
 */
@Slf4j
@RestController
public class RedisController {
    @Autowired
    public StringRedisTemplate template;
    @Autowired
    private Redisson redisson;
    public int a=0;
    public int b=0;
    
    @RequestMapping("/s")
    public String string() {
        String lockKey = "lockKey";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            //获取锁，它将一直持有，知道锁释放，或者因为超过了超时时间，防止系统因为bug --其他线程一直获得不到锁
            redissonLock.lock(10L,TimeUnit.SECONDS);
            int stock = Integer.parseInt(template.opsForValue().get("stock"));
            if (stock <= 0) {
                log.info("获得锁成功b============1{}",a);
                return "index";
            }
            stock--;
            template.opsForValue().set("stock", String.valueOf(stock));
            
            System.out.println("减库存成功,剩余" + stock);
        } finally {
            //            释放锁
            redissonLock.unlock();
            
            return "index";
            
        }
    }
    
}
