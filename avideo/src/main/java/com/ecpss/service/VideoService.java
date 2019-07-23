package com.ecpss.service;

import com.ecpss.spring.domain.Video;

import java.util.List;

/**
 * Created by xc on 2019/6/18.
 */
public interface VideoService {
    
    List<Video> findAll();
    
    Video findById(Integer id);
    
    void delete(Integer id);
    
    void saveOrUpdate(Video video);
}
