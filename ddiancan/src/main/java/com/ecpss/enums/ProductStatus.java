package com.ecpss.enums;

/**
 * Created by xc on 2019/6/26.
 */
public enum  ProductStatus {
    
    UP(0,"上架"),
    
    down(1,"下架");
    
    private Integer code;
    private String message;
    
    ProductStatus(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}
