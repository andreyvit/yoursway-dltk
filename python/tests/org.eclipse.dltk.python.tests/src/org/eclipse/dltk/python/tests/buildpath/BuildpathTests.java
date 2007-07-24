/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests.buildpath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Test;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelMarker;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.tests.model.ModifyingResourceTests;
import org.eclipse.dltk.core.tests.util.Util;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;
import org.eclipse.dltk.utils.CorePrinter;


public class BuildpathTests extends ModifyingResourceTests {

	private static final String[] TEST_NATURE = new String[] { PythonNature.NATURE_ID };


	public BuildpathTests(String name) {
		super(PythonTestsPlugin.PLUGIN_NAME, name);
	}

	public static Test suite() {
		return new Suite(BuildpathTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();					
	}

	private void assertEncodeDecodeEntry(String projectName, String expectedEncoded,
			IBuildpathEntry entry) {
		IScriptProject project = getScriptProject(projectName);
		String encoded = project.encodeBuildpathEntry(entry);
		assertSourceEquals("Unexpected encoded entry", expectedEncoded, encoded);
		IBuildpathEntry decoded = project.decodeBuildpathEntry(encoded);
		assertEquals("Unexpected decoded entry", entry, decoded);
	}

	protected void assertStatus(String expected, IStatus status) {
		String actual = status.getMessage();
		if (!expected.equals(actual)) {
			System.out.print(Util.displayString(actual, 2));
			System.out.println(",");
		}
		assertEquals(expected, actual);
	}

	protected void assertStatus(String message, String expected, IStatus status) {
		String actual = status.getMessage();
		if (!expected.equals(actual)) {
			System.out.print(Util.displayString(actual, 2));
			System.out.println(",");
		}
		assertEquals(message, expected, actual);
	}

	protected File createFile(File parent, String name, String content) throws IOException {
		File file = new File(parent, name);
		FileOutputStream out = new FileOutputStream(file);
		out.write(content.getBytes());
		out.close();
		/*
		 * Need to change the time stamp to realize that the file has been
		 * modified
		 */
		file.setLastModified(System.currentTimeMillis() + 2000);
		return file;
	}

	protected File createFolder(File parent, String name) {
		File file = new File(parent, name);
		file.mkdirs();
		return file;
	}
	protected int numberOfCycleMarkers(IScriptProject scriptProject) throws CoreException {
		IMarker[] markers = scriptProject.getProject().findMarkers(IModelMarker.BUILDPATH_PROBLEM_MARKER, false, IResource.DEPTH_ZERO);
		int result = 0;
		for (int i = 0, length = markers.length; i < length; i++) {
			IMarker marker = markers[i];
			String cycleAttr = (String)marker.getAttribute(IModelMarker.CYCLE_DETECTED);
			if (cycleAttr != null && cycleAttr.equals("true")){ //$NON-NLS-1$
				result++;
			}
		}
		return result;
	}

	public void tearDownSuite() throws Exception {
		// TODO Auto-generated method stub		
		super.tearDownSuite();
	}	
	
//	/**
//	 * Library BuildpathEntry test  
//	 * @throws Exception 
//	 */
//	public void test004() throws Exception {
//		setUpScriptProject(BUILDPATH_PRJ_2);
//		IScriptProject project = (IScriptProject) getScriptProject(BUILDPATH_PRJ_2);
//		assertNotNull(project);
//		IBuildpathEntry entrys[] = project.getRawBuildpath();
//		assertEquals(1, entrys.length);
//		assertEquals(IBuildpathEntry.BPE_LIBRARY, entrys[0].getEntryKind());
//		IProjectFragment[] fragments = project.getProjectFragments();
//		assertEquals(1, fragments.length);
//		assertTrue(fragments[0] instanceof ArchiveProjectFragment);
//		IProjectFragment fragment = fragments[0];
//		IModelElement[] elements = fragment.getChildren();
//		
//		System.out.println("Model:");
//		CorePrinter printer = new CorePrinter(System.out);
//		((ScriptProject)project).printNode(printer);
//		printer.flush();
//		
//		deleteProject(BUILDPATH_PRJ_2);		
//	}
	
	/**
	 * External folder Library BuildpathEntry test   
	 * @throws Exception 
	 */
	public void test005() throws Exception {
		try {			
			String filePath = "/usr/lib/python2.4/";			
			IScriptProject proj = this.createScriptProject("P", TEST_NATURE, new String[] { "src" });
			IBuildpathEntry[] originalCP = proj.getRawBuildpath();

			IBuildpathEntry[] newCP = new IBuildpathEntry[originalCP.length + 1];
			System.arraycopy(originalCP, 0, newCP, 0, originalCP.length);
			newCP[originalCP.length] = DLTKCore.newExtLibraryEntry(new Path( filePath ));

			IModelStatus status = BuildpathEntry.validateBuildpath(proj, newCP);

			assertStatus("OK", status);
			
			proj.setRawBuildpath(newCP, null);
			
			System.out.println("Model:");
			CorePrinter printer = new CorePrinter(System.out, true);
			//((ScriptProject)proj).printNode(printer);
			printer.flush();
		} finally {
			this.deleteProject("P");			
		}
	}
}
