package com.ecpss.service;

import com.ecpss.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/27.
 */
public interface BlogService {
    Blog getBlog(Integer id);
    
    Blog getAndConvert(Integer id);
    
//    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    
    Page<Blog> listBlog(Pageable pageable);
    
    Page<Blog> listBlog(Integer tagId,Pageable pageable);
    
    Page<Blog> listBlog(String query,Pageable pageable);
    
    List<Blog> listRecommendBlogTop(Integer size);
    
    Map<String,List<Blog>> archiveBlog();
    
    Integer countBlog();
    
    void saveBlog(Blog blog);
    
    Blog updateBlog(Integer id,Blog blog);
    
    void deleteBlog(Integer id);
}
