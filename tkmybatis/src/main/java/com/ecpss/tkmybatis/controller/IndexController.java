package com.ecpss.tkmybatis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.00
 * @date 2019/10/12
 */
@Controller
@Slf4j
public class IndexController {
//    访问localhost:8081/index
//    访问localhost:8081/
//    访问localhost/
    @RequestMapping("/")
//    @ResponseBody
    public String index(){
        log.info("开始请求");
        return "index";
    }



}
