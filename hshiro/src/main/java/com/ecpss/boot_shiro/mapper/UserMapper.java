package com.ecpss.boot_shiro.mapper;

import com.ecpss.boot_shiro.bean.User;

/**
 * Created by xc on 2019/8/4.
 */
public interface UserMapper {
    
    User getUser(Integer id);//mybatis 运行没有错误但是根据主键查不出数据
    
    User getUserByName(String name);
    
    String getAutoByid(Integer id);
}
