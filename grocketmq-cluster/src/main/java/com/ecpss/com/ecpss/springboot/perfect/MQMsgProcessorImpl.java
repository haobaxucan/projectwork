package com.ecpss.com.ecpss.springboot.perfect;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xc on 2019/7/29.
 */
public abstract class MQMsgProcessorImpl implements MQMsgProcessor{
    @Override
    public MQConsumeResult handle(String topic, String tag, List<MessageExt> msgs) {
        MQConsumeResult mqConsumeResult = new MQConsumeResult();
        /**可以增加一些其他逻辑*/
    
        for (MessageExt messageExt : msgs) {
            //消费具体的消息，抛出钩子供真正消费该消息的服务调用
            mqConsumeResult = this.consumeMessage(tag,messageExt.getKeys()==null?null: Arrays.asList(messageExt.getKeys().split(MessageConst.KEY_SEPARATOR)),messageExt);
        }
    
        /**可以增加一些其他逻辑*/
        return mqConsumeResult;
    
    }
    
     abstract MQConsumeResult consumeMessage(String tag,List<String> keys, MessageExt messageExt);
}
