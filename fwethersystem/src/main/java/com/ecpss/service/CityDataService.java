package com.ecpss.service;

import com.ecpss.spring.domain.City;

import java.util.List;

/**
 * Created by xc on 2019/7/16.
 */
public interface CityDataService {
    /**
     * 获得城市列表数据
     */
    public List<City> getCityListData () throws Exception;
}
