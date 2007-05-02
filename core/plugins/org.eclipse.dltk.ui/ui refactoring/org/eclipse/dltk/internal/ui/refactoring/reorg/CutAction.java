/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringAvailabilityTester;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringExecutionStarter;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgUtils;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.actions.SelectionDispatchAction;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchSite;


public class CutAction extends SelectionDispatchAction{

	private CopyToClipboardAction fCopyToClipboardAction;

	public CutAction(IWorkbenchSite site, Clipboard clipboard, SelectionDispatchAction pasteAction) {
		super(site);
		setText(ReorgMessages.CutAction_text); 
		fCopyToClipboardAction= new CopyToClipboardAction(site, clipboard, pasteAction);

		ISharedImages workbenchImages= DLTKUIPlugin.getDefault().getWorkbench().getSharedImages();
		setDisabledImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		setImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setHoverImageDescriptor(workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));

//		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IScriptHelpContextIds.CUT_ACTION);
	}

	public void selectionChanged(IStructuredSelection selection) {
		if (!selection.isEmpty()) {
			try {
				// cannot cut top-level types. this deletes the cu and then you cannot paste because the cu is gone.
				if (!containsOnlyElementsInsideSourceModules(selection) || containsTopLevelTypes(selection)) {
					setEnabled(false);
					return;
				}
				fCopyToClipboardAction.selectionChanged(selection);
				setEnabled(fCopyToClipboardAction.isEnabled() && RefactoringAvailabilityTester.isDeleteAvailable(selection));
			} catch (CoreException e) {
				// no ui here - this happens on selection changes
				// http://bugs.eclipse.org/bugs/show_bug.cgi?id=19253
				if (DLTKModelUtil.isExceptionToBeLogged(e))
					DLTKUIPlugin.log(e);
				setEnabled(false);
			}
		} else
			setEnabled(false);
	}

	private static boolean containsOnlyElementsInsideSourceModules(IStructuredSelection selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object object= iter.next();
			if (! (object instanceof IModelElement && ReorgUtils.isInsideSourceModule((IModelElement)object)))
				return false;
		}
		return true;
	}

	private static boolean containsTopLevelTypes(IStructuredSelection selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object each= iter.next();
			if ((each instanceof IType) && ((IType)each).getDeclaringType() == null)
				return true;
		}
		return false;
	}

	public void run(IStructuredSelection selection) {
		try {
			selectionChanged(selection);
			if (isEnabled()) {
				fCopyToClipboardAction.run(selection);
				RefactoringExecutionStarter.startCutRefactoring(selection.toArray(), getShell());
			}
		} catch (CoreException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		} catch (InterruptedException e) {
			//OK
		} catch (InvocationTargetException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		}
	}
}
