package ocelot.test;

import java.util.Collection;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * SVN工具类
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryUtils.java, v 0.1 2016-3-16 上午12:21:00 sheng.oys Exp $
 */
public class SVNTest {

    /** svn根目录 */
    private String        svnRoot;

    /** 用户名 */
    private String        userName;

    /** 密码 */
    private String        password;

    /** SVN仓库 */
    private SVNRepository repository;

    /**
     * 默认构造方法
     */
    public SVNTest() {

    }

    /**
     * 带有SVN仓库对象的构造函数
     * 
     * @param repository        SVN仓库对象
     */
    public SVNTest(SVNRepository repository) {
        this.repository = repository;
    }

    public SVNTest(String svnRoot) {
        this.svnRoot = svnRoot;
    }

    public SVNTest(String svnRoot, String userName, String password) {
        this.svnRoot = svnRoot;
        this.userName = userName;
        this.password = password;
    }

    /**
     * 通过不同协议初始化版本库
     */
    private static void setupLibrary() {

        // 对于使用http://和https：//
        DAVRepositoryFactory.setup();

        //对于使用svn：/ /和svn+xxx：/ /
        SVNRepositoryFactoryImpl.setup();

        //对于使用file://
        FSRepositoryFactory.setup();
    }

    /**
     * 
     * @return      登陆返回结果
     */
    public boolean login() {

        setupLibrary();
        try {
            //创建库连接
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(this.svnRoot));

            //身份验证
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
                this.userName, this.password);

            //创建身份验证管理器
            repository.setAuthenticationManager(authManager);

            return true;
        } catch (SVNException svne) {
            svne.printStackTrace();
            return false;
        }
    }

    /***

    *

    * @param path

    * @return 查询给定路径下的条目列表

    * @throws SVNException

    */

    @SuppressWarnings("rawtypes")
    public void listEntries(SVNRepository repository, String path) throws SVNException {

        Collection entries = repository.getDir(path, -1, null, (Collection) null);

        Iterator iterator = entries.iterator();

        while (iterator.hasNext()) {

            SVNDirEntry entry = (SVNDirEntry) iterator.next();

            System.out.println("/" + (path.equals("") ? "" : path + "/") + entry.getName()
                               + " (author: '" + entry.getAuthor() + "'; revision: "
                               + entry.getRevision() + "; date: " + entry.getDate() + ")");
            if (entry.getKind() == SVNNodeKind.DIR) {
                listEntries(repository,
                    (path.equals("")) ? entry.getName() : path + "/" + entry.getName());
            }
        }
    }

}
