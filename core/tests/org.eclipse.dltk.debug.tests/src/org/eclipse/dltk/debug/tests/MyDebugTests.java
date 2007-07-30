package org.eclipse.dltk.debug.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.tests.model.SuiteOfTestCases;

public class MyDebugTests extends SuiteOfTestCases {
	private static class FileHelper {
		public static byte[] readFile(java.io.File file)
				throws java.io.IOException {
			int fileLength = (int) file.length();
			byte[] fileBytes = new byte[fileLength];
			java.io.FileInputStream stream = new java.io.FileInputStream(file);
			int bytesRead = 0;
			int lastReadSize = 0;
			while ((lastReadSize != -1) && (bytesRead != fileLength)) {
				lastReadSize = stream.read(fileBytes, bytesRead, fileLength
						- bytesRead);
				bytesRead += lastReadSize;
			}
			stream.close();
			return fileBytes;
		}

		/**
		 * Copy the given source directory (and all its contents) to the given
		 * target directory.
		 */
		public static void copyDirectory(File source, File target)
				throws IOException {
			if (!target.exists()) {
				target.mkdirs();
			}
			File[] files = source.listFiles();
			if (files == null)
				return;
			for (int i = 0; i < files.length; i++) {
				File sourceChild = files[i];
				String name = sourceChild.getName();
				if (name.equals("CVS"))
					continue;
				File targetChild = new File(target, name);
				if (sourceChild.isDirectory()) {
					copyDirectory(sourceChild, targetChild);
				} else {
					copyFile(sourceChild, targetChild);
				}
			}
		}

		/**
		 * Copy file from src (path to the original file) to dest (path to the
		 * destination file).
		 */
		public static void copyFile(File source, File target)
				throws IOException {
			// read source bytes
			byte[] srcBytes = readFile(source);

			// write bytes to dest
			FileOutputStream out = new FileOutputStream(target);
			out.write(srcBytes);
			out.close();
		}
	}

	public MyDebugTests(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void setUpSuite() throws Exception {
		// TODO Auto-generated method stub
		super.setUpSuite();
	}

	public void tearDownSuite() throws Exception {
		// TODO Auto-generated method stub
		super.tearDownSuite();
	}

	// Helper methods
	protected IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	protected IWorkspaceRoot getWorkspaceRoot() {
		return getWorkspace().getRoot();
	}

	protected IProject getProject(String project) {
		return getWorkspaceRoot().getProject(project);
	}

	// Create project
	protected IProject createProject(final String projectName)
			throws CoreException {
		final IProject project = getProject(projectName);
		IWorkspaceRunnable create = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				project.create(null);
				project.open(null);
			}
		};
		getWorkspace().run(create, null);
		return project;
	}

	protected File getPluginDirectoryPath() {
		try {
			URL url = Activator.getDefault().getBundle().getEntry("/");
			return new File(FileLocator.toFileURL(url).getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public File getSourceWorkspacePath() {
		return new File(getPluginDirectoryPath(), "workspace");
	}

	protected IProject createProjectWithContent(final String projectName)
			throws CoreException, IOException {
		final File source = getSourceWorkspacePath();
		final File target = getWorkspaceRoot().getLocation().toFile();
		FileHelper.copyDirectory(new File(source, projectName), new File(
				target, projectName));

		return createProject(projectName);
	}

	protected IScriptProject createScriptProject(final String projectName,
			final String[] natureIds) throws CoreException {
		final IScriptProject[] result = new IScriptProject[1];

		IWorkspaceRunnable create = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				final IProject project = createProject(projectName);

				// Natures
				IProjectDescription description = project.getDescription();
				description.setNatureIds(natureIds);
				project.setDescription(description, null);

				// Script project
				result[0] = DLTKCore.create(project);
			}
		};

		getWorkspace().run(create, null);

		return result[0];
	}

	// protected IScriptProject createScriptProject(final String projectName,
	// final String[] natures, final String[] sourceFolders,
	// final String[] projects) throws CoreException {
	// final IScriptProject[] result = new IScriptProject[1];
	//
	// IWorkspaceRunnable create = new IWorkspaceRunnable() {
	// public void run(IProgressMonitor monitor) throws CoreException {
	// final IProject project = createProject(projectName);
	//
	// // Natures
	// IProjectDescription description = project.getDescription();
	// description.setNatureIds(natures);
	// project.setDescription(description, null);
	//
	// // Buildpath entries
	// IPath projectPath = project.getFullPath();
	// int sourceLength = sourceFolders == null ? 0
	// : sourceFolders.length;
	// int projectLength = projects == null ? 0 : projects.length;
	//
	// IBuildpathEntry[] entries = new IBuildpathEntry[sourceLength
	// + projectLength];
	// for (int i = 0; i < sourceLength; i++) {
	// IPath sourcePath = new Path(sourceFolders[i]);
	// int segmentCount = sourcePath.segmentCount();
	// if (segmentCount > 0) {
	// // create folder and its parents
	// IContainer container = project;
	// for (int j = 0; j < segmentCount; j++) {
	// IFolder folder = container.getFolder(new Path(
	// sourcePath.segment(j)));
	// if (!folder.exists()) {
	// folder.create(true, true, null);
	// }
	// container = folder;
	// }
	// }
	// // create source entry
	// entries[i] = DLTKCore.newSourceEntry(projectPath
	// .append(sourcePath));
	// }
	// for (int i = 0; i < projectLength; i++) {
	// // accessible files
	// IPath[] accessibleFiles;
	// accessibleFiles = new IPath[0];
	//
	// // non accessible files
	// IPath[] nonAccessibleFiles;
	// nonAccessibleFiles = new IPath[0];
	//
	// entries[sourceLength + i] = DLTKCore.newProjectEntry(
	// new Path(projects[i]), BuildpathEntry
	// .getAccessRules(accessibleFiles,
	// nonAccessibleFiles), true,
	// new IBuildpathAttribute[0], false);
	// }
	// // set buildpath and output location
	// IScriptProject scriptProject = DLTKCore.create(project);
	// scriptProject.setRawBuildpath(entries, null);
	//
	// result[0] = scriptProject;
	// }
	// };
	// getWorkspace().run(create, null);
	// return result[0];
	// }

	// protected IScriptProject setUpScriptProject(final String projectName)
	// throws CoreException, IOException {
	// final IProject project = setUpProject(projectName);
	// return DLTKCore.create(project);
	// }
}
