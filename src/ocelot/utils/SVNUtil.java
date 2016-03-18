package ocelot.utils;

import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;

/**
 * SVN������
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryUtils.java, v 0.1 2016-3-16 ����12:21:00 sheng.oys Exp $
 */
public final class SVNUtil {

    /**
     * ˽�й��췽��
     */
    private SVNUtil() {

    }

    /**
     * ͨ����ͬЭ���ʼ���汾��
     */
    public static void setupLibrary() {
        // ����ʹ��http://��https��//
        DAVRepositoryFactory.setup();
        //����ʹ��svn��/ /��svn+xxx��/ /
        SVNRepositoryFactoryImpl.setup();
        //����ʹ��file://
        FSRepositoryFactory.setup();
    }
}
