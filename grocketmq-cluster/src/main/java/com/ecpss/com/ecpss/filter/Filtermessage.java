package com.ecpss.com.ecpss.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by xc on 2019/7/27.
 */
public class Filtermessage {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");//设置分组
    
        //指定 NameServer 地址
        producer.setNamesrvAddr("localhost:9876");
    
        //初始化 Producer，整个应用生命周期内只需要初始化一次
        producer.start();

        
        //      发送消息  tag2 类似邮件中标签-过滤，keys 和业务有关--服务器会根据keys创建hash索引
        Message message=new Message("filter","aa","filter发送消息".getBytes("utf-8"));
       message.putUserProperty("name","name");
       message.putUserProperty("age","12");
        
    
        producer.shutdown();
        
    
        
        
    }
}
