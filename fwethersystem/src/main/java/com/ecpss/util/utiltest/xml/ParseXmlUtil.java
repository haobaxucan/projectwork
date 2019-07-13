package com.ecpss.util.utiltest.xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecpss.util.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析xml的工具类
 * 1、将多层级xml解析为Map
 * 2、将多层级xml解析为Json
 *
 * @author lmb
 */
@Slf4j
public class ParseXmlUtil {
    
    public static final String TAG = "ParseXmlUtil";
    
    /**
     * 将xml格式响应报文解析为Map格式
     *
     * @param responseXmlTemp
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> xml2map(String responseXmlTemp) throws DocumentException {
        Document doc = DocumentHelper.parseText(responseXmlTemp);
        Element rootElement = doc.getRootElement();
        Map<String, Object> mapXml = new HashMap<>();
        element2Map(mapXml, rootElement);
        return mapXml;
    }
    
    /**
     * 使用递归调用将多层级xml转为map
     *
     * @param map
     * @param rootElement
     */
    private static void element2Map(Map<String, Object> map, Element rootElement) {
        // 获得当前节点的子节点
        List<Element> childElements = rootElement.elements();
        if (childElements.size() > 0) {
            Map<String, Object> tempMap = new HashMap<>();
            for (Element e : childElements) {
                element2Map(tempMap, e);
                map.put(rootElement.getName(), tempMap);
            }
        } else {
            map.put(rootElement.getName(), rootElement.getText());
        }
    }
    
    public static void main(String[] args) throws Exception{
        String s="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<yemadai>\t\n" +
                "\t<tt>0</tt>\n" +
                "\t<signType>RSA</signType>\n" +
                "\t<transferList>\n" +
                "\t\t<transId>test10001</transId>\n" +
                "\t\t<bankCode>工商银行</bankCode>\n" +
                "\t\t<provice>北京市</provice>\t\t\n" +
                "\t\t<city>北京市</city>\t\t\n" +
                "\t\t<branchName>平江路支行</branchName>\n" +
                "\t\t<accountName>王五</accountName>\n" +
                "         <idNo>150624197307108592</idNo>\n" +
                "         <phone>17555551234</phone>\n" +
                "\t\t<cardNo>6222021001067998889</cardNo>\n" +
                "\t\t<amount>10.00</amount>\n" +
                "\t\t<remark>测试转账</remark>\n" +
                "\t\t<secureCode>A13230D0CBFD964621B26984D513D13F</secureCode>\n" +
                "\t</transferList>\n" +
                "</yemadai>\n";
        Map<String, Object> map = ParseXmlUtil.xml2map(s);
        String string = JSONObject.toJSONString(map.get("yemadai"));
        JSONObject jsonObject = JSONObject.parseObject(string);
        String transferList = jsonObject.getString("transferList");
        JSONObject jsonObject1 = JSONObject.parseObject(transferList);
        System.out.println(jsonObject1.getString("bankCode"));//工商银行
    
    }
}
