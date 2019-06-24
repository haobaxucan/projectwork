package com.ecpss.dao.impl;

import com.ecpss.dao.VideoOrderDao;
import com.ecpss.domain.VideoOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by xc on 2019/6/21.
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class VideoOrderDaoImpl implements VideoOrderDao{
    @PersistenceContext
    EntityManager em;
    @Override
    public List<VideoOrder> findAll() {
        String hql="from VideoOrder";
        Query query = em.createQuery(hql);
        
        return query.getResultList();
    }
    
    @Override
    public VideoOrder findById(Integer id) {
        String hql="from VideoOrder v where v.id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        
        return (VideoOrder) query.getResultList().stream().findFirst().orElse(null);
    }
    
    @Override
    public void delete(Integer id) {
        String hql="delete from VideoOrder v where v.id=?1";
        Query query = em.createQuery(hql).setParameter(1, id);
        query.executeUpdate();
    }
    
    @Override
    public void saveOrUpdate(VideoOrder videoOrder) {
            if(videoOrder.getId()==null){
                em.persist(videoOrder);
            }else {
                em.merge(videoOrder);
            }
    }
}
