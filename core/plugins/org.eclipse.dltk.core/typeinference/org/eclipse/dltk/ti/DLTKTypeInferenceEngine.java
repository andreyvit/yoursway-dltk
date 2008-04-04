/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.ti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.TypeNameMatch;
import org.eclipse.dltk.core.search.TypeNameMatchRequestor;
import org.eclipse.dltk.evaluation.types.AmbiguousType;
import org.eclipse.dltk.evaluation.types.UnknownType;
import org.eclipse.dltk.ti.goals.AbstractTypeGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class DLTKTypeInferenceEngine implements ITypeInferencer {

	private static final String NATURE = "nature"; //$NON-NLS-1$
	private static final String TYPE_EVALUATORS = "org.eclipse.dltk.core.typeEvaluators"; //$NON-NLS-1$
	private final static Map evaluatorsByNatures = new HashMap();

	static {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(TYPE_EVALUATORS);
		IExtension[] ext = extensionPoint.getExtensions();
		// ArrayList resolvers = new ArrayList();
		for (int a = 0; a < ext.length; a++) {
			IConfigurationElement[] elements = ext[a]
					.getConfigurationElements();
			IConfigurationElement myElement = elements[0];
			try {
				String nature = myElement.getAttribute(NATURE);
				List list = (List) evaluatorsByNatures.get(nature);
				if (list == null) {
					list = new ArrayList();
					evaluatorsByNatures.put(nature, list);
				}
				// ITypeInferencer resolver = (ITypeInferencer) myElement
				// .createExecutableExtension("evaluator");
				// resolvers.add(resolver);
				// list.add(resolver);
				list.add(myElement);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public DLTKTypeInferenceEngine() {
	}

	private void searchTypeDeclarations(IScriptProject dltkProject,
			String patternString, TypeNameMatchRequestor requestor) {
		try {
			int includeMask = IDLTKSearchScope.SOURCES;
			includeMask |= (IDLTKSearchScope.APPLICATION_LIBRARIES
					| IDLTKSearchScope.REFERENCED_PROJECTS | IDLTKSearchScope.SYSTEM_LIBRARIES);
			IDLTKSearchScope scope = SearchEngine.createSearchScope(
					new IScriptProject[] { dltkProject }, includeMask);
			SearchEngine engine = new SearchEngine();
			String typeName = ""; //$NON-NLS-1$
			if (patternString.indexOf("::") != -1) { //$NON-NLS-1$
				typeName = patternString
						.substring(patternString.indexOf("::") + 2); //$NON-NLS-1$
			} else {
				typeName = patternString;
			}
			engine.searchAllTypeNames(null, 0, typeName.toCharArray(),
					SearchPattern.R_EXACT_MATCH, IDLTKSearchConstants.TYPE,
					scope, requestor,
					IDLTKSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, null);
		} catch (CoreException cxcn) {
			cxcn.printStackTrace();
		}
	}

	private void flatten(AmbiguousType type, Set typeSet) {
		IEvaluatedType[] possibleTypes = type.getPossibleTypes();
		for (int cnt = 0, max = possibleTypes.length; cnt < max; cnt++) {
			if (possibleTypes[cnt] instanceof AmbiguousType) {
				flatten((AmbiguousType) possibleTypes[cnt], typeSet);
			} else {
				typeSet.add(possibleTypes[cnt]);
			}
		}
	}

	public IEvaluatedType evaluateType(AbstractTypeGoal goal, int time) {
		String nature = goal.getContext().getLangNature();
		List list = (List) evaluatorsByNatures.get(nature);
		if (list != null) {
			Set typeSet = new HashSet();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				IConfigurationElement element = (IConfigurationElement) iterator
						.next();
				ITypeInferencer ti;
				try {
					ti = (ITypeInferencer) element
							.createExecutableExtension("evaluator"); //$NON-NLS-1$
				} catch (CoreException e) {
					e.printStackTrace();
					continue;
				}
				// ITypeInferencer ti = (ITypeInferencer) iterator.next();
				IEvaluatedType type = ti.evaluateType(goal, time);
				if (type != null && !(type instanceof UnknownType)) {
					if (type instanceof AmbiguousType) {
						flatten((AmbiguousType) type, typeSet);
					} else {
						typeSet.add(type);
					}
				}
			}
			if ((typeSet.size() > 1)
					&& (goal.getContext() instanceof BasicContext)) {
				boolean foundSub = true;
				IType itype;
				Set superTypeSet;
				while (foundSub == true) {
					IEvaluatedType type;
					IEvaluatedType type2;
					for (Iterator iter = typeSet.iterator(); iter.hasNext();) {
						type = (IEvaluatedType) iter.next();
						foundSub = false;
						itype = null;
						final Set iTypeSet = new HashSet();
						String typeName = type.getTypeName();
						searchTypeDeclarations(((BasicContext) goal
								.getContext()).getSourceModule()
								.getScriptProject(), typeName,
								new TypeNameMatchRequestor() {

									public void acceptTypeNameMatch(
											TypeNameMatch match) {
										iTypeSet.add(match.getType());
									}

								});
						if (iTypeSet.isEmpty() != true) {
							itype = (IType) iTypeSet.iterator().next();
							try {
								superTypeSet = new HashSet(Arrays.asList(itype
										.getSuperClasses()));

								for (Iterator iter2 = typeSet.iterator(); iter2
										.hasNext();) {
									type2 = (IEvaluatedType) iter2.next();
									foundSub = (superTypeSet
											.contains("::" + type2.getTypeName()) == true); //$NON-NLS-1$
									if (foundSub == true) {
										typeSet.remove(type2);
										break;
									}
								}

								if (foundSub == true) {
									break;
								}
							} catch (ModelException mxcn) {
								mxcn.printStackTrace();
							}
						}
					}
				}
			}
			if (typeSet.size() == 1) {
				return (IEvaluatedType) typeSet.iterator().next();
			} else if (typeSet.size() > 1) {
				return new AmbiguousType((IEvaluatedType[]) typeSet
						.toArray(new IEvaluatedType[typeSet.size()]));
			}
		}
		return null;
	}

}
