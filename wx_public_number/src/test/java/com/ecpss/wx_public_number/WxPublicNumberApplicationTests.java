package com.ecpss.wx_public_number;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.SysexMessage;
import java.util.Arrays;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WxPublicNumberApplicationTests {

    @Test
    public void contextLoads() {
        String s[]={"xxx","aa","ru"};
        Arrays.sort(s);
        Arrays.stream(s).forEach(System.out::println);
    }

}
