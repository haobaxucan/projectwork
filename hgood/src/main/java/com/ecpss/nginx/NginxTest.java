package com.ecpss.nginx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class NginxTest {
    @RequestMapping("/nginx")
    public String nginx(HttpServletRequest request){
        
        log.info("端口={}",request.getRequestURI());
        
        return "nginx";
    }
}
