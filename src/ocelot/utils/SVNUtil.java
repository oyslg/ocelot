package ocelot.utils;

import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;

/**
 * SVN工具类
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryUtils.java, v 0.1 2016-3-16 上午12:21:00 sheng.oys Exp $
 */
public final class SVNUtil {

    /**
     * 私有构造方法
     */
    private SVNUtil() {

    }

    /**
     * 通过不同协议初始化版本库
     */
    public static void setupLibrary() {
        // 对于使用http://和https：//
        DAVRepositoryFactory.setup();
        //对于使用svn：/ /和svn+xxx：/ /
        SVNRepositoryFactoryImpl.setup();
        //对于使用file://
        FSRepositoryFactory.setup();
    }
}
