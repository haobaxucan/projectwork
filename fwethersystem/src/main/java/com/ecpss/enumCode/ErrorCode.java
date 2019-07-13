package com.ecpss.enumCode;

/**
 * Created by xc on 2019/7/12.
 */
public enum ErrorCode {
    SUCESS("成功"),
    Fail("失败");
    private String text;
    
    ErrorCode(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
