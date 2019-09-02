package com.ecpss.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by xc on 2019/7/30.
 */
public class jedis {
    
    @Test
    public void aa(){
        
        Jedis jedis=new Jedis("192.168.88.128",6379);
        jedis.set("chaoshen","12");
        String chaoshen = jedis.get("chaoshen");
        System.out.println(chaoshen);
    
    
    }
    
    
    
    
}
