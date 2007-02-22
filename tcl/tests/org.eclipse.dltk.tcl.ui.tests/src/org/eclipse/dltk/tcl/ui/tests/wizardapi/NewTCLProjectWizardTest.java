/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.tcl.ui.tests.wizardapi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;

public class NewTCLProjectWizardTest extends TestCase {

	public static Test suite() {
		return new TestSuite(NewTCLProjectWizardTest.class);
	}

	private class TestNewScriptProjectWizardPage extends
			NewTclProjectWizardPage {

		private IProject fNewProject;

		public TestNewScriptProjectWizardPage(IWorkspaceRoot root) {
			super(root, null);
		}

		public void setProjectHandle(IProject newProject) {
			fNewProject = newProject;
		}

		protected IPath getLocationPath() {
			return null;
		}

		protected IProject getProjectHandle() {
			return fNewProject;
		}

		public void initBuildPath() {
			super.initBuildPaths();
		}

	}

	private static final String PROJECT_NAME = "DummyProject";
	private static final String OTHER_PROJECT_NAME = "OtherProject";

	private TestNewScriptProjectWizardPage fWizardPage;

	public NewTCLProjectWizardTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		IProject project = root.getProject(PROJECT_NAME);

		fWizardPage = new TestNewScriptProjectWizardPage(root);
		fWizardPage.setProjectHandle(project);
	}

	protected void tearDown() throws Exception {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(PROJECT_NAME);
		if (project.exists()) {
			project.delete(true, null);
		}

		project = root.getProject(OTHER_PROJECT_NAME);
		if (project.exists()) {
			project.delete(true, null);
		}

		super.tearDown();
	}

	private void assertBasicBuildPath(IProject project,
			IBuildpathEntry[] buildpath) {
		assertNotNull("b", buildpath);
		assertTrue("c", buildpath.length == 1);

		if (TclUI.getDefault().getPreferenceStore().getBoolean(
				PreferenceConstants.SRCBIN_FOLDERS_IN_NEWPROJ)) {
			assertEquals("e", buildpath[0].getPath(), project.getFolder("src")
					.getFullPath());
		} else {
			assertEquals("g", buildpath[0].getPath(), project.getFullPath());
		}
	}

	public void testBasicSet() throws Exception {
		fWizardPage.initBuildPath();
		IProject project = fWizardPage.getProjectHandle();

		IBuildpathEntry[] buildpath = fWizardPage.getRawBuildPath();
		assertBasicBuildPath(project, buildpath);
	}

	public void testBasicCreate() throws Exception {
		IProject project = fWizardPage.getProjectHandle();

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				fWizardPage.getRunnable());
		op.run(null);

		IDLTKProject jproj = fWizardPage.getNewScriptProject();

		assertEquals("a", jproj.getProject(), project);

		IBuildpathEntry[] buildpath = jproj.getRawBuildpath();
		assertBasicBuildPath(jproj.getProject(), buildpath);
	}

	public void testProjectChange() throws Exception {
		fWizardPage.initBuildPath();
		IProject project = fWizardPage.getProjectHandle();

		IBuildpathEntry[] buildpath = fWizardPage.getRawBuildPath();
		assertBasicBuildPath(project, buildpath);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject otherProject = root.getProject(OTHER_PROJECT_NAME);

		// change the project before create
		fWizardPage.setProjectHandle(otherProject);

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				fWizardPage.getRunnable());
		op.run(null);

		IDLTKProject jproj = fWizardPage.getNewScriptProject();

		assertEquals("a", jproj.getProject(), otherProject);

		IBuildpathEntry[] buildpath1 = fWizardPage.getRawBuildPath();
		assertBasicBuildPath(otherProject, buildpath1);
	}

	private void assertUserBuildPath(IProject project,
			IBuildpathEntry[] buildpath) {
		assertNotNull("b", buildpath);
		assertTrue("c", buildpath.length == 2);

		assertEquals("e", buildpath[0].getPath(), project.getFolder("dsrc1")
				.getFullPath());
		assertEquals("f", buildpath[1].getPath(), project.getFolder("dsrc2")
				.getFullPath());
	}

	public void testUserSet() throws Exception {
		IProject project = fWizardPage.getProjectHandle();

		//IPath folderPath = project.getFolder("dbin").getFullPath();

		IBuildpathEntry[] entries = new IBuildpathEntry[] {
				DLTKCore.newSourceEntry(project.getFolder("dsrc1")
						.getFullPath()),
				DLTKCore.newSourceEntry(project.getFolder("dsrc2")
						.getFullPath()) };

		fWizardPage.setDefaultBuildPath(entries, true);
		fWizardPage.initBuildPath();

		IBuildpathEntry[] buildpath = fWizardPage.getRawBuildPath();
		assertUserBuildPath(project, buildpath);

		fWizardPage.setDefaultBuildPath(null, false);
		fWizardPage.initBuildPath();

		IBuildpathEntry[] buildpath1 = fWizardPage.getRawBuildPath();
		assertBasicBuildPath(project, buildpath1);
	}

	public void testUserCreate() throws Exception {
		IProject project = fWizardPage.getProjectHandle();

		IBuildpathEntry[] entries = new IBuildpathEntry[] {
				DLTKCore.newSourceEntry(project.getFolder("dsrc1")
						.getFullPath()),
				DLTKCore.newSourceEntry(project.getFolder("dsrc2")
						.getFullPath()) };

		fWizardPage.setDefaultBuildPath(entries, true);

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				fWizardPage.getRunnable());
		op.run(null);

		IDLTKProject jproj = fWizardPage.getNewScriptProject();

		assertEquals("a", jproj.getProject(), project);

		IBuildpathEntry[] buildpath = jproj.getRawBuildpath();
		assertUserBuildPath(jproj.getProject(), buildpath);
	}

	public void testReadExisting() throws Exception {
		IProject project = fWizardPage.getProjectHandle();

		//IPath folderPath = project.getFolder("dbin").getFullPath();
		IBuildpathEntry[] entries = new IBuildpathEntry[] {
				DLTKCore.newSourceEntry(project.getFolder("dsrc1")
						.getFullPath()),
				DLTKCore.newSourceEntry(project.getFolder("dsrc2")
						.getFullPath()) };

		fWizardPage.setDefaultBuildPath(entries, true);

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				fWizardPage.getRunnable());
		op.run(null);

		IProject proj = fWizardPage.getNewScriptProject().getProject();

		fWizardPage.setDefaultBuildPath(null, false);
		fWizardPage.setProjectHandle(proj);

		// reads from existing
		fWizardPage.initBuildPath();

		IBuildpathEntry[] buildpath1 = fWizardPage.getRawBuildPath();
		assertUserBuildPath(project, buildpath1);
	}

	public void testExistingOverwrite() throws Exception {
		IProject project = fWizardPage.getProjectHandle();

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				fWizardPage.getRunnable());
		op.run(null);

		//IPath folderPath = project.getFolder("dbin").getFullPath();
		IBuildpathEntry[] entries = new IBuildpathEntry[] {
				DLTKCore.newSourceEntry(project.getFolder("dsrc1")
						.getFullPath()),
				DLTKCore.newSourceEntry(project.getFolder("dsrc2")
						.getFullPath()) };

		fWizardPage.setDefaultBuildPath(entries, true);

		// should overwrite existing
		IRunnableWithProgress op1 = new WorkspaceModifyDelegatingOperation(
				fWizardPage.getRunnable());
		op1.run(null);

		IDLTKProject jproj = fWizardPage.getNewScriptProject();

		IBuildpathEntry[] buildpath1 = jproj.getRawBuildpath();
		assertUserBuildPath(project, buildpath1);
	}
}
