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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.internal.ui.filters.NonScriptElementFilter;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.actions.SelectAllAction;
import org.eclipse.dltk.ui.viewsupport.AppearanceAwareLabelProvider;
import org.eclipse.dltk.ui.viewsupport.DecoratingModelLabelProvider;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

public class TypesView extends ScriptBrowsingPart {

	private SelectAllAction fSelectAllAction;

	/**
	 * Creates and returns the label provider for this part.
	 * 
	 * @return the label provider
	 * @see org.eclipse.jface.viewers.ILabelProvider
	 */
	protected ScriptUILabelProvider createLabelProvider() {
		return new AppearanceAwareLabelProvider(
				AppearanceAwareLabelProvider.DEFAULT_TEXTFLAGS
						| ScriptElementLabels.T_CATEGORY,
				AppearanceAwareLabelProvider.DEFAULT_IMAGEFLAGS, DLTKUIPlugin
						.getDefault().getPreferenceStore());
	}

	protected StructuredViewer createViewer(Composite parent) {
		StructuredViewer viewer = super.createViewer(parent);
		// ColoredViewersManager.install(viewer);
		return viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class key) {
//		if (key == IShowInTargetList.class) {
//			return new IShowInTargetList() {
//				public String[] getShowInTargetIds() {
//					return new String[] { JavaUI.ID_PACKAGES,
//							IPageLayout.ID_RES_NAV };
//				}
//
//			};
//		}
		return super.getAdapter(key);
	}

	/**
	 * Adds filters the viewer of this part.
	 */
	protected void addFilters() {
		super.addFilters();
		getViewer().addFilter(new NonScriptElementFilter());
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
		return element instanceof IScriptFolder;
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
		if (element instanceof ISourceModule)
			return super.isValidElement(((ISourceModule) element)
					.getParent());
		else if (element instanceof IType) {
			IType type = (IType) element;
			return type.getDeclaringType() == null
					&& isValidElement(type.getSourceModule());
		}
		return false;
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
		case IModelElement.TYPE:
			IType type = ((IType) je).getDeclaringType();
			if (type == null)
				type = (IType) je;
			return type;
		case IModelElement.SOURCE_MODULE:
//			return getTypeForCU((ISourceModule) je);
			return je;
		case IModelElement.PACKAGE_DECLARATION:
			return findElementToSelect(je.getParent());
		default:
			if (je instanceof IMember)
				return findElementToSelect(((IMember) je).getDeclaringType());
			return null;

		}
	}

	/**
	 * Returns the context ID for the Help system
	 * 
	 * @return the string used as ID for the Help context
	 */
	protected String getHelpContextId() {
//		return IJavaHelpContextIds.TYPES_VIEW;
		return ""; //$NON-NLS-1$
	}

	protected String getLinkToEditorKey() {
		return PreferenceConstants.LINK_BROWSING_TYPES_TO_EDITOR;
	}

	protected void createActions() {
		super.createActions();
		fSelectAllAction = new SelectAllAction((TableViewer) getViewer());
	}

	protected void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);

		// Add selectAll action handlers.
		actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(),
				fSelectAllAction);
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
			Object selectedElement = sel.getFirstElement();
			if (sel.size() == 1 && (selectedElement instanceof LogicalPackage)) {
				IScriptFolder[] fragments = ((LogicalPackage) selectedElement)
						.getScriptFolders();
				List selectedElements = Arrays.asList(fragments);
				if (selectedElements.size() > 1) {
					adjustInput(part, selectedElements);
					fPreviousSelectedElement = selectedElements;
					fPreviousSelectionProvider = part;
				} else if (selectedElements.size() == 1)
					super.selectionChanged(part, new StructuredSelection(
							selectedElements.get(0)));
				else
					Assert.isLegal(false);
				return;
			}
		}
		super.selectionChanged(part, selection);
	}

	private void adjustInput(IWorkbenchPart part, List selectedElements) {
		Object currentInput = getViewer().getInput();
		if (!selectedElements.equals(currentInput))
			setInput(selectedElements);
	}

	/*
	 * @see org.eclipse.jdt.internal.ui.browsing.JavaBrowsingPart#createDecoratingLabelProvider(org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider)
	 */
	protected DecoratingModelLabelProvider createDecoratingLabelProvider(
			ScriptUILabelProvider provider) {
		DecoratingModelLabelProvider decoratingLabelProvider = super
				.createDecoratingLabelProvider(provider);
		provider
				.addLabelDecorator(new TopLevelTypeProblemsLabelDecorator(null));
		return decoratingLabelProvider;
	}

}
