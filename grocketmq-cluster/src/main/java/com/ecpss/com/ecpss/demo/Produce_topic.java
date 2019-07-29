package com.ecpss.com.ecpss.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by xc on 2019/7/27.
 */
public class Produce_topic {
//    No route info of this topic, test-topic1
//   需要我们手动去创建
    public static void main(String[] args) throws Exception{
        //创建一个消息生产者，并设置一个消息生产者组
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");//设置分组
    
        //指定 NameServer 地址  192.168.88.128
        producer.setNamesrvAddr("localhost:9876");
    
        //初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();
        
           //创建topic
        /**
         * key broker 的名字
         * topic 的名字
         * 队列的数量默认为4
         */
        producer.createTopic("xc","test-topic2",4);
    
    }
    
    
}
