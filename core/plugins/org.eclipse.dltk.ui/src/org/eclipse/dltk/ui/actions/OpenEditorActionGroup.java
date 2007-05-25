/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.actions.ActionMessages;
import org.eclipse.dltk.internal.ui.actions.OpenModelElementWithMenu;
import org.eclipse.dltk.internal.ui.actions.OpenStorageWithMenu;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.IContextMenuConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.actions.OpenWithMenu;

/**
 * Action group that adds the actions opening a new editor to the context menu
 * and the action bar's navigate menu.
 * 
 */
public class OpenEditorActionGroup extends ActionGroup {
	private IWorkbenchSite fSite;
	private boolean fIsEditorOwner;
	private OpenAction fOpen;

	/**
	 * Creates a new <code>OpenActionGroup</code>. The group requires that
	 * the selection provided by the part's selection provider is of type <code>
	 * org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param part
	 *            the view part that owns this action group
	 */
	public OpenEditorActionGroup(IViewPart part) {
		fSite = part.getSite();
		fOpen = new OpenAction(fSite);
		fOpen.setActionDefinitionId(IDLTKEditorActionDefinitionIds.OPEN_EDITOR);
		initialize(fSite.getSelectionProvider());
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call
	 * this constructor.
	 * 
	 * @param editor
	 *            the Script editor
	 */
	public OpenEditorActionGroup(ScriptEditor editor) {
		fIsEditorOwner = true;
		fOpen = new OpenAction(editor);
		fOpen.setActionDefinitionId(IDLTKEditorActionDefinitionIds.OPEN_EDITOR);
		editor.setAction("OpenEditor", fOpen); //$NON-NLS-1$
		fSite = editor.getEditorSite();
		initialize(fSite.getSelectionProvider());
	}

	/**
	 * Returns the open action managed by this action group.
	 * 
	 * @return the open action. Returns <code>null</code> if the group doesn't
	 *         provide any open action
	 */
	public IAction getOpenAction() {
		return fOpen;
	}

	private void initialize(ISelectionProvider provider) {
		ISelection selection = provider.getSelection();
		fOpen.update(selection);
		if (!fIsEditorOwner) {
			provider.addSelectionChangedListener(fOpen);
		}
	}

	/*
	 * (non-Javadoc) Method declared in ActionGroup
	 */
	public void fillActionBars(IActionBars actionBar) {
		super.fillActionBars(actionBar);
		setGlobalActionHandlers(actionBar);
	}

	/*
	 * (non-Javadoc) Method declared in ActionGroup
	 */
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		appendToGroup(menu, fOpen);
		if (!fIsEditorOwner) {
			addOpenWithMenu(menu);
		}
	}

	/*
	 * @see ActionGroup#dispose()
	 */
	public void dispose() {
		ISelectionProvider provider = fSite.getSelectionProvider();
		provider.removeSelectionChangedListener(fOpen);
		super.dispose();
	}

	private void setGlobalActionHandlers(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(DLTKActionConstants.OPEN, fOpen);
	}

	private void appendToGroup(IMenuManager menu, IAction action) {
		if (action.isEnabled())
			menu.appendToGroup(IContextMenuConstants.GROUP_OPEN, action);
	}

	private void addOpenWithMenu(IMenuManager menu) {
		ISelection selection = getContext().getSelection();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection ss = (IStructuredSelection) selection;
		if (ss.size() != 1)
			return;
		Object o = ss.getFirstElement();
		if (!(o instanceof IAdaptable))
			return;
		IAdaptable element = (IAdaptable) o;
		Object resource = element.getAdapter(IResource.class);
		if (element instanceof IStorage && !(resource instanceof IFile)) {
			// Create a menu.
			IMenuManager submenu = new MenuManager(ActionMessages.OpenWithMenu_label);
			submenu.add(new OpenStorageWithMenu(fSite.getPage(), element));
			// Add the submenu.
			menu.appendToGroup(IContextMenuConstants.GROUP_OPEN, submenu);
		} else if ((element instanceof IModelElement)) {
			// Create a menu.
			IMenuManager submenu = new MenuManager(ActionMessages.OpenWithMenu_label);
			submenu.add(new OpenModelElementWithMenu(fSite.getPage(), element));
			// Add the submenu.
			menu.appendToGroup(IContextMenuConstants.GROUP_OPEN, submenu);
		} else if ((resource instanceof IFile)) {
			// Create a menu.
			IMenuManager submenu = new MenuManager(ActionMessages.OpenWithMenu_label);
			submenu.add(new OpenWithMenu(fSite.getPage(), (IFile) resource));
			// Add the submenu.
			menu.appendToGroup(IContextMenuConstants.GROUP_OPEN, submenu);
		} 
	}
}
