package org.eclipse.dltk.tcl.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.codeassist.ICompletionEngine;
import org.eclipse.dltk.codeassist.ISelectionEngine;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.problem.DefaultProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemFactory;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.ISearchableEnvironment;
import org.eclipse.dltk.core.ISourceElementParser;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.DLTKSearchParticipant;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.internal.core.util.Messages;
import org.eclipse.dltk.tcl.internal.core.TclCalleeProcessor;
import org.eclipse.dltk.tcl.internal.core.TclSourceIndexerRequestor;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclCompletionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclSelectionEngine;
import org.eclipse.dltk.tcl.internal.core.search.TclCallProcessor;
import org.eclipse.dltk.tcl.internal.core.search.TclMatchLocator;
import org.eclipse.dltk.tcl.internal.parser.TclSourceElementParser;
import org.eclipse.dltk.tcl.internal.problem.TclProblemReporter;


public class TclLanguageToolkit implements IDLTKLanguageToolkit {
	private static String[] patterns = {
			"#!/usr/bin/tclsh",
			"#!/usr/bin/expect",
			"# ;;; Local Variable: \\*\\*\\*\\s*\r*\n# ;;; mode: tcl \\*\\*\\*\\s*\r*\n# ;;; End: \\*\\*\\*",
			"#!/bin/sh\\s*\r*\n#.*\\\\s*\r*\nexec .*tclsh .*",
			"#!/bin/sh\\s*\r*\n#.*\\\\s*\r*\nexec .*expect .*",
			"#!/bin/sh\\s*\r*\n#.*\\\\s*\r*\nexec .*wish.*"};
	private static IDLTKLanguageToolkit sInstance = new TclLanguageToolkit();

	public String[] getLanguageFileExtensions() {
		return new String[] {"tcl", "exp"};
	}

	private IStatus isTclHeadered(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int size = (int)file.length(); //i hope, that size is covertable to int
			char buf[] = new char[size + 1];
			reader.read(buf);

			String header = new String(buf);

			if (header != null) {
				for (int i = 0; i < patterns.length; i++) {
					Pattern p = Pattern.compile(patterns[i], Pattern.MULTILINE);
					Matcher m = p.matcher(header);
					if (m.find()) {
						return IModelStatus.VERIFIED_OK;
					}
				}
			}
		} catch (FileNotFoundException e) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					"Can't open file", null);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					"Can't read file", null);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// Nothing to do
				}
			}
		}

		return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
				"Header not found", null);
	}


	private boolean isSureNotTCLFile(IPath path) {
		String name = path.toOSString();
		String[] exts = { "so", "a", "la", "c", "h" };
		for( int i = 0; i < exts.length; ++i ) {
			if (name.endsWith("." + exts[i])) {
				return true;
			}
		}
		if(Platform.getOS().equals(Platform.OS_LINUX ) ) {
			name = path.lastSegment();
			if( name.startsWith("lib") && Character.isDigit(name.charAt(name.length() -1 ))) {
				return true;
			}
		}
		return false;
	}
	private boolean isTCLLikeFileName(String name) {
		String[] exts = getLanguageFileExtensions();
		for( int i = 0; i < exts.length; ++i ) {
			if (name.endsWith("." + exts[i])) {
				return true;
			}
		}
		if( name.toLowerCase().equals("tclindex")) {
			return true;
		}
		return false;
	}

	public TclLanguageToolkit() {
	}

	public IProblemReporter createProblemReporter(IResource resource,
			IProblemFactory factory) {
		return new TclProblemReporter(resource, factory);
	}

	public IProblemFactory createProblemFactory() {
		return new DefaultProblemFactory();
	}

	public ISourceElementParser createSourceElementParser(
			ISourceElementRequestor requestor,
			IProblemReporter problemReporter, Map options)
			throws CoreException {

		return new TclSourceElementParser(requestor,
				(TclProblemReporter) problemReporter);
	}

	public boolean languageSupportZIPBuildpath() {
		return false;
	}

	public IStatus validateSourceModuleName(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					Messages.convention_unit_nullName, null);
		}
		if (!isTCLLikeFileName(name)) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] { getLanguageFileExtensions().toString(), "Tcl" }), null);
		}
		return IModelStatus.VERIFIED_OK;
	}

	public IStatus validateSourceModule(IPath path) {
		IStatus status = validateSourceModuleName(path.lastSegment());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (isSureNotTCLFile(path)) {
			return new Status(IStatus.ERROR, TclPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] { getLanguageFileExtensions().toString(), "Tcl" }), null);
		}

		if (isTclHeadered(path.toFile()) == IModelStatus.VERIFIED_OK)
			return IModelStatus.VERIFIED_OK;

		return status;
	}

	public IStatus validateSourceModule(IResource resource) {
		IStatus status = validateSourceModuleName(resource.getName());

		if (status == IModelStatus.VERIFIED_OK)
			return status;

		if (resource != null) {
			IPath path = resource.getRawLocation();

			if (path == null) {
				return status;
			}

			if (isTclHeadered(path.toFile()) == IModelStatus.VERIFIED_OK) {
				return IModelStatus.VERIFIED_OK;
			}
		}
		return status;
	}

	public boolean validateSourcePackage(IPath path) {
		File file = new File(path.toOSString());
		if (file != null && file.isDirectory()) {
			String members[] = file.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (name.toLowerCase().equals("pkgindex.tcl")
							|| name.toLowerCase().equals("tclindex")) {
						return true;
					}
					return false;
				}
			});
			if (members != null && members.length > 0) {
				return true;
			}
		}
		return false;
	}

	public ICompletionEngine createCompletionEngine(
			ISearchableEnvironment environment, CompletionRequestor requestor,
			Map options, IDLTKProject project) {
		return new TclCompletionEngine(environment, requestor, options, project);
	}

	public IMatchLocatorParser createMatchParser(MatchLocator locator) {
		return new TclMatchLocatorParser(locator);
	}

	public String getPartitioningID() {
		return TclConstants.TCL_PARTITIONING;
	}

	public String getNatureID() {
		return TclNature.NATURE_ID;
	}

	public String getEditorID(Object inputElement) {
		return "org.eclipse.dltk.tcl.ui.editor.TclEditor";
	}

	public ISelectionEngine createSelectionEngine(ISearchableEnvironment environment, Map options) {
		return new TclSelectionEngine(environment, options, this);
	}
	public SourceIndexerRequestor createSourceRequestor() {
		return new TclSourceIndexerRequestor();
	}

	public DLTKSearchParticipant createSearchParticipant() {
		return null;
	}

	public static IDLTKLanguageToolkit getDefault() {
		return sInstance;
	}

	public MatchLocator createMatchLocator(SearchPattern pattern, SearchRequestor requestor, IDLTKSearchScope scope, SubProgressMonitor monitor) {
		return new TclMatchLocator(pattern, requestor, scope, monitor);
	}

	public ICalleeProcessor createCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope) {
		return new TclCalleeProcessor( method, monitor, scope );
	}

	public String getDelimeterReplacerString() {
		return "::";
	}

	public ICallProcessor createCallProcessor() {
		return new TclCallProcessor();
	}

	public IType[] getParentTypes(IType type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getLanguageName()
	{
		return "Tcl";
	}
}
