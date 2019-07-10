package com.ecpss.enumpay;

import java.util.Arrays;

/**
 * Created by xc on 2019/7/8.
 */
public enum Pay {
    PAY(0,"成功"),
    SUCCESS(1,"成功2");
    private int code;
    private String text;
    
    Pay(int code, String text) {
        this.code = code;
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    public int getCode() {
        return code;
    }
    
    public static void main(String[] args) {
        System.out.println(Pay.PAY.getText());
        Pay[] values = Pay.values();
        Arrays.stream(values).forEach(System.out::println);
    }
}
