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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.core.tests.model.ModifyingResourceTests;
import org.eclipse.dltk.core.tests.model.TestBuffer;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;

public class WorkingCopyTests extends ModifyingResourceTests {
	private static final String[] PYTHON_NATURE = new String[] {
		PythonNature.NATURE_ID
	};
	ISourceModule cu = null;
	ISourceModule copy = null;
	public class TestWorkingCopyOwner extends WorkingCopyOwner {
		public IBuffer createBuffer(ISourceModule workingCopy) {
			return new TestBuffer(workingCopy);
		}
	}

	public WorkingCopyTests(String name ) {
		super(  PythonTestsPlugin.PLUGIN_NAME, name );
	}

	public static Test suite() {
		return new Suite(WorkingCopyTests.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		try {
			this.createScriptProject("P", PYTHON_NATURE, new String[] {
				"src"
			});
			this.createFolder("P/src/x/y");
			this.createFile("P/src/x/y/A.py", 
					"import os;\n" + 
					"class A:\n" + 
					"    class Inner:\n" + 
					"        def __init__():pass\n"+
					"        innerField = 10;\n" +
					"        def innerMethod():pass\n" + 
					"    FIELD=\"\";\n"+ 
					"    field1=1;\n" +
					"    field2=false;\n" +
					"    def foo():pass\n\n");
			this.cu = this.getSourceModule("P/src/x/y/A.py");
			this.copy = cu.getWorkingCopy(null);
		} catch (CoreException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	protected void tearDown() throws Exception {
		if (this.copy != null)
			this.copy.discardWorkingCopy();
		this.deleteProject("P");
		super.tearDown();
	}

	/*
	 * Ensures that cancelling a make consistent operation doesn't leave the
	 * working copy closed (regression test for bug 61719 Incorrect fine grain
	 * delta after method copy-rename)
	 */
	public void testCancelMakeConsistent() throws ModelException {
		String newContents = "class A:\n" + "\tdef bar():\n" + "\t\tpass\n";
		this.copy.getBuffer().setContents(newContents);
		NullProgressMonitor monitor = new NullProgressMonitor();
		monitor.setCanceled(true);
		try {
			this.copy.makeConsistent(monitor);
		} catch (OperationCanceledException e) {
			// got exception
		}
		assertTrue("Working copy should be opened", this.copy.isOpen());
	}

	/**
	 */
	public void testChangeContent() throws CoreException {
		String newContents = "class A:\n" + "\tdef bar():\n" + "\t\tpass\n";
		this.copy.getBuffer().setContents(newContents);
		this.copy.reconcile(false, null, null);
		assertSourceEquals("Unexpected working copy contents", newContents, this.copy.getBuffer().getContents());
		this.copy.commitWorkingCopy(true, null);
		assertSourceEquals("Unexpected original cu contents", newContents, this.cu.getBuffer().getContents());
	}

	/*
	 * Ensures that one cannot commit the contents of a working copy on a read
	 * only cu.
	 */
	public void testChangeContentOfReadOnlyCU1() throws CoreException {
		IResource resource = this.cu.getUnderlyingResource();
		boolean readOnlyFlag = isReadOnly(resource);
		boolean didComplain = false;
		try {
			setReadOnly(resource, true);
			this.copy.getBuffer().setContents("invalid");
			this.copy.commitWorkingCopy(true, null);
		} catch (ModelException e) {
			didComplain = true;
		} finally {
			setReadOnly(resource, readOnlyFlag);
		}
		assertTrue("Should have complained about modifying a read-only unit:", didComplain);
		assertTrue("ReadOnly buffer got modified:", !this.cu.getBuffer().getContents().equals("invalid"));
	}

	/**
	 * Ensures that the primary cu can be retrieved.
	 */
	public void testGetPrimaryCU() {
		IModelElement primary = this.copy.getPrimaryElement();
		assertTrue("Element is not a cu", primary instanceof ISourceModule && !((ISourceModule) primary).isWorkingCopy());
		assertTrue("Element should exist", primary.exists());
	}

	/**
	 * Ensures that the primary field can be retrieved.
	 */
	public void REM_testGetPrimaryField() {
		IType type = this.copy.getType("A");
		IModelElement primary = type.getField("FIELD").getPrimaryElement();
		assertTrue("Element is not a field", primary instanceof IField
				&& !((ISourceModule) primary.getParent().getParent()).isWorkingCopy());
		assertTrue("Element should exist", primary.exists());
	}

	// /**
	// * Ensures that the primary import declaration can be retrieved.
	// */
	// public void testGetPrimaryImportDeclaration() {
	// IImportDeclaration imprt = copy.getImport("java.io.File");
	// IModelElement primary = imprt.getPrimaryElement();
	// assertTrue("Element should exist", primary.exists());
	// }
	// /**
	// * Ensures that the primary import container can be retrieved.
	// */
	// public void testGetPrimaryImportContainer() {
	// IImportContainer container = this.copy.getImportContainer();
	// IModelElement primary = container.getPrimaryElement();
	// assertTrue("Element should not be null", primary != null);
	// assertTrue("Element should exist", primary.exists());
	// }
	// /**
	// * Ensures that the primary initializer can be retrieved.
	// */
	// public void testGetPrimaryInitializer() {
	// IType type= copy.getType("A");
	// IModelElement primary= type.getInitializer(1).getPrimaryElement();
	// assertTrue("Element should exist", primary.exists());
	// }
	/**
	 */
	public void testGetPrimaryInnerField() {
		IType innerType = this.copy.getType("A").getType("Inner");
		IModelElement primary = innerType.getField("innerField").getPrimaryElement();
		assertTrue("Element is not a field", primary instanceof IField);
		assertTrue("Element should exist", primary.exists());
	}

	/**
	 */
	public void testGetPrimaryInnerMethod() throws ModelException {
		IType innerType = this.copy.getType("A").getType("Inner");
		IModelElement primary = innerType.getMethods()[0].getPrimaryElement();
		assertTrue("Element is not a method", primary instanceof IMethod);
		assertTrue("Element should exist", primary.exists());
	}
}
