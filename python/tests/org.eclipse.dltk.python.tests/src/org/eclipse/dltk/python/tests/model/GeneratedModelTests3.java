
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

public class GeneratedModelTests3 extends AbstractModelTests
{
	public GeneratedModelTests3(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests3.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_3", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests3" );
	}
	public void testModelGen150( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cgi.py"));

		assertNotNull("Module test_cgi.py not found", module);
		assertEquals("test_cgi.py", module.getElementName());
		
		//Class test
		{
		IType classHackedSysModule0;
			IModelElement[] moduleChilds = module.getChildren();
			classHackedSysModule0 = ModelTestUtils.getAssertClass( moduleChilds, "HackedSysModule" );
			{
				IModelElement[] classHackedSysModule0Childs = classHackedSysModule0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHackedSysModule0Childs, "argv");
			}
			{
				IModelElement[] classHackedSysModule0Childs = classHackedSysModule0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHackedSysModule0Childs, "stdin");
			}
		}
		//Class test
		{
		IType classComparableException1;
			IModelElement[] moduleChilds = module.getChildren();
			classComparableException1 = ModelTestUtils.getAssertClass( moduleChilds, "ComparableException" );
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classComparableException1Childs = classComparableException1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classComparableException1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "err"} );
			}
			//Function test:__str__
			{
			IMethod method__str__3;
				IModelElement[] classComparableException1Childs = classComparableException1.getChildren();
				method__str__3 = ModelTestUtils.getAssertMethod( classComparableException1Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__3, new String[] {"self"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__4;
				IModelElement[] classComparableException1Childs = classComparableException1.getChildren();
				method__cmp__4 = ModelTestUtils.getAssertMethod( classComparableException1Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__4, new String[] {"self", "anExc"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__5;
				IModelElement[] classComparableException1Childs = classComparableException1.getChildren();
				method__getattr__5 = ModelTestUtils.getAssertMethod( classComparableException1Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__5, new String[] {"self", "attr"} );
			}
		}
		//Function test:do_test
		{
		IMethod methoddo_test6;
			IModelElement[] moduleChilds = module.getChildren();
			methoddo_test6 = ModelTestUtils.getAssertMethod( moduleChilds, "do_test", 2 );
			ModelTestUtils.assertParameterNames( methoddo_test6, new String[] {"buf", "method"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parse_qsl_test_cases");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parse_strict_test_cases");
		}
		//Function test:norm
		{
		IMethod methodnorm7;
			IModelElement[] moduleChilds = module.getChildren();
			methodnorm7 = ModelTestUtils.getAssertMethod( moduleChilds, "norm", 1 );
			ModelTestUtils.assertParameterNames( methodnorm7, new String[] {"list"} );
		}
		//Function test:first_elts
		{
		IMethod methodfirst_elts8;
			IModelElement[] moduleChilds = module.getChildren();
			methodfirst_elts8 = ModelTestUtils.getAssertMethod( moduleChilds, "first_elts", 1 );
			ModelTestUtils.assertParameterNames( methodfirst_elts8, new String[] {"list"} );
		}
		//Function test:first_second_elts
		{
		IMethod methodfirst_second_elts9;
			IModelElement[] moduleChilds = module.getChildren();
			methodfirst_second_elts9 = ModelTestUtils.getAssertMethod( moduleChilds, "first_second_elts", 1 );
			ModelTestUtils.assertParameterNames( methodfirst_second_elts9, new String[] {"list"} );
		}
		//Function test:main
		{
		IMethod methodmain10;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain10 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen151( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bz2.py"));

		assertNotNull("Module test_bz2.py not found", module);
		assertEquals("test_bz2.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_cmdline_bunzip2");
		}
		//Class test
		{
		IType classBaseTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseTest0 = ModelTestUtils.getAssertClass( moduleChilds, "BaseTest" );
			{
				IModelElement[] classBaseTest0Childs = classBaseTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest0Childs, "TEXT");
			}
			{
				IModelElement[] classBaseTest0Childs = classBaseTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest0Childs, "DATA");
			}
			{
				IModelElement[] classBaseTest0Childs = classBaseTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest0Childs, "DATA_CRLF");
			}
			//Function test:decompress
			{
			IMethod methoddecompress1;
				IModelElement[] classBaseTest0Childs = classBaseTest0.getChildren();
				methoddecompress1 = ModelTestUtils.getAssertMethod( classBaseTest0Childs, "decompress", 2 );
				ModelTestUtils.assertParameterNames( methoddecompress1, new String[] {"self", "data"} );
			}
			//Function test:decompress
			{
			IMethod methoddecompress2;
				IModelElement[] classBaseTest0Childs = classBaseTest0.getChildren();
				methoddecompress2 = ModelTestUtils.getAssertMethod( classBaseTest0Childs, "decompress", 2 );
				ModelTestUtils.assertParameterNames( methoddecompress2, new String[] {"self", "data"} );
			}
		}
		//Class test
		{
		IType classBZ2FileTest3;
			IModelElement[] moduleChilds = module.getChildren();
			classBZ2FileTest3 = ModelTestUtils.getAssertClass( moduleChilds, "BZ2FileTest" );
			//Function test:setUp
			{
			IMethod methodsetUp4;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodsetUp4 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp4, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown5;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtearDown5 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown5, new String[] {"self"} );
			}
			//Function test:createTempFile
			{
			IMethod methodcreateTempFile6;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodcreateTempFile6 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "createTempFile", 2 );
				ModelTestUtils.assertParameterNames( methodcreateTempFile6, new String[] {"self", "crlf"} );
			}
			//Function test:testRead
			{
			IMethod methodtestRead7;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestRead7 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testRead", 1 );
				ModelTestUtils.assertParameterNames( methodtestRead7, new String[] {"self"} );
			}
			//Function test:testReadChunk10
			{
			IMethod methodtestReadChunk108;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestReadChunk108 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testReadChunk10", 1 );
				ModelTestUtils.assertParameterNames( methodtestReadChunk108, new String[] {"self"} );
			}
			//Function test:testRead100
			{
			IMethod methodtestRead1009;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestRead1009 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testRead100", 1 );
				ModelTestUtils.assertParameterNames( methodtestRead1009, new String[] {"self"} );
			}
			//Function test:testReadLine
			{
			IMethod methodtestReadLine10;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestReadLine10 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testReadLine", 1 );
				ModelTestUtils.assertParameterNames( methodtestReadLine10, new String[] {"self"} );
			}
			//Function test:testReadLines
			{
			IMethod methodtestReadLines11;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestReadLines11 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testReadLines", 1 );
				ModelTestUtils.assertParameterNames( methodtestReadLines11, new String[] {"self"} );
			}
			//Function test:testIterator
			{
			IMethod methodtestIterator12;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestIterator12 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testIterator", 1 );
				ModelTestUtils.assertParameterNames( methodtestIterator12, new String[] {"self"} );
			}
			//Function test:testXReadLines
			{
			IMethod methodtestXReadLines13;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestXReadLines13 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testXReadLines", 1 );
				ModelTestUtils.assertParameterNames( methodtestXReadLines13, new String[] {"self"} );
			}
			//Function test:testUniversalNewlinesLF
			{
			IMethod methodtestUniversalNewlinesLF14;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestUniversalNewlinesLF14 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testUniversalNewlinesLF", 1 );
				ModelTestUtils.assertParameterNames( methodtestUniversalNewlinesLF14, new String[] {"self"} );
			}
			//Function test:testUniversalNewlinesCRLF
			{
			IMethod methodtestUniversalNewlinesCRLF15;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestUniversalNewlinesCRLF15 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testUniversalNewlinesCRLF", 1 );
				ModelTestUtils.assertParameterNames( methodtestUniversalNewlinesCRLF15, new String[] {"self"} );
			}
			//Function test:testWrite
			{
			IMethod methodtestWrite16;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestWrite16 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testWrite", 1 );
				ModelTestUtils.assertParameterNames( methodtestWrite16, new String[] {"self"} );
			}
			//Function test:testWriteChunks10
			{
			IMethod methodtestWriteChunks1017;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestWriteChunks1017 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testWriteChunks10", 1 );
				ModelTestUtils.assertParameterNames( methodtestWriteChunks1017, new String[] {"self"} );
			}
			//Function test:testWriteLines
			{
			IMethod methodtestWriteLines18;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestWriteLines18 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testWriteLines", 1 );
				ModelTestUtils.assertParameterNames( methodtestWriteLines18, new String[] {"self"} );
			}
			//Function test:testSeekForward
			{
			IMethod methodtestSeekForward19;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestSeekForward19 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testSeekForward", 1 );
				ModelTestUtils.assertParameterNames( methodtestSeekForward19, new String[] {"self"} );
			}
			//Function test:testSeekBackwards
			{
			IMethod methodtestSeekBackwards20;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestSeekBackwards20 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testSeekBackwards", 1 );
				ModelTestUtils.assertParameterNames( methodtestSeekBackwards20, new String[] {"self"} );
			}
			//Function test:testSeekBackwardsFromEnd
			{
			IMethod methodtestSeekBackwardsFromEnd21;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestSeekBackwardsFromEnd21 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testSeekBackwardsFromEnd", 1 );
				ModelTestUtils.assertParameterNames( methodtestSeekBackwardsFromEnd21, new String[] {"self"} );
			}
			//Function test:testSeekPostEnd
			{
			IMethod methodtestSeekPostEnd22;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestSeekPostEnd22 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testSeekPostEnd", 1 );
				ModelTestUtils.assertParameterNames( methodtestSeekPostEnd22, new String[] {"self"} );
			}
			//Function test:testSeekPostEndTwice
			{
			IMethod methodtestSeekPostEndTwice23;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestSeekPostEndTwice23 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testSeekPostEndTwice", 1 );
				ModelTestUtils.assertParameterNames( methodtestSeekPostEndTwice23, new String[] {"self"} );
			}
			//Function test:testSeekPreStart
			{
			IMethod methodtestSeekPreStart24;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestSeekPreStart24 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testSeekPreStart", 1 );
				ModelTestUtils.assertParameterNames( methodtestSeekPreStart24, new String[] {"self"} );
			}
			//Function test:testOpenDel
			{
			IMethod methodtestOpenDel25;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestOpenDel25 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testOpenDel", 1 );
				ModelTestUtils.assertParameterNames( methodtestOpenDel25, new String[] {"self"} );
			}
			//Function test:testOpenNonexistent
			{
			IMethod methodtestOpenNonexistent26;
				IModelElement[] classBZ2FileTest3Childs = classBZ2FileTest3.getChildren();
				methodtestOpenNonexistent26 = ModelTestUtils.getAssertMethod( classBZ2FileTest3Childs, "testOpenNonexistent", 1 );
				ModelTestUtils.assertParameterNames( methodtestOpenNonexistent26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBZ2CompressorTest27;
			IModelElement[] moduleChilds = module.getChildren();
			classBZ2CompressorTest27 = ModelTestUtils.getAssertClass( moduleChilds, "BZ2CompressorTest" );
			//Function test:testCompress
			{
			IMethod methodtestCompress28;
				IModelElement[] classBZ2CompressorTest27Childs = classBZ2CompressorTest27.getChildren();
				methodtestCompress28 = ModelTestUtils.getAssertMethod( classBZ2CompressorTest27Childs, "testCompress", 1 );
				ModelTestUtils.assertParameterNames( methodtestCompress28, new String[] {"self"} );
			}
			//Function test:testCompressChunks10
			{
			IMethod methodtestCompressChunks1029;
				IModelElement[] classBZ2CompressorTest27Childs = classBZ2CompressorTest27.getChildren();
				methodtestCompressChunks1029 = ModelTestUtils.getAssertMethod( classBZ2CompressorTest27Childs, "testCompressChunks10", 1 );
				ModelTestUtils.assertParameterNames( methodtestCompressChunks1029, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBZ2DecompressorTest30;
			IModelElement[] moduleChilds = module.getChildren();
			classBZ2DecompressorTest30 = ModelTestUtils.getAssertClass( moduleChilds, "BZ2DecompressorTest" );
			//Function test:test_Constructor
			{
			IMethod methodtest_Constructor31;
				IModelElement[] classBZ2DecompressorTest30Childs = classBZ2DecompressorTest30.getChildren();
				methodtest_Constructor31 = ModelTestUtils.getAssertMethod( classBZ2DecompressorTest30Childs, "test_Constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_Constructor31, new String[] {"self"} );
			}
			//Function test:testDecompress
			{
			IMethod methodtestDecompress32;
				IModelElement[] classBZ2DecompressorTest30Childs = classBZ2DecompressorTest30.getChildren();
				methodtestDecompress32 = ModelTestUtils.getAssertMethod( classBZ2DecompressorTest30Childs, "testDecompress", 1 );
				ModelTestUtils.assertParameterNames( methodtestDecompress32, new String[] {"self"} );
			}
			//Function test:testDecompressChunks10
			{
			IMethod methodtestDecompressChunks1033;
				IModelElement[] classBZ2DecompressorTest30Childs = classBZ2DecompressorTest30.getChildren();
				methodtestDecompressChunks1033 = ModelTestUtils.getAssertMethod( classBZ2DecompressorTest30Childs, "testDecompressChunks10", 1 );
				ModelTestUtils.assertParameterNames( methodtestDecompressChunks1033, new String[] {"self"} );
			}
			//Function test:testDecompressUnusedData
			{
			IMethod methodtestDecompressUnusedData34;
				IModelElement[] classBZ2DecompressorTest30Childs = classBZ2DecompressorTest30.getChildren();
				methodtestDecompressUnusedData34 = ModelTestUtils.getAssertMethod( classBZ2DecompressorTest30Childs, "testDecompressUnusedData", 1 );
				ModelTestUtils.assertParameterNames( methodtestDecompressUnusedData34, new String[] {"self"} );
			}
			//Function test:testEOFError
			{
			IMethod methodtestEOFError35;
				IModelElement[] classBZ2DecompressorTest30Childs = classBZ2DecompressorTest30.getChildren();
				methodtestEOFError35 = ModelTestUtils.getAssertMethod( classBZ2DecompressorTest30Childs, "testEOFError", 1 );
				ModelTestUtils.assertParameterNames( methodtestEOFError35, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFuncTest36;
			IModelElement[] moduleChilds = module.getChildren();
			classFuncTest36 = ModelTestUtils.getAssertClass( moduleChilds, "FuncTest" );
			//Function test:testCompress
			{
			IMethod methodtestCompress37;
				IModelElement[] classFuncTest36Childs = classFuncTest36.getChildren();
				methodtestCompress37 = ModelTestUtils.getAssertMethod( classFuncTest36Childs, "testCompress", 1 );
				ModelTestUtils.assertParameterNames( methodtestCompress37, new String[] {"self"} );
			}
			//Function test:testDecompress
			{
			IMethod methodtestDecompress38;
				IModelElement[] classFuncTest36Childs = classFuncTest36.getChildren();
				methodtestDecompress38 = ModelTestUtils.getAssertMethod( classFuncTest36Childs, "testDecompress", 1 );
				ModelTestUtils.assertParameterNames( methodtestDecompress38, new String[] {"self"} );
			}
			//Function test:testDecompressEmpty
			{
			IMethod methodtestDecompressEmpty39;
				IModelElement[] classFuncTest36Childs = classFuncTest36.getChildren();
				methodtestDecompressEmpty39 = ModelTestUtils.getAssertMethod( classFuncTest36Childs, "testDecompressEmpty", 1 );
				ModelTestUtils.assertParameterNames( methodtestDecompressEmpty39, new String[] {"self"} );
			}
			//Function test:testDecompressIncomplete
			{
			IMethod methodtestDecompressIncomplete40;
				IModelElement[] classFuncTest36Childs = classFuncTest36.getChildren();
				methodtestDecompressIncomplete40 = ModelTestUtils.getAssertMethod( classFuncTest36Childs, "testDecompressIncomplete", 1 );
				ModelTestUtils.assertParameterNames( methodtestDecompressIncomplete40, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main41 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen152( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_getopt.py"));

		assertNotNull("Module test_getopt.py not found", module);
		assertEquals("test_getopt.py", module.getElementName());
		
		//Function test:expectException
		{
		IMethod methodexpectException0;
			IModelElement[] moduleChilds = module.getChildren();
			methodexpectException0 = ModelTestUtils.getAssertMethod( moduleChilds, "expectException", 3 );
			ModelTestUtils.assertParameterNames( methodexpectException0, new String[] {"teststr", "expected", "failure"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "old_posixly_correct");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cmdline");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cmdline");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "libreftest");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}

	}
	public void testModelGen153( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dbm.py"));

		assertNotNull("Module test_dbm.py not found", module);
		assertEquals("test_dbm.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "filename");
		}
		//Function test:cleanup
		{
		IMethod methodcleanup0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcleanup0 = ModelTestUtils.getAssertMethod( moduleChilds, "cleanup", 0 );
		}
		//Function test:test_keys
		{
		IMethod methodtest_keys1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_keys1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_keys", 0 );
		}
		//Function test:test_modes
		{
		IMethod methodtest_modes2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_modes2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_modes", 0 );
		}

	}
	public void testModelGen154( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_mimetools.py"));

		assertNotNull("Module test_mimetools.py not found", module);
		assertEquals("test_mimetools.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msgtext1");
		}
		//Class test
		{
		IType classMimeToolsTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classMimeToolsTest0 = ModelTestUtils.getAssertClass( moduleChilds, "MimeToolsTest" );
			//Function test:test_decodeencode
			{
			IMethod methodtest_decodeencode1;
				IModelElement[] classMimeToolsTest0Childs = classMimeToolsTest0.getChildren();
				methodtest_decodeencode1 = ModelTestUtils.getAssertMethod( classMimeToolsTest0Childs, "test_decodeencode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodeencode1, new String[] {"self"} );
			}
			//Function test:test_boundary
			{
			IMethod methodtest_boundary2;
				IModelElement[] classMimeToolsTest0Childs = classMimeToolsTest0.getChildren();
				methodtest_boundary2 = ModelTestUtils.getAssertMethod( classMimeToolsTest0Childs, "test_boundary", 1 );
				ModelTestUtils.assertParameterNames( methodtest_boundary2, new String[] {"self"} );
			}
			//Function test:test_message
			{
			IMethod methodtest_message3;
				IModelElement[] classMimeToolsTest0Childs = classMimeToolsTest0.getChildren();
				methodtest_message3 = ModelTestUtils.getAssertMethod( classMimeToolsTest0Childs, "test_message", 1 );
				ModelTestUtils.assertParameterNames( methodtest_message3, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen155( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_largefile.py"));

		assertNotNull("Module test_largefile.py not found", module);
		assertEquals("test_largefile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "oldhandler");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "size");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "name");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		//Function test:expect
		{
		IMethod methodexpect0;
			IModelElement[] moduleChilds = module.getChildren();
			methodexpect0 = ModelTestUtils.getAssertMethod( moduleChilds, "expect", 2 );
			ModelTestUtils.assertParameterNames( methodexpect0, new String[] {"got_this", "expect_this"} );
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "newsize");
		}

	}
	public void testModelGen156( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_urllibnet.py"));

		assertNotNull("Module test_urllibnet.py not found", module);
		assertEquals("test_urllibnet.py", module.getElementName());
		
		//Class test
		{
		IType classURLTimeoutTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classURLTimeoutTest0 = ModelTestUtils.getAssertClass( moduleChilds, "URLTimeoutTest" );
			{
				IModelElement[] classURLTimeoutTest0Childs = classURLTimeoutTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classURLTimeoutTest0Childs, "TIMEOUT");
			}
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classURLTimeoutTest0Childs = classURLTimeoutTest0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classURLTimeoutTest0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classURLTimeoutTest0Childs = classURLTimeoutTest0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classURLTimeoutTest0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:testURLread
			{
			IMethod methodtestURLread3;
				IModelElement[] classURLTimeoutTest0Childs = classURLTimeoutTest0.getChildren();
				methodtestURLread3 = ModelTestUtils.getAssertMethod( classURLTimeoutTest0Childs, "testURLread", 1 );
				ModelTestUtils.assertParameterNames( methodtestURLread3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlopenNetworkTests4;
			IModelElement[] moduleChilds = module.getChildren();
			classurlopenNetworkTests4 = ModelTestUtils.getAssertClass( moduleChilds, "urlopenNetworkTests" );
			//Function test:test_basic
			{
			IMethod methodtest_basic5;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_basic5 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic5, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines6;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_readlines6 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines6, new String[] {"self"} );
			}
			//Function test:test_info
			{
			IMethod methodtest_info7;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_info7 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_info7, new String[] {"self"} );
			}
			//Function test:test_geturl
			{
			IMethod methodtest_geturl8;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_geturl8 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_geturl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_geturl8, new String[] {"self"} );
			}
			//Function test:test_fileno
			{
			IMethod methodtest_fileno9;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_fileno9 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_fileno", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fileno9, new String[] {"self"} );
			}
			//Function test:test_bad_address
			{
			IMethod methodtest_bad_address10;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_bad_address10 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_bad_address", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_address10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlretrieveNetworkTests11;
			IModelElement[] moduleChilds = module.getChildren();
			classurlretrieveNetworkTests11 = ModelTestUtils.getAssertClass( moduleChilds, "urlretrieveNetworkTests" );
			//Function test:test_basic
			{
			IMethod methodtest_basic12;
				IModelElement[] classurlretrieveNetworkTests11Childs = classurlretrieveNetworkTests11.getChildren();
				methodtest_basic12 = ModelTestUtils.getAssertMethod( classurlretrieveNetworkTests11Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic12, new String[] {"self"} );
			}
			//Function test:test_specified_path
			{
			IMethod methodtest_specified_path13;
				IModelElement[] classurlretrieveNetworkTests11Childs = classurlretrieveNetworkTests11.getChildren();
				methodtest_specified_path13 = ModelTestUtils.getAssertMethod( classurlretrieveNetworkTests11Childs, "test_specified_path", 1 );
				ModelTestUtils.assertParameterNames( methodtest_specified_path13, new String[] {"self"} );
			}
			//Function test:test_header
			{
			IMethod methodtest_header14;
				IModelElement[] classurlretrieveNetworkTests11Childs = classurlretrieveNetworkTests11.getChildren();
				methodtest_header14 = ModelTestUtils.getAssertMethod( classurlretrieveNetworkTests11Childs, "test_header", 1 );
				ModelTestUtils.assertParameterNames( methodtest_header14, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen157( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_operations.py"));

		assertNotNull("Module test_operations.py not found", module);
		assertEquals("test_operations.py", module.getElementName());
		
		//Class test
		{
		IType classBadDictKey0;
			IModelElement[] moduleChilds = module.getChildren();
			classBadDictKey0 = ModelTestUtils.getAssertClass( moduleChilds, "BadDictKey" );
			{
				IModelElement[] classBadDictKey0Childs = classBadDictKey0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBadDictKey0Childs, "already_printed_raising_error");
			}
			//Function test:__hash__
			{
			IMethod method__hash__1;
				IModelElement[] classBadDictKey0Childs = classBadDictKey0.getChildren();
				method__hash__1 = ModelTestUtils.getAssertMethod( classBadDictKey0Childs, "__hash__", 1 );
				ModelTestUtils.assertParameterNames( method__hash__1, new String[] {"self"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__2;
				IModelElement[] classBadDictKey0Childs = classBadDictKey0.getChildren();
				method__cmp__2 = ModelTestUtils.getAssertMethod( classBadDictKey0Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__2, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}

	}
	public void testModelGen158( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_csv.py"));

		assertNotNull("Module test_csv.py not found", module);
		assertEquals("test_csv.py", module.getElementName());
		
		//Class test
		{
		IType classTest_Csv0;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_Csv0 = ModelTestUtils.getAssertClass( moduleChilds, "Test_Csv" );
			//Function test:test_reader_arg_valid
			{
			IMethod methodtest_reader_arg_valid1;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_reader_arg_valid1 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_reader_arg_valid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reader_arg_valid1, new String[] {"self"} );
				//Class test
				{
				IType classBadClass2;
					IModelElement[] methodtest_reader_arg_valid1Childs = methodtest_reader_arg_valid1.getChildren();
					classBadClass2 = ModelTestUtils.getAssertClass( methodtest_reader_arg_valid1Childs, "BadClass" );
					//Function test:__init__
					{
					IMethod method__init__3;
						IModelElement[] classBadClass2Childs = classBadClass2.getChildren();
						method__init__3 = ModelTestUtils.getAssertMethod( classBadClass2Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBadDialect4;
					IModelElement[] methodtest_reader_arg_valid1Childs = methodtest_reader_arg_valid1.getChildren();
					classBadDialect4 = ModelTestUtils.getAssertClass( methodtest_reader_arg_valid1Childs, "BadDialect" );
				}
			}
			//Function test:test_writer_arg_valid
			{
			IMethod methodtest_writer_arg_valid5;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_writer_arg_valid5 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_writer_arg_valid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writer_arg_valid5, new String[] {"self"} );
			}
			//Function test:_test_attrs
			{
			IMethod method_test_attrs6;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				method_test_attrs6 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "_test_attrs", 2 );
				ModelTestUtils.assertParameterNames( method_test_attrs6, new String[] {"self", "obj"} );
			}
			//Function test:test_reader_attrs
			{
			IMethod methodtest_reader_attrs7;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_reader_attrs7 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_reader_attrs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reader_attrs7, new String[] {"self"} );
			}
			//Function test:test_writer_attrs
			{
			IMethod methodtest_writer_attrs8;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_writer_attrs8 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_writer_attrs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writer_attrs8, new String[] {"self"} );
			}
			//Function test:_write_test
			{
			IMethod method_write_test9;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				method_write_test9 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "_write_test", 4 );
				ModelTestUtils.assertParameterNames( method_write_test9, new String[] {"self", "fields", "expect", "kwargs"} );
			}
			//Function test:test_write_arg_valid
			{
			IMethod methodtest_write_arg_valid10;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_write_arg_valid10 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_write_arg_valid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_arg_valid10, new String[] {"self"} );
				//Class test
				{
				IType classBadList11;
					IModelElement[] methodtest_write_arg_valid10Childs = methodtest_write_arg_valid10.getChildren();
					classBadList11 = ModelTestUtils.getAssertClass( methodtest_write_arg_valid10Childs, "BadList" );
					//Function test:__len__
					{
					IMethod method__len__12;
						IModelElement[] classBadList11Childs = classBadList11.getChildren();
						method__len__12 = ModelTestUtils.getAssertMethod( classBadList11Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__12, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__13;
						IModelElement[] classBadList11Childs = classBadList11.getChildren();
						method__getitem__13 = ModelTestUtils.getAssertMethod( classBadList11Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__13, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classBadItem14;
					IModelElement[] methodtest_write_arg_valid10Childs = methodtest_write_arg_valid10.getChildren();
					classBadItem14 = ModelTestUtils.getAssertClass( methodtest_write_arg_valid10Childs, "BadItem" );
					//Function test:__str__
					{
					IMethod method__str__15;
						IModelElement[] classBadItem14Childs = classBadItem14.getChildren();
						method__str__15 = ModelTestUtils.getAssertMethod( classBadItem14Childs, "__str__", 1 );
						ModelTestUtils.assertParameterNames( method__str__15, new String[] {"self"} );
					}
				}
			}
			//Function test:test_write_bigfield
			{
			IMethod methodtest_write_bigfield16;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_write_bigfield16 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_write_bigfield", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_bigfield16, new String[] {"self"} );
			}
			//Function test:test_write_quoting
			{
			IMethod methodtest_write_quoting17;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_write_quoting17 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_write_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_quoting17, new String[] {"self"} );
			}
			//Function test:test_write_escape
			{
			IMethod methodtest_write_escape18;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_write_escape18 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_write_escape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_escape18, new String[] {"self"} );
			}
			//Function test:test_writerows
			{
			IMethod methodtest_writerows19;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_writerows19 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_writerows", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writerows19, new String[] {"self"} );
				//Class test
				{
				IType classBrokenFile20;
					IModelElement[] methodtest_writerows19Childs = methodtest_writerows19.getChildren();
					classBrokenFile20 = ModelTestUtils.getAssertClass( methodtest_writerows19Childs, "BrokenFile" );
					//Function test:write
					{
					IMethod methodwrite21;
						IModelElement[] classBrokenFile20Childs = classBrokenFile20.getChildren();
						methodwrite21 = ModelTestUtils.getAssertMethod( classBrokenFile20Childs, "write", 2 );
						ModelTestUtils.assertParameterNames( methodwrite21, new String[] {"self", "buf"} );
					}
				}
			}
			//Function test:_read_test
			{
			IMethod method_read_test22;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				method_read_test22 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "_read_test", 4 );
				ModelTestUtils.assertParameterNames( method_read_test22, new String[] {"self", "input", "expect", "kwargs"} );
			}
			//Function test:test_read_oddinputs
			{
			IMethod methodtest_read_oddinputs23;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_read_oddinputs23 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_read_oddinputs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_oddinputs23, new String[] {"self"} );
			}
			//Function test:test_read_eol
			{
			IMethod methodtest_read_eol24;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_read_eol24 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_read_eol", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_eol24, new String[] {"self"} );
			}
			//Function test:test_read_escape
			{
			IMethod methodtest_read_escape25;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_read_escape25 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_read_escape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_escape25, new String[] {"self"} );
			}
			//Function test:test_read_bigfield
			{
			IMethod methodtest_read_bigfield26;
				IModelElement[] classTest_Csv0Childs = classTest_Csv0.getChildren();
				methodtest_read_bigfield26 = ModelTestUtils.getAssertMethod( classTest_Csv0Childs, "test_read_bigfield", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_bigfield26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDialectRegistry27;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDialectRegistry27 = ModelTestUtils.getAssertClass( moduleChilds, "TestDialectRegistry" );
			//Function test:test_registry_badargs
			{
			IMethod methodtest_registry_badargs28;
				IModelElement[] classTestDialectRegistry27Childs = classTestDialectRegistry27.getChildren();
				methodtest_registry_badargs28 = ModelTestUtils.getAssertMethod( classTestDialectRegistry27Childs, "test_registry_badargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_registry_badargs28, new String[] {"self"} );
				//Class test
				{
				IType classbogus29;
					IModelElement[] methodtest_registry_badargs28Childs = methodtest_registry_badargs28.getChildren();
					classbogus29 = ModelTestUtils.getAssertClass( methodtest_registry_badargs28Childs, "bogus" );
					//Function test:__init__
					{
					IMethod method__init__30;
						IModelElement[] classbogus29Childs = classbogus29.getChildren();
						method__init__30 = ModelTestUtils.getAssertMethod( classbogus29Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__30, new String[] {"self"} );
					}
				}
			}
			//Function test:test_registry
			{
			IMethod methodtest_registry31;
				IModelElement[] classTestDialectRegistry27Childs = classTestDialectRegistry27.getChildren();
				methodtest_registry31 = ModelTestUtils.getAssertMethod( classTestDialectRegistry27Childs, "test_registry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_registry31, new String[] {"self"} );
				//Class test
				{
				IType classmyexceltsv32;
					IModelElement[] methodtest_registry31Childs = methodtest_registry31.getChildren();
					classmyexceltsv32 = ModelTestUtils.getAssertClass( methodtest_registry31Childs, "myexceltsv" );
				}
			}
			//Function test:test_incomplete_dialect
			{
			IMethod methodtest_incomplete_dialect33;
				IModelElement[] classTestDialectRegistry27Childs = classTestDialectRegistry27.getChildren();
				methodtest_incomplete_dialect33 = ModelTestUtils.getAssertMethod( classTestDialectRegistry27Childs, "test_incomplete_dialect", 1 );
				ModelTestUtils.assertParameterNames( methodtest_incomplete_dialect33, new String[] {"self"} );
				//Class test
				{
				IType classmyexceltsv34;
					IModelElement[] methodtest_incomplete_dialect33Childs = methodtest_incomplete_dialect33.getChildren();
					classmyexceltsv34 = ModelTestUtils.getAssertClass( methodtest_incomplete_dialect33Childs, "myexceltsv" );
				}
			}
			//Function test:test_space_dialect
			{
			IMethod methodtest_space_dialect35;
				IModelElement[] classTestDialectRegistry27Childs = classTestDialectRegistry27.getChildren();
				methodtest_space_dialect35 = ModelTestUtils.getAssertMethod( classTestDialectRegistry27Childs, "test_space_dialect", 1 );
				ModelTestUtils.assertParameterNames( methodtest_space_dialect35, new String[] {"self"} );
				//Class test
				{
				IType classspace36;
					IModelElement[] methodtest_space_dialect35Childs = methodtest_space_dialect35.getChildren();
					classspace36 = ModelTestUtils.getAssertClass( methodtest_space_dialect35Childs, "space" );
				}
			}
			//Function test:test_dialect_apply
			{
			IMethod methodtest_dialect_apply37;
				IModelElement[] classTestDialectRegistry27Childs = classTestDialectRegistry27.getChildren();
				methodtest_dialect_apply37 = ModelTestUtils.getAssertMethod( classTestDialectRegistry27Childs, "test_dialect_apply", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dialect_apply37, new String[] {"self"} );
				//Class test
				{
				IType classtestA38;
					IModelElement[] methodtest_dialect_apply37Childs = methodtest_dialect_apply37.getChildren();
					classtestA38 = ModelTestUtils.getAssertClass( methodtest_dialect_apply37Childs, "testA" );
				}
				//Class test
				{
				IType classtestB39;
					IModelElement[] methodtest_dialect_apply37Childs = methodtest_dialect_apply37.getChildren();
					classtestB39 = ModelTestUtils.getAssertClass( methodtest_dialect_apply37Childs, "testB" );
				}
				//Class test
				{
				IType classtestC40;
					IModelElement[] methodtest_dialect_apply37Childs = methodtest_dialect_apply37.getChildren();
					classtestC40 = ModelTestUtils.getAssertClass( methodtest_dialect_apply37Childs, "testC" );
				}
			}
			//Function test:test_bad_dialect
			{
			IMethod methodtest_bad_dialect41;
				IModelElement[] classTestDialectRegistry27Childs = classTestDialectRegistry27.getChildren();
				methodtest_bad_dialect41 = ModelTestUtils.getAssertMethod( classTestDialectRegistry27Childs, "test_bad_dialect", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_dialect41, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestCsvBase42;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCsvBase42 = ModelTestUtils.getAssertClass( moduleChilds, "TestCsvBase" );
			//Function test:readerAssertEqual
			{
			IMethod methodreaderAssertEqual43;
				IModelElement[] classTestCsvBase42Childs = classTestCsvBase42.getChildren();
				methodreaderAssertEqual43 = ModelTestUtils.getAssertMethod( classTestCsvBase42Childs, "readerAssertEqual", 3 );
				ModelTestUtils.assertParameterNames( methodreaderAssertEqual43, new String[] {"self", "input", "expected_result"} );
			}
			//Function test:writerAssertEqual
			{
			IMethod methodwriterAssertEqual44;
				IModelElement[] classTestCsvBase42Childs = classTestCsvBase42.getChildren();
				methodwriterAssertEqual44 = ModelTestUtils.getAssertMethod( classTestCsvBase42Childs, "writerAssertEqual", 3 );
				ModelTestUtils.assertParameterNames( methodwriterAssertEqual44, new String[] {"self", "input", "expected_result"} );
			}
		}
		//Class test
		{
		IType classTestDialectExcel45;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDialectExcel45 = ModelTestUtils.getAssertClass( moduleChilds, "TestDialectExcel" );
			{
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDialectExcel45Childs, "dialect");
			}
			//Function test:test_single
			{
			IMethod methodtest_single46;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_single46 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_single", 1 );
				ModelTestUtils.assertParameterNames( methodtest_single46, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple47;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_simple47 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple47, new String[] {"self"} );
			}
			//Function test:test_blankline
			{
			IMethod methodtest_blankline48;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_blankline48 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_blankline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_blankline48, new String[] {"self"} );
			}
			//Function test:test_empty_fields
			{
			IMethod methodtest_empty_fields49;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_empty_fields49 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_empty_fields", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_fields49, new String[] {"self"} );
			}
			//Function test:test_singlequoted
			{
			IMethod methodtest_singlequoted50;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_singlequoted50 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_singlequoted", 1 );
				ModelTestUtils.assertParameterNames( methodtest_singlequoted50, new String[] {"self"} );
			}
			//Function test:test_singlequoted_left_empty
			{
			IMethod methodtest_singlequoted_left_empty51;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_singlequoted_left_empty51 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_singlequoted_left_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_singlequoted_left_empty51, new String[] {"self"} );
			}
			//Function test:test_singlequoted_right_empty
			{
			IMethod methodtest_singlequoted_right_empty52;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_singlequoted_right_empty52 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_singlequoted_right_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_singlequoted_right_empty52, new String[] {"self"} );
			}
			//Function test:test_single_quoted_quote
			{
			IMethod methodtest_single_quoted_quote53;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_single_quoted_quote53 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_single_quoted_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_single_quoted_quote53, new String[] {"self"} );
			}
			//Function test:test_quoted_quotes
			{
			IMethod methodtest_quoted_quotes54;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quoted_quotes54 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quoted_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoted_quotes54, new String[] {"self"} );
			}
			//Function test:test_inline_quote
			{
			IMethod methodtest_inline_quote55;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_inline_quote55 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_inline_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inline_quote55, new String[] {"self"} );
			}
			//Function test:test_inline_quotes
			{
			IMethod methodtest_inline_quotes56;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_inline_quotes56 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_inline_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_inline_quotes56, new String[] {"self"} );
			}
			//Function test:test_quotes_and_more
			{
			IMethod methodtest_quotes_and_more57;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quotes_and_more57 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quotes_and_more", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quotes_and_more57, new String[] {"self"} );
			}
			//Function test:test_lone_quote
			{
			IMethod methodtest_lone_quote58;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_lone_quote58 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_lone_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lone_quote58, new String[] {"self"} );
			}
			//Function test:test_quote_and_quote
			{
			IMethod methodtest_quote_and_quote59;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quote_and_quote59 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quote_and_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quote_and_quote59, new String[] {"self"} );
			}
			//Function test:test_space_and_quote
			{
			IMethod methodtest_space_and_quote60;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_space_and_quote60 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_space_and_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_space_and_quote60, new String[] {"self"} );
			}
			//Function test:test_quoted
			{
			IMethod methodtest_quoted61;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quoted61 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quoted", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoted61, new String[] {"self"} );
			}
			//Function test:test_quoted_quote
			{
			IMethod methodtest_quoted_quote62;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quoted_quote62 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quoted_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoted_quote62, new String[] {"self"} );
			}
			//Function test:test_quoted_nl
			{
			IMethod methodtest_quoted_nl63;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quoted_nl63 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quoted_nl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoted_nl63, new String[] {"self"} );
			}
			//Function test:test_dubious_quote
			{
			IMethod methodtest_dubious_quote64;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_dubious_quote64 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_dubious_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dubious_quote64, new String[] {"self"} );
			}
			//Function test:test_null
			{
			IMethod methodtest_null65;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_null65 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_null", 1 );
				ModelTestUtils.assertParameterNames( methodtest_null65, new String[] {"self"} );
			}
			//Function test:test_single
			{
			IMethod methodtest_single66;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_single66 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_single", 1 );
				ModelTestUtils.assertParameterNames( methodtest_single66, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple67;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_simple67 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple67, new String[] {"self"} );
			}
			//Function test:test_quotes
			{
			IMethod methodtest_quotes68;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quotes68 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quotes68, new String[] {"self"} );
			}
			//Function test:test_quote_fieldsep
			{
			IMethod methodtest_quote_fieldsep69;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_quote_fieldsep69 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_quote_fieldsep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quote_fieldsep69, new String[] {"self"} );
			}
			//Function test:test_newlines
			{
			IMethod methodtest_newlines70;
				IModelElement[] classTestDialectExcel45Childs = classTestDialectExcel45.getChildren();
				methodtest_newlines70 = ModelTestUtils.getAssertMethod( classTestDialectExcel45Childs, "test_newlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newlines70, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEscapedExcel71;
			IModelElement[] moduleChilds = module.getChildren();
			classEscapedExcel71 = ModelTestUtils.getAssertClass( moduleChilds, "EscapedExcel" );
			{
				IModelElement[] classEscapedExcel71Childs = classEscapedExcel71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEscapedExcel71Childs, "quoting");
			}
			{
				IModelElement[] classEscapedExcel71Childs = classEscapedExcel71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEscapedExcel71Childs, "escapechar");
			}
		}
		//Class test
		{
		IType classTestEscapedExcel72;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEscapedExcel72 = ModelTestUtils.getAssertClass( moduleChilds, "TestEscapedExcel" );
			{
				IModelElement[] classTestEscapedExcel72Childs = classTestEscapedExcel72.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestEscapedExcel72Childs, "dialect");
			}
			//Function test:test_escape_fieldsep
			{
			IMethod methodtest_escape_fieldsep73;
				IModelElement[] classTestEscapedExcel72Childs = classTestEscapedExcel72.getChildren();
				methodtest_escape_fieldsep73 = ModelTestUtils.getAssertMethod( classTestEscapedExcel72Childs, "test_escape_fieldsep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_escape_fieldsep73, new String[] {"self"} );
			}
			//Function test:test_read_escape_fieldsep
			{
			IMethod methodtest_read_escape_fieldsep74;
				IModelElement[] classTestEscapedExcel72Childs = classTestEscapedExcel72.getChildren();
				methodtest_read_escape_fieldsep74 = ModelTestUtils.getAssertMethod( classTestEscapedExcel72Childs, "test_read_escape_fieldsep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_escape_fieldsep74, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classQuotedEscapedExcel75;
			IModelElement[] moduleChilds = module.getChildren();
			classQuotedEscapedExcel75 = ModelTestUtils.getAssertClass( moduleChilds, "QuotedEscapedExcel" );
			{
				IModelElement[] classQuotedEscapedExcel75Childs = classQuotedEscapedExcel75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQuotedEscapedExcel75Childs, "quoting");
			}
			{
				IModelElement[] classQuotedEscapedExcel75Childs = classQuotedEscapedExcel75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQuotedEscapedExcel75Childs, "escapechar");
			}
		}
		//Class test
		{
		IType classTestQuotedEscapedExcel76;
			IModelElement[] moduleChilds = module.getChildren();
			classTestQuotedEscapedExcel76 = ModelTestUtils.getAssertClass( moduleChilds, "TestQuotedEscapedExcel" );
			{
				IModelElement[] classTestQuotedEscapedExcel76Childs = classTestQuotedEscapedExcel76.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestQuotedEscapedExcel76Childs, "dialect");
			}
			//Function test:test_write_escape_fieldsep
			{
			IMethod methodtest_write_escape_fieldsep77;
				IModelElement[] classTestQuotedEscapedExcel76Childs = classTestQuotedEscapedExcel76.getChildren();
				methodtest_write_escape_fieldsep77 = ModelTestUtils.getAssertMethod( classTestQuotedEscapedExcel76Childs, "test_write_escape_fieldsep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_escape_fieldsep77, new String[] {"self"} );
			}
			//Function test:test_read_escape_fieldsep
			{
			IMethod methodtest_read_escape_fieldsep78;
				IModelElement[] classTestQuotedEscapedExcel76Childs = classTestQuotedEscapedExcel76.getChildren();
				methodtest_read_escape_fieldsep78 = ModelTestUtils.getAssertMethod( classTestQuotedEscapedExcel76Childs, "test_read_escape_fieldsep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_escape_fieldsep78, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDictFields79;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictFields79 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictFields" );
			//Function test:test_write_simple_dict
			{
			IMethod methodtest_write_simple_dict80;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_write_simple_dict80 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_write_simple_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_simple_dict80, new String[] {"self"} );
			}
			//Function test:test_write_no_fields
			{
			IMethod methodtest_write_no_fields81;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_write_no_fields81 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_write_no_fields", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_no_fields81, new String[] {"self"} );
			}
			//Function test:test_read_dict_fields
			{
			IMethod methodtest_read_dict_fields82;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_dict_fields82 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_dict_fields", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_dict_fields82, new String[] {"self"} );
			}
			//Function test:test_read_dict_no_fieldnames
			{
			IMethod methodtest_read_dict_no_fieldnames83;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_dict_no_fieldnames83 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_dict_no_fieldnames", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_dict_no_fieldnames83, new String[] {"self"} );
			}
			//Function test:test_read_long
			{
			IMethod methodtest_read_long84;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_long84 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_long84, new String[] {"self"} );
			}
			//Function test:test_read_long_with_rest
			{
			IMethod methodtest_read_long_with_rest85;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_long_with_rest85 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_long_with_rest", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_long_with_rest85, new String[] {"self"} );
			}
			//Function test:test_read_long_with_rest_no_fieldnames
			{
			IMethod methodtest_read_long_with_rest_no_fieldnames86;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_long_with_rest_no_fieldnames86 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_long_with_rest_no_fieldnames", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_long_with_rest_no_fieldnames86, new String[] {"self"} );
			}
			//Function test:test_read_short
			{
			IMethod methodtest_read_short87;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_short87 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_short", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_short87, new String[] {"self"} );
			}
			//Function test:test_read_multi
			{
			IMethod methodtest_read_multi88;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_multi88 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_multi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_multi88, new String[] {"self"} );
			}
			//Function test:test_read_with_blanks
			{
			IMethod methodtest_read_with_blanks89;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_with_blanks89 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_with_blanks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_with_blanks89, new String[] {"self"} );
			}
			//Function test:test_read_semi_sep
			{
			IMethod methodtest_read_semi_sep90;
				IModelElement[] classTestDictFields79Childs = classTestDictFields79.getChildren();
				methodtest_read_semi_sep90 = ModelTestUtils.getAssertMethod( classTestDictFields79Childs, "test_read_semi_sep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read_semi_sep90, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestArrayWrites91;
			IModelElement[] moduleChilds = module.getChildren();
			classTestArrayWrites91 = ModelTestUtils.getAssertClass( moduleChilds, "TestArrayWrites" );
			//Function test:test_int_write
			{
			IMethod methodtest_int_write92;
				IModelElement[] classTestArrayWrites91Childs = classTestArrayWrites91.getChildren();
				methodtest_int_write92 = ModelTestUtils.getAssertMethod( classTestArrayWrites91Childs, "test_int_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int_write92, new String[] {"self"} );
			}
			//Function test:test_double_write
			{
			IMethod methodtest_double_write93;
				IModelElement[] classTestArrayWrites91Childs = classTestArrayWrites91.getChildren();
				methodtest_double_write93 = ModelTestUtils.getAssertMethod( classTestArrayWrites91Childs, "test_double_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_double_write93, new String[] {"self"} );
			}
			//Function test:test_float_write
			{
			IMethod methodtest_float_write94;
				IModelElement[] classTestArrayWrites91Childs = classTestArrayWrites91.getChildren();
				methodtest_float_write94 = ModelTestUtils.getAssertMethod( classTestArrayWrites91Childs, "test_float_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float_write94, new String[] {"self"} );
			}
			//Function test:test_char_write
			{
			IMethod methodtest_char_write95;
				IModelElement[] classTestArrayWrites91Childs = classTestArrayWrites91.getChildren();
				methodtest_char_write95 = ModelTestUtils.getAssertMethod( classTestArrayWrites91Childs, "test_char_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_char_write95, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDialectValidity96;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDialectValidity96 = ModelTestUtils.getAssertClass( moduleChilds, "TestDialectValidity" );
			//Function test:test_quoting
			{
			IMethod methodtest_quoting97;
				IModelElement[] classTestDialectValidity96Childs = classTestDialectValidity96.getChildren();
				methodtest_quoting97 = ModelTestUtils.getAssertMethod( classTestDialectValidity96Childs, "test_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting97, new String[] {"self"} );
				//Class test
				{
				IType classmydialect98;
					IModelElement[] methodtest_quoting97Childs = methodtest_quoting97.getChildren();
					classmydialect98 = ModelTestUtils.getAssertClass( methodtest_quoting97Childs, "mydialect" );
				}
			}
			//Function test:test_delimiter
			{
			IMethod methodtest_delimiter99;
				IModelElement[] classTestDialectValidity96Childs = classTestDialectValidity96.getChildren();
				methodtest_delimiter99 = ModelTestUtils.getAssertMethod( classTestDialectValidity96Childs, "test_delimiter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delimiter99, new String[] {"self"} );
				//Class test
				{
				IType classmydialect100;
					IModelElement[] methodtest_delimiter99Childs = methodtest_delimiter99.getChildren();
					classmydialect100 = ModelTestUtils.getAssertClass( methodtest_delimiter99Childs, "mydialect" );
				}
			}
			//Function test:test_lineterminator
			{
			IMethod methodtest_lineterminator101;
				IModelElement[] classTestDialectValidity96Childs = classTestDialectValidity96.getChildren();
				methodtest_lineterminator101 = ModelTestUtils.getAssertMethod( classTestDialectValidity96Childs, "test_lineterminator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lineterminator101, new String[] {"self"} );
				//Class test
				{
				IType classmydialect102;
					IModelElement[] methodtest_lineterminator101Childs = methodtest_lineterminator101.getChildren();
					classmydialect102 = ModelTestUtils.getAssertClass( methodtest_lineterminator101Childs, "mydialect" );
				}
			}
		}
		//Class test
		{
		IType classTestSniffer103;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSniffer103 = ModelTestUtils.getAssertClass( moduleChilds, "TestSniffer" );
			{
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSniffer103Childs, "sample1");
			}
			{
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSniffer103Childs, "sample2");
			}
			{
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSniffer103Childs, "header");
			}
			{
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSniffer103Childs, "sample3");
			}
			{
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSniffer103Childs, "sample4");
			}
			//Function test:test_has_header
			{
			IMethod methodtest_has_header104;
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				methodtest_has_header104 = ModelTestUtils.getAssertMethod( classTestSniffer103Childs, "test_has_header", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_header104, new String[] {"self"} );
			}
			//Function test:test_sniff
			{
			IMethod methodtest_sniff105;
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				methodtest_sniff105 = ModelTestUtils.getAssertMethod( classTestSniffer103Childs, "test_sniff", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sniff105, new String[] {"self"} );
			}
			//Function test:test_delimiters
			{
			IMethod methodtest_delimiters106;
				IModelElement[] classTestSniffer103Childs = classTestSniffer103.getChildren();
				methodtest_delimiters106 = ModelTestUtils.getAssertMethod( classTestSniffer103Childs, "test_delimiters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delimiters106, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNUL107;
			IModelElement[] moduleChilds = module.getChildren();
			classNUL107 = ModelTestUtils.getAssertClass( moduleChilds, "NUL" );
			//Function test:write
			{
			IMethod methodwrite108;
				IModelElement[] classNUL107Childs = classNUL107.getChildren();
				methodwrite108 = ModelTestUtils.getAssertMethod( classNUL107Childs, "write", 2 );
				ModelTestUtils.assertParameterNames( methodwrite108, new String[] {"s", "args"} );
			}
			{
				IModelElement[] classNUL107Childs = classNUL107.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classNUL107Childs, "writelines");
			}
		}
		//Class test
		{
		IType classTestLeaks109;
			IModelElement[] moduleChilds = module.getChildren();
			classTestLeaks109 = ModelTestUtils.getAssertClass( moduleChilds, "TestLeaks" );
			//Function test:test_create_read
			{
			IMethod methodtest_create_read110;
				IModelElement[] classTestLeaks109Childs = classTestLeaks109.getChildren();
				methodtest_create_read110 = ModelTestUtils.getAssertMethod( classTestLeaks109Childs, "test_create_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_create_read110, new String[] {"self"} );
			}
			//Function test:test_create_write
			{
			IMethod methodtest_create_write111;
				IModelElement[] classTestLeaks109Childs = classTestLeaks109.getChildren();
				methodtest_create_write111 = ModelTestUtils.getAssertMethod( classTestLeaks109Childs, "test_create_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_create_write111, new String[] {"self"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read112;
				IModelElement[] classTestLeaks109Childs = classTestLeaks109.getChildren();
				methodtest_read112 = ModelTestUtils.getAssertMethod( classTestLeaks109Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read112, new String[] {"self"} );
			}
			//Function test:test_write
			{
			IMethod methodtest_write113;
				IModelElement[] classTestLeaks109Childs = classTestLeaks109.getChildren();
				methodtest_write113 = ModelTestUtils.getAssertMethod( classTestLeaks109Childs, "test_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write113, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main114;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main114 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen159( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dis.py"));

		assertNotNull("Module test_dis.py not found", module);
		assertEquals("test_dis.py", module.getElementName());
		
		//Function test:_f
		{
		IMethod method_f0;
			IModelElement[] moduleChilds = module.getChildren();
			method_f0 = ModelTestUtils.getAssertMethod( moduleChilds, "_f", 1 );
			ModelTestUtils.assertParameterNames( method_f0, new String[] {"a"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dis_f");
		}
		//Function test:bug708901
		{
		IMethod methodbug7089011;
			IModelElement[] moduleChilds = module.getChildren();
			methodbug7089011 = ModelTestUtils.getAssertMethod( moduleChilds, "bug708901", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dis_bug708901");
		}
		//Class test
		{
		IType classDisTests2;
			IModelElement[] moduleChilds = module.getChildren();
			classDisTests2 = ModelTestUtils.getAssertClass( moduleChilds, "DisTests" );
			//Function test:do_disassembly_test
			{
			IMethod methoddo_disassembly_test3;
				IModelElement[] classDisTests2Childs = classDisTests2.getChildren();
				methoddo_disassembly_test3 = ModelTestUtils.getAssertMethod( classDisTests2Childs, "do_disassembly_test", 3 );
				ModelTestUtils.assertParameterNames( methoddo_disassembly_test3, new String[] {"self", "func", "expected"} );
			}
			//Function test:test_opmap
			{
			IMethod methodtest_opmap4;
				IModelElement[] classDisTests2Childs = classDisTests2.getChildren();
				methodtest_opmap4 = ModelTestUtils.getAssertMethod( classDisTests2Childs, "test_opmap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opmap4, new String[] {"self"} );
			}
			//Function test:test_opname
			{
			IMethod methodtest_opname5;
				IModelElement[] classDisTests2Childs = classDisTests2.getChildren();
				methodtest_opname5 = ModelTestUtils.getAssertMethod( classDisTests2Childs, "test_opname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_opname5, new String[] {"self"} );
			}
			//Function test:test_boundaries
			{
			IMethod methodtest_boundaries6;
				IModelElement[] classDisTests2Childs = classDisTests2.getChildren();
				methodtest_boundaries6 = ModelTestUtils.getAssertMethod( classDisTests2Childs, "test_boundaries", 1 );
				ModelTestUtils.assertParameterNames( methodtest_boundaries6, new String[] {"self"} );
			}
			//Function test:test_dis
			{
			IMethod methodtest_dis7;
				IModelElement[] classDisTests2Childs = classDisTests2.getChildren();
				methodtest_dis7 = ModelTestUtils.getAssertMethod( classDisTests2Childs, "test_dis", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dis7, new String[] {"self"} );
			}
			//Function test:test_bug_708901
			{
			IMethod methodtest_bug_7089018;
				IModelElement[] classDisTests2Childs = classDisTests2.getChildren();
				methodtest_bug_7089018 = ModelTestUtils.getAssertMethod( classDisTests2Childs, "test_bug_708901", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_7089018, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen160( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_itertools.py"));

		assertNotNull("Module test_itertools.py not found", module);
		assertEquals("test_itertools.py", module.getElementName());
		
		//Function test:onearg
		{
		IMethod methodonearg0;
			IModelElement[] moduleChilds = module.getChildren();
			methodonearg0 = ModelTestUtils.getAssertMethod( moduleChilds, "onearg", 1 );
			ModelTestUtils.assertParameterNames( methodonearg0, new String[] {"x"} );
		}
		//Function test:errfunc
		{
		IMethod methoderrfunc1;
			IModelElement[] moduleChilds = module.getChildren();
			methoderrfunc1 = ModelTestUtils.getAssertMethod( moduleChilds, "errfunc", 1 );
			ModelTestUtils.assertParameterNames( methoderrfunc1, new String[] {"args"} );
		}
		//Function test:gen3
		{
		IMethod methodgen32;
			IModelElement[] moduleChilds = module.getChildren();
			methodgen32 = ModelTestUtils.getAssertMethod( moduleChilds, "gen3", 0 );
		}
		//Function test:isEven
		{
		IMethod methodisEven3;
			IModelElement[] moduleChilds = module.getChildren();
			methodisEven3 = ModelTestUtils.getAssertMethod( moduleChilds, "isEven", 1 );
			ModelTestUtils.assertParameterNames( methodisEven3, new String[] {"x"} );
		}
		//Function test:isOdd
		{
		IMethod methodisOdd4;
			IModelElement[] moduleChilds = module.getChildren();
			methodisOdd4 = ModelTestUtils.getAssertMethod( moduleChilds, "isOdd", 1 );
			ModelTestUtils.assertParameterNames( methodisOdd4, new String[] {"x"} );
		}
		//Class test
		{
		IType classStopNow5;
			IModelElement[] moduleChilds = module.getChildren();
			classStopNow5 = ModelTestUtils.getAssertClass( moduleChilds, "StopNow" );
			//Function test:__iter__
			{
			IMethod method__iter__6;
				IModelElement[] classStopNow5Childs = classStopNow5.getChildren();
				method__iter__6 = ModelTestUtils.getAssertMethod( classStopNow5Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__6, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext7;
				IModelElement[] classStopNow5Childs = classStopNow5.getChildren();
				methodnext7 = ModelTestUtils.getAssertMethod( classStopNow5Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext7, new String[] {"self"} );
			}
		}
		//Function test:take
		{
		IMethod methodtake8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtake8 = ModelTestUtils.getAssertMethod( moduleChilds, "take", 2 );
			ModelTestUtils.assertParameterNames( methodtake8, new String[] {"n", "seq"} );
		}
		//Class test
		{
		IType classTestBasicOps9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOps9 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOps" );
			//Function test:test_chain
			{
			IMethod methodtest_chain10;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_chain10 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_chain", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chain10, new String[] {"self"} );
			}
			//Function test:test_count
			{
			IMethod methodtest_count11;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_count11 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_count", 1 );
				ModelTestUtils.assertParameterNames( methodtest_count11, new String[] {"self"} );
			}
			//Function test:test_cycle
			{
			IMethod methodtest_cycle12;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_cycle12 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_cycle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cycle12, new String[] {"self"} );
			}
			//Function test:test_groupby
			{
			IMethod methodtest_groupby13;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_groupby13 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_groupby", 1 );
				ModelTestUtils.assertParameterNames( methodtest_groupby13, new String[] {"self"} );
				//Class test
				{
				IType classExpectedError14;
					IModelElement[] methodtest_groupby13Childs = methodtest_groupby13.getChildren();
					classExpectedError14 = ModelTestUtils.getAssertClass( methodtest_groupby13Childs, "ExpectedError" );
				}
				//Function test:delayed_raise
				{
				IMethod methoddelayed_raise15;
					IModelElement[] methodtest_groupby13Childs = methodtest_groupby13.getChildren();
					methoddelayed_raise15 = ModelTestUtils.getAssertMethod( methodtest_groupby13Childs, "delayed_raise", 1 );
					ModelTestUtils.assertParameterNames( methoddelayed_raise15, new String[] {"n"} );
				}
				//Function test:gulp
				{
				IMethod methodgulp16;
					IModelElement[] methodtest_groupby13Childs = methodtest_groupby13.getChildren();
					methodgulp16 = ModelTestUtils.getAssertMethod( methodtest_groupby13Childs, "gulp", 3 );
					ModelTestUtils.assertParameterNames( methodgulp16, new String[] {"iterable", "keyp", "func"} );
				}
				//Class test
				{
				IType classDummyCmp17;
					IModelElement[] methodtest_groupby13Childs = methodtest_groupby13.getChildren();
					classDummyCmp17 = ModelTestUtils.getAssertClass( methodtest_groupby13Childs, "DummyCmp" );
					//Function test:__cmp__
					{
					IMethod method__cmp__18;
						IModelElement[] classDummyCmp17Childs = classDummyCmp17.getChildren();
						method__cmp__18 = ModelTestUtils.getAssertMethod( classDummyCmp17Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__18, new String[] {"self", "dst"} );
					}
				}
				//Function test:keyfunc
				{
				IMethod methodkeyfunc19;
					IModelElement[] methodtest_groupby13Childs = methodtest_groupby13.getChildren();
					methodkeyfunc19 = ModelTestUtils.getAssertMethod( methodtest_groupby13Childs, "keyfunc", 1 );
					ModelTestUtils.assertParameterNames( methodkeyfunc19, new String[] {"obj"} );
				}
			}
			//Function test:test_ifilter
			{
			IMethod methodtest_ifilter20;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_ifilter20 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_ifilter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ifilter20, new String[] {"self"} );
			}
			//Function test:test_ifilterfalse
			{
			IMethod methodtest_ifilterfalse21;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_ifilterfalse21 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_ifilterfalse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ifilterfalse21, new String[] {"self"} );
			}
			//Function test:test_izip
			{
			IMethod methodtest_izip22;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_izip22 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_izip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_izip22, new String[] {"self"} );
			}
			//Function test:test_repeat
			{
			IMethod methodtest_repeat23;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_repeat23 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat23, new String[] {"self"} );
			}
			//Function test:test_imap
			{
			IMethod methodtest_imap24;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_imap24 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_imap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imap24, new String[] {"self"} );
			}
			//Function test:test_starmap
			{
			IMethod methodtest_starmap25;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_starmap25 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_starmap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starmap25, new String[] {"self"} );
			}
			//Function test:test_islice
			{
			IMethod methodtest_islice26;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_islice26 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_islice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islice26, new String[] {"self"} );
			}
			//Function test:test_takewhile
			{
			IMethod methodtest_takewhile27;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_takewhile27 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_takewhile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_takewhile27, new String[] {"self"} );
			}
			//Function test:test_dropwhile
			{
			IMethod methodtest_dropwhile28;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_dropwhile28 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_dropwhile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dropwhile28, new String[] {"self"} );
			}
			//Function test:test_tee
			{
			IMethod methodtest_tee29;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_tee29 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_tee", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tee29, new String[] {"self"} );
				//Function test:irange
				{
				IMethod methodirange30;
					IModelElement[] methodtest_tee29Childs = methodtest_tee29.getChildren();
					methodirange30 = ModelTestUtils.getAssertMethod( methodtest_tee29Childs, "irange", 1 );
					ModelTestUtils.assertParameterNames( methodirange30, new String[] {"n"} );
				}
			}
			//Function test:test_StopIteration
			{
			IMethod methodtest_StopIteration31;
				IModelElement[] classTestBasicOps9Childs = classTestBasicOps9.getChildren();
				methodtest_StopIteration31 = ModelTestUtils.getAssertMethod( classTestBasicOps9Childs, "test_StopIteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_StopIteration31, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestGC32;
			IModelElement[] moduleChilds = module.getChildren();
			classTestGC32 = ModelTestUtils.getAssertClass( moduleChilds, "TestGC" );
			//Function test:makecycle
			{
			IMethod methodmakecycle33;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodmakecycle33 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "makecycle", 3 );
				ModelTestUtils.assertParameterNames( methodmakecycle33, new String[] {"self", "iterator", "container"} );
			}
			//Function test:test_chain
			{
			IMethod methodtest_chain34;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_chain34 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_chain", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chain34, new String[] {"self"} );
			}
			//Function test:test_cycle
			{
			IMethod methodtest_cycle35;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_cycle35 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_cycle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cycle35, new String[] {"self"} );
			}
			//Function test:test_dropwhile
			{
			IMethod methodtest_dropwhile36;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_dropwhile36 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_dropwhile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dropwhile36, new String[] {"self"} );
			}
			//Function test:test_groupby
			{
			IMethod methodtest_groupby37;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_groupby37 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_groupby", 1 );
				ModelTestUtils.assertParameterNames( methodtest_groupby37, new String[] {"self"} );
			}
			//Function test:test_ifilter
			{
			IMethod methodtest_ifilter38;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_ifilter38 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_ifilter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ifilter38, new String[] {"self"} );
			}
			//Function test:test_ifilterfalse
			{
			IMethod methodtest_ifilterfalse39;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_ifilterfalse39 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_ifilterfalse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ifilterfalse39, new String[] {"self"} );
			}
			//Function test:test_izip
			{
			IMethod methodtest_izip40;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_izip40 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_izip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_izip40, new String[] {"self"} );
			}
			//Function test:test_imap
			{
			IMethod methodtest_imap41;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_imap41 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_imap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imap41, new String[] {"self"} );
			}
			//Function test:test_islice
			{
			IMethod methodtest_islice42;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_islice42 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_islice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islice42, new String[] {"self"} );
			}
			//Function test:test_repeat
			{
			IMethod methodtest_repeat43;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_repeat43 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat43, new String[] {"self"} );
			}
			//Function test:test_starmap
			{
			IMethod methodtest_starmap44;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_starmap44 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_starmap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starmap44, new String[] {"self"} );
			}
			//Function test:test_takewhile
			{
			IMethod methodtest_takewhile45;
				IModelElement[] classTestGC32Childs = classTestGC32.getChildren();
				methodtest_takewhile45 = ModelTestUtils.getAssertMethod( classTestGC32Childs, "test_takewhile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_takewhile45, new String[] {"self"} );
			}
		}
		//Function test:R
		{
		IMethod methodR46;
			IModelElement[] moduleChilds = module.getChildren();
			methodR46 = ModelTestUtils.getAssertMethod( moduleChilds, "R", 1 );
			ModelTestUtils.assertParameterNames( methodR46, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classG47;
			IModelElement[] moduleChilds = module.getChildren();
			classG47 = ModelTestUtils.getAssertClass( moduleChilds, "G" );
			//Function test:__init__
			{
			IMethod method__init__48;
				IModelElement[] classG47Childs = classG47.getChildren();
				method__init__48 = ModelTestUtils.getAssertMethod( classG47Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__48, new String[] {"self", "seqn"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__49;
				IModelElement[] classG47Childs = classG47.getChildren();
				method__getitem__49 = ModelTestUtils.getAssertMethod( classG47Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__49, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI50;
			IModelElement[] moduleChilds = module.getChildren();
			classI50 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__51;
				IModelElement[] classI50Childs = classI50.getChildren();
				method__init__51 = ModelTestUtils.getAssertMethod( classI50Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__51, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__52;
				IModelElement[] classI50Childs = classI50.getChildren();
				method__iter__52 = ModelTestUtils.getAssertMethod( classI50Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__52, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext53;
				IModelElement[] classI50Childs = classI50.getChildren();
				methodnext53 = ModelTestUtils.getAssertMethod( classI50Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext53, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg54;
			IModelElement[] moduleChilds = module.getChildren();
			classIg54 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__55;
				IModelElement[] classIg54Childs = classIg54.getChildren();
				method__init__55 = ModelTestUtils.getAssertMethod( classIg54Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__55, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__56;
				IModelElement[] classIg54Childs = classIg54.getChildren();
				method__iter__56 = ModelTestUtils.getAssertMethod( classIg54Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__56, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX57;
			IModelElement[] moduleChilds = module.getChildren();
			classX57 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__58;
				IModelElement[] classX57Childs = classX57.getChildren();
				method__init__58 = ModelTestUtils.getAssertMethod( classX57Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__58, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext59;
				IModelElement[] classX57Childs = classX57.getChildren();
				methodnext59 = ModelTestUtils.getAssertMethod( classX57Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext59, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN60;
			IModelElement[] moduleChilds = module.getChildren();
			classN60 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__61;
				IModelElement[] classN60Childs = classN60.getChildren();
				method__init__61 = ModelTestUtils.getAssertMethod( classN60Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__61, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__62;
				IModelElement[] classN60Childs = classN60.getChildren();
				method__iter__62 = ModelTestUtils.getAssertMethod( classN60Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__62, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE63;
			IModelElement[] moduleChilds = module.getChildren();
			classE63 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__64;
				IModelElement[] classE63Childs = classE63.getChildren();
				method__init__64 = ModelTestUtils.getAssertMethod( classE63Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__64, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__65;
				IModelElement[] classE63Childs = classE63.getChildren();
				method__iter__65 = ModelTestUtils.getAssertMethod( classE63Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__65, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext66;
				IModelElement[] classE63Childs = classE63.getChildren();
				methodnext66 = ModelTestUtils.getAssertMethod( classE63Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext66, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classS67;
			IModelElement[] moduleChilds = module.getChildren();
			classS67 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
			//Function test:__init__
			{
			IMethod method__init__68;
				IModelElement[] classS67Childs = classS67.getChildren();
				method__init__68 = ModelTestUtils.getAssertMethod( classS67Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__68, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__69;
				IModelElement[] classS67Childs = classS67.getChildren();
				method__iter__69 = ModelTestUtils.getAssertMethod( classS67Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__69, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext70;
				IModelElement[] classS67Childs = classS67.getChildren();
				methodnext70 = ModelTestUtils.getAssertMethod( classS67Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext70, new String[] {"self"} );
			}
		}
		//Function test:L
		{
		IMethod methodL71;
			IModelElement[] moduleChilds = module.getChildren();
			methodL71 = ModelTestUtils.getAssertMethod( moduleChilds, "L", 1 );
			ModelTestUtils.assertParameterNames( methodL71, new String[] {"seqn"} );
		}
		//Class test
		{
		IType classTestVariousIteratorArgs72;
			IModelElement[] moduleChilds = module.getChildren();
			classTestVariousIteratorArgs72 = ModelTestUtils.getAssertClass( moduleChilds, "TestVariousIteratorArgs" );
			//Function test:test_chain
			{
			IMethod methodtest_chain73;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_chain73 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_chain", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chain73, new String[] {"self"} );
			}
			//Function test:test_cycle
			{
			IMethod methodtest_cycle74;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_cycle74 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_cycle", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cycle74, new String[] {"self"} );
			}
			//Function test:test_groupby
			{
			IMethod methodtest_groupby75;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_groupby75 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_groupby", 1 );
				ModelTestUtils.assertParameterNames( methodtest_groupby75, new String[] {"self"} );
			}
			//Function test:test_ifilter
			{
			IMethod methodtest_ifilter76;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_ifilter76 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_ifilter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ifilter76, new String[] {"self"} );
			}
			//Function test:test_ifilterfalse
			{
			IMethod methodtest_ifilterfalse77;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_ifilterfalse77 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_ifilterfalse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ifilterfalse77, new String[] {"self"} );
			}
			//Function test:test_izip
			{
			IMethod methodtest_izip78;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_izip78 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_izip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_izip78, new String[] {"self"} );
			}
			//Function test:test_imap
			{
			IMethod methodtest_imap79;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_imap79 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_imap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imap79, new String[] {"self"} );
			}
			//Function test:test_islice
			{
			IMethod methodtest_islice80;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_islice80 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_islice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_islice80, new String[] {"self"} );
			}
			//Function test:test_starmap
			{
			IMethod methodtest_starmap81;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_starmap81 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_starmap", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starmap81, new String[] {"self"} );
			}
			//Function test:test_takewhile
			{
			IMethod methodtest_takewhile82;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_takewhile82 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_takewhile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_takewhile82, new String[] {"self"} );
			}
			//Function test:test_dropwhile
			{
			IMethod methodtest_dropwhile83;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_dropwhile83 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_dropwhile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dropwhile83, new String[] {"self"} );
			}
			//Function test:test_tee
			{
			IMethod methodtest_tee84;
				IModelElement[] classTestVariousIteratorArgs72Childs = classTestVariousIteratorArgs72.getChildren();
				methodtest_tee84 = ModelTestUtils.getAssertMethod( classTestVariousIteratorArgs72Childs, "test_tee", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tee84, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLengthTransparency85;
			IModelElement[] moduleChilds = module.getChildren();
			classLengthTransparency85 = ModelTestUtils.getAssertClass( moduleChilds, "LengthTransparency" );
			//Function test:test_repeat
			{
			IMethod methodtest_repeat86;
				IModelElement[] classLengthTransparency85Childs = classLengthTransparency85.getChildren();
				methodtest_repeat86 = ModelTestUtils.getAssertMethod( classLengthTransparency85Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat86, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classRegressionTests87;
			IModelElement[] moduleChilds = module.getChildren();
			classRegressionTests87 = ModelTestUtils.getAssertClass( moduleChilds, "RegressionTests" );
			//Function test:test_sf_793826
			{
			IMethod methodtest_sf_79382688;
				IModelElement[] classRegressionTests87Childs = classRegressionTests87.getChildren();
				methodtest_sf_79382688 = ModelTestUtils.getAssertMethod( classRegressionTests87Childs, "test_sf_793826", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sf_79382688, new String[] {"self"} );
				//Function test:mutatingtuple
				{
				IMethod methodmutatingtuple89;
					IModelElement[] methodtest_sf_79382688Childs = methodtest_sf_79382688.getChildren();
					methodmutatingtuple89 = ModelTestUtils.getAssertMethod( methodtest_sf_79382688Childs, "mutatingtuple", 3 );
					ModelTestUtils.assertParameterNames( methodmutatingtuple89, new String[] {"tuple1", "f", "tuple2"} );
					//Function test:g
					{
					IMethod methodg90;
						IModelElement[] methodmutatingtuple89Childs = methodmutatingtuple89.getChildren();
						methodg90 = ModelTestUtils.getAssertMethod( methodmutatingtuple89Childs, "g", 2 );
						ModelTestUtils.assertParameterNames( methodg90, new String[] {"value", "first"} );
					}
				}
				//Function test:f
				{
				IMethod methodf91;
					IModelElement[] methodtest_sf_79382688Childs = methodtest_sf_79382688.getChildren();
					methodf91 = ModelTestUtils.getAssertMethod( methodtest_sf_79382688Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf91, new String[] {"t"} );
				}
			}
			//Function test:test_sf_950057
			{
			IMethod methodtest_sf_95005792;
				IModelElement[] classRegressionTests87Childs = classRegressionTests87.getChildren();
				methodtest_sf_95005792 = ModelTestUtils.getAssertMethod( classRegressionTests87Childs, "test_sf_950057", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sf_95005792, new String[] {"self"} );
				//Function test:gen1
				{
				IMethod methodgen193;
					IModelElement[] methodtest_sf_95005792Childs = methodtest_sf_95005792.getChildren();
					methodgen193 = ModelTestUtils.getAssertMethod( methodtest_sf_95005792Childs, "gen1", 0 );
				}
				//Function test:gen2
				{
				IMethod methodgen294;
					IModelElement[] methodtest_sf_95005792Childs = methodtest_sf_95005792.getChildren();
					methodgen294 = ModelTestUtils.getAssertMethod( methodtest_sf_95005792Childs, "gen2", 1 );
					ModelTestUtils.assertParameterNames( methodgen294, new String[] {"x"} );
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
		IMethod methodtest_main95;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main95 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main95, new String[] {"verbose"} );
		}

	}
	public void testModelGen161( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_multifile.py"));

		assertNotNull("Module test_multifile.py not found", module);
		assertEquals("test_multifile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		//Function test:getMIMEMsg
		{
		IMethod methodgetMIMEMsg0;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetMIMEMsg0 = ModelTestUtils.getAssertMethod( moduleChilds, "getMIMEMsg", 1 );
			ModelTestUtils.assertParameterNames( methodgetMIMEMsg0, new String[] {"mf"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen162( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_eof.py"));

		assertNotNull("Module test_eof.py not found", module);
		assertEquals("test_eof.py", module.getElementName());
		
		//Class test
		{
		IType classEOFTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classEOFTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "EOFTestCase" );
			//Function test:test_EOFC
			{
			IMethod methodtest_EOFC1;
				IModelElement[] classEOFTestCase0Childs = classEOFTestCase0.getChildren();
				methodtest_EOFC1 = ModelTestUtils.getAssertMethod( classEOFTestCase0Childs, "test_EOFC", 1 );
				ModelTestUtils.assertParameterNames( methodtest_EOFC1, new String[] {"self"} );
			}
			//Function test:test_EOFS
			{
			IMethod methodtest_EOFS2;
				IModelElement[] classEOFTestCase0Childs = classEOFTestCase0.getChildren();
				methodtest_EOFS2 = ModelTestUtils.getAssertMethod( classEOFTestCase0Childs, "test_EOFS", 1 );
				ModelTestUtils.assertParameterNames( methodtest_EOFS2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen163( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cookielib.py"));

		assertNotNull("Module test_cookielib.py not found", module);
		assertEquals("test_cookielib.py", module.getElementName());
		
		//Class test
		{
		IType classDateTimeTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classDateTimeTests0 = ModelTestUtils.getAssertClass( moduleChilds, "DateTimeTests" );
			//Function test:test_time2isoz
			{
			IMethod methodtest_time2isoz1;
				IModelElement[] classDateTimeTests0Childs = classDateTimeTests0.getChildren();
				methodtest_time2isoz1 = ModelTestUtils.getAssertMethod( classDateTimeTests0Childs, "test_time2isoz", 1 );
				ModelTestUtils.assertParameterNames( methodtest_time2isoz1, new String[] {"self"} );
			}
			//Function test:test_http2time
			{
			IMethod methodtest_http2time2;
				IModelElement[] classDateTimeTests0Childs = classDateTimeTests0.getChildren();
				methodtest_http2time2 = ModelTestUtils.getAssertMethod( classDateTimeTests0Childs, "test_http2time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http2time2, new String[] {"self"} );
				//Function test:parse_date
				{
				IMethod methodparse_date3;
					IModelElement[] methodtest_http2time2Childs = methodtest_http2time2.getChildren();
					methodparse_date3 = ModelTestUtils.getAssertMethod( methodtest_http2time2Childs, "parse_date", 1 );
					ModelTestUtils.assertParameterNames( methodparse_date3, new String[] {"text"} );
				}
			}
			//Function test:test_http2time_formats
			{
			IMethod methodtest_http2time_formats4;
				IModelElement[] classDateTimeTests0Childs = classDateTimeTests0.getChildren();
				methodtest_http2time_formats4 = ModelTestUtils.getAssertMethod( classDateTimeTests0Childs, "test_http2time_formats", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http2time_formats4, new String[] {"self"} );
			}
			//Function test:test_http2time_garbage
			{
			IMethod methodtest_http2time_garbage5;
				IModelElement[] classDateTimeTests0Childs = classDateTimeTests0.getChildren();
				methodtest_http2time_garbage5 = ModelTestUtils.getAssertMethod( classDateTimeTests0Childs, "test_http2time_garbage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http2time_garbage5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classHeaderTests6;
			IModelElement[] moduleChilds = module.getChildren();
			classHeaderTests6 = ModelTestUtils.getAssertClass( moduleChilds, "HeaderTests" );
			//Function test:test_parse_ns_headers
			{
			IMethod methodtest_parse_ns_headers7;
				IModelElement[] classHeaderTests6Childs = classHeaderTests6.getChildren();
				methodtest_parse_ns_headers7 = ModelTestUtils.getAssertMethod( classHeaderTests6Childs, "test_parse_ns_headers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parse_ns_headers7, new String[] {"self"} );
			}
			//Function test:test_parse_ns_headers_special_names
			{
			IMethod methodtest_parse_ns_headers_special_names8;
				IModelElement[] classHeaderTests6Childs = classHeaderTests6.getChildren();
				methodtest_parse_ns_headers_special_names8 = ModelTestUtils.getAssertMethod( classHeaderTests6Childs, "test_parse_ns_headers_special_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parse_ns_headers_special_names8, new String[] {"self"} );
			}
			//Function test:test_join_header_words
			{
			IMethod methodtest_join_header_words9;
				IModelElement[] classHeaderTests6Childs = classHeaderTests6.getChildren();
				methodtest_join_header_words9 = ModelTestUtils.getAssertMethod( classHeaderTests6Childs, "test_join_header_words", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join_header_words9, new String[] {"self"} );
			}
			//Function test:test_split_header_words
			{
			IMethod methodtest_split_header_words10;
				IModelElement[] classHeaderTests6Childs = classHeaderTests6.getChildren();
				methodtest_split_header_words10 = ModelTestUtils.getAssertMethod( classHeaderTests6Childs, "test_split_header_words", 1 );
				ModelTestUtils.assertParameterNames( methodtest_split_header_words10, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip11;
				IModelElement[] classHeaderTests6Childs = classHeaderTests6.getChildren();
				methodtest_roundtrip11 = ModelTestUtils.getAssertMethod( classHeaderTests6Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFakeResponse12;
			IModelElement[] moduleChilds = module.getChildren();
			classFakeResponse12 = ModelTestUtils.getAssertClass( moduleChilds, "FakeResponse" );
			//Function test:__init__
			{
			IMethod method__init__13;
				IModelElement[] classFakeResponse12Childs = classFakeResponse12.getChildren();
				method__init__13 = ModelTestUtils.getAssertMethod( classFakeResponse12Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__13, new String[] {"self", "headers", "url"} );
			}
			//Function test:info
			{
			IMethod methodinfo14;
				IModelElement[] classFakeResponse12Childs = classFakeResponse12.getChildren();
				methodinfo14 = ModelTestUtils.getAssertMethod( classFakeResponse12Childs, "info", 1 );
				ModelTestUtils.assertParameterNames( methodinfo14, new String[] {"self"} );
			}
		}
		//Function test:interact_2965
		{
		IMethod methodinteract_296515;
			IModelElement[] moduleChilds = module.getChildren();
			methodinteract_296515 = ModelTestUtils.getAssertMethod( moduleChilds, "interact_2965", 3 );
			ModelTestUtils.assertParameterNames( methodinteract_296515, new String[] {"cookiejar", "url", "set_cookie_hdrs"} );
		}
		//Function test:interact_netscape
		{
		IMethod methodinteract_netscape16;
			IModelElement[] moduleChilds = module.getChildren();
			methodinteract_netscape16 = ModelTestUtils.getAssertMethod( moduleChilds, "interact_netscape", 3 );
			ModelTestUtils.assertParameterNames( methodinteract_netscape16, new String[] {"cookiejar", "url", "set_cookie_hdrs"} );
		}
		//Function test:_interact
		{
		IMethod method_interact17;
			IModelElement[] moduleChilds = module.getChildren();
			method_interact17 = ModelTestUtils.getAssertMethod( moduleChilds, "_interact", 4 );
			ModelTestUtils.assertParameterNames( method_interact17, new String[] {"cookiejar", "url", "set_cookie_hdrs", "hdr_name"} );
		}
		//Class test
		{
		IType classFileCookieJarTests18;
			IModelElement[] moduleChilds = module.getChildren();
			classFileCookieJarTests18 = ModelTestUtils.getAssertClass( moduleChilds, "FileCookieJarTests" );
			//Function test:test_lwp_valueless_cookie
			{
			IMethod methodtest_lwp_valueless_cookie19;
				IModelElement[] classFileCookieJarTests18Childs = classFileCookieJarTests18.getChildren();
				methodtest_lwp_valueless_cookie19 = ModelTestUtils.getAssertMethod( classFileCookieJarTests18Childs, "test_lwp_valueless_cookie", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lwp_valueless_cookie19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCookieTests20;
			IModelElement[] moduleChilds = module.getChildren();
			classCookieTests20 = ModelTestUtils.getAssertClass( moduleChilds, "CookieTests" );
			//Function test:test_domain_return_ok
			{
			IMethod methodtest_domain_return_ok21;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_domain_return_ok21 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_domain_return_ok", 1 );
				ModelTestUtils.assertParameterNames( methodtest_domain_return_ok21, new String[] {"self"} );
			}
			//Function test:test_missing_value
			{
			IMethod methodtest_missing_value22;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_missing_value22 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_missing_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_missing_value22, new String[] {"self"} );
			}
			//Function test:test_ns_parser
			{
			IMethod methodtest_ns_parser23;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_ns_parser23 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_ns_parser", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ns_parser23, new String[] {"self"} );
			}
			//Function test:test_ns_parser_special_names
			{
			IMethod methodtest_ns_parser_special_names24;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_ns_parser_special_names24 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_ns_parser_special_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ns_parser_special_names24, new String[] {"self"} );
			}
			//Function test:test_expires
			{
			IMethod methodtest_expires25;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_expires25 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_expires", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expires25, new String[] {"self"} );
			}
			//Function test:test_default_path
			{
			IMethod methodtest_default_path26;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_default_path26 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_default_path", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_path26, new String[] {"self"} );
			}
			//Function test:test_escape_path
			{
			IMethod methodtest_escape_path27;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_escape_path27 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_escape_path", 1 );
				ModelTestUtils.assertParameterNames( methodtest_escape_path27, new String[] {"self"} );
			}
			//Function test:test_request_path
			{
			IMethod methodtest_request_path28;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_request_path28 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_request_path", 1 );
				ModelTestUtils.assertParameterNames( methodtest_request_path28, new String[] {"self"} );
			}
			//Function test:test_request_port
			{
			IMethod methodtest_request_port29;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_request_port29 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_request_port", 1 );
				ModelTestUtils.assertParameterNames( methodtest_request_port29, new String[] {"self"} );
			}
			//Function test:test_request_host
			{
			IMethod methodtest_request_host30;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_request_host30 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_request_host", 1 );
				ModelTestUtils.assertParameterNames( methodtest_request_host30, new String[] {"self"} );
			}
			//Function test:test_is_HDN
			{
			IMethod methodtest_is_HDN31;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_is_HDN31 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_is_HDN", 1 );
				ModelTestUtils.assertParameterNames( methodtest_is_HDN31, new String[] {"self"} );
			}
			//Function test:test_reach
			{
			IMethod methodtest_reach32;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_reach32 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_reach", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reach32, new String[] {"self"} );
			}
			//Function test:test_domain_match
			{
			IMethod methodtest_domain_match33;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_domain_match33 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_domain_match", 1 );
				ModelTestUtils.assertParameterNames( methodtest_domain_match33, new String[] {"self"} );
			}
			//Function test:test_wrong_domain
			{
			IMethod methodtest_wrong_domain34;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_wrong_domain34 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_wrong_domain", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wrong_domain34, new String[] {"self"} );
			}
			//Function test:test_two_component_domain_ns
			{
			IMethod methodtest_two_component_domain_ns35;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_two_component_domain_ns35 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_two_component_domain_ns", 1 );
				ModelTestUtils.assertParameterNames( methodtest_two_component_domain_ns35, new String[] {"self"} );
			}
			//Function test:test_two_component_domain_rfc2965
			{
			IMethod methodtest_two_component_domain_rfc296536;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_two_component_domain_rfc296536 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_two_component_domain_rfc2965", 1 );
				ModelTestUtils.assertParameterNames( methodtest_two_component_domain_rfc296536, new String[] {"self"} );
			}
			//Function test:test_domain_allow
			{
			IMethod methodtest_domain_allow37;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_domain_allow37 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_domain_allow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_domain_allow37, new String[] {"self"} );
			}
			//Function test:test_domain_block
			{
			IMethod methodtest_domain_block38;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_domain_block38 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_domain_block", 1 );
				ModelTestUtils.assertParameterNames( methodtest_domain_block38, new String[] {"self"} );
			}
			//Function test:test_secure
			{
			IMethod methodtest_secure39;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_secure39 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_secure", 1 );
				ModelTestUtils.assertParameterNames( methodtest_secure39, new String[] {"self"} );
			}
			//Function test:test_quote_cookie_value
			{
			IMethod methodtest_quote_cookie_value40;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_quote_cookie_value40 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_quote_cookie_value", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quote_cookie_value40, new String[] {"self"} );
			}
			//Function test:test_missing_final_slash
			{
			IMethod methodtest_missing_final_slash41;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_missing_final_slash41 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_missing_final_slash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_missing_final_slash41, new String[] {"self"} );
			}
			//Function test:test_domain_mirror
			{
			IMethod methodtest_domain_mirror42;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_domain_mirror42 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_domain_mirror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_domain_mirror42, new String[] {"self"} );
			}
			//Function test:test_path_mirror
			{
			IMethod methodtest_path_mirror43;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_path_mirror43 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_path_mirror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_path_mirror43, new String[] {"self"} );
			}
			//Function test:test_port_mirror
			{
			IMethod methodtest_port_mirror44;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_port_mirror44 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_port_mirror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_port_mirror44, new String[] {"self"} );
			}
			//Function test:test_no_return_comment
			{
			IMethod methodtest_no_return_comment45;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_no_return_comment45 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_no_return_comment", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_return_comment45, new String[] {"self"} );
			}
			//Function test:test_Cookie_iterator
			{
			IMethod methodtest_Cookie_iterator46;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_Cookie_iterator46 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_Cookie_iterator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_Cookie_iterator46, new String[] {"self"} );
			}
			//Function test:test_parse_ns_headers
			{
			IMethod methodtest_parse_ns_headers47;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_parse_ns_headers47 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_parse_ns_headers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parse_ns_headers47, new String[] {"self"} );
			}
			//Function test:test_bad_cookie_header
			{
			IMethod methodtest_bad_cookie_header48;
				IModelElement[] classCookieTests20Childs = classCookieTests20.getChildren();
				methodtest_bad_cookie_header48 = ModelTestUtils.getAssertMethod( classCookieTests20Childs, "test_bad_cookie_header", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_cookie_header48, new String[] {"self"} );
				//Function test:cookiejar_from_cookie_headers
				{
				IMethod methodcookiejar_from_cookie_headers49;
					IModelElement[] methodtest_bad_cookie_header48Childs = methodtest_bad_cookie_header48.getChildren();
					methodcookiejar_from_cookie_headers49 = ModelTestUtils.getAssertMethod( methodtest_bad_cookie_header48Childs, "cookiejar_from_cookie_headers", 1 );
					ModelTestUtils.assertParameterNames( methodcookiejar_from_cookie_headers49, new String[] {"headers"} );
				}
			}
		}
		//Class test
		{
		IType classLWPCookieTests50;
			IModelElement[] moduleChilds = module.getChildren();
			classLWPCookieTests50 = ModelTestUtils.getAssertClass( moduleChilds, "LWPCookieTests" );
			//Function test:test_netscape_example_1
			{
			IMethod methodtest_netscape_example_151;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_netscape_example_151 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_netscape_example_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_netscape_example_151, new String[] {"self"} );
			}
			//Function test:test_netscape_example_2
			{
			IMethod methodtest_netscape_example_252;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_netscape_example_252 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_netscape_example_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_netscape_example_252, new String[] {"self"} );
			}
			//Function test:test_ietf_example_1
			{
			IMethod methodtest_ietf_example_153;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_ietf_example_153 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_ietf_example_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ietf_example_153, new String[] {"self"} );
			}
			//Function test:test_ietf_example_2
			{
			IMethod methodtest_ietf_example_254;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_ietf_example_254 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_ietf_example_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ietf_example_254, new String[] {"self"} );
			}
			//Function test:test_rejection
			{
			IMethod methodtest_rejection55;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_rejection55 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_rejection", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rejection55, new String[] {"self"} );
			}
			//Function test:test_url_encoding
			{
			IMethod methodtest_url_encoding56;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_url_encoding56 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_url_encoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_url_encoding56, new String[] {"self"} );
			}
			//Function test:test_mozilla
			{
			IMethod methodtest_mozilla57;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_mozilla57 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_mozilla", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mozilla57, new String[] {"self"} );
				//Function test:save_and_restore
				{
				IMethod methodsave_and_restore58;
					IModelElement[] methodtest_mozilla57Childs = methodtest_mozilla57.getChildren();
					methodsave_and_restore58 = ModelTestUtils.getAssertMethod( methodtest_mozilla57Childs, "save_and_restore", 2 );
					ModelTestUtils.assertParameterNames( methodsave_and_restore58, new String[] {"cj", "ignore_discard"} );
				}
			}
			//Function test:test_netscape_misc
			{
			IMethod methodtest_netscape_misc59;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_netscape_misc59 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_netscape_misc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_netscape_misc59, new String[] {"self"} );
			}
			//Function test:test_intranet_domains_2965
			{
			IMethod methodtest_intranet_domains_296560;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_intranet_domains_296560 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_intranet_domains_2965", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intranet_domains_296560, new String[] {"self"} );
			}
			//Function test:test_intranet_domains_ns
			{
			IMethod methodtest_intranet_domains_ns61;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_intranet_domains_ns61 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_intranet_domains_ns", 1 );
				ModelTestUtils.assertParameterNames( methodtest_intranet_domains_ns61, new String[] {"self"} );
			}
			//Function test:test_empty_path
			{
			IMethod methodtest_empty_path62;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_empty_path62 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_empty_path", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty_path62, new String[] {"self"} );
			}
			//Function test:test_session_cookies
			{
			IMethod methodtest_session_cookies63;
				IModelElement[] classLWPCookieTests50Childs = classLWPCookieTests50.getChildren();
				methodtest_session_cookies63 = ModelTestUtils.getAssertMethod( classLWPCookieTests50Childs, "test_session_cookies", 1 );
				ModelTestUtils.assertParameterNames( methodtest_session_cookies63, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main64;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main64 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main64, new String[] {"verbose"} );
		}

	}
	public void testModelGen164( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_unicodedata.py"));

		assertNotNull("Module test_unicodedata.py not found", module);
		assertEquals("test_unicodedata.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "encoding");
		}
		//Class test
		{
		IType classUnicodeMethodsTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeMethodsTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeMethodsTest" );
			{
				IModelElement[] classUnicodeMethodsTest0Childs = classUnicodeMethodsTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeMethodsTest0Childs, "expectedchecksum");
			}
			//Function test:test_method_checksum
			{
			IMethod methodtest_method_checksum1;
				IModelElement[] classUnicodeMethodsTest0Childs = classUnicodeMethodsTest0.getChildren();
				methodtest_method_checksum1 = ModelTestUtils.getAssertMethod( classUnicodeMethodsTest0Childs, "test_method_checksum", 1 );
				ModelTestUtils.assertParameterNames( methodtest_method_checksum1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnicodeDatabaseTest2;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeDatabaseTest2 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeDatabaseTest" );
			//Function test:setUp
			{
			IMethod methodsetUp3;
				IModelElement[] classUnicodeDatabaseTest2Childs = classUnicodeDatabaseTest2.getChildren();
				methodsetUp3 = ModelTestUtils.getAssertMethod( classUnicodeDatabaseTest2Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp3, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown4;
				IModelElement[] classUnicodeDatabaseTest2Childs = classUnicodeDatabaseTest2.getChildren();
				methodtearDown4 = ModelTestUtils.getAssertMethod( classUnicodeDatabaseTest2Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnicodeFunctionsTest5;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeFunctionsTest5 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeFunctionsTest" );
			{
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnicodeFunctionsTest5Childs, "expectedchecksum");
			}
			//Function test:test_function_checksum
			{
			IMethod methodtest_function_checksum6;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_function_checksum6 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_function_checksum", 1 );
				ModelTestUtils.assertParameterNames( methodtest_function_checksum6, new String[] {"self"} );
			}
			//Function test:test_digit
			{
			IMethod methodtest_digit7;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_digit7 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_digit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_digit7, new String[] {"self"} );
			}
			//Function test:test_numeric
			{
			IMethod methodtest_numeric8;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_numeric8 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_numeric", 1 );
				ModelTestUtils.assertParameterNames( methodtest_numeric8, new String[] {"self"} );
			}
			//Function test:test_decimal
			{
			IMethod methodtest_decimal9;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_decimal9 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_decimal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decimal9, new String[] {"self"} );
			}
			//Function test:test_category
			{
			IMethod methodtest_category10;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_category10 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_category", 1 );
				ModelTestUtils.assertParameterNames( methodtest_category10, new String[] {"self"} );
			}
			//Function test:test_bidirectional
			{
			IMethod methodtest_bidirectional11;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_bidirectional11 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_bidirectional", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bidirectional11, new String[] {"self"} );
			}
			//Function test:test_decomposition
			{
			IMethod methodtest_decomposition12;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_decomposition12 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_decomposition", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decomposition12, new String[] {"self"} );
			}
			//Function test:test_mirrored
			{
			IMethod methodtest_mirrored13;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_mirrored13 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_mirrored", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mirrored13, new String[] {"self"} );
			}
			//Function test:test_combining
			{
			IMethod methodtest_combining14;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_combining14 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_combining", 1 );
				ModelTestUtils.assertParameterNames( methodtest_combining14, new String[] {"self"} );
			}
			//Function test:test_normalize
			{
			IMethod methodtest_normalize15;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_normalize15 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_normalize", 1 );
				ModelTestUtils.assertParameterNames( methodtest_normalize15, new String[] {"self"} );
			}
			//Function test:test_east_asian_width
			{
			IMethod methodtest_east_asian_width16;
				IModelElement[] classUnicodeFunctionsTest5Childs = classUnicodeFunctionsTest5.getChildren();
				methodtest_east_asian_width16 = ModelTestUtils.getAssertMethod( classUnicodeFunctionsTest5Childs, "test_east_asian_width", 1 );
				ModelTestUtils.assertParameterNames( methodtest_east_asian_width16, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnicodeMiscTest17;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeMiscTest17 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeMiscTest" );
			//Function test:test_decimal_numeric_consistent
			{
			IMethod methodtest_decimal_numeric_consistent18;
				IModelElement[] classUnicodeMiscTest17Childs = classUnicodeMiscTest17.getChildren();
				methodtest_decimal_numeric_consistent18 = ModelTestUtils.getAssertMethod( classUnicodeMiscTest17Childs, "test_decimal_numeric_consistent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decimal_numeric_consistent18, new String[] {"self"} );
			}
			//Function test:test_digit_numeric_consistent
			{
			IMethod methodtest_digit_numeric_consistent19;
				IModelElement[] classUnicodeMiscTest17Childs = classUnicodeMiscTest17.getChildren();
				methodtest_digit_numeric_consistent19 = ModelTestUtils.getAssertMethod( classUnicodeMiscTest17Childs, "test_digit_numeric_consistent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_digit_numeric_consistent19, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen165( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_glob.py"));

		assertNotNull("Module test_glob.py not found", module);
		assertEquals("test_glob.py", module.getElementName());
		
		//Function test:mkdirs
		{
		IMethod methodmkdirs0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmkdirs0 = ModelTestUtils.getAssertMethod( moduleChilds, "mkdirs", 1 );
			ModelTestUtils.assertParameterNames( methodmkdirs0, new String[] {"fname"} );
		}
		//Function test:touchfile
		{
		IMethod methodtouchfile1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtouchfile1 = ModelTestUtils.getAssertMethod( moduleChilds, "touchfile", 1 );
			ModelTestUtils.assertParameterNames( methodtouchfile1, new String[] {"fname"} );
		}
		//Function test:deltree
		{
		IMethod methoddeltree2;
			IModelElement[] moduleChilds = module.getChildren();
			methoddeltree2 = ModelTestUtils.getAssertMethod( moduleChilds, "deltree", 1 );
			ModelTestUtils.assertParameterNames( methoddeltree2, new String[] {"fname"} );
		}
		//Class test
		{
		IType classGlobTests3;
			IModelElement[] moduleChilds = module.getChildren();
			classGlobTests3 = ModelTestUtils.getAssertClass( moduleChilds, "GlobTests" );
			//Function test:norm
			{
			IMethod methodnorm4;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodnorm4 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "norm", 2 );
				ModelTestUtils.assertParameterNames( methodnorm4, new String[] {"self", "parts"} );
			}
			//Function test:mktemp
			{
			IMethod methodmktemp5;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodmktemp5 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "mktemp", 2 );
				ModelTestUtils.assertParameterNames( methodmktemp5, new String[] {"self", "parts"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp6;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodsetUp6 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp6, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown7;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodtearDown7 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown7, new String[] {"self"} );
			}
			//Function test:glob
			{
			IMethod methodglob8;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodglob8 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "glob", 2 );
				ModelTestUtils.assertParameterNames( methodglob8, new String[] {"self", "parts"} );
			}
			//Function test:assertSequencesEqual_noorder
			{
			IMethod methodassertSequencesEqual_noorder9;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodassertSequencesEqual_noorder9 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "assertSequencesEqual_noorder", 3 );
				ModelTestUtils.assertParameterNames( methodassertSequencesEqual_noorder9, new String[] {"self", "l1", "l2"} );
			}
			//Function test:test_glob_literal
			{
			IMethod methodtest_glob_literal10;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodtest_glob_literal10 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "test_glob_literal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_glob_literal10, new String[] {"self"} );
			}
			//Function test:test_glob_one_directory
			{
			IMethod methodtest_glob_one_directory11;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodtest_glob_one_directory11 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "test_glob_one_directory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_glob_one_directory11, new String[] {"self"} );
			}
			//Function test:test_glob_nested_directory
			{
			IMethod methodtest_glob_nested_directory12;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodtest_glob_nested_directory12 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "test_glob_nested_directory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_glob_nested_directory12, new String[] {"self"} );
			}
			//Function test:test_glob_directory_names
			{
			IMethod methodtest_glob_directory_names13;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodtest_glob_directory_names13 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "test_glob_directory_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_glob_directory_names13, new String[] {"self"} );
			}
			//Function test:test_glob_broken_symlinks
			{
			IMethod methodtest_glob_broken_symlinks14;
				IModelElement[] classGlobTests3Childs = classGlobTests3.getChildren();
				methodtest_glob_broken_symlinks14 = ModelTestUtils.getAssertMethod( classGlobTests3Childs, "test_glob_broken_symlinks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_glob_broken_symlinks14, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen166( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_fnmatch.py"));

		assertNotNull("Module test_fnmatch.py not found", module);
		assertEquals("test_fnmatch.py", module.getElementName());
		
		//Class test
		{
		IType classFnmatchTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classFnmatchTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "FnmatchTestCase" );
			//Function test:check_match
			{
			IMethod methodcheck_match1;
				IModelElement[] classFnmatchTestCase0Childs = classFnmatchTestCase0.getChildren();
				methodcheck_match1 = ModelTestUtils.getAssertMethod( classFnmatchTestCase0Childs, "check_match", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_match1, new String[] {"self", "filename", "pattern", "should_match"} );
			}
			//Function test:test_fnmatch
			{
			IMethod methodtest_fnmatch2;
				IModelElement[] classFnmatchTestCase0Childs = classFnmatchTestCase0.getChildren();
				methodtest_fnmatch2 = ModelTestUtils.getAssertMethod( classFnmatchTestCase0Childs, "test_fnmatch", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fnmatch2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen167( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_hash.py"));

		assertNotNull("Module test_hash.py not found", module);
		assertEquals("test_hash.py", module.getElementName());
		
		//Class test
		{
		IType classHashEqualityTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classHashEqualityTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "HashEqualityTestCase" );
			//Function test:same_hash
			{
			IMethod methodsame_hash1;
				IModelElement[] classHashEqualityTestCase0Childs = classHashEqualityTestCase0.getChildren();
				methodsame_hash1 = ModelTestUtils.getAssertMethod( classHashEqualityTestCase0Childs, "same_hash", 2 );
				ModelTestUtils.assertParameterNames( methodsame_hash1, new String[] {"self", "objlist"} );
			}
			//Function test:test_numeric_literals
			{
			IMethod methodtest_numeric_literals2;
				IModelElement[] classHashEqualityTestCase0Childs = classHashEqualityTestCase0.getChildren();
				methodtest_numeric_literals2 = ModelTestUtils.getAssertMethod( classHashEqualityTestCase0Childs, "test_numeric_literals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_numeric_literals2, new String[] {"self"} );
			}
			//Function test:test_coerced_integers
			{
			IMethod methodtest_coerced_integers3;
				IModelElement[] classHashEqualityTestCase0Childs = classHashEqualityTestCase0.getChildren();
				methodtest_coerced_integers3 = ModelTestUtils.getAssertMethod( classHashEqualityTestCase0Childs, "test_coerced_integers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coerced_integers3, new String[] {"self"} );
			}
			//Function test:test_coerced_floats
			{
			IMethod methodtest_coerced_floats4;
				IModelElement[] classHashEqualityTestCase0Childs = classHashEqualityTestCase0.getChildren();
				methodtest_coerced_floats4 = ModelTestUtils.getAssertMethod( classHashEqualityTestCase0Childs, "test_coerced_floats", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coerced_floats4, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen168( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_stringprep.py"));

		assertNotNull("Module test_stringprep.py not found", module);
		assertEquals("test_stringprep.py", module.getElementName());
		

	}
	public void testModelGen169( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_strftime.py"));

		assertNotNull("Module test_strftime.py not found", module);
		assertEquals("test_strftime.py", module.getElementName());
		
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}
		//Function test:escapestr
		{
		IMethod methodescapestr1;
			IModelElement[] moduleChilds = module.getChildren();
			methodescapestr1 = ModelTestUtils.getAssertMethod( moduleChilds, "escapestr", 2 );
			ModelTestUtils.assertParameterNames( methodescapestr1, new String[] {"text", "ampm"} );
		}
		//Function test:strftest
		{
		IMethod methodstrftest2;
			IModelElement[] moduleChilds = module.getChildren();
			methodstrftest2 = ModelTestUtils.getAssertMethod( moduleChilds, "strftest", 1 );
			ModelTestUtils.assertParameterNames( methodstrftest2, new String[] {"now"} );
		}
		//Function test:fixasctime
		{
		IMethod methodfixasctime3;
			IModelElement[] moduleChilds = module.getChildren();
			methodfixasctime3 = ModelTestUtils.getAssertMethod( moduleChilds, "fixasctime", 1 );
			ModelTestUtils.assertParameterNames( methodfixasctime3, new String[] {"s"} );
		}

	}
	public void testModelGen170( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_grp.py"));

		assertNotNull("Module test_grp.py not found", module);
		assertEquals("test_grp.py", module.getElementName());
		
		//Class test
		{
		IType classGroupDatabaseTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classGroupDatabaseTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "GroupDatabaseTestCase" );
			//Function test:check_value
			{
			IMethod methodcheck_value1;
				IModelElement[] classGroupDatabaseTestCase0Childs = classGroupDatabaseTestCase0.getChildren();
				methodcheck_value1 = ModelTestUtils.getAssertMethod( classGroupDatabaseTestCase0Childs, "check_value", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_value1, new String[] {"self", "value"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values2;
				IModelElement[] classGroupDatabaseTestCase0Childs = classGroupDatabaseTestCase0.getChildren();
				methodtest_values2 = ModelTestUtils.getAssertMethod( classGroupDatabaseTestCase0Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values2, new String[] {"self"} );
			}
			//Function test:test_errors
			{
			IMethod methodtest_errors3;
				IModelElement[] classGroupDatabaseTestCase0Childs = classGroupDatabaseTestCase0.getChildren();
				methodtest_errors3 = ModelTestUtils.getAssertMethod( classGroupDatabaseTestCase0Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors3, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen171( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_gettext.py"));

		assertNotNull("Module test_gettext.py not found", module);
		assertEquals("test_gettext.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "GNU_MO_DATA");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "UMO_DATA");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MMO_DATA");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LOCALEDIR");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MOFILE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "UMOFILE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "MMOFILE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LANG");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LANG");
		}
		//Class test
		{
		IType classGettextBaseTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classGettextBaseTest0 = ModelTestUtils.getAssertClass( moduleChilds, "GettextBaseTest" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classGettextBaseTest0Childs = classGettextBaseTest0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classGettextBaseTest0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classGettextBaseTest0Childs = classGettextBaseTest0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classGettextBaseTest0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classGettextTestCase13;
			IModelElement[] moduleChilds = module.getChildren();
			classGettextTestCase13 = ModelTestUtils.getAssertClass( moduleChilds, "GettextTestCase1" );
			//Function test:setUp
			{
			IMethod methodsetUp4;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodsetUp4 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp4, new String[] {"self"} );
			}
			//Function test:test_some_translations
			{
			IMethod methodtest_some_translations5;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodtest_some_translations5 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "test_some_translations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_some_translations5, new String[] {"self"} );
			}
			//Function test:test_double_quotes
			{
			IMethod methodtest_double_quotes6;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodtest_double_quotes6 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "test_double_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_double_quotes6, new String[] {"self"} );
			}
			//Function test:test_triple_single_quotes
			{
			IMethod methodtest_triple_single_quotes7;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodtest_triple_single_quotes7 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "test_triple_single_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_triple_single_quotes7, new String[] {"self"} );
			}
			//Function test:test_triple_double_quotes
			{
			IMethod methodtest_triple_double_quotes8;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodtest_triple_double_quotes8 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "test_triple_double_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_triple_double_quotes8, new String[] {"self"} );
			}
			//Function test:test_multiline_strings
			{
			IMethod methodtest_multiline_strings9;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodtest_multiline_strings9 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "test_multiline_strings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiline_strings9, new String[] {"self"} );
			}
			//Function test:test_the_alternative_interface
			{
			IMethod methodtest_the_alternative_interface10;
				IModelElement[] classGettextTestCase13Childs = classGettextTestCase13.getChildren();
				methodtest_the_alternative_interface10 = ModelTestUtils.getAssertMethod( classGettextTestCase13Childs, "test_the_alternative_interface", 1 );
				ModelTestUtils.assertParameterNames( methodtest_the_alternative_interface10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classGettextTestCase211;
			IModelElement[] moduleChilds = module.getChildren();
			classGettextTestCase211 = ModelTestUtils.getAssertClass( moduleChilds, "GettextTestCase2" );
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
			}
			//Function test:test_bindtextdomain
			{
			IMethod methodtest_bindtextdomain13;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_bindtextdomain13 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_bindtextdomain", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bindtextdomain13, new String[] {"self"} );
			}
			//Function test:test_textdomain
			{
			IMethod methodtest_textdomain14;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_textdomain14 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_textdomain", 1 );
				ModelTestUtils.assertParameterNames( methodtest_textdomain14, new String[] {"self"} );
			}
			//Function test:test_some_translations
			{
			IMethod methodtest_some_translations15;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_some_translations15 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_some_translations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_some_translations15, new String[] {"self"} );
			}
			//Function test:test_double_quotes
			{
			IMethod methodtest_double_quotes16;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_double_quotes16 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_double_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_double_quotes16, new String[] {"self"} );
			}
			//Function test:test_triple_single_quotes
			{
			IMethod methodtest_triple_single_quotes17;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_triple_single_quotes17 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_triple_single_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_triple_single_quotes17, new String[] {"self"} );
			}
			//Function test:test_triple_double_quotes
			{
			IMethod methodtest_triple_double_quotes18;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_triple_double_quotes18 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_triple_double_quotes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_triple_double_quotes18, new String[] {"self"} );
			}
			//Function test:test_multiline_strings
			{
			IMethod methodtest_multiline_strings19;
				IModelElement[] classGettextTestCase211Childs = classGettextTestCase211.getChildren();
				methodtest_multiline_strings19 = ModelTestUtils.getAssertMethod( classGettextTestCase211Childs, "test_multiline_strings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiline_strings19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPluralFormsTestCase20;
			IModelElement[] moduleChilds = module.getChildren();
			classPluralFormsTestCase20 = ModelTestUtils.getAssertClass( moduleChilds, "PluralFormsTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp21;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodsetUp21 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp21, new String[] {"self"} );
			}
			//Function test:test_plural_forms1
			{
			IMethod methodtest_plural_forms122;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_plural_forms122 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_plural_forms1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_plural_forms122, new String[] {"self"} );
			}
			//Function test:test_plural_forms2
			{
			IMethod methodtest_plural_forms223;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_plural_forms223 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_plural_forms2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_plural_forms223, new String[] {"self"} );
			}
			//Function test:test_hu
			{
			IMethod methodtest_hu24;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_hu24 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_hu", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hu24, new String[] {"self"} );
			}
			//Function test:test_de
			{
			IMethod methodtest_de25;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_de25 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_de", 1 );
				ModelTestUtils.assertParameterNames( methodtest_de25, new String[] {"self"} );
			}
			//Function test:test_fr
			{
			IMethod methodtest_fr26;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_fr26 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_fr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fr26, new String[] {"self"} );
			}
			//Function test:test_gd
			{
			IMethod methodtest_gd27;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_gd27 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_gd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gd27, new String[] {"self"} );
			}
			//Function test:test_gd2
			{
			IMethod methodtest_gd228;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_gd228 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_gd2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gd228, new String[] {"self"} );
			}
			//Function test:test_lt
			{
			IMethod methodtest_lt29;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_lt29 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_lt", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lt29, new String[] {"self"} );
			}
			//Function test:test_ru
			{
			IMethod methodtest_ru30;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_ru30 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_ru", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ru30, new String[] {"self"} );
			}
			//Function test:test_pl
			{
			IMethod methodtest_pl31;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_pl31 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_pl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pl31, new String[] {"self"} );
			}
			//Function test:test_sl
			{
			IMethod methodtest_sl32;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_sl32 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_sl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sl32, new String[] {"self"} );
			}
			//Function test:test_security
			{
			IMethod methodtest_security33;
				IModelElement[] classPluralFormsTestCase20Childs = classPluralFormsTestCase20.getChildren();
				methodtest_security33 = ModelTestUtils.getAssertMethod( classPluralFormsTestCase20Childs, "test_security", 1 );
				ModelTestUtils.assertParameterNames( methodtest_security33, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnicodeTranslationsTest34;
			IModelElement[] moduleChilds = module.getChildren();
			classUnicodeTranslationsTest34 = ModelTestUtils.getAssertClass( moduleChilds, "UnicodeTranslationsTest" );
			//Function test:setUp
			{
			IMethod methodsetUp35;
				IModelElement[] classUnicodeTranslationsTest34Childs = classUnicodeTranslationsTest34.getChildren();
				methodsetUp35 = ModelTestUtils.getAssertMethod( classUnicodeTranslationsTest34Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp35, new String[] {"self"} );
			}
			//Function test:test_unicode_msgid
			{
			IMethod methodtest_unicode_msgid36;
				IModelElement[] classUnicodeTranslationsTest34Childs = classUnicodeTranslationsTest34.getChildren();
				methodtest_unicode_msgid36 = ModelTestUtils.getAssertMethod( classUnicodeTranslationsTest34Childs, "test_unicode_msgid", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_msgid36, new String[] {"self"} );
			}
			//Function test:test_unicode_msgstr
			{
			IMethod methodtest_unicode_msgstr37;
				IModelElement[] classUnicodeTranslationsTest34Childs = classUnicodeTranslationsTest34.getChildren();
				methodtest_unicode_msgstr37 = ModelTestUtils.getAssertMethod( classUnicodeTranslationsTest34Childs, "test_unicode_msgstr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode_msgstr37, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWeirdMetadataTest38;
			IModelElement[] moduleChilds = module.getChildren();
			classWeirdMetadataTest38 = ModelTestUtils.getAssertClass( moduleChilds, "WeirdMetadataTest" );
			//Function test:setUp
			{
			IMethod methodsetUp39;
				IModelElement[] classWeirdMetadataTest38Childs = classWeirdMetadataTest38.getChildren();
				methodsetUp39 = ModelTestUtils.getAssertMethod( classWeirdMetadataTest38Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp39, new String[] {"self"} );
			}
			//Function test:test_weird_metadata
			{
			IMethod methodtest_weird_metadata40;
				IModelElement[] classWeirdMetadataTest38Childs = classWeirdMetadataTest38.getChildren();
				methodtest_weird_metadata40 = ModelTestUtils.getAssertMethod( classWeirdMetadataTest38Childs, "test_weird_metadata", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weird_metadata40, new String[] {"self"} );
			}
		}
		//Function test:suite
		{
		IMethod methodsuite41;
			IModelElement[] moduleChilds = module.getChildren();
			methodsuite41 = ModelTestUtils.getAssertMethod( moduleChilds, "suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen172( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pickle.py"));

		assertNotNull("Module test_pickle.py not found", module);
		assertEquals("test_pickle.py", module.getElementName());
		
		//Class test
		{
		IType classPickleTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classPickleTests0 = ModelTestUtils.getAssertClass( moduleChilds, "PickleTests" );
			//Function test:dumps
			{
			IMethod methoddumps1;
				IModelElement[] classPickleTests0Childs = classPickleTests0.getChildren();
				methoddumps1 = ModelTestUtils.getAssertMethod( classPickleTests0Childs, "dumps", 4 );
				ModelTestUtils.assertParameterNames( methoddumps1, new String[] {"self", "arg", "proto", "fast"} );
			}
			//Function test:loads
			{
			IMethod methodloads2;
				IModelElement[] classPickleTests0Childs = classPickleTests0.getChildren();
				methodloads2 = ModelTestUtils.getAssertMethod( classPickleTests0Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads2, new String[] {"self", "buf"} );
			}
			{
				IModelElement[] classPickleTests0Childs = classPickleTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPickleTests0Childs, "module");
			}
			{
				IModelElement[] classPickleTests0Childs = classPickleTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPickleTests0Childs, "error");
			}
		}
		//Class test
		{
		IType classPicklerTests3;
			IModelElement[] moduleChilds = module.getChildren();
			classPicklerTests3 = ModelTestUtils.getAssertClass( moduleChilds, "PicklerTests" );
			{
				IModelElement[] classPicklerTests3Childs = classPicklerTests3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPicklerTests3Childs, "error");
			}
			//Function test:dumps
			{
			IMethod methoddumps4;
				IModelElement[] classPicklerTests3Childs = classPicklerTests3.getChildren();
				methoddumps4 = ModelTestUtils.getAssertMethod( classPicklerTests3Childs, "dumps", 4 );
				ModelTestUtils.assertParameterNames( methoddumps4, new String[] {"self", "arg", "proto", "fast"} );
			}
			//Function test:loads
			{
			IMethod methodloads5;
				IModelElement[] classPicklerTests3Childs = classPicklerTests3.getChildren();
				methodloads5 = ModelTestUtils.getAssertMethod( classPicklerTests3Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads5, new String[] {"self", "buf"} );
			}
		}
		//Class test
		{
		IType classPersPicklerTests6;
			IModelElement[] moduleChilds = module.getChildren();
			classPersPicklerTests6 = ModelTestUtils.getAssertClass( moduleChilds, "PersPicklerTests" );
			//Function test:dumps
			{
			IMethod methoddumps7;
				IModelElement[] classPersPicklerTests6Childs = classPersPicklerTests6.getChildren();
				methoddumps7 = ModelTestUtils.getAssertMethod( classPersPicklerTests6Childs, "dumps", 4 );
				ModelTestUtils.assertParameterNames( methoddumps7, new String[] {"self", "arg", "proto", "fast"} );
				//Class test
				{
				IType classPersPickler8;
					IModelElement[] methoddumps7Childs = methoddumps7.getChildren();
					classPersPickler8 = ModelTestUtils.getAssertClass( methoddumps7Childs, "PersPickler" );
					//Function test:persistent_id
					{
					IMethod methodpersistent_id9;
						IModelElement[] classPersPickler8Childs = classPersPickler8.getChildren();
						methodpersistent_id9 = ModelTestUtils.getAssertMethod( classPersPickler8Childs, "persistent_id", 2 );
						ModelTestUtils.assertParameterNames( methodpersistent_id9, new String[] {"subself", "obj"} );
					}
				}
			}
			//Function test:loads
			{
			IMethod methodloads10;
				IModelElement[] classPersPicklerTests6Childs = classPersPicklerTests6.getChildren();
				methodloads10 = ModelTestUtils.getAssertMethod( classPersPicklerTests6Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads10, new String[] {"self", "buf"} );
				//Class test
				{
				IType classPersUnpickler11;
					IModelElement[] methodloads10Childs = methodloads10.getChildren();
					classPersUnpickler11 = ModelTestUtils.getAssertClass( methodloads10Childs, "PersUnpickler" );
					//Function test:persistent_load
					{
					IMethod methodpersistent_load12;
						IModelElement[] classPersUnpickler11Childs = classPersUnpickler11.getChildren();
						methodpersistent_load12 = ModelTestUtils.getAssertMethod( classPersUnpickler11Childs, "persistent_load", 2 );
						ModelTestUtils.assertParameterNames( methodpersistent_load12, new String[] {"subself", "obj"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main13 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen173( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_format.py"));

		assertNotNull("Module test_format.py not found", module);
		assertEquals("test_format.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "overflowok");
		}
		//Function test:testformat
		{
		IMethod methodtestformat0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestformat0 = ModelTestUtils.getAssertMethod( moduleChilds, "testformat", 3 );
			ModelTestUtils.assertParameterNames( methodtestformat0, new String[] {"formatstr", "args", "output"} );
		}
		//Function test:testboth
		{
		IMethod methodtestboth1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestboth1 = ModelTestUtils.getAssertMethod( moduleChilds, "testboth", 2 );
			ModelTestUtils.assertParameterNames( methodtestboth1, new String[] {"formatstr", "args"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "overflowok");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "big");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "big");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "big");
		}
		//Function test:test_exc
		{
		IMethod methodtest_exc2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_exc2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_exc", 4 );
			ModelTestUtils.assertParameterNames( methodtest_exc2, new String[] {"formatstr", "args", "exception", "excmsg"} );
		}

	}
	public void testModelGen174( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("re_tests.py"));

		assertNotNull("Module re_tests.py not found", module);
		assertEquals("re_tests.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "benchmarks");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "u");
		}

	}
	public void testModelGen175( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_tempfile.py"));

		assertNotNull("Module test_tempfile.py not found", module);
		assertEquals("test_tempfile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_stat");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_stat");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_textmode");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "has_spawnl");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEST_FILES");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEST_FILES");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEST_FILES");
		}
		//Class test
		{
		IType classTC0;
			IModelElement[] moduleChilds = module.getChildren();
			classTC0 = ModelTestUtils.getAssertClass( moduleChilds, "TC" );
			{
				IModelElement[] classTC0Childs = classTC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTC0Childs, "str_check");
			}
			//Function test:failOnException
			{
			IMethod methodfailOnException1;
				IModelElement[] classTC0Childs = classTC0.getChildren();
				methodfailOnException1 = ModelTestUtils.getAssertMethod( classTC0Childs, "failOnException", 3 );
				ModelTestUtils.assertParameterNames( methodfailOnException1, new String[] {"self", "what", "ei"} );
			}
			//Function test:nameCheck
			{
			IMethod methodnameCheck2;
				IModelElement[] classTC0Childs = classTC0.getChildren();
				methodnameCheck2 = ModelTestUtils.getAssertMethod( classTC0Childs, "nameCheck", 5 );
				ModelTestUtils.assertParameterNames( methodnameCheck2, new String[] {"self", "name", "dir", "pre", "suf"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_classes");
		}
		//Class test
		{
		IType classtest_exports3;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_exports3 = ModelTestUtils.getAssertClass( moduleChilds, "test_exports" );
			//Function test:test_exports
			{
			IMethod methodtest_exports4;
				IModelElement[] classtest_exports3Childs = classtest_exports3.getChildren();
				methodtest_exports4 = ModelTestUtils.getAssertMethod( classtest_exports3Childs, "test_exports", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exports4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest__RandomNameSequence5;
			IModelElement[] moduleChilds = module.getChildren();
			classtest__RandomNameSequence5 = ModelTestUtils.getAssertClass( moduleChilds, "test__RandomNameSequence" );
			//Function test:setUp
			{
			IMethod methodsetUp6;
				IModelElement[] classtest__RandomNameSequence5Childs = classtest__RandomNameSequence5.getChildren();
				methodsetUp6 = ModelTestUtils.getAssertMethod( classtest__RandomNameSequence5Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp6, new String[] {"self"} );
			}
			//Function test:test_get_six_char_str
			{
			IMethod methodtest_get_six_char_str7;
				IModelElement[] classtest__RandomNameSequence5Childs = classtest__RandomNameSequence5.getChildren();
				methodtest_get_six_char_str7 = ModelTestUtils.getAssertMethod( classtest__RandomNameSequence5Childs, "test_get_six_char_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_six_char_str7, new String[] {"self"} );
			}
			//Function test:test_many
			{
			IMethod methodtest_many8;
				IModelElement[] classtest__RandomNameSequence5Childs = classtest__RandomNameSequence5.getChildren();
				methodtest_many8 = ModelTestUtils.getAssertMethod( classtest__RandomNameSequence5Childs, "test_many", 1 );
				ModelTestUtils.assertParameterNames( methodtest_many8, new String[] {"self"} );
			}
			//Function test:test_supports_iter
			{
			IMethod methodtest_supports_iter9;
				IModelElement[] classtest__RandomNameSequence5Childs = classtest__RandomNameSequence5.getChildren();
				methodtest_supports_iter9 = ModelTestUtils.getAssertMethod( classtest__RandomNameSequence5Childs, "test_supports_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_supports_iter9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest__candidate_tempdir_list10;
			IModelElement[] moduleChilds = module.getChildren();
			classtest__candidate_tempdir_list10 = ModelTestUtils.getAssertClass( moduleChilds, "test__candidate_tempdir_list" );
			//Function test:test_nonempty_list
			{
			IMethod methodtest_nonempty_list11;
				IModelElement[] classtest__candidate_tempdir_list10Childs = classtest__candidate_tempdir_list10.getChildren();
				methodtest_nonempty_list11 = ModelTestUtils.getAssertMethod( classtest__candidate_tempdir_list10Childs, "test_nonempty_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonempty_list11, new String[] {"self"} );
			}
			//Function test:test_wanted_dirs
			{
			IMethod methodtest_wanted_dirs12;
				IModelElement[] classtest__candidate_tempdir_list10Childs = classtest__candidate_tempdir_list10.getChildren();
				methodtest_wanted_dirs12 = ModelTestUtils.getAssertMethod( classtest__candidate_tempdir_list10Childs, "test_wanted_dirs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_wanted_dirs12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest__get_candidate_names13;
			IModelElement[] moduleChilds = module.getChildren();
			classtest__get_candidate_names13 = ModelTestUtils.getAssertClass( moduleChilds, "test__get_candidate_names" );
			//Function test:test_retval
			{
			IMethod methodtest_retval14;
				IModelElement[] classtest__get_candidate_names13Childs = classtest__get_candidate_names13.getChildren();
				methodtest_retval14 = ModelTestUtils.getAssertMethod( classtest__get_candidate_names13Childs, "test_retval", 1 );
				ModelTestUtils.assertParameterNames( methodtest_retval14, new String[] {"self"} );
			}
			//Function test:test_same_thing
			{
			IMethod methodtest_same_thing15;
				IModelElement[] classtest__get_candidate_names13Childs = classtest__get_candidate_names13.getChildren();
				methodtest_same_thing15 = ModelTestUtils.getAssertMethod( classtest__get_candidate_names13Childs, "test_same_thing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_same_thing15, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest__mkstemp_inner16;
			IModelElement[] moduleChilds = module.getChildren();
			classtest__mkstemp_inner16 = ModelTestUtils.getAssertClass( moduleChilds, "test__mkstemp_inner" );
			//Class test
			{
			IType classmkstemped17;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				classmkstemped17 = ModelTestUtils.getAssertClass( classtest__mkstemp_inner16Childs, "mkstemped" );
				{
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmkstemped17Childs, "_bflags");
				}
				{
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmkstemped17Childs, "_tflags");
				}
				{
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmkstemped17Childs, "_close");
				}
				{
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmkstemped17Childs, "_unlink");
				}
				//Function test:__init__
				{
				IMethod method__init__18;
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					method__init__18 = ModelTestUtils.getAssertMethod( classmkstemped17Childs, "__init__", 5 );
					ModelTestUtils.assertParameterNames( method__init__18, new String[] {"self", "dir", "pre", "suf", "bin"} );
				}
				//Function test:write
				{
				IMethod methodwrite19;
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					methodwrite19 = ModelTestUtils.getAssertMethod( classmkstemped17Childs, "write", 2 );
					ModelTestUtils.assertParameterNames( methodwrite19, new String[] {"self", "str"} );
				}
				//Function test:__del__
				{
				IMethod method__del__20;
					IModelElement[] classmkstemped17Childs = classmkstemped17.getChildren();
					method__del__20 = ModelTestUtils.getAssertMethod( classmkstemped17Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__20, new String[] {"self"} );
				}
			}
			//Function test:do_create
			{
			IMethod methoddo_create21;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methoddo_create21 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "do_create", 5 );
				ModelTestUtils.assertParameterNames( methoddo_create21, new String[] {"self", "dir", "pre", "suf", "bin"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic22;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methodtest_basic22 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic22, new String[] {"self"} );
			}
			//Function test:test_basic_many
			{
			IMethod methodtest_basic_many23;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methodtest_basic_many23 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "test_basic_many", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_many23, new String[] {"self"} );
			}
			//Function test:test_choose_directory
			{
			IMethod methodtest_choose_directory24;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methodtest_choose_directory24 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "test_choose_directory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_choose_directory24, new String[] {"self"} );
			}
			//Function test:test_file_mode
			{
			IMethod methodtest_file_mode25;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methodtest_file_mode25 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "test_file_mode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file_mode25, new String[] {"self"} );
			}
			//Function test:test_noinherit
			{
			IMethod methodtest_noinherit26;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methodtest_noinherit26 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "test_noinherit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_noinherit26, new String[] {"self"} );
			}
			//Function test:test_textmode
			{
			IMethod methodtest_textmode27;
				IModelElement[] classtest__mkstemp_inner16Childs = classtest__mkstemp_inner16.getChildren();
				methodtest_textmode27 = ModelTestUtils.getAssertMethod( classtest__mkstemp_inner16Childs, "test_textmode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_textmode27, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_gettempprefix28;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_gettempprefix28 = ModelTestUtils.getAssertClass( moduleChilds, "test_gettempprefix" );
			//Function test:test_sane_template
			{
			IMethod methodtest_sane_template29;
				IModelElement[] classtest_gettempprefix28Childs = classtest_gettempprefix28.getChildren();
				methodtest_sane_template29 = ModelTestUtils.getAssertMethod( classtest_gettempprefix28Childs, "test_sane_template", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sane_template29, new String[] {"self"} );
			}
			//Function test:test_usable_template
			{
			IMethod methodtest_usable_template30;
				IModelElement[] classtest_gettempprefix28Childs = classtest_gettempprefix28.getChildren();
				methodtest_usable_template30 = ModelTestUtils.getAssertMethod( classtest_gettempprefix28Childs, "test_usable_template", 1 );
				ModelTestUtils.assertParameterNames( methodtest_usable_template30, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_gettempdir31;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_gettempdir31 = ModelTestUtils.getAssertClass( moduleChilds, "test_gettempdir" );
			//Function test:test_directory_exists
			{
			IMethod methodtest_directory_exists32;
				IModelElement[] classtest_gettempdir31Childs = classtest_gettempdir31.getChildren();
				methodtest_directory_exists32 = ModelTestUtils.getAssertMethod( classtest_gettempdir31Childs, "test_directory_exists", 1 );
				ModelTestUtils.assertParameterNames( methodtest_directory_exists32, new String[] {"self"} );
			}
			//Function test:test_directory_writable
			{
			IMethod methodtest_directory_writable33;
				IModelElement[] classtest_gettempdir31Childs = classtest_gettempdir31.getChildren();
				methodtest_directory_writable33 = ModelTestUtils.getAssertMethod( classtest_gettempdir31Childs, "test_directory_writable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_directory_writable33, new String[] {"self"} );
			}
			//Function test:test_same_thing
			{
			IMethod methodtest_same_thing34;
				IModelElement[] classtest_gettempdir31Childs = classtest_gettempdir31.getChildren();
				methodtest_same_thing34 = ModelTestUtils.getAssertMethod( classtest_gettempdir31Childs, "test_same_thing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_same_thing34, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_mkstemp35;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_mkstemp35 = ModelTestUtils.getAssertClass( moduleChilds, "test_mkstemp" );
			//Function test:do_create
			{
			IMethod methoddo_create36;
				IModelElement[] classtest_mkstemp35Childs = classtest_mkstemp35.getChildren();
				methoddo_create36 = ModelTestUtils.getAssertMethod( classtest_mkstemp35Childs, "do_create", 4 );
				ModelTestUtils.assertParameterNames( methoddo_create36, new String[] {"self", "dir", "pre", "suf"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic37;
				IModelElement[] classtest_mkstemp35Childs = classtest_mkstemp35.getChildren();
				methodtest_basic37 = ModelTestUtils.getAssertMethod( classtest_mkstemp35Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic37, new String[] {"self"} );
			}
			//Function test:test_choose_directory
			{
			IMethod methodtest_choose_directory38;
				IModelElement[] classtest_mkstemp35Childs = classtest_mkstemp35.getChildren();
				methodtest_choose_directory38 = ModelTestUtils.getAssertMethod( classtest_mkstemp35Childs, "test_choose_directory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_choose_directory38, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_mkdtemp39;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_mkdtemp39 = ModelTestUtils.getAssertClass( moduleChilds, "test_mkdtemp" );
			//Function test:do_create
			{
			IMethod methoddo_create40;
				IModelElement[] classtest_mkdtemp39Childs = classtest_mkdtemp39.getChildren();
				methoddo_create40 = ModelTestUtils.getAssertMethod( classtest_mkdtemp39Childs, "do_create", 4 );
				ModelTestUtils.assertParameterNames( methoddo_create40, new String[] {"self", "dir", "pre", "suf"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic41;
				IModelElement[] classtest_mkdtemp39Childs = classtest_mkdtemp39.getChildren();
				methodtest_basic41 = ModelTestUtils.getAssertMethod( classtest_mkdtemp39Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic41, new String[] {"self"} );
			}
			//Function test:test_basic_many
			{
			IMethod methodtest_basic_many42;
				IModelElement[] classtest_mkdtemp39Childs = classtest_mkdtemp39.getChildren();
				methodtest_basic_many42 = ModelTestUtils.getAssertMethod( classtest_mkdtemp39Childs, "test_basic_many", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_many42, new String[] {"self"} );
			}
			//Function test:test_choose_directory
			{
			IMethod methodtest_choose_directory43;
				IModelElement[] classtest_mkdtemp39Childs = classtest_mkdtemp39.getChildren();
				methodtest_choose_directory43 = ModelTestUtils.getAssertMethod( classtest_mkdtemp39Childs, "test_choose_directory", 1 );
				ModelTestUtils.assertParameterNames( methodtest_choose_directory43, new String[] {"self"} );
			}
			//Function test:test_mode
			{
			IMethod methodtest_mode44;
				IModelElement[] classtest_mkdtemp39Childs = classtest_mkdtemp39.getChildren();
				methodtest_mode44 = ModelTestUtils.getAssertMethod( classtest_mkdtemp39Childs, "test_mode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mode44, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_mktemp45;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_mktemp45 = ModelTestUtils.getAssertClass( moduleChilds, "test_mktemp" );
			//Function test:setUp
			{
			IMethod methodsetUp46;
				IModelElement[] classtest_mktemp45Childs = classtest_mktemp45.getChildren();
				methodsetUp46 = ModelTestUtils.getAssertMethod( classtest_mktemp45Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp46, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown47;
				IModelElement[] classtest_mktemp45Childs = classtest_mktemp45.getChildren();
				methodtearDown47 = ModelTestUtils.getAssertMethod( classtest_mktemp45Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown47, new String[] {"self"} );
			}
			//Class test
			{
			IType classmktemped48;
				IModelElement[] classtest_mktemp45Childs = classtest_mktemp45.getChildren();
				classmktemped48 = ModelTestUtils.getAssertClass( classtest_mktemp45Childs, "mktemped" );
				{
					IModelElement[] classmktemped48Childs = classmktemped48.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmktemped48Childs, "_unlink");
				}
				{
					IModelElement[] classmktemped48Childs = classmktemped48.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classmktemped48Childs, "_bflags");
				}
				//Function test:__init__
				{
				IMethod method__init__49;
					IModelElement[] classmktemped48Childs = classmktemped48.getChildren();
					method__init__49 = ModelTestUtils.getAssertMethod( classmktemped48Childs, "__init__", 4 );
					ModelTestUtils.assertParameterNames( method__init__49, new String[] {"self", "dir", "pre", "suf"} );
				}
				//Function test:__del__
				{
				IMethod method__del__50;
					IModelElement[] classmktemped48Childs = classmktemped48.getChildren();
					method__del__50 = ModelTestUtils.getAssertMethod( classmktemped48Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__50, new String[] {"self"} );
				}
			}
			//Function test:do_create
			{
			IMethod methoddo_create51;
				IModelElement[] classtest_mktemp45Childs = classtest_mktemp45.getChildren();
				methoddo_create51 = ModelTestUtils.getAssertMethod( classtest_mktemp45Childs, "do_create", 3 );
				ModelTestUtils.assertParameterNames( methoddo_create51, new String[] {"self", "pre", "suf"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic52;
				IModelElement[] classtest_mktemp45Childs = classtest_mktemp45.getChildren();
				methodtest_basic52 = ModelTestUtils.getAssertMethod( classtest_mktemp45Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic52, new String[] {"self"} );
			}
			//Function test:test_many
			{
			IMethod methodtest_many53;
				IModelElement[] classtest_mktemp45Childs = classtest_mktemp45.getChildren();
				methodtest_many53 = ModelTestUtils.getAssertMethod( classtest_mktemp45Childs, "test_many", 1 );
				ModelTestUtils.assertParameterNames( methodtest_many53, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_NamedTemporaryFile54;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_NamedTemporaryFile54 = ModelTestUtils.getAssertClass( moduleChilds, "test_NamedTemporaryFile" );
			//Function test:do_create
			{
			IMethod methoddo_create55;
				IModelElement[] classtest_NamedTemporaryFile54Childs = classtest_NamedTemporaryFile54.getChildren();
				methoddo_create55 = ModelTestUtils.getAssertMethod( classtest_NamedTemporaryFile54Childs, "do_create", 4 );
				ModelTestUtils.assertParameterNames( methoddo_create55, new String[] {"self", "dir", "pre", "suf"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic56;
				IModelElement[] classtest_NamedTemporaryFile54Childs = classtest_NamedTemporaryFile54.getChildren();
				methodtest_basic56 = ModelTestUtils.getAssertMethod( classtest_NamedTemporaryFile54Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic56, new String[] {"self"} );
			}
			//Function test:test_creates_named
			{
			IMethod methodtest_creates_named57;
				IModelElement[] classtest_NamedTemporaryFile54Childs = classtest_NamedTemporaryFile54.getChildren();
				methodtest_creates_named57 = ModelTestUtils.getAssertMethod( classtest_NamedTemporaryFile54Childs, "test_creates_named", 1 );
				ModelTestUtils.assertParameterNames( methodtest_creates_named57, new String[] {"self"} );
			}
			//Function test:test_del_on_close
			{
			IMethod methodtest_del_on_close58;
				IModelElement[] classtest_NamedTemporaryFile54Childs = classtest_NamedTemporaryFile54.getChildren();
				methodtest_del_on_close58 = ModelTestUtils.getAssertMethod( classtest_NamedTemporaryFile54Childs, "test_del_on_close", 1 );
				ModelTestUtils.assertParameterNames( methodtest_del_on_close58, new String[] {"self"} );
			}
			//Function test:test_multiple_close
			{
			IMethod methodtest_multiple_close59;
				IModelElement[] classtest_NamedTemporaryFile54Childs = classtest_NamedTemporaryFile54.getChildren();
				methodtest_multiple_close59 = ModelTestUtils.getAssertMethod( classtest_NamedTemporaryFile54Childs, "test_multiple_close", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiple_close59, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classtest_TemporaryFile60;
			IModelElement[] moduleChilds = module.getChildren();
			classtest_TemporaryFile60 = ModelTestUtils.getAssertClass( moduleChilds, "test_TemporaryFile" );
			//Function test:test_basic
			{
			IMethod methodtest_basic61;
				IModelElement[] classtest_TemporaryFile60Childs = classtest_TemporaryFile60.getChildren();
				methodtest_basic61 = ModelTestUtils.getAssertMethod( classtest_TemporaryFile60Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic61, new String[] {"self"} );
			}
			//Function test:test_has_no_name
			{
			IMethod methodtest_has_no_name62;
				IModelElement[] classtest_TemporaryFile60Childs = classtest_TemporaryFile60.getChildren();
				methodtest_has_no_name62 = ModelTestUtils.getAssertMethod( classtest_TemporaryFile60Childs, "test_has_no_name", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_no_name62, new String[] {"self"} );
			}
			//Function test:test_multiple_close
			{
			IMethod methodtest_multiple_close63;
				IModelElement[] classtest_TemporaryFile60Childs = classtest_TemporaryFile60.getChildren();
				methodtest_multiple_close63 = ModelTestUtils.getAssertMethod( classtest_TemporaryFile60Childs, "test_multiple_close", 1 );
				ModelTestUtils.assertParameterNames( methodtest_multiple_close63, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main64;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main64 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen176( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_urllib2.py"));

		assertNotNull("Module test_urllib2.py not found", module);
		assertEquals("test_urllib2.py", module.getElementName());
		
		//Class test
		{
		IType classTrivialTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classTrivialTests0 = ModelTestUtils.getAssertClass( moduleChilds, "TrivialTests" );
			//Function test:test_trivial
			{
			IMethod methodtest_trivial1;
				IModelElement[] classTrivialTests0Childs = classTrivialTests0.getChildren();
				methodtest_trivial1 = ModelTestUtils.getAssertMethod( classTrivialTests0Childs, "test_trivial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_trivial1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMockOpener2;
			IModelElement[] moduleChilds = module.getChildren();
			classMockOpener2 = ModelTestUtils.getAssertClass( moduleChilds, "MockOpener" );
			{
				IModelElement[] classMockOpener2Childs = classMockOpener2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMockOpener2Childs, "addheaders");
			}
			//Function test:open
			{
			IMethod methodopen3;
				IModelElement[] classMockOpener2Childs = classMockOpener2.getChildren();
				methodopen3 = ModelTestUtils.getAssertMethod( classMockOpener2Childs, "open", 3 );
				ModelTestUtils.assertParameterNames( methodopen3, new String[] {"self", "req", "data"} );
			}
			//Function test:error
			{
			IMethod methoderror4;
				IModelElement[] classMockOpener2Childs = classMockOpener2.getChildren();
				methoderror4 = ModelTestUtils.getAssertMethod( classMockOpener2Childs, "error", 3 );
				ModelTestUtils.assertParameterNames( methoderror4, new String[] {"self", "proto", "args"} );
			}
		}
		//Class test
		{
		IType classMockFile5;
			IModelElement[] moduleChilds = module.getChildren();
			classMockFile5 = ModelTestUtils.getAssertClass( moduleChilds, "MockFile" );
			//Function test:read
			{
			IMethod methodread6;
				IModelElement[] classMockFile5Childs = classMockFile5.getChildren();
				methodread6 = ModelTestUtils.getAssertMethod( classMockFile5Childs, "read", 2 );
				ModelTestUtils.assertParameterNames( methodread6, new String[] {"self", "count"} );
			}
			//Function test:readline
			{
			IMethod methodreadline7;
				IModelElement[] classMockFile5Childs = classMockFile5.getChildren();
				methodreadline7 = ModelTestUtils.getAssertMethod( classMockFile5Childs, "readline", 2 );
				ModelTestUtils.assertParameterNames( methodreadline7, new String[] {"self", "count"} );
			}
			//Function test:close
			{
			IMethod methodclose8;
				IModelElement[] classMockFile5Childs = classMockFile5.getChildren();
				methodclose8 = ModelTestUtils.getAssertMethod( classMockFile5Childs, "close", 1 );
				ModelTestUtils.assertParameterNames( methodclose8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMockHeaders9;
			IModelElement[] moduleChilds = module.getChildren();
			classMockHeaders9 = ModelTestUtils.getAssertClass( moduleChilds, "MockHeaders" );
			//Function test:getheaders
			{
			IMethod methodgetheaders10;
				IModelElement[] classMockHeaders9Childs = classMockHeaders9.getChildren();
				methodgetheaders10 = ModelTestUtils.getAssertMethod( classMockHeaders9Childs, "getheaders", 2 );
				ModelTestUtils.assertParameterNames( methodgetheaders10, new String[] {"self", "name"} );
			}
		}
		//Class test
		{
		IType classMockResponse11;
			IModelElement[] moduleChilds = module.getChildren();
			classMockResponse11 = ModelTestUtils.getAssertClass( moduleChilds, "MockResponse" );
			//Function test:__init__
			{
			IMethod method__init__12;
				IModelElement[] classMockResponse11Childs = classMockResponse11.getChildren();
				method__init__12 = ModelTestUtils.getAssertMethod( classMockResponse11Childs, "__init__", 6 );
				ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self", "code", "msg", "headers", "data", "url"} );
			}
			//Function test:info
			{
			IMethod methodinfo13;
				IModelElement[] classMockResponse11Childs = classMockResponse11.getChildren();
				methodinfo13 = ModelTestUtils.getAssertMethod( classMockResponse11Childs, "info", 1 );
				ModelTestUtils.assertParameterNames( methodinfo13, new String[] {"self"} );
			}
			//Function test:geturl
			{
			IMethod methodgeturl14;
				IModelElement[] classMockResponse11Childs = classMockResponse11.getChildren();
				methodgeturl14 = ModelTestUtils.getAssertMethod( classMockResponse11Childs, "geturl", 1 );
				ModelTestUtils.assertParameterNames( methodgeturl14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMockCookieJar15;
			IModelElement[] moduleChilds = module.getChildren();
			classMockCookieJar15 = ModelTestUtils.getAssertClass( moduleChilds, "MockCookieJar" );
			//Function test:add_cookie_header
			{
			IMethod methodadd_cookie_header16;
				IModelElement[] classMockCookieJar15Childs = classMockCookieJar15.getChildren();
				methodadd_cookie_header16 = ModelTestUtils.getAssertMethod( classMockCookieJar15Childs, "add_cookie_header", 2 );
				ModelTestUtils.assertParameterNames( methodadd_cookie_header16, new String[] {"self", "request"} );
			}
			//Function test:extract_cookies
			{
			IMethod methodextract_cookies17;
				IModelElement[] classMockCookieJar15Childs = classMockCookieJar15.getChildren();
				methodextract_cookies17 = ModelTestUtils.getAssertMethod( classMockCookieJar15Childs, "extract_cookies", 3 );
				ModelTestUtils.assertParameterNames( methodextract_cookies17, new String[] {"self", "response", "request"} );
			}
		}
		//Class test
		{
		IType classFakeMethod18;
			IModelElement[] moduleChilds = module.getChildren();
			classFakeMethod18 = ModelTestUtils.getAssertClass( moduleChilds, "FakeMethod" );
			//Function test:__init__
			{
			IMethod method__init__19;
				IModelElement[] classFakeMethod18Childs = classFakeMethod18.getChildren();
				method__init__19 = ModelTestUtils.getAssertMethod( classFakeMethod18Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__19, new String[] {"self", "meth_name", "action", "handle"} );
			}
			//Function test:__call__
			{
			IMethod method__call__20;
				IModelElement[] classFakeMethod18Childs = classFakeMethod18.getChildren();
				method__call__20 = ModelTestUtils.getAssertMethod( classFakeMethod18Childs, "__call__", 2 );
				ModelTestUtils.assertParameterNames( method__call__20, new String[] {"self", "args"} );
			}
		}
		//Class test
		{
		IType classMockHandler21;
			IModelElement[] moduleChilds = module.getChildren();
			classMockHandler21 = ModelTestUtils.getAssertClass( moduleChilds, "MockHandler" );
			//Function test:__init__
			{
			IMethod method__init__22;
				IModelElement[] classMockHandler21Childs = classMockHandler21.getChildren();
				method__init__22 = ModelTestUtils.getAssertMethod( classMockHandler21Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__22, new String[] {"self", "methods"} );
			}
			//Function test:_define_methods
			{
			IMethod method_define_methods23;
				IModelElement[] classMockHandler21Childs = classMockHandler21.getChildren();
				method_define_methods23 = ModelTestUtils.getAssertMethod( classMockHandler21Childs, "_define_methods", 2 );
				ModelTestUtils.assertParameterNames( method_define_methods23, new String[] {"self", "methods"} );
			}
			//Function test:handle
			{
			IMethod methodhandle24;
				IModelElement[] classMockHandler21Childs = classMockHandler21.getChildren();
				methodhandle24 = ModelTestUtils.getAssertMethod( classMockHandler21Childs, "handle", 5 );
				ModelTestUtils.assertParameterNames( methodhandle24, new String[] {"self", "fn_name", "action", "args", "kwds"} );
			}
			//Function test:close
			{
			IMethod methodclose25;
				IModelElement[] classMockHandler21Childs = classMockHandler21.getChildren();
				methodclose25 = ModelTestUtils.getAssertMethod( classMockHandler21Childs, "close", 1 );
				ModelTestUtils.assertParameterNames( methodclose25, new String[] {"self"} );
			}
			//Function test:add_parent
			{
			IMethod methodadd_parent26;
				IModelElement[] classMockHandler21Childs = classMockHandler21.getChildren();
				methodadd_parent26 = ModelTestUtils.getAssertMethod( classMockHandler21Childs, "add_parent", 2 );
				ModelTestUtils.assertParameterNames( methodadd_parent26, new String[] {"self", "parent"} );
			}
			//Function test:__lt__
			{
			IMethod method__lt__27;
				IModelElement[] classMockHandler21Childs = classMockHandler21.getChildren();
				method__lt__27 = ModelTestUtils.getAssertMethod( classMockHandler21Childs, "__lt__", 2 );
				ModelTestUtils.assertParameterNames( method__lt__27, new String[] {"self", "other"} );
			}
		}
		//Function test:add_ordered_mock_handlers
		{
		IMethod methodadd_ordered_mock_handlers28;
			IModelElement[] moduleChilds = module.getChildren();
			methodadd_ordered_mock_handlers28 = ModelTestUtils.getAssertMethod( moduleChilds, "add_ordered_mock_handlers", 2 );
			ModelTestUtils.assertParameterNames( methodadd_ordered_mock_handlers28, new String[] {"opener", "meth_spec"} );
			//Class test
			{
			IType classMockHandlerSubclass29;
				IModelElement[] methodadd_ordered_mock_handlers28Childs = methodadd_ordered_mock_handlers28.getChildren();
				classMockHandlerSubclass29 = ModelTestUtils.getAssertClass( methodadd_ordered_mock_handlers28Childs, "MockHandlerSubclass" );
			}
		}
		//Class test
		{
		IType classOpenerDirectorTests30;
			IModelElement[] moduleChilds = module.getChildren();
			classOpenerDirectorTests30 = ModelTestUtils.getAssertClass( moduleChilds, "OpenerDirectorTests" );
			//Function test:test_handled
			{
			IMethod methodtest_handled31;
				IModelElement[] classOpenerDirectorTests30Childs = classOpenerDirectorTests30.getChildren();
				methodtest_handled31 = ModelTestUtils.getAssertMethod( classOpenerDirectorTests30Childs, "test_handled", 1 );
				ModelTestUtils.assertParameterNames( methodtest_handled31, new String[] {"self"} );
			}
			//Function test:test_handler_order
			{
			IMethod methodtest_handler_order32;
				IModelElement[] classOpenerDirectorTests30Childs = classOpenerDirectorTests30.getChildren();
				methodtest_handler_order32 = ModelTestUtils.getAssertMethod( classOpenerDirectorTests30Childs, "test_handler_order", 1 );
				ModelTestUtils.assertParameterNames( methodtest_handler_order32, new String[] {"self"} );
				//Class test
				{
				IType classMockHandlerSubclass33;
					IModelElement[] methodtest_handler_order32Childs = methodtest_handler_order32.getChildren();
					classMockHandlerSubclass33 = ModelTestUtils.getAssertClass( methodtest_handler_order32Childs, "MockHandlerSubclass" );
				}
			}
			//Function test:test_raise
			{
			IMethod methodtest_raise34;
				IModelElement[] classOpenerDirectorTests30Childs = classOpenerDirectorTests30.getChildren();
				methodtest_raise34 = ModelTestUtils.getAssertMethod( classOpenerDirectorTests30Childs, "test_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise34, new String[] {"self"} );
			}
			//Function test:test_http_error
			{
			IMethod methodtest_http_error35;
				IModelElement[] classOpenerDirectorTests30Childs = classOpenerDirectorTests30.getChildren();
				methodtest_http_error35 = ModelTestUtils.getAssertMethod( classOpenerDirectorTests30Childs, "test_http_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http_error35, new String[] {"self"} );
				//Class test
				{
				IType classUnknown36;
					IModelElement[] methodtest_http_error35Childs = methodtest_http_error35.getChildren();
					classUnknown36 = ModelTestUtils.getAssertClass( methodtest_http_error35Childs, "Unknown" );
					//Function test:__eq__
					{
					IMethod method__eq__37;
						IModelElement[] classUnknown36Childs = classUnknown36.getChildren();
						method__eq__37 = ModelTestUtils.getAssertMethod( classUnknown36Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__37, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_processors
			{
			IMethod methodtest_processors38;
				IModelElement[] classOpenerDirectorTests30Childs = classOpenerDirectorTests30.getChildren();
				methodtest_processors38 = ModelTestUtils.getAssertMethod( classOpenerDirectorTests30Childs, "test_processors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_processors38, new String[] {"self"} );
			}
		}
		//Function test:sanepathname2url
		{
		IMethod methodsanepathname2url39;
			IModelElement[] moduleChilds = module.getChildren();
			methodsanepathname2url39 = ModelTestUtils.getAssertMethod( moduleChilds, "sanepathname2url", 1 );
			ModelTestUtils.assertParameterNames( methodsanepathname2url39, new String[] {"path"} );
		}
		//Class test
		{
		IType classHandlerTests40;
			IModelElement[] moduleChilds = module.getChildren();
			classHandlerTests40 = ModelTestUtils.getAssertClass( moduleChilds, "HandlerTests" );
			//Function test:test_ftp
			{
			IMethod methodtest_ftp41;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_ftp41 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_ftp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ftp41, new String[] {"self"} );
				//Class test
				{
				IType classMockFTPWrapper42;
					IModelElement[] methodtest_ftp41Childs = methodtest_ftp41.getChildren();
					classMockFTPWrapper42 = ModelTestUtils.getAssertClass( methodtest_ftp41Childs, "MockFTPWrapper" );
					//Function test:__init__
					{
					IMethod method__init__43;
						IModelElement[] classMockFTPWrapper42Childs = classMockFTPWrapper42.getChildren();
						method__init__43 = ModelTestUtils.getAssertMethod( classMockFTPWrapper42Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__43, new String[] {"self", "data"} );
					}
					//Function test:retrfile
					{
					IMethod methodretrfile44;
						IModelElement[] classMockFTPWrapper42Childs = classMockFTPWrapper42.getChildren();
						methodretrfile44 = ModelTestUtils.getAssertMethod( classMockFTPWrapper42Childs, "retrfile", 3 );
						ModelTestUtils.assertParameterNames( methodretrfile44, new String[] {"self", "filename", "filetype"} );
					}
				}
				//Class test
				{
				IType classNullFTPHandler45;
					IModelElement[] methodtest_ftp41Childs = methodtest_ftp41.getChildren();
					classNullFTPHandler45 = ModelTestUtils.getAssertClass( methodtest_ftp41Childs, "NullFTPHandler" );
					//Function test:__init__
					{
					IMethod method__init__46;
						IModelElement[] classNullFTPHandler45Childs = classNullFTPHandler45.getChildren();
						method__init__46 = ModelTestUtils.getAssertMethod( classNullFTPHandler45Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__46, new String[] {"self", "data"} );
					}
					//Function test:connect_ftp
					{
					IMethod methodconnect_ftp47;
						IModelElement[] classNullFTPHandler45Childs = classNullFTPHandler45.getChildren();
						methodconnect_ftp47 = ModelTestUtils.getAssertMethod( classNullFTPHandler45Childs, "connect_ftp", 6 );
						ModelTestUtils.assertParameterNames( methodconnect_ftp47, new String[] {"self", "user", "passwd", "host", "port", "dirs"} );
					}
				}
			}
			//Function test:test_file
			{
			IMethod methodtest_file48;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_file48 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file48, new String[] {"self"} );
			}
			//Function test:test_http
			{
			IMethod methodtest_http49;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_http49 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_http", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http49, new String[] {"self"} );
				//Class test
				{
				IType classMockHTTPResponse50;
					IModelElement[] methodtest_http49Childs = methodtest_http49.getChildren();
					classMockHTTPResponse50 = ModelTestUtils.getAssertClass( methodtest_http49Childs, "MockHTTPResponse" );
					//Function test:__init__
					{
					IMethod method__init__51;
						IModelElement[] classMockHTTPResponse50Childs = classMockHTTPResponse50.getChildren();
						method__init__51 = ModelTestUtils.getAssertMethod( classMockHTTPResponse50Childs, "__init__", 5 );
						ModelTestUtils.assertParameterNames( method__init__51, new String[] {"self", "fp", "msg", "status", "reason"} );
					}
					//Function test:read
					{
					IMethod methodread52;
						IModelElement[] classMockHTTPResponse50Childs = classMockHTTPResponse50.getChildren();
						methodread52 = ModelTestUtils.getAssertMethod( classMockHTTPResponse50Childs, "read", 1 );
						ModelTestUtils.assertParameterNames( methodread52, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classMockHTTPClass53;
					IModelElement[] methodtest_http49Childs = methodtest_http49.getChildren();
					classMockHTTPClass53 = ModelTestUtils.getAssertClass( methodtest_http49Childs, "MockHTTPClass" );
					//Function test:__init__
					{
					IMethod method__init__54;
						IModelElement[] classMockHTTPClass53Childs = classMockHTTPClass53.getChildren();
						method__init__54 = ModelTestUtils.getAssertMethod( classMockHTTPClass53Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__54, new String[] {"self"} );
					}
					//Function test:__call__
					{
					IMethod method__call__55;
						IModelElement[] classMockHTTPClass53Childs = classMockHTTPClass53.getChildren();
						method__call__55 = ModelTestUtils.getAssertMethod( classMockHTTPClass53Childs, "__call__", 2 );
						ModelTestUtils.assertParameterNames( method__call__55, new String[] {"self", "host"} );
					}
					//Function test:set_debuglevel
					{
					IMethod methodset_debuglevel56;
						IModelElement[] classMockHTTPClass53Childs = classMockHTTPClass53.getChildren();
						methodset_debuglevel56 = ModelTestUtils.getAssertMethod( classMockHTTPClass53Childs, "set_debuglevel", 2 );
						ModelTestUtils.assertParameterNames( methodset_debuglevel56, new String[] {"self", "level"} );
					}
					//Function test:request
					{
					IMethod methodrequest57;
						IModelElement[] classMockHTTPClass53Childs = classMockHTTPClass53.getChildren();
						methodrequest57 = ModelTestUtils.getAssertMethod( classMockHTTPClass53Childs, "request", 5 );
						ModelTestUtils.assertParameterNames( methodrequest57, new String[] {"self", "method", "url", "body", "headers"} );
					}
					//Function test:getresponse
					{
					IMethod methodgetresponse58;
						IModelElement[] classMockHTTPClass53Childs = classMockHTTPClass53.getChildren();
						methodgetresponse58 = ModelTestUtils.getAssertMethod( classMockHTTPClass53Childs, "getresponse", 1 );
						ModelTestUtils.assertParameterNames( methodgetresponse58, new String[] {"self"} );
					}
				}
			}
			//Function test:test_errors
			{
			IMethod methodtest_errors59;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_errors59 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors59, new String[] {"self"} );
			}
			//Function test:test_cookies
			{
			IMethod methodtest_cookies60;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_cookies60 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_cookies", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cookies60, new String[] {"self"} );
			}
			//Function test:test_redirect
			{
			IMethod methodtest_redirect61;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_redirect61 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_redirect", 1 );
				ModelTestUtils.assertParameterNames( methodtest_redirect61, new String[] {"self"} );
				//Function test:redirect
				{
				IMethod methodredirect62;
					IModelElement[] methodtest_redirect61Childs = methodtest_redirect61.getChildren();
					methodredirect62 = ModelTestUtils.getAssertMethod( methodtest_redirect61Childs, "redirect", 3 );
					ModelTestUtils.assertParameterNames( methodredirect62, new String[] {"h", "req", "url"} );
				}
			}
			//Function test:test_cookie_redirect
			{
			IMethod methodtest_cookie_redirect63;
				IModelElement[] classHandlerTests40Childs = classHandlerTests40.getChildren();
				methodtest_cookie_redirect63 = ModelTestUtils.getAssertMethod( classHandlerTests40Childs, "test_cookie_redirect", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cookie_redirect63, new String[] {"self"} );
				//Class test
				{
				IType classMockHTTPHandler64;
					IModelElement[] methodtest_cookie_redirect63Childs = methodtest_cookie_redirect63.getChildren();
					classMockHTTPHandler64 = ModelTestUtils.getAssertClass( methodtest_cookie_redirect63Childs, "MockHTTPHandler" );
					//Function test:__init__
					{
					IMethod method__init__65;
						IModelElement[] classMockHTTPHandler64Childs = classMockHTTPHandler64.getChildren();
						method__init__65 = ModelTestUtils.getAssertMethod( classMockHTTPHandler64Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__65, new String[] {"self"} );
					}
					//Function test:http_open
					{
					IMethod methodhttp_open66;
						IModelElement[] classMockHTTPHandler64Childs = classMockHTTPHandler64.getChildren();
						methodhttp_open66 = ModelTestUtils.getAssertMethod( classMockHTTPHandler64Childs, "http_open", 2 );
						ModelTestUtils.assertParameterNames( methodhttp_open66, new String[] {"self", "req"} );
					}
				}
			}
		}
		//Class test
		{
		IType classMiscTests67;
			IModelElement[] moduleChilds = module.getChildren();
			classMiscTests67 = ModelTestUtils.getAssertClass( moduleChilds, "MiscTests" );
			//Function test:test_build_opener
			{
			IMethod methodtest_build_opener68;
				IModelElement[] classMiscTests67Childs = classMiscTests67.getChildren();
				methodtest_build_opener68 = ModelTestUtils.getAssertMethod( classMiscTests67Childs, "test_build_opener", 1 );
				ModelTestUtils.assertParameterNames( methodtest_build_opener68, new String[] {"self"} );
				//Class test
				{
				IType classMyHTTPHandler69;
					IModelElement[] methodtest_build_opener68Childs = methodtest_build_opener68.getChildren();
					classMyHTTPHandler69 = ModelTestUtils.getAssertClass( methodtest_build_opener68Childs, "MyHTTPHandler" );
				}
				//Class test
				{
				IType classFooHandler70;
					IModelElement[] methodtest_build_opener68Childs = methodtest_build_opener68.getChildren();
					classFooHandler70 = ModelTestUtils.getAssertClass( methodtest_build_opener68Childs, "FooHandler" );
					//Function test:foo_open
					{
					IMethod methodfoo_open71;
						IModelElement[] classFooHandler70Childs = classFooHandler70.getChildren();
						methodfoo_open71 = ModelTestUtils.getAssertMethod( classFooHandler70Childs, "foo_open", 1 );
						ModelTestUtils.assertParameterNames( methodfoo_open71, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBarHandler72;
					IModelElement[] methodtest_build_opener68Childs = methodtest_build_opener68.getChildren();
					classBarHandler72 = ModelTestUtils.getAssertClass( methodtest_build_opener68Childs, "BarHandler" );
					//Function test:bar_open
					{
					IMethod methodbar_open73;
						IModelElement[] classBarHandler72Childs = classBarHandler72.getChildren();
						methodbar_open73 = ModelTestUtils.getAssertMethod( classBarHandler72Childs, "bar_open", 1 );
						ModelTestUtils.assertParameterNames( methodbar_open73, new String[] {"self"} );
					}
				}
			}
			//Function test:opener_has_handler
			{
			IMethod methodopener_has_handler74;
				IModelElement[] classMiscTests67Childs = classMiscTests67.getChildren();
				methodopener_has_handler74 = ModelTestUtils.getAssertMethod( classMiscTests67Childs, "opener_has_handler", 3 );
				ModelTestUtils.assertParameterNames( methodopener_has_handler74, new String[] {"self", "opener", "handler_class"} );
			}
		}
		//Class test
		{
		IType classNetworkTests75;
			IModelElement[] moduleChilds = module.getChildren();
			classNetworkTests75 = ModelTestUtils.getAssertClass( moduleChilds, "NetworkTests" );
			//Function test:setUp
			{
			IMethod methodsetUp76;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				methodsetUp76 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp76, new String[] {"self"} );
			}
			//Function test:test_range
			{
			IMethod methodtest_range77;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				methodtest_range77 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "test_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_range77, new String[] {"self"} );
			}
			//Function test:test_ftp
			{
			IMethod methodtest_ftp78;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				methodtest_ftp78 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "test_ftp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ftp78, new String[] {"self"} );
			}
			//Function test:test_gopher
			{
			IMethod methodtest_gopher79;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				methodtest_gopher79 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "test_gopher", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gopher79, new String[] {"self"} );
			}
			//Function test:test_file
			{
			IMethod methodtest_file80;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				methodtest_file80 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "test_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file80, new String[] {"self"} );
			}
			//Function test:test_http
			{
			IMethod methodtest_http81;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				methodtest_http81 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "test_http", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http81, new String[] {"self"} );
			}
			//Function test:_test_urls
			{
			IMethod method_test_urls82;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				method_test_urls82 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "_test_urls", 3 );
				ModelTestUtils.assertParameterNames( method_test_urls82, new String[] {"self", "urls", "handlers"} );
			}
			//Function test:_extra_handlers
			{
			IMethod method_extra_handlers83;
				IModelElement[] classNetworkTests75Childs = classNetworkTests75.getChildren();
				method_extra_handlers83 = ModelTestUtils.getAssertMethod( classNetworkTests75Childs, "_extra_handlers", 1 );
				ModelTestUtils.assertParameterNames( method_extra_handlers83, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main84;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main84 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main84, new String[] {"verbose"} );
		}

	}
	public void testModelGen177( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_imp.py"));

		assertNotNull("Module test_imp.py not found", module);
		assertEquals("test_imp.py", module.getElementName());
		
		//Function test:verify_lock_state
		{
		IMethod methodverify_lock_state0;
			IModelElement[] moduleChilds = module.getChildren();
			methodverify_lock_state0 = ModelTestUtils.getAssertMethod( moduleChilds, "verify_lock_state", 1 );
			ModelTestUtils.assertParameterNames( methodverify_lock_state0, new String[] {"expected"} );
		}
		//Function test:testLock
		{
		IMethod methodtestLock1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestLock1 = ModelTestUtils.getAssertMethod( moduleChilds, "testLock", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen178( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_fcntl.py"));

		assertNotNull("Module test_fcntl.py not found", module);
		assertEquals("test_fcntl.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "filename");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "start_len");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "start_len");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "start_len");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "lockdata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "lockdata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "lockdata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "lockdata");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rv");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rv");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rv");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rv");
		}

	}
	public void testModelGen179( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_mutants.py"));

		assertNotNull("Module test_mutants.py not found", module);
		assertEquals("test_mutants.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict1keys");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict2keys");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mutate");
		}
		//Function test:maybe_mutate
		{
		IMethod methodmaybe_mutate0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmaybe_mutate0 = ModelTestUtils.getAssertMethod( moduleChilds, "maybe_mutate", 0 );
		}
		//Class test
		{
		IType classHorrid1;
			IModelElement[] moduleChilds = module.getChildren();
			classHorrid1 = ModelTestUtils.getAssertClass( moduleChilds, "Horrid" );
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classHorrid1Childs = classHorrid1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classHorrid1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "i"} );
			}
			//Function test:__hash__
			{
			IMethod method__hash__3;
				IModelElement[] classHorrid1Childs = classHorrid1.getChildren();
				method__hash__3 = ModelTestUtils.getAssertMethod( classHorrid1Childs, "__hash__", 1 );
				ModelTestUtils.assertParameterNames( method__hash__3, new String[] {"self"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__4;
				IModelElement[] classHorrid1Childs = classHorrid1.getChildren();
				method__cmp__4 = ModelTestUtils.getAssertMethod( classHorrid1Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__4, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__5;
				IModelElement[] classHorrid1Childs = classHorrid1.getChildren();
				method__repr__5 = ModelTestUtils.getAssertMethod( classHorrid1Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__5, new String[] {"self"} );
			}
		}
		//Function test:fill_dict
		{
		IMethod methodfill_dict6;
			IModelElement[] moduleChilds = module.getChildren();
			methodfill_dict6 = ModelTestUtils.getAssertMethod( moduleChilds, "fill_dict", 3 );
			ModelTestUtils.assertParameterNames( methodfill_dict6, new String[] {"d", "candidates", "numentries"} );
		}
		//Function test:test_one
		{
		IMethod methodtest_one7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_one7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_one", 1 );
			ModelTestUtils.assertParameterNames( methodtest_one7, new String[] {"n"} );
		}
		//Function test:test
		{
		IMethod methodtest8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest8 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 1 );
			ModelTestUtils.assertParameterNames( methodtest8, new String[] {"n"} );
		}
		//Class test
		{
		IType classChild9;
			IModelElement[] moduleChilds = module.getChildren();
			classChild9 = ModelTestUtils.getAssertClass( moduleChilds, "Child" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classChild9Childs = classChild9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classChild9Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self", "parent"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__11;
				IModelElement[] classChild9Childs = classChild9.getChildren();
				method__getattr__11 = ModelTestUtils.getAssertMethod( classChild9Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__11, new String[] {"self", "attr"} );
			}
		}
		//Class test
		{
		IType classParent12;
			IModelElement[] moduleChilds = module.getChildren();
			classParent12 = ModelTestUtils.getAssertClass( moduleChilds, "Parent" );
			//Function test:__init__
			{
			IMethod method__init__13;
				IModelElement[] classParent12Childs = classParent12.getChildren();
				method__init__13 = ModelTestUtils.getAssertMethod( classParent12Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__13, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		//Class test
		{
		IType classMachiavelli14;
			IModelElement[] moduleChilds = module.getChildren();
			classMachiavelli14 = ModelTestUtils.getAssertClass( moduleChilds, "Machiavelli" );
			//Function test:__repr__
			{
			IMethod method__repr__15;
				IModelElement[] classMachiavelli14Childs = classMachiavelli14.getChildren();
				method__repr__15 = ModelTestUtils.getAssertMethod( classMachiavelli14Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__15, new String[] {"self"} );
			}
			//Function test:__hash__
			{
			IMethod method__hash__16;
				IModelElement[] classMachiavelli14Childs = classMachiavelli14.getChildren();
				method__hash__16 = ModelTestUtils.getAssertMethod( classMachiavelli14Childs, "__hash__", 1 );
				ModelTestUtils.assertParameterNames( method__hash__16, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict");
		}
		//Class test
		{
		IType classMachiavelli217;
			IModelElement[] moduleChilds = module.getChildren();
			classMachiavelli217 = ModelTestUtils.getAssertClass( moduleChilds, "Machiavelli2" );
			//Function test:__eq__
			{
			IMethod method__eq__18;
				IModelElement[] classMachiavelli217Childs = classMachiavelli217.getChildren();
				method__eq__18 = ModelTestUtils.getAssertMethod( classMachiavelli217Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__18, new String[] {"self", "other"} );
			}
			//Function test:__hash__
			{
			IMethod method__hash__19;
				IModelElement[] classMachiavelli217Childs = classMachiavelli217.getChildren();
				method__hash__19 = ModelTestUtils.getAssertMethod( classMachiavelli217Childs, "__hash__", 1 );
				ModelTestUtils.assertParameterNames( method__hash__19, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dict");
		}
		//Class test
		{
		IType classMachiavelli320;
			IModelElement[] moduleChilds = module.getChildren();
			classMachiavelli320 = ModelTestUtils.getAssertClass( moduleChilds, "Machiavelli3" );
			//Function test:__init__
			{
			IMethod method__init__21;
				IModelElement[] classMachiavelli320Childs = classMachiavelli320.getChildren();
				method__init__21 = ModelTestUtils.getAssertMethod( classMachiavelli320Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__21, new String[] {"self", "id"} );
			}
			//Function test:__eq__
			{
			IMethod method__eq__22;
				IModelElement[] classMachiavelli320Childs = classMachiavelli320.getChildren();
				method__eq__22 = ModelTestUtils.getAssertMethod( classMachiavelli320Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__22, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__23;
				IModelElement[] classMachiavelli320Childs = classMachiavelli320.getChildren();
				method__repr__23 = ModelTestUtils.getAssertMethod( classMachiavelli320Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__23, new String[] {"self"} );
			}
			//Function test:__hash__
			{
			IMethod method__hash__24;
				IModelElement[] classMachiavelli320Childs = classMachiavelli320.getChildren();
				method__hash__24 = ModelTestUtils.getAssertMethod( classMachiavelli320Childs, "__hash__", 1 );
				ModelTestUtils.assertParameterNames( method__hash__24, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}

	}
	public void testModelGen180( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("doctest_aliases.py"));

		assertNotNull("Module doctest_aliases.py not found", module);
		assertEquals("doctest_aliases.py", module.getElementName());
		
		//Class test
		{
		IType classTwoNames0;
			IModelElement[] moduleChilds = module.getChildren();
			classTwoNames0 = ModelTestUtils.getAssertClass( moduleChilds, "TwoNames" );
			//Function test:f
			{
			IMethod methodf1;
				IModelElement[] classTwoNames0Childs = classTwoNames0.getChildren();
				methodf1 = ModelTestUtils.getAssertMethod( classTwoNames0Childs, "f", 1 );
				ModelTestUtils.assertParameterNames( methodf1, new String[] {"self"} );
			}
		}

	}
	public void testModelGen181( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_threading_local.py"));

		assertNotNull("Module test_threading_local.py not found", module);
		assertEquals("test_threading_local.py", module.getElementName());
		
		//Function test:test_main
		{
		IMethod methodtest_main0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] methodtest_main0Childs = methodtest_main0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( methodtest_main0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"test"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] methodtest_main0Childs = methodtest_main0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( methodtest_main0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"test"} );
			}
		}

	}
	public void testModelGen182( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_winsound.py"));

		assertNotNull("Module test_winsound.py not found", module);
		assertEquals("test_winsound.py", module.getElementName());
		
		//Class test
		{
		IType classBeepTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classBeepTest0 = ModelTestUtils.getAssertClass( moduleChilds, "BeepTest" );
			//Function test:test_errors
			{
			IMethod methodtest_errors1;
				IModelElement[] classBeepTest0Childs = classBeepTest0.getChildren();
				methodtest_errors1 = ModelTestUtils.getAssertMethod( classBeepTest0Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors1, new String[] {"self"} );
			}
			//Function test:test_extremes
			{
			IMethod methodtest_extremes2;
				IModelElement[] classBeepTest0Childs = classBeepTest0.getChildren();
				methodtest_extremes2 = ModelTestUtils.getAssertMethod( classBeepTest0Childs, "test_extremes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extremes2, new String[] {"self"} );
			}
			//Function test:test_increasingfrequency
			{
			IMethod methodtest_increasingfrequency3;
				IModelElement[] classBeepTest0Childs = classBeepTest0.getChildren();
				methodtest_increasingfrequency3 = ModelTestUtils.getAssertMethod( classBeepTest0Childs, "test_increasingfrequency", 1 );
				ModelTestUtils.assertParameterNames( methodtest_increasingfrequency3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMessageBeepTest4;
			IModelElement[] moduleChilds = module.getChildren();
			classMessageBeepTest4 = ModelTestUtils.getAssertClass( moduleChilds, "MessageBeepTest" );
			//Function test:tearDown
			{
			IMethod methodtearDown5;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtearDown5 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown5, new String[] {"self"} );
			}
			//Function test:test_default
			{
			IMethod methodtest_default6;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtest_default6 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "test_default", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default6, new String[] {"self"} );
			}
			//Function test:test_ok
			{
			IMethod methodtest_ok7;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtest_ok7 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "test_ok", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ok7, new String[] {"self"} );
			}
			//Function test:test_asterisk
			{
			IMethod methodtest_asterisk8;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtest_asterisk8 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "test_asterisk", 1 );
				ModelTestUtils.assertParameterNames( methodtest_asterisk8, new String[] {"self"} );
			}
			//Function test:test_exclamation
			{
			IMethod methodtest_exclamation9;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtest_exclamation9 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "test_exclamation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exclamation9, new String[] {"self"} );
			}
			//Function test:test_hand
			{
			IMethod methodtest_hand10;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtest_hand10 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "test_hand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hand10, new String[] {"self"} );
			}
			//Function test:test_question
			{
			IMethod methodtest_question11;
				IModelElement[] classMessageBeepTest4Childs = classMessageBeepTest4.getChildren();
				methodtest_question11 = ModelTestUtils.getAssertMethod( classMessageBeepTest4Childs, "test_question", 1 );
				ModelTestUtils.assertParameterNames( methodtest_question11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPlaySoundTest12;
			IModelElement[] moduleChilds = module.getChildren();
			classPlaySoundTest12 = ModelTestUtils.getAssertClass( moduleChilds, "PlaySoundTest" );
			//Function test:test_errors
			{
			IMethod methodtest_errors13;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_errors13 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_errors13, new String[] {"self"} );
			}
			//Function test:test_alias_asterisk
			{
			IMethod methodtest_alias_asterisk14;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_asterisk14 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_asterisk", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_asterisk14, new String[] {"self"} );
			}
			//Function test:test_alias_exclamation
			{
			IMethod methodtest_alias_exclamation15;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_exclamation15 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_exclamation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_exclamation15, new String[] {"self"} );
			}
			//Function test:test_alias_exit
			{
			IMethod methodtest_alias_exit16;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_exit16 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_exit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_exit16, new String[] {"self"} );
			}
			//Function test:test_alias_hand
			{
			IMethod methodtest_alias_hand17;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_hand17 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_hand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_hand17, new String[] {"self"} );
			}
			//Function test:test_alias_question
			{
			IMethod methodtest_alias_question18;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_question18 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_question", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_question18, new String[] {"self"} );
			}
			//Function test:test_alias_fallback
			{
			IMethod methodtest_alias_fallback19;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_fallback19 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_fallback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_fallback19, new String[] {"self"} );
			}
			//Function test:test_alias_nofallback
			{
			IMethod methodtest_alias_nofallback20;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_alias_nofallback20 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_alias_nofallback", 1 );
				ModelTestUtils.assertParameterNames( methodtest_alias_nofallback20, new String[] {"self"} );
			}
			//Function test:test_stopasync
			{
			IMethod methodtest_stopasync21;
				IModelElement[] classPlaySoundTest12Childs = classPlaySoundTest12.getChildren();
				methodtest_stopasync21 = ModelTestUtils.getAssertMethod( classPlaySoundTest12Childs, "test_stopasync", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stopasync21, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen183( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_richcmp.py"));

		assertNotNull("Module test_richcmp.py not found", module);
		assertEquals("test_richcmp.py", module.getElementName());
		
		//Class test
		{
		IType classNumber0;
			IModelElement[] moduleChilds = module.getChildren();
			classNumber0 = ModelTestUtils.getAssertClass( moduleChilds, "Number" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "x"} );
			}
			//Function test:__lt__
			{
			IMethod method__lt__2;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__lt__2 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__lt__", 2 );
				ModelTestUtils.assertParameterNames( method__lt__2, new String[] {"self", "other"} );
			}
			//Function test:__le__
			{
			IMethod method__le__3;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__le__3 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__le__", 2 );
				ModelTestUtils.assertParameterNames( method__le__3, new String[] {"self", "other"} );
			}
			//Function test:__eq__
			{
			IMethod method__eq__4;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__eq__4 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__4, new String[] {"self", "other"} );
			}
			//Function test:__ne__
			{
			IMethod method__ne__5;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__ne__5 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__ne__", 2 );
				ModelTestUtils.assertParameterNames( method__ne__5, new String[] {"self", "other"} );
			}
			//Function test:__gt__
			{
			IMethod method__gt__6;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__gt__6 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__gt__", 2 );
				ModelTestUtils.assertParameterNames( method__gt__6, new String[] {"self", "other"} );
			}
			//Function test:__ge__
			{
			IMethod method__ge__7;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__ge__7 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__ge__", 2 );
				ModelTestUtils.assertParameterNames( method__ge__7, new String[] {"self", "other"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__8;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__cmp__8 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__8, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__9;
				IModelElement[] classNumber0Childs = classNumber0.getChildren();
				method__repr__9 = ModelTestUtils.getAssertMethod( classNumber0Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classVector10;
			IModelElement[] moduleChilds = module.getChildren();
			classVector10 = ModelTestUtils.getAssertClass( moduleChilds, "Vector" );
			//Function test:__init__
			{
			IMethod method__init__11;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__init__11 = ModelTestUtils.getAssertMethod( classVector10Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__11, new String[] {"self", "data"} );
			}
			//Function test:__len__
			{
			IMethod method__len__12;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__len__12 = ModelTestUtils.getAssertMethod( classVector10Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__12, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__13;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__getitem__13 = ModelTestUtils.getAssertMethod( classVector10Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__13, new String[] {"self", "i"} );
			}
			//Function test:__setitem__
			{
			IMethod method__setitem__14;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__setitem__14 = ModelTestUtils.getAssertMethod( classVector10Childs, "__setitem__", 3 );
				ModelTestUtils.assertParameterNames( method__setitem__14, new String[] {"self", "i", "v"} );
			}
			//Function test:__hash__
			{
			IMethod method__hash__15;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__hash__15 = ModelTestUtils.getAssertMethod( classVector10Childs, "__hash__", 1 );
				ModelTestUtils.assertParameterNames( method__hash__15, new String[] {"self"} );
			}
			//Function test:__nonzero__
			{
			IMethod method__nonzero__16;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__nonzero__16 = ModelTestUtils.getAssertMethod( classVector10Childs, "__nonzero__", 1 );
				ModelTestUtils.assertParameterNames( method__nonzero__16, new String[] {"self"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__17;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__cmp__17 = ModelTestUtils.getAssertMethod( classVector10Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__17, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__18;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__repr__18 = ModelTestUtils.getAssertMethod( classVector10Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__18, new String[] {"self"} );
			}
			//Function test:__lt__
			{
			IMethod method__lt__19;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__lt__19 = ModelTestUtils.getAssertMethod( classVector10Childs, "__lt__", 2 );
				ModelTestUtils.assertParameterNames( method__lt__19, new String[] {"self", "other"} );
			}
			//Function test:__le__
			{
			IMethod method__le__20;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__le__20 = ModelTestUtils.getAssertMethod( classVector10Childs, "__le__", 2 );
				ModelTestUtils.assertParameterNames( method__le__20, new String[] {"self", "other"} );
			}
			//Function test:__eq__
			{
			IMethod method__eq__21;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__eq__21 = ModelTestUtils.getAssertMethod( classVector10Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__21, new String[] {"self", "other"} );
			}
			//Function test:__ne__
			{
			IMethod method__ne__22;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__ne__22 = ModelTestUtils.getAssertMethod( classVector10Childs, "__ne__", 2 );
				ModelTestUtils.assertParameterNames( method__ne__22, new String[] {"self", "other"} );
			}
			//Function test:__gt__
			{
			IMethod method__gt__23;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__gt__23 = ModelTestUtils.getAssertMethod( classVector10Childs, "__gt__", 2 );
				ModelTestUtils.assertParameterNames( method__gt__23, new String[] {"self", "other"} );
			}
			//Function test:__ge__
			{
			IMethod method__ge__24;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__ge__24 = ModelTestUtils.getAssertMethod( classVector10Childs, "__ge__", 2 );
				ModelTestUtils.assertParameterNames( method__ge__24, new String[] {"self", "other"} );
			}
			//Function test:__cast
			{
			IMethod method__cast25;
				IModelElement[] classVector10Childs = classVector10.getChildren();
				method__cast25 = ModelTestUtils.getAssertMethod( classVector10Childs, "__cast", 2 );
				ModelTestUtils.assertParameterNames( method__cast25, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "opmap");
		}
		//Class test
		{
		IType classVectorTest26;
			IModelElement[] moduleChilds = module.getChildren();
			classVectorTest26 = ModelTestUtils.getAssertClass( moduleChilds, "VectorTest" );
			//Function test:checkfail
			{
			IMethod methodcheckfail27;
				IModelElement[] classVectorTest26Childs = classVectorTest26.getChildren();
				methodcheckfail27 = ModelTestUtils.getAssertMethod( classVectorTest26Childs, "checkfail", 4 );
				ModelTestUtils.assertParameterNames( methodcheckfail27, new String[] {"self", "error", "opname", "args"} );
			}
			//Function test:checkequal
			{
			IMethod methodcheckequal28;
				IModelElement[] classVectorTest26Childs = classVectorTest26.getChildren();
				methodcheckequal28 = ModelTestUtils.getAssertMethod( classVectorTest26Childs, "checkequal", 5 );
				ModelTestUtils.assertParameterNames( methodcheckequal28, new String[] {"self", "opname", "a", "b", "expres"} );
			}
			//Function test:test_mixed
			{
			IMethod methodtest_mixed29;
				IModelElement[] classVectorTest26Childs = classVectorTest26.getChildren();
				methodtest_mixed29 = ModelTestUtils.getAssertMethod( classVectorTest26Childs, "test_mixed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed29, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNumberTest30;
			IModelElement[] moduleChilds = module.getChildren();
			classNumberTest30 = ModelTestUtils.getAssertClass( moduleChilds, "NumberTest" );
			//Function test:test_basic
			{
			IMethod methodtest_basic31;
				IModelElement[] classNumberTest30Childs = classNumberTest30.getChildren();
				methodtest_basic31 = ModelTestUtils.getAssertMethod( classNumberTest30Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic31, new String[] {"self"} );
			}
			//Function test:checkvalue
			{
			IMethod methodcheckvalue32;
				IModelElement[] classNumberTest30Childs = classNumberTest30.getChildren();
				methodcheckvalue32 = ModelTestUtils.getAssertMethod( classNumberTest30Childs, "checkvalue", 5 );
				ModelTestUtils.assertParameterNames( methodcheckvalue32, new String[] {"self", "opname", "a", "b", "expres"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values33;
				IModelElement[] classNumberTest30Childs = classNumberTest30.getChildren();
				methodtest_values33 = ModelTestUtils.getAssertMethod( classNumberTest30Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values33, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMiscTest34;
			IModelElement[] moduleChilds = module.getChildren();
			classMiscTest34 = ModelTestUtils.getAssertClass( moduleChilds, "MiscTest" );
			//Function test:test_misbehavin
			{
			IMethod methodtest_misbehavin35;
				IModelElement[] classMiscTest34Childs = classMiscTest34.getChildren();
				methodtest_misbehavin35 = ModelTestUtils.getAssertMethod( classMiscTest34Childs, "test_misbehavin", 1 );
				ModelTestUtils.assertParameterNames( methodtest_misbehavin35, new String[] {"self"} );
				//Class test
				{
				IType classMisb36;
					IModelElement[] methodtest_misbehavin35Childs = methodtest_misbehavin35.getChildren();
					classMisb36 = ModelTestUtils.getAssertClass( methodtest_misbehavin35Childs, "Misb" );
					//Function test:__lt__
					{
					IMethod method__lt__37;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__lt__37 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__lt__", 2 );
						ModelTestUtils.assertParameterNames( method__lt__37, new String[] {"self", "other"} );
					}
					//Function test:__gt__
					{
					IMethod method__gt__38;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__gt__38 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__gt__", 2 );
						ModelTestUtils.assertParameterNames( method__gt__38, new String[] {"self", "other"} );
					}
					//Function test:__eq__
					{
					IMethod method__eq__39;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__eq__39 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__39, new String[] {"self", "other"} );
					}
					//Function test:__le__
					{
					IMethod method__le__40;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__le__40 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__le__", 2 );
						ModelTestUtils.assertParameterNames( method__le__40, new String[] {"self", "other"} );
					}
					//Function test:__ge__
					{
					IMethod method__ge__41;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__ge__41 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__ge__", 2 );
						ModelTestUtils.assertParameterNames( method__ge__41, new String[] {"self", "other"} );
					}
					//Function test:__ne__
					{
					IMethod method__ne__42;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__ne__42 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__ne__", 2 );
						ModelTestUtils.assertParameterNames( method__ne__42, new String[] {"self", "other"} );
					}
					//Function test:__cmp__
					{
					IMethod method__cmp__43;
						IModelElement[] classMisb36Childs = classMisb36.getChildren();
						method__cmp__43 = ModelTestUtils.getAssertMethod( classMisb36Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__43, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_not
			{
			IMethod methodtest_not44;
				IModelElement[] classMiscTest34Childs = classMiscTest34.getChildren();
				methodtest_not44 = ModelTestUtils.getAssertMethod( classMiscTest34Childs, "test_not", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not44, new String[] {"self"} );
				//Class test
				{
				IType classExc45;
					IModelElement[] methodtest_not44Childs = methodtest_not44.getChildren();
					classExc45 = ModelTestUtils.getAssertClass( methodtest_not44Childs, "Exc" );
				}
				//Class test
				{
				IType classBad46;
					IModelElement[] methodtest_not44Childs = methodtest_not44.getChildren();
					classBad46 = ModelTestUtils.getAssertClass( methodtest_not44Childs, "Bad" );
					//Function test:__nonzero__
					{
					IMethod method__nonzero__47;
						IModelElement[] classBad46Childs = classBad46.getChildren();
						method__nonzero__47 = ModelTestUtils.getAssertMethod( classBad46Childs, "__nonzero__", 1 );
						ModelTestUtils.assertParameterNames( method__nonzero__47, new String[] {"self"} );
					}
				}
				//Function test:do
				{
				IMethod methoddo48;
					IModelElement[] methodtest_not44Childs = methodtest_not44.getChildren();
					methoddo48 = ModelTestUtils.getAssertMethod( methodtest_not44Childs, "do", 1 );
					ModelTestUtils.assertParameterNames( methoddo48, new String[] {"bad"} );
				}
			}
			//Function test:test_recursion
			{
			IMethod methodtest_recursion49;
				IModelElement[] classMiscTest34Childs = classMiscTest34.getChildren();
				methodtest_recursion49 = ModelTestUtils.getAssertMethod( classMiscTest34Childs, "test_recursion", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursion49, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDictTest50;
			IModelElement[] moduleChilds = module.getChildren();
			classDictTest50 = ModelTestUtils.getAssertClass( moduleChilds, "DictTest" );
			//Function test:test_dicts
			{
			IMethod methodtest_dicts51;
				IModelElement[] classDictTest50Childs = classDictTest50.getChildren();
				methodtest_dicts51 = ModelTestUtils.getAssertMethod( classDictTest50Childs, "test_dicts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dicts51, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classListTest52;
			IModelElement[] moduleChilds = module.getChildren();
			classListTest52 = ModelTestUtils.getAssertClass( moduleChilds, "ListTest" );
			//Function test:assertIs
			{
			IMethod methodassertIs53;
				IModelElement[] classListTest52Childs = classListTest52.getChildren();
				methodassertIs53 = ModelTestUtils.getAssertMethod( classListTest52Childs, "assertIs", 3 );
				ModelTestUtils.assertParameterNames( methodassertIs53, new String[] {"self", "a", "b"} );
			}
			//Function test:test_coverage
			{
			IMethod methodtest_coverage54;
				IModelElement[] classListTest52Childs = classListTest52.getChildren();
				methodtest_coverage54 = ModelTestUtils.getAssertMethod( classListTest52Childs, "test_coverage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coverage54, new String[] {"self"} );
			}
			//Function test:test_badentry
			{
			IMethod methodtest_badentry55;
				IModelElement[] classListTest52Childs = classListTest52.getChildren();
				methodtest_badentry55 = ModelTestUtils.getAssertMethod( classListTest52Childs, "test_badentry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badentry55, new String[] {"self"} );
				//Class test
				{
				IType classExc56;
					IModelElement[] methodtest_badentry55Childs = methodtest_badentry55.getChildren();
					classExc56 = ModelTestUtils.getAssertClass( methodtest_badentry55Childs, "Exc" );
				}
				//Class test
				{
				IType classBad57;
					IModelElement[] methodtest_badentry55Childs = methodtest_badentry55.getChildren();
					classBad57 = ModelTestUtils.getAssertClass( methodtest_badentry55Childs, "Bad" );
					//Function test:__eq__
					{
					IMethod method__eq__58;
						IModelElement[] classBad57Childs = classBad57.getChildren();
						method__eq__58 = ModelTestUtils.getAssertMethod( classBad57Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__58, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_goodentry
			{
			IMethod methodtest_goodentry59;
				IModelElement[] classListTest52Childs = classListTest52.getChildren();
				methodtest_goodentry59 = ModelTestUtils.getAssertMethod( classListTest52Childs, "test_goodentry", 1 );
				ModelTestUtils.assertParameterNames( methodtest_goodentry59, new String[] {"self"} );
				//Class test
				{
				IType classGood60;
					IModelElement[] methodtest_goodentry59Childs = methodtest_goodentry59.getChildren();
					classGood60 = ModelTestUtils.getAssertClass( methodtest_goodentry59Childs, "Good" );
					//Function test:__lt__
					{
					IMethod method__lt__61;
						IModelElement[] classGood60Childs = classGood60.getChildren();
						method__lt__61 = ModelTestUtils.getAssertMethod( classGood60Childs, "__lt__", 2 );
						ModelTestUtils.assertParameterNames( method__lt__61, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main62;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main62 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen184( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_md5.py"));

		assertNotNull("Module test_md5.py not found", module);
		assertEquals("test_md5.py", module.getElementName());
		
		//Function test:hexstr
		{
		IMethod methodhexstr0;
			IModelElement[] moduleChilds = module.getChildren();
			methodhexstr0 = ModelTestUtils.getAssertMethod( moduleChilds, "hexstr", 1 );
			ModelTestUtils.assertParameterNames( methodhexstr0, new String[] {"s"} );
		}
		//Class test
		{
		IType classMD5_Test1;
			IModelElement[] moduleChilds = module.getChildren();
			classMD5_Test1 = ModelTestUtils.getAssertClass( moduleChilds, "MD5_Test" );
			//Function test:md5test
			{
			IMethod methodmd5test2;
				IModelElement[] classMD5_Test1Childs = classMD5_Test1.getChildren();
				methodmd5test2 = ModelTestUtils.getAssertMethod( classMD5_Test1Childs, "md5test", 3 );
				ModelTestUtils.assertParameterNames( methodmd5test2, new String[] {"self", "s", "expected"} );
			}
			//Function test:test_basics
			{
			IMethod methodtest_basics3;
				IModelElement[] classMD5_Test1Childs = classMD5_Test1.getChildren();
				methodtest_basics3 = ModelTestUtils.getAssertMethod( classMD5_Test1Childs, "test_basics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basics3, new String[] {"self"} );
			}
			//Function test:test_hexdigest
			{
			IMethod methodtest_hexdigest4;
				IModelElement[] classMD5_Test1Childs = classMD5_Test1.getChildren();
				methodtest_hexdigest4 = ModelTestUtils.getAssertMethod( classMD5_Test1Childs, "test_hexdigest", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hexdigest4, new String[] {"self"} );
			}
			//Function test:test_large_update
			{
			IMethod methodtest_large_update5;
				IModelElement[] classMD5_Test1Childs = classMD5_Test1.getChildren();
				methodtest_large_update5 = ModelTestUtils.getAssertMethod( classMD5_Test1Childs, "test_large_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_large_update5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen185( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bsddb3.py"));

		assertNotNull("Module test_bsddb3.py not found", module);
		assertEquals("test_bsddb3.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "verbose");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "verbose");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "verbose");
		}
		//Function test:suite
		{
		IMethod methodsuite0;
			IModelElement[] moduleChilds = module.getChildren();
			methodsuite0 = ModelTestUtils.getAssertMethod( moduleChilds, "suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen186( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_module.py"));

		assertNotNull("Module test_module.py not found", module);
		assertEquals("test_module.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "module");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "foo");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "foo");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "foo");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "foo");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}

	}
	public void testModelGen187( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_syntax.py"));

		assertNotNull("Module test_syntax.py not found", module);
		assertEquals("test_syntax.py", module.getElementName());
		
		//Class test
		{
		IType classSyntaxTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classSyntaxTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "SyntaxTestCase" );
			//Function test:_check_error
			{
			IMethod method_check_error1;
				IModelElement[] classSyntaxTestCase0Childs = classSyntaxTestCase0.getChildren();
				method_check_error1 = ModelTestUtils.getAssertMethod( classSyntaxTestCase0Childs, "_check_error", 5 );
				ModelTestUtils.assertParameterNames( method_check_error1, new String[] {"self", "code", "errtext", "filename", "mode"} );
			}
			//Function test:test_assign_call
			{
			IMethod methodtest_assign_call2;
				IModelElement[] classSyntaxTestCase0Childs = classSyntaxTestCase0.getChildren();
				methodtest_assign_call2 = ModelTestUtils.getAssertMethod( classSyntaxTestCase0Childs, "test_assign_call", 1 );
				ModelTestUtils.assertParameterNames( methodtest_assign_call2, new String[] {"self"} );
			}
			//Function test:test_assign_del
			{
			IMethod methodtest_assign_del3;
				IModelElement[] classSyntaxTestCase0Childs = classSyntaxTestCase0.getChildren();
				methodtest_assign_del3 = ModelTestUtils.getAssertMethod( classSyntaxTestCase0Childs, "test_assign_del", 1 );
				ModelTestUtils.assertParameterNames( methodtest_assign_del3, new String[] {"self"} );
			}
			//Function test:test_global_err_then_warn
			{
			IMethod methodtest_global_err_then_warn4;
				IModelElement[] classSyntaxTestCase0Childs = classSyntaxTestCase0.getChildren();
				methodtest_global_err_then_warn4 = ModelTestUtils.getAssertMethod( classSyntaxTestCase0Childs, "test_global_err_then_warn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_err_then_warn4, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen188( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_strptime.py"));

		assertNotNull("Module test_strptime.py not found", module);
		assertEquals("test_strptime.py", module.getElementName());
		
		//Class test
		{
		IType classgetlang_Tests0;
			IModelElement[] moduleChilds = module.getChildren();
			classgetlang_Tests0 = ModelTestUtils.getAssertClass( moduleChilds, "getlang_Tests" );
			//Function test:test_basic
			{
			IMethod methodtest_basic1;
				IModelElement[] classgetlang_Tests0Childs = classgetlang_Tests0.getChildren();
				methodtest_basic1 = ModelTestUtils.getAssertMethod( classgetlang_Tests0Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classLocaleTime_Tests2;
			IModelElement[] moduleChilds = module.getChildren();
			classLocaleTime_Tests2 = ModelTestUtils.getAssertClass( moduleChilds, "LocaleTime_Tests" );
			//Function test:setUp
			{
			IMethod methodsetUp3;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodsetUp3 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp3, new String[] {"self"} );
			}
			//Function test:compare_against_time
			{
			IMethod methodcompare_against_time4;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodcompare_against_time4 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "compare_against_time", 5 );
				ModelTestUtils.assertParameterNames( methodcompare_against_time4, new String[] {"self", "testing", "directive", "tuple_position", "error_msg"} );
			}
			//Function test:test_weekday
			{
			IMethod methodtest_weekday5;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodtest_weekday5 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "test_weekday", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weekday5, new String[] {"self"} );
			}
			//Function test:test_month
			{
			IMethod methodtest_month6;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodtest_month6 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "test_month", 1 );
				ModelTestUtils.assertParameterNames( methodtest_month6, new String[] {"self"} );
			}
			//Function test:test_am_pm
			{
			IMethod methodtest_am_pm7;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodtest_am_pm7 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "test_am_pm", 1 );
				ModelTestUtils.assertParameterNames( methodtest_am_pm7, new String[] {"self"} );
			}
			//Function test:test_timezone
			{
			IMethod methodtest_timezone8;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodtest_timezone8 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "test_timezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_timezone8, new String[] {"self"} );
			}
			//Function test:test_date_time
			{
			IMethod methodtest_date_time9;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodtest_date_time9 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "test_date_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_date_time9, new String[] {"self"} );
			}
			//Function test:test_lang
			{
			IMethod methodtest_lang10;
				IModelElement[] classLocaleTime_Tests2Childs = classLocaleTime_Tests2.getChildren();
				methodtest_lang10 = ModelTestUtils.getAssertMethod( classLocaleTime_Tests2Childs, "test_lang", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lang10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTimeRETests11;
			IModelElement[] moduleChilds = module.getChildren();
			classTimeRETests11 = ModelTestUtils.getAssertClass( moduleChilds, "TimeRETests" );
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
			}
			//Function test:test_pattern
			{
			IMethod methodtest_pattern13;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodtest_pattern13 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "test_pattern", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pattern13, new String[] {"self"} );
			}
			//Function test:test_pattern_escaping
			{
			IMethod methodtest_pattern_escaping14;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodtest_pattern_escaping14 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "test_pattern_escaping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pattern_escaping14, new String[] {"self"} );
			}
			//Function test:test_compile
			{
			IMethod methodtest_compile15;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodtest_compile15 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "test_compile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compile15, new String[] {"self"} );
			}
			//Function test:test_blankpattern
			{
			IMethod methodtest_blankpattern16;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodtest_blankpattern16 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "test_blankpattern", 1 );
				ModelTestUtils.assertParameterNames( methodtest_blankpattern16, new String[] {"self"} );
			}
			//Function test:test_matching_with_escapes
			{
			IMethod methodtest_matching_with_escapes17;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodtest_matching_with_escapes17 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "test_matching_with_escapes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_matching_with_escapes17, new String[] {"self"} );
			}
			//Function test:test_locale_data_w_regex_metacharacters
			{
			IMethod methodtest_locale_data_w_regex_metacharacters18;
				IModelElement[] classTimeRETests11Childs = classTimeRETests11.getChildren();
				methodtest_locale_data_w_regex_metacharacters18 = ModelTestUtils.getAssertMethod( classTimeRETests11Childs, "test_locale_data_w_regex_metacharacters", 1 );
				ModelTestUtils.assertParameterNames( methodtest_locale_data_w_regex_metacharacters18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStrptimeTests19;
			IModelElement[] moduleChilds = module.getChildren();
			classStrptimeTests19 = ModelTestUtils.getAssertClass( moduleChilds, "StrptimeTests" );
			//Function test:setUp
			{
			IMethod methodsetUp20;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodsetUp20 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp20, new String[] {"self"} );
			}
			//Function test:test_TypeError
			{
			IMethod methodtest_TypeError21;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_TypeError21 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_TypeError", 1 );
				ModelTestUtils.assertParameterNames( methodtest_TypeError21, new String[] {"self"} );
			}
			//Function test:test_unconverteddata
			{
			IMethod methodtest_unconverteddata22;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_unconverteddata22 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_unconverteddata", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unconverteddata22, new String[] {"self"} );
			}
			//Function test:helper
			{
			IMethod methodhelper23;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodhelper23 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "helper", 3 );
				ModelTestUtils.assertParameterNames( methodhelper23, new String[] {"self", "directive", "position"} );
			}
			//Function test:test_year
			{
			IMethod methodtest_year24;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_year24 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_year", 1 );
				ModelTestUtils.assertParameterNames( methodtest_year24, new String[] {"self"} );
			}
			//Function test:test_month
			{
			IMethod methodtest_month25;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_month25 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_month", 1 );
				ModelTestUtils.assertParameterNames( methodtest_month25, new String[] {"self"} );
			}
			//Function test:test_day
			{
			IMethod methodtest_day26;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_day26 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_day", 1 );
				ModelTestUtils.assertParameterNames( methodtest_day26, new String[] {"self"} );
			}
			//Function test:test_hour
			{
			IMethod methodtest_hour27;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_hour27 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_hour", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hour27, new String[] {"self"} );
			}
			//Function test:test_minute
			{
			IMethod methodtest_minute28;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_minute28 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_minute", 1 );
				ModelTestUtils.assertParameterNames( methodtest_minute28, new String[] {"self"} );
			}
			//Function test:test_second
			{
			IMethod methodtest_second29;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_second29 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_second", 1 );
				ModelTestUtils.assertParameterNames( methodtest_second29, new String[] {"self"} );
			}
			//Function test:test_weekday
			{
			IMethod methodtest_weekday30;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_weekday30 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_weekday", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weekday30, new String[] {"self"} );
			}
			//Function test:test_julian
			{
			IMethod methodtest_julian31;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_julian31 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_julian", 1 );
				ModelTestUtils.assertParameterNames( methodtest_julian31, new String[] {"self"} );
			}
			//Function test:test_timezone
			{
			IMethod methodtest_timezone32;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_timezone32 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_timezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_timezone32, new String[] {"self"} );
			}
			//Function test:test_bad_timezone
			{
			IMethod methodtest_bad_timezone33;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_bad_timezone33 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_bad_timezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_timezone33, new String[] {"self"} );
			}
			//Function test:test_date_time
			{
			IMethod methodtest_date_time34;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_date_time34 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_date_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_date_time34, new String[] {"self"} );
			}
			//Function test:test_date
			{
			IMethod methodtest_date35;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_date35 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_date", 1 );
				ModelTestUtils.assertParameterNames( methodtest_date35, new String[] {"self"} );
			}
			//Function test:test_time
			{
			IMethod methodtest_time36;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_time36 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_time36, new String[] {"self"} );
			}
			//Function test:test_percent
			{
			IMethod methodtest_percent37;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_percent37 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_percent", 1 );
				ModelTestUtils.assertParameterNames( methodtest_percent37, new String[] {"self"} );
			}
			//Function test:test_caseinsensitive
			{
			IMethod methodtest_caseinsensitive38;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_caseinsensitive38 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_caseinsensitive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caseinsensitive38, new String[] {"self"} );
			}
			//Function test:test_defaults
			{
			IMethod methodtest_defaults39;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_defaults39 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_defaults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_defaults39, new String[] {"self"} );
			}
			//Function test:test_escaping
			{
			IMethod methodtest_escaping40;
				IModelElement[] classStrptimeTests19Childs = classStrptimeTests19.getChildren();
				methodtest_escaping40 = ModelTestUtils.getAssertMethod( classStrptimeTests19Childs, "test_escaping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_escaping40, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStrptime12AMPMTests41;
			IModelElement[] moduleChilds = module.getChildren();
			classStrptime12AMPMTests41 = ModelTestUtils.getAssertClass( moduleChilds, "Strptime12AMPMTests" );
			//Function test:test_twelve_noon_midnight
			{
			IMethod methodtest_twelve_noon_midnight42;
				IModelElement[] classStrptime12AMPMTests41Childs = classStrptime12AMPMTests41.getChildren();
				methodtest_twelve_noon_midnight42 = ModelTestUtils.getAssertMethod( classStrptime12AMPMTests41Childs, "test_twelve_noon_midnight", 1 );
				ModelTestUtils.assertParameterNames( methodtest_twelve_noon_midnight42, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classJulianTests43;
			IModelElement[] moduleChilds = module.getChildren();
			classJulianTests43 = ModelTestUtils.getAssertClass( moduleChilds, "JulianTests" );
			//Function test:test_all_julian_days
			{
			IMethod methodtest_all_julian_days44;
				IModelElement[] classJulianTests43Childs = classJulianTests43.getChildren();
				methodtest_all_julian_days44 = ModelTestUtils.getAssertMethod( classJulianTests43Childs, "test_all_julian_days", 1 );
				ModelTestUtils.assertParameterNames( methodtest_all_julian_days44, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCalculationTests45;
			IModelElement[] moduleChilds = module.getChildren();
			classCalculationTests45 = ModelTestUtils.getAssertClass( moduleChilds, "CalculationTests" );
			//Function test:setUp
			{
			IMethod methodsetUp46;
				IModelElement[] classCalculationTests45Childs = classCalculationTests45.getChildren();
				methodsetUp46 = ModelTestUtils.getAssertMethod( classCalculationTests45Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp46, new String[] {"self"} );
			}
			//Function test:test_julian_calculation
			{
			IMethod methodtest_julian_calculation47;
				IModelElement[] classCalculationTests45Childs = classCalculationTests45.getChildren();
				methodtest_julian_calculation47 = ModelTestUtils.getAssertMethod( classCalculationTests45Childs, "test_julian_calculation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_julian_calculation47, new String[] {"self"} );
			}
			//Function test:test_gregorian_calculation
			{
			IMethod methodtest_gregorian_calculation48;
				IModelElement[] classCalculationTests45Childs = classCalculationTests45.getChildren();
				methodtest_gregorian_calculation48 = ModelTestUtils.getAssertMethod( classCalculationTests45Childs, "test_gregorian_calculation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gregorian_calculation48, new String[] {"self"} );
			}
			//Function test:test_day_of_week_calculation
			{
			IMethod methodtest_day_of_week_calculation49;
				IModelElement[] classCalculationTests45Childs = classCalculationTests45.getChildren();
				methodtest_day_of_week_calculation49 = ModelTestUtils.getAssertMethod( classCalculationTests45Childs, "test_day_of_week_calculation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_day_of_week_calculation49, new String[] {"self"} );
			}
			//Function test:test_week_of_year_and_day_of_week_calculation
			{
			IMethod methodtest_week_of_year_and_day_of_week_calculation50;
				IModelElement[] classCalculationTests45Childs = classCalculationTests45.getChildren();
				methodtest_week_of_year_and_day_of_week_calculation50 = ModelTestUtils.getAssertMethod( classCalculationTests45Childs, "test_week_of_year_and_day_of_week_calculation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_week_of_year_and_day_of_week_calculation50, new String[] {"self"} );
				//Function test:test_helper
				{
				IMethod methodtest_helper51;
					IModelElement[] methodtest_week_of_year_and_day_of_week_calculation50Childs = methodtest_week_of_year_and_day_of_week_calculation50.getChildren();
					methodtest_helper51 = ModelTestUtils.getAssertMethod( methodtest_week_of_year_and_day_of_week_calculation50Childs, "test_helper", 2 );
					ModelTestUtils.assertParameterNames( methodtest_helper51, new String[] {"ymd_tuple", "test_reason"} );
				}
			}
		}
		//Class test
		{
		IType classCacheTests52;
			IModelElement[] moduleChilds = module.getChildren();
			classCacheTests52 = ModelTestUtils.getAssertClass( moduleChilds, "CacheTests" );
			//Function test:test_time_re_recreation
			{
			IMethod methodtest_time_re_recreation53;
				IModelElement[] classCacheTests52Childs = classCacheTests52.getChildren();
				methodtest_time_re_recreation53 = ModelTestUtils.getAssertMethod( classCacheTests52Childs, "test_time_re_recreation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_time_re_recreation53, new String[] {"self"} );
			}
			//Function test:test_regex_cleanup
			{
			IMethod methodtest_regex_cleanup54;
				IModelElement[] classCacheTests52Childs = classCacheTests52.getChildren();
				methodtest_regex_cleanup54 = ModelTestUtils.getAssertMethod( classCacheTests52Childs, "test_regex_cleanup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_regex_cleanup54, new String[] {"self"} );
			}
			//Function test:test_new_localetime
			{
			IMethod methodtest_new_localetime55;
				IModelElement[] classCacheTests52Childs = classCacheTests52.getChildren();
				methodtest_new_localetime55 = ModelTestUtils.getAssertMethod( classCacheTests52Childs, "test_new_localetime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_new_localetime55, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main56;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main56 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen189( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_curses.py"));

		assertNotNull("Module test_curses.py not found", module);
		assertEquals("test_curses.py", module.getElementName());
		
		//Function test:window_funcs
		{
		IMethod methodwindow_funcs0;
			IModelElement[] moduleChilds = module.getChildren();
			methodwindow_funcs0 = ModelTestUtils.getAssertMethod( moduleChilds, "window_funcs", 1 );
			ModelTestUtils.assertParameterNames( methodwindow_funcs0, new String[] {"stdscr"} );
		}
		//Function test:module_funcs
		{
		IMethod methodmodule_funcs1;
			IModelElement[] moduleChilds = module.getChildren();
			methodmodule_funcs1 = ModelTestUtils.getAssertMethod( moduleChilds, "module_funcs", 1 );
			ModelTestUtils.assertParameterNames( methodmodule_funcs1, new String[] {"stdscr"} );
		}
		//Function test:unit_tests
		{
		IMethod methodunit_tests2;
			IModelElement[] moduleChilds = module.getChildren();
			methodunit_tests2 = ModelTestUtils.getAssertMethod( moduleChilds, "unit_tests", 0 );
		}
		//Function test:main
		{
		IMethod methodmain3;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain3 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 1 );
			ModelTestUtils.assertParameterNames( methodmain3, new String[] {"stdscr"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "stdscr");
		}

	}
	public void testModelGen190( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_hexoct.py"));

		assertNotNull("Module test_hexoct.py not found", module);
		assertEquals("test_hexoct.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "platform_long_is_32_bits");
		}
		//Class test
		{
		IType classTextHexOct0;
			IModelElement[] moduleChilds = module.getChildren();
			classTextHexOct0 = ModelTestUtils.getAssertClass( moduleChilds, "TextHexOct" );
			//Function test:test_hex_baseline
			{
			IMethod methodtest_hex_baseline1;
				IModelElement[] classTextHexOct0Childs = classTextHexOct0.getChildren();
				methodtest_hex_baseline1 = ModelTestUtils.getAssertMethod( classTextHexOct0Childs, "test_hex_baseline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hex_baseline1, new String[] {"self"} );
			}
			//Function test:test_hex_unsigned
			{
			IMethod methodtest_hex_unsigned2;
				IModelElement[] classTextHexOct0Childs = classTextHexOct0.getChildren();
				methodtest_hex_unsigned2 = ModelTestUtils.getAssertMethod( classTextHexOct0Childs, "test_hex_unsigned", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hex_unsigned2, new String[] {"self"} );
			}
			//Function test:test_oct_baseline
			{
			IMethod methodtest_oct_baseline3;
				IModelElement[] classTextHexOct0Childs = classTextHexOct0.getChildren();
				methodtest_oct_baseline3 = ModelTestUtils.getAssertMethod( classTextHexOct0Childs, "test_oct_baseline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oct_baseline3, new String[] {"self"} );
			}
			//Function test:test_oct_unsigned
			{
			IMethod methodtest_oct_unsigned4;
				IModelElement[] classTextHexOct0Childs = classTextHexOct0.getChildren();
				methodtest_oct_unsigned4 = ModelTestUtils.getAssertMethod( classTextHexOct0Childs, "test_oct_unsigned", 1 );
				ModelTestUtils.assertParameterNames( methodtest_oct_unsigned4, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen191( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_new.py"));

		assertNotNull("Module test_new.py not found", module);
		assertEquals("test_new.py", module.getElementName());
		
		//Class test
		{
		IType classEggs0;
			IModelElement[] moduleChilds = module.getChildren();
			classEggs0 = ModelTestUtils.getAssertClass( moduleChilds, "Eggs" );
			//Function test:get_yolks
			{
			IMethod methodget_yolks1;
				IModelElement[] classEggs0Childs = classEggs0.getChildren();
				methodget_yolks1 = ModelTestUtils.getAssertMethod( classEggs0Childs, "get_yolks", 1 );
				ModelTestUtils.assertParameterNames( methodget_yolks1, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "m");
		}
		//Function test:get_more_yolks
		{
		IMethod methodget_more_yolks2;
			IModelElement[] moduleChilds = module.getChildren();
			methodget_more_yolks2 = ModelTestUtils.getAssertMethod( moduleChilds, "get_more_yolks", 1 );
			ModelTestUtils.assertParameterNames( methodget_more_yolks2, new String[] {"self"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "C");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "o");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "o");
		}
		//Function test:break_yolks
		{
		IMethod methodbreak_yolks3;
			IModelElement[] moduleChilds = module.getChildren();
			methodbreak_yolks3 = ModelTestUtils.getAssertMethod( moduleChilds, "break_yolks", 1 );
			ModelTestUtils.assertParameterNames( methodbreak_yolks3, new String[] {"self"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "im");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "codestr");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ccode");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "func");
		}
		//Function test:f
		{
		IMethod methodf4;
			IModelElement[] moduleChilds = module.getChildren();
			methodf4 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf4, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg5;
				IModelElement[] methodf4Childs = methodf4.getChildren();
				methodg5 = ModelTestUtils.getAssertMethod( methodf4Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg5, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g3");
		}
		//Function test:test_closure
		{
		IMethod methodtest_closure6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_closure6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_closure", 3 );
			ModelTestUtils.assertParameterNames( methodtest_closure6, new String[] {"func", "closure", "exc"} );
		}
		//Function test:f
		{
		IMethod methodf7;
			IModelElement[] moduleChilds = module.getChildren();
			methodf7 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf7, new String[] {"a"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "argcount");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nlocals");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "stacksize");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "flags");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "codestring");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "constants");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "names");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "varnames");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "filename");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "name");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "firstlineno");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "lnotab");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "freevars");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cellvars");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		//Class test
		{
		IType classS8;
			IModelElement[] moduleChilds = module.getChildren();
			classS8 = ModelTestUtils.getAssertClass( moduleChilds, "S" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}

	}
	public void testModelGen192( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_structseq.py"));

		assertNotNull("Module test_structseq.py not found", module);
		assertEquals("test_structseq.py", module.getElementName());
		
		//Class test
		{
		IType classStructSeqTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classStructSeqTest0 = ModelTestUtils.getAssertClass( moduleChilds, "StructSeqTest" );
			//Function test:test_tuple
			{
			IMethod methodtest_tuple1;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_tuple1 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tuple1, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr2;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_repr2 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr2, new String[] {"self"} );
			}
			//Function test:test_concat
			{
			IMethod methodtest_concat3;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_concat3 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_concat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_concat3, new String[] {"self"} );
			}
			//Function test:test_repeat
			{
			IMethod methodtest_repeat4;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_repeat4 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat4, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains5;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_contains5 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains5, new String[] {"self"} );
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash6;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_hash6 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash6, new String[] {"self"} );
			}
			//Function test:test_cmp
			{
			IMethod methodtest_cmp7;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_cmp7 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_cmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp7, new String[] {"self"} );
			}
			//Function test:test_fields
			{
			IMethod methodtest_fields8;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_fields8 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_fields", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fields8, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor9;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_constructor9 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor9, new String[] {"self"} );
			}
			//Function test:test_eviltuple
			{
			IMethod methodtest_eviltuple10;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_eviltuple10 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_eviltuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eviltuple10, new String[] {"self"} );
				//Class test
				{
				IType classExc11;
					IModelElement[] methodtest_eviltuple10Childs = methodtest_eviltuple10.getChildren();
					classExc11 = ModelTestUtils.getAssertClass( methodtest_eviltuple10Childs, "Exc" );
				}
				//Class test
				{
				IType classC12;
					IModelElement[] methodtest_eviltuple10Childs = methodtest_eviltuple10.getChildren();
					classC12 = ModelTestUtils.getAssertClass( methodtest_eviltuple10Childs, "C" );
					//Function test:__getitem__
					{
					IMethod method__getitem__13;
						IModelElement[] classC12Childs = classC12.getChildren();
						method__getitem__13 = ModelTestUtils.getAssertMethod( classC12Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__13, new String[] {"self", "i"} );
					}
					//Function test:__len__
					{
					IMethod method__len__14;
						IModelElement[] classC12Childs = classC12.getChildren();
						method__len__14 = ModelTestUtils.getAssertMethod( classC12Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__14, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reduce
			{
			IMethod methodtest_reduce15;
				IModelElement[] classStructSeqTest0Childs = classStructSeqTest0.getChildren();
				methodtest_reduce15 = ModelTestUtils.getAssertMethod( classStructSeqTest0Childs, "test_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen193( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_nis.py"));

		assertNotNull("Module test_nis.py not found", module);
		assertEquals("test_nis.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "maps");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "done");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mapping");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "done");
		}

	}
	public void testModelGen194( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_locale.py"));

		assertNotNull("Module test_locale.py not found", module);
		assertEquals("test_locale.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "oldlocale");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tloc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tloc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tloc");
		}
		//Function test:testformat
		{
		IMethod methodtestformat0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestformat0 = ModelTestUtils.getAssertMethod( moduleChilds, "testformat", 4 );
			ModelTestUtils.assertParameterNames( methodtestformat0, new String[] {"formatstr", "value", "grouping", "output"} );
		}
		//Function test:teststrop
		{
		IMethod methodteststrop1;
			IModelElement[] moduleChilds = module.getChildren();
			methodteststrop1 = ModelTestUtils.getAssertMethod( moduleChilds, "teststrop", 3 );
			ModelTestUtils.assertParameterNames( methodteststrop1, new String[] {"s", "method", "output"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "oldlocale");
		}

	}
	public void testModelGen195( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_imaplib.py"));

		assertNotNull("Module test_imaplib.py not found", module);
		assertEquals("test_imaplib.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "timevalues");
		}

	}
	public void testModelGen196( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_grammar.py"));

		assertNotNull("Module test_grammar.py not found", module);
		assertEquals("test_grammar.py", module.getElementName());
		
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "maxint");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f1
		{
		IMethod methodf10;
			IModelElement[] moduleChilds = module.getChildren();
			methodf10 = ModelTestUtils.getAssertMethod( moduleChilds, "f1", 0 );
		}
		//Function test:f2
		{
		IMethod methodf21;
			IModelElement[] moduleChilds = module.getChildren();
			methodf21 = ModelTestUtils.getAssertMethod( moduleChilds, "f2", 1 );
			ModelTestUtils.assertParameterNames( methodf21, new String[] {"one_argument"} );
		}
		//Function test:f3
		{
		IMethod methodf32;
			IModelElement[] moduleChilds = module.getChildren();
			methodf32 = ModelTestUtils.getAssertMethod( moduleChilds, "f3", 2 );
			ModelTestUtils.assertParameterNames( methodf32, new String[] {"two", "arguments"} );
		}
		//Function test:f4
		{
		IMethod methodf43;
			IModelElement[] moduleChilds = module.getChildren();
			methodf43 = ModelTestUtils.getAssertMethod( moduleChilds, "f4", 2 );
			ModelTestUtils.assertParameterNames( methodf43, new String[] {"two", "('compound', ('argument', 'list'))"} );
		}
		//Function test:f5
		{
		IMethod methodf54;
			IModelElement[] moduleChilds = module.getChildren();
			methodf54 = ModelTestUtils.getAssertMethod( moduleChilds, "f5", 2 );
			ModelTestUtils.assertParameterNames( methodf54, new String[] {"('compound', 'first')", "two"} );
		}
		//Function test:a1
		{
		IMethod methoda15;
			IModelElement[] moduleChilds = module.getChildren();
			methoda15 = ModelTestUtils.getAssertMethod( moduleChilds, "a1", 1 );
			ModelTestUtils.assertParameterNames( methoda15, new String[] {"one_arg"} );
		}
		//Function test:a2
		{
		IMethod methoda26;
			IModelElement[] moduleChilds = module.getChildren();
			methoda26 = ModelTestUtils.getAssertMethod( moduleChilds, "a2", 2 );
			ModelTestUtils.assertParameterNames( methoda26, new String[] {"two", "args"} );
		}
		//Function test:v0
		{
		IMethod methodv07;
			IModelElement[] moduleChilds = module.getChildren();
			methodv07 = ModelTestUtils.getAssertMethod( moduleChilds, "v0", 1 );
			ModelTestUtils.assertParameterNames( methodv07, new String[] {"rest"} );
		}
		//Function test:v1
		{
		IMethod methodv18;
			IModelElement[] moduleChilds = module.getChildren();
			methodv18 = ModelTestUtils.getAssertMethod( moduleChilds, "v1", 2 );
			ModelTestUtils.assertParameterNames( methodv18, new String[] {"a", "rest"} );
		}
		//Function test:v2
		{
		IMethod methodv29;
			IModelElement[] moduleChilds = module.getChildren();
			methodv29 = ModelTestUtils.getAssertMethod( moduleChilds, "v2", 3 );
			ModelTestUtils.assertParameterNames( methodv29, new String[] {"a", "b", "rest"} );
		}
		//Function test:v3
		{
		IMethod methodv310;
			IModelElement[] moduleChilds = module.getChildren();
			methodv310 = ModelTestUtils.getAssertMethod( moduleChilds, "v3", 3 );
			ModelTestUtils.assertParameterNames( methodv310, new String[] {"a", "('b', 'c')", "rest"} );
		}
		//Function test:d01
		{
		IMethod methodd0111;
			IModelElement[] moduleChilds = module.getChildren();
			methodd0111 = ModelTestUtils.getAssertMethod( moduleChilds, "d01", 1 );
			ModelTestUtils.assertParameterNames( methodd0111, new String[] {"a"} );
		}
		//Function test:d11
		{
		IMethod methodd1112;
			IModelElement[] moduleChilds = module.getChildren();
			methodd1112 = ModelTestUtils.getAssertMethod( moduleChilds, "d11", 2 );
			ModelTestUtils.assertParameterNames( methodd1112, new String[] {"a", "b"} );
		}
		//Function test:d21
		{
		IMethod methodd2113;
			IModelElement[] moduleChilds = module.getChildren();
			methodd2113 = ModelTestUtils.getAssertMethod( moduleChilds, "d21", 3 );
			ModelTestUtils.assertParameterNames( methodd2113, new String[] {"a", "b", "c"} );
		}
		//Function test:d02
		{
		IMethod methodd0214;
			IModelElement[] moduleChilds = module.getChildren();
			methodd0214 = ModelTestUtils.getAssertMethod( moduleChilds, "d02", 2 );
			ModelTestUtils.assertParameterNames( methodd0214, new String[] {"a", "b"} );
		}
		//Function test:d12
		{
		IMethod methodd1215;
			IModelElement[] moduleChilds = module.getChildren();
			methodd1215 = ModelTestUtils.getAssertMethod( moduleChilds, "d12", 3 );
			ModelTestUtils.assertParameterNames( methodd1215, new String[] {"a", "b", "c"} );
		}
		//Function test:d22
		{
		IMethod methodd2216;
			IModelElement[] moduleChilds = module.getChildren();
			methodd2216 = ModelTestUtils.getAssertMethod( moduleChilds, "d22", 4 );
			ModelTestUtils.assertParameterNames( methodd2216, new String[] {"a", "b", "c", "d"} );
		}
		//Function test:d01v
		{
		IMethod methodd01v17;
			IModelElement[] moduleChilds = module.getChildren();
			methodd01v17 = ModelTestUtils.getAssertMethod( moduleChilds, "d01v", 2 );
			ModelTestUtils.assertParameterNames( methodd01v17, new String[] {"a", "rest"} );
		}
		//Function test:d11v
		{
		IMethod methodd11v18;
			IModelElement[] moduleChilds = module.getChildren();
			methodd11v18 = ModelTestUtils.getAssertMethod( moduleChilds, "d11v", 3 );
			ModelTestUtils.assertParameterNames( methodd11v18, new String[] {"a", "b", "rest"} );
		}
		//Function test:d21v
		{
		IMethod methodd21v19;
			IModelElement[] moduleChilds = module.getChildren();
			methodd21v19 = ModelTestUtils.getAssertMethod( moduleChilds, "d21v", 4 );
			ModelTestUtils.assertParameterNames( methodd21v19, new String[] {"a", "b", "c", "rest"} );
		}
		//Function test:d02v
		{
		IMethod methodd02v20;
			IModelElement[] moduleChilds = module.getChildren();
			methodd02v20 = ModelTestUtils.getAssertMethod( moduleChilds, "d02v", 3 );
			ModelTestUtils.assertParameterNames( methodd02v20, new String[] {"a", "b", "rest"} );
		}
		//Function test:d12v
		{
		IMethod methodd12v21;
			IModelElement[] moduleChilds = module.getChildren();
			methodd12v21 = ModelTestUtils.getAssertMethod( moduleChilds, "d12v", 4 );
			ModelTestUtils.assertParameterNames( methodd12v21, new String[] {"a", "b", "c", "rest"} );
		}
		//Function test:d22v
		{
		IMethod methodd22v22;
			IModelElement[] moduleChilds = module.getChildren();
			methodd22v22 = ModelTestUtils.getAssertMethod( moduleChilds, "d22v", 5 );
			ModelTestUtils.assertParameterNames( methodd22v22, new String[] {"a", "b", "c", "d", "rest"} );
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "abc");
		}
		//Class test
		{
		IType classGulp23;
			IModelElement[] moduleChilds = module.getChildren();
			classGulp23 = ModelTestUtils.getAssertClass( moduleChilds, "Gulp" );
			//Function test:write
			{
			IMethod methodwrite24;
				IModelElement[] classGulp23Childs = classGulp23.getChildren();
				methodwrite24 = ModelTestUtils.getAssertMethod( classGulp23Childs, "write", 2 );
				ModelTestUtils.assertParameterNames( methodwrite24, new String[] {"self", "msg"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "gulp");
		}
		//Function test:driver
		{
		IMethod methoddriver25;
			IModelElement[] moduleChilds = module.getChildren();
			methoddriver25 = ModelTestUtils.getAssertMethod( moduleChilds, "driver", 0 );
		}
		//Function test:tellme
		{
		IMethod methodtellme26;
			IModelElement[] moduleChilds = module.getChildren();
			methodtellme26 = ModelTestUtils.getAssertMethod( moduleChilds, "tellme", 1 );
			ModelTestUtils.assertParameterNames( methodtellme26, new String[] {"file"} );
		}
		//Function test:tellme
		{
		IMethod methodtellme27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtellme27 = ModelTestUtils.getAssertMethod( moduleChilds, "tellme", 1 );
			ModelTestUtils.assertParameterNames( methodtellme27, new String[] {"file"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "i");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "i");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "msg");
		}
		//Function test:test_break_continue_loop
		{
		IMethod methodtest_break_continue_loop28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_break_continue_loop28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_break_continue_loop", 2 );
			ModelTestUtils.assertParameterNames( methodtest_break_continue_loop28, new String[] {"extra_burning_oil", "count"} );
		}
		//Function test:g1
		{
		IMethod methodg129;
			IModelElement[] moduleChilds = module.getChildren();
			methodg129 = ModelTestUtils.getAssertMethod( moduleChilds, "g1", 0 );
		}
		//Function test:g2
		{
		IMethod methodg230;
			IModelElement[] moduleChilds = module.getChildren();
			methodg230 = ModelTestUtils.getAssertMethod( moduleChilds, "g2", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f
		{
		IMethod methodf31;
			IModelElement[] moduleChilds = module.getChildren();
			methodf31 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
		}
		//Function test:f
		{
		IMethod methodf32;
			IModelElement[] moduleChilds = module.getChildren();
			methodf32 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "l");
		}
		//Class test
		{
		IType classSquares33;
			IModelElement[] moduleChilds = module.getChildren();
			classSquares33 = ModelTestUtils.getAssertClass( moduleChilds, "Squares" );
			//Function test:__init__
			{
			IMethod method__init__34;
				IModelElement[] classSquares33Childs = classSquares33.getChildren();
				method__init__34 = ModelTestUtils.getAssertMethod( classSquares33Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__34, new String[] {"self", "max"} );
			}
			//Function test:__len__
			{
			IMethod method__len__35;
				IModelElement[] classSquares33Childs = classSquares33.getChildren();
				method__len__35 = ModelTestUtils.getAssertMethod( classSquares33Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__35, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__36;
				IModelElement[] classSquares33Childs = classSquares33.getChildren();
				method__getitem__36 = ModelTestUtils.getAssertMethod( classSquares33Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__36, new String[] {"self", "i"} );
			}
		}
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Class test
		{
		IType classB37;
			IModelElement[] moduleChilds = module.getChildren();
			classB37 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
		}
		//Class test
		{
		IType classC138;
			IModelElement[] moduleChilds = module.getChildren();
			classC138 = ModelTestUtils.getAssertClass( moduleChilds, "C1" );
		}
		//Class test
		{
		IType classC239;
			IModelElement[] moduleChilds = module.getChildren();
			classC239 = ModelTestUtils.getAssertClass( moduleChilds, "C2" );
		}
		//Class test
		{
		IType classD40;
			IModelElement[] moduleChilds = module.getChildren();
			classD40 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
		}
		//Class test
		{
		IType classC41;
			IModelElement[] moduleChilds = module.getChildren();
			classC41 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:meth1
			{
			IMethod methodmeth142;
				IModelElement[] classC41Childs = classC41.getChildren();
				methodmeth142 = ModelTestUtils.getAssertMethod( classC41Childs, "meth1", 1 );
				ModelTestUtils.assertParameterNames( methodmeth142, new String[] {"self"} );
			}
			//Function test:meth2
			{
			IMethod methodmeth243;
				IModelElement[] classC41Childs = classC41.getChildren();
				methodmeth243 = ModelTestUtils.getAssertMethod( classC41Childs, "meth2", 2 );
				ModelTestUtils.assertParameterNames( methodmeth243, new String[] {"self", "arg"} );
			}
			//Function test:meth3
			{
			IMethod methodmeth344;
				IModelElement[] classC41Childs = classC41.getChildren();
				methodmeth344 = ModelTestUtils.getAssertMethod( classC41Childs, "meth3", 3 );
				ModelTestUtils.assertParameterNames( methodmeth344, new String[] {"self", "a1", "a2"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nums");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "strs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "spcs");
		}
		//Function test:test_in_func
		{
		IMethod methodtest_in_func45;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_in_func45 = ModelTestUtils.getAssertMethod( moduleChilds, "test_in_func", 1 );
			ModelTestUtils.assertParameterNames( methodtest_in_func45, new String[] {"l"} );
		}
		//Function test:test_nested_front
		{
		IMethod methodtest_nested_front46;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_nested_front46 = ModelTestUtils.getAssertMethod( moduleChilds, "test_nested_front", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "suppliers");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "parts");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "suppart");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}

	}
	public void testModelGen197( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_difflib.py"));

		assertNotNull("Module test_difflib.py not found", module);
		assertEquals("test_difflib.py", module.getElementName());
		
		//Class test
		{
		IType classTestSFbugs0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSFbugs0 = ModelTestUtils.getAssertClass( moduleChilds, "TestSFbugs" );
			//Function test:test_ratio_for_null_seqn
			{
			IMethod methodtest_ratio_for_null_seqn1;
				IModelElement[] classTestSFbugs0Childs = classTestSFbugs0.getChildren();
				methodtest_ratio_for_null_seqn1 = ModelTestUtils.getAssertMethod( classTestSFbugs0Childs, "test_ratio_for_null_seqn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ratio_for_null_seqn1, new String[] {"self"} );
			}
			//Function test:test_comparing_empty_lists
			{
			IMethod methodtest_comparing_empty_lists2;
				IModelElement[] classTestSFbugs0Childs = classTestSFbugs0.getChildren();
				methodtest_comparing_empty_lists2 = ModelTestUtils.getAssertMethod( classTestSFbugs0Childs, "test_comparing_empty_lists", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparing_empty_lists2, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "patch914575_from1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "patch914575_to1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "patch914575_from2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "patch914575_to2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "patch914575_from3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "patch914575_to3");
		}
		//Class test
		{
		IType classTestSFpatches3;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSFpatches3 = ModelTestUtils.getAssertClass( moduleChilds, "TestSFpatches" );
			//Function test:test_html_diff
			{
			IMethod methodtest_html_diff4;
				IModelElement[] classTestSFpatches3Childs = classTestSFpatches3.getChildren();
				methodtest_html_diff4 = ModelTestUtils.getAssertMethod( classTestSFpatches3Childs, "test_html_diff", 1 );
				ModelTestUtils.assertParameterNames( methodtest_html_diff4, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Doctests");
		}

	}
	public void testModelGen198( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_unittest.py"));

		assertNotNull("Module test_unittest.py not found", module);
		assertEquals("test_unittest.py", module.getElementName());
		
		//Function test:test_TestSuite_iter
		{
		IMethod methodtest_TestSuite_iter0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_TestSuite_iter0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_TestSuite_iter", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen199( ) throws Exception {
		String prj = "pytests_3";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_future.py"));

		assertNotNull("Module test_future.py not found", module);
		assertEquals("test_future.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rx");
		}
		//Function test:get_error_location
		{
		IMethod methodget_error_location0;
			IModelElement[] moduleChilds = module.getChildren();
			methodget_error_location0 = ModelTestUtils.getAssertMethod( moduleChilds, "get_error_location", 1 );
			ModelTestUtils.assertParameterNames( methodget_error_location0, new String[] {"msg"} );
		}
		//Class test
		{
		IType classFutureTest1;
			IModelElement[] moduleChilds = module.getChildren();
			classFutureTest1 = ModelTestUtils.getAssertClass( moduleChilds, "FutureTest" );
			//Function test:test_future1
			{
			IMethod methodtest_future12;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_future12 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_future1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_future12, new String[] {"self"} );
			}
			//Function test:test_future2
			{
			IMethod methodtest_future23;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_future23 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_future2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_future23, new String[] {"self"} );
			}
			//Function test:test_future3
			{
			IMethod methodtest_future34;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_future34 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_future3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_future34, new String[] {"self"} );
			}
			//Function test:test_badfuture3
			{
			IMethod methodtest_badfuture35;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture35 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture3", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture35, new String[] {"self"} );
			}
			//Function test:test_badfuture4
			{
			IMethod methodtest_badfuture46;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture46 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture46, new String[] {"self"} );
			}
			//Function test:test_badfuture5
			{
			IMethod methodtest_badfuture57;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture57 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture5", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture57, new String[] {"self"} );
			}
			//Function test:test_badfuture6
			{
			IMethod methodtest_badfuture68;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture68 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture6", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture68, new String[] {"self"} );
			}
			//Function test:test_badfuture7
			{
			IMethod methodtest_badfuture79;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture79 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture7", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture79, new String[] {"self"} );
			}
			//Function test:test_badfuture8
			{
			IMethod methodtest_badfuture810;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture810 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture8", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture810, new String[] {"self"} );
			}
			//Function test:test_badfuture9
			{
			IMethod methodtest_badfuture911;
				IModelElement[] classFutureTest1Childs = classFutureTest1.getChildren();
				methodtest_badfuture911 = ModelTestUtils.getAssertMethod( classFutureTest1Childs, "test_badfuture9", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badfuture911, new String[] {"self"} );
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
	