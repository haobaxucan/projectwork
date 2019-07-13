package com.ecpss.util.utiltest;

import java.math.BigDecimal;

/**
 * Created by xc on 2019/1/13.
 */
public class BigDecimalUtil {
    private BigDecimalUtil(){
    
    }
    
    public static BigDecimal addTwo(String v1, String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(1,BigDecimal.ROUND_HALF_UP);//四舍五入保留两位小数
    }
    public static BigDecimal addFour(String v1, String v2,String v3,String v4){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal b3 = new BigDecimal(v3);
        BigDecimal b4 = new BigDecimal(v4);
        return b1.add(b2).add(b3).add(b4);
    }
    
    public static BigDecimal sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }
    
    public static BigDecimal mul(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }
    
    public static BigDecimal div(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//四舍五入,保留2位小数
    }
//    转化double
    public static double convertToDouble(BigDecimal bigDecimal){
        return bigDecimal.doubleValue();
    }
    
    public static void main(String[] args) {
        BigDecimal add = new BigDecimal("1").add(new BigDecimal("1"));
        double v = BigDecimalUtil.convertToDouble(add);
       
    
    }
}
