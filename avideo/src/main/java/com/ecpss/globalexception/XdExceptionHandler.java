package com.ecpss.globalexception;

import com.ecpss.utils.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xc on 2019/6/22.
 */
@ControllerAdvice
public class XdExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handler(Exception e){
        
        if(e instanceof GlobalException){
            GlobalException xdException =  (GlobalException)e;
            return JsonData.buildError(xdException.getMsg(),xdException.getCode());
        }else{
            return JsonData.buildError("全局异常，未知错误");
        }
    }
    
}
