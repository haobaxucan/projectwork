package com.ecpss.schedule;

import org.springframework.stereotype.Service;

@Service
public class WeChatMessageSendSchedule {
    static Integer batchProcessCount = 1000;//批量处理数据条数
    static Integer batchProcessSeconds = 600;//批量处理数据时间

//    @Autowired
//    MscSendTaskService mscSendTaskService;

//    public void process() {
//        int count = 0;
//        while (count < batchProcessCount) {
//            List<MscSendTaskEntity> list = mscSendTaskService.listToSendTasks();
//            for (MscSendTaskEntity entity : list) {
//                mscSendTaskService.processSendTask(entity);
//                count += 1;
//            }
//            if (list.size() == 0) {
//                break;
//            }
//        }
//    }

}
