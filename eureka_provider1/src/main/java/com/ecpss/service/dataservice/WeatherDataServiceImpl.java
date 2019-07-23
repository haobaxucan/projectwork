package com.ecpss.service.dataservice;

import com.ecpss.util.FastJsonUtils;
import com.ecpss.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * WeatherDataService 实现.
 * 
 * @since 1.0.0 2017年11月22日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Slf4j
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	
	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Override
	public Map<String, Object> getDataByCityName(String cityName) {
		// 1先去缓存中拿--查询缓存数据
		// 2缓存中没有----就去第三方拿数据,把数据写入缓存
		String uri = WEATHER_URI + "city=" + cityName;
		String key = uri;
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		if (redisTemplate.hasKey(uri)) {//缓存数据
			log.info("redis has data");
			String s = opsForValue.get(key);
			return FastJsonUtils.stringToMap(s);
		} else {//拿到接口数据
			log.info("redis has not data");
			Map<String, Object> map = HttpUtils.doGet(uri);
			String s = FastJsonUtils.mapToString(map);
			opsForValue.set(key, s, 10L, TimeUnit.MINUTES);//数据保存到缓存
			return map;
		}
	}
}