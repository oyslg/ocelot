/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package ocelot.test;

import javax.swing.JFrame;

/**
 * 
 * @author sheng.oys
 * @version $Id: test.java, v 0.1 2016-3-18 ÏÂÎç3:07:06 sheng.oys Exp $
 */
public class test {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableFilterDemo newContentPane = new TableFilterDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    //
    //    public static void main(String[] args) {
    //        //Schedule a job for the event-dispatching thread:
    //        //creating and showing this application's GUI.
    //        javax.swing.SwingUtilities.invokeLater(new Runnable() {
    //            @Override
    //            public void run() {
    //                createAndShowGUI();
    //            }
    //        });
    //    }

}
