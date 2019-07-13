package com.ecpss;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.test.User;
import com.ecpss.util.FastJsonUtils;
import com.ecpss.util.ParseExcelUtil;
import com.ecpss.util.utiltest.FileUtils;
import com.ecpss.util.utiltest.NumberConvertUtil;
import com.ecpss.util.utiltest.xml.ParseXmlUtil;
import com.ecpss.util.utiltest.xml.XStreamUtil;
import com.ecpss.util.utiltest.xml.XmlToJsonUtil;
import com.ecpss.util.utiltest.xml.XmlUtil;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/7/12.
 */
public class FileTest {
    private static final String path="D:\\a\\b\\";
    
    @Test
    public void F1(){
        FileUtils.createNewFile(path+"a.txt");
    }
    
    @Test
    public void F211() throws FileNotFoundException {
        InputStream inputStream=new FileInputStream(path+"a.txt");
        FileUtils.writeFileFromInputStream(inputStream,"d:\\a\\xx.txt");
    }
    
    @Test
    public void F3(){
        String string = FileUtils.readFileToString("d:\\a\\xx.txt");
        System.out.println(string);
    
    }
    
    @Test
    public void F21(){
        String s = FileUtils.readFileByLines("d:\\a\\xx.txt");
        System.out.println(s);
    }
    
    @Test
    public void F51(){
//        FileUtils.copyFiles("D:\\a\\b","D:\\a\\cc");
//        FileUtils.deleteFile("D:\\a\\cc");
        String s = FileUtils.FormetFileSize(1024L);
        System.out.println(s);
    }
    
    @Test
    public void F6(){
        String string = NumberConvertUtil.doubleConvertString(0);
        System.out.println(NumberConvertUtil.intConvertString((int)0.0));
        System.out.println(Double.toString(10.0));
    }
    
    @Test
    public void F55()throws Exception{
    String path="C:\\Users\\Raytine\\Desktop\\众多\\工具类util\\xml_util\\xml格式数据.xml";
        String string = FileUtils.readFileByLines(path);
//         System.out.println(string);
        String s = XmlToJsonUtil.xmlToJson(string);
        
        System.out.println(s);
        JSONObject jsonObject = FastJsonUtils.stringToJson(s);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("transferList");
        System.out.println(jsonObject1.getString("amount"));
        
    }
    @Test
    public void F56()throws Exception{
        String path="C:\\Users\\Raytine\\Desktop\\众多\\工具类util\\xml_util\\xml格式数据.xml";
        String string = FileUtils.readFileToString(path);
//        String s = ParseXmlUtil.xml2Json(string);
        Map<String, Object> map = ParseXmlUtil.xml2map(string);
    
        System.out.println(map);
    }
    @Test
    public void ff()throws Exception{
        JSONObject jsonObject=new JSONObject();
        User user=new User("cc",12,"dd","xx");
        jsonObject.put("user",user);
        jsonObject.put("name","cc");
        String string = JSONObject.toJSONString(jsonObject);
        System.out.println(string);
//        String s = XmlToJsonUtil.js(string);
//        System.out.println(s);
//        System.out.println(XmlToJsonUtil.xmlToJson(s));
        Map map = FastJsonUtils.stringToMap(string);
        System.out.println(map);
//        String s = XmlUtil.mapToXml(map);
//
//        System.out.println(s);
     
        
    }
    
    @Test
    public void testJ(){
        User user=new User("cc",12,"dd","xx");
        JSONObject jsonObject = XStreamUtil.objToJSONStr(user);
        System.out.println(jsonObject);
//        List<String> strings=new ArrayList<>();
//        strings.add("sub1");
//        strings.add("sub1");
//
//        user.setSub(strings);
//        String s = XStreamUtil.pojoToXml(user);
//        System.out.println(s);
    }
    
}
