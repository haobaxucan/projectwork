package com.ecpss.service.impl;

import com.ecpss.User.Permission;
import com.ecpss.User.User;
import com.ecpss.mapper.PermissionMapper;
import com.ecpss.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xc on 2019/8/30.
 */
@Transactional
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionMapper permissionMapper;
    
    
    @Override
    public Permission queryRootPermission() {
        return permissionMapper.queryRootPermission();
    }
    
    @Override
    public List<Permission> queryChildPermissions(Integer id) {
        return permissionMapper.quryChild(id);
    }
    
    @Override
    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }
    
    @Override
    public List<Permission> queryPermissionByUser(User user) {
        return permissionMapper.queryPermissionByUser(user);
    }
}
