package com.ecpss.service.dataservice;

import java.util.Map;

/**
 * Weather Data Service.
 * 
 * @since 1.0.0 2017年11月26日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface WeatherDataService {
	 static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	public Map<String, Object> getDataByCityName(String cityName);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param cityName
	 * @return
	 */
	
	
}
