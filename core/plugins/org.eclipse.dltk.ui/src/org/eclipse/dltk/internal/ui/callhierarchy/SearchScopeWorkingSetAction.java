/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jesper Kamstrup Linnet (eclipse@kamstrup-linnet.dk) - initial API and implementation 
 *          (report 36180: Callers/Callees view)
 *   Michael Fraenkel (fraenkel@us.ibm.com) - patch
 *          (report 60714: Call Hierarchy: display search scope in view title)
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.callhierarchy;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.internal.ui.search.DLTKSearchScopeFactory;
import org.eclipse.ui.IWorkingSet;



class SearchScopeWorkingSetAction extends SearchScopeAction {
	private IWorkingSet[] fWorkingSets;
	SearchScopeActionGroup group;
	public SearchScopeWorkingSetAction(SearchScopeActionGroup group, IWorkingSet[] workingSets, String name) {
		super(group, name);
		this.group = group;
		setToolTipText(CallHierarchyMessages.SearchScopeActionGroup_workingset_tooltip); 
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.CALL_HIERARCHY_SEARCH_SCOPE_ACTION);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
		
		this.fWorkingSets = workingSets;
	}
	
	public IDLTKSearchScope getSearchScope() {
		return DLTKSearchScopeFactory.getInstance().createSearchScope(fWorkingSets, true, group.getLangaugeToolkit());
	}
	
	/**
	 *
	 */
	public IWorkingSet[] getWorkingSets() {
		return fWorkingSets;
	}
		
	public int getSearchScopeType() {
		return SearchScopeActionGroup.SEARCH_SCOPE_TYPE_WORKING_SET;
	}
	
	public String getFullDescription() {
		return DLTKSearchScopeFactory.getInstance().getWorkingSetScopeDescription(fWorkingSets, true);
	}
}
