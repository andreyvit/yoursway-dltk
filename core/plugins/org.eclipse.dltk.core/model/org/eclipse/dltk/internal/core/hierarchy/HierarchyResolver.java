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
package org.eclipse.dltk.internal.core.hierarchy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.core.Openable;

public class HierarchyResolver {

	private HierarchyBuilder hierarchyBuilder;
	private SearchEngine engine;

	public HierarchyResolver(HierarchyBuilder hierarchy) {
		this.hierarchyBuilder = hierarchy;
		this.engine = new SearchEngine();
	}

	public void resolve(boolean computeSubtypes) throws CoreException {

		IType focusType = hierarchyBuilder.getType();

		hierarchyBuilder.hierarchy.initialize(0);

		if (computeSubtypes) {
			computeSubtypes(focusType);
		}

		computeSupertypes(focusType);
	}

	protected void computeSubtypes(IType focusType) throws CoreException {

		// Collect all inheritance information:
		final Map superTypeToExtender = new HashMap();
		SearchRequestor typesCollector = new SearchRequestor() {
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				IType element = (IType) match.getElement();
				String[] superClasses = element.getSuperClasses();
				if (superClasses != null) {
					for (int i = 0; i < superClasses.length; i++) {
						String s = superClasses[i];
						if (!superTypeToExtender.containsKey(s)) {
							superTypeToExtender.put(s, new LinkedList());
						}
						List extenders = (List) superTypeToExtender.get(s);
						extenders.add(element.getElementName());
					}
				}
			}
		};
		SearchPattern pattern = SearchPattern.createPattern("*", //$NON-NLS-1$
				IDLTKSearchConstants.TYPE, IDLTKSearchConstants.DECLARATIONS,
				SearchPattern.R_REGEXP_MATCH);
		engine.search(pattern, new SearchParticipant[] { SearchEngine
				.getDefaultSearchParticipant() },
				hierarchyBuilder.hierarchy.scope, typesCollector,
				hierarchyBuilder.hierarchy.progressMonitor);

		computeSubtypesFor(focusType, superTypeToExtender, new HashMap());
	}

	protected void computeSubtypesFor(IType focusType, Map superTypeToExtender, Map subTypesCache) throws CoreException {
		List extenders = (List) superTypeToExtender.get(focusType.getElementName());
		if (extenders != null) {
			IType[] subTypes = searchTypes((String[])extenders.toArray(new String[extenders.size()]), subTypesCache);
			for (int i = 0; i < subTypes.length; i++) {
				IType subType = subTypes[i];
				hierarchyBuilder.hierarchy.addSubtype(focusType, subType);
			}

			for (int i = 0; i < subTypes.length; i++) {
				IType subType = subTypes[i];
				computeSubtypesFor(subType, superTypeToExtender, subTypesCache);
			}
		}
	}

	protected void computeSupertypes(IType focusType) throws CoreException {
		// Build superclasses hieararchy:
		String[] superClasses = focusType.getSuperClasses();
		if (superClasses != null && superClasses.length > 0) {
			IType[] searchTypes = searchTypes(superClasses);

			for (int i = 0; i < searchTypes.length; i++) {
				IType superclass = searchTypes[i];
				hierarchyBuilder.hierarchy.cacheSuperclass(focusType, superclass);
			}

			for (int i = 0; i < searchTypes.length; i++) {
				IType superclass = searchTypes[i];
				computeSupertypes(superclass);
			}
		} else {
			if (!hierarchyBuilder.hierarchy.contains(focusType)) {
				hierarchyBuilder.hierarchy.addRootClass(focusType);
			}
		}
	}

	protected IType[] searchTypes(String[] types) throws CoreException {
		return searchTypes(types, null);
	}

	protected IType[] searchTypes(String[] types, Map cache)
			throws CoreException {
		List result = new LinkedList();
		for (int i = 0; i < types.length; i++) {
			String type = types[i];
			result.addAll(Arrays.asList(searchTypes(type, cache)));
		}
		return (IType[]) result.toArray(new IType[result.size()]);
	}

	protected IType[] searchTypes(String type) throws CoreException {
		return searchTypes(type, null);
	}

	protected IType[] searchTypes(String type, Map cache) throws CoreException {
		if (cache != null && cache.containsKey(type)) {
			return (IType[]) cache.get(type);
		}
		final List result = new LinkedList();
		SearchRequestor typesCollector = new SearchRequestor() {
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				result.add((IType) match.getElement());
			}
		};
		SearchPattern pattern = SearchPattern.createPattern(type,
				IDLTKSearchConstants.TYPE, IDLTKSearchConstants.DECLARATIONS,
				SearchPattern.R_EXACT_MATCH);
		engine.search(pattern, new SearchParticipant[] { SearchEngine
				.getDefaultSearchParticipant() },
				hierarchyBuilder.hierarchy.scope, typesCollector,
				hierarchyBuilder.hierarchy.progressMonitor);

		IType[] types = (IType[]) result.toArray(new IType[result.size()]);
		if (cache != null) {
			cache.put(type, types);
		}
		return types;
	}

	public void resolve(Openable[] openables, HashSet localTypes) {
		try {
			resolve(true);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}
}
