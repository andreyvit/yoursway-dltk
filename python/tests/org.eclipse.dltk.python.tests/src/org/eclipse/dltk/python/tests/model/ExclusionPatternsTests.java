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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.ModifyingResourceTests;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;


public class ExclusionPatternsTests extends ModifyingResourceTests {
	private static final String[] PYTHON_NATURE = { PythonNature.NATURE_ID };
	IScriptProject project;
	public ExclusionPatternsTests(String name) {
		super(  PythonTestsPlugin.PLUGIN_NAME, name );
	}

protected void setBuildpath(String[] sourceFoldersAndExclusionPatterns) throws ModelException {
	this.project.setRawBuildpath(createBuildpath(sourceFoldersAndExclusionPatterns, false/*no inclusion*/, true/*exclusion*/), null);
}
protected void setUp() throws Exception {
	super.setUp();
	this.project = createScriptProject("P", PYTHON_NATURE, new String[] {"src", "bin"});
	this.project.setRawBuildpath(createBuildpath(new String[]{"/P/bin"}), null);
	startDeltas();
}

// Use this static initializer to specify subset for tests
// All specified tests which do not belong to the class are skipped...
static {
//		TESTS_NAMES = new String[] { "testCreateExcludedPackage2" };
//		TESTS_NUMBERS = new int[] { 2, 12 };
//		TESTS_RANGE = new int[] { 16, -1 };
}
public static Test suite() {
	return new Suite(ExclusionPatternsTests.class);
}

protected void tearDown() throws Exception {
	stopDeltas();
	deleteProject("P");
	super.tearDown();
}
/*
 * Ensure that adding an exclusion on a compilation unit
 * makes it disappear from the children of its package and it is added to the non-java resources.
 */
public void testAddExclusionOnSourceModule() throws CoreException {
	createFolder("/P/src/p");
	createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
	
	clearDeltas();
	setBuildpath(new String[] {"/P/src", "**/A.py"});
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN | BUILDPATH CHANGED}\n" + 
		"	src[*]: {ADDED TO BUILDPATH | REMOVED FROM BUILDPATH}\n" + 
		"	ResourceDelta(/P/.buildpath)[*]"
	);
	
	IScriptFolder pkg = getPackage("/P/src/p");
	assertSortedElementsEqual(
		"Unexpected children",
		"",
		pkg.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"A.py",
		pkg.getForeignResources());
}
/*
 * Ensure that adding an exclusion on a folder directly under a project (and prj=src)
 * makes it appear as a non-java resources.
 * (regression test for bug 29374 Excluded folder on project not returned by Script Model)
 */
public void testAddExclusionOnFolderUnderProject() throws CoreException {
	try {
		IScriptProject scriptProject = createScriptProject("P1", PYTHON_NATURE, new String[] {""});
		createFolder("/P1/doc");

		clearDeltas();
		scriptProject.setRawBuildpath(createBuildpath(new String[] {"/P1", "doc/"}, false/*no inclusion*/, true/*exclusion*/), null);
		//
	
		assertDeltas(
			"Unexpected deltas",
			"P1[*]: {CHILDREN | BUILDPATH CHANGED}\n" + 
			"	<project root>[*]: {ADDED TO BUILDPATH | REMOVED FROM BUILDPATH}\n" + 
			"	ResourceDelta(/P1/.buildpath)[*]"
		);
	
		IProjectFragment root = getProjectFragment("/P1");
		assertSortedElementsEqual(
			"Unexpected children",
			"<default> [in <project root> [in P1]]",
			root.getChildren());
		
		assertResourceNamesEqual(
			"Unexpected non-java resources of project",
			".buildpath\n" +
			".project\n" +
			"doc",
			scriptProject.getForeignResources());
	} finally {
		deleteProject("P1");
	}
}

/*
 * Ensure that adding an exclusion on a package
 * makes it disappear from the children of its package fragment root 
 * and it is added to the non-java resources.
 */
public void testAddExclusionOnPackage() throws CoreException {
	createFolder("/P/src/p");
	
	clearDeltas();
	setBuildpath(new String[] {"/P/src", "p/"});
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN | BUILDPATH CHANGED}\n" + 
		"	src[*]: {ADDED TO BUILDPATH | REMOVED FROM BUILDPATH}\n" + 
		"	ResourceDelta(/P/.buildpath)[*]"
	);
	
	IProjectFragment root = getProjectFragment("/P/src");
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"p",
		root.getForeignResources());
}
/*
 * Ensure that adding an exclusion on a primary working copy
 * makes it disappear from the children of its package and it is added to the non-java resources.
 */
public void testAddExclusionOnPrimaryWorkingCopy() throws CoreException {
	createFolder("/P/src/p");
	createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
		
	ISourceModule workingCopy = null;
	try {
		workingCopy = getSourceModule("/P/src/p/A.py");
		workingCopy.becomeWorkingCopy(null, null);
		
		clearDeltas();
		setBuildpath(new String[] {"/P/src", "**/A.py"});
		
		assertDeltas(
			"Unexpected deltas",
			"P[*]: {CHILDREN | BUILDPATH CHANGED}\n" + 
			"	src[*]: {ADDED TO BUILDPATH | REMOVED FROM BUILDPATH}\n" + 
			"	ResourceDelta(/P/.buildpath)[*]"
		);
		
		IScriptFolder pkg = getPackage("/P/src/p");
		assertSortedElementsEqual(
			"Unexpected children",
			"",
			pkg.getChildren());
			
		assertResourceNamesEqual(
			"Unexpected non-java resources",
			"A.py",
			pkg.getForeignResources());
	} finally {
		if (workingCopy != null) {
			workingCopy.discardWorkingCopy();
		}
	}
}
/*
 * Ensure that adding a file to a folder that is excluded reports the correct delta.
 * (regression test for bug 29621 Wrong Delta When Adding to Filtered Folder)
 */
public void testAddToExcludedFolder() throws CoreException {
	createFolder("/P/src/icons");
	
	// exclude folder and its contents
	setBuildpath(new String[] {"/P/src", "icons/"});
	
	clearDeltas();
	createFile("/P/src/icons/my.txt", "");
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CONTENT}\n" + 
		"		ResourceDelta(/P/src/icons)[*]"
	);
	
	clearDeltas();
	deleteFile("/P/src/icons/my.txt");
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CONTENT}\n" + 
		"		ResourceDelta(/P/src/icons)[*]"
	);
}
/*
 * Ensure that creating an excluded compilation unit 
 * doesn't make it appear as a child of its package but it is a non-java resource.
 */
public void testCreateExcludedSourceModule() throws CoreException {
	setBuildpath(new String[] {"/P/src", "**/A.py"});
	createFolder("/P/src/p");
	IScriptFolder pkg = getPackage("/P/src/p");

	clearDeltas();
	pkg.createSourceModule(
		"A.py",
		"package p;\n" +
		"public class A {\n" +
		"}",
		false,
		null);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN}\n" + 
		"		p[*]: {CONTENT}\n" + 
		"			ResourceDelta(/P/src/p/A.py)[+]"
	);
	
	assertSortedElementsEqual(
		"Unexpected children",
		"",
		pkg.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"A.py",
		pkg.getForeignResources());
}
/*
 * Ensure that crearing an excluded package 
 * doesn't make it appear as a child of its package fragment root but it is a non-java resource.
 */
public void testCreateExcludedPackage() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p/"});
	IProjectFragment root = getProjectFragment("/P/src");
	
	clearDeltas();
	root.createScriptFolder("p", false, null);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CONTENT}\n" + 
		"		ResourceDelta(/P/src/p)[+]"
	);
	
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"p",
		root.getForeignResources());
}
/*
 * Ensure that crearing an excluded package doesn't make it appear as a child of its package fragment root but it is a non-java resource.
 * (regression test for bug 65637 [model] Excluded package still in Script model)
 */
public void testCreateExcludedPackage2() throws CoreException {
	setBuildpath(new String[] {"/P/src", "org/*|org/eclipse/*"});
	
	clearDeltas();
	createFolder("/P/src/org/eclipse/mypack");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CONTENT}\n" + 
		"		ResourceDelta(/P/src/org)[+]"
	);
	
	IProjectFragment root = getProjectFragment("/P/src");
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]\n" + 
		"org.eclipse.mypack [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"org",
		root.getForeignResources());
}
/*
 * Ensure that creating a folder that represents an excluded and included package
 * have the correct delta, Script elements and non-Java resources.
 * (regression test for 67789 Script element delta from refresh contains excluded package)
 */
public void testCreateExcludedAndIncludedPackages() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p1/p2/"});
	IProjectFragment root = getProjectFragment("/P/src");
	
	clearDeltas();
	createFolder("/P/src/p1/p2");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN}\n" + 
		"		p1[+]: {}"
	);
	
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]\n" + 
		"p1 [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"p2",
		root.getScriptFolder("p1").getForeignResources());
}
/*
 * Ensure that creating a file that corresponds to an excluded compilation unit 
 * doesn't make it appear as a child of its package but it is a non-java resource.
 */
public void testCreateResourceExcludedSourceModule() throws CoreException {
	setBuildpath(new String[] {"/P/src", "**/A.py"});
	createFolder("/P/src/p");
	
	clearDeltas();
	createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN}\n" + 
		"		p[*]: {CONTENT}\n" + 
		"			ResourceDelta(/P/src/p/A.py)[+]"
	);
	
	IScriptFolder pkg = getPackage("/P/src/p");
	assertSortedElementsEqual(
		"Unexpected children",
		"",
		pkg.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"A.py",
		pkg.getForeignResources());
}
/*
 * Ensure that creating a folder that corresponds to an excluded package 
 * doesn't make it appear as a child of its package fragment root but it is a non-java resource.
 */
public void testCreateResourceExcludedPackage() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p/"});
	
	clearDeltas();
	createFolder("/P/src/p");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CONTENT}\n" + 
		"		ResourceDelta(/P/src/p)[+]"
	);
	
	IProjectFragment root = getProjectFragment("/P/src");
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"p",
		root.getForeignResources());
}
/*
 * Ensures that a cu that is not excluded is on the buildpath of the project.
 */
public void testIsOnClasspath1() throws CoreException {
	setBuildpath(new String[] {"/P/src", ""});
	createFolder("/P/src/p");
	IFile file = createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
	assertTrue("Resource should be on buildpath", project.isOnBuildpath(file));
	
	ISourceModule cu = getSourceModule("/P/src/p/A.py");
	assertTrue("CU should be on buildpath", project.isOnBuildpath(cu));
}
/*
 * Ensures that a cu that is excluded is not on the buildpath of the project.
 */
public void testIsOnClasspath2() throws CoreException {
	setBuildpath(new String[] {"/P/src", "**/A.py"});
	createFolder("/P/src/p");
	IFile file = createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
	assertTrue("Resource should not be on buildpath", !project.isOnBuildpath(file));
	
	ISourceModule cu = getSourceModule("/P/src/p/A.py");
	assertTrue("CU should not be on buildpath", !project.isOnBuildpath(cu));
}
/*
 * Ensures that a non-java resource that is not excluded is on the buildpath of the project.
 */
public void testIsOnClasspath3() throws CoreException {
	setBuildpath(new String[] {"/P/src", ""});
	createFolder("/P/src/p");
	IFile file = createFile("/P/src/p/readme.txt", "");
	assertTrue("Resource should be on buildpath", project.isOnBuildpath(file));
}
/*
 * Ensures that a non-java resource that is excluded is not on the buildpath of the project.
 */
public void testIsOnClasspath4() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p/**"});
	createFolder("/P/src/p");
	IFile file = createFile("/P/src/p/readme.txt", "");
	assertTrue("Resource should not be on buildpath", !project.isOnBuildpath(file));
}
/*
 * Ensures that an excluded nested source folder doesn't appear as a non-java resource of the outer folder.
 * (regression test for bug 28115 Ubiquitous resource in the ScriptModel)
 * 
 */
public void testNestedSourceFolder1() throws CoreException {
	setBuildpath(new String[] {"/P/src1", "src2/**", "/P/src1/src2", ""});
	createFolder("/P/src1/src2");
	IProjectFragment root1 = getProjectFragment("/P/src1");
	assertResourceNamesEqual(
		"Unexpected non-java resources for /P/src1",
		"",
		root1.getForeignResources());
}
/*
 * Ensures that adding a .py file in a nested source folder reports 
 * a delta on the nested source folder and not on the outer one.
 */
public void testNestedSourceFolder2() throws CoreException {
	setBuildpath(new String[] {"/P/src1", "src2/**", "/P/src1/src2", ""});
	createFolder("/P/src1/src2");
	
	clearDeltas();
	createFile(
		"/P/src1/src2/A.py",
		"public class A {\n" +
		"}"
	);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src1/src2[*]: {CHILDREN}\n" + 
		"		<default>[*]: {CHILDREN}\n" + 
		"			A.py[+]: {}"
	);
}
/*
 * Ensures that adding a .txt file in a nested source folder reports 
 * a resource delta on the nested source folder and not on the outer one.
 */
public void testNestedSourceFolder3() throws CoreException {
	setBuildpath(new String[] {"/P/src1", "src2/**", "/P/src1/src2", ""});
	createFolder("/P/src1/src2");
	
	clearDeltas();
	createFile("/P/src1/src2/readme.txt", "");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src1/src2[*]: {CONTENT}\n" + 
		"		ResourceDelta(/P/src1/src2/readme.txt)[+]"
	);
}
/*
 * Ensures that adding a folder in a nested source folder reports 
 * a delta on the nested source folder and not on the outer one.
 */
public void testNestedSourceFolder4() throws CoreException {
	setBuildpath(new String[] {"/P/src1", "src2/**", "/P/src1/src2", ""});
	createFolder("/P/src1/src2");
	
	clearDeltas();
	createFolder("/P/src1/src2/p");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src1/src2[*]: {CHILDREN}\n" + 
		"		p[+]: {}"
	);
}
/*
 * Ensures that adding a folder in a outer source folder reports 
 * a delta on the outer source folder and not on the nested one.
 */
public void testNestedSourceFolder5() throws CoreException {
	setBuildpath(new String[] {"/P/src1", "src2/**", "/P/src1/src2", ""});
	createFolder("/P/src1/src2");
	
	clearDeltas();
	createFolder("/P/src1/p");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src1[*]: {CHILDREN}\n" + 
		"		p[+]: {}"
	);
}
/*
 * Ensures that moving a package from an outer source folder to a nested
 * source folder reports a move delta.
 */
public void testNestedSourceFolder6() throws CoreException {
	setBuildpath(new String[] {"/P/src1", "src2/**", "/P/src1/src2", ""});
	createFolder("/P/src1/src2");
	createFolder("/P/src1/p");
	
	clearDeltas();
	moveFolder("/P/src1/p", "/P/src1/src2/p");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src1[*]: {CHILDREN}\n" + 
		"		p[-]: {MOVED_TO(p [in src1/src2 [in P]])}\n" + 
		"	src1/src2[*]: {CHILDREN}\n" + 
		"		p[+]: {MOVED_FROM(p [in src1 [in P]])}"
	);
}
/*
 * Ensure that renaming an excluded compilation unit so that it is not excluded any longer
 * makes it appears as a child of its package and it is removed from the non-java resources.
 */
public void testRenameExcludedSourceModule() throws CoreException {
	setBuildpath(new String[] {"/P/src", "**/A.py"});
	createFolder("/P/src/p");
	IFile file = createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);

	clearDeltas();
	file.move(new Path("/P/src/p/B.py"), false, null);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN}\n" + 
		"		p[*]: {CHILDREN | CONTENT}\n" + 
		"			B.py[+]: {}\n" + 
		"			ResourceDelta(/P/src/p/A.py)[-]"
	);
	
	IScriptFolder pkg = getPackage("/P/src/p");
	assertSortedElementsEqual(
		"Unexpected children",
		"B.py [in p [in src [in P]]]",
		pkg.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		pkg.getForeignResources());
}
/*
 * Ensure that renaming an excluded package so that it is not excluded any longer
 * makes it appears as a child of its package fragment root 
 * and it is removed from the non-java resources.
 */
public void testRenameExcludedPackage() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p/"});
	IProjectFragment root = getProjectFragment("/P/src");
	IScriptFolder pkg = root.createScriptFolder("p", false, null);
	
	clearDeltas();
	pkg.getResource().move(new Path("/P/src/q"), false, null);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN | CONTENT}\n" + 
		"		q[+]: {}\n" + 
		"		ResourceDelta(/P/src/p)[-]"
	);
	
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]\n" + 
		"q [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		root.getForeignResources());
}
/*
 * Ensure that renaming a file that corresponds to an excluded compilation unit so that it is not excluded any longer
 * makes it appears as a child of its package and it is removed from the non-java resources.
 */
public void testRenameResourceExcludedSourceModule() throws CoreException {
	setBuildpath(new String[] {"/P/src", "**/A.py"});
	createFolder("/P/src/p");
	IFile file = createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
	
	clearDeltas();
	file.move(new Path("/P/src/p/B.py"),  false, null);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN}\n" + 
		"		p[*]: {CHILDREN | CONTENT}\n" + 
		"			B.py[+]: {}\n" + 
		"			ResourceDelta(/P/src/p/A.py)[-]"
	);
	
	IScriptFolder pkg = getPackage("/P/src/p");
	assertSortedElementsEqual(
		"Unexpected children",
		"B.py [in p [in src [in P]]]",
		pkg.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		pkg.getForeignResources());
}
///*
// * Ensure search doesn't find matches in an excluded compilation unit.
// */
//public void testSearchWithExcludedSourceModule1() throws CoreException {
//	setClasspath(new String[] {"/P/src", "**/A.py"});
//	createFolder("/P/src/p");
//	createFile(
//		"/P/src/p/A.py",
//		"package p;\n" +
//		"public class A {\n" +
//		"}"
//	);
//	ModelSearchTests.ModelSearchResultCollector resultCollector = new ModelSearchTests.ModelSearchResultCollector();
//	search(
//		"A", 
//		IDLTKSearchConstants.TYPE,
//		IDLTKSearchConstants.DECLARATIONS,
//		SearchEngine.createModelSearchScope(new IScriptProject[] {getScriptProject("P")}), 
//		resultCollector);
//	assertEquals(
//		"Unexpected matches found",
//		"",
//		resultCollector.toString());
//}
///*
// * Ensure search find matches in a compilation unit that was excluded but that is not any longer.
// */
//public void testSearchWithExcludedSourceModule2() throws CoreException {
//	setClasspath(new String[] {"/P/src", "A.py"});
//	createFolder("/P/src/p");
//	createFile(
//		"/P/src/p/A.py",
//		"package p;\n" +
//		"public class A {\n" +
//		"}"
//	);
//	
//	setBuildpath(new String[] {"/P/src", ""});
//	ModelSearchTests.ModelSearchResultCollector resultCollector = new ModelSearchTests.ModelSearchResultCollector();
//	search(
//		"A", 
//		IModelSearchConstants.TYPE,
//		IModelSearchConstants.DECLARATIONS,
//		SearchEngine.createModelSearchScope(new IScriptProject[] {getScriptProject("P")}), 
//		resultCollector);
//	assertEquals(
//		"Unexpected matches found",
//		"src/p/A.py p.A [A]",
//		resultCollector.toString());
//}
/*
 * Ensure that removing a folder that represents an excluded and included package
 * have the correct delta, Script elements and non-Java resources.
 * (regression test for 67789 Script element delta from refresh contains excluded package)
 */
public void testRemoveExcludedAndIncludedPackages() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p1/p2/"});
	IProjectFragment root = getProjectFragment("/P/src");
	createFolder("/P/src/p1/p2");
	
	clearDeltas();
	deleteFolder("/P/src/p1");
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN}\n" + 
		"		p1[-]: {}"
	);
	
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		root.getForeignResources());
}

/*
 * Ensure that renaming a folder that corresponds to an excluded package 
 * so that it is not excluded any longer
 * makes it appears as a child of its package fragment root 
 * and it is removed from the non-java resources.
 */
public void testRenameResourceExcludedPackage() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p/"});
	IFolder folder = createFolder("/P/src/p");
	
	clearDeltas();
	folder.move(new Path("/P/src/q"), false, null);
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN}\n" + 
		"	src[*]: {CHILDREN | CONTENT}\n" + 
		"		q[+]: {}\n" + 
		"		ResourceDelta(/P/src/p)[-]"
	);
	
	IProjectFragment root = getProjectFragment("/P/src");
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]\n" + 
		"q [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		root.getForeignResources());
}
//TODO: Source Searching
///*
// * Ensure that a potential match in the output folder is not indexed.
// * (regression test for bug 32041 Multiple output folders fooling Script Model)
// */
//public void testSearchPotentialMatchInOutput() throws CoreException {
//	try {
//		DLTKCore.run(new IWorkspaceRunnable() {
//			public void run(IProgressMonitor monitor) throws CoreException {
//				IScriptProject scriptProject = createScriptProject("P2", new String[] {}, "bin");
//				scriptProject.setRawClasspath(createClasspath(new String[] {"/P2", "src/", "/P2/src", ""}, false/*no inclusion*/, true/*exclusion*/), null);
//				createFile(
//					"/P2/bin/X.py",
//					"public class X {\n" +
//					"}"
//				);
//			}
//		}, null);
//		
//		ModelSearchTests.ModelSearchResultCollector resultCollector = new ModelSearchTests.ModelSearchResultCollector();
//		IModelSearchScope scope = SearchEngine.createModelSearchScope(new IModelElement[] {getScriptProject("P")});
//		search(
//			"X", 
//			IModelSearchConstants.TYPE,
//			IModelSearchConstants.DECLARATIONS,
//			scope, 
//			resultCollector);
//		assertEquals("", resultCollector.toString());
//	} finally {
//		deleteProject("P2");
//	}
//}
/*
 * Ensure that removing the exclusion on a compilation unit
 * makes it appears as a child of its package and it is removed from the non-java resources.
 */
public void testRemoveExclusionOnSourceModule() throws CoreException {
	setBuildpath(new String[] {"/P/src", "A.py"});
	createFolder("/P/src/p");
	createFile(
		"/P/src/p/A.py",
		"package p;\n" +
		"public class A {\n" +
		"}"
	);
	
	clearDeltas();
	setBuildpath(new String[] {"/P/src", ""});
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN | BUILDPATH CHANGED}\n" + 
		"	src[*]: {ADDED TO BUILDPATH | REMOVED FROM BUILDPATH}\n" + 
		"	ResourceDelta(/P/.classpath)[*]"
	);
	
	IScriptFolder pkg = getPackage("/P/src/p");
	assertSortedElementsEqual(
		"Unexpected children",
		"A.py [in p [in src [in P]]]",
		pkg.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		pkg.getForeignResources());
}
/*
 * Ensure that removing the exclusion on a package
 * makes it appears as a child of its package fragment root 
 * and it is removed from the non-java resources.
 */
public void testRemoveExclusionOnPackage() throws CoreException {
	setBuildpath(new String[] {"/P/src", "p"});
	createFolder("/P/src/p");
	
	clearDeltas();
	setBuildpath(new String[] {"/P/src", ""});
	
	assertDeltas(
		"Unexpected deltas",
		"P[*]: {CHILDREN | BUILDPATH CHANGED}\n" + 
		"	src[*]: {ADDED TO BUILDPATH | REMOVED FROM BUILDPATH}\n" + 
		"	ResourceDelta(/P/.classpath)[*]"
	);
	
	IProjectFragment root = getProjectFragment("/P/src");
	assertSortedElementsEqual(
		"Unexpected children",
		"<default> [in src [in P]]\n" + 
		"p [in src [in P]]",
		root.getChildren());
		
	assertResourceNamesEqual(
		"Unexpected non-java resources",
		"",
		root.getForeignResources());
}
}
