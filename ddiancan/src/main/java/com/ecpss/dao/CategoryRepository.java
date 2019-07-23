package com.ecpss.dao;

import com.ecpss.spring.domain.ProductCategory;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
public interface CategoryRepository {
    public ProductCategory getById(Integer id);
    
    public List<ProductCategory> getList();
    
    public void saveOrUpddate(ProductCategory productCategory);
    
}
