package com.ecpss.cart;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by xc on 2019/9/12.
 */
@Data
public class Cart {
    
    private long id;
    private String productId;
    private String productSkuId;
    private String memberId;
    private int quantity;
    private BigDecimal price;
    
    private String productName;
    private String productSubTitle;
    private String productSkuCode;
}
