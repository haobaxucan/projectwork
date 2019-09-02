package com.ecpss.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by xc on 2019/8/26.
 */
public class DefaultDaoImpl {

    @PersistenceContext
    EntityManager em;
}
