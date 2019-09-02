package com.ecpss.service.impl;

import com.ecpss.dao.TypeDao;
import com.ecpss.domain.BlogType;
import com.ecpss.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xc on 2019/8/26.
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    
    @Override
    public void saveType(BlogType type) {
        typeDao.saveOrUpdate(type);
        
    }
    
    @Override
    public BlogType getType(Integer id) {
        return typeDao.findType(id);
    }
    
    @Override
    public BlogType getType(String name) {
        return typeDao.findType(name);
    }
    
    @Override
    public Page<BlogType> listType(Pageable pageable) {
        return null;
    }
    
    @Override
    public List<BlogType> getAll() {
        return typeDao.findAll();
    }
    
    @Override
    public void delete(Integer id) {
        typeDao.delete(id);
    }
    
    @Override
    public void updateType(BlogType type) {
        typeDao.saveOrUpdate(type);
    }
}
