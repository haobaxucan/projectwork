package com.ecpss.dao.impl;

import com.ecpss.dao.BlogDao;
import com.ecpss.domain.Blog;
import com.ecpss.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


/**
 * Created by xc on 2019/8/27.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogDaoImplTest {
    @Autowired
    BlogDao blogDao;
    @Test
    public void res(){
        List<Blog> list = blogDao.getRecommend();
        list.stream().forEach((x)-> System.out.println(x.getContent()));
    }
    
    @Test
    public void like(){
        String hql="%计%";
//        String hql="计";
        List<Blog> list = blogDao.getLike(hql);
        System.out.println(list.size());
        list.stream().forEach((x)-> System.out.println(x.getContent()));
    }
    @Test
    public void count(){
        int i = blogDao.updateViews(27);
        System.out.println(i);
    
    }
    @Test
    public void date(){
        List<Blog> blogs = blogDao.queryByStringDate("2019-08-28 10:47:26");
        System.out.println(blogs.size());
        blogs.stream().forEach((x)-> System.out.println(x.getContent()));
        
    }
    @Test
    public void byQuety(){

        List<Blog> blogs = blogDao.queryByDate(new Date());
        System.out.println(blogs.size());
//        blogs.stream().forEach((x)-> System.out.println(x.getContent()));
        
    }
    
}