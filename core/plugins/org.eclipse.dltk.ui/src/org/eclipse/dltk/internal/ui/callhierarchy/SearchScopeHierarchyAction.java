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
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.internal.ui.search.DLTKSearchScopeFactory;
import org.eclipse.dltk.ui.DLTKUIPlugin;


class SearchScopeHierarchyAction extends SearchScopeAction {
	private final SearchScopeActionGroup fGroup;
	
	public SearchScopeHierarchyAction(SearchScopeActionGroup group) {
		super(group, CallHierarchyMessages.SearchScopeActionGroup_hierarchy_text); 
		this.fGroup = group;
		setToolTipText(CallHierarchyMessages.SearchScopeActionGroup_hierarchy_tooltip); 
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.CALL_HIERARCHY_SEARCH_SCOPE_ACTION);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
	}
	
	public IDLTKSearchScope getSearchScope() {
		try {
			IMethod method = this.fGroup.getView().getMethod();
			
			if (method != null) {
				return SearchEngine.createHierarchyScope(method.getDeclaringType());
			} else {
				return null;
			}
		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
		}
		
		return null;
	}

	public int getSearchScopeType() {
		return SearchScopeActionGroup.SEARCH_SCOPE_TYPE_HIERARCHY;
	}


	public String getFullDescription() {
		IMethod method = this.fGroup.getView().getMethod();
		return DLTKSearchScopeFactory.getInstance().getHierarchyScopeDescription(method.getDeclaringType());
	}

}
