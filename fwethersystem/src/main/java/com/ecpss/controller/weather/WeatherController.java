package com.ecpss.controller.weather;

import com.ecpss.spring.domain.WeatherResponse;
import com.ecpss.service.CityDataService;
import com.ecpss.service.WeatherDataService;
import com.ecpss.service.WeatherReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xc on 2019/7/15.
 */
@Slf4j
@Controller
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    @Qualifier(value = "wservice")
    private WeatherDataService weatherDataService;
    @Autowired
    private CityDataService cityDataService;
    
    @Autowired
    WeatherReportService weatherReportService;
    
    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
        return weatherDataService.getDataByCityId(cityId);
    }
    
    @GetMapping("/cityName/{cityName}")
    public String getWeatherByCityName(@PathVariable("cityName") String cityName, Model model) {
    
        try {
            model.addAttribute("cityList",cityDataService.getCityListData());
//            选中效果
            model.addAttribute("cityName",cityName);
            model.addAttribute("weather",weatherReportService.getWeatherByName(cityName));
        } catch (Exception e) {
            log.error("解析城市数据失败");
            e.printStackTrace();
        }
        weatherDataService.getDataByCityName(cityName);
         return "report/report";
    }
}
