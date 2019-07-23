package com.ecpss;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.spring.domain.WeatherResponse;
import com.ecpss.test.User;
import com.ecpss.util.excelutil.ExcelUtil;
import com.ecpss.util.utiltest.CollectionUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xc on 2019/7/10.
 */

public class Default {
    @Test
    public void testResponse(){
    String s="{\"data\":{\"yesterday\":{\"date\":\"9日星期二\",\"high\":\"高温 28℃\",\"fx\":\"东风\",\"low\":\"低温 21℃\",\"fl\":\"<![CDATA[<3级]]>\",\"type\":\"雷阵雨\"},\"city\":\"北京\",\"forecast\":[{\"date\":\"10日星期三\",\"high\":\"高温 28℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 19℃\",\"fengxiang\":\"东风\",\"type\":\"雷阵雨\"},{\"date\":\"11日星期四\",\"high\":\"高温 29℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 20℃\",\"fengxiang\":\"南风\",\"type\":\"多云\"},{\"date\":\"12日星期五\",\"high\":\"高温 32℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 22℃\",\"fengxiang\":\"西南风\",\"type\":\"晴\"},{\"date\":\"13日星期六\",\"high\":\"高温 32℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 22℃\",\"fengxiang\":\"北风\",\"type\":\"多云\"},{\"date\":\"14日星期天\",\"high\":\"高温 33℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 23℃\",\"fengxiang\":\"西南风\",\"type\":\"晴\"}],\"ganmao\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"wendu\":\"28\"},\"status\":1000,\"desc\":\"OK\"}";
        WeatherResponse weatherResponse = JSONObject.parseObject(s, WeatherResponse.class);
        System.out.println(weatherResponse.getData().getCity());
    }
    
    /**
     * https://yq.aliyun.com/articles/109818
     * @throws Exception
     */
    @Test
    public void testFileUpLoad() throws Exception{
        
        String path="C:\\Users\\Raytine\\Desktop\\xc.xlsx";
        InputStream inputStream=new FileInputStream(new File(path));
        OutputStream out=new FileOutputStream(new File("a.txt"));
        byte b[]=new byte[1024000];
        int len=0;
       
        
        String keyValue="姓名:name,密码:password,地址:address,年龄:age";//全部参数匹配
        //String keyValue="姓名:name,密码:password,地址:address";//部分参数匹配 readXlsPart
        List<User> users = ExcelUtil.readXls(b, ExcelUtil.getMap(keyValue), "com.ecpss.test.User");
        users.forEach(System.out::print);

    }
    
    @Test
    public void testFileExport()throws Exception{
        List list=new ArrayList();
        System.out.println("---"+CollectionUtils.isEmpty(list)+"--");
        
       
    }
    @Test
    public void test1(){
    
    }

}
