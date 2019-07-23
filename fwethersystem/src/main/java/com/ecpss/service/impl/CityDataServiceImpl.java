package com.ecpss.service.impl;

import com.ecpss.spring.domain.City;
import com.ecpss.spring.domain.CityList;
import com.ecpss.service.CityDataService;
import com.ecpss.util.utiltest.xml.JaxbUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by xc on 2019/7/16.
 */
@Service
public class CityDataServiceImpl implements CityDataService {
    
    @Override
    public List<City> getCityListData() throws Exception{
        Resource resource = new ClassPathResource("citylist.xml");
        InputStream inputStream = resource.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(reader);
        String line="";
        StringBuffer buffer=new StringBuffer();
        while ((line=bufferedReader.readLine())!=null){
            buffer.append(line);
        }
        bufferedReader.close();
        CityList cityList = JaxbUtil.convertXmlToJava(buffer.toString(), CityList.class);

        return cityList.getCityLists();
        // XML转为Java对象
//        CityList cityList = (CityList) XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
//        return cityList.getCityLists();
    }
}
