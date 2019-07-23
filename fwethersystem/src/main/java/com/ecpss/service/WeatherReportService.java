package com.ecpss.service;

import com.ecpss.spring.domain.Weather;

/**
 * Created by xc on 2019/7/16.
 */
public interface WeatherReportService {
    
    public Weather getWeatherByName(String name);
    
    
}
