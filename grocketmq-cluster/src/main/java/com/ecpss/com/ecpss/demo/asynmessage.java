package com.ecpss.com.ecpss.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by xc on 2019/7/29.
 */
public class asynmessage {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");//设置分组
        
        //指定 NameServer 地址
        producer.setNamesrvAddr("localhost:9876");
        
        //初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();
//        发送消息
        
        //     异步 发送消息
        Message message=new Message("test-topic2","mytags","第一个异步消息".getBytes("utf-8"));
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功了"+sendResult.getMsgId());
            }
    
            @Override
            public void onException(Throwable e) {
                System.out.println("发送失败");
            }
        });
        
        
        
        producer.shutdown();
        
        
        
        
    }
    
}
