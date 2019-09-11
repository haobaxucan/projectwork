package com.ecpss.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by xc on 2019/9/9.
 */
public class MqTestConsQ {
    
    public static void main(String[] args) throws Exception {
        ConnectionFactory connect = new ActiveMQConnectionFactory("tcp://192.168.88.133:61616");
        Session session = null;
        try {
            Connection connection = connect.createConnection();
            connection.start();
            //第一个值表示是否使用事务，如果选择true，第二个值相当于选择0
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("xc_queue");//队列模式
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(null!=message && message instanceof TextMessage){
                        try {
                            String text = ((TextMessage) message).getText();
                            System.out.println(text);
                            message.acknowledge();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            
//          session.rollback();
            
        } catch (JMSException e) {
            session.rollback();
        }
    }
}
