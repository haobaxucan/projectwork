package com.ecpss.spring.config;

import com.ecpss.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xc on 2019/7/15.
 */
@Configuration
public class QuartConfig {
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("weatherJob")
                .storeDurably()// 即使没有Trigger关联时，也不需要删除该JobDetail
                .requestRecovery(true)//故障恢复
                .build();
        
    }
    @Bean
    public Trigger getTrigger(){
//        SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
//        simpleSchedule.withMisfireHandlingInstructionNextWithExistingCount();
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0 0/30 * * * ?");
        return TriggerBuilder.newTrigger().withIdentity("trigger").forJob(jobDetail())
                .withSchedule(cronSchedule).build();
    }
    
    
}
