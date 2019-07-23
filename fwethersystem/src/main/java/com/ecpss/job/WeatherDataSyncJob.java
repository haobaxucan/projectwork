package com.ecpss.job;

import com.ecpss.spring.domain.City;
import com.ecpss.service.CityDataService;
import com.ecpss.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xc on 2019/7/15.
 * job 实现数据的同步
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean{
    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    private CityDataService cityDataService;
    
    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<City> cityListData =null;
        try {
             cityListData = cityDataService.getCityListData();
        } catch (Exception e) {
            log.error("解析城市数据失败");
            e.printStackTrace();
        }
        List<String> cityName = cityListData.stream().map(City::getCityName).collect(Collectors.toList());
        cityName.forEach(name->{
            weatherDataService.syncDataByName(name);
        });
        
        log.info("data");
    }
}
