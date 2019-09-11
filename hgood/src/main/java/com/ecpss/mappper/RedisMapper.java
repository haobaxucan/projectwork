package com.ecpss.mappper;

import com.ecpss.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xc on 2019/9/4.
 */
@Mapper
public interface RedisMapper {
    @Select("select * from user where id=#{id}")
    User getByid(Long id);
    
    @Select("select * from user where name=#{name}")
    User getHot(String name);
    
    @Select("select * from user where id=#{id}")
    User getData(Long id);
    
}
