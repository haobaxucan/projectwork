package com.ecpss.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xc on 2019/9/4.
 */
@Configuration
public class RedissonConfig {
//    @Bean
//    public RedissonClient redissonClient(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://192.168.88.128:6379");
//        return  Redisson.create(config);
//    }
}
