package com.ecpss.lamda.guavademo;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/7/14.
 */
public class gua {
    @Test
    public void aa() {
        String s = "a|b|c | || ";
        List<String> strings = Splitter.on("|").omitEmptyStrings().trimResults().splitToList(s);
        System.out.println(strings);//[a, b, c] omitEmptyStrings 忽略空元素 trimResults()//忽略空格
        List<String> strings1 = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(",a,,,b,c,,");
        System.out.println(strings1);
    }
    @Test
    public void aad() {
        String s = "a=ss|b=ss|c=b | || ";
        Map<String, String> split = Splitter.on("|").omitEmptyStrings().trimResults().withKeyValueSeparator("=").split(s);
        System.out.println(split);//{a=ss, b=ss, c=b}
//        assertt
        
    
    }
}
