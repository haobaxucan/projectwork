package com.ecpss.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by xc on 2019/7/23.
 */
@Configuration
public class RedissonConfig {
//    @Bean
    public Redisson redisson(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return (Redisson) Redisson.create(config);
    }
    
}
