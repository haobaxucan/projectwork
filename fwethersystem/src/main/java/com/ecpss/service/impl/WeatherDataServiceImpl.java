package com.ecpss.service.impl;

import com.ecpss.domain.WeatherResponse;
import com.ecpss.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xc on 2019/7/10.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService{
    /**
     * 天气的接口
     */
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
//    @Autowired
//    RestTemplate template;
    
    
    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
       
        return null;
    }
    
    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        
        return null;
    }
}
