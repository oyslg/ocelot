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
 * SVN������
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryUtils.java, v 0.1 2016-3-16 ����12:21:00 sheng.oys Exp $
 */
public class SVNTest {

    /** svn��Ŀ¼ */
    private String        svnRoot;

    /** �û��� */
    private String        userName;

    /** ���� */
    private String        password;

    /** SVN�ֿ� */
    private SVNRepository repository;

    /**
     * Ĭ�Ϲ��췽��
     */
    public SVNTest() {

    }

    /**
     * ����SVN�ֿ����Ĺ��캯��
     * 
     * @param repository        SVN�ֿ����
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
     * ͨ����ͬЭ���ʼ���汾��
     */
    private static void setupLibrary() {

        // ����ʹ��http://��https��//
        DAVRepositoryFactory.setup();

        //����ʹ��svn��/ /��svn+xxx��/ /
        SVNRepositoryFactoryImpl.setup();

        //����ʹ��file://
        FSRepositoryFactory.setup();
    }

    /**
     * 
     * @return      ��½���ؽ��
     */
    public boolean login() {

        setupLibrary();
        try {
            //����������
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(this.svnRoot));

            //�����֤
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
                this.userName, this.password);

            //���������֤������
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

    * @return ��ѯ����·���µ���Ŀ�б�

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
