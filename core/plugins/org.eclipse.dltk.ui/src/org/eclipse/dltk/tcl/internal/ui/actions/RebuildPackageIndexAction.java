package org.eclipse.dltk.tcl.internal.ui.actions;

import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;



public class RebuildPackageIndexAction implements IObjectActionDelegate {

	private ISelection selection;

	public RebuildPackageIndexAction() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	public void run(IAction action) {
		
		if (selection instanceof IStructuredSelection) {
			for (Iterator it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				if (element instanceof IScriptFolder) {
					IScriptFolder folder = (IScriptFolder)element;
					try {
						IPath path = folder.getCorrespondingResource().getLocation();
						
					} catch (ModelException e) {
						//tell user about problems
					}
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}