/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.model.convert;

import java.util.List;

import ocelot.model.vo.SVNHistoryRecordVO;

import org.tmatesoft.svn.core.SVNLogEntry;

/**
 * SVN历史记录对象转换器
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryRecordVOConvertor.java, v 0.1 2016-3-18 上午8:33:20 sheng.oys Exp $
 */
public final class SVNHistoryRecordVOConvertor {

    /**
     * 私有构造方法
     */
    private SVNHistoryRecordVOConvertor() {

    }

    /**
     * 转换历史记录对象
     * 
     * @param svnlogentry       历史记录
     * @return                  历史记录对象
     */
    public static SVNHistoryRecordVO convert(SVNLogEntry svnlogentry) {
        SVNHistoryRecordVO historyRecordVO = new SVNHistoryRecordVO();

        if (null != svnlogentry) {
            historyRecordVO.setVersion(svnlogentry.getRevision());
            historyRecordVO.setGmtModified(svnlogentry.getDate());
            historyRecordVO.setOperator(svnlogentry.getAuthor());
            historyRecordVO.setComment(svnlogentry.getMessage());
        }

        return historyRecordVO;
    }

    /**
     * 
     * @param historyRecordVOs
     * @return
     */
    public static Object[][] convert(List<SVNHistoryRecordVO> historyRecordVOs) {
        Object[][] dataList = null;

        if (historyRecordVOs != null) {
            dataList = new Object[historyRecordVOs.size()][];
            for (int i = 0; i < historyRecordVOs.size(); i++) {
                SVNHistoryRecordVO historyRecordVO = historyRecordVOs.get(i);
                dataList[i][0] = historyRecordVO.getVersion();
                dataList[i][1] = historyRecordVO.getGmtModified();
                dataList[i][2] = historyRecordVO.getOperator();
                dataList[i][3] = historyRecordVO.getComment();
            }
        }

        return dataList;
    }
}
