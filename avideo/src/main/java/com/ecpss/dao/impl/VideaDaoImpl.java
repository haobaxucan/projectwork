package com.ecpss.dao.impl;

import com.ecpss.dao.VideoDao;
import com.ecpss.spring.domain.Video;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by xc on 2019/6/18.
 */
@Repository
/**
 * 调用persist 和 merge 必须使用事务
 @Transactional(rollbackFor = Exception.class)
 delete 也会报事务要求异常
 */
@Transactional(rollbackFor = Exception.class)
public class VideaDaoImpl implements VideoDao {
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<Video> findAll() {
        String hql = "from Video";
        Query query = entityManager.createQuery(hql);
        List<Video> resultList = query.getResultList();
        return resultList;
        
    }
    
    @Override
    public Video findById(Integer id) {
        String hql = "from Video v where v.id= ?1";
        Query query = entityManager.createQuery(hql);
        query.setParameter(1, id);
        return (Video) query.getResultList().stream().findFirst().orElse(null);
    }
    
    @Override
    public void delete(Integer id) {
        String hql = "delete from Video v where v.id= ?1"; //Hibernate语句: delete from video_order where id=?
        Query query = entityManager.createQuery(hql);
        query.setParameter(1, id);
        query.executeUpdate();
    }
    
    @Override
    public void saveOrUpdate(Video video) {
        if (video.getId() == null) {
            entityManager.persist(video); //临时态 变为持久态
        } else {
            entityManager.merge(video);//游离态（数据库中有，但是没在session缓存中） 变为持久态
        }
        
    }
    
    
}
