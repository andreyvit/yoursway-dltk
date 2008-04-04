package org.eclipse.dltk.launching.sourcelookup;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.dltk.internal.core.ScriptProject;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;

public class ScriptSourceLookupParticipant extends
		AbstractSourceLookupParticipant {

	public String getSourceName(Object object) throws CoreException {
		ScriptStackFrame frame = (ScriptStackFrame) object;

		String path = frame.getFileName().getPath();
		if (path.length() == 0) {
			return null;
		}
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			path = path.substring(1);
		}

		String root = getProjectRoot();

		// strip off the project root
		if (path.indexOf(root) != -1) {
			return path.substring(root.length() + 1);
		}

		IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
				.findFilesForLocation(new Path(path));

		IProject project = LaunchConfigurationUtils.getProject(getDirector()
				.getLaunchConfiguration());
		for (int i = 0; i < files.length; i++) {
			IFile file = files[i];
			if (file.exists()) {
				if (file.getProject().equals(project)) {
					return file.getProjectRelativePath().toString();
				}
			}
		}
		return path;
	}

	protected String getProjectRoot() throws CoreException {
		IProject project = LaunchConfigurationUtils.getProject(getDirector()
				.getLaunchConfiguration());
		return project.getLocation().toPortableString();
	}

	public Object[] findSourceElements(Object object) throws CoreException {
		Object[] elements = super.findSourceElements(object);
		if (elements.length > 0) {
			return elements;
		}
		ILaunchConfiguration launchConfiguration = this.getDirector()
				.getLaunchConfiguration();

		IProject project = LaunchConfigurationUtils
				.getProject(launchConfiguration);
		ScriptProject scriptProject = (ScriptProject) DLTKCore.create(project);

		ScriptStackFrame frame = (ScriptStackFrame) object;
		final String path = frame.getFileName().getPath();
		File file = new File(path);
		final ISourceModule[] result = new ISourceModule[] { null };
		if (file.exists()) {
			// Try to open external source module.
			scriptProject.accept(new IModelElementVisitor() {
				public boolean visit(IModelElement element) {
					if (element.getElementType() == IModelElement.PROJECT_FRAGMENT) {
						IProjectFragment fragment = (IProjectFragment) element;
						if (!fragment.isExternal()) {
							return false;
						}
					}
					if (element.getElementType() == IModelElement.SOURCE_MODULE) {
						ISourceModule module = (ISourceModule) element;
						IPath modulePath = module.getPath();
						if (path.equals(modulePath.toOSString())) {
							result[0] = module;
						}

						return false;
					}
					return true;
				}
			});
		}
		if (result[0] != null) {
			return result;
		}
		return new Object[] { new DBGPSourceModule(scriptProject, frame
				.getFileName().getPath(), DefaultWorkingCopyOwner.PRIMARY,
				frame) };
//		return elements;
	}
}
