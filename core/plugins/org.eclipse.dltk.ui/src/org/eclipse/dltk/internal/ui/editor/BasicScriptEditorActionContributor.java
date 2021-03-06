/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.selectionaction.GoToNextPreviousMemberAction;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.dltk.ui.actions.IScriptEditorActionDefinitionIds;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;

/**
 * Common base class for action contributors for Script editors.
 */
public class BasicScriptEditorActionContributor extends
		BasicTextEditorActionContributor {

	private List fPartListeners = new ArrayList();

	private RetargetTextEditorAction fGotoMatchingBracket;
	private RetargetTextEditorAction fShowOutline;
	private RetargetTextEditorAction fOpenHierarchy;

	private RetargetAction fRetargetShowScriptDoc;
	private RetargetTextEditorAction fShowScriptDoc;

	private RetargetTextEditorAction fGotoNextMemberAction;
	private RetargetTextEditorAction fGotoPreviousMemberAction;

	private RetargetTextEditorAction fRetargetShowInformationAction;

	public BasicScriptEditorActionContributor() {
		super();

		ResourceBundle b = DLTKEditorMessages.getBundleForConstructedKeys();

		fRetargetShowInformationAction = new RetargetTextEditorAction(b,
				"Editor.ShowInformation."); //$NON-NLS-1$
		fRetargetShowInformationAction
				.setActionDefinitionId(ITextEditorActionDefinitionIds.SHOW_INFORMATION);

		fRetargetShowScriptDoc = new RetargetAction(
				DLTKActionConstants.SHOW_DOCUMENTAION,
				DLTKEditorMessages.ShowScriptDoc_label);
		fRetargetShowScriptDoc
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.SHOW_DOCUMENTATION);
		markAsPartListener(fRetargetShowScriptDoc);
		fShowScriptDoc = new RetargetTextEditorAction(b, "ShowDocumentation."); //$NON-NLS-1$
		fShowScriptDoc
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.SHOW_DOCUMENTATION);

		fGotoMatchingBracket = new RetargetTextEditorAction(b,
				"GotoMatchingBracket."); //$NON-NLS-1$
		fGotoMatchingBracket
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.GOTO_MATCHING_BRACKET);

		fShowOutline = new RetargetTextEditorAction(DLTKEditorMessages
				.getBundleForConstructedKeys(), "ShowOutline."); //$NON-NLS-1$
		fShowOutline
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.SHOW_OUTLINE);

		fOpenHierarchy = new RetargetTextEditorAction(DLTKEditorMessages
				.getBundleForConstructedKeys(), "OpenHierarchy."); //$NON-NLS-1$
		fOpenHierarchy
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.OPEN_HIERARCHY);

		fGotoNextMemberAction = new RetargetTextEditorAction(b,
				"GotoNextMember."); //$NON-NLS-1$
		fGotoNextMemberAction
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.GOTO_NEXT_MEMBER);
		fGotoPreviousMemberAction = new RetargetTextEditorAction(b,
				"GotoPreviousMember."); //$NON-NLS-1$
		fGotoPreviousMemberAction
				.setActionDefinitionId(IScriptEditorActionDefinitionIds.GOTO_PREVIOUS_MEMBER);

	}

	protected final void markAsPartListener(RetargetAction action) {
		fPartListeners.add(action);
	}

	/*
	 * @see IEditorActionBarContributor#init(IActionBars, IWorkbenchPage)
	 */
	public void init(IActionBars bars, IWorkbenchPage page) {
		Iterator e = fPartListeners.iterator();
		while (e.hasNext())
			page.addPartListener((RetargetAction) e.next());

		super.init(bars, page);

		bars.setGlobalActionHandler(DLTKActionConstants.SHOW_DOCUMENTAION,
				fShowScriptDoc);
	}

	/*
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void contributeToMenu(IMenuManager menu) {
		super.contributeToMenu(menu);

		IMenuManager editMenu = menu
				.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {

			MenuManager structureSelection = new MenuManager(
					DLTKEditorMessages.ExpandSelectionMenu_label,
					"expandSelection"); //$NON-NLS-1$
			editMenu.insertAfter(ITextEditorActionConstants.SELECT_ALL,
					structureSelection);

			editMenu.appendToGroup(
					ITextEditorActionConstants.GROUP_INFORMATION,
					fRetargetShowInformationAction);
		}

		IMenuManager navigateMenu = menu
				.findMenuUsingPath(IWorkbenchActionConstants.M_NAVIGATE);
		if (navigateMenu != null) {
			try {
				navigateMenu.appendToGroup(IWorkbenchActionConstants.SHOW_EXT,
						fShowOutline);
				navigateMenu.appendToGroup(IWorkbenchActionConstants.SHOW_EXT,
						fOpenHierarchy);
			} catch (IllegalArgumentException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}

		IMenuManager gotoMenu = menu.findMenuUsingPath("navigate/goTo"); //$NON-NLS-1$
		if (gotoMenu != null) {
			gotoMenu.add(new Separator("additions2")); //$NON-NLS-1$
			gotoMenu.appendToGroup("additions2", fGotoPreviousMemberAction); //$NON-NLS-1$
			gotoMenu.appendToGroup("additions2", fGotoNextMemberAction); //$NON-NLS-1$
			gotoMenu.appendToGroup("additions2", fGotoMatchingBracket); //$NON-NLS-1$
		}
	}

	/*
	 * @see EditorActionBarContributor#setActiveEditor(IEditorPart)
	 */
	public void setActiveEditor(IEditorPart part) {

		super.setActiveEditor(part);

		ITextEditor textEditor = null;
		if (part instanceof ITextEditor)
			textEditor = (ITextEditor) part;

		fShowOutline.setAction(getAction(textEditor,
				IScriptEditorActionDefinitionIds.SHOW_OUTLINE));
		fOpenHierarchy.setAction(getAction(textEditor,
				IScriptEditorActionDefinitionIds.OPEN_HIERARCHY));
		fGotoMatchingBracket.setAction(getAction(textEditor,
				GotoMatchingBracketAction.GOTO_MATCHING_BRACKET));

		fGotoNextMemberAction.setAction(getAction(textEditor,
				GoToNextPreviousMemberAction.NEXT_MEMBER));
		fGotoPreviousMemberAction.setAction(getAction(textEditor,
				GoToNextPreviousMemberAction.PREVIOUS_MEMBER));

		fRetargetShowInformationAction.setAction(getAction(textEditor,
				ITextEditorActionConstants.SHOW_INFORMATION));

		if (part instanceof ScriptEditor) {
			ScriptEditor editor = (ScriptEditor) part;
			editor.getActionGroup().fillActionBars(getActionBars());
			FoldingActionGroup foldingActions = editor.getFoldingActionGroup();
			if (foldingActions != null)
				foldingActions.updateActionBars();
		}

		IActionBars actionBars = getActionBars();
		IStatusLineManager manager = actionBars.getStatusLineManager();
		manager.setMessage(null);
		manager.setErrorMessage(null);

		/** The global actions to be connected with editor actions */
		IAction action = getAction(textEditor, ITextEditorActionConstants.NEXT);
		actionBars.setGlobalActionHandler(
				ITextEditorActionDefinitionIds.GOTO_NEXT_ANNOTATION, action);
		actionBars.setGlobalActionHandler(ITextEditorActionConstants.NEXT,
				action);
		action = getAction(textEditor, ITextEditorActionConstants.PREVIOUS);
		actionBars
				.setGlobalActionHandler(
						ITextEditorActionDefinitionIds.GOTO_PREVIOUS_ANNOTATION,
						action);
		actionBars.setGlobalActionHandler(ITextEditorActionConstants.PREVIOUS,
				action);
	}

	/*
	 * @see IEditorActionBarContributor#dispose()
	 */
	public void dispose() {

		Iterator e = fPartListeners.iterator();
		while (e.hasNext())
			getPage().removePartListener((RetargetAction) e.next());
		fPartListeners.clear();

		setActiveEditor(null);
		super.dispose();
	}
}
