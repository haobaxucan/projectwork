package com.ecpss.controller;

import com.ecpss.service.RedisUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xc on 2019/9/4.
 */
@Slf4j
@Controller
public class RedisController {
   
    @Autowired
    RedisUserService redisUserService;
    @ResponseBody
    @RequestMapping("/redis/{id}")

    public String s(@PathVariable("id")String id, HttpServletRequest request){
        log.info("请求开始id={},ip={}",id,request.getRemoteAddr());
        Long aLong = Long.valueOf(id);
        redisUserService.getData(aLong);
        return  "aa";
    }
}
