package com.ecpss.wx_public_number.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @version 1.00
 * @date 2019/10/11
 */
public class WXSignUtil {//微信效验签名工具


        public static final String token = "wx4";

        public static boolean checkSignatrue(String signature, String timestamp, String nonce) {
            // 1）将token、timestamp、nonce三个参数进行字典序排序
            String[] strings = new String[] { token, timestamp, nonce };
            Arrays.sort(strings);
            // 2）将三个参数字符串拼接成一个字符串进行sha1加密
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
            }
            // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            String tmpStr = getSha1(sb.toString());
            return tmpStr.equals(signature);
        }

        // Sha1签名
        public static String getSha1(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            try {
                MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
                mdTemp.update(str.getBytes("UTF-8"));

                byte[] md = mdTemp.digest();
                int j = md.length;
                char buf[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    buf[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(buf);
            } catch (Exception e) {
                return null;
            }
        }
    }



