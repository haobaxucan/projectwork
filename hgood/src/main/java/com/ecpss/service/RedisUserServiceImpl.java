package com.ecpss.service;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.domain.User;
import com.ecpss.mappper.RedisMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by xc on 2019/9/4.
 */
@Service
@Slf4j
public class RedisUserServiceImpl implements RedisUserService {
    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public User getByid(Long id) {
        
        String key = "user:" + id + ":info";
        User user = null;
        if (redisTemplate.hasKey(key)) {
            log.info("查询缓存");
            String userJson = redisTemplate.opsForValue().get(key);
            user = JSONObject.parseObject(userJson, User.class);
            
        } else {
            log.info("查询数据库");

                user = redisMapper.getByid(id);
                if (user != null) {
                    redisTemplate.opsForValue().set(key, JSONObject.toJSONString(user));
                }
                redisTemplate.opsForValue().set(key, "",60, TimeUnit.SECONDS);
            }
    
           
        
        return user;
    }
    
    @Override
    public User getHot(String name) {
        String key = "user:" + name + ":info";
        User user = null;
        if (redisTemplate.hasKey(key)) {
            log.info("查询缓存");
            String userJson = redisTemplate.opsForValue().get(key);
            user = JSONObject.parseObject(userJson, User.class);
        
        }else {
            user = redisMapper.getHot(name);
            if (user != null) { //设置不同不同的过期时间解决缓存雪崩
                Random random=new Random();
                int time=6000+ random.nextInt(6000);
                if(user.getName().equals("cold")){
                    redisTemplate.opsForValue().set(key,JSONObject.toJSONString(user),time,TimeUnit.SECONDS);
                }
                if(user.getName().equals("cold")){
                    int coldTime=6000+ random.nextInt(6000);
                    redisTemplate.opsForValue().set(key,JSONObject.toJSONString(user),coldTime,TimeUnit.SECONDS);
                }
            }
        }
        
    
    
        return user;
    }
    
    @Override
    public User getData(Long id) {
        String key = "user:" + id + ":info";
        User user = null;
        if (redisTemplate.hasKey(key)) {
            log.info("查询缓存");
            String userJson = redisTemplate.opsForValue().get(key);
            user = JSONObject.parseObject(userJson, User.class);
        }else {
            log.info("查询数据库，缓存击穿");//解决缓存击穿1
            String lock = "user:" + id + ":lock";
            Boolean metnx = redisTemplate.opsForValue().setIfAbsent(lock, "1", 10, TimeUnit.SECONDS);
            if (metnx) {
                user = redisMapper.getByid(id);
                if (user != null) {
                    redisTemplate.opsForValue().set(key, JSONObject.toJSONString(user));
                }else {
                    redisTemplate.opsForValue().set(key, "", 60, TimeUnit.SECONDS);
                }
                log.info("删除锁{}",lock);
                redisTemplate.delete(lock);//删除锁
            }else {
                //其他线程休息500毫秒后重试
                try {
                    Thread.sleep(500);
                    getData(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             
            }
        }
        return user;
    }
}
