package com.ecpss.dao;

import com.ecpss.spring.domain.ProductInfo;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
public interface ProductInfoRepository{
    public void saveOrUpdate(ProductInfo productInfo);
    
    List<ProductInfo> findByProductStatus(Integer productStatus);
    ProductInfo findOne(Integer id);
    
   
}
