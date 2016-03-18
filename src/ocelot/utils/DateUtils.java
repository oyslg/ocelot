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
 * 日期工具类
 * 
 * @author sheng.oys
 * @version $Id: DateUtils.java, v 0.1 2016-3-18 上午2:06:14 sheng.oys Exp $
 */
public class DateUtils {

    /**
     * 日期格式
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
     * 日期格式是否合法(yyyyMMdd)
     * 
     * @param strDate
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != SHORT_DATE_FORMAT.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- 避免日期中输入非数字 ----
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
