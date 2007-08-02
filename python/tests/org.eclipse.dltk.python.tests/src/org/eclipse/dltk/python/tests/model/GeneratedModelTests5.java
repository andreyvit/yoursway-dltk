
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
			{
				IModelElement[] classSequence0Childs = classSequence0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSequence0Childs, "seq");
			}
			//Function test:__len__
			{
			IMethod method__len__3;
				IModelElement[] classSequence0Childs = classSequence0.getChildren();
				method__len__3 = ModelTestUtils.getAssertMethod( classSequence0Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__3, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__4;
				IModelElement[] classSequence0Childs = classSequence0.getChildren();
				method__getitem__4 = ModelTestUtils.getAssertMethod( classSequence0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__4, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classBadSeq15;
			IModelElement[] moduleChilds = module.getChildren();
			classBadSeq15 = ModelTestUtils.getAssertClass( moduleChilds, "BadSeq1" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classBadSeq15Childs = classBadSeq15.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classBadSeq15Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self"} );
			}
			{
				IModelElement[] classBadSeq15Childs = classBadSeq15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadSeq15Childs, "seq");
			}
		}
		//Class test
		{
		IType classBadSeq28;
			IModelElement[] moduleChilds = module.getChildren();
			classBadSeq28 = ModelTestUtils.getAssertClass( moduleChilds, "BadSeq2" );
			//Function test:__init__
			{
			IMethod method__init__9;
				IModelElement[] classBadSeq28Childs = classBadSeq28.getChildren();
				method__init__9 = ModelTestUtils.getAssertMethod( classBadSeq28Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__9, new String[] {"self"} );
			}
			{
				IModelElement[] classBadSeq28Childs = classBadSeq28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadSeq28Childs, "seq");
			}
			//Function test:__len__
			{
			IMethod method__len__11;
				IModelElement[] classBadSeq28Childs = classBadSeq28.getChildren();
				method__len__11 = ModelTestUtils.getAssertMethod( classBadSeq28Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCommonTest12;
			IModelElement[] moduleChilds = module.getChildren();
			classCommonTest12 = ModelTestUtils.getAssertClass( moduleChilds, "CommonTest" );
			{
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCommonTest12Childs, "type2test");
			}
			//Function test:fixtype
			{
			IMethod methodfixtype13;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodfixtype13 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "fixtype", 2 );
				ModelTestUtils.assertParameterNames( methodfixtype13, new String[] {"self", "obj"} );
			}
			//Function test:checkequal
			{
			IMethod methodcheckequal14;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodcheckequal14 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "checkequal", 5 );
				ModelTestUtils.assertParameterNames( methodcheckequal14, new String[] {"self", "result", "object", "methodname", "args"} );
				//Class test
				{
				IType classsubtype15;
					IModelElement[] methodcheckequal14Childs = methodcheckequal14.getChildren();
					classsubtype15 = ModelTestUtils.getAssertClass( methodcheckequal14Childs, "subtype" );
				}
			}
			//Function test:checkraises
			{
			IMethod methodcheckraises16;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodcheckraises16 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "checkraises", 5 );
				ModelTestUtils.assertParameterNames( methodcheckraises16, new String[] {"self", "exc", "object", "methodname", "args"} );
			}
			//Function test:checkcall
			{
			IMethod methodcheckcall17;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodcheckcall17 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "checkcall", 4 );
				ModelTestUtils.assertParameterNames( methodcheckcall17, new String[] {"self", "object", "methodname", "args"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash18;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_hash18 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash18, new String[] {"self"} );
			}
			//Function test:test_capitalize
			{
			IMethod methodtest_capitalize19;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_capitalize19 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_capitalize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_capitalize19, new String[] {"self"} );
			}
			//Function test:test_count
			{
			IMethod methodtest_count20;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_count20 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_count", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count20, new String[] {"self"} );
			}
			//Function test:test_find
			{
			IMethod methodtest_find21;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_find21 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_find", 1 );
				ModelTestUtils.assertParameterNames( methodtest_find21, new String[] {"self"} );
			}
			//Function test:test_rfind
			{
			IMethod methodtest_rfind22;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_rfind22 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_rfind", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rfind22, new String[] {"self"} );
			}
			//Function test:test_index
			{
			IMethod methodtest_index23;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_index23 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_index", 1 );
				ModelTestUtils.assertParameterNames( methodtest_index23, new String[] {"self"} );
			}
			//Function test:test_rindex
			{
			IMethod methodtest_rindex24;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_rindex24 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_rindex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rindex24, new String[] {"self"} );
			}
			//Function test:test_lower
			{
			IMethod methodtest_lower25;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_lower25 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_lower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lower25, new String[] {"self"} );
			}
			//Function test:test_upper
			{
			IMethod methodtest_upper26;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_upper26 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_upper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_upper26, new String[] {"self"} );
			}
			//Function test:test_expandtabs
			{
			IMethod methodtest_expandtabs27;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_expandtabs27 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_expandtabs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expandtabs27, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split28;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_split28 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split28, new String[] {"self"} );
			}
			//Function test:test_rsplit
			{
			IMethod methodtest_rsplit29;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_rsplit29 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_rsplit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rsplit29, new String[] {"self"} );
			}
			//Function test:test_strip
			{
			IMethod methodtest_strip30;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_strip30 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_strip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strip30, new String[] {"self"} );
			}
			//Function test:test_ljust
			{
			IMethod methodtest_ljust31;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_ljust31 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_ljust", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ljust31, new String[] {"self"} );
			}
			//Function test:test_rjust
			{
			IMethod methodtest_rjust32;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_rjust32 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_rjust", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rjust32, new String[] {"self"} );
			}
			//Function test:test_center
			{
			IMethod methodtest_center33;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_center33 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_center", 1 );
				ModelTestUtils.assertParameterNames( methodtest_center33, new String[] {"self"} );
			}
			//Function test:test_swapcase
			{
			IMethod methodtest_swapcase34;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_swapcase34 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_swapcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_swapcase34, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace35;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_replace35 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace35, new String[] {"self"} );
			}
			//Function test:test_zfill
			{
			IMethod methodtest_zfill36;
				IModelElement[] classCommonTest12Childs = classCommonTest12.getChildren();
				methodtest_zfill36 = ModelTestUtils.getAssertMethod( classCommonTest12Childs, "test_zfill", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zfill36, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrUnicodeUserStringTest37;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrUnicodeUserStringTest37 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrUnicodeUserStringTest" );
			//Function test:test_islower
			{
			IMethod methodtest_islower38;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_islower38 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_islower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islower38, new String[] {"self"} );
			}
			//Function test:test_isupper
			{
			IMethod methodtest_isupper39;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_isupper39 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_isupper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isupper39, new String[] {"self"} );
			}
			//Function test:test_istitle
			{
			IMethod methodtest_istitle40;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_istitle40 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_istitle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_istitle40, new String[] {"self"} );
			}
			//Function test:test_isspace
			{
			IMethod methodtest_isspace41;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_isspace41 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_isspace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isspace41, new String[] {"self"} );
			}
			//Function test:test_isalpha
			{
			IMethod methodtest_isalpha42;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_isalpha42 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_isalpha", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isalpha42, new String[] {"self"} );
			}
			//Function test:test_isalnum
			{
			IMethod methodtest_isalnum43;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_isalnum43 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_isalnum", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isalnum43, new String[] {"self"} );
			}
			//Function test:test_isdigit
			{
			IMethod methodtest_isdigit44;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_isdigit44 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_isdigit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isdigit44, new String[] {"self"} );
			}
			//Function test:test_title
			{
			IMethod methodtest_title45;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_title45 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_title", 1 );
				ModelTestUtils.assertParameterNames( methodtest_title45, new String[] {"self"} );
			}
			//Function test:test_splitlines
			{
			IMethod methodtest_splitlines46;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_splitlines46 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_splitlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_splitlines46, new String[] {"self"} );
			}
			//Function test:test_startswith
			{
			IMethod methodtest_startswith47;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_startswith47 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_startswith", 1 );
				ModelTestUtils.assertParameterNames( methodtest_startswith47, new String[] {"self"} );
			}
			//Function test:test_endswith
			{
			IMethod methodtest_endswith48;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_endswith48 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_endswith", 1 );
				ModelTestUtils.assertParameterNames( methodtest_endswith48, new String[] {"self"} );
			}
			//Function test:test___contains__
			{
			IMethod methodtest___contains__49;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest___contains__49 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test___contains__", 1 );
				ModelTestUtils.assertParameterNames( methodtest___contains__49, new String[] {"self"} );
			}
			//Function test:test_subscript
			{
			IMethod methodtest_subscript50;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_subscript50 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_subscript", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subscript50, new String[] {"self"} );
			}
			//Function test:test_slice
			{
			IMethod methodtest_slice51;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_slice51 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_slice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_slice51, new String[] {"self"} );
			}
			//Function test:test_mul
			{
			IMethod methodtest_mul52;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_mul52 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_mul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mul52, new String[] {"self"} );
			}
			//Function test:test_join
			{
			IMethod methodtest_join53;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_join53 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_join", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join53, new String[] {"self"} );
			}
			//Function test:test_formatting
			{
			IMethod methodtest_formatting54;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_formatting54 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_formatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_formatting54, new String[] {"self"} );
			}
			//Function test:test_floatformatting
			{
			IMethod methodtest_floatformatting55;
				IModelElement[] classMixinStrUnicodeUserStringTest37Childs = classMixinStrUnicodeUserStringTest37.getChildren();
				methodtest_floatformatting55 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeUserStringTest37Childs, "test_floatformatting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floatformatting55, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrStringUserStringTest56;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrStringUserStringTest56 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrStringUserStringTest" );
			//Function test:test_maketrans
			{
			IMethod methodtest_maketrans57;
				IModelElement[] classMixinStrStringUserStringTest56Childs = classMixinStrStringUserStringTest56.getChildren();
				methodtest_maketrans57 = ModelTestUtils.getAssertMethod( classMixinStrStringUserStringTest56Childs, "test_maketrans", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maketrans57, new String[] {"self"} );
			}
			//Function test:test_translate
			{
			IMethod methodtest_translate58;
				IModelElement[] classMixinStrStringUserStringTest56Childs = classMixinStrStringUserStringTest56.getChildren();
				methodtest_translate58 = ModelTestUtils.getAssertMethod( classMixinStrStringUserStringTest56Childs, "test_translate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_translate58, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrUserStringTest59;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrUserStringTest59 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrUserStringTest" );
			//Function test:test_encoding_decoding
			{
			IMethod methodtest_encoding_decoding60;
				IModelElement[] classMixinStrUserStringTest59Childs = classMixinStrUserStringTest59.getChildren();
				methodtest_encoding_decoding60 = ModelTestUtils.getAssertMethod( classMixinStrUserStringTest59Childs, "test_encoding_decoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encoding_decoding60, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMixinStrUnicodeTest61;
			IModelElement[] moduleChilds = module.getChildren();
			classMixinStrUnicodeTest61 = ModelTestUtils.getAssertClass( moduleChilds, "MixinStrUnicodeTest" );
			//Function test:test_bug1001011
			{
			IMethod methodtest_bug100101162;
				IModelElement[] classMixinStrUnicodeTest61Childs = classMixinStrUnicodeTest61.getChildren();
				methodtest_bug100101162 = ModelTestUtils.getAssertMethod( classMixinStrUnicodeTest61Childs, "test_bug1001011", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug100101162, new String[] {"self"} );
				//Class test
				{
				IType classsubclass63;
					IModelElement[] methodtest_bug100101162Childs = methodtest_bug100101162.getChildren();
					classsubclass63 = ModelTestUtils.getAssertClass( methodtest_bug100101162Childs, "subclass" );
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
			{
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCreationTestCase0Childs, "sock");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:testObjectCreation
			{
			IMethod methodtestObjectCreation4;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestObjectCreation4 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testObjectCreation", 1 );
				ModelTestUtils.assertParameterNames( methodtestObjectCreation4, new String[] {"self"} );
			}
			//Function test:testFloatReturnValue
			{
			IMethod methodtestFloatReturnValue5;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestFloatReturnValue5 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testFloatReturnValue", 1 );
				ModelTestUtils.assertParameterNames( methodtestFloatReturnValue5, new String[] {"self"} );
			}
			//Function test:testReturnType
			{
			IMethod methodtestReturnType6;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestReturnType6 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testReturnType", 1 );
				ModelTestUtils.assertParameterNames( methodtestReturnType6, new String[] {"self"} );
			}
			//Function test:testTypeCheck
			{
			IMethod methodtestTypeCheck7;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestTypeCheck7 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testTypeCheck", 1 );
				ModelTestUtils.assertParameterNames( methodtestTypeCheck7, new String[] {"self"} );
			}
			//Function test:testRangeCheck
			{
			IMethod methodtestRangeCheck8;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestRangeCheck8 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testRangeCheck", 1 );
				ModelTestUtils.assertParameterNames( methodtestRangeCheck8, new String[] {"self"} );
			}
			//Function test:testTimeoutThenBlocking
			{
			IMethod methodtestTimeoutThenBlocking9;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestTimeoutThenBlocking9 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testTimeoutThenBlocking", 1 );
				ModelTestUtils.assertParameterNames( methodtestTimeoutThenBlocking9, new String[] {"self"} );
			}
			//Function test:testBlockingThenTimeout
			{
			IMethod methodtestBlockingThenTimeout10;
				IModelElement[] classCreationTestCase0Childs = classCreationTestCase0.getChildren();
				methodtestBlockingThenTimeout10 = ModelTestUtils.getAssertMethod( classCreationTestCase0Childs, "testBlockingThenTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestBlockingThenTimeout10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTimeoutTestCase11;
			IModelElement[] moduleChilds = module.getChildren();
			classTimeoutTestCase11 = ModelTestUtils.getAssertClass( moduleChilds, "TimeoutTestCase" );
			{
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeoutTestCase11Childs, "fuzz");
			}
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
			}
			{
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeoutTestCase11Childs, "sock");
			}
			{
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeoutTestCase11Childs, "addr_remote");
			}
			{
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeoutTestCase11Childs, "addr_local");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown14;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtearDown14 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown14, new String[] {"self"} );
			}
			//Function test:testConnectTimeout
			{
			IMethod methodtestConnectTimeout15;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestConnectTimeout15 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testConnectTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestConnectTimeout15, new String[] {"self"} );
			}
			//Function test:testRecvTimeout
			{
			IMethod methodtestRecvTimeout16;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestRecvTimeout16 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testRecvTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvTimeout16, new String[] {"self"} );
			}
			//Function test:testAcceptTimeout
			{
			IMethod methodtestAcceptTimeout17;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestAcceptTimeout17 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testAcceptTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestAcceptTimeout17, new String[] {"self"} );
			}
			//Function test:testRecvfromTimeout
			{
			IMethod methodtestRecvfromTimeout18;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestRecvfromTimeout18 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testRecvfromTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvfromTimeout18, new String[] {"self"} );
			}
			{
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeoutTestCase11Childs, "sock");
			}
			//Function test:testSend
			{
			IMethod methodtestSend20;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestSend20 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testSend", 1 );
				ModelTestUtils.assertParameterNames( methodtestSend20, new String[] {"self"} );
			}
			//Function test:testSendto
			{
			IMethod methodtestSendto21;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestSendto21 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testSendto", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendto21, new String[] {"self"} );
			}
			//Function test:testSendall
			{
			IMethod methodtestSendall22;
				IModelElement[] classTimeoutTestCase11Childs = classTimeoutTestCase11.getChildren();
				methodtestSendall22 = ModelTestUtils.getAssertMethod( classTimeoutTestCase11Childs, "testSendall", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendall22, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
	public void testModelGen255( ) throws Exception {
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
					{
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classEditableString44Childs, "color");
					}
					//Function test:strip
					{
					IMethod methodstrip48;
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						methodstrip48 = ModelTestUtils.getAssertMethod( classEditableString44Childs, "strip", 1 );
						ModelTestUtils.assertParameterNames( methodstrip48, new String[] {"self"} );
					}
					{
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classEditableString44Childs, "OP_ASSIGN");
					}
					//Function test:__repr__
					{
					IMethod method__repr__50;
						IModelElement[] classEditableString44Childs = classEditableString44.getChildren();
						method__repr__50 = ModelTestUtils.getAssertMethod( classEditableString44Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__50, new String[] {"self"} );
					}
				}
			}
			//Function test:test_nounicode
			{
			IMethod methodtest_nounicode51;
				IModelElement[] classCharacterTest42Childs = classCharacterTest42.getChildren();
				methodtest_nounicode51 = ModelTestUtils.getAssertMethod( classCharacterTest42Childs, "test_nounicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nounicode51, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnicodeTest52;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeTest52 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeTest" );
			{
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest52Childs, "typecode");
			}
			{
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest52Childs, "example");
			}
			{
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest52Childs, "smallerexample");
			}
			{
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest52Childs, "biggerexample");
			}
			{
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest52Childs, "outside");
			}
			{
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeTest52Childs, "minitemsize");
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode53;
				IModelElement[] classUnicodeTest52Childs = classUnicodeTest52.getChildren();
				methodtest_unicode53 = ModelTestUtils.getAssertMethod( classUnicodeTest52Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode53, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNumberTest54;
			IModelElement[] moduleChilds = module.getChildren();
			classNumberTest54 = ModelTestUtils.getAssertClass( moduleChilds, "NumberTest" );
			//Function test:test_extslice
			{
			IMethod methodtest_extslice55;
				IModelElement[] classNumberTest54Childs = classNumberTest54.getChildren();
				methodtest_extslice55 = ModelTestUtils.getAssertMethod( classNumberTest54Childs, "test_extslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extslice55, new String[] {"self"} );
			}
			//Function test:test_delslice
			{
			IMethod methodtest_delslice56;
				IModelElement[] classNumberTest54Childs = classNumberTest54.getChildren();
				methodtest_delslice56 = ModelTestUtils.getAssertMethod( classNumberTest54Childs, "test_delslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delslice56, new String[] {"self"} );
			}
			//Function test:test_assignment
			{
			IMethod methodtest_assignment57;
				IModelElement[] classNumberTest54Childs = classNumberTest54.getChildren();
				methodtest_assignment57 = ModelTestUtils.getAssertMethod( classNumberTest54Childs, "test_assignment", 1 );
				ModelTestUtils.assertParameterNames( methodtest_assignment57, new String[] {"self"} );
			}
			//Function test:test_iterationcontains
			{
			IMethod methodtest_iterationcontains58;
				IModelElement[] classNumberTest54Childs = classNumberTest54.getChildren();
				methodtest_iterationcontains58 = ModelTestUtils.getAssertMethod( classNumberTest54Childs, "test_iterationcontains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iterationcontains58, new String[] {"self"} );
			}
			//Function test:check_overflow
			{
			IMethod methodcheck_overflow59;
				IModelElement[] classNumberTest54Childs = classNumberTest54.getChildren();
				methodcheck_overflow59 = ModelTestUtils.getAssertMethod( classNumberTest54Childs, "check_overflow", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_overflow59, new String[] {"self", "lower", "upper"} );
			}
			//Function test:test_subclassing
			{
			IMethod methodtest_subclassing60;
				IModelElement[] classNumberTest54Childs = classNumberTest54.getChildren();
				methodtest_subclassing60 = ModelTestUtils.getAssertMethod( classNumberTest54Childs, "test_subclassing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclassing60, new String[] {"self"} );
				//Class test
				{
				IType classExaggeratingArray61;
					IModelElement[] methodtest_subclassing60Childs = methodtest_subclassing60.getChildren();
					classExaggeratingArray61 = ModelTestUtils.getAssertClass( methodtest_subclassing60Childs, "ExaggeratingArray" );
					//Function test:__new__
					{
					IMethod method__new__62;
						IModelElement[] classExaggeratingArray61Childs = classExaggeratingArray61.getChildren();
						method__new__62 = ModelTestUtils.getAssertMethod( classExaggeratingArray61Childs, "__new__", 4 );
						ModelTestUtils.assertParameterNames( method__new__62, new String[] {"cls", "typecode", "data", "offset"} );
					}
					//Function test:__init__
					{
					IMethod method__init__63;
						IModelElement[] classExaggeratingArray61Childs = classExaggeratingArray61.getChildren();
						method__init__63 = ModelTestUtils.getAssertMethod( classExaggeratingArray61Childs, "__init__", 4 );
						ModelTestUtils.assertParameterNames( method__init__63, new String[] {"self", "typecode", "data", "offset"} );
					}
					{
						IModelElement[] classExaggeratingArray61Childs = classExaggeratingArray61.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classExaggeratingArray61Childs, "offset");
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__65;
						IModelElement[] classExaggeratingArray61Childs = classExaggeratingArray61.getChildren();
						method__getitem__65 = ModelTestUtils.getAssertMethod( classExaggeratingArray61Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__65, new String[] {"self", "i"} );
					}
				}
			}
		}
		//Class test
		{
		IType classSignedNumberTest66;
			IModelElement[] moduleChilds = module.getChildren();
			classSignedNumberTest66 = ModelTestUtils.getAssertClass( moduleChilds, "SignedNumberTest" );
			{
				IModelElement[] classSignedNumberTest66Childs = classSignedNumberTest66.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest66Childs, "example");
			}
			{
				IModelElement[] classSignedNumberTest66Childs = classSignedNumberTest66.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest66Childs, "smallerexample");
			}
			{
				IModelElement[] classSignedNumberTest66Childs = classSignedNumberTest66.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest66Childs, "biggerexample");
			}
			{
				IModelElement[] classSignedNumberTest66Childs = classSignedNumberTest66.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSignedNumberTest66Childs, "outside");
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow67;
				IModelElement[] classSignedNumberTest66Childs = classSignedNumberTest66.getChildren();
				methodtest_overflow67 = ModelTestUtils.getAssertMethod( classSignedNumberTest66Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow67, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnsignedNumberTest68;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedNumberTest68 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedNumberTest" );
			{
				IModelElement[] classUnsignedNumberTest68Childs = classUnsignedNumberTest68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest68Childs, "example");
			}
			{
				IModelElement[] classUnsignedNumberTest68Childs = classUnsignedNumberTest68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest68Childs, "smallerexample");
			}
			{
				IModelElement[] classUnsignedNumberTest68Childs = classUnsignedNumberTest68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest68Childs, "biggerexample");
			}
			{
				IModelElement[] classUnsignedNumberTest68Childs = classUnsignedNumberTest68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedNumberTest68Childs, "outside");
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow69;
				IModelElement[] classUnsignedNumberTest68Childs = classUnsignedNumberTest68.getChildren();
				methodtest_overflow69 = ModelTestUtils.getAssertMethod( classUnsignedNumberTest68Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow69, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classByteTest70;
			IModelElement[] moduleChilds = module.getChildren();
			classByteTest70 = ModelTestUtils.getAssertClass( moduleChilds, "ByteTest" );
			{
				IModelElement[] classByteTest70Childs = classByteTest70.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classByteTest70Childs, "typecode");
			}
			{
				IModelElement[] classByteTest70Childs = classByteTest70.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classByteTest70Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedByteTest71;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedByteTest71 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedByteTest" );
			{
				IModelElement[] classUnsignedByteTest71Childs = classUnsignedByteTest71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedByteTest71Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedByteTest71Childs = classUnsignedByteTest71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedByteTest71Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classShortTest72;
			IModelElement[] moduleChilds = module.getChildren();
			classShortTest72 = ModelTestUtils.getAssertClass( moduleChilds, "ShortTest" );
			{
				IModelElement[] classShortTest72Childs = classShortTest72.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classShortTest72Childs, "typecode");
			}
			{
				IModelElement[] classShortTest72Childs = classShortTest72.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classShortTest72Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedShortTest73;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedShortTest73 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedShortTest" );
			{
				IModelElement[] classUnsignedShortTest73Childs = classUnsignedShortTest73.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedShortTest73Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedShortTest73Childs = classUnsignedShortTest73.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedShortTest73Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classIntTest74;
			IModelElement[] moduleChilds = module.getChildren();
			classIntTest74 = ModelTestUtils.getAssertClass( moduleChilds, "IntTest" );
			{
				IModelElement[] classIntTest74Childs = classIntTest74.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTest74Childs, "typecode");
			}
			{
				IModelElement[] classIntTest74Childs = classIntTest74.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIntTest74Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedIntTest75;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedIntTest75 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedIntTest" );
			{
				IModelElement[] classUnsignedIntTest75Childs = classUnsignedIntTest75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedIntTest75Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedIntTest75Childs = classUnsignedIntTest75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedIntTest75Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classLongTest76;
			IModelElement[] moduleChilds = module.getChildren();
			classLongTest76 = ModelTestUtils.getAssertClass( moduleChilds, "LongTest" );
			{
				IModelElement[] classLongTest76Childs = classLongTest76.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongTest76Childs, "typecode");
			}
			{
				IModelElement[] classLongTest76Childs = classLongTest76.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongTest76Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classUnsignedLongTest77;
			IModelElement[] moduleChilds = module.getChildren();
			classUnsignedLongTest77 = ModelTestUtils.getAssertClass( moduleChilds, "UnsignedLongTest" );
			{
				IModelElement[] classUnsignedLongTest77Childs = classUnsignedLongTest77.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedLongTest77Childs, "typecode");
			}
			{
				IModelElement[] classUnsignedLongTest77Childs = classUnsignedLongTest77.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnsignedLongTest77Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classFPTest78;
			IModelElement[] moduleChilds = module.getChildren();
			classFPTest78 = ModelTestUtils.getAssertClass( moduleChilds, "FPTest" );
			{
				IModelElement[] classFPTest78Childs = classFPTest78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest78Childs, "example");
			}
			{
				IModelElement[] classFPTest78Childs = classFPTest78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest78Childs, "smallerexample");
			}
			{
				IModelElement[] classFPTest78Childs = classFPTest78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest78Childs, "biggerexample");
			}
			{
				IModelElement[] classFPTest78Childs = classFPTest78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFPTest78Childs, "outside");
			}
			//Function test:assertEntryEqual
			{
			IMethod methodassertEntryEqual79;
				IModelElement[] classFPTest78Childs = classFPTest78.getChildren();
				methodassertEntryEqual79 = ModelTestUtils.getAssertMethod( classFPTest78Childs, "assertEntryEqual", 3 );
				ModelTestUtils.assertParameterNames( methodassertEntryEqual79, new String[] {"self", "entry1", "entry2"} );
			}
			//Function test:test_byteswap
			{
			IMethod methodtest_byteswap80;
				IModelElement[] classFPTest78Childs = classFPTest78.getChildren();
				methodtest_byteswap80 = ModelTestUtils.getAssertMethod( classFPTest78Childs, "test_byteswap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_byteswap80, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFloatTest81;
			IModelElement[] moduleChilds = module.getChildren();
			classFloatTest81 = ModelTestUtils.getAssertClass( moduleChilds, "FloatTest" );
			{
				IModelElement[] classFloatTest81Childs = classFloatTest81.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFloatTest81Childs, "typecode");
			}
			{
				IModelElement[] classFloatTest81Childs = classFloatTest81.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFloatTest81Childs, "minitemsize");
			}
		}
		//Class test
		{
		IType classDoubleTest82;
			IModelElement[] moduleChilds = module.getChildren();
			classDoubleTest82 = ModelTestUtils.getAssertClass( moduleChilds, "DoubleTest" );
			{
				IModelElement[] classDoubleTest82Childs = classDoubleTest82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDoubleTest82Childs, "typecode");
			}
			{
				IModelElement[] classDoubleTest82Childs = classDoubleTest82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDoubleTest82Childs, "minitemsize");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main83;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main83 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main83, new String[] {"verbose"} );
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
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleTests0Childs, "dumps");
			}
			{
				IModelElement[] classcPickleTests0Childs = classcPickleTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleTests0Childs, "loads");
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
		IType classcPicklePicklerTests3;
			IModelElement[] moduleChilds = module.getChildren();
			classcPicklePicklerTests3 = ModelTestUtils.getAssertClass( moduleChilds, "cPicklePicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps4;
				IModelElement[] classcPicklePicklerTests3Childs = classcPicklePicklerTests3.getChildren();
				methoddumps4 = ModelTestUtils.getAssertMethod( classcPicklePicklerTests3Childs, "dumps", 3 );
				ModelTestUtils.assertParameterNames( methoddumps4, new String[] {"self", "arg", "proto"} );
			}
			//Function test:loads
			{
			IMethod methodloads5;
				IModelElement[] classcPicklePicklerTests3Childs = classcPicklePicklerTests3.getChildren();
				methodloads5 = ModelTestUtils.getAssertMethod( classcPicklePicklerTests3Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads5, new String[] {"self", "buf"} );
			}
			{
				IModelElement[] classcPicklePicklerTests3Childs = classcPicklePicklerTests3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPicklePicklerTests3Childs, "error");
			}
		}
		//Class test
		{
		IType classcPickleListPicklerTests6;
			IModelElement[] moduleChilds = module.getChildren();
			classcPickleListPicklerTests6 = ModelTestUtils.getAssertClass( moduleChilds, "cPickleListPicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps7;
				IModelElement[] classcPickleListPicklerTests6Childs = classcPickleListPicklerTests6.getChildren();
				methoddumps7 = ModelTestUtils.getAssertMethod( classcPickleListPicklerTests6Childs, "dumps", 3 );
				ModelTestUtils.assertParameterNames( methoddumps7, new String[] {"self", "arg", "proto"} );
			}
			//Function test:loads
			{
			IMethod methodloads8;
				IModelElement[] classcPickleListPicklerTests6Childs = classcPickleListPicklerTests6.getChildren();
				methodloads8 = ModelTestUtils.getAssertMethod( classcPickleListPicklerTests6Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads8, new String[] {"self", "args"} );
			}
			{
				IModelElement[] classcPickleListPicklerTests6Childs = classcPickleListPicklerTests6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleListPicklerTests6Childs, "error");
			}
		}
		//Class test
		{
		IType classcPickleFastPicklerTests9;
			IModelElement[] moduleChilds = module.getChildren();
			classcPickleFastPicklerTests9 = ModelTestUtils.getAssertClass( moduleChilds, "cPickleFastPicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps10;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methoddumps10 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "dumps", 3 );
				ModelTestUtils.assertParameterNames( methoddumps10, new String[] {"self", "arg", "proto"} );
			}
			//Function test:loads
			{
			IMethod methodloads11;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methodloads11 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads11, new String[] {"self", "args"} );
			}
			{
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classcPickleFastPicklerTests9Childs, "error");
			}
			//Function test:test_recursive_list
			{
			IMethod methodtest_recursive_list12;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methodtest_recursive_list12 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "test_recursive_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_list12, new String[] {"self"} );
			}
			//Function test:test_recursive_inst
			{
			IMethod methodtest_recursive_inst13;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methodtest_recursive_inst13 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "test_recursive_inst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_inst13, new String[] {"self"} );
			}
			//Function test:test_recursive_dict
			{
			IMethod methodtest_recursive_dict14;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methodtest_recursive_dict14 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "test_recursive_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_dict14, new String[] {"self"} );
			}
			//Function test:test_recursive_multi
			{
			IMethod methodtest_recursive_multi15;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methodtest_recursive_multi15 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "test_recursive_multi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_multi15, new String[] {"self"} );
			}
			//Function test:test_nonrecursive_deep
			{
			IMethod methodtest_nonrecursive_deep16;
				IModelElement[] classcPickleFastPicklerTests9Childs = classcPickleFastPicklerTests9.getChildren();
				methodtest_nonrecursive_deep16 = ModelTestUtils.getAssertMethod( classcPickleFastPicklerTests9Childs, "test_nonrecursive_deep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonrecursive_deep16, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] class_LocaleTests0Childs = class_LocaleTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( class_LocaleTests0Childs, "oldlocale");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] class_LocaleTests0Childs = class_LocaleTests0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( class_LocaleTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:test_lc_numeric
			{
			IMethod methodtest_lc_numeric4;
				IModelElement[] class_LocaleTests0Childs = class_LocaleTests0.getChildren();
				methodtest_lc_numeric4 = ModelTestUtils.getAssertMethod( class_LocaleTests0Childs, "test_lc_numeric", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lc_numeric4, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classTempFileGreedy0Childs = classTempFileGreedy0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTempFileGreedy0Childs, "errors");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classSocketTCPTest0Childs = classSocketTCPTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketTCPTest0Childs, "serv");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classSocketTCPTest0Childs = classSocketTCPTest0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classSocketTCPTest0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			{
				IModelElement[] classSocketTCPTest0Childs = classSocketTCPTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketTCPTest0Childs, "serv");
			}
		}
		//Class test
		{
		IType classSocketUDPTest5;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketUDPTest5 = ModelTestUtils.getAssertClass( moduleChilds, "SocketUDPTest" );
			//Function test:setUp
			{
			IMethod methodsetUp6;
				IModelElement[] classSocketUDPTest5Childs = classSocketUDPTest5.getChildren();
				methodsetUp6 = ModelTestUtils.getAssertMethod( classSocketUDPTest5Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp6, new String[] {"self"} );
			}
			{
				IModelElement[] classSocketUDPTest5Childs = classSocketUDPTest5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketUDPTest5Childs, "serv");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown8;
				IModelElement[] classSocketUDPTest5Childs = classSocketUDPTest5.getChildren();
				methodtearDown8 = ModelTestUtils.getAssertMethod( classSocketUDPTest5Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadableTest9;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadableTest9 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadableTest" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self"} );
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "__setUp");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "__tearDown");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "setUp");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "tearDown");
			}
			//Function test:serverExplicitReady
			{
			IMethod methodserverExplicitReady12;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				methodserverExplicitReady12 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "serverExplicitReady", 1 );
				ModelTestUtils.assertParameterNames( methodserverExplicitReady12, new String[] {"self"} );
			}
			//Function test:_setUp
			{
			IMethod method_setUp13;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				method_setUp13 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "_setUp", 1 );
				ModelTestUtils.assertParameterNames( method_setUp13, new String[] {"self"} );
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "server_ready");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "client_ready");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "done");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "queue");
			}
			{
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadableTest9Childs, "client_thread");
			}
			//Function test:_tearDown
			{
			IMethod method_tearDown15;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				method_tearDown15 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "_tearDown", 1 );
				ModelTestUtils.assertParameterNames( method_tearDown15, new String[] {"self"} );
			}
			//Function test:clientRun
			{
			IMethod methodclientRun16;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				methodclientRun16 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "clientRun", 2 );
				ModelTestUtils.assertParameterNames( methodclientRun16, new String[] {"self", "test_func"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp17;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				methodclientSetUp17 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp17, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown18;
				IModelElement[] classThreadableTest9Childs = classThreadableTest9.getChildren();
				methodclientTearDown18 = ModelTestUtils.getAssertMethod( classThreadableTest9Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classThreadedTCPSocketTest19;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadedTCPSocketTest19 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadedTCPSocketTest" );
			//Function test:__init__
			{
			IMethod method__init__20;
				IModelElement[] classThreadedTCPSocketTest19Childs = classThreadedTCPSocketTest19.getChildren();
				method__init__20 = ModelTestUtils.getAssertMethod( classThreadedTCPSocketTest19Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__20, new String[] {"self", "methodName"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp21;
				IModelElement[] classThreadedTCPSocketTest19Childs = classThreadedTCPSocketTest19.getChildren();
				methodclientSetUp21 = ModelTestUtils.getAssertMethod( classThreadedTCPSocketTest19Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp21, new String[] {"self"} );
			}
			{
				IModelElement[] classThreadedTCPSocketTest19Childs = classThreadedTCPSocketTest19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadedTCPSocketTest19Childs, "cli");
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown23;
				IModelElement[] classThreadedTCPSocketTest19Childs = classThreadedTCPSocketTest19.getChildren();
				methodclientTearDown23 = ModelTestUtils.getAssertMethod( classThreadedTCPSocketTest19Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown23, new String[] {"self"} );
			}
			{
				IModelElement[] classThreadedTCPSocketTest19Childs = classThreadedTCPSocketTest19.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadedTCPSocketTest19Childs, "cli");
			}
		}
		//Class test
		{
		IType classThreadedUDPSocketTest25;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadedUDPSocketTest25 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadedUDPSocketTest" );
			//Function test:__init__
			{
			IMethod method__init__26;
				IModelElement[] classThreadedUDPSocketTest25Childs = classThreadedUDPSocketTest25.getChildren();
				method__init__26 = ModelTestUtils.getAssertMethod( classThreadedUDPSocketTest25Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__26, new String[] {"self", "methodName"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp27;
				IModelElement[] classThreadedUDPSocketTest25Childs = classThreadedUDPSocketTest25.getChildren();
				methodclientSetUp27 = ModelTestUtils.getAssertMethod( classThreadedUDPSocketTest25Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp27, new String[] {"self"} );
			}
			{
				IModelElement[] classThreadedUDPSocketTest25Childs = classThreadedUDPSocketTest25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classThreadedUDPSocketTest25Childs, "cli");
			}
		}
		//Class test
		{
		IType classSocketConnectedTest29;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketConnectedTest29 = ModelTestUtils.getAssertClass( moduleChilds, "SocketConnectedTest" );
			//Function test:__init__
			{
			IMethod method__init__30;
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				method__init__30 = ModelTestUtils.getAssertMethod( classSocketConnectedTest29Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__30, new String[] {"self", "methodName"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp31;
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				methodsetUp31 = ModelTestUtils.getAssertMethod( classSocketConnectedTest29Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp31, new String[] {"self"} );
			}
			{
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketConnectedTest29Childs, "cli_conn");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown33;
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				methodtearDown33 = ModelTestUtils.getAssertMethod( classSocketConnectedTest29Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown33, new String[] {"self"} );
			}
			{
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketConnectedTest29Childs, "cli_conn");
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp35;
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				methodclientSetUp35 = ModelTestUtils.getAssertMethod( classSocketConnectedTest29Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp35, new String[] {"self"} );
			}
			{
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketConnectedTest29Childs, "serv_conn");
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown37;
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				methodclientTearDown37 = ModelTestUtils.getAssertMethod( classSocketConnectedTest29Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown37, new String[] {"self"} );
			}
			{
				IModelElement[] classSocketConnectedTest29Childs = classSocketConnectedTest29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSocketConnectedTest29Childs, "serv_conn");
			}
		}
		//Class test
		{
		IType classSocketPairTest39;
			IModelElement[] moduleChilds = module.getChildren();
			classSocketPairTest39 = ModelTestUtils.getAssertClass( moduleChilds, "SocketPairTest" );
			//Function test:__init__
			{
			IMethod method__init__40;
				IModelElement[] classSocketPairTest39Childs = classSocketPairTest39.getChildren();
				method__init__40 = ModelTestUtils.getAssertMethod( classSocketPairTest39Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__40, new String[] {"self", "methodName"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp41;
				IModelElement[] classSocketPairTest39Childs = classSocketPairTest39.getChildren();
				methodsetUp41 = ModelTestUtils.getAssertMethod( classSocketPairTest39Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp41, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown42;
				IModelElement[] classSocketPairTest39Childs = classSocketPairTest39.getChildren();
				methodtearDown42 = ModelTestUtils.getAssertMethod( classSocketPairTest39Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown42, new String[] {"self"} );
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp43;
				IModelElement[] classSocketPairTest39Childs = classSocketPairTest39.getChildren();
				methodclientSetUp43 = ModelTestUtils.getAssertMethod( classSocketPairTest39Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp43, new String[] {"self"} );
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown44;
				IModelElement[] classSocketPairTest39Childs = classSocketPairTest39.getChildren();
				methodclientTearDown44 = ModelTestUtils.getAssertMethod( classSocketPairTest39Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown44, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classGeneralModuleTests45;
			IModelElement[] moduleChilds = module.getChildren();
			classGeneralModuleTests45 = ModelTestUtils.getAssertClass( moduleChilds, "GeneralModuleTests" );
			//Function test:test_weakref
			{
			IMethod methodtest_weakref46;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtest_weakref46 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref46, new String[] {"self"} );
			}
			//Function test:testSocketError
			{
			IMethod methodtestSocketError47;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestSocketError47 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testSocketError", 1 );
				ModelTestUtils.assertParameterNames( methodtestSocketError47, new String[] {"self"} );
				//Function test:raise_error
				{
				IMethod methodraise_error48;
					IModelElement[] methodtestSocketError47Childs = methodtestSocketError47.getChildren();
					methodraise_error48 = ModelTestUtils.getAssertMethod( methodtestSocketError47Childs, "raise_error", 2 );
					ModelTestUtils.assertParameterNames( methodraise_error48, new String[] {"args", "kwargs"} );
				}
				//Function test:raise_herror
				{
				IMethod methodraise_herror49;
					IModelElement[] methodtestSocketError47Childs = methodtestSocketError47.getChildren();
					methodraise_herror49 = ModelTestUtils.getAssertMethod( methodtestSocketError47Childs, "raise_herror", 2 );
					ModelTestUtils.assertParameterNames( methodraise_herror49, new String[] {"args", "kwargs"} );
				}
				//Function test:raise_gaierror
				{
				IMethod methodraise_gaierror50;
					IModelElement[] methodtestSocketError47Childs = methodtestSocketError47.getChildren();
					methodraise_gaierror50 = ModelTestUtils.getAssertMethod( methodtestSocketError47Childs, "raise_gaierror", 2 );
					ModelTestUtils.assertParameterNames( methodraise_gaierror50, new String[] {"args", "kwargs"} );
				}
			}
			//Function test:testCrucialConstants
			{
			IMethod methodtestCrucialConstants51;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestCrucialConstants51 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testCrucialConstants", 1 );
				ModelTestUtils.assertParameterNames( methodtestCrucialConstants51, new String[] {"self"} );
			}
			//Function test:testHostnameRes
			{
			IMethod methodtestHostnameRes52;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestHostnameRes52 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testHostnameRes", 1 );
				ModelTestUtils.assertParameterNames( methodtestHostnameRes52, new String[] {"self"} );
			}
			//Function test:testRefCountGetNameInfo
			{
			IMethod methodtestRefCountGetNameInfo53;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestRefCountGetNameInfo53 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testRefCountGetNameInfo", 1 );
				ModelTestUtils.assertParameterNames( methodtestRefCountGetNameInfo53, new String[] {"self"} );
			}
			//Function test:testInterpreterCrash
			{
			IMethod methodtestInterpreterCrash54;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestInterpreterCrash54 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testInterpreterCrash", 1 );
				ModelTestUtils.assertParameterNames( methodtestInterpreterCrash54, new String[] {"self"} );
			}
			//Function test:testNtoH
			{
			IMethod methodtestNtoH55;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestNtoH55 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testNtoH", 1 );
				ModelTestUtils.assertParameterNames( methodtestNtoH55, new String[] {"self"} );
			}
			//Function test:testGetServBy
			{
			IMethod methodtestGetServBy56;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestGetServBy56 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testGetServBy", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetServBy56, new String[] {"self"} );
			}
			//Function test:testDefaultTimeout
			{
			IMethod methodtestDefaultTimeout57;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestDefaultTimeout57 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testDefaultTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestDefaultTimeout57, new String[] {"self"} );
			}
			//Function test:testIPv4toString
			{
			IMethod methodtestIPv4toString58;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestIPv4toString58 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testIPv4toString", 1 );
				ModelTestUtils.assertParameterNames( methodtestIPv4toString58, new String[] {"self"} );
			}
			//Function test:testIPv6toString
			{
			IMethod methodtestIPv6toString59;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestIPv6toString59 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testIPv6toString", 1 );
				ModelTestUtils.assertParameterNames( methodtestIPv6toString59, new String[] {"self"} );
			}
			//Function test:testStringToIPv4
			{
			IMethod methodtestStringToIPv460;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestStringToIPv460 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testStringToIPv4", 1 );
				ModelTestUtils.assertParameterNames( methodtestStringToIPv460, new String[] {"self"} );
			}
			//Function test:testStringToIPv6
			{
			IMethod methodtestStringToIPv661;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestStringToIPv661 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testStringToIPv6", 1 );
				ModelTestUtils.assertParameterNames( methodtestStringToIPv661, new String[] {"self"} );
			}
			//Function test:testSockName
			{
			IMethod methodtestSockName62;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestSockName62 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testSockName", 1 );
				ModelTestUtils.assertParameterNames( methodtestSockName62, new String[] {"self"} );
			}
			//Function test:testGetSockOpt
			{
			IMethod methodtestGetSockOpt63;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestGetSockOpt63 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testGetSockOpt", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetSockOpt63, new String[] {"self"} );
			}
			//Function test:testSetSockOpt
			{
			IMethod methodtestSetSockOpt64;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestSetSockOpt64 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testSetSockOpt", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetSockOpt64, new String[] {"self"} );
			}
			//Function test:testSendAfterClose
			{
			IMethod methodtestSendAfterClose65;
				IModelElement[] classGeneralModuleTests45Childs = classGeneralModuleTests45.getChildren();
				methodtestSendAfterClose65 = ModelTestUtils.getAssertMethod( classGeneralModuleTests45Childs, "testSendAfterClose", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendAfterClose65, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBasicTCPTest66;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicTCPTest66 = ModelTestUtils.getAssertClass( moduleChilds, "BasicTCPTest" );
			//Function test:__init__
			{
			IMethod method__init__67;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method__init__67 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__67, new String[] {"self", "methodName"} );
			}
			//Function test:testRecv
			{
			IMethod methodtestRecv68;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestRecv68 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecv68, new String[] {"self"} );
			}
			//Function test:_testRecv
			{
			IMethod method_testRecv69;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testRecv69 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testRecv69, new String[] {"self"} );
			}
			//Function test:testOverFlowRecv
			{
			IMethod methodtestOverFlowRecv70;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestOverFlowRecv70 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testOverFlowRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestOverFlowRecv70, new String[] {"self"} );
			}
			//Function test:_testOverFlowRecv
			{
			IMethod method_testOverFlowRecv71;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testOverFlowRecv71 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testOverFlowRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testOverFlowRecv71, new String[] {"self"} );
			}
			//Function test:testRecvFrom
			{
			IMethod methodtestRecvFrom72;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestRecvFrom72 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvFrom72, new String[] {"self"} );
			}
			//Function test:_testRecvFrom
			{
			IMethod method_testRecvFrom73;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testRecvFrom73 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( method_testRecvFrom73, new String[] {"self"} );
			}
			//Function test:testOverFlowRecvFrom
			{
			IMethod methodtestOverFlowRecvFrom74;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestOverFlowRecvFrom74 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testOverFlowRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( methodtestOverFlowRecvFrom74, new String[] {"self"} );
			}
			//Function test:_testOverFlowRecvFrom
			{
			IMethod method_testOverFlowRecvFrom75;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testOverFlowRecvFrom75 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testOverFlowRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( method_testOverFlowRecvFrom75, new String[] {"self"} );
			}
			//Function test:testSendAll
			{
			IMethod methodtestSendAll76;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestSendAll76 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testSendAll", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendAll76, new String[] {"self"} );
			}
			//Function test:_testSendAll
			{
			IMethod method_testSendAll77;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testSendAll77 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testSendAll", 1 );
				ModelTestUtils.assertParameterNames( method_testSendAll77, new String[] {"self"} );
			}
			//Function test:testFromFd
			{
			IMethod methodtestFromFd78;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestFromFd78 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testFromFd", 1 );
				ModelTestUtils.assertParameterNames( methodtestFromFd78, new String[] {"self"} );
			}
			//Function test:_testFromFd
			{
			IMethod method_testFromFd79;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testFromFd79 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testFromFd", 1 );
				ModelTestUtils.assertParameterNames( method_testFromFd79, new String[] {"self"} );
			}
			//Function test:testShutdown
			{
			IMethod methodtestShutdown80;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				methodtestShutdown80 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "testShutdown", 1 );
				ModelTestUtils.assertParameterNames( methodtestShutdown80, new String[] {"self"} );
			}
			//Function test:_testShutdown
			{
			IMethod method_testShutdown81;
				IModelElement[] classBasicTCPTest66Childs = classBasicTCPTest66.getChildren();
				method_testShutdown81 = ModelTestUtils.getAssertMethod( classBasicTCPTest66Childs, "_testShutdown", 1 );
				ModelTestUtils.assertParameterNames( method_testShutdown81, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBasicUDPTest82;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicUDPTest82 = ModelTestUtils.getAssertClass( moduleChilds, "BasicUDPTest" );
			//Function test:__init__
			{
			IMethod method__init__83;
				IModelElement[] classBasicUDPTest82Childs = classBasicUDPTest82.getChildren();
				method__init__83 = ModelTestUtils.getAssertMethod( classBasicUDPTest82Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__83, new String[] {"self", "methodName"} );
			}
			//Function test:testSendtoAndRecv
			{
			IMethod methodtestSendtoAndRecv84;
				IModelElement[] classBasicUDPTest82Childs = classBasicUDPTest82.getChildren();
				methodtestSendtoAndRecv84 = ModelTestUtils.getAssertMethod( classBasicUDPTest82Childs, "testSendtoAndRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestSendtoAndRecv84, new String[] {"self"} );
			}
			//Function test:_testSendtoAndRecv
			{
			IMethod method_testSendtoAndRecv85;
				IModelElement[] classBasicUDPTest82Childs = classBasicUDPTest82.getChildren();
				method_testSendtoAndRecv85 = ModelTestUtils.getAssertMethod( classBasicUDPTest82Childs, "_testSendtoAndRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testSendtoAndRecv85, new String[] {"self"} );
			}
			//Function test:testRecvFrom
			{
			IMethod methodtestRecvFrom86;
				IModelElement[] classBasicUDPTest82Childs = classBasicUDPTest82.getChildren();
				methodtestRecvFrom86 = ModelTestUtils.getAssertMethod( classBasicUDPTest82Childs, "testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecvFrom86, new String[] {"self"} );
			}
			//Function test:_testRecvFrom
			{
			IMethod method_testRecvFrom87;
				IModelElement[] classBasicUDPTest82Childs = classBasicUDPTest82.getChildren();
				method_testRecvFrom87 = ModelTestUtils.getAssertMethod( classBasicUDPTest82Childs, "_testRecvFrom", 1 );
				ModelTestUtils.assertParameterNames( method_testRecvFrom87, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBasicSocketPairTest88;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicSocketPairTest88 = ModelTestUtils.getAssertClass( moduleChilds, "BasicSocketPairTest" );
			//Function test:__init__
			{
			IMethod method__init__89;
				IModelElement[] classBasicSocketPairTest88Childs = classBasicSocketPairTest88.getChildren();
				method__init__89 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest88Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__89, new String[] {"self", "methodName"} );
			}
			//Function test:testRecv
			{
			IMethod methodtestRecv90;
				IModelElement[] classBasicSocketPairTest88Childs = classBasicSocketPairTest88.getChildren();
				methodtestRecv90 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest88Childs, "testRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecv90, new String[] {"self"} );
			}
			//Function test:_testRecv
			{
			IMethod method_testRecv91;
				IModelElement[] classBasicSocketPairTest88Childs = classBasicSocketPairTest88.getChildren();
				method_testRecv91 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest88Childs, "_testRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testRecv91, new String[] {"self"} );
			}
			//Function test:testSend
			{
			IMethod methodtestSend92;
				IModelElement[] classBasicSocketPairTest88Childs = classBasicSocketPairTest88.getChildren();
				methodtestSend92 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest88Childs, "testSend", 1 );
				ModelTestUtils.assertParameterNames( methodtestSend92, new String[] {"self"} );
			}
			//Function test:_testSend
			{
			IMethod method_testSend93;
				IModelElement[] classBasicSocketPairTest88Childs = classBasicSocketPairTest88.getChildren();
				method_testSend93 = ModelTestUtils.getAssertMethod( classBasicSocketPairTest88Childs, "_testSend", 1 );
				ModelTestUtils.assertParameterNames( method_testSend93, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNonBlockingTCPTests94;
			IModelElement[] moduleChilds = module.getChildren();
			classNonBlockingTCPTests94 = ModelTestUtils.getAssertClass( moduleChilds, "NonBlockingTCPTests" );
			//Function test:__init__
			{
			IMethod method__init__95;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				method__init__95 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__95, new String[] {"self", "methodName"} );
			}
			//Function test:testSetBlocking
			{
			IMethod methodtestSetBlocking96;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				methodtestSetBlocking96 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "testSetBlocking", 1 );
				ModelTestUtils.assertParameterNames( methodtestSetBlocking96, new String[] {"self"} );
			}
			//Function test:_testSetBlocking
			{
			IMethod method_testSetBlocking97;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				method_testSetBlocking97 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "_testSetBlocking", 1 );
				ModelTestUtils.assertParameterNames( method_testSetBlocking97, new String[] {"self"} );
			}
			//Function test:testAccept
			{
			IMethod methodtestAccept98;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				methodtestAccept98 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "testAccept", 1 );
				ModelTestUtils.assertParameterNames( methodtestAccept98, new String[] {"self"} );
			}
			//Function test:_testAccept
			{
			IMethod method_testAccept99;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				method_testAccept99 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "_testAccept", 1 );
				ModelTestUtils.assertParameterNames( method_testAccept99, new String[] {"self"} );
			}
			//Function test:testConnect
			{
			IMethod methodtestConnect100;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				methodtestConnect100 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "testConnect", 1 );
				ModelTestUtils.assertParameterNames( methodtestConnect100, new String[] {"self"} );
			}
			//Function test:_testConnect
			{
			IMethod method_testConnect101;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				method_testConnect101 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "_testConnect", 1 );
				ModelTestUtils.assertParameterNames( method_testConnect101, new String[] {"self"} );
			}
			//Function test:testRecv
			{
			IMethod methodtestRecv102;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				methodtestRecv102 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "testRecv", 1 );
				ModelTestUtils.assertParameterNames( methodtestRecv102, new String[] {"self"} );
			}
			//Function test:_testRecv
			{
			IMethod method_testRecv103;
				IModelElement[] classNonBlockingTCPTests94Childs = classNonBlockingTCPTests94.getChildren();
				method_testRecv103 = ModelTestUtils.getAssertMethod( classNonBlockingTCPTests94Childs, "_testRecv", 1 );
				ModelTestUtils.assertParameterNames( method_testRecv103, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFileObjectClassTestCase104;
			IModelElement[] moduleChilds = module.getChildren();
			classFileObjectClassTestCase104 = ModelTestUtils.getAssertClass( moduleChilds, "FileObjectClassTestCase" );
			{
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileObjectClassTestCase104Childs, "bufsize");
			}
			//Function test:__init__
			{
			IMethod method__init__105;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				method__init__105 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__105, new String[] {"self", "methodName"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp106;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodsetUp106 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp106, new String[] {"self"} );
			}
			{
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileObjectClassTestCase104Childs, "serv_file");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown108;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodtearDown108 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown108, new String[] {"self"} );
			}
			{
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileObjectClassTestCase104Childs, "serv_file");
			}
			//Function test:clientSetUp
			{
			IMethod methodclientSetUp110;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodclientSetUp110 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "clientSetUp", 1 );
				ModelTestUtils.assertParameterNames( methodclientSetUp110, new String[] {"self"} );
			}
			{
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileObjectClassTestCase104Childs, "cli_file");
			}
			//Function test:clientTearDown
			{
			IMethod methodclientTearDown112;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodclientTearDown112 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "clientTearDown", 1 );
				ModelTestUtils.assertParameterNames( methodclientTearDown112, new String[] {"self"} );
			}
			{
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileObjectClassTestCase104Childs, "cli_file");
			}
			//Function test:testSmallRead
			{
			IMethod methodtestSmallRead114;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodtestSmallRead114 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "testSmallRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestSmallRead114, new String[] {"self"} );
			}
			//Function test:_testSmallRead
			{
			IMethod method_testSmallRead115;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				method_testSmallRead115 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "_testSmallRead", 1 );
				ModelTestUtils.assertParameterNames( method_testSmallRead115, new String[] {"self"} );
			}
			//Function test:testFullRead
			{
			IMethod methodtestFullRead116;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodtestFullRead116 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "testFullRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestFullRead116, new String[] {"self"} );
			}
			//Function test:_testFullRead
			{
			IMethod method_testFullRead117;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				method_testFullRead117 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "_testFullRead", 1 );
				ModelTestUtils.assertParameterNames( method_testFullRead117, new String[] {"self"} );
			}
			//Function test:testUnbufferedRead
			{
			IMethod methodtestUnbufferedRead118;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodtestUnbufferedRead118 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "testUnbufferedRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnbufferedRead118, new String[] {"self"} );
			}
			//Function test:_testUnbufferedRead
			{
			IMethod method_testUnbufferedRead119;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				method_testUnbufferedRead119 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "_testUnbufferedRead", 1 );
				ModelTestUtils.assertParameterNames( method_testUnbufferedRead119, new String[] {"self"} );
			}
			//Function test:testReadline
			{
			IMethod methodtestReadline120;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodtestReadline120 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "testReadline", 1 );
				ModelTestUtils.assertParameterNames( methodtestReadline120, new String[] {"self"} );
			}
			//Function test:_testReadline
			{
			IMethod method_testReadline121;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				method_testReadline121 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "_testReadline", 1 );
				ModelTestUtils.assertParameterNames( method_testReadline121, new String[] {"self"} );
			}
			//Function test:testClosedAttr
			{
			IMethod methodtestClosedAttr122;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				methodtestClosedAttr122 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "testClosedAttr", 1 );
				ModelTestUtils.assertParameterNames( methodtestClosedAttr122, new String[] {"self"} );
			}
			//Function test:_testClosedAttr
			{
			IMethod method_testClosedAttr123;
				IModelElement[] classFileObjectClassTestCase104Childs = classFileObjectClassTestCase104.getChildren();
				method_testClosedAttr123 = ModelTestUtils.getAssertMethod( classFileObjectClassTestCase104Childs, "_testClosedAttr", 1 );
				ModelTestUtils.assertParameterNames( method_testClosedAttr123, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnbufferedFileObjectClassTestCase124;
			IModelElement[] moduleChilds = module.getChildren();
			classUnbufferedFileObjectClassTestCase124 = ModelTestUtils.getAssertClass( moduleChilds, "UnbufferedFileObjectClassTestCase" );
			{
				IModelElement[] classUnbufferedFileObjectClassTestCase124Childs = classUnbufferedFileObjectClassTestCase124.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnbufferedFileObjectClassTestCase124Childs, "bufsize");
			}
			//Function test:testUnbufferedReadline
			{
			IMethod methodtestUnbufferedReadline125;
				IModelElement[] classUnbufferedFileObjectClassTestCase124Childs = classUnbufferedFileObjectClassTestCase124.getChildren();
				methodtestUnbufferedReadline125 = ModelTestUtils.getAssertMethod( classUnbufferedFileObjectClassTestCase124Childs, "testUnbufferedReadline", 1 );
				ModelTestUtils.assertParameterNames( methodtestUnbufferedReadline125, new String[] {"self"} );
			}
			{
				IModelElement[] classUnbufferedFileObjectClassTestCase124Childs = classUnbufferedFileObjectClassTestCase124.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnbufferedFileObjectClassTestCase124Childs, "serv_file");
			}
			//Function test:_testUnbufferedReadline
			{
			IMethod method_testUnbufferedReadline127;
				IModelElement[] classUnbufferedFileObjectClassTestCase124Childs = classUnbufferedFileObjectClassTestCase124.getChildren();
				method_testUnbufferedReadline127 = ModelTestUtils.getAssertMethod( classUnbufferedFileObjectClassTestCase124Childs, "_testUnbufferedReadline", 1 );
				ModelTestUtils.assertParameterNames( method_testUnbufferedReadline127, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLineBufferedFileObjectClassTestCase128;
			IModelElement[] moduleChilds = module.getChildren();
			classLineBufferedFileObjectClassTestCase128 = ModelTestUtils.getAssertClass( moduleChilds, "LineBufferedFileObjectClassTestCase" );
			{
				IModelElement[] classLineBufferedFileObjectClassTestCase128Childs = classLineBufferedFileObjectClassTestCase128.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLineBufferedFileObjectClassTestCase128Childs, "bufsize");
			}
		}
		//Class test
		{
		IType classSmallBufferedFileObjectClassTestCase129;
			IModelElement[] moduleChilds = module.getChildren();
			classSmallBufferedFileObjectClassTestCase129 = ModelTestUtils.getAssertClass( moduleChilds, "SmallBufferedFileObjectClassTestCase" );
			{
				IModelElement[] classSmallBufferedFileObjectClassTestCase129Childs = classSmallBufferedFileObjectClassTestCase129.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSmallBufferedFileObjectClassTestCase129Childs, "bufsize");
			}
		}
		//Class test
		{
		IType classTCPTimeoutTest130;
			IModelElement[] moduleChilds = module.getChildren();
			classTCPTimeoutTest130 = ModelTestUtils.getAssertClass( moduleChilds, "TCPTimeoutTest" );
			//Function test:testTCPTimeout
			{
			IMethod methodtestTCPTimeout131;
				IModelElement[] classTCPTimeoutTest130Childs = classTCPTimeoutTest130.getChildren();
				methodtestTCPTimeout131 = ModelTestUtils.getAssertMethod( classTCPTimeoutTest130Childs, "testTCPTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestTCPTimeout131, new String[] {"self"} );
				//Function test:raise_timeout
				{
				IMethod methodraise_timeout132;
					IModelElement[] methodtestTCPTimeout131Childs = methodtestTCPTimeout131.getChildren();
					methodraise_timeout132 = ModelTestUtils.getAssertMethod( methodtestTCPTimeout131Childs, "raise_timeout", 2 );
					ModelTestUtils.assertParameterNames( methodraise_timeout132, new String[] {"args", "kwargs"} );
				}
			}
			//Function test:testTimeoutZero
			{
			IMethod methodtestTimeoutZero133;
				IModelElement[] classTCPTimeoutTest130Childs = classTCPTimeoutTest130.getChildren();
				methodtestTimeoutZero133 = ModelTestUtils.getAssertMethod( classTCPTimeoutTest130Childs, "testTimeoutZero", 1 );
				ModelTestUtils.assertParameterNames( methodtestTimeoutZero133, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUDPTimeoutTest134;
			IModelElement[] moduleChilds = module.getChildren();
			classUDPTimeoutTest134 = ModelTestUtils.getAssertClass( moduleChilds, "UDPTimeoutTest" );
			//Function test:testUDPTimeout
			{
			IMethod methodtestUDPTimeout135;
				IModelElement[] classUDPTimeoutTest134Childs = classUDPTimeoutTest134.getChildren();
				methodtestUDPTimeout135 = ModelTestUtils.getAssertMethod( classUDPTimeoutTest134Childs, "testUDPTimeout", 1 );
				ModelTestUtils.assertParameterNames( methodtestUDPTimeout135, new String[] {"self"} );
				//Function test:raise_timeout
				{
				IMethod methodraise_timeout136;
					IModelElement[] methodtestUDPTimeout135Childs = methodtestUDPTimeout135.getChildren();
					methodraise_timeout136 = ModelTestUtils.getAssertMethod( methodtestUDPTimeout135Childs, "raise_timeout", 2 );
					ModelTestUtils.assertParameterNames( methodraise_timeout136, new String[] {"args", "kwargs"} );
				}
			}
			//Function test:testTimeoutZero
			{
			IMethod methodtestTimeoutZero137;
				IModelElement[] classUDPTimeoutTest134Childs = classUDPTimeoutTest134.getChildren();
				methodtestTimeoutZero137 = ModelTestUtils.getAssertMethod( classUDPTimeoutTest134Childs, "testTimeoutZero", 1 );
				ModelTestUtils.assertParameterNames( methodtestTimeoutZero137, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestExceptions138;
			IModelElement[] moduleChilds = module.getChildren();
			classTestExceptions138 = ModelTestUtils.getAssertClass( moduleChilds, "TestExceptions" );
			//Function test:testExceptionTree
			{
			IMethod methodtestExceptionTree139;
				IModelElement[] classTestExceptions138Childs = classTestExceptions138.getChildren();
				methodtestExceptionTree139 = ModelTestUtils.getAssertMethod( classTestExceptions138Childs, "testExceptionTree", 1 );
				ModelTestUtils.assertParameterNames( methodtestExceptionTree139, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main140;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main140 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen262( ) throws Exception {
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
			{
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSquares0Childs, "max");
			}
			{
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSquares0Childs, "sofar");
			}
			//Function test:__len__
			{
			IMethod method__len__3;
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				method__len__3 = ModelTestUtils.getAssertMethod( classSquares0Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__3, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__4;
				IModelElement[] classSquares0Childs = classSquares0.getChildren();
				method__getitem__4 = ModelTestUtils.getAssertMethod( classSquares0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__4, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classStrSquares5;
			IModelElement[] moduleChilds = module.getChildren();
			classStrSquares5 = ModelTestUtils.getAssertClass( moduleChilds, "StrSquares" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classStrSquares5Childs = classStrSquares5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classStrSquares5Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "max"} );
			}
			//Function test:__len__
			{
			IMethod method__len__7;
				IModelElement[] classStrSquares5Childs = classStrSquares5.getChildren();
				method__len__7 = ModelTestUtils.getAssertMethod( classStrSquares5Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__7, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__8;
				IModelElement[] classStrSquares5Childs = classStrSquares5.getChildren();
				method__getitem__8 = ModelTestUtils.getAssertMethod( classStrSquares5Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__8, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classBitBucket9;
			IModelElement[] moduleChilds = module.getChildren();
			classBitBucket9 = ModelTestUtils.getAssertClass( moduleChilds, "BitBucket" );
			//Function test:write
			{
			IMethod methodwrite10;
				IModelElement[] classBitBucket9Childs = classBitBucket9.getChildren();
				methodwrite10 = ModelTestUtils.getAssertMethod( classBitBucket9Childs, "write", 2 );
				ModelTestUtils.assertParameterNames( methodwrite10, new String[] {"self", "line"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "L");
		}
		//Class test
		{
		IType classBuiltinTest11;
			IModelElement[] moduleChilds = module.getChildren();
			classBuiltinTest11 = ModelTestUtils.getAssertClass( moduleChilds, "BuiltinTest" );
			//Function test:test_import
			{
			IMethod methodtest_import12;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_import12 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_import", 1 );
				ModelTestUtils.assertParameterNames( methodtest_import12, new String[] {"self"} );
			}
			//Function test:test_abs
			{
			IMethod methodtest_abs13;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_abs13 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_abs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs13, new String[] {"self"} );
			}
			//Function test:test_apply
			{
			IMethod methodtest_apply14;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_apply14 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_apply", 1 );
				ModelTestUtils.assertParameterNames( methodtest_apply14, new String[] {"self"} );
				//Function test:f0
				{
				IMethod methodf015;
					IModelElement[] methodtest_apply14Childs = methodtest_apply14.getChildren();
					methodf015 = ModelTestUtils.getAssertMethod( methodtest_apply14Childs, "f0", 1 );
					ModelTestUtils.assertParameterNames( methodf015, new String[] {"args"} );
				}
				//Function test:f1
				{
				IMethod methodf116;
					IModelElement[] methodtest_apply14Childs = methodtest_apply14.getChildren();
					methodf116 = ModelTestUtils.getAssertMethod( methodtest_apply14Childs, "f1", 1 );
					ModelTestUtils.assertParameterNames( methodf116, new String[] {"a1"} );
				}
				//Function test:f2
				{
				IMethod methodf217;
					IModelElement[] methodtest_apply14Childs = methodtest_apply14.getChildren();
					methodf217 = ModelTestUtils.getAssertMethod( methodtest_apply14Childs, "f2", 2 );
					ModelTestUtils.assertParameterNames( methodf217, new String[] {"a1", "a2"} );
				}
				//Function test:f3
				{
				IMethod methodf318;
					IModelElement[] methodtest_apply14Childs = methodtest_apply14.getChildren();
					methodf318 = ModelTestUtils.getAssertMethod( methodtest_apply14Childs, "f3", 3 );
					ModelTestUtils.assertParameterNames( methodf318, new String[] {"a1", "a2", "a3"} );
				}
			}
			//Function test:test_callable
			{
			IMethod methodtest_callable19;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_callable19 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callable19, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf20;
					IModelElement[] methodtest_callable19Childs = methodtest_callable19.getChildren();
					methodf20 = ModelTestUtils.getAssertMethod( methodtest_callable19Childs, "f", 0 );
				}
				//Class test
				{
				IType classC21;
					IModelElement[] methodtest_callable19Childs = methodtest_callable19.getChildren();
					classC21 = ModelTestUtils.getAssertClass( methodtest_callable19Childs, "C" );
					//Function test:meth
					{
					IMethod methodmeth22;
						IModelElement[] classC21Childs = classC21.getChildren();
						methodmeth22 = ModelTestUtils.getAssertMethod( classC21Childs, "meth", 1 );
						ModelTestUtils.assertParameterNames( methodmeth22, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classD23;
					IModelElement[] methodtest_callable19Childs = methodtest_callable19.getChildren();
					classD23 = ModelTestUtils.getAssertClass( methodtest_callable19Childs, "D" );
					//Function test:__call__
					{
					IMethod method__call__24;
						IModelElement[] classD23Childs = classD23.getChildren();
						method__call__24 = ModelTestUtils.getAssertMethod( classD23Childs, "__call__", 1 );
						ModelTestUtils.assertParameterNames( method__call__24, new String[] {"self"} );
					}
				}
			}
			//Function test:test_chr
			{
			IMethod methodtest_chr25;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_chr25 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_chr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chr25, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp26;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_cmp26 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp26, new String[] {"self"} );
			}
			//Function test:test_coerce
			{
			IMethod methodtest_coerce27;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_coerce27 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_coerce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coerce27, new String[] {"self"} );
				//Class test
				{
				IType classBadNumber28;
					IModelElement[] methodtest_coerce27Childs = methodtest_coerce27.getChildren();
					classBadNumber28 = ModelTestUtils.getAssertClass( methodtest_coerce27Childs, "BadNumber" );
					//Function test:__coerce__
					{
					IMethod method__coerce__29;
						IModelElement[] classBadNumber28Childs = classBadNumber28.getChildren();
						method__coerce__29 = ModelTestUtils.getAssertMethod( classBadNumber28Childs, "__coerce__", 2 );
						ModelTestUtils.assertParameterNames( method__coerce__29, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_compile
			{
			IMethod methodtest_compile30;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_compile30 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_compile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compile30, new String[] {"self"} );
			}
			//Function test:test_delattr
			{
			IMethod methodtest_delattr31;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_delattr31 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_delattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delattr31, new String[] {"self"} );
			}
			//Function test:test_dir
			{
			IMethod methodtest_dir32;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_dir32 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_dir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dir32, new String[] {"self"} );
			}
			//Function test:test_divmod
			{
			IMethod methodtest_divmod33;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_divmod33 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_divmod", 1 );
				ModelTestUtils.assertParameterNames( methodtest_divmod33, new String[] {"self"} );
			}
			//Function test:test_eval
			{
			IMethod methodtest_eval34;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_eval34 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_eval", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eval34, new String[] {"self"} );
			}
			//Function test:test_general_eval
			{
			IMethod methodtest_general_eval35;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_general_eval35 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_general_eval", 1 );
				ModelTestUtils.assertParameterNames( methodtest_general_eval35, new String[] {"self"} );
				//Class test
				{
				IType classM36;
					IModelElement[] methodtest_general_eval35Childs = methodtest_general_eval35.getChildren();
					classM36 = ModelTestUtils.getAssertClass( methodtest_general_eval35Childs, "M" );
					//Function test:__getitem__
					{
					IMethod method__getitem__37;
						IModelElement[] classM36Childs = classM36.getChildren();
						method__getitem__37 = ModelTestUtils.getAssertMethod( classM36Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__37, new String[] {"self", "key"} );
					}
					//Function test:keys
					{
					IMethod methodkeys38;
						IModelElement[] classM36Childs = classM36.getChildren();
						methodkeys38 = ModelTestUtils.getAssertMethod( classM36Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys38, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classA39;
					IModelElement[] methodtest_general_eval35Childs = methodtest_general_eval35.getChildren();
					classA39 = ModelTestUtils.getAssertClass( methodtest_general_eval35Childs, "A" );
				}
				//Class test
				{
				IType classD40;
					IModelElement[] methodtest_general_eval35Childs = methodtest_general_eval35.getChildren();
					classD40 = ModelTestUtils.getAssertClass( methodtest_general_eval35Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__41;
						IModelElement[] classD40Childs = classD40.getChildren();
						method__getitem__41 = ModelTestUtils.getAssertMethod( classD40Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__41, new String[] {"self", "key"} );
					}
					//Function test:keys
					{
					IMethod methodkeys42;
						IModelElement[] classD40Childs = classD40.getChildren();
						methodkeys42 = ModelTestUtils.getAssertMethod( classD40Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys42, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classSpreadSheet43;
					IModelElement[] methodtest_general_eval35Childs = methodtest_general_eval35.getChildren();
					classSpreadSheet43 = ModelTestUtils.getAssertClass( methodtest_general_eval35Childs, "SpreadSheet" );
					//Function test:__setitem__
					{
					IMethod method__setitem__44;
						IModelElement[] classSpreadSheet43Childs = classSpreadSheet43.getChildren();
						method__setitem__44 = ModelTestUtils.getAssertMethod( classSpreadSheet43Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__44, new String[] {"self", "key", "formula"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__45;
						IModelElement[] classSpreadSheet43Childs = classSpreadSheet43.getChildren();
						method__getitem__45 = ModelTestUtils.getAssertMethod( classSpreadSheet43Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__45, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classC46;
					IModelElement[] methodtest_general_eval35Childs = methodtest_general_eval35.getChildren();
					classC46 = ModelTestUtils.getAssertClass( methodtest_general_eval35Childs, "C" );
					//Function test:__getitem__
					{
					IMethod method__getitem__47;
						IModelElement[] classC46Childs = classC46.getChildren();
						method__getitem__47 = ModelTestUtils.getAssertMethod( classC46Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__47, new String[] {"self", "item"} );
					}
					//Function test:keys
					{
					IMethod methodkeys48;
						IModelElement[] classC46Childs = classC46.getChildren();
						methodkeys48 = ModelTestUtils.getAssertMethod( classC46Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys48, new String[] {"self"} );
					}
				}
			}
			{
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest11Childs, "z");
			}
			{
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest11Childs, "f");
			}
			//Function test:test_execfile
			{
			IMethod methodtest_execfile49;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_execfile49 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_execfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_execfile49, new String[] {"self"} );
				//Class test
				{
				IType classM50;
					IModelElement[] methodtest_execfile49Childs = methodtest_execfile49.getChildren();
					classM50 = ModelTestUtils.getAssertClass( methodtest_execfile49Childs, "M" );
					//Function test:__init__
					{
					IMethod method__init__51;
						IModelElement[] classM50Childs = classM50.getChildren();
						method__init__51 = ModelTestUtils.getAssertMethod( classM50Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__51, new String[] {"self"} );
					}
					{
						IModelElement[] classM50Childs = classM50.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classM50Childs, "z");
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__53;
						IModelElement[] classM50Childs = classM50.getChildren();
						method__getitem__53 = ModelTestUtils.getAssertMethod( classM50Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__53, new String[] {"self", "key"} );
					}
					//Function test:__setitem__
					{
					IMethod method__setitem__54;
						IModelElement[] classM50Childs = classM50.getChildren();
						method__setitem__54 = ModelTestUtils.getAssertMethod( classM50Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__54, new String[] {"self", "key", "value"} );
					}
					{
						IModelElement[] classM50Childs = classM50.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classM50Childs, "z");
					}
				}
			}
			//Function test:test_filter
			{
			IMethod methodtest_filter56;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_filter56 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_filter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filter56, new String[] {"self"} );
				//Function test:identity
				{
				IMethod methodidentity57;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					methodidentity57 = ModelTestUtils.getAssertMethod( methodtest_filter56Childs, "identity", 1 );
					ModelTestUtils.assertParameterNames( methodidentity57, new String[] {"item"} );
				}
				//Class test
				{
				IType classBadSeq58;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classBadSeq58 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__59;
						IModelElement[] classBadSeq58Childs = classBadSeq58.getChildren();
						method__getitem__59 = ModelTestUtils.getAssertMethod( classBadSeq58Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__59, new String[] {"self", "index"} );
					}
				}
				//Function test:badfunc
				{
				IMethod methodbadfunc60;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					methodbadfunc60 = ModelTestUtils.getAssertMethod( methodtest_filter56Childs, "badfunc", 0 );
				}
				//Class test
				{
				IType classbadstr61;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classbadstr61 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "badstr" );
					//Function test:__getitem__
					{
					IMethod method__getitem__62;
						IModelElement[] classbadstr61Childs = classbadstr61.getChildren();
						method__getitem__62 = ModelTestUtils.getAssertMethod( classbadstr61Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__62, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classbadstr263;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classbadstr263 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "badstr2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__64;
						IModelElement[] classbadstr263Childs = classbadstr263.getChildren();
						method__getitem__64 = ModelTestUtils.getAssertMethod( classbadstr263Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__64, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classweirdstr65;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classweirdstr65 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "weirdstr" );
					//Function test:__getitem__
					{
					IMethod method__getitem__66;
						IModelElement[] classweirdstr65Childs = classweirdstr65.getChildren();
						method__getitem__66 = ModelTestUtils.getAssertMethod( classweirdstr65Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__66, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classshiftstr67;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classshiftstr67 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "shiftstr" );
					//Function test:__getitem__
					{
					IMethod method__getitem__68;
						IModelElement[] classshiftstr67Childs = classshiftstr67.getChildren();
						method__getitem__68 = ModelTestUtils.getAssertMethod( classshiftstr67Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__68, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classbadunicode69;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classbadunicode69 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "badunicode" );
					//Function test:__getitem__
					{
					IMethod method__getitem__70;
						IModelElement[] classbadunicode69Childs = classbadunicode69.getChildren();
						method__getitem__70 = ModelTestUtils.getAssertMethod( classbadunicode69Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__70, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classweirdunicode71;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classweirdunicode71 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "weirdunicode" );
					//Function test:__getitem__
					{
					IMethod method__getitem__72;
						IModelElement[] classweirdunicode71Childs = classweirdunicode71.getChildren();
						method__getitem__72 = ModelTestUtils.getAssertMethod( classweirdunicode71Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__72, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classshiftunicode73;
					IModelElement[] methodtest_filter56Childs = methodtest_filter56.getChildren();
					classshiftunicode73 = ModelTestUtils.getAssertClass( methodtest_filter56Childs, "shiftunicode" );
					//Function test:__getitem__
					{
					IMethod method__getitem__74;
						IModelElement[] classshiftunicode73Childs = classshiftunicode73.getChildren();
						method__getitem__74 = ModelTestUtils.getAssertMethod( classshiftunicode73Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__74, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_filter_subclasses
			{
			IMethod methodtest_filter_subclasses75;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_filter_subclasses75 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_filter_subclasses", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filter_subclasses75, new String[] {"self"} );
				//Class test
				{
				IType classtuple276;
					IModelElement[] methodtest_filter_subclasses75Childs = methodtest_filter_subclasses75.getChildren();
					classtuple276 = ModelTestUtils.getAssertClass( methodtest_filter_subclasses75Childs, "tuple2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__77;
						IModelElement[] classtuple276Childs = classtuple276.getChildren();
						method__getitem__77 = ModelTestUtils.getAssertMethod( classtuple276Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__77, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classstr278;
					IModelElement[] methodtest_filter_subclasses75Childs = methodtest_filter_subclasses75.getChildren();
					classstr278 = ModelTestUtils.getAssertClass( methodtest_filter_subclasses75Childs, "str2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__79;
						IModelElement[] classstr278Childs = classstr278.getChildren();
						method__getitem__79 = ModelTestUtils.getAssertMethod( classstr278Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__79, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classunicode280;
					IModelElement[] methodtest_filter_subclasses75Childs = methodtest_filter_subclasses75.getChildren();
					classunicode280 = ModelTestUtils.getAssertClass( methodtest_filter_subclasses75Childs, "unicode2" );
					//Function test:__getitem__
					{
					IMethod method__getitem__81;
						IModelElement[] classunicode280Childs = classunicode280.getChildren();
						method__getitem__81 = ModelTestUtils.getAssertMethod( classunicode280Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__81, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_float
			{
			IMethod methodtest_float82;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_float82 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float82, new String[] {"self"} );
			}
			//Function test:test_getattr
			{
			IMethod methodtest_getattr83;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_getattr83 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_getattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getattr83, new String[] {"self"} );
			}
			//Function test:test_hasattr
			{
			IMethod methodtest_hasattr84;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_hasattr84 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_hasattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hasattr84, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash85;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_hash85 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash85, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf86;
					IModelElement[] methodtest_hash85Childs = methodtest_hash85.getChildren();
					methodf86 = ModelTestUtils.getAssertMethod( methodtest_hash85Childs, "f", 0 );
				}
			}
			//Function test:test_hex
			{
			IMethod methodtest_hex87;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_hex87 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_hex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hex87, new String[] {"self"} );
			}
			//Function test:test_id
			{
			IMethod methodtest_id88;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_id88 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_id", 1 );
				ModelTestUtils.assertParameterNames( methodtest_id88, new String[] {"self"} );
			}
			//Function test:test_int
			{
			IMethod methodtest_int89;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_int89 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_int", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int89, new String[] {"self"} );
			}
			//Function test:test_intern
			{
			IMethod methodtest_intern90;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_intern90 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_intern", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intern90, new String[] {"self"} );
				//Class test
				{
				IType classS91;
					IModelElement[] methodtest_intern90Childs = methodtest_intern90.getChildren();
					classS91 = ModelTestUtils.getAssertClass( methodtest_intern90Childs, "S" );
					//Function test:__hash__
					{
					IMethod method__hash__92;
						IModelElement[] classS91Childs = classS91.getChildren();
						method__hash__92 = ModelTestUtils.getAssertMethod( classS91Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__92, new String[] {"self"} );
					}
				}
			}
			//Function test:test_iter
			{
			IMethod methodtest_iter93;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_iter93 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter93, new String[] {"self"} );
			}
			//Function test:test_isinstance
			{
			IMethod methodtest_isinstance94;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_isinstance94 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_isinstance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance94, new String[] {"self"} );
				//Class test
				{
				IType classC95;
					IModelElement[] methodtest_isinstance94Childs = methodtest_isinstance94.getChildren();
					classC95 = ModelTestUtils.getAssertClass( methodtest_isinstance94Childs, "C" );
				}
				//Class test
				{
				IType classD96;
					IModelElement[] methodtest_isinstance94Childs = methodtest_isinstance94.getChildren();
					classD96 = ModelTestUtils.getAssertClass( methodtest_isinstance94Childs, "D" );
				}
				//Class test
				{
				IType classE97;
					IModelElement[] methodtest_isinstance94Childs = methodtest_isinstance94.getChildren();
					classE97 = ModelTestUtils.getAssertClass( methodtest_isinstance94Childs, "E" );
				}
			}
			//Function test:test_issubclass
			{
			IMethod methodtest_issubclass98;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_issubclass98 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_issubclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_issubclass98, new String[] {"self"} );
				//Class test
				{
				IType classC99;
					IModelElement[] methodtest_issubclass98Childs = methodtest_issubclass98.getChildren();
					classC99 = ModelTestUtils.getAssertClass( methodtest_issubclass98Childs, "C" );
				}
				//Class test
				{
				IType classD100;
					IModelElement[] methodtest_issubclass98Childs = methodtest_issubclass98.getChildren();
					classD100 = ModelTestUtils.getAssertClass( methodtest_issubclass98Childs, "D" );
				}
				//Class test
				{
				IType classE101;
					IModelElement[] methodtest_issubclass98Childs = methodtest_issubclass98.getChildren();
					classE101 = ModelTestUtils.getAssertClass( methodtest_issubclass98Childs, "E" );
				}
			}
			//Function test:test_len
			{
			IMethod methodtest_len102;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_len102 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len102, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq103;
					IModelElement[] methodtest_len102Childs = methodtest_len102.getChildren();
					classBadSeq103 = ModelTestUtils.getAssertClass( methodtest_len102Childs, "BadSeq" );
					//Function test:__len__
					{
					IMethod method__len__104;
						IModelElement[] classBadSeq103Childs = classBadSeq103.getChildren();
						method__len__104 = ModelTestUtils.getAssertMethod( classBadSeq103Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__104, new String[] {"self"} );
					}
				}
			}
			//Function test:test_list
			{
			IMethod methodtest_list105;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_list105 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_list105, new String[] {"self"} );
			}
			//Function test:test_long
			{
			IMethod methodtest_long106;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_long106 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long106, new String[] {"self"} );
			}
			//Function test:test_map
			{
			IMethod methodtest_map107;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_map107 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_map", 1 );
				ModelTestUtils.assertParameterNames( methodtest_map107, new String[] {"self"} );
				//Function test:sqrt
				{
				IMethod methodsqrt108;
					IModelElement[] methodtest_map107Childs = methodtest_map107.getChildren();
					methodsqrt108 = ModelTestUtils.getAssertMethod( methodtest_map107Childs, "sqrt", 1 );
					ModelTestUtils.assertParameterNames( methodsqrt108, new String[] {"x"} );
				}
				//Function test:plus
				{
				IMethod methodplus109;
					IModelElement[] methodtest_map107Childs = methodtest_map107.getChildren();
					methodplus109 = ModelTestUtils.getAssertMethod( methodtest_map107Childs, "plus", 1 );
					ModelTestUtils.assertParameterNames( methodplus109, new String[] {"v"} );
				}
				//Class test
				{
				IType classBadSeq110;
					IModelElement[] methodtest_map107Childs = methodtest_map107.getChildren();
					classBadSeq110 = ModelTestUtils.getAssertClass( methodtest_map107Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__111;
						IModelElement[] classBadSeq110Childs = classBadSeq110.getChildren();
						method__getitem__111 = ModelTestUtils.getAssertMethod( classBadSeq110Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__111, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_max
			{
			IMethod methodtest_max112;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_max112 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_max", 1 );
				ModelTestUtils.assertParameterNames( methodtest_max112, new String[] {"self"} );
			}
			//Function test:test_min
			{
			IMethod methodtest_min113;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_min113 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_min", 1 );
				ModelTestUtils.assertParameterNames( methodtest_min113, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq114;
					IModelElement[] methodtest_min113Childs = methodtest_min113.getChildren();
					classBadSeq114 = ModelTestUtils.getAssertClass( methodtest_min113Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__115;
						IModelElement[] classBadSeq114Childs = classBadSeq114.getChildren();
						method__getitem__115 = ModelTestUtils.getAssertMethod( classBadSeq114Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__115, new String[] {"self", "index"} );
					}
				}
				//Class test
				{
				IType classBadNumber116;
					IModelElement[] methodtest_min113Childs = methodtest_min113.getChildren();
					classBadNumber116 = ModelTestUtils.getAssertClass( methodtest_min113Childs, "BadNumber" );
					//Function test:__cmp__
					{
					IMethod method__cmp__117;
						IModelElement[] classBadNumber116Childs = classBadNumber116.getChildren();
						method__cmp__117 = ModelTestUtils.getAssertMethod( classBadNumber116Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__117, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_oct
			{
			IMethod methodtest_oct118;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_oct118 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_oct", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oct118, new String[] {"self"} );
			}
			//Function test:write_testfile
			{
			IMethod methodwrite_testfile119;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodwrite_testfile119 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "write_testfile", 1 );
				ModelTestUtils.assertParameterNames( methodwrite_testfile119, new String[] {"self"} );
			}
			//Function test:test_open
			{
			IMethod methodtest_open120;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_open120 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_open", 1 );
				ModelTestUtils.assertParameterNames( methodtest_open120, new String[] {"self"} );
			}
			//Function test:test_ord
			{
			IMethod methodtest_ord121;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_ord121 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_ord", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ord121, new String[] {"self"} );
			}
			//Function test:test_pow
			{
			IMethod methodtest_pow122;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_pow122 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_pow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pow122, new String[] {"self"} );
			}
			//Function test:test_range
			{
			IMethod methodtest_range123;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_range123 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_range123, new String[] {"self"} );
			}
			//Function test:test_input_and_raw_input
			{
			IMethod methodtest_input_and_raw_input124;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_input_and_raw_input124 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_input_and_raw_input", 1 );
				ModelTestUtils.assertParameterNames( methodtest_input_and_raw_input124, new String[] {"self"} );
			}
			//Function test:test_reduce
			{
			IMethod methodtest_reduce125;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_reduce125 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce125, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq126;
					IModelElement[] methodtest_reduce125Childs = methodtest_reduce125.getChildren();
					classBadSeq126 = ModelTestUtils.getAssertClass( methodtest_reduce125Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__127;
						IModelElement[] classBadSeq126Childs = classBadSeq126.getChildren();
						method__getitem__127 = ModelTestUtils.getAssertMethod( classBadSeq126Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__127, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_reload
			{
			IMethod methodtest_reload128;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_reload128 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_reload", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reload128, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr129;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_repr129 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr129, new String[] {"self"} );
			}
			//Function test:test_round
			{
			IMethod methodtest_round130;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_round130 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_round", 1 );
				ModelTestUtils.assertParameterNames( methodtest_round130, new String[] {"self"} );
			}
			//Function test:test_setattr
			{
			IMethod methodtest_setattr131;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_setattr131 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_setattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setattr131, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str132;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_str132 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str132, new String[] {"self"} );
			}
			//Function test:test_sum
			{
			IMethod methodtest_sum133;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_sum133 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_sum", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sum133, new String[] {"self"} );
				//Class test
				{
				IType classBadSeq134;
					IModelElement[] methodtest_sum133Childs = methodtest_sum133.getChildren();
					classBadSeq134 = ModelTestUtils.getAssertClass( methodtest_sum133Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__135;
						IModelElement[] classBadSeq134Childs = classBadSeq134.getChildren();
						method__getitem__135 = ModelTestUtils.getAssertMethod( classBadSeq134Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__135, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_tuple
			{
			IMethod methodtest_tuple136;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_tuple136 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tuple136, new String[] {"self"} );
			}
			//Function test:test_type
			{
			IMethod methodtest_type137;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_type137 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type137, new String[] {"self"} );
			}
			//Function test:test_unichr
			{
			IMethod methodtest_unichr138;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_unichr138 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_unichr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unichr138, new String[] {"self"} );
			}
			//Function test:get_vars_f0
			{
			IMethod methodget_vars_f0139;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodget_vars_f0139 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "get_vars_f0", 0 );
			}
			{
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest11Childs, "get_vars_f0");
			}
			//Function test:get_vars_f2
			{
			IMethod methodget_vars_f2140;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodget_vars_f2140 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "get_vars_f2", 0 );
			}
			{
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBuiltinTest11Childs, "get_vars_f2");
			}
			//Function test:test_vars
			{
			IMethod methodtest_vars141;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_vars141 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_vars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_vars141, new String[] {"self"} );
			}
			//Function test:test_zip
			{
			IMethod methodtest_zip142;
				IModelElement[] classBuiltinTest11Childs = classBuiltinTest11.getChildren();
				methodtest_zip142 = ModelTestUtils.getAssertMethod( classBuiltinTest11Childs, "test_zip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zip142, new String[] {"self"} );
				//Class test
				{
				IType classI143;
					IModelElement[] methodtest_zip142Childs = methodtest_zip142.getChildren();
					classI143 = ModelTestUtils.getAssertClass( methodtest_zip142Childs, "I" );
					//Function test:__getitem__
					{
					IMethod method__getitem__144;
						IModelElement[] classI143Childs = classI143.getChildren();
						method__getitem__144 = ModelTestUtils.getAssertMethod( classI143Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__144, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classG145;
					IModelElement[] methodtest_zip142Childs = methodtest_zip142.getChildren();
					classG145 = ModelTestUtils.getAssertClass( methodtest_zip142Childs, "G" );
				}
				//Class test
				{
				IType classSequenceWithoutALength146;
					IModelElement[] methodtest_zip142Childs = methodtest_zip142.getChildren();
					classSequenceWithoutALength146 = ModelTestUtils.getAssertClass( methodtest_zip142Childs, "SequenceWithoutALength" );
					//Function test:__getitem__
					{
					IMethod method__getitem__147;
						IModelElement[] classSequenceWithoutALength146Childs = classSequenceWithoutALength146.getChildren();
						method__getitem__147 = ModelTestUtils.getAssertMethod( classSequenceWithoutALength146Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__147, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classBadSeq148;
					IModelElement[] methodtest_zip142Childs = methodtest_zip142.getChildren();
					classBadSeq148 = ModelTestUtils.getAssertClass( methodtest_zip142Childs, "BadSeq" );
					//Function test:__getitem__
					{
					IMethod method__getitem__149;
						IModelElement[] classBadSeq148Childs = classBadSeq148.getChildren();
						method__getitem__149 = ModelTestUtils.getAssertMethod( classBadSeq148Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__149, new String[] {"self", "i"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestSorted150;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSorted150 = ModelTestUtils.getAssertClass( moduleChilds, "TestSorted" );
			//Function test:test_basic
			{
			IMethod methodtest_basic151;
				IModelElement[] classTestSorted150Childs = classTestSorted150.getChildren();
				methodtest_basic151 = ModelTestUtils.getAssertMethod( classTestSorted150Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic151, new String[] {"self"} );
			}
			//Function test:test_inputtypes
			{
			IMethod methodtest_inputtypes152;
				IModelElement[] classTestSorted150Childs = classTestSorted150.getChildren();
				methodtest_inputtypes152 = ModelTestUtils.getAssertMethod( classTestSorted150Childs, "test_inputtypes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inputtypes152, new String[] {"self"} );
			}
			//Function test:test_baddecorator
			{
			IMethod methodtest_baddecorator153;
				IModelElement[] classTestSorted150Childs = classTestSorted150.getChildren();
				methodtest_baddecorator153 = ModelTestUtils.getAssertMethod( classTestSorted150Childs, "test_baddecorator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_baddecorator153, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main154;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main154 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classCoerceNumber0Childs = classCoerceNumber0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCoerceNumber0Childs, "arg");
			}
			//Function test:__repr__
			{
			IMethod method__repr__3;
				IModelElement[] classCoerceNumber0Childs = classCoerceNumber0.getChildren();
				method__repr__3 = ModelTestUtils.getAssertMethod( classCoerceNumber0Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__3, new String[] {"self"} );
			}
			//Function test:__coerce__
			{
			IMethod method__coerce__4;
				IModelElement[] classCoerceNumber0Childs = classCoerceNumber0.getChildren();
				method__coerce__4 = ModelTestUtils.getAssertMethod( classCoerceNumber0Childs, "__coerce__", 2 );
				ModelTestUtils.assertParameterNames( method__coerce__4, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classMethodNumber5;
			IModelElement[] moduleChilds = module.getChildren();
			classMethodNumber5 = ModelTestUtils.getAssertClass( moduleChilds, "MethodNumber" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__7;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__repr__7 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__7, new String[] {"self"} );
			}
			//Function test:__add__
			{
			IMethod method__add__8;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__add__8 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__8, new String[] {"self", "other"} );
			}
			//Function test:__radd__
			{
			IMethod method__radd__9;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__radd__9 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__radd__", 2 );
				ModelTestUtils.assertParameterNames( method__radd__9, new String[] {"self", "other"} );
			}
			//Function test:__sub__
			{
			IMethod method__sub__10;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__sub__10 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__sub__", 2 );
				ModelTestUtils.assertParameterNames( method__sub__10, new String[] {"self", "other"} );
			}
			//Function test:__rsub__
			{
			IMethod method__rsub__11;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__rsub__11 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__rsub__", 2 );
				ModelTestUtils.assertParameterNames( method__rsub__11, new String[] {"self", "other"} );
			}
			//Function test:__mul__
			{
			IMethod method__mul__12;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__mul__12 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__mul__", 2 );
				ModelTestUtils.assertParameterNames( method__mul__12, new String[] {"self", "other"} );
			}
			//Function test:__rmul__
			{
			IMethod method__rmul__13;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__rmul__13 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__rmul__", 2 );
				ModelTestUtils.assertParameterNames( method__rmul__13, new String[] {"self", "other"} );
			}
			//Function test:__div__
			{
			IMethod method__div__14;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__div__14 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__div__", 2 );
				ModelTestUtils.assertParameterNames( method__div__14, new String[] {"self", "other"} );
			}
			//Function test:__rdiv__
			{
			IMethod method__rdiv__15;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__rdiv__15 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__rdiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rdiv__15, new String[] {"self", "other"} );
			}
			//Function test:__pow__
			{
			IMethod method__pow__16;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__pow__16 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__pow__", 2 );
				ModelTestUtils.assertParameterNames( method__pow__16, new String[] {"self", "other"} );
			}
			//Function test:__rpow__
			{
			IMethod method__rpow__17;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__rpow__17 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__rpow__", 2 );
				ModelTestUtils.assertParameterNames( method__rpow__17, new String[] {"self", "other"} );
			}
			//Function test:__mod__
			{
			IMethod method__mod__18;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__mod__18 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__mod__", 2 );
				ModelTestUtils.assertParameterNames( method__mod__18, new String[] {"self", "other"} );
			}
			//Function test:__rmod__
			{
			IMethod method__rmod__19;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__rmod__19 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__rmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rmod__19, new String[] {"self", "other"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__20;
				IModelElement[] classMethodNumber5Childs = classMethodNumber5.getChildren();
				method__cmp__20 = ModelTestUtils.getAssertMethod( classMethodNumber5Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__20, new String[] {"self", "other"} );
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
		IMethod methodformat_float21;
			IModelElement[] moduleChilds = module.getChildren();
			methodformat_float21 = ModelTestUtils.getAssertMethod( moduleChilds, "format_float", 1 );
			ModelTestUtils.assertParameterNames( methodformat_float21, new String[] {"value"} );
		}
		//Function test:format_result
		{
		IMethod methodformat_result22;
			IModelElement[] moduleChilds = module.getChildren();
			methodformat_result22 = ModelTestUtils.getAssertMethod( moduleChilds, "format_result", 1 );
			ModelTestUtils.assertParameterNames( methodformat_result22, new String[] {"value"} );
		}
		//Function test:do_infix_binops
		{
		IMethod methoddo_infix_binops23;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_infix_binops23 = ModelTestUtils.getAssertMethod( moduleChilds, "do_infix_binops", 0 );
		}
		//Function test:do_prefix_binops
		{
		IMethod methoddo_prefix_binops24;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_prefix_binops24 = ModelTestUtils.getAssertMethod( moduleChilds, "do_prefix_binops", 0 );
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
			{
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWrapTestCase5Childs, "wrapper");
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple8;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_simple8 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple8, new String[] {"self"} );
			}
			//Function test:test_whitespace
			{
			IMethod methodtest_whitespace9;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_whitespace9 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_whitespace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_whitespace9, new String[] {"self"} );
			}
			//Function test:test_fix_sentence_endings
			{
			IMethod methodtest_fix_sentence_endings10;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_fix_sentence_endings10 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_fix_sentence_endings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fix_sentence_endings10, new String[] {"self"} );
			}
			//Function test:test_wrap_short
			{
			IMethod methodtest_wrap_short11;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_wrap_short11 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_wrap_short", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrap_short11, new String[] {"self"} );
			}
			//Function test:test_wrap_short_1line
			{
			IMethod methodtest_wrap_short_1line12;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_wrap_short_1line12 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_wrap_short_1line", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrap_short_1line12, new String[] {"self"} );
			}
			//Function test:test_hyphenated
			{
			IMethod methodtest_hyphenated13;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_hyphenated13 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_hyphenated", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hyphenated13, new String[] {"self"} );
			}
			//Function test:test_hyphenated_numbers
			{
			IMethod methodtest_hyphenated_numbers14;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_hyphenated_numbers14 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_hyphenated_numbers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hyphenated_numbers14, new String[] {"self"} );
			}
			//Function test:test_em_dash
			{
			IMethod methodtest_em_dash15;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_em_dash15 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_em_dash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_em_dash15, new String[] {"self"} );
			}
			//Function test:test_unix_options
			{
			IMethod methodtest_unix_options16;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_unix_options16 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_unix_options", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unix_options16, new String[] {"self"} );
			}
			//Function test:test_funky_hyphens
			{
			IMethod methodtest_funky_hyphens17;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_funky_hyphens17 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_funky_hyphens", 1 );
				ModelTestUtils.assertParameterNames( methodtest_funky_hyphens17, new String[] {"self"} );
			}
			//Function test:test_punct_hyphens
			{
			IMethod methodtest_punct_hyphens18;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_punct_hyphens18 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_punct_hyphens", 1 );
				ModelTestUtils.assertParameterNames( methodtest_punct_hyphens18, new String[] {"self"} );
			}
			//Function test:test_funky_parens
			{
			IMethod methodtest_funky_parens19;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_funky_parens19 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_funky_parens", 1 );
				ModelTestUtils.assertParameterNames( methodtest_funky_parens19, new String[] {"self"} );
			}
			//Function test:test_initial_whitespace
			{
			IMethod methodtest_initial_whitespace20;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_initial_whitespace20 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_initial_whitespace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_initial_whitespace20, new String[] {"self"} );
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode21;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_unicode21 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode21, new String[] {"self"} );
			}
			//Function test:test_split
			{
			IMethod methodtest_split22;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_split22 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split22, new String[] {"self"} );
			}
			//Function test:test_bad_width
			{
			IMethod methodtest_bad_width23;
				IModelElement[] classWrapTestCase5Childs = classWrapTestCase5.getChildren();
				methodtest_bad_width23 = ModelTestUtils.getAssertMethod( classWrapTestCase5Childs, "test_bad_width", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_width23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLongWordTestCase24;
			IModelElement[] moduleChilds = module.getChildren();
			classLongWordTestCase24 = ModelTestUtils.getAssertClass( moduleChilds, "LongWordTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp25;
				IModelElement[] classLongWordTestCase24Childs = classLongWordTestCase24.getChildren();
				methodsetUp25 = ModelTestUtils.getAssertMethod( classLongWordTestCase24Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp25, new String[] {"self"} );
			}
			{
				IModelElement[] classLongWordTestCase24Childs = classLongWordTestCase24.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongWordTestCase24Childs, "wrapper");
			}
			{
				IModelElement[] classLongWordTestCase24Childs = classLongWordTestCase24.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongWordTestCase24Childs, "text");
			}
			//Function test:test_break_long
			{
			IMethod methodtest_break_long27;
				IModelElement[] classLongWordTestCase24Childs = classLongWordTestCase24.getChildren();
				methodtest_break_long27 = ModelTestUtils.getAssertMethod( classLongWordTestCase24Childs, "test_break_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_break_long27, new String[] {"self"} );
			}
			//Function test:test_nobreak_long
			{
			IMethod methodtest_nobreak_long28;
				IModelElement[] classLongWordTestCase24Childs = classLongWordTestCase24.getChildren();
				methodtest_nobreak_long28 = ModelTestUtils.getAssertMethod( classLongWordTestCase24Childs, "test_nobreak_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nobreak_long28, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIndentTestCases29;
			IModelElement[] moduleChilds = module.getChildren();
			classIndentTestCases29 = ModelTestUtils.getAssertClass( moduleChilds, "IndentTestCases" );
			//Function test:setUp
			{
			IMethod methodsetUp30;
				IModelElement[] classIndentTestCases29Childs = classIndentTestCases29.getChildren();
				methodsetUp30 = ModelTestUtils.getAssertMethod( classIndentTestCases29Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp30, new String[] {"self"} );
			}
			{
				IModelElement[] classIndentTestCases29Childs = classIndentTestCases29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIndentTestCases29Childs, "text");
			}
			//Function test:test_fill
			{
			IMethod methodtest_fill32;
				IModelElement[] classIndentTestCases29Childs = classIndentTestCases29.getChildren();
				methodtest_fill32 = ModelTestUtils.getAssertMethod( classIndentTestCases29Childs, "test_fill", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fill32, new String[] {"self"} );
			}
			//Function test:test_initial_indent
			{
			IMethod methodtest_initial_indent33;
				IModelElement[] classIndentTestCases29Childs = classIndentTestCases29.getChildren();
				methodtest_initial_indent33 = ModelTestUtils.getAssertMethod( classIndentTestCases29Childs, "test_initial_indent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_initial_indent33, new String[] {"self"} );
			}
			//Function test:test_subsequent_indent
			{
			IMethod methodtest_subsequent_indent34;
				IModelElement[] classIndentTestCases29Childs = classIndentTestCases29.getChildren();
				methodtest_subsequent_indent34 = ModelTestUtils.getAssertMethod( classIndentTestCases29Childs, "test_subsequent_indent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subsequent_indent34, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDedentTestCase35;
			IModelElement[] moduleChilds = module.getChildren();
			classDedentTestCase35 = ModelTestUtils.getAssertClass( moduleChilds, "DedentTestCase" );
			//Function test:test_dedent_nomargin
			{
			IMethod methodtest_dedent_nomargin36;
				IModelElement[] classDedentTestCase35Childs = classDedentTestCase35.getChildren();
				methodtest_dedent_nomargin36 = ModelTestUtils.getAssertMethod( classDedentTestCase35Childs, "test_dedent_nomargin", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dedent_nomargin36, new String[] {"self"} );
			}
			//Function test:test_dedent_even
			{
			IMethod methodtest_dedent_even37;
				IModelElement[] classDedentTestCase35Childs = classDedentTestCase35.getChildren();
				methodtest_dedent_even37 = ModelTestUtils.getAssertMethod( classDedentTestCase35Childs, "test_dedent_even", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dedent_even37, new String[] {"self"} );
			}
			//Function test:test_dedent_uneven
			{
			IMethod methodtest_dedent_uneven38;
				IModelElement[] classDedentTestCase35Childs = classDedentTestCase35.getChildren();
				methodtest_dedent_uneven38 = ModelTestUtils.getAssertMethod( classDedentTestCase35Childs, "test_dedent_uneven", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dedent_uneven38, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "_dir");
			}
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "_counter");
			}
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "_msgfiles");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:createMessage
			{
			IMethod methodcreateMessage4;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodcreateMessage4 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "createMessage", 3 );
				ModelTestUtils.assertParameterNames( methodcreateMessage4, new String[] {"self", "dir", "mbox"} );
			}
			//Function test:test_empty_maildir
			{
			IMethod methodtest_empty_maildir5;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_empty_maildir5 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_empty_maildir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_maildir5, new String[] {"self"} );
			}
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "mbox");
			}
			//Function test:test_nonempty_maildir_cur
			{
			IMethod methodtest_nonempty_maildir_cur7;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_nonempty_maildir_cur7 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_nonempty_maildir_cur", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_maildir_cur7, new String[] {"self"} );
			}
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "mbox");
			}
			//Function test:test_nonempty_maildir_new
			{
			IMethod methodtest_nonempty_maildir_new9;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_nonempty_maildir_new9 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_nonempty_maildir_new", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_maildir_new9, new String[] {"self"} );
			}
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "mbox");
			}
			//Function test:test_nonempty_maildir_both
			{
			IMethod methodtest_nonempty_maildir_both11;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_nonempty_maildir_both11 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_nonempty_maildir_both", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_maildir_both11, new String[] {"self"} );
			}
			{
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMaildirTestCase0Childs, "mbox");
			}
			//Function test:test_unix_mbox
			{
			IMethod methodtest_unix_mbox13;
				IModelElement[] classMaildirTestCase0Childs = classMaildirTestCase0.getChildren();
				methodtest_unix_mbox13 = ModelTestUtils.getAssertMethod( classMaildirTestCase0Childs, "test_unix_mbox", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unix_mbox13, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main14 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classG17Childs = classG17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classG17Childs, "seqn");
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__20;
				IModelElement[] classG17Childs = classG17.getChildren();
				method__getitem__20 = ModelTestUtils.getAssertMethod( classG17Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__20, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI21;
			IModelElement[] moduleChilds = module.getChildren();
			classI21 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__22;
				IModelElement[] classI21Childs = classI21.getChildren();
				method__init__22 = ModelTestUtils.getAssertMethod( classI21Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__22, new String[] {"self", "seqn"} );
			}
			{
				IModelElement[] classI21Childs = classI21.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI21Childs, "seqn");
			}
			{
				IModelElement[] classI21Childs = classI21.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI21Childs, "i");
			}
			//Function test:__iter__
			{
			IMethod method__iter__24;
				IModelElement[] classI21Childs = classI21.getChildren();
				method__iter__24 = ModelTestUtils.getAssertMethod( classI21Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__24, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext25;
				IModelElement[] classI21Childs = classI21.getChildren();
				methodnext25 = ModelTestUtils.getAssertMethod( classI21Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext25, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg26;
			IModelElement[] moduleChilds = module.getChildren();
			classIg26 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__27;
				IModelElement[] classIg26Childs = classIg26.getChildren();
				method__init__27 = ModelTestUtils.getAssertMethod( classIg26Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__27, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__28;
				IModelElement[] classIg26Childs = classIg26.getChildren();
				method__iter__28 = ModelTestUtils.getAssertMethod( classIg26Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__28, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX29;
			IModelElement[] moduleChilds = module.getChildren();
			classX29 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__30;
				IModelElement[] classX29Childs = classX29.getChildren();
				method__init__30 = ModelTestUtils.getAssertMethod( classX29Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__30, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext31;
				IModelElement[] classX29Childs = classX29.getChildren();
				methodnext31 = ModelTestUtils.getAssertMethod( classX29Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext31, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN32;
			IModelElement[] moduleChilds = module.getChildren();
			classN32 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__33;
				IModelElement[] classN32Childs = classN32.getChildren();
				method__init__33 = ModelTestUtils.getAssertMethod( classN32Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__33, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__34;
				IModelElement[] classN32Childs = classN32.getChildren();
				method__iter__34 = ModelTestUtils.getAssertMethod( classN32Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__34, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE35;
			IModelElement[] moduleChilds = module.getChildren();
			classE35 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__36;
				IModelElement[] classE35Childs = classE35.getChildren();
				method__init__36 = ModelTestUtils.getAssertMethod( classE35Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__36, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__37;
				IModelElement[] classE35Childs = classE35.getChildren();
				method__iter__37 = ModelTestUtils.getAssertMethod( classE35Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__37, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext38;
				IModelElement[] classE35Childs = classE35.getChildren();
				methodnext38 = ModelTestUtils.getAssertMethod( classE35Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext38, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS39;
			IModelElement[] moduleChilds = module.getChildren();
			classS39 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__40;
				IModelElement[] classS39Childs = classS39.getChildren();
				method__init__40 = ModelTestUtils.getAssertMethod( classS39Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__40, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__41;
				IModelElement[] classS39Childs = classS39.getChildren();
				method__iter__41 = ModelTestUtils.getAssertMethod( classS39Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__41, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext42;
				IModelElement[] classS39Childs = classS39.getChildren();
				methodnext42 = ModelTestUtils.getAssertMethod( classS39Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext42, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL43;
			IModelElement[] moduleChilds = module.getChildren();
			methodL43 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL43, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestErrorHandling44;
			IModelElement[] moduleChilds = module.getChildren();
			classTestErrorHandling44 = ModelTestUtils.getAssertClass( moduleChilds, "TestErrorHandling" );
			//Function test:test_non_sequence
			{
			IMethod methodtest_non_sequence45;
				IModelElement[] classTestErrorHandling44Childs = classTestErrorHandling44.getChildren();
				methodtest_non_sequence45 = ModelTestUtils.getAssertMethod( classTestErrorHandling44Childs, "test_non_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_sequence45, new String[] {"self"} );
			}
			//Function test:test_len_only
			{
			IMethod methodtest_len_only46;
				IModelElement[] classTestErrorHandling44Childs = classTestErrorHandling44.getChildren();
				methodtest_len_only46 = ModelTestUtils.getAssertMethod( classTestErrorHandling44Childs, "test_len_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len_only46, new String[] {"self"} );
			}
			//Function test:test_get_only
			{
			IMethod methodtest_get_only47;
				IModelElement[] classTestErrorHandling44Childs = classTestErrorHandling44.getChildren();
				methodtest_get_only47 = ModelTestUtils.getAssertMethod( classTestErrorHandling44Childs, "test_get_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_only47, new String[] {"self"} );
			}
			//Function test:test_get_only
			{
			IMethod methodtest_get_only48;
				IModelElement[] classTestErrorHandling44Childs = classTestErrorHandling44.getChildren();
				methodtest_get_only48 = ModelTestUtils.getAssertMethod( classTestErrorHandling44Childs, "test_get_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_only48, new String[] {"self"} );
			}
			//Function test:test_arg_parsing
			{
			IMethod methodtest_arg_parsing49;
				IModelElement[] classTestErrorHandling44Childs = classTestErrorHandling44.getChildren();
				methodtest_arg_parsing49 = ModelTestUtils.getAssertMethod( classTestErrorHandling44Childs, "test_arg_parsing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_arg_parsing49, new String[] {"self"} );
			}
			//Function test:test_iterable_args
			{
			IMethod methodtest_iterable_args50;
				IModelElement[] classTestErrorHandling44Childs = classTestErrorHandling44.getChildren();
				methodtest_iterable_args50 = ModelTestUtils.getAssertMethod( classTestErrorHandling44Childs, "test_iterable_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iterable_args50, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main51;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main51 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main51, new String[] {"verbose"} );
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
			{
				IModelElement[] classbase_set0Childs = classbase_set0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classbase_set0Childs, "el");
			}
		}
		//Class test
		{
		IType classset3;
			IModelElement[] moduleChilds = module.getChildren();
			classset3 = ModelTestUtils.getAssertClass( moduleChilds, "set" );
			//Function test:__contains__
			{
			IMethod method__contains__4;
				IModelElement[] classset3Childs = classset3.getChildren();
				method__contains__4 = ModelTestUtils.getAssertMethod( classset3Childs, "__contains__", 2 );
				ModelTestUtils.assertParameterNames( method__contains__4, new String[] {"self", "el"} );
			}
		}
		//Class test
		{
		IType classseq5;
			IModelElement[] moduleChilds = module.getChildren();
			classseq5 = ModelTestUtils.getAssertClass( moduleChilds, "seq" );
			//Function test:__getitem__
			{
			IMethod method__getitem__6;
				IModelElement[] classseq5Childs = classseq5.getChildren();
				method__getitem__6 = ModelTestUtils.getAssertMethod( classseq5Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__6, new String[] {"self", "n"} );
			}
		}
		//Function test:check
		{
		IMethod methodcheck7;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck7 = ModelTestUtils.getAssertMethod( moduleChilds, "check", 2 );
			ModelTestUtils.assertParameterNames( methodcheck7, new String[] {"ok", "args"} );
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
		IType classDeviant18;
			IModelElement[] moduleChilds = module.getChildren();
			classDeviant18 = ModelTestUtils.getAssertClass( moduleChilds, "Deviant1" );
			{
				IModelElement[] classDeviant18Childs = classDeviant18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDeviant18Childs, "aList");
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__9;
				IModelElement[] classDeviant18Childs = classDeviant18.getChildren();
				method__cmp__9 = ModelTestUtils.getAssertMethod( classDeviant18Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__9, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classDeviant210;
			IModelElement[] moduleChilds = module.getChildren();
			classDeviant210 = ModelTestUtils.getAssertClass( moduleChilds, "Deviant2" );
			//Function test:__cmp__
			{
			IMethod method__cmp__11;
				IModelElement[] classDeviant210Childs = classDeviant210.getChildren();
				method__cmp__11 = ModelTestUtils.getAssertMethod( classDeviant210Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__11, new String[] {"self", "other"} );
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
			{
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFakeSocket0Childs, "text");
			}
			{
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFakeSocket0Childs, "fileclass");
			}
			//Function test:sendall
			{
			IMethod methodsendall3;
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				methodsendall3 = ModelTestUtils.getAssertMethod( classFakeSocket0Childs, "sendall", 2 );
				ModelTestUtils.assertParameterNames( methodsendall3, new String[] {"self", "data"} );
			}
			{
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFakeSocket0Childs, "data");
			}
			//Function test:makefile
			{
			IMethod methodmakefile5;
				IModelElement[] classFakeSocket0Childs = classFakeSocket0.getChildren();
				methodmakefile5 = ModelTestUtils.getAssertMethod( classFakeSocket0Childs, "makefile", 3 );
				ModelTestUtils.assertParameterNames( methodmakefile5, new String[] {"self", "mode", "bufsize"} );
			}
		}
		//Class test
		{
		IType classNoEOFStringIO6;
			IModelElement[] moduleChilds = module.getChildren();
			classNoEOFStringIO6 = ModelTestUtils.getAssertClass( moduleChilds, "NoEOFStringIO" );
			//Function test:read
			{
			IMethod methodread7;
				IModelElement[] classNoEOFStringIO6Childs = classNoEOFStringIO6.getChildren();
				methodread7 = ModelTestUtils.getAssertMethod( classNoEOFStringIO6Childs, "read", 2 );
				ModelTestUtils.assertParameterNames( methodread7, new String[] {"self", "n"} );
			}
			//Function test:readline
			{
			IMethod methodreadline8;
				IModelElement[] classNoEOFStringIO6Childs = classNoEOFStringIO6.getChildren();
				methodreadline8 = ModelTestUtils.getAssertMethod( classNoEOFStringIO6Childs, "readline", 2 );
				ModelTestUtils.assertParameterNames( methodreadline8, new String[] {"self", "length"} );
			}
		}
		//Class test
		{
		IType classHeaderTests9;
			IModelElement[] moduleChilds = module.getChildren();
			classHeaderTests9 = ModelTestUtils.getAssertClass( moduleChilds, "HeaderTests" );
			//Function test:test_auto_headers
			{
			IMethod methodtest_auto_headers10;
				IModelElement[] classHeaderTests9Childs = classHeaderTests9.getChildren();
				methodtest_auto_headers10 = ModelTestUtils.getAssertMethod( classHeaderTests9Childs, "test_auto_headers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_auto_headers10, new String[] {"self"} );
				//Class test
				{
				IType classHeaderCountingBuffer11;
					IModelElement[] methodtest_auto_headers10Childs = methodtest_auto_headers10.getChildren();
					classHeaderCountingBuffer11 = ModelTestUtils.getAssertClass( methodtest_auto_headers10Childs, "HeaderCountingBuffer" );
					//Function test:__init__
					{
					IMethod method__init__12;
						IModelElement[] classHeaderCountingBuffer11Childs = classHeaderCountingBuffer11.getChildren();
						method__init__12 = ModelTestUtils.getAssertMethod( classHeaderCountingBuffer11Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self"} );
					}
					{
						IModelElement[] classHeaderCountingBuffer11Childs = classHeaderCountingBuffer11.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classHeaderCountingBuffer11Childs, "count");
					}
					//Function test:append
					{
					IMethod methodappend14;
						IModelElement[] classHeaderCountingBuffer11Childs = classHeaderCountingBuffer11.getChildren();
						methodappend14 = ModelTestUtils.getAssertMethod( classHeaderCountingBuffer11Childs, "append", 2 );
						ModelTestUtils.assertParameterNames( methodappend14, new String[] {"self", "item"} );
					}
				}
			}
		}
		//Function test:test
		{
		IMethod methodtest15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest15 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}
		//Function test:_test
		{
		IMethod method_test16;
			IModelElement[] moduleChilds = module.getChildren();
			method_test16 = ModelTestUtils.getAssertMethod( moduleChilds, "_test", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main17, new String[] {"verbose"} );
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
	public void testModelGen290( ) throws Exception {
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
			{
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestModule2Childs, "_filters");
			}
			{
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestModule2Childs, "_showwarning");
			}
			{
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestModule2Childs, "ignored");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown5;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtearDown5 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown5, new String[] {"self"} );
			}
			//Function test:test_warn_default_category
			{
			IMethod methodtest_warn_default_category6;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtest_warn_default_category6 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "test_warn_default_category", 1 );
				ModelTestUtils.assertParameterNames( methodtest_warn_default_category6, new String[] {"self"} );
			}
			//Function test:test_warn_specific_category
			{
			IMethod methodtest_warn_specific_category7;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtest_warn_specific_category7 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "test_warn_specific_category", 1 );
				ModelTestUtils.assertParameterNames( methodtest_warn_specific_category7, new String[] {"self"} );
			}
			//Function test:test_filtering
			{
			IMethod methodtest_filtering8;
				IModelElement[] classTestModule2Childs = classTestModule2.getChildren();
				methodtest_filtering8 = ModelTestUtils.getAssertMethod( classTestModule2Childs, "test_filtering", 1 );
				ModelTestUtils.assertParameterNames( methodtest_filtering8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main9, new String[] {"verbose"} );
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
	public void testModelGen293( ) throws Exception {
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
			{
				IModelElement[] classBasicIterClass0Childs = classBasicIterClass0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicIterClass0Childs, "n");
			}
			{
				IModelElement[] classBasicIterClass0Childs = classBasicIterClass0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicIterClass0Childs, "i");
			}
			//Function test:next
			{
			IMethod methodnext3;
				IModelElement[] classBasicIterClass0Childs = classBasicIterClass0.getChildren();
				methodnext3 = ModelTestUtils.getAssertMethod( classBasicIterClass0Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext3, new String[] {"self"} );
			}
			{
				IModelElement[] classBasicIterClass0Childs = classBasicIterClass0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicIterClass0Childs, "i");
			}
		}
		//Class test
		{
		IType classIteratingSequenceClass5;
			IModelElement[] moduleChilds = module.getChildren();
			classIteratingSequenceClass5 = ModelTestUtils.getAssertClass( moduleChilds, "IteratingSequenceClass" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classIteratingSequenceClass5Childs = classIteratingSequenceClass5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classIteratingSequenceClass5Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "n"} );
			}
			{
				IModelElement[] classIteratingSequenceClass5Childs = classIteratingSequenceClass5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classIteratingSequenceClass5Childs, "n");
			}
			//Function test:__iter__
			{
			IMethod method__iter__8;
				IModelElement[] classIteratingSequenceClass5Childs = classIteratingSequenceClass5.getChildren();
				method__iter__8 = ModelTestUtils.getAssertMethod( classIteratingSequenceClass5Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSequenceClass9;
			IModelElement[] moduleChilds = module.getChildren();
			classSequenceClass9 = ModelTestUtils.getAssertClass( moduleChilds, "SequenceClass" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classSequenceClass9Childs = classSequenceClass9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classSequenceClass9Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self", "n"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__11;
				IModelElement[] classSequenceClass9Childs = classSequenceClass9.getChildren();
				method__getitem__11 = ModelTestUtils.getAssertMethod( classSequenceClass9Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__11, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classTestCase12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCase12 = ModelTestUtils.getAssertClass( moduleChilds, "TestCase" );
			//Function test:check_iterator
			{
			IMethod methodcheck_iterator13;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodcheck_iterator13 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "check_iterator", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_iterator13, new String[] {"self", "it", "seq"} );
			}
			//Function test:check_for_loop
			{
			IMethod methodcheck_for_loop14;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodcheck_for_loop14 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "check_for_loop", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_for_loop14, new String[] {"self", "expr", "seq"} );
			}
			//Function test:test_iter_basic
			{
			IMethod methodtest_iter_basic15;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_basic15 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_basic15, new String[] {"self"} );
			}
			//Function test:test_iter_idempotency
			{
			IMethod methodtest_iter_idempotency16;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_idempotency16 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_idempotency", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_idempotency16, new String[] {"self"} );
			}
			//Function test:test_iter_for_loop
			{
			IMethod methodtest_iter_for_loop17;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_for_loop17 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_for_loop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_for_loop17, new String[] {"self"} );
			}
			//Function test:test_iter_independence
			{
			IMethod methodtest_iter_independence18;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_independence18 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_independence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_independence18, new String[] {"self"} );
			}
			//Function test:test_nested_comprehensions_iter
			{
			IMethod methodtest_nested_comprehensions_iter19;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_nested_comprehensions_iter19 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_nested_comprehensions_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_comprehensions_iter19, new String[] {"self"} );
			}
			//Function test:test_nested_comprehensions_for
			{
			IMethod methodtest_nested_comprehensions_for20;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_nested_comprehensions_for20 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_nested_comprehensions_for", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_comprehensions_for20, new String[] {"self"} );
			}
			//Function test:test_iter_class_for
			{
			IMethod methodtest_iter_class_for21;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_class_for21 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_class_for", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_class_for21, new String[] {"self"} );
			}
			//Function test:test_iter_class_iter
			{
			IMethod methodtest_iter_class_iter22;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_class_iter22 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_class_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_class_iter22, new String[] {"self"} );
			}
			//Function test:test_seq_class_for
			{
			IMethod methodtest_seq_class_for23;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_seq_class_for23 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_seq_class_for", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seq_class_for23, new String[] {"self"} );
			}
			//Function test:test_seq_class_iter
			{
			IMethod methodtest_seq_class_iter24;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_seq_class_iter24 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_seq_class_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seq_class_iter24, new String[] {"self"} );
			}
			//Function test:test_iter_callable
			{
			IMethod methodtest_iter_callable25;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_callable25 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_callable25, new String[] {"self"} );
				//Class test
				{
				IType classC26;
					IModelElement[] methodtest_iter_callable25Childs = methodtest_iter_callable25.getChildren();
					classC26 = ModelTestUtils.getAssertClass( methodtest_iter_callable25Childs, "C" );
					//Function test:__init__
					{
					IMethod method__init__27;
						IModelElement[] classC26Childs = classC26.getChildren();
						method__init__27 = ModelTestUtils.getAssertMethod( classC26Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__27, new String[] {"self"} );
					}
					{
						IModelElement[] classC26Childs = classC26.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC26Childs, "i");
					}
					//Function test:__call__
					{
					IMethod method__call__29;
						IModelElement[] classC26Childs = classC26.getChildren();
						method__call__29 = ModelTestUtils.getAssertMethod( classC26Childs, "__call__", 1 );
						ModelTestUtils.assertParameterNames( method__call__29, new String[] {"self"} );
					}
					{
						IModelElement[] classC26Childs = classC26.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classC26Childs, "i");
					}
				}
			}
			//Function test:test_iter_function
			{
			IMethod methodtest_iter_function31;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_function31 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_function31, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam32;
					IModelElement[] methodtest_iter_function31Childs = methodtest_iter_function31.getChildren();
					methodspam32 = ModelTestUtils.getAssertMethod( methodtest_iter_function31Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam32, new String[] {"state"} );
				}
			}
			//Function test:test_iter_function_stop
			{
			IMethod methodtest_iter_function_stop33;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_function_stop33 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_function_stop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_function_stop33, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam34;
					IModelElement[] methodtest_iter_function_stop33Childs = methodtest_iter_function_stop33.getChildren();
					methodspam34 = ModelTestUtils.getAssertMethod( methodtest_iter_function_stop33Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam34, new String[] {"state"} );
				}
			}
			//Function test:test_exception_function
			{
			IMethod methodtest_exception_function35;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_exception_function35 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_exception_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_function35, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam36;
					IModelElement[] methodtest_exception_function35Childs = methodtest_exception_function35.getChildren();
					methodspam36 = ModelTestUtils.getAssertMethod( methodtest_exception_function35Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam36, new String[] {"state"} );
				}
			}
			//Function test:test_exception_sequence
			{
			IMethod methodtest_exception_sequence37;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_exception_sequence37 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_exception_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_sequence37, new String[] {"self"} );
				//Class test
				{
				IType classMySequenceClass38;
					IModelElement[] methodtest_exception_sequence37Childs = methodtest_exception_sequence37.getChildren();
					classMySequenceClass38 = ModelTestUtils.getAssertClass( methodtest_exception_sequence37Childs, "MySequenceClass" );
					//Function test:__getitem__
					{
					IMethod method__getitem__39;
						IModelElement[] classMySequenceClass38Childs = classMySequenceClass38.getChildren();
						method__getitem__39 = ModelTestUtils.getAssertMethod( classMySequenceClass38Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__39, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_stop_sequence
			{
			IMethod methodtest_stop_sequence40;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_stop_sequence40 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_stop_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_sequence40, new String[] {"self"} );
				//Class test
				{
				IType classMySequenceClass41;
					IModelElement[] methodtest_stop_sequence40Childs = methodtest_stop_sequence40.getChildren();
					classMySequenceClass41 = ModelTestUtils.getAssertClass( methodtest_stop_sequence40Childs, "MySequenceClass" );
					//Function test:__getitem__
					{
					IMethod method__getitem__42;
						IModelElement[] classMySequenceClass41Childs = classMySequenceClass41.getChildren();
						method__getitem__42 = ModelTestUtils.getAssertMethod( classMySequenceClass41Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__42, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_iter_big_range
			{
			IMethod methodtest_iter_big_range43;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_big_range43 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_big_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_big_range43, new String[] {"self"} );
			}
			//Function test:test_iter_empty
			{
			IMethod methodtest_iter_empty44;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_empty44 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_empty44, new String[] {"self"} );
			}
			//Function test:test_iter_tuple
			{
			IMethod methodtest_iter_tuple45;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_tuple45 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_tuple45, new String[] {"self"} );
			}
			//Function test:test_iter_xrange
			{
			IMethod methodtest_iter_xrange46;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_xrange46 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_xrange", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_xrange46, new String[] {"self"} );
			}
			//Function test:test_iter_string
			{
			IMethod methodtest_iter_string47;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_string47 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_string47, new String[] {"self"} );
			}
			//Function test:test_iter_unicode
			{
			IMethod methodtest_iter_unicode48;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_unicode48 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_unicode48, new String[] {"self"} );
			}
			//Function test:test_iter_dict
			{
			IMethod methodtest_iter_dict49;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_dict49 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_dict49, new String[] {"self"} );
			}
			//Function test:test_iter_file
			{
			IMethod methodtest_iter_file50;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_iter_file50 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_iter_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_file50, new String[] {"self"} );
			}
			//Function test:test_builtin_list
			{
			IMethod methodtest_builtin_list51;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_list51 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_list51, new String[] {"self"} );
			}
			//Function test:test_builtin_tuple
			{
			IMethod methodtest_builtin_tuple52;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_tuple52 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_tuple52, new String[] {"self"} );
			}
			//Function test:test_builtin_filter
			{
			IMethod methodtest_builtin_filter53;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_filter53 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_filter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_filter53, new String[] {"self"} );
				//Class test
				{
				IType classBoolean54;
					IModelElement[] methodtest_builtin_filter53Childs = methodtest_builtin_filter53.getChildren();
					classBoolean54 = ModelTestUtils.getAssertClass( methodtest_builtin_filter53Childs, "Boolean" );
					//Function test:__init__
					{
					IMethod method__init__55;
						IModelElement[] classBoolean54Childs = classBoolean54.getChildren();
						method__init__55 = ModelTestUtils.getAssertMethod( classBoolean54Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__55, new String[] {"self", "truth"} );
					}
					{
						IModelElement[] classBoolean54Childs = classBoolean54.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classBoolean54Childs, "truth");
					}
					//Function test:__nonzero__
					{
					IMethod method__nonzero__57;
						IModelElement[] classBoolean54Childs = classBoolean54.getChildren();
						method__nonzero__57 = ModelTestUtils.getAssertMethod( classBoolean54Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__57, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classSeq58;
					IModelElement[] methodtest_builtin_filter53Childs = methodtest_builtin_filter53.getChildren();
					classSeq58 = ModelTestUtils.getAssertClass( methodtest_builtin_filter53Childs, "Seq" );
					//Function test:__init__
					{
					IMethod method__init__59;
						IModelElement[] classSeq58Childs = classSeq58.getChildren();
						method__init__59 = ModelTestUtils.getAssertMethod( classSeq58Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__59, new String[] {"self", "args"} );
					}
					{
						IModelElement[] classSeq58Childs = classSeq58.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classSeq58Childs, "vals");
					}
					//Function test:__iter__
					{
					IMethod method__iter__61;
						IModelElement[] classSeq58Childs = classSeq58.getChildren();
						method__iter__61 = ModelTestUtils.getAssertMethod( classSeq58Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__61, new String[] {"self"} );
						//Class test
						{
						IType classSeqIter62;
							IModelElement[] method__iter__61Childs = method__iter__61.getChildren();
							classSeqIter62 = ModelTestUtils.getAssertClass( method__iter__61Childs, "SeqIter" );
							//Function test:__init__
							{
							IMethod method__init__63;
								IModelElement[] classSeqIter62Childs = classSeqIter62.getChildren();
								method__init__63 = ModelTestUtils.getAssertMethod( classSeqIter62Childs, "__init__", 2 );
								ModelTestUtils.assertParameterNames( method__init__63, new String[] {"self", "vals"} );
							}
							{
								IModelElement[] classSeqIter62Childs = classSeqIter62.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classSeqIter62Childs, "vals");
							}
							{
								IModelElement[] classSeqIter62Childs = classSeqIter62.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classSeqIter62Childs, "i");
							}
							//Function test:__iter__
							{
							IMethod method__iter__65;
								IModelElement[] classSeqIter62Childs = classSeqIter62.getChildren();
								method__iter__65 = ModelTestUtils.getAssertMethod( classSeqIter62Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__65, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext66;
								IModelElement[] classSeqIter62Childs = classSeqIter62.getChildren();
								methodnext66 = ModelTestUtils.getAssertMethod( classSeqIter62Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext66, new String[] {"self"} );
							}
							{
								IModelElement[] classSeqIter62Childs = classSeqIter62.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classSeqIter62Childs, "i");
							}
						}
					}
				}
			}
			//Function test:test_builtin_max_min
			{
			IMethod methodtest_builtin_max_min68;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_max_min68 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_max_min", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_max_min68, new String[] {"self"} );
			}
			//Function test:test_builtin_map
			{
			IMethod methodtest_builtin_map69;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_map69 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_map", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_map69, new String[] {"self"} );
			}
			//Function test:test_builtin_zip
			{
			IMethod methodtest_builtin_zip70;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_zip70 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_zip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_zip70, new String[] {"self"} );
				//Class test
				{
				IType classIntsFrom71;
					IModelElement[] methodtest_builtin_zip70Childs = methodtest_builtin_zip70.getChildren();
					classIntsFrom71 = ModelTestUtils.getAssertClass( methodtest_builtin_zip70Childs, "IntsFrom" );
					//Function test:__init__
					{
					IMethod method__init__72;
						IModelElement[] classIntsFrom71Childs = classIntsFrom71.getChildren();
						method__init__72 = ModelTestUtils.getAssertMethod( classIntsFrom71Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__72, new String[] {"self", "start"} );
					}
					{
						IModelElement[] classIntsFrom71Childs = classIntsFrom71.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classIntsFrom71Childs, "i");
					}
					//Function test:__iter__
					{
					IMethod method__iter__74;
						IModelElement[] classIntsFrom71Childs = classIntsFrom71.getChildren();
						method__iter__74 = ModelTestUtils.getAssertMethod( classIntsFrom71Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__74, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext75;
						IModelElement[] classIntsFrom71Childs = classIntsFrom71.getChildren();
						methodnext75 = ModelTestUtils.getAssertMethod( classIntsFrom71Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext75, new String[] {"self"} );
					}
					{
						IModelElement[] classIntsFrom71Childs = classIntsFrom71.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classIntsFrom71Childs, "i");
					}
				}
				//Class test
				{
				IType classNoGuessLen577;
					IModelElement[] methodtest_builtin_zip70Childs = methodtest_builtin_zip70.getChildren();
					classNoGuessLen577 = ModelTestUtils.getAssertClass( methodtest_builtin_zip70Childs, "NoGuessLen5" );
					//Function test:__getitem__
					{
					IMethod method__getitem__78;
						IModelElement[] classNoGuessLen577Childs = classNoGuessLen577.getChildren();
						method__getitem__78 = ModelTestUtils.getAssertMethod( classNoGuessLen577Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__78, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classGuess3Len579;
					IModelElement[] methodtest_builtin_zip70Childs = methodtest_builtin_zip70.getChildren();
					classGuess3Len579 = ModelTestUtils.getAssertClass( methodtest_builtin_zip70Childs, "Guess3Len5" );
					//Function test:__len__
					{
					IMethod method__len__80;
						IModelElement[] classGuess3Len579Childs = classGuess3Len579.getChildren();
						method__len__80 = ModelTestUtils.getAssertMethod( classGuess3Len579Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__80, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classGuess30Len581;
					IModelElement[] methodtest_builtin_zip70Childs = methodtest_builtin_zip70.getChildren();
					classGuess30Len581 = ModelTestUtils.getAssertClass( methodtest_builtin_zip70Childs, "Guess30Len5" );
					//Function test:__len__
					{
					IMethod method__len__82;
						IModelElement[] classGuess30Len581Childs = classGuess30Len581.getChildren();
						method__len__82 = ModelTestUtils.getAssertMethod( classGuess30Len581Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__82, new String[] {"self"} );
					}
				}
			}
			//Function test:test_builtin_reduce
			{
			IMethod methodtest_builtin_reduce83;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_builtin_reduce83 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_builtin_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_reduce83, new String[] {"self"} );
			}
			//Function test:test_unicode_join_endcase
			{
			IMethod methodtest_unicode_join_endcase84;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_unicode_join_endcase84 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_unicode_join_endcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_join_endcase84, new String[] {"self"} );
				//Class test
				{
				IType classOhPhooey85;
					IModelElement[] methodtest_unicode_join_endcase84Childs = methodtest_unicode_join_endcase84.getChildren();
					classOhPhooey85 = ModelTestUtils.getAssertClass( methodtest_unicode_join_endcase84Childs, "OhPhooey" );
					//Function test:__init__
					{
					IMethod method__init__86;
						IModelElement[] classOhPhooey85Childs = classOhPhooey85.getChildren();
						method__init__86 = ModelTestUtils.getAssertMethod( classOhPhooey85Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__86, new String[] {"self", "seq"} );
					}
					{
						IModelElement[] classOhPhooey85Childs = classOhPhooey85.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classOhPhooey85Childs, "it");
					}
					{
						IModelElement[] classOhPhooey85Childs = classOhPhooey85.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classOhPhooey85Childs, "i");
					}
					//Function test:__iter__
					{
					IMethod method__iter__88;
						IModelElement[] classOhPhooey85Childs = classOhPhooey85.getChildren();
						method__iter__88 = ModelTestUtils.getAssertMethod( classOhPhooey85Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__88, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext89;
						IModelElement[] classOhPhooey85Childs = classOhPhooey85.getChildren();
						methodnext89 = ModelTestUtils.getAssertMethod( classOhPhooey85Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext89, new String[] {"self"} );
					}
					{
						IModelElement[] classOhPhooey85Childs = classOhPhooey85.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classOhPhooey85Childs, "i");
					}
				}
			}
			//Function test:test_unicode_join_endcase
			{
			IMethod methodtest_unicode_join_endcase91;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_unicode_join_endcase91 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_unicode_join_endcase", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_join_endcase91, new String[] {"self"} );
			}
			//Function test:test_in_and_not_in
			{
			IMethod methodtest_in_and_not_in92;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_in_and_not_in92 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_in_and_not_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_in_and_not_in92, new String[] {"self"} );
			}
			//Function test:test_countOf
			{
			IMethod methodtest_countOf93;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_countOf93 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_countOf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_countOf93, new String[] {"self"} );
			}
			//Function test:test_indexOf
			{
			IMethod methodtest_indexOf94;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_indexOf94 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_indexOf", 1 );
				ModelTestUtils.assertParameterNames( methodtest_indexOf94, new String[] {"self"} );
			}
			//Function test:test_writelines
			{
			IMethod methodtest_writelines95;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_writelines95 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_writelines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writelines95, new String[] {"self"} );
				//Class test
				{
				IType classIterator96;
					IModelElement[] methodtest_writelines95Childs = methodtest_writelines95.getChildren();
					classIterator96 = ModelTestUtils.getAssertClass( methodtest_writelines95Childs, "Iterator" );
					//Function test:__init__
					{
					IMethod method__init__97;
						IModelElement[] classIterator96Childs = classIterator96.getChildren();
						method__init__97 = ModelTestUtils.getAssertMethod( classIterator96Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__97, new String[] {"self", "start", "finish"} );
					}
					{
						IModelElement[] classIterator96Childs = classIterator96.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classIterator96Childs, "start");
					}
					{
						IModelElement[] classIterator96Childs = classIterator96.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classIterator96Childs, "finish");
					}
					{
						IModelElement[] classIterator96Childs = classIterator96.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classIterator96Childs, "i");
					}
					//Function test:next
					{
					IMethod methodnext99;
						IModelElement[] classIterator96Childs = classIterator96.getChildren();
						methodnext99 = ModelTestUtils.getAssertMethod( classIterator96Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext99, new String[] {"self"} );
					}
					//Function test:__iter__
					{
					IMethod method__iter__100;
						IModelElement[] classIterator96Childs = classIterator96.getChildren();
						method__iter__100 = ModelTestUtils.getAssertMethod( classIterator96Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__100, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classWhatever101;
					IModelElement[] methodtest_writelines95Childs = methodtest_writelines95.getChildren();
					classWhatever101 = ModelTestUtils.getAssertClass( methodtest_writelines95Childs, "Whatever" );
					//Function test:__init__
					{
					IMethod method__init__102;
						IModelElement[] classWhatever101Childs = classWhatever101.getChildren();
						method__init__102 = ModelTestUtils.getAssertMethod( classWhatever101Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__102, new String[] {"self", "start", "finish"} );
					}
					{
						IModelElement[] classWhatever101Childs = classWhatever101.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classWhatever101Childs, "start");
					}
					{
						IModelElement[] classWhatever101Childs = classWhatever101.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classWhatever101Childs, "finish");
					}
					//Function test:__iter__
					{
					IMethod method__iter__104;
						IModelElement[] classWhatever101Childs = classWhatever101.getChildren();
						method__iter__104 = ModelTestUtils.getAssertMethod( classWhatever101Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__104, new String[] {"self"} );
					}
				}
			}
			//Function test:test_unpack_iter
			{
			IMethod methodtest_unpack_iter105;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_unpack_iter105 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_unpack_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unpack_iter105, new String[] {"self"} );
				//Class test
				{
				IType classC106;
					IModelElement[] methodtest_unpack_iter105Childs = methodtest_unpack_iter105.getChildren();
					classC106 = ModelTestUtils.getAssertClass( methodtest_unpack_iter105Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__107;
						IModelElement[] classC106Childs = classC106.getChildren();
						method__new__107 = ModelTestUtils.getAssertMethod( classC106Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__107, new String[] {"cls"} );
					}
					//Function test:__del__
					{
					IMethod method__del__108;
						IModelElement[] classC106Childs = classC106.getChildren();
						method__del__108 = ModelTestUtils.getAssertMethod( classC106Childs, "__del__", 1 );
						ModelTestUtils.assertParameterNames( method__del__108, new String[] {"self"} );
					}
				}
			}
			//Function test:test_sinkstate_list
			{
			IMethod methodtest_sinkstate_list109;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_list109 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_list109, new String[] {"self"} );
			}
			//Function test:test_sinkstate_tuple
			{
			IMethod methodtest_sinkstate_tuple110;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_tuple110 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_tuple110, new String[] {"self"} );
			}
			//Function test:test_sinkstate_string
			{
			IMethod methodtest_sinkstate_string111;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_string111 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_string111, new String[] {"self"} );
			}
			//Function test:test_sinkstate_sequence
			{
			IMethod methodtest_sinkstate_sequence112;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_sequence112 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_sequence112, new String[] {"self"} );
			}
			//Function test:test_sinkstate_callable
			{
			IMethod methodtest_sinkstate_callable113;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_callable113 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_callable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_callable113, new String[] {"self"} );
				//Function test:spam
				{
				IMethod methodspam114;
					IModelElement[] methodtest_sinkstate_callable113Childs = methodtest_sinkstate_callable113.getChildren();
					methodspam114 = ModelTestUtils.getAssertMethod( methodtest_sinkstate_callable113Childs, "spam", 1 );
					ModelTestUtils.assertParameterNames( methodspam114, new String[] {"state"} );
				}
			}
			//Function test:test_sinkstate_dict
			{
			IMethod methodtest_sinkstate_dict115;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_dict115 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_dict115, new String[] {"self"} );
			}
			//Function test:test_sinkstate_yield
			{
			IMethod methodtest_sinkstate_yield116;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_yield116 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_yield", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_yield116, new String[] {"self"} );
				//Function test:gen
				{
				IMethod methodgen117;
					IModelElement[] methodtest_sinkstate_yield116Childs = methodtest_sinkstate_yield116.getChildren();
					methodgen117 = ModelTestUtils.getAssertMethod( methodtest_sinkstate_yield116Childs, "gen", 0 );
				}
			}
			//Function test:test_sinkstate_range
			{
			IMethod methodtest_sinkstate_range118;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_range118 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_range118, new String[] {"self"} );
			}
			//Function test:test_sinkstate_enumerate
			{
			IMethod methodtest_sinkstate_enumerate119;
				IModelElement[] classTestCase12Childs = classTestCase12.getChildren();
				methodtest_sinkstate_enumerate119 = ModelTestUtils.getAssertMethod( classTestCase12Childs, "test_sinkstate_enumerate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sinkstate_enumerate119, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main120;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main120 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
	