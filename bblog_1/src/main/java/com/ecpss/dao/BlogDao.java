package com.ecpss.dao;

import com.ecpss.domain.Blog;
import com.ecpss.domain.BlogType;

import java.util.Date;
import java.util.List;

/**
 * Created by xc on 2019/8/27.
 */
public interface BlogDao {
    void saveOrUpdate(Blog blog);
    
    List<Blog> getlist(Integer id);
    
    List<Blog> getRecommend();
    List<Blog> getLike(String like);
    
    int updateViews(Integer id);
    
    List<Blog> queryByStringDate(String date);
    List<Blog> queryByDate(Date date);
}
