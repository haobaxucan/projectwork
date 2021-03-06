package com.ecpss;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;


public class ActiveMqProducer {
    
    public static void main(String[] args) throws Exception {
    
    
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 2 通过连接工厂连接 connection  和 启动
        javax.jms.Connection connection = activeMQConnectionFactory.createConnection();
        connection.start(); //  启动
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Queue queue = session.createQueue("cc");
        // 5 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        for (int i = 1; i < 4 ; i++) {
            // 7  创建字消息
            TextMessage textMessage = session.createTextMessage("msg--" + i);
            // 8  通过messageProducer发布消息
            messageProducer.send(textMessage);
        }
        // 9 关闭资源
        messageProducer.close();
        session.close();
        connection.close();
   
    
    }
   
    
}
