package ocelot.view.dialog;

import javax.swing.table.AbstractTableModel;

/**
 * SVN��ʷ��¼�����ʽ
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryTableModel.java, v 0.1 2016-3-18 ����1:06:51 sheng.oys Exp $
 */
public class SVNHistoryTableModel extends AbstractTableModel {

    /** serialVersionUID */
    private static final long     serialVersionUID = 8059717661046585103L;

    /** ���� */
    private final static String[] colNames         = { "�ļ�", "�汾", "�޸�����", "������", "��ע" };

    /** ���� */
    private final Object[][]      data;

    /**
     * ���Ǹ���Ĺ��췽��
     * 
     * @param data              ����
     * @param columnNames       ����
     */
    public SVNHistoryTableModel(Object[][] data) {
        super();
        this.data = data;
    }

    /** 
     * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /** 
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /** 
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /** 
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    /** 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /** 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
