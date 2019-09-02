package com.ecpss.dao.impl;

import com.ecpss.dao.UserDao;
import com.ecpss.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by xc on 2019/8/26.
 */
@Repository
@Transactional
public class UserDaoImpl extends DefaultDaoImpl implements UserDao {
    
    @Override
    public User getByName(String name) {
        String hql="from User u where u.username=?1";
        Query query = em.createQuery(hql).setParameter(1, name);
        User user = (User) query.getResultList().stream().findFirst().orElse(null);
        
        return user;
    }
}
