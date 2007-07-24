/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests.model;

import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.ModifyingResourceTests;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;


/*
 * Tests for ISourceManipulation.delete(...)
 */
public class DeleteResourcesTests extends ModifyingResourceTests {
	private static final String[] PYTHON_NATURE = new String[] { PythonNature.NATURE_ID };

	public DeleteResourcesTests(String name) {
		super(PythonTestsPlugin.PLUGIN_NAME, name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// ensure that indexing is not going to interfer with deletion
		// TODO: Turn on when indexing would be added
		// waitUntilIndexesReady();
		createScriptProject("P", PYTHON_NATURE, new String[] { "" });
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		deleteProject("P");
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
	}

	public static Test suite() {
		return new Suite(DeleteResourcesTests.class);
	}

	// Use this static initializer to specify subset for tests
	// All specified tests which do not belong to the class are skipped...
	static {
		// TESTS_NAMES = new String[] { "testDeleteField5" };
		// TESTS_NUMBERS = new int[] { 2, 12 };
		// TESTS_RANGE = new int[] { 16, -1 };
	}

	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
	}

	/**
	 * Should be able to delete a CU.
	 */
	public void testDeleteSourceModule1() throws CoreException {
		try {
			createFile("P/X.py", "class X:\n" + "    pass");
			ISourceModule cu = getSourceModule("P/X.py");

			startDeltas();
			assertTrue("Resource should exists!", cu.exists());
			cu.delete(false, null);
			assertTrue("Should be able to delete a CU", !cu.exists());
			assertDeltas("Unexpected delta", "P[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		<default>[*]: {CHILDREN}\n"
					+ "			X.py[-]: {}");
		} finally {
			stopDeltas();
			deleteFile("P/X.py");
		}
	}

	/**
	 * Ensure that if a CU is deleted from underneath us in the default package
	 * of a nested root, it disappears from existence.
	 */
	public void testDeleteSourceModule2() throws CoreException {
		try {
			IFile file = createFile("P/X.py", "class X:\n" + "    pass");
			ISourceModule cu = getSourceModule("P/X.py");

			startDeltas();
			file.delete(false, null);
			assertTrue("Should be able to delete a CU", !cu.exists());
			assertDeltas("Unexpected delta", "P[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		<default>[*]: {CHILDREN}\n"
					+ "			X.py[-]: {}");
		} finally {
			stopDeltas();
			deleteFile("P/X.py");
		}
	}

	/**
	 * After deleting a CU in an IWorkspaceRunnable, it should not exist.
	 * (regression test for bug 9232 ISourceModule.delete() fails)
	 */
	public void testDeleteSourceModule3() throws CoreException {
		try {
			createFile("P/X.py", "class X:\n" + "    pass");
			final ISourceModule cu = getSourceModule("P/X.py");

			// force the cu to be opened
			cu.open(null);

			startDeltas();
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					cu.delete(true, null);
					assertTrue("Should be able to delete a CU", !cu.exists());
				}
			}, null);
			assertTrue("Should be able to delete a CU", !cu.exists());
			assertDeltas("Unexpected delta", "P[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		<default>[*]: {CHILDREN}\n"
					+ "			X.py[-]: {}");
		} finally {
			stopDeltas();
			deleteFile("P/X.py");
		}
	}

	/**
	 * Should be able to delete a CU in a non-default package.
	 */
	public void testDeleteSourceModule4() throws CoreException {
		try {
			createFolder("P/p");
			IFile file = createFile("P/p/X.py", "package p;\n" + "class X:\n" + "    pass");
			ISourceModule cu = getSourceModule("P/p/X.py");

			startDeltas();
			cu.delete(false, null);
			assertTrue("CU should not exist", !cu.exists());
			assertTrue("Corresponding file should not exist", !file.exists());
			assertDeltas("Unexpected delta", "P[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		p[*]: {CHILDREN}\n"
					+ "			X.py[-]: {}");
		} finally {
			stopDeltas();
			deleteFolder("P/p");
		}
	}

	/**
	 * Ensure that deleting an empty package fragment that has a sub-package is
	 * not possible.
	 */
	public void testDeleteEmptyScriptFolder() throws CoreException {
		try {
			createFolder("P/p1/p2");
			IScriptFolder pkg = getPackage("P/p1");
			IFolder folder = getFolder("P/p1");

			startDeltas();
			pkg.delete(false, null);
			assertTrue("Folder should exist", folder.exists());
			assertTrue("Fragment should exist", pkg.exists());
			assertDeltas("Unexpected delta", "");
		} finally {
			stopDeltas();
			deleteFolder("P/p1");
		}
	}

	public void testDeleteScriptFolder1() throws CoreException {
		try {
			createFolder("P/a/b/c");
			createFile("P/a/b/c/X.py", "class X:\n" + "    pass");
			IScriptFolder pkg = getPackage("P/a/b/c");
			IFolder folder = getFolder("P/a/b/c");

			startDeltas();
			pkg.delete(false, null);
			assertTrue("Folder should not exist", !folder.exists());
			assertTrue("Fragment should not exist", !pkg.exists());
			assertDeltas("Unexpected delta", "P[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		a/b/c[-]: {}");
		} finally {
			stopDeltas();
			deleteFolder("P/p1");
		}
	}

	/*
	 * Ensures that deleting a default package doesn't remove the source folder.
	 * (regression test for bug 38450 Delete: Removing default package removes
	 * source folder)
	 */
	public void testDeleteScriptFolder2() throws CoreException {
		try {
			createScriptProject("P1", PYTHON_NATURE, new String[] { "src", "bin" });
			IFile file = createFile("P1/src/X.py", "class X:\n" + "    pass");
			IScriptFolder pkg = getPackage("P1/src");
			IFolder folder = getFolder("P1/src");
			ISourceModule cu = getSourceModule("P1/src/X.py");

			startDeltas();
			pkg.delete(false, null);
			assertTrue("Folder should still exist", folder.exists());
			assertTrue("Fragment should still exist", pkg.exists());
			assertTrue("File should no longer exist", !file.exists());
			assertTrue("Compilation unit should no longer exist", !cu.exists());
			assertDeltas("Unexpected delta", "P1[*]: {CHILDREN}\n" + "	src[*]: {CHILDREN}\n"
					+ "		<default>[*]: {CHILDREN}\n" + "			X.py[-]: {}");
		} finally {
			stopDeltas();
			deleteProject("P1");
		}
	}

	/*
	 * Ensures that deleting a default package where prj=src removes its
	 * compilation units is successful. (regression test for bug 39926 deleting
	 * default package (not in source folder) does nothing)
	 */
	public void testDeleteScriptFolder3() throws CoreException {
		try {
			createScriptProject("P1", PYTHON_NATURE, new String[] { "" });
			IFile file = createFile("P1/X.py", "class X:\n" + "    pass");
			IScriptFolder pkg = getPackage("P1");
			IProject project = getProject("P1");
			ISourceModule cu = getSourceModule("P1/X.py");

			startDeltas();
			pkg.delete(false, null);
			assertTrue("Project should still exist", project.exists());
			assertTrue("Fragment should still exist", pkg.exists());
			assertTrue("File should no longer exist", !file.exists());
			assertTrue("Compilation unit should no longer exist", !cu.exists());
			assertDeltas("Unexpected delta", "P1[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		<default>[*]: {CHILDREN}\n"
					+ "			X.py[-]: {}");
		} finally {
			stopDeltas();
			deleteProject("P1");
		}
	}

	/*
	 * Ensures that deleting a package that only contains a .class file is
	 * successful. (regression test for bug 40606 Unable to discard empty
	 * package if containing .class files)
	 */
	public void testDeleteScriptFolder4() throws CoreException {
		try {
			createScriptProject("P1", PYTHON_NATURE, new String[] { "" });
			IFolder folder = createFolder("P1/p");
			IFile file = createFile("P1/p/X.class", "");
			IScriptFolder pkg = getPackage("P1/p");

			startDeltas();
			pkg.delete(false, null);
			assertTrue("Folder should no longer exist", !folder.exists());
			assertTrue("Fragment should no longer exist", !pkg.exists());
			assertTrue("File should no longer exist", !file.exists());
			assertDeltas("Unexpected delta", "P1[*]: {CHILDREN}\n"
					+ "	<project root>[*]: {CHILDREN}\n" + "		p[-]: {}");
		} finally {
			stopDeltas();
			deleteProject("P1");
		}
	}

	private void assertDeletion(IModelElement element) throws ModelException {
		assertDeletion(new IModelElement[] { element });

	}
}
