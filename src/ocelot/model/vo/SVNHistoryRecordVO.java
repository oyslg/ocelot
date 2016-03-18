package ocelot.model.vo;

import java.util.Date;

/**
 * SVN��ʷ��¼����
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryRecordVO.java, v 0.1 2016-3-17 ����11:59:21 sheng.oys Exp $
 */
public class SVNHistoryRecordVO {

    /** �汾 */
    private long   version;

    /** �޸����� */
    private Date   gmtModified;

    /** ������ */
    private String operator;

    /** ��ע */
    private String comment;

    /**
     * Getter method for property <tt>version</tt>.
     * 
     * @return property value of version
     */
    public long getVersion() {
        return version;
    }

    /**
     * Setter method for property <tt>version</tt>.
     * 
     * @param version value to be assigned to property version
     */
    public void setVersion(long version) {
        this.version = version;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * Getter method for property <tt>operator</tt>.
     * 
     * @return property value of operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Setter method for property <tt>operator</tt>.
     * 
     * @param operator value to be assigned to property operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Getter method for property <tt>comment</tt>.
     * 
     * @return property value of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method for property <tt>comment</tt>.
     * 
     * @param comment value to be assigned to property comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
