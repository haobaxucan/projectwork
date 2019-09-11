package com.ecpss.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by xc on 2019/9/9.
 */
public class MqTestProQ {
    
    
    public static void main(String[] args) throws Exception {
        
        ConnectionFactory connect = new ActiveMQConnectionFactory("tcp://192.168.88.133:61616");
        Session session=null;
        try {
            Connection connection = connect.createConnection();
            connection.start();
            //第一个值表示是否使用事务，如果选择true，第二个值相当于选择0
             session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("xc_queue");//队列模式
            
            
            MessageProducer producer = session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            
            TextMessage textMessage = session.createTextMessage("今天天气真好！11113333333333");
            
            producer.send(textMessage);
            
            session.commit();
            connection.close();
            
        } catch (JMSException e) {
            session.rollback();
        }
    }
    
    
}
