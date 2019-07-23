package com.ecpss.dao.impl;

import com.ecpss.dao.UserDao;
import com.ecpss.spring.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by xc on 2019/6/25.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public void Save(User user) {
        
        if(user.getId()==null){
            entityManager.persist(user);
        }
        entityManager.merge(user);
        
    }
}
