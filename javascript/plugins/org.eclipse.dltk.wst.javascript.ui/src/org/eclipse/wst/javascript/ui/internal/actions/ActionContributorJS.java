/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.actions;

import java.util.ResourceBundle;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorActionConstants;
import org.eclipse.wst.javascript.ui.internal.editor.JavaScriptUIMessages;
import org.eclipse.wst.sse.ui.internal.actions.ActionContributor;

/**
 * XMLEditorActionContributor
 * 
 * This class should not be used inside multi page editor's ActionBarContributor, since cascaded init() call from the ActionBarContributor will causes exception and it leads to lose whole toolbars.
 * 
 * Instead, use SourcePageActionContributor for source page contributor of multi page editor.
 * 
 * Note that this class is still valid for single page editor.
 */
public class ActionContributorJS extends ActionContributor {
	private static final String[] EDITOR_IDS = {"org.eclipse.wst.javascript.core.javascriptsource.source", "org.eclipse.wst.sse.ui.StructuredTextEditor"}; //$NON-NLS-1$ //$NON-NLS-2$

	protected RetargetTextEditorAction fContentAssist = null;

	public ActionContributorJS() {
		super();

		ResourceBundle resourceBundle = JavaScriptUIMessages.getResourceBundle();

		// edit commands
		fContentAssist = new RetargetTextEditorAction(resourceBundle, ""); //$NON-NLS-1$
		fContentAssist.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
	}

	protected String[] getExtensionIDs() {
		return EDITOR_IDS;
	}

	protected void addToMenu(IMenuManager menu) {
		// edit commands
		IMenuManager editMenu = menu.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.add(fCommandsSeparator);
			editMenu.add(fContentAssist);
			editMenu.add(fMenuAdditionsGroupMarker);
			editMenu.add(fToggleInsertModeAction);
		}

		// source commands
		String sourceMenuLabel = JavaScriptUIMessages.SourceMenu_label; //$NON-NLS-1$
		String sourceMenuId = "sourceMenuId"; // This is just a menu id. No need to translate. //$NON-NLS-1$
		IMenuManager sourceMenu = new MenuManager(sourceMenuLabel, sourceMenuId);
		menu.insertAfter(IWorkbenchActionConstants.M_EDIT, sourceMenu);
		if (sourceMenu != null) {
			sourceMenu.add(fCommandsSeparator);
			sourceMenu.add(fShiftRight);
			sourceMenu.add(fShiftLeft);
		}
	}

	public void setActiveEditor(IEditorPart activeEditor) {
		super.setActiveEditor(activeEditor);

		ITextEditor textEditor = getTextEditor(activeEditor);

		fContentAssist.setAction(getAction(textEditor, JSEditorActionConstants.ACTION_NAME_CONTENT_ASSIST_PROPOSAL));
	}
}
