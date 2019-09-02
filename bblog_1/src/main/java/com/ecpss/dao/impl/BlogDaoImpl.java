package com.ecpss.dao.impl;

import com.ecpss.dao.BlogDao;
import com.ecpss.domain.Blog;
import com.ecpss.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by xc on 2019/8/27.
 */
@Slf4j
@Repository
@Transactional
public class BlogDaoImpl extends DefaultDaoImpl implements BlogDao{
    
    @Override
    public void saveOrUpdate(Blog blog) {
        if (blog.getId() != null) {
            log.info("这是修改 id={}", blog.getId());
            em.merge(blog);
        } else {
            log.info("这是增加 id={}", blog.getId());
            em.persist(blog);
        }
    }
    
    @Override
    public List<Blog> getlist(Integer id) {
        String hql="from Blog b where b.Type_id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        List list = query.getResultList();
        return list;
    }
    
    @Override
    public List<Blog> getRecommend() {
        String hql="from Blog b where b.recommend=?1 order by updateTime desc";
        Query query = em.createQuery(hql).setParameter(1, true);
        List list = query.getResultList();
        return list;
    }
    
    @Override
    public List<Blog> getLike(String like) {
    
        String hql="from Blog b where b.title like ?1 or b.content like ?1";
        Query query = em.createQuery(hql).setParameter(1, like);
        List list = query.getResultList();
        return list;
    }
    
    @Override
    public int updateViews(Integer id) {
        String hql="update Blog b set b.views=b.views+1 where id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        int i = query.executeUpdate();
        return i;
    }
    
    @Override
    public List<Blog> queryByStringDate(String date) {

        String hql="from Blog b where b.updateTime =?1";
        Query query = em.createQuery(hql).setParameter(1, DateUtils.toDate(date));
        List list = query.getResultList();
        return list;
    }
    
    @Override
    public List<Blog> queryByDate(Date date) {
        String hql="from Blog b where b.updateTime < ?1";
        log.info("date={}",date);
        Query query = em.createQuery(hql).setParameter(1,date);
        List list = query.getResultList();
        return list;
    }
}
