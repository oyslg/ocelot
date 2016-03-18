/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.utils;

import javax.swing.JFrame;

import ocelot.view.dialog.SVNHistoryDialog;

/**
 * 
 * @author sheng.oys
 * @version $Id: SVNHistoryDialogStarter.java, v 0.1 2016-3-18 下午3:39:47 sheng.oys Exp $
 */
public class SVNHistoryDialogStarter {

    public static void createAndShowGUI(Object[][] data) {

        JFrame frame = new JFrame("历史记录");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SVNHistoryDialog newContentPane = new SVNHistoryDialog(data);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

}
