package com.ecpss.com.ecpss.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by xc on 2019/7/29.
 */
public class consumer {
    public static void main(String[] args)  throws Exception{
//   推送消息，服务端向客户端推动消息
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("niwei_consumer_group");
        consumer.setNamesrvAddr("localhost:9876");
//        接收所有消息         ---------------------------- 匹配add  和支持或者的关系--就是匹配模式
        consumer.subscribe("test-topic2","aa || add || update");// * 在test-topic2 下面的所有消息都订阅
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("开始接受消息");
                msgs.forEach((x)->{
                    try {
                        System.out.println(new String(x.getBody(),"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("msgs"+msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//这里是给服务端的一个响应
            }
        });
        consumer.start();
        
    }
}
