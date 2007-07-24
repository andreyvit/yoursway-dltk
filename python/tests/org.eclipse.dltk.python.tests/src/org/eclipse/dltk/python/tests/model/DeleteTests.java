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
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.ModifyingResourceTests;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;


/*
 * Tests for ISourceManipulation.delete(...)
 */
public class DeleteTests extends ModifyingResourceTests {
	private static final String[] PYTHON_NATURE = new String[] {
		PythonNature.NATURE_ID
	};
		
	public DeleteTests(String name) {
		super(  PythonTestsPlugin.PLUGIN_NAME, name );
	}

protected void setUp() throws Exception {
	super.setUp();
//	 ensure that indexing is not going to interfer with deletion
//  TODO: Turn on when indexing would be added
//	waitUntilIndexesReady();
	createScriptProject("P", PYTHON_NATURE, new String[]{""});
}

protected void tearDown() throws Exception {
	super.tearDown();
	deleteProject("P");
}

public void setUpSuite() throws Exception {
	super.setUpSuite();
}
public static Test suite() {
	return new Suite(DeleteTests.class);
}
// Use this static initializer to specify subset for tests
// All specified tests which do not belong to the class are skipped...
static {
//		TESTS_NAMES = new String[] { "testDeleteField5" };
//		TESTS_NUMBERS = new int[] { 2, 12 };
//		TESTS_RANGE = new int[] { 16, -1 };
}
public void tearDownSuite() throws Exception {
	super.tearDownSuite();
}
/**
 * Ensures that a field can be deleted.
 */
public void testDeleteField1() throws CoreException { // was testDeleteField
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  field=5;\n" +
			"  pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IField field = cu.getType("X").getField("field");

		startDeltas();
		assertDeletion(field);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					field[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
/**
 * Ensures that deletion can be canceled.
 */
public void testDeleteField2() throws CoreException { // was testDeleteFieldWithCancel
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  field=5;\n" +
			"  pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IField field = cu.getType("X").getField("field");

		boolean isCanceled = false;
		try {
			TestProgressMonitor monitor = TestProgressMonitor.getInstance();
			monitor.setCancelledCounter(1);
			getScriptModel().delete(new IModelElement[] {field}, false, monitor);
		} catch (OperationCanceledException e) {
			isCanceled = true;
		}
		assertTrue("Operation should have thrown an operation canceled exception", isCanceled);
	} finally {
		deleteFile("P/X.py");
	}
}
/*
 * Ensures that a field can be deleted inside a scheduling rule that include the resource only.
 * (regression test for bug 73078 ISourceManipulation.delete() tries to run in WorkspaceRoot scheduling rule)
 */
public void testDeleteField3() throws CoreException {
	try {
		IFile file = createFile(
			"P/X.py",
			"class X:\n" +
			"  field = 5;\n" +
			"  pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		final IField field = cu.getType("X").getField("field");

		startDeltas();
		getWorkspace().run(
			new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					assertDeletion(field);
				}
			}, 
			file,
			IWorkspace.AVOID_UPDATE,
			null);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					field[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}

//TODO: Source manipulations for in-file model elements
///*
// * Ensures that a field with initializer can be deleted.
// * (regression test for bug 112935 IField.delete is not deleting the value of the variable.)
// */
//public void testDeleteField4() throws CoreException {
//	try {
//		createFile(
//			"P/X.py",
//			"class X:\n" +
//			"  private String t = \"sample test\";\n" +
//			"    pass"
//		);
//		ISourceModule cu = getSourceModule("P/X.py");
//		IField field = cu.getType("X").getField("t");
//		field.delete(false, null);
//		assertSourceEquals(
//			"Unexpected source", 
//			"class X:\n" + 
//			"    pass",
//			cu.getSource());
//	} finally {
//		deleteFile("P/X.py");
//	}
//}
//TODO: Source manipulations for in-file model elements
///**
// * Delete enum
// */
//public void testDeleteField5() throws CoreException {
//	try {
//		this.createScriptProject("P1", PYTHON_NATURE, new String[] {"JCL15_LIB"});
//		createFile(
//			"P1/X.py",
//			"public enum X {\n" +
//			"  A, B, C\n" +
//			"    pass"
//		);
//		ISourceModule cu = getSourceModule("P1/X.py");
//		IField field = cu.getType("X").getField("A");
//		field.delete(false, null);
//		assertSourceEquals(
//			"Unexpected source", 
//			"public enum X {\n" + 
//			"  B, C\n" + 
//			"    pass",
//			cu.getSource());
//	} finally {
//		deleteProject("P1");
//	}
//}
//TODO: Implement import declarations
///**
// * Ensures that an import declaration can be deleted.
// */
//public void testDeleteImportDeclaration() throws CoreException {
//	try {
//		createFile(
//			"P/X.py",
//			"import java.util.*;\n" +
//			"import q.Y;\n" +
//			"class X:\n" +
//			"    pass"
//		);
//		ISourceModule cu = getSourceModule("P/X.py");
//		IImportDeclaration imp= cu.getImport("q.Y");
//
//		startDeltas();
//		assertDeletion(imp);
//		assertDeltas(
//			"Unexpected delta",
//			"P[*]: {CHILDREN}\n" + 
//			"	<project root>[*]: {CHILDREN}\n" + 
//			"		<default>[*]: {CHILDREN}\n" + 
//			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
//			"				<import container>[*]: {CHILDREN | FINE GRAINED}\n" + 
//			"					import q.Y[-]: {}"
//		);
//	} finally {
//		stopDeltas();
//		deleteFile("P/X.py");
//	}
//}
/**
 * Ensures that a method can be deleted.
 */
public void testDeleteMethod() throws CoreException {
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  def foo(self):\n" +
			"    pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IMethod method = cu.getType("X").getMethod("foo");

		startDeltas();
		assertDeletion(method);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					foo()[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}

////TODO: Implement imports
///**
// * Ensures that multiple member Script elements contained within different
// * compilation units can be deleted.
// * Verifies that the correct changed deltas are generated.
// */
//public void testDeleteMultipleMembersFromVariousCUs() throws CoreException {
//	try {
//		createFolder("P/a/b/c");
//		createFile(
//			"P/a/b/c/X.py",
//			"package a.b.c;\n" +
//			"import java.util.Vector;\n" +
//			"import java.util.Enumeration;\n" +
//			"class X:\n" +
//			"  public static void main(String[] args) {\n" +
//			"    System.out.println(\"Hello World\");\n" +
//			"  }\n" +
//			"  static class Bar {\n" +
//			"    private final java.lang.String test = \"testminor\";\n" +
//			"    public Bar() {\n" +
//			"      super();\n" +
//			"    }\n" +
//			"    private void test() {\n" +
//			"    }\n" +
//			"  }\n" +
//			"    pass"
//		);
//		createFile(
//			"P/a/b/Y.py",
//			"package a.b;\n" +
//			"public class Y {\n" +
//			"  int foo;\n" +
//			"  public static void main(String[] args) {\n" +
//			"    System.out.println(\"Hello World\");\n" +
//			"  }\n" +
//			"    pass"
//		);
//		
//		// elements to be deleted:
//		// from a/b/c/X.py:
//		//   java.util.Vector
//		//	  main
//		//   Bar (inner type)
//		//	    Bar (constructor)
//		//	    test
//		//   Bar (inner type, same as above)
//	
//		// from a/b/Y.py
//		//   foo
//		//   main
//		
//		ISourceModule cuX = getSourceModule("P/a/b/c/X.py");
//		IType typeX = cuX.getType("X");
//		IType typeBar = typeX.getType("Bar");
//	
//		IModelElement[] toBeDeleted = new IModelElement[8];
//		toBeDeleted[0] = cuX.getImport("java.util.Vector");
//		toBeDeleted[1] = typeX.getMethod("main");
//		toBeDeleted[2] = typeBar;
//		toBeDeleted[3] = typeBar.getMethod("Bar");
//		toBeDeleted[4] = typeBar.getMethod("test");
//		toBeDeleted[5] = typeBar;
//		
//		ISourceModule cuY = getSourceModule("P/a/b/Y.py");
//		IType typeY = cuY.getType("Y");
//		
//		toBeDeleted[6] = typeY.getField("foo");
//		toBeDeleted[7] = typeY.getMethod("main");
//	
//		startDeltas();
//		assertDeletion(toBeDeleted);
//		assertDeltas(
//			"Unexpected delta",
//			"P[*]: {CHILDREN}\n" + 
//			"	<project root>[*]: {CHILDREN}\n" + 
//			"		a.b.c[*]: {CHILDREN}\n" + 
//			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
//			"				<import container>[*]: {CHILDREN | FINE GRAINED}\n" + 
//			"					import java.util.Vector[-]: {}\n" + 
//			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
//			"					main(String[])[-]: {}\n" + 
//			"					Bar[-]: {}\n" + 
//			"		a.b[*]: {CHILDREN}\n" + 
//			"			Y.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
//			"				Y[*]: {CHILDREN | FINE GRAINED}\n" + 
//			"					foo[-]: {}\n" + 
//			"					main(String[])[-]: {}"
//		);
//	} finally {
//		stopDeltas();
//		deleteFolder("P/a");
//	}
//}

//TODO: Implement package declarations
///**
// * Ensures that a package declaration can be deleted from a compilation unit.
// */
//public void testDeletePackageDeclaration() throws CoreException {
//	try {
//		createFolder("P/a/b/c");
//		createFile(
//			"P/a/b/c/X.py",
//			"package a.b.c;\n" +
//			"class X:\n" +
//			"    pass"
//		);
//		ISourceModule cu = getSourceModule("P/a/b/c/X.py");
//		IPackageDeclaration packageDeclaration = cu.getPackageDeclaration("a.b.c");
//
//		startDeltas();
//		assertDeletion(packageDeclaration);
//		assertDeltas(
//			"Unexpected delta",
//			"P[*]: {CHILDREN}\n" + 
//			"	<project root>[*]: {CHILDREN}\n" + 
//			"		a.b.c[*]: {CHILDREN}\n" + 
//			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
//			"				package a.b.c[-]: {}"
//		);
//	} finally {
//		stopDeltas();
//		deleteFolder("P/a");
//	}
//}
/**
 * Ensures that a field can be deleted if it contains syntax errors
 */
public void testDeleteSyntaxErrorField() throws CoreException {
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  field=\n" + // missing right side
			"    pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IField field = cu.getType("X").getField("field");

		startDeltas();
		assertDeletion(field);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					field[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
private void assertDeletion(IModelElement element) throws ModelException {
	assertDeletion(new IModelElement[] {element});
	
}

/**
 * Ensures that a method can be deleted if it contains syntax errors
 */
public void testDeleteSyntaxErrorInMethod1() throws CoreException {
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  def foo(self):\n" +
			"    s = \n" +
			"    print s\n" +
			"  pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IMethod method = cu.getType("X").getMethod("foo");

		startDeltas();
		assertDeletion(method);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					foo()[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
/**
 * Ensures that a method can be deleted if it contains syntax errors
 */
public void testDeleteSyntaxErrorInMethod2() throws CoreException {
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  def foo() \n" +
			"    pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IMethod method = cu.getType("X").getMethod("foo");

		startDeltas();
		assertDeletion(method);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					foo()[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
/**
 * Ensures that a method can be deleted if it contains syntax errors
 */
public void testDeleteSyntaxErrorInMethod3() throws CoreException {
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  def foo(self: \n" +
			"    pass\n" +
			"  pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IMethod method = cu.getType("X").getMethod("foo");

		startDeltas();
		assertDeletion(method);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[*]: {CHILDREN | FINE GRAINED}\n" + 
			"					foo()[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
/**
 * Ensures that a type can be deleted if it contains syntax errors
 */
public void testDeleteSyntaxErrorType() throws CoreException {
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"  def method(self):\n" +
			"  pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IType type = cu.getType("X");

		startDeltas();
		assertDeletion(type);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
/**
 * Ensures that a type can be deleted from a compilation unit.
 */
public void testDeleteType1() throws CoreException{
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"    pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		IType type = cu.getType("X");

		startDeltas();
		assertDeletion(type);
		assertDeltas(
			"Unexpected delta",
			"P[*]: {CHILDREN}\n" + 
			"	<project root>[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteFile("P/X.py");
	}
}
/**
 * Delete a type in a default package that is nested
 * in a root folder that is not the project folder.
 */
public void testDeleteType2() throws CoreException {
	try {
		createScriptProject("P1", PYTHON_NATURE, new String[] {"src", "bin"});
		createFile(
			"P1/src/X.py",
			"class X:\n" +
			"    pass"
		);
		ISourceModule cu = getSourceModule("P1/src/X.py");
		IType type = cu.getType("X");

		startDeltas();
		assertDeletion(type);
		assertDeltas(
			"Unexpected delta",
			"P1[*]: {CHILDREN}\n" + 
			"	src[*]: {CHILDREN}\n" + 
			"		<default>[*]: {CHILDREN}\n" + 
			"			X.py[*]: {CHILDREN | FINE GRAINED | PRIMARY RESOURCE}\n" + 
			"				X[-]: {}"
		);
	} finally {
		stopDeltas();
		deleteProject("P1");
	}
}
/**
 * Ensure that the correct exception is thrown for invalid input to the <code>DeleteOperation</code>
 */
public void testDeleteWithInvalidInput() throws CoreException {
	IType type = null;
	try {
		createFile(
			"P/X.py",
			"class X:\n" +
			"    pass"
		);
		ISourceModule cu = getSourceModule("P/X.py");
		type = cu.getType("X");

		getScriptModel().delete(null, false, null);
	} catch (ModelException e) {
		assertTrue("Should be an no elements to process: null supplied", e.getStatus().getCode() == IModelStatusConstants.NO_ELEMENTS_TO_PROCESS);
		try {
			getScriptModel().delete(new IModelElement[] {type}, false, null);
		} catch (ModelException e2) {
			assertTrue("Should be an no elements to process: null in the array supplied", e2.getStatus().getCode() == IModelStatusConstants.NO_ELEMENTS_TO_PROCESS);
		}
		return;
	} finally {
		deleteFile("P/X.py");
	}
}
}
