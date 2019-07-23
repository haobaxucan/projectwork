package com.ecpss.service.impl;

import com.ecpss.dao.VideoDao;
import com.ecpss.spring.domain.Video;
import com.ecpss.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xc on 2019/6/18.
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    
    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }
    
    @Override
    public Video findById(Integer id) {
        
        return videoDao.findById(id);
    }
    
    @Override
    public void delete(Integer id) {
        videoDao.delete(id);
    }
    
    @Override
    public void saveOrUpdate(Video video) {
        videoDao.saveOrUpdate(video);
    }
}
