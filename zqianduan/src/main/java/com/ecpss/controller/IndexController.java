package com.ecpss.controller;

import com.ecpss.enumpay.Pay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xc on 2019/7/5.
 */
@Slf4j
@Controller
public class IndexController {
    
    @RequestMapping("/")
    public String index(Model model){
        
        model.addAttribute("pays", Pay.values());
        
        return "index";
    }
    @RequestMapping("/login")
    public String login(@RequestParam("username")String name, HttpServletResponse response){
        log.info(name+"姓名");
        List<String> strings = Arrays.asList("aa", "cc", "bv");
        response.setContentType("text/html");
        strings.stream().forEach((e)->{
            if(e.equals(name)){
                try {
                    
                    log.info(name);
                    response.getWriter().print("用户名已经存在");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    
        return "index";
    }
    
    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam("name")String name,@RequestParam("pass")String pass){
        log.info("姓名{} ，密码{}",name,pass);
        return "index";
    }
    @RequestMapping("/ajax")
    public String ajax(@RequestParam(value = "name",required = false)String name,@RequestParam(value = "pass",required = false)String pass
    ,@RequestParam(value = "sel",required = false) String sel,Model model){
//        model.addAttribute("pays", Pay.values());
        log.info("姓名{} ，密码{} ,,{}",name,pass,sel);
        System.out.println("进行跳转");
        return "index";
    }
  
}
