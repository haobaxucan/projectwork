package com.ecpss.com.ecpss.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by xc on 2019/7/29.
 */
public class FilterConsumer {
    public static void main(String[] args)  throws Exception{
//   推送消息，服务端向客户端推动消息
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("niwei_consumer_group");
        consumer.setNamesrvAddr("localhost:9876");
//        接收所有消息         ---------------------------- 匹配add  和支持或者的关系--就是匹配模式
        consumer.subscribe("filter", MessageSelector.bySql("name='name' and age>=12"));//需要在rocketmq 配置文件进行开启
        consumer.registerMessageListener(new MessageListenerConcurrently() {//注册监听器
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("开始接收消息");
                msgs.forEach((x)->{
                    try {
                        System.out.println(new String(x.getBody(),"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
                
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//这里是给服务端的一个响应
            }
        });
        consumer.start();
        
    }
}
