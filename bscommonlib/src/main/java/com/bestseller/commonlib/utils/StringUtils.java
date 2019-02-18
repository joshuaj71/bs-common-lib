package com.bestseller.commonlib.utils;

import java.net.URLEncoder;

/**
 * @author joshua
 */

public class StringUtils {

    // 判断一个字符是否是中文
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    // 截取字符串左边开始前段部分的中文
    public static String subChinese(String str) {
        StringBuffer sb = new StringBuffer();
        if (str == null)
            return "";
        for (char c : str.toCharArray()) {
            if (isChinese(c))
                sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 验证手机格式
     */
    public static boolean isMobileNumber(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188、198
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "^1(3|4|5|7|8|9)[0-9]\\d{8}$";
        if (number.length() != 11) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }


    public final static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString().trim());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString().trim()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }


    public final static float convertToFloat(Object value, float defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Float.valueOf(value.toString().trim()).floatValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public final static double convertToDouble(Object value, double defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value.toString().trim()).doubleValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public final static String convertToString(Object value) {
        if (value == null || "".equals(value.toString().trim())) {
            return "";
        }
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 如果只是用于程序中的格式化数值然后输出，这个方法方便
     * 使用：System.out.println(String.format("%.2f", d));
     *
     * @param d
     * @return
     */
    public static String formatD2(double d) {
        return String.format("%.2f", d);
//        这个也可以
//        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(d));
    }

    public final static String getUrlEncodePath(String path) {
        try {
            String substring1 = path.substring(0, path.lastIndexOf("/") + 1);
            String substring = path.substring(path.lastIndexOf("/") + 1);
            path = substring1 + URLEncoder.encode(substring, "utf-8");
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String getFileType(String path) {
        try {
            String substring = path.substring(path.lastIndexOf("."));
            return substring;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDislodgeSuffix(String name) {
        try {
            if (name.lastIndexOf(".") != -1) {
                String substring = name.substring(0, name.lastIndexOf("."));
                return substring;
            } else {
                return name;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
