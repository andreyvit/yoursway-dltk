/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.ui.actions;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.debug.core.ScriptDebugManager;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptExceptionBreakpoint;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.breakpoints.BreakpointUtils;
import org.eclipse.dltk.internal.ui.dialogs.TypeSelectionDialog2;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * The workbench menu action for adding an exception breakpoint
 */
public abstract class AddExceptionAction implements IViewActionDelegate, IWorkbenchWindowActionDelegate {
	
	public static final String CAUGHT_CHECKED = "caughtChecked"; //$NON-NLS-1$
	public static final String UNCAUGHT_CHECKED = "uncaughtChecked"; //$NON-NLS-1$
	public static final String DIALOG_SETTINGS = "AddExceptionDialog"; //$NON-NLS-1$	
	private IDLTKUILanguageToolkit fToolkit;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		String natureId = ScriptDebugManager.getInstance().getNatureByDebugModel(getDebugModelId());
		fToolkit = DLTKUILanguageManager.getLanguageToolkit(natureId);
		IDialogSettings settings = getDialogSettings();
		AddExceptionTypeDialogExtension ext = new AddExceptionTypeDialogExtension(null,
				settings.getBoolean(CAUGHT_CHECKED), settings
						.getBoolean(UNCAUGHT_CHECKED));
		
		TypeSelectionDialog2 dialog = new TypeSelectionDialog2(
				DLTKUIPlugin.getActiveWorkbenchShell(), false, 
				PlatformUI.getWorkbench().getProgressService(), 
				SearchEngine.createWorkspaceScope(fToolkit.getCoreToolkit()),
				IDLTKSearchConstants.TYPE, ext, fToolkit);
		
		dialog.setMessage("Search");
		dialog.setTitle("Add exception breakpoint");
		if (dialog.open() == IDialogConstants.OK_ID) {
			Object[] types = dialog.getResult();
			if (types != null && types.length > 0) {
				boolean caught = ext.shouldHandleCaughtExceptions();
				boolean uncaught = ext.shouldHandleUncaughtExceptions();
				Object[] results = dialog.getResult(); 
				if(results != null && results.length > 0) {
					try {
						createBreakpoint(caught, uncaught, (IType)results[0]);
						settings.put(CAUGHT_CHECKED, caught);
						settings.put(UNCAUGHT_CHECKED, uncaught);
					}
					catch (CoreException e) {
						DLTKDebugUIPlugin.errorDialog("Unable to create breakpoint", e.getStatus());
					}
				}

			}
		}
	}
	
	/**
	 * Returns the existing dialog settings for the persisted state of the caught and uncaught check boxes.
	 * If no section exists then a new one is created
	 * 
	 * @return the dialog settings section for the type dialog extension
	 * 
	 * @since 3.4
	 */
	private IDialogSettings getDialogSettings() {
        IDialogSettings allSetttings = DLTKDebugUIPlugin.getDefault().getDialogSettings();
        IDialogSettings section = allSetttings.getSection(DIALOG_SETTINGS);
        if (section == null) {
            section = allSetttings.addNewSection(DIALOG_SETTINGS);
            section.put(CAUGHT_CHECKED, true);
            section.put(UNCAUGHT_CHECKED, true);
        }
        return section;
    }
	
	/**
	 * creates a single breakpoint of the specified type
	 * @param caught if the exception is caught
	 * @param uncaught if the exception is uncaught
	 * @param type the type of the exception
	 * @since 3.2
	 */
	private void createBreakpoint(final boolean caught, final boolean uncaught, final IType type) throws CoreException {
		IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(
						getDebugModelId());
		boolean exists = false;
		for (int j = 0; j < breakpoints.length; j++) {
			IScriptBreakpoint breakpoint = (IScriptBreakpoint) breakpoints[j];
			if (breakpoint instanceof IScriptExceptionBreakpoint) {
				IScriptExceptionBreakpoint exceptBreak = (IScriptExceptionBreakpoint) breakpoint;
				if (exceptBreak.getTypeName().equals(type.getFullyQualifiedName())) {
					exists = true;
					break;
				}
			}
		}
		if (!exists) {
			new Job("Script Toggle Exception Breakpoint") {
				protected IStatus run(IProgressMonitor monitor) {
					try {
						BreakpointUtils.addExceptionBreakpoint(getDebugModelId(), caught, uncaught, type);
						return Status.OK_STATUS;
					} catch (CoreException e) {
						return e.getStatus();
					}
				}

			}.schedule();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
	}
	
	protected abstract String getDebugModelId();
}
