package com.ecpss.Stringformat;

import org.junit.Test;

/**
 * Created by xc on 2019/6/20.
 */
public class StrFormatTest {
    /**
     %s	字符串类型	“喜欢请收藏”
     %c	字符类型	'm'
     %b	布尔类型	true
     %d	整数类型（十进制）	88
     %x	整数类型（十六进制）	FF
     %o	整数类型（八进制）	77
     %f	浮点类型	8.888
     %a	十六进制浮点类型	FF.35AE
     %e	指数类型	9.38e+5
     %g	通用浮点类型（f和e类型中较短的）	不举例(基本用不到)
     %h	散列码	不举例(基本用不到)
     %%	百分比类型	％(%特殊字符%%才能显示%)
     %n	换行符	不举例(基本用不到)
     %tx	日期与时间类型（x代表不同的日期与时间转换符)	不举例(基本用不到)
     */
    @Test
    public void t1(){
        String str=null;
        str=String.format("Hi,%s", "xx"); //请示%s 就相当于字符串的占位符
        System.out.println(str);//Hi,xx
        str=String.format("Hi,%s:%s.%s", "张三","李四","王张");//Hi,张三:李四.王张
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        
    }
}
