/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.actions.WorkbenchRunnableAdapter;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IThreadListener;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.PerformChangeOperation;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.internal.ui.refactoring.ChangeExceptionHandler;
import org.eclipse.ltk.ui.refactoring.RefactoringUI;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Shell;


/**
 * A helper class to execute a refactoring. The class takes care of pushing the
 * undo change onto the undo stack and folding editor edits into one editor
 * undo object.
 */
public class RefactoringExecutionHelper {

	private final Refactoring fRefactoring;
	private final Shell fParent;
	private final IRunnableContext fExecContext;
	private final int fStopSeverity;
	private final boolean fNeedsSavedEditors;

	private class Operation implements IWorkspaceRunnable {
		public Change fChange;
		public PerformChangeOperation fPerformChangeOperation;
		public void run(IProgressMonitor pm) throws CoreException {
			try {
				pm.beginTask("", 11); //$NON-NLS-1$
				pm.subTask(""); //$NON-NLS-1$
				RefactoringStatus status= fRefactoring.checkAllConditions(new SubProgressMonitor(pm, 4, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
				if (status.getSeverity() >= fStopSeverity) {
					Dialog dialog= RefactoringUI.createRefactoringStatusDialog(status, fParent, fRefactoring.getName(), false);
					if(dialog.open() == IDialogConstants.CANCEL_ID) {
						throw new OperationCanceledException();
					}
				}
				fChange= fRefactoring.createChange(new SubProgressMonitor(pm, 2, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
				fChange.initializeValidationData(new SubProgressMonitor(pm, 1, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
				fPerformChangeOperation= RefactoringUI.createUIAwareChangeOperation(fChange);
				fPerformChangeOperation.setUndoManager(RefactoringCore.getUndoManager(), fRefactoring.getName());
				fPerformChangeOperation.run(new SubProgressMonitor(pm, 4, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
			} finally {
				pm.done();
			}
		}
	}

	public RefactoringExecutionHelper(Refactoring refactoring, int stopSevertity, boolean needsSavedEditors, Shell parent, IRunnableContext context) {
		super();
		fRefactoring= refactoring;
		fStopSeverity= stopSevertity;
		fParent= parent;
		fExecContext= context;
		fNeedsSavedEditors= needsSavedEditors;
	}

	public void perform(boolean cancelable) throws InterruptedException, InvocationTargetException {
		final IJobManager manager=  Job.getJobManager();
		final IWorkspaceRoot rule= ResourcesPlugin.getWorkspace().getRoot();
		class OperationRunner extends WorkbenchRunnableAdapter implements IThreadListener {
			public OperationRunner(IWorkspaceRunnable runnable, ISchedulingRule schedulingRule) {
				super(runnable, schedulingRule);
			}
			public void threadChange(Thread thread) {
				manager.transferRule(getSchedulingRule(), thread);
			}
		}
		try {
			try {
				Runnable r= new Runnable() {
					public void run() {
						manager.beginRule(rule, null);
					}
				};
				BusyIndicator.showWhile(fParent.getDisplay(), r);
			} catch (OperationCanceledException e) {
				throw new InterruptedException(e.getMessage());
			}

			RefactoringSaveHelper saveHelper= new RefactoringSaveHelper();
			if (fNeedsSavedEditors && !saveHelper.saveEditors(fParent))
				throw new InterruptedException();
			Operation op= new Operation();
			fRefactoring.setValidationContext(fParent);
			try{
				fExecContext.run(false, cancelable, new OperationRunner(op, rule));
				RefactoringStatus validationStatus= op.fPerformChangeOperation.getValidationStatus();
				if (validationStatus != null && validationStatus.hasFatalError()) {
					MessageDialog.openError(fParent, fRefactoring.getName(),
						Messages.format(
							RefactoringMessages.RefactoringExecutionHelper_cannot_execute,
							validationStatus.getMessageMatchingSeverity(RefactoringStatus.FATAL)));
					return;
				}
			} catch (InvocationTargetException e) {
				PerformChangeOperation pco= op.fPerformChangeOperation;
				if (pco != null && pco.changeExecutionFailed()) {
					ChangeExceptionHandler handler= new ChangeExceptionHandler(fParent, fRefactoring);
					Throwable inner= e.getTargetException();
					if (inner instanceof RuntimeException) {
						handler.handle(pco.getChange(), (RuntimeException)inner);
					} else if (inner instanceof CoreException) {
						handler.handle(pco.getChange(), (CoreException)inner);
					} else {
						throw e;
					}
				} else {
					throw e;
				}
			}catch (OperationCanceledException e) {
				throw new InterruptedException(e.getMessage());
			} finally {
				saveHelper.triggerBuild();
			}
		} finally {
			manager.endRule(rule);
			fRefactoring.setValidationContext(null);
		}
	}
}
