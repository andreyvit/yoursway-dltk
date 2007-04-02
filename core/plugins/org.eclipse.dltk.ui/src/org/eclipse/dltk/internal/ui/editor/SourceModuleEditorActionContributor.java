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
package org.eclipse.dltk.internal.ui.editor;

import java.util.ResourceBundle;

import org.eclipse.dltk.ui.IContextMenuConstants;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;


public class SourceModuleEditorActionContributor extends BasicSourceModuleEditorActionContributor {

	private RetargetTextEditorAction fToggleInsertModeAction;

	public SourceModuleEditorActionContributor() {
		super();
		ResourceBundle b= ScriptEditorMessages.getBundleForConstructedKeys();
		fToggleInsertModeAction= new RetargetTextEditorAction(b, "SourceModuleEditorActionContributor.ToggleInsertMode.", IAction.AS_CHECK_BOX); //$NON-NLS-1$
		fToggleInsertModeAction.setActionDefinitionId(ITextEditorActionDefinitionIds.TOGGLE_INSERT_MODE);
	}

	public void contributeToMenu(IMenuManager menu) {
		super.contributeToMenu(menu);

		IMenuManager editMenu= menu.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.appendToGroup(IContextMenuConstants.GROUP_ADDITIONS, fToggleInsertModeAction);
		}
	}

	/*
	 * @see IEditorActionBarContributor#setActiveEditor(IEditorPart)
	 */
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

		ITextEditor textEditor= null;
		if (part instanceof ITextEditor)
			textEditor= (ITextEditor) part;

		// Source menu.
		IActionBars bars= getActionBars();
		bars.setGlobalActionHandler(DLTKActionConstants.COMMENT, getAction(textEditor, "Comment")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.UNCOMMENT, getAction(textEditor, "Uncomment")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.TOGGLE_COMMENT, getAction(textEditor, "ToggleComment")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.FORMAT, getAction(textEditor, "Format")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.FORMAT_ELEMENT, getAction(textEditor, "QuickFormat")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.ADD_BLOCK_COMMENT, getAction(textEditor, "AddBlockComment")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.REMOVE_BLOCK_COMMENT, getAction(textEditor, "RemoveBlockComment")); //$NON-NLS-1$
		bars.setGlobalActionHandler(DLTKActionConstants.INDENT, getAction(textEditor, "Indent")); //$NON-NLS-1$ 

		fToggleInsertModeAction.setAction(getAction(textEditor, ITextEditorActionConstants.TOGGLE_INSERT_MODE));
	}
}
