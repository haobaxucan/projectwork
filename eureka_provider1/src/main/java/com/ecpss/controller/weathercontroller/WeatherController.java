package com.ecpss.controller.weathercontroller;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.service.dataservice.WeatherDataService;
import com.ecpss.service.dataservice.vo.Weather;
import com.ecpss.service.weatherservice.CityDataService;
import com.ecpss.service.weatherservice.WeatherDataCollectionService;
import com.ecpss.util.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xc on 2019/7/15.
 */
@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    private CityDataService cityDataService;
    
    @Autowired
    WeatherDataCollectionService weatherDataCollectionService;
    

    
    @GetMapping("/cityName")
    public Weather getWeatherByCityName() {
        Map<String, Object> cityName = weatherDataService.getDataByCityName("广州");
        JSONObject data =(JSONObject) cityName.get("data");
        Weather weather = FastJsonUtils.JsonToJava(data, Weather.class);
        return weather;
    }
    
}
