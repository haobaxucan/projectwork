package com.ecpss.controller;

import com.ecpss.User.Permission;
import com.ecpss.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/30.
 */
@Slf4j
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @RequestMapping("/ztree")
    public String ztree() {
        return "/permission/ztree";
    }
    
    @RequestMapping("/to")
    public String to() {
        return "/permission/permission";
    }
    
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData() {
        log.info("已经请求");
        List<Permission> permissions = new ArrayList<Permission>();
        List<Permission> ps = permissionService.queryAll();
//		Permission root = new Permission();
//		root.setName("顶级节点");
//
//		Permission child = new Permission();
//		child.setName("子节点");
//
//		root.getList().add(child);
//		permissions.add(root);
    
//        Permission rootPermission = permissionService.queryRootPermission();//顶级节点pid 设置为0
//        查询子节点
//        List<Permission> childPermissions=permissionService.queryChildPermissions( rootPermission.getId());
//        rootPermission.setList(childPermissions);
//
//        permissions.add(rootPermission);
//		Permission parent = new Permission();
//		parent.setId(0);
//		queryChildPermissions(parent);
		//permissions.
    
        Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
        for (Permission p : ps) {
            permissionMap.put(p.getId(), p);
        }
        for ( Permission p : ps ) {
            Permission child = p;
            if ( child.getPid() == 0 ) {
                permissions.add(p);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getList().add(child);
            }
        }
    
        return permissions;
        
    
    }
    
    /**
     * 递归查询许可信息
     * 1） 方法自己调用自己
     * 2）方法一定要存在跳出逻辑
     * 3）方法调用时，参数之间应该有规律
     * 4） 递归算法，效率比较低
     * @param parent
     */
    private void queryChildPermissions( Permission parent ) {
        List<Permission> childPermissions = permissionService.queryChildPermissions(parent.getId());
        
        for ( Permission permission : childPermissions ) {
            queryChildPermissions(permission);
        }
        
        parent.setList(childPermissions);
    }
    
}
