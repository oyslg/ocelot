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
 * SVN�ͻ��˽ӿ�
 * 
 * @author sheng.oys
 * @version $Id: SVNClientInterface.java, v 0.1 2016-3-18 ����1:20:33 sheng.oys Exp $
 */
public class SVNClient {

    /** SVN�ֿ� */
    private SVNRepository repository = null;

    /**
     * SVN��½
     * 
     * @param request           SVN����
     * @return                  ��½���ؽ��
     */
    public boolean login(SVNClientLoginRequest request) {
        // �ǿ�У��
        ParaCheckUtil.checkParamNotBlank(request);

        // ��ʼ���汾��
        SVNUtil.setupLibrary();
        try {
            //����������
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(request.getSvnRoot()));

            //�����֤
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
                request.getUserName(), request.getPassword());

            //���������֤������
            repository.setAuthenticationManager(authManager);

        } catch (SVNException svne) {
            svne.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * ��ȡ��ʷ��¼
     * 
     * @param fileName              �ļ�����
     * @param operator              ������(ѡ��)
     * @param startDateStr          ��ʼʱ��yyyyMMdd(ѡ��)
     * @param endDateStr            ����ʱ��yyyyMMdd(ѡ��)
     * @return                      ��ʷ��¼���
     * @throws Exception         
     */
    public List<SVNHistoryRecordVO> getHistoryRecords(String fileName, final String operator,
                                                      String startDateStr, String endDateStr)
                                                                                             throws Exception {
        // �ǿ�У��
        ParaCheckUtil.checkParamNotBlank(repository);
        ParaCheckUtil.checkParamNotBlank(startDateStr);
        ParaCheckUtil.checkParamNotBlank(endDateStr);

        final List<SVNHistoryRecordVO> retHistoryRecordVOs = new ArrayList<SVNHistoryRecordVO>();

        final SimpleDateFormat format = new SimpleDateFormat(SHORT_DATE_FORMAT);
        final Date startDate = DateUtils.isValidShortDateFormat(startDateStr) ? format
            .parse(startDateStr) : null;
        final Date endDate = DateUtils.isValidShortDateFormat(endDateStr) ? format
            .parse(endDateStr) : null;

        //TODO:�����ļ�����ȡ�ļ�·��
        String filePath = fileName;

        repository.log(new String[] { filePath }, START_REVISION, END_REVISION, true, false,
            new ISVNLogEntryHandler() {
                @Override
                public void handleLogEntry(SVNLogEntry svnlogentry) throws SVNException {

                    //�����ύʱ����й���
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
