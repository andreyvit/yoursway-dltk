/**
 * 
 */
package org.eclipse.dltk.tcl.internal.ui.text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathAttribute;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.core.builder.State;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterContainerHelper;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.tcl.internal.core.packages.PackagesManager;
import org.eclipse.ui.IMarkerResolution;

final class TclRequirePackageMarkerResolution implements IMarkerResolution {
	private String pkgName;
	private IScriptProject project;

	public TclRequirePackageMarkerResolution(String pkgName,
			IScriptProject scriptProject) {
		this.pkgName = pkgName;
		this.project = scriptProject;
	}

	public String getLabel() {
		return "Add package " + this.pkgName + " to buildpath.";
	}

	public void run(final IMarker marker) {
		final IInterpreterInstall install;
		try {
			install = ScriptRuntime.getInterpreterInstall(project);
		} catch (CoreException e1) {
			if (DLTKCore.DEBUG) {
				e1.printStackTrace();
			}
			return;
		}
		if (install != null) {
			// Ru = new Job("Add package dependencies") {
			// protected IStatus run(IProgressMonitor monitor) {
			try {
				marker.delete();
			} catch (CoreException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
			Set names = InterpreterContainerHelper.getInterpreterContainerDependencies(project);
			names.add(pkgName);
			InterpreterContainerHelper.setInterpreterContainerDependencies(project, names);
		}
	}
}