package com.ecpss.job;

import com.ecpss.service.weatherservice.WeatherDataCollectionService;
import com.ecpss.service.weatherservice.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xc on 2019/7/15.
 * job 实现数据的同步
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {
    @Autowired
    private WeatherDataCollectionService collectionService;
    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("获取data");
        // 获取城市ID列表
        // TODO 改为由城市数据API微服务来提供数据
        List<City> cityList = null;
    
        try {
        
            // TODO 改为由城市数据API微服务提供数据
            cityList = new ArrayList<>();
            City city = new City();
            city.setCityName("广州");
            cityList.add(city);
        
        } catch (Exception e) {
        
        }
    
        // 遍历城市ID获取天气
        for (City city : cityList) {
            String cityName = city.getCityName();
            collectionService.syncDateByCityName(cityName);
        }
    
      
    }
    
}
