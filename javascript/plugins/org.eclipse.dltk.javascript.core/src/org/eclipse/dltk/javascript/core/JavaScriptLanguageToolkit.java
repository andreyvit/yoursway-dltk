package org.eclipse.dltk.javascript.core;

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.codeassist.ISelectionEngine;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.DLTKProblemReporter;
import org.eclipse.dltk.compiler.problem.DefaultProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.search.DLTKSearchParticipant;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.internal.javascript.parser.JavaScriptSourceElementParser;
import org.eclipse.dltk.internal.javascript.parser.JavaScriptSourceParser;
import org.eclipse.dltk.javascript.internal.core.codeassist.JavaScriptCallProcessor;
import org.eclipse.dltk.javascript.internal.core.codeassist.JavaScriptCalleeProcessor;
import org.eclipse.dltk.javascript.internal.core.codeassist.completion.JavaScriptCompletionEngine;
import org.eclipse.dltk.javascript.internal.core.codeassist.selection.JavaScriptSelectionEngine;

public class JavaScriptLanguageToolkit implements IDLTKLanguageToolkit {
	private static JavaScriptLanguageToolkit sInstance = new JavaScriptLanguageToolkit();

	public JavaScriptLanguageToolkit() {
	}

	public IProblemReporter createProblemReporter(IResource resource,
			IProblemFactory factory) {

		try {
			resource.deleteMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return new DLTKProblemReporter(resource, factory);
	}

	public IProblemFactory createProblemFactory() {
		return new DefaultProblemFactory();
	}

	public ISourceElementParser createSourceElementParser(
			ISourceElementRequestor requestor,
			IProblemReporter problemReporter, Map options) throws CoreException {
		return new JavaScriptSourceElementParser(requestor, problemReporter);
	}

	public IStatus validateSourceModule(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, JavaScriptPlugin.PLUGIN_ID, -1,
					Messages.convention_unit_nullName, null);
		}
		if (!isJavaScriptLikeFileName(name)) {
			return new Status(IStatus.ERROR, JavaScriptPlugin.PLUGIN_ID, -1,
					MessageFormat
							.format(Messages.convention_unit_notScriptName,
									new String[] { getJavaScriptExtension(),
											"Python" }), null);
		}
		return IModelStatus.VERIFIED_OK;
	}

	private String getJavaScriptExtension() {
		return "js";
	}

	private boolean isJavaScriptLikeFileName(String name) {
		// TODO: Add more correct checking here.
		if (name.endsWith("." + getJavaScriptExtension())) {
			return true;
		}
		return false;
	}

	public boolean languageSupportZIPBuildpath() {
		return false;
	}

	public boolean validateSourcePackage(IPath path) {
		return true;
	}

	public String getNatureID() {
		return JavaScriptNature.NATURE_ID;
	}

	public ICompletionEngine createCompletionEngine(
			ISearchableEnvironment environment, CompletionRequestor requestor,
			Map options, IDLTKProject project) {
		return new JavaScriptCompletionEngine(environment, requestor, options,
				project);
	}

	public String getPartitioningID() {
		return IJavaScriptConstants.JS_PARTITIONING;
	}

	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new JavaScriptMatchLocatorParser(locator);
	}

	public IStatus validateSourceModule(IResource resource) {
		return validateSourceModule(resource.getName());
	}

	public IStatus validateSourceModule(IPath resource) {
		return validateSourceModule(resource.lastSegment());
	}

	public IStatus validateSourceModuleName(String str) {
		return validateSourceModule(str);
	}

	public String getEditorID(Object inputElement) {
		return "org.eclipse.dltk.javascript.ui.editor.JavascriptEditor";
	}

	public ModuleDeclaration createFullAST(ISourceModule module) {
		JavaScriptSourceParser parser = new JavaScriptSourceParser();
		try {
			return parser.parse(module.getSource());
		} catch (ModelException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ISelectionEngine createSelectionEngine(
			ISearchableEnvironment environment, Map options) {
		return new JavaScriptSelectionEngine(options);
	}

	public SourceIndexerRequestor createSourceRequestor() {
		return new SourceIndexerRequestor();
	}

	public DLTKSearchParticipant createSearchParticipant() {
		// TODO Auto-generated method stub
		return null;
	}

	public static IDLTKLanguageToolkit getDefault() {
		return sInstance;
	}

	public MatchLocator createMatchLocator(SearchPattern pattern,
			SearchRequestor requestor, IDLTKSearchScope scope,
			SubProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICalleeProcessor createCalleeProcessor(IMethod method,
			IProgressMonitor monitor, IDLTKSearchScope scope) {
		return new JavaScriptCalleeProcessor(method,monitor,scope);
	}

	public String getDelimeterReplacerString() {
		return ".";
	}

	public ICallProcessor createCallProcessor() {
		return new JavaScriptCallProcessor();
	}

	public String[] getLanguageFileExtensions() {
		return new String[] { "js" };
	}

	public IType[] getParentTypes(IType type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getLanguageName()
	{
		return "Javascript";
	}
}
