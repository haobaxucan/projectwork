package com.ecpss.dao;

import com.ecpss.domain.Video;

import java.util.List;

/**
 * Created by xc on 2019/6/18.
 */
public interface VideoDao {
    
    List<Video> findAll();
    
    Video findById(Integer id);
    
    void delete(Integer id);
    
    void saveOrUpdate(Video video);
    
    
}
