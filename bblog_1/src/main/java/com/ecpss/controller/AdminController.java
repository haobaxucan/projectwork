package com.ecpss.controller;

import com.ecpss.domain.BlogType;
import com.ecpss.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by xc on 2019/8/26.
 */

@Controller
@Slf4j
public class AdminController {
    
    @RequestMapping("/")
    public String ToLogin() {
        
        return "admin/login";
    }
    
    
    @RequestMapping("/login")
    public String ToLogin(@RequestParam(value = "name", required = false) String username, @RequestParam(value = "password", required = false) String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("账号错误");
            return "admin/login";
        } catch (CredentialsException e) {
            log.error("密码错误");
            return "admin/login";
        }
        
        return "admin/blog";
    }
    
    /**
     * 增加分類
     */
    @Autowired
    private TypeService typeService;
    
    @RequestMapping("/toAddType")
    public String toAddType(Model model) {
        List<BlogType> types = typeService.getAll();
        model.addAttribute("types", types);
        
        return "admin/types";
    }
    
    @RequestMapping("/addType")
    public String addType(BlogType blogType) {
//        进行不为空判断
        if(blogType.getName()==null){
            return null;//可以在前进行判断
        }
       
      
//        也不能进行重复添加类型
        BlogType type = typeService.getType(blogType.getName());
        if(type!=null){
            log.info("不能重复添加");
            return "admin/types";
        }
    
        log.info("type={}", blogType.getName());
        typeService.saveType(blogType);
        return "admin/types";
    }
    
    //    删除分类
    @RequestMapping("/delete")
    public String del(@RequestParam("id") Integer id) {
        log.info("删除id={}",id);
        typeService.delete(id);
        
        return "admin/types";
    }
    
    //    修改分类
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        BlogType blogType = typeService.getType(id);
        log.info("类型={}", blogType.getName());
        model.addAttribute("type", blogType);
        model.addAttribute("tid", blogType.getId());
        
        return "admin/update";
    }
    
    @RequestMapping("/update")
    public String update(BlogType type,@RequestParam("id")Integer id) {//update/1 ---路径 //update/id=1---参数
        
        BlogType blogType=new BlogType();
//        BeanUtils.copyProperties(type,blogType);
        log.info("id={}",id);
        blogType.setId(id);
        blogType.setName(type.getName());//实体对象分离
        typeService.saveType(blogType);
        return "admin/types";
    }
    
}
