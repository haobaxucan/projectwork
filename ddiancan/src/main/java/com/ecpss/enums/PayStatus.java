package com.ecpss.enums;

/**
 * Created by xc on 2019/6/26.
 */
public enum  PayStatus {
    PAY(0,"d等待支付"),
    SUCCESS(1,"支付成功");
    
    private Integer code;
    private String desc;
    
    PayStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
