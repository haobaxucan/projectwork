package com.ecpss.service;

import com.ecpss.domain.BlogType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by xc on 2019/8/26.
 */
public interface TypeService {
    void saveType(BlogType type);
    BlogType getType(Integer id);
    BlogType getType(String name);
    Page<BlogType> listType(Pageable pageable);
    
    List<BlogType> getAll();
    
    void delete(Integer id);
    void updateType(BlogType type);
    
}
