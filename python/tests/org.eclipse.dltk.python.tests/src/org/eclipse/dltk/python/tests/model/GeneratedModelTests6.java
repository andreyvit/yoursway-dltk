
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

public class GeneratedModelTests6 extends AbstractModelTests
{
	public GeneratedModelTests6(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests6.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_6", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests6" );
	}
	public void testModelGen300( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_mimetypes.py"));

		assertNotNull("Module test_mimetypes.py not found", module);
		assertEquals("test_mimetypes.py", module.getElementName());
		
		//Class test
		{
		IType classMimeTypesTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classMimeTypesTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "MimeTypesTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			{
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMimeTypesTestCase0Childs, "db");
			}
			//Function test:test_default_data
			{
			IMethod methodtest_default_data3;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_default_data3 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_default_data", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_data3, new String[] {"self"} );
			}
			//Function test:test_data_urls
			{
			IMethod methodtest_data_urls4;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_data_urls4 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_data_urls", 1 );
				ModelTestUtils.assertParameterNames( methodtest_data_urls4, new String[] {"self"} );
			}
			//Function test:test_file_parsing
			{
			IMethod methodtest_file_parsing5;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_file_parsing5 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_file_parsing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file_parsing5, new String[] {"self"} );
			}
			//Function test:test_non_standard_types
			{
			IMethod methodtest_non_standard_types6;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_non_standard_types6 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_non_standard_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_standard_types6, new String[] {"self"} );
			}
			//Function test:test_guess_all_types
			{
			IMethod methodtest_guess_all_types7;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_guess_all_types7 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_guess_all_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_guess_all_types7, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen301( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_inspect.py"));

		assertNotNull("Module test_inspect.py not found", module);
		assertEquals("test_inspect.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "source");
		}
		//Function test:test
		{
		IMethod methodtest0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest0 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 3 );
			ModelTestUtils.assertParameterNames( methodtest0, new String[] {"assertion", "message", "args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "file");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mod");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "files_to_clean_up");
		}
		//Function test:istest
		{
		IMethod methodistest1;
			IModelElement[] moduleChilds = module.getChildren();
			methodistest1 = ModelTestUtils.getAssertMethod( moduleChilds, "istest", 2 );
			ModelTestUtils.assertParameterNames( methodistest1, new String[] {"func", "exp"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "git");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tb");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "classes");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tree");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "functions");
		}
		//Function test:sourcerange
		{
		IMethod methodsourcerange2;
			IModelElement[] moduleChilds = module.getChildren();
			methodsourcerange2 = ModelTestUtils.getAssertMethod( moduleChilds, "sourcerange", 2 );
			ModelTestUtils.assertParameterNames( methodsourcerange2, new String[] {"top", "bottom"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "source");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "file");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "files_to_clean_up");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mod2");
		}
		//Class test
		{
		IType classA3;
			IModelElement[] moduleChilds = module.getChildren();
			classA3 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
		}
		//Class test
		{
		IType classB4;
			IModelElement[] moduleChilds = module.getChildren();
			classB4 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
		}
		//Class test
		{
		IType classC5;
			IModelElement[] moduleChilds = module.getChildren();
			classC5 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
		}
		//Class test
		{
		IType classD6;
			IModelElement[] moduleChilds = module.getChildren();
			classD6 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "expected");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "got");
		}
		//Class test
		{
		IType classA7;
			IModelElement[] moduleChilds = module.getChildren();
			classA7 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
		}
		//Class test
		{
		IType classB8;
			IModelElement[] moduleChilds = module.getChildren();
			classB8 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
		}
		//Class test
		{
		IType classC9;
			IModelElement[] moduleChilds = module.getChildren();
			classC9 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
		}
		//Class test
		{
		IType classD10;
			IModelElement[] moduleChilds = module.getChildren();
			classD10 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "expected");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "got");
		}
		//Function test:attrs_wo_objs
		{
		IMethod methodattrs_wo_objs11;
			IModelElement[] moduleChilds = module.getChildren();
			methodattrs_wo_objs11 = ModelTestUtils.getAssertMethod( moduleChilds, "attrs_wo_objs", 1 );
			ModelTestUtils.assertParameterNames( methodattrs_wo_objs11, new String[] {"cls"} );
		}
		//Class test
		{
		IType classA12;
			IModelElement[] moduleChilds = module.getChildren();
			classA12 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
			//Function test:s
			{
			IMethod methods13;
				IModelElement[] classA12Childs = classA12.getChildren();
				methods13 = ModelTestUtils.getAssertMethod( classA12Childs, "s", 0 );
			}
			{
				IModelElement[] classA12Childs = classA12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA12Childs, "s");
			}
			//Function test:c
			{
			IMethod methodc14;
				IModelElement[] classA12Childs = classA12.getChildren();
				methodc14 = ModelTestUtils.getAssertMethod( classA12Childs, "c", 1 );
				ModelTestUtils.assertParameterNames( methodc14, new String[] {"cls"} );
			}
			{
				IModelElement[] classA12Childs = classA12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA12Childs, "c");
			}
			//Function test:getp
			{
			IMethod methodgetp15;
				IModelElement[] classA12Childs = classA12.getChildren();
				methodgetp15 = ModelTestUtils.getAssertMethod( classA12Childs, "getp", 1 );
				ModelTestUtils.assertParameterNames( methodgetp15, new String[] {"self"} );
			}
			{
				IModelElement[] classA12Childs = classA12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA12Childs, "p");
			}
			//Function test:m
			{
			IMethod methodm16;
				IModelElement[] classA12Childs = classA12.getChildren();
				methodm16 = ModelTestUtils.getAssertMethod( classA12Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm16, new String[] {"self"} );
			}
			//Function test:m1
			{
			IMethod methodm117;
				IModelElement[] classA12Childs = classA12.getChildren();
				methodm117 = ModelTestUtils.getAssertMethod( classA12Childs, "m1", 1 );
				ModelTestUtils.assertParameterNames( methodm117, new String[] {"self"} );
			}
			{
				IModelElement[] classA12Childs = classA12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA12Childs, "datablob");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classB18;
			IModelElement[] moduleChilds = module.getChildren();
			classB18 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
			//Function test:m
			{
			IMethod methodm19;
				IModelElement[] classB18Childs = classB18.getChildren();
				methodm19 = ModelTestUtils.getAssertMethod( classB18Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm19, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classC20;
			IModelElement[] moduleChilds = module.getChildren();
			classC20 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:m
			{
			IMethod methodm21;
				IModelElement[] classC20Childs = classC20.getChildren();
				methodm21 = ModelTestUtils.getAssertMethod( classC20Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm21, new String[] {"self"} );
			}
			//Function test:c
			{
			IMethod methodc22;
				IModelElement[] classC20Childs = classC20.getChildren();
				methodc22 = ModelTestUtils.getAssertMethod( classC20Childs, "c", 1 );
				ModelTestUtils.assertParameterNames( methodc22, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classD23;
			IModelElement[] moduleChilds = module.getChildren();
			classD23 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
			//Function test:m1
			{
			IMethod methodm124;
				IModelElement[] classD23Childs = classD23.getChildren();
				methodm124 = ModelTestUtils.getAssertMethod( classD23Childs, "m1", 1 );
				ModelTestUtils.assertParameterNames( methodm124, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classA25;
			IModelElement[] moduleChilds = module.getChildren();
			classA25 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
			//Function test:s
			{
			IMethod methods26;
				IModelElement[] classA25Childs = classA25.getChildren();
				methods26 = ModelTestUtils.getAssertMethod( classA25Childs, "s", 0 );
			}
			{
				IModelElement[] classA25Childs = classA25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA25Childs, "s");
			}
			//Function test:c
			{
			IMethod methodc27;
				IModelElement[] classA25Childs = classA25.getChildren();
				methodc27 = ModelTestUtils.getAssertMethod( classA25Childs, "c", 1 );
				ModelTestUtils.assertParameterNames( methodc27, new String[] {"cls"} );
			}
			{
				IModelElement[] classA25Childs = classA25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA25Childs, "c");
			}
			//Function test:getp
			{
			IMethod methodgetp28;
				IModelElement[] classA25Childs = classA25.getChildren();
				methodgetp28 = ModelTestUtils.getAssertMethod( classA25Childs, "getp", 1 );
				ModelTestUtils.assertParameterNames( methodgetp28, new String[] {"self"} );
			}
			{
				IModelElement[] classA25Childs = classA25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA25Childs, "p");
			}
			//Function test:m
			{
			IMethod methodm29;
				IModelElement[] classA25Childs = classA25.getChildren();
				methodm29 = ModelTestUtils.getAssertMethod( classA25Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm29, new String[] {"self"} );
			}
			//Function test:m1
			{
			IMethod methodm130;
				IModelElement[] classA25Childs = classA25.getChildren();
				methodm130 = ModelTestUtils.getAssertMethod( classA25Childs, "m1", 1 );
				ModelTestUtils.assertParameterNames( methodm130, new String[] {"self"} );
			}
			{
				IModelElement[] classA25Childs = classA25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA25Childs, "datablob");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classB31;
			IModelElement[] moduleChilds = module.getChildren();
			classB31 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
			//Function test:m
			{
			IMethod methodm32;
				IModelElement[] classB31Childs = classB31.getChildren();
				methodm32 = ModelTestUtils.getAssertMethod( classB31Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm32, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classC33;
			IModelElement[] moduleChilds = module.getChildren();
			classC33 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:m
			{
			IMethod methodm34;
				IModelElement[] classC33Childs = classC33.getChildren();
				methodm34 = ModelTestUtils.getAssertMethod( classC33Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm34, new String[] {"self"} );
			}
			//Function test:c
			{
			IMethod methodc35;
				IModelElement[] classC33Childs = classC33.getChildren();
				methodc35 = ModelTestUtils.getAssertMethod( classC33Childs, "c", 1 );
				ModelTestUtils.assertParameterNames( methodc35, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		//Class test
		{
		IType classD36;
			IModelElement[] moduleChilds = module.getChildren();
			classD36 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
			//Function test:m1
			{
			IMethod methodm137;
				IModelElement[] classD36Childs = classD36.getChildren();
				methodm137 = ModelTestUtils.getAssertMethod( classD36Childs, "m1", 1 );
				ModelTestUtils.assertParameterNames( methodm137, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "attrs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "count");
		}
		//Function test:sublistOfOne
		{
		IMethod methodsublistOfOne38;
			IModelElement[] moduleChilds = module.getChildren();
			methodsublistOfOne38 = ModelTestUtils.getAssertMethod( moduleChilds, "sublistOfOne", 1 );
			ModelTestUtils.assertParameterNames( methodsublistOfOne38, new String[] {"foo"} );
		}

	}
	public void testModelGen302( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_support.py"));

		assertNotNull("Module test_support.py not found", module);
		assertEquals("test_support.py", module.getElementName());
		
		//Class test
		{
		IType classError0;
			IModelElement[] moduleChilds = module.getChildren();
			classError0 = ModelTestUtils.getAssertClass( moduleChilds, "Error" );
		}
		//Class test
		{
		IType classTestFailed1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestFailed1 = ModelTestUtils.getAssertClass( moduleChilds, "TestFailed" );
		}
		//Class test
		{
		IType classTestSkipped2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSkipped2 = ModelTestUtils.getAssertClass( moduleChilds, "TestSkipped" );
		}
		//Class test
		{
		IType classResourceDenied3;
			IModelElement[] moduleChilds = module.getChildren();
			classResourceDenied3 = ModelTestUtils.getAssertClass( moduleChilds, "ResourceDenied" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "verbose");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "use_resources");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_original_stdout");
		}
		//Function test:record_original_stdout
		{
		IMethod methodrecord_original_stdout4;
			IModelElement[] moduleChilds = module.getChildren();
			methodrecord_original_stdout4 = ModelTestUtils.getAssertMethod( moduleChilds, "record_original_stdout", 1 );
			ModelTestUtils.assertParameterNames( methodrecord_original_stdout4, new String[] {"stdout"} );
		}
		//Function test:get_original_stdout
		{
		IMethod methodget_original_stdout5;
			IModelElement[] moduleChilds = module.getChildren();
			methodget_original_stdout5 = ModelTestUtils.getAssertMethod( moduleChilds, "get_original_stdout", 0 );
		}
		//Function test:unload
		{
		IMethod methodunload6;
			IModelElement[] moduleChilds = module.getChildren();
			methodunload6 = ModelTestUtils.getAssertMethod( moduleChilds, "unload", 1 );
			ModelTestUtils.assertParameterNames( methodunload6, new String[] {"name"} );
		}
		//Function test:forget
		{
		IMethod methodforget7;
			IModelElement[] moduleChilds = module.getChildren();
			methodforget7 = ModelTestUtils.getAssertMethod( moduleChilds, "forget", 1 );
			ModelTestUtils.assertParameterNames( methodforget7, new String[] {"modname"} );
		}
		//Function test:is_resource_enabled
		{
		IMethod methodis_resource_enabled8;
			IModelElement[] moduleChilds = module.getChildren();
			methodis_resource_enabled8 = ModelTestUtils.getAssertMethod( moduleChilds, "is_resource_enabled", 1 );
			ModelTestUtils.assertParameterNames( methodis_resource_enabled8, new String[] {"resource"} );
		}
		//Function test:requires
		{
		IMethod methodrequires9;
			IModelElement[] moduleChilds = module.getChildren();
			methodrequires9 = ModelTestUtils.getAssertMethod( moduleChilds, "requires", 2 );
			ModelTestUtils.assertParameterNames( methodrequires9, new String[] {"resource", "msg"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FUZZ");
		}
		//Function test:fcmp
		{
		IMethod methodfcmp10;
			IModelElement[] moduleChilds = module.getChildren();
			methodfcmp10 = ModelTestUtils.getAssertMethod( moduleChilds, "fcmp", 2 );
			ModelTestUtils.assertParameterNames( methodfcmp10, new String[] {"x", "y"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "have_unicode");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "have_unicode");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "is_jython");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_UNICODE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_UNICODE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_ENCODING");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_UNICODE_UNENCODEABLE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_UNICODE_UNENCODEABLE");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TMP_TESTFN");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fp");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN");
		}
		//Function test:findfile
		{
		IMethod methodfindfile11;
			IModelElement[] moduleChilds = module.getChildren();
			methodfindfile11 = ModelTestUtils.getAssertMethod( moduleChilds, "findfile", 2 );
			ModelTestUtils.assertParameterNames( methodfindfile11, new String[] {"file", "here"} );
		}
		//Function test:verify
		{
		IMethod methodverify12;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify12 = ModelTestUtils.getAssertMethod( moduleChilds, "verify", 2 );
			ModelTestUtils.assertParameterNames( methodverify12, new String[] {"condition", "reason"} );
		}
		//Function test:vereq
		{
		IMethod methodvereq13;
			IModelElement[] moduleChilds = module.getChildren();
			methodvereq13 = ModelTestUtils.getAssertMethod( moduleChilds, "vereq", 2 );
			ModelTestUtils.assertParameterNames( methodvereq13, new String[] {"a", "b"} );
		}
		//Function test:sortdict
		{
		IMethod methodsortdict14;
			IModelElement[] moduleChilds = module.getChildren();
			methodsortdict14 = ModelTestUtils.getAssertMethod( moduleChilds, "sortdict", 1 );
			ModelTestUtils.assertParameterNames( methodsortdict14, new String[] {"dict"} );
		}
		//Function test:check_syntax
		{
		IMethod methodcheck_syntax15;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_syntax15 = ModelTestUtils.getAssertMethod( moduleChilds, "check_syntax", 1 );
			ModelTestUtils.assertParameterNames( methodcheck_syntax15, new String[] {"statement"} );
		}
		//Class test
		{
		IType classBasicTestRunner16;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicTestRunner16 = ModelTestUtils.getAssertClass( moduleChilds, "BasicTestRunner" );
			//Function test:run
			{
			IMethod methodrun17;
				IModelElement[] classBasicTestRunner16Childs = classBasicTestRunner16.getChildren();
				methodrun17 = ModelTestUtils.getAssertMethod( classBasicTestRunner16Childs, "run", 2 );
				ModelTestUtils.assertParameterNames( methodrun17, new String[] {"self", "test"} );
			}
		}
		//Function test:run_suite
		{
		IMethod methodrun_suite18;
			IModelElement[] moduleChilds = module.getChildren();
			methodrun_suite18 = ModelTestUtils.getAssertMethod( moduleChilds, "run_suite", 2 );
			ModelTestUtils.assertParameterNames( methodrun_suite18, new String[] {"suite", "testclass"} );
		}
		//Function test:run_unittest
		{
		IMethod methodrun_unittest19;
			IModelElement[] moduleChilds = module.getChildren();
			methodrun_unittest19 = ModelTestUtils.getAssertMethod( moduleChilds, "run_unittest", 1 );
			ModelTestUtils.assertParameterNames( methodrun_unittest19, new String[] {"classes"} );
		}
		//Function test:run_doctest
		{
		IMethod methodrun_doctest20;
			IModelElement[] moduleChilds = module.getChildren();
			methodrun_doctest20 = ModelTestUtils.getAssertMethod( moduleChilds, "run_doctest", 2 );
			ModelTestUtils.assertParameterNames( methodrun_doctest20, new String[] {"module", "verbosity"} );
		}

	}
	public void testModelGen303( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("list_tests.py"));

		assertNotNull("Module list_tests.py not found", module);
		assertEquals("list_tests.py", module.getElementName());
		
		//Class test
		{
		IType classCommonTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classCommonTest0 = ModelTestUtils.getAssertClass( moduleChilds, "CommonTest" );
			//Function test:test_init
			{
			IMethod methodtest_init1;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_init1 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_init", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init1, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr2;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_repr2 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr2, new String[] {"self"} );
			}
			//Function test:test_print
			{
			IMethod methodtest_print3;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_print3 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_print", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print3, new String[] {"self"} );
			}
			//Function test:test_set_subscript
			{
			IMethod methodtest_set_subscript4;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_set_subscript4 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_set_subscript", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_subscript4, new String[] {"self"} );
			}
			//Function test:test_reversed
			{
			IMethod methodtest_reversed5;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_reversed5 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_reversed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reversed5, new String[] {"self"} );
			}
			//Function test:test_setitem
			{
			IMethod methodtest_setitem6;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_setitem6 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_setitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setitem6, new String[] {"self"} );
			}
			//Function test:test_delitem
			{
			IMethod methodtest_delitem7;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_delitem7 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delitem7, new String[] {"self"} );
			}
			//Function test:test_setslice
			{
			IMethod methodtest_setslice8;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_setslice8 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_setslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setslice8, new String[] {"self"} );
			}
			//Function test:test_delslice
			{
			IMethod methodtest_delslice9;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_delslice9 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_delslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delslice9, new String[] {"self"} );
			}
			//Function test:test_append
			{
			IMethod methodtest_append10;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_append10 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_append10, new String[] {"self"} );
			}
			//Function test:test_extend
			{
			IMethod methodtest_extend11;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_extend11 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_extend", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend11, new String[] {"self"} );
			}
			//Function test:test_insert
			{
			IMethod methodtest_insert12;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_insert12 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_insert", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insert12, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop13;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_pop13 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop13, new String[] {"self"} );
			}
			//Function test:test_remove
			{
			IMethod methodtest_remove14;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_remove14 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_remove", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove14, new String[] {"self"} );
				//Class test
				{
				IType classBadExc15;
					IModelElement[] methodtest_remove14Childs = methodtest_remove14.getChildren();
					classBadExc15 = ModelTestUtils.getAssertClass( methodtest_remove14Childs, "BadExc" );
				}
				//Class test
				{
				IType classBadCmp16;
					IModelElement[] methodtest_remove14Childs = methodtest_remove14.getChildren();
					classBadCmp16 = ModelTestUtils.getAssertClass( methodtest_remove14Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__17;
						IModelElement[] classBadCmp16Childs = classBadCmp16.getChildren();
						method__eq__17 = ModelTestUtils.getAssertMethod( classBadCmp16Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__17, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_count
			{
			IMethod methodtest_count18;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_count18 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_count", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count18, new String[] {"self"} );
				//Class test
				{
				IType classBadExc19;
					IModelElement[] methodtest_count18Childs = methodtest_count18.getChildren();
					classBadExc19 = ModelTestUtils.getAssertClass( methodtest_count18Childs, "BadExc" );
				}
				//Class test
				{
				IType classBadCmp20;
					IModelElement[] methodtest_count18Childs = methodtest_count18.getChildren();
					classBadCmp20 = ModelTestUtils.getAssertClass( methodtest_count18Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__21;
						IModelElement[] classBadCmp20Childs = classBadCmp20.getChildren();
						method__eq__21 = ModelTestUtils.getAssertMethod( classBadCmp20Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__21, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_index
			{
			IMethod methodtest_index22;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_index22 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_index", 1 );
				ModelTestUtils.assertParameterNames( methodtest_index22, new String[] {"self"} );
				//Class test
				{
				IType classBadExc23;
					IModelElement[] methodtest_index22Childs = methodtest_index22.getChildren();
					classBadExc23 = ModelTestUtils.getAssertClass( methodtest_index22Childs, "BadExc" );
				}
				//Class test
				{
				IType classBadCmp24;
					IModelElement[] methodtest_index22Childs = methodtest_index22.getChildren();
					classBadCmp24 = ModelTestUtils.getAssertClass( methodtest_index22Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__25;
						IModelElement[] classBadCmp24Childs = classBadCmp24.getChildren();
						method__eq__25 = ModelTestUtils.getAssertMethod( classBadCmp24Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__25, new String[] {"self", "other"} );
					}
				}
				//Class test
				{
				IType classEvilCmp26;
					IModelElement[] methodtest_index22Childs = methodtest_index22.getChildren();
					classEvilCmp26 = ModelTestUtils.getAssertClass( methodtest_index22Childs, "EvilCmp" );
					//Function test:__init__
					{
					IMethod method__init__27;
						IModelElement[] classEvilCmp26Childs = classEvilCmp26.getChildren();
						method__init__27 = ModelTestUtils.getAssertMethod( classEvilCmp26Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__27, new String[] {"self", "victim"} );
					}
					{
						IModelElement[] classEvilCmp26Childs = classEvilCmp26.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classEvilCmp26Childs, "victim");
					}
					//Function test:__eq__
					{
					IMethod method__eq__29;
						IModelElement[] classEvilCmp26Childs = classEvilCmp26.getChildren();
						method__eq__29 = ModelTestUtils.getAssertMethod( classEvilCmp26Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__29, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reverse
			{
			IMethod methodtest_reverse30;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_reverse30 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_reverse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse30, new String[] {"self"} );
			}
			//Function test:test_sort
			{
			IMethod methodtest_sort31;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_sort31 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_sort", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sort31, new String[] {"self"} );
				//Function test:revcmp
				{
				IMethod methodrevcmp32;
					IModelElement[] methodtest_sort31Childs = methodtest_sort31.getChildren();
					methodrevcmp32 = ModelTestUtils.getAssertMethod( methodtest_sort31Childs, "revcmp", 2 );
					ModelTestUtils.assertParameterNames( methodrevcmp32, new String[] {"a", "b"} );
				}
				//Function test:myComparison
				{
				IMethod methodmyComparison33;
					IModelElement[] methodtest_sort31Childs = methodtest_sort31.getChildren();
					methodmyComparison33 = ModelTestUtils.getAssertMethod( methodtest_sort31Childs, "myComparison", 2 );
					ModelTestUtils.assertParameterNames( methodmyComparison33, new String[] {"x", "y"} );
				}
				//Function test:selfmodifyingComparison
				{
				IMethod methodselfmodifyingComparison34;
					IModelElement[] methodtest_sort31Childs = methodtest_sort31.getChildren();
					methodselfmodifyingComparison34 = ModelTestUtils.getAssertMethod( methodtest_sort31Childs, "selfmodifyingComparison", 2 );
					ModelTestUtils.assertParameterNames( methodselfmodifyingComparison34, new String[] {"x", "y"} );
				}
			}
			//Function test:test_slice
			{
			IMethod methodtest_slice35;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_slice35 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_slice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_slice35, new String[] {"self"} );
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd36;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_iadd36 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd36, new String[] {"self"} );
			}
			//Function test:test_imul
			{
			IMethod methodtest_imul37;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_imul37 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_imul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imul37, new String[] {"self"} );
			}
			//Function test:test_extendedslicing
			{
			IMethod methodtest_extendedslicing38;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_extendedslicing38 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_extendedslicing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extendedslicing38, new String[] {"self"} );
			}
		}

	}
	public void testModelGen304( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_strop.py"));

		assertNotNull("Module test_strop.py not found", module);
		assertEquals("test_strop.py", module.getElementName());
		
		//Class test
		{
		IType classStropFunctionTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classStropFunctionTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "StropFunctionTestCase" );
			//Function test:test_atoi
			{
			IMethod methodtest_atoi1;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_atoi1 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_atoi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_atoi1, new String[] {"self"} );
			}
			//Function test:test_atol
			{
			IMethod methodtest_atol2;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_atol2 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_atol", 1 );
				ModelTestUtils.assertParameterNames( methodtest_atol2, new String[] {"self"} );
			}
			//Function test:test_atof
			{
			IMethod methodtest_atof3;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_atof3 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_atof", 1 );
				ModelTestUtils.assertParameterNames( methodtest_atof3, new String[] {"self"} );
			}
			//Function test:test_capitalize
			{
			IMethod methodtest_capitalize4;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_capitalize4 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_capitalize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_capitalize4, new String[] {"self"} );
			}
			//Function test:test_find
			{
			IMethod methodtest_find5;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_find5 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_find", 1 );
				ModelTestUtils.assertParameterNames( methodtest_find5, new String[] {"self"} );
			}
			//Function test:test_rfind
			{
			IMethod methodtest_rfind6;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_rfind6 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_rfind", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rfind6, new String[] {"self"} );
			}
			//Function test:test_lower
			{
			IMethod methodtest_lower7;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_lower7 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_lower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lower7, new String[] {"self"} );
			}
			//Function test:test_upper
			{
			IMethod methodtest_upper8;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_upper8 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_upper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_upper8, new String[] {"self"} );
			}
			//Function test:test_swapcase
			{
			IMethod methodtest_swapcase9;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_swapcase9 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_swapcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_swapcase9, new String[] {"self"} );
			}
			//Function test:test_strip
			{
			IMethod methodtest_strip10;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_strip10 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_strip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strip10, new String[] {"self"} );
			}
			//Function test:test_lstrip
			{
			IMethod methodtest_lstrip11;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_lstrip11 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_lstrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lstrip11, new String[] {"self"} );
			}
			//Function test:test_rstrip
			{
			IMethod methodtest_rstrip12;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_rstrip12 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_rstrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rstrip12, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace13;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_replace13 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace13, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split14;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_split14 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split14, new String[] {"self"} );
			}
			//Function test:test_join
			{
			IMethod methodtest_join15;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_join15 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_join", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join15, new String[] {"self"} );
			}
			//Function test:test_maketrans
			{
			IMethod methodtest_maketrans16;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_maketrans16 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_maketrans", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maketrans16, new String[] {"self"} );
			}
			//Function test:test_translate
			{
			IMethod methodtest_translate17;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_translate17 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_translate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_translate17, new String[] {"self"} );
			}
			//Function test:test_data_attributes
			{
			IMethod methodtest_data_attributes18;
				IModelElement[] classStropFunctionTestCase0Childs = classStropFunctionTestCase0.getChildren();
				methodtest_data_attributes18 = ModelTestUtils.getAssertMethod( classStropFunctionTestCase0Childs, "test_data_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_data_attributes18, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "transtable");
		}
		//Class test
		{
		IType classSequence19;
			IModelElement[] moduleChilds = module.getChildren();
			classSequence19 = ModelTestUtils.getAssertClass( moduleChilds, "Sequence" );
			//Function test:__init__
			{
			IMethod method__init__20;
				IModelElement[] classSequence19Childs = classSequence19.getChildren();
				method__init__20 = ModelTestUtils.getAssertMethod( classSequence19Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__20, new String[] {"self"} );
			}
			{
				IModelElement[] classSequence19Childs = classSequence19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSequence19Childs, "seq");
			}
			//Function test:__len__
			{
			IMethod method__len__22;
				IModelElement[] classSequence19Childs = classSequence19.getChildren();
				method__len__22 = ModelTestUtils.getAssertMethod( classSequence19Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__22, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__23;
				IModelElement[] classSequence19Childs = classSequence19.getChildren();
				method__getitem__23 = ModelTestUtils.getAssertMethod( classSequence19Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__23, new String[] {"self", "i"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main24 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen305( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_ioctl.py"));

		assertNotNull("Module test_ioctl.py not found", module);
		assertEquals("test_ioctl.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tty");
		}
		//Class test
		{
		IType classIoctlTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classIoctlTests0 = ModelTestUtils.getAssertClass( moduleChilds, "IoctlTests" );
			//Function test:test_ioctl
			{
			IMethod methodtest_ioctl1;
				IModelElement[] classIoctlTests0Childs = classIoctlTests0.getChildren();
				methodtest_ioctl1 = ModelTestUtils.getAssertMethod( classIoctlTests0Childs, "test_ioctl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ioctl1, new String[] {"self"} );
			}
			//Function test:test_ioctl_mutate
			{
			IMethod methodtest_ioctl_mutate2;
				IModelElement[] classIoctlTests0Childs = classIoctlTests0.getChildren();
				methodtest_ioctl_mutate2 = ModelTestUtils.getAssertMethod( classIoctlTests0Childs, "test_ioctl_mutate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ioctl_mutate2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen306( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_traceback.py"));

		assertNotNull("Module test_traceback.py not found", module);
		assertEquals("test_traceback.py", module.getElementName());
		
		//Class test
		{
		IType classTracebackCases0;
			IModelElement[] moduleChilds = module.getChildren();
			classTracebackCases0 = ModelTestUtils.getAssertClass( moduleChilds, "TracebackCases" );
			//Function test:get_exception_format
			{
			IMethod methodget_exception_format1;
				IModelElement[] classTracebackCases0Childs = classTracebackCases0.getChildren();
				methodget_exception_format1 = ModelTestUtils.getAssertMethod( classTracebackCases0Childs, "get_exception_format", 3 );
				ModelTestUtils.assertParameterNames( methodget_exception_format1, new String[] {"self", "func", "exc"} );
			}
			//Function test:syntax_error_with_caret
			{
			IMethod methodsyntax_error_with_caret2;
				IModelElement[] classTracebackCases0Childs = classTracebackCases0.getChildren();
				methodsyntax_error_with_caret2 = ModelTestUtils.getAssertMethod( classTracebackCases0Childs, "syntax_error_with_caret", 1 );
				ModelTestUtils.assertParameterNames( methodsyntax_error_with_caret2, new String[] {"self"} );
			}
			//Function test:syntax_error_without_caret
			{
			IMethod methodsyntax_error_without_caret3;
				IModelElement[] classTracebackCases0Childs = classTracebackCases0.getChildren();
				methodsyntax_error_without_caret3 = ModelTestUtils.getAssertMethod( classTracebackCases0Childs, "syntax_error_without_caret", 1 );
				ModelTestUtils.assertParameterNames( methodsyntax_error_without_caret3, new String[] {"self"} );
			}
			//Function test:test_caret
			{
			IMethod methodtest_caret4;
				IModelElement[] classTracebackCases0Childs = classTracebackCases0.getChildren();
				methodtest_caret4 = ModelTestUtils.getAssertMethod( classTracebackCases0Childs, "test_caret", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caret4, new String[] {"self"} );
			}
			//Function test:test_nocaret
			{
			IMethod methodtest_nocaret5;
				IModelElement[] classTracebackCases0Childs = classTracebackCases0.getChildren();
				methodtest_nocaret5 = ModelTestUtils.getAssertMethod( classTracebackCases0Childs, "test_nocaret", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nocaret5, new String[] {"self"} );
			}
			//Function test:test_bug737473
			{
			IMethod methodtest_bug7374736;
				IModelElement[] classTracebackCases0Childs = classTracebackCases0.getChildren();
				methodtest_bug7374736 = ModelTestUtils.getAssertMethod( classTracebackCases0Childs, "test_bug737473", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug7374736, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen307( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pyexpat.py"));

		assertNotNull("Module test_pyexpat.py not found", module);
		assertEquals("test_pyexpat.py", module.getElementName());
		
		//Class test
		{
		IType classOutputter0;
			IModelElement[] moduleChilds = module.getChildren();
			classOutputter0 = ModelTestUtils.getAssertClass( moduleChilds, "Outputter" );
			//Function test:StartElementHandler
			{
			IMethod methodStartElementHandler1;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodStartElementHandler1 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "StartElementHandler", 3 );
				ModelTestUtils.assertParameterNames( methodStartElementHandler1, new String[] {"self", "name", "attrs"} );
			}
			//Function test:EndElementHandler
			{
			IMethod methodEndElementHandler2;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodEndElementHandler2 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "EndElementHandler", 2 );
				ModelTestUtils.assertParameterNames( methodEndElementHandler2, new String[] {"self", "name"} );
			}
			//Function test:CharacterDataHandler
			{
			IMethod methodCharacterDataHandler3;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodCharacterDataHandler3 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "CharacterDataHandler", 2 );
				ModelTestUtils.assertParameterNames( methodCharacterDataHandler3, new String[] {"self", "data"} );
			}
			//Function test:ProcessingInstructionHandler
			{
			IMethod methodProcessingInstructionHandler4;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodProcessingInstructionHandler4 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "ProcessingInstructionHandler", 3 );
				ModelTestUtils.assertParameterNames( methodProcessingInstructionHandler4, new String[] {"self", "target", "data"} );
			}
			//Function test:StartNamespaceDeclHandler
			{
			IMethod methodStartNamespaceDeclHandler5;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodStartNamespaceDeclHandler5 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "StartNamespaceDeclHandler", 3 );
				ModelTestUtils.assertParameterNames( methodStartNamespaceDeclHandler5, new String[] {"self", "prefix", "uri"} );
			}
			//Function test:EndNamespaceDeclHandler
			{
			IMethod methodEndNamespaceDeclHandler6;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodEndNamespaceDeclHandler6 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "EndNamespaceDeclHandler", 2 );
				ModelTestUtils.assertParameterNames( methodEndNamespaceDeclHandler6, new String[] {"self", "prefix"} );
			}
			//Function test:StartCdataSectionHandler
			{
			IMethod methodStartCdataSectionHandler7;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodStartCdataSectionHandler7 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "StartCdataSectionHandler", 1 );
				ModelTestUtils.assertParameterNames( methodStartCdataSectionHandler7, new String[] {"self"} );
			}
			//Function test:EndCdataSectionHandler
			{
			IMethod methodEndCdataSectionHandler8;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodEndCdataSectionHandler8 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "EndCdataSectionHandler", 1 );
				ModelTestUtils.assertParameterNames( methodEndCdataSectionHandler8, new String[] {"self"} );
			}
			//Function test:CommentHandler
			{
			IMethod methodCommentHandler9;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodCommentHandler9 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "CommentHandler", 2 );
				ModelTestUtils.assertParameterNames( methodCommentHandler9, new String[] {"self", "text"} );
			}
			//Function test:NotationDeclHandler
			{
			IMethod methodNotationDeclHandler10;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodNotationDeclHandler10 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "NotationDeclHandler", 2 );
				ModelTestUtils.assertParameterNames( methodNotationDeclHandler10, new String[] {"self", "args"} );
			}
			//Function test:UnparsedEntityDeclHandler
			{
			IMethod methodUnparsedEntityDeclHandler11;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodUnparsedEntityDeclHandler11 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "UnparsedEntityDeclHandler", 2 );
				ModelTestUtils.assertParameterNames( methodUnparsedEntityDeclHandler11, new String[] {"self", "args"} );
			}
			//Function test:NotStandaloneHandler
			{
			IMethod methodNotStandaloneHandler12;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodNotStandaloneHandler12 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "NotStandaloneHandler", 2 );
				ModelTestUtils.assertParameterNames( methodNotStandaloneHandler12, new String[] {"self", "userData"} );
			}
			//Function test:ExternalEntityRefHandler
			{
			IMethod methodExternalEntityRefHandler13;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodExternalEntityRefHandler13 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "ExternalEntityRefHandler", 2 );
				ModelTestUtils.assertParameterNames( methodExternalEntityRefHandler13, new String[] {"self", "args"} );
			}
			//Function test:DefaultHandler
			{
			IMethod methodDefaultHandler14;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodDefaultHandler14 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "DefaultHandler", 2 );
				ModelTestUtils.assertParameterNames( methodDefaultHandler14, new String[] {"self", "userData"} );
			}
			//Function test:DefaultHandlerExpand
			{
			IMethod methodDefaultHandlerExpand15;
				IModelElement[] classOutputter0Childs = classOutputter0.getChildren();
				methodDefaultHandlerExpand15 = ModelTestUtils.getAssertMethod( classOutputter0Childs, "DefaultHandlerExpand", 2 );
				ModelTestUtils.assertParameterNames( methodDefaultHandlerExpand15, new String[] {"self", "userData"} );
			}
		}
		//Function test:confirm
		{
		IMethod methodconfirm16;
			IModelElement[] moduleChilds = module.getChildren();
			methodconfirm16 = ModelTestUtils.getAssertMethod( moduleChilds, "confirm", 1 );
			ModelTestUtils.assertParameterNames( methodconfirm16, new String[] {"ok"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "out");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parser");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "HANDLER_NAMES");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "data");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parser");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parser");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "file");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "p");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "L");
		}
		//Function test:collector
		{
		IMethod methodcollector17;
			IModelElement[] moduleChilds = module.getChildren();
			methodcollector17 = ModelTestUtils.getAssertMethod( moduleChilds, "collector", 2 );
			ModelTestUtils.assertParameterNames( methodcollector17, new String[] {"name", "args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tag");
		}
		//Class test
		{
		IType classTextCollector18;
			IModelElement[] moduleChilds = module.getChildren();
			classTextCollector18 = ModelTestUtils.getAssertClass( moduleChilds, "TextCollector" );
			//Function test:__init__
			{
			IMethod method__init__19;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				method__init__19 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__19, new String[] {"self", "parser"} );
			}
			{
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTextCollector18Childs, "stuff");
			}
			//Function test:check
			{
			IMethod methodcheck21;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodcheck21 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "check", 3 );
				ModelTestUtils.assertParameterNames( methodcheck21, new String[] {"self", "expected", "label"} );
			}
			//Function test:CharacterDataHandler
			{
			IMethod methodCharacterDataHandler22;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodCharacterDataHandler22 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "CharacterDataHandler", 2 );
				ModelTestUtils.assertParameterNames( methodCharacterDataHandler22, new String[] {"self", "text"} );
			}
			//Function test:StartElementHandler
			{
			IMethod methodStartElementHandler23;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodStartElementHandler23 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "StartElementHandler", 3 );
				ModelTestUtils.assertParameterNames( methodStartElementHandler23, new String[] {"self", "name", "attrs"} );
			}
			//Function test:EndElementHandler
			{
			IMethod methodEndElementHandler24;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodEndElementHandler24 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "EndElementHandler", 2 );
				ModelTestUtils.assertParameterNames( methodEndElementHandler24, new String[] {"self", "name"} );
			}
			//Function test:CommentHandler
			{
			IMethod methodCommentHandler25;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodCommentHandler25 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "CommentHandler", 2 );
				ModelTestUtils.assertParameterNames( methodCommentHandler25, new String[] {"self", "data"} );
			}
		}
		//Function test:require
		{
		IMethod methodrequire26;
			IModelElement[] moduleChilds = module.getChildren();
			methodrequire26 = ModelTestUtils.getAssertMethod( moduleChilds, "require", 2 );
			ModelTestUtils.assertParameterNames( methodrequire26, new String[] {"cond", "label"} );
		}
		//Function test:setup
		{
		IMethod methodsetup27;
			IModelElement[] moduleChilds = module.getChildren();
			methodsetup27 = ModelTestUtils.getAssertMethod( moduleChilds, "setup", 1 );
			ModelTestUtils.assertParameterNames( methodsetup27, new String[] {"handlers"} );
		}
		//Function test:StartElementHandler
		{
		IMethod methodStartElementHandler28;
			IModelElement[] moduleChilds = module.getChildren();
			methodStartElementHandler28 = ModelTestUtils.getAssertMethod( moduleChilds, "StartElementHandler", 2 );
			ModelTestUtils.assertParameterNames( methodStartElementHandler28, new String[] {"name", "attrs"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parser");
		}
		//Class test
		{
		IType classPositionTest29;
			IModelElement[] moduleChilds = module.getChildren();
			classPositionTest29 = ModelTestUtils.getAssertClass( moduleChilds, "PositionTest" );
			//Function test:__init__
			{
			IMethod method__init__30;
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				method__init__30 = ModelTestUtils.getAssertMethod( classPositionTest29Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__30, new String[] {"self", "expected_list", "parser"} );
			}
			{
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPositionTest29Childs, "parser");
			}
			{
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPositionTest29Childs, "expected_list");
			}
			{
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPositionTest29Childs, "upto");
			}
			//Function test:StartElementHandler
			{
			IMethod methodStartElementHandler32;
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				methodStartElementHandler32 = ModelTestUtils.getAssertMethod( classPositionTest29Childs, "StartElementHandler", 3 );
				ModelTestUtils.assertParameterNames( methodStartElementHandler32, new String[] {"self", "name", "attrs"} );
			}
			//Function test:EndElementHandler
			{
			IMethod methodEndElementHandler33;
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				methodEndElementHandler33 = ModelTestUtils.getAssertMethod( classPositionTest29Childs, "EndElementHandler", 2 );
				ModelTestUtils.assertParameterNames( methodEndElementHandler33, new String[] {"self", "name"} );
			}
			//Function test:check_pos
			{
			IMethod methodcheck_pos34;
				IModelElement[] classPositionTest29Childs = classPositionTest29.getChildren();
				methodcheck_pos34 = ModelTestUtils.getAssertMethod( classPositionTest29Childs, "check_pos", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_pos34, new String[] {"self", "event"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parser");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "handler");
		}

	}
	public void testModelGen308( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_shelve.py"));

		assertNotNull("Module test_shelve.py not found", module);
		assertEquals("test_shelve.py", module.getElementName());
		
		//Class test
		{
		IType classTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "TestCase" );
			{
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCase0Childs, "fn");
			}
			//Function test:test_ascii_file_shelf
			{
			IMethod methodtest_ascii_file_shelf1;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_ascii_file_shelf1 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_ascii_file_shelf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ascii_file_shelf1, new String[] {"self"} );
			}
			//Function test:test_binary_file_shelf
			{
			IMethod methodtest_binary_file_shelf2;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_binary_file_shelf2 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_binary_file_shelf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binary_file_shelf2, new String[] {"self"} );
			}
			//Function test:test_proto2_file_shelf
			{
			IMethod methodtest_proto2_file_shelf3;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_proto2_file_shelf3 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_proto2_file_shelf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proto2_file_shelf3, new String[] {"self"} );
			}
			//Function test:test_in_memory_shelf
			{
			IMethod methodtest_in_memory_shelf4;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_in_memory_shelf4 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_in_memory_shelf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in_memory_shelf4, new String[] {"self"} );
			}
			//Function test:test_mutable_entry
			{
			IMethod methodtest_mutable_entry5;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_mutable_entry5 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_mutable_entry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutable_entry5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestShelveBase6;
			IModelElement[] moduleChilds = module.getChildren();
			classTestShelveBase6 = ModelTestUtils.getAssertClass( moduleChilds, "TestShelveBase" );
			{
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShelveBase6Childs, "fn");
			}
			{
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShelveBase6Childs, "counter");
			}
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "args", "kw"} );
			}
			{
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShelveBase6Childs, "_db");
			}
			{
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShelveBase6Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference9;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				method_reference9 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference9, new String[] {"self"} );
			}
			//Function test:_empty_mapping
			{
			IMethod method_empty_mapping10;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				method_empty_mapping10 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "_empty_mapping", 1 );
				ModelTestUtils.assertParameterNames( method_empty_mapping10, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown11;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				methodtearDown11 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown11, new String[] {"self"} );
			}
			{
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShelveBase6Childs, "_db");
			}
		}
		//Class test
		{
		IType classTestAsciiFileShelve13;
			IModelElement[] moduleChilds = module.getChildren();
			classTestAsciiFileShelve13 = ModelTestUtils.getAssertClass( moduleChilds, "TestAsciiFileShelve" );
			{
				IModelElement[] classTestAsciiFileShelve13Childs = classTestAsciiFileShelve13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiFileShelve13Childs, "_args");
			}
			{
				IModelElement[] classTestAsciiFileShelve13Childs = classTestAsciiFileShelve13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiFileShelve13Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestBinaryFileShelve14;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryFileShelve14 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryFileShelve" );
			{
				IModelElement[] classTestBinaryFileShelve14Childs = classTestBinaryFileShelve14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryFileShelve14Childs, "_args");
			}
			{
				IModelElement[] classTestBinaryFileShelve14Childs = classTestBinaryFileShelve14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryFileShelve14Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestProto2FileShelve15;
			IModelElement[] moduleChilds = module.getChildren();
			classTestProto2FileShelve15 = ModelTestUtils.getAssertClass( moduleChilds, "TestProto2FileShelve" );
			{
				IModelElement[] classTestProto2FileShelve15Childs = classTestProto2FileShelve15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2FileShelve15Childs, "_args");
			}
			{
				IModelElement[] classTestProto2FileShelve15Childs = classTestProto2FileShelve15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2FileShelve15Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestAsciiMemShelve16;
			IModelElement[] moduleChilds = module.getChildren();
			classTestAsciiMemShelve16 = ModelTestUtils.getAssertClass( moduleChilds, "TestAsciiMemShelve" );
			{
				IModelElement[] classTestAsciiMemShelve16Childs = classTestAsciiMemShelve16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiMemShelve16Childs, "_args");
			}
			{
				IModelElement[] classTestAsciiMemShelve16Childs = classTestAsciiMemShelve16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiMemShelve16Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestBinaryMemShelve17;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryMemShelve17 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryMemShelve" );
			{
				IModelElement[] classTestBinaryMemShelve17Childs = classTestBinaryMemShelve17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryMemShelve17Childs, "_args");
			}
			{
				IModelElement[] classTestBinaryMemShelve17Childs = classTestBinaryMemShelve17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryMemShelve17Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestProto2MemShelve18;
			IModelElement[] moduleChilds = module.getChildren();
			classTestProto2MemShelve18 = ModelTestUtils.getAssertClass( moduleChilds, "TestProto2MemShelve" );
			{
				IModelElement[] classTestProto2MemShelve18Childs = classTestProto2MemShelve18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2MemShelve18Childs, "_args");
			}
			{
				IModelElement[] classTestProto2MemShelve18Childs = classTestProto2MemShelve18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2MemShelve18Childs, "_in_mem");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen309( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_augassign.py"));

		assertNotNull("Module test_augassign.py not found", module);
		assertEquals("test_augassign.py", module.getElementName());
		
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		//Class test
		{
		IType classaug_test0;
			IModelElement[] moduleChilds = module.getChildren();
			classaug_test0 = ModelTestUtils.getAssertClass( moduleChilds, "aug_test" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classaug_test0Childs = classaug_test0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classaug_test0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "value"} );
			}
			{
				IModelElement[] classaug_test0Childs = classaug_test0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classaug_test0Childs, "val");
			}
			//Function test:__radd__
			{
			IMethod method__radd__3;
				IModelElement[] classaug_test0Childs = classaug_test0.getChildren();
				method__radd__3 = ModelTestUtils.getAssertMethod( classaug_test0Childs, "__radd__", 2 );
				ModelTestUtils.assertParameterNames( method__radd__3, new String[] {"self", "val"} );
			}
			//Function test:__add__
			{
			IMethod method__add__4;
				IModelElement[] classaug_test0Childs = classaug_test0.getChildren();
				method__add__4 = ModelTestUtils.getAssertMethod( classaug_test0Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__4, new String[] {"self", "val"} );
			}
		}
		//Class test
		{
		IType classaug_test25;
			IModelElement[] moduleChilds = module.getChildren();
			classaug_test25 = ModelTestUtils.getAssertClass( moduleChilds, "aug_test2" );
			//Function test:__iadd__
			{
			IMethod method__iadd__6;
				IModelElement[] classaug_test25Childs = classaug_test25.getChildren();
				method__iadd__6 = ModelTestUtils.getAssertMethod( classaug_test25Childs, "__iadd__", 2 );
				ModelTestUtils.assertParameterNames( method__iadd__6, new String[] {"self", "val"} );
			}
			{
				IModelElement[] classaug_test25Childs = classaug_test25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classaug_test25Childs, "val");
			}
		}
		//Class test
		{
		IType classaug_test38;
			IModelElement[] moduleChilds = module.getChildren();
			classaug_test38 = ModelTestUtils.getAssertClass( moduleChilds, "aug_test3" );
			//Function test:__iadd__
			{
			IMethod method__iadd__9;
				IModelElement[] classaug_test38Childs = classaug_test38.getChildren();
				method__iadd__9 = ModelTestUtils.getAssertMethod( classaug_test38Childs, "__iadd__", 2 );
				ModelTestUtils.assertParameterNames( method__iadd__9, new String[] {"self", "val"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		//Class test
		{
		IType classtestall10;
			IModelElement[] moduleChilds = module.getChildren();
			classtestall10 = ModelTestUtils.getAssertClass( moduleChilds, "testall" );
			//Function test:__add__
			{
			IMethod method__add__11;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__add__11 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__11, new String[] {"self", "val"} );
			}
			//Function test:__radd__
			{
			IMethod method__radd__12;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__radd__12 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__radd__", 2 );
				ModelTestUtils.assertParameterNames( method__radd__12, new String[] {"self", "val"} );
			}
			//Function test:__iadd__
			{
			IMethod method__iadd__13;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__iadd__13 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__iadd__", 2 );
				ModelTestUtils.assertParameterNames( method__iadd__13, new String[] {"self", "val"} );
			}
			//Function test:__sub__
			{
			IMethod method__sub__14;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__sub__14 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__sub__", 2 );
				ModelTestUtils.assertParameterNames( method__sub__14, new String[] {"self", "val"} );
			}
			//Function test:__rsub__
			{
			IMethod method__rsub__15;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rsub__15 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rsub__", 2 );
				ModelTestUtils.assertParameterNames( method__rsub__15, new String[] {"self", "val"} );
			}
			//Function test:__isub__
			{
			IMethod method__isub__16;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__isub__16 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__isub__", 2 );
				ModelTestUtils.assertParameterNames( method__isub__16, new String[] {"self", "val"} );
			}
			//Function test:__mul__
			{
			IMethod method__mul__17;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__mul__17 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__mul__", 2 );
				ModelTestUtils.assertParameterNames( method__mul__17, new String[] {"self", "val"} );
			}
			//Function test:__rmul__
			{
			IMethod method__rmul__18;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rmul__18 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rmul__", 2 );
				ModelTestUtils.assertParameterNames( method__rmul__18, new String[] {"self", "val"} );
			}
			//Function test:__imul__
			{
			IMethod method__imul__19;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__imul__19 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__imul__", 2 );
				ModelTestUtils.assertParameterNames( method__imul__19, new String[] {"self", "val"} );
			}
			//Function test:__div__
			{
			IMethod method__div__20;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__div__20 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__div__", 2 );
				ModelTestUtils.assertParameterNames( method__div__20, new String[] {"self", "val"} );
			}
			//Function test:__rdiv__
			{
			IMethod method__rdiv__21;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rdiv__21 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rdiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rdiv__21, new String[] {"self", "val"} );
			}
			//Function test:__idiv__
			{
			IMethod method__idiv__22;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__idiv__22 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__idiv__", 2 );
				ModelTestUtils.assertParameterNames( method__idiv__22, new String[] {"self", "val"} );
			}
			//Function test:__floordiv__
			{
			IMethod method__floordiv__23;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__floordiv__23 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__floordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__floordiv__23, new String[] {"self", "val"} );
			}
			//Function test:__ifloordiv__
			{
			IMethod method__ifloordiv__24;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__ifloordiv__24 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__ifloordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__ifloordiv__24, new String[] {"self", "val"} );
			}
			//Function test:__rfloordiv__
			{
			IMethod method__rfloordiv__25;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rfloordiv__25 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rfloordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rfloordiv__25, new String[] {"self", "val"} );
			}
			//Function test:__truediv__
			{
			IMethod method__truediv__26;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__truediv__26 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__truediv__", 2 );
				ModelTestUtils.assertParameterNames( method__truediv__26, new String[] {"self", "val"} );
			}
			//Function test:__itruediv__
			{
			IMethod method__itruediv__27;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__itruediv__27 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__itruediv__", 2 );
				ModelTestUtils.assertParameterNames( method__itruediv__27, new String[] {"self", "val"} );
			}
			//Function test:__mod__
			{
			IMethod method__mod__28;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__mod__28 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__mod__", 2 );
				ModelTestUtils.assertParameterNames( method__mod__28, new String[] {"self", "val"} );
			}
			//Function test:__rmod__
			{
			IMethod method__rmod__29;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rmod__29 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rmod__29, new String[] {"self", "val"} );
			}
			//Function test:__imod__
			{
			IMethod method__imod__30;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__imod__30 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__imod__", 2 );
				ModelTestUtils.assertParameterNames( method__imod__30, new String[] {"self", "val"} );
			}
			//Function test:__pow__
			{
			IMethod method__pow__31;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__pow__31 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__pow__", 2 );
				ModelTestUtils.assertParameterNames( method__pow__31, new String[] {"self", "val"} );
			}
			//Function test:__rpow__
			{
			IMethod method__rpow__32;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rpow__32 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rpow__", 2 );
				ModelTestUtils.assertParameterNames( method__rpow__32, new String[] {"self", "val"} );
			}
			//Function test:__ipow__
			{
			IMethod method__ipow__33;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__ipow__33 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__ipow__", 2 );
				ModelTestUtils.assertParameterNames( method__ipow__33, new String[] {"self", "val"} );
			}
			//Function test:__or__
			{
			IMethod method__or__34;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__or__34 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__or__", 2 );
				ModelTestUtils.assertParameterNames( method__or__34, new String[] {"self", "val"} );
			}
			//Function test:__ror__
			{
			IMethod method__ror__35;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__ror__35 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__ror__", 2 );
				ModelTestUtils.assertParameterNames( method__ror__35, new String[] {"self", "val"} );
			}
			//Function test:__ior__
			{
			IMethod method__ior__36;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__ior__36 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__ior__", 2 );
				ModelTestUtils.assertParameterNames( method__ior__36, new String[] {"self", "val"} );
			}
			//Function test:__and__
			{
			IMethod method__and__37;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__and__37 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__and__", 2 );
				ModelTestUtils.assertParameterNames( method__and__37, new String[] {"self", "val"} );
			}
			//Function test:__rand__
			{
			IMethod method__rand__38;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rand__38 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rand__", 2 );
				ModelTestUtils.assertParameterNames( method__rand__38, new String[] {"self", "val"} );
			}
			//Function test:__iand__
			{
			IMethod method__iand__39;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__iand__39 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__iand__", 2 );
				ModelTestUtils.assertParameterNames( method__iand__39, new String[] {"self", "val"} );
			}
			//Function test:__xor__
			{
			IMethod method__xor__40;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__xor__40 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__xor__", 2 );
				ModelTestUtils.assertParameterNames( method__xor__40, new String[] {"self", "val"} );
			}
			//Function test:__rxor__
			{
			IMethod method__rxor__41;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rxor__41 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rxor__", 2 );
				ModelTestUtils.assertParameterNames( method__rxor__41, new String[] {"self", "val"} );
			}
			//Function test:__ixor__
			{
			IMethod method__ixor__42;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__ixor__42 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__ixor__", 2 );
				ModelTestUtils.assertParameterNames( method__ixor__42, new String[] {"self", "val"} );
			}
			//Function test:__rshift__
			{
			IMethod method__rshift__43;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rshift__43 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rshift__", 2 );
				ModelTestUtils.assertParameterNames( method__rshift__43, new String[] {"self", "val"} );
			}
			//Function test:__rrshift__
			{
			IMethod method__rrshift__44;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rrshift__44 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rrshift__", 2 );
				ModelTestUtils.assertParameterNames( method__rrshift__44, new String[] {"self", "val"} );
			}
			//Function test:__irshift__
			{
			IMethod method__irshift__45;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__irshift__45 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__irshift__", 2 );
				ModelTestUtils.assertParameterNames( method__irshift__45, new String[] {"self", "val"} );
			}
			//Function test:__lshift__
			{
			IMethod method__lshift__46;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__lshift__46 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__lshift__", 2 );
				ModelTestUtils.assertParameterNames( method__lshift__46, new String[] {"self", "val"} );
			}
			//Function test:__rlshift__
			{
			IMethod method__rlshift__47;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__rlshift__47 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__rlshift__", 2 );
				ModelTestUtils.assertParameterNames( method__rlshift__47, new String[] {"self", "val"} );
			}
			//Function test:__ilshift__
			{
			IMethod method__ilshift__48;
				IModelElement[] classtestall10Childs = classtestall10.getChildren();
				method__ilshift__48 = ModelTestUtils.getAssertMethod( classtestall10Childs, "__ilshift__", 2 );
				ModelTestUtils.assertParameterNames( method__ilshift__48, new String[] {"self", "val"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}

	}
	public void testModelGen310( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_multibytecodec_support.py"));

		assertNotNull("Module test_multibytecodec_support.py not found", module);
		assertEquals("test_multibytecodec_support.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__cjkcodecs__");
		}
		//Class test
		{
		IType classTestBase0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBase0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBase" );
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "encoding");
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "codec");
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "tstring");
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "codectests");
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "roundtriptest");
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "has_iso10646");
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "xmlcharnametest");
			}
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase0Childs, "codec");
			}
			//Function test:test_chunkcoding
			{
			IMethod methodtest_chunkcoding3;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_chunkcoding3 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_chunkcoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chunkcoding3, new String[] {"self"} );
			}
			//Function test:test_errorhandle
			{
			IMethod methodtest_errorhandle4;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_errorhandle4 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_errorhandle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errorhandle4, new String[] {"self"} );
			}
			//Function test:test_xmlcharrefreplace
			{
			IMethod methodtest_xmlcharrefreplace5;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_xmlcharrefreplace5 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_xmlcharrefreplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharrefreplace5, new String[] {"self"} );
			}
			//Function test:test_customreplace
			{
			IMethod methodtest_customreplace6;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_customreplace6 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_customreplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_customreplace6, new String[] {"self"} );
				//Function test:xmlcharnamereplace
				{
				IMethod methodxmlcharnamereplace7;
					IModelElement[] methodtest_customreplace6Childs = methodtest_customreplace6.getChildren();
					methodxmlcharnamereplace7 = ModelTestUtils.getAssertMethod( methodtest_customreplace6Childs, "xmlcharnamereplace", 1 );
					ModelTestUtils.assertParameterNames( methodxmlcharnamereplace7, new String[] {"exc"} );
				}
			}
			//Function test:test_streamreader
			{
			IMethod methodtest_streamreader8;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_streamreader8 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_streamreader", 1 );
				ModelTestUtils.assertParameterNames( methodtest_streamreader8, new String[] {"self"} );
			}
			//Function test:test_streamwriter
			{
			IMethod methodtest_streamwriter9;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_streamwriter9 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_streamwriter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_streamwriter9, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_unichr");
		}
		//Function test:unichr
		{
		IMethod methodunichr10;
			IModelElement[] moduleChilds = module.getChildren();
			methodunichr10 = ModelTestUtils.getAssertMethod( moduleChilds, "unichr", 1 );
			ModelTestUtils.assertParameterNames( methodunichr10, new String[] {"v"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_ord");
		}
		//Function test:ord
		{
		IMethod methodord11;
			IModelElement[] moduleChilds = module.getChildren();
			methodord11 = ModelTestUtils.getAssertMethod( moduleChilds, "ord", 1 );
			ModelTestUtils.assertParameterNames( methodord11, new String[] {"c"} );
		}
		//Class test
		{
		IType classTestBase_Mapping12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBase_Mapping12 = ModelTestUtils.getAssertClass( moduleChilds, "TestBase_Mapping" );
			{
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase_Mapping12Childs, "pass_enctest");
			}
			{
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase_Mapping12Childs, "pass_dectest");
			}
			{
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase_Mapping12Childs, "supmaps");
			}
			//Function test:__init__
			{
			IMethod method__init__13;
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				method__init__13 = ModelTestUtils.getAssertMethod( classTestBase_Mapping12Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__13, new String[] {"self", "args", "kw"} );
			}
			//Function test:test_mapping_file
			{
			IMethod methodtest_mapping_file14;
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				methodtest_mapping_file14 = ModelTestUtils.getAssertMethod( classTestBase_Mapping12Childs, "test_mapping_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mapping_file14, new String[] {"self"} );
			}
			//Function test:test_mapping_supplemental
			{
			IMethod methodtest_mapping_supplemental15;
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				methodtest_mapping_supplemental15 = ModelTestUtils.getAssertMethod( classTestBase_Mapping12Childs, "test_mapping_supplemental", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mapping_supplemental15, new String[] {"self"} );
			}
			//Function test:_testpoint
			{
			IMethod method_testpoint16;
				IModelElement[] classTestBase_Mapping12Childs = classTestBase_Mapping12.getChildren();
				method_testpoint16 = ModelTestUtils.getAssertMethod( classTestBase_Mapping12Childs, "_testpoint", 3 );
				ModelTestUtils.assertParameterNames( method_testpoint16, new String[] {"self", "csetch", "unich"} );
			}
		}
		//Function test:load_teststring
		{
		IMethod methodload_teststring17;
			IModelElement[] moduleChilds = module.getChildren();
			methodload_teststring17 = ModelTestUtils.getAssertMethod( moduleChilds, "load_teststring", 1 );
			ModelTestUtils.assertParameterNames( methodload_teststring17, new String[] {"encoding"} );
		}
		//Function test:register_skip_expected
		{
		IMethod methodregister_skip_expected18;
			IModelElement[] moduleChilds = module.getChildren();
			methodregister_skip_expected18 = ModelTestUtils.getAssertMethod( moduleChilds, "register_skip_expected", 1 );
			ModelTestUtils.assertParameterNames( methodregister_skip_expected18, new String[] {"cases"} );
		}

	}
	public void testModelGen311( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pprint.py"));

		assertNotNull("Module test_pprint.py not found", module);
		assertEquals("test_pprint.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "uni");
		}
		//Function test:uni
		{
		IMethod methoduni0;
			IModelElement[] moduleChilds = module.getChildren();
			methoduni0 = ModelTestUtils.getAssertMethod( moduleChilds, "uni", 1 );
			ModelTestUtils.assertParameterNames( methoduni0, new String[] {"x"} );
		}
		//Class test
		{
		IType classlist21;
			IModelElement[] moduleChilds = module.getChildren();
			classlist21 = ModelTestUtils.getAssertClass( moduleChilds, "list2" );
		}
		//Class test
		{
		IType classlist32;
			IModelElement[] moduleChilds = module.getChildren();
			classlist32 = ModelTestUtils.getAssertClass( moduleChilds, "list3" );
			//Function test:__repr__
			{
			IMethod method__repr__3;
				IModelElement[] classlist32Childs = classlist32.getChildren();
				method__repr__3 = ModelTestUtils.getAssertMethod( classlist32Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtuple24;
			IModelElement[] moduleChilds = module.getChildren();
			classtuple24 = ModelTestUtils.getAssertClass( moduleChilds, "tuple2" );
		}
		//Class test
		{
		IType classtuple35;
			IModelElement[] moduleChilds = module.getChildren();
			classtuple35 = ModelTestUtils.getAssertClass( moduleChilds, "tuple3" );
			//Function test:__repr__
			{
			IMethod method__repr__6;
				IModelElement[] classtuple35Childs = classtuple35.getChildren();
				method__repr__6 = ModelTestUtils.getAssertMethod( classtuple35Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classdict27;
			IModelElement[] moduleChilds = module.getChildren();
			classdict27 = ModelTestUtils.getAssertClass( moduleChilds, "dict2" );
		}
		//Class test
		{
		IType classdict38;
			IModelElement[] moduleChilds = module.getChildren();
			classdict38 = ModelTestUtils.getAssertClass( moduleChilds, "dict3" );
			//Function test:__repr__
			{
			IMethod method__repr__9;
				IModelElement[] classdict38Childs = classdict38.getChildren();
				method__repr__9 = ModelTestUtils.getAssertMethod( classdict38Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classQueryTestCase10;
			IModelElement[] moduleChilds = module.getChildren();
			classQueryTestCase10 = ModelTestUtils.getAssertClass( moduleChilds, "QueryTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp11;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodsetUp11 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp11, new String[] {"self"} );
			}
			{
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQueryTestCase10Childs, "a");
			}
			{
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQueryTestCase10Childs, "b");
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic13;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_basic13 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic13, new String[] {"self"} );
			}
			//Function test:test_knotted
			{
			IMethod methodtest_knotted14;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_knotted14 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_knotted", 1 );
				ModelTestUtils.assertParameterNames( methodtest_knotted14, new String[] {"self"} );
			}
			{
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQueryTestCase10Childs, "d");
			}
			//Function test:test_unreadable
			{
			IMethod methodtest_unreadable16;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_unreadable16 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_unreadable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unreadable16, new String[] {"self"} );
			}
			//Function test:test_same_as_repr
			{
			IMethod methodtest_same_as_repr17;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_same_as_repr17 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_same_as_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_same_as_repr17, new String[] {"self"} );
			}
			//Function test:test_basic_line_wrap
			{
			IMethod methodtest_basic_line_wrap18;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_basic_line_wrap18 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_basic_line_wrap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_line_wrap18, new String[] {"self"} );
			}
			//Function test:test_subclassing
			{
			IMethod methodtest_subclassing19;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_subclassing19 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_subclassing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclassing19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDottedPrettyPrinter20;
			IModelElement[] moduleChilds = module.getChildren();
			classDottedPrettyPrinter20 = ModelTestUtils.getAssertClass( moduleChilds, "DottedPrettyPrinter" );
			//Function test:format
			{
			IMethod methodformat21;
				IModelElement[] classDottedPrettyPrinter20Childs = classDottedPrettyPrinter20.getChildren();
				methodformat21 = ModelTestUtils.getAssertMethod( classDottedPrettyPrinter20Childs, "format", 5 );
				ModelTestUtils.assertParameterNames( methodformat21, new String[] {"self", "object", "context", "maxlevels", "level"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen312( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_copy.py"));

		assertNotNull("Module test_copy.py not found", module);
		assertEquals("test_copy.py", module.getElementName());
		
		//Class test
		{
		IType classTestCopy0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopy0 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopy" );
			//Function test:test_exceptions
			{
			IMethod methodtest_exceptions1;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_exceptions1 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_exceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exceptions1, new String[] {"self"} );
			}
			//Function test:test_copy_basic
			{
			IMethod methodtest_copy_basic2;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_basic2 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_basic2, new String[] {"self"} );
			}
			//Function test:test_copy_copy
			{
			IMethod methodtest_copy_copy3;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_copy3 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_copy3, new String[] {"self"} );
				//Class test
				{
				IType classC4;
					IModelElement[] methodtest_copy_copy3Childs = methodtest_copy_copy3.getChildren();
					classC4 = ModelTestUtils.getAssertClass( methodtest_copy_copy3Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__5;
						IModelElement[] classC4Childs = classC4.getChildren();
						method__init__5 = ModelTestUtils.getAssertMethod( classC4Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "foo"} );
					}
					{
						IModelElement[] classC4Childs = classC4.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC4Childs, "foo");
					}
					//Function test:__copy__
					{
					IMethod method__copy__7;
						IModelElement[] classC4Childs = classC4.getChildren();
						method__copy__7 = ModelTestUtils.getAssertMethod( classC4Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__7, new String[] {"self"} );
					}
				}
			}
			//Function test:test_copy_registry
			{
			IMethod methodtest_copy_registry8;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_registry8 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_registry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_registry8, new String[] {"self"} );
				//Class test
				{
				IType classC9;
					IModelElement[] methodtest_copy_registry8Childs = methodtest_copy_registry8.getChildren();
					classC9 = ModelTestUtils.getAssertClass( methodtest_copy_registry8Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__10;
						IModelElement[] classC9Childs = classC9.getChildren();
						method__new__10 = ModelTestUtils.getAssertMethod( classC9Childs, "__new__", 2 );
						ModelTestUtils.assertParameterNames( method__new__10, new String[] {"cls", "foo"} );
					}
				}
				//Function test:pickle_C
				{
				IMethod methodpickle_C11;
					IModelElement[] methodtest_copy_registry8Childs = methodtest_copy_registry8.getChildren();
					methodpickle_C11 = ModelTestUtils.getAssertMethod( methodtest_copy_registry8Childs, "pickle_C", 1 );
					ModelTestUtils.assertParameterNames( methodpickle_C11, new String[] {"obj"} );
				}
			}
			//Function test:test_copy_reduce_ex
			{
			IMethod methodtest_copy_reduce_ex12;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_reduce_ex12 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_reduce_ex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_reduce_ex12, new String[] {"self"} );
				//Class test
				{
				IType classC13;
					IModelElement[] methodtest_copy_reduce_ex12Childs = methodtest_copy_reduce_ex12.getChildren();
					classC13 = ModelTestUtils.getAssertClass( methodtest_copy_reduce_ex12Childs, "C" );
					//Function test:__reduce_ex__
					{
					IMethod method__reduce_ex__14;
						IModelElement[] classC13Childs = classC13.getChildren();
						method__reduce_ex__14 = ModelTestUtils.getAssertMethod( classC13Childs, "__reduce_ex__", 2 );
						ModelTestUtils.assertParameterNames( method__reduce_ex__14, new String[] {"self", "proto"} );
					}
					//Function test:__reduce__
					{
					IMethod method__reduce__15;
						IModelElement[] classC13Childs = classC13.getChildren();
						method__reduce__15 = ModelTestUtils.getAssertMethod( classC13Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__15, new String[] {"self"} );
					}
				}
			}
			//Function test:test_copy_reduce
			{
			IMethod methodtest_copy_reduce16;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_reduce16 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_reduce16, new String[] {"self"} );
				//Class test
				{
				IType classC17;
					IModelElement[] methodtest_copy_reduce16Childs = methodtest_copy_reduce16.getChildren();
					classC17 = ModelTestUtils.getAssertClass( methodtest_copy_reduce16Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__18;
						IModelElement[] classC17Childs = classC17.getChildren();
						method__reduce__18 = ModelTestUtils.getAssertMethod( classC17Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__18, new String[] {"self"} );
					}
				}
			}
			//Function test:test_copy_cant
			{
			IMethod methodtest_copy_cant19;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_cant19 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_cant", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_cant19, new String[] {"self"} );
				//Class test
				{
				IType classC20;
					IModelElement[] methodtest_copy_cant19Childs = methodtest_copy_cant19.getChildren();
					classC20 = ModelTestUtils.getAssertClass( methodtest_copy_cant19Childs, "C" );
					//Function test:__getattribute__
					{
					IMethod method__getattribute__21;
						IModelElement[] classC20Childs = classC20.getChildren();
						method__getattribute__21 = ModelTestUtils.getAssertMethod( classC20Childs, "__getattribute__", 2 );
						ModelTestUtils.assertParameterNames( method__getattribute__21, new String[] {"self", "name"} );
					}
				}
			}
			//Function test:test_copy_atomic
			{
			IMethod methodtest_copy_atomic22;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_atomic22 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_atomic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_atomic22, new String[] {"self"} );
				//Class test
				{
				IType classClassic23;
					IModelElement[] methodtest_copy_atomic22Childs = methodtest_copy_atomic22.getChildren();
					classClassic23 = ModelTestUtils.getAssertClass( methodtest_copy_atomic22Childs, "Classic" );
				}
				//Class test
				{
				IType classNewStyle24;
					IModelElement[] methodtest_copy_atomic22Childs = methodtest_copy_atomic22.getChildren();
					classNewStyle24 = ModelTestUtils.getAssertClass( methodtest_copy_atomic22Childs, "NewStyle" );
				}
				//Function test:f
				{
				IMethod methodf25;
					IModelElement[] methodtest_copy_atomic22Childs = methodtest_copy_atomic22.getChildren();
					methodf25 = ModelTestUtils.getAssertMethod( methodtest_copy_atomic22Childs, "f", 0 );
				}
			}
			//Function test:test_copy_list
			{
			IMethod methodtest_copy_list26;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_list26 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_list26, new String[] {"self"} );
			}
			//Function test:test_copy_tuple
			{
			IMethod methodtest_copy_tuple27;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_tuple27 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_tuple27, new String[] {"self"} );
			}
			//Function test:test_copy_dict
			{
			IMethod methodtest_copy_dict28;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_dict28 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_dict28, new String[] {"self"} );
			}
			//Function test:test_copy_inst_vanilla
			{
			IMethod methodtest_copy_inst_vanilla29;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_vanilla29 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_vanilla", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_vanilla29, new String[] {"self"} );
				//Class test
				{
				IType classC30;
					IModelElement[] methodtest_copy_inst_vanilla29Childs = methodtest_copy_inst_vanilla29.getChildren();
					classC30 = ModelTestUtils.getAssertClass( methodtest_copy_inst_vanilla29Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__31;
						IModelElement[] classC30Childs = classC30.getChildren();
						method__init__31 = ModelTestUtils.getAssertMethod( classC30Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__31, new String[] {"self", "foo"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__32;
						IModelElement[] classC30Childs = classC30.getChildren();
						method__cmp__32 = ModelTestUtils.getAssertMethod( classC30Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__32, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_copy
			{
			IMethod methodtest_copy_inst_copy33;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_copy33 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_copy33, new String[] {"self"} );
				//Class test
				{
				IType classC34;
					IModelElement[] methodtest_copy_inst_copy33Childs = methodtest_copy_inst_copy33.getChildren();
					classC34 = ModelTestUtils.getAssertClass( methodtest_copy_inst_copy33Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__35;
						IModelElement[] classC34Childs = classC34.getChildren();
						method__init__35 = ModelTestUtils.getAssertMethod( classC34Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__35, new String[] {"self", "foo"} );
					}
					//Function test:__copy__
					{
					IMethod method__copy__36;
						IModelElement[] classC34Childs = classC34.getChildren();
						method__copy__36 = ModelTestUtils.getAssertMethod( classC34Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__36, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__37;
						IModelElement[] classC34Childs = classC34.getChildren();
						method__cmp__37 = ModelTestUtils.getAssertMethod( classC34Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__37, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_getinitargs
			{
			IMethod methodtest_copy_inst_getinitargs38;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_getinitargs38 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_getinitargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_getinitargs38, new String[] {"self"} );
				//Class test
				{
				IType classC39;
					IModelElement[] methodtest_copy_inst_getinitargs38Childs = methodtest_copy_inst_getinitargs38.getChildren();
					classC39 = ModelTestUtils.getAssertClass( methodtest_copy_inst_getinitargs38Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__40;
						IModelElement[] classC39Childs = classC39.getChildren();
						method__init__40 = ModelTestUtils.getAssertMethod( classC39Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__40, new String[] {"self", "foo"} );
					}
					//Function test:__getinitargs__
					{
					IMethod method__getinitargs__41;
						IModelElement[] classC39Childs = classC39.getChildren();
						method__getinitargs__41 = ModelTestUtils.getAssertMethod( classC39Childs, "__getinitargs__", 1 );
						ModelTestUtils.assertParameterNames( method__getinitargs__41, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__42;
						IModelElement[] classC39Childs = classC39.getChildren();
						method__cmp__42 = ModelTestUtils.getAssertMethod( classC39Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__42, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_getstate
			{
			IMethod methodtest_copy_inst_getstate43;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_getstate43 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_getstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_getstate43, new String[] {"self"} );
				//Class test
				{
				IType classC44;
					IModelElement[] methodtest_copy_inst_getstate43Childs = methodtest_copy_inst_getstate43.getChildren();
					classC44 = ModelTestUtils.getAssertClass( methodtest_copy_inst_getstate43Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__45;
						IModelElement[] classC44Childs = classC44.getChildren();
						method__init__45 = ModelTestUtils.getAssertMethod( classC44Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__45, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__46;
						IModelElement[] classC44Childs = classC44.getChildren();
						method__getstate__46 = ModelTestUtils.getAssertMethod( classC44Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__46, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__47;
						IModelElement[] classC44Childs = classC44.getChildren();
						method__cmp__47 = ModelTestUtils.getAssertMethod( classC44Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__47, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_setstate
			{
			IMethod methodtest_copy_inst_setstate48;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_setstate48 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_setstate48, new String[] {"self"} );
				//Class test
				{
				IType classC49;
					IModelElement[] methodtest_copy_inst_setstate48Childs = methodtest_copy_inst_setstate48.getChildren();
					classC49 = ModelTestUtils.getAssertClass( methodtest_copy_inst_setstate48Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__50;
						IModelElement[] classC49Childs = classC49.getChildren();
						method__init__50 = ModelTestUtils.getAssertMethod( classC49Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__50, new String[] {"self", "foo"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__51;
						IModelElement[] classC49Childs = classC49.getChildren();
						method__setstate__51 = ModelTestUtils.getAssertMethod( classC49Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__51, new String[] {"self", "state"} );
					}
					{
						IModelElement[] classC49Childs = classC49.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC49Childs, "foo");
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__53;
						IModelElement[] classC49Childs = classC49.getChildren();
						method__cmp__53 = ModelTestUtils.getAssertMethod( classC49Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__53, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_getstate_setstate
			{
			IMethod methodtest_copy_inst_getstate_setstate54;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_getstate_setstate54 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_getstate_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_getstate_setstate54, new String[] {"self"} );
				//Class test
				{
				IType classC55;
					IModelElement[] methodtest_copy_inst_getstate_setstate54Childs = methodtest_copy_inst_getstate_setstate54.getChildren();
					classC55 = ModelTestUtils.getAssertClass( methodtest_copy_inst_getstate_setstate54Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__56;
						IModelElement[] classC55Childs = classC55.getChildren();
						method__init__56 = ModelTestUtils.getAssertMethod( classC55Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__56, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__57;
						IModelElement[] classC55Childs = classC55.getChildren();
						method__getstate__57 = ModelTestUtils.getAssertMethod( classC55Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__57, new String[] {"self"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__58;
						IModelElement[] classC55Childs = classC55.getChildren();
						method__setstate__58 = ModelTestUtils.getAssertMethod( classC55Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__58, new String[] {"self", "state"} );
					}
					{
						IModelElement[] classC55Childs = classC55.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC55Childs, "foo");
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__60;
						IModelElement[] classC55Childs = classC55.getChildren();
						method__cmp__60 = ModelTestUtils.getAssertMethod( classC55Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__60, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_classictype
			{
			IMethod methodtest_copy_classictype61;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_classictype61 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_classictype", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_classictype61, new String[] {"self"} );
			}
			//Function test:test_deepcopy_classictype
			{
			IMethod methodtest_deepcopy_classictype62;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_classictype62 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_classictype", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_classictype62, new String[] {"self"} );
			}
			//Function test:test_copy_classoverinstance
			{
			IMethod methodtest_copy_classoverinstance63;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_classoverinstance63 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_classoverinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_classoverinstance63, new String[] {"self"} );
				//Class test
				{
				IType classC64;
					IModelElement[] methodtest_copy_classoverinstance63Childs = methodtest_copy_classoverinstance63.getChildren();
					classC64 = ModelTestUtils.getAssertClass( methodtest_copy_classoverinstance63Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__65;
						IModelElement[] classC64Childs = classC64.getChildren();
						method__init__65 = ModelTestUtils.getAssertMethod( classC64Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__65, new String[] {"self", "v"} );
					}
					{
						IModelElement[] classC64Childs = classC64.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC64Childs, "v");
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__67;
						IModelElement[] classC64Childs = classC64.getChildren();
						method__cmp__67 = ModelTestUtils.getAssertMethod( classC64Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__67, new String[] {"self", "other"} );
					}
					//Function test:__copy__
					{
					IMethod method__copy__68;
						IModelElement[] classC64Childs = classC64.getChildren();
						method__copy__68 = ModelTestUtils.getAssertMethod( classC64Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__68, new String[] {"self"} );
					}
				}
			}
			//Function test:test_deepcopy_classoverinstance
			{
			IMethod methodtest_deepcopy_classoverinstance69;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_classoverinstance69 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_classoverinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_classoverinstance69, new String[] {"self"} );
				//Class test
				{
				IType classC70;
					IModelElement[] methodtest_deepcopy_classoverinstance69Childs = methodtest_deepcopy_classoverinstance69.getChildren();
					classC70 = ModelTestUtils.getAssertClass( methodtest_deepcopy_classoverinstance69Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__71;
						IModelElement[] classC70Childs = classC70.getChildren();
						method__init__71 = ModelTestUtils.getAssertMethod( classC70Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__71, new String[] {"self", "v"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__72;
						IModelElement[] classC70Childs = classC70.getChildren();
						method__cmp__72 = ModelTestUtils.getAssertMethod( classC70Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__72, new String[] {"self", "other"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__73;
						IModelElement[] classC70Childs = classC70.getChildren();
						method__deepcopy__73 = ModelTestUtils.getAssertMethod( classC70Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__73, new String[] {"self", "memo"} );
					}
				}
			}
			//Function test:test_copy_metaclassconfusion
			{
			IMethod methodtest_copy_metaclassconfusion74;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_metaclassconfusion74 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_metaclassconfusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_metaclassconfusion74, new String[] {"self"} );
				//Class test
				{
				IType classMyOwnError75;
					IModelElement[] methodtest_copy_metaclassconfusion74Childs = methodtest_copy_metaclassconfusion74.getChildren();
					classMyOwnError75 = ModelTestUtils.getAssertClass( methodtest_copy_metaclassconfusion74Childs, "MyOwnError" );
				}
				//Class test
				{
				IType classMeta76;
					IModelElement[] methodtest_copy_metaclassconfusion74Childs = methodtest_copy_metaclassconfusion74.getChildren();
					classMeta76 = ModelTestUtils.getAssertClass( methodtest_copy_metaclassconfusion74Childs, "Meta" );
					//Function test:__copy__
					{
					IMethod method__copy__77;
						IModelElement[] classMeta76Childs = classMeta76.getChildren();
						method__copy__77 = ModelTestUtils.getAssertMethod( classMeta76Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__77, new String[] {"cls"} );
					}
				}
				//Class test
				{
				IType classC78;
					IModelElement[] methodtest_copy_metaclassconfusion74Childs = methodtest_copy_metaclassconfusion74.getChildren();
					classC78 = ModelTestUtils.getAssertClass( methodtest_copy_metaclassconfusion74Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__79;
						IModelElement[] classC78Childs = classC78.getChildren();
						method__init__79 = ModelTestUtils.getAssertMethod( classC78Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__79, new String[] {"self", "tag"} );
					}
					{
						IModelElement[] classC78Childs = classC78.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC78Childs, "tag");
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__81;
						IModelElement[] classC78Childs = classC78.getChildren();
						method__cmp__81 = ModelTestUtils.getAssertMethod( classC78Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__81, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_metaclassconfusion
			{
			IMethod methodtest_deepcopy_metaclassconfusion82;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_metaclassconfusion82 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_metaclassconfusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_metaclassconfusion82, new String[] {"self"} );
				//Class test
				{
				IType classMyOwnError83;
					IModelElement[] methodtest_deepcopy_metaclassconfusion82Childs = methodtest_deepcopy_metaclassconfusion82.getChildren();
					classMyOwnError83 = ModelTestUtils.getAssertClass( methodtest_deepcopy_metaclassconfusion82Childs, "MyOwnError" );
				}
				//Class test
				{
				IType classMeta84;
					IModelElement[] methodtest_deepcopy_metaclassconfusion82Childs = methodtest_deepcopy_metaclassconfusion82.getChildren();
					classMeta84 = ModelTestUtils.getAssertClass( methodtest_deepcopy_metaclassconfusion82Childs, "Meta" );
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__85;
						IModelElement[] classMeta84Childs = classMeta84.getChildren();
						method__deepcopy__85 = ModelTestUtils.getAssertMethod( classMeta84Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__85, new String[] {"cls", "memo"} );
					}
				}
				//Class test
				{
				IType classC86;
					IModelElement[] methodtest_deepcopy_metaclassconfusion82Childs = methodtest_deepcopy_metaclassconfusion82.getChildren();
					classC86 = ModelTestUtils.getAssertClass( methodtest_deepcopy_metaclassconfusion82Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__87;
						IModelElement[] classC86Childs = classC86.getChildren();
						method__init__87 = ModelTestUtils.getAssertMethod( classC86Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__87, new String[] {"self", "tag"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__88;
						IModelElement[] classC86Childs = classC86.getChildren();
						method__cmp__88 = ModelTestUtils.getAssertMethod( classC86Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__88, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:_nomro
			{
			IMethod method_nomro89;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				method_nomro89 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "_nomro", 1 );
				ModelTestUtils.assertParameterNames( method_nomro89, new String[] {"self"} );
				//Class test
				{
				IType classC90;
					IModelElement[] method_nomro89Childs = method_nomro89.getChildren();
					classC90 = ModelTestUtils.getAssertClass( method_nomro89Childs, "C" );
					//Function test:__getattribute__
					{
					IMethod method__getattribute__91;
						IModelElement[] classC90Childs = classC90.getChildren();
						method__getattribute__91 = ModelTestUtils.getAssertMethod( classC90Childs, "__getattribute__", 2 );
						ModelTestUtils.assertParameterNames( method__getattribute__91, new String[] {"self", "attr"} );
					}
				}
				//Class test
				{
				IType classD92;
					IModelElement[] method_nomro89Childs = method_nomro89.getChildren();
					classD92 = ModelTestUtils.getAssertClass( method_nomro89Childs, "D" );
				}
			}
			//Function test:test_copy_mro
			{
			IMethod methodtest_copy_mro93;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_mro93 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_mro", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_mro93, new String[] {"self"} );
			}
			//Function test:test_deepcopy_mro
			{
			IMethod methodtest_deepcopy_mro94;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_mro94 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_mro", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_mro94, new String[] {"self"} );
			}
			//Function test:test_deepcopy_basic
			{
			IMethod methodtest_deepcopy_basic95;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_basic95 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_basic95, new String[] {"self"} );
			}
			//Function test:test_deepcopy_memo
			{
			IMethod methodtest_deepcopy_memo96;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_memo96 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_memo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_memo96, new String[] {"self"} );
			}
			//Function test:test_deepcopy_issubclass
			{
			IMethod methodtest_deepcopy_issubclass97;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_issubclass97 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_issubclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_issubclass97, new String[] {"self"} );
				//Class test
				{
				IType classMeta98;
					IModelElement[] methodtest_deepcopy_issubclass97Childs = methodtest_deepcopy_issubclass97.getChildren();
					classMeta98 = ModelTestUtils.getAssertClass( methodtest_deepcopy_issubclass97Childs, "Meta" );
				}
				//Class test
				{
				IType classC99;
					IModelElement[] methodtest_deepcopy_issubclass97Childs = methodtest_deepcopy_issubclass97.getChildren();
					classC99 = ModelTestUtils.getAssertClass( methodtest_deepcopy_issubclass97Childs, "C" );
				}
			}
			//Function test:test_deepcopy_deepcopy
			{
			IMethod methodtest_deepcopy_deepcopy100;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_deepcopy100 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_deepcopy100, new String[] {"self"} );
				//Class test
				{
				IType classC101;
					IModelElement[] methodtest_deepcopy_deepcopy100Childs = methodtest_deepcopy_deepcopy100.getChildren();
					classC101 = ModelTestUtils.getAssertClass( methodtest_deepcopy_deepcopy100Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__102;
						IModelElement[] classC101Childs = classC101.getChildren();
						method__init__102 = ModelTestUtils.getAssertMethod( classC101Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__102, new String[] {"self", "foo"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__103;
						IModelElement[] classC101Childs = classC101.getChildren();
						method__deepcopy__103 = ModelTestUtils.getAssertMethod( classC101Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__103, new String[] {"self", "memo"} );
					}
				}
			}
			//Function test:test_deepcopy_registry
			{
			IMethod methodtest_deepcopy_registry104;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_registry104 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_registry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_registry104, new String[] {"self"} );
				//Class test
				{
				IType classC105;
					IModelElement[] methodtest_deepcopy_registry104Childs = methodtest_deepcopy_registry104.getChildren();
					classC105 = ModelTestUtils.getAssertClass( methodtest_deepcopy_registry104Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__106;
						IModelElement[] classC105Childs = classC105.getChildren();
						method__new__106 = ModelTestUtils.getAssertMethod( classC105Childs, "__new__", 2 );
						ModelTestUtils.assertParameterNames( method__new__106, new String[] {"cls", "foo"} );
					}
				}
				//Function test:pickle_C
				{
				IMethod methodpickle_C107;
					IModelElement[] methodtest_deepcopy_registry104Childs = methodtest_deepcopy_registry104.getChildren();
					methodpickle_C107 = ModelTestUtils.getAssertMethod( methodtest_deepcopy_registry104Childs, "pickle_C", 1 );
					ModelTestUtils.assertParameterNames( methodpickle_C107, new String[] {"obj"} );
				}
			}
			//Function test:test_deepcopy_reduce_ex
			{
			IMethod methodtest_deepcopy_reduce_ex108;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reduce_ex108 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reduce_ex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reduce_ex108, new String[] {"self"} );
				//Class test
				{
				IType classC109;
					IModelElement[] methodtest_deepcopy_reduce_ex108Childs = methodtest_deepcopy_reduce_ex108.getChildren();
					classC109 = ModelTestUtils.getAssertClass( methodtest_deepcopy_reduce_ex108Childs, "C" );
					//Function test:__reduce_ex__
					{
					IMethod method__reduce_ex__110;
						IModelElement[] classC109Childs = classC109.getChildren();
						method__reduce_ex__110 = ModelTestUtils.getAssertMethod( classC109Childs, "__reduce_ex__", 2 );
						ModelTestUtils.assertParameterNames( method__reduce_ex__110, new String[] {"self", "proto"} );
					}
					//Function test:__reduce__
					{
					IMethod method__reduce__111;
						IModelElement[] classC109Childs = classC109.getChildren();
						method__reduce__111 = ModelTestUtils.getAssertMethod( classC109Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__111, new String[] {"self"} );
					}
				}
			}
			//Function test:test_deepcopy_reduce
			{
			IMethod methodtest_deepcopy_reduce112;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reduce112 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reduce112, new String[] {"self"} );
				//Class test
				{
				IType classC113;
					IModelElement[] methodtest_deepcopy_reduce112Childs = methodtest_deepcopy_reduce112.getChildren();
					classC113 = ModelTestUtils.getAssertClass( methodtest_deepcopy_reduce112Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__114;
						IModelElement[] classC113Childs = classC113.getChildren();
						method__reduce__114 = ModelTestUtils.getAssertMethod( classC113Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__114, new String[] {"self"} );
					}
				}
			}
			//Function test:test_deepcopy_cant
			{
			IMethod methodtest_deepcopy_cant115;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_cant115 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_cant", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_cant115, new String[] {"self"} );
				//Class test
				{
				IType classC116;
					IModelElement[] methodtest_deepcopy_cant115Childs = methodtest_deepcopy_cant115.getChildren();
					classC116 = ModelTestUtils.getAssertClass( methodtest_deepcopy_cant115Childs, "C" );
					//Function test:__getattribute__
					{
					IMethod method__getattribute__117;
						IModelElement[] classC116Childs = classC116.getChildren();
						method__getattribute__117 = ModelTestUtils.getAssertMethod( classC116Childs, "__getattribute__", 2 );
						ModelTestUtils.assertParameterNames( method__getattribute__117, new String[] {"self", "name"} );
					}
				}
			}
			//Function test:test_deepcopy_atomic
			{
			IMethod methodtest_deepcopy_atomic118;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_atomic118 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_atomic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_atomic118, new String[] {"self"} );
				//Class test
				{
				IType classClassic119;
					IModelElement[] methodtest_deepcopy_atomic118Childs = methodtest_deepcopy_atomic118.getChildren();
					classClassic119 = ModelTestUtils.getAssertClass( methodtest_deepcopy_atomic118Childs, "Classic" );
				}
				//Class test
				{
				IType classNewStyle120;
					IModelElement[] methodtest_deepcopy_atomic118Childs = methodtest_deepcopy_atomic118.getChildren();
					classNewStyle120 = ModelTestUtils.getAssertClass( methodtest_deepcopy_atomic118Childs, "NewStyle" );
				}
				//Function test:f
				{
				IMethod methodf121;
					IModelElement[] methodtest_deepcopy_atomic118Childs = methodtest_deepcopy_atomic118.getChildren();
					methodf121 = ModelTestUtils.getAssertMethod( methodtest_deepcopy_atomic118Childs, "f", 0 );
				}
			}
			//Function test:test_deepcopy_list
			{
			IMethod methodtest_deepcopy_list122;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_list122 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_list122, new String[] {"self"} );
			}
			//Function test:test_deepcopy_reflexive_list
			{
			IMethod methodtest_deepcopy_reflexive_list123;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_list123 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_list123, new String[] {"self"} );
			}
			//Function test:test_deepcopy_tuple
			{
			IMethod methodtest_deepcopy_tuple124;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_tuple124 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_tuple124, new String[] {"self"} );
			}
			//Function test:test_deepcopy_reflexive_tuple
			{
			IMethod methodtest_deepcopy_reflexive_tuple125;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_tuple125 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_tuple125, new String[] {"self"} );
			}
			//Function test:test_deepcopy_dict
			{
			IMethod methodtest_deepcopy_dict126;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_dict126 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_dict126, new String[] {"self"} );
			}
			//Function test:test_deepcopy_reflexive_dict
			{
			IMethod methodtest_deepcopy_reflexive_dict127;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_dict127 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_dict127, new String[] {"self"} );
			}
			//Function test:test_deepcopy_keepalive
			{
			IMethod methodtest_deepcopy_keepalive128;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_keepalive128 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_keepalive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_keepalive128, new String[] {"self"} );
			}
			//Function test:test_deepcopy_inst_vanilla
			{
			IMethod methodtest_deepcopy_inst_vanilla129;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_vanilla129 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_vanilla", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_vanilla129, new String[] {"self"} );
				//Class test
				{
				IType classC130;
					IModelElement[] methodtest_deepcopy_inst_vanilla129Childs = methodtest_deepcopy_inst_vanilla129.getChildren();
					classC130 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_vanilla129Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__131;
						IModelElement[] classC130Childs = classC130.getChildren();
						method__init__131 = ModelTestUtils.getAssertMethod( classC130Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__131, new String[] {"self", "foo"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__132;
						IModelElement[] classC130Childs = classC130.getChildren();
						method__cmp__132 = ModelTestUtils.getAssertMethod( classC130Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__132, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_deepcopy
			{
			IMethod methodtest_deepcopy_inst_deepcopy133;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_deepcopy133 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_deepcopy133, new String[] {"self"} );
				//Class test
				{
				IType classC134;
					IModelElement[] methodtest_deepcopy_inst_deepcopy133Childs = methodtest_deepcopy_inst_deepcopy133.getChildren();
					classC134 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_deepcopy133Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__135;
						IModelElement[] classC134Childs = classC134.getChildren();
						method__init__135 = ModelTestUtils.getAssertMethod( classC134Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__135, new String[] {"self", "foo"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__136;
						IModelElement[] classC134Childs = classC134.getChildren();
						method__deepcopy__136 = ModelTestUtils.getAssertMethod( classC134Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__136, new String[] {"self", "memo"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__137;
						IModelElement[] classC134Childs = classC134.getChildren();
						method__cmp__137 = ModelTestUtils.getAssertMethod( classC134Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__137, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_getinitargs
			{
			IMethod methodtest_deepcopy_inst_getinitargs138;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_getinitargs138 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_getinitargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_getinitargs138, new String[] {"self"} );
				//Class test
				{
				IType classC139;
					IModelElement[] methodtest_deepcopy_inst_getinitargs138Childs = methodtest_deepcopy_inst_getinitargs138.getChildren();
					classC139 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_getinitargs138Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__140;
						IModelElement[] classC139Childs = classC139.getChildren();
						method__init__140 = ModelTestUtils.getAssertMethod( classC139Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__140, new String[] {"self", "foo"} );
					}
					//Function test:__getinitargs__
					{
					IMethod method__getinitargs__141;
						IModelElement[] classC139Childs = classC139.getChildren();
						method__getinitargs__141 = ModelTestUtils.getAssertMethod( classC139Childs, "__getinitargs__", 1 );
						ModelTestUtils.assertParameterNames( method__getinitargs__141, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__142;
						IModelElement[] classC139Childs = classC139.getChildren();
						method__cmp__142 = ModelTestUtils.getAssertMethod( classC139Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__142, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_getstate
			{
			IMethod methodtest_deepcopy_inst_getstate143;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_getstate143 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_getstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_getstate143, new String[] {"self"} );
				//Class test
				{
				IType classC144;
					IModelElement[] methodtest_deepcopy_inst_getstate143Childs = methodtest_deepcopy_inst_getstate143.getChildren();
					classC144 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_getstate143Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__145;
						IModelElement[] classC144Childs = classC144.getChildren();
						method__init__145 = ModelTestUtils.getAssertMethod( classC144Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__145, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__146;
						IModelElement[] classC144Childs = classC144.getChildren();
						method__getstate__146 = ModelTestUtils.getAssertMethod( classC144Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__146, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__147;
						IModelElement[] classC144Childs = classC144.getChildren();
						method__cmp__147 = ModelTestUtils.getAssertMethod( classC144Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__147, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_setstate
			{
			IMethod methodtest_deepcopy_inst_setstate148;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_setstate148 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_setstate148, new String[] {"self"} );
				//Class test
				{
				IType classC149;
					IModelElement[] methodtest_deepcopy_inst_setstate148Childs = methodtest_deepcopy_inst_setstate148.getChildren();
					classC149 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_setstate148Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__150;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__init__150 = ModelTestUtils.getAssertMethod( classC149Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__150, new String[] {"self", "foo"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__151;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__setstate__151 = ModelTestUtils.getAssertMethod( classC149Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__151, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__152;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__cmp__152 = ModelTestUtils.getAssertMethod( classC149Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__152, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_getstate_setstate
			{
			IMethod methodtest_deepcopy_inst_getstate_setstate153;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_getstate_setstate153 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_getstate_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_getstate_setstate153, new String[] {"self"} );
				//Class test
				{
				IType classC154;
					IModelElement[] methodtest_deepcopy_inst_getstate_setstate153Childs = methodtest_deepcopy_inst_getstate_setstate153.getChildren();
					classC154 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_getstate_setstate153Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__155;
						IModelElement[] classC154Childs = classC154.getChildren();
						method__init__155 = ModelTestUtils.getAssertMethod( classC154Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__155, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__156;
						IModelElement[] classC154Childs = classC154.getChildren();
						method__getstate__156 = ModelTestUtils.getAssertMethod( classC154Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__156, new String[] {"self"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__157;
						IModelElement[] classC154Childs = classC154.getChildren();
						method__setstate__157 = ModelTestUtils.getAssertMethod( classC154Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__157, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__158;
						IModelElement[] classC154Childs = classC154.getChildren();
						method__cmp__158 = ModelTestUtils.getAssertMethod( classC154Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__158, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_reflexive_inst
			{
			IMethod methodtest_deepcopy_reflexive_inst159;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_inst159 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_inst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_inst159, new String[] {"self"} );
				//Class test
				{
				IType classC160;
					IModelElement[] methodtest_deepcopy_reflexive_inst159Childs = methodtest_deepcopy_reflexive_inst159.getChildren();
					classC160 = ModelTestUtils.getAssertClass( methodtest_deepcopy_reflexive_inst159Childs, "C" );
				}
			}
			//Function test:test_reconstruct_string
			{
			IMethod methodtest_reconstruct_string161;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_string161 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_string161, new String[] {"self"} );
				//Class test
				{
				IType classC162;
					IModelElement[] methodtest_reconstruct_string161Childs = methodtest_reconstruct_string161.getChildren();
					classC162 = ModelTestUtils.getAssertClass( methodtest_reconstruct_string161Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__163;
						IModelElement[] classC162Childs = classC162.getChildren();
						method__reduce__163 = ModelTestUtils.getAssertMethod( classC162Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__163, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reconstruct_nostate
			{
			IMethod methodtest_reconstruct_nostate164;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_nostate164 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_nostate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_nostate164, new String[] {"self"} );
				//Class test
				{
				IType classC165;
					IModelElement[] methodtest_reconstruct_nostate164Childs = methodtest_reconstruct_nostate164.getChildren();
					classC165 = ModelTestUtils.getAssertClass( methodtest_reconstruct_nostate164Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__166;
						IModelElement[] classC165Childs = classC165.getChildren();
						method__reduce__166 = ModelTestUtils.getAssertMethod( classC165Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__166, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reconstruct_state
			{
			IMethod methodtest_reconstruct_state167;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_state167 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_state", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_state167, new String[] {"self"} );
				//Class test
				{
				IType classC168;
					IModelElement[] methodtest_reconstruct_state167Childs = methodtest_reconstruct_state167.getChildren();
					classC168 = ModelTestUtils.getAssertClass( methodtest_reconstruct_state167Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__169;
						IModelElement[] classC168Childs = classC168.getChildren();
						method__reduce__169 = ModelTestUtils.getAssertMethod( classC168Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__169, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__170;
						IModelElement[] classC168Childs = classC168.getChildren();
						method__cmp__170 = ModelTestUtils.getAssertMethod( classC168Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__170, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reconstruct_state_setstate
			{
			IMethod methodtest_reconstruct_state_setstate171;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_state_setstate171 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_state_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_state_setstate171, new String[] {"self"} );
				//Class test
				{
				IType classC172;
					IModelElement[] methodtest_reconstruct_state_setstate171Childs = methodtest_reconstruct_state_setstate171.getChildren();
					classC172 = ModelTestUtils.getAssertClass( methodtest_reconstruct_state_setstate171Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__173;
						IModelElement[] classC172Childs = classC172.getChildren();
						method__reduce__173 = ModelTestUtils.getAssertMethod( classC172Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__173, new String[] {"self"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__174;
						IModelElement[] classC172Childs = classC172.getChildren();
						method__setstate__174 = ModelTestUtils.getAssertMethod( classC172Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__174, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__175;
						IModelElement[] classC172Childs = classC172.getChildren();
						method__cmp__175 = ModelTestUtils.getAssertMethod( classC172Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__175, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reconstruct_reflexive
			{
			IMethod methodtest_reconstruct_reflexive176;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_reflexive176 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_reflexive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_reflexive176, new String[] {"self"} );
				//Class test
				{
				IType classC177;
					IModelElement[] methodtest_reconstruct_reflexive176Childs = methodtest_reconstruct_reflexive176.getChildren();
					classC177 = ModelTestUtils.getAssertClass( methodtest_reconstruct_reflexive176Childs, "C" );
				}
			}
			//Function test:test_reduce_4tuple
			{
			IMethod methodtest_reduce_4tuple178;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reduce_4tuple178 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reduce_4tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_4tuple178, new String[] {"self"} );
				//Class test
				{
				IType classC179;
					IModelElement[] methodtest_reduce_4tuple178Childs = methodtest_reduce_4tuple178.getChildren();
					classC179 = ModelTestUtils.getAssertClass( methodtest_reduce_4tuple178Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__180;
						IModelElement[] classC179Childs = classC179.getChildren();
						method__reduce__180 = ModelTestUtils.getAssertMethod( classC179Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__180, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__181;
						IModelElement[] classC179Childs = classC179.getChildren();
						method__cmp__181 = ModelTestUtils.getAssertMethod( classC179Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__181, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reduce_5tuple
			{
			IMethod methodtest_reduce_5tuple182;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reduce_5tuple182 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reduce_5tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_5tuple182, new String[] {"self"} );
				//Class test
				{
				IType classC183;
					IModelElement[] methodtest_reduce_5tuple182Childs = methodtest_reduce_5tuple182.getChildren();
					classC183 = ModelTestUtils.getAssertClass( methodtest_reduce_5tuple182Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__184;
						IModelElement[] classC183Childs = classC183.getChildren();
						method__reduce__184 = ModelTestUtils.getAssertMethod( classC183Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__184, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__185;
						IModelElement[] classC183Childs = classC183.getChildren();
						method__cmp__185 = ModelTestUtils.getAssertMethod( classC183Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__185, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_slots
			{
			IMethod methodtest_copy_slots186;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_slots186 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_slots186, new String[] {"self"} );
				//Class test
				{
				IType classC187;
					IModelElement[] methodtest_copy_slots186Childs = methodtest_copy_slots186.getChildren();
					classC187 = ModelTestUtils.getAssertClass( methodtest_copy_slots186Childs, "C" );
				}
			}
			//Function test:test_deepcopy_slots
			{
			IMethod methodtest_deepcopy_slots188;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_slots188 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_slots188, new String[] {"self"} );
				//Class test
				{
				IType classC189;
					IModelElement[] methodtest_deepcopy_slots188Childs = methodtest_deepcopy_slots188.getChildren();
					classC189 = ModelTestUtils.getAssertClass( methodtest_deepcopy_slots188Childs, "C" );
				}
			}
			//Function test:test_copy_list_subclass
			{
			IMethod methodtest_copy_list_subclass190;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_list_subclass190 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_list_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_list_subclass190, new String[] {"self"} );
				//Class test
				{
				IType classC191;
					IModelElement[] methodtest_copy_list_subclass190Childs = methodtest_copy_list_subclass190.getChildren();
					classC191 = ModelTestUtils.getAssertClass( methodtest_copy_list_subclass190Childs, "C" );
				}
			}
			//Function test:test_deepcopy_list_subclass
			{
			IMethod methodtest_deepcopy_list_subclass192;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_list_subclass192 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_list_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_list_subclass192, new String[] {"self"} );
				//Class test
				{
				IType classC193;
					IModelElement[] methodtest_deepcopy_list_subclass192Childs = methodtest_deepcopy_list_subclass192.getChildren();
					classC193 = ModelTestUtils.getAssertClass( methodtest_deepcopy_list_subclass192Childs, "C" );
				}
			}
			//Function test:test_copy_tuple_subclass
			{
			IMethod methodtest_copy_tuple_subclass194;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_tuple_subclass194 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_tuple_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_tuple_subclass194, new String[] {"self"} );
				//Class test
				{
				IType classC195;
					IModelElement[] methodtest_copy_tuple_subclass194Childs = methodtest_copy_tuple_subclass194.getChildren();
					classC195 = ModelTestUtils.getAssertClass( methodtest_copy_tuple_subclass194Childs, "C" );
				}
			}
			//Function test:test_deepcopy_tuple_subclass
			{
			IMethod methodtest_deepcopy_tuple_subclass196;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_tuple_subclass196 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_tuple_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_tuple_subclass196, new String[] {"self"} );
				//Class test
				{
				IType classC197;
					IModelElement[] methodtest_deepcopy_tuple_subclass196Childs = methodtest_deepcopy_tuple_subclass196.getChildren();
					classC197 = ModelTestUtils.getAssertClass( methodtest_deepcopy_tuple_subclass196Childs, "C" );
				}
			}
			//Function test:test_getstate_exc
			{
			IMethod methodtest_getstate_exc198;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_getstate_exc198 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_getstate_exc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getstate_exc198, new String[] {"self"} );
				//Class test
				{
				IType classEvilState199;
					IModelElement[] methodtest_getstate_exc198Childs = methodtest_getstate_exc198.getChildren();
					classEvilState199 = ModelTestUtils.getAssertClass( methodtest_getstate_exc198Childs, "EvilState" );
					//Function test:__getstate__
					{
					IMethod method__getstate__200;
						IModelElement[] classEvilState199Childs = classEvilState199.getChildren();
						method__getstate__200 = ModelTestUtils.getAssertMethod( classEvilState199Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__200, new String[] {"self"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main201;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main201 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen313( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_weakref.py"));

		assertNotNull("Module test_weakref.py not found", module);
		assertEquals("test_weakref.py", module.getElementName());
		
		//Class test
		{
		IType classC0;
			IModelElement[] moduleChilds = module.getChildren();
			classC0 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:method
			{
			IMethod methodmethod1;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodmethod1 = ModelTestUtils.getAssertMethod( classC0Childs, "method", 1 );
				ModelTestUtils.assertParameterNames( methodmethod1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCallable2;
			IModelElement[] moduleChilds = module.getChildren();
			classCallable2 = ModelTestUtils.getAssertClass( moduleChilds, "Callable" );
			{
				IModelElement[] classCallable2Childs = classCallable2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCallable2Childs, "bar");
			}
			//Function test:__call__
			{
			IMethod method__call__3;
				IModelElement[] classCallable2Childs = classCallable2.getChildren();
				method__call__3 = ModelTestUtils.getAssertMethod( classCallable2Childs, "__call__", 2 );
				ModelTestUtils.assertParameterNames( method__call__3, new String[] {"self", "x"} );
			}
			{
				IModelElement[] classCallable2Childs = classCallable2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCallable2Childs, "bar");
			}
		}
		//Function test:create_function
		{
		IMethod methodcreate_function5;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_function5 = ModelTestUtils.getAssertMethod( moduleChilds, "create_function", 0 );
			//Function test:f
			{
			IMethod methodf6;
				IModelElement[] methodcreate_function5Childs = methodcreate_function5.getChildren();
				methodf6 = ModelTestUtils.getAssertMethod( methodcreate_function5Childs, "f", 0 );
			}
		}
		//Function test:create_bound_method
		{
		IMethod methodcreate_bound_method7;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_bound_method7 = ModelTestUtils.getAssertMethod( moduleChilds, "create_bound_method", 0 );
		}
		//Function test:create_unbound_method
		{
		IMethod methodcreate_unbound_method8;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_unbound_method8 = ModelTestUtils.getAssertMethod( moduleChilds, "create_unbound_method", 0 );
		}
		//Class test
		{
		IType classTestBase9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBase9 = ModelTestUtils.getAssertClass( moduleChilds, "TestBase" );
			//Function test:setUp
			{
			IMethod methodsetUp10;
				IModelElement[] classTestBase9Childs = classTestBase9.getChildren();
				methodsetUp10 = ModelTestUtils.getAssertMethod( classTestBase9Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp10, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBase9Childs = classTestBase9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase9Childs, "cbcalled");
			}
			//Function test:callback
			{
			IMethod methodcallback12;
				IModelElement[] classTestBase9Childs = classTestBase9.getChildren();
				methodcallback12 = ModelTestUtils.getAssertMethod( classTestBase9Childs, "callback", 2 );
				ModelTestUtils.assertParameterNames( methodcallback12, new String[] {"self", "ref"} );
			}
		}
		//Class test
		{
		IType classReferencesTestCase13;
			IModelElement[] moduleChilds = module.getChildren();
			classReferencesTestCase13 = ModelTestUtils.getAssertClass( moduleChilds, "ReferencesTestCase" );
			//Function test:test_basic_ref
			{
			IMethod methodtest_basic_ref14;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_basic_ref14 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_basic_ref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_ref14, new String[] {"self"} );
			}
			//Function test:test_basic_callback
			{
			IMethod methodtest_basic_callback15;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_basic_callback15 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_basic_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_callback15, new String[] {"self"} );
			}
			//Function test:test_multiple_callbacks
			{
			IMethod methodtest_multiple_callbacks16;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_multiple_callbacks16 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_multiple_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiple_callbacks16, new String[] {"self"} );
			}
			//Function test:test_multiple_selfref_callbacks
			{
			IMethod methodtest_multiple_selfref_callbacks17;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_multiple_selfref_callbacks17 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_multiple_selfref_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiple_selfref_callbacks17, new String[] {"self"} );
				//Function test:callback
				{
				IMethod methodcallback18;
					IModelElement[] methodtest_multiple_selfref_callbacks17Childs = methodtest_multiple_selfref_callbacks17.getChildren();
					methodcallback18 = ModelTestUtils.getAssertMethod( methodtest_multiple_selfref_callbacks17Childs, "callback", 2 );
					ModelTestUtils.assertParameterNames( methodcallback18, new String[] {"object", "self"} );
				}
			}
			{
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReferencesTestCase13Childs, "ref");
			}
			//Function test:test_proxy_ref
			{
			IMethod methodtest_proxy_ref20;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_proxy_ref20 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_proxy_ref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proxy_ref20, new String[] {"self"} );
				//Function test:check
				{
				IMethod methodcheck21;
					IModelElement[] methodtest_proxy_ref20Childs = methodtest_proxy_ref20.getChildren();
					methodcheck21 = ModelTestUtils.getAssertMethod( methodtest_proxy_ref20Childs, "check", 1 );
					ModelTestUtils.assertParameterNames( methodcheck21, new String[] {"proxy"} );
				}
			}
			//Function test:check_basic_ref
			{
			IMethod methodcheck_basic_ref22;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodcheck_basic_ref22 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "check_basic_ref", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_basic_ref22, new String[] {"self", "factory"} );
			}
			//Function test:check_basic_callback
			{
			IMethod methodcheck_basic_callback23;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodcheck_basic_callback23 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "check_basic_callback", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_basic_callback23, new String[] {"self", "factory"} );
			}
			{
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReferencesTestCase13Childs, "cbcalled");
			}
			//Function test:test_ref_reuse
			{
			IMethod methodtest_ref_reuse25;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_ref_reuse25 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_ref_reuse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ref_reuse25, new String[] {"self"} );
			}
			//Function test:test_proxy_reuse
			{
			IMethod methodtest_proxy_reuse26;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_proxy_reuse26 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_proxy_reuse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proxy_reuse26, new String[] {"self"} );
			}
			//Function test:test_basic_proxy
			{
			IMethod methodtest_basic_proxy27;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_basic_proxy27 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_basic_proxy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_proxy27, new String[] {"self"} );
			}
			//Function test:test_shared_ref_without_callback
			{
			IMethod methodtest_shared_ref_without_callback28;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_shared_ref_without_callback28 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_shared_ref_without_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shared_ref_without_callback28, new String[] {"self"} );
			}
			//Function test:test_shared_proxy_without_callback
			{
			IMethod methodtest_shared_proxy_without_callback29;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_shared_proxy_without_callback29 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_shared_proxy_without_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shared_proxy_without_callback29, new String[] {"self"} );
			}
			//Function test:check_shared_without_callback
			{
			IMethod methodcheck_shared_without_callback30;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodcheck_shared_without_callback30 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "check_shared_without_callback", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_shared_without_callback30, new String[] {"self", "makeref"} );
			}
			//Function test:test_callable_proxy
			{
			IMethod methodtest_callable_proxy31;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callable_proxy31 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callable_proxy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callable_proxy31, new String[] {"self"} );
			}
			//Function test:check_proxy
			{
			IMethod methodcheck_proxy32;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodcheck_proxy32 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "check_proxy", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_proxy32, new String[] {"self", "o", "proxy"} );
			}
			//Function test:test_proxy_deletion
			{
			IMethod methodtest_proxy_deletion33;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_proxy_deletion33 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_proxy_deletion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proxy_deletion33, new String[] {"self"} );
				//Class test
				{
				IType classFoo34;
					IModelElement[] methodtest_proxy_deletion33Childs = methodtest_proxy_deletion33.getChildren();
					classFoo34 = ModelTestUtils.getAssertClass( methodtest_proxy_deletion33Childs, "Foo" );
					//Function test:__delitem__
					{
					IMethod method__delitem__35;
						IModelElement[] classFoo34Childs = classFoo34.getChildren();
						method__delitem__35 = ModelTestUtils.getAssertMethod( classFoo34Childs, "__delitem__", 2 );
						ModelTestUtils.assertParameterNames( method__delitem__35, new String[] {"self", "accessor"} );
					}
					{
						IModelElement[] classFoo34Childs = classFoo34.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classFoo34Childs, "result");
					}
				}
			}
			//Function test:test_getweakrefcount
			{
			IMethod methodtest_getweakrefcount37;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_getweakrefcount37 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_getweakrefcount", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getweakrefcount37, new String[] {"self"} );
			}
			//Function test:test_getweakrefs
			{
			IMethod methodtest_getweakrefs38;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_getweakrefs38 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_getweakrefs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getweakrefs38, new String[] {"self"} );
			}
			//Function test:test_newstyle_number_ops
			{
			IMethod methodtest_newstyle_number_ops39;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_newstyle_number_ops39 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_newstyle_number_ops", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newstyle_number_ops39, new String[] {"self"} );
				//Class test
				{
				IType classF40;
					IModelElement[] methodtest_newstyle_number_ops39Childs = methodtest_newstyle_number_ops39.getChildren();
					classF40 = ModelTestUtils.getAssertClass( methodtest_newstyle_number_ops39Childs, "F" );
				}
			}
			//Function test:test_callbacks_protected
			{
			IMethod methodtest_callbacks_protected41;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callbacks_protected41 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callbacks_protected", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callbacks_protected41, new String[] {"self"} );
				//Class test
				{
				IType classBogusError42;
					IModelElement[] methodtest_callbacks_protected41Childs = methodtest_callbacks_protected41.getChildren();
					classBogusError42 = ModelTestUtils.getAssertClass( methodtest_callbacks_protected41Childs, "BogusError" );
				}
				//Function test:remove
				{
				IMethod methodremove43;
					IModelElement[] methodtest_callbacks_protected41Childs = methodtest_callbacks_protected41.getChildren();
					methodremove43 = ModelTestUtils.getAssertMethod( methodtest_callbacks_protected41Childs, "remove", 1 );
					ModelTestUtils.assertParameterNames( methodremove43, new String[] {"k"} );
				}
				//Function test:encapsulate
				{
				IMethod methodencapsulate44;
					IModelElement[] methodtest_callbacks_protected41Childs = methodtest_callbacks_protected41.getChildren();
					methodencapsulate44 = ModelTestUtils.getAssertMethod( methodtest_callbacks_protected41Childs, "encapsulate", 0 );
				}
			}
			//Function test:test_sf_bug_840829
			{
			IMethod methodtest_sf_bug_84082945;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_sf_bug_84082945 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_sf_bug_840829", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sf_bug_84082945, new String[] {"self"} );
				//Class test
				{
				IType classC46;
					IModelElement[] methodtest_sf_bug_84082945Childs = methodtest_sf_bug_84082945.getChildren();
					classC46 = ModelTestUtils.getAssertClass( methodtest_sf_bug_84082945Childs, "C" );
				}
			}
			//Function test:test_callback_in_cycle_1
			{
			IMethod methodtest_callback_in_cycle_147;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callback_in_cycle_147 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callback_in_cycle_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_147, new String[] {"self"} );
				//Class test
				{
				IType classJ48;
					IModelElement[] methodtest_callback_in_cycle_147Childs = methodtest_callback_in_cycle_147.getChildren();
					classJ48 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_147Childs, "J" );
				}
				//Class test
				{
				IType classII49;
					IModelElement[] methodtest_callback_in_cycle_147Childs = methodtest_callback_in_cycle_147.getChildren();
					classII49 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_147Childs, "II" );
					//Function test:acallback
					{
					IMethod methodacallback50;
						IModelElement[] classII49Childs = classII49.getChildren();
						methodacallback50 = ModelTestUtils.getAssertMethod( classII49Childs, "acallback", 2 );
						ModelTestUtils.assertParameterNames( methodacallback50, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_callback_in_cycle_2
			{
			IMethod methodtest_callback_in_cycle_251;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callback_in_cycle_251 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callback_in_cycle_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_251, new String[] {"self"} );
				//Class test
				{
				IType classJ52;
					IModelElement[] methodtest_callback_in_cycle_251Childs = methodtest_callback_in_cycle_251.getChildren();
					classJ52 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_251Childs, "J" );
				}
				//Class test
				{
				IType classII53;
					IModelElement[] methodtest_callback_in_cycle_251Childs = methodtest_callback_in_cycle_251.getChildren();
					classII53 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_251Childs, "II" );
					//Function test:acallback
					{
					IMethod methodacallback54;
						IModelElement[] classII53Childs = classII53.getChildren();
						methodacallback54 = ModelTestUtils.getAssertMethod( classII53Childs, "acallback", 2 );
						ModelTestUtils.assertParameterNames( methodacallback54, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_callback_in_cycle_3
			{
			IMethod methodtest_callback_in_cycle_355;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callback_in_cycle_355 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callback_in_cycle_3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_355, new String[] {"self"} );
				//Class test
				{
				IType classC56;
					IModelElement[] methodtest_callback_in_cycle_355Childs = methodtest_callback_in_cycle_355.getChildren();
					classC56 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_355Childs, "C" );
					//Function test:cb
					{
					IMethod methodcb57;
						IModelElement[] classC56Childs = classC56.getChildren();
						methodcb57 = ModelTestUtils.getAssertMethod( classC56Childs, "cb", 2 );
						ModelTestUtils.assertParameterNames( methodcb57, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_callback_in_cycle_4
			{
			IMethod methodtest_callback_in_cycle_458;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callback_in_cycle_458 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callback_in_cycle_4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_458, new String[] {"self"} );
				//Class test
				{
				IType classC59;
					IModelElement[] methodtest_callback_in_cycle_458Childs = methodtest_callback_in_cycle_458.getChildren();
					classC59 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_458Childs, "C" );
					//Function test:cb
					{
					IMethod methodcb60;
						IModelElement[] classC59Childs = classC59.getChildren();
						methodcb60 = ModelTestUtils.getAssertMethod( classC59Childs, "cb", 2 );
						ModelTestUtils.assertParameterNames( methodcb60, new String[] {"self", "ignore"} );
					}
				}
				//Class test
				{
				IType classD61;
					IModelElement[] methodtest_callback_in_cycle_458Childs = methodtest_callback_in_cycle_458.getChildren();
					classD61 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_458Childs, "D" );
				}
			}
			//Function test:test_callback_in_cycle_resurrection
			{
			IMethod methodtest_callback_in_cycle_resurrection62;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callback_in_cycle_resurrection62 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callback_in_cycle_resurrection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_resurrection62, new String[] {"self"} );
				//Class test
				{
				IType classC63;
					IModelElement[] methodtest_callback_in_cycle_resurrection62Childs = methodtest_callback_in_cycle_resurrection62.getChildren();
					classC63 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_resurrection62Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__64;
						IModelElement[] classC63Childs = classC63.getChildren();
						method__init__64 = ModelTestUtils.getAssertMethod( classC63Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__64, new String[] {"self", "value"} );
					}
					{
						IModelElement[] classC63Childs = classC63.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC63Childs, "attribute");
					}
					//Function test:acallback
					{
					IMethod methodacallback66;
						IModelElement[] classC63Childs = classC63.getChildren();
						methodacallback66 = ModelTestUtils.getAssertMethod( classC63Childs, "acallback", 2 );
						ModelTestUtils.assertParameterNames( methodacallback66, new String[] {"self", "ignore"} );
					}
				}
				//Function test:C_went_away
				{
				IMethod methodC_went_away67;
					IModelElement[] methodtest_callback_in_cycle_resurrection62Childs = methodtest_callback_in_cycle_resurrection62.getChildren();
					methodC_went_away67 = ModelTestUtils.getAssertMethod( methodtest_callback_in_cycle_resurrection62Childs, "C_went_away", 1 );
					ModelTestUtils.assertParameterNames( methodC_went_away67, new String[] {"ignore"} );
				}
			}
			//Function test:test_callbacks_on_callback
			{
			IMethod methodtest_callbacks_on_callback68;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_callbacks_on_callback68 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_callbacks_on_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callbacks_on_callback68, new String[] {"self"} );
				//Function test:safe_callback
				{
				IMethod methodsafe_callback69;
					IModelElement[] methodtest_callbacks_on_callback68Childs = methodtest_callbacks_on_callback68.getChildren();
					methodsafe_callback69 = ModelTestUtils.getAssertMethod( methodtest_callbacks_on_callback68Childs, "safe_callback", 1 );
					ModelTestUtils.assertParameterNames( methodsafe_callback69, new String[] {"ignore"} );
				}
				//Class test
				{
				IType classC70;
					IModelElement[] methodtest_callbacks_on_callback68Childs = methodtest_callbacks_on_callback68.getChildren();
					classC70 = ModelTestUtils.getAssertClass( methodtest_callbacks_on_callback68Childs, "C" );
					//Function test:cb
					{
					IMethod methodcb71;
						IModelElement[] classC70Childs = classC70.getChildren();
						methodcb71 = ModelTestUtils.getAssertMethod( classC70Childs, "cb", 2 );
						ModelTestUtils.assertParameterNames( methodcb71, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_gc_during_ref_creation
			{
			IMethod methodtest_gc_during_ref_creation72;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_gc_during_ref_creation72 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_gc_during_ref_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc_during_ref_creation72, new String[] {"self"} );
			}
			//Function test:test_gc_during_proxy_creation
			{
			IMethod methodtest_gc_during_proxy_creation73;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodtest_gc_during_proxy_creation73 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "test_gc_during_proxy_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc_during_proxy_creation73, new String[] {"self"} );
			}
			//Function test:check_gc_during_creation
			{
			IMethod methodcheck_gc_during_creation74;
				IModelElement[] classReferencesTestCase13Childs = classReferencesTestCase13.getChildren();
				methodcheck_gc_during_creation74 = ModelTestUtils.getAssertMethod( classReferencesTestCase13Childs, "check_gc_during_creation", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_gc_during_creation74, new String[] {"self", "makeref"} );
				//Class test
				{
				IType classA75;
					IModelElement[] methodcheck_gc_during_creation74Childs = methodcheck_gc_during_creation74.getChildren();
					classA75 = ModelTestUtils.getAssertClass( methodcheck_gc_during_creation74Childs, "A" );
				}
				//Function test:callback
				{
				IMethod methodcallback76;
					IModelElement[] methodcheck_gc_during_creation74Childs = methodcheck_gc_during_creation74.getChildren();
					methodcallback76 = ModelTestUtils.getAssertMethod( methodcheck_gc_during_creation74Childs, "callback", 1 );
					ModelTestUtils.assertParameterNames( methodcallback76, new String[] {"args"} );
				}
			}
		}
		//Class test
		{
		IType classSubclassableWeakrefTestCase77;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassableWeakrefTestCase77 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassableWeakrefTestCase" );
			//Function test:test_subclass_refs
			{
			IMethod methodtest_subclass_refs78;
				IModelElement[] classSubclassableWeakrefTestCase77Childs = classSubclassableWeakrefTestCase77.getChildren();
				methodtest_subclass_refs78 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase77Childs, "test_subclass_refs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs78, new String[] {"self"} );
				//Class test
				{
				IType classMyRef79;
					IModelElement[] methodtest_subclass_refs78Childs = methodtest_subclass_refs78.getChildren();
					classMyRef79 = ModelTestUtils.getAssertClass( methodtest_subclass_refs78Childs, "MyRef" );
					//Function test:__init__
					{
					IMethod method__init__80;
						IModelElement[] classMyRef79Childs = classMyRef79.getChildren();
						method__init__80 = ModelTestUtils.getAssertMethod( classMyRef79Childs, "__init__", 4 );
						ModelTestUtils.assertParameterNames( method__init__80, new String[] {"self", "ob", "callback", "value"} );
					}
					{
						IModelElement[] classMyRef79Childs = classMyRef79.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classMyRef79Childs, "value");
					}
					//Function test:__call__
					{
					IMethod method__call__82;
						IModelElement[] classMyRef79Childs = classMyRef79.getChildren();
						method__call__82 = ModelTestUtils.getAssertMethod( classMyRef79Childs, "__call__", 1 );
						ModelTestUtils.assertParameterNames( method__call__82, new String[] {"self"} );
					}
					{
						IModelElement[] classMyRef79Childs = classMyRef79.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classMyRef79Childs, "called");
					}
				}
			}
			//Function test:test_subclass_refs_dont_replace_standard_refs
			{
			IMethod methodtest_subclass_refs_dont_replace_standard_refs84;
				IModelElement[] classSubclassableWeakrefTestCase77Childs = classSubclassableWeakrefTestCase77.getChildren();
				methodtest_subclass_refs_dont_replace_standard_refs84 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase77Childs, "test_subclass_refs_dont_replace_standard_refs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs_dont_replace_standard_refs84, new String[] {"self"} );
				//Class test
				{
				IType classMyRef85;
					IModelElement[] methodtest_subclass_refs_dont_replace_standard_refs84Childs = methodtest_subclass_refs_dont_replace_standard_refs84.getChildren();
					classMyRef85 = ModelTestUtils.getAssertClass( methodtest_subclass_refs_dont_replace_standard_refs84Childs, "MyRef" );
				}
			}
			//Function test:test_subclass_refs_dont_conflate_callbacks
			{
			IMethod methodtest_subclass_refs_dont_conflate_callbacks86;
				IModelElement[] classSubclassableWeakrefTestCase77Childs = classSubclassableWeakrefTestCase77.getChildren();
				methodtest_subclass_refs_dont_conflate_callbacks86 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase77Childs, "test_subclass_refs_dont_conflate_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs_dont_conflate_callbacks86, new String[] {"self"} );
				//Class test
				{
				IType classMyRef87;
					IModelElement[] methodtest_subclass_refs_dont_conflate_callbacks86Childs = methodtest_subclass_refs_dont_conflate_callbacks86.getChildren();
					classMyRef87 = ModelTestUtils.getAssertClass( methodtest_subclass_refs_dont_conflate_callbacks86Childs, "MyRef" );
				}
			}
			//Function test:test_subclass_refs_with_slots
			{
			IMethod methodtest_subclass_refs_with_slots88;
				IModelElement[] classSubclassableWeakrefTestCase77Childs = classSubclassableWeakrefTestCase77.getChildren();
				methodtest_subclass_refs_with_slots88 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase77Childs, "test_subclass_refs_with_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs_with_slots88, new String[] {"self"} );
				//Class test
				{
				IType classMyRef89;
					IModelElement[] methodtest_subclass_refs_with_slots88Childs = methodtest_subclass_refs_with_slots88.getChildren();
					classMyRef89 = ModelTestUtils.getAssertClass( methodtest_subclass_refs_with_slots88Childs, "MyRef" );
					//Function test:__new__
					{
					IMethod method__new__90;
						IModelElement[] classMyRef89Childs = classMyRef89.getChildren();
						method__new__90 = ModelTestUtils.getAssertMethod( classMyRef89Childs, "__new__", 5 );
						ModelTestUtils.assertParameterNames( method__new__90, new String[] {"type", "ob", "callback", "slot1", "slot2"} );
					}
					//Function test:__init__
					{
					IMethod method__init__91;
						IModelElement[] classMyRef89Childs = classMyRef89.getChildren();
						method__init__91 = ModelTestUtils.getAssertMethod( classMyRef89Childs, "__init__", 5 );
						ModelTestUtils.assertParameterNames( method__init__91, new String[] {"self", "ob", "callback", "slot1", "slot2"} );
					}
					{
						IModelElement[] classMyRef89Childs = classMyRef89.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classMyRef89Childs, "slot1");
					}
					{
						IModelElement[] classMyRef89Childs = classMyRef89.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classMyRef89Childs, "slot2");
					}
					//Function test:meth
					{
					IMethod methodmeth93;
						IModelElement[] classMyRef89Childs = classMyRef89.getChildren();
						methodmeth93 = ModelTestUtils.getAssertMethod( classMyRef89Childs, "meth", 1 );
						ModelTestUtils.assertParameterNames( methodmeth93, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classObject94;
			IModelElement[] moduleChilds = module.getChildren();
			classObject94 = ModelTestUtils.getAssertClass( moduleChilds, "Object" );
			//Function test:__init__
			{
			IMethod method__init__95;
				IModelElement[] classObject94Childs = classObject94.getChildren();
				method__init__95 = ModelTestUtils.getAssertMethod( classObject94Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__95, new String[] {"self", "arg"} );
			}
			{
				IModelElement[] classObject94Childs = classObject94.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classObject94Childs, "arg");
			}
			//Function test:__repr__
			{
			IMethod method__repr__97;
				IModelElement[] classObject94Childs = classObject94.getChildren();
				method__repr__97 = ModelTestUtils.getAssertMethod( classObject94Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__97, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMappingTestCase98;
			IModelElement[] moduleChilds = module.getChildren();
			classMappingTestCase98 = ModelTestUtils.getAssertClass( moduleChilds, "MappingTestCase" );
			{
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMappingTestCase98Childs, "COUNT");
			}
			//Function test:test_weak_values
			{
			IMethod methodtest_weak_values99;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_values99 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_values99, new String[] {"self"} );
			}
			//Function test:test_weak_keys
			{
			IMethod methodtest_weak_keys100;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keys100 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keys100, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_iters
			{
			IMethod methodtest_weak_keyed_iters101;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_iters101 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_iters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_iters101, new String[] {"self"} );
			}
			//Function test:test_weak_valued_iters
			{
			IMethod methodtest_weak_valued_iters102;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_valued_iters102 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_valued_iters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_iters102, new String[] {"self"} );
			}
			//Function test:check_iters
			{
			IMethod methodcheck_iters103;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodcheck_iters103 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "check_iters", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_iters103, new String[] {"self", "dict"} );
			}
			//Function test:test_make_weak_keyed_dict_from_dict
			{
			IMethod methodtest_make_weak_keyed_dict_from_dict104;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_make_weak_keyed_dict_from_dict104 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_make_weak_keyed_dict_from_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_make_weak_keyed_dict_from_dict104, new String[] {"self"} );
			}
			//Function test:test_make_weak_keyed_dict_from_weak_keyed_dict
			{
			IMethod methodtest_make_weak_keyed_dict_from_weak_keyed_dict105;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_make_weak_keyed_dict_from_weak_keyed_dict105 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_make_weak_keyed_dict_from_weak_keyed_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_make_weak_keyed_dict_from_weak_keyed_dict105, new String[] {"self"} );
			}
			//Function test:make_weak_keyed_dict
			{
			IMethod methodmake_weak_keyed_dict106;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodmake_weak_keyed_dict106 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "make_weak_keyed_dict", 1 );
				ModelTestUtils.assertParameterNames( methodmake_weak_keyed_dict106, new String[] {"self"} );
			}
			//Function test:make_weak_valued_dict
			{
			IMethod methodmake_weak_valued_dict107;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodmake_weak_valued_dict107 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "make_weak_valued_dict", 1 );
				ModelTestUtils.assertParameterNames( methodmake_weak_valued_dict107, new String[] {"self"} );
			}
			//Function test:check_popitem
			{
			IMethod methodcheck_popitem108;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodcheck_popitem108 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "check_popitem", 6 );
				ModelTestUtils.assertParameterNames( methodcheck_popitem108, new String[] {"self", "klass", "key1", "value1", "key2", "value2"} );
			}
			//Function test:test_weak_valued_dict_popitem
			{
			IMethod methodtest_weak_valued_dict_popitem109;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_valued_dict_popitem109 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_valued_dict_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_dict_popitem109, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_dict_popitem
			{
			IMethod methodtest_weak_keyed_dict_popitem110;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_dict_popitem110 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_dict_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_dict_popitem110, new String[] {"self"} );
			}
			//Function test:check_setdefault
			{
			IMethod methodcheck_setdefault111;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodcheck_setdefault111 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "check_setdefault", 5 );
				ModelTestUtils.assertParameterNames( methodcheck_setdefault111, new String[] {"self", "klass", "key", "value1", "value2"} );
			}
			//Function test:test_weak_valued_dict_setdefault
			{
			IMethod methodtest_weak_valued_dict_setdefault112;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_valued_dict_setdefault112 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_valued_dict_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_dict_setdefault112, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_dict_setdefault
			{
			IMethod methodtest_weak_keyed_dict_setdefault113;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_dict_setdefault113 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_dict_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_dict_setdefault113, new String[] {"self"} );
			}
			//Function test:check_update
			{
			IMethod methodcheck_update114;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodcheck_update114 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "check_update", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_update114, new String[] {"self", "klass", "dict"} );
			}
			//Function test:test_weak_valued_dict_update
			{
			IMethod methodtest_weak_valued_dict_update115;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_valued_dict_update115 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_valued_dict_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_dict_update115, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_dict_update
			{
			IMethod methodtest_weak_keyed_dict_update116;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_dict_update116 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_dict_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_dict_update116, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_delitem
			{
			IMethod methodtest_weak_keyed_delitem117;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_delitem117 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_delitem117, new String[] {"self"} );
			}
			//Function test:test_weak_valued_delitem
			{
			IMethod methodtest_weak_valued_delitem118;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_valued_delitem118 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_valued_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_delitem118, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_bad_delitem
			{
			IMethod methodtest_weak_keyed_bad_delitem119;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_bad_delitem119 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_bad_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_bad_delitem119, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_cascading_deletes
			{
			IMethod methodtest_weak_keyed_cascading_deletes120;
				IModelElement[] classMappingTestCase98Childs = classMappingTestCase98.getChildren();
				methodtest_weak_keyed_cascading_deletes120 = ModelTestUtils.getAssertMethod( classMappingTestCase98Childs, "test_weak_keyed_cascading_deletes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_cascading_deletes120, new String[] {"self"} );
				//Class test
				{
				IType classC121;
					IModelElement[] methodtest_weak_keyed_cascading_deletes120Childs = methodtest_weak_keyed_cascading_deletes120.getChildren();
					classC121 = ModelTestUtils.getAssertClass( methodtest_weak_keyed_cascading_deletes120Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__122;
						IModelElement[] classC121Childs = classC121.getChildren();
						method__init__122 = ModelTestUtils.getAssertMethod( classC121Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__122, new String[] {"self", "i"} );
					}
					{
						IModelElement[] classC121Childs = classC121.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC121Childs, "value");
					}
					//Function test:__hash__
					{
					IMethod method__hash__124;
						IModelElement[] classC121Childs = classC121.getChildren();
						method__hash__124 = ModelTestUtils.getAssertMethod( classC121Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__124, new String[] {"self"} );
					}
					//Function test:__eq__
					{
					IMethod method__eq__125;
						IModelElement[] classC121Childs = classC121.getChildren();
						method__eq__125 = ModelTestUtils.getAssertMethod( classC121Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__125, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Class test
		{
		IType classWeakValueDictionaryTestCase126;
			IModelElement[] moduleChilds = module.getChildren();
			classWeakValueDictionaryTestCase126 = ModelTestUtils.getAssertClass( moduleChilds, "WeakValueDictionaryTestCase" );
			{
				IModelElement[] classWeakValueDictionaryTestCase126Childs = classWeakValueDictionaryTestCase126.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakValueDictionaryTestCase126Childs, "__ref");
			}
			{
				IModelElement[] classWeakValueDictionaryTestCase126Childs = classWeakValueDictionaryTestCase126.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakValueDictionaryTestCase126Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference127;
				IModelElement[] classWeakValueDictionaryTestCase126Childs = classWeakValueDictionaryTestCase126.getChildren();
				method_reference127 = ModelTestUtils.getAssertMethod( classWeakValueDictionaryTestCase126Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference127, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWeakKeyDictionaryTestCase128;
			IModelElement[] moduleChilds = module.getChildren();
			classWeakKeyDictionaryTestCase128 = ModelTestUtils.getAssertClass( moduleChilds, "WeakKeyDictionaryTestCase" );
			{
				IModelElement[] classWeakKeyDictionaryTestCase128Childs = classWeakKeyDictionaryTestCase128.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakKeyDictionaryTestCase128Childs, "__ref");
			}
			{
				IModelElement[] classWeakKeyDictionaryTestCase128Childs = classWeakKeyDictionaryTestCase128.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakKeyDictionaryTestCase128Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference129;
				IModelElement[] classWeakKeyDictionaryTestCase128Childs = classWeakKeyDictionaryTestCase128.getChildren();
				method_reference129 = ModelTestUtils.getAssertMethod( classWeakKeyDictionaryTestCase128Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference129, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main130;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main130 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen314( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_minidom.py"));

		assertNotNull("Module test_minidom.py not found", module);
		assertEquals("test_minidom.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "base");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "base");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tstfile");
		}
		//Function test:confirm
		{
		IMethod methodconfirm0;
			IModelElement[] moduleChilds = module.getChildren();
			methodconfirm0 = ModelTestUtils.getAssertMethod( moduleChilds, "confirm", 2 );
			ModelTestUtils.assertParameterNames( methodconfirm0, new String[] {"test", "testname"} );
		}
		//Function test:testParseFromFile
		{
		IMethod methodtestParseFromFile1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseFromFile1 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseFromFile", 0 );
		}
		//Function test:testGetElementsByTagName
		{
		IMethod methodtestGetElementsByTagName2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetElementsByTagName2 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetElementsByTagName", 0 );
		}
		//Function test:testInsertBefore
		{
		IMethod methodtestInsertBefore3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestInsertBefore3 = ModelTestUtils.getAssertMethod( moduleChilds, "testInsertBefore", 0 );
		}
		//Function test:_create_fragment_test_nodes
		{
		IMethod method_create_fragment_test_nodes4;
			IModelElement[] moduleChilds = module.getChildren();
			method_create_fragment_test_nodes4 = ModelTestUtils.getAssertMethod( moduleChilds, "_create_fragment_test_nodes", 0 );
		}
		//Function test:testInsertBeforeFragment
		{
		IMethod methodtestInsertBeforeFragment5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestInsertBeforeFragment5 = ModelTestUtils.getAssertMethod( moduleChilds, "testInsertBeforeFragment", 0 );
		}
		//Function test:testAppendChild
		{
		IMethod methodtestAppendChild6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAppendChild6 = ModelTestUtils.getAssertMethod( moduleChilds, "testAppendChild", 0 );
		}
		//Function test:testAppendChildFragment
		{
		IMethod methodtestAppendChildFragment7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAppendChildFragment7 = ModelTestUtils.getAssertMethod( moduleChilds, "testAppendChildFragment", 0 );
		}
		//Function test:testReplaceChildFragment
		{
		IMethod methodtestReplaceChildFragment8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestReplaceChildFragment8 = ModelTestUtils.getAssertMethod( moduleChilds, "testReplaceChildFragment", 0 );
		}
		//Function test:testLegalChildren
		{
		IMethod methodtestLegalChildren9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestLegalChildren9 = ModelTestUtils.getAssertMethod( moduleChilds, "testLegalChildren", 0 );
		}
		//Function test:testNamedNodeMapSetItem
		{
		IMethod methodtestNamedNodeMapSetItem10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestNamedNodeMapSetItem10 = ModelTestUtils.getAssertMethod( moduleChilds, "testNamedNodeMapSetItem", 0 );
		}
		//Function test:testNonZero
		{
		IMethod methodtestNonZero11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestNonZero11 = ModelTestUtils.getAssertMethod( moduleChilds, "testNonZero", 0 );
		}
		//Function test:testUnlink
		{
		IMethod methodtestUnlink12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestUnlink12 = ModelTestUtils.getAssertMethod( moduleChilds, "testUnlink", 0 );
		}
		//Function test:testElement
		{
		IMethod methodtestElement13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestElement13 = ModelTestUtils.getAssertMethod( moduleChilds, "testElement", 0 );
		}
		//Function test:testAAA
		{
		IMethod methodtestAAA14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAAA14 = ModelTestUtils.getAssertMethod( moduleChilds, "testAAA", 0 );
		}
		//Function test:testAAB
		{
		IMethod methodtestAAB15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAAB15 = ModelTestUtils.getAssertMethod( moduleChilds, "testAAB", 0 );
		}
		//Function test:testAddAttr
		{
		IMethod methodtestAddAttr16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAddAttr16 = ModelTestUtils.getAssertMethod( moduleChilds, "testAddAttr", 0 );
		}
		//Function test:testDeleteAttr
		{
		IMethod methodtestDeleteAttr17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestDeleteAttr17 = ModelTestUtils.getAssertMethod( moduleChilds, "testDeleteAttr", 0 );
		}
		//Function test:testRemoveAttr
		{
		IMethod methodtestRemoveAttr18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRemoveAttr18 = ModelTestUtils.getAssertMethod( moduleChilds, "testRemoveAttr", 0 );
		}
		//Function test:testRemoveAttrNS
		{
		IMethod methodtestRemoveAttrNS19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRemoveAttrNS19 = ModelTestUtils.getAssertMethod( moduleChilds, "testRemoveAttrNS", 0 );
		}
		//Function test:testRemoveAttributeNode
		{
		IMethod methodtestRemoveAttributeNode20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRemoveAttributeNode20 = ModelTestUtils.getAssertMethod( moduleChilds, "testRemoveAttributeNode", 0 );
		}
		//Function test:testChangeAttr
		{
		IMethod methodtestChangeAttr21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestChangeAttr21 = ModelTestUtils.getAssertMethod( moduleChilds, "testChangeAttr", 0 );
		}
		//Function test:testGetAttrList
		{
		IMethod methodtestGetAttrList22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetAttrList22 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetAttrList", 0 );
		}
		//Function test:testGetAttrValues
		{
		IMethod methodtestGetAttrValues23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetAttrValues23 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetAttrValues", 0 );
		}
		//Function test:testGetAttrLength
		{
		IMethod methodtestGetAttrLength24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetAttrLength24 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetAttrLength", 0 );
		}
		//Function test:testGetAttribute
		{
		IMethod methodtestGetAttribute25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetAttribute25 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetAttribute", 0 );
		}
		//Function test:testGetAttributeNS
		{
		IMethod methodtestGetAttributeNS26;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetAttributeNS26 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetAttributeNS", 0 );
		}
		//Function test:testGetAttributeNode
		{
		IMethod methodtestGetAttributeNode27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetAttributeNode27 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetAttributeNode", 0 );
		}
		//Function test:testGetElementsByTagNameNS
		{
		IMethod methodtestGetElementsByTagNameNS28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetElementsByTagNameNS28 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetElementsByTagNameNS", 0 );
		}
		//Function test:get_empty_nodelist_from_elements_by_tagName_ns_helper
		{
		IMethod methodget_empty_nodelist_from_elements_by_tagName_ns_helper29;
			IModelElement[] moduleChilds = module.getChildren();
			methodget_empty_nodelist_from_elements_by_tagName_ns_helper29 = ModelTestUtils.getAssertMethod( moduleChilds, "get_empty_nodelist_from_elements_by_tagName_ns_helper", 3 );
			ModelTestUtils.assertParameterNames( methodget_empty_nodelist_from_elements_by_tagName_ns_helper29, new String[] {"doc", "nsuri", "lname"} );
		}
		//Function test:testGetEmptyNodeListFromElementsByTagNameNS
		{
		IMethod methodtestGetEmptyNodeListFromElementsByTagNameNS30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestGetEmptyNodeListFromElementsByTagNameNS30 = ModelTestUtils.getAssertMethod( moduleChilds, "testGetEmptyNodeListFromElementsByTagNameNS", 0 );
		}
		//Function test:testElementReprAndStr
		{
		IMethod methodtestElementReprAndStr31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestElementReprAndStr31 = ModelTestUtils.getAssertMethod( moduleChilds, "testElementReprAndStr", 0 );
		}
		//Function test:_testElementReprAndStrUnicode
		{
		IMethod method_testElementReprAndStrUnicode32;
			IModelElement[] moduleChilds = module.getChildren();
			method_testElementReprAndStrUnicode32 = ModelTestUtils.getAssertMethod( moduleChilds, "_testElementReprAndStrUnicode", 0 );
		}
		//Function test:_testElementReprAndStrUnicodeNS
		{
		IMethod method_testElementReprAndStrUnicodeNS33;
			IModelElement[] moduleChilds = module.getChildren();
			method_testElementReprAndStrUnicodeNS33 = ModelTestUtils.getAssertMethod( moduleChilds, "_testElementReprAndStrUnicodeNS", 0 );
		}
		//Function test:testAttributeRepr
		{
		IMethod methodtestAttributeRepr34;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttributeRepr34 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttributeRepr", 0 );
		}
		//Function test:testTextNodeRepr
		{
		IMethod methodtestTextNodeRepr35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestTextNodeRepr35 = ModelTestUtils.getAssertMethod( moduleChilds, "testTextNodeRepr", 0 );
		}
		//Function test:testWriteXML
		{
		IMethod methodtestWriteXML36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestWriteXML36 = ModelTestUtils.getAssertMethod( moduleChilds, "testWriteXML", 0 );
		}
		//Function test:testProcessingInstruction
		{
		IMethod methodtestProcessingInstruction37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestProcessingInstruction37 = ModelTestUtils.getAssertMethod( moduleChilds, "testProcessingInstruction", 0 );
		}
		//Function test:testProcessingInstructionRepr
		{
		IMethod methodtestProcessingInstructionRepr38;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestProcessingInstructionRepr38 = ModelTestUtils.getAssertMethod( moduleChilds, "testProcessingInstructionRepr", 0 );
		}
		//Function test:testTextRepr
		{
		IMethod methodtestTextRepr39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestTextRepr39 = ModelTestUtils.getAssertMethod( moduleChilds, "testTextRepr", 0 );
		}
		//Function test:testWriteText
		{
		IMethod methodtestWriteText40;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestWriteText40 = ModelTestUtils.getAssertMethod( moduleChilds, "testWriteText", 0 );
		}
		//Function test:testDocumentElement
		{
		IMethod methodtestDocumentElement41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestDocumentElement41 = ModelTestUtils.getAssertMethod( moduleChilds, "testDocumentElement", 0 );
		}
		//Function test:testTooManyDocumentElements
		{
		IMethod methodtestTooManyDocumentElements42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestTooManyDocumentElements42 = ModelTestUtils.getAssertMethod( moduleChilds, "testTooManyDocumentElements", 0 );
		}
		//Function test:testCreateElementNS
		{
		IMethod methodtestCreateElementNS43;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCreateElementNS43 = ModelTestUtils.getAssertMethod( moduleChilds, "testCreateElementNS", 0 );
		}
		//Function test:testCreateAttributeNS
		{
		IMethod methodtestCreateAttributeNS44;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCreateAttributeNS44 = ModelTestUtils.getAssertMethod( moduleChilds, "testCreateAttributeNS", 0 );
		}
		//Function test:testParse
		{
		IMethod methodtestParse45;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParse45 = ModelTestUtils.getAssertMethod( moduleChilds, "testParse", 0 );
		}
		//Function test:testParseString
		{
		IMethod methodtestParseString46;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseString46 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseString", 0 );
		}
		//Function test:testComment
		{
		IMethod methodtestComment47;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestComment47 = ModelTestUtils.getAssertMethod( moduleChilds, "testComment", 0 );
		}
		//Function test:testAttrListItem
		{
		IMethod methodtestAttrListItem48;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListItem48 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListItem", 0 );
		}
		//Function test:testAttrListItems
		{
		IMethod methodtestAttrListItems49;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListItems49 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListItems", 0 );
		}
		//Function test:testAttrListItemNS
		{
		IMethod methodtestAttrListItemNS50;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListItemNS50 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListItemNS", 0 );
		}
		//Function test:testAttrListKeys
		{
		IMethod methodtestAttrListKeys51;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListKeys51 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListKeys", 0 );
		}
		//Function test:testAttrListKeysNS
		{
		IMethod methodtestAttrListKeysNS52;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListKeysNS52 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListKeysNS", 0 );
		}
		//Function test:testRemoveNamedItem
		{
		IMethod methodtestRemoveNamedItem53;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRemoveNamedItem53 = ModelTestUtils.getAssertMethod( moduleChilds, "testRemoveNamedItem", 0 );
		}
		//Function test:testRemoveNamedItemNS
		{
		IMethod methodtestRemoveNamedItemNS54;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRemoveNamedItemNS54 = ModelTestUtils.getAssertMethod( moduleChilds, "testRemoveNamedItemNS", 0 );
		}
		//Function test:testAttrListValues
		{
		IMethod methodtestAttrListValues55;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListValues55 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListValues", 0 );
		}
		//Function test:testAttrListLength
		{
		IMethod methodtestAttrListLength56;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrListLength56 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrListLength", 0 );
		}
		//Function test:testAttrList__getitem__
		{
		IMethod methodtestAttrList__getitem__57;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrList__getitem__57 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrList__getitem__", 0 );
		}
		//Function test:testAttrList__setitem__
		{
		IMethod methodtestAttrList__setitem__58;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestAttrList__setitem__58 = ModelTestUtils.getAssertMethod( moduleChilds, "testAttrList__setitem__", 0 );
		}
		//Function test:testSetAttrValueandNodeValue
		{
		IMethod methodtestSetAttrValueandNodeValue59;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetAttrValueandNodeValue59 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetAttrValueandNodeValue", 0 );
		}
		//Function test:testParseElement
		{
		IMethod methodtestParseElement60;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseElement60 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseElement", 0 );
		}
		//Function test:testParseAttributes
		{
		IMethod methodtestParseAttributes61;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseAttributes61 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseAttributes", 0 );
		}
		//Function test:testParseElementNamespaces
		{
		IMethod methodtestParseElementNamespaces62;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseElementNamespaces62 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseElementNamespaces", 0 );
		}
		//Function test:testParseAttributeNamespaces
		{
		IMethod methodtestParseAttributeNamespaces63;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseAttributeNamespaces63 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseAttributeNamespaces", 0 );
		}
		//Function test:testParseProcessingInstructions
		{
		IMethod methodtestParseProcessingInstructions64;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParseProcessingInstructions64 = ModelTestUtils.getAssertMethod( moduleChilds, "testParseProcessingInstructions", 0 );
		}
		//Function test:testChildNodes
		{
		IMethod methodtestChildNodes65;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestChildNodes65 = ModelTestUtils.getAssertMethod( moduleChilds, "testChildNodes", 0 );
		}
		//Function test:testFirstChild
		{
		IMethod methodtestFirstChild66;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestFirstChild66 = ModelTestUtils.getAssertMethod( moduleChilds, "testFirstChild", 0 );
		}
		//Function test:testHasChildNodes
		{
		IMethod methodtestHasChildNodes67;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestHasChildNodes67 = ModelTestUtils.getAssertMethod( moduleChilds, "testHasChildNodes", 0 );
		}
		//Function test:testCloneElementShallow
		{
		IMethod methodtestCloneElementShallow68;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneElementShallow68 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneElementShallow", 0 );
		}
		//Function test:testCloneElementDeep
		{
		IMethod methodtestCloneElementDeep69;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneElementDeep69 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneElementDeep", 0 );
		}
		//Function test:_setupCloneElement
		{
		IMethod method_setupCloneElement70;
			IModelElement[] moduleChilds = module.getChildren();
			method_setupCloneElement70 = ModelTestUtils.getAssertMethod( moduleChilds, "_setupCloneElement", 1 );
			ModelTestUtils.assertParameterNames( method_setupCloneElement70, new String[] {"deep"} );
		}
		//Function test:_testCloneElementCopiesAttributes
		{
		IMethod method_testCloneElementCopiesAttributes71;
			IModelElement[] moduleChilds = module.getChildren();
			method_testCloneElementCopiesAttributes71 = ModelTestUtils.getAssertMethod( moduleChilds, "_testCloneElementCopiesAttributes", 3 );
			ModelTestUtils.assertParameterNames( method_testCloneElementCopiesAttributes71, new String[] {"e1", "e2", "test"} );
		}
		//Function test:testCloneDocumentShallow
		{
		IMethod methodtestCloneDocumentShallow72;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneDocumentShallow72 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneDocumentShallow", 0 );
		}
		//Function test:testCloneDocumentDeep
		{
		IMethod methodtestCloneDocumentDeep73;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneDocumentDeep73 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneDocumentDeep", 0 );
		}
		//Function test:testCloneDocumentTypeDeepOk
		{
		IMethod methodtestCloneDocumentTypeDeepOk74;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneDocumentTypeDeepOk74 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneDocumentTypeDeepOk", 0 );
		}
		//Function test:testCloneDocumentTypeDeepNotOk
		{
		IMethod methodtestCloneDocumentTypeDeepNotOk75;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneDocumentTypeDeepNotOk75 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneDocumentTypeDeepNotOk", 0 );
		}
		//Function test:testCloneDocumentTypeShallowOk
		{
		IMethod methodtestCloneDocumentTypeShallowOk76;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneDocumentTypeShallowOk76 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneDocumentTypeShallowOk", 0 );
		}
		//Function test:testCloneDocumentTypeShallowNotOk
		{
		IMethod methodtestCloneDocumentTypeShallowNotOk77;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneDocumentTypeShallowNotOk77 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneDocumentTypeShallowNotOk", 0 );
		}
		//Function test:check_import_document
		{
		IMethod methodcheck_import_document78;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_import_document78 = ModelTestUtils.getAssertMethod( moduleChilds, "check_import_document", 2 );
			ModelTestUtils.assertParameterNames( methodcheck_import_document78, new String[] {"deep", "testName"} );
		}
		//Function test:testImportDocumentShallow
		{
		IMethod methodtestImportDocumentShallow79;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestImportDocumentShallow79 = ModelTestUtils.getAssertMethod( moduleChilds, "testImportDocumentShallow", 0 );
		}
		//Function test:testImportDocumentDeep
		{
		IMethod methodtestImportDocumentDeep80;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestImportDocumentDeep80 = ModelTestUtils.getAssertMethod( moduleChilds, "testImportDocumentDeep", 0 );
		}
		//Function test:create_doc_without_doctype
		{
		IMethod methodcreate_doc_without_doctype81;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_doc_without_doctype81 = ModelTestUtils.getAssertMethod( moduleChilds, "create_doc_without_doctype", 1 );
			ModelTestUtils.assertParameterNames( methodcreate_doc_without_doctype81, new String[] {"doctype"} );
		}
		//Function test:create_nonempty_doctype
		{
		IMethod methodcreate_nonempty_doctype82;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_nonempty_doctype82 = ModelTestUtils.getAssertMethod( moduleChilds, "create_nonempty_doctype", 0 );
		}
		//Function test:create_doc_with_doctype
		{
		IMethod methodcreate_doc_with_doctype83;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_doc_with_doctype83 = ModelTestUtils.getAssertMethod( moduleChilds, "create_doc_with_doctype", 0 );
		}
		//Function test:testImportDocumentTypeShallow
		{
		IMethod methodtestImportDocumentTypeShallow84;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestImportDocumentTypeShallow84 = ModelTestUtils.getAssertMethod( moduleChilds, "testImportDocumentTypeShallow", 0 );
		}
		//Function test:testImportDocumentTypeDeep
		{
		IMethod methodtestImportDocumentTypeDeep85;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestImportDocumentTypeDeep85 = ModelTestUtils.getAssertMethod( moduleChilds, "testImportDocumentTypeDeep", 0 );
		}
		//Function test:check_clone_attribute
		{
		IMethod methodcheck_clone_attribute86;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_clone_attribute86 = ModelTestUtils.getAssertMethod( moduleChilds, "check_clone_attribute", 2 );
			ModelTestUtils.assertParameterNames( methodcheck_clone_attribute86, new String[] {"deep", "testName"} );
		}
		//Function test:testCloneAttributeShallow
		{
		IMethod methodtestCloneAttributeShallow87;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneAttributeShallow87 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneAttributeShallow", 0 );
		}
		//Function test:testCloneAttributeDeep
		{
		IMethod methodtestCloneAttributeDeep88;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestCloneAttributeDeep88 = ModelTestUtils.getAssertMethod( moduleChilds, "testCloneAttributeDeep", 0 );
		}
		//Function test:check_clone_pi
		{
		IMethod methodcheck_clone_pi89;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_clone_pi89 = ModelTestUtils.getAssertMethod( moduleChilds, "check_clone_pi", 2 );
			ModelTestUtils.assertParameterNames( methodcheck_clone_pi89, new String[] {"deep", "testName"} );
		}
		//Function test:testClonePIShallow
		{
		IMethod methodtestClonePIShallow90;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestClonePIShallow90 = ModelTestUtils.getAssertMethod( moduleChilds, "testClonePIShallow", 0 );
		}
		//Function test:testClonePIDeep
		{
		IMethod methodtestClonePIDeep91;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestClonePIDeep91 = ModelTestUtils.getAssertMethod( moduleChilds, "testClonePIDeep", 0 );
		}
		//Function test:testNormalize
		{
		IMethod methodtestNormalize92;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestNormalize92 = ModelTestUtils.getAssertMethod( moduleChilds, "testNormalize", 0 );
		}
		//Function test:testSiblings
		{
		IMethod methodtestSiblings93;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSiblings93 = ModelTestUtils.getAssertMethod( moduleChilds, "testSiblings", 0 );
		}
		//Function test:testParents
		{
		IMethod methodtestParents94;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestParents94 = ModelTestUtils.getAssertMethod( moduleChilds, "testParents", 0 );
		}
		//Function test:testNodeListItem
		{
		IMethod methodtestNodeListItem95;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestNodeListItem95 = ModelTestUtils.getAssertMethod( moduleChilds, "testNodeListItem", 0 );
		}
		//Function test:testSAX2DOM
		{
		IMethod methodtestSAX2DOM96;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSAX2DOM96 = ModelTestUtils.getAssertMethod( moduleChilds, "testSAX2DOM", 0 );
		}
		//Function test:testEncodings
		{
		IMethod methodtestEncodings97;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestEncodings97 = ModelTestUtils.getAssertMethod( moduleChilds, "testEncodings", 0 );
		}
		//Class test
		{
		IType classUserDataHandler98;
			IModelElement[] moduleChilds = module.getChildren();
			classUserDataHandler98 = ModelTestUtils.getAssertClass( moduleChilds, "UserDataHandler" );
			{
				IModelElement[] classUserDataHandler98Childs = classUserDataHandler98.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserDataHandler98Childs, "called");
			}
			//Function test:handle
			{
			IMethod methodhandle99;
				IModelElement[] classUserDataHandler98Childs = classUserDataHandler98.getChildren();
				methodhandle99 = ModelTestUtils.getAssertMethod( classUserDataHandler98Childs, "handle", 6 );
				ModelTestUtils.assertParameterNames( methodhandle99, new String[] {"self", "operation", "key", "data", "src", "dst"} );
			}
			{
				IModelElement[] classUserDataHandler98Childs = classUserDataHandler98.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserDataHandler98Childs, "called");
			}
		}
		//Function test:testUserData
		{
		IMethod methodtestUserData101;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestUserData101 = ModelTestUtils.getAssertMethod( moduleChilds, "testUserData", 0 );
		}
		//Function test:testRenameAttribute
		{
		IMethod methodtestRenameAttribute102;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRenameAttribute102 = ModelTestUtils.getAssertMethod( moduleChilds, "testRenameAttribute", 0 );
		}
		//Function test:testRenameElement
		{
		IMethod methodtestRenameElement103;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRenameElement103 = ModelTestUtils.getAssertMethod( moduleChilds, "testRenameElement", 0 );
		}
		//Function test:checkRenameNodeSharedConstraints
		{
		IMethod methodcheckRenameNodeSharedConstraints104;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheckRenameNodeSharedConstraints104 = ModelTestUtils.getAssertMethod( moduleChilds, "checkRenameNodeSharedConstraints", 2 );
			ModelTestUtils.assertParameterNames( methodcheckRenameNodeSharedConstraints104, new String[] {"doc", "node"} );
		}
		//Function test:testRenameOther
		{
		IMethod methodtestRenameOther105;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRenameOther105 = ModelTestUtils.getAssertMethod( moduleChilds, "testRenameOther", 0 );
		}
		//Function test:checkWholeText
		{
		IMethod methodcheckWholeText106;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheckWholeText106 = ModelTestUtils.getAssertMethod( moduleChilds, "checkWholeText", 2 );
			ModelTestUtils.assertParameterNames( methodcheckWholeText106, new String[] {"node", "s"} );
		}
		//Function test:testWholeText
		{
		IMethod methodtestWholeText107;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestWholeText107 = ModelTestUtils.getAssertMethod( moduleChilds, "testWholeText", 0 );
		}
		//Function test:testReplaceWholeText
		{
		IMethod methodtestReplaceWholeText108;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestReplaceWholeText108 = ModelTestUtils.getAssertMethod( moduleChilds, "testReplaceWholeText", 0 );
			//Function test:setup
			{
			IMethod methodsetup109;
				IModelElement[] methodtestReplaceWholeText108Childs = methodtestReplaceWholeText108.getChildren();
				methodsetup109 = ModelTestUtils.getAssertMethod( methodtestReplaceWholeText108Childs, "setup", 0 );
			}
		}
		//Function test:testSchemaType
		{
		IMethod methodtestSchemaType110;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSchemaType110 = ModelTestUtils.getAssertMethod( moduleChilds, "testSchemaType", 0 );
		}
		//Function test:testSetIdAttribute
		{
		IMethod methodtestSetIdAttribute111;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetIdAttribute111 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetIdAttribute", 0 );
		}
		//Function test:testSetIdAttributeNS
		{
		IMethod methodtestSetIdAttributeNS112;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetIdAttributeNS112 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetIdAttributeNS", 0 );
		}
		//Function test:testSetIdAttributeNode
		{
		IMethod methodtestSetIdAttributeNode113;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetIdAttributeNode113 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetIdAttributeNode", 0 );
		}
		//Function test:testPickledDocument
		{
		IMethod methodtestPickledDocument114;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestPickledDocument114 = ModelTestUtils.getAssertMethod( moduleChilds, "testPickledDocument", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "names");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "failed");
		}
		//Function test:check_allnodes
		{
		IMethod methodcheck_allnodes115;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_allnodes115 = ModelTestUtils.getAssertMethod( moduleChilds, "check_allnodes", 0 );
		}
		//Function test:check_allnodes
		{
		IMethod methodcheck_allnodes116;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_allnodes116 = ModelTestUtils.getAssertMethod( moduleChilds, "check_allnodes", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "func");
		}

	}
	public void testModelGen315( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("regrtest.py"));

		assertNotNull("Module regrtest.py not found", module);
		assertEquals("regrtest.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "newsoft");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "RESOURCE_NAMES");
		}
		//Function test:usage
		{
		IMethod methodusage0;
			IModelElement[] moduleChilds = module.getChildren();
			methodusage0 = ModelTestUtils.getAssertMethod( moduleChilds, "usage", 2 );
			ModelTestUtils.assertParameterNames( methodusage0, new String[] {"code", "msg"} );
		}
		//Function test:main
		{
		IMethod methodmain1;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain1 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 15 );
			ModelTestUtils.assertParameterNames( methodmain1, new String[] {"tests", "testdir", "verbose", "quiet", "generate", "exclude", "single", "randomize", "fromfile", "findleaks", "use_resources", "trace", "coverdir", "runleaks", "huntrleaks"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "STDTESTS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NOTTESTS");
		}
		//Function test:findtests
		{
		IMethod methodfindtests2;
			IModelElement[] moduleChilds = module.getChildren();
			methodfindtests2 = ModelTestUtils.getAssertMethod( moduleChilds, "findtests", 3 );
			ModelTestUtils.assertParameterNames( methodfindtests2, new String[] {"testdir", "stdtests", "nottests"} );
		}
		//Function test:runtest
		{
		IMethod methodruntest3;
			IModelElement[] moduleChilds = module.getChildren();
			methodruntest3 = ModelTestUtils.getAssertMethod( moduleChilds, "runtest", 6 );
			ModelTestUtils.assertParameterNames( methodruntest3, new String[] {"test", "generate", "verbose", "quiet", "testdir", "huntrleaks"} );
			//Function test:cleanup
			{
			IMethod methodcleanup4;
				IModelElement[] methodruntest3Childs = methodruntest3.getChildren();
				methodcleanup4 = ModelTestUtils.getAssertMethod( methodruntest3Childs, "cleanup", 0 );
			}
			//Function test:run_the_test
			{
			IMethod methodrun_the_test5;
				IModelElement[] methodruntest3Childs = methodruntest3.getChildren();
				methodrun_the_test5 = ModelTestUtils.getAssertMethod( methodruntest3Childs, "run_the_test", 0 );
			}
			//Function test:run_the_test
			{
			IMethod methodrun_the_test6;
				IModelElement[] methodruntest3Childs = methodruntest3.getChildren();
				methodrun_the_test6 = ModelTestUtils.getAssertMethod( methodruntest3Childs, "run_the_test", 0 );
			}
		}
		//Function test:reportdiff
		{
		IMethod methodreportdiff7;
			IModelElement[] moduleChilds = module.getChildren();
			methodreportdiff7 = ModelTestUtils.getAssertMethod( moduleChilds, "reportdiff", 2 );
			ModelTestUtils.assertParameterNames( methodreportdiff7, new String[] {"expected", "output"} );
			//Function test:pair
			{
			IMethod methodpair8;
				IModelElement[] methodreportdiff7Childs = methodreportdiff7.getChildren();
				methodpair8 = ModelTestUtils.getAssertMethod( methodreportdiff7Childs, "pair", 2 );
				ModelTestUtils.assertParameterNames( methodpair8, new String[] {"x0", "x1"} );
			}
		}
		//Function test:findtestdir
		{
		IMethod methodfindtestdir9;
			IModelElement[] moduleChilds = module.getChildren();
			methodfindtestdir9 = ModelTestUtils.getAssertMethod( moduleChilds, "findtestdir", 0 );
		}
		//Function test:removepy
		{
		IMethod methodremovepy10;
			IModelElement[] moduleChilds = module.getChildren();
			methodremovepy10 = ModelTestUtils.getAssertMethod( moduleChilds, "removepy", 1 );
			ModelTestUtils.assertParameterNames( methodremovepy10, new String[] {"name"} );
		}
		//Function test:count
		{
		IMethod methodcount11;
			IModelElement[] moduleChilds = module.getChildren();
			methodcount11 = ModelTestUtils.getAssertMethod( moduleChilds, "count", 2 );
			ModelTestUtils.assertParameterNames( methodcount11, new String[] {"n", "word"} );
		}
		//Function test:printlist
		{
		IMethod methodprintlist12;
			IModelElement[] moduleChilds = module.getChildren();
			methodprintlist12 = ModelTestUtils.getAssertMethod( moduleChilds, "printlist", 3 );
			ModelTestUtils.assertParameterNames( methodprintlist12, new String[] {"x", "width", "indent"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_expectations");
		}
		//Class test
		{
		IType class_ExpectedSkips13;
			IModelElement[] moduleChilds = module.getChildren();
			class_ExpectedSkips13 = ModelTestUtils.getAssertClass( moduleChilds, "_ExpectedSkips" );
			//Function test:__init__
			{
			IMethod method__init__14;
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				method__init__14 = ModelTestUtils.getAssertMethod( class_ExpectedSkips13Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__14, new String[] {"self"} );
			}
			{
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_ExpectedSkips13Childs, "valid");
			}
			{
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_ExpectedSkips13Childs, "expected");
			}
			//Function test:isvalid
			{
			IMethod methodisvalid16;
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				methodisvalid16 = ModelTestUtils.getAssertMethod( class_ExpectedSkips13Childs, "isvalid", 1 );
				ModelTestUtils.assertParameterNames( methodisvalid16, new String[] {"self"} );
			}
			//Function test:getexpected
			{
			IMethod methodgetexpected17;
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				methodgetexpected17 = ModelTestUtils.getAssertMethod( class_ExpectedSkips13Childs, "getexpected", 1 );
				ModelTestUtils.assertParameterNames( methodgetexpected17, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mydir");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "i");
		}

	}
	public void testModelGen316( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_calendar.py"));

		assertNotNull("Module test_calendar.py not found", module);
		assertEquals("test_calendar.py", module.getElementName());
		
		//Class test
		{
		IType classCalendarTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classCalendarTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "CalendarTestCase" );
			//Function test:test_isleap
			{
			IMethod methodtest_isleap1;
				IModelElement[] classCalendarTestCase0Childs = classCalendarTestCase0.getChildren();
				methodtest_isleap1 = ModelTestUtils.getAssertMethod( classCalendarTestCase0Childs, "test_isleap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isleap1, new String[] {"self"} );
			}
			//Function test:test_setfirstweekday
			{
			IMethod methodtest_setfirstweekday2;
				IModelElement[] classCalendarTestCase0Childs = classCalendarTestCase0.getChildren();
				methodtest_setfirstweekday2 = ModelTestUtils.getAssertMethod( classCalendarTestCase0Childs, "test_setfirstweekday", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setfirstweekday2, new String[] {"self"} );
			}
			//Function test:test_enumerateweekdays
			{
			IMethod methodtest_enumerateweekdays3;
				IModelElement[] classCalendarTestCase0Childs = classCalendarTestCase0.getChildren();
				methodtest_enumerateweekdays3 = ModelTestUtils.getAssertMethod( classCalendarTestCase0Childs, "test_enumerateweekdays", 1 );
				ModelTestUtils.assertParameterNames( methodtest_enumerateweekdays3, new String[] {"self"} );
			}
			//Function test:test_days
			{
			IMethod methodtest_days4;
				IModelElement[] classCalendarTestCase0Childs = classCalendarTestCase0.getChildren();
				methodtest_days4 = ModelTestUtils.getAssertMethod( classCalendarTestCase0Childs, "test_days", 1 );
				ModelTestUtils.assertParameterNames( methodtest_days4, new String[] {"self"} );
			}
			//Function test:test_months
			{
			IMethod methodtest_months5;
				IModelElement[] classCalendarTestCase0Childs = classCalendarTestCase0.getChildren();
				methodtest_months5 = ModelTestUtils.getAssertMethod( classCalendarTestCase0Childs, "test_months", 1 );
				ModelTestUtils.assertParameterNames( methodtest_months5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMonthCalendarTestCase6;
			IModelElement[] moduleChilds = module.getChildren();
			classMonthCalendarTestCase6 = ModelTestUtils.getAssertClass( moduleChilds, "MonthCalendarTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp7;
				IModelElement[] classMonthCalendarTestCase6Childs = classMonthCalendarTestCase6.getChildren();
				methodsetUp7 = ModelTestUtils.getAssertMethod( classMonthCalendarTestCase6Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp7, new String[] {"self"} );
			}
			{
				IModelElement[] classMonthCalendarTestCase6Childs = classMonthCalendarTestCase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMonthCalendarTestCase6Childs, "oldfirstweekday");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown9;
				IModelElement[] classMonthCalendarTestCase6Childs = classMonthCalendarTestCase6.getChildren();
				methodtearDown9 = ModelTestUtils.getAssertMethod( classMonthCalendarTestCase6Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown9, new String[] {"self"} );
			}
			//Function test:check_weeks
			{
			IMethod methodcheck_weeks10;
				IModelElement[] classMonthCalendarTestCase6Childs = classMonthCalendarTestCase6.getChildren();
				methodcheck_weeks10 = ModelTestUtils.getAssertMethod( classMonthCalendarTestCase6Childs, "check_weeks", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_weeks10, new String[] {"self", "year", "month", "weeks"} );
			}
		}
		//Class test
		{
		IType classMondayTestCase11;
			IModelElement[] moduleChilds = module.getChildren();
			classMondayTestCase11 = ModelTestUtils.getAssertClass( moduleChilds, "MondayTestCase" );
			{
				IModelElement[] classMondayTestCase11Childs = classMondayTestCase11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMondayTestCase11Childs, "firstweekday");
			}
			//Function test:test_february
			{
			IMethod methodtest_february12;
				IModelElement[] classMondayTestCase11Childs = classMondayTestCase11.getChildren();
				methodtest_february12 = ModelTestUtils.getAssertMethod( classMondayTestCase11Childs, "test_february", 1 );
				ModelTestUtils.assertParameterNames( methodtest_february12, new String[] {"self"} );
			}
			//Function test:test_april
			{
			IMethod methodtest_april13;
				IModelElement[] classMondayTestCase11Childs = classMondayTestCase11.getChildren();
				methodtest_april13 = ModelTestUtils.getAssertMethod( classMondayTestCase11Childs, "test_april", 1 );
				ModelTestUtils.assertParameterNames( methodtest_april13, new String[] {"self"} );
			}
			//Function test:test_december
			{
			IMethod methodtest_december14;
				IModelElement[] classMondayTestCase11Childs = classMondayTestCase11.getChildren();
				methodtest_december14 = ModelTestUtils.getAssertMethod( classMondayTestCase11Childs, "test_december", 1 );
				ModelTestUtils.assertParameterNames( methodtest_december14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSundayTestCase15;
			IModelElement[] moduleChilds = module.getChildren();
			classSundayTestCase15 = ModelTestUtils.getAssertClass( moduleChilds, "SundayTestCase" );
			{
				IModelElement[] classSundayTestCase15Childs = classSundayTestCase15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSundayTestCase15Childs, "firstweekday");
			}
			//Function test:test_february
			{
			IMethod methodtest_february16;
				IModelElement[] classSundayTestCase15Childs = classSundayTestCase15.getChildren();
				methodtest_february16 = ModelTestUtils.getAssertMethod( classSundayTestCase15Childs, "test_february", 1 );
				ModelTestUtils.assertParameterNames( methodtest_february16, new String[] {"self"} );
			}
			//Function test:test_april
			{
			IMethod methodtest_april17;
				IModelElement[] classSundayTestCase15Childs = classSundayTestCase15.getChildren();
				methodtest_april17 = ModelTestUtils.getAssertMethod( classSundayTestCase15Childs, "test_april", 1 );
				ModelTestUtils.assertParameterNames( methodtest_april17, new String[] {"self"} );
			}
			//Function test:test_december
			{
			IMethod methodtest_december18;
				IModelElement[] classSundayTestCase15Childs = classSundayTestCase15.getChildren();
				methodtest_december18 = ModelTestUtils.getAssertMethod( classSundayTestCase15Childs, "test_december", 1 );
				ModelTestUtils.assertParameterNames( methodtest_december18, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen317( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_anydbm.py"));

		assertNotNull("Module test_anydbm.py not found", module);
		assertEquals("test_anydbm.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_fname");
		}
		//Function test:_delete_files
		{
		IMethod method_delete_files0;
			IModelElement[] moduleChilds = module.getChildren();
			method_delete_files0 = ModelTestUtils.getAssertMethod( moduleChilds, "_delete_files", 0 );
		}
		//Class test
		{
		IType classAnyDBMTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classAnyDBMTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "AnyDBMTestCase" );
			{
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAnyDBMTestCase1Childs, "_dict");
			}
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "args"} );
			}
			//Function test:test_anydbm_creation
			{
			IMethod methodtest_anydbm_creation3;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodtest_anydbm_creation3 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "test_anydbm_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anydbm_creation3, new String[] {"self"} );
			}
			//Function test:test_anydbm_modification
			{
			IMethod methodtest_anydbm_modification4;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodtest_anydbm_modification4 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "test_anydbm_modification", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anydbm_modification4, new String[] {"self"} );
			}
			//Function test:test_anydbm_read
			{
			IMethod methodtest_anydbm_read5;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodtest_anydbm_read5 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "test_anydbm_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anydbm_read5, new String[] {"self"} );
			}
			//Function test:test_anydbm_keys
			{
			IMethod methodtest_anydbm_keys6;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodtest_anydbm_keys6 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "test_anydbm_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anydbm_keys6, new String[] {"self"} );
			}
			//Function test:read_helper
			{
			IMethod methodread_helper7;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodread_helper7 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "read_helper", 2 );
				ModelTestUtils.assertParameterNames( methodread_helper7, new String[] {"self", "f"} );
			}
			//Function test:init_db
			{
			IMethod methodinit_db8;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodinit_db8 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "init_db", 1 );
				ModelTestUtils.assertParameterNames( methodinit_db8, new String[] {"self"} );
			}
			//Function test:keys_helper
			{
			IMethod methodkeys_helper9;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodkeys_helper9 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "keys_helper", 2 );
				ModelTestUtils.assertParameterNames( methodkeys_helper9, new String[] {"self", "f"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown10;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodtearDown10 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown10, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp11;
				IModelElement[] classAnyDBMTestCase1Childs = classAnyDBMTestCase1.getChildren();
				methodsetUp11 = ModelTestUtils.getAssertMethod( classAnyDBMTestCase1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp11, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen318( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_regex.py"));

		assertNotNull("Module test_regex.py not found", module);
		assertEquals("test_regex.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "re");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cre");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prev");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "re");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cre");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cre");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "re");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cre");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cre");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pattern");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "obj");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "result");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "found");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "groups");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "vardict");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "repl");
		}

	}
	public void testModelGen319( ) throws Exception
	{		throw new RuntimeException("Failed to parse file:../workspace/pytests/src/badsyntax_nocaret.py");
	}
	public void testModelGen320( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_decimal.py"));

		assertNotNull("Module test_decimal.py not found", module);
		assertEquals("test_decimal.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "threading");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Signals");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTDATADIR");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "file");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "file");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testdir");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "directory");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "skip_expected");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "EXTENDEDERRORTEST");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ErrorNames");
		}
		//Function test:Nonfunction
		{
		IMethod methodNonfunction0;
			IModelElement[] moduleChilds = module.getChildren();
			methodNonfunction0 = ModelTestUtils.getAssertMethod( moduleChilds, "Nonfunction", 1 );
			ModelTestUtils.assertParameterNames( methodNonfunction0, new String[] {"args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "RoundingDict");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nameAdapter");
		}
		//Class test
		{
		IType classDecimalTest1;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalTest1 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalTest" );
			//Function test:setUp
			{
			IMethod methodsetUp2;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodsetUp2 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp2, new String[] {"self"} );
			}
			{
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalTest1Childs, "context");
			}
			{
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalTest1Childs, "ignore_list");
			}
			{
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalTest1Childs, "ChangeDict");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown4;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodtearDown4 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown4, new String[] {"self"} );
			}
			//Function test:eval_file
			{
			IMethod methodeval_file5;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_file5 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_file", 2 );
				ModelTestUtils.assertParameterNames( methodeval_file5, new String[] {"self", "file"} );
			}
			//Function test:eval_line
			{
			IMethod methodeval_line6;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_line6 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_line", 2 );
				ModelTestUtils.assertParameterNames( methodeval_line6, new String[] {"self", "s"} );
			}
			//Function test:eval_directive
			{
			IMethod methodeval_directive7;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_directive7 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_directive", 2 );
				ModelTestUtils.assertParameterNames( methodeval_directive7, new String[] {"self", "s"} );
			}
			//Function test:eval_equation
			{
			IMethod methodeval_equation8;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_equation8 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_equation", 2 );
				ModelTestUtils.assertParameterNames( methodeval_equation8, new String[] {"self", "s"} );
				//Function test:FixQuotes
				{
				IMethod methodFixQuotes9;
					IModelElement[] methodeval_equation8Childs = methodeval_equation8.getChildren();
					methodFixQuotes9 = ModelTestUtils.getAssertMethod( methodeval_equation8Childs, "FixQuotes", 1 );
					ModelTestUtils.assertParameterNames( methodFixQuotes9, new String[] {"val"} );
				}
			}
			//Function test:getexceptions
			{
			IMethod methodgetexceptions10;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodgetexceptions10 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "getexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodgetexceptions10, new String[] {"self"} );
			}
			//Function test:change_precision
			{
			IMethod methodchange_precision11;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_precision11 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_precision", 2 );
				ModelTestUtils.assertParameterNames( methodchange_precision11, new String[] {"self", "prec"} );
			}
			//Function test:change_rounding_method
			{
			IMethod methodchange_rounding_method12;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_rounding_method12 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_rounding_method", 2 );
				ModelTestUtils.assertParameterNames( methodchange_rounding_method12, new String[] {"self", "rounding"} );
			}
			//Function test:change_min_exponent
			{
			IMethod methodchange_min_exponent13;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_min_exponent13 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_min_exponent", 2 );
				ModelTestUtils.assertParameterNames( methodchange_min_exponent13, new String[] {"self", "exp"} );
			}
			//Function test:change_max_exponent
			{
			IMethod methodchange_max_exponent14;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_max_exponent14 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_max_exponent", 2 );
				ModelTestUtils.assertParameterNames( methodchange_max_exponent14, new String[] {"self", "exp"} );
			}
			//Function test:change_clamp
			{
			IMethod methodchange_clamp15;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_clamp15 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_clamp", 2 );
				ModelTestUtils.assertParameterNames( methodchange_clamp15, new String[] {"self", "clamp"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tester");
		}
		//Class test
		{
		IType classDecimalExplicitConstructionTest16;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalExplicitConstructionTest16 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalExplicitConstructionTest" );
			//Function test:test_explicit_empty
			{
			IMethod methodtest_explicit_empty17;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_empty17 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_empty17, new String[] {"self"} );
			}
			//Function test:test_explicit_from_None
			{
			IMethod methodtest_explicit_from_None18;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_from_None18 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_from_None", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_None18, new String[] {"self"} );
			}
			//Function test:test_explicit_from_int
			{
			IMethod methodtest_explicit_from_int19;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_from_int19 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_from_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_int19, new String[] {"self"} );
			}
			//Function test:test_explicit_from_string
			{
			IMethod methodtest_explicit_from_string20;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_from_string20 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_from_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_string20, new String[] {"self"} );
			}
			//Function test:test_explicit_from_tuples
			{
			IMethod methodtest_explicit_from_tuples21;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_from_tuples21 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_from_tuples", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_tuples21, new String[] {"self"} );
			}
			//Function test:test_explicit_from_Decimal
			{
			IMethod methodtest_explicit_from_Decimal22;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_from_Decimal22 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_from_Decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_Decimal22, new String[] {"self"} );
			}
			//Function test:test_explicit_context_create_decimal
			{
			IMethod methodtest_explicit_context_create_decimal23;
				IModelElement[] classDecimalExplicitConstructionTest16Childs = classDecimalExplicitConstructionTest16.getChildren();
				methodtest_explicit_context_create_decimal23 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest16Childs, "test_explicit_context_create_decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_context_create_decimal23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDecimalImplicitConstructionTest24;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalImplicitConstructionTest24 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalImplicitConstructionTest" );
			//Function test:test_implicit_from_None
			{
			IMethod methodtest_implicit_from_None25;
				IModelElement[] classDecimalImplicitConstructionTest24Childs = classDecimalImplicitConstructionTest24.getChildren();
				methodtest_implicit_from_None25 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest24Childs, "test_implicit_from_None", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_None25, new String[] {"self"} );
			}
			//Function test:test_implicit_from_int
			{
			IMethod methodtest_implicit_from_int26;
				IModelElement[] classDecimalImplicitConstructionTest24Childs = classDecimalImplicitConstructionTest24.getChildren();
				methodtest_implicit_from_int26 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest24Childs, "test_implicit_from_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_int26, new String[] {"self"} );
			}
			//Function test:test_implicit_from_string
			{
			IMethod methodtest_implicit_from_string27;
				IModelElement[] classDecimalImplicitConstructionTest24Childs = classDecimalImplicitConstructionTest24.getChildren();
				methodtest_implicit_from_string27 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest24Childs, "test_implicit_from_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_string27, new String[] {"self"} );
			}
			//Function test:test_implicit_from_float
			{
			IMethod methodtest_implicit_from_float28;
				IModelElement[] classDecimalImplicitConstructionTest24Childs = classDecimalImplicitConstructionTest24.getChildren();
				methodtest_implicit_from_float28 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest24Childs, "test_implicit_from_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_float28, new String[] {"self"} );
			}
			//Function test:test_implicit_from_Decimal
			{
			IMethod methodtest_implicit_from_Decimal29;
				IModelElement[] classDecimalImplicitConstructionTest24Childs = classDecimalImplicitConstructionTest24.getChildren();
				methodtest_implicit_from_Decimal29 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest24Childs, "test_implicit_from_Decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_Decimal29, new String[] {"self"} );
			}
			//Function test:test_rop
			{
			IMethod methodtest_rop30;
				IModelElement[] classDecimalImplicitConstructionTest24Childs = classDecimalImplicitConstructionTest24.getChildren();
				methodtest_rop30 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest24Childs, "test_rop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rop30, new String[] {"self"} );
				//Class test
				{
				IType classE31;
					IModelElement[] methodtest_rop30Childs = methodtest_rop30.getChildren();
					classE31 = ModelTestUtils.getAssertClass( methodtest_rop30Childs, "E" );
					//Function test:__divmod__
					{
					IMethod method__divmod__32;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__divmod__32 = ModelTestUtils.getAssertMethod( classE31Childs, "__divmod__", 2 );
						ModelTestUtils.assertParameterNames( method__divmod__32, new String[] {"self", "other"} );
					}
					//Function test:__rdivmod__
					{
					IMethod method__rdivmod__33;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__rdivmod__33 = ModelTestUtils.getAssertMethod( classE31Childs, "__rdivmod__", 2 );
						ModelTestUtils.assertParameterNames( method__rdivmod__33, new String[] {"self", "other"} );
					}
					//Function test:__lt__
					{
					IMethod method__lt__34;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__lt__34 = ModelTestUtils.getAssertMethod( classE31Childs, "__lt__", 2 );
						ModelTestUtils.assertParameterNames( method__lt__34, new String[] {"self", "other"} );
					}
					//Function test:__gt__
					{
					IMethod method__gt__35;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__gt__35 = ModelTestUtils.getAssertMethod( classE31Childs, "__gt__", 2 );
						ModelTestUtils.assertParameterNames( method__gt__35, new String[] {"self", "other"} );
					}
					//Function test:__le__
					{
					IMethod method__le__36;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__le__36 = ModelTestUtils.getAssertMethod( classE31Childs, "__le__", 2 );
						ModelTestUtils.assertParameterNames( method__le__36, new String[] {"self", "other"} );
					}
					//Function test:__ge__
					{
					IMethod method__ge__37;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__ge__37 = ModelTestUtils.getAssertMethod( classE31Childs, "__ge__", 2 );
						ModelTestUtils.assertParameterNames( method__ge__37, new String[] {"self", "other"} );
					}
					//Function test:__eq__
					{
					IMethod method__eq__38;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__eq__38 = ModelTestUtils.getAssertMethod( classE31Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__38, new String[] {"self", "other"} );
					}
					//Function test:__ne__
					{
					IMethod method__ne__39;
						IModelElement[] classE31Childs = classE31.getChildren();
						method__ne__39 = ModelTestUtils.getAssertMethod( classE31Childs, "__ne__", 2 );
						ModelTestUtils.assertParameterNames( method__ne__39, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Class test
		{
		IType classDecimalArithmeticOperatorsTest40;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalArithmeticOperatorsTest40 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalArithmeticOperatorsTest" );
			//Function test:test_addition
			{
			IMethod methodtest_addition41;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_addition41 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_addition", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addition41, new String[] {"self"} );
			}
			//Function test:test_subtraction
			{
			IMethod methodtest_subtraction42;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_subtraction42 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_subtraction", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subtraction42, new String[] {"self"} );
			}
			//Function test:test_multiplication
			{
			IMethod methodtest_multiplication43;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_multiplication43 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_multiplication", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiplication43, new String[] {"self"} );
			}
			//Function test:test_division
			{
			IMethod methodtest_division44;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_division44 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_division", 1 );
				ModelTestUtils.assertParameterNames( methodtest_division44, new String[] {"self"} );
			}
			//Function test:test_floor_division
			{
			IMethod methodtest_floor_division45;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_floor_division45 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_floor_division", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floor_division45, new String[] {"self"} );
			}
			//Function test:test_powering
			{
			IMethod methodtest_powering46;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_powering46 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_powering", 1 );
				ModelTestUtils.assertParameterNames( methodtest_powering46, new String[] {"self"} );
			}
			//Function test:test_module
			{
			IMethod methodtest_module47;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_module47 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_module", 1 );
				ModelTestUtils.assertParameterNames( methodtest_module47, new String[] {"self"} );
			}
			//Function test:test_floor_div_module
			{
			IMethod methodtest_floor_div_module48;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_floor_div_module48 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_floor_div_module", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floor_div_module48, new String[] {"self"} );
			}
			//Function test:test_unary_operators
			{
			IMethod methodtest_unary_operators49;
				IModelElement[] classDecimalArithmeticOperatorsTest40Childs = classDecimalArithmeticOperatorsTest40.getChildren();
				methodtest_unary_operators49 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest40Childs, "test_unary_operators", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unary_operators49, new String[] {"self"} );
			}
		}
		//Function test:thfunc1
		{
		IMethod methodthfunc150;
			IModelElement[] moduleChilds = module.getChildren();
			methodthfunc150 = ModelTestUtils.getAssertMethod( moduleChilds, "thfunc1", 1 );
			ModelTestUtils.assertParameterNames( methodthfunc150, new String[] {"cls"} );
		}
		//Function test:thfunc2
		{
		IMethod methodthfunc251;
			IModelElement[] moduleChilds = module.getChildren();
			methodthfunc251 = ModelTestUtils.getAssertMethod( moduleChilds, "thfunc2", 1 );
			ModelTestUtils.assertParameterNames( methodthfunc251, new String[] {"cls"} );
		}
		//Class test
		{
		IType classDecimalUseOfContextTest52;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalUseOfContextTest52 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalUseOfContextTest" );
			{
				IModelElement[] classDecimalUseOfContextTest52Childs = classDecimalUseOfContextTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalUseOfContextTest52Childs, "threading");
			}
			//Function test:test_threading
			{
			IMethod methodtest_threading53;
				IModelElement[] classDecimalUseOfContextTest52Childs = classDecimalUseOfContextTest52.getChildren();
				methodtest_threading53 = ModelTestUtils.getAssertMethod( classDecimalUseOfContextTest52Childs, "test_threading", 1 );
				ModelTestUtils.assertParameterNames( methodtest_threading53, new String[] {"self"} );
			}
			{
				IModelElement[] classDecimalUseOfContextTest52Childs = classDecimalUseOfContextTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalUseOfContextTest52Childs, "synchro");
			}
			{
				IModelElement[] classDecimalUseOfContextTest52Childs = classDecimalUseOfContextTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalUseOfContextTest52Childs, "finish1");
			}
			{
				IModelElement[] classDecimalUseOfContextTest52Childs = classDecimalUseOfContextTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalUseOfContextTest52Childs, "finish2");
			}
		}
		//Class test
		{
		IType classDecimalUsabilityTest55;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalUsabilityTest55 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalUsabilityTest" );
			//Function test:test_comparison_operators
			{
			IMethod methodtest_comparison_operators56;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_comparison_operators56 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_comparison_operators", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparison_operators56, new String[] {"self"} );
			}
			//Function test:test_copy_and_deepcopy_methods
			{
			IMethod methodtest_copy_and_deepcopy_methods57;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_copy_and_deepcopy_methods57 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_copy_and_deepcopy_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_and_deepcopy_methods57, new String[] {"self"} );
			}
			//Function test:test_hash_method
			{
			IMethod methodtest_hash_method58;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_hash_method58 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_hash_method", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_method58, new String[] {"self"} );
			}
			//Function test:test_min_and_max_methods
			{
			IMethod methodtest_min_and_max_methods59;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_min_and_max_methods59 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_min_and_max_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_min_and_max_methods59, new String[] {"self"} );
			}
			//Function test:test_as_nonzero
			{
			IMethod methodtest_as_nonzero60;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_as_nonzero60 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_as_nonzero", 1 );
				ModelTestUtils.assertParameterNames( methodtest_as_nonzero60, new String[] {"self"} );
			}
			//Function test:test_tostring_methods
			{
			IMethod methodtest_tostring_methods61;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_tostring_methods61 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_tostring_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tostring_methods61, new String[] {"self"} );
			}
			//Function test:test_tonum_methods
			{
			IMethod methodtest_tonum_methods62;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_tonum_methods62 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_tonum_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tonum_methods62, new String[] {"self"} );
			}
			//Function test:test_eval_round_trip
			{
			IMethod methodtest_eval_round_trip63;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_eval_round_trip63 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_eval_round_trip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eval_round_trip63, new String[] {"self"} );
			}
			//Function test:test_as_tuple
			{
			IMethod methodtest_as_tuple64;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_as_tuple64 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_as_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_as_tuple64, new String[] {"self"} );
			}
			//Function test:test_immutability_operations
			{
			IMethod methodtest_immutability_operations65;
				IModelElement[] classDecimalUsabilityTest55Childs = classDecimalUsabilityTest55.getChildren();
				methodtest_immutability_operations65 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest55Childs, "test_immutability_operations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_immutability_operations65, new String[] {"self"} );
				//Function test:checkSameDec
				{
				IMethod methodcheckSameDec66;
					IModelElement[] methodtest_immutability_operations65Childs = methodtest_immutability_operations65.getChildren();
					methodcheckSameDec66 = ModelTestUtils.getAssertMethod( methodtest_immutability_operations65Childs, "checkSameDec", 2 );
					ModelTestUtils.assertParameterNames( methodcheckSameDec66, new String[] {"operation", "useOther"} );
				}
			}
		}
		//Class test
		{
		IType classDecimalPythonAPItests67;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalPythonAPItests67 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalPythonAPItests" );
			//Function test:test_pickle
			{
			IMethod methodtest_pickle68;
				IModelElement[] classDecimalPythonAPItests67Childs = classDecimalPythonAPItests67.getChildren();
				methodtest_pickle68 = ModelTestUtils.getAssertMethod( classDecimalPythonAPItests67Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle68, new String[] {"self"} );
			}
			//Function test:test_int
			{
			IMethod methodtest_int69;
				IModelElement[] classDecimalPythonAPItests67Childs = classDecimalPythonAPItests67.getChildren();
				methodtest_int69 = ModelTestUtils.getAssertMethod( classDecimalPythonAPItests67Childs, "test_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int69, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classContextAPItests70;
			IModelElement[] moduleChilds = module.getChildren();
			classContextAPItests70 = ModelTestUtils.getAssertClass( moduleChilds, "ContextAPItests" );
			//Function test:test_pickle
			{
			IMethod methodtest_pickle71;
				IModelElement[] classContextAPItests70Childs = classContextAPItests70.getChildren();
				methodtest_pickle71 = ModelTestUtils.getAssertMethod( classContextAPItests70Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle71, new String[] {"self"} );
			}
			//Function test:test_equality_with_other_types
			{
			IMethod methodtest_equality_with_other_types72;
				IModelElement[] classContextAPItests70Childs = classContextAPItests70.getChildren();
				methodtest_equality_with_other_types72 = ModelTestUtils.getAssertMethod( classContextAPItests70Childs, "test_equality_with_other_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equality_with_other_types72, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy73;
				IModelElement[] classContextAPItests70Childs = classContextAPItests70.getChildren();
				methodtest_copy73 = ModelTestUtils.getAssertMethod( classContextAPItests70Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy73, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main74;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main74 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 2 );
			ModelTestUtils.assertParameterNames( methodtest_main74, new String[] {"arith", "verbose"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "arith");
		}

	}
	public void testModelGen321( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_trace.py"));

		assertNotNull("Module test_trace.py not found", module);
		assertEquals("test_trace.py", module.getElementName());
		
		//Function test:basic
		{
		IMethod methodbasic0;
			IModelElement[] moduleChilds = module.getChildren();
			methodbasic0 = ModelTestUtils.getAssertMethod( moduleChilds, "basic", 0 );
		}
		//Function test:arigo_example
		{
		IMethod methodarigo_example1;
			IModelElement[] moduleChilds = module.getChildren();
			methodarigo_example1 = ModelTestUtils.getAssertMethod( moduleChilds, "arigo_example", 0 );
		}
		//Function test:one_instr_line
		{
		IMethod methodone_instr_line2;
			IModelElement[] moduleChilds = module.getChildren();
			methodone_instr_line2 = ModelTestUtils.getAssertMethod( moduleChilds, "one_instr_line", 0 );
		}
		//Function test:no_pop_tops
		{
		IMethod methodno_pop_tops3;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_pop_tops3 = ModelTestUtils.getAssertMethod( moduleChilds, "no_pop_tops", 0 );
		}
		//Function test:no_pop_blocks
		{
		IMethod methodno_pop_blocks4;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_pop_blocks4 = ModelTestUtils.getAssertMethod( moduleChilds, "no_pop_blocks", 0 );
		}
		//Function test:called
		{
		IMethod methodcalled5;
			IModelElement[] moduleChilds = module.getChildren();
			methodcalled5 = ModelTestUtils.getAssertMethod( moduleChilds, "called", 0 );
		}
		//Function test:call
		{
		IMethod methodcall6;
			IModelElement[] moduleChilds = module.getChildren();
			methodcall6 = ModelTestUtils.getAssertMethod( moduleChilds, "call", 0 );
		}
		//Function test:raises
		{
		IMethod methodraises7;
			IModelElement[] moduleChilds = module.getChildren();
			methodraises7 = ModelTestUtils.getAssertMethod( moduleChilds, "raises", 0 );
		}
		//Function test:test_raise
		{
		IMethod methodtest_raise8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_raise8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_raise", 0 );
		}
		//Function test:_settrace_and_return
		{
		IMethod method_settrace_and_return9;
			IModelElement[] moduleChilds = module.getChildren();
			method_settrace_and_return9 = ModelTestUtils.getAssertMethod( moduleChilds, "_settrace_and_return", 1 );
			ModelTestUtils.assertParameterNames( method_settrace_and_return9, new String[] {"tracefunc"} );
		}
		//Function test:settrace_and_return
		{
		IMethod methodsettrace_and_return10;
			IModelElement[] moduleChilds = module.getChildren();
			methodsettrace_and_return10 = ModelTestUtils.getAssertMethod( moduleChilds, "settrace_and_return", 1 );
			ModelTestUtils.assertParameterNames( methodsettrace_and_return10, new String[] {"tracefunc"} );
		}
		//Function test:_settrace_and_raise
		{
		IMethod method_settrace_and_raise11;
			IModelElement[] moduleChilds = module.getChildren();
			method_settrace_and_raise11 = ModelTestUtils.getAssertMethod( moduleChilds, "_settrace_and_raise", 1 );
			ModelTestUtils.assertParameterNames( method_settrace_and_raise11, new String[] {"tracefunc"} );
		}
		//Function test:settrace_and_raise
		{
		IMethod methodsettrace_and_raise12;
			IModelElement[] moduleChilds = module.getChildren();
			methodsettrace_and_raise12 = ModelTestUtils.getAssertMethod( moduleChilds, "settrace_and_raise", 1 );
			ModelTestUtils.assertParameterNames( methodsettrace_and_raise12, new String[] {"tracefunc"} );
		}
		//Function test:ireturn_example
		{
		IMethod methodireturn_example13;
			IModelElement[] moduleChilds = module.getChildren();
			methodireturn_example13 = ModelTestUtils.getAssertMethod( moduleChilds, "ireturn_example", 0 );
		}
		//Function test:tightloop_example
		{
		IMethod methodtightloop_example14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtightloop_example14 = ModelTestUtils.getAssertMethod( moduleChilds, "tightloop_example", 0 );
		}
		//Function test:tighterloop_example
		{
		IMethod methodtighterloop_example15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtighterloop_example15 = ModelTestUtils.getAssertMethod( moduleChilds, "tighterloop_example", 0 );
		}
		//Class test
		{
		IType classTracer16;
			IModelElement[] moduleChilds = module.getChildren();
			classTracer16 = ModelTestUtils.getAssertClass( moduleChilds, "Tracer" );
			//Function test:__init__
			{
			IMethod method__init__17;
				IModelElement[] classTracer16Childs = classTracer16.getChildren();
				method__init__17 = ModelTestUtils.getAssertMethod( classTracer16Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__17, new String[] {"self"} );
			}
			{
				IModelElement[] classTracer16Childs = classTracer16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTracer16Childs, "events");
			}
			//Function test:trace
			{
			IMethod methodtrace19;
				IModelElement[] classTracer16Childs = classTracer16.getChildren();
				methodtrace19 = ModelTestUtils.getAssertMethod( classTracer16Childs, "trace", 4 );
				ModelTestUtils.assertParameterNames( methodtrace19, new String[] {"self", "frame", "event", "arg"} );
			}
		}
		//Class test
		{
		IType classTraceTestCase20;
			IModelElement[] moduleChilds = module.getChildren();
			classTraceTestCase20 = ModelTestUtils.getAssertClass( moduleChilds, "TraceTestCase" );
			//Function test:compare_events
			{
			IMethod methodcompare_events21;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodcompare_events21 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "compare_events", 4 );
				ModelTestUtils.assertParameterNames( methodcompare_events21, new String[] {"self", "line_offset", "events", "expected_events"} );
			}
			//Function test:run_test
			{
			IMethod methodrun_test22;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodrun_test22 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "run_test", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test22, new String[] {"self", "func"} );
			}
			//Function test:run_test2
			{
			IMethod methodrun_test223;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodrun_test223 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "run_test2", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test223, new String[] {"self", "func"} );
			}
			//Function test:test_01_basic
			{
			IMethod methodtest_01_basic24;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_01_basic24 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_01_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_01_basic24, new String[] {"self"} );
			}
			//Function test:test_02_arigo
			{
			IMethod methodtest_02_arigo25;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_02_arigo25 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_02_arigo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_02_arigo25, new String[] {"self"} );
			}
			//Function test:test_03_one_instr
			{
			IMethod methodtest_03_one_instr26;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_03_one_instr26 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_03_one_instr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_03_one_instr26, new String[] {"self"} );
			}
			//Function test:test_04_no_pop_blocks
			{
			IMethod methodtest_04_no_pop_blocks27;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_04_no_pop_blocks27 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_04_no_pop_blocks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_04_no_pop_blocks27, new String[] {"self"} );
			}
			//Function test:test_05_no_pop_tops
			{
			IMethod methodtest_05_no_pop_tops28;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_05_no_pop_tops28 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_05_no_pop_tops", 1 );
				ModelTestUtils.assertParameterNames( methodtest_05_no_pop_tops28, new String[] {"self"} );
			}
			//Function test:test_06_call
			{
			IMethod methodtest_06_call29;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_06_call29 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_06_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_06_call29, new String[] {"self"} );
			}
			//Function test:test_07_raise
			{
			IMethod methodtest_07_raise30;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_07_raise30 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_07_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_07_raise30, new String[] {"self"} );
			}
			//Function test:test_08_settrace_and_return
			{
			IMethod methodtest_08_settrace_and_return31;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_08_settrace_and_return31 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_08_settrace_and_return", 1 );
				ModelTestUtils.assertParameterNames( methodtest_08_settrace_and_return31, new String[] {"self"} );
			}
			//Function test:test_09_settrace_and_raise
			{
			IMethod methodtest_09_settrace_and_raise32;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_09_settrace_and_raise32 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_09_settrace_and_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_09_settrace_and_raise32, new String[] {"self"} );
			}
			//Function test:test_10_ireturn
			{
			IMethod methodtest_10_ireturn33;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_10_ireturn33 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_10_ireturn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_10_ireturn33, new String[] {"self"} );
			}
			//Function test:test_11_tightloop
			{
			IMethod methodtest_11_tightloop34;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_11_tightloop34 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_11_tightloop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_11_tightloop34, new String[] {"self"} );
			}
			//Function test:test_12_tighterloop
			{
			IMethod methodtest_12_tighterloop35;
				IModelElement[] classTraceTestCase20Childs = classTraceTestCase20.getChildren();
				methodtest_12_tighterloop35 = ModelTestUtils.getAssertMethod( classTraceTestCase20Childs, "test_12_tighterloop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_12_tighterloop35, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRaisingTraceFuncTestCase36;
			IModelElement[] moduleChilds = module.getChildren();
			classRaisingTraceFuncTestCase36 = ModelTestUtils.getAssertClass( moduleChilds, "RaisingTraceFuncTestCase" );
			//Function test:trace
			{
			IMethod methodtrace37;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodtrace37 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "trace", 4 );
				ModelTestUtils.assertParameterNames( methodtrace37, new String[] {"self", "frame", "event", "arg"} );
			}
			//Function test:f
			{
			IMethod methodf38;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodf38 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "f", 1 );
				ModelTestUtils.assertParameterNames( methodf38, new String[] {"self"} );
			}
			//Function test:run_test_for_event
			{
			IMethod methodrun_test_for_event39;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodrun_test_for_event39 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "run_test_for_event", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test_for_event39, new String[] {"self", "event"} );
			}
			{
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRaisingTraceFuncTestCase36Childs, "raiseOnEvent");
			}
			//Function test:test_call
			{
			IMethod methodtest_call41;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodtest_call41 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "test_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_call41, new String[] {"self"} );
			}
			//Function test:test_line
			{
			IMethod methodtest_line42;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodtest_line42 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "test_line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_line42, new String[] {"self"} );
			}
			//Function test:test_return
			{
			IMethod methodtest_return43;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodtest_return43 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "test_return", 1 );
				ModelTestUtils.assertParameterNames( methodtest_return43, new String[] {"self"} );
			}
			//Function test:test_exception
			{
			IMethod methodtest_exception44;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodtest_exception44 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "test_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception44, new String[] {"self"} );
			}
			//Function test:test_trash_stack
			{
			IMethod methodtest_trash_stack45;
				IModelElement[] classRaisingTraceFuncTestCase36Childs = classRaisingTraceFuncTestCase36.getChildren();
				methodtest_trash_stack45 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase36Childs, "test_trash_stack", 1 );
				ModelTestUtils.assertParameterNames( methodtest_trash_stack45, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf46;
					IModelElement[] methodtest_trash_stack45Childs = methodtest_trash_stack45.getChildren();
					methodf46 = ModelTestUtils.getAssertMethod( methodtest_trash_stack45Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg47;
					IModelElement[] methodtest_trash_stack45Childs = methodtest_trash_stack45.getChildren();
					methodg47 = ModelTestUtils.getAssertMethod( methodtest_trash_stack45Childs, "g", 3 );
					ModelTestUtils.assertParameterNames( methodg47, new String[] {"frame", "why", "extra"} );
				}
			}
		}
		//Class test
		{
		IType classJumpTracer48;
			IModelElement[] moduleChilds = module.getChildren();
			classJumpTracer48 = ModelTestUtils.getAssertClass( moduleChilds, "JumpTracer" );
			//Function test:__init__
			{
			IMethod method__init__49;
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				method__init__49 = ModelTestUtils.getAssertMethod( classJumpTracer48Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__49, new String[] {"self", "function"} );
			}
			{
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classJumpTracer48Childs, "function");
			}
			{
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classJumpTracer48Childs, "jumpFrom");
			}
			{
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classJumpTracer48Childs, "jumpTo");
			}
			{
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classJumpTracer48Childs, "done");
			}
			//Function test:trace
			{
			IMethod methodtrace51;
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				methodtrace51 = ModelTestUtils.getAssertMethod( classJumpTracer48Childs, "trace", 4 );
				ModelTestUtils.assertParameterNames( methodtrace51, new String[] {"self", "frame", "event", "arg"} );
			}
			{
				IModelElement[] classJumpTracer48Childs = classJumpTracer48.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classJumpTracer48Childs, "done");
			}
		}
		//Function test:jump_simple_forwards
		{
		IMethod methodjump_simple_forwards53;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_simple_forwards53 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_simple_forwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_simple_forwards53, new String[] {"output"} );
		}
		//Function test:jump_simple_backwards
		{
		IMethod methodjump_simple_backwards54;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_simple_backwards54 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_simple_backwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_simple_backwards54, new String[] {"output"} );
		}
		//Function test:jump_out_of_block_forwards
		{
		IMethod methodjump_out_of_block_forwards55;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_out_of_block_forwards55 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_out_of_block_forwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_out_of_block_forwards55, new String[] {"output"} );
		}
		//Function test:jump_out_of_block_backwards
		{
		IMethod methodjump_out_of_block_backwards56;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_out_of_block_backwards56 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_out_of_block_backwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_out_of_block_backwards56, new String[] {"output"} );
		}
		//Function test:jump_to_codeless_line
		{
		IMethod methodjump_to_codeless_line57;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_to_codeless_line57 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_to_codeless_line", 1 );
			ModelTestUtils.assertParameterNames( methodjump_to_codeless_line57, new String[] {"output"} );
		}
		//Function test:jump_to_same_line
		{
		IMethod methodjump_to_same_line58;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_to_same_line58 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_to_same_line", 1 );
			ModelTestUtils.assertParameterNames( methodjump_to_same_line58, new String[] {"output"} );
		}
		//Function test:jump_in_nested_finally
		{
		IMethod methodjump_in_nested_finally59;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_in_nested_finally59 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_in_nested_finally", 1 );
			ModelTestUtils.assertParameterNames( methodjump_in_nested_finally59, new String[] {"output"} );
		}
		//Function test:no_jump_too_far_forwards
		{
		IMethod methodno_jump_too_far_forwards60;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_too_far_forwards60 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_too_far_forwards", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_too_far_forwards60, new String[] {"output"} );
		}
		//Function test:no_jump_too_far_backwards
		{
		IMethod methodno_jump_too_far_backwards61;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_too_far_backwards61 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_too_far_backwards", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_too_far_backwards61, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_1
		{
		IMethod methodno_jump_to_except_162;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_162 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_1", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_162, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_2
		{
		IMethod methodno_jump_to_except_263;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_263 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_2", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_263, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_3
		{
		IMethod methodno_jump_to_except_364;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_364 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_3", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_364, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_4
		{
		IMethod methodno_jump_to_except_465;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_465 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_4", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_465, new String[] {"output"} );
		}
		//Function test:no_jump_forwards_into_block
		{
		IMethod methodno_jump_forwards_into_block66;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_forwards_into_block66 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_forwards_into_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_forwards_into_block66, new String[] {"output"} );
		}
		//Function test:no_jump_backwards_into_block
		{
		IMethod methodno_jump_backwards_into_block67;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_backwards_into_block67 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_backwards_into_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_backwards_into_block67, new String[] {"output"} );
		}
		//Function test:no_jump_into_finally_block
		{
		IMethod methodno_jump_into_finally_block68;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_into_finally_block68 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_into_finally_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_into_finally_block68, new String[] {"output"} );
		}
		//Function test:no_jump_out_of_finally_block
		{
		IMethod methodno_jump_out_of_finally_block69;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_out_of_finally_block69 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_out_of_finally_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_out_of_finally_block69, new String[] {"output"} );
		}
		//Function test:no_jump_to_non_integers
		{
		IMethod methodno_jump_to_non_integers70;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_non_integers70 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_non_integers", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_non_integers70, new String[] {"output"} );
		}
		//Function test:no_jump_without_trace_function
		{
		IMethod methodno_jump_without_trace_function71;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_without_trace_function71 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_without_trace_function", 0 );
		}
		//Class test
		{
		IType classJumpTestCase72;
			IModelElement[] moduleChilds = module.getChildren();
			classJumpTestCase72 = ModelTestUtils.getAssertClass( moduleChilds, "JumpTestCase" );
			//Function test:compare_jump_output
			{
			IMethod methodcompare_jump_output73;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodcompare_jump_output73 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "compare_jump_output", 3 );
				ModelTestUtils.assertParameterNames( methodcompare_jump_output73, new String[] {"self", "expected", "received"} );
			}
			//Function test:run_test
			{
			IMethod methodrun_test74;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodrun_test74 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "run_test", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test74, new String[] {"self", "func"} );
			}
			//Function test:test_01_jump_simple_forwards
			{
			IMethod methodtest_01_jump_simple_forwards75;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_01_jump_simple_forwards75 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_01_jump_simple_forwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_01_jump_simple_forwards75, new String[] {"self"} );
			}
			//Function test:test_02_jump_simple_backwards
			{
			IMethod methodtest_02_jump_simple_backwards76;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_02_jump_simple_backwards76 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_02_jump_simple_backwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_02_jump_simple_backwards76, new String[] {"self"} );
			}
			//Function test:test_03_jump_out_of_block_forwards
			{
			IMethod methodtest_03_jump_out_of_block_forwards77;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_03_jump_out_of_block_forwards77 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_03_jump_out_of_block_forwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_03_jump_out_of_block_forwards77, new String[] {"self"} );
			}
			//Function test:test_04_jump_out_of_block_backwards
			{
			IMethod methodtest_04_jump_out_of_block_backwards78;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_04_jump_out_of_block_backwards78 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_04_jump_out_of_block_backwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_04_jump_out_of_block_backwards78, new String[] {"self"} );
			}
			//Function test:test_05_jump_to_codeless_line
			{
			IMethod methodtest_05_jump_to_codeless_line79;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_05_jump_to_codeless_line79 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_05_jump_to_codeless_line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_05_jump_to_codeless_line79, new String[] {"self"} );
			}
			//Function test:test_06_jump_to_same_line
			{
			IMethod methodtest_06_jump_to_same_line80;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_06_jump_to_same_line80 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_06_jump_to_same_line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_06_jump_to_same_line80, new String[] {"self"} );
			}
			//Function test:test_07_jump_in_nested_finally
			{
			IMethod methodtest_07_jump_in_nested_finally81;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_07_jump_in_nested_finally81 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_07_jump_in_nested_finally", 1 );
				ModelTestUtils.assertParameterNames( methodtest_07_jump_in_nested_finally81, new String[] {"self"} );
			}
			//Function test:test_08_no_jump_too_far_forwards
			{
			IMethod methodtest_08_no_jump_too_far_forwards82;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_08_no_jump_too_far_forwards82 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_08_no_jump_too_far_forwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_08_no_jump_too_far_forwards82, new String[] {"self"} );
			}
			//Function test:test_09_no_jump_too_far_backwards
			{
			IMethod methodtest_09_no_jump_too_far_backwards83;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_09_no_jump_too_far_backwards83 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_09_no_jump_too_far_backwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_09_no_jump_too_far_backwards83, new String[] {"self"} );
			}
			//Function test:test_10_no_jump_to_except_1
			{
			IMethod methodtest_10_no_jump_to_except_184;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_10_no_jump_to_except_184 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_10_no_jump_to_except_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_10_no_jump_to_except_184, new String[] {"self"} );
			}
			//Function test:test_11_no_jump_to_except_2
			{
			IMethod methodtest_11_no_jump_to_except_285;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_11_no_jump_to_except_285 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_11_no_jump_to_except_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_11_no_jump_to_except_285, new String[] {"self"} );
			}
			//Function test:test_12_no_jump_to_except_3
			{
			IMethod methodtest_12_no_jump_to_except_386;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_12_no_jump_to_except_386 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_12_no_jump_to_except_3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_12_no_jump_to_except_386, new String[] {"self"} );
			}
			//Function test:test_13_no_jump_to_except_4
			{
			IMethod methodtest_13_no_jump_to_except_487;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_13_no_jump_to_except_487 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_13_no_jump_to_except_4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_13_no_jump_to_except_487, new String[] {"self"} );
			}
			//Function test:test_14_no_jump_forwards_into_block
			{
			IMethod methodtest_14_no_jump_forwards_into_block88;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_14_no_jump_forwards_into_block88 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_14_no_jump_forwards_into_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_14_no_jump_forwards_into_block88, new String[] {"self"} );
			}
			//Function test:test_15_no_jump_backwards_into_block
			{
			IMethod methodtest_15_no_jump_backwards_into_block89;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_15_no_jump_backwards_into_block89 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_15_no_jump_backwards_into_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_15_no_jump_backwards_into_block89, new String[] {"self"} );
			}
			//Function test:test_16_no_jump_into_finally_block
			{
			IMethod methodtest_16_no_jump_into_finally_block90;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_16_no_jump_into_finally_block90 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_16_no_jump_into_finally_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_16_no_jump_into_finally_block90, new String[] {"self"} );
			}
			//Function test:test_17_no_jump_out_of_finally_block
			{
			IMethod methodtest_17_no_jump_out_of_finally_block91;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_17_no_jump_out_of_finally_block91 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_17_no_jump_out_of_finally_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_17_no_jump_out_of_finally_block91, new String[] {"self"} );
			}
			//Function test:test_18_no_jump_to_non_integers
			{
			IMethod methodtest_18_no_jump_to_non_integers92;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_18_no_jump_to_non_integers92 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_18_no_jump_to_non_integers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_18_no_jump_to_non_integers92, new String[] {"self"} );
			}
			//Function test:test_19_no_jump_without_trace_function
			{
			IMethod methodtest_19_no_jump_without_trace_function93;
				IModelElement[] classJumpTestCase72Childs = classJumpTestCase72.getChildren();
				methodtest_19_no_jump_without_trace_function93 = ModelTestUtils.getAssertMethod( classJumpTestCase72Childs, "test_19_no_jump_without_trace_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_19_no_jump_without_trace_function93, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main94;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main94 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen322( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sundry.py"));

		assertNotNull("Module test_sundry.py not found", module);
		assertEquals("test_sundry.py", module.getElementName());
		

	}
	public void testModelGen323( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_htmllib.py"));

		assertNotNull("Module test_htmllib.py not found", module);
		assertEquals("test_htmllib.py", module.getElementName());
		
		//Class test
		{
		IType classAnchorCollector0;
			IModelElement[] moduleChilds = module.getChildren();
			classAnchorCollector0 = ModelTestUtils.getAssertClass( moduleChilds, "AnchorCollector" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classAnchorCollector0Childs = classAnchorCollector0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classAnchorCollector0Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "args", "kw"} );
			}
			{
				IModelElement[] classAnchorCollector0Childs = classAnchorCollector0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAnchorCollector0Childs, "__anchors");
			}
			//Function test:get_anchor_info
			{
			IMethod methodget_anchor_info3;
				IModelElement[] classAnchorCollector0Childs = classAnchorCollector0.getChildren();
				methodget_anchor_info3 = ModelTestUtils.getAssertMethod( classAnchorCollector0Childs, "get_anchor_info", 1 );
				ModelTestUtils.assertParameterNames( methodget_anchor_info3, new String[] {"self"} );
			}
			//Function test:anchor_bgn
			{
			IMethod methodanchor_bgn4;
				IModelElement[] classAnchorCollector0Childs = classAnchorCollector0.getChildren();
				methodanchor_bgn4 = ModelTestUtils.getAssertMethod( classAnchorCollector0Childs, "anchor_bgn", 2 );
				ModelTestUtils.assertParameterNames( methodanchor_bgn4, new String[] {"self", "args"} );
			}
		}
		//Class test
		{
		IType classDeclCollector5;
			IModelElement[] moduleChilds = module.getChildren();
			classDeclCollector5 = ModelTestUtils.getAssertClass( moduleChilds, "DeclCollector" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classDeclCollector5Childs = classDeclCollector5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classDeclCollector5Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "args", "kw"} );
			}
			{
				IModelElement[] classDeclCollector5Childs = classDeclCollector5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDeclCollector5Childs, "__decls");
			}
			//Function test:get_decl_info
			{
			IMethod methodget_decl_info8;
				IModelElement[] classDeclCollector5Childs = classDeclCollector5.getChildren();
				methodget_decl_info8 = ModelTestUtils.getAssertMethod( classDeclCollector5Childs, "get_decl_info", 1 );
				ModelTestUtils.assertParameterNames( methodget_decl_info8, new String[] {"self"} );
			}
			//Function test:unknown_decl
			{
			IMethod methodunknown_decl9;
				IModelElement[] classDeclCollector5Childs = classDeclCollector5.getChildren();
				methodunknown_decl9 = ModelTestUtils.getAssertMethod( classDeclCollector5Childs, "unknown_decl", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_decl9, new String[] {"self", "data"} );
			}
		}
		//Class test
		{
		IType classHTMLParserTestCase10;
			IModelElement[] moduleChilds = module.getChildren();
			classHTMLParserTestCase10 = ModelTestUtils.getAssertClass( moduleChilds, "HTMLParserTestCase" );
			//Function test:test_anchor_collection
			{
			IMethod methodtest_anchor_collection11;
				IModelElement[] classHTMLParserTestCase10Childs = classHTMLParserTestCase10.getChildren();
				methodtest_anchor_collection11 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase10Childs, "test_anchor_collection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anchor_collection11, new String[] {"self"} );
			}
			//Function test:test_decl_collection
			{
			IMethod methodtest_decl_collection12;
				IModelElement[] classHTMLParserTestCase10Childs = classHTMLParserTestCase10.getChildren();
				methodtest_decl_collection12 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase10Childs, "test_decl_collection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decl_collection12, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main13 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen324( ) throws Exception {
		String prj = "pytests_6";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cfgparser.py"));

		assertNotNull("Module test_cfgparser.py not found", module);
		assertEquals("test_cfgparser.py", module.getElementName());
		
		//Class test
		{
		IType classTestCaseBase0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCaseBase0 = ModelTestUtils.getAssertClass( moduleChilds, "TestCaseBase" );
			//Function test:newconfig
			{
			IMethod methodnewconfig1;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodnewconfig1 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "newconfig", 2 );
				ModelTestUtils.assertParameterNames( methodnewconfig1, new String[] {"self", "defaults"} );
			}
			{
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCaseBase0Childs, "cf");
			}
			//Function test:fromstring
			{
			IMethod methodfromstring3;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodfromstring3 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "fromstring", 3 );
				ModelTestUtils.assertParameterNames( methodfromstring3, new String[] {"self", "string", "defaults"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic4;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_basic4 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic4, new String[] {"self"} );
			}
			//Function test:test_case_sensitivity
			{
			IMethod methodtest_case_sensitivity5;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_case_sensitivity5 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_case_sensitivity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_sensitivity5, new String[] {"self"} );
			}
			//Function test:test_default_case_sensitivity
			{
			IMethod methodtest_default_case_sensitivity6;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_default_case_sensitivity6 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_default_case_sensitivity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_case_sensitivity6, new String[] {"self"} );
			}
			//Function test:test_parse_errors
			{
			IMethod methodtest_parse_errors7;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_parse_errors7 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_parse_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parse_errors7, new String[] {"self"} );
			}
			//Function test:parse_error
			{
			IMethod methodparse_error8;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodparse_error8 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "parse_error", 3 );
				ModelTestUtils.assertParameterNames( methodparse_error8, new String[] {"self", "exc", "src"} );
			}
			//Function test:test_query_errors
			{
			IMethod methodtest_query_errors9;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_query_errors9 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_query_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_query_errors9, new String[] {"self"} );
			}
			//Function test:get_error
			{
			IMethod methodget_error10;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodget_error10 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "get_error", 4 );
				ModelTestUtils.assertParameterNames( methodget_error10, new String[] {"self", "exc", "section", "option"} );
			}
			//Function test:test_boolean
			{
			IMethod methodtest_boolean11;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_boolean11 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_boolean", 1 );
				ModelTestUtils.assertParameterNames( methodtest_boolean11, new String[] {"self"} );
			}
			//Function test:test_weird_errors
			{
			IMethod methodtest_weird_errors12;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_weird_errors12 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_weird_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weird_errors12, new String[] {"self"} );
			}
			//Function test:test_write
			{
			IMethod methodtest_write13;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_write13 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write13, new String[] {"self"} );
			}
			//Function test:test_set_string_types
			{
			IMethod methodtest_set_string_types14;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_set_string_types14 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_set_string_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_string_types14, new String[] {"self"} );
				//Class test
				{
				IType classmystr15;
					IModelElement[] methodtest_set_string_types14Childs = methodtest_set_string_types14.getChildren();
					classmystr15 = ModelTestUtils.getAssertClass( methodtest_set_string_types14Childs, "mystr" );
				}
			}
			//Function test:test_read_returns_file_list
			{
			IMethod methodtest_read_returns_file_list16;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_read_returns_file_list16 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_read_returns_file_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_returns_file_list16, new String[] {"self"} );
			}
			//Function test:get_interpolation_config
			{
			IMethod methodget_interpolation_config17;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodget_interpolation_config17 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "get_interpolation_config", 1 );
				ModelTestUtils.assertParameterNames( methodget_interpolation_config17, new String[] {"self"} );
			}
			//Function test:check_items_config
			{
			IMethod methodcheck_items_config18;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodcheck_items_config18 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "check_items_config", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_items_config18, new String[] {"self", "expected"} );
			}
		}
		//Class test
		{
		IType classConfigParserTestCase19;
			IModelElement[] moduleChilds = module.getChildren();
			classConfigParserTestCase19 = ModelTestUtils.getAssertClass( moduleChilds, "ConfigParserTestCase" );
			{
				IModelElement[] classConfigParserTestCase19Childs = classConfigParserTestCase19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classConfigParserTestCase19Childs, "config_class");
			}
			//Function test:test_interpolation
			{
			IMethod methodtest_interpolation20;
				IModelElement[] classConfigParserTestCase19Childs = classConfigParserTestCase19.getChildren();
				methodtest_interpolation20 = ModelTestUtils.getAssertMethod( classConfigParserTestCase19Childs, "test_interpolation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interpolation20, new String[] {"self"} );
			}
			//Function test:test_interpolation_missing_value
			{
			IMethod methodtest_interpolation_missing_value21;
				IModelElement[] classConfigParserTestCase19Childs = classConfigParserTestCase19.getChildren();
				methodtest_interpolation_missing_value21 = ModelTestUtils.getAssertMethod( classConfigParserTestCase19Childs, "test_interpolation_missing_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interpolation_missing_value21, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items22;
				IModelElement[] classConfigParserTestCase19Childs = classConfigParserTestCase19.getChildren();
				methodtest_items22 = ModelTestUtils.getAssertMethod( classConfigParserTestCase19Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items22, new String[] {"self"} );
			}
			//Function test:test_set_nonstring_types
			{
			IMethod methodtest_set_nonstring_types23;
				IModelElement[] classConfigParserTestCase19Childs = classConfigParserTestCase19.getChildren();
				methodtest_set_nonstring_types23 = ModelTestUtils.getAssertMethod( classConfigParserTestCase19Childs, "test_set_nonstring_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_nonstring_types23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRawConfigParserTestCase24;
			IModelElement[] moduleChilds = module.getChildren();
			classRawConfigParserTestCase24 = ModelTestUtils.getAssertClass( moduleChilds, "RawConfigParserTestCase" );
			{
				IModelElement[] classRawConfigParserTestCase24Childs = classRawConfigParserTestCase24.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRawConfigParserTestCase24Childs, "config_class");
			}
			//Function test:test_interpolation
			{
			IMethod methodtest_interpolation25;
				IModelElement[] classRawConfigParserTestCase24Childs = classRawConfigParserTestCase24.getChildren();
				methodtest_interpolation25 = ModelTestUtils.getAssertMethod( classRawConfigParserTestCase24Childs, "test_interpolation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interpolation25, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items26;
				IModelElement[] classRawConfigParserTestCase24Childs = classRawConfigParserTestCase24.getChildren();
				methodtest_items26 = ModelTestUtils.getAssertMethod( classRawConfigParserTestCase24Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items26, new String[] {"self"} );
			}
			//Function test:test_set_nonstring_types
			{
			IMethod methodtest_set_nonstring_types27;
				IModelElement[] classRawConfigParserTestCase24Childs = classRawConfigParserTestCase24.getChildren();
				methodtest_set_nonstring_types27 = ModelTestUtils.getAssertMethod( classRawConfigParserTestCase24Childs, "test_set_nonstring_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_nonstring_types27, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSafeConfigParserTestCase28;
			IModelElement[] moduleChilds = module.getChildren();
			classSafeConfigParserTestCase28 = ModelTestUtils.getAssertClass( moduleChilds, "SafeConfigParserTestCase" );
			{
				IModelElement[] classSafeConfigParserTestCase28Childs = classSafeConfigParserTestCase28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSafeConfigParserTestCase28Childs, "config_class");
			}
			//Function test:test_safe_interpolation
			{
			IMethod methodtest_safe_interpolation29;
				IModelElement[] classSafeConfigParserTestCase28Childs = classSafeConfigParserTestCase28.getChildren();
				methodtest_safe_interpolation29 = ModelTestUtils.getAssertMethod( classSafeConfigParserTestCase28Childs, "test_safe_interpolation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_safe_interpolation29, new String[] {"self"} );
			}
			//Function test:test_set_nonstring_types
			{
			IMethod methodtest_set_nonstring_types30;
				IModelElement[] classSafeConfigParserTestCase28Childs = classSafeConfigParserTestCase28.getChildren();
				methodtest_set_nonstring_types30 = ModelTestUtils.getAssertMethod( classSafeConfigParserTestCase28Childs, "test_set_nonstring_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_nonstring_types30, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}

}
	