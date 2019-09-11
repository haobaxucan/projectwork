package com.ecpss;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by xc on 2019/9/4.
 */
public class ActiveMqConsumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connect = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection connection = connect.createConnection();
            connection.start();
            //第一个值表示是否使用事务，如果选择true，第二个值相当于选择0
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue testqueue = session.createQueue("cc");
        
            MessageConsumer consumer = session.createConsumer(testqueue);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(null != message&&message instanceof TextMessage){
                        try {
                            String text = ((TextMessage) message).getText();
                            System.out.println(text);
                        
                          
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }
  
}
