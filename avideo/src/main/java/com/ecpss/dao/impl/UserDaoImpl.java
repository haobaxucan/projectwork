package com.ecpss.dao.impl;

import com.ecpss.dao.UserDao;
import com.ecpss.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.logging.Logger;

/**
 * Created by xc on 2019/6/20.
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl implements UserDao {
    
   
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public User findById(Integer id) {
        String hql="from User u where u.id=?1";
        Query query = entityManager.createQuery(hql).setParameter(1, id);
        
    
        return (User)query.getResultList().stream().findFirst().orElse(null);
    }
    
    @Override
    public User findByOpen(String openId) {
        String hql="from User u where u.openid=?1";
        Query query = entityManager.createQuery(hql).setParameter(1, openId);
        return (User)query.getResultList().stream().findFirst().orElse(null);
    }
    
    @Override
    public void save(User user) {
        entityManager.persist(user);
    
    }
}
