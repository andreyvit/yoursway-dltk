
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
import java.net.URL;
import org.osgi.framework.Bundle;

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
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_4", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests4" );
	}
	public void testModelGen200( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
					//Function test:__getattr__
					{
					IMethod method__getattr__47;
						IModelElement[] classNameLookupTracer45Childs = classNameLookupTracer45.getChildren();
						method__getattr__47 = ModelTestUtils.getAssertMethod( classNameLookupTracer45Childs, "__getattr__", 2 );
						ModelTestUtils.assertParameterNames( method__getattr__47, new String[] {"self", "fname"} );
					}
				}
				//Function test:foo
				{
				IMethod methodfoo48;
					IModelElement[] methodtest_eval_order42Childs = methodtest_eval_order42.getChildren();
					methodfoo48 = ModelTestUtils.getAssertMethod( methodtest_eval_order42Childs, "foo", 0 );
				}
				//Function test:bar
				{
				IMethod methodbar49;
					IModelElement[] methodtest_eval_order42Childs = methodtest_eval_order42.getChildren();
					methodbar49 = ModelTestUtils.getAssertMethod( methodtest_eval_order42Childs, "bar", 0 );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main50;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main50 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen212( ) throws Exception {
		String prj = "pytests_4";
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
			//Class test
			{
			IType classget_desc79;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				classget_desc79 = ModelTestUtils.getAssertClass( classFunkyProperties77Childs, "get_desc" );
				//Function test:__init__
				{
				IMethod method__init__80;
					IModelElement[] classget_desc79Childs = classget_desc79.getChildren();
					method__init__80 = ModelTestUtils.getAssertMethod( classget_desc79Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__80, new String[] {"self", "attr"} );
				}
				//Function test:__call__
				{
				IMethod method__call__81;
					IModelElement[] classget_desc79Childs = classget_desc79.getChildren();
					method__call__81 = ModelTestUtils.getAssertMethod( classget_desc79Childs, "__call__", 2 );
					ModelTestUtils.assertParameterNames( method__call__81, new String[] {"self", "inst"} );
				}
			}
			//Class test
			{
			IType classset_desc82;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				classset_desc82 = ModelTestUtils.getAssertClass( classFunkyProperties77Childs, "set_desc" );
				//Function test:__init__
				{
				IMethod method__init__83;
					IModelElement[] classset_desc82Childs = classset_desc82.getChildren();
					method__init__83 = ModelTestUtils.getAssertMethod( classset_desc82Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__83, new String[] {"self", "attr"} );
				}
				//Function test:__call__
				{
				IMethod method__call__84;
					IModelElement[] classset_desc82Childs = classset_desc82.getChildren();
					method__call__84 = ModelTestUtils.getAssertMethod( classset_desc82Childs, "__call__", 3 );
					ModelTestUtils.assertParameterNames( method__call__84, new String[] {"self", "inst", "val"} );
				}
			}
			//Class test
			{
			IType classdel_desc85;
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				classdel_desc85 = ModelTestUtils.getAssertClass( classFunkyProperties77Childs, "del_desc" );
				//Function test:__init__
				{
				IMethod method__init__86;
					IModelElement[] classdel_desc85Childs = classdel_desc85.getChildren();
					method__init__86 = ModelTestUtils.getAssertMethod( classdel_desc85Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__86, new String[] {"self", "attr"} );
				}
				//Function test:__call__
				{
				IMethod method__call__87;
					IModelElement[] classdel_desc85Childs = classdel_desc85.getChildren();
					method__call__87 = ModelTestUtils.getAssertMethod( classdel_desc85Childs, "__call__", 2 );
					ModelTestUtils.assertParameterNames( method__call__87, new String[] {"self", "inst"} );
				}
			}
			{
				IModelElement[] classFunkyProperties77Childs = classFunkyProperties77.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFunkyProperties77Childs, "x");
			}
		}

	}
	public void testModelGen213( ) throws Exception {
		String prj = "pytests_4";
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
			//Function test:notationDecl
			{
			IMethod methodnotationDecl25;
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				methodnotationDecl25 = ModelTestUtils.getAssertMethod( classTestDTDHandler23Childs, "notationDecl", 4 );
				ModelTestUtils.assertParameterNames( methodnotationDecl25, new String[] {"self", "name", "publicId", "systemId"} );
			}
			//Function test:unparsedEntityDecl
			{
			IMethod methodunparsedEntityDecl26;
				IModelElement[] classTestDTDHandler23Childs = classTestDTDHandler23.getChildren();
				methodunparsedEntityDecl26 = ModelTestUtils.getAssertMethod( classTestDTDHandler23Childs, "unparsedEntityDecl", 5 );
				ModelTestUtils.assertParameterNames( methodunparsedEntityDecl26, new String[] {"self", "name", "publicId", "systemId", "ndata"} );
			}
		}
		//Function test:test_expat_dtdhandler
		{
		IMethod methodtest_expat_dtdhandler27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_dtdhandler27 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_dtdhandler", 0 );
		}
		//Class test
		{
		IType classTestEntityResolver28;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEntityResolver28 = ModelTestUtils.getAssertClass( moduleChilds, "TestEntityResolver" );
			//Function test:resolveEntity
			{
			IMethod methodresolveEntity29;
				IModelElement[] classTestEntityResolver28Childs = classTestEntityResolver28.getChildren();
				methodresolveEntity29 = ModelTestUtils.getAssertMethod( classTestEntityResolver28Childs, "resolveEntity", 3 );
				ModelTestUtils.assertParameterNames( methodresolveEntity29, new String[] {"self", "publicId", "systemId"} );
			}
		}
		//Function test:test_expat_entityresolver
		{
		IMethod methodtest_expat_entityresolver30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_entityresolver30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_entityresolver", 0 );
		}
		//Class test
		{
		IType classAttrGatherer31;
			IModelElement[] moduleChilds = module.getChildren();
			classAttrGatherer31 = ModelTestUtils.getAssertClass( moduleChilds, "AttrGatherer" );
			//Function test:startElement
			{
			IMethod methodstartElement32;
				IModelElement[] classAttrGatherer31Childs = classAttrGatherer31.getChildren();
				methodstartElement32 = ModelTestUtils.getAssertMethod( classAttrGatherer31Childs, "startElement", 3 );
				ModelTestUtils.assertParameterNames( methodstartElement32, new String[] {"self", "name", "attrs"} );
			}
			//Function test:startElementNS
			{
			IMethod methodstartElementNS33;
				IModelElement[] classAttrGatherer31Childs = classAttrGatherer31.getChildren();
				methodstartElementNS33 = ModelTestUtils.getAssertMethod( classAttrGatherer31Childs, "startElementNS", 4 );
				ModelTestUtils.assertParameterNames( methodstartElementNS33, new String[] {"self", "name", "qname", "attrs"} );
			}
		}
		//Function test:test_expat_attrs_empty
		{
		IMethod methodtest_expat_attrs_empty34;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_attrs_empty34 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_attrs_empty", 0 );
		}
		//Function test:test_expat_attrs_wattr
		{
		IMethod methodtest_expat_attrs_wattr35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_attrs_wattr35 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_attrs_wattr", 0 );
		}
		//Function test:test_expat_nsattrs_empty
		{
		IMethod methodtest_expat_nsattrs_empty36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_nsattrs_empty36 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_nsattrs_empty", 0 );
		}
		//Function test:test_expat_nsattrs_wattr
		{
		IMethod methodtest_expat_nsattrs_wattr37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_nsattrs_wattr37 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_nsattrs_wattr", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "xml_test_out");
		}
		//Function test:test_expat_inpsource_filename
		{
		IMethod methodtest_expat_inpsource_filename38;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_filename38 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_filename", 0 );
		}
		//Function test:test_expat_inpsource_sysid
		{
		IMethod methodtest_expat_inpsource_sysid39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_sysid39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_sysid", 0 );
		}
		//Function test:test_expat_inpsource_stream
		{
		IMethod methodtest_expat_inpsource_stream40;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_stream40 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_stream", 0 );
		}
		//Function test:test_expat_incremental
		{
		IMethod methodtest_expat_incremental41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_incremental41 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_incremental", 0 );
		}
		//Function test:test_expat_incremental_reset
		{
		IMethod methodtest_expat_incremental_reset42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_incremental_reset42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_incremental_reset", 0 );
		}
		//Function test:test_expat_locator_noinfo
		{
		IMethod methodtest_expat_locator_noinfo43;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_locator_noinfo43 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_locator_noinfo", 0 );
		}
		//Function test:test_expat_locator_withinfo
		{
		IMethod methodtest_expat_locator_withinfo44;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_locator_withinfo44 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_locator_withinfo", 0 );
		}
		//Function test:test_expat_inpsource_location
		{
		IMethod methodtest_expat_inpsource_location45;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_inpsource_location45 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_inpsource_location", 0 );
		}
		//Function test:test_expat_incomplete
		{
		IMethod methodtest_expat_incomplete46;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_expat_incomplete46 = ModelTestUtils.getAssertMethod( moduleChilds, "test_expat_incomplete", 0 );
		}
		//Function test:test_sax_parse_exception_str
		{
		IMethod methodtest_sax_parse_exception_str47;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_sax_parse_exception_str47 = ModelTestUtils.getAssertMethod( moduleChilds, "test_sax_parse_exception_str", 0 );
		}
		//Class test
		{
		IType classDummyLocator48;
			IModelElement[] moduleChilds = module.getChildren();
			classDummyLocator48 = ModelTestUtils.getAssertClass( moduleChilds, "DummyLocator" );
			//Function test:__init__
			{
			IMethod method__init__49;
				IModelElement[] classDummyLocator48Childs = classDummyLocator48.getChildren();
				method__init__49 = ModelTestUtils.getAssertMethod( classDummyLocator48Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__49, new String[] {"self", "lineno", "colno"} );
			}
			//Function test:getPublicId
			{
			IMethod methodgetPublicId50;
				IModelElement[] classDummyLocator48Childs = classDummyLocator48.getChildren();
				methodgetPublicId50 = ModelTestUtils.getAssertMethod( classDummyLocator48Childs, "getPublicId", 1 );
				ModelTestUtils.assertParameterNames( methodgetPublicId50, new String[] {"self"} );
			}
			//Function test:getSystemId
			{
			IMethod methodgetSystemId51;
				IModelElement[] classDummyLocator48Childs = classDummyLocator48.getChildren();
				methodgetSystemId51 = ModelTestUtils.getAssertMethod( classDummyLocator48Childs, "getSystemId", 1 );
				ModelTestUtils.assertParameterNames( methodgetSystemId51, new String[] {"self"} );
			}
			//Function test:getLineNumber
			{
			IMethod methodgetLineNumber52;
				IModelElement[] classDummyLocator48Childs = classDummyLocator48.getChildren();
				methodgetLineNumber52 = ModelTestUtils.getAssertMethod( classDummyLocator48Childs, "getLineNumber", 1 );
				ModelTestUtils.assertParameterNames( methodgetLineNumber52, new String[] {"self"} );
			}
			//Function test:getColumnNumber
			{
			IMethod methodgetColumnNumber53;
				IModelElement[] classDummyLocator48Childs = classDummyLocator48.getChildren();
				methodgetColumnNumber53 = ModelTestUtils.getAssertMethod( classDummyLocator48Childs, "getColumnNumber", 1 );
				ModelTestUtils.assertParameterNames( methodgetColumnNumber53, new String[] {"self"} );
			}
		}
		//Function test:verify_empty_attrs
		{
		IMethod methodverify_empty_attrs54;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_empty_attrs54 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_empty_attrs", 1 );
			ModelTestUtils.assertParameterNames( methodverify_empty_attrs54, new String[] {"attrs"} );
		}
		//Function test:verify_attrs_wattr
		{
		IMethod methodverify_attrs_wattr55;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_attrs_wattr55 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_attrs_wattr", 1 );
			ModelTestUtils.assertParameterNames( methodverify_attrs_wattr55, new String[] {"attrs"} );
		}
		//Function test:test_attrs_empty
		{
		IMethod methodtest_attrs_empty56;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_attrs_empty56 = ModelTestUtils.getAssertMethod( moduleChilds, "test_attrs_empty", 0 );
		}
		//Function test:test_attrs_wattr
		{
		IMethod methodtest_attrs_wattr57;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_attrs_wattr57 = ModelTestUtils.getAssertMethod( moduleChilds, "test_attrs_wattr", 0 );
		}
		//Function test:verify_empty_nsattrs
		{
		IMethod methodverify_empty_nsattrs58;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_empty_nsattrs58 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_empty_nsattrs", 1 );
			ModelTestUtils.assertParameterNames( methodverify_empty_nsattrs58, new String[] {"attrs"} );
		}
		//Function test:test_nsattrs_empty
		{
		IMethod methodtest_nsattrs_empty59;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_nsattrs_empty59 = ModelTestUtils.getAssertMethod( moduleChilds, "test_nsattrs_empty", 0 );
		}
		//Function test:test_nsattrs_wattr
		{
		IMethod methodtest_nsattrs_wattr60;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_nsattrs_wattr60 = ModelTestUtils.getAssertMethod( moduleChilds, "test_nsattrs_wattr", 0 );
		}
		//Function test:make_test_output
		{
		IMethod methodmake_test_output61;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_test_output61 = ModelTestUtils.getAssertMethod( moduleChilds, "make_test_output", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "items");
		}

	}
	public void testModelGen214( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
			//Function test:test_new_or_init
			{
			IMethod methodtest_new_or_init4;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_new_or_init4 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_new_or_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_new_or_init4, new String[] {"self"} );
			}
			//Function test:test_uniquification
			{
			IMethod methodtest_uniquification5;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_uniquification5 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_uniquification", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uniquification5, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len6;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_len6 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len6, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains7;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_contains7 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains7, new String[] {"self"} );
			}
			//Function test:test_union
			{
			IMethod methodtest_union8;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_union8 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union8, new String[] {"self"} );
			}
			//Function test:test_or
			{
			IMethod methodtest_or9;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_or9 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_or", 1 );
				ModelTestUtils.assertParameterNames( methodtest_or9, new String[] {"self"} );
			}
			//Function test:test_intersection
			{
			IMethod methodtest_intersection10;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_intersection10 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection10, new String[] {"self"} );
			}
			//Function test:test_and
			{
			IMethod methodtest_and11;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_and11 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_and", 1 );
				ModelTestUtils.assertParameterNames( methodtest_and11, new String[] {"self"} );
			}
			//Function test:test_difference
			{
			IMethod methodtest_difference12;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_difference12 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference12, new String[] {"self"} );
			}
			//Function test:test_sub
			{
			IMethod methodtest_sub13;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_sub13 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub13, new String[] {"self"} );
			}
			//Function test:test_symmetric_difference
			{
			IMethod methodtest_symmetric_difference14;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_symmetric_difference14 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_symmetric_difference14, new String[] {"self"} );
			}
			//Function test:test_xor
			{
			IMethod methodtest_xor15;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_xor15 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_xor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xor15, new String[] {"self"} );
			}
			//Function test:test_equality
			{
			IMethod methodtest_equality16;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_equality16 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equality16, new String[] {"self"} );
			}
			//Function test:test_setOfFrozensets
			{
			IMethod methodtest_setOfFrozensets17;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_setOfFrozensets17 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_setOfFrozensets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setOfFrozensets17, new String[] {"self"} );
			}
			//Function test:test_compare
			{
			IMethod methodtest_compare18;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_compare18 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compare18, new String[] {"self"} );
			}
			//Function test:test_sub_and_super
			{
			IMethod methodtest_sub_and_super19;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_sub_and_super19 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_sub_and_super", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub_and_super19, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling20;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_pickling20 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling20, new String[] {"self"} );
			}
			//Function test:test_deepcopy
			{
			IMethod methodtest_deepcopy21;
				IModelElement[] classTestJointOps2Childs = classTestJointOps2.getChildren();
				methodtest_deepcopy21 = ModelTestUtils.getAssertMethod( classTestJointOps2Childs, "test_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy21, new String[] {"self"} );
				//Class test
				{
				IType classTracer22;
					IModelElement[] methodtest_deepcopy21Childs = methodtest_deepcopy21.getChildren();
					classTracer22 = ModelTestUtils.getAssertClass( methodtest_deepcopy21Childs, "Tracer" );
					//Function test:__init__
					{
					IMethod method__init__23;
						IModelElement[] classTracer22Childs = classTracer22.getChildren();
						method__init__23 = ModelTestUtils.getAssertMethod( classTracer22Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__23, new String[] {"self", "value"} );
					}
					//Function test:__hash__
					{
					IMethod method__hash__24;
						IModelElement[] classTracer22Childs = classTracer22.getChildren();
						method__hash__24 = ModelTestUtils.getAssertMethod( classTracer22Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__24, new String[] {"self"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__25;
						IModelElement[] classTracer22Childs = classTracer22.getChildren();
						method__deepcopy__25 = ModelTestUtils.getAssertMethod( classTracer22Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__25, new String[] {"self", "memo"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestSet26;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSet26 = ModelTestUtils.getAssertClass( moduleChilds, "TestSet" );
			{
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSet26Childs, "thetype");
			}
			//Function test:test_init
			{
			IMethod methodtest_init27;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_init27 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init27, new String[] {"self"} );
			}
			//Function test:test_constructor_identity
			{
			IMethod methodtest_constructor_identity28;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_constructor_identity28 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_constructor_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_identity28, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash29;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_hash29 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash29, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear30;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_clear30 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear30, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy31;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_copy31 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy31, new String[] {"self"} );
			}
			//Function test:test_add
			{
			IMethod methodtest_add32;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_add32 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_add", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add32, new String[] {"self"} );
			}
			//Function test:test_remove
			{
			IMethod methodtest_remove33;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_remove33 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_remove", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove33, new String[] {"self"} );
			}
			//Function test:test_discard
			{
			IMethod methodtest_discard34;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_discard34 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_discard", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard34, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop35;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_pop35 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop35, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update36;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_update36 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update36, new String[] {"self"} );
			}
			//Function test:test_ior
			{
			IMethod methodtest_ior37;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_ior37 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_ior", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ior37, new String[] {"self"} );
			}
			//Function test:test_intersection_update
			{
			IMethod methodtest_intersection_update38;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_intersection_update38 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_intersection_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update38, new String[] {"self"} );
			}
			//Function test:test_iand
			{
			IMethod methodtest_iand39;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_iand39 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_iand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iand39, new String[] {"self"} );
			}
			//Function test:test_difference_update
			{
			IMethod methodtest_difference_update40;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_difference_update40 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update40, new String[] {"self"} );
			}
			//Function test:test_isub
			{
			IMethod methodtest_isub41;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_isub41 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_isub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isub41, new String[] {"self"} );
			}
			//Function test:test_symmetric_difference_update
			{
			IMethod methodtest_symmetric_difference_update42;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_symmetric_difference_update42 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_symmetric_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_symmetric_difference_update42, new String[] {"self"} );
			}
			//Function test:test_ixor
			{
			IMethod methodtest_ixor43;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_ixor43 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_ixor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ixor43, new String[] {"self"} );
			}
			//Function test:test_weakref
			{
			IMethod methodtest_weakref44;
				IModelElement[] classTestSet26Childs = classTestSet26.getChildren();
				methodtest_weakref44 = ModelTestUtils.getAssertMethod( classTestSet26Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref44, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSetSubclass45;
			IModelElement[] moduleChilds = module.getChildren();
			classSetSubclass45 = ModelTestUtils.getAssertClass( moduleChilds, "SetSubclass" );
		}
		//Class test
		{
		IType classTestSetSubclass46;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSetSubclass46 = ModelTestUtils.getAssertClass( moduleChilds, "TestSetSubclass" );
			{
				IModelElement[] classTestSetSubclass46Childs = classTestSetSubclass46.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSetSubclass46Childs, "thetype");
			}
		}
		//Class test
		{
		IType classTestFrozenSet47;
			IModelElement[] moduleChilds = module.getChildren();
			classTestFrozenSet47 = ModelTestUtils.getAssertClass( moduleChilds, "TestFrozenSet" );
			{
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestFrozenSet47Childs, "thetype");
			}
			//Function test:test_init
			{
			IMethod methodtest_init48;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_init48 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init48, new String[] {"self"} );
			}
			//Function test:test_constructor_identity
			{
			IMethod methodtest_constructor_identity49;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_constructor_identity49 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_constructor_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_identity49, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash50;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_hash50 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash50, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy51;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_copy51 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy51, new String[] {"self"} );
			}
			//Function test:test_frozen_as_dictkey
			{
			IMethod methodtest_frozen_as_dictkey52;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_frozen_as_dictkey52 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_frozen_as_dictkey", 1 );
				ModelTestUtils.assertParameterNames( methodtest_frozen_as_dictkey52, new String[] {"self"} );
			}
			//Function test:test_hash_caching
			{
			IMethod methodtest_hash_caching53;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_hash_caching53 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_hash_caching", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_caching53, new String[] {"self"} );
			}
			//Function test:test_hash_effectiveness
			{
			IMethod methodtest_hash_effectiveness54;
				IModelElement[] classTestFrozenSet47Childs = classTestFrozenSet47.getChildren();
				methodtest_hash_effectiveness54 = ModelTestUtils.getAssertMethod( classTestFrozenSet47Childs, "test_hash_effectiveness", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_effectiveness54, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFrozenSetSubclass55;
			IModelElement[] moduleChilds = module.getChildren();
			classFrozenSetSubclass55 = ModelTestUtils.getAssertClass( moduleChilds, "FrozenSetSubclass" );
		}
		//Class test
		{
		IType classTestFrozenSetSubclass56;
			IModelElement[] moduleChilds = module.getChildren();
			classTestFrozenSetSubclass56 = ModelTestUtils.getAssertClass( moduleChilds, "TestFrozenSetSubclass" );
			{
				IModelElement[] classTestFrozenSetSubclass56Childs = classTestFrozenSetSubclass56.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestFrozenSetSubclass56Childs, "thetype");
			}
			//Function test:test_constructor_identity
			{
			IMethod methodtest_constructor_identity57;
				IModelElement[] classTestFrozenSetSubclass56Childs = classTestFrozenSetSubclass56.getChildren();
				methodtest_constructor_identity57 = ModelTestUtils.getAssertMethod( classTestFrozenSetSubclass56Childs, "test_constructor_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_identity57, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy58;
				IModelElement[] classTestFrozenSetSubclass56Childs = classTestFrozenSetSubclass56.getChildren();
				methodtest_copy58 = ModelTestUtils.getAssertMethod( classTestFrozenSetSubclass56Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy58, new String[] {"self"} );
			}
			//Function test:test_nested_empty_constructor
			{
			IMethod methodtest_nested_empty_constructor59;
				IModelElement[] classTestFrozenSetSubclass56Childs = classTestFrozenSetSubclass56.getChildren();
				methodtest_nested_empty_constructor59 = ModelTestUtils.getAssertMethod( classTestFrozenSetSubclass56Childs, "test_nested_empty_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_empty_constructor59, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "empty_set");
		}
		//Class test
		{
		IType classTestBasicOps60;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOps60 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOps" );
			//Function test:test_repr
			{
			IMethod methodtest_repr61;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_repr61 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr61, new String[] {"self"} );
			}
			//Function test:test_print
			{
			IMethod methodtest_print62;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_print62 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_print", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print62, new String[] {"self"} );
			}
			//Function test:test_length
			{
			IMethod methodtest_length63;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_length63 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_length", 1 );
				ModelTestUtils.assertParameterNames( methodtest_length63, new String[] {"self"} );
			}
			//Function test:test_self_equality
			{
			IMethod methodtest_self_equality64;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_self_equality64 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_self_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_equality64, new String[] {"self"} );
			}
			//Function test:test_equivalent_equality
			{
			IMethod methodtest_equivalent_equality65;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_equivalent_equality65 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_equivalent_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equivalent_equality65, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy66;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_copy66 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy66, new String[] {"self"} );
			}
			//Function test:test_self_union
			{
			IMethod methodtest_self_union67;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_self_union67 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_self_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_union67, new String[] {"self"} );
			}
			//Function test:test_empty_union
			{
			IMethod methodtest_empty_union68;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_empty_union68 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_empty_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_union68, new String[] {"self"} );
			}
			//Function test:test_union_empty
			{
			IMethod methodtest_union_empty69;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_union_empty69 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_union_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_empty69, new String[] {"self"} );
			}
			//Function test:test_self_intersection
			{
			IMethod methodtest_self_intersection70;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_self_intersection70 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_self_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_intersection70, new String[] {"self"} );
			}
			//Function test:test_empty_intersection
			{
			IMethod methodtest_empty_intersection71;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_empty_intersection71 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_empty_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_intersection71, new String[] {"self"} );
			}
			//Function test:test_intersection_empty
			{
			IMethod methodtest_intersection_empty72;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_intersection_empty72 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_intersection_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_empty72, new String[] {"self"} );
			}
			//Function test:test_self_symmetric_difference
			{
			IMethod methodtest_self_symmetric_difference73;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_self_symmetric_difference73 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_self_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_symmetric_difference73, new String[] {"self"} );
			}
			//Function test:checkempty_symmetric_difference
			{
			IMethod methodcheckempty_symmetric_difference74;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodcheckempty_symmetric_difference74 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "checkempty_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodcheckempty_symmetric_difference74, new String[] {"self"} );
			}
			//Function test:test_self_difference
			{
			IMethod methodtest_self_difference75;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_self_difference75 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_self_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_difference75, new String[] {"self"} );
			}
			//Function test:test_empty_difference
			{
			IMethod methodtest_empty_difference76;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_empty_difference76 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_empty_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_difference76, new String[] {"self"} );
			}
			//Function test:test_empty_difference_rev
			{
			IMethod methodtest_empty_difference_rev77;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_empty_difference_rev77 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_empty_difference_rev", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_difference_rev77, new String[] {"self"} );
			}
			//Function test:test_iteration
			{
			IMethod methodtest_iteration78;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_iteration78 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_iteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteration78, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling79;
				IModelElement[] classTestBasicOps60Childs = classTestBasicOps60.getChildren();
				methodtest_pickling79 = ModelTestUtils.getAssertMethod( classTestBasicOps60Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling79, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsEmpty80;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsEmpty80 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp81;
				IModelElement[] classTestBasicOpsEmpty80Childs = classTestBasicOpsEmpty80.getChildren();
				methodsetUp81 = ModelTestUtils.getAssertMethod( classTestBasicOpsEmpty80Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp81, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsSingleton82;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsSingleton82 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp83;
				IModelElement[] classTestBasicOpsSingleton82Childs = classTestBasicOpsSingleton82.getChildren();
				methodsetUp83 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton82Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp83, new String[] {"self"} );
			}
			//Function test:test_in
			{
			IMethod methodtest_in84;
				IModelElement[] classTestBasicOpsSingleton82Childs = classTestBasicOpsSingleton82.getChildren();
				methodtest_in84 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton82Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in84, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in85;
				IModelElement[] classTestBasicOpsSingleton82Childs = classTestBasicOpsSingleton82.getChildren();
				methodtest_not_in85 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton82Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in85, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTuple86;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTuple86 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp87;
				IModelElement[] classTestBasicOpsTuple86Childs = classTestBasicOpsTuple86.getChildren();
				methodsetUp87 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple86Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp87, new String[] {"self"} );
			}
			//Function test:test_in
			{
			IMethod methodtest_in88;
				IModelElement[] classTestBasicOpsTuple86Childs = classTestBasicOpsTuple86.getChildren();
				methodtest_in88 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple86Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in88, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in89;
				IModelElement[] classTestBasicOpsTuple86Childs = classTestBasicOpsTuple86.getChildren();
				methodtest_not_in89 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple86Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in89, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTriple90;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTriple90 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp91;
				IModelElement[] classTestBasicOpsTriple90Childs = classTestBasicOpsTriple90.getChildren();
				methodsetUp91 = ModelTestUtils.getAssertMethod( classTestBasicOpsTriple90Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp91, new String[] {"self"} );
			}
		}
		//Function test:baditer
		{
		IMethod methodbaditer92;
			IModelElement[] moduleChilds = module.getChildren();
			methodbaditer92 = ModelTestUtils.getAssertMethod( moduleChilds, "baditer", 0 );
		}
		//Function test:gooditer
		{
		IMethod methodgooditer93;
			IModelElement[] moduleChilds = module.getChildren();
			methodgooditer93 = ModelTestUtils.getAssertMethod( moduleChilds, "gooditer", 0 );
		}
		//Class test
		{
		IType classTestExceptionPropagation94;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExceptionPropagation94 = ModelTestUtils.getAssertClass( moduleChilds, "TestExceptionPropagation" );
			//Function test:test_instanceWithException
			{
			IMethod methodtest_instanceWithException95;
				IModelElement[] classTestExceptionPropagation94Childs = classTestExceptionPropagation94.getChildren();
				methodtest_instanceWithException95 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation94Childs, "test_instanceWithException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instanceWithException95, new String[] {"self"} );
			}
			//Function test:test_instancesWithoutException
			{
			IMethod methodtest_instancesWithoutException96;
				IModelElement[] classTestExceptionPropagation94Childs = classTestExceptionPropagation94.getChildren();
				methodtest_instancesWithoutException96 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation94Childs, "test_instancesWithoutException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instancesWithoutException96, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSetOfSets97;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSetOfSets97 = ModelTestUtils.getAssertClass( moduleChilds, "TestSetOfSets" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor98;
				IModelElement[] classTestSetOfSets97Childs = classTestSetOfSets97.getChildren();
				methodtest_constructor98 = ModelTestUtils.getAssertMethod( classTestSetOfSets97Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor98, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBinaryOps99;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryOps99 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryOps" );
			//Function test:setUp
			{
			IMethod methodsetUp100;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodsetUp100 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp100, new String[] {"self"} );
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq101;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_eq101 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq101, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset102;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_union_subset102 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset102, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset103;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_union_superset103 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset103, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap104;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_union_overlap104 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap104, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap105;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_union_non_overlap105 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap105, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset106;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_intersection_subset106 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset106, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset107;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_intersection_superset107 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset107, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap108;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_intersection_overlap108 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap108, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap109;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_intersection_non_overlap109 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap109, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset110;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_sym_difference_subset110 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset110, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset111;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_sym_difference_superset111 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset111, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap112;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_sym_difference_overlap112 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap112, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap113;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_sym_difference_non_overlap113 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap113, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp114;
				IModelElement[] classTestBinaryOps99Childs = classTestBinaryOps99.getChildren();
				methodtest_cmp114 = ModelTestUtils.getAssertMethod( classTestBinaryOps99Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp114, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestUpdateOps115;
			IModelElement[] moduleChilds = module.getChildren();
			classTestUpdateOps115 = ModelTestUtils.getAssertClass( moduleChilds, "TestUpdateOps" );
			//Function test:setUp
			{
			IMethod methodsetUp116;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodsetUp116 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp116, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset117;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_union_subset117 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset117, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset118;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_union_superset118 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset118, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap119;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_union_overlap119 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap119, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap120;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_union_non_overlap120 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap120, new String[] {"self"} );
			}
			//Function test:test_union_method_call
			{
			IMethod methodtest_union_method_call121;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_union_method_call121 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_union_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_method_call121, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset122;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_intersection_subset122 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset122, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset123;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_intersection_superset123 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset123, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap124;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_intersection_overlap124 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap124, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap125;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_intersection_non_overlap125 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap125, new String[] {"self"} );
			}
			//Function test:test_intersection_method_call
			{
			IMethod methodtest_intersection_method_call126;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_intersection_method_call126 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_intersection_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_method_call126, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset127;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_sym_difference_subset127 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset127, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset128;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_sym_difference_superset128 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset128, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap129;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_sym_difference_overlap129 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap129, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap130;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_sym_difference_non_overlap130 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap130, new String[] {"self"} );
			}
			//Function test:test_sym_difference_method_call
			{
			IMethod methodtest_sym_difference_method_call131;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_sym_difference_method_call131 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_sym_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_method_call131, new String[] {"self"} );
			}
			//Function test:test_difference_subset
			{
			IMethod methodtest_difference_subset132;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_difference_subset132 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_subset132, new String[] {"self"} );
			}
			//Function test:test_difference_superset
			{
			IMethod methodtest_difference_superset133;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_difference_superset133 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_superset133, new String[] {"self"} );
			}
			//Function test:test_difference_overlap
			{
			IMethod methodtest_difference_overlap134;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_difference_overlap134 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_overlap134, new String[] {"self"} );
			}
			//Function test:test_difference_non_overlap
			{
			IMethod methodtest_difference_non_overlap135;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_difference_non_overlap135 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_non_overlap135, new String[] {"self"} );
			}
			//Function test:test_difference_method_call
			{
			IMethod methodtest_difference_method_call136;
				IModelElement[] classTestUpdateOps115Childs = classTestUpdateOps115.getChildren();
				methodtest_difference_method_call136 = ModelTestUtils.getAssertMethod( classTestUpdateOps115Childs, "test_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_method_call136, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMutate137;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMutate137 = ModelTestUtils.getAssertClass( moduleChilds, "TestMutate" );
			//Function test:setUp
			{
			IMethod methodsetUp138;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodsetUp138 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp138, new String[] {"self"} );
			}
			//Function test:test_add_present
			{
			IMethod methodtest_add_present139;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_add_present139 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_add_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_present139, new String[] {"self"} );
			}
			//Function test:test_add_absent
			{
			IMethod methodtest_add_absent140;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_add_absent140 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_add_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_absent140, new String[] {"self"} );
			}
			//Function test:test_add_until_full
			{
			IMethod methodtest_add_until_full141;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_add_until_full141 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_add_until_full", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_until_full141, new String[] {"self"} );
			}
			//Function test:test_remove_present
			{
			IMethod methodtest_remove_present142;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_remove_present142 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_remove_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_present142, new String[] {"self"} );
			}
			//Function test:test_remove_absent
			{
			IMethod methodtest_remove_absent143;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_remove_absent143 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_remove_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_absent143, new String[] {"self"} );
			}
			//Function test:test_remove_until_empty
			{
			IMethod methodtest_remove_until_empty144;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_remove_until_empty144 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_remove_until_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_until_empty144, new String[] {"self"} );
			}
			//Function test:test_discard_present
			{
			IMethod methodtest_discard_present145;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_discard_present145 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_discard_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_present145, new String[] {"self"} );
			}
			//Function test:test_discard_absent
			{
			IMethod methodtest_discard_absent146;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_discard_absent146 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_discard_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_absent146, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear147;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_clear147 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear147, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop148;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_pop148 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop148, new String[] {"self"} );
			}
			//Function test:test_update_empty_tuple
			{
			IMethod methodtest_update_empty_tuple149;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_update_empty_tuple149 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_update_empty_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_empty_tuple149, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_overlap
			{
			IMethod methodtest_update_unit_tuple_overlap150;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_update_unit_tuple_overlap150 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_update_unit_tuple_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_overlap150, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_non_overlap
			{
			IMethod methodtest_update_unit_tuple_non_overlap151;
				IModelElement[] classTestMutate137Childs = classTestMutate137.getChildren();
				methodtest_update_unit_tuple_non_overlap151 = ModelTestUtils.getAssertMethod( classTestMutate137Childs, "test_update_unit_tuple_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_non_overlap151, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsets152;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsets152 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsets" );
			{
				IModelElement[] classTestSubsets152Childs = classTestSubsets152.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets152Childs, "case2method");
			}
			{
				IModelElement[] classTestSubsets152Childs = classTestSubsets152.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets152Childs, "reverse");
			}
			//Function test:test_issubset
			{
			IMethod methodtest_issubset153;
				IModelElement[] classTestSubsets152Childs = classTestSubsets152.getChildren();
				methodtest_issubset153 = ModelTestUtils.getAssertMethod( classTestSubsets152Childs, "test_issubset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubset153, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsetEqualEmpty154;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualEmpty154 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualEmpty" );
			{
				IModelElement[] classTestSubsetEqualEmpty154Childs = classTestSubsetEqualEmpty154.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty154Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty154Childs = classTestSubsetEqualEmpty154.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty154Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty154Childs = classTestSubsetEqualEmpty154.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty154Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty154Childs = classTestSubsetEqualEmpty154.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty154Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEqualNonEmpty155;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualNonEmpty155 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualNonEmpty" );
			{
				IModelElement[] classTestSubsetEqualNonEmpty155Childs = classTestSubsetEqualNonEmpty155.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty155Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty155Childs = classTestSubsetEqualNonEmpty155.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty155Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty155Childs = classTestSubsetEqualNonEmpty155.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty155Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty155Childs = classTestSubsetEqualNonEmpty155.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty155Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEmptyNonEmpty156;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEmptyNonEmpty156 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEmptyNonEmpty" );
			{
				IModelElement[] classTestSubsetEmptyNonEmpty156Childs = classTestSubsetEmptyNonEmpty156.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty156Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty156Childs = classTestSubsetEmptyNonEmpty156.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty156Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty156Childs = classTestSubsetEmptyNonEmpty156.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty156Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty156Childs = classTestSubsetEmptyNonEmpty156.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty156Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetPartial157;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetPartial157 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetPartial" );
			{
				IModelElement[] classTestSubsetPartial157Childs = classTestSubsetPartial157.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial157Childs, "left");
			}
			{
				IModelElement[] classTestSubsetPartial157Childs = classTestSubsetPartial157.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial157Childs, "right");
			}
			{
				IModelElement[] classTestSubsetPartial157Childs = classTestSubsetPartial157.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial157Childs, "name");
			}
			{
				IModelElement[] classTestSubsetPartial157Childs = classTestSubsetPartial157.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial157Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetNonOverlap158;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetNonOverlap158 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetNonOverlap" );
			{
				IModelElement[] classTestSubsetNonOverlap158Childs = classTestSubsetNonOverlap158.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap158Childs, "left");
			}
			{
				IModelElement[] classTestSubsetNonOverlap158Childs = classTestSubsetNonOverlap158.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap158Childs, "right");
			}
			{
				IModelElement[] classTestSubsetNonOverlap158Childs = classTestSubsetNonOverlap158.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap158Childs, "name");
			}
			{
				IModelElement[] classTestSubsetNonOverlap158Childs = classTestSubsetNonOverlap158.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap158Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestOnlySetsInBinaryOps159;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsInBinaryOps159 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsInBinaryOps" );
			//Function test:test_eq_ne
			{
			IMethod methodtest_eq_ne160;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_eq_ne160 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_eq_ne", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq_ne160, new String[] {"self"} );
			}
			//Function test:test_ge_gt_le_lt
			{
			IMethod methodtest_ge_gt_le_lt161;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_ge_gt_le_lt161 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_ge_gt_le_lt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ge_gt_le_lt161, new String[] {"self"} );
			}
			//Function test:test_update_operator
			{
			IMethod methodtest_update_operator162;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_update_operator162 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_operator162, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update163;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_update163 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update163, new String[] {"self"} );
			}
			//Function test:test_union
			{
			IMethod methodtest_union164;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_union164 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union164, new String[] {"self"} );
			}
			//Function test:test_intersection_update_operator
			{
			IMethod methodtest_intersection_update_operator165;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_intersection_update_operator165 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_intersection_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update_operator165, new String[] {"self"} );
			}
			//Function test:test_intersection_update
			{
			IMethod methodtest_intersection_update166;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_intersection_update166 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_intersection_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update166, new String[] {"self"} );
			}
			//Function test:test_intersection
			{
			IMethod methodtest_intersection167;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_intersection167 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection167, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update_operator
			{
			IMethod methodtest_sym_difference_update_operator168;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_sym_difference_update_operator168 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_sym_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update_operator168, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update
			{
			IMethod methodtest_sym_difference_update169;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_sym_difference_update169 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_sym_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update169, new String[] {"self"} );
			}
			//Function test:test_sym_difference
			{
			IMethod methodtest_sym_difference170;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_sym_difference170 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_sym_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference170, new String[] {"self"} );
			}
			//Function test:test_difference_update_operator
			{
			IMethod methodtest_difference_update_operator171;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_difference_update_operator171 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update_operator171, new String[] {"self"} );
			}
			//Function test:test_difference_update
			{
			IMethod methodtest_difference_update172;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_difference_update172 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update172, new String[] {"self"} );
			}
			//Function test:test_difference
			{
			IMethod methodtest_difference173;
				IModelElement[] classTestOnlySetsInBinaryOps159Childs = classTestOnlySetsInBinaryOps159.getChildren();
				methodtest_difference173 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps159Childs, "test_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference173, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsNumeric174;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsNumeric174 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsNumeric" );
			//Function test:setUp
			{
			IMethod methodsetUp175;
				IModelElement[] classTestOnlySetsNumeric174Childs = classTestOnlySetsNumeric174.getChildren();
				methodsetUp175 = ModelTestUtils.getAssertMethod( classTestOnlySetsNumeric174Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp175, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsDict176;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsDict176 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsDict" );
			//Function test:setUp
			{
			IMethod methodsetUp177;
				IModelElement[] classTestOnlySetsDict176Childs = classTestOnlySetsDict176.getChildren();
				methodsetUp177 = ModelTestUtils.getAssertMethod( classTestOnlySetsDict176Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp177, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsOperator178;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsOperator178 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsOperator" );
			//Function test:setUp
			{
			IMethod methodsetUp179;
				IModelElement[] classTestOnlySetsOperator178Childs = classTestOnlySetsOperator178.getChildren();
				methodsetUp179 = ModelTestUtils.getAssertMethod( classTestOnlySetsOperator178Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp179, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsTuple180;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsTuple180 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp181;
				IModelElement[] classTestOnlySetsTuple180Childs = classTestOnlySetsTuple180.getChildren();
				methodsetUp181 = ModelTestUtils.getAssertMethod( classTestOnlySetsTuple180Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp181, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsString182;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsString182 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsString" );
			//Function test:setUp
			{
			IMethod methodsetUp183;
				IModelElement[] classTestOnlySetsString182Childs = classTestOnlySetsString182.getChildren();
				methodsetUp183 = ModelTestUtils.getAssertMethod( classTestOnlySetsString182Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp183, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsGenerator184;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsGenerator184 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsGenerator" );
			//Function test:setUp
			{
			IMethod methodsetUp185;
				IModelElement[] classTestOnlySetsGenerator184Childs = classTestOnlySetsGenerator184.getChildren();
				methodsetUp185 = ModelTestUtils.getAssertMethod( classTestOnlySetsGenerator184Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp185, new String[] {"self"} );
				//Function test:gen
				{
				IMethod methodgen186;
					IModelElement[] methodsetUp185Childs = methodsetUp185.getChildren();
					methodgen186 = ModelTestUtils.getAssertMethod( methodsetUp185Childs, "gen", 0 );
				}
			}
		}
		//Class test
		{
		IType classTestCopying187;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopying187 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopying" );
			//Function test:test_copy
			{
			IMethod methodtest_copy188;
				IModelElement[] classTestCopying187Childs = classTestCopying187.getChildren();
				methodtest_copy188 = ModelTestUtils.getAssertMethod( classTestCopying187Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy188, new String[] {"self"} );
			}
			//Function test:test_deep_copy
			{
			IMethod methodtest_deep_copy189;
				IModelElement[] classTestCopying187Childs = classTestCopying187.getChildren();
				methodtest_deep_copy189 = ModelTestUtils.getAssertMethod( classTestCopying187Childs, "test_deep_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deep_copy189, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingEmpty190;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingEmpty190 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp191;
				IModelElement[] classTestCopyingEmpty190Childs = classTestCopyingEmpty190.getChildren();
				methodsetUp191 = ModelTestUtils.getAssertMethod( classTestCopyingEmpty190Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp191, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingSingleton192;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingSingleton192 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp193;
				IModelElement[] classTestCopyingSingleton192Childs = classTestCopyingSingleton192.getChildren();
				methodsetUp193 = ModelTestUtils.getAssertMethod( classTestCopyingSingleton192Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp193, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingTriple194;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTriple194 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp195;
				IModelElement[] classTestCopyingTriple194Childs = classTestCopyingTriple194.getChildren();
				methodsetUp195 = ModelTestUtils.getAssertMethod( classTestCopyingTriple194Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp195, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingTuple196;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTuple196 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp197;
				IModelElement[] classTestCopyingTuple196Childs = classTestCopyingTuple196.getChildren();
				methodsetUp197 = ModelTestUtils.getAssertMethod( classTestCopyingTuple196Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp197, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingNested198;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingNested198 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingNested" );
			//Function test:setUp
			{
			IMethod methodsetUp199;
				IModelElement[] classTestCopyingNested198Childs = classTestCopyingNested198.getChildren();
				methodsetUp199 = ModelTestUtils.getAssertMethod( classTestCopyingNested198Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp199, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestIdentities200;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIdentities200 = ModelTestUtils.getAssertClass( moduleChilds, "TestIdentities" );
			//Function test:setUp
			{
			IMethod methodsetUp201;
				IModelElement[] classTestIdentities200Childs = classTestIdentities200.getChildren();
				methodsetUp201 = ModelTestUtils.getAssertMethod( classTestIdentities200Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp201, new String[] {"self"} );
			}
			//Function test:test_binopsVsSubsets
			{
			IMethod methodtest_binopsVsSubsets202;
				IModelElement[] classTestIdentities200Childs = classTestIdentities200.getChildren();
				methodtest_binopsVsSubsets202 = ModelTestUtils.getAssertMethod( classTestIdentities200Childs, "test_binopsVsSubsets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binopsVsSubsets202, new String[] {"self"} );
			}
			//Function test:test_commutativity
			{
			IMethod methodtest_commutativity203;
				IModelElement[] classTestIdentities200Childs = classTestIdentities200.getChildren();
				methodtest_commutativity203 = ModelTestUtils.getAssertMethod( classTestIdentities200Childs, "test_commutativity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commutativity203, new String[] {"self"} );
			}
			//Function test:test_summations
			{
			IMethod methodtest_summations204;
				IModelElement[] classTestIdentities200Childs = classTestIdentities200.getChildren();
				methodtest_summations204 = ModelTestUtils.getAssertMethod( classTestIdentities200Childs, "test_summations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_summations204, new String[] {"self"} );
			}
			//Function test:test_exclusion
			{
			IMethod methodtest_exclusion205;
				IModelElement[] classTestIdentities200Childs = classTestIdentities200.getChildren();
				methodtest_exclusion205 = ModelTestUtils.getAssertMethod( classTestIdentities200Childs, "test_exclusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exclusion205, new String[] {"self"} );
			}
		}
		//Function test:R
		{
		IMethod methodR206;
			IModelElement[] moduleChilds = module.getChildren();
			methodR206 = ModelTestUtils.getAssertMethod( moduleChilds, "R", 1 );
			ModelTestUtils.assertParameterNames( methodR206, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classG207;
			IModelElement[] moduleChilds = module.getChildren();
			classG207 = ModelTestUtils.getAssertClass( moduleChilds, "G" );
			//Function test:__init__
			{
			IMethod method__init__208;
				IModelElement[] classG207Childs = classG207.getChildren();
				method__init__208 = ModelTestUtils.getAssertMethod( classG207Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__208, new String[] {"self", "seqn"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__209;
				IModelElement[] classG207Childs = classG207.getChildren();
				method__getitem__209 = ModelTestUtils.getAssertMethod( classG207Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__209, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI210;
			IModelElement[] moduleChilds = module.getChildren();
			classI210 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__211;
				IModelElement[] classI210Childs = classI210.getChildren();
				method__init__211 = ModelTestUtils.getAssertMethod( classI210Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__211, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__212;
				IModelElement[] classI210Childs = classI210.getChildren();
				method__iter__212 = ModelTestUtils.getAssertMethod( classI210Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__212, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext213;
				IModelElement[] classI210Childs = classI210.getChildren();
				methodnext213 = ModelTestUtils.getAssertMethod( classI210Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext213, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg214;
			IModelElement[] moduleChilds = module.getChildren();
			classIg214 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__215;
				IModelElement[] classIg214Childs = classIg214.getChildren();
				method__init__215 = ModelTestUtils.getAssertMethod( classIg214Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__215, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__216;
				IModelElement[] classIg214Childs = classIg214.getChildren();
				method__iter__216 = ModelTestUtils.getAssertMethod( classIg214Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__216, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX217;
			IModelElement[] moduleChilds = module.getChildren();
			classX217 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__218;
				IModelElement[] classX217Childs = classX217.getChildren();
				method__init__218 = ModelTestUtils.getAssertMethod( classX217Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__218, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext219;
				IModelElement[] classX217Childs = classX217.getChildren();
				methodnext219 = ModelTestUtils.getAssertMethod( classX217Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext219, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN220;
			IModelElement[] moduleChilds = module.getChildren();
			classN220 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__221;
				IModelElement[] classN220Childs = classN220.getChildren();
				method__init__221 = ModelTestUtils.getAssertMethod( classN220Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__221, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__222;
				IModelElement[] classN220Childs = classN220.getChildren();
				method__iter__222 = ModelTestUtils.getAssertMethod( classN220Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__222, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE223;
			IModelElement[] moduleChilds = module.getChildren();
			classE223 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__224;
				IModelElement[] classE223Childs = classE223.getChildren();
				method__init__224 = ModelTestUtils.getAssertMethod( classE223Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__224, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__225;
				IModelElement[] classE223Childs = classE223.getChildren();
				method__iter__225 = ModelTestUtils.getAssertMethod( classE223Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__225, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext226;
				IModelElement[] classE223Childs = classE223.getChildren();
				methodnext226 = ModelTestUtils.getAssertMethod( classE223Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext226, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS227;
			IModelElement[] moduleChilds = module.getChildren();
			classS227 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__228;
				IModelElement[] classS227Childs = classS227.getChildren();
				method__init__228 = ModelTestUtils.getAssertMethod( classS227Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__228, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__229;
				IModelElement[] classS227Childs = classS227.getChildren();
				method__iter__229 = ModelTestUtils.getAssertMethod( classS227Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__229, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext230;
				IModelElement[] classS227Childs = classS227.getChildren();
				methodnext230 = ModelTestUtils.getAssertMethod( classS227Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext230, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL231;
			IModelElement[] moduleChilds = module.getChildren();
			methodL231 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL231, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestVariousIteratorArgs232;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVariousIteratorArgs232 = ModelTestUtils.getAssertClass( moduleChilds, "TestVariousIteratorArgs" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor233;
				IModelElement[] classTestVariousIteratorArgs232Childs = classTestVariousIteratorArgs232.getChildren();
				methodtest_constructor233 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs232Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor233, new String[] {"self"} );
			}
			//Function test:test_inline_methods
			{
			IMethod methodtest_inline_methods234;
				IModelElement[] classTestVariousIteratorArgs232Childs = classTestVariousIteratorArgs232.getChildren();
				methodtest_inline_methods234 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs232Childs, "test_inline_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inline_methods234, new String[] {"self"} );
			}
			//Function test:test_inplace_methods
			{
			IMethod methodtest_inplace_methods235;
				IModelElement[] classTestVariousIteratorArgs232Childs = classTestVariousIteratorArgs232.getChildren();
				methodtest_inplace_methods235 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs232Childs, "test_inplace_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inplace_methods235, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main236;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main236 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main236, new String[] {"verbose"} );
		}

	}
	public void testModelGen216( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
			//Function test:testEval
			{
			IMethod methodtestEval2;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEval2 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEval", 1 );
				ModelTestUtils.assertParameterNames( methodtestEval2, new String[] {"self"} );
			}
			//Function test:testEvalException
			{
			IMethod methodtestEvalException3;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalException3 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalException", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalException3, new String[] {"self"} );
			}
			//Function test:testEvalException2
			{
			IMethod methodtestEvalException24;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalException24 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalException2", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalException24, new String[] {"self"} );
			}
			//Function test:testCall
			{
			IMethod methodtestCall5;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestCall5 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testCall", 1 );
				ModelTestUtils.assertParameterNames( methodtestCall5, new String[] {"self"} );
			}
			//Function test:testCallException
			{
			IMethod methodtestCallException6;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestCallException6 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testCallException", 1 );
				ModelTestUtils.assertParameterNames( methodtestCallException6, new String[] {"self"} );
			}
			//Function test:testCallException2
			{
			IMethod methodtestCallException27;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestCallException27 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testCallException2", 1 );
				ModelTestUtils.assertParameterNames( methodtestCallException27, new String[] {"self"} );
			}
			//Function test:testSetVar
			{
			IMethod methodtestSetVar8;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestSetVar8 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testSetVar", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetVar8, new String[] {"self"} );
			}
			//Function test:testSetVarArray
			{
			IMethod methodtestSetVarArray9;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestSetVarArray9 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testSetVarArray", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetVarArray9, new String[] {"self"} );
			}
			//Function test:testGetVar
			{
			IMethod methodtestGetVar10;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVar10 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVar", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVar10, new String[] {"self"} );
			}
			//Function test:testGetVarArray
			{
			IMethod methodtestGetVarArray11;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVarArray11 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVarArray", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVarArray11, new String[] {"self"} );
			}
			//Function test:testGetVarException
			{
			IMethod methodtestGetVarException12;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVarException12 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVarException", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVarException12, new String[] {"self"} );
			}
			//Function test:testGetVarArrayException
			{
			IMethod methodtestGetVarArrayException13;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestGetVarArrayException13 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testGetVarArrayException", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetVarArrayException13, new String[] {"self"} );
			}
			//Function test:testUnsetVar
			{
			IMethod methodtestUnsetVar14;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestUnsetVar14 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testUnsetVar", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnsetVar14, new String[] {"self"} );
			}
			//Function test:testUnsetVarArray
			{
			IMethod methodtestUnsetVarArray15;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestUnsetVarArray15 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testUnsetVarArray", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnsetVarArray15, new String[] {"self"} );
			}
			//Function test:testUnsetVarException
			{
			IMethod methodtestUnsetVarException16;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestUnsetVarException16 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testUnsetVarException", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnsetVarException16, new String[] {"self"} );
			}
			//Function test:testEvalFile
			{
			IMethod methodtestEvalFile17;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalFile17 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalFile", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalFile17, new String[] {"self"} );
			}
			//Function test:testEvalFileException
			{
			IMethod methodtestEvalFileException18;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestEvalFileException18 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testEvalFileException", 1 );
				ModelTestUtils.assertParameterNames( methodtestEvalFileException18, new String[] {"self"} );
			}
			//Function test:testPackageRequireException
			{
			IMethod methodtestPackageRequireException19;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestPackageRequireException19 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testPackageRequireException", 1 );
				ModelTestUtils.assertParameterNames( methodtestPackageRequireException19, new String[] {"self"} );
			}
			//Function test:testLoadTk
			{
			IMethod methodtestLoadTk20;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestLoadTk20 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testLoadTk", 1 );
				ModelTestUtils.assertParameterNames( methodtestLoadTk20, new String[] {"self"} );
			}
			//Function test:testLoadTkFailure
			{
			IMethod methodtestLoadTkFailure21;
				IModelElement[] classTclTest0Childs = classTclTest0.getChildren();
				methodtestLoadTkFailure21 = ModelTestUtils.getAssertMethod( classTclTest0Childs, "testLoadTkFailure", 1 );
				ModelTestUtils.assertParameterNames( methodtestLoadTkFailure21, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen225( ) throws Exception {
		String prj = "pytests_4";
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
			//Function test:write
			{
			IMethod methodwrite2;
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				methodwrite2 = ModelTestUtils.getAssertMethod( classQueue0Childs, "write", 2 );
				ModelTestUtils.assertParameterNames( methodwrite2, new String[] {"self", "chars"} );
			}
			//Function test:read
			{
			IMethod methodread3;
				IModelElement[] classQueue0Childs = classQueue0.getChildren();
				methodread3 = ModelTestUtils.getAssertMethod( classQueue0Childs, "read", 2 );
				ModelTestUtils.assertParameterNames( methodread3, new String[] {"self", "size"} );
			}
		}
		//Class test
		{
		IType classReadTest4;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTest4 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTest" );
			//Function test:test_seek
			{
			IMethod methodtest_seek5;
				IModelElement[] classReadTest4Childs = classReadTest4.getChildren();
				methodtest_seek5 = ModelTestUtils.getAssertMethod( classReadTest4Childs, "test_seek", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seek5, new String[] {"self"} );
			}
			//Function test:check_partial
			{
			IMethod methodcheck_partial6;
				IModelElement[] classReadTest4Childs = classReadTest4.getChildren();
				methodcheck_partial6 = ModelTestUtils.getAssertMethod( classReadTest4Childs, "check_partial", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_partial6, new String[] {"self", "input", "partialresults"} );
			}
			//Function test:test_readline
			{
			IMethod methodtest_readline7;
				IModelElement[] classReadTest4Childs = classReadTest4.getChildren();
				methodtest_readline7 = ModelTestUtils.getAssertMethod( classReadTest4Childs, "test_readline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readline7, new String[] {"self"} );
				//Function test:getreader
				{
				IMethod methodgetreader8;
					IModelElement[] methodtest_readline7Childs = methodtest_readline7.getChildren();
					methodgetreader8 = ModelTestUtils.getAssertMethod( methodtest_readline7Childs, "getreader", 1 );
					ModelTestUtils.assertParameterNames( methodgetreader8, new String[] {"input"} );
				}
				//Function test:readalllines
				{
				IMethod methodreadalllines9;
					IModelElement[] methodtest_readline7Childs = methodtest_readline7.getChildren();
					methodreadalllines9 = ModelTestUtils.getAssertMethod( methodtest_readline7Childs, "readalllines", 2 );
					ModelTestUtils.assertParameterNames( methodreadalllines9, new String[] {"input", "keepends"} );
				}
			}
			//Function test:test_readlinequeue
			{
			IMethod methodtest_readlinequeue10;
				IModelElement[] classReadTest4Childs = classReadTest4.getChildren();
				methodtest_readlinequeue10 = ModelTestUtils.getAssertMethod( classReadTest4Childs, "test_readlinequeue", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlinequeue10, new String[] {"self"} );
			}
			//Function test:test_bug1098990_a
			{
			IMethod methodtest_bug1098990_a11;
				IModelElement[] classReadTest4Childs = classReadTest4.getChildren();
				methodtest_bug1098990_a11 = ModelTestUtils.getAssertMethod( classReadTest4Childs, "test_bug1098990_a", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug1098990_a11, new String[] {"self"} );
			}
			//Function test:test_bug1098990_b
			{
			IMethod methodtest_bug1098990_b12;
				IModelElement[] classReadTest4Childs = classReadTest4.getChildren();
				methodtest_bug1098990_b12 = ModelTestUtils.getAssertMethod( classReadTest4Childs, "test_bug1098990_b", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug1098990_b12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF16Test13;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF16Test13 = ModelTestUtils.getAssertClass( moduleChilds, "UTF16Test" );
			{
				IModelElement[] classUTF16Test13Childs = classUTF16Test13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16Test13Childs, "encoding");
			}
			{
				IModelElement[] classUTF16Test13Childs = classUTF16Test13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16Test13Childs, "spamle");
			}
			{
				IModelElement[] classUTF16Test13Childs = classUTF16Test13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16Test13Childs, "spambe");
			}
			//Function test:test_only_one_bom
			{
			IMethod methodtest_only_one_bom14;
				IModelElement[] classUTF16Test13Childs = classUTF16Test13.getChildren();
				methodtest_only_one_bom14 = ModelTestUtils.getAssertMethod( classUTF16Test13Childs, "test_only_one_bom", 1 );
				ModelTestUtils.assertParameterNames( methodtest_only_one_bom14, new String[] {"self"} );
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial15;
				IModelElement[] classUTF16Test13Childs = classUTF16Test13.getChildren();
				methodtest_partial15 = ModelTestUtils.getAssertMethod( classUTF16Test13Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial15, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF16LETest16;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF16LETest16 = ModelTestUtils.getAssertClass( moduleChilds, "UTF16LETest" );
			{
				IModelElement[] classUTF16LETest16Childs = classUTF16LETest16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16LETest16Childs, "encoding");
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial17;
				IModelElement[] classUTF16LETest16Childs = classUTF16LETest16.getChildren();
				methodtest_partial17 = ModelTestUtils.getAssertMethod( classUTF16LETest16Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF16BETest18;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF16BETest18 = ModelTestUtils.getAssertClass( moduleChilds, "UTF16BETest" );
			{
				IModelElement[] classUTF16BETest18Childs = classUTF16BETest18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF16BETest18Childs, "encoding");
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial19;
				IModelElement[] classUTF16BETest18Childs = classUTF16BETest18.getChildren();
				methodtest_partial19 = ModelTestUtils.getAssertMethod( classUTF16BETest18Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUTF8Test20;
			IModelElement[] moduleChilds = module.getChildren();
			classUTF8Test20 = ModelTestUtils.getAssertClass( moduleChilds, "UTF8Test" );
			{
				IModelElement[] classUTF8Test20Childs = classUTF8Test20.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUTF8Test20Childs, "encoding");
			}
			//Function test:test_partial
			{
			IMethod methodtest_partial21;
				IModelElement[] classUTF8Test20Childs = classUTF8Test20.getChildren();
				methodtest_partial21 = ModelTestUtils.getAssertMethod( classUTF8Test20Childs, "test_partial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_partial21, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEscapeDecodeTest22;
			IModelElement[] moduleChilds = module.getChildren();
			classEscapeDecodeTest22 = ModelTestUtils.getAssertClass( moduleChilds, "EscapeDecodeTest" );
			//Function test:test_empty_escape_decode
			{
			IMethod methodtest_empty_escape_decode23;
				IModelElement[] classEscapeDecodeTest22Childs = classEscapeDecodeTest22.getChildren();
				methodtest_empty_escape_decode23 = ModelTestUtils.getAssertMethod( classEscapeDecodeTest22Childs, "test_empty_escape_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_escape_decode23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRecodingTest24;
			IModelElement[] moduleChilds = module.getChildren();
			classRecodingTest24 = ModelTestUtils.getAssertClass( moduleChilds, "RecodingTest" );
			//Function test:test_recoding
			{
			IMethod methodtest_recoding25;
				IModelElement[] classRecodingTest24Childs = classRecodingTest24.getChildren();
				methodtest_recoding25 = ModelTestUtils.getAssertMethod( classRecodingTest24Childs, "test_recoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recoding25, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "punycode_testcases");
		}
		//Class test
		{
		IType classPunycodeTest26;
			IModelElement[] moduleChilds = module.getChildren();
			classPunycodeTest26 = ModelTestUtils.getAssertClass( moduleChilds, "PunycodeTest" );
			//Function test:test_encode
			{
			IMethod methodtest_encode27;
				IModelElement[] classPunycodeTest26Childs = classPunycodeTest26.getChildren();
				methodtest_encode27 = ModelTestUtils.getAssertMethod( classPunycodeTest26Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode27, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode28;
				IModelElement[] classPunycodeTest26Childs = classPunycodeTest26.getChildren();
				methodtest_decode28 = ModelTestUtils.getAssertMethod( classPunycodeTest26Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode28, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nameprep_tests");
		}
		//Class test
		{
		IType classNameprepTest29;
			IModelElement[] moduleChilds = module.getChildren();
			classNameprepTest29 = ModelTestUtils.getAssertClass( moduleChilds, "NameprepTest" );
			//Function test:test_nameprep
			{
			IMethod methodtest_nameprep30;
				IModelElement[] classNameprepTest29Childs = classNameprepTest29.getChildren();
				methodtest_nameprep30 = ModelTestUtils.getAssertMethod( classNameprepTest29Childs, "test_nameprep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nameprep30, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCodecTest31;
			IModelElement[] moduleChilds = module.getChildren();
			classCodecTest31 = ModelTestUtils.getAssertClass( moduleChilds, "CodecTest" );
			//Function test:test_builtin
			{
			IMethod methodtest_builtin32;
				IModelElement[] classCodecTest31Childs = classCodecTest31.getChildren();
				methodtest_builtin32 = ModelTestUtils.getAssertMethod( classCodecTest31Childs, "test_builtin", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin32, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCodecsModuleTest33;
			IModelElement[] moduleChilds = module.getChildren();
			classCodecsModuleTest33 = ModelTestUtils.getAssertClass( moduleChilds, "CodecsModuleTest" );
			//Function test:test_decode
			{
			IMethod methodtest_decode34;
				IModelElement[] classCodecsModuleTest33Childs = classCodecsModuleTest33.getChildren();
				methodtest_decode34 = ModelTestUtils.getAssertMethod( classCodecsModuleTest33Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode34, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode35;
				IModelElement[] classCodecsModuleTest33Childs = classCodecsModuleTest33.getChildren();
				methodtest_encode35 = ModelTestUtils.getAssertMethod( classCodecsModuleTest33Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode35, new String[] {"self"} );
			}
			//Function test:test_register
			{
			IMethod methodtest_register36;
				IModelElement[] classCodecsModuleTest33Childs = classCodecsModuleTest33.getChildren();
				methodtest_register36 = ModelTestUtils.getAssertMethod( classCodecsModuleTest33Childs, "test_register", 1 );
				ModelTestUtils.assertParameterNames( methodtest_register36, new String[] {"self"} );
			}
			//Function test:test_lookup
			{
			IMethod methodtest_lookup37;
				IModelElement[] classCodecsModuleTest33Childs = classCodecsModuleTest33.getChildren();
				methodtest_lookup37 = ModelTestUtils.getAssertMethod( classCodecsModuleTest33Childs, "test_lookup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lookup37, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStreamReaderTest38;
			IModelElement[] moduleChilds = module.getChildren();
			classStreamReaderTest38 = ModelTestUtils.getAssertClass( moduleChilds, "StreamReaderTest" );
			//Function test:setUp
			{
			IMethod methodsetUp39;
				IModelElement[] classStreamReaderTest38Childs = classStreamReaderTest38.getChildren();
				methodsetUp39 = ModelTestUtils.getAssertMethod( classStreamReaderTest38Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp39, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines40;
				IModelElement[] classStreamReaderTest38Childs = classStreamReaderTest38.getChildren();
				methodtest_readlines40 = ModelTestUtils.getAssertMethod( classStreamReaderTest38Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines40, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main41 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen226( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
			//Function test:__getitem__
			{
			IMethod method__getitem__33;
				IModelElement[] classG31Childs = classG31.getChildren();
				method__getitem__33 = ModelTestUtils.getAssertMethod( classG31Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__33, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI34;
			IModelElement[] moduleChilds = module.getChildren();
			classI34 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__35;
				IModelElement[] classI34Childs = classI34.getChildren();
				method__init__35 = ModelTestUtils.getAssertMethod( classI34Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__35, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__36;
				IModelElement[] classI34Childs = classI34.getChildren();
				method__iter__36 = ModelTestUtils.getAssertMethod( classI34Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__36, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext37;
				IModelElement[] classI34Childs = classI34.getChildren();
				methodnext37 = ModelTestUtils.getAssertMethod( classI34Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext37, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg38;
			IModelElement[] moduleChilds = module.getChildren();
			classIg38 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__39;
				IModelElement[] classIg38Childs = classIg38.getChildren();
				method__init__39 = ModelTestUtils.getAssertMethod( classIg38Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__39, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__40;
				IModelElement[] classIg38Childs = classIg38.getChildren();
				method__iter__40 = ModelTestUtils.getAssertMethod( classIg38Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__40, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX41;
			IModelElement[] moduleChilds = module.getChildren();
			classX41 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__42;
				IModelElement[] classX41Childs = classX41.getChildren();
				method__init__42 = ModelTestUtils.getAssertMethod( classX41Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__42, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext43;
				IModelElement[] classX41Childs = classX41.getChildren();
				methodnext43 = ModelTestUtils.getAssertMethod( classX41Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext43, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN44;
			IModelElement[] moduleChilds = module.getChildren();
			classN44 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__45;
				IModelElement[] classN44Childs = classN44.getChildren();
				method__init__45 = ModelTestUtils.getAssertMethod( classN44Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__45, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__46;
				IModelElement[] classN44Childs = classN44.getChildren();
				method__iter__46 = ModelTestUtils.getAssertMethod( classN44Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__46, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE47;
			IModelElement[] moduleChilds = module.getChildren();
			classE47 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__48;
				IModelElement[] classE47Childs = classE47.getChildren();
				method__init__48 = ModelTestUtils.getAssertMethod( classE47Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__48, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__49;
				IModelElement[] classE47Childs = classE47.getChildren();
				method__iter__49 = ModelTestUtils.getAssertMethod( classE47Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__49, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext50;
				IModelElement[] classE47Childs = classE47.getChildren();
				methodnext50 = ModelTestUtils.getAssertMethod( classE47Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext50, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS51;
			IModelElement[] moduleChilds = module.getChildren();
			classS51 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__52;
				IModelElement[] classS51Childs = classS51.getChildren();
				method__init__52 = ModelTestUtils.getAssertMethod( classS51Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__52, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__53;
				IModelElement[] classS51Childs = classS51.getChildren();
				method__iter__53 = ModelTestUtils.getAssertMethod( classS51Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__53, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext54;
				IModelElement[] classS51Childs = classS51.getChildren();
				methodnext54 = ModelTestUtils.getAssertMethod( classS51Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext54, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL55;
			IModelElement[] moduleChilds = module.getChildren();
			methodL55 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL55, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestVariousIteratorArgs56;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVariousIteratorArgs56 = ModelTestUtils.getAssertClass( moduleChilds, "TestVariousIteratorArgs" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor57;
				IModelElement[] classTestVariousIteratorArgs56Childs = classTestVariousIteratorArgs56.getChildren();
				methodtest_constructor57 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs56Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor57, new String[] {"self"} );
			}
			//Function test:test_iter_with_altered_data
			{
			IMethod methodtest_iter_with_altered_data58;
				IModelElement[] classTestVariousIteratorArgs56Childs = classTestVariousIteratorArgs56.getChildren();
				methodtest_iter_with_altered_data58 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs56Childs, "test_iter_with_altered_data", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_with_altered_data58, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDeque59;
			IModelElement[] moduleChilds = module.getChildren();
			classDeque59 = ModelTestUtils.getAssertClass( moduleChilds, "Deque" );
		}
		//Class test
		{
		IType classDequeWithBadIter60;
			IModelElement[] moduleChilds = module.getChildren();
			classDequeWithBadIter60 = ModelTestUtils.getAssertClass( moduleChilds, "DequeWithBadIter" );
			//Function test:__iter__
			{
			IMethod method__iter__61;
				IModelElement[] classDequeWithBadIter60Childs = classDequeWithBadIter60.getChildren();
				method__iter__61 = ModelTestUtils.getAssertMethod( classDequeWithBadIter60Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__61, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubclass62;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubclass62 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubclass" );
			//Function test:test_basics
			{
			IMethod methodtest_basics63;
				IModelElement[] classTestSubclass62Childs = classTestSubclass62.getChildren();
				methodtest_basics63 = ModelTestUtils.getAssertMethod( classTestSubclass62Childs, "test_basics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basics63, new String[] {"self"} );
			}
			//Function test:test_copy_pickle
			{
			IMethod methodtest_copy_pickle64;
				IModelElement[] classTestSubclass62Childs = classTestSubclass62.getChildren();
				methodtest_copy_pickle64 = ModelTestUtils.getAssertMethod( classTestSubclass62Childs, "test_copy_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_pickle64, new String[] {"self"} );
			}
			//Function test:test_pickle
			{
			IMethod methodtest_pickle65;
				IModelElement[] classTestSubclass62Childs = classTestSubclass62.getChildren();
				methodtest_pickle65 = ModelTestUtils.getAssertMethod( classTestSubclass62Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle65, new String[] {"self"} );
			}
			//Function test:test_weakref
			{
			IMethod methodtest_weakref66;
				IModelElement[] classTestSubclass62Childs = classTestSubclass62.getChildren();
				methodtest_weakref66 = ModelTestUtils.getAssertMethod( classTestSubclass62Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref66, new String[] {"self"} );
			}
			//Function test:test_strange_subclass
			{
			IMethod methodtest_strange_subclass67;
				IModelElement[] classTestSubclass62Childs = classTestSubclass62.getChildren();
				methodtest_strange_subclass67 = ModelTestUtils.getAssertMethod( classTestSubclass62Childs, "test_strange_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strange_subclass67, new String[] {"self"} );
				//Class test
				{
				IType classX68;
					IModelElement[] methodtest_strange_subclass67Childs = methodtest_strange_subclass67.getChildren();
					classX68 = ModelTestUtils.getAssertClass( methodtest_strange_subclass67Childs, "X" );
					//Function test:__iter__
					{
					IMethod method__iter__69;
						IModelElement[] classX68Childs = classX68.getChildren();
						method__iter__69 = ModelTestUtils.getAssertMethod( classX68Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__69, new String[] {"self"} );
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
		IMethod methodtest_main70;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main70 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main70, new String[] {"verbose"} );
		}

	}
	public void testModelGen228( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
	public void REM_testModelGen231( ) throws Exception {
		String prj = "pytests_4";
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
				//Function test:__iter__
				{
				IMethod method__iter__20;
					IModelElement[] classAddressBookEntry18Childs = classAddressBookEntry18.getChildren();
					method__iter__20 = ModelTestUtils.getAssertMethod( classAddressBookEntry18Childs, "__iter__", 1 );
					ModelTestUtils.assertParameterNames( method__iter__20, new String[] {"self"} );
				}
			}
		}
		//Function test:test_dir
		{
		IMethod methodtest_dir21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_dir21 = ModelTestUtils.getAssertMethod( moduleChilds, "test_dir", 0 );
			//Class test
			{
			IType classC22;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classC22 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "C" );
				//Function test:Cmethod
				{
				IMethod methodCmethod23;
					IModelElement[] classC22Childs = classC22.getChildren();
					methodCmethod23 = ModelTestUtils.getAssertMethod( classC22Childs, "Cmethod", 1 );
					ModelTestUtils.assertParameterNames( methodCmethod23, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classA24;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classA24 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "A" );
				//Function test:Amethod
				{
				IMethod methodAmethod25;
					IModelElement[] classA24Childs = classA24.getChildren();
					methodAmethod25 = ModelTestUtils.getAssertMethod( classA24Childs, "Amethod", 1 );
					ModelTestUtils.assertParameterNames( methodAmethod25, new String[] {"self"} );
				}
			}
			//Function test:interesting
			{
			IMethod methodinteresting26;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				methodinteresting26 = ModelTestUtils.getAssertMethod( methodtest_dir21Childs, "interesting", 1 );
				ModelTestUtils.assertParameterNames( methodinteresting26, new String[] {"strings"} );
			}
			//Class test
			{
			IType classC27;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classC27 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "C" );
				//Function test:Cmethod
				{
				IMethod methodCmethod28;
					IModelElement[] classC27Childs = classC27.getChildren();
					methodCmethod28 = ModelTestUtils.getAssertMethod( classC27Childs, "Cmethod", 1 );
					ModelTestUtils.assertParameterNames( methodCmethod28, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classA29;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classA29 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "A" );
				//Function test:Amethod
				{
				IMethod methodAmethod30;
					IModelElement[] classA29Childs = classA29.getChildren();
					methodAmethod30 = ModelTestUtils.getAssertMethod( classA29Childs, "Amethod", 1 );
					ModelTestUtils.assertParameterNames( methodAmethod30, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM31;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classM31 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "M" );
			}
			//Class test
			{
			IType classM232;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classM232 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "M2" );
				//Function test:getdict
				{
				IMethod methodgetdict33;
					IModelElement[] classM232Childs = classM232.getChildren();
					methodgetdict33 = ModelTestUtils.getAssertMethod( classM232Childs, "getdict", 1 );
					ModelTestUtils.assertParameterNames( methodgetdict33, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classWrapper34;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classWrapper34 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "Wrapper" );
				//Function test:__init__
				{
				IMethod method__init__35;
					IModelElement[] classWrapper34Childs = classWrapper34.getChildren();
					method__init__35 = ModelTestUtils.getAssertMethod( classWrapper34Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__35, new String[] {"self", "obj"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__36;
					IModelElement[] classWrapper34Childs = classWrapper34.getChildren();
					method__repr__36 = ModelTestUtils.getAssertMethod( classWrapper34Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__36, new String[] {"self"} );
				}
				//Function test:__getitem__
				{
				IMethod method__getitem__37;
					IModelElement[] classWrapper34Childs = classWrapper34.getChildren();
					method__getitem__37 = ModelTestUtils.getAssertMethod( classWrapper34Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__37, new String[] {"self", "key"} );
				}
				//Function test:__len__
				{
				IMethod method__len__38;
					IModelElement[] classWrapper34Childs = classWrapper34.getChildren();
					method__len__38 = ModelTestUtils.getAssertMethod( classWrapper34Childs, "__len__", 1 );
					ModelTestUtils.assertParameterNames( method__len__38, new String[] {"self"} );
				}
				//Function test:__getattr__
				{
				IMethod method__getattr__39;
					IModelElement[] classWrapper34Childs = classWrapper34.getChildren();
					method__getattr__39 = ModelTestUtils.getAssertMethod( classWrapper34Childs, "__getattr__", 2 );
					ModelTestUtils.assertParameterNames( method__getattr__39, new String[] {"self", "name"} );
				}
			}
			//Class test
			{
			IType classC40;
				IModelElement[] methodtest_dir21Childs = methodtest_dir21.getChildren();
				classC40 = ModelTestUtils.getAssertClass( methodtest_dir21Childs, "C" );
				//Function test:__getclass
				{
				IMethod method__getclass41;
					IModelElement[] classC40Childs = classC40.getChildren();
					method__getclass41 = ModelTestUtils.getAssertMethod( classC40Childs, "__getclass", 1 );
					ModelTestUtils.assertParameterNames( method__getclass41, new String[] {"self"} );
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
		IMethod methodnumops42;
			IModelElement[] moduleChilds = module.getChildren();
			methodnumops42 = ModelTestUtils.getAssertMethod( moduleChilds, "numops", 3 );
			ModelTestUtils.assertParameterNames( methodnumops42, new String[] {"a", "b", "skip"} );
		}
		//Function test:ints
		{
		IMethod methodints43;
			IModelElement[] moduleChilds = module.getChildren();
			methodints43 = ModelTestUtils.getAssertMethod( moduleChilds, "ints", 0 );
			//Class test
			{
			IType classC44;
				IModelElement[] methodints43Childs = methodints43.getChildren();
				classC44 = ModelTestUtils.getAssertClass( methodints43Childs, "C" );
				//Function test:__add__
				{
				IMethod method__add__45;
					IModelElement[] classC44Childs = classC44.getChildren();
					method__add__45 = ModelTestUtils.getAssertMethod( classC44Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__45, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:longs
		{
		IMethod methodlongs46;
			IModelElement[] moduleChilds = module.getChildren();
			methodlongs46 = ModelTestUtils.getAssertMethod( moduleChilds, "longs", 0 );
		}
		//Function test:floats
		{
		IMethod methodfloats47;
			IModelElement[] moduleChilds = module.getChildren();
			methodfloats47 = ModelTestUtils.getAssertMethod( moduleChilds, "floats", 0 );
		}
		//Function test:complexes
		{
		IMethod methodcomplexes48;
			IModelElement[] moduleChilds = module.getChildren();
			methodcomplexes48 = ModelTestUtils.getAssertMethod( moduleChilds, "complexes", 0 );
			//Class test
			{
			IType classNumber49;
				IModelElement[] methodcomplexes48Childs = methodcomplexes48.getChildren();
				classNumber49 = ModelTestUtils.getAssertClass( methodcomplexes48Childs, "Number" );
				//Function test:__new__
				{
				IMethod method__new__50;
					IModelElement[] classNumber49Childs = classNumber49.getChildren();
					method__new__50 = ModelTestUtils.getAssertMethod( classNumber49Childs, "__new__", 3 );
					ModelTestUtils.assertParameterNames( method__new__50, new String[] {"cls", "args", "kwds"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__51;
					IModelElement[] classNumber49Childs = classNumber49.getChildren();
					method__repr__51 = ModelTestUtils.getAssertMethod( classNumber49Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__51, new String[] {"self"} );
				}
			}
		}
		//Function test:spamlists
		{
		IMethod methodspamlists52;
			IModelElement[] moduleChilds = module.getChildren();
			methodspamlists52 = ModelTestUtils.getAssertMethod( moduleChilds, "spamlists", 0 );
			//Function test:spamlist
			{
			IMethod methodspamlist53;
				IModelElement[] methodspamlists52Childs = methodspamlists52.getChildren();
				methodspamlist53 = ModelTestUtils.getAssertMethod( methodspamlists52Childs, "spamlist", 2 );
				ModelTestUtils.assertParameterNames( methodspamlist53, new String[] {"l", "memo"} );
			}
			//Class test
			{
			IType classC54;
				IModelElement[] methodspamlists52Childs = methodspamlists52.getChildren();
				classC54 = ModelTestUtils.getAssertClass( methodspamlists52Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo55;
					IModelElement[] classC54Childs = classC54.getChildren();
					methodfoo55 = ModelTestUtils.getAssertMethod( classC54Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo55, new String[] {"self"} );
				}
			}
		}
		//Function test:spamdicts
		{
		IMethod methodspamdicts56;
			IModelElement[] moduleChilds = module.getChildren();
			methodspamdicts56 = ModelTestUtils.getAssertMethod( moduleChilds, "spamdicts", 0 );
			//Function test:spamdict
			{
			IMethod methodspamdict57;
				IModelElement[] methodspamdicts56Childs = methodspamdicts56.getChildren();
				methodspamdict57 = ModelTestUtils.getAssertMethod( methodspamdicts56Childs, "spamdict", 2 );
				ModelTestUtils.assertParameterNames( methodspamdict57, new String[] {"d", "memo"} );
			}
			//Class test
			{
			IType classC58;
				IModelElement[] methodspamdicts56Childs = methodspamdicts56.getChildren();
				classC58 = ModelTestUtils.getAssertClass( methodspamdicts56Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo59;
					IModelElement[] classC58Childs = classC58.getChildren();
					methodfoo59 = ModelTestUtils.getAssertMethod( classC58Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo59, new String[] {"self"} );
				}
			}
		}
		//Function test:pydicts
		{
		IMethod methodpydicts60;
			IModelElement[] moduleChilds = module.getChildren();
			methodpydicts60 = ModelTestUtils.getAssertMethod( moduleChilds, "pydicts", 0 );
			//Class test
			{
			IType classC61;
				IModelElement[] methodpydicts60Childs = methodpydicts60.getChildren();
				classC61 = ModelTestUtils.getAssertClass( methodpydicts60Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__62;
					IModelElement[] classC61Childs = classC61.getChildren();
					method__init__62 = ModelTestUtils.getAssertMethod( classC61Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__62, new String[] {"self", "a", "kw"} );
				}
				//Function test:__getitem__
				{
				IMethod method__getitem__63;
					IModelElement[] classC61Childs = classC61.getChildren();
					method__getitem__63 = ModelTestUtils.getAssertMethod( classC61Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__63, new String[] {"self", "key"} );
				}
				//Function test:__setitem__
				{
				IMethod method__setitem__64;
					IModelElement[] classC61Childs = classC61.getChildren();
					method__setitem__64 = ModelTestUtils.getAssertMethod( classC61Childs, "__setitem__", 3 );
					ModelTestUtils.assertParameterNames( method__setitem__64, new String[] {"self", "key", "value"} );
				}
				//Function test:setstate
				{
				IMethod methodsetstate65;
					IModelElement[] classC61Childs = classC61.getChildren();
					methodsetstate65 = ModelTestUtils.getAssertMethod( classC61Childs, "setstate", 2 );
					ModelTestUtils.assertParameterNames( methodsetstate65, new String[] {"self", "state"} );
				}
				//Function test:getstate
				{
				IMethod methodgetstate66;
					IModelElement[] classC61Childs = classC61.getChildren();
					methodgetstate66 = ModelTestUtils.getAssertMethod( classC61Childs, "getstate", 1 );
					ModelTestUtils.assertParameterNames( methodgetstate66, new String[] {"self"} );
				}
			}
		}
		//Function test:pylists
		{
		IMethod methodpylists67;
			IModelElement[] moduleChilds = module.getChildren();
			methodpylists67 = ModelTestUtils.getAssertMethod( moduleChilds, "pylists", 0 );
			//Class test
			{
			IType classC68;
				IModelElement[] methodpylists67Childs = methodpylists67.getChildren();
				classC68 = ModelTestUtils.getAssertClass( methodpylists67Childs, "C" );
				//Function test:__getitem__
				{
				IMethod method__getitem__69;
					IModelElement[] classC68Childs = classC68.getChildren();
					method__getitem__69 = ModelTestUtils.getAssertMethod( classC68Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__69, new String[] {"self", "i"} );
				}
				//Function test:__getslice__
				{
				IMethod method__getslice__70;
					IModelElement[] classC68Childs = classC68.getChildren();
					method__getslice__70 = ModelTestUtils.getAssertMethod( classC68Childs, "__getslice__", 3 );
					ModelTestUtils.assertParameterNames( method__getslice__70, new String[] {"self", "i", "j"} );
				}
			}
		}
		//Function test:metaclass
		{
		IMethod methodmetaclass71;
			IModelElement[] moduleChilds = module.getChildren();
			methodmetaclass71 = ModelTestUtils.getAssertMethod( moduleChilds, "metaclass", 0 );
			//Class test
			{
			IType classC72;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC72 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__73;
					IModelElement[] classC72Childs = classC72.getChildren();
					method__init__73 = ModelTestUtils.getAssertMethod( classC72Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__73, new String[] {"self"} );
				}
				//Function test:getstate
				{
				IMethod methodgetstate74;
					IModelElement[] classC72Childs = classC72.getChildren();
					methodgetstate74 = ModelTestUtils.getAssertMethod( classC72Childs, "getstate", 1 );
					ModelTestUtils.assertParameterNames( methodgetstate74, new String[] {"self"} );
				}
				//Function test:setstate
				{
				IMethod methodsetstate75;
					IModelElement[] classC72Childs = classC72.getChildren();
					methodsetstate75 = ModelTestUtils.getAssertMethod( classC72Childs, "setstate", 2 );
					ModelTestUtils.assertParameterNames( methodsetstate75, new String[] {"self", "state"} );
				}
			}
			//Class test
			{
			IType classD76;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classD76 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "D" );
				//Class test
				{
				IType class__metaclass__77;
					IModelElement[] classD76Childs = classD76.getChildren();
					class__metaclass__77 = ModelTestUtils.getAssertClass( classD76Childs, "__metaclass__" );
					//Function test:myself
					{
					IMethod methodmyself78;
						IModelElement[] class__metaclass__77Childs = class__metaclass__77.getChildren();
						methodmyself78 = ModelTestUtils.getAssertMethod( class__metaclass__77Childs, "myself", 1 );
						ModelTestUtils.assertParameterNames( methodmyself78, new String[] {"cls"} );
					}
				}
			}
			//Class test
			{
			IType classM179;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classM179 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "M1" );
				//Function test:__new__
				{
				IMethod method__new__80;
					IModelElement[] classM179Childs = classM179.getChildren();
					method__new__80 = ModelTestUtils.getAssertMethod( classM179Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__80, new String[] {"cls", "name", "bases", "dict"} );
				}
			}
			//Class test
			{
			IType classC81;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC81 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
			}
			//Class test
			{
			IType class_instance82;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				class_instance82 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "_instance" );
			}
			//Class test
			{
			IType classM283;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classM283 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "M2" );
				//Function test:__new__
				{
				IMethod method__new__84;
					IModelElement[] classM283Childs = classM283.getChildren();
					method__new__84 = ModelTestUtils.getAssertMethod( classM283Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__84, new String[] {"cls", "name", "bases", "dict"} );
				}
				//Function test:__call__
				{
				IMethod method__call__85;
					IModelElement[] classM283Childs = classM283.getChildren();
					method__call__85 = ModelTestUtils.getAssertMethod( classM283Childs, "__call__", 1 );
					ModelTestUtils.assertParameterNames( method__call__85, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC86;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC86 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
				//Function test:spam
				{
				IMethod methodspam87;
					IModelElement[] classC86Childs = classC86.getChildren();
					methodspam87 = ModelTestUtils.getAssertMethod( classC86Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam87, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classautosuper88;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classautosuper88 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "autosuper" );
				//Function test:__new__
				{
				IMethod method__new__89;
					IModelElement[] classautosuper88Childs = classautosuper88.getChildren();
					method__new__89 = ModelTestUtils.getAssertMethod( classautosuper88Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__89, new String[] {"metaclass", "name", "bases", "dict"} );
				}
			}
			//Class test
			{
			IType classA90;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classA90 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "A" );
				//Function test:meth
				{
				IMethod methodmeth91;
					IModelElement[] classA90Childs = classA90.getChildren();
					methodmeth91 = ModelTestUtils.getAssertMethod( classA90Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth91, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB92;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classB92 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "B" );
				//Function test:meth
				{
				IMethod methodmeth93;
					IModelElement[] classB92Childs = classB92.getChildren();
					methodmeth93 = ModelTestUtils.getAssertMethod( classB92Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth93, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC94;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC94 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth95;
					IModelElement[] classC94Childs = classC94.getChildren();
					methodmeth95 = ModelTestUtils.getAssertMethod( classC94Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth95, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD96;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classD96 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "D" );
				//Function test:meth
				{
				IMethod methodmeth97;
					IModelElement[] classD96Childs = classD96.getChildren();
					methodmeth97 = ModelTestUtils.getAssertMethod( classD96Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth97, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classE98;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classE98 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "E" );
				//Function test:meth
				{
				IMethod methodmeth99;
					IModelElement[] classE98Childs = classE98.getChildren();
					methodmeth99 = ModelTestUtils.getAssertMethod( classE98Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth99, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classautoproperty100;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classautoproperty100 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "autoproperty" );
				//Function test:__new__
				{
				IMethod method__new__101;
					IModelElement[] classautoproperty100Childs = classautoproperty100.getChildren();
					method__new__101 = ModelTestUtils.getAssertMethod( classautoproperty100Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__101, new String[] {"metaclass", "name", "bases", "dict"} );
				}
			}
			//Class test
			{
			IType classA102;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classA102 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "A" );
				//Function test:_get_x
				{
				IMethod method_get_x103;
					IModelElement[] classA102Childs = classA102.getChildren();
					method_get_x103 = ModelTestUtils.getAssertMethod( classA102Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x103, new String[] {"self"} );
				}
				//Function test:_set_x
				{
				IMethod method_set_x104;
					IModelElement[] classA102Childs = classA102.getChildren();
					method_set_x104 = ModelTestUtils.getAssertMethod( classA102Childs, "_set_x", 2 );
					ModelTestUtils.assertParameterNames( method_set_x104, new String[] {"self", "x"} );
				}
			}
			//Class test
			{
			IType classmultimetaclass105;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classmultimetaclass105 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "multimetaclass" );
			}
			//Class test
			{
			IType classA106;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classA106 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "A" );
				//Function test:_get_x
				{
				IMethod method_get_x107;
					IModelElement[] classA106Childs = classA106.getChildren();
					method_get_x107 = ModelTestUtils.getAssertMethod( classA106Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x107, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB108;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classB108 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "B" );
				//Function test:_get_x
				{
				IMethod method_get_x109;
					IModelElement[] classB108Childs = classB108.getChildren();
					method_get_x109 = ModelTestUtils.getAssertMethod( classB108Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x109, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC110;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC110 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
				//Function test:_get_x
				{
				IMethod method_get_x111;
					IModelElement[] classC110Childs = classC110.getChildren();
					method_get_x111 = ModelTestUtils.getAssertMethod( classC110Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x111, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD112;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classD112 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "D" );
				//Function test:_get_x
				{
				IMethod method_get_x113;
					IModelElement[] classD112Childs = classD112.getChildren();
					method_get_x113 = ModelTestUtils.getAssertMethod( classD112Childs, "_get_x", 1 );
					ModelTestUtils.assertParameterNames( method_get_x113, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classT114;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classT114 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "T" );
				//Function test:__init__
				{
				IMethod method__init__115;
					IModelElement[] classT114Childs = classT114.getChildren();
					method__init__115 = ModelTestUtils.getAssertMethod( classT114Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__115, new String[] {"self", "args"} );
				}
			}
			//Class test
			{
			IType classC116;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC116 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
			}
			//Class test
			{
			IType classC117;
				IModelElement[] methodmetaclass71Childs = methodmetaclass71.getChildren();
				classC117 = ModelTestUtils.getAssertClass( methodmetaclass71Childs, "C" );
			}
		}
		//Function test:pymods
		{
		IMethod methodpymods118;
			IModelElement[] moduleChilds = module.getChildren();
			methodpymods118 = ModelTestUtils.getAssertMethod( moduleChilds, "pymods", 0 );
			//Class test
			{
			IType classMM119;
				IModelElement[] methodpymods118Childs = methodpymods118.getChildren();
				classMM119 = ModelTestUtils.getAssertClass( methodpymods118Childs, "MM" );
				//Function test:__init__
				{
				IMethod method__init__120;
					IModelElement[] classMM119Childs = classMM119.getChildren();
					method__init__120 = ModelTestUtils.getAssertMethod( classMM119Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__120, new String[] {"self", "name"} );
				}
				//Function test:__getattribute__
				{
				IMethod method__getattribute__121;
					IModelElement[] classMM119Childs = classMM119.getChildren();
					method__getattribute__121 = ModelTestUtils.getAssertMethod( classMM119Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__121, new String[] {"self", "name"} );
				}
				//Function test:__setattr__
				{
				IMethod method__setattr__122;
					IModelElement[] classMM119Childs = classMM119.getChildren();
					method__setattr__122 = ModelTestUtils.getAssertMethod( classMM119Childs, "__setattr__", 3 );
					ModelTestUtils.assertParameterNames( method__setattr__122, new String[] {"self", "name", "value"} );
				}
				//Function test:__delattr__
				{
				IMethod method__delattr__123;
					IModelElement[] classMM119Childs = classMM119.getChildren();
					method__delattr__123 = ModelTestUtils.getAssertMethod( classMM119Childs, "__delattr__", 2 );
					ModelTestUtils.assertParameterNames( method__delattr__123, new String[] {"self", "name"} );
				}
			}
		}
		//Function test:multi
		{
		IMethod methodmulti124;
			IModelElement[] moduleChilds = module.getChildren();
			methodmulti124 = ModelTestUtils.getAssertMethod( moduleChilds, "multi", 0 );
			//Class test
			{
			IType classC125;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classC125 = ModelTestUtils.getAssertClass( methodmulti124Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__126;
					IModelElement[] classC125Childs = classC125.getChildren();
					method__init__126 = ModelTestUtils.getAssertMethod( classC125Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__126, new String[] {"self"} );
				}
				//Function test:getstate
				{
				IMethod methodgetstate127;
					IModelElement[] classC125Childs = classC125.getChildren();
					methodgetstate127 = ModelTestUtils.getAssertMethod( classC125Childs, "getstate", 1 );
					ModelTestUtils.assertParameterNames( methodgetstate127, new String[] {"self"} );
				}
				//Function test:setstate
				{
				IMethod methodsetstate128;
					IModelElement[] classC125Childs = classC125.getChildren();
					methodsetstate128 = ModelTestUtils.getAssertMethod( classC125Childs, "setstate", 2 );
					ModelTestUtils.assertParameterNames( methodsetstate128, new String[] {"self", "state"} );
				}
			}
			//Class test
			{
			IType classD129;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classD129 = ModelTestUtils.getAssertClass( methodmulti124Childs, "D" );
				//Function test:__init__
				{
				IMethod method__init__130;
					IModelElement[] classD129Childs = classD129.getChildren();
					method__init__130 = ModelTestUtils.getAssertMethod( classD129Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__130, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classNode131;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classNode131 = ModelTestUtils.getAssertClass( methodmulti124Childs, "Node" );
				//Function test:__int__
				{
				IMethod method__int__132;
					IModelElement[] classNode131Childs = classNode131.getChildren();
					method__int__132 = ModelTestUtils.getAssertMethod( classNode131Childs, "__int__", 1 );
					ModelTestUtils.assertParameterNames( method__int__132, new String[] {"self"} );
				}
				//Function test:foo
				{
				IMethod methodfoo133;
					IModelElement[] classNode131Childs = classNode131.getChildren();
					methodfoo133 = ModelTestUtils.getAssertMethod( classNode131Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo133, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classFrag134;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classFrag134 = ModelTestUtils.getAssertClass( methodmulti124Childs, "Frag" );
				//Function test:foo
				{
				IMethod methodfoo135;
					IModelElement[] classFrag134Childs = classFrag134.getChildren();
					methodfoo135 = ModelTestUtils.getAssertMethod( classFrag134Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo135, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classA136;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classA136 = ModelTestUtils.getAssertClass( methodmulti124Childs, "A" );
			}
			//Class test
			{
			IType classB137;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classB137 = ModelTestUtils.getAssertClass( methodmulti124Childs, "B" );
			}
			//Class test
			{
			IType classC138;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classC138 = ModelTestUtils.getAssertClass( methodmulti124Childs, "C" );
			}
			//Class test
			{
			IType classD139;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classD139 = ModelTestUtils.getAssertClass( methodmulti124Childs, "D" );
			}
			//Class test
			{
			IType classE140;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classE140 = ModelTestUtils.getAssertClass( methodmulti124Childs, "E" );
			}
			//Class test
			{
			IType classF141;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classF141 = ModelTestUtils.getAssertClass( methodmulti124Childs, "F" );
			}
			//Class test
			{
			IType classC142;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classC142 = ModelTestUtils.getAssertClass( methodmulti124Childs, "C" );
				//Function test:cmethod
				{
				IMethod methodcmethod143;
					IModelElement[] classC142Childs = classC142.getChildren();
					methodcmethod143 = ModelTestUtils.getAssertMethod( classC142Childs, "cmethod", 1 );
					ModelTestUtils.assertParameterNames( methodcmethod143, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method144;
					IModelElement[] classC142Childs = classC142.getChildren();
					methodall_method144 = ModelTestUtils.getAssertMethod( classC142Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method144, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM1145;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classM1145 = ModelTestUtils.getAssertClass( methodmulti124Childs, "M1" );
				//Function test:m1method
				{
				IMethod methodm1method146;
					IModelElement[] classM1145Childs = classM1145.getChildren();
					methodm1method146 = ModelTestUtils.getAssertMethod( classM1145Childs, "m1method", 1 );
					ModelTestUtils.assertParameterNames( methodm1method146, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method147;
					IModelElement[] classM1145Childs = classM1145.getChildren();
					methodall_method147 = ModelTestUtils.getAssertMethod( classM1145Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method147, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD148;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classD148 = ModelTestUtils.getAssertClass( methodmulti124Childs, "D" );
				//Function test:dmethod
				{
				IMethod methoddmethod149;
					IModelElement[] classD148Childs = classD148.getChildren();
					methoddmethod149 = ModelTestUtils.getAssertMethod( classD148Childs, "dmethod", 1 );
					ModelTestUtils.assertParameterNames( methoddmethod149, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method150;
					IModelElement[] classD148Childs = classD148.getChildren();
					methodall_method150 = ModelTestUtils.getAssertMethod( classD148Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method150, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM2151;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classM2151 = ModelTestUtils.getAssertClass( methodmulti124Childs, "M2" );
				//Function test:m2method
				{
				IMethod methodm2method152;
					IModelElement[] classM2151Childs = classM2151.getChildren();
					methodm2method152 = ModelTestUtils.getAssertMethod( classM2151Childs, "m2method", 1 );
					ModelTestUtils.assertParameterNames( methodm2method152, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method153;
					IModelElement[] classM2151Childs = classM2151.getChildren();
					methodall_method153 = ModelTestUtils.getAssertMethod( classM2151Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method153, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classM3154;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classM3154 = ModelTestUtils.getAssertClass( methodmulti124Childs, "M3" );
				//Function test:m3method
				{
				IMethod methodm3method155;
					IModelElement[] classM3154Childs = classM3154.getChildren();
					methodm3method155 = ModelTestUtils.getAssertMethod( classM3154Childs, "m3method", 1 );
					ModelTestUtils.assertParameterNames( methodm3method155, new String[] {"self"} );
				}
				//Function test:all_method
				{
				IMethod methodall_method156;
					IModelElement[] classM3154Childs = classM3154.getChildren();
					methodall_method156 = ModelTestUtils.getAssertMethod( classM3154Childs, "all_method", 1 );
					ModelTestUtils.assertParameterNames( methodall_method156, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classClassic157;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classClassic157 = ModelTestUtils.getAssertClass( methodmulti124Childs, "Classic" );
			}
			//Class test
			{
			IType classNew158;
				IModelElement[] methodmulti124Childs = methodmulti124.getChildren();
				classNew158 = ModelTestUtils.getAssertClass( methodmulti124Childs, "New" );
			}
		}
		//Function test:diamond
		{
		IMethod methoddiamond159;
			IModelElement[] moduleChilds = module.getChildren();
			methoddiamond159 = ModelTestUtils.getAssertMethod( moduleChilds, "diamond", 0 );
			//Class test
			{
			IType classA160;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classA160 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "A" );
				//Function test:spam
				{
				IMethod methodspam161;
					IModelElement[] classA160Childs = classA160.getChildren();
					methodspam161 = ModelTestUtils.getAssertMethod( classA160Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam161, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB162;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classB162 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "B" );
				//Function test:boo
				{
				IMethod methodboo163;
					IModelElement[] classB162Childs = classB162.getChildren();
					methodboo163 = ModelTestUtils.getAssertMethod( classB162Childs, "boo", 1 );
					ModelTestUtils.assertParameterNames( methodboo163, new String[] {"self"} );
				}
				//Function test:spam
				{
				IMethod methodspam164;
					IModelElement[] classB162Childs = classB162.getChildren();
					methodspam164 = ModelTestUtils.getAssertMethod( classB162Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam164, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC165;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classC165 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "C" );
				//Function test:boo
				{
				IMethod methodboo166;
					IModelElement[] classC165Childs = classC165.getChildren();
					methodboo166 = ModelTestUtils.getAssertMethod( classC165Childs, "boo", 1 );
					ModelTestUtils.assertParameterNames( methodboo166, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD167;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classD167 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "D" );
			}
			//Class test
			{
			IType classE168;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classE168 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "E" );
			}
			//Class test
			{
			IType classF169;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classF169 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "F" );
			}
			//Class test
			{
			IType classG170;
				IModelElement[] methoddiamond159Childs = methoddiamond159.getChildren();
				classG170 = ModelTestUtils.getAssertClass( methoddiamond159Childs, "G" );
			}
		}
		//Function test:ex5
		{
		IMethod methodex5171;
			IModelElement[] moduleChilds = module.getChildren();
			methodex5171 = ModelTestUtils.getAssertMethod( moduleChilds, "ex5", 0 );
			//Class test
			{
			IType classA172;
				IModelElement[] methodex5171Childs = methodex5171.getChildren();
				classA172 = ModelTestUtils.getAssertClass( methodex5171Childs, "A" );
			}
			//Class test
			{
			IType classB173;
				IModelElement[] methodex5171Childs = methodex5171.getChildren();
				classB173 = ModelTestUtils.getAssertClass( methodex5171Childs, "B" );
			}
			//Class test
			{
			IType classC174;
				IModelElement[] methodex5171Childs = methodex5171.getChildren();
				classC174 = ModelTestUtils.getAssertClass( methodex5171Childs, "C" );
			}
			//Class test
			{
			IType classX175;
				IModelElement[] methodex5171Childs = methodex5171.getChildren();
				classX175 = ModelTestUtils.getAssertClass( methodex5171Childs, "X" );
			}
			//Class test
			{
			IType classY176;
				IModelElement[] methodex5171Childs = methodex5171.getChildren();
				classY176 = ModelTestUtils.getAssertClass( methodex5171Childs, "Y" );
			}
			//Class test
			{
			IType classZ177;
				IModelElement[] methodex5171Childs = methodex5171.getChildren();
				classZ177 = ModelTestUtils.getAssertClass( methodex5171Childs, "Z" );
			}
		}
		//Function test:monotonicity
		{
		IMethod methodmonotonicity178;
			IModelElement[] moduleChilds = module.getChildren();
			methodmonotonicity178 = ModelTestUtils.getAssertMethod( moduleChilds, "monotonicity", 0 );
			//Class test
			{
			IType classBoat179;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classBoat179 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "Boat" );
			}
			//Class test
			{
			IType classDayBoat180;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classDayBoat180 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "DayBoat" );
			}
			//Class test
			{
			IType classWheelBoat181;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classWheelBoat181 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "WheelBoat" );
			}
			//Class test
			{
			IType classEngineLess182;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classEngineLess182 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "EngineLess" );
			}
			//Class test
			{
			IType classSmallMultihull183;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classSmallMultihull183 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "SmallMultihull" );
			}
			//Class test
			{
			IType classPedalWheelBoat184;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classPedalWheelBoat184 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "PedalWheelBoat" );
			}
			//Class test
			{
			IType classSmallCatamaran185;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classSmallCatamaran185 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "SmallCatamaran" );
			}
			//Class test
			{
			IType classPedalo186;
				IModelElement[] methodmonotonicity178Childs = methodmonotonicity178.getChildren();
				classPedalo186 = ModelTestUtils.getAssertClass( methodmonotonicity178Childs, "Pedalo" );
			}
		}
		//Function test:consistency_with_epg
		{
		IMethod methodconsistency_with_epg187;
			IModelElement[] moduleChilds = module.getChildren();
			methodconsistency_with_epg187 = ModelTestUtils.getAssertMethod( moduleChilds, "consistency_with_epg", 0 );
			//Class test
			{
			IType classPane188;
				IModelElement[] methodconsistency_with_epg187Childs = methodconsistency_with_epg187.getChildren();
				classPane188 = ModelTestUtils.getAssertClass( methodconsistency_with_epg187Childs, "Pane" );
			}
			//Class test
			{
			IType classScrollingMixin189;
				IModelElement[] methodconsistency_with_epg187Childs = methodconsistency_with_epg187.getChildren();
				classScrollingMixin189 = ModelTestUtils.getAssertClass( methodconsistency_with_epg187Childs, "ScrollingMixin" );
			}
			//Class test
			{
			IType classEditingMixin190;
				IModelElement[] methodconsistency_with_epg187Childs = methodconsistency_with_epg187.getChildren();
				classEditingMixin190 = ModelTestUtils.getAssertClass( methodconsistency_with_epg187Childs, "EditingMixin" );
			}
			//Class test
			{
			IType classScrollablePane191;
				IModelElement[] methodconsistency_with_epg187Childs = methodconsistency_with_epg187.getChildren();
				classScrollablePane191 = ModelTestUtils.getAssertClass( methodconsistency_with_epg187Childs, "ScrollablePane" );
			}
			//Class test
			{
			IType classEditablePane192;
				IModelElement[] methodconsistency_with_epg187Childs = methodconsistency_with_epg187.getChildren();
				classEditablePane192 = ModelTestUtils.getAssertClass( methodconsistency_with_epg187Childs, "EditablePane" );
			}
			//Class test
			{
			IType classEditableScrollablePane193;
				IModelElement[] methodconsistency_with_epg187Childs = methodconsistency_with_epg187.getChildren();
				classEditableScrollablePane193 = ModelTestUtils.getAssertClass( methodconsistency_with_epg187Childs, "EditableScrollablePane" );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mro_err_msg");
		}
		//Function test:mro_disagreement
		{
		IMethod methodmro_disagreement194;
			IModelElement[] moduleChilds = module.getChildren();
			methodmro_disagreement194 = ModelTestUtils.getAssertMethod( moduleChilds, "mro_disagreement", 0 );
			//Function test:raises
			{
			IMethod methodraises195;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				methodraises195 = ModelTestUtils.getAssertMethod( methodmro_disagreement194Childs, "raises", 4 );
				ModelTestUtils.assertParameterNames( methodraises195, new String[] {"exc", "expected", "callable", "args"} );
			}
			//Class test
			{
			IType classA196;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classA196 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "A" );
			}
			//Class test
			{
			IType classB197;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classB197 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "B" );
			}
			//Class test
			{
			IType classC198;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classC198 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "C" );
			}
			//Class test
			{
			IType classGridLayout199;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classGridLayout199 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "GridLayout" );
			}
			//Class test
			{
			IType classHorizontalGrid200;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classHorizontalGrid200 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "HorizontalGrid" );
			}
			//Class test
			{
			IType classVerticalGrid201;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classVerticalGrid201 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "VerticalGrid" );
			}
			//Class test
			{
			IType classHVGrid202;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classHVGrid202 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "HVGrid" );
			}
			//Class test
			{
			IType classVHGrid203;
				IModelElement[] methodmro_disagreement194Childs = methodmro_disagreement194.getChildren();
				classVHGrid203 = ModelTestUtils.getAssertClass( methodmro_disagreement194Childs, "VHGrid" );
			}
		}
		//Function test:objects
		{
		IMethod methodobjects204;
			IModelElement[] moduleChilds = module.getChildren();
			methodobjects204 = ModelTestUtils.getAssertMethod( moduleChilds, "objects", 0 );
			//Class test
			{
			IType classCdict205;
				IModelElement[] methodobjects204Childs = methodobjects204.getChildren();
				classCdict205 = ModelTestUtils.getAssertClass( methodobjects204Childs, "Cdict" );
			}
		}
		//Function test:slots
		{
		IMethod methodslots206;
			IModelElement[] moduleChilds = module.getChildren();
			methodslots206 = ModelTestUtils.getAssertMethod( moduleChilds, "slots", 0 );
			//Class test
			{
			IType classC0207;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC0207 = ModelTestUtils.getAssertClass( methodslots206Childs, "C0" );
			}
			//Class test
			{
			IType classC1208;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC1208 = ModelTestUtils.getAssertClass( methodslots206Childs, "C1" );
			}
			//Class test
			{
			IType classC3209;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC3209 = ModelTestUtils.getAssertClass( methodslots206Childs, "C3" );
			}
			//Class test
			{
			IType classC4210;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC4210 = ModelTestUtils.getAssertClass( methodslots206Childs, "C4" );
				//Function test:__init__
				{
				IMethod method__init__211;
					IModelElement[] classC4210Childs = classC4210.getChildren();
					method__init__211 = ModelTestUtils.getAssertMethod( classC4210Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__211, new String[] {"self", "value"} );
				}
				//Function test:get
				{
				IMethod methodget212;
					IModelElement[] classC4210Childs = classC4210.getChildren();
					methodget212 = ModelTestUtils.getAssertMethod( classC4210Childs, "get", 1 );
					ModelTestUtils.assertParameterNames( methodget212, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC213;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC213 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classC214;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC214 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classC215;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC215 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classC216;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC216 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classC217;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC217 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classC218;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC218 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classCounted219;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classCounted219 = ModelTestUtils.getAssertClass( methodslots206Childs, "Counted" );
				//Function test:__init__
				{
				IMethod method__init__220;
					IModelElement[] classCounted219Childs = classCounted219.getChildren();
					method__init__220 = ModelTestUtils.getAssertMethod( classCounted219Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__220, new String[] {"self"} );
				}
				//Function test:__del__
				{
				IMethod method__del__221;
					IModelElement[] classCounted219Childs = classCounted219.getChildren();
					method__del__221 = ModelTestUtils.getAssertMethod( classCounted219Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__221, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC222;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classC222 = ModelTestUtils.getAssertClass( methodslots206Childs, "C" );
			}
			//Class test
			{
			IType classD223;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classD223 = ModelTestUtils.getAssertClass( methodslots206Childs, "D" );
			}
			//Class test
			{
			IType classE224;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classE224 = ModelTestUtils.getAssertClass( methodslots206Childs, "E" );
			}
			//Class test
			{
			IType classF225;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classF225 = ModelTestUtils.getAssertClass( methodslots206Childs, "F" );
			}
			//Class test
			{
			IType classG226;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classG226 = ModelTestUtils.getAssertClass( methodslots206Childs, "G" );
				//Function test:__cmp__
				{
				IMethod method__cmp__227;
					IModelElement[] classG226Childs = classG226.getChildren();
					method__cmp__227 = ModelTestUtils.getAssertMethod( classG226Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__227, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classH228;
				IModelElement[] methodslots206Childs = methodslots206.getChildren();
				classH228 = ModelTestUtils.getAssertClass( methodslots206Childs, "H" );
				//Function test:__init__
				{
				IMethod method__init__229;
					IModelElement[] classH228Childs = classH228.getChildren();
					method__init__229 = ModelTestUtils.getAssertMethod( classH228Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__229, new String[] {"self"} );
				}
				//Function test:__del__
				{
				IMethod method__del__230;
					IModelElement[] classH228Childs = classH228.getChildren();
					method__del__230 = ModelTestUtils.getAssertMethod( classH228Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__230, new String[] {"self"} );
				}
			}
		}
		//Function test:slotspecials
		{
		IMethod methodslotspecials231;
			IModelElement[] moduleChilds = module.getChildren();
			methodslotspecials231 = ModelTestUtils.getAssertMethod( moduleChilds, "slotspecials", 0 );
			//Class test
			{
			IType classD232;
				IModelElement[] methodslotspecials231Childs = methodslotspecials231.getChildren();
				classD232 = ModelTestUtils.getAssertClass( methodslotspecials231Childs, "D" );
			}
			//Class test
			{
			IType classW233;
				IModelElement[] methodslotspecials231Childs = methodslotspecials231.getChildren();
				classW233 = ModelTestUtils.getAssertClass( methodslotspecials231Childs, "W" );
			}
			//Class test
			{
			IType classC1234;
				IModelElement[] methodslotspecials231Childs = methodslotspecials231.getChildren();
				classC1234 = ModelTestUtils.getAssertClass( methodslotspecials231Childs, "C1" );
			}
			//Class test
			{
			IType classC2235;
				IModelElement[] methodslotspecials231Childs = methodslotspecials231.getChildren();
				classC2235 = ModelTestUtils.getAssertClass( methodslotspecials231Childs, "C2" );
			}
		}
		//Function test:dynamics
		{
		IMethod methoddynamics236;
			IModelElement[] moduleChilds = module.getChildren();
			methoddynamics236 = ModelTestUtils.getAssertMethod( moduleChilds, "dynamics", 0 );
			//Class test
			{
			IType classD237;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classD237 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "D" );
			}
			//Class test
			{
			IType classE238;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classE238 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "E" );
			}
			//Class test
			{
			IType classF239;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classF239 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "F" );
			}
			//Class test
			{
			IType classC240;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classC240 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "C" );
			}
			//Function test:mygetattr
			{
			IMethod methodmygetattr241;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				methodmygetattr241 = ModelTestUtils.getAssertMethod( methoddynamics236Childs, "mygetattr", 2 );
				ModelTestUtils.assertParameterNames( methodmygetattr241, new String[] {"self", "name"} );
			}
			//Function test:mysetattr
			{
			IMethod methodmysetattr242;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				methodmysetattr242 = ModelTestUtils.getAssertMethod( methoddynamics236Childs, "mysetattr", 3 );
				ModelTestUtils.assertParameterNames( methodmysetattr242, new String[] {"self", "name", "value"} );
			}
			//Class test
			{
			IType classD243;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classD243 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "D" );
			}
			//Class test
			{
			IType classI244;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classI244 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "I" );
			}
			//Class test
			{
			IType classL245;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classL245 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "L" );
			}
			//Class test
			{
			IType classdynamicmetaclass246;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classdynamicmetaclass246 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "dynamicmetaclass" );
			}
			//Class test
			{
			IType classsomeclass247;
				IModelElement[] methoddynamics236Childs = methoddynamics236.getChildren();
				classsomeclass247 = ModelTestUtils.getAssertClass( methoddynamics236Childs, "someclass" );
			}
		}
		//Function test:errors
		{
		IMethod methoderrors248;
			IModelElement[] moduleChilds = module.getChildren();
			methoderrors248 = ModelTestUtils.getAssertMethod( moduleChilds, "errors", 0 );
			//Class test
			{
			IType classC249;
				IModelElement[] methoderrors248Childs = methoderrors248.getChildren();
				classC249 = ModelTestUtils.getAssertClass( methoderrors248Childs, "C" );
			}
			//Class test
			{
			IType classC250;
				IModelElement[] methoderrors248Childs = methoderrors248.getChildren();
				classC250 = ModelTestUtils.getAssertClass( methoderrors248Childs, "C" );
			}
			//Class test
			{
			IType classClassic251;
				IModelElement[] methoderrors248Childs = methoderrors248.getChildren();
				classClassic251 = ModelTestUtils.getAssertClass( methoderrors248Childs, "Classic" );
			}
			//Class test
			{
			IType classC252;
				IModelElement[] methoderrors248Childs = methoderrors248.getChildren();
				classC252 = ModelTestUtils.getAssertClass( methoderrors248Childs, "C" );
			}
			//Class test
			{
			IType classC253;
				IModelElement[] methoderrors248Childs = methoderrors248.getChildren();
				classC253 = ModelTestUtils.getAssertClass( methoderrors248Childs, "C" );
			}
			//Class test
			{
			IType classC254;
				IModelElement[] methoderrors248Childs = methoderrors248.getChildren();
				classC254 = ModelTestUtils.getAssertClass( methoderrors248Childs, "C" );
			}
		}
		//Function test:classmethods
		{
		IMethod methodclassmethods255;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassmethods255 = ModelTestUtils.getAssertMethod( moduleChilds, "classmethods", 0 );
			//Class test
			{
			IType classC256;
				IModelElement[] methodclassmethods255Childs = methodclassmethods255.getChildren();
				classC256 = ModelTestUtils.getAssertClass( methodclassmethods255Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo257;
					IModelElement[] classC256Childs = classC256.getChildren();
					methodfoo257 = ModelTestUtils.getAssertMethod( classC256Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo257, new String[] {"a"} );
				}
			}
			//Class test
			{
			IType classD258;
				IModelElement[] methodclassmethods255Childs = methodclassmethods255.getChildren();
				classD258 = ModelTestUtils.getAssertClass( methodclassmethods255Childs, "D" );
			}
			//Function test:f
			{
			IMethod methodf259;
				IModelElement[] methodclassmethods255Childs = methodclassmethods255.getChildren();
				methodf259 = ModelTestUtils.getAssertMethod( methodclassmethods255Childs, "f", 2 );
				ModelTestUtils.assertParameterNames( methodf259, new String[] {"cls", "arg"} );
			}
		}
		//Function test:classmethods_in_c
		{
		IMethod methodclassmethods_in_c260;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassmethods_in_c260 = ModelTestUtils.getAssertMethod( moduleChilds, "classmethods_in_c", 0 );
		}
		//Function test:staticmethods
		{
		IMethod methodstaticmethods261;
			IModelElement[] moduleChilds = module.getChildren();
			methodstaticmethods261 = ModelTestUtils.getAssertMethod( moduleChilds, "staticmethods", 0 );
			//Class test
			{
			IType classC262;
				IModelElement[] methodstaticmethods261Childs = methodstaticmethods261.getChildren();
				classC262 = ModelTestUtils.getAssertClass( methodstaticmethods261Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo263;
					IModelElement[] classC262Childs = classC262.getChildren();
					methodfoo263 = ModelTestUtils.getAssertMethod( classC262Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo263, new String[] {"a"} );
				}
			}
			//Class test
			{
			IType classD264;
				IModelElement[] methodstaticmethods261Childs = methodstaticmethods261.getChildren();
				classD264 = ModelTestUtils.getAssertClass( methodstaticmethods261Childs, "D" );
			}
		}
		//Function test:staticmethods_in_c
		{
		IMethod methodstaticmethods_in_c265;
			IModelElement[] moduleChilds = module.getChildren();
			methodstaticmethods_in_c265 = ModelTestUtils.getAssertMethod( moduleChilds, "staticmethods_in_c", 0 );
		}
		//Function test:classic
		{
		IMethod methodclassic266;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassic266 = ModelTestUtils.getAssertMethod( moduleChilds, "classic", 0 );
			//Class test
			{
			IType classC267;
				IModelElement[] methodclassic266Childs = methodclassic266.getChildren();
				classC267 = ModelTestUtils.getAssertClass( methodclassic266Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo268;
					IModelElement[] classC267Childs = classC267.getChildren();
					methodfoo268 = ModelTestUtils.getAssertMethod( classC267Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo268, new String[] {"a"} );
				}
			}
			//Class test
			{
			IType classD269;
				IModelElement[] methodclassic266Childs = methodclassic266.getChildren();
				classD269 = ModelTestUtils.getAssertClass( methodclassic266Childs, "D" );
			}
			//Class test
			{
			IType classE270;
				IModelElement[] methodclassic266Childs = methodclassic266.getChildren();
				classE270 = ModelTestUtils.getAssertClass( methodclassic266Childs, "E" );
			}
		}
		//Function test:compattr
		{
		IMethod methodcompattr271;
			IModelElement[] moduleChilds = module.getChildren();
			methodcompattr271 = ModelTestUtils.getAssertMethod( moduleChilds, "compattr", 0 );
			//Class test
			{
			IType classC272;
				IModelElement[] methodcompattr271Childs = methodcompattr271.getChildren();
				classC272 = ModelTestUtils.getAssertClass( methodcompattr271Childs, "C" );
				//Class test
				{
				IType classcomputed_attribute273;
					IModelElement[] classC272Childs = classC272.getChildren();
					classcomputed_attribute273 = ModelTestUtils.getAssertClass( classC272Childs, "computed_attribute" );
					//Function test:__init__
					{
					IMethod method__init__274;
						IModelElement[] classcomputed_attribute273Childs = classcomputed_attribute273.getChildren();
						method__init__274 = ModelTestUtils.getAssertMethod( classcomputed_attribute273Childs, "__init__", 4 );
						ModelTestUtils.assertParameterNames( method__init__274, new String[] {"self", "get", "set", "delete"} );
					}
					//Function test:__get__
					{
					IMethod method__get__275;
						IModelElement[] classcomputed_attribute273Childs = classcomputed_attribute273.getChildren();
						method__get__275 = ModelTestUtils.getAssertMethod( classcomputed_attribute273Childs, "__get__", 3 );
						ModelTestUtils.assertParameterNames( method__get__275, new String[] {"self", "obj", "type"} );
					}
					//Function test:__set__
					{
					IMethod method__set__276;
						IModelElement[] classcomputed_attribute273Childs = classcomputed_attribute273.getChildren();
						method__set__276 = ModelTestUtils.getAssertMethod( classcomputed_attribute273Childs, "__set__", 3 );
						ModelTestUtils.assertParameterNames( method__set__276, new String[] {"self", "obj", "value"} );
					}
					//Function test:__delete__
					{
					IMethod method__delete__277;
						IModelElement[] classcomputed_attribute273Childs = classcomputed_attribute273.getChildren();
						method__delete__277 = ModelTestUtils.getAssertMethod( classcomputed_attribute273Childs, "__delete__", 2 );
						ModelTestUtils.assertParameterNames( method__delete__277, new String[] {"self", "obj"} );
					}
				}
				//Function test:__init__
				{
				IMethod method__init__278;
					IModelElement[] classC272Childs = classC272.getChildren();
					method__init__278 = ModelTestUtils.getAssertMethod( classC272Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__278, new String[] {"self"} );
				}
				//Function test:__get_x
				{
				IMethod method__get_x279;
					IModelElement[] classC272Childs = classC272.getChildren();
					method__get_x279 = ModelTestUtils.getAssertMethod( classC272Childs, "__get_x", 1 );
					ModelTestUtils.assertParameterNames( method__get_x279, new String[] {"self"} );
				}
				//Function test:__set_x
				{
				IMethod method__set_x280;
					IModelElement[] classC272Childs = classC272.getChildren();
					method__set_x280 = ModelTestUtils.getAssertMethod( classC272Childs, "__set_x", 2 );
					ModelTestUtils.assertParameterNames( method__set_x280, new String[] {"self", "x"} );
				}
				//Function test:__delete_x
				{
				IMethod method__delete_x281;
					IModelElement[] classC272Childs = classC272.getChildren();
					method__delete_x281 = ModelTestUtils.getAssertMethod( classC272Childs, "__delete_x", 1 );
					ModelTestUtils.assertParameterNames( method__delete_x281, new String[] {"self"} );
				}
			}
		}
		//Function test:newslot
		{
		IMethod methodnewslot282;
			IModelElement[] moduleChilds = module.getChildren();
			methodnewslot282 = ModelTestUtils.getAssertMethod( moduleChilds, "newslot", 0 );
			//Class test
			{
			IType classC283;
				IModelElement[] methodnewslot282Childs = methodnewslot282.getChildren();
				classC283 = ModelTestUtils.getAssertClass( methodnewslot282Childs, "C" );
				//Function test:__new__
				{
				IMethod method__new__284;
					IModelElement[] classC283Childs = classC283.getChildren();
					method__new__284 = ModelTestUtils.getAssertMethod( classC283Childs, "__new__", 1 );
					ModelTestUtils.assertParameterNames( method__new__284, new String[] {"cls"} );
				}
				//Function test:__init__
				{
				IMethod method__init__285;
					IModelElement[] classC283Childs = classC283.getChildren();
					method__init__285 = ModelTestUtils.getAssertMethod( classC283Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__285, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD286;
				IModelElement[] methodnewslot282Childs = methodnewslot282.getChildren();
				classD286 = ModelTestUtils.getAssertClass( methodnewslot282Childs, "D" );
			}
		}
		//Function test:altmro
		{
		IMethod methodaltmro287;
			IModelElement[] moduleChilds = module.getChildren();
			methodaltmro287 = ModelTestUtils.getAssertMethod( moduleChilds, "altmro", 0 );
			//Class test
			{
			IType classA288;
				IModelElement[] methodaltmro287Childs = methodaltmro287.getChildren();
				classA288 = ModelTestUtils.getAssertClass( methodaltmro287Childs, "A" );
				//Function test:f
				{
				IMethod methodf289;
					IModelElement[] classA288Childs = classA288.getChildren();
					methodf289 = ModelTestUtils.getAssertMethod( classA288Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf289, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB290;
				IModelElement[] methodaltmro287Childs = methodaltmro287.getChildren();
				classB290 = ModelTestUtils.getAssertClass( methodaltmro287Childs, "B" );
			}
			//Class test
			{
			IType classC291;
				IModelElement[] methodaltmro287Childs = methodaltmro287.getChildren();
				classC291 = ModelTestUtils.getAssertClass( methodaltmro287Childs, "C" );
				//Function test:f
				{
				IMethod methodf292;
					IModelElement[] classC291Childs = classC291.getChildren();
					methodf292 = ModelTestUtils.getAssertMethod( classC291Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf292, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD293;
				IModelElement[] methodaltmro287Childs = methodaltmro287.getChildren();
				classD293 = ModelTestUtils.getAssertClass( methodaltmro287Childs, "D" );
			}
			//Class test
			{
			IType classPerverseMetaType294;
				IModelElement[] methodaltmro287Childs = methodaltmro287.getChildren();
				classPerverseMetaType294 = ModelTestUtils.getAssertClass( methodaltmro287Childs, "PerverseMetaType" );
				//Function test:mro
				{
				IMethod methodmro295;
					IModelElement[] classPerverseMetaType294Childs = classPerverseMetaType294.getChildren();
					methodmro295 = ModelTestUtils.getAssertMethod( classPerverseMetaType294Childs, "mro", 1 );
					ModelTestUtils.assertParameterNames( methodmro295, new String[] {"cls"} );
				}
			}
			//Class test
			{
			IType classX296;
				IModelElement[] methodaltmro287Childs = methodaltmro287.getChildren();
				classX296 = ModelTestUtils.getAssertClass( methodaltmro287Childs, "X" );
			}
		}
		//Function test:overloading
		{
		IMethod methodoverloading297;
			IModelElement[] moduleChilds = module.getChildren();
			methodoverloading297 = ModelTestUtils.getAssertMethod( moduleChilds, "overloading", 0 );
			//Class test
			{
			IType classB298;
				IModelElement[] methodoverloading297Childs = methodoverloading297.getChildren();
				classB298 = ModelTestUtils.getAssertClass( methodoverloading297Childs, "B" );
			}
			//Class test
			{
			IType classC299;
				IModelElement[] methodoverloading297Childs = methodoverloading297.getChildren();
				classC299 = ModelTestUtils.getAssertClass( methodoverloading297Childs, "C" );
				//Function test:__getattr__
				{
				IMethod method__getattr__300;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__getattr__300 = ModelTestUtils.getAssertMethod( classC299Childs, "__getattr__", 2 );
					ModelTestUtils.assertParameterNames( method__getattr__300, new String[] {"self", "name"} );
				}
				//Function test:__setattr__
				{
				IMethod method__setattr__301;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__setattr__301 = ModelTestUtils.getAssertMethod( classC299Childs, "__setattr__", 3 );
					ModelTestUtils.assertParameterNames( method__setattr__301, new String[] {"self", "name", "value"} );
				}
				//Function test:__delattr__
				{
				IMethod method__delattr__302;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__delattr__302 = ModelTestUtils.getAssertMethod( classC299Childs, "__delattr__", 2 );
					ModelTestUtils.assertParameterNames( method__delattr__302, new String[] {"self", "name"} );
				}
				//Function test:__getitem__
				{
				IMethod method__getitem__303;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__getitem__303 = ModelTestUtils.getAssertMethod( classC299Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__303, new String[] {"self", "key"} );
				}
				//Function test:__setitem__
				{
				IMethod method__setitem__304;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__setitem__304 = ModelTestUtils.getAssertMethod( classC299Childs, "__setitem__", 3 );
					ModelTestUtils.assertParameterNames( method__setitem__304, new String[] {"self", "key", "value"} );
				}
				//Function test:__delitem__
				{
				IMethod method__delitem__305;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__delitem__305 = ModelTestUtils.getAssertMethod( classC299Childs, "__delitem__", 2 );
					ModelTestUtils.assertParameterNames( method__delitem__305, new String[] {"self", "key"} );
				}
				//Function test:__getslice__
				{
				IMethod method__getslice__306;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__getslice__306 = ModelTestUtils.getAssertMethod( classC299Childs, "__getslice__", 3 );
					ModelTestUtils.assertParameterNames( method__getslice__306, new String[] {"self", "i", "j"} );
				}
				//Function test:__setslice__
				{
				IMethod method__setslice__307;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__setslice__307 = ModelTestUtils.getAssertMethod( classC299Childs, "__setslice__", 4 );
					ModelTestUtils.assertParameterNames( method__setslice__307, new String[] {"self", "i", "j", "value"} );
				}
				//Function test:__delslice__
				{
				IMethod method__delslice__308;
					IModelElement[] classC299Childs = classC299.getChildren();
					method__delslice__308 = ModelTestUtils.getAssertMethod( classC299Childs, "__delslice__", 3 );
					ModelTestUtils.assertParameterNames( method__delslice__308, new String[] {"self", "i", "j"} );
				}
			}
		}
		//Function test:methods
		{
		IMethod methodmethods309;
			IModelElement[] moduleChilds = module.getChildren();
			methodmethods309 = ModelTestUtils.getAssertMethod( moduleChilds, "methods", 0 );
			//Class test
			{
			IType classC310;
				IModelElement[] methodmethods309Childs = methodmethods309.getChildren();
				classC310 = ModelTestUtils.getAssertClass( methodmethods309Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__311;
					IModelElement[] classC310Childs = classC310.getChildren();
					method__init__311 = ModelTestUtils.getAssertMethod( classC310Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__311, new String[] {"self", "x"} );
				}
				//Function test:foo
				{
				IMethod methodfoo312;
					IModelElement[] classC310Childs = classC310.getChildren();
					methodfoo312 = ModelTestUtils.getAssertMethod( classC310Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo312, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD313;
				IModelElement[] methodmethods309Childs = methodmethods309.getChildren();
				classD313 = ModelTestUtils.getAssertClass( methodmethods309Childs, "D" );
			}
			//Class test
			{
			IType classE314;
				IModelElement[] methodmethods309Childs = methodmethods309.getChildren();
				classE314 = ModelTestUtils.getAssertClass( methodmethods309Childs, "E" );
			}
		}
		//Function test:specials
		{
		IMethod methodspecials315;
			IModelElement[] moduleChilds = module.getChildren();
			methodspecials315 = ModelTestUtils.getAssertMethod( moduleChilds, "specials", 0 );
			//Class test
			{
			IType classC316;
				IModelElement[] methodspecials315Childs = methodspecials315.getChildren();
				classC316 = ModelTestUtils.getAssertClass( methodspecials315Childs, "C" );
				//Function test:__getitem__
				{
				IMethod method__getitem__317;
					IModelElement[] classC316Childs = classC316.getChildren();
					method__getitem__317 = ModelTestUtils.getAssertMethod( classC316Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__317, new String[] {"self", "i"} );
				}
			}
			//Class test
			{
			IType classD318;
				IModelElement[] methodspecials315Childs = methodspecials315.getChildren();
				classD318 = ModelTestUtils.getAssertClass( methodspecials315Childs, "D" );
				//Function test:__getitem__
				{
				IMethod method__getitem__319;
					IModelElement[] classD318Childs = classD318.getChildren();
					method__getitem__319 = ModelTestUtils.getAssertMethod( classD318Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__319, new String[] {"self", "i"} );
				}
			}
			//Class test
			{
			IType classProxy320;
				IModelElement[] methodspecials315Childs = methodspecials315.getChildren();
				classProxy320 = ModelTestUtils.getAssertClass( methodspecials315Childs, "Proxy" );
				//Function test:__init__
				{
				IMethod method__init__321;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__init__321 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__321, new String[] {"self", "x"} );
				}
				//Function test:__nonzero__
				{
				IMethod method__nonzero__322;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__nonzero__322 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__nonzero__", 1 );
					ModelTestUtils.assertParameterNames( method__nonzero__322, new String[] {"self"} );
				}
				//Function test:__hash__
				{
				IMethod method__hash__323;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__hash__323 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__323, new String[] {"self"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__324;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__eq__324 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__324, new String[] {"self", "other"} );
				}
				//Function test:__ne__
				{
				IMethod method__ne__325;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__ne__325 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__ne__", 2 );
					ModelTestUtils.assertParameterNames( method__ne__325, new String[] {"self", "other"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__326;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__cmp__326 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__326, new String[] {"self", "other"} );
				}
				//Function test:__str__
				{
				IMethod method__str__327;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__str__327 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__327, new String[] {"self"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__328;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__repr__328 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__328, new String[] {"self"} );
				}
				//Function test:__contains__
				{
				IMethod method__contains__329;
					IModelElement[] classProxy320Childs = classProxy320.getChildren();
					method__contains__329 = ModelTestUtils.getAssertMethod( classProxy320Childs, "__contains__", 2 );
					ModelTestUtils.assertParameterNames( method__contains__329, new String[] {"self", "value"} );
				}
			}
			//Class test
			{
			IType classDProxy330;
				IModelElement[] methodspecials315Childs = methodspecials315.getChildren();
				classDProxy330 = ModelTestUtils.getAssertClass( methodspecials315Childs, "DProxy" );
				//Function test:__init__
				{
				IMethod method__init__331;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__init__331 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__331, new String[] {"self", "x"} );
				}
				//Function test:__nonzero__
				{
				IMethod method__nonzero__332;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__nonzero__332 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__nonzero__", 1 );
					ModelTestUtils.assertParameterNames( method__nonzero__332, new String[] {"self"} );
				}
				//Function test:__hash__
				{
				IMethod method__hash__333;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__hash__333 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__333, new String[] {"self"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__334;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__eq__334 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__334, new String[] {"self", "other"} );
				}
				//Function test:__ne__
				{
				IMethod method__ne__335;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__ne__335 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__ne__", 2 );
					ModelTestUtils.assertParameterNames( method__ne__335, new String[] {"self", "other"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__336;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__cmp__336 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__336, new String[] {"self", "other"} );
				}
				//Function test:__str__
				{
				IMethod method__str__337;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__str__337 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__337, new String[] {"self"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__338;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__repr__338 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__338, new String[] {"self"} );
				}
				//Function test:__contains__
				{
				IMethod method__contains__339;
					IModelElement[] classDProxy330Childs = classDProxy330.getChildren();
					method__contains__339 = ModelTestUtils.getAssertMethod( classDProxy330Childs, "__contains__", 2 );
					ModelTestUtils.assertParameterNames( method__contains__339, new String[] {"self", "value"} );
				}
			}
			//Function test:unsafecmp
			{
			IMethod methodunsafecmp340;
				IModelElement[] methodspecials315Childs = methodspecials315.getChildren();
				methodunsafecmp340 = ModelTestUtils.getAssertMethod( methodspecials315Childs, "unsafecmp", 2 );
				ModelTestUtils.assertParameterNames( methodunsafecmp340, new String[] {"a", "b"} );
			}
			//Class test
			{
			IType classLetter341;
				IModelElement[] methodspecials315Childs = methodspecials315.getChildren();
				classLetter341 = ModelTestUtils.getAssertClass( methodspecials315Childs, "Letter" );
				//Function test:__new__
				{
				IMethod method__new__342;
					IModelElement[] classLetter341Childs = classLetter341.getChildren();
					method__new__342 = ModelTestUtils.getAssertMethod( classLetter341Childs, "__new__", 2 );
					ModelTestUtils.assertParameterNames( method__new__342, new String[] {"cls", "letter"} );
				}
				//Function test:__str__
				{
				IMethod method__str__343;
					IModelElement[] classLetter341Childs = classLetter341.getChildren();
					method__str__343 = ModelTestUtils.getAssertMethod( classLetter341Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__343, new String[] {"self"} );
				}
			}
		}
		//Function test:weakrefs
		{
		IMethod methodweakrefs344;
			IModelElement[] moduleChilds = module.getChildren();
			methodweakrefs344 = ModelTestUtils.getAssertMethod( moduleChilds, "weakrefs", 0 );
			//Class test
			{
			IType classC345;
				IModelElement[] methodweakrefs344Childs = methodweakrefs344.getChildren();
				classC345 = ModelTestUtils.getAssertClass( methodweakrefs344Childs, "C" );
			}
			//Class test
			{
			IType classNoWeak346;
				IModelElement[] methodweakrefs344Childs = methodweakrefs344.getChildren();
				classNoWeak346 = ModelTestUtils.getAssertClass( methodweakrefs344Childs, "NoWeak" );
			}
			//Class test
			{
			IType classWeak347;
				IModelElement[] methodweakrefs344Childs = methodweakrefs344.getChildren();
				classWeak347 = ModelTestUtils.getAssertClass( methodweakrefs344Childs, "Weak" );
			}
		}
		//Function test:properties
		{
		IMethod methodproperties348;
			IModelElement[] moduleChilds = module.getChildren();
			methodproperties348 = ModelTestUtils.getAssertMethod( moduleChilds, "properties", 0 );
			//Class test
			{
			IType classC349;
				IModelElement[] methodproperties348Childs = methodproperties348.getChildren();
				classC349 = ModelTestUtils.getAssertClass( methodproperties348Childs, "C" );
				//Function test:getx
				{
				IMethod methodgetx350;
					IModelElement[] classC349Childs = classC349.getChildren();
					methodgetx350 = ModelTestUtils.getAssertMethod( classC349Childs, "getx", 1 );
					ModelTestUtils.assertParameterNames( methodgetx350, new String[] {"self"} );
				}
				//Function test:setx
				{
				IMethod methodsetx351;
					IModelElement[] classC349Childs = classC349.getChildren();
					methodsetx351 = ModelTestUtils.getAssertMethod( classC349Childs, "setx", 2 );
					ModelTestUtils.assertParameterNames( methodsetx351, new String[] {"self", "value"} );
				}
				//Function test:delx
				{
				IMethod methoddelx352;
					IModelElement[] classC349Childs = classC349.getChildren();
					methoddelx352 = ModelTestUtils.getAssertMethod( classC349Childs, "delx", 1 );
					ModelTestUtils.assertParameterNames( methoddelx352, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD353;
				IModelElement[] methodproperties348Childs = methodproperties348.getChildren();
				classD353 = ModelTestUtils.getAssertClass( methodproperties348Childs, "D" );
			}
		}
		//Function test:supers
		{
		IMethod methodsupers354;
			IModelElement[] moduleChilds = module.getChildren();
			methodsupers354 = ModelTestUtils.getAssertMethod( moduleChilds, "supers", 0 );
			//Class test
			{
			IType classA355;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classA355 = ModelTestUtils.getAssertClass( methodsupers354Childs, "A" );
				//Function test:meth
				{
				IMethod methodmeth356;
					IModelElement[] classA355Childs = classA355.getChildren();
					methodmeth356 = ModelTestUtils.getAssertMethod( classA355Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth356, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classB357;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classB357 = ModelTestUtils.getAssertClass( methodsupers354Childs, "B" );
				//Function test:__init__
				{
				IMethod method__init__358;
					IModelElement[] classB357Childs = classB357.getChildren();
					method__init__358 = ModelTestUtils.getAssertMethod( classB357Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__358, new String[] {"self"} );
				}
				//Function test:meth
				{
				IMethod methodmeth359;
					IModelElement[] classB357Childs = classB357.getChildren();
					methodmeth359 = ModelTestUtils.getAssertMethod( classB357Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth359, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classC360;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classC360 = ModelTestUtils.getAssertClass( methodsupers354Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth361;
					IModelElement[] classC360Childs = classC360.getChildren();
					methodmeth361 = ModelTestUtils.getAssertMethod( classC360Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth361, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classD362;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classD362 = ModelTestUtils.getAssertClass( methodsupers354Childs, "D" );
				//Function test:meth
				{
				IMethod methodmeth363;
					IModelElement[] classD362Childs = classD362.getChildren();
					methodmeth363 = ModelTestUtils.getAssertMethod( classD362Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth363, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classmysuper364;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classmysuper364 = ModelTestUtils.getAssertClass( methodsupers354Childs, "mysuper" );
				//Function test:__init__
				{
				IMethod method__init__365;
					IModelElement[] classmysuper364Childs = classmysuper364.getChildren();
					method__init__365 = ModelTestUtils.getAssertMethod( classmysuper364Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__365, new String[] {"self", "args"} );
				}
			}
			//Class test
			{
			IType classE366;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classE366 = ModelTestUtils.getAssertClass( methodsupers354Childs, "E" );
				//Function test:meth
				{
				IMethod methodmeth367;
					IModelElement[] classE366Childs = classE366.getChildren();
					methodmeth367 = ModelTestUtils.getAssertMethod( classE366Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth367, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classF368;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classF368 = ModelTestUtils.getAssertClass( methodsupers354Childs, "F" );
				//Function test:meth
				{
				IMethod methodmeth369;
					IModelElement[] classF368Childs = classF368.getChildren();
					methodmeth369 = ModelTestUtils.getAssertMethod( classF368Childs, "meth", 2 );
					ModelTestUtils.assertParameterNames( methodmeth369, new String[] {"self", "a"} );
				}
			}
			//Class test
			{
			IType classDDbase370;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classDDbase370 = ModelTestUtils.getAssertClass( methodsupers354Childs, "DDbase" );
				//Function test:getx
				{
				IMethod methodgetx371;
					IModelElement[] classDDbase370Childs = classDDbase370.getChildren();
					methodgetx371 = ModelTestUtils.getAssertMethod( classDDbase370Childs, "getx", 1 );
					ModelTestUtils.assertParameterNames( methodgetx371, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classDDsub372;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classDDsub372 = ModelTestUtils.getAssertClass( methodsupers354Childs, "DDsub" );
				//Function test:getx
				{
				IMethod methodgetx373;
					IModelElement[] classDDsub372Childs = classDDsub372.getChildren();
					methodgetx373 = ModelTestUtils.getAssertMethod( classDDsub372Childs, "getx", 1 );
					ModelTestUtils.assertParameterNames( methodgetx373, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classBase374;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classBase374 = ModelTestUtils.getAssertClass( methodsupers354Childs, "Base" );
			}
			//Class test
			{
			IType classSub375;
				IModelElement[] methodsupers354Childs = methodsupers354.getChildren();
				classSub375 = ModelTestUtils.getAssertClass( methodsupers354Childs, "Sub" );
				//Function test:test
				{
				IMethod methodtest376;
					IModelElement[] classSub375Childs = classSub375.getChildren();
					methodtest376 = ModelTestUtils.getAssertMethod( classSub375Childs, "test", 1 );
					ModelTestUtils.assertParameterNames( methodtest376, new String[] {"klass"} );
				}
			}
		}
		//Function test:inherits
		{
		IMethod methodinherits377;
			IModelElement[] moduleChilds = module.getChildren();
			methodinherits377 = ModelTestUtils.getAssertMethod( moduleChilds, "inherits", 0 );
			//Class test
			{
			IType classhexint378;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classhexint378 = ModelTestUtils.getAssertClass( methodinherits377Childs, "hexint" );
				//Function test:__repr__
				{
				IMethod method__repr__379;
					IModelElement[] classhexint378Childs = classhexint378.getChildren();
					method__repr__379 = ModelTestUtils.getAssertMethod( classhexint378Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__379, new String[] {"self"} );
				}
				//Function test:__add__
				{
				IMethod method__add__380;
					IModelElement[] classhexint378Childs = classhexint378.getChildren();
					method__add__380 = ModelTestUtils.getAssertMethod( classhexint378Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__380, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classoctlong381;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classoctlong381 = ModelTestUtils.getAssertClass( methodinherits377Childs, "octlong" );
				//Function test:__str__
				{
				IMethod method__str__382;
					IModelElement[] classoctlong381Childs = classoctlong381.getChildren();
					method__str__382 = ModelTestUtils.getAssertMethod( classoctlong381Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__382, new String[] {"self"} );
				}
				//Function test:__add__
				{
				IMethod method__add__383;
					IModelElement[] classoctlong381Childs = classoctlong381.getChildren();
					method__add__383 = ModelTestUtils.getAssertMethod( classoctlong381Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__383, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classlongclone384;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classlongclone384 = ModelTestUtils.getAssertClass( methodinherits377Childs, "longclone" );
			}
			//Class test
			{
			IType classprecfloat385;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classprecfloat385 = ModelTestUtils.getAssertClass( methodinherits377Childs, "precfloat" );
				//Function test:__init__
				{
				IMethod method__init__386;
					IModelElement[] classprecfloat385Childs = classprecfloat385.getChildren();
					method__init__386 = ModelTestUtils.getAssertMethod( classprecfloat385Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__386, new String[] {"self", "value", "prec"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__387;
					IModelElement[] classprecfloat385Childs = classprecfloat385.getChildren();
					method__repr__387 = ModelTestUtils.getAssertMethod( classprecfloat385Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__387, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classmadcomplex388;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classmadcomplex388 = ModelTestUtils.getAssertClass( methodinherits377Childs, "madcomplex" );
				//Function test:__repr__
				{
				IMethod method__repr__389;
					IModelElement[] classmadcomplex388Childs = classmadcomplex388.getChildren();
					method__repr__389 = ModelTestUtils.getAssertMethod( classmadcomplex388Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__389, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classmadtuple390;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classmadtuple390 = ModelTestUtils.getAssertClass( methodinherits377Childs, "madtuple" );
				//Function test:rev
				{
				IMethod methodrev391;
					IModelElement[] classmadtuple390Childs = classmadtuple390.getChildren();
					methodrev391 = ModelTestUtils.getAssertMethod( classmadtuple390Childs, "rev", 1 );
					ModelTestUtils.assertParameterNames( methodrev391, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classmadstring392;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classmadstring392 = ModelTestUtils.getAssertClass( methodinherits377Childs, "madstring" );
				//Function test:rev
				{
				IMethod methodrev393;
					IModelElement[] classmadstring392Childs = classmadstring392.getChildren();
					methodrev393 = ModelTestUtils.getAssertMethod( classmadstring392Childs, "rev", 1 );
					ModelTestUtils.assertParameterNames( methodrev393, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classmadunicode394;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classmadunicode394 = ModelTestUtils.getAssertClass( methodinherits377Childs, "madunicode" );
				//Function test:rev
				{
				IMethod methodrev395;
					IModelElement[] classmadunicode394Childs = classmadunicode394.getChildren();
					methodrev395 = ModelTestUtils.getAssertMethod( classmadunicode394Childs, "rev", 1 );
					ModelTestUtils.assertParameterNames( methodrev395, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classsublist396;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classsublist396 = ModelTestUtils.getAssertClass( methodinherits377Childs, "sublist" );
			}
			//Class test
			{
			IType classCountedInput397;
				IModelElement[] methodinherits377Childs = methodinherits377.getChildren();
				classCountedInput397 = ModelTestUtils.getAssertClass( methodinherits377Childs, "CountedInput" );
				//Function test:readline
				{
				IMethod methodreadline398;
					IModelElement[] classCountedInput397Childs = classCountedInput397.getChildren();
					methodreadline398 = ModelTestUtils.getAssertMethod( classCountedInput397Childs, "readline", 1 );
					ModelTestUtils.assertParameterNames( methodreadline398, new String[] {"self"} );
				}
			}
		}
		//Function test:keywords
		{
		IMethod methodkeywords399;
			IModelElement[] moduleChilds = module.getChildren();
			methodkeywords399 = ModelTestUtils.getAssertMethod( moduleChilds, "keywords", 0 );
		}
		//Function test:restricted
		{
		IMethod methodrestricted400;
			IModelElement[] moduleChilds = module.getChildren();
			methodrestricted400 = ModelTestUtils.getAssertMethod( moduleChilds, "restricted", 0 );
		}
		//Function test:str_subclass_as_dict_key
		{
		IMethod methodstr_subclass_as_dict_key401;
			IModelElement[] moduleChilds = module.getChildren();
			methodstr_subclass_as_dict_key401 = ModelTestUtils.getAssertMethod( moduleChilds, "str_subclass_as_dict_key", 0 );
			//Class test
			{
			IType classcistr402;
				IModelElement[] methodstr_subclass_as_dict_key401Childs = methodstr_subclass_as_dict_key401.getChildren();
				classcistr402 = ModelTestUtils.getAssertClass( methodstr_subclass_as_dict_key401Childs, "cistr" );
				//Function test:__init__
				{
				IMethod method__init__403;
					IModelElement[] classcistr402Childs = classcistr402.getChildren();
					method__init__403 = ModelTestUtils.getAssertMethod( classcistr402Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__403, new String[] {"self", "value"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__404;
					IModelElement[] classcistr402Childs = classcistr402.getChildren();
					method__eq__404 = ModelTestUtils.getAssertMethod( classcistr402Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__404, new String[] {"self", "other"} );
				}
				//Function test:__hash__
				{
				IMethod method__hash__405;
					IModelElement[] classcistr402Childs = classcistr402.getChildren();
					method__hash__405 = ModelTestUtils.getAssertMethod( classcistr402Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__405, new String[] {"self"} );
				}
			}
		}
		//Function test:classic_comparisons
		{
		IMethod methodclassic_comparisons406;
			IModelElement[] moduleChilds = module.getChildren();
			methodclassic_comparisons406 = ModelTestUtils.getAssertMethod( moduleChilds, "classic_comparisons", 0 );
			//Class test
			{
			IType classclassic407;
				IModelElement[] methodclassic_comparisons406Childs = methodclassic_comparisons406.getChildren();
				classclassic407 = ModelTestUtils.getAssertClass( methodclassic_comparisons406Childs, "classic" );
			}
			//Class test
			{
			IType classC408;
				IModelElement[] methodclassic_comparisons406Childs = methodclassic_comparisons406.getChildren();
				classC408 = ModelTestUtils.getAssertClass( methodclassic_comparisons406Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__409;
					IModelElement[] classC408Childs = classC408.getChildren();
					method__init__409 = ModelTestUtils.getAssertMethod( classC408Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__409, new String[] {"self", "value"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__410;
					IModelElement[] classC408Childs = classC408.getChildren();
					method__cmp__410 = ModelTestUtils.getAssertMethod( classC408Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__410, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:rich_comparisons
		{
		IMethod methodrich_comparisons411;
			IModelElement[] moduleChilds = module.getChildren();
			methodrich_comparisons411 = ModelTestUtils.getAssertMethod( moduleChilds, "rich_comparisons", 0 );
			//Class test
			{
			IType classZ412;
				IModelElement[] methodrich_comparisons411Childs = methodrich_comparisons411.getChildren();
				classZ412 = ModelTestUtils.getAssertClass( methodrich_comparisons411Childs, "Z" );
			}
			//Class test
			{
			IType classZZ413;
				IModelElement[] methodrich_comparisons411Childs = methodrich_comparisons411.getChildren();
				classZZ413 = ModelTestUtils.getAssertClass( methodrich_comparisons411Childs, "ZZ" );
				//Function test:__eq__
				{
				IMethod method__eq__414;
					IModelElement[] classZZ413Childs = classZZ413.getChildren();
					method__eq__414 = ModelTestUtils.getAssertMethod( classZZ413Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__414, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classclassic415;
				IModelElement[] methodrich_comparisons411Childs = methodrich_comparisons411.getChildren();
				classclassic415 = ModelTestUtils.getAssertClass( methodrich_comparisons411Childs, "classic" );
			}
			//Class test
			{
			IType classC416;
				IModelElement[] methodrich_comparisons411Childs = methodrich_comparisons411.getChildren();
				classC416 = ModelTestUtils.getAssertClass( methodrich_comparisons411Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__417;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__init__417 = ModelTestUtils.getAssertMethod( classC416Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__417, new String[] {"self", "value"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__418;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__cmp__418 = ModelTestUtils.getAssertMethod( classC416Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__418, new String[] {"self", "other"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__419;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__eq__419 = ModelTestUtils.getAssertMethod( classC416Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__419, new String[] {"self", "other"} );
				}
				//Function test:__ne__
				{
				IMethod method__ne__420;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__ne__420 = ModelTestUtils.getAssertMethod( classC416Childs, "__ne__", 2 );
					ModelTestUtils.assertParameterNames( method__ne__420, new String[] {"self", "other"} );
				}
				//Function test:__lt__
				{
				IMethod method__lt__421;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__lt__421 = ModelTestUtils.getAssertMethod( classC416Childs, "__lt__", 2 );
					ModelTestUtils.assertParameterNames( method__lt__421, new String[] {"self", "other"} );
				}
				//Function test:__le__
				{
				IMethod method__le__422;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__le__422 = ModelTestUtils.getAssertMethod( classC416Childs, "__le__", 2 );
					ModelTestUtils.assertParameterNames( method__le__422, new String[] {"self", "other"} );
				}
				//Function test:__gt__
				{
				IMethod method__gt__423;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__gt__423 = ModelTestUtils.getAssertMethod( classC416Childs, "__gt__", 2 );
					ModelTestUtils.assertParameterNames( method__gt__423, new String[] {"self", "other"} );
				}
				//Function test:__ge__
				{
				IMethod method__ge__424;
					IModelElement[] classC416Childs = classC416.getChildren();
					method__ge__424 = ModelTestUtils.getAssertMethod( classC416Childs, "__ge__", 2 );
					ModelTestUtils.assertParameterNames( method__ge__424, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:coercions
		{
		IMethod methodcoercions425;
			IModelElement[] moduleChilds = module.getChildren();
			methodcoercions425 = ModelTestUtils.getAssertMethod( moduleChilds, "coercions", 0 );
			//Class test
			{
			IType classI426;
				IModelElement[] methodcoercions425Childs = methodcoercions425.getChildren();
				classI426 = ModelTestUtils.getAssertClass( methodcoercions425Childs, "I" );
			}
			//Class test
			{
			IType classL427;
				IModelElement[] methodcoercions425Childs = methodcoercions425.getChildren();
				classL427 = ModelTestUtils.getAssertClass( methodcoercions425Childs, "L" );
			}
			//Class test
			{
			IType classF428;
				IModelElement[] methodcoercions425Childs = methodcoercions425.getChildren();
				classF428 = ModelTestUtils.getAssertClass( methodcoercions425Childs, "F" );
			}
			//Class test
			{
			IType classC429;
				IModelElement[] methodcoercions425Childs = methodcoercions425.getChildren();
				classC429 = ModelTestUtils.getAssertClass( methodcoercions425Childs, "C" );
			}
		}
		//Function test:descrdoc
		{
		IMethod methoddescrdoc430;
			IModelElement[] moduleChilds = module.getChildren();
			methoddescrdoc430 = ModelTestUtils.getAssertMethod( moduleChilds, "descrdoc", 0 );
			//Function test:check
			{
			IMethod methodcheck431;
				IModelElement[] methoddescrdoc430Childs = methoddescrdoc430.getChildren();
				methodcheck431 = ModelTestUtils.getAssertMethod( methoddescrdoc430Childs, "check", 2 );
				ModelTestUtils.assertParameterNames( methodcheck431, new String[] {"descr", "what"} );
			}
		}
		//Function test:setclass
		{
		IMethod methodsetclass432;
			IModelElement[] moduleChilds = module.getChildren();
			methodsetclass432 = ModelTestUtils.getAssertMethod( moduleChilds, "setclass", 0 );
			//Class test
			{
			IType classC433;
				IModelElement[] methodsetclass432Childs = methodsetclass432.getChildren();
				classC433 = ModelTestUtils.getAssertClass( methodsetclass432Childs, "C" );
			}
			//Class test
			{
			IType classD434;
				IModelElement[] methodsetclass432Childs = methodsetclass432.getChildren();
				classD434 = ModelTestUtils.getAssertClass( methodsetclass432Childs, "D" );
			}
			//Class test
			{
			IType classE435;
				IModelElement[] methodsetclass432Childs = methodsetclass432.getChildren();
				classE435 = ModelTestUtils.getAssertClass( methodsetclass432Childs, "E" );
			}
			//Class test
			{
			IType classF436;
				IModelElement[] methodsetclass432Childs = methodsetclass432.getChildren();
				classF436 = ModelTestUtils.getAssertClass( methodsetclass432Childs, "F" );
			}
			//Function test:cant
			{
			IMethod methodcant437;
				IModelElement[] methodsetclass432Childs = methodsetclass432.getChildren();
				methodcant437 = ModelTestUtils.getAssertMethod( methodsetclass432Childs, "cant", 2 );
				ModelTestUtils.assertParameterNames( methodcant437, new String[] {"x", "C"} );
			}
			//Class test
			{
			IType classInt438;
				IModelElement[] methodsetclass432Childs = methodsetclass432.getChildren();
				classInt438 = ModelTestUtils.getAssertClass( methodsetclass432Childs, "Int" );
			}
		}
		//Function test:setdict
		{
		IMethod methodsetdict439;
			IModelElement[] moduleChilds = module.getChildren();
			methodsetdict439 = ModelTestUtils.getAssertMethod( moduleChilds, "setdict", 0 );
			//Class test
			{
			IType classC440;
				IModelElement[] methodsetdict439Childs = methodsetdict439.getChildren();
				classC440 = ModelTestUtils.getAssertClass( methodsetdict439Childs, "C" );
			}
			//Function test:cant
			{
			IMethod methodcant441;
				IModelElement[] methodsetdict439Childs = methodsetdict439.getChildren();
				methodcant441 = ModelTestUtils.getAssertMethod( methodsetdict439Childs, "cant", 2 );
				ModelTestUtils.assertParameterNames( methodcant441, new String[] {"x", "dict"} );
			}
		}
		//Function test:pickles
		{
		IMethod methodpickles442;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickles442 = ModelTestUtils.getAssertMethod( moduleChilds, "pickles", 0 );
			//Function test:sorteditems
			{
			IMethod methodsorteditems443;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				methodsorteditems443 = ModelTestUtils.getAssertMethod( methodpickles442Childs, "sorteditems", 1 );
				ModelTestUtils.assertParameterNames( methodsorteditems443, new String[] {"d"} );
			}
			//Class test
			{
			IType classC444;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				classC444 = ModelTestUtils.getAssertClass( methodpickles442Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__445;
					IModelElement[] classC444Childs = classC444.getChildren();
					method__init__445 = ModelTestUtils.getAssertMethod( classC444Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__445, new String[] {"self", "a", "b"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__446;
					IModelElement[] classC444Childs = classC444.getChildren();
					method__repr__446 = ModelTestUtils.getAssertMethod( classC444Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__446, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC1447;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				classC1447 = ModelTestUtils.getAssertClass( methodpickles442Childs, "C1" );
				//Function test:__new__
				{
				IMethod method__new__448;
					IModelElement[] classC1447Childs = classC1447.getChildren();
					method__new__448 = ModelTestUtils.getAssertMethod( classC1447Childs, "__new__", 3 );
					ModelTestUtils.assertParameterNames( method__new__448, new String[] {"cls", "a", "b"} );
				}
				//Function test:__getnewargs__
				{
				IMethod method__getnewargs__449;
					IModelElement[] classC1447Childs = classC1447.getChildren();
					method__getnewargs__449 = ModelTestUtils.getAssertMethod( classC1447Childs, "__getnewargs__", 1 );
					ModelTestUtils.assertParameterNames( method__getnewargs__449, new String[] {"self"} );
				}
				//Function test:__init__
				{
				IMethod method__init__450;
					IModelElement[] classC1447Childs = classC1447.getChildren();
					method__init__450 = ModelTestUtils.getAssertMethod( classC1447Childs, "__init__", 3 );
					ModelTestUtils.assertParameterNames( method__init__450, new String[] {"self", "a", "b"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__451;
					IModelElement[] classC1447Childs = classC1447.getChildren();
					method__repr__451 = ModelTestUtils.getAssertMethod( classC1447Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__451, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC2452;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				classC2452 = ModelTestUtils.getAssertClass( methodpickles442Childs, "C2" );
				//Function test:__new__
				{
				IMethod method__new__453;
					IModelElement[] classC2452Childs = classC2452.getChildren();
					method__new__453 = ModelTestUtils.getAssertMethod( classC2452Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__453, new String[] {"cls", "a", "b", "val"} );
				}
				//Function test:__getnewargs__
				{
				IMethod method__getnewargs__454;
					IModelElement[] classC2452Childs = classC2452.getChildren();
					method__getnewargs__454 = ModelTestUtils.getAssertMethod( classC2452Childs, "__getnewargs__", 1 );
					ModelTestUtils.assertParameterNames( method__getnewargs__454, new String[] {"self"} );
				}
				//Function test:__init__
				{
				IMethod method__init__455;
					IModelElement[] classC2452Childs = classC2452.getChildren();
					method__init__455 = ModelTestUtils.getAssertMethod( classC2452Childs, "__init__", 4 );
					ModelTestUtils.assertParameterNames( method__init__455, new String[] {"self", "a", "b", "val"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__456;
					IModelElement[] classC2452Childs = classC2452.getChildren();
					method__repr__456 = ModelTestUtils.getAssertMethod( classC2452Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__456, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC3457;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				classC3457 = ModelTestUtils.getAssertClass( methodpickles442Childs, "C3" );
				//Function test:__init__
				{
				IMethod method__init__458;
					IModelElement[] classC3457Childs = classC3457.getChildren();
					method__init__458 = ModelTestUtils.getAssertMethod( classC3457Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__458, new String[] {"self", "foo"} );
				}
				//Function test:__getstate__
				{
				IMethod method__getstate__459;
					IModelElement[] classC3457Childs = classC3457.getChildren();
					method__getstate__459 = ModelTestUtils.getAssertMethod( classC3457Childs, "__getstate__", 1 );
					ModelTestUtils.assertParameterNames( method__getstate__459, new String[] {"self"} );
				}
				//Function test:__setstate__
				{
				IMethod method__setstate__460;
					IModelElement[] classC3457Childs = classC3457.getChildren();
					method__setstate__460 = ModelTestUtils.getAssertMethod( classC3457Childs, "__setstate__", 2 );
					ModelTestUtils.assertParameterNames( method__setstate__460, new String[] {"self", "foo"} );
				}
			}
			//Class test
			{
			IType classC4classic461;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				classC4classic461 = ModelTestUtils.getAssertClass( methodpickles442Childs, "C4classic" );
			}
			//Class test
			{
			IType classC4462;
				IModelElement[] methodpickles442Childs = methodpickles442.getChildren();
				classC4462 = ModelTestUtils.getAssertClass( methodpickles442Childs, "C4" );
			}
		}
		//Function test:pickleslots
		{
		IMethod methodpickleslots463;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickleslots463 = ModelTestUtils.getAssertMethod( moduleChilds, "pickleslots", 0 );
			//Class test
			{
			IType classB464;
				IModelElement[] methodpickleslots463Childs = methodpickleslots463.getChildren();
				classB464 = ModelTestUtils.getAssertClass( methodpickleslots463Childs, "B" );
			}
			//Class test
			{
			IType classC465;
				IModelElement[] methodpickleslots463Childs = methodpickleslots463.getChildren();
				classC465 = ModelTestUtils.getAssertClass( methodpickleslots463Childs, "C" );
			}
			//Class test
			{
			IType classD466;
				IModelElement[] methodpickleslots463Childs = methodpickleslots463.getChildren();
				classD466 = ModelTestUtils.getAssertClass( methodpickleslots463Childs, "D" );
			}
			//Class test
			{
			IType classC467;
				IModelElement[] methodpickleslots463Childs = methodpickleslots463.getChildren();
				classC467 = ModelTestUtils.getAssertClass( methodpickleslots463Childs, "C" );
				//Function test:__getstate__
				{
				IMethod method__getstate__468;
					IModelElement[] classC467Childs = classC467.getChildren();
					method__getstate__468 = ModelTestUtils.getAssertMethod( classC467Childs, "__getstate__", 1 );
					ModelTestUtils.assertParameterNames( method__getstate__468, new String[] {"self"} );
				}
				//Function test:__setstate__
				{
				IMethod method__setstate__469;
					IModelElement[] classC467Childs = classC467.getChildren();
					method__setstate__469 = ModelTestUtils.getAssertMethod( classC467Childs, "__setstate__", 2 );
					ModelTestUtils.assertParameterNames( method__setstate__469, new String[] {"self", "d"} );
				}
			}
			//Class test
			{
			IType classD470;
				IModelElement[] methodpickleslots463Childs = methodpickleslots463.getChildren();
				classD470 = ModelTestUtils.getAssertClass( methodpickleslots463Childs, "D" );
			}
			//Class test
			{
			IType classE471;
				IModelElement[] methodpickleslots463Childs = methodpickleslots463.getChildren();
				classE471 = ModelTestUtils.getAssertClass( methodpickleslots463Childs, "E" );
			}
		}
		//Function test:copies
		{
		IMethod methodcopies472;
			IModelElement[] moduleChilds = module.getChildren();
			methodcopies472 = ModelTestUtils.getAssertMethod( moduleChilds, "copies", 0 );
			//Class test
			{
			IType classC473;
				IModelElement[] methodcopies472Childs = methodcopies472.getChildren();
				classC473 = ModelTestUtils.getAssertClass( methodcopies472Childs, "C" );
			}
		}
		//Function test:binopoverride
		{
		IMethod methodbinopoverride474;
			IModelElement[] moduleChilds = module.getChildren();
			methodbinopoverride474 = ModelTestUtils.getAssertMethod( moduleChilds, "binopoverride", 0 );
			//Class test
			{
			IType classI475;
				IModelElement[] methodbinopoverride474Childs = methodbinopoverride474.getChildren();
				classI475 = ModelTestUtils.getAssertClass( methodbinopoverride474Childs, "I" );
				//Function test:__repr__
				{
				IMethod method__repr__476;
					IModelElement[] classI475Childs = classI475.getChildren();
					method__repr__476 = ModelTestUtils.getAssertMethod( classI475Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__476, new String[] {"self"} );
				}
				//Function test:__add__
				{
				IMethod method__add__477;
					IModelElement[] classI475Childs = classI475.getChildren();
					method__add__477 = ModelTestUtils.getAssertMethod( classI475Childs, "__add__", 2 );
					ModelTestUtils.assertParameterNames( method__add__477, new String[] {"self", "other"} );
				}
				//Function test:__pow__
				{
				IMethod method__pow__478;
					IModelElement[] classI475Childs = classI475.getChildren();
					method__pow__478 = ModelTestUtils.getAssertMethod( classI475Childs, "__pow__", 3 );
					ModelTestUtils.assertParameterNames( method__pow__478, new String[] {"self", "other", "mod"} );
				}
				//Function test:__rpow__
				{
				IMethod method__rpow__479;
					IModelElement[] classI475Childs = classI475.getChildren();
					method__rpow__479 = ModelTestUtils.getAssertMethod( classI475Childs, "__rpow__", 3 );
					ModelTestUtils.assertParameterNames( method__rpow__479, new String[] {"self", "other", "mod"} );
				}
			}
			//Class test
			{
			IType classS480;
				IModelElement[] methodbinopoverride474Childs = methodbinopoverride474.getChildren();
				classS480 = ModelTestUtils.getAssertClass( methodbinopoverride474Childs, "S" );
				//Function test:__eq__
				{
				IMethod method__eq__481;
					IModelElement[] classS480Childs = classS480.getChildren();
					method__eq__481 = ModelTestUtils.getAssertMethod( classS480Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__481, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:subclasspropagation
		{
		IMethod methodsubclasspropagation482;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubclasspropagation482 = ModelTestUtils.getAssertMethod( moduleChilds, "subclasspropagation", 0 );
			//Class test
			{
			IType classA483;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				classA483 = ModelTestUtils.getAssertClass( methodsubclasspropagation482Childs, "A" );
			}
			//Class test
			{
			IType classB484;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				classB484 = ModelTestUtils.getAssertClass( methodsubclasspropagation482Childs, "B" );
			}
			//Class test
			{
			IType classC485;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				classC485 = ModelTestUtils.getAssertClass( methodsubclasspropagation482Childs, "C" );
			}
			//Class test
			{
			IType classD486;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				classD486 = ModelTestUtils.getAssertClass( methodsubclasspropagation482Childs, "D" );
			}
			//Function test:__getattribute__
			{
			IMethod method__getattribute__487;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				method__getattribute__487 = ModelTestUtils.getAssertMethod( methodsubclasspropagation482Childs, "__getattribute__", 2 );
				ModelTestUtils.assertParameterNames( method__getattribute__487, new String[] {"self", "name"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__488;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				method__getattr__488 = ModelTestUtils.getAssertMethod( methodsubclasspropagation482Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__488, new String[] {"self", "name"} );
			}
			//Class test
			{
			IType classA489;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				classA489 = ModelTestUtils.getAssertClass( methodsubclasspropagation482Childs, "A" );
			}
			//Class test
			{
			IType classB490;
				IModelElement[] methodsubclasspropagation482Childs = methodsubclasspropagation482.getChildren();
				classB490 = ModelTestUtils.getAssertClass( methodsubclasspropagation482Childs, "B" );
			}
		}
		//Function test:buffer_inherit
		{
		IMethod methodbuffer_inherit491;
			IModelElement[] moduleChilds = module.getChildren();
			methodbuffer_inherit491 = ModelTestUtils.getAssertMethod( moduleChilds, "buffer_inherit", 0 );
			//Class test
			{
			IType classMyStr492;
				IModelElement[] methodbuffer_inherit491Childs = methodbuffer_inherit491.getChildren();
				classMyStr492 = ModelTestUtils.getAssertClass( methodbuffer_inherit491Childs, "MyStr" );
			}
			//Class test
			{
			IType classMyUni493;
				IModelElement[] methodbuffer_inherit491Childs = methodbuffer_inherit491.getChildren();
				classMyUni493 = ModelTestUtils.getAssertClass( methodbuffer_inherit491Childs, "MyUni" );
			}
			//Class test
			{
			IType classMyInt494;
				IModelElement[] methodbuffer_inherit491Childs = methodbuffer_inherit491.getChildren();
				classMyInt494 = ModelTestUtils.getAssertClass( methodbuffer_inherit491Childs, "MyInt" );
			}
		}
		//Function test:str_of_str_subclass
		{
		IMethod methodstr_of_str_subclass495;
			IModelElement[] moduleChilds = module.getChildren();
			methodstr_of_str_subclass495 = ModelTestUtils.getAssertMethod( moduleChilds, "str_of_str_subclass", 0 );
			//Class test
			{
			IType classoctetstring496;
				IModelElement[] methodstr_of_str_subclass495Childs = methodstr_of_str_subclass495.getChildren();
				classoctetstring496 = ModelTestUtils.getAssertClass( methodstr_of_str_subclass495Childs, "octetstring" );
				//Function test:__str__
				{
				IMethod method__str__497;
					IModelElement[] classoctetstring496Childs = classoctetstring496.getChildren();
					method__str__497 = ModelTestUtils.getAssertMethod( classoctetstring496Childs, "__str__", 1 );
					ModelTestUtils.assertParameterNames( method__str__497, new String[] {"self"} );
				}
				//Function test:__repr__
				{
				IMethod method__repr__498;
					IModelElement[] classoctetstring496Childs = classoctetstring496.getChildren();
					method__repr__498 = ModelTestUtils.getAssertMethod( classoctetstring496Childs, "__repr__", 1 );
					ModelTestUtils.assertParameterNames( method__repr__498, new String[] {"self"} );
				}
			}
		}
		//Function test:kwdargs
		{
		IMethod methodkwdargs499;
			IModelElement[] moduleChilds = module.getChildren();
			methodkwdargs499 = ModelTestUtils.getAssertMethod( moduleChilds, "kwdargs", 0 );
			//Function test:f
			{
			IMethod methodf500;
				IModelElement[] methodkwdargs499Childs = methodkwdargs499.getChildren();
				methodf500 = ModelTestUtils.getAssertMethod( methodkwdargs499Childs, "f", 1 );
				ModelTestUtils.assertParameterNames( methodf500, new String[] {"a"} );
			}
		}
		//Function test:delhook
		{
		IMethod methoddelhook501;
			IModelElement[] moduleChilds = module.getChildren();
			methoddelhook501 = ModelTestUtils.getAssertMethod( moduleChilds, "delhook", 0 );
			//Class test
			{
			IType classC502;
				IModelElement[] methoddelhook501Childs = methoddelhook501.getChildren();
				classC502 = ModelTestUtils.getAssertClass( methoddelhook501Childs, "C" );
				//Function test:__del__
				{
				IMethod method__del__503;
					IModelElement[] classC502Childs = classC502.getChildren();
					method__del__503 = ModelTestUtils.getAssertMethod( classC502Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__503, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD504;
				IModelElement[] methoddelhook501Childs = methoddelhook501.getChildren();
				classD504 = ModelTestUtils.getAssertClass( methoddelhook501Childs, "D" );
			}
		}
		//Function test:hashinherit
		{
		IMethod methodhashinherit505;
			IModelElement[] moduleChilds = module.getChildren();
			methodhashinherit505 = ModelTestUtils.getAssertMethod( moduleChilds, "hashinherit", 0 );
			//Class test
			{
			IType classmydict506;
				IModelElement[] methodhashinherit505Childs = methodhashinherit505.getChildren();
				classmydict506 = ModelTestUtils.getAssertClass( methodhashinherit505Childs, "mydict" );
			}
			//Class test
			{
			IType classmylist507;
				IModelElement[] methodhashinherit505Childs = methodhashinherit505.getChildren();
				classmylist507 = ModelTestUtils.getAssertClass( methodhashinherit505Childs, "mylist" );
			}
		}
		//Function test:strops
		{
		IMethod methodstrops508;
			IModelElement[] moduleChilds = module.getChildren();
			methodstrops508 = ModelTestUtils.getAssertMethod( moduleChilds, "strops", 0 );
		}
		//Function test:deepcopyrecursive
		{
		IMethod methoddeepcopyrecursive509;
			IModelElement[] moduleChilds = module.getChildren();
			methoddeepcopyrecursive509 = ModelTestUtils.getAssertMethod( moduleChilds, "deepcopyrecursive", 0 );
			//Class test
			{
			IType classNode510;
				IModelElement[] methoddeepcopyrecursive509Childs = methoddeepcopyrecursive509.getChildren();
				classNode510 = ModelTestUtils.getAssertClass( methoddeepcopyrecursive509Childs, "Node" );
			}
		}
		//Function test:modules
		{
		IMethod methodmodules511;
			IModelElement[] moduleChilds = module.getChildren();
			methodmodules511 = ModelTestUtils.getAssertMethod( moduleChilds, "modules", 0 );
		}
		//Function test:dictproxyiterkeys
		{
		IMethod methoddictproxyiterkeys512;
			IModelElement[] moduleChilds = module.getChildren();
			methoddictproxyiterkeys512 = ModelTestUtils.getAssertMethod( moduleChilds, "dictproxyiterkeys", 0 );
			//Class test
			{
			IType classC513;
				IModelElement[] methoddictproxyiterkeys512Childs = methoddictproxyiterkeys512.getChildren();
				classC513 = ModelTestUtils.getAssertClass( methoddictproxyiterkeys512Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth514;
					IModelElement[] classC513Childs = classC513.getChildren();
					methodmeth514 = ModelTestUtils.getAssertMethod( classC513Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth514, new String[] {"self"} );
				}
			}
		}
		//Function test:dictproxyitervalues
		{
		IMethod methoddictproxyitervalues515;
			IModelElement[] moduleChilds = module.getChildren();
			methoddictproxyitervalues515 = ModelTestUtils.getAssertMethod( moduleChilds, "dictproxyitervalues", 0 );
			//Class test
			{
			IType classC516;
				IModelElement[] methoddictproxyitervalues515Childs = methoddictproxyitervalues515.getChildren();
				classC516 = ModelTestUtils.getAssertClass( methoddictproxyitervalues515Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth517;
					IModelElement[] classC516Childs = classC516.getChildren();
					methodmeth517 = ModelTestUtils.getAssertMethod( classC516Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth517, new String[] {"self"} );
				}
			}
		}
		//Function test:dictproxyiteritems
		{
		IMethod methoddictproxyiteritems518;
			IModelElement[] moduleChilds = module.getChildren();
			methoddictproxyiteritems518 = ModelTestUtils.getAssertMethod( moduleChilds, "dictproxyiteritems", 0 );
			//Class test
			{
			IType classC519;
				IModelElement[] methoddictproxyiteritems518Childs = methoddictproxyiteritems518.getChildren();
				classC519 = ModelTestUtils.getAssertClass( methoddictproxyiteritems518Childs, "C" );
				//Function test:meth
				{
				IMethod methodmeth520;
					IModelElement[] classC519Childs = classC519.getChildren();
					methodmeth520 = ModelTestUtils.getAssertMethod( classC519Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth520, new String[] {"self"} );
				}
			}
		}
		//Function test:funnynew
		{
		IMethod methodfunnynew521;
			IModelElement[] moduleChilds = module.getChildren();
			methodfunnynew521 = ModelTestUtils.getAssertMethod( moduleChilds, "funnynew", 0 );
			//Class test
			{
			IType classC522;
				IModelElement[] methodfunnynew521Childs = methodfunnynew521.getChildren();
				classC522 = ModelTestUtils.getAssertClass( methodfunnynew521Childs, "C" );
				//Function test:__new__
				{
				IMethod method__new__523;
					IModelElement[] classC522Childs = classC522.getChildren();
					method__new__523 = ModelTestUtils.getAssertMethod( classC522Childs, "__new__", 2 );
					ModelTestUtils.assertParameterNames( method__new__523, new String[] {"cls", "arg"} );
				}
			}
			//Class test
			{
			IType classD524;
				IModelElement[] methodfunnynew521Childs = methodfunnynew521.getChildren();
				classD524 = ModelTestUtils.getAssertClass( methodfunnynew521Childs, "D" );
				//Function test:__init__
				{
				IMethod method__init__525;
					IModelElement[] classD524Childs = classD524.getChildren();
					method__init__525 = ModelTestUtils.getAssertMethod( classD524Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__525, new String[] {"self", "arg"} );
				}
			}
		}
		//Function test:imulbug
		{
		IMethod methodimulbug526;
			IModelElement[] moduleChilds = module.getChildren();
			methodimulbug526 = ModelTestUtils.getAssertMethod( moduleChilds, "imulbug", 0 );
			//Class test
			{
			IType classC527;
				IModelElement[] methodimulbug526Childs = methodimulbug526.getChildren();
				classC527 = ModelTestUtils.getAssertClass( methodimulbug526Childs, "C" );
				//Function test:__imul__
				{
				IMethod method__imul__528;
					IModelElement[] classC527Childs = classC527.getChildren();
					method__imul__528 = ModelTestUtils.getAssertMethod( classC527Childs, "__imul__", 2 );
					ModelTestUtils.assertParameterNames( method__imul__528, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:docdescriptor
		{
		IMethod methoddocdescriptor529;
			IModelElement[] moduleChilds = module.getChildren();
			methoddocdescriptor529 = ModelTestUtils.getAssertMethod( moduleChilds, "docdescriptor", 0 );
			//Class test
			{
			IType classDocDescr530;
				IModelElement[] methoddocdescriptor529Childs = methoddocdescriptor529.getChildren();
				classDocDescr530 = ModelTestUtils.getAssertClass( methoddocdescriptor529Childs, "DocDescr" );
				//Function test:__get__
				{
				IMethod method__get__531;
					IModelElement[] classDocDescr530Childs = classDocDescr530.getChildren();
					method__get__531 = ModelTestUtils.getAssertMethod( classDocDescr530Childs, "__get__", 3 );
					ModelTestUtils.assertParameterNames( method__get__531, new String[] {"self", "object", "otype"} );
				}
			}
			//Class test
			{
			IType classOldClass532;
				IModelElement[] methoddocdescriptor529Childs = methoddocdescriptor529.getChildren();
				classOldClass532 = ModelTestUtils.getAssertClass( methoddocdescriptor529Childs, "OldClass" );
			}
			//Class test
			{
			IType classNewClass533;
				IModelElement[] methoddocdescriptor529Childs = methoddocdescriptor529.getChildren();
				classNewClass533 = ModelTestUtils.getAssertClass( methoddocdescriptor529Childs, "NewClass" );
			}
		}
		//Function test:string_exceptions
		{
		IMethod methodstring_exceptions534;
			IModelElement[] moduleChilds = module.getChildren();
			methodstring_exceptions534 = ModelTestUtils.getAssertMethod( moduleChilds, "string_exceptions", 0 );
			//Class test
			{
			IType classMyStr535;
				IModelElement[] methodstring_exceptions534Childs = methodstring_exceptions534.getChildren();
				classMyStr535 = ModelTestUtils.getAssertClass( methodstring_exceptions534Childs, "MyStr" );
			}
		}
		//Function test:copy_setstate
		{
		IMethod methodcopy_setstate536;
			IModelElement[] moduleChilds = module.getChildren();
			methodcopy_setstate536 = ModelTestUtils.getAssertMethod( moduleChilds, "copy_setstate", 0 );
			//Class test
			{
			IType classC537;
				IModelElement[] methodcopy_setstate536Childs = methodcopy_setstate536.getChildren();
				classC537 = ModelTestUtils.getAssertClass( methodcopy_setstate536Childs, "C" );
				//Function test:__init__
				{
				IMethod method__init__538;
					IModelElement[] classC537Childs = classC537.getChildren();
					method__init__538 = ModelTestUtils.getAssertMethod( classC537Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__538, new String[] {"self", "foo"} );
				}
				//Function test:setfoo
				{
				IMethod methodsetfoo539;
					IModelElement[] classC537Childs = classC537.getChildren();
					methodsetfoo539 = ModelTestUtils.getAssertMethod( classC537Childs, "setfoo", 2 );
					ModelTestUtils.assertParameterNames( methodsetfoo539, new String[] {"self", "foo"} );
				}
				//Function test:getfoo
				{
				IMethod methodgetfoo540;
					IModelElement[] classC537Childs = classC537.getChildren();
					methodgetfoo540 = ModelTestUtils.getAssertMethod( classC537Childs, "getfoo", 1 );
					ModelTestUtils.assertParameterNames( methodgetfoo540, new String[] {"self"} );
				}
				//Function test:__getstate__
				{
				IMethod method__getstate__541;
					IModelElement[] classC537Childs = classC537.getChildren();
					method__getstate__541 = ModelTestUtils.getAssertMethod( classC537Childs, "__getstate__", 1 );
					ModelTestUtils.assertParameterNames( method__getstate__541, new String[] {"self"} );
				}
				//Function test:__setstate__
				{
				IMethod method__setstate__542;
					IModelElement[] classC537Childs = classC537.getChildren();
					method__setstate__542 = ModelTestUtils.getAssertMethod( classC537Childs, "__setstate__", 2 );
					ModelTestUtils.assertParameterNames( method__setstate__542, new String[] {"self", "lst"} );
				}
			}
		}
		//Function test:slices
		{
		IMethod methodslices543;
			IModelElement[] moduleChilds = module.getChildren();
			methodslices543 = ModelTestUtils.getAssertMethod( moduleChilds, "slices", 0 );
			//Class test
			{
			IType classS544;
				IModelElement[] methodslices543Childs = methodslices543.getChildren();
				classS544 = ModelTestUtils.getAssertClass( methodslices543Childs, "S" );
				//Function test:__getitem__
				{
				IMethod method__getitem__545;
					IModelElement[] classS544Childs = classS544.getChildren();
					method__getitem__545 = ModelTestUtils.getAssertMethod( classS544Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__545, new String[] {"self", "x"} );
				}
			}
			//Class test
			{
			IType classT546;
				IModelElement[] methodslices543Childs = methodslices543.getChildren();
				classT546 = ModelTestUtils.getAssertClass( methodslices543Childs, "T" );
				//Function test:__getitem__
				{
				IMethod method__getitem__547;
					IModelElement[] classT546Childs = classT546.getChildren();
					method__getitem__547 = ModelTestUtils.getAssertMethod( classT546Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__547, new String[] {"self", "x"} );
				}
			}
			//Class test
			{
			IType classL548;
				IModelElement[] methodslices543Childs = methodslices543.getChildren();
				classL548 = ModelTestUtils.getAssertClass( methodslices543Childs, "L" );
				//Function test:__getitem__
				{
				IMethod method__getitem__549;
					IModelElement[] classL548Childs = classL548.getChildren();
					method__getitem__549 = ModelTestUtils.getAssertMethod( classL548Childs, "__getitem__", 2 );
					ModelTestUtils.assertParameterNames( method__getitem__549, new String[] {"self", "x"} );
				}
			}
		}
		//Function test:subtype_resurrection
		{
		IMethod methodsubtype_resurrection550;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubtype_resurrection550 = ModelTestUtils.getAssertMethod( moduleChilds, "subtype_resurrection", 0 );
			//Class test
			{
			IType classC551;
				IModelElement[] methodsubtype_resurrection550Childs = methodsubtype_resurrection550.getChildren();
				classC551 = ModelTestUtils.getAssertClass( methodsubtype_resurrection550Childs, "C" );
				//Function test:__del__
				{
				IMethod method__del__552;
					IModelElement[] classC551Childs = classC551.getChildren();
					method__del__552 = ModelTestUtils.getAssertMethod( classC551Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__552, new String[] {"self"} );
				}
			}
		}
		//Function test:slottrash
		{
		IMethod methodslottrash553;
			IModelElement[] moduleChilds = module.getChildren();
			methodslottrash553 = ModelTestUtils.getAssertMethod( moduleChilds, "slottrash", 0 );
			//Class test
			{
			IType classtrash554;
				IModelElement[] methodslottrash553Childs = methodslottrash553.getChildren();
				classtrash554 = ModelTestUtils.getAssertClass( methodslottrash553Childs, "trash" );
				//Function test:__init__
				{
				IMethod method__init__555;
					IModelElement[] classtrash554Childs = classtrash554.getChildren();
					method__init__555 = ModelTestUtils.getAssertMethod( classtrash554Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__555, new String[] {"self", "x"} );
				}
			}
		}
		//Function test:slotmultipleinheritance
		{
		IMethod methodslotmultipleinheritance556;
			IModelElement[] moduleChilds = module.getChildren();
			methodslotmultipleinheritance556 = ModelTestUtils.getAssertMethod( moduleChilds, "slotmultipleinheritance", 0 );
			//Class test
			{
			IType classA557;
				IModelElement[] methodslotmultipleinheritance556Childs = methodslotmultipleinheritance556.getChildren();
				classA557 = ModelTestUtils.getAssertClass( methodslotmultipleinheritance556Childs, "A" );
			}
			//Class test
			{
			IType classB558;
				IModelElement[] methodslotmultipleinheritance556Childs = methodslotmultipleinheritance556.getChildren();
				classB558 = ModelTestUtils.getAssertClass( methodslotmultipleinheritance556Childs, "B" );
			}
			//Class test
			{
			IType classC559;
				IModelElement[] methodslotmultipleinheritance556Childs = methodslotmultipleinheritance556.getChildren();
				classC559 = ModelTestUtils.getAssertClass( methodslotmultipleinheritance556Childs, "C" );
			}
		}
		//Function test:testrmul
		{
		IMethod methodtestrmul560;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestrmul560 = ModelTestUtils.getAssertMethod( moduleChilds, "testrmul", 0 );
			//Class test
			{
			IType classC561;
				IModelElement[] methodtestrmul560Childs = methodtestrmul560.getChildren();
				classC561 = ModelTestUtils.getAssertClass( methodtestrmul560Childs, "C" );
				//Function test:__mul__
				{
				IMethod method__mul__562;
					IModelElement[] classC561Childs = classC561.getChildren();
					method__mul__562 = ModelTestUtils.getAssertMethod( classC561Childs, "__mul__", 2 );
					ModelTestUtils.assertParameterNames( method__mul__562, new String[] {"self", "other"} );
				}
				//Function test:__rmul__
				{
				IMethod method__rmul__563;
					IModelElement[] classC561Childs = classC561.getChildren();
					method__rmul__563 = ModelTestUtils.getAssertMethod( classC561Childs, "__rmul__", 2 );
					ModelTestUtils.assertParameterNames( method__rmul__563, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:testipow
		{
		IMethod methodtestipow564;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestipow564 = ModelTestUtils.getAssertMethod( moduleChilds, "testipow", 0 );
			//Class test
			{
			IType classC565;
				IModelElement[] methodtestipow564Childs = methodtestipow564.getChildren();
				classC565 = ModelTestUtils.getAssertClass( methodtestipow564Childs, "C" );
				//Function test:__ipow__
				{
				IMethod method__ipow__566;
					IModelElement[] classC565Childs = classC565.getChildren();
					method__ipow__566 = ModelTestUtils.getAssertMethod( classC565Childs, "__ipow__", 2 );
					ModelTestUtils.assertParameterNames( method__ipow__566, new String[] {"self", "other"} );
				}
			}
		}
		//Function test:do_this_first
		{
		IMethod methoddo_this_first567;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_this_first567 = ModelTestUtils.getAssertMethod( moduleChilds, "do_this_first", 0 );
			//Class test
			{
			IType classUserLong568;
				IModelElement[] methoddo_this_first567Childs = methoddo_this_first567.getChildren();
				classUserLong568 = ModelTestUtils.getAssertClass( methoddo_this_first567Childs, "UserLong" );
				//Function test:__pow__
				{
				IMethod method__pow__569;
					IModelElement[] classUserLong568Childs = classUserLong568.getChildren();
					method__pow__569 = ModelTestUtils.getAssertMethod( classUserLong568Childs, "__pow__", 2 );
					ModelTestUtils.assertParameterNames( method__pow__569, new String[] {"self", "args"} );
				}
			}
		}
		//Function test:test_mutable_bases
		{
		IMethod methodtest_mutable_bases570;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mutable_bases570 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mutable_bases", 0 );
			//Class test
			{
			IType classC571;
				IModelElement[] methodtest_mutable_bases570Childs = methodtest_mutable_bases570.getChildren();
				classC571 = ModelTestUtils.getAssertClass( methodtest_mutable_bases570Childs, "C" );
			}
			//Class test
			{
			IType classC2572;
				IModelElement[] methodtest_mutable_bases570Childs = methodtest_mutable_bases570.getChildren();
				classC2572 = ModelTestUtils.getAssertClass( methodtest_mutable_bases570Childs, "C2" );
				//Function test:__getattribute__
				{
				IMethod method__getattribute__573;
					IModelElement[] classC2572Childs = classC2572.getChildren();
					method__getattribute__573 = ModelTestUtils.getAssertMethod( classC2572Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__573, new String[] {"self", "attr"} );
				}
				//Function test:meth
				{
				IMethod methodmeth574;
					IModelElement[] classC2572Childs = classC2572.getChildren();
					methodmeth574 = ModelTestUtils.getAssertMethod( classC2572Childs, "meth", 1 );
					ModelTestUtils.assertParameterNames( methodmeth574, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classD575;
				IModelElement[] methodtest_mutable_bases570Childs = methodtest_mutable_bases570.getChildren();
				classD575 = ModelTestUtils.getAssertClass( methodtest_mutable_bases570Childs, "D" );
			}
			//Class test
			{
			IType classE576;
				IModelElement[] methodtest_mutable_bases570Childs = methodtest_mutable_bases570.getChildren();
				classE576 = ModelTestUtils.getAssertClass( methodtest_mutable_bases570Childs, "E" );
			}
			//Class test
			{
			IType classL577;
				IModelElement[] methodtest_mutable_bases570Childs = methodtest_mutable_bases570.getChildren();
				classL577 = ModelTestUtils.getAssertClass( methodtest_mutable_bases570Childs, "L" );
			}
			//Class test
			{
			IType classClassic578;
				IModelElement[] methodtest_mutable_bases570Childs = methodtest_mutable_bases570.getChildren();
				classClassic578 = ModelTestUtils.getAssertClass( methodtest_mutable_bases570Childs, "Classic" );
				//Function test:meth2
				{
				IMethod methodmeth2579;
					IModelElement[] classClassic578Childs = classClassic578.getChildren();
					methodmeth2579 = ModelTestUtils.getAssertMethod( classClassic578Childs, "meth2", 1 );
					ModelTestUtils.assertParameterNames( methodmeth2579, new String[] {"self"} );
				}
			}
		}
		//Function test:test_mutable_bases_with_failing_mro
		{
		IMethod methodtest_mutable_bases_with_failing_mro580;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mutable_bases_with_failing_mro580 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mutable_bases_with_failing_mro", 0 );
			//Class test
			{
			IType classWorkOnce581;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classWorkOnce581 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "WorkOnce" );
				//Function test:__new__
				{
				IMethod method__new__582;
					IModelElement[] classWorkOnce581Childs = classWorkOnce581.getChildren();
					method__new__582 = ModelTestUtils.getAssertMethod( classWorkOnce581Childs, "__new__", 4 );
					ModelTestUtils.assertParameterNames( method__new__582, new String[] {"self", "name", "bases", "ns"} );
				}
				//Function test:mro
				{
				IMethod methodmro583;
					IModelElement[] classWorkOnce581Childs = classWorkOnce581.getChildren();
					methodmro583 = ModelTestUtils.getAssertMethod( classWorkOnce581Childs, "mro", 1 );
					ModelTestUtils.assertParameterNames( methodmro583, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classWorkAlways584;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classWorkAlways584 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "WorkAlways" );
				//Function test:mro
				{
				IMethod methodmro585;
					IModelElement[] classWorkAlways584Childs = classWorkAlways584.getChildren();
					methodmro585 = ModelTestUtils.getAssertMethod( classWorkAlways584Childs, "mro", 1 );
					ModelTestUtils.assertParameterNames( methodmro585, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC586;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classC586 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "C" );
			}
			//Class test
			{
			IType classC2587;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classC2587 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "C2" );
			}
			//Class test
			{
			IType classD588;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classD588 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "D" );
			}
			//Class test
			{
			IType classE589;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classE589 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "E" );
			}
			//Class test
			{
			IType classF590;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classF590 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "F" );
			}
			//Class test
			{
			IType classG591;
				IModelElement[] methodtest_mutable_bases_with_failing_mro580Childs = methodtest_mutable_bases_with_failing_mro580.getChildren();
				classG591 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_with_failing_mro580Childs, "G" );
			}
		}
		//Function test:test_mutable_bases_catch_mro_conflict
		{
		IMethod methodtest_mutable_bases_catch_mro_conflict592;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mutable_bases_catch_mro_conflict592 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mutable_bases_catch_mro_conflict", 0 );
			//Class test
			{
			IType classA593;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict592Childs = methodtest_mutable_bases_catch_mro_conflict592.getChildren();
				classA593 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict592Childs, "A" );
			}
			//Class test
			{
			IType classB594;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict592Childs = methodtest_mutable_bases_catch_mro_conflict592.getChildren();
				classB594 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict592Childs, "B" );
			}
			//Class test
			{
			IType classC595;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict592Childs = methodtest_mutable_bases_catch_mro_conflict592.getChildren();
				classC595 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict592Childs, "C" );
			}
			//Class test
			{
			IType classD596;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict592Childs = methodtest_mutable_bases_catch_mro_conflict592.getChildren();
				classD596 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict592Childs, "D" );
			}
			//Class test
			{
			IType classE597;
				IModelElement[] methodtest_mutable_bases_catch_mro_conflict592Childs = methodtest_mutable_bases_catch_mro_conflict592.getChildren();
				classE597 = ModelTestUtils.getAssertClass( methodtest_mutable_bases_catch_mro_conflict592Childs, "E" );
			}
		}
		//Function test:mutable_names
		{
		IMethod methodmutable_names598;
			IModelElement[] moduleChilds = module.getChildren();
			methodmutable_names598 = ModelTestUtils.getAssertMethod( moduleChilds, "mutable_names", 0 );
			//Class test
			{
			IType classC599;
				IModelElement[] methodmutable_names598Childs = methodmutable_names598.getChildren();
				classC599 = ModelTestUtils.getAssertClass( methodmutable_names598Childs, "C" );
			}
		}
		//Function test:subclass_right_op
		{
		IMethod methodsubclass_right_op600;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubclass_right_op600 = ModelTestUtils.getAssertMethod( moduleChilds, "subclass_right_op", 0 );
			//Class test
			{
			IType classB601;
				IModelElement[] methodsubclass_right_op600Childs = methodsubclass_right_op600.getChildren();
				classB601 = ModelTestUtils.getAssertClass( methodsubclass_right_op600Childs, "B" );
				//Function test:__floordiv__
				{
				IMethod method__floordiv__602;
					IModelElement[] classB601Childs = classB601.getChildren();
					method__floordiv__602 = ModelTestUtils.getAssertMethod( classB601Childs, "__floordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__floordiv__602, new String[] {"self", "other"} );
				}
				//Function test:__rfloordiv__
				{
				IMethod method__rfloordiv__603;
					IModelElement[] classB601Childs = classB601.getChildren();
					method__rfloordiv__603 = ModelTestUtils.getAssertMethod( classB601Childs, "__rfloordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__rfloordiv__603, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classC604;
				IModelElement[] methodsubclass_right_op600Childs = methodsubclass_right_op600.getChildren();
				classC604 = ModelTestUtils.getAssertClass( methodsubclass_right_op600Childs, "C" );
				//Function test:__floordiv__
				{
				IMethod method__floordiv__605;
					IModelElement[] classC604Childs = classC604.getChildren();
					method__floordiv__605 = ModelTestUtils.getAssertMethod( classC604Childs, "__floordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__floordiv__605, new String[] {"self", "other"} );
				}
				//Function test:__rfloordiv__
				{
				IMethod method__rfloordiv__606;
					IModelElement[] classC604Childs = classC604.getChildren();
					method__rfloordiv__606 = ModelTestUtils.getAssertMethod( classC604Childs, "__rfloordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__rfloordiv__606, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classD607;
				IModelElement[] methodsubclass_right_op600Childs = methodsubclass_right_op600.getChildren();
				classD607 = ModelTestUtils.getAssertClass( methodsubclass_right_op600Childs, "D" );
				//Function test:__floordiv__
				{
				IMethod method__floordiv__608;
					IModelElement[] classD607Childs = classD607.getChildren();
					method__floordiv__608 = ModelTestUtils.getAssertMethod( classD607Childs, "__floordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__floordiv__608, new String[] {"self", "other"} );
				}
				//Function test:__rfloordiv__
				{
				IMethod method__rfloordiv__609;
					IModelElement[] classD607Childs = classD607.getChildren();
					method__rfloordiv__609 = ModelTestUtils.getAssertMethod( classD607Childs, "__rfloordiv__", 2 );
					ModelTestUtils.assertParameterNames( method__rfloordiv__609, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classE610;
				IModelElement[] methodsubclass_right_op600Childs = methodsubclass_right_op600.getChildren();
				classE610 = ModelTestUtils.getAssertClass( methodsubclass_right_op600Childs, "E" );
			}
		}
		//Function test:dict_type_with_metaclass
		{
		IMethod methoddict_type_with_metaclass611;
			IModelElement[] moduleChilds = module.getChildren();
			methoddict_type_with_metaclass611 = ModelTestUtils.getAssertMethod( moduleChilds, "dict_type_with_metaclass", 0 );
			//Class test
			{
			IType classB612;
				IModelElement[] methoddict_type_with_metaclass611Childs = methoddict_type_with_metaclass611.getChildren();
				classB612 = ModelTestUtils.getAssertClass( methoddict_type_with_metaclass611Childs, "B" );
			}
			//Class test
			{
			IType classM613;
				IModelElement[] methoddict_type_with_metaclass611Childs = methoddict_type_with_metaclass611.getChildren();
				classM613 = ModelTestUtils.getAssertClass( methoddict_type_with_metaclass611Childs, "M" );
			}
			//Class test
			{
			IType classC614;
				IModelElement[] methoddict_type_with_metaclass611Childs = methoddict_type_with_metaclass611.getChildren();
				classC614 = ModelTestUtils.getAssertClass( methoddict_type_with_metaclass611Childs, "C" );
			}
		}
		//Function test:meth_class_get
		{
		IMethod methodmeth_class_get615;
			IModelElement[] moduleChilds = module.getChildren();
			methodmeth_class_get615 = ModelTestUtils.getAssertMethod( moduleChilds, "meth_class_get", 0 );
		}
		//Function test:isinst_isclass
		{
		IMethod methodisinst_isclass616;
			IModelElement[] moduleChilds = module.getChildren();
			methodisinst_isclass616 = ModelTestUtils.getAssertMethod( moduleChilds, "isinst_isclass", 0 );
			//Class test
			{
			IType classProxy617;
				IModelElement[] methodisinst_isclass616Childs = methodisinst_isclass616.getChildren();
				classProxy617 = ModelTestUtils.getAssertClass( methodisinst_isclass616Childs, "Proxy" );
				//Function test:__init__
				{
				IMethod method__init__618;
					IModelElement[] classProxy617Childs = classProxy617.getChildren();
					method__init__618 = ModelTestUtils.getAssertMethod( classProxy617Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__618, new String[] {"self", "obj"} );
				}
				//Function test:__getattribute__
				{
				IMethod method__getattribute__619;
					IModelElement[] classProxy617Childs = classProxy617.getChildren();
					method__getattribute__619 = ModelTestUtils.getAssertMethod( classProxy617Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__619, new String[] {"self", "name"} );
				}
			}
			//Class test
			{
			IType classC620;
				IModelElement[] methodisinst_isclass616Childs = methodisinst_isclass616.getChildren();
				classC620 = ModelTestUtils.getAssertClass( methodisinst_isclass616Childs, "C" );
			}
			//Class test
			{
			IType classD621;
				IModelElement[] methodisinst_isclass616Childs = methodisinst_isclass616.getChildren();
				classD621 = ModelTestUtils.getAssertClass( methodisinst_isclass616Childs, "D" );
			}
			//Class test
			{
			IType classC622;
				IModelElement[] methodisinst_isclass616Childs = methodisinst_isclass616.getChildren();
				classC622 = ModelTestUtils.getAssertClass( methodisinst_isclass616Childs, "C" );
			}
			//Class test
			{
			IType classD623;
				IModelElement[] methodisinst_isclass616Childs = methodisinst_isclass616.getChildren();
				classD623 = ModelTestUtils.getAssertClass( methodisinst_isclass616Childs, "D" );
			}
		}
		//Function test:proxysuper
		{
		IMethod methodproxysuper624;
			IModelElement[] moduleChilds = module.getChildren();
			methodproxysuper624 = ModelTestUtils.getAssertMethod( moduleChilds, "proxysuper", 0 );
			//Class test
			{
			IType classProxy625;
				IModelElement[] methodproxysuper624Childs = methodproxysuper624.getChildren();
				classProxy625 = ModelTestUtils.getAssertClass( methodproxysuper624Childs, "Proxy" );
				//Function test:__init__
				{
				IMethod method__init__626;
					IModelElement[] classProxy625Childs = classProxy625.getChildren();
					method__init__626 = ModelTestUtils.getAssertMethod( classProxy625Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__626, new String[] {"self", "obj"} );
				}
				//Function test:__getattribute__
				{
				IMethod method__getattribute__627;
					IModelElement[] classProxy625Childs = classProxy625.getChildren();
					method__getattribute__627 = ModelTestUtils.getAssertMethod( classProxy625Childs, "__getattribute__", 2 );
					ModelTestUtils.assertParameterNames( method__getattribute__627, new String[] {"self", "name"} );
				}
			}
			//Class test
			{
			IType classB628;
				IModelElement[] methodproxysuper624Childs = methodproxysuper624.getChildren();
				classB628 = ModelTestUtils.getAssertClass( methodproxysuper624Childs, "B" );
				//Function test:f
				{
				IMethod methodf629;
					IModelElement[] classB628Childs = classB628.getChildren();
					methodf629 = ModelTestUtils.getAssertMethod( classB628Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf629, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classC630;
				IModelElement[] methodproxysuper624Childs = methodproxysuper624.getChildren();
				classC630 = ModelTestUtils.getAssertClass( methodproxysuper624Childs, "C" );
				//Function test:f
				{
				IMethod methodf631;
					IModelElement[] classC630Childs = classC630.getChildren();
					methodf631 = ModelTestUtils.getAssertMethod( classC630Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf631, new String[] {"self"} );
				}
			}
		}
		//Function test:carloverre
		{
		IMethod methodcarloverre632;
			IModelElement[] moduleChilds = module.getChildren();
			methodcarloverre632 = ModelTestUtils.getAssertMethod( moduleChilds, "carloverre", 0 );
		}
		//Function test:weakref_segfault
		{
		IMethod methodweakref_segfault633;
			IModelElement[] moduleChilds = module.getChildren();
			methodweakref_segfault633 = ModelTestUtils.getAssertMethod( moduleChilds, "weakref_segfault", 0 );
			//Class test
			{
			IType classProvoker634;
				IModelElement[] methodweakref_segfault633Childs = methodweakref_segfault633.getChildren();
				classProvoker634 = ModelTestUtils.getAssertClass( methodweakref_segfault633Childs, "Provoker" );
				//Function test:__init__
				{
				IMethod method__init__635;
					IModelElement[] classProvoker634Childs = classProvoker634.getChildren();
					method__init__635 = ModelTestUtils.getAssertMethod( classProvoker634Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__635, new String[] {"self", "referrent"} );
				}
				//Function test:__del__
				{
				IMethod method__del__636;
					IModelElement[] classProvoker634Childs = classProvoker634.getChildren();
					method__del__636 = ModelTestUtils.getAssertMethod( classProvoker634Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__636, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classOops637;
				IModelElement[] methodweakref_segfault633Childs = methodweakref_segfault633.getChildren();
				classOops637 = ModelTestUtils.getAssertClass( methodweakref_segfault633Childs, "Oops" );
			}
		}
		//Function test:filefault
		{
		IMethod methodfilefault638;
			IModelElement[] moduleChilds = module.getChildren();
			methodfilefault638 = ModelTestUtils.getAssertMethod( moduleChilds, "filefault", 0 );
			//Class test
			{
			IType classStdoutGuard639;
				IModelElement[] methodfilefault638Childs = methodfilefault638.getChildren();
				classStdoutGuard639 = ModelTestUtils.getAssertClass( methodfilefault638Childs, "StdoutGuard" );
				//Function test:__getattr__
				{
				IMethod method__getattr__640;
					IModelElement[] classStdoutGuard639Childs = classStdoutGuard639.getChildren();
					method__getattr__640 = ModelTestUtils.getAssertMethod( classStdoutGuard639Childs, "__getattr__", 2 );
					ModelTestUtils.assertParameterNames( method__getattr__640, new String[] {"self", "attr"} );
				}
			}
		}
		//Function test:vicious_descriptor_nonsense
		{
		IMethod methodvicious_descriptor_nonsense641;
			IModelElement[] moduleChilds = module.getChildren();
			methodvicious_descriptor_nonsense641 = ModelTestUtils.getAssertMethod( moduleChilds, "vicious_descriptor_nonsense", 0 );
			//Class test
			{
			IType classEvil642;
				IModelElement[] methodvicious_descriptor_nonsense641Childs = methodvicious_descriptor_nonsense641.getChildren();
				classEvil642 = ModelTestUtils.getAssertClass( methodvicious_descriptor_nonsense641Childs, "Evil" );
				//Function test:__hash__
				{
				IMethod method__hash__643;
					IModelElement[] classEvil642Childs = classEvil642.getChildren();
					method__hash__643 = ModelTestUtils.getAssertMethod( classEvil642Childs, "__hash__", 1 );
					ModelTestUtils.assertParameterNames( method__hash__643, new String[] {"self"} );
				}
				//Function test:__eq__
				{
				IMethod method__eq__644;
					IModelElement[] classEvil642Childs = classEvil642.getChildren();
					method__eq__644 = ModelTestUtils.getAssertMethod( classEvil642Childs, "__eq__", 2 );
					ModelTestUtils.assertParameterNames( method__eq__644, new String[] {"self", "other"} );
				}
			}
			//Class test
			{
			IType classDescr645;
				IModelElement[] methodvicious_descriptor_nonsense641Childs = methodvicious_descriptor_nonsense641.getChildren();
				classDescr645 = ModelTestUtils.getAssertClass( methodvicious_descriptor_nonsense641Childs, "Descr" );
				//Function test:__get__
				{
				IMethod method__get__646;
					IModelElement[] classDescr645Childs = classDescr645.getChildren();
					method__get__646 = ModelTestUtils.getAssertMethod( classDescr645Childs, "__get__", 3 );
					ModelTestUtils.assertParameterNames( method__get__646, new String[] {"self", "ob", "type"} );
				}
			}
			//Class test
			{
			IType classC647;
				IModelElement[] methodvicious_descriptor_nonsense641Childs = methodvicious_descriptor_nonsense641.getChildren();
				classC647 = ModelTestUtils.getAssertClass( methodvicious_descriptor_nonsense641Childs, "C" );
			}
		}
		//Function test:test_init
		{
		IMethod methodtest_init648;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_init648 = ModelTestUtils.getAssertMethod( moduleChilds, "test_init", 0 );
			//Class test
			{
			IType classFoo649;
				IModelElement[] methodtest_init648Childs = methodtest_init648.getChildren();
				classFoo649 = ModelTestUtils.getAssertClass( methodtest_init648Childs, "Foo" );
				//Function test:__init__
				{
				IMethod method__init__650;
					IModelElement[] classFoo649Childs = classFoo649.getChildren();
					method__init__650 = ModelTestUtils.getAssertMethod( classFoo649Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__650, new String[] {"self"} );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main651;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main651 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen232( ) throws Exception {
		String prj = "pytests_4";
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
			//Function test:runTest
			{
			IMethod methodrunTest2;
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				methodrunTest2 = ModelTestUtils.getAssertMethod( classRobotTestCase0Childs, "runTest", 1 );
				ModelTestUtils.assertParameterNames( methodrunTest2, new String[] {"self"} );
			}
			//Function test:__str__
			{
			IMethod method__str__3;
				IModelElement[] classRobotTestCase0Childs = classRobotTestCase0.getChildren();
				method__str__3 = ModelTestUtils.getAssertMethod( classRobotTestCase0Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__3, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		//Function test:RobotTest
		{
		IMethod methodRobotTest4;
			IModelElement[] moduleChilds = module.getChildren();
			methodRobotTest4 = ModelTestUtils.getAssertMethod( moduleChilds, "RobotTest", 5 );
			ModelTestUtils.assertParameterNames( methodRobotTest4, new String[] {"index", "robots_txt", "good_urls", "bad_urls", "agent"} );
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
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen233( ) throws Exception {
		String prj = "pytests_4";
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
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classNetrcTestCase0Childs = classNetrcTestCase0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classNetrcTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_case_1
			{
			IMethod methodtest_case_13;
				IModelElement[] classNetrcTestCase0Childs = classNetrcTestCase0.getChildren();
				methodtest_case_13 = ModelTestUtils.getAssertMethod( classNetrcTestCase0Childs, "test_case_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_13, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen234( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
			//Function test:run
			{
			IMethod methodrun2;
				IModelElement[] class_TriggerThread0Childs = class_TriggerThread0.getChildren();
				methodrun2 = ModelTestUtils.getAssertMethod( class_TriggerThread0Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun2, new String[] {"self"} );
			}
		}
		//Function test:_doBlockingTest
		{
		IMethod method_doBlockingTest3;
			IModelElement[] moduleChilds = module.getChildren();
			method_doBlockingTest3 = ModelTestUtils.getAssertMethod( moduleChilds, "_doBlockingTest", 4 );
			ModelTestUtils.assertParameterNames( method_doBlockingTest3, new String[] {"block_func", "block_args", "trigger_func", "trigger_args"} );
		}
		//Function test:_doExceptionalBlockingTest
		{
		IMethod method_doExceptionalBlockingTest4;
			IModelElement[] moduleChilds = module.getChildren();
			method_doExceptionalBlockingTest4 = ModelTestUtils.getAssertMethod( moduleChilds, "_doExceptionalBlockingTest", 5 );
			ModelTestUtils.assertParameterNames( method_doExceptionalBlockingTest4, new String[] {"block_func", "block_args", "trigger_func", "trigger_args", "expected_exception_class"} );
		}
		//Class test
		{
		IType classFailingQueueException5;
			IModelElement[] moduleChilds = module.getChildren();
			classFailingQueueException5 = ModelTestUtils.getAssertClass( moduleChilds, "FailingQueueException" );
		}
		//Class test
		{
		IType classFailingQueue6;
			IModelElement[] moduleChilds = module.getChildren();
			classFailingQueue6 = ModelTestUtils.getAssertClass( moduleChilds, "FailingQueue" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classFailingQueue6Childs = classFailingQueue6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classFailingQueue6Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "args"} );
			}
			//Function test:_put
			{
			IMethod method_put8;
				IModelElement[] classFailingQueue6Childs = classFailingQueue6.getChildren();
				method_put8 = ModelTestUtils.getAssertMethod( classFailingQueue6Childs, "_put", 2 );
				ModelTestUtils.assertParameterNames( method_put8, new String[] {"self", "item"} );
			}
			//Function test:_get
			{
			IMethod method_get9;
				IModelElement[] classFailingQueue6Childs = classFailingQueue6.getChildren();
				method_get9 = ModelTestUtils.getAssertMethod( classFailingQueue6Childs, "_get", 1 );
				ModelTestUtils.assertParameterNames( method_get9, new String[] {"self"} );
			}
		}
		//Function test:FailingQueueTest
		{
		IMethod methodFailingQueueTest10;
			IModelElement[] moduleChilds = module.getChildren();
			methodFailingQueueTest10 = ModelTestUtils.getAssertMethod( moduleChilds, "FailingQueueTest", 1 );
			ModelTestUtils.assertParameterNames( methodFailingQueueTest10, new String[] {"q"} );
		}
		//Function test:SimpleQueueTest
		{
		IMethod methodSimpleQueueTest11;
			IModelElement[] moduleChilds = module.getChildren();
			methodSimpleQueueTest11 = ModelTestUtils.getAssertMethod( moduleChilds, "SimpleQueueTest", 1 );
			ModelTestUtils.assertParameterNames( methodSimpleQueueTest11, new String[] {"q"} );
		}
		//Function test:test
		{
		IMethod methodtest12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest12 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen243( ) throws Exception {
		String prj = "pytests_4";
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
			//Function test:serve_until_stopped
			{
			IMethod methodserve_until_stopped6;
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				methodserve_until_stopped6 = ModelTestUtils.getAssertMethod( classLogRecordSocketReceiver4Childs, "serve_until_stopped", 1 );
				ModelTestUtils.assertParameterNames( methodserve_until_stopped6, new String[] {"self"} );
			}
			//Function test:process_request
			{
			IMethod methodprocess_request7;
				IModelElement[] classLogRecordSocketReceiver4Childs = classLogRecordSocketReceiver4.getChildren();
				methodprocess_request7 = ModelTestUtils.getAssertMethod( classLogRecordSocketReceiver4Childs, "process_request", 3 );
				ModelTestUtils.assertParameterNames( methodprocess_request7, new String[] {"self", "request", "client_address"} );
			}
		}
		//Function test:runTCP
		{
		IMethod methodrunTCP8;
			IModelElement[] moduleChilds = module.getChildren();
			methodrunTCP8 = ModelTestUtils.getAssertMethod( moduleChilds, "runTCP", 1 );
			ModelTestUtils.assertParameterNames( methodrunTCP8, new String[] {"tcpserver"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msgcount");
		}
		//Function test:nextmessage
		{
		IMethod methodnextmessage9;
			IModelElement[] moduleChilds = module.getChildren();
			methodnextmessage9 = ModelTestUtils.getAssertMethod( moduleChilds, "nextmessage", 0 );
		}
		//Function test:test0
		{
		IMethod methodtest010;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest010 = ModelTestUtils.getAssertMethod( moduleChilds, "test0", 0 );
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
		IType classSpecificLevelFilter11;
			IModelElement[] moduleChilds = module.getChildren();
			classSpecificLevelFilter11 = ModelTestUtils.getAssertClass( moduleChilds, "SpecificLevelFilter" );
			//Function test:__init__
			{
			IMethod method__init__12;
				IModelElement[] classSpecificLevelFilter11Childs = classSpecificLevelFilter11.getChildren();
				method__init__12 = ModelTestUtils.getAssertMethod( classSpecificLevelFilter11Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self", "lvl"} );
			}
			//Function test:filter
			{
			IMethod methodfilter13;
				IModelElement[] classSpecificLevelFilter11Childs = classSpecificLevelFilter11.getChildren();
				methodfilter13 = ModelTestUtils.getAssertMethod( classSpecificLevelFilter11Childs, "filter", 2 );
				ModelTestUtils.assertParameterNames( methodfilter13, new String[] {"self", "record"} );
			}
		}
		//Class test
		{
		IType classGarrulousFilter14;
			IModelElement[] moduleChilds = module.getChildren();
			classGarrulousFilter14 = ModelTestUtils.getAssertClass( moduleChilds, "GarrulousFilter" );
			//Function test:__init__
			{
			IMethod method__init__15;
				IModelElement[] classGarrulousFilter14Childs = classGarrulousFilter14.getChildren();
				method__init__15 = ModelTestUtils.getAssertMethod( classGarrulousFilter14Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__15, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classVerySpecificFilter16;
			IModelElement[] moduleChilds = module.getChildren();
			classVerySpecificFilter16 = ModelTestUtils.getAssertClass( moduleChilds, "VerySpecificFilter" );
			//Function test:filter
			{
			IMethod methodfilter17;
				IModelElement[] classVerySpecificFilter16Childs = classVerySpecificFilter16.getChildren();
				methodfilter17 = ModelTestUtils.getAssertMethod( classVerySpecificFilter16Childs, "filter", 2 );
				ModelTestUtils.assertParameterNames( methodfilter17, new String[] {"self", "record"} );
			}
		}
		//Function test:message
		{
		IMethod methodmessage18;
			IModelElement[] moduleChilds = module.getChildren();
			methodmessage18 = ModelTestUtils.getAssertMethod( moduleChilds, "message", 1 );
			ModelTestUtils.assertParameterNames( methodmessage18, new String[] {"s"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SHOULD1");
		}
		//Function test:test1
		{
		IMethod methodtest119;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest119 = ModelTestUtils.getAssertMethod( moduleChilds, "test1", 0 );
			//Function test:doLog
			{
			IMethod methoddoLog20;
				IModelElement[] methodtest119Childs = methodtest119.getChildren();
				methoddoLog20 = ModelTestUtils.getAssertMethod( methodtest119Childs, "doLog", 1 );
				ModelTestUtils.assertParameterNames( methoddoLog20, new String[] {"log"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MSG");
		}
		//Function test:test2
		{
		IMethod methodtest221;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest221 = ModelTestUtils.getAssertMethod( moduleChilds, "test2", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FILTER");
		}
		//Function test:doLog3
		{
		IMethod methoddoLog322;
			IModelElement[] moduleChilds = module.getChildren();
			methoddoLog322 = ModelTestUtils.getAssertMethod( moduleChilds, "doLog3", 0 );
		}
		//Function test:test3
		{
		IMethod methodtest323;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest323 = ModelTestUtils.getAssertMethod( moduleChilds, "test3", 0 );
		}
		//Function test:banner
		{
		IMethod methodbanner24;
			IModelElement[] moduleChilds = module.getChildren();
			methodbanner24 = ModelTestUtils.getAssertMethod( moduleChilds, "banner", 2 );
			ModelTestUtils.assertParameterNames( methodbanner24, new String[] {"nm", "typ"} );
		}
		//Function test:test_main_inner
		{
		IMethod methodtest_main_inner25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main_inner25 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main_inner", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main26;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main26 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen244( ) throws Exception {
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
			//Function test:copy
			{
			IMethod methodcopy2;
				IModelElement[] classRecord0Childs = classRecord0.getChildren();
				methodcopy2 = ModelTestUtils.getAssertMethod( classRecord0Childs, "copy", 1 );
				ModelTestUtils.assertParameterNames( methodcopy2, new String[] {"self"} );
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
		IMethod methodmain3;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain3 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 1 );
			ModelTestUtils.assertParameterNames( methodmain3, new String[] {"loops"} );
		}
		//Function test:pystones
		{
		IMethod methodpystones4;
			IModelElement[] moduleChilds = module.getChildren();
			methodpystones4 = ModelTestUtils.getAssertMethod( moduleChilds, "pystones", 1 );
			ModelTestUtils.assertParameterNames( methodpystones4, new String[] {"loops"} );
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
		IMethod methodProc05;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc05 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc0", 1 );
			ModelTestUtils.assertParameterNames( methodProc05, new String[] {"loops"} );
		}
		//Function test:Proc1
		{
		IMethod methodProc16;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc16 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc1", 1 );
			ModelTestUtils.assertParameterNames( methodProc16, new String[] {"PtrParIn"} );
		}
		//Function test:Proc2
		{
		IMethod methodProc27;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc27 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc2", 1 );
			ModelTestUtils.assertParameterNames( methodProc27, new String[] {"IntParIO"} );
		}
		//Function test:Proc3
		{
		IMethod methodProc38;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc38 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc3", 1 );
			ModelTestUtils.assertParameterNames( methodProc38, new String[] {"PtrParOut"} );
		}
		//Function test:Proc4
		{
		IMethod methodProc49;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc49 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc4", 0 );
		}
		//Function test:Proc5
		{
		IMethod methodProc510;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc510 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc5", 0 );
		}
		//Function test:Proc6
		{
		IMethod methodProc611;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc611 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc6", 1 );
			ModelTestUtils.assertParameterNames( methodProc611, new String[] {"EnumParIn"} );
		}
		//Function test:Proc7
		{
		IMethod methodProc712;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc712 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc7", 2 );
			ModelTestUtils.assertParameterNames( methodProc712, new String[] {"IntParI1", "IntParI2"} );
		}
		//Function test:Proc8
		{
		IMethod methodProc813;
			IModelElement[] moduleChilds = module.getChildren();
			methodProc813 = ModelTestUtils.getAssertMethod( moduleChilds, "Proc8", 4 );
			ModelTestUtils.assertParameterNames( methodProc813, new String[] {"Array1Par", "Array2Par", "IntParI1", "IntParI2"} );
		}
		//Function test:Func1
		{
		IMethod methodFunc114;
			IModelElement[] moduleChilds = module.getChildren();
			methodFunc114 = ModelTestUtils.getAssertMethod( moduleChilds, "Func1", 2 );
			ModelTestUtils.assertParameterNames( methodFunc114, new String[] {"CharPar1", "CharPar2"} );
		}
		//Function test:Func2
		{
		IMethod methodFunc215;
			IModelElement[] moduleChilds = module.getChildren();
			methodFunc215 = ModelTestUtils.getAssertMethod( moduleChilds, "Func2", 2 );
			ModelTestUtils.assertParameterNames( methodFunc215, new String[] {"StrParI1", "StrParI2"} );
		}
		//Function test:Func3
		{
		IMethod methodFunc316;
			IModelElement[] moduleChilds = module.getChildren();
			methodFunc316 = ModelTestUtils.getAssertMethod( moduleChilds, "Func3", 1 );
			ModelTestUtils.assertParameterNames( methodFunc316, new String[] {"EnumParIn"} );
		}
		//Function test:error
		{
		IMethod methoderror17;
			IModelElement[] moduleChilds = module.getChildren();
			methoderror17 = ModelTestUtils.getAssertMethod( moduleChilds, "error", 1 );
			ModelTestUtils.assertParameterNames( methoderror17, new String[] {"msg"} );
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
		String prj = "pytests_4";
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
	