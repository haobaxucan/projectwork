package com.ecpss.util.utiltest;

/**
 * Created by xc on 2019/7/12.
 */
public class NumberConvertUtil {
    public static int convertToint(String intStr, int defValue) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }
    
    public static long convertTolong(String longStr, long defValue) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }
    
    public static float convertTofloat(String fStr, float defValue) {
        try {
            return Float.parseFloat(fStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defValue;
    }
    
    public static double convertTodouble(String dStr, double defValue) {
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }
    
    
    public static Integer convertToInteger(String intStr) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Long convertToLong(String longStr) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Float convertToFloat(String fStr) {
        try {
            return Float.parseFloat(fStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }
    
    public static Double convertToDouble(String dStr) {
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        String string = NumberConvertUtil.doubleConvertString(0);
        System.out.println(NumberConvertUtil.intConvertString((int)0.0));
        System.out.println(Double.toString(10.0));//转为String类型
    }
    public static  String doubleConvertString(double d){
        return String.valueOf(d);
    }
    public static  String intConvertString(int i){
        return String.valueOf(i);
    }
}
