package org.eclipse.dltk.internal.debug.ui.launcher;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferencePage;


public abstract class AbstractNoInterpreterStatusHandler implements IStatusHandler
{
    /*
     * @see org.eclipse.debug.core.IStatusHandler#handleStatus(org.eclipse.core.runtime.IStatus, java.lang.Object)
     */
    public Object handleStatus(IStatus status, Object source)
    {
        final boolean[] result = new boolean[1];
        DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    String title = LaunchingMessages.NoDefaultInterpreterStatusHandler_title;
                    String message =
                        LaunchingMessages.NoDefaultInterpreterStatusHandler_message;
                    result[0] =
                        (MessageDialog.openQuestion(DLTKDebugUIPlugin.getActiveWorkbenchShell(),
                                title, message));
                    if (result[0])
                    {
                        // show preference page
                        showInterpreterPreferencePage();
                    }
                }
            });
        return new Boolean(result[0]);
    }

    /**
     * shows the interpreter preference page
     *
     * <p>sub-classes should create their intepreter specific preference page and then make
     * a call to @link {@link #showPrefPage(String, IPreferencePage)} to render the page.</p>
     */
    protected abstract void showInterpreterPreferencePage();

    /**
     * cause the reference page to appear
     *
     * @param id preference page id
     * @param page preference page
     */
    protected final void showPrefPage(String id, IPreferencePage page)
    {
        DLTKDebugUIPlugin.showPreferencePage(id, page);
    }
}
