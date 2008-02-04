
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

public class GeneratedModelTests5 extends AbstractModelTests
{
	public GeneratedModelTests5(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests5.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_5", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests5" );
	}
	public void testModelGen250( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("string_tests.py"));

		assertNotNull("Module string_tests.py not found", module);
		assertEquals("string_tests.py", module.getElementName());
		
		//Class test
		{
		IType classSequence0;
			IModelElement[] moduleChilds = module.getChildren();
			classSequence0 = ModelTestUtils.getAssertClass( moduleChilds, "Sequence" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classSequence0Childs = classSequence0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classSequence0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "seq"} );
			}
			//Function test:__len__
			{
			IMethod method__len__2;
				IModelElement[] classSequence0Childs = classSequence0.getChildren();
				method__len__2 = ModelTestUtils.getAssertMethod( classSequence0Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__2, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__3;
				IModelElement[] classSequence0Childs = classSequence0.getChildren();
				method__getitem__3 = ModelTestUtils.getAssertMethod( classSequence0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__3, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classBadSeq14;
			IModelElement[] moduleChilds = module.getChildren();
			classBadSeq14 = ModelTestUtils.getAssertClass( moduleChilds, "BadSeq1" );
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classBadSeq14Childs = classBadSeq14.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classBadSeq14Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBadSeq26;
			IModelElement[] moduleChilds = module.getChildren();
			classBadSeq26 = ModelTestUtils.getAssertClass( moduleChilds, "BadSeq2" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classBadSeq26Childs = classBadSeq26.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classBadSeq26Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self"} );
			}
			//Function test:__len__
			{
			IMethod method__len__8;
				IModelElement[] classBadSeq26Childs = classBadSeq26.getChildren();
				method__len__8 = ModelTestUtils.getAssertMethod( classBadSeq26Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCommonTest9;
			IModelElement[] moduleChilds = module.getChildren();
			classCommonTest9 = ModelTestUtils.getAssertClass( moduleChilds, "CommonTest" );
			{
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCommonTest9Childs, "type2test");
			}
			//Function test:fixtype
			{
			IMethod methodfixtype10;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodfixtype10 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "fixtype", 2 );
				ModelTestUtils.assertParameterNames( methodfixtype10, new String[] {"self", "obj"} );
			}
			//Function test:checkequal
			{
			IMethod methodcheckequal11;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodcheckequal11 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "checkequal", 5 );
				ModelTestUtils.assertParameterNames( methodcheckequal11, new String[] {"self", "result", "object", "methodname", "args"} );
				//Class test
				{
				IType classsubtype12;
					IModelElement[] methodcheckequal11Childs = methodcheckequal11.getChildren();
					classsubtype12 = ModelTestUtils.getAssertClass( methodcheckequal11Childs, "subtype" );
				}
			}
			//Function test:checkraises
			{
			IMethod methodcheckraises13;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodcheckraises13 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "checkraises", 5 );
				ModelTestUtils.assertParameterNames( methodcheckraises13, new String[] {"self", "exc", "object", "methodname", "args"} );
			}
			//Function test:checkcall
			{
			IMethod methodcheckcall14;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodcheckcall14 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "checkcall", 4 );
				ModelTestUtils.assertParameterNames( methodcheckcall14, new String[] {"self", "object", "methodname", "args"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash15;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_hash15 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash15, new String[] {"self"} );
			}
			//Function test:test_capitalize
			{
			IMethod methodtest_capitalize16;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_capitalize16 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_capitalize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_capitalize16, new String[] {"self"} );
			}
			//Function test:test_count
			{
			IMethod methodtest_count17;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_count17 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_count", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count17, new String[] {"self"} );
			}
			//Function test:test_find
			{
			IMethod methodtest_find18;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_find18 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_find", 1 );
				ModelTestUtils.assertParameterNames( methodtest_find18, new String[] {"self"} );
			}
			//Function test:test_rfind
			{
			IMethod methodtest_rfind19;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_rfind19 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_rfind", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rfind19, new String[] {"self"} );
			}
			//Function test:test_index
			{
			IMethod methodtest_index20;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_index20 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_index", 1 );
				ModelTestUtils.assertParameterNames( methodtest_index20, new String[] {"self"} );
			}
			//Function test:test_rindex
			{
			IMethod methodtest_rindex21;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_rindex21 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_rindex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rindex21, new String[] {"self"} );
			}
			//Function test:test_lower
			{
			IMethod methodtest_lower22;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_lower22 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_lower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lower22, new String[] {"self"} );
			}
			//Function test:test_upper
			{
			IMethod methodtest_upper23;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_upper23 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_upper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_upper23, new String[] {"self"} );
			}
			//Function test:test_expandtabs
			{
			IMethod methodtest_expandtabs24;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_expandtabs24 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_expandtabs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expandtabs24, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split25;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_split25 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split25, new String[] {"self"} );
			}
			//Function test:test_rsplit
			{
			IMethod methodtest_rsplit26;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_rsplit26 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_rsplit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rsplit26, new String[] {"self"} );
			}
			//Function test:test_strip
			{
			IMethod methodtest_strip27;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_strip27 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_strip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strip27, new String[] {"self"} );
			}
			//Function test:test_ljust
			{
			IMethod methodtest_ljust28;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_ljust28 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_ljust", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ljust28, new String[] {"self"} );
			}
			//Function test:test_rjust
			{
			IMethod methodtest_rjust29;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_rjust29 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_rjust", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rjust29, new String[] {"self"} );
			}
			//Function test:test_center
			{
			IMethod methodtest_center30;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_center30 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_center", 1 );
				ModelTestUtils.assertParameterNames( methodtest_center30, new String[] {"self"} );
			}
			//Function test:test_swapcase
			{
			IMethod methodtest_swapcase31;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_swapcase31 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_swapcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_swapcase31, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace32;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_replace32 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace32, new String[] {"self"} );
			}
			//Function test:test_zfill
			{
			IMethod methodtest_zfill33;
				IModelElement[] classCommonTest9Childs = classCommonTest9.getChildren();
				methodtest_zfill33 = ModelTestUtils.getAssertMethod( classCommonTest9Childs, "test_zfill", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zfill33, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrUnicodeUserStringTest34;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrUnicodeUserStringTest34 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrUnicodeUserStringTest" );
			//Function test:test_islower
			{
			IMethod methodtest_islower35;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_islower35 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_islower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islower35, new String[] {"self"} );
			}
			//Function test:test_isupper
			{
			IMethod methodtest_isupper36;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_isupper36 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_isupper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isupper36, new String[] {"self"} );
			}
			//Function test:test_istitle
			{
			IMethod methodtest_istitle37;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_istitle37 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_istitle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_istitle37, new String[] {"self"} );
			}
			//Function test:test_isspace
			{
			IMethod methodtest_isspace38;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_isspace38 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_isspace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isspace38, new String[] {"self"} );
			}
			//Function test:test_isalpha
			{
			IMethod methodtest_isalpha39;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_isalpha39 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_isalpha", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isalpha39, new String[] {"self"} );
			}
			//Function test:test_isalnum
			{
			IMethod methodtest_isalnum40;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_isalnum40 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_isalnum", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isalnum40, new String[] {"self"} );
			}
			//Function test:test_isdigit
			{
			IMethod methodtest_isdigit41;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_isdigit41 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_isdigit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isdigit41, new String[] {"self"} );
			}
			//Function test:test_title
			{
			IMethod methodtest_title42;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_title42 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_title", 1 );
				ModelTestUtils.assertParameterNames( methodtest_title42, new String[] {"self"} );
			}
			//Function test:test_splitlines
			{
			IMethod methodtest_splitlines43;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_splitlines43 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_splitlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitlines43, new String[] {"self"} );
			}
			//Function test:test_startswith
			{
			IMethod methodtest_startswith44;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_startswith44 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_startswith", 1 );
				ModelTestUtils.assertParameterNames( methodtest_startswith44, new String[] {"self"} );
			}
			//Function test:test_endswith
			{
			IMethod methodtest_endswith45;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_endswith45 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_endswith", 1 );
				ModelTestUtils.assertParameterNames( methodtest_endswith45, new String[] {"self"} );
			}
			//Function test:test___contains__
			{
			IMethod methodtest___contains__46;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest___contains__46 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test___contains__", 1 );
				ModelTestUtils.assertParameterNames( methodtest___contains__46, new String[] {"self"} );
			}
			//Function test:test_subscript
			{
			IMethod methodtest_subscript47;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_subscript47 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_subscript", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subscript47, new String[] {"self"} );
			}
			//Function test:test_slice
			{
			IMethod methodtest_slice48;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_slice48 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_slice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_slice48, new String[] {"self"} );
			}
			//Function test:test_mul
			{
			IMethod methodtest_mul49;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_mul49 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_mul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mul49, new String[] {"self"} );
			}
			//Function test:test_join
			{
			IMethod methodtest_join50;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_join50 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_join", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join50, new String[] {"self"} );
			}
			//Function test:test_formatting
			{
			IMethod methodtest_formatting51;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_formatting51 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_formatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_formatting51, new String[] {"self"} );
			}
			//Function test:test_floatformatting
			{
			IMethod methodtest_floatformatting52;
				IModelElement[] classMixinStrUnicodeUserStringTest34Childs = classMixinStrUnicodeUserStringTest34.getChildren();
				methodtest_floatformatting52 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest34Childs, "test_floatformatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floatformatting52, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrStringUserStringTest53;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrStringUserStringTest53 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrStringUserStringTest" );
			//Function test:test_maketrans
			{
			IMethod methodtest_maketrans54;
				IModelElement[] classMixinStrStringUserStringTest53Childs = classMixinStrStringUserStringTest53.getChildren();
				methodtest_maketrans54 = ModelTestUtils.getAssertMethod( classMixinStrStringUserStringTest53Childs, "test_maketrans", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maketrans54, new String[] {"self"} );
			}
			//Function test:test_translate
			{
			IMethod methodtest_translate55;
				IModelElement[] classMixinStrStringUserStringTest53Childs = classMixinStrStringUserStringTest53.getChildren();
				methodtest_translate55 = ModelTestUtils.getAssertMethod( classMixinStrStringUserStringTest53Childs, "test_translate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_translate55, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrUserStringTest56;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrUserStringTest56 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrUserStringTest" );
			//Function test:test_encoding_decoding
			{
			IMethod methodtest_encoding_decoding57;
				IModelElement[] classMixinStrUserStringTest56Childs = classMixinStrUserStringTest56.getChildren();
				methodtest_encoding_decoding57 = ModelTestUtils.getAssertMethod( classMixinStrUserStringTest56Childs, "test_encoding_decoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encoding_decoding57, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrUnicodeTest58;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrUnicodeTest58 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrUnicodeTest" );
			//Function test:test_bug1001011
			{
			IMethod methodtest_bug100101159;
				IModelElement[] classMixinStrUnicodeTest58Childs = classMixinStrUnicodeTest58.getChildren();
				methodtest_bug100101159 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeTest58Childs, "test_bug1001011", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug100101159, new String[] {"self"} );
				//Class test
				{
				IType classsubclass60;
					IModelElement[] methodtest_bug100101159Childs = methodtest_bug100101159.getChildren();
					classsubclass60 = ModelTestUtils.getAssertClass( methodtest_bug100101159Childs, "subclass" );
				}
			}
		}

	}
	public void testModelGen251( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_charmapcodec.py"));

		assertNotNull("Module test_charmapcodec.py not found", module);
		assertEquals("test_charmapcodec.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "codecname");
		}
		//Class test
		{
		IType classCharmapCodecTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classCharmapCodecTest0 = ModelTestUtils.getAssertClass( moduleChilds, "CharmapCodecTest" );
			//Function test:test_constructorx
			{
			IMethod methodtest_constructorx1;
				IModelElement[] classCharmapCodecTest0Childs = classCharmapCodecTest0.getChildren();
				methodtest_constructorx1 = ModelTestUtils.getAssertMethod( classCharmapCodecTest0Childs, "test_constructorx", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructorx1, new String[] {"self"} );
			}
			//Function test:test_encodex
			{
			IMethod methodtest_encodex2;
				IModelElement[] classCharmapCodecTest0Childs = classCharmapCodecTest0.getChildren();
				methodtest_encodex2 = ModelTestUtils.getAssertMethod( classCharmapCodecTest0Childs, "test_encodex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encodex2, new String[] {"self"} );
			}
			//Function test:test_constructory
			{
			IMethod methodtest_constructory3;
				IModelElement[] classCharmapCodecTest0Childs = classCharmapCodecTest0.getChildren();
				methodtest_constructory3 = ModelTestUtils.getAssertMethod( classCharmapCodecTest0Childs, "test_constructory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructory3, new String[] {"self"} );
			}
			//Function test:test_maptoundefined
			{
			IMethod methodtest_maptoundefined4;
				IModelElement[] classCharmapCodecTest0Childs = classCharmapCodecTest0.getChildren();
				methodtest_maptoundefined4 = ModelTestUtils.getAssertMethod( classCharmapCodecTest0Childs, "test_maptoundefined", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maptoundefined4, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen252( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_timeout.py"));

		assertNotNull("Module test_timeout.py not found", module);
		assertEquals("test_timeout.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "skip_expected");
		}
		//Class test
		{
		IType classCreationTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classCreationTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "CreationTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:testObjectCreation
			{
			IMethod methodtestObjectCreation3;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestObjectCreation3 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testObjectCreation", 1 );
				ModelTestUtils.assertParameterNames( methodtestObjectCreation3, new String[] {"self"} );
			}
			//Function test:testFloatReturnValue
			{
			IMethod methodtestFloatReturnValue4;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestFloatReturnValue4 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testFloatReturnValue", 1 );
				ModelTestUtils.assertParameterNames( methodtestFloatReturnValue4, new String[] {"self"} );
			}
			//Function test:testReturnType
			{
			IMethod methodtestReturnType5;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestReturnType5 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testReturnType", 1 );
				ModelTestUtils.assertParameterNames( methodtestReturnType5, new String[] {"self"} );
			}
			//Function test:testTypeCheck
			{
			IMethod methodtestTypeCheck6;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestTypeCheck6 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testTypeCheck", 1 );
				ModelTestUtils.assertParameterNames( methodtestTypeCheck6, new String[] {"self"} );
			}
			//Function test:testRangeCheck
			{
			IMethod methodtestRangeCheck7;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestRangeCheck7 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testRangeCheck", 1 );
				ModelTestUtils.assertParameterNames( methodtestRangeCheck7, new String[] {"self"} );
			}
			//Function test:testTimeoutThenBlocking
			{
			IMethod methodtestTimeoutThenBlocking8;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestTimeoutThenBlocking8 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testTimeoutThenBlocking", 1 );
				ModelTestUtils.assertParameterNames( methodtestTimeoutThenBlocking8, new String[] {"self"} );
			}
			//Function test:testBlockingThenTimeout
			{
			IMethod methodtestBlockingThenTimeout9;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestBlockingThenTimeout9 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testBlockingThenTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestBlockingThenTimeout9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTimeoutTestCase10;
			IModelElement[] moduleChilds = module.getChildren();
			classTimeoutTestCase10 = ModelTestUtils.getAssertClass( moduleChilds, "TimeoutTestCase" );
			{
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeoutTestCase10Childs, "fuzz");
			}
			//Function test:setUp
			{
			IMethod methodsetUp11;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodsetUp11 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp11, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown12;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtearDown12 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown12, new String[] {"self"} );
			}
			//Function test:testConnectTimeout
			{
			IMethod methodtestConnectTimeout13;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestConnectTimeout13 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testConnectTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestConnectTimeout13, new String[] {"self"} );
			}
			//Function test:testRecvTimeout
			{
			IMethod methodtestRecvTimeout14;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestRecvTimeout14 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testRecvTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvTimeout14, new String[] {"self"} );
			}
			//Function test:testAcceptTimeout
			{
			IMethod methodtestAcceptTimeout15;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestAcceptTimeout15 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testAcceptTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestAcceptTimeout15, new String[] {"self"} );
			}
			//Function test:testRecvfromTimeout
			{
			IMethod methodtestRecvfromTimeout16;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestRecvfromTimeout16 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testRecvfromTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvfromTimeout16, new String[] {"self"} );
			}
			//Function test:testSend
			{
			IMethod methodtestSend17;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestSend17 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testSend", 1 );
				ModelTestUtils.assertParameterNames( methodtestSend17, new String[] {"self"} );
			}
			//Function test:testSendto
			{
			IMethod methodtestSendto18;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestSendto18 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testSendto", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendto18, new String[] {"self"} );
			}
			//Function test:testSendall
			{
			IMethod methodtestSendall19;
				IModelElement[] classTimeoutTestCase10Childs = classTimeoutTestCase10.getChildren();
				methodtestSendall19 = ModelTestUtils.getAssertMethod( classTimeoutTestCase10Childs, "testSendall", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendall19, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen253( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_xmllib.py"));

		assertNotNull("Module test_xmllib.py not found", module);
		assertEquals("test_xmllib.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testdoc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nsdoc");
		}
		//Class test
		{
		IType classXMLParserTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classXMLParserTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "XMLParserTestCase" );
			//Function test:test_simple
			{
			IMethod methodtest_simple1;
				IModelElement[] classXMLParserTestCase0Childs = classXMLParserTestCase0.getChildren();
				methodtest_simple1 = ModelTestUtils.getAssertMethod( classXMLParserTestCase0Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple1, new String[] {"self"} );
			}
			//Function test:test_default_namespace
			{
			IMethod methodtest_default_namespace2;
				IModelElement[] classXMLParserTestCase0Childs = classXMLParserTestCase0.getChildren();
				methodtest_default_namespace2 = ModelTestUtils.getAssertMethod( classXMLParserTestCase0Childs, "test_default_namespace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_namespace2, new String[] {"self"} );
				//Class test
				{
				IType classH3;
					IModelElement[] methodtest_default_namespace2Childs = methodtest_default_namespace2.getChildren();
					classH3 = ModelTestUtils.getAssertClass( methodtest_default_namespace2Childs, "H" );
					//Function test:unknown_starttag
					{
					IMethod methodunknown_starttag4;
						IModelElement[] classH3Childs = classH3.getChildren();
						methodunknown_starttag4 = ModelTestUtils.getAssertMethod( classH3Childs, "unknown_starttag", 3 );
						ModelTestUtils.assertParameterNames( methodunknown_starttag4, new String[] {"self", "name", "attr"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen254( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_call.py"));

		assertNotNull("Module test_call.py not found", module);
		assertEquals("test_call.py", module.getElementName());
		
		//Class test
		{
		IType classCFunctionCalls0;
			IModelElement[] moduleChilds = module.getChildren();
			classCFunctionCalls0 = ModelTestUtils.getAssertClass( moduleChilds, "CFunctionCalls" );
			//Function test:test_varargs0
			{
			IMethod methodtest_varargs01;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs01 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs0", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs01, new String[] {"self"} );
			}
			//Function test:test_varargs1
			{
			IMethod methodtest_varargs12;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs12 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs12, new String[] {"self"} );
			}
			//Function test:test_varargs2
			{
			IMethod methodtest_varargs23;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs23 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs23, new String[] {"self"} );
			}
			//Function test:test_varargs0_ext
			{
			IMethod methodtest_varargs0_ext4;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs0_ext4 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs0_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs0_ext4, new String[] {"self"} );
			}
			//Function test:test_varargs1_ext
			{
			IMethod methodtest_varargs1_ext5;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs1_ext5 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs1_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs1_ext5, new String[] {"self"} );
			}
			//Function test:test_varargs2_ext
			{
			IMethod methodtest_varargs2_ext6;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs2_ext6 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs2_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs2_ext6, new String[] {"self"} );
			}
			//Function test:test_varargs0_kw
			{
			IMethod methodtest_varargs0_kw7;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs0_kw7 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs0_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs0_kw7, new String[] {"self"} );
			}
			//Function test:test_varargs1_kw
			{
			IMethod methodtest_varargs1_kw8;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs1_kw8 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs1_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs1_kw8, new String[] {"self"} );
			}
			//Function test:test_varargs2_kw
			{
			IMethod methodtest_varargs2_kw9;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_varargs2_kw9 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_varargs2_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_varargs2_kw9, new String[] {"self"} );
			}
			//Function test:test_oldargs0_0
			{
			IMethod methodtest_oldargs0_010;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_010 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_0", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_010, new String[] {"self"} );
			}
			//Function test:test_oldargs0_1
			{
			IMethod methodtest_oldargs0_111;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_111 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_111, new String[] {"self"} );
			}
			//Function test:test_oldargs0_2
			{
			IMethod methodtest_oldargs0_212;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_212 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_212, new String[] {"self"} );
			}
			//Function test:test_oldargs0_0_ext
			{
			IMethod methodtest_oldargs0_0_ext13;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_0_ext13 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_0_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_0_ext13, new String[] {"self"} );
			}
			//Function test:test_oldargs0_1_ext
			{
			IMethod methodtest_oldargs0_1_ext14;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_1_ext14 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_1_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_1_ext14, new String[] {"self"} );
			}
			//Function test:test_oldargs0_2_ext
			{
			IMethod methodtest_oldargs0_2_ext15;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_2_ext15 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_2_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_2_ext15, new String[] {"self"} );
			}
			//Function test:test_oldargs0_0_kw
			{
			IMethod methodtest_oldargs0_0_kw16;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_0_kw16 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_0_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_0_kw16, new String[] {"self"} );
			}
			//Function test:test_oldargs0_1_kw
			{
			IMethod methodtest_oldargs0_1_kw17;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_1_kw17 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_1_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_1_kw17, new String[] {"self"} );
			}
			//Function test:test_oldargs0_2_kw
			{
			IMethod methodtest_oldargs0_2_kw18;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs0_2_kw18 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs0_2_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs0_2_kw18, new String[] {"self"} );
			}
			//Function test:test_oldargs1_0
			{
			IMethod methodtest_oldargs1_019;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_019 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_0", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_019, new String[] {"self"} );
			}
			//Function test:test_oldargs1_1
			{
			IMethod methodtest_oldargs1_120;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_120 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_120, new String[] {"self"} );
			}
			//Function test:test_oldargs1_2
			{
			IMethod methodtest_oldargs1_221;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_221 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_221, new String[] {"self"} );
			}
			//Function test:test_oldargs1_0_ext
			{
			IMethod methodtest_oldargs1_0_ext22;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_0_ext22 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_0_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_0_ext22, new String[] {"self"} );
			}
			//Function test:test_oldargs1_1_ext
			{
			IMethod methodtest_oldargs1_1_ext23;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_1_ext23 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_1_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_1_ext23, new String[] {"self"} );
			}
			//Function test:test_oldargs1_2_ext
			{
			IMethod methodtest_oldargs1_2_ext24;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_2_ext24 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_2_ext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_2_ext24, new String[] {"self"} );
			}
			//Function test:test_oldargs1_0_kw
			{
			IMethod methodtest_oldargs1_0_kw25;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_0_kw25 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_0_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_0_kw25, new String[] {"self"} );
			}
			//Function test:test_oldargs1_1_kw
			{
			IMethod methodtest_oldargs1_1_kw26;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_1_kw26 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_1_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_1_kw26, new String[] {"self"} );
			}
			//Function test:test_oldargs1_2_kw
			{
			IMethod methodtest_oldargs1_2_kw27;
				IModelElement[] classCFunctionCalls0Childs = classCFunctionCalls0.getChildren();
				methodtest_oldargs1_2_kw27 = ModelTestUtils.getAssertMethod( classCFunctionCalls0Childs, "test_oldargs1_2_kw", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oldargs1_2_kw27, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen255( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_array.py"));

		assertNotNull("Module test_array.py not found", module);
		assertEquals("test_array.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "typecodes");
		}
		//Class test
		{
		IType classBadConstructorTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classBadConstructorTest0 = ModelTestUtils.getAssertClass( moduleChilds, "BadConstructorTest" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor1;
				IModelElement[] classBadConstructorTest0Childs = classBadConstructorTest0.getChildren();
				methodtest_constructor1 = ModelTestUtils.getAssertMethod( classBadConstructorTest0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBaseTest2;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseTest2 = ModelTestUtils.getAssertClass( moduleChilds, "BaseTest" );
			//Function test:assertEntryEqual
			{
			IMethod methodassertEntryEqual3;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodassertEntryEqual3 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "assertEntryEqual", 3 );
				ModelTestUtils.assertParameterNames( methodassertEntryEqual3, new String[] {"self", "entry1", "entry2"} );
			}
			//Function test:badtypecode
			{
			IMethod methodbadtypecode4;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodbadtypecode4 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "badtypecode", 1 );
				ModelTestUtils.assertParameterNames( methodbadtypecode4, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor5;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_constructor5 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor5, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len6;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_len6 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len6, new String[] {"self"} );
			}
			//Function test:test_buffer_info
			{
			IMethod methodtest_buffer_info7;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_buffer_info7 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_buffer_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_buffer_info7, new String[] {"self"} );
			}
			//Function test:test_byteswap
			{
			IMethod methodtest_byteswap8;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_byteswap8 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_byteswap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_byteswap8, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy9;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_copy9 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy9, new String[] {"self"} );
			}
			//Function test:test_insert
			{
			IMethod methodtest_insert10;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_insert10 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_insert", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insert10, new String[] {"self"} );
			}
			//Function test:test_tofromfile
			{
			IMethod methodtest_tofromfile11;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_tofromfile11 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_tofromfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tofromfile11, new String[] {"self"} );
			}
			//Function test:test_tofromlist
			{
			IMethod methodtest_tofromlist12;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_tofromlist12 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_tofromlist", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tofromlist12, new String[] {"self"} );
			}
			//Function test:test_tofromstring
			{
			IMethod methodtest_tofromstring13;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_tofromstring13 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_tofromstring", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tofromstring13, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr14;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_repr14 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr14, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str15;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_str15 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str15, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp16;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_cmp16 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp16, new String[] {"self"} );
			}
			//Function test:test_add
			{
			IMethod methodtest_add17;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_add17 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_add", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add17, new String[] {"self"} );
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd18;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_iadd18 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd18, new String[] {"self"} );
			}
			//Function test:test_mul
			{
			IMethod methodtest_mul19;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_mul19 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_mul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mul19, new String[] {"self"} );
			}
			//Function test:test_imul
			{
			IMethod methodtest_imul20;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_imul20 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_imul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imul20, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem21;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_getitem21 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem21, new String[] {"self"} );
			}
			//Function test:test_setitem
			{
			IMethod methodtest_setitem22;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_setitem22 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_setitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setitem22, new String[] {"self"} );
			}
			//Function test:test_delitem
			{
			IMethod methodtest_delitem23;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_delitem23 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delitem23, new String[] {"self"} );
			}
			//Function test:test_getslice
			{
			IMethod methodtest_getslice24;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_getslice24 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_getslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getslice24, new String[] {"self"} );
			}
			//Function test:test_setslice
			{
			IMethod methodtest_setslice25;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_setslice25 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_setslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setslice25, new String[] {"self"} );
			}
			//Function test:test_index
			{
			IMethod methodtest_index26;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_index26 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_index", 1 );
				ModelTestUtils.assertParameterNames( methodtest_index26, new String[] {"self"} );
			}
			//Function test:test_count
			{
			IMethod methodtest_count27;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_count27 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_count", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count27, new String[] {"self"} );
			}
			//Function test:test_remove
			{
			IMethod methodtest_remove28;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_remove28 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_remove", 1 );
				ModelTestUtils.assertParameterNames( methodtest_remove28, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop29;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_pop29 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop29, new String[] {"self"} );
			}
			//Function test:test_reverse
			{
			IMethod methodtest_reverse30;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_reverse30 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_reverse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse30, new String[] {"self"} );
			}
			//Function test:test_extend
			{
			IMethod methodtest_extend31;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_extend31 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_extend", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extend31, new String[] {"self"} );
			}
			//Function test:test_constructor_with_iterable_argument
			{
			IMethod methodtest_constructor_with_iterable_argument32;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_constructor_with_iterable_argument32 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_constructor_with_iterable_argument", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor_with_iterable_argument32, new String[] {"self"} );
				//Class test
				{
				IType classA33;
					IModelElement[] methodtest_constructor_with_iterable_argument32Childs = methodtest_constructor_with_iterable_argument32.getChildren();
					classA33 = ModelTestUtils.getAssertClass( methodtest_constructor_with_iterable_argument32Childs, "A" );
					//Function test:__iter__
					{
					IMethod method__iter__34;
						IModelElement[] classA33Childs = classA33.getChildren();
						method__iter__34 = ModelTestUtils.getAssertMethod( classA33Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__34, new String[] {"self"} );
					}
				}
				//Function test:B
				{
				IMethod methodB35;
					IModelElement[] methodtest_constructor_with_iterable_argument32Childs = methodtest_constructor_with_iterable_argument32.getChildren();
					methodB35 = ModelTestUtils.getAssertMethod( methodtest_constructor_with_iterable_argument32Childs, "B", 0 );
				}
			}
			//Function test:test_coveritertraverse
			{
			IMethod methodtest_coveritertraverse36;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_coveritertraverse36 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_coveritertraverse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coveritertraverse36, new String[] {"self"} );
			}
			//Function test:test_buffer
			{
			IMethod methodtest_buffer37;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_buffer37 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_buffer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_buffer37, new String[] {"self"} );
			}
			//Function test:test_weakref
			{
			IMethod methodtest_weakref38;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_weakref38 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref38, new String[] {"self"} );
			}
			//Function test:test_bug_782369
			{
			IMethod methodtest_bug_78236939;
				IModelElement[] classBaseTest2Childs = classBaseTest2.getChildren();
				methodtest_bug_78236939 = ModelTestUtils.getAssertMethod( classBaseTest2Childs, "test_bug_782369", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_78236939, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStringTest40;
			IModelElement[] moduleChilds = module.getChildren();
			classStringTest40 = ModelTestUtils.getAssertClass( moduleChilds, "StringTest" );
			//Function test:test_setitem
			{
			IMethod methodtest_setitem41;
				IModelElement[] classStringTest40Childs = classStringTest40.getChildren();
				methodtest_setitem41 = ModelTestUtils.getAssertMethod( classStringTest40Childs, "test_setitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setitem41, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCharacterTest42;
			IModelElement[] moduleChilds = module.getChildren();
			classCharacterTest42 = ModelTestUtils.getAssertClass( moduleChilds, "CharacterTest" );
			{
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCharacterTest42Childs, "typecode");
			}
			{
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCharacterTest42Childs, "example");
			}
			{
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCharacterTest42Childs, "smallerexample");
			}
			{
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCharacterTest42Childs, "biggerexample");
			}
			{
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCharacterTest42Childs, "outside");
			}
			{
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCharacterTest42Childs, "minitemsize");
			}
			//Function test:test_subbclassing
			{
			IMethod methodtest_subbclassing43;
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				methodtest_subbclassing43 = ModelTestUtils.getAssertMethod( classCharacterTest42Childs, "test_subbclassing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subbclassing43, new String[] {"self"} );
				//Class test
				{
				IType classEditableString44;
					IModelElement[] methodtest_subbclassing43Childs = methodtest_subbclassing43.getChildren();
					classEditableString44 = ModelTestUtils.getAssertClass( methodtest_subbclassing43Childs, "EditableString" );
					//Function test:__new__
					{
					IMethod method__new__45;
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						method__new__45 = ModelTestUtils.getAssertMethod( classEditableString44Childs, "__new__", 4 );
						ModelTestUtils.assertParameterNames( method__new__45, new String[] {"cls", "s", "args", "kwargs"} );
					}
					//Function test:__init__
					{
					IMethod method__init__46;
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						method__init__46 = ModelTestUtils.getAssertMethod( classEditableString44Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__46, new String[] {"self", "s", "color"} );
					}
					//Function test:strip
					{
					IMethod methodstrip47;
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						methodstrip47 = ModelTestUtils.getAssertMethod( classEditableString44Childs, "strip", 1 );
						ModelTestUtils.assertParameterNames( methodstrip47, new String[] {"self"} );
					}
					//Function test:__repr__
					{
					IMethod method__repr__48;
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						method__repr__48 = ModelTestUtils.getAssertMethod( classEditableString44Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__48, new String[] {"self"} );
					}
				}
			}
			//Function test:test_nounicode
			{
			IMethod methodtest_nounicode49;
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				methodtest_nounicode49 = ModelTestUtils.getAssertMethod( classCharacterTest42Childs, "test_nounicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nounicode49, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnicodeTest50;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeTest50 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeTest" );
			{
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest50Childs, "typecode");
			}
			{
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest50Childs, "example");
			}
			{
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest50Childs, "smallerexample");
			}
			{
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest50Childs, "biggerexample");
			}
			{
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest50Childs, "outside");
			}
			{
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest50Childs, "minitemsize");
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode51;
				IModelElement[] classUnicodeTest50Childs = classUnicodeTest50.getChildren();
				methodtest_unicode51 = ModelTestUtils.getAssertMethod( classUnicodeTest50Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode51, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNumberTest52;
			IModelElement[] moduleChilds = module.getChildren();
			classNumberTest52 = ModelTestUtils.getAssertClass( moduleChilds, "NumberTest" );
			//Function test:test_extslice
			{
			IMethod methodtest_extslice53;
				IModelElement[] classNumberTest52Childs = classNumberTest52.getChildren();
				methodtest_extslice53 = ModelTestUtils.getAssertMethod( classNumberTest52Childs, "test_extslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extslice53, new String[] {"self"} );
			}
			//Function test:test_delslice
			{
			IMethod methodtest_delslice54;
				IModelElement[] classNumberTest52Childs = classNumberTest52.getChildren();
				methodtest_delslice54 = ModelTestUtils.getAssertMethod( classNumberTest52Childs, "test_delslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delslice54, new String[] {"self"} );
			}
			//Function test:test_assignment
			{
			IMethod methodtest_assignment55;
				IModelElement[] classNumberTest52Childs = classNumberTest52.getChildren();
				methodtest_assignment55 = ModelTestUtils.getAssertMethod( classNumberTest52Childs, "test_assignment", 1 );
				ModelTestUtils.assertParameterNames( methodtest_assignment55, new String[] {"self"} );
			}
			//Function test:test_iterationcontains
			{
			IMethod methodtest_iterationcontains56;
				IModelElement[] classNumberTest52Childs = classNumberTest52.getChildren();
				methodtest_iterationcontains56 = ModelTestUtils.getAssertMethod( classNumberTest52Childs, "test_iterationcontains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iterationcontains56, new String[] {"self"} );
			}
			//Function test:check_overflow
			{
			IMethod methodcheck_overflow57;
				IModelElement[] classNumberTest52Childs = classNumberTest52.getChildren();
				methodcheck_overflow57 = ModelTestUtils.getAssertMethod( classNumberTest52Childs, "check_overflow", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_overflow57, new String[] {"self", "lower", "upper"} );
			}
			//Function test:test_subclassing
			{
			IMethod methodtest_subclassing58;
				IModelElement[] classNumberTest52Childs = classNumberTest52.getChildren();
				methodtest_subclassing58 = ModelTestUtils.getAssertMethod( classNumberTest52Childs, "test_subclassing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclassing58, new String[] {"self"} );
				//Class test
				{
				IType classExaggeratingArray59;
					IModelElement[] methodtest_subclassing58Childs = methodtest_subclassing58.getChildren();
					classExaggeratingArray59 = ModelTestUtils.getAssertClass( methodtest_subclassing58Childs, "ExaggeratingArray" );
					//Function test:__new__
					{
					IMethod method__new__60;
						IModelElement[] classExaggeratingArray59Childs = classExaggeratingArray59.getChildren();
						method__new__60 = ModelTestUtils.getAssertMethod( classExaggeratingArray59Childs, "__new__", 4 );
						ModelTestUtils.assertParameterNames( method__new__60, new String[] {"cls", "typecode", "data", "offset"} );
					}
					//Function test:__init__
					{
					IMethod method__init__61;
						IModelElement[] classExaggeratingArray59Childs = classExaggeratingArray59.getChildren();
						method__init__61 = ModelTestUtils.getAssertMethod( classExaggeratingArray59Childs, "__init__", 4 );
						ModelTestUtils.assertParameterNames( method__init__61, new String[] {"self", "typecode", "data", "offset"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__62;
						IModelElement[] classExaggeratingArray59Childs = classExaggeratingArray59.getChildren();
						method__getitem__62 = ModelTestUtils.getAssertMethod( classExaggeratingArray59Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__62, new String[] {"self", "i"} );
					}
				}
			}
		}
		//Class test
		{
		IType classSignedNumberTest63;
			IModelElement[] moduleChilds = module.getChildren();
			classSignedNumberTest63 = ModelTestUtils.getAssertClass( moduleChilds, "SignedNumberTest" );
			{
				IModelElement[] classSignedNumberTest63Childs = classSignedNumberTest63.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest63Childs, "example");
			}
			{
				IModelElement[] classSignedNumberTest63Childs = classSignedNumberTest63.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest63Childs, "smallerexample");
			}
			{
				IModelElement[] classSignedNumberTest63Childs = classSignedNumberTest63.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest63Childs, "biggerexample");
			}
			{
				IModelElement[] classSignedNumberTest63Childs = classSignedNumberTest63.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest63Childs, "outside");
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow64;
				IModelElement[] classSignedNumberTest63Childs = classSignedNumberTest63.getChildren();
				methodtest_overflow64 = ModelTestUtils.getAssertMethod( classSignedNumberTest63Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow64, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnsignedNumberTest65;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedNumberTest65 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedNumberTest" );
			{
				IModelElement[] classUnsignedNumberTest65Childs = classUnsignedNumberTest65.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest65Childs, "example");
			}
			{
				IModelElement[] classUnsignedNumberTest65Childs = classUnsignedNumberTest65.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest65Childs, "smallerexample");
			}
			{
				IModelElement[] classUnsignedNumberTest65Childs = classUnsignedNumberTest65.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest65Childs, "biggerexample");
			}
			{
				IModelElement[] classUnsignedNumberTest65Childs = classUnsignedNumberTest65.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest65Childs, "outside");
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow66;
				IModelElement[] classUnsignedNumberTest65Childs = classUnsignedNumberTest65.getChildren();
				methodtest_overflow66 = ModelTestUtils.getAssertMethod( classUnsignedNumberTest65Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow66, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classByteTest67;
			IModelElement[] moduleChilds = module.getChildren();
			classByteTest67 = ModelTestUtils.getAssertClass( moduleChilds, "ByteTest" );
			{
				IModelElement[] classByteTest67Childs = classByteTest67.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classByteTest67Childs, "typecode");
			}
			{
				IModelElement[] classByteTest67Childs = classByteTest67.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classByteTest67Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedByteTest68;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedByteTest68 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedByteTest" );
			{
				IModelElement[] classUnsignedByteTest68Childs = classUnsignedByteTest68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedByteTest68Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedByteTest68Childs = classUnsignedByteTest68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedByteTest68Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classShortTest69;
			IModelElement[] moduleChilds = module.getChildren();
			classShortTest69 = ModelTestUtils.getAssertClass( moduleChilds, "ShortTest" );
			{
				IModelElement[] classShortTest69Childs = classShortTest69.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classShortTest69Childs, "typecode");
			}
			{
				IModelElement[] classShortTest69Childs = classShortTest69.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classShortTest69Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedShortTest70;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedShortTest70 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedShortTest" );
			{
				IModelElement[] classUnsignedShortTest70Childs = classUnsignedShortTest70.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedShortTest70Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedShortTest70Childs = classUnsignedShortTest70.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedShortTest70Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classIntTest71;
			IModelElement[] moduleChilds = module.getChildren();
			classIntTest71 = ModelTestUtils.getAssertClass( moduleChilds, "IntTest" );
			{
				IModelElement[] classIntTest71Childs = classIntTest71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTest71Childs, "typecode");
			}
			{
				IModelElement[] classIntTest71Childs = classIntTest71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTest71Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedIntTest72;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedIntTest72 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedIntTest" );
			{
				IModelElement[] classUnsignedIntTest72Childs = classUnsignedIntTest72.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedIntTest72Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedIntTest72Childs = classUnsignedIntTest72.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedIntTest72Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classLongTest73;
			IModelElement[] moduleChilds = module.getChildren();
			classLongTest73 = ModelTestUtils.getAssertClass( moduleChilds, "LongTest" );
			{
				IModelElement[] classLongTest73Childs = classLongTest73.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongTest73Childs, "typecode");
			}
			{
				IModelElement[] classLongTest73Childs = classLongTest73.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongTest73Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedLongTest74;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedLongTest74 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedLongTest" );
			{
				IModelElement[] classUnsignedLongTest74Childs = classUnsignedLongTest74.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedLongTest74Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedLongTest74Childs = classUnsignedLongTest74.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedLongTest74Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classFPTest75;
			IModelElement[] moduleChilds = module.getChildren();
			classFPTest75 = ModelTestUtils.getAssertClass( moduleChilds, "FPTest" );
			{
				IModelElement[] classFPTest75Childs = classFPTest75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest75Childs, "example");
			}
			{
				IModelElement[] classFPTest75Childs = classFPTest75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest75Childs, "smallerexample");
			}
			{
				IModelElement[] classFPTest75Childs = classFPTest75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest75Childs, "biggerexample");
			}
			{
				IModelElement[] classFPTest75Childs = classFPTest75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest75Childs, "outside");
			}
			//Function test:assertEntryEqual
			{
			IMethod methodassertEntryEqual76;
				IModelElement[] classFPTest75Childs = classFPTest75.getChildren();
				methodassertEntryEqual76 = ModelTestUtils.getAssertMethod( classFPTest75Childs, "assertEntryEqual", 3 );
				ModelTestUtils.assertParameterNames( methodassertEntryEqual76, new String[] {"self", "entry1", "entry2"} );
			}
			//Function test:test_byteswap
			{
			IMethod methodtest_byteswap77;
				IModelElement[] classFPTest75Childs = classFPTest75.getChildren();
				methodtest_byteswap77 = ModelTestUtils.getAssertMethod( classFPTest75Childs, "test_byteswap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_byteswap77, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFloatTest78;
			IModelElement[] moduleChilds = module.getChildren();
			classFloatTest78 = ModelTestUtils.getAssertClass( moduleChilds, "FloatTest" );
			{
				IModelElement[] classFloatTest78Childs = classFloatTest78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFloatTest78Childs, "typecode");
			}
			{
				IModelElement[] classFloatTest78Childs = classFloatTest78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFloatTest78Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classDoubleTest79;
			IModelElement[] moduleChilds = module.getChildren();
			classDoubleTest79 = ModelTestUtils.getAssertClass( moduleChilds, "DoubleTest" );
			{
				IModelElement[] classDoubleTest79Childs = classDoubleTest79.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDoubleTest79Childs, "typecode");
			}
			{
				IModelElement[] classDoubleTest79Childs = classDoubleTest79.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDoubleTest79Childs, "minitemsize");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main80;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main80 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main80, new String[] {"verbose"} );
		}

	}
	public void testModelGen256( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_capi.py"));

		assertNotNull("Module test_capi.py not found", module);
		assertEquals("test_capi.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test");
		}
		//Function test:TestThreadState
		{
		IMethod methodTestThreadState0;
			IModelElement[] moduleChilds = module.getChildren();
			methodTestThreadState0 = ModelTestUtils.getAssertMethod( moduleChilds, "TestThreadState", 0 );
			//Function test:callback
			{
			IMethod methodcallback1;
				IModelElement[] methodTestThreadState0Childs = methodTestThreadState0.getChildren();
				methodcallback1 = ModelTestUtils.getAssertMethod( methodTestThreadState0Childs, "callback", 0 );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "have_thread_state");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "have_thread_state");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}

	}
	public void testModelGen257( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cpickle.py"));

		assertNotNull("Module test_cpickle.py not found", module);
		assertEquals("test_cpickle.py", module.getElementName());
		
		//Class test
		{
		IType classcPickleTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classcPickleTests0 = ModelTestUtils.getAssertClass( moduleChilds, "cPickleTests" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classcPickleTests0Childs = classcPickleTests0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classcPickleTests0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			{
				IModelElement[] classcPickleTests0Childs = classcPickleTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleTests0Childs, "error");
			}
			{
				IModelElement[] classcPickleTests0Childs = classcPickleTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleTests0Childs, "module");
			}
		}
		//Class test
		{
		IType classcPicklePicklerTests2;
			IModelElement[] moduleChilds = module.getChildren();
			classcPicklePicklerTests2 = ModelTestUtils.getAssertClass( moduleChilds, "cPicklePicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps3;
				IModelElement[] classcPicklePicklerTests2Childs = classcPicklePicklerTests2.getChildren();
				methoddumps3 = ModelTestUtils.getAssertMethod( classcPicklePicklerTests2Childs, "dumps", 3 );
				ModelTestUtils.assertParameterNames( methoddumps3, new String[] {"self", "arg", "proto"} );
			}
			//Function test:loads
			{
			IMethod methodloads4;
				IModelElement[] classcPicklePicklerTests2Childs = classcPicklePicklerTests2.getChildren();
				methodloads4 = ModelTestUtils.getAssertMethod( classcPicklePicklerTests2Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads4, new String[] {"self", "buf"} );
			}
			{
				IModelElement[] classcPicklePicklerTests2Childs = classcPicklePicklerTests2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPicklePicklerTests2Childs, "error");
			}
		}
		//Class test
		{
		IType classcPickleListPicklerTests5;
			IModelElement[] moduleChilds = module.getChildren();
			classcPickleListPicklerTests5 = ModelTestUtils.getAssertClass( moduleChilds, "cPickleListPicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps6;
				IModelElement[] classcPickleListPicklerTests5Childs = classcPickleListPicklerTests5.getChildren();
				methoddumps6 = ModelTestUtils.getAssertMethod( classcPickleListPicklerTests5Childs, "dumps", 3 );
				ModelTestUtils.assertParameterNames( methoddumps6, new String[] {"self", "arg", "proto"} );
			}
			//Function test:loads
			{
			IMethod methodloads7;
				IModelElement[] classcPickleListPicklerTests5Childs = classcPickleListPicklerTests5.getChildren();
				methodloads7 = ModelTestUtils.getAssertMethod( classcPickleListPicklerTests5Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads7, new String[] {"self", "args"} );
			}
			{
				IModelElement[] classcPickleListPicklerTests5Childs = classcPickleListPicklerTests5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleListPicklerTests5Childs, "error");
			}
		}
		//Class test
		{
		IType classcPickleFastPicklerTests8;
			IModelElement[] moduleChilds = module.getChildren();
			classcPickleFastPicklerTests8 = ModelTestUtils.getAssertClass( moduleChilds, "cPickleFastPicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps9;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methoddumps9 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "dumps", 3 );
				ModelTestUtils.assertParameterNames( methoddumps9, new String[] {"self", "arg", "proto"} );
			}
			//Function test:loads
			{
			IMethod methodloads10;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methodloads10 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads10, new String[] {"self", "args"} );
			}
			{
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleFastPicklerTests8Childs, "error");
			}
			//Function test:test_recursive_list
			{
			IMethod methodtest_recursive_list11;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methodtest_recursive_list11 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "test_recursive_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_list11, new String[] {"self"} );
			}
			//Function test:test_recursive_inst
			{
			IMethod methodtest_recursive_inst12;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methodtest_recursive_inst12 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "test_recursive_inst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_inst12, new String[] {"self"} );
			}
			//Function test:test_recursive_dict
			{
			IMethod methodtest_recursive_dict13;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methodtest_recursive_dict13 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "test_recursive_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_dict13, new String[] {"self"} );
			}
			//Function test:test_recursive_multi
			{
			IMethod methodtest_recursive_multi14;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methodtest_recursive_multi14 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "test_recursive_multi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_multi14, new String[] {"self"} );
			}
			//Function test:test_nonrecursive_deep
			{
			IMethod methodtest_nonrecursive_deep15;
				IModelElement[] classcPickleFastPicklerTests8Childs = classcPickleFastPicklerTests8.getChildren();
				methodtest_nonrecursive_deep15 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests8Childs, "test_nonrecursive_deep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonrecursive_deep15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen258( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_import.py"));

		assertNotNull("Module test_import.py not found", module);
		assertEquals("test_import.py", module.getElementName());
		
		//Function test:remove_files
		{
		IMethod methodremove_files0;
			IModelElement[] moduleChilds = module.getChildren();
			methodremove_files0 = ModelTestUtils.getAssertMethod( moduleChilds, "remove_files", 1 );
			ModelTestUtils.assertParameterNames( methodremove_files0, new String[] {"name"} );
		}
		//Function test:test_with_extension
		{
		IMethod methodtest_with_extension1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_with_extension1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_with_extension", 1 );
			ModelTestUtils.assertParameterNames( methodtest_with_extension1, new String[] {"ext"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "os");
		}
		//Function test:test_module_with_large_stack
		{
		IMethod methodtest_module_with_large_stack2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_module_with_large_stack2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_module_with_large_stack", 1 );
			ModelTestUtils.assertParameterNames( methodtest_module_with_large_stack2, new String[] {"module"} );
		}
		//Function test:test_failing_import_sticks
		{
		IMethod methodtest_failing_import_sticks3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_failing_import_sticks3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_failing_import_sticks", 0 );
		}
		//Function test:test_failing_reload
		{
		IMethod methodtest_failing_reload4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_failing_reload4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_failing_reload", 0 );
		}

	}
	public void testModelGen259( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test__locale.py"));

		assertNotNull("Module test__locale.py not found", module);
		assertEquals("test__locale.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "candidate_locales");
		}
		//Class test
		{
		IType class_LocaleTests0;
			IModelElement[] moduleChilds = module.getChildren();
			class_LocaleTests0 = ModelTestUtils.getAssertClass( moduleChilds, "_LocaleTests" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] class_LocaleTests0Childs = class_LocaleTests0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( class_LocaleTests0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] class_LocaleTests0Childs = class_LocaleTests0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( class_LocaleTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_lc_numeric
			{
			IMethod methodtest_lc_numeric3;
				IModelElement[] class_LocaleTests0Childs = class_LocaleTests0.getChildren();
				methodtest_lc_numeric3 = ModelTestUtils.getAssertMethod( class_LocaleTests0Childs, "test_lc_numeric", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lc_numeric3, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen260( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_threadedtempfile.py"));

		assertNotNull("Module test_threadedtempfile.py not found", module);
		assertEquals("test_threadedtempfile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NUM_THREADS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FILES_PER_THREAD");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "startEvent");
		}
		//Class test
		{
		IType classTempFileGreedy0;
			IModelElement[] moduleChilds = module.getChildren();
			classTempFileGreedy0 = ModelTestUtils.getAssertClass( moduleChilds, "TempFileGreedy" );
			{
				IModelElement[] classTempFileGreedy0Childs = classTempFileGreedy0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTempFileGreedy0Childs, "error_count");
			}
			{
				IModelElement[] classTempFileGreedy0Childs = classTempFileGreedy0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTempFileGreedy0Childs, "ok_count");
			}
			//Function test:run
			{
			IMethod methodrun1;
				IModelElement[] classTempFileGreedy0Childs = classTempFileGreedy0.getChildren();
				methodrun1 = ModelTestUtils.getAssertMethod( classTempFileGreedy0Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun1, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FILES_PER_THREAD");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NUM_THREADS");
		}

	}
	public void testModelGen261( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_socket.py"));

		assertNotNull("Module test_socket.py not found", module);
		assertEquals("test_socket.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PORT");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "HOST");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MSG");
		}
		//Class test
		{
		IType classSocketTCPTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketTCPTest0 = ModelTestUtils.getAssertClass( moduleChilds, "SocketTCPTest" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classSocketTCPTest0Childs = classSocketTCPTest0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classSocketTCPTest0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classSocketTCPTest0Childs = classSocketTCPTest0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classSocketTCPTest0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSocketUDPTest3;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketUDPTest3 = ModelTestUtils.getAssertClass( moduleChilds, "SocketUDPTest" );
			//Function test:setUp
			{
			IMethod methodsetUp4;
				IModelElement[] classSocketUDPTest3Childs = classSocketUDPTest3.getChildren();
				methodsetUp4 = ModelTestUtils.getAssertMethod( classSocketUDPTest3Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp4, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown5;
				IModelElement[] classSocketUDPTest3Childs = classSocketUDPTest3.getChildren();
				methodtearDown5 = ModelTestUtils.getAssertMethod( classSocketUDPTest3Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadableTest6;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadableTest6 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadableTest" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self"} );
			}
			//Function test:serverExplicitReady
			{
			IMethod methodserverExplicitReady8;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				methodserverExplicitReady8 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "serverExplicitReady", 1 );
				ModelTestUtils.assertParameterNames( methodserverExplicitReady8, new String[] {"self"} );
			}
			//Function test:_setUp
			{
			IMethod method_setUp9;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				method_setUp9 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "_setUp", 1 );
				ModelTestUtils.assertParameterNames( method_setUp9, new String[] {"self"} );
			}
			//Function test:_tearDown
			{
			IMethod method_tearDown10;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				method_tearDown10 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "_tearDown", 1 );
				ModelTestUtils.assertParameterNames( method_tearDown10, new String[] {"self"} );
			}
			//Function test:clientRun
			{
			IMethod methodclientRun11;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				methodclientRun11 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "clientRun", 2 );
				ModelTestUtils.assertParameterNames( methodclientRun11, new String[] {"self", "test_func"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp12;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				methodclientSetUp12 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp12, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown13;
				IModelElement[] classThreadableTest6Childs = classThreadableTest6.getChildren();
				methodclientTearDown13 = ModelTestUtils.getAssertMethod( classThreadableTest6Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadedTCPSocketTest14;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadedTCPSocketTest14 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadedTCPSocketTest" );
			//Function test:__init__
			{
			IMethod method__init__15;
				IModelElement[] classThreadedTCPSocketTest14Childs = classThreadedTCPSocketTest14.getChildren();
				method__init__15 = ModelTestUtils.getAssertMethod( classThreadedTCPSocketTest14Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__15, new String[] {"self", "methodName"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp16;
				IModelElement[] classThreadedTCPSocketTest14Childs = classThreadedTCPSocketTest14.getChildren();
				methodclientSetUp16 = ModelTestUtils.getAssertMethod( classThreadedTCPSocketTest14Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp16, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown17;
				IModelElement[] classThreadedTCPSocketTest14Childs = classThreadedTCPSocketTest14.getChildren();
				methodclientTearDown17 = ModelTestUtils.getAssertMethod( classThreadedTCPSocketTest14Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadedUDPSocketTest18;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadedUDPSocketTest18 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadedUDPSocketTest" );
			//Function test:__init__
			{
			IMethod method__init__19;
				IModelElement[] classThreadedUDPSocketTest18Childs = classThreadedUDPSocketTest18.getChildren();
				method__init__19 = ModelTestUtils.getAssertMethod( classThreadedUDPSocketTest18Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__19, new String[] {"self", "methodName"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp20;
				IModelElement[] classThreadedUDPSocketTest18Childs = classThreadedUDPSocketTest18.getChildren();
				methodclientSetUp20 = ModelTestUtils.getAssertMethod( classThreadedUDPSocketTest18Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp20, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSocketConnectedTest21;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketConnectedTest21 = ModelTestUtils.getAssertClass( moduleChilds, "SocketConnectedTest" );
			//Function test:__init__
			{
			IMethod method__init__22;
				IModelElement[] classSocketConnectedTest21Childs = classSocketConnectedTest21.getChildren();
				method__init__22 = ModelTestUtils.getAssertMethod( classSocketConnectedTest21Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__22, new String[] {"self", "methodName"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp23;
				IModelElement[] classSocketConnectedTest21Childs = classSocketConnectedTest21.getChildren();
				methodsetUp23 = ModelTestUtils.getAssertMethod( classSocketConnectedTest21Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp23, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown24;
				IModelElement[] classSocketConnectedTest21Childs = classSocketConnectedTest21.getChildren();
				methodtearDown24 = ModelTestUtils.getAssertMethod( classSocketConnectedTest21Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown24, new String[] {"self"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp25;
				IModelElement[] classSocketConnectedTest21Childs = classSocketConnectedTest21.getChildren();
				methodclientSetUp25 = ModelTestUtils.getAssertMethod( classSocketConnectedTest21Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp25, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown26;
				IModelElement[] classSocketConnectedTest21Childs = classSocketConnectedTest21.getChildren();
				methodclientTearDown26 = ModelTestUtils.getAssertMethod( classSocketConnectedTest21Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSocketPairTest27;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketPairTest27 = ModelTestUtils.getAssertClass( moduleChilds, "SocketPairTest" );
			//Function test:__init__
			{
			IMethod method__init__28;
				IModelElement[] classSocketPairTest27Childs = classSocketPairTest27.getChildren();
				method__init__28 = ModelTestUtils.getAssertMethod( classSocketPairTest27Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__28, new String[] {"self", "methodName"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp29;
				IModelElement[] classSocketPairTest27Childs = classSocketPairTest27.getChildren();
				methodsetUp29 = ModelTestUtils.getAssertMethod( classSocketPairTest27Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp29, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown30;
				IModelElement[] classSocketPairTest27Childs = classSocketPairTest27.getChildren();
				methodtearDown30 = ModelTestUtils.getAssertMethod( classSocketPairTest27Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown30, new String[] {"self"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp31;
				IModelElement[] classSocketPairTest27Childs = classSocketPairTest27.getChildren();
				methodclientSetUp31 = ModelTestUtils.getAssertMethod( classSocketPairTest27Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp31, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown32;
				IModelElement[] classSocketPairTest27Childs = classSocketPairTest27.getChildren();
				methodclientTearDown32 = ModelTestUtils.getAssertMethod( classSocketPairTest27Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown32, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classGeneralModuleTests33;
			IModelElement[] moduleChilds = module.getChildren();
			classGeneralModuleTests33 = ModelTestUtils.getAssertClass( moduleChilds, "GeneralModuleTests" );
			//Function test:test_weakref
			{
			IMethod methodtest_weakref34;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtest_weakref34 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref34, new String[] {"self"} );
			}
			//Function test:testSocketError
			{
			IMethod methodtestSocketError35;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestSocketError35 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testSocketError", 1 );
				ModelTestUtils.assertParameterNames( methodtestSocketError35, new String[] {"self"} );
				//Function test:raise_error
				{
				IMethod methodraise_error36;
					IModelElement[] methodtestSocketError35Childs = methodtestSocketError35.getChildren();
					methodraise_error36 = ModelTestUtils.getAssertMethod( methodtestSocketError35Childs, "raise_error", 2 );
					ModelTestUtils.assertParameterNames( methodraise_error36, new String[] {"args", "kwargs"} );
				}
				//Function test:raise_herror
				{
				IMethod methodraise_herror37;
					IModelElement[] methodtestSocketError35Childs = methodtestSocketError35.getChildren();
					methodraise_herror37 = ModelTestUtils.getAssertMethod( methodtestSocketError35Childs, "raise_herror", 2 );
					ModelTestUtils.assertParameterNames( methodraise_herror37, new String[] {"args", "kwargs"} );
				}
				//Function test:raise_gaierror
				{
				IMethod methodraise_gaierror38;
					IModelElement[] methodtestSocketError35Childs = methodtestSocketError35.getChildren();
					methodraise_gaierror38 = ModelTestUtils.getAssertMethod( methodtestSocketError35Childs, "raise_gaierror", 2 );
					ModelTestUtils.assertParameterNames( methodraise_gaierror38, new String[] {"args", "kwargs"} );
				}
			}
			//Function test:testCrucialConstants
			{
			IMethod methodtestCrucialConstants39;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestCrucialConstants39 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testCrucialConstants", 1 );
				ModelTestUtils.assertParameterNames( methodtestCrucialConstants39, new String[] {"self"} );
			}
			//Function test:testHostnameRes
			{
			IMethod methodtestHostnameRes40;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestHostnameRes40 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testHostnameRes", 1 );
				ModelTestUtils.assertParameterNames( methodtestHostnameRes40, new String[] {"self"} );
			}
			//Function test:testRefCountGetNameInfo
			{
			IMethod methodtestRefCountGetNameInfo41;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestRefCountGetNameInfo41 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testRefCountGetNameInfo", 1 );
				ModelTestUtils.assertParameterNames( methodtestRefCountGetNameInfo41, new String[] {"self"} );
			}
			//Function test:testInterpreterCrash
			{
			IMethod methodtestInterpreterCrash42;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestInterpreterCrash42 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testInterpreterCrash", 1 );
				ModelTestUtils.assertParameterNames( methodtestInterpreterCrash42, new String[] {"self"} );
			}
			//Function test:testNtoH
			{
			IMethod methodtestNtoH43;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestNtoH43 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testNtoH", 1 );
				ModelTestUtils.assertParameterNames( methodtestNtoH43, new String[] {"self"} );
			}
			//Function test:testGetServBy
			{
			IMethod methodtestGetServBy44;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestGetServBy44 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testGetServBy", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetServBy44, new String[] {"self"} );
			}
			//Function test:testDefaultTimeout
			{
			IMethod methodtestDefaultTimeout45;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestDefaultTimeout45 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testDefaultTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestDefaultTimeout45, new String[] {"self"} );
			}
			//Function test:testIPv4toString
			{
			IMethod methodtestIPv4toString46;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestIPv4toString46 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testIPv4toString", 1 );
				ModelTestUtils.assertParameterNames( methodtestIPv4toString46, new String[] {"self"} );
			}
			//Function test:testIPv6toString
			{
			IMethod methodtestIPv6toString47;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestIPv6toString47 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testIPv6toString", 1 );
				ModelTestUtils.assertParameterNames( methodtestIPv6toString47, new String[] {"self"} );
			}
			//Function test:testStringToIPv4
			{
			IMethod methodtestStringToIPv448;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestStringToIPv448 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testStringToIPv4", 1 );
				ModelTestUtils.assertParameterNames( methodtestStringToIPv448, new String[] {"self"} );
			}
			//Function test:testStringToIPv6
			{
			IMethod methodtestStringToIPv649;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestStringToIPv649 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testStringToIPv6", 1 );
				ModelTestUtils.assertParameterNames( methodtestStringToIPv649, new String[] {"self"} );
			}
			//Function test:testSockName
			{
			IMethod methodtestSockName50;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestSockName50 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testSockName", 1 );
				ModelTestUtils.assertParameterNames( methodtestSockName50, new String[] {"self"} );
			}
			//Function test:testGetSockOpt
			{
			IMethod methodtestGetSockOpt51;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestGetSockOpt51 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testGetSockOpt", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetSockOpt51, new String[] {"self"} );
			}
			//Function test:testSetSockOpt
			{
			IMethod methodtestSetSockOpt52;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestSetSockOpt52 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testSetSockOpt", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetSockOpt52, new String[] {"self"} );
			}
			//Function test:testSendAfterClose
			{
			IMethod methodtestSendAfterClose53;
				IModelElement[] classGeneralModuleTests33Childs = classGeneralModuleTests33.getChildren();
				methodtestSendAfterClose53 = ModelTestUtils.getAssertMethod( classGeneralModuleTests33Childs, "testSendAfterClose", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendAfterClose53, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBasicTCPTest54;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicTCPTest54 = ModelTestUtils.getAssertClass( moduleChilds, "BasicTCPTest" );
			//Function test:__init__
			{
			IMethod method__init__55;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method__init__55 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__55, new String[] {"self", "methodName"} );
			}
			//Function test:testRecv
			{
			IMethod methodtestRecv56;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestRecv56 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecv56, new String[] {"self"} );
			}
			//Function test:_testRecv
			{
			IMethod method_testRecv57;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testRecv57 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testRecv57, new String[] {"self"} );
			}
			//Function test:testOverFlowRecv
			{
			IMethod methodtestOverFlowRecv58;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestOverFlowRecv58 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testOverFlowRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestOverFlowRecv58, new String[] {"self"} );
			}
			//Function test:_testOverFlowRecv
			{
			IMethod method_testOverFlowRecv59;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testOverFlowRecv59 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testOverFlowRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testOverFlowRecv59, new String[] {"self"} );
			}
			//Function test:testRecvFrom
			{
			IMethod methodtestRecvFrom60;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestRecvFrom60 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvFrom60, new String[] {"self"} );
			}
			//Function test:_testRecvFrom
			{
			IMethod method_testRecvFrom61;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testRecvFrom61 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( method_testRecvFrom61, new String[] {"self"} );
			}
			//Function test:testOverFlowRecvFrom
			{
			IMethod methodtestOverFlowRecvFrom62;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestOverFlowRecvFrom62 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testOverFlowRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( methodtestOverFlowRecvFrom62, new String[] {"self"} );
			}
			//Function test:_testOverFlowRecvFrom
			{
			IMethod method_testOverFlowRecvFrom63;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testOverFlowRecvFrom63 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testOverFlowRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( method_testOverFlowRecvFrom63, new String[] {"self"} );
			}
			//Function test:testSendAll
			{
			IMethod methodtestSendAll64;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestSendAll64 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testSendAll", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendAll64, new String[] {"self"} );
			}
			//Function test:_testSendAll
			{
			IMethod method_testSendAll65;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testSendAll65 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testSendAll", 1 );
				ModelTestUtils.assertParameterNames( method_testSendAll65, new String[] {"self"} );
			}
			//Function test:testFromFd
			{
			IMethod methodtestFromFd66;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestFromFd66 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testFromFd", 1 );
				ModelTestUtils.assertParameterNames( methodtestFromFd66, new String[] {"self"} );
			}
			//Function test:_testFromFd
			{
			IMethod method_testFromFd67;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testFromFd67 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testFromFd", 1 );
				ModelTestUtils.assertParameterNames( method_testFromFd67, new String[] {"self"} );
			}
			//Function test:testShutdown
			{
			IMethod methodtestShutdown68;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				methodtestShutdown68 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "testShutdown", 1 );
				ModelTestUtils.assertParameterNames( methodtestShutdown68, new String[] {"self"} );
			}
			//Function test:_testShutdown
			{
			IMethod method_testShutdown69;
				IModelElement[] classBasicTCPTest54Childs = classBasicTCPTest54.getChildren();
				method_testShutdown69 = ModelTestUtils.getAssertMethod( classBasicTCPTest54Childs, "_testShutdown", 1 );
				ModelTestUtils.assertParameterNames( method_testShutdown69, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBasicUDPTest70;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicUDPTest70 = ModelTestUtils.getAssertClass( moduleChilds, "BasicUDPTest" );
			//Function test:__init__
			{
			IMethod method__init__71;
				IModelElement[] classBasicUDPTest70Childs = classBasicUDPTest70.getChildren();
				method__init__71 = ModelTestUtils.getAssertMethod( classBasicUDPTest70Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__71, new String[] {"self", "methodName"} );
			}
			//Function test:testSendtoAndRecv
			{
			IMethod methodtestSendtoAndRecv72;
				IModelElement[] classBasicUDPTest70Childs = classBasicUDPTest70.getChildren();
				methodtestSendtoAndRecv72 = ModelTestUtils.getAssertMethod( classBasicUDPTest70Childs, "testSendtoAndRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendtoAndRecv72, new String[] {"self"} );
			}
			//Function test:_testSendtoAndRecv
			{
			IMethod method_testSendtoAndRecv73;
				IModelElement[] classBasicUDPTest70Childs = classBasicUDPTest70.getChildren();
				method_testSendtoAndRecv73 = ModelTestUtils.getAssertMethod( classBasicUDPTest70Childs, "_testSendtoAndRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testSendtoAndRecv73, new String[] {"self"} );
			}
			//Function test:testRecvFrom
			{
			IMethod methodtestRecvFrom74;
				IModelElement[] classBasicUDPTest70Childs = classBasicUDPTest70.getChildren();
				methodtestRecvFrom74 = ModelTestUtils.getAssertMethod( classBasicUDPTest70Childs, "testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvFrom74, new String[] {"self"} );
			}
			//Function test:_testRecvFrom
			{
			IMethod method_testRecvFrom75;
				IModelElement[] classBasicUDPTest70Childs = classBasicUDPTest70.getChildren();
				method_testRecvFrom75 = ModelTestUtils.getAssertMethod( classBasicUDPTest70Childs, "_testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( method_testRecvFrom75, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBasicSocketPairTest76;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicSocketPairTest76 = ModelTestUtils.getAssertClass( moduleChilds, "BasicSocketPairTest" );
			//Function test:__init__
			{
			IMethod method__init__77;
				IModelElement[] classBasicSocketPairTest76Childs = classBasicSocketPairTest76.getChildren();
				method__init__77 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest76Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__77, new String[] {"self", "methodName"} );
			}
			//Function test:testRecv
			{
			IMethod methodtestRecv78;
				IModelElement[] classBasicSocketPairTest76Childs = classBasicSocketPairTest76.getChildren();
				methodtestRecv78 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest76Childs, "testRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecv78, new String[] {"self"} );
			}
			//Function test:_testRecv
			{
			IMethod method_testRecv79;
				IModelElement[] classBasicSocketPairTest76Childs = classBasicSocketPairTest76.getChildren();
				method_testRecv79 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest76Childs, "_testRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testRecv79, new String[] {"self"} );
			}
			//Function test:testSend
			{
			IMethod methodtestSend80;
				IModelElement[] classBasicSocketPairTest76Childs = classBasicSocketPairTest76.getChildren();
				methodtestSend80 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest76Childs, "testSend", 1 );
				ModelTestUtils.assertParameterNames( methodtestSend80, new String[] {"self"} );
			}
			//Function test:_testSend
			{
			IMethod method_testSend81;
				IModelElement[] classBasicSocketPairTest76Childs = classBasicSocketPairTest76.getChildren();
				method_testSend81 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest76Childs, "_testSend", 1 );
				ModelTestUtils.assertParameterNames( method_testSend81, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNonBlockingTCPTests82;
			IModelElement[] moduleChilds = module.getChildren();
			classNonBlockingTCPTests82 = ModelTestUtils.getAssertClass( moduleChilds, "NonBlockingTCPTests" );
			//Function test:__init__
			{
			IMethod method__init__83;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				method__init__83 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__83, new String[] {"self", "methodName"} );
			}
			//Function test:testSetBlocking
			{
			IMethod methodtestSetBlocking84;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				methodtestSetBlocking84 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "testSetBlocking", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetBlocking84, new String[] {"self"} );
			}
			//Function test:_testSetBlocking
			{
			IMethod method_testSetBlocking85;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				method_testSetBlocking85 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "_testSetBlocking", 1 );
				ModelTestUtils.assertParameterNames( method_testSetBlocking85, new String[] {"self"} );
			}
			//Function test:testAccept
			{
			IMethod methodtestAccept86;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				methodtestAccept86 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "testAccept", 1 );
				ModelTestUtils.assertParameterNames( methodtestAccept86, new String[] {"self"} );
			}
			//Function test:_testAccept
			{
			IMethod method_testAccept87;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				method_testAccept87 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "_testAccept", 1 );
				ModelTestUtils.assertParameterNames( method_testAccept87, new String[] {"self"} );
			}
			//Function test:testConnect
			{
			IMethod methodtestConnect88;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				methodtestConnect88 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "testConnect", 1 );
				ModelTestUtils.assertParameterNames( methodtestConnect88, new String[] {"self"} );
			}
			//Function test:_testConnect
			{
			IMethod method_testConnect89;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				method_testConnect89 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "_testConnect", 1 );
				ModelTestUtils.assertParameterNames( method_testConnect89, new String[] {"self"} );
			}
			//Function test:testRecv
			{
			IMethod methodtestRecv90;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				methodtestRecv90 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "testRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecv90, new String[] {"self"} );
			}
			//Function test:_testRecv
			{
			IMethod method_testRecv91;
				IModelElement[] classNonBlockingTCPTests82Childs = classNonBlockingTCPTests82.getChildren();
				method_testRecv91 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests82Childs, "_testRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testRecv91, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFileObjectClassTestCase92;
			IModelElement[] moduleChilds = module.getChildren();
			classFileObjectClassTestCase92 = ModelTestUtils.getAssertClass( moduleChilds, "FileObjectClassTestCase" );
			{
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileObjectClassTestCase92Childs, "bufsize");
			}
			//Function test:__init__
			{
			IMethod method__init__93;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				method__init__93 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__93, new String[] {"self", "methodName"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp94;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodsetUp94 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp94, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown95;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodtearDown95 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown95, new String[] {"self"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp96;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodclientSetUp96 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp96, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown97;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodclientTearDown97 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown97, new String[] {"self"} );
			}
			//Function test:testSmallRead
			{
			IMethod methodtestSmallRead98;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodtestSmallRead98 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "testSmallRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestSmallRead98, new String[] {"self"} );
			}
			//Function test:_testSmallRead
			{
			IMethod method_testSmallRead99;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				method_testSmallRead99 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "_testSmallRead", 1 );
				ModelTestUtils.assertParameterNames( method_testSmallRead99, new String[] {"self"} );
			}
			//Function test:testFullRead
			{
			IMethod methodtestFullRead100;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodtestFullRead100 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "testFullRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestFullRead100, new String[] {"self"} );
			}
			//Function test:_testFullRead
			{
			IMethod method_testFullRead101;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				method_testFullRead101 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "_testFullRead", 1 );
				ModelTestUtils.assertParameterNames( method_testFullRead101, new String[] {"self"} );
			}
			//Function test:testUnbufferedRead
			{
			IMethod methodtestUnbufferedRead102;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodtestUnbufferedRead102 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "testUnbufferedRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnbufferedRead102, new String[] {"self"} );
			}
			//Function test:_testUnbufferedRead
			{
			IMethod method_testUnbufferedRead103;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				method_testUnbufferedRead103 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "_testUnbufferedRead", 1 );
				ModelTestUtils.assertParameterNames( method_testUnbufferedRead103, new String[] {"self"} );
			}
			//Function test:testReadline
			{
			IMethod methodtestReadline104;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodtestReadline104 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "testReadline", 1 );
				ModelTestUtils.assertParameterNames( methodtestReadline104, new String[] {"self"} );
			}
			//Function test:_testReadline
			{
			IMethod method_testReadline105;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				method_testReadline105 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "_testReadline", 1 );
				ModelTestUtils.assertParameterNames( method_testReadline105, new String[] {"self"} );
			}
			//Function test:testClosedAttr
			{
			IMethod methodtestClosedAttr106;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				methodtestClosedAttr106 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "testClosedAttr", 1 );
				ModelTestUtils.assertParameterNames( methodtestClosedAttr106, new String[] {"self"} );
			}
			//Function test:_testClosedAttr
			{
			IMethod method_testClosedAttr107;
				IModelElement[] classFileObjectClassTestCase92Childs = classFileObjectClassTestCase92.getChildren();
				method_testClosedAttr107 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase92Childs, "_testClosedAttr", 1 );
				ModelTestUtils.assertParameterNames( method_testClosedAttr107, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnbufferedFileObjectClassTestCase108;
			IModelElement[] moduleChilds = module.getChildren();
			classUnbufferedFileObjectClassTestCase108 = ModelTestUtils.getAssertClass( moduleChilds, "UnbufferedFileObjectClassTestCase" );
			{
				IModelElement[] classUnbufferedFileObjectClassTestCase108Childs = classUnbufferedFileObjectClassTestCase108.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnbufferedFileObjectClassTestCase108Childs, "bufsize");
			}
			//Function test:testUnbufferedReadline
			{
			IMethod methodtestUnbufferedReadline109;
				IModelElement[] classUnbufferedFileObjectClassTestCase108Childs = classUnbufferedFileObjectClassTestCase108.getChildren();
				methodtestUnbufferedReadline109 = ModelTestUtils.getAssertMethod( classUnbufferedFileObjectClassTestCase108Childs, "testUnbufferedReadline", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnbufferedReadline109, new String[] {"self"} );
			}
			//Function test:_testUnbufferedReadline
			{
			IMethod method_testUnbufferedReadline110;
				IModelElement[] classUnbufferedFileObjectClassTestCase108Childs = classUnbufferedFileObjectClassTestCase108.getChildren();
				method_testUnbufferedReadline110 = ModelTestUtils.getAssertMethod( classUnbufferedFileObjectClassTestCase108Childs, "_testUnbufferedReadline", 1 );
				ModelTestUtils.assertParameterNames( method_testUnbufferedReadline110, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLineBufferedFileObjectClassTestCase111;
			IModelElement[] moduleChilds = module.getChildren();
			classLineBufferedFileObjectClassTestCase111 = ModelTestUtils.getAssertClass( moduleChilds, "LineBufferedFileObjectClassTestCase" );
			{
				IModelElement[] classLineBufferedFileObjectClassTestCase111Childs = classLineBufferedFileObjectClassTestCase111.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLineBufferedFileObjectClassTestCase111Childs, "bufsize");
			}
		}
		//Class test
		{
		IType classSmallBufferedFileObjectClassTestCase112;
			IModelElement[] moduleChilds = module.getChildren();
			classSmallBufferedFileObjectClassTestCase112 = ModelTestUtils.getAssertClass( moduleChilds, "SmallBufferedFileObjectClassTestCase" );
			{
				IModelElement[] classSmallBufferedFileObjectClassTestCase112Childs = classSmallBufferedFileObjectClassTestCase112.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSmallBufferedFileObjectClassTestCase112Childs, "bufsize");
			}
		}
		//Class test
		{
		IType classTCPTimeoutTest113;
			IModelElement[] moduleChilds = module.getChildren();
			classTCPTimeoutTest113 = ModelTestUtils.getAssertClass( moduleChilds, "TCPTimeoutTest" );
			//Function test:testTCPTimeout
			{
			IMethod methodtestTCPTimeout114;
				IModelElement[] classTCPTimeoutTest113Childs = classTCPTimeoutTest113.getChildren();
				methodtestTCPTimeout114 = ModelTestUtils.getAssertMethod( classTCPTimeoutTest113Childs, "testTCPTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestTCPTimeout114, new String[] {"self"} );
				//Function test:raise_timeout
				{
				IMethod methodraise_timeout115;
					IModelElement[] methodtestTCPTimeout114Childs = methodtestTCPTimeout114.getChildren();
					methodraise_timeout115 = ModelTestUtils.getAssertMethod( methodtestTCPTimeout114Childs, "raise_timeout", 2 );
					ModelTestUtils.assertParameterNames( methodraise_timeout115, new String[] {"args", "kwargs"} );
				}
			}
			//Function test:testTimeoutZero
			{
			IMethod methodtestTimeoutZero116;
				IModelElement[] classTCPTimeoutTest113Childs = classTCPTimeoutTest113.getChildren();
				methodtestTimeoutZero116 = ModelTestUtils.getAssertMethod( classTCPTimeoutTest113Childs, "testTimeoutZero", 1 );
				ModelTestUtils.assertParameterNames( methodtestTimeoutZero116, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUDPTimeoutTest117;
			IModelElement[] moduleChilds = module.getChildren();
			classUDPTimeoutTest117 = ModelTestUtils.getAssertClass( moduleChilds, "UDPTimeoutTest" );
			//Function test:testUDPTimeout
			{
			IMethod methodtestUDPTimeout118;
				IModelElement[] classUDPTimeoutTest117Childs = classUDPTimeoutTest117.getChildren();
				methodtestUDPTimeout118 = ModelTestUtils.getAssertMethod( classUDPTimeoutTest117Childs, "testUDPTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestUDPTimeout118, new String[] {"self"} );
				//Function test:raise_timeout
				{
				IMethod methodraise_timeout119;
					IModelElement[] methodtestUDPTimeout118Childs = methodtestUDPTimeout118.getChildren();
					methodraise_timeout119 = ModelTestUtils.getAssertMethod( methodtestUDPTimeout118Childs, "raise_timeout", 2 );
					ModelTestUtils.assertParameterNames( methodraise_timeout119, new String[] {"args", "kwargs"} );
				}
			}
			//Function test:testTimeoutZero
			{
			IMethod methodtestTimeoutZero120;
				IModelElement[] classUDPTimeoutTest117Childs = classUDPTimeoutTest117.getChildren();
				methodtestTimeoutZero120 = ModelTestUtils.getAssertMethod( classUDPTimeoutTest117Childs, "testTimeoutZero", 1 );
				ModelTestUtils.assertParameterNames( methodtestTimeoutZero120, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExceptions121;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExceptions121 = ModelTestUtils.getAssertClass( moduleChilds, "TestExceptions" );
			//Function test:testExceptionTree
			{
			IMethod methodtestExceptionTree122;
				IModelElement[] classTestExceptions121Childs = classTestExceptions121.getChildren();
				methodtestExceptionTree122 = ModelTestUtils.getAssertMethod( classTestExceptions121Childs, "testExceptionTree", 1 );
				ModelTestUtils.assertParameterNames( methodtestExceptionTree122, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main123;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main123 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen262( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_builtin.py"));

		assertNotNull("Module test_builtin.py not found", module);
		assertEquals("test_builtin.py", module.getElementName());
		
		//Class test
		{
		IType classSquares0;
			IModelElement[] moduleChilds = module.getChildren();
			classSquares0 = ModelTestUtils.getAssertClass( moduleChilds, "Squares" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classSquares0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "max"} );
			}
			//Function test:__len__
			{
			IMethod method__len__2;
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				method__len__2 = ModelTestUtils.getAssertMethod( classSquares0Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__2, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__3;
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				method__getitem__3 = ModelTestUtils.getAssertMethod( classSquares0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__3, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classStrSquares4;
			IModelElement[] moduleChilds = module.getChildren();
			classStrSquares4 = ModelTestUtils.getAssertClass( moduleChilds, "StrSquares" );
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classStrSquares4Childs = classStrSquares4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classStrSquares4Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "max"} );
			}
			//Function test:__len__
			{
			IMethod method__len__6;
				IModelElement[] classStrSquares4Childs = classStrSquares4.getChildren();
				method__len__6 = ModelTestUtils.getAssertMethod( classStrSquares4Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__6, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__7;
				IModelElement[] classStrSquares4Childs = classStrSquares4.getChildren();
				method__getitem__7 = ModelTestUtils.getAssertMethod( classStrSquares4Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__7, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classBitBucket8;
			IModelElement[] moduleChilds = module.getChildren();
			classBitBucket8 = ModelTestUtils.getAssertClass( moduleChilds, "BitBucket" );
			//Function test:write
			{
			IMethod methodwrite9;
				IModelElement[] classBitBucket8Childs = classBitBucket8.getChildren();
				methodwrite9 = ModelTestUtils.getAssertMethod( classBitBucket8Childs, "write", 2 );
				ModelTestUtils.assertParameterNames( methodwrite9, new String[] {"self", "line"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "L");
		}
		//Class test
		{
		IType classBuiltinTest10;
			IModelElement[] moduleChilds = module.getChildren();
			classBuiltinTest10 = ModelTestUtils.getAssertClass( moduleChilds, "BuiltinTest" );
			//Function test:test_import
			{
			IMethod methodtest_import11;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_import11 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_import", 1 );
				ModelTestUtils.assertParameterNames( methodtest_import11, new String[] {"self"} );
			}
			//Function test:test_abs
			{
			IMethod methodtest_abs12;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_abs12 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_abs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs12, new String[] {"self"} );
			}
			//Function test:test_apply
			{
			IMethod methodtest_apply13;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_apply13 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_apply", 1 );
				ModelTestUtils.assertParameterNames( methodtest_apply13, new String[] {"self"} );
				//Function test:f0
				{
				IMethod methodf014;
					IModelElement[] methodtest_apply13Childs = methodtest_apply13.getChildren();
					methodf014 = ModelTestUtils.getAssertMethod( methodtest_apply13Childs, "f0", 1 );
					ModelTestUtils.assertParameterNames( methodf014, new String[] {"args"} );
				}
				//Function test:f1
				{
				IMethod methodf115;
					IModelElement[] methodtest_apply13Childs = methodtest_apply13.getChildren();
					methodf115 = ModelTestUtils.getAssertMethod( methodtest_apply13Childs, "f1", 1 );
					ModelTestUtils.assertParameterNames( methodf115, new String[] {"a1"} );
				}
				//Function test:f2
				{
				IMethod methodf216;
					IModelElement[] methodtest_apply13Childs = methodtest_apply13.getChildren();
					methodf216 = ModelTestUtils.getAssertMethod( methodtest_apply13Childs, "f2", 2 );
					ModelTestUtils.assertParameterNames( methodf216, new String[] {"a1", "a2"} );
				}
				//Function test:f3
				{
				IMethod methodf317;
					IModelElement[] methodtest_apply13Childs = methodtest_apply13.getChildren();
					methodf317 = ModelTestUtils.getAssertMethod( methodtest_apply13Childs, "f3", 3 );
					ModelTestUtils.assertParameterNames( methodf317, new String[] {"a1", "a2", "a3"} );
				}
			}
			//Function test:test_callable
			{
			IMethod methodtest_callable18;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_callable18 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callable18, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf19;
					IModelElement[] methodtest_callable18Childs = methodtest_callable18.getChildren();
					methodf19 = ModelTestUtils.getAssertMethod( methodtest_callable18Childs, "f", 0 );
				}
				//Class test
				{
				IType classC20;
					IModelElement[] methodtest_callable18Childs = methodtest_callable18.getChildren();
					classC20 = ModelTestUtils.getAssertClass( methodtest_callable18Childs, "C" );
					//Function test:meth
					{
					IMethod methodmeth21;
						IModelElement[] classC20Childs = classC20.getChildren();
						methodmeth21 = ModelTestUtils.getAssertMethod( classC20Childs, "meth", 1 );
						ModelTestUtils.assertParameterNames( methodmeth21, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classD22;
					IModelElement[] methodtest_callable18Childs = methodtest_callable18.getChildren();
					classD22 = ModelTestUtils.getAssertClass( methodtest_callable18Childs, "D" );
					//Function test:__call__
					{
					IMethod method__call__23;
						IModelElement[] classD22Childs = classD22.getChildren();
						method__call__23 = ModelTestUtils.getAssertMethod( classD22Childs, "__call__", 1 );
						ModelTestUtils.assertParameterNames( method__call__23, new String[] {"self"} );
					}
				}
			}
			//Function test:test_chr
			{
			IMethod methodtest_chr24;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_chr24 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_chr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chr24, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp25;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_cmp25 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp25, new String[] {"self"} );
			}
			//Function test:test_coerce
			{
			IMethod methodtest_coerce26;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_coerce26 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_coerce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coerce26, new String[] {"self"} );
				//Class test
				{
				IType classBadNumber27;
					IModelElement[] methodtest_coerce26Childs = methodtest_coerce26.getChildren();
					classBadNumber27 = ModelTestUtils.getAssertClass( methodtest_coerce26Childs, "BadNumber" );
					//Function test:__coerce__
					{
					IMethod method__coerce__28;
						IModelElement[] classBadNumber27Childs = classBadNumber27.getChildren();
						method__coerce__28 = ModelTestUtils.getAssertMethod( classBadNumber27Childs, "__coerce__", 2 );
						ModelTestUtils.assertParameterNames( method__coerce__28, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_compile
			{
			IMethod methodtest_compile29;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_compile29 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_compile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compile29, new String[] {"self"} );
			}
			//Function test:test_delattr
			{
			IMethod methodtest_delattr30;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_delattr30 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_delattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delattr30, new String[] {"self"} );
			}
			//Function test:test_dir
			{
			IMethod methodtest_dir31;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_dir31 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_dir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dir31, new String[] {"self"} );
			}
			//Function test:test_divmod
			{
			IMethod methodtest_divmod32;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_divmod32 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_divmod", 1 );
				ModelTestUtils.assertParameterNames( methodtest_divmod32, new String[] {"self"} );
			}
			//Function test:test_eval
			{
			IMethod methodtest_eval33;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_eval33 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_eval", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eval33, new String[] {"self"} );
			}
			//Function test:test_general_eval
			{
			IMethod methodtest_general_eval34;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_general_eval34 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_general_eval", 1 );
				ModelTestUtils.assertParameterNames( methodtest_general_eval34, new String[] {"self"} );
				//Class test
				{
				IType classM35;
					IModelElement[] methodtest_general_eval34Childs = methodtest_general_eval34.getChildren();
					classM35 = ModelTestUtils.getAssertClass( methodtest_general_eval34Childs, "M" );
					//Function test:__getitem__
					{
					IMethod method__getitem__36;
						IModelElement[] classM35Childs = classM35.getChildren();
						method__getitem__36 = ModelTestUtils.getAssertMethod( classM35Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__36, new String[] {"self", "key"} );
					}
					//Function test:keys
					{
					IMethod methodkeys37;
						IModelElement[] classM35Childs = classM35.getChildren();
						methodkeys37 = ModelTestUtils.getAssertMethod( classM35Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys37, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classA38;
					IModelElement[] methodtest_general_eval34Childs = methodtest_general_eval34.getChildren();
					classA38 = ModelTestUtils.getAssertClass( methodtest_general_eval34Childs, "A" );
				}
				//Class test
				{
				IType classD39;
					IModelElement[] methodtest_general_eval34Childs = methodtest_general_eval34.getChildren();
					classD39 = ModelTestUtils.getAssertClass( methodtest_general_eval34Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__40;
						IModelElement[] classD39Childs = classD39.getChildren();
						method__getitem__40 = ModelTestUtils.getAssertMethod( classD39Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__40, new String[] {"self", "key"} );
					}
					//Function test:keys
					{
					IMethod methodkeys41;
						IModelElement[] classD39Childs = classD39.getChildren();
						methodkeys41 = ModelTestUtils.getAssertMethod( classD39Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys41, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classSpreadSheet42;
					IModelElement[] methodtest_general_eval34Childs = methodtest_general_eval34.getChildren();
					classSpreadSheet42 = ModelTestUtils.getAssertClass( methodtest_general_eval34Childs, "SpreadSheet" );
					//Function test:__setitem__
					{
					IMethod method__setitem__43;
						IModelElement[] classSpreadSheet42Childs = classSpreadSheet42.getChildren();
						method__setitem__43 = ModelTestUtils.getAssertMethod( classSpreadSheet42Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__43, new String[] {"self", "key", "formula"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__44;
						IModelElement[] classSpreadSheet42Childs = classSpreadSheet42.getChildren();
						method__getitem__44 = ModelTestUtils.getAssertMethod( classSpreadSheet42Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__44, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classC45;
					IModelElement[] methodtest_general_eval34Childs = methodtest_general_eval34.getChildren();
					classC45 = ModelTestUtils.getAssertClass( methodtest_general_eval34Childs, "C" );
					//Function test:__getitem__
					{
					IMethod method__getitem__46;
						IModelElement[] classC45Childs = classC45.getChildren();
						method__getitem__46 = ModelTestUtils.getAssertMethod( classC45Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__46, new String[] {"self", "item"} );
					}
					//Function test:keys
					{
					IMethod methodkeys47;
						IModelElement[] classC45Childs = classC45.getChildren();
						methodkeys47 = ModelTestUtils.getAssertMethod( classC45Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys47, new String[] {"self"} );
					}
				}
			}
			{
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest10Childs, "z");
			}
			{
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest10Childs, "f");
			}
			//Function test:test_execfile
			{
			IMethod methodtest_execfile48;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_execfile48 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_execfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_execfile48, new String[] {"self"} );
				//Class test
				{
				IType classM49;
					IModelElement[] methodtest_execfile48Childs = methodtest_execfile48.getChildren();
					classM49 = ModelTestUtils.getAssertClass( methodtest_execfile48Childs, "M" );
					//Function test:__init__
					{
					IMethod method__init__50;
						IModelElement[] classM49Childs = classM49.getChildren();
						method__init__50 = ModelTestUtils.getAssertMethod( classM49Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__50, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__51;
						IModelElement[] classM49Childs = classM49.getChildren();
						method__getitem__51 = ModelTestUtils.getAssertMethod( classM49Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__51, new String[] {"self", "key"} );
					}
					//Function test:__setitem__
					{
					IMethod method__setitem__52;
						IModelElement[] classM49Childs = classM49.getChildren();
						method__setitem__52 = ModelTestUtils.getAssertMethod( classM49Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__52, new String[] {"self", "key", "value"} );
					}
				}
			}
			//Function test:test_filter
			{
			IMethod methodtest_filter53;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_filter53 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_filter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filter53, new String[] {"self"} );
				//Function test:identity
				{
				IMethod methodidentity54;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					methodidentity54 = ModelTestUtils.getAssertMethod( methodtest_filter53Childs, "identity", 1 );
					ModelTestUtils.assertParameterNames( methodidentity54, new String[] {"item"} );
				}
				//Class test
				{
				IType classBadSeq55;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classBadSeq55 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__56;
						IModelElement[] classBadSeq55Childs = classBadSeq55.getChildren();
						method__getitem__56 = ModelTestUtils.getAssertMethod( classBadSeq55Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__56, new String[] {"self", "index"} );
					}
				}
				//Function test:badfunc
				{
				IMethod methodbadfunc57;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					methodbadfunc57 = ModelTestUtils.getAssertMethod( methodtest_filter53Childs, "badfunc", 0 );
				}
				//Class test
				{
				IType classbadstr58;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classbadstr58 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "badstr" );
					//Function test:__getitem__
					{
					IMethod method__getitem__59;
						IModelElement[] classbadstr58Childs = classbadstr58.getChildren();
						method__getitem__59 = ModelTestUtils.getAssertMethod( classbadstr58Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__59, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classbadstr260;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classbadstr260 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "badstr2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__61;
						IModelElement[] classbadstr260Childs = classbadstr260.getChildren();
						method__getitem__61 = ModelTestUtils.getAssertMethod( classbadstr260Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__61, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classweirdstr62;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classweirdstr62 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "weirdstr" );
					//Function test:__getitem__
					{
					IMethod method__getitem__63;
						IModelElement[] classweirdstr62Childs = classweirdstr62.getChildren();
						method__getitem__63 = ModelTestUtils.getAssertMethod( classweirdstr62Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__63, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classshiftstr64;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classshiftstr64 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "shiftstr" );
					//Function test:__getitem__
					{
					IMethod method__getitem__65;
						IModelElement[] classshiftstr64Childs = classshiftstr64.getChildren();
						method__getitem__65 = ModelTestUtils.getAssertMethod( classshiftstr64Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__65, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classbadunicode66;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classbadunicode66 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "badunicode" );
					//Function test:__getitem__
					{
					IMethod method__getitem__67;
						IModelElement[] classbadunicode66Childs = classbadunicode66.getChildren();
						method__getitem__67 = ModelTestUtils.getAssertMethod( classbadunicode66Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__67, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classweirdunicode68;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classweirdunicode68 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "weirdunicode" );
					//Function test:__getitem__
					{
					IMethod method__getitem__69;
						IModelElement[] classweirdunicode68Childs = classweirdunicode68.getChildren();
						method__getitem__69 = ModelTestUtils.getAssertMethod( classweirdunicode68Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__69, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classshiftunicode70;
					IModelElement[] methodtest_filter53Childs = methodtest_filter53.getChildren();
					classshiftunicode70 = ModelTestUtils.getAssertClass( methodtest_filter53Childs, "shiftunicode" );
					//Function test:__getitem__
					{
					IMethod method__getitem__71;
						IModelElement[] classshiftunicode70Childs = classshiftunicode70.getChildren();
						method__getitem__71 = ModelTestUtils.getAssertMethod( classshiftunicode70Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__71, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_filter_subclasses
			{
			IMethod methodtest_filter_subclasses72;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_filter_subclasses72 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_filter_subclasses", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filter_subclasses72, new String[] {"self"} );
				//Class test
				{
				IType classtuple273;
					IModelElement[] methodtest_filter_subclasses72Childs = methodtest_filter_subclasses72.getChildren();
					classtuple273 = ModelTestUtils.getAssertClass( methodtest_filter_subclasses72Childs, "tuple2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__74;
						IModelElement[] classtuple273Childs = classtuple273.getChildren();
						method__getitem__74 = ModelTestUtils.getAssertMethod( classtuple273Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__74, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classstr275;
					IModelElement[] methodtest_filter_subclasses72Childs = methodtest_filter_subclasses72.getChildren();
					classstr275 = ModelTestUtils.getAssertClass( methodtest_filter_subclasses72Childs, "str2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__76;
						IModelElement[] classstr275Childs = classstr275.getChildren();
						method__getitem__76 = ModelTestUtils.getAssertMethod( classstr275Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__76, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classunicode277;
					IModelElement[] methodtest_filter_subclasses72Childs = methodtest_filter_subclasses72.getChildren();
					classunicode277 = ModelTestUtils.getAssertClass( methodtest_filter_subclasses72Childs, "unicode2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__78;
						IModelElement[] classunicode277Childs = classunicode277.getChildren();
						method__getitem__78 = ModelTestUtils.getAssertMethod( classunicode277Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__78, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_float
			{
			IMethod methodtest_float79;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_float79 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float79, new String[] {"self"} );
			}
			//Function test:test_getattr
			{
			IMethod methodtest_getattr80;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_getattr80 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_getattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getattr80, new String[] {"self"} );
			}
			//Function test:test_hasattr
			{
			IMethod methodtest_hasattr81;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_hasattr81 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_hasattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hasattr81, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash82;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_hash82 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash82, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf83;
					IModelElement[] methodtest_hash82Childs = methodtest_hash82.getChildren();
					methodf83 = ModelTestUtils.getAssertMethod( methodtest_hash82Childs, "f", 0 );
				}
			}
			//Function test:test_hex
			{
			IMethod methodtest_hex84;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_hex84 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_hex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hex84, new String[] {"self"} );
			}
			//Function test:test_id
			{
			IMethod methodtest_id85;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_id85 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_id", 1 );
				ModelTestUtils.assertParameterNames( methodtest_id85, new String[] {"self"} );
			}
			//Function test:test_int
			{
			IMethod methodtest_int86;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_int86 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int86, new String[] {"self"} );
			}
			//Function test:test_intern
			{
			IMethod methodtest_intern87;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_intern87 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_intern", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intern87, new String[] {"self"} );
				//Class test
				{
				IType classS88;
					IModelElement[] methodtest_intern87Childs = methodtest_intern87.getChildren();
					classS88 = ModelTestUtils.getAssertClass( methodtest_intern87Childs, "S" );
					//Function test:__hash__
					{
					IMethod method__hash__89;
						IModelElement[] classS88Childs = classS88.getChildren();
						method__hash__89 = ModelTestUtils.getAssertMethod( classS88Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__89, new String[] {"self"} );
					}
				}
			}
			//Function test:test_iter
			{
			IMethod methodtest_iter90;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_iter90 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter90, new String[] {"self"} );
			}
			//Function test:test_isinstance
			{
			IMethod methodtest_isinstance91;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_isinstance91 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_isinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance91, new String[] {"self"} );
				//Class test
				{
				IType classC92;
					IModelElement[] methodtest_isinstance91Childs = methodtest_isinstance91.getChildren();
					classC92 = ModelTestUtils.getAssertClass( methodtest_isinstance91Childs, "C" );
				}
				//Class test
				{
				IType classD93;
					IModelElement[] methodtest_isinstance91Childs = methodtest_isinstance91.getChildren();
					classD93 = ModelTestUtils.getAssertClass( methodtest_isinstance91Childs, "D" );
				}
				//Class test
				{
				IType classE94;
					IModelElement[] methodtest_isinstance91Childs = methodtest_isinstance91.getChildren();
					classE94 = ModelTestUtils.getAssertClass( methodtest_isinstance91Childs, "E" );
				}
			}
			//Function test:test_issubclass
			{
			IMethod methodtest_issubclass95;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_issubclass95 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_issubclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubclass95, new String[] {"self"} );
				//Class test
				{
				IType classC96;
					IModelElement[] methodtest_issubclass95Childs = methodtest_issubclass95.getChildren();
					classC96 = ModelTestUtils.getAssertClass( methodtest_issubclass95Childs, "C" );
				}
				//Class test
				{
				IType classD97;
					IModelElement[] methodtest_issubclass95Childs = methodtest_issubclass95.getChildren();
					classD97 = ModelTestUtils.getAssertClass( methodtest_issubclass95Childs, "D" );
				}
				//Class test
				{
				IType classE98;
					IModelElement[] methodtest_issubclass95Childs = methodtest_issubclass95.getChildren();
					classE98 = ModelTestUtils.getAssertClass( methodtest_issubclass95Childs, "E" );
				}
			}
			//Function test:test_len
			{
			IMethod methodtest_len99;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_len99 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len99, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq100;
					IModelElement[] methodtest_len99Childs = methodtest_len99.getChildren();
					classBadSeq100 = ModelTestUtils.getAssertClass( methodtest_len99Childs, "BadSeq" );
					//Function test:__len__
					{
					IMethod method__len__101;
						IModelElement[] classBadSeq100Childs = classBadSeq100.getChildren();
						method__len__101 = ModelTestUtils.getAssertMethod( classBadSeq100Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__101, new String[] {"self"} );
					}
				}
			}
			//Function test:test_list
			{
			IMethod methodtest_list102;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_list102 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_list102, new String[] {"self"} );
			}
			//Function test:test_long
			{
			IMethod methodtest_long103;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_long103 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long103, new String[] {"self"} );
			}
			//Function test:test_map
			{
			IMethod methodtest_map104;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_map104 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_map", 1 );
				ModelTestUtils.assertParameterNames( methodtest_map104, new String[] {"self"} );
				//Function test:sqrt
				{
				IMethod methodsqrt105;
					IModelElement[] methodtest_map104Childs = methodtest_map104.getChildren();
					methodsqrt105 = ModelTestUtils.getAssertMethod( methodtest_map104Childs, "sqrt", 1 );
					ModelTestUtils.assertParameterNames( methodsqrt105, new String[] {"x"} );
				}
				//Function test:plus
				{
				IMethod methodplus106;
					IModelElement[] methodtest_map104Childs = methodtest_map104.getChildren();
					methodplus106 = ModelTestUtils.getAssertMethod( methodtest_map104Childs, "plus", 1 );
					ModelTestUtils.assertParameterNames( methodplus106, new String[] {"v"} );
				}
				//Class test
				{
				IType classBadSeq107;
					IModelElement[] methodtest_map104Childs = methodtest_map104.getChildren();
					classBadSeq107 = ModelTestUtils.getAssertClass( methodtest_map104Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__108;
						IModelElement[] classBadSeq107Childs = classBadSeq107.getChildren();
						method__getitem__108 = ModelTestUtils.getAssertMethod( classBadSeq107Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__108, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_max
			{
			IMethod methodtest_max109;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_max109 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_max", 1 );
				ModelTestUtils.assertParameterNames( methodtest_max109, new String[] {"self"} );
			}
			//Function test:test_min
			{
			IMethod methodtest_min110;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_min110 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_min", 1 );
				ModelTestUtils.assertParameterNames( methodtest_min110, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq111;
					IModelElement[] methodtest_min110Childs = methodtest_min110.getChildren();
					classBadSeq111 = ModelTestUtils.getAssertClass( methodtest_min110Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__112;
						IModelElement[] classBadSeq111Childs = classBadSeq111.getChildren();
						method__getitem__112 = ModelTestUtils.getAssertMethod( classBadSeq111Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__112, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classBadNumber113;
					IModelElement[] methodtest_min110Childs = methodtest_min110.getChildren();
					classBadNumber113 = ModelTestUtils.getAssertClass( methodtest_min110Childs, "BadNumber" );
					//Function test:__cmp__
					{
					IMethod method__cmp__114;
						IModelElement[] classBadNumber113Childs = classBadNumber113.getChildren();
						method__cmp__114 = ModelTestUtils.getAssertMethod( classBadNumber113Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__114, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_oct
			{
			IMethod methodtest_oct115;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_oct115 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_oct", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oct115, new String[] {"self"} );
			}
			//Function test:write_testfile
			{
			IMethod methodwrite_testfile116;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodwrite_testfile116 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "write_testfile", 1 );
				ModelTestUtils.assertParameterNames( methodwrite_testfile116, new String[] {"self"} );
			}
			//Function test:test_open
			{
			IMethod methodtest_open117;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_open117 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_open", 1 );
				ModelTestUtils.assertParameterNames( methodtest_open117, new String[] {"self"} );
			}
			//Function test:test_ord
			{
			IMethod methodtest_ord118;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_ord118 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_ord", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ord118, new String[] {"self"} );
			}
			//Function test:test_pow
			{
			IMethod methodtest_pow119;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_pow119 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_pow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pow119, new String[] {"self"} );
			}
			//Function test:test_range
			{
			IMethod methodtest_range120;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_range120 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_range120, new String[] {"self"} );
			}
			//Function test:test_input_and_raw_input
			{
			IMethod methodtest_input_and_raw_input121;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_input_and_raw_input121 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_input_and_raw_input", 1 );
				ModelTestUtils.assertParameterNames( methodtest_input_and_raw_input121, new String[] {"self"} );
			}
			//Function test:test_reduce
			{
			IMethod methodtest_reduce122;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_reduce122 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce122, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq123;
					IModelElement[] methodtest_reduce122Childs = methodtest_reduce122.getChildren();
					classBadSeq123 = ModelTestUtils.getAssertClass( methodtest_reduce122Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__124;
						IModelElement[] classBadSeq123Childs = classBadSeq123.getChildren();
						method__getitem__124 = ModelTestUtils.getAssertMethod( classBadSeq123Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__124, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_reload
			{
			IMethod methodtest_reload125;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_reload125 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_reload", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reload125, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr126;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_repr126 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr126, new String[] {"self"} );
			}
			//Function test:test_round
			{
			IMethod methodtest_round127;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_round127 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_round", 1 );
				ModelTestUtils.assertParameterNames( methodtest_round127, new String[] {"self"} );
			}
			//Function test:test_setattr
			{
			IMethod methodtest_setattr128;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_setattr128 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_setattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setattr128, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str129;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_str129 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str129, new String[] {"self"} );
			}
			//Function test:test_sum
			{
			IMethod methodtest_sum130;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_sum130 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_sum", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sum130, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq131;
					IModelElement[] methodtest_sum130Childs = methodtest_sum130.getChildren();
					classBadSeq131 = ModelTestUtils.getAssertClass( methodtest_sum130Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__132;
						IModelElement[] classBadSeq131Childs = classBadSeq131.getChildren();
						method__getitem__132 = ModelTestUtils.getAssertMethod( classBadSeq131Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__132, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_tuple
			{
			IMethod methodtest_tuple133;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_tuple133 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tuple133, new String[] {"self"} );
			}
			//Function test:test_type
			{
			IMethod methodtest_type134;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_type134 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type134, new String[] {"self"} );
			}
			//Function test:test_unichr
			{
			IMethod methodtest_unichr135;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_unichr135 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_unichr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unichr135, new String[] {"self"} );
			}
			//Function test:get_vars_f0
			{
			IMethod methodget_vars_f0136;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodget_vars_f0136 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "get_vars_f0", 0 );
			}
			{
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest10Childs, "get_vars_f0");
			}
			//Function test:get_vars_f2
			{
			IMethod methodget_vars_f2137;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodget_vars_f2137 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "get_vars_f2", 0 );
			}
			{
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest10Childs, "get_vars_f2");
			}
			//Function test:test_vars
			{
			IMethod methodtest_vars138;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_vars138 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_vars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_vars138, new String[] {"self"} );
			}
			//Function test:test_zip
			{
			IMethod methodtest_zip139;
				IModelElement[] classBuiltinTest10Childs = classBuiltinTest10.getChildren();
				methodtest_zip139 = ModelTestUtils.getAssertMethod( classBuiltinTest10Childs, "test_zip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zip139, new String[] {"self"} );
				//Class test
				{
				IType classI140;
					IModelElement[] methodtest_zip139Childs = methodtest_zip139.getChildren();
					classI140 = ModelTestUtils.getAssertClass( methodtest_zip139Childs, "I" );
					//Function test:__getitem__
					{
					IMethod method__getitem__141;
						IModelElement[] classI140Childs = classI140.getChildren();
						method__getitem__141 = ModelTestUtils.getAssertMethod( classI140Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__141, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classG142;
					IModelElement[] methodtest_zip139Childs = methodtest_zip139.getChildren();
					classG142 = ModelTestUtils.getAssertClass( methodtest_zip139Childs, "G" );
				}
				//Class test
				{
				IType classSequenceWithoutALength143;
					IModelElement[] methodtest_zip139Childs = methodtest_zip139.getChildren();
					classSequenceWithoutALength143 = ModelTestUtils.getAssertClass( methodtest_zip139Childs, "SequenceWithoutALength" );
					//Function test:__getitem__
					{
					IMethod method__getitem__144;
						IModelElement[] classSequenceWithoutALength143Childs = classSequenceWithoutALength143.getChildren();
						method__getitem__144 = ModelTestUtils.getAssertMethod( classSequenceWithoutALength143Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__144, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classBadSeq145;
					IModelElement[] methodtest_zip139Childs = methodtest_zip139.getChildren();
					classBadSeq145 = ModelTestUtils.getAssertClass( methodtest_zip139Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__146;
						IModelElement[] classBadSeq145Childs = classBadSeq145.getChildren();
						method__getitem__146 = ModelTestUtils.getAssertMethod( classBadSeq145Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__146, new String[] {"self", "i"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestSorted147;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSorted147 = ModelTestUtils.getAssertClass( moduleChilds, "TestSorted" );
			//Function test:test_basic
			{
			IMethod methodtest_basic148;
				IModelElement[] classTestSorted147Childs = classTestSorted147.getChildren();
				methodtest_basic148 = ModelTestUtils.getAssertMethod( classTestSorted147Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic148, new String[] {"self"} );
			}
			//Function test:test_inputtypes
			{
			IMethod methodtest_inputtypes149;
				IModelElement[] classTestSorted147Childs = classTestSorted147.getChildren();
				methodtest_inputtypes149 = ModelTestUtils.getAssertMethod( classTestSorted147Childs, "test_inputtypes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inputtypes149, new String[] {"self"} );
			}
			//Function test:test_baddecorator
			{
			IMethod methodtest_baddecorator150;
				IModelElement[] classTestSorted147Childs = classTestSorted147.getChildren();
				methodtest_baddecorator150 = ModelTestUtils.getAssertMethod( classTestSorted147Childs, "test_baddecorator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_baddecorator150, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main151;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main151 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen263( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bool.py"));

		assertNotNull("Module test_bool.py not found", module);
		assertEquals("test_bool.py", module.getElementName());
		
		//Class test
		{
		IType classBoolTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classBoolTest0 = ModelTestUtils.getAssertClass( moduleChilds, "BoolTest" );
			//Function test:assertIs
			{
			IMethod methodassertIs1;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodassertIs1 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "assertIs", 3 );
				ModelTestUtils.assertParameterNames( methodassertIs1, new String[] {"self", "a", "b"} );
			}
			//Function test:assertIsNot
			{
			IMethod methodassertIsNot2;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodassertIsNot2 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "assertIsNot", 3 );
				ModelTestUtils.assertParameterNames( methodassertIsNot2, new String[] {"self", "a", "b"} );
			}
			//Function test:test_subclass
			{
			IMethod methodtest_subclass3;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_subclass3 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass3, new String[] {"self"} );
				//Class test
				{
				IType classC4;
					IModelElement[] methodtest_subclass3Childs = methodtest_subclass3.getChildren();
					classC4 = ModelTestUtils.getAssertClass( methodtest_subclass3Childs, "C" );
				}
			}
			//Function test:test_print
			{
			IMethod methodtest_print5;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_print5 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_print", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print5, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr6;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_repr6 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr6, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str7;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_str7 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str7, new String[] {"self"} );
			}
			//Function test:test_int
			{
			IMethod methodtest_int8;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_int8 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int8, new String[] {"self"} );
			}
			//Function test:test_math
			{
			IMethod methodtest_math9;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_math9 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_math", 1 );
				ModelTestUtils.assertParameterNames( methodtest_math9, new String[] {"self"} );
			}
			//Function test:test_convert
			{
			IMethod methodtest_convert10;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_convert10 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_convert", 1 );
				ModelTestUtils.assertParameterNames( methodtest_convert10, new String[] {"self"} );
			}
			//Function test:test_hasattr
			{
			IMethod methodtest_hasattr11;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_hasattr11 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_hasattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hasattr11, new String[] {"self"} );
			}
			//Function test:test_callable
			{
			IMethod methodtest_callable12;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_callable12 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callable12, new String[] {"self"} );
			}
			//Function test:test_isinstance
			{
			IMethod methodtest_isinstance13;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_isinstance13 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_isinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance13, new String[] {"self"} );
			}
			//Function test:test_issubclass
			{
			IMethod methodtest_issubclass14;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_issubclass14 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_issubclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubclass14, new String[] {"self"} );
			}
			//Function test:test_haskey
			{
			IMethod methodtest_haskey15;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_haskey15 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_haskey", 1 );
				ModelTestUtils.assertParameterNames( methodtest_haskey15, new String[] {"self"} );
			}
			//Function test:test_string
			{
			IMethod methodtest_string16;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_string16 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_string16, new String[] {"self"} );
			}
			//Function test:test_boolean
			{
			IMethod methodtest_boolean17;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_boolean17 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_boolean", 1 );
				ModelTestUtils.assertParameterNames( methodtest_boolean17, new String[] {"self"} );
			}
			//Function test:test_fileclosed
			{
			IMethod methodtest_fileclosed18;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_fileclosed18 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_fileclosed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fileclosed18, new String[] {"self"} );
			}
			//Function test:test_operator
			{
			IMethod methodtest_operator19;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_operator19 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_operator19, new String[] {"self"} );
			}
			//Function test:test_marshal
			{
			IMethod methodtest_marshal20;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_marshal20 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_marshal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_marshal20, new String[] {"self"} );
			}
			//Function test:test_pickle
			{
			IMethod methodtest_pickle21;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_pickle21 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_pickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickle21, new String[] {"self"} );
			}
			//Function test:test_cpickle
			{
			IMethod methodtest_cpickle22;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_cpickle22 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_cpickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cpickle22, new String[] {"self"} );
			}
			//Function test:test_mixedpickle
			{
			IMethod methodtest_mixedpickle23;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_mixedpickle23 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_mixedpickle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixedpickle23, new String[] {"self"} );
			}
			//Function test:test_picklevalues
			{
			IMethod methodtest_picklevalues24;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_picklevalues24 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_picklevalues", 1 );
				ModelTestUtils.assertParameterNames( methodtest_picklevalues24, new String[] {"self"} );
			}
			//Function test:test_convert_to_bool
			{
			IMethod methodtest_convert_to_bool25;
				IModelElement[] classBoolTest0Childs = classBoolTest0.getChildren();
				methodtest_convert_to_bool25 = ModelTestUtils.getAssertMethod( classBoolTest0Childs, "test_convert_to_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_convert_to_bool25, new String[] {"self"} );
				//Class test
				{
				IType classFoo26;
					IModelElement[] methodtest_convert_to_bool25Childs = methodtest_convert_to_bool25.getChildren();
					classFoo26 = ModelTestUtils.getAssertClass( methodtest_convert_to_bool25Childs, "Foo" );
					//Function test:__nonzero__
					{
					IMethod method__nonzero__27;
						IModelElement[] classFoo26Childs = classFoo26.getChildren();
						method__nonzero__27 = ModelTestUtils.getAssertMethod( classFoo26Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__27, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBar28;
					IModelElement[] methodtest_convert_to_bool25Childs = methodtest_convert_to_bool25.getChildren();
					classBar28 = ModelTestUtils.getAssertClass( methodtest_convert_to_bool25Childs, "Bar" );
					//Function test:__nonzero__
					{
					IMethod method__nonzero__29;
						IModelElement[] classBar28Childs = classBar28.getChildren();
						method__nonzero__29 = ModelTestUtils.getAssertMethod( classBar28Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__29, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBaz30;
					IModelElement[] methodtest_convert_to_bool25Childs = methodtest_convert_to_bool25.getChildren();
					classBaz30 = ModelTestUtils.getAssertClass( methodtest_convert_to_bool25Childs, "Baz" );
					//Function test:__nonzero__
					{
					IMethod method__nonzero__31;
						IModelElement[] classBaz30Childs = classBaz30.getChildren();
						method__nonzero__31 = ModelTestUtils.getAssertMethod( classBaz30Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__31, new String[] {"self"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main32;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main32 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen264( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_getargs2.py"));

		assertNotNull("Module test_getargs2.py not found", module);
		assertEquals("test_getargs2.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LARGE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "VERY_LARGE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LLONG_MAX");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LLONG_MIN");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ULLONG_MAX");
		}
		//Class test
		{
		IType classLong0;
			IModelElement[] moduleChilds = module.getChildren();
			classLong0 = ModelTestUtils.getAssertClass( moduleChilds, "Long" );
			//Function test:__int__
			{
			IMethod method__int__1;
				IModelElement[] classLong0Childs = classLong0.getChildren();
				method__int__1 = ModelTestUtils.getAssertMethod( classLong0Childs, "__int__", 1 );
				ModelTestUtils.assertParameterNames( method__int__1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classInt2;
			IModelElement[] moduleChilds = module.getChildren();
			classInt2 = ModelTestUtils.getAssertClass( moduleChilds, "Int" );
			//Function test:__int__
			{
			IMethod method__int__3;
				IModelElement[] classInt2Childs = classInt2.getChildren();
				method__int__3 = ModelTestUtils.getAssertMethod( classInt2Childs, "__int__", 1 );
				ModelTestUtils.assertParameterNames( method__int__3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnsigned_TestCase4;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsigned_TestCase4 = ModelTestUtils.getAssertClass( moduleChilds, "Unsigned_TestCase" );
			//Function test:test_b
			{
			IMethod methodtest_b5;
				IModelElement[] classUnsigned_TestCase4Childs = classUnsigned_TestCase4.getChildren();
				methodtest_b5 = ModelTestUtils.getAssertMethod( classUnsigned_TestCase4Childs, "test_b", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b5, new String[] {"self"} );
			}
			//Function test:test_B
			{
			IMethod methodtest_B6;
				IModelElement[] classUnsigned_TestCase4Childs = classUnsigned_TestCase4.getChildren();
				methodtest_B6 = ModelTestUtils.getAssertMethod( classUnsigned_TestCase4Childs, "test_B", 1 );
				ModelTestUtils.assertParameterNames( methodtest_B6, new String[] {"self"} );
			}
			//Function test:test_H
			{
			IMethod methodtest_H7;
				IModelElement[] classUnsigned_TestCase4Childs = classUnsigned_TestCase4.getChildren();
				methodtest_H7 = ModelTestUtils.getAssertMethod( classUnsigned_TestCase4Childs, "test_H", 1 );
				ModelTestUtils.assertParameterNames( methodtest_H7, new String[] {"self"} );
			}
			//Function test:test_I
			{
			IMethod methodtest_I8;
				IModelElement[] classUnsigned_TestCase4Childs = classUnsigned_TestCase4.getChildren();
				methodtest_I8 = ModelTestUtils.getAssertMethod( classUnsigned_TestCase4Childs, "test_I", 1 );
				ModelTestUtils.assertParameterNames( methodtest_I8, new String[] {"self"} );
			}
			//Function test:test_k
			{
			IMethod methodtest_k9;
				IModelElement[] classUnsigned_TestCase4Childs = classUnsigned_TestCase4.getChildren();
				methodtest_k9 = ModelTestUtils.getAssertMethod( classUnsigned_TestCase4Childs, "test_k", 1 );
				ModelTestUtils.assertParameterNames( methodtest_k9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSigned_TestCase10;
			IModelElement[] moduleChilds = module.getChildren();
			classSigned_TestCase10 = ModelTestUtils.getAssertClass( moduleChilds, "Signed_TestCase" );
			//Function test:test_i
			{
			IMethod methodtest_i11;
				IModelElement[] classSigned_TestCase10Childs = classSigned_TestCase10.getChildren();
				methodtest_i11 = ModelTestUtils.getAssertMethod( classSigned_TestCase10Childs, "test_i", 1 );
				ModelTestUtils.assertParameterNames( methodtest_i11, new String[] {"self"} );
			}
			//Function test:test_l
			{
			IMethod methodtest_l12;
				IModelElement[] classSigned_TestCase10Childs = classSigned_TestCase10.getChildren();
				methodtest_l12 = ModelTestUtils.getAssertMethod( classSigned_TestCase10Childs, "test_l", 1 );
				ModelTestUtils.assertParameterNames( methodtest_l12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLongLong_TestCase13;
			IModelElement[] moduleChilds = module.getChildren();
			classLongLong_TestCase13 = ModelTestUtils.getAssertClass( moduleChilds, "LongLong_TestCase" );
			//Function test:test_L
			{
			IMethod methodtest_L14;
				IModelElement[] classLongLong_TestCase13Childs = classLongLong_TestCase13.getChildren();
				methodtest_L14 = ModelTestUtils.getAssertMethod( classLongLong_TestCase13Childs, "test_L", 1 );
				ModelTestUtils.assertParameterNames( methodtest_L14, new String[] {"self"} );
			}
			//Function test:test_K
			{
			IMethod methodtest_K15;
				IModelElement[] classLongLong_TestCase13Childs = classLongLong_TestCase13.getChildren();
				methodtest_K15 = ModelTestUtils.getAssertMethod( classLongLong_TestCase13Childs, "test_K", 1 );
				ModelTestUtils.assertParameterNames( methodtest_K15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen265( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_signal.py"));

		assertNotNull("Module test_signal.py not found", module);
		assertEquals("test_signal.py", module.getElementName());
		
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pid");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "script");
		}
		//Function test:handlerA
		{
		IMethod methodhandlerA0;
			IModelElement[] moduleChilds = module.getChildren();
			methodhandlerA0 = ModelTestUtils.getAssertMethod( moduleChilds, "handlerA", 1 );
			ModelTestUtils.assertParameterNames( methodhandlerA0, new String[] {"args"} );
		}
		//Class test
		{
		IType classHandlerBCalled1;
			IModelElement[] moduleChilds = module.getChildren();
			classHandlerBCalled1 = ModelTestUtils.getAssertClass( moduleChilds, "HandlerBCalled" );
		}
		//Function test:handlerB
		{
		IMethod methodhandlerB2;
			IModelElement[] moduleChilds = module.getChildren();
			methodhandlerB2 = ModelTestUtils.getAssertMethod( moduleChilds, "handlerB", 1 );
			ModelTestUtils.assertParameterNames( methodhandlerB2, new String[] {"args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "hup");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "usr1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "usr2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "alrm");
		}

	}
	public void testModelGen266( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_opcodes.py"));

		assertNotNull("Module test_opcodes.py not found", module);
		assertEquals("test_opcodes.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
		}
		//Class test
		{
		IType classAClass0;
			IModelElement[] moduleChilds = module.getChildren();
			classAClass0 = ModelTestUtils.getAssertClass( moduleChilds, "AClass" );
		}
		//Class test
		{
		IType classBClass1;
			IModelElement[] moduleChilds = module.getChildren();
			classBClass1 = ModelTestUtils.getAssertClass( moduleChilds, "BClass" );
		}
		//Class test
		{
		IType classCClass2;
			IModelElement[] moduleChilds = module.getChildren();
			classCClass2 = ModelTestUtils.getAssertClass( moduleChilds, "CClass" );
		}
		//Class test
		{
		IType classDClass3;
			IModelElement[] moduleChilds = module.getChildren();
			classDClass3 = ModelTestUtils.getAssertClass( moduleChilds, "DClass" );
			//Function test:__init__
			{
			IMethod method__init__4;
				IModelElement[] classDClass3Childs = classDClass3.getChildren();
				method__init__4 = ModelTestUtils.getAssertMethod( classDClass3Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__4, new String[] {"self", "ignore"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "b");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}

	}
	public void testModelGen267( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_coercion.py"));

		assertNotNull("Module test_coercion.py not found", module);
		assertEquals("test_coercion.py", module.getElementName());
		
		//Class test
		{
		IType classCoerceNumber0;
			IModelElement[] moduleChilds = module.getChildren();
			classCoerceNumber0 = ModelTestUtils.getAssertClass( moduleChilds, "CoerceNumber" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classCoerceNumber0Childs = classCoerceNumber0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classCoerceNumber0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__2;
				IModelElement[] classCoerceNumber0Childs = classCoerceNumber0.getChildren();
				method__repr__2 = ModelTestUtils.getAssertMethod( classCoerceNumber0Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__2, new String[] {"self"} );
			}
			//Function test:__coerce__
			{
			IMethod method__coerce__3;
				IModelElement[] classCoerceNumber0Childs = classCoerceNumber0.getChildren();
				method__coerce__3 = ModelTestUtils.getAssertMethod( classCoerceNumber0Childs, "__coerce__", 2 );
				ModelTestUtils.assertParameterNames( method__coerce__3, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classMethodNumber4;
			IModelElement[] moduleChilds = module.getChildren();
			classMethodNumber4 = ModelTestUtils.getAssertClass( moduleChilds, "MethodNumber" );
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__6;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__repr__6 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__6, new String[] {"self"} );
			}
			//Function test:__add__
			{
			IMethod method__add__7;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__add__7 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__7, new String[] {"self", "other"} );
			}
			//Function test:__radd__
			{
			IMethod method__radd__8;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__radd__8 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__radd__", 2 );
				ModelTestUtils.assertParameterNames( method__radd__8, new String[] {"self", "other"} );
			}
			//Function test:__sub__
			{
			IMethod method__sub__9;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__sub__9 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__sub__", 2 );
				ModelTestUtils.assertParameterNames( method__sub__9, new String[] {"self", "other"} );
			}
			//Function test:__rsub__
			{
			IMethod method__rsub__10;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__rsub__10 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__rsub__", 2 );
				ModelTestUtils.assertParameterNames( method__rsub__10, new String[] {"self", "other"} );
			}
			//Function test:__mul__
			{
			IMethod method__mul__11;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__mul__11 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__mul__", 2 );
				ModelTestUtils.assertParameterNames( method__mul__11, new String[] {"self", "other"} );
			}
			//Function test:__rmul__
			{
			IMethod method__rmul__12;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__rmul__12 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__rmul__", 2 );
				ModelTestUtils.assertParameterNames( method__rmul__12, new String[] {"self", "other"} );
			}
			//Function test:__div__
			{
			IMethod method__div__13;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__div__13 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__div__", 2 );
				ModelTestUtils.assertParameterNames( method__div__13, new String[] {"self", "other"} );
			}
			//Function test:__rdiv__
			{
			IMethod method__rdiv__14;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__rdiv__14 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__rdiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rdiv__14, new String[] {"self", "other"} );
			}
			//Function test:__pow__
			{
			IMethod method__pow__15;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__pow__15 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__pow__", 2 );
				ModelTestUtils.assertParameterNames( method__pow__15, new String[] {"self", "other"} );
			}
			//Function test:__rpow__
			{
			IMethod method__rpow__16;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__rpow__16 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__rpow__", 2 );
				ModelTestUtils.assertParameterNames( method__rpow__16, new String[] {"self", "other"} );
			}
			//Function test:__mod__
			{
			IMethod method__mod__17;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__mod__17 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__mod__", 2 );
				ModelTestUtils.assertParameterNames( method__mod__17, new String[] {"self", "other"} );
			}
			//Function test:__rmod__
			{
			IMethod method__rmod__18;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__rmod__18 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__rmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rmod__18, new String[] {"self", "other"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__19;
				IModelElement[] classMethodNumber4Childs = classMethodNumber4.getChildren();
				method__cmp__19 = ModelTestUtils.getAssertMethod( classMethodNumber4Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__19, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "candidates");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "infix_binops");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "prefix_binops");
		}
		//Function test:format_float
		{
		IMethod methodformat_float20;
			IModelElement[] moduleChilds = module.getChildren();
			methodformat_float20 = ModelTestUtils.getAssertMethod( moduleChilds, "format_float", 1 );
			ModelTestUtils.assertParameterNames( methodformat_float20, new String[] {"value"} );
		}
		//Function test:format_result
		{
		IMethod methodformat_result21;
			IModelElement[] moduleChilds = module.getChildren();
			methodformat_result21 = ModelTestUtils.getAssertMethod( moduleChilds, "format_result", 1 );
			ModelTestUtils.assertParameterNames( methodformat_result21, new String[] {"value"} );
		}
		//Function test:do_infix_binops
		{
		IMethod methoddo_infix_binops22;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_infix_binops22 = ModelTestUtils.getAssertMethod( moduleChilds, "do_infix_binops", 0 );
		}
		//Function test:do_prefix_binops
		{
		IMethod methoddo_prefix_binops23;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_prefix_binops23 = ModelTestUtils.getAssertMethod( moduleChilds, "do_prefix_binops", 0 );
		}

	}
	public void testModelGen268( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_unicode_file.py"));

		assertNotNull("Module test_unicode_file.py not found", module);
		assertEquals("test_unicode_file.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_ENCODED");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_UNICODE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTFN_ENCODED");
		}
		//Function test:remove_if_exists
		{
		IMethod methodremove_if_exists0;
			IModelElement[] moduleChilds = module.getChildren();
			methodremove_if_exists0 = ModelTestUtils.getAssertMethod( moduleChilds, "remove_if_exists", 1 );
			ModelTestUtils.assertParameterNames( methodremove_if_exists0, new String[] {"filename"} );
		}
		//Class test
		{
		IType classTestUnicodeFiles1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestUnicodeFiles1 = ModelTestUtils.getAssertClass( moduleChilds, "TestUnicodeFiles" );
			//Function test:_do_single
			{
			IMethod method_do_single2;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				method_do_single2 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "_do_single", 2 );
				ModelTestUtils.assertParameterNames( method_do_single2, new String[] {"self", "filename"} );
			}
			//Function test:_do_equivilent
			{
			IMethod method_do_equivilent3;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				method_do_equivilent3 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "_do_equivilent", 3 );
				ModelTestUtils.assertParameterNames( method_do_equivilent3, new String[] {"self", "filename1", "filename2"} );
			}
			//Function test:_do_copyish
			{
			IMethod method_do_copyish4;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				method_do_copyish4 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "_do_copyish", 3 );
				ModelTestUtils.assertParameterNames( method_do_copyish4, new String[] {"self", "filename1", "filename2"} );
			}
			//Function test:_do_directory
			{
			IMethod method_do_directory5;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				method_do_directory5 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "_do_directory", 4 );
				ModelTestUtils.assertParameterNames( method_do_directory5, new String[] {"self", "make_name", "chdir_name", "encoded"} );
			}
			//Function test:_test_single
			{
			IMethod method_test_single6;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				method_test_single6 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "_test_single", 2 );
				ModelTestUtils.assertParameterNames( method_test_single6, new String[] {"self", "filename"} );
			}
			//Function test:_test_equivalent
			{
			IMethod method_test_equivalent7;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				method_test_equivalent7 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "_test_equivalent", 3 );
				ModelTestUtils.assertParameterNames( method_test_equivalent7, new String[] {"self", "filename1", "filename2"} );
			}
			//Function test:test_single_files
			{
			IMethod methodtest_single_files8;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				methodtest_single_files8 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "test_single_files", 1 );
				ModelTestUtils.assertParameterNames( methodtest_single_files8, new String[] {"self"} );
			}
			//Function test:test_equivalent_files
			{
			IMethod methodtest_equivalent_files9;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				methodtest_equivalent_files9 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "test_equivalent_files", 1 );
				ModelTestUtils.assertParameterNames( methodtest_equivalent_files9, new String[] {"self"} );
			}
			//Function test:test_directories
			{
			IMethod methodtest_directories10;
				IModelElement[] classTestUnicodeFiles1Childs = classTestUnicodeFiles1.getChildren();
				methodtest_directories10 = ModelTestUtils.getAssertMethod( classTestUnicodeFiles1Childs, "test_directories", 1 );
				ModelTestUtils.assertParameterNames( methodtest_directories10, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen269( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_textwrap.py"));

		assertNotNull("Module test_textwrap.py not found", module);
		assertEquals("test_textwrap.py", module.getElementName());
		
		//Class test
		{
		IType classBaseTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "BaseTestCase" );
			//Function test:show
			{
			IMethod methodshow1;
				IModelElement[] classBaseTestCase0Childs = classBaseTestCase0.getChildren();
				methodshow1 = ModelTestUtils.getAssertMethod( classBaseTestCase0Childs, "show", 2 );
				ModelTestUtils.assertParameterNames( methodshow1, new String[] {"self", "textin"} );
			}
			//Function test:check
			{
			IMethod methodcheck2;
				IModelElement[] classBaseTestCase0Childs = classBaseTestCase0.getChildren();
				methodcheck2 = ModelTestUtils.getAssertMethod( classBaseTestCase0Childs, "check", 3 );
				ModelTestUtils.assertParameterNames( methodcheck2, new String[] {"self", "result", "expect"} );
			}
			//Function test:check_wrap
			{
			IMethod methodcheck_wrap3;
				IModelElement[] classBaseTestCase0Childs = classBaseTestCase0.getChildren();
				methodcheck_wrap3 = ModelTestUtils.getAssertMethod( classBaseTestCase0Childs, "check_wrap", 5 );
				ModelTestUtils.assertParameterNames( methodcheck_wrap3, new String[] {"self", "text", "width", "expect", "kwargs"} );
			}
			//Function test:check_split
			{
			IMethod methodcheck_split4;
				IModelElement[] classBaseTestCase0Childs = classBaseTestCase0.getChildren();
				methodcheck_split4 = ModelTestUtils.getAssertMethod( classBaseTestCase0Childs, "check_split", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_split4, new String[] {"self", "text", "expect"} );
			}
		}
		//Class test
		{
		IType classWrapTestCase5;
			IModelElement[] moduleChilds = module.getChildren();
			classWrapTestCase5 = ModelTestUtils.getAssertClass( moduleChilds, "WrapTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp6;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodsetUp6 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp6, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple7;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_simple7 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple7, new String[] {"self"} );
			}
			//Function test:test_whitespace
			{
			IMethod methodtest_whitespace8;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_whitespace8 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_whitespace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_whitespace8, new String[] {"self"} );
			}
			//Function test:test_fix_sentence_endings
			{
			IMethod methodtest_fix_sentence_endings9;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_fix_sentence_endings9 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_fix_sentence_endings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fix_sentence_endings9, new String[] {"self"} );
			}
			//Function test:test_wrap_short
			{
			IMethod methodtest_wrap_short10;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_wrap_short10 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_wrap_short", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrap_short10, new String[] {"self"} );
			}
			//Function test:test_wrap_short_1line
			{
			IMethod methodtest_wrap_short_1line11;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_wrap_short_1line11 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_wrap_short_1line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrap_short_1line11, new String[] {"self"} );
			}
			//Function test:test_hyphenated
			{
			IMethod methodtest_hyphenated12;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_hyphenated12 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_hyphenated", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hyphenated12, new String[] {"self"} );
			}
			//Function test:test_hyphenated_numbers
			{
			IMethod methodtest_hyphenated_numbers13;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_hyphenated_numbers13 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_hyphenated_numbers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hyphenated_numbers13, new String[] {"self"} );
			}
			//Function test:test_em_dash
			{
			IMethod methodtest_em_dash14;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_em_dash14 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_em_dash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_em_dash14, new String[] {"self"} );
			}
			//Function test:test_unix_options
			{
			IMethod methodtest_unix_options15;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_unix_options15 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_unix_options", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unix_options15, new String[] {"self"} );
			}
			//Function test:test_funky_hyphens
			{
			IMethod methodtest_funky_hyphens16;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_funky_hyphens16 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_funky_hyphens", 1 );
				ModelTestUtils.assertParameterNames( methodtest_funky_hyphens16, new String[] {"self"} );
			}
			//Function test:test_punct_hyphens
			{
			IMethod methodtest_punct_hyphens17;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_punct_hyphens17 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_punct_hyphens", 1 );
				ModelTestUtils.assertParameterNames( methodtest_punct_hyphens17, new String[] {"self"} );
			}
			//Function test:test_funky_parens
			{
			IMethod methodtest_funky_parens18;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_funky_parens18 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_funky_parens", 1 );
				ModelTestUtils.assertParameterNames( methodtest_funky_parens18, new String[] {"self"} );
			}
			//Function test:test_initial_whitespace
			{
			IMethod methodtest_initial_whitespace19;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_initial_whitespace19 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_initial_whitespace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_initial_whitespace19, new String[] {"self"} );
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode20;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_unicode20 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode20, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split21;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_split21 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split21, new String[] {"self"} );
			}
			//Function test:test_bad_width
			{
			IMethod methodtest_bad_width22;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_bad_width22 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_bad_width", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_width22, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLongWordTestCase23;
			IModelElement[] moduleChilds = module.getChildren();
			classLongWordTestCase23 = ModelTestUtils.getAssertClass( moduleChilds, "LongWordTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp24;
				IModelElement[] classLongWordTestCase23Childs = classLongWordTestCase23.getChildren();
				methodsetUp24 = ModelTestUtils.getAssertMethod( classLongWordTestCase23Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp24, new String[] {"self"} );
			}
			//Function test:test_break_long
			{
			IMethod methodtest_break_long25;
				IModelElement[] classLongWordTestCase23Childs = classLongWordTestCase23.getChildren();
				methodtest_break_long25 = ModelTestUtils.getAssertMethod( classLongWordTestCase23Childs, "test_break_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_break_long25, new String[] {"self"} );
			}
			//Function test:test_nobreak_long
			{
			IMethod methodtest_nobreak_long26;
				IModelElement[] classLongWordTestCase23Childs = classLongWordTestCase23.getChildren();
				methodtest_nobreak_long26 = ModelTestUtils.getAssertMethod( classLongWordTestCase23Childs, "test_nobreak_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nobreak_long26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIndentTestCases27;
			IModelElement[] moduleChilds = module.getChildren();
			classIndentTestCases27 = ModelTestUtils.getAssertClass( moduleChilds, "IndentTestCases" );
			//Function test:setUp
			{
			IMethod methodsetUp28;
				IModelElement[] classIndentTestCases27Childs = classIndentTestCases27.getChildren();
				methodsetUp28 = ModelTestUtils.getAssertMethod( classIndentTestCases27Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp28, new String[] {"self"} );
			}
			//Function test:test_fill
			{
			IMethod methodtest_fill29;
				IModelElement[] classIndentTestCases27Childs = classIndentTestCases27.getChildren();
				methodtest_fill29 = ModelTestUtils.getAssertMethod( classIndentTestCases27Childs, "test_fill", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fill29, new String[] {"self"} );
			}
			//Function test:test_initial_indent
			{
			IMethod methodtest_initial_indent30;
				IModelElement[] classIndentTestCases27Childs = classIndentTestCases27.getChildren();
				methodtest_initial_indent30 = ModelTestUtils.getAssertMethod( classIndentTestCases27Childs, "test_initial_indent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_initial_indent30, new String[] {"self"} );
			}
			//Function test:test_subsequent_indent
			{
			IMethod methodtest_subsequent_indent31;
				IModelElement[] classIndentTestCases27Childs = classIndentTestCases27.getChildren();
				methodtest_subsequent_indent31 = ModelTestUtils.getAssertMethod( classIndentTestCases27Childs, "test_subsequent_indent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subsequent_indent31, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDedentTestCase32;
			IModelElement[] moduleChilds = module.getChildren();
			classDedentTestCase32 = ModelTestUtils.getAssertClass( moduleChilds, "DedentTestCase" );
			//Function test:test_dedent_nomargin
			{
			IMethod methodtest_dedent_nomargin33;
				IModelElement[] classDedentTestCase32Childs = classDedentTestCase32.getChildren();
				methodtest_dedent_nomargin33 = ModelTestUtils.getAssertMethod( classDedentTestCase32Childs, "test_dedent_nomargin", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dedent_nomargin33, new String[] {"self"} );
			}
			//Function test:test_dedent_even
			{
			IMethod methodtest_dedent_even34;
				IModelElement[] classDedentTestCase32Childs = classDedentTestCase32.getChildren();
				methodtest_dedent_even34 = ModelTestUtils.getAssertMethod( classDedentTestCase32Childs, "test_dedent_even", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dedent_even34, new String[] {"self"} );
			}
			//Function test:test_dedent_uneven
			{
			IMethod methodtest_dedent_uneven35;
				IModelElement[] classDedentTestCase32Childs = classDedentTestCase32.getChildren();
				methodtest_dedent_uneven35 = ModelTestUtils.getAssertMethod( classDedentTestCase32Childs, "test_dedent_uneven", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dedent_uneven35, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main36 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen270( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_macpath.py"));

		assertNotNull("Module test_macpath.py not found", module);
		assertEquals("test_macpath.py", module.getElementName());
		
		//Class test
		{
		IType classMacPathTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classMacPathTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "MacPathTestCase" );
			//Function test:test_abspath
			{
			IMethod methodtest_abspath1;
				IModelElement[] classMacPathTestCase0Childs = classMacPathTestCase0.getChildren();
				methodtest_abspath1 = ModelTestUtils.getAssertMethod( classMacPathTestCase0Childs, "test_abspath", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abspath1, new String[] {"self"} );
			}
			//Function test:test_isabs
			{
			IMethod methodtest_isabs2;
				IModelElement[] classMacPathTestCase0Childs = classMacPathTestCase0.getChildren();
				methodtest_isabs2 = ModelTestUtils.getAssertMethod( classMacPathTestCase0Childs, "test_isabs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isabs2, new String[] {"self"} );
			}
			//Function test:test_commonprefix
			{
			IMethod methodtest_commonprefix3;
				IModelElement[] classMacPathTestCase0Childs = classMacPathTestCase0.getChildren();
				methodtest_commonprefix3 = ModelTestUtils.getAssertMethod( classMacPathTestCase0Childs, "test_commonprefix", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commonprefix3, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split4;
				IModelElement[] classMacPathTestCase0Childs = classMacPathTestCase0.getChildren();
				methodtest_split4 = ModelTestUtils.getAssertMethod( classMacPathTestCase0Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split4, new String[] {"self"} );
			}
			//Function test:test_splitdrive
			{
			IMethod methodtest_splitdrive5;
				IModelElement[] classMacPathTestCase0Childs = classMacPathTestCase0.getChildren();
				methodtest_splitdrive5 = ModelTestUtils.getAssertMethod( classMacPathTestCase0Childs, "test_splitdrive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitdrive5, new String[] {"self"} );
			}
			//Function test:test_splitext
			{
			IMethod methodtest_splitext6;
				IModelElement[] classMacPathTestCase0Childs = classMacPathTestCase0.getChildren();
				methodtest_splitext6 = ModelTestUtils.getAssertMethod( classMacPathTestCase0Childs, "test_splitext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitext6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen271( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_funcattrs.py"));

		assertNotNull("Module test_funcattrs.py not found", module);
		assertEquals("test_funcattrs.py", module.getElementName());
		
		//Class test
		{
		IType classF0;
			IModelElement[] moduleChilds = module.getChildren();
			classF0 = ModelTestUtils.getAssertClass( moduleChilds, "F" );
			//Function test:a
			{
			IMethod methoda1;
				IModelElement[] classF0Childs = classF0.getChildren();
				methoda1 = ModelTestUtils.getAssertMethod( classF0Childs, "a", 1 );
				ModelTestUtils.assertParameterNames( methoda1, new String[] {"self"} );
			}
		}
		//Function test:b
		{
		IMethod methodb2;
			IModelElement[] moduleChilds = module.getChildren();
			methodb2 = ModelTestUtils.getAssertMethod( moduleChilds, "b", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "docstring");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "eff");
		}
		//Function test:another
		{
		IMethod methodanother3;
			IModelElement[] moduleChilds = module.getChildren();
			methodanother3 = ModelTestUtils.getAssertMethod( moduleChilds, "another", 0 );
		}
		//Function test:foo
		{
		IMethod methodfoo4;
			IModelElement[] moduleChilds = module.getChildren();
			methodfoo4 = ModelTestUtils.getAssertMethod( moduleChilds, "foo", 0 );
		}
		//Function test:bar
		{
		IMethod methodbar5;
			IModelElement[] moduleChilds = module.getChildren();
			methodbar5 = ModelTestUtils.getAssertMethod( moduleChilds, "bar", 0 );
		}
		//Function test:temp
		{
		IMethod methodtemp6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtemp6 = ModelTestUtils.getAssertMethod( moduleChilds, "temp", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		//Function test:cantset
		{
		IMethod methodcantset7;
			IModelElement[] moduleChilds = module.getChildren();
			methodcantset7 = ModelTestUtils.getAssertMethod( moduleChilds, "cantset", 4 );
			ModelTestUtils.assertParameterNames( methodcantset7, new String[] {"obj", "name", "value", "exception"} );
		}
		//Function test:test_func_closure
		{
		IMethod methodtest_func_closure8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_closure8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_closure", 0 );
			//Function test:f
			{
			IMethod methodf9;
				IModelElement[] methodtest_func_closure8Childs = methodtest_func_closure8.getChildren();
				methodf9 = ModelTestUtils.getAssertMethod( methodtest_func_closure8Childs, "f", 0 );
			}
		}
		//Function test:test_func_doc
		{
		IMethod methodtest_func_doc10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_doc10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_doc", 0 );
			//Function test:f
			{
			IMethod methodf11;
				IModelElement[] methodtest_func_doc10Childs = methodtest_func_doc10.getChildren();
				methodf11 = ModelTestUtils.getAssertMethod( methodtest_func_doc10Childs, "f", 0 );
			}
		}
		//Function test:test_func_globals
		{
		IMethod methodtest_func_globals12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_globals12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_globals", 0 );
			//Function test:f
			{
			IMethod methodf13;
				IModelElement[] methodtest_func_globals12Childs = methodtest_func_globals12.getChildren();
				methodf13 = ModelTestUtils.getAssertMethod( methodtest_func_globals12Childs, "f", 0 );
			}
		}
		//Function test:test_func_name
		{
		IMethod methodtest_func_name14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_name14 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_name", 0 );
			//Function test:f
			{
			IMethod methodf15;
				IModelElement[] methodtest_func_name14Childs = methodtest_func_name14.getChildren();
				methodf15 = ModelTestUtils.getAssertMethod( methodtest_func_name14Childs, "f", 0 );
			}
		}
		//Function test:test_func_code
		{
		IMethod methodtest_func_code16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_code16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_code", 0 );
			//Function test:f
			{
			IMethod methodf17;
				IModelElement[] methodtest_func_code16Childs = methodtest_func_code16.getChildren();
				methodf17 = ModelTestUtils.getAssertMethod( methodtest_func_code16Childs, "f", 0 );
			}
			//Function test:g
			{
			IMethod methodg18;
				IModelElement[] methodtest_func_code16Childs = methodtest_func_code16.getChildren();
				methodg18 = ModelTestUtils.getAssertMethod( methodtest_func_code16Childs, "g", 0 );
			}
			//Function test:f1
			{
			IMethod methodf119;
				IModelElement[] methodtest_func_code16Childs = methodtest_func_code16.getChildren();
				methodf119 = ModelTestUtils.getAssertMethod( methodtest_func_code16Childs, "f1", 0 );
			}
			//Function test:g1
			{
			IMethod methodg120;
				IModelElement[] methodtest_func_code16Childs = methodtest_func_code16.getChildren();
				methodg120 = ModelTestUtils.getAssertMethod( methodtest_func_code16Childs, "g1", 0 );
			}
			//Function test:f2
			{
			IMethod methodf221;
				IModelElement[] methodtest_func_code16Childs = methodtest_func_code16.getChildren();
				methodf221 = ModelTestUtils.getAssertMethod( methodtest_func_code16Childs, "f2", 0 );
			}
		}
		//Function test:test_func_defaults
		{
		IMethod methodtest_func_defaults22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_defaults22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_defaults", 0 );
			//Function test:f
			{
			IMethod methodf23;
				IModelElement[] methodtest_func_defaults22Childs = methodtest_func_defaults22.getChildren();
				methodf23 = ModelTestUtils.getAssertMethod( methodtest_func_defaults22Childs, "f", 2 );
				ModelTestUtils.assertParameterNames( methodf23, new String[] {"a", "b"} );
			}
			//Function test:g
			{
			IMethod methodg24;
				IModelElement[] methodtest_func_defaults22Childs = methodtest_func_defaults22.getChildren();
				methodg24 = ModelTestUtils.getAssertMethod( methodtest_func_defaults22Childs, "g", 2 );
				ModelTestUtils.assertParameterNames( methodg24, new String[] {"a", "b"} );
			}
		}
		//Function test:test_func_dict
		{
		IMethod methodtest_func_dict25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_func_dict25 = ModelTestUtils.getAssertMethod( moduleChilds, "test_func_dict", 0 );
			//Function test:f
			{
			IMethod methodf26;
				IModelElement[] methodtest_func_dict25Childs = methodtest_func_dict25.getChildren();
				methodf26 = ModelTestUtils.getAssertMethod( methodtest_func_dict25Childs, "f", 0 );
			}
		}
		//Function test:test_im_class
		{
		IMethod methodtest_im_class27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_im_class27 = ModelTestUtils.getAssertMethod( moduleChilds, "test_im_class", 0 );
			//Class test
			{
			IType classC28;
				IModelElement[] methodtest_im_class27Childs = methodtest_im_class27.getChildren();
				classC28 = ModelTestUtils.getAssertClass( methodtest_im_class27Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo29;
					IModelElement[] classC28Childs = classC28.getChildren();
					methodfoo29 = ModelTestUtils.getAssertMethod( classC28Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo29, new String[] {"self"} );
				}
			}
		}
		//Function test:test_im_func
		{
		IMethod methodtest_im_func30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_im_func30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_im_func", 0 );
			//Function test:foo
			{
			IMethod methodfoo31;
				IModelElement[] methodtest_im_func30Childs = methodtest_im_func30.getChildren();
				methodfoo31 = ModelTestUtils.getAssertMethod( methodtest_im_func30Childs, "foo", 1 );
				ModelTestUtils.assertParameterNames( methodfoo31, new String[] {"self"} );
			}
			//Class test
			{
			IType classC32;
				IModelElement[] methodtest_im_func30Childs = methodtest_im_func30.getChildren();
				classC32 = ModelTestUtils.getAssertClass( methodtest_im_func30Childs, "C" );
			}
		}
		//Function test:test_im_self
		{
		IMethod methodtest_im_self33;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_im_self33 = ModelTestUtils.getAssertMethod( moduleChilds, "test_im_self", 0 );
			//Class test
			{
			IType classC34;
				IModelElement[] methodtest_im_self33Childs = methodtest_im_self33.getChildren();
				classC34 = ModelTestUtils.getAssertClass( methodtest_im_self33Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo35;
					IModelElement[] classC34Childs = classC34.getChildren();
					methodfoo35 = ModelTestUtils.getAssertMethod( classC34Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo35, new String[] {"self"} );
				}
			}
		}
		//Function test:test_im_dict
		{
		IMethod methodtest_im_dict36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_im_dict36 = ModelTestUtils.getAssertMethod( moduleChilds, "test_im_dict", 0 );
			//Class test
			{
			IType classC37;
				IModelElement[] methodtest_im_dict36Childs = methodtest_im_dict36.getChildren();
				classC37 = ModelTestUtils.getAssertClass( methodtest_im_dict36Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo38;
					IModelElement[] classC37Childs = classC37.getChildren();
					methodfoo38 = ModelTestUtils.getAssertMethod( classC37Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo38, new String[] {"self"} );
				}
			}
		}
		//Function test:test_im_doc
		{
		IMethod methodtest_im_doc39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_im_doc39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_im_doc", 0 );
			//Class test
			{
			IType classC40;
				IModelElement[] methodtest_im_doc39Childs = methodtest_im_doc39.getChildren();
				classC40 = ModelTestUtils.getAssertClass( methodtest_im_doc39Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo41;
					IModelElement[] classC40Childs = classC40.getChildren();
					methodfoo41 = ModelTestUtils.getAssertMethod( classC40Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo41, new String[] {"self"} );
				}
			}
		}
		//Function test:test_im_name
		{
		IMethod methodtest_im_name42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_im_name42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_im_name", 0 );
			//Class test
			{
			IType classC43;
				IModelElement[] methodtest_im_name42Childs = methodtest_im_name42.getChildren();
				classC43 = ModelTestUtils.getAssertClass( methodtest_im_name42Childs, "C" );
				//Function test:foo
				{
				IMethod methodfoo44;
					IModelElement[] classC43Childs = classC43.getChildren();
					methodfoo44 = ModelTestUtils.getAssertMethod( classC43Childs, "foo", 1 );
					ModelTestUtils.assertParameterNames( methodfoo44, new String[] {"self"} );
				}
			}
		}
		//Function test:testmore
		{
		IMethod methodtestmore45;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestmore45 = ModelTestUtils.getAssertMethod( moduleChilds, "testmore", 0 );
		}

	}
	public void testModelGen272( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_mailbox.py"));

		assertNotNull("Module test_mailbox.py not found", module);
		assertEquals("test_mailbox.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "FROM_");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DUMMY_MESSAGE");
		}
		//Class test
		{
		IType classMaildirTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classMaildirTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "MaildirTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:createMessage
			{
			IMethod methodcreateMessage3;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodcreateMessage3 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "createMessage", 3 );
				ModelTestUtils.assertParameterNames( methodcreateMessage3, new String[] {"self", "dir", "mbox"} );
			}
			//Function test:test_empty_maildir
			{
			IMethod methodtest_empty_maildir4;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_empty_maildir4 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_empty_maildir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_maildir4, new String[] {"self"} );
			}
			//Function test:test_nonempty_maildir_cur
			{
			IMethod methodtest_nonempty_maildir_cur5;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_nonempty_maildir_cur5 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_nonempty_maildir_cur", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_maildir_cur5, new String[] {"self"} );
			}
			//Function test:test_nonempty_maildir_new
			{
			IMethod methodtest_nonempty_maildir_new6;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_nonempty_maildir_new6 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_nonempty_maildir_new", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_maildir_new6, new String[] {"self"} );
			}
			//Function test:test_nonempty_maildir_both
			{
			IMethod methodtest_nonempty_maildir_both7;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_nonempty_maildir_both7 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_nonempty_maildir_both", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_maildir_both7, new String[] {"self"} );
			}
			//Function test:test_unix_mbox
			{
			IMethod methodtest_unix_mbox8;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_unix_mbox8 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_unix_mbox", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unix_mbox8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen273( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_heapq.py"));

		assertNotNull("Module test_heapq.py not found", module);
		assertEquals("test_heapq.py", module.getElementName());
		
		//Function test:heapiter
		{
		IMethod methodheapiter0;
			IModelElement[] moduleChilds = module.getChildren();
			methodheapiter0 = ModelTestUtils.getAssertMethod( moduleChilds, "heapiter", 1 );
			ModelTestUtils.assertParameterNames( methodheapiter0, new String[] {"heap"} );
		}
		//Class test
		{
		IType classTestHeap1;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHeap1 = ModelTestUtils.getAssertClass( moduleChilds, "TestHeap" );
			//Function test:test_push_pop
			{
			IMethod methodtest_push_pop2;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_push_pop2 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_push_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_push_pop2, new String[] {"self"} );
			}
			//Function test:check_invariant
			{
			IMethod methodcheck_invariant3;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodcheck_invariant3 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "check_invariant", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_invariant3, new String[] {"self", "heap"} );
			}
			//Function test:test_heapify
			{
			IMethod methodtest_heapify4;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_heapify4 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_heapify", 1 );
				ModelTestUtils.assertParameterNames( methodtest_heapify4, new String[] {"self"} );
			}
			//Function test:test_naive_nbest
			{
			IMethod methodtest_naive_nbest5;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_naive_nbest5 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_naive_nbest", 1 );
				ModelTestUtils.assertParameterNames( methodtest_naive_nbest5, new String[] {"self"} );
			}
			//Function test:test_nbest
			{
			IMethod methodtest_nbest6;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_nbest6 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_nbest", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nbest6, new String[] {"self"} );
			}
			//Function test:test_heapsort
			{
			IMethod methodtest_heapsort7;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_heapsort7 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_heapsort", 1 );
				ModelTestUtils.assertParameterNames( methodtest_heapsort7, new String[] {"self"} );
			}
			//Function test:test_nsmallest
			{
			IMethod methodtest_nsmallest8;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_nsmallest8 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_nsmallest", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nsmallest8, new String[] {"self"} );
			}
			//Function test:test_largest
			{
			IMethod methodtest_largest9;
				IModelElement[] classTestHeap1Childs = classTestHeap1.getChildren();
				methodtest_largest9 = ModelTestUtils.getAssertMethod( classTestHeap1Childs, "test_largest", 1 );
				ModelTestUtils.assertParameterNames( methodtest_largest9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLenOnly10;
			IModelElement[] moduleChilds = module.getChildren();
			classLenOnly10 = ModelTestUtils.getAssertClass( moduleChilds, "LenOnly" );
			//Function test:__len__
			{
			IMethod method__len__11;
				IModelElement[] classLenOnly10Childs = classLenOnly10.getChildren();
				method__len__11 = ModelTestUtils.getAssertMethod( classLenOnly10Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classGetOnly12;
			IModelElement[] moduleChilds = module.getChildren();
			classGetOnly12 = ModelTestUtils.getAssertClass( moduleChilds, "GetOnly" );
			//Function test:__getitem__
			{
			IMethod method__getitem__13;
				IModelElement[] classGetOnly12Childs = classGetOnly12.getChildren();
				method__getitem__13 = ModelTestUtils.getAssertMethod( classGetOnly12Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__13, new String[] {"self", "ndx"} );
			}
		}
		//Class test
		{
		IType classCmpErr14;
			IModelElement[] moduleChilds = module.getChildren();
			classCmpErr14 = ModelTestUtils.getAssertClass( moduleChilds, "CmpErr" );
			//Function test:__cmp__
			{
			IMethod method__cmp__15;
				IModelElement[] classCmpErr14Childs = classCmpErr14.getChildren();
				method__cmp__15 = ModelTestUtils.getAssertMethod( classCmpErr14Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__15, new String[] {"self", "other"} );
			}
		}
		//Function test:R
		{
		IMethod methodR16;
			IModelElement[] moduleChilds = module.getChildren();
			methodR16 = ModelTestUtils.getAssertMethod( moduleChilds, "R", 1 );
			ModelTestUtils.assertParameterNames( methodR16, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classG17;
			IModelElement[] moduleChilds = module.getChildren();
			classG17 = ModelTestUtils.getAssertClass( moduleChilds, "G" );
			//Function test:__init__
			{
			IMethod method__init__18;
				IModelElement[] classG17Childs = classG17.getChildren();
				method__init__18 = ModelTestUtils.getAssertMethod( classG17Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__18, new String[] {"self", "seqn"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__19;
				IModelElement[] classG17Childs = classG17.getChildren();
				method__getitem__19 = ModelTestUtils.getAssertMethod( classG17Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__19, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI20;
			IModelElement[] moduleChilds = module.getChildren();
			classI20 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__21;
				IModelElement[] classI20Childs = classI20.getChildren();
				method__init__21 = ModelTestUtils.getAssertMethod( classI20Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__21, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__22;
				IModelElement[] classI20Childs = classI20.getChildren();
				method__iter__22 = ModelTestUtils.getAssertMethod( classI20Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__22, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext23;
				IModelElement[] classI20Childs = classI20.getChildren();
				methodnext23 = ModelTestUtils.getAssertMethod( classI20Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg24;
			IModelElement[] moduleChilds = module.getChildren();
			classIg24 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__25;
				IModelElement[] classIg24Childs = classIg24.getChildren();
				method__init__25 = ModelTestUtils.getAssertMethod( classIg24Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__25, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__26;
				IModelElement[] classIg24Childs = classIg24.getChildren();
				method__iter__26 = ModelTestUtils.getAssertMethod( classIg24Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX27;
			IModelElement[] moduleChilds = module.getChildren();
			classX27 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__28;
				IModelElement[] classX27Childs = classX27.getChildren();
				method__init__28 = ModelTestUtils.getAssertMethod( classX27Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__28, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext29;
				IModelElement[] classX27Childs = classX27.getChildren();
				methodnext29 = ModelTestUtils.getAssertMethod( classX27Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext29, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN30;
			IModelElement[] moduleChilds = module.getChildren();
			classN30 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__31;
				IModelElement[] classN30Childs = classN30.getChildren();
				method__init__31 = ModelTestUtils.getAssertMethod( classN30Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__31, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__32;
				IModelElement[] classN30Childs = classN30.getChildren();
				method__iter__32 = ModelTestUtils.getAssertMethod( classN30Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__32, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE33;
			IModelElement[] moduleChilds = module.getChildren();
			classE33 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__34;
				IModelElement[] classE33Childs = classE33.getChildren();
				method__init__34 = ModelTestUtils.getAssertMethod( classE33Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__34, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__35;
				IModelElement[] classE33Childs = classE33.getChildren();
				method__iter__35 = ModelTestUtils.getAssertMethod( classE33Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__35, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext36;
				IModelElement[] classE33Childs = classE33.getChildren();
				methodnext36 = ModelTestUtils.getAssertMethod( classE33Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext36, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS37;
			IModelElement[] moduleChilds = module.getChildren();
			classS37 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__38;
				IModelElement[] classS37Childs = classS37.getChildren();
				method__init__38 = ModelTestUtils.getAssertMethod( classS37Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__38, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__39;
				IModelElement[] classS37Childs = classS37.getChildren();
				method__iter__39 = ModelTestUtils.getAssertMethod( classS37Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__39, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext40;
				IModelElement[] classS37Childs = classS37.getChildren();
				methodnext40 = ModelTestUtils.getAssertMethod( classS37Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext40, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL41;
			IModelElement[] moduleChilds = module.getChildren();
			methodL41 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL41, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestErrorHandling42;
			IModelElement[] moduleChilds = module.getChildren();
			classTestErrorHandling42 = ModelTestUtils.getAssertClass( moduleChilds, "TestErrorHandling" );
			//Function test:test_non_sequence
			{
			IMethod methodtest_non_sequence43;
				IModelElement[] classTestErrorHandling42Childs = classTestErrorHandling42.getChildren();
				methodtest_non_sequence43 = ModelTestUtils.getAssertMethod( classTestErrorHandling42Childs, "test_non_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_sequence43, new String[] {"self"} );
			}
			//Function test:test_len_only
			{
			IMethod methodtest_len_only44;
				IModelElement[] classTestErrorHandling42Childs = classTestErrorHandling42.getChildren();
				methodtest_len_only44 = ModelTestUtils.getAssertMethod( classTestErrorHandling42Childs, "test_len_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len_only44, new String[] {"self"} );
			}
			//Function test:test_get_only
			{
			IMethod methodtest_get_only45;
				IModelElement[] classTestErrorHandling42Childs = classTestErrorHandling42.getChildren();
				methodtest_get_only45 = ModelTestUtils.getAssertMethod( classTestErrorHandling42Childs, "test_get_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_only45, new String[] {"self"} );
			}
			//Function test:test_get_only
			{
			IMethod methodtest_get_only46;
				IModelElement[] classTestErrorHandling42Childs = classTestErrorHandling42.getChildren();
				methodtest_get_only46 = ModelTestUtils.getAssertMethod( classTestErrorHandling42Childs, "test_get_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_only46, new String[] {"self"} );
			}
			//Function test:test_arg_parsing
			{
			IMethod methodtest_arg_parsing47;
				IModelElement[] classTestErrorHandling42Childs = classTestErrorHandling42.getChildren();
				methodtest_arg_parsing47 = ModelTestUtils.getAssertMethod( classTestErrorHandling42Childs, "test_arg_parsing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_arg_parsing47, new String[] {"self"} );
			}
			//Function test:test_iterable_args
			{
			IMethod methodtest_iterable_args48;
				IModelElement[] classTestErrorHandling42Childs = classTestErrorHandling42.getChildren();
				methodtest_iterable_args48 = ModelTestUtils.getAssertMethod( classTestErrorHandling42Childs, "test_iterable_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iterable_args48, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main49;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main49 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main49, new String[] {"verbose"} );
		}

	}
	public void testModelGen274( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_contains.py"));

		assertNotNull("Module test_contains.py not found", module);
		assertEquals("test_contains.py", module.getElementName());
		
		//Class test
		{
		IType classbase_set0;
			IModelElement[] moduleChilds = module.getChildren();
			classbase_set0 = ModelTestUtils.getAssertClass( moduleChilds, "base_set" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classbase_set0Childs = classbase_set0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classbase_set0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "el"} );
			}
		}
		//Class test
		{
		IType classset2;
			IModelElement[] moduleChilds = module.getChildren();
			classset2 = ModelTestUtils.getAssertClass( moduleChilds, "set" );
			//Function test:__contains__
			{
			IMethod method__contains__3;
				IModelElement[] classset2Childs = classset2.getChildren();
				method__contains__3 = ModelTestUtils.getAssertMethod( classset2Childs, "__contains__", 2 );
				ModelTestUtils.assertParameterNames( method__contains__3, new String[] {"self", "el"} );
			}
		}
		//Class test
		{
		IType classseq4;
			IModelElement[] moduleChilds = module.getChildren();
			classseq4 = ModelTestUtils.getAssertClass( moduleChilds, "seq" );
			//Function test:__getitem__
			{
			IMethod method__getitem__5;
				IModelElement[] classseq4Childs = classseq4.getChildren();
				method__getitem__5 = ModelTestUtils.getAssertMethod( classseq4Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__5, new String[] {"self", "n"} );
			}
		}
		//Function test:check
		{
		IMethod methodcheck6;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck6 = ModelTestUtils.getAssertMethod( moduleChilds, "check", 2 );
			ModelTestUtils.assertParameterNames( methodcheck6, new String[] {"ok", "args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "b");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		//Class test
		{
		IType classDeviant17;
			IModelElement[] moduleChilds = module.getChildren();
			classDeviant17 = ModelTestUtils.getAssertClass( moduleChilds, "Deviant1" );
			{
				IModelElement[] classDeviant17Childs = classDeviant17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDeviant17Childs, "aList");
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__8;
				IModelElement[] classDeviant17Childs = classDeviant17.getChildren();
				method__cmp__8 = ModelTestUtils.getAssertMethod( classDeviant17Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__8, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classDeviant29;
			IModelElement[] moduleChilds = module.getChildren();
			classDeviant29 = ModelTestUtils.getAssertClass( moduleChilds, "Deviant2" );
			//Function test:__cmp__
			{
			IMethod method__cmp__10;
				IModelElement[] classDeviant29Childs = classDeviant29.getChildren();
				method__cmp__10 = ModelTestUtils.getAssertMethod( classDeviant29Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__10, new String[] {"self", "other"} );
			}
		}

	}
	public void testModelGen275( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_poll.py"));

		assertNotNull("Module test_poll.py not found", module);
		assertEquals("test_poll.py", module.getElementName());
		
		//Function test:find_ready_matching
		{
		IMethod methodfind_ready_matching0;
			IModelElement[] moduleChilds = module.getChildren();
			methodfind_ready_matching0 = ModelTestUtils.getAssertMethod( moduleChilds, "find_ready_matching", 2 );
			ModelTestUtils.assertParameterNames( methodfind_ready_matching0, new String[] {"ready", "flag"} );
		}
		//Function test:test_poll1
		{
		IMethod methodtest_poll11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_poll11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_poll1", 0 );
		}
		//Function test:poll_unit_tests
		{
		IMethod methodpoll_unit_tests2;
			IModelElement[] moduleChilds = module.getChildren();
			methodpoll_unit_tests2 = ModelTestUtils.getAssertMethod( moduleChilds, "poll_unit_tests", 0 );
			//Class test
			{
			IType classNope3;
				IModelElement[] methodpoll_unit_tests2Childs = methodpoll_unit_tests2.getChildren();
				classNope3 = ModelTestUtils.getAssertClass( methodpoll_unit_tests2Childs, "Nope" );
			}
			//Class test
			{
			IType classAlmost4;
				IModelElement[] methodpoll_unit_tests2Childs = methodpoll_unit_tests2.getChildren();
				classAlmost4 = ModelTestUtils.getAssertClass( methodpoll_unit_tests2Childs, "Almost" );
				//Function test:fileno
				{
				IMethod methodfileno5;
					IModelElement[] classAlmost4Childs = classAlmost4.getChildren();
					methodfileno5 = ModelTestUtils.getAssertMethod( classAlmost4Childs, "fileno", 1 );
					ModelTestUtils.assertParameterNames( methodfileno5, new String[] {"self"} );
				}
			}
		}
		//Function test:test_poll2
		{
		IMethod methodtest_poll26;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_poll26 = ModelTestUtils.getAssertMethod( moduleChilds, "test_poll2", 0 );
		}

	}
	public void testModelGen276( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_xmlrpc.py"));

		assertNotNull("Module test_xmlrpc.py not found", module);
		assertEquals("test_xmlrpc.py", module.getElementName());
		
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "alist");
		}
		//Class test
		{
		IType classXMLRPCTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classXMLRPCTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "XMLRPCTestCase" );
			//Function test:test_dump_load
			{
			IMethod methodtest_dump_load1;
				IModelElement[] classXMLRPCTestCase0Childs = classXMLRPCTestCase0.getChildren();
				methodtest_dump_load1 = ModelTestUtils.getAssertMethod( classXMLRPCTestCase0Childs, "test_dump_load", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_load1, new String[] {"self"} );
			}
			//Function test:test_dump_big_long
			{
			IMethod methodtest_dump_big_long2;
				IModelElement[] classXMLRPCTestCase0Childs = classXMLRPCTestCase0.getChildren();
				methodtest_dump_big_long2 = ModelTestUtils.getAssertMethod( classXMLRPCTestCase0Childs, "test_dump_big_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_big_long2, new String[] {"self"} );
			}
			//Function test:test_dump_bad_dict
			{
			IMethod methodtest_dump_bad_dict3;
				IModelElement[] classXMLRPCTestCase0Childs = classXMLRPCTestCase0.getChildren();
				methodtest_dump_bad_dict3 = ModelTestUtils.getAssertMethod( classXMLRPCTestCase0Childs, "test_dump_bad_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_bad_dict3, new String[] {"self"} );
			}
			//Function test:test_dump_big_int
			{
			IMethod methodtest_dump_big_int4;
				IModelElement[] classXMLRPCTestCase0Childs = classXMLRPCTestCase0.getChildren();
				methodtest_dump_big_int4 = ModelTestUtils.getAssertMethod( classXMLRPCTestCase0Childs, "test_dump_big_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_big_int4, new String[] {"self"} );
			}
			//Function test:test_dump_none
			{
			IMethod methodtest_dump_none5;
				IModelElement[] classXMLRPCTestCase0Childs = classXMLRPCTestCase0.getChildren();
				methodtest_dump_none5 = ModelTestUtils.getAssertMethod( classXMLRPCTestCase0Childs, "test_dump_none", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_none5, new String[] {"self"} );
			}
			//Function test:test_default_encoding_issues
			{
			IMethod methodtest_default_encoding_issues6;
				IModelElement[] classXMLRPCTestCase0Childs = classXMLRPCTestCase0.getChildren();
				methodtest_default_encoding_issues6 = ModelTestUtils.getAssertMethod( classXMLRPCTestCase0Childs, "test_default_encoding_issues", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_encoding_issues6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen277( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_longexp.py"));

		assertNotNull("Module test_longexp.py not found", module);
		assertEquals("test_longexp.py", module.getElementName());
		
		//Class test
		{
		IType classLongExpText0;
			IModelElement[] moduleChilds = module.getChildren();
			classLongExpText0 = ModelTestUtils.getAssertClass( moduleChilds, "LongExpText" );
			//Function test:test_longexp
			{
			IMethod methodtest_longexp1;
				IModelElement[] classLongExpText0Childs = classLongExpText0.getChildren();
				methodtest_longexp1 = ModelTestUtils.getAssertMethod( classLongExpText0Childs, "test_longexp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longexp1, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen278( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecencodings_cn.py"));

		assertNotNull("Module test_codecencodings_cn.py not found", module);
		assertEquals("test_codecencodings_cn.py", module.getElementName());
		
		//Class test
		{
		IType classTest_GB23120;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_GB23120 = ModelTestUtils.getAssertClass( moduleChilds, "Test_GB2312" );
			{
				IModelElement[] classTest_GB23120Childs = classTest_GB23120.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB23120Childs, "encoding");
			}
			{
				IModelElement[] classTest_GB23120Childs = classTest_GB23120.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB23120Childs, "tstring");
			}
			{
				IModelElement[] classTest_GB23120Childs = classTest_GB23120.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB23120Childs, "codectests");
			}
		}
		//Class test
		{
		IType classTest_GBK1;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_GBK1 = ModelTestUtils.getAssertClass( moduleChilds, "Test_GBK" );
			{
				IModelElement[] classTest_GBK1Childs = classTest_GBK1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GBK1Childs, "encoding");
			}
			{
				IModelElement[] classTest_GBK1Childs = classTest_GBK1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GBK1Childs, "tstring");
			}
			{
				IModelElement[] classTest_GBK1Childs = classTest_GBK1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GBK1Childs, "codectests");
			}
		}
		//Class test
		{
		IType classTest_GB180302;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_GB180302 = ModelTestUtils.getAssertClass( moduleChilds, "Test_GB18030" );
			{
				IModelElement[] classTest_GB180302Childs = classTest_GB180302.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB180302Childs, "encoding");
			}
			{
				IModelElement[] classTest_GB180302Childs = classTest_GB180302.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB180302Childs, "tstring");
			}
			{
				IModelElement[] classTest_GB180302Childs = classTest_GB180302.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB180302Childs, "codectests");
			}
			{
				IModelElement[] classTest_GB180302Childs = classTest_GB180302.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_GB180302Childs, "has_iso10646");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen279( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_operator.py"));

		assertNotNull("Module test_operator.py not found", module);
		assertEquals("test_operator.py", module.getElementName());
		
		//Class test
		{
		IType classOperatorTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classOperatorTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "OperatorTestCase" );
			//Function test:test_lt
			{
			IMethod methodtest_lt1;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_lt1 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_lt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lt1, new String[] {"self"} );
			}
			//Function test:test_le
			{
			IMethod methodtest_le2;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_le2 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_le", 1 );
				ModelTestUtils.assertParameterNames( methodtest_le2, new String[] {"self"} );
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq3;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_eq3 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq3, new String[] {"self"} );
				//Class test
				{
				IType classC4;
					IModelElement[] methodtest_eq3Childs = methodtest_eq3.getChildren();
					classC4 = ModelTestUtils.getAssertClass( methodtest_eq3Childs, "C" );
					//Function test:__eq__
					{
					IMethod method__eq__5;
						IModelElement[] classC4Childs = classC4.getChildren();
						method__eq__5 = ModelTestUtils.getAssertMethod( classC4Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__5, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_ne
			{
			IMethod methodtest_ne6;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_ne6 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_ne", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ne6, new String[] {"self"} );
				//Class test
				{
				IType classC7;
					IModelElement[] methodtest_ne6Childs = methodtest_ne6.getChildren();
					classC7 = ModelTestUtils.getAssertClass( methodtest_ne6Childs, "C" );
					//Function test:__ne__
					{
					IMethod method__ne__8;
						IModelElement[] classC7Childs = classC7.getChildren();
						method__ne__8 = ModelTestUtils.getAssertMethod( classC7Childs, "__ne__", 2 );
						ModelTestUtils.assertParameterNames( method__ne__8, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_ge
			{
			IMethod methodtest_ge9;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_ge9 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_ge", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ge9, new String[] {"self"} );
			}
			//Function test:test_gt
			{
			IMethod methodtest_gt10;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_gt10 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_gt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gt10, new String[] {"self"} );
			}
			//Function test:test_abs
			{
			IMethod methodtest_abs11;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_abs11 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_abs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs11, new String[] {"self"} );
			}
			//Function test:test_add
			{
			IMethod methodtest_add12;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_add12 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_add", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add12, new String[] {"self"} );
			}
			//Function test:test_bitwise_and
			{
			IMethod methodtest_bitwise_and13;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_bitwise_and13 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_bitwise_and", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bitwise_and13, new String[] {"self"} );
			}
			//Function test:test_concat
			{
			IMethod methodtest_concat14;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_concat14 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_concat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_concat14, new String[] {"self"} );
			}
			//Function test:test_countOf
			{
			IMethod methodtest_countOf15;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_countOf15 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_countOf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_countOf15, new String[] {"self"} );
			}
			//Function test:test_delitem
			{
			IMethod methodtest_delitem16;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_delitem16 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_delitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delitem16, new String[] {"self"} );
			}
			//Function test:test_delslice
			{
			IMethod methodtest_delslice17;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_delslice17 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_delslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delslice17, new String[] {"self"} );
			}
			//Function test:test_div
			{
			IMethod methodtest_div18;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_div18 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_div", 1 );
				ModelTestUtils.assertParameterNames( methodtest_div18, new String[] {"self"} );
			}
			//Function test:test_floordiv
			{
			IMethod methodtest_floordiv19;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_floordiv19 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_floordiv", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floordiv19, new String[] {"self"} );
			}
			//Function test:test_truediv
			{
			IMethod methodtest_truediv20;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_truediv20 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_truediv", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truediv20, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem21;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_getitem21 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem21, new String[] {"self"} );
			}
			//Function test:test_getslice
			{
			IMethod methodtest_getslice22;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_getslice22 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_getslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getslice22, new String[] {"self"} );
			}
			//Function test:test_indexOf
			{
			IMethod methodtest_indexOf23;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_indexOf23 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_indexOf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_indexOf23, new String[] {"self"} );
			}
			//Function test:test_invert
			{
			IMethod methodtest_invert24;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_invert24 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_invert", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invert24, new String[] {"self"} );
			}
			//Function test:test_isCallable
			{
			IMethod methodtest_isCallable25;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_isCallable25 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_isCallable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isCallable25, new String[] {"self"} );
				//Class test
				{
				IType classC26;
					IModelElement[] methodtest_isCallable25Childs = methodtest_isCallable25.getChildren();
					classC26 = ModelTestUtils.getAssertClass( methodtest_isCallable25Childs, "C" );
				}
				//Function test:check
				{
				IMethod methodcheck27;
					IModelElement[] methodtest_isCallable25Childs = methodtest_isCallable25.getChildren();
					methodcheck27 = ModelTestUtils.getAssertMethod( methodtest_isCallable25Childs, "check", 3 );
					ModelTestUtils.assertParameterNames( methodcheck27, new String[] {"self", "o", "v"} );
				}
			}
			//Function test:test_isMappingType
			{
			IMethod methodtest_isMappingType28;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_isMappingType28 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_isMappingType", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isMappingType28, new String[] {"self"} );
			}
			//Function test:test_isNumberType
			{
			IMethod methodtest_isNumberType29;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_isNumberType29 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_isNumberType", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isNumberType29, new String[] {"self"} );
			}
			//Function test:test_isSequenceType
			{
			IMethod methodtest_isSequenceType30;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_isSequenceType30 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_isSequenceType", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isSequenceType30, new String[] {"self"} );
			}
			//Function test:test_lshift
			{
			IMethod methodtest_lshift31;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_lshift31 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_lshift", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lshift31, new String[] {"self"} );
			}
			//Function test:test_mod
			{
			IMethod methodtest_mod32;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_mod32 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_mod", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mod32, new String[] {"self"} );
			}
			//Function test:test_mul
			{
			IMethod methodtest_mul33;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_mul33 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_mul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mul33, new String[] {"self"} );
			}
			//Function test:test_neg
			{
			IMethod methodtest_neg34;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_neg34 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_neg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_neg34, new String[] {"self"} );
			}
			//Function test:test_bitwise_or
			{
			IMethod methodtest_bitwise_or35;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_bitwise_or35 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_bitwise_or", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bitwise_or35, new String[] {"self"} );
			}
			//Function test:test_pos
			{
			IMethod methodtest_pos36;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_pos36 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_pos", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pos36, new String[] {"self"} );
			}
			//Function test:test_pow
			{
			IMethod methodtest_pow37;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_pow37 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_pow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pow37, new String[] {"self"} );
			}
			//Function test:test_repeat
			{
			IMethod methodtest_repeat38;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_repeat38 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat38, new String[] {"self"} );
			}
			//Function test:test_rshift
			{
			IMethod methodtest_rshift39;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_rshift39 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_rshift", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rshift39, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains40;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_contains40 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains40, new String[] {"self"} );
			}
			//Function test:test_setitem
			{
			IMethod methodtest_setitem41;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_setitem41 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_setitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setitem41, new String[] {"self"} );
			}
			//Function test:test_setslice
			{
			IMethod methodtest_setslice42;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_setslice42 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_setslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setslice42, new String[] {"self"} );
			}
			//Function test:test_sub
			{
			IMethod methodtest_sub43;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_sub43 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub43, new String[] {"self"} );
			}
			//Function test:test_truth
			{
			IMethod methodtest_truth44;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_truth44 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_truth", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truth44, new String[] {"self"} );
				//Class test
				{
				IType classC45;
					IModelElement[] methodtest_truth44Childs = methodtest_truth44.getChildren();
					classC45 = ModelTestUtils.getAssertClass( methodtest_truth44Childs, "C" );
					//Function test:__nonzero__
					{
					IMethod method__nonzero__46;
						IModelElement[] classC45Childs = classC45.getChildren();
						method__nonzero__46 = ModelTestUtils.getAssertMethod( classC45Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__46, new String[] {"self"} );
					}
				}
			}
			//Function test:test_bitwise_xor
			{
			IMethod methodtest_bitwise_xor47;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_bitwise_xor47 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_bitwise_xor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bitwise_xor47, new String[] {"self"} );
			}
			//Function test:test_is
			{
			IMethod methodtest_is48;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_is48 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_is", 1 );
				ModelTestUtils.assertParameterNames( methodtest_is48, new String[] {"self"} );
			}
			//Function test:test_is_not
			{
			IMethod methodtest_is_not49;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_is_not49 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_is_not", 1 );
				ModelTestUtils.assertParameterNames( methodtest_is_not49, new String[] {"self"} );
			}
			//Function test:test_attrgetter
			{
			IMethod methodtest_attrgetter50;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_attrgetter50 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_attrgetter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attrgetter50, new String[] {"self"} );
				//Class test
				{
				IType classA51;
					IModelElement[] methodtest_attrgetter50Childs = methodtest_attrgetter50.getChildren();
					classA51 = ModelTestUtils.getAssertClass( methodtest_attrgetter50Childs, "A" );
				}
				//Class test
				{
				IType classC52;
					IModelElement[] methodtest_attrgetter50Childs = methodtest_attrgetter50.getChildren();
					classC52 = ModelTestUtils.getAssertClass( methodtest_attrgetter50Childs, "C" );
					//Function test:__getattr
					{
					IMethod method__getattr53;
						IModelElement[] classC52Childs = classC52.getChildren();
						method__getattr53 = ModelTestUtils.getAssertMethod( classC52Childs, "__getattr", 2 );
						ModelTestUtils.assertParameterNames( method__getattr53, new String[] {"self", "name"} );
					}
				}
			}
			//Function test:test_itemgetter
			{
			IMethod methodtest_itemgetter54;
				IModelElement[] classOperatorTestCase0Childs = classOperatorTestCase0.getChildren();
				methodtest_itemgetter54 = ModelTestUtils.getAssertMethod( classOperatorTestCase0Childs, "test_itemgetter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_itemgetter54, new String[] {"self"} );
				//Class test
				{
				IType classC55;
					IModelElement[] methodtest_itemgetter54Childs = methodtest_itemgetter54.getChildren();
					classC55 = ModelTestUtils.getAssertClass( methodtest_itemgetter54Childs, "C" );
					//Function test:__getitem
					{
					IMethod method__getitem56;
						IModelElement[] classC55Childs = classC55.getChildren();
						method__getitem56 = ModelTestUtils.getAssertMethod( classC55Childs, "__getitem", 2 );
						ModelTestUtils.assertParameterNames( method__getitem56, new String[] {"self", "name"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main57;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main57 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen280( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_base64.py"));

		assertNotNull("Module test_base64.py not found", module);
		assertEquals("test_base64.py", module.getElementName());
		
		//Class test
		{
		IType classLegacyBase64TestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classLegacyBase64TestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "LegacyBase64TestCase" );
			//Function test:test_encodestring
			{
			IMethod methodtest_encodestring1;
				IModelElement[] classLegacyBase64TestCase0Childs = classLegacyBase64TestCase0.getChildren();
				methodtest_encodestring1 = ModelTestUtils.getAssertMethod( classLegacyBase64TestCase0Childs, "test_encodestring", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encodestring1, new String[] {"self"} );
			}
			//Function test:test_decodestring
			{
			IMethod methodtest_decodestring2;
				IModelElement[] classLegacyBase64TestCase0Childs = classLegacyBase64TestCase0.getChildren();
				methodtest_decodestring2 = ModelTestUtils.getAssertMethod( classLegacyBase64TestCase0Childs, "test_decodestring", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodestring2, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode3;
				IModelElement[] classLegacyBase64TestCase0Childs = classLegacyBase64TestCase0.getChildren();
				methodtest_encode3 = ModelTestUtils.getAssertMethod( classLegacyBase64TestCase0Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode3, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode4;
				IModelElement[] classLegacyBase64TestCase0Childs = classLegacyBase64TestCase0.getChildren();
				methodtest_decode4 = ModelTestUtils.getAssertMethod( classLegacyBase64TestCase0Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBaseXYTestCase5;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseXYTestCase5 = ModelTestUtils.getAssertClass( moduleChilds, "BaseXYTestCase" );
			//Function test:test_b64encode
			{
			IMethod methodtest_b64encode6;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b64encode6 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b64encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b64encode6, new String[] {"self"} );
			}
			//Function test:test_b64decode
			{
			IMethod methodtest_b64decode7;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b64decode7 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b64decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b64decode7, new String[] {"self"} );
			}
			//Function test:test_b64decode_error
			{
			IMethod methodtest_b64decode_error8;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b64decode_error8 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b64decode_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b64decode_error8, new String[] {"self"} );
			}
			//Function test:test_b32encode
			{
			IMethod methodtest_b32encode9;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b32encode9 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b32encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b32encode9, new String[] {"self"} );
			}
			//Function test:test_b32decode
			{
			IMethod methodtest_b32decode10;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b32decode10 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b32decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b32decode10, new String[] {"self"} );
			}
			//Function test:test_b32decode_casefold
			{
			IMethod methodtest_b32decode_casefold11;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b32decode_casefold11 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b32decode_casefold", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b32decode_casefold11, new String[] {"self"} );
			}
			//Function test:test_b32decode_error
			{
			IMethod methodtest_b32decode_error12;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b32decode_error12 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b32decode_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b32decode_error12, new String[] {"self"} );
			}
			//Function test:test_b16encode
			{
			IMethod methodtest_b16encode13;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b16encode13 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b16encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b16encode13, new String[] {"self"} );
			}
			//Function test:test_b16decode
			{
			IMethod methodtest_b16decode14;
				IModelElement[] classBaseXYTestCase5Childs = classBaseXYTestCase5.getChildren();
				methodtest_b16decode14 = ModelTestUtils.getAssertMethod( classBaseXYTestCase5Childs, "test_b16decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_b16decode14, new String[] {"self"} );
			}
		}
		//Function test:suite
		{
		IMethod methodsuite15;
			IModelElement[] moduleChilds = module.getChildren();
			methodsuite15 = ModelTestUtils.getAssertMethod( moduleChilds, "suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen281( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecencodings_hk.py"));

		assertNotNull("Module test_codecencodings_hk.py not found", module);
		assertEquals("test_codecencodings_hk.py", module.getElementName());
		
		//Class test
		{
		IType classTest_Big5HKSCS0;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_Big5HKSCS0 = ModelTestUtils.getAssertClass( moduleChilds, "Test_Big5HKSCS" );
			{
				IModelElement[] classTest_Big5HKSCS0Childs = classTest_Big5HKSCS0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_Big5HKSCS0Childs, "encoding");
			}
			{
				IModelElement[] classTest_Big5HKSCS0Childs = classTest_Big5HKSCS0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_Big5HKSCS0Childs, "tstring");
			}
			{
				IModelElement[] classTest_Big5HKSCS0Childs = classTest_Big5HKSCS0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_Big5HKSCS0Childs, "codectests");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen282( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecencodings_jp.py"));

		assertNotNull("Module test_codecencodings_jp.py not found", module);
		assertEquals("test_codecencodings_jp.py", module.getElementName());
		
		//Class test
		{
		IType classTest_CP9320;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_CP9320 = ModelTestUtils.getAssertClass( moduleChilds, "Test_CP932" );
			{
				IModelElement[] classTest_CP9320Childs = classTest_CP9320.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_CP9320Childs, "encoding");
			}
			{
				IModelElement[] classTest_CP9320Childs = classTest_CP9320.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_CP9320Childs, "tstring");
			}
			{
				IModelElement[] classTest_CP9320Childs = classTest_CP9320.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_CP9320Childs, "codectests");
			}
		}
		//Class test
		{
		IType classTest_EUC_JISX02131;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_EUC_JISX02131 = ModelTestUtils.getAssertClass( moduleChilds, "Test_EUC_JISX0213" );
			{
				IModelElement[] classTest_EUC_JISX02131Childs = classTest_EUC_JISX02131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JISX02131Childs, "encoding");
			}
			{
				IModelElement[] classTest_EUC_JISX02131Childs = classTest_EUC_JISX02131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JISX02131Childs, "tstring");
			}
			{
				IModelElement[] classTest_EUC_JISX02131Childs = classTest_EUC_JISX02131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JISX02131Childs, "codectests");
			}
			{
				IModelElement[] classTest_EUC_JISX02131Childs = classTest_EUC_JISX02131.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JISX02131Childs, "xmlcharnametest");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "eucjp_commontests");
		}
		//Class test
		{
		IType classTest_EUC_JP_COMPAT2;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_EUC_JP_COMPAT2 = ModelTestUtils.getAssertClass( moduleChilds, "Test_EUC_JP_COMPAT" );
			{
				IModelElement[] classTest_EUC_JP_COMPAT2Childs = classTest_EUC_JP_COMPAT2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JP_COMPAT2Childs, "encoding");
			}
			{
				IModelElement[] classTest_EUC_JP_COMPAT2Childs = classTest_EUC_JP_COMPAT2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JP_COMPAT2Childs, "tstring");
			}
			{
				IModelElement[] classTest_EUC_JP_COMPAT2Childs = classTest_EUC_JP_COMPAT2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUC_JP_COMPAT2Childs, "codectests");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "shiftjis_commonenctests");
		}
		//Class test
		{
		IType classTest_SJIS_COMPAT3;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_SJIS_COMPAT3 = ModelTestUtils.getAssertClass( moduleChilds, "Test_SJIS_COMPAT" );
			{
				IModelElement[] classTest_SJIS_COMPAT3Childs = classTest_SJIS_COMPAT3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJIS_COMPAT3Childs, "encoding");
			}
			{
				IModelElement[] classTest_SJIS_COMPAT3Childs = classTest_SJIS_COMPAT3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJIS_COMPAT3Childs, "tstring");
			}
			{
				IModelElement[] classTest_SJIS_COMPAT3Childs = classTest_SJIS_COMPAT3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJIS_COMPAT3Childs, "codectests");
			}
		}
		//Class test
		{
		IType classTest_SJISX02134;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_SJISX02134 = ModelTestUtils.getAssertClass( moduleChilds, "Test_SJISX0213" );
			{
				IModelElement[] classTest_SJISX02134Childs = classTest_SJISX02134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJISX02134Childs, "encoding");
			}
			{
				IModelElement[] classTest_SJISX02134Childs = classTest_SJISX02134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJISX02134Childs, "tstring");
			}
			{
				IModelElement[] classTest_SJISX02134Childs = classTest_SJISX02134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJISX02134Childs, "codectests");
			}
			{
				IModelElement[] classTest_SJISX02134Childs = classTest_SJISX02134.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_SJISX02134Childs, "xmlcharnametest");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen283( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_subprocess.py"));

		assertNotNull("Module test_subprocess.py not found", module);
		assertEquals("test_subprocess.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mswindows");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SETBINARY");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SETBINARY");
		}
		//Function test:remove_stderr_debug_decorations
		{
		IMethod methodremove_stderr_debug_decorations0;
			IModelElement[] moduleChilds = module.getChildren();
			methodremove_stderr_debug_decorations0 = ModelTestUtils.getAssertMethod( moduleChilds, "remove_stderr_debug_decorations", 1 );
			ModelTestUtils.assertParameterNames( methodremove_stderr_debug_decorations0, new String[] {"stderr"} );
		}
		//Class test
		{
		IType classProcessTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classProcessTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "ProcessTestCase" );
			//Function test:mkstemp
			{
			IMethod methodmkstemp2;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodmkstemp2 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "mkstemp", 1 );
				ModelTestUtils.assertParameterNames( methodmkstemp2, new String[] {"self"} );
			}
			//Function test:test_call_seq
			{
			IMethod methodtest_call_seq3;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_call_seq3 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_call_seq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_call_seq3, new String[] {"self"} );
			}
			//Function test:test_call_kwargs
			{
			IMethod methodtest_call_kwargs4;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_call_kwargs4 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_call_kwargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_call_kwargs4, new String[] {"self"} );
			}
			//Function test:test_stdin_none
			{
			IMethod methodtest_stdin_none5;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdin_none5 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdin_none", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdin_none5, new String[] {"self"} );
			}
			//Function test:test_stdout_none
			{
			IMethod methodtest_stdout_none6;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdout_none6 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdout_none", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdout_none6, new String[] {"self"} );
			}
			//Function test:test_stderr_none
			{
			IMethod methodtest_stderr_none7;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stderr_none7 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stderr_none", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stderr_none7, new String[] {"self"} );
			}
			//Function test:test_executable
			{
			IMethod methodtest_executable8;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_executable8 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_executable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_executable8, new String[] {"self"} );
			}
			//Function test:test_stdin_pipe
			{
			IMethod methodtest_stdin_pipe9;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdin_pipe9 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdin_pipe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdin_pipe9, new String[] {"self"} );
			}
			//Function test:test_stdin_filedes
			{
			IMethod methodtest_stdin_filedes10;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdin_filedes10 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdin_filedes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdin_filedes10, new String[] {"self"} );
			}
			//Function test:test_stdin_fileobj
			{
			IMethod methodtest_stdin_fileobj11;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdin_fileobj11 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdin_fileobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdin_fileobj11, new String[] {"self"} );
			}
			//Function test:test_stdout_pipe
			{
			IMethod methodtest_stdout_pipe12;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdout_pipe12 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdout_pipe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdout_pipe12, new String[] {"self"} );
			}
			//Function test:test_stdout_filedes
			{
			IMethod methodtest_stdout_filedes13;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdout_filedes13 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdout_filedes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdout_filedes13, new String[] {"self"} );
			}
			//Function test:test_stdout_fileobj
			{
			IMethod methodtest_stdout_fileobj14;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdout_fileobj14 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdout_fileobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdout_fileobj14, new String[] {"self"} );
			}
			//Function test:test_stderr_pipe
			{
			IMethod methodtest_stderr_pipe15;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stderr_pipe15 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stderr_pipe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stderr_pipe15, new String[] {"self"} );
			}
			//Function test:test_stderr_filedes
			{
			IMethod methodtest_stderr_filedes16;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stderr_filedes16 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stderr_filedes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stderr_filedes16, new String[] {"self"} );
			}
			//Function test:test_stderr_fileobj
			{
			IMethod methodtest_stderr_fileobj17;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stderr_fileobj17 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stderr_fileobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stderr_fileobj17, new String[] {"self"} );
			}
			//Function test:test_stdout_stderr_pipe
			{
			IMethod methodtest_stdout_stderr_pipe18;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdout_stderr_pipe18 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdout_stderr_pipe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdout_stderr_pipe18, new String[] {"self"} );
			}
			//Function test:test_stdout_stderr_file
			{
			IMethod methodtest_stdout_stderr_file19;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_stdout_stderr_file19 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_stdout_stderr_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stdout_stderr_file19, new String[] {"self"} );
			}
			//Function test:test_cwd
			{
			IMethod methodtest_cwd20;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_cwd20 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_cwd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cwd20, new String[] {"self"} );
			}
			//Function test:test_env
			{
			IMethod methodtest_env21;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_env21 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_env", 1 );
				ModelTestUtils.assertParameterNames( methodtest_env21, new String[] {"self"} );
			}
			//Function test:test_communicate
			{
			IMethod methodtest_communicate22;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_communicate22 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_communicate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_communicate22, new String[] {"self"} );
			}
			//Function test:test_communicate_returns
			{
			IMethod methodtest_communicate_returns23;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_communicate_returns23 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_communicate_returns", 1 );
				ModelTestUtils.assertParameterNames( methodtest_communicate_returns23, new String[] {"self"} );
			}
			//Function test:test_communicate_pipe_buf
			{
			IMethod methodtest_communicate_pipe_buf24;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_communicate_pipe_buf24 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_communicate_pipe_buf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_communicate_pipe_buf24, new String[] {"self"} );
			}
			//Function test:test_writes_before_communicate
			{
			IMethod methodtest_writes_before_communicate25;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_writes_before_communicate25 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_writes_before_communicate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writes_before_communicate25, new String[] {"self"} );
			}
			//Function test:test_universal_newlines
			{
			IMethod methodtest_universal_newlines26;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_universal_newlines26 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_universal_newlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_universal_newlines26, new String[] {"self"} );
			}
			//Function test:test_universal_newlines_communicate
			{
			IMethod methodtest_universal_newlines_communicate27;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_universal_newlines_communicate27 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_universal_newlines_communicate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_universal_newlines_communicate27, new String[] {"self"} );
			}
			//Function test:test_no_leaking
			{
			IMethod methodtest_no_leaking28;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_no_leaking28 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_no_leaking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_leaking28, new String[] {"self"} );
			}
			//Function test:test_list2cmdline
			{
			IMethod methodtest_list2cmdline29;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_list2cmdline29 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_list2cmdline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_list2cmdline29, new String[] {"self"} );
			}
			//Function test:test_poll
			{
			IMethod methodtest_poll30;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_poll30 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_poll", 1 );
				ModelTestUtils.assertParameterNames( methodtest_poll30, new String[] {"self"} );
			}
			//Function test:test_wait
			{
			IMethod methodtest_wait31;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_wait31 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_wait", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wait31, new String[] {"self"} );
			}
			//Function test:test_invalid_bufsize
			{
			IMethod methodtest_invalid_bufsize32;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_invalid_bufsize32 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_invalid_bufsize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_bufsize32, new String[] {"self"} );
			}
			//Function test:test_exceptions
			{
			IMethod methodtest_exceptions33;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_exceptions33 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_exceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exceptions33, new String[] {"self"} );
			}
			//Function test:test_run_abort
			{
			IMethod methodtest_run_abort34;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_run_abort34 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_run_abort", 1 );
				ModelTestUtils.assertParameterNames( methodtest_run_abort34, new String[] {"self"} );
			}
			//Function test:test_preexec
			{
			IMethod methodtest_preexec35;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_preexec35 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_preexec", 1 );
				ModelTestUtils.assertParameterNames( methodtest_preexec35, new String[] {"self"} );
			}
			//Function test:test_args_string
			{
			IMethod methodtest_args_string36;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_args_string36 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_args_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_args_string36, new String[] {"self"} );
			}
			//Function test:test_invalid_args
			{
			IMethod methodtest_invalid_args37;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_invalid_args37 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_invalid_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_args37, new String[] {"self"} );
			}
			//Function test:test_shell_sequence
			{
			IMethod methodtest_shell_sequence38;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_shell_sequence38 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_shell_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shell_sequence38, new String[] {"self"} );
			}
			//Function test:test_shell_string
			{
			IMethod methodtest_shell_string39;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_shell_string39 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_shell_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shell_string39, new String[] {"self"} );
			}
			//Function test:test_call_string
			{
			IMethod methodtest_call_string40;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_call_string40 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_call_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_call_string40, new String[] {"self"} );
			}
			//Function test:test_startupinfo
			{
			IMethod methodtest_startupinfo41;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_startupinfo41 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_startupinfo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_startupinfo41, new String[] {"self"} );
			}
			//Function test:test_creationflags
			{
			IMethod methodtest_creationflags42;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_creationflags42 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_creationflags", 1 );
				ModelTestUtils.assertParameterNames( methodtest_creationflags42, new String[] {"self"} );
			}
			//Function test:test_invalid_args
			{
			IMethod methodtest_invalid_args43;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_invalid_args43 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_invalid_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invalid_args43, new String[] {"self"} );
			}
			//Function test:test_shell_sequence
			{
			IMethod methodtest_shell_sequence44;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_shell_sequence44 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_shell_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shell_sequence44, new String[] {"self"} );
			}
			//Function test:test_shell_string
			{
			IMethod methodtest_shell_string45;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_shell_string45 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_shell_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_shell_string45, new String[] {"self"} );
			}
			//Function test:test_call_string
			{
			IMethod methodtest_call_string46;
				IModelElement[] classProcessTestCase1Childs = classProcessTestCase1.getChildren();
				methodtest_call_string46 = ModelTestUtils.getAssertMethod( classProcessTestCase1Childs, "test_call_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_call_string46, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main47;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main47 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen284( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecencodings_kr.py"));

		assertNotNull("Module test_codecencodings_kr.py not found", module);
		assertEquals("test_codecencodings_kr.py", module.getElementName());
		
		//Class test
		{
		IType classTest_CP9490;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_CP9490 = ModelTestUtils.getAssertClass( moduleChilds, "Test_CP949" );
			{
				IModelElement[] classTest_CP9490Childs = classTest_CP9490.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_CP9490Childs, "encoding");
			}
			{
				IModelElement[] classTest_CP9490Childs = classTest_CP9490.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_CP9490Childs, "tstring");
			}
			{
				IModelElement[] classTest_CP9490Childs = classTest_CP9490.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_CP9490Childs, "codectests");
			}
		}
		//Class test
		{
		IType classTest_EUCKR1;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_EUCKR1 = ModelTestUtils.getAssertClass( moduleChilds, "Test_EUCKR" );
			{
				IModelElement[] classTest_EUCKR1Childs = classTest_EUCKR1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUCKR1Childs, "encoding");
			}
			{
				IModelElement[] classTest_EUCKR1Childs = classTest_EUCKR1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUCKR1Childs, "tstring");
			}
			{
				IModelElement[] classTest_EUCKR1Childs = classTest_EUCKR1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_EUCKR1Childs, "codectests");
			}
		}
		//Class test
		{
		IType classTest_JOHAB2;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_JOHAB2 = ModelTestUtils.getAssertClass( moduleChilds, "Test_JOHAB" );
			{
				IModelElement[] classTest_JOHAB2Childs = classTest_JOHAB2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_JOHAB2Childs, "encoding");
			}
			{
				IModelElement[] classTest_JOHAB2Childs = classTest_JOHAB2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_JOHAB2Childs, "tstring");
			}
			{
				IModelElement[] classTest_JOHAB2Childs = classTest_JOHAB2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_JOHAB2Childs, "codectests");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen285( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_httplib.py"));

		assertNotNull("Module test_httplib.py not found", module);
		assertEquals("test_httplib.py", module.getElementName());
		
		//Class test
		{
		IType classFakeSocket0;
			IModelElement[] moduleChilds = module.getChildren();
			classFakeSocket0 = ModelTestUtils.getAssertClass( moduleChilds, "FakeSocket" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classFakeSocket0Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "text", "fileclass"} );
			}
			//Function test:sendall
			{
			IMethod methodsendall2;
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				methodsendall2 = ModelTestUtils.getAssertMethod( classFakeSocket0Childs, "sendall", 2 );
				ModelTestUtils.assertParameterNames( methodsendall2, new String[] {"self", "data"} );
			}
			//Function test:makefile
			{
			IMethod methodmakefile3;
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				methodmakefile3 = ModelTestUtils.getAssertMethod( classFakeSocket0Childs, "makefile", 3 );
				ModelTestUtils.assertParameterNames( methodmakefile3, new String[] {"self", "mode", "bufsize"} );
			}
		}
		//Class test
		{
		IType classNoEOFStringIO4;
			IModelElement[] moduleChilds = module.getChildren();
			classNoEOFStringIO4 = ModelTestUtils.getAssertClass( moduleChilds, "NoEOFStringIO" );
			//Function test:read
			{
			IMethod methodread5;
				IModelElement[] classNoEOFStringIO4Childs = classNoEOFStringIO4.getChildren();
				methodread5 = ModelTestUtils.getAssertMethod( classNoEOFStringIO4Childs, "read", 2 );
				ModelTestUtils.assertParameterNames( methodread5, new String[] {"self", "n"} );
			}
			//Function test:readline
			{
			IMethod methodreadline6;
				IModelElement[] classNoEOFStringIO4Childs = classNoEOFStringIO4.getChildren();
				methodreadline6 = ModelTestUtils.getAssertMethod( classNoEOFStringIO4Childs, "readline", 2 );
				ModelTestUtils.assertParameterNames( methodreadline6, new String[] {"self", "length"} );
			}
		}
		//Class test
		{
		IType classHeaderTests7;
			IModelElement[] moduleChilds = module.getChildren();
			classHeaderTests7 = ModelTestUtils.getAssertClass( moduleChilds, "HeaderTests" );
			//Function test:test_auto_headers
			{
			IMethod methodtest_auto_headers8;
				IModelElement[] classHeaderTests7Childs = classHeaderTests7.getChildren();
				methodtest_auto_headers8 = ModelTestUtils.getAssertMethod( classHeaderTests7Childs, "test_auto_headers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_auto_headers8, new String[] {"self"} );
				//Class test
				{
				IType classHeaderCountingBuffer9;
					IModelElement[] methodtest_auto_headers8Childs = methodtest_auto_headers8.getChildren();
					classHeaderCountingBuffer9 = ModelTestUtils.getAssertClass( methodtest_auto_headers8Childs, "HeaderCountingBuffer" );
					//Function test:__init__
					{
					IMethod method__init__10;
						IModelElement[] classHeaderCountingBuffer9Childs = classHeaderCountingBuffer9.getChildren();
						method__init__10 = ModelTestUtils.getAssertMethod( classHeaderCountingBuffer9Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self"} );
					}
					//Function test:append
					{
					IMethod methodappend11;
						IModelElement[] classHeaderCountingBuffer9Childs = classHeaderCountingBuffer9.getChildren();
						methodappend11 = ModelTestUtils.getAssertMethod( classHeaderCountingBuffer9Childs, "append", 2 );
						ModelTestUtils.assertParameterNames( methodappend11, new String[] {"self", "item"} );
					}
				}
			}
		}
		//Function test:test
		{
		IMethod methodtest12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest12 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}
		//Function test:_test
		{
		IMethod method_test13;
			IModelElement[] moduleChilds = module.getChildren();
			method_test13 = ModelTestUtils.getAssertMethod( moduleChilds, "_test", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main14 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main14, new String[] {"verbose"} );
		}

	}
	public void testModelGen286( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_posixpath.py"));

		assertNotNull("Module test_posixpath.py not found", module);
		assertEquals("test_posixpath.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ABSTFN");
		}
		//Class test
		{
		IType classPosixPathTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classPosixPathTest0 = ModelTestUtils.getAssertClass( moduleChilds, "PosixPathTest" );
			//Function test:assertIs
			{
			IMethod methodassertIs1;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodassertIs1 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "assertIs", 3 );
				ModelTestUtils.assertParameterNames( methodassertIs1, new String[] {"self", "a", "b"} );
			}
			//Function test:test_normcase
			{
			IMethod methodtest_normcase2;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_normcase2 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_normcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_normcase2, new String[] {"self"} );
			}
			//Function test:test_join
			{
			IMethod methodtest_join3;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_join3 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_join", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join3, new String[] {"self"} );
			}
			//Function test:test_splitdrive
			{
			IMethod methodtest_splitdrive4;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_splitdrive4 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_splitdrive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitdrive4, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split5;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_split5 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split5, new String[] {"self"} );
			}
			//Function test:test_splitext
			{
			IMethod methodtest_splitext6;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_splitext6 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_splitext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitext6, new String[] {"self"} );
			}
			//Function test:test_isabs
			{
			IMethod methodtest_isabs7;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_isabs7 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_isabs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isabs7, new String[] {"self"} );
			}
			//Function test:test_splitdrive
			{
			IMethod methodtest_splitdrive8;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_splitdrive8 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_splitdrive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitdrive8, new String[] {"self"} );
			}
			//Function test:test_basename
			{
			IMethod methodtest_basename9;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_basename9 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_basename", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basename9, new String[] {"self"} );
			}
			//Function test:test_dirname
			{
			IMethod methodtest_dirname10;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_dirname10 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_dirname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dirname10, new String[] {"self"} );
			}
			//Function test:test_commonprefix
			{
			IMethod methodtest_commonprefix11;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_commonprefix11 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_commonprefix", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commonprefix11, new String[] {"self"} );
			}
			//Function test:test_getsize
			{
			IMethod methodtest_getsize12;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_getsize12 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_getsize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getsize12, new String[] {"self"} );
			}
			//Function test:test_time
			{
			IMethod methodtest_time13;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_time13 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_time13, new String[] {"self"} );
			}
			//Function test:test_islink
			{
			IMethod methodtest_islink14;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_islink14 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_islink", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islink14, new String[] {"self"} );
			}
			//Function test:test_exists
			{
			IMethod methodtest_exists15;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_exists15 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_exists", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exists15, new String[] {"self"} );
			}
			//Function test:test_isdir
			{
			IMethod methodtest_isdir16;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_isdir16 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_isdir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isdir16, new String[] {"self"} );
			}
			//Function test:test_isfile
			{
			IMethod methodtest_isfile17;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_isfile17 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_isfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isfile17, new String[] {"self"} );
				//Function test:test_samefile
				{
				IMethod methodtest_samefile18;
					IModelElement[] methodtest_isfile17Childs = methodtest_isfile17.getChildren();
					methodtest_samefile18 = ModelTestUtils.getAssertMethod( methodtest_isfile17Childs, "test_samefile", 1 );
					ModelTestUtils.assertParameterNames( methodtest_samefile18, new String[] {"self"} );
				}
			}
			//Function test:test_samestat
			{
			IMethod methodtest_samestat19;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_samestat19 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_samestat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_samestat19, new String[] {"self"} );
			}
			//Function test:test_ismount
			{
			IMethod methodtest_ismount20;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_ismount20 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_ismount", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ismount20, new String[] {"self"} );
			}
			//Function test:test_expanduser
			{
			IMethod methodtest_expanduser21;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_expanduser21 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_expanduser", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expanduser21, new String[] {"self"} );
			}
			//Function test:test_expandvars
			{
			IMethod methodtest_expandvars22;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_expandvars22 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_expandvars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expandvars22, new String[] {"self"} );
			}
			//Function test:test_normpath
			{
			IMethod methodtest_normpath23;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_normpath23 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_normpath", 1 );
				ModelTestUtils.assertParameterNames( methodtest_normpath23, new String[] {"self"} );
			}
			//Function test:test_abspath
			{
			IMethod methodtest_abspath24;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_abspath24 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_abspath", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abspath24, new String[] {"self"} );
			}
			//Function test:test_realpath
			{
			IMethod methodtest_realpath25;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_realpath25 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_realpath", 1 );
				ModelTestUtils.assertParameterNames( methodtest_realpath25, new String[] {"self"} );
			}
			//Function test:test_realpath_basic
			{
			IMethod methodtest_realpath_basic26;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_realpath_basic26 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_realpath_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_realpath_basic26, new String[] {"self"} );
			}
			//Function test:test_realpath_symlink_loops
			{
			IMethod methodtest_realpath_symlink_loops27;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_realpath_symlink_loops27 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_realpath_symlink_loops", 1 );
				ModelTestUtils.assertParameterNames( methodtest_realpath_symlink_loops27, new String[] {"self"} );
			}
			//Function test:test_realpath_resolve_parents
			{
			IMethod methodtest_realpath_resolve_parents28;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_realpath_resolve_parents28 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_realpath_resolve_parents", 1 );
				ModelTestUtils.assertParameterNames( methodtest_realpath_resolve_parents28, new String[] {"self"} );
			}
			//Function test:test_realpath_resolve_before_normalizing
			{
			IMethod methodtest_realpath_resolve_before_normalizing29;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodtest_realpath_resolve_before_normalizing29 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "test_realpath_resolve_before_normalizing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_realpath_resolve_before_normalizing29, new String[] {"self"} );
			}
			//Function test:pass_os_error
			{
			IMethod methodpass_os_error30;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodpass_os_error30 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "pass_os_error", 3 );
				ModelTestUtils.assertParameterNames( methodpass_os_error30, new String[] {"self", "func", "filename"} );
			}
			//Function test:safe_remove
			{
			IMethod methodsafe_remove31;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodsafe_remove31 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "safe_remove", 2 );
				ModelTestUtils.assertParameterNames( methodsafe_remove31, new String[] {"self", "filename"} );
			}
			//Function test:safe_rmdir
			{
			IMethod methodsafe_rmdir32;
				IModelElement[] classPosixPathTest0Childs = classPosixPathTest0.getChildren();
				methodsafe_rmdir32 = ModelTestUtils.getAssertMethod( classPosixPathTest0Childs, "safe_rmdir", 2 );
				ModelTestUtils.assertParameterNames( methodsafe_rmdir32, new String[] {"self", "dirname"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main33;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main33 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen287( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_distutils.py"));

		assertNotNull("Module test_distutils.py not found", module);
		assertEquals("test_distutils.py", module.getElementName());
		
		//Function test:test_main
		{
		IMethod methodtest_main0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen288( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_copy_reg.py"));

		assertNotNull("Module test_copy_reg.py not found", module);
		assertEquals("test_copy_reg.py", module.getElementName());
		
		//Class test
		{
		IType classC0;
			IModelElement[] moduleChilds = module.getChildren();
			classC0 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
		}
		//Class test
		{
		IType classCopyRegTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classCopyRegTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "CopyRegTestCase" );
			//Function test:test_class
			{
			IMethod methodtest_class2;
				IModelElement[] classCopyRegTestCase1Childs = classCopyRegTestCase1.getChildren();
				methodtest_class2 = ModelTestUtils.getAssertMethod( classCopyRegTestCase1Childs, "test_class", 1 );
				ModelTestUtils.assertParameterNames( methodtest_class2, new String[] {"self"} );
			}
			//Function test:test_noncallable_reduce
			{
			IMethod methodtest_noncallable_reduce3;
				IModelElement[] classCopyRegTestCase1Childs = classCopyRegTestCase1.getChildren();
				methodtest_noncallable_reduce3 = ModelTestUtils.getAssertMethod( classCopyRegTestCase1Childs, "test_noncallable_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_noncallable_reduce3, new String[] {"self"} );
			}
			//Function test:test_noncallable_constructor
			{
			IMethod methodtest_noncallable_constructor4;
				IModelElement[] classCopyRegTestCase1Childs = classCopyRegTestCase1.getChildren();
				methodtest_noncallable_constructor4 = ModelTestUtils.getAssertMethod( classCopyRegTestCase1Childs, "test_noncallable_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_noncallable_constructor4, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool5;
				IModelElement[] classCopyRegTestCase1Childs = classCopyRegTestCase1.getChildren();
				methodtest_bool5 = ModelTestUtils.getAssertMethod( classCopyRegTestCase1Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool5, new String[] {"self"} );
			}
			//Function test:test_extension_registry
			{
			IMethod methodtest_extension_registry6;
				IModelElement[] classCopyRegTestCase1Childs = classCopyRegTestCase1.getChildren();
				methodtest_extension_registry6 = ModelTestUtils.getAssertMethod( classCopyRegTestCase1Childs, "test_extension_registry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extension_registry6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen289( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("__init__.py"));

		assertNotNull("Module __init__.py not found", module);
		assertEquals("__init__.py", module.getElementName());
		

	}
	public void REM_testModelGen290( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_class.py"));

		assertNotNull("Module test_class.py not found", module);
		assertEquals("test_class.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testmeths");
		}
		//Class test
		{
		IType classAllTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classAllTests0 = ModelTestUtils.getAssertClass( moduleChilds, "AllTests" );
			//Function test:__coerce__
			{
			IMethod method__coerce__1;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__coerce__1 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__coerce__", 2 );
				ModelTestUtils.assertParameterNames( method__coerce__1, new String[] {"self", "args"} );
			}
			//Function test:__hash__
			{
			IMethod method__hash__2;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__hash__2 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__hash__", 2 );
				ModelTestUtils.assertParameterNames( method__hash__2, new String[] {"self", "args"} );
			}
			//Function test:__str__
			{
			IMethod method__str__3;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__str__3 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__str__", 2 );
				ModelTestUtils.assertParameterNames( method__str__3, new String[] {"self", "args"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__4;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__repr__4 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__repr__", 2 );
				ModelTestUtils.assertParameterNames( method__repr__4, new String[] {"self", "args"} );
			}
			//Function test:__int__
			{
			IMethod method__int__5;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__int__5 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__int__", 2 );
				ModelTestUtils.assertParameterNames( method__int__5, new String[] {"self", "args"} );
			}
			//Function test:__float__
			{
			IMethod method__float__6;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__float__6 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__float__", 2 );
				ModelTestUtils.assertParameterNames( method__float__6, new String[] {"self", "args"} );
			}
			//Function test:__long__
			{
			IMethod method__long__7;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__long__7 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__long__", 2 );
				ModelTestUtils.assertParameterNames( method__long__7, new String[] {"self", "args"} );
			}
			//Function test:__oct__
			{
			IMethod method__oct__8;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__oct__8 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__oct__", 2 );
				ModelTestUtils.assertParameterNames( method__oct__8, new String[] {"self", "args"} );
			}
			//Function test:__hex__
			{
			IMethod method__hex__9;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__hex__9 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__hex__", 2 );
				ModelTestUtils.assertParameterNames( method__hex__9, new String[] {"self", "args"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__10;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__cmp__10 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__10, new String[] {"self", "args"} );
			}
			//Function test:__del__
			{
			IMethod method__del__11;
				IModelElement[] classAllTests0Childs = classAllTests0.getChildren();
				method__del__11 = ModelTestUtils.getAssertMethod( classAllTests0Childs, "__del__", 2 );
				ModelTestUtils.assertParameterNames( method__del__11, new String[] {"self", "args"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "method_template");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testme");
		}
		//Class test
		{
		IType classExtraTests12;
			IModelElement[] moduleChilds = module.getChildren();
			classExtraTests12 = ModelTestUtils.getAssertClass( moduleChilds, "ExtraTests" );
			//Function test:__getattr__
			{
			IMethod method__getattr__13;
				IModelElement[] classExtraTests12Childs = classExtraTests12.getChildren();
				method__getattr__13 = ModelTestUtils.getAssertMethod( classExtraTests12Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__13, new String[] {"self", "args"} );
			}
			//Function test:__setattr__
			{
			IMethod method__setattr__14;
				IModelElement[] classExtraTests12Childs = classExtraTests12.getChildren();
				method__setattr__14 = ModelTestUtils.getAssertMethod( classExtraTests12Childs, "__setattr__", 2 );
				ModelTestUtils.assertParameterNames( method__setattr__14, new String[] {"self", "args"} );
			}
			//Function test:__delattr__
			{
			IMethod method__delattr__15;
				IModelElement[] classExtraTests12Childs = classExtraTests12.getChildren();
				method__delattr__15 = ModelTestUtils.getAssertMethod( classExtraTests12Childs, "__delattr__", 2 );
				ModelTestUtils.assertParameterNames( method__delattr__15, new String[] {"self", "args"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testme");
		}
		//Class test
		{
		IType classBadTypeClass16;
			IModelElement[] moduleChilds = module.getChildren();
			classBadTypeClass16 = ModelTestUtils.getAssertClass( moduleChilds, "BadTypeClass" );
			//Function test:__int__
			{
			IMethod method__int__17;
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				method__int__17 = ModelTestUtils.getAssertMethod( classBadTypeClass16Childs, "__int__", 1 );
				ModelTestUtils.assertParameterNames( method__int__17, new String[] {"self"} );
			}
			{
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadTypeClass16Childs, "__float__");
			}
			{
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadTypeClass16Childs, "__long__");
			}
			{
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadTypeClass16Childs, "__str__");
			}
			{
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadTypeClass16Childs, "__repr__");
			}
			{
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadTypeClass16Childs, "__oct__");
			}
			{
				IModelElement[] classBadTypeClass16Childs = classBadTypeClass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadTypeClass16Childs, "__hex__");
			}
		}
		//Function test:check_exc
		{
		IMethod methodcheck_exc18;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck_exc18 = ModelTestUtils.getAssertMethod( moduleChilds, "check_exc", 2 );
			ModelTestUtils.assertParameterNames( methodcheck_exc18, new String[] {"stmt", "exception"} );
		}
		//Class test
		{
		IType classIntLongMixClass19;
			IModelElement[] moduleChilds = module.getChildren();
			classIntLongMixClass19 = ModelTestUtils.getAssertClass( moduleChilds, "IntLongMixClass" );
			//Function test:__int__
			{
			IMethod method__int__20;
				IModelElement[] classIntLongMixClass19Childs = classIntLongMixClass19.getChildren();
				method__int__20 = ModelTestUtils.getAssertMethod( classIntLongMixClass19Childs, "__int__", 1 );
				ModelTestUtils.assertParameterNames( method__int__20, new String[] {"self"} );
			}
			//Function test:__long__
			{
			IMethod method__long__21;
				IModelElement[] classIntLongMixClass19Childs = classIntLongMixClass19.getChildren();
				method__long__21 = ModelTestUtils.getAssertMethod( classIntLongMixClass19Childs, "__long__", 1 );
				ModelTestUtils.assertParameterNames( method__long__21, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC022;
			IModelElement[] moduleChilds = module.getChildren();
			classC022 = ModelTestUtils.getAssertClass( moduleChilds, "C0" );
		}
		//Class test
		{
		IType classC123;
			IModelElement[] moduleChilds = module.getChildren();
			classC123 = ModelTestUtils.getAssertClass( moduleChilds, "C1" );
			//Function test:__cmp__
			{
			IMethod method__cmp__24;
				IModelElement[] classC123Childs = classC123.getChildren();
				method__cmp__24 = ModelTestUtils.getAssertMethod( classC123Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__24, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classC225;
			IModelElement[] moduleChilds = module.getChildren();
			classC225 = ModelTestUtils.getAssertClass( moduleChilds, "C2" );
			//Function test:__eq__
			{
			IMethod method__eq__26;
				IModelElement[] classC225Childs = classC225.getChildren();
				method__eq__26 = ModelTestUtils.getAssertMethod( classC225Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__26, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classA27;
			IModelElement[] moduleChilds = module.getChildren();
			classA27 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		//Function test:booh
		{
		IMethod methodbooh28;
			IModelElement[] moduleChilds = module.getChildren();
			methodbooh28 = ModelTestUtils.getAssertMethod( moduleChilds, "booh", 1 );
			ModelTestUtils.assertParameterNames( methodbooh28, new String[] {"self"} );
		}
		//Class test
		{
		IType classA29;
			IModelElement[] moduleChilds = module.getChildren();
			classA29 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
			{
				IModelElement[] classA29Childs = classA29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classA29Childs, "a");
			}
		}
		//Class test
		{
		IType classE30;
			IModelElement[] moduleChilds = module.getChildren();
			classE30 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			{
				IModelElement[] classE30Childs = classE30.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classE30Childs, "__eq__");
			}
		}
		//Class test
		{
		IType classI31;
			IModelElement[] moduleChilds = module.getChildren();
			classI31 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			{
				IModelElement[] classI31Childs = classI31.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI31Childs, "__init__");
			}
		}

	}
	public void testModelGen291( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_warnings.py"));

		assertNotNull("Module test_warnings.py not found", module);
		assertEquals("test_warnings.py", module.getElementName());
		
		//Class test
		{
		IType classWarningMessage0;
			IModelElement[] moduleChilds = module.getChildren();
			classWarningMessage0 = ModelTestUtils.getAssertClass( moduleChilds, "WarningMessage" );
		}
		//Function test:showwarning
		{
		IMethod methodshowwarning1;
			IModelElement[] moduleChilds = module.getChildren();
			methodshowwarning1 = ModelTestUtils.getAssertMethod( moduleChilds, "showwarning", 5 );
			ModelTestUtils.assertParameterNames( methodshowwarning1, new String[] {"message", "category", "filename", "lineno", "file"} );
		}
		//Class test
		{
		IType classTestModule2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestModule2 = ModelTestUtils.getAssertClass( moduleChilds, "TestModule" );
			//Function test:setUp
			{
			IMethod methodsetUp3;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodsetUp3 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp3, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown4;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtearDown4 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown4, new String[] {"self"} );
			}
			//Function test:test_warn_default_category
			{
			IMethod methodtest_warn_default_category5;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtest_warn_default_category5 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "test_warn_default_category", 1 );
				ModelTestUtils.assertParameterNames( methodtest_warn_default_category5, new String[] {"self"} );
			}
			//Function test:test_warn_specific_category
			{
			IMethod methodtest_warn_specific_category6;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtest_warn_specific_category6 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "test_warn_specific_category", 1 );
				ModelTestUtils.assertParameterNames( methodtest_warn_specific_category6, new String[] {"self"} );
			}
			//Function test:test_filtering
			{
			IMethod methodtest_filtering7;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtest_filtering7 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "test_filtering", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filtering7, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main8, new String[] {"verbose"} );
		}

	}
	public void testModelGen292( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codecencodings_tw.py"));

		assertNotNull("Module test_codecencodings_tw.py not found", module);
		assertEquals("test_codecencodings_tw.py", module.getElementName());
		
		//Class test
		{
		IType classTest_Big50;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_Big50 = ModelTestUtils.getAssertClass( moduleChilds, "Test_Big5" );
			{
				IModelElement[] classTest_Big50Childs = classTest_Big50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_Big50Childs, "encoding");
			}
			{
				IModelElement[] classTest_Big50Childs = classTest_Big50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_Big50Childs, "tstring");
			}
			{
				IModelElement[] classTest_Big50Childs = classTest_Big50.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTest_Big50Childs, "codectests");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen293( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_iter.py"));

		assertNotNull("Module test_iter.py not found", module);
		assertEquals("test_iter.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TRIPLETS");
		}
		//Class test
		{
		IType classBasicIterClass0;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicIterClass0 = ModelTestUtils.getAssertClass( moduleChilds, "BasicIterClass" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classBasicIterClass0Childs = classBasicIterClass0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classBasicIterClass0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "n"} );
			}
			//Function test:next
			{
			IMethod methodnext2;
				IModelElement[] classBasicIterClass0Childs = classBasicIterClass0.getChildren();
				methodnext2 = ModelTestUtils.getAssertMethod( classBasicIterClass0Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext2, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIteratingSequenceClass3;
			IModelElement[] moduleChilds = module.getChildren();
			classIteratingSequenceClass3 = ModelTestUtils.getAssertClass( moduleChilds, "IteratingSequenceClass" );
			//Function test:__init__
			{
			IMethod method__init__4;
				IModelElement[] classIteratingSequenceClass3Childs = classIteratingSequenceClass3.getChildren();
				method__init__4 = ModelTestUtils.getAssertMethod( classIteratingSequenceClass3Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__4, new String[] {"self", "n"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__5;
				IModelElement[] classIteratingSequenceClass3Childs = classIteratingSequenceClass3.getChildren();
				method__iter__5 = ModelTestUtils.getAssertMethod( classIteratingSequenceClass3Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSequenceClass6;
			IModelElement[] moduleChilds = module.getChildren();
			classSequenceClass6 = ModelTestUtils.getAssertClass( moduleChilds, "SequenceClass" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classSequenceClass6Childs = classSequenceClass6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classSequenceClass6Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "n"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__8;
				IModelElement[] classSequenceClass6Childs = classSequenceClass6.getChildren();
				method__getitem__8 = ModelTestUtils.getAssertMethod( classSequenceClass6Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__8, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classTestCase9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCase9 = ModelTestUtils.getAssertClass( moduleChilds, "TestCase" );
			//Function test:check_iterator
			{
			IMethod methodcheck_iterator10;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodcheck_iterator10 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "check_iterator", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_iterator10, new String[] {"self", "it", "seq"} );
			}
			//Function test:check_for_loop
			{
			IMethod methodcheck_for_loop11;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodcheck_for_loop11 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "check_for_loop", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_for_loop11, new String[] {"self", "expr", "seq"} );
			}
			//Function test:test_iter_basic
			{
			IMethod methodtest_iter_basic12;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_basic12 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_basic12, new String[] {"self"} );
			}
			//Function test:test_iter_idempotency
			{
			IMethod methodtest_iter_idempotency13;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_idempotency13 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_idempotency", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_idempotency13, new String[] {"self"} );
			}
			//Function test:test_iter_for_loop
			{
			IMethod methodtest_iter_for_loop14;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_for_loop14 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_for_loop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_for_loop14, new String[] {"self"} );
			}
			//Function test:test_iter_independence
			{
			IMethod methodtest_iter_independence15;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_independence15 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_independence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_independence15, new String[] {"self"} );
			}
			//Function test:test_nested_comprehensions_iter
			{
			IMethod methodtest_nested_comprehensions_iter16;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_nested_comprehensions_iter16 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_nested_comprehensions_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_comprehensions_iter16, new String[] {"self"} );
			}
			//Function test:test_nested_comprehensions_for
			{
			IMethod methodtest_nested_comprehensions_for17;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_nested_comprehensions_for17 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_nested_comprehensions_for", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_comprehensions_for17, new String[] {"self"} );
			}
			//Function test:test_iter_class_for
			{
			IMethod methodtest_iter_class_for18;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_class_for18 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_class_for", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_class_for18, new String[] {"self"} );
			}
			//Function test:test_iter_class_iter
			{
			IMethod methodtest_iter_class_iter19;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_class_iter19 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_class_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_class_iter19, new String[] {"self"} );
			}
			//Function test:test_seq_class_for
			{
			IMethod methodtest_seq_class_for20;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_seq_class_for20 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_seq_class_for", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seq_class_for20, new String[] {"self"} );
			}
			//Function test:test_seq_class_iter
			{
			IMethod methodtest_seq_class_iter21;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_seq_class_iter21 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_seq_class_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seq_class_iter21, new String[] {"self"} );
			}
			//Function test:test_iter_callable
			{
			IMethod methodtest_iter_callable22;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_callable22 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_callable22, new String[] {"self"} );
				//Class test
				{
				IType classC23;
					IModelElement[] methodtest_iter_callable22Childs = methodtest_iter_callable22.getChildren();
					classC23 = ModelTestUtils.getAssertClass( methodtest_iter_callable22Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__24;
						IModelElement[] classC23Childs = classC23.getChildren();
						method__init__24 = ModelTestUtils.getAssertMethod( classC23Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__24, new String[] {"self"} );
					}
					//Function test:__call__
					{
					IMethod method__call__25;
						IModelElement[] classC23Childs = classC23.getChildren();
						method__call__25 = ModelTestUtils.getAssertMethod( classC23Childs, "__call__", 1 );
						ModelTestUtils.assertParameterNames( method__call__25, new String[] {"self"} );
					}
				}
			}
			//Function test:test_iter_function
			{
			IMethod methodtest_iter_function26;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_function26 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_function26, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam27;
					IModelElement[] methodtest_iter_function26Childs = methodtest_iter_function26.getChildren();
					methodspam27 = ModelTestUtils.getAssertMethod( methodtest_iter_function26Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam27, new String[] {"state"} );
				}
			}
			//Function test:test_iter_function_stop
			{
			IMethod methodtest_iter_function_stop28;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_function_stop28 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_function_stop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_function_stop28, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam29;
					IModelElement[] methodtest_iter_function_stop28Childs = methodtest_iter_function_stop28.getChildren();
					methodspam29 = ModelTestUtils.getAssertMethod( methodtest_iter_function_stop28Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam29, new String[] {"state"} );
				}
			}
			//Function test:test_exception_function
			{
			IMethod methodtest_exception_function30;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_exception_function30 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_exception_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_function30, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam31;
					IModelElement[] methodtest_exception_function30Childs = methodtest_exception_function30.getChildren();
					methodspam31 = ModelTestUtils.getAssertMethod( methodtest_exception_function30Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam31, new String[] {"state"} );
				}
			}
			//Function test:test_exception_sequence
			{
			IMethod methodtest_exception_sequence32;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_exception_sequence32 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_exception_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_sequence32, new String[] {"self"} );
				//Class test
				{
				IType classMySequenceClass33;
					IModelElement[] methodtest_exception_sequence32Childs = methodtest_exception_sequence32.getChildren();
					classMySequenceClass33 = ModelTestUtils.getAssertClass( methodtest_exception_sequence32Childs, "MySequenceClass" );
					//Function test:__getitem__
					{
					IMethod method__getitem__34;
						IModelElement[] classMySequenceClass33Childs = classMySequenceClass33.getChildren();
						method__getitem__34 = ModelTestUtils.getAssertMethod( classMySequenceClass33Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__34, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_stop_sequence
			{
			IMethod methodtest_stop_sequence35;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_stop_sequence35 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_stop_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_sequence35, new String[] {"self"} );
				//Class test
				{
				IType classMySequenceClass36;
					IModelElement[] methodtest_stop_sequence35Childs = methodtest_stop_sequence35.getChildren();
					classMySequenceClass36 = ModelTestUtils.getAssertClass( methodtest_stop_sequence35Childs, "MySequenceClass" );
					//Function test:__getitem__
					{
					IMethod method__getitem__37;
						IModelElement[] classMySequenceClass36Childs = classMySequenceClass36.getChildren();
						method__getitem__37 = ModelTestUtils.getAssertMethod( classMySequenceClass36Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__37, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_iter_big_range
			{
			IMethod methodtest_iter_big_range38;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_big_range38 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_big_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_big_range38, new String[] {"self"} );
			}
			//Function test:test_iter_empty
			{
			IMethod methodtest_iter_empty39;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_empty39 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_empty39, new String[] {"self"} );
			}
			//Function test:test_iter_tuple
			{
			IMethod methodtest_iter_tuple40;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_tuple40 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_tuple40, new String[] {"self"} );
			}
			//Function test:test_iter_xrange
			{
			IMethod methodtest_iter_xrange41;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_xrange41 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_xrange", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_xrange41, new String[] {"self"} );
			}
			//Function test:test_iter_string
			{
			IMethod methodtest_iter_string42;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_string42 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_string42, new String[] {"self"} );
			}
			//Function test:test_iter_unicode
			{
			IMethod methodtest_iter_unicode43;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_unicode43 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_unicode43, new String[] {"self"} );
			}
			//Function test:test_iter_dict
			{
			IMethod methodtest_iter_dict44;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_dict44 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_dict44, new String[] {"self"} );
			}
			//Function test:test_iter_file
			{
			IMethod methodtest_iter_file45;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_iter_file45 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_iter_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_file45, new String[] {"self"} );
			}
			//Function test:test_builtin_list
			{
			IMethod methodtest_builtin_list46;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_list46 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_list46, new String[] {"self"} );
			}
			//Function test:test_builtin_tuple
			{
			IMethod methodtest_builtin_tuple47;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_tuple47 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_tuple47, new String[] {"self"} );
			}
			//Function test:test_builtin_filter
			{
			IMethod methodtest_builtin_filter48;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_filter48 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_filter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_filter48, new String[] {"self"} );
				//Class test
				{
				IType classBoolean49;
					IModelElement[] methodtest_builtin_filter48Childs = methodtest_builtin_filter48.getChildren();
					classBoolean49 = ModelTestUtils.getAssertClass( methodtest_builtin_filter48Childs, "Boolean" );
					//Function test:__init__
					{
					IMethod method__init__50;
						IModelElement[] classBoolean49Childs = classBoolean49.getChildren();
						method__init__50 = ModelTestUtils.getAssertMethod( classBoolean49Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__50, new String[] {"self", "truth"} );
					}
					//Function test:__nonzero__
					{
					IMethod method__nonzero__51;
						IModelElement[] classBoolean49Childs = classBoolean49.getChildren();
						method__nonzero__51 = ModelTestUtils.getAssertMethod( classBoolean49Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__51, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classSeq52;
					IModelElement[] methodtest_builtin_filter48Childs = methodtest_builtin_filter48.getChildren();
					classSeq52 = ModelTestUtils.getAssertClass( methodtest_builtin_filter48Childs, "Seq" );
					//Function test:__init__
					{
					IMethod method__init__53;
						IModelElement[] classSeq52Childs = classSeq52.getChildren();
						method__init__53 = ModelTestUtils.getAssertMethod( classSeq52Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__53, new String[] {"self", "args"} );
					}
					//Function test:__iter__
					{
					IMethod method__iter__54;
						IModelElement[] classSeq52Childs = classSeq52.getChildren();
						method__iter__54 = ModelTestUtils.getAssertMethod( classSeq52Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__54, new String[] {"self"} );
						//Class test
						{
						IType classSeqIter55;
							IModelElement[] method__iter__54Childs = method__iter__54.getChildren();
							classSeqIter55 = ModelTestUtils.getAssertClass( method__iter__54Childs, "SeqIter" );
							//Function test:__init__
							{
							IMethod method__init__56;
								IModelElement[] classSeqIter55Childs = classSeqIter55.getChildren();
								method__init__56 = ModelTestUtils.getAssertMethod( classSeqIter55Childs, "__init__", 2 );
								ModelTestUtils.assertParameterNames( method__init__56, new String[] {"self", "vals"} );
							}
							//Function test:__iter__
							{
							IMethod method__iter__57;
								IModelElement[] classSeqIter55Childs = classSeqIter55.getChildren();
								method__iter__57 = ModelTestUtils.getAssertMethod( classSeqIter55Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__57, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext58;
								IModelElement[] classSeqIter55Childs = classSeqIter55.getChildren();
								methodnext58 = ModelTestUtils.getAssertMethod( classSeqIter55Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext58, new String[] {"self"} );
							}
						}
					}
				}
			}
			//Function test:test_builtin_max_min
			{
			IMethod methodtest_builtin_max_min59;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_max_min59 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_max_min", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_max_min59, new String[] {"self"} );
			}
			//Function test:test_builtin_map
			{
			IMethod methodtest_builtin_map60;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_map60 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_map", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_map60, new String[] {"self"} );
			}
			//Function test:test_builtin_zip
			{
			IMethod methodtest_builtin_zip61;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_zip61 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_zip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_zip61, new String[] {"self"} );
				//Class test
				{
				IType classIntsFrom62;
					IModelElement[] methodtest_builtin_zip61Childs = methodtest_builtin_zip61.getChildren();
					classIntsFrom62 = ModelTestUtils.getAssertClass( methodtest_builtin_zip61Childs, "IntsFrom" );
					//Function test:__init__
					{
					IMethod method__init__63;
						IModelElement[] classIntsFrom62Childs = classIntsFrom62.getChildren();
						method__init__63 = ModelTestUtils.getAssertMethod( classIntsFrom62Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__63, new String[] {"self", "start"} );
					}
					//Function test:__iter__
					{
					IMethod method__iter__64;
						IModelElement[] classIntsFrom62Childs = classIntsFrom62.getChildren();
						method__iter__64 = ModelTestUtils.getAssertMethod( classIntsFrom62Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__64, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext65;
						IModelElement[] classIntsFrom62Childs = classIntsFrom62.getChildren();
						methodnext65 = ModelTestUtils.getAssertMethod( classIntsFrom62Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext65, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classNoGuessLen566;
					IModelElement[] methodtest_builtin_zip61Childs = methodtest_builtin_zip61.getChildren();
					classNoGuessLen566 = ModelTestUtils.getAssertClass( methodtest_builtin_zip61Childs, "NoGuessLen5" );
					//Function test:__getitem__
					{
					IMethod method__getitem__67;
						IModelElement[] classNoGuessLen566Childs = classNoGuessLen566.getChildren();
						method__getitem__67 = ModelTestUtils.getAssertMethod( classNoGuessLen566Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__67, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classGuess3Len568;
					IModelElement[] methodtest_builtin_zip61Childs = methodtest_builtin_zip61.getChildren();
					classGuess3Len568 = ModelTestUtils.getAssertClass( methodtest_builtin_zip61Childs, "Guess3Len5" );
					//Function test:__len__
					{
					IMethod method__len__69;
						IModelElement[] classGuess3Len568Childs = classGuess3Len568.getChildren();
						method__len__69 = ModelTestUtils.getAssertMethod( classGuess3Len568Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__69, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classGuess30Len570;
					IModelElement[] methodtest_builtin_zip61Childs = methodtest_builtin_zip61.getChildren();
					classGuess30Len570 = ModelTestUtils.getAssertClass( methodtest_builtin_zip61Childs, "Guess30Len5" );
					//Function test:__len__
					{
					IMethod method__len__71;
						IModelElement[] classGuess30Len570Childs = classGuess30Len570.getChildren();
						method__len__71 = ModelTestUtils.getAssertMethod( classGuess30Len570Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__71, new String[] {"self"} );
					}
				}
			}
			//Function test:test_builtin_reduce
			{
			IMethod methodtest_builtin_reduce72;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_builtin_reduce72 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_builtin_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_reduce72, new String[] {"self"} );
			}
			//Function test:test_unicode_join_endcase
			{
			IMethod methodtest_unicode_join_endcase73;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_unicode_join_endcase73 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_unicode_join_endcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_join_endcase73, new String[] {"self"} );
				//Class test
				{
				IType classOhPhooey74;
					IModelElement[] methodtest_unicode_join_endcase73Childs = methodtest_unicode_join_endcase73.getChildren();
					classOhPhooey74 = ModelTestUtils.getAssertClass( methodtest_unicode_join_endcase73Childs, "OhPhooey" );
					//Function test:__init__
					{
					IMethod method__init__75;
						IModelElement[] classOhPhooey74Childs = classOhPhooey74.getChildren();
						method__init__75 = ModelTestUtils.getAssertMethod( classOhPhooey74Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__75, new String[] {"self", "seq"} );
					}
					//Function test:__iter__
					{
					IMethod method__iter__76;
						IModelElement[] classOhPhooey74Childs = classOhPhooey74.getChildren();
						method__iter__76 = ModelTestUtils.getAssertMethod( classOhPhooey74Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__76, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext77;
						IModelElement[] classOhPhooey74Childs = classOhPhooey74.getChildren();
						methodnext77 = ModelTestUtils.getAssertMethod( classOhPhooey74Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext77, new String[] {"self"} );
					}
				}
			}
			//Function test:test_unicode_join_endcase
			{
			IMethod methodtest_unicode_join_endcase78;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_unicode_join_endcase78 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_unicode_join_endcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_join_endcase78, new String[] {"self"} );
			}
			//Function test:test_in_and_not_in
			{
			IMethod methodtest_in_and_not_in79;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_in_and_not_in79 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_in_and_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in_and_not_in79, new String[] {"self"} );
			}
			//Function test:test_countOf
			{
			IMethod methodtest_countOf80;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_countOf80 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_countOf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_countOf80, new String[] {"self"} );
			}
			//Function test:test_indexOf
			{
			IMethod methodtest_indexOf81;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_indexOf81 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_indexOf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_indexOf81, new String[] {"self"} );
			}
			//Function test:test_writelines
			{
			IMethod methodtest_writelines82;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_writelines82 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_writelines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writelines82, new String[] {"self"} );
				//Class test
				{
				IType classIterator83;
					IModelElement[] methodtest_writelines82Childs = methodtest_writelines82.getChildren();
					classIterator83 = ModelTestUtils.getAssertClass( methodtest_writelines82Childs, "Iterator" );
					//Function test:__init__
					{
					IMethod method__init__84;
						IModelElement[] classIterator83Childs = classIterator83.getChildren();
						method__init__84 = ModelTestUtils.getAssertMethod( classIterator83Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__84, new String[] {"self", "start", "finish"} );
					}
					//Function test:next
					{
					IMethod methodnext85;
						IModelElement[] classIterator83Childs = classIterator83.getChildren();
						methodnext85 = ModelTestUtils.getAssertMethod( classIterator83Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext85, new String[] {"self"} );
					}
					//Function test:__iter__
					{
					IMethod method__iter__86;
						IModelElement[] classIterator83Childs = classIterator83.getChildren();
						method__iter__86 = ModelTestUtils.getAssertMethod( classIterator83Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__86, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classWhatever87;
					IModelElement[] methodtest_writelines82Childs = methodtest_writelines82.getChildren();
					classWhatever87 = ModelTestUtils.getAssertClass( methodtest_writelines82Childs, "Whatever" );
					//Function test:__init__
					{
					IMethod method__init__88;
						IModelElement[] classWhatever87Childs = classWhatever87.getChildren();
						method__init__88 = ModelTestUtils.getAssertMethod( classWhatever87Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__88, new String[] {"self", "start", "finish"} );
					}
					//Function test:__iter__
					{
					IMethod method__iter__89;
						IModelElement[] classWhatever87Childs = classWhatever87.getChildren();
						method__iter__89 = ModelTestUtils.getAssertMethod( classWhatever87Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__89, new String[] {"self"} );
					}
				}
			}
			//Function test:test_unpack_iter
			{
			IMethod methodtest_unpack_iter90;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_unpack_iter90 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_unpack_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unpack_iter90, new String[] {"self"} );
				//Class test
				{
				IType classC91;
					IModelElement[] methodtest_unpack_iter90Childs = methodtest_unpack_iter90.getChildren();
					classC91 = ModelTestUtils.getAssertClass( methodtest_unpack_iter90Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__92;
						IModelElement[] classC91Childs = classC91.getChildren();
						method__new__92 = ModelTestUtils.getAssertMethod( classC91Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__92, new String[] {"cls"} );
					}
					//Function test:__del__
					{
					IMethod method__del__93;
						IModelElement[] classC91Childs = classC91.getChildren();
						method__del__93 = ModelTestUtils.getAssertMethod( classC91Childs, "__del__", 1 );
						ModelTestUtils.assertParameterNames( method__del__93, new String[] {"self"} );
					}
				}
			}
			//Function test:test_sinkstate_list
			{
			IMethod methodtest_sinkstate_list94;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_list94 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_list94, new String[] {"self"} );
			}
			//Function test:test_sinkstate_tuple
			{
			IMethod methodtest_sinkstate_tuple95;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_tuple95 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_tuple95, new String[] {"self"} );
			}
			//Function test:test_sinkstate_string
			{
			IMethod methodtest_sinkstate_string96;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_string96 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_string96, new String[] {"self"} );
			}
			//Function test:test_sinkstate_sequence
			{
			IMethod methodtest_sinkstate_sequence97;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_sequence97 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_sequence97, new String[] {"self"} );
			}
			//Function test:test_sinkstate_callable
			{
			IMethod methodtest_sinkstate_callable98;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_callable98 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_callable98, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam99;
					IModelElement[] methodtest_sinkstate_callable98Childs = methodtest_sinkstate_callable98.getChildren();
					methodspam99 = ModelTestUtils.getAssertMethod( methodtest_sinkstate_callable98Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam99, new String[] {"state"} );
				}
			}
			//Function test:test_sinkstate_dict
			{
			IMethod methodtest_sinkstate_dict100;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_dict100 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_dict100, new String[] {"self"} );
			}
			//Function test:test_sinkstate_yield
			{
			IMethod methodtest_sinkstate_yield101;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_yield101 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_yield", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_yield101, new String[] {"self"} );
				//Function test:gen
				{
				IMethod methodgen102;
					IModelElement[] methodtest_sinkstate_yield101Childs = methodtest_sinkstate_yield101.getChildren();
					methodgen102 = ModelTestUtils.getAssertMethod( methodtest_sinkstate_yield101Childs, "gen", 0 );
				}
			}
			//Function test:test_sinkstate_range
			{
			IMethod methodtest_sinkstate_range103;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_range103 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_range103, new String[] {"self"} );
			}
			//Function test:test_sinkstate_enumerate
			{
			IMethod methodtest_sinkstate_enumerate104;
				IModelElement[] classTestCase9Childs = classTestCase9.getChildren();
				methodtest_sinkstate_enumerate104 = ModelTestUtils.getAssertMethod( classTestCase9Childs, "test_sinkstate_enumerate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_enumerate104, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main105;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main105 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen294( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_genexps.py"));

		assertNotNull("Module test_genexps.py not found", module);
		assertEquals("test_genexps.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "doctests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}
		//Function test:test_main
		{
		IMethod methodtest_main0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main0, new String[] {"verbose"} );
		}

	}
	public void testModelGen295( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test___future__.py"));

		assertNotNull("Module test___future__.py not found", module);
		assertEquals("test___future__.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "GOOD_SERIALS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "features");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "given_feature_names");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "obj");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "value");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "optional");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mandatory");
		}

	}
	public void testModelGen296( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_getargs.py"));

		assertNotNull("Module test_getargs.py not found", module);
		assertEquals("test_getargs.py", module.getElementName());
		

	}
	public void testModelGen297( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("regex_tests.py"));

		assertNotNull("Module regex_tests.py not found", module);
		assertEquals("regex_tests.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "benchmarks");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}

	}
	public void testModelGen298( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_popen2.py"));

		assertNotNull("Module test_popen2.py not found", module);
		assertEquals("test_popen2.py", module.getElementName());
		
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}
		//Function test:_test
		{
		IMethod method_test1;
			IModelElement[] moduleChilds = module.getChildren();
			method_test1 = ModelTestUtils.getAssertMethod( moduleChilds, "_test", 0 );
		}

	}
	public void testModelGen299( ) throws Exception {
		String prj = "pytests_5";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_plistlib.py"));

		assertNotNull("Module test_plistlib.py not found", module);
		assertEquals("test_plistlib.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTDATA");
		}
		//Class test
		{
		IType classTestPlistlib0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestPlistlib0 = ModelTestUtils.getAssertClass( moduleChilds, "TestPlistlib" );
			//Function test:tearDown
			{
			IMethod methodtearDown1;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtearDown1 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown1, new String[] {"self"} );
			}
			//Function test:_create
			{
			IMethod method_create2;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				method_create2 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "_create", 1 );
				ModelTestUtils.assertParameterNames( method_create2, new String[] {"self"} );
			}
			//Function test:test_create
			{
			IMethod methodtest_create3;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_create3 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_create", 1 );
				ModelTestUtils.assertParameterNames( methodtest_create3, new String[] {"self"} );
			}
			//Function test:test_io
			{
			IMethod methodtest_io4;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_io4 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_io", 1 );
				ModelTestUtils.assertParameterNames( methodtest_io4, new String[] {"self"} );
			}
			//Function test:test_string
			{
			IMethod methodtest_string5;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_string5 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_string5, new String[] {"self"} );
			}
			//Function test:test_appleformatting
			{
			IMethod methodtest_appleformatting6;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_appleformatting6 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_appleformatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_appleformatting6, new String[] {"self"} );
			}
			//Function test:test_appleformattingfromliteral
			{
			IMethod methodtest_appleformattingfromliteral7;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_appleformattingfromliteral7 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_appleformattingfromliteral", 1 );
				ModelTestUtils.assertParameterNames( methodtest_appleformattingfromliteral7, new String[] {"self"} );
			}
			//Function test:test_stringio
			{
			IMethod methodtest_stringio8;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_stringio8 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_stringio", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stringio8, new String[] {"self"} );
			}
			//Function test:test_cstringio
			{
			IMethod methodtest_cstringio9;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_cstringio9 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_cstringio", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cstringio9, new String[] {"self"} );
			}
			//Function test:test_controlcharacters
			{
			IMethod methodtest_controlcharacters10;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_controlcharacters10 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_controlcharacters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_controlcharacters10, new String[] {"self"} );
			}
			//Function test:test_nondictroot
			{
			IMethod methodtest_nondictroot11;
				IModelElement[] classTestPlistlib0Childs = classTestPlistlib0.getChildren();
				methodtest_nondictroot11 = ModelTestUtils.getAssertMethod( classTestPlistlib0Childs, "test_nondictroot", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nondictroot11, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}

}
	