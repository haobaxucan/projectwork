package com.ecpss.util.utiltest.xml;

import com.ecpss.test.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by xc on 2019/7/16.
 */
public class JaxbUtil {
    
    /**
     * xml convert java_obj
     * @param xml
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T convertXmlToJava(String xml, Class<T> c) {
        if (xml == null || xml.equals(""))
            return null;
        T t = null;
        StringReader reader = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new StringReader(xml);
            t = (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
        } finally {
            if (reader != null)
                reader.close();
        }
        return t;
    }
    
    public static void main(String[] args) {
        /**
         * 使用时 实体需要 标注 @XmlRootElement注解
         */
        String s1="<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
                "<u>\n" +
                "    <address>cc</address>\n" +
                "    <age>21</age>\n" +
                "    <name>aa</name>\n" +
                "    <password>cc</password>\n" +
                "</u>";
        User user1 = JaxbUtil.convertXmlToJava(s1, User.class);
        System.out.println(user1);
    }
    
    /**
     * obj convert xml
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertObjToXml(Object obj, String encoding) {
        
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return result;
    }

    
   

}


