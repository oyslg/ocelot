package ocelot.svn;

import static ocelot.model.constant.GeneralConstant.END_REVISION;
import static ocelot.model.constant.GeneralConstant.SHORT_DATE_FORMAT;
import static ocelot.model.constant.GeneralConstant.START_REVISION;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ocelot.model.convert.SVNHistoryRecordVOConvertor;
import ocelot.model.request.SVNClientLoginRequest;
import ocelot.model.vo.SVNHistoryRecordVO;
import ocelot.utils.DateUtils;
import ocelot.utils.ParaCheckUtil;
import ocelot.utils.SVNUtil;
import ocelot.utils.StringUtils;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * SVN客户端接口
 * 
 * @author sheng.oys
 * @version $Id: SVNClientInterface.java, v 0.1 2016-3-18 上午1:20:33 sheng.oys Exp $
 */
public class SVNClient {

    /** SVN仓库 */
    private SVNRepository repository = null;

    /**
     * SVN登陆
     * 
     * @param request           SVN请求
     * @return                  登陆返回结果
     */
    public boolean login(SVNClientLoginRequest request) {
        // 非空校验
        ParaCheckUtil.checkParamNotBlank(request);

        // 初始化版本库
        SVNUtil.setupLibrary();
        try {
            //创建库连接
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(request.getSvnRoot()));

            //身份验证
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
                request.getUserName(), request.getPassword());

            //创建身份验证管理器
            repository.setAuthenticationManager(authManager);

        } catch (SVNException svne) {
            svne.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取历史记录
     * 
     * @param fileName              文件名称
     * @param operator              操作者(选填)
     * @param startDateStr          开始时间yyyyMMdd(选填)
     * @param endDateStr            结束时间yyyyMMdd(选填)
     * @return                      历史记录结果
     * @throws Exception         
     */
    public List<SVNHistoryRecordVO> getHistoryRecords(String fileName, final String operator,
                                                      String startDateStr, String endDateStr)
                                                                                             throws Exception {
        // 非空校验
        ParaCheckUtil.checkParamNotBlank(repository);
        ParaCheckUtil.checkParamNotBlank(startDateStr);
        ParaCheckUtil.checkParamNotBlank(endDateStr);

        final List<SVNHistoryRecordVO> retHistoryRecordVOs = new ArrayList<SVNHistoryRecordVO>();

        final SimpleDateFormat format = new SimpleDateFormat(SHORT_DATE_FORMAT);
        final Date startDate = DateUtils.isValidShortDateFormat(startDateStr) ? format
            .parse(startDateStr) : null;
        final Date endDate = DateUtils.isValidShortDateFormat(endDateStr) ? format
            .parse(endDateStr) : null;

        //TODO:根据文件名获取文件路径
        String filePath = fileName;

        repository.log(new String[] { filePath }, START_REVISION, END_REVISION, true, false,
            new ISVNLogEntryHandler() {
                @Override
                public void handleLogEntry(SVNLogEntry svnlogentry) throws SVNException {

                    //依据提交时间进行过滤
                    if (svnlogentry.getDate().after(startDate)
                        && svnlogentry.getDate().before(endDate)) {

                        if (!StringUtils.isBlank(operator)) {
                            if (operator.equals(svnlogentry.getAuthor())) {
                                fillResult(svnlogentry);
                            }
                        } else {
                            fillResult(svnlogentry);
                        }
                    }
                }

                public void fillResult(SVNLogEntry svnlogentry) {
                    if (null != svnlogentry) {
                        retHistoryRecordVOs.add(SVNHistoryRecordVOConvertor.convert(svnlogentry));
                    }
                }
            });

        return retHistoryRecordVOs;
    }

}
