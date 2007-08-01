
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
		setUpScriptProjectTo( "pytests_1", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		IScriptProject scriptProject = setUpScriptProject("pytests_1");
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
					{
						IModelElement[] classUnicodeCompat31Childs = classUnicodeCompat31.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classUnicodeCompat31Childs, "x");
					}
					//Function test:__unicode__
					{
					IMethod method__unicode__34;
						IModelElement[] classUnicodeCompat31Childs = classUnicodeCompat31.getChildren();
						method__unicode__34 = ModelTestUtils.getAssertMethod( classUnicodeCompat31Childs, "__unicode__", 1 );
						ModelTestUtils.assertParameterNames( method__unicode__34, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classStringCompat35;
					IModelElement[] methodtest_constructor29Childs = methodtest_constructor29.getChildren();
					classStringCompat35 = ModelTestUtils.getAssertClass( methodtest_constructor29Childs, "StringCompat" );
					//Function test:__init__
					{
					IMethod method__init__36;
						IModelElement[] classStringCompat35Childs = classStringCompat35.getChildren();
						method__init__36 = ModelTestUtils.getAssertMethod( classStringCompat35Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__36, new String[] {"self", "x"} );
					}
					//Function test:__str__
					{
					IMethod method__str__37;
						IModelElement[] classStringCompat35Childs = classStringCompat35.getChildren();
						method__str__37 = ModelTestUtils.getAssertMethod( classStringCompat35Childs, "__str__", 1 );
						ModelTestUtils.assertParameterNames( method__str__37, new String[] {"self"} );
					}
				}
			}
			//Function test:test_codecs_utf7
			{
			IMethod methodtest_codecs_utf738;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_utf738 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_utf7", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_utf738, new String[] {"self"} );
			}
			//Function test:test_codecs_utf8
			{
			IMethod methodtest_codecs_utf839;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_utf839 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_utf8", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_utf839, new String[] {"self"} );
			}
			//Function test:test_codecs_idna
			{
			IMethod methodtest_codecs_idna40;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_idna40 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_idna", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_idna40, new String[] {"self"} );
			}
			//Function test:test_codecs_errors
			{
			IMethod methodtest_codecs_errors41;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_errors41 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_errors41, new String[] {"self"} );
				//Function test:search_function
				{
				IMethod methodsearch_function42;
					IModelElement[] methodtest_codecs_errors41Childs = methodtest_codecs_errors41.getChildren();
					methodsearch_function42 = ModelTestUtils.getAssertMethod( methodtest_codecs_errors41Childs, "search_function", 1 );
					ModelTestUtils.assertParameterNames( methodsearch_function42, new String[] {"encoding"} );
					//Function test:decode1
					{
					IMethod methoddecode143;
						IModelElement[] methodsearch_function42Childs = methodsearch_function42.getChildren();
						methoddecode143 = ModelTestUtils.getAssertMethod( methodsearch_function42Childs, "decode1", 2 );
						ModelTestUtils.assertParameterNames( methoddecode143, new String[] {"input", "errors"} );
					}
					//Function test:encode1
					{
					IMethod methodencode144;
						IModelElement[] methodsearch_function42Childs = methodsearch_function42.getChildren();
						methodencode144 = ModelTestUtils.getAssertMethod( methodsearch_function42Childs, "encode1", 2 );
						ModelTestUtils.assertParameterNames( methodencode144, new String[] {"input", "errors"} );
					}
					//Function test:encode2
					{
					IMethod methodencode245;
						IModelElement[] methodsearch_function42Childs = methodsearch_function42.getChildren();
						methodencode245 = ModelTestUtils.getAssertMethod( methodsearch_function42Childs, "encode2", 2 );
						ModelTestUtils.assertParameterNames( methodencode245, new String[] {"input", "errors"} );
					}
					//Function test:decode2
					{
					IMethod methoddecode246;
						IModelElement[] methodsearch_function42Childs = methodsearch_function42.getChildren();
						methoddecode246 = ModelTestUtils.getAssertMethod( methodsearch_function42Childs, "decode2", 2 );
						ModelTestUtils.assertParameterNames( methoddecode246, new String[] {"input", "errors"} );
					}
				}
			}
			//Function test:test_codecs
			{
			IMethod methodtest_codecs47;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs47 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs47, new String[] {"self"} );
			}
			//Function test:test_codecs_charmap
			{
			IMethod methodtest_codecs_charmap48;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_codecs_charmap48 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_codecs_charmap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_codecs_charmap48, new String[] {"self"} );
			}
			//Function test:test_concatenation
			{
			IMethod methodtest_concatenation49;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_concatenation49 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_concatenation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_concatenation49, new String[] {"self"} );
			}
			//Function test:test_printing
			{
			IMethod methodtest_printing50;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_printing50 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_printing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_printing50, new String[] {"self"} );
				//Class test
				{
				IType classBitBucket51;
					IModelElement[] methodtest_printing50Childs = methodtest_printing50.getChildren();
					classBitBucket51 = ModelTestUtils.getAssertClass( methodtest_printing50Childs, "BitBucket" );
					//Function test:write
					{
					IMethod methodwrite52;
						IModelElement[] classBitBucket51Childs = classBitBucket51.getChildren();
						methodwrite52 = ModelTestUtils.getAssertMethod( classBitBucket51Childs, "write", 2 );
						ModelTestUtils.assertParameterNames( methodwrite52, new String[] {"self", "text"} );
					}
				}
			}
			//Function test:test_ucs4
			{
			IMethod methodtest_ucs453;
				IModelElement[] classUnicodeTest0Childs = classUnicodeTest0.getChildren();
				methodtest_ucs453 = ModelTestUtils.getAssertMethod( classUnicodeTest0Childs, "test_ucs4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ucs453, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main54;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main54 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty19Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty19Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty19Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty19Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty19Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsEmpty19Childs = classTestBasicOpsEmpty19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsEmpty19Childs, "repr");
			}
		}
		//Class test
		{
		IType classTestBasicOpsSingleton22;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsSingleton22 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp23;
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				methodsetUp23 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton22Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp23, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton22Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton22Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton22Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton22Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton22Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsSingleton22Childs, "repr");
			}
			//Function test:test_in
			{
			IMethod methodtest_in25;
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				methodtest_in25 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton22Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in25, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in26;
				IModelElement[] classTestBasicOpsSingleton22Childs = classTestBasicOpsSingleton22.getChildren();
				methodtest_not_in26 = ModelTestUtils.getAssertMethod( classTestBasicOpsSingleton22Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTuple27;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTuple27 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp28;
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				methodsetUp28 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple27Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp28, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple27Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple27Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple27Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple27Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple27Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTuple27Childs, "repr");
			}
			//Function test:test_in
			{
			IMethod methodtest_in30;
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				methodtest_in30 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple27Childs, "test_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in30, new String[] {"self"} );
			}
			//Function test:test_not_in
			{
			IMethod methodtest_not_in31;
				IModelElement[] classTestBasicOpsTuple27Childs = classTestBasicOpsTuple27.getChildren();
				methodtest_not_in31 = ModelTestUtils.getAssertMethod( classTestBasicOpsTuple27Childs, "test_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_in31, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBasicOpsTriple32;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOpsTriple32 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOpsTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp33;
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				methodsetUp33 = ModelTestUtils.getAssertMethod( classTestBasicOpsTriple32Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp33, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple32Childs, "case");
			}
			{
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple32Childs, "values");
			}
			{
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple32Childs, "set");
			}
			{
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple32Childs, "dup");
			}
			{
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple32Childs, "length");
			}
			{
				IModelElement[] classTestBasicOpsTriple32Childs = classTestBasicOpsTriple32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBasicOpsTriple32Childs, "repr");
			}
		}
		//Function test:baditer
		{
		IMethod methodbaditer35;
			IModelElement[] moduleChilds = module.getChildren();
			methodbaditer35 = ModelTestUtils.getAssertMethod( moduleChilds, "baditer", 0 );
		}
		//Function test:gooditer
		{
		IMethod methodgooditer36;
			IModelElement[] moduleChilds = module.getChildren();
			methodgooditer36 = ModelTestUtils.getAssertMethod( moduleChilds, "gooditer", 0 );
		}
		//Class test
		{
		IType classTestExceptionPropagation37;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExceptionPropagation37 = ModelTestUtils.getAssertClass( moduleChilds, "TestExceptionPropagation" );
			//Function test:test_instanceWithException
			{
			IMethod methodtest_instanceWithException38;
				IModelElement[] classTestExceptionPropagation37Childs = classTestExceptionPropagation37.getChildren();
				methodtest_instanceWithException38 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation37Childs, "test_instanceWithException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instanceWithException38, new String[] {"self"} );
			}
			//Function test:test_instancesWithoutException
			{
			IMethod methodtest_instancesWithoutException39;
				IModelElement[] classTestExceptionPropagation37Childs = classTestExceptionPropagation37.getChildren();
				methodtest_instancesWithoutException39 = ModelTestUtils.getAssertMethod( classTestExceptionPropagation37Childs, "test_instancesWithoutException", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instancesWithoutException39, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSetOfSets40;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSetOfSets40 = ModelTestUtils.getAssertClass( moduleChilds, "TestSetOfSets" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor41;
				IModelElement[] classTestSetOfSets40Childs = classTestSetOfSets40.getChildren();
				methodtest_constructor41 = ModelTestUtils.getAssertMethod( classTestSetOfSets40Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor41, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBinaryOps42;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBinaryOps42 = ModelTestUtils.getAssertClass( moduleChilds, "TestBinaryOps" );
			//Function test:setUp
			{
			IMethod methodsetUp43;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodsetUp43 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp43, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBinaryOps42Childs, "set");
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq45;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_eq45 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq45, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset46;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_union_subset46 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset46, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset47;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_union_superset47 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset47, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap48;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_union_overlap48 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap48, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap49;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_union_non_overlap49 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap49, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset50;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_intersection_subset50 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset50, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset51;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_intersection_superset51 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset51, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap52;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_intersection_overlap52 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap52, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap53;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_intersection_non_overlap53 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap53, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset54;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_sym_difference_subset54 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset54, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset55;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_sym_difference_superset55 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset55, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap56;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_sym_difference_overlap56 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap56, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap57;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_sym_difference_non_overlap57 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap57, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp58;
				IModelElement[] classTestBinaryOps42Childs = classTestBinaryOps42.getChildren();
				methodtest_cmp58 = ModelTestUtils.getAssertMethod( classTestBinaryOps42Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp58, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestUpdateOps59;
			IModelElement[] moduleChilds = module.getChildren();
			classTestUpdateOps59 = ModelTestUtils.getAssertClass( moduleChilds, "TestUpdateOps" );
			//Function test:setUp
			{
			IMethod methodsetUp60;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodsetUp60 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp60, new String[] {"self"} );
			}
			//Function test:test_union_subset
			{
			IMethod methodtest_union_subset61;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_union_subset61 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_union_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_subset61, new String[] {"self"} );
			}
			//Function test:test_union_superset
			{
			IMethod methodtest_union_superset62;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_union_superset62 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_union_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_superset62, new String[] {"self"} );
			}
			//Function test:test_union_overlap
			{
			IMethod methodtest_union_overlap63;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_union_overlap63 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_union_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_overlap63, new String[] {"self"} );
			}
			//Function test:test_union_non_overlap
			{
			IMethod methodtest_union_non_overlap64;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_union_non_overlap64 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_union_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_non_overlap64, new String[] {"self"} );
			}
			//Function test:test_union_method_call
			{
			IMethod methodtest_union_method_call65;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_union_method_call65 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_union_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_method_call65, new String[] {"self"} );
			}
			//Function test:test_intersection_subset
			{
			IMethod methodtest_intersection_subset66;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_intersection_subset66 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_intersection_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_subset66, new String[] {"self"} );
			}
			//Function test:test_intersection_superset
			{
			IMethod methodtest_intersection_superset67;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_intersection_superset67 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_intersection_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_superset67, new String[] {"self"} );
			}
			//Function test:test_intersection_overlap
			{
			IMethod methodtest_intersection_overlap68;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_intersection_overlap68 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_intersection_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_overlap68, new String[] {"self"} );
			}
			//Function test:test_intersection_non_overlap
			{
			IMethod methodtest_intersection_non_overlap69;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_intersection_non_overlap69 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_intersection_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_non_overlap69, new String[] {"self"} );
			}
			//Function test:test_intersection_method_call
			{
			IMethod methodtest_intersection_method_call70;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_intersection_method_call70 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_intersection_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_method_call70, new String[] {"self"} );
			}
			//Function test:test_sym_difference_subset
			{
			IMethod methodtest_sym_difference_subset71;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_sym_difference_subset71 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_sym_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_subset71, new String[] {"self"} );
			}
			//Function test:test_sym_difference_superset
			{
			IMethod methodtest_sym_difference_superset72;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_sym_difference_superset72 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_sym_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_superset72, new String[] {"self"} );
			}
			//Function test:test_sym_difference_overlap
			{
			IMethod methodtest_sym_difference_overlap73;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_sym_difference_overlap73 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_sym_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_overlap73, new String[] {"self"} );
			}
			//Function test:test_sym_difference_non_overlap
			{
			IMethod methodtest_sym_difference_non_overlap74;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_sym_difference_non_overlap74 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_sym_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_non_overlap74, new String[] {"self"} );
			}
			//Function test:test_sym_difference_method_call
			{
			IMethod methodtest_sym_difference_method_call75;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_sym_difference_method_call75 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_sym_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_method_call75, new String[] {"self"} );
			}
			//Function test:test_difference_subset
			{
			IMethod methodtest_difference_subset76;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_difference_subset76 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_difference_subset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_subset76, new String[] {"self"} );
			}
			//Function test:test_difference_superset
			{
			IMethod methodtest_difference_superset77;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_difference_superset77 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_difference_superset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_superset77, new String[] {"self"} );
			}
			//Function test:test_difference_overlap
			{
			IMethod methodtest_difference_overlap78;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_difference_overlap78 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_difference_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_overlap78, new String[] {"self"} );
			}
			//Function test:test_difference_non_overlap
			{
			IMethod methodtest_difference_non_overlap79;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_difference_non_overlap79 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_difference_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_non_overlap79, new String[] {"self"} );
			}
			//Function test:test_difference_method_call
			{
			IMethod methodtest_difference_method_call80;
				IModelElement[] classTestUpdateOps59Childs = classTestUpdateOps59.getChildren();
				methodtest_difference_method_call80 = ModelTestUtils.getAssertMethod( classTestUpdateOps59Childs, "test_difference_method_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_method_call80, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMutate81;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMutate81 = ModelTestUtils.getAssertClass( moduleChilds, "TestMutate" );
			//Function test:setUp
			{
			IMethod methodsetUp82;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodsetUp82 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp82, new String[] {"self"} );
			}
			{
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMutate81Childs, "values");
			}
			{
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMutate81Childs, "set");
			}
			//Function test:test_add_present
			{
			IMethod methodtest_add_present84;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_add_present84 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_add_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_present84, new String[] {"self"} );
			}
			//Function test:test_add_absent
			{
			IMethod methodtest_add_absent85;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_add_absent85 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_add_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_absent85, new String[] {"self"} );
			}
			//Function test:test_add_until_full
			{
			IMethod methodtest_add_until_full86;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_add_until_full86 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_add_until_full", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_until_full86, new String[] {"self"} );
			}
			//Function test:test_remove_present
			{
			IMethod methodtest_remove_present87;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_remove_present87 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_remove_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_present87, new String[] {"self"} );
			}
			//Function test:test_remove_absent
			{
			IMethod methodtest_remove_absent88;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_remove_absent88 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_remove_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_absent88, new String[] {"self"} );
			}
			//Function test:test_remove_until_empty
			{
			IMethod methodtest_remove_until_empty89;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_remove_until_empty89 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_remove_until_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_until_empty89, new String[] {"self"} );
			}
			//Function test:test_discard_present
			{
			IMethod methodtest_discard_present90;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_discard_present90 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_discard_present", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_present90, new String[] {"self"} );
			}
			//Function test:test_discard_absent
			{
			IMethod methodtest_discard_absent91;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_discard_absent91 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_discard_absent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_discard_absent91, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear92;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_clear92 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear92, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop93;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_pop93 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop93, new String[] {"self"} );
			}
			//Function test:test_update_empty_tuple
			{
			IMethod methodtest_update_empty_tuple94;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_update_empty_tuple94 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_update_empty_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_empty_tuple94, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_overlap
			{
			IMethod methodtest_update_unit_tuple_overlap95;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_update_unit_tuple_overlap95 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_update_unit_tuple_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_overlap95, new String[] {"self"} );
			}
			//Function test:test_update_unit_tuple_non_overlap
			{
			IMethod methodtest_update_unit_tuple_non_overlap96;
				IModelElement[] classTestMutate81Childs = classTestMutate81.getChildren();
				methodtest_update_unit_tuple_non_overlap96 = ModelTestUtils.getAssertMethod( classTestMutate81Childs, "test_update_unit_tuple_non_overlap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update_unit_tuple_non_overlap96, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsets97;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsets97 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsets" );
			{
				IModelElement[] classTestSubsets97Childs = classTestSubsets97.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets97Childs, "case2method");
			}
			{
				IModelElement[] classTestSubsets97Childs = classTestSubsets97.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsets97Childs, "reverse");
			}
			//Function test:test_issubset
			{
			IMethod methodtest_issubset98;
				IModelElement[] classTestSubsets97Childs = classTestSubsets97.getChildren();
				methodtest_issubset98 = ModelTestUtils.getAssertMethod( classTestSubsets97Childs, "test_issubset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubset98, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSubsetEqualEmpty99;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualEmpty99 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualEmpty" );
			{
				IModelElement[] classTestSubsetEqualEmpty99Childs = classTestSubsetEqualEmpty99.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty99Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty99Childs = classTestSubsetEqualEmpty99.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty99Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty99Childs = classTestSubsetEqualEmpty99.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty99Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualEmpty99Childs = classTestSubsetEqualEmpty99.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualEmpty99Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEqualNonEmpty100;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEqualNonEmpty100 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEqualNonEmpty" );
			{
				IModelElement[] classTestSubsetEqualNonEmpty100Childs = classTestSubsetEqualNonEmpty100.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty100Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty100Childs = classTestSubsetEqualNonEmpty100.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty100Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty100Childs = classTestSubsetEqualNonEmpty100.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty100Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEqualNonEmpty100Childs = classTestSubsetEqualNonEmpty100.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEqualNonEmpty100Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetEmptyNonEmpty101;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetEmptyNonEmpty101 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetEmptyNonEmpty" );
			{
				IModelElement[] classTestSubsetEmptyNonEmpty101Childs = classTestSubsetEmptyNonEmpty101.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty101Childs, "left");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty101Childs = classTestSubsetEmptyNonEmpty101.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty101Childs, "right");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty101Childs = classTestSubsetEmptyNonEmpty101.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty101Childs, "name");
			}
			{
				IModelElement[] classTestSubsetEmptyNonEmpty101Childs = classTestSubsetEmptyNonEmpty101.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetEmptyNonEmpty101Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetPartial102;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetPartial102 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetPartial" );
			{
				IModelElement[] classTestSubsetPartial102Childs = classTestSubsetPartial102.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial102Childs, "left");
			}
			{
				IModelElement[] classTestSubsetPartial102Childs = classTestSubsetPartial102.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial102Childs, "right");
			}
			{
				IModelElement[] classTestSubsetPartial102Childs = classTestSubsetPartial102.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial102Childs, "name");
			}
			{
				IModelElement[] classTestSubsetPartial102Childs = classTestSubsetPartial102.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetPartial102Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestSubsetNonOverlap103;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSubsetNonOverlap103 = ModelTestUtils.getAssertClass( moduleChilds, "TestSubsetNonOverlap" );
			{
				IModelElement[] classTestSubsetNonOverlap103Childs = classTestSubsetNonOverlap103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap103Childs, "left");
			}
			{
				IModelElement[] classTestSubsetNonOverlap103Childs = classTestSubsetNonOverlap103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap103Childs, "right");
			}
			{
				IModelElement[] classTestSubsetNonOverlap103Childs = classTestSubsetNonOverlap103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap103Childs, "name");
			}
			{
				IModelElement[] classTestSubsetNonOverlap103Childs = classTestSubsetNonOverlap103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSubsetNonOverlap103Childs, "cases");
			}
		}
		//Class test
		{
		IType classTestOnlySetsInBinaryOps104;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsInBinaryOps104 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsInBinaryOps" );
			//Function test:test_eq_ne
			{
			IMethod methodtest_eq_ne105;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_eq_ne105 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_eq_ne", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq_ne105, new String[] {"self"} );
			}
			//Function test:test_ge_gt_le_lt
			{
			IMethod methodtest_ge_gt_le_lt106;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_ge_gt_le_lt106 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_ge_gt_le_lt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ge_gt_le_lt106, new String[] {"self"} );
			}
			//Function test:test_union_update_operator
			{
			IMethod methodtest_union_update_operator107;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_union_update_operator107 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_union_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_update_operator107, new String[] {"self"} );
			}
			//Function test:test_union_update
			{
			IMethod methodtest_union_update108;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_union_update108 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_union_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union_update108, new String[] {"self"} );
			}
			//Function test:test_union
			{
			IMethod methodtest_union109;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_union109 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_union", 1 );
				ModelTestUtils.assertParameterNames( methodtest_union109, new String[] {"self"} );
			}
			//Function test:test_intersection_update_operator
			{
			IMethod methodtest_intersection_update_operator110;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_intersection_update_operator110 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_intersection_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update_operator110, new String[] {"self"} );
			}
			//Function test:test_intersection_update
			{
			IMethod methodtest_intersection_update111;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_intersection_update111 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_intersection_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection_update111, new String[] {"self"} );
			}
			//Function test:test_intersection
			{
			IMethod methodtest_intersection112;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_intersection112 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_intersection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intersection112, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update_operator
			{
			IMethod methodtest_sym_difference_update_operator113;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_sym_difference_update_operator113 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_sym_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update_operator113, new String[] {"self"} );
			}
			//Function test:test_sym_difference_update
			{
			IMethod methodtest_sym_difference_update114;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_sym_difference_update114 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_sym_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference_update114, new String[] {"self"} );
			}
			//Function test:test_sym_difference
			{
			IMethod methodtest_sym_difference115;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_sym_difference115 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_sym_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sym_difference115, new String[] {"self"} );
			}
			//Function test:test_difference_update_operator
			{
			IMethod methodtest_difference_update_operator116;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_difference_update_operator116 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_difference_update_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update_operator116, new String[] {"self"} );
			}
			//Function test:test_difference_update
			{
			IMethod methodtest_difference_update117;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_difference_update117 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_difference_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference_update117, new String[] {"self"} );
			}
			//Function test:test_difference
			{
			IMethod methodtest_difference118;
				IModelElement[] classTestOnlySetsInBinaryOps104Childs = classTestOnlySetsInBinaryOps104.getChildren();
				methodtest_difference118 = ModelTestUtils.getAssertMethod( classTestOnlySetsInBinaryOps104Childs, "test_difference", 1 );
				ModelTestUtils.assertParameterNames( methodtest_difference118, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOnlySetsNumeric119;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsNumeric119 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsNumeric" );
			//Function test:setUp
			{
			IMethod methodsetUp120;
				IModelElement[] classTestOnlySetsNumeric119Childs = classTestOnlySetsNumeric119.getChildren();
				methodsetUp120 = ModelTestUtils.getAssertMethod( classTestOnlySetsNumeric119Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp120, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsNumeric119Childs = classTestOnlySetsNumeric119.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsNumeric119Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsNumeric119Childs = classTestOnlySetsNumeric119.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsNumeric119Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsNumeric119Childs = classTestOnlySetsNumeric119.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsNumeric119Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsDict122;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsDict122 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsDict" );
			//Function test:setUp
			{
			IMethod methodsetUp123;
				IModelElement[] classTestOnlySetsDict122Childs = classTestOnlySetsDict122.getChildren();
				methodsetUp123 = ModelTestUtils.getAssertMethod( classTestOnlySetsDict122Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp123, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsDict122Childs = classTestOnlySetsDict122.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsDict122Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsDict122Childs = classTestOnlySetsDict122.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsDict122Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsDict122Childs = classTestOnlySetsDict122.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsDict122Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsOperator125;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsOperator125 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsOperator" );
			//Function test:setUp
			{
			IMethod methodsetUp126;
				IModelElement[] classTestOnlySetsOperator125Childs = classTestOnlySetsOperator125.getChildren();
				methodsetUp126 = ModelTestUtils.getAssertMethod( classTestOnlySetsOperator125Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp126, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsOperator125Childs = classTestOnlySetsOperator125.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsOperator125Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsOperator125Childs = classTestOnlySetsOperator125.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsOperator125Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsOperator125Childs = classTestOnlySetsOperator125.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsOperator125Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsTuple128;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsTuple128 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp129;
				IModelElement[] classTestOnlySetsTuple128Childs = classTestOnlySetsTuple128.getChildren();
				methodsetUp129 = ModelTestUtils.getAssertMethod( classTestOnlySetsTuple128Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp129, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsTuple128Childs = classTestOnlySetsTuple128.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsTuple128Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsTuple128Childs = classTestOnlySetsTuple128.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsTuple128Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsTuple128Childs = classTestOnlySetsTuple128.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsTuple128Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsString131;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsString131 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsString" );
			//Function test:setUp
			{
			IMethod methodsetUp132;
				IModelElement[] classTestOnlySetsString131Childs = classTestOnlySetsString131.getChildren();
				methodsetUp132 = ModelTestUtils.getAssertMethod( classTestOnlySetsString131Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp132, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsString131Childs = classTestOnlySetsString131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsString131Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsString131Childs = classTestOnlySetsString131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsString131Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsString131Childs = classTestOnlySetsString131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsString131Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsGenerator134;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsGenerator134 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsGenerator" );
			//Function test:setUp
			{
			IMethod methodsetUp135;
				IModelElement[] classTestOnlySetsGenerator134Childs = classTestOnlySetsGenerator134.getChildren();
				methodsetUp135 = ModelTestUtils.getAssertMethod( classTestOnlySetsGenerator134Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp135, new String[] {"self"} );
				//Function test:gen
				{
				IMethod methodgen136;
					IModelElement[] methodsetUp135Childs = methodsetUp135.getChildren();
					methodgen136 = ModelTestUtils.getAssertMethod( methodsetUp135Childs, "gen", 0 );
				}
			}
			{
				IModelElement[] classTestOnlySetsGenerator134Childs = classTestOnlySetsGenerator134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsGenerator134Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsGenerator134Childs = classTestOnlySetsGenerator134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsGenerator134Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsGenerator134Childs = classTestOnlySetsGenerator134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsGenerator134Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestOnlySetsofSets138;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOnlySetsofSets138 = ModelTestUtils.getAssertClass( moduleChilds, "TestOnlySetsofSets" );
			//Function test:setUp
			{
			IMethod methodsetUp139;
				IModelElement[] classTestOnlySetsofSets138Childs = classTestOnlySetsofSets138.getChildren();
				methodsetUp139 = ModelTestUtils.getAssertMethod( classTestOnlySetsofSets138Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp139, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOnlySetsofSets138Childs = classTestOnlySetsofSets138.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsofSets138Childs, "set");
			}
			{
				IModelElement[] classTestOnlySetsofSets138Childs = classTestOnlySetsofSets138.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsofSets138Childs, "other");
			}
			{
				IModelElement[] classTestOnlySetsofSets138Childs = classTestOnlySetsofSets138.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOnlySetsofSets138Childs, "otherIsIterable");
			}
		}
		//Class test
		{
		IType classTestCopying141;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopying141 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopying" );
			//Function test:test_copy
			{
			IMethod methodtest_copy142;
				IModelElement[] classTestCopying141Childs = classTestCopying141.getChildren();
				methodtest_copy142 = ModelTestUtils.getAssertMethod( classTestCopying141Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy142, new String[] {"self"} );
			}
			//Function test:test_deep_copy
			{
			IMethod methodtest_deep_copy143;
				IModelElement[] classTestCopying141Childs = classTestCopying141.getChildren();
				methodtest_deep_copy143 = ModelTestUtils.getAssertMethod( classTestCopying141Childs, "test_deep_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_deep_copy143, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCopyingEmpty144;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingEmpty144 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingEmpty" );
			//Function test:setUp
			{
			IMethod methodsetUp145;
				IModelElement[] classTestCopyingEmpty144Childs = classTestCopyingEmpty144.getChildren();
				methodsetUp145 = ModelTestUtils.getAssertMethod( classTestCopyingEmpty144Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp145, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingEmpty144Childs = classTestCopyingEmpty144.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingEmpty144Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingSingleton147;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingSingleton147 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingSingleton" );
			//Function test:setUp
			{
			IMethod methodsetUp148;
				IModelElement[] classTestCopyingSingleton147Childs = classTestCopyingSingleton147.getChildren();
				methodsetUp148 = ModelTestUtils.getAssertMethod( classTestCopyingSingleton147Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp148, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingSingleton147Childs = classTestCopyingSingleton147.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingSingleton147Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingTriple150;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTriple150 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTriple" );
			//Function test:setUp
			{
			IMethod methodsetUp151;
				IModelElement[] classTestCopyingTriple150Childs = classTestCopyingTriple150.getChildren();
				methodsetUp151 = ModelTestUtils.getAssertMethod( classTestCopyingTriple150Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp151, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingTriple150Childs = classTestCopyingTriple150.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingTriple150Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingTuple153;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingTuple153 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp154;
				IModelElement[] classTestCopyingTuple153Childs = classTestCopyingTuple153.getChildren();
				methodsetUp154 = ModelTestUtils.getAssertMethod( classTestCopyingTuple153Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp154, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingTuple153Childs = classTestCopyingTuple153.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingTuple153Childs, "set");
			}
		}
		//Class test
		{
		IType classTestCopyingNested156;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCopyingNested156 = ModelTestUtils.getAssertClass( moduleChilds, "TestCopyingNested" );
			//Function test:setUp
			{
			IMethod methodsetUp157;
				IModelElement[] classTestCopyingNested156Childs = classTestCopyingNested156.getChildren();
				methodsetUp157 = ModelTestUtils.getAssertMethod( classTestCopyingNested156Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp157, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCopyingNested156Childs = classTestCopyingNested156.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCopyingNested156Childs, "set");
			}
		}
		//Class test
		{
		IType classTestIdentities159;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIdentities159 = ModelTestUtils.getAssertClass( moduleChilds, "TestIdentities" );
			//Function test:setUp
			{
			IMethod methodsetUp160;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodsetUp160 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp160, new String[] {"self"} );
			}
			{
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestIdentities159Childs, "a");
			}
			{
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestIdentities159Childs, "b");
			}
			//Function test:test_binopsVsSubsets
			{
			IMethod methodtest_binopsVsSubsets162;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodtest_binopsVsSubsets162 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "test_binopsVsSubsets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binopsVsSubsets162, new String[] {"self"} );
			}
			//Function test:test_commutativity
			{
			IMethod methodtest_commutativity163;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodtest_commutativity163 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "test_commutativity", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commutativity163, new String[] {"self"} );
			}
			//Function test:test_reflexsive_relations
			{
			IMethod methodtest_reflexsive_relations164;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodtest_reflexsive_relations164 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "test_reflexsive_relations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reflexsive_relations164, new String[] {"self"} );
			}
			//Function test:test_summations
			{
			IMethod methodtest_summations165;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodtest_summations165 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "test_summations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_summations165, new String[] {"self"} );
			}
			//Function test:test_exclusion
			{
			IMethod methodtest_exclusion166;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodtest_exclusion166 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "test_exclusion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exclusion166, new String[] {"self"} );
			}
			//Function test:test_cardinality_relations
			{
			IMethod methodtest_cardinality_relations167;
				IModelElement[] classTestIdentities159Childs = classTestIdentities159.getChildren();
				methodtest_cardinality_relations167 = ModelTestUtils.getAssertMethod( classTestIdentities159Childs, "test_cardinality_relations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cardinality_relations167, new String[] {"self"} );
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
		IMethod methodtest_main168;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main168 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main168, new String[] {"verbose"} );
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
			{
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSeqDict6Childs, "keylist");
			}
			{
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSeqDict6Childs, "valuelist");
			}
			{
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSeqDict6Childs, "OP_ASSIGN");
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__9;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__getitem__9 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__9, new String[] {"self", "key"} );
			}
			//Function test:__setitem__
			{
			IMethod method__setitem__10;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__setitem__10 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__setitem__", 3 );
				ModelTestUtils.assertParameterNames( method__setitem__10, new String[] {"self", "key", "value"} );
			}
			//Function test:__delitem__
			{
			IMethod method__delitem__11;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				method__delitem__11 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "__delitem__", 2 );
				ModelTestUtils.assertParameterNames( method__delitem__11, new String[] {"self", "key"} );
			}
			//Function test:keys
			{
			IMethod methodkeys12;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				methodkeys12 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "keys", 1 );
				ModelTestUtils.assertParameterNames( methodkeys12, new String[] {"self"} );
			}
			//Function test:copy
			{
			IMethod methodcopy13;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				methodcopy13 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "copy", 1 );
				ModelTestUtils.assertParameterNames( methodcopy13, new String[] {"self"} );
			}
			//Function test:fromkeys
			{
			IMethod methodfromkeys14;
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				methodfromkeys14 = ModelTestUtils.getAssertMethod( classSeqDict6Childs, "fromkeys", 3 );
				ModelTestUtils.assertParameterNames( methodfromkeys14, new String[] {"cls", "keys", "value"} );
			}
			{
				IModelElement[] classSeqDict6Childs = classSeqDict6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSeqDict6Childs, "fromkeys");
			}
		}
		//Class test
		{
		IType classUserDictMixinTest15;
			IModelElement[] moduleChilds = module.getChildren();
			classUserDictMixinTest15 = ModelTestUtils.getAssertClass( moduleChilds, "UserDictMixinTest" );
			{
				IModelElement[] classUserDictMixinTest15Childs = classUserDictMixinTest15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserDictMixinTest15Childs, "type2test");
			}
			//Function test:test_all
			{
			IMethod methodtest_all16;
				IModelElement[] classUserDictMixinTest15Childs = classUserDictMixinTest15.getChildren();
				methodtest_all16 = ModelTestUtils.getAssertMethod( classUserDictMixinTest15Childs, "test_all", 1 );
				ModelTestUtils.assertParameterNames( methodtest_all16, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLockTests0Childs, "lock");
			}
			//Function test:test_initlock
			{
			IMethod methodtest_initlock3;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_initlock3 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_initlock", 1 );
				ModelTestUtils.assertParameterNames( methodtest_initlock3, new String[] {"self"} );
			}
			//Function test:test_release
			{
			IMethod methodtest_release4;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_release4 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_release", 1 );
				ModelTestUtils.assertParameterNames( methodtest_release4, new String[] {"self"} );
			}
			//Function test:test_improper_release
			{
			IMethod methodtest_improper_release5;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_improper_release5 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_improper_release", 1 );
				ModelTestUtils.assertParameterNames( methodtest_improper_release5, new String[] {"self"} );
			}
			//Function test:test_cond_acquire_success
			{
			IMethod methodtest_cond_acquire_success6;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_cond_acquire_success6 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_cond_acquire_success", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cond_acquire_success6, new String[] {"self"} );
			}
			//Function test:test_cond_acquire_fail
			{
			IMethod methodtest_cond_acquire_fail7;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_cond_acquire_fail7 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_cond_acquire_fail", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cond_acquire_fail7, new String[] {"self"} );
			}
			//Function test:test_uncond_acquire_success
			{
			IMethod methodtest_uncond_acquire_success8;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_uncond_acquire_success8 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_uncond_acquire_success", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uncond_acquire_success8, new String[] {"self"} );
			}
			//Function test:test_uncond_acquire_return_val
			{
			IMethod methodtest_uncond_acquire_return_val9;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_uncond_acquire_return_val9 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_uncond_acquire_return_val", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uncond_acquire_return_val9, new String[] {"self"} );
			}
			//Function test:test_uncond_acquire_blocking
			{
			IMethod methodtest_uncond_acquire_blocking10;
				IModelElement[] classLockTests0Childs = classLockTests0.getChildren();
				methodtest_uncond_acquire_blocking10 = ModelTestUtils.getAssertMethod( classLockTests0Childs, "test_uncond_acquire_blocking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uncond_acquire_blocking10, new String[] {"self"} );
				//Function test:delay_unlock
				{
				IMethod methoddelay_unlock11;
					IModelElement[] methodtest_uncond_acquire_blocking10Childs = methodtest_uncond_acquire_blocking10.getChildren();
					methoddelay_unlock11 = ModelTestUtils.getAssertMethod( methodtest_uncond_acquire_blocking10Childs, "delay_unlock", 2 );
					ModelTestUtils.assertParameterNames( methoddelay_unlock11, new String[] {"to_unlock", "delay"} );
				}
			}
		}
		//Class test
		{
		IType classMiscTests12;
			IModelElement[] moduleChilds = module.getChildren();
			classMiscTests12 = ModelTestUtils.getAssertClass( moduleChilds, "MiscTests" );
			//Function test:test_exit
			{
			IMethod methodtest_exit13;
				IModelElement[] classMiscTests12Childs = classMiscTests12.getChildren();
				methodtest_exit13 = ModelTestUtils.getAssertMethod( classMiscTests12Childs, "test_exit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exit13, new String[] {"self"} );
			}
			//Function test:test_ident
			{
			IMethod methodtest_ident14;
				IModelElement[] classMiscTests12Childs = classMiscTests12.getChildren();
				methodtest_ident14 = ModelTestUtils.getAssertMethod( classMiscTests12Childs, "test_ident", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ident14, new String[] {"self"} );
			}
			//Function test:test_LockType
			{
			IMethod methodtest_LockType15;
				IModelElement[] classMiscTests12Childs = classMiscTests12.getChildren();
				methodtest_LockType15 = ModelTestUtils.getAssertMethod( classMiscTests12Childs, "test_LockType", 1 );
				ModelTestUtils.assertParameterNames( methodtest_LockType15, new String[] {"self"} );
			}
			//Function test:test_interrupt_main
			{
			IMethod methodtest_interrupt_main16;
				IModelElement[] classMiscTests12Childs = classMiscTests12.getChildren();
				methodtest_interrupt_main16 = ModelTestUtils.getAssertMethod( classMiscTests12Childs, "test_interrupt_main", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interrupt_main16, new String[] {"self"} );
				//Function test:call_interrupt
				{
				IMethod methodcall_interrupt17;
					IModelElement[] methodtest_interrupt_main16Childs = methodtest_interrupt_main16.getChildren();
					methodcall_interrupt17 = ModelTestUtils.getAssertMethod( methodtest_interrupt_main16Childs, "call_interrupt", 0 );
				}
			}
			//Function test:test_interrupt_in_main
			{
			IMethod methodtest_interrupt_in_main18;
				IModelElement[] classMiscTests12Childs = classMiscTests12.getChildren();
				methodtest_interrupt_in_main18 = ModelTestUtils.getAssertMethod( classMiscTests12Childs, "test_interrupt_in_main", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interrupt_in_main18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadTests19;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadTests19 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadTests" );
			//Function test:test_arg_passing
			{
			IMethod methodtest_arg_passing20;
				IModelElement[] classThreadTests19Childs = classThreadTests19.getChildren();
				methodtest_arg_passing20 = ModelTestUtils.getAssertMethod( classThreadTests19Childs, "test_arg_passing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_arg_passing20, new String[] {"self"} );
				//Function test:arg_tester
				{
				IMethod methodarg_tester21;
					IModelElement[] methodtest_arg_passing20Childs = methodtest_arg_passing20.getChildren();
					methodarg_tester21 = ModelTestUtils.getAssertMethod( methodtest_arg_passing20Childs, "arg_tester", 3 );
					ModelTestUtils.assertParameterNames( methodarg_tester21, new String[] {"queue", "arg1", "arg2"} );
				}
			}
			//Function test:test_multi_creation
			{
			IMethod methodtest_multi_creation22;
				IModelElement[] classThreadTests19Childs = classThreadTests19.getChildren();
				methodtest_multi_creation22 = ModelTestUtils.getAssertMethod( classThreadTests19Childs, "test_multi_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multi_creation22, new String[] {"self"} );
				//Function test:queue_mark
				{
				IMethod methodqueue_mark23;
					IModelElement[] methodtest_multi_creation22Childs = methodtest_multi_creation22.getChildren();
					methodqueue_mark23 = ModelTestUtils.getAssertMethod( methodtest_multi_creation22Childs, "queue_mark", 2 );
					ModelTestUtils.assertParameterNames( methodqueue_mark23, new String[] {"queue", "delay"} );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main24 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main24, new String[] {"imported_module"} );
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
			{
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSampleClass1Childs, "val");
			}
			//Function test:double
			{
			IMethod methoddouble4;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methoddouble4 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "double", 1 );
				ModelTestUtils.assertParameterNames( methoddouble4, new String[] {"self"} );
			}
			//Function test:get
			{
			IMethod methodget5;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methodget5 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "get", 1 );
				ModelTestUtils.assertParameterNames( methodget5, new String[] {"self"} );
			}
			//Function test:a_staticmethod
			{
			IMethod methoda_staticmethod6;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methoda_staticmethod6 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "a_staticmethod", 1 );
				ModelTestUtils.assertParameterNames( methoda_staticmethod6, new String[] {"v"} );
			}
			{
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSampleClass1Childs, "a_staticmethod");
			}
			//Function test:a_classmethod
			{
			IMethod methoda_classmethod7;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				methoda_classmethod7 = ModelTestUtils.getAssertMethod( classSampleClass1Childs, "a_classmethod", 2 );
				ModelTestUtils.assertParameterNames( methoda_classmethod7, new String[] {"cls", "v"} );
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
			IType classNestedClass8;
				IModelElement[] classSampleClass1Childs = classSampleClass1.getChildren();
				classNestedClass8 = ModelTestUtils.getAssertClass( classSampleClass1Childs, "NestedClass" );
				//Function test:__init__
				{
				IMethod method__init__9;
					IModelElement[] classNestedClass8Childs = classNestedClass8.getChildren();
					method__init__9 = ModelTestUtils.getAssertMethod( classNestedClass8Childs, "__init__", 2 );
					ModelTestUtils.assertParameterNames( method__init__9, new String[] {"self", "val"} );
				}
				{
					IModelElement[] classNestedClass8Childs = classNestedClass8.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classNestedClass8Childs, "val");
				}
				//Function test:square
				{
				IMethod methodsquare11;
					IModelElement[] classNestedClass8Childs = classNestedClass8.getChildren();
					methodsquare11 = ModelTestUtils.getAssertMethod( classNestedClass8Childs, "square", 1 );
					ModelTestUtils.assertParameterNames( methodsquare11, new String[] {"self"} );
				}
				//Function test:get
				{
				IMethod methodget12;
					IModelElement[] classNestedClass8Childs = classNestedClass8.getChildren();
					methodget12 = ModelTestUtils.getAssertMethod( classNestedClass8Childs, "get", 1 );
					ModelTestUtils.assertParameterNames( methodget12, new String[] {"self"} );
				}
			}
		}
		//Class test
		{
		IType classSampleNewStyleClass13;
			IModelElement[] moduleChilds = module.getChildren();
			classSampleNewStyleClass13 = ModelTestUtils.getAssertClass( moduleChilds, "SampleNewStyleClass" );
			//Function test:__init__
			{
			IMethod method__init__14;
				IModelElement[] classSampleNewStyleClass13Childs = classSampleNewStyleClass13.getChildren();
				method__init__14 = ModelTestUtils.getAssertMethod( classSampleNewStyleClass13Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__14, new String[] {"self", "val"} );
			}
			{
				IModelElement[] classSampleNewStyleClass13Childs = classSampleNewStyleClass13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSampleNewStyleClass13Childs, "val");
			}
			//Function test:double
			{
			IMethod methoddouble16;
				IModelElement[] classSampleNewStyleClass13Childs = classSampleNewStyleClass13.getChildren();
				methoddouble16 = ModelTestUtils.getAssertMethod( classSampleNewStyleClass13Childs, "double", 1 );
				ModelTestUtils.assertParameterNames( methoddouble16, new String[] {"self"} );
			}
			//Function test:get
			{
			IMethod methodget17;
				IModelElement[] classSampleNewStyleClass13Childs = classSampleNewStyleClass13.getChildren();
				methodget17 = ModelTestUtils.getAssertMethod( classSampleNewStyleClass13Childs, "get", 1 );
				ModelTestUtils.assertParameterNames( methodget17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType class_FakeInput18;
			IModelElement[] moduleChilds = module.getChildren();
			class_FakeInput18 = ModelTestUtils.getAssertClass( moduleChilds, "_FakeInput" );
			//Function test:__init__
			{
			IMethod method__init__19;
				IModelElement[] class_FakeInput18Childs = class_FakeInput18.getChildren();
				method__init__19 = ModelTestUtils.getAssertMethod( class_FakeInput18Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__19, new String[] {"self", "lines"} );
			}
			{
				IModelElement[] class_FakeInput18Childs = class_FakeInput18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_FakeInput18Childs, "lines");
			}
			//Function test:readline
			{
			IMethod methodreadline21;
				IModelElement[] class_FakeInput18Childs = class_FakeInput18.getChildren();
				methodreadline21 = ModelTestUtils.getAssertMethod( class_FakeInput18Childs, "readline", 1 );
				ModelTestUtils.assertParameterNames( methodreadline21, new String[] {"self"} );
			}
		}
		//Function test:test_Example
		{
		IMethod methodtest_Example22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_Example22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_Example", 0 );
		}
		//Function test:test_DocTest
		{
		IMethod methodtest_DocTest23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTest23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTest", 0 );
		}
		//Function test:test_DocTestFinder
		{
		IMethod methodtest_DocTestFinder24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTestFinder24 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTestFinder", 0 );
		}
		//Function test:test_DocTestParser
		{
		IMethod methodtest_DocTestParser25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTestParser25 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTestParser", 0 );
		}
		//Class test
		{
		IType classtest_DocTestRunner26;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_DocTestRunner26 = ModelTestUtils.getAssertClass( moduleChilds, "test_DocTestRunner" );
			//Function test:basics
			{
			IMethod methodbasics27;
				IModelElement[] classtest_DocTestRunner26Childs = classtest_DocTestRunner26.getChildren();
				methodbasics27 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner26Childs, "basics", 0 );
			}
			//Function test:verbose_flag
			{
			IMethod methodverbose_flag28;
				IModelElement[] classtest_DocTestRunner26Childs = classtest_DocTestRunner26.getChildren();
				methodverbose_flag28 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner26Childs, "verbose_flag", 0 );
			}
			//Function test:exceptions
			{
			IMethod methodexceptions29;
				IModelElement[] classtest_DocTestRunner26Childs = classtest_DocTestRunner26.getChildren();
				methodexceptions29 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner26Childs, "exceptions", 0 );
			}
			//Function test:optionflags
			{
			IMethod methodoptionflags30;
				IModelElement[] classtest_DocTestRunner26Childs = classtest_DocTestRunner26.getChildren();
				methodoptionflags30 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner26Childs, "optionflags", 0 );
			}
			//Function test:option_directives
			{
			IMethod methodoption_directives31;
				IModelElement[] classtest_DocTestRunner26Childs = classtest_DocTestRunner26.getChildren();
				methodoption_directives31 = ModelTestUtils.getAssertMethod( classtest_DocTestRunner26Childs, "option_directives", 0 );
			}
		}
		//Function test:test_testsource
		{
		IMethod methodtest_testsource32;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_testsource32 = ModelTestUtils.getAssertMethod( moduleChilds, "test_testsource", 0 );
		}
		//Function test:test_debug
		{
		IMethod methodtest_debug33;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_debug33 = ModelTestUtils.getAssertMethod( moduleChilds, "test_debug", 0 );
		}
		//Function test:test_pdb_set_trace
		{
		IMethod methodtest_pdb_set_trace34;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_pdb_set_trace34 = ModelTestUtils.getAssertMethod( moduleChilds, "test_pdb_set_trace", 0 );
		}
		//Function test:test_pdb_set_trace_nested
		{
		IMethod methodtest_pdb_set_trace_nested35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_pdb_set_trace_nested35 = ModelTestUtils.getAssertMethod( moduleChilds, "test_pdb_set_trace_nested", 0 );
		}
		//Function test:test_DocTestSuite
		{
		IMethod methodtest_DocTestSuite36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocTestSuite36 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocTestSuite", 0 );
		}
		//Function test:test_DocFileSuite
		{
		IMethod methodtest_DocFileSuite37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_DocFileSuite37 = ModelTestUtils.getAssertMethod( moduleChilds, "test_DocFileSuite", 0 );
		}
		//Function test:test_trailing_space_in_test
		{
		IMethod methodtest_trailing_space_in_test38;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_trailing_space_in_test38 = ModelTestUtils.getAssertMethod( moduleChilds, "test_trailing_space_in_test", 0 );
		}
		//Function test:test_unittest_reportflags
		{
		IMethod methodtest_unittest_reportflags39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_unittest_reportflags39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_unittest_reportflags", 0 );
		}
		//Function test:test_testfile
		{
		IMethod methodtest_testfile40;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_testfile40 = ModelTestUtils.getAssertMethod( moduleChilds, "test_testfile", 0 );
		}
		//Function test:old_test1
		{
		IMethod methodold_test141;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test141 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test1", 0 );
		}
		//Function test:old_test2
		{
		IMethod methodold_test242;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test242 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test2", 0 );
		}
		//Function test:old_test3
		{
		IMethod methodold_test343;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test343 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test3", 0 );
		}
		//Function test:old_test4
		{
		IMethod methodold_test444;
			IModelElement[] moduleChilds = module.getChildren();
			methodold_test444 = ModelTestUtils.getAssertMethod( moduleChilds, "old_test4", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main45;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main45 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}
		//Function test:test_coverage
		{
		IMethod methodtest_coverage46;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_coverage46 = ModelTestUtils.getAssertMethod( moduleChilds, "test_coverage", 1 );
			ModelTestUtils.assertParameterNames( methodtest_coverage46, new String[] {"coverdir"} );
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
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "formatpair");
			}
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "bytesize");
			}
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "bitsize");
			}
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "unsigned_min");
			}
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "unsigned_max");
			}
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "signed_min");
			}
			{
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTester5Childs, "signed_max");
			}
			//Function test:test_one
			{
			IMethod methodtest_one8;
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				methodtest_one8 = ModelTestUtils.getAssertMethod( classIntTester5Childs, "test_one", 5 );
				ModelTestUtils.assertParameterNames( methodtest_one8, new String[] {"self", "x", "pack", "unpack", "unhexlify"} );
			}
			//Function test:run
			{
			IMethod methodrun9;
				IModelElement[] classIntTester5Childs = classIntTester5.getChildren();
				methodrun9 = ModelTestUtils.getAssertMethod( classIntTester5Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun9, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}
		//Function test:test_p_code
		{
		IMethod methodtest_p_code10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_p_code10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_p_code", 0 );
		}
		//Function test:test_705836
		{
		IMethod methodtest_70583611;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_70583611 = ModelTestUtils.getAssertMethod( moduleChilds, "test_705836", 0 );
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
			{
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestsWithSourceFile0Childs, "data");
			}
			//Function test:zipTest
			{
			IMethod methodzipTest3;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodzipTest3 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "zipTest", 3 );
				ModelTestUtils.assertParameterNames( methodzipTest3, new String[] {"self", "f", "compression"} );
			}
			//Function test:testStored
			{
			IMethod methodtestStored4;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodtestStored4 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "testStored", 1 );
				ModelTestUtils.assertParameterNames( methodtestStored4, new String[] {"self"} );
			}
			//Function test:testDeflated
			{
			IMethod methodtestDeflated5;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodtestDeflated5 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "testDeflated", 1 );
				ModelTestUtils.assertParameterNames( methodtestDeflated5, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown6;
				IModelElement[] classTestsWithSourceFile0Childs = classTestsWithSourceFile0.getChildren();
				methodtearDown6 = ModelTestUtils.getAssertMethod( classTestsWithSourceFile0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classOtherTests7;
			IModelElement[] moduleChilds = module.getChildren();
			classOtherTests7 = ModelTestUtils.getAssertClass( moduleChilds, "OtherTests" );
			//Function test:testCloseErroneousFile
			{
			IMethod methodtestCloseErroneousFile8;
				IModelElement[] classOtherTests7Childs = classOtherTests7.getChildren();
				methodtestCloseErroneousFile8 = ModelTestUtils.getAssertMethod( classOtherTests7Childs, "testCloseErroneousFile", 1 );
				ModelTestUtils.assertParameterNames( methodtestCloseErroneousFile8, new String[] {"self"} );
			}
			//Function test:testNonExistentFileRaisesIOError
			{
			IMethod methodtestNonExistentFileRaisesIOError9;
				IModelElement[] classOtherTests7Childs = classOtherTests7.getChildren();
				methodtestNonExistentFileRaisesIOError9 = ModelTestUtils.getAssertMethod( classOtherTests7Childs, "testNonExistentFileRaisesIOError", 1 );
				ModelTestUtils.assertParameterNames( methodtestNonExistentFileRaisesIOError9, new String[] {"self"} );
			}
			//Function test:testClosedZipRaisesRuntimeError
			{
			IMethod methodtestClosedZipRaisesRuntimeError10;
				IModelElement[] classOtherTests7Childs = classOtherTests7.getChildren();
				methodtestClosedZipRaisesRuntimeError10 = ModelTestUtils.getAssertMethod( classOtherTests7Childs, "testClosedZipRaisesRuntimeError", 1 );
				ModelTestUtils.assertParameterNames( methodtestClosedZipRaisesRuntimeError10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classImportTracker0Childs = classImportTracker0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportTracker0Childs, "imports");
			}
			//Function test:find_module
			{
			IMethod methodfind_module3;
				IModelElement[] classImportTracker0Childs = classImportTracker0.getChildren();
				methodfind_module3 = ModelTestUtils.getAssertMethod( classImportTracker0Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module3, new String[] {"self", "fullname", "path"} );
			}
		}
		//Class test
		{
		IType classTestImporter4;
			IModelElement[] moduleChilds = module.getChildren();
			classTestImporter4 = ModelTestUtils.getAssertClass( moduleChilds, "TestImporter" );
			{
				IModelElement[] classTestImporter4Childs = classTestImporter4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImporter4Childs, "modules");
			}
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classTestImporter4Childs = classTestImporter4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classTestImporter4Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "path"} );
			}
			{
				IModelElement[] classTestImporter4Childs = classTestImporter4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImporter4Childs, "path");
			}
			//Function test:_get__path__
			{
			IMethod method_get__path__7;
				IModelElement[] classTestImporter4Childs = classTestImporter4.getChildren();
				method_get__path__7 = ModelTestUtils.getAssertMethod( classTestImporter4Childs, "_get__path__", 1 );
				ModelTestUtils.assertParameterNames( method_get__path__7, new String[] {"self"} );
			}
			//Function test:find_module
			{
			IMethod methodfind_module8;
				IModelElement[] classTestImporter4Childs = classTestImporter4.getChildren();
				methodfind_module8 = ModelTestUtils.getAssertMethod( classTestImporter4Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module8, new String[] {"self", "fullname", "path"} );
			}
			//Function test:load_module
			{
			IMethod methodload_module9;
				IModelElement[] classTestImporter4Childs = classTestImporter4.getChildren();
				methodload_module9 = ModelTestUtils.getAssertMethod( classTestImporter4Childs, "load_module", 2 );
				ModelTestUtils.assertParameterNames( methodload_module9, new String[] {"self", "fullname"} );
			}
		}
		//Class test
		{
		IType classMetaImporter10;
			IModelElement[] moduleChilds = module.getChildren();
			classMetaImporter10 = ModelTestUtils.getAssertClass( moduleChilds, "MetaImporter" );
			//Function test:_get__path__
			{
			IMethod method_get__path__11;
				IModelElement[] classMetaImporter10Childs = classMetaImporter10.getChildren();
				method_get__path__11 = ModelTestUtils.getAssertMethod( classMetaImporter10Childs, "_get__path__", 1 );
				ModelTestUtils.assertParameterNames( method_get__path__11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPathImporter12;
			IModelElement[] moduleChilds = module.getChildren();
			classPathImporter12 = ModelTestUtils.getAssertClass( moduleChilds, "PathImporter" );
			//Function test:_get__path__
			{
			IMethod method_get__path__13;
				IModelElement[] classPathImporter12Childs = classPathImporter12.getChildren();
				method_get__path__13 = ModelTestUtils.getAssertMethod( classPathImporter12Childs, "_get__path__", 1 );
				ModelTestUtils.assertParameterNames( method_get__path__13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classImportBlocker14;
			IModelElement[] moduleChilds = module.getChildren();
			classImportBlocker14 = ModelTestUtils.getAssertClass( moduleChilds, "ImportBlocker" );
			//Function test:__init__
			{
			IMethod method__init__15;
				IModelElement[] classImportBlocker14Childs = classImportBlocker14.getChildren();
				method__init__15 = ModelTestUtils.getAssertMethod( classImportBlocker14Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__15, new String[] {"self", "namestoblock"} );
			}
			{
				IModelElement[] classImportBlocker14Childs = classImportBlocker14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportBlocker14Childs, "namestoblock");
			}
			//Function test:find_module
			{
			IMethod methodfind_module17;
				IModelElement[] classImportBlocker14Childs = classImportBlocker14.getChildren();
				methodfind_module17 = ModelTestUtils.getAssertMethod( classImportBlocker14Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module17, new String[] {"self", "fullname", "path"} );
			}
			//Function test:load_module
			{
			IMethod methodload_module18;
				IModelElement[] classImportBlocker14Childs = classImportBlocker14.getChildren();
				methodload_module18 = ModelTestUtils.getAssertMethod( classImportBlocker14Childs, "load_module", 2 );
				ModelTestUtils.assertParameterNames( methodload_module18, new String[] {"self", "fullname"} );
			}
		}
		//Class test
		{
		IType classImpWrapper19;
			IModelElement[] moduleChilds = module.getChildren();
			classImpWrapper19 = ModelTestUtils.getAssertClass( moduleChilds, "ImpWrapper" );
			//Function test:__init__
			{
			IMethod method__init__20;
				IModelElement[] classImpWrapper19Childs = classImpWrapper19.getChildren();
				method__init__20 = ModelTestUtils.getAssertMethod( classImpWrapper19Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__20, new String[] {"self", "path"} );
			}
			{
				IModelElement[] classImpWrapper19Childs = classImpWrapper19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImpWrapper19Childs, "path");
			}
			//Function test:find_module
			{
			IMethod methodfind_module22;
				IModelElement[] classImpWrapper19Childs = classImpWrapper19.getChildren();
				methodfind_module22 = ModelTestUtils.getAssertMethod( classImpWrapper19Childs, "find_module", 3 );
				ModelTestUtils.assertParameterNames( methodfind_module22, new String[] {"self", "fullname", "path"} );
			}
		}
		//Class test
		{
		IType classImpLoader23;
			IModelElement[] moduleChilds = module.getChildren();
			classImpLoader23 = ModelTestUtils.getAssertClass( moduleChilds, "ImpLoader" );
			//Function test:__init__
			{
			IMethod method__init__24;
				IModelElement[] classImpLoader23Childs = classImpLoader23.getChildren();
				method__init__24 = ModelTestUtils.getAssertMethod( classImpLoader23Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__24, new String[] {"self", "file", "filename", "stuff"} );
			}
			{
				IModelElement[] classImpLoader23Childs = classImpLoader23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImpLoader23Childs, "file");
			}
			{
				IModelElement[] classImpLoader23Childs = classImpLoader23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImpLoader23Childs, "filename");
			}
			{
				IModelElement[] classImpLoader23Childs = classImpLoader23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImpLoader23Childs, "stuff");
			}
			//Function test:load_module
			{
			IMethod methodload_module26;
				IModelElement[] classImpLoader23Childs = classImpLoader23.getChildren();
				methodload_module26 = ModelTestUtils.getAssertMethod( classImpLoader23Childs, "load_module", 2 );
				ModelTestUtils.assertParameterNames( methodload_module26, new String[] {"self", "fullname"} );
			}
		}
		//Class test
		{
		IType classImportHooksBaseTestCase27;
			IModelElement[] moduleChilds = module.getChildren();
			classImportHooksBaseTestCase27 = ModelTestUtils.getAssertClass( moduleChilds, "ImportHooksBaseTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp28;
				IModelElement[] classImportHooksBaseTestCase27Childs = classImportHooksBaseTestCase27.getChildren();
				methodsetUp28 = ModelTestUtils.getAssertMethod( classImportHooksBaseTestCase27Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp28, new String[] {"self"} );
			}
			{
				IModelElement[] classImportHooksBaseTestCase27Childs = classImportHooksBaseTestCase27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportHooksBaseTestCase27Childs, "path");
			}
			{
				IModelElement[] classImportHooksBaseTestCase27Childs = classImportHooksBaseTestCase27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportHooksBaseTestCase27Childs, "meta_path");
			}
			{
				IModelElement[] classImportHooksBaseTestCase27Childs = classImportHooksBaseTestCase27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportHooksBaseTestCase27Childs, "path_hooks");
			}
			{
				IModelElement[] classImportHooksBaseTestCase27Childs = classImportHooksBaseTestCase27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportHooksBaseTestCase27Childs, "tracker");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown30;
				IModelElement[] classImportHooksBaseTestCase27Childs = classImportHooksBaseTestCase27.getChildren();
				methodtearDown30 = ModelTestUtils.getAssertMethod( classImportHooksBaseTestCase27Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown30, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classImportHooksTestCase31;
			IModelElement[] moduleChilds = module.getChildren();
			classImportHooksTestCase31 = ModelTestUtils.getAssertClass( moduleChilds, "ImportHooksTestCase" );
			//Function test:doTestImports
			{
			IMethod methoddoTestImports32;
				IModelElement[] classImportHooksTestCase31Childs = classImportHooksTestCase31.getChildren();
				methoddoTestImports32 = ModelTestUtils.getAssertMethod( classImportHooksTestCase31Childs, "doTestImports", 2 );
				ModelTestUtils.assertParameterNames( methoddoTestImports32, new String[] {"self", "importer"} );
			}
			//Function test:testMetaPath
			{
			IMethod methodtestMetaPath33;
				IModelElement[] classImportHooksTestCase31Childs = classImportHooksTestCase31.getChildren();
				methodtestMetaPath33 = ModelTestUtils.getAssertMethod( classImportHooksTestCase31Childs, "testMetaPath", 1 );
				ModelTestUtils.assertParameterNames( methodtestMetaPath33, new String[] {"self"} );
			}
			//Function test:testPathHook
			{
			IMethod methodtestPathHook34;
				IModelElement[] classImportHooksTestCase31Childs = classImportHooksTestCase31.getChildren();
				methodtestPathHook34 = ModelTestUtils.getAssertMethod( classImportHooksTestCase31Childs, "testPathHook", 1 );
				ModelTestUtils.assertParameterNames( methodtestPathHook34, new String[] {"self"} );
			}
			//Function test:testBlocker
			{
			IMethod methodtestBlocker35;
				IModelElement[] classImportHooksTestCase31Childs = classImportHooksTestCase31.getChildren();
				methodtestBlocker35 = ModelTestUtils.getAssertMethod( classImportHooksTestCase31Childs, "testBlocker", 1 );
				ModelTestUtils.assertParameterNames( methodtestBlocker35, new String[] {"self"} );
			}
			//Function test:testImpWrapper
			{
			IMethod methodtestImpWrapper36;
				IModelElement[] classImportHooksTestCase31Childs = classImportHooksTestCase31.getChildren();
				methodtestImpWrapper36 = ModelTestUtils.getAssertMethod( classImportHooksTestCase31Childs, "testImpWrapper", 1 );
				ModelTestUtils.assertParameterNames( methodtestImpWrapper36, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main37 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHelperFunctionsTests0Childs, "sys_path");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:test_makepath
			{
			IMethod methodtest_makepath4;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_makepath4 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_makepath", 1 );
				ModelTestUtils.assertParameterNames( methodtest_makepath4, new String[] {"self"} );
			}
			//Function test:test_init_pathinfo
			{
			IMethod methodtest_init_pathinfo5;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_init_pathinfo5 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_init_pathinfo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_init_pathinfo5, new String[] {"self"} );
			}
			//Function test:pth_file_tests
			{
			IMethod methodpth_file_tests6;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodpth_file_tests6 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "pth_file_tests", 2 );
				ModelTestUtils.assertParameterNames( methodpth_file_tests6, new String[] {"self", "pth_file"} );
			}
			//Function test:test_addpackage
			{
			IMethod methodtest_addpackage7;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_addpackage7 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_addpackage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addpackage7, new String[] {"self"} );
			}
			//Function test:test_addsitedir
			{
			IMethod methodtest_addsitedir8;
				IModelElement[] classHelperFunctionsTests0Childs = classHelperFunctionsTests0.getChildren();
				methodtest_addsitedir8 = ModelTestUtils.getAssertMethod( classHelperFunctionsTests0Childs, "test_addsitedir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addsitedir8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPthFile9;
			IModelElement[] moduleChilds = module.getChildren();
			classPthFile9 = ModelTestUtils.getAssertClass( moduleChilds, "PthFile" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classPthFile9Childs, "__init__", 5 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self", "filename_base", "imported", "good_dirname", "bad_dirname"} );
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "filename");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "base_dir");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "file_path");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "imported");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "good_dirname");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "bad_dirname");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "good_dir_path");
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "bad_dir_path");
			}
			//Function test:create
			{
			IMethod methodcreate12;
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				methodcreate12 = ModelTestUtils.getAssertMethod( classPthFile9Childs, "create", 1 );
				ModelTestUtils.assertParameterNames( methodcreate12, new String[] {"self"} );
			}
			//Function test:cleanup
			{
			IMethod methodcleanup13;
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				methodcleanup13 = ModelTestUtils.getAssertMethod( classPthFile9Childs, "cleanup", 2 );
				ModelTestUtils.assertParameterNames( methodcleanup13, new String[] {"self", "prep"} );
			}
			{
				IModelElement[] classPthFile9Childs = classPthFile9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPthFile9Childs, "imported_module");
			}
		}
		//Class test
		{
		IType classImportSideEffectTests15;
			IModelElement[] moduleChilds = module.getChildren();
			classImportSideEffectTests15 = ModelTestUtils.getAssertClass( moduleChilds, "ImportSideEffectTests" );
			//Function test:setUp
			{
			IMethod methodsetUp16;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodsetUp16 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp16, new String[] {"self"} );
			}
			{
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classImportSideEffectTests15Childs, "sys_path");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown18;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtearDown18 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown18, new String[] {"self"} );
			}
			//Function test:test_abs__file__
			{
			IMethod methodtest_abs__file__19;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_abs__file__19 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_abs__file__", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs__file__19, new String[] {"self"} );
			}
			//Function test:test_no_duplicate_paths
			{
			IMethod methodtest_no_duplicate_paths20;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_no_duplicate_paths20 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_no_duplicate_paths", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_duplicate_paths20, new String[] {"self"} );
			}
			//Function test:test_add_build_dir
			{
			IMethod methodtest_add_build_dir21;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_add_build_dir21 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_add_build_dir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_build_dir21, new String[] {"self"} );
			}
			//Function test:test_setting_quit
			{
			IMethod methodtest_setting_quit22;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_setting_quit22 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_setting_quit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setting_quit22, new String[] {"self"} );
			}
			//Function test:test_setting_copyright
			{
			IMethod methodtest_setting_copyright23;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_setting_copyright23 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_setting_copyright", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setting_copyright23, new String[] {"self"} );
			}
			//Function test:test_setting_help
			{
			IMethod methodtest_setting_help24;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_setting_help24 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_setting_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setting_help24, new String[] {"self"} );
			}
			//Function test:test_aliasing_mbcs
			{
			IMethod methodtest_aliasing_mbcs25;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_aliasing_mbcs25 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_aliasing_mbcs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_aliasing_mbcs25, new String[] {"self"} );
			}
			//Function test:test_setdefaultencoding_removed
			{
			IMethod methodtest_setdefaultencoding_removed26;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_setdefaultencoding_removed26 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_setdefaultencoding_removed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefaultencoding_removed26, new String[] {"self"} );
			}
			//Function test:test_sitecustomize_executed
			{
			IMethod methodtest_sitecustomize_executed27;
				IModelElement[] classImportSideEffectTests15Childs = classImportSideEffectTests15.getChildren();
				methodtest_sitecustomize_executed27 = ModelTestUtils.getAssertMethod( classImportSideEffectTests15Childs, "test_sitecustomize_executed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sitecustomize_executed27, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
				{
					IModelElement[] classRat20Childs = classRat20.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classRat20Childs, "n");
				}
				{
					IModelElement[] classRat20Childs = classRat20.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classRat20Childs, "d");
				}
				//Function test:__cmp__
				{
				IMethod method__cmp__23;
					IModelElement[] classRat20Childs = classRat20.getChildren();
					method__cmp__23 = ModelTestUtils.getAssertMethod( classRat20Childs, "__cmp__", 2 );
					ModelTestUtils.assertParameterNames( method__cmp__23, new String[] {"self", "other"} );
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
			{
				IModelElement[] classInterceptedError0Childs = classInterceptedError0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classInterceptedError0Childs, "error_message");
			}
			{
				IModelElement[] classInterceptedError0Childs = classInterceptedError0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classInterceptedError0Childs, "exit_status");
			}
			{
				IModelElement[] classInterceptedError0Childs = classInterceptedError0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classInterceptedError0Childs, "exit_message");
			}
			//Function test:__str__
			{
			IMethod method__str__3;
				IModelElement[] classInterceptedError0Childs = classInterceptedError0.getChildren();
				method__str__3 = ModelTestUtils.getAssertMethod( classInterceptedError0Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classInterceptingOptionParser4;
			IModelElement[] moduleChilds = module.getChildren();
			classInterceptingOptionParser4 = ModelTestUtils.getAssertClass( moduleChilds, "InterceptingOptionParser" );
			//Function test:exit
			{
			IMethod methodexit5;
				IModelElement[] classInterceptingOptionParser4Childs = classInterceptingOptionParser4.getChildren();
				methodexit5 = ModelTestUtils.getAssertMethod( classInterceptingOptionParser4Childs, "exit", 3 );
				ModelTestUtils.assertParameterNames( methodexit5, new String[] {"self", "status", "msg"} );
			}
			//Function test:error
			{
			IMethod methoderror6;
				IModelElement[] classInterceptingOptionParser4Childs = classInterceptingOptionParser4.getChildren();
				methoderror6 = ModelTestUtils.getAssertMethod( classInterceptingOptionParser4Childs, "error", 2 );
				ModelTestUtils.assertParameterNames( methoderror6, new String[] {"self", "msg"} );
			}
		}
		//Class test
		{
		IType classBaseTest7;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseTest7 = ModelTestUtils.getAssertClass( moduleChilds, "BaseTest" );
			//Function test:assertParseOK
			{
			IMethod methodassertParseOK8;
				IModelElement[] classBaseTest7Childs = classBaseTest7.getChildren();
				methodassertParseOK8 = ModelTestUtils.getAssertMethod( classBaseTest7Childs, "assertParseOK", 4 );
				ModelTestUtils.assertParameterNames( methodassertParseOK8, new String[] {"self", "args", "expected_opts", "expected_positional_args"} );
			}
			//Function test:assertRaises
			{
			IMethod methodassertRaises9;
				IModelElement[] classBaseTest7Childs = classBaseTest7.getChildren();
				methodassertRaises9 = ModelTestUtils.getAssertMethod( classBaseTest7Childs, "assertRaises", 6 );
				ModelTestUtils.assertParameterNames( methodassertRaises9, new String[] {"self", "func", "args", "kwargs", "expected_exception", "expected_message"} );
			}
			//Function test:assertParseFail
			{
			IMethod methodassertParseFail10;
				IModelElement[] classBaseTest7Childs = classBaseTest7.getChildren();
				methodassertParseFail10 = ModelTestUtils.getAssertMethod( classBaseTest7Childs, "assertParseFail", 3 );
				ModelTestUtils.assertParameterNames( methodassertParseFail10, new String[] {"self", "cmdline_args", "expected_output"} );
			}
			//Function test:assertOutput
			{
			IMethod methodassertOutput11;
				IModelElement[] classBaseTest7Childs = classBaseTest7.getChildren();
				methodassertOutput11 = ModelTestUtils.getAssertMethod( classBaseTest7Childs, "assertOutput", 5 );
				ModelTestUtils.assertParameterNames( methodassertOutput11, new String[] {"self", "cmdline_args", "expected_output", "expected_status", "expected_error"} );
			}
			//Function test:assertTypeError
			{
			IMethod methodassertTypeError12;
				IModelElement[] classBaseTest7Childs = classBaseTest7.getChildren();
				methodassertTypeError12 = ModelTestUtils.getAssertMethod( classBaseTest7Childs, "assertTypeError", 4 );
				ModelTestUtils.assertParameterNames( methodassertTypeError12, new String[] {"self", "func", "expected_message", "args"} );
			}
			//Function test:assertHelp
			{
			IMethod methodassertHelp13;
				IModelElement[] classBaseTest7Childs = classBaseTest7.getChildren();
				methodassertHelp13 = ModelTestUtils.getAssertMethod( classBaseTest7Childs, "assertHelp", 3 );
				ModelTestUtils.assertParameterNames( methodassertHelp13, new String[] {"self", "parser", "expected_help"} );
			}
		}
		//Class test
		{
		IType classTestOptionChecks14;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionChecks14 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionChecks" );
			//Function test:setUp
			{
			IMethod methodsetUp15;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodsetUp15 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp15, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOptionChecks14Childs, "parser");
			}
			//Function test:assertOptionError
			{
			IMethod methodassertOptionError17;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodassertOptionError17 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "assertOptionError", 4 );
				ModelTestUtils.assertParameterNames( methodassertOptionError17, new String[] {"self", "expected_message", "args", "kwargs"} );
			}
			//Function test:test_opt_string_empty
			{
			IMethod methodtest_opt_string_empty18;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_opt_string_empty18 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_opt_string_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_empty18, new String[] {"self"} );
			}
			//Function test:test_opt_string_too_short
			{
			IMethod methodtest_opt_string_too_short19;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_opt_string_too_short19 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_opt_string_too_short", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_too_short19, new String[] {"self"} );
			}
			//Function test:test_opt_string_short_invalid
			{
			IMethod methodtest_opt_string_short_invalid20;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_opt_string_short_invalid20 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_opt_string_short_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_short_invalid20, new String[] {"self"} );
			}
			//Function test:test_opt_string_long_invalid
			{
			IMethod methodtest_opt_string_long_invalid21;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_opt_string_long_invalid21 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_opt_string_long_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opt_string_long_invalid21, new String[] {"self"} );
			}
			//Function test:test_attr_invalid
			{
			IMethod methodtest_attr_invalid22;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_attr_invalid22 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_attr_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_invalid22, new String[] {"self"} );
			}
			//Function test:test_action_invalid
			{
			IMethod methodtest_action_invalid23;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_action_invalid23 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_action_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_action_invalid23, new String[] {"self"} );
			}
			//Function test:test_type_invalid
			{
			IMethod methodtest_type_invalid24;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_type_invalid24 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_type_invalid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type_invalid24, new String[] {"self"} );
			}
			//Function test:test_no_type_for_action
			{
			IMethod methodtest_no_type_for_action25;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_type_for_action25 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_type_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_type_for_action25, new String[] {"self"} );
			}
			//Function test:test_no_choices_list
			{
			IMethod methodtest_no_choices_list26;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_choices_list26 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_choices_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_choices_list26, new String[] {"self"} );
			}
			//Function test:test_bad_choices_list
			{
			IMethod methodtest_bad_choices_list27;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_bad_choices_list27 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_bad_choices_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_choices_list27, new String[] {"self"} );
			}
			//Function test:test_no_choices_for_type
			{
			IMethod methodtest_no_choices_for_type28;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_choices_for_type28 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_choices_for_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_choices_for_type28, new String[] {"self"} );
			}
			//Function test:test_no_const_for_action
			{
			IMethod methodtest_no_const_for_action29;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_const_for_action29 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_const_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_const_for_action29, new String[] {"self"} );
			}
			//Function test:test_no_nargs_for_action
			{
			IMethod methodtest_no_nargs_for_action30;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_nargs_for_action30 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_nargs_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_nargs_for_action30, new String[] {"self"} );
			}
			//Function test:test_callback_not_callable
			{
			IMethod methodtest_callback_not_callable31;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_callback_not_callable31 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_callback_not_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_not_callable31, new String[] {"self"} );
			}
			//Function test:dummy
			{
			IMethod methoddummy32;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methoddummy32 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "dummy", 1 );
				ModelTestUtils.assertParameterNames( methoddummy32, new String[] {"self"} );
			}
			//Function test:test_callback_args_no_tuple
			{
			IMethod methodtest_callback_args_no_tuple33;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_callback_args_no_tuple33 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_callback_args_no_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_args_no_tuple33, new String[] {"self"} );
			}
			//Function test:test_callback_kwargs_no_dict
			{
			IMethod methodtest_callback_kwargs_no_dict34;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_callback_kwargs_no_dict34 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_callback_kwargs_no_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_kwargs_no_dict34, new String[] {"self"} );
			}
			//Function test:test_no_callback_for_action
			{
			IMethod methodtest_no_callback_for_action35;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_callback_for_action35 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_callback_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_callback_for_action35, new String[] {"self"} );
			}
			//Function test:test_no_callback_args_for_action
			{
			IMethod methodtest_no_callback_args_for_action36;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_callback_args_for_action36 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_callback_args_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_callback_args_for_action36, new String[] {"self"} );
			}
			//Function test:test_no_callback_kwargs_for_action
			{
			IMethod methodtest_no_callback_kwargs_for_action37;
				IModelElement[] classTestOptionChecks14Childs = classTestOptionChecks14.getChildren();
				methodtest_no_callback_kwargs_for_action37 = ModelTestUtils.getAssertMethod( classTestOptionChecks14Childs, "test_no_callback_kwargs_for_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_callback_kwargs_for_action37, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOptionParser38;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionParser38 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionParser" );
			//Function test:setUp
			{
			IMethod methodsetUp39;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodsetUp39 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp39, new String[] {"self"} );
			}
			{
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestOptionParser38Childs, "parser");
			}
			//Function test:test_add_option_no_Option
			{
			IMethod methodtest_add_option_no_Option41;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_add_option_no_Option41 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_add_option_no_Option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_option_no_Option41, new String[] {"self"} );
			}
			//Function test:test_add_option_invalid_arguments
			{
			IMethod methodtest_add_option_invalid_arguments42;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_add_option_invalid_arguments42 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_add_option_invalid_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_option_invalid_arguments42, new String[] {"self"} );
			}
			//Function test:test_get_option
			{
			IMethod methodtest_get_option43;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_get_option43 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_get_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_option43, new String[] {"self"} );
			}
			//Function test:test_get_option_equals
			{
			IMethod methodtest_get_option_equals44;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_get_option_equals44 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_get_option_equals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_option_equals44, new String[] {"self"} );
			}
			//Function test:test_has_option
			{
			IMethod methodtest_has_option45;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_has_option45 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_has_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_option45, new String[] {"self"} );
			}
			//Function test:assert_removed
			{
			IMethod methodassert_removed46;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodassert_removed46 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "assert_removed", 1 );
				ModelTestUtils.assertParameterNames( methodassert_removed46, new String[] {"self"} );
			}
			//Function test:test_remove_short_opt
			{
			IMethod methodtest_remove_short_opt47;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_remove_short_opt47 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_remove_short_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_short_opt47, new String[] {"self"} );
			}
			//Function test:test_remove_long_opt
			{
			IMethod methodtest_remove_long_opt48;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_remove_long_opt48 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_remove_long_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_long_opt48, new String[] {"self"} );
			}
			//Function test:test_remove_nonexistent
			{
			IMethod methodtest_remove_nonexistent49;
				IModelElement[] classTestOptionParser38Childs = classTestOptionParser38.getChildren();
				methodtest_remove_nonexistent49 = ModelTestUtils.getAssertMethod( classTestOptionParser38Childs, "test_remove_nonexistent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove_nonexistent49, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOptionValues50;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionValues50 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionValues" );
			//Function test:setUp
			{
			IMethod methodsetUp51;
				IModelElement[] classTestOptionValues50Childs = classTestOptionValues50.getChildren();
				methodsetUp51 = ModelTestUtils.getAssertMethod( classTestOptionValues50Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp51, new String[] {"self"} );
			}
			//Function test:test_basics
			{
			IMethod methodtest_basics52;
				IModelElement[] classTestOptionValues50Childs = classTestOptionValues50.getChildren();
				methodtest_basics52 = ModelTestUtils.getAssertMethod( classTestOptionValues50Childs, "test_basics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basics52, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestTypeAliases53;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTypeAliases53 = ModelTestUtils.getAssertClass( moduleChilds, "TestTypeAliases" );
			//Function test:setUp
			{
			IMethod methodsetUp54;
				IModelElement[] classTestTypeAliases53Childs = classTestTypeAliases53.getChildren();
				methodsetUp54 = ModelTestUtils.getAssertMethod( classTestTypeAliases53Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp54, new String[] {"self"} );
			}
			{
				IModelElement[] classTestTypeAliases53Childs = classTestTypeAliases53.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTypeAliases53Childs, "parser");
			}
			//Function test:test_type_aliases
			{
			IMethod methodtest_type_aliases56;
				IModelElement[] classTestTypeAliases53Childs = classTestTypeAliases53.getChildren();
				methodtest_type_aliases56 = ModelTestUtils.getAssertMethod( classTestTypeAliases53Childs, "test_type_aliases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type_aliases56, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_time_units");
		}
		//Function test:_check_duration
		{
		IMethod method_check_duration57;
			IModelElement[] moduleChilds = module.getChildren();
			method_check_duration57 = ModelTestUtils.getAssertMethod( moduleChilds, "_check_duration", 3 );
			ModelTestUtils.assertParameterNames( method_check_duration57, new String[] {"option", "opt", "value"} );
		}
		//Class test
		{
		IType classDurationOption58;
			IModelElement[] moduleChilds = module.getChildren();
			classDurationOption58 = ModelTestUtils.getAssertClass( moduleChilds, "DurationOption" );
			{
				IModelElement[] classDurationOption58Childs = classDurationOption58.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDurationOption58Childs, "TYPES");
			}
			{
				IModelElement[] classDurationOption58Childs = classDurationOption58.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDurationOption58Childs, "TYPE_CHECKER");
			}
		}
		//Class test
		{
		IType classTestDefaultValues59;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDefaultValues59 = ModelTestUtils.getAssertClass( moduleChilds, "TestDefaultValues" );
			//Function test:setUp
			{
			IMethod methodsetUp60;
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				methodsetUp60 = ModelTestUtils.getAssertMethod( classTestDefaultValues59Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp60, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDefaultValues59Childs, "parser");
			}
			{
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDefaultValues59Childs, "expected");
			}
			//Function test:test_basic_defaults
			{
			IMethod methodtest_basic_defaults62;
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				methodtest_basic_defaults62 = ModelTestUtils.getAssertMethod( classTestDefaultValues59Childs, "test_basic_defaults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_defaults62, new String[] {"self"} );
			}
			//Function test:test_mixed_defaults_post
			{
			IMethod methodtest_mixed_defaults_post63;
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				methodtest_mixed_defaults_post63 = ModelTestUtils.getAssertMethod( classTestDefaultValues59Childs, "test_mixed_defaults_post", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_defaults_post63, new String[] {"self"} );
			}
			//Function test:test_mixed_defaults_pre
			{
			IMethod methodtest_mixed_defaults_pre64;
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				methodtest_mixed_defaults_pre64 = ModelTestUtils.getAssertMethod( classTestDefaultValues59Childs, "test_mixed_defaults_pre", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_defaults_pre64, new String[] {"self"} );
			}
			//Function test:test_process_default
			{
			IMethod methodtest_process_default65;
				IModelElement[] classTestDefaultValues59Childs = classTestDefaultValues59.getChildren();
				methodtest_process_default65 = ModelTestUtils.getAssertMethod( classTestDefaultValues59Childs, "test_process_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_process_default65, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestProgName66;
			IModelElement[] moduleChilds = module.getChildren();
			classTestProgName66 = ModelTestUtils.getAssertClass( moduleChilds, "TestProgName" );
			//Function test:assertUsage
			{
			IMethod methodassertUsage67;
				IModelElement[] classTestProgName66Childs = classTestProgName66.getChildren();
				methodassertUsage67 = ModelTestUtils.getAssertMethod( classTestProgName66Childs, "assertUsage", 3 );
				ModelTestUtils.assertParameterNames( methodassertUsage67, new String[] {"self", "parser", "expected_usage"} );
			}
			//Function test:assertVersion
			{
			IMethod methodassertVersion68;
				IModelElement[] classTestProgName66Childs = classTestProgName66.getChildren();
				methodassertVersion68 = ModelTestUtils.getAssertMethod( classTestProgName66Childs, "assertVersion", 3 );
				ModelTestUtils.assertParameterNames( methodassertVersion68, new String[] {"self", "parser", "expected_version"} );
			}
			//Function test:test_default_progname
			{
			IMethod methodtest_default_progname69;
				IModelElement[] classTestProgName66Childs = classTestProgName66.getChildren();
				methodtest_default_progname69 = ModelTestUtils.getAssertMethod( classTestProgName66Childs, "test_default_progname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_progname69, new String[] {"self"} );
			}
			//Function test:test_custom_progname
			{
			IMethod methodtest_custom_progname70;
				IModelElement[] classTestProgName66Childs = classTestProgName66.getChildren();
				methodtest_custom_progname70 = ModelTestUtils.getAssertMethod( classTestProgName66Childs, "test_custom_progname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_custom_progname70, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExpandDefaults71;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExpandDefaults71 = ModelTestUtils.getAssertClass( moduleChilds, "TestExpandDefaults" );
			//Function test:setUp
			{
			IMethod methodsetUp72;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodsetUp72 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp72, new String[] {"self"} );
			}
			{
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExpandDefaults71Childs, "parser");
			}
			{
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExpandDefaults71Childs, "help_prefix");
			}
			{
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExpandDefaults71Childs, "file_help");
			}
			{
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExpandDefaults71Childs, "expected_help_file");
			}
			{
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExpandDefaults71Childs, "expected_help_none");
			}
			//Function test:test_option_default
			{
			IMethod methodtest_option_default74;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_option_default74 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_option_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_default74, new String[] {"self"} );
			}
			//Function test:test_parser_default_1
			{
			IMethod methodtest_parser_default_175;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_parser_default_175 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_parser_default_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parser_default_175, new String[] {"self"} );
			}
			//Function test:test_parser_default_2
			{
			IMethod methodtest_parser_default_276;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_parser_default_276 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_parser_default_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parser_default_276, new String[] {"self"} );
			}
			//Function test:test_no_default
			{
			IMethod methodtest_no_default77;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_no_default77 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_no_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_default77, new String[] {"self"} );
			}
			//Function test:test_default_none_1
			{
			IMethod methodtest_default_none_178;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_default_none_178 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_default_none_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_none_178, new String[] {"self"} );
			}
			//Function test:test_default_none_2
			{
			IMethod methodtest_default_none_279;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_default_none_279 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_default_none_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_none_279, new String[] {"self"} );
			}
			//Function test:test_float_default
			{
			IMethod methodtest_float_default80;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_float_default80 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_float_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float_default80, new String[] {"self"} );
			}
			//Function test:test_alt_expand
			{
			IMethod methodtest_alt_expand81;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_alt_expand81 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_alt_expand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alt_expand81, new String[] {"self"} );
			}
			//Function test:test_no_expand
			{
			IMethod methodtest_no_expand82;
				IModelElement[] classTestExpandDefaults71Childs = classTestExpandDefaults71.getChildren();
				methodtest_no_expand82 = ModelTestUtils.getAssertMethod( classTestExpandDefaults71Childs, "test_no_expand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_expand82, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestStandard83;
			IModelElement[] moduleChilds = module.getChildren();
			classTestStandard83 = ModelTestUtils.getAssertClass( moduleChilds, "TestStandard" );
			//Function test:setUp
			{
			IMethod methodsetUp84;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodsetUp84 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp84, new String[] {"self"} );
			}
			{
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestStandard83Childs, "parser");
			}
			//Function test:test_required_value
			{
			IMethod methodtest_required_value86;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_required_value86 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_required_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_required_value86, new String[] {"self"} );
			}
			//Function test:test_invalid_integer
			{
			IMethod methodtest_invalid_integer87;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_invalid_integer87 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_invalid_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_integer87, new String[] {"self"} );
			}
			//Function test:test_no_such_option
			{
			IMethod methodtest_no_such_option88;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_no_such_option88 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_no_such_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_such_option88, new String[] {"self"} );
			}
			//Function test:test_long_invalid_integer
			{
			IMethod methodtest_long_invalid_integer89;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_long_invalid_integer89 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_long_invalid_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_invalid_integer89, new String[] {"self"} );
			}
			//Function test:test_empty
			{
			IMethod methodtest_empty90;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_empty90 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty90, new String[] {"self"} );
			}
			//Function test:test_shortopt_empty_longopt_append
			{
			IMethod methodtest_shortopt_empty_longopt_append91;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_shortopt_empty_longopt_append91 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_shortopt_empty_longopt_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shortopt_empty_longopt_append91, new String[] {"self"} );
			}
			//Function test:test_long_option_append
			{
			IMethod methodtest_long_option_append92;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_long_option_append92 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_long_option_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_append92, new String[] {"self"} );
			}
			//Function test:test_option_argument_joined
			{
			IMethod methodtest_option_argument_joined93;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_option_argument_joined93 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_option_argument_joined", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_joined93, new String[] {"self"} );
			}
			//Function test:test_option_argument_split
			{
			IMethod methodtest_option_argument_split94;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_option_argument_split94 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_option_argument_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_split94, new String[] {"self"} );
			}
			//Function test:test_option_argument_joined_integer
			{
			IMethod methodtest_option_argument_joined_integer95;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_option_argument_joined_integer95 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_option_argument_joined_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_joined_integer95, new String[] {"self"} );
			}
			//Function test:test_option_argument_split_negative_integer
			{
			IMethod methodtest_option_argument_split_negative_integer96;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_option_argument_split_negative_integer96 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_option_argument_split_negative_integer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_argument_split_negative_integer96, new String[] {"self"} );
			}
			//Function test:test_long_option_argument_joined
			{
			IMethod methodtest_long_option_argument_joined97;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_long_option_argument_joined97 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_long_option_argument_joined", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_argument_joined97, new String[] {"self"} );
			}
			//Function test:test_long_option_argument_split
			{
			IMethod methodtest_long_option_argument_split98;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_long_option_argument_split98 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_long_option_argument_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_argument_split98, new String[] {"self"} );
			}
			//Function test:test_long_option_short_option
			{
			IMethod methodtest_long_option_short_option99;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_long_option_short_option99 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_long_option_short_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_option_short_option99, new String[] {"self"} );
			}
			//Function test:test_abbrev_long_option
			{
			IMethod methodtest_abbrev_long_option100;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_abbrev_long_option100 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_abbrev_long_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abbrev_long_option100, new String[] {"self"} );
			}
			//Function test:test_defaults
			{
			IMethod methodtest_defaults101;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_defaults101 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_defaults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_defaults101, new String[] {"self"} );
			}
			//Function test:test_ambiguous_option
			{
			IMethod methodtest_ambiguous_option102;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_ambiguous_option102 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_ambiguous_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ambiguous_option102, new String[] {"self"} );
			}
			//Function test:test_short_and_long_option_split
			{
			IMethod methodtest_short_and_long_option_split103;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_short_and_long_option_split103 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_short_and_long_option_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_and_long_option_split103, new String[] {"self"} );
			}
			//Function test:test_short_option_split_long_option_append
			{
			IMethod methodtest_short_option_split_long_option_append104;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_short_option_split_long_option_append104 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_short_option_split_long_option_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_split_long_option_append104, new String[] {"self"} );
			}
			//Function test:test_short_option_split_one_positional_arg
			{
			IMethod methodtest_short_option_split_one_positional_arg105;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_short_option_split_one_positional_arg105 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_short_option_split_one_positional_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_split_one_positional_arg105, new String[] {"self"} );
			}
			//Function test:test_short_option_consumes_separator
			{
			IMethod methodtest_short_option_consumes_separator106;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_short_option_consumes_separator106 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_short_option_consumes_separator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_consumes_separator106, new String[] {"self"} );
			}
			//Function test:test_short_option_joined_and_separator
			{
			IMethod methodtest_short_option_joined_and_separator107;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_short_option_joined_and_separator107 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_short_option_joined_and_separator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_option_joined_and_separator107, new String[] {"self"} );
			}
			//Function test:test_invalid_option_becomes_positional_arg
			{
			IMethod methodtest_invalid_option_becomes_positional_arg108;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_invalid_option_becomes_positional_arg108 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_invalid_option_becomes_positional_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_option_becomes_positional_arg108, new String[] {"self"} );
			}
			//Function test:test_no_append_versus_append
			{
			IMethod methodtest_no_append_versus_append109;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_no_append_versus_append109 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_no_append_versus_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_append_versus_append109, new String[] {"self"} );
			}
			//Function test:test_option_consumes_optionlike_string
			{
			IMethod methodtest_option_consumes_optionlike_string110;
				IModelElement[] classTestStandard83Childs = classTestStandard83.getChildren();
				methodtest_option_consumes_optionlike_string110 = ModelTestUtils.getAssertMethod( classTestStandard83Childs, "test_option_consumes_optionlike_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_consumes_optionlike_string110, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBool111;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBool111 = ModelTestUtils.getAssertClass( moduleChilds, "TestBool" );
			//Function test:setUp
			{
			IMethod methodsetUp112;
				IModelElement[] classTestBool111Childs = classTestBool111.getChildren();
				methodsetUp112 = ModelTestUtils.getAssertMethod( classTestBool111Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp112, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBool111Childs = classTestBool111.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBool111Childs, "parser");
			}
			//Function test:test_bool_default
			{
			IMethod methodtest_bool_default114;
				IModelElement[] classTestBool111Childs = classTestBool111.getChildren();
				methodtest_bool_default114 = ModelTestUtils.getAssertMethod( classTestBool111Childs, "test_bool_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_default114, new String[] {"self"} );
			}
			//Function test:test_bool_false
			{
			IMethod methodtest_bool_false115;
				IModelElement[] classTestBool111Childs = classTestBool111.getChildren();
				methodtest_bool_false115 = ModelTestUtils.getAssertMethod( classTestBool111Childs, "test_bool_false", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_false115, new String[] {"self"} );
			}
			//Function test:test_bool_true
			{
			IMethod methodtest_bool_true116;
				IModelElement[] classTestBool111Childs = classTestBool111.getChildren();
				methodtest_bool_true116 = ModelTestUtils.getAssertMethod( classTestBool111Childs, "test_bool_true", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_true116, new String[] {"self"} );
			}
			//Function test:test_bool_flicker_on_and_off
			{
			IMethod methodtest_bool_flicker_on_and_off117;
				IModelElement[] classTestBool111Childs = classTestBool111.getChildren();
				methodtest_bool_flicker_on_and_off117 = ModelTestUtils.getAssertMethod( classTestBool111Childs, "test_bool_flicker_on_and_off", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool_flicker_on_and_off117, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestChoice118;
			IModelElement[] moduleChilds = module.getChildren();
			classTestChoice118 = ModelTestUtils.getAssertClass( moduleChilds, "TestChoice" );
			//Function test:setUp
			{
			IMethod methodsetUp119;
				IModelElement[] classTestChoice118Childs = classTestChoice118.getChildren();
				methodsetUp119 = ModelTestUtils.getAssertMethod( classTestChoice118Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp119, new String[] {"self"} );
			}
			{
				IModelElement[] classTestChoice118Childs = classTestChoice118.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestChoice118Childs, "parser");
			}
			//Function test:test_valid_choice
			{
			IMethod methodtest_valid_choice121;
				IModelElement[] classTestChoice118Childs = classTestChoice118.getChildren();
				methodtest_valid_choice121 = ModelTestUtils.getAssertMethod( classTestChoice118Childs, "test_valid_choice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_valid_choice121, new String[] {"self"} );
			}
			//Function test:test_invalid_choice
			{
			IMethod methodtest_invalid_choice122;
				IModelElement[] classTestChoice118Childs = classTestChoice118.getChildren();
				methodtest_invalid_choice122 = ModelTestUtils.getAssertMethod( classTestChoice118Childs, "test_invalid_choice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_choice122, new String[] {"self"} );
			}
			//Function test:test_add_choice_option
			{
			IMethod methodtest_add_choice_option123;
				IModelElement[] classTestChoice118Childs = classTestChoice118.getChildren();
				methodtest_add_choice_option123 = ModelTestUtils.getAssertMethod( classTestChoice118Childs, "test_add_choice_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_choice_option123, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCount124;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCount124 = ModelTestUtils.getAssertClass( moduleChilds, "TestCount" );
			//Function test:setUp
			{
			IMethod methodsetUp125;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodsetUp125 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp125, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCount124Childs, "parser");
			}
			{
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCount124Childs, "v_opt");
			}
			//Function test:test_empty
			{
			IMethod methodtest_empty127;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_empty127 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty127, new String[] {"self"} );
			}
			//Function test:test_count_one
			{
			IMethod methodtest_count_one128;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_one128 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_one", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_one128, new String[] {"self"} );
			}
			//Function test:test_count_three
			{
			IMethod methodtest_count_three129;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_three129 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_three", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_three129, new String[] {"self"} );
			}
			//Function test:test_count_three_apart
			{
			IMethod methodtest_count_three_apart130;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_three_apart130 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_three_apart", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_three_apart130, new String[] {"self"} );
			}
			//Function test:test_count_override_amount
			{
			IMethod methodtest_count_override_amount131;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_override_amount131 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_override_amount", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_override_amount131, new String[] {"self"} );
			}
			//Function test:test_count_override_quiet
			{
			IMethod methodtest_count_override_quiet132;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_override_quiet132 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_override_quiet", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_override_quiet132, new String[] {"self"} );
			}
			//Function test:test_count_overriding
			{
			IMethod methodtest_count_overriding133;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_overriding133 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_overriding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_overriding133, new String[] {"self"} );
			}
			//Function test:test_count_interspersed_args
			{
			IMethod methodtest_count_interspersed_args134;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_interspersed_args134 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_interspersed_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_interspersed_args134, new String[] {"self"} );
			}
			//Function test:test_count_no_interspersed_args
			{
			IMethod methodtest_count_no_interspersed_args135;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_no_interspersed_args135 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_no_interspersed_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_no_interspersed_args135, new String[] {"self"} );
			}
			//Function test:test_count_no_such_option
			{
			IMethod methodtest_count_no_such_option136;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_no_such_option136 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_no_such_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_no_such_option136, new String[] {"self"} );
			}
			//Function test:test_count_option_no_value
			{
			IMethod methodtest_count_option_no_value137;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_option_no_value137 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_option_no_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_option_no_value137, new String[] {"self"} );
			}
			//Function test:test_count_with_default
			{
			IMethod methodtest_count_with_default138;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_with_default138 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_with_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_with_default138, new String[] {"self"} );
			}
			//Function test:test_count_overriding_default
			{
			IMethod methodtest_count_overriding_default139;
				IModelElement[] classTestCount124Childs = classTestCount124.getChildren();
				methodtest_count_overriding_default139 = ModelTestUtils.getAssertMethod( classTestCount124Childs, "test_count_overriding_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count_overriding_default139, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMultipleArgs140;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMultipleArgs140 = ModelTestUtils.getAssertClass( moduleChilds, "TestMultipleArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp141;
				IModelElement[] classTestMultipleArgs140Childs = classTestMultipleArgs140.getChildren();
				methodsetUp141 = ModelTestUtils.getAssertMethod( classTestMultipleArgs140Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp141, new String[] {"self"} );
			}
			{
				IModelElement[] classTestMultipleArgs140Childs = classTestMultipleArgs140.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMultipleArgs140Childs, "parser");
			}
			//Function test:test_nargs_with_positional_args
			{
			IMethod methodtest_nargs_with_positional_args143;
				IModelElement[] classTestMultipleArgs140Childs = classTestMultipleArgs140.getChildren();
				methodtest_nargs_with_positional_args143 = ModelTestUtils.getAssertMethod( classTestMultipleArgs140Childs, "test_nargs_with_positional_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_with_positional_args143, new String[] {"self"} );
			}
			//Function test:test_nargs_long_opt
			{
			IMethod methodtest_nargs_long_opt144;
				IModelElement[] classTestMultipleArgs140Childs = classTestMultipleArgs140.getChildren();
				methodtest_nargs_long_opt144 = ModelTestUtils.getAssertMethod( classTestMultipleArgs140Childs, "test_nargs_long_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_long_opt144, new String[] {"self"} );
			}
			//Function test:test_nargs_invalid_float_value
			{
			IMethod methodtest_nargs_invalid_float_value145;
				IModelElement[] classTestMultipleArgs140Childs = classTestMultipleArgs140.getChildren();
				methodtest_nargs_invalid_float_value145 = ModelTestUtils.getAssertMethod( classTestMultipleArgs140Childs, "test_nargs_invalid_float_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_invalid_float_value145, new String[] {"self"} );
			}
			//Function test:test_nargs_required_values
			{
			IMethod methodtest_nargs_required_values146;
				IModelElement[] classTestMultipleArgs140Childs = classTestMultipleArgs140.getChildren();
				methodtest_nargs_required_values146 = ModelTestUtils.getAssertMethod( classTestMultipleArgs140Childs, "test_nargs_required_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_required_values146, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMultipleArgsAppend147;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMultipleArgsAppend147 = ModelTestUtils.getAssertClass( moduleChilds, "TestMultipleArgsAppend" );
			//Function test:setUp
			{
			IMethod methodsetUp148;
				IModelElement[] classTestMultipleArgsAppend147Childs = classTestMultipleArgsAppend147.getChildren();
				methodsetUp148 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend147Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp148, new String[] {"self"} );
			}
			{
				IModelElement[] classTestMultipleArgsAppend147Childs = classTestMultipleArgsAppend147.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestMultipleArgsAppend147Childs, "parser");
			}
			//Function test:test_nargs_append
			{
			IMethod methodtest_nargs_append150;
				IModelElement[] classTestMultipleArgsAppend147Childs = classTestMultipleArgsAppend147.getChildren();
				methodtest_nargs_append150 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend147Childs, "test_nargs_append", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_append150, new String[] {"self"} );
			}
			//Function test:test_nargs_append_required_values
			{
			IMethod methodtest_nargs_append_required_values151;
				IModelElement[] classTestMultipleArgsAppend147Childs = classTestMultipleArgsAppend147.getChildren();
				methodtest_nargs_append_required_values151 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend147Childs, "test_nargs_append_required_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_append_required_values151, new String[] {"self"} );
			}
			//Function test:test_nargs_append_simple
			{
			IMethod methodtest_nargs_append_simple152;
				IModelElement[] classTestMultipleArgsAppend147Childs = classTestMultipleArgsAppend147.getChildren();
				methodtest_nargs_append_simple152 = ModelTestUtils.getAssertMethod( classTestMultipleArgsAppend147Childs, "test_nargs_append_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nargs_append_simple152, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestVersion153;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVersion153 = ModelTestUtils.getAssertClass( moduleChilds, "TestVersion" );
			//Function test:test_version
			{
			IMethod methodtest_version154;
				IModelElement[] classTestVersion153Childs = classTestVersion153.getChildren();
				methodtest_version154 = ModelTestUtils.getAssertMethod( classTestVersion153Childs, "test_version", 1 );
				ModelTestUtils.assertParameterNames( methodtest_version154, new String[] {"self"} );
			}
			{
				IModelElement[] classTestVersion153Childs = classTestVersion153.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestVersion153Childs, "parser");
			}
			//Function test:test_no_version
			{
			IMethod methodtest_no_version156;
				IModelElement[] classTestVersion153Childs = classTestVersion153.getChildren();
				methodtest_no_version156 = ModelTestUtils.getAssertMethod( classTestVersion153Childs, "test_no_version", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_version156, new String[] {"self"} );
			}
			{
				IModelElement[] classTestVersion153Childs = classTestVersion153.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestVersion153Childs, "parser");
			}
		}
		//Class test
		{
		IType classTestConflictingDefaults158;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflictingDefaults158 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflictingDefaults" );
			//Function test:setUp
			{
			IMethod methodsetUp159;
				IModelElement[] classTestConflictingDefaults158Childs = classTestConflictingDefaults158.getChildren();
				methodsetUp159 = ModelTestUtils.getAssertMethod( classTestConflictingDefaults158Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp159, new String[] {"self"} );
			}
			{
				IModelElement[] classTestConflictingDefaults158Childs = classTestConflictingDefaults158.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestConflictingDefaults158Childs, "parser");
			}
			//Function test:test_conflict_default
			{
			IMethod methodtest_conflict_default161;
				IModelElement[] classTestConflictingDefaults158Childs = classTestConflictingDefaults158.getChildren();
				methodtest_conflict_default161 = ModelTestUtils.getAssertMethod( classTestConflictingDefaults158Childs, "test_conflict_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_default161, new String[] {"self"} );
			}
			//Function test:test_conflict_default_none
			{
			IMethod methodtest_conflict_default_none162;
				IModelElement[] classTestConflictingDefaults158Childs = classTestConflictingDefaults158.getChildren();
				methodtest_conflict_default_none162 = ModelTestUtils.getAssertMethod( classTestConflictingDefaults158Childs, "test_conflict_default_none", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_default_none162, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestOptionGroup163;
			IModelElement[] moduleChilds = module.getChildren();
			classTestOptionGroup163 = ModelTestUtils.getAssertClass( moduleChilds, "TestOptionGroup" );
			//Function test:setUp
			{
			IMethod methodsetUp164;
				IModelElement[] classTestOptionGroup163Childs = classTestOptionGroup163.getChildren();
				methodsetUp164 = ModelTestUtils.getAssertMethod( classTestOptionGroup163Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp164, new String[] {"self"} );
			}
			//Function test:test_option_group_create_instance
			{
			IMethod methodtest_option_group_create_instance165;
				IModelElement[] classTestOptionGroup163Childs = classTestOptionGroup163.getChildren();
				methodtest_option_group_create_instance165 = ModelTestUtils.getAssertMethod( classTestOptionGroup163Childs, "test_option_group_create_instance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_option_group_create_instance165, new String[] {"self"} );
			}
			//Function test:test_add_group_no_group
			{
			IMethod methodtest_add_group_no_group166;
				IModelElement[] classTestOptionGroup163Childs = classTestOptionGroup163.getChildren();
				methodtest_add_group_no_group166 = ModelTestUtils.getAssertMethod( classTestOptionGroup163Childs, "test_add_group_no_group", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_group_no_group166, new String[] {"self"} );
			}
			//Function test:test_add_group_invalid_arguments
			{
			IMethod methodtest_add_group_invalid_arguments167;
				IModelElement[] classTestOptionGroup163Childs = classTestOptionGroup163.getChildren();
				methodtest_add_group_invalid_arguments167 = ModelTestUtils.getAssertMethod( classTestOptionGroup163Childs, "test_add_group_invalid_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_group_invalid_arguments167, new String[] {"self"} );
			}
			//Function test:test_add_group_wrong_parser
			{
			IMethod methodtest_add_group_wrong_parser168;
				IModelElement[] classTestOptionGroup163Childs = classTestOptionGroup163.getChildren();
				methodtest_add_group_wrong_parser168 = ModelTestUtils.getAssertMethod( classTestOptionGroup163Childs, "test_add_group_wrong_parser", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_group_wrong_parser168, new String[] {"self"} );
			}
			//Function test:test_group_manipulate
			{
			IMethod methodtest_group_manipulate169;
				IModelElement[] classTestOptionGroup163Childs = classTestOptionGroup163.getChildren();
				methodtest_group_manipulate169 = ModelTestUtils.getAssertMethod( classTestOptionGroup163Childs, "test_group_manipulate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_group_manipulate169, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExtendAddTypes170;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExtendAddTypes170 = ModelTestUtils.getAssertClass( moduleChilds, "TestExtendAddTypes" );
			//Function test:setUp
			{
			IMethod methodsetUp171;
				IModelElement[] classTestExtendAddTypes170Childs = classTestExtendAddTypes170.getChildren();
				methodsetUp171 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes170Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp171, new String[] {"self"} );
			}
			{
				IModelElement[] classTestExtendAddTypes170Childs = classTestExtendAddTypes170.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExtendAddTypes170Childs, "parser");
			}
			//Class test
			{
			IType classMyOption173;
				IModelElement[] classTestExtendAddTypes170Childs = classTestExtendAddTypes170.getChildren();
				classMyOption173 = ModelTestUtils.getAssertClass( classTestExtendAddTypes170Childs, "MyOption" );
				//Function test:check_file
				{
				IMethod methodcheck_file174;
					IModelElement[] classMyOption173Childs = classMyOption173.getChildren();
					methodcheck_file174 = ModelTestUtils.getAssertMethod( classMyOption173Childs, "check_file", 3 );
					ModelTestUtils.assertParameterNames( methodcheck_file174, new String[] {"option", "opt", "value"} );
				}
				{
					IModelElement[] classMyOption173Childs = classMyOption173.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption173Childs, "TYPES");
				}
				{
					IModelElement[] classMyOption173Childs = classMyOption173.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption173Childs, "TYPE_CHECKER");
				}
			}
			//Function test:test_extend_file
			{
			IMethod methodtest_extend_file175;
				IModelElement[] classTestExtendAddTypes170Childs = classTestExtendAddTypes170.getChildren();
				methodtest_extend_file175 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes170Childs, "test_extend_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_file175, new String[] {"self"} );
			}
			//Function test:test_extend_file_nonexistent
			{
			IMethod methodtest_extend_file_nonexistent176;
				IModelElement[] classTestExtendAddTypes170Childs = classTestExtendAddTypes170.getChildren();
				methodtest_extend_file_nonexistent176 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes170Childs, "test_extend_file_nonexistent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_file_nonexistent176, new String[] {"self"} );
			}
			//Function test:test_file_irregular
			{
			IMethod methodtest_file_irregular177;
				IModelElement[] classTestExtendAddTypes170Childs = classTestExtendAddTypes170.getChildren();
				methodtest_file_irregular177 = ModelTestUtils.getAssertMethod( classTestExtendAddTypes170Childs, "test_file_irregular", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file_irregular177, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExtendAddActions178;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExtendAddActions178 = ModelTestUtils.getAssertClass( moduleChilds, "TestExtendAddActions" );
			//Function test:setUp
			{
			IMethod methodsetUp179;
				IModelElement[] classTestExtendAddActions178Childs = classTestExtendAddActions178.getChildren();
				methodsetUp179 = ModelTestUtils.getAssertMethod( classTestExtendAddActions178Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp179, new String[] {"self"} );
			}
			{
				IModelElement[] classTestExtendAddActions178Childs = classTestExtendAddActions178.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestExtendAddActions178Childs, "parser");
			}
			//Class test
			{
			IType classMyOption181;
				IModelElement[] classTestExtendAddActions178Childs = classTestExtendAddActions178.getChildren();
				classMyOption181 = ModelTestUtils.getAssertClass( classTestExtendAddActions178Childs, "MyOption" );
				{
					IModelElement[] classMyOption181Childs = classMyOption181.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption181Childs, "ACTIONS");
				}
				{
					IModelElement[] classMyOption181Childs = classMyOption181.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption181Childs, "STORE_ACTIONS");
				}
				{
					IModelElement[] classMyOption181Childs = classMyOption181.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classMyOption181Childs, "TYPED_ACTIONS");
				}
				//Function test:take_action
				{
				IMethod methodtake_action182;
					IModelElement[] classMyOption181Childs = classMyOption181.getChildren();
					methodtake_action182 = ModelTestUtils.getAssertMethod( classMyOption181Childs, "take_action", 7 );
					ModelTestUtils.assertParameterNames( methodtake_action182, new String[] {"self", "action", "dest", "opt", "value", "values", "parser"} );
				}
			}
			//Function test:test_extend_add_action
			{
			IMethod methodtest_extend_add_action183;
				IModelElement[] classTestExtendAddActions178Childs = classTestExtendAddActions178.getChildren();
				methodtest_extend_add_action183 = ModelTestUtils.getAssertMethod( classTestExtendAddActions178Childs, "test_extend_add_action", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_add_action183, new String[] {"self"} );
			}
			//Function test:test_extend_add_action_normal
			{
			IMethod methodtest_extend_add_action_normal184;
				IModelElement[] classTestExtendAddActions178Childs = classTestExtendAddActions178.getChildren();
				methodtest_extend_add_action_normal184 = ModelTestUtils.getAssertMethod( classTestExtendAddActions178Childs, "test_extend_add_action_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend_add_action_normal184, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallback185;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallback185 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallback" );
			//Function test:setUp
			{
			IMethod methodsetUp186;
				IModelElement[] classTestCallback185Childs = classTestCallback185.getChildren();
				methodsetUp186 = ModelTestUtils.getAssertMethod( classTestCallback185Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp186, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCallback185Childs = classTestCallback185.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCallback185Childs, "parser");
			}
			//Function test:process_opt
			{
			IMethod methodprocess_opt188;
				IModelElement[] classTestCallback185Childs = classTestCallback185.getChildren();
				methodprocess_opt188 = ModelTestUtils.getAssertMethod( classTestCallback185Childs, "process_opt", 5 );
				ModelTestUtils.assertParameterNames( methodprocess_opt188, new String[] {"self", "option", "opt", "value", "parser_"} );
			}
			//Function test:test_callback
			{
			IMethod methodtest_callback189;
				IModelElement[] classTestCallback185Childs = classTestCallback185.getChildren();
				methodtest_callback189 = ModelTestUtils.getAssertMethod( classTestCallback185Childs, "test_callback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback189, new String[] {"self"} );
			}
			//Function test:test_callback_help
			{
			IMethod methodtest_callback_help190;
				IModelElement[] classTestCallback185Childs = classTestCallback185.getChildren();
				methodtest_callback_help190 = ModelTestUtils.getAssertMethod( classTestCallback185Childs, "test_callback_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_help190, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackExtraArgs191;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackExtraArgs191 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackExtraArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp192;
				IModelElement[] classTestCallbackExtraArgs191Childs = classTestCallbackExtraArgs191.getChildren();
				methodsetUp192 = ModelTestUtils.getAssertMethod( classTestCallbackExtraArgs191Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp192, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCallbackExtraArgs191Childs = classTestCallbackExtraArgs191.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCallbackExtraArgs191Childs, "parser");
			}
			//Function test:process_tuple
			{
			IMethod methodprocess_tuple194;
				IModelElement[] classTestCallbackExtraArgs191Childs = classTestCallbackExtraArgs191.getChildren();
				methodprocess_tuple194 = ModelTestUtils.getAssertMethod( classTestCallbackExtraArgs191Childs, "process_tuple", 7 );
				ModelTestUtils.assertParameterNames( methodprocess_tuple194, new String[] {"self", "option", "opt", "value", "parser_", "len", "type"} );
			}
			//Function test:test_callback_extra_args
			{
			IMethod methodtest_callback_extra_args195;
				IModelElement[] classTestCallbackExtraArgs191Childs = classTestCallbackExtraArgs191.getChildren();
				methodtest_callback_extra_args195 = ModelTestUtils.getAssertMethod( classTestCallbackExtraArgs191Childs, "test_callback_extra_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_extra_args195, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackMeddleArgs196;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackMeddleArgs196 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackMeddleArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp197;
				IModelElement[] classTestCallbackMeddleArgs196Childs = classTestCallbackMeddleArgs196.getChildren();
				methodsetUp197 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs196Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp197, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCallbackMeddleArgs196Childs = classTestCallbackMeddleArgs196.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCallbackMeddleArgs196Childs, "parser");
			}
			//Function test:process_n
			{
			IMethod methodprocess_n199;
				IModelElement[] classTestCallbackMeddleArgs196Childs = classTestCallbackMeddleArgs196.getChildren();
				methodprocess_n199 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs196Childs, "process_n", 5 );
				ModelTestUtils.assertParameterNames( methodprocess_n199, new String[] {"self", "option", "opt", "value", "parser_"} );
			}
			//Function test:test_callback_meddle_args
			{
			IMethod methodtest_callback_meddle_args200;
				IModelElement[] classTestCallbackMeddleArgs196Childs = classTestCallbackMeddleArgs196.getChildren();
				methodtest_callback_meddle_args200 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs196Childs, "test_callback_meddle_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_meddle_args200, new String[] {"self"} );
			}
			//Function test:test_callback_meddle_args_separator
			{
			IMethod methodtest_callback_meddle_args_separator201;
				IModelElement[] classTestCallbackMeddleArgs196Childs = classTestCallbackMeddleArgs196.getChildren();
				methodtest_callback_meddle_args_separator201 = ModelTestUtils.getAssertMethod( classTestCallbackMeddleArgs196Childs, "test_callback_meddle_args_separator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callback_meddle_args_separator201, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackManyArgs202;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackManyArgs202 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackManyArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp203;
				IModelElement[] classTestCallbackManyArgs202Childs = classTestCallbackManyArgs202.getChildren();
				methodsetUp203 = ModelTestUtils.getAssertMethod( classTestCallbackManyArgs202Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp203, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCallbackManyArgs202Childs = classTestCallbackManyArgs202.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCallbackManyArgs202Childs, "parser");
			}
			//Function test:process_many
			{
			IMethod methodprocess_many205;
				IModelElement[] classTestCallbackManyArgs202Childs = classTestCallbackManyArgs202.getChildren();
				methodprocess_many205 = ModelTestUtils.getAssertMethod( classTestCallbackManyArgs202Childs, "process_many", 5 );
				ModelTestUtils.assertParameterNames( methodprocess_many205, new String[] {"self", "option", "opt", "value", "parser_"} );
			}
			//Function test:test_many_args
			{
			IMethod methodtest_many_args206;
				IModelElement[] classTestCallbackManyArgs202Childs = classTestCallbackManyArgs202.getChildren();
				methodtest_many_args206 = ModelTestUtils.getAssertMethod( classTestCallbackManyArgs202Childs, "test_many_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_many_args206, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackCheckAbbrev207;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackCheckAbbrev207 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackCheckAbbrev" );
			//Function test:setUp
			{
			IMethod methodsetUp208;
				IModelElement[] classTestCallbackCheckAbbrev207Childs = classTestCallbackCheckAbbrev207.getChildren();
				methodsetUp208 = ModelTestUtils.getAssertMethod( classTestCallbackCheckAbbrev207Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp208, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCallbackCheckAbbrev207Childs = classTestCallbackCheckAbbrev207.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCallbackCheckAbbrev207Childs, "parser");
			}
			//Function test:check_abbrev
			{
			IMethod methodcheck_abbrev210;
				IModelElement[] classTestCallbackCheckAbbrev207Childs = classTestCallbackCheckAbbrev207.getChildren();
				methodcheck_abbrev210 = ModelTestUtils.getAssertMethod( classTestCallbackCheckAbbrev207Childs, "check_abbrev", 5 );
				ModelTestUtils.assertParameterNames( methodcheck_abbrev210, new String[] {"self", "option", "opt", "value", "parser"} );
			}
			//Function test:test_abbrev_callback_expansion
			{
			IMethod methodtest_abbrev_callback_expansion211;
				IModelElement[] classTestCallbackCheckAbbrev207Childs = classTestCallbackCheckAbbrev207.getChildren();
				methodtest_abbrev_callback_expansion211 = ModelTestUtils.getAssertMethod( classTestCallbackCheckAbbrev207Childs, "test_abbrev_callback_expansion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abbrev_callback_expansion211, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCallbackVarArgs212;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCallbackVarArgs212 = ModelTestUtils.getAssertClass( moduleChilds, "TestCallbackVarArgs" );
			//Function test:setUp
			{
			IMethod methodsetUp213;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodsetUp213 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp213, new String[] {"self"} );
			}
			{
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestCallbackVarArgs212Childs, "parser");
			}
			//Function test:variable_args
			{
			IMethod methodvariable_args215;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodvariable_args215 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "variable_args", 5 );
				ModelTestUtils.assertParameterNames( methodvariable_args215, new String[] {"self", "option", "opt", "value", "parser"} );
			}
			//Function test:test_variable_args
			{
			IMethod methodtest_variable_args216;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodtest_variable_args216 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "test_variable_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_variable_args216, new String[] {"self"} );
			}
			//Function test:test_consume_separator_stop_at_option
			{
			IMethod methodtest_consume_separator_stop_at_option217;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodtest_consume_separator_stop_at_option217 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "test_consume_separator_stop_at_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_consume_separator_stop_at_option217, new String[] {"self"} );
			}
			//Function test:test_positional_arg_and_variable_args
			{
			IMethod methodtest_positional_arg_and_variable_args218;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodtest_positional_arg_and_variable_args218 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "test_positional_arg_and_variable_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_positional_arg_and_variable_args218, new String[] {"self"} );
			}
			//Function test:test_stop_at_option
			{
			IMethod methodtest_stop_at_option219;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodtest_stop_at_option219 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "test_stop_at_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_at_option219, new String[] {"self"} );
			}
			//Function test:test_stop_at_invalid_option
			{
			IMethod methodtest_stop_at_invalid_option220;
				IModelElement[] classTestCallbackVarArgs212Childs = classTestCallbackVarArgs212.getChildren();
				methodtest_stop_at_invalid_option220 = ModelTestUtils.getAssertMethod( classTestCallbackVarArgs212Childs, "test_stop_at_invalid_option", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_at_invalid_option220, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classConflictBase221;
			IModelElement[] moduleChilds = module.getChildren();
			classConflictBase221 = ModelTestUtils.getAssertClass( moduleChilds, "ConflictBase" );
			//Function test:setUp
			{
			IMethod methodsetUp222;
				IModelElement[] classConflictBase221Childs = classConflictBase221.getChildren();
				methodsetUp222 = ModelTestUtils.getAssertMethod( classConflictBase221Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp222, new String[] {"self"} );
			}
			{
				IModelElement[] classConflictBase221Childs = classConflictBase221.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classConflictBase221Childs, "parser");
			}
			//Function test:show_version
			{
			IMethod methodshow_version224;
				IModelElement[] classConflictBase221Childs = classConflictBase221.getChildren();
				methodshow_version224 = ModelTestUtils.getAssertMethod( classConflictBase221Childs, "show_version", 5 );
				ModelTestUtils.assertParameterNames( methodshow_version224, new String[] {"self", "option", "opt", "value", "parser"} );
			}
		}
		//Class test
		{
		IType classTestConflict225;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflict225 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflict" );
			//Function test:assert_conflict_error
			{
			IMethod methodassert_conflict_error226;
				IModelElement[] classTestConflict225Childs = classTestConflict225.getChildren();
				methodassert_conflict_error226 = ModelTestUtils.getAssertMethod( classTestConflict225Childs, "assert_conflict_error", 2 );
				ModelTestUtils.assertParameterNames( methodassert_conflict_error226, new String[] {"self", "func"} );
			}
			//Function test:test_conflict_error
			{
			IMethod methodtest_conflict_error227;
				IModelElement[] classTestConflict225Childs = classTestConflict225.getChildren();
				methodtest_conflict_error227 = ModelTestUtils.getAssertMethod( classTestConflict225Childs, "test_conflict_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_error227, new String[] {"self"} );
			}
			//Function test:test_conflict_error_group
			{
			IMethod methodtest_conflict_error_group228;
				IModelElement[] classTestConflict225Childs = classTestConflict225.getChildren();
				methodtest_conflict_error_group228 = ModelTestUtils.getAssertMethod( classTestConflict225Childs, "test_conflict_error_group", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_error_group228, new String[] {"self"} );
			}
			//Function test:test_no_such_conflict_handler
			{
			IMethod methodtest_no_such_conflict_handler229;
				IModelElement[] classTestConflict225Childs = classTestConflict225.getChildren();
				methodtest_no_such_conflict_handler229 = ModelTestUtils.getAssertMethod( classTestConflict225Childs, "test_no_such_conflict_handler", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_such_conflict_handler229, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestConflictResolve230;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflictResolve230 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflictResolve" );
			//Function test:setUp
			{
			IMethod methodsetUp231;
				IModelElement[] classTestConflictResolve230Childs = classTestConflictResolve230.getChildren();
				methodsetUp231 = ModelTestUtils.getAssertMethod( classTestConflictResolve230Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp231, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve
			{
			IMethod methodtest_conflict_resolve232;
				IModelElement[] classTestConflictResolve230Childs = classTestConflictResolve230.getChildren();
				methodtest_conflict_resolve232 = ModelTestUtils.getAssertMethod( classTestConflictResolve230Childs, "test_conflict_resolve", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve232, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_help
			{
			IMethod methodtest_conflict_resolve_help233;
				IModelElement[] classTestConflictResolve230Childs = classTestConflictResolve230.getChildren();
				methodtest_conflict_resolve_help233 = ModelTestUtils.getAssertMethod( classTestConflictResolve230Childs, "test_conflict_resolve_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_help233, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_short_opt
			{
			IMethod methodtest_conflict_resolve_short_opt234;
				IModelElement[] classTestConflictResolve230Childs = classTestConflictResolve230.getChildren();
				methodtest_conflict_resolve_short_opt234 = ModelTestUtils.getAssertMethod( classTestConflictResolve230Childs, "test_conflict_resolve_short_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_short_opt234, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_long_opt
			{
			IMethod methodtest_conflict_resolve_long_opt235;
				IModelElement[] classTestConflictResolve230Childs = classTestConflictResolve230.getChildren();
				methodtest_conflict_resolve_long_opt235 = ModelTestUtils.getAssertMethod( classTestConflictResolve230Childs, "test_conflict_resolve_long_opt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_long_opt235, new String[] {"self"} );
			}
			//Function test:test_conflict_resolve_long_opts
			{
			IMethod methodtest_conflict_resolve_long_opts236;
				IModelElement[] classTestConflictResolve230Childs = classTestConflictResolve230.getChildren();
				methodtest_conflict_resolve_long_opts236 = ModelTestUtils.getAssertMethod( classTestConflictResolve230Childs, "test_conflict_resolve_long_opts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_resolve_long_opts236, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestConflictOverride237;
			IModelElement[] moduleChilds = module.getChildren();
			classTestConflictOverride237 = ModelTestUtils.getAssertClass( moduleChilds, "TestConflictOverride" );
			//Function test:setUp
			{
			IMethod methodsetUp238;
				IModelElement[] classTestConflictOverride237Childs = classTestConflictOverride237.getChildren();
				methodsetUp238 = ModelTestUtils.getAssertMethod( classTestConflictOverride237Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp238, new String[] {"self"} );
			}
			{
				IModelElement[] classTestConflictOverride237Childs = classTestConflictOverride237.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestConflictOverride237Childs, "parser");
			}
			//Function test:test_conflict_override_opts
			{
			IMethod methodtest_conflict_override_opts240;
				IModelElement[] classTestConflictOverride237Childs = classTestConflictOverride237.getChildren();
				methodtest_conflict_override_opts240 = ModelTestUtils.getAssertMethod( classTestConflictOverride237Childs, "test_conflict_override_opts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_override_opts240, new String[] {"self"} );
			}
			//Function test:test_conflict_override_help
			{
			IMethod methodtest_conflict_override_help241;
				IModelElement[] classTestConflictOverride237Childs = classTestConflictOverride237.getChildren();
				methodtest_conflict_override_help241 = ModelTestUtils.getAssertMethod( classTestConflictOverride237Childs, "test_conflict_override_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_override_help241, new String[] {"self"} );
			}
			//Function test:test_conflict_override_args
			{
			IMethod methodtest_conflict_override_args242;
				IModelElement[] classTestConflictOverride237Childs = classTestConflictOverride237.getChildren();
				methodtest_conflict_override_args242 = ModelTestUtils.getAssertMethod( classTestConflictOverride237Childs, "test_conflict_override_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conflict_override_args242, new String[] {"self"} );
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
		IType classTestHelp243;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHelp243 = ModelTestUtils.getAssertClass( moduleChilds, "TestHelp" );
			//Function test:setUp
			{
			IMethod methodsetUp244;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodsetUp244 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp244, new String[] {"self"} );
			}
			{
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHelp243Childs, "parser");
			}
			//Function test:make_parser
			{
			IMethod methodmake_parser246;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodmake_parser246 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "make_parser", 2 );
				ModelTestUtils.assertParameterNames( methodmake_parser246, new String[] {"self", "columns"} );
			}
			//Function test:assertHelpEquals
			{
			IMethod methodassertHelpEquals247;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodassertHelpEquals247 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "assertHelpEquals", 2 );
				ModelTestUtils.assertParameterNames( methodassertHelpEquals247, new String[] {"self", "expected_output"} );
			}
			//Function test:test_help
			{
			IMethod methodtest_help248;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodtest_help248 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "test_help", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help248, new String[] {"self"} );
			}
			//Function test:test_help_old_usage
			{
			IMethod methodtest_help_old_usage249;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodtest_help_old_usage249 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "test_help_old_usage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_old_usage249, new String[] {"self"} );
			}
			//Function test:test_help_long_opts_first
			{
			IMethod methodtest_help_long_opts_first250;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodtest_help_long_opts_first250 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "test_help_long_opts_first", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_long_opts_first250, new String[] {"self"} );
			}
			//Function test:test_help_title_formatter
			{
			IMethod methodtest_help_title_formatter251;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodtest_help_title_formatter251 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "test_help_title_formatter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_title_formatter251, new String[] {"self"} );
			}
			//Function test:test_wrap_columns
			{
			IMethod methodtest_wrap_columns252;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodtest_wrap_columns252 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "test_wrap_columns", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrap_columns252, new String[] {"self"} );
			}
			{
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHelp243Childs, "parser");
			}
			//Function test:test_help_description_groups
			{
			IMethod methodtest_help_description_groups254;
				IModelElement[] classTestHelp243Childs = classTestHelp243.getChildren();
				methodtest_help_description_groups254 = ModelTestUtils.getAssertMethod( classTestHelp243Childs, "test_help_description_groups", 1 );
				ModelTestUtils.assertParameterNames( methodtest_help_description_groups254, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMatchAbbrev255;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMatchAbbrev255 = ModelTestUtils.getAssertClass( moduleChilds, "TestMatchAbbrev" );
			//Function test:test_match_abbrev
			{
			IMethod methodtest_match_abbrev256;
				IModelElement[] classTestMatchAbbrev255Childs = classTestMatchAbbrev255.getChildren();
				methodtest_match_abbrev256 = ModelTestUtils.getAssertMethod( classTestMatchAbbrev255Childs, "test_match_abbrev", 1 );
				ModelTestUtils.assertParameterNames( methodtest_match_abbrev256, new String[] {"self"} );
			}
			//Function test:test_match_abbrev_error
			{
			IMethod methodtest_match_abbrev_error257;
				IModelElement[] classTestMatchAbbrev255Childs = classTestMatchAbbrev255.getChildren();
				methodtest_match_abbrev_error257 = ModelTestUtils.getAssertMethod( classTestMatchAbbrev255Childs, "test_match_abbrev_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_match_abbrev_error257, new String[] {"self"} );
			}
		}
		//Function test:_testclasses
		{
		IMethod method_testclasses258;
			IModelElement[] moduleChilds = module.getChildren();
			method_testclasses258 = ModelTestUtils.getAssertMethod( moduleChilds, "_testclasses", 0 );
		}
		//Function test:suite
		{
		IMethod methodsuite259;
			IModelElement[] moduleChilds = module.getChildren();
			methodsuite259 = ModelTestUtils.getAssertMethod( moduleChilds, "suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main260;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main260 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
	