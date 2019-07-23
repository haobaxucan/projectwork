package com.ecpss;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZzjavaApplicationTests {
	
	@Autowired
	StringRedisTemplate redisTemplate;
	public  int i =0;
	@Test
	public void aa(){
	
		
		
//		Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("1", "aa", 13, TimeUnit.SECONDS);
//		if(aBoolean){
//			log.info("请求次数{}",i++);
//		}else {
//			log.error("这次没有成功--线程等待还是作废");
//		}
	}
	
	@Test
	public void contextLoads() {
//		  int a =0;
//
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
//		for(int i=0;i<50;i++) {
//			executorService.execute(()->{
//
//				System.out.println("cde0001");
//
//			});
//		}
//
//
	}
	

}
