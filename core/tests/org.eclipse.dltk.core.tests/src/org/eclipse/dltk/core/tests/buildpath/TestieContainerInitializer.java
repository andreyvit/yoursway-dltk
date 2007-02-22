package org.eclipse.dltk.core.tests.buildpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IDLTKProject;


public class TestieContainerInitializer extends BuildpathContainerInitializer {
	public void initialize(IPath containerPath, IDLTKProject project) throws CoreException {
		int size = containerPath.segmentCount();
		IPath path = containerPath.removeFirstSegments(1);
		path = path.makeAbsolute();
		if (size > 0) {
			TestieContainer container = new TestieContainer(path);
			DLTKCore.setBuildpathContainer(containerPath, new IDLTKProject[] {
				project
			}, new IBuildpathContainer[] {
				container
			}, null);
		}
	}
}
