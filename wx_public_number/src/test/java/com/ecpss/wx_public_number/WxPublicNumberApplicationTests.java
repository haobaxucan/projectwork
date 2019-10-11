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
    public static final String str = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Test
    public void contextLoads() {
        String s[] = null;
        boolean b = ArrayUtils.isEmpty(s);
        System.out.println(b);
//        Arrays.sort(s);
//        Arrays.stream(s).forEach(System.out::println);
    }

    @Test
    public void contextLoads1() {
        String s="haha%s%s";
        String format = String.format(s, "aaa", 1);
        System.out.println(format);

    }

}
