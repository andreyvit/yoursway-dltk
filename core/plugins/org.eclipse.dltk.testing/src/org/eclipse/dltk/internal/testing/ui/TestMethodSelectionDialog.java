/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.testing.ui;


import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.testing.Messages;
import org.eclipse.dltk.ui.ModelElementLabelProvider;


/**
 * A dialog to select a test method.
 */
public class TestMethodSelectionDialog extends ElementListSelectionDialog {

	private IModelElement fElement;

	public static class TestReferenceCollector extends SearchRequestor {
		Set fResult= new HashSet(200);

		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			IModelElement enclosingElement= (IModelElement) match.getElement();
			if (enclosingElement.getElementName().startsWith("test")) //$NON-NLS-1$
				fResult.add(enclosingElement);
		}

		public Object[] getResult() {
			return fResult.toArray();
		}
	}

	public TestMethodSelectionDialog(Shell shell, IModelElement element) {
		super(shell, new ModelElementLabelProvider(ModelElementLabelProvider.SHOW_PARAMETERS | ModelElementLabelProvider.SHOW_POST_QUALIFIED));
		fElement= element;
	}

	/*
	 * @see Windows#configureShell
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(newShell, IDLTKTestingHelpContextIds.TEST_SELECTION_DIALOG);
	}

	/*
	 * @see Window#open()
	 */
	public int open() {
		Object[] elements;
		IType testType= null;

		if (testType == null)
			return CANCEL;

		try {
			elements= searchTestMethods(fElement, testType);
		} catch (InterruptedException e) {
			return CANCEL;
		} catch (InvocationTargetException e) {
			MessageDialog.openError(getParentShell(), DLTKTestingMessages.TestMethodSelectionDialog_error_title, e.getTargetException().getMessage());
			return CANCEL;
		}

		if (elements.length == 0) {
			String msg= Messages.format(DLTKTestingMessages.TestMethodSelectionDialog_notfound_message, fElement.getElementName());
			MessageDialog.openInformation(getParentShell(), DLTKTestingMessages.TestMethodSelectionDialog_no_tests_title, msg);
			return CANCEL;
		}
		setElements(elements);
		return super.open();
	}

	private IType selectTestType(Set result) {
		ILabelProvider labelProvider= new ModelElementLabelProvider(ModelElementLabelProvider.SHOW_PARAMETERS | ModelElementLabelProvider.SHOW_ROOT);
		ElementListSelectionDialog dialog= new ElementListSelectionDialog(getParentShell(), labelProvider);
		dialog.setTitle(DLTKTestingMessages.TestMethodSelectionDialog_dialog_title);
		String msg= Messages.format(DLTKTestingMessages.TestMethodSelectionDialog_testproject, "junit.framework.Test"); //$NON-NLS-1$
		dialog.setMessage(msg);
		IScriptProject[] projects= new IScriptProject[result.size()];
		IType[] testTypes= (IType[]) result.toArray(new IType[result.size()]);
		for (int i= 0; i < projects.length; i++)
			projects[i]= testTypes[i].getScriptProject();
		dialog.setElements(projects);
		if (dialog.open() == Window.CANCEL)
			return null;
		IScriptProject project= (IScriptProject) dialog.getFirstResult();
		for (int i= 0; i < testTypes.length; i++) {
			if (testTypes[i].getScriptProject().equals(project))
				return testTypes[i];
		}
		return null;
	}

	public Object[] searchTestMethods(final IModelElement element, final IType testType) throws InvocationTargetException, InterruptedException {
		final TestReferenceCollector[] col= new TestReferenceCollector[1];

		IRunnableWithProgress runnable= new IRunnableWithProgress() {
			public void run(IProgressMonitor pm) throws InvocationTargetException {
				try {
					col[0]= doSearchTestMethods(element, testType, pm);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		PlatformUI.getWorkbench().getProgressService().busyCursorWhile(runnable);
		return col[0].getResult();
	}

	private TestReferenceCollector doSearchTestMethods(IModelElement element, IType testType, IProgressMonitor pm) throws CoreException {
		int matchRule= SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE | SearchPattern.R_ERASURE_MATCH;
		SearchPattern pattern= SearchPattern.createPattern(element, IDLTKSearchConstants.REFERENCES, matchRule);
		SearchParticipant[] participants= new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() };
		IDLTKSearchScope scope= SearchEngine.createHierarchyScope(testType);
		TestReferenceCollector requestor= new TestReferenceCollector();
		new SearchEngine().search(pattern, participants, scope, requestor, pm);
		return requestor;
	}
}
