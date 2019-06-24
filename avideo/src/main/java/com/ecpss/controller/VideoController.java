package com.ecpss.controller;

import com.ecpss.domain.Video;
import com.ecpss.service.VideoService;
import com.ecpss.utils.JpaPageHelper;
import com.ecpss.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xc on 2019/6/18.
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @RequestMapping("/list")
    public List<Video> list(){
        return videoService.findAll();
    }
    
    @RequestMapping("/id")
    public Video getById(Integer id){
        return videoService.findById(id);
    }
    @RequestMapping("/delete")
    public void delete(Integer id){
        videoService.delete(id);
    }
    @RequestMapping("/save")
    public void Update(String title){
        Video video=new Video();
        video.setTitle(title);
        videoService.saveOrUpdate(video);
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/page")
    public List<PageInfo> all(){
        List<Video> list = videoService.findAll();
        JpaPageHelper jpaPageHelper=new JpaPageHelper();
        List<PageInfo> infos = jpaPageHelper.SetStartPage(list, 1, 2);
        return  infos;
        
    }

}
