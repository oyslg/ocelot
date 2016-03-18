package ocelot.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 功能模块分析Action
 * 
 * @author sheng.oys
 * @version $Id: AnalyseModuleAction.java, v 0.1 2016-3-18 上午1:35:27 sheng.oys Exp $
 */
public class AnalyseModuleAction implements IObjectActionDelegate {

    /**  */
    private Shell shell;

    /**
     * 
     */
    public AnalyseModuleAction() {
        super();
    }

    /* 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        MessageDialog.openInformation(shell, "Ocelot", "分析功能模块 was executed.");
    }

    /* 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

    /* 
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        shell = targetPart.getSite().getShell();
    }

}
