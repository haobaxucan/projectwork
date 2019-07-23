package com.ecpss.dao;

import com.ecpss.spring.domain.ProductInfo;
import com.ecpss.service.qservice.PageResponsity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private PageResponsity pageResponsity;
    
    @Autowired
    private ProductInfoRepository repository;
    
    /**
     * 查询分页
     * @throws Exception
     */
    @Test
    public void page(){
        Pageable pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> page = pageResponsity.findAll(pageRequest);
        System.out.println(page.getTotalPages());
    }
    @Test
    public void saveOrUpdate() throws Exception {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductName("好吃2").setMoney(new BigDecimal(221.1)).setDescr("hao2").setProductIcon("http://www.2.com")
                .setProductStatus(0).setStore(12).setCreateTime(new Date());
        
        repository.saveOrUpdate(productInfo);
        
    }
    
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> byProductStatus = repository.findByProductStatus(0);
        System.out.println(byProductStatus);
        
    }
    
    
    
}