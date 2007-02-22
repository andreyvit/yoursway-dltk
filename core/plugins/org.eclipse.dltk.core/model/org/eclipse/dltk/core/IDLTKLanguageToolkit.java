package org.eclipse.dltk.core;

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.codeassist.ISelectionEngine;
import org.eclipse.dltk.compiler.IProblemFactory;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.core.search.DLTKSearchParticipant;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;


public interface IDLTKLanguageToolkit {

	// Note, that DLTKProblemReporter can be changed to interface
	IProblemReporter createProblemReporter(IResource resource,
			IProblemFactory factory);

	// This method should be removed later
	IProblemFactory createProblemFactory();

	ISourceElementParser createSourceElementParser(
			ISourceElementRequestor requestor,
			IProblemReporter problemReporter, Map options)
			throws CoreException;

	/*
	 * Validation of language toolkit resources
	 */

	IStatus validateSourceModule(IResource resource);

	IStatus validateSourceModule(IPath path);

	IStatus validateSourceModuleName(String name);

	boolean validateSourcePackage(IPath path);

	/*
	 * Different stuff
	 */
	
	boolean languageSupportZIPBuildpath();

	String getNatureID();

	String getPartitioningID();

	String getEditorID(Object inputElement);

	ICompletionEngine createCompletionEngine(
			ISearchableEnvironment environment, CompletionRequestor requestor,
			Map options, IDLTKProject project);

	IMatchLocatorParser createMatchParser(MatchLocator locator);
	
	/**
	 * AST Should contain Method invocations and other stuff.
	 * @param module
	 * @return
	 */
	ModuleDeclaration createFullAST(ISourceModule module);

	ISelectionEngine createSelectionEngine(ISearchableEnvironment environment, Map options);

	SourceIndexerRequestor createSourceRequestor();
	
	// Is this method really need?
	DLTKSearchParticipant createSearchParticipant();

	MatchLocator createMatchLocator(SearchPattern pattern, SearchRequestor requestor, IDLTKSearchScope scope, SubProgressMonitor monitor);

	
	ICalleeProcessor createCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope);
	ICallProcessor createCallProcessor();
	
	String getDelimeterReplacerString();
	
	
	String[] getLanguageFileExtensions();
}