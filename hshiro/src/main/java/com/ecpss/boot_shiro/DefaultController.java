package com.ecpss.boot_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xc on 2019/8/4.
 */
@Controller

public class DefaultController {


@RequestMapping("/ok")
    public String index(){
        return "index";
    }
    @RequiresPermissions("user:add")
    @RequestMapping("/add")
    public String zj(){
        System.out.println("---测试缓存");//每一次都去数据库中进行权限查询
        return "user/add";
    }
    @RequiresPermissions("user:update")
    @RequestMapping("/update")
    public String up(){
        return "user/update";
    }
   
    @RequestMapping("/")
    public String login(){
        
        return "login";
    }
    
     @RequestMapping("/unAuthior")
    public String unAuthior(){
        return "user/unAuthior";
    }
    
    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "username",required = false)String username, @RequestParam(value = "password",required = false)String password, Model model){
    
        Subject subject = SecurityUtils.getSubject();
    
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
    
        try {
            subject.login(token);
            Session session =  SecurityUtils.getSubject().getSession();
            session.setAttribute("msg", "自定义传递的信息...");
            
//            subject.hasRole()
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (CredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    
    
        return "index";
    }
    
}
