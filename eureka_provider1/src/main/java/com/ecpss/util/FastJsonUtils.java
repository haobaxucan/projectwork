package com.ecpss.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * Created by xc on 2019/7/10.
 */

public class FastJsonUtils {
    /**
     * string转换成指定的java对象
     */
    public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }
    
    /**
     * 把java对象转换成string
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }
    
    /**
     * 将map转化为string
     */
    public static String mapToString(Map m) {
        return JSONObject.toJSONString(m);
    }
    
    /**
     * json字符串转化为map
     */
    public static Map<String,Object> stringToMap(String s) {
        return JSONObject.parseObject(s);
    }
    
    /**
     * String转成json对象
     */
    public static JSONObject stringToJson(String jsonData) {
        return JSON.parseObject(jsonData);
    }
    
    /**
     * json对象转成String
     */
    public static String jsonToString(JSON jsonData) {
        return JSON.toJSONString(jsonData);
    }
    
    public static void main(String[] args) {
    

    }
    /**
     * java 对象转成json 对象
     */
    public static JSON jsonToObject(Object object) {
        return (JSON) JSON.toJSON(object);
    }
    
    /**
     * json 对象转成 java 对象
     * @param jsonObject
     * @param cls
     * @param <T>
     * @return
     */
    public static <T>T JsonToJava(JSONObject jsonObject,Class<T> cls){
        return JSONObject.toJavaObject(jsonObject,cls);
    }
    
    /**
     * 把JSON数据转换成指定的java对象列表
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }
    
    /**
     * JSON数据转换成较为复杂的List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }
    
    /**
     * String转成Object对象
     */
    public static Object jsonToObject(String s) {
        return JSON.parse(s);
    }
    
}
