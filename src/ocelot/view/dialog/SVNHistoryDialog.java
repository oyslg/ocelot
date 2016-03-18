package ocelot.view.dialog;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * SVN��ʷ��¼�Ի���
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryDialog.java, v 0.1 2016-3-18 ����12:08:00 sheng.oys Exp $
 */
public class SVNHistoryDialog extends JFrame {

    /** serialVersionUID */
    private static final long serialVersionUID = -8936928469317899496L;

    /** ��ʷ��¼��� */
    private final JTable      historyRecordTable;

    /**
     * SVN��ʷ��¼�Ի����캯��
     * 
     * @param data  ��ʼ������
     */
    public SVNHistoryDialog(Object[][] data) {
        // ���ڳ�ʼ��
        super("�޸ļ�¼");
        setSize(400, 300);

        try {
            // ����ͼ�ν������
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SVN��ʷ��¼�����ʽ
        SVNHistoryTableModel historyTableModel = new SVNHistoryTableModel(data, null);

        // ���������ñ�����  
        historyRecordTable = new JTable();
        historyRecordTable.setModel(historyTableModel);

        // ��������
        Container container = getContentPane();
        container.add(new JScrollPane(historyRecordTable));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
