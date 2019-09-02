package com.ecpss.service;

import com.ecpss.User.Permission;
import com.ecpss.User.User;

import java.util.List;

/**
 * Created by xc on 2019/8/30.
 */
public interface PermissionService {
    
    Permission queryRootPermission();
    
    List<Permission> queryChildPermissions(Integer id);
    
    List<Permission> queryAll();
    
    List<Permission> queryPermissionByUser(User user);
}
