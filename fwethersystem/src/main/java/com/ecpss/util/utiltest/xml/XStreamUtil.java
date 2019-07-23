package com.ecpss.util.utiltest.xml;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.test.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.xml.sax.InputSource;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;

/**
 * Created by xc on 2019/7/12.
 * XStream工具类，用于把实体对象转换为XML字符串以及把XML字符串映射为Java实体对象
 */
public class XStreamUtil {
    
    private static XStream xStream = null;

    static{
        if(xStream == null){
            xStream = new XStream(new XppDriver(new NoNameCoder()));
            xStream.aliasSystemAttribute(null,"class");//去掉class属性
            xStream.autodetectAnnotations(true);//自动探测注解
            xStream.ignoreUnknownElements();//忽略未知元素
        }
    }

    /**
     *  把实体对象转换为XML字符串
     * @param pojo
     * @return
     */
    public static String pojoToXml(Object pojo){
        if(null == pojo){
            return "";
        }
        try{
            String top = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
            xStream.alias("yimadai", pojo.getClass());
            String xmlStr = top+xStream.toXML(pojo);
            return xmlStr;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 对象转换成json
     * @param object
     * @return
     */
    public static JSONObject objToJSONStr(Object object){
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver(){
            public HierarchicalStreamWriter createWriter(Writer writer){
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        xstream.processAnnotations(object.getClass());
        return JSONObject.parseObject(xstream.toXML(object));
    }

    /**
     * 把实体对象转换为XML字符串并保存至指定文件中
     * @param obj
     * @param file
     */
    public void pojoToXmlFile(Object obj,File file){
        try{
            String xmlStr =  pojoToXml(obj);
            pojoToXmlFile(xmlStr,file);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    /**
     *  使用同步类 Transformer,将 xml 字符串保存到文件中
     * @param xml
     * @param file
     * @throws Exception
     */
    private  void pojoToXmlFile(String xml,File file) throws Exception{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        
        // 将字符串xml转为Source对象
        InputSource is = new InputSource(new ByteArrayInputStream(xml.getBytes()));
        Source xmlSource = new SAXSource(is);
        // 同步操作
        transformer.transform(xmlSource, new StreamResult(file));
    }
    
    
    /**
     * 从XML字符串反序列化一个对象
     * @param xmlStr
     * @return
     */
    public static  <T> T xmlToObj(String xmlStr,Class<T> cls){
        if(null == xmlStr || xmlStr.length() == 0){
            return null;
        }
        try{
            T object = (T) xStream.fromXML(xmlStr);
            return object;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从XML Reader反序列化一个对象
     * @param reader
     * @return
     */
    public static  <T> T xmlToPojo(Reader reader, Class<T> cls){
        if(null == reader ){
            return null;
        }
        try{
            T object = (T) xStream.fromXML(reader);
            return object;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从一个XML InputStream.中反序列化一个对象
     * @param input
     * @return
     */
    public <T> T xmlToPojo(InputStream input, Class<T> cls){
        if(null == input){
            return null;
        }
        try{
            T object = (T) xStream.fromXML(input);
            return object;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从一个URL中反序列化一个对象
     * @param url
     * @return
     */
    public <T> T  xmlToPojo(URL url, Class<T> cls){
        if(null == url){
            return null;
        }
        try{
            T object =(T)xStream.fromXML(url);
            return object;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 从一个File中反序列化一个对象
     * @param file
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T xmlToPojo(File file, Class<T> cls){
        if(null == file){
            return null;
        }
        try{
            T object = (T)xStream.fromXML(file);
            return object;
        }catch(Exception e ){
            e.printStackTrace();
        }
        return null;
    }
    
}
