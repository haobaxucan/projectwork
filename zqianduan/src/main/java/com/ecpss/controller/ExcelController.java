package com.ecpss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by xc on 2019/7/8.
 */
@Slf4j
@Controller
public class ExcelController {
    
    @RequestMapping(value = "/import",method = RequestMethod.POST)
   
    public Object importSubMerchant(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request) {
        try {
            System.out.println("1111==========");
//            log.info("a{}",file.getName());
            InputStream inputStream = file.getInputStream();
//            String s = ParseExcelUtil.readExcel(inputStream, file.getOriginalFilename(), 0, 2);
//            log.info("信息{}");
    
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
    
    @RequestMapping("/swi")
    public String switch1(Model m){
        List<String> strings = java.util.Arrays.asList("aa", "vv", "dd");
//        ServletRequest
        
        
        m.addAttribute("lists",strings);
        m.addAttribute("a","1");
        return "index";
    }
}
