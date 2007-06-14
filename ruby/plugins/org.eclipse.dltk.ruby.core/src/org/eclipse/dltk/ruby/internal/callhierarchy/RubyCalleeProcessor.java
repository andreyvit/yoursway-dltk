/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.callhierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.ruby.core.RubyNature;


public class RubyCalleeProcessor implements ICalleeProcessor {
	protected static int EXACT_RULE = SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE;
	
	private Map fSearchResults = new HashMap();

	private IMethod method;
		
	//private IDLTKSearchScope scope;

	public RubyCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope) {
		this.method = method;
		//this.scope = scope;
	}

	private class CaleeSourceElementRequestor implements ISourceElementRequestor {
		public void acceptFieldReference(char[] fieldName, int sourcePosition) {
		}

		public void acceptMethodReference(char[] methodName, int argCount, int sourcePosition, int sourceEndPosition) {
			String name = new String(methodName);
			int off = 0;
			try {
				off = method.getSourceRange().getOffset();
			} catch (ModelException e) {
				e.printStackTrace();
			} 
			SimpleReference ref = new SimpleReference(off + sourcePosition, off + sourceEndPosition, name);
			IMethod[] methods = findMethods(name, argCount, off + sourceEndPosition - 1);
			fSearchResults.put(ref, methods);
		}

		public void acceptPackage(int declarationStart, int declarationEnd, char[] name) {
			// TODO Auto-generated method stub

		}

		public void acceptTypeReference(char[][] typeName, int sourceStart, int sourceEnd) {
			// TODO Auto-generated method stub

		}

		public void acceptTypeReference(char[] typeName, int sourcePosition) {
			// TODO Auto-generated method stub

		}

		public void enterField(FieldInfo info) {
			// TODO Auto-generated method stub

		}

		public boolean enterFieldCheckDuplicates(FieldInfo info) {
			// TODO Auto-generated method stub
			return false;
		}

		public void enterMethod(MethodInfo info) {
			// TODO Auto-generated method stub

		}

		public void enterMethodRemoveSame(MethodInfo info) {
			// TODO Auto-generated method stub

		}

		public boolean enterMethodWithParentType(MethodInfo info, String parentName, String delimiter) {
			// TODO Auto-generated method stub
			return false;
		}
		
		public boolean enterFieldWithParentType(FieldInfo info, String parentName, String delimiter) {
			// TODO Auto-generated method stub
			return false;
		}

		public void enterModule() {
			// TODO Auto-generated method stub

		}

		public void enterType(TypeInfo info) {
			// TODO Auto-generated method stub

		}

		public boolean enterTypeAppend(TypeInfo info, String fullName, String delimiter) {
			// TODO Auto-generated method stub
			return false;
		}

		public void exitField(int declarationEnd) {
			// TODO Auto-generated method stub

		}

		public void exitMethod(int declarationEnd) {
			// TODO Auto-generated method stub

		}

		public void exitModule(int declarationEnd) {
			// TODO Auto-generated method stub

		}

		public void exitType(int declarationEnd) {
		}

		public void enterModuleRoot() {
			// TODO Auto-generated method stub
			
		}

		public boolean enterTypeAppend(String fullName, String delimiter) {
			// TODO Auto-generated method stub
			return false;
		}

		public void exitModuleRoot() {
			// TODO Auto-generated method stub
			
		}
	}

	public Map doOperation() {
		try {
			String methodSource = method.getSource();
			CaleeSourceElementRequestor requestor = new CaleeSourceElementRequestor();
			ISourceElementParser parser = null;
			
			parser = DLTKLanguageManager.getSourceElementParser(RubyNature.NATURE_ID);
		
			parser.setRequestor(requestor);
			
//			parser.parseModule(null, methodSource, null );
			parser.parseSourceModule(methodSource.toCharArray(), null, method.getSourceModule().getPath().toString().toCharArray());

			return fSearchResults;
		} catch (ModelException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return fSearchResults;
	}
	
	public IMethod[] findMethods(final String methodName, int argCount, int sourcePosition) {
		final List methods = new ArrayList();
		ISourceModule module = this.method.getSourceModule();
		try {
			IModelElement[] elements = module.codeSelect(sourcePosition, /*methodName.length()*/1);
			for( int i = 0; i < elements.length; ++i ) {
				if( elements[i] instanceof IMethod ) {
					methods.add(elements[i]);
				}
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
		
		return (IMethod[])methods.toArray(new IMethod[methods.size()]);
	}

	protected void search(String patternString, int searchFor, int limitTo, IDLTKSearchScope scope, SearchRequestor resultCollector)
			throws CoreException {
		search(patternString, searchFor, limitTo, EXACT_RULE, scope, resultCollector);
	}

	protected void search(String patternString, int searchFor, int limitTo, int matchRule, IDLTKSearchScope scope, SearchRequestor requestor)
			throws CoreException {
		if (patternString.indexOf('*') != -1 || patternString.indexOf('?') != -1) {
			matchRule |= SearchPattern.R_PATTERN_MATCH;
		}
		SearchPattern pattern = SearchPattern.createPattern(patternString, searchFor, limitTo, matchRule);
		new SearchEngine().search(pattern, new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() }, scope, requestor, null);
	}
}
