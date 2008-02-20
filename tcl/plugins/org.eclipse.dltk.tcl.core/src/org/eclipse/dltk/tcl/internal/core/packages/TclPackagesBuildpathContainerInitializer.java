package org.eclipse.dltk.tcl.internal.core.packages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IScriptProject;

public class TclPackagesBuildpathContainerInitializer extends
		BuildpathContainerInitializer {

	public static final String CONTAINER_PATH = "org.eclipse.dltk.tcl.core.PACKAGES";

	public TclPackagesBuildpathContainerInitializer() {
	}

	public void initialize(IPath containerPath, IScriptProject project)
			throws CoreException {
		if (containerPath.segment(0).equals(CONTAINER_PATH)) {
			TclPackagesBuildpathContainer container = new TclPackagesBuildpathContainer(containerPath, project);
			DLTKCore.setBuildpathContainer(containerPath,
					new IScriptProject[] { project },
					new IBuildpathContainer[] { container }, null);
		}
	}
}
