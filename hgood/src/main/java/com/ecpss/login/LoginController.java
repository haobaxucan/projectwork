package com.ecpss.login;

import io.searchbox.strings.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {
    
    /**
     * 添加拦截器  LoginInterceptor  WebMvcConfig
     *
     */

    @RequestMapping("/do_login")
    public String do_login(String name, String password, String requestURL, HttpServletRequest request) {
        log.info("name={},password={}",name,password);
        if (name.equals("xc")&&password.equals("123")){
            // 登录成功
    
            // 用jwt制作token
            String memberId ="xcid";
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("memberId",memberId);
    
            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();// 从request中获取ip
                if(StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }
            // 按照设计的算法对参数进行加密后，生成token
           String token = JwtUtil.encode("2019gmall0105", userMap, ip);
    
            // 将token存入redis一份(直接存进去)
            
            return "login/login";
        }
    
        return "login/index";
    }
    
    
    
    @RequestMapping("/login")
    public String to_login() {
        
        return "login/login";
    }
    
    
    
    
     @RequestMapping("/log1in")
    public String aa() {
        return "";
    }
    
     @RequestMapping("/a")
    public String bb() {
        return "";
    }
    
    
}
