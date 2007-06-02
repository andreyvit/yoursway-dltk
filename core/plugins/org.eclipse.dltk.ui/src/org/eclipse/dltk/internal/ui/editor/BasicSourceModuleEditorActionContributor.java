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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.dltk.ui.text.completion.CompletionProposalCategory;
import org.eclipse.dltk.ui.text.completion.CompletionProposalComputerRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;



public class BasicSourceModuleEditorActionContributor extends BasicScriptEditorActionContributor {

	/**
	 * A menu listener that can remove itself from the menu it listens to.
	 *
	 */
    private final class MenuListener implements IMenuListener {
        private final IMenuManager fMenu;

		public MenuListener(IMenuManager menu) {
			fMenu= menu;
        }

		public void menuAboutToShow(IMenuManager manager) {
	    	for (int i= 0; i < fSpecificAssistActions.length; i++) {
	            fSpecificAssistActions[i].update();
	        }
	    }
		
		public void dispose() {
			fMenu.removeMenuListener(this);
		}
    }

	protected RetargetAction fRetargetContentAssist;
	protected RetargetTextEditorAction fContentAssist;
	protected RetargetTextEditorAction fContextInformation;
	protected RetargetTextEditorAction fQuickAssistAction;
	protected RetargetTextEditorAction fChangeEncodingAction;
	
	protected SpecificContentAssistAction[] fSpecificAssistActions;

	private MenuListener fContentAssistMenuListener;


	public BasicSourceModuleEditorActionContributor() {

		fRetargetContentAssist= new RetargetAction(DLTKActionConstants.CONTENT_ASSIST,  ScriptEditorMessages.ContentAssistProposal_label);
		fRetargetContentAssist.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		fRetargetContentAssist.setImageDescriptor(DLTKPluginImages.DESC_ELCL_CODE_ASSIST);
		fRetargetContentAssist.setDisabledImageDescriptor(DLTKPluginImages.DESC_DLCL_CODE_ASSIST);
		markAsPartListener(fRetargetContentAssist);

		fContentAssist= new RetargetTextEditorAction(ScriptEditorMessages.getBundleForConstructedKeys(), "ContentAssistProposal."); //$NON-NLS-1$
		fContentAssist.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		fContentAssist.setImageDescriptor(DLTKPluginImages.DESC_ELCL_CODE_ASSIST);
		fContentAssist.setDisabledImageDescriptor(DLTKPluginImages.DESC_DLCL_CODE_ASSIST);

		fContextInformation= new RetargetTextEditorAction(ScriptEditorMessages.getBundleForConstructedKeys(), "ContentAssistContextInformation."); //$NON-NLS-1$
		fContextInformation.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);

		fQuickAssistAction= new RetargetTextEditorAction(ScriptEditorMessages.getBundleForConstructedKeys(), "CorrectionAssistProposal."); //$NON-NLS-1$
		fQuickAssistAction.setActionDefinitionId(ITextEditorActionDefinitionIds.QUICK_ASSIST);

		fChangeEncodingAction= new RetargetTextEditorAction(ScriptEditorMessages.getBundleForConstructedKeys(), "Editor.ChangeEncodingAction."); //$NON-NLS-1$
	}

	/*
	 * @see EditorActionBarContributor#contributeToMenu(IMenuManager)
	 */
	public void contributeToMenu(IMenuManager menu) {

		super.contributeToMenu(menu);
		if (fContentAssistMenuListener != null)
			fContentAssistMenuListener.dispose();

		IMenuManager editMenu= menu.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
		if (editMenu != null) {
			editMenu.add(fChangeEncodingAction);
			IMenuManager caMenu= new MenuManager(ScriptEditorMessages.BasicEditorActionContributor_specific_content_assist_menu, "specific_content_assist"); //$NON-NLS-1$
			editMenu.insertAfter(ITextEditorActionConstants.GROUP_ASSIST, caMenu);
			
			caMenu.add(fRetargetContentAssist);
			Collection descriptors= CompletionProposalComputerRegistry.getDefault().getProposalCategories();
			List specificAssistActions= new ArrayList(descriptors.size());
			for (Iterator it= descriptors.iterator(); it.hasNext();) {
				final CompletionProposalCategory cat= (CompletionProposalCategory) it.next();
				if (cat.hasComputers()) {
					IAction caAction= new SpecificContentAssistAction(cat);
					caMenu.add(caAction);
					specificAssistActions.add(caAction);
				}
			}
			fSpecificAssistActions= (SpecificContentAssistAction[]) specificAssistActions.toArray(new SpecificContentAssistAction[specificAssistActions.size()]);
			if (fSpecificAssistActions.length > 0) {
				fContentAssistMenuListener= new MenuListener(caMenu);
				caMenu.addMenuListener(fContentAssistMenuListener);
			}
			caMenu.add(new Separator("context_info")); //$NON-NLS-1$
			caMenu.add(fContextInformation);
			
			editMenu.appendToGroup(ITextEditorActionConstants.GROUP_ASSIST, fQuickAssistAction);
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

		fContentAssist.setAction(getAction(textEditor, "ContentAssistProposal")); //$NON-NLS-1$
		fContextInformation.setAction(getAction(textEditor, "ContentAssistContextInformation")); //$NON-NLS-1$
		fQuickAssistAction.setAction(getAction(textEditor, ITextEditorActionConstants.QUICK_ASSIST));
		
		if (fSpecificAssistActions != null) {
			for (int i= 0; i < fSpecificAssistActions.length; i++) {
				SpecificContentAssistAction assistAction= fSpecificAssistActions[i];
				assistAction.setActiveEditor(part);
			}
		}

		fChangeEncodingAction.setAction(getAction(textEditor, ITextEditorActionConstants.CHANGE_ENCODING));

		IActionBars actionBars= getActionBars();
		actionBars.setGlobalActionHandler(DLTKActionConstants.SHIFT_RIGHT, getAction(textEditor, "ShiftRight")); //$NON-NLS-1$
		actionBars.setGlobalActionHandler(DLTKActionConstants.SHIFT_LEFT, getAction(textEditor, "ShiftLeft")); //$NON-NLS-1$

		actionBars.setGlobalActionHandler(IDEActionFactory.ADD_TASK.getId(), getAction(textEditor, IDEActionFactory.ADD_TASK.getId()));
		actionBars.setGlobalActionHandler(IDEActionFactory.BOOKMARK.getId(), getAction(textEditor, IDEActionFactory.BOOKMARK.getId()));
	}

	/*
	 * @see IEditorActionBarContributor#init(IActionBars, IWorkbenchPage)
	 */
	public void init(IActionBars bars, IWorkbenchPage page) {
		super.init(bars, page);
		// register actions that have a dynamic editor.
		bars.setGlobalActionHandler(DLTKActionConstants.CONTENT_ASSIST, fContentAssist);
	}

	public void dispose() {
		if (fRetargetContentAssist != null) {
			fRetargetContentAssist.dispose();
			fRetargetContentAssist= null;
		}
		if (fContentAssistMenuListener != null) {
			fContentAssistMenuListener.dispose();
			fContentAssistMenuListener= null;
		}
		super.dispose();
	}
}
