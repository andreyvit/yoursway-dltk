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
package org.eclipse.dltk.ui.tests.core;

import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.tests.DLTKProjectHelper;
import org.eclipse.dltk.ui.tests.StringAsserts;

public class ScriptElementLabelsTest extends AbstractModelTests {
	
	public static final String PROJECT_NAME= "TestSetupProject";
	
	//private static final Class THIS= ScriptElementLabelsTest.class;
	
	private IDLTKProject fJProject1;

	public ScriptElementLabelsTest(String name) {	
		super (PROJECT_NAME, name);
	}
	
	/*public static Test setUpTest(Test test) {
		return new ProjectTestSetup(test);
	}*/

	protected void setUp() throws Exception {
		fJProject1 = DLTKProjectHelper.createDLTKProject(PROJECT_NAME);
	}


	protected void tearDown() throws Exception {	
	}
	
	public static void assertEqualString(String actual, String expected) {	
		StringAsserts.assertEqualString(actual, expected);
	}
	
	
		
	public void testTypeLabelOuter() throws Exception {
	
		
		IProjectFragment sourceFolder= DLTKProjectHelper.addSourceContainer(fJProject1, "src");
		//
		IScriptFolder pack1= sourceFolder.getScriptFolder(""); //sourceFolder.createScriptFolder("org.test", false, null);
		
		StringBuffer buf= new StringBuffer();
		buf.append("namespace eval Outer {\n");
		buf.append("}\n");
		
		String content= buf.toString();
		ISourceModule cu= pack1.createSourceModule("Outer.tcl", content, false, null);

		IModelElement elem= cu.getElementAt(content.indexOf("Outer"));
		String lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED);
		assertEqualString(lab, "Outer");

		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_CONTAINER_QUALIFIED);
		assertEqualString(lab, "Outer");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_POST_QUALIFIED);
		assertEqualString(lab, "Outer");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.APPEND_ROOT_PATH);
		assertEqualString(lab, "Outer - TestSetupProject/src/Outer.tcl");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.PREPEND_ROOT_PATH);
		assertEqualString(lab, "TestSetupProject/src/Outer.tcl - Outer");
	}
	
	public void testTypeLabelInner() throws Exception {
		
		IProjectFragment sourceFolder= DLTKProjectHelper.addSourceContainer(fJProject1, "src");
		
		IScriptFolder pack1= sourceFolder.getScriptFolder("");
		
		StringBuffer buf= new StringBuffer();
				
		buf.append("package require Tk\n");
		buf.append("namespace eval Outer {\n");
		buf.append("    proc foo{vec} {\n");
		buf.append("    }\n");
		buf.append("    namespace eval Inner {\n");
		buf.append("        proc inner {vec} {\n");
		buf.append("        }\n");	
		buf.append("    }\n");	
		buf.append("}\n");	
		String content= buf.toString();
		ISourceModule cu= pack1.createSourceModule("Outer2.tcl", content, false, null);

		IModelElement elem= cu.getElementAt(content.indexOf("Inner"));
		
		String lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED);
		assertEqualString(lab, "Outer.Inner");

		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_CONTAINER_QUALIFIED);
		assertEqualString(lab, "Outer.Inner");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_POST_QUALIFIED);
		assertEqualString(lab, "Inner - Outer");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.APPEND_ROOT_PATH);
		assertEqualString(lab, "Outer.Inner - TestSetupProject/src/Outer2.tcl");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.PREPEND_ROOT_PATH);
		assertEqualString(lab, "TestSetupProject/src/Outer2.tcl - Outer.Inner");
	}
	
	public void testTypeLabelLocal() throws Exception {
		
		IProjectFragment sourceFolder= DLTKProjectHelper.addSourceContainer(fJProject1, "src");
		
		IScriptFolder pack1= sourceFolder.createScriptFolder("", false, null);
		
		StringBuffer buf= new StringBuffer();

		buf.append("package require Vector\n");
		buf.append("namespace eval Outer {\n");
		buf.append("    proc foo {vec} {\n");
		buf.append("        namespace eval Local {\n");
		buf.append("        }\n");
		buf.append("    }\n");	
		buf.append("}\n");	
		String content= buf.toString();
		ISourceModule cu= pack1.createSourceModule("Outer3.tcl", content, false, null);

		IModelElement elem= cu.getElementAt(content.indexOf("Local"));
		
		String lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED);
		assertEqualString(lab, "Outer.foo(...).Local");

		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_CONTAINER_QUALIFIED);
		assertEqualString(lab, "Outer.foo(...).Local");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_POST_QUALIFIED);
		assertEqualString(lab, "Local - Outer.foo(...)");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.APPEND_ROOT_PATH);
		assertEqualString(lab, "Outer.foo(...).Local - TestSetupProject/src/Outer3.tcl");
		
		lab= ScriptElementLabels.getDefault().getTextLabel(elem, ScriptElementLabels.T_FULLY_QUALIFIED | ScriptElementLabels.PREPEND_ROOT_PATH);
		assertEqualString(lab, "TestSetupProject/src/Outer3.tcl - Outer.foo(...).Local");
	}
		
	
		
}
