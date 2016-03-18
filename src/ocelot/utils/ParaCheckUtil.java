package ocelot.utils;


/**
 * ����У����
 * 
 * @author sheng.oys
 * @version $Id: ParaCheckerer.java, v 0.1 2016-3-18 ����1:41:45 sheng.oys Exp $
 */
public final class ParaCheckUtil {

    /**
     * ˽�й��췽��
     */
    private ParaCheckUtil() {

    }

    /**
     * �ǿ�У��
     * 
     * @param obj       У�����
     */
    public static void checkParamNotBlank(Object obj) {
        if (null == obj) {
            throw new RuntimeException("������Ϊ��:" + obj);
        }
    }

    public static void checkShortDateFormat(String dateStr) {
        if (StringUtils.isBlank(dateStr) || !DateUtils.isValidShortDateFormat(dateStr)) {
            throw new RuntimeException("���ڸ�ʽ����" + dateStr);
        }
    }
}
