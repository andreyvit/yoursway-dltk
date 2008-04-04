/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *          (report 36180: Callers/Callees view)
 *   Michael Fraenkel (fraenkel@us.ibm.com) - patch
 *          (report 60714: Call Hierarchy: display search scope in view title)
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.callhierarchy;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.internal.ui.search.DLTKSearchScopeFactory;


public class SearchScopeWorkspaceAction extends SearchScopeAction {	
	public SearchScopeWorkspaceAction(SearchScopeActionGroup group) {
		super(group, CallHierarchyMessages.SearchScopeActionGroup_workspace_text); 
		setToolTipText(CallHierarchyMessages.SearchScopeActionGroup_workspace_tooltip);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here..."); //$NON-NLS-1$
		}		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.CALL_HIERARCHY_SEARCH_SCOPE_ACTION);
	}
	
	public IDLTKSearchScope getSearchScope() {
		return SearchEngine.createWorkspaceScope(fGroup.getLangaugeToolkit());
	}
		
	public int getSearchScopeType() {
		return SearchScopeActionGroup.SEARCH_SCOPE_TYPE_WORKSPACE;
	}
	
	public String getFullDescription() {
		DLTKSearchScopeFactory factory= DLTKSearchScopeFactory.getInstance();
		return factory.getWorkspaceScopeDescription(true);
	}
}
