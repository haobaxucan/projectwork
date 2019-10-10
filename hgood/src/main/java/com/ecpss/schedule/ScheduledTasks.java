package com.ecpss.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    WeChatMessageSendSchedule weChatMessageSendSchedule;
    @Autowired
    WeChatSendMessageSchedule weChatSendMessageSchedule;


    @Scheduled(cron = "0 0 10 * * ?")
    public void say(){
        weChatSendMessageSchedule.execute();
    }


}
