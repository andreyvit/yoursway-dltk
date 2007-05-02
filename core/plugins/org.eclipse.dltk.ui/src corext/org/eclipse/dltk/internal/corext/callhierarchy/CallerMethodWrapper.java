/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *          (report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.callhierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.ui.DLTKUIPlugin;


class CallerMethodWrapper extends MethodWrapper {
	public CallerMethodWrapper(MethodWrapper parent, MethodCall methodCall) {
		super(parent, methodCall);
	}

	protected IDLTKSearchScope getSearchScope() {
		IModelElement el = this.getMember();
		IDLTKLanguageToolkit toolkit = null;
		try {
			toolkit = DLTKLanguageManager.getLanguageToolkit(el);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CallHierarchy.getDefault().getSearchScope(toolkit);
	}

	protected String getTaskName() {
		return CallHierarchyMessages.CallerMethodWrapper_taskname;
	}
	
	protected MethodWrapper createMethodWrapper(MethodCall methodCall) {
		return new CallerMethodWrapper(this, methodCall);
	}

	/**
	 * @return The result of the search for children
	 */
	protected Map findChildren(IProgressMonitor progressMonitor) {
		try {			
			CallSearchResultCollector fSearchResults = new CallSearchResultCollector();

			IProgressMonitor monitor = new SubProgressMonitor(progressMonitor, 95, SubProgressMonitor.SUPPRESS_SUBTASK_LABEL);
			IDLTKSearchScope defaultSearchScope = getSearchScope();
			IModelElement el = this.getMember();
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLanguageToolkit(el);
			boolean isWorkspaceScope = SearchEngine.createWorkspaceScope(toolkit).equals(defaultSearchScope);

			ICallProcessor processor = DLTKLanguageManager.createCallProcessor(toolkit.getNatureID());
			if (processor == null) {
				return fSearchResults.getCallers();
			}
			for (Iterator iter = getMembers().iterator(); iter.hasNext();) {
				checkCanceled(progressMonitor);

				IModelElement member = (IMember) iter.next();
				IDLTKSearchScope searchScope = isWorkspaceScope ? getAccurateSearchScope(defaultSearchScope, member) : defaultSearchScope;
				Map elements = processor.process(el, member, searchScope, monitor);
				for (Iterator i = elements.keySet().iterator(); i.hasNext();) {
					SimpleReference ref = (SimpleReference) i.next();
					IModelElement ell = (IModelElement) elements.get(ref);
					switch (ell.getElementType()) {
					case IModelElement.METHOD:
					case IModelElement.TYPE:
					case IModelElement.FIELD:
					case IModelElement.SOURCE_MODULE:
						// case IModelElement.INITIALIZER:
						fSearchResults.addMember(ell, ell, ref.sourceStart(), ref.sourceEnd());
						break;
					}
				}
			}
			return fSearchResults.getCallers();

		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
			return new HashMap(0);
		}
	}

	private IDLTKSearchScope getAccurateSearchScope(IDLTKSearchScope defaultSearchScope, IModelElement member) throws ModelException {
		return defaultSearchScope;

		// if (member.getSourceModule() != null) {
		// return SearchEngine.createSearchScope(new IModelElement[]{
		// member.getSourceModule() });
		// } else {
		// return defaultSearchScope;
		// }
	}

	/**
	 * Returns a collection of IMember instances representing what to search for
	 */
	private Collection getMembers() {
		Collection result = new ArrayList();

		result.add(getMember());

		return result;
	}
}
