package com.ecpss.mapper;


import com.ecpss.User.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    List<Role> pageQuery(Map<String, Object> map);
    
    int queryAllCount();
    
    List<Role> queryAll();
    
    Role queryRoleById(Integer roleid);
}
