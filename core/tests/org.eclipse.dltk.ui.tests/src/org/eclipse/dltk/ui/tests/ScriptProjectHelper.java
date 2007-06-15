/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.ModelTestsPlugin;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;


/**
 * Helper methods to set up a IScriptProject.
 */
public class ScriptProjectHelper {
	
	private static final int MAX_RETRY= 5;
	
	public static final IPath JUNIT_SRC_381= new Path("testresources/junit381-noUI-src.zip");
	public static final String JUNIT_SRC_ENCODING= "ISO-8859-1";
	public static final IPath MYLIB= new Path("testresources/mylib.zip");
	
	private static class ImportOverwriteQuery implements IOverwriteQuery {
		public String queryOverwrite(String file) {
			return ALL;
		}	
	}		
	/**
	 * Adds a source container to a IScriptProject.
	 * @param jproject The parent project
	 * @param containerName The name of the new source container
	 * @param inclusionFilters Inclusion filters to set
	 * @param exclusionFilters Exclusion filters to set
	 * @return The handle to the new source container
	 * @throws CoreException Creation failed
	 */				
	public static IProjectFragment addSourceContainer(IScriptProject jproject, String containerName, IPath[] inclusionFilters, IPath[] exclusionFilters) throws CoreException {
		IProject project= jproject.getProject();
		IContainer container= null;
		if (containerName == null || containerName.length() == 0) {
			container= project;
		} else {
			IFolder folder= project.getFolder(containerName);
			if (!folder.exists()) {
				CoreUtility.createFolder(folder, false, true, null);
			}
			container= folder;
		}
		IProjectFragment root= jproject.getProjectFragment(container);
		IBuildpathEntry bpe= DLTKCore.newSourceEntry(root.getPath(), 
				inclusionFilters, exclusionFilters, BuildpathEntry.NO_EXTRA_ATTRIBUTES);
		
		addToBuildpath(jproject, bpe);
		
		return root;
	}
	
	public static void addToBuildpath(IScriptProject jproject, IBuildpathEntry cpe) throws ModelException {
		IBuildpathEntry[] oldEntries= jproject.getRawBuildpath();
		for (int i= 0; i < oldEntries.length; i++) {
			if (oldEntries[i].equals(cpe)) {
				return;
			}
		}
		int nEntries= oldEntries.length;
		IBuildpathEntry[] newEntries= new IBuildpathEntry[nEntries + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0, nEntries);
		newEntries[nEntries]= cpe;
		jproject.setRawBuildpath(newEntries, null);
	}
	
	/**
	 * Adds a source container to a IScriptProject.
	 * @param jproject The parent project
	 * @param containerName The name of the new source container
	 * @return The handle to the new source container
	 * @throws CoreException Creation failed
	 */		
	public static IProjectFragment addSourceContainer(IScriptProject jproject, String containerName) throws CoreException {
		return addSourceContainer(jproject, containerName, new Path[0]);
	}

	/**
	 * Adds a source container to a IScriptProject.
	 * @param jproject The parent project
	 * @param containerName The name of the new source container
	 * @param exclusionFilters Exclusion filters to set
	 * @return The handle to the new source container
	 * @throws CoreException Creation failed
	 */		
	public static IProjectFragment addSourceContainer(IScriptProject jproject, String containerName, IPath[] exclusionFilters) throws CoreException {
		return addSourceContainer(jproject, containerName, new Path[0], exclusionFilters);
	}
	
	/**
	 * Creates a IScriptProject.
	 * @param projectName The name of the project
	 * @param binFolderName Name of the output folder
	 * @return Returns the script project handle
	 * @throws CoreException Project creation failed
	 */	
	public static IScriptProject createScriptProject(String projectName) throws CoreException {
		IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
		IProject project= root.getProject(projectName);
		
		IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());			
		description.setNatureIds(new String[] { ModelTestsPlugin.TEST_NATURE });
		
		
		if (!project.exists()) {
			project.create(description, null);
			
		} else {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		}
		
		if (!project.isOpen()) {
			project.open(null);
		}
		
		IScriptProject jproject= DLTKCore.create(project);
		
		jproject.setRawBuildpath(new IBuildpathEntry[0], null);
				
		return jproject;	
	}
	public static boolean setAutoBuilding(boolean state) throws CoreException {
		// disable auto build
		IWorkspace workspace= ResourcesPlugin.getWorkspace();
		IWorkspaceDescription desc= workspace.getDescription();
		boolean result= desc.isAutoBuilding();
		desc.setAutoBuilding(state);
		workspace.setDescription(desc);
		return result;
	}
	public static void delete(final IModelElement elem) throws CoreException {				
		IWorkspaceRunnable runnable= new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				//performDummySearch();
				if (elem instanceof IScriptProject) {
					IScriptProject jproject= (IScriptProject) elem;
					jproject.setRawBuildpath(new IBuildpathEntry[0], null);
				}
				for (int i= 0; i < MAX_RETRY; i++) {
					try {
						elem.getResource().delete(true, null);
						i= MAX_RETRY;
					} catch (CoreException e) {
						if (i == MAX_RETRY - 1) {
							DLTKUIPlugin.log(e);
							throw e;
						}
						try {
							Thread.sleep(1000); // sleep a second
						} catch (InterruptedException e1) {
						} 
					}
				}
			}
		};
		ResourcesPlugin.getWorkspace().run(runnable, null);
	}

	/**
	 * Adds a library entry with source attachment to a IScriptProject.
	 * @param jproject The parent project
	 * @param path The path of the library to add
	 * @param sourceAttachPath The source attachment path
	 * @param sourceAttachRoot The source attachment root path
	 * @return The handle of the created root
	 * @throws ScriptModelException 
	 */			
	public static IProjectFragment addLibrary(IScriptProject jproject, IPath path) throws ModelException {
		IBuildpathEntry cpe= DLTKCore.newLibraryEntry(path);
		addToBuildpath(jproject, cpe);
		return jproject.getProjectFragment(path.toString());
	}
	public static IProjectFragment addSourceContainerWithImport(IScriptProject jproject, String containerName, File zipFile, String containerEncoding) throws InvocationTargetException, CoreException, IOException {
		return addSourceContainerWithImport(jproject, containerName, zipFile, containerEncoding, new Path[0]);
	}
	public static IProjectFragment addSourceContainerWithImport(IScriptProject jproject, String containerName, File zipFile, String containerEncoding, IPath[] exclusionFilters) throws InvocationTargetException, CoreException, IOException {
		ZipFile file= new ZipFile(zipFile);
		try {
			IProjectFragment root= addSourceContainer(jproject, containerName, exclusionFilters);
			((IContainer) root.getCorrespondingResource()).setDefaultCharset(containerEncoding, null);
			importFilesFromZip(file, root.getPath(), null);
			return root;
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}
	private static void importFilesFromZip(ZipFile srcZipFile, IPath destPath, IProgressMonitor monitor) throws InvocationTargetException {		
		ZipFileStructureProvider structureProvider=	new ZipFileStructureProvider(srcZipFile);
		try {
			ImportOperation op= new ImportOperation(destPath, structureProvider.getRoot(), structureProvider, new ImportOverwriteQuery());
			op.run(monitor);
		} catch (InterruptedException e) {
			// should not happen
		}
	}
	public static IProjectFragment addLibraryWithImport(IScriptProject jproject, IPath archivePath) throws IOException, CoreException {
		IProject project= jproject.getProject();
		IFile newFile= project.getFile(archivePath.lastSegment());
		InputStream inputStream= null;
		try {
			inputStream= new FileInputStream(archivePath.toFile()); 
			newFile.create(inputStream, true, null);
		} finally {
			if (inputStream != null) {
				try { inputStream.close(); } catch (IOException e) { }
			}
		}				
		return addLibrary(jproject, newFile.getFullPath());
	}
}

