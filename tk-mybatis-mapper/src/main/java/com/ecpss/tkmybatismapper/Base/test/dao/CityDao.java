package com.ecpss.tkmybatismapper.Base.test.dao;

import com.ecpss.tkmybatismapper.Base.CrudDAO;
import com.ecpss.tkmybatismapper.Base.test.entity.CityEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@Mapper
public interface CityDao extends CrudDAO<CityEntity> {

}
