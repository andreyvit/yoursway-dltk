package org.eclipse.dltk.ruby.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.codeassist.ISelectionEngine;
import org.eclipse.dltk.compiler.DLTKProblemReporter;
import org.eclipse.dltk.compiler.DefaultProblemFactory;
import org.eclipse.dltk.compiler.IProblemFactory;
import org.eclipse.dltk.compiler.IProblemReporter;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
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
import org.eclipse.dltk.ruby.internal.core.codeassist.RubyCompletionEngine;
import org.eclipse.dltk.ruby.internal.core.codeassist.RubySelectionEngine;
import org.eclipse.dltk.ruby.internal.core.search.RubyMatchLocator;
import org.eclipse.dltk.ruby.internal.core.search.RubyMatchLocatorParser;
import org.eclipse.dltk.ruby.internal.parser.JRubySourceParser;
import org.eclipse.dltk.ruby.internal.parser.RubySourceElementParser;
import org.eclipse.dltk.ruby.internal.typehierarchy.RubyTypeHierarchyEngine;

public class RubyLanguageToolkit implements IDLTKLanguageToolkit  {
	private static RubyLanguageToolkit sToolkit = new RubyLanguageToolkit();
	public RubyLanguageToolkit() {

	}

	public IProblemFactory createProblemFactory() {
		return new DefaultProblemFactory();
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

	public ISourceElementParser createSourceElementParser(
			ISourceElementRequestor requestor,
			IProblemReporter problemReporter, Map options)
			throws CoreException {

		return new RubySourceElementParser(requestor, problemReporter);
	}

	public IStatus validateSourceModule(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, RubyPlugin.PLUGIN_ID, -1,
					Messages.convention_unit_nullName, null);
		}
		if (!isRubyLikeFileName(name)) {
			return new Status(IStatus.ERROR, RubyPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] { getRubyExtension(), "Ruby" }),
					null);
		}
		return IModelStatus.VERIFIED_OK;
	}

	private String getRubyExtension() {
		return "rb";
	}

	private boolean isRubyLikeFileName(String name) {
		if (name.endsWith("." + getRubyExtension())) {
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
		return RubyNature.NATURE_ID;
	}

	public ICompletionEngine createCompletionEngine(
			ISearchableEnvironment environment, CompletionRequestor requestor,
			Map options, IDLTKProject project) {
		return new RubyCompletionEngine(environment, requestor, options, project);
	}

	public String getPartitioningID() {
		return IRubyConstants.RUBY_PARTITIONING;
	}

	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new RubyMatchLocatorParser(locator);
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
		return "org.eclipse.dltk.ruby.ui.editor.RubyEditor";
	}

	public ModuleDeclaration createFullAST(ISourceModule module) {
		JRubySourceParser parser = new JRubySourceParser(null);
		try {
			return parser.parse(module.getSource());
		} catch (ModelException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ISelectionEngine createSelectionEngine(ISearchableEnvironment environment, Map options) {
		return new RubySelectionEngine(environment, options, this);
	}

	public SourceIndexerRequestor createSourceRequestor() {		
		return new SourceIndexerRequestor();
	}

	public DLTKSearchParticipant createSearchParticipant() {
		// TODO Auto-generated method stub
		return null;
	}

	public MatchLocator createMatchLocator(SearchPattern pattern, SearchRequestor requestor, IDLTKSearchScope scope, SubProgressMonitor monitor) {
		return new RubyMatchLocator(pattern, requestor, scope, monitor );
	}

	public ICalleeProcessor createCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope) {
		// TODO Auto-generated method stub
		return null;
	}

	public ICallProcessor createCallProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDelimeterReplacerString() {
		return ".";
	}

	public static IDLTKLanguageToolkit getDefault() {	
		return sToolkit;
	}

	public String[] getLanguageFileExtensions() {		
		return  new String[] {"rb"};
	}

	public IType[] getParentTypes(IType type) {
		return RubyTypeHierarchyEngine.locateSuperTypes(type, new NullProgressMonitor());
	}
}
