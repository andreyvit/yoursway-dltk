package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.dltk.debug.core.model.MethodEntryManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class MethodExitSuspendAction implements IViewActionDelegate {

	public void init(IViewPart view) {

	}

	public void run(IAction action) {
		MethodEntryManager.setSuspendOnMethodExit(!MethodEntryManager.isSuspendOnMethodExit());
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

}
