package com.ecpss.com.ecpss.trans;

import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;
import java.util.Random;

/**
 * Created by xc on 2019/7/29.
 */
/**
 * @Date: Created in  2018/2/12 15:55
执行本地事务
 */
public class TransactionExecuterimpl implements LocalTransactionExecuter{
    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message message, final Object o) {
        try{
            //DB操作 应该带上事务 service -> dao
            //如果数据操作失败  需要回滚    同事返回RocketMQ一个失败消息  意味着 消费者无法消费到这条失败的消息
            //如果成功 就要返回一个rocketMQ成功的消息，意味着消费者将读取到这条消息
            //o就是attachment
            
            //测试代码
            System.out.println("用户A减了 500");
            Thread.sleep(800);
            
//           int a = 1 / 0;

            System.out.println("用户b增加了 500元");
            Thread.sleep(800);
            

            System.out.println(new Date()+"===> 本地事务执行成功，发送确认消息");
        }catch (Exception e){
            System.out.println(new Date()+"===> 本地事务执行失败！！！");
            return LocalTransactionState.ROLLBACK_MESSAGE;
            
        }
        return LocalTransactionState.UNKNOW; //会查演示
//        return LocalTransactionState.COMMIT_MESSAGE;
    }
}