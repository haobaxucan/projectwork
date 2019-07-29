package com.ecpss.com.ecpss.springboot.perfect;

import java.io.Serializable;

/**
 * Created by xc on 2019/7/29.
 */
public class MQConsumeResult implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 是否处理成功
     */
    private boolean isSuccess;
    /**
     * 如果处理失败，是否允许消息队列继续调用，直到处理成功，默认true
     */
    private boolean isReconsumeLater = true;
    /**
     * 是否需要记录消费日志，默认不记录
     */
    private boolean isSaveConsumeLog = false;
    /**
     * 错误Code
     */
    private String errCode;
    /**
     * 错误消息
     */
    private String errMsg;
    /**
     * 错误堆栈
     */
    private Throwable e;
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public boolean isSuccess() {
        return isSuccess;
    }
    
    public void setSuccess(boolean success) {
        isSuccess = success;
    }
    
    public boolean isReconsumeLater() {
        return isReconsumeLater;
    }
    
    public void setReconsumeLater(boolean reconsumeLater) {
        isReconsumeLater = reconsumeLater;
    }
    
    public boolean isSaveConsumeLog() {
        return isSaveConsumeLog;
    }
    
    public void setSaveConsumeLog(boolean saveConsumeLog) {
        isSaveConsumeLog = saveConsumeLog;
    }
    
    public String getErrCode() {
        return errCode;
    }
    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
    
    public String getErrMsg() {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    public Throwable getE() {
        return e;
    }
    
    public void setE(Throwable e) {
        this.e = e;
    }
}
