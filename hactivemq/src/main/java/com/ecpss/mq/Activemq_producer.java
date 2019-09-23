package com.ecpss.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * Created by xc on 2019/9/9.
 */
@Component
public class Activemq_producer {
    
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    
    @Autowired
    Queue queue;
    
    
    public void produceMessage() {
        
        jmsMessagingTemplate.convertAndSend(queue, "****" + UUID.randomUUID().toString().substring(0, 6));
    }
    
    // 带定时投递的业务方法
    @Scheduled(fixedDelay = 3000)    // 每3秒自动调用
    public void produceMessageScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "** scheduled **" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("  produceMessage  send   ok   ");
    }
}
