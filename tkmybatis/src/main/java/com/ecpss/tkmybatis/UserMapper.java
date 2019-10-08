package com.ecpss.tkmybatis;

import com.ecpss.tkmybatis.com.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xc
 * @version 1.00
 * @date 2019/9/25
 */
@Mapper
public interface UserMapper {

public User getById(Integer id);

public User getByIdAndAddr(@Param("id1") Integer id,@Param("addx") String addx);
public User getPo(User  user);
public User getMap(Map<String,Object> map);


}
