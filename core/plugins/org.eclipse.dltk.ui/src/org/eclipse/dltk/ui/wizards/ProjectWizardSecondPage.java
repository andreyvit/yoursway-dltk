/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.core.ModelManager;
import org.eclipse.dltk.internal.core.builder.State;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.BuildpathDetector;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage.AbstractInterpreterGroup;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;

/**
 * As addition to the DLTKCapabilityConfigurationPage, the wizard does an early
 * project creation (so that linked folders can be defined) and, if an existing
 * external location was specified, offers to do a buildpath detection
 */
public abstract class ProjectWizardSecondPage extends
		CapabilityConfigurationPage {

	private static final String FILENAME_PROJECT = ".project"; //$NON-NLS-1$
	private static final String FILENAME_BUILDPATH = ".buildpath"; //$NON-NLS-1$

	private final ProjectWizardFirstPage fFirstPage;

	private URI fCurrProjectLocation; // null if location is platform location
	private IProject fCurrProject;

	private boolean fKeepContent;

	private File fDotProjectBackup;
	private File fDotBuildpathBackup;
	private Boolean fIsAutobuild;

	/**
	 * Constructor for ScriptProjectWizardSecondPage.
	 */
	public ProjectWizardSecondPage(ProjectWizardFirstPage mainPage) {
		fFirstPage = mainPage;
		fCurrProjectLocation = null;
		fCurrProject = null;
		fKeepContent = false;

		fDotProjectBackup = null;
		fDotBuildpathBackup = null;
		fIsAutobuild = null;
	}

	protected boolean useNewSourcePage() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		if (visible) {
			changeToNewProject();
		} else {
			removeProject();
		}
		super.setVisible(visible);
	}

	private void changeToNewProject() {
		fKeepContent = fFirstPage.getDetect();

		final IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					if (fIsAutobuild == null) {
						fIsAutobuild = Boolean.valueOf(CoreUtility
								.enableAutoBuild(false));
					}
					updateProject(monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} catch (OperationCanceledException e) {
					throw new InterruptedException();
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false,
					new WorkspaceModifyDelegatingOperation(op));
		} catch (InvocationTargetException e) {
			final String title = NewWizardMessages.ScriptProjectWizardSecondPage_error_title;
			final String message = NewWizardMessages.ScriptProjectWizardSecondPage_error_message;
			ExceptionHandler.handle(e, getShell(), title, message);
		} catch (InterruptedException e) {
			// cancel pressed
		}
	}

	final void updateProject(IProgressMonitor monitor) throws CoreException,
			InterruptedException {

		fCurrProject = fFirstPage.getProjectHandle();
		fCurrProjectLocation = getProjectLocationURI();

		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		try {
			monitor
					.beginTask(
							NewWizardMessages.ScriptProjectWizardSecondPage_operation_initialize,
							7);
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			URI realLocation = fCurrProjectLocation;
			if (fCurrProjectLocation == null) { // inside workspace
				try {
					URI rootLocation = ResourcesPlugin.getWorkspace().getRoot()
							.getLocationURI();
					realLocation = new URI(rootLocation.getScheme(), null, Path
							.fromPortableString(rootLocation.getPath()).append(
									fCurrProject.getName()).toString(), null);
				} catch (URISyntaxException e) {
					Assert.isTrue(false, "Can't happen"); //$NON-NLS-1$
				}
			}

			rememberExistingFiles(realLocation);

			createProject(fCurrProject, fCurrProjectLocation,
					new SubProgressMonitor(monitor, 2));

			IBuildpathEntry[] entries = null;

			if (fFirstPage.getDetect()) {
				if (!fCurrProject.getFile(FILENAME_BUILDPATH).exists()) {
					IDLTKLanguageToolkit toolkit = DLTKLanguageManager
							.getLanguageToolkit(getScriptNature());
					final BuildpathDetector detector = new BuildpathDetector(
							fCurrProject, new SubProgressMonitor(monitor, 2),
							toolkit);
					entries = detector.getBuildpath();
				} else {
					monitor.worked(2);
				}
			} else if (fFirstPage.isSrc()) {
				IPreferenceStore store = getPreferenceStore();
				IPath srcPath = new Path(store
						.getString(PreferenceConstants.SRC_SRCNAME));

				if (srcPath.segmentCount() > 0) {
					IFolder folder = fCurrProject.getFolder(srcPath);
					CoreUtility.createFolder(folder, true, true,
							new SubProgressMonitor(monitor, 1));
				} else {
					monitor.worked(1);
				}

				if (srcPath.segmentCount() > 0) {
					IFolder folder = fCurrProject.getFolder(srcPath);
					CoreUtility.createFolder(folder, true, true,
							new SubProgressMonitor(monitor, 1));
				} else {
					monitor.worked(1);
				}

				final IPath projectPath = fCurrProject.getFullPath();

				// configure the buildpath entries, including the default
				// InterpreterEnvironment library.
				List cpEntries = new ArrayList();
				cpEntries.add(DLTKCore.newSourceEntry(projectPath
						.append(srcPath)));
				IBuildpathEntry[] entrys = getDefaultBuildpathEntry();
				if (entrys != null) {
					cpEntries.addAll(Arrays.asList(entrys));
				}
				entries = (IBuildpathEntry[]) cpEntries
						.toArray(new IBuildpathEntry[cpEntries.size()]);

			} else {
				IPath projectPath = fCurrProject.getFullPath();
				List cpEntries = new ArrayList();
				cpEntries.add(DLTKCore.newSourceEntry(projectPath));
				IBuildpathEntry[] entrys = getDefaultBuildpathEntry();
				if (entrys != null) {
					cpEntries.addAll(Arrays.asList(entrys));
				}
				entries = (IBuildpathEntry[]) cpEntries
						.toArray(new IBuildpathEntry[cpEntries.size()]);

				monitor.worked(2);
			}
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			init(DLTKCore.create(fCurrProject), entries, false);
			configureScriptProject(new SubProgressMonitor(monitor, 3)); // create
			// the
			// script
			// project
			// to
			// allow
			// the
			// use
			// of
			// the
			// new
			// source
			// folder
			// page
		} finally {
			monitor.done();
		}
	}

	protected abstract IPreferenceStore getPreferenceStore();

	private URI getProjectLocationURI() throws CoreException {
		if (fFirstPage.isInWorkspace()) {
			return null;
		}
		return URIUtil.toURI(fFirstPage.getLocationPath());
	}

	private IBuildpathEntry[] getDefaultBuildpathEntry() {
		IBuildpathEntry defaultPath = ScriptRuntime
				.getDefaultInterpreterContainerEntry();

		IPath InterpreterEnvironmentContainerPath = new Path(
				ScriptRuntime.INTERPRETER_CONTAINER);

		IInterpreterInstall inst = fFirstPage.getInterpreter();
		if (inst != null) {
			IPath newPath = InterpreterEnvironmentContainerPath.append(
					inst.getInterpreterInstallType().getId()).append(
					inst.getName());
			return new IBuildpathEntry[] { DLTKCore.newContainerEntry(newPath) };
		}

		if (defaultPath != null)
			return new IBuildpathEntry[] { defaultPath };

		return null;
	}

	private void rememberExistingFiles(URI projectLocation)
			throws CoreException {
		fDotProjectBackup = null;
		fDotBuildpathBackup = null;

		IFileStore file = EFS.getStore(projectLocation);
		if (file.fetchInfo().exists()) {
			IFileStore projectFile = file.getChild(FILENAME_PROJECT);
			if (projectFile.fetchInfo().exists()) {
				fDotProjectBackup = createBackup(projectFile, "project-desc"); //$NON-NLS-1$ 
			}
			IFileStore buildpathFile = file.getChild(FILENAME_BUILDPATH);
			if (buildpathFile.fetchInfo().exists()) {
				fDotBuildpathBackup = createBackup(buildpathFile,
						"buildpath-desc"); //$NON-NLS-1$ 
			}
		}
	}

	private void restoreExistingFiles(URI projectLocation,
			IProgressMonitor monitor) throws CoreException {
		int ticks = ((fDotProjectBackup != null ? 1 : 0) + (fDotBuildpathBackup != null ? 1
				: 0)) * 2;
		monitor.beginTask("", ticks); //$NON-NLS-1$
		try {
			if (fDotProjectBackup != null) {
				IFileStore projectFile = EFS.getStore(projectLocation)
						.getChild(FILENAME_PROJECT);
				projectFile
						.delete(EFS.NONE, new SubProgressMonitor(monitor, 1));
				copyFile(fDotProjectBackup, projectFile,
						new SubProgressMonitor(monitor, 1));
			}
		} catch (IOException e) {
			IStatus status = new Status(
					IStatus.ERROR,
					DLTKUIPlugin.PLUGIN_ID,
					IStatus.ERROR,
					NewWizardMessages.ScriptProjectWizardSecondPage_problem_restore_project,
					e);
			throw new CoreException(status);
		}
		try {
			if (fDotBuildpathBackup != null) {
				IFileStore buildpathFile = EFS.getStore(projectLocation)
						.getChild(FILENAME_BUILDPATH);
				buildpathFile.delete(EFS.NONE, new SubProgressMonitor(monitor,
						1));
				copyFile(fDotBuildpathBackup, buildpathFile,
						new SubProgressMonitor(monitor, 1));
			}
		} catch (IOException e) {
			IStatus status = new Status(
					IStatus.ERROR,
					DLTKUIPlugin.PLUGIN_ID,
					IStatus.ERROR,
					NewWizardMessages.ScriptProjectWizardSecondPage_problem_restore_buildpath,
					e);
			throw new CoreException(status);
		}
	}

	private File createBackup(IFileStore source, String name)
			throws CoreException {
		try {
			File bak = File.createTempFile("eclipse-" + name, ".bak"); //$NON-NLS-1$//$NON-NLS-2$
			copyFile(source, bak);
			return bak;
		} catch (IOException e) {
			IStatus status = new Status(
					IStatus.ERROR,
					DLTKUIPlugin.PLUGIN_ID,
					IStatus.ERROR,
					Messages
							.format(
									NewWizardMessages.ScriptProjectWizardSecondPage_problem_backup,
									name), e);
			throw new CoreException(status);
		}
	}

	private void copyFile(IFileStore source, File target) throws IOException,
			CoreException {
		InputStream is = source.openInputStream(EFS.NONE, null);
		FileOutputStream os = new FileOutputStream(target);
		copyFile(is, os);
	}

	private void copyFile(File source, IFileStore target,
			IProgressMonitor monitor) throws IOException, CoreException {
		FileInputStream is = new FileInputStream(source);
		OutputStream os = target.openOutputStream(EFS.NONE, monitor);
		copyFile(is, os);
	}

	private void copyFile(InputStream is, OutputStream os) throws IOException {
		try {
			byte[] buffer = new byte[8192];
			while (true) {
				int bytesRead = is.read(buffer);
				if (bytesRead == -1)
					break;

				os.write(buffer, 0, bytesRead);
			}
		} finally {
			try {
				is.close();
			} finally {
				os.close();
			}
		}
	}

	/**
	 * Called from the wizard on finish.
	 */
	public void performFinish(IProgressMonitor monitor) throws CoreException,
			InterruptedException {
		try {
			monitor
					.beginTask(
							NewWizardMessages.ScriptProjectWizardSecondPage_operation_create,
							3);
			if (fCurrProject == null) {
				updateProject(new SubProgressMonitor(monitor, 1));
			}
			configureScriptProject(new SubProgressMonitor(monitor, 2));

			if (!fKeepContent) {
				if (DLTKCore.DEBUG) {
					System.err
							.println("Add compiler compilance options here...");
				}
				// String compliance= fFirstPage.getCompilerCompliance();
				// if (compliance != null) {
				// IScriptProject project= DLTKCore.create(fCurrProject);
				// Map options= project.getOptions(false);
				// ModelUtil.setCompilanceOptions(options, compliance);
				// project.setOptions(options);
				// }
			}

			// Not rebuild project external libraries if exist project with same
			// interpreter.
			IInterpreterInstall projectInterpreter = this.fFirstPage
					.getInterpreter();
			if (projectInterpreter == null) {
				Observable observable = fFirstPage
						.getInterpreterGroupObservable();
				String nature = null;
				if (observable != null
						&& observable instanceof AbstractInterpreterGroup) {
					AbstractInterpreterGroup gr = (AbstractInterpreterGroup) observable;
					nature = gr.getCurrentLanguageNature();
				}
				if (nature != null) {
					projectInterpreter = ScriptRuntime
							.getDefaultInterpreterInstall(nature);
				}

			}
			if (projectInterpreter != null) {
				State lastState = (State) ModelManager.getModelManager()
						.getLastBuiltState(fCurrProject, monitor);
				if (lastState == null) {
					lastState = new State(fCurrProject);
				}
				IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
						.getProjects();
				for (int i = 0; i < projects.length; i++) {
					if (projects[i].isAccessible()
							&& DLTKLanguageManager.hasScriptNature(projects[i])) {
						IScriptProject scriptProject = DLTKCore
								.create(projects[i]);

						IInterpreterInstall install = ScriptRuntime
								.getInterpreterInstall(scriptProject);
						if (projectInterpreter.equals(install)) {
							// We found project with same interpreter.
							State state = (State) ModelManager
									.getModelManager().getLastBuiltState(
											projects[i], monitor);
							if (state != null) {
								lastState.getExternalFolders().addAll(
										state.getExternalFolders());
							}
						}
					}
				}
				lastState.setNoCleanExternalFolders();
				ModelManager.getModelManager().setLastBuiltState(fCurrProject,
						lastState);
			}
			// Locate projects with same interpreter.
		} finally {
			monitor.done();
			fCurrProject = null;
			if (fIsAutobuild != null) {
				CoreUtility.enableAutoBuild(fIsAutobuild.booleanValue());
				fIsAutobuild = null;
			}
		}
	}

	private void removeProject() {
		if (fCurrProject == null || !fCurrProject.exists()) {
			return;
		}

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				doRemoveProject(monitor);
			}
		};

		try {
			getContainer().run(true, true,
					new WorkspaceModifyDelegatingOperation(op));
		} catch (InvocationTargetException e) {
			final String title = NewWizardMessages.ScriptProjectWizardSecondPage_error_remove_title;
			final String message = NewWizardMessages.ScriptProjectWizardSecondPage_error_remove_message;
			ExceptionHandler.handle(e, getShell(), title, message);
		} catch (InterruptedException e) {
			// cancel pressed
		}
	}

	final void doRemoveProject(IProgressMonitor monitor)
			throws InvocationTargetException {
		final boolean noProgressMonitor = (fCurrProjectLocation == null); // inside
		// workspace
		if (monitor == null || noProgressMonitor) {
			monitor = new NullProgressMonitor();
		}
		monitor
				.beginTask(
						NewWizardMessages.ScriptProjectWizardSecondPage_operation_remove,
						3);
		try {
			try {
				URI projLoc = fCurrProject.getLocationURI();

				boolean removeContent = !fKeepContent
						&& fCurrProject
								.isSynchronized(IResource.DEPTH_INFINITE);
				fCurrProject.delete(removeContent, false,
						new SubProgressMonitor(monitor, 2));

				restoreExistingFiles(projLoc,
						new SubProgressMonitor(monitor, 1));
			} finally {
				CoreUtility.enableAutoBuild(fIsAutobuild.booleanValue()); // fIsAutobuild
				// must
				// be
				// set
				fIsAutobuild = null;
			}
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		} finally {
			monitor.done();
			fCurrProject = null;
			fKeepContent = false;
		}
	}

	/**
	 * Called from the wizard on cancel.
	 */
	public void performCancel() {
		removeProject();
	}
}
