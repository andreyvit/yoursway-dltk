package org.eclipse.dltk.launching;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IPersistableSourceLocator;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.util.HandleFactory;
import org.eclipse.dltk.internal.debug.core.model.ScriptStackFrame;

public class ScriptSourceLookupDirector implements IPersistableSourceLocator {

	public ScriptSourceLookupDirector() {
	}

	public Object getSourceElement(IStackFrame stackFrame) {
		if (stackFrame instanceof ScriptStackFrame) {

			ScriptStackFrame sf = (ScriptStackFrame) stackFrame;
			URI uri = sf.getFileName();

			String pathname = uri.getPath();

			if (Platform.getOS().equals(Platform.OS_WIN32)) {
				pathname = pathname.substring(1);
			}

			// Lets try to locate model element.
			try {
				final ILaunchConfiguration configuration = sf.getLaunch()
						.getLaunchConfiguration();

				String projectName;

				projectName = configuration.getAttribute(
						ScriptLaunchConfigurationConstants.ATTR_PROJECT_NAME,
						(String) null);

				if (projectName != null) {

					IProject project = ResourcesPlugin.getWorkspace().getRoot()
							.getProject(projectName);
					IScriptProject prj = DLTKCore.create(project);
					IDLTKSearchScope scope = SearchEngine
							.createSearchScope(new IModelElement[] { prj });
					HandleFactory fac = new HandleFactory();
					Openable op = fac.createOpenable(pathname, scope);
					
					if (op != null && op.exists() && op instanceof IStorage) {
						return op;
					}
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File file = new File(pathname);

			IContainer container = ResourcesPlugin.getWorkspace().getRoot()
					.getContainerForLocation(new Path(file.getParent()));

			if (container != null) {
				IResource resource = container.findMember(file.getName());

				if (resource instanceof IFile) {
					return (IFile) resource;
				}
			} else {
				return file;
			}
		}

		return null;
	}

	public String getMemento() throws CoreException {
		return null;
	}

	public void initializeDefaults(ILaunchConfiguration configuration)
			throws CoreException {

	}

	public void initializeFromMemento(String memento) throws CoreException {

	}
}