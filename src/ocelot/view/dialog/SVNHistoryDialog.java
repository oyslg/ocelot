package ocelot.view.dialog;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

/**
 * SVN历史记录对话框
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryDialog.java, v 0.1 2016-3-18 上午12:08:00 sheng.oys Exp $
 */
public class SVNHistoryDialog extends JPanel {

    /** serialVersionUID */
    private static final long                          serialVersionUID = -8936928469317899496L;

    /** 历史记录表格 */
    private final JTable                               historyRecordTable;

    /** 过滤文本 */
    private final JTextField                           filterText;

    /** 排序器 */
    private final TableRowSorter<SVNHistoryTableModel> sorter;

    /**
     * SVN历史记录对话框构造函数
     * 
     * @param data  初始化参数
     */
    public SVNHistoryDialog(Object[][] data) {
        // 窗口初始化
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(400, 300);

        SVNHistoryTableModel model = new SVNHistoryTableModel(data);

        sorter = new TableRowSorter<SVNHistoryTableModel>(model);

        historyRecordTable = new JTable(model);
        historyRecordTable.setRowSorter(sorter);
        historyRecordTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        historyRecordTable.setFillsViewportHeight(true);

        historyRecordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(historyRecordTable);
        add(scrollPane);
        JPanel form = new JPanel(new SpringLayout());
        JLabel label = new JLabel("提交者过滤:", SwingConstants.TRAILING);
        form.add(label);
        filterText = new JTextField();

        filterText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });
        label.setLabelFor(filterText);
        form.add(filterText);
        makeCompactGrid(form, 1, 2, 6, 6, 6, 6);
        add(form);

    }

    /**
     * 
     */
    private void newFilter() {
        RowFilter<SVNHistoryTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 3);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    /**
     * 
     * @param parent
     * @param rows
     * @param cols
     * @param initialX
     * @param initialY
     * @param xPad
     * @param yPad
     */
    public static void makeCompactGrid(Container parent, int rows, int cols, int initialX,
                                       int initialY, int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout) parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }

        // Align all cells in each column and make them the same width.
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width, getConstraintsForCell(r, c, parent, cols).getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        // Align all cells in each row and make them the same height.
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height, getConstraintsForCell(r, c, parent, cols).getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        // Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }

    private static SpringLayout.Constraints getConstraintsForCell(int row, int col,
                                                                  Container parent, int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }
}
