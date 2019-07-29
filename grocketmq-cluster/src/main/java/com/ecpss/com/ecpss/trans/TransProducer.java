package com.ecpss.com.ecpss.trans;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Created by xc on 2019/7/29.
 */
public class TransProducer {
    /**
     * demo  转账的事务
     *
     *（1） a开始转账的时候发送一条消息给--mq--但是这个时候消费者等待消息，mq不发送消息给消费者--比如发送短信的系统==通知转账成功
     *
     * （2）a 转账100元 给b--调用转账系统--转账成功
     *
     *（3）b 可能调用一些其他的服务 然后增加100 元
     *
     *（2）（3）结束后转账成功---然后在给mq 发送一条确认消息---mq 推动消息到 短信系统--之后发送短信给用户
     *
     * 异常 失败的情况下 转账失败了--就不会发送确认消息
     *
     * 消息的回查---确认消息是否成功
     *
     */

    public static void main(String[] args) throws Exception{
        TransactionMQProducer producer=new TransactionMQProducer("trans_producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //事务回查监听器
        TransactionCheckListenerImpl checkListener = new TransactionCheckListenerImpl();
//        注册事务监听
        producer.setTransactionCheckListener(checkListener);
    
        //本地事务执行器
        TransactionExecuterimpl executerimpl = new TransactionExecuterimpl();
        Message message=new Message("pay-topic","pay","用户a给用户b转账500元".getBytes("utf-8"));
        
        producer.sendMessageInTransaction(message,executerimpl,null);
        
        Thread.sleep(1000);
        producer.shutdown();
    }
}
