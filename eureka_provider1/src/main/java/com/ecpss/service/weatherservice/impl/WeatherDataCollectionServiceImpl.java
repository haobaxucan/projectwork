package com.ecpss.service.weatherservice.impl;

import com.ecpss.service.weatherservice.WeatherDataCollectionService;
import com.ecpss.util.FastJsonUtils;
import com.ecpss.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	private static final long TIME_OUT = 1800L; // 1800s
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public void syncDateByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		String key = uri;
		Map<String, Object> map = HttpUtils.doGet(uri);
		String s=FastJsonUtils.mapToString(map);
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key,s,10L, TimeUnit.MINUTES);//数据保存到缓存
		
	}
}
