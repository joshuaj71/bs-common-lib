package com.bestseller.commonlib.utils;

import java.math.BigDecimal;

/**
 * Author:Joshua
 * Date:2019/3/7
 * Description:
 */
public class MathUtil {
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 39      * 提供精确的除法运算方法div
     * 40      * @param value1 被除数
     * 41      * @param value2 除数
     * 42      * @param scale 精确范围
     * 43      * @return 两个参数的商
     * 44      * @throws IllegalAccessException
     * 45
     */
    public static double div(double v1, double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, 2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
