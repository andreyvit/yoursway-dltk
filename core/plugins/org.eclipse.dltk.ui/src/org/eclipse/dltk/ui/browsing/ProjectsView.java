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
package org.eclipse.dltk.ui.browsing;

import java.util.Iterator;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptModel;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.actions.ProjectActionGroup;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.viewsupport.FilterUpdater;
import org.eclipse.dltk.ui.viewsupport.ProblemTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

public class ProjectsView extends ScriptBrowsingPart {

	private FilterUpdater fFilterUpdater;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#createViewer(org.eclipse.swt.widgets.Composite)
	 */
	protected StructuredViewer createViewer(Composite parent) {
		ProblemTreeViewer result = new ProblemTreeViewer(parent, SWT.MULTI);
		// ColoredViewersManager.install(result);
		fFilterUpdater = new FilterUpdater(result);
		ResourcesPlugin.getWorkspace()
				.addResourceChangeListener(fFilterUpdater);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#dispose()
	 */
	public void dispose() {
		if (fFilterUpdater != null)
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(
					fFilterUpdater);
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
		// if (key == IShowInTargetList.class) {
		// return new IShowInTargetList() {
		// public String[] getShowInTargetIds() {
		// return new String[] { JavaUI.ID_PACKAGES, IPageLayout.ID_RES_NAV };
		// }
		//
		// };
		// }
		return super.getAdapter(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#createContentProvider()
	 */
	protected IContentProvider createContentProvider() {
		return new ProjectAndSourceFolderContentProvider(this, getToolkit());
	}

	/**
	 * Returns the context ID for the Help system.
	 * 
	 * @return the string used as ID for the Help context
	 */
	protected String getHelpContextId() {
		// return IJavaHelpContextIds.PROJECTS_VIEW;
		return ""; //$NON-NLS-1$
	}

	protected String getLinkToEditorKey() {
		return PreferenceConstants.LINK_BROWSING_PROJECTS_TO_EDITOR;
	}

	/**
	 * Adds additional listeners to this view.
	 */
	protected void hookViewerListeners() {
		super.hookViewerListeners();
		getViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				TreeViewer viewer = (TreeViewer) getViewer();
				Object element = ((IStructuredSelection) event.getSelection())
						.getFirstElement();
				if (viewer.isExpandable(element))
					viewer.setExpandedState(element, !viewer
							.getExpandedState(element));
			}
		});
	}

	protected void setInitialInput() {
		IModelElement root = DLTKCore.create(DLTKUIPlugin.getWorkspace()
				.getRoot());
		getViewer().setInput(root);
		updateTitle();
	}

	/**
	 * Answers if the given <code>element</code> is a valid input for this
	 * part.
	 * 
	 * @param element
	 *            the object to test
	 * @return <true> if the given element is a valid input
	 */
	protected boolean isValidInput(Object element) {
		return element instanceof IScriptModel;
	}

	/**
	 * Answers if the given <code>element</code> is a valid element for this
	 * part.
	 * 
	 * @param element
	 *            the object to test
	 * @return <true> if the given element is a valid element
	 */
	protected boolean isValidElement(Object element) {
		if (!(element instanceof IScriptProject || element instanceof IProjectFragment)) {
			return false;
		}
		IDLTKLanguageToolkit languageToolkit;
		try {
			languageToolkit = DLTKLanguageManager
					.getLanguageToolkit((IModelElement) element);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return false;
		}
		return languageToolkit.getNatureId().equals(getToolkit().getNatureId());
	}

	/**
	 * Finds the element which has to be selected in this part.
	 * 
	 * @param je
	 *            the Java element which has the focus
	 * @return the element to select
	 */
	protected IModelElement findElementToSelect(IModelElement je) {
		if (je == null)
			return null;

		switch (je.getElementType()) {
		case IModelElement.SCRIPT_MODEL:
			return null;
		case IModelElement.SCRIPT_PROJECT:
			return je;
		case IModelElement.PROJECT_FRAGMENT:
			if (je.getElementName().equals(
					IProjectFragment.DEFAULT_PACKAGE_ROOT))
				return je.getParent();
			else
				return je;
		default:
			return findElementToSelect(je.getParent());
		}
	}

	/*
	 * @see JavaBrowsingPart#setInput(Object)
	 */
	protected void setInput(Object input) {
		// Don't allow to clear input for this view
		if (input != null)
			super.setInput(input);
		else
			getViewer().setSelection(null);
	}

	protected void createActions() {
		super.createActions();
		fActionGroups.addGroup(new ProjectActionGroup(this));
	}

	/**
	 * Handles selection of LogicalPackage in Packages view.
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 * @since 2.1
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (!needsToProcessSelectionChanged(part, selection))
			return;

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			Iterator iter = sel.iterator();
			while (iter.hasNext()) {
				Object selectedElement = iter.next();
				if (selectedElement instanceof LogicalPackage) {
					selection = new StructuredSelection(
							((LogicalPackage) selectedElement)
									.getScriptProject());
					break;
				}
			}
		}
		super.selectionChanged(part, selection);
	}
}
