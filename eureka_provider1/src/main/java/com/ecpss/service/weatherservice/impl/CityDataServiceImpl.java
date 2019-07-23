package com.ecpss.service.weatherservice.impl;

import com.ecpss.service.weatherservice.CityDataService;
import com.ecpss.service.weatherservice.vo.City;
import com.ecpss.service.weatherservice.vo.CityList;
import com.ecpss.util.JaxbUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


@Service
public class CityDataServiceImpl implements CityDataService {

	@Override
	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		
		while ((line = br.readLine()) !=null) {
			buffer.append(line);
		}
		
		br.close();
		
		// XML转为Java对象
		CityList cityList = JaxbUtil.convertXmlToJava(buffer.toString(), CityList.class);
		
		return cityList.getCityLists();
	}

}
