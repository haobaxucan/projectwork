package com.ecpss.mq.topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration

public class ActiveMQConfig_topic {
    @Value("${topicname}")
    private String topicName;
    
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }
    
}