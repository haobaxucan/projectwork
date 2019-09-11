package com.ecpss.mappper;

import com.ecpss.es.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xc on 2019/9/6.
 */
@Mapper
public interface GoodsMapper {
    @Select("select * from good")
     List<Goods> queryAll();

}
