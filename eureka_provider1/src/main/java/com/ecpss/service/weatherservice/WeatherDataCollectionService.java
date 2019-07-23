package com.ecpss.service.weatherservice;

/**
 * Weather Data Collection Service.
 * 
 * @since 1.0.0 2017年11月26日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface WeatherDataCollectionService {

	/**
	 * 根据城市Name同步天气
	 * @param cityName
	 */
	void syncDateByCityName(String cityName);
}
