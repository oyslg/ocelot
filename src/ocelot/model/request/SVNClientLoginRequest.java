package ocelot.model.request;

/**
 * SVN��½����
 * 
 * @author sheng.oys
 * @version $Id: SVNClientRequest.java, v 0.1 2016-3-18 ����1:19:11 sheng.oys Exp $
 */
public class SVNClientLoginRequest {

    /** svn��Ŀ¼ */
    private String svnRoot;

    /** �û��� */
    private String userName;

    /** ���� */
    private String password;

    /**
     * Getter method for property <tt>svnRoot</tt>.
     * 
     * @return property value of svnRoot
     */
    public String getSvnRoot() {
        return svnRoot;
    }

    /**
     * Setter method for property <tt>svnRoot</tt>.
     * 
     * @param svnRoot value to be assigned to property svnRoot
     */
    public void setSvnRoot(String svnRoot) {
        this.svnRoot = svnRoot;
    }

    /**
     * Getter method for property <tt>userName</tt>.
     * 
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>userName</tt>.
     * 
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
