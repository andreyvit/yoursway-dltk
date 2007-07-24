/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.tests.model;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.core.tests.util.ModelTestUtils;
import org.eclipse.dltk.internal.core.ScriptProject;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;
import org.eclipse.dltk.utils.CorePrinter;


public class ModelPythonTests extends AbstractModelTests {
	public ModelPythonTests(String name) {
		super(PythonTestsPlugin.PLUGIN_NAME, name);
	}

	public static Test suite() {
		return new Suite(ModelPythonTests.class);
	}

	public void setUpSuite() throws Exception {
		super.setUpSuite();
	}

	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
	}

	public void testModel00_() throws Exception {
		String prj = "model0";
		IScriptProject project = setUpScriptProject(prj);

		CorePrinter cPrinter = new CorePrinter(System.out);
		System.out.println("Project TREE:##########################################");
		((ScriptProject) project).printNode(cPrinter);
		System.out.println("Project TREE:##########################################");
		cPrinter.flush();
		System.out.println("Project TREE:##########################################");

		ISourceModule module = this.getSourceModule(prj, "src", new Path("X.py"));

		// IModule module = project.findModule(new Path("/src/module0.py"));

		assertNotNull("Module X.py not found", module);
		// assertEquals("X.py exists", true, module.exists() );

		IModelElement[] moduleChildren = module.getChildren();
		assertNotNull(moduleChildren);
		// Check count of module childrens
		assertEquals(1, moduleChildren.length);

		IType type = (IType) moduleChildren[0];

		IModelElement[] typeChilds = type.getChildren();

		// assertEquals(true, false);

		deleteProject(prj);
	}

	public void testModel00() throws Exception {

		String prj = "prj0";
		IScriptProject project = setUpScriptProject(prj);

		ISourceModule module = this.getSourceModule(prj, "src", new Path("module0.py"));

		assertNotNull("Module module0.py not found", module);
		assertEquals("module0.py", module.getElementName());

		IModelElement[] moduleChildren = module.getChildren();
		assertNotNull(moduleChildren);
		// Check count of module childrens
		assertEquals(11, moduleChildren.length);

		// Count field, classes and methods of this module.
		ModelTestUtils.counterAssert(moduleChildren, 1, 9, 1);

		// Check for class A
		IType aClass = ModelTestUtils.getAssertClass(moduleChildren, "A");

		IModelElement[] classAChildren = aClass.getChildren();
		ModelTestUtils.counterAssert(classAChildren, 0, 3, 2);

		// Check for class A sub structure.
		IField uoField = ModelTestUtils.getAssertField(classAChildren, "uo");
		IField aField = ModelTestUtils.getAssertField(classAChildren, "a");
		IField qField = ModelTestUtils.getAssertField(classAChildren, "q");
		IMethod initMethod = ModelTestUtils.getAssertMethod(classAChildren, "__init__", 1);
		IMethod fMethod = ModelTestUtils.getAssertMethod(classAChildren, "f", 2);

		ModelTestUtils.assertParameterNames(initMethod, new String[] { "self" });
		ModelTestUtils.assertParameterNames(fMethod, new String[] { "self", "t" });
		deleteProject(prj);
	}
	//
	// public void testModel01() throws Exception
	// {
	// IScriptProject project = ModelTestUtils.createProject("model01",
	// "scripts/model/prj1",
	// PythonCoreTestsPlugin.PLUGIN_NAME,
	// DynamicPythonProjectCreator.getInstance());
	// IModule module = project.findModule(new Path("/src/module0.py"));
	//
	// assertNotNull("Module module0.py not found", module);
	// assertEquals(module.getName(), "module0.py");
	//
	// Collection<IModelElement> moduleChildren = module.getChildren();
	// assertNotNull( moduleChildren );
	//		
	// assertEquals(2, moduleChildren.size());
	//												
	// ModelTestUtils.counterAssert(moduleChildren, 1, 0, 1);
	//		
	// IType myClass = ModelTestUtils.getAssertClass(moduleChildren, "A" );
	// IMethod myMethod = ModelTestUtils.getAssertMethod(moduleChildren, "f", 0
	// );
	//		
	// assertEquals(module, myClass.getModule());
	// assertEquals(module, myMethod.getModule());
	// }
	//
	// public void testModel02() throws Exception
	// {
	// IScriptProject project = ModelTestUtils.createProject("model02",
	// "scripts/model/prj2",
	// PythonCoreTestsPlugin.PLUGIN_NAME,
	// DynamicPythonProjectCreator.getInstance());
	// IModule module = project.findModule(new Path("/src/module0.py"));
	//
	// assertNotNull("Module module0.py not found", module);
	// assertEquals(module.getName(), "module0.py");
	//
	// Collection<IModelElement> moduleChildren = module.getChildren();
	// assertNotNull( moduleChildren );
	//
	// assertEquals(7, moduleChildren.size());
	// ModelTestUtils.counterAssert(moduleChildren, 0, 4, 3);
	//		
	// IField qwe_Field = ModelTestUtils.getAssertField(moduleChildren, "qwe_"
	// );
	// IField qweField = ModelTestUtils.getAssertField(moduleChildren, "qwe" );
	// IField qwe2Field = ModelTestUtils.getAssertField(moduleChildren, "qwe2"
	// );
	// IField qwe3Field = ModelTestUtils.getAssertField(moduleChildren, "qwe3"
	// );
	//		
	// IMethod iMethod = ModelTestUtils.getAssertMethod(moduleChildren, "i", 4
	// );
	// ModelTestUtils.assertParameterNames(iMethod, new String[] {"val", "val2",
	// "vale", "val4" } );
	//		
	// IMethod bMethod = ModelTestUtils.getAssertMethod(moduleChildren, "b", 1
	// );
	// ModelTestUtils.assertParameterNames( bMethod, new String[] { "t" } );
	//		
	// IMethod lambdaMethod = ModelTestUtils.getAssertMethod(moduleChildren, "f"
	// , 1);
	// ModelTestUtils.assertParameterNames( lambdaMethod, new String[] { "x" }
	// );
	//		
	//		
	// }
	// public void testModel03() throws Exception
	// {
	// IScriptProject project = ModelTestUtils.createProject("model03",
	// "scripts/model/prj3",
	// PythonCoreTestsPlugin.PLUGIN_NAME,
	// DynamicPythonProjectCreator.getInstance());
	// IModule module = project.findModule(new Path("/src/module0.py"));
	//
	// assertNotNull("Module module0.py not found", module);
	// assertEquals(module.getName(), "module0.py");
	//
	// Collection<IModelElement> moduleChildren = module.getChildren();
	// assertNotNull( moduleChildren );
	//
	// assertEquals(8, moduleChildren.size());
	// ModelTestUtils.counterAssert(moduleChildren, 0, 4, 4);
	//		
	// IField qwe_Field = ModelTestUtils.getAssertField(moduleChildren, "qwe_"
	// );
	// IField qweField = ModelTestUtils.getAssertField(moduleChildren, "qwe" );
	// IField qwe2Field = ModelTestUtils.getAssertField(moduleChildren, "qwe2"
	// );
	// IField qwe3Field = ModelTestUtils.getAssertField(moduleChildren, "qwe3"
	// );
	//		
	// IMethod iMethod = ModelTestUtils.getAssertMethod(moduleChildren, "i", 4
	// );
	// ModelTestUtils.assertParameterNames(iMethod, new String[] {"val", "val2",
	// "vale", "val4" } );
	//		
	// IMethod bMethod = ModelTestUtils.getAssertMethod(moduleChildren, "b", 1
	// );
	// ModelTestUtils.assertParameterNames( bMethod, new String[] { "t" } );
	//		
	// IMethod lambdaMethod = ModelTestUtils.getAssertMethod(moduleChildren, "f"
	// , 1);
	// ModelTestUtils.assertParameterNames( lambdaMethod, new String[] { "x" }
	// );
	//		
	// IMethod gMethod = ModelTestUtils.getAssertMethod(moduleChildren, "g", 1
	// );
	// ModelTestUtils.assertParameterNames( gMethod, new String[] { "la" } );
	// }
	// public void testModel04() throws Exception
	// {
	// IScriptProject project = ModelTestUtils.createProject("model04",
	// "scripts/model/prj4",
	// PythonCoreTestsPlugin.PLUGIN_NAME,
	// DynamicPythonProjectCreator.getInstance());
	// IModule module = project.findModule(new Path("/src/module0.py"));
	//
	// assertNotNull("Module module0.py not found", module);
	// assertEquals(module.getName(), "module0.py");
	//
	// Collection<IModelElement> moduleChildren = module.getChildren();
	// assertNotNull( moduleChildren );
	//
	// assertEquals(1, moduleChildren.size());
	// ModelTestUtils.counterAssert(moduleChildren, 0, 0, 1);
	//		
	// IMethod fMethod = ModelTestUtils.getAssertMethod(moduleChildren, "f", 1
	// );
	// ModelTestUtils.assertParameterNames( fMethod, new String[] { "x" } );
	//		
	// // Check for sub function g
	// Collection<IModelElement> fMethodChilds = fMethod.getChildren();
	// assertEquals(1, moduleChildren.size());
	// assertNotNull( fMethodChilds );
	// ModelTestUtils.counterAssert(moduleChildren, 0, 0, 1);
	//		
	// IMethod gMethod = ModelTestUtils.getAssertMethod(fMethodChilds, "g", 1 );
	// ModelTestUtils.assertParameterNames( gMethod, new String[] { "y" } );
	// }
	// public void testModel05() throws Exception
	// {
	// IScriptProject project = ModelTestUtils.createProject("model05",
	// "scripts/model/prj5",
	// PythonCoreTestsPlugin.PLUGIN_NAME,
	// DynamicPythonProjectCreator.getInstance());
	// IModule module = project.findModule(new Path("/src/module0.py"));
	//
	// assertNotNull("Module module0.py not found", module);
	// assertEquals(module.getName(), "module0.py");
	//
	// Collection<IModelElement> moduleChildren = module.getChildren();
	// assertNotNull( moduleChildren );
	//		
	// assertEquals(24, moduleChildren.size());
	// ModelTestUtils.counterAssert(moduleChildren, 4, 0, 20);
	//		
	// // Class SampleClass model testing.
	// IType sampleClass = ModelTestUtils.getAssertClass(moduleChildren,
	// "SampleClass" );
	// {
	// Collection< IModelElement > childs = sampleClass.getChildren();
	//			
	// assertNotNull( childs );
	// assertEquals(10, childs.size());
	// ModelTestUtils.counterAssert(childs, 1, 4, 5);
	// }
	//		
	// }
	// public void testModel06() throws Exception
	// {
	// IScriptProject project = ModelTestUtils.createProject("model06",
	// "scripts/model/prj6",
	// PythonCoreTestsPlugin.PLUGIN_NAME,
	// DynamicPythonProjectCreator.getInstance());
	// IModule module = project.findModule(new Path("/src/module0.py"));
	//
	// assertNotNull("Module module0.py not found", module);
	// assertEquals(module.getName(), "module0.py");
	//
	// Collection<IModelElement> moduleChildren = module.getChildren();
	// assertNotNull( moduleChildren );
	//		
	// assertEquals(24, moduleChildren.size());
	// ModelTestUtils.counterAssert(moduleChildren, 4, 0, 20);
	//		
	// // Class SampleClass model testing.
	// IType sampleClass = ModelTestUtils.getAssertClass(moduleChildren,
	// "SampleClass" );
	// {
	// Collection< IModelElement > childs = sampleClass.getChildren();
	//			
	// assertNotNull( childs );
	// assertEquals(10, childs.size());
	// ModelTestUtils.counterAssert(childs, 1, 4, 5);
	// }
	//		
	// }
}
