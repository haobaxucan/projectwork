package com.ecpss.wx_public_number;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.ArrayUtils;

import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.Collections;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WxPublicNumberApplicationTests {

    @Test
    public void contextLoads() {
        String s[]=null;
        boolean b = ArrayUtils.isEmpty(s);
        System.out.println(b);
//        Arrays.sort(s);
//        Arrays.stream(s).forEach(System.out::println);
    }
    @Test
    public void S(){
        /**
         * //匹配 就是全等。
         * /*
         * 语法：
         *    switch(表达式){
         *      case 值1:
         *        表达式的值和 值1匹配上了，需要执行的代码;
         *        break;
         *      case 值2:
         *        表达式的值和 值2匹配上了，需要执行的代码;
         *      break;
         *      case 值3:
         *        表达式的值和 值3匹配上了，需要执行的代码;
         *      break;
         *      default:
         *        如果表达式的值和以上的case后面的值都没有匹配上，那么就执行这里的代码。
         *        break;
         *    }
        //不写break会穿透到下一个break
         */
        String s="a";
        switch (s){
            case "s":
                System.out.println("11");
            case "b":
                System.out.println("bbbb");
            case "c":
                System.out.println("cccc");
                break;
            case "d":
                System.out.println("dd");
                default:
                    System.out.println("dbreak");

        }
    }

}
