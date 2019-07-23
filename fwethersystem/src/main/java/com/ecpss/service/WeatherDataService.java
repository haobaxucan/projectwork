package com.ecpss.service;


import com.ecpss.spring.domain.WeatherResponse;

import java.util.Map;

public interface WeatherDataService {
	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param cityName
	 * @return
	 */
	Map<String, Object> getDataByCityName(String cityName);
	/**
	 * 同步数据 放入缓存中
	 */
	 void syncDataByName(String name);
	
}
