package org.eclipse.dltk.tcl.internal.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IScriptProject;

public class TclPackageBuildpathContainerInitializer extends
		BuildpathContainerInitializer {

	public static final String CONTAINER_PATH = "org.eclipse.dltk.tcl.core.PACKAGE";

	public TclPackageBuildpathContainerInitializer() {
	}

	public void initialize(IPath containerPath, IScriptProject project)
			throws CoreException {
		if (containerPath.segment(0).equals(CONTAINER_PATH)
				&& containerPath.segmentCount() == 2) {
			TclPackageBuildpathContainer container = new TclPackageBuildpathContainer(
					containerPath.segment(1), project);
			DLTKCore.setBuildpathContainer(containerPath,
					new IScriptProject[] { project },
					new IBuildpathContainer[] { container }, null);
		}
	}
}
