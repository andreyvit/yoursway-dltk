package org.eclipse.dltk.internal.launching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.DirectorySourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ExternalArchiveSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.FolderSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.ProjectSourceContainer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.sourcelookup.containers.BuildpathContainerSourceContainer;
import org.eclipse.dltk.launching.sourcelookup.containers.ScriptProjectSourceContainer;

/**
 * Script source lookup utilities.
 */
public class ScriptSourceLookupUtil {

	/**
	 * Translate the runtime buildpath entries into a set of source containers.
	 * 
	 * @param entries
	 *            build path entries
	 * 
	 * @return source container representation of the build path entries
	 */
	public static ISourceContainer[] translate(IRuntimeBuildpathEntry[] entries) {
		List containers = new ArrayList(entries.length);
		for (int i = 0; i < entries.length; i++) {
			IRuntimeBuildpathEntry entry = entries[i];

			ISourceContainer container = null;
			switch (entry.getType()) {
			case IRuntimeBuildpathEntry.PROJECT:
				container = createProjectContainer(entry);
				break;
			case IRuntimeBuildpathEntry.ARCHIVE:
				container = createArchivePathContainer(entry);
				break;
			case IRuntimeBuildpathEntry.SOURCE:
				container = createBuildPathContainer(entry);
				break;
			default:
				// TODO: support other entry types?
				break;
			}

			// only add if a container was created
			if (container != null) {
				containers.add(container);
			}
		}
		return (ISourceContainer[]) containers
				.toArray(new ISourceContainer[containers.size()]);
	}

	private static ISourceContainer createArchivePathContainer(IRuntimeBuildpathEntry entry) {
		// getPath works, use that?
		return new BuildpathContainerSourceContainer(entry.getLocation());
	}
	
	private static ISourceContainer createBuildPathContainer(
			IRuntimeBuildpathEntry entry) {
		ISourceContainer container = null;

		String path = entry.getLocation();
		File file = new File(path);

		if (file.isDirectory()) {
			IResource resource = entry.getResource();
			if (resource instanceof IContainer) {
				container = new FolderSourceContainer((IContainer) resource,
						false);
			} else {
				container = new DirectorySourceContainer(file, false);
			}
		} else {
			container = new BuildpathContainerSourceContainer(path);
		}

		return container;
	}

	private static ISourceContainer createProjectContainer(
			IRuntimeBuildpathEntry entry) {
		IResource resource = entry.getResource();
		ISourceContainer container = null;
		if (resource != null && resource.getType() == IResource.PROJECT) {
			IScriptProject project = DLTKCore.create((IProject) resource);

			if (project.exists()) {
				container = new ScriptProjectSourceContainer(project);
			} else if (resource.exists()) {
				container = new ProjectSourceContainer((IProject) resource,
						false);
			}
		}

		return container;
	}

}
