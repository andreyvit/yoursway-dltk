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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceManipulation;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.CopyMoveTests;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;


public class CopyMoveResourcesTests extends CopyMoveTests {
private static final String[] PYTHON_NATURE = new String[] {
	PythonNature.NATURE_ID
};
	
public CopyMoveResourcesTests(String name) {
	super(  PythonTestsPlugin.PLUGIN_NAME, name );
}
/**
 * Copies the element to the container with optional rename
 * and forcing. The operation should succeed, so any exceptions
 * encountered are thrown.
 */
public IModelElement copyPositive(IModelElement element, IModelElement container, IModelElement sibling, String rename, boolean force) throws ModelException {
	try {
		startDeltas();
		
		// if forcing, ensure that a name collision exists
		if (force) {
			IModelElement collision = generateHandle(element, rename, container);
			assertTrue("Collision does not exist", collision.exists());
		}
	
		// copy
	 	((ISourceManipulation) element).copy(container, sibling, rename, force, null);
	
		// ensure the original element still exists
		assertTrue("The original element must still exist", element.exists());
	
		// generate the new element	handle
		IModelElement copy = generateHandle(element, rename, container);
		assertTrue("Copy should exist", copy.exists());
		//ensure correct position
		if (element.getElementType() > IModelElement.SOURCE_MODULE) {
			ensureCorrectPositioning((IParent) container, sibling, copy);
		} else if (container.getElementType() != IModelElement.SCRIPT_PROJECT) {
			// ensure package name is correct
//			TODO: Package declarations
//			if (container.getElementName().equals("")) {
//				// default package - should be no package decl
//				IModelElement[] children = ((ISourceModule) copy).getChildren();
//				boolean found = false;
//				for (int i = 0; i < children.length; i++) {
//					if (children[i] instanceof IPackageDeclaration) {
//						found = true;
//					}
//				}
//				assertTrue("Should not find package decl", !found);
//			} else {
//				IModelElement[] children = ((ISourceModule) copy).getChildren();
//				boolean found = false;
//				for (int i = 0; i < children.length; i++) {
//					if (children[i] instanceof IPackageDeclaration) {
//						assertTrue("package declaration incorrect", ((IPackageDeclaration) children[i]).getElementName().equals(container.getElementName()));
//						found = true;
//					}
//				}
//				assertTrue("Did not find package decl", found);
//			}
		}
		IModelElementDelta destDelta = getDeltaFor(container, true);
		assertTrue("Destination container not changed", destDelta != null && destDelta.getKind() == IModelElementDelta.CHANGED);
		IModelElementDelta[] deltas = destDelta.getAddedChildren();
		// FIXME: not strong enough
		boolean found = false;
		for (int i = 0; i < deltas.length; i++) {
			if (deltas[i].getElement().equals(copy))
				found = true;
		}
		assertTrue("Added children not correct for element copy", found);
		return copy;
	} finally {
		stopDeltas();
	}
}

/**
 * Moves the elements to the containers with optional renaming
 * and forcing. The operation should succeed, so any exceptions
 * encountered are thrown.
 */
public void movePositive(IModelElement[] elements, IModelElement[] destinations, IModelElement[] siblings, String[] names, boolean force, IProgressMonitor monitor) throws ModelException {
	try {
		startDeltas();
		
		// if forcing, ensure that a name collision exists
		int i;
		if (force) {
			for (i = 0; i < elements.length; i++) {
				IModelElement e = elements[i];
				IModelElement collision = null;
				if (names == null) {
					collision = generateHandle(e, null, destinations[i]);
				} else {
					collision = generateHandle(e, names[i], destinations[i]);
				}
				assertTrue("Collision does not exist", collision.exists());
			}
		}
	
		// move
		getScriptModel().move(elements, destinations, siblings, names, force, monitor);
	
		for (i = 0; i < elements.length; i++) {
			IModelElement element = elements[i];
			IModelElement moved = null;
			if (names == null) {
				moved = generateHandle(element, null, destinations[i]);
			} else {
				moved = generateHandle(element, names[i], destinations[i]);
			}
			// ensure the original element no longer exists, unless moving within the same container, or moving a primary working copy
			if (!destinations[i].equals(element.getParent())) {
				if (element.getElementType() != IModelElement.SOURCE_MODULE || !((ISourceModule) element).isWorkingCopy())
					assertTrue("The original element must not exist", !element.exists());
			}
			assertTrue("Moved element should exist", moved.exists());
	
//			TODO: Package declarations
//			IModelElement container = destinations[i];
//			if (container.getElementType() == IModelElement.SCRIPT_FOLDER) {
//				if (container.getElementName().equals("")) {
//					// default package
//					if (moved.getElementType() == IModelElement.SOURCE_MODULE) {
//						IModelElement[] children = ((ISourceModule) moved).getChildren();
//						for (int j = 0; j < children.length; j++) {
//							if (children[j].getElementType() == IModelElement.PACKAGE_DECLARATION) {
//								assertTrue("Should not find package decl", false);
//							}
//						}
//					}
//				} else {
//					IModelElement[] children = ((ISourceModule) moved).getChildren();
//					boolean found = false;
//					for (int j = 0; j < children.length; j++) {
//						if (children[j] instanceof IPackageDeclaration) {
//							assertTrue("package declaration incorrect", ((IPackageDeclaration) children[j]).getElementName().equals(container.getElementName()));
//							found = true;
//							break;
//						}
//					}
//					assertTrue("Did not find package decl", found);
//				}
//			}
			IModelElementDelta destDelta = null;
			if (isMainType(element, destinations[i]) && names != null && names[i] != null) { //moved/renamed main type to same cu
				destDelta = getDeltaFor(moved.getParent());
				assertTrue("Renamed compilation unit as result of main type not added", destDelta != null && destDelta.getKind() == IModelElementDelta.ADDED);
				IModelElementDelta[] deltas = destDelta.getAddedChildren();
				assertTrue("Added children not correct for element copy", deltas[0].getElement().equals(moved));
				assertTrue("flag should be F_MOVED_FROM", (deltas[0].getFlags() & IModelElementDelta.F_MOVED_FROM) > 0);
				assertTrue("moved from handle should be original", deltas[0].getMovedFromElement().equals(element));
			} else {
				destDelta = getDeltaFor(destinations[i], true);
				assertTrue("Destination container not changed", destDelta != null && destDelta.getKind() == IModelElementDelta.CHANGED);
				IModelElementDelta[] deltas = destDelta.getAddedChildren();
				for (int j = 0; j < deltas.length - 1; j++) {
					// side effect packages added
					IModelElement pkg = deltas[j].getElement();
					assertTrue("Side effect child should be a package fragment", pkg.getElementType() == IModelElement.SCRIPT_FOLDER);
					assertTrue("Side effect child should be an enclosing package", element.getElementName().startsWith(pkg.getElementName()));
				}
				IModelElementDelta pkgDelta = deltas[deltas.length - 1];
				assertTrue("Added children not correct for element copy", pkgDelta.getElement().equals(moved));
				assertTrue("flag should be F_MOVED_FROM", (pkgDelta.getFlags() & IModelElementDelta.F_MOVED_FROM) > 0);
				assertTrue("moved from handle shoud be original", pkgDelta.getMovedFromElement().equals(element));
				IModelElementDelta sourceDelta = getDeltaFor(element, true);
				assertTrue("moved to handle should be original", sourceDelta.getMovedToElement().equals(moved));
			}
		}
	} finally {
		stopDeltas();
	}
}
/**
 * Setup for the next test.
 */
public void setUp() throws Exception {
	super.setUp();
	
	this.createScriptProject("P", PYTHON_NATURE, new String[] {"src", "src2", "bin"});
}
static {
//	TESTS_NAMES = new String[] { "testCopyWorkingCopyDestination"};
}
public static Test suite() {
	return new Suite(CopyMoveResourcesTests.class);
}
/**
 * Cleanup after the previous test.
 */
public void tearDown() throws Exception {
	this.deleteProject("P");
	
	super.tearDown();
}
//TODO: Package declarations
///**
// * Ensures that a CU can be copied to a different package.
// */
//public void testCopyCU() throws CoreException {
//	this.createFolder("/P/src/p1");
//	this.createFile(
//		"/P/src/p1/X.py",
//		"class X:\n" +
//		"    pass"
//	);
//	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
//
//	this.createFolder("/P/src/p2");
//	IScriptFolder pkgDest = getPackage("/P/src/p2");
//
//	copyPositive(cuSource, pkgDest, null, null, false);
//	
//	ISourceModule cu= pkgDest.getSourceModule("X.py");
//	assertTrue("Package declaration not updated for copied cu", cu.getPackageDeclaration("p2").exists());
//}
/**
 * This operation should fail as copying a CU and a CU member at the
 * same time is not supported.
 */
public void testCopyCUAndType() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	copyNegative(
		new IModelElement[]{cuSource, cuSource.getType("X")}, 
		new IModelElement[]{cuSource.getParent(), cuSource}, 
		null, 
		new String[]{"Y.py", "Y"}, 
		false, 
		IModelStatusConstants.INVALID_ELEMENT_TYPES);
}
/**
 * Ensures that a CU can be copied to a different package, replacing an existing CU.
 */
public void testCopyCUForce() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	this.createFile(
		"/P/src/p2/X.py",
		"package p2;\n" +
		"class X:\n" +
		"    pass"
	);
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	copyPositive(cuSource, pkgDest, null, null, true);
}

//TODO: Package declarations
///**
// * Ensures that a CU can be copied from a default package to a non-default package.
// */
//public void testCopyCUFromDefaultToNonDefault() throws CoreException {
//	createFile(
//		"/P/src/X.py",
//		"class X:\n" +
//		"    pass"
//	);
//	ISourceModule cuSource = getSourceModule("/P/src/X.py");
//
//	createFolder("/P/src/p");
//	IScriptFolder pkgDest = getPackage("/P/src/p");
//
//	copyPositive(cuSource, pkgDest, null, null, false);
//	
//	ISourceModule cu= pkgDest.getSourceModule("X.py");
//	assertTrue("Package declaration not updated for copied cu", cu.getPackageDeclaration("p").exists());
//}
/**
 * Ensures that a CU can be copied to a different package,
 * and be renamed.
 */
public void testCopyCURename() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	copyPositive(cuSource, pkgDest, null, "Y.py", false);
}
/**
 * Ensures that a read-only CU can be copied to a different package.
 */
public void testCopyCUReadOnly() throws CoreException {
	IFile file = null;
	IFile file2 = null;
	try {
		this.createFolder("/P/src/p1");
		file = this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		setReadOnly(file, true);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
	
		this.createFolder("/P/src/p2");
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		copyPositive(cuSource, pkgDest, null, null, false);
		
		file2 = getFile("/P/src/p2/X.py");
		assertTrue("Destination cu should be read-only", file2.isReadOnly());
	} finally {
		if (file != null) {
			setReadOnly(file, false);
		}
		if (file2 != null) {
			setReadOnly(file2, false);
		}
		deleteFolder("/P/src/p1");
		deleteFolder("/P/src/p2");
	}
}
/**
 * Ensures that a CU can be copied to a different package,
 * and be renamed, overwriting an existing CU
 */
public void testCopyCURenameForce() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	this.createFile(
		"/P/src/p2/Y.py",
		"package p2;\n" +
		"public class Y {\n" +
		"    pass"
	);
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	copyPositive(cuSource, pkgDest, null, "Y.py", true);
}
/**
 * Ensures that a CU cannot be copied to a different package,over an existing CU when no force.
 */
public void testCopyCUWithCollision() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	this.createFile(
		"/P/src/p2/X.py",
		"package p2;\n" +
		"class X:\n" +
		"    pass"
	);
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	copyNegative(cuSource, pkgDest, null, null, false, IModelStatusConstants.NAME_COLLISION);
}
/**
 * Ensures that a CU cannot be copied to an invalid destination
 */
public void testCopyCUWithInvalidDestination() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	copyNegative(cuSource, cuSource, null, null, false, IModelStatusConstants.INVALID_DESTINATION);
}
/**
 * Ensures that a CU can be copied to a null container
 */
public void testCopyCUWithNullContainer() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	try {
		cuSource.copy(null, null, null, false, null);
	} catch (IllegalArgumentException iae) {
		return;
	}
	assertTrue("Should not be able to move a cu to a null container", false);
}
/**
 * Ensures that a CU can be copied to along with its server properties.
 * (Regression test for PR #1G56QT9)
 */
public void testCopyCUWithServerProperties() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	QualifiedName qualifiedName = new QualifiedName("x.y.z", "a property");
	cuSource.getUnderlyingResource().setPersistentProperty(
		qualifiedName,
		"some value");

	this.createFolder("/P/src/p2");
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	copyPositive(cuSource, pkgDest, null, null, false);
	ISourceModule cu= pkgDest.getSourceModule("X.py");
	String propertyValue = cu.getUnderlyingResource().getPersistentProperty(qualifiedName);
	assertEquals(
		"Server property should be copied with cu",
		"some value",
		propertyValue
	);
}
/**
 * Ensures that a package fragment can be copied to a different package fragment root.
 */
public void testCopyScriptFolder() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	IScriptFolder pkgSource = getPackage("/P/src/p1");

	IProjectFragment rootDest= getProjectFragment("P", "src2");

	copyPositive(pkgSource, rootDest, null, null, false);
}
/**
 * Ensures that a package fragment can be copied to a different package fragment root.
 */
public void testCopyReadOnlyScriptFolder() throws CoreException {
	IScriptFolder pkgSource = null;
	IScriptFolder pkg2 = null;
	try {
		this.createFolder("/P/src/p1/p2/p3");
		this.createFile(
			"/P/src/p1/p2/p3/X.py",
//			"package p1.p2.p3;\n" +
			"class X:\n" +
			"    pass"
		);
		setReadOnly(getFile("/P/src/p1/p2/p3/X.py"), true);
		pkgSource = getPackage("/P/src/p1");
		setReadOnly(pkgSource.getResource(), true);
		pkg2 = getPackage("/P/src/p1/p2/p3");
		setReadOnly(pkg2.getResource(), true);
	
		IProjectFragment rootDest= getProjectFragment("P", "src2");
	
		copyPositive(pkg2, rootDest, null, null, false);
		
		assertTrue("Not readOnly", isReadOnly(getPackage("/P/src2/p1").getResource()));
		assertTrue("Is readOnly", !isReadOnly(getPackage("/P/src2/p1/p2").getResource()));
		assertTrue("Not readOnly", isReadOnly(getPackage("/P/src2/p1/p2/p3").getResource()));
		assertTrue("Is readOnly", isReadOnly(getFile("/P/src2/p1/p2/p3/X.py")));
	} finally {
		IFile xSrcFile = getFile("/P/src/p1/p2/p3/X.py");
		if (xSrcFile != null) {
			setReadOnly(xSrcFile, false);
		}
		if (pkg2 != null) {
			setReadOnly(pkg2.getResource(), false);
		}
		if (pkgSource != null) {
			setReadOnly(pkgSource.getResource(), false);
		}
		IScriptFolder p1Fragment = getPackage("/P/src2/p1");
		if (p1Fragment != null) {
			setReadOnly(p1Fragment.getResource(), false);
		}
		IScriptFolder p3Fragment = getPackage("/P/src2/p1/p2/p3");
		if (p3Fragment != null) {
			setReadOnly(p3Fragment.getResource(), false);
		}
		IFile xFile = getFile("/P/src2/p1/p2/p3/X.py");
		if (xFile != null) {
			setReadOnly(xFile, false);
		}
		deleteFolder("/P/src/p1");
	}
}
/**
 * Ensures that a WorkingCopy can be copied to a different package.
 */
public void testCopyWorkingCopy() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		this.createFolder("/P/src/p2");
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		copyPositive(copy, pkgDest, null, null, false);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
/*
 * Ensures that a CU can be copied over an existing primary working copy in a different package.
 * (regression test for bug 117282 Package declaration inserted on wrong CU while copying class if names collide and editor opened)
 */
public void testCopyWorkingCopyDestination() throws CoreException {
	ISourceModule copy = null;
	try {
		createFolder("/P/src/p1");
		createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"  void foo() {}\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
	
		createFolder("/P/src/p2");
		IScriptFolder pkgDest = getPackage("/P/src/p2");
		createFile(
			"/P/src/p2/X.py",
			"\n" +
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		copy = getSourceModule("/P/src/p2/X.py");
		copy.becomeWorkingCopy(null, null);
	
		copyPositive(cuSource, pkgDest, null, null, true/*force*/);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
/**
 * Ensures that a WorkingCopy can be copied to a different package, replacing an existing WorkingCopy.
 */
public void testCopyWorkingCopyForce() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		this.createFolder("/P/src/p2");
		this.createFile(
			"/P/src/p2/X.py",
			"package p2;\n" +
			"class X:\n" +
			"    pass"
		);
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		copyPositive(copy, pkgDest, null, null, true);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
/**
 * Ensures that a WorkingCopy can be copied to a different package,
 * and be renamed.
 */
public void testCopyWorkingCopyRename() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		this.createFolder("/P/src/p2");
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		copyPositive(copy, pkgDest, null, "Y.py", false);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
/**
 * Ensures that a WorkingCopy can be copied to a different package,
 * and be renamed, overwriting an existing WorkingCopy
 */
public void testCopyWorkingCopyRenameForce() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		this.createFolder("/P/src/p2");
		this.createFile(
			"/P/src/p2/Y.py",
			"package p2;\n" +
			"public class Y {\n" +
			"    pass"
		);
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		copyPositive(copy, pkgDest, null, "Y.py", true);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
/**
 * Ensures that a WorkingCopy cannot be copied to a different package,over an existing WorkingCopy when no force.
 */
public void testCopyWorkingCopyWithCollision() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		this.createFolder("/P/src/p2");
		this.createFile(
			"/P/src/p2/X.py",
			"package p2;\n" +
			"class X:\n" +
			"    pass"
		);
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		copyNegative(copy, pkgDest, null, null, false, IModelStatusConstants.NAME_COLLISION);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
/**
 * Ensures that a WorkingCopy cannot be copied to an invalid destination
 */
public void testCopyWorkingCopyWithInvalidDestination() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		copyNegative(copy, cuSource, null, null, false, IModelStatusConstants.INVALID_DESTINATION);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
//TODO: Package declarations
///**
// * Ensures that a CU can be moved to a different package.
// */
//public void testMoveCU() throws CoreException {
//	this.createFolder("/P/src/p1");
//	this.createFile(
//		"/P/src/p1/X.py",
//		"package p1;\n" +
//		"class X:\n" +
//		"    pass"
//	);
//	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
//
//	this.createFolder("/P/src/p2");
//	IScriptFolder pkgDest = getPackage("/P/src/p2");
//
//	movePositive(cuSource, pkgDest, null, null, false);
//	
//	ISourceModule cu= pkgDest.getSourceModule("X.py");
//	assertTrue("Package declaration not updated for copied cu", cu.getPackageDeclaration("p2").exists());
//}

//TODO: Implement moving of members
///**
// * This operation should fail as moving a CU and a CU member at the
// * same time is not supported.
// */
//public void testMoveCUAndType() throws CoreException {
//	this.createFolder("/P/src/p1");
//	this.createFile(
//		"/P/src/p1/X.py",
////		"package p1;\n" +
//		"class T:\n" +
//		"    pass"
//	);
//	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
//
//	moveNegative(
//		new IModelElement[]{cuSource, cuSource.getType("T")}, 
//		new IModelElement[]{cuSource.getParent(), cuSource}, 
//		null, 
//		new String[]{"Y.py", "Y"}, 
//		false, 
//		IModelStatusConstants.INVALID_ELEMENT_TYPES);
//}
/**
 * Ensures that a CU can be moved to a different package, replacing an
 * existing CU.
 */
public void testMoveCUForce() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	this.createFile(
		"/P/src/p2/X.py",
		"package p2;\n" +
		"class X:\n" +
		"    pass"
	);
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	movePositive(cuSource, pkgDest, null, null, true);
}
/**
 * Ensures that a CU can be moved to a different package,
 * be renamed
 */
public void testMoveCURename() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	movePositive(cuSource, pkgDest, null, "Y.py", false);
}
/**
 * Ensures that a CU can be moved to a different package,
 * be renamed, overwriting an existing resource.
 */
public void testMoveCURenameForce() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	this.createFile(
		"/P/src/p2/Y.py",
		"package p2;\n" +
		"public class Y {\n" +
		"    pass"
	);
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	movePositive(cuSource, pkgDest, null, "Y.py", true);
}
/**
 * Ensures that a CU cannot be moved to a different package, replacing an
 * existing CU when not forced.
 */
public void testMoveCUWithCollision() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	this.createFolder("/P/src/p2");
	this.createFile(
		"/P/src/p2/X.py",
		"package p2;\n" +
		"class X:\n" +
		"    pass"
	);
	IScriptFolder pkgDest = getPackage("/P/src/p2");

	moveNegative(cuSource, pkgDest, null, null, false, IModelStatusConstants.NAME_COLLISION);
}
/**
 * Ensures that a CU cannot be moved to an invalid destination.
 */
public void testMoveCUWithInvalidDestination() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	moveNegative(cuSource, cuSource, null, null, false, IModelStatusConstants.INVALID_DESTINATION);
}
/**
 * Ensures that a CU cannot be moved to a null container
 */
public void testMoveCUWithNullContainer() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");

	try {
		cuSource.move(null, null, null, false, null);
	} catch (IllegalArgumentException iae) {
		return;
	}
	assertTrue("Should not be able to move a cu to a null container", false);
}
/**
 * Ensures that a package fragment can be moved to a different package fragment root.
 */
public void testMoveScriptFolder() throws CoreException {
	this.createFolder("/P/src/p1");
	this.createFile(
		"/P/src/p1/X.py",
//		"package p1;\n" +
		"class X:\n" +
		"    pass"
	);
	IScriptFolder pkgSource = getPackage("/P/src/p1");

	IProjectFragment rootDest= getProjectFragment("P", "src2");

	movePositive(pkgSource, rootDest, null, null, false);
}
/**
 * Ensures that a package fragment can be copied to a different package fragment root.
 */
public void testMoveReadOnlyScriptFolder() throws CoreException {
	IScriptFolder pkgSource = null;
	IScriptFolder pkg2 = null;
	try {
		this.createFolder("/P/src/p1/p2/p3");
		this.createFile(
			"/P/src/p1/p2/p3/X.py",
//			"package p1.p2.p3;\n" +
			"class X:\n" +
			"    pass"
		);
		setReadOnly(getFile("/P/src/p1/p2/p3/X.py"), true);
		pkgSource = getPackage("/P/src/p1");
		setReadOnly(pkgSource.getResource(), true);
		pkg2 = getPackage("/P/src/p1/p2/p3");
		setReadOnly(pkg2.getResource(), true);
	
		IProjectFragment rootDest= getProjectFragment("P", "src2");
	
		movePositive(pkg2, rootDest, null, null, false);
		
		assertTrue("Not readOnly", isReadOnly(getPackage("/P/src2/p1").getResource()));
		assertTrue("Is readOnly", !isReadOnly(getPackage("/P/src2/p1/p2").getResource()));
		assertTrue("Not readOnly", isReadOnly(getPackage("/P/src2/p1/p2/p3").getResource()));
		assertTrue("Is readOnly", isReadOnly(getFile("/P/src2/p1/p2/p3/X.py")));
	} finally {
		IFile xSrcFile = getFile("/P/src/p1/p2/p3/X.py");
		if (xSrcFile != null) {
			setReadOnly(xSrcFile, false);
		}
		if (pkg2 != null) {
			setReadOnly(pkg2.getResource(), false);
		}
		if (pkgSource != null) {
			setReadOnly(pkgSource.getResource(), false);
		}
		IScriptFolder p1Fragment = getPackage("/P/src2/p1");
		if (p1Fragment != null) {
			setReadOnly(p1Fragment.getResource(), false);
		}
		IScriptFolder p3Fragment = getPackage("/P/src2/p1/p2/p3");
		if (p3Fragment != null) {
			setReadOnly(p3Fragment.getResource(), false);
		}
		IFile xFile = getFile("/P/src2/p1/p2/p3/X.py");
		if (xFile != null) {
			setReadOnly(xFile, false);
		}
		deleteFolder("/P/src/p1");
	}
}
/**
 * Ensures that a WorkingCopy cannot be moved to a different package.
 */
public void testMoveWorkingCopy() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		ISourceModule cuSource = getSourceModule("/P/src/p1/X.py");
		copy = cuSource.getWorkingCopy(null);
	
		this.createFolder("/P/src/p2");
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		moveNegative(copy, pkgDest, null, null, false, IModelStatusConstants.INVALID_ELEMENT_TYPES);
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}

/*
 * Ensures that a primary working copy can be moved to a different package
 * and that its buffer doesn't contain unsaved changed after the move.
 * (regression test for bug 83599 CU dirty after move refactoring)
 */
public void testMoveWorkingCopy2() throws CoreException {
	ISourceModule copy = null;
	try {
		this.createFolder("/P/src/p1");
		this.createFile(
			"/P/src/p1/X.py",
//			"package p1;\n" +
			"class X:\n" +
			"    pass"
		);
		copy = getSourceModule("/P/src/p1/X.py");
		copy.becomeWorkingCopy(null, null);
	
		this.createFolder("/P/src/p2");
		IScriptFolder pkgDest = getPackage("/P/src/p2");
	
		movePositive(copy, pkgDest, null, null, false);
		assertTrue("Should not have unsaved changes", !copy.getBuffer().hasUnsavedChanges());
	} finally {
		if (copy != null) copy.discardWorkingCopy();
	}
}
}
