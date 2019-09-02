package com.ecpss;

import com.ecpss.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xc on 2019/8/24.
 */
public class Son extends  Father{
    public Son(String message) {
        super(message);
    }
    
    public static void main(String[] args) {
        Son son=new Son("aa");
        Date date = DateUtils.toDateTime("2019-08-28 10:47:26", "yyyy-MM-dd HH:mm:ss");
       
    
    }
}
