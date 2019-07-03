package com.ecpss.enums;

/**
 * Created by xc on 2019/6/26.
 */
public enum  OrderStatus {

    NEW(0,"创建订单"),
    FINISHED(1,"完成订单"),
    CANCLE(2,"取消订单");
    private Integer code;
    private String mess;
    
    OrderStatus(Integer code, String mess) {
        this.code = code;
        this.mess = mess;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getMess() {
        return mess;
    }
}
