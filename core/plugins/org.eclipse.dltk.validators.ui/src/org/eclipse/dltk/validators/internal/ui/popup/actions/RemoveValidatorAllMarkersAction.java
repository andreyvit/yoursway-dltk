package org.eclipse.dltk.validators.internal.ui.popup.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.validators.core.ValidatorRuntime;
import org.eclipse.dltk.validators.internal.core.ValidatorUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class RemoveValidatorAllMarkersAction implements
		IObjectActionDelegate {
	ISelection selection;

	/**
	 * Constructor for Action1.
	 */
	public RemoveValidatorAllMarkersAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		processSelectionToElements(selection);
	}

	private void processSelectionToElements(ISelection selection ) {
		List elements = new ArrayList();
		List resources = new ArrayList();
		if (this.selection != null
				&& this.selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) this.selection;
			Iterator iterator = sel.iterator();
			for (; iterator.hasNext();) {
				Object o = iterator.next();
				ValidatorUtils.processResourcesToElements(o, elements, resources);
			}
		}
		ValidatorRuntime.executeCleanAllValidatorsWithConsole(elements, resources);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
