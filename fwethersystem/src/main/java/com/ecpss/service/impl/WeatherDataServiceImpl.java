package com.ecpss.service.impl;

import com.ecpss.spring.domain.WeatherResponse;
import com.ecpss.service.WeatherDataService;
import com.ecpss.util.FastJsonUtils;
import com.ecpss.util.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xc on 2019/7/10.
 */
@Slf4j
@Service("wservice")
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeahter(uri);
    }
    
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
            String s=FastJsonUtils.mapToString(map);
            opsForValue.set(key,s,10L, TimeUnit.MINUTES);//数据保存到缓存
            return map;
        }
    }
    
    private WeatherResponse doGetWeahter(String uri) {
        log.info("aa");
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        String strBody = null;
        
        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
            System.out.println("strBody" + strBody);
        }
        
        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
//            resp= FastJsonUtils.getJsonToBean(strBody,WeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resp;
    }
    
    @Override
    public void syncDataByName(String name) {
        String uri = WEATHER_URI + "city=" + name;
        String key = uri;
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        Map<String, Object> map = HttpUtils.doGet(uri);
        String s=FastJsonUtils.mapToString(map);
        opsForValue.set(key,s,10L, TimeUnit.MINUTES);//数据保存到缓存
        
    }
}
