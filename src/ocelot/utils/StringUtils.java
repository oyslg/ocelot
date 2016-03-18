/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.utils;

/**
 * �ַ�����������
 * 
 * @author sheng.oys
 * @version $Id: StringUtils.java, v 0.1 2016-3-18 ����2:00:31 sheng.oys Exp $
 */
public final class StringUtils {

    /**
     * ˽�й��췽��
     */
    private StringUtils() {

    }

    /**
     * �ж��Ƿ���ַ���
     * 
     * @param str       У���ַ���
     * @return          У����
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
