package com.ecpss.utils;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * Created by xc on 2019/9/16.
 */
@Component
public class MqListener {
    
    @JmsListener(destination = "q")
    public void consumePaymentCheckResult(MapMessage mapMessage) throws JMSException {
    
        String out_trade_no = mapMessage.getString("out_trade_no");
        System.out.println(out_trade_no);
    
    }
}
