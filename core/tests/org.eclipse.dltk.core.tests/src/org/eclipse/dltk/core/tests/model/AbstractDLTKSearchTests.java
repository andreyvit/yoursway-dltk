/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.tests.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.CharOperation;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.MethodReferenceMatch;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.core.ScriptFolder;
import org.eclipse.dltk.internal.core.SourceRefElement;
import org.eclipse.dltk.internal.core.util.Util;


/**
 * Abstract class for Script Search tests.
 */
public class AbstractDLTKSearchTests extends AbstractModelTests implements IDLTKSearchConstants {

	public static List JAVA_SEARCH_SUITES = null;
	protected static IDLTKProject SCRIPT_PROJECT;
	protected static boolean COPY_DIRS = true;
	protected static int EXACT_RULE = SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE;
	protected static int EQUIVALENT_RULE = EXACT_RULE | SearchPattern.R_EQUIVALENT_MATCH;
	protected static int ERASURE_RULE = EXACT_RULE | SearchPattern.R_ERASURE_MATCH;
	protected static int RAW_RULE = EXACT_RULE | SearchPattern.R_ERASURE_MATCH | SearchPattern.R_EQUIVALENT_MATCH;

//	ISourceModule[] workingCopies;
//	boolean discard;

	/**
	 * Collects results as a string.
	 */
	public static class DLTKSearchResultCollector extends SearchRequestor {
		protected SearchMatch match;
		public StringBuffer results = new StringBuffer(), line;
		public boolean showAccuracy;
		public boolean showContext;
		public boolean showRule;
		public boolean showInsideDoc;
		public boolean showPotential = true;
		public boolean showProject;
		public boolean showSynthetic;
		public int count = 0;
		public void acceptSearchMatch(SearchMatch searchMatch) throws CoreException {
			count++;
			this.match = searchMatch;
			writeLine();
			writeLineToResult();
		}
		protected void writeLineToResult() {
			if (match.getAccuracy() == SearchMatch.A_ACCURATE || showPotential) {
				if (results.length() > 0) results.append("\n");
				results.append(line);
			}
		}
		protected void writeLine() throws CoreException {
			try {
				IResource resource = match.getResource();
				IModelElement element = getElement(match);
				line = new StringBuffer(getPathString(resource, element));
				if (this.showProject) {
					IProject project = element.getScriptProject().getProject();
					line.append(" [in ");
					line.append(project.getName());
					line.append("]");
				}
				ISourceModule unit = null;
				if (element instanceof IMethod) {
					line.append(" ");
					IMethod method = (IMethod)element;
					append(method);
					unit = method.getSourceModule();
				} else if (element instanceof IType) {
					line.append(" ");
					IType type = (IType)element;
					append(type);
					unit = type.getSourceModule();
				} else if (element instanceof IField) {
					line.append(" ");
					IField field = (IField)element;
					append(field);
					unit = field.getSourceModule();				
				} else if (element instanceof IScriptFolder) {
					line.append(" ");
					append((IScriptFolder)element);				
				} 
//				else if (element instanceof IImportDeclaration) {
//					IImportDeclaration importDeclaration = (IImportDeclaration)element;
//					unit = (ISourceModule)importDeclaration.getAncestor(IModelElement.COMPILATION_UNIT);
//				} else if (element instanceof IPackageDeclaration) {
//					IPackageDeclaration packageDeclaration = (IPackageDeclaration)element;
//					unit = (ISourceModule)packageDeclaration.getAncestor(IModelElement.COMPILATION_UNIT);
//				}
				if (resource instanceof IFile) {
					char[] contents = getSource(resource, element, unit);
					int start = match.getOffset();
					int end = start + match.getLength();
					if (start == -1 || (contents != null && contents.length > 0)) { // retrieving attached source not implemented here
						line.append(" [");
						if (start > -1) {
							if (this.showContext) {
								int lineStart1 = CharOperation.lastIndexOf('\n', contents, 0, start);
								int lineStart2 = CharOperation.lastIndexOf('\r', contents, 0, start);
								int lineStart = Math.max(lineStart1, lineStart2) + 1;
								line.append(CharOperation.subarray(contents, lineStart, start));
								line.append("<");
							}
							line.append(CharOperation.subarray(contents, start, end));
							if (this.showContext) {
								line.append(">");
								int lineEnd1 = CharOperation.indexOf('\n', contents, end);
								int lineEnd2 = CharOperation.indexOf('\r', contents, end);
								int lineEnd = lineEnd1 > 0 && lineEnd2 > 0 ? Math.min(lineEnd1, lineEnd2) : Math.max(lineEnd1, lineEnd2);
								if (lineEnd == -1) lineEnd = contents.length;
								line.append(CharOperation.subarray(contents, end, lineEnd));
							}
						} else {
							line.append("No source");
						}
						line.append("]");
					}
				}
				if (this.showAccuracy) {
					line.append(" ");
					if (match.getAccuracy() == SearchMatch.A_ACCURATE) {
						if (this.showRule) {
							if (match.isExact()) {
								line.append("EXACT_");
							} else if (match.isEquivalent()) {
								line.append("EQUIVALENT_");
							} else if (match.isErasure()) {
								line.append("ERASURE_");
							} else {
								line.append("INVALID_RULE_");
							}
							if (match.isRaw()) {
								line.append("RAW_");
							}
						} else {
							line.append("EXACT_");
						}
						line.append("MATCH");
					} else {
						line.append("POTENTIAL_MATCH");
					}
				}
				if (this.showInsideDoc) {
					line.append(" ");
					if (match.isInsideDocComment()) {
						line.append("INSIDE_JAVADOC");
					} else {
						line.append("OUTSIDE_JAVADOC");
					}
				}
				if (this.showSynthetic) {
					if (match instanceof MethodReferenceMatch) {
						MethodReferenceMatch methRef = (MethodReferenceMatch) match;
						if (methRef.isSynthetic()) {
							line.append(" SYNTHETIC");
						}
					}
				}
			} catch (ModelException e) {
				results.append("\n");
				results.append(e.toString());
			}
		}
		protected void append(IField field) throws ModelException {
			IType type = field.getDeclaringType();
			if( type != null ) {
				append(type);
				line.append("$");
			}
			line.append(field.getElementName());
		}		
		private void append(IMethod method) throws ModelException {
			if (!method.isConstructor()) {
				//line.append(Signature.toString(method.getReturnType()));
				line.append(" ");
			}
			append(method.getDeclaringType());
			if (!method.isConstructor()) {
				line.append("$");
				line.append(method.getElementName());
			}
			line.append("(");
			String[] parameters = method.getParameters();		
			for (int i = 0, length=parameters.length; i<length; i++) {
				if (i < length - 1) {
					//line.append(Signature.toString(parameters[i]));
					line.append(parameters[i]);
					line.append(", "); //$NON-NLS-1$
				} else {
					//line.append(Signature.toString(parameters[i]));
					line.append(parameters[i]);
				}
			}
			line.append(")");
		}
		private void append(IScriptFolder pkg) {
			line.append(pkg.getElementName());
		}
		private void append(IType type) throws ModelException {
			if( type == null ) {
				return;
			}
			IModelElement parent = type.getParent();
			boolean isLocal = false;
			switch (parent.getElementType()) {
				case IModelElement.SOURCE_MODULE:
					IScriptFolder pkg = type.getScriptFolder();
					append(pkg);
					if (!pkg.getElementName().equals(IScriptFolder.DEFAULT_FOLDER_NAME)) {
						line.append(IScriptFolder.PACKAGE_DELIMITER);
					}
					break;				
				case IModelElement.TYPE:
					append((IType)parent);
					line.append("$");
					break;
				case IModelElement.FIELD:
					append((IField)parent);
					isLocal = true;
					break;				
				case IModelElement.METHOD:
					append((IMethod)parent);
					isLocal = true;
					break;
			}
			if (isLocal) {
				line.append(":");
			}
			String typeName = type.getElementName();
			if (typeName.length() == 0) {
				line.append("<anonymous>");
			} else {
				line.append(typeName);
			}
			if (isLocal) {
				line.append("#");
				line.append(((SourceRefElement)type).occurrenceCount);
			}
		}
		protected IModelElement getElement(SearchMatch searchMatch) {
			return (IModelElement) searchMatch.getElement();
		}
		protected String getPathString(IResource resource, IModelElement element) {
			String pathString;
			if (resource != null) {
				IPath path = resource.getProjectRelativePath();
				if (path.segmentCount() == 0) {
					IModelElement root = element;
					while (root != null && !(root instanceof IProjectFragment)) {
						root = root.getParent();
					}
					if (root != null) {
						IProjectFragment pkgFragmentRoot = (IProjectFragment)root;
						if (pkgFragmentRoot.isExternal()) {
							pathString = pkgFragmentRoot.getPath().toOSString();
						} else {
							pathString = pkgFragmentRoot.getPath().toString();
						}
					} else {
						pathString = "";
					}
				} else {
					pathString = path.toString();
				}
			} else {
				pathString = element.getPath().toString();
			}
			return pathString;
		}
		protected char[] getSource(IResource resource, IModelElement element, ISourceModule unit) throws CoreException {
			char[] contents = CharOperation.NO_CHAR;
			if( Util.isValidSourceModule(resource) && element != null ) {
				ISourceModule cu = (ISourceModule)element.getAncestor(IModelElement.SOURCE_MODULE);
				if (cu != null && cu.isWorkingCopy()) {
					// working copy
					contents = unit.getBuffer().getCharacters();
				}
			}
			return contents;
		}
		public String toString() {
			return results.toString();
		}
	}
	
	protected DLTKSearchResultCollector resultCollector;

	public AbstractDLTKSearchTests(String pluginName, String name) {
		this(pluginName, name, 2);
	}
	public AbstractDLTKSearchTests(String pluginName, String name, int tabs) {
		super( pluginName, name );
		this.displayName = true;
		this.tabs = tabs;
	}
	
	protected void assertSearchResults(String expected) {
		assertSearchResults(expected, resultCollector);
	}
	protected void assertSearchResults(String expected, DLTKSearchResultCollector collector) {
		assertSearchResults("Unexpected search results", expected, collector);
	}
	protected void assertSearchResults(String message, String expected, DLTKSearchResultCollector collector) {
		String actual = collector.toString();
		if (!expected.equals(actual)) {
			if (this.displayName) {
				System.out.print(getName());
				System.out.print(" got ");
				if (collector.count==0)
					System.out.println("no result!");
				else {
					System.out.print(collector.count);
					System.out.print(" result");
					if (collector.count==1)
						System.out.println(":");
					else
						System.out.println("s:");
				}
			}
			if (!displayName || collector.count>0) {
				System.out.print(actual);
				System.out.println(this.endChar);
			}
			if (this.workingCopies != null) {
				int length = this.workingCopies.length;
				String[] sources = new String[length*2];
				for (int i=0; i<length; i++) {
					sources[i*2] = this.workingCopies[i].getPath().toString();
					try {
						sources[i*2+1] = this.workingCopies[i].getSource();
					} catch (ModelException e) {
						// ignore
					}
				}
				System.out.println("--------------------------------------------------------------------------------");
				for (int i=0; i<length; i+=2) {
					System.out.println(sources[i]);
					System.out.println(sources[i+1]);
				}
			}
		}
		assertEquals(
			message,
			expected,
			actual
		);
	}
	
	protected void copyDirectory(File sourceDir, File targetDir) throws IOException {
		if (COPY_DIRS) {
			super.copyDirectory(sourceDir, targetDir);
		} else {
			targetDir.mkdirs();
			File sourceFile = new File(sourceDir, ".project");
			File targetFile = new File(targetDir, ".project");
			targetFile.createNewFile();
			copy(sourceFile, targetFile);
			sourceFile = new File(sourceDir, ".buildpath");
			targetFile = new File(targetDir, ".buildpath");
			targetFile.createNewFile();
			copy(sourceFile, targetFile);
		}
	}
	protected IDLTKSearchScope getSearchScope(String project) {
		return SearchEngine.createSearchScope(new IDLTKProject[] {getScriptProject(project)});
	}
	protected IDLTKSearchScope getSearchScope(IModelElement element ) {
		return SearchEngine.createSearchScope(new IModelElement[] {element});
	}
	protected IDLTKSearchScope getScriptSearchPackageScope(String projectName, String packageName, boolean addSubpackages) throws ModelException {
		IScriptFolder fragment = getScriptFolder(projectName, "src", new Path( packageName ));
		if (fragment == null) return null;
		IModelElement[] searchPackages = null;
		if (addSubpackages) {
			// Create list of package with first found one
			List packages = new ArrayList();
			packages.add(fragment);
			// Add all possible subpackages
			IModelElement[] children= ((IProjectFragment)fragment.getParent()).getChildren();
			String[] names = ((ScriptFolder)fragment).getRelativePath().segments();
			int namesLength = names.length;
			nextPackage: for (int i= 0, length = children.length; i < length; i++) {
				ScriptFolder currentPackage = (ScriptFolder) children[i];
				String[] otherNames = currentPackage.getRelativePath().segments();
				if (otherNames.length <= namesLength) continue nextPackage;
				for (int j = 0; j < namesLength; j++) {
					if (!names[j].equals(otherNames[j]))
						continue nextPackage;
				}
				packages.add(currentPackage);
			}
			searchPackages = new IModelElement[packages.size()];
			packages.toArray(searchPackages);
		} else {
			searchPackages = new IModelElement[1];
			searchPackages[0] = fragment;
		}
		return SearchEngine.createSearchScope(searchPackages);
	}
	IDLTKSearchScope getScriptSearchCUScope(String projectName, String packageName, String cuName) throws ModelException {
		ISourceModule cu = getSourceModule(projectName, "src", new Path( packageName ).append(cuName));
		return SearchEngine.createSearchScope(new ISourceModule[] { cu });
	}
	protected void search(IModelElement element, int limitTo, IDLTKSearchScope scope) throws CoreException {
		search(element, limitTo, EXACT_RULE, scope, resultCollector);
	}
	IDLTKSearchScope getSearchWorkingCopiesScope(ISourceModule workingCopy) throws ModelException {
		return SearchEngine.createSearchScope(new ISourceModule[] { workingCopy });
	}
	IDLTKSearchScope getSearchWorkingCopiesScope() throws ModelException {
		return SearchEngine.createSearchScope(this.workingCopies);
	}
	protected void search(IModelElement element, int limitTo, int matchRule, IDLTKSearchScope scope) throws CoreException {
		search(element, limitTo, matchRule, scope, resultCollector);
	}
	protected void search(IModelElement element, int limitTo, int matchRule, IDLTKSearchScope scope, SearchRequestor requestor) throws CoreException {
		SearchPattern pattern = SearchPattern.createPattern(element, limitTo, matchRule);
		assertNotNull("Pattern should not be null", pattern);
		new SearchEngine().search(
			pattern,
			new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
			scope,
			requestor,
			null
		);
	}
	protected void search(SearchPattern searchPattern, IDLTKSearchScope scope, SearchRequestor requestor) throws CoreException {
		new SearchEngine().search(
			searchPattern, 
			new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
			scope,
			requestor,
			null);
	}
	protected void search(String patternString, int searchFor, int limitTo, IDLTKSearchScope scope) throws CoreException {
		search(patternString, searchFor, limitTo, EXACT_RULE, scope, resultCollector);
	}
	protected void search(String patternString, int searchFor, int limitTo, int matchRule, IDLTKSearchScope scope) throws CoreException {
		search(patternString, searchFor, limitTo, matchRule, scope, resultCollector);
	}
	protected void search(String patternString, int searchFor, int limitTo, int matchRule, IDLTKSearchScope scope, SearchRequestor requestor) throws CoreException {
		if (patternString.indexOf('*') != -1 || patternString.indexOf('?') != -1) {
			matchRule |= SearchPattern.R_PATTERN_MATCH;
		}
		SearchPattern pattern = SearchPattern.createPattern(
			patternString, 
			searchFor,
			limitTo, 
			matchRule);
		assertNotNull("Pattern should not be null", pattern);
		new SearchEngine().search(
			pattern,
			new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
			scope,
			requestor,
			null);
	}
	// Search for sources only.
	protected List searchSourceOnly(IModelElement element, int limitTo, int matchRule, IDLTKSearchScope scope) throws CoreException {
		SearchPattern pattern = SearchPattern.createPattern(element, limitTo, matchRule);
		assertNotNull("Pattern should not be null", pattern);
		return new SearchEngine().searchSourceOnly(
			pattern,
			new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
			scope,
			null
		);
	}
	protected List searchSourceOnly(SearchPattern searchPattern, IDLTKSearchScope scope) throws CoreException {
		return new SearchEngine().searchSourceOnly(
			searchPattern, 
			new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
			scope,
			null);
	}
	protected List searchSourceOnly(String patternString, int searchFor, int limitTo, IDLTKSearchScope scope) throws CoreException {
		return searchSourceOnly(patternString, searchFor, limitTo, EXACT_RULE, scope);
	}
	protected List searchSourceOnly(String patternString, int searchFor, int limitTo, int matchRule, IDLTKSearchScope scope) throws CoreException {
		if (patternString.indexOf('*') != -1 || patternString.indexOf('?') != -1) {
			matchRule |= SearchPattern.R_PATTERN_MATCH;
		}
		SearchPattern pattern = SearchPattern.createPattern(
			patternString, 
			searchFor,
			limitTo, 
			matchRule);
		assertNotNull("Pattern should not be null", pattern);
		return new SearchEngine().searchSourceOnly(
			pattern,
			new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
			scope,
			null);
	}
	///
	protected void searchDeclarationsOfAccessedFields(IModelElement enclosingElement, SearchRequestor requestor) throws ModelException {
		new SearchEngine().searchDeclarationsOfAccessedFields(enclosingElement, requestor, null);
	}
	protected void searchDeclarationsOfReferencedTypes(IModelElement enclosingElement, SearchRequestor requestor) throws ModelException {
		new SearchEngine().searchDeclarationsOfReferencedTypes(enclosingElement, requestor, null);
	}
	protected void searchDeclarationsOfSentMessages(IModelElement enclosingElement, SearchRequestor requestor) throws ModelException {
		new SearchEngine().searchDeclarationsOfSentMessages(enclosingElement, requestor, null);
	}
	protected void setUp () throws Exception {
		super.setUp();
		this.resultCollector = new DLTKSearchResultCollector();
	}
}
