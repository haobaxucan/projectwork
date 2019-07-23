package com.ecpss.service;

import com.alibaba.fastjson.JSON;
import com.ecpss.spring.domain.Coupons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by xc on 2019/6/25.
 */
@Slf4j
@Component
public class ConsumerCoupons {
    @Autowired
    CouponsService couponsService;
    
    public void recive(@Payload String passTemplate,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info("Consumer Receive PassTemplate: {}", passTemplate);
        Coupons coupons;
    
        try {
            coupons = JSON.parseObject(passTemplate, Coupons.class);
        } catch (Exception ex) {
            log.error("Parse PassTemplate Error: {}", ex.getMessage());
            return;
        }
    
//        log.info("DropPassTemplateToHBase: {}", passService.dropPassTemplateToHBase(pt));
        
    }
    
    
}
