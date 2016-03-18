/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.utils;

import static ocelot.model.constant.GeneralConstant.SHORT_DATE_FORMAT;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * ���ڹ�����
 * 
 * @author sheng.oys
 * @version $Id: DateUtils.java, v 0.1 2016-3-18 ����2:06:14 sheng.oys Exp $
 */
public class DateUtils {

    /**
     * ���ڸ�ʽ
     * 
     * @param pattern
     * @return
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * ���ڸ�ʽ�Ƿ�Ϸ�(yyyyMMdd)
     * 
     * @param strDate
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != SHORT_DATE_FORMAT.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- ������������������� ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(SHORT_DATE_FORMAT);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
