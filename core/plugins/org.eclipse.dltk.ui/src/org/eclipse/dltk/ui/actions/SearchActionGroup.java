/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.actions;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.search.SearchMessages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;


/**
 * Action group that adds the Script search actions to a context menu and
 * the global menu bar.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 */
public class SearchActionGroup extends ActionGroup {

	private ScriptEditor fEditor;

	private ReferencesSearchGroup fReferencesGroup;
	//private ReadReferencesSearchGroup fReadAccessGroup;
	//private WriteReferencesSearchGroup fWriteAccessGroup;
	private DeclarationsSearchGroup fDeclarationsGroup;
	//private ImplementorsSearchGroup fImplementorsGroup;
	//private OccurrencesSearchGroup fOccurrencesGroup;
	
	
	/**
	 * Creates a new <code>JavaSearchActionGroup</code>. The group 
	 * requires that the selection provided by the part's selection provider 
	 * is of type <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param part the view part that owns this action group
	 * @param toolkit 
	 */
	public SearchActionGroup(IViewPart part, IDLTKLanguageToolkit toolkit) {
		this(part.getViewSite(), toolkit);
	}
	
	/**
	 * Creates a new <code>JavaSearchActionGroup</code>. The group 
	 * requires that the selection provided by the page's selection provider 
	 * is of type <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param page the page that owns this action group
	 */
	public SearchActionGroup(Page page, IDLTKLanguageToolkit toolkit) {
		this(page.getSite(), toolkit);
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call this constructor.
	 * @param editor the Script editor
	 */
	public SearchActionGroup(ScriptEditor editor) {
		fEditor= editor;
		
		fReferencesGroup= new ReferencesSearchGroup(fEditor, fEditor.getLanguageToolkit());
		//fReadAccessGroup= new ReadReferencesSearchGroup(fEditor);
		//fWriteAccessGroup= new WriteReferencesSearchGroup(fEditor);
		fDeclarationsGroup= new DeclarationsSearchGroup(fEditor, fEditor.getLanguageToolkit());
		//fImplementorsGroup= new ImplementorsSearchGroup(fEditor);
		//fOccurrencesGroup= new OccurrencesSearchGroup(fEditor);
	}

	private SearchActionGroup(IWorkbenchSite site, IDLTKLanguageToolkit toolkit) {
		fReferencesGroup= new ReferencesSearchGroup(site, toolkit);
		//fReadAccessGroup= new ReadReferencesSearchGroup(site);
		//fWriteAccessGroup= new WriteReferencesSearchGroup(site);
		fDeclarationsGroup= new DeclarationsSearchGroup(site, toolkit);
		//fImplementorsGroup= new ImplementorsSearchGroup(site);
		//fOccurrencesGroup= new OccurrencesSearchGroup(site);
	}

	/* 
	 * Method declared on ActionGroup.
	 */
	public void setContext(ActionContext context) {
		fReferencesGroup.setContext(context);
		fDeclarationsGroup.setContext(context);
		//fImplementorsGroup.setContext(context);
		//fReadAccessGroup.setContext(context);
		//fWriteAccessGroup.setContext(context);
		//fOccurrencesGroup.setContext(context);
	}

	/* 
	 * Method declared on ActionGroup.
	 */
	public void fillActionBars(IActionBars actionBar) {
		super.fillActionBars(actionBar);
		fReferencesGroup.fillActionBars(actionBar);
		fDeclarationsGroup.fillActionBars(actionBar);
		//fImplementorsGroup.fillActionBars(actionBar);
		//fReadAccessGroup.fillActionBars(actionBar);
		//fWriteAccessGroup.fillActionBars(actionBar);
		//fOccurrencesGroup.fillActionBars(actionBar);
	}
	
	/* 
	 * Method declared on ActionGroup.
	 */
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		
		if(DLTKUIPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.SEARCH_USE_REDUCED_MENU)) {
			fReferencesGroup.fillContextMenu(menu);
			fDeclarationsGroup.fillContextMenu(menu);

//			if (fEditor == null) {
//				fImplementorsGroup.fillContextMenu(menu);
//				fReadAccessGroup.fillContextMenu(menu);
//				fWriteAccessGroup.fillContextMenu(menu);
//			}
		} else {
			IMenuManager target= menu;
			IMenuManager searchSubMenu= null;
			if (fEditor != null) {
				String groupName= SearchMessages.group_search; 
				searchSubMenu= new MenuManager(groupName, ITextEditorActionConstants.GROUP_FIND);
				searchSubMenu.add(new GroupMarker(ITextEditorActionConstants.GROUP_FIND));
				target= searchSubMenu;
			}
			
			fReferencesGroup.fillContextMenu(target);
			fDeclarationsGroup.fillContextMenu(target);
			//fImplementorsGroup.fillContextMenu(target);
			//fReadAccessGroup.fillContextMenu(target);
			//fWriteAccessGroup.fillContextMenu(target);
			
//			if (searchSubMenu != null) {
//				fOccurrencesGroup.fillContextMenu(target);
//				searchSubMenu.add(new Separator());
//			}
			
			// no other way to find out if we have added items.
			if (searchSubMenu != null && searchSubMenu.getItems().length > 2) {		
				menu.appendToGroup(ITextEditorActionConstants.GROUP_FIND, searchSubMenu);
			}
		}
	}	

	/* 
	 * Method declared on ActionGroup.
	 */
	public void dispose() {
		fReferencesGroup.dispose();
		fDeclarationsGroup.dispose();
		//fImplementorsGroup.dispose();
		//fReadAccessGroup.dispose();
		//fWriteAccessGroup.dispose();
		//fOccurrencesGroup.dispose();

		super.dispose();
	}
}
