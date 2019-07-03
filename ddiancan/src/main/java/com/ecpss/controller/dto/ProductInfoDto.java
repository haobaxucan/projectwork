package com.ecpss.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by xc on 2019/6/26.
 */
@Data
public class ProductInfoDto {
    
    @JsonProperty("id")
    private String productId;
    
    @JsonProperty("name")
    private String productName;
    
    @JsonProperty("price")
    private double productPrice;
    
    @JsonProperty("description")
    private String productDescription;
    
    @JsonProperty("icon")
    private String productIcon;
}
