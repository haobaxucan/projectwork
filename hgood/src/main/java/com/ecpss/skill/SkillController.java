package com.ecpss.skill;

import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xc on 2019/9/17.
 */
@Controller
public class SkillController {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @RequestMapping("secKill")
    @ResponseBody
    public String secKill(){
        
        
        RSemaphore semaphore = redissonClient.getSemaphore("106");
        boolean b = semaphore.tryAcquire();
        
        int stock = Integer.parseInt(redisTemplate.opsForValue().get("106"));
        if(b){
            System.out.println("当前库存剩余数量"+stock+",某用户抢购成功，当前抢购人数："+(1000-stock));
            // 用消息队列发出订单消息
            System.out.println("发出订单的消息队列，由订单系统对当前抢购生成订单");
        }else {
            System.out.println("当前库存剩余数量"+stock+",某用户抢购失败");
        }
        
        return "1";
    }

}
