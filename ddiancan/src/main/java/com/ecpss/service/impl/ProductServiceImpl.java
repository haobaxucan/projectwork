package com.ecpss.service.impl;

import com.ecpss.controller.dto.CartDto;
import com.ecpss.dao.ProductInfoRepository;
import com.ecpss.spring.domain.ProductInfo;
import com.ecpss.enums.ProductStatus;
import com.ecpss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductInfoRepository repository;
    
    @Override
    public ProductInfo findOne(Integer id) {
        return repository.findOne(id);
    }
    
    /**
     * 查询上架的商品
     * @return
     */
    @Override
    public List<ProductInfo> findAll() {
        return repository.findByProductStatus(ProductStatus.UP.getCode());
    }
    
    @Override
    public void addStore(List<CartDto> cartDtoList) {
        for(CartDto cartDto:cartDtoList){
            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
            if(productInfo==null){
                throw new RuntimeException("该商品不存在");
            }
            int store = productInfo.getStore() + cartDto.getQuantity();
            productInfo.setStore(store);
            repository.saveOrUpdate(productInfo);
        }
    
    }
    
    @Override
    public void decStore(List<CartDto> cartDtoList) { //多线程--扣库存--如果都是够--查出就来错了--可以用redis 锁机制
//        todo
        for(CartDto cartDto:cartDtoList){
            Integer id = cartDto.getProductId();
            ProductInfo productInfo = repository.findOne(id);
            if(null==productInfo){
                throw new RuntimeException("该商品不存在");
            }
            Integer store = productInfo.getStore()-cartDto.getQuantity();
            if(store<0){
                throw new RuntimeException("该商库存不够");
            }
            productInfo.setStore(store);//减掉的结果设置到库存
    
            repository.saveOrUpdate(productInfo);
    
        }
    }
}
