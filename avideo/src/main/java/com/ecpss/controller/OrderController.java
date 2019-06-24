package com.ecpss.controller;

import com.ecpss.dto.VideoOrderDto;
import com.ecpss.service.VideoOrderService;
import com.ecpss.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xc on 2019/6/21.
 */
@Controller
public class OrderController {
    @Autowired
    private VideoOrderService videoOrderService;
    
    @RequestMapping("/add")
    @ResponseBody
    public JsonData saveOrder(@RequestParam(value = "video_id",required = true)int videoId,
                              HttpServletRequest request,
                              HttpServletResponse response){
        int userId = 1;    //临时写死的配置
        String ip = "120.25.1.43";
        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);
        String codeUrl = videoOrderService.saveOrUpdate(videoOrderDto);
        
        
    
    
        return JsonData.buildSuccess("success");
    }
}
