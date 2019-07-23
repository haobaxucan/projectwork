package com.ecpss.controller;

import com.ecpss.controller.dto.ResultDto;
import com.ecpss.spring.domain.ProductInfo;
import com.ecpss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xc on 2019/6/26.
 */
@RestController
@RequestMapping("/buy")
public class BuyController {
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/list")
    public ResultDto list(){
        List<ProductInfo> infoList = productService.findAll();
//        List<Integer> list=new ArrayList<>();
        List<Integer> collect = infoList.stream().map(ProductInfo::getProductStatus).collect(Collectors.toList());

//        BeanUtils拷贝
        return null;
    }
    
}
