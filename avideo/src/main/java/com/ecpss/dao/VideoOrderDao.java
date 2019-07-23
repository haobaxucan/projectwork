package com.ecpss.dao;



import com.ecpss.spring.domain.VideoOrder;

import java.util.List;

/**
 * Created by xc on 2019/6/21.
 */
public interface VideoOrderDao {
    
    List<VideoOrder> findAll();
    
    VideoOrder findById(Integer id);
    
    void delete(Integer id);
    
    void saveOrUpdate(VideoOrder videoOrder);
}
