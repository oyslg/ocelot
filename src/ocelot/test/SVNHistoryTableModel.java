package ocelot.test;

import javax.swing.table.DefaultTableModel;

/**
 * SVN历史记录表格样式
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryTableModel.java, v 0.1 2016-3-18 上午1:06:51 sheng.oys Exp $
 */
public class SVNHistoryTableModel extends DefaultTableModel {

    /** serialVersionUID */
    private static final long     serialVersionUID = 8059717661046585103L;

    /** 列名 */
    private final static String[] colNames         = { "文件", "版本", "修改日期", "操作者", "备注" };

    /**
     * 覆盖父类的构造方法
     * 
     * @param data              数据
     * @param columnNames       列名
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
