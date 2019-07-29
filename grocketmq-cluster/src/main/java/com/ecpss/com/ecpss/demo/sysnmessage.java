package com.ecpss.com.ecpss.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Created by xc on 2019/7/27.
 */
public class sysnmessage {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");//设置分组
    
        //指定 NameServer 地址
        producer.setNamesrvAddr("localhost:9876");
//    设置重试次数
        producer.setRetryTimesWhenSendFailed(3);
        //初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();
//        发送消息
      
    
        //      发送消息  tag2 类似邮件中标签-过滤，keys 和业务有关--服务器会根据keys创建hash索引
        Message message=new Message("test-topic2","aa","我的第一个发送消息".getBytes("utf-8"));
        SendResult send = null;
       
            send = producer.send(message,1000);//发送的消息
       
        System.out.println("消息的id"+message.getBody());
        System.out.println("消息的id"+send.getMsgId());
        System.out.println("消息的id"+send.getMessageQueue());
        System.out.println("消息的id"+send.getOffsetMsgId());
        System.out.println("消息的id"+send.getQueueOffset());
        System.out.println("消息的id"+send.getSendStatus());
        
    
        producer.shutdown();
        
    
        
        
    }
}
