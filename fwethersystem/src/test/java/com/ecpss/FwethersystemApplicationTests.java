package com.ecpss;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.spring.domain.City;
import com.ecpss.spring.domain.Weather;
import com.ecpss.service.CityDataService;
import com.ecpss.service.WeatherDataService;
import com.ecpss.util.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FwethersystemApplicationTests {
	@Autowired
	DataSource dataSource;
	@Autowired
	CityDataService dataService;
	@Autowired
	WeatherDataService weatherDataService;

	@Test
	public void contextLoads() throws Exception{
		List<City> cityListData = dataService.getCityListData();
//		System.out.println(cityListData.size());
		Map<String, Object> guang = weatherDataService.getDataByCityName("广州");
		JSONObject data =(JSONObject) guang.get("data");
		Weather weather = FastJsonUtils.JsonToJava(data, Weather.class);
		System.out.println(weather.getWendu());
		
	}
	

}
