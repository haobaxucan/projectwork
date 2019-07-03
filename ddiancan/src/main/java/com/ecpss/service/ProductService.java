package com.ecpss.service;

import com.ecpss.controller.dto.CartDto;
import com.ecpss.domain.ProductInfo;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
public interface ProductService {
    /**
     * 查询一个
     *
     */
    public ProductInfo findOne(Integer id);
    
    /**
     * 查询所有
     */
    public List<ProductInfo> findAll();
    
    void addStore(List<CartDto> cartDtoList); //增加库存
    void decStore(List<CartDto> cartDtoList); //减少
}
