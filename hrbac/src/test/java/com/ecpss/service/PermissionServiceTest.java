package com.ecpss.service;

import com.ecpss.User.Permission;
import com.ecpss.User.User;
import com.ecpss.mapper.PermissionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xc on 2019/8/30.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermissionServiceTest {


    @Autowired
    private PermissionMapper permissionMapper;
    @Test
    public void queryRootPermission() throws Exception {
        Permission rootPermission = permissionMapper.queryRootPermission();
        System.out.println(rootPermission.getId());
    }
    @Test
    public void queryRPermissions() throws Exception {
        User user=new User();
        user.setId(1);
        List<Permission> permissions = permissionMapper.queryPermissionByUser(user);
        permissions.forEach((e)-> System.out.println(e.getName()));
    }
}