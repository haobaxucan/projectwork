package com.ecpss.dao;

import com.ecpss.domain.BlogType;

import java.util.List;

/**
 * Created by xc on 2019/8/26.
 */
public interface TypeDao {
    void saveOrUpdate(BlogType type);
    
    BlogType findType(Integer id);
    BlogType findType(String name);
    
    List<BlogType> findAll();
    
    void delete(Integer id);
    
}
