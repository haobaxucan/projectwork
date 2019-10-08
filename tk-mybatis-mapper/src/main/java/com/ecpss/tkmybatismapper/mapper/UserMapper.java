package com.ecpss.tkmybatismapper.mapper;

import com.ecpss.tkmybatismapper.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.00
 * @date 2019/9/27
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

}
