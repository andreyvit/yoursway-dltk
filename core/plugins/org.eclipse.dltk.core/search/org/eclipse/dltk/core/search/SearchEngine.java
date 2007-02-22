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
package org.eclipse.dltk.core.search;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.compiler.env.AccessRestriction;
import org.eclipse.dltk.internal.core.search.IRestrictedAccessTypeRequestor;
import org.eclipse.dltk.internal.core.search.TypeNameMatchRequestorWrapper;
import org.eclipse.dltk.internal.core.search.TypeNameRequestorWrapper;
import org.eclipse.dltk.internal.core.search.matching.DeclarationOfAccessedFieldsPattern;
import org.eclipse.dltk.internal.core.search.matching.DeclarationOfReferencedMethodsPattern;
import org.eclipse.dltk.internal.core.search.matching.DeclarationOfReferencedTypesPattern;


/**
 * A {@link SearchEngine} searches for Script elements following a search pattern.
 * The search can be limited to a search scope.
 * <p>
 * Various search patterns can be created using the factory methods 
 * {@link SearchPattern#createPattern(String, int, int, int)}, {@link SearchPattern#createPattern(IModelElement, int)},
 * {@link SearchPattern#createOrPattern(SearchPattern, SearchPattern)}.
 * </p>
 * <p>For example, one can search for references to a method in the hierarchy of a type, 
 * or one can search for the declarations of types starting with "Abstract" in a project.
 * </p>
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class SearchEngine {

	/**
	 * Internal adapter class.
	 * @deprecated marking deprecated as it uses deprecated ISearchPattern
	 */
	static class SearchPatternAdapter implements ISearchPattern {
		SearchPattern pattern;
		SearchPatternAdapter(SearchPattern pattern) {
			this.pattern = pattern;
		}
	}

	/**
	 * Internal adapter class.
	 * @deprecated marking deprecated as it uses deprecated IJavaSearchResultCollector
	 */
	class ResultCollectorAdapter extends SearchRequestor {
		IDLTKSearchResultCollector resultCollector;
		ResultCollectorAdapter(IDLTKSearchResultCollector resultCollector) {
			this.resultCollector = resultCollector;
		}
		
		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			this.resultCollector.accept(
				match.getResource(),
				match.getOffset(),
				match.getOffset() + match.getLength(),
				(IModelElement) match.getElement(),
				match.getAccuracy()
			);
		}
		
		public void beginReporting() {
			this.resultCollector.aboutToStart();
		}
		
		public void endReporting() {
			this.resultCollector.done();
		}
	}

	/**
	 * Internal adapter class.
	 * @deprecated marking deprecated as it uses deprecated ITypeNameRequestor
	 */
	class TypeNameRequestorAdapter implements IRestrictedAccessTypeRequestor {
		ITypeNameRequestor nameRequestor;
		TypeNameRequestorAdapter(ITypeNameRequestor requestor) {
			this.nameRequestor = requestor;
		}
		public void acceptType(int modifiers, char[] packageName, char[] simpleTypeName, char[][] enclosingTypeNames, String path, AccessRestriction access) {
			nameRequestor.acceptClass(packageName, simpleTypeName, enclosingTypeNames, path);
		}
	}
		
	// Search engine now uses basic engine functionalities
	private BasicSearchEngine basicEngine;

	/**
	 * Creates a new search engine.
	 */
	public SearchEngine() {
		this.basicEngine = new BasicSearchEngine();
	}
	
	/**
	 * Creates a new search engine with a list of working copies that will take precedence over 
	 * their original compilation units in the subsequent search operations.
	 * <p>
	 * Note that passing an empty working copy will be as if the original compilation
	 * unit had been deleted.</p>
	 * <p>
	 * The given working copies take precedence over primary working copies (if any).
	 * 
	 * @param workingCopies the working copies that take precedence over their original compilation units
	 *
	 */
	public SearchEngine(ISourceModule[] workingCopies) {
		int length = workingCopies.length;
		ISourceModule[] units = new ISourceModule[length];
		System.arraycopy(workingCopies, 0, units, 0, length);
		this.basicEngine = new BasicSearchEngine(units);
	}
	
	/**
	 * Creates a new search engine with the given working copy owner.
	 * The working copies owned by this owner will take precedence over 
	 * the primary compilation units in the subsequent search operations.
	 * 
	 * @param workingCopyOwner the owner of the working copies that take precedence over their original compilation units
	 *
	 */
	public SearchEngine(WorkingCopyOwner workingCopyOwner) {
		this.basicEngine = new BasicSearchEngine(workingCopyOwner);
	}

	/**
	 * Returns a Script search scope limited to the hierarchy of the given type.
	 * The Script elements resulting from a search with this scope will
	 * be types in this hierarchy, or members of the types in this hierarchy.
	 *
	 * @param type the focus of the hierarchy scope
	 * @return a new hierarchy scope
	 * @exception ModelException if the hierarchy could not be computed on the given type
	 */
	public static IDLTKSearchScope createHierarchyScope(IType type) throws ModelException {
		return BasicSearchEngine.createHierarchyScope(type);
	}
	
	/**
	 * Returns a Script search scope limited to the hierarchy of the given type.
	 * When the hierarchy is computed, the types defined in the working copies owned
	 * by the given owner take precedence over the original compilation units.
	 * The Script elements resulting from a search with this scope will
	 * be types in this hierarchy, or members of the types in this hierarchy.
	 *
	 * @param type the focus of the hierarchy scope
	 * @param owner the owner of working copies that take precedence over original compilation units
	 * @return a new hierarchy scope
	 * @exception ModelException if the hierarchy could not be computed on the given type
	 *
	 */
	public static IDLTKSearchScope createHierarchyScope(IType type, WorkingCopyOwner owner) throws ModelException {
		return BasicSearchEngine.createHierarchyScope(type, owner);
	}

	/**
	 * Returns a Script search scope limited to the given resources.
	 * The Script elements resulting from a search with this scope will
	 * have their underlying resource included in or equals to one of the given
	 * resources.
	 * <p>
	 * Resources must not overlap, for example, one cannot include a folder and its children.
	 * </p>
	 *
	 * @param resources the resources the scope is limited to
	 * @return a new Script search scope
	 * @deprecated Use {@link #createSearchScope(IModelElement[])} instead.
	 */
	public static IDLTKSearchScope createScriptSearchScope(IResource[] resources) {
		int length = resources.length;
		IModelElement[] elements = new IModelElement[length];
		for (int i = 0; i < length; i++) {
			elements[i] = DLTKCore.create(resources[i]);
		}
		return createSearchScope(elements);
	}

	/**
	 * Returns a Script search scope limited to the given Script elements.
	 * The Script elements resulting from a search with this scope will
	 * be children of the given elements.
	 * <p>
	 * If an element is an IScriptProject, then the project's source folders, 
	 * its jars (external and internal) and its referenced projects (with their source 
	 * folders and jars, recursively) will be included.
	 * If an element is an IProjectFragment, then only the package fragments of 
	 * this package fragment root will be included.
	 * If an element is an IScriptFolder, then only the compilation unit and class 
	 * files of this package fragment will be included. Subpackages will NOT be 
	 * included.</p>
	 * <p>
	 * In other words, this is equivalent to using SearchEngine.createJavaSearchScope(elements, true).</p>
	 *
	 * @param elements the Script elements the scope is limited to
	 * @return a new Script search scope
	 *
	 */
	public static IDLTKSearchScope createSearchScope(IModelElement[] elements) {
		return BasicSearchEngine.createSearchScope(elements);
	}

	/**
	 * Returns a Script search scope limited to the given Script elements.
	 * The Script elements resulting from a search with this scope will
	 * be children of the given elements.
	 * 
	 * If an element is an IScriptProject, then the project's source folders, 
	 * its jars (external and internal) and - if specified - its referenced projects 
	 * (with their source folders and jars, recursively) will be included.
	 * If an element is an IProjectFragment, then only the package fragments of 
	 * this package fragment root will be included.
	 * If an element is an IScriptFolder, then only the compilation unit and class 
	 * files of this package fragment will be included. Subpackages will NOT be 
	 * included.
	 *
	 * @param elements the Script elements the scope is limited to
	 * @param includeReferencedProjects a flag indicating if referenced projects must be 
	 * 									 recursively included
	 * @return a new Script search scope
	 *
	 */
	public static IDLTKSearchScope createSearchScope(IModelElement[] elements, boolean includeReferencedProjects) {
		return BasicSearchEngine.createSearchScope(elements, includeReferencedProjects);
	}

	/**
	 * Returns a Script search scope limited to the given Script elements.
	 * The Script elements resulting from a search with this scope will
	 * be children of the given elements.
	 * 
	 * If an element is an IScriptProject, then it includes:
	 * - its source folders if IJavaSearchScope.SOURCES is specified, 
	 * - its application libraries (internal and external jars, class folders that are on the raw buildpath, 
	 *   or the ones that are coming from a buildpath path variable,
	 *   or the ones that are coming from a buildpath container with the K_APPLICATION kind)
	 *   if IJavaSearchScope.APPLICATION_LIBRARIES is specified
	 * - its system libraries (internal and external jars, class folders that are coming from an 
	 *   IBuildpathContainer with the K_SYSTEM kind) 
	 *   if IJavaSearchScope.APPLICATION_LIBRARIES is specified
	 * - its referenced projects (with their source folders and jars, recursively) 
	 *   if IJavaSearchScope.REFERENCED_PROJECTS is specified.
	 * If an element is an IProjectFragment, then only the package fragments of 
	 * this package fragment root will be included.
	 * If an element is an IScriptFolder, then only the compilation unit and class 
	 * files of this package fragment will be included. Subpackages will NOT be 
	 * included.
	 *
	 * @param elements the Script elements the scope is limited to
	 * @param includeMask the bit-wise OR of all include types of interest
	 * @return a new Script search scope
	 * @see IJavaSearchScope#SOURCES
	 * @see IJavaSearchScope#APPLICATION_LIBRARIES
	 * @see IJavaSearchScope#SYSTEM_LIBRARIES
	 * @see IJavaSearchScope#REFERENCED_PROJECTS
	 *
	 */
	public static IDLTKSearchScope createSearchScope(IModelElement[] elements, int includeMask) {
		return BasicSearchEngine.createSearchScope(elements, includeMask);
	}
	
	/**
	 * Returns a search pattern that combines the given two patterns into a "or" pattern.
	 * The search result will match either the left pattern or the right pattern.
	 *
	 * @param leftPattern the left pattern
	 * @param rightPattern the right pattern
	 * @return a "or" pattern
	 * @deprecated Use {@link SearchPattern#createOrPattern(SearchPattern, SearchPattern)} instead.
	 */
	public static ISearchPattern createOrSearchPattern(ISearchPattern leftPattern, ISearchPattern rightPattern) {
		SearchPattern left = ((SearchPatternAdapter) leftPattern).pattern;
		SearchPattern right = ((SearchPatternAdapter) rightPattern).pattern;
		SearchPattern pattern = SearchPattern.createOrPattern(left, right);
		return new SearchPatternAdapter(pattern);
	}
	
	/**
	 * Returns a search pattern based on a given string pattern. The string patterns support '*' wild-cards.
	 * The remaining parameters are used to narrow down the type of expected results.
	 *
	 * <br>
	 *	Examples:
	 *	<ul>
	 * 		<li>search for case insensitive references to <code>Object</code>:
	 *			<code>createSearchPattern("Object", TYPE, REFERENCES, false);</code></li>
	 *  	<li>search for case sensitive references to exact <code>Object()</code> constructor:
	 *			<code>createSearchPattern("java.lang.Object()", CONSTRUCTOR, REFERENCES, true);</code></li>
	 *  	<li>search for implementers of <code>java.lang.Runnable</code>:
	 *			<code>createSearchPattern("java.lang.Runnable", TYPE, IMPLEMENTORS, true);</code></li>
	 *  </ul>
	 * @param stringPattern the given pattern
	 * @param searchFor determines the nature of the searched elements
	 *	<ul>
	 * 	<li>{@link IDLTKSearchConstants#CLASS}: only look for classes</li>
	 *		<li>{@link IDLTKSearchConstants#INTERFACE}: only look for interfaces</li>
	 * 	<li>{@link IDLTKSearchConstants#TYPE}: look for both classes and interfaces</li>
	 *		<li>{@link IDLTKSearchConstants#FIELD}: look for fields</li>
	 *		<li>{@link IDLTKSearchConstants#METHOD}: look for methods</li>
	 *		<li>{@link IDLTKSearchConstants#CONSTRUCTOR}: look for constructors</li>
	 *		<li>{@link IDLTKSearchConstants#PACKAGE}: look for packages</li>
	 *	</ul>
	 * @param limitTo determines the nature of the expected matches
	 *	<ul>
	 * 		<li>{@link IDLTKSearchConstants#DECLARATIONS}: will search declarations matching with the corresponding
	 * 			element. In case the element is a method, declarations of matching methods in subtypes will also
	 *  		be found, allowing to find declarations of abstract methods, etc.</li>
	 *
	 *		 <li>{@link IDLTKSearchConstants#REFERENCES}: will search references to the given element.</li>
	 *
	 *		 <li>{@link IDLTKSearchConstants#ALL_OCCURRENCES}: will search for either declarations or references as specified
	 *  		above.</li>
	 *
	 *		 <li>{@link IDLTKSearchConstants#IMPLEMENTORS}: for types, will find all types
	 *				which directly implement/extend a given interface.
	 *				Note that types may be only classes or only interfaces if {@link IDLTKSearchConstants#CLASS } or
	 *				{@link IDLTKSearchConstants#INTERFACE} is respectively used instead of {@link IDLTKSearchConstants#TYPE}.
	 *		</li>
	 *	</ul>
	 *
	 * @param isCaseSensitive indicates whether the search is case sensitive or not.
	 * @return a search pattern on the given string pattern, or <code>null</code> if the string pattern is ill-formed.
	 * @deprecated Use {@link SearchPattern#createPattern(String, int, int, int)} instead.
	 */
	public static ISearchPattern createSearchPattern(String stringPattern, int searchFor, int limitTo, boolean isCaseSensitive) {
		int matchMode = stringPattern.indexOf('*') != -1 || stringPattern.indexOf('?') != -1
			? SearchPattern.R_PATTERN_MATCH
			: SearchPattern.R_EXACT_MATCH;
		int matchRule = isCaseSensitive ? matchMode | SearchPattern.R_CASE_SENSITIVE : matchMode;
		return  new SearchPatternAdapter(SearchPattern.createPattern(stringPattern, searchFor, limitTo, matchRule));
	}
	
	/**
	 * Returns a search pattern based on a given Script element. 
	 * The pattern is used to trigger the appropriate search, and can be parameterized as follows:
	 *
	 * @param element the Script element the search pattern is based on
	 * @param limitTo determines the nature of the expected matches
	 * 	<ul>
	 * 		<li>{@link IDLTKSearchConstants#DECLARATIONS}: will search declarations matching with the corresponding
	 * 			element. In case the element is a method, declarations of matching methods in subtypes will also
	 *  		be found, allowing to find declarations of abstract methods, etc.</li>
	 *
	 *		 <li>{@link IDLTKSearchConstants#REFERENCES}: will search references to the given element.</li>
	 *
	 *		 <li>{@link IDLTKSearchConstants#ALL_OCCURRENCES}: will search for either declarations or references as specified
	 *  		above.</li>
	 *
	 *		 <li>{@link IDLTKSearchConstants#IMPLEMENTORS}: for types, will find all types
	 *				which directly implement/extend a given interface.</li>
	 *	</ul>
	 * @return a search pattern for a Script element or <code>null</code> if the given element is ill-formed
	 * @deprecated Use {@link SearchPattern#createPattern(IModelElement, int)} instead.
	 */
	public static ISearchPattern createSearchPattern(IModelElement element, int limitTo) {
		return new SearchPatternAdapter(SearchPattern.createPattern(element, limitTo));
	}

	/**
	 * Create a type name match on a given type with specific modifiers.
	 * 
	 * @param type The java model handle of the type
	 * @param modifiers Modifiers of the type
	 * @return A non-null match on the given type.
	 */
	public static TypeNameMatch createTypeNameMatch(IType type, int modifiers) {
		return BasicSearchEngine.createTypeNameMatch(type, modifiers);
	}
	
	/**
	 * Returns a Script search scope with the workspace as the only limit.
	 *
	 * @return a new workspace scope
	 */
	public static IDLTKSearchScope createWorkspaceScope(IDLTKLanguageToolkit toolkit) {
		return BasicSearchEngine.createWorkspaceScope(toolkit);
	}
	/**
	 * Returns a new default Script search participant.
	 * 
	 * @return a new default Script search participant
	 *
	 */
	public static SearchParticipant getDefaultSearchParticipant() {
		return BasicSearchEngine.getDefaultSearchParticipant();
	}

	/**
	 * Searches for the Script element determined by the given signature. The signature
	 * can be incomplete. For example, a call like 
	 * <code>search(ws, "run()", METHOD,REFERENCES, col)</code>
	 * searches for all references to the method <code>run</code>.
	 *
	 * Note that by default the pattern will be case insensitive. For specifying case s
	 * sensitive search, use <code>search(workspace, createSearchPattern(patternString, searchFor, limitTo, true), scope, resultCollector);</code>
	 * 
	 * @param workspace the workspace
	 * @param patternString the pattern to be searched for
	 * @param searchFor a hint what kind of DLTK element the string pattern represents.
	 *  Look into {@link IDLTKSearchConstants} for valid values
	 * @param limitTo one of the following values:
	 *	<ul>
	 *	  <li>{@link IDLTKSearchConstants#DECLARATIONS}: search 
	 *		  for declarations only </li>
	 *	  <li>{@link IDLTKSearchConstants#REFERENCES}: search 
	 *		  for all references </li>
	 *	  <li>{@link IDLTKSearchConstants#ALL_OCCURRENCES}: search 
	 *		  for both declarations and all references </li>
	 *	  <li>{@link IDLTKSearchConstants#IMPLEMENTORS}: for types, will find all types
	 *			which directly implement/extend a given interface.<br>
	 *			Note that types may be only classes or only interfaces if respectively {@link IDLTKSearchConstants#CLASS} or
	 *			{@link IDLTKSearchConstants#INTERFACE} is used for searchFor parameter instead of {@link IDLTKSearchConstants#TYPE}.
	 *	  </li>
	 * </ul>
	 * @param scope the search result has to be limited to the given scope
	 * @param resultCollector a callback object to which each match is reported	 
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 * @deprecated Use {@link  #search(SearchPattern, SearchParticipant[], IDLTKSearchScope, SearchRequestor, IProgressMonitor)} instead.
	 */
	public void search(IWorkspace workspace, String patternString, int searchFor, int limitTo, IDLTKSearchScope scope, IDLTKSearchResultCollector resultCollector) throws ModelException {
		try {
			int matchMode = patternString.indexOf('*') != -1 || patternString.indexOf('?') != -1
				? SearchPattern.R_PATTERN_MATCH
				: SearchPattern.R_EXACT_MATCH;
			search(
				SearchPattern.createPattern(patternString, searchFor, limitTo, matchMode | SearchPattern.R_CASE_SENSITIVE), 
				new SearchParticipant[] {getDefaultSearchParticipant()}, 
				scope, 
				new ResultCollectorAdapter(resultCollector), 
				resultCollector.getProgressMonitor());
		} catch (CoreException e) {
			if (e instanceof ModelException)
				throw (ModelException) e;
			throw new ModelException(e);
		}
	}

	/**
	 * Searches for the given Script element.
	 *
	 * @param workspace the workspace
	 * @param element the Script element to be searched for
	 * @param limitTo one of the following values:
	 *	<ul>
	 *	  <li>{@link IDLTKSearchConstants#DECLARATIONS}: search 
	 *		  for declarations only </li>
	 *	  <li>{@link IDLTKSearchConstants#REFERENCES}: search 
	 *		  for all references </li>
	 *	  <li>{@link IDLTKSearchConstants#ALL_OCCURRENCES}: search 
	 *		  for both declarations and all references </li>
	 *	  <li>{@link IDLTKSearchConstants#IMPLEMENTORS}: for types, will find all types
	 *				which directly implement/extend a given interface.</li>
	 * 	</ul>
	 * @param scope the search result has to be limited to the given scope
	 * @param resultCollector a callback object to which each match is reported
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 * @deprecated Use {@link #search(SearchPattern, SearchParticipant[], IJavaSearchScope, SearchRequestor, IProgressMonitor)} instead.
	 */
	public void search(IWorkspace workspace, IModelElement element, int limitTo, IDLTKSearchScope scope, IDLTKSearchResultCollector resultCollector) throws ModelException {
		search(workspace, createSearchPattern(element, limitTo), scope, resultCollector);
	}

	/**
	 * Searches for matches of a given search pattern. Search patterns can be created using helper
	 * methods (from a String pattern or a Script element) and encapsulate the description of what is
	 * being searched (for example, search method declarations in a case sensitive way).
	 *
	 * @param workspace the workspace
	 * @param searchPattern the pattern to be searched for
	 * @param scope the search result has to be limited to the given scope
	 * @param resultCollector a callback object to which each match is reported
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 * @deprecated Use {@link  #search(SearchPattern, SearchParticipant[], IJavaSearchScope, SearchRequestor, IProgressMonitor)} instead.
	 */
	public void search(IWorkspace workspace, ISearchPattern searchPattern, IDLTKSearchScope scope, IDLTKSearchResultCollector resultCollector) throws ModelException {
		try {
			search(
				((SearchPatternAdapter)searchPattern).pattern, 
				new SearchParticipant[] {getDefaultSearchParticipant()}, 
				scope, 
				new ResultCollectorAdapter(resultCollector), 
				resultCollector.getProgressMonitor());
		} catch (CoreException e) {
			if (e instanceof ModelException)
				throw (ModelException) e;
			throw new ModelException(e);
		}
	}
	
	/**
	 * Searches for matches of a given search pattern. Search patterns can be created using helper
	 * methods (from a String pattern or a Script element) and encapsulate the description of what is
	 * being searched (for example, search method declarations in a case sensitive way).
	 *
	 * @param pattern the pattern to search
	 * @param participants the particpants in the search
	 * @param scope the search scope
	 * @param requestor the requestor to report the matches to
	 * @param monitor the progress monitor used to report progress
	 * @exception CoreException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */
	public void search(SearchPattern pattern, SearchParticipant[] participants, IDLTKSearchScope scope, SearchRequestor requestor, IProgressMonitor monitor) throws CoreException {
		this.basicEngine.search(pattern, participants, scope, requestor, monitor);
	}
	
	public List searchSourceOnly(SearchPattern pattern, SearchParticipant[] participants, IDLTKSearchScope scope, IProgressMonitor monitor) throws CoreException {
		return this.basicEngine.searchSourceOnly(pattern, participants, scope, monitor);
	}

	/**
	 * Searches for all top-level types and member types in the given scope.
	 * The search can be selecting specific types (given a package exact full name or
	 * a type name with specific match mode). 
	 * 
	 * @param packageExactName the exact package full name of the searched types.<br>
	 * 					If you want to use a prefix or a wild-carded string for package, you need to use
	 * 					{@link #searchAllTypeNames(char[], int, char[], int, int, IJavaSearchScope, TypeNameRequestor, int, IProgressMonitor)} method  instead.
	 * @param typeName the dot-separated qualified name of the searched type (the qualification include
	 *					the enclosing types if the searched type is a member type), or a prefix
	 *					for this type, or a wild-carded string for this type.
	 * @param matchRule type name match rule one of
	 * <ul>
	 *		<li>{@link SearchPattern#R_EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 *		<li>{@link SearchPattern#R_CAMELCASE_MATCH} if type name are camel case of the names of the searched types.</li>
	 * </ul>
	 * combined with {@link SearchPattern#R_CASE_SENSITIVE},
	 *   e.g. {@link SearchPattern#R_EXACT_MATCH} | {@link SearchPattern#R_CASE_SENSITIVE} if an exact and case sensitive match is requested, 
	 *   or {@link SearchPattern#R_PREFIX_MATCH} if a prefix non case sensitive match is requested.
	 * @param searchFor determines the nature of the searched elements
	 *	<ul>
	 * 	<li>{@link IDLTKSearchConstants#CLASS}: only look for classes</li>
	 *		<li>{@link IDLTKSearchConstants#INTERFACE}: only look for interfaces</li>
	 * 	<li>{@link IDLTKSearchConstants#ENUM}: only look for enumeration</li>
	 *		<li>{@link IDLTKSearchConstants#ANNOTATION_TYPE}: only look for annotation type</li>
	 * 	<li>{@link IDLTKSearchConstants#CLASS_AND_ENUM}: only look for classes and enumerations</li>
	 *		<li>{@link IDLTKSearchConstants#CLASS_AND_INTERFACE}: only look for classes and interfaces</li>
	 * 	<li>{@link IDLTKSearchConstants#TYPE}: look for all types (ie. classes, interfaces, enum and annotation types)</li>
	 *	</ul>
	 * @param scope the scope to search in
	 * @param nameRequestor the requestor that collects the results of the search
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IDLTKSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IDLTKSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IDLTKSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */
	public void searchAllTypeNames(
		final char[] packageExactName, 
		final char[] typeName,
		final int matchRule, 
		int searchFor, 
		IDLTKSearchScope scope, 
		final TypeNameRequestor nameRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {
		
		searchAllTypeNames(packageExactName, SearchPattern.R_EXACT_MATCH, typeName, matchRule, searchFor, scope, nameRequestor, waitingPolicy, progressMonitor);
	}

	/**
	 * Searches for all top-level types and member types in the given scope.
	 * The search can be selecting specific types (given a package name using specific match mode
	 * and/or a type name using another specific match mode). 
	 * 
	 * @param packageName the full name of the package of the searched types, or a prefix for this
	 *						package, or a wild-carded string for this package.
	 * @param typeName the dot-separated qualified name of the searched type (the qualification include
	 *					the enclosing types if the searched type is a member type), or a prefix
	 *					for this type, or a wild-carded string for this type.
	 * @param packageMatchRule one of
	 * <ul>
	 *		<li>{@link SearchPattern#R_EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 *		<li>{@link SearchPattern#R_CAMELCASE_MATCH} if type name are camel case of the names of the searched types.</li>
	 * </ul>
	 * combined with {@link SearchPattern#R_CASE_SENSITIVE},
	 *   e.g. {@link SearchPattern#R_EXACT_MATCH} | {@link SearchPattern#R_CASE_SENSITIVE} if an exact and case sensitive match is requested, 
	 *   or {@link SearchPattern#R_PREFIX_MATCH} if a prefix non case sensitive match is requested.
	 * @param typeMatchRule one of
	 * <ul>
	 *		<li>{@link SearchPattern#R_EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 *		<li>{@link SearchPattern#R_CAMELCASE_MATCH} if type name are camel case of the names of the searched types.</li>
	 * </ul>
	 * combined with {@link SearchPattern#R_CASE_SENSITIVE},
	 *   e.g. {@link SearchPattern#R_EXACT_MATCH} | {@link SearchPattern#R_CASE_SENSITIVE} if an exact and case sensitive match is requested, 
	 *   or {@link SearchPattern#R_PREFIX_MATCH} if a prefix non case sensitive match is requested.
	 * @param searchFor determines the nature of the searched elements
	 *	<ul>
	 * 	<li>{@link IDLTKSearchConstants#CLASS}: only look for classes</li>
	 *		<li>{@link IDLTKSearchConstants#INTERFACE}: only look for interfaces</li>
	 * 	<li>{@link IDLTKSearchConstants#ENUM}: only look for enumeration</li>
	 *		<li>{@link IDLTKSearchConstants#ANNOTATION_TYPE}: only look for annotation type</li>
	 * 	<li>{@link IDLTKSearchConstants#CLASS_AND_ENUM}: only look for classes and enumerations</li>
	 *		<li>{@link IDLTKSearchConstants#CLASS_AND_INTERFACE}: only look for classes and interfaces</li>
	 * 	<li>{@link IDLTKSearchConstants#TYPE}: look for all types (ie. classes, interfaces, enum and annotation types)</li>
	 *	</ul>
	 * @param scope the scope to search in
	 * @param nameRequestor the requestor that collects the results of the search
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IDLTKSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IDLTKSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IDLTKSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */
	public void searchAllTypeNames(
		final char[] packageName, 
		final int packageMatchRule, 
		final char[] typeName,
		final int typeMatchRule, 
		int searchFor, 
		IDLTKSearchScope scope, 
		final TypeNameRequestor nameRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {
		
		TypeNameRequestorWrapper requestorWrapper = new TypeNameRequestorWrapper(nameRequestor);
		this.basicEngine.searchAllTypeNames(packageName, packageMatchRule, typeName, typeMatchRule, searchFor, scope, requestorWrapper, waitingPolicy, progressMonitor);
	}

	/**
	 * Searches for all top-level types and member types in the given scope.
	 * The search can be selecting specific types (given a package name using specific match mode
	 * and/or a type name using another specific match mode).
	 * <p>
	 * Provided {@link TypeNameMatchRequestor} requestor will collect {@link TypeNameMatch}
	 * matches found during the search.
	 * </p>
	 * 
	 * @param packageName the full name of the package of the searched types, or a prefix for this
	 *						package, or a wild-carded string for this package.
	 *						May be <code>null</code>, then any package name is accepted.
	 * @param packageMatchRule one of
	 * <ul>
	 *		<li>{@link SearchPattern#R_EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 *		<li>{@link SearchPattern#R_CAMELCASE_MATCH} if type name are camel case of the names of the searched types.</li>
	 * </ul>
	 * combined with {@link SearchPattern#R_CASE_SENSITIVE},
	 *   e.g. {@link SearchPattern#R_EXACT_MATCH} | {@link SearchPattern#R_CASE_SENSITIVE} if an exact and case sensitive match is requested, 
	 *   or {@link SearchPattern#R_PREFIX_MATCH} if a prefix non case sensitive match is requested.
	 * @param typeName the dot-separated qualified name of the searched type (the qualification include
	 *					the enclosing types if the searched type is a member type), or a prefix
	 *					for this type, or a wild-carded string for this type.
	 *					May be <code>null</code>, then any type name is accepted.
	 * @param typeMatchRule one of
	 * <ul>
	 *		<li>{@link SearchPattern#R_EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 *		<li>{@link SearchPattern#R_CAMELCASE_MATCH} if type name are camel case of the names of the searched types.</li>
	 * </ul>
	 * combined with {@link SearchPattern#R_CASE_SENSITIVE},
	 *   e.g. {@link SearchPattern#R_EXACT_MATCH} | {@link SearchPattern#R_CASE_SENSITIVE} if an exact and case sensitive match is requested, 
	 *   or {@link SearchPattern#R_PREFIX_MATCH} if a prefix non case sensitive match is requested.
	 * @param searchFor determines the nature of the searched elements
	 *	<ul>
	 * 	<li>{@link IJavaSearchConstants#CLASS}: only look for classes</li>
	 *		<li>{@link IJavaSearchConstants#INTERFACE}: only look for interfaces</li>
	 * 	<li>{@link IJavaSearchConstants#ENUM}: only look for enumeration</li>
	 *		<li>{@link IJavaSearchConstants#ANNOTATION_TYPE}: only look for annotation type</li>
	 * 	<li>{@link IJavaSearchConstants#CLASS_AND_ENUM}: only look for classes and enumerations</li>
	 *		<li>{@link IJavaSearchConstants#CLASS_AND_INTERFACE}: only look for classes and interfaces</li>
	 * 	<li>{@link IJavaSearchConstants#TYPE}: look for all types (ie. classes, interfaces, enum and annotation types)</li>
	 *	</ul>
	 * @param scope the scope to search in
	 * @param nameMatchRequestor the {@link TypeNameMatchRequestor requestor} that collects
	 * 				{@link TypeNameMatch matches} of the search.
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IJavaSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IJavaSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IJavaSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception JavaModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the classpath is incorrectly set</li>
	 *	</ul>
	 * @since 3.3
	 */
	public void searchAllTypeNames(
		final char[] packageName, 
		final int packageMatchRule, 
		final char[] typeName,
		final int typeMatchRule, 
		int searchFor, 
		IDLTKSearchScope scope, 
		final TypeNameMatchRequestor nameMatchRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {
		
		TypeNameMatchRequestorWrapper requestorWrapper = new TypeNameMatchRequestorWrapper(nameMatchRequestor, scope);
		this.basicEngine.searchAllTypeNames(packageName, packageMatchRule, typeName, typeMatchRule, searchFor, scope, requestorWrapper, waitingPolicy, progressMonitor);
	}
	/**
	 * Searches for all top-level types and member types in the given scope matching any of the given qualifications
	 * and type names in a case sensitive way.
	 * 
	 * @param qualifications the qualified name of the package/enclosing type of the searched types
	 * @param typeNames the simple names of the searched types
	 * @param scope the scope to search in
	 * @param nameRequestor the requestor that collects the results of the search
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IDLTKSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IDLTKSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IDLTKSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */
	public void searchAllTypeNames(
		final char[][] qualifications, 
		final char[][] typeNames,
		IDLTKSearchScope scope, 
		final TypeNameRequestor nameRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {

		TypeNameRequestorWrapper requestorWrapper = new TypeNameRequestorWrapper(nameRequestor);
		this.basicEngine.searchAllTypeNames(
			qualifications,
			typeNames,
			SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE,
			IDLTKSearchConstants.TYPE,
			scope,
			requestorWrapper,
			waitingPolicy,
			progressMonitor);
	}

	/**
	 * Searches for all top-level types and member types in the given scope matching any of the given qualifications
	 * and type names in a case sensitive way.
	 * <p>
	 * Provided {@link TypeNameMatchRequestor} requestor will collect {@link TypeNameMatch}
	 * matches found during the search.
	 * </p>
	 * 
	 * @param qualifications the qualified name of the package/enclosing type of the searched types.
	 *					May be <code>null</code>, then any package name is accepted.
	 * @param typeNames the simple names of the searched types.
	 *					If this parameter is <code>null</code>, then no type will be found.
	 * @param scope the scope to search in
	 * @param nameMatchRequestor the {@link TypeNameMatchRequestor requestor} that collects
	 * 				{@link TypeNameMatch matches} of the search.
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IJavaSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IJavaSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IJavaSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception JavaModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the classpath is incorrectly set</li>
	 *	</ul>
	 */
	public void searchAllTypeNames(
		final char[][] qualifications, 
		final char[][] typeNames,
		IDLTKSearchScope scope, 
		final TypeNameMatchRequestor nameMatchRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {

		TypeNameMatchRequestorWrapper requestorWrapper = new TypeNameMatchRequestorWrapper(nameMatchRequestor, scope);
		this.basicEngine.searchAllTypeNames(
			qualifications,
			typeNames,
			SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE,
			IDLTKSearchConstants.TYPE,
			scope,
			requestorWrapper,
			waitingPolicy,
			progressMonitor);
	}

	/**
	 * Searches for all top-level types and member types in the given scope.
	 * The search can be selecting specific types (given a package or a type name
	 * prefix and match modes). 
	 * 
	 * @param packageName the full name of the package of the searched types, or a prefix for this
	 *						package, or a wild-carded string for this package.
	 * @param typeName the dot-separated qualified name of the searched type (the qualification include
	 *					the enclosing types if the searched type is a member type), or a prefix
	 *					for this type, or a wild-carded string for this type.
	 * @param matchRule one of
	 * <ul>
	 *		<li>{@link SearchPattern#R_EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link SearchPattern#R_PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 * </ul>
	 * combined with {@link SearchPattern#R_CASE_SENSITIVE},
	 *   e.g. {@link SearchPattern#R_EXACT_MATCH} | {@link SearchPattern#R_CASE_SENSITIVE} if an exact and case sensitive match is requested, 
	 *   or {@link SearchPattern#R_PREFIX_MATCH} if a prefix non case sensitive match is requested.
	 * @param searchFor one of
	 * <ul>
	 * 		<li>{@link IDLTKSearchConstants#CLASS} if searching for classes only</li>
	 * 		<li>{@link IDLTKSearchConstants#INTERFACE} if searching for interfaces only</li>
	 * 		<li>{@link IDLTKSearchConstants#TYPE} if searching for both classes and interfaces</li>
	 * </ul>
	 * @param scope the scope to search in
	 * @param nameRequestor the requestor that collects the results of the search
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IDLTKSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IDLTKSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IDLTKSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 *@deprecated Use {@link #searchAllTypeNames(char[], char[], int, int, IJavaSearchScope, TypeNameRequestor, int, IProgressMonitor)} instead
	 */
	public void searchAllTypeNames(
		final char[] packageName, 
		final char[] typeName,
		final int matchRule, 
		int searchFor, 
		IDLTKSearchScope scope, 
		final ITypeNameRequestor nameRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {
		
		TypeNameRequestorAdapter requestorAdapter = new TypeNameRequestorAdapter(nameRequestor);
		this.basicEngine.searchAllTypeNames(packageName, SearchPattern.R_EXACT_MATCH, typeName, matchRule, searchFor, scope, requestorAdapter, waitingPolicy, progressMonitor);
	}

	/**
	 * Searches for all top-level types and member types in the given scope.
	 * The search can be selecting specific types (given a package or a type name
	 * prefix and match modes). 
	 * 
	 * @param workspace the workspace to search in
	 * @param packageName the full name of the package of the searched types, or a prefix for this
	 *						package, or a wild-carded string for this package.
	 * @param typeName the dot-separated qualified name of the searched type (the qualification include
	 *					the enclosing types if the searched type is a member type), or a prefix
	 *					for this type, or a wild-carded string for this type.
	 * @param matchMode one of
	 * <ul>
	 *		<li>{@link IDLTKSearchConstants#EXACT_MATCH} if the package name and type name are the full names
	 *			of the searched types.</li>
	 *		<li>{@link IDLTKSearchConstants#PREFIX_MATCH} if the package name and type name are prefixes of the names
	 *			of the searched types.</li>
	 *		<li>{@link IDLTKSearchConstants#PATTERN_MATCH} if the package name and type name contain wild-cards.</li>
	 * </ul>
	 * @param isCaseSensitive whether the search should be case sensitive
	 * @param searchFor one of
	 * <ul>
	 * 		<li>{@link IDLTKSearchConstants#CLASS} if searching for classes only</li>
	 * 		<li>{@link IDLTKSearchConstants#INTERFACE} if searching for interfaces only</li>
	 * 		<li>{@link IDLTKSearchConstants#TYPE} if searching for both classes and interfaces</li>
	 * </ul>
	 * @param scope the scope to search in
	 * @param nameRequestor the requestor that collects the results of the search
	 * @param waitingPolicy one of
	 * <ul>
	 *		<li>{@link IDLTKSearchConstants#FORCE_IMMEDIATE_SEARCH} if the search should start immediately</li>
	 *		<li>{@link IDLTKSearchConstants#CANCEL_IF_NOT_READY_TO_SEARCH} if the search should be cancelled if the
	 *			underlying indexer has not finished indexing the workspace</li>
	 *		<li>{@link IDLTKSearchConstants#WAIT_UNTIL_READY_TO_SEARCH} if the search should wait for the
	 *			underlying indexer to finish indexing the workspace</li>
	 * </ul>
	 * @param progressMonitor the progress monitor to report progress to, or <code>null</code> if no progress
	 *							monitor is provided
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *@deprecated Use {@link #searchAllTypeNames(char[], char[], int, int, IJavaSearchScope, ITypeNameRequestor, int, IProgressMonitor)} instead
	 */
	public void searchAllTypeNames(
		IWorkspace workspace,
		final char[] packageName, 
		final char[] typeName,
		final int matchMode, 
		final boolean isCaseSensitive,
		int searchFor, 
		IDLTKSearchScope scope, 
		final ITypeNameRequestor nameRequestor,
		int waitingPolicy,
		IProgressMonitor progressMonitor)  throws ModelException {
		
		searchAllTypeNames(
			packageName, 
			typeName, 
			isCaseSensitive ? matchMode | SearchPattern.R_CASE_SENSITIVE : matchMode, 
			searchFor, 
			scope, 
			nameRequestor, 
			waitingPolicy, 
			progressMonitor);
	}	

	/**
	 * Searches for all declarations of the fields accessed in the given element.
	 * The element can be a compilation unit, a source type, or a source method.
	 * Reports the field declarations using the given requestor.
	 * <p>
	 * Consider the following code:
	 * <code>
	 * <pre>
	 *		class A {
	 *			int field1;
	 *		}
	 *		class B extends A {
	 *			String value;
	 *		}
	 *		class X {
	 *			void test() {
	 *				B b = new B();
	 *				System.out.println(b.value + b.field1);
	 *			};
	 *		}
	 * </pre>
	 * </code>
	 * then searching for declarations of accessed fields in method 
	 * <code>X.test()</code> would collect the fields
	 * <code>B.value</code> and <code>A.field1</code>.
	 * </p>
	 *
	 * @param enclosingElement the method, type, or compilation unit to be searched in
	 * @param requestor a callback object to which each match is reported
	 * @param monitor the progress monitor used to report progress
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */	
	public void searchDeclarationsOfAccessedFields(IModelElement enclosingElement, SearchRequestor requestor, IProgressMonitor monitor) throws ModelException {
		this.basicEngine.searchDeclarationsOfAccessedFields(enclosingElement, requestor, monitor);
	}
	
	/**
	 * Searches for all declarations of the fields accessed in the given element.
	 * The element can be a compilation unit, a source type, or a source method.
	 * Reports the field declarations using the given collector.
	 * <p>
	 * Consider the following code:
	 * <code>
	 * <pre>
	 *		class A {
	 *			int field1;
	 *		}
	 *		class B extends A {
	 *			String value;
	 *		}
	 *		class X {
	 *			void test() {
	 *				B b = new B();
	 *				System.out.println(b.value + b.field1);
	 *			};
	 *		}
	 * </pre>
	 * </code>
	 * then searching for declarations of accessed fields in method 
	 * <code>X.test()</code> would collect the fields
	 * <code>B.value</code> and <code>A.field1</code>.
	 * </p>
	 *
	 * @param workspace the workspace
	 * @param enclosingElement the method, type, or compilation unit to be searched in
	 * @param resultCollector a callback object to which each match is reported
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 * @deprecated Use {@link  #searchDeclarationsOfAccessedFields(IModelElement, SearchRequestor, IProgressMonitor)} instead.
	 */	
	public void searchDeclarationsOfAccessedFields(IWorkspace workspace, IModelElement enclosingElement, IDLTKSearchResultCollector resultCollector) throws ModelException {
		SearchPattern pattern = new DeclarationOfAccessedFieldsPattern(enclosingElement);
		this.basicEngine.searchDeclarations(enclosingElement, new ResultCollectorAdapter(resultCollector), pattern, resultCollector.getProgressMonitor());
	}
	
	/**
	 * Searches for all declarations of the types referenced in the given element.
	 * The element can be a compilation unit, a source type, or a source method.
	 * Reports the type declarations using the given requestor.
	 * <p>
	 * Consider the following code:
	 * <code>
	 * <pre>
	 *		class A {
	 *		}
	 *		class B extends A {
	 *		}
	 *		interface I {
	 *		  int VALUE = 0;
	 *		}
	 *		class X {
	 *			void test() {
	 *				B b = new B();
	 *				this.foo(b, I.VALUE);
	 *			};
	 *		}
	 * </pre>
	 * </code>
	 * then searching for declarations of referenced types in method <code>X.test()</code>
	 * would collect the class <code>B</code> and the interface <code>I</code>.
	 * </p>
	 *
	 * @param enclosingElement the method, type, or compilation unit to be searched in
	 * @param requestor a callback object to which each match is reported
	 * @param monitor the progress monitor used to report progress
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */	
	public void searchDeclarationsOfReferencedTypes(IModelElement enclosingElement, SearchRequestor requestor, IProgressMonitor monitor) throws ModelException {
		this.basicEngine.searchDeclarationsOfReferencedTypes(enclosingElement, requestor, monitor);
	}
	
	/**
	 * Searches for all declarations of the types referenced in the given element.
	 * The element can be a compilation unit, a source type, or a source method.
	 * Reports the type declarations using the given collector.
	 * <p>
	 * Consider the following code:
	 * <code>
	 * <pre>
	 *		class A {
	 *		}
	 *		class B extends A {
	 *		}
	 *		interface I {
	 *		  int VALUE = 0;
	 *		}
	 *		class X {
	 *			void test() {
	 *				B b = new B();
	 *				this.foo(b, I.VALUE);
	 *			};
	 *		}
	 * </pre>
	 * </code>
	 * then searching for declarations of referenced types in method <code>X.test()</code>
	 * would collect the class <code>B</code> and the interface <code>I</code>.
	 * </p>
	 *
	 * @param workspace the workspace
	 * @param enclosingElement the method, type, or compilation unit to be searched in
	 * @param resultCollector a callback object to which each match is reported
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 * @deprecated Use {@link #searchDeclarationsOfReferencedTypes(IModelElement, SearchRequestor, IProgressMonitor)} instead.
	 */	
	public void searchDeclarationsOfReferencedTypes(IWorkspace workspace, IModelElement enclosingElement, IDLTKSearchResultCollector resultCollector) throws ModelException {
		SearchPattern pattern = new DeclarationOfReferencedTypesPattern(enclosingElement);
		this.basicEngine.searchDeclarations(enclosingElement, new ResultCollectorAdapter(resultCollector), pattern, resultCollector.getProgressMonitor());
	}
	
	/**
	 * Searches for all declarations of the methods invoked in the given element.
	 * The element can be a compilation unit, a source type, or a source method.
	 * Reports the method declarations using the given requestor.
	 * <p>
	 * Consider the following code:
	 * <code>
	 * <pre>
	 *		class A {
	 *			void foo() {};
	 *			void bar() {};
	 *		}
	 *		class B extends A {
	 *			void foo() {};
	 *		}
	 *		class X {
	 *			void test() {
	 *				A a = new B();
	 *				a.foo();
	 *				B b = (B)a;
	 *				b.bar();
	 *			};
	 *		}
	 * </pre>
	 * </code>
	 * then searching for declarations of sent messages in method 
	 * <code>X.test()</code> would collect the methods
	 * <code>A.foo()</code>, <code>B.foo()</code>, and <code>A.bar()</code>.
	 * </p>
	 *
	 * @param enclosingElement the method, type, or compilation unit to be searched in
	 * @param requestor a callback object to which each match is reported
	 * @param monitor the progress monitor used to report progress
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 *
	 */	
	public void searchDeclarationsOfSentMessages(IModelElement enclosingElement, SearchRequestor requestor, IProgressMonitor monitor) throws ModelException {
		this.basicEngine.searchDeclarationsOfSentMessages(enclosingElement, requestor, monitor);
	}

	/**
	 * Searches for all declarations of the methods invoked in the given element.
	 * The element can be a compilation unit, a source type, or a source method.
	 * Reports the method declarations using the given collector.
	 * <p>
	 * Consider the following code:
	 * <code>
	 * <pre>
	 *		class A {
	 *			void foo() {};
	 *			void bar() {};
	 *		}
	 *		class B extends A {
	 *			void foo() {};
	 *		}
	 *		class X {
	 *			void test() {
	 *				A a = new B();
	 *				a.foo();
	 *				B b = (B)a;
	 *				b.bar();
	 *			};
	 *		}
	 * </pre>
	 * </code>
	 * then searching for declarations of sent messages in method 
	 * <code>X.test()</code> would collect the methods
	 * <code>A.foo()</code>, <code>B.foo()</code>, and <code>A.bar()</code>.
	 * </p>
	 *
	 * @param workspace the workspace
	 * @param enclosingElement the method, type, or compilation unit to be searched in
	 * @param resultCollector a callback object to which each match is reported
	 * @exception ModelException if the search failed. Reasons include:
	 *	<ul>
	 *		<li>the element doesn't exist</li>
	 *		<li>the buildpath is incorrectly set</li>
	 *	</ul>
	 * @deprecated Use {@link #searchDeclarationsOfSentMessages(IModelElement, SearchRequestor, IProgressMonitor)} instead.
	 */	
	public void searchDeclarationsOfSentMessages(IWorkspace workspace, IModelElement enclosingElement, IDLTKSearchResultCollector resultCollector) throws ModelException {
		SearchPattern pattern = new DeclarationOfReferencedMethodsPattern(enclosingElement);
		this.basicEngine.searchDeclarations(enclosingElement, new ResultCollectorAdapter(resultCollector), pattern, resultCollector.getProgressMonitor());
	}
}
