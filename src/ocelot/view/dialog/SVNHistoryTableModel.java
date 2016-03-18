package ocelot.view.dialog;

import javax.swing.table.AbstractTableModel;

/**
 * SVN历史记录表格样式
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryTableModel.java, v 0.1 2016-3-18 上午1:06:51 sheng.oys Exp $
 */
public class SVNHistoryTableModel extends AbstractTableModel {

    /** serialVersionUID */
    private static final long     serialVersionUID = 8059717661046585103L;

    /** 列名 */
    private final static String[] colNames         = { "文件", "版本", "修改日期", "操作者", "备注" };

    /** 数据 */
    private final Object[][]      data;

    /**
     * 覆盖父类的构造方法
     * 
     * @param data              数据
     * @param columnNames       列名
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
