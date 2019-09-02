package com.ecpss.mapper;

import com.ecpss.User.Permission;
import com.ecpss.User.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xc on 2019/8/30.
 */
public interface PermissionMapper {
    @Select("select * from permission where pid is null")
    Permission queryRootPermission();
    @Select("select * from permission where pid=#{id}")
    List<Permission> quryChild(Integer id);
    @Select("select * from permission")
    List<Permission> queryAll();
    
    List<Permission> queryPermissionByUser(User user);
}
