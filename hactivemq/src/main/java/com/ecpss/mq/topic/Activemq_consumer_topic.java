package com.ecpss.mq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * Created by xc on 2019/9/9.
 */
@Component
public class Activemq_consumer_topic {

 
    @JmsListener(destination ="${topicname}")
    public void receive(TextMessage textMessage)throws Exception{
    
        System.out.println(textMessage.getText()+"*************");
    }
    
}
