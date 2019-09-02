package com.ecpss.service;

import com.ecpss.User.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/29.
 */
public interface RoleService {
    List<Role> pageQuery(Map<String, Object> map);
    
    int queryAllCount();
    
    List<Role> queryAll();
    
    Role queryById(Integer roleid);
}
