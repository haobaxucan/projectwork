package com.ecpss.com.ecpss.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * Created by xc on 2019/7/29.
 */
public class Orderpro {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer=new DefaultMQProducer("xc-pp");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i=0;i<100;i++){
            String s="order->"+i;
//            模拟生成订单id
            int orderId=i%10;
            Message message=new Message("xc-order","orderTag",s.getBytes("utf-8"));
            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer args=(Integer) arg;
                    int index=orderId%mqs.size();
                    MessageQueue queue = mqs.get(index);
                    /**
                     MessageQueue [topic=xc-order, brokerName=xhost, queueId=1]
                     MessageQueue [topic=xc-order, brokerName=xhost, queueId=2]
                     MessageQueue [topic=xc-order, brokerName=xhost, queueId=3]
                     MessageQueue [topic=xc-order, brokerName=xhost, queueId=0]
                     MessageQueue [topic=xc-order, brokerName=xhost, queueId=1]
                     */
                    System.out.println(queue);//消息队列
                    return queue;
                }
            },orderId);//订单号相同的肯定在同一个队列中
            
        }
     
    
    }
}
