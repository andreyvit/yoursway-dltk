package org.eclipse.dltk.launching.sourcelookup.containers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourceContainerType;
import org.eclipse.debug.core.sourcelookup.containers.CompositeSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.dltk.core.DLTKContentTypeManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;

public class ScriptProjectSourceContainer extends CompositeSourceContainer {

	public static final String TYPE_ID = "org.eclipse.dltk.launching.sourceContainer.scriptProject";

	private IScriptProject project;

	private ISourceContainer[] sourceFolders;

	private ISourceContainer[] others;

	public ScriptProjectSourceContainer(IScriptProject project) {
		this.project = project;
	}

	/*
	 * @see org.eclipse.debug.core.sourcelookup.containers.CompositeSourceContainer#createSourceContainers()
	 */
	protected ISourceContainer[] createSourceContainers() throws CoreException {
		List containers = new ArrayList();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (project.getProject().isOpen()) {
			IBuildpathEntry[] entries = project.getRawBuildpath();
			for (int i = 0; i < entries.length; i++) {
				IBuildpathEntry entry = entries[i];
				switch (entry.getEntryKind()) {
				case IBuildpathEntry.BPE_SOURCE:
					IPath path = entry.getPath();
					IResource resource = root.findMember(path);
					if (resource instanceof IContainer) {
						containers.add(new FolderSourceContainer(
								(IContainer) resource, false));
					}
					break;
				}
			}
			
			// XXX: include project root for source lookup as well?
//			IPath projectRoot = project.getProject().getFullPath();
//			if (!containers.contains(projectRoot)) {				
//				containers.add(projectRoot);
//			}
		}
		
		// cache the script source folders to search for script like files
		sourceFolders = (ISourceContainer[]) containers
				.toArray(new ISourceContainer[containers.size()]);
		ISourceContainer theProject = new ProjectSourceContainer(project
				.getProject(), false);

		others = new ISourceContainer[] { theProject };
		containers.add(theProject);

		return (ISourceContainer[]) containers
				.toArray(new ISourceContainer[containers.size()]);
	}

	public IScriptProject getScriptProject() {
		return this.project;
	}

	/*
	 * @see org.eclipse.debug.core.sourcelookup.ISourceContainer#getName()
	 */
	public String getName() {
		return project.getElementName();
	}

	/*
	 * @see org.eclipse.debug.core.sourcelookup.ISourceContainer#getType()
	 */
	public ISourceContainerType getType() {
		return getSourceContainerType(TYPE_ID);
	}

	public Object[] findSourceElements(String name) throws CoreException {
		// force container initialization
		getSourceContainers();

		if (isScriptLikeFile(name)) {
			Object[] objects = findSourceElements(name, sourceFolders);
			List filtered = null;
			for (int i = 0; i < objects.length; i++) {
				Object object = objects[i];
				if (object instanceof IResource) {
					if (!project.isOnBuildpath((IResource) object)) {
						if (filtered == null) {
							filtered = new ArrayList(objects.length);
							for (int j = 0; j < objects.length; j++) {
								filtered.add(objects[j]);
							}
						}
						filtered.remove(object);
					}
				}
			}
			if (filtered == null) {
				return objects;
			}
			return filtered.toArray();
		}

		return findSourceElements(name, others);
	}

	/*
	 * @see org.eclipse.debug.core.sourcelookup.containers.CompositeSourceContainer#dispose()
	 */
	public void dispose() {
		others = null;
		sourceFolders = null;
		super.dispose();
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return project.hashCode();
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ScriptProjectSourceContainer) {
			return project.equals(((ScriptProjectSourceContainer) obj)
					.getScriptProject());
		}
		return super.equals(obj);
	}
	
	private boolean isScriptLikeFile(String name)
	{
		try {
			IDLTKLanguageToolkit toolkit = DLTKLanguageManager.getLanguageToolkit(project);
			if( toolkit != null ) {
				return false;
			}
			return DLTKContentTypeManager.isValidFileNameForContentType(toolkit, name);			
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return false;
		}
	}
}
