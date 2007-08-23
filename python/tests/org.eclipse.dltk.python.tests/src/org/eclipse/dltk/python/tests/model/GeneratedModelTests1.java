
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

public class GeneratedModelTests1 extends AbstractModelTests
{
	public GeneratedModelTests1(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests1.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_1", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests1" );
	}
	public void testModelGen50( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecmaps_cn.py"));

		assertNotNull("Module test_codecmaps_cn.py not found", module);
		assertEquals("test_codecmaps_cn.py", module.getElementName());
		
		//Class test
		{
		IType classTestGB2312Map0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestGB2312Map0 = ModelTestUtils.getAssertClass( moduleChilds, "TestGB2312Map" );
			{
				IModelElement[] classTestGB2312Map0Childs = classTestGB2312Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGB2312Map0Childs, "encoding");
			}
			{
				IModelElement[] classTestGB2312Map0Childs = classTestGB2312Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGB2312Map0Childs, "mapfilename");
			}
			{
				IModelElement[] classTestGB2312Map0Childs = classTestGB2312Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGB2312Map0Childs, "mapfileurl");
			}
		}
		//Class test
		{
		IType classTestGBKMap1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestGBKMap1 = ModelTestUtils.getAssertClass( moduleChilds, "TestGBKMap" );
			{
				IModelElement[] classTestGBKMap1Childs = classTestGBKMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGBKMap1Childs, "encoding");
			}
			{
				IModelElement[] classTestGBKMap1Childs = classTestGBKMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGBKMap1Childs, "mapfilename");
			}
			{
				IModelElement[] classTestGBKMap1Childs = classTestGBKMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGBKMap1Childs, "mapfileurl");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen51( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_whichdb.py"));

		assertNotNull("Module test_whichdb.py not found", module);
		assertEquals("test_whichdb.py", module.getElementName());
		
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
		IType classWhichDBTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classWhichDBTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "WhichDBTestCase" );
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classWhichDBTestCase1Childs = classWhichDBTestCase1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classWhichDBTestCase1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "args"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classWhichDBTestCase1Childs = classWhichDBTestCase1.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classWhichDBTestCase1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp4;
				IModelElement[] classWhichDBTestCase1Childs = classWhichDBTestCase1.getChildren();
				methodsetUp4 = ModelTestUtils.getAssertMethod( classWhichDBTestCase1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp4, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mod");
		}
		//Function test:test_whichdb_name
		{
		IMethod methodtest_whichdb_name5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_whichdb_name5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_whichdb_name", 3 );
			ModelTestUtils.assertParameterNames( methodtest_whichdb_name5, new String[] {"self", "name", "mod"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen52( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_unicode.py"));

		assertNotNull("Module test_unicode.py not found", module);
		assertEquals("test_unicode.py", module.getElementName());
		
		//Class test
		{
		IType classUnicodeTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeTest" );
			{
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest0Childs, "type2test");
			}
			//Function test:checkequalnofix
			{
			IMethod methodcheckequalnofix1;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodcheckequalnofix1 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "checkequalnofix", 5 );
				ModelTestUtils.assertParameterNames( methodcheckequalnofix1, new String[] {"self", "result", "object", "methodname", "args"} );
				//Class test
				{
				IType classusub2;
					IModelElement[] methodcheckequalnofix1Childs = methodcheckequalnofix1.getChildren();
					classusub2 = ModelTestUtils.getAssertClass( methodcheckequalnofix1Childs, "usub" );
					//Function test:__repr__
					{
					IMethod method__repr__3;
						IModelElement[] classusub2Childs = classusub2.getChildren();
						method__repr__3 = ModelTestUtils.getAssertMethod( classusub2Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__3, new String[] {"self"} );
					}
				}
			}
			//Function test:test_literals
			{
			IMethod methodtest_literals4;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_literals4 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_literals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_literals4, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr5;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_repr5 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr5, new String[] {"self"} );
			}
			//Function test:test_count
			{
			IMethod methodtest_count6;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_count6 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_count", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count6, new String[] {"self"} );
			}
			//Function test:test_find
			{
			IMethod methodtest_find7;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_find7 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_find", 1 );
				ModelTestUtils.assertParameterNames( methodtest_find7, new String[] {"self"} );
			}
			//Function test:test_rfind
			{
			IMethod methodtest_rfind8;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_rfind8 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_rfind", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rfind8, new String[] {"self"} );
			}
			//Function test:test_index
			{
			IMethod methodtest_index9;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_index9 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_index", 1 );
				ModelTestUtils.assertParameterNames( methodtest_index9, new String[] {"self"} );
			}
			//Function test:test_rindex
			{
			IMethod methodtest_rindex10;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_rindex10 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_rindex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rindex10, new String[] {"self"} );
			}
			//Function test:test_translate
			{
			IMethod methodtest_translate11;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_translate11 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_translate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_translate11, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split12;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_split12 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split12, new String[] {"self"} );
			}
			//Function test:test_join
			{
			IMethod methodtest_join13;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_join13 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_join", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join13, new String[] {"self"} );
			}
			//Function test:test_strip
			{
			IMethod methodtest_strip14;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_strip14 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_strip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strip14, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace15;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_replace15 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace15, new String[] {"self"} );
			}
			//Function test:test_comparison
			{
			IMethod methodtest_comparison16;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_comparison16 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_comparison", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparison16, new String[] {"self"} );
				//Function test:test_lecmp
				{
				IMethod methodtest_lecmp17;
					IModelElement[] methodtest_comparison16Childs = methodtest_comparison16.getChildren();
					methodtest_lecmp17 = ModelTestUtils.getAssertMethod( methodtest_comparison16Childs, "test_lecmp", 2 );
					ModelTestUtils.assertParameterNames( methodtest_lecmp17, new String[] {"s", "s2"} );
				}
				//Function test:test_fixup
				{
				IMethod methodtest_fixup18;
					IModelElement[] methodtest_comparison16Childs = methodtest_comparison16.getChildren();
					methodtest_fixup18 = ModelTestUtils.getAssertMethod( methodtest_comparison16Childs, "test_fixup", 1 );
					ModelTestUtils.assertParameterNames( methodtest_fixup18, new String[] {"s"} );
				}
			}
			//Function test:test_islower
			{
			IMethod methodtest_islower19;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_islower19 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_islower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islower19, new String[] {"self"} );
			}
			//Function test:test_isupper
			{
			IMethod methodtest_isupper20;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_isupper20 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_isupper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isupper20, new String[] {"self"} );
			}
			//Function test:test_istitle
			{
			IMethod methodtest_istitle21;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_istitle21 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_istitle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_istitle21, new String[] {"self"} );
			}
			//Function test:test_isspace
			{
			IMethod methodtest_isspace22;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_isspace22 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_isspace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isspace22, new String[] {"self"} );
			}
			//Function test:test_isalpha
			{
			IMethod methodtest_isalpha23;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_isalpha23 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_isalpha", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isalpha23, new String[] {"self"} );
			}
			//Function test:test_isdecimal
			{
			IMethod methodtest_isdecimal24;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_isdecimal24 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_isdecimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isdecimal24, new String[] {"self"} );
			}
			//Function test:test_isdigit
			{
			IMethod methodtest_isdigit25;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_isdigit25 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_isdigit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isdigit25, new String[] {"self"} );
			}
			//Function test:test_isnumeric
			{
			IMethod methodtest_isnumeric26;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_isnumeric26 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_isnumeric", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isnumeric26, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains27;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_contains27 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains27, new String[] {"self"} );
			}
			//Function test:test_formatting
			{
			IMethod methodtest_formatting28;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_formatting28 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_formatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_formatting28, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor29;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_constructor29 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor29, new String[] {"self"} );
				//Class test
				{
				IType classUnicodeSubclass30;
					IModelElement[] methodtest_constructor29Childs = methodtest_constructor29.getChildren();
					classUnicodeSubclass30 = ModelTestUtils.getAssertClass( methodtest_constructor29Childs, "UnicodeSubclass" );
				}
				//Class test
				{
				IType classUnicodeCompat31;
					IModelElement[] methodtest_constructor29Childs = methodtest_constructor29.getChildren();
					classUnicodeCompat31 = ModelTestUtils.getAssertClass( methodtest_constructor29Childs, "UnicodeCompat" );
					//Function test:__init__
					{
					IMethod method__init__32;
						IModelElement[] classUnicodeCompat31Childs = classUnicodeCompat31.getChildren();
						method__init__32 = ModelTestUtils.getAssertMethod( classUnicodeCompat31Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__32, new String[] {"self", "x"} );
					}
					//Function test:__unicode__
					{
					IMethod method__unicode__33;
						IModelElement[] classUnicodeCompat31Childs = classUnicodeCompat31.getChildren();
						method__unicode__33 = ModelTestUtils.getAssertMethod( classUnicodeCompat31Childs, "__unicode__", 1 );
						ModelTestUtils.assertParameterNames( method__unicode__33, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classStringCompat34;
					IModelElement[] methodtest_constructor29Childs = methodtest_constructor29.getChildren();
					classStringCompat34 = ModelTestUtils.getAssertClass( methodtest_constructor29Childs, "StringCompat" );
					//Function test:__init__
					{
					IMethod method__init__35;
						IModelElement[] classStringCompat34Childs = classStringCompat34.getChildren();
						method__init__35 = ModelTestUtils.getAssertMethod( classStringCompat34Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__35, new String[] {"self", "x"} );
					}
					//Function test:__str__
					{
					IMethod method__str__36;
						IModelElement[] classStringCompat34Childs = classStringCompat34.getChildren();
						method__str__36 = ModelTestUtils.getAssertMethod( classStringCompat34Childs, "__str__", 1 );
						ModelTestUtils.assertParameterNames( method__str__36, new String[] {"self"} );
					}
				}
			}
			//Function test:test_codecs_utf7
			{
			IMethod methodtest_codecs_utf737;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_utf737 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_utf7", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_utf737, new String[] {"self"} );
			}
			//Function test:test_codecs_utf8
			{
			IMethod methodtest_codecs_utf838;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_utf838 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_utf8", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_utf838, new String[] {"self"} );
			}
			//Function test:test_codecs_idna
			{
			IMethod methodtest_codecs_idna39;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_idna39 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_idna", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_idna39, new String[] {"self"} );
			}
			//Function test:test_codecs_errors
			{
			IMethod methodtest_codecs_errors40;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_errors40 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_errors40, new String[] {"self"} );
				//Function test:search_function
				{
				IMethod methodsearch_function41;
					IModelElement[] methodtest_codecs_errors40Childs = methodtest_codecs_errors40.getChildren();
					methodsearch_function41 = ModelTestUtils.getAssertMethod( methodtest_codecs_errors40Childs, "search_function", 1 );
					ModelTestUtils.assertParameterNames( methodsearch_function41, new String[] {"encoding"} );
					//Function test:decode1
					{
					IMethod methoddecode142;
						IModelElement[] methodsearch_function41Childs = methodsearch_function41.getChildren();
						methoddecode142 = ModelTestUtils.getAssertMethod( methodsearch_function41Childs, "decode1", 2 );
						ModelTestUtils.assertParameterNames( methoddecode142, new String[] {"input", "errors"} );
					}
					//Function test:encode1
					{
					IMethod methodencode143;
						IModelElement[] methodsearch_function41Childs = methodsearch_function41.getChildren();
						methodencode143 = ModelTestUtils.getAssertMethod( methodsearch_function41Childs, "encode1", 2 );
						ModelTestUtils.assertParameterNames( methodencode143, new String[] {"input", "errors"} );
					}
					//Function test:encode2
					{
					IMethod methodencode244;
						IModelElement[] methodsearch_function41Childs = methodsearch_function41.getChildren();
						methodencode244 = ModelTestUtils.getAssertMethod( methodsearch_function41Childs, "encode2", 2 );
						ModelTestUtils.assertParameterNames( methodencode244, new String[] {"input", "errors"} );
					}
					//Function test:decode2
					{
					IMethod methoddecode245;
						IModelElement[] methodsearch_function41Childs = methodsearch_function41.getChildren();
						methoddecode245 = ModelTestUtils.getAssertMethod( methodsearch_function41Childs, "decode2", 2 );
						ModelTestUtils.assertParameterNames( methoddecode245, new String[] {"input", "errors"} );
					}
				}
			}
			//Function test:test_codecs
			{
			IMethod methodtest_codecs46;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs46 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs46, new String[] {"self"} );
			}
			//Function test:test_codecs_charmap
			{
			IMethod methodtest_codecs_charmap47;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_charmap47 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_charmap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_charmap47, new String[] {"self"} );
			}
			//Function test:test_concatenation
			{
			IMethod methodtest_concatenation48;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_concatenation48 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_concatenation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_concatenation48, new String[] {"self"} );
			}
			//Function test:test_printing
			{
			IMethod methodtest_printing49;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_printing49 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_printing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_printing49, new String[] {"self"} );
				//Class test
				{
				IType classBitBucket50;
					IModelElement[] methodtest_printing49Childs = methodtest_printing49.getChildren();
					classBitBucket50 = ModelTestUtils.getAssertClass( methodtest_printing49Childs, "BitBucket" );
					//Function test:write
					{
					IMethod methodwrite51;
						IModelElement[] classBitBucket50Childs = classBitBucket50.getChildren();
						methodwrite51 = ModelTestUtils.getAssertMethod( classBitBucket50Childs, "write", 2 );
						ModelTestUtils.assertParameterNames( methodwrite51, new String[] {"self", "text"} );
					}
				}
			}
			//Function test:test_ucs4
			{
			IMethod methodtest_ucs452;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_ucs452 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_ucs4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ucs452, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main53;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main53 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen53( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecmaps_hk.py"));

		assertNotNull("Module test_codecmaps_hk.py not found", module);
		assertEquals("test_codecmaps_hk.py", module.getElementName());
		
		//Class test
		{
		IType classTestBig5HKSCSMap0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBig5HKSCSMap0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBig5HKSCSMap" );
			{
				IModelElement[] classTestBig5HKSCSMap0Childs = classTestBig5HKSCSMap0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig5HKSCSMap0Childs, "encoding");
			}
			{
				IModelElement[] classTestBig5HKSCSMap0Childs = classTestBig5HKSCSMap0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig5HKSCSMap0Childs, "mapfilename");
			}
			{
				IModelElement[] classTestBig5HKSCSMap0Childs = classTestBig5HKSCSMap0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig5HKSCSMap0Childs, "mapfileurl");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen54( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("sortperf.py"));

		assertNotNull("Module sortperf.py not found", module);
		assertEquals("sortperf.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "td");
		}
		//Function test:randfloats
		{
		IMethod methodrandfloats0;
			IModelElement[] moduleChilds = module.getChildren();
			methodrandfloats0 = ModelTestUtils.getAssertMethod( moduleChilds, "randfloats", 1 );
			ModelTestUtils.assertParameterNames( methodrandfloats0, new String[] {"n"} );
		}
		//Function test:flush
		{
		IMethod methodflush1;
			IModelElement[] moduleChilds = module.getChildren();
			methodflush1 = ModelTestUtils.getAssertMethod( moduleChilds, "flush", 0 );
		}
		//Function test:doit
		{
		IMethod methoddoit2;
			IModelElement[] moduleChilds = module.getChildren();
			methoddoit2 = ModelTestUtils.getAssertMethod( moduleChilds, "doit", 1 );
			ModelTestUtils.assertParameterNames( methoddoit2, new String[] {"L"} );
		}
		//Function test:tabulate
		{
		IMethod methodtabulate3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtabulate3 = ModelTestUtils.getAssertMethod( moduleChilds, "tabulate", 1 );
			ModelTestUtils.assertParameterNames( methodtabulate3, new String[] {"r"} );
		}
		//Function test:main
		{
		IMethod methodmain4;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain4 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen55( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_resource.py"));

		assertNotNull("Module test_resource.py not found", module);
		assertEquals("test_resource.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "limit_set");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "limit_set");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "too_big");
		}

	}
	public void testModelGen56( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecmaps_jp.py"));

		assertNotNull("Module test_codecmaps_jp.py not found", module);
		assertEquals("test_codecmaps_jp.py", module.getElementName());
		
		//Class test
		{
		IType classTestCP932Map0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCP932Map0 = ModelTestUtils.getAssertClass( moduleChilds, "TestCP932Map" );
			{
				IModelElement[] classTestCP932Map0Childs = classTestCP932Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP932Map0Childs, "encoding");
			}
			{
				IModelElement[] classTestCP932Map0Childs = classTestCP932Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP932Map0Childs, "mapfilename");
			}
			{
				IModelElement[] classTestCP932Map0Childs = classTestCP932Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP932Map0Childs, "mapfileurl");
			}
			{
				IModelElement[] classTestCP932Map0Childs = classTestCP932Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP932Map0Childs, "supmaps");
			}
		}
		//Class test
		{
		IType classTestEUCJPCOMPATMap1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEUCJPCOMPATMap1 = ModelTestUtils.getAssertClass( moduleChilds, "TestEUCJPCOMPATMap" );
			{
				IModelElement[] classTestEUCJPCOMPATMap1Childs = classTestEUCJPCOMPATMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCJPCOMPATMap1Childs, "encoding");
			}
			{
				IModelElement[] classTestEUCJPCOMPATMap1Childs = classTestEUCJPCOMPATMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCJPCOMPATMap1Childs, "mapfilename");
			}
			{
				IModelElement[] classTestEUCJPCOMPATMap1Childs = classTestEUCJPCOMPATMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCJPCOMPATMap1Childs, "mapfileurl");
			}
		}
		//Class test
		{
		IType classTestSJISCOMPATMap2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSJISCOMPATMap2 = ModelTestUtils.getAssertClass( moduleChilds, "TestSJISCOMPATMap" );
			{
				IModelElement[] classTestSJISCOMPATMap2Childs = classTestSJISCOMPATMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISCOMPATMap2Childs, "encoding");
			}
			{
				IModelElement[] classTestSJISCOMPATMap2Childs = classTestSJISCOMPATMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISCOMPATMap2Childs, "mapfilename");
			}
			{
				IModelElement[] classTestSJISCOMPATMap2Childs = classTestSJISCOMPATMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISCOMPATMap2Childs, "mapfileurl");
			}
			{
				IModelElement[] classTestSJISCOMPATMap2Childs = classTestSJISCOMPATMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISCOMPATMap2Childs, "pass_enctest");
			}
			{
				IModelElement[] classTestSJISCOMPATMap2Childs = classTestSJISCOMPATMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISCOMPATMap2Childs, "pass_dectest");
			}
		}
		//Class test
		{
		IType classTestEUCJISX0213Map3;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEUCJISX0213Map3 = ModelTestUtils.getAssertClass( moduleChilds, "TestEUCJISX0213Map" );
			{
				IModelElement[] classTestEUCJISX0213Map3Childs = classTestEUCJISX0213Map3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCJISX0213Map3Childs, "encoding");
			}
			{
				IModelElement[] classTestEUCJISX0213Map3Childs = classTestEUCJISX0213Map3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCJISX0213Map3Childs, "mapfilename");
			}
			{
				IModelElement[] classTestEUCJISX0213Map3Childs = classTestEUCJISX0213Map3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCJISX0213Map3Childs, "mapfileurl");
			}
		}
		//Class test
		{
		IType classTestSJISX0213Map4;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSJISX0213Map4 = ModelTestUtils.getAssertClass( moduleChilds, "TestSJISX0213Map" );
			{
				IModelElement[] classTestSJISX0213Map4Childs = classTestSJISX0213Map4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISX0213Map4Childs, "encoding");
			}
			{
				IModelElement[] classTestSJISX0213Map4Childs = classTestSJISX0213Map4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISX0213Map4Childs, "mapfilename");
			}
			{
				IModelElement[] classTestSJISX0213Map4Childs = classTestSJISX0213Map4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSJISX0213Map4Childs, "mapfileurl");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen57( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecmaps_kr.py"));

		assertNotNull("Module test_codecmaps_kr.py not found", module);
		assertEquals("test_codecmaps_kr.py", module.getElementName());
		
		//Class test
		{
		IType classTestCP949Map0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCP949Map0 = ModelTestUtils.getAssertClass( moduleChilds, "TestCP949Map" );
			{
				IModelElement[] classTestCP949Map0Childs = classTestCP949Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP949Map0Childs, "encoding");
			}
			{
				IModelElement[] classTestCP949Map0Childs = classTestCP949Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP949Map0Childs, "mapfilename");
			}
			{
				IModelElement[] classTestCP949Map0Childs = classTestCP949Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP949Map0Childs, "mapfileurl");
			}
		}
		//Class test
		{
		IType classTestEUCKRMap1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEUCKRMap1 = ModelTestUtils.getAssertClass( moduleChilds, "TestEUCKRMap" );
			{
				IModelElement[] classTestEUCKRMap1Childs = classTestEUCKRMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCKRMap1Childs, "encoding");
			}
			{
				IModelElement[] classTestEUCKRMap1Childs = classTestEUCKRMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCKRMap1Childs, "mapfilename");
			}
			{
				IModelElement[] classTestEUCKRMap1Childs = classTestEUCKRMap1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEUCKRMap1Childs, "mapfileurl");
			}
		}
		//Class test
		{
		IType classTestJOHABMap2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestJOHABMap2 = ModelTestUtils.getAssertClass( moduleChilds, "TestJOHABMap" );
			{
				IModelElement[] classTestJOHABMap2Childs = classTestJOHABMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJOHABMap2Childs, "encoding");
			}
			{
				IModelElement[] classTestJOHABMap2Childs = classTestJOHABMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJOHABMap2Childs, "mapfilename");
			}
			{
				IModelElement[] classTestJOHABMap2Childs = classTestJOHABMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJOHABMap2Childs, "mapfileurl");
			}
			{
				IModelElement[] classTestJOHABMap2Childs = classTestJOHABMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJOHABMap2Childs, "pass_enctest");
			}
			{
				IModelElement[] classTestJOHABMap2Childs = classTestJOHABMap2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestJOHABMap2Childs, "pass_dectest");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen58( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_threading.py"));

		assertNotNull("Module test_threading.py not found", module);
		assertEquals("test_threading.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "numtasks");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sema");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mutex");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "running");
		}
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
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "threads");
		}
		//Function test:starttasks
		{
		IMethod methodstarttasks2;
			IModelElement[] moduleChilds = module.getChildren();
			methodstarttasks2 = ModelTestUtils.getAssertMethod( moduleChilds, "starttasks", 0 );
		}

	}
	public void testModelGen59( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_quopri.py"));

		assertNotNull("Module test_quopri.py not found", module);
		assertEquals("test_quopri.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ENCSAMPLE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DECSAMPLE");
		}
		//Class test
		{
		IType classQuopriTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classQuopriTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "QuopriTestCase" );
			{
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQuopriTestCase0Childs, "STRINGS");
			}
			{
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQuopriTestCase0Childs, "ESTRINGS");
			}
			{
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQuopriTestCase0Childs, "HSTRINGS");
			}
			//Function test:test_encodestring
			{
			IMethod methodtest_encodestring1;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_encodestring1 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_encodestring", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encodestring1, new String[] {"self"} );
			}
			//Function test:test_decodestring
			{
			IMethod methodtest_decodestring2;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_decodestring2 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_decodestring", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodestring2, new String[] {"self"} );
			}
			//Function test:test_idempotent_string
			{
			IMethod methodtest_idempotent_string3;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_idempotent_string3 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_idempotent_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_idempotent_string3, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode4;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_encode4 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode4, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode5;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_decode5 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode5, new String[] {"self"} );
			}
			//Function test:test_embedded_ws
			{
			IMethod methodtest_embedded_ws6;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_embedded_ws6 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_embedded_ws", 1 );
				ModelTestUtils.assertParameterNames( methodtest_embedded_ws6, new String[] {"self"} );
			}
			//Function test:test_encode_header
			{
			IMethod methodtest_encode_header7;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_encode_header7 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_encode_header", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode_header7, new String[] {"self"} );
			}
			//Function test:test_decode_header
			{
			IMethod methodtest_decode_header8;
				IModelElement[] classQuopriTestCase0Childs = classQuopriTestCase0.getChildren();
				methodtest_decode_header8 = ModelTestUtils.getAssertMethod( classQuopriTestCase0Childs, "test_decode_header", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode_header8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen60( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_applesingle.py"));

		assertNotNull("Module test_applesingle.py not found", module);
		assertEquals("test_applesingle.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "AS_MAGIC");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "AS_VERSION");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dataforkdata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "resourceforkdata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "applesingledata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN2");
		}
		//Class test
		{
		IType classTestApplesingle0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestApplesingle0 = ModelTestUtils.getAssertClass( moduleChilds, "TestApplesingle" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestApplesingle0Childs = classTestApplesingle0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestApplesingle0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classTestApplesingle0Childs = classTestApplesingle0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classTestApplesingle0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:compareData
			{
			IMethod methodcompareData3;
				IModelElement[] classTestApplesingle0Childs = classTestApplesingle0.getChildren();
				methodcompareData3 = ModelTestUtils.getAssertMethod( classTestApplesingle0Childs, "compareData", 3 );
				ModelTestUtils.assertParameterNames( methodcompareData3, new String[] {"self", "isrf", "data"} );
			}
			//Function test:test_applesingle
			{
			IMethod methodtest_applesingle4;
				IModelElement[] classTestApplesingle0Childs = classTestApplesingle0.getChildren();
				methodtest_applesingle4 = ModelTestUtils.getAssertMethod( classTestApplesingle0Childs, "test_applesingle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_applesingle4, new String[] {"self"} );
			}
			//Function test:test_applesingle_resonly
			{
			IMethod methodtest_applesingle_resonly5;
				IModelElement[] classTestApplesingle0Childs = classTestApplesingle0.getChildren();
				methodtest_applesingle_resonly5 = ModelTestUtils.getAssertMethod( classTestApplesingle0Childs, "test_applesingle_resonly", 1 );
				ModelTestUtils.assertParameterNames( methodtest_applesingle_resonly5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen61( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sets.py"));

		assertNotNull("Module test_sets.py not found", module);
		assertEquals("test_sets.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "empty_set");
		}
		//Class test
		{
		IType classTestBasicOps0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOps0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOps" );
			//Function test:test_repr
			{
			IMethod methodtest_repr1;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_repr1 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr1, new String[] {"self"} );
			}
			//Function test:test_length
			{
			IMethod methodtest_length2;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_length2 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_length", 1 );
				ModelTestUtils.assertParameterNames( methodtest_length2, new String[] {"self"} );
			}
			//Function test:test_self_equality
			{
			IMethod methodtest_self_equality3;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_self_equality3 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_self_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_equality3, new String[] {"self"} );
			}
			//Function test:test_equivalent_equality
			{
			IMethod methodtest_equivalent_equality4;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_equivalent_equality4 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_equivalent_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equivalent_equality4, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy5;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_copy5 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy5, new String[] {"self"} );
			}
			//Function test:test_self_union
			{
			IMethod methodtest_self_union6;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_self_union6 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_self_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_union6, new String[] {"self"} );
			}
			//Function test:test_empty_union
			{
			IMethod methodtest_empty_union7;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_empty_union7 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_empty_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_union7, new String[] {"self"} );
			}
			//Function test:test_union_empty
			{
			IMethod methodtest_union_empty8;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_union_empty8 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_union_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_empty8, new String[] {"self"} );
			}
			//Function test:test_self_intersection
			{
			IMethod methodtest_self_intersection9;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_self_intersection9 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_self_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_intersection9, new String[] {"self"} );
			}
			//Function test:test_empty_intersection
			{
			IMethod methodtest_empty_intersection10;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_empty_intersection10 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_empty_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_intersection10, new String[] {"self"} );
			}
			//Function test:test_intersection_empty
			{
			IMethod methodtest_intersection_empty11;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_intersection_empty11 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_intersection_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_empty11, new String[] {"self"} );
			}
			//Function test:test_self_symmetric_difference
			{
			IMethod methodtest_self_symmetric_difference12;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_self_symmetric_difference12 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_self_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_symmetric_difference12, new String[] {"self"} );
			}
			//Function test:checkempty_symmetric_difference
			{
			IMethod methodcheckempty_symmetric_difference13;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodcheckempty_symmetric_difference13 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "checkempty_symmetric_difference", 1 );
				ModelTestUtils.assertParameterNames( methodcheckempty_symmetric_difference13, new String[] {"self"} );
			}
			//Function test:test_self_difference
			{
			IMethod methodtest_self_difference14;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_self_difference14 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_self_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_self_difference14, new String[] {"self"} );
			}
			//Function test:test_empty_difference
			{
			IMethod methodtest_empty_difference15;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_empty_difference15 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_empty_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_difference15, new String[] {"self"} );
			}
			//Function test:test_empty_difference_rev
			{
			IMethod methodtest_empty_difference_rev16;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_empty_difference_rev16 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_empty_difference_rev", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_difference_rev16, new String[] {"self"} );
			}
			//Function test:test_iteration
			{
			IMethod methodtest_iteration17;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_iteration17 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_iteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteration17, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling18;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_pickling18 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsEmpty19;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsEmpty19 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp20;
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				methodsetUp20 = ModelTestUtils.getAssertMethod( classTestBasicOpsEmpty19Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp20, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsSingleton21;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsSingleton21 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp22;
				IModelElement[] classTestBasicOpsSingleton21Childs = classTestBasicOpsSingleton21.getChildren();
				methodsetUp22 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton21Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp22, new String[] {"self"} );
			}
			//Function test:test_in
			{
			IMethod methodtest_in23;
				IModelElement[] classTestBasicOpsSingleton21Childs = classTestBasicOpsSingleton21.getChildren();
				methodtest_in23 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton21Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in23, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in24;
				IModelElement[] classTestBasicOpsSingleton21Childs = classTestBasicOpsSingleton21.getChildren();
				methodtest_not_in24 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton21Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in24, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTuple25;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTuple25 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp26;
				IModelElement[] classTestBasicOpsTuple25Childs = classTestBasicOpsTuple25.getChildren();
				methodsetUp26 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple25Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp26, new String[] {"self"} );
			}
			//Function test:test_in
			{
			IMethod methodtest_in27;
				IModelElement[] classTestBasicOpsTuple25Childs = classTestBasicOpsTuple25.getChildren();
				methodtest_in27 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple25Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in27, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in28;
				IModelElement[] classTestBasicOpsTuple25Childs = classTestBasicOpsTuple25.getChildren();
				methodtest_not_in28 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple25Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in28, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTriple29;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTriple29 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp30;
				IModelElement[] classTestBasicOpsTriple29Childs = classTestBasicOpsTriple29.getChildren();
				methodsetUp30 = ModelTestUtils.getAssertMethod( classTestBasicOpsTriple29Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp30, new String[] {"self"} );
			}
		}
		//Function test:baditer
		{
		IMethod methodbaditer31;
			IModelElement[] moduleChilds = module.getChildren();
			methodbaditer31 = ModelTestUtils.getAssertMethod( moduleChilds, "baditer", 0 );
		}
		//Function test:gooditer
		{
		IMethod methodgooditer32;
			IModelElement[] moduleChilds = module.getChildren();
			methodgooditer32 = ModelTestUtils.getAssertMethod( moduleChilds, "gooditer", 0 );
		}
		//Class test
		{
		IType classTestExceptionPropagation33;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExceptionPropagation33 = ModelTestUtils.getAssertClass( moduleChilds, "TestExceptionPropagation" );
			//Function test:test_instanceWithException
			{
			IMethod methodtest_instanceWithException34;
				IModelElement[] classTestExceptionPropagation33Childs = classTestExceptionPropagation33.getChildren();
				methodtest_instanceWithException34 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation33Childs, "test_instanceWithException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instanceWithException34, new String[] {"self"} );
			}
			//Function test:test_instancesWithoutException
			{
			IMethod methodtest_instancesWithoutException35;
				IModelElement[] classTestExceptionPropagation33Childs = classTestExceptionPropagation33.getChildren();
				methodtest_instancesWithoutException35 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation33Childs, "test_instancesWithoutException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instancesWithoutException35, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSetOfSets36;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSetOfSets36 = ModelTestUtils.getAssertClass( moduleChilds, "TestSetOfSets" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor37;
				IModelElement[] classTestSetOfSets36Childs = classTestSetOfSets36.getChildren();
				methodtest_constructor37 = ModelTestUtils.getAssertMethod( classTestSetOfSets36Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor37, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBinaryOps38;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryOps38 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryOps" );
			//Function test:setUp
			{
			IMethod methodsetUp39;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodsetUp39 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp39, new String[] {"self"} );
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq40;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_eq40 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq40, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset41;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_union_subset41 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset41, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset42;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_union_superset42 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset42, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap43;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_union_overlap43 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap43, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap44;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_union_non_overlap44 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap44, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset45;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_intersection_subset45 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset45, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset46;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_intersection_superset46 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset46, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap47;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_intersection_overlap47 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap47, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap48;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_intersection_non_overlap48 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap48, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset49;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_sym_difference_subset49 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset49, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset50;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_sym_difference_superset50 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset50, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap51;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_sym_difference_overlap51 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap51, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap52;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_sym_difference_non_overlap52 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap52, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp53;
				IModelElement[] classTestBinaryOps38Childs = classTestBinaryOps38.getChildren();
				methodtest_cmp53 = ModelTestUtils.getAssertMethod( classTestBinaryOps38Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp53, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestUpdateOps54;
			IModelElement[] moduleChilds = module.getChildren();
			classTestUpdateOps54 = ModelTestUtils.getAssertClass( moduleChilds, "TestUpdateOps" );
			//Function test:setUp
			{
			IMethod methodsetUp55;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodsetUp55 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp55, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset56;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_union_subset56 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset56, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset57;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_union_superset57 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset57, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap58;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_union_overlap58 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap58, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap59;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_union_non_overlap59 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap59, new String[] {"self"} );
			}
			//Function test:test_union_method_call
			{
			IMethod methodtest_union_method_call60;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_union_method_call60 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_union_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_method_call60, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset61;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_intersection_subset61 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset61, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset62;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_intersection_superset62 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset62, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap63;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_intersection_overlap63 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap63, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap64;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_intersection_non_overlap64 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap64, new String[] {"self"} );
			}
			//Function test:test_intersection_method_call
			{
			IMethod methodtest_intersection_method_call65;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_intersection_method_call65 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_intersection_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_method_call65, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset66;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_sym_difference_subset66 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset66, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset67;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_sym_difference_superset67 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset67, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap68;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_sym_difference_overlap68 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap68, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap69;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_sym_difference_non_overlap69 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap69, new String[] {"self"} );
			}
			//Function test:test_sym_difference_method_call
			{
			IMethod methodtest_sym_difference_method_call70;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_sym_difference_method_call70 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_sym_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_method_call70, new String[] {"self"} );
			}
			//Function test:test_difference_subset
			{
			IMethod methodtest_difference_subset71;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_difference_subset71 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_subset71, new String[] {"self"} );
			}
			//Function test:test_difference_superset
			{
			IMethod methodtest_difference_superset72;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_difference_superset72 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_superset72, new String[] {"self"} );
			}
			//Function test:test_difference_overlap
			{
			IMethod methodtest_difference_overlap73;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_difference_overlap73 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_overlap73, new String[] {"self"} );
			}
			//Function test:test_difference_non_overlap
			{
			IMethod methodtest_difference_non_overlap74;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_difference_non_overlap74 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_non_overlap74, new String[] {"self"} );
			}
			//Function test:test_difference_method_call
			{
			IMethod methodtest_difference_method_call75;
				IModelElement[] classTestUpdateOps54Childs = classTestUpdateOps54.getChildren();
				methodtest_difference_method_call75 = ModelTestUtils.getAssertMethod( classTestUpdateOps54Childs, "test_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_method_call75, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMutate76;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMutate76 = ModelTestUtils.getAssertClass( moduleChilds, "TestMutate" );
			//Function test:setUp
			{
			IMethod methodsetUp77;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodsetUp77 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp77, new String[] {"self"} );
			}
			//Function test:test_add_present
			{
			IMethod methodtest_add_present78;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_add_present78 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_add_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_present78, new String[] {"self"} );
			}
			//Function test:test_add_absent
			{
			IMethod methodtest_add_absent79;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_add_absent79 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_add_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_absent79, new String[] {"self"} );
			}
			//Function test:test_add_until_full
			{
			IMethod methodtest_add_until_full80;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_add_until_full80 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_add_until_full", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_until_full80, new String[] {"self"} );
			}
			//Function test:test_remove_present
			{
			IMethod methodtest_remove_present81;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_remove_present81 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_remove_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_present81, new String[] {"self"} );
			}
			//Function test:test_remove_absent
			{
			IMethod methodtest_remove_absent82;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_remove_absent82 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_remove_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_absent82, new String[] {"self"} );
			}
			//Function test:test_remove_until_empty
			{
			IMethod methodtest_remove_until_empty83;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_remove_until_empty83 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_remove_until_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_until_empty83, new String[] {"self"} );
			}
			//Function test:test_discard_present
			{
			IMethod methodtest_discard_present84;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_discard_present84 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_discard_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_present84, new String[] {"self"} );
			}
			//Function test:test_discard_absent
			{
			IMethod methodtest_discard_absent85;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_discard_absent85 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_discard_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_absent85, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear86;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_clear86 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear86, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop87;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_pop87 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop87, new String[] {"self"} );
			}
			//Function test:test_update_empty_tuple
			{
			IMethod methodtest_update_empty_tuple88;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_update_empty_tuple88 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_update_empty_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_empty_tuple88, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_overlap
			{
			IMethod methodtest_update_unit_tuple_overlap89;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_update_unit_tuple_overlap89 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_update_unit_tuple_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_overlap89, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_non_overlap
			{
			IMethod methodtest_update_unit_tuple_non_overlap90;
				IModelElement[] classTestMutate76Childs = classTestMutate76.getChildren();
				methodtest_update_unit_tuple_non_overlap90 = ModelTestUtils.getAssertMethod( classTestMutate76Childs, "test_update_unit_tuple_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_non_overlap90, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsets91;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsets91 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsets" );
			{
				IModelElement[] classTestSubsets91Childs = classTestSubsets91.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets91Childs, "case2method");
			}
			{
				IModelElement[] classTestSubsets91Childs = classTestSubsets91.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets91Childs, "reverse");
			}
			//Function test:test_issubset
			{
			IMethod methodtest_issubset92;
				IModelElement[] classTestSubsets91Childs = classTestSubsets91.getChildren();
				methodtest_issubset92 = ModelTestUtils.getAssertMethod( classTestSubsets91Childs, "test_issubset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubset92, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsetEqualEmpty93;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualEmpty93 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualEmpty" );
			{
				IModelElement[] classTestSubsetEqualEmpty93Childs = classTestSubsetEqualEmpty93.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty93Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty93Childs = classTestSubsetEqualEmpty93.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty93Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty93Childs = classTestSubsetEqualEmpty93.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty93Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty93Childs = classTestSubsetEqualEmpty93.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty93Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEqualNonEmpty94;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualNonEmpty94 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualNonEmpty" );
			{
				IModelElement[] classTestSubsetEqualNonEmpty94Childs = classTestSubsetEqualNonEmpty94.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty94Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty94Childs = classTestSubsetEqualNonEmpty94.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty94Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty94Childs = classTestSubsetEqualNonEmpty94.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty94Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty94Childs = classTestSubsetEqualNonEmpty94.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty94Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEmptyNonEmpty95;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEmptyNonEmpty95 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEmptyNonEmpty" );
			{
				IModelElement[] classTestSubsetEmptyNonEmpty95Childs = classTestSubsetEmptyNonEmpty95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty95Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty95Childs = classTestSubsetEmptyNonEmpty95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty95Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty95Childs = classTestSubsetEmptyNonEmpty95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty95Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty95Childs = classTestSubsetEmptyNonEmpty95.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty95Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetPartial96;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetPartial96 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetPartial" );
			{
				IModelElement[] classTestSubsetPartial96Childs = classTestSubsetPartial96.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial96Childs, "left");
			}
			{
				IModelElement[] classTestSubsetPartial96Childs = classTestSubsetPartial96.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial96Childs, "right");
			}
			{
				IModelElement[] classTestSubsetPartial96Childs = classTestSubsetPartial96.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial96Childs, "name");
			}
			{
				IModelElement[] classTestSubsetPartial96Childs = classTestSubsetPartial96.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial96Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetNonOverlap97;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetNonOverlap97 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetNonOverlap" );
			{
				IModelElement[] classTestSubsetNonOverlap97Childs = classTestSubsetNonOverlap97.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap97Childs, "left");
			}
			{
				IModelElement[] classTestSubsetNonOverlap97Childs = classTestSubsetNonOverlap97.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap97Childs, "right");
			}
			{
				IModelElement[] classTestSubsetNonOverlap97Childs = classTestSubsetNonOverlap97.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap97Childs, "name");
			}
			{
				IModelElement[] classTestSubsetNonOverlap97Childs = classTestSubsetNonOverlap97.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap97Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestOnlySetsInBinaryOps98;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsInBinaryOps98 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsInBinaryOps" );
			//Function test:test_eq_ne
			{
			IMethod methodtest_eq_ne99;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_eq_ne99 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_eq_ne", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq_ne99, new String[] {"self"} );
			}
			//Function test:test_ge_gt_le_lt
			{
			IMethod methodtest_ge_gt_le_lt100;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_ge_gt_le_lt100 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_ge_gt_le_lt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ge_gt_le_lt100, new String[] {"self"} );
			}
			//Function test:test_union_update_operator
			{
			IMethod methodtest_union_update_operator101;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_union_update_operator101 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_union_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_update_operator101, new String[] {"self"} );
			}
			//Function test:test_union_update
			{
			IMethod methodtest_union_update102;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_union_update102 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_union_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_update102, new String[] {"self"} );
			}
			//Function test:test_union
			{
			IMethod methodtest_union103;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_union103 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union103, new String[] {"self"} );
			}
			//Function test:test_intersection_update_operator
			{
			IMethod methodtest_intersection_update_operator104;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_intersection_update_operator104 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_intersection_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update_operator104, new String[] {"self"} );
			}
			//Function test:test_intersection_update
			{
			IMethod methodtest_intersection_update105;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_intersection_update105 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_intersection_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update105, new String[] {"self"} );
			}
			//Function test:test_intersection
			{
			IMethod methodtest_intersection106;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_intersection106 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection106, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update_operator
			{
			IMethod methodtest_sym_difference_update_operator107;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_sym_difference_update_operator107 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_sym_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update_operator107, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update
			{
			IMethod methodtest_sym_difference_update108;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_sym_difference_update108 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_sym_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update108, new String[] {"self"} );
			}
			//Function test:test_sym_difference
			{
			IMethod methodtest_sym_difference109;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_sym_difference109 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_sym_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference109, new String[] {"self"} );
			}
			//Function test:test_difference_update_operator
			{
			IMethod methodtest_difference_update_operator110;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_difference_update_operator110 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update_operator110, new String[] {"self"} );
			}
			//Function test:test_difference_update
			{
			IMethod methodtest_difference_update111;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_difference_update111 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update111, new String[] {"self"} );
			}
			//Function test:test_difference
			{
			IMethod methodtest_difference112;
				IModelElement[] classTestOnlySetsInBinaryOps98Childs = classTestOnlySetsInBinaryOps98.getChildren();
				methodtest_difference112 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps98Childs, "test_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference112, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsNumeric113;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsNumeric113 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsNumeric" );
			//Function test:setUp
			{
			IMethod methodsetUp114;
				IModelElement[] classTestOnlySetsNumeric113Childs = classTestOnlySetsNumeric113.getChildren();
				methodsetUp114 = ModelTestUtils.getAssertMethod( classTestOnlySetsNumeric113Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp114, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsDict115;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsDict115 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsDict" );
			//Function test:setUp
			{
			IMethod methodsetUp116;
				IModelElement[] classTestOnlySetsDict115Childs = classTestOnlySetsDict115.getChildren();
				methodsetUp116 = ModelTestUtils.getAssertMethod( classTestOnlySetsDict115Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp116, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsOperator117;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsOperator117 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsOperator" );
			//Function test:setUp
			{
			IMethod methodsetUp118;
				IModelElement[] classTestOnlySetsOperator117Childs = classTestOnlySetsOperator117.getChildren();
				methodsetUp118 = ModelTestUtils.getAssertMethod( classTestOnlySetsOperator117Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp118, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsTuple119;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsTuple119 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp120;
				IModelElement[] classTestOnlySetsTuple119Childs = classTestOnlySetsTuple119.getChildren();
				methodsetUp120 = ModelTestUtils.getAssertMethod( classTestOnlySetsTuple119Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp120, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsString121;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsString121 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsString" );
			//Function test:setUp
			{
			IMethod methodsetUp122;
				IModelElement[] classTestOnlySetsString121Childs = classTestOnlySetsString121.getChildren();
				methodsetUp122 = ModelTestUtils.getAssertMethod( classTestOnlySetsString121Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp122, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsGenerator123;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsGenerator123 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsGenerator" );
			//Function test:setUp
			{
			IMethod methodsetUp124;
				IModelElement[] classTestOnlySetsGenerator123Childs = classTestOnlySetsGenerator123.getChildren();
				methodsetUp124 = ModelTestUtils.getAssertMethod( classTestOnlySetsGenerator123Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp124, new String[] {"self"} );
				//Function test:gen
				{
				IMethod methodgen125;
					IModelElement[] methodsetUp124Childs = methodsetUp124.getChildren();
					methodgen125 = ModelTestUtils.getAssertMethod( methodsetUp124Childs, "gen", 0 );
				}
			}
		}
		//Class test
		{
		IType classTestOnlySetsofSets126;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsofSets126 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsofSets" );
			//Function test:setUp
			{
			IMethod methodsetUp127;
				IModelElement[] classTestOnlySetsofSets126Childs = classTestOnlySetsofSets126.getChildren();
				methodsetUp127 = ModelTestUtils.getAssertMethod( classTestOnlySetsofSets126Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp127, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopying128;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopying128 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopying" );
			//Function test:test_copy
			{
			IMethod methodtest_copy129;
				IModelElement[] classTestCopying128Childs = classTestCopying128.getChildren();
				methodtest_copy129 = ModelTestUtils.getAssertMethod( classTestCopying128Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy129, new String[] {"self"} );
			}
			//Function test:test_deep_copy
			{
			IMethod methodtest_deep_copy130;
				IModelElement[] classTestCopying128Childs = classTestCopying128.getChildren();
				methodtest_deep_copy130 = ModelTestUtils.getAssertMethod( classTestCopying128Childs, "test_deep_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deep_copy130, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingEmpty131;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingEmpty131 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp132;
				IModelElement[] classTestCopyingEmpty131Childs = classTestCopyingEmpty131.getChildren();
				methodsetUp132 = ModelTestUtils.getAssertMethod( classTestCopyingEmpty131Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp132, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingSingleton133;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingSingleton133 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp134;
				IModelElement[] classTestCopyingSingleton133Childs = classTestCopyingSingleton133.getChildren();
				methodsetUp134 = ModelTestUtils.getAssertMethod( classTestCopyingSingleton133Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp134, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingTriple135;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTriple135 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp136;
				IModelElement[] classTestCopyingTriple135Childs = classTestCopyingTriple135.getChildren();
				methodsetUp136 = ModelTestUtils.getAssertMethod( classTestCopyingTriple135Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp136, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingTuple137;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTuple137 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp138;
				IModelElement[] classTestCopyingTuple137Childs = classTestCopyingTuple137.getChildren();
				methodsetUp138 = ModelTestUtils.getAssertMethod( classTestCopyingTuple137Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp138, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingNested139;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingNested139 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingNested" );
			//Function test:setUp
			{
			IMethod methodsetUp140;
				IModelElement[] classTestCopyingNested139Childs = classTestCopyingNested139.getChildren();
				methodsetUp140 = ModelTestUtils.getAssertMethod( classTestCopyingNested139Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp140, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestIdentities141;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIdentities141 = ModelTestUtils.getAssertClass( moduleChilds, "TestIdentities" );
			//Function test:setUp
			{
			IMethod methodsetUp142;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodsetUp142 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp142, new String[] {"self"} );
			}
			//Function test:test_binopsVsSubsets
			{
			IMethod methodtest_binopsVsSubsets143;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodtest_binopsVsSubsets143 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "test_binopsVsSubsets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binopsVsSubsets143, new String[] {"self"} );
			}
			//Function test:test_commutativity
			{
			IMethod methodtest_commutativity144;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodtest_commutativity144 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "test_commutativity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commutativity144, new String[] {"self"} );
			}
			//Function test:test_reflexsive_relations
			{
			IMethod methodtest_reflexsive_relations145;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodtest_reflexsive_relations145 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "test_reflexsive_relations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reflexsive_relations145, new String[] {"self"} );
			}
			//Function test:test_summations
			{
			IMethod methodtest_summations146;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodtest_summations146 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "test_summations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_summations146, new String[] {"self"} );
			}
			//Function test:test_exclusion
			{
			IMethod methodtest_exclusion147;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodtest_exclusion147 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "test_exclusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exclusion147, new String[] {"self"} );
			}
			//Function test:test_cardinality_relations
			{
			IMethod methodtest_cardinality_relations148;
				IModelElement[] classTestIdentities141Childs = classTestIdentities141.getChildren();
				methodtest_cardinality_relations148 = ModelTestUtils.getAssertMethod( classTestIdentities141Childs, "test_cardinality_relations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cardinality_relations148, new String[] {"self"} );
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
		IMethod methodtest_main149;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main149 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main149, new String[] {"verbose"} );
		}

	}
	public void testModelGen62( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecmaps_tw.py"));

		assertNotNull("Module test_codecmaps_tw.py not found", module);
		assertEquals("test_codecmaps_tw.py", module.getElementName());
		
		//Class test
		{
		IType classTestBIG5Map0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBIG5Map0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBIG5Map" );
			{
				IModelElement[] classTestBIG5Map0Childs = classTestBIG5Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBIG5Map0Childs, "encoding");
			}
			{
				IModelElement[] classTestBIG5Map0Childs = classTestBIG5Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBIG5Map0Childs, "mapfilename");
			}
			{
				IModelElement[] classTestBIG5Map0Childs = classTestBIG5Map0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBIG5Map0Childs, "mapfileurl");
			}
		}
		//Class test
		{
		IType classTestCP950Map1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCP950Map1 = ModelTestUtils.getAssertClass( moduleChilds, "TestCP950Map" );
			{
				IModelElement[] classTestCP950Map1Childs = classTestCP950Map1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP950Map1Childs, "encoding");
			}
			{
				IModelElement[] classTestCP950Map1Childs = classTestCP950Map1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP950Map1Childs, "mapfilename");
			}
			{
				IModelElement[] classTestCP950Map1Childs = classTestCP950Map1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP950Map1Childs, "mapfileurl");
			}
			{
				IModelElement[] classTestCP950Map1Childs = classTestCP950Map1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCP950Map1Childs, "pass_enctest");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen63( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pyclbr.py"));

		assertNotNull("Module test_pyclbr.py not found", module);
		assertEquals("test_pyclbr.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "StaticMethodType");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ClassMethodType");
		}
		//Class test
		{
		IType classPyclbrTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classPyclbrTest0 = ModelTestUtils.getAssertClass( moduleChilds, "PyclbrTest" );
			//Function test:assertListEq
			{
			IMethod methodassertListEq1;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodassertListEq1 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "assertListEq", 4 );
				ModelTestUtils.assertParameterNames( methodassertListEq1, new String[] {"self", "l1", "l2", "ignore"} );
			}
			//Function test:assertHasattr
			{
			IMethod methodassertHasattr2;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodassertHasattr2 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "assertHasattr", 4 );
				ModelTestUtils.assertParameterNames( methodassertHasattr2, new String[] {"self", "obj", "attr", "ignore"} );
			}
			//Function test:assertHaskey
			{
			IMethod methodassertHaskey3;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodassertHaskey3 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "assertHaskey", 4 );
				ModelTestUtils.assertParameterNames( methodassertHaskey3, new String[] {"self", "obj", "key", "ignore"} );
			}
			//Function test:assertEqualsOrIgnored
			{
			IMethod methodassertEqualsOrIgnored4;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodassertEqualsOrIgnored4 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "assertEqualsOrIgnored", 4 );
				ModelTestUtils.assertParameterNames( methodassertEqualsOrIgnored4, new String[] {"self", "a", "b", "ignore"} );
			}
			//Function test:checkModule
			{
			IMethod methodcheckModule5;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodcheckModule5 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "checkModule", 4 );
				ModelTestUtils.assertParameterNames( methodcheckModule5, new String[] {"self", "moduleName", "module", "ignore"} );
				//Function test:ismethod
				{
				IMethod methodismethod6;
					IModelElement[] methodcheckModule5Childs = methodcheckModule5.getChildren();
					methodismethod6 = ModelTestUtils.getAssertMethod( methodcheckModule5Childs, "ismethod", 3 );
					ModelTestUtils.assertParameterNames( methodismethod6, new String[] {"oclass", "obj", "name"} );
				}
				//Function test:defined_in
				{
				IMethod methoddefined_in7;
					IModelElement[] methodcheckModule5Childs = methodcheckModule5.getChildren();
					methoddefined_in7 = ModelTestUtils.getAssertMethod( methodcheckModule5Childs, "defined_in", 2 );
					ModelTestUtils.assertParameterNames( methoddefined_in7, new String[] {"item", "module"} );
				}
			}
			//Function test:test_easy
			{
			IMethod methodtest_easy8;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodtest_easy8 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "test_easy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_easy8, new String[] {"self"} );
			}
			//Function test:test_decorators
			{
			IMethod methodtest_decorators9;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodtest_decorators9 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "test_decorators", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decorators9, new String[] {"self"} );
			}
			//Function test:test_others
			{
			IMethod methodtest_others10;
				IModelElement[] classPyclbrTest0Childs = classPyclbrTest0.getChildren();
				methodtest_others10 = ModelTestUtils.getAssertMethod( classPyclbrTest0Childs, "test_others", 1 );
				ModelTestUtils.assertParameterNames( methodtest_others10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen64( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("reperf.py"));

		assertNotNull("Module reperf.py not found", module);
		assertEquals("reperf.py", module.getElementName());
		
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}
		//Function test:timefunc
		{
		IMethod methodtimefunc1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtimefunc1 = ModelTestUtils.getAssertMethod( moduleChilds, "timefunc", 4 );
			ModelTestUtils.assertParameterNames( methodtimefunc1, new String[] {"n", "func", "args", "kw"} );
		}

	}
	public void testModelGen65( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_winreg.py"));

		assertNotNull("Module test_winreg.py not found", module);
		assertEquals("test_winreg.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_key_name");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_data");
		}
		//Function test:WriteTestData
		{
		IMethod methodWriteTestData0;
			IModelElement[] moduleChilds = module.getChildren();
			methodWriteTestData0 = ModelTestUtils.getAssertMethod( moduleChilds, "WriteTestData", 1 );
			ModelTestUtils.assertParameterNames( methodWriteTestData0, new String[] {"root_key"} );
		}
		//Function test:ReadTestData
		{
		IMethod methodReadTestData1;
			IModelElement[] moduleChilds = module.getChildren();
			methodReadTestData1 = ModelTestUtils.getAssertMethod( moduleChilds, "ReadTestData", 1 );
			ModelTestUtils.assertParameterNames( methodReadTestData1, new String[] {"root_key"} );
		}
		//Function test:DeleteTestData
		{
		IMethod methodDeleteTestData2;
			IModelElement[] moduleChilds = module.getChildren();
			methodDeleteTestData2 = ModelTestUtils.getAssertMethod( moduleChilds, "DeleteTestData", 1 );
			ModelTestUtils.assertParameterNames( methodDeleteTestData2, new String[] {"root_key"} );
		}
		//Function test:TestAll
		{
		IMethod methodTestAll3;
			IModelElement[] moduleChilds = module.getChildren();
			methodTestAll3 = ModelTestUtils.getAssertMethod( moduleChilds, "TestAll", 1 );
			ModelTestUtils.assertParameterNames( methodTestAll3, new String[] {"root_key"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "remote_name");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "remote_name");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "remote_key");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "remote_key");
		}

	}
	public void testModelGen66( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_userdict.py"));

		assertNotNull("Module test_userdict.py not found", module);
		assertEquals("test_userdict.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d0");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d4");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d5");
		}
		//Class test
		{
		IType classUserDictTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUserDictTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UserDictTest" );
			{
				IModelElement[] classUserDictTest0Childs = classUserDictTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserDictTest0Childs, "type2test");
			}
			//Function test:test_all
			{
			IMethod methodtest_all1;
				IModelElement[] classUserDictTest0Childs = classUserDictTest0.getChildren();
				methodtest_all1 = ModelTestUtils.getAssertMethod( classUserDictTest0Childs, "test_all", 1 );
				ModelTestUtils.assertParameterNames( methodtest_all1, new String[] {"self"} );
				//Class test
				{
				IType classMyUserDict2;
					IModelElement[] methodtest_all1Childs = methodtest_all1.getChildren();
					classMyUserDict2 = ModelTestUtils.getAssertClass( methodtest_all1Childs, "MyUserDict" );
					//Function test:display
					{
					IMethod methoddisplay3;
						IModelElement[] classMyUserDict2Childs = classMyUserDict2.getChildren();
						methoddisplay3 = ModelTestUtils.getAssertMethod( classMyUserDict2Childs, "display", 1 );
						ModelTestUtils.assertParameterNames( methoddisplay3, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classItems4;
					IModelElement[] methodtest_all1Childs = methodtest_all1.getChildren();
					classItems4 = ModelTestUtils.getAssertClass( methodtest_all1Childs, "Items" );
					//Function test:items
					{
					IMethod methoditems5;
						IModelElement[] classItems4Childs = classItems4.getChildren();
						methoditems5 = ModelTestUtils.getAssertMethod( classItems4Childs, "items", 1 );
						ModelTestUtils.assertParameterNames( methoditems5, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classSeqDict6;
			IModelElement[] moduleChilds = module.getChildren();
			classSeqDict6 = ModelTestUtils.getAssertClass( moduleChilds, "SeqDict" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "other", "kwargs"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__8;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__getitem__8 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__8, new String[] {"self", "key"} );
			}
			//Function test:__setitem__
			{
			IMethod method__setitem__9;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__setitem__9 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__setitem__", 3 );
				ModelTestUtils.assertParameterNames( method__setitem__9, new String[] {"self", "key", "value"} );
			}
			//Function test:__delitem__
			{
			IMethod method__delitem__10;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__delitem__10 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__delitem__", 2 );
				ModelTestUtils.assertParameterNames( method__delitem__10, new String[] {"self", "key"} );
			}
			//Function test:keys
			{
			IMethod methodkeys11;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				methodkeys11 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "keys", 1 );
				ModelTestUtils.assertParameterNames( methodkeys11, new String[] {"self"} );
			}
			//Function test:copy
			{
			IMethod methodcopy12;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				methodcopy12 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "copy", 1 );
				ModelTestUtils.assertParameterNames( methodcopy12, new String[] {"self"} );
			}
			//Function test:fromkeys
			{
			IMethod methodfromkeys13;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				methodfromkeys13 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "fromkeys", 3 );
				ModelTestUtils.assertParameterNames( methodfromkeys13, new String[] {"cls", "keys", "value"} );
			}
			{
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSeqDict6Childs, "fromkeys");
			}
		}
		//Class test
		{
		IType classUserDictMixinTest14;
			IModelElement[] moduleChilds = module.getChildren();
			classUserDictMixinTest14 = ModelTestUtils.getAssertClass( moduleChilds, "UserDictMixinTest" );
			{
				IModelElement[] classUserDictMixinTest14Childs = classUserDictMixinTest14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserDictMixinTest14Childs, "type2test");
			}
			//Function test:test_all
			{
			IMethod methodtest_all15;
				IModelElement[] classUserDictMixinTest14Childs = classUserDictMixinTest14.getChildren();
				methodtest_all15 = ModelTestUtils.getAssertMethod( classUserDictMixinTest14Childs, "test_all", 1 );
				ModelTestUtils.assertParameterNames( methodtest_all15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen67( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dummy_thread.py"));

		assertNotNull("Module test_dummy_thread.py not found", module);
		assertEquals("test_dummy_thread.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DELAY");
		}
		//Class test
		{
		IType classLockTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classLockTests0 = ModelTestUtils.getAssertClass( moduleChilds, "LockTests" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:test_initlock
			{
			IMethod methodtest_initlock2;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_initlock2 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_initlock", 1 );
				ModelTestUtils.assertParameterNames( methodtest_initlock2, new String[] {"self"} );
			}
			//Function test:test_release
			{
			IMethod methodtest_release3;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_release3 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_release", 1 );
				ModelTestUtils.assertParameterNames( methodtest_release3, new String[] {"self"} );
			}
			//Function test:test_improper_release
			{
			IMethod methodtest_improper_release4;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_improper_release4 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_improper_release", 1 );
				ModelTestUtils.assertParameterNames( methodtest_improper_release4, new String[] {"self"} );
			}
			//Function test:test_cond_acquire_success
			{
			IMethod methodtest_cond_acquire_success5;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_cond_acquire_success5 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_cond_acquire_success", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cond_acquire_success5, new String[] {"self"} );
			}
			//Function test:test_cond_acquire_fail
			{
			IMethod methodtest_cond_acquire_fail6;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_cond_acquire_fail6 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_cond_acquire_fail", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cond_acquire_fail6, new String[] {"self"} );
			}
			//Function test:test_uncond_acquire_success
			{
			IMethod methodtest_uncond_acquire_success7;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_uncond_acquire_success7 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_uncond_acquire_success", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uncond_acquire_success7, new String[] {"self"} );
			}
			//Function test:test_uncond_acquire_return_val
			{
			IMethod methodtest_uncond_acquire_return_val8;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_uncond_acquire_return_val8 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_uncond_acquire_return_val", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uncond_acquire_return_val8, new String[] {"self"} );
			}
			//Function test:test_uncond_acquire_blocking
			{
			IMethod methodtest_uncond_acquire_blocking9;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_uncond_acquire_blocking9 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_uncond_acquire_blocking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uncond_acquire_blocking9, new String[] {"self"} );
				//Function test:delay_unlock
				{
				IMethod methoddelay_unlock10;
					IModelElement[] methodtest_uncond_acquire_blocking9Childs = methodtest_uncond_acquire_blocking9.getChildren();
					methoddelay_unlock10 = ModelTestUtils.getAssertMethod( methodtest_uncond_acquire_blocking9Childs, "delay_unlock", 2 );
					ModelTestUtils.assertParameterNames( methoddelay_unlock10, new String[] {"to_unlock", "delay"} );
				}
			}
		}
		//Class test
		{
		IType classMiscTests11;
			IModelElement[] moduleChilds = module.getChildren();
			classMiscTests11 = ModelTestUtils.getAssertClass( moduleChilds, "MiscTests" );
			//Function test:test_exit
			{
			IMethod methodtest_exit12;
				IModelElement[] classMiscTests11Childs = classMiscTests11.getChildren();
				methodtest_exit12 = ModelTestUtils.getAssertMethod( classMiscTests11Childs, "test_exit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exit12, new String[] {"self"} );
			}
			//Function test:test_ident
			{
			IMethod methodtest_ident13;
				IModelElement[] classMiscTests11Childs = classMiscTests11.getChildren();
				methodtest_ident13 = ModelTestUtils.getAssertMethod( classMiscTests11Childs, "test_ident", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ident13, new String[] {"self"} );
			}
			//Function test:test_LockType
			{
			IMethod methodtest_LockType14;
				IModelElement[] classMiscTests11Childs = classMiscTests11.getChildren();
				methodtest_LockType14 = ModelTestUtils.getAssertMethod( classMiscTests11Childs, "test_LockType", 1 );
				ModelTestUtils.assertParameterNames( methodtest_LockType14, new String[] {"self"} );
			}
			//Function test:test_interrupt_main
			{
			IMethod methodtest_interrupt_main15;
				IModelElement[] classMiscTests11Childs = classMiscTests11.getChildren();
				methodtest_interrupt_main15 = ModelTestUtils.getAssertMethod( classMiscTests11Childs, "test_interrupt_main", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interrupt_main15, new String[] {"self"} );
				//Function test:call_interrupt
				{
				IMethod methodcall_interrupt16;
					IModelElement[] methodtest_interrupt_main15Childs = methodtest_interrupt_main15.getChildren();
					methodcall_interrupt16 = ModelTestUtils.getAssertMethod( methodtest_interrupt_main15Childs, "call_interrupt", 0 );
				}
			}
			//Function test:test_interrupt_in_main
			{
			IMethod methodtest_interrupt_in_main17;
				IModelElement[] classMiscTests11Childs = classMiscTests11.getChildren();
				methodtest_interrupt_in_main17 = ModelTestUtils.getAssertMethod( classMiscTests11Childs, "test_interrupt_in_main", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interrupt_in_main17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadTests18;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadTests18 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadTests" );
			//Function test:test_arg_passing
			{
			IMethod methodtest_arg_passing19;
				IModelElement[] classThreadTests18Childs = classThreadTests18.getChildren();
				methodtest_arg_passing19 = ModelTestUtils.getAssertMethod( classThreadTests18Childs, "test_arg_passing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_arg_passing19, new String[] {"self"} );
				//Function test:arg_tester
				{
				IMethod methodarg_tester20;
					IModelElement[] methodtest_arg_passing19Childs = methodtest_arg_passing19.getChildren();
					methodarg_tester20 = ModelTestUtils.getAssertMethod( methodtest_arg_passing19Childs, "arg_tester", 3 );
					ModelTestUtils.assertParameterNames( methodarg_tester20, new String[] {"queue", "arg1", "arg2"} );
				}
			}
			//Function test:test_multi_creation
			{
			IMethod methodtest_multi_creation21;
				IModelElement[] classThreadTests18Childs = classThreadTests18.getChildren();
				methodtest_multi_creation21 = ModelTestUtils.getAssertMethod( classThreadTests18Childs, "test_multi_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multi_creation21, new String[] {"self"} );
				//Function test:queue_mark
				{
				IMethod methodqueue_mark22;
					IModelElement[] methodtest_multi_creation21Childs = methodtest_multi_creation21.getChildren();
					methodqueue_mark22 = ModelTestUtils.getAssertMethod( methodtest_multi_creation21Childs, "queue_mark", 2 );
					ModelTestUtils.assertParameterNames( methodqueue_mark22, new String[] {"queue", "delay"} );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main23, new String[] {"imported_module"} );
		}

	}
	public void testModelGen68( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_errno.py"));

		assertNotNull("Module test_errno.py not found", module);
		assertEquals("test_errno.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "errors");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}

	}
	public void testModelGen69( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bsddb185.py"));

		assertNotNull("Module test_bsddb185.py not found", module);
		assertEquals("test_bsddb185.py", module.getElementName());
		
		//Class test
		{
		IType classBsddb185Tests0;
			IModelElement[] moduleChilds = module.getChildren();
			classBsddb185Tests0 = ModelTestUtils.getAssertClass( moduleChilds, "Bsddb185Tests" );
			//Function test:test_open_existing_hash
			{
			IMethod methodtest_open_existing_hash1;
				IModelElement[] classBsddb185Tests0Childs = classBsddb185Tests0.getChildren();
				methodtest_open_existing_hash1 = ModelTestUtils.getAssertMethod( classBsddb185Tests0Childs, "test_open_existing_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_open_existing_hash1, new String[] {"self"} );
			}
			//Function test:test_whichdb
			{
			IMethod methodtest_whichdb2;
				IModelElement[] classBsddb185Tests0Childs = classBsddb185Tests0.getChildren();
				methodtest_whichdb2 = ModelTestUtils.getAssertMethod( classBsddb185Tests0Childs, "test_whichdb", 1 );
				ModelTestUtils.assertParameterNames( methodtest_whichdb2, new String[] {"self"} );
			}
			//Function test:test_anydbm_create
			{
			IMethod methodtest_anydbm_create3;
				IModelElement[] classBsddb185Tests0Childs = classBsddb185Tests0.getChildren();
				methodtest_anydbm_create3 = ModelTestUtils.getAssertMethod( classBsddb185Tests0Childs, "test_anydbm_create", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anydbm_create3, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen70( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_doctest.py"));

		assertNotNull("Module test_doctest.py not found", module);
		assertEquals("test_doctest.py", module.getElementName());
		
		//Function test:sample_func
		{
		IMethod methodsample_func0;
			IModelElement[] moduleChilds = module.getChildren();
			methodsample_func0 = ModelTestUtils.getAssertMethod( moduleChilds, "sample_func", 1 );
			ModelTestUtils.assertParameterNames( methodsample_func0, new String[] {"v"} );
		}
		//Class test
		{
		IType classSampleClass1;
			IModelElement[] moduleChilds = module.getChildren();
			classSampleClass1 = ModelTestUtils.getAssertClass( moduleChilds, "SampleClass" );
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "val"} );
			}
			//Function test:double
			{
			IMethod methoddouble3;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methoddouble3 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "double", 1 );
				ModelTestUtils.assertParameterNames( methoddouble3, new String[] {"self"} );
			}
			//Function test:get
			{
			IMethod methodget4;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methodget4 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "get", 1 );
				ModelTestUtils.assertParameterNames( methodget4, new String[] {"self"} );
			}
			//Function test:a_staticmethod
			{
			IMethod methoda_staticmethod5;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methoda_staticmethod5 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "a_staticmethod", 1 );
				ModelTestUtils.assertParameterNames( methoda_staticmethod5, new String[] {"v"} );
			}
			{
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSampleClass1Childs, "a_staticmethod");
			}
			//Function test:a_classmethod
			{
			IMethod methoda_classmethod6;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methoda_classmethod6 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "a_classmethod", 2 );
				ModelTestUtils.assertParameterNames( methoda_classmethod6, new String[] {"cls", "v"} );
			}
			{
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSampleClass1Childs, "a_classmethod");
			}
			{
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSampleClass1Childs, "a_property");
			}
			//Class test
			{
			IType classNestedClass7;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				classNestedClass7 = ModelTestUtils.getAssertClass( classSampleClass1Childs, "NestedClass" );
				//Function test:__init__
				{
				IMethod method__init__8;
					IModelElement[] classNestedClass7Childs = classNestedClass7.getChildren();
					method__init__8 = ModelTestUtils.getAssertMethod( classNestedClass7Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "val"} );
				}
				//Function test:square
				{
				IMethod methodsquare9;
					IModelElement[] classNestedClass7Childs = classNestedClass7.getChildren();
					methodsquare9 = ModelTestUtils.getAssertMethod( classNestedClass7Childs, "square", 1 );
					ModelTestUtils.assertParameterNames( methodsquare9, new String[] {"self"} );
				}
				//Function test:get
				{
				IMethod methodget10;
					IModelElement[] classNestedClass7Childs = classNestedClass7.getChildren();
					methodget10 = ModelTestUtils.getAssertMethod( classNestedClass7Childs, "get", 1 );
					ModelTestUtils.assertParameterNames( methodget10, new String[] {"self"} );
				}
			}
		}
		//Class test
		{
		IType classSampleNewStyleClass11;
			IModelElement[] moduleChilds = module.getChildren();
			classSampleNewStyleClass11 = ModelTestUtils.getAssertClass( moduleChilds, "SampleNewStyleClass" );
			//Function test:__init__
			{
			IMethod method__init__12;
				IModelElement[] classSampleNewStyleClass11Childs = classSampleNewStyleClass11.getChildren();
				method__init__12 = ModelTestUtils.getAssertMethod( classSampleNewStyleClass11Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self", "val"} );
			}
			//Function test:double
			{
			IMethod methoddouble13;
				IModelElement[] classSampleNewStyleClass11Childs = classSampleNewStyleClass11.getChildren();
				methoddouble13 = ModelTestUtils.getAssertMethod( classSampleNewStyleClass11Childs, "double", 1 );
				ModelTestUtils.assertParameterNames( methoddouble13, new String[] {"self"} );
			}
			//Function test:get
			{
			IMethod methodget14;
				IModelElement[] classSampleNewStyleClass11Childs = classSampleNewStyleClass11.getChildren();
				methodget14 = ModelTestUtils.getAssertMethod( classSampleNewStyleClass11Childs, "get", 1 );
				ModelTestUtils.assertParameterNames( methodget14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType class_FakeInput15;
			IModelElement[] moduleChilds = module.getChildren();
			class_FakeInput15 = ModelTestUtils.getAssertClass( moduleChilds, "_FakeInput" );
			//Function test:__init__
			{
			IMethod method__init__16;
				IModelElement[] class_FakeInput15Childs = class_FakeInput15.getChildren();
				method__init__16 = ModelTestUtils.getAssertMethod( class_FakeInput15Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__16, new String[] {"self", "lines"} );
			}
			//Function test:readline
			{
			IMethod methodreadline17;
				IModelElement[] class_FakeInput15Childs = class_FakeInput15.getChildren();
				methodreadline17 = ModelTestUtils.getAssertMethod( class_FakeInput15Childs, "readline", 1 );
				ModelTestUtils.assertParameterNames( methodreadline17, new String[] {"self"} );
			}
		}
		//Function test:test_Example
		{
		IMethod methodtest_Example18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_Example18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_Example", 0 );
		}
		//Function test:test_DocTest
		{
		IMethod methodtest_DocTest19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTest19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTest", 0 );
		}
		//Function test:test_DocTestFinder
		{
		IMethod methodtest_DocTestFinder20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTestFinder20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTestFinder", 0 );
		}
		//Function test:test_DocTestParser
		{
		IMethod methodtest_DocTestParser21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTestParser21 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTestParser", 0 );
		}
		//Class test
		{
		IType classtest_DocTestRunner22;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_DocTestRunner22 = ModelTestUtils.getAssertClass( moduleChilds, "test_DocTestRunner" );
			//Function test:basics
			{
			IMethod methodbasics23;
				IModelElement[] classtest_DocTestRunner22Childs = classtest_DocTestRunner22.getChildren();
				methodbasics23 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner22Childs, "basics", 0 );
			}
			//Function test:verbose_flag
			{
			IMethod methodverbose_flag24;
				IModelElement[] classtest_DocTestRunner22Childs = classtest_DocTestRunner22.getChildren();
				methodverbose_flag24 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner22Childs, "verbose_flag", 0 );
			}
			//Function test:exceptions
			{
			IMethod methodexceptions25;
				IModelElement[] classtest_DocTestRunner22Childs = classtest_DocTestRunner22.getChildren();
				methodexceptions25 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner22Childs, "exceptions", 0 );
			}
			//Function test:optionflags
			{
			IMethod methodoptionflags26;
				IModelElement[] classtest_DocTestRunner22Childs = classtest_DocTestRunner22.getChildren();
				methodoptionflags26 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner22Childs, "optionflags", 0 );
			}
			//Function test:option_directives
			{
			IMethod methodoption_directives27;
				IModelElement[] classtest_DocTestRunner22Childs = classtest_DocTestRunner22.getChildren();
				methodoption_directives27 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner22Childs, "option_directives", 0 );
			}
		}
		//Function test:test_testsource
		{
		IMethod methodtest_testsource28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_testsource28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_testsource", 0 );
		}
		//Function test:test_debug
		{
		IMethod methodtest_debug29;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_debug29 = ModelTestUtils.getAssertMethod( moduleChilds, "test_debug", 0 );
		}
		//Function test:test_pdb_set_trace
		{
		IMethod methodtest_pdb_set_trace30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_pdb_set_trace30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_pdb_set_trace", 0 );
		}
		//Function test:test_pdb_set_trace_nested
		{
		IMethod methodtest_pdb_set_trace_nested31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_pdb_set_trace_nested31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_pdb_set_trace_nested", 0 );
		}
		//Function test:test_DocTestSuite
		{
		IMethod methodtest_DocTestSuite32;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTestSuite32 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTestSuite", 0 );
		}
		//Function test:test_DocFileSuite
		{
		IMethod methodtest_DocFileSuite33;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocFileSuite33 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocFileSuite", 0 );
		}
		//Function test:test_trailing_space_in_test
		{
		IMethod methodtest_trailing_space_in_test34;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_trailing_space_in_test34 = ModelTestUtils.getAssertMethod( moduleChilds, "test_trailing_space_in_test", 0 );
		}
		//Function test:test_unittest_reportflags
		{
		IMethod methodtest_unittest_reportflags35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_unittest_reportflags35 = ModelTestUtils.getAssertMethod( moduleChilds, "test_unittest_reportflags", 0 );
		}
		//Function test:test_testfile
		{
		IMethod methodtest_testfile36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_testfile36 = ModelTestUtils.getAssertMethod( moduleChilds, "test_testfile", 0 );
		}
		//Function test:old_test1
		{
		IMethod methodold_test137;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test137 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test1", 0 );
		}
		//Function test:old_test2
		{
		IMethod methodold_test238;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test238 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test2", 0 );
		}
		//Function test:old_test3
		{
		IMethod methodold_test339;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test339 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test3", 0 );
		}
		//Function test:old_test4
		{
		IMethod methodold_test440;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test440 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test4", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main41 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}
		//Function test:test_coverage
		{
		IMethod methodtest_coverage42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_coverage42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_coverage", 1 );
			ModelTestUtils.assertParameterNames( methodtest_coverage42, new String[] {"coverdir"} );
		}

	}
	public void testModelGen71( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_list.py"));

		assertNotNull("Module test_list.py not found", module);
		assertEquals("test_list.py", module.getElementName());
		
		//Class test
		{
		IType classListTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classListTest0 = ModelTestUtils.getAssertClass( moduleChilds, "ListTest" );
			{
				IModelElement[] classListTest0Childs = classListTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classListTest0Childs, "type2test");
			}
			//Function test:test_truth
			{
			IMethod methodtest_truth1;
				IModelElement[] classListTest0Childs = classListTest0.getChildren();
				methodtest_truth1 = ModelTestUtils.getAssertMethod( classListTest0Childs, "test_truth", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truth1, new String[] {"self"} );
			}
			//Function test:test_identity
			{
			IMethod methodtest_identity2;
				IModelElement[] classListTest0Childs = classListTest0.getChildren();
				methodtest_identity2 = ModelTestUtils.getAssertMethod( classListTest0Childs, "test_identity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_identity2, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len3;
				IModelElement[] classListTest0Childs = classListTest0.getChildren();
				methodtest_len3 = ModelTestUtils.getAssertMethod( classListTest0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len3, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main4, new String[] {"verbose"} );
		}

	}
	public void testModelGen72( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pep247.py"));

		assertNotNull("Module test_pep247.py not found", module);
		assertEquals("test_pep247.py", module.getElementName());
		
		//Function test:check_hash_module
		{
		IMethod methodcheck_hash_module0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_hash_module0 = ModelTestUtils.getAssertMethod( moduleChilds, "check_hash_module", 2 );
			ModelTestUtils.assertParameterNames( methodcheck_hash_module0, new String[] {"module", "key"} );
		}

	}
	public void testModelGen73( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_struct.py"));

		assertNotNull("Module test_struct.py not found", module);
		assertEquals("test_struct.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ISBIGENDIAN");
		}
		//Function test:string_reverse
		{
		IMethod methodstring_reverse0;
			IModelElement[] moduleChilds = module.getChildren();
			methodstring_reverse0 = ModelTestUtils.getAssertMethod( moduleChilds, "string_reverse", 1 );
			ModelTestUtils.assertParameterNames( methodstring_reverse0, new String[] {"s"} );
		}
		//Function test:bigendian_to_native
		{
		IMethod methodbigendian_to_native1;
			IModelElement[] moduleChilds = module.getChildren();
			methodbigendian_to_native1 = ModelTestUtils.getAssertMethod( moduleChilds, "bigendian_to_native", 1 );
			ModelTestUtils.assertParameterNames( methodbigendian_to_native1, new String[] {"value"} );
		}
		//Function test:simple_err
		{
		IMethod methodsimple_err2;
			IModelElement[] moduleChilds = module.getChildren();
			methodsimple_err2 = ModelTestUtils.getAssertMethod( moduleChilds, "simple_err", 2 );
			ModelTestUtils.assertParameterNames( methodsimple_err2, new String[] {"func", "args"} );
		}
		//Function test:any_err
		{
		IMethod methodany_err3;
			IModelElement[] moduleChilds = module.getChildren();
			methodany_err3 = ModelTestUtils.getAssertMethod( moduleChilds, "any_err", 2 );
			ModelTestUtils.assertParameterNames( methodany_err3, new String[] {"func", "args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sz");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fmt");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fmt3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sz");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sz3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "b");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "h");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "i");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "l");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "format");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "res");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rev");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_native_qQ");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_native_qQ");
		}
		//Function test:test_native_qQ
		{
		IMethod methodtest_native_qQ4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_native_qQ4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_native_qQ", 0 );
		}
		//Class test
		{
		IType classIntTester5;
			IModelElement[] moduleChilds = module.getChildren();
			classIntTester5 = ModelTestUtils.getAssertClass( moduleChilds, "IntTester" );
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "BUGGY_RANGE_CHECK");
			}
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classIntTester5Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "formatpair", "bytesize"} );
			}
			//Function test:test_one
			{
			IMethod methodtest_one7;
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				methodtest_one7 = ModelTestUtils.getAssertMethod( classIntTester5Childs, "test_one", 5 );
				ModelTestUtils.assertParameterNames( methodtest_one7, new String[] {"self", "x", "pack", "unpack", "unhexlify"} );
			}
			//Function test:run
			{
			IMethod methodrun8;
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				methodrun8 = ModelTestUtils.getAssertMethod( classIntTester5Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun8, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}
		//Function test:test_p_code
		{
		IMethod methodtest_p_code9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_p_code9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_p_code", 0 );
		}
		//Function test:test_705836
		{
		IMethod methodtest_70583610;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_70583610 = ModelTestUtils.getAssertMethod( moduleChilds, "test_705836", 0 );
		}

	}
	public void testModelGen74( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pep263.py"));

		assertNotNull("Module test_pep263.py not found", module);
		assertEquals("test_pep263.py", module.getElementName());
		
		//Class test
		{
		IType classPEP263Test0;
			IModelElement[] moduleChilds = module.getChildren();
			classPEP263Test0 = ModelTestUtils.getAssertClass( moduleChilds, "PEP263Test" );
			//Function test:test_pep263
			{
			IMethod methodtest_pep2631;
				IModelElement[] classPEP263Test0Childs = classPEP263Test0.getChildren();
				methodtest_pep2631 = ModelTestUtils.getAssertMethod( classPEP263Test0Childs, "test_pep263", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pep2631, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen75( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("double_const.py"));

		assertNotNull("Module double_const.py not found", module);
		assertEquals("double_const.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PI");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TWOPI");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PI_str");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TWOPI_str");
		}
		//Function test:check_ok
		{
		IMethod methodcheck_ok0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_ok0 = ModelTestUtils.getAssertMethod( moduleChilds, "check_ok", 2 );
			ModelTestUtils.assertParameterNames( methodcheck_ok0, new String[] {"x", "x_str"} );
		}

	}
	public void testModelGen76( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pep277.py"));

		assertNotNull("Module test_pep277.py not found", module);
		assertEquals("test_pep277.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "filenames");
		}
		//Function test:deltree
		{
		IMethod methoddeltree0;
			IModelElement[] moduleChilds = module.getChildren();
			methoddeltree0 = ModelTestUtils.getAssertMethod( moduleChilds, "deltree", 1 );
			ModelTestUtils.assertParameterNames( methoddeltree0, new String[] {"dirname"} );
		}
		//Class test
		{
		IType classUnicodeFileTests1;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeFileTests1 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeFileTests" );
			{
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeFileTests1Childs, "files");
			}
			//Function test:setUp
			{
			IMethod methodsetUp2;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodsetUp2 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp2, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:_apply_failure
			{
			IMethod method_apply_failure4;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				method_apply_failure4 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "_apply_failure", 5 );
				ModelTestUtils.assertParameterNames( method_apply_failure4, new String[] {"self", "fn", "filename", "expected_exception", "check_fn_in_exception"} );
			}
			//Function test:test_failures
			{
			IMethod methodtest_failures5;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodtest_failures5 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "test_failures", 1 );
				ModelTestUtils.assertParameterNames( methodtest_failures5, new String[] {"self"} );
			}
			//Function test:test_open
			{
			IMethod methodtest_open6;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodtest_open6 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "test_open", 1 );
				ModelTestUtils.assertParameterNames( methodtest_open6, new String[] {"self"} );
			}
			//Function test:test_listdir
			{
			IMethod methodtest_listdir7;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodtest_listdir7 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "test_listdir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_listdir7, new String[] {"self"} );
			}
			//Function test:test_rename
			{
			IMethod methodtest_rename8;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodtest_rename8 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "test_rename", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rename8, new String[] {"self"} );
			}
			//Function test:test_directory
			{
			IMethod methodtest_directory9;
				IModelElement[] classUnicodeFileTests1Childs = classUnicodeFileTests1.getChildren();
				methodtest_directory9 = ModelTestUtils.getAssertMethod( classUnicodeFileTests1Childs, "test_directory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_directory9, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen77( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_zipfile.py"));

		assertNotNull("Module test_zipfile.py not found", module);
		assertEquals("test_zipfile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "zlib");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN2");
		}
		//Class test
		{
		IType classTestsWithSourceFile0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestsWithSourceFile0 = ModelTestUtils.getAssertClass( moduleChilds, "TestsWithSourceFile" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:zipTest
			{
			IMethod methodzipTest2;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodzipTest2 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "zipTest", 3 );
				ModelTestUtils.assertParameterNames( methodzipTest2, new String[] {"self", "f", "compression"} );
			}
			//Function test:testStored
			{
			IMethod methodtestStored3;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodtestStored3 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "testStored", 1 );
				ModelTestUtils.assertParameterNames( methodtestStored3, new String[] {"self"} );
			}
			//Function test:testDeflated
			{
			IMethod methodtestDeflated4;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodtestDeflated4 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "testDeflated", 1 );
				ModelTestUtils.assertParameterNames( methodtestDeflated4, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown5;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodtearDown5 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classOtherTests6;
			IModelElement[] moduleChilds = module.getChildren();
			classOtherTests6 = ModelTestUtils.getAssertClass( moduleChilds, "OtherTests" );
			//Function test:testCloseErroneousFile
			{
			IMethod methodtestCloseErroneousFile7;
				IModelElement[] classOtherTests6Childs = classOtherTests6.getChildren();
				methodtestCloseErroneousFile7 = ModelTestUtils.getAssertMethod( classOtherTests6Childs, "testCloseErroneousFile", 1 );
				ModelTestUtils.assertParameterNames( methodtestCloseErroneousFile7, new String[] {"self"} );
			}
			//Function test:testNonExistentFileRaisesIOError
			{
			IMethod methodtestNonExistentFileRaisesIOError8;
				IModelElement[] classOtherTests6Childs = classOtherTests6.getChildren();
				methodtestNonExistentFileRaisesIOError8 = ModelTestUtils.getAssertMethod( classOtherTests6Childs, "testNonExistentFileRaisesIOError", 1 );
				ModelTestUtils.assertParameterNames( methodtestNonExistentFileRaisesIOError8, new String[] {"self"} );
			}
			//Function test:testClosedZipRaisesRuntimeError
			{
			IMethod methodtestClosedZipRaisesRuntimeError9;
				IModelElement[] classOtherTests6Childs = classOtherTests6.getChildren();
				methodtestClosedZipRaisesRuntimeError9 = ModelTestUtils.getAssertMethod( classOtherTests6Childs, "testClosedZipRaisesRuntimeError", 1 );
				ModelTestUtils.assertParameterNames( methodtestClosedZipRaisesRuntimeError9, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen78( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pep292.py"));

		assertNotNull("Module test_pep292.py not found", module);
		assertEquals("test_pep292.py", module.getElementName());
		
		//Class test
		{
		IType classBag0;
			IModelElement[] moduleChilds = module.getChildren();
			classBag0 = ModelTestUtils.getAssertClass( moduleChilds, "Bag" );
		}
		//Class test
		{
		IType classMapping1;
			IModelElement[] moduleChilds = module.getChildren();
			classMapping1 = ModelTestUtils.getAssertClass( moduleChilds, "Mapping" );
			//Function test:__getitem__
			{
			IMethod method__getitem__2;
				IModelElement[] classMapping1Childs = classMapping1.getChildren();
				method__getitem__2 = ModelTestUtils.getAssertMethod( classMapping1Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__2, new String[] {"self", "name"} );
			}
		}
		//Class test
		{
		IType classTestTemplate3;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTemplate3 = ModelTestUtils.getAssertClass( moduleChilds, "TestTemplate" );
			//Function test:test_regular_templates
			{
			IMethod methodtest_regular_templates4;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_regular_templates4 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_regular_templates", 1 );
				ModelTestUtils.assertParameterNames( methodtest_regular_templates4, new String[] {"self"} );
			}
			//Function test:test_regular_templates_with_braces
			{
			IMethod methodtest_regular_templates_with_braces5;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_regular_templates_with_braces5 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_regular_templates_with_braces", 1 );
				ModelTestUtils.assertParameterNames( methodtest_regular_templates_with_braces5, new String[] {"self"} );
			}
			//Function test:test_escapes
			{
			IMethod methodtest_escapes6;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_escapes6 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_escapes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_escapes6, new String[] {"self"} );
			}
			//Function test:test_percents
			{
			IMethod methodtest_percents7;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_percents7 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_percents", 1 );
				ModelTestUtils.assertParameterNames( methodtest_percents7, new String[] {"self"} );
			}
			//Function test:test_stringification
			{
			IMethod methodtest_stringification8;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_stringification8 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_stringification", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stringification8, new String[] {"self"} );
			}
			//Function test:test_SafeTemplate
			{
			IMethod methodtest_SafeTemplate9;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_SafeTemplate9 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_SafeTemplate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_SafeTemplate9, new String[] {"self"} );
			}
			//Function test:test_invalid_placeholders
			{
			IMethod methodtest_invalid_placeholders10;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_invalid_placeholders10 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_invalid_placeholders", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_placeholders10, new String[] {"self"} );
			}
			//Function test:test_delimiter_override
			{
			IMethod methodtest_delimiter_override11;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_delimiter_override11 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_delimiter_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delimiter_override11, new String[] {"self"} );
				//Class test
				{
				IType classPieDelims12;
					IModelElement[] methodtest_delimiter_override11Childs = methodtest_delimiter_override11.getChildren();
					classPieDelims12 = ModelTestUtils.getAssertClass( methodtest_delimiter_override11Childs, "PieDelims" );
				}
			}
			//Function test:test_idpattern_override
			{
			IMethod methodtest_idpattern_override13;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_idpattern_override13 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_idpattern_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_idpattern_override13, new String[] {"self"} );
				//Class test
				{
				IType classPathPattern14;
					IModelElement[] methodtest_idpattern_override13Childs = methodtest_idpattern_override13.getChildren();
					classPathPattern14 = ModelTestUtils.getAssertClass( methodtest_idpattern_override13Childs, "PathPattern" );
				}
			}
			//Function test:test_pattern_override
			{
			IMethod methodtest_pattern_override15;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_pattern_override15 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_pattern_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pattern_override15, new String[] {"self"} );
				//Class test
				{
				IType classMyPattern16;
					IModelElement[] methodtest_pattern_override15Childs = methodtest_pattern_override15.getChildren();
					classMyPattern16 = ModelTestUtils.getAssertClass( methodtest_pattern_override15Childs, "MyPattern" );
				}
				//Class test
				{
				IType classBadPattern17;
					IModelElement[] methodtest_pattern_override15Childs = methodtest_pattern_override15.getChildren();
					classBadPattern17 = ModelTestUtils.getAssertClass( methodtest_pattern_override15Childs, "BadPattern" );
				}
			}
			//Function test:test_unicode_values
			{
			IMethod methodtest_unicode_values18;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_unicode_values18 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_unicode_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_values18, new String[] {"self"} );
			}
			//Function test:test_keyword_arguments
			{
			IMethod methodtest_keyword_arguments19;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_keyword_arguments19 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_keyword_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keyword_arguments19, new String[] {"self"} );
			}
			//Function test:test_keyword_arguments_safe
			{
			IMethod methodtest_keyword_arguments_safe20;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_keyword_arguments_safe20 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_keyword_arguments_safe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keyword_arguments_safe20, new String[] {"self"} );
			}
			//Function test:test_delimiter_override
			{
			IMethod methodtest_delimiter_override21;
				IModelElement[] classTestTemplate3Childs = classTestTemplate3.getChildren();
				methodtest_delimiter_override21 = ModelTestUtils.getAssertMethod( classTestTemplate3Childs, "test_delimiter_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delimiter_override21, new String[] {"self"} );
				//Class test
				{
				IType classAmpersandTemplate22;
					IModelElement[] methodtest_delimiter_override21Childs = methodtest_delimiter_override21.getChildren();
					classAmpersandTemplate22 = ModelTestUtils.getAssertClass( methodtest_delimiter_override21Childs, "AmpersandTemplate" );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen79( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_imageop.py"));

		assertNotNull("Module test_imageop.py not found", module);
		assertEquals("test_imageop.py", module.getElementName());
		
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 1 );
			ModelTestUtils.assertParameterNames( methodmain0, new String[] {"use_rgbimg"} );
		}
		//Function test:getrgbimage
		{
		IMethod methodgetrgbimage1;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetrgbimage1 = ModelTestUtils.getAssertMethod( moduleChilds, "getrgbimage", 1 );
			ModelTestUtils.assertParameterNames( methodgetrgbimage1, new String[] {"name"} );
		}
		//Function test:getimage
		{
		IMethod methodgetimage2;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetimage2 = ModelTestUtils.getAssertMethod( moduleChilds, "getimage", 1 );
			ModelTestUtils.assertParameterNames( methodgetimage2, new String[] {"name"} );
		}
		//Function test:get_qualified_path
		{
		IMethod methodget_qualified_path3;
			IModelElement[] moduleChilds = module.getChildren();
			methodget_qualified_path3 = ModelTestUtils.getAssertMethod( moduleChilds, "get_qualified_path", 1 );
			ModelTestUtils.assertParameterNames( methodget_qualified_path3, new String[] {"name"} );
		}

	}
	public void testModelGen80( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_transformer.py"));

		assertNotNull("Module test_transformer.py not found", module);
		assertEquals("test_transformer.py", module.getElementName());
		
		//Class test
		{
		IType classTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classTests0 = ModelTestUtils.getAssertClass( moduleChilds, "Tests" );
			//Function test:testMultipleLHS
			{
			IMethod methodtestMultipleLHS1;
				IModelElement[] classTests0Childs = classTests0.getChildren();
				methodtestMultipleLHS1 = ModelTestUtils.getAssertMethod( classTests0Childs, "testMultipleLHS", 1 );
				ModelTestUtils.assertParameterNames( methodtestMultipleLHS1, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen81( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_email.py"));

		assertNotNull("Module test_email.py not found", module);
		assertEquals("test_email.py", module.getElementName());
		
		//Function test:test_main
		{
		IMethod methodtest_main0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen82( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_importhooks.py"));

		assertNotNull("Module test_importhooks.py not found", module);
		assertEquals("test_importhooks.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_src");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "reload_src");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_co");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "reload_co");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_path");
		}
		//Class test
		{
		IType classImportTracker0;
			IModelElement[] moduleChilds = module.getChildren();
			classImportTracker0 = ModelTestUtils.getAssertClass( moduleChilds, "ImportTracker" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classImportTracker0Childs = classImportTracker0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classImportTracker0Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self"} );
			}
			//Function test:find_module
			{
			IMethod methodfind_module2;
				IModelElement[] classImportTracker0Childs = classImportTracker0.getChildren();
				methodfind_module2 = ModelTestUtils.getAssertMethod( classImportTracker0Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module2, new String[] {"self", "fullname", "path"} );
			}
		}
		//Class test
		{
		IType classTestImporter3;
			IModelElement[] moduleChilds = module.getChildren();
			classTestImporter3 = ModelTestUtils.getAssertClass( moduleChilds, "TestImporter" );
			{
				IModelElement[] classTestImporter3Childs = classTestImporter3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImporter3Childs, "modules");
			}
			//Function test:__init__
			{
			IMethod method__init__4;
				IModelElement[] classTestImporter3Childs = classTestImporter3.getChildren();
				method__init__4 = ModelTestUtils.getAssertMethod( classTestImporter3Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__4, new String[] {"self", "path"} );
			}
			//Function test:_get__path__
			{
			IMethod method_get__path__5;
				IModelElement[] classTestImporter3Childs = classTestImporter3.getChildren();
				method_get__path__5 = ModelTestUtils.getAssertMethod( classTestImporter3Childs, "_get__path__", 1 );
				ModelTestUtils.assertParameterNames( method_get__path__5, new String[] {"self"} );
			}
			//Function test:find_module
			{
			IMethod methodfind_module6;
				IModelElement[] classTestImporter3Childs = classTestImporter3.getChildren();
				methodfind_module6 = ModelTestUtils.getAssertMethod( classTestImporter3Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module6, new String[] {"self", "fullname", "path"} );
			}
			//Function test:load_module
			{
			IMethod methodload_module7;
				IModelElement[] classTestImporter3Childs = classTestImporter3.getChildren();
				methodload_module7 = ModelTestUtils.getAssertMethod( classTestImporter3Childs, "load_module", 2 );
				ModelTestUtils.assertParameterNames( methodload_module7, new String[] {"self", "fullname"} );
			}
		}
		//Class test
		{
		IType classMetaImporter8;
			IModelElement[] moduleChilds = module.getChildren();
			classMetaImporter8 = ModelTestUtils.getAssertClass( moduleChilds, "MetaImporter" );
			//Function test:_get__path__
			{
			IMethod method_get__path__9;
				IModelElement[] classMetaImporter8Childs = classMetaImporter8.getChildren();
				method_get__path__9 = ModelTestUtils.getAssertMethod( classMetaImporter8Childs, "_get__path__", 1 );
				ModelTestUtils.assertParameterNames( method_get__path__9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPathImporter10;
			IModelElement[] moduleChilds = module.getChildren();
			classPathImporter10 = ModelTestUtils.getAssertClass( moduleChilds, "PathImporter" );
			//Function test:_get__path__
			{
			IMethod method_get__path__11;
				IModelElement[] classPathImporter10Childs = classPathImporter10.getChildren();
				method_get__path__11 = ModelTestUtils.getAssertMethod( classPathImporter10Childs, "_get__path__", 1 );
				ModelTestUtils.assertParameterNames( method_get__path__11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classImportBlocker12;
			IModelElement[] moduleChilds = module.getChildren();
			classImportBlocker12 = ModelTestUtils.getAssertClass( moduleChilds, "ImportBlocker" );
			//Function test:__init__
			{
			IMethod method__init__13;
				IModelElement[] classImportBlocker12Childs = classImportBlocker12.getChildren();
				method__init__13 = ModelTestUtils.getAssertMethod( classImportBlocker12Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__13, new String[] {"self", "namestoblock"} );
			}
			//Function test:find_module
			{
			IMethod methodfind_module14;
				IModelElement[] classImportBlocker12Childs = classImportBlocker12.getChildren();
				methodfind_module14 = ModelTestUtils.getAssertMethod( classImportBlocker12Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module14, new String[] {"self", "fullname", "path"} );
			}
			//Function test:load_module
			{
			IMethod methodload_module15;
				IModelElement[] classImportBlocker12Childs = classImportBlocker12.getChildren();
				methodload_module15 = ModelTestUtils.getAssertMethod( classImportBlocker12Childs, "load_module", 2 );
				ModelTestUtils.assertParameterNames( methodload_module15, new String[] {"self", "fullname"} );
			}
		}
		//Class test
		{
		IType classImpWrapper16;
			IModelElement[] moduleChilds = module.getChildren();
			classImpWrapper16 = ModelTestUtils.getAssertClass( moduleChilds, "ImpWrapper" );
			//Function test:__init__
			{
			IMethod method__init__17;
				IModelElement[] classImpWrapper16Childs = classImpWrapper16.getChildren();
				method__init__17 = ModelTestUtils.getAssertMethod( classImpWrapper16Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__17, new String[] {"self", "path"} );
			}
			//Function test:find_module
			{
			IMethod methodfind_module18;
				IModelElement[] classImpWrapper16Childs = classImpWrapper16.getChildren();
				methodfind_module18 = ModelTestUtils.getAssertMethod( classImpWrapper16Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module18, new String[] {"self", "fullname", "path"} );
			}
		}
		//Class test
		{
		IType classImpLoader19;
			IModelElement[] moduleChilds = module.getChildren();
			classImpLoader19 = ModelTestUtils.getAssertClass( moduleChilds, "ImpLoader" );
			//Function test:__init__
			{
			IMethod method__init__20;
				IModelElement[] classImpLoader19Childs = classImpLoader19.getChildren();
				method__init__20 = ModelTestUtils.getAssertMethod( classImpLoader19Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__20, new String[] {"self", "file", "filename", "stuff"} );
			}
			//Function test:load_module
			{
			IMethod methodload_module21;
				IModelElement[] classImpLoader19Childs = classImpLoader19.getChildren();
				methodload_module21 = ModelTestUtils.getAssertMethod( classImpLoader19Childs, "load_module", 2 );
				ModelTestUtils.assertParameterNames( methodload_module21, new String[] {"self", "fullname"} );
			}
		}
		//Class test
		{
		IType classImportHooksBaseTestCase22;
			IModelElement[] moduleChilds = module.getChildren();
			classImportHooksBaseTestCase22 = ModelTestUtils.getAssertClass( moduleChilds, "ImportHooksBaseTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp23;
				IModelElement[] classImportHooksBaseTestCase22Childs = classImportHooksBaseTestCase22.getChildren();
				methodsetUp23 = ModelTestUtils.getAssertMethod( classImportHooksBaseTestCase22Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp23, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown24;
				IModelElement[] classImportHooksBaseTestCase22Childs = classImportHooksBaseTestCase22.getChildren();
				methodtearDown24 = ModelTestUtils.getAssertMethod( classImportHooksBaseTestCase22Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown24, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classImportHooksTestCase25;
			IModelElement[] moduleChilds = module.getChildren();
			classImportHooksTestCase25 = ModelTestUtils.getAssertClass( moduleChilds, "ImportHooksTestCase" );
			//Function test:doTestImports
			{
			IMethod methoddoTestImports26;
				IModelElement[] classImportHooksTestCase25Childs = classImportHooksTestCase25.getChildren();
				methoddoTestImports26 = ModelTestUtils.getAssertMethod( classImportHooksTestCase25Childs, "doTestImports", 2 );
				ModelTestUtils.assertParameterNames( methoddoTestImports26, new String[] {"self", "importer"} );
			}
			//Function test:testMetaPath
			{
			IMethod methodtestMetaPath27;
				IModelElement[] classImportHooksTestCase25Childs = classImportHooksTestCase25.getChildren();
				methodtestMetaPath27 = ModelTestUtils.getAssertMethod( classImportHooksTestCase25Childs, "testMetaPath", 1 );
				ModelTestUtils.assertParameterNames( methodtestMetaPath27, new String[] {"self"} );
			}
			//Function test:testPathHook
			{
			IMethod methodtestPathHook28;
				IModelElement[] classImportHooksTestCase25Childs = classImportHooksTestCase25.getChildren();
				methodtestPathHook28 = ModelTestUtils.getAssertMethod( classImportHooksTestCase25Childs, "testPathHook", 1 );
				ModelTestUtils.assertParameterNames( methodtestPathHook28, new String[] {"self"} );
			}
			//Function test:testBlocker
			{
			IMethod methodtestBlocker29;
				IModelElement[] classImportHooksTestCase25Childs = classImportHooksTestCase25.getChildren();
				methodtestBlocker29 = ModelTestUtils.getAssertMethod( classImportHooksTestCase25Childs, "testBlocker", 1 );
				ModelTestUtils.assertParameterNames( methodtestBlocker29, new String[] {"self"} );
			}
			//Function test:testImpWrapper
			{
			IMethod methodtestImpWrapper30;
				IModelElement[] classImportHooksTestCase25Childs = classImportHooksTestCase25.getChildren();
				methodtestImpWrapper30 = ModelTestUtils.getAssertMethod( classImportHooksTestCase25Childs, "testImpWrapper", 1 );
				ModelTestUtils.assertParameterNames( methodtestImpWrapper30, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen83( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_types.py"));

		assertNotNull("Module test_types.py not found", module);
		assertEquals("test_types.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
		}
		//Class test
		{
		IType classC1;
			IModelElement[] moduleChilds = module.getChildren();
			classC1 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "m");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "j");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prod");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "j");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prod");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "m");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "j");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prod");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "b");
		}

	}
	public void testModelGen84( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_timing.py"));

		assertNotNull("Module test_timing.py not found", module);
		assertEquals("test_timing.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "r");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "secs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "milli");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "micro");
		}

	}
	public void testModelGen85( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_site.py"));

		assertNotNull("Module test_site.py not found", module);
		assertEquals("test_site.py", module.getElementName());
		
		//Class test
		{
		IType classHelperFunctionsTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classHelperFunctionsTests0 = ModelTestUtils.getAssertClass( moduleChilds, "HelperFunctionsTests" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_makepath
			{
			IMethod methodtest_makepath3;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_makepath3 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_makepath", 1 );
				ModelTestUtils.assertParameterNames( methodtest_makepath3, new String[] {"self"} );
			}
			//Function test:test_init_pathinfo
			{
			IMethod methodtest_init_pathinfo4;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_init_pathinfo4 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_init_pathinfo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init_pathinfo4, new String[] {"self"} );
			}
			//Function test:pth_file_tests
			{
			IMethod methodpth_file_tests5;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodpth_file_tests5 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "pth_file_tests", 2 );
				ModelTestUtils.assertParameterNames( methodpth_file_tests5, new String[] {"self", "pth_file"} );
			}
			//Function test:test_addpackage
			{
			IMethod methodtest_addpackage6;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_addpackage6 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_addpackage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addpackage6, new String[] {"self"} );
			}
			//Function test:test_addsitedir
			{
			IMethod methodtest_addsitedir7;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_addsitedir7 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_addsitedir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addsitedir7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPthFile8;
			IModelElement[] moduleChilds = module.getChildren();
			classPthFile8 = ModelTestUtils.getAssertClass( moduleChilds, "PthFile" );
			//Function test:__init__
			{
			IMethod method__init__9;
				IModelElement[] classPthFile8Childs = classPthFile8.getChildren();
				method__init__9 = ModelTestUtils.getAssertMethod( classPthFile8Childs, "__init__", 5 );
				ModelTestUtils.assertParameterNames( method__init__9, new String[] {"self", "filename_base", "imported", "good_dirname", "bad_dirname"} );
			}
			//Function test:create
			{
			IMethod methodcreate10;
				IModelElement[] classPthFile8Childs = classPthFile8.getChildren();
				methodcreate10 = ModelTestUtils.getAssertMethod( classPthFile8Childs, "create", 1 );
				ModelTestUtils.assertParameterNames( methodcreate10, new String[] {"self"} );
			}
			//Function test:cleanup
			{
			IMethod methodcleanup11;
				IModelElement[] classPthFile8Childs = classPthFile8.getChildren();
				methodcleanup11 = ModelTestUtils.getAssertMethod( classPthFile8Childs, "cleanup", 2 );
				ModelTestUtils.assertParameterNames( methodcleanup11, new String[] {"self", "prep"} );
			}
		}
		//Class test
		{
		IType classImportSideEffectTests12;
			IModelElement[] moduleChilds = module.getChildren();
			classImportSideEffectTests12 = ModelTestUtils.getAssertClass( moduleChilds, "ImportSideEffectTests" );
			//Function test:setUp
			{
			IMethod methodsetUp13;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodsetUp13 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp13, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown14;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtearDown14 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown14, new String[] {"self"} );
			}
			//Function test:test_abs__file__
			{
			IMethod methodtest_abs__file__15;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_abs__file__15 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_abs__file__", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs__file__15, new String[] {"self"} );
			}
			//Function test:test_no_duplicate_paths
			{
			IMethod methodtest_no_duplicate_paths16;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_no_duplicate_paths16 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_no_duplicate_paths", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_duplicate_paths16, new String[] {"self"} );
			}
			//Function test:test_add_build_dir
			{
			IMethod methodtest_add_build_dir17;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_add_build_dir17 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_add_build_dir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_build_dir17, new String[] {"self"} );
			}
			//Function test:test_setting_quit
			{
			IMethod methodtest_setting_quit18;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_setting_quit18 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_setting_quit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setting_quit18, new String[] {"self"} );
			}
			//Function test:test_setting_copyright
			{
			IMethod methodtest_setting_copyright19;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_setting_copyright19 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_setting_copyright", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setting_copyright19, new String[] {"self"} );
			}
			//Function test:test_setting_help
			{
			IMethod methodtest_setting_help20;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_setting_help20 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_setting_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setting_help20, new String[] {"self"} );
			}
			//Function test:test_aliasing_mbcs
			{
			IMethod methodtest_aliasing_mbcs21;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_aliasing_mbcs21 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_aliasing_mbcs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_aliasing_mbcs21, new String[] {"self"} );
			}
			//Function test:test_setdefaultencoding_removed
			{
			IMethod methodtest_setdefaultencoding_removed22;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_setdefaultencoding_removed22 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_setdefaultencoding_removed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefaultencoding_removed22, new String[] {"self"} );
			}
			//Function test:test_sitecustomize_executed
			{
			IMethod methodtest_sitecustomize_executed23;
				IModelElement[] classImportSideEffectTests12Childs = classImportSideEffectTests12.getChildren();
				methodtest_sitecustomize_executed23 = ModelTestUtils.getAssertMethod( classImportSideEffectTests12Childs, "test_sitecustomize_executed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sitecustomize_executed23, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main24 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen86( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("cjkencodings_test.py"));

		assertNotNull("Module cjkencodings_test.py not found", module);
		assertEquals("cjkencodings_test.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "teststring");
		}

	}
	public void testModelGen87( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("autotest.py"));

		assertNotNull("Module autotest.py not found", module);
		assertEquals("autotest.py", module.getElementName());
		

	}
	public void testModelGen88( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_profile.py"));

		assertNotNull("Module test_profile.py not found", module);
		assertEquals("test_profile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ticks");
		}
		//Function test:test_main
		{
		IMethod methodtest_main0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}
		//Function test:timer
		{
		IMethod methodtimer1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtimer1 = ModelTestUtils.getAssertMethod( moduleChilds, "timer", 0 );
		}
		//Function test:testfunc
		{
		IMethod methodtestfunc2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestfunc2 = ModelTestUtils.getAssertMethod( moduleChilds, "testfunc", 0 );
		}
		//Function test:helper
		{
		IMethod methodhelper3;
			IModelElement[] moduleChilds = module.getChildren();
			methodhelper3 = ModelTestUtils.getAssertMethod( moduleChilds, "helper", 0 );
		}
		//Function test:helper1
		{
		IMethod methodhelper14;
			IModelElement[] moduleChilds = module.getChildren();
			methodhelper14 = ModelTestUtils.getAssertMethod( moduleChilds, "helper1", 0 );
		}
		//Function test:helper2
		{
		IMethod methodhelper25;
			IModelElement[] moduleChilds = module.getChildren();
			methodhelper25 = ModelTestUtils.getAssertMethod( moduleChilds, "helper2", 0 );
		}
		//Function test:subhelper
		{
		IMethod methodsubhelper6;
			IModelElement[] moduleChilds = module.getChildren();
			methodsubhelper6 = ModelTestUtils.getAssertMethod( moduleChilds, "subhelper", 0 );
		}
		//Class test
		{
		IType classC7;
			IModelElement[] moduleChilds = module.getChildren();
			classC7 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:__getattr__
			{
			IMethod method__getattr__8;
				IModelElement[] classC7Childs = classC7.getChildren();
				method__getattr__8 = ModelTestUtils.getAssertMethod( classC7Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__8, new String[] {"self", "name"} );
			}
		}
		//Function test:test_2
		{
		IMethod methodtest_29;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_29 = ModelTestUtils.getAssertMethod( moduleChilds, "test_2", 0 );
			//Function test:testfunc
			{
			IMethod methodtestfunc10;
				IModelElement[] methodtest_29Childs = methodtest_29.getChildren();
				methodtestfunc10 = ModelTestUtils.getAssertMethod( methodtest_29Childs, "testfunc", 0 );
			}
		}

	}
	public void testModelGen89( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("sample_doctest.py"));

		assertNotNull("Module sample_doctest.py not found", module);
		assertEquals("sample_doctest.py", module.getElementName());
		
		//Function test:foo
		{
		IMethod methodfoo0;
			IModelElement[] moduleChilds = module.getChildren();
			methodfoo0 = ModelTestUtils.getAssertMethod( moduleChilds, "foo", 0 );
		}
		//Function test:bar
		{
		IMethod methodbar1;
			IModelElement[] moduleChilds = module.getChildren();
			methodbar1 = ModelTestUtils.getAssertMethod( moduleChilds, "bar", 0 );
		}
		//Function test:test_silly_setup
		{
		IMethod methodtest_silly_setup2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_silly_setup2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_silly_setup", 0 );
		}
		//Function test:w_blank
		{
		IMethod methodw_blank3;
			IModelElement[] moduleChilds = module.getChildren();
			methodw_blank3 = ModelTestUtils.getAssertMethod( moduleChilds, "w_blank", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:x_is_one
		{
		IMethod methodx_is_one4;
			IModelElement[] moduleChilds = module.getChildren();
			methodx_is_one4 = ModelTestUtils.getAssertMethod( moduleChilds, "x_is_one", 0 );
		}
		//Function test:y_is_one
		{
		IMethod methody_is_one5;
			IModelElement[] moduleChilds = module.getChildren();
			methody_is_one5 = ModelTestUtils.getAssertMethod( moduleChilds, "y_is_one", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}
		//Function test:test_suite
		{
		IMethod methodtest_suite6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_suite6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_suite", 0 );
		}

	}
	public void testModelGen90( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_math.py"));

		assertNotNull("Module test_math.py not found", module);
		assertEquals("test_math.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "seps");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "eps");
		}
		//Function test:testit
		{
		IMethod methodtestit0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestit0 = ModelTestUtils.getAssertMethod( moduleChilds, "testit", 3 );
			ModelTestUtils.assertParameterNames( methodtestit0, new String[] {"name", "value", "expected"} );
		}
		//Function test:testfrexp
		{
		IMethod methodtestfrexp1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestfrexp1 = ModelTestUtils.getAssertMethod( moduleChilds, "testfrexp", 3 );
			ModelTestUtils.assertParameterNames( methodtestfrexp1, new String[] {"name", "('mant', 'exp')", "('emant', 'eexp')"} );
		}
		//Function test:testmodf
		{
		IMethod methodtestmodf2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestmodf2 = ModelTestUtils.getAssertMethod( moduleChilds, "testmodf", 3 );
			ModelTestUtils.assertParameterNames( methodtestmodf2, new String[] {"name", "('v1', 'v2')", "('e1', 'e2')"} );
		}
		//Function test:test_exceptions
		{
		IMethod methodtest_exceptions3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_exceptions3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_exceptions", 0 );
		}

	}
	public void testModelGen91( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_softspace.py"));

		assertNotNull("Module test_softspace.py not found", module);
		assertEquals("test_softspace.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		//Class test
		{
		IType classC0;
			IModelElement[] moduleChilds = module.getChildren();
			classC0 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:__str__
			{
			IMethod method__str__1;
				IModelElement[] classC0Childs = classC0.getChildren();
				method__str__1 = ModelTestUtils.getAssertMethod( classC0Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__1, new String[] {"self"} );
			}
		}

	}
	public void testModelGen92( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("pyclbr_input.py"));

		assertNotNull("Module pyclbr_input.py not found", module);
		assertEquals("pyclbr_input.py", module.getElementName());
		
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
		}
		//Class test
		{
		IType classOther1;
			IModelElement[] moduleChilds = module.getChildren();
			classOther1 = ModelTestUtils.getAssertClass( moduleChilds, "Other" );
			//Function test:foo
			{
			IMethod methodfoo2;
				IModelElement[] classOther1Childs = classOther1.getChildren();
				methodfoo2 = ModelTestUtils.getAssertMethod( classOther1Childs, "foo", 1 );
				ModelTestUtils.assertParameterNames( methodfoo2, new String[] {"c"} );
			}
			//Function test:om
			{
			IMethod methodom3;
				IModelElement[] classOther1Childs = classOther1.getChildren();
				methodom3 = ModelTestUtils.getAssertMethod( classOther1Childs, "om", 1 );
				ModelTestUtils.assertParameterNames( methodom3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classB4;
			IModelElement[] moduleChilds = module.getChildren();
			classB4 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
			//Function test:bm
			{
			IMethod methodbm5;
				IModelElement[] classB4Childs = classB4.getChildren();
				methodbm5 = ModelTestUtils.getAssertMethod( classB4Childs, "bm", 1 );
				ModelTestUtils.assertParameterNames( methodbm5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC6;
			IModelElement[] moduleChilds = module.getChildren();
			classC6 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			{
				IModelElement[] classC6Childs = classC6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC6Childs, "foo");
			}
			{
				IModelElement[] classC6Childs = classC6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC6Childs, "om");
			}
			{
				IModelElement[] classC6Childs = classC6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC6Childs, "d");
			}
			//Function test:m
			{
			IMethod methodm7;
				IModelElement[] classC6Childs = classC6.getChildren();
				methodm7 = ModelTestUtils.getAssertMethod( classC6Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm7, new String[] {"self"} );
			}
			//Function test:sm
			{
			IMethod methodsm8;
				IModelElement[] classC6Childs = classC6.getChildren();
				methodsm8 = ModelTestUtils.getAssertMethod( classC6Childs, "sm", 1 );
				ModelTestUtils.assertParameterNames( methodsm8, new String[] {"self"} );
			}
			//Function test:cm
			{
			IMethod methodcm9;
				IModelElement[] classC6Childs = classC6.getChildren();
				methodcm9 = ModelTestUtils.getAssertMethod( classC6Childs, "cm", 1 );
				ModelTestUtils.assertParameterNames( methodcm9, new String[] {"self"} );
			}
		}

	}
	public void testModelGen93( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_colorsys.py"));

		assertNotNull("Module test_colorsys.py not found", module);
		assertEquals("test_colorsys.py", module.getElementName());
		
		//Function test:frange
		{
		IMethod methodfrange0;
			IModelElement[] moduleChilds = module.getChildren();
			methodfrange0 = ModelTestUtils.getAssertMethod( moduleChilds, "frange", 3 );
			ModelTestUtils.assertParameterNames( methodfrange0, new String[] {"start", "stop", "step"} );
		}
		//Class test
		{
		IType classColorsysTest1;
			IModelElement[] moduleChilds = module.getChildren();
			classColorsysTest1 = ModelTestUtils.getAssertClass( moduleChilds, "ColorsysTest" );
			//Function test:assertTripleEqual
			{
			IMethod methodassertTripleEqual2;
				IModelElement[] classColorsysTest1Childs = classColorsysTest1.getChildren();
				methodassertTripleEqual2 = ModelTestUtils.getAssertMethod( classColorsysTest1Childs, "assertTripleEqual", 3 );
				ModelTestUtils.assertParameterNames( methodassertTripleEqual2, new String[] {"self", "tr1", "tr2"} );
			}
			//Function test:test_hsv_roundtrip
			{
			IMethod methodtest_hsv_roundtrip3;
				IModelElement[] classColorsysTest1Childs = classColorsysTest1.getChildren();
				methodtest_hsv_roundtrip3 = ModelTestUtils.getAssertMethod( classColorsysTest1Childs, "test_hsv_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hsv_roundtrip3, new String[] {"self"} );
			}
			//Function test:test_hsv_values
			{
			IMethod methodtest_hsv_values4;
				IModelElement[] classColorsysTest1Childs = classColorsysTest1.getChildren();
				methodtest_hsv_values4 = ModelTestUtils.getAssertMethod( classColorsysTest1Childs, "test_hsv_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hsv_values4, new String[] {"self"} );
			}
			//Function test:test_hls_roundtrip
			{
			IMethod methodtest_hls_roundtrip5;
				IModelElement[] classColorsysTest1Childs = classColorsysTest1.getChildren();
				methodtest_hls_roundtrip5 = ModelTestUtils.getAssertMethod( classColorsysTest1Childs, "test_hls_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hls_roundtrip5, new String[] {"self"} );
			}
			//Function test:test_hls_values
			{
			IMethod methodtest_hls_values6;
				IModelElement[] classColorsysTest1Childs = classColorsysTest1.getChildren();
				methodtest_hls_values6 = ModelTestUtils.getAssertMethod( classColorsysTest1Childs, "test_hls_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hls_values6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen94( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_zlib.py"));

		assertNotNull("Module test_zlib.py not found", module);
		assertEquals("test_zlib.py", module.getElementName());
		
		//Function test:getbuf
		{
		IMethod methodgetbuf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetbuf0 = ModelTestUtils.getAssertMethod( moduleChilds, "getbuf", 0 );
		}
		//Class test
		{
		IType classChecksumTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classChecksumTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "ChecksumTestCase" );
			//Function test:test_crc32start
			{
			IMethod methodtest_crc32start2;
				IModelElement[] classChecksumTestCase1Childs = classChecksumTestCase1.getChildren();
				methodtest_crc32start2 = ModelTestUtils.getAssertMethod( classChecksumTestCase1Childs, "test_crc32start", 1 );
				ModelTestUtils.assertParameterNames( methodtest_crc32start2, new String[] {"self"} );
			}
			//Function test:test_crc32empty
			{
			IMethod methodtest_crc32empty3;
				IModelElement[] classChecksumTestCase1Childs = classChecksumTestCase1.getChildren();
				methodtest_crc32empty3 = ModelTestUtils.getAssertMethod( classChecksumTestCase1Childs, "test_crc32empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_crc32empty3, new String[] {"self"} );
			}
			//Function test:test_adler32start
			{
			IMethod methodtest_adler32start4;
				IModelElement[] classChecksumTestCase1Childs = classChecksumTestCase1.getChildren();
				methodtest_adler32start4 = ModelTestUtils.getAssertMethod( classChecksumTestCase1Childs, "test_adler32start", 1 );
				ModelTestUtils.assertParameterNames( methodtest_adler32start4, new String[] {"self"} );
			}
			//Function test:test_adler32empty
			{
			IMethod methodtest_adler32empty5;
				IModelElement[] classChecksumTestCase1Childs = classChecksumTestCase1.getChildren();
				methodtest_adler32empty5 = ModelTestUtils.getAssertMethod( classChecksumTestCase1Childs, "test_adler32empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_adler32empty5, new String[] {"self"} );
			}
			//Function test:assertEqual32
			{
			IMethod methodassertEqual326;
				IModelElement[] classChecksumTestCase1Childs = classChecksumTestCase1.getChildren();
				methodassertEqual326 = ModelTestUtils.getAssertMethod( classChecksumTestCase1Childs, "assertEqual32", 3 );
				ModelTestUtils.assertParameterNames( methodassertEqual326, new String[] {"self", "seen", "expected"} );
			}
			//Function test:test_penguins
			{
			IMethod methodtest_penguins7;
				IModelElement[] classChecksumTestCase1Childs = classChecksumTestCase1.getChildren();
				methodtest_penguins7 = ModelTestUtils.getAssertMethod( classChecksumTestCase1Childs, "test_penguins", 1 );
				ModelTestUtils.assertParameterNames( methodtest_penguins7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classExceptionTestCase8;
			IModelElement[] moduleChilds = module.getChildren();
			classExceptionTestCase8 = ModelTestUtils.getAssertClass( moduleChilds, "ExceptionTestCase" );
			//Function test:test_bigbits
			{
			IMethod methodtest_bigbits9;
				IModelElement[] classExceptionTestCase8Childs = classExceptionTestCase8.getChildren();
				methodtest_bigbits9 = ModelTestUtils.getAssertMethod( classExceptionTestCase8Childs, "test_bigbits", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigbits9, new String[] {"self"} );
			}
			//Function test:test_badcompressobj
			{
			IMethod methodtest_badcompressobj10;
				IModelElement[] classExceptionTestCase8Childs = classExceptionTestCase8.getChildren();
				methodtest_badcompressobj10 = ModelTestUtils.getAssertMethod( classExceptionTestCase8Childs, "test_badcompressobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badcompressobj10, new String[] {"self"} );
			}
			//Function test:test_baddecompressobj
			{
			IMethod methodtest_baddecompressobj11;
				IModelElement[] classExceptionTestCase8Childs = classExceptionTestCase8.getChildren();
				methodtest_baddecompressobj11 = ModelTestUtils.getAssertMethod( classExceptionTestCase8Childs, "test_baddecompressobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_baddecompressobj11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCompressTestCase12;
			IModelElement[] moduleChilds = module.getChildren();
			classCompressTestCase12 = ModelTestUtils.getAssertClass( moduleChilds, "CompressTestCase" );
			//Function test:test_speech
			{
			IMethod methodtest_speech13;
				IModelElement[] classCompressTestCase12Childs = classCompressTestCase12.getChildren();
				methodtest_speech13 = ModelTestUtils.getAssertMethod( classCompressTestCase12Childs, "test_speech", 1 );
				ModelTestUtils.assertParameterNames( methodtest_speech13, new String[] {"self"} );
			}
			//Function test:test_speech128
			{
			IMethod methodtest_speech12814;
				IModelElement[] classCompressTestCase12Childs = classCompressTestCase12.getChildren();
				methodtest_speech12814 = ModelTestUtils.getAssertMethod( classCompressTestCase12Childs, "test_speech128", 1 );
				ModelTestUtils.assertParameterNames( methodtest_speech12814, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCompressObjectTestCase15;
			IModelElement[] moduleChilds = module.getChildren();
			classCompressObjectTestCase15 = ModelTestUtils.getAssertClass( moduleChilds, "CompressObjectTestCase" );
			//Function test:test_pair
			{
			IMethod methodtest_pair16;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_pair16 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_pair", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pair16, new String[] {"self"} );
			}
			//Function test:test_compressoptions
			{
			IMethod methodtest_compressoptions17;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_compressoptions17 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_compressoptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compressoptions17, new String[] {"self"} );
			}
			//Function test:test_compressincremental
			{
			IMethod methodtest_compressincremental18;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_compressincremental18 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_compressincremental", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compressincremental18, new String[] {"self"} );
			}
			//Function test:test_decompinc
			{
			IMethod methodtest_decompinc19;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_decompinc19 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_decompinc", 5 );
				ModelTestUtils.assertParameterNames( methodtest_decompinc19, new String[] {"self", "flush", "source", "cx", "dcx"} );
			}
			//Function test:test_decompincflush
			{
			IMethod methodtest_decompincflush20;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_decompincflush20 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_decompincflush", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decompincflush20, new String[] {"self"} );
			}
			//Function test:test_decompimax
			{
			IMethod methodtest_decompimax21;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_decompimax21 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_decompimax", 4 );
				ModelTestUtils.assertParameterNames( methodtest_decompimax21, new String[] {"self", "source", "cx", "dcx"} );
			}
			//Function test:test_decompressmaxlen
			{
			IMethod methodtest_decompressmaxlen22;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_decompressmaxlen22 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_decompressmaxlen", 2 );
				ModelTestUtils.assertParameterNames( methodtest_decompressmaxlen22, new String[] {"self", "flush"} );
			}
			//Function test:test_decompressmaxlenflush
			{
			IMethod methodtest_decompressmaxlenflush23;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_decompressmaxlenflush23 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_decompressmaxlenflush", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decompressmaxlenflush23, new String[] {"self"} );
			}
			//Function test:test_maxlenmisc
			{
			IMethod methodtest_maxlenmisc24;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_maxlenmisc24 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_maxlenmisc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maxlenmisc24, new String[] {"self"} );
			}
			//Function test:test_flushes
			{
			IMethod methodtest_flushes25;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_flushes25 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_flushes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_flushes25, new String[] {"self"} );
			}
			//Function test:test_odd_flush
			{
			IMethod methodtest_odd_flush26;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_odd_flush26 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_odd_flush", 1 );
				ModelTestUtils.assertParameterNames( methodtest_odd_flush26, new String[] {"self"} );
			}
			//Function test:test_empty_flush
			{
			IMethod methodtest_empty_flush27;
				IModelElement[] classCompressObjectTestCase15Childs = classCompressObjectTestCase15.getChildren();
				methodtest_empty_flush27 = ModelTestUtils.getAssertMethod( classCompressObjectTestCase15Childs, "test_empty_flush", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_flush27, new String[] {"self"} );
			}
		}
		//Function test:genblock
		{
		IMethod methodgenblock28;
			IModelElement[] moduleChilds = module.getChildren();
			methodgenblock28 = ModelTestUtils.getAssertMethod( moduleChilds, "genblock", 4 );
			ModelTestUtils.assertParameterNames( methodgenblock28, new String[] {"seed", "length", "step", "generator"} );
		}
		//Function test:choose_lines
		{
		IMethod methodchoose_lines29;
			IModelElement[] moduleChilds = module.getChildren();
			methodchoose_lines29 = ModelTestUtils.getAssertMethod( moduleChilds, "choose_lines", 4 );
			ModelTestUtils.assertParameterNames( methodchoose_lines29, new String[] {"source", "number", "seed", "generator"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "HAMLET_SCENE");
		}
		//Function test:test_main
		{
		IMethod methodtest_main30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}
		//Function test:test
		{
		IMethod methodtest31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest31 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 1 );
			ModelTestUtils.assertParameterNames( methodtest31, new String[] {"tests"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "su");
		}

	}
	public void testModelGen95( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_aepack.py"));

		assertNotNull("Module test_aepack.py not found", module);
		assertEquals("test_aepack.py", module.getElementName());
		
		//Class test
		{
		IType classTestAepack0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestAepack0 = ModelTestUtils.getAssertClass( moduleChilds, "TestAepack" );
			{
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestAepack0Childs, "OBJECTS");
			}
			//Function test:test_roundtrip_string
			{
			IMethod methodtest_roundtrip_string1;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_string1 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_string1, new String[] {"self"} );
			}
			//Function test:test_roundtrip_int
			{
			IMethod methodtest_roundtrip_int2;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_int2 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_int2, new String[] {"self"} );
			}
			//Function test:test_roundtrip_float
			{
			IMethod methodtest_roundtrip_float3;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_float3 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_float3, new String[] {"self"} );
			}
			//Function test:test_roundtrip_None
			{
			IMethod methodtest_roundtrip_None4;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_None4 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_None", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_None4, new String[] {"self"} );
			}
			//Function test:test_roundtrip_aeobjects
			{
			IMethod methodtest_roundtrip_aeobjects5;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_aeobjects5 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_aeobjects", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_aeobjects5, new String[] {"self"} );
			}
			//Function test:test_roundtrip_FSSpec
			{
			IMethod methodtest_roundtrip_FSSpec6;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_FSSpec6 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_FSSpec", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_FSSpec6, new String[] {"self"} );
			}
			//Function test:test_roundtrip_Alias
			{
			IMethod methodtest_roundtrip_Alias7;
				IModelElement[] classTestAepack0Childs = classTestAepack0.getChildren();
				methodtest_roundtrip_Alias7 = ModelTestUtils.getAssertMethod( classTestAepack0Childs, "test_roundtrip_Alias", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_Alias7, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen96( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_long.py"));

		assertNotNull("Module test_long.py not found", module);
		assertEquals("test_long.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SHIFT");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "BASE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MASK");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "KARATSUBA_CUTOFF");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MAXDIGITS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "special");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "p2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "p2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "special");
		}
		//Function test:check
		{
		IMethod methodcheck0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck0 = ModelTestUtils.getAssertMethod( moduleChilds, "check", 2 );
			ModelTestUtils.assertParameterNames( methodcheck0, new String[] {"ok", "args"} );
		}
		//Function test:getran
		{
		IMethod methodgetran1;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetran1 = ModelTestUtils.getAssertMethod( moduleChilds, "getran", 1 );
			ModelTestUtils.assertParameterNames( methodgetran1, new String[] {"ndigits"} );
		}
		//Function test:getran2
		{
		IMethod methodgetran22;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetran22 = ModelTestUtils.getAssertMethod( moduleChilds, "getran2", 1 );
			ModelTestUtils.assertParameterNames( methodgetran22, new String[] {"ndigits"} );
		}
		//Function test:test_division_2
		{
		IMethod methodtest_division_23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_division_23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_division_2", 2 );
			ModelTestUtils.assertParameterNames( methodtest_division_23, new String[] {"x", "y"} );
		}
		//Function test:test_division
		{
		IMethod methodtest_division4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_division4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_division", 1 );
			ModelTestUtils.assertParameterNames( methodtest_division4, new String[] {"maxdigits"} );
		}
		//Function test:test_karatsuba
		{
		IMethod methodtest_karatsuba5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_karatsuba5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_karatsuba", 0 );
		}
		//Function test:test_bitop_identities_1
		{
		IMethod methodtest_bitop_identities_16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bitop_identities_16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bitop_identities_1", 1 );
			ModelTestUtils.assertParameterNames( methodtest_bitop_identities_16, new String[] {"x"} );
		}
		//Function test:test_bitop_identities_2
		{
		IMethod methodtest_bitop_identities_27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bitop_identities_27 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bitop_identities_2", 2 );
			ModelTestUtils.assertParameterNames( methodtest_bitop_identities_27, new String[] {"x", "y"} );
		}
		//Function test:test_bitop_identities_3
		{
		IMethod methodtest_bitop_identities_38;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bitop_identities_38 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bitop_identities_3", 3 );
			ModelTestUtils.assertParameterNames( methodtest_bitop_identities_38, new String[] {"x", "y", "z"} );
		}
		//Function test:test_bitop_identities
		{
		IMethod methodtest_bitop_identities9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bitop_identities9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bitop_identities", 1 );
			ModelTestUtils.assertParameterNames( methodtest_bitop_identities9, new String[] {"maxdigits"} );
		}
		//Function test:slow_format
		{
		IMethod methodslow_format10;
			IModelElement[] moduleChilds = module.getChildren();
			methodslow_format10 = ModelTestUtils.getAssertMethod( moduleChilds, "slow_format", 2 );
			ModelTestUtils.assertParameterNames( methodslow_format10, new String[] {"x", "base"} );
		}
		//Function test:test_format_1
		{
		IMethod methodtest_format_111;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_format_111 = ModelTestUtils.getAssertMethod( moduleChilds, "test_format_1", 1 );
			ModelTestUtils.assertParameterNames( methodtest_format_111, new String[] {"x"} );
		}
		//Function test:test_format
		{
		IMethod methodtest_format12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_format12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_format", 1 );
			ModelTestUtils.assertParameterNames( methodtest_format12, new String[] {"maxdigits"} );
		}
		//Function test:test_misc
		{
		IMethod methodtest_misc13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_misc13 = ModelTestUtils.getAssertMethod( moduleChilds, "test_misc", 1 );
			ModelTestUtils.assertParameterNames( methodtest_misc13, new String[] {"maxdigits"} );
			//Class test
			{
			IType classlong214;
				IModelElement[] methodtest_misc13Childs = methodtest_misc13.getChildren();
				classlong214 = ModelTestUtils.getAssertClass( methodtest_misc13Childs, "long2" );
			}
		}
		//Function test:test_auto_overflow
		{
		IMethod methodtest_auto_overflow15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_auto_overflow15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_auto_overflow", 0 );
			//Function test:checkit
			{
			IMethod methodcheckit16;
				IModelElement[] methodtest_auto_overflow15Childs = methodtest_auto_overflow15.getChildren();
				methodcheckit16 = ModelTestUtils.getAssertMethod( methodtest_auto_overflow15Childs, "checkit", 1 );
				ModelTestUtils.assertParameterNames( methodcheckit16, new String[] {"args"} );
			}
		}
		//Function test:test_float_overflow
		{
		IMethod methodtest_float_overflow17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_float_overflow17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_float_overflow", 0 );
		}
		//Function test:test_logs
		{
		IMethod methodtest_logs18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_logs18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_logs", 0 );
		}
		//Function test:test_mixed_compares
		{
		IMethod methodtest_mixed_compares19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_mixed_compares19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_mixed_compares", 0 );
			//Class test
			{
			IType classRat20;
				IModelElement[] methodtest_mixed_compares19Childs = methodtest_mixed_compares19.getChildren();
				classRat20 = ModelTestUtils.getAssertClass( methodtest_mixed_compares19Childs, "Rat" );
				//Function test:__init__
				{
				IMethod method__init__21;
					IModelElement[] classRat20Childs = classRat20.getChildren();
					method__init__21 = ModelTestUtils.getAssertMethod( classRat20Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__21, new String[] {"self", "value"} );
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__22;
					IModelElement[] classRat20Childs = classRat20.getChildren();
					method__cmp__22 = ModelTestUtils.getAssertMethod( classRat20Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__22, new String[] {"self", "other"} );
				}
			}
		}

	}
	public void testModelGen97( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_macfs.py"));

		assertNotNull("Module test_macfs.py not found", module);
		assertEquals("test_macfs.py", module.getElementName());
		
		//Class test
		{
		IType classTestMacfs0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMacfs0 = ModelTestUtils.getAssertClass( moduleChilds, "TestMacfs" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_fsspec
			{
			IMethod methodtest_fsspec3;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_fsspec3 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_fsspec", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fsspec3, new String[] {"self"} );
			}
			//Function test:test_fsref
			{
			IMethod methodtest_fsref4;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_fsref4 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_fsref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fsref4, new String[] {"self"} );
			}
			//Function test:test_fsref_unicode
			{
			IMethod methodtest_fsref_unicode5;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_fsref_unicode5 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_fsref_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fsref_unicode5, new String[] {"self"} );
			}
			//Function test:test_coercion
			{
			IMethod methodtest_coercion6;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_coercion6 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_coercion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coercion6, new String[] {"self"} );
			}
			//Function test:test_dates
			{
			IMethod methodtest_dates7;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_dates7 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_dates", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dates7, new String[] {"self"} );
			}
			//Function test:test_ctor_type
			{
			IMethod methodtest_ctor_type8;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_ctor_type8 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_ctor_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ctor_type8, new String[] {"self"} );
			}
			//Function test:test_alias
			{
			IMethod methodtest_alias9;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_alias9 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_alias", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias9, new String[] {"self"} );
			}
			//Function test:test_fss_alias
			{
			IMethod methodtest_fss_alias10;
				IModelElement[] classTestMacfs0Childs = classTestMacfs0.getChildren();
				methodtest_fss_alias10 = ModelTestUtils.getAssertMethod( classTestMacfs0Childs, "test_fss_alias", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fss_alias10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen98( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_optparse.py"));

		assertNotNull("Module test_optparse.py not found", module);
		assertEquals("test_optparse.py", module.getElementName());
		
		//Class test
		{
		IType classInterceptedError0;
			IModelElement[] moduleChilds = module.getChildren();
			classInterceptedError0 = ModelTestUtils.getAssertClass( moduleChilds, "InterceptedError" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classInterceptedError0Childs = classInterceptedError0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classInterceptedError0Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "error_message", "exit_status", "exit_message"} );
			}
			//Function test:__str__
			{
			IMethod method__str__2;
				IModelElement[] classInterceptedError0Childs = classInterceptedError0.getChildren();
				method__str__2 = ModelTestUtils.getAssertMethod( classInterceptedError0Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__2, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classInterceptingOptionParser3;
			IModelElement[] moduleChilds = module.getChildren();
			classInterceptingOptionParser3 = ModelTestUtils.getAssertClass( moduleChilds, "InterceptingOptionParser" );
			//Function test:exit
			{
			IMethod methodexit4;
				IModelElement[] classInterceptingOptionParser3Childs = classInterceptingOptionParser3.getChildren();
				methodexit4 = ModelTestUtils.getAssertMethod( classInterceptingOptionParser3Childs, "exit", 3 );
				ModelTestUtils.assertParameterNames( methodexit4, new String[] {"self", "status", "msg"} );
			}
			//Function test:error
			{
			IMethod methoderror5;
				IModelElement[] classInterceptingOptionParser3Childs = classInterceptingOptionParser3.getChildren();
				methoderror5 = ModelTestUtils.getAssertMethod( classInterceptingOptionParser3Childs, "error", 2 );
				ModelTestUtils.assertParameterNames( methoderror5, new String[] {"self", "msg"} );
			}
		}
		//Class test
		{
		IType classBaseTest6;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseTest6 = ModelTestUtils.getAssertClass( moduleChilds, "BaseTest" );
			//Function test:assertParseOK
			{
			IMethod methodassertParseOK7;
				IModelElement[] classBaseTest6Childs = classBaseTest6.getChildren();
				methodassertParseOK7 = ModelTestUtils.getAssertMethod( classBaseTest6Childs, "assertParseOK", 4 );
				ModelTestUtils.assertParameterNames( methodassertParseOK7, new String[] {"self", "args", "expected_opts", "expected_positional_args"} );
			}
			//Function test:assertRaises
			{
			IMethod methodassertRaises8;
				IModelElement[] classBaseTest6Childs = classBaseTest6.getChildren();
				methodassertRaises8 = ModelTestUtils.getAssertMethod( classBaseTest6Childs, "assertRaises", 6 );
				ModelTestUtils.assertParameterNames( methodassertRaises8, new String[] {"self", "func", "args", "kwargs", "expected_exception", "expected_message"} );
			}
			//Function test:assertParseFail
			{
			IMethod methodassertParseFail9;
				IModelElement[] classBaseTest6Childs = classBaseTest6.getChildren();
				methodassertParseFail9 = ModelTestUtils.getAssertMethod( classBaseTest6Childs, "assertParseFail", 3 );
				ModelTestUtils.assertParameterNames( methodassertParseFail9, new String[] {"self", "cmdline_args", "expected_output"} );
			}
			//Function test:assertOutput
			{
			IMethod methodassertOutput10;
				IModelElement[] classBaseTest6Childs = classBaseTest6.getChildren();
				methodassertOutput10 = ModelTestUtils.getAssertMethod( classBaseTest6Childs, "assertOutput", 5 );
				ModelTestUtils.assertParameterNames( methodassertOutput10, new String[] {"self", "cmdline_args", "expected_output", "expected_status", "expected_error"} );
			}
			//Function test:assertTypeError
			{
			IMethod methodassertTypeError11;
				IModelElement[] classBaseTest6Childs = classBaseTest6.getChildren();
				methodassertTypeError11 = ModelTestUtils.getAssertMethod( classBaseTest6Childs, "assertTypeError", 4 );
				ModelTestUtils.assertParameterNames( methodassertTypeError11, new String[] {"self", "func", "expected_message", "args"} );
			}
			//Function test:assertHelp
			{
			IMethod methodassertHelp12;
				IModelElement[] classBaseTest6Childs = classBaseTest6.getChildren();
				methodassertHelp12 = ModelTestUtils.getAssertMethod( classBaseTest6Childs, "assertHelp", 3 );
				ModelTestUtils.assertParameterNames( methodassertHelp12, new String[] {"self", "parser", "expected_help"} );
			}
		}
		//Class test
		{
		IType classTestOptionChecks13;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionChecks13 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionChecks" );
			//Function test:setUp
			{
			IMethod methodsetUp14;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodsetUp14 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp14, new String[] {"self"} );
			}
			//Function test:assertOptionError
			{
			IMethod methodassertOptionError15;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodassertOptionError15 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "assertOptionError", 4 );
				ModelTestUtils.assertParameterNames( methodassertOptionError15, new String[] {"self", "expected_message", "args", "kwargs"} );
			}
			//Function test:test_opt_string_empty
			{
			IMethod methodtest_opt_string_empty16;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_opt_string_empty16 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_opt_string_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_empty16, new String[] {"self"} );
			}
			//Function test:test_opt_string_too_short
			{
			IMethod methodtest_opt_string_too_short17;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_opt_string_too_short17 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_opt_string_too_short", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_too_short17, new String[] {"self"} );
			}
			//Function test:test_opt_string_short_invalid
			{
			IMethod methodtest_opt_string_short_invalid18;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_opt_string_short_invalid18 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_opt_string_short_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_short_invalid18, new String[] {"self"} );
			}
			//Function test:test_opt_string_long_invalid
			{
			IMethod methodtest_opt_string_long_invalid19;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_opt_string_long_invalid19 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_opt_string_long_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_long_invalid19, new String[] {"self"} );
			}
			//Function test:test_attr_invalid
			{
			IMethod methodtest_attr_invalid20;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_attr_invalid20 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_attr_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_invalid20, new String[] {"self"} );
			}
			//Function test:test_action_invalid
			{
			IMethod methodtest_action_invalid21;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_action_invalid21 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_action_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_action_invalid21, new String[] {"self"} );
			}
			//Function test:test_type_invalid
			{
			IMethod methodtest_type_invalid22;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_type_invalid22 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_type_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type_invalid22, new String[] {"self"} );
			}
			//Function test:test_no_type_for_action
			{
			IMethod methodtest_no_type_for_action23;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_type_for_action23 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_type_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_type_for_action23, new String[] {"self"} );
			}
			//Function test:test_no_choices_list
			{
			IMethod methodtest_no_choices_list24;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_choices_list24 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_choices_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_choices_list24, new String[] {"self"} );
			}
			//Function test:test_bad_choices_list
			{
			IMethod methodtest_bad_choices_list25;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_bad_choices_list25 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_bad_choices_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_choices_list25, new String[] {"self"} );
			}
			//Function test:test_no_choices_for_type
			{
			IMethod methodtest_no_choices_for_type26;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_choices_for_type26 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_choices_for_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_choices_for_type26, new String[] {"self"} );
			}
			//Function test:test_no_const_for_action
			{
			IMethod methodtest_no_const_for_action27;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_const_for_action27 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_const_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_const_for_action27, new String[] {"self"} );
			}
			//Function test:test_no_nargs_for_action
			{
			IMethod methodtest_no_nargs_for_action28;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_nargs_for_action28 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_nargs_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_nargs_for_action28, new String[] {"self"} );
			}
			//Function test:test_callback_not_callable
			{
			IMethod methodtest_callback_not_callable29;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_callback_not_callable29 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_callback_not_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_not_callable29, new String[] {"self"} );
			}
			//Function test:dummy
			{
			IMethod methoddummy30;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methoddummy30 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "dummy", 1 );
				ModelTestUtils.assertParameterNames( methoddummy30, new String[] {"self"} );
			}
			//Function test:test_callback_args_no_tuple
			{
			IMethod methodtest_callback_args_no_tuple31;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_callback_args_no_tuple31 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_callback_args_no_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_args_no_tuple31, new String[] {"self"} );
			}
			//Function test:test_callback_kwargs_no_dict
			{
			IMethod methodtest_callback_kwargs_no_dict32;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_callback_kwargs_no_dict32 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_callback_kwargs_no_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_kwargs_no_dict32, new String[] {"self"} );
			}
			//Function test:test_no_callback_for_action
			{
			IMethod methodtest_no_callback_for_action33;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_callback_for_action33 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_callback_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_callback_for_action33, new String[] {"self"} );
			}
			//Function test:test_no_callback_args_for_action
			{
			IMethod methodtest_no_callback_args_for_action34;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_callback_args_for_action34 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_callback_args_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_callback_args_for_action34, new String[] {"self"} );
			}
			//Function test:test_no_callback_kwargs_for_action
			{
			IMethod methodtest_no_callback_kwargs_for_action35;
				IModelElement[] classTestOptionChecks13Childs = classTestOptionChecks13.getChildren();
				methodtest_no_callback_kwargs_for_action35 = ModelTestUtils.getAssertMethod( classTestOptionChecks13Childs, "test_no_callback_kwargs_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_callback_kwargs_for_action35, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOptionParser36;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionParser36 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionParser" );
			//Function test:setUp
			{
			IMethod methodsetUp37;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodsetUp37 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp37, new String[] {"self"} );
			}
			//Function test:test_add_option_no_Option
			{
			IMethod methodtest_add_option_no_Option38;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_add_option_no_Option38 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_add_option_no_Option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_option_no_Option38, new String[] {"self"} );
			}
			//Function test:test_add_option_invalid_arguments
			{
			IMethod methodtest_add_option_invalid_arguments39;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_add_option_invalid_arguments39 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_add_option_invalid_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_option_invalid_arguments39, new String[] {"self"} );
			}
			//Function test:test_get_option
			{
			IMethod methodtest_get_option40;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_get_option40 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_get_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_option40, new String[] {"self"} );
			}
			//Function test:test_get_option_equals
			{
			IMethod methodtest_get_option_equals41;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_get_option_equals41 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_get_option_equals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_option_equals41, new String[] {"self"} );
			}
			//Function test:test_has_option
			{
			IMethod methodtest_has_option42;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_has_option42 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_has_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_option42, new String[] {"self"} );
			}
			//Function test:assert_removed
			{
			IMethod methodassert_removed43;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodassert_removed43 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "assert_removed", 1 );
				ModelTestUtils.assertParameterNames( methodassert_removed43, new String[] {"self"} );
			}
			//Function test:test_remove_short_opt
			{
			IMethod methodtest_remove_short_opt44;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_remove_short_opt44 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_remove_short_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_short_opt44, new String[] {"self"} );
			}
			//Function test:test_remove_long_opt
			{
			IMethod methodtest_remove_long_opt45;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_remove_long_opt45 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_remove_long_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_long_opt45, new String[] {"self"} );
			}
			//Function test:test_remove_nonexistent
			{
			IMethod methodtest_remove_nonexistent46;
				IModelElement[] classTestOptionParser36Childs = classTestOptionParser36.getChildren();
				methodtest_remove_nonexistent46 = ModelTestUtils.getAssertMethod( classTestOptionParser36Childs, "test_remove_nonexistent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_nonexistent46, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOptionValues47;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionValues47 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionValues" );
			//Function test:setUp
			{
			IMethod methodsetUp48;
				IModelElement[] classTestOptionValues47Childs = classTestOptionValues47.getChildren();
				methodsetUp48 = ModelTestUtils.getAssertMethod( classTestOptionValues47Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp48, new String[] {"self"} );
			}
			//Function test:test_basics
			{
			IMethod methodtest_basics49;
				IModelElement[] classTestOptionValues47Childs = classTestOptionValues47.getChildren();
				methodtest_basics49 = ModelTestUtils.getAssertMethod( classTestOptionValues47Childs, "test_basics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basics49, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestTypeAliases50;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTypeAliases50 = ModelTestUtils.getAssertClass( moduleChilds, "TestTypeAliases" );
			//Function test:setUp
			{
			IMethod methodsetUp51;
				IModelElement[] classTestTypeAliases50Childs = classTestTypeAliases50.getChildren();
				methodsetUp51 = ModelTestUtils.getAssertMethod( classTestTypeAliases50Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp51, new String[] {"self"} );
			}
			//Function test:test_type_aliases
			{
			IMethod methodtest_type_aliases52;
				IModelElement[] classTestTypeAliases50Childs = classTestTypeAliases50.getChildren();
				methodtest_type_aliases52 = ModelTestUtils.getAssertMethod( classTestTypeAliases50Childs, "test_type_aliases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type_aliases52, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_time_units");
		}
		//Function test:_check_duration
		{
		IMethod method_check_duration53;
			IModelElement[] moduleChilds = module.getChildren();
			method_check_duration53 = ModelTestUtils.getAssertMethod( moduleChilds, "_check_duration", 3 );
			ModelTestUtils.assertParameterNames( method_check_duration53, new String[] {"option", "opt", "value"} );
		}
		//Class test
		{
		IType classDurationOption54;
			IModelElement[] moduleChilds = module.getChildren();
			classDurationOption54 = ModelTestUtils.getAssertClass( moduleChilds, "DurationOption" );
			{
				IModelElement[] classDurationOption54Childs = classDurationOption54.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDurationOption54Childs, "TYPES");
			}
			{
				IModelElement[] classDurationOption54Childs = classDurationOption54.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDurationOption54Childs, "TYPE_CHECKER");
			}
		}
		//Class test
		{
		IType classTestDefaultValues55;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDefaultValues55 = ModelTestUtils.getAssertClass( moduleChilds, "TestDefaultValues" );
			//Function test:setUp
			{
			IMethod methodsetUp56;
				IModelElement[] classTestDefaultValues55Childs = classTestDefaultValues55.getChildren();
				methodsetUp56 = ModelTestUtils.getAssertMethod( classTestDefaultValues55Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp56, new String[] {"self"} );
			}
			//Function test:test_basic_defaults
			{
			IMethod methodtest_basic_defaults57;
				IModelElement[] classTestDefaultValues55Childs = classTestDefaultValues55.getChildren();
				methodtest_basic_defaults57 = ModelTestUtils.getAssertMethod( classTestDefaultValues55Childs, "test_basic_defaults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_defaults57, new String[] {"self"} );
			}
			//Function test:test_mixed_defaults_post
			{
			IMethod methodtest_mixed_defaults_post58;
				IModelElement[] classTestDefaultValues55Childs = classTestDefaultValues55.getChildren();
				methodtest_mixed_defaults_post58 = ModelTestUtils.getAssertMethod( classTestDefaultValues55Childs, "test_mixed_defaults_post", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_defaults_post58, new String[] {"self"} );
			}
			//Function test:test_mixed_defaults_pre
			{
			IMethod methodtest_mixed_defaults_pre59;
				IModelElement[] classTestDefaultValues55Childs = classTestDefaultValues55.getChildren();
				methodtest_mixed_defaults_pre59 = ModelTestUtils.getAssertMethod( classTestDefaultValues55Childs, "test_mixed_defaults_pre", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_defaults_pre59, new String[] {"self"} );
			}
			//Function test:test_process_default
			{
			IMethod methodtest_process_default60;
				IModelElement[] classTestDefaultValues55Childs = classTestDefaultValues55.getChildren();
				methodtest_process_default60 = ModelTestUtils.getAssertMethod( classTestDefaultValues55Childs, "test_process_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_process_default60, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestProgName61;
			IModelElement[] moduleChilds = module.getChildren();
			classTestProgName61 = ModelTestUtils.getAssertClass( moduleChilds, "TestProgName" );
			//Function test:assertUsage
			{
			IMethod methodassertUsage62;
				IModelElement[] classTestProgName61Childs = classTestProgName61.getChildren();
				methodassertUsage62 = ModelTestUtils.getAssertMethod( classTestProgName61Childs, "assertUsage", 3 );
				ModelTestUtils.assertParameterNames( methodassertUsage62, new String[] {"self", "parser", "expected_usage"} );
			}
			//Function test:assertVersion
			{
			IMethod methodassertVersion63;
				IModelElement[] classTestProgName61Childs = classTestProgName61.getChildren();
				methodassertVersion63 = ModelTestUtils.getAssertMethod( classTestProgName61Childs, "assertVersion", 3 );
				ModelTestUtils.assertParameterNames( methodassertVersion63, new String[] {"self", "parser", "expected_version"} );
			}
			//Function test:test_default_progname
			{
			IMethod methodtest_default_progname64;
				IModelElement[] classTestProgName61Childs = classTestProgName61.getChildren();
				methodtest_default_progname64 = ModelTestUtils.getAssertMethod( classTestProgName61Childs, "test_default_progname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_progname64, new String[] {"self"} );
			}
			//Function test:test_custom_progname
			{
			IMethod methodtest_custom_progname65;
				IModelElement[] classTestProgName61Childs = classTestProgName61.getChildren();
				methodtest_custom_progname65 = ModelTestUtils.getAssertMethod( classTestProgName61Childs, "test_custom_progname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_custom_progname65, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExpandDefaults66;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExpandDefaults66 = ModelTestUtils.getAssertClass( moduleChilds, "TestExpandDefaults" );
			//Function test:setUp
			{
			IMethod methodsetUp67;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodsetUp67 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp67, new String[] {"self"} );
			}
			//Function test:test_option_default
			{
			IMethod methodtest_option_default68;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_option_default68 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_option_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_default68, new String[] {"self"} );
			}
			//Function test:test_parser_default_1
			{
			IMethod methodtest_parser_default_169;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_parser_default_169 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_parser_default_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parser_default_169, new String[] {"self"} );
			}
			//Function test:test_parser_default_2
			{
			IMethod methodtest_parser_default_270;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_parser_default_270 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_parser_default_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parser_default_270, new String[] {"self"} );
			}
			//Function test:test_no_default
			{
			IMethod methodtest_no_default71;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_no_default71 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_no_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_default71, new String[] {"self"} );
			}
			//Function test:test_default_none_1
			{
			IMethod methodtest_default_none_172;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_default_none_172 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_default_none_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_none_172, new String[] {"self"} );
			}
			//Function test:test_default_none_2
			{
			IMethod methodtest_default_none_273;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_default_none_273 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_default_none_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_none_273, new String[] {"self"} );
			}
			//Function test:test_float_default
			{
			IMethod methodtest_float_default74;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_float_default74 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_float_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float_default74, new String[] {"self"} );
			}
			//Function test:test_alt_expand
			{
			IMethod methodtest_alt_expand75;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_alt_expand75 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_alt_expand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alt_expand75, new String[] {"self"} );
			}
			//Function test:test_no_expand
			{
			IMethod methodtest_no_expand76;
				IModelElement[] classTestExpandDefaults66Childs = classTestExpandDefaults66.getChildren();
				methodtest_no_expand76 = ModelTestUtils.getAssertMethod( classTestExpandDefaults66Childs, "test_no_expand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_expand76, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestStandard77;
			IModelElement[] moduleChilds = module.getChildren();
			classTestStandard77 = ModelTestUtils.getAssertClass( moduleChilds, "TestStandard" );
			//Function test:setUp
			{
			IMethod methodsetUp78;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodsetUp78 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp78, new String[] {"self"} );
			}
			//Function test:test_required_value
			{
			IMethod methodtest_required_value79;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_required_value79 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_required_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_required_value79, new String[] {"self"} );
			}
			//Function test:test_invalid_integer
			{
			IMethod methodtest_invalid_integer80;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_invalid_integer80 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_invalid_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_integer80, new String[] {"self"} );
			}
			//Function test:test_no_such_option
			{
			IMethod methodtest_no_such_option81;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_no_such_option81 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_no_such_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_such_option81, new String[] {"self"} );
			}
			//Function test:test_long_invalid_integer
			{
			IMethod methodtest_long_invalid_integer82;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_long_invalid_integer82 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_long_invalid_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_invalid_integer82, new String[] {"self"} );
			}
			//Function test:test_empty
			{
			IMethod methodtest_empty83;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_empty83 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty83, new String[] {"self"} );
			}
			//Function test:test_shortopt_empty_longopt_append
			{
			IMethod methodtest_shortopt_empty_longopt_append84;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_shortopt_empty_longopt_append84 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_shortopt_empty_longopt_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shortopt_empty_longopt_append84, new String[] {"self"} );
			}
			//Function test:test_long_option_append
			{
			IMethod methodtest_long_option_append85;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_long_option_append85 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_long_option_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_append85, new String[] {"self"} );
			}
			//Function test:test_option_argument_joined
			{
			IMethod methodtest_option_argument_joined86;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_option_argument_joined86 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_option_argument_joined", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_joined86, new String[] {"self"} );
			}
			//Function test:test_option_argument_split
			{
			IMethod methodtest_option_argument_split87;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_option_argument_split87 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_option_argument_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_split87, new String[] {"self"} );
			}
			//Function test:test_option_argument_joined_integer
			{
			IMethod methodtest_option_argument_joined_integer88;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_option_argument_joined_integer88 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_option_argument_joined_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_joined_integer88, new String[] {"self"} );
			}
			//Function test:test_option_argument_split_negative_integer
			{
			IMethod methodtest_option_argument_split_negative_integer89;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_option_argument_split_negative_integer89 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_option_argument_split_negative_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_split_negative_integer89, new String[] {"self"} );
			}
			//Function test:test_long_option_argument_joined
			{
			IMethod methodtest_long_option_argument_joined90;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_long_option_argument_joined90 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_long_option_argument_joined", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_argument_joined90, new String[] {"self"} );
			}
			//Function test:test_long_option_argument_split
			{
			IMethod methodtest_long_option_argument_split91;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_long_option_argument_split91 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_long_option_argument_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_argument_split91, new String[] {"self"} );
			}
			//Function test:test_long_option_short_option
			{
			IMethod methodtest_long_option_short_option92;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_long_option_short_option92 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_long_option_short_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_short_option92, new String[] {"self"} );
			}
			//Function test:test_abbrev_long_option
			{
			IMethod methodtest_abbrev_long_option93;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_abbrev_long_option93 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_abbrev_long_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abbrev_long_option93, new String[] {"self"} );
			}
			//Function test:test_defaults
			{
			IMethod methodtest_defaults94;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_defaults94 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_defaults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_defaults94, new String[] {"self"} );
			}
			//Function test:test_ambiguous_option
			{
			IMethod methodtest_ambiguous_option95;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_ambiguous_option95 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_ambiguous_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ambiguous_option95, new String[] {"self"} );
			}
			//Function test:test_short_and_long_option_split
			{
			IMethod methodtest_short_and_long_option_split96;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_short_and_long_option_split96 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_short_and_long_option_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_and_long_option_split96, new String[] {"self"} );
			}
			//Function test:test_short_option_split_long_option_append
			{
			IMethod methodtest_short_option_split_long_option_append97;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_short_option_split_long_option_append97 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_short_option_split_long_option_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_split_long_option_append97, new String[] {"self"} );
			}
			//Function test:test_short_option_split_one_positional_arg
			{
			IMethod methodtest_short_option_split_one_positional_arg98;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_short_option_split_one_positional_arg98 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_short_option_split_one_positional_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_split_one_positional_arg98, new String[] {"self"} );
			}
			//Function test:test_short_option_consumes_separator
			{
			IMethod methodtest_short_option_consumes_separator99;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_short_option_consumes_separator99 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_short_option_consumes_separator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_consumes_separator99, new String[] {"self"} );
			}
			//Function test:test_short_option_joined_and_separator
			{
			IMethod methodtest_short_option_joined_and_separator100;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_short_option_joined_and_separator100 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_short_option_joined_and_separator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_joined_and_separator100, new String[] {"self"} );
			}
			//Function test:test_invalid_option_becomes_positional_arg
			{
			IMethod methodtest_invalid_option_becomes_positional_arg101;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_invalid_option_becomes_positional_arg101 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_invalid_option_becomes_positional_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_option_becomes_positional_arg101, new String[] {"self"} );
			}
			//Function test:test_no_append_versus_append
			{
			IMethod methodtest_no_append_versus_append102;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_no_append_versus_append102 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_no_append_versus_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_append_versus_append102, new String[] {"self"} );
			}
			//Function test:test_option_consumes_optionlike_string
			{
			IMethod methodtest_option_consumes_optionlike_string103;
				IModelElement[] classTestStandard77Childs = classTestStandard77.getChildren();
				methodtest_option_consumes_optionlike_string103 = ModelTestUtils.getAssertMethod( classTestStandard77Childs, "test_option_consumes_optionlike_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_consumes_optionlike_string103, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBool104;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBool104 = ModelTestUtils.getAssertClass( moduleChilds, "TestBool" );
			//Function test:setUp
			{
			IMethod methodsetUp105;
				IModelElement[] classTestBool104Childs = classTestBool104.getChildren();
				methodsetUp105 = ModelTestUtils.getAssertMethod( classTestBool104Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp105, new String[] {"self"} );
			}
			//Function test:test_bool_default
			{
			IMethod methodtest_bool_default106;
				IModelElement[] classTestBool104Childs = classTestBool104.getChildren();
				methodtest_bool_default106 = ModelTestUtils.getAssertMethod( classTestBool104Childs, "test_bool_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_default106, new String[] {"self"} );
			}
			//Function test:test_bool_false
			{
			IMethod methodtest_bool_false107;
				IModelElement[] classTestBool104Childs = classTestBool104.getChildren();
				methodtest_bool_false107 = ModelTestUtils.getAssertMethod( classTestBool104Childs, "test_bool_false", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_false107, new String[] {"self"} );
			}
			//Function test:test_bool_true
			{
			IMethod methodtest_bool_true108;
				IModelElement[] classTestBool104Childs = classTestBool104.getChildren();
				methodtest_bool_true108 = ModelTestUtils.getAssertMethod( classTestBool104Childs, "test_bool_true", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_true108, new String[] {"self"} );
			}
			//Function test:test_bool_flicker_on_and_off
			{
			IMethod methodtest_bool_flicker_on_and_off109;
				IModelElement[] classTestBool104Childs = classTestBool104.getChildren();
				methodtest_bool_flicker_on_and_off109 = ModelTestUtils.getAssertMethod( classTestBool104Childs, "test_bool_flicker_on_and_off", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_flicker_on_and_off109, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestChoice110;
			IModelElement[] moduleChilds = module.getChildren();
			classTestChoice110 = ModelTestUtils.getAssertClass( moduleChilds, "TestChoice" );
			//Function test:setUp
			{
			IMethod methodsetUp111;
				IModelElement[] classTestChoice110Childs = classTestChoice110.getChildren();
				methodsetUp111 = ModelTestUtils.getAssertMethod( classTestChoice110Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp111, new String[] {"self"} );
			}
			//Function test:test_valid_choice
			{
			IMethod methodtest_valid_choice112;
				IModelElement[] classTestChoice110Childs = classTestChoice110.getChildren();
				methodtest_valid_choice112 = ModelTestUtils.getAssertMethod( classTestChoice110Childs, "test_valid_choice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_valid_choice112, new String[] {"self"} );
			}
			//Function test:test_invalid_choice
			{
			IMethod methodtest_invalid_choice113;
				IModelElement[] classTestChoice110Childs = classTestChoice110.getChildren();
				methodtest_invalid_choice113 = ModelTestUtils.getAssertMethod( classTestChoice110Childs, "test_invalid_choice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_choice113, new String[] {"self"} );
			}
			//Function test:test_add_choice_option
			{
			IMethod methodtest_add_choice_option114;
				IModelElement[] classTestChoice110Childs = classTestChoice110.getChildren();
				methodtest_add_choice_option114 = ModelTestUtils.getAssertMethod( classTestChoice110Childs, "test_add_choice_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_choice_option114, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCount115;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCount115 = ModelTestUtils.getAssertClass( moduleChilds, "TestCount" );
			//Function test:setUp
			{
			IMethod methodsetUp116;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodsetUp116 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp116, new String[] {"self"} );
			}
			//Function test:test_empty
			{
			IMethod methodtest_empty117;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_empty117 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty117, new String[] {"self"} );
			}
			//Function test:test_count_one
			{
			IMethod methodtest_count_one118;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_one118 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_one", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_one118, new String[] {"self"} );
			}
			//Function test:test_count_three
			{
			IMethod methodtest_count_three119;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_three119 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_three", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_three119, new String[] {"self"} );
			}
			//Function test:test_count_three_apart
			{
			IMethod methodtest_count_three_apart120;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_three_apart120 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_three_apart", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_three_apart120, new String[] {"self"} );
			}
			//Function test:test_count_override_amount
			{
			IMethod methodtest_count_override_amount121;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_override_amount121 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_override_amount", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_override_amount121, new String[] {"self"} );
			}
			//Function test:test_count_override_quiet
			{
			IMethod methodtest_count_override_quiet122;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_override_quiet122 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_override_quiet", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_override_quiet122, new String[] {"self"} );
			}
			//Function test:test_count_overriding
			{
			IMethod methodtest_count_overriding123;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_overriding123 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_overriding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_overriding123, new String[] {"self"} );
			}
			//Function test:test_count_interspersed_args
			{
			IMethod methodtest_count_interspersed_args124;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_interspersed_args124 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_interspersed_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_interspersed_args124, new String[] {"self"} );
			}
			//Function test:test_count_no_interspersed_args
			{
			IMethod methodtest_count_no_interspersed_args125;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_no_interspersed_args125 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_no_interspersed_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_no_interspersed_args125, new String[] {"self"} );
			}
			//Function test:test_count_no_such_option
			{
			IMethod methodtest_count_no_such_option126;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_no_such_option126 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_no_such_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_no_such_option126, new String[] {"self"} );
			}
			//Function test:test_count_option_no_value
			{
			IMethod methodtest_count_option_no_value127;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_option_no_value127 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_option_no_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_option_no_value127, new String[] {"self"} );
			}
			//Function test:test_count_with_default
			{
			IMethod methodtest_count_with_default128;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_with_default128 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_with_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_with_default128, new String[] {"self"} );
			}
			//Function test:test_count_overriding_default
			{
			IMethod methodtest_count_overriding_default129;
				IModelElement[] classTestCount115Childs = classTestCount115.getChildren();
				methodtest_count_overriding_default129 = ModelTestUtils.getAssertMethod( classTestCount115Childs, "test_count_overriding_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_overriding_default129, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMultipleArgs130;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMultipleArgs130 = ModelTestUtils.getAssertClass( moduleChilds, "TestMultipleArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp131;
				IModelElement[] classTestMultipleArgs130Childs = classTestMultipleArgs130.getChildren();
				methodsetUp131 = ModelTestUtils.getAssertMethod( classTestMultipleArgs130Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp131, new String[] {"self"} );
			}
			//Function test:test_nargs_with_positional_args
			{
			IMethod methodtest_nargs_with_positional_args132;
				IModelElement[] classTestMultipleArgs130Childs = classTestMultipleArgs130.getChildren();
				methodtest_nargs_with_positional_args132 = ModelTestUtils.getAssertMethod( classTestMultipleArgs130Childs, "test_nargs_with_positional_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_with_positional_args132, new String[] {"self"} );
			}
			//Function test:test_nargs_long_opt
			{
			IMethod methodtest_nargs_long_opt133;
				IModelElement[] classTestMultipleArgs130Childs = classTestMultipleArgs130.getChildren();
				methodtest_nargs_long_opt133 = ModelTestUtils.getAssertMethod( classTestMultipleArgs130Childs, "test_nargs_long_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_long_opt133, new String[] {"self"} );
			}
			//Function test:test_nargs_invalid_float_value
			{
			IMethod methodtest_nargs_invalid_float_value134;
				IModelElement[] classTestMultipleArgs130Childs = classTestMultipleArgs130.getChildren();
				methodtest_nargs_invalid_float_value134 = ModelTestUtils.getAssertMethod( classTestMultipleArgs130Childs, "test_nargs_invalid_float_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_invalid_float_value134, new String[] {"self"} );
			}
			//Function test:test_nargs_required_values
			{
			IMethod methodtest_nargs_required_values135;
				IModelElement[] classTestMultipleArgs130Childs = classTestMultipleArgs130.getChildren();
				methodtest_nargs_required_values135 = ModelTestUtils.getAssertMethod( classTestMultipleArgs130Childs, "test_nargs_required_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_required_values135, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMultipleArgsAppend136;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMultipleArgsAppend136 = ModelTestUtils.getAssertClass( moduleChilds, "TestMultipleArgsAppend" );
			//Function test:setUp
			{
			IMethod methodsetUp137;
				IModelElement[] classTestMultipleArgsAppend136Childs = classTestMultipleArgsAppend136.getChildren();
				methodsetUp137 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend136Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp137, new String[] {"self"} );
			}
			//Function test:test_nargs_append
			{
			IMethod methodtest_nargs_append138;
				IModelElement[] classTestMultipleArgsAppend136Childs = classTestMultipleArgsAppend136.getChildren();
				methodtest_nargs_append138 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend136Childs, "test_nargs_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_append138, new String[] {"self"} );
			}
			//Function test:test_nargs_append_required_values
			{
			IMethod methodtest_nargs_append_required_values139;
				IModelElement[] classTestMultipleArgsAppend136Childs = classTestMultipleArgsAppend136.getChildren();
				methodtest_nargs_append_required_values139 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend136Childs, "test_nargs_append_required_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_append_required_values139, new String[] {"self"} );
			}
			//Function test:test_nargs_append_simple
			{
			IMethod methodtest_nargs_append_simple140;
				IModelElement[] classTestMultipleArgsAppend136Childs = classTestMultipleArgsAppend136.getChildren();
				methodtest_nargs_append_simple140 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend136Childs, "test_nargs_append_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_append_simple140, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestVersion141;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVersion141 = ModelTestUtils.getAssertClass( moduleChilds, "TestVersion" );
			//Function test:test_version
			{
			IMethod methodtest_version142;
				IModelElement[] classTestVersion141Childs = classTestVersion141.getChildren();
				methodtest_version142 = ModelTestUtils.getAssertMethod( classTestVersion141Childs, "test_version", 1 );
				ModelTestUtils.assertParameterNames( methodtest_version142, new String[] {"self"} );
			}
			//Function test:test_no_version
			{
			IMethod methodtest_no_version143;
				IModelElement[] classTestVersion141Childs = classTestVersion141.getChildren();
				methodtest_no_version143 = ModelTestUtils.getAssertMethod( classTestVersion141Childs, "test_no_version", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_version143, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestConflictingDefaults144;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflictingDefaults144 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflictingDefaults" );
			//Function test:setUp
			{
			IMethod methodsetUp145;
				IModelElement[] classTestConflictingDefaults144Childs = classTestConflictingDefaults144.getChildren();
				methodsetUp145 = ModelTestUtils.getAssertMethod( classTestConflictingDefaults144Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp145, new String[] {"self"} );
			}
			//Function test:test_conflict_default
			{
			IMethod methodtest_conflict_default146;
				IModelElement[] classTestConflictingDefaults144Childs = classTestConflictingDefaults144.getChildren();
				methodtest_conflict_default146 = ModelTestUtils.getAssertMethod( classTestConflictingDefaults144Childs, "test_conflict_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_default146, new String[] {"self"} );
			}
			//Function test:test_conflict_default_none
			{
			IMethod methodtest_conflict_default_none147;
				IModelElement[] classTestConflictingDefaults144Childs = classTestConflictingDefaults144.getChildren();
				methodtest_conflict_default_none147 = ModelTestUtils.getAssertMethod( classTestConflictingDefaults144Childs, "test_conflict_default_none", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_default_none147, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOptionGroup148;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionGroup148 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionGroup" );
			//Function test:setUp
			{
			IMethod methodsetUp149;
				IModelElement[] classTestOptionGroup148Childs = classTestOptionGroup148.getChildren();
				methodsetUp149 = ModelTestUtils.getAssertMethod( classTestOptionGroup148Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp149, new String[] {"self"} );
			}
			//Function test:test_option_group_create_instance
			{
			IMethod methodtest_option_group_create_instance150;
				IModelElement[] classTestOptionGroup148Childs = classTestOptionGroup148.getChildren();
				methodtest_option_group_create_instance150 = ModelTestUtils.getAssertMethod( classTestOptionGroup148Childs, "test_option_group_create_instance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_group_create_instance150, new String[] {"self"} );
			}
			//Function test:test_add_group_no_group
			{
			IMethod methodtest_add_group_no_group151;
				IModelElement[] classTestOptionGroup148Childs = classTestOptionGroup148.getChildren();
				methodtest_add_group_no_group151 = ModelTestUtils.getAssertMethod( classTestOptionGroup148Childs, "test_add_group_no_group", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_group_no_group151, new String[] {"self"} );
			}
			//Function test:test_add_group_invalid_arguments
			{
			IMethod methodtest_add_group_invalid_arguments152;
				IModelElement[] classTestOptionGroup148Childs = classTestOptionGroup148.getChildren();
				methodtest_add_group_invalid_arguments152 = ModelTestUtils.getAssertMethod( classTestOptionGroup148Childs, "test_add_group_invalid_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_group_invalid_arguments152, new String[] {"self"} );
			}
			//Function test:test_add_group_wrong_parser
			{
			IMethod methodtest_add_group_wrong_parser153;
				IModelElement[] classTestOptionGroup148Childs = classTestOptionGroup148.getChildren();
				methodtest_add_group_wrong_parser153 = ModelTestUtils.getAssertMethod( classTestOptionGroup148Childs, "test_add_group_wrong_parser", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_group_wrong_parser153, new String[] {"self"} );
			}
			//Function test:test_group_manipulate
			{
			IMethod methodtest_group_manipulate154;
				IModelElement[] classTestOptionGroup148Childs = classTestOptionGroup148.getChildren();
				methodtest_group_manipulate154 = ModelTestUtils.getAssertMethod( classTestOptionGroup148Childs, "test_group_manipulate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_group_manipulate154, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExtendAddTypes155;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExtendAddTypes155 = ModelTestUtils.getAssertClass( moduleChilds, "TestExtendAddTypes" );
			//Function test:setUp
			{
			IMethod methodsetUp156;
				IModelElement[] classTestExtendAddTypes155Childs = classTestExtendAddTypes155.getChildren();
				methodsetUp156 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes155Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp156, new String[] {"self"} );
			}
			//Class test
			{
			IType classMyOption157;
				IModelElement[] classTestExtendAddTypes155Childs = classTestExtendAddTypes155.getChildren();
				classMyOption157 = ModelTestUtils.getAssertClass( classTestExtendAddTypes155Childs, "MyOption" );
				//Function test:check_file
				{
				IMethod methodcheck_file158;
					IModelElement[] classMyOption157Childs = classMyOption157.getChildren();
					methodcheck_file158 = ModelTestUtils.getAssertMethod( classMyOption157Childs, "check_file", 3 );
					ModelTestUtils.assertParameterNames( methodcheck_file158, new String[] {"option", "opt", "value"} );
				}
				{
					IModelElement[] classMyOption157Childs = classMyOption157.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption157Childs, "TYPES");
				}
				{
					IModelElement[] classMyOption157Childs = classMyOption157.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption157Childs, "TYPE_CHECKER");
				}
			}
			//Function test:test_extend_file
			{
			IMethod methodtest_extend_file159;
				IModelElement[] classTestExtendAddTypes155Childs = classTestExtendAddTypes155.getChildren();
				methodtest_extend_file159 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes155Childs, "test_extend_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_file159, new String[] {"self"} );
			}
			//Function test:test_extend_file_nonexistent
			{
			IMethod methodtest_extend_file_nonexistent160;
				IModelElement[] classTestExtendAddTypes155Childs = classTestExtendAddTypes155.getChildren();
				methodtest_extend_file_nonexistent160 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes155Childs, "test_extend_file_nonexistent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_file_nonexistent160, new String[] {"self"} );
			}
			//Function test:test_file_irregular
			{
			IMethod methodtest_file_irregular161;
				IModelElement[] classTestExtendAddTypes155Childs = classTestExtendAddTypes155.getChildren();
				methodtest_file_irregular161 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes155Childs, "test_file_irregular", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file_irregular161, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExtendAddActions162;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExtendAddActions162 = ModelTestUtils.getAssertClass( moduleChilds, "TestExtendAddActions" );
			//Function test:setUp
			{
			IMethod methodsetUp163;
				IModelElement[] classTestExtendAddActions162Childs = classTestExtendAddActions162.getChildren();
				methodsetUp163 = ModelTestUtils.getAssertMethod( classTestExtendAddActions162Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp163, new String[] {"self"} );
			}
			//Class test
			{
			IType classMyOption164;
				IModelElement[] classTestExtendAddActions162Childs = classTestExtendAddActions162.getChildren();
				classMyOption164 = ModelTestUtils.getAssertClass( classTestExtendAddActions162Childs, "MyOption" );
				{
					IModelElement[] classMyOption164Childs = classMyOption164.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption164Childs, "ACTIONS");
				}
				{
					IModelElement[] classMyOption164Childs = classMyOption164.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption164Childs, "STORE_ACTIONS");
				}
				{
					IModelElement[] classMyOption164Childs = classMyOption164.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption164Childs, "TYPED_ACTIONS");
				}
				//Function test:take_action
				{
				IMethod methodtake_action165;
					IModelElement[] classMyOption164Childs = classMyOption164.getChildren();
					methodtake_action165 = ModelTestUtils.getAssertMethod( classMyOption164Childs, "take_action", 7 );
					ModelTestUtils.assertParameterNames( methodtake_action165, new String[] {"self", "action", "dest", "opt", "value", "values", "parser"} );
				}
			}
			//Function test:test_extend_add_action
			{
			IMethod methodtest_extend_add_action166;
				IModelElement[] classTestExtendAddActions162Childs = classTestExtendAddActions162.getChildren();
				methodtest_extend_add_action166 = ModelTestUtils.getAssertMethod( classTestExtendAddActions162Childs, "test_extend_add_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_add_action166, new String[] {"self"} );
			}
			//Function test:test_extend_add_action_normal
			{
			IMethod methodtest_extend_add_action_normal167;
				IModelElement[] classTestExtendAddActions162Childs = classTestExtendAddActions162.getChildren();
				methodtest_extend_add_action_normal167 = ModelTestUtils.getAssertMethod( classTestExtendAddActions162Childs, "test_extend_add_action_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_add_action_normal167, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallback168;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallback168 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallback" );
			//Function test:setUp
			{
			IMethod methodsetUp169;
				IModelElement[] classTestCallback168Childs = classTestCallback168.getChildren();
				methodsetUp169 = ModelTestUtils.getAssertMethod( classTestCallback168Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp169, new String[] {"self"} );
			}
			//Function test:process_opt
			{
			IMethod methodprocess_opt170;
				IModelElement[] classTestCallback168Childs = classTestCallback168.getChildren();
				methodprocess_opt170 = ModelTestUtils.getAssertMethod( classTestCallback168Childs, "process_opt", 5 );
				ModelTestUtils.assertParameterNames( methodprocess_opt170, new String[] {"self", "option", "opt", "value", "parser_"} );
			}
			//Function test:test_callback
			{
			IMethod methodtest_callback171;
				IModelElement[] classTestCallback168Childs = classTestCallback168.getChildren();
				methodtest_callback171 = ModelTestUtils.getAssertMethod( classTestCallback168Childs, "test_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback171, new String[] {"self"} );
			}
			//Function test:test_callback_help
			{
			IMethod methodtest_callback_help172;
				IModelElement[] classTestCallback168Childs = classTestCallback168.getChildren();
				methodtest_callback_help172 = ModelTestUtils.getAssertMethod( classTestCallback168Childs, "test_callback_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_help172, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackExtraArgs173;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackExtraArgs173 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackExtraArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp174;
				IModelElement[] classTestCallbackExtraArgs173Childs = classTestCallbackExtraArgs173.getChildren();
				methodsetUp174 = ModelTestUtils.getAssertMethod( classTestCallbackExtraArgs173Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp174, new String[] {"self"} );
			}
			//Function test:process_tuple
			{
			IMethod methodprocess_tuple175;
				IModelElement[] classTestCallbackExtraArgs173Childs = classTestCallbackExtraArgs173.getChildren();
				methodprocess_tuple175 = ModelTestUtils.getAssertMethod( classTestCallbackExtraArgs173Childs, "process_tuple", 7 );
				ModelTestUtils.assertParameterNames( methodprocess_tuple175, new String[] {"self", "option", "opt", "value", "parser_", "len", "type"} );
			}
			//Function test:test_callback_extra_args
			{
			IMethod methodtest_callback_extra_args176;
				IModelElement[] classTestCallbackExtraArgs173Childs = classTestCallbackExtraArgs173.getChildren();
				methodtest_callback_extra_args176 = ModelTestUtils.getAssertMethod( classTestCallbackExtraArgs173Childs, "test_callback_extra_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_extra_args176, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackMeddleArgs177;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackMeddleArgs177 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackMeddleArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp178;
				IModelElement[] classTestCallbackMeddleArgs177Childs = classTestCallbackMeddleArgs177.getChildren();
				methodsetUp178 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs177Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp178, new String[] {"self"} );
			}
			//Function test:process_n
			{
			IMethod methodprocess_n179;
				IModelElement[] classTestCallbackMeddleArgs177Childs = classTestCallbackMeddleArgs177.getChildren();
				methodprocess_n179 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs177Childs, "process_n", 5 );
				ModelTestUtils.assertParameterNames( methodprocess_n179, new String[] {"self", "option", "opt", "value", "parser_"} );
			}
			//Function test:test_callback_meddle_args
			{
			IMethod methodtest_callback_meddle_args180;
				IModelElement[] classTestCallbackMeddleArgs177Childs = classTestCallbackMeddleArgs177.getChildren();
				methodtest_callback_meddle_args180 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs177Childs, "test_callback_meddle_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_meddle_args180, new String[] {"self"} );
			}
			//Function test:test_callback_meddle_args_separator
			{
			IMethod methodtest_callback_meddle_args_separator181;
				IModelElement[] classTestCallbackMeddleArgs177Childs = classTestCallbackMeddleArgs177.getChildren();
				methodtest_callback_meddle_args_separator181 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs177Childs, "test_callback_meddle_args_separator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_meddle_args_separator181, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackManyArgs182;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackManyArgs182 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackManyArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp183;
				IModelElement[] classTestCallbackManyArgs182Childs = classTestCallbackManyArgs182.getChildren();
				methodsetUp183 = ModelTestUtils.getAssertMethod( classTestCallbackManyArgs182Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp183, new String[] {"self"} );
			}
			//Function test:process_many
			{
			IMethod methodprocess_many184;
				IModelElement[] classTestCallbackManyArgs182Childs = classTestCallbackManyArgs182.getChildren();
				methodprocess_many184 = ModelTestUtils.getAssertMethod( classTestCallbackManyArgs182Childs, "process_many", 5 );
				ModelTestUtils.assertParameterNames( methodprocess_many184, new String[] {"self", "option", "opt", "value", "parser_"} );
			}
			//Function test:test_many_args
			{
			IMethod methodtest_many_args185;
				IModelElement[] classTestCallbackManyArgs182Childs = classTestCallbackManyArgs182.getChildren();
				methodtest_many_args185 = ModelTestUtils.getAssertMethod( classTestCallbackManyArgs182Childs, "test_many_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_many_args185, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackCheckAbbrev186;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackCheckAbbrev186 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackCheckAbbrev" );
			//Function test:setUp
			{
			IMethod methodsetUp187;
				IModelElement[] classTestCallbackCheckAbbrev186Childs = classTestCallbackCheckAbbrev186.getChildren();
				methodsetUp187 = ModelTestUtils.getAssertMethod( classTestCallbackCheckAbbrev186Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp187, new String[] {"self"} );
			}
			//Function test:check_abbrev
			{
			IMethod methodcheck_abbrev188;
				IModelElement[] classTestCallbackCheckAbbrev186Childs = classTestCallbackCheckAbbrev186.getChildren();
				methodcheck_abbrev188 = ModelTestUtils.getAssertMethod( classTestCallbackCheckAbbrev186Childs, "check_abbrev", 5 );
				ModelTestUtils.assertParameterNames( methodcheck_abbrev188, new String[] {"self", "option", "opt", "value", "parser"} );
			}
			//Function test:test_abbrev_callback_expansion
			{
			IMethod methodtest_abbrev_callback_expansion189;
				IModelElement[] classTestCallbackCheckAbbrev186Childs = classTestCallbackCheckAbbrev186.getChildren();
				methodtest_abbrev_callback_expansion189 = ModelTestUtils.getAssertMethod( classTestCallbackCheckAbbrev186Childs, "test_abbrev_callback_expansion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abbrev_callback_expansion189, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackVarArgs190;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackVarArgs190 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackVarArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp191;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodsetUp191 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp191, new String[] {"self"} );
			}
			//Function test:variable_args
			{
			IMethod methodvariable_args192;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodvariable_args192 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "variable_args", 5 );
				ModelTestUtils.assertParameterNames( methodvariable_args192, new String[] {"self", "option", "opt", "value", "parser"} );
			}
			//Function test:test_variable_args
			{
			IMethod methodtest_variable_args193;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodtest_variable_args193 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "test_variable_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_variable_args193, new String[] {"self"} );
			}
			//Function test:test_consume_separator_stop_at_option
			{
			IMethod methodtest_consume_separator_stop_at_option194;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodtest_consume_separator_stop_at_option194 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "test_consume_separator_stop_at_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_consume_separator_stop_at_option194, new String[] {"self"} );
			}
			//Function test:test_positional_arg_and_variable_args
			{
			IMethod methodtest_positional_arg_and_variable_args195;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodtest_positional_arg_and_variable_args195 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "test_positional_arg_and_variable_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_positional_arg_and_variable_args195, new String[] {"self"} );
			}
			//Function test:test_stop_at_option
			{
			IMethod methodtest_stop_at_option196;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodtest_stop_at_option196 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "test_stop_at_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_at_option196, new String[] {"self"} );
			}
			//Function test:test_stop_at_invalid_option
			{
			IMethod methodtest_stop_at_invalid_option197;
				IModelElement[] classTestCallbackVarArgs190Childs = classTestCallbackVarArgs190.getChildren();
				methodtest_stop_at_invalid_option197 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs190Childs, "test_stop_at_invalid_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_at_invalid_option197, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classConflictBase198;
			IModelElement[] moduleChilds = module.getChildren();
			classConflictBase198 = ModelTestUtils.getAssertClass( moduleChilds, "ConflictBase" );
			//Function test:setUp
			{
			IMethod methodsetUp199;
				IModelElement[] classConflictBase198Childs = classConflictBase198.getChildren();
				methodsetUp199 = ModelTestUtils.getAssertMethod( classConflictBase198Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp199, new String[] {"self"} );
			}
			//Function test:show_version
			{
			IMethod methodshow_version200;
				IModelElement[] classConflictBase198Childs = classConflictBase198.getChildren();
				methodshow_version200 = ModelTestUtils.getAssertMethod( classConflictBase198Childs, "show_version", 5 );
				ModelTestUtils.assertParameterNames( methodshow_version200, new String[] {"self", "option", "opt", "value", "parser"} );
			}
		}
		//Class test
		{
		IType classTestConflict201;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflict201 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflict" );
			//Function test:assert_conflict_error
			{
			IMethod methodassert_conflict_error202;
				IModelElement[] classTestConflict201Childs = classTestConflict201.getChildren();
				methodassert_conflict_error202 = ModelTestUtils.getAssertMethod( classTestConflict201Childs, "assert_conflict_error", 2 );
				ModelTestUtils.assertParameterNames( methodassert_conflict_error202, new String[] {"self", "func"} );
			}
			//Function test:test_conflict_error
			{
			IMethod methodtest_conflict_error203;
				IModelElement[] classTestConflict201Childs = classTestConflict201.getChildren();
				methodtest_conflict_error203 = ModelTestUtils.getAssertMethod( classTestConflict201Childs, "test_conflict_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_error203, new String[] {"self"} );
			}
			//Function test:test_conflict_error_group
			{
			IMethod methodtest_conflict_error_group204;
				IModelElement[] classTestConflict201Childs = classTestConflict201.getChildren();
				methodtest_conflict_error_group204 = ModelTestUtils.getAssertMethod( classTestConflict201Childs, "test_conflict_error_group", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_error_group204, new String[] {"self"} );
			}
			//Function test:test_no_such_conflict_handler
			{
			IMethod methodtest_no_such_conflict_handler205;
				IModelElement[] classTestConflict201Childs = classTestConflict201.getChildren();
				methodtest_no_such_conflict_handler205 = ModelTestUtils.getAssertMethod( classTestConflict201Childs, "test_no_such_conflict_handler", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_such_conflict_handler205, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestConflictResolve206;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflictResolve206 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflictResolve" );
			//Function test:setUp
			{
			IMethod methodsetUp207;
				IModelElement[] classTestConflictResolve206Childs = classTestConflictResolve206.getChildren();
				methodsetUp207 = ModelTestUtils.getAssertMethod( classTestConflictResolve206Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp207, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve
			{
			IMethod methodtest_conflict_resolve208;
				IModelElement[] classTestConflictResolve206Childs = classTestConflictResolve206.getChildren();
				methodtest_conflict_resolve208 = ModelTestUtils.getAssertMethod( classTestConflictResolve206Childs, "test_conflict_resolve", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve208, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_help
			{
			IMethod methodtest_conflict_resolve_help209;
				IModelElement[] classTestConflictResolve206Childs = classTestConflictResolve206.getChildren();
				methodtest_conflict_resolve_help209 = ModelTestUtils.getAssertMethod( classTestConflictResolve206Childs, "test_conflict_resolve_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_help209, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_short_opt
			{
			IMethod methodtest_conflict_resolve_short_opt210;
				IModelElement[] classTestConflictResolve206Childs = classTestConflictResolve206.getChildren();
				methodtest_conflict_resolve_short_opt210 = ModelTestUtils.getAssertMethod( classTestConflictResolve206Childs, "test_conflict_resolve_short_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_short_opt210, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_long_opt
			{
			IMethod methodtest_conflict_resolve_long_opt211;
				IModelElement[] classTestConflictResolve206Childs = classTestConflictResolve206.getChildren();
				methodtest_conflict_resolve_long_opt211 = ModelTestUtils.getAssertMethod( classTestConflictResolve206Childs, "test_conflict_resolve_long_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_long_opt211, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_long_opts
			{
			IMethod methodtest_conflict_resolve_long_opts212;
				IModelElement[] classTestConflictResolve206Childs = classTestConflictResolve206.getChildren();
				methodtest_conflict_resolve_long_opts212 = ModelTestUtils.getAssertMethod( classTestConflictResolve206Childs, "test_conflict_resolve_long_opts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_long_opts212, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestConflictOverride213;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflictOverride213 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflictOverride" );
			//Function test:setUp
			{
			IMethod methodsetUp214;
				IModelElement[] classTestConflictOverride213Childs = classTestConflictOverride213.getChildren();
				methodsetUp214 = ModelTestUtils.getAssertMethod( classTestConflictOverride213Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp214, new String[] {"self"} );
			}
			//Function test:test_conflict_override_opts
			{
			IMethod methodtest_conflict_override_opts215;
				IModelElement[] classTestConflictOverride213Childs = classTestConflictOverride213.getChildren();
				methodtest_conflict_override_opts215 = ModelTestUtils.getAssertMethod( classTestConflictOverride213Childs, "test_conflict_override_opts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_override_opts215, new String[] {"self"} );
			}
			//Function test:test_conflict_override_help
			{
			IMethod methodtest_conflict_override_help216;
				IModelElement[] classTestConflictOverride213Childs = classTestConflictOverride213.getChildren();
				methodtest_conflict_override_help216 = ModelTestUtils.getAssertMethod( classTestConflictOverride213Childs, "test_conflict_override_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_override_help216, new String[] {"self"} );
			}
			//Function test:test_conflict_override_args
			{
			IMethod methodtest_conflict_override_args217;
				IModelElement[] classTestConflictOverride213Childs = classTestConflictOverride213.getChildren();
				methodtest_conflict_override_args217 = ModelTestUtils.getAssertMethod( classTestConflictOverride213Childs, "test_conflict_override_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_override_args217, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_expected_help_basic");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_expected_help_long_opts_first");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_expected_help_title_formatter");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_expected_help_short_lines");
		}
		//Class test
		{
		IType classTestHelp218;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHelp218 = ModelTestUtils.getAssertClass( moduleChilds, "TestHelp" );
			//Function test:setUp
			{
			IMethod methodsetUp219;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodsetUp219 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp219, new String[] {"self"} );
			}
			//Function test:make_parser
			{
			IMethod methodmake_parser220;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodmake_parser220 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "make_parser", 2 );
				ModelTestUtils.assertParameterNames( methodmake_parser220, new String[] {"self", "columns"} );
			}
			//Function test:assertHelpEquals
			{
			IMethod methodassertHelpEquals221;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodassertHelpEquals221 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "assertHelpEquals", 2 );
				ModelTestUtils.assertParameterNames( methodassertHelpEquals221, new String[] {"self", "expected_output"} );
			}
			//Function test:test_help
			{
			IMethod methodtest_help222;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodtest_help222 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "test_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help222, new String[] {"self"} );
			}
			//Function test:test_help_old_usage
			{
			IMethod methodtest_help_old_usage223;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodtest_help_old_usage223 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "test_help_old_usage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_old_usage223, new String[] {"self"} );
			}
			//Function test:test_help_long_opts_first
			{
			IMethod methodtest_help_long_opts_first224;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodtest_help_long_opts_first224 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "test_help_long_opts_first", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_long_opts_first224, new String[] {"self"} );
			}
			//Function test:test_help_title_formatter
			{
			IMethod methodtest_help_title_formatter225;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodtest_help_title_formatter225 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "test_help_title_formatter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_title_formatter225, new String[] {"self"} );
			}
			//Function test:test_wrap_columns
			{
			IMethod methodtest_wrap_columns226;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodtest_wrap_columns226 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "test_wrap_columns", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrap_columns226, new String[] {"self"} );
			}
			//Function test:test_help_description_groups
			{
			IMethod methodtest_help_description_groups227;
				IModelElement[] classTestHelp218Childs = classTestHelp218.getChildren();
				methodtest_help_description_groups227 = ModelTestUtils.getAssertMethod( classTestHelp218Childs, "test_help_description_groups", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_description_groups227, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMatchAbbrev228;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMatchAbbrev228 = ModelTestUtils.getAssertClass( moduleChilds, "TestMatchAbbrev" );
			//Function test:test_match_abbrev
			{
			IMethod methodtest_match_abbrev229;
				IModelElement[] classTestMatchAbbrev228Childs = classTestMatchAbbrev228.getChildren();
				methodtest_match_abbrev229 = ModelTestUtils.getAssertMethod( classTestMatchAbbrev228Childs, "test_match_abbrev", 1 );
				ModelTestUtils.assertParameterNames( methodtest_match_abbrev229, new String[] {"self"} );
			}
			//Function test:test_match_abbrev_error
			{
			IMethod methodtest_match_abbrev_error230;
				IModelElement[] classTestMatchAbbrev228Childs = classTestMatchAbbrev228.getChildren();
				methodtest_match_abbrev_error230 = ModelTestUtils.getAssertMethod( classTestMatchAbbrev228Childs, "test_match_abbrev_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_match_abbrev_error230, new String[] {"self"} );
			}
		}
		//Function test:_testclasses
		{
		IMethod method_testclasses231;
			IModelElement[] moduleChilds = module.getChildren();
			method_testclasses231 = ModelTestUtils.getAssertMethod( moduleChilds, "_testclasses", 0 );
		}
		//Function test:suite
		{
		IMethod methodsuite232;
			IModelElement[] moduleChilds = module.getChildren();
			methodsuite232 = ModelTestUtils.getAssertMethod( moduleChilds, "suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main233;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main233 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen99( ) throws Exception {
		String prj = "pytests_1";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_fpformat.py"));

		assertNotNull("Module test_fpformat.py not found", module);
		assertEquals("test_fpformat.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "StringType");
		}
		//Class test
		{
		IType classFpformatTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classFpformatTest0 = ModelTestUtils.getAssertClass( moduleChilds, "FpformatTest" );
			//Function test:checkFix
			{
			IMethod methodcheckFix1;
				IModelElement[] classFpformatTest0Childs = classFpformatTest0.getChildren();
				methodcheckFix1 = ModelTestUtils.getAssertMethod( classFpformatTest0Childs, "checkFix", 3 );
				ModelTestUtils.assertParameterNames( methodcheckFix1, new String[] {"self", "n", "digits"} );
			}
			//Function test:checkSci
			{
			IMethod methodcheckSci2;
				IModelElement[] classFpformatTest0Childs = classFpformatTest0.getChildren();
				methodcheckSci2 = ModelTestUtils.getAssertMethod( classFpformatTest0Childs, "checkSci", 3 );
				ModelTestUtils.assertParameterNames( methodcheckSci2, new String[] {"self", "n", "digits"} );
			}
			//Function test:test_basic_cases
			{
			IMethod methodtest_basic_cases3;
				IModelElement[] classFpformatTest0Childs = classFpformatTest0.getChildren();
				methodtest_basic_cases3 = ModelTestUtils.getAssertMethod( classFpformatTest0Childs, "test_basic_cases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_cases3, new String[] {"self"} );
			}
			//Function test:test_reasonable_values
			{
			IMethod methodtest_reasonable_values4;
				IModelElement[] classFpformatTest0Childs = classFpformatTest0.getChildren();
				methodtest_reasonable_values4 = ModelTestUtils.getAssertMethod( classFpformatTest0Childs, "test_reasonable_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reasonable_values4, new String[] {"self"} );
			}
			//Function test:test_failing_values
			{
			IMethod methodtest_failing_values5;
				IModelElement[] classFpformatTest0Childs = classFpformatTest0.getChildren();
				methodtest_failing_values5 = ModelTestUtils.getAssertMethod( classFpformatTest0Childs, "test_failing_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_failing_values5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}

}
	