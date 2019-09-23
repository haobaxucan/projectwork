package com.ecpss.skill;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by xc on 2019/9/17.
 */
@Configuration
public class RedissionConfig {
    
    
    @Bean
    public RedissonClient redisson(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return  Redisson.create(config);
    }
}
