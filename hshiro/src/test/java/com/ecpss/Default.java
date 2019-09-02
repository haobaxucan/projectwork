package com.ecpss;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xc on 2019/8/4.
 */

public class Default {
    //登录
    @Test
    public void te1() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2.通过Factory对象获取SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        // 3.将SecurityManager对象添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
    
        // 4.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("root", "123456");
        // 登录操作
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
    
        }
        // 获取登录的状态
        System.out.println(subject.isAuthenticated());
    
    }
    
    //    授权
    @Test
    public void te2() {
    
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro1.ini");
        // 2.通过Factory对象获取SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        // 3.将SecurityManager对象添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
    
        // 4.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("root", "123456");
        // 登录操作
        try {
            subject.login(token);
//
            
            
        } catch (UnknownAccountException e) {
            System.out.println("账号出错...");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码出错...");
        }
        // 获取登录的状态
        System.out.println(subject.isAuthenticated());
        // 认证通过后进行权限验证 角色
//        System.out.println(subject.getPrincipal()+"是否具有role1角色:"+subject.hasRole("role1"));
//        System.out.println(subject.getPrincipal()+"是否具有role3角色:"+subject.hasRole("role3"));
//        boolean[] types = subject.hasRoles(Arrays.asList("role1","role2"));
//        System.out.println(subject.getPrincipal()+"是否具有role1和role2角色:"+types[0]+","+types[1]);
        //subject.checkRole("role1");
        //subject.checkRole("role3");
        // 验证权限
//        System.out.println(subject.getPrincipal()+"是否具有user:create权限:"+subject.isPermitted("user:create"));
//        System.out.println(subject.getPrincipal()+"是否具有user:delete角色:"+subject.isPermitted("user:delete"));
//        boolean t = subject.isPermittedAll("user:create","user:delete");
//        System.out.println(subject.getPrincipal()+"是否具有user:create和user:delete的权限:"+t);
//        ---------------------
    
        System.out.println(subject.getPrincipal() + "--" + subject.hasRole("xc") + "权限" + subject.isPermitted("xc:update"));
    
    
    }
    
    /**
     * 盐值加密
     */
    @Test
    public void te3() {//使用  HashedCredentialsMatcher匹配器
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro1.ini");
        // 2.通过Factory对象获取SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        // 3.将SecurityManager对象添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
    
        // 4.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("root", "123456");
        // 登录操作
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号出错...");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码出错...");
        }
        // 获取登录的状态
        System.out.println(subject.isAuthenticated());
    }
}