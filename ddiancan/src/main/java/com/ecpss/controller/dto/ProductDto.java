package com.ecpss.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xc on 2019/6/26.
 */
public class ProductDto<T>{
    @JsonProperty("name")//json序列时候的名字
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoDto> infoList;
    
}
