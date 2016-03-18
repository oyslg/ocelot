/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
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
 * 
 * @author sheng.oys
 * @version $Id: SVNUtilTest.java, v 0.1 2016-3-16 下午10:28:43 sheng.oys Exp $
 */
public class SVNUtilTest {

    private static String        url        = "http://svnhz.alipay.net/svn/assettrans/branches/ANT00892312_20160225_assettrans";
    private static SVNRepository repository = null;

    @BeforeClass
    public static void setupLibrary() {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        } catch (SVNException e) {
            System.out.println(e);
        }
        // 身份验证
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
            "sheng.oys", "Ken.18825194101!");
        repository.setAuthenticationManager(authManager);
    }

    @Test
    public void filterCommitHistoryTest() throws Exception {
        // 过滤条件
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date begin = format.parse("2015-01-01");
        final Date end = format.parse("2016-03-16");
        final String author = "";
        long startRevision = 0;
        long endRevision = -1;//表示最后一个版本
        final List<String> history = new ArrayList<String>();
        //String[] 为过滤的文件路径前缀，为空表示不进行过滤
        repository
            .log(
                ///src/main/java/com/alipay/assettrans/facade/api
                ///src/main/java/com/alipay/assettrans/facade/enums/PledgeEnum.java
                new String[] { "app/common/service/facade/src/main/java/com/alipay/assettrans/facade/enums" },
                startRevision, endRevision, true, false, new ISVNLogEntryHandler() {
                    @Override
                    public void handleLogEntry(SVNLogEntry svnlogentry) throws SVNException {
                        //依据提交时间进行过滤
                        if (svnlogentry.getDate().after(begin) && svnlogentry.getDate().before(end)) {
                            // 依据提交人过滤
                            if (!"".equals(author)) {
                                if (author.equals(svnlogentry.getAuthor())) {
                                    fillResult(svnlogentry);
                                }
                            } else {
                                fillResult(svnlogentry);
                            }
                        }
                    }

                    public void fillResult(SVNLogEntry svnlogentry) {
                        //getChangedPaths为提交的历史记录MAP key为文件名，value为文件详情
                        history.addAll(svnlogentry.getChangedPaths().keySet());
                        System.out.println(svnlogentry.getChangedPaths() + " and "
                                           + svnlogentry.getRevision());
                    }
                });
        for (String path : history) {
            //            System.out.println(path);
        }
    }

    public void testListEntries() {
        try {
            listEntries(repository,
                "app/common/service/facade/src/main/java/com/alipay/assettrans/facade/enums");
        } catch (SVNException e) {
            System.out.println(e);
        }
    }

    public void listEntries(SVNRepository repository, String path) throws SVNException {

        Collection entries = repository.getDir(path, -1, null, (Collection) null);

        Iterator iterator = entries.iterator();

        while (iterator.hasNext()) {

            SVNDirEntry entry = (SVNDirEntry) iterator.next();

            if ("ProductTypeEnum.java".equals(entry.getName()))
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