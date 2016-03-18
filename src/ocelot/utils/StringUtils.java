/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.utils;

/**
 * 字符串处理工具类
 * 
 * @author sheng.oys
 * @version $Id: StringUtils.java, v 0.1 2016-3-18 上午2:00:31 sheng.oys Exp $
 */
public final class StringUtils {

    /**
     * 私有构造方法
     */
    private StringUtils() {

    }

    /**
     * 判断是否空字符串
     * 
     * @param str       校验字符串
     * @return          校验结果
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
