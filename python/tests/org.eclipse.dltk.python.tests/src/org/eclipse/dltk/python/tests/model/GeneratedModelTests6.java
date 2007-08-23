
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
			//Function test:test_default_data
			{
			IMethod methodtest_default_data2;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_default_data2 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_default_data", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_data2, new String[] {"self"} );
			}
			//Function test:test_data_urls
			{
			IMethod methodtest_data_urls3;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_data_urls3 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_data_urls", 1 );
				ModelTestUtils.assertParameterNames( methodtest_data_urls3, new String[] {"self"} );
			}
			//Function test:test_file_parsing
			{
			IMethod methodtest_file_parsing4;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_file_parsing4 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_file_parsing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file_parsing4, new String[] {"self"} );
			}
			//Function test:test_non_standard_types
			{
			IMethod methodtest_non_standard_types5;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_non_standard_types5 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_non_standard_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_standard_types5, new String[] {"self"} );
			}
			//Function test:test_guess_all_types
			{
			IMethod methodtest_guess_all_types6;
				IModelElement[] classMimeTypesTestCase0Childs = classMimeTypesTestCase0.getChildren();
				methodtest_guess_all_types6 = ModelTestUtils.getAssertMethod( classMimeTypesTestCase0Childs, "test_guess_all_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_guess_all_types6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
					//Function test:__eq__
					{
					IMethod method__eq__28;
						IModelElement[] classEvilCmp26Childs = classEvilCmp26.getChildren();
						method__eq__28 = ModelTestUtils.getAssertMethod( classEvilCmp26Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__28, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reverse
			{
			IMethod methodtest_reverse29;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_reverse29 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_reverse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse29, new String[] {"self"} );
			}
			//Function test:test_sort
			{
			IMethod methodtest_sort30;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_sort30 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_sort", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sort30, new String[] {"self"} );
				//Function test:revcmp
				{
				IMethod methodrevcmp31;
					IModelElement[] methodtest_sort30Childs = methodtest_sort30.getChildren();
					methodrevcmp31 = ModelTestUtils.getAssertMethod( methodtest_sort30Childs, "revcmp", 2 );
					ModelTestUtils.assertParameterNames( methodrevcmp31, new String[] {"a", "b"} );
				}
				//Function test:myComparison
				{
				IMethod methodmyComparison32;
					IModelElement[] methodtest_sort30Childs = methodtest_sort30.getChildren();
					methodmyComparison32 = ModelTestUtils.getAssertMethod( methodtest_sort30Childs, "myComparison", 2 );
					ModelTestUtils.assertParameterNames( methodmyComparison32, new String[] {"x", "y"} );
				}
				//Function test:selfmodifyingComparison
				{
				IMethod methodselfmodifyingComparison33;
					IModelElement[] methodtest_sort30Childs = methodtest_sort30.getChildren();
					methodselfmodifyingComparison33 = ModelTestUtils.getAssertMethod( methodtest_sort30Childs, "selfmodifyingComparison", 2 );
					ModelTestUtils.assertParameterNames( methodselfmodifyingComparison33, new String[] {"x", "y"} );
				}
			}
			//Function test:test_slice
			{
			IMethod methodtest_slice34;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_slice34 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_slice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_slice34, new String[] {"self"} );
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd35;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_iadd35 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd35, new String[] {"self"} );
			}
			//Function test:test_imul
			{
			IMethod methodtest_imul36;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_imul36 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_imul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imul36, new String[] {"self"} );
			}
			//Function test:test_extendedslicing
			{
			IMethod methodtest_extendedslicing37;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_extendedslicing37 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_extendedslicing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extendedslicing37, new String[] {"self"} );
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
			//Function test:__len__
			{
			IMethod method__len__21;
				IModelElement[] classSequence19Childs = classSequence19.getChildren();
				method__len__21 = ModelTestUtils.getAssertMethod( classSequence19Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__21, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__22;
				IModelElement[] classSequence19Childs = classSequence19.getChildren();
				method__getitem__22 = ModelTestUtils.getAssertMethod( classSequence19Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__22, new String[] {"self", "i"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			//Function test:check
			{
			IMethod methodcheck20;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodcheck20 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "check", 3 );
				ModelTestUtils.assertParameterNames( methodcheck20, new String[] {"self", "expected", "label"} );
			}
			//Function test:CharacterDataHandler
			{
			IMethod methodCharacterDataHandler21;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodCharacterDataHandler21 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "CharacterDataHandler", 2 );
				ModelTestUtils.assertParameterNames( methodCharacterDataHandler21, new String[] {"self", "text"} );
			}
			//Function test:StartElementHandler
			{
			IMethod methodStartElementHandler22;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodStartElementHandler22 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "StartElementHandler", 3 );
				ModelTestUtils.assertParameterNames( methodStartElementHandler22, new String[] {"self", "name", "attrs"} );
			}
			//Function test:EndElementHandler
			{
			IMethod methodEndElementHandler23;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodEndElementHandler23 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "EndElementHandler", 2 );
				ModelTestUtils.assertParameterNames( methodEndElementHandler23, new String[] {"self", "name"} );
			}
			//Function test:CommentHandler
			{
			IMethod methodCommentHandler24;
				IModelElement[] classTextCollector18Childs = classTextCollector18.getChildren();
				methodCommentHandler24 = ModelTestUtils.getAssertMethod( classTextCollector18Childs, "CommentHandler", 2 );
				ModelTestUtils.assertParameterNames( methodCommentHandler24, new String[] {"self", "data"} );
			}
		}
		//Function test:require
		{
		IMethod methodrequire25;
			IModelElement[] moduleChilds = module.getChildren();
			methodrequire25 = ModelTestUtils.getAssertMethod( moduleChilds, "require", 2 );
			ModelTestUtils.assertParameterNames( methodrequire25, new String[] {"cond", "label"} );
		}
		//Function test:setup
		{
		IMethod methodsetup26;
			IModelElement[] moduleChilds = module.getChildren();
			methodsetup26 = ModelTestUtils.getAssertMethod( moduleChilds, "setup", 1 );
			ModelTestUtils.assertParameterNames( methodsetup26, new String[] {"handlers"} );
		}
		//Function test:StartElementHandler
		{
		IMethod methodStartElementHandler27;
			IModelElement[] moduleChilds = module.getChildren();
			methodStartElementHandler27 = ModelTestUtils.getAssertMethod( moduleChilds, "StartElementHandler", 2 );
			ModelTestUtils.assertParameterNames( methodStartElementHandler27, new String[] {"name", "attrs"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parser");
		}
		//Class test
		{
		IType classPositionTest28;
			IModelElement[] moduleChilds = module.getChildren();
			classPositionTest28 = ModelTestUtils.getAssertClass( moduleChilds, "PositionTest" );
			//Function test:__init__
			{
			IMethod method__init__29;
				IModelElement[] classPositionTest28Childs = classPositionTest28.getChildren();
				method__init__29 = ModelTestUtils.getAssertMethod( classPositionTest28Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__29, new String[] {"self", "expected_list", "parser"} );
			}
			//Function test:StartElementHandler
			{
			IMethod methodStartElementHandler30;
				IModelElement[] classPositionTest28Childs = classPositionTest28.getChildren();
				methodStartElementHandler30 = ModelTestUtils.getAssertMethod( classPositionTest28Childs, "StartElementHandler", 3 );
				ModelTestUtils.assertParameterNames( methodStartElementHandler30, new String[] {"self", "name", "attrs"} );
			}
			//Function test:EndElementHandler
			{
			IMethod methodEndElementHandler31;
				IModelElement[] classPositionTest28Childs = classPositionTest28.getChildren();
				methodEndElementHandler31 = ModelTestUtils.getAssertMethod( classPositionTest28Childs, "EndElementHandler", 2 );
				ModelTestUtils.assertParameterNames( methodEndElementHandler31, new String[] {"self", "name"} );
			}
			//Function test:check_pos
			{
			IMethod methodcheck_pos32;
				IModelElement[] classPositionTest28Childs = classPositionTest28.getChildren();
				methodcheck_pos32 = ModelTestUtils.getAssertMethod( classPositionTest28Childs, "check_pos", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_pos32, new String[] {"self", "event"} );
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
				IField fieldValue = ModelTestUtils.getAssertField( classTestShelveBase6Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference8;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				method_reference8 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference8, new String[] {"self"} );
			}
			//Function test:_empty_mapping
			{
			IMethod method_empty_mapping9;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				method_empty_mapping9 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "_empty_mapping", 1 );
				ModelTestUtils.assertParameterNames( method_empty_mapping9, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown10;
				IModelElement[] classTestShelveBase6Childs = classTestShelveBase6.getChildren();
				methodtearDown10 = ModelTestUtils.getAssertMethod( classTestShelveBase6Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestAsciiFileShelve11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestAsciiFileShelve11 = ModelTestUtils.getAssertClass( moduleChilds, "TestAsciiFileShelve" );
			{
				IModelElement[] classTestAsciiFileShelve11Childs = classTestAsciiFileShelve11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiFileShelve11Childs, "_args");
			}
			{
				IModelElement[] classTestAsciiFileShelve11Childs = classTestAsciiFileShelve11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiFileShelve11Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestBinaryFileShelve12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryFileShelve12 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryFileShelve" );
			{
				IModelElement[] classTestBinaryFileShelve12Childs = classTestBinaryFileShelve12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryFileShelve12Childs, "_args");
			}
			{
				IModelElement[] classTestBinaryFileShelve12Childs = classTestBinaryFileShelve12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryFileShelve12Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestProto2FileShelve13;
			IModelElement[] moduleChilds = module.getChildren();
			classTestProto2FileShelve13 = ModelTestUtils.getAssertClass( moduleChilds, "TestProto2FileShelve" );
			{
				IModelElement[] classTestProto2FileShelve13Childs = classTestProto2FileShelve13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2FileShelve13Childs, "_args");
			}
			{
				IModelElement[] classTestProto2FileShelve13Childs = classTestProto2FileShelve13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2FileShelve13Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestAsciiMemShelve14;
			IModelElement[] moduleChilds = module.getChildren();
			classTestAsciiMemShelve14 = ModelTestUtils.getAssertClass( moduleChilds, "TestAsciiMemShelve" );
			{
				IModelElement[] classTestAsciiMemShelve14Childs = classTestAsciiMemShelve14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiMemShelve14Childs, "_args");
			}
			{
				IModelElement[] classTestAsciiMemShelve14Childs = classTestAsciiMemShelve14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAsciiMemShelve14Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestBinaryMemShelve15;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryMemShelve15 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryMemShelve" );
			{
				IModelElement[] classTestBinaryMemShelve15Childs = classTestBinaryMemShelve15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryMemShelve15Childs, "_args");
			}
			{
				IModelElement[] classTestBinaryMemShelve15Childs = classTestBinaryMemShelve15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryMemShelve15Childs, "_in_mem");
			}
		}
		//Class test
		{
		IType classTestProto2MemShelve16;
			IModelElement[] moduleChilds = module.getChildren();
			classTestProto2MemShelve16 = ModelTestUtils.getAssertClass( moduleChilds, "TestProto2MemShelve" );
			{
				IModelElement[] classTestProto2MemShelve16Childs = classTestProto2MemShelve16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2MemShelve16Childs, "_args");
			}
			{
				IModelElement[] classTestProto2MemShelve16Childs = classTestProto2MemShelve16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestProto2MemShelve16Childs, "_in_mem");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			//Function test:__radd__
			{
			IMethod method__radd__2;
				IModelElement[] classaug_test0Childs = classaug_test0.getChildren();
				method__radd__2 = ModelTestUtils.getAssertMethod( classaug_test0Childs, "__radd__", 2 );
				ModelTestUtils.assertParameterNames( method__radd__2, new String[] {"self", "val"} );
			}
			//Function test:__add__
			{
			IMethod method__add__3;
				IModelElement[] classaug_test0Childs = classaug_test0.getChildren();
				method__add__3 = ModelTestUtils.getAssertMethod( classaug_test0Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__3, new String[] {"self", "val"} );
			}
		}
		//Class test
		{
		IType classaug_test24;
			IModelElement[] moduleChilds = module.getChildren();
			classaug_test24 = ModelTestUtils.getAssertClass( moduleChilds, "aug_test2" );
			//Function test:__iadd__
			{
			IMethod method__iadd__5;
				IModelElement[] classaug_test24Childs = classaug_test24.getChildren();
				method__iadd__5 = ModelTestUtils.getAssertMethod( classaug_test24Childs, "__iadd__", 2 );
				ModelTestUtils.assertParameterNames( method__iadd__5, new String[] {"self", "val"} );
			}
		}
		//Class test
		{
		IType classaug_test36;
			IModelElement[] moduleChilds = module.getChildren();
			classaug_test36 = ModelTestUtils.getAssertClass( moduleChilds, "aug_test3" );
			//Function test:__iadd__
			{
			IMethod method__iadd__7;
				IModelElement[] classaug_test36Childs = classaug_test36.getChildren();
				method__iadd__7 = ModelTestUtils.getAssertMethod( classaug_test36Childs, "__iadd__", 2 );
				ModelTestUtils.assertParameterNames( method__iadd__7, new String[] {"self", "val"} );
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
		IType classtestall8;
			IModelElement[] moduleChilds = module.getChildren();
			classtestall8 = ModelTestUtils.getAssertClass( moduleChilds, "testall" );
			//Function test:__add__
			{
			IMethod method__add__9;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__add__9 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__9, new String[] {"self", "val"} );
			}
			//Function test:__radd__
			{
			IMethod method__radd__10;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__radd__10 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__radd__", 2 );
				ModelTestUtils.assertParameterNames( method__radd__10, new String[] {"self", "val"} );
			}
			//Function test:__iadd__
			{
			IMethod method__iadd__11;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__iadd__11 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__iadd__", 2 );
				ModelTestUtils.assertParameterNames( method__iadd__11, new String[] {"self", "val"} );
			}
			//Function test:__sub__
			{
			IMethod method__sub__12;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__sub__12 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__sub__", 2 );
				ModelTestUtils.assertParameterNames( method__sub__12, new String[] {"self", "val"} );
			}
			//Function test:__rsub__
			{
			IMethod method__rsub__13;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rsub__13 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rsub__", 2 );
				ModelTestUtils.assertParameterNames( method__rsub__13, new String[] {"self", "val"} );
			}
			//Function test:__isub__
			{
			IMethod method__isub__14;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__isub__14 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__isub__", 2 );
				ModelTestUtils.assertParameterNames( method__isub__14, new String[] {"self", "val"} );
			}
			//Function test:__mul__
			{
			IMethod method__mul__15;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__mul__15 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__mul__", 2 );
				ModelTestUtils.assertParameterNames( method__mul__15, new String[] {"self", "val"} );
			}
			//Function test:__rmul__
			{
			IMethod method__rmul__16;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rmul__16 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rmul__", 2 );
				ModelTestUtils.assertParameterNames( method__rmul__16, new String[] {"self", "val"} );
			}
			//Function test:__imul__
			{
			IMethod method__imul__17;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__imul__17 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__imul__", 2 );
				ModelTestUtils.assertParameterNames( method__imul__17, new String[] {"self", "val"} );
			}
			//Function test:__div__
			{
			IMethod method__div__18;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__div__18 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__div__", 2 );
				ModelTestUtils.assertParameterNames( method__div__18, new String[] {"self", "val"} );
			}
			//Function test:__rdiv__
			{
			IMethod method__rdiv__19;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rdiv__19 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rdiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rdiv__19, new String[] {"self", "val"} );
			}
			//Function test:__idiv__
			{
			IMethod method__idiv__20;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__idiv__20 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__idiv__", 2 );
				ModelTestUtils.assertParameterNames( method__idiv__20, new String[] {"self", "val"} );
			}
			//Function test:__floordiv__
			{
			IMethod method__floordiv__21;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__floordiv__21 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__floordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__floordiv__21, new String[] {"self", "val"} );
			}
			//Function test:__ifloordiv__
			{
			IMethod method__ifloordiv__22;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__ifloordiv__22 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__ifloordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__ifloordiv__22, new String[] {"self", "val"} );
			}
			//Function test:__rfloordiv__
			{
			IMethod method__rfloordiv__23;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rfloordiv__23 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rfloordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rfloordiv__23, new String[] {"self", "val"} );
			}
			//Function test:__truediv__
			{
			IMethod method__truediv__24;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__truediv__24 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__truediv__", 2 );
				ModelTestUtils.assertParameterNames( method__truediv__24, new String[] {"self", "val"} );
			}
			//Function test:__itruediv__
			{
			IMethod method__itruediv__25;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__itruediv__25 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__itruediv__", 2 );
				ModelTestUtils.assertParameterNames( method__itruediv__25, new String[] {"self", "val"} );
			}
			//Function test:__mod__
			{
			IMethod method__mod__26;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__mod__26 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__mod__", 2 );
				ModelTestUtils.assertParameterNames( method__mod__26, new String[] {"self", "val"} );
			}
			//Function test:__rmod__
			{
			IMethod method__rmod__27;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rmod__27 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rmod__27, new String[] {"self", "val"} );
			}
			//Function test:__imod__
			{
			IMethod method__imod__28;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__imod__28 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__imod__", 2 );
				ModelTestUtils.assertParameterNames( method__imod__28, new String[] {"self", "val"} );
			}
			//Function test:__pow__
			{
			IMethod method__pow__29;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__pow__29 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__pow__", 2 );
				ModelTestUtils.assertParameterNames( method__pow__29, new String[] {"self", "val"} );
			}
			//Function test:__rpow__
			{
			IMethod method__rpow__30;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rpow__30 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rpow__", 2 );
				ModelTestUtils.assertParameterNames( method__rpow__30, new String[] {"self", "val"} );
			}
			//Function test:__ipow__
			{
			IMethod method__ipow__31;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__ipow__31 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__ipow__", 2 );
				ModelTestUtils.assertParameterNames( method__ipow__31, new String[] {"self", "val"} );
			}
			//Function test:__or__
			{
			IMethod method__or__32;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__or__32 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__or__", 2 );
				ModelTestUtils.assertParameterNames( method__or__32, new String[] {"self", "val"} );
			}
			//Function test:__ror__
			{
			IMethod method__ror__33;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__ror__33 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__ror__", 2 );
				ModelTestUtils.assertParameterNames( method__ror__33, new String[] {"self", "val"} );
			}
			//Function test:__ior__
			{
			IMethod method__ior__34;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__ior__34 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__ior__", 2 );
				ModelTestUtils.assertParameterNames( method__ior__34, new String[] {"self", "val"} );
			}
			//Function test:__and__
			{
			IMethod method__and__35;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__and__35 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__and__", 2 );
				ModelTestUtils.assertParameterNames( method__and__35, new String[] {"self", "val"} );
			}
			//Function test:__rand__
			{
			IMethod method__rand__36;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rand__36 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rand__", 2 );
				ModelTestUtils.assertParameterNames( method__rand__36, new String[] {"self", "val"} );
			}
			//Function test:__iand__
			{
			IMethod method__iand__37;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__iand__37 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__iand__", 2 );
				ModelTestUtils.assertParameterNames( method__iand__37, new String[] {"self", "val"} );
			}
			//Function test:__xor__
			{
			IMethod method__xor__38;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__xor__38 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__xor__", 2 );
				ModelTestUtils.assertParameterNames( method__xor__38, new String[] {"self", "val"} );
			}
			//Function test:__rxor__
			{
			IMethod method__rxor__39;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rxor__39 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rxor__", 2 );
				ModelTestUtils.assertParameterNames( method__rxor__39, new String[] {"self", "val"} );
			}
			//Function test:__ixor__
			{
			IMethod method__ixor__40;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__ixor__40 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__ixor__", 2 );
				ModelTestUtils.assertParameterNames( method__ixor__40, new String[] {"self", "val"} );
			}
			//Function test:__rshift__
			{
			IMethod method__rshift__41;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rshift__41 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rshift__", 2 );
				ModelTestUtils.assertParameterNames( method__rshift__41, new String[] {"self", "val"} );
			}
			//Function test:__rrshift__
			{
			IMethod method__rrshift__42;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rrshift__42 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rrshift__", 2 );
				ModelTestUtils.assertParameterNames( method__rrshift__42, new String[] {"self", "val"} );
			}
			//Function test:__irshift__
			{
			IMethod method__irshift__43;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__irshift__43 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__irshift__", 2 );
				ModelTestUtils.assertParameterNames( method__irshift__43, new String[] {"self", "val"} );
			}
			//Function test:__lshift__
			{
			IMethod method__lshift__44;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__lshift__44 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__lshift__", 2 );
				ModelTestUtils.assertParameterNames( method__lshift__44, new String[] {"self", "val"} );
			}
			//Function test:__rlshift__
			{
			IMethod method__rlshift__45;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__rlshift__45 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__rlshift__", 2 );
				ModelTestUtils.assertParameterNames( method__rlshift__45, new String[] {"self", "val"} );
			}
			//Function test:__ilshift__
			{
			IMethod method__ilshift__46;
				IModelElement[] classtestall8Childs = classtestall8.getChildren();
				method__ilshift__46 = ModelTestUtils.getAssertMethod( classtestall8Childs, "__ilshift__", 2 );
				ModelTestUtils.assertParameterNames( method__ilshift__46, new String[] {"self", "val"} );
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
			//Function test:test_chunkcoding
			{
			IMethod methodtest_chunkcoding2;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_chunkcoding2 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_chunkcoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chunkcoding2, new String[] {"self"} );
			}
			//Function test:test_errorhandle
			{
			IMethod methodtest_errorhandle3;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_errorhandle3 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_errorhandle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errorhandle3, new String[] {"self"} );
			}
			//Function test:test_xmlcharrefreplace
			{
			IMethod methodtest_xmlcharrefreplace4;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_xmlcharrefreplace4 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_xmlcharrefreplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharrefreplace4, new String[] {"self"} );
			}
			//Function test:test_customreplace
			{
			IMethod methodtest_customreplace5;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_customreplace5 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_customreplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_customreplace5, new String[] {"self"} );
				//Function test:xmlcharnamereplace
				{
				IMethod methodxmlcharnamereplace6;
					IModelElement[] methodtest_customreplace5Childs = methodtest_customreplace5.getChildren();
					methodxmlcharnamereplace6 = ModelTestUtils.getAssertMethod( methodtest_customreplace5Childs, "xmlcharnamereplace", 1 );
					ModelTestUtils.assertParameterNames( methodxmlcharnamereplace6, new String[] {"exc"} );
				}
			}
			//Function test:test_streamreader
			{
			IMethod methodtest_streamreader7;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_streamreader7 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_streamreader", 1 );
				ModelTestUtils.assertParameterNames( methodtest_streamreader7, new String[] {"self"} );
			}
			//Function test:test_streamwriter
			{
			IMethod methodtest_streamwriter8;
				IModelElement[] classTestBase0Childs = classTestBase0.getChildren();
				methodtest_streamwriter8 = ModelTestUtils.getAssertMethod( classTestBase0Childs, "test_streamwriter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_streamwriter8, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_unichr");
		}
		//Function test:unichr
		{
		IMethod methodunichr9;
			IModelElement[] moduleChilds = module.getChildren();
			methodunichr9 = ModelTestUtils.getAssertMethod( moduleChilds, "unichr", 1 );
			ModelTestUtils.assertParameterNames( methodunichr9, new String[] {"v"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_ord");
		}
		//Function test:ord
		{
		IMethod methodord10;
			IModelElement[] moduleChilds = module.getChildren();
			methodord10 = ModelTestUtils.getAssertMethod( moduleChilds, "ord", 1 );
			ModelTestUtils.assertParameterNames( methodord10, new String[] {"c"} );
		}
		//Class test
		{
		IType classTestBase_Mapping11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBase_Mapping11 = ModelTestUtils.getAssertClass( moduleChilds, "TestBase_Mapping" );
			{
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase_Mapping11Childs, "pass_enctest");
			}
			{
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase_Mapping11Childs, "pass_dectest");
			}
			{
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBase_Mapping11Childs, "supmaps");
			}
			//Function test:__init__
			{
			IMethod method__init__12;
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				method__init__12 = ModelTestUtils.getAssertMethod( classTestBase_Mapping11Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self", "args", "kw"} );
			}
			//Function test:test_mapping_file
			{
			IMethod methodtest_mapping_file13;
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				methodtest_mapping_file13 = ModelTestUtils.getAssertMethod( classTestBase_Mapping11Childs, "test_mapping_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mapping_file13, new String[] {"self"} );
			}
			//Function test:test_mapping_supplemental
			{
			IMethod methodtest_mapping_supplemental14;
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				methodtest_mapping_supplemental14 = ModelTestUtils.getAssertMethod( classTestBase_Mapping11Childs, "test_mapping_supplemental", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mapping_supplemental14, new String[] {"self"} );
			}
			//Function test:_testpoint
			{
			IMethod method_testpoint15;
				IModelElement[] classTestBase_Mapping11Childs = classTestBase_Mapping11.getChildren();
				method_testpoint15 = ModelTestUtils.getAssertMethod( classTestBase_Mapping11Childs, "_testpoint", 3 );
				ModelTestUtils.assertParameterNames( method_testpoint15, new String[] {"self", "csetch", "unich"} );
			}
		}
		//Function test:load_teststring
		{
		IMethod methodload_teststring16;
			IModelElement[] moduleChilds = module.getChildren();
			methodload_teststring16 = ModelTestUtils.getAssertMethod( moduleChilds, "load_teststring", 1 );
			ModelTestUtils.assertParameterNames( methodload_teststring16, new String[] {"encoding"} );
		}
		//Function test:register_skip_expected
		{
		IMethod methodregister_skip_expected17;
			IModelElement[] moduleChilds = module.getChildren();
			methodregister_skip_expected17 = ModelTestUtils.getAssertMethod( moduleChilds, "register_skip_expected", 1 );
			ModelTestUtils.assertParameterNames( methodregister_skip_expected17, new String[] {"cases"} );
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
			//Function test:test_basic
			{
			IMethod methodtest_basic12;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_basic12 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic12, new String[] {"self"} );
			}
			//Function test:test_knotted
			{
			IMethod methodtest_knotted13;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_knotted13 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_knotted", 1 );
				ModelTestUtils.assertParameterNames( methodtest_knotted13, new String[] {"self"} );
			}
			//Function test:test_unreadable
			{
			IMethod methodtest_unreadable14;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_unreadable14 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_unreadable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unreadable14, new String[] {"self"} );
			}
			//Function test:test_same_as_repr
			{
			IMethod methodtest_same_as_repr15;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_same_as_repr15 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_same_as_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_same_as_repr15, new String[] {"self"} );
			}
			//Function test:test_basic_line_wrap
			{
			IMethod methodtest_basic_line_wrap16;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_basic_line_wrap16 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_basic_line_wrap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_line_wrap16, new String[] {"self"} );
			}
			//Function test:test_subclassing
			{
			IMethod methodtest_subclassing17;
				IModelElement[] classQueryTestCase10Childs = classQueryTestCase10.getChildren();
				methodtest_subclassing17 = ModelTestUtils.getAssertMethod( classQueryTestCase10Childs, "test_subclassing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclassing17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDottedPrettyPrinter18;
			IModelElement[] moduleChilds = module.getChildren();
			classDottedPrettyPrinter18 = ModelTestUtils.getAssertClass( moduleChilds, "DottedPrettyPrinter" );
			//Function test:format
			{
			IMethod methodformat19;
				IModelElement[] classDottedPrettyPrinter18Childs = classDottedPrettyPrinter18.getChildren();
				methodformat19 = ModelTestUtils.getAssertMethod( classDottedPrettyPrinter18Childs, "format", 5 );
				ModelTestUtils.assertParameterNames( methodformat19, new String[] {"self", "object", "context", "maxlevels", "level"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
					//Function test:__copy__
					{
					IMethod method__copy__6;
						IModelElement[] classC4Childs = classC4.getChildren();
						method__copy__6 = ModelTestUtils.getAssertMethod( classC4Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__6, new String[] {"self"} );
					}
				}
			}
			//Function test:test_copy_registry
			{
			IMethod methodtest_copy_registry7;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_registry7 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_registry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_registry7, new String[] {"self"} );
				//Class test
				{
				IType classC8;
					IModelElement[] methodtest_copy_registry7Childs = methodtest_copy_registry7.getChildren();
					classC8 = ModelTestUtils.getAssertClass( methodtest_copy_registry7Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__9;
						IModelElement[] classC8Childs = classC8.getChildren();
						method__new__9 = ModelTestUtils.getAssertMethod( classC8Childs, "__new__", 2 );
						ModelTestUtils.assertParameterNames( method__new__9, new String[] {"cls", "foo"} );
					}
				}
				//Function test:pickle_C
				{
				IMethod methodpickle_C10;
					IModelElement[] methodtest_copy_registry7Childs = methodtest_copy_registry7.getChildren();
					methodpickle_C10 = ModelTestUtils.getAssertMethod( methodtest_copy_registry7Childs, "pickle_C", 1 );
					ModelTestUtils.assertParameterNames( methodpickle_C10, new String[] {"obj"} );
				}
			}
			//Function test:test_copy_reduce_ex
			{
			IMethod methodtest_copy_reduce_ex11;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_reduce_ex11 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_reduce_ex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_reduce_ex11, new String[] {"self"} );
				//Class test
				{
				IType classC12;
					IModelElement[] methodtest_copy_reduce_ex11Childs = methodtest_copy_reduce_ex11.getChildren();
					classC12 = ModelTestUtils.getAssertClass( methodtest_copy_reduce_ex11Childs, "C" );
					//Function test:__reduce_ex__
					{
					IMethod method__reduce_ex__13;
						IModelElement[] classC12Childs = classC12.getChildren();
						method__reduce_ex__13 = ModelTestUtils.getAssertMethod( classC12Childs, "__reduce_ex__", 2 );
						ModelTestUtils.assertParameterNames( method__reduce_ex__13, new String[] {"self", "proto"} );
					}
					//Function test:__reduce__
					{
					IMethod method__reduce__14;
						IModelElement[] classC12Childs = classC12.getChildren();
						method__reduce__14 = ModelTestUtils.getAssertMethod( classC12Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__14, new String[] {"self"} );
					}
				}
			}
			//Function test:test_copy_reduce
			{
			IMethod methodtest_copy_reduce15;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_reduce15 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_reduce15, new String[] {"self"} );
				//Class test
				{
				IType classC16;
					IModelElement[] methodtest_copy_reduce15Childs = methodtest_copy_reduce15.getChildren();
					classC16 = ModelTestUtils.getAssertClass( methodtest_copy_reduce15Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__17;
						IModelElement[] classC16Childs = classC16.getChildren();
						method__reduce__17 = ModelTestUtils.getAssertMethod( classC16Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__17, new String[] {"self"} );
					}
				}
			}
			//Function test:test_copy_cant
			{
			IMethod methodtest_copy_cant18;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_cant18 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_cant", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_cant18, new String[] {"self"} );
				//Class test
				{
				IType classC19;
					IModelElement[] methodtest_copy_cant18Childs = methodtest_copy_cant18.getChildren();
					classC19 = ModelTestUtils.getAssertClass( methodtest_copy_cant18Childs, "C" );
					//Function test:__getattribute__
					{
					IMethod method__getattribute__20;
						IModelElement[] classC19Childs = classC19.getChildren();
						method__getattribute__20 = ModelTestUtils.getAssertMethod( classC19Childs, "__getattribute__", 2 );
						ModelTestUtils.assertParameterNames( method__getattribute__20, new String[] {"self", "name"} );
					}
				}
			}
			//Function test:test_copy_atomic
			{
			IMethod methodtest_copy_atomic21;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_atomic21 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_atomic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_atomic21, new String[] {"self"} );
				//Class test
				{
				IType classClassic22;
					IModelElement[] methodtest_copy_atomic21Childs = methodtest_copy_atomic21.getChildren();
					classClassic22 = ModelTestUtils.getAssertClass( methodtest_copy_atomic21Childs, "Classic" );
				}
				//Class test
				{
				IType classNewStyle23;
					IModelElement[] methodtest_copy_atomic21Childs = methodtest_copy_atomic21.getChildren();
					classNewStyle23 = ModelTestUtils.getAssertClass( methodtest_copy_atomic21Childs, "NewStyle" );
				}
				//Function test:f
				{
				IMethod methodf24;
					IModelElement[] methodtest_copy_atomic21Childs = methodtest_copy_atomic21.getChildren();
					methodf24 = ModelTestUtils.getAssertMethod( methodtest_copy_atomic21Childs, "f", 0 );
				}
			}
			//Function test:test_copy_list
			{
			IMethod methodtest_copy_list25;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_list25 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_list25, new String[] {"self"} );
			}
			//Function test:test_copy_tuple
			{
			IMethod methodtest_copy_tuple26;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_tuple26 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_tuple26, new String[] {"self"} );
			}
			//Function test:test_copy_dict
			{
			IMethod methodtest_copy_dict27;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_dict27 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_dict27, new String[] {"self"} );
			}
			//Function test:test_copy_inst_vanilla
			{
			IMethod methodtest_copy_inst_vanilla28;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_vanilla28 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_vanilla", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_vanilla28, new String[] {"self"} );
				//Class test
				{
				IType classC29;
					IModelElement[] methodtest_copy_inst_vanilla28Childs = methodtest_copy_inst_vanilla28.getChildren();
					classC29 = ModelTestUtils.getAssertClass( methodtest_copy_inst_vanilla28Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__30;
						IModelElement[] classC29Childs = classC29.getChildren();
						method__init__30 = ModelTestUtils.getAssertMethod( classC29Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__30, new String[] {"self", "foo"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__31;
						IModelElement[] classC29Childs = classC29.getChildren();
						method__cmp__31 = ModelTestUtils.getAssertMethod( classC29Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__31, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_copy
			{
			IMethod methodtest_copy_inst_copy32;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_copy32 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_copy32, new String[] {"self"} );
				//Class test
				{
				IType classC33;
					IModelElement[] methodtest_copy_inst_copy32Childs = methodtest_copy_inst_copy32.getChildren();
					classC33 = ModelTestUtils.getAssertClass( methodtest_copy_inst_copy32Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__34;
						IModelElement[] classC33Childs = classC33.getChildren();
						method__init__34 = ModelTestUtils.getAssertMethod( classC33Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__34, new String[] {"self", "foo"} );
					}
					//Function test:__copy__
					{
					IMethod method__copy__35;
						IModelElement[] classC33Childs = classC33.getChildren();
						method__copy__35 = ModelTestUtils.getAssertMethod( classC33Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__35, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__36;
						IModelElement[] classC33Childs = classC33.getChildren();
						method__cmp__36 = ModelTestUtils.getAssertMethod( classC33Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__36, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_getinitargs
			{
			IMethod methodtest_copy_inst_getinitargs37;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_getinitargs37 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_getinitargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_getinitargs37, new String[] {"self"} );
				//Class test
				{
				IType classC38;
					IModelElement[] methodtest_copy_inst_getinitargs37Childs = methodtest_copy_inst_getinitargs37.getChildren();
					classC38 = ModelTestUtils.getAssertClass( methodtest_copy_inst_getinitargs37Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__39;
						IModelElement[] classC38Childs = classC38.getChildren();
						method__init__39 = ModelTestUtils.getAssertMethod( classC38Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__39, new String[] {"self", "foo"} );
					}
					//Function test:__getinitargs__
					{
					IMethod method__getinitargs__40;
						IModelElement[] classC38Childs = classC38.getChildren();
						method__getinitargs__40 = ModelTestUtils.getAssertMethod( classC38Childs, "__getinitargs__", 1 );
						ModelTestUtils.assertParameterNames( method__getinitargs__40, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__41;
						IModelElement[] classC38Childs = classC38.getChildren();
						method__cmp__41 = ModelTestUtils.getAssertMethod( classC38Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__41, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_getstate
			{
			IMethod methodtest_copy_inst_getstate42;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_getstate42 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_getstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_getstate42, new String[] {"self"} );
				//Class test
				{
				IType classC43;
					IModelElement[] methodtest_copy_inst_getstate42Childs = methodtest_copy_inst_getstate42.getChildren();
					classC43 = ModelTestUtils.getAssertClass( methodtest_copy_inst_getstate42Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__44;
						IModelElement[] classC43Childs = classC43.getChildren();
						method__init__44 = ModelTestUtils.getAssertMethod( classC43Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__44, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__45;
						IModelElement[] classC43Childs = classC43.getChildren();
						method__getstate__45 = ModelTestUtils.getAssertMethod( classC43Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__45, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__46;
						IModelElement[] classC43Childs = classC43.getChildren();
						method__cmp__46 = ModelTestUtils.getAssertMethod( classC43Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__46, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_setstate
			{
			IMethod methodtest_copy_inst_setstate47;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_setstate47 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_setstate47, new String[] {"self"} );
				//Class test
				{
				IType classC48;
					IModelElement[] methodtest_copy_inst_setstate47Childs = methodtest_copy_inst_setstate47.getChildren();
					classC48 = ModelTestUtils.getAssertClass( methodtest_copy_inst_setstate47Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__49;
						IModelElement[] classC48Childs = classC48.getChildren();
						method__init__49 = ModelTestUtils.getAssertMethod( classC48Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__49, new String[] {"self", "foo"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__50;
						IModelElement[] classC48Childs = classC48.getChildren();
						method__setstate__50 = ModelTestUtils.getAssertMethod( classC48Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__50, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__51;
						IModelElement[] classC48Childs = classC48.getChildren();
						method__cmp__51 = ModelTestUtils.getAssertMethod( classC48Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__51, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_inst_getstate_setstate
			{
			IMethod methodtest_copy_inst_getstate_setstate52;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_inst_getstate_setstate52 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_inst_getstate_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_inst_getstate_setstate52, new String[] {"self"} );
				//Class test
				{
				IType classC53;
					IModelElement[] methodtest_copy_inst_getstate_setstate52Childs = methodtest_copy_inst_getstate_setstate52.getChildren();
					classC53 = ModelTestUtils.getAssertClass( methodtest_copy_inst_getstate_setstate52Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__54;
						IModelElement[] classC53Childs = classC53.getChildren();
						method__init__54 = ModelTestUtils.getAssertMethod( classC53Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__54, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__55;
						IModelElement[] classC53Childs = classC53.getChildren();
						method__getstate__55 = ModelTestUtils.getAssertMethod( classC53Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__55, new String[] {"self"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__56;
						IModelElement[] classC53Childs = classC53.getChildren();
						method__setstate__56 = ModelTestUtils.getAssertMethod( classC53Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__56, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__57;
						IModelElement[] classC53Childs = classC53.getChildren();
						method__cmp__57 = ModelTestUtils.getAssertMethod( classC53Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__57, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_classictype
			{
			IMethod methodtest_copy_classictype58;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_classictype58 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_classictype", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_classictype58, new String[] {"self"} );
			}
			//Function test:test_deepcopy_classictype
			{
			IMethod methodtest_deepcopy_classictype59;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_classictype59 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_classictype", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_classictype59, new String[] {"self"} );
			}
			//Function test:test_copy_classoverinstance
			{
			IMethod methodtest_copy_classoverinstance60;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_classoverinstance60 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_classoverinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_classoverinstance60, new String[] {"self"} );
				//Class test
				{
				IType classC61;
					IModelElement[] methodtest_copy_classoverinstance60Childs = methodtest_copy_classoverinstance60.getChildren();
					classC61 = ModelTestUtils.getAssertClass( methodtest_copy_classoverinstance60Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__62;
						IModelElement[] classC61Childs = classC61.getChildren();
						method__init__62 = ModelTestUtils.getAssertMethod( classC61Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__62, new String[] {"self", "v"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__63;
						IModelElement[] classC61Childs = classC61.getChildren();
						method__cmp__63 = ModelTestUtils.getAssertMethod( classC61Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__63, new String[] {"self", "other"} );
					}
					//Function test:__copy__
					{
					IMethod method__copy__64;
						IModelElement[] classC61Childs = classC61.getChildren();
						method__copy__64 = ModelTestUtils.getAssertMethod( classC61Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__64, new String[] {"self"} );
					}
				}
			}
			//Function test:test_deepcopy_classoverinstance
			{
			IMethod methodtest_deepcopy_classoverinstance65;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_classoverinstance65 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_classoverinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_classoverinstance65, new String[] {"self"} );
				//Class test
				{
				IType classC66;
					IModelElement[] methodtest_deepcopy_classoverinstance65Childs = methodtest_deepcopy_classoverinstance65.getChildren();
					classC66 = ModelTestUtils.getAssertClass( methodtest_deepcopy_classoverinstance65Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__67;
						IModelElement[] classC66Childs = classC66.getChildren();
						method__init__67 = ModelTestUtils.getAssertMethod( classC66Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__67, new String[] {"self", "v"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__68;
						IModelElement[] classC66Childs = classC66.getChildren();
						method__cmp__68 = ModelTestUtils.getAssertMethod( classC66Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__68, new String[] {"self", "other"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__69;
						IModelElement[] classC66Childs = classC66.getChildren();
						method__deepcopy__69 = ModelTestUtils.getAssertMethod( classC66Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__69, new String[] {"self", "memo"} );
					}
				}
			}
			//Function test:test_copy_metaclassconfusion
			{
			IMethod methodtest_copy_metaclassconfusion70;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_metaclassconfusion70 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_metaclassconfusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_metaclassconfusion70, new String[] {"self"} );
				//Class test
				{
				IType classMyOwnError71;
					IModelElement[] methodtest_copy_metaclassconfusion70Childs = methodtest_copy_metaclassconfusion70.getChildren();
					classMyOwnError71 = ModelTestUtils.getAssertClass( methodtest_copy_metaclassconfusion70Childs, "MyOwnError" );
				}
				//Class test
				{
				IType classMeta72;
					IModelElement[] methodtest_copy_metaclassconfusion70Childs = methodtest_copy_metaclassconfusion70.getChildren();
					classMeta72 = ModelTestUtils.getAssertClass( methodtest_copy_metaclassconfusion70Childs, "Meta" );
					//Function test:__copy__
					{
					IMethod method__copy__73;
						IModelElement[] classMeta72Childs = classMeta72.getChildren();
						method__copy__73 = ModelTestUtils.getAssertMethod( classMeta72Childs, "__copy__", 1 );
						ModelTestUtils.assertParameterNames( method__copy__73, new String[] {"cls"} );
					}
				}
				//Class test
				{
				IType classC74;
					IModelElement[] methodtest_copy_metaclassconfusion70Childs = methodtest_copy_metaclassconfusion70.getChildren();
					classC74 = ModelTestUtils.getAssertClass( methodtest_copy_metaclassconfusion70Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__75;
						IModelElement[] classC74Childs = classC74.getChildren();
						method__init__75 = ModelTestUtils.getAssertMethod( classC74Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__75, new String[] {"self", "tag"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__76;
						IModelElement[] classC74Childs = classC74.getChildren();
						method__cmp__76 = ModelTestUtils.getAssertMethod( classC74Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__76, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_metaclassconfusion
			{
			IMethod methodtest_deepcopy_metaclassconfusion77;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_metaclassconfusion77 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_metaclassconfusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_metaclassconfusion77, new String[] {"self"} );
				//Class test
				{
				IType classMyOwnError78;
					IModelElement[] methodtest_deepcopy_metaclassconfusion77Childs = methodtest_deepcopy_metaclassconfusion77.getChildren();
					classMyOwnError78 = ModelTestUtils.getAssertClass( methodtest_deepcopy_metaclassconfusion77Childs, "MyOwnError" );
				}
				//Class test
				{
				IType classMeta79;
					IModelElement[] methodtest_deepcopy_metaclassconfusion77Childs = methodtest_deepcopy_metaclassconfusion77.getChildren();
					classMeta79 = ModelTestUtils.getAssertClass( methodtest_deepcopy_metaclassconfusion77Childs, "Meta" );
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__80;
						IModelElement[] classMeta79Childs = classMeta79.getChildren();
						method__deepcopy__80 = ModelTestUtils.getAssertMethod( classMeta79Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__80, new String[] {"cls", "memo"} );
					}
				}
				//Class test
				{
				IType classC81;
					IModelElement[] methodtest_deepcopy_metaclassconfusion77Childs = methodtest_deepcopy_metaclassconfusion77.getChildren();
					classC81 = ModelTestUtils.getAssertClass( methodtest_deepcopy_metaclassconfusion77Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__82;
						IModelElement[] classC81Childs = classC81.getChildren();
						method__init__82 = ModelTestUtils.getAssertMethod( classC81Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__82, new String[] {"self", "tag"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__83;
						IModelElement[] classC81Childs = classC81.getChildren();
						method__cmp__83 = ModelTestUtils.getAssertMethod( classC81Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__83, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:_nomro
			{
			IMethod method_nomro84;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				method_nomro84 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "_nomro", 1 );
				ModelTestUtils.assertParameterNames( method_nomro84, new String[] {"self"} );
				//Class test
				{
				IType classC85;
					IModelElement[] method_nomro84Childs = method_nomro84.getChildren();
					classC85 = ModelTestUtils.getAssertClass( method_nomro84Childs, "C" );
					//Function test:__getattribute__
					{
					IMethod method__getattribute__86;
						IModelElement[] classC85Childs = classC85.getChildren();
						method__getattribute__86 = ModelTestUtils.getAssertMethod( classC85Childs, "__getattribute__", 2 );
						ModelTestUtils.assertParameterNames( method__getattribute__86, new String[] {"self", "attr"} );
					}
				}
				//Class test
				{
				IType classD87;
					IModelElement[] method_nomro84Childs = method_nomro84.getChildren();
					classD87 = ModelTestUtils.getAssertClass( method_nomro84Childs, "D" );
				}
			}
			//Function test:test_copy_mro
			{
			IMethod methodtest_copy_mro88;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_mro88 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_mro", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_mro88, new String[] {"self"} );
			}
			//Function test:test_deepcopy_mro
			{
			IMethod methodtest_deepcopy_mro89;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_mro89 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_mro", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_mro89, new String[] {"self"} );
			}
			//Function test:test_deepcopy_basic
			{
			IMethod methodtest_deepcopy_basic90;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_basic90 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_basic90, new String[] {"self"} );
			}
			//Function test:test_deepcopy_memo
			{
			IMethod methodtest_deepcopy_memo91;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_memo91 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_memo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_memo91, new String[] {"self"} );
			}
			//Function test:test_deepcopy_issubclass
			{
			IMethod methodtest_deepcopy_issubclass92;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_issubclass92 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_issubclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_issubclass92, new String[] {"self"} );
				//Class test
				{
				IType classMeta93;
					IModelElement[] methodtest_deepcopy_issubclass92Childs = methodtest_deepcopy_issubclass92.getChildren();
					classMeta93 = ModelTestUtils.getAssertClass( methodtest_deepcopy_issubclass92Childs, "Meta" );
				}
				//Class test
				{
				IType classC94;
					IModelElement[] methodtest_deepcopy_issubclass92Childs = methodtest_deepcopy_issubclass92.getChildren();
					classC94 = ModelTestUtils.getAssertClass( methodtest_deepcopy_issubclass92Childs, "C" );
				}
			}
			//Function test:test_deepcopy_deepcopy
			{
			IMethod methodtest_deepcopy_deepcopy95;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_deepcopy95 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_deepcopy95, new String[] {"self"} );
				//Class test
				{
				IType classC96;
					IModelElement[] methodtest_deepcopy_deepcopy95Childs = methodtest_deepcopy_deepcopy95.getChildren();
					classC96 = ModelTestUtils.getAssertClass( methodtest_deepcopy_deepcopy95Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__97;
						IModelElement[] classC96Childs = classC96.getChildren();
						method__init__97 = ModelTestUtils.getAssertMethod( classC96Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__97, new String[] {"self", "foo"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__98;
						IModelElement[] classC96Childs = classC96.getChildren();
						method__deepcopy__98 = ModelTestUtils.getAssertMethod( classC96Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__98, new String[] {"self", "memo"} );
					}
				}
			}
			//Function test:test_deepcopy_registry
			{
			IMethod methodtest_deepcopy_registry99;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_registry99 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_registry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_registry99, new String[] {"self"} );
				//Class test
				{
				IType classC100;
					IModelElement[] methodtest_deepcopy_registry99Childs = methodtest_deepcopy_registry99.getChildren();
					classC100 = ModelTestUtils.getAssertClass( methodtest_deepcopy_registry99Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__101;
						IModelElement[] classC100Childs = classC100.getChildren();
						method__new__101 = ModelTestUtils.getAssertMethod( classC100Childs, "__new__", 2 );
						ModelTestUtils.assertParameterNames( method__new__101, new String[] {"cls", "foo"} );
					}
				}
				//Function test:pickle_C
				{
				IMethod methodpickle_C102;
					IModelElement[] methodtest_deepcopy_registry99Childs = methodtest_deepcopy_registry99.getChildren();
					methodpickle_C102 = ModelTestUtils.getAssertMethod( methodtest_deepcopy_registry99Childs, "pickle_C", 1 );
					ModelTestUtils.assertParameterNames( methodpickle_C102, new String[] {"obj"} );
				}
			}
			//Function test:test_deepcopy_reduce_ex
			{
			IMethod methodtest_deepcopy_reduce_ex103;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reduce_ex103 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reduce_ex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reduce_ex103, new String[] {"self"} );
				//Class test
				{
				IType classC104;
					IModelElement[] methodtest_deepcopy_reduce_ex103Childs = methodtest_deepcopy_reduce_ex103.getChildren();
					classC104 = ModelTestUtils.getAssertClass( methodtest_deepcopy_reduce_ex103Childs, "C" );
					//Function test:__reduce_ex__
					{
					IMethod method__reduce_ex__105;
						IModelElement[] classC104Childs = classC104.getChildren();
						method__reduce_ex__105 = ModelTestUtils.getAssertMethod( classC104Childs, "__reduce_ex__", 2 );
						ModelTestUtils.assertParameterNames( method__reduce_ex__105, new String[] {"self", "proto"} );
					}
					//Function test:__reduce__
					{
					IMethod method__reduce__106;
						IModelElement[] classC104Childs = classC104.getChildren();
						method__reduce__106 = ModelTestUtils.getAssertMethod( classC104Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__106, new String[] {"self"} );
					}
				}
			}
			//Function test:test_deepcopy_reduce
			{
			IMethod methodtest_deepcopy_reduce107;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reduce107 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reduce107, new String[] {"self"} );
				//Class test
				{
				IType classC108;
					IModelElement[] methodtest_deepcopy_reduce107Childs = methodtest_deepcopy_reduce107.getChildren();
					classC108 = ModelTestUtils.getAssertClass( methodtest_deepcopy_reduce107Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__109;
						IModelElement[] classC108Childs = classC108.getChildren();
						method__reduce__109 = ModelTestUtils.getAssertMethod( classC108Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__109, new String[] {"self"} );
					}
				}
			}
			//Function test:test_deepcopy_cant
			{
			IMethod methodtest_deepcopy_cant110;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_cant110 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_cant", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_cant110, new String[] {"self"} );
				//Class test
				{
				IType classC111;
					IModelElement[] methodtest_deepcopy_cant110Childs = methodtest_deepcopy_cant110.getChildren();
					classC111 = ModelTestUtils.getAssertClass( methodtest_deepcopy_cant110Childs, "C" );
					//Function test:__getattribute__
					{
					IMethod method__getattribute__112;
						IModelElement[] classC111Childs = classC111.getChildren();
						method__getattribute__112 = ModelTestUtils.getAssertMethod( classC111Childs, "__getattribute__", 2 );
						ModelTestUtils.assertParameterNames( method__getattribute__112, new String[] {"self", "name"} );
					}
				}
			}
			//Function test:test_deepcopy_atomic
			{
			IMethod methodtest_deepcopy_atomic113;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_atomic113 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_atomic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_atomic113, new String[] {"self"} );
				//Class test
				{
				IType classClassic114;
					IModelElement[] methodtest_deepcopy_atomic113Childs = methodtest_deepcopy_atomic113.getChildren();
					classClassic114 = ModelTestUtils.getAssertClass( methodtest_deepcopy_atomic113Childs, "Classic" );
				}
				//Class test
				{
				IType classNewStyle115;
					IModelElement[] methodtest_deepcopy_atomic113Childs = methodtest_deepcopy_atomic113.getChildren();
					classNewStyle115 = ModelTestUtils.getAssertClass( methodtest_deepcopy_atomic113Childs, "NewStyle" );
				}
				//Function test:f
				{
				IMethod methodf116;
					IModelElement[] methodtest_deepcopy_atomic113Childs = methodtest_deepcopy_atomic113.getChildren();
					methodf116 = ModelTestUtils.getAssertMethod( methodtest_deepcopy_atomic113Childs, "f", 0 );
				}
			}
			//Function test:test_deepcopy_list
			{
			IMethod methodtest_deepcopy_list117;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_list117 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_list117, new String[] {"self"} );
			}
			//Function test:test_deepcopy_reflexive_list
			{
			IMethod methodtest_deepcopy_reflexive_list118;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_list118 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_list118, new String[] {"self"} );
			}
			//Function test:test_deepcopy_tuple
			{
			IMethod methodtest_deepcopy_tuple119;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_tuple119 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_tuple119, new String[] {"self"} );
			}
			//Function test:test_deepcopy_reflexive_tuple
			{
			IMethod methodtest_deepcopy_reflexive_tuple120;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_tuple120 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_tuple120, new String[] {"self"} );
			}
			//Function test:test_deepcopy_dict
			{
			IMethod methodtest_deepcopy_dict121;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_dict121 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_dict121, new String[] {"self"} );
			}
			//Function test:test_deepcopy_reflexive_dict
			{
			IMethod methodtest_deepcopy_reflexive_dict122;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_dict122 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_dict122, new String[] {"self"} );
			}
			//Function test:test_deepcopy_keepalive
			{
			IMethod methodtest_deepcopy_keepalive123;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_keepalive123 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_keepalive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_keepalive123, new String[] {"self"} );
			}
			//Function test:test_deepcopy_inst_vanilla
			{
			IMethod methodtest_deepcopy_inst_vanilla124;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_vanilla124 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_vanilla", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_vanilla124, new String[] {"self"} );
				//Class test
				{
				IType classC125;
					IModelElement[] methodtest_deepcopy_inst_vanilla124Childs = methodtest_deepcopy_inst_vanilla124.getChildren();
					classC125 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_vanilla124Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__126;
						IModelElement[] classC125Childs = classC125.getChildren();
						method__init__126 = ModelTestUtils.getAssertMethod( classC125Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__126, new String[] {"self", "foo"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__127;
						IModelElement[] classC125Childs = classC125.getChildren();
						method__cmp__127 = ModelTestUtils.getAssertMethod( classC125Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__127, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_deepcopy
			{
			IMethod methodtest_deepcopy_inst_deepcopy128;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_deepcopy128 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_deepcopy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_deepcopy128, new String[] {"self"} );
				//Class test
				{
				IType classC129;
					IModelElement[] methodtest_deepcopy_inst_deepcopy128Childs = methodtest_deepcopy_inst_deepcopy128.getChildren();
					classC129 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_deepcopy128Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__130;
						IModelElement[] classC129Childs = classC129.getChildren();
						method__init__130 = ModelTestUtils.getAssertMethod( classC129Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__130, new String[] {"self", "foo"} );
					}
					//Function test:__deepcopy__
					{
					IMethod method__deepcopy__131;
						IModelElement[] classC129Childs = classC129.getChildren();
						method__deepcopy__131 = ModelTestUtils.getAssertMethod( classC129Childs, "__deepcopy__", 2 );
						ModelTestUtils.assertParameterNames( method__deepcopy__131, new String[] {"self", "memo"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__132;
						IModelElement[] classC129Childs = classC129.getChildren();
						method__cmp__132 = ModelTestUtils.getAssertMethod( classC129Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__132, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_inst_getinitargs
			{
			IMethod methodtest_deepcopy_inst_getinitargs133;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_getinitargs133 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_getinitargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_getinitargs133, new String[] {"self"} );
				//Class test
				{
				IType classC134;
					IModelElement[] methodtest_deepcopy_inst_getinitargs133Childs = methodtest_deepcopy_inst_getinitargs133.getChildren();
					classC134 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_getinitargs133Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__135;
						IModelElement[] classC134Childs = classC134.getChildren();
						method__init__135 = ModelTestUtils.getAssertMethod( classC134Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__135, new String[] {"self", "foo"} );
					}
					//Function test:__getinitargs__
					{
					IMethod method__getinitargs__136;
						IModelElement[] classC134Childs = classC134.getChildren();
						method__getinitargs__136 = ModelTestUtils.getAssertMethod( classC134Childs, "__getinitargs__", 1 );
						ModelTestUtils.assertParameterNames( method__getinitargs__136, new String[] {"self"} );
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
			//Function test:test_deepcopy_inst_getstate
			{
			IMethod methodtest_deepcopy_inst_getstate138;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_getstate138 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_getstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_getstate138, new String[] {"self"} );
				//Class test
				{
				IType classC139;
					IModelElement[] methodtest_deepcopy_inst_getstate138Childs = methodtest_deepcopy_inst_getstate138.getChildren();
					classC139 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_getstate138Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__140;
						IModelElement[] classC139Childs = classC139.getChildren();
						method__init__140 = ModelTestUtils.getAssertMethod( classC139Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__140, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__141;
						IModelElement[] classC139Childs = classC139.getChildren();
						method__getstate__141 = ModelTestUtils.getAssertMethod( classC139Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__141, new String[] {"self"} );
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
			//Function test:test_deepcopy_inst_setstate
			{
			IMethod methodtest_deepcopy_inst_setstate143;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_setstate143 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_setstate143, new String[] {"self"} );
				//Class test
				{
				IType classC144;
					IModelElement[] methodtest_deepcopy_inst_setstate143Childs = methodtest_deepcopy_inst_setstate143.getChildren();
					classC144 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_setstate143Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__145;
						IModelElement[] classC144Childs = classC144.getChildren();
						method__init__145 = ModelTestUtils.getAssertMethod( classC144Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__145, new String[] {"self", "foo"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__146;
						IModelElement[] classC144Childs = classC144.getChildren();
						method__setstate__146 = ModelTestUtils.getAssertMethod( classC144Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__146, new String[] {"self", "state"} );
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
			//Function test:test_deepcopy_inst_getstate_setstate
			{
			IMethod methodtest_deepcopy_inst_getstate_setstate148;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_inst_getstate_setstate148 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_inst_getstate_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_inst_getstate_setstate148, new String[] {"self"} );
				//Class test
				{
				IType classC149;
					IModelElement[] methodtest_deepcopy_inst_getstate_setstate148Childs = methodtest_deepcopy_inst_getstate_setstate148.getChildren();
					classC149 = ModelTestUtils.getAssertClass( methodtest_deepcopy_inst_getstate_setstate148Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__150;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__init__150 = ModelTestUtils.getAssertMethod( classC149Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__150, new String[] {"self", "foo"} );
					}
					//Function test:__getstate__
					{
					IMethod method__getstate__151;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__getstate__151 = ModelTestUtils.getAssertMethod( classC149Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__151, new String[] {"self"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__152;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__setstate__152 = ModelTestUtils.getAssertMethod( classC149Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__152, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__153;
						IModelElement[] classC149Childs = classC149.getChildren();
						method__cmp__153 = ModelTestUtils.getAssertMethod( classC149Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__153, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_deepcopy_reflexive_inst
			{
			IMethod methodtest_deepcopy_reflexive_inst154;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_reflexive_inst154 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_reflexive_inst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_reflexive_inst154, new String[] {"self"} );
				//Class test
				{
				IType classC155;
					IModelElement[] methodtest_deepcopy_reflexive_inst154Childs = methodtest_deepcopy_reflexive_inst154.getChildren();
					classC155 = ModelTestUtils.getAssertClass( methodtest_deepcopy_reflexive_inst154Childs, "C" );
				}
			}
			//Function test:test_reconstruct_string
			{
			IMethod methodtest_reconstruct_string156;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_string156 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_string156, new String[] {"self"} );
				//Class test
				{
				IType classC157;
					IModelElement[] methodtest_reconstruct_string156Childs = methodtest_reconstruct_string156.getChildren();
					classC157 = ModelTestUtils.getAssertClass( methodtest_reconstruct_string156Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__158;
						IModelElement[] classC157Childs = classC157.getChildren();
						method__reduce__158 = ModelTestUtils.getAssertMethod( classC157Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__158, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reconstruct_nostate
			{
			IMethod methodtest_reconstruct_nostate159;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_nostate159 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_nostate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_nostate159, new String[] {"self"} );
				//Class test
				{
				IType classC160;
					IModelElement[] methodtest_reconstruct_nostate159Childs = methodtest_reconstruct_nostate159.getChildren();
					classC160 = ModelTestUtils.getAssertClass( methodtest_reconstruct_nostate159Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__161;
						IModelElement[] classC160Childs = classC160.getChildren();
						method__reduce__161 = ModelTestUtils.getAssertMethod( classC160Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__161, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reconstruct_state
			{
			IMethod methodtest_reconstruct_state162;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_state162 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_state", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_state162, new String[] {"self"} );
				//Class test
				{
				IType classC163;
					IModelElement[] methodtest_reconstruct_state162Childs = methodtest_reconstruct_state162.getChildren();
					classC163 = ModelTestUtils.getAssertClass( methodtest_reconstruct_state162Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__164;
						IModelElement[] classC163Childs = classC163.getChildren();
						method__reduce__164 = ModelTestUtils.getAssertMethod( classC163Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__164, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__165;
						IModelElement[] classC163Childs = classC163.getChildren();
						method__cmp__165 = ModelTestUtils.getAssertMethod( classC163Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__165, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reconstruct_state_setstate
			{
			IMethod methodtest_reconstruct_state_setstate166;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_state_setstate166 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_state_setstate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_state_setstate166, new String[] {"self"} );
				//Class test
				{
				IType classC167;
					IModelElement[] methodtest_reconstruct_state_setstate166Childs = methodtest_reconstruct_state_setstate166.getChildren();
					classC167 = ModelTestUtils.getAssertClass( methodtest_reconstruct_state_setstate166Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__168;
						IModelElement[] classC167Childs = classC167.getChildren();
						method__reduce__168 = ModelTestUtils.getAssertMethod( classC167Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__168, new String[] {"self"} );
					}
					//Function test:__setstate__
					{
					IMethod method__setstate__169;
						IModelElement[] classC167Childs = classC167.getChildren();
						method__setstate__169 = ModelTestUtils.getAssertMethod( classC167Childs, "__setstate__", 2 );
						ModelTestUtils.assertParameterNames( method__setstate__169, new String[] {"self", "state"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__170;
						IModelElement[] classC167Childs = classC167.getChildren();
						method__cmp__170 = ModelTestUtils.getAssertMethod( classC167Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__170, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reconstruct_reflexive
			{
			IMethod methodtest_reconstruct_reflexive171;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reconstruct_reflexive171 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reconstruct_reflexive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reconstruct_reflexive171, new String[] {"self"} );
				//Class test
				{
				IType classC172;
					IModelElement[] methodtest_reconstruct_reflexive171Childs = methodtest_reconstruct_reflexive171.getChildren();
					classC172 = ModelTestUtils.getAssertClass( methodtest_reconstruct_reflexive171Childs, "C" );
				}
			}
			//Function test:test_reduce_4tuple
			{
			IMethod methodtest_reduce_4tuple173;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reduce_4tuple173 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reduce_4tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_4tuple173, new String[] {"self"} );
				//Class test
				{
				IType classC174;
					IModelElement[] methodtest_reduce_4tuple173Childs = methodtest_reduce_4tuple173.getChildren();
					classC174 = ModelTestUtils.getAssertClass( methodtest_reduce_4tuple173Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__175;
						IModelElement[] classC174Childs = classC174.getChildren();
						method__reduce__175 = ModelTestUtils.getAssertMethod( classC174Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__175, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__176;
						IModelElement[] classC174Childs = classC174.getChildren();
						method__cmp__176 = ModelTestUtils.getAssertMethod( classC174Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__176, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_reduce_5tuple
			{
			IMethod methodtest_reduce_5tuple177;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_reduce_5tuple177 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_reduce_5tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_5tuple177, new String[] {"self"} );
				//Class test
				{
				IType classC178;
					IModelElement[] methodtest_reduce_5tuple177Childs = methodtest_reduce_5tuple177.getChildren();
					classC178 = ModelTestUtils.getAssertClass( methodtest_reduce_5tuple177Childs, "C" );
					//Function test:__reduce__
					{
					IMethod method__reduce__179;
						IModelElement[] classC178Childs = classC178.getChildren();
						method__reduce__179 = ModelTestUtils.getAssertMethod( classC178Childs, "__reduce__", 1 );
						ModelTestUtils.assertParameterNames( method__reduce__179, new String[] {"self"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__180;
						IModelElement[] classC178Childs = classC178.getChildren();
						method__cmp__180 = ModelTestUtils.getAssertMethod( classC178Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__180, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_copy_slots
			{
			IMethod methodtest_copy_slots181;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_slots181 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_slots181, new String[] {"self"} );
				//Class test
				{
				IType classC182;
					IModelElement[] methodtest_copy_slots181Childs = methodtest_copy_slots181.getChildren();
					classC182 = ModelTestUtils.getAssertClass( methodtest_copy_slots181Childs, "C" );
				}
			}
			//Function test:test_deepcopy_slots
			{
			IMethod methodtest_deepcopy_slots183;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_slots183 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_slots183, new String[] {"self"} );
				//Class test
				{
				IType classC184;
					IModelElement[] methodtest_deepcopy_slots183Childs = methodtest_deepcopy_slots183.getChildren();
					classC184 = ModelTestUtils.getAssertClass( methodtest_deepcopy_slots183Childs, "C" );
				}
			}
			//Function test:test_copy_list_subclass
			{
			IMethod methodtest_copy_list_subclass185;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_list_subclass185 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_list_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_list_subclass185, new String[] {"self"} );
				//Class test
				{
				IType classC186;
					IModelElement[] methodtest_copy_list_subclass185Childs = methodtest_copy_list_subclass185.getChildren();
					classC186 = ModelTestUtils.getAssertClass( methodtest_copy_list_subclass185Childs, "C" );
				}
			}
			//Function test:test_deepcopy_list_subclass
			{
			IMethod methodtest_deepcopy_list_subclass187;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_list_subclass187 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_list_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_list_subclass187, new String[] {"self"} );
				//Class test
				{
				IType classC188;
					IModelElement[] methodtest_deepcopy_list_subclass187Childs = methodtest_deepcopy_list_subclass187.getChildren();
					classC188 = ModelTestUtils.getAssertClass( methodtest_deepcopy_list_subclass187Childs, "C" );
				}
			}
			//Function test:test_copy_tuple_subclass
			{
			IMethod methodtest_copy_tuple_subclass189;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_copy_tuple_subclass189 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_copy_tuple_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_tuple_subclass189, new String[] {"self"} );
				//Class test
				{
				IType classC190;
					IModelElement[] methodtest_copy_tuple_subclass189Childs = methodtest_copy_tuple_subclass189.getChildren();
					classC190 = ModelTestUtils.getAssertClass( methodtest_copy_tuple_subclass189Childs, "C" );
				}
			}
			//Function test:test_deepcopy_tuple_subclass
			{
			IMethod methodtest_deepcopy_tuple_subclass191;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_deepcopy_tuple_subclass191 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_deepcopy_tuple_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deepcopy_tuple_subclass191, new String[] {"self"} );
				//Class test
				{
				IType classC192;
					IModelElement[] methodtest_deepcopy_tuple_subclass191Childs = methodtest_deepcopy_tuple_subclass191.getChildren();
					classC192 = ModelTestUtils.getAssertClass( methodtest_deepcopy_tuple_subclass191Childs, "C" );
				}
			}
			//Function test:test_getstate_exc
			{
			IMethod methodtest_getstate_exc193;
				IModelElement[] classTestCopy0Childs = classTestCopy0.getChildren();
				methodtest_getstate_exc193 = ModelTestUtils.getAssertMethod( classTestCopy0Childs, "test_getstate_exc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getstate_exc193, new String[] {"self"} );
				//Class test
				{
				IType classEvilState194;
					IModelElement[] methodtest_getstate_exc193Childs = methodtest_getstate_exc193.getChildren();
					classEvilState194 = ModelTestUtils.getAssertClass( methodtest_getstate_exc193Childs, "EvilState" );
					//Function test:__getstate__
					{
					IMethod method__getstate__195;
						IModelElement[] classEvilState194Childs = classEvilState194.getChildren();
						method__getstate__195 = ModelTestUtils.getAssertMethod( classEvilState194Childs, "__getstate__", 1 );
						ModelTestUtils.assertParameterNames( method__getstate__195, new String[] {"self"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main196;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main196 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
		}
		//Function test:create_function
		{
		IMethod methodcreate_function4;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_function4 = ModelTestUtils.getAssertMethod( moduleChilds, "create_function", 0 );
			//Function test:f
			{
			IMethod methodf5;
				IModelElement[] methodcreate_function4Childs = methodcreate_function4.getChildren();
				methodf5 = ModelTestUtils.getAssertMethod( methodcreate_function4Childs, "f", 0 );
			}
		}
		//Function test:create_bound_method
		{
		IMethod methodcreate_bound_method6;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_bound_method6 = ModelTestUtils.getAssertMethod( moduleChilds, "create_bound_method", 0 );
		}
		//Function test:create_unbound_method
		{
		IMethod methodcreate_unbound_method7;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_unbound_method7 = ModelTestUtils.getAssertMethod( moduleChilds, "create_unbound_method", 0 );
		}
		//Class test
		{
		IType classTestBase8;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBase8 = ModelTestUtils.getAssertClass( moduleChilds, "TestBase" );
			//Function test:setUp
			{
			IMethod methodsetUp9;
				IModelElement[] classTestBase8Childs = classTestBase8.getChildren();
				methodsetUp9 = ModelTestUtils.getAssertMethod( classTestBase8Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp9, new String[] {"self"} );
			}
			//Function test:callback
			{
			IMethod methodcallback10;
				IModelElement[] classTestBase8Childs = classTestBase8.getChildren();
				methodcallback10 = ModelTestUtils.getAssertMethod( classTestBase8Childs, "callback", 2 );
				ModelTestUtils.assertParameterNames( methodcallback10, new String[] {"self", "ref"} );
			}
		}
		//Class test
		{
		IType classReferencesTestCase11;
			IModelElement[] moduleChilds = module.getChildren();
			classReferencesTestCase11 = ModelTestUtils.getAssertClass( moduleChilds, "ReferencesTestCase" );
			//Function test:test_basic_ref
			{
			IMethod methodtest_basic_ref12;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_basic_ref12 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_basic_ref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_ref12, new String[] {"self"} );
			}
			//Function test:test_basic_callback
			{
			IMethod methodtest_basic_callback13;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_basic_callback13 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_basic_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_callback13, new String[] {"self"} );
			}
			//Function test:test_multiple_callbacks
			{
			IMethod methodtest_multiple_callbacks14;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_multiple_callbacks14 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_multiple_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiple_callbacks14, new String[] {"self"} );
			}
			//Function test:test_multiple_selfref_callbacks
			{
			IMethod methodtest_multiple_selfref_callbacks15;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_multiple_selfref_callbacks15 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_multiple_selfref_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiple_selfref_callbacks15, new String[] {"self"} );
				//Function test:callback
				{
				IMethod methodcallback16;
					IModelElement[] methodtest_multiple_selfref_callbacks15Childs = methodtest_multiple_selfref_callbacks15.getChildren();
					methodcallback16 = ModelTestUtils.getAssertMethod( methodtest_multiple_selfref_callbacks15Childs, "callback", 2 );
					ModelTestUtils.assertParameterNames( methodcallback16, new String[] {"object", "self"} );
				}
			}
			//Function test:test_proxy_ref
			{
			IMethod methodtest_proxy_ref17;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_proxy_ref17 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_proxy_ref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proxy_ref17, new String[] {"self"} );
				//Function test:check
				{
				IMethod methodcheck18;
					IModelElement[] methodtest_proxy_ref17Childs = methodtest_proxy_ref17.getChildren();
					methodcheck18 = ModelTestUtils.getAssertMethod( methodtest_proxy_ref17Childs, "check", 1 );
					ModelTestUtils.assertParameterNames( methodcheck18, new String[] {"proxy"} );
				}
			}
			//Function test:check_basic_ref
			{
			IMethod methodcheck_basic_ref19;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodcheck_basic_ref19 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "check_basic_ref", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_basic_ref19, new String[] {"self", "factory"} );
			}
			//Function test:check_basic_callback
			{
			IMethod methodcheck_basic_callback20;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodcheck_basic_callback20 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "check_basic_callback", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_basic_callback20, new String[] {"self", "factory"} );
			}
			//Function test:test_ref_reuse
			{
			IMethod methodtest_ref_reuse21;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_ref_reuse21 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_ref_reuse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ref_reuse21, new String[] {"self"} );
			}
			//Function test:test_proxy_reuse
			{
			IMethod methodtest_proxy_reuse22;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_proxy_reuse22 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_proxy_reuse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proxy_reuse22, new String[] {"self"} );
			}
			//Function test:test_basic_proxy
			{
			IMethod methodtest_basic_proxy23;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_basic_proxy23 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_basic_proxy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_proxy23, new String[] {"self"} );
			}
			//Function test:test_shared_ref_without_callback
			{
			IMethod methodtest_shared_ref_without_callback24;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_shared_ref_without_callback24 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_shared_ref_without_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shared_ref_without_callback24, new String[] {"self"} );
			}
			//Function test:test_shared_proxy_without_callback
			{
			IMethod methodtest_shared_proxy_without_callback25;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_shared_proxy_without_callback25 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_shared_proxy_without_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shared_proxy_without_callback25, new String[] {"self"} );
			}
			//Function test:check_shared_without_callback
			{
			IMethod methodcheck_shared_without_callback26;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodcheck_shared_without_callback26 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "check_shared_without_callback", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_shared_without_callback26, new String[] {"self", "makeref"} );
			}
			//Function test:test_callable_proxy
			{
			IMethod methodtest_callable_proxy27;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callable_proxy27 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callable_proxy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callable_proxy27, new String[] {"self"} );
			}
			//Function test:check_proxy
			{
			IMethod methodcheck_proxy28;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodcheck_proxy28 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "check_proxy", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_proxy28, new String[] {"self", "o", "proxy"} );
			}
			//Function test:test_proxy_deletion
			{
			IMethod methodtest_proxy_deletion29;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_proxy_deletion29 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_proxy_deletion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proxy_deletion29, new String[] {"self"} );
				//Class test
				{
				IType classFoo30;
					IModelElement[] methodtest_proxy_deletion29Childs = methodtest_proxy_deletion29.getChildren();
					classFoo30 = ModelTestUtils.getAssertClass( methodtest_proxy_deletion29Childs, "Foo" );
					//Function test:__delitem__
					{
					IMethod method__delitem__31;
						IModelElement[] classFoo30Childs = classFoo30.getChildren();
						method__delitem__31 = ModelTestUtils.getAssertMethod( classFoo30Childs, "__delitem__", 2 );
						ModelTestUtils.assertParameterNames( method__delitem__31, new String[] {"self", "accessor"} );
					}
				}
			}
			//Function test:test_getweakrefcount
			{
			IMethod methodtest_getweakrefcount32;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_getweakrefcount32 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_getweakrefcount", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getweakrefcount32, new String[] {"self"} );
			}
			//Function test:test_getweakrefs
			{
			IMethod methodtest_getweakrefs33;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_getweakrefs33 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_getweakrefs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getweakrefs33, new String[] {"self"} );
			}
			//Function test:test_newstyle_number_ops
			{
			IMethod methodtest_newstyle_number_ops34;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_newstyle_number_ops34 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_newstyle_number_ops", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newstyle_number_ops34, new String[] {"self"} );
				//Class test
				{
				IType classF35;
					IModelElement[] methodtest_newstyle_number_ops34Childs = methodtest_newstyle_number_ops34.getChildren();
					classF35 = ModelTestUtils.getAssertClass( methodtest_newstyle_number_ops34Childs, "F" );
				}
			}
			//Function test:test_callbacks_protected
			{
			IMethod methodtest_callbacks_protected36;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callbacks_protected36 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callbacks_protected", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callbacks_protected36, new String[] {"self"} );
				//Class test
				{
				IType classBogusError37;
					IModelElement[] methodtest_callbacks_protected36Childs = methodtest_callbacks_protected36.getChildren();
					classBogusError37 = ModelTestUtils.getAssertClass( methodtest_callbacks_protected36Childs, "BogusError" );
				}
				//Function test:remove
				{
				IMethod methodremove38;
					IModelElement[] methodtest_callbacks_protected36Childs = methodtest_callbacks_protected36.getChildren();
					methodremove38 = ModelTestUtils.getAssertMethod( methodtest_callbacks_protected36Childs, "remove", 1 );
					ModelTestUtils.assertParameterNames( methodremove38, new String[] {"k"} );
				}
				//Function test:encapsulate
				{
				IMethod methodencapsulate39;
					IModelElement[] methodtest_callbacks_protected36Childs = methodtest_callbacks_protected36.getChildren();
					methodencapsulate39 = ModelTestUtils.getAssertMethod( methodtest_callbacks_protected36Childs, "encapsulate", 0 );
				}
			}
			//Function test:test_sf_bug_840829
			{
			IMethod methodtest_sf_bug_84082940;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_sf_bug_84082940 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_sf_bug_840829", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sf_bug_84082940, new String[] {"self"} );
				//Class test
				{
				IType classC41;
					IModelElement[] methodtest_sf_bug_84082940Childs = methodtest_sf_bug_84082940.getChildren();
					classC41 = ModelTestUtils.getAssertClass( methodtest_sf_bug_84082940Childs, "C" );
				}
			}
			//Function test:test_callback_in_cycle_1
			{
			IMethod methodtest_callback_in_cycle_142;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callback_in_cycle_142 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callback_in_cycle_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_142, new String[] {"self"} );
				//Class test
				{
				IType classJ43;
					IModelElement[] methodtest_callback_in_cycle_142Childs = methodtest_callback_in_cycle_142.getChildren();
					classJ43 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_142Childs, "J" );
				}
				//Class test
				{
				IType classII44;
					IModelElement[] methodtest_callback_in_cycle_142Childs = methodtest_callback_in_cycle_142.getChildren();
					classII44 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_142Childs, "II" );
					//Function test:acallback
					{
					IMethod methodacallback45;
						IModelElement[] classII44Childs = classII44.getChildren();
						methodacallback45 = ModelTestUtils.getAssertMethod( classII44Childs, "acallback", 2 );
						ModelTestUtils.assertParameterNames( methodacallback45, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_callback_in_cycle_2
			{
			IMethod methodtest_callback_in_cycle_246;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callback_in_cycle_246 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callback_in_cycle_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_246, new String[] {"self"} );
				//Class test
				{
				IType classJ47;
					IModelElement[] methodtest_callback_in_cycle_246Childs = methodtest_callback_in_cycle_246.getChildren();
					classJ47 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_246Childs, "J" );
				}
				//Class test
				{
				IType classII48;
					IModelElement[] methodtest_callback_in_cycle_246Childs = methodtest_callback_in_cycle_246.getChildren();
					classII48 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_246Childs, "II" );
					//Function test:acallback
					{
					IMethod methodacallback49;
						IModelElement[] classII48Childs = classII48.getChildren();
						methodacallback49 = ModelTestUtils.getAssertMethod( classII48Childs, "acallback", 2 );
						ModelTestUtils.assertParameterNames( methodacallback49, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_callback_in_cycle_3
			{
			IMethod methodtest_callback_in_cycle_350;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callback_in_cycle_350 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callback_in_cycle_3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_350, new String[] {"self"} );
				//Class test
				{
				IType classC51;
					IModelElement[] methodtest_callback_in_cycle_350Childs = methodtest_callback_in_cycle_350.getChildren();
					classC51 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_350Childs, "C" );
					//Function test:cb
					{
					IMethod methodcb52;
						IModelElement[] classC51Childs = classC51.getChildren();
						methodcb52 = ModelTestUtils.getAssertMethod( classC51Childs, "cb", 2 );
						ModelTestUtils.assertParameterNames( methodcb52, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_callback_in_cycle_4
			{
			IMethod methodtest_callback_in_cycle_453;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callback_in_cycle_453 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callback_in_cycle_4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_453, new String[] {"self"} );
				//Class test
				{
				IType classC54;
					IModelElement[] methodtest_callback_in_cycle_453Childs = methodtest_callback_in_cycle_453.getChildren();
					classC54 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_453Childs, "C" );
					//Function test:cb
					{
					IMethod methodcb55;
						IModelElement[] classC54Childs = classC54.getChildren();
						methodcb55 = ModelTestUtils.getAssertMethod( classC54Childs, "cb", 2 );
						ModelTestUtils.assertParameterNames( methodcb55, new String[] {"self", "ignore"} );
					}
				}
				//Class test
				{
				IType classD56;
					IModelElement[] methodtest_callback_in_cycle_453Childs = methodtest_callback_in_cycle_453.getChildren();
					classD56 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_453Childs, "D" );
				}
			}
			//Function test:test_callback_in_cycle_resurrection
			{
			IMethod methodtest_callback_in_cycle_resurrection57;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callback_in_cycle_resurrection57 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callback_in_cycle_resurrection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_in_cycle_resurrection57, new String[] {"self"} );
				//Class test
				{
				IType classC58;
					IModelElement[] methodtest_callback_in_cycle_resurrection57Childs = methodtest_callback_in_cycle_resurrection57.getChildren();
					classC58 = ModelTestUtils.getAssertClass( methodtest_callback_in_cycle_resurrection57Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__59;
						IModelElement[] classC58Childs = classC58.getChildren();
						method__init__59 = ModelTestUtils.getAssertMethod( classC58Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__59, new String[] {"self", "value"} );
					}
					//Function test:acallback
					{
					IMethod methodacallback60;
						IModelElement[] classC58Childs = classC58.getChildren();
						methodacallback60 = ModelTestUtils.getAssertMethod( classC58Childs, "acallback", 2 );
						ModelTestUtils.assertParameterNames( methodacallback60, new String[] {"self", "ignore"} );
					}
				}
				//Function test:C_went_away
				{
				IMethod methodC_went_away61;
					IModelElement[] methodtest_callback_in_cycle_resurrection57Childs = methodtest_callback_in_cycle_resurrection57.getChildren();
					methodC_went_away61 = ModelTestUtils.getAssertMethod( methodtest_callback_in_cycle_resurrection57Childs, "C_went_away", 1 );
					ModelTestUtils.assertParameterNames( methodC_went_away61, new String[] {"ignore"} );
				}
			}
			//Function test:test_callbacks_on_callback
			{
			IMethod methodtest_callbacks_on_callback62;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_callbacks_on_callback62 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_callbacks_on_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callbacks_on_callback62, new String[] {"self"} );
				//Function test:safe_callback
				{
				IMethod methodsafe_callback63;
					IModelElement[] methodtest_callbacks_on_callback62Childs = methodtest_callbacks_on_callback62.getChildren();
					methodsafe_callback63 = ModelTestUtils.getAssertMethod( methodtest_callbacks_on_callback62Childs, "safe_callback", 1 );
					ModelTestUtils.assertParameterNames( methodsafe_callback63, new String[] {"ignore"} );
				}
				//Class test
				{
				IType classC64;
					IModelElement[] methodtest_callbacks_on_callback62Childs = methodtest_callbacks_on_callback62.getChildren();
					classC64 = ModelTestUtils.getAssertClass( methodtest_callbacks_on_callback62Childs, "C" );
					//Function test:cb
					{
					IMethod methodcb65;
						IModelElement[] classC64Childs = classC64.getChildren();
						methodcb65 = ModelTestUtils.getAssertMethod( classC64Childs, "cb", 2 );
						ModelTestUtils.assertParameterNames( methodcb65, new String[] {"self", "ignore"} );
					}
				}
			}
			//Function test:test_gc_during_ref_creation
			{
			IMethod methodtest_gc_during_ref_creation66;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_gc_during_ref_creation66 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_gc_during_ref_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc_during_ref_creation66, new String[] {"self"} );
			}
			//Function test:test_gc_during_proxy_creation
			{
			IMethod methodtest_gc_during_proxy_creation67;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodtest_gc_during_proxy_creation67 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "test_gc_during_proxy_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc_during_proxy_creation67, new String[] {"self"} );
			}
			//Function test:check_gc_during_creation
			{
			IMethod methodcheck_gc_during_creation68;
				IModelElement[] classReferencesTestCase11Childs = classReferencesTestCase11.getChildren();
				methodcheck_gc_during_creation68 = ModelTestUtils.getAssertMethod( classReferencesTestCase11Childs, "check_gc_during_creation", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_gc_during_creation68, new String[] {"self", "makeref"} );
				//Class test
				{
				IType classA69;
					IModelElement[] methodcheck_gc_during_creation68Childs = methodcheck_gc_during_creation68.getChildren();
					classA69 = ModelTestUtils.getAssertClass( methodcheck_gc_during_creation68Childs, "A" );
				}
				//Function test:callback
				{
				IMethod methodcallback70;
					IModelElement[] methodcheck_gc_during_creation68Childs = methodcheck_gc_during_creation68.getChildren();
					methodcallback70 = ModelTestUtils.getAssertMethod( methodcheck_gc_during_creation68Childs, "callback", 1 );
					ModelTestUtils.assertParameterNames( methodcallback70, new String[] {"args"} );
				}
			}
		}
		//Class test
		{
		IType classSubclassableWeakrefTestCase71;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassableWeakrefTestCase71 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassableWeakrefTestCase" );
			//Function test:test_subclass_refs
			{
			IMethod methodtest_subclass_refs72;
				IModelElement[] classSubclassableWeakrefTestCase71Childs = classSubclassableWeakrefTestCase71.getChildren();
				methodtest_subclass_refs72 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase71Childs, "test_subclass_refs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs72, new String[] {"self"} );
				//Class test
				{
				IType classMyRef73;
					IModelElement[] methodtest_subclass_refs72Childs = methodtest_subclass_refs72.getChildren();
					classMyRef73 = ModelTestUtils.getAssertClass( methodtest_subclass_refs72Childs, "MyRef" );
					//Function test:__init__
					{
					IMethod method__init__74;
						IModelElement[] classMyRef73Childs = classMyRef73.getChildren();
						method__init__74 = ModelTestUtils.getAssertMethod( classMyRef73Childs, "__init__", 4 );
						ModelTestUtils.assertParameterNames( method__init__74, new String[] {"self", "ob", "callback", "value"} );
					}
					//Function test:__call__
					{
					IMethod method__call__75;
						IModelElement[] classMyRef73Childs = classMyRef73.getChildren();
						method__call__75 = ModelTestUtils.getAssertMethod( classMyRef73Childs, "__call__", 1 );
						ModelTestUtils.assertParameterNames( method__call__75, new String[] {"self"} );
					}
				}
			}
			//Function test:test_subclass_refs_dont_replace_standard_refs
			{
			IMethod methodtest_subclass_refs_dont_replace_standard_refs76;
				IModelElement[] classSubclassableWeakrefTestCase71Childs = classSubclassableWeakrefTestCase71.getChildren();
				methodtest_subclass_refs_dont_replace_standard_refs76 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase71Childs, "test_subclass_refs_dont_replace_standard_refs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs_dont_replace_standard_refs76, new String[] {"self"} );
				//Class test
				{
				IType classMyRef77;
					IModelElement[] methodtest_subclass_refs_dont_replace_standard_refs76Childs = methodtest_subclass_refs_dont_replace_standard_refs76.getChildren();
					classMyRef77 = ModelTestUtils.getAssertClass( methodtest_subclass_refs_dont_replace_standard_refs76Childs, "MyRef" );
				}
			}
			//Function test:test_subclass_refs_dont_conflate_callbacks
			{
			IMethod methodtest_subclass_refs_dont_conflate_callbacks78;
				IModelElement[] classSubclassableWeakrefTestCase71Childs = classSubclassableWeakrefTestCase71.getChildren();
				methodtest_subclass_refs_dont_conflate_callbacks78 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase71Childs, "test_subclass_refs_dont_conflate_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs_dont_conflate_callbacks78, new String[] {"self"} );
				//Class test
				{
				IType classMyRef79;
					IModelElement[] methodtest_subclass_refs_dont_conflate_callbacks78Childs = methodtest_subclass_refs_dont_conflate_callbacks78.getChildren();
					classMyRef79 = ModelTestUtils.getAssertClass( methodtest_subclass_refs_dont_conflate_callbacks78Childs, "MyRef" );
				}
			}
			//Function test:test_subclass_refs_with_slots
			{
			IMethod methodtest_subclass_refs_with_slots80;
				IModelElement[] classSubclassableWeakrefTestCase71Childs = classSubclassableWeakrefTestCase71.getChildren();
				methodtest_subclass_refs_with_slots80 = ModelTestUtils.getAssertMethod( classSubclassableWeakrefTestCase71Childs, "test_subclass_refs_with_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_refs_with_slots80, new String[] {"self"} );
				//Class test
				{
				IType classMyRef81;
					IModelElement[] methodtest_subclass_refs_with_slots80Childs = methodtest_subclass_refs_with_slots80.getChildren();
					classMyRef81 = ModelTestUtils.getAssertClass( methodtest_subclass_refs_with_slots80Childs, "MyRef" );
					//Function test:__new__
					{
					IMethod method__new__82;
						IModelElement[] classMyRef81Childs = classMyRef81.getChildren();
						method__new__82 = ModelTestUtils.getAssertMethod( classMyRef81Childs, "__new__", 5 );
						ModelTestUtils.assertParameterNames( method__new__82, new String[] {"type", "ob", "callback", "slot1", "slot2"} );
					}
					//Function test:__init__
					{
					IMethod method__init__83;
						IModelElement[] classMyRef81Childs = classMyRef81.getChildren();
						method__init__83 = ModelTestUtils.getAssertMethod( classMyRef81Childs, "__init__", 5 );
						ModelTestUtils.assertParameterNames( method__init__83, new String[] {"self", "ob", "callback", "slot1", "slot2"} );
					}
					//Function test:meth
					{
					IMethod methodmeth84;
						IModelElement[] classMyRef81Childs = classMyRef81.getChildren();
						methodmeth84 = ModelTestUtils.getAssertMethod( classMyRef81Childs, "meth", 1 );
						ModelTestUtils.assertParameterNames( methodmeth84, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classObject85;
			IModelElement[] moduleChilds = module.getChildren();
			classObject85 = ModelTestUtils.getAssertClass( moduleChilds, "Object" );
			//Function test:__init__
			{
			IMethod method__init__86;
				IModelElement[] classObject85Childs = classObject85.getChildren();
				method__init__86 = ModelTestUtils.getAssertMethod( classObject85Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__86, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__87;
				IModelElement[] classObject85Childs = classObject85.getChildren();
				method__repr__87 = ModelTestUtils.getAssertMethod( classObject85Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__87, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMappingTestCase88;
			IModelElement[] moduleChilds = module.getChildren();
			classMappingTestCase88 = ModelTestUtils.getAssertClass( moduleChilds, "MappingTestCase" );
			{
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMappingTestCase88Childs, "COUNT");
			}
			//Function test:test_weak_values
			{
			IMethod methodtest_weak_values89;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_values89 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_values89, new String[] {"self"} );
			}
			//Function test:test_weak_keys
			{
			IMethod methodtest_weak_keys90;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keys90 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keys90, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_iters
			{
			IMethod methodtest_weak_keyed_iters91;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_iters91 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_iters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_iters91, new String[] {"self"} );
			}
			//Function test:test_weak_valued_iters
			{
			IMethod methodtest_weak_valued_iters92;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_valued_iters92 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_valued_iters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_iters92, new String[] {"self"} );
			}
			//Function test:check_iters
			{
			IMethod methodcheck_iters93;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodcheck_iters93 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "check_iters", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_iters93, new String[] {"self", "dict"} );
			}
			//Function test:test_make_weak_keyed_dict_from_dict
			{
			IMethod methodtest_make_weak_keyed_dict_from_dict94;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_make_weak_keyed_dict_from_dict94 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_make_weak_keyed_dict_from_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_make_weak_keyed_dict_from_dict94, new String[] {"self"} );
			}
			//Function test:test_make_weak_keyed_dict_from_weak_keyed_dict
			{
			IMethod methodtest_make_weak_keyed_dict_from_weak_keyed_dict95;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_make_weak_keyed_dict_from_weak_keyed_dict95 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_make_weak_keyed_dict_from_weak_keyed_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_make_weak_keyed_dict_from_weak_keyed_dict95, new String[] {"self"} );
			}
			//Function test:make_weak_keyed_dict
			{
			IMethod methodmake_weak_keyed_dict96;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodmake_weak_keyed_dict96 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "make_weak_keyed_dict", 1 );
				ModelTestUtils.assertParameterNames( methodmake_weak_keyed_dict96, new String[] {"self"} );
			}
			//Function test:make_weak_valued_dict
			{
			IMethod methodmake_weak_valued_dict97;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodmake_weak_valued_dict97 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "make_weak_valued_dict", 1 );
				ModelTestUtils.assertParameterNames( methodmake_weak_valued_dict97, new String[] {"self"} );
			}
			//Function test:check_popitem
			{
			IMethod methodcheck_popitem98;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodcheck_popitem98 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "check_popitem", 6 );
				ModelTestUtils.assertParameterNames( methodcheck_popitem98, new String[] {"self", "klass", "key1", "value1", "key2", "value2"} );
			}
			//Function test:test_weak_valued_dict_popitem
			{
			IMethod methodtest_weak_valued_dict_popitem99;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_valued_dict_popitem99 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_valued_dict_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_dict_popitem99, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_dict_popitem
			{
			IMethod methodtest_weak_keyed_dict_popitem100;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_dict_popitem100 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_dict_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_dict_popitem100, new String[] {"self"} );
			}
			//Function test:check_setdefault
			{
			IMethod methodcheck_setdefault101;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodcheck_setdefault101 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "check_setdefault", 5 );
				ModelTestUtils.assertParameterNames( methodcheck_setdefault101, new String[] {"self", "klass", "key", "value1", "value2"} );
			}
			//Function test:test_weak_valued_dict_setdefault
			{
			IMethod methodtest_weak_valued_dict_setdefault102;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_valued_dict_setdefault102 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_valued_dict_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_dict_setdefault102, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_dict_setdefault
			{
			IMethod methodtest_weak_keyed_dict_setdefault103;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_dict_setdefault103 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_dict_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_dict_setdefault103, new String[] {"self"} );
			}
			//Function test:check_update
			{
			IMethod methodcheck_update104;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodcheck_update104 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "check_update", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_update104, new String[] {"self", "klass", "dict"} );
			}
			//Function test:test_weak_valued_dict_update
			{
			IMethod methodtest_weak_valued_dict_update105;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_valued_dict_update105 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_valued_dict_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_dict_update105, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_dict_update
			{
			IMethod methodtest_weak_keyed_dict_update106;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_dict_update106 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_dict_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_dict_update106, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_delitem
			{
			IMethod methodtest_weak_keyed_delitem107;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_delitem107 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_delitem107, new String[] {"self"} );
			}
			//Function test:test_weak_valued_delitem
			{
			IMethod methodtest_weak_valued_delitem108;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_valued_delitem108 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_valued_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_valued_delitem108, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_bad_delitem
			{
			IMethod methodtest_weak_keyed_bad_delitem109;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_bad_delitem109 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_bad_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_bad_delitem109, new String[] {"self"} );
			}
			//Function test:test_weak_keyed_cascading_deletes
			{
			IMethod methodtest_weak_keyed_cascading_deletes110;
				IModelElement[] classMappingTestCase88Childs = classMappingTestCase88.getChildren();
				methodtest_weak_keyed_cascading_deletes110 = ModelTestUtils.getAssertMethod( classMappingTestCase88Childs, "test_weak_keyed_cascading_deletes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weak_keyed_cascading_deletes110, new String[] {"self"} );
				//Class test
				{
				IType classC111;
					IModelElement[] methodtest_weak_keyed_cascading_deletes110Childs = methodtest_weak_keyed_cascading_deletes110.getChildren();
					classC111 = ModelTestUtils.getAssertClass( methodtest_weak_keyed_cascading_deletes110Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__112;
						IModelElement[] classC111Childs = classC111.getChildren();
						method__init__112 = ModelTestUtils.getAssertMethod( classC111Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__112, new String[] {"self", "i"} );
					}
					//Function test:__hash__
					{
					IMethod method__hash__113;
						IModelElement[] classC111Childs = classC111.getChildren();
						method__hash__113 = ModelTestUtils.getAssertMethod( classC111Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__113, new String[] {"self"} );
					}
					//Function test:__eq__
					{
					IMethod method__eq__114;
						IModelElement[] classC111Childs = classC111.getChildren();
						method__eq__114 = ModelTestUtils.getAssertMethod( classC111Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__114, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Class test
		{
		IType classWeakValueDictionaryTestCase115;
			IModelElement[] moduleChilds = module.getChildren();
			classWeakValueDictionaryTestCase115 = ModelTestUtils.getAssertClass( moduleChilds, "WeakValueDictionaryTestCase" );
			{
				IModelElement[] classWeakValueDictionaryTestCase115Childs = classWeakValueDictionaryTestCase115.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakValueDictionaryTestCase115Childs, "__ref");
			}
			{
				IModelElement[] classWeakValueDictionaryTestCase115Childs = classWeakValueDictionaryTestCase115.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakValueDictionaryTestCase115Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference116;
				IModelElement[] classWeakValueDictionaryTestCase115Childs = classWeakValueDictionaryTestCase115.getChildren();
				method_reference116 = ModelTestUtils.getAssertMethod( classWeakValueDictionaryTestCase115Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference116, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWeakKeyDictionaryTestCase117;
			IModelElement[] moduleChilds = module.getChildren();
			classWeakKeyDictionaryTestCase117 = ModelTestUtils.getAssertClass( moduleChilds, "WeakKeyDictionaryTestCase" );
			{
				IModelElement[] classWeakKeyDictionaryTestCase117Childs = classWeakKeyDictionaryTestCase117.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakKeyDictionaryTestCase117Childs, "__ref");
			}
			{
				IModelElement[] classWeakKeyDictionaryTestCase117Childs = classWeakKeyDictionaryTestCase117.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWeakKeyDictionaryTestCase117Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference118;
				IModelElement[] classWeakKeyDictionaryTestCase117Childs = classWeakKeyDictionaryTestCase117.getChildren();
				method_reference118 = ModelTestUtils.getAssertMethod( classWeakKeyDictionaryTestCase117Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference118, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main119;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main119 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
		}
		//Function test:testUserData
		{
		IMethod methodtestUserData100;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestUserData100 = ModelTestUtils.getAssertMethod( moduleChilds, "testUserData", 0 );
		}
		//Function test:testRenameAttribute
		{
		IMethod methodtestRenameAttribute101;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRenameAttribute101 = ModelTestUtils.getAssertMethod( moduleChilds, "testRenameAttribute", 0 );
		}
		//Function test:testRenameElement
		{
		IMethod methodtestRenameElement102;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRenameElement102 = ModelTestUtils.getAssertMethod( moduleChilds, "testRenameElement", 0 );
		}
		//Function test:checkRenameNodeSharedConstraints
		{
		IMethod methodcheckRenameNodeSharedConstraints103;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheckRenameNodeSharedConstraints103 = ModelTestUtils.getAssertMethod( moduleChilds, "checkRenameNodeSharedConstraints", 2 );
			ModelTestUtils.assertParameterNames( methodcheckRenameNodeSharedConstraints103, new String[] {"doc", "node"} );
		}
		//Function test:testRenameOther
		{
		IMethod methodtestRenameOther104;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestRenameOther104 = ModelTestUtils.getAssertMethod( moduleChilds, "testRenameOther", 0 );
		}
		//Function test:checkWholeText
		{
		IMethod methodcheckWholeText105;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheckWholeText105 = ModelTestUtils.getAssertMethod( moduleChilds, "checkWholeText", 2 );
			ModelTestUtils.assertParameterNames( methodcheckWholeText105, new String[] {"node", "s"} );
		}
		//Function test:testWholeText
		{
		IMethod methodtestWholeText106;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestWholeText106 = ModelTestUtils.getAssertMethod( moduleChilds, "testWholeText", 0 );
		}
		//Function test:testReplaceWholeText
		{
		IMethod methodtestReplaceWholeText107;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestReplaceWholeText107 = ModelTestUtils.getAssertMethod( moduleChilds, "testReplaceWholeText", 0 );
			//Function test:setup
			{
			IMethod methodsetup108;
				IModelElement[] methodtestReplaceWholeText107Childs = methodtestReplaceWholeText107.getChildren();
				methodsetup108 = ModelTestUtils.getAssertMethod( methodtestReplaceWholeText107Childs, "setup", 0 );
			}
		}
		//Function test:testSchemaType
		{
		IMethod methodtestSchemaType109;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSchemaType109 = ModelTestUtils.getAssertMethod( moduleChilds, "testSchemaType", 0 );
		}
		//Function test:testSetIdAttribute
		{
		IMethod methodtestSetIdAttribute110;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetIdAttribute110 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetIdAttribute", 0 );
		}
		//Function test:testSetIdAttributeNS
		{
		IMethod methodtestSetIdAttributeNS111;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetIdAttributeNS111 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetIdAttributeNS", 0 );
		}
		//Function test:testSetIdAttributeNode
		{
		IMethod methodtestSetIdAttributeNode112;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestSetIdAttributeNode112 = ModelTestUtils.getAssertMethod( moduleChilds, "testSetIdAttributeNode", 0 );
		}
		//Function test:testPickledDocument
		{
		IMethod methodtestPickledDocument113;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestPickledDocument113 = ModelTestUtils.getAssertMethod( moduleChilds, "testPickledDocument", 0 );
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
		IMethod methodcheck_allnodes114;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_allnodes114 = ModelTestUtils.getAssertMethod( moduleChilds, "check_allnodes", 0 );
		}
		//Function test:check_allnodes
		{
		IMethod methodcheck_allnodes115;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_allnodes115 = ModelTestUtils.getAssertMethod( moduleChilds, "check_allnodes", 0 );
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
			//Function test:isvalid
			{
			IMethod methodisvalid15;
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				methodisvalid15 = ModelTestUtils.getAssertMethod( class_ExpectedSkips13Childs, "isvalid", 1 );
				ModelTestUtils.assertParameterNames( methodisvalid15, new String[] {"self"} );
			}
			//Function test:getexpected
			{
			IMethod methodgetexpected16;
				IModelElement[] class_ExpectedSkips13Childs = class_ExpectedSkips13.getChildren();
				methodgetexpected16 = ModelTestUtils.getAssertMethod( class_ExpectedSkips13Childs, "getexpected", 1 );
				ModelTestUtils.assertParameterNames( methodgetexpected16, new String[] {"self"} );
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
			//Function test:tearDown
			{
			IMethod methodtearDown8;
				IModelElement[] classMonthCalendarTestCase6Childs = classMonthCalendarTestCase6.getChildren();
				methodtearDown8 = ModelTestUtils.getAssertMethod( classMonthCalendarTestCase6Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown8, new String[] {"self"} );
			}
			//Function test:check_weeks
			{
			IMethod methodcheck_weeks9;
				IModelElement[] classMonthCalendarTestCase6Childs = classMonthCalendarTestCase6.getChildren();
				methodcheck_weeks9 = ModelTestUtils.getAssertMethod( classMonthCalendarTestCase6Childs, "check_weeks", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_weeks9, new String[] {"self", "year", "month", "weeks"} );
			}
		}
		//Class test
		{
		IType classMondayTestCase10;
			IModelElement[] moduleChilds = module.getChildren();
			classMondayTestCase10 = ModelTestUtils.getAssertClass( moduleChilds, "MondayTestCase" );
			{
				IModelElement[] classMondayTestCase10Childs = classMondayTestCase10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMondayTestCase10Childs, "firstweekday");
			}
			//Function test:test_february
			{
			IMethod methodtest_february11;
				IModelElement[] classMondayTestCase10Childs = classMondayTestCase10.getChildren();
				methodtest_february11 = ModelTestUtils.getAssertMethod( classMondayTestCase10Childs, "test_february", 1 );
				ModelTestUtils.assertParameterNames( methodtest_february11, new String[] {"self"} );
			}
			//Function test:test_april
			{
			IMethod methodtest_april12;
				IModelElement[] classMondayTestCase10Childs = classMondayTestCase10.getChildren();
				methodtest_april12 = ModelTestUtils.getAssertMethod( classMondayTestCase10Childs, "test_april", 1 );
				ModelTestUtils.assertParameterNames( methodtest_april12, new String[] {"self"} );
			}
			//Function test:test_december
			{
			IMethod methodtest_december13;
				IModelElement[] classMondayTestCase10Childs = classMondayTestCase10.getChildren();
				methodtest_december13 = ModelTestUtils.getAssertMethod( classMondayTestCase10Childs, "test_december", 1 );
				ModelTestUtils.assertParameterNames( methodtest_december13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSundayTestCase14;
			IModelElement[] moduleChilds = module.getChildren();
			classSundayTestCase14 = ModelTestUtils.getAssertClass( moduleChilds, "SundayTestCase" );
			{
				IModelElement[] classSundayTestCase14Childs = classSundayTestCase14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSundayTestCase14Childs, "firstweekday");
			}
			//Function test:test_february
			{
			IMethod methodtest_february15;
				IModelElement[] classSundayTestCase14Childs = classSundayTestCase14.getChildren();
				methodtest_february15 = ModelTestUtils.getAssertMethod( classSundayTestCase14Childs, "test_february", 1 );
				ModelTestUtils.assertParameterNames( methodtest_february15, new String[] {"self"} );
			}
			//Function test:test_april
			{
			IMethod methodtest_april16;
				IModelElement[] classSundayTestCase14Childs = classSundayTestCase14.getChildren();
				methodtest_april16 = ModelTestUtils.getAssertMethod( classSundayTestCase14Childs, "test_april", 1 );
				ModelTestUtils.assertParameterNames( methodtest_april16, new String[] {"self"} );
			}
			//Function test:test_december
			{
			IMethod methodtest_december17;
				IModelElement[] classSundayTestCase14Childs = classSundayTestCase14.getChildren();
				methodtest_december17 = ModelTestUtils.getAssertMethod( classSundayTestCase14Childs, "test_december", 1 );
				ModelTestUtils.assertParameterNames( methodtest_december17, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:eval_file
			{
			IMethod methodeval_file4;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_file4 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_file", 2 );
				ModelTestUtils.assertParameterNames( methodeval_file4, new String[] {"self", "file"} );
			}
			//Function test:eval_line
			{
			IMethod methodeval_line5;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_line5 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_line", 2 );
				ModelTestUtils.assertParameterNames( methodeval_line5, new String[] {"self", "s"} );
			}
			//Function test:eval_directive
			{
			IMethod methodeval_directive6;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_directive6 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_directive", 2 );
				ModelTestUtils.assertParameterNames( methodeval_directive6, new String[] {"self", "s"} );
			}
			//Function test:eval_equation
			{
			IMethod methodeval_equation7;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodeval_equation7 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "eval_equation", 2 );
				ModelTestUtils.assertParameterNames( methodeval_equation7, new String[] {"self", "s"} );
				//Function test:FixQuotes
				{
				IMethod methodFixQuotes8;
					IModelElement[] methodeval_equation7Childs = methodeval_equation7.getChildren();
					methodFixQuotes8 = ModelTestUtils.getAssertMethod( methodeval_equation7Childs, "FixQuotes", 1 );
					ModelTestUtils.assertParameterNames( methodFixQuotes8, new String[] {"val"} );
				}
			}
			//Function test:getexceptions
			{
			IMethod methodgetexceptions9;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodgetexceptions9 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "getexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodgetexceptions9, new String[] {"self"} );
			}
			//Function test:change_precision
			{
			IMethod methodchange_precision10;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_precision10 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_precision", 2 );
				ModelTestUtils.assertParameterNames( methodchange_precision10, new String[] {"self", "prec"} );
			}
			//Function test:change_rounding_method
			{
			IMethod methodchange_rounding_method11;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_rounding_method11 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_rounding_method", 2 );
				ModelTestUtils.assertParameterNames( methodchange_rounding_method11, new String[] {"self", "rounding"} );
			}
			//Function test:change_min_exponent
			{
			IMethod methodchange_min_exponent12;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_min_exponent12 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_min_exponent", 2 );
				ModelTestUtils.assertParameterNames( methodchange_min_exponent12, new String[] {"self", "exp"} );
			}
			//Function test:change_max_exponent
			{
			IMethod methodchange_max_exponent13;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_max_exponent13 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_max_exponent", 2 );
				ModelTestUtils.assertParameterNames( methodchange_max_exponent13, new String[] {"self", "exp"} );
			}
			//Function test:change_clamp
			{
			IMethod methodchange_clamp14;
				IModelElement[] classDecimalTest1Childs = classDecimalTest1.getChildren();
				methodchange_clamp14 = ModelTestUtils.getAssertMethod( classDecimalTest1Childs, "change_clamp", 2 );
				ModelTestUtils.assertParameterNames( methodchange_clamp14, new String[] {"self", "clamp"} );
			}
		}
		//Class test
		{
		IType classDecimalExplicitConstructionTest15;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalExplicitConstructionTest15 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalExplicitConstructionTest" );
			//Function test:test_explicit_empty
			{
			IMethod methodtest_explicit_empty16;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_empty16 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_empty16, new String[] {"self"} );
			}
			//Function test:test_explicit_from_None
			{
			IMethod methodtest_explicit_from_None17;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_from_None17 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_from_None", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_None17, new String[] {"self"} );
			}
			//Function test:test_explicit_from_int
			{
			IMethod methodtest_explicit_from_int18;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_from_int18 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_from_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_int18, new String[] {"self"} );
			}
			//Function test:test_explicit_from_string
			{
			IMethod methodtest_explicit_from_string19;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_from_string19 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_from_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_string19, new String[] {"self"} );
			}
			//Function test:test_explicit_from_tuples
			{
			IMethod methodtest_explicit_from_tuples20;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_from_tuples20 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_from_tuples", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_tuples20, new String[] {"self"} );
			}
			//Function test:test_explicit_from_Decimal
			{
			IMethod methodtest_explicit_from_Decimal21;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_from_Decimal21 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_from_Decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_from_Decimal21, new String[] {"self"} );
			}
			//Function test:test_explicit_context_create_decimal
			{
			IMethod methodtest_explicit_context_create_decimal22;
				IModelElement[] classDecimalExplicitConstructionTest15Childs = classDecimalExplicitConstructionTest15.getChildren();
				methodtest_explicit_context_create_decimal22 = ModelTestUtils.getAssertMethod( classDecimalExplicitConstructionTest15Childs, "test_explicit_context_create_decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_explicit_context_create_decimal22, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDecimalImplicitConstructionTest23;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalImplicitConstructionTest23 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalImplicitConstructionTest" );
			//Function test:test_implicit_from_None
			{
			IMethod methodtest_implicit_from_None24;
				IModelElement[] classDecimalImplicitConstructionTest23Childs = classDecimalImplicitConstructionTest23.getChildren();
				methodtest_implicit_from_None24 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest23Childs, "test_implicit_from_None", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_None24, new String[] {"self"} );
			}
			//Function test:test_implicit_from_int
			{
			IMethod methodtest_implicit_from_int25;
				IModelElement[] classDecimalImplicitConstructionTest23Childs = classDecimalImplicitConstructionTest23.getChildren();
				methodtest_implicit_from_int25 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest23Childs, "test_implicit_from_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_int25, new String[] {"self"} );
			}
			//Function test:test_implicit_from_string
			{
			IMethod methodtest_implicit_from_string26;
				IModelElement[] classDecimalImplicitConstructionTest23Childs = classDecimalImplicitConstructionTest23.getChildren();
				methodtest_implicit_from_string26 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest23Childs, "test_implicit_from_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_string26, new String[] {"self"} );
			}
			//Function test:test_implicit_from_float
			{
			IMethod methodtest_implicit_from_float27;
				IModelElement[] classDecimalImplicitConstructionTest23Childs = classDecimalImplicitConstructionTest23.getChildren();
				methodtest_implicit_from_float27 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest23Childs, "test_implicit_from_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_float27, new String[] {"self"} );
			}
			//Function test:test_implicit_from_Decimal
			{
			IMethod methodtest_implicit_from_Decimal28;
				IModelElement[] classDecimalImplicitConstructionTest23Childs = classDecimalImplicitConstructionTest23.getChildren();
				methodtest_implicit_from_Decimal28 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest23Childs, "test_implicit_from_Decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_implicit_from_Decimal28, new String[] {"self"} );
			}
			//Function test:test_rop
			{
			IMethod methodtest_rop29;
				IModelElement[] classDecimalImplicitConstructionTest23Childs = classDecimalImplicitConstructionTest23.getChildren();
				methodtest_rop29 = ModelTestUtils.getAssertMethod( classDecimalImplicitConstructionTest23Childs, "test_rop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rop29, new String[] {"self"} );
				//Class test
				{
				IType classE30;
					IModelElement[] methodtest_rop29Childs = methodtest_rop29.getChildren();
					classE30 = ModelTestUtils.getAssertClass( methodtest_rop29Childs, "E" );
					//Function test:__divmod__
					{
					IMethod method__divmod__31;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__divmod__31 = ModelTestUtils.getAssertMethod( classE30Childs, "__divmod__", 2 );
						ModelTestUtils.assertParameterNames( method__divmod__31, new String[] {"self", "other"} );
					}
					//Function test:__rdivmod__
					{
					IMethod method__rdivmod__32;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__rdivmod__32 = ModelTestUtils.getAssertMethod( classE30Childs, "__rdivmod__", 2 );
						ModelTestUtils.assertParameterNames( method__rdivmod__32, new String[] {"self", "other"} );
					}
					//Function test:__lt__
					{
					IMethod method__lt__33;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__lt__33 = ModelTestUtils.getAssertMethod( classE30Childs, "__lt__", 2 );
						ModelTestUtils.assertParameterNames( method__lt__33, new String[] {"self", "other"} );
					}
					//Function test:__gt__
					{
					IMethod method__gt__34;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__gt__34 = ModelTestUtils.getAssertMethod( classE30Childs, "__gt__", 2 );
						ModelTestUtils.assertParameterNames( method__gt__34, new String[] {"self", "other"} );
					}
					//Function test:__le__
					{
					IMethod method__le__35;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__le__35 = ModelTestUtils.getAssertMethod( classE30Childs, "__le__", 2 );
						ModelTestUtils.assertParameterNames( method__le__35, new String[] {"self", "other"} );
					}
					//Function test:__ge__
					{
					IMethod method__ge__36;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__ge__36 = ModelTestUtils.getAssertMethod( classE30Childs, "__ge__", 2 );
						ModelTestUtils.assertParameterNames( method__ge__36, new String[] {"self", "other"} );
					}
					//Function test:__eq__
					{
					IMethod method__eq__37;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__eq__37 = ModelTestUtils.getAssertMethod( classE30Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__37, new String[] {"self", "other"} );
					}
					//Function test:__ne__
					{
					IMethod method__ne__38;
						IModelElement[] classE30Childs = classE30.getChildren();
						method__ne__38 = ModelTestUtils.getAssertMethod( classE30Childs, "__ne__", 2 );
						ModelTestUtils.assertParameterNames( method__ne__38, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Class test
		{
		IType classDecimalArithmeticOperatorsTest39;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalArithmeticOperatorsTest39 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalArithmeticOperatorsTest" );
			//Function test:test_addition
			{
			IMethod methodtest_addition40;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_addition40 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_addition", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addition40, new String[] {"self"} );
			}
			//Function test:test_subtraction
			{
			IMethod methodtest_subtraction41;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_subtraction41 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_subtraction", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subtraction41, new String[] {"self"} );
			}
			//Function test:test_multiplication
			{
			IMethod methodtest_multiplication42;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_multiplication42 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_multiplication", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiplication42, new String[] {"self"} );
			}
			//Function test:test_division
			{
			IMethod methodtest_division43;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_division43 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_division", 1 );
				ModelTestUtils.assertParameterNames( methodtest_division43, new String[] {"self"} );
			}
			//Function test:test_floor_division
			{
			IMethod methodtest_floor_division44;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_floor_division44 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_floor_division", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floor_division44, new String[] {"self"} );
			}
			//Function test:test_powering
			{
			IMethod methodtest_powering45;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_powering45 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_powering", 1 );
				ModelTestUtils.assertParameterNames( methodtest_powering45, new String[] {"self"} );
			}
			//Function test:test_module
			{
			IMethod methodtest_module46;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_module46 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_module", 1 );
				ModelTestUtils.assertParameterNames( methodtest_module46, new String[] {"self"} );
			}
			//Function test:test_floor_div_module
			{
			IMethod methodtest_floor_div_module47;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_floor_div_module47 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_floor_div_module", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floor_div_module47, new String[] {"self"} );
			}
			//Function test:test_unary_operators
			{
			IMethod methodtest_unary_operators48;
				IModelElement[] classDecimalArithmeticOperatorsTest39Childs = classDecimalArithmeticOperatorsTest39.getChildren();
				methodtest_unary_operators48 = ModelTestUtils.getAssertMethod( classDecimalArithmeticOperatorsTest39Childs, "test_unary_operators", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unary_operators48, new String[] {"self"} );
			}
		}
		//Function test:thfunc1
		{
		IMethod methodthfunc149;
			IModelElement[] moduleChilds = module.getChildren();
			methodthfunc149 = ModelTestUtils.getAssertMethod( moduleChilds, "thfunc1", 1 );
			ModelTestUtils.assertParameterNames( methodthfunc149, new String[] {"cls"} );
		}
		//Function test:thfunc2
		{
		IMethod methodthfunc250;
			IModelElement[] moduleChilds = module.getChildren();
			methodthfunc250 = ModelTestUtils.getAssertMethod( moduleChilds, "thfunc2", 1 );
			ModelTestUtils.assertParameterNames( methodthfunc250, new String[] {"cls"} );
		}
		//Class test
		{
		IType classDecimalUseOfContextTest51;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalUseOfContextTest51 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalUseOfContextTest" );
			{
				IModelElement[] classDecimalUseOfContextTest51Childs = classDecimalUseOfContextTest51.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDecimalUseOfContextTest51Childs, "threading");
			}
			//Function test:test_threading
			{
			IMethod methodtest_threading52;
				IModelElement[] classDecimalUseOfContextTest51Childs = classDecimalUseOfContextTest51.getChildren();
				methodtest_threading52 = ModelTestUtils.getAssertMethod( classDecimalUseOfContextTest51Childs, "test_threading", 1 );
				ModelTestUtils.assertParameterNames( methodtest_threading52, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDecimalUsabilityTest53;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalUsabilityTest53 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalUsabilityTest" );
			//Function test:test_comparison_operators
			{
			IMethod methodtest_comparison_operators54;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_comparison_operators54 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_comparison_operators", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparison_operators54, new String[] {"self"} );
			}
			//Function test:test_copy_and_deepcopy_methods
			{
			IMethod methodtest_copy_and_deepcopy_methods55;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_copy_and_deepcopy_methods55 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_copy_and_deepcopy_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy_and_deepcopy_methods55, new String[] {"self"} );
			}
			//Function test:test_hash_method
			{
			IMethod methodtest_hash_method56;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_hash_method56 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_hash_method", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_method56, new String[] {"self"} );
			}
			//Function test:test_min_and_max_methods
			{
			IMethod methodtest_min_and_max_methods57;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_min_and_max_methods57 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_min_and_max_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_min_and_max_methods57, new String[] {"self"} );
			}
			//Function test:test_as_nonzero
			{
			IMethod methodtest_as_nonzero58;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_as_nonzero58 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_as_nonzero", 1 );
				ModelTestUtils.assertParameterNames( methodtest_as_nonzero58, new String[] {"self"} );
			}
			//Function test:test_tostring_methods
			{
			IMethod methodtest_tostring_methods59;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_tostring_methods59 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_tostring_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tostring_methods59, new String[] {"self"} );
			}
			//Function test:test_tonum_methods
			{
			IMethod methodtest_tonum_methods60;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_tonum_methods60 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_tonum_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tonum_methods60, new String[] {"self"} );
			}
			//Function test:test_eval_round_trip
			{
			IMethod methodtest_eval_round_trip61;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_eval_round_trip61 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_eval_round_trip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eval_round_trip61, new String[] {"self"} );
			}
			//Function test:test_as_tuple
			{
			IMethod methodtest_as_tuple62;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_as_tuple62 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_as_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_as_tuple62, new String[] {"self"} );
			}
			//Function test:test_immutability_operations
			{
			IMethod methodtest_immutability_operations63;
				IModelElement[] classDecimalUsabilityTest53Childs = classDecimalUsabilityTest53.getChildren();
				methodtest_immutability_operations63 = ModelTestUtils.getAssertMethod( classDecimalUsabilityTest53Childs, "test_immutability_operations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_immutability_operations63, new String[] {"self"} );
				//Function test:checkSameDec
				{
				IMethod methodcheckSameDec64;
					IModelElement[] methodtest_immutability_operations63Childs = methodtest_immutability_operations63.getChildren();
					methodcheckSameDec64 = ModelTestUtils.getAssertMethod( methodtest_immutability_operations63Childs, "checkSameDec", 2 );
					ModelTestUtils.assertParameterNames( methodcheckSameDec64, new String[] {"operation", "useOther"} );
				}
			}
		}
		//Class test
		{
		IType classDecimalPythonAPItests65;
			IModelElement[] moduleChilds = module.getChildren();
			classDecimalPythonAPItests65 = ModelTestUtils.getAssertClass( moduleChilds, "DecimalPythonAPItests" );
			//Function test:test_pickle
			{
			IMethod methodtest_pickle66;
				IModelElement[] classDecimalPythonAPItests65Childs = classDecimalPythonAPItests65.getChildren();
				methodtest_pickle66 = ModelTestUtils.getAssertMethod( classDecimalPythonAPItests65Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle66, new String[] {"self"} );
			}
			//Function test:test_int
			{
			IMethod methodtest_int67;
				IModelElement[] classDecimalPythonAPItests65Childs = classDecimalPythonAPItests65.getChildren();
				methodtest_int67 = ModelTestUtils.getAssertMethod( classDecimalPythonAPItests65Childs, "test_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int67, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classContextAPItests68;
			IModelElement[] moduleChilds = module.getChildren();
			classContextAPItests68 = ModelTestUtils.getAssertClass( moduleChilds, "ContextAPItests" );
			//Function test:test_pickle
			{
			IMethod methodtest_pickle69;
				IModelElement[] classContextAPItests68Childs = classContextAPItests68.getChildren();
				methodtest_pickle69 = ModelTestUtils.getAssertMethod( classContextAPItests68Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle69, new String[] {"self"} );
			}
			//Function test:test_equality_with_other_types
			{
			IMethod methodtest_equality_with_other_types70;
				IModelElement[] classContextAPItests68Childs = classContextAPItests68.getChildren();
				methodtest_equality_with_other_types70 = ModelTestUtils.getAssertMethod( classContextAPItests68Childs, "test_equality_with_other_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equality_with_other_types70, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy71;
				IModelElement[] classContextAPItests68Childs = classContextAPItests68.getChildren();
				methodtest_copy71 = ModelTestUtils.getAssertMethod( classContextAPItests68Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy71, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main72;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main72 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 2 );
			ModelTestUtils.assertParameterNames( methodtest_main72, new String[] {"arith", "verbose"} );
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
			//Function test:trace
			{
			IMethod methodtrace18;
				IModelElement[] classTracer16Childs = classTracer16.getChildren();
				methodtrace18 = ModelTestUtils.getAssertMethod( classTracer16Childs, "trace", 4 );
				ModelTestUtils.assertParameterNames( methodtrace18, new String[] {"self", "frame", "event", "arg"} );
			}
		}
		//Class test
		{
		IType classTraceTestCase19;
			IModelElement[] moduleChilds = module.getChildren();
			classTraceTestCase19 = ModelTestUtils.getAssertClass( moduleChilds, "TraceTestCase" );
			//Function test:compare_events
			{
			IMethod methodcompare_events20;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodcompare_events20 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "compare_events", 4 );
				ModelTestUtils.assertParameterNames( methodcompare_events20, new String[] {"self", "line_offset", "events", "expected_events"} );
			}
			//Function test:run_test
			{
			IMethod methodrun_test21;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodrun_test21 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "run_test", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test21, new String[] {"self", "func"} );
			}
			//Function test:run_test2
			{
			IMethod methodrun_test222;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodrun_test222 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "run_test2", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test222, new String[] {"self", "func"} );
			}
			//Function test:test_01_basic
			{
			IMethod methodtest_01_basic23;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_01_basic23 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_01_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_01_basic23, new String[] {"self"} );
			}
			//Function test:test_02_arigo
			{
			IMethod methodtest_02_arigo24;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_02_arigo24 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_02_arigo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_02_arigo24, new String[] {"self"} );
			}
			//Function test:test_03_one_instr
			{
			IMethod methodtest_03_one_instr25;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_03_one_instr25 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_03_one_instr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_03_one_instr25, new String[] {"self"} );
			}
			//Function test:test_04_no_pop_blocks
			{
			IMethod methodtest_04_no_pop_blocks26;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_04_no_pop_blocks26 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_04_no_pop_blocks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_04_no_pop_blocks26, new String[] {"self"} );
			}
			//Function test:test_05_no_pop_tops
			{
			IMethod methodtest_05_no_pop_tops27;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_05_no_pop_tops27 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_05_no_pop_tops", 1 );
				ModelTestUtils.assertParameterNames( methodtest_05_no_pop_tops27, new String[] {"self"} );
			}
			//Function test:test_06_call
			{
			IMethod methodtest_06_call28;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_06_call28 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_06_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_06_call28, new String[] {"self"} );
			}
			//Function test:test_07_raise
			{
			IMethod methodtest_07_raise29;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_07_raise29 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_07_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_07_raise29, new String[] {"self"} );
			}
			//Function test:test_08_settrace_and_return
			{
			IMethod methodtest_08_settrace_and_return30;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_08_settrace_and_return30 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_08_settrace_and_return", 1 );
				ModelTestUtils.assertParameterNames( methodtest_08_settrace_and_return30, new String[] {"self"} );
			}
			//Function test:test_09_settrace_and_raise
			{
			IMethod methodtest_09_settrace_and_raise31;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_09_settrace_and_raise31 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_09_settrace_and_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_09_settrace_and_raise31, new String[] {"self"} );
			}
			//Function test:test_10_ireturn
			{
			IMethod methodtest_10_ireturn32;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_10_ireturn32 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_10_ireturn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_10_ireturn32, new String[] {"self"} );
			}
			//Function test:test_11_tightloop
			{
			IMethod methodtest_11_tightloop33;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_11_tightloop33 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_11_tightloop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_11_tightloop33, new String[] {"self"} );
			}
			//Function test:test_12_tighterloop
			{
			IMethod methodtest_12_tighterloop34;
				IModelElement[] classTraceTestCase19Childs = classTraceTestCase19.getChildren();
				methodtest_12_tighterloop34 = ModelTestUtils.getAssertMethod( classTraceTestCase19Childs, "test_12_tighterloop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_12_tighterloop34, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRaisingTraceFuncTestCase35;
			IModelElement[] moduleChilds = module.getChildren();
			classRaisingTraceFuncTestCase35 = ModelTestUtils.getAssertClass( moduleChilds, "RaisingTraceFuncTestCase" );
			//Function test:trace
			{
			IMethod methodtrace36;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodtrace36 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "trace", 4 );
				ModelTestUtils.assertParameterNames( methodtrace36, new String[] {"self", "frame", "event", "arg"} );
			}
			//Function test:f
			{
			IMethod methodf37;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodf37 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "f", 1 );
				ModelTestUtils.assertParameterNames( methodf37, new String[] {"self"} );
			}
			//Function test:run_test_for_event
			{
			IMethod methodrun_test_for_event38;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodrun_test_for_event38 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "run_test_for_event", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test_for_event38, new String[] {"self", "event"} );
			}
			//Function test:test_call
			{
			IMethod methodtest_call39;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodtest_call39 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "test_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_call39, new String[] {"self"} );
			}
			//Function test:test_line
			{
			IMethod methodtest_line40;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodtest_line40 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "test_line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_line40, new String[] {"self"} );
			}
			//Function test:test_return
			{
			IMethod methodtest_return41;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodtest_return41 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "test_return", 1 );
				ModelTestUtils.assertParameterNames( methodtest_return41, new String[] {"self"} );
			}
			//Function test:test_exception
			{
			IMethod methodtest_exception42;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodtest_exception42 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "test_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception42, new String[] {"self"} );
			}
			//Function test:test_trash_stack
			{
			IMethod methodtest_trash_stack43;
				IModelElement[] classRaisingTraceFuncTestCase35Childs = classRaisingTraceFuncTestCase35.getChildren();
				methodtest_trash_stack43 = ModelTestUtils.getAssertMethod( classRaisingTraceFuncTestCase35Childs, "test_trash_stack", 1 );
				ModelTestUtils.assertParameterNames( methodtest_trash_stack43, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf44;
					IModelElement[] methodtest_trash_stack43Childs = methodtest_trash_stack43.getChildren();
					methodf44 = ModelTestUtils.getAssertMethod( methodtest_trash_stack43Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg45;
					IModelElement[] methodtest_trash_stack43Childs = methodtest_trash_stack43.getChildren();
					methodg45 = ModelTestUtils.getAssertMethod( methodtest_trash_stack43Childs, "g", 3 );
					ModelTestUtils.assertParameterNames( methodg45, new String[] {"frame", "why", "extra"} );
				}
			}
		}
		//Class test
		{
		IType classJumpTracer46;
			IModelElement[] moduleChilds = module.getChildren();
			classJumpTracer46 = ModelTestUtils.getAssertClass( moduleChilds, "JumpTracer" );
			//Function test:__init__
			{
			IMethod method__init__47;
				IModelElement[] classJumpTracer46Childs = classJumpTracer46.getChildren();
				method__init__47 = ModelTestUtils.getAssertMethod( classJumpTracer46Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__47, new String[] {"self", "function"} );
			}
			//Function test:trace
			{
			IMethod methodtrace48;
				IModelElement[] classJumpTracer46Childs = classJumpTracer46.getChildren();
				methodtrace48 = ModelTestUtils.getAssertMethod( classJumpTracer46Childs, "trace", 4 );
				ModelTestUtils.assertParameterNames( methodtrace48, new String[] {"self", "frame", "event", "arg"} );
			}
		}
		//Function test:jump_simple_forwards
		{
		IMethod methodjump_simple_forwards49;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_simple_forwards49 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_simple_forwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_simple_forwards49, new String[] {"output"} );
		}
		//Function test:jump_simple_backwards
		{
		IMethod methodjump_simple_backwards50;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_simple_backwards50 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_simple_backwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_simple_backwards50, new String[] {"output"} );
		}
		//Function test:jump_out_of_block_forwards
		{
		IMethod methodjump_out_of_block_forwards51;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_out_of_block_forwards51 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_out_of_block_forwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_out_of_block_forwards51, new String[] {"output"} );
		}
		//Function test:jump_out_of_block_backwards
		{
		IMethod methodjump_out_of_block_backwards52;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_out_of_block_backwards52 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_out_of_block_backwards", 1 );
			ModelTestUtils.assertParameterNames( methodjump_out_of_block_backwards52, new String[] {"output"} );
		}
		//Function test:jump_to_codeless_line
		{
		IMethod methodjump_to_codeless_line53;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_to_codeless_line53 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_to_codeless_line", 1 );
			ModelTestUtils.assertParameterNames( methodjump_to_codeless_line53, new String[] {"output"} );
		}
		//Function test:jump_to_same_line
		{
		IMethod methodjump_to_same_line54;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_to_same_line54 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_to_same_line", 1 );
			ModelTestUtils.assertParameterNames( methodjump_to_same_line54, new String[] {"output"} );
		}
		//Function test:jump_in_nested_finally
		{
		IMethod methodjump_in_nested_finally55;
			IModelElement[] moduleChilds = module.getChildren();
			methodjump_in_nested_finally55 = ModelTestUtils.getAssertMethod( moduleChilds, "jump_in_nested_finally", 1 );
			ModelTestUtils.assertParameterNames( methodjump_in_nested_finally55, new String[] {"output"} );
		}
		//Function test:no_jump_too_far_forwards
		{
		IMethod methodno_jump_too_far_forwards56;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_too_far_forwards56 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_too_far_forwards", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_too_far_forwards56, new String[] {"output"} );
		}
		//Function test:no_jump_too_far_backwards
		{
		IMethod methodno_jump_too_far_backwards57;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_too_far_backwards57 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_too_far_backwards", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_too_far_backwards57, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_1
		{
		IMethod methodno_jump_to_except_158;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_158 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_1", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_158, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_2
		{
		IMethod methodno_jump_to_except_259;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_259 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_2", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_259, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_3
		{
		IMethod methodno_jump_to_except_360;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_360 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_3", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_360, new String[] {"output"} );
		}
		//Function test:no_jump_to_except_4
		{
		IMethod methodno_jump_to_except_461;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_except_461 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_except_4", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_except_461, new String[] {"output"} );
		}
		//Function test:no_jump_forwards_into_block
		{
		IMethod methodno_jump_forwards_into_block62;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_forwards_into_block62 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_forwards_into_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_forwards_into_block62, new String[] {"output"} );
		}
		//Function test:no_jump_backwards_into_block
		{
		IMethod methodno_jump_backwards_into_block63;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_backwards_into_block63 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_backwards_into_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_backwards_into_block63, new String[] {"output"} );
		}
		//Function test:no_jump_into_finally_block
		{
		IMethod methodno_jump_into_finally_block64;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_into_finally_block64 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_into_finally_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_into_finally_block64, new String[] {"output"} );
		}
		//Function test:no_jump_out_of_finally_block
		{
		IMethod methodno_jump_out_of_finally_block65;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_out_of_finally_block65 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_out_of_finally_block", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_out_of_finally_block65, new String[] {"output"} );
		}
		//Function test:no_jump_to_non_integers
		{
		IMethod methodno_jump_to_non_integers66;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_to_non_integers66 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_to_non_integers", 1 );
			ModelTestUtils.assertParameterNames( methodno_jump_to_non_integers66, new String[] {"output"} );
		}
		//Function test:no_jump_without_trace_function
		{
		IMethod methodno_jump_without_trace_function67;
			IModelElement[] moduleChilds = module.getChildren();
			methodno_jump_without_trace_function67 = ModelTestUtils.getAssertMethod( moduleChilds, "no_jump_without_trace_function", 0 );
		}
		//Class test
		{
		IType classJumpTestCase68;
			IModelElement[] moduleChilds = module.getChildren();
			classJumpTestCase68 = ModelTestUtils.getAssertClass( moduleChilds, "JumpTestCase" );
			//Function test:compare_jump_output
			{
			IMethod methodcompare_jump_output69;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodcompare_jump_output69 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "compare_jump_output", 3 );
				ModelTestUtils.assertParameterNames( methodcompare_jump_output69, new String[] {"self", "expected", "received"} );
			}
			//Function test:run_test
			{
			IMethod methodrun_test70;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodrun_test70 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "run_test", 2 );
				ModelTestUtils.assertParameterNames( methodrun_test70, new String[] {"self", "func"} );
			}
			//Function test:test_01_jump_simple_forwards
			{
			IMethod methodtest_01_jump_simple_forwards71;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_01_jump_simple_forwards71 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_01_jump_simple_forwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_01_jump_simple_forwards71, new String[] {"self"} );
			}
			//Function test:test_02_jump_simple_backwards
			{
			IMethod methodtest_02_jump_simple_backwards72;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_02_jump_simple_backwards72 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_02_jump_simple_backwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_02_jump_simple_backwards72, new String[] {"self"} );
			}
			//Function test:test_03_jump_out_of_block_forwards
			{
			IMethod methodtest_03_jump_out_of_block_forwards73;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_03_jump_out_of_block_forwards73 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_03_jump_out_of_block_forwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_03_jump_out_of_block_forwards73, new String[] {"self"} );
			}
			//Function test:test_04_jump_out_of_block_backwards
			{
			IMethod methodtest_04_jump_out_of_block_backwards74;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_04_jump_out_of_block_backwards74 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_04_jump_out_of_block_backwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_04_jump_out_of_block_backwards74, new String[] {"self"} );
			}
			//Function test:test_05_jump_to_codeless_line
			{
			IMethod methodtest_05_jump_to_codeless_line75;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_05_jump_to_codeless_line75 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_05_jump_to_codeless_line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_05_jump_to_codeless_line75, new String[] {"self"} );
			}
			//Function test:test_06_jump_to_same_line
			{
			IMethod methodtest_06_jump_to_same_line76;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_06_jump_to_same_line76 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_06_jump_to_same_line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_06_jump_to_same_line76, new String[] {"self"} );
			}
			//Function test:test_07_jump_in_nested_finally
			{
			IMethod methodtest_07_jump_in_nested_finally77;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_07_jump_in_nested_finally77 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_07_jump_in_nested_finally", 1 );
				ModelTestUtils.assertParameterNames( methodtest_07_jump_in_nested_finally77, new String[] {"self"} );
			}
			//Function test:test_08_no_jump_too_far_forwards
			{
			IMethod methodtest_08_no_jump_too_far_forwards78;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_08_no_jump_too_far_forwards78 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_08_no_jump_too_far_forwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_08_no_jump_too_far_forwards78, new String[] {"self"} );
			}
			//Function test:test_09_no_jump_too_far_backwards
			{
			IMethod methodtest_09_no_jump_too_far_backwards79;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_09_no_jump_too_far_backwards79 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_09_no_jump_too_far_backwards", 1 );
				ModelTestUtils.assertParameterNames( methodtest_09_no_jump_too_far_backwards79, new String[] {"self"} );
			}
			//Function test:test_10_no_jump_to_except_1
			{
			IMethod methodtest_10_no_jump_to_except_180;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_10_no_jump_to_except_180 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_10_no_jump_to_except_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_10_no_jump_to_except_180, new String[] {"self"} );
			}
			//Function test:test_11_no_jump_to_except_2
			{
			IMethod methodtest_11_no_jump_to_except_281;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_11_no_jump_to_except_281 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_11_no_jump_to_except_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_11_no_jump_to_except_281, new String[] {"self"} );
			}
			//Function test:test_12_no_jump_to_except_3
			{
			IMethod methodtest_12_no_jump_to_except_382;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_12_no_jump_to_except_382 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_12_no_jump_to_except_3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_12_no_jump_to_except_382, new String[] {"self"} );
			}
			//Function test:test_13_no_jump_to_except_4
			{
			IMethod methodtest_13_no_jump_to_except_483;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_13_no_jump_to_except_483 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_13_no_jump_to_except_4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_13_no_jump_to_except_483, new String[] {"self"} );
			}
			//Function test:test_14_no_jump_forwards_into_block
			{
			IMethod methodtest_14_no_jump_forwards_into_block84;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_14_no_jump_forwards_into_block84 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_14_no_jump_forwards_into_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_14_no_jump_forwards_into_block84, new String[] {"self"} );
			}
			//Function test:test_15_no_jump_backwards_into_block
			{
			IMethod methodtest_15_no_jump_backwards_into_block85;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_15_no_jump_backwards_into_block85 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_15_no_jump_backwards_into_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_15_no_jump_backwards_into_block85, new String[] {"self"} );
			}
			//Function test:test_16_no_jump_into_finally_block
			{
			IMethod methodtest_16_no_jump_into_finally_block86;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_16_no_jump_into_finally_block86 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_16_no_jump_into_finally_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_16_no_jump_into_finally_block86, new String[] {"self"} );
			}
			//Function test:test_17_no_jump_out_of_finally_block
			{
			IMethod methodtest_17_no_jump_out_of_finally_block87;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_17_no_jump_out_of_finally_block87 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_17_no_jump_out_of_finally_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_17_no_jump_out_of_finally_block87, new String[] {"self"} );
			}
			//Function test:test_18_no_jump_to_non_integers
			{
			IMethod methodtest_18_no_jump_to_non_integers88;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_18_no_jump_to_non_integers88 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_18_no_jump_to_non_integers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_18_no_jump_to_non_integers88, new String[] {"self"} );
			}
			//Function test:test_19_no_jump_without_trace_function
			{
			IMethod methodtest_19_no_jump_without_trace_function89;
				IModelElement[] classJumpTestCase68Childs = classJumpTestCase68.getChildren();
				methodtest_19_no_jump_without_trace_function89 = ModelTestUtils.getAssertMethod( classJumpTestCase68Childs, "test_19_no_jump_without_trace_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_19_no_jump_without_trace_function89, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main90;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main90 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			//Function test:get_anchor_info
			{
			IMethod methodget_anchor_info2;
				IModelElement[] classAnchorCollector0Childs = classAnchorCollector0.getChildren();
				methodget_anchor_info2 = ModelTestUtils.getAssertMethod( classAnchorCollector0Childs, "get_anchor_info", 1 );
				ModelTestUtils.assertParameterNames( methodget_anchor_info2, new String[] {"self"} );
			}
			//Function test:anchor_bgn
			{
			IMethod methodanchor_bgn3;
				IModelElement[] classAnchorCollector0Childs = classAnchorCollector0.getChildren();
				methodanchor_bgn3 = ModelTestUtils.getAssertMethod( classAnchorCollector0Childs, "anchor_bgn", 2 );
				ModelTestUtils.assertParameterNames( methodanchor_bgn3, new String[] {"self", "args"} );
			}
		}
		//Class test
		{
		IType classDeclCollector4;
			IModelElement[] moduleChilds = module.getChildren();
			classDeclCollector4 = ModelTestUtils.getAssertClass( moduleChilds, "DeclCollector" );
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classDeclCollector4Childs = classDeclCollector4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classDeclCollector4Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "args", "kw"} );
			}
			//Function test:get_decl_info
			{
			IMethod methodget_decl_info6;
				IModelElement[] classDeclCollector4Childs = classDeclCollector4.getChildren();
				methodget_decl_info6 = ModelTestUtils.getAssertMethod( classDeclCollector4Childs, "get_decl_info", 1 );
				ModelTestUtils.assertParameterNames( methodget_decl_info6, new String[] {"self"} );
			}
			//Function test:unknown_decl
			{
			IMethod methodunknown_decl7;
				IModelElement[] classDeclCollector4Childs = classDeclCollector4.getChildren();
				methodunknown_decl7 = ModelTestUtils.getAssertMethod( classDeclCollector4Childs, "unknown_decl", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_decl7, new String[] {"self", "data"} );
			}
		}
		//Class test
		{
		IType classHTMLParserTestCase8;
			IModelElement[] moduleChilds = module.getChildren();
			classHTMLParserTestCase8 = ModelTestUtils.getAssertClass( moduleChilds, "HTMLParserTestCase" );
			//Function test:test_anchor_collection
			{
			IMethod methodtest_anchor_collection9;
				IModelElement[] classHTMLParserTestCase8Childs = classHTMLParserTestCase8.getChildren();
				methodtest_anchor_collection9 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase8Childs, "test_anchor_collection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anchor_collection9, new String[] {"self"} );
			}
			//Function test:test_decl_collection
			{
			IMethod methodtest_decl_collection10;
				IModelElement[] classHTMLParserTestCase8Childs = classHTMLParserTestCase8.getChildren();
				methodtest_decl_collection10 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase8Childs, "test_decl_collection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decl_collection10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			//Function test:fromstring
			{
			IMethod methodfromstring2;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodfromstring2 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "fromstring", 3 );
				ModelTestUtils.assertParameterNames( methodfromstring2, new String[] {"self", "string", "defaults"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic3;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_basic3 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic3, new String[] {"self"} );
			}
			//Function test:test_case_sensitivity
			{
			IMethod methodtest_case_sensitivity4;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_case_sensitivity4 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_case_sensitivity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_case_sensitivity4, new String[] {"self"} );
			}
			//Function test:test_default_case_sensitivity
			{
			IMethod methodtest_default_case_sensitivity5;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_default_case_sensitivity5 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_default_case_sensitivity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_case_sensitivity5, new String[] {"self"} );
			}
			//Function test:test_parse_errors
			{
			IMethod methodtest_parse_errors6;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_parse_errors6 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_parse_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parse_errors6, new String[] {"self"} );
			}
			//Function test:parse_error
			{
			IMethod methodparse_error7;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodparse_error7 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "parse_error", 3 );
				ModelTestUtils.assertParameterNames( methodparse_error7, new String[] {"self", "exc", "src"} );
			}
			//Function test:test_query_errors
			{
			IMethod methodtest_query_errors8;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_query_errors8 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_query_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_query_errors8, new String[] {"self"} );
			}
			//Function test:get_error
			{
			IMethod methodget_error9;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodget_error9 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "get_error", 4 );
				ModelTestUtils.assertParameterNames( methodget_error9, new String[] {"self", "exc", "section", "option"} );
			}
			//Function test:test_boolean
			{
			IMethod methodtest_boolean10;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_boolean10 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_boolean", 1 );
				ModelTestUtils.assertParameterNames( methodtest_boolean10, new String[] {"self"} );
			}
			//Function test:test_weird_errors
			{
			IMethod methodtest_weird_errors11;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_weird_errors11 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_weird_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weird_errors11, new String[] {"self"} );
			}
			//Function test:test_write
			{
			IMethod methodtest_write12;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_write12 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write12, new String[] {"self"} );
			}
			//Function test:test_set_string_types
			{
			IMethod methodtest_set_string_types13;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_set_string_types13 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_set_string_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_string_types13, new String[] {"self"} );
				//Class test
				{
				IType classmystr14;
					IModelElement[] methodtest_set_string_types13Childs = methodtest_set_string_types13.getChildren();
					classmystr14 = ModelTestUtils.getAssertClass( methodtest_set_string_types13Childs, "mystr" );
				}
			}
			//Function test:test_read_returns_file_list
			{
			IMethod methodtest_read_returns_file_list15;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodtest_read_returns_file_list15 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "test_read_returns_file_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_returns_file_list15, new String[] {"self"} );
			}
			//Function test:get_interpolation_config
			{
			IMethod methodget_interpolation_config16;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodget_interpolation_config16 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "get_interpolation_config", 1 );
				ModelTestUtils.assertParameterNames( methodget_interpolation_config16, new String[] {"self"} );
			}
			//Function test:check_items_config
			{
			IMethod methodcheck_items_config17;
				IModelElement[] classTestCaseBase0Childs = classTestCaseBase0.getChildren();
				methodcheck_items_config17 = ModelTestUtils.getAssertMethod( classTestCaseBase0Childs, "check_items_config", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_items_config17, new String[] {"self", "expected"} );
			}
		}
		//Class test
		{
		IType classConfigParserTestCase18;
			IModelElement[] moduleChilds = module.getChildren();
			classConfigParserTestCase18 = ModelTestUtils.getAssertClass( moduleChilds, "ConfigParserTestCase" );
			{
				IModelElement[] classConfigParserTestCase18Childs = classConfigParserTestCase18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classConfigParserTestCase18Childs, "config_class");
			}
			//Function test:test_interpolation
			{
			IMethod methodtest_interpolation19;
				IModelElement[] classConfigParserTestCase18Childs = classConfigParserTestCase18.getChildren();
				methodtest_interpolation19 = ModelTestUtils.getAssertMethod( classConfigParserTestCase18Childs, "test_interpolation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interpolation19, new String[] {"self"} );
			}
			//Function test:test_interpolation_missing_value
			{
			IMethod methodtest_interpolation_missing_value20;
				IModelElement[] classConfigParserTestCase18Childs = classConfigParserTestCase18.getChildren();
				methodtest_interpolation_missing_value20 = ModelTestUtils.getAssertMethod( classConfigParserTestCase18Childs, "test_interpolation_missing_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interpolation_missing_value20, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items21;
				IModelElement[] classConfigParserTestCase18Childs = classConfigParserTestCase18.getChildren();
				methodtest_items21 = ModelTestUtils.getAssertMethod( classConfigParserTestCase18Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items21, new String[] {"self"} );
			}
			//Function test:test_set_nonstring_types
			{
			IMethod methodtest_set_nonstring_types22;
				IModelElement[] classConfigParserTestCase18Childs = classConfigParserTestCase18.getChildren();
				methodtest_set_nonstring_types22 = ModelTestUtils.getAssertMethod( classConfigParserTestCase18Childs, "test_set_nonstring_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_nonstring_types22, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRawConfigParserTestCase23;
			IModelElement[] moduleChilds = module.getChildren();
			classRawConfigParserTestCase23 = ModelTestUtils.getAssertClass( moduleChilds, "RawConfigParserTestCase" );
			{
				IModelElement[] classRawConfigParserTestCase23Childs = classRawConfigParserTestCase23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRawConfigParserTestCase23Childs, "config_class");
			}
			//Function test:test_interpolation
			{
			IMethod methodtest_interpolation24;
				IModelElement[] classRawConfigParserTestCase23Childs = classRawConfigParserTestCase23.getChildren();
				methodtest_interpolation24 = ModelTestUtils.getAssertMethod( classRawConfigParserTestCase23Childs, "test_interpolation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interpolation24, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items25;
				IModelElement[] classRawConfigParserTestCase23Childs = classRawConfigParserTestCase23.getChildren();
				methodtest_items25 = ModelTestUtils.getAssertMethod( classRawConfigParserTestCase23Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items25, new String[] {"self"} );
			}
			//Function test:test_set_nonstring_types
			{
			IMethod methodtest_set_nonstring_types26;
				IModelElement[] classRawConfigParserTestCase23Childs = classRawConfigParserTestCase23.getChildren();
				methodtest_set_nonstring_types26 = ModelTestUtils.getAssertMethod( classRawConfigParserTestCase23Childs, "test_set_nonstring_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_nonstring_types26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSafeConfigParserTestCase27;
			IModelElement[] moduleChilds = module.getChildren();
			classSafeConfigParserTestCase27 = ModelTestUtils.getAssertClass( moduleChilds, "SafeConfigParserTestCase" );
			{
				IModelElement[] classSafeConfigParserTestCase27Childs = classSafeConfigParserTestCase27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSafeConfigParserTestCase27Childs, "config_class");
			}
			//Function test:test_safe_interpolation
			{
			IMethod methodtest_safe_interpolation28;
				IModelElement[] classSafeConfigParserTestCase27Childs = classSafeConfigParserTestCase27.getChildren();
				methodtest_safe_interpolation28 = ModelTestUtils.getAssertMethod( classSafeConfigParserTestCase27Childs, "test_safe_interpolation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_safe_interpolation28, new String[] {"self"} );
			}
			//Function test:test_set_nonstring_types
			{
			IMethod methodtest_set_nonstring_types29;
				IModelElement[] classSafeConfigParserTestCase27Childs = classSafeConfigParserTestCase27.getChildren();
				methodtest_set_nonstring_types29 = ModelTestUtils.getAssertMethod( classSafeConfigParserTestCase27Childs, "test_set_nonstring_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_nonstring_types29, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}

}
	