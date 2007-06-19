/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.search.matching;

import java.io.IOException;
import java.text.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.compiler.env.INameEnvironment;
import org.eclipse.dltk.compiler.env.ISourceType;
import org.eclipse.dltk.compiler.env.lookup.Scope;
import org.eclipse.dltk.compiler.util.SimpleLookupTable;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.BasicSearchEngine;
import org.eclipse.dltk.core.search.FieldDeclarationMatch;
import org.eclipse.dltk.core.search.FieldReferenceMatch;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.MethodDeclarationMatch;
import org.eclipse.dltk.core.search.MethodReferenceMatch;
import org.eclipse.dltk.core.search.SearchDocument;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.TypeDeclarationMatch;
import org.eclipse.dltk.core.search.TypeReferenceMatch;
import org.eclipse.dltk.core.search.index.Index;
import org.eclipse.dltk.internal.compiler.env.AccessRestriction;
import org.eclipse.dltk.internal.compiler.impl.ITypeRequestor;
import org.eclipse.dltk.internal.compiler.lookup.LookupEnvironment;
import org.eclipse.dltk.internal.compiler.lookup.SourceModuleScope;
import org.eclipse.dltk.internal.core.ArchiveProjectFragment;
import org.eclipse.dltk.internal.core.BuiltinSourceModule;
import org.eclipse.dltk.internal.core.ScriptProject;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.core.ModelStatus;
import org.eclipse.dltk.internal.core.NameLookup;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.SourceField;
import org.eclipse.dltk.internal.core.SourceMethod;
import org.eclipse.dltk.internal.core.SourceModule;
import org.eclipse.dltk.internal.core.search.DLTKSearchDocument;
import org.eclipse.dltk.internal.core.search.IndexQueryRequestor;
import org.eclipse.dltk.internal.core.search.IndexSelector;
import org.eclipse.dltk.internal.core.search.matching.AndPattern;
import org.eclipse.dltk.internal.core.search.matching.InternalSearchPattern;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;
import org.eclipse.dltk.internal.core.search.matching.PossibleMatchSet;
import org.eclipse.dltk.internal.core.util.HandleFactory;
import org.eclipse.dltk.internal.core.util.Util;

public class MatchLocator implements ITypeRequestor {
	public static final int MAX_AT_ONCE;
	static {
		long maxMemory = Runtime.getRuntime().maxMemory();
		int ratio = (int) Math.round(((double) maxMemory) / (64 * 0x100000));
		switch (ratio) {
		case 0:
		case 1:
			MAX_AT_ONCE = 100;
			break;
		case 2:
			MAX_AT_ONCE = 200;
			break;
		case 3:
			MAX_AT_ONCE = 300;
			break;
		default:
			MAX_AT_ONCE = 400;
			break;
		}
	}

	// permanent state
	public SearchPattern pattern;

	public PatternLocator patternLocator;

	public int matchContainer;

	public SearchRequestor requestor;

	public IDLTKSearchScope scope;

	public IProgressMonitor progressMonitor;

	public org.eclipse.dltk.core.ISourceModule[] workingCopies;

	public HandleFactory handleFactory;

	// cache of all super type names if scope is hierarchy scope
	public char[][][] allSuperTypeNames;

	// the following is valid for the current project
	public IMatchLocatorParser parser;

	// private Parser basicParser;
	public INameEnvironment nameEnvironment;

	public NameLookup nameLookup;

	public LookupEnvironment lookupEnvironment;

	// management of PossibleMatch to be processed
	public int numberOfMatches; // (numberOfMatches - 1) is the last unit in

	// matchesToProcess
	public PossibleMatch[] matchesToProcess;

	public PossibleMatch currentPossibleMatch;

	/*
	 * Time spent in the IJavaSearchResultCollector
	 */
	public long resultCollectorTime = 0;

	// Progress information
	protected int progressStep;

	protected int progressWorked;

	// Binding resolution and cache
	protected SourceModuleScope unitScope;

	protected SimpleLookupTable bindings;

	// Cache for method handles
	protected HashSet methodHandles;
	
	// Cache for field handles
	protected HashSet fieldHandles;

	public static class WorkingCopyDocument extends DLTKSearchDocument {
		public org.eclipse.dltk.core.ISourceModule workingCopy;

		WorkingCopyDocument(org.eclipse.dltk.core.ISourceModule workingCopy,
				SearchParticipant participant, boolean external) {
			super(workingCopy.getPath().toString(), participant, external);
			this.charContents = ((SourceModule) workingCopy)
					.getSourceContents();
			this.workingCopy = workingCopy;
		}

		public String toString() {
			return "WorkingCopyDocument for " + getPath(); //$NON-NLS-1$
		}
	}

	public class WrappedCoreException extends RuntimeException {
		private static final long serialVersionUID = 8354329870126121212L; // backward

		// compatible
		public CoreException coreException;

		public WrappedCoreException(CoreException coreException) {
			this.coreException = coreException;
		}
	}

	public static SearchDocument[] addWorkingCopies(
			InternalSearchPattern pattern, SearchDocument[] indexMatches,
			org.eclipse.dltk.core.ISourceModule[] copies,
			SearchParticipant participant) {
		// working copies take precedence over corresponding compilation units
		HashMap workingCopyDocuments = workingCopiesThatCanSeeFocus(copies,
				pattern.focus, pattern.isPolymorphicSearch(), participant);
		SearchDocument[] matches = null;
		int length = indexMatches.length;
		for (int i = 0; i < length; i++) {
			SearchDocument searchDocument = indexMatches[i];
			if (searchDocument.getParticipant() == participant) {
				SearchDocument workingCopyDocument = (SearchDocument) workingCopyDocuments
						.remove(searchDocument.getPath());
				if (workingCopyDocument != null) {
					if (matches == null) {
						System
								.arraycopy(indexMatches, 0,
										matches = new SearchDocument[length],
										0, length);
					}
					matches[i] = workingCopyDocument;
				}
			}
		}
		if (matches == null) { // no working copy
			matches = indexMatches;
		}
		int remainingWorkingCopiesSize = workingCopyDocuments.size();
		if (remainingWorkingCopiesSize != 0) {
			System.arraycopy(matches, 0, matches = new SearchDocument[length
					+ remainingWorkingCopiesSize], 0, length);
			Iterator iterator = workingCopyDocuments.values().iterator();
			int index = length;
			while (iterator.hasNext()) {
				matches[index++] = (SearchDocument) iterator.next();
			}
		}
		return matches;
	}

	public static void setFocus(InternalSearchPattern pattern,
			IModelElement focus) {
		pattern.focus = focus;
	}

	/*
	 * Returns the working copies that can see the given focus.
	 */
	private static HashMap workingCopiesThatCanSeeFocus(
			org.eclipse.dltk.core.ISourceModule[] copies, IModelElement focus,
			boolean isPolymorphicSearch, SearchParticipant participant) {
		if (copies == null)
			return new HashMap();
		if (focus != null) {
			while (!(focus instanceof IScriptProject)
					&& !(focus instanceof ArchiveProjectFragment)) {
				focus = focus.getParent();
			}
		}
		HashMap result = new HashMap();
		for (int i = 0, length = copies.length; i < length; i++) {
			org.eclipse.dltk.core.ISourceModule workingCopy = copies[i];
			IPath projectOrArchive = MatchLocator.getProjectOrArchive(
					workingCopy).getPath();
			if (focus == null
					|| IndexSelector.canSeeFocus(focus, isPolymorphicSearch,
							projectOrArchive)) {
				boolean external = false;
				IProjectFragment frag = (IProjectFragment) workingCopy
						.getAncestor(IModelElement.PROJECT_FRAGMENT);
				if (frag != null) {
					external = frag.isExternal();
				}

				result.put(workingCopy.getPath().toString(),
						new WorkingCopyDocument(workingCopy, participant,
								external));
			}
		}
		return result;
	}

	public static SearchPattern createAndPattern(
			final SearchPattern leftPattern, final SearchPattern rightPattern) {
		return new AndPattern(0/* no kind */, 0/* no rule */) {
			SearchPattern current = leftPattern;

			public SearchPattern currentPattern() {
				return this.current;
			}

			protected boolean hasNextQuery() {
				if (this.current == leftPattern) {
					this.current = rightPattern;
					return true;
				}
				return false;
			}

			protected void resetQuery() {
				this.current = leftPattern;
			}
		};
	}

	/**
	 * Query a given index for matching entries. Assumes the sender has opened
	 * the index and will close when finished.
	 */
	public static void findIndexMatches(InternalSearchPattern pattern,
			Index index, IndexQueryRequestor requestor,
			SearchParticipant participant, IDLTKSearchScope scope,
			IProgressMonitor monitor) throws IOException {
		pattern.findIndexMatches(index, requestor, participant, scope, monitor);
	}

	public static IModelElement getProjectOrArchive(IModelElement element) {
		while (!(element instanceof IScriptProject)
				&& !(element instanceof ArchiveProjectFragment)) {
			element = element.getParent();
		}
		return element;
	}

	public static boolean isPolymorphicSearch(InternalSearchPattern pattern) {
		return pattern.isPolymorphicSearch();
	}

	public static IModelElement projectOrArchiveFocus(
			InternalSearchPattern pattern) {
		return pattern == null || pattern.focus == null ? null
				: getProjectOrArchive(pattern.focus);
	}

	public MatchLocator(SearchPattern pattern, SearchRequestor requestor,
			IDLTKSearchScope scope, IProgressMonitor progressMonitor) {
		this.pattern = pattern;
		this.patternLocator = PatternLocator.patternLocator(this.pattern);
		this.matchContainer = this.patternLocator.matchContainer();
		this.requestor = requestor;
		this.scope = scope;
		this.progressMonitor = progressMonitor;
	}

	/**
	 * Add an additional compilation unit into the loop -> build compilation
	 * unit declarations, their bindings and record their results.
	 */
	public void accept(ISourceModule sourceUnit,
			AccessRestriction accessRestriction) {
	}

	/**
	 * Add additional source types
	 */
	public void accept(ISourceType[] sourceTypes,
			AccessRestriction accessRestriction) {
	}

	/*
	 * /* Computes the super type names of the focus type if any.
	 */
	protected char[][][] computeSuperTypeNames(IType focusType) {
		return null;
	}

	/**
	 * Creates an IMethod from the given method declaration and type.
	 */
	protected IModelElement createHandle(MethodDeclaration method,
			IModelElement parent) {
		// if (!(parent instanceof IType)) return parent;
		if (parent instanceof IType) {
			IType type = (IType) parent;
			return createMethodHandle(type, new String(method.getName()));
		} else if (parent instanceof ISourceModule) {
			return createMethodHandle((ISourceModule) parent, method.getName());
		}
		return null;
	}

	/**
	 * Creates an IMethod from the given method declaration and type.
	 */
	protected IModelElement createHandle(FieldDeclaration field,
			IModelElement parent) {
		// if (!(parent instanceof IType)) return parent;
		if (parent instanceof IType) {
			IType type = (IType) parent;
			return createFieldHandle(type, new String(field.getName()));
		} else if (parent instanceof ISourceModule) {
			return createFieldHandle((ISourceModule) parent, field.getName());
		}
		return null;
	}

	/*
	 * Create method handle. Store occurences for create handle to retrieve
	 * possible duplicate ones.
	 */
	protected IModelElement createMethodHandle(IType type, String methodName) {
		IMethod methodHandle = type.getMethod(methodName);
		if (methodHandle instanceof SourceMethod) {
			while (this.methodHandles.contains(methodHandle)) {
				((SourceMethod) methodHandle).occurrenceCount++;
			}
		}
		this.methodHandles.add(methodHandle);
		return methodHandle;
	}

	/*
	 * Create method handle. Store occurences for create handle to retrieve
	 * possible duplicate ones.
	 */
	protected IModelElement createMethodHandle(ISourceModule module,
			String methodName) {

		IMethod methodHandle = module.getMethod(methodName);
		if (methodHandle instanceof SourceMethod) {
			while (this.methodHandles.contains(methodHandle)) {
				((SourceMethod) methodHandle).occurrenceCount++;
			}
		}
		this.methodHandles.add(methodHandle);
		return methodHandle;
	}

	/*
	 * Create method handle. Store occurences for create handle to retrieve
	 * possible duplicate ones.
	 */
	protected IModelElement createFieldHandle(IType type, String methodName) {
		IField fieldHandle = type.getField(methodName);
		if (fieldHandle instanceof SourceField) {
			while (this.fieldHandles.contains(fieldHandle)) {
				((SourceField) fieldHandle).occurrenceCount++;
			}
		}
		this.fieldHandles.add(fieldHandle);
		return fieldHandle;
	}

	/*
	 * Create method handle. Store occurences for create handle to retrieve
	 * possible duplicate ones.
	 */
	protected IModelElement createFieldHandle(ISourceModule module,
			String methodName) {

		IField fieldHandle = module.getField(methodName);
		if (fieldHandle instanceof SourceField) {
			while (this.fieldHandles.contains(fieldHandle)) {
				((SourceField) fieldHandle).occurrenceCount++;
			}
		}
		this.fieldHandles.add(fieldHandle);
		return fieldHandle;
	}

	/**
	 * Creates an IType from the given simple top level type name.
	 */
	protected IType createTypeHandle(String simpleTypeName) {
		Openable openable = this.currentPossibleMatch.openable;
		if (openable instanceof SourceModule)
			return ((SourceModule) openable).getType(simpleTypeName);
		else if (openable instanceof ExternalSourceModule) {
			return ((ExternalSourceModule) openable).getType(simpleTypeName);
		}
		else if (openable instanceof BuiltinSourceModule) {
			return ((BuiltinSourceModule) openable).getType(simpleTypeName);
		}
		return null;
	}

	/**
	 * Creates an IType from the given simple top level type name.
	 */
	protected ISourceModule createSourceModuleHandle() {
		Openable openable = this.currentPossibleMatch.openable;
		if (openable instanceof ISourceModule)
			return ((ISourceModule) openable);
		return null;
	}

	/**
	 * Creates an IType from the given simple top level type name.
	 */
	protected IMethod createMethodHandle(String simpleTypeName) {
		Openable openable = this.currentPossibleMatch.openable;
		if (openable instanceof SourceModule)
			return ((SourceModule) openable).getMethod(simpleTypeName);
		if (openable instanceof ExternalSourceModule) {
			return ((ExternalSourceModule) openable).getMethod(simpleTypeName);
		}
		return null;
	}
	/**
	 * Creates an IType from the given simple top level type name.
	 */
	protected IField createFieldHandle(String simpleTypeName) {
		Openable openable = this.currentPossibleMatch.openable;
		if (openable instanceof SourceModule)
			return ((SourceModule) openable).getField(simpleTypeName);
		if (openable instanceof ExternalSourceModule) {
			return ((ExternalSourceModule) openable).getField(simpleTypeName);
		}
		return null;
	}

	protected boolean encloses(IModelElement element) {
		return element != null && this.scope.encloses(element);
	}

	protected void getMethodBodies(ModuleDeclaration unit,
			MatchingNodeSet nodeSet) {

		try {
			this.parser.setNodeSet(nodeSet);
			this.parser.parseBodies(unit);
		} finally {
			this.parser.setNodeSet(null);
		}
	}

	/**
	 * Create a new parser for the given project, as well as a lookup
	 * environment.
	 */
	public void initialize(ScriptProject project, int possibleMatchSize)
			throws ModelException {
		// clean up name environment only if there are several possible match as
		// it is
		// reused
		// when only one possible match (bug 58581)
		if (this.nameEnvironment != null && possibleMatchSize != 1)
			this.nameEnvironment.cleanup();

		ISearchableEnvironment searchableEnvironment = project
				.newSearchableNameEnvironment(this.workingCopies);

		// if only one possible match, a file name environment costs too much,
		// so use the existing searchable environment which will populate the
		// scriptmodel
		// only for this possible match and its required types.

		this.nameEnvironment = possibleMatchSize == 1 ? (INameEnvironment) searchableEnvironment
				: null;// (INameEnvironment)
		// new
		// DLTKSearchNameEnvironment(project,
		// this.workingCopies);

		// create lookup environment
		// Map map = project.getOptions(true);
		// map.put(CompilerOptions.OPTION_TaskTags, ""); //$NON-NLS-1$
		// this.options = new CompilerOptions(map);
		// ProblemReporter problemReporter =
		// new ProblemReporter(
		// DefaultErrorHandlingPolicies.proceedWithAllProblems(),
		// this.options,
		// new DefaultProblemFactory());
		this.lookupEnvironment = new LookupEnvironment(this, /* problemReporter, */
				this.nameEnvironment);

		IDLTKLanguageToolkit tk = null;
		try {
			tk = DLTKLanguageManager.getLanguageToolkit(project);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if (tk == null) {
			throw new ModelException(new ModelStatus(
					IModelStatusConstants.INVALID_PROJECT, project,
					"Language Toolkit not found on project"));
		}
		this.parser = DLTKLanguageManager.createMatchParser(tk.getNatureId(), this);

		// remember project's name lookup
		this.nameLookup = searchableEnvironment.getNameLookup();

		// initialize queue of units
		this.numberOfMatches = 0;
		this.matchesToProcess = new PossibleMatch[possibleMatchSize];
	}

	protected void locateMatches(ScriptProject scriptProject,
			PossibleMatch[] possibleMatches, int start, int length)
			throws CoreException {
		initialize(scriptProject, length);
		// create and resolve binding (equivalent to beginCompilation() in
		// Compiler)
		for (int i = start, maxUnits = start + length; i < maxUnits; i++) {
			PossibleMatch possibleMatch = possibleMatches[i];
			try {
				if (!parse(possibleMatch))
					continue;
				if (this.progressMonitor != null) {
					this.progressWorked++;
					if ((this.progressWorked % this.progressStep) == 0)
						this.progressMonitor.worked(this.progressStep);
				}
				process(possibleMatch);
				if (this.numberOfMatches > 0
						&& this.matchesToProcess[this.numberOfMatches - 1] == possibleMatch) {
					// forget last possible match as it was processed
					this.numberOfMatches--;
				}
			} finally {
				possibleMatch.cleanUp();
			}
		}
	}

	private boolean parse(PossibleMatch possibleMatch) {
		if (this.progressMonitor != null && this.progressMonitor.isCanceled())
			throw new OperationCanceledException();

		try {
			if (BasicSearchEngine.VERBOSE)
				System.out
						.println("Parsing " + possibleMatch.openable.toStringWithAncestors()); //$NON-NLS-1$

			this.parser.setNodeSet(possibleMatch.nodeSet);
			ModuleDeclaration parsedUnit = this.parser.parse(possibleMatch);
			if (parsedUnit != null) {

				// if (hasAlreadyDefinedType(parsedUnit)) return false; // skip
				// type has it is hidden so not visible
				getMethodBodies(parsedUnit, possibleMatch.nodeSet);

				// add the possibleMatch with its parsedUnit to matchesToProcess
				possibleMatch.parsedUnit = parsedUnit;
				int size = this.matchesToProcess.length;
				if (this.numberOfMatches == size)
					System
							.arraycopy(
									this.matchesToProcess,
									0,
									this.matchesToProcess = new PossibleMatch[size == 0 ? 1
											: size * 2], 0,
									this.numberOfMatches);
				this.matchesToProcess[this.numberOfMatches++] = possibleMatch;
			}
		} finally {
			this.parser.setNodeSet(null);
		}
		return true;
	}

	/**
	 * Locate the matches amongst the possible matches.
	 */
	protected void locateMatches(ScriptProject scriptProject,
			PossibleMatchSet matchSet, int expected) throws CoreException {
		PossibleMatch[] possibleMatches = matchSet
				.getPossibleMatches(scriptProject.getProjectFragments());
		int length = possibleMatches.length;
		// increase progress from duplicate matches not stored in matchSet while
		// adding...
		if (this.progressMonitor != null && expected > length) {
			this.progressWorked += expected - length;
			this.progressMonitor.worked(expected - length);
		}
		// locate matches (processed matches are limited to avoid problem while
		// using Interpreter default memory heap size)
		for (int index = 0; index < length;) {
			int max = Math.min(MAX_AT_ONCE, length - index);
			locateMatches(scriptProject, possibleMatches, index, max);
			index += max;
		}
		this.patternLocator.clear();
	}

	/**
	 * Locate the matches in the given files and report them using the search
	 * requestor.
	 */
	public void locateMatches(SearchDocument[] searchDocuments)
			throws CoreException {
		int docsLength = searchDocuments.length;
		if (BasicSearchEngine.VERBOSE) {
			System.out.println("Locating matches in documents ["); //$NON-NLS-1$
			for (int i = 0; i < docsLength; i++)
				System.out.println("\t" + searchDocuments[i]); //$NON-NLS-1$
			System.out.println("]"); //$NON-NLS-1$
		}
		// init infos for progress increasing
		int n = docsLength < 1000 ? Math.min(Math.max(docsLength / 200 + 1, 2),
				4) : 5 * (docsLength / 1000);
		this.progressStep = docsLength < n ? 1 : docsLength / n; // step
		// should
		// not be 0
		this.progressWorked = 0;
		// extract working copies
		ArrayList copies = new ArrayList();
		for (int i = 0; i < docsLength; i++) {
			SearchDocument document = searchDocuments[i];
			if (document instanceof WorkingCopyDocument) {
				copies.add(((WorkingCopyDocument) document).workingCopy);
			}
		}
		int copiesLength = copies.size();
		this.workingCopies = new org.eclipse.dltk.core.ISourceModule[copiesLength];
		copies.toArray(this.workingCopies);
		ModelManager manager = ModelManager.getModelManager();
		this.bindings = new SimpleLookupTable();
		try {
			// optimize access to zip files during search operation
			manager.cacheZipFiles();
			// initialize handle factory (used as a cache of handles so as to
			// optimize space)
			if (this.handleFactory == null)
				this.handleFactory = new HandleFactory();
			if (this.progressMonitor != null) {
				this.progressMonitor.beginTask("", searchDocuments.length); //$NON-NLS-1$
			}
			// initialize pattern for polymorphic search (ie. method reference
			// pattern)
			this.patternLocator.initializePolymorphicSearch(this);
			ScriptProject previousScriptProject = null;
			PossibleMatchSet matchSet = new PossibleMatchSet();
			Util.sort(searchDocuments, new Util.Comparer() {
				public int compare(Object a, Object b) {
					return ((SearchDocument) a).getPath().compareTo(
							((SearchDocument) b).getPath());
				}
			});
			int displayed = 0; // progress worked displayed
			String previousPath = null;
			for (int i = 0; i < docsLength; i++) {
				if (this.progressMonitor != null
						&& this.progressMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				// skip duplicate paths
				SearchDocument searchDocument = searchDocuments[i];
				searchDocuments[i] = null; // free current document
				String pathString = searchDocument.getPath();
				if (i > 0 && pathString.equals(previousPath)) {
					if (this.progressMonitor != null) {
						this.progressWorked++;
						if ((this.progressWorked % this.progressStep) == 0)
							this.progressMonitor.worked(this.progressStep);
					}
					displayed++;
					continue;
				}
				previousPath = pathString;
				Openable openable;
				org.eclipse.dltk.core.ISourceModule workingCopy = null;
				if (searchDocument instanceof WorkingCopyDocument) {
					workingCopy = ((WorkingCopyDocument) searchDocument).workingCopy;
					openable = (Openable) workingCopy;
				} else {
					openable = this.handleFactory.createOpenable(pathString,
							this.scope);
				}
				if (openable == null) {
					if (this.progressMonitor != null) {
						this.progressWorked++;
						if ((this.progressWorked % this.progressStep) == 0)
							this.progressMonitor.worked(this.progressStep);
					}
					displayed++;
					continue; // match is outside buildpath
				}
				// create new parser and lookup environment if this is a new
				// project
				IResource resource = null;
				ScriptProject scriptProject = (ScriptProject) openable
						.getScriptProject();
				resource = workingCopy != null ? workingCopy.getResource()
						: openable.getResource();
				if (resource == null)
					resource = scriptProject.getProject(); // case of a file in
				// an external jar
				if (!scriptProject.equals(previousScriptProject)) {
					// locate matches in previous project
					if (previousScriptProject != null) {
						try {
							locateMatches(previousScriptProject, matchSet, i
									- displayed);
							displayed = i;
						} catch (ModelException e) {
							// problem with buildpath in this project -> skip it
						}
						matchSet.reset();
					}
					previousScriptProject = scriptProject;
				}
				matchSet.add(new PossibleMatch(this, resource, openable,
						searchDocument));
			}
			// last project
			if (previousScriptProject != null) {
				try {
					locateMatches(previousScriptProject, matchSet, docsLength
							- displayed);
				} catch (ModelException e) {
					// problem with buildpath in last project -> ignore
				}
			}
			if (this.progressMonitor != null)
				this.progressMonitor.done();
		} finally {
			if (this.nameEnvironment != null)
				this.nameEnvironment.cleanup();
			manager.flushZipFiles();
			this.bindings = null;
		}
	}
	
	public ISourceModule[] locateModules(SearchDocument[] searchDocuments) {
		List modules = new ArrayList();
		int docsLength = searchDocuments.length;
		if (BasicSearchEngine.VERBOSE) {
			System.out.println("Locating matches in documents ["); //$NON-NLS-1$
			for (int i = 0; i < docsLength; i++)
				System.out.println("\t" + searchDocuments[i]); //$NON-NLS-1$
			System.out.println("]"); //$NON-NLS-1$
		}
		// init infos for progress increasing
		int n = docsLength < 1000 ? Math.min(Math.max(docsLength / 200 + 1, 2),
				4) : 5 * (docsLength / 1000);
		this.progressStep = docsLength < n ? 1 : docsLength / n; // step
		// should
		// not be 0
		this.progressWorked = 0;
		// extract working copies
		ArrayList copies = new ArrayList();
		for (int i = 0; i < docsLength; i++) {
			SearchDocument document = searchDocuments[i];
			if (document instanceof WorkingCopyDocument) {
				copies.add(((WorkingCopyDocument) document).workingCopy);
			}
		}
		int copiesLength = copies.size();
		this.workingCopies = new org.eclipse.dltk.core.ISourceModule[copiesLength];
		copies.toArray(this.workingCopies);
		ModelManager manager = ModelManager.getModelManager();
		this.bindings = new SimpleLookupTable();
		try {
			// optimize access to zip files during search operation
			manager.cacheZipFiles();
			// initialize handle factory (used as a cache of handles so as to
			// optimize space)
			if (this.handleFactory == null)
				this.handleFactory = new HandleFactory();
			if (this.progressMonitor != null) {
				this.progressMonitor.beginTask("", searchDocuments.length); //$NON-NLS-1$
			}
			// initialize pattern for polymorphic search (ie. method reference
			// pattern)
			this.patternLocator.initializePolymorphicSearch(this);
			Util.sort(searchDocuments, new Util.Comparer() {
				public int compare(Object a, Object b) {
					return ((SearchDocument) a).getPath().compareTo(
							((SearchDocument) b).getPath());
				}
			});
			int displayed = 0; // progress worked displayed
			String previousPath = null;
			for (int i = 0; i < docsLength; i++) {
				if (this.progressMonitor != null
						&& this.progressMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				// skip duplicate paths
				SearchDocument searchDocument = searchDocuments[i];
				searchDocuments[i] = null; // free current document
				String pathString = searchDocument.getPath();
				if (i > 0 && pathString.equals(previousPath)) {
					if (this.progressMonitor != null) {
						this.progressWorked++;
						if ((this.progressWorked % this.progressStep) == 0)
							this.progressMonitor.worked(this.progressStep);
					}
					displayed++;
					continue;
				}
				previousPath = pathString;
				Openable openable;
				org.eclipse.dltk.core.ISourceModule workingCopy = null;
				if (searchDocument instanceof WorkingCopyDocument) {
					workingCopy = ((WorkingCopyDocument) searchDocument).workingCopy;
					openable = (Openable) workingCopy;
				} else {
					openable = this.handleFactory.createOpenable(pathString,
							this.scope);
				}
				if (openable == null) {
					if (this.progressMonitor != null) {
						this.progressWorked++;
						if ((this.progressWorked % this.progressStep) == 0)
							this.progressMonitor.worked(this.progressStep);
					}
					displayed++;
					continue; // match is outside buildpath
				}
				// create new parser and lookup environment if this is a new
				// project
				IResource resource = null;
				ScriptProject scriptProject = (ScriptProject) openable
						.getScriptProject();
				resource = workingCopy != null ? workingCopy.getResource()
						: openable.getResource();
				if (resource == null)
					resource = scriptProject.getProject(); // case of a file in
				if( !modules.contains(openable)) {
					modules.add(openable);
				}
			}
			if (this.progressMonitor != null)
				this.progressMonitor.done();
		} finally {
			if (this.nameEnvironment != null)
				this.nameEnvironment.cleanup();
			manager.flushZipFiles();
			this.bindings = null;
		}
		return (ISourceModule[])modules.toArray(new ISourceModule[modules.size()]);
	}

	public SearchMatch newDeclarationMatch(IModelElement element, int accuracy,
			int offset, int length) {
		SearchParticipant participant = getParticipant();
		IResource resource = this.currentPossibleMatch.resource;
		return newDeclarationMatch(element, accuracy, offset, length,
				participant, resource);
	}

	public SearchMatch newDeclarationMatch(IModelElement element, int accuracy,
			int offset, int length, SearchParticipant participant,
			IResource resource) {
		switch (element.getElementType()) {
		// case IModelElement.PACKAGE_FRAGMENT:
		// return new PackageDeclarationMatch(element, accuracy, offset, length,
		// participant, resource);
		case IModelElement.TYPE:
			return new TypeDeclarationMatch(element, accuracy, offset, length,
					participant, resource);
			// case IModelElement.FIELD:
			// return new FieldDeclarationMatch(binding == null ? element :
			// ((ModelElement)
			// element).resolved(binding), accuracy, offset,
			// length, participant, resource);
		case IModelElement.FIELD:
			return new FieldDeclarationMatch(element, accuracy, offset, length,
					participant, resource);

		case IModelElement.METHOD:
			return new MethodDeclarationMatch(element, accuracy, offset,
					length, participant, resource);
			// case IModelElement.LOCAL_VARIABLE:
			// return new LocalVariableDeclarationMatch(element, accuracy,
			// offset, length,
			// participant, resource);
			// case IModelElement.PACKAGE_DECLARATION:
			// return new PackageDeclarationMatch(element, accuracy, offset,
			// length,
			// participant, resource);
			// case IModelElement.TYPE_PARAMETER:
			// return new TypeParameterDeclarationMatch(element, accuracy,
			// offset, length,
			// participant, resource);
		default:
			return null;
		}
	}

	public SearchMatch newFieldReferenceMatch(IModelElement enclosingElement,
			int accuracy, int offset, int length, ASTNode reference) {
		SearchParticipant participant = getParticipant();
		IResource resource = this.currentPossibleMatch.resource;
		boolean insideDocComment = false;
		boolean isReadAccess = false;
		boolean isWriteAccess = false;
		// if (enclosingBinding != null)
		// enclosingElement = ((ModelElement)
		// enclosingElement).resolved(enclosingBinding);
		if( enclosingElement instanceof IParent && reference instanceof FieldDeclaration  ) {
			IParent parent = (IParent)enclosingElement;
			IModelElement[] children;
			try {
				FieldDeclaration decl = (FieldDeclaration)reference;
				children = parent.getChildren();
				boolean found = false;
				for (int i = 0; i < children.length; i++) {
					if( children[i].getElementName().equals( decl.getName() ) && children[i] instanceof IField ) {
						enclosingElement = children[i];
						found = true;
						break;
					}
				}
				if( !found ) {
					return null;
				}
			} catch (ModelException e) {
				return null;
			}
		}
		return new FieldReferenceMatch(enclosingElement, reference, accuracy, offset,
				length, isReadAccess, isWriteAccess, insideDocComment,
				participant, resource);
	}

	public SearchMatch newLocalVariableReferenceMatch(
			IModelElement enclosingElement, int accuracy, int offset,
			int length, ASTNode reference) {
		// int bits = reference.bits;
		// boolean isCoupoundAssigned = (bits & ASTNode.IsCompoundAssigned) !=
		// 0;
		// boolean isReadAccess = isCoupoundAssigned || (bits &
		// ASTNode.IsStrictlyAssigned) == 0;
		// boolean isWriteAccess = isCoupoundAssigned || (bits &
		// ASTNode.IsStrictlyAssigned) != 0;
		// boolean insideDocComment = (bits & ASTNode.InsideJavadoc) != 0;
		// SearchParticipant participant = getParticipant();
		// IResource resource = this.currentPossibleMatch.resource;
		// return new LocalVariableReferenceMatch(enclosingElement, accuracy,
		// offset,
		// length, isReadAccess, isWriteAccess, insideDocComment,
		// participant, resource);
		return null;
	}

	public SearchMatch newMethodReferenceMatch(IModelElement enclosingElement,
			int accuracy, int offset, int length, boolean isConstructor,
			boolean isSynthetic, ASTNode reference) {
		SearchParticipant participant = getParticipant();
		IResource resource = this.currentPossibleMatch.resource;
		return new MethodReferenceMatch(enclosingElement, accuracy, offset,
				length, isConstructor, isSynthetic, false, participant,
				resource, reference);
	}

	public SearchMatch newPackageReferenceMatch(IModelElement enclosingElement,
			int accuracy, int offset, int length, ASTNode reference) {
		// SearchParticipant participant = getParticipant();
		// IResource resource = this.currentPossibleMatch.resource;
		// boolean insideDocComment = (reference.bits & ASTNode.InsideJavadoc)
		// != 0;
		// return new PackageReferenceMatch(enclosingElement, accuracy, offset,
		// length,
		// insideDocComment, participant, resource);
		return null;
	}

	public TypeReferenceMatch newTypeReferenceMatch(
			IModelElement enclosingElement, int accuracy, int offset,
			int length, ASTNode reference) {
		SearchParticipant participant = getParticipant();
		IResource resource = this.currentPossibleMatch.resource;
		return new TypeReferenceMatch(enclosingElement, accuracy, offset,
				length, false, participant, resource);
	}

	public TypeReferenceMatch newTypeReferenceMatch(
			IModelElement enclosingElement, int accuracy, ASTNode reference) {
		return newTypeReferenceMatch(enclosingElement, accuracy, reference
				.sourceStart(), reference.sourceEnd() - reference.sourceStart()
				+ 1, reference);
	}

	/*
	 * Process a compilation unit already parsed and build.
	 */
	protected void process(PossibleMatch possibleMatch) throws CoreException {
		this.currentPossibleMatch = possibleMatch;
		ModuleDeclaration unit = possibleMatch.parsedUnit;
		try {
			if (unit == null || unit.isEmpty()) {
				return;
			}
			reportMatching(unit);
		} finally {
			this.currentPossibleMatch = null;
		}
	}

	/**
	 * Called prior to the unit being resolved. Reduce the parse tree where
	 * possible.
	 */
	protected void reduceParseTree(ModuleDeclaration unit) {
		// // remove statements from methods that have no possible matching
		// nodes
		// TypeDeclaration[] types = unit.types;
		// for (int i = 0, l = types.length; i < l; i++)
		// purgeMethodStatements(types[i], true);
	}

	public SearchParticipant getParticipant() {
		return this.currentPossibleMatch.document.getParticipant();
	}

	protected void report(SearchMatch match) throws CoreException {
		long start = -1;
		if (BasicSearchEngine.VERBOSE) {
			start = System.currentTimeMillis();
			System.out.println("Reporting match"); //$NON-NLS-1$
			System.out.println("\tResource: " + match.getResource());//$NON-NLS-1$
			System.out
					.println("\tPositions: [offset=" + match.getOffset() + ", length=" + match.getLength() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			try {
				// if (this.parser != null && match.getOffset() > 0 &&
				// match.getLength() > 0 &&
				// !(match.getElement() instanceof BinaryMember)) {
				// String selection = new String(this.parser.scanner.source,
				// match.getOffset(),
				// match.getLength());
				// System.out.println("\tSelection: -->" + selection + "<--");
				// //$NON-NLS-1$
				// //$NON-NLS-2$
				// }
			} catch (Exception e) {
				// it's just for debug purposes... ignore all exceptions in this
				// area
			}
			try {
				ModelElement modelElement = (ModelElement) match.getElement();
				System.out
						.println("\tJava element: " + modelElement.toStringWithAncestors()); //$NON-NLS-1$
				if (!modelElement.exists())
					System.out
							.println("\t\tWARNING: this element does NOT exist!"); //$NON-NLS-1$
			} catch (Exception e) {
				// it's just for debug purposes... ignore all exceptions in this
				// area
			}
			if (match instanceof TypeReferenceMatch) {
				try {
					TypeReferenceMatch typeRefMatch = (TypeReferenceMatch) match;
					ModelElement local = (ModelElement) typeRefMatch
							.getLocalElement();
					if (local != null) {
						System.out
								.println("\tLocal element: " + local.toStringWithAncestors()); //$NON-NLS-1$
					}
					IModelElement[] others = typeRefMatch.getOtherElements();
					if (others != null) {
						int length = others.length;
						if (length > 0) {
							System.out.println("\tOther elements:"); //$NON-NLS-1$
							for (int i = 0; i < length; i++) {
								ModelElement other = (ModelElement) others[i];
								System.out
										.println("\t\t- " + other.toStringWithAncestors()); //$NON-NLS-1$
							}
						}
					}
				} catch (Exception e) {
					// it's just for debug purposes... ignore all exceptions in
					// this area
				}
			}
			System.out
					.println(match.getAccuracy() == SearchMatch.A_ACCURATE ? "\tAccuracy: EXACT_MATCH" //$NON-NLS-1$
							: "\tAccuracy: POTENTIAL_MATCH"); //$NON-NLS-1$
			System.out.print("\tRule: "); //$NON-NLS-1$
			if (match.isExact()) {
				System.out.println("EXACT"); //$NON-NLS-1$
			} else if (match.isEquivalent()) {
				System.out.println("EQUIVALENT"); //$NON-NLS-1$
			} else if (match.isErasure()) {
				System.out.println("ERASURE"); //$NON-NLS-1$
			} else {
				System.out.println("INVALID RULE"); //$NON-NLS-1$
			}
			System.out.println("\tRaw: " + match.isRaw()); //$NON-NLS-1$
		}
		if( this.requestor != null ) {
			this.requestor.acceptSearchMatch(match);
		}
		if (BasicSearchEngine.VERBOSE)
			this.resultCollectorTime += System.currentTimeMillis() - start;
	}

	/**
	 * Finds the accurate positions of the sequence of tokens given by
	 * qualifiedName in the source and reports a reference to this this
	 * qualified name to the search requestor.
	 */
	protected void reportAccurateTypeReference(SearchMatch match,
			ASTNode typeRef, char[] name) throws CoreException {
		// if (match.getRule() == 0)
		// return;
		// if (!encloses((IModelElement) match.getElement()))
		// return;
		// // Compute source positions of the qualified reference
		// int sourceStart = typeRef.sourceStart;
		// int sourceEnd = typeRef.sourceEnd;
		// Scanner scanner = this.parser.scanner;
		// scanner.setSource(this.currentPossibleMatch.getContents());
		// scanner.resetTo(sourceStart, sourceEnd);
		// int token = -1;
		// int currentPosition;
		// do {
		// currentPosition = scanner.currentPosition;
		// try {
		// token = scanner.getNextToken();
		// } catch (InvalidInputException e) {
		// // ignore
		// }
		// if (token == TerminalTokens.TokenNameIdentifier &&
		// this.pattern.matchesName(name, scanner.getCurrentTokenSource())) {
		// int length = scanner.currentPosition - currentPosition;
		// match.setOffset(currentPosition);
		// match.setLength(length);
		// report(match);
		// return;
		// }
		// } while (token != TerminalTokens.TokenNameEOF);
		// // Report match
		// match.setOffset(sourceStart);
		// match.setLength(sourceEnd - sourceStart + 1);
		// report(match);
	}

//	/**
//	 * Finds the accurate positions of each valid token in the source and
//	 * reports a reference to this token to the search requestor. A token is
//	 * valid if it has an accuracy which is not -1.
//	 */
//	protected void reportAccurateFieldReference(SearchMatch[] matches,
//			ExtendedVariableReference qNameRef) throws CoreException {
//		if (matches == null)
//			return; // there's nothing to accurate in this case
//		// int matchesLength = matches.length;
//		// int sourceStart = qNameRef.sourceStart;
//		// int sourceEnd = qNameRef.sourceEnd;
//		// char[][] tokens = qNameRef.tokens;
//		// // compute source positions of the qualified reference
//		// Scanner scanner = this.parser.scanner;
//		// scanner.setSource(this.currentPossibleMatch.getContents());
//		// scanner.resetTo(sourceStart, sourceEnd);
//		// int sourceLength = sourceEnd - sourceStart + 1;
//		// int refSourceStart = -1, refSourceEnd = -1;
//		// int length = tokens.length;
//		// int token = -1;
//		// int previousValid = -1;
//		// int i = 0;
//		// int index = 0;
//		// do {
//		// int currentPosition = scanner.currentPosition;
//		// // read token
//		// try {
//		// token = scanner.getNextToken();
//		// } catch (InvalidInputException e) {
//		// // ignore
//		// }
//		// if (token != TerminalTokens.TokenNameEOF) {
//		// char[] currentTokenSource = scanner.getCurrentTokenSource();
//		// boolean equals = false;
//		// while (i < length && !(equals =
//		// this.pattern.matchesName(tokens[i++],
//		// currentTokenSource))) {/* empty */
//		// }
//		// if (equals && (previousValid == -1 || previousValid == i - 2)) {
//		// previousValid = i - 1;
//		// if (refSourceStart == -1)
//		// refSourceStart = currentPosition;
//		// refSourceEnd = scanner.currentPosition - 1;
//		// } else {
//		// i = 0;
//		// refSourceStart = -1;
//		// previousValid = -1;
//		// }
//		// // read '.'
//		// try {
//		// token = scanner.getNextToken();
//		// } catch (InvalidInputException e) {
//		// // ignore
//		// }
//		// }
//		// SearchMatch match = matches[index];
//		// if (match != null && match.getRule() != 0) {
//		// if (!encloses((IModelElement) match.getElement()))
//		// return;
//		// // accept reference
//		// if (refSourceStart != -1) {
//		// match.setOffset(refSourceStart);
//		// match.setLength(refSourceEnd - refSourceStart + 1);
//		// report(match);
//		// } else {
//		// match.setOffset(sourceStart);
//		// match.setLength(sourceLength);
//		// report(match);
//		// }
//		// i = 0;
//		// }
//		// refSourceStart = -1;
//		// previousValid = -1;
//		// if (index < matchesLength - 1) {
//		// index++;
//		// }
//		// } while (token != TerminalTokens.TokenNameEOF);
//	}

	protected void reportBinaryMemberDeclaration(IResource resource,
			IMember binaryMember, int accuracy) throws CoreException {
		// ClassFile classFile = (ClassFile) binaryMember.getClassFile();
		// ISourceRange range = classFile.isOpen() ? binaryMember.getNameRange()
		// :
		// SourceMapper.UNKNOWN_RANGE;
		// if (range.getOffset() == -1) {
		// BinaryType type = (BinaryType) classFile.getType();
		// String sourceFileName = type.sourceFileName(info);
		// if (sourceFileName != null) {
		// SourceMapper mapper = classFile.getSourceMapper();
		// if (mapper != null) {
		// char[] contents = mapper.findSource(type, sourceFileName);
		// if (contents != null)
		// range = mapper.mapSource(type, contents, info, binaryMember);
		// }
		// }
		// }
		// if (resource == null)
		// resource = this.currentPossibleMatch.resource;
		// SearchMatch match = newDeclarationMatch(binaryMember,
		// binaryMemberBinding,
		// accuracy, range.getOffset(), range.getLength(),
		// getParticipant(), resource);
		// report(match);
	}

	/**
	 * Report matching in annotations.
	 */
	protected void reportMatching(Annotation[] annotations,
			IModelElement enclosingElement, MatchingNodeSet nodeSet,
			boolean matchedContainer, boolean enclosesElement)
			throws CoreException {
		// for (int i = 0, al = annotations.length; i < al; i++) {
		// Annotation annotationType = annotations[i];
		// // Look for annotation type ref
		// TypeReference typeRef = annotationType.type;
		// Integer level = (Integer) nodeSet.matchingNodes.removeKey(typeRef);
		// if (level != null && matchedContainer) {
		// this.patternLocator.matchReportReference(typeRef, enclosingElement,
		// elementBinding, level.intValue(), this);
		// }
		// // Look for attribute ref
		// MemberValuePair[] pairs = annotationType.memberValuePairs();
		// for (int j = 0, pl = pairs.length; j < pl; j++) {
		// MemberValuePair pair = pairs[j];
		// level = (Integer) nodeSet.matchingNodes.removeKey(pair);
		// if (level != null && enclosesElement) {
		// ASTNode reference = (annotationType instanceof
		// SingleMemberAnnotation) ?
		// (ASTNode) annotationType : pair;
		// this.patternLocator.matchReportReference(reference, enclosingElement,
		// pair.binding, level.intValue(), this);
		// }
		// }
		// // Look for reference inside annotation
		// ASTNode[] nodes = nodeSet.matchingNodes(annotationType.sourceStart,
		// annotationType.declarationSourceEnd);
		// if (nodes != null) {
		// if (!matchedContainer) {
		// for (int j = 0, nl = nodes.length; j < nl; j++) {
		// nodeSet.matchingNodes.removeKey(nodes[j]);
		// }
		// } else {
		// for (int j = 0, nl = nodes.length; j < nl; j++) {
		// ASTNode node = nodes[j];
		// level = (Integer) nodeSet.matchingNodes.removeKey(node);
		// if (enclosesElement) {
		// this.patternLocator.matchReportReference(node, enclosingElement,
		// elementBinding, level.intValue(), this);
		// }
		// }
		// }
		// }
		// }
	}

	/**
	 * Visit the given resolved parse tree and report the nodes that match the
	 * search pattern.
	 */
	protected void reportMatching(ModuleDeclaration unit) throws CoreException {
		MatchingNodeSet nodeSet = this.currentPossibleMatch.nodeSet;

		if (BasicSearchEngine.VERBOSE) {
			System.out.println("Report matching: "); //$NON-NLS-1$
			int size = nodeSet.matchingNodes == null ? 0
					: nodeSet.matchingNodes.elementSize;
			System.out.print("	- node set: accurate=" + size); //$NON-NLS-1$
			size = nodeSet.possibleMatchingNodesSet == null ? 0
					: nodeSet.possibleMatchingNodesSet.elementSize;
			System.out.println(", possible=" + size); //$NON-NLS-1$			

		}
		this.unitScope = null;
		if (nodeSet.matchingNodes.elementSize == 0)
			return; // no matching nodes were found
		this.methodHandles = new HashSet();
		this.fieldHandles = new HashSet();
		boolean matchedUnitContainer = (this.matchContainer & PatternLocator.COMPILATION_UNIT_CONTAINER) != 0;
		// report references in javadoc

		TypeDeclaration[] types = unit.getTypes();
		if (types != null) {
			for (int i = 0, l = types.length; i < l; i++) {
				if (nodeSet.matchingNodes.elementSize == 0)
					return; // reported all the matching nodes
				TypeDeclaration type = types[i];
				Integer level = (Integer) nodeSet.matchingNodes.removeKey(type);
				int accuracy = (level != null && matchedUnitContainer) ? level
						.intValue() : -1;
				reportMatching(type, null, accuracy, nodeSet, 1);
			}
		}
		// Visit functions
		MethodDeclaration[] methods = unit.getFunctions();
		if (methods != null) {
			for (int i = 0, l = methods.length; i < l; i++) {
				if (nodeSet.matchingNodes.elementSize == 0)
					return; // reported all the matching nodes
				MethodDeclaration method = methods[i];
				Integer level = (Integer) nodeSet.matchingNodes
						.removeKey(method);
				int accuracy = (level != null && matchedUnitContainer) ? level
						.intValue() : -1;
				reportMatching(unit, method, null, accuracy, nodeSet);
			}
		}
		// Visit global variables
		FieldDeclaration[] fields = unit.getVariables();
		if (fields != null) {
			for (int i = 0, l = fields.length; i < l; i++) {
				if (nodeSet.matchingNodes.elementSize == 0)
					return; // reported all the matching nodes
				FieldDeclaration method = fields[i];
				Integer level = (Integer) nodeSet.matchingNodes
						.removeKey(method);
				int accuracy = (level != null && matchedUnitContainer) ? level
						.intValue() : -1;
				reportMatching(unit, method, null, accuracy, nodeSet);
			}
		}

		// references in this module
		ASTNode[] nodes = nodeSet.matchingNodes(unit.sourceStart(), unit
				.sourceEnd());
		if (nodes != null) {
			if ((this.matchContainer & PatternLocator.COMPILATION_UNIT_CONTAINER) != 0) {
				ISourceModule enclosingElement = createSourceModuleHandle();
				for (int i = 0, l = nodes.length; i < l; i++) {
					ASTNode node = nodes[i];
					Integer level = (Integer) nodeSet.matchingNodes
							.removeKey(node);
					if (DLTKCore.DEBUG) {
						System.out
								.println("TODO: Searching. Add scope support.");
					}
					this.patternLocator.matchReportReference(node,
							enclosingElement, (Scope) null, level.intValue(),
							this);
				}

			}
			for (int i = 0, l = nodes.length; i < l; i++)
				nodeSet.matchingNodes.removeKey(nodes[i]);
		}

		// Clear handle cache
		this.methodHandles = null;
		this.bindings.removeKey(this.pattern);
	}

	/**
	 * type Visit the given type declaration and report the nodes that match
	 * exactly the search pattern (ie. the ones in the matching nodes set)
	 */
	protected void reportMatching(TypeDeclaration type, IModelElement parent,
			int accuracy, MatchingNodeSet nodeSet, int occurrenceCount)
			throws CoreException {
		// create type handle
		IModelElement enclosingElement = parent;
		if (enclosingElement == null) {
			enclosingElement = createTypeHandle(type.getName());
		} else if (enclosingElement instanceof IType) {
			enclosingElement = ((IType) parent).getType(type.getName());
		} else if (enclosingElement instanceof IMember) {
			IMember member = (IMember) parent;
			enclosingElement = member.getType(type.getName(), occurrenceCount);
		}
		if (enclosingElement == null)
			return;
		boolean enclosesElement = encloses(enclosingElement);
		// report the type declaration
		if (accuracy > -1 && enclosesElement) {
			int offset = type.sourceStart();
			SearchMatch match = this.patternLocator.newDeclarationMatch(type,
					enclosingElement, accuracy, type.sourceEnd() - offset + 1,
					this);
			report(match);
		}
		boolean matchedClassContainer = (this.matchContainer & PatternLocator.CLASS_CONTAINER) != 0;

		// filter out element not in hierarchy scope
		if (DLTKCore.DEBUG) {
			System.out
					.println("TODO: Searching. add variable handling here...");
		}

		boolean typeInHierarchy = true;// type.binding == null ||
		
		// Visit methods
		MethodDeclaration[] methods = type.getMethods();
		if (methods != null) {
			if (nodeSet.matchingNodes.elementSize == 0)
				return; // end as all matching nodes were reported
			for (int i = 0, l = methods.length; i < l; i++) {
				MethodDeclaration method = methods[i];
				Integer level = (Integer) nodeSet.matchingNodes
						.removeKey(method);
				int value = (level != null && matchedClassContainer) ? level
						.intValue() : -1;
				reportMatching(type, method, enclosingElement, value, true,
						nodeSet);
			}
		}
		// Visit types
		TypeDeclaration[] memberTypes = type.getTypes();
		if (memberTypes != null) {
			for (int i = 0, l = memberTypes.length; i < l; i++) {
				if (nodeSet.matchingNodes.elementSize == 0)
					return; // end as all matching nodes were reported
				TypeDeclaration memberType = memberTypes[i];
				Integer level = (Integer) nodeSet.matchingNodes
						.removeKey(memberType);
				int value = (level != null && matchedClassContainer) ? level
						.intValue() : -1;
				reportMatching(memberType, enclosingElement, value, nodeSet, 1);
			}
		}

		// Visit variables
		FieldDeclaration[] fields = type.getVariables();
		if (fields != null) {
			for (int i = 0, l = fields.length; i < l; i++) {
				if (nodeSet.matchingNodes.elementSize == 0)
					return; // reported all the matching nodes
				FieldDeclaration field = fields[i];
				Integer level = (Integer) nodeSet.matchingNodes
						.removeKey(field);
				int value = (level != null && matchedClassContainer) ? level
						.intValue() : -1;
				reportMatching(type, field, enclosingElement, value, true,
						nodeSet);
			}
		}

		// references in this type
		if (typeInHierarchy) {
			ASTNode[] nodes = nodeSet.matchingNodes(type.sourceStart(), type
					.sourceEnd());
			if (nodes != null) {
				if ((this.matchContainer & PatternLocator.METHOD_CONTAINER) != 0) {
					if (encloses(enclosingElement)) {
						for (int i = 0, l = nodes.length; i < l; i++) {
							ASTNode node = nodes[i];
							Integer level = (Integer) nodeSet.matchingNodes
									.removeKey(node);
							if (DLTKCore.DEBUG) {
								System.out
										.println("TODO: Searching. Add scope support.");
							}
							this.patternLocator.matchReportReference(node,
									enclosingElement, (Scope) null, level
											.intValue(), this);
						}
						return;
					}
				}
				for (int i = 0, l = nodes.length; i < l; i++)
					nodeSet.matchingNodes.removeKey(nodes[i]);
			}
		}
	}

	/**
	 * Visit the given method declaration and report the nodes that match
	 * exactly the search pattern (ie. the ones in the matching nodes set) Note
	 * that the method declaration has already been checked.
	 */
	protected void reportMatching(TypeDeclaration type,
			MethodDeclaration method, IModelElement parent, int accuracy,
			boolean typeInHierarchy, MatchingNodeSet nodeSet)
			throws CoreException {
		IModelElement enclosingElement = null;
		if (accuracy > -1) {
			enclosingElement = createHandle(method, parent);
			if (enclosingElement != null) { // skip if unable to find method
				// compute source positions of the selector
				// Scanner scanner = parser.scanner;
				int nameSourceStart = method.sourceStart();
				// scanner.setSource(this.currentPossibleMatch.getContents());
				// scanner.resetTo(nameSourceStart, method.sourceEnd());
				// try {
				// scanner.getNextToken();
				// } catch (InvalidInputException e) {
				// // ignore
				// }
				if (encloses(enclosingElement)) {
					SearchMatch match = null;
					if (DLTKCore.DEBUG) {
						System.out
								.println("TODO: AST Add constructor support.");
					}
					// if (method.isConstructor()) {
					// // Use type for match associated element as default
					// constructor does not
					// exist in source
					// int offset = type.sourceStart();
					// match = this.patternLocator.newDeclarationMatch(type,
					// parent, accuracy,
					// type.sourceEnd()-offset+1, this);
					// } else {
					int length = method.sourceEnd() - nameSourceStart;
					match = this.patternLocator.newDeclarationMatch(method,
							enclosingElement, accuracy, length, this);
					// }
					if (match != null) {
						report(match);
					}
				}
			}
		}

		// handle nodes for the local type first
		// if ((method.bits & ASTNode.HasLocalType) != 0) {
		// if (enclosingElement == null)
		// enclosingElement = createHandle(method, parent);
		// LocalDeclarationVisitor localDeclarationVisitor = new
		// LocalDeclarationVisitor(enclosingElement, method.binding, nodeSet);
		// try {
		// method.traverse(localDeclarationVisitor, (ClassScope) null);
		// } catch (WrappedCoreException e) {
		// throw e.coreException;
		// }
		// }

		// // report annotations
		// if (method.annotations != null) {
		// if (enclosingElement == null) {
		// enclosingElement = createHandle(method, parent);
		// }
		// reportMatching(method.annotations, enclosingElement, method.binding,
		// nodeSet,
		// true, true);
		// }

		// references in this method
		if (typeInHierarchy) {
			ASTNode[] nodes = nodeSet.matchingNodes(method.sourceStart(),
					method.sourceEnd());
			if (nodes != null) {
				if ((this.matchContainer & PatternLocator.CLASS_CONTAINER) != 0) {
					if (enclosingElement == null)
						enclosingElement = createHandle(method, parent);
					if (encloses(enclosingElement)) {
						for (int i = 0, l = nodes.length; i < l; i++) {
							ASTNode node = nodes[i];
							Integer level = (Integer) nodeSet.matchingNodes
									.removeKey(node);
							if (DLTKCore.DEBUG) {
								System.out
										.println("TODO: Searching. Add scope support.");
							}
							this.patternLocator.matchReportReference(node,
									enclosingElement, (Scope) null, level
											.intValue(), this);
						}
						return;
					}
				}
				for (int i = 0, l = nodes.length; i < l; i++)
					nodeSet.matchingNodes.removeKey(nodes[i]);
			}
		}
	}

	/**
	 * Visit the given method declaration and report the nodes that match
	 * exactly the search pattern (ie. the ones in the matching nodes set) Note
	 * that the method declaration has already been checked.
	 */
	protected void reportMatching(TypeDeclaration type, FieldDeclaration field,
			IModelElement parent, int accuracy, boolean typeInHierarchy,
			MatchingNodeSet nodeSet) throws CoreException {
		IModelElement enclosingElement = null;
		if (accuracy > -1) {
			enclosingElement = createHandle(field, parent);
			if (enclosingElement != null) { // skip if unable to find method
				// compute source positions of the selector
				// Scanner scanner = parser.scanner;
				int nameSourceStart = field.sourceStart();
				// scanner.setSource(this.currentPossibleMatch.getContents());
				// scanner.resetTo(nameSourceStart, method.sourceEnd());
				// try {
				// scanner.getNextToken();
				// } catch (InvalidInputException e) {
				// // ignore
				// }
				if (encloses(enclosingElement)) {
					SearchMatch match = null;
					if (DLTKCore.DEBUG) {
						System.out
								.println("TODO: AST Add constructor support.");
					}
					// if (method.isConstructor()) {
					// // Use type for match associated element as default
					// constructor does not
					// exist in source
					// int offset = type.sourceStart();
					// match = this.patternLocator.newDeclarationMatch(type,
					// parent, accuracy,
					// type.sourceEnd()-offset+1, this);
					// } else {
					int length = field.sourceEnd() - nameSourceStart;
					match = this.patternLocator.newDeclarationMatch(field,
							enclosingElement, accuracy, length, this);
					// }
					if (match != null) {
						report(match);
					}
				}
			}
		}

		// handle nodes for the local type first
		// if ((method.bits & ASTNode.HasLocalType) != 0) {
		// if (enclosingElement == null)
		// enclosingElement = createHandle(method, parent);
		// LocalDeclarationVisitor localDeclarationVisitor = new
		// LocalDeclarationVisitor(enclosingElement, method.binding, nodeSet);
		// try {
		// method.traverse(localDeclarationVisitor, (ClassScope) null);
		// } catch (WrappedCoreException e) {
		// throw e.coreException;
		// }
		// }

		// // report annotations
		// if (method.annotations != null) {
		// if (enclosingElement == null) {
		// enclosingElement = createHandle(method, parent);
		// }
		// reportMatching(method.annotations, enclosingElement, method.binding,
		// nodeSet,
		// true, true);
		// }

		// references in this method
		if (typeInHierarchy) {
			ASTNode[] nodes = nodeSet.matchingNodes(field.sourceStart(), field
					.sourceEnd());
			if (nodes != null) {
				if ((this.matchContainer & PatternLocator.CLASS_CONTAINER) != 0) {
					if (enclosingElement == null)
						enclosingElement = createHandle(field, parent);
					if (encloses(enclosingElement)) {
						for (int i = 0, l = nodes.length; i < l; i++) {
							ASTNode node = nodes[i];
							Integer level = (Integer) nodeSet.matchingNodes
									.removeKey(node);
							if (DLTKCore.DEBUG) {
								System.out
										.println("TODO: Searching. Add scope support.");
							}
							this.patternLocator.matchReportReference(node,
									enclosingElement, (Scope) null, level
											.intValue(), this);
						}
						return;
					}
				}
				for (int i = 0, l = nodes.length; i < l; i++)
					nodeSet.matchingNodes.removeKey(nodes[i]);
			}
		}
	}

	/**
	 * Visit the given method declaration and report the nodes that match
	 * exactly the search pattern (ie. the ones in the matching nodes set) Note
	 * that the method declaration has already been checked.
	 */
	protected void reportMatching(ModuleDeclaration module,
			FieldDeclaration field, IModelElement parent, int accuracy,
			MatchingNodeSet nodeSet) throws CoreException {
		IModelElement enclosingElement = null;
		if (accuracy > -1) {
			if (parent == null) {
				parent = createSourceModuleHandle();
			}
			enclosingElement = createHandle(field, parent);
			if (enclosingElement == null) {
				enclosingElement = createFieldHandle(field.getName());
			}
			if (enclosingElement != null) { // skip if unable to find method
				// compute source positions of the selector
				// Scanner scanner = parser.scanner;
				int nameSourceStart = field.sourceStart();
				// scanner.setSource(this.currentPossibleMatch.getContents());
				// scanner.resetTo(nameSourceStart, method.sourceEnd());
				// try {
				// scanner.getNextToken();
				// } catch (InvalidInputException e) {
				// // ignore
				// }
				if (encloses(enclosingElement)) {
					SearchMatch match = null;
					if (DLTKCore.DEBUG) {
						System.out
								.println("TODO: AST Add constructor support.");
					}
					// if (method.isConstructor()) {
					// // Use type for match associated element as default
					// constructor does not
					// exist in source
					// int offset = type.sourceStart();
					// match = this.patternLocator.newDeclarationMatch(type,
					// parent, accuracy,
					// type.sourceEnd()-offset+1, this);
					// } else {
					int length = field.sourceEnd() - nameSourceStart;
					match = this.patternLocator.newDeclarationMatch(field,
							enclosingElement, accuracy, length, this);
					// }
					if (match != null) {
						report(match);
					}
				}
			}
		}

		// handle nodes for the local type first
		// if ((method.bits & ASTNode.HasLocalType) != 0) {
		// if (enclosingElement == null)
		// enclosingElement = createHandle(method, parent);
		// LocalDeclarationVisitor localDeclarationVisitor = new
		// LocalDeclarationVisitor(enclosingElement, method.binding, nodeSet);
		// try {
		// method.traverse(localDeclarationVisitor, (ClassScope) null);
		// } catch (WrappedCoreException e) {
		// throw e.coreException;
		// }
		// }

		// // report annotations
		// if (method.annotations != null) {
		// if (enclosingElement == null) {
		// enclosingElement = createHandle(method, parent);
		// }
		// reportMatching(method.annotations, enclosingElement, method.binding,
		// nodeSet,
		// true, true);
		// }
		// references in this method
		ASTNode[] nodes = nodeSet.matchingNodes(field.sourceStart(), field
				.sourceEnd());
		if (nodes != null) {
			if (parent == null) {
				parent = createSourceModuleHandle();
			}
			if ((this.matchContainer & PatternLocator.METHOD_CONTAINER) != 0) {
				if (enclosingElement == null)
					enclosingElement = createHandle(field, parent);
				if (encloses(enclosingElement)) {
					for (int i = 0, l = nodes.length; i < l; i++) {
						ASTNode node = nodes[i];
						Integer level = (Integer) nodeSet.matchingNodes
								.removeKey(node);
						if (DLTKCore.DEBUG) {
							System.out
									.println("TODO: Searching. Add scope support.");
						}
						this.patternLocator.matchReportReference(node,
								enclosingElement, (Scope) null, level
										.intValue(), this);
					}
					return;
				}
			}
			for (int i = 0, l = nodes.length; i < l; i++)
				nodeSet.matchingNodes.removeKey(nodes[i]);
		}
	}

	protected void reportMatching(ModuleDeclaration module,
			MethodDeclaration method, IModelElement parent, int accuracy,
			MatchingNodeSet nodeSet) throws CoreException {
		IModelElement enclosingElement = null;
		if (accuracy > -1) {
			if (parent == null) {
				parent = createSourceModuleHandle();
			}
			enclosingElement = createHandle(method, parent);
			if (enclosingElement == null) {
				enclosingElement = createMethodHandle(method.getName());
			}
			if (enclosingElement != null) { // skip if unable to find method
				// compute source positions of the selector
				// Scanner scanner = parser.scanner;
				int nameSourceStart = method.sourceStart();
				// scanner.setSource(this.currentPossibleMatch.getContents());
				// scanner.resetTo(nameSourceStart, method.sourceEnd());
				// try {
				// scanner.getNextToken();
				// } catch (InvalidInputException e) {
				// // ignore
				// }
				if (encloses(enclosingElement)) {
					SearchMatch match = null;
					if (DLTKCore.DEBUG) {
						System.out
								.println("TODO: AST Add constructor support.");
					}
					// if (method.isConstructor()) {
					// // Use type for match associated element as default
					// constructor does not
					// exist in source
					// int offset = type.sourceStart();
					// match = this.patternLocator.newDeclarationMatch(type,
					// parent, accuracy,
					// type.sourceEnd()-offset+1, this);
					// } else {
					int length = method.sourceEnd() - nameSourceStart;
					match = this.patternLocator.newDeclarationMatch(method,
							enclosingElement, accuracy, length, this);
					// }
					if (match != null) {
						report(match);
					}
				}
			}
		}

		// handle nodes for the local type first
		// if ((method.bits & ASTNode.HasLocalType) != 0) {
		// if (enclosingElement == null)
		// enclosingElement = createHandle(method, parent);
		// LocalDeclarationVisitor localDeclarationVisitor = new
		// LocalDeclarationVisitor(enclosingElement, method.binding, nodeSet);
		// try {
		// method.traverse(localDeclarationVisitor, (ClassScope) null);
		// } catch (WrappedCoreException e) {
		// throw e.coreException;
		// }
		// }

		// // report annotations
		// if (method.annotations != null) {
		// if (enclosingElement == null) {
		// enclosingElement = createHandle(method, parent);
		// }
		// reportMatching(method.annotations, enclosingElement, method.binding,
		// nodeSet,
		// true, true);
		// }
		// references in this method
		ASTNode[] nodes = nodeSet.matchingNodes(method.sourceStart(), method
				.sourceEnd());
		if (nodes != null) {
			if (parent == null) {
				parent = createSourceModuleHandle();
			}
			if ((this.matchContainer & PatternLocator.METHOD_CONTAINER) != 0) {
				if (enclosingElement == null)
					enclosingElement = createHandle(method, parent);
				if (encloses(enclosingElement)) {
					for (int i = 0, l = nodes.length; i < l; i++) {
						ASTNode node = nodes[i];
						Integer level = (Integer) nodeSet.matchingNodes
								.removeKey(node);
						if (DLTKCore.DEBUG) {
							System.out
									.println("TODO: Searching. Add scope support.");
						}
						this.patternLocator.matchReportReference(node,
								enclosingElement, (Scope) null, level
										.intValue(), this);
					}
					return;
				}
			}
			for (int i = 0, l = nodes.length; i < l; i++)
				nodeSet.matchingNodes.removeKey(nodes[i]);
		}
	}

	protected IType findTypeFrom(IModelElement[] childs, String name,
			String parentName, char delimiter) {
		try {
			for (int i = 0; i < childs.length; ++i) {
				if (childs[i] instanceof IType) {
					IType type = (IType) childs[i];
					String qname = name + delimiter + type.getElementName();
					if (qname.equals(parentName)) {
						return type;
					}
					IType val = findTypeFrom(type.getChildren(), qname,
							parentName, delimiter);
					if (val != null) {
						return val;
					}
				}
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return null;
	}


}
