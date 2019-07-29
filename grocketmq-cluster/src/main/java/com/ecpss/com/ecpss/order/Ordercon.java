package com.ecpss.com.ecpss.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by xc on 2019/7/29.
 */
public class Ordercon {
    /**
     * 提供顺序消息
     * @param args
     */
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("xc-cc");
        consumer.setNamesrvAddr("localhost:9876");
        
        consumer.subscribe("xc-order","*");
        
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                System.out.println("消息message---"+msgs+Thread.currentThread().getName());//同一个线程接收相同的orderid
                
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }
    /**econsumeTimes=0, preparedTransactionOffset=0, toString()=Message [topic=xc-order, flag=0, properties={MIN_OFFSET=0,
     MAX_OFFSET=90, UNIQ_KEY=C0A8013D977818B4AAC2929FFAAC0032, WAIT=true, TAGS=orderTag}, body=9]]]ConsumeMessageThread_3
     消息message---[MessageExt [queueId=3,
   
     , WAIT=true, TAGS=orderTag}, body=9]]]ConsumeMessageThread_2
     */
}
