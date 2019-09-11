package com.ecpss.mq.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * Created by xc on 2019/9/9.
 */
@Component
public class Activemq_producer_topic {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    
    @Autowired
    Topic topic;
    
    
    public void produceMessage(){
     
        jmsMessagingTemplate.convertAndSend(topic,"主题****"+ UUID.randomUUID().toString().substring(0,6));
    }
   
   
}
