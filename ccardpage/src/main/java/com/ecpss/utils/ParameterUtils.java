package com.ecpss.utils;

import com.alibaba.fastjson.JSON;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangweichun
 * @describe 参数合法性校验工具类
 * @date 2018/6/1
 */
public class ParameterUtils {
    /**
     * 400或800电话格式验证
     */
    public static Pattern apportionPhone = Pattern.compile("^(((400)|(800))\\-+\\d{7})?$");

    /**
     * 正浮点数
     */

    public static Pattern parameter = Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
    /**
     * 手机号验证
     */
    public static Pattern patternPhone = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$");

    /**
     * 简单手机号验证(11位数字, 1开头)/^1\d{10}$/
     */
    public static Pattern patternPhoneSimple = Pattern.compile("^1\\d{10}$");

    /**
     * 身份证号验证
     */
    // public static Pattern patternCardNo = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");

    public static Pattern patternCardNo = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");

    /**
     * 邮箱验证
     */
      public static Pattern patternEmail = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * 金额验证
     */
    public static Pattern patternMoney = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
    /**
     * 整数验证
     */
    public static Pattern patternInteger = Pattern.compile("^[-\\+]?[\\d]*$");
    /**
     * 网址
     */
    public static Pattern patternUrl = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");

    /**
     * 座机
     */
    public static Pattern patternMoblie = Pattern.compile("(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)");
    /**
     * 用户名: 由字母数字下划线组成且开头必须是字母，不能超过16位
     */
    public static Pattern patternName = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");

    /**
     * 密码：字母和数字构成，不能小于6位超过16位
     */
    public static Pattern patternPass = Pattern.compile("[a-zA-Z0-9]{6,16}");


    public static String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    public static Pattern patternIp = Pattern.compile(ip);

    /**
     * ip格式验证
     */
    public static boolean checkIp(String ip) {
        return patternIp.matcher(ip).matches();
    }

    /**
     * 网址格式验证
     */
    public static boolean checkUrl(String url) {
        return patternUrl.matcher(url).matches();
    }

    /**
     * 座机格式验证
     */
    public static boolean checkMoblie(String moblie) {
        return patternMoblie.matcher(moblie).matches();
    }


    /**
     * 手机号码格式验证
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        if (patternPhone.matcher(phone).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 手机号 座机号 400 800 等验证
     */
    public static boolean checkPhoneMoblie(String moblie) {
        if (checkPhoneSimple(moblie) || patternMoblie.matcher(moblie).matches() || apportionPhone.matcher(moblie).matches()) {
            return true;
        }
        return false;
    }


    /**
     * 手机号码简单的格式验证
     *
     * @param phone
     * @return
     */
    public static boolean checkPhoneSimple(String phone) {
        if (patternPhoneSimple.matcher(phone).matches()) {
            return true;
        }
        return false;
    }


    /**
     * 身份证格式验证
     *
     * @param cardNo
     * @return
     */

    public static boolean checkCardNo(String cardNo) {
        if (patternCardNo.matcher(cardNo).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 正浮点数验证
     */

    public static boolean checkIntDouble(String str) {
        if (parameter.matcher(str).matches()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(apportionPhone.matcher("400-0001256").matches());
        System.out.println(checkPhoneMoblie("400-4567897"));
    }

    /**
     * 邮箱验证
     */

    public static boolean checkEmail(String email) {
        if (patternEmail.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 金额格式验证
     *
     * @param money
     * @return
     */
    public static boolean checkMoney(String money) {
        if (patternMoney.matcher(money).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 整数验证
     */
    public static boolean checkInt(String str) {
        if (patternInteger.matcher(str).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 用户名验证
     */
    public static boolean checkName(String name) {
        return patternName.matcher(name).matches();
    }

    /**
     * 密码验证
     */
    public static boolean checkPass(String pass) {
        return patternPass.matcher(pass).matches();
    }

    /**
     * 结束时间不小于当天日期
     */
    public static boolean checkEndTime(Date endTime) {
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.setTime(new Date());
        c2.setTime(endTime);
        Date now = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        Date et = new Date(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH));

        if (et.after(now)) {
            return true;
        }
        return false;
    }

    /**
     * 校验银行卡卡号
     */
    public static boolean checkBankCard(String bankCard) {
        if (bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
//        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
//        if(bit == 'N'){
//            return false;
//        }
//        return bankCard.charAt(bankCard.length() - 1) == bit;
        return true;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeBankCard
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard) {
        if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static String toErrorMsg(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("value", value);
        return JSON.toJSONString(map);
    }

    /**
     * 验证用户名，支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数
     *
     * @param userName
     * @return
     */
    public static boolean validateUserName(String userName) {
        String validateStr = "^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$";
        boolean rs = matcher(validateStr, userName);
        if (rs) {
            int strLength = getStrLength(userName);
            if (strLength < 4 || strLength > 20) {
                rs = false;
            }
        }
        return rs;
    }

    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     *
     * @param value
     * @return
     */
    private static int getStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    private static boolean matcher(String reg, String string) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        boolean tem = matcher.matches();
        return tem;
    }

}
