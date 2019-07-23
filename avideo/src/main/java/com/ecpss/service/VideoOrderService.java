package com.ecpss.service;

import com.ecpss.spring.domain.VideoOrder;
import com.ecpss.dto.VideoOrderDto;

import java.util.List;

/**
 * Created by xc on 2019/6/21.
 */
public interface VideoOrderService {
    public List<VideoOrder> findAll();
    
    public VideoOrder findById(Integer id);
    
    public void delete(Integer id);
    
    public String saveOrUpdate(VideoOrderDto videoOrderDto);
}
