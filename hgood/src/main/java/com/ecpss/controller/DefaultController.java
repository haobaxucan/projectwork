package com.ecpss.controller;

import com.ecpss.utils.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by xc on 2019/9/3.
 */
@Controller
@Slf4j
public class DefaultController {

    @RequestMapping("/upload")
    public String upload(){
        
        return "upload";
    }
    @RequestMapping("/to_upload")
    @ResponseBody
    public String index(@RequestParam("file") MultipartFile multipartFile){
//        log.info("--{},--{}",multipartFile.getName(),multipartFile.getOriginalFilename());
        FastDFSClient.uploadFile(multipartFile);
        
        return "success";
    }
    
    
    
    
}
