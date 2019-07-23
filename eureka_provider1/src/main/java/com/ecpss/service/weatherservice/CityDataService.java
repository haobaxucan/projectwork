package com.ecpss.service.weatherservice;

import com.ecpss.service.weatherservice.vo.City;

import java.util.List;


public interface CityDataService {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity() throws Exception;
}
