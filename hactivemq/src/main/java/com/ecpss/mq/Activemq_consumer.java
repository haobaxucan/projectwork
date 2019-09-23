package com.ecpss.mq;

import com.ecpss.service.OrderUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class Activemq_consumer {
    @Autowired
    private OrderUpdate orderUpdate;
    
    @JmsListener(destination = "${queuename}")
    public void receive(TextMessage textMessage) throws Exception {
        
        System.out.println(textMessage.getText() + "修改订状态");
    }
    
}
