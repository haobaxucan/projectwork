package com.ecpss.schedule;

import com.ecpss.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WeChatSendMessageSchedule extends BaseJobScheduler {

//    @Autowired
//    MscSendTaskService mscSendTaskService;

//    @Override
//    protected List<MscSendTaskEntity> fetchList(int batchRecordCount) {
//        String currnetTime = DateTimeUtils.getCurrnetTime();
//        System.out.println("currnetTime = " + currnetTime);
//        List<MscSendTaskEntity> waitToSendMessageList = mscSendTaskService.findAllPlnSendTask(currnetTime);
//        return waitToSendMessageList;
//    }

//    @Override
//    protected void process(MscSendTaskEntity item) {
//        String content = item.getContent();
//        System.out.println("content = " + content);
//        mscSendTaskService.sendMssage(item);
//    }


    @Override
    protected List fetchList(int batchRecordCount) {
        String currnetTime = DateTimeUtils.getCurrnetTime();
        System.out.println("currnetTime = " + currnetTime);
        List<String> list= Arrays.asList("1","3","4");//将所有的书分封装数组，遍历数组，对于每一个
        return list;
    }

    @Override
    protected void process(Object item) {
        System.out.println("content = " +"1111");
    }
}
