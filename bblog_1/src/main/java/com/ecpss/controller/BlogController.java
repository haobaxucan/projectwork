package com.ecpss.controller;

import com.ecpss.domain.Blog;
import com.ecpss.domain.dto.BlogDto;
import com.ecpss.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by xc on 2019/8/24.
 */
@Controller
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;
    
    @RequestMapping("/toAddBlog")
    public String blog() {
        
        return "admin/add_blog/add";
    }
    
    /**
     * 博客的添加
     *
     * @param blogDto
     * @return
     */
    @RequestMapping("/add")
    public String add(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        log.info("类型id={}", blogDto.getType_id());
        blog.setType_id(blogDto.getType_id());
        blog.setRecommend(true);
        blog.setUpdateTime(new Date());
        blogService.saveBlog(blog);
        return "admin/add_blog/add";
        
    }
//    博客的查询
    
    @RequestMapping("/query")
    public String query(@RequestParam("type_id") Integer id) {//更具条件查询--类型
        log.info("id={}", id);
        List<Blog> blogs = blogService.listRecommendBlogTop(id);

//       blogs.forEach(System.out::println);
        blogs.forEach((x) -> System.out.println(x.getContent()));
        return "admin/add_blog/add";
    }
    
    //    博客的搜索
    @RequestMapping("/queryLike")
    public String queryLike(@RequestParam("type_id") Integer id) {//更具条件查询--类型
        
        
        return "admin/add_blog/add";
    }
}
