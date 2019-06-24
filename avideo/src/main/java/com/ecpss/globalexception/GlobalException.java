package com.ecpss.globalexception;

import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

/**
 *
 * 异常处理控制器
 *
 */
public class GlobalException extends RuntimeException {
    
    
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String msg;
    
    public GlobalException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
