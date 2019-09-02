package com.ecpss.service.impl;

import com.ecpss.dao.BlogDao;
import com.ecpss.domain.Blog;
import com.ecpss.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/8/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    
    @Override
    public Blog getBlog(Integer id) {
        return null;
    }
    
    @Override
    public Blog getAndConvert(Integer id) {
        return null;
    }
    
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return null;
    }
    
    @Override
    public Page<Blog> listBlog(Integer tagId, Pageable pageable) {
        return null;
    }
    
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return null;
    }
    
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return blogDao.getlist(size);
    }
    
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }
    
    @Override
    public Integer countBlog() {
        return null;
    }
    
    @Override
    public void saveBlog(Blog blog) {
         blogDao.saveOrUpdate(blog);
    }
    
    @Override
    public Blog updateBlog(Integer id, Blog blog) {
        return null;
    }
    
    @Override
    public void deleteBlog(Integer id) {
    
    }
}
