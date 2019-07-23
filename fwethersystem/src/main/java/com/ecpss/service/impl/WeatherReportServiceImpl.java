package com.ecpss.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.spring.domain.Weather;
import com.ecpss.service.WeatherDataService;
import com.ecpss.service.WeatherReportService;
import com.ecpss.util.FastJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xc on 2019/7/16.
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService{
    @Autowired
    private WeatherDataService weatherDataService;
    @Override
    public Weather getWeatherByName(String name) {
        Map<String, Object> map = weatherDataService.getDataByCityName(name);
        
        JSONObject data =(JSONObject) map.get("data");
        Weather weather = FastJsonUtils.JsonToJava(data, Weather.class);
        return weather;
    }
}
