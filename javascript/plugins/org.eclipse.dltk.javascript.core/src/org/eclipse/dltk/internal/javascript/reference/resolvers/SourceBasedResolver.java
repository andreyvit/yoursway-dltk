/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.reference.resolvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.search.FieldReferenceMatch;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.IIndexConstants;
import org.eclipse.dltk.internal.javascript.typeinference.AbstractCallResultReference;
import org.eclipse.dltk.internal.javascript.typeinference.HostCollection;
import org.eclipse.dltk.internal.javascript.typeinference.IReference;
import org.eclipse.dltk.internal.javascript.typeinference.VaribleDeclarationReference;
import org.eclipse.dltk.javascript.core.FunctionDeclarationReference;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.internal.core.mixin.JavaScriptMixinModel;

public class SourceBasedResolver implements IReferenceResolver,
		IExecutableExtension {

	public boolean canResolve(ISourceModule module) {
		return true;
	}

	public Set getChilds(IResolvableReference ref) {
		if (ref instanceof AbstractCallResultReference) {
			AbstractCallResultReference cm = (AbstractCallResultReference) ref;
			String id = cm.getId();
			List result = searchMethods(id);
			HashSet hashSet = new HashSet();

			for (int a = 0; a < result.size(); a++) {
				FunctionDeclarationReference fr = (FunctionDeclarationReference) result
						.get(a);
				HostCollection ms = fr.getCollection();
				IReference reference = ms.getReference(cm.getResultId());
				if (reference != null) {
					hashSet.addAll(reference.getChilds(true));
				}
			}
			result = searchRefs(id);
			for (int a = 0; a < result.size(); a++) {
				VaribleDeclarationReference fr = (VaribleDeclarationReference) result
						.get(a);
				IReference reference = fr.getReference().getChild(
						cm.getResultId(), true);
				if (reference != null)
					hashSet.addAll(reference.getChilds(true));
			}
			if (hashSet.isEmpty())
				return null;
			return hashSet;
		}
		return null;
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {

	}

	protected static IDLTKLanguageToolkit toolkit;
	protected static IDLTKSearchScope scope;

	static {
		try {
			toolkit = DLTKLanguageManager
					.getLanguageToolkit(JavaScriptNature.NATURE_ID);
			scope = SearchEngine.createWorkspaceScope(toolkit);
		} catch (CoreException e) {
			throw new LinkageError();
		}
	}
	protected static int EXACT_RULE = SearchPattern.R_EXACT_MATCH
			| SearchPattern.R_CASE_SENSITIVE;

	protected List searchMethods(String name) {
		final List result = new ArrayList(2);
		try {
			searchMethodDeclarations(name, new SearchRequestor() {

				public void acceptSearchMatch(SearchMatch match)
						throws CoreException {
					FieldReferenceMatch fr = (FieldReferenceMatch) match;
					ASTNode node = fr.getNode();
					if (node instanceof FunctionDeclarationReference) {
						FunctionDeclarationReference funcRef = (FunctionDeclarationReference) node;
						result.add(funcRef);
					}
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return result;
	}

	protected List searchRefs(String name) {
		final List result = new ArrayList(2);
		try {
			searchMethodDefs(name, new SearchRequestor() {

				public void acceptSearchMatch(SearchMatch match)
						throws CoreException {
					FieldReferenceMatch fr = (FieldReferenceMatch) match;
					ASTNode node = fr.getNode();
					if (node instanceof VaribleDeclarationReference) {
						VaribleDeclarationReference funcRef = (VaribleDeclarationReference) node;
						result.add(funcRef);
					}
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return result;
	}

	protected void searchMethodDeclarations(String methodName,
			SearchRequestor resultCollector) throws CoreException {
		search("!!!" + methodName, IDLTKSearchConstants.FIELD,
				IDLTKSearchConstants.REFERENCES, resultCollector);
	}

	protected void searchMethodDefs(String methodName,
			SearchRequestor resultCollector) throws CoreException {
		search(methodName, IDLTKSearchConstants.FIELD,
				IDLTKSearchConstants.REFERENCES, resultCollector);
	}

	private void search(String patternString, int searchFor, int limitTo,
			SearchRequestor resultCollector) throws CoreException {
		search(patternString, searchFor, limitTo, EXACT_RULE, resultCollector);
	}

	private void search(String patternString, int searchFor, int limitTo,
			int matchRule, SearchRequestor requestor) throws CoreException {
		if (patternString.indexOf('*') != -1
				|| patternString.indexOf('?') != -1) {
			matchRule |= SearchPattern.R_PATTERN_MATCH;
		}
		SearchPattern pattern = SearchPattern.createPattern(patternString,
				searchFor, limitTo, matchRule);
		new SearchEngine().search(pattern,
				new SearchParticipant[] { SearchEngine
						.getDefaultSearchParticipant() }, scope, requestor,
				null);
	}
	
	public Set resolveGlobals(String id) {
		JavaScriptMixinModel m=JavaScriptMixinModel.getInstance();
		String key = IIndexConstants.SEPARATOR+ id.replace('.',IIndexConstants.SEPARATOR);
		String[] findElements = m.findElements(key);
		HashSet result=new HashSet();
		for (int a=0;a<findElements.length;a++){
			IMixinElement mixinElement = m.getRawInstance().get(findElements[a]);
			if (mixinElement==null)continue;
			String keye= mixinElement.getKey().substring(key.length());
			if (keye.indexOf(IIndexConstants.SEPARATOR)!=-1)continue;
			if (mixinElement==null)continue;			
			Object[] allObjects = mixinElement.getAllObjects();
			
			for (int i=0;i<allObjects.length;i++){
				result.add(allObjects[i]);
			}
		}
		return result;
	}
	
	public void processCall(String call, String objId) {
	
	}

	public void init() {

	}

	public void init(ReferenceResolverContext owner) {

	}

}
