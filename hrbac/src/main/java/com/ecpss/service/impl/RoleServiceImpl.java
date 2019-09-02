package com.ecpss.service.impl;

import com.ecpss.User.Role;
import com.ecpss.mapper.RoleMapper;
import com.ecpss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/29.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    
    
    @Override
    public List<Role> pageQuery(Map<String, Object> map) {
        return roleMapper.pageQuery(map);
    }
    
    @Override
    public int queryAllCount() {
        return roleMapper.queryAllCount();
    }
    
    @Override
    public List<Role> queryAll() {
        return roleMapper.queryAll();
    }
    
    @Override
    public Role queryById(Integer roleid) {
        return roleMapper.queryRoleById(roleid);
    }
}
