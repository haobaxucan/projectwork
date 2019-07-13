package com.ecpss.enumCode;

/**
 * Created by xc on 2019/7/12.
 */
public class ResponseCode {
    private String mess;
    
    public ResponseCode(String mess) {
        this.mess = mess;
    }
    
    public String getCode() {
        return mess;
    }
    
    public void setCode(String mess) {
        this.mess = mess;
    }
    
   
}
