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
import org.eclipse.dltk.python.tests.PythonTestsPlugin;

public class GeneratedModelTests4 extends AbstractModelTests
{
	public GeneratedModelTests4(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests4.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		setUpScriptProject( "pytests" );
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests" );
	}
	public void testModelGen200( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_slice.py"));

		assertNotNull("Module test_slice.py not found", module);
		assertEquals("test_slice.py", module.getElementName());
		
		//Class test
		{
		IType classSliceTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classSliceTest0 = ModelTestUtils.getAssertClass( moduleChilds, "SliceTest" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor1;
				IModelElement[] classSliceTest0Childs = classSliceTest0.getChildren();
				methodtest_constructor1 = ModelTestUtils.getAssertMethod( classSliceTest0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor1, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr2;
				IModelElement[] classSliceTest0Childs = classSliceTest0.getChildren();
				methodtest_repr2 = ModelTestUtils.getAssertMethod( classSliceTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr2, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash3;
				IModelElement[] classSliceTest0Childs = classSliceTest0.getChildren();
				methodtest_hash3 = ModelTestUtils.getAssertMethod( classSliceTest0Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash3, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp4;
				IModelElement[] classSliceTest0Childs = classSliceTest0.getChildren();
				methodtest_cmp4 = ModelTestUtils.getAssertMethod( classSliceTest0Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp4, new String[] {"self"} );
				//Class test
				{
				IType classExc5;
					IModelElement[] methodtest_cmp4Childs = methodtest_cmp4.getChildren();
					classExc5 = ModelTestUtils.getAssertClass( methodtest_cmp4Childs, "Exc" );
				}
				//Class test
				{
				IType classBadCmp6;
					IModelElement[] methodtest_cmp4Childs = methodtest_cmp4.getChildren();
					classBadCmp6 = ModelTestUtils.getAssertClass( methodtest_cmp4Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__7;
						IModelElement[] classBadCmp6Childs = classBadCmp6.getChildren();
						method__eq__7 = ModelTestUtils.getAssertMethod( classBadCmp6Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__7, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_members
			{
			IMethod methodtest_members8;
				IModelElement[] classSliceTest0Childs = classSliceTest0.getChildren();
				methodtest_members8 = ModelTestUtils.getAssertMethod( classSliceTest0Childs, "test_members", 1 );
				ModelTestUtils.assertParameterNames( methodtest_members8, new String[] {"self"} );
				//Class test
				{
				IType classAnyClass9;
					IModelElement[] methodtest_members8Childs = methodtest_members8.getChildren();
					classAnyClass9 = ModelTestUtils.getAssertClass( methodtest_members8Childs, "AnyClass" );
				}
			}
			//Function test:test_indices
			{
			IMethod methodtest_indices10;
				IModelElement[] classSliceTest0Childs = classSliceTest0.getChildren();
				methodtest_indices10 = ModelTestUtils.getAssertMethod( classSliceTest0Childs, "test_indices", 1 );
				ModelTestUtils.assertParameterNames( methodtest_indices10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen201( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pkg.py"));

		assertNotNull("Module test_pkg.py not found", module);
		assertEquals("test_pkg.py", module.getElementName());
		
		//Function test:mkhier
		{
		IMethod methodmkhier0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmkhier0 = ModelTestUtils.getAssertMethod( moduleChilds, "mkhier", 2 );
			ModelTestUtils.assertParameterNames( methodmkhier0, new String[] {"root", "descr"} );
		}
		//Function test:mkdir
		{
		IMethod methodmkdir1;
			IModelElement[] moduleChilds = module.getChildren();
			methodmkdir1 = ModelTestUtils.getAssertMethod( moduleChilds, "mkdir", 1 );
			ModelTestUtils.assertParameterNames( methodmkdir1, new String[] {"x"} );
		}
		//Function test:cleanout
		{
		IMethod methodcleanout2;
			IModelElement[] moduleChilds = module.getChildren();
			methodcleanout2 = ModelTestUtils.getAssertMethod( moduleChilds, "cleanout", 1 );
			ModelTestUtils.assertParameterNames( methodcleanout2, new String[] {"root"} );
		}
		//Function test:rmdir
		{
		IMethod methodrmdir3;
			IModelElement[] moduleChilds = module.getChildren();
			methodrmdir3 = ModelTestUtils.getAssertMethod( moduleChilds, "rmdir", 1 );
			ModelTestUtils.assertParameterNames( methodrmdir3, new String[] {"x"} );
		}
		//Function test:fixdir
		{
		IMethod methodfixdir4;
			IModelElement[] moduleChilds = module.getChildren();
			methodfixdir4 = ModelTestUtils.getAssertMethod( moduleChilds, "fixdir", 1 );
			ModelTestUtils.assertParameterNames( methodfixdir4, new String[] {"lst"} );
		}
		//Function test:runtest
		{
		IMethod methodruntest5;
			IModelElement[] moduleChilds = module.getChildren();
			methodruntest5 = ModelTestUtils.getAssertMethod( moduleChilds, "runtest", 2 );
			ModelTestUtils.assertParameterNames( methodruntest5, new String[] {"hier", "code"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nontests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "args");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "args");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "verbose");
		}

	}
	public void testModelGen202( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_fileinput.py"));

		assertNotNull("Module test_fileinput.py not found", module);
		assertEquals("test_fileinput.py", module.getElementName());
		
		//Function test:writeTmp
		{
		IMethod methodwriteTmp0;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteTmp0 = ModelTestUtils.getAssertMethod( moduleChilds, "writeTmp", 2 );
			ModelTestUtils.assertParameterNames( methodwriteTmp0, new String[] {"i", "lines"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pat");
		}
		//Function test:remove_tempfiles
		{
		IMethod methodremove_tempfiles1;
			IModelElement[] moduleChilds = module.getChildren();
			methodremove_tempfiles1 = ModelTestUtils.getAssertMethod( moduleChilds, "remove_tempfiles", 1 );
			ModelTestUtils.assertParameterNames( methodremove_tempfiles1, new String[] {"names"} );
		}
		//Function test:runTests
		{
		IMethod methodrunTests2;
			IModelElement[] moduleChilds = module.getChildren();
			methodrunTests2 = ModelTestUtils.getAssertMethod( moduleChilds, "runTests", 6 );
			ModelTestUtils.assertParameterNames( methodrunTests2, new String[] {"t1", "t2", "t3", "t4", "bs", "round"} );
		}
		//Function test:writeFiles
		{
		IMethod methodwriteFiles3;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteFiles3 = ModelTestUtils.getAssertMethod( moduleChilds, "writeFiles", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t4");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fi");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "line");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "line");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fi");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "lines");
		}

	}
	public void testModelGen203( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pow.py"));

		assertNotNull("Module test_pow.py not found", module);
		assertEquals("test_pow.py", module.getElementName());
		
		//Class test
		{
		IType classPowTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classPowTest0 = ModelTestUtils.getAssertClass( moduleChilds, "PowTest" );
			//Function test:powtest
			{
			IMethod methodpowtest1;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodpowtest1 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "powtest", 2 );
				ModelTestUtils.assertParameterNames( methodpowtest1, new String[] {"self", "type"} );
			}
			//Function test:test_powint
			{
			IMethod methodtest_powint2;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodtest_powint2 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "test_powint", 1 );
				ModelTestUtils.assertParameterNames( methodtest_powint2, new String[] {"self"} );
			}
			//Function test:test_powlong
			{
			IMethod methodtest_powlong3;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodtest_powlong3 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "test_powlong", 1 );
				ModelTestUtils.assertParameterNames( methodtest_powlong3, new String[] {"self"} );
			}
			//Function test:test_powfloat
			{
			IMethod methodtest_powfloat4;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodtest_powfloat4 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "test_powfloat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_powfloat4, new String[] {"self"} );
			}
			//Function test:test_other
			{
			IMethod methodtest_other5;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodtest_other5 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "test_other", 1 );
				ModelTestUtils.assertParameterNames( methodtest_other5, new String[] {"self"} );
			}
			//Function test:test_bug643260
			{
			IMethod methodtest_bug6432606;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodtest_bug6432606 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "test_bug643260", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug6432606, new String[] {"self"} );
				//Class test
				{
				IType classTestRpow7;
					IModelElement[] methodtest_bug6432606Childs = methodtest_bug6432606.getChildren();
					classTestRpow7 = ModelTestUtils.getAssertClass( methodtest_bug6432606Childs, "TestRpow" );
					//Function test:__rpow__
					{
					IMethod method__rpow__8;
						IModelElement[] classTestRpow7Childs = classTestRpow7.getChildren();
						method__rpow__8 = ModelTestUtils.getAssertMethod( classTestRpow7Childs, "__rpow__", 2 );
						ModelTestUtils.assertParameterNames( method__rpow__8, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_bug705231
			{
			IMethod methodtest_bug7052319;
				IModelElement[] classPowTest0Childs = classPowTest0.getChildren();
				methodtest_bug7052319 = ModelTestUtils.getAssertMethod( classPowTest0Childs, "test_bug705231", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug7052319, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen204( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_univnewlines.py"));

		assertNotNull("Module test_univnewlines.py not found", module);
		assertEquals("test_univnewlines.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FATX");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA_TEMPLATE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA_LF");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA_CR");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA_CRLF");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA_MIXED");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA_SPLIT");
		}
		//Class test
		{
		IType classTestGenericUnivNewlines0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestGenericUnivNewlines0 = ModelTestUtils.getAssertClass( moduleChilds, "TestGenericUnivNewlines" );
			{
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericUnivNewlines0Childs, "READMODE");
			}
			{
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericUnivNewlines0Childs, "WRITEMODE");
			}
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read3;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodtest_read3 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read3, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines4;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodtest_readlines4 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines4, new String[] {"self"} );
			}
			//Function test:test_readline
			{
			IMethod methodtest_readline5;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodtest_readline5 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "test_readline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readline5, new String[] {"self"} );
			}
			//Function test:test_seek
			{
			IMethod methodtest_seek6;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodtest_seek6 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "test_seek", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seek6, new String[] {"self"} );
			}
			//Function test:test_execfile
			{
			IMethod methodtest_execfile7;
				IModelElement[] classTestGenericUnivNewlines0Childs = classTestGenericUnivNewlines0.getChildren();
				methodtest_execfile7 = ModelTestUtils.getAssertMethod( classTestGenericUnivNewlines0Childs, "test_execfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_execfile7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestNativeNewlines8;
			IModelElement[] moduleChilds = module.getChildren();
			classTestNativeNewlines8 = ModelTestUtils.getAssertClass( moduleChilds, "TestNativeNewlines" );
			{
				IModelElement[] classTestNativeNewlines8Childs = classTestNativeNewlines8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestNativeNewlines8Childs, "NEWLINE");
			}
			{
				IModelElement[] classTestNativeNewlines8Childs = classTestNativeNewlines8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestNativeNewlines8Childs, "DATA");
			}
			{
				IModelElement[] classTestNativeNewlines8Childs = classTestNativeNewlines8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestNativeNewlines8Childs, "READMODE");
			}
			{
				IModelElement[] classTestNativeNewlines8Childs = classTestNativeNewlines8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestNativeNewlines8Childs, "WRITEMODE");
			}
		}
		//Class test
		{
		IType classTestCRNewlines9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCRNewlines9 = ModelTestUtils.getAssertClass( moduleChilds, "TestCRNewlines" );
			{
				IModelElement[] classTestCRNewlines9Childs = classTestCRNewlines9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCRNewlines9Childs, "NEWLINE");
			}
			{
				IModelElement[] classTestCRNewlines9Childs = classTestCRNewlines9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCRNewlines9Childs, "DATA");
			}
		}
		//Class test
		{
		IType classTestLFNewlines10;
			IModelElement[] moduleChilds = module.getChildren();
			classTestLFNewlines10 = ModelTestUtils.getAssertClass( moduleChilds, "TestLFNewlines" );
			{
				IModelElement[] classTestLFNewlines10Childs = classTestLFNewlines10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestLFNewlines10Childs, "NEWLINE");
			}
			{
				IModelElement[] classTestLFNewlines10Childs = classTestLFNewlines10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestLFNewlines10Childs, "DATA");
			}
		}
		//Class test
		{
		IType classTestCRLFNewlines11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCRLFNewlines11 = ModelTestUtils.getAssertClass( moduleChilds, "TestCRLFNewlines" );
			{
				IModelElement[] classTestCRLFNewlines11Childs = classTestCRLFNewlines11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCRLFNewlines11Childs, "NEWLINE");
			}
			{
				IModelElement[] classTestCRLFNewlines11Childs = classTestCRLFNewlines11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCRLFNewlines11Childs, "DATA");
			}
		}
		//Class test
		{
		IType classTestMixedNewlines12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMixedNewlines12 = ModelTestUtils.getAssertClass( moduleChilds, "TestMixedNewlines" );
			{
				IModelElement[] classTestMixedNewlines12Childs = classTestMixedNewlines12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMixedNewlines12Childs, "NEWLINE");
			}
			{
				IModelElement[] classTestMixedNewlines12Childs = classTestMixedNewlines12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMixedNewlines12Childs, "DATA");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main13 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen205( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_MimeWriter.py"));

		assertNotNull("Module test_MimeWriter.py not found", module);
		assertEquals("test_MimeWriter.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SELLER");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "BUYER");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "STATE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SIMPLE_METADATA");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "COMPLEX_METADATA");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "EXTERNAL_METADATA");
		}
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen206( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pty.py"));

		assertNotNull("Module test_pty.py not found", module);
		assertEquals("test_pty.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEST_STRING_1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEST_STRING_2");
		}
		//Function test:debug
		{
		IMethod methoddebug0;
			IModelElement[] moduleChilds = module.getChildren();
			methoddebug0 = ModelTestUtils.getAssertMethod( moduleChilds, "debug", 1 );
			ModelTestUtils.assertParameterNames( methoddebug0, new String[] {"msg"} );
		}
		//Function test:debug
		{
		IMethod methoddebug1;
			IModelElement[] moduleChilds = module.getChildren();
			methoddebug1 = ModelTestUtils.getAssertMethod( moduleChilds, "debug", 1 );
			ModelTestUtils.assertParameterNames( methoddebug1, new String[] {"msg"} );
		}
		//Function test:test_basic_pty
		{
		IMethod methodtest_basic_pty2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_basic_pty2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_basic_pty", 0 );
		}
		//Function test:handle_sig
		{
		IMethod methodhandle_sig3;
			IModelElement[] moduleChilds = module.getChildren();
			methodhandle_sig3 = ModelTestUtils.getAssertMethod( moduleChilds, "handle_sig", 2 );
			ModelTestUtils.assertParameterNames( methodhandle_sig3, new String[] {"sig", "frame"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "old_alarm");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "res");
		}

	}
	public void testModelGen207( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pwd.py"));

		assertNotNull("Module test_pwd.py not found", module);
		assertEquals("test_pwd.py", module.getElementName());
		
		//Class test
		{
		IType classPwdTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classPwdTest0 = ModelTestUtils.getAssertClass( moduleChilds, "PwdTest" );
			//Function test:test_values
			{
			IMethod methodtest_values1;
				IModelElement[] classPwdTest0Childs = classPwdTest0.getChildren();
				methodtest_values1 = ModelTestUtils.getAssertMethod( classPwdTest0Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values1, new String[] {"self"} );
			}
			//Function test:test_errors
			{
			IMethod methodtest_errors2;
				IModelElement[] classPwdTest0Childs = classPwdTest0.getChildren();
				methodtest_errors2 = ModelTestUtils.getAssertMethod( classPwdTest0Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen208( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_tuple.py"));

		assertNotNull("Module test_tuple.py not found", module);
		assertEquals("test_tuple.py", module.getElementName());
		
		//Class test
		{
		IType classTupleTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classTupleTest0 = ModelTestUtils.getAssertClass( moduleChilds, "TupleTest" );
			{
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTupleTest0Childs, "type2test");
			}
			//Function test:test_constructors
			{
			IMethod methodtest_constructors1;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_constructors1 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_constructors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructors1, new String[] {"self"} );
			}
			//Function test:test_truth
			{
			IMethod methodtest_truth2;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_truth2 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_truth", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truth2, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len3;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_len3 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len3, new String[] {"self"} );
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd4;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_iadd4 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd4, new String[] {"self"} );
			}
			//Function test:test_imul
			{
			IMethod methodtest_imul5;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_imul5 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_imul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imul5, new String[] {"self"} );
			}
			//Function test:test_tupleresizebug
			{
			IMethod methodtest_tupleresizebug6;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_tupleresizebug6 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_tupleresizebug", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tupleresizebug6, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf7;
					IModelElement[] methodtest_tupleresizebug6Childs = methodtest_tupleresizebug6.getChildren();
					methodf7 = ModelTestUtils.getAssertMethod( methodtest_tupleresizebug6Childs, "f", 0 );
				}
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash8;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_hash8 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash8, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr9;
				IModelElement[] classTupleTest0Childs = classTupleTest0.getChildren();
				methodtest_repr9 = ModelTestUtils.getAssertMethod( classTupleTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr9, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen209( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_hmac.py"));

		assertNotNull("Module test_hmac.py not found", module);
		assertEquals("test_hmac.py", module.getElementName());
		
		//Class test
		{
		IType classTestVectorsTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVectorsTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "TestVectorsTestCase" );
			//Function test:test_md5_vectors
			{
			IMethod methodtest_md5_vectors1;
				IModelElement[] classTestVectorsTestCase0Childs = classTestVectorsTestCase0.getChildren();
				methodtest_md5_vectors1 = ModelTestUtils.getAssertMethod( classTestVectorsTestCase0Childs, "test_md5_vectors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_md5_vectors1, new String[] {"self"} );
				//Function test:md5test
				{
				IMethod methodmd5test2;
					IModelElement[] methodtest_md5_vectors1Childs = methodtest_md5_vectors1.getChildren();
					methodmd5test2 = ModelTestUtils.getAssertMethod( methodtest_md5_vectors1Childs, "md5test", 3 );
					ModelTestUtils.assertParameterNames( methodmd5test2, new String[] {"key", "data", "digest"} );
				}
			}
			//Function test:test_sha_vectors
			{
			IMethod methodtest_sha_vectors3;
				IModelElement[] classTestVectorsTestCase0Childs = classTestVectorsTestCase0.getChildren();
				methodtest_sha_vectors3 = ModelTestUtils.getAssertMethod( classTestVectorsTestCase0Childs, "test_sha_vectors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sha_vectors3, new String[] {"self"} );
				//Function test:shatest
				{
				IMethod methodshatest4;
					IModelElement[] methodtest_sha_vectors3Childs = methodtest_sha_vectors3.getChildren();
					methodshatest4 = ModelTestUtils.getAssertMethod( methodtest_sha_vectors3Childs, "shatest", 3 );
					ModelTestUtils.assertParameterNames( methodshatest4, new String[] {"key", "data", "digest"} );
				}
			}
		}
		//Class test
		{
		IType classConstructorTestCase5;
			IModelElement[] moduleChilds = module.getChildren();
			classConstructorTestCase5 = ModelTestUtils.getAssertClass( moduleChilds, "ConstructorTestCase" );
			//Function test:test_normal
			{
			IMethod methodtest_normal6;
				IModelElement[] classConstructorTestCase5Childs = classConstructorTestCase5.getChildren();
				methodtest_normal6 = ModelTestUtils.getAssertMethod( classConstructorTestCase5Childs, "test_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_normal6, new String[] {"self"} );
			}
			//Function test:test_withtext
			{
			IMethod methodtest_withtext7;
				IModelElement[] classConstructorTestCase5Childs = classConstructorTestCase5.getChildren();
				methodtest_withtext7 = ModelTestUtils.getAssertMethod( classConstructorTestCase5Childs, "test_withtext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_withtext7, new String[] {"self"} );
			}
			//Function test:test_withmodule
			{
			IMethod methodtest_withmodule8;
				IModelElement[] classConstructorTestCase5Childs = classConstructorTestCase5.getChildren();
				methodtest_withmodule8 = ModelTestUtils.getAssertMethod( classConstructorTestCase5Childs, "test_withmodule", 1 );
				ModelTestUtils.assertParameterNames( methodtest_withmodule8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSanityTestCase9;
			IModelElement[] moduleChilds = module.getChildren();
			classSanityTestCase9 = ModelTestUtils.getAssertClass( moduleChilds, "SanityTestCase" );
			//Function test:test_default_is_md5
			{
			IMethod methodtest_default_is_md510;
				IModelElement[] classSanityTestCase9Childs = classSanityTestCase9.getChildren();
				methodtest_default_is_md510 = ModelTestUtils.getAssertMethod( classSanityTestCase9Childs, "test_default_is_md5", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_is_md510, new String[] {"self"} );
			}
			//Function test:test_exercise_all_methods
			{
			IMethod methodtest_exercise_all_methods11;
				IModelElement[] classSanityTestCase9Childs = classSanityTestCase9.getChildren();
				methodtest_exercise_all_methods11 = ModelTestUtils.getAssertMethod( classSanityTestCase9Childs, "test_exercise_all_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exercise_all_methods11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCopyTestCase12;
			IModelElement[] moduleChilds = module.getChildren();
			classCopyTestCase12 = ModelTestUtils.getAssertClass( moduleChilds, "CopyTestCase" );
			//Function test:test_attributes
			{
			IMethod methodtest_attributes13;
				IModelElement[] classCopyTestCase12Childs = classCopyTestCase12.getChildren();
				methodtest_attributes13 = ModelTestUtils.getAssertMethod( classCopyTestCase12Childs, "test_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attributes13, new String[] {"self"} );
			}
			//Function test:test_realcopy
			{
			IMethod methodtest_realcopy14;
				IModelElement[] classCopyTestCase12Childs = classCopyTestCase12.getChildren();
				methodtest_realcopy14 = ModelTestUtils.getAssertMethod( classCopyTestCase12Childs, "test_realcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_realcopy14, new String[] {"self"} );
			}
			//Function test:test_equality
			{
			IMethod methodtest_equality15;
				IModelElement[] classCopyTestCase12Childs = classCopyTestCase12.getChildren();
				methodtest_equality15 = ModelTestUtils.getAssertMethod( classCopyTestCase12Childs, "test_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equality15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen210( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dummy_threading.py"));

		assertNotNull("Module test_dummy_threading.py not found", module);
		assertEquals("test_dummy_threading.py", module.getElementName());
		
		//Class test
		{
		IType classTestThread0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestThread0 = ModelTestUtils.getAssertClass( moduleChilds, "TestThread" );
			//Function test:run
			{
			IMethod methodrun1;
				IModelElement[] classTestThread0Childs = classTestThread0.getChildren();
				methodrun1 = ModelTestUtils.getAssertMethod( classTestThread0Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun1, new String[] {"self"} );
			}
		}
		//Function test:starttasks
		{
		IMethod methodstarttasks2;
			IModelElement[] moduleChilds = module.getChildren();
			methodstarttasks2 = ModelTestUtils.getAssertMethod( moduleChilds, "starttasks", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen211( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_decorators.py"));

		assertNotNull("Module test_decorators.py not found", module);
		assertEquals("test_decorators.py", module.getElementName());
		
		//Function test:funcattrs
		{
		IMethod methodfuncattrs0;
			IModelElement[] moduleChilds = module.getChildren();
			methodfuncattrs0 = ModelTestUtils.getAssertMethod( moduleChilds, "funcattrs", 1 );
			ModelTestUtils.assertParameterNames( methodfuncattrs0, new String[] {"kwds"} );
			//Function test:decorate
			{
			IMethod methoddecorate1;
				IModelElement[] methodfuncattrs0Childs = methodfuncattrs0.getChildren();
				methoddecorate1 = ModelTestUtils.getAssertMethod( methodfuncattrs0Childs, "decorate", 1 );
				ModelTestUtils.assertParameterNames( methoddecorate1, new String[] {"func"} );
			}
		}
		//Class test
		{
		IType classMiscDecorators2;
			IModelElement[] moduleChilds = module.getChildren();
			classMiscDecorators2 = ModelTestUtils.getAssertClass( moduleChilds, "MiscDecorators" );
			//Function test:author
			{
			IMethod methodauthor3;
				IModelElement[] classMiscDecorators2Childs = classMiscDecorators2.getChildren();
				methodauthor3 = ModelTestUtils.getAssertMethod( classMiscDecorators2Childs, "author", 1 );
				ModelTestUtils.assertParameterNames( methodauthor3, new String[] {"name"} );
				//Function test:decorate
				{
				IMethod methoddecorate4;
					IModelElement[] methodauthor3Childs = methodauthor3.getChildren();
					methoddecorate4 = ModelTestUtils.getAssertMethod( methodauthor3Childs, "decorate", 1 );
					ModelTestUtils.assertParameterNames( methoddecorate4, new String[] {"func"} );
				}
			}
		}
		//Class test
		{
		IType classDbcheckError5;
			IModelElement[] moduleChilds = module.getChildren();
			classDbcheckError5 = ModelTestUtils.getAssertClass( moduleChilds, "DbcheckError" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classDbcheckError5Childs = classDbcheckError5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classDbcheckError5Childs, "__init__", 5 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "exprstr", "func", "args", "kwds"} );
			}
		}
		//Function test:dbcheck
		{
		IMethod methoddbcheck7;
			IModelElement[] moduleChilds = module.getChildren();
			methoddbcheck7 = ModelTestUtils.getAssertMethod( moduleChilds, "dbcheck", 3 );
			ModelTestUtils.assertParameterNames( methoddbcheck7, new String[] {"exprstr", "globals", "locals"} );
			//Function test:decorate
			{
			IMethod methoddecorate8;
				IModelElement[] methoddbcheck7Childs = methoddbcheck7.getChildren();
				methoddecorate8 = ModelTestUtils.getAssertMethod( methoddbcheck7Childs, "decorate", 1 );
				ModelTestUtils.assertParameterNames( methoddecorate8, new String[] {"func"} );
				//Function test:check
				{
				IMethod methodcheck9;
					IModelElement[] methoddecorate8Childs = methoddecorate8.getChildren();
					methodcheck9 = ModelTestUtils.getAssertMethod( methoddecorate8Childs, "check", 2 );
					ModelTestUtils.assertParameterNames( methodcheck9, new String[] {"args", "kwds"} );
				}
			}
		}
		//Function test:countcalls
		{
		IMethod methodcountcalls10;
			IModelElement[] moduleChilds = module.getChildren();
			methodcountcalls10 = ModelTestUtils.getAssertMethod( moduleChilds, "countcalls", 1 );
			ModelTestUtils.assertParameterNames( methodcountcalls10, new String[] {"counts"} );
			//Function test:decorate
			{
			IMethod methoddecorate11;
				IModelElement[] methodcountcalls10Childs = methodcountcalls10.getChildren();
				methoddecorate11 = ModelTestUtils.getAssertMethod( methodcountcalls10Childs, "decorate", 1 );
				ModelTestUtils.assertParameterNames( methoddecorate11, new String[] {"func"} );
				//Function test:call
				{
				IMethod methodcall12;
					IModelElement[] methoddecorate11Childs = methoddecorate11.getChildren();
					methodcall12 = ModelTestUtils.getAssertMethod( methoddecorate11Childs, "call", 2 );
					ModelTestUtils.assertParameterNames( methodcall12, new String[] {"args", "kwds"} );
				}
			}
		}
		//Function test:memoize
		{
		IMethod methodmemoize13;
			IModelElement[] moduleChilds = module.getChildren();
			methodmemoize13 = ModelTestUtils.getAssertMethod( moduleChilds, "memoize", 1 );
			ModelTestUtils.assertParameterNames( methodmemoize13, new String[] {"func"} );
			//Function test:call
			{
			IMethod methodcall14;
				IModelElement[] methodmemoize13Childs = methodmemoize13.getChildren();
				methodcall14 = ModelTestUtils.getAssertMethod( methodmemoize13Childs, "call", 1 );
				ModelTestUtils.assertParameterNames( methodcall14, new String[] {"args"} );
			}
		}
		//Class test
		{
		IType classTestDecorators15;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDecorators15 = ModelTestUtils.getAssertClass( moduleChilds, "TestDecorators" );
			//Function test:test_single
			{
			IMethod methodtest_single16;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_single16 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_single", 1 );
				ModelTestUtils.assertParameterNames( methodtest_single16, new String[] {"self"} );
				//Class test
				{
				IType classC17;
					IModelElement[] methodtest_single16Childs = methodtest_single16.getChildren();
					classC17 = ModelTestUtils.getAssertClass( methodtest_single16Childs, "C" );
					//Function test:foo
					{
					IMethod methodfoo18;
						IModelElement[] classC17Childs = classC17.getChildren();
						methodfoo18 = ModelTestUtils.getAssertMethod( classC17Childs, "foo", 0 );
					}
				}
			}
			//Function test:test_staticmethod_function
			{
			IMethod methodtest_staticmethod_function19;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_staticmethod_function19 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_staticmethod_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_staticmethod_function19, new String[] {"self"} );
				//Function test:notamethod
				{
				IMethod methodnotamethod20;
					IModelElement[] methodtest_staticmethod_function19Childs = methodtest_staticmethod_function19.getChildren();
					methodnotamethod20 = ModelTestUtils.getAssertMethod( methodtest_staticmethod_function19Childs, "notamethod", 1 );
					ModelTestUtils.assertParameterNames( methodnotamethod20, new String[] {"x"} );
				}
			}
			//Function test:test_dotted
			{
			IMethod methodtest_dotted21;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_dotted21 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_dotted", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dotted21, new String[] {"self"} );
				//Function test:foo
				{
				IMethod methodfoo22;
					IModelElement[] methodtest_dotted21Childs = methodtest_dotted21.getChildren();
					methodfoo22 = ModelTestUtils.getAssertMethod( methodtest_dotted21Childs, "foo", 0 );
				}
			}
			//Function test:test_argforms
			{
			IMethod methodtest_argforms23;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_argforms23 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_argforms", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argforms23, new String[] {"self"} );
				//Function test:noteargs
				{
				IMethod methodnoteargs24;
					IModelElement[] methodtest_argforms23Childs = methodtest_argforms23.getChildren();
					methodnoteargs24 = ModelTestUtils.getAssertMethod( methodtest_argforms23Childs, "noteargs", 2 );
					ModelTestUtils.assertParameterNames( methodnoteargs24, new String[] {"args", "kwds"} );
					//Function test:decorate
					{
					IMethod methoddecorate25;
						IModelElement[] methodnoteargs24Childs = methodnoteargs24.getChildren();
						methoddecorate25 = ModelTestUtils.getAssertMethod( methodnoteargs24Childs, "decorate", 1 );
						ModelTestUtils.assertParameterNames( methoddecorate25, new String[] {"func"} );
					}
				}
				//Function test:f1
				{
				IMethod methodf126;
					IModelElement[] methodtest_argforms23Childs = methodtest_argforms23.getChildren();
					methodf126 = ModelTestUtils.getAssertMethod( methodtest_argforms23Childs, "f1", 0 );
				}
				//Function test:f2
				{
				IMethod methodf227;
					IModelElement[] methodtest_argforms23Childs = methodtest_argforms23.getChildren();
					methodf227 = ModelTestUtils.getAssertMethod( methodtest_argforms23Childs, "f2", 0 );
				}
				//Function test:f3
				{
				IMethod methodf328;
					IModelElement[] methodtest_argforms23Childs = methodtest_argforms23.getChildren();
					methodf328 = ModelTestUtils.getAssertMethod( methodtest_argforms23Childs, "f3", 0 );
				}
			}
			//Function test:test_dbcheck
			{
			IMethod methodtest_dbcheck29;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_dbcheck29 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_dbcheck", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dbcheck29, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf30;
					IModelElement[] methodtest_dbcheck29Childs = methodtest_dbcheck29.getChildren();
					methodf30 = ModelTestUtils.getAssertMethod( methodtest_dbcheck29Childs, "f", 2 );
					ModelTestUtils.assertParameterNames( methodf30, new String[] {"a", "b"} );
				}
			}
			//Function test:test_memoize
			{
			IMethod methodtest_memoize31;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_memoize31 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_memoize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_memoize31, new String[] {"self"} );
				//Function test:double
				{
				IMethod methoddouble32;
					IModelElement[] methodtest_memoize31Childs = methodtest_memoize31.getChildren();
					methoddouble32 = ModelTestUtils.getAssertMethod( methodtest_memoize31Childs, "double", 1 );
					ModelTestUtils.assertParameterNames( methoddouble32, new String[] {"x"} );
				}
			}
			//Function test:test_errors
			{
			IMethod methodtest_errors33;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_errors33 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors33, new String[] {"self"} );
				//Function test:unimp
				{
				IMethod methodunimp34;
					IModelElement[] methodtest_errors33Childs = methodtest_errors33.getChildren();
					methodunimp34 = ModelTestUtils.getAssertMethod( methodtest_errors33Childs, "unimp", 1 );
					ModelTestUtils.assertParameterNames( methodunimp34, new String[] {"func"} );
				}
			}
			//Function test:test_double
			{
			IMethod methodtest_double35;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_double35 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_double", 1 );
				ModelTestUtils.assertParameterNames( methodtest_double35, new String[] {"self"} );
				//Class test
				{
				IType classC36;
					IModelElement[] methodtest_double35Childs = methodtest_double35.getChildren();
					classC36 = ModelTestUtils.getAssertClass( methodtest_double35Childs, "C" );
					//Function test:foo
					{
					IMethod methodfoo37;
						IModelElement[] classC36Childs = classC36.getChildren();
						methodfoo37 = ModelTestUtils.getAssertMethod( classC36Childs, "foo", 1 );
						ModelTestUtils.assertParameterNames( methodfoo37, new String[] {"self"} );
					}
				}
			}
			//Function test:test_order
			{
			IMethod methodtest_order38;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_order38 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_order", 1 );
				ModelTestUtils.assertParameterNames( methodtest_order38, new String[] {"self"} );
				//Function test:callnum
				{
				IMethod methodcallnum39;
					IModelElement[] methodtest_order38Childs = methodtest_order38.getChildren();
					methodcallnum39 = ModelTestUtils.getAssertMethod( methodtest_order38Childs, "callnum", 1 );
					ModelTestUtils.assertParameterNames( methodcallnum39, new String[] {"num"} );
					//Function test:deco
					{
					IMethod methoddeco40;
						IModelElement[] methodcallnum39Childs = methodcallnum39.getChildren();
						methoddeco40 = ModelTestUtils.getAssertMethod( methodcallnum39Childs, "deco", 1 );
						ModelTestUtils.assertParameterNames( methoddeco40, new String[] {"func"} );
					}
				}
				//Function test:foo
				{
				IMethod methodfoo41;
					IModelElement[] methodtest_order38Childs = methodtest_order38.getChildren();
					methodfoo41 = ModelTestUtils.getAssertMethod( methodtest_order38Childs, "foo", 0 );
				}
			}
			//Function test:test_eval_order
			{
			IMethod methodtest_eval_order42;
				IModelElement[] classTestDecorators15Childs = classTestDecorators15.getChildren();
				methodtest_eval_order42 = ModelTestUtils.getAssertMethod( classTestDecorators15Childs, "test_eval_order", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eval_order42, new String[] {"self"} );
				//Function test:make_decorator
				{
				IMethod methodmake_decorator43;
					IModelElement[] methodtest_eval_order42Childs = methodtest_eval_order42.getChildren();
					methodmake_decorator43 = ModelTestUtils.getAssertMethod( methodtest_eval_order42Childs, "make_decorator", 1 );
					ModelTestUtils.assertParameterNames( methodmake_decorator43, new String[] {"tag"} );
					//Function test:decorate
					{
					IMethod methoddecorate44;
						IModelElement[] methodmake_decorator43Childs = methodmake_decorator43.getChildren();
						methoddecorate44 = ModelTestUtils.getAssertMethod( methodmake_decorator43Childs, "decorate", 1 );
						ModelTestUtils.assertParameterNames( methoddecorate44, new String[] {"func"} );
					}
				}
				//Class test
				{
				IType classNameLookupTracer45;
					IModelElement[] methodtest_eval_order42Childs = methodtest_eval_order42.getChildren();
					classNameLookupTracer45 = ModelTestUtils.getAssertClass( methodtest_eval_order42Childs, "NameLookupTracer" );
					//Function test:__init__
					{
					IMethod method__init__46;
						IModelElement[] classNameLookupTracer45Childs = classNameLookupTracer45.getChildren();
						method__init__46 = ModelTestUtils.getAssertMethod( classNameLookupTracer45Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__46, new String[] {"self", "index"} );
					}
					{
						IModelElement[] classNameLookupTracer45Childs = classNameLookupTracer45.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classNameLookupTracer45Childs, "index");
					}
					//Function test:__getattr__
					{
					IMethod method__getattr__48;
						IModelElement[] classNameLookupTracer45Childs = classNameLookupTracer45.getChildren();
						method__getattr__48 = ModelTestUtils.getAssertMethod( classNameLookupTracer45Childs, "__getattr__", 2 );
						ModelTestUtils.assertParameterNames( method__getattr__48, new String[] {"self", "fname"} );
					}
				}
				//Function test:foo
				{
				IMethod methodfoo49;
					IModelElement[] methodtest_eval_order42Childs = methodtest_eval_order42.getChildren();
					methodfoo49 = ModelTestUtils.getAssertMethod( methodtest_eval_order42Childs, "foo", 0 );
				}
				//Function test:bar
				{
				IMethod methodbar50;
					IModelElement[] methodtest_eval_order42Childs = methodtest_eval_order42.getChildren();
					methodbar50 = ModelTestUtils.getAssertMethod( methodtest_eval_order42Childs, "bar", 0 );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main51;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main51 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen212( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("pydocfodder.py"));

		assertNotNull("Module pydocfodder.py not found", module);
		assertEquals("pydocfodder.py", module.getElementName());
		
		//Class test
		{
		IType classA_classic0;
			IModelElement[] moduleChilds = module.getChildren();
			classA_classic0 = ModelTestUtils.getAssertClass( moduleChilds, "A_classic" );
			//Function test:A_method
			{
			IMethod methodA_method1;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodA_method1 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "A_method", 1 );
				ModelTestUtils.assertParameterNames( methodA_method1, new String[] {"self"} );
			}
			//Function test:AB_method
			{
			IMethod methodAB_method2;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodAB_method2 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "AB_method", 1 );
				ModelTestUtils.assertParameterNames( methodAB_method2, new String[] {"self"} );
			}
			//Function test:AC_method
			{
			IMethod methodAC_method3;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodAC_method3 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "AC_method", 1 );
				ModelTestUtils.assertParameterNames( methodAC_method3, new String[] {"self"} );
			}
			//Function test:AD_method
			{
			IMethod methodAD_method4;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodAD_method4 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "AD_method", 1 );
				ModelTestUtils.assertParameterNames( methodAD_method4, new String[] {"self"} );
			}
			//Function test:ABC_method
			{
			IMethod methodABC_method5;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodABC_method5 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "ABC_method", 1 );
				ModelTestUtils.assertParameterNames( methodABC_method5, new String[] {"self"} );
			}
			//Function test:ABD_method
			{
			IMethod methodABD_method6;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodABD_method6 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "ABD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABD_method6, new String[] {"self"} );
			}
			//Function test:ACD_method
			{
			IMethod methodACD_method7;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodACD_method7 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "ACD_method", 1 );
				ModelTestUtils.assertParameterNames( methodACD_method7, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method8;
				IModelElement[] classA_classic0Childs = classA_classic0.getChildren();
				methodABCD_method8 = ModelTestUtils.getAssertMethod( classA_classic0Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classB_classic9;
			IModelElement[] moduleChilds = module.getChildren();
			classB_classic9 = ModelTestUtils.getAssertClass( moduleChilds, "B_classic" );
			//Function test:AB_method
			{
			IMethod methodAB_method10;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodAB_method10 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "AB_method", 1 );
				ModelTestUtils.assertParameterNames( methodAB_method10, new String[] {"self"} );
			}
			//Function test:ABC_method
			{
			IMethod methodABC_method11;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodABC_method11 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "ABC_method", 1 );
				ModelTestUtils.assertParameterNames( methodABC_method11, new String[] {"self"} );
			}
			//Function test:ABD_method
			{
			IMethod methodABD_method12;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodABD_method12 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "ABD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABD_method12, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method13;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodABCD_method13 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method13, new String[] {"self"} );
			}
			//Function test:B_method
			{
			IMethod methodB_method14;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodB_method14 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "B_method", 1 );
				ModelTestUtils.assertParameterNames( methodB_method14, new String[] {"self"} );
			}
			//Function test:BC_method
			{
			IMethod methodBC_method15;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodBC_method15 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "BC_method", 1 );
				ModelTestUtils.assertParameterNames( methodBC_method15, new String[] {"self"} );
			}
			//Function test:BD_method
			{
			IMethod methodBD_method16;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodBD_method16 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "BD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBD_method16, new String[] {"self"} );
			}
			//Function test:BCD_method
			{
			IMethod methodBCD_method17;
				IModelElement[] classB_classic9Childs = classB_classic9.getChildren();
				methodBCD_method17 = ModelTestUtils.getAssertMethod( classB_classic9Childs, "BCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBCD_method17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC_classic18;
			IModelElement[] moduleChilds = module.getChildren();
			classC_classic18 = ModelTestUtils.getAssertClass( moduleChilds, "C_classic" );
			//Function test:AC_method
			{
			IMethod methodAC_method19;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodAC_method19 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "AC_method", 1 );
				ModelTestUtils.assertParameterNames( methodAC_method19, new String[] {"self"} );
			}
			//Function test:ABC_method
			{
			IMethod methodABC_method20;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodABC_method20 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "ABC_method", 1 );
				ModelTestUtils.assertParameterNames( methodABC_method20, new String[] {"self"} );
			}
			//Function test:ACD_method
			{
			IMethod methodACD_method21;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodACD_method21 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "ACD_method", 1 );
				ModelTestUtils.assertParameterNames( methodACD_method21, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method22;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodABCD_method22 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method22, new String[] {"self"} );
			}
			//Function test:BC_method
			{
			IMethod methodBC_method23;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodBC_method23 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "BC_method", 1 );
				ModelTestUtils.assertParameterNames( methodBC_method23, new String[] {"self"} );
			}
			//Function test:BCD_method
			{
			IMethod methodBCD_method24;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodBCD_method24 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "BCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBCD_method24, new String[] {"self"} );
			}
			//Function test:C_method
			{
			IMethod methodC_method25;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodC_method25 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "C_method", 1 );
				ModelTestUtils.assertParameterNames( methodC_method25, new String[] {"self"} );
			}
			//Function test:CD_method
			{
			IMethod methodCD_method26;
				IModelElement[] classC_classic18Childs = classC_classic18.getChildren();
				methodCD_method26 = ModelTestUtils.getAssertMethod( classC_classic18Childs, "CD_method", 1 );
				ModelTestUtils.assertParameterNames( methodCD_method26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classD_classic27;
			IModelElement[] moduleChilds = module.getChildren();
			classD_classic27 = ModelTestUtils.getAssertClass( moduleChilds, "D_classic" );
			//Function test:AD_method
			{
			IMethod methodAD_method28;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodAD_method28 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "AD_method", 1 );
				ModelTestUtils.assertParameterNames( methodAD_method28, new String[] {"self"} );
			}
			//Function test:ABD_method
			{
			IMethod methodABD_method29;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodABD_method29 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "ABD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABD_method29, new String[] {"self"} );
			}
			//Function test:ACD_method
			{
			IMethod methodACD_method30;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodACD_method30 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "ACD_method", 1 );
				ModelTestUtils.assertParameterNames( methodACD_method30, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method31;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodABCD_method31 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method31, new String[] {"self"} );
			}
			//Function test:BD_method
			{
			IMethod methodBD_method32;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodBD_method32 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "BD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBD_method32, new String[] {"self"} );
			}
			//Function test:BCD_method
			{
			IMethod methodBCD_method33;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodBCD_method33 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "BCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBCD_method33, new String[] {"self"} );
			}
			//Function test:CD_method
			{
			IMethod methodCD_method34;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodCD_method34 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "CD_method", 1 );
				ModelTestUtils.assertParameterNames( methodCD_method34, new String[] {"self"} );
			}
			//Function test:D_method
			{
			IMethod methodD_method35;
				IModelElement[] classD_classic27Childs = classD_classic27.getChildren();
				methodD_method35 = ModelTestUtils.getAssertMethod( classD_classic27Childs, "D_method", 1 );
				ModelTestUtils.assertParameterNames( methodD_method35, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classA_new36;
			IModelElement[] moduleChilds = module.getChildren();
			classA_new36 = ModelTestUtils.getAssertClass( moduleChilds, "A_new" );
			//Function test:A_method
			{
			IMethod methodA_method37;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodA_method37 = ModelTestUtils.getAssertMethod( classA_new36Childs, "A_method", 1 );
				ModelTestUtils.assertParameterNames( methodA_method37, new String[] {"self"} );
			}
			//Function test:AB_method
			{
			IMethod methodAB_method38;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodAB_method38 = ModelTestUtils.getAssertMethod( classA_new36Childs, "AB_method", 1 );
				ModelTestUtils.assertParameterNames( methodAB_method38, new String[] {"self"} );
			}
			//Function test:AC_method
			{
			IMethod methodAC_method39;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodAC_method39 = ModelTestUtils.getAssertMethod( classA_new36Childs, "AC_method", 1 );
				ModelTestUtils.assertParameterNames( methodAC_method39, new String[] {"self"} );
			}
			//Function test:AD_method
			{
			IMethod methodAD_method40;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodAD_method40 = ModelTestUtils.getAssertMethod( classA_new36Childs, "AD_method", 1 );
				ModelTestUtils.assertParameterNames( methodAD_method40, new String[] {"self"} );
			}
			//Function test:ABC_method
			{
			IMethod methodABC_method41;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodABC_method41 = ModelTestUtils.getAssertMethod( classA_new36Childs, "ABC_method", 1 );
				ModelTestUtils.assertParameterNames( methodABC_method41, new String[] {"self"} );
			}
			//Function test:ABD_method
			{
			IMethod methodABD_method42;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodABD_method42 = ModelTestUtils.getAssertMethod( classA_new36Childs, "ABD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABD_method42, new String[] {"self"} );
			}
			//Function test:ACD_method
			{
			IMethod methodACD_method43;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodACD_method43 = ModelTestUtils.getAssertMethod( classA_new36Childs, "ACD_method", 1 );
				ModelTestUtils.assertParameterNames( methodACD_method43, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method44;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodABCD_method44 = ModelTestUtils.getAssertMethod( classA_new36Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method44, new String[] {"self"} );
			}
			//Function test:A_classmethod
			{
			IMethod methodA_classmethod45;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodA_classmethod45 = ModelTestUtils.getAssertMethod( classA_new36Childs, "A_classmethod", 2 );
				ModelTestUtils.assertParameterNames( methodA_classmethod45, new String[] {"cls", "x"} );
			}
			{
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA_new36Childs, "A_classmethod");
			}
			//Function test:A_staticmethod
			{
			IMethod methodA_staticmethod46;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				methodA_staticmethod46 = ModelTestUtils.getAssertMethod( classA_new36Childs, "A_staticmethod", 0 );
			}
			{
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA_new36Childs, "A_staticmethod");
			}
			//Function test:_getx
			{
			IMethod method_getx47;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				method_getx47 = ModelTestUtils.getAssertMethod( classA_new36Childs, "_getx", 1 );
				ModelTestUtils.assertParameterNames( method_getx47, new String[] {"self"} );
			}
			//Function test:_setx
			{
			IMethod method_setx48;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				method_setx48 = ModelTestUtils.getAssertMethod( classA_new36Childs, "_setx", 2 );
				ModelTestUtils.assertParameterNames( method_setx48, new String[] {"self", "value"} );
			}
			//Function test:_delx
			{
			IMethod method_delx49;
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				method_delx49 = ModelTestUtils.getAssertMethod( classA_new36Childs, "_delx", 1 );
				ModelTestUtils.assertParameterNames( method_delx49, new String[] {"self"} );
			}
			{
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA_new36Childs, "A_property");
			}
			{
				IModelElement[] classA_new36Childs = classA_new36.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA_new36Childs, "A_int_alias");
			}
		}
		//Class test
		{
		IType classB_new50;
			IModelElement[] moduleChilds = module.getChildren();
			classB_new50 = ModelTestUtils.getAssertClass( moduleChilds, "B_new" );
			//Function test:AB_method
			{
			IMethod methodAB_method51;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodAB_method51 = ModelTestUtils.getAssertMethod( classB_new50Childs, "AB_method", 1 );
				ModelTestUtils.assertParameterNames( methodAB_method51, new String[] {"self"} );
			}
			//Function test:ABC_method
			{
			IMethod methodABC_method52;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodABC_method52 = ModelTestUtils.getAssertMethod( classB_new50Childs, "ABC_method", 1 );
				ModelTestUtils.assertParameterNames( methodABC_method52, new String[] {"self"} );
			}
			//Function test:ABD_method
			{
			IMethod methodABD_method53;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodABD_method53 = ModelTestUtils.getAssertMethod( classB_new50Childs, "ABD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABD_method53, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method54;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodABCD_method54 = ModelTestUtils.getAssertMethod( classB_new50Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method54, new String[] {"self"} );
			}
			//Function test:B_method
			{
			IMethod methodB_method55;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodB_method55 = ModelTestUtils.getAssertMethod( classB_new50Childs, "B_method", 1 );
				ModelTestUtils.assertParameterNames( methodB_method55, new String[] {"self"} );
			}
			//Function test:BC_method
			{
			IMethod methodBC_method56;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodBC_method56 = ModelTestUtils.getAssertMethod( classB_new50Childs, "BC_method", 1 );
				ModelTestUtils.assertParameterNames( methodBC_method56, new String[] {"self"} );
			}
			//Function test:BD_method
			{
			IMethod methodBD_method57;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodBD_method57 = ModelTestUtils.getAssertMethod( classB_new50Childs, "BD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBD_method57, new String[] {"self"} );
			}
			//Function test:BCD_method
			{
			IMethod methodBCD_method58;
				IModelElement[] classB_new50Childs = classB_new50.getChildren();
				methodBCD_method58 = ModelTestUtils.getAssertMethod( classB_new50Childs, "BCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBCD_method58, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC_new59;
			IModelElement[] moduleChilds = module.getChildren();
			classC_new59 = ModelTestUtils.getAssertClass( moduleChilds, "C_new" );
			//Function test:AC_method
			{
			IMethod methodAC_method60;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodAC_method60 = ModelTestUtils.getAssertMethod( classC_new59Childs, "AC_method", 1 );
				ModelTestUtils.assertParameterNames( methodAC_method60, new String[] {"self"} );
			}
			//Function test:ABC_method
			{
			IMethod methodABC_method61;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodABC_method61 = ModelTestUtils.getAssertMethod( classC_new59Childs, "ABC_method", 1 );
				ModelTestUtils.assertParameterNames( methodABC_method61, new String[] {"self"} );
			}
			//Function test:ACD_method
			{
			IMethod methodACD_method62;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodACD_method62 = ModelTestUtils.getAssertMethod( classC_new59Childs, "ACD_method", 1 );
				ModelTestUtils.assertParameterNames( methodACD_method62, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method63;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodABCD_method63 = ModelTestUtils.getAssertMethod( classC_new59Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method63, new String[] {"self"} );
			}
			//Function test:BC_method
			{
			IMethod methodBC_method64;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodBC_method64 = ModelTestUtils.getAssertMethod( classC_new59Childs, "BC_method", 1 );
				ModelTestUtils.assertParameterNames( methodBC_method64, new String[] {"self"} );
			}
			//Function test:BCD_method
			{
			IMethod methodBCD_method65;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodBCD_method65 = ModelTestUtils.getAssertMethod( classC_new59Childs, "BCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBCD_method65, new String[] {"self"} );
			}
			//Function test:C_method
			{
			IMethod methodC_method66;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodC_method66 = ModelTestUtils.getAssertMethod( classC_new59Childs, "C_method", 1 );
				ModelTestUtils.assertParameterNames( methodC_method66, new String[] {"self"} );
			}
			//Function test:CD_method
			{
			IMethod methodCD_method67;
				IModelElement[] classC_new59Childs = classC_new59.getChildren();
				methodCD_method67 = ModelTestUtils.getAssertMethod( classC_new59Childs, "CD_method", 1 );
				ModelTestUtils.assertParameterNames( methodCD_method67, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classD_new68;
			IModelElement[] moduleChilds = module.getChildren();
			classD_new68 = ModelTestUtils.getAssertClass( moduleChilds, "D_new" );
			//Function test:AD_method
			{
			IMethod methodAD_method69;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodAD_method69 = ModelTestUtils.getAssertMethod( classD_new68Childs, "AD_method", 1 );
				ModelTestUtils.assertParameterNames( methodAD_method69, new String[] {"self"} );
			}
			//Function test:ABD_method
			{
			IMethod methodABD_method70;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodABD_method70 = ModelTestUtils.getAssertMethod( classD_new68Childs, "ABD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABD_method70, new String[] {"self"} );
			}
			//Function test:ACD_method
			{
			IMethod methodACD_method71;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodACD_method71 = ModelTestUtils.getAssertMethod( classD_new68Childs, "ACD_method", 1 );
				ModelTestUtils.assertParameterNames( methodACD_method71, new String[] {"self"} );
			}
			//Function test:ABCD_method
			{
			IMethod methodABCD_method72;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodABCD_method72 = ModelTestUtils.getAssertMethod( classD_new68Childs, "ABCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodABCD_method72, new String[] {"self"} );
			}
			//Function test:BD_method
			{
			IMethod methodBD_method73;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodBD_method73 = ModelTestUtils.getAssertMethod( classD_new68Childs, "BD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBD_method73, new String[] {"self"} );
			}
			//Function test:BCD_method
			{
			IMethod methodBCD_method74;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodBCD_method74 = ModelTestUtils.getAssertMethod( classD_new68Childs, "BCD_method", 1 );
				ModelTestUtils.assertParameterNames( methodBCD_method74, new String[] {"self"} );
			}
			//Function test:CD_method
			{
			IMethod methodCD_method75;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodCD_method75 = ModelTestUtils.getAssertMethod( classD_new68Childs, "CD_method", 1 );
				ModelTestUtils.assertParameterNames( methodCD_method75, new String[] {"self"} );
			}
			//Function test:D_method
			{
			IMethod methodD_method76;
				IModelElement[] classD_new68Childs = classD_new68.getChildren();
				methodD_method76 = ModelTestUtils.getAssertMethod( classD_new68Childs, "D_method", 1 );
				ModelTestUtils.assertParameterNames( methodD_method76, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFunkyProperties77;
			IModelElement[] moduleChilds = module.getChildren();
			classFunkyProperties77 = ModelTestUtils.getAssertClass( moduleChilds, "FunkyProperties" );
			//Function test:__init__
			{
			IMethod method__init__78;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				method__init__78 = ModelTestUtils.getAssertMethod( classFunkyProperties77Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__78, new String[] {"self"} );
			}
			{
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFunkyProperties77Childs, "desc");
			}
			//Class test
			{
			IType classget_desc80;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				classget_desc80 = ModelTestUtils.getAssertClass( classFunkyProperties77Childs, "get_desc" );
				//Function test:__init__
				{
				IMethod method__init__81;
					IModelElement[] classget_desc80Childs = classget_desc80.getChildren();
					method__init__81 = ModelTestUtils.getAssertMethod( classget_desc80Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__81, new String[] {"self", "attr"} );
				}
				{
					IModelElement[] classget_desc80Childs = classget_desc80.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classget_desc80Childs, "attr");
				}
				//Function test:__call__
				{
				IMethod method__call__83;
					IModelElement[] classget_desc80Childs = classget_desc80.getChildren();
					method__call__83 = ModelTestUtils.getAssertMethod( classget_desc80Childs, "__call__", 2 );
					ModelTestUtils.assertParameterNames( method__call__83, new String[] {"self", "inst"} );
				}
			}
			//Class test
			{
			IType classset_desc84;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				classset_desc84 = ModelTestUtils.getAssertClass( classFunkyProperties77Childs, "set_desc" );
				//Function test:__init__
				{
				IMethod method__init__85;
					IModelElement[] classset_desc84Childs = classset_desc84.getChildren();
					method__init__85 = ModelTestUtils.getAssertMethod( classset_desc84Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__85, new String[] {"self", "attr"} );
				}
				//Function test:__call__
				{
				IMethod method__call__86;
					IModelElement[] classset_desc84Childs = classset_desc84.getChildren();
					method__call__86 = ModelTestUtils.getAssertMethod( classset_desc84Childs, "__call__", 3 );
					ModelTestUtils.assertParameterNames( method__call__86, new String[] {"self", "inst", "val"} );
				}
			}
			//Class test
			{
			IType classdel_desc87;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				classdel_desc87 = ModelTestUtils.getAssertClass( classFunkyProperties77Childs, "del_desc" );
				//Function test:__init__
				{
				IMethod method__init__88;
					IModelElement[] classdel_desc87Childs = classdel_desc87.getChildren();
					method__init__88 = ModelTestUtils.getAssertMethod( classdel_desc87Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__88, new String[] {"self", "attr"} );
				}
				//Function test:__call__
				{
				IMethod method__call__89;
					IModelElement[] classdel_desc87Childs = classdel_desc87.getChildren();
					method__call__89 = ModelTestUtils.getAssertMethod( classdel_desc87Childs, "__call__", 2 );
					ModelTestUtils.assertParameterNames( method__call__89, new String[] {"self", "inst"} );
				}
			}
			{
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFunkyProperties77Childs, "x");
			}
		}

	}
	public void testModelGen213( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sax.py"));

		assertNotNull("Module test_sax.py not found", module);
		assertEquals("test_sax.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "failures");
		}
		//Function test:confirm
		{
		IMethod methodconfirm0;
			IModelElement[] moduleChilds = module.getChildren();
			methodconfirm0 = ModelTestUtils.getAssertMethod( moduleChilds, "confirm", 2 );
			ModelTestUtils.assertParameterNames( methodconfirm0, new String[] {"outcome", "name"} );
		}
		//Function test:test_make_parser2
		{
		IMethod methodtest_make_parser21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_make_parser21 = ModelTestUtils.getAssertMethod( moduleChilds, "test_make_parser2", 0 );
		}
		//Function test:test_escape_basic
		{
		IMethod methodtest_escape_basic2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_escape_basic2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_escape_basic", 0 );
		}
		//Function test:test_escape_all
		{
		IMethod methodtest_escape_all3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_escape_all3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_escape_all", 0 );
		}
		//Function test:test_escape_extra
		{
		IMethod methodtest_escape_extra4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_escape_extra4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_escape_extra", 0 );
		}
		//Function test:test_unescape_basic
		{
		IMethod methodtest_unescape_basic5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_unescape_basic5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_unescape_basic", 0 );
		}
		//Function test:test_unescape_all
		{
		IMethod methodtest_unescape_all6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_unescape_all6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_unescape_all", 0 );
		}
		//Function test:test_unescape_extra
		{
		IMethod methodtest_unescape_extra7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_unescape_extra7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_unescape_extra", 0 );
		}
		//Function test:test_unescape_amp_extra
		{
		IMethod methodtest_unescape_amp_extra8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_unescape_amp_extra8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_unescape_amp_extra", 0 );
		}
		//Function test:test_quoteattr_basic
		{
		IMethod methodtest_quoteattr_basic9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_quoteattr_basic9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_quoteattr_basic", 0 );
		}
		//Function test:test_single_quoteattr
		{
		IMethod methodtest_single_quoteattr10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_single_quoteattr10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_single_quoteattr", 0 );
		}
		//Function test:test_double_quoteattr
		{
		IMethod methodtest_double_quoteattr11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_double_quoteattr11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_double_quoteattr", 0 );
		}
		//Function test:test_single_double_quoteattr
		{
		IMethod methodtest_single_double_quoteattr12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_single_double_quoteattr12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_single_double_quoteattr", 0 );
		}
		//Function test:test_make_parser
		{
		IMethod methodtest_make_parser13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_make_parser13 = ModelTestUtils.getAssertMethod( moduleChilds, "test_make_parser", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "start");
		}
		//Function test:test_xmlgen_basic
		{
		IMethod methodtest_xmlgen_basic14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_basic14 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_basic", 0 );
		}
		//Function test:test_xmlgen_content
		{
		IMethod methodtest_xmlgen_content15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_content15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_content", 0 );
		}
		//Function test:test_xmlgen_pi
		{
		IMethod methodtest_xmlgen_pi16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_pi16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_pi", 0 );
		}
		//Function test:test_xmlgen_content_escape
		{
		IMethod methodtest_xmlgen_content_escape17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_content_escape17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_content_escape", 0 );
		}
		//Function test:test_xmlgen_attr_escape
		{
		IMethod methodtest_xmlgen_attr_escape18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_attr_escape18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_attr_escape", 0 );
		}
		//Function test:test_xmlgen_ignorable
		{
		IMethod methodtest_xmlgen_ignorable19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_ignorable19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_ignorable", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ns_uri");
		}
		//Function test:test_xmlgen_ns
		{
		IMethod methodtest_xmlgen_ns20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_xmlgen_ns20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_xmlgen_ns", 0 );
		}
		//Function test:test_filter_basic
		{
		IMethod methodtest_filter_basic21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_filter_basic21 = ModelTestUtils.getAssertMethod( moduleChilds, "test_filter_basic", 0 );
		}
		//Function test:test_expat_file
		{
		IMethod methodtest_expat_file22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_file22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_file", 0 );
		}
		//Class test
		{
		IType classTestDTDHandler23;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDTDHandler23 = ModelTestUtils.getAssertClass( moduleChilds, "TestDTDHandler" );
			//Function test:__init__
			{
			IMethod method__init__24;
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				method__init__24 = ModelTestUtils.getAssertMethod( classTestDTDHandler23Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__24, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDTDHandler23Childs, "_notations");
			}
			{
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDTDHandler23Childs, "_entities");
			}
			//Function test:notationDecl
			{
			IMethod methodnotationDecl26;
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				methodnotationDecl26 = ModelTestUtils.getAssertMethod( classTestDTDHandler23Childs, "notationDecl", 4 );
				ModelTestUtils.assertParameterNames( methodnotationDecl26, new String[] {"self", "name", "publicId", "systemId"} );
			}
			//Function test:unparsedEntityDecl
			{
			IMethod methodunparsedEntityDecl27;
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				methodunparsedEntityDecl27 = ModelTestUtils.getAssertMethod( classTestDTDHandler23Childs, "unparsedEntityDecl", 5 );
				ModelTestUtils.assertParameterNames( methodunparsedEntityDecl27, new String[] {"self", "name", "publicId", "systemId", "ndata"} );
			}
		}
		//Function test:test_expat_dtdhandler
		{
		IMethod methodtest_expat_dtdhandler28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_dtdhandler28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_dtdhandler", 0 );
		}
		//Class test
		{
		IType classTestEntityResolver29;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEntityResolver29 = ModelTestUtils.getAssertClass( moduleChilds, "TestEntityResolver" );
			//Function test:resolveEntity
			{
			IMethod methodresolveEntity30;
				IModelElement[] classTestEntityResolver29Childs = classTestEntityResolver29.getChildren();
				methodresolveEntity30 = ModelTestUtils.getAssertMethod( classTestEntityResolver29Childs, "resolveEntity", 3 );
				ModelTestUtils.assertParameterNames( methodresolveEntity30, new String[] {"self", "publicId", "systemId"} );
			}
		}
		//Function test:test_expat_entityresolver
		{
		IMethod methodtest_expat_entityresolver31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_entityresolver31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_entityresolver", 0 );
		}
		//Class test
		{
		IType classAttrGatherer32;
			IModelElement[] moduleChilds = module.getChildren();
			classAttrGatherer32 = ModelTestUtils.getAssertClass( moduleChilds, "AttrGatherer" );
			//Function test:startElement
			{
			IMethod methodstartElement33;
				IModelElement[] classAttrGatherer32Childs = classAttrGatherer32.getChildren();
				methodstartElement33 = ModelTestUtils.getAssertMethod( classAttrGatherer32Childs, "startElement", 3 );
				ModelTestUtils.assertParameterNames( methodstartElement33, new String[] {"self", "name", "attrs"} );
			}
			{
				IModelElement[] classAttrGatherer32Childs = classAttrGatherer32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAttrGatherer32Childs, "_attrs");
			}
			//Function test:startElementNS
			{
			IMethod methodstartElementNS35;
				IModelElement[] classAttrGatherer32Childs = classAttrGatherer32.getChildren();
				methodstartElementNS35 = ModelTestUtils.getAssertMethod( classAttrGatherer32Childs, "startElementNS", 4 );
				ModelTestUtils.assertParameterNames( methodstartElementNS35, new String[] {"self", "name", "qname", "attrs"} );
			}
			{
				IModelElement[] classAttrGatherer32Childs = classAttrGatherer32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAttrGatherer32Childs, "_attrs");
			}
		}
		//Function test:test_expat_attrs_empty
		{
		IMethod methodtest_expat_attrs_empty37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_attrs_empty37 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_attrs_empty", 0 );
		}
		//Function test:test_expat_attrs_wattr
		{
		IMethod methodtest_expat_attrs_wattr38;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_attrs_wattr38 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_attrs_wattr", 0 );
		}
		//Function test:test_expat_nsattrs_empty
		{
		IMethod methodtest_expat_nsattrs_empty39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_nsattrs_empty39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_nsattrs_empty", 0 );
		}
		//Function test:test_expat_nsattrs_wattr
		{
		IMethod methodtest_expat_nsattrs_wattr40;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_nsattrs_wattr40 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_nsattrs_wattr", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "xml_test_out");
		}
		//Function test:test_expat_inpsource_filename
		{
		IMethod methodtest_expat_inpsource_filename41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_filename41 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_filename", 0 );
		}
		//Function test:test_expat_inpsource_sysid
		{
		IMethod methodtest_expat_inpsource_sysid42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_sysid42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_sysid", 0 );
		}
		//Function test:test_expat_inpsource_stream
		{
		IMethod methodtest_expat_inpsource_stream43;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_stream43 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_stream", 0 );
		}
		//Function test:test_expat_incremental
		{
		IMethod methodtest_expat_incremental44;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_incremental44 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_incremental", 0 );
		}
		//Function test:test_expat_incremental_reset
		{
		IMethod methodtest_expat_incremental_reset45;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_incremental_reset45 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_incremental_reset", 0 );
		}
		//Function test:test_expat_locator_noinfo
		{
		IMethod methodtest_expat_locator_noinfo46;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_locator_noinfo46 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_locator_noinfo", 0 );
		}
		//Function test:test_expat_locator_withinfo
		{
		IMethod methodtest_expat_locator_withinfo47;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_locator_withinfo47 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_locator_withinfo", 0 );
		}
		//Function test:test_expat_inpsource_location
		{
		IMethod methodtest_expat_inpsource_location48;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_location48 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_location", 0 );
		}
		//Function test:test_expat_incomplete
		{
		IMethod methodtest_expat_incomplete49;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_incomplete49 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_incomplete", 0 );
		}
		//Function test:test_sax_parse_exception_str
		{
		IMethod methodtest_sax_parse_exception_str50;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_sax_parse_exception_str50 = ModelTestUtils.getAssertMethod( moduleChilds, "test_sax_parse_exception_str", 0 );
		}
		//Class test
		{
		IType classDummyLocator51;
			IModelElement[] moduleChilds = module.getChildren();
			classDummyLocator51 = ModelTestUtils.getAssertClass( moduleChilds, "DummyLocator" );
			//Function test:__init__
			{
			IMethod method__init__52;
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				method__init__52 = ModelTestUtils.getAssertMethod( classDummyLocator51Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__52, new String[] {"self", "lineno", "colno"} );
			}
			{
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDummyLocator51Childs, "_lineno");
			}
			{
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDummyLocator51Childs, "_colno");
			}
			//Function test:getPublicId
			{
			IMethod methodgetPublicId54;
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				methodgetPublicId54 = ModelTestUtils.getAssertMethod( classDummyLocator51Childs, "getPublicId", 1 );
				ModelTestUtils.assertParameterNames( methodgetPublicId54, new String[] {"self"} );
			}
			//Function test:getSystemId
			{
			IMethod methodgetSystemId55;
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				methodgetSystemId55 = ModelTestUtils.getAssertMethod( classDummyLocator51Childs, "getSystemId", 1 );
				ModelTestUtils.assertParameterNames( methodgetSystemId55, new String[] {"self"} );
			}
			//Function test:getLineNumber
			{
			IMethod methodgetLineNumber56;
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				methodgetLineNumber56 = ModelTestUtils.getAssertMethod( classDummyLocator51Childs, "getLineNumber", 1 );
				ModelTestUtils.assertParameterNames( methodgetLineNumber56, new String[] {"self"} );
			}
			//Function test:getColumnNumber
			{
			IMethod methodgetColumnNumber57;
				IModelElement[] classDummyLocator51Childs = classDummyLocator51.getChildren();
				methodgetColumnNumber57 = ModelTestUtils.getAssertMethod( classDummyLocator51Childs, "getColumnNumber", 1 );
				ModelTestUtils.assertParameterNames( methodgetColumnNumber57, new String[] {"self"} );
			}
		}
		//Function test:verify_empty_attrs
		{
		IMethod methodverify_empty_attrs58;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_empty_attrs58 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_empty_attrs", 1 );
			ModelTestUtils.assertParameterNames( methodverify_empty_attrs58, new String[] {"attrs"} );
		}
		//Function test:verify_attrs_wattr
		{
		IMethod methodverify_attrs_wattr59;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_attrs_wattr59 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_attrs_wattr", 1 );
			ModelTestUtils.assertParameterNames( methodverify_attrs_wattr59, new String[] {"attrs"} );
		}
		//Function test:test_attrs_empty
		{
		IMethod methodtest_attrs_empty60;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_attrs_empty60 = ModelTestUtils.getAssertMethod( moduleChilds, "test_attrs_empty", 0 );
		}
		//Function test:test_attrs_wattr
		{
		IMethod methodtest_attrs_wattr61;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_attrs_wattr61 = ModelTestUtils.getAssertMethod( moduleChilds, "test_attrs_wattr", 0 );
		}
		//Function test:verify_empty_nsattrs
		{
		IMethod methodverify_empty_nsattrs62;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_empty_nsattrs62 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_empty_nsattrs", 1 );
			ModelTestUtils.assertParameterNames( methodverify_empty_nsattrs62, new String[] {"attrs"} );
		}
		//Function test:test_nsattrs_empty
		{
		IMethod methodtest_nsattrs_empty63;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_nsattrs_empty63 = ModelTestUtils.getAssertMethod( moduleChilds, "test_nsattrs_empty", 0 );
		}
		//Function test:test_nsattrs_wattr
		{
		IMethod methodtest_nsattrs_wattr64;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_nsattrs_wattr64 = ModelTestUtils.getAssertMethod( moduleChilds, "test_nsattrs_wattr", 0 );
		}
		//Function test:make_test_output
		{
		IMethod methodmake_test_output65;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_test_output65 = ModelTestUtils.getAssertMethod( moduleChilds, "make_test_output", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "items");
		}

	}
	public void testModelGen214( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bufio.py"));

		assertNotNull("Module test_bufio.py not found", module);
		assertEquals("test_bufio.py", module.getElementName());
		
		//Function test:drive_one
		{
		IMethod methoddrive_one0;
			IModelElement[] moduleChilds = module.getChildren();
			methoddrive_one0 = ModelTestUtils.getAssertMethod( moduleChilds, "drive_one", 2 );
			ModelTestUtils.assertParameterNames( methoddrive_one0, new String[] {"pattern", "length"} );
		}
		//Function test:try_one
		{
		IMethod methodtry_one1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtry_one1 = ModelTestUtils.getAssertMethod( moduleChilds, "try_one", 1 );
			ModelTestUtils.assertParameterNames( methodtry_one1, new String[] {"s"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "primepat");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nullpat");
		}

	}
	public void testModelGen215( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_set.py"));

		assertNotNull("Module test_set.py not found", module);
		assertEquals("test_set.py", module.getElementName());
		
		//Class test
		{
		IType classPassThru0;
			IModelElement[] moduleChilds = module.getChildren();
			classPassThru0 = ModelTestUtils.getAssertClass( moduleChilds, "PassThru" );
		}
		//Function test:check_pass_thru
		{
		IMethod methodcheck_pass_thru1;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_pass_thru1 = ModelTestUtils.getAssertMethod( moduleChilds, "check_pass_thru", 0 );
		}
		//Class test
		{
		IType classTestJointOps2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestJointOps2 = ModelTestUtils.getAssertClass( moduleChilds, "TestJointOps" );
			//Function test:setUp
			{
			IMethod methodsetUp3;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodsetUp3 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp3, new String[] {"self"} );
			}
			{
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJointOps2Childs, "word");
			}
			{
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJointOps2Childs, "otherword");
			}
			{
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJointOps2Childs, "letters");
			}
			{
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJointOps2Childs, "s");
			}
			{
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJointOps2Childs, "d");
			}
			//Function test:test_new_or_init
			{
			IMethod methodtest_new_or_init5;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_new_or_init5 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_new_or_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_new_or_init5, new String[] {"self"} );
			}
			//Function test:test_uniquification
			{
			IMethod methodtest_uniquification6;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_uniquification6 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_uniquification", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uniquification6, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len7;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_len7 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len7, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains8;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_contains8 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains8, new String[] {"self"} );
			}
			//Function test:test_union
			{
			IMethod methodtest_union9;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_union9 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union9, new String[] {"self"} );
			}
			//Function test:test_or
			{
			IMethod methodtest_or10;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_or10 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_or", 1 );
				ModelTestUtils.assertParameterNames( methodtest_or10, new String[] {"self"} );
			}
			//Function test:test_intersection
			{
			IMethod methodtest_intersection11;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_intersection11 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection11, new String[] {"self"} );
			}
			//Function test:test_and
			{
			IMethod methodtest_and12;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_and12 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_and", 1 );
				ModelTestUtils.assertParameterNames( methodtest_and12, new String[] {"self"} );
			}
			//Function test:test_difference
			{
			IMethod methodtest_difference13;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_difference13 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference13, new String[] {"self"} );
			}
			//Function test:test_sub
			{
			IMethod methodtest_sub14;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_sub14 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub14, new String[] {"self"} );
			}
			//Function test:test_symmetric_difference
			{
			IMethod methodtest_symmetric_difference15;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_symmetric_difference15 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_symmetric_difference15, new String[] {"self"} );
			}
			//Function test:test_xor
			{
			IMethod methodtest_xor16;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_xor16 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_xor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xor16, new String[] {"self"} );
			}
			//Function test:test_equality
			{
			IMethod methodtest_equality17;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_equality17 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equality17, new String[] {"self"} );
			}
			//Function test:test_setOfFrozensets
			{
			IMethod methodtest_setOfFrozensets18;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_setOfFrozensets18 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_setOfFrozensets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setOfFrozensets18, new String[] {"self"} );
			}
			//Function test:test_compare
			{
			IMethod methodtest_compare19;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_compare19 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compare19, new String[] {"self"} );
			}
			//Function test:test_sub_and_super
			{
			IMethod methodtest_sub_and_super20;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_sub_and_super20 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_sub_and_super", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub_and_super20, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling21;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_pickling21 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling21, new String[] {"self"} );
			}
			//Function test:test_deepcopy
			{
			IMethod methodtest_deepcopy22;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_deepcopy22 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy22, new String[] {"self"} );
				//Class test
				{
				IType classTracer23;
					IModelElement[] methodtest_deepcopy22Childs = methodtest_deepcopy22.getChildren();
					classTracer23 = ModelTestUtils.getAssertClass( methodtest_deepcopy22Childs, "Tracer" );
					//Function test:__init__
					{
					IMethod method__init__24;
						IModelElement[] classTracer23Childs = classTracer23.getChildren();
						method__init__24 = ModelTestUtils.getAssertMethod( classTracer23Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__24, new String[] {"self", "value"} );
					}
					{
						IModelElement[] classTracer23Childs = classTracer23.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classTracer23Childs, "value");
					}
					//Function test:__hash__
					{
					IMethod method__hash__26;
						IModelElement[] classTracer23Childs = classTracer23.getChildren();
						method__hash__26 = ModelTestUtils.getAssertMethod( classTracer23Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__26, new String[] {"self"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__27;
						IModelElement[] classTracer23Childs = classTracer23.getChildren();
						method__deepcopy__27 = ModelTestUtils.getAssertMethod( classTracer23Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__27, new String[] {"self", "memo"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestSet28;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSet28 = ModelTestUtils.getAssertClass( moduleChilds, "TestSet" );
			{
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSet28Childs, "thetype");
			}
			//Function test:test_init
			{
			IMethod methodtest_init29;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_init29 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init29, new String[] {"self"} );
			}
			//Function test:test_constructor_identity
			{
			IMethod methodtest_constructor_identity30;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_constructor_identity30 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_constructor_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_identity30, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash31;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_hash31 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash31, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear32;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_clear32 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear32, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy33;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_copy33 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy33, new String[] {"self"} );
			}
			//Function test:test_add
			{
			IMethod methodtest_add34;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_add34 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_add", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add34, new String[] {"self"} );
			}
			//Function test:test_remove
			{
			IMethod methodtest_remove35;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_remove35 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_remove", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove35, new String[] {"self"} );
			}
			//Function test:test_discard
			{
			IMethod methodtest_discard36;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_discard36 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_discard", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard36, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop37;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_pop37 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop37, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update38;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_update38 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update38, new String[] {"self"} );
			}
			//Function test:test_ior
			{
			IMethod methodtest_ior39;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_ior39 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_ior", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ior39, new String[] {"self"} );
			}
			//Function test:test_intersection_update
			{
			IMethod methodtest_intersection_update40;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_intersection_update40 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_intersection_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update40, new String[] {"self"} );
			}
			//Function test:test_iand
			{
			IMethod methodtest_iand41;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_iand41 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_iand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iand41, new String[] {"self"} );
			}
			//Function test:test_difference_update
			{
			IMethod methodtest_difference_update42;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_difference_update42 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update42, new String[] {"self"} );
			}
			//Function test:test_isub
			{
			IMethod methodtest_isub43;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_isub43 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_isub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isub43, new String[] {"self"} );
			}
			//Function test:test_symmetric_difference_update
			{
			IMethod methodtest_symmetric_difference_update44;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_symmetric_difference_update44 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_symmetric_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_symmetric_difference_update44, new String[] {"self"} );
			}
			//Function test:test_ixor
			{
			IMethod methodtest_ixor45;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_ixor45 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_ixor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ixor45, new String[] {"self"} );
			}
			//Function test:test_weakref
			{
			IMethod methodtest_weakref46;
				IModelElement[] classTestSet28Childs = classTestSet28.getChildren();
				methodtest_weakref46 = ModelTestUtils.getAssertMethod( classTestSet28Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref46, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSetSubclass47;
			IModelElement[] moduleChilds = module.getChildren();
			classSetSubclass47 = ModelTestUtils.getAssertClass( moduleChilds, "SetSubclass" );
		}
		//Class test
		{
		IType classTestSetSubclass48;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSetSubclass48 = ModelTestUtils.getAssertClass( moduleChilds, "TestSetSubclass" );
			{
				IModelElement[] classTestSetSubclass48Childs = classTestSetSubclass48.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSetSubclass48Childs, "thetype");
			}
		}
		//Class test
		{
		IType classTestFrozenSet49;
			IModelElement[] moduleChilds = module.getChildren();
			classTestFrozenSet49 = ModelTestUtils.getAssertClass( moduleChilds, "TestFrozenSet" );
			{
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestFrozenSet49Childs, "thetype");
			}
			//Function test:test_init
			{
			IMethod methodtest_init50;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_init50 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init50, new String[] {"self"} );
			}
			//Function test:test_constructor_identity
			{
			IMethod methodtest_constructor_identity51;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_constructor_identity51 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_constructor_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_identity51, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash52;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_hash52 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash52, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy53;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_copy53 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy53, new String[] {"self"} );
			}
			//Function test:test_frozen_as_dictkey
			{
			IMethod methodtest_frozen_as_dictkey54;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_frozen_as_dictkey54 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_frozen_as_dictkey", 1 );
				ModelTestUtils.assertParameterNames( methodtest_frozen_as_dictkey54, new String[] {"self"} );
			}
			//Function test:test_hash_caching
			{
			IMethod methodtest_hash_caching55;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_hash_caching55 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_hash_caching", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_caching55, new String[] {"self"} );
			}
			//Function test:test_hash_effectiveness
			{
			IMethod methodtest_hash_effectiveness56;
				IModelElement[] classTestFrozenSet49Childs = classTestFrozenSet49.getChildren();
				methodtest_hash_effectiveness56 = ModelTestUtils.getAssertMethod( classTestFrozenSet49Childs, "test_hash_effectiveness", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_effectiveness56, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFrozenSetSubclass57;
			IModelElement[] moduleChilds = module.getChildren();
			classFrozenSetSubclass57 = ModelTestUtils.getAssertClass( moduleChilds, "FrozenSetSubclass" );
		}
		//Class test
		{
		IType classTestFrozenSetSubclass58;
			IModelElement[] moduleChilds = module.getChildren();
			classTestFrozenSetSubclass58 = ModelTestUtils.getAssertClass( moduleChilds, "TestFrozenSetSubclass" );
			{
				IModelElement[] classTestFrozenSetSubclass58Childs = classTestFrozenSetSubclass58.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestFrozenSetSubclass58Childs, "thetype");
			}
			//Function test:test_constructor_identity
			{
			IMethod methodtest_constructor_identity59;
				IModelElement[] classTestFrozenSetSubclass58Childs = classTestFrozenSetSubclass58.getChildren();
				methodtest_constructor_identity59 = ModelTestUtils.getAssertMethod( classTestFrozenSetSubclass58Childs, "test_constructor_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_identity59, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy60;
				IModelElement[] classTestFrozenSetSubclass58Childs = classTestFrozenSetSubclass58.getChildren();
				methodtest_copy60 = ModelTestUtils.getAssertMethod( classTestFrozenSetSubclass58Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy60, new String[] {"self"} );
			}
			//Function test:test_nested_empty_constructor
			{
			IMethod methodtest_nested_empty_constructor61;
				IModelElement[] classTestFrozenSetSubclass58Childs = classTestFrozenSetSubclass58.getChildren();
				methodtest_nested_empty_constructor61 = ModelTestUtils.getAssertMethod( classTestFrozenSetSubclass58Childs, "test_nested_empty_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_empty_constructor61, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "empty_set");
		}
		//Class test
		{
		IType classTestBasicOps62;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOps62 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOps" );
			//Function test:test_repr
			{
			IMethod methodtest_repr63;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_repr63 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr63, new String[] {"self"} );
			}
			//Function test:test_print
			{
			IMethod methodtest_print64;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_print64 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_print", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print64, new String[] {"self"} );
			}
			//Function test:test_length
			{
			IMethod methodtest_length65;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_length65 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_length", 1 );
				ModelTestUtils.assertParameterNames( methodtest_length65, new String[] {"self"} );
			}
			//Function test:test_self_equality
			{
			IMethod methodtest_self_equality66;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_self_equality66 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_self_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_equality66, new String[] {"self"} );
			}
			//Function test:test_equivalent_equality
			{
			IMethod methodtest_equivalent_equality67;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_equivalent_equality67 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_equivalent_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equivalent_equality67, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy68;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_copy68 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy68, new String[] {"self"} );
			}
			//Function test:test_self_union
			{
			IMethod methodtest_self_union69;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_self_union69 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_self_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_union69, new String[] {"self"} );
			}
			//Function test:test_empty_union
			{
			IMethod methodtest_empty_union70;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_empty_union70 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_empty_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_union70, new String[] {"self"} );
			}
			//Function test:test_union_empty
			{
			IMethod methodtest_union_empty71;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_union_empty71 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_union_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_empty71, new String[] {"self"} );
			}
			//Function test:test_self_intersection
			{
			IMethod methodtest_self_intersection72;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_self_intersection72 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_self_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_intersection72, new String[] {"self"} );
			}
			//Function test:test_empty_intersection
			{
			IMethod methodtest_empty_intersection73;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_empty_intersection73 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_empty_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_intersection73, new String[] {"self"} );
			}
			//Function test:test_intersection_empty
			{
			IMethod methodtest_intersection_empty74;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_intersection_empty74 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_intersection_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_empty74, new String[] {"self"} );
			}
			//Function test:test_self_symmetric_difference
			{
			IMethod methodtest_self_symmetric_difference75;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_self_symmetric_difference75 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_self_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_symmetric_difference75, new String[] {"self"} );
			}
			//Function test:checkempty_symmetric_difference
			{
			IMethod methodcheckempty_symmetric_difference76;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodcheckempty_symmetric_difference76 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "checkempty_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodcheckempty_symmetric_difference76, new String[] {"self"} );
			}
			//Function test:test_self_difference
			{
			IMethod methodtest_self_difference77;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_self_difference77 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_self_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_difference77, new String[] {"self"} );
			}
			//Function test:test_empty_difference
			{
			IMethod methodtest_empty_difference78;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_empty_difference78 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_empty_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_difference78, new String[] {"self"} );
			}
			//Function test:test_empty_difference_rev
			{
			IMethod methodtest_empty_difference_rev79;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_empty_difference_rev79 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_empty_difference_rev", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_difference_rev79, new String[] {"self"} );
			}
			//Function test:test_iteration
			{
			IMethod methodtest_iteration80;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_iteration80 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_iteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteration80, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling81;
				IModelElement[] classTestBasicOps62Childs = classTestBasicOps62.getChildren();
				methodtest_pickling81 = ModelTestUtils.getAssertMethod( classTestBasicOps62Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling81, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsEmpty82;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsEmpty82 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp83;
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				methodsetUp83 = ModelTestUtils.getAssertMethod( classTestBasicOpsEmpty82Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp83, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty82Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty82Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty82Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty82Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty82Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsEmpty82Childs = classTestBasicOpsEmpty82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty82Childs, "repr");
			}
		}
		//Class test
		{
		IType classTestBasicOpsSingleton85;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsSingleton85 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp86;
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				methodsetUp86 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton85Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp86, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton85Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton85Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton85Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton85Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton85Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton85Childs, "repr");
			}
			//Function test:test_in
			{
			IMethod methodtest_in88;
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				methodtest_in88 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton85Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in88, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in89;
				IModelElement[] classTestBasicOpsSingleton85Childs = classTestBasicOpsSingleton85.getChildren();
				methodtest_not_in89 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton85Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in89, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTuple90;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTuple90 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp91;
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				methodsetUp91 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple90Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp91, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple90Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple90Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple90Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple90Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple90Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple90Childs, "repr");
			}
			//Function test:test_in
			{
			IMethod methodtest_in93;
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				methodtest_in93 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple90Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in93, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in94;
				IModelElement[] classTestBasicOpsTuple90Childs = classTestBasicOpsTuple90.getChildren();
				methodtest_not_in94 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple90Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in94, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTriple95;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTriple95 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp96;
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				methodsetUp96 = ModelTestUtils.getAssertMethod( classTestBasicOpsTriple95Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp96, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple95Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple95Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple95Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple95Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple95Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsTriple95Childs = classTestBasicOpsTriple95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple95Childs, "repr");
			}
		}
		//Function test:baditer
		{
		IMethod methodbaditer98;
			IModelElement[] moduleChilds = module.getChildren();
			methodbaditer98 = ModelTestUtils.getAssertMethod( moduleChilds, "baditer", 0 );
		}
		//Function test:gooditer
		{
		IMethod methodgooditer99;
			IModelElement[] moduleChilds = module.getChildren();
			methodgooditer99 = ModelTestUtils.getAssertMethod( moduleChilds, "gooditer", 0 );
		}
		//Class test
		{
		IType classTestExceptionPropagation100;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExceptionPropagation100 = ModelTestUtils.getAssertClass( moduleChilds, "TestExceptionPropagation" );
			//Function test:test_instanceWithException
			{
			IMethod methodtest_instanceWithException101;
				IModelElement[] classTestExceptionPropagation100Childs = classTestExceptionPropagation100.getChildren();
				methodtest_instanceWithException101 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation100Childs, "test_instanceWithException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instanceWithException101, new String[] {"self"} );
			}
			//Function test:test_instancesWithoutException
			{
			IMethod methodtest_instancesWithoutException102;
				IModelElement[] classTestExceptionPropagation100Childs = classTestExceptionPropagation100.getChildren();
				methodtest_instancesWithoutException102 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation100Childs, "test_instancesWithoutException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instancesWithoutException102, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSetOfSets103;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSetOfSets103 = ModelTestUtils.getAssertClass( moduleChilds, "TestSetOfSets" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor104;
				IModelElement[] classTestSetOfSets103Childs = classTestSetOfSets103.getChildren();
				methodtest_constructor104 = ModelTestUtils.getAssertMethod( classTestSetOfSets103Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor104, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBinaryOps105;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryOps105 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryOps" );
			//Function test:setUp
			{
			IMethod methodsetUp106;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodsetUp106 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp106, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryOps105Childs, "set");
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq108;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_eq108 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq108, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset109;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_union_subset109 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset109, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset110;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_union_superset110 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset110, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap111;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_union_overlap111 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap111, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap112;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_union_non_overlap112 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap112, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset113;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_intersection_subset113 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset113, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset114;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_intersection_superset114 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset114, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap115;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_intersection_overlap115 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap115, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap116;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_intersection_non_overlap116 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap116, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset117;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_sym_difference_subset117 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset117, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset118;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_sym_difference_superset118 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset118, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap119;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_sym_difference_overlap119 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap119, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap120;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_sym_difference_non_overlap120 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap120, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp121;
				IModelElement[] classTestBinaryOps105Childs = classTestBinaryOps105.getChildren();
				methodtest_cmp121 = ModelTestUtils.getAssertMethod( classTestBinaryOps105Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp121, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestUpdateOps122;
			IModelElement[] moduleChilds = module.getChildren();
			classTestUpdateOps122 = ModelTestUtils.getAssertClass( moduleChilds, "TestUpdateOps" );
			//Function test:setUp
			{
			IMethod methodsetUp123;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodsetUp123 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp123, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset124;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_union_subset124 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset124, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset125;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_union_superset125 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset125, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap126;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_union_overlap126 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap126, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap127;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_union_non_overlap127 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap127, new String[] {"self"} );
			}
			//Function test:test_union_method_call
			{
			IMethod methodtest_union_method_call128;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_union_method_call128 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_union_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_method_call128, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset129;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_intersection_subset129 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset129, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset130;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_intersection_superset130 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset130, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap131;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_intersection_overlap131 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap131, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap132;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_intersection_non_overlap132 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap132, new String[] {"self"} );
			}
			//Function test:test_intersection_method_call
			{
			IMethod methodtest_intersection_method_call133;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_intersection_method_call133 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_intersection_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_method_call133, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset134;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_sym_difference_subset134 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset134, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset135;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_sym_difference_superset135 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset135, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap136;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_sym_difference_overlap136 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap136, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap137;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_sym_difference_non_overlap137 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap137, new String[] {"self"} );
			}
			//Function test:test_sym_difference_method_call
			{
			IMethod methodtest_sym_difference_method_call138;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_sym_difference_method_call138 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_sym_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_method_call138, new String[] {"self"} );
			}
			//Function test:test_difference_subset
			{
			IMethod methodtest_difference_subset139;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_difference_subset139 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_subset139, new String[] {"self"} );
			}
			//Function test:test_difference_superset
			{
			IMethod methodtest_difference_superset140;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_difference_superset140 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_superset140, new String[] {"self"} );
			}
			//Function test:test_difference_overlap
			{
			IMethod methodtest_difference_overlap141;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_difference_overlap141 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_overlap141, new String[] {"self"} );
			}
			//Function test:test_difference_non_overlap
			{
			IMethod methodtest_difference_non_overlap142;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_difference_non_overlap142 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_non_overlap142, new String[] {"self"} );
			}
			//Function test:test_difference_method_call
			{
			IMethod methodtest_difference_method_call143;
				IModelElement[] classTestUpdateOps122Childs = classTestUpdateOps122.getChildren();
				methodtest_difference_method_call143 = ModelTestUtils.getAssertMethod( classTestUpdateOps122Childs, "test_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_method_call143, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMutate144;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMutate144 = ModelTestUtils.getAssertClass( moduleChilds, "TestMutate" );
			//Function test:setUp
			{
			IMethod methodsetUp145;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodsetUp145 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp145, new String[] {"self"} );
			}
			{
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMutate144Childs, "values");
			}
			{
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMutate144Childs, "set");
			}
			//Function test:test_add_present
			{
			IMethod methodtest_add_present147;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_add_present147 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_add_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_present147, new String[] {"self"} );
			}
			//Function test:test_add_absent
			{
			IMethod methodtest_add_absent148;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_add_absent148 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_add_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_absent148, new String[] {"self"} );
			}
			//Function test:test_add_until_full
			{
			IMethod methodtest_add_until_full149;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_add_until_full149 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_add_until_full", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_until_full149, new String[] {"self"} );
			}
			//Function test:test_remove_present
			{
			IMethod methodtest_remove_present150;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_remove_present150 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_remove_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_present150, new String[] {"self"} );
			}
			//Function test:test_remove_absent
			{
			IMethod methodtest_remove_absent151;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_remove_absent151 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_remove_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_absent151, new String[] {"self"} );
			}
			//Function test:test_remove_until_empty
			{
			IMethod methodtest_remove_until_empty152;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_remove_until_empty152 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_remove_until_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_until_empty152, new String[] {"self"} );
			}
			//Function test:test_discard_present
			{
			IMethod methodtest_discard_present153;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_discard_present153 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_discard_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_present153, new String[] {"self"} );
			}
			//Function test:test_discard_absent
			{
			IMethod methodtest_discard_absent154;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_discard_absent154 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_discard_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_absent154, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear155;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_clear155 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear155, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop156;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_pop156 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop156, new String[] {"self"} );
			}
			//Function test:test_update_empty_tuple
			{
			IMethod methodtest_update_empty_tuple157;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_update_empty_tuple157 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_update_empty_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_empty_tuple157, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_overlap
			{
			IMethod methodtest_update_unit_tuple_overlap158;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_update_unit_tuple_overlap158 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_update_unit_tuple_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_overlap158, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_non_overlap
			{
			IMethod methodtest_update_unit_tuple_non_overlap159;
				IModelElement[] classTestMutate144Childs = classTestMutate144.getChildren();
				methodtest_update_unit_tuple_non_overlap159 = ModelTestUtils.getAssertMethod( classTestMutate144Childs, "test_update_unit_tuple_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_non_overlap159, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsets160;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsets160 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsets" );
			{
				IModelElement[] classTestSubsets160Childs = classTestSubsets160.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets160Childs, "case2method");
			}
			{
				IModelElement[] classTestSubsets160Childs = classTestSubsets160.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets160Childs, "reverse");
			}
			//Function test:test_issubset
			{
			IMethod methodtest_issubset161;
				IModelElement[] classTestSubsets160Childs = classTestSubsets160.getChildren();
				methodtest_issubset161 = ModelTestUtils.getAssertMethod( classTestSubsets160Childs, "test_issubset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubset161, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsetEqualEmpty162;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualEmpty162 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualEmpty" );
			{
				IModelElement[] classTestSubsetEqualEmpty162Childs = classTestSubsetEqualEmpty162.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty162Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty162Childs = classTestSubsetEqualEmpty162.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty162Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty162Childs = classTestSubsetEqualEmpty162.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty162Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty162Childs = classTestSubsetEqualEmpty162.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty162Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEqualNonEmpty163;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualNonEmpty163 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualNonEmpty" );
			{
				IModelElement[] classTestSubsetEqualNonEmpty163Childs = classTestSubsetEqualNonEmpty163.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty163Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty163Childs = classTestSubsetEqualNonEmpty163.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty163Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty163Childs = classTestSubsetEqualNonEmpty163.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty163Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty163Childs = classTestSubsetEqualNonEmpty163.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty163Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEmptyNonEmpty164;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEmptyNonEmpty164 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEmptyNonEmpty" );
			{
				IModelElement[] classTestSubsetEmptyNonEmpty164Childs = classTestSubsetEmptyNonEmpty164.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty164Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty164Childs = classTestSubsetEmptyNonEmpty164.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty164Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty164Childs = classTestSubsetEmptyNonEmpty164.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty164Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty164Childs = classTestSubsetEmptyNonEmpty164.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty164Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetPartial165;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetPartial165 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetPartial" );
			{
				IModelElement[] classTestSubsetPartial165Childs = classTestSubsetPartial165.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial165Childs, "left");
			}
			{
				IModelElement[] classTestSubsetPartial165Childs = classTestSubsetPartial165.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial165Childs, "right");
			}
			{
				IModelElement[] classTestSubsetPartial165Childs = classTestSubsetPartial165.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial165Childs, "name");
			}
			{
				IModelElement[] classTestSubsetPartial165Childs = classTestSubsetPartial165.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial165Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetNonOverlap166;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetNonOverlap166 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetNonOverlap" );
			{
				IModelElement[] classTestSubsetNonOverlap166Childs = classTestSubsetNonOverlap166.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap166Childs, "left");
			}
			{
				IModelElement[] classTestSubsetNonOverlap166Childs = classTestSubsetNonOverlap166.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap166Childs, "right");
			}
			{
				IModelElement[] classTestSubsetNonOverlap166Childs = classTestSubsetNonOverlap166.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap166Childs, "name");
			}
			{
				IModelElement[] classTestSubsetNonOverlap166Childs = classTestSubsetNonOverlap166.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap166Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestOnlySetsInBinaryOps167;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsInBinaryOps167 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsInBinaryOps" );
			//Function test:test_eq_ne
			{
			IMethod methodtest_eq_ne168;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_eq_ne168 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_eq_ne", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq_ne168, new String[] {"self"} );
			}
			//Function test:test_ge_gt_le_lt
			{
			IMethod methodtest_ge_gt_le_lt169;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_ge_gt_le_lt169 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_ge_gt_le_lt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ge_gt_le_lt169, new String[] {"self"} );
			}
			//Function test:test_update_operator
			{
			IMethod methodtest_update_operator170;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_update_operator170 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_operator170, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update171;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_update171 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update171, new String[] {"self"} );
			}
			//Function test:test_union
			{
			IMethod methodtest_union172;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_union172 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union172, new String[] {"self"} );
			}
			//Function test:test_intersection_update_operator
			{
			IMethod methodtest_intersection_update_operator173;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_intersection_update_operator173 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_intersection_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update_operator173, new String[] {"self"} );
			}
			//Function test:test_intersection_update
			{
			IMethod methodtest_intersection_update174;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_intersection_update174 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_intersection_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update174, new String[] {"self"} );
			}
			//Function test:test_intersection
			{
			IMethod methodtest_intersection175;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_intersection175 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection175, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update_operator
			{
			IMethod methodtest_sym_difference_update_operator176;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_sym_difference_update_operator176 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_sym_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update_operator176, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update
			{
			IMethod methodtest_sym_difference_update177;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_sym_difference_update177 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_sym_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update177, new String[] {"self"} );
			}
			//Function test:test_sym_difference
			{
			IMethod methodtest_sym_difference178;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_sym_difference178 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_sym_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference178, new String[] {"self"} );
			}
			//Function test:test_difference_update_operator
			{
			IMethod methodtest_difference_update_operator179;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_difference_update_operator179 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update_operator179, new String[] {"self"} );
			}
			//Function test:test_difference_update
			{
			IMethod methodtest_difference_update180;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_difference_update180 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update180, new String[] {"self"} );
			}
			//Function test:test_difference
			{
			IMethod methodtest_difference181;
				IModelElement[] classTestOnlySetsInBinaryOps167Childs = classTestOnlySetsInBinaryOps167.getChildren();
				methodtest_difference181 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps167Childs, "test_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference181, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsNumeric182;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsNumeric182 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsNumeric" );
			//Function test:setUp
			{
			IMethod methodsetUp183;
				IModelElement[] classTestOnlySetsNumeric182Childs = classTestOnlySetsNumeric182.getChildren();
				methodsetUp183 = ModelTestUtils.getAssertMethod( classTestOnlySetsNumeric182Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp183, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsNumeric182Childs = classTestOnlySetsNumeric182.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsNumeric182Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsNumeric182Childs = classTestOnlySetsNumeric182.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsNumeric182Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsNumeric182Childs = classTestOnlySetsNumeric182.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsNumeric182Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsDict185;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsDict185 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsDict" );
			//Function test:setUp
			{
			IMethod methodsetUp186;
				IModelElement[] classTestOnlySetsDict185Childs = classTestOnlySetsDict185.getChildren();
				methodsetUp186 = ModelTestUtils.getAssertMethod( classTestOnlySetsDict185Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp186, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsDict185Childs = classTestOnlySetsDict185.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsDict185Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsDict185Childs = classTestOnlySetsDict185.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsDict185Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsDict185Childs = classTestOnlySetsDict185.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsDict185Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsOperator188;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsOperator188 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsOperator" );
			//Function test:setUp
			{
			IMethod methodsetUp189;
				IModelElement[] classTestOnlySetsOperator188Childs = classTestOnlySetsOperator188.getChildren();
				methodsetUp189 = ModelTestUtils.getAssertMethod( classTestOnlySetsOperator188Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp189, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsOperator188Childs = classTestOnlySetsOperator188.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsOperator188Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsOperator188Childs = classTestOnlySetsOperator188.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsOperator188Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsOperator188Childs = classTestOnlySetsOperator188.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsOperator188Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsTuple191;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsTuple191 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp192;
				IModelElement[] classTestOnlySetsTuple191Childs = classTestOnlySetsTuple191.getChildren();
				methodsetUp192 = ModelTestUtils.getAssertMethod( classTestOnlySetsTuple191Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp192, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsTuple191Childs = classTestOnlySetsTuple191.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsTuple191Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsTuple191Childs = classTestOnlySetsTuple191.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsTuple191Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsTuple191Childs = classTestOnlySetsTuple191.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsTuple191Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsString194;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsString194 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsString" );
			//Function test:setUp
			{
			IMethod methodsetUp195;
				IModelElement[] classTestOnlySetsString194Childs = classTestOnlySetsString194.getChildren();
				methodsetUp195 = ModelTestUtils.getAssertMethod( classTestOnlySetsString194Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp195, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsString194Childs = classTestOnlySetsString194.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsString194Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsString194Childs = classTestOnlySetsString194.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsString194Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsString194Childs = classTestOnlySetsString194.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsString194Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsGenerator197;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsGenerator197 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsGenerator" );
			//Function test:setUp
			{
			IMethod methodsetUp198;
				IModelElement[] classTestOnlySetsGenerator197Childs = classTestOnlySetsGenerator197.getChildren();
				methodsetUp198 = ModelTestUtils.getAssertMethod( classTestOnlySetsGenerator197Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp198, new String[] {"self"} );
				//Function test:gen
				{
				IMethod methodgen199;
					IModelElement[] methodsetUp198Childs = methodsetUp198.getChildren();
					methodgen199 = ModelTestUtils.getAssertMethod( methodsetUp198Childs, "gen", 0 );
				}
			}
			{
				IModelElement[] classTestOnlySetsGenerator197Childs = classTestOnlySetsGenerator197.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsGenerator197Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsGenerator197Childs = classTestOnlySetsGenerator197.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsGenerator197Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsGenerator197Childs = classTestOnlySetsGenerator197.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsGenerator197Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestCopying201;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopying201 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopying" );
			//Function test:test_copy
			{
			IMethod methodtest_copy202;
				IModelElement[] classTestCopying201Childs = classTestCopying201.getChildren();
				methodtest_copy202 = ModelTestUtils.getAssertMethod( classTestCopying201Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy202, new String[] {"self"} );
			}
			//Function test:test_deep_copy
			{
			IMethod methodtest_deep_copy203;
				IModelElement[] classTestCopying201Childs = classTestCopying201.getChildren();
				methodtest_deep_copy203 = ModelTestUtils.getAssertMethod( classTestCopying201Childs, "test_deep_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deep_copy203, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingEmpty204;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingEmpty204 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp205;
				IModelElement[] classTestCopyingEmpty204Childs = classTestCopyingEmpty204.getChildren();
				methodsetUp205 = ModelTestUtils.getAssertMethod( classTestCopyingEmpty204Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp205, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingEmpty204Childs = classTestCopyingEmpty204.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingEmpty204Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingSingleton207;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingSingleton207 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp208;
				IModelElement[] classTestCopyingSingleton207Childs = classTestCopyingSingleton207.getChildren();
				methodsetUp208 = ModelTestUtils.getAssertMethod( classTestCopyingSingleton207Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp208, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingSingleton207Childs = classTestCopyingSingleton207.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingSingleton207Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingTriple210;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTriple210 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp211;
				IModelElement[] classTestCopyingTriple210Childs = classTestCopyingTriple210.getChildren();
				methodsetUp211 = ModelTestUtils.getAssertMethod( classTestCopyingTriple210Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp211, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingTriple210Childs = classTestCopyingTriple210.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingTriple210Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingTuple213;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTuple213 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp214;
				IModelElement[] classTestCopyingTuple213Childs = classTestCopyingTuple213.getChildren();
				methodsetUp214 = ModelTestUtils.getAssertMethod( classTestCopyingTuple213Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp214, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingTuple213Childs = classTestCopyingTuple213.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingTuple213Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingNested216;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingNested216 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingNested" );
			//Function test:setUp
			{
			IMethod methodsetUp217;
				IModelElement[] classTestCopyingNested216Childs = classTestCopyingNested216.getChildren();
				methodsetUp217 = ModelTestUtils.getAssertMethod( classTestCopyingNested216Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp217, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingNested216Childs = classTestCopyingNested216.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingNested216Childs, "set");
			}
		}
		//Class test
		{
		IType classTestIdentities219;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIdentities219 = ModelTestUtils.getAssertClass( moduleChilds, "TestIdentities" );
			//Function test:setUp
			{
			IMethod methodsetUp220;
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				methodsetUp220 = ModelTestUtils.getAssertMethod( classTestIdentities219Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp220, new String[] {"self"} );
			}
			{
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestIdentities219Childs, "a");
			}
			{
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestIdentities219Childs, "b");
			}
			//Function test:test_binopsVsSubsets
			{
			IMethod methodtest_binopsVsSubsets222;
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				methodtest_binopsVsSubsets222 = ModelTestUtils.getAssertMethod( classTestIdentities219Childs, "test_binopsVsSubsets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binopsVsSubsets222, new String[] {"self"} );
			}
			//Function test:test_commutativity
			{
			IMethod methodtest_commutativity223;
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				methodtest_commutativity223 = ModelTestUtils.getAssertMethod( classTestIdentities219Childs, "test_commutativity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commutativity223, new String[] {"self"} );
			}
			//Function test:test_summations
			{
			IMethod methodtest_summations224;
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				methodtest_summations224 = ModelTestUtils.getAssertMethod( classTestIdentities219Childs, "test_summations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_summations224, new String[] {"self"} );
			}
			//Function test:test_exclusion
			{
			IMethod methodtest_exclusion225;
				IModelElement[] classTestIdentities219Childs = classTestIdentities219.getChildren();
				methodtest_exclusion225 = ModelTestUtils.getAssertMethod( classTestIdentities219Childs, "test_exclusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exclusion225, new String[] {"self"} );
			}
		}
		//Function test:R
		{
		IMethod methodR226;
			IModelElement[] moduleChilds = module.getChildren();
			methodR226 = ModelTestUtils.getAssertMethod( moduleChilds, "R", 1 );
			ModelTestUtils.assertParameterNames( methodR226, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classG227;
			IModelElement[] moduleChilds = module.getChildren();
			classG227 = ModelTestUtils.getAssertClass( moduleChilds, "G" );
			//Function test:__init__
			{
			IMethod method__init__228;
				IModelElement[] classG227Childs = classG227.getChildren();
				method__init__228 = ModelTestUtils.getAssertMethod( classG227Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__228, new String[] {"self", "seqn"} );
			}
			{
				IModelElement[] classG227Childs = classG227.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classG227Childs, "seqn");
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__230;
				IModelElement[] classG227Childs = classG227.getChildren();
				method__getitem__230 = ModelTestUtils.getAssertMethod( classG227Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__230, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI231;
			IModelElement[] moduleChilds = module.getChildren();
			classI231 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__232;
				IModelElement[] classI231Childs = classI231.getChildren();
				method__init__232 = ModelTestUtils.getAssertMethod( classI231Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__232, new String[] {"self", "seqn"} );
			}
			{
				IModelElement[] classI231Childs = classI231.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI231Childs, "seqn");
			}
			{
				IModelElement[] classI231Childs = classI231.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI231Childs, "i");
			}
			//Function test:__iter__
			{
			IMethod method__iter__234;
				IModelElement[] classI231Childs = classI231.getChildren();
				method__iter__234 = ModelTestUtils.getAssertMethod( classI231Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__234, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext235;
				IModelElement[] classI231Childs = classI231.getChildren();
				methodnext235 = ModelTestUtils.getAssertMethod( classI231Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext235, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg236;
			IModelElement[] moduleChilds = module.getChildren();
			classIg236 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__237;
				IModelElement[] classIg236Childs = classIg236.getChildren();
				method__init__237 = ModelTestUtils.getAssertMethod( classIg236Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__237, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__238;
				IModelElement[] classIg236Childs = classIg236.getChildren();
				method__iter__238 = ModelTestUtils.getAssertMethod( classIg236Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__238, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX239;
			IModelElement[] moduleChilds = module.getChildren();
			classX239 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__240;
				IModelElement[] classX239Childs = classX239.getChildren();
				method__init__240 = ModelTestUtils.getAssertMethod( classX239Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__240, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext241;
				IModelElement[] classX239Childs = classX239.getChildren();
				methodnext241 = ModelTestUtils.getAssertMethod( classX239Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext241, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN242;
			IModelElement[] moduleChilds = module.getChildren();
			classN242 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__243;
				IModelElement[] classN242Childs = classN242.getChildren();
				method__init__243 = ModelTestUtils.getAssertMethod( classN242Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__243, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__244;
				IModelElement[] classN242Childs = classN242.getChildren();
				method__iter__244 = ModelTestUtils.getAssertMethod( classN242Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__244, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE245;
			IModelElement[] moduleChilds = module.getChildren();
			classE245 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__246;
				IModelElement[] classE245Childs = classE245.getChildren();
				method__init__246 = ModelTestUtils.getAssertMethod( classE245Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__246, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__247;
				IModelElement[] classE245Childs = classE245.getChildren();
				method__iter__247 = ModelTestUtils.getAssertMethod( classE245Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__247, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext248;
				IModelElement[] classE245Childs = classE245.getChildren();
				methodnext248 = ModelTestUtils.getAssertMethod( classE245Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext248, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS249;
			IModelElement[] moduleChilds = module.getChildren();
			classS249 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__250;
				IModelElement[] classS249Childs = classS249.getChildren();
				method__init__250 = ModelTestUtils.getAssertMethod( classS249Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__250, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__251;
				IModelElement[] classS249Childs = classS249.getChildren();
				method__iter__251 = ModelTestUtils.getAssertMethod( classS249Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__251, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext252;
				IModelElement[] classS249Childs = classS249.getChildren();
				methodnext252 = ModelTestUtils.getAssertMethod( classS249Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext252, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL253;
			IModelElement[] moduleChilds = module.getChildren();
			methodL253 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL253, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestVariousIteratorArgs254;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVariousIteratorArgs254 = ModelTestUtils.getAssertClass( moduleChilds, "TestVariousIteratorArgs" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor255;
				IModelElement[] classTestVariousIteratorArgs254Childs = classTestVariousIteratorArgs254.getChildren();
				methodtest_constructor255 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs254Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor255, new String[] {"self"} );
			}
			//Function test:test_inline_methods
			{
			IMethod methodtest_inline_methods256;
				IModelElement[] classTestVariousIteratorArgs254Childs = classTestVariousIteratorArgs254.getChildren();
				methodtest_inline_methods256 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs254Childs, "test_inline_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inline_methods256, new String[] {"self"} );
			}
			//Function test:test_inplace_methods
			{
			IMethod methodtest_inplace_methods257;
				IModelElement[] classTestVariousIteratorArgs254Childs = classTestVariousIteratorArgs254.getChildren();
				methodtest_inplace_methods257 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs254Childs, "test_inplace_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inplace_methods257, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main258;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main258 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main258, new String[] {"verbose"} );
		}

	}
	public void testModelGen216( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sha.py"));

		assertNotNull("Module test_sha.py not found", module);
		assertEquals("test_sha.py", module.getElementName());
		
		//Class test
		{
		IType classSHATestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classSHATestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "SHATestCase" );
			//Function test:check
			{
			IMethod methodcheck1;
				IModelElement[] classSHATestCase0Childs = classSHATestCase0.getChildren();
				methodcheck1 = ModelTestUtils.getAssertMethod( classSHATestCase0Childs, "check", 3 );
				ModelTestUtils.assertParameterNames( methodcheck1, new String[] {"self", "data", "digest"} );
			}
			//Function test:test_case_1
			{
			IMethod methodtest_case_12;
				IModelElement[] classSHATestCase0Childs = classSHATestCase0.getChildren();
				methodtest_case_12 = ModelTestUtils.getAssertMethod( classSHATestCase0Childs, "test_case_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_12, new String[] {"self"} );
			}
			//Function test:test_case_2
			{
			IMethod methodtest_case_23;
				IModelElement[] classSHATestCase0Childs = classSHATestCase0.getChildren();
				methodtest_case_23 = ModelTestUtils.getAssertMethod( classSHATestCase0Childs, "test_case_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_23, new String[] {"self"} );
			}
			//Function test:test_case_3
			{
			IMethod methodtest_case_34;
				IModelElement[] classSHATestCase0Childs = classSHATestCase0.getChildren();
				methodtest_case_34 = ModelTestUtils.getAssertMethod( classSHATestCase0Childs, "test_case_3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_34, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen217( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future3.py"));

		assertNotNull("Module badsyntax_future3.py not found", module);
		assertEquals("badsyntax_future3.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen218( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future4.py"));

		assertNotNull("Module badsyntax_future4.py not found", module);
		assertEquals("badsyntax_future4.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen219( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future5.py"));

		assertNotNull("Module badsyntax_future5.py not found", module);
		assertEquals("badsyntax_future5.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen220( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future6.py"));

		assertNotNull("Module badsyntax_future6.py not found", module);
		assertEquals("badsyntax_future6.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen221( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future7.py"));

		assertNotNull("Module badsyntax_future7.py not found", module);
		assertEquals("badsyntax_future7.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen222( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future8.py"));

		assertNotNull("Module badsyntax_future8.py not found", module);
		assertEquals("badsyntax_future8.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}

	}
	public void testModelGen223( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("badsyntax_future9.py"));

		assertNotNull("Module badsyntax_future9.py not found", module);
		assertEquals("badsyntax_future9.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}

	}
	public void testModelGen224( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_tcl.py"));

		assertNotNull("Module test_tcl.py not found", module);
		assertEquals("test_tcl.py", module.getElementName());
		
		//Class test
		{
		IType classTclTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classTclTest0 = ModelTestUtils.getAssertClass( moduleChilds, "TclTest" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			{
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTclTest0Childs, "interp");
			}
			//Function test:testEval
			{
			IMethod methodtestEval3;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEval3 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEval", 1 );
				ModelTestUtils.assertParameterNames( methodtestEval3, new String[] {"self"} );
			}
			//Function test:testEvalException
			{
			IMethod methodtestEvalException4;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalException4 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalException", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalException4, new String[] {"self"} );
			}
			//Function test:testEvalException2
			{
			IMethod methodtestEvalException25;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalException25 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalException2", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalException25, new String[] {"self"} );
			}
			//Function test:testCall
			{
			IMethod methodtestCall6;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestCall6 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testCall", 1 );
				ModelTestUtils.assertParameterNames( methodtestCall6, new String[] {"self"} );
			}
			//Function test:testCallException
			{
			IMethod methodtestCallException7;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestCallException7 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testCallException", 1 );
				ModelTestUtils.assertParameterNames( methodtestCallException7, new String[] {"self"} );
			}
			//Function test:testCallException2
			{
			IMethod methodtestCallException28;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestCallException28 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testCallException2", 1 );
				ModelTestUtils.assertParameterNames( methodtestCallException28, new String[] {"self"} );
			}
			//Function test:testSetVar
			{
			IMethod methodtestSetVar9;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestSetVar9 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testSetVar", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetVar9, new String[] {"self"} );
			}
			//Function test:testSetVarArray
			{
			IMethod methodtestSetVarArray10;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestSetVarArray10 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testSetVarArray", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetVarArray10, new String[] {"self"} );
			}
			//Function test:testGetVar
			{
			IMethod methodtestGetVar11;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVar11 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVar", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVar11, new String[] {"self"} );
			}
			//Function test:testGetVarArray
			{
			IMethod methodtestGetVarArray12;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVarArray12 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVarArray", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVarArray12, new String[] {"self"} );
			}
			//Function test:testGetVarException
			{
			IMethod methodtestGetVarException13;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVarException13 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVarException", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVarException13, new String[] {"self"} );
			}
			//Function test:testGetVarArrayException
			{
			IMethod methodtestGetVarArrayException14;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVarArrayException14 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVarArrayException", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVarArrayException14, new String[] {"self"} );
			}
			//Function test:testUnsetVar
			{
			IMethod methodtestUnsetVar15;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestUnsetVar15 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testUnsetVar", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnsetVar15, new String[] {"self"} );
			}
			//Function test:testUnsetVarArray
			{
			IMethod methodtestUnsetVarArray16;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestUnsetVarArray16 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testUnsetVarArray", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnsetVarArray16, new String[] {"self"} );
			}
			//Function test:testUnsetVarException
			{
			IMethod methodtestUnsetVarException17;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestUnsetVarException17 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testUnsetVarException", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnsetVarException17, new String[] {"self"} );
			}
			//Function test:testEvalFile
			{
			IMethod methodtestEvalFile18;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalFile18 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalFile", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalFile18, new String[] {"self"} );
			}
			//Function test:testEvalFileException
			{
			IMethod methodtestEvalFileException19;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalFileException19 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalFileException", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalFileException19, new String[] {"self"} );
			}
			//Function test:testPackageRequireException
			{
			IMethod methodtestPackageRequireException20;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestPackageRequireException20 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testPackageRequireException", 1 );
				ModelTestUtils.assertParameterNames( methodtestPackageRequireException20, new String[] {"self"} );
			}
			//Function test:testLoadTk
			{
			IMethod methodtestLoadTk21;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestLoadTk21 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testLoadTk", 1 );
				ModelTestUtils.assertParameterNames( methodtestLoadTk21, new String[] {"self"} );
			}
			//Function test:testLoadTkFailure
			{
			IMethod methodtestLoadTkFailure22;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestLoadTkFailure22 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testLoadTkFailure", 1 );
				ModelTestUtils.assertParameterNames( methodtestLoadTkFailure22, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen225( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecs.py"));

		assertNotNull("Module test_codecs.py not found", module);
		assertEquals("test_codecs.py", module.getElementName());
		
		//Class test
		{
		IType classQueue0;
			IModelElement[] moduleChilds = module.getChildren();
			classQueue0 = ModelTestUtils.getAssertClass( moduleChilds, "Queue" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classQueue0Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self"} );
			}
			{
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQueue0Childs, "_buffer");
			}
			//Function test:write
			{
			IMethod methodwrite3;
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				methodwrite3 = ModelTestUtils.getAssertMethod( classQueue0Childs, "write", 2 );
				ModelTestUtils.assertParameterNames( methodwrite3, new String[] {"self", "chars"} );
			}
			//Function test:read
			{
			IMethod methodread4;
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				methodread4 = ModelTestUtils.getAssertMethod( classQueue0Childs, "read", 2 );
				ModelTestUtils.assertParameterNames( methodread4, new String[] {"self", "size"} );
			}
			{
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQueue0Childs, "_buffer");
			}
		}
		//Class test
		{
		IType classReadTest6;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTest6 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTest" );
			//Function test:test_seek
			{
			IMethod methodtest_seek7;
				IModelElement[] classReadTest6Childs = classReadTest6.getChildren();
				methodtest_seek7 = ModelTestUtils.getAssertMethod( classReadTest6Childs, "test_seek", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seek7, new String[] {"self"} );
			}
			//Function test:check_partial
			{
			IMethod methodcheck_partial8;
				IModelElement[] classReadTest6Childs = classReadTest6.getChildren();
				methodcheck_partial8 = ModelTestUtils.getAssertMethod( classReadTest6Childs, "check_partial", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_partial8, new String[] {"self", "input", "partialresults"} );
			}
			//Function test:test_readline
			{
			IMethod methodtest_readline9;
				IModelElement[] classReadTest6Childs = classReadTest6.getChildren();
				methodtest_readline9 = ModelTestUtils.getAssertMethod( classReadTest6Childs, "test_readline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readline9, new String[] {"self"} );
				//Function test:getreader
				{
				IMethod methodgetreader10;
					IModelElement[] methodtest_readline9Childs = methodtest_readline9.getChildren();
					methodgetreader10 = ModelTestUtils.getAssertMethod( methodtest_readline9Childs, "getreader", 1 );
					ModelTestUtils.assertParameterNames( methodgetreader10, new String[] {"input"} );
				}
				//Function test:readalllines
				{
				IMethod methodreadalllines11;
					IModelElement[] methodtest_readline9Childs = methodtest_readline9.getChildren();
					methodreadalllines11 = ModelTestUtils.getAssertMethod( methodtest_readline9Childs, "readalllines", 2 );
					ModelTestUtils.assertParameterNames( methodreadalllines11, new String[] {"input", "keepends"} );
				}
			}
			//Function test:test_readlinequeue
			{
			IMethod methodtest_readlinequeue12;
				IModelElement[] classReadTest6Childs = classReadTest6.getChildren();
				methodtest_readlinequeue12 = ModelTestUtils.getAssertMethod( classReadTest6Childs, "test_readlinequeue", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlinequeue12, new String[] {"self"} );
			}
			//Function test:test_bug1098990_a
			{
			IMethod methodtest_bug1098990_a13;
				IModelElement[] classReadTest6Childs = classReadTest6.getChildren();
				methodtest_bug1098990_a13 = ModelTestUtils.getAssertMethod( classReadTest6Childs, "test_bug1098990_a", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug1098990_a13, new String[] {"self"} );
			}
			//Function test:test_bug1098990_b
			{
			IMethod methodtest_bug1098990_b14;
				IModelElement[] classReadTest6Childs = classReadTest6.getChildren();
				methodtest_bug1098990_b14 = ModelTestUtils.getAssertMethod( classReadTest6Childs, "test_bug1098990_b", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug1098990_b14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF16Test15;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF16Test15 = ModelTestUtils.getAssertClass( moduleChilds, "UTF16Test" );
			{
				IModelElement[] classUTF16Test15Childs = classUTF16Test15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16Test15Childs, "encoding");
			}
			{
				IModelElement[] classUTF16Test15Childs = classUTF16Test15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16Test15Childs, "spamle");
			}
			{
				IModelElement[] classUTF16Test15Childs = classUTF16Test15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16Test15Childs, "spambe");
			}
			//Function test:test_only_one_bom
			{
			IMethod methodtest_only_one_bom16;
				IModelElement[] classUTF16Test15Childs = classUTF16Test15.getChildren();
				methodtest_only_one_bom16 = ModelTestUtils.getAssertMethod( classUTF16Test15Childs, "test_only_one_bom", 1 );
				ModelTestUtils.assertParameterNames( methodtest_only_one_bom16, new String[] {"self"} );
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial17;
				IModelElement[] classUTF16Test15Childs = classUTF16Test15.getChildren();
				methodtest_partial17 = ModelTestUtils.getAssertMethod( classUTF16Test15Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF16LETest18;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF16LETest18 = ModelTestUtils.getAssertClass( moduleChilds, "UTF16LETest" );
			{
				IModelElement[] classUTF16LETest18Childs = classUTF16LETest18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16LETest18Childs, "encoding");
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial19;
				IModelElement[] classUTF16LETest18Childs = classUTF16LETest18.getChildren();
				methodtest_partial19 = ModelTestUtils.getAssertMethod( classUTF16LETest18Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF16BETest20;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF16BETest20 = ModelTestUtils.getAssertClass( moduleChilds, "UTF16BETest" );
			{
				IModelElement[] classUTF16BETest20Childs = classUTF16BETest20.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16BETest20Childs, "encoding");
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial21;
				IModelElement[] classUTF16BETest20Childs = classUTF16BETest20.getChildren();
				methodtest_partial21 = ModelTestUtils.getAssertMethod( classUTF16BETest20Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial21, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF8Test22;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF8Test22 = ModelTestUtils.getAssertClass( moduleChilds, "UTF8Test" );
			{
				IModelElement[] classUTF8Test22Childs = classUTF8Test22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF8Test22Childs, "encoding");
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial23;
				IModelElement[] classUTF8Test22Childs = classUTF8Test22.getChildren();
				methodtest_partial23 = ModelTestUtils.getAssertMethod( classUTF8Test22Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEscapeDecodeTest24;
			IModelElement[] moduleChilds = module.getChildren();
			classEscapeDecodeTest24 = ModelTestUtils.getAssertClass( moduleChilds, "EscapeDecodeTest" );
			//Function test:test_empty_escape_decode
			{
			IMethod methodtest_empty_escape_decode25;
				IModelElement[] classEscapeDecodeTest24Childs = classEscapeDecodeTest24.getChildren();
				methodtest_empty_escape_decode25 = ModelTestUtils.getAssertMethod( classEscapeDecodeTest24Childs, "test_empty_escape_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_escape_decode25, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRecodingTest26;
			IModelElement[] moduleChilds = module.getChildren();
			classRecodingTest26 = ModelTestUtils.getAssertClass( moduleChilds, "RecodingTest" );
			//Function test:test_recoding
			{
			IMethod methodtest_recoding27;
				IModelElement[] classRecodingTest26Childs = classRecodingTest26.getChildren();
				methodtest_recoding27 = ModelTestUtils.getAssertMethod( classRecodingTest26Childs, "test_recoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recoding27, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "punycode_testcases");
		}
		//Class test
		{
		IType classPunycodeTest28;
			IModelElement[] moduleChilds = module.getChildren();
			classPunycodeTest28 = ModelTestUtils.getAssertClass( moduleChilds, "PunycodeTest" );
			//Function test:test_encode
			{
			IMethod methodtest_encode29;
				IModelElement[] classPunycodeTest28Childs = classPunycodeTest28.getChildren();
				methodtest_encode29 = ModelTestUtils.getAssertMethod( classPunycodeTest28Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode29, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode30;
				IModelElement[] classPunycodeTest28Childs = classPunycodeTest28.getChildren();
				methodtest_decode30 = ModelTestUtils.getAssertMethod( classPunycodeTest28Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode30, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nameprep_tests");
		}
		//Class test
		{
		IType classNameprepTest31;
			IModelElement[] moduleChilds = module.getChildren();
			classNameprepTest31 = ModelTestUtils.getAssertClass( moduleChilds, "NameprepTest" );
			//Function test:test_nameprep
			{
			IMethod methodtest_nameprep32;
				IModelElement[] classNameprepTest31Childs = classNameprepTest31.getChildren();
				methodtest_nameprep32 = ModelTestUtils.getAssertMethod( classNameprepTest31Childs, "test_nameprep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nameprep32, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCodecTest33;
			IModelElement[] moduleChilds = module.getChildren();
			classCodecTest33 = ModelTestUtils.getAssertClass( moduleChilds, "CodecTest" );
			//Function test:test_builtin
			{
			IMethod methodtest_builtin34;
				IModelElement[] classCodecTest33Childs = classCodecTest33.getChildren();
				methodtest_builtin34 = ModelTestUtils.getAssertMethod( classCodecTest33Childs, "test_builtin", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin34, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCodecsModuleTest35;
			IModelElement[] moduleChilds = module.getChildren();
			classCodecsModuleTest35 = ModelTestUtils.getAssertClass( moduleChilds, "CodecsModuleTest" );
			//Function test:test_decode
			{
			IMethod methodtest_decode36;
				IModelElement[] classCodecsModuleTest35Childs = classCodecsModuleTest35.getChildren();
				methodtest_decode36 = ModelTestUtils.getAssertMethod( classCodecsModuleTest35Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode36, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode37;
				IModelElement[] classCodecsModuleTest35Childs = classCodecsModuleTest35.getChildren();
				methodtest_encode37 = ModelTestUtils.getAssertMethod( classCodecsModuleTest35Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode37, new String[] {"self"} );
			}
			//Function test:test_register
			{
			IMethod methodtest_register38;
				IModelElement[] classCodecsModuleTest35Childs = classCodecsModuleTest35.getChildren();
				methodtest_register38 = ModelTestUtils.getAssertMethod( classCodecsModuleTest35Childs, "test_register", 1 );
				ModelTestUtils.assertParameterNames( methodtest_register38, new String[] {"self"} );
			}
			//Function test:test_lookup
			{
			IMethod methodtest_lookup39;
				IModelElement[] classCodecsModuleTest35Childs = classCodecsModuleTest35.getChildren();
				methodtest_lookup39 = ModelTestUtils.getAssertMethod( classCodecsModuleTest35Childs, "test_lookup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lookup39, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStreamReaderTest40;
			IModelElement[] moduleChilds = module.getChildren();
			classStreamReaderTest40 = ModelTestUtils.getAssertClass( moduleChilds, "StreamReaderTest" );
			//Function test:setUp
			{
			IMethod methodsetUp41;
				IModelElement[] classStreamReaderTest40Childs = classStreamReaderTest40.getChildren();
				methodsetUp41 = ModelTestUtils.getAssertMethod( classStreamReaderTest40Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp41, new String[] {"self"} );
			}
			{
				IModelElement[] classStreamReaderTest40Childs = classStreamReaderTest40.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStreamReaderTest40Childs, "reader");
			}
			{
				IModelElement[] classStreamReaderTest40Childs = classStreamReaderTest40.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStreamReaderTest40Childs, "stream");
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines43;
				IModelElement[] classStreamReaderTest40Childs = classStreamReaderTest40.getChildren();
				methodtest_readlines43 = ModelTestUtils.getAssertMethod( classStreamReaderTest40Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines43, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main44;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main44 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen226( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_str.py"));

		assertNotNull("Module test_str.py not found", module);
		assertEquals("test_str.py", module.getElementName());
		
		//Class test
		{
		IType classStrTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classStrTest0 = ModelTestUtils.getAssertClass( moduleChilds, "StrTest" );
			{
				IModelElement[] classStrTest0Childs = classStrTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStrTest0Childs, "type2test");
			}
			//Function test:fixtype
			{
			IMethod methodfixtype1;
				IModelElement[] classStrTest0Childs = classStrTest0.getChildren();
				methodfixtype1 = ModelTestUtils.getAssertMethod( classStrTest0Childs, "fixtype", 2 );
				ModelTestUtils.assertParameterNames( methodfixtype1, new String[] {"self", "obj"} );
			}
			//Function test:test_formatting
			{
			IMethod methodtest_formatting2;
				IModelElement[] classStrTest0Childs = classStrTest0.getChildren();
				methodtest_formatting2 = ModelTestUtils.getAssertMethod( classStrTest0Childs, "test_formatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_formatting2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen227( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_deque.py"));

		assertNotNull("Module test_deque.py not found", module);
		assertEquals("test_deque.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "BIG");
		}
		//Function test:fail
		{
		IMethod methodfail0;
			IModelElement[] moduleChilds = module.getChildren();
			methodfail0 = ModelTestUtils.getAssertMethod( moduleChilds, "fail", 0 );
		}
		//Class test
		{
		IType classTestBasic1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasic1 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasic" );
			//Function test:test_basics
			{
			IMethod methodtest_basics2;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_basics2 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_basics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basics2, new String[] {"self"} );
			}
			//Function test:test_comparisons
			{
			IMethod methodtest_comparisons3;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_comparisons3 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_comparisons", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparisons3, new String[] {"self"} );
			}
			//Function test:test_extend
			{
			IMethod methodtest_extend4;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_extend4 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_extend", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend4, new String[] {"self"} );
			}
			//Function test:test_extendleft
			{
			IMethod methodtest_extendleft5;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_extendleft5 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_extendleft", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extendleft5, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem6;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_getitem6 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem6, new String[] {"self"} );
			}
			//Function test:test_setitem
			{
			IMethod methodtest_setitem7;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_setitem7 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_setitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setitem7, new String[] {"self"} );
			}
			//Function test:test_delitem
			{
			IMethod methodtest_delitem8;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_delitem8 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delitem8, new String[] {"self"} );
			}
			//Function test:test_rotate
			{
			IMethod methodtest_rotate9;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_rotate9 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_rotate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rotate9, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len10;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_len10 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len10, new String[] {"self"} );
			}
			//Function test:test_underflow
			{
			IMethod methodtest_underflow11;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_underflow11 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_underflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_underflow11, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear12;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_clear12 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear12, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr13;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_repr13 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr13, new String[] {"self"} );
			}
			//Function test:test_print
			{
			IMethod methodtest_print14;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_print14 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_print", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print14, new String[] {"self"} );
			}
			//Function test:test_init
			{
			IMethod methodtest_init15;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_init15 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init15, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash16;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_hash16 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash16, new String[] {"self"} );
			}
			//Function test:test_long_steadystate_queue_popleft
			{
			IMethod methodtest_long_steadystate_queue_popleft17;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_long_steadystate_queue_popleft17 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_long_steadystate_queue_popleft", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_steadystate_queue_popleft17, new String[] {"self"} );
			}
			//Function test:test_long_steadystate_queue_popright
			{
			IMethod methodtest_long_steadystate_queue_popright18;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_long_steadystate_queue_popright18 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_long_steadystate_queue_popright", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_steadystate_queue_popright18, new String[] {"self"} );
			}
			//Function test:test_big_queue_popleft
			{
			IMethod methodtest_big_queue_popleft19;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_big_queue_popleft19 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_big_queue_popleft", 1 );
				ModelTestUtils.assertParameterNames( methodtest_big_queue_popleft19, new String[] {"self"} );
			}
			//Function test:test_big_queue_popright
			{
			IMethod methodtest_big_queue_popright20;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_big_queue_popright20 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_big_queue_popright", 1 );
				ModelTestUtils.assertParameterNames( methodtest_big_queue_popright20, new String[] {"self"} );
			}
			//Function test:test_big_stack_right
			{
			IMethod methodtest_big_stack_right21;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_big_stack_right21 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_big_stack_right", 1 );
				ModelTestUtils.assertParameterNames( methodtest_big_stack_right21, new String[] {"self"} );
			}
			//Function test:test_big_stack_left
			{
			IMethod methodtest_big_stack_left22;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_big_stack_left22 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_big_stack_left", 1 );
				ModelTestUtils.assertParameterNames( methodtest_big_stack_left22, new String[] {"self"} );
			}
			//Function test:test_roundtrip_iter_init
			{
			IMethod methodtest_roundtrip_iter_init23;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_roundtrip_iter_init23 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_roundtrip_iter_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_iter_init23, new String[] {"self"} );
			}
			//Function test:test_pickle
			{
			IMethod methodtest_pickle24;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_pickle24 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle24, new String[] {"self"} );
			}
			//Function test:test_pickle_recursive
			{
			IMethod methodtest_pickle_recursive25;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_pickle_recursive25 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_pickle_recursive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle_recursive25, new String[] {"self"} );
			}
			//Function test:test_deepcopy
			{
			IMethod methodtest_deepcopy26;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_deepcopy26 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy26, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy27;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_copy27 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy27, new String[] {"self"} );
			}
			//Function test:test_reversed
			{
			IMethod methodtest_reversed28;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_reversed28 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_reversed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reversed28, new String[] {"self"} );
			}
			//Function test:test_gc_doesnt_blowup
			{
			IMethod methodtest_gc_doesnt_blowup29;
				IModelElement[] classTestBasic1Childs = classTestBasic1.getChildren();
				methodtest_gc_doesnt_blowup29 = ModelTestUtils.getAssertMethod( classTestBasic1Childs, "test_gc_doesnt_blowup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc_doesnt_blowup29, new String[] {"self"} );
			}
		}
		//Function test:R
		{
		IMethod methodR30;
			IModelElement[] moduleChilds = module.getChildren();
			methodR30 = ModelTestUtils.getAssertMethod( moduleChilds, "R", 1 );
			ModelTestUtils.assertParameterNames( methodR30, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classG31;
			IModelElement[] moduleChilds = module.getChildren();
			classG31 = ModelTestUtils.getAssertClass( moduleChilds, "G" );
			//Function test:__init__
			{
			IMethod method__init__32;
				IModelElement[] classG31Childs = classG31.getChildren();
				method__init__32 = ModelTestUtils.getAssertMethod( classG31Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__32, new String[] {"self", "seqn"} );
			}
			{
				IModelElement[] classG31Childs = classG31.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classG31Childs, "seqn");
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__34;
				IModelElement[] classG31Childs = classG31.getChildren();
				method__getitem__34 = ModelTestUtils.getAssertMethod( classG31Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__34, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI35;
			IModelElement[] moduleChilds = module.getChildren();
			classI35 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__36;
				IModelElement[] classI35Childs = classI35.getChildren();
				method__init__36 = ModelTestUtils.getAssertMethod( classI35Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__36, new String[] {"self", "seqn"} );
			}
			{
				IModelElement[] classI35Childs = classI35.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI35Childs, "seqn");
			}
			{
				IModelElement[] classI35Childs = classI35.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI35Childs, "i");
			}
			//Function test:__iter__
			{
			IMethod method__iter__38;
				IModelElement[] classI35Childs = classI35.getChildren();
				method__iter__38 = ModelTestUtils.getAssertMethod( classI35Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__38, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext39;
				IModelElement[] classI35Childs = classI35.getChildren();
				methodnext39 = ModelTestUtils.getAssertMethod( classI35Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext39, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg40;
			IModelElement[] moduleChilds = module.getChildren();
			classIg40 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__41;
				IModelElement[] classIg40Childs = classIg40.getChildren();
				method__init__41 = ModelTestUtils.getAssertMethod( classIg40Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__41, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__42;
				IModelElement[] classIg40Childs = classIg40.getChildren();
				method__iter__42 = ModelTestUtils.getAssertMethod( classIg40Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__42, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX43;
			IModelElement[] moduleChilds = module.getChildren();
			classX43 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__44;
				IModelElement[] classX43Childs = classX43.getChildren();
				method__init__44 = ModelTestUtils.getAssertMethod( classX43Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__44, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext45;
				IModelElement[] classX43Childs = classX43.getChildren();
				methodnext45 = ModelTestUtils.getAssertMethod( classX43Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext45, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN46;
			IModelElement[] moduleChilds = module.getChildren();
			classN46 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__47;
				IModelElement[] classN46Childs = classN46.getChildren();
				method__init__47 = ModelTestUtils.getAssertMethod( classN46Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__47, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__48;
				IModelElement[] classN46Childs = classN46.getChildren();
				method__iter__48 = ModelTestUtils.getAssertMethod( classN46Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__48, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE49;
			IModelElement[] moduleChilds = module.getChildren();
			classE49 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__50;
				IModelElement[] classE49Childs = classE49.getChildren();
				method__init__50 = ModelTestUtils.getAssertMethod( classE49Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__50, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__51;
				IModelElement[] classE49Childs = classE49.getChildren();
				method__iter__51 = ModelTestUtils.getAssertMethod( classE49Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__51, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext52;
				IModelElement[] classE49Childs = classE49.getChildren();
				methodnext52 = ModelTestUtils.getAssertMethod( classE49Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext52, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS53;
			IModelElement[] moduleChilds = module.getChildren();
			classS53 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__54;
				IModelElement[] classS53Childs = classS53.getChildren();
				method__init__54 = ModelTestUtils.getAssertMethod( classS53Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__54, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__55;
				IModelElement[] classS53Childs = classS53.getChildren();
				method__iter__55 = ModelTestUtils.getAssertMethod( classS53Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__55, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext56;
				IModelElement[] classS53Childs = classS53.getChildren();
				methodnext56 = ModelTestUtils.getAssertMethod( classS53Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext56, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL57;
			IModelElement[] moduleChilds = module.getChildren();
			methodL57 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL57, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestVariousIteratorArgs58;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVariousIteratorArgs58 = ModelTestUtils.getAssertClass( moduleChilds, "TestVariousIteratorArgs" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor59;
				IModelElement[] classTestVariousIteratorArgs58Childs = classTestVariousIteratorArgs58.getChildren();
				methodtest_constructor59 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs58Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor59, new String[] {"self"} );
			}
			//Function test:test_iter_with_altered_data
			{
			IMethod methodtest_iter_with_altered_data60;
				IModelElement[] classTestVariousIteratorArgs58Childs = classTestVariousIteratorArgs58.getChildren();
				methodtest_iter_with_altered_data60 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs58Childs, "test_iter_with_altered_data", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_with_altered_data60, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDeque61;
			IModelElement[] moduleChilds = module.getChildren();
			classDeque61 = ModelTestUtils.getAssertClass( moduleChilds, "Deque" );
		}
		//Class test
		{
		IType classDequeWithBadIter62;
			IModelElement[] moduleChilds = module.getChildren();
			classDequeWithBadIter62 = ModelTestUtils.getAssertClass( moduleChilds, "DequeWithBadIter" );
			//Function test:__iter__
			{
			IMethod method__iter__63;
				IModelElement[] classDequeWithBadIter62Childs = classDequeWithBadIter62.getChildren();
				method__iter__63 = ModelTestUtils.getAssertMethod( classDequeWithBadIter62Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__63, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubclass64;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubclass64 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubclass" );
			//Function test:test_basics
			{
			IMethod methodtest_basics65;
				IModelElement[] classTestSubclass64Childs = classTestSubclass64.getChildren();
				methodtest_basics65 = ModelTestUtils.getAssertMethod( classTestSubclass64Childs, "test_basics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basics65, new String[] {"self"} );
			}
			//Function test:test_copy_pickle
			{
			IMethod methodtest_copy_pickle66;
				IModelElement[] classTestSubclass64Childs = classTestSubclass64.getChildren();
				methodtest_copy_pickle66 = ModelTestUtils.getAssertMethod( classTestSubclass64Childs, "test_copy_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_pickle66, new String[] {"self"} );
			}
			//Function test:test_pickle
			{
			IMethod methodtest_pickle67;
				IModelElement[] classTestSubclass64Childs = classTestSubclass64.getChildren();
				methodtest_pickle67 = ModelTestUtils.getAssertMethod( classTestSubclass64Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle67, new String[] {"self"} );
			}
			//Function test:test_weakref
			{
			IMethod methodtest_weakref68;
				IModelElement[] classTestSubclass64Childs = classTestSubclass64.getChildren();
				methodtest_weakref68 = ModelTestUtils.getAssertMethod( classTestSubclass64Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref68, new String[] {"self"} );
			}
			//Function test:test_strange_subclass
			{
			IMethod methodtest_strange_subclass69;
				IModelElement[] classTestSubclass64Childs = classTestSubclass64.getChildren();
				methodtest_strange_subclass69 = ModelTestUtils.getAssertMethod( classTestSubclass64Childs, "test_strange_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strange_subclass69, new String[] {"self"} );
				//Class test
				{
				IType classX70;
					IModelElement[] methodtest_strange_subclass69Childs = methodtest_strange_subclass69.getChildren();
					classX70 = ModelTestUtils.getAssertClass( methodtest_strange_subclass69Childs, "X" );
					//Function test:__iter__
					{
					IMethod method__iter__71;
						IModelElement[] classX70Childs = classX70.getChildren();
						method__iter__71 = ModelTestUtils.getAssertMethod( classX70Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__71, new String[] {"self"} );
					}
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "libreftest");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}
		//Function test:test_main
		{
		IMethod methodtest_main72;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main72 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main72, new String[] {"verbose"} );
		}

	}
	public void testModelGen228( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_gzip.py"));

		assertNotNull("Module test_gzip.py not found", module);
		assertEquals("test_gzip.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "filename");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "data1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "data2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "line_length");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "L");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "line_length");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "L");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "L");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "oldpos");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "line1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "newpos");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "amount");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "amount");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "line2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}

	}
	public void testModelGen229( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_ucn.py"));

		assertNotNull("Module test_ucn.py not found", module);
		assertEquals("test_ucn.py", module.getElementName());
		
		//Class test
		{
		IType classUnicodeNamesTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeNamesTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeNamesTest" );
			//Function test:checkletter
			{
			IMethod methodcheckletter1;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodcheckletter1 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "checkletter", 3 );
				ModelTestUtils.assertParameterNames( methodcheckletter1, new String[] {"self", "name", "code"} );
			}
			//Function test:test_general
			{
			IMethod methodtest_general2;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_general2 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_general", 1 );
				ModelTestUtils.assertParameterNames( methodtest_general2, new String[] {"self"} );
			}
			//Function test:test_ascii_letters
			{
			IMethod methodtest_ascii_letters3;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_ascii_letters3 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_ascii_letters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ascii_letters3, new String[] {"self"} );
			}
			//Function test:test_hangul_syllables
			{
			IMethod methodtest_hangul_syllables4;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_hangul_syllables4 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_hangul_syllables", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hangul_syllables4, new String[] {"self"} );
			}
			//Function test:test_cjk_unified_ideographs
			{
			IMethod methodtest_cjk_unified_ideographs5;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_cjk_unified_ideographs5 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_cjk_unified_ideographs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cjk_unified_ideographs5, new String[] {"self"} );
			}
			//Function test:test_bmp_characters
			{
			IMethod methodtest_bmp_characters6;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_bmp_characters6 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_bmp_characters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bmp_characters6, new String[] {"self"} );
			}
			//Function test:test_misc_symbols
			{
			IMethod methodtest_misc_symbols7;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_misc_symbols7 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_misc_symbols", 1 );
				ModelTestUtils.assertParameterNames( methodtest_misc_symbols7, new String[] {"self"} );
			}
			//Function test:test_errors
			{
			IMethod methodtest_errors8;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_errors8 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors8, new String[] {"self"} );
			}
			//Function test:test_strict_eror_handling
			{
			IMethod methodtest_strict_eror_handling9;
				IModelElement[] classUnicodeNamesTest0Childs = classUnicodeNamesTest0.getChildren();
				methodtest_strict_eror_handling9 = ModelTestUtils.getAssertMethod( classUnicodeNamesTest0Childs, "test_strict_eror_handling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strict_eror_handling9, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen230( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sys.py"));

		assertNotNull("Module test_sys.py not found", module);
		assertEquals("test_sys.py", module.getElementName());
		
		//Class test
		{
		IType classSysModuleTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classSysModuleTest0 = ModelTestUtils.getAssertClass( moduleChilds, "SysModuleTest" );
			//Function test:test_original_displayhook
			{
			IMethod methodtest_original_displayhook1;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_original_displayhook1 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_original_displayhook", 1 );
				ModelTestUtils.assertParameterNames( methodtest_original_displayhook1, new String[] {"self"} );
			}
			//Function test:test_lost_displayhook
			{
			IMethod methodtest_lost_displayhook2;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_lost_displayhook2 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_lost_displayhook", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lost_displayhook2, new String[] {"self"} );
			}
			//Function test:test_custom_displayhook
			{
			IMethod methodtest_custom_displayhook3;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_custom_displayhook3 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_custom_displayhook", 1 );
				ModelTestUtils.assertParameterNames( methodtest_custom_displayhook3, new String[] {"self"} );
				//Function test:baddisplayhook
				{
				IMethod methodbaddisplayhook4;
					IModelElement[] methodtest_custom_displayhook3Childs = methodtest_custom_displayhook3.getChildren();
					methodbaddisplayhook4 = ModelTestUtils.getAssertMethod( methodtest_custom_displayhook3Childs, "baddisplayhook", 1 );
					ModelTestUtils.assertParameterNames( methodbaddisplayhook4, new String[] {"obj"} );
				}
			}
			//Function test:test_original_excepthook
			{
			IMethod methodtest_original_excepthook5;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_original_excepthook5 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_original_excepthook", 1 );
				ModelTestUtils.assertParameterNames( methodtest_original_excepthook5, new String[] {"self"} );
			}
			//Function test:test_exc_clear
			{
			IMethod methodtest_exc_clear6;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_exc_clear6 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_exc_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exc_clear6, new String[] {"self"} );
				//Function test:clear_check
				{
				IMethod methodclear_check7;
					IModelElement[] methodtest_exc_clear6Childs = methodtest_exc_clear6.getChildren();
					methodclear_check7 = ModelTestUtils.getAssertMethod( methodtest_exc_clear6Childs, "clear_check", 1 );
					ModelTestUtils.assertParameterNames( methodclear_check7, new String[] {"exc"} );
				}
				//Function test:clear
				{
				IMethod methodclear8;
					IModelElement[] methodtest_exc_clear6Childs = methodtest_exc_clear6.getChildren();
					methodclear8 = ModelTestUtils.getAssertMethod( methodtest_exc_clear6Childs, "clear", 0 );
				}
			}
			//Function test:test_exit
			{
			IMethod methodtest_exit9;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_exit9 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_exit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exit9, new String[] {"self"} );
			}
			//Function test:test_getdefaultencoding
			{
			IMethod methodtest_getdefaultencoding10;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_getdefaultencoding10 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_getdefaultencoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getdefaultencoding10, new String[] {"self"} );
			}
			//Function test:test_setcheckinterval
			{
			IMethod methodtest_setcheckinterval11;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_setcheckinterval11 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_setcheckinterval", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setcheckinterval11, new String[] {"self"} );
			}
			//Function test:test_recursionlimit
			{
			IMethod methodtest_recursionlimit12;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_recursionlimit12 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_recursionlimit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursionlimit12, new String[] {"self"} );
			}
			//Function test:test_getwindowsversion
			{
			IMethod methodtest_getwindowsversion13;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_getwindowsversion13 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_getwindowsversion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getwindowsversion13, new String[] {"self"} );
			}
			//Function test:test_dlopenflags
			{
			IMethod methodtest_dlopenflags14;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_dlopenflags14 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_dlopenflags", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dlopenflags14, new String[] {"self"} );
			}
			//Function test:test_refcount
			{
			IMethod methodtest_refcount15;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_refcount15 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_refcount", 1 );
				ModelTestUtils.assertParameterNames( methodtest_refcount15, new String[] {"self"} );
			}
			//Function test:test_getframe
			{
			IMethod methodtest_getframe16;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_getframe16 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_getframe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getframe16, new String[] {"self"} );
			}
			//Function test:test_attributes
			{
			IMethod methodtest_attributes17;
				IModelElement[] classSysModuleTest0Childs = classSysModuleTest0.getChildren();
				methodtest_attributes17 = ModelTestUtils.getAssertMethod( classSysModuleTest0Childs, "test_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attributes17, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen231( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_descr.py"));

		assertNotNull("Module test_descr.py not found", module);
		assertEquals("test_descr.py", module.getElementName());
		
		//Function test:veris
		{
		IMethod methodveris0;
			IModelElement[] moduleChilds = module.getChildren();
			methodveris0 = ModelTestUtils.getAssertMethod( moduleChilds, "veris", 2 );
			ModelTestUtils.assertParameterNames( methodveris0, new String[] {"a", "b"} );
		}
		//Function test:testunop
		{
		IMethod methodtestunop1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestunop1 = ModelTestUtils.getAssertMethod( moduleChilds, "testunop", 4 );
			ModelTestUtils.assertParameterNames( methodtestunop1, new String[] {"a", "res", "expr", "meth"} );
		}
		//Function test:testbinop
		{
		IMethod methodtestbinop2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestbinop2 = ModelTestUtils.getAssertMethod( moduleChilds, "testbinop", 5 );
			ModelTestUtils.assertParameterNames( methodtestbinop2, new String[] {"a", "b", "res", "expr", "meth"} );
		}
		//Function test:testternop
		{
		IMethod methodtestternop3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestternop3 = ModelTestUtils.getAssertMethod( moduleChilds, "testternop", 6 );
			ModelTestUtils.assertParameterNames( methodtestternop3, new String[] {"a", "b", "c", "res", "expr", "meth"} );
		}
		//Function test:testsetop
		{
		IMethod methodtestsetop4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestsetop4 = ModelTestUtils.getAssertMethod( moduleChilds, "testsetop", 5 );
			ModelTestUtils.assertParameterNames( methodtestsetop4, new String[] {"a", "b", "res", "stmt", "meth"} );
		}
		//Function test:testset2op
		{
		IMethod methodtestset2op5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestset2op5 = ModelTestUtils.getAssertMethod( moduleChilds, "testset2op", 6 );
			ModelTestUtils.assertParameterNames( methodtestset2op5, new String[] {"a", "b", "c", "res", "stmt", "meth"} );
		}
		//Function test:testset3op
		{
		IMethod methodtestset3op6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestset3op6 = ModelTestUtils.getAssertMethod( moduleChilds, "testset3op", 7 );
			ModelTestUtils.assertParameterNames( methodtestset3op6, new String[] {"a", "b", "c", "d", "res", "stmt", "meth"} );
		}
		//Function test:class_docstrings
		{
		IMethod methodclass_docstrings7;
			IModelElement[] moduleChilds = module.getChildren();
			methodclass_docstrings7 = ModelTestUtils.getAssertMethod( moduleChilds, "class_docstrings", 0 );
			//Class test
			{
			IType classClassic8;
				IModelElement[] methodclass_docstrings7Childs = methodclass_docstrings7.getChildren();
				classClassic8 = ModelTestUtils.getAssertClass( methodclass_docstrings7Childs, "Classic" );
			}
			//Class test
			{
			IType classClassic29;
				IModelElement[] methodclass_docstrings7Childs = methodclass_docstrings7.getChildren();
				classClassic29 = ModelTestUtils.getAssertClass( methodclass_docstrings7Childs, "Classic2" );
			}
			//Class test
			{
			IType classNewStatic10;
				IModelElement[] methodclass_docstrings7Childs = methodclass_docstrings7.getChildren();
				classNewStatic10 = ModelTestUtils.getAssertClass( methodclass_docstrings7Childs, "NewStatic" );
			}
			//Class test
			{
			IType classNewStatic211;
				IModelElement[] methodclass_docstrings7Childs = methodclass_docstrings7.getChildren();
				classNewStatic211 = ModelTestUtils.getAssertClass( methodclass_docstrings7Childs, "NewStatic2" );
			}
			//Class test
			{
			IType classNewDynamic12;
				IModelElement[] methodclass_docstrings7Childs = methodclass_docstrings7.getChildren();
				classNewDynamic12 = ModelTestUtils.getAssertClass( methodclass_docstrings7Childs, "NewDynamic" );
			}
			//Class test
			{
			IType classNewDynamic213;
				IModelElement[] methodclass_docstrings7Childs = methodclass_docstrings7.getChildren();
				classNewDynamic213 = ModelTestUtils.getAssertClass( methodclass_docstrings7Childs, "NewDynamic2" );
			}
		}
		//Function test:lists
		{
		IMethod methodlists14;
			IModelElement[] moduleChilds = module.getChildren();
			methodlists14 = ModelTestUtils.getAssertMethod( moduleChilds, "lists", 0 );
		}
		//Function test:dicts
		{
		IMethod methoddicts15;
			IModelElement[] moduleChilds = module.getChildren();
			methoddicts15 = ModelTestUtils.getAssertMethod( moduleChilds, "dicts", 0 );
		}
		//Function test:dict_constructor
		{
		IMethod methoddict_constructor16;
			IModelElement[] moduleChilds = module.getChildren();
			methoddict_constructor16 = ModelTestUtils.getAssertMethod( moduleChilds, "dict_constructor", 0 );
			//Class test
			{
			IType classMapping17;
				IModelElement[] methoddict_constructor16Childs = methoddict_constructor16.getChildren();
				classMapping17 = ModelTestUtils.getAssertClass( methoddict_constructor16Childs, "Mapping" );
			}
			//Class test
			{
			IType classAddressBookEntry18;
				IModelElement[] methoddict_constructor16Childs = methoddict_constructor16.getChildren();
				classAddressBookEntry18 = ModelTestUtils.getAssertClass( methoddict_constructor16Childs, "AddressBookEntry" );
				//Function test:__init__
				{
				IMethod method__init__19;
					IModelElement[] classAddressBookEntry18Childs = classAddressBookEntry18.getChildren();
					method__init__19 = ModelTestUtils.getAssertMethod( classAddressBookEntry18Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__19, new String[] {"self", "first", "last"} );
				}
				{
					IModelElement[] classAddressBookEntry18Childs = classAddressBookEntry18.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classAddressBookEntry18Childs, "first");
				}
				{
					IModelElement[] classAddressBookEntry18Childs = classAddressBookEntry18.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classAddressBookEntry18Childs, "last");
				}
				//Function test:__iter__
				{
				IMethod method__iter__21;
					IModelElement[] classAddressBookEntry18Childs = classAddressBookEntry18.getChildren();
					method__iter__21 = ModelTestUtils.getAssertMethod( classAddressBookEntry18Childs, "__iter__", 1 );
					ModelTestUtils.assertParameterNames( method__iter__21, new String[] {"self"} );
				}
			}
		}
		//Function test:test_dir
		{
		IMethod methodtest_dir22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_dir22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_dir", 0 );
			//Class test
			{
			IType classC23;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classC23 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "C" );
				//Function test:Cmethod
				{
				IMethod methodCmethod24;
					IModelElement[] classC23Childs = classC23.getChildren();
					methodCmethod24 = ModelTestUtils.getAssertMethod( classC23Childs, "Cmethod", 1 );
					ModelTestUtils.assertParameterNames( methodCmethod24, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classA25;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classA25 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "A" );
				//Function test:Amethod
				{
				IMethod methodAmethod26;
					IModelElement[] classA25Childs = classA25.getChildren();
					methodAmethod26 = ModelTestUtils.getAssertMethod( classA25Childs, "Amethod", 1 );
					ModelTestUtils.assertParameterNames( methodAmethod26, new String[] {"self"} );
				}
			}
			//Function test:interesting
			{
			IMethod methodinteresting27;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				methodinteresting27 = ModelTestUtils.getAssertMethod( methodtest_dir22Childs, "interesting", 1 );
				ModelTestUtils.assertParameterNames( methodinteresting27, new String[] {"strings"} );
			}
			//Class test
			{
			IType classC28;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classC28 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "C" );
				//Function test:Cmethod
				{
				IMethod methodCmethod29;
					IModelElement[] classC28Childs = classC28.getChildren();
					methodCmethod29 = ModelTestUtils.getAssertMethod( classC28Childs, "Cmethod", 1 );
					ModelTestUtils.assertParameterNames( methodCmethod29, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classA30;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classA30 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "A" );
				//Function test:Amethod
				{
				IMethod methodAmethod31;
					IModelElement[] classA30Childs = classA30.getChildren();
					methodAmethod31 = ModelTestUtils.getAssertMethod( classA30Childs, "Amethod", 1 );
					ModelTestUtils.assertParameterNames( methodAmethod31, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM32;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classM32 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "M" );
			}
			//Class test
			{
			IType classM233;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classM233 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "M2" );
				//Function test:getdict
				{
				IMethod methodgetdict34;
					IModelElement[] classM233Childs = classM233.getChildren();
					methodgetdict34 = ModelTestUtils.getAssertMethod( classM233Childs, "getdict", 1 );
					ModelTestUtils.assertParameterNames( methodgetdict34, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classWrapper35;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classWrapper35 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "Wrapper" );
				//Function test:__init__
				{
				IMethod method__init__36;
					IModelElement[] classWrapper35Childs = classWrapper35.getChildren();
					method__init__36 = ModelTestUtils.getAssertMethod( classWrapper35Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__36, new String[] {"self", "obj"} );
				}
				{
					IModelElement[] classWrapper35Childs = classWrapper35.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classWrapper35Childs, "__obj");
				}
				//Function test:__repr__
				{
				IMethod method__repr__38;
					IModelElement[] classWrapper35Childs = classWrapper35.getChildren();
					method__repr__38 = ModelTestUtils.getAssertMethod( classWrapper35Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__38, new String[] {"self"} );
				}
				//Function test:__getitem__
				{
				IMethod method__getitem__39;
					IModelElement[] classWrapper35Childs = classWrapper35.getChildren();
					method__getitem__39 = ModelTestUtils.getAssertMethod( classWrapper35Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__39, new String[] {"self", "key"} );
				}
				//Function test:__len__
				{
				IMethod method__len__40;
					IModelElement[] classWrapper35Childs = classWrapper35.getChildren();
					method__len__40 = ModelTestUtils.getAssertMethod( classWrapper35Childs, "__len__", 1 );
					ModelTestUtils.assertParameterNames( method__len__40, new String[] {"self"} );
				}
				//Function test:__getattr__
				{
				IMethod method__getattr__41;
					IModelElement[] classWrapper35Childs = classWrapper35.getChildren();
					method__getattr__41 = ModelTestUtils.getAssertMethod( classWrapper35Childs, "__getattr__", 2 );
					ModelTestUtils.assertParameterNames( method__getattr__41, new String[] {"self", "name"} );
				}
			}
			//Class test
			{
			IType classC42;
				IModelElement[] methodtest_dir22Childs = methodtest_dir22.getChildren();
				classC42 = ModelTestUtils.getAssertClass( methodtest_dir22Childs, "C" );
				//Function test:__getclass
				{
				IMethod method__getclass43;
					IModelElement[] classC42Childs = classC42.getChildren();
					method__getclass43 = ModelTestUtils.getAssertMethod( classC42Childs, "__getclass", 1 );
					ModelTestUtils.assertParameterNames( method__getclass43, new String[] {"self"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "binops");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "expr");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "expr");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "unops");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "expr");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "expr");
		}
		//Function test:numops
		{
		IMethod methodnumops44;
			IModelElement[] moduleChilds = module.getChildren();
			methodnumops44 = ModelTestUtils.getAssertMethod( moduleChilds, "numops", 3 );
			ModelTestUtils.assertParameterNames( methodnumops44, new String[] {"a", "b", "skip"} );
		}
		//Function test:ints
		{
		IMethod methodints45;
			IModelElement[] moduleChilds = module.getChildren();
			methodints45 = ModelTestUtils.getAssertMethod( moduleChilds, "ints", 0 );
			//Class test
			{
			IType classC46;
				IModelElement[] methodints45Childs = methodints45.getChildren();
				classC46 = ModelTestUtils.getAssertClass( methodints45Childs, "C" );
				//Function test:__add__
				{
				IMethod method__add__47;
					IModelElement[] classC46Childs = classC46.getChildren();
					method__add__47 = ModelTestUtils.getAssertMethod( classC46Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__47, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:longs
		{
		IMethod methodlongs48;
			IModelElement[] moduleChilds = module.getChildren();
			methodlongs48 = ModelTestUtils.getAssertMethod( moduleChilds, "longs", 0 );
		}
		//Function test:floats
		{
		IMethod methodfloats49;
			IModelElement[] moduleChilds = module.getChildren();
			methodfloats49 = ModelTestUtils.getAssertMethod( moduleChilds, "floats", 0 );
		}
		//Function test:complexes
		{
		IMethod methodcomplexes50;
			IModelElement[] moduleChilds = module.getChildren();
			methodcomplexes50 = ModelTestUtils.getAssertMethod( moduleChilds, "complexes", 0 );
			//Class test
			{
			IType classNumber51;
				IModelElement[] methodcomplexes50Childs = methodcomplexes50.getChildren();
				classNumber51 = ModelTestUtils.getAssertClass( methodcomplexes50Childs, "Number" );
				//Function test:__new__
				{
				IMethod method__new__52;
					IModelElement[] classNumber51Childs = classNumber51.getChildren();
					method__new__52 = ModelTestUtils.getAssertMethod( classNumber51Childs, "__new__", 3 );
					ModelTestUtils.assertParameterNames( method__new__52, new String[] {"cls", "args", "kwds"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__53;
					IModelElement[] classNumber51Childs = classNumber51.getChildren();
					method__repr__53 = ModelTestUtils.getAssertMethod( classNumber51Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__53, new String[] {"self"} );
				}
			}
		}
		//Function test:spamlists
		{
		IMethod methodspamlists54;
			IModelElement[] moduleChilds = module.getChildren();
			methodspamlists54 = ModelTestUtils.getAssertMethod( moduleChilds, "spamlists", 0 );
			//Function test:spamlist
			{
			IMethod methodspamlist55;
				IModelElement[] methodspamlists54Childs = methodspamlists54.getChildren();
				methodspamlist55 = ModelTestUtils.getAssertMethod( methodspamlists54Childs, "spamlist", 2 );
				ModelTestUtils.assertParameterNames( methodspamlist55, new String[] {"l", "memo"} );
			}
			//Class test
			{
			IType classC56;
				IModelElement[] methodspamlists54Childs = methodspamlists54.getChildren();
				classC56 = ModelTestUtils.getAssertClass( methodspamlists54Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo57;
					IModelElement[] classC56Childs = classC56.getChildren();
					methodfoo57 = ModelTestUtils.getAssertMethod( classC56Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo57, new String[] {"self"} );
				}
			}
		}
		//Function test:spamdicts
		{
		IMethod methodspamdicts58;
			IModelElement[] moduleChilds = module.getChildren();
			methodspamdicts58 = ModelTestUtils.getAssertMethod( moduleChilds, "spamdicts", 0 );
			//Function test:spamdict
			{
			IMethod methodspamdict59;
				IModelElement[] methodspamdicts58Childs = methodspamdicts58.getChildren();
				methodspamdict59 = ModelTestUtils.getAssertMethod( methodspamdicts58Childs, "spamdict", 2 );
				ModelTestUtils.assertParameterNames( methodspamdict59, new String[] {"d", "memo"} );
			}
			//Class test
			{
			IType classC60;
				IModelElement[] methodspamdicts58Childs = methodspamdicts58.getChildren();
				classC60 = ModelTestUtils.getAssertClass( methodspamdicts58Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo61;
					IModelElement[] classC60Childs = classC60.getChildren();
					methodfoo61 = ModelTestUtils.getAssertMethod( classC60Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo61, new String[] {"self"} );
				}
			}
		}
		//Function test:pydicts
		{
		IMethod methodpydicts62;
			IModelElement[] moduleChilds = module.getChildren();
			methodpydicts62 = ModelTestUtils.getAssertMethod( moduleChilds, "pydicts", 0 );
			//Class test
			{
			IType classC63;
				IModelElement[] methodpydicts62Childs = methodpydicts62.getChildren();
				classC63 = ModelTestUtils.getAssertClass( methodpydicts62Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__64;
					IModelElement[] classC63Childs = classC63.getChildren();
					method__init__64 = ModelTestUtils.getAssertMethod( classC63Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__64, new String[] {"self", "a", "kw"} );
				}
				{
					IModelElement[] classC63Childs = classC63.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC63Childs, "state");
				}
				{
					IModelElement[] classC63Childs = classC63.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC63Childs, "OP_ASSIGN");
				}
				//Function test:__getitem__
				{
				IMethod method__getitem__66;
					IModelElement[] classC63Childs = classC63.getChildren();
					method__getitem__66 = ModelTestUtils.getAssertMethod( classC63Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__66, new String[] {"self", "key"} );
				}
				//Function test:__setitem__
				{
				IMethod method__setitem__67;
					IModelElement[] classC63Childs = classC63.getChildren();
					method__setitem__67 = ModelTestUtils.getAssertMethod( classC63Childs, "__setitem__", 3 );
					ModelTestUtils.assertParameterNames( method__setitem__67, new String[] {"self", "key", "value"} );
				}
				//Function test:setstate
				{
				IMethod methodsetstate68;
					IModelElement[] classC63Childs = classC63.getChildren();
					methodsetstate68 = ModelTestUtils.getAssertMethod( classC63Childs, "setstate", 2 );
					ModelTestUtils.assertParameterNames( methodsetstate68, new String[] {"self", "state"} );
				}
				{
					IModelElement[] classC63Childs = classC63.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC63Childs, "state");
				}
				//Function test:getstate
				{
				IMethod methodgetstate70;
					IModelElement[] classC63Childs = classC63.getChildren();
					methodgetstate70 = ModelTestUtils.getAssertMethod( classC63Childs, "getstate", 1 );
					ModelTestUtils.assertParameterNames( methodgetstate70, new String[] {"self"} );
				}
			}
		}
		//Function test:pylists
		{
		IMethod methodpylists71;
			IModelElement[] moduleChilds = module.getChildren();
			methodpylists71 = ModelTestUtils.getAssertMethod( moduleChilds, "pylists", 0 );
			//Class test
			{
			IType classC72;
				IModelElement[] methodpylists71Childs = methodpylists71.getChildren();
				classC72 = ModelTestUtils.getAssertClass( methodpylists71Childs, "C" );
				//Function test:__getitem__
				{
				IMethod method__getitem__73;
					IModelElement[] classC72Childs = classC72.getChildren();
					method__getitem__73 = ModelTestUtils.getAssertMethod( classC72Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__73, new String[] {"self", "i"} );
				}
				//Function test:__getslice__
				{
				IMethod method__getslice__74;
					IModelElement[] classC72Childs = classC72.getChildren();
					method__getslice__74 = ModelTestUtils.getAssertMethod( classC72Childs, "__getslice__", 3 );
					ModelTestUtils.assertParameterNames( method__getslice__74, new String[] {"self", "i", "j"} );
				}
			}
		}
		//Function test:metaclass
		{
		IMethod methodmetaclass75;
			IModelElement[] moduleChilds = module.getChildren();
			methodmetaclass75 = ModelTestUtils.getAssertMethod( moduleChilds, "metaclass", 0 );
			//Class test
			{
			IType classC76;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC76 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__77;
					IModelElement[] classC76Childs = classC76.getChildren();
					method__init__77 = ModelTestUtils.getAssertMethod( classC76Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__77, new String[] {"self"} );
				}
				{
					IModelElement[] classC76Childs = classC76.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC76Childs, "__state");
				}
				//Function test:getstate
				{
				IMethod methodgetstate79;
					IModelElement[] classC76Childs = classC76.getChildren();
					methodgetstate79 = ModelTestUtils.getAssertMethod( classC76Childs, "getstate", 1 );
					ModelTestUtils.assertParameterNames( methodgetstate79, new String[] {"self"} );
				}
				//Function test:setstate
				{
				IMethod methodsetstate80;
					IModelElement[] classC76Childs = classC76.getChildren();
					methodsetstate80 = ModelTestUtils.getAssertMethod( classC76Childs, "setstate", 2 );
					ModelTestUtils.assertParameterNames( methodsetstate80, new String[] {"self", "state"} );
				}
				{
					IModelElement[] classC76Childs = classC76.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC76Childs, "__state");
				}
			}
			//Class test
			{
			IType classD82;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classD82 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "D" );
				//Class test
				{
				IType class__metaclass__83;
					IModelElement[] classD82Childs = classD82.getChildren();
					class__metaclass__83 = ModelTestUtils.getAssertClass( classD82Childs, "__metaclass__" );
					//Function test:myself
					{
					IMethod methodmyself84;
						IModelElement[] class__metaclass__83Childs = class__metaclass__83.getChildren();
						methodmyself84 = ModelTestUtils.getAssertMethod( class__metaclass__83Childs, "myself", 1 );
						ModelTestUtils.assertParameterNames( methodmyself84, new String[] {"cls"} );
					}
				}
			}
			//Class test
			{
			IType classM185;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classM185 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "M1" );
				//Function test:__new__
				{
				IMethod method__new__86;
					IModelElement[] classM185Childs = classM185.getChildren();
					method__new__86 = ModelTestUtils.getAssertMethod( classM185Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__86, new String[] {"cls", "name", "bases", "dict"} );
				}
			}
			//Class test
			{
			IType classC87;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC87 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
			}
			//Class test
			{
			IType class_instance88;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				class_instance88 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "_instance" );
			}
			//Class test
			{
			IType classM289;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classM289 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "M2" );
				//Function test:__new__
				{
				IMethod method__new__90;
					IModelElement[] classM289Childs = classM289.getChildren();
					method__new__90 = ModelTestUtils.getAssertMethod( classM289Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__90, new String[] {"cls", "name", "bases", "dict"} );
				}
				{
					IModelElement[] classM289Childs = classM289.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classM289Childs, "name");
				}
				{
					IModelElement[] classM289Childs = classM289.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classM289Childs, "bases");
				}
				{
					IModelElement[] classM289Childs = classM289.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classM289Childs, "dict");
				}
				//Function test:__call__
				{
				IMethod method__call__92;
					IModelElement[] classM289Childs = classM289.getChildren();
					method__call__92 = ModelTestUtils.getAssertMethod( classM289Childs, "__call__", 1 );
					ModelTestUtils.assertParameterNames( method__call__92, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC93;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC93 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
				//Function test:spam
				{
				IMethod methodspam94;
					IModelElement[] classC93Childs = classC93.getChildren();
					methodspam94 = ModelTestUtils.getAssertMethod( classC93Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam94, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classautosuper95;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classautosuper95 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "autosuper" );
				//Function test:__new__
				{
				IMethod method__new__96;
					IModelElement[] classautosuper95Childs = classautosuper95.getChildren();
					method__new__96 = ModelTestUtils.getAssertMethod( classautosuper95Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__96, new String[] {"metaclass", "name", "bases", "dict"} );
				}
			}
			//Class test
			{
			IType classA97;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classA97 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "A" );
				//Function test:meth
				{
				IMethod methodmeth98;
					IModelElement[] classA97Childs = classA97.getChildren();
					methodmeth98 = ModelTestUtils.getAssertMethod( classA97Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth98, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB99;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classB99 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "B" );
				//Function test:meth
				{
				IMethod methodmeth100;
					IModelElement[] classB99Childs = classB99.getChildren();
					methodmeth100 = ModelTestUtils.getAssertMethod( classB99Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth100, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC101;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC101 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth102;
					IModelElement[] classC101Childs = classC101.getChildren();
					methodmeth102 = ModelTestUtils.getAssertMethod( classC101Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth102, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD103;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classD103 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "D" );
				//Function test:meth
				{
				IMethod methodmeth104;
					IModelElement[] classD103Childs = classD103.getChildren();
					methodmeth104 = ModelTestUtils.getAssertMethod( classD103Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth104, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classE105;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classE105 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "E" );
				//Function test:meth
				{
				IMethod methodmeth106;
					IModelElement[] classE105Childs = classE105.getChildren();
					methodmeth106 = ModelTestUtils.getAssertMethod( classE105Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth106, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classautoproperty107;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classautoproperty107 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "autoproperty" );
				//Function test:__new__
				{
				IMethod method__new__108;
					IModelElement[] classautoproperty107Childs = classautoproperty107.getChildren();
					method__new__108 = ModelTestUtils.getAssertMethod( classautoproperty107Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__108, new String[] {"metaclass", "name", "bases", "dict"} );
				}
			}
			//Class test
			{
			IType classA109;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classA109 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "A" );
				//Function test:_get_x
				{
				IMethod method_get_x110;
					IModelElement[] classA109Childs = classA109.getChildren();
					method_get_x110 = ModelTestUtils.getAssertMethod( classA109Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x110, new String[] {"self"} );
				}
				//Function test:_set_x
				{
				IMethod method_set_x111;
					IModelElement[] classA109Childs = classA109.getChildren();
					method_set_x111 = ModelTestUtils.getAssertMethod( classA109Childs, "_set_x", 2 );
					ModelTestUtils.assertParameterNames( method_set_x111, new String[] {"self", "x"} );
				}
				{
					IModelElement[] classA109Childs = classA109.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classA109Childs, "__x");
				}
			}
			//Class test
			{
			IType classmultimetaclass113;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classmultimetaclass113 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "multimetaclass" );
			}
			//Class test
			{
			IType classA114;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classA114 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "A" );
				//Function test:_get_x
				{
				IMethod method_get_x115;
					IModelElement[] classA114Childs = classA114.getChildren();
					method_get_x115 = ModelTestUtils.getAssertMethod( classA114Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x115, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB116;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classB116 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "B" );
				//Function test:_get_x
				{
				IMethod method_get_x117;
					IModelElement[] classB116Childs = classB116.getChildren();
					method_get_x117 = ModelTestUtils.getAssertMethod( classB116Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x117, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC118;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC118 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
				//Function test:_get_x
				{
				IMethod method_get_x119;
					IModelElement[] classC118Childs = classC118.getChildren();
					method_get_x119 = ModelTestUtils.getAssertMethod( classC118Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x119, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD120;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classD120 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "D" );
				//Function test:_get_x
				{
				IMethod method_get_x121;
					IModelElement[] classD120Childs = classD120.getChildren();
					method_get_x121 = ModelTestUtils.getAssertMethod( classD120Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x121, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classT122;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classT122 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "T" );
				//Function test:__init__
				{
				IMethod method__init__123;
					IModelElement[] classT122Childs = classT122.getChildren();
					method__init__123 = ModelTestUtils.getAssertMethod( classT122Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__123, new String[] {"self", "args"} );
				}
			}
			//Class test
			{
			IType classC124;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC124 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
			}
			//Class test
			{
			IType classC125;
				IModelElement[] methodmetaclass75Childs = methodmetaclass75.getChildren();
				classC125 = ModelTestUtils.getAssertClass( methodmetaclass75Childs, "C" );
			}
		}
		//Function test:pymods
		{
		IMethod methodpymods126;
			IModelElement[] moduleChilds = module.getChildren();
			methodpymods126 = ModelTestUtils.getAssertMethod( moduleChilds, "pymods", 0 );
			//Class test
			{
			IType classMM127;
				IModelElement[] methodpymods126Childs = methodpymods126.getChildren();
				classMM127 = ModelTestUtils.getAssertClass( methodpymods126Childs, "MM" );
				//Function test:__init__
				{
				IMethod method__init__128;
					IModelElement[] classMM127Childs = classMM127.getChildren();
					method__init__128 = ModelTestUtils.getAssertMethod( classMM127Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__128, new String[] {"self", "name"} );
				}
				//Function test:__getattribute__
				{
				IMethod method__getattribute__129;
					IModelElement[] classMM127Childs = classMM127.getChildren();
					method__getattribute__129 = ModelTestUtils.getAssertMethod( classMM127Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__129, new String[] {"self", "name"} );
				}
				//Function test:__setattr__
				{
				IMethod method__setattr__130;
					IModelElement[] classMM127Childs = classMM127.getChildren();
					method__setattr__130 = ModelTestUtils.getAssertMethod( classMM127Childs, "__setattr__", 3 );
					ModelTestUtils.assertParameterNames( method__setattr__130, new String[] {"self", "name", "value"} );
				}
				//Function test:__delattr__
				{
				IMethod method__delattr__131;
					IModelElement[] classMM127Childs = classMM127.getChildren();
					method__delattr__131 = ModelTestUtils.getAssertMethod( classMM127Childs, "__delattr__", 2 );
					ModelTestUtils.assertParameterNames( method__delattr__131, new String[] {"self", "name"} );
				}
			}
		}
		//Function test:multi
		{
		IMethod methodmulti132;
			IModelElement[] moduleChilds = module.getChildren();
			methodmulti132 = ModelTestUtils.getAssertMethod( moduleChilds, "multi", 0 );
			//Class test
			{
			IType classC133;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classC133 = ModelTestUtils.getAssertClass( methodmulti132Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__134;
					IModelElement[] classC133Childs = classC133.getChildren();
					method__init__134 = ModelTestUtils.getAssertMethod( classC133Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__134, new String[] {"self"} );
				}
				//Function test:getstate
				{
				IMethod methodgetstate135;
					IModelElement[] classC133Childs = classC133.getChildren();
					methodgetstate135 = ModelTestUtils.getAssertMethod( classC133Childs, "getstate", 1 );
					ModelTestUtils.assertParameterNames( methodgetstate135, new String[] {"self"} );
				}
				//Function test:setstate
				{
				IMethod methodsetstate136;
					IModelElement[] classC133Childs = classC133.getChildren();
					methodsetstate136 = ModelTestUtils.getAssertMethod( classC133Childs, "setstate", 2 );
					ModelTestUtils.assertParameterNames( methodsetstate136, new String[] {"self", "state"} );
				}
			}
			//Class test
			{
			IType classD137;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classD137 = ModelTestUtils.getAssertClass( methodmulti132Childs, "D" );
				//Function test:__init__
				{
				IMethod method__init__138;
					IModelElement[] classD137Childs = classD137.getChildren();
					method__init__138 = ModelTestUtils.getAssertMethod( classD137Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__138, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classNode139;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classNode139 = ModelTestUtils.getAssertClass( methodmulti132Childs, "Node" );
				//Function test:__int__
				{
				IMethod method__int__140;
					IModelElement[] classNode139Childs = classNode139.getChildren();
					method__int__140 = ModelTestUtils.getAssertMethod( classNode139Childs, "__int__", 1 );
					ModelTestUtils.assertParameterNames( method__int__140, new String[] {"self"} );
				}
				//Function test:foo
				{
				IMethod methodfoo141;
					IModelElement[] classNode139Childs = classNode139.getChildren();
					methodfoo141 = ModelTestUtils.getAssertMethod( classNode139Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo141, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classFrag142;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classFrag142 = ModelTestUtils.getAssertClass( methodmulti132Childs, "Frag" );
				//Function test:foo
				{
				IMethod methodfoo143;
					IModelElement[] classFrag142Childs = classFrag142.getChildren();
					methodfoo143 = ModelTestUtils.getAssertMethod( classFrag142Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo143, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classA144;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classA144 = ModelTestUtils.getAssertClass( methodmulti132Childs, "A" );
			}
			//Class test
			{
			IType classB145;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classB145 = ModelTestUtils.getAssertClass( methodmulti132Childs, "B" );
			}
			//Class test
			{
			IType classC146;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classC146 = ModelTestUtils.getAssertClass( methodmulti132Childs, "C" );
			}
			//Class test
			{
			IType classD147;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classD147 = ModelTestUtils.getAssertClass( methodmulti132Childs, "D" );
			}
			//Class test
			{
			IType classE148;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classE148 = ModelTestUtils.getAssertClass( methodmulti132Childs, "E" );
			}
			//Class test
			{
			IType classF149;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classF149 = ModelTestUtils.getAssertClass( methodmulti132Childs, "F" );
			}
			//Class test
			{
			IType classC150;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classC150 = ModelTestUtils.getAssertClass( methodmulti132Childs, "C" );
				//Function test:cmethod
				{
				IMethod methodcmethod151;
					IModelElement[] classC150Childs = classC150.getChildren();
					methodcmethod151 = ModelTestUtils.getAssertMethod( classC150Childs, "cmethod", 1 );
					ModelTestUtils.assertParameterNames( methodcmethod151, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method152;
					IModelElement[] classC150Childs = classC150.getChildren();
					methodall_method152 = ModelTestUtils.getAssertMethod( classC150Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method152, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM1153;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classM1153 = ModelTestUtils.getAssertClass( methodmulti132Childs, "M1" );
				//Function test:m1method
				{
				IMethod methodm1method154;
					IModelElement[] classM1153Childs = classM1153.getChildren();
					methodm1method154 = ModelTestUtils.getAssertMethod( classM1153Childs, "m1method", 1 );
					ModelTestUtils.assertParameterNames( methodm1method154, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method155;
					IModelElement[] classM1153Childs = classM1153.getChildren();
					methodall_method155 = ModelTestUtils.getAssertMethod( classM1153Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method155, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD156;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classD156 = ModelTestUtils.getAssertClass( methodmulti132Childs, "D" );
				//Function test:dmethod
				{
				IMethod methoddmethod157;
					IModelElement[] classD156Childs = classD156.getChildren();
					methoddmethod157 = ModelTestUtils.getAssertMethod( classD156Childs, "dmethod", 1 );
					ModelTestUtils.assertParameterNames( methoddmethod157, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method158;
					IModelElement[] classD156Childs = classD156.getChildren();
					methodall_method158 = ModelTestUtils.getAssertMethod( classD156Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method158, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM2159;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classM2159 = ModelTestUtils.getAssertClass( methodmulti132Childs, "M2" );
				//Function test:m2method
				{
				IMethod methodm2method160;
					IModelElement[] classM2159Childs = classM2159.getChildren();
					methodm2method160 = ModelTestUtils.getAssertMethod( classM2159Childs, "m2method", 1 );
					ModelTestUtils.assertParameterNames( methodm2method160, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method161;
					IModelElement[] classM2159Childs = classM2159.getChildren();
					methodall_method161 = ModelTestUtils.getAssertMethod( classM2159Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method161, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM3162;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classM3162 = ModelTestUtils.getAssertClass( methodmulti132Childs, "M3" );
				//Function test:m3method
				{
				IMethod methodm3method163;
					IModelElement[] classM3162Childs = classM3162.getChildren();
					methodm3method163 = ModelTestUtils.getAssertMethod( classM3162Childs, "m3method", 1 );
					ModelTestUtils.assertParameterNames( methodm3method163, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method164;
					IModelElement[] classM3162Childs = classM3162.getChildren();
					methodall_method164 = ModelTestUtils.getAssertMethod( classM3162Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method164, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classClassic165;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classClassic165 = ModelTestUtils.getAssertClass( methodmulti132Childs, "Classic" );
			}
			//Class test
			{
			IType classNew166;
				IModelElement[] methodmulti132Childs = methodmulti132.getChildren();
				classNew166 = ModelTestUtils.getAssertClass( methodmulti132Childs, "New" );
			}
		}
		//Function test:diamond
		{
		IMethod methoddiamond167;
			IModelElement[] moduleChilds = module.getChildren();
			methoddiamond167 = ModelTestUtils.getAssertMethod( moduleChilds, "diamond", 0 );
			//Class test
			{
			IType classA168;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classA168 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "A" );
				//Function test:spam
				{
				IMethod methodspam169;
					IModelElement[] classA168Childs = classA168.getChildren();
					methodspam169 = ModelTestUtils.getAssertMethod( classA168Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam169, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB170;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classB170 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "B" );
				//Function test:boo
				{
				IMethod methodboo171;
					IModelElement[] classB170Childs = classB170.getChildren();
					methodboo171 = ModelTestUtils.getAssertMethod( classB170Childs, "boo", 1 );
					ModelTestUtils.assertParameterNames( methodboo171, new String[] {"self"} );
				}
				//Function test:spam
				{
				IMethod methodspam172;
					IModelElement[] classB170Childs = classB170.getChildren();
					methodspam172 = ModelTestUtils.getAssertMethod( classB170Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam172, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC173;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classC173 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "C" );
				//Function test:boo
				{
				IMethod methodboo174;
					IModelElement[] classC173Childs = classC173.getChildren();
					methodboo174 = ModelTestUtils.getAssertMethod( classC173Childs, "boo", 1 );
					ModelTestUtils.assertParameterNames( methodboo174, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD175;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classD175 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "D" );
			}
			//Class test
			{
			IType classE176;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classE176 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "E" );
			}
			//Class test
			{
			IType classF177;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classF177 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "F" );
			}
			//Class test
			{
			IType classG178;
				IModelElement[] methoddiamond167Childs = methoddiamond167.getChildren();
				classG178 = ModelTestUtils.getAssertClass( methoddiamond167Childs, "G" );
			}
		}
		//Function test:ex5
		{
		IMethod methodex5179;
			IModelElement[] moduleChilds = module.getChildren();
			methodex5179 = ModelTestUtils.getAssertMethod( moduleChilds, "ex5", 0 );
			//Class test
			{
			IType classA180;
				IModelElement[] methodex5179Childs = methodex5179.getChildren();
				classA180 = ModelTestUtils.getAssertClass( methodex5179Childs, "A" );
			}
			//Class test
			{
			IType classB181;
				IModelElement[] methodex5179Childs = methodex5179.getChildren();
				classB181 = ModelTestUtils.getAssertClass( methodex5179Childs, "B" );
			}
			//Class test
			{
			IType classC182;
				IModelElement[] methodex5179Childs = methodex5179.getChildren();
				classC182 = ModelTestUtils.getAssertClass( methodex5179Childs, "C" );
			}
			//Class test
			{
			IType classX183;
				IModelElement[] methodex5179Childs = methodex5179.getChildren();
				classX183 = ModelTestUtils.getAssertClass( methodex5179Childs, "X" );
			}
			//Class test
			{
			IType classY184;
				IModelElement[] methodex5179Childs = methodex5179.getChildren();
				classY184 = ModelTestUtils.getAssertClass( methodex5179Childs, "Y" );
			}
			//Class test
			{
			IType classZ185;
				IModelElement[] methodex5179Childs = methodex5179.getChildren();
				classZ185 = ModelTestUtils.getAssertClass( methodex5179Childs, "Z" );
			}
		}
		//Function test:monotonicity
		{
		IMethod methodmonotonicity186;
			IModelElement[] moduleChilds = module.getChildren();
			methodmonotonicity186 = ModelTestUtils.getAssertMethod( moduleChilds, "monotonicity", 0 );
			//Class test
			{
			IType classBoat187;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classBoat187 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "Boat" );
			}
			//Class test
			{
			IType classDayBoat188;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classDayBoat188 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "DayBoat" );
			}
			//Class test
			{
			IType classWheelBoat189;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classWheelBoat189 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "WheelBoat" );
			}
			//Class test
			{
			IType classEngineLess190;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classEngineLess190 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "EngineLess" );
			}
			//Class test
			{
			IType classSmallMultihull191;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classSmallMultihull191 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "SmallMultihull" );
			}
			//Class test
			{
			IType classPedalWheelBoat192;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classPedalWheelBoat192 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "PedalWheelBoat" );
			}
			//Class test
			{
			IType classSmallCatamaran193;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classSmallCatamaran193 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "SmallCatamaran" );
			}
			//Class test
			{
			IType classPedalo194;
				IModelElement[] methodmonotonicity186Childs = methodmonotonicity186.getChildren();
				classPedalo194 = ModelTestUtils.getAssertClass( methodmonotonicity186Childs, "Pedalo" );
			}
		}
		//Function test:consistency_with_epg
		{
		IMethod methodconsistency_with_epg195;
			IModelElement[] moduleChilds = module.getChildren();
			methodconsistency_with_epg195 = ModelTestUtils.getAssertMethod( moduleChilds, "consistency_with_epg", 0 );
			//Class test
			{
			IType classPane196;
				IModelElement[] methodconsistency_with_epg195Childs = methodconsistency_with_epg195.getChildren();
				classPane196 = ModelTestUtils.getAssertClass( methodconsistency_with_epg195Childs, "Pane" );
			}
			//Class test
			{
			IType classScrollingMixin197;
				IModelElement[] methodconsistency_with_epg195Childs = methodconsistency_with_epg195.getChildren();
				classScrollingMixin197 = ModelTestUtils.getAssertClass( methodconsistency_with_epg195Childs, "ScrollingMixin" );
			}
			//Class test
			{
			IType classEditingMixin198;
				IModelElement[] methodconsistency_with_epg195Childs = methodconsistency_with_epg195.getChildren();
				classEditingMixin198 = ModelTestUtils.getAssertClass( methodconsistency_with_epg195Childs, "EditingMixin" );
			}
			//Class test
			{
			IType classScrollablePane199;
				IModelElement[] methodconsistency_with_epg195Childs = methodconsistency_with_epg195.getChildren();
				classScrollablePane199 = ModelTestUtils.getAssertClass( methodconsistency_with_epg195Childs, "ScrollablePane" );
			}
			//Class test
			{
			IType classEditablePane200;
				IModelElement[] methodconsistency_with_epg195Childs = methodconsistency_with_epg195.getChildren();
				classEditablePane200 = ModelTestUtils.getAssertClass( methodconsistency_with_epg195Childs, "EditablePane" );
			}
			//Class test
			{
			IType classEditableScrollablePane201;
				IModelElement[] methodconsistency_with_epg195Childs = methodconsistency_with_epg195.getChildren();
				classEditableScrollablePane201 = ModelTestUtils.getAssertClass( methodconsistency_with_epg195Childs, "EditableScrollablePane" );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mro_err_msg");
		}
		//Function test:mro_disagreement
		{
		IMethod methodmro_disagreement202;
			IModelElement[] moduleChilds = module.getChildren();
			methodmro_disagreement202 = ModelTestUtils.getAssertMethod( moduleChilds, "mro_disagreement", 0 );
			//Function test:raises
			{
			IMethod methodraises203;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				methodraises203 = ModelTestUtils.getAssertMethod( methodmro_disagreement202Childs, "raises", 4 );
				ModelTestUtils.assertParameterNames( methodraises203, new String[] {"exc", "expected", "callable", "args"} );
			}
			//Class test
			{
			IType classA204;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classA204 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "A" );
			}
			//Class test
			{
			IType classB205;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classB205 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "B" );
			}
			//Class test
			{
			IType classC206;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classC206 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "C" );
			}
			//Class test
			{
			IType classGridLayout207;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classGridLayout207 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "GridLayout" );
			}
			//Class test
			{
			IType classHorizontalGrid208;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classHorizontalGrid208 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "HorizontalGrid" );
			}
			//Class test
			{
			IType classVerticalGrid209;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classVerticalGrid209 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "VerticalGrid" );
			}
			//Class test
			{
			IType classHVGrid210;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classHVGrid210 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "HVGrid" );
			}
			//Class test
			{
			IType classVHGrid211;
				IModelElement[] methodmro_disagreement202Childs = methodmro_disagreement202.getChildren();
				classVHGrid211 = ModelTestUtils.getAssertClass( methodmro_disagreement202Childs, "VHGrid" );
			}
		}
		//Function test:objects
		{
		IMethod methodobjects212;
			IModelElement[] moduleChilds = module.getChildren();
			methodobjects212 = ModelTestUtils.getAssertMethod( moduleChilds, "objects", 0 );
			//Class test
			{
			IType classCdict213;
				IModelElement[] methodobjects212Childs = methodobjects212.getChildren();
				classCdict213 = ModelTestUtils.getAssertClass( methodobjects212Childs, "Cdict" );
			}
		}
		//Function test:slots
		{
		IMethod methodslots214;
			IModelElement[] moduleChilds = module.getChildren();
			methodslots214 = ModelTestUtils.getAssertMethod( moduleChilds, "slots", 0 );
			//Class test
			{
			IType classC0215;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC0215 = ModelTestUtils.getAssertClass( methodslots214Childs, "C0" );
			}
			//Class test
			{
			IType classC1216;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC1216 = ModelTestUtils.getAssertClass( methodslots214Childs, "C1" );
			}
			//Class test
			{
			IType classC3217;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC3217 = ModelTestUtils.getAssertClass( methodslots214Childs, "C3" );
			}
			//Class test
			{
			IType classC4218;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC4218 = ModelTestUtils.getAssertClass( methodslots214Childs, "C4" );
				//Function test:__init__
				{
				IMethod method__init__219;
					IModelElement[] classC4218Childs = classC4218.getChildren();
					method__init__219 = ModelTestUtils.getAssertMethod( classC4218Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__219, new String[] {"self", "value"} );
				}
				{
					IModelElement[] classC4218Childs = classC4218.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC4218Childs, "__a");
				}
				//Function test:get
				{
				IMethod methodget221;
					IModelElement[] classC4218Childs = classC4218.getChildren();
					methodget221 = ModelTestUtils.getAssertMethod( classC4218Childs, "get", 1 );
					ModelTestUtils.assertParameterNames( methodget221, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC222;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC222 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classC223;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC223 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classC224;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC224 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classC225;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC225 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classC226;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC226 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classC227;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC227 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classCounted228;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classCounted228 = ModelTestUtils.getAssertClass( methodslots214Childs, "Counted" );
				//Function test:__init__
				{
				IMethod method__init__229;
					IModelElement[] classCounted228Childs = classCounted228.getChildren();
					method__init__229 = ModelTestUtils.getAssertMethod( classCounted228Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__229, new String[] {"self"} );
				}
				//Function test:__del__
				{
				IMethod method__del__230;
					IModelElement[] classCounted228Childs = classCounted228.getChildren();
					method__del__230 = ModelTestUtils.getAssertMethod( classCounted228Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__230, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC231;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classC231 = ModelTestUtils.getAssertClass( methodslots214Childs, "C" );
			}
			//Class test
			{
			IType classD232;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classD232 = ModelTestUtils.getAssertClass( methodslots214Childs, "D" );
			}
			//Class test
			{
			IType classE233;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classE233 = ModelTestUtils.getAssertClass( methodslots214Childs, "E" );
			}
			//Class test
			{
			IType classF234;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classF234 = ModelTestUtils.getAssertClass( methodslots214Childs, "F" );
			}
			//Class test
			{
			IType classG235;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classG235 = ModelTestUtils.getAssertClass( methodslots214Childs, "G" );
				//Function test:__cmp__
				{
				IMethod method__cmp__236;
					IModelElement[] classG235Childs = classG235.getChildren();
					method__cmp__236 = ModelTestUtils.getAssertMethod( classG235Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__236, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classH237;
				IModelElement[] methodslots214Childs = methodslots214.getChildren();
				classH237 = ModelTestUtils.getAssertClass( methodslots214Childs, "H" );
				//Function test:__init__
				{
				IMethod method__init__238;
					IModelElement[] classH237Childs = classH237.getChildren();
					method__init__238 = ModelTestUtils.getAssertMethod( classH237Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__238, new String[] {"self"} );
				}
				{
					IModelElement[] classH237Childs = classH237.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classH237Childs, "a");
				}
				{
					IModelElement[] classH237Childs = classH237.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classH237Childs, "b");
				}
				//Function test:__del__
				{
				IMethod method__del__240;
					IModelElement[] classH237Childs = classH237.getChildren();
					method__del__240 = ModelTestUtils.getAssertMethod( classH237Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__240, new String[] {"self"} );
				}
			}
		}
		//Function test:slotspecials
		{
		IMethod methodslotspecials241;
			IModelElement[] moduleChilds = module.getChildren();
			methodslotspecials241 = ModelTestUtils.getAssertMethod( moduleChilds, "slotspecials", 0 );
			//Class test
			{
			IType classD242;
				IModelElement[] methodslotspecials241Childs = methodslotspecials241.getChildren();
				classD242 = ModelTestUtils.getAssertClass( methodslotspecials241Childs, "D" );
			}
			//Class test
			{
			IType classW243;
				IModelElement[] methodslotspecials241Childs = methodslotspecials241.getChildren();
				classW243 = ModelTestUtils.getAssertClass( methodslotspecials241Childs, "W" );
			}
			//Class test
			{
			IType classC1244;
				IModelElement[] methodslotspecials241Childs = methodslotspecials241.getChildren();
				classC1244 = ModelTestUtils.getAssertClass( methodslotspecials241Childs, "C1" );
			}
			//Class test
			{
			IType classC2245;
				IModelElement[] methodslotspecials241Childs = methodslotspecials241.getChildren();
				classC2245 = ModelTestUtils.getAssertClass( methodslotspecials241Childs, "C2" );
			}
		}
		//Function test:dynamics
		{
		IMethod methoddynamics246;
			IModelElement[] moduleChilds = module.getChildren();
			methoddynamics246 = ModelTestUtils.getAssertMethod( moduleChilds, "dynamics", 0 );
			//Class test
			{
			IType classD247;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classD247 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "D" );
			}
			//Class test
			{
			IType classE248;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classE248 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "E" );
			}
			//Class test
			{
			IType classF249;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classF249 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "F" );
			}
			//Class test
			{
			IType classC250;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classC250 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "C" );
			}
			//Function test:mygetattr
			{
			IMethod methodmygetattr251;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				methodmygetattr251 = ModelTestUtils.getAssertMethod( methoddynamics246Childs, "mygetattr", 2 );
				ModelTestUtils.assertParameterNames( methodmygetattr251, new String[] {"self", "name"} );
			}
			//Function test:mysetattr
			{
			IMethod methodmysetattr252;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				methodmysetattr252 = ModelTestUtils.getAssertMethod( methoddynamics246Childs, "mysetattr", 3 );
				ModelTestUtils.assertParameterNames( methodmysetattr252, new String[] {"self", "name", "value"} );
			}
			//Class test
			{
			IType classD253;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classD253 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "D" );
			}
			//Class test
			{
			IType classI254;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classI254 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "I" );
			}
			//Class test
			{
			IType classL255;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classL255 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "L" );
			}
			//Class test
			{
			IType classdynamicmetaclass256;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classdynamicmetaclass256 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "dynamicmetaclass" );
			}
			//Class test
			{
			IType classsomeclass257;
				IModelElement[] methoddynamics246Childs = methoddynamics246.getChildren();
				classsomeclass257 = ModelTestUtils.getAssertClass( methoddynamics246Childs, "someclass" );
			}
		}
		//Function test:errors
		{
		IMethod methoderrors258;
			IModelElement[] moduleChilds = module.getChildren();
			methoderrors258 = ModelTestUtils.getAssertMethod( moduleChilds, "errors", 0 );
			//Class test
			{
			IType classC259;
				IModelElement[] methoderrors258Childs = methoderrors258.getChildren();
				classC259 = ModelTestUtils.getAssertClass( methoderrors258Childs, "C" );
			}
			//Class test
			{
			IType classC260;
				IModelElement[] methoderrors258Childs = methoderrors258.getChildren();
				classC260 = ModelTestUtils.getAssertClass( methoderrors258Childs, "C" );
			}
			//Class test
			{
			IType classClassic261;
				IModelElement[] methoderrors258Childs = methoderrors258.getChildren();
				classClassic261 = ModelTestUtils.getAssertClass( methoderrors258Childs, "Classic" );
			}
			//Class test
			{
			IType classC262;
				IModelElement[] methoderrors258Childs = methoderrors258.getChildren();
				classC262 = ModelTestUtils.getAssertClass( methoderrors258Childs, "C" );
			}
			//Class test
			{
			IType classC263;
				IModelElement[] methoderrors258Childs = methoderrors258.getChildren();
				classC263 = ModelTestUtils.getAssertClass( methoderrors258Childs, "C" );
			}
			//Class test
			{
			IType classC264;
				IModelElement[] methoderrors258Childs = methoderrors258.getChildren();
				classC264 = ModelTestUtils.getAssertClass( methoderrors258Childs, "C" );
			}
		}
		//Function test:classmethods
		{
		IMethod methodclassmethods265;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassmethods265 = ModelTestUtils.getAssertMethod( moduleChilds, "classmethods", 0 );
			//Class test
			{
			IType classC266;
				IModelElement[] methodclassmethods265Childs = methodclassmethods265.getChildren();
				classC266 = ModelTestUtils.getAssertClass( methodclassmethods265Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo267;
					IModelElement[] classC266Childs = classC266.getChildren();
					methodfoo267 = ModelTestUtils.getAssertMethod( classC266Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo267, new String[] {"a"} );
				}
			}
			//Class test
			{
			IType classD268;
				IModelElement[] methodclassmethods265Childs = methodclassmethods265.getChildren();
				classD268 = ModelTestUtils.getAssertClass( methodclassmethods265Childs, "D" );
			}
			//Function test:f
			{
			IMethod methodf269;
				IModelElement[] methodclassmethods265Childs = methodclassmethods265.getChildren();
				methodf269 = ModelTestUtils.getAssertMethod( methodclassmethods265Childs, "f", 2 );
				ModelTestUtils.assertParameterNames( methodf269, new String[] {"cls", "arg"} );
			}
		}
		//Function test:classmethods_in_c
		{
		IMethod methodclassmethods_in_c270;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassmethods_in_c270 = ModelTestUtils.getAssertMethod( moduleChilds, "classmethods_in_c", 0 );
		}
		//Function test:staticmethods
		{
		IMethod methodstaticmethods271;
			IModelElement[] moduleChilds = module.getChildren();
			methodstaticmethods271 = ModelTestUtils.getAssertMethod( moduleChilds, "staticmethods", 0 );
			//Class test
			{
			IType classC272;
				IModelElement[] methodstaticmethods271Childs = methodstaticmethods271.getChildren();
				classC272 = ModelTestUtils.getAssertClass( methodstaticmethods271Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo273;
					IModelElement[] classC272Childs = classC272.getChildren();
					methodfoo273 = ModelTestUtils.getAssertMethod( classC272Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo273, new String[] {"a"} );
				}
			}
			//Class test
			{
			IType classD274;
				IModelElement[] methodstaticmethods271Childs = methodstaticmethods271.getChildren();
				classD274 = ModelTestUtils.getAssertClass( methodstaticmethods271Childs, "D" );
			}
		}
		//Function test:staticmethods_in_c
		{
		IMethod methodstaticmethods_in_c275;
			IModelElement[] moduleChilds = module.getChildren();
			methodstaticmethods_in_c275 = ModelTestUtils.getAssertMethod( moduleChilds, "staticmethods_in_c", 0 );
		}
		//Function test:classic
		{
		IMethod methodclassic276;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassic276 = ModelTestUtils.getAssertMethod( moduleChilds, "classic", 0 );
			//Class test
			{
			IType classC277;
				IModelElement[] methodclassic276Childs = methodclassic276.getChildren();
				classC277 = ModelTestUtils.getAssertClass( methodclassic276Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo278;
					IModelElement[] classC277Childs = classC277.getChildren();
					methodfoo278 = ModelTestUtils.getAssertMethod( classC277Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo278, new String[] {"a"} );
				}
			}
			//Class test
			{
			IType classD279;
				IModelElement[] methodclassic276Childs = methodclassic276.getChildren();
				classD279 = ModelTestUtils.getAssertClass( methodclassic276Childs, "D" );
			}
			//Class test
			{
			IType classE280;
				IModelElement[] methodclassic276Childs = methodclassic276.getChildren();
				classE280 = ModelTestUtils.getAssertClass( methodclassic276Childs, "E" );
			}
		}
		//Function test:compattr
		{
		IMethod methodcompattr281;
			IModelElement[] moduleChilds = module.getChildren();
			methodcompattr281 = ModelTestUtils.getAssertMethod( moduleChilds, "compattr", 0 );
			//Class test
			{
			IType classC282;
				IModelElement[] methodcompattr281Childs = methodcompattr281.getChildren();
				classC282 = ModelTestUtils.getAssertClass( methodcompattr281Childs, "C" );
				//Class test
				{
				IType classcomputed_attribute283;
					IModelElement[] classC282Childs = classC282.getChildren();
					classcomputed_attribute283 = ModelTestUtils.getAssertClass( classC282Childs, "computed_attribute" );
					//Function test:__init__
					{
					IMethod method__init__284;
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						method__init__284 = ModelTestUtils.getAssertMethod( classcomputed_attribute283Childs, "__init__", 4 );
						ModelTestUtils.assertParameterNames( method__init__284, new String[] {"self", "get", "set", "delete"} );
					}
					{
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classcomputed_attribute283Childs, "__get");
					}
					{
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classcomputed_attribute283Childs, "__set");
					}
					{
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classcomputed_attribute283Childs, "__delete");
					}
					//Function test:__get__
					{
					IMethod method__get__286;
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						method__get__286 = ModelTestUtils.getAssertMethod( classcomputed_attribute283Childs, "__get__", 3 );
						ModelTestUtils.assertParameterNames( method__get__286, new String[] {"self", "obj", "type"} );
					}
					//Function test:__set__
					{
					IMethod method__set__287;
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						method__set__287 = ModelTestUtils.getAssertMethod( classcomputed_attribute283Childs, "__set__", 3 );
						ModelTestUtils.assertParameterNames( method__set__287, new String[] {"self", "obj", "value"} );
					}
					//Function test:__delete__
					{
					IMethod method__delete__288;
						IModelElement[] classcomputed_attribute283Childs = classcomputed_attribute283.getChildren();
						method__delete__288 = ModelTestUtils.getAssertMethod( classcomputed_attribute283Childs, "__delete__", 2 );
						ModelTestUtils.assertParameterNames( method__delete__288, new String[] {"self", "obj"} );
					}
				}
				//Function test:__init__
				{
				IMethod method__init__289;
					IModelElement[] classC282Childs = classC282.getChildren();
					method__init__289 = ModelTestUtils.getAssertMethod( classC282Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__289, new String[] {"self"} );
				}
				{
					IModelElement[] classC282Childs = classC282.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC282Childs, "__x");
				}
				//Function test:__get_x
				{
				IMethod method__get_x291;
					IModelElement[] classC282Childs = classC282.getChildren();
					method__get_x291 = ModelTestUtils.getAssertMethod( classC282Childs, "__get_x", 1 );
					ModelTestUtils.assertParameterNames( method__get_x291, new String[] {"self"} );
				}
				{
					IModelElement[] classC282Childs = classC282.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC282Childs, "__x");
				}
				//Function test:__set_x
				{
				IMethod method__set_x293;
					IModelElement[] classC282Childs = classC282.getChildren();
					method__set_x293 = ModelTestUtils.getAssertMethod( classC282Childs, "__set_x", 2 );
					ModelTestUtils.assertParameterNames( method__set_x293, new String[] {"self", "x"} );
				}
				{
					IModelElement[] classC282Childs = classC282.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC282Childs, "__x");
				}
				//Function test:__delete_x
				{
				IMethod method__delete_x295;
					IModelElement[] classC282Childs = classC282.getChildren();
					method__delete_x295 = ModelTestUtils.getAssertMethod( classC282Childs, "__delete_x", 1 );
					ModelTestUtils.assertParameterNames( method__delete_x295, new String[] {"self"} );
				}
			}
		}
		//Function test:newslot
		{
		IMethod methodnewslot296;
			IModelElement[] moduleChilds = module.getChildren();
			methodnewslot296 = ModelTestUtils.getAssertMethod( moduleChilds, "newslot", 0 );
			//Class test
			{
			IType classC297;
				IModelElement[] methodnewslot296Childs = methodnewslot296.getChildren();
				classC297 = ModelTestUtils.getAssertClass( methodnewslot296Childs, "C" );
				//Function test:__new__
				{
				IMethod method__new__298;
					IModelElement[] classC297Childs = classC297.getChildren();
					method__new__298 = ModelTestUtils.getAssertMethod( classC297Childs, "__new__", 1 );
					ModelTestUtils.assertParameterNames( method__new__298, new String[] {"cls"} );
				}
				{
					IModelElement[] classC297Childs = classC297.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC297Childs, "foo");
				}
				//Function test:__init__
				{
				IMethod method__init__300;
					IModelElement[] classC297Childs = classC297.getChildren();
					method__init__300 = ModelTestUtils.getAssertMethod( classC297Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__300, new String[] {"self"} );
				}
				{
					IModelElement[] classC297Childs = classC297.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC297Childs, "foo");
				}
			}
			//Class test
			{
			IType classD302;
				IModelElement[] methodnewslot296Childs = methodnewslot296.getChildren();
				classD302 = ModelTestUtils.getAssertClass( methodnewslot296Childs, "D" );
			}
		}
		//Function test:altmro
		{
		IMethod methodaltmro303;
			IModelElement[] moduleChilds = module.getChildren();
			methodaltmro303 = ModelTestUtils.getAssertMethod( moduleChilds, "altmro", 0 );
			//Class test
			{
			IType classA304;
				IModelElement[] methodaltmro303Childs = methodaltmro303.getChildren();
				classA304 = ModelTestUtils.getAssertClass( methodaltmro303Childs, "A" );
				//Function test:f
				{
				IMethod methodf305;
					IModelElement[] classA304Childs = classA304.getChildren();
					methodf305 = ModelTestUtils.getAssertMethod( classA304Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf305, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB306;
				IModelElement[] methodaltmro303Childs = methodaltmro303.getChildren();
				classB306 = ModelTestUtils.getAssertClass( methodaltmro303Childs, "B" );
			}
			//Class test
			{
			IType classC307;
				IModelElement[] methodaltmro303Childs = methodaltmro303.getChildren();
				classC307 = ModelTestUtils.getAssertClass( methodaltmro303Childs, "C" );
				//Function test:f
				{
				IMethod methodf308;
					IModelElement[] classC307Childs = classC307.getChildren();
					methodf308 = ModelTestUtils.getAssertMethod( classC307Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf308, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD309;
				IModelElement[] methodaltmro303Childs = methodaltmro303.getChildren();
				classD309 = ModelTestUtils.getAssertClass( methodaltmro303Childs, "D" );
			}
			//Class test
			{
			IType classPerverseMetaType310;
				IModelElement[] methodaltmro303Childs = methodaltmro303.getChildren();
				classPerverseMetaType310 = ModelTestUtils.getAssertClass( methodaltmro303Childs, "PerverseMetaType" );
				//Function test:mro
				{
				IMethod methodmro311;
					IModelElement[] classPerverseMetaType310Childs = classPerverseMetaType310.getChildren();
					methodmro311 = ModelTestUtils.getAssertMethod( classPerverseMetaType310Childs, "mro", 1 );
					ModelTestUtils.assertParameterNames( methodmro311, new String[] {"cls"} );
				}
			}
			//Class test
			{
			IType classX312;
				IModelElement[] methodaltmro303Childs = methodaltmro303.getChildren();
				classX312 = ModelTestUtils.getAssertClass( methodaltmro303Childs, "X" );
			}
		}
		//Function test:overloading
		{
		IMethod methodoverloading313;
			IModelElement[] moduleChilds = module.getChildren();
			methodoverloading313 = ModelTestUtils.getAssertMethod( moduleChilds, "overloading", 0 );
			//Class test
			{
			IType classB314;
				IModelElement[] methodoverloading313Childs = methodoverloading313.getChildren();
				classB314 = ModelTestUtils.getAssertClass( methodoverloading313Childs, "B" );
			}
			//Class test
			{
			IType classC315;
				IModelElement[] methodoverloading313Childs = methodoverloading313.getChildren();
				classC315 = ModelTestUtils.getAssertClass( methodoverloading313Childs, "C" );
				//Function test:__getattr__
				{
				IMethod method__getattr__316;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__getattr__316 = ModelTestUtils.getAssertMethod( classC315Childs, "__getattr__", 2 );
					ModelTestUtils.assertParameterNames( method__getattr__316, new String[] {"self", "name"} );
				}
				//Function test:__setattr__
				{
				IMethod method__setattr__317;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__setattr__317 = ModelTestUtils.getAssertMethod( classC315Childs, "__setattr__", 3 );
					ModelTestUtils.assertParameterNames( method__setattr__317, new String[] {"self", "name", "value"} );
				}
				{
					IModelElement[] classC315Childs = classC315.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC315Childs, "setattr");
				}
				//Function test:__delattr__
				{
				IMethod method__delattr__319;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__delattr__319 = ModelTestUtils.getAssertMethod( classC315Childs, "__delattr__", 2 );
					ModelTestUtils.assertParameterNames( method__delattr__319, new String[] {"self", "name"} );
				}
				{
					IModelElement[] classC315Childs = classC315.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC315Childs, "delattr");
				}
				//Function test:__getitem__
				{
				IMethod method__getitem__321;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__getitem__321 = ModelTestUtils.getAssertMethod( classC315Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__321, new String[] {"self", "key"} );
				}
				//Function test:__setitem__
				{
				IMethod method__setitem__322;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__setitem__322 = ModelTestUtils.getAssertMethod( classC315Childs, "__setitem__", 3 );
					ModelTestUtils.assertParameterNames( method__setitem__322, new String[] {"self", "key", "value"} );
				}
				{
					IModelElement[] classC315Childs = classC315.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC315Childs, "setitem");
				}
				//Function test:__delitem__
				{
				IMethod method__delitem__324;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__delitem__324 = ModelTestUtils.getAssertMethod( classC315Childs, "__delitem__", 2 );
					ModelTestUtils.assertParameterNames( method__delitem__324, new String[] {"self", "key"} );
				}
				{
					IModelElement[] classC315Childs = classC315.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC315Childs, "delitem");
				}
				//Function test:__getslice__
				{
				IMethod method__getslice__326;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__getslice__326 = ModelTestUtils.getAssertMethod( classC315Childs, "__getslice__", 3 );
					ModelTestUtils.assertParameterNames( method__getslice__326, new String[] {"self", "i", "j"} );
				}
				//Function test:__setslice__
				{
				IMethod method__setslice__327;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__setslice__327 = ModelTestUtils.getAssertMethod( classC315Childs, "__setslice__", 4 );
					ModelTestUtils.assertParameterNames( method__setslice__327, new String[] {"self", "i", "j", "value"} );
				}
				{
					IModelElement[] classC315Childs = classC315.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC315Childs, "setslice");
				}
				//Function test:__delslice__
				{
				IMethod method__delslice__329;
					IModelElement[] classC315Childs = classC315.getChildren();
					method__delslice__329 = ModelTestUtils.getAssertMethod( classC315Childs, "__delslice__", 3 );
					ModelTestUtils.assertParameterNames( method__delslice__329, new String[] {"self", "i", "j"} );
				}
				{
					IModelElement[] classC315Childs = classC315.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC315Childs, "delslice");
				}
			}
		}
		//Function test:methods
		{
		IMethod methodmethods331;
			IModelElement[] moduleChilds = module.getChildren();
			methodmethods331 = ModelTestUtils.getAssertMethod( moduleChilds, "methods", 0 );
			//Class test
			{
			IType classC332;
				IModelElement[] methodmethods331Childs = methodmethods331.getChildren();
				classC332 = ModelTestUtils.getAssertClass( methodmethods331Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__333;
					IModelElement[] classC332Childs = classC332.getChildren();
					method__init__333 = ModelTestUtils.getAssertMethod( classC332Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__333, new String[] {"self", "x"} );
				}
				{
					IModelElement[] classC332Childs = classC332.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC332Childs, "x");
				}
				//Function test:foo
				{
				IMethod methodfoo335;
					IModelElement[] classC332Childs = classC332.getChildren();
					methodfoo335 = ModelTestUtils.getAssertMethod( classC332Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo335, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD336;
				IModelElement[] methodmethods331Childs = methodmethods331.getChildren();
				classD336 = ModelTestUtils.getAssertClass( methodmethods331Childs, "D" );
			}
			//Class test
			{
			IType classE337;
				IModelElement[] methodmethods331Childs = methodmethods331.getChildren();
				classE337 = ModelTestUtils.getAssertClass( methodmethods331Childs, "E" );
			}
		}
		//Function test:specials
		{
		IMethod methodspecials338;
			IModelElement[] moduleChilds = module.getChildren();
			methodspecials338 = ModelTestUtils.getAssertMethod( moduleChilds, "specials", 0 );
			//Class test
			{
			IType classC339;
				IModelElement[] methodspecials338Childs = methodspecials338.getChildren();
				classC339 = ModelTestUtils.getAssertClass( methodspecials338Childs, "C" );
				//Function test:__getitem__
				{
				IMethod method__getitem__340;
					IModelElement[] classC339Childs = classC339.getChildren();
					method__getitem__340 = ModelTestUtils.getAssertMethod( classC339Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__340, new String[] {"self", "i"} );
				}
			}
			//Class test
			{
			IType classD341;
				IModelElement[] methodspecials338Childs = methodspecials338.getChildren();
				classD341 = ModelTestUtils.getAssertClass( methodspecials338Childs, "D" );
				//Function test:__getitem__
				{
				IMethod method__getitem__342;
					IModelElement[] classD341Childs = classD341.getChildren();
					method__getitem__342 = ModelTestUtils.getAssertMethod( classD341Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__342, new String[] {"self", "i"} );
				}
			}
			//Class test
			{
			IType classProxy343;
				IModelElement[] methodspecials338Childs = methodspecials338.getChildren();
				classProxy343 = ModelTestUtils.getAssertClass( methodspecials338Childs, "Proxy" );
				//Function test:__init__
				{
				IMethod method__init__344;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__init__344 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__344, new String[] {"self", "x"} );
				}
				//Function test:__nonzero__
				{
				IMethod method__nonzero__345;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__nonzero__345 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__nonzero__", 1 );
					ModelTestUtils.assertParameterNames( method__nonzero__345, new String[] {"self"} );
				}
				//Function test:__hash__
				{
				IMethod method__hash__346;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__hash__346 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__346, new String[] {"self"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__347;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__eq__347 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__347, new String[] {"self", "other"} );
				}
				//Function test:__ne__
				{
				IMethod method__ne__348;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__ne__348 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__ne__", 2 );
					ModelTestUtils.assertParameterNames( method__ne__348, new String[] {"self", "other"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__349;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__cmp__349 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__349, new String[] {"self", "other"} );
				}
				//Function test:__str__
				{
				IMethod method__str__350;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__str__350 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__350, new String[] {"self"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__351;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__repr__351 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__351, new String[] {"self"} );
				}
				//Function test:__contains__
				{
				IMethod method__contains__352;
					IModelElement[] classProxy343Childs = classProxy343.getChildren();
					method__contains__352 = ModelTestUtils.getAssertMethod( classProxy343Childs, "__contains__", 2 );
					ModelTestUtils.assertParameterNames( method__contains__352, new String[] {"self", "value"} );
				}
			}
			//Class test
			{
			IType classDProxy353;
				IModelElement[] methodspecials338Childs = methodspecials338.getChildren();
				classDProxy353 = ModelTestUtils.getAssertClass( methodspecials338Childs, "DProxy" );
				//Function test:__init__
				{
				IMethod method__init__354;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__init__354 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__354, new String[] {"self", "x"} );
				}
				//Function test:__nonzero__
				{
				IMethod method__nonzero__355;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__nonzero__355 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__nonzero__", 1 );
					ModelTestUtils.assertParameterNames( method__nonzero__355, new String[] {"self"} );
				}
				//Function test:__hash__
				{
				IMethod method__hash__356;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__hash__356 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__356, new String[] {"self"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__357;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__eq__357 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__357, new String[] {"self", "other"} );
				}
				//Function test:__ne__
				{
				IMethod method__ne__358;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__ne__358 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__ne__", 2 );
					ModelTestUtils.assertParameterNames( method__ne__358, new String[] {"self", "other"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__359;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__cmp__359 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__359, new String[] {"self", "other"} );
				}
				//Function test:__str__
				{
				IMethod method__str__360;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__str__360 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__360, new String[] {"self"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__361;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__repr__361 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__361, new String[] {"self"} );
				}
				//Function test:__contains__
				{
				IMethod method__contains__362;
					IModelElement[] classDProxy353Childs = classDProxy353.getChildren();
					method__contains__362 = ModelTestUtils.getAssertMethod( classDProxy353Childs, "__contains__", 2 );
					ModelTestUtils.assertParameterNames( method__contains__362, new String[] {"self", "value"} );
				}
			}
			//Function test:unsafecmp
			{
			IMethod methodunsafecmp363;
				IModelElement[] methodspecials338Childs = methodspecials338.getChildren();
				methodunsafecmp363 = ModelTestUtils.getAssertMethod( methodspecials338Childs, "unsafecmp", 2 );
				ModelTestUtils.assertParameterNames( methodunsafecmp363, new String[] {"a", "b"} );
			}
			//Class test
			{
			IType classLetter364;
				IModelElement[] methodspecials338Childs = methodspecials338.getChildren();
				classLetter364 = ModelTestUtils.getAssertClass( methodspecials338Childs, "Letter" );
				//Function test:__new__
				{
				IMethod method__new__365;
					IModelElement[] classLetter364Childs = classLetter364.getChildren();
					method__new__365 = ModelTestUtils.getAssertMethod( classLetter364Childs, "__new__", 2 );
					ModelTestUtils.assertParameterNames( method__new__365, new String[] {"cls", "letter"} );
				}
				//Function test:__str__
				{
				IMethod method__str__366;
					IModelElement[] classLetter364Childs = classLetter364.getChildren();
					method__str__366 = ModelTestUtils.getAssertMethod( classLetter364Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__366, new String[] {"self"} );
				}
			}
		}
		//Function test:weakrefs
		{
		IMethod methodweakrefs367;
			IModelElement[] moduleChilds = module.getChildren();
			methodweakrefs367 = ModelTestUtils.getAssertMethod( moduleChilds, "weakrefs", 0 );
			//Class test
			{
			IType classC368;
				IModelElement[] methodweakrefs367Childs = methodweakrefs367.getChildren();
				classC368 = ModelTestUtils.getAssertClass( methodweakrefs367Childs, "C" );
			}
			//Class test
			{
			IType classNoWeak369;
				IModelElement[] methodweakrefs367Childs = methodweakrefs367.getChildren();
				classNoWeak369 = ModelTestUtils.getAssertClass( methodweakrefs367Childs, "NoWeak" );
			}
			//Class test
			{
			IType classWeak370;
				IModelElement[] methodweakrefs367Childs = methodweakrefs367.getChildren();
				classWeak370 = ModelTestUtils.getAssertClass( methodweakrefs367Childs, "Weak" );
			}
		}
		//Function test:properties
		{
		IMethod methodproperties371;
			IModelElement[] moduleChilds = module.getChildren();
			methodproperties371 = ModelTestUtils.getAssertMethod( moduleChilds, "properties", 0 );
			//Class test
			{
			IType classC372;
				IModelElement[] methodproperties371Childs = methodproperties371.getChildren();
				classC372 = ModelTestUtils.getAssertClass( methodproperties371Childs, "C" );
				//Function test:getx
				{
				IMethod methodgetx373;
					IModelElement[] classC372Childs = classC372.getChildren();
					methodgetx373 = ModelTestUtils.getAssertMethod( classC372Childs, "getx", 1 );
					ModelTestUtils.assertParameterNames( methodgetx373, new String[] {"self"} );
				}
				//Function test:setx
				{
				IMethod methodsetx374;
					IModelElement[] classC372Childs = classC372.getChildren();
					methodsetx374 = ModelTestUtils.getAssertMethod( classC372Childs, "setx", 2 );
					ModelTestUtils.assertParameterNames( methodsetx374, new String[] {"self", "value"} );
				}
				{
					IModelElement[] classC372Childs = classC372.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC372Childs, "__x");
				}
				//Function test:delx
				{
				IMethod methoddelx376;
					IModelElement[] classC372Childs = classC372.getChildren();
					methoddelx376 = ModelTestUtils.getAssertMethod( classC372Childs, "delx", 1 );
					ModelTestUtils.assertParameterNames( methoddelx376, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD377;
				IModelElement[] methodproperties371Childs = methodproperties371.getChildren();
				classD377 = ModelTestUtils.getAssertClass( methodproperties371Childs, "D" );
			}
		}
		//Function test:supers
		{
		IMethod methodsupers378;
			IModelElement[] moduleChilds = module.getChildren();
			methodsupers378 = ModelTestUtils.getAssertMethod( moduleChilds, "supers", 0 );
			//Class test
			{
			IType classA379;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classA379 = ModelTestUtils.getAssertClass( methodsupers378Childs, "A" );
				//Function test:meth
				{
				IMethod methodmeth380;
					IModelElement[] classA379Childs = classA379.getChildren();
					methodmeth380 = ModelTestUtils.getAssertMethod( classA379Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth380, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classB381;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classB381 = ModelTestUtils.getAssertClass( methodsupers378Childs, "B" );
				//Function test:__init__
				{
				IMethod method__init__382;
					IModelElement[] classB381Childs = classB381.getChildren();
					method__init__382 = ModelTestUtils.getAssertMethod( classB381Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__382, new String[] {"self"} );
				}
				{
					IModelElement[] classB381Childs = classB381.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classB381Childs, "__super");
				}
				//Function test:meth
				{
				IMethod methodmeth384;
					IModelElement[] classB381Childs = classB381.getChildren();
					methodmeth384 = ModelTestUtils.getAssertMethod( classB381Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth384, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classC385;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classC385 = ModelTestUtils.getAssertClass( methodsupers378Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth386;
					IModelElement[] classC385Childs = classC385.getChildren();
					methodmeth386 = ModelTestUtils.getAssertMethod( classC385Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth386, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classD387;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classD387 = ModelTestUtils.getAssertClass( methodsupers378Childs, "D" );
				//Function test:meth
				{
				IMethod methodmeth388;
					IModelElement[] classD387Childs = classD387.getChildren();
					methodmeth388 = ModelTestUtils.getAssertMethod( classD387Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth388, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classmysuper389;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classmysuper389 = ModelTestUtils.getAssertClass( methodsupers378Childs, "mysuper" );
				//Function test:__init__
				{
				IMethod method__init__390;
					IModelElement[] classmysuper389Childs = classmysuper389.getChildren();
					method__init__390 = ModelTestUtils.getAssertMethod( classmysuper389Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__390, new String[] {"self", "args"} );
				}
			}
			//Class test
			{
			IType classE391;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classE391 = ModelTestUtils.getAssertClass( methodsupers378Childs, "E" );
				//Function test:meth
				{
				IMethod methodmeth392;
					IModelElement[] classE391Childs = classE391.getChildren();
					methodmeth392 = ModelTestUtils.getAssertMethod( classE391Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth392, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classF393;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classF393 = ModelTestUtils.getAssertClass( methodsupers378Childs, "F" );
				//Function test:meth
				{
				IMethod methodmeth394;
					IModelElement[] classF393Childs = classF393.getChildren();
					methodmeth394 = ModelTestUtils.getAssertMethod( classF393Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth394, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classDDbase395;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classDDbase395 = ModelTestUtils.getAssertClass( methodsupers378Childs, "DDbase" );
				//Function test:getx
				{
				IMethod methodgetx396;
					IModelElement[] classDDbase395Childs = classDDbase395.getChildren();
					methodgetx396 = ModelTestUtils.getAssertMethod( classDDbase395Childs, "getx", 1 );
					ModelTestUtils.assertParameterNames( methodgetx396, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classDDsub397;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classDDsub397 = ModelTestUtils.getAssertClass( methodsupers378Childs, "DDsub" );
				//Function test:getx
				{
				IMethod methodgetx398;
					IModelElement[] classDDsub397Childs = classDDsub397.getChildren();
					methodgetx398 = ModelTestUtils.getAssertMethod( classDDsub397Childs, "getx", 1 );
					ModelTestUtils.assertParameterNames( methodgetx398, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classBase399;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classBase399 = ModelTestUtils.getAssertClass( methodsupers378Childs, "Base" );
			}
			//Class test
			{
			IType classSub400;
				IModelElement[] methodsupers378Childs = methodsupers378.getChildren();
				classSub400 = ModelTestUtils.getAssertClass( methodsupers378Childs, "Sub" );
				//Function test:test
				{
				IMethod methodtest401;
					IModelElement[] classSub400Childs = classSub400.getChildren();
					methodtest401 = ModelTestUtils.getAssertMethod( classSub400Childs, "test", 1 );
					ModelTestUtils.assertParameterNames( methodtest401, new String[] {"klass"} );
				}
			}
		}
		//Function test:inherits
		{
		IMethod methodinherits402;
			IModelElement[] moduleChilds = module.getChildren();
			methodinherits402 = ModelTestUtils.getAssertMethod( moduleChilds, "inherits", 0 );
			//Class test
			{
			IType classhexint403;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classhexint403 = ModelTestUtils.getAssertClass( methodinherits402Childs, "hexint" );
				//Function test:__repr__
				{
				IMethod method__repr__404;
					IModelElement[] classhexint403Childs = classhexint403.getChildren();
					method__repr__404 = ModelTestUtils.getAssertMethod( classhexint403Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__404, new String[] {"self"} );
				}
				//Function test:__add__
				{
				IMethod method__add__405;
					IModelElement[] classhexint403Childs = classhexint403.getChildren();
					method__add__405 = ModelTestUtils.getAssertMethod( classhexint403Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__405, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classoctlong406;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classoctlong406 = ModelTestUtils.getAssertClass( methodinherits402Childs, "octlong" );
				//Function test:__str__
				{
				IMethod method__str__407;
					IModelElement[] classoctlong406Childs = classoctlong406.getChildren();
					method__str__407 = ModelTestUtils.getAssertMethod( classoctlong406Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__407, new String[] {"self"} );
				}
				//Function test:__add__
				{
				IMethod method__add__408;
					IModelElement[] classoctlong406Childs = classoctlong406.getChildren();
					method__add__408 = ModelTestUtils.getAssertMethod( classoctlong406Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__408, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classlongclone409;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classlongclone409 = ModelTestUtils.getAssertClass( methodinherits402Childs, "longclone" );
			}
			//Class test
			{
			IType classprecfloat410;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classprecfloat410 = ModelTestUtils.getAssertClass( methodinherits402Childs, "precfloat" );
				//Function test:__init__
				{
				IMethod method__init__411;
					IModelElement[] classprecfloat410Childs = classprecfloat410.getChildren();
					method__init__411 = ModelTestUtils.getAssertMethod( classprecfloat410Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__411, new String[] {"self", "value", "prec"} );
				}
				{
					IModelElement[] classprecfloat410Childs = classprecfloat410.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classprecfloat410Childs, "prec");
				}
				//Function test:__repr__
				{
				IMethod method__repr__413;
					IModelElement[] classprecfloat410Childs = classprecfloat410.getChildren();
					method__repr__413 = ModelTestUtils.getAssertMethod( classprecfloat410Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__413, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classmadcomplex414;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classmadcomplex414 = ModelTestUtils.getAssertClass( methodinherits402Childs, "madcomplex" );
				//Function test:__repr__
				{
				IMethod method__repr__415;
					IModelElement[] classmadcomplex414Childs = classmadcomplex414.getChildren();
					method__repr__415 = ModelTestUtils.getAssertMethod( classmadcomplex414Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__415, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classmadtuple416;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classmadtuple416 = ModelTestUtils.getAssertClass( methodinherits402Childs, "madtuple" );
				//Function test:rev
				{
				IMethod methodrev417;
					IModelElement[] classmadtuple416Childs = classmadtuple416.getChildren();
					methodrev417 = ModelTestUtils.getAssertMethod( classmadtuple416Childs, "rev", 1 );
					ModelTestUtils.assertParameterNames( methodrev417, new String[] {"self"} );
				}
				{
					IModelElement[] classmadtuple416Childs = classmadtuple416.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmadtuple416Childs, "_rev");
				}
			}
			//Class test
			{
			IType classmadstring419;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classmadstring419 = ModelTestUtils.getAssertClass( methodinherits402Childs, "madstring" );
				//Function test:rev
				{
				IMethod methodrev420;
					IModelElement[] classmadstring419Childs = classmadstring419.getChildren();
					methodrev420 = ModelTestUtils.getAssertMethod( classmadstring419Childs, "rev", 1 );
					ModelTestUtils.assertParameterNames( methodrev420, new String[] {"self"} );
				}
				{
					IModelElement[] classmadstring419Childs = classmadstring419.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmadstring419Childs, "_rev");
				}
			}
			//Class test
			{
			IType classmadunicode422;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classmadunicode422 = ModelTestUtils.getAssertClass( methodinherits402Childs, "madunicode" );
				//Function test:rev
				{
				IMethod methodrev423;
					IModelElement[] classmadunicode422Childs = classmadunicode422.getChildren();
					methodrev423 = ModelTestUtils.getAssertMethod( classmadunicode422Childs, "rev", 1 );
					ModelTestUtils.assertParameterNames( methodrev423, new String[] {"self"} );
				}
				{
					IModelElement[] classmadunicode422Childs = classmadunicode422.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmadunicode422Childs, "_rev");
				}
			}
			//Class test
			{
			IType classsublist425;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classsublist425 = ModelTestUtils.getAssertClass( methodinherits402Childs, "sublist" );
			}
			//Class test
			{
			IType classCountedInput426;
				IModelElement[] methodinherits402Childs = methodinherits402.getChildren();
				classCountedInput426 = ModelTestUtils.getAssertClass( methodinherits402Childs, "CountedInput" );
				//Function test:readline
				{
				IMethod methodreadline427;
					IModelElement[] classCountedInput426Childs = classCountedInput426.getChildren();
					methodreadline427 = ModelTestUtils.getAssertMethod( classCountedInput426Childs, "readline", 1 );
					ModelTestUtils.assertParameterNames( methodreadline427, new String[] {"self"} );
				}
				{
					IModelElement[] classCountedInput426Childs = classCountedInput426.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classCountedInput426Childs, "ateof");
				}
			}
		}
		//Function test:keywords
		{
		IMethod methodkeywords429;
			IModelElement[] moduleChilds = module.getChildren();
			methodkeywords429 = ModelTestUtils.getAssertMethod( moduleChilds, "keywords", 0 );
		}
		//Function test:restricted
		{
		IMethod methodrestricted430;
			IModelElement[] moduleChilds = module.getChildren();
			methodrestricted430 = ModelTestUtils.getAssertMethod( moduleChilds, "restricted", 0 );
		}
		//Function test:str_subclass_as_dict_key
		{
		IMethod methodstr_subclass_as_dict_key431;
			IModelElement[] moduleChilds = module.getChildren();
			methodstr_subclass_as_dict_key431 = ModelTestUtils.getAssertMethod( moduleChilds, "str_subclass_as_dict_key", 0 );
			//Class test
			{
			IType classcistr432;
				IModelElement[] methodstr_subclass_as_dict_key431Childs = methodstr_subclass_as_dict_key431.getChildren();
				classcistr432 = ModelTestUtils.getAssertClass( methodstr_subclass_as_dict_key431Childs, "cistr" );
				//Function test:__init__
				{
				IMethod method__init__433;
					IModelElement[] classcistr432Childs = classcistr432.getChildren();
					method__init__433 = ModelTestUtils.getAssertMethod( classcistr432Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__433, new String[] {"self", "value"} );
				}
				{
					IModelElement[] classcistr432Childs = classcistr432.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classcistr432Childs, "canonical");
				}
				{
					IModelElement[] classcistr432Childs = classcistr432.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classcistr432Childs, "hashcode");
				}
				//Function test:__eq__
				{
				IMethod method__eq__435;
					IModelElement[] classcistr432Childs = classcistr432.getChildren();
					method__eq__435 = ModelTestUtils.getAssertMethod( classcistr432Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__435, new String[] {"self", "other"} );
				}
				//Function test:__hash__
				{
				IMethod method__hash__436;
					IModelElement[] classcistr432Childs = classcistr432.getChildren();
					method__hash__436 = ModelTestUtils.getAssertMethod( classcistr432Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__436, new String[] {"self"} );
				}
			}
		}
		//Function test:classic_comparisons
		{
		IMethod methodclassic_comparisons437;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassic_comparisons437 = ModelTestUtils.getAssertMethod( moduleChilds, "classic_comparisons", 0 );
			//Class test
			{
			IType classclassic438;
				IModelElement[] methodclassic_comparisons437Childs = methodclassic_comparisons437.getChildren();
				classclassic438 = ModelTestUtils.getAssertClass( methodclassic_comparisons437Childs, "classic" );
			}
			//Class test
			{
			IType classC439;
				IModelElement[] methodclassic_comparisons437Childs = methodclassic_comparisons437.getChildren();
				classC439 = ModelTestUtils.getAssertClass( methodclassic_comparisons437Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__440;
					IModelElement[] classC439Childs = classC439.getChildren();
					method__init__440 = ModelTestUtils.getAssertMethod( classC439Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__440, new String[] {"self", "value"} );
				}
				{
					IModelElement[] classC439Childs = classC439.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC439Childs, "value");
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__442;
					IModelElement[] classC439Childs = classC439.getChildren();
					method__cmp__442 = ModelTestUtils.getAssertMethod( classC439Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__442, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:rich_comparisons
		{
		IMethod methodrich_comparisons443;
			IModelElement[] moduleChilds = module.getChildren();
			methodrich_comparisons443 = ModelTestUtils.getAssertMethod( moduleChilds, "rich_comparisons", 0 );
			//Class test
			{
			IType classZ444;
				IModelElement[] methodrich_comparisons443Childs = methodrich_comparisons443.getChildren();
				classZ444 = ModelTestUtils.getAssertClass( methodrich_comparisons443Childs, "Z" );
			}
			//Class test
			{
			IType classZZ445;
				IModelElement[] methodrich_comparisons443Childs = methodrich_comparisons443.getChildren();
				classZZ445 = ModelTestUtils.getAssertClass( methodrich_comparisons443Childs, "ZZ" );
				//Function test:__eq__
				{
				IMethod method__eq__446;
					IModelElement[] classZZ445Childs = classZZ445.getChildren();
					method__eq__446 = ModelTestUtils.getAssertMethod( classZZ445Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__446, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classclassic447;
				IModelElement[] methodrich_comparisons443Childs = methodrich_comparisons443.getChildren();
				classclassic447 = ModelTestUtils.getAssertClass( methodrich_comparisons443Childs, "classic" );
			}
			//Class test
			{
			IType classC448;
				IModelElement[] methodrich_comparisons443Childs = methodrich_comparisons443.getChildren();
				classC448 = ModelTestUtils.getAssertClass( methodrich_comparisons443Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__449;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__init__449 = ModelTestUtils.getAssertMethod( classC448Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__449, new String[] {"self", "value"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__450;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__cmp__450 = ModelTestUtils.getAssertMethod( classC448Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__450, new String[] {"self", "other"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__451;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__eq__451 = ModelTestUtils.getAssertMethod( classC448Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__451, new String[] {"self", "other"} );
				}
				//Function test:__ne__
				{
				IMethod method__ne__452;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__ne__452 = ModelTestUtils.getAssertMethod( classC448Childs, "__ne__", 2 );
					ModelTestUtils.assertParameterNames( method__ne__452, new String[] {"self", "other"} );
				}
				//Function test:__lt__
				{
				IMethod method__lt__453;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__lt__453 = ModelTestUtils.getAssertMethod( classC448Childs, "__lt__", 2 );
					ModelTestUtils.assertParameterNames( method__lt__453, new String[] {"self", "other"} );
				}
				//Function test:__le__
				{
				IMethod method__le__454;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__le__454 = ModelTestUtils.getAssertMethod( classC448Childs, "__le__", 2 );
					ModelTestUtils.assertParameterNames( method__le__454, new String[] {"self", "other"} );
				}
				//Function test:__gt__
				{
				IMethod method__gt__455;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__gt__455 = ModelTestUtils.getAssertMethod( classC448Childs, "__gt__", 2 );
					ModelTestUtils.assertParameterNames( method__gt__455, new String[] {"self", "other"} );
				}
				//Function test:__ge__
				{
				IMethod method__ge__456;
					IModelElement[] classC448Childs = classC448.getChildren();
					method__ge__456 = ModelTestUtils.getAssertMethod( classC448Childs, "__ge__", 2 );
					ModelTestUtils.assertParameterNames( method__ge__456, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:coercions
		{
		IMethod methodcoercions457;
			IModelElement[] moduleChilds = module.getChildren();
			methodcoercions457 = ModelTestUtils.getAssertMethod( moduleChilds, "coercions", 0 );
			//Class test
			{
			IType classI458;
				IModelElement[] methodcoercions457Childs = methodcoercions457.getChildren();
				classI458 = ModelTestUtils.getAssertClass( methodcoercions457Childs, "I" );
			}
			//Class test
			{
			IType classL459;
				IModelElement[] methodcoercions457Childs = methodcoercions457.getChildren();
				classL459 = ModelTestUtils.getAssertClass( methodcoercions457Childs, "L" );
			}
			//Class test
			{
			IType classF460;
				IModelElement[] methodcoercions457Childs = methodcoercions457.getChildren();
				classF460 = ModelTestUtils.getAssertClass( methodcoercions457Childs, "F" );
			}
			//Class test
			{
			IType classC461;
				IModelElement[] methodcoercions457Childs = methodcoercions457.getChildren();
				classC461 = ModelTestUtils.getAssertClass( methodcoercions457Childs, "C" );
			}
		}
		//Function test:descrdoc
		{
		IMethod methoddescrdoc462;
			IModelElement[] moduleChilds = module.getChildren();
			methoddescrdoc462 = ModelTestUtils.getAssertMethod( moduleChilds, "descrdoc", 0 );
			//Function test:check
			{
			IMethod methodcheck463;
				IModelElement[] methoddescrdoc462Childs = methoddescrdoc462.getChildren();
				methodcheck463 = ModelTestUtils.getAssertMethod( methoddescrdoc462Childs, "check", 2 );
				ModelTestUtils.assertParameterNames( methodcheck463, new String[] {"descr", "what"} );
			}
		}
		//Function test:setclass
		{
		IMethod methodsetclass464;
			IModelElement[] moduleChilds = module.getChildren();
			methodsetclass464 = ModelTestUtils.getAssertMethod( moduleChilds, "setclass", 0 );
			//Class test
			{
			IType classC465;
				IModelElement[] methodsetclass464Childs = methodsetclass464.getChildren();
				classC465 = ModelTestUtils.getAssertClass( methodsetclass464Childs, "C" );
			}
			//Class test
			{
			IType classD466;
				IModelElement[] methodsetclass464Childs = methodsetclass464.getChildren();
				classD466 = ModelTestUtils.getAssertClass( methodsetclass464Childs, "D" );
			}
			//Class test
			{
			IType classE467;
				IModelElement[] methodsetclass464Childs = methodsetclass464.getChildren();
				classE467 = ModelTestUtils.getAssertClass( methodsetclass464Childs, "E" );
			}
			//Class test
			{
			IType classF468;
				IModelElement[] methodsetclass464Childs = methodsetclass464.getChildren();
				classF468 = ModelTestUtils.getAssertClass( methodsetclass464Childs, "F" );
			}
			//Function test:cant
			{
			IMethod methodcant469;
				IModelElement[] methodsetclass464Childs = methodsetclass464.getChildren();
				methodcant469 = ModelTestUtils.getAssertMethod( methodsetclass464Childs, "cant", 2 );
				ModelTestUtils.assertParameterNames( methodcant469, new String[] {"x", "C"} );
			}
			//Class test
			{
			IType classInt470;
				IModelElement[] methodsetclass464Childs = methodsetclass464.getChildren();
				classInt470 = ModelTestUtils.getAssertClass( methodsetclass464Childs, "Int" );
			}
		}
		//Function test:setdict
		{
		IMethod methodsetdict471;
			IModelElement[] moduleChilds = module.getChildren();
			methodsetdict471 = ModelTestUtils.getAssertMethod( moduleChilds, "setdict", 0 );
			//Class test
			{
			IType classC472;
				IModelElement[] methodsetdict471Childs = methodsetdict471.getChildren();
				classC472 = ModelTestUtils.getAssertClass( methodsetdict471Childs, "C" );
			}
			//Function test:cant
			{
			IMethod methodcant473;
				IModelElement[] methodsetdict471Childs = methodsetdict471.getChildren();
				methodcant473 = ModelTestUtils.getAssertMethod( methodsetdict471Childs, "cant", 2 );
				ModelTestUtils.assertParameterNames( methodcant473, new String[] {"x", "dict"} );
			}
		}
		//Function test:pickles
		{
		IMethod methodpickles474;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickles474 = ModelTestUtils.getAssertMethod( moduleChilds, "pickles", 0 );
			//Function test:sorteditems
			{
			IMethod methodsorteditems475;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				methodsorteditems475 = ModelTestUtils.getAssertMethod( methodpickles474Childs, "sorteditems", 1 );
				ModelTestUtils.assertParameterNames( methodsorteditems475, new String[] {"d"} );
			}
			//Class test
			{
			IType classC476;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				classC476 = ModelTestUtils.getAssertClass( methodpickles474Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__477;
					IModelElement[] classC476Childs = classC476.getChildren();
					method__init__477 = ModelTestUtils.getAssertMethod( classC476Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__477, new String[] {"self", "a", "b"} );
				}
				{
					IModelElement[] classC476Childs = classC476.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC476Childs, "a");
				}
				{
					IModelElement[] classC476Childs = classC476.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC476Childs, "b");
				}
				//Function test:__repr__
				{
				IMethod method__repr__479;
					IModelElement[] classC476Childs = classC476.getChildren();
					method__repr__479 = ModelTestUtils.getAssertMethod( classC476Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__479, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC1480;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				classC1480 = ModelTestUtils.getAssertClass( methodpickles474Childs, "C1" );
				//Function test:__new__
				{
				IMethod method__new__481;
					IModelElement[] classC1480Childs = classC1480.getChildren();
					method__new__481 = ModelTestUtils.getAssertMethod( classC1480Childs, "__new__", 3 );
					ModelTestUtils.assertParameterNames( method__new__481, new String[] {"cls", "a", "b"} );
				}
				//Function test:__getnewargs__
				{
				IMethod method__getnewargs__482;
					IModelElement[] classC1480Childs = classC1480.getChildren();
					method__getnewargs__482 = ModelTestUtils.getAssertMethod( classC1480Childs, "__getnewargs__", 1 );
					ModelTestUtils.assertParameterNames( method__getnewargs__482, new String[] {"self"} );
				}
				//Function test:__init__
				{
				IMethod method__init__483;
					IModelElement[] classC1480Childs = classC1480.getChildren();
					method__init__483 = ModelTestUtils.getAssertMethod( classC1480Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__483, new String[] {"self", "a", "b"} );
				}
				{
					IModelElement[] classC1480Childs = classC1480.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC1480Childs, "a");
				}
				{
					IModelElement[] classC1480Childs = classC1480.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC1480Childs, "b");
				}
				//Function test:__repr__
				{
				IMethod method__repr__485;
					IModelElement[] classC1480Childs = classC1480.getChildren();
					method__repr__485 = ModelTestUtils.getAssertMethod( classC1480Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__485, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC2486;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				classC2486 = ModelTestUtils.getAssertClass( methodpickles474Childs, "C2" );
				//Function test:__new__
				{
				IMethod method__new__487;
					IModelElement[] classC2486Childs = classC2486.getChildren();
					method__new__487 = ModelTestUtils.getAssertMethod( classC2486Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__487, new String[] {"cls", "a", "b", "val"} );
				}
				//Function test:__getnewargs__
				{
				IMethod method__getnewargs__488;
					IModelElement[] classC2486Childs = classC2486.getChildren();
					method__getnewargs__488 = ModelTestUtils.getAssertMethod( classC2486Childs, "__getnewargs__", 1 );
					ModelTestUtils.assertParameterNames( method__getnewargs__488, new String[] {"self"} );
				}
				//Function test:__init__
				{
				IMethod method__init__489;
					IModelElement[] classC2486Childs = classC2486.getChildren();
					method__init__489 = ModelTestUtils.getAssertMethod( classC2486Childs, "__init__", 4 );
					ModelTestUtils.assertParameterNames( method__init__489, new String[] {"self", "a", "b", "val"} );
				}
				{
					IModelElement[] classC2486Childs = classC2486.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC2486Childs, "a");
				}
				{
					IModelElement[] classC2486Childs = classC2486.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC2486Childs, "b");
				}
				//Function test:__repr__
				{
				IMethod method__repr__491;
					IModelElement[] classC2486Childs = classC2486.getChildren();
					method__repr__491 = ModelTestUtils.getAssertMethod( classC2486Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__491, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC3492;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				classC3492 = ModelTestUtils.getAssertClass( methodpickles474Childs, "C3" );
				//Function test:__init__
				{
				IMethod method__init__493;
					IModelElement[] classC3492Childs = classC3492.getChildren();
					method__init__493 = ModelTestUtils.getAssertMethod( classC3492Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__493, new String[] {"self", "foo"} );
				}
				{
					IModelElement[] classC3492Childs = classC3492.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC3492Childs, "foo");
				}
				//Function test:__getstate__
				{
				IMethod method__getstate__495;
					IModelElement[] classC3492Childs = classC3492.getChildren();
					method__getstate__495 = ModelTestUtils.getAssertMethod( classC3492Childs, "__getstate__", 1 );
					ModelTestUtils.assertParameterNames( method__getstate__495, new String[] {"self"} );
				}
				//Function test:__setstate__
				{
				IMethod method__setstate__496;
					IModelElement[] classC3492Childs = classC3492.getChildren();
					method__setstate__496 = ModelTestUtils.getAssertMethod( classC3492Childs, "__setstate__", 2 );
					ModelTestUtils.assertParameterNames( method__setstate__496, new String[] {"self", "foo"} );
				}
				{
					IModelElement[] classC3492Childs = classC3492.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC3492Childs, "foo");
				}
			}
			//Class test
			{
			IType classC4classic498;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				classC4classic498 = ModelTestUtils.getAssertClass( methodpickles474Childs, "C4classic" );
			}
			//Class test
			{
			IType classC4499;
				IModelElement[] methodpickles474Childs = methodpickles474.getChildren();
				classC4499 = ModelTestUtils.getAssertClass( methodpickles474Childs, "C4" );
			}
		}
		//Function test:pickleslots
		{
		IMethod methodpickleslots500;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickleslots500 = ModelTestUtils.getAssertMethod( moduleChilds, "pickleslots", 0 );
			//Class test
			{
			IType classB501;
				IModelElement[] methodpickleslots500Childs = methodpickleslots500.getChildren();
				classB501 = ModelTestUtils.getAssertClass( methodpickleslots500Childs, "B" );
			}
			//Class test
			{
			IType classC502;
				IModelElement[] methodpickleslots500Childs = methodpickleslots500.getChildren();
				classC502 = ModelTestUtils.getAssertClass( methodpickleslots500Childs, "C" );
			}
			//Class test
			{
			IType classD503;
				IModelElement[] methodpickleslots500Childs = methodpickleslots500.getChildren();
				classD503 = ModelTestUtils.getAssertClass( methodpickleslots500Childs, "D" );
			}
			//Class test
			{
			IType classC504;
				IModelElement[] methodpickleslots500Childs = methodpickleslots500.getChildren();
				classC504 = ModelTestUtils.getAssertClass( methodpickleslots500Childs, "C" );
				//Function test:__getstate__
				{
				IMethod method__getstate__505;
					IModelElement[] classC504Childs = classC504.getChildren();
					method__getstate__505 = ModelTestUtils.getAssertMethod( classC504Childs, "__getstate__", 1 );
					ModelTestUtils.assertParameterNames( method__getstate__505, new String[] {"self"} );
				}
				//Function test:__setstate__
				{
				IMethod method__setstate__506;
					IModelElement[] classC504Childs = classC504.getChildren();
					method__setstate__506 = ModelTestUtils.getAssertMethod( classC504Childs, "__setstate__", 2 );
					ModelTestUtils.assertParameterNames( method__setstate__506, new String[] {"self", "d"} );
				}
			}
			//Class test
			{
			IType classD507;
				IModelElement[] methodpickleslots500Childs = methodpickleslots500.getChildren();
				classD507 = ModelTestUtils.getAssertClass( methodpickleslots500Childs, "D" );
			}
			//Class test
			{
			IType classE508;
				IModelElement[] methodpickleslots500Childs = methodpickleslots500.getChildren();
				classE508 = ModelTestUtils.getAssertClass( methodpickleslots500Childs, "E" );
			}
		}
		//Function test:copies
		{
		IMethod methodcopies509;
			IModelElement[] moduleChilds = module.getChildren();
			methodcopies509 = ModelTestUtils.getAssertMethod( moduleChilds, "copies", 0 );
			//Class test
			{
			IType classC510;
				IModelElement[] methodcopies509Childs = methodcopies509.getChildren();
				classC510 = ModelTestUtils.getAssertClass( methodcopies509Childs, "C" );
			}
		}
		//Function test:binopoverride
		{
		IMethod methodbinopoverride511;
			IModelElement[] moduleChilds = module.getChildren();
			methodbinopoverride511 = ModelTestUtils.getAssertMethod( moduleChilds, "binopoverride", 0 );
			//Class test
			{
			IType classI512;
				IModelElement[] methodbinopoverride511Childs = methodbinopoverride511.getChildren();
				classI512 = ModelTestUtils.getAssertClass( methodbinopoverride511Childs, "I" );
				//Function test:__repr__
				{
				IMethod method__repr__513;
					IModelElement[] classI512Childs = classI512.getChildren();
					method__repr__513 = ModelTestUtils.getAssertMethod( classI512Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__513, new String[] {"self"} );
				}
				//Function test:__add__
				{
				IMethod method__add__514;
					IModelElement[] classI512Childs = classI512.getChildren();
					method__add__514 = ModelTestUtils.getAssertMethod( classI512Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__514, new String[] {"self", "other"} );
				}
				//Function test:__pow__
				{
				IMethod method__pow__515;
					IModelElement[] classI512Childs = classI512.getChildren();
					method__pow__515 = ModelTestUtils.getAssertMethod( classI512Childs, "__pow__", 3 );
					ModelTestUtils.assertParameterNames( method__pow__515, new String[] {"self", "other", "mod"} );
				}
				//Function test:__rpow__
				{
				IMethod method__rpow__516;
					IModelElement[] classI512Childs = classI512.getChildren();
					method__rpow__516 = ModelTestUtils.getAssertMethod( classI512Childs, "__rpow__", 3 );
					ModelTestUtils.assertParameterNames( method__rpow__516, new String[] {"self", "other", "mod"} );
				}
			}
			//Class test
			{
			IType classS517;
				IModelElement[] methodbinopoverride511Childs = methodbinopoverride511.getChildren();
				classS517 = ModelTestUtils.getAssertClass( methodbinopoverride511Childs, "S" );
				//Function test:__eq__
				{
				IMethod method__eq__518;
					IModelElement[] classS517Childs = classS517.getChildren();
					method__eq__518 = ModelTestUtils.getAssertMethod( classS517Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__518, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:subclasspropagation
		{
		IMethod methodsubclasspropagation519;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubclasspropagation519 = ModelTestUtils.getAssertMethod( moduleChilds, "subclasspropagation", 0 );
			//Class test
			{
			IType classA520;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				classA520 = ModelTestUtils.getAssertClass( methodsubclasspropagation519Childs, "A" );
			}
			//Class test
			{
			IType classB521;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				classB521 = ModelTestUtils.getAssertClass( methodsubclasspropagation519Childs, "B" );
			}
			//Class test
			{
			IType classC522;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				classC522 = ModelTestUtils.getAssertClass( methodsubclasspropagation519Childs, "C" );
			}
			//Class test
			{
			IType classD523;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				classD523 = ModelTestUtils.getAssertClass( methodsubclasspropagation519Childs, "D" );
			}
			//Function test:__getattribute__
			{
			IMethod method__getattribute__524;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				method__getattribute__524 = ModelTestUtils.getAssertMethod( methodsubclasspropagation519Childs, "__getattribute__", 2 );
				ModelTestUtils.assertParameterNames( method__getattribute__524, new String[] {"self", "name"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__525;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				method__getattr__525 = ModelTestUtils.getAssertMethod( methodsubclasspropagation519Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__525, new String[] {"self", "name"} );
			}
			//Class test
			{
			IType classA526;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				classA526 = ModelTestUtils.getAssertClass( methodsubclasspropagation519Childs, "A" );
			}
			//Class test
			{
			IType classB527;
				IModelElement[] methodsubclasspropagation519Childs = methodsubclasspropagation519.getChildren();
				classB527 = ModelTestUtils.getAssertClass( methodsubclasspropagation519Childs, "B" );
			}
		}
		//Function test:buffer_inherit
		{
		IMethod methodbuffer_inherit528;
			IModelElement[] moduleChilds = module.getChildren();
			methodbuffer_inherit528 = ModelTestUtils.getAssertMethod( moduleChilds, "buffer_inherit", 0 );
			//Class test
			{
			IType classMyStr529;
				IModelElement[] methodbuffer_inherit528Childs = methodbuffer_inherit528.getChildren();
				classMyStr529 = ModelTestUtils.getAssertClass( methodbuffer_inherit528Childs, "MyStr" );
			}
			//Class test
			{
			IType classMyUni530;
				IModelElement[] methodbuffer_inherit528Childs = methodbuffer_inherit528.getChildren();
				classMyUni530 = ModelTestUtils.getAssertClass( methodbuffer_inherit528Childs, "MyUni" );
			}
			//Class test
			{
			IType classMyInt531;
				IModelElement[] methodbuffer_inherit528Childs = methodbuffer_inherit528.getChildren();
				classMyInt531 = ModelTestUtils.getAssertClass( methodbuffer_inherit528Childs, "MyInt" );
			}
		}
		//Function test:str_of_str_subclass
		{
		IMethod methodstr_of_str_subclass532;
			IModelElement[] moduleChilds = module.getChildren();
			methodstr_of_str_subclass532 = ModelTestUtils.getAssertMethod( moduleChilds, "str_of_str_subclass", 0 );
			//Class test
			{
			IType classoctetstring533;
				IModelElement[] methodstr_of_str_subclass532Childs = methodstr_of_str_subclass532.getChildren();
				classoctetstring533 = ModelTestUtils.getAssertClass( methodstr_of_str_subclass532Childs, "octetstring" );
				//Function test:__str__
				{
				IMethod method__str__534;
					IModelElement[] classoctetstring533Childs = classoctetstring533.getChildren();
					method__str__534 = ModelTestUtils.getAssertMethod( classoctetstring533Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__534, new String[] {"self"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__535;
					IModelElement[] classoctetstring533Childs = classoctetstring533.getChildren();
					method__repr__535 = ModelTestUtils.getAssertMethod( classoctetstring533Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__535, new String[] {"self"} );
				}
			}
		}
		//Function test:kwdargs
		{
		IMethod methodkwdargs536;
			IModelElement[] moduleChilds = module.getChildren();
			methodkwdargs536 = ModelTestUtils.getAssertMethod( moduleChilds, "kwdargs", 0 );
			//Function test:f
			{
			IMethod methodf537;
				IModelElement[] methodkwdargs536Childs = methodkwdargs536.getChildren();
				methodf537 = ModelTestUtils.getAssertMethod( methodkwdargs536Childs, "f", 1 );
				ModelTestUtils.assertParameterNames( methodf537, new String[] {"a"} );
			}
		}
		//Function test:delhook
		{
		IMethod methoddelhook538;
			IModelElement[] moduleChilds = module.getChildren();
			methoddelhook538 = ModelTestUtils.getAssertMethod( moduleChilds, "delhook", 0 );
			//Class test
			{
			IType classC539;
				IModelElement[] methoddelhook538Childs = methoddelhook538.getChildren();
				classC539 = ModelTestUtils.getAssertClass( methoddelhook538Childs, "C" );
				//Function test:__del__
				{
				IMethod method__del__540;
					IModelElement[] classC539Childs = classC539.getChildren();
					method__del__540 = ModelTestUtils.getAssertMethod( classC539Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__540, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD541;
				IModelElement[] methoddelhook538Childs = methoddelhook538.getChildren();
				classD541 = ModelTestUtils.getAssertClass( methoddelhook538Childs, "D" );
			}
		}
		//Function test:hashinherit
		{
		IMethod methodhashinherit542;
			IModelElement[] moduleChilds = module.getChildren();
			methodhashinherit542 = ModelTestUtils.getAssertMethod( moduleChilds, "hashinherit", 0 );
			//Class test
			{
			IType classmydict543;
				IModelElement[] methodhashinherit542Childs = methodhashinherit542.getChildren();
				classmydict543 = ModelTestUtils.getAssertClass( methodhashinherit542Childs, "mydict" );
			}
			//Class test
			{
			IType classmylist544;
				IModelElement[] methodhashinherit542Childs = methodhashinherit542.getChildren();
				classmylist544 = ModelTestUtils.getAssertClass( methodhashinherit542Childs, "mylist" );
			}
		}
		//Function test:strops
		{
		IMethod methodstrops545;
			IModelElement[] moduleChilds = module.getChildren();
			methodstrops545 = ModelTestUtils.getAssertMethod( moduleChilds, "strops", 0 );
		}
		//Function test:deepcopyrecursive
		{
		IMethod methoddeepcopyrecursive546;
			IModelElement[] moduleChilds = module.getChildren();
			methoddeepcopyrecursive546 = ModelTestUtils.getAssertMethod( moduleChilds, "deepcopyrecursive", 0 );
			//Class test
			{
			IType classNode547;
				IModelElement[] methoddeepcopyrecursive546Childs = methoddeepcopyrecursive546.getChildren();
				classNode547 = ModelTestUtils.getAssertClass( methoddeepcopyrecursive546Childs, "Node" );
			}
		}
		//Function test:modules
		{
		IMethod methodmodules548;
			IModelElement[] moduleChilds = module.getChildren();
			methodmodules548 = ModelTestUtils.getAssertMethod( moduleChilds, "modules", 0 );
		}
		//Function test:dictproxyiterkeys
		{
		IMethod methoddictproxyiterkeys549;
			IModelElement[] moduleChilds = module.getChildren();
			methoddictproxyiterkeys549 = ModelTestUtils.getAssertMethod( moduleChilds, "dictproxyiterkeys", 0 );
			//Class test
			{
			IType classC550;
				IModelElement[] methoddictproxyiterkeys549Childs = methoddictproxyiterkeys549.getChildren();
				classC550 = ModelTestUtils.getAssertClass( methoddictproxyiterkeys549Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth551;
					IModelElement[] classC550Childs = classC550.getChildren();
					methodmeth551 = ModelTestUtils.getAssertMethod( classC550Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth551, new String[] {"self"} );
				}
			}
		}
		//Function test:dictproxyitervalues
		{
		IMethod methoddictproxyitervalues552;
			IModelElement[] moduleChilds = module.getChildren();
			methoddictproxyitervalues552 = ModelTestUtils.getAssertMethod( moduleChilds, "dictproxyitervalues", 0 );
			//Class test
			{
			IType classC553;
				IModelElement[] methoddictproxyitervalues552Childs = methoddictproxyitervalues552.getChildren();
				classC553 = ModelTestUtils.getAssertClass( methoddictproxyitervalues552Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth554;
					IModelElement[] classC553Childs = classC553.getChildren();
					methodmeth554 = ModelTestUtils.getAssertMethod( classC553Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth554, new String[] {"self"} );
				}
			}
		}
		//Function test:dictproxyiteritems
		{
		IMethod methoddictproxyiteritems555;
			IModelElement[] moduleChilds = module.getChildren();
			methoddictproxyiteritems555 = ModelTestUtils.getAssertMethod( moduleChilds, "dictproxyiteritems", 0 );
			//Class test
			{
			IType classC556;
				IModelElement[] methoddictproxyiteritems555Childs = methoddictproxyiteritems555.getChildren();
				classC556 = ModelTestUtils.getAssertClass( methoddictproxyiteritems555Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth557;
					IModelElement[] classC556Childs = classC556.getChildren();
					methodmeth557 = ModelTestUtils.getAssertMethod( classC556Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth557, new String[] {"self"} );
				}
			}
		}
		//Function test:funnynew
		{
		IMethod methodfunnynew558;
			IModelElement[] moduleChilds = module.getChildren();
			methodfunnynew558 = ModelTestUtils.getAssertMethod( moduleChilds, "funnynew", 0 );
			//Class test
			{
			IType classC559;
				IModelElement[] methodfunnynew558Childs = methodfunnynew558.getChildren();
				classC559 = ModelTestUtils.getAssertClass( methodfunnynew558Childs, "C" );
				//Function test:__new__
				{
				IMethod method__new__560;
					IModelElement[] classC559Childs = classC559.getChildren();
					method__new__560 = ModelTestUtils.getAssertMethod( classC559Childs, "__new__", 2 );
					ModelTestUtils.assertParameterNames( method__new__560, new String[] {"cls", "arg"} );
				}
			}
			//Class test
			{
			IType classD561;
				IModelElement[] methodfunnynew558Childs = methodfunnynew558.getChildren();
				classD561 = ModelTestUtils.getAssertClass( methodfunnynew558Childs, "D" );
				//Function test:__init__
				{
				IMethod method__init__562;
					IModelElement[] classD561Childs = classD561.getChildren();
					method__init__562 = ModelTestUtils.getAssertMethod( classD561Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__562, new String[] {"self", "arg"} );
				}
				{
					IModelElement[] classD561Childs = classD561.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classD561Childs, "foo");
				}
			}
		}
		//Function test:imulbug
		{
		IMethod methodimulbug564;
			IModelElement[] moduleChilds = module.getChildren();
			methodimulbug564 = ModelTestUtils.getAssertMethod( moduleChilds, "imulbug", 0 );
			//Class test
			{
			IType classC565;
				IModelElement[] methodimulbug564Childs = methodimulbug564.getChildren();
				classC565 = ModelTestUtils.getAssertClass( methodimulbug564Childs, "C" );
				//Function test:__imul__
				{
				IMethod method__imul__566;
					IModelElement[] classC565Childs = classC565.getChildren();
					method__imul__566 = ModelTestUtils.getAssertMethod( classC565Childs, "__imul__", 2 );
					ModelTestUtils.assertParameterNames( method__imul__566, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:docdescriptor
		{
		IMethod methoddocdescriptor567;
			IModelElement[] moduleChilds = module.getChildren();
			methoddocdescriptor567 = ModelTestUtils.getAssertMethod( moduleChilds, "docdescriptor", 0 );
			//Class test
			{
			IType classDocDescr568;
				IModelElement[] methoddocdescriptor567Childs = methoddocdescriptor567.getChildren();
				classDocDescr568 = ModelTestUtils.getAssertClass( methoddocdescriptor567Childs, "DocDescr" );
				//Function test:__get__
				{
				IMethod method__get__569;
					IModelElement[] classDocDescr568Childs = classDocDescr568.getChildren();
					method__get__569 = ModelTestUtils.getAssertMethod( classDocDescr568Childs, "__get__", 3 );
					ModelTestUtils.assertParameterNames( method__get__569, new String[] {"self", "object", "otype"} );
				}
			}
			//Class test
			{
			IType classOldClass570;
				IModelElement[] methoddocdescriptor567Childs = methoddocdescriptor567.getChildren();
				classOldClass570 = ModelTestUtils.getAssertClass( methoddocdescriptor567Childs, "OldClass" );
			}
			//Class test
			{
			IType classNewClass571;
				IModelElement[] methoddocdescriptor567Childs = methoddocdescriptor567.getChildren();
				classNewClass571 = ModelTestUtils.getAssertClass( methoddocdescriptor567Childs, "NewClass" );
			}
		}
		//Function test:string_exceptions
		{
		IMethod methodstring_exceptions572;
			IModelElement[] moduleChilds = module.getChildren();
			methodstring_exceptions572 = ModelTestUtils.getAssertMethod( moduleChilds, "string_exceptions", 0 );
			//Class test
			{
			IType classMyStr573;
				IModelElement[] methodstring_exceptions572Childs = methodstring_exceptions572.getChildren();
				classMyStr573 = ModelTestUtils.getAssertClass( methodstring_exceptions572Childs, "MyStr" );
			}
		}
		//Function test:copy_setstate
		{
		IMethod methodcopy_setstate574;
			IModelElement[] moduleChilds = module.getChildren();
			methodcopy_setstate574 = ModelTestUtils.getAssertMethod( moduleChilds, "copy_setstate", 0 );
			//Class test
			{
			IType classC575;
				IModelElement[] methodcopy_setstate574Childs = methodcopy_setstate574.getChildren();
				classC575 = ModelTestUtils.getAssertClass( methodcopy_setstate574Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__576;
					IModelElement[] classC575Childs = classC575.getChildren();
					method__init__576 = ModelTestUtils.getAssertMethod( classC575Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__576, new String[] {"self", "foo"} );
				}
				{
					IModelElement[] classC575Childs = classC575.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC575Childs, "foo");
				}
				{
					IModelElement[] classC575Childs = classC575.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC575Childs, "__foo");
				}
				//Function test:setfoo
				{
				IMethod methodsetfoo578;
					IModelElement[] classC575Childs = classC575.getChildren();
					methodsetfoo578 = ModelTestUtils.getAssertMethod( classC575Childs, "setfoo", 2 );
					ModelTestUtils.assertParameterNames( methodsetfoo578, new String[] {"self", "foo"} );
				}
				{
					IModelElement[] classC575Childs = classC575.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC575Childs, "foo");
				}
				//Function test:getfoo
				{
				IMethod methodgetfoo580;
					IModelElement[] classC575Childs = classC575.getChildren();
					methodgetfoo580 = ModelTestUtils.getAssertMethod( classC575Childs, "getfoo", 1 );
					ModelTestUtils.assertParameterNames( methodgetfoo580, new String[] {"self"} );
				}
				//Function test:__getstate__
				{
				IMethod method__getstate__581;
					IModelElement[] classC575Childs = classC575.getChildren();
					method__getstate__581 = ModelTestUtils.getAssertMethod( classC575Childs, "__getstate__", 1 );
					ModelTestUtils.assertParameterNames( method__getstate__581, new String[] {"self"} );
				}
				//Function test:__setstate__
				{
				IMethod method__setstate__582;
					IModelElement[] classC575Childs = classC575.getChildren();
					method__setstate__582 = ModelTestUtils.getAssertMethod( classC575Childs, "__setstate__", 2 );
					ModelTestUtils.assertParameterNames( method__setstate__582, new String[] {"self", "lst"} );
				}
				{
					IModelElement[] classC575Childs = classC575.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classC575Childs, "__foo");
				}
			}
		}
		//Function test:slices
		{
		IMethod methodslices584;
			IModelElement[] moduleChilds = module.getChildren();
			methodslices584 = ModelTestUtils.getAssertMethod( moduleChilds, "slices", 0 );
			//Class test
			{
			IType classS585;
				IModelElement[] methodslices584Childs = methodslices584.getChildren();
				classS585 = ModelTestUtils.getAssertClass( methodslices584Childs, "S" );
				//Function test:__getitem__
				{
				IMethod method__getitem__586;
					IModelElement[] classS585Childs = classS585.getChildren();
					method__getitem__586 = ModelTestUtils.getAssertMethod( classS585Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__586, new String[] {"self", "x"} );
				}
			}
			//Class test
			{
			IType classT587;
				IModelElement[] methodslices584Childs = methodslices584.getChildren();
				classT587 = ModelTestUtils.getAssertClass( methodslices584Childs, "T" );
				//Function test:__getitem__
				{
				IMethod method__getitem__588;
					IModelElement[] classT587Childs = classT587.getChildren();
					method__getitem__588 = ModelTestUtils.getAssertMethod( classT587Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__588, new String[] {"self", "x"} );
				}
			}
			//Class test
			{
			IType classL589;
				IModelElement[] methodslices584Childs = methodslices584.getChildren();
				classL589 = ModelTestUtils.getAssertClass( methodslices584Childs, "L" );
				//Function test:__getitem__
				{
				IMethod method__getitem__590;
					IModelElement[] classL589Childs = classL589.getChildren();
					method__getitem__590 = ModelTestUtils.getAssertMethod( classL589Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__590, new String[] {"self", "x"} );
				}
			}
		}
		//Function test:subtype_resurrection
		{
		IMethod methodsubtype_resurrection591;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubtype_resurrection591 = ModelTestUtils.getAssertMethod( moduleChilds, "subtype_resurrection", 0 );
			//Class test
			{
			IType classC592;
				IModelElement[] methodsubtype_resurrection591Childs = methodsubtype_resurrection591.getChildren();
				classC592 = ModelTestUtils.getAssertClass( methodsubtype_resurrection591Childs, "C" );
				//Function test:__del__
				{
				IMethod method__del__593;
					IModelElement[] classC592Childs = classC592.getChildren();
					method__del__593 = ModelTestUtils.getAssertMethod( classC592Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__593, new String[] {"self"} );
				}
			}
		}
		//Function test:slottrash
		{
		IMethod methodslottrash594;
			IModelElement[] moduleChilds = module.getChildren();
			methodslottrash594 = ModelTestUtils.getAssertMethod( moduleChilds, "slottrash", 0 );
			//Class test
			{
			IType classtrash595;
				IModelElement[] methodslottrash594Childs = methodslottrash594.getChildren();
				classtrash595 = ModelTestUtils.getAssertClass( methodslottrash594Childs, "trash" );
				//Function test:__init__
				{
				IMethod method__init__596;
					IModelElement[] classtrash595Childs = classtrash595.getChildren();
					method__init__596 = ModelTestUtils.getAssertMethod( classtrash595Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__596, new String[] {"self", "x"} );
				}
			}
		}
		//Function test:slotmultipleinheritance
		{
		IMethod methodslotmultipleinheritance597;
			IModelElement[] moduleChilds = module.getChildren();
			methodslotmultipleinheritance597 = ModelTestUtils.getAssertMethod( moduleChilds, "slotmultipleinheritance", 0 );
			//Class test
			{
			IType classA598;
				IModelElement[] methodslotmultipleinheritance597Childs = methodslotmultipleinheritance597.getChildren();
				classA598 = ModelTestUtils.getAssertClass( methodslotmultipleinheritance597Childs, "A" );
			}
			//Class test
			{
			IType classB599;
				IModelElement[] methodslotmultipleinheritance597Childs = methodslotmultipleinheritance597.getChildren();
				classB599 = ModelTestUtils.getAssertClass( methodslotmultipleinheritance597Childs, "B" );
			}
			//Class test
			{
			IType classC600;
				IModelElement[] methodslotmultipleinheritance597Childs = methodslotmultipleinheritance597.getChildren();
				classC600 = ModelTestUtils.getAssertClass( methodslotmultipleinheritance597Childs, "C" );
			}
		}
		//Function test:testrmul
		{
		IMethod methodtestrmul601;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestrmul601 = ModelTestUtils.getAssertMethod( moduleChilds, "testrmul", 0 );
			//Class test
			{
			IType classC602;
				IModelElement[] methodtestrmul601Childs = methodtestrmul601.getChildren();
				classC602 = ModelTestUtils.getAssertClass( methodtestrmul601Childs, "C" );
				//Function test:__mul__
				{
				IMethod method__mul__603;
					IModelElement[] classC602Childs = classC602.getChildren();
					method__mul__603 = ModelTestUtils.getAssertMethod( classC602Childs, "__mul__", 2 );
					ModelTestUtils.assertParameterNames( method__mul__603, new String[] {"self", "other"} );
				}
				//Function test:__rmul__
				{
				IMethod method__rmul__604;
					IModelElement[] classC602Childs = classC602.getChildren();
					method__rmul__604 = ModelTestUtils.getAssertMethod( classC602Childs, "__rmul__", 2 );
					ModelTestUtils.assertParameterNames( method__rmul__604, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:testipow
		{
		IMethod methodtestipow605;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestipow605 = ModelTestUtils.getAssertMethod( moduleChilds, "testipow", 0 );
			//Class test
			{
			IType classC606;
				IModelElement[] methodtestipow605Childs = methodtestipow605.getChildren();
				classC606 = ModelTestUtils.getAssertClass( methodtestipow605Childs, "C" );
				//Function test:__ipow__
				{
				IMethod method__ipow__607;
					IModelElement[] classC606Childs = classC606.getChildren();
					method__ipow__607 = ModelTestUtils.getAssertMethod( classC606Childs, "__ipow__", 2 );
					ModelTestUtils.assertParameterNames( method__ipow__607, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:do_this_first
		{
		IMethod methoddo_this_first608;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_this_first608 = ModelTestUtils.getAssertMethod( moduleChilds, "do_this_first", 0 );
			//Class test
			{
			IType classUserLong609;
				IModelElement[] methoddo_this_first608Childs = methoddo_this_first608.getChildren();
				classUserLong609 = ModelTestUtils.getAssertClass( methoddo_this_first608Childs, "UserLong" );
				//Function test:__pow__
				{
				IMethod method__pow__610;
					IModelElement[] classUserLong609Childs = classUserLong609.getChildren();
					method__pow__610 = ModelTestUtils.getAssertMethod( classUserLong609Childs, "__pow__", 2 );
					ModelTestUtils.assertParameterNames( method__pow__610, new String[] {"self", "args"} );
				}
			}
		}
		//Function test:test_mutable_bases
		{
		IMethod methodtest_mutable_bases611;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mutable_bases611 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mutable_bases", 0 );
			//Class test
			{
			IType classC612;
				IModelElement[] methodtest_mutable_bases611Childs = methodtest_mutable_bases611.getChildren();
				classC612 = ModelTestUtils.getAssertClass( methodtest_mutable_bases611Childs, "C" );
			}
			//Class test
			{
			IType classC2613;
				IModelElement[] methodtest_mutable_bases611Childs = methodtest_mutable_bases611.getChildren();
				classC2613 = ModelTestUtils.getAssertClass( methodtest_mutable_bases611Childs, "C2" );
				//Function test:__getattribute__
				{
				IMethod method__getattribute__614;
					IModelElement[] classC2613Childs = classC2613.getChildren();
					method__getattribute__614 = ModelTestUtils.getAssertMethod( classC2613Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__614, new String[] {"self", "attr"} );
				}
				//Function test:meth
				{
				IMethod methodmeth615;
					IModelElement[] classC2613Childs = classC2613.getChildren();
					methodmeth615 = ModelTestUtils.getAssertMethod( classC2613Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth615, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD616;
				IModelElement[] methodtest_mutable_bases611Childs = methodtest_mutable_bases611.getChildren();
				classD616 = ModelTestUtils.getAssertClass( methodtest_mutable_bases611Childs, "D" );
			}
			//Class test
			{
			IType classE617;
				IModelElement[] methodtest_mutable_bases611Childs = methodtest_mutable_bases611.getChildren();
				classE617 = ModelTestUtils.getAssertClass( methodtest_mutable_bases611Childs, "E" );
			}
			//Class test
			{
			IType classL618;
				IModelElement[] methodtest_mutable_bases611Childs = methodtest_mutable_bases611.getChildren();
				classL618 = ModelTestUtils.getAssertClass( methodtest_mutable_bases611Childs, "L" );
			}
			//Class test
			{
			IType classClassic619;
				IModelElement[] methodtest_mutable_bases611Childs = methodtest_mutable_bases611.getChildren();
				classClassic619 = ModelTestUtils.getAssertClass( methodtest_mutable_bases611Childs, "Classic" );
				//Function test:meth2
				{
				IMethod methodmeth2620;
					IModelElement[] classClassic619Childs = classClassic619.getChildren();
					methodmeth2620 = ModelTestUtils.getAssertMethod( classClassic619Childs, "meth2", 1 );
					ModelTestUtils.assertParameterNames( methodmeth2620, new String[] {"self"} );
				}
			}
		}
		//Function test:test_mutable_bases_with_failing_mro
		{
		IMethod methodtest_mutable_bases_with_failing_mro621;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mutable_bases_with_failing_mro621 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mutable_bases_with_failing_mro", 0 );
			//Class test
			{
			IType classWorkOnce622;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classWorkOnce622 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "WorkOnce" );
				//Function test:__new__
				{
				IMethod method__new__623;
					IModelElement[] classWorkOnce622Childs = classWorkOnce622.getChildren();
					method__new__623 = ModelTestUtils.getAssertMethod( classWorkOnce622Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__623, new String[] {"self", "name", "bases", "ns"} );
				}
				{
					IModelElement[] classWorkOnce622Childs = classWorkOnce622.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classWorkOnce622Childs, "flag");
				}
				//Function test:mro
				{
				IMethod methodmro625;
					IModelElement[] classWorkOnce622Childs = classWorkOnce622.getChildren();
					methodmro625 = ModelTestUtils.getAssertMethod( classWorkOnce622Childs, "mro", 1 );
					ModelTestUtils.assertParameterNames( methodmro625, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classWorkAlways626;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classWorkAlways626 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "WorkAlways" );
				//Function test:mro
				{
				IMethod methodmro627;
					IModelElement[] classWorkAlways626Childs = classWorkAlways626.getChildren();
					methodmro627 = ModelTestUtils.getAssertMethod( classWorkAlways626Childs, "mro", 1 );
					ModelTestUtils.assertParameterNames( methodmro627, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC628;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classC628 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "C" );
			}
			//Class test
			{
			IType classC2629;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classC2629 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "C2" );
			}
			//Class test
			{
			IType classD630;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classD630 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "D" );
			}
			//Class test
			{
			IType classE631;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classE631 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "E" );
			}
			//Class test
			{
			IType classF632;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classF632 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "F" );
			}
			//Class test
			{
			IType classG633;
				IModelElement[] methodtest_mutable_bases_with_failing_mro621Childs = methodtest_mutable_bases_with_failing_mro621.getChildren();
				classG633 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro621Childs, "G" );
			}
		}
		//Function test:test_mutable_bases_catch_mro_conflict
		{
		IMethod methodtest_mutable_bases_catch_mro_conflict634;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mutable_bases_catch_mro_conflict634 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mutable_bases_catch_mro_conflict", 0 );
			//Class test
			{
			IType classA635;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict634Childs = methodtest_mutable_bases_catch_mro_conflict634.getChildren();
				classA635 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict634Childs, "A" );
			}
			//Class test
			{
			IType classB636;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict634Childs = methodtest_mutable_bases_catch_mro_conflict634.getChildren();
				classB636 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict634Childs, "B" );
			}
			//Class test
			{
			IType classC637;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict634Childs = methodtest_mutable_bases_catch_mro_conflict634.getChildren();
				classC637 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict634Childs, "C" );
			}
			//Class test
			{
			IType classD638;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict634Childs = methodtest_mutable_bases_catch_mro_conflict634.getChildren();
				classD638 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict634Childs, "D" );
			}
			//Class test
			{
			IType classE639;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict634Childs = methodtest_mutable_bases_catch_mro_conflict634.getChildren();
				classE639 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict634Childs, "E" );
			}
		}
		//Function test:mutable_names
		{
		IMethod methodmutable_names640;
			IModelElement[] moduleChilds = module.getChildren();
			methodmutable_names640 = ModelTestUtils.getAssertMethod( moduleChilds, "mutable_names", 0 );
			//Class test
			{
			IType classC641;
				IModelElement[] methodmutable_names640Childs = methodmutable_names640.getChildren();
				classC641 = ModelTestUtils.getAssertClass( methodmutable_names640Childs, "C" );
			}
		}
		//Function test:subclass_right_op
		{
		IMethod methodsubclass_right_op642;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubclass_right_op642 = ModelTestUtils.getAssertMethod( moduleChilds, "subclass_right_op", 0 );
			//Class test
			{
			IType classB643;
				IModelElement[] methodsubclass_right_op642Childs = methodsubclass_right_op642.getChildren();
				classB643 = ModelTestUtils.getAssertClass( methodsubclass_right_op642Childs, "B" );
				//Function test:__floordiv__
				{
				IMethod method__floordiv__644;
					IModelElement[] classB643Childs = classB643.getChildren();
					method__floordiv__644 = ModelTestUtils.getAssertMethod( classB643Childs, "__floordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__floordiv__644, new String[] {"self", "other"} );
				}
				//Function test:__rfloordiv__
				{
				IMethod method__rfloordiv__645;
					IModelElement[] classB643Childs = classB643.getChildren();
					method__rfloordiv__645 = ModelTestUtils.getAssertMethod( classB643Childs, "__rfloordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__rfloordiv__645, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classC646;
				IModelElement[] methodsubclass_right_op642Childs = methodsubclass_right_op642.getChildren();
				classC646 = ModelTestUtils.getAssertClass( methodsubclass_right_op642Childs, "C" );
				//Function test:__floordiv__
				{
				IMethod method__floordiv__647;
					IModelElement[] classC646Childs = classC646.getChildren();
					method__floordiv__647 = ModelTestUtils.getAssertMethod( classC646Childs, "__floordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__floordiv__647, new String[] {"self", "other"} );
				}
				//Function test:__rfloordiv__
				{
				IMethod method__rfloordiv__648;
					IModelElement[] classC646Childs = classC646.getChildren();
					method__rfloordiv__648 = ModelTestUtils.getAssertMethod( classC646Childs, "__rfloordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__rfloordiv__648, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classD649;
				IModelElement[] methodsubclass_right_op642Childs = methodsubclass_right_op642.getChildren();
				classD649 = ModelTestUtils.getAssertClass( methodsubclass_right_op642Childs, "D" );
				//Function test:__floordiv__
				{
				IMethod method__floordiv__650;
					IModelElement[] classD649Childs = classD649.getChildren();
					method__floordiv__650 = ModelTestUtils.getAssertMethod( classD649Childs, "__floordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__floordiv__650, new String[] {"self", "other"} );
				}
				//Function test:__rfloordiv__
				{
				IMethod method__rfloordiv__651;
					IModelElement[] classD649Childs = classD649.getChildren();
					method__rfloordiv__651 = ModelTestUtils.getAssertMethod( classD649Childs, "__rfloordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__rfloordiv__651, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classE652;
				IModelElement[] methodsubclass_right_op642Childs = methodsubclass_right_op642.getChildren();
				classE652 = ModelTestUtils.getAssertClass( methodsubclass_right_op642Childs, "E" );
			}
		}
		//Function test:dict_type_with_metaclass
		{
		IMethod methoddict_type_with_metaclass653;
			IModelElement[] moduleChilds = module.getChildren();
			methoddict_type_with_metaclass653 = ModelTestUtils.getAssertMethod( moduleChilds, "dict_type_with_metaclass", 0 );
			//Class test
			{
			IType classB654;
				IModelElement[] methoddict_type_with_metaclass653Childs = methoddict_type_with_metaclass653.getChildren();
				classB654 = ModelTestUtils.getAssertClass( methoddict_type_with_metaclass653Childs, "B" );
			}
			//Class test
			{
			IType classM655;
				IModelElement[] methoddict_type_with_metaclass653Childs = methoddict_type_with_metaclass653.getChildren();
				classM655 = ModelTestUtils.getAssertClass( methoddict_type_with_metaclass653Childs, "M" );
			}
			//Class test
			{
			IType classC656;
				IModelElement[] methoddict_type_with_metaclass653Childs = methoddict_type_with_metaclass653.getChildren();
				classC656 = ModelTestUtils.getAssertClass( methoddict_type_with_metaclass653Childs, "C" );
			}
		}
		//Function test:meth_class_get
		{
		IMethod methodmeth_class_get657;
			IModelElement[] moduleChilds = module.getChildren();
			methodmeth_class_get657 = ModelTestUtils.getAssertMethod( moduleChilds, "meth_class_get", 0 );
		}
		//Function test:isinst_isclass
		{
		IMethod methodisinst_isclass658;
			IModelElement[] moduleChilds = module.getChildren();
			methodisinst_isclass658 = ModelTestUtils.getAssertMethod( moduleChilds, "isinst_isclass", 0 );
			//Class test
			{
			IType classProxy659;
				IModelElement[] methodisinst_isclass658Childs = methodisinst_isclass658.getChildren();
				classProxy659 = ModelTestUtils.getAssertClass( methodisinst_isclass658Childs, "Proxy" );
				//Function test:__init__
				{
				IMethod method__init__660;
					IModelElement[] classProxy659Childs = classProxy659.getChildren();
					method__init__660 = ModelTestUtils.getAssertMethod( classProxy659Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__660, new String[] {"self", "obj"} );
				}
				//Function test:__getattribute__
				{
				IMethod method__getattribute__661;
					IModelElement[] classProxy659Childs = classProxy659.getChildren();
					method__getattribute__661 = ModelTestUtils.getAssertMethod( classProxy659Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__661, new String[] {"self", "name"} );
				}
			}
			//Class test
			{
			IType classC662;
				IModelElement[] methodisinst_isclass658Childs = methodisinst_isclass658.getChildren();
				classC662 = ModelTestUtils.getAssertClass( methodisinst_isclass658Childs, "C" );
			}
			//Class test
			{
			IType classD663;
				IModelElement[] methodisinst_isclass658Childs = methodisinst_isclass658.getChildren();
				classD663 = ModelTestUtils.getAssertClass( methodisinst_isclass658Childs, "D" );
			}
			//Class test
			{
			IType classC664;
				IModelElement[] methodisinst_isclass658Childs = methodisinst_isclass658.getChildren();
				classC664 = ModelTestUtils.getAssertClass( methodisinst_isclass658Childs, "C" );
			}
			//Class test
			{
			IType classD665;
				IModelElement[] methodisinst_isclass658Childs = methodisinst_isclass658.getChildren();
				classD665 = ModelTestUtils.getAssertClass( methodisinst_isclass658Childs, "D" );
			}
		}
		//Function test:proxysuper
		{
		IMethod methodproxysuper666;
			IModelElement[] moduleChilds = module.getChildren();
			methodproxysuper666 = ModelTestUtils.getAssertMethod( moduleChilds, "proxysuper", 0 );
			//Class test
			{
			IType classProxy667;
				IModelElement[] methodproxysuper666Childs = methodproxysuper666.getChildren();
				classProxy667 = ModelTestUtils.getAssertClass( methodproxysuper666Childs, "Proxy" );
				//Function test:__init__
				{
				IMethod method__init__668;
					IModelElement[] classProxy667Childs = classProxy667.getChildren();
					method__init__668 = ModelTestUtils.getAssertMethod( classProxy667Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__668, new String[] {"self", "obj"} );
				}
				//Function test:__getattribute__
				{
				IMethod method__getattribute__669;
					IModelElement[] classProxy667Childs = classProxy667.getChildren();
					method__getattribute__669 = ModelTestUtils.getAssertMethod( classProxy667Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__669, new String[] {"self", "name"} );
				}
			}
			//Class test
			{
			IType classB670;
				IModelElement[] methodproxysuper666Childs = methodproxysuper666.getChildren();
				classB670 = ModelTestUtils.getAssertClass( methodproxysuper666Childs, "B" );
				//Function test:f
				{
				IMethod methodf671;
					IModelElement[] classB670Childs = classB670.getChildren();
					methodf671 = ModelTestUtils.getAssertMethod( classB670Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf671, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC672;
				IModelElement[] methodproxysuper666Childs = methodproxysuper666.getChildren();
				classC672 = ModelTestUtils.getAssertClass( methodproxysuper666Childs, "C" );
				//Function test:f
				{
				IMethod methodf673;
					IModelElement[] classC672Childs = classC672.getChildren();
					methodf673 = ModelTestUtils.getAssertMethod( classC672Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf673, new String[] {"self"} );
				}
			}
		}
		//Function test:carloverre
		{
		IMethod methodcarloverre674;
			IModelElement[] moduleChilds = module.getChildren();
			methodcarloverre674 = ModelTestUtils.getAssertMethod( moduleChilds, "carloverre", 0 );
		}
		//Function test:weakref_segfault
		{
		IMethod methodweakref_segfault675;
			IModelElement[] moduleChilds = module.getChildren();
			methodweakref_segfault675 = ModelTestUtils.getAssertMethod( moduleChilds, "weakref_segfault", 0 );
			//Class test
			{
			IType classProvoker676;
				IModelElement[] methodweakref_segfault675Childs = methodweakref_segfault675.getChildren();
				classProvoker676 = ModelTestUtils.getAssertClass( methodweakref_segfault675Childs, "Provoker" );
				//Function test:__init__
				{
				IMethod method__init__677;
					IModelElement[] classProvoker676Childs = classProvoker676.getChildren();
					method__init__677 = ModelTestUtils.getAssertMethod( classProvoker676Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__677, new String[] {"self", "referrent"} );
				}
				{
					IModelElement[] classProvoker676Childs = classProvoker676.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classProvoker676Childs, "ref");
				}
				//Function test:__del__
				{
				IMethod method__del__679;
					IModelElement[] classProvoker676Childs = classProvoker676.getChildren();
					method__del__679 = ModelTestUtils.getAssertMethod( classProvoker676Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__679, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classOops680;
				IModelElement[] methodweakref_segfault675Childs = methodweakref_segfault675.getChildren();
				classOops680 = ModelTestUtils.getAssertClass( methodweakref_segfault675Childs, "Oops" );
			}
		}
		//Function test:filefault
		{
		IMethod methodfilefault681;
			IModelElement[] moduleChilds = module.getChildren();
			methodfilefault681 = ModelTestUtils.getAssertMethod( moduleChilds, "filefault", 0 );
			//Class test
			{
			IType classStdoutGuard682;
				IModelElement[] methodfilefault681Childs = methodfilefault681.getChildren();
				classStdoutGuard682 = ModelTestUtils.getAssertClass( methodfilefault681Childs, "StdoutGuard" );
				//Function test:__getattr__
				{
				IMethod method__getattr__683;
					IModelElement[] classStdoutGuard682Childs = classStdoutGuard682.getChildren();
					method__getattr__683 = ModelTestUtils.getAssertMethod( classStdoutGuard682Childs, "__getattr__", 2 );
					ModelTestUtils.assertParameterNames( method__getattr__683, new String[] {"self", "attr"} );
				}
			}
		}
		//Function test:vicious_descriptor_nonsense
		{
		IMethod methodvicious_descriptor_nonsense684;
			IModelElement[] moduleChilds = module.getChildren();
			methodvicious_descriptor_nonsense684 = ModelTestUtils.getAssertMethod( moduleChilds, "vicious_descriptor_nonsense", 0 );
			//Class test
			{
			IType classEvil685;
				IModelElement[] methodvicious_descriptor_nonsense684Childs = methodvicious_descriptor_nonsense684.getChildren();
				classEvil685 = ModelTestUtils.getAssertClass( methodvicious_descriptor_nonsense684Childs, "Evil" );
				//Function test:__hash__
				{
				IMethod method__hash__686;
					IModelElement[] classEvil685Childs = classEvil685.getChildren();
					method__hash__686 = ModelTestUtils.getAssertMethod( classEvil685Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__686, new String[] {"self"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__687;
					IModelElement[] classEvil685Childs = classEvil685.getChildren();
					method__eq__687 = ModelTestUtils.getAssertMethod( classEvil685Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__687, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classDescr688;
				IModelElement[] methodvicious_descriptor_nonsense684Childs = methodvicious_descriptor_nonsense684.getChildren();
				classDescr688 = ModelTestUtils.getAssertClass( methodvicious_descriptor_nonsense684Childs, "Descr" );
				//Function test:__get__
				{
				IMethod method__get__689;
					IModelElement[] classDescr688Childs = classDescr688.getChildren();
					method__get__689 = ModelTestUtils.getAssertMethod( classDescr688Childs, "__get__", 3 );
					ModelTestUtils.assertParameterNames( method__get__689, new String[] {"self", "ob", "type"} );
				}
			}
			//Class test
			{
			IType classC690;
				IModelElement[] methodvicious_descriptor_nonsense684Childs = methodvicious_descriptor_nonsense684.getChildren();
				classC690 = ModelTestUtils.getAssertClass( methodvicious_descriptor_nonsense684Childs, "C" );
			}
		}
		//Function test:test_init
		{
		IMethod methodtest_init691;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_init691 = ModelTestUtils.getAssertMethod( moduleChilds, "test_init", 0 );
			//Class test
			{
			IType classFoo692;
				IModelElement[] methodtest_init691Childs = methodtest_init691.getChildren();
				classFoo692 = ModelTestUtils.getAssertClass( methodtest_init691Childs, "Foo" );
				//Function test:__init__
				{
				IMethod method__init__693;
					IModelElement[] classFoo692Childs = classFoo692.getChildren();
					method__init__693 = ModelTestUtils.getAssertMethod( classFoo692Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__693, new String[] {"self"} );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main694;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main694 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen232( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_robotparser.py"));

		assertNotNull("Module test_robotparser.py not found", module);
		assertEquals("test_robotparser.py", module.getElementName());
		
		//Class test
		{
		IType classRobotTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classRobotTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "RobotTestCase" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classRobotTestCase0Childs, "__init__", 6 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "index", "parser", "url", "good", "agent"} );
			}
			{
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRobotTestCase0Childs, "str");
			}
			{
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRobotTestCase0Childs, "parser");
			}
			{
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRobotTestCase0Childs, "url");
			}
			{
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRobotTestCase0Childs, "good");
			}
			{
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRobotTestCase0Childs, "agent");
			}
			//Function test:runTest
			{
			IMethod methodrunTest3;
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				methodrunTest3 = ModelTestUtils.getAssertMethod( classRobotTestCase0Childs, "runTest", 1 );
				ModelTestUtils.assertParameterNames( methodrunTest3, new String[] {"self"} );
			}
			//Function test:__str__
			{
			IMethod method__str__4;
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				method__str__4 = ModelTestUtils.getAssertMethod( classRobotTestCase0Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__4, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		//Function test:RobotTest
		{
		IMethod methodRobotTest5;
			IModelElement[] moduleChilds = module.getChildren();
			methodRobotTest5 = ModelTestUtils.getAssertMethod( moduleChilds, "RobotTest", 5 );
			ModelTestUtils.assertParameterNames( methodRobotTest5, new String[] {"index", "robots_txt", "good_urls", "bad_urls", "agent"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "good");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "good");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "good");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "good");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "good");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "good");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad");
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen233( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_netrc.py"));

		assertNotNull("Module test_netrc.py not found", module);
		assertEquals("test_netrc.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEST_NETRC");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "temp_filename");
		}
		//Class test
		{
		IType classNetrcTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classNetrcTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "NetrcTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classNetrcTestCase0Childs = classNetrcTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classNetrcTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			{
				IModelElement[] classNetrcTestCase0Childs = classNetrcTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classNetrcTestCase0Childs, "netrc");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classNetrcTestCase0Childs = classNetrcTestCase0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classNetrcTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:test_case_1
			{
			IMethod methodtest_case_14;
				IModelElement[] classNetrcTestCase0Childs = classNetrcTestCase0.getChildren();
				methodtest_case_14 = ModelTestUtils.getAssertMethod( classNetrcTestCase0Childs, "test_case_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_14, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen234( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codeop.py"));

		assertNotNull("Module test_codeop.py not found", module);
		assertEquals("test_codeop.py", module.getElementName());
		
		//Function test:unify_callables
		{
		IMethod methodunify_callables0;
			IModelElement[] moduleChilds = module.getChildren();
			methodunify_callables0 = ModelTestUtils.getAssertMethod( moduleChilds, "unify_callables", 1 );
			ModelTestUtils.assertParameterNames( methodunify_callables0, new String[] {"d"} );
		}
		//Class test
		{
		IType classCodeopTests1;
			IModelElement[] moduleChilds = module.getChildren();
			classCodeopTests1 = ModelTestUtils.getAssertClass( moduleChilds, "CodeopTests" );
			//Function test:assertValid
			{
			IMethod methodassertValid2;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodassertValid2 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "assertValid", 3 );
				ModelTestUtils.assertParameterNames( methodassertValid2, new String[] {"self", "str", "symbol"} );
			}
			//Function test:assertIncomplete
			{
			IMethod methodassertIncomplete3;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodassertIncomplete3 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "assertIncomplete", 3 );
				ModelTestUtils.assertParameterNames( methodassertIncomplete3, new String[] {"self", "str", "symbol"} );
			}
			//Function test:assertInvalid
			{
			IMethod methodassertInvalid4;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodassertInvalid4 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "assertInvalid", 4 );
				ModelTestUtils.assertParameterNames( methodassertInvalid4, new String[] {"self", "str", "symbol", "is_syntax"} );
			}
			//Function test:test_valid
			{
			IMethod methodtest_valid5;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodtest_valid5 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "test_valid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_valid5, new String[] {"self"} );
			}
			//Function test:test_incomplete
			{
			IMethod methodtest_incomplete6;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodtest_incomplete6 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "test_incomplete", 1 );
				ModelTestUtils.assertParameterNames( methodtest_incomplete6, new String[] {"self"} );
			}
			//Function test:test_invalid
			{
			IMethod methodtest_invalid7;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodtest_invalid7 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "test_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid7, new String[] {"self"} );
			}
			//Function test:test_filename
			{
			IMethod methodtest_filename8;
				IModelElement[] classCodeopTests1Childs = classCodeopTests1.getChildren();
				methodtest_filename8 = ModelTestUtils.getAssertMethod( classCodeopTests1Childs, "test_filename", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filename8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen235( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_imgfile.py"));

		assertNotNull("Module test_imgfile.py not found", module);
		assertEquals("test_imgfile.py", module.getElementName());
		
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}
		//Function test:testimage
		{
		IMethod methodtestimage1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestimage1 = ModelTestUtils.getAssertMethod( moduleChilds, "testimage", 1 );
			ModelTestUtils.assertParameterNames( methodtestimage1, new String[] {"name"} );
		}

	}
	public void testModelGen236( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_wave.py"));

		assertNotNull("Module test_wave.py not found", module);
		assertEquals("test_wave.py", module.getElementName());
		
		//Function test:check
		{
		IMethod methodcheck0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck0 = ModelTestUtils.getAssertMethod( moduleChilds, "check", 2 );
			ModelTestUtils.assertParameterNames( methodcheck0, new String[] {"t", "msg"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nchannels");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sampwidth");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "framerate");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nframes");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "output");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "input");
		}

	}
	public void testModelGen237( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_macostools.py"));

		assertNotNull("Module test_macostools.py not found", module);
		assertEquals("test_macostools.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN2");
		}
		//Class test
		{
		IType classTestMacostools0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMacostools0 = ModelTestUtils.getAssertClass( moduleChilds, "TestMacostools" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:compareData
			{
			IMethod methodcompareData3;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodcompareData3 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "compareData", 1 );
				ModelTestUtils.assertParameterNames( methodcompareData3, new String[] {"self"} );
			}
			//Function test:test_touched
			{
			IMethod methodtest_touched4;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodtest_touched4 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "test_touched", 1 );
				ModelTestUtils.assertParameterNames( methodtest_touched4, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy5;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodtest_copy5 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy5, new String[] {"self"} );
			}
			//Function test:test_mkalias
			{
			IMethod methodtest_mkalias6;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodtest_mkalias6 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "test_mkalias", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mkalias6, new String[] {"self"} );
			}
			//Function test:test_mkalias_relative
			{
			IMethod methodtest_mkalias_relative7;
				IModelElement[] classTestMacostools0Childs = classTestMacostools0.getChildren();
				methodtest_mkalias_relative7 = ModelTestUtils.getAssertMethod( classTestMacostools0Childs, "test_mkalias_relative", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mkalias_relative7, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen238( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_exceptions.py"));

		assertNotNull("Module test_exceptions.py not found", module);
		assertEquals("test_exceptions.py", module.getElementName());
		
		//Function test:test_raise_catch
		{
		IMethod methodtest_raise_catch0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_raise_catch0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_raise_catch", 1 );
			ModelTestUtils.assertParameterNames( methodtest_raise_catch0, new String[] {"exc"} );
		}
		//Function test:r
		{
		IMethod methodr1;
			IModelElement[] moduleChilds = module.getChildren();
			methodr1 = ModelTestUtils.getAssertMethod( moduleChilds, "r", 1 );
			ModelTestUtils.assertParameterNames( methodr1, new String[] {"thing"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fp");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fp");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "savestdin");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:ckmsg
		{
		IMethod methodckmsg2;
			IModelElement[] moduleChilds = module.getChildren();
			methodckmsg2 = ModelTestUtils.getAssertMethod( moduleChilds, "ckmsg", 2 );
			ModelTestUtils.assertParameterNames( methodckmsg2, new String[] {"src", "msg"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Class test
		{
		IType classBadException3;
			IModelElement[] moduleChilds = module.getChildren();
			classBadException3 = ModelTestUtils.getAssertClass( moduleChilds, "BadException" );
			//Function test:__init__
			{
			IMethod method__init__4;
				IModelElement[] classBadException3Childs = classBadException3.getChildren();
				method__init__4 = ModelTestUtils.getAssertMethod( classBadException3Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__4, new String[] {"self"} );
			}
		}
		//Function test:test_capi1
		{
		IMethod methodtest_capi15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_capi15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_capi1", 0 );
		}
		//Function test:test_capi2
		{
		IMethod methodtest_capi26;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_capi26 = ModelTestUtils.getAssertMethod( moduleChilds, "test_capi2", 0 );
		}

	}
	public void testModelGen239( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_global.py"));

		assertNotNull("Module test_global.py not found", module);
		assertEquals("test_global.py", module.getElementName());
		
		//Function test:compile_and_check
		{
		IMethod methodcompile_and_check0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcompile_and_check0 = ModelTestUtils.getAssertMethod( moduleChilds, "compile_and_check", 2 );
			ModelTestUtils.assertParameterNames( methodcompile_and_check0, new String[] {"text", "should_fail"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prog_text_1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prog_text_2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prog_text_3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prog_text_4");
		}

	}
	public void testModelGen240( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bisect.py"));

		assertNotNull("Module test_bisect.py not found", module);
		assertEquals("test_bisect.py", module.getElementName());
		
		//Class test
		{
		IType classTestBisect0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBisect0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBisect" );
			{
				IModelElement[] classTestBisect0Childs = classTestBisect0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBisect0Childs, "precomputedCases");
			}
			//Function test:test_precomputed
			{
			IMethod methodtest_precomputed1;
				IModelElement[] classTestBisect0Childs = classTestBisect0.getChildren();
				methodtest_precomputed1 = ModelTestUtils.getAssertMethod( classTestBisect0Childs, "test_precomputed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_precomputed1, new String[] {"self"} );
			}
			//Function test:test_random
			{
			IMethod methodtest_random2;
				IModelElement[] classTestBisect0Childs = classTestBisect0.getChildren();
				methodtest_random2 = ModelTestUtils.getAssertMethod( classTestBisect0Childs, "test_random", 2 );
				ModelTestUtils.assertParameterNames( methodtest_random2, new String[] {"self", "n"} );
			}
			//Function test:test_optionalSlicing
			{
			IMethod methodtest_optionalSlicing3;
				IModelElement[] classTestBisect0Childs = classTestBisect0.getChildren();
				methodtest_optionalSlicing3 = ModelTestUtils.getAssertMethod( classTestBisect0Childs, "test_optionalSlicing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_optionalSlicing3, new String[] {"self"} );
			}
			//Function test:test_backcompatibility
			{
			IMethod methodtest_backcompatibility4;
				IModelElement[] classTestBisect0Childs = classTestBisect0.getChildren();
				methodtest_backcompatibility4 = ModelTestUtils.getAssertMethod( classTestBisect0Childs, "test_backcompatibility", 1 );
				ModelTestUtils.assertParameterNames( methodtest_backcompatibility4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestInsort5;
			IModelElement[] moduleChilds = module.getChildren();
			classTestInsort5 = ModelTestUtils.getAssertClass( moduleChilds, "TestInsort" );
			//Function test:test_vsBuiltinSort
			{
			IMethod methodtest_vsBuiltinSort6;
				IModelElement[] classTestInsort5Childs = classTestInsort5.getChildren();
				methodtest_vsBuiltinSort6 = ModelTestUtils.getAssertMethod( classTestInsort5Childs, "test_vsBuiltinSort", 2 );
				ModelTestUtils.assertParameterNames( methodtest_vsBuiltinSort6, new String[] {"self", "n"} );
			}
			//Function test:test_backcompatibility
			{
			IMethod methodtest_backcompatibility7;
				IModelElement[] classTestInsort5Childs = classTestInsort5.getChildren();
				methodtest_backcompatibility7 = ModelTestUtils.getAssertMethod( classTestInsort5Childs, "test_backcompatibility", 1 );
				ModelTestUtils.assertParameterNames( methodtest_backcompatibility7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLenOnly8;
			IModelElement[] moduleChilds = module.getChildren();
			classLenOnly8 = ModelTestUtils.getAssertClass( moduleChilds, "LenOnly" );
			//Function test:__len__
			{
			IMethod method__len__9;
				IModelElement[] classLenOnly8Childs = classLenOnly8.getChildren();
				method__len__9 = ModelTestUtils.getAssertMethod( classLenOnly8Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classGetOnly10;
			IModelElement[] moduleChilds = module.getChildren();
			classGetOnly10 = ModelTestUtils.getAssertClass( moduleChilds, "GetOnly" );
			//Function test:__getitem__
			{
			IMethod method__getitem__11;
				IModelElement[] classGetOnly10Childs = classGetOnly10.getChildren();
				method__getitem__11 = ModelTestUtils.getAssertMethod( classGetOnly10Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__11, new String[] {"self", "ndx"} );
			}
		}
		//Class test
		{
		IType classCmpErr12;
			IModelElement[] moduleChilds = module.getChildren();
			classCmpErr12 = ModelTestUtils.getAssertClass( moduleChilds, "CmpErr" );
			//Function test:__cmp__
			{
			IMethod method__cmp__13;
				IModelElement[] classCmpErr12Childs = classCmpErr12.getChildren();
				method__cmp__13 = ModelTestUtils.getAssertMethod( classCmpErr12Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__13, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classTestErrorHandling14;
			IModelElement[] moduleChilds = module.getChildren();
			classTestErrorHandling14 = ModelTestUtils.getAssertClass( moduleChilds, "TestErrorHandling" );
			//Function test:test_non_sequence
			{
			IMethod methodtest_non_sequence15;
				IModelElement[] classTestErrorHandling14Childs = classTestErrorHandling14.getChildren();
				methodtest_non_sequence15 = ModelTestUtils.getAssertMethod( classTestErrorHandling14Childs, "test_non_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_sequence15, new String[] {"self"} );
			}
			//Function test:test_len_only
			{
			IMethod methodtest_len_only16;
				IModelElement[] classTestErrorHandling14Childs = classTestErrorHandling14.getChildren();
				methodtest_len_only16 = ModelTestUtils.getAssertMethod( classTestErrorHandling14Childs, "test_len_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len_only16, new String[] {"self"} );
			}
			//Function test:test_get_only
			{
			IMethod methodtest_get_only17;
				IModelElement[] classTestErrorHandling14Childs = classTestErrorHandling14.getChildren();
				methodtest_get_only17 = ModelTestUtils.getAssertMethod( classTestErrorHandling14Childs, "test_get_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_only17, new String[] {"self"} );
			}
			//Function test:test_cmp_err
			{
			IMethod methodtest_cmp_err18;
				IModelElement[] classTestErrorHandling14Childs = classTestErrorHandling14.getChildren();
				methodtest_cmp_err18 = ModelTestUtils.getAssertMethod( classTestErrorHandling14Childs, "test_cmp_err", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp_err18, new String[] {"self"} );
			}
			//Function test:test_arg_parsing
			{
			IMethod methodtest_arg_parsing19;
				IModelElement[] classTestErrorHandling14Childs = classTestErrorHandling14.getChildren();
				methodtest_arg_parsing19 = ModelTestUtils.getAssertMethod( classTestErrorHandling14Childs, "test_arg_parsing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_arg_parsing19, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "libreftest");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}
		//Function test:test_main
		{
		IMethod methodtest_main20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main20, new String[] {"verbose"} );
		}

	}
	public void testModelGen241( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_binascii.py"));

		assertNotNull("Module test_binascii.py not found", module);
		assertEquals("test_binascii.py", module.getElementName());
		
		//Class test
		{
		IType classBinASCIITest0;
			IModelElement[] moduleChilds = module.getChildren();
			classBinASCIITest0 = ModelTestUtils.getAssertClass( moduleChilds, "BinASCIITest" );
			{
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBinASCIITest0Childs, "data");
			}
			//Function test:test_exceptions
			{
			IMethod methodtest_exceptions1;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_exceptions1 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_exceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exceptions1, new String[] {"self"} );
			}
			//Function test:test_functions
			{
			IMethod methodtest_functions2;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_functions2 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_functions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_functions2, new String[] {"self"} );
			}
			//Function test:test_base64valid
			{
			IMethod methodtest_base64valid3;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_base64valid3 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_base64valid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_base64valid3, new String[] {"self"} );
			}
			//Function test:test_base64invalid
			{
			IMethod methodtest_base64invalid4;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_base64invalid4 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_base64invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_base64invalid4, new String[] {"self"} );
				//Function test:addnoise
				{
				IMethod methodaddnoise5;
					IModelElement[] methodtest_base64invalid4Childs = methodtest_base64invalid4.getChildren();
					methodaddnoise5 = ModelTestUtils.getAssertMethod( methodtest_base64invalid4Childs, "addnoise", 1 );
					ModelTestUtils.assertParameterNames( methodaddnoise5, new String[] {"line"} );
				}
			}
			//Function test:test_uu
			{
			IMethod methodtest_uu6;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_uu6 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_uu", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uu6, new String[] {"self"} );
			}
			//Function test:test_crc32
			{
			IMethod methodtest_crc327;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_crc327 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_crc32", 1 );
				ModelTestUtils.assertParameterNames( methodtest_crc327, new String[] {"self"} );
			}
			//Function test:test_hex
			{
			IMethod methodtest_hex8;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_hex8 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_hex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hex8, new String[] {"self"} );
			}
			//Function test:test_qp
			{
			IMethod methodtest_qp9;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_qp9 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_qp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_qp9, new String[] {"self"} );
			}
			//Function test:test_empty_string
			{
			IMethod methodtest_empty_string10;
				IModelElement[] classBinASCIITest0Childs = classBinASCIITest0.getChildren();
				methodtest_empty_string10 = ModelTestUtils.getAssertMethod( classBinASCIITest0Childs, "test_empty_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_string10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen242( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_queue.py"));

		assertNotNull("Module test_queue.py not found", module);
		assertEquals("test_queue.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "QUEUE_SIZE");
		}
		//Class test
		{
		IType class_TriggerThread0;
			IModelElement[] moduleChilds = module.getChildren();
			class_TriggerThread0 = ModelTestUtils.getAssertClass( moduleChilds, "_TriggerThread" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] class_TriggerThread0Childs = class_TriggerThread0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( class_TriggerThread0Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "fn", "args"} );
			}
			{
				IModelElement[] class_TriggerThread0Childs = class_TriggerThread0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_TriggerThread0Childs, "fn");
			}
			{
				IModelElement[] class_TriggerThread0Childs = class_TriggerThread0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_TriggerThread0Childs, "args");
			}
			{
				IModelElement[] class_TriggerThread0Childs = class_TriggerThread0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_TriggerThread0Childs, "startedEvent");
			}
			//Function test:run
			{
			IMethod methodrun3;
				IModelElement[] class_TriggerThread0Childs = class_TriggerThread0.getChildren();
				methodrun3 = ModelTestUtils.getAssertMethod( class_TriggerThread0Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun3, new String[] {"self"} );
			}
		}
		//Function test:_doBlockingTest
		{
		IMethod method_doBlockingTest4;
			IModelElement[] moduleChilds = module.getChildren();
			method_doBlockingTest4 = ModelTestUtils.getAssertMethod( moduleChilds, "_doBlockingTest", 4 );
			ModelTestUtils.assertParameterNames( method_doBlockingTest4, new String[] {"block_func", "block_args", "trigger_func", "trigger_args"} );
		}
		//Function test:_doExceptionalBlockingTest
		{
		IMethod method_doExceptionalBlockingTest5;
			IModelElement[] moduleChilds = module.getChildren();
			method_doExceptionalBlockingTest5 = ModelTestUtils.getAssertMethod( moduleChilds, "_doExceptionalBlockingTest", 5 );
			ModelTestUtils.assertParameterNames( method_doExceptionalBlockingTest5, new String[] {"block_func", "block_args", "trigger_func", "trigger_args", "expected_exception_class"} );
		}
		//Class test
		{
		IType classFailingQueueException6;
			IModelElement[] moduleChilds = module.getChildren();
			classFailingQueueException6 = ModelTestUtils.getAssertClass( moduleChilds, "FailingQueueException" );
		}
		//Class test
		{
		IType classFailingQueue7;
			IModelElement[] moduleChilds = module.getChildren();
			classFailingQueue7 = ModelTestUtils.getAssertClass( moduleChilds, "FailingQueue" );
			//Function test:__init__
			{
			IMethod method__init__8;
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				method__init__8 = ModelTestUtils.getAssertMethod( classFailingQueue7Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "args"} );
			}
			{
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFailingQueue7Childs, "fail_next_put");
			}
			{
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFailingQueue7Childs, "fail_next_get");
			}
			//Function test:_put
			{
			IMethod method_put10;
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				method_put10 = ModelTestUtils.getAssertMethod( classFailingQueue7Childs, "_put", 2 );
				ModelTestUtils.assertParameterNames( method_put10, new String[] {"self", "item"} );
			}
			{
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFailingQueue7Childs, "fail_next_put");
			}
			//Function test:_get
			{
			IMethod method_get12;
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				method_get12 = ModelTestUtils.getAssertMethod( classFailingQueue7Childs, "_get", 1 );
				ModelTestUtils.assertParameterNames( method_get12, new String[] {"self"} );
			}
			{
				IModelElement[] classFailingQueue7Childs = classFailingQueue7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFailingQueue7Childs, "fail_next_get");
			}
		}
		//Function test:FailingQueueTest
		{
		IMethod methodFailingQueueTest14;
			IModelElement[] moduleChilds = module.getChildren();
			methodFailingQueueTest14 = ModelTestUtils.getAssertMethod( moduleChilds, "FailingQueueTest", 1 );
			ModelTestUtils.assertParameterNames( methodFailingQueueTest14, new String[] {"q"} );
		}
		//Function test:SimpleQueueTest
		{
		IMethod methodSimpleQueueTest15;
			IModelElement[] moduleChilds = module.getChildren();
			methodSimpleQueueTest15 = ModelTestUtils.getAssertMethod( moduleChilds, "SimpleQueueTest", 1 );
			ModelTestUtils.assertParameterNames( methodSimpleQueueTest15, new String[] {"q"} );
		}
		//Function test:test
		{
		IMethod methodtest16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest16 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen243( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_logging.py"));

		assertNotNull("Module test_logging.py not found", module);
		assertEquals("test_logging.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "BANNER");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FINISH_UP");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TIMEOUT");
		}
		//Class test
		{
		IType classLogRecordStreamHandler0;
			IModelElement[] moduleChilds = module.getChildren();
			classLogRecordStreamHandler0 = ModelTestUtils.getAssertClass( moduleChilds, "LogRecordStreamHandler" );
			//Function test:handle
			{
			IMethod methodhandle1;
				IModelElement[] classLogRecordStreamHandler0Childs = classLogRecordStreamHandler0.getChildren();
				methodhandle1 = ModelTestUtils.getAssertMethod( classLogRecordStreamHandler0Childs, "handle", 1 );
				ModelTestUtils.assertParameterNames( methodhandle1, new String[] {"self"} );
			}
			//Function test:unPickle
			{
			IMethod methodunPickle2;
				IModelElement[] classLogRecordStreamHandler0Childs = classLogRecordStreamHandler0.getChildren();
				methodunPickle2 = ModelTestUtils.getAssertMethod( classLogRecordStreamHandler0Childs, "unPickle", 2 );
				ModelTestUtils.assertParameterNames( methodunPickle2, new String[] {"self", "data"} );
			}
			//Function test:handleLogRecord
			{
			IMethod methodhandleLogRecord3;
				IModelElement[] classLogRecordStreamHandler0Childs = classLogRecordStreamHandler0.getChildren();
				methodhandleLogRecord3 = ModelTestUtils.getAssertMethod( classLogRecordStreamHandler0Childs, "handleLogRecord", 2 );
				ModelTestUtils.assertParameterNames( methodhandleLogRecord3, new String[] {"self", "record"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "socketDataProcessed");
		}
		//Class test
		{
		IType classLogRecordSocketReceiver4;
			IModelElement[] moduleChilds = module.getChildren();
			classLogRecordSocketReceiver4 = ModelTestUtils.getAssertClass( moduleChilds, "LogRecordSocketReceiver" );
			{
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLogRecordSocketReceiver4Childs, "allow_reuse_address");
			}
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classLogRecordSocketReceiver4Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "host", "port", "handler"} );
			}
			{
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLogRecordSocketReceiver4Childs, "abort");
			}
			{
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLogRecordSocketReceiver4Childs, "timeout");
			}
			//Function test:serve_until_stopped
			{
			IMethod methodserve_until_stopped7;
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				methodserve_until_stopped7 = ModelTestUtils.getAssertMethod( classLogRecordSocketReceiver4Childs, "serve_until_stopped", 1 );
				ModelTestUtils.assertParameterNames( methodserve_until_stopped7, new String[] {"self"} );
			}
			//Function test:process_request
			{
			IMethod methodprocess_request8;
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				methodprocess_request8 = ModelTestUtils.getAssertMethod( classLogRecordSocketReceiver4Childs, "process_request", 3 );
				ModelTestUtils.assertParameterNames( methodprocess_request8, new String[] {"self", "request", "client_address"} );
			}
		}
		//Function test:runTCP
		{
		IMethod methodrunTCP9;
			IModelElement[] moduleChilds = module.getChildren();
			methodrunTCP9 = ModelTestUtils.getAssertMethod( moduleChilds, "runTCP", 1 );
			ModelTestUtils.assertParameterNames( methodrunTCP9, new String[] {"tcpserver"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msgcount");
		}
		//Function test:nextmessage
		{
		IMethod methodnextmessage10;
			IModelElement[] moduleChilds = module.getChildren();
			methodnextmessage10 = ModelTestUtils.getAssertMethod( moduleChilds, "nextmessage", 0 );
		}
		//Function test:test0
		{
		IMethod methodtest011;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest011 = ModelTestUtils.getAssertMethod( moduleChilds, "test0", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SILENT");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TACITURN");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TERSE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "EFFUSIVE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SOCIABLE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "VERBOSE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TALKATIVE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "GARRULOUS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "CHATTERBOX");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "BORING");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LEVEL_RANGE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "my_logging_levels");
		}
		//Class test
		{
		IType classSpecificLevelFilter12;
			IModelElement[] moduleChilds = module.getChildren();
			classSpecificLevelFilter12 = ModelTestUtils.getAssertClass( moduleChilds, "SpecificLevelFilter" );
			//Function test:__init__
			{
			IMethod method__init__13;
				IModelElement[] classSpecificLevelFilter12Childs = classSpecificLevelFilter12.getChildren();
				method__init__13 = ModelTestUtils.getAssertMethod( classSpecificLevelFilter12Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__13, new String[] {"self", "lvl"} );
			}
			{
				IModelElement[] classSpecificLevelFilter12Childs = classSpecificLevelFilter12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSpecificLevelFilter12Childs, "level");
			}
			//Function test:filter
			{
			IMethod methodfilter15;
				IModelElement[] classSpecificLevelFilter12Childs = classSpecificLevelFilter12.getChildren();
				methodfilter15 = ModelTestUtils.getAssertMethod( classSpecificLevelFilter12Childs, "filter", 2 );
				ModelTestUtils.assertParameterNames( methodfilter15, new String[] {"self", "record"} );
			}
		}
		//Class test
		{
		IType classGarrulousFilter16;
			IModelElement[] moduleChilds = module.getChildren();
			classGarrulousFilter16 = ModelTestUtils.getAssertClass( moduleChilds, "GarrulousFilter" );
			//Function test:__init__
			{
			IMethod method__init__17;
				IModelElement[] classGarrulousFilter16Childs = classGarrulousFilter16.getChildren();
				method__init__17 = ModelTestUtils.getAssertMethod( classGarrulousFilter16Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classVerySpecificFilter18;
			IModelElement[] moduleChilds = module.getChildren();
			classVerySpecificFilter18 = ModelTestUtils.getAssertClass( moduleChilds, "VerySpecificFilter" );
			//Function test:filter
			{
			IMethod methodfilter19;
				IModelElement[] classVerySpecificFilter18Childs = classVerySpecificFilter18.getChildren();
				methodfilter19 = ModelTestUtils.getAssertMethod( classVerySpecificFilter18Childs, "filter", 2 );
				ModelTestUtils.assertParameterNames( methodfilter19, new String[] {"self", "record"} );
			}
		}
		//Function test:message
		{
		IMethod methodmessage20;
			IModelElement[] moduleChilds = module.getChildren();
			methodmessage20 = ModelTestUtils.getAssertMethod( moduleChilds, "message", 1 );
			ModelTestUtils.assertParameterNames( methodmessage20, new String[] {"s"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SHOULD1");
		}
		//Function test:test1
		{
		IMethod methodtest121;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest121 = ModelTestUtils.getAssertMethod( moduleChilds, "test1", 0 );
			//Function test:doLog
			{
			IMethod methoddoLog22;
				IModelElement[] methodtest121Childs = methodtest121.getChildren();
				methoddoLog22 = ModelTestUtils.getAssertMethod( methodtest121Childs, "doLog", 1 );
				ModelTestUtils.assertParameterNames( methoddoLog22, new String[] {"log"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MSG");
		}
		//Function test:test2
		{
		IMethod methodtest223;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest223 = ModelTestUtils.getAssertMethod( moduleChilds, "test2", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FILTER");
		}
		//Function test:doLog3
		{
		IMethod methoddoLog324;
			IModelElement[] moduleChilds = module.getChildren();
			methoddoLog324 = ModelTestUtils.getAssertMethod( moduleChilds, "doLog3", 0 );
		}
		//Function test:test3
		{
		IMethod methodtest325;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest325 = ModelTestUtils.getAssertMethod( moduleChilds, "test3", 0 );
		}
		//Function test:banner
		{
		IMethod methodbanner26;
			IModelElement[] moduleChilds = module.getChildren();
			methodbanner26 = ModelTestUtils.getAssertMethod( moduleChilds, "banner", 2 );
			ModelTestUtils.assertParameterNames( methodbanner26, new String[] {"nm", "typ"} );
		}
		//Function test:test_main_inner
		{
		IMethod methodtest_main_inner27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main_inner27 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main_inner", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen244( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_ntpath.py"));

		assertNotNull("Module test_ntpath.py not found", module);
		assertEquals("test_ntpath.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "errors");
		}
		//Function test:tester
		{
		IMethod methodtester0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtester0 = ModelTestUtils.getAssertMethod( moduleChilds, "tester", 2 );
			ModelTestUtils.assertParameterNames( methodtester0, new String[] {"fn", "wantResult"} );
		}

	}
	public void testModelGen245( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("pystone.py"));

		assertNotNull("Module pystone.py not found", module);
		assertEquals("pystone.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LOOPS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__version__");
		}
		//Class test
		{
		IType classRecord0;
			IModelElement[] moduleChilds = module.getChildren();
			classRecord0 = ModelTestUtils.getAssertClass( moduleChilds, "Record" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classRecord0Childs, "__init__", 6 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "PtrComp", "Discr", "EnumComp", "IntComp", "StringComp"} );
			}
			{
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRecord0Childs, "PtrComp");
			}
			{
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRecord0Childs, "Discr");
			}
			{
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRecord0Childs, "EnumComp");
			}
			{
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRecord0Childs, "IntComp");
			}
			{
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRecord0Childs, "StringComp");
			}
			//Function test:copy
			{
			IMethod methodcopy3;
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				methodcopy3 = ModelTestUtils.getAssertMethod( classRecord0Childs, "copy", 1 );
				ModelTestUtils.assertParameterNames( methodcopy3, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TRUE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FALSE");
		}
		//Function test:main
		{
		IMethod methodmain4;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain4 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 1 );
			ModelTestUtils.assertParameterNames( methodmain4, new String[] {"loops"} );
		}
		//Function test:pystones
		{
		IMethod methodpystones5;
			IModelElement[] moduleChilds = module.getChildren();
			methodpystones5 = ModelTestUtils.getAssertMethod( moduleChilds, "pystones", 1 );
			ModelTestUtils.assertParameterNames( methodpystones5, new String[] {"loops"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "IntGlob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "BoolGlob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Char1Glob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Char2Glob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Array1Glob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Array2Glob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PtrGlb");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PtrGlbNext");
		}
		//Function test:Proc0
		{
		IMethod methodProc06;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc06 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc0", 1 );
			ModelTestUtils.assertParameterNames( methodProc06, new String[] {"loops"} );
		}
		//Function test:Proc1
		{
		IMethod methodProc17;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc17 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc1", 1 );
			ModelTestUtils.assertParameterNames( methodProc17, new String[] {"PtrParIn"} );
		}
		//Function test:Proc2
		{
		IMethod methodProc28;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc28 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc2", 1 );
			ModelTestUtils.assertParameterNames( methodProc28, new String[] {"IntParIO"} );
		}
		//Function test:Proc3
		{
		IMethod methodProc39;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc39 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc3", 1 );
			ModelTestUtils.assertParameterNames( methodProc39, new String[] {"PtrParOut"} );
		}
		//Function test:Proc4
		{
		IMethod methodProc410;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc410 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc4", 0 );
		}
		//Function test:Proc5
		{
		IMethod methodProc511;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc511 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc5", 0 );
		}
		//Function test:Proc6
		{
		IMethod methodProc612;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc612 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc6", 1 );
			ModelTestUtils.assertParameterNames( methodProc612, new String[] {"EnumParIn"} );
		}
		//Function test:Proc7
		{
		IMethod methodProc713;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc713 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc7", 2 );
			ModelTestUtils.assertParameterNames( methodProc713, new String[] {"IntParI1", "IntParI2"} );
		}
		//Function test:Proc8
		{
		IMethod methodProc814;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc814 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc8", 4 );
			ModelTestUtils.assertParameterNames( methodProc814, new String[] {"Array1Par", "Array2Par", "IntParI1", "IntParI2"} );
		}
		//Function test:Func1
		{
		IMethod methodFunc115;
			IModelElement[] moduleChilds = module.getChildren();
			methodFunc115 = ModelTestUtils.getAssertMethod( moduleChilds, "Func1", 2 );
			ModelTestUtils.assertParameterNames( methodFunc115, new String[] {"CharPar1", "CharPar2"} );
		}
		//Function test:Func2
		{
		IMethod methodFunc216;
			IModelElement[] moduleChilds = module.getChildren();
			methodFunc216 = ModelTestUtils.getAssertMethod( moduleChilds, "Func2", 2 );
			ModelTestUtils.assertParameterNames( methodFunc216, new String[] {"StrParI1", "StrParI2"} );
		}
		//Function test:Func3
		{
		IMethod methodFunc317;
			IModelElement[] moduleChilds = module.getChildren();
			methodFunc317 = ModelTestUtils.getAssertMethod( moduleChilds, "Func3", 1 );
			ModelTestUtils.assertParameterNames( methodFunc317, new String[] {"EnumParIn"} );
		}
		//Function test:error
		{
		IMethod methoderror18;
			IModelElement[] moduleChilds = module.getChildren();
			methoderror18 = ModelTestUtils.getAssertMethod( moduleChilds, "error", 1 );
			ModelTestUtils.assertParameterNames( methoderror18, new String[] {"msg"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nargs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "loops");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "loops");
		}

	}
	public void testModelGen246( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_future1.py"));

		assertNotNull("Module test_future1.py not found", module);
		assertEquals("test_future1.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen247( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_future2.py"));

		assertNotNull("Module test_future2.py not found", module);
		assertEquals("test_future2.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg1;
				IModelElement[] methodf0Childs = methodf0.getChildren();
				methodg1 = ModelTestUtils.getAssertMethod( methodf0Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}

	}
	public void testModelGen248( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_future3.py"));

		assertNotNull("Module test_future3.py not found", module);
		assertEquals("test_future3.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:nester
		{
		IMethod methodnester0;
			IModelElement[] moduleChilds = module.getChildren();
			methodnester0 = ModelTestUtils.getAssertMethod( moduleChilds, "nester", 0 );
			//Function test:inner
			{
			IMethod methodinner1;
				IModelElement[] methodnester0Childs = methodnester0.getChildren();
				methodinner1 = ModelTestUtils.getAssertMethod( methodnester0Childs, "inner", 0 );
			}
		}
		//Class test
		{
		IType classTestFuture2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestFuture2 = ModelTestUtils.getAssertClass( moduleChilds, "TestFuture" );
			//Function test:test_floor_div_operator
			{
			IMethod methodtest_floor_div_operator3;
				IModelElement[] classTestFuture2Childs = classTestFuture2.getChildren();
				methodtest_floor_div_operator3 = ModelTestUtils.getAssertMethod( classTestFuture2Childs, "test_floor_div_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floor_div_operator3, new String[] {"self"} );
			}
			//Function test:test_true_div_as_default
			{
			IMethod methodtest_true_div_as_default4;
				IModelElement[] classTestFuture2Childs = classTestFuture2.getChildren();
				methodtest_true_div_as_default4 = ModelTestUtils.getAssertMethod( classTestFuture2Childs, "test_true_div_as_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_true_div_as_default4, new String[] {"self"} );
			}
			//Function test:test_nested_scopes
			{
			IMethod methodtest_nested_scopes5;
				IModelElement[] classTestFuture2Childs = classTestFuture2.getChildren();
				methodtest_nested_scopes5 = ModelTestUtils.getAssertMethod( classTestFuture2Childs, "test_nested_scopes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_scopes5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen249( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_userstring.py"));

		assertNotNull("Module test_userstring.py not found", module);
		assertEquals("test_userstring.py", module.getElementName());
		
		//Class test
		{
		IType classUserStringTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUserStringTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UserStringTest" );
			{
				IModelElement[] classUserStringTest0Childs = classUserStringTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserStringTest0Childs, "type2test");
			}
			//Function test:checkequal
			{
			IMethod methodcheckequal1;
				IModelElement[] classUserStringTest0Childs = classUserStringTest0.getChildren();
				methodcheckequal1 = ModelTestUtils.getAssertMethod( classUserStringTest0Childs, "checkequal", 5 );
				ModelTestUtils.assertParameterNames( methodcheckequal1, new String[] {"self", "result", "object", "methodname", "args"} );
			}
			//Function test:checkraises
			{
			IMethod methodcheckraises2;
				IModelElement[] classUserStringTest0Childs = classUserStringTest0.getChildren();
				methodcheckraises2 = ModelTestUtils.getAssertMethod( classUserStringTest0Childs, "checkraises", 5 );
				ModelTestUtils.assertParameterNames( methodcheckraises2, new String[] {"self", "exc", "object", "methodname", "args"} );
			}
			//Function test:checkcall
			{
			IMethod methodcheckcall3;
				IModelElement[] classUserStringTest0Childs = classUserStringTest0.getChildren();
				methodcheckcall3 = ModelTestUtils.getAssertMethod( classUserStringTest0Childs, "checkcall", 4 );
				ModelTestUtils.assertParameterNames( methodcheckcall3, new String[] {"self", "object", "methodname", "args"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}

}
	