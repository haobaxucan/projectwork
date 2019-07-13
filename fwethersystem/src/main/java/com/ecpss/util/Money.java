package com.ecpss.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额
 * Created by lindongcheng on 14/10/25.
 */
@SuppressWarnings("serial")
public class Money extends BigDecimal {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    /**
     * string 构造 保留2位小数
     *
     * @param value
     */
    public Money(String value) {
        this(new BigDecimal(value));
    }

    /**
     * 默认保留2位小数
     *
     * @param value
     */
    public Money(BigDecimal value) {
        this(value, null);
    }

    public Money(BigDecimal value, Integer scale) {
        super(getVal(value, scale).toString());
    }

    private static BigDecimal getVal(BigDecimal value, Integer scale) {
        if (scale == null) return value;
        return value.setScale(scale, ROUND_HALF_UP);
    }

    public Money getCent() {
        return new Money(movePointRight(2), 0);
    }

    public String toString(int scale) {
        return setScale(scale, RoundingMode.HALF_UP).toString();
    }

    public Money max(Money other) {
        if (other == null) return this;
        if (this.compareTo(other) > 0)
            return this;
        else
            return other;
    }

    public Money multiply(Money other) {
        return multiply(other,(Integer)null);
    }

    public Money multiply(Money other, Integer scale) {
        Money money = new Money(super.multiply(other));
        if (scale != null) {
            return new Money(money.setScale(scale, ROUND_HALF_UP));
        }
        return money;
    }

    public Money bankMultiply(Money other, Integer scale) {
        Money money = new Money(super.multiply(other),null);
        if (scale != null) {
            return new Money(money,scale);
        }
        return money;
    }

    public static void main(String[] args) {
        Money money = new Money("0.003000");
        Money fee=new Money(new BigDecimal("0.0003"),7);
        System.out.println(fee+" "+money);
        Money txNo=new Money(new BigDecimal("1000"));
        System.out.println(txNo.bankMultiply(fee,6));
        System.out.println(Money.bankRoundoff(new Money(new BigDecimal("0.3351"),4)));
        System.out.println(Money.bankRoundoff(new Money(new BigDecimal("0.3352"),4)));
    }

    public Money divide(Money other) {
        return new Money(super.divide(other));
    }

    public Money addition(Money other){
        return new Money(add(other));
    }

    public Float getFloat() {
        return floatValue();
    }
    
	// 金额验证, 保留2位小数
	public static boolean isMoney(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

    /**
     * 银行家舍入--四舍，六进，五成双（奇进，偶舍）
     * @param money
     * @return
     */
    public static Money bankRoundoff(Money money) {
        Money uMoney2 = new Money(money.setScale(2, BigDecimal.ROUND_DOWN));
        Money uMoney3 = new Money(money.setScale(3, BigDecimal.ROUND_DOWN));
        switch (uMoney3.subtract(uMoney2).compareTo(new Money(BigDecimal.valueOf(0.005)))) {
            case 0:
                BigDecimal a = (uMoney2.getCent().divideAndRemainder(BigDecimal.valueOf(2)))[1];
                uMoney2 = a.compareTo(BigDecimal.ZERO) == 0 ? uMoney2 : new Money(uMoney2.add(new Money(BigDecimal.valueOf(0.01))));
                break;
            case 1:
                uMoney2 = new Money(uMoney2.add(new Money(BigDecimal.valueOf(0.01))));
                break;
        }
        return uMoney2;
    }




}
