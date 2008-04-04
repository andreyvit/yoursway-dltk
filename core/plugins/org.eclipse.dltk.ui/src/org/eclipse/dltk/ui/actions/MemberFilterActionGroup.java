/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.actions;

import org.eclipse.dltk.ui.viewsupport.AbstractModelElementFilter;
import org.eclipse.dltk.ui.viewsupport.MemberFilterAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.actions.ActionGroup;



/**
 * Action Group that contributes filter buttons for a view parts showing 
 * methods and fields. Contributed filters are: hide fields, hide static
 * members hide non-public members and hide local types.
 * <p>
 * The action group installs a filter on a structured viewer. The filter is connected 
 * to the actions installed in the view part's toolbar menu and is updated when the 
 * state of the buttons changes.
 *  
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
	 *
 */
public class MemberFilterActionGroup extends ActionGroup {

	IPreferenceStore fStore;
	private MemberFilterAction[] fFilterActions;
	
	private StructuredViewer fViewer;	
	private boolean fInViewMenu;
	
	
	/**
	 * Creates a new <code>MemberFilterActionGroup</code>.
	 * 
	 * @param viewer the viewer to be filtered
	 * @param viewerId a unique id of the viewer. Used as a key to to store 
	 * the last used filter settings in the preference store
	 */
	public MemberFilterActionGroup(StructuredViewer viewer, IPreferenceStore store) {
		this(viewer, store, false);
	}
	
	/**
	 * Creates a new <code>MemberFilterActionGroup</code>.
	 * 
	 * @param viewer the viewer to be filtered
	 * @param viewerId a unique id of the viewer. Used as a key to to store 
	 * the last used filter settings in the preference store
	 * @param inViewMenu if <code>true</code> the actions are added to the view
	 * menu. If <code>false</code> they are added to the toolbar.
	 */
	public MemberFilterActionGroup(StructuredViewer viewer, IPreferenceStore store, boolean inViewMenu) {	
				
		fViewer= viewer;
		fStore = store;
		fInViewMenu= inViewMenu;
		
	}
	
	/**
	 * Set actions for group. You need for call this after constucting
	 * @param actions array of MemberFilterAction
	 */
	public void setActions ( MemberFilterAction[] actions) {			
		fFilterActions = new MemberFilterAction[actions.length];
		int i;
		for (i = 0; i < actions.length; i++) {
			MemberFilterAction action = actions[i];
			AbstractModelElementFilter filter = action.getFilter();
			
			boolean filterEnabled = false;
			if (fStore != null)
				filterEnabled= fStore.getBoolean(getPreferenceKey(action.getFilter().getFilteringType()));			
			
			if (filterEnabled) {
				fViewer.addFilter(filter);
			} else
				fViewer.removeFilter(filter);
			
			action.setChecked(filterEnabled);
			fFilterActions[i] = action;			
		}
	}
	
	private String getPreferenceKey(String filterProperty) {
		return "MemberFilterActionGroup." + filterProperty; //$NON-NLS-1$
	}
	
	public void processMemberFilterAction (MemberFilterAction action) {
		boolean set = action.isChecked();
		AbstractModelElementFilter filter = action.getFilter();
		if (fStore != null)
			fStore.setValue(getPreferenceKey(filter.getFilteringType()), set);
		if (set) {
			fViewer.addFilter(filter);
		} else {
			fViewer.removeFilter(filter);
		}
		
		//	refresh
		fViewer.getControl().setRedraw(false);
		BusyIndicator.showWhile(fViewer.getControl().getDisplay(), new Runnable() {
			public void run() {
				fViewer.refresh();
			}
		});
		fViewer.getControl().setRedraw(true);
	}

	/* (non-Javadoc)
	 * @see ActionGroup#fillActionBars(IActionBars)
	 */
	public void fillActionBars(IActionBars actionBars) {
		contributeToToolBar(actionBars.getToolBarManager());
	}
	
	/**
	 * Adds the filter actions to the given tool bar
	 * 
	 * @param tbm the tool bar to which the actions are added
	 */
	public void contributeToToolBar(IToolBarManager tbm) {
		if (fInViewMenu || fFilterActions == null)
			return;
		for (int i= 0; i < fFilterActions.length; i++) {
			tbm.add(fFilterActions[i]);
		}
	}
	
	/**
	 * Adds the filter actions to the given menu manager.
	 * 
	 * @param menu the menu manager to which the actions are added
	 *
	 */
	public void contributeToViewMenu(IMenuManager menu) {
		if (!fInViewMenu)
			return;
		final String filters= "filters"; //$NON-NLS-1$
		if (menu.find(filters) != null) {
			for (int i= 0; i < fFilterActions.length; i++) {
				menu.prependToGroup(filters, fFilterActions[i]);
			}
		} else {
			for (int i= 0; i < fFilterActions.length; i++) {
				menu.add(fFilterActions[i]);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see ActionGroup#dispose()
	 */
	public void dispose() {
		super.dispose();
	}
	
	public void saveState(IMemento memento) {
		//TODO
	}
	
	public void restoreState(IMemento memento) {
		//TODO
	}

}
