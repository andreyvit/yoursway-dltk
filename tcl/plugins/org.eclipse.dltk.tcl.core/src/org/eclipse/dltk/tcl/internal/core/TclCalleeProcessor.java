package org.eclipse.dltk.tcl.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementParser;


public class TclCalleeProcessor implements ICalleeProcessor {
	protected static int EXACT_RULE = SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE;
	
	private Map fSearchResults = new HashMap();

	private IMethod method;
		
	//private IDLTKSearchScope scope;

	public TclCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope) {
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
			IMethod[] methods = findMethods(name, argCount, off + sourcePosition);
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
			TclSourceElementParser parser = new TclSourceElementParser(requestor);
			parser.parseSourceModule(methodSource.toCharArray());

			return fSearchResults;
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fSearchResults;
	}
	
	public IMethod[] findMethods(final String methodName, int argCount, int sourcePosition) {
		final List methods = new ArrayList();
		ISourceModule module = this.method.getSourceModule();
		try {
			IModelElement[] elements = module.codeSelect(sourcePosition, methodName.length());
			for( int i = 0; i < elements.length; ++i ) {
				if( elements[i] instanceof IMethod ) {
					methods.add(elements[i]);
				}
			}
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		final String nsName;
//		if( methodName.indexOf("::") != -1 ) {
//			String mmName = methodName;
//			if( mmName.startsWith("::")) {
//				mmName = mmName.substring(2);
//			}
//			if( mmName.indexOf("::") != -1 ) {
//				int posb = mmName.indexOf("::");
//				nsName = mmName.substring(0, posb);
//			}
//			else {
//				nsName = null;
//			}
//		}
//		else {
//			nsName = null;
//		}
//		SearchRequestor requestor = new SearchRequestor() {
//			public void acceptSearchMatch(SearchMatch match) throws CoreException {
//				Object element = match.getElement();
//				if( element instanceof IMethod ) {
//					IMethod method = (IMethod)element;
//					String mn = method.getTypeQualifiedName('$', false).replaceAll("\\$", "::");
//					if( mn.equals(methodName) && !isIgnoredBySearchScope(method) ) {
//						methods.add(method);
//					}
//				}
//				else {
//					IType type = (IType) element;
//					if( !( type.getParent() instanceof ISourceModule )) {						
//						return;
//					}
//					processTypeFunctions(type);
//				}
//			}
//			private void processTypeFunctions(IType type) throws ModelException {				
//				IMethod[] tmethods = type.getMethods();
//				for (int i = 0; i < tmethods.length; ++i) {
//					String mn = tmethods[i].getTypeQualifiedName('$', false).replaceAll("\\$", "::");
//					if( mn.equals(methodName) && !isIgnoredBySearchScope(tmethods[i]) ) {
//						methods.add(tmethods[i]);
//					}
//				}
//				IType[] types = type.getTypes();
//				for( int i = 0; i < types.length; ++i ) {
//					processTypeFunctions(types[i]);
//				}
//			}
//		};
//		
//		try {
//			String pattern = methodName;
//			if( pattern.startsWith("::")) {
//				pattern = pattern.substring(2);
//			}
//			if( pattern.indexOf("::")==-1) {
//				search(pattern, IDLTKSearchConstants.METHOD, IDLTKSearchConstants.DECLARATIONS, scope, requestor);				
//			}
//			if( nsName != null ) {				
//				search(nsName, IDLTKSearchConstants.TYPE, IDLTKSearchConstants.DECLARATIONS, scope, requestor);
//			}
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
