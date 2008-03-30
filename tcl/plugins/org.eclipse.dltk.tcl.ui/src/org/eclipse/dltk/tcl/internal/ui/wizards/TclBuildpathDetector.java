package org.eclipse.dltk.tcl.internal.ui.wizards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.ui.wizards.BuildpathDetector;
import org.eclipse.dltk.launching.InterpreterContainerHelper;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.internal.core.packages.TclCheckBuilder;

public class TclBuildpathDetector extends BuildpathDetector {
	private Set packagesInBuild;
	private Set packageNamesInProject;

	public TclBuildpathDetector(IProject project, IDLTKLanguageToolkit toolkit)
			throws CoreException {
		super(project, toolkit);
		packagesInBuild = new HashSet();
		packageNamesInProject = new HashSet();
	}

	protected void addInterpreterContainer(ArrayList cpEntries) {
		cpEntries
				.add(InterpreterContainerHelper.createPackagesContainer(
						packagesInBuild, new Path(
								ScriptRuntime.INTERPRETER_CONTAINER)));
	}

	protected void processSources(List correctFiles, SubProgressMonitor sub) {
		sub.beginTask("Analysing", correctFiles.size());
		int count = 0;
		for (Iterator iterator = correctFiles.iterator(); iterator.hasNext();) {
			IFile object = (IFile) iterator.next();
			sub.subTask("Analysing " + "("
					+ String.valueOf(correctFiles.size() - count) + "):"
					+ object.getName());
			processModule(object);
			count++;
		}
		sub.done();
	}

	protected boolean processModule(IFile file) {
		if (super.visitSourceModule(file)) {
			ISourceModule module = DLTKCore.createSourceModuleFrom(file);
			if (module.exists()) {
				ModuleDeclaration moduleDeclaration = SourceParserUtil
						.getModuleDeclaration(module, null);
				try {
					TclCheckBuilder.fillPackagesDeclarations(moduleDeclaration,
							null, packagesInBuild, packageNamesInProject);
				} catch (Exception e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}
}
