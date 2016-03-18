package ocelot.test;

import javax.swing.table.DefaultTableModel;

/**
 * SVN��ʷ��¼�����ʽ
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryTableModel.java, v 0.1 2016-3-18 ����1:06:51 sheng.oys Exp $
 */
public class SVNHistoryTableModel extends DefaultTableModel {

    /** serialVersionUID */
    private static final long     serialVersionUID = 8059717661046585103L;

    /** ���� */
    private final static String[] colNames         = { "�ļ�", "�汾", "�޸�����", "������", "��ע" };

    /**
     * ���Ǹ���Ĺ��췽��
     * 
     * @param data              ����
     * @param columnNames       ����
     */
    public SVNHistoryTableModel(Object[][] data, Object[] columnNames) {
        super(data, null == columnNames ? colNames : columnNames);
    }

    /** 
     * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
