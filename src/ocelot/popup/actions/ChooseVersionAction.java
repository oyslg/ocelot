package ocelot.popup.actions;

import ocelot.model.request.SVNClientLoginRequest;
import ocelot.utils.SVNHistoryDialogStarter;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 选择历史版本Action
 * 
 * @author sheng.oys
 * @version $Id: ChooseVersionAction.java, v 0.1 2016-3-18 上午1:35:32 sheng.oys Exp $
 */
public class ChooseVersionAction implements IObjectActionDelegate {

    /**  */
    @SuppressWarnings("unused")
    private Shell      shell;

    /** 选中项 */
    private ISelection selection;

    /**
     * Constructor for ChooseVersionAction.
     */
    public ChooseVersionAction() {
        super();
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        shell = targetPart.getSite().getShell();
    }

    /**
     * @see IActionDelegate#run(IAction)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void run(IAction action) {

        //        SVNClient client = new SVNClient();

        Object[][] data = { { "", "123", "2016.03.17", "sheng.oys", "新增" },
                { "", "124", "2016.03.18", "sheng.oys", "修改" },
                { "", "125", "2016.03.18", "wugu", "提交2" },
                { "", "128", "2016.03.18", "mohan", "提交2" } };

        SVNClientLoginRequest request = new SVNClientLoginRequest();

        //        client.login(request);
        //        List<SVNHistoryRecordVO> historyRecordVOs = null;
        //        try {
        //            historyRecordVOs = client.getHistoryRecords(
        //                "app/common/service/facade/src/main/java/com/alipay/assettrans/facade/enums", null,
        //                "20160101", "20160318");
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }

        SVNHistoryDialogStarter.createAndShowGUI(data);
        //            SVNHistoryRecordVOConvertor.convert(historyRecordVOs));
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }

}
