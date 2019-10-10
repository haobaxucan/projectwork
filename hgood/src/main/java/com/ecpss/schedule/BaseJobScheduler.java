package com.ecpss.schedule;

import com.ecpss.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * BaseJobScheduler
 * Author niuzhanjun
 * Date  2019年8月7日
 *
 * @param <T> 需要处理的数据类型
 */
@Slf4j
public abstract class BaseJobScheduler<T> {
    protected static final int BATCH_RECORD_COUNT = 100;  //单次获取数据的limit count
    protected int MAX_PROCESS_RECORD_COUNT = 1000; // default value - job can process max amount

    protected String jobName = this.getClass().getSimpleName();
    protected int processed_count = 0;



    public void execute() {
        try {
            log.info("{} start work.{}", jobName, DateTimeUtils.formatDateTimeString());

            boolean finalRoundFlag = false;
            processed_count = 0;

            while (true) {
                int batchRecordCount = getBatchRecordCount();
                if (processed_count >= MAX_PROCESS_RECORD_COUNT) break;

                //Get data list
                List<T> dataList = fetchList(batchRecordCount);

                if (null != dataList && dataList.size() > 0) {
                    if (dataList.size() < batchRecordCount) {
                        finalRoundFlag = true;
                    }

                    processList(dataList);

                } else {
                    // Logger.debug(this, "no data found to process");
                    finalRoundFlag = true;
                }

                if (finalRoundFlag) break;
            }

            log.info("{} finished work.process {} records。{}", jobName, processed_count, DateTimeUtils.formatDateTimeString());
        } catch (Exception e) {
            log.error("{} has exception : {}", jobName, e.getMessage());
        }
    }

    protected abstract List<T> fetchList(int batchRecordCount);

    protected void processList(List<T> list) {
        if (list.size() > 0) {
            for (T item : list) {
                try {
                    process(item);
                    processed_count = processed_count + 1;
                } catch (Exception e) {
                    log.error("{} process data has exception : {}", jobName, e.getMessage());
                }
            }
        }
    }

    protected abstract void process(T item);

    protected int getBatchRecordCount() {
        return BATCH_RECORD_COUNT;
    }

}
