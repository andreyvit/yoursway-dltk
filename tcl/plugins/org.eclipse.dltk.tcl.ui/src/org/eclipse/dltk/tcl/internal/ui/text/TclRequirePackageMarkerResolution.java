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
import org.eclipse.dltk.internal.core.ScriptProject;
import org.eclipse.dltk.internal.core.SetBuildpathOperation;
import org.eclipse.dltk.internal.core.builder.State;
import org.eclipse.dltk.launching.IInterpreterInstall;
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
			PackagesManager manager = PackagesManager.getInstance();
			IPath[] paths = manager.getAllPaths(pkgName, install);
			try {
				Set spaths = new HashSet();
				List newBuildPath = new ArrayList();
				final IBuildpathEntry[] buildpath = project.getRawBuildpath();

				for (int i = 0; i < buildpath.length; i++) {
					if (buildpath[i].isExternal()
							&& buildpath[i].getEntryKind() == IBuildpathEntry.BPE_LIBRARY) {
						spaths.add(buildpath[i].getPath());
					}
					newBuildPath.add(buildpath[i]);
				}
				for (int i = 0; i < paths.length; i++) {
					// IPath path = new Path(
					// TclPackageBuildpathContainerInitializer.CONTAINER_PATH
					// ).append(pkgName);
					IPath path = paths[i];
					if (!spaths.contains(path.toString())) {
						IBuildpathAttribute attr = DLTKCore
								.newBuildpathAttribute("interpreter_library",
										"true");
						IBuildpathEntry entry = DLTKCore.newLibraryEntry(path,
								BuildpathEntry.NO_ACCESS_RULES,
								new IBuildpathAttribute[] { attr },
								false/* not exported */, true);
						newBuildPath.add(entry);
					}
				}
				final IBuildpathEntry[] newBP = (IBuildpathEntry[]) newBuildPath
						.toArray(new IBuildpathEntry[newBuildPath.size()]);
				// monitor.beginTask("", 100);
				SetBuildpathOperation op = new SetBuildpathOperation(
						(ScriptProject) project, buildpath, newBP, true, true,
						true);
				try {
					op.run(new NullProgressMonitor()/*
													 * new
													 * SubProgressMonitor(monitor,
													 * 50)
													 */);
					// We need to rebuild all resources
					IProject prj = project.getProject();
					State builtState = (State) ModelManager.getModelManager()
							.getLastBuiltState(project.getProject(),
									new NullProgressMonitor());
					if (builtState != null) {
						builtState.setNoCleanExternalFolders();
					}
				} catch (CoreException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			} catch (ModelException e1) {
				if (DLTKCore.DEBUG) {
					e1.printStackTrace();
				}
			}
			// monitor.done();
			// return Status.OK_STATUS;
			// }
			// };
			// j.setUser(true);
			// j.schedule();
		}
	}
}