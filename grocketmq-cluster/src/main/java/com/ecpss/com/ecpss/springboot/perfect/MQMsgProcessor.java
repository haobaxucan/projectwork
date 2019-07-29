package com.ecpss.com.ecpss.springboot.perfect;

import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by xc on 2019/7/29.
 */
public interface MQMsgProcessor {
    MQConsumeResult handle(String topic, String tag, List<MessageExt> msgs);
    
}
