package com.ecpss.utils;

import org.junit.Test;

import java.util.*;

/**
 * Created by xc on 2019/6/21.
 */
public class WXPayUtilTest {
    
    @Test
    public void test1(){
  String string="<xml>\n" +
          "\n" +
          "<appid>wxd930ea5d5a258f4f</appid>\n" +
          "\n" +
          "<mch_id>10000100</mch_id>\n" +
          "\n" +
          "<device_info>1000</device_info>\n" +
          "\n" +
          "<body>test</body>\n" +
          "\n" +
          "<nonce_str>ibuaiVcKdpRxkhJA</nonce_str>\n" +
          "\n" +
          "<sign>9A0A8659F005D6984697E2CA0A9CF3B7</sign>\n" +
          "\n" +
          "</xml>";
        try {
            Map<String, String> map = WXPayUtil.xmlToMap(string);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    @Test
    public void te2(){
//        List list=new LinkedList();
//        list.add("ccc");
//        list.add(null);
//        list.add("ccc");
//        list.add("dd");
//        list.add("aae");
//        list.add("aae1");
//        list.add("aae2");
//        list.add("aae3");
//        list.add("aae4");
//        System.out.println(list);
    
    }
    
}