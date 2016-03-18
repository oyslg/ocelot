package ocelot.view.dialog;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * SVN历史记录对话框
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryDialog.java, v 0.1 2016-3-18 上午12:08:00 sheng.oys Exp $
 */
public class SVNHistoryDialog extends JFrame {

    /** serialVersionUID */
    private static final long serialVersionUID = -8936928469317899496L;

    /** 历史记录表格 */
    private final JTable      historyRecordTable;

    /**
     * SVN历史记录对话框构造函数
     * 
     * @param data  初始化参数
     */
    public SVNHistoryDialog(Object[][] data) {
        // 窗口初始化
        super("修改记录");
        setSize(400, 300);

        try {
            // 设置图形界面外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SVN历史记录表格样式
        SVNHistoryTableModel historyTableModel = new SVNHistoryTableModel(data, null);

        // 创建并设置表格对象  
        historyRecordTable = new JTable();
        historyRecordTable.setModel(historyTableModel);

        // 构造容器
        Container container = getContentPane();
        container.add(new JScrollPane(historyRecordTable));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
