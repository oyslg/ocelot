package ocelot.utils;


/**
 * 参数校验器
 * 
 * @author sheng.oys
 * @version $Id: ParaCheckerer.java, v 0.1 2016-3-18 上午1:41:45 sheng.oys Exp $
 */
public final class ParaCheckUtil {

    /**
     * 私有构造方法
     */
    private ParaCheckUtil() {

    }

    /**
     * 非空校验
     * 
     * @param obj       校验对象
     */
    public static void checkParamNotBlank(Object obj) {
        if (null == obj) {
            throw new RuntimeException("对象不能为空:" + obj);
        }
    }

    public static void checkShortDateFormat(String dateStr) {
        if (StringUtils.isBlank(dateStr) || !DateUtils.isValidShortDateFormat(dateStr)) {
            throw new RuntimeException("日期格式错误" + dateStr);
        }
    }
}
