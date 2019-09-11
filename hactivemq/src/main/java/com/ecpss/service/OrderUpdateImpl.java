package com.ecpss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;

@Service
public class OrderUpdateImpl implements OrderUpdate {
    @Autowired
    @Qualifier(value = "queue2")
    Queue queue;
    
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
 
    
    @Transactional
    @Override
    public void update() {
        // 支付成功后，引起的系统服务-》订单服务的更新-》库存服务-》物流服务
        // 调用mq发送支付成功的消息

        jmsMessagingTemplate.convertAndSend(queue,"----");

    
    }
}
