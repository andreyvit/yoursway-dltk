
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

public class GeneratedModelTests2 extends AbstractModelTests
{
	public GeneratedModelTests2(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests2.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_2", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests2" );
	}
	public void testModelGen100( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_xrange.py"));

		assertNotNull("Module test_xrange.py not found", module);
		assertEquals("test_xrange.py", module.getElementName());
		
		//Class test
		{
		IType classXrangeTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classXrangeTest0 = ModelTestUtils.getAssertClass( moduleChilds, "XrangeTest" );
			//Function test:test_xrange
			{
			IMethod methodtest_xrange1;
				IModelElement[] classXrangeTest0Childs = classXrangeTest0.getChildren();
				methodtest_xrange1 = ModelTestUtils.getAssertMethod( classXrangeTest0Childs, "test_xrange", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xrange1, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen101( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_compare.py"));

		assertNotNull("Module test_compare.py not found", module);
		assertEquals("test_compare.py", module.getElementName());
		
		//Class test
		{
		IType classEmpty0;
			IModelElement[] moduleChilds = module.getChildren();
			classEmpty0 = ModelTestUtils.getAssertClass( moduleChilds, "Empty" );
			//Function test:__repr__
			{
			IMethod method__repr__1;
				IModelElement[] classEmpty0Childs = classEmpty0.getChildren();
				method__repr__1 = ModelTestUtils.getAssertMethod( classEmpty0Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCoerce2;
			IModelElement[] moduleChilds = module.getChildren();
			classCoerce2 = ModelTestUtils.getAssertClass( moduleChilds, "Coerce" );
			//Function test:__init__
			{
			IMethod method__init__3;
				IModelElement[] classCoerce2Childs = classCoerce2.getChildren();
				method__init__3 = ModelTestUtils.getAssertMethod( classCoerce2Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__4;
				IModelElement[] classCoerce2Childs = classCoerce2.getChildren();
				method__repr__4 = ModelTestUtils.getAssertMethod( classCoerce2Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__4, new String[] {"self"} );
			}
			//Function test:__coerce__
			{
			IMethod method__coerce__5;
				IModelElement[] classCoerce2Childs = classCoerce2.getChildren();
				method__coerce__5 = ModelTestUtils.getAssertMethod( classCoerce2Childs, "__coerce__", 2 );
				ModelTestUtils.assertParameterNames( method__coerce__5, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classCmp6;
			IModelElement[] moduleChilds = module.getChildren();
			classCmp6 = ModelTestUtils.getAssertClass( moduleChilds, "Cmp" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classCmp6Childs = classCmp6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classCmp6Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__8;
				IModelElement[] classCmp6Childs = classCmp6.getChildren();
				method__repr__8 = ModelTestUtils.getAssertMethod( classCmp6Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__8, new String[] {"self"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__9;
				IModelElement[] classCmp6Childs = classCmp6.getChildren();
				method__cmp__9 = ModelTestUtils.getAssertMethod( classCmp6Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__9, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "candidates");
		}
		//Function test:test
		{
		IMethod methodtest10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest10 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen102( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_thread.py"));

		assertNotNull("Module test_thread.py not found", module);
		assertEquals("test_thread.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "mutex");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "rmutex");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "running");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "done");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "numtasks");
		}
		//Function test:task
		{
		IMethod methodtask0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtask0 = ModelTestUtils.getAssertMethod( moduleChilds, "task", 1 );
			ModelTestUtils.assertParameterNames( methodtask0, new String[] {"ident"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "next_ident");
		}
		//Function test:newtask
		{
		IMethod methodnewtask1;
			IModelElement[] moduleChilds = module.getChildren();
			methodnewtask1 = ModelTestUtils.getAssertMethod( moduleChilds, "newtask", 0 );
		}
		//Class test
		{
		IType classbarrier2;
			IModelElement[] moduleChilds = module.getChildren();
			classbarrier2 = ModelTestUtils.getAssertClass( moduleChilds, "barrier" );
			//Function test:__init__
			{
			IMethod method__init__3;
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				method__init__3 = ModelTestUtils.getAssertMethod( classbarrier2Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self", "n"} );
			}
			//Function test:enter
			{
			IMethod methodenter4;
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				methodenter4 = ModelTestUtils.getAssertMethod( classbarrier2Childs, "enter", 1 );
				ModelTestUtils.assertParameterNames( methodenter4, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "numtrips");
		}
		//Function test:task2
		{
		IMethod methodtask25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtask25 = ModelTestUtils.getAssertMethod( moduleChilds, "task2", 1 );
			ModelTestUtils.assertParameterNames( methodtask25, new String[] {"ident"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bar");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "running");
		}

	}
	public void testModelGen103( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_codeccallbacks.py"));

		assertNotNull("Module test_codeccallbacks.py not found", module);
		assertEquals("test_codeccallbacks.py", module.getElementName());
		
		//Class test
		{
		IType classPosReturn0;
			IModelElement[] moduleChilds = module.getChildren();
			classPosReturn0 = ModelTestUtils.getAssertClass( moduleChilds, "PosReturn" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classPosReturn0Childs = classPosReturn0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classPosReturn0Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self"} );
			}
			//Function test:handle
			{
			IMethod methodhandle2;
				IModelElement[] classPosReturn0Childs = classPosReturn0.getChildren();
				methodhandle2 = ModelTestUtils.getAssertMethod( classPosReturn0Childs, "handle", 2 );
				ModelTestUtils.assertParameterNames( methodhandle2, new String[] {"self", "exc"} );
			}
		}
		//Class test
		{
		IType classCodecCallbackTest3;
			IModelElement[] moduleChilds = module.getChildren();
			classCodecCallbackTest3 = ModelTestUtils.getAssertClass( moduleChilds, "CodecCallbackTest" );
			//Function test:test_xmlcharrefreplace
			{
			IMethod methodtest_xmlcharrefreplace4;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_xmlcharrefreplace4 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_xmlcharrefreplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharrefreplace4, new String[] {"self"} );
			}
			//Function test:test_xmlcharnamereplace
			{
			IMethod methodtest_xmlcharnamereplace5;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_xmlcharnamereplace5 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_xmlcharnamereplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharnamereplace5, new String[] {"self"} );
				//Function test:xmlcharnamereplace
				{
				IMethod methodxmlcharnamereplace6;
					IModelElement[] methodtest_xmlcharnamereplace5Childs = methodtest_xmlcharnamereplace5.getChildren();
					methodxmlcharnamereplace6 = ModelTestUtils.getAssertMethod( methodtest_xmlcharnamereplace5Childs, "xmlcharnamereplace", 1 );
					ModelTestUtils.assertParameterNames( methodxmlcharnamereplace6, new String[] {"exc"} );
				}
			}
			//Function test:test_uninamereplace
			{
			IMethod methodtest_uninamereplace7;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_uninamereplace7 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_uninamereplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uninamereplace7, new String[] {"self"} );
				//Function test:uninamereplace
				{
				IMethod methoduninamereplace8;
					IModelElement[] methodtest_uninamereplace7Childs = methodtest_uninamereplace7.getChildren();
					methoduninamereplace8 = ModelTestUtils.getAssertMethod( methodtest_uninamereplace7Childs, "uninamereplace", 1 );
					ModelTestUtils.assertParameterNames( methoduninamereplace8, new String[] {"exc"} );
				}
			}
			//Function test:test_backslashescape
			{
			IMethod methodtest_backslashescape9;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_backslashescape9 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_backslashescape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_backslashescape9, new String[] {"self"} );
			}
			//Function test:test_relaxedutf8
			{
			IMethod methodtest_relaxedutf810;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_relaxedutf810 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_relaxedutf8", 1 );
				ModelTestUtils.assertParameterNames( methodtest_relaxedutf810, new String[] {"self"} );
				//Function test:relaxedutf8
				{
				IMethod methodrelaxedutf811;
					IModelElement[] methodtest_relaxedutf810Childs = methodtest_relaxedutf810.getChildren();
					methodrelaxedutf811 = ModelTestUtils.getAssertMethod( methodtest_relaxedutf810Childs, "relaxedutf8", 1 );
					ModelTestUtils.assertParameterNames( methodrelaxedutf811, new String[] {"exc"} );
				}
			}
			//Function test:test_charmapencode
			{
			IMethod methodtest_charmapencode12;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_charmapencode12 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_charmapencode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_charmapencode12, new String[] {"self"} );
			}
			//Function test:test_callbacks
			{
			IMethod methodtest_callbacks13;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_callbacks13 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callbacks13, new String[] {"self"} );
				//Function test:handler1
				{
				IMethod methodhandler114;
					IModelElement[] methodtest_callbacks13Childs = methodtest_callbacks13.getChildren();
					methodhandler114 = ModelTestUtils.getAssertMethod( methodtest_callbacks13Childs, "handler1", 1 );
					ModelTestUtils.assertParameterNames( methodhandler114, new String[] {"exc"} );
				}
				//Function test:handler2
				{
				IMethod methodhandler215;
					IModelElement[] methodtest_callbacks13Childs = methodtest_callbacks13.getChildren();
					methodhandler215 = ModelTestUtils.getAssertMethod( methodtest_callbacks13Childs, "handler2", 1 );
					ModelTestUtils.assertParameterNames( methodhandler215, new String[] {"exc"} );
				}
			}
			//Function test:test_longstrings
			{
			IMethod methodtest_longstrings16;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_longstrings16 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_longstrings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longstrings16, new String[] {"self"} );
			}
			//Function test:check_exceptionobjectargs
			{
			IMethod methodcheck_exceptionobjectargs17;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodcheck_exceptionobjectargs17 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "check_exceptionobjectargs", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_exceptionobjectargs17, new String[] {"self", "exctype", "args", "msg"} );
			}
			//Function test:test_unicodeencodeerror
			{
			IMethod methodtest_unicodeencodeerror18;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_unicodeencodeerror18 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_unicodeencodeerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicodeencodeerror18, new String[] {"self"} );
			}
			//Function test:test_unicodedecodeerror
			{
			IMethod methodtest_unicodedecodeerror19;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_unicodedecodeerror19 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_unicodedecodeerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicodedecodeerror19, new String[] {"self"} );
			}
			//Function test:test_unicodetranslateerror
			{
			IMethod methodtest_unicodetranslateerror20;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_unicodetranslateerror20 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_unicodetranslateerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicodetranslateerror20, new String[] {"self"} );
			}
			//Function test:test_badandgoodstrictexceptions
			{
			IMethod methodtest_badandgoodstrictexceptions21;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badandgoodstrictexceptions21 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badandgoodstrictexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodstrictexceptions21, new String[] {"self"} );
			}
			//Function test:test_badandgoodignoreexceptions
			{
			IMethod methodtest_badandgoodignoreexceptions22;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badandgoodignoreexceptions22 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badandgoodignoreexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodignoreexceptions22, new String[] {"self"} );
			}
			//Function test:test_badandgoodreplaceexceptions
			{
			IMethod methodtest_badandgoodreplaceexceptions23;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badandgoodreplaceexceptions23 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badandgoodreplaceexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodreplaceexceptions23, new String[] {"self"} );
			}
			//Function test:test_badandgoodxmlcharrefreplaceexceptions
			{
			IMethod methodtest_badandgoodxmlcharrefreplaceexceptions24;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badandgoodxmlcharrefreplaceexceptions24 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badandgoodxmlcharrefreplaceexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodxmlcharrefreplaceexceptions24, new String[] {"self"} );
			}
			//Function test:test_badandgoodbackslashreplaceexceptions
			{
			IMethod methodtest_badandgoodbackslashreplaceexceptions25;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badandgoodbackslashreplaceexceptions25 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badandgoodbackslashreplaceexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodbackslashreplaceexceptions25, new String[] {"self"} );
			}
			//Function test:test_badhandlerresults
			{
			IMethod methodtest_badhandlerresults26;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badhandlerresults26 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badhandlerresults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badhandlerresults26, new String[] {"self"} );
			}
			//Function test:test_lookup
			{
			IMethod methodtest_lookup27;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_lookup27 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_lookup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lookup27, new String[] {"self"} );
			}
			//Function test:test_unencodablereplacement
			{
			IMethod methodtest_unencodablereplacement28;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_unencodablereplacement28 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_unencodablereplacement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unencodablereplacement28, new String[] {"self"} );
				//Function test:unencrepl
				{
				IMethod methodunencrepl29;
					IModelElement[] methodtest_unencodablereplacement28Childs = methodtest_unencodablereplacement28.getChildren();
					methodunencrepl29 = ModelTestUtils.getAssertMethod( methodtest_unencodablereplacement28Childs, "unencrepl", 1 );
					ModelTestUtils.assertParameterNames( methodunencrepl29, new String[] {"exc"} );
				}
			}
			//Function test:test_badregistercall
			{
			IMethod methodtest_badregistercall30;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_badregistercall30 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_badregistercall", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badregistercall30, new String[] {"self"} );
			}
			//Function test:test_unknownhandler
			{
			IMethod methodtest_unknownhandler31;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_unknownhandler31 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_unknownhandler", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unknownhandler31, new String[] {"self"} );
			}
			//Function test:test_xmlcharrefvalues
			{
			IMethod methodtest_xmlcharrefvalues32;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_xmlcharrefvalues32 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_xmlcharrefvalues", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharrefvalues32, new String[] {"self"} );
			}
			//Function test:test_decodehelper
			{
			IMethod methodtest_decodehelper33;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_decodehelper33 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_decodehelper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodehelper33, new String[] {"self"} );
				//Function test:baddecodereturn1
				{
				IMethod methodbaddecodereturn134;
					IModelElement[] methodtest_decodehelper33Childs = methodtest_decodehelper33.getChildren();
					methodbaddecodereturn134 = ModelTestUtils.getAssertMethod( methodtest_decodehelper33Childs, "baddecodereturn1", 1 );
					ModelTestUtils.assertParameterNames( methodbaddecodereturn134, new String[] {"exc"} );
				}
				//Function test:baddecodereturn2
				{
				IMethod methodbaddecodereturn235;
					IModelElement[] methodtest_decodehelper33Childs = methodtest_decodehelper33.getChildren();
					methodbaddecodereturn235 = ModelTestUtils.getAssertMethod( methodtest_decodehelper33Childs, "baddecodereturn2", 1 );
					ModelTestUtils.assertParameterNames( methodbaddecodereturn235, new String[] {"exc"} );
				}
				//Class test
				{
				IType classD36;
					IModelElement[] methodtest_decodehelper33Childs = methodtest_decodehelper33.getChildren();
					classD36 = ModelTestUtils.getAssertClass( methodtest_decodehelper33Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__37;
						IModelElement[] classD36Childs = classD36.getChildren();
						method__getitem__37 = ModelTestUtils.getAssertMethod( classD36Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__37, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_encodehelper
			{
			IMethod methodtest_encodehelper38;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_encodehelper38 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_encodehelper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encodehelper38, new String[] {"self"} );
				//Function test:badencodereturn1
				{
				IMethod methodbadencodereturn139;
					IModelElement[] methodtest_encodehelper38Childs = methodtest_encodehelper38.getChildren();
					methodbadencodereturn139 = ModelTestUtils.getAssertMethod( methodtest_encodehelper38Childs, "badencodereturn1", 1 );
					ModelTestUtils.assertParameterNames( methodbadencodereturn139, new String[] {"exc"} );
				}
				//Function test:badencodereturn2
				{
				IMethod methodbadencodereturn240;
					IModelElement[] methodtest_encodehelper38Childs = methodtest_encodehelper38.getChildren();
					methodbadencodereturn240 = ModelTestUtils.getAssertMethod( methodtest_encodehelper38Childs, "badencodereturn2", 1 );
					ModelTestUtils.assertParameterNames( methodbadencodereturn240, new String[] {"exc"} );
				}
				//Class test
				{
				IType classD41;
					IModelElement[] methodtest_encodehelper38Childs = methodtest_encodehelper38.getChildren();
					classD41 = ModelTestUtils.getAssertClass( methodtest_encodehelper38Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__42;
						IModelElement[] classD41Childs = classD41.getChildren();
						method__getitem__42 = ModelTestUtils.getAssertMethod( classD41Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__42, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_translatehelper
			{
			IMethod methodtest_translatehelper43;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_translatehelper43 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_translatehelper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_translatehelper43, new String[] {"self"} );
				//Class test
				{
				IType classD44;
					IModelElement[] methodtest_translatehelper43Childs = methodtest_translatehelper43.getChildren();
					classD44 = ModelTestUtils.getAssertClass( methodtest_translatehelper43Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__45;
						IModelElement[] classD44Childs = classD44.getChildren();
						method__getitem__45 = ModelTestUtils.getAssertMethod( classD44Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__45, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_bug828737
			{
			IMethod methodtest_bug82873746;
				IModelElement[] classCodecCallbackTest3Childs = classCodecCallbackTest3.getChildren();
				methodtest_bug82873746 = ModelTestUtils.getAssertMethod( classCodecCallbackTest3Childs, "test_bug828737", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug82873746, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main47;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main47 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen104( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_rgbimg.py"));

		assertNotNull("Module test_rgbimg.py not found", module);
		assertEquals("test_rgbimg.py", module.getElementName());
		
		//Class test
		{
		IType classerror0;
			IModelElement[] moduleChilds = module.getChildren();
			classerror0 = ModelTestUtils.getAssertClass( moduleChilds, "error" );
		}
		//Function test:testimg
		{
		IMethod methodtestimg1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestimg1 = ModelTestUtils.getAssertMethod( moduleChilds, "testimg", 2 );
			ModelTestUtils.assertParameterNames( methodtestimg1, new String[] {"rgb_file", "raw_file"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "table");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "source");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "target");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ttob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ttob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ttob");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ttob");
		}

	}
	public void testModelGen105( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("tf_inherit_check.py"));

		assertNotNull("Module tf_inherit_check.py not found", module);
		assertEquals("tf_inherit_check.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "verbose");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fd");
		}

	}
	public void testModelGen106( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sort.py"));

		assertNotNull("Module test_sort.py not found", module);
		assertEquals("test_sort.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "nerrors");
		}
		//Function test:check
		{
		IMethod methodcheck0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheck0 = ModelTestUtils.getAssertMethod( moduleChilds, "check", 4 );
			ModelTestUtils.assertParameterNames( methodcheck0, new String[] {"tag", "expected", "raw", "compare"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sizes");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
		}
		//Class test
		{
		IType classComplains1;
			IModelElement[] moduleChilds = module.getChildren();
			classComplains1 = ModelTestUtils.getAssertClass( moduleChilds, "Complains" );
			{
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classComplains1Childs, "maybe_complain");
			}
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classComplains1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "i"} );
			}
			//Function test:__lt__
			{
			IMethod method__lt__3;
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				method__lt__3 = ModelTestUtils.getAssertMethod( classComplains1Childs, "__lt__", 2 );
				ModelTestUtils.assertParameterNames( method__lt__3, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__4;
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				method__repr__4 = ModelTestUtils.getAssertMethod( classComplains1Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStable5;
			IModelElement[] moduleChilds = module.getChildren();
			classStable5 = ModelTestUtils.getAssertClass( moduleChilds, "Stable" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classStable5Childs = classStable5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classStable5Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "key", "i"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__7;
				IModelElement[] classStable5Childs = classStable5.getChildren();
				method__cmp__7 = ModelTestUtils.getAssertMethod( classStable5Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__7, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__8;
				IModelElement[] classStable5Childs = classStable5.getChildren();
				method__repr__8 = ModelTestUtils.getAssertMethod( classStable5Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__8, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "y");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "it_complained");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "it_complained");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "augmented");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Class test
		{
		IType classTestBugs9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBugs9 = ModelTestUtils.getAssertClass( moduleChilds, "TestBugs" );
			//Function test:test_bug453523
			{
			IMethod methodtest_bug45352310;
				IModelElement[] classTestBugs9Childs = classTestBugs9.getChildren();
				methodtest_bug45352310 = ModelTestUtils.getAssertMethod( classTestBugs9Childs, "test_bug453523", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug45352310, new String[] {"self"} );
				//Class test
				{
				IType classC11;
					IModelElement[] methodtest_bug45352310Childs = methodtest_bug45352310.getChildren();
					classC11 = ModelTestUtils.getAssertClass( methodtest_bug45352310Childs, "C" );
					//Function test:__lt__
					{
					IMethod method__lt__12;
						IModelElement[] classC11Childs = classC11.getChildren();
						method__lt__12 = ModelTestUtils.getAssertMethod( classC11Childs, "__lt__", 2 );
						ModelTestUtils.assertParameterNames( method__lt__12, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_cmpNone
			{
			IMethod methodtest_cmpNone13;
				IModelElement[] classTestBugs9Childs = classTestBugs9.getChildren();
				methodtest_cmpNone13 = ModelTestUtils.getAssertMethod( classTestBugs9Childs, "test_cmpNone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmpNone13, new String[] {"self"} );
			}
			//Function test:test_undetected_mutation
			{
			IMethod methodtest_undetected_mutation14;
				IModelElement[] classTestBugs9Childs = classTestBugs9.getChildren();
				methodtest_undetected_mutation14 = ModelTestUtils.getAssertMethod( classTestBugs9Childs, "test_undetected_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_undetected_mutation14, new String[] {"self"} );
				//Function test:mutating_cmp
				{
				IMethod methodmutating_cmp15;
					IModelElement[] methodtest_undetected_mutation14Childs = methodtest_undetected_mutation14.getChildren();
					methodmutating_cmp15 = ModelTestUtils.getAssertMethod( methodtest_undetected_mutation14Childs, "mutating_cmp", 2 );
					ModelTestUtils.assertParameterNames( methodmutating_cmp15, new String[] {"x", "y"} );
				}
				//Function test:mutating_cmp
				{
				IMethod methodmutating_cmp16;
					IModelElement[] methodtest_undetected_mutation14Childs = methodtest_undetected_mutation14.getChildren();
					methodmutating_cmp16 = ModelTestUtils.getAssertMethod( methodtest_undetected_mutation14Childs, "mutating_cmp", 2 );
					ModelTestUtils.assertParameterNames( methodmutating_cmp16, new String[] {"x", "y"} );
				}
			}
		}
		//Class test
		{
		IType classTestDecorateSortUndecorate17;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDecorateSortUndecorate17 = ModelTestUtils.getAssertClass( moduleChilds, "TestDecorateSortUndecorate" );
			//Function test:test_decorated
			{
			IMethod methodtest_decorated18;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_decorated18 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_decorated", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decorated18, new String[] {"self"} );
			}
			//Function test:test_baddecorator
			{
			IMethod methodtest_baddecorator19;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_baddecorator19 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_baddecorator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_baddecorator19, new String[] {"self"} );
			}
			//Function test:test_stability
			{
			IMethod methodtest_stability20;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_stability20 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_stability", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stability20, new String[] {"self"} );
			}
			//Function test:test_cmp_and_key_combination
			{
			IMethod methodtest_cmp_and_key_combination21;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_cmp_and_key_combination21 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_cmp_and_key_combination", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp_and_key_combination21, new String[] {"self"} );
				//Function test:compare
				{
				IMethod methodcompare22;
					IModelElement[] methodtest_cmp_and_key_combination21Childs = methodtest_cmp_and_key_combination21.getChildren();
					methodcompare22 = ModelTestUtils.getAssertMethod( methodtest_cmp_and_key_combination21Childs, "compare", 2 );
					ModelTestUtils.assertParameterNames( methodcompare22, new String[] {"x", "y"} );
				}
			}
			//Function test:test_badcmp_with_key
			{
			IMethod methodtest_badcmp_with_key23;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_badcmp_with_key23 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_badcmp_with_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badcmp_with_key23, new String[] {"self"} );
			}
			//Function test:test_key_with_exception
			{
			IMethod methodtest_key_with_exception24;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_key_with_exception24 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_key_with_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_exception24, new String[] {"self"} );
			}
			//Function test:test_key_with_mutation
			{
			IMethod methodtest_key_with_mutation25;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_key_with_mutation25 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_key_with_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_mutation25, new String[] {"self"} );
				//Function test:k
				{
				IMethod methodk26;
					IModelElement[] methodtest_key_with_mutation25Childs = methodtest_key_with_mutation25.getChildren();
					methodk26 = ModelTestUtils.getAssertMethod( methodtest_key_with_mutation25Childs, "k", 1 );
					ModelTestUtils.assertParameterNames( methodk26, new String[] {"x"} );
				}
			}
			//Function test:test_key_with_mutating_del
			{
			IMethod methodtest_key_with_mutating_del27;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_key_with_mutating_del27 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_key_with_mutating_del", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_mutating_del27, new String[] {"self"} );
				//Class test
				{
				IType classSortKiller28;
					IModelElement[] methodtest_key_with_mutating_del27Childs = methodtest_key_with_mutating_del27.getChildren();
					classSortKiller28 = ModelTestUtils.getAssertClass( methodtest_key_with_mutating_del27Childs, "SortKiller" );
					//Function test:__init__
					{
					IMethod method__init__29;
						IModelElement[] classSortKiller28Childs = classSortKiller28.getChildren();
						method__init__29 = ModelTestUtils.getAssertMethod( classSortKiller28Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__29, new String[] {"self", "x"} );
					}
					//Function test:__del__
					{
					IMethod method__del__30;
						IModelElement[] classSortKiller28Childs = classSortKiller28.getChildren();
						method__del__30 = ModelTestUtils.getAssertMethod( classSortKiller28Childs, "__del__", 1 );
						ModelTestUtils.assertParameterNames( method__del__30, new String[] {"self"} );
					}
				}
			}
			//Function test:test_key_with_mutating_del_and_exception
			{
			IMethod methodtest_key_with_mutating_del_and_exception31;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_key_with_mutating_del_and_exception31 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_key_with_mutating_del_and_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_mutating_del_and_exception31, new String[] {"self"} );
				//Class test
				{
				IType classSortKiller32;
					IModelElement[] methodtest_key_with_mutating_del_and_exception31Childs = methodtest_key_with_mutating_del_and_exception31.getChildren();
					classSortKiller32 = ModelTestUtils.getAssertClass( methodtest_key_with_mutating_del_and_exception31Childs, "SortKiller" );
					//Function test:__init__
					{
					IMethod method__init__33;
						IModelElement[] classSortKiller32Childs = classSortKiller32.getChildren();
						method__init__33 = ModelTestUtils.getAssertMethod( classSortKiller32Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__33, new String[] {"self", "x"} );
					}
					//Function test:__del__
					{
					IMethod method__del__34;
						IModelElement[] classSortKiller32Childs = classSortKiller32.getChildren();
						method__del__34 = ModelTestUtils.getAssertMethod( classSortKiller32Childs, "__del__", 1 );
						ModelTestUtils.assertParameterNames( method__del__34, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reverse
			{
			IMethod methodtest_reverse35;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_reverse35 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_reverse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse35, new String[] {"self"} );
			}
			//Function test:test_reverse_stability
			{
			IMethod methodtest_reverse_stability36;
				IModelElement[] classTestDecorateSortUndecorate17Childs = classTestDecorateSortUndecorate17.getChildren();
				methodtest_reverse_stability36 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate17Childs, "test_reverse_stability", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse_stability36, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main37 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main37, new String[] {"verbose"} );
		}

	}
	public void testModelGen107( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_file.py"));

		assertNotNull("Module test_file.py not found", module);
		assertEquals("test_file.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "p");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "softspace");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "buf");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "a");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "l");
		}
		//Class test
		{
		IType classNonString0;
			IModelElement[] moduleChilds = module.getChildren();
			classNonString0 = ModelTestUtils.getAssertClass( moduleChilds, "NonString" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bad_mode");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "s");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "methods");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "method");
		}
		//Function test:bug801631
		{
		IMethod methodbug8016311;
			IModelElement[] moduleChilds = module.getChildren();
			methodbug8016311 = ModelTestUtils.getAssertMethod( moduleChilds, "bug801631", 0 );
		}

	}
	public void testModelGen108( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("seq_tests.py"));

		assertNotNull("Module seq_tests.py not found", module);
		assertEquals("seq_tests.py", module.getElementName());
		
		//Class test
		{
		IType classCommonTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classCommonTest0 = ModelTestUtils.getAssertClass( moduleChilds, "CommonTest" );
			{
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCommonTest0Childs, "type2test");
			}
			//Function test:test_constructors
			{
			IMethod methodtest_constructors1;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_constructors1 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_constructors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructors1, new String[] {"self"} );
				//Class test
				{
				IType classOtherSeq2;
					IModelElement[] methodtest_constructors1Childs = methodtest_constructors1.getChildren();
					classOtherSeq2 = ModelTestUtils.getAssertClass( methodtest_constructors1Childs, "OtherSeq" );
					//Function test:__init__
					{
					IMethod method__init__3;
						IModelElement[] classOtherSeq2Childs = classOtherSeq2.getChildren();
						method__init__3 = ModelTestUtils.getAssertMethod( classOtherSeq2Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self", "initseq"} );
					}
					//Function test:__len__
					{
					IMethod method__len__4;
						IModelElement[] classOtherSeq2Childs = classOtherSeq2.getChildren();
						method__len__4 = ModelTestUtils.getAssertMethod( classOtherSeq2Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__4, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__5;
						IModelElement[] classOtherSeq2Childs = classOtherSeq2.getChildren();
						method__getitem__5 = ModelTestUtils.getAssertMethod( classOtherSeq2Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__5, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_truth
			{
			IMethod methodtest_truth6;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_truth6 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_truth", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truth6, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem7;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_getitem7 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem7, new String[] {"self"} );
			}
			//Function test:test_getslice
			{
			IMethod methodtest_getslice8;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_getslice8 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_getslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getslice8, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains9;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_contains9 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains9, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len10;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_len10 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len10, new String[] {"self"} );
			}
			//Function test:test_minmax
			{
			IMethod methodtest_minmax11;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_minmax11 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_minmax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_minmax11, new String[] {"self"} );
			}
			//Function test:test_addmul
			{
			IMethod methodtest_addmul12;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_addmul12 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_addmul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addmul12, new String[] {"self"} );
				//Class test
				{
				IType classsubclass13;
					IModelElement[] methodtest_addmul12Childs = methodtest_addmul12.getChildren();
					classsubclass13 = ModelTestUtils.getAssertClass( methodtest_addmul12Childs, "subclass" );
				}
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd14;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_iadd14 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd14, new String[] {"self"} );
			}
			//Function test:test_imul
			{
			IMethod methodtest_imul15;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_imul15 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_imul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imul15, new String[] {"self"} );
			}
			//Function test:test_getitemoverwriteiter
			{
			IMethod methodtest_getitemoverwriteiter16;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_getitemoverwriteiter16 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_getitemoverwriteiter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitemoverwriteiter16, new String[] {"self"} );
				//Class test
				{
				IType classT17;
					IModelElement[] methodtest_getitemoverwriteiter16Childs = methodtest_getitemoverwriteiter16.getChildren();
					classT17 = ModelTestUtils.getAssertClass( methodtest_getitemoverwriteiter16Childs, "T" );
					//Function test:__getitem__
					{
					IMethod method__getitem__18;
						IModelElement[] classT17Childs = classT17.getChildren();
						method__getitem__18 = ModelTestUtils.getAssertMethod( classT17Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__18, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_repeat
			{
			IMethod methodtest_repeat19;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_repeat19 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat19, new String[] {"self"} );
			}
			//Function test:test_subscript
			{
			IMethod methodtest_subscript20;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_subscript20 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_subscript", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subscript20, new String[] {"self"} );
			}
		}

	}
	public void testModelGen109( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_isinstance.py"));

		assertNotNull("Module test_isinstance.py not found", module);
		assertEquals("test_isinstance.py", module.getElementName());
		
		//Class test
		{
		IType classTestIsInstanceExceptions0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIsInstanceExceptions0 = ModelTestUtils.getAssertClass( moduleChilds, "TestIsInstanceExceptions" );
			//Function test:test_class_has_no_bases
			{
			IMethod methodtest_class_has_no_bases1;
				IModelElement[] classTestIsInstanceExceptions0Childs = classTestIsInstanceExceptions0.getChildren();
				methodtest_class_has_no_bases1 = ModelTestUtils.getAssertMethod( classTestIsInstanceExceptions0Childs, "test_class_has_no_bases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_class_has_no_bases1, new String[] {"self"} );
				//Class test
				{
				IType classI2;
					IModelElement[] methodtest_class_has_no_bases1Childs = methodtest_class_has_no_bases1.getChildren();
					classI2 = ModelTestUtils.getAssertClass( methodtest_class_has_no_bases1Childs, "I" );
					//Function test:getclass
					{
					IMethod methodgetclass3;
						IModelElement[] classI2Childs = classI2.getChildren();
						methodgetclass3 = ModelTestUtils.getAssertMethod( classI2Childs, "getclass", 1 );
						ModelTestUtils.assertParameterNames( methodgetclass3, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classC4;
					IModelElement[] methodtest_class_has_no_bases1Childs = methodtest_class_has_no_bases1.getChildren();
					classC4 = ModelTestUtils.getAssertClass( methodtest_class_has_no_bases1Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases5;
						IModelElement[] classC4Childs = classC4.getChildren();
						methodgetbases5 = ModelTestUtils.getAssertMethod( classC4Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases5, new String[] {"self"} );
					}
				}
			}
			//Function test:test_bases_raises_other_than_attribute_error
			{
			IMethod methodtest_bases_raises_other_than_attribute_error6;
				IModelElement[] classTestIsInstanceExceptions0Childs = classTestIsInstanceExceptions0.getChildren();
				methodtest_bases_raises_other_than_attribute_error6 = ModelTestUtils.getAssertMethod( classTestIsInstanceExceptions0Childs, "test_bases_raises_other_than_attribute_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bases_raises_other_than_attribute_error6, new String[] {"self"} );
				//Class test
				{
				IType classE7;
					IModelElement[] methodtest_bases_raises_other_than_attribute_error6Childs = methodtest_bases_raises_other_than_attribute_error6.getChildren();
					classE7 = ModelTestUtils.getAssertClass( methodtest_bases_raises_other_than_attribute_error6Childs, "E" );
					//Function test:getbases
					{
					IMethod methodgetbases8;
						IModelElement[] classE7Childs = classE7.getChildren();
						methodgetbases8 = ModelTestUtils.getAssertMethod( classE7Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases8, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classI9;
					IModelElement[] methodtest_bases_raises_other_than_attribute_error6Childs = methodtest_bases_raises_other_than_attribute_error6.getChildren();
					classI9 = ModelTestUtils.getAssertClass( methodtest_bases_raises_other_than_attribute_error6Childs, "I" );
					//Function test:getclass
					{
					IMethod methodgetclass10;
						IModelElement[] classI9Childs = classI9.getChildren();
						methodgetclass10 = ModelTestUtils.getAssertMethod( classI9Childs, "getclass", 1 );
						ModelTestUtils.assertParameterNames( methodgetclass10, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classC11;
					IModelElement[] methodtest_bases_raises_other_than_attribute_error6Childs = methodtest_bases_raises_other_than_attribute_error6.getChildren();
					classC11 = ModelTestUtils.getAssertClass( methodtest_bases_raises_other_than_attribute_error6Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases12;
						IModelElement[] classC11Childs = classC11.getChildren();
						methodgetbases12 = ModelTestUtils.getAssertMethod( classC11Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases12, new String[] {"self"} );
					}
				}
			}
			//Function test:test_dont_mask_non_attribute_error
			{
			IMethod methodtest_dont_mask_non_attribute_error13;
				IModelElement[] classTestIsInstanceExceptions0Childs = classTestIsInstanceExceptions0.getChildren();
				methodtest_dont_mask_non_attribute_error13 = ModelTestUtils.getAssertMethod( classTestIsInstanceExceptions0Childs, "test_dont_mask_non_attribute_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_mask_non_attribute_error13, new String[] {"self"} );
				//Class test
				{
				IType classI14;
					IModelElement[] methodtest_dont_mask_non_attribute_error13Childs = methodtest_dont_mask_non_attribute_error13.getChildren();
					classI14 = ModelTestUtils.getAssertClass( methodtest_dont_mask_non_attribute_error13Childs, "I" );
				}
				//Class test
				{
				IType classC15;
					IModelElement[] methodtest_dont_mask_non_attribute_error13Childs = methodtest_dont_mask_non_attribute_error13.getChildren();
					classC15 = ModelTestUtils.getAssertClass( methodtest_dont_mask_non_attribute_error13Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases16;
						IModelElement[] classC15Childs = classC15.getChildren();
						methodgetbases16 = ModelTestUtils.getAssertMethod( classC15Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases16, new String[] {"self"} );
					}
				}
			}
			//Function test:test_mask_attribute_error
			{
			IMethod methodtest_mask_attribute_error17;
				IModelElement[] classTestIsInstanceExceptions0Childs = classTestIsInstanceExceptions0.getChildren();
				methodtest_mask_attribute_error17 = ModelTestUtils.getAssertMethod( classTestIsInstanceExceptions0Childs, "test_mask_attribute_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mask_attribute_error17, new String[] {"self"} );
				//Class test
				{
				IType classI18;
					IModelElement[] methodtest_mask_attribute_error17Childs = methodtest_mask_attribute_error17.getChildren();
					classI18 = ModelTestUtils.getAssertClass( methodtest_mask_attribute_error17Childs, "I" );
				}
				//Class test
				{
				IType classC19;
					IModelElement[] methodtest_mask_attribute_error17Childs = methodtest_mask_attribute_error17.getChildren();
					classC19 = ModelTestUtils.getAssertClass( methodtest_mask_attribute_error17Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases20;
						IModelElement[] classC19Childs = classC19.getChildren();
						methodgetbases20 = ModelTestUtils.getAssertMethod( classC19Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases20, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestIsSubclassExceptions21;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIsSubclassExceptions21 = ModelTestUtils.getAssertClass( moduleChilds, "TestIsSubclassExceptions" );
			//Function test:test_dont_mask_non_attribute_error
			{
			IMethod methodtest_dont_mask_non_attribute_error22;
				IModelElement[] classTestIsSubclassExceptions21Childs = classTestIsSubclassExceptions21.getChildren();
				methodtest_dont_mask_non_attribute_error22 = ModelTestUtils.getAssertMethod( classTestIsSubclassExceptions21Childs, "test_dont_mask_non_attribute_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_mask_non_attribute_error22, new String[] {"self"} );
				//Class test
				{
				IType classC23;
					IModelElement[] methodtest_dont_mask_non_attribute_error22Childs = methodtest_dont_mask_non_attribute_error22.getChildren();
					classC23 = ModelTestUtils.getAssertClass( methodtest_dont_mask_non_attribute_error22Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases24;
						IModelElement[] classC23Childs = classC23.getChildren();
						methodgetbases24 = ModelTestUtils.getAssertMethod( classC23Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases24, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classS25;
					IModelElement[] methodtest_dont_mask_non_attribute_error22Childs = methodtest_dont_mask_non_attribute_error22.getChildren();
					classS25 = ModelTestUtils.getAssertClass( methodtest_dont_mask_non_attribute_error22Childs, "S" );
				}
			}
			//Function test:test_mask_attribute_error
			{
			IMethod methodtest_mask_attribute_error26;
				IModelElement[] classTestIsSubclassExceptions21Childs = classTestIsSubclassExceptions21.getChildren();
				methodtest_mask_attribute_error26 = ModelTestUtils.getAssertMethod( classTestIsSubclassExceptions21Childs, "test_mask_attribute_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mask_attribute_error26, new String[] {"self"} );
				//Class test
				{
				IType classC27;
					IModelElement[] methodtest_mask_attribute_error26Childs = methodtest_mask_attribute_error26.getChildren();
					classC27 = ModelTestUtils.getAssertClass( methodtest_mask_attribute_error26Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases28;
						IModelElement[] classC27Childs = classC27.getChildren();
						methodgetbases28 = ModelTestUtils.getAssertMethod( classC27Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases28, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classS29;
					IModelElement[] methodtest_mask_attribute_error26Childs = methodtest_mask_attribute_error26.getChildren();
					classS29 = ModelTestUtils.getAssertClass( methodtest_mask_attribute_error26Childs, "S" );
				}
			}
			//Function test:test_dont_mask_non_attribute_error_in_cls_arg
			{
			IMethod methodtest_dont_mask_non_attribute_error_in_cls_arg30;
				IModelElement[] classTestIsSubclassExceptions21Childs = classTestIsSubclassExceptions21.getChildren();
				methodtest_dont_mask_non_attribute_error_in_cls_arg30 = ModelTestUtils.getAssertMethod( classTestIsSubclassExceptions21Childs, "test_dont_mask_non_attribute_error_in_cls_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_mask_non_attribute_error_in_cls_arg30, new String[] {"self"} );
				//Class test
				{
				IType classB31;
					IModelElement[] methodtest_dont_mask_non_attribute_error_in_cls_arg30Childs = methodtest_dont_mask_non_attribute_error_in_cls_arg30.getChildren();
					classB31 = ModelTestUtils.getAssertClass( methodtest_dont_mask_non_attribute_error_in_cls_arg30Childs, "B" );
				}
				//Class test
				{
				IType classC32;
					IModelElement[] methodtest_dont_mask_non_attribute_error_in_cls_arg30Childs = methodtest_dont_mask_non_attribute_error_in_cls_arg30.getChildren();
					classC32 = ModelTestUtils.getAssertClass( methodtest_dont_mask_non_attribute_error_in_cls_arg30Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases33;
						IModelElement[] classC32Childs = classC32.getChildren();
						methodgetbases33 = ModelTestUtils.getAssertMethod( classC32Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases33, new String[] {"self"} );
					}
				}
			}
			//Function test:test_mask_attribute_error_in_cls_arg
			{
			IMethod methodtest_mask_attribute_error_in_cls_arg34;
				IModelElement[] classTestIsSubclassExceptions21Childs = classTestIsSubclassExceptions21.getChildren();
				methodtest_mask_attribute_error_in_cls_arg34 = ModelTestUtils.getAssertMethod( classTestIsSubclassExceptions21Childs, "test_mask_attribute_error_in_cls_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mask_attribute_error_in_cls_arg34, new String[] {"self"} );
				//Class test
				{
				IType classB35;
					IModelElement[] methodtest_mask_attribute_error_in_cls_arg34Childs = methodtest_mask_attribute_error_in_cls_arg34.getChildren();
					classB35 = ModelTestUtils.getAssertClass( methodtest_mask_attribute_error_in_cls_arg34Childs, "B" );
				}
				//Class test
				{
				IType classC36;
					IModelElement[] methodtest_mask_attribute_error_in_cls_arg34Childs = methodtest_mask_attribute_error_in_cls_arg34.getChildren();
					classC36 = ModelTestUtils.getAssertClass( methodtest_mask_attribute_error_in_cls_arg34Childs, "C" );
					//Function test:getbases
					{
					IMethod methodgetbases37;
						IModelElement[] classC36Childs = classC36.getChildren();
						methodgetbases37 = ModelTestUtils.getAssertMethod( classC36Childs, "getbases", 1 );
						ModelTestUtils.assertParameterNames( methodgetbases37, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classAbstractClass38;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractClass38 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractClass" );
			//Function test:__init__
			{
			IMethod method__init__39;
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				method__init__39 = ModelTestUtils.getAssertMethod( classAbstractClass38Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__39, new String[] {"self", "bases"} );
			}
			//Function test:getbases
			{
			IMethod methodgetbases40;
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				methodgetbases40 = ModelTestUtils.getAssertMethod( classAbstractClass38Childs, "getbases", 1 );
				ModelTestUtils.assertParameterNames( methodgetbases40, new String[] {"self"} );
			}
			{
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractClass38Childs, "__bases__");
			}
			//Function test:__call__
			{
			IMethod method__call__41;
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				method__call__41 = ModelTestUtils.getAssertMethod( classAbstractClass38Childs, "__call__", 1 );
				ModelTestUtils.assertParameterNames( method__call__41, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classAbstractInstance42;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractInstance42 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractInstance" );
			//Function test:__init__
			{
			IMethod method__init__43;
				IModelElement[] classAbstractInstance42Childs = classAbstractInstance42.getChildren();
				method__init__43 = ModelTestUtils.getAssertMethod( classAbstractInstance42Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__43, new String[] {"self", "klass"} );
			}
			//Function test:getclass
			{
			IMethod methodgetclass44;
				IModelElement[] classAbstractInstance42Childs = classAbstractInstance42.getChildren();
				methodgetclass44 = ModelTestUtils.getAssertMethod( classAbstractInstance42Childs, "getclass", 1 );
				ModelTestUtils.assertParameterNames( methodgetclass44, new String[] {"self"} );
			}
			{
				IModelElement[] classAbstractInstance42Childs = classAbstractInstance42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractInstance42Childs, "__class__");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "AbstractSuper");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "AbstractChild");
		}
		//Class test
		{
		IType classSuper45;
			IModelElement[] moduleChilds = module.getChildren();
			classSuper45 = ModelTestUtils.getAssertClass( moduleChilds, "Super" );
		}
		//Class test
		{
		IType classChild46;
			IModelElement[] moduleChilds = module.getChildren();
			classChild46 = ModelTestUtils.getAssertClass( moduleChilds, "Child" );
		}
		//Class test
		{
		IType classNewSuper47;
			IModelElement[] moduleChilds = module.getChildren();
			classNewSuper47 = ModelTestUtils.getAssertClass( moduleChilds, "NewSuper" );
		}
		//Class test
		{
		IType classNewChild48;
			IModelElement[] moduleChilds = module.getChildren();
			classNewChild48 = ModelTestUtils.getAssertClass( moduleChilds, "NewChild" );
		}
		//Class test
		{
		IType classTestIsInstanceIsSubclass49;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIsInstanceIsSubclass49 = ModelTestUtils.getAssertClass( moduleChilds, "TestIsInstanceIsSubclass" );
			//Function test:test_isinstance_normal
			{
			IMethod methodtest_isinstance_normal50;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_isinstance_normal50 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_isinstance_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance_normal50, new String[] {"self"} );
			}
			//Function test:test_isinstance_abstract
			{
			IMethod methodtest_isinstance_abstract51;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_isinstance_abstract51 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_isinstance_abstract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance_abstract51, new String[] {"self"} );
			}
			//Function test:test_subclass_normal
			{
			IMethod methodtest_subclass_normal52;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_subclass_normal52 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_subclass_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_normal52, new String[] {"self"} );
			}
			//Function test:test_subclass_abstract
			{
			IMethod methodtest_subclass_abstract53;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_subclass_abstract53 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_subclass_abstract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_abstract53, new String[] {"self"} );
			}
			//Function test:test_subclass_tuple
			{
			IMethod methodtest_subclass_tuple54;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_subclass_tuple54 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_subclass_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_tuple54, new String[] {"self"} );
			}
			//Function test:test_subclass_recursion_limit
			{
			IMethod methodtest_subclass_recursion_limit55;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_subclass_recursion_limit55 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_subclass_recursion_limit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_recursion_limit55, new String[] {"self"} );
			}
			//Function test:test_isinstance_recursion_limit
			{
			IMethod methodtest_isinstance_recursion_limit56;
				IModelElement[] classTestIsInstanceIsSubclass49Childs = classTestIsInstanceIsSubclass49.getChildren();
				methodtest_isinstance_recursion_limit56 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass49Childs, "test_isinstance_recursion_limit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance_recursion_limit56, new String[] {"self"} );
			}
		}
		//Function test:blowstack
		{
		IMethod methodblowstack57;
			IModelElement[] moduleChilds = module.getChildren();
			methodblowstack57 = ModelTestUtils.getAssertMethod( moduleChilds, "blowstack", 3 );
			ModelTestUtils.assertParameterNames( methodblowstack57, new String[] {"fxn", "arg", "compare_to"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main58;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main58 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen110( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sunaudiodev.py"));

		assertNotNull("Module test_sunaudiodev.py not found", module);
		assertEquals("test_sunaudiodev.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "audiodev");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "audiodev");
		}
		//Function test:play_sound_file
		{
		IMethod methodplay_sound_file0;
			IModelElement[] moduleChilds = module.getChildren();
			methodplay_sound_file0 = ModelTestUtils.getAssertMethod( moduleChilds, "play_sound_file", 1 );
			ModelTestUtils.assertParameterNames( methodplay_sound_file0, new String[] {"path"} );
		}
		//Function test:test
		{
		IMethod methodtest1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest1 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen111( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_fork1.py"));

		assertNotNull("Module test_fork1.py not found", module);
		assertEquals("test_fork1.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "LONGSLEEP");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SHORTSLEEP");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NUM_THREADS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "alive");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "stop");
		}
		//Function test:f
		{
		IMethod methodf0;
			IModelElement[] moduleChilds = module.getChildren();
			methodf0 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf0, new String[] {"id"} );
		}
		//Function test:main
		{
		IMethod methodmain1;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain1 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen112( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_datetime.py"));

		assertNotNull("Module test_datetime.py not found", module);
		assertEquals("test_datetime.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pickle_choices");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "OTHERSTUFF");
		}
		//Class test
		{
		IType classTestModule0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestModule0 = ModelTestUtils.getAssertClass( moduleChilds, "TestModule" );
			//Function test:test_constants
			{
			IMethod methodtest_constants1;
				IModelElement[] classTestModule0Childs = classTestModule0.getChildren();
				methodtest_constants1 = ModelTestUtils.getAssertMethod( classTestModule0Childs, "test_constants", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constants1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFixedOffset2;
			IModelElement[] moduleChilds = module.getChildren();
			classFixedOffset2 = ModelTestUtils.getAssertClass( moduleChilds, "FixedOffset" );
			//Function test:__init__
			{
			IMethod method__init__3;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				method__init__3 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self", "offset", "name", "dstoffset"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__4;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				method__repr__4 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__4, new String[] {"self"} );
			}
			//Function test:utcoffset
			{
			IMethod methodutcoffset5;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				methodutcoffset5 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "utcoffset", 2 );
				ModelTestUtils.assertParameterNames( methodutcoffset5, new String[] {"self", "dt"} );
			}
			//Function test:tzname
			{
			IMethod methodtzname6;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				methodtzname6 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "tzname", 2 );
				ModelTestUtils.assertParameterNames( methodtzname6, new String[] {"self", "dt"} );
			}
			//Function test:dst
			{
			IMethod methoddst7;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				methoddst7 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "dst", 2 );
				ModelTestUtils.assertParameterNames( methoddst7, new String[] {"self", "dt"} );
			}
		}
		//Class test
		{
		IType classPicklableFixedOffset8;
			IModelElement[] moduleChilds = module.getChildren();
			classPicklableFixedOffset8 = ModelTestUtils.getAssertClass( moduleChilds, "PicklableFixedOffset" );
			//Function test:__init__
			{
			IMethod method__init__9;
				IModelElement[] classPicklableFixedOffset8Childs = classPicklableFixedOffset8.getChildren();
				method__init__9 = ModelTestUtils.getAssertMethod( classPicklableFixedOffset8Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__9, new String[] {"self", "offset", "name", "dstoffset"} );
			}
		}
		//Class test
		{
		IType classTestTZInfo10;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTZInfo10 = ModelTestUtils.getAssertClass( moduleChilds, "TestTZInfo" );
			//Function test:test_non_abstractness
			{
			IMethod methodtest_non_abstractness11;
				IModelElement[] classTestTZInfo10Childs = classTestTZInfo10.getChildren();
				methodtest_non_abstractness11 = ModelTestUtils.getAssertMethod( classTestTZInfo10Childs, "test_non_abstractness", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_abstractness11, new String[] {"self"} );
			}
			//Function test:test_subclass_must_override
			{
			IMethod methodtest_subclass_must_override12;
				IModelElement[] classTestTZInfo10Childs = classTestTZInfo10.getChildren();
				methodtest_subclass_must_override12 = ModelTestUtils.getAssertMethod( classTestTZInfo10Childs, "test_subclass_must_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_must_override12, new String[] {"self"} );
				//Class test
				{
				IType classNotEnough13;
					IModelElement[] methodtest_subclass_must_override12Childs = methodtest_subclass_must_override12.getChildren();
					classNotEnough13 = ModelTestUtils.getAssertClass( methodtest_subclass_must_override12Childs, "NotEnough" );
					//Function test:__init__
					{
					IMethod method__init__14;
						IModelElement[] classNotEnough13Childs = classNotEnough13.getChildren();
						method__init__14 = ModelTestUtils.getAssertMethod( classNotEnough13Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__14, new String[] {"self", "offset", "name"} );
					}
				}
			}
			//Function test:test_normal
			{
			IMethod methodtest_normal15;
				IModelElement[] classTestTZInfo10Childs = classTestTZInfo10.getChildren();
				methodtest_normal15 = ModelTestUtils.getAssertMethod( classTestTZInfo10Childs, "test_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_normal15, new String[] {"self"} );
			}
			//Function test:test_pickling_base
			{
			IMethod methodtest_pickling_base16;
				IModelElement[] classTestTZInfo10Childs = classTestTZInfo10.getChildren();
				methodtest_pickling_base16 = ModelTestUtils.getAssertMethod( classTestTZInfo10Childs, "test_pickling_base", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_base16, new String[] {"self"} );
			}
			//Function test:test_pickling_subclass
			{
			IMethod methodtest_pickling_subclass17;
				IModelElement[] classTestTZInfo10Childs = classTestTZInfo10.getChildren();
				methodtest_pickling_subclass17 = ModelTestUtils.getAssertMethod( classTestTZInfo10Childs, "test_pickling_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classHarmlessMixedComparison18;
			IModelElement[] moduleChilds = module.getChildren();
			classHarmlessMixedComparison18 = ModelTestUtils.getAssertClass( moduleChilds, "HarmlessMixedComparison" );
			//Function test:test_harmless_mixed_comparison
			{
			IMethod methodtest_harmless_mixed_comparison19;
				IModelElement[] classHarmlessMixedComparison18Childs = classHarmlessMixedComparison18.getChildren();
				methodtest_harmless_mixed_comparison19 = ModelTestUtils.getAssertMethod( classHarmlessMixedComparison18Childs, "test_harmless_mixed_comparison", 1 );
				ModelTestUtils.assertParameterNames( methodtest_harmless_mixed_comparison19, new String[] {"self"} );
			}
			//Function test:test_harmful_mixed_comparison
			{
			IMethod methodtest_harmful_mixed_comparison20;
				IModelElement[] classHarmlessMixedComparison18Childs = classHarmlessMixedComparison18.getChildren();
				methodtest_harmful_mixed_comparison20 = ModelTestUtils.getAssertMethod( classHarmlessMixedComparison18Childs, "test_harmful_mixed_comparison", 1 );
				ModelTestUtils.assertParameterNames( methodtest_harmful_mixed_comparison20, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestTimeDelta21;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTimeDelta21 = ModelTestUtils.getAssertClass( moduleChilds, "TestTimeDelta" );
			{
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimeDelta21Childs, "theclass");
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor22;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_constructor22 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor22, new String[] {"self"} );
			}
			//Function test:test_computations
			{
			IMethod methodtest_computations23;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_computations23 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_computations23, new String[] {"self"} );
			}
			//Function test:test_disallowed_computations
			{
			IMethod methodtest_disallowed_computations24;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_disallowed_computations24 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_disallowed_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_disallowed_computations24, new String[] {"self"} );
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes25;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_basic_attributes25 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes25, new String[] {"self"} );
			}
			//Function test:test_carries
			{
			IMethod methodtest_carries26;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_carries26 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_carries", 1 );
				ModelTestUtils.assertParameterNames( methodtest_carries26, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality27;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_hash_equality27 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality27, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling28;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_pickling28 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling28, new String[] {"self"} );
			}
			//Function test:test_compare
			{
			IMethod methodtest_compare29;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_compare29 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compare29, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str30;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_str30 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str30, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip31;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_roundtrip31 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip31, new String[] {"self"} );
			}
			//Function test:test_resolution_info
			{
			IMethod methodtest_resolution_info32;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_resolution_info32 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_resolution_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_resolution_info32, new String[] {"self"} );
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow33;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_overflow33 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow33, new String[] {"self"} );
			}
			//Function test:test_microsecond_rounding
			{
			IMethod methodtest_microsecond_rounding34;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_microsecond_rounding34 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_microsecond_rounding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_microsecond_rounding34, new String[] {"self"} );
			}
			//Function test:test_massive_normalization
			{
			IMethod methodtest_massive_normalization35;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_massive_normalization35 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_massive_normalization", 1 );
				ModelTestUtils.assertParameterNames( methodtest_massive_normalization35, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool36;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_bool36 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool36, new String[] {"self"} );
			}
			//Function test:test_subclass_timedelta
			{
			IMethod methodtest_subclass_timedelta37;
				IModelElement[] classTestTimeDelta21Childs = classTestTimeDelta21.getChildren();
				methodtest_subclass_timedelta37 = ModelTestUtils.getAssertMethod( classTestTimeDelta21Childs, "test_subclass_timedelta", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_timedelta37, new String[] {"self"} );
				//Class test
				{
				IType classT38;
					IModelElement[] methodtest_subclass_timedelta37Childs = methodtest_subclass_timedelta37.getChildren();
					classT38 = ModelTestUtils.getAssertClass( methodtest_subclass_timedelta37Childs, "T" );
					//Function test:from_td
					{
					IMethod methodfrom_td39;
						IModelElement[] classT38Childs = classT38.getChildren();
						methodfrom_td39 = ModelTestUtils.getAssertMethod( classT38Childs, "from_td", 1 );
						ModelTestUtils.assertParameterNames( methodfrom_td39, new String[] {"td"} );
					}
					//Function test:as_hours
					{
					IMethod methodas_hours40;
						IModelElement[] classT38Childs = classT38.getChildren();
						methodas_hours40 = ModelTestUtils.getAssertMethod( classT38Childs, "as_hours", 1 );
						ModelTestUtils.assertParameterNames( methodas_hours40, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestDateOnly41;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDateOnly41 = ModelTestUtils.getAssertClass( moduleChilds, "TestDateOnly" );
			//Function test:test_delta_non_days_ignored
			{
			IMethod methodtest_delta_non_days_ignored42;
				IModelElement[] classTestDateOnly41Childs = classTestDateOnly41.getChildren();
				methodtest_delta_non_days_ignored42 = ModelTestUtils.getAssertMethod( classTestDateOnly41Childs, "test_delta_non_days_ignored", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delta_non_days_ignored42, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSubclassDate43;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassDate43 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassDate" );
			{
				IModelElement[] classSubclassDate43Childs = classSubclassDate43.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassDate43Childs, "sub_var");
			}
		}
		//Class test
		{
		IType classTestDate44;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDate44 = ModelTestUtils.getAssertClass( moduleChilds, "TestDate" );
			{
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDate44Childs, "theclass");
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes45;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_basic_attributes45 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes45, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip46;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_roundtrip46 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip46, new String[] {"self"} );
			}
			//Function test:test_ordinal_conversions
			{
			IMethod methodtest_ordinal_conversions47;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_ordinal_conversions47 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_ordinal_conversions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ordinal_conversions47, new String[] {"self"} );
			}
			//Function test:test_extreme_ordinals
			{
			IMethod methodtest_extreme_ordinals48;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_extreme_ordinals48 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_extreme_ordinals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extreme_ordinals48, new String[] {"self"} );
			}
			//Function test:test_bad_constructor_arguments
			{
			IMethod methodtest_bad_constructor_arguments49;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_bad_constructor_arguments49 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_bad_constructor_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_constructor_arguments49, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality50;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_hash_equality50 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality50, new String[] {"self"} );
			}
			//Function test:test_computations
			{
			IMethod methodtest_computations51;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_computations51 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_computations51, new String[] {"self"} );
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow52;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_overflow52 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow52, new String[] {"self"} );
			}
			//Function test:test_fromtimestamp
			{
			IMethod methodtest_fromtimestamp53;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_fromtimestamp53 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromtimestamp53, new String[] {"self"} );
			}
			//Function test:test_insane_fromtimestamp
			{
			IMethod methodtest_insane_fromtimestamp54;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_insane_fromtimestamp54 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_insane_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_fromtimestamp54, new String[] {"self"} );
			}
			//Function test:test_today
			{
			IMethod methodtest_today55;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_today55 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_today", 1 );
				ModelTestUtils.assertParameterNames( methodtest_today55, new String[] {"self"} );
			}
			//Function test:test_weekday
			{
			IMethod methodtest_weekday56;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_weekday56 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_weekday", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weekday56, new String[] {"self"} );
			}
			//Function test:test_isocalendar
			{
			IMethod methodtest_isocalendar57;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_isocalendar57 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_isocalendar", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isocalendar57, new String[] {"self"} );
			}
			//Function test:test_iso_long_years
			{
			IMethod methodtest_iso_long_years58;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_iso_long_years58 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_iso_long_years", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iso_long_years58, new String[] {"self"} );
			}
			//Function test:test_isoformat
			{
			IMethod methodtest_isoformat59;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_isoformat59 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isoformat59, new String[] {"self"} );
			}
			//Function test:test_ctime
			{
			IMethod methodtest_ctime60;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_ctime60 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_ctime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ctime60, new String[] {"self"} );
			}
			//Function test:test_strftime
			{
			IMethod methodtest_strftime61;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_strftime61 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime61, new String[] {"self"} );
			}
			//Function test:test_resolution_info
			{
			IMethod methodtest_resolution_info62;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_resolution_info62 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_resolution_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_resolution_info62, new String[] {"self"} );
			}
			//Function test:test_extreme_timedelta
			{
			IMethod methodtest_extreme_timedelta63;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_extreme_timedelta63 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_extreme_timedelta", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extreme_timedelta63, new String[] {"self"} );
			}
			//Function test:test_timetuple
			{
			IMethod methodtest_timetuple64;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_timetuple64 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_timetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_timetuple64, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling65;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_pickling65 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling65, new String[] {"self"} );
			}
			//Function test:test_compare
			{
			IMethod methodtest_compare66;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_compare66 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compare66, new String[] {"self"} );
			}
			//Function test:test_mixed_compare
			{
			IMethod methodtest_mixed_compare67;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_mixed_compare67 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_mixed_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_compare67, new String[] {"self"} );
				//Class test
				{
				IType classAnotherDateTimeClass68;
					IModelElement[] methodtest_mixed_compare67Childs = methodtest_mixed_compare67.getChildren();
					classAnotherDateTimeClass68 = ModelTestUtils.getAssertClass( methodtest_mixed_compare67Childs, "AnotherDateTimeClass" );
					//Function test:__cmp__
					{
					IMethod method__cmp__69;
						IModelElement[] classAnotherDateTimeClass68Childs = classAnotherDateTimeClass68.getChildren();
						method__cmp__69 = ModelTestUtils.getAssertMethod( classAnotherDateTimeClass68Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__69, new String[] {"self", "other"} );
					}
				}
				//Class test
				{
				IType classComparable70;
					IModelElement[] methodtest_mixed_compare67Childs = methodtest_mixed_compare67.getChildren();
					classComparable70 = ModelTestUtils.getAssertClass( methodtest_mixed_compare67Childs, "Comparable" );
					//Function test:timetuple
					{
					IMethod methodtimetuple71;
						IModelElement[] classComparable70Childs = classComparable70.getChildren();
						methodtimetuple71 = ModelTestUtils.getAssertMethod( classComparable70Childs, "timetuple", 1 );
						ModelTestUtils.assertParameterNames( methodtimetuple71, new String[] {"self"} );
					}
				}
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool72;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_bool72 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool72, new String[] {"self"} );
			}
			//Function test:test_srftime_out_of_range
			{
			IMethod methodtest_srftime_out_of_range73;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_srftime_out_of_range73 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_srftime_out_of_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_srftime_out_of_range73, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace74;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_replace74 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace74, new String[] {"self"} );
			}
			//Function test:test_subclass_date
			{
			IMethod methodtest_subclass_date75;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_subclass_date75 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_subclass_date", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_date75, new String[] {"self"} );
				//Class test
				{
				IType classC76;
					IModelElement[] methodtest_subclass_date75Childs = methodtest_subclass_date75.getChildren();
					classC76 = ModelTestUtils.getAssertClass( methodtest_subclass_date75Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__77;
						IModelElement[] classC76Childs = classC76.getChildren();
						method__new__77 = ModelTestUtils.getAssertMethod( classC76Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__77, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth78;
						IModelElement[] classC76Childs = classC76.getChildren();
						methodnewmeth78 = ModelTestUtils.getAssertMethod( classC76Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth78, new String[] {"self", "start"} );
					}
				}
			}
			//Function test:test_pickling_subclass_date
			{
			IMethod methodtest_pickling_subclass_date79;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_pickling_subclass_date79 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_pickling_subclass_date", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass_date79, new String[] {"self"} );
			}
			//Function test:test_backdoor_resistance
			{
			IMethod methodtest_backdoor_resistance80;
				IModelElement[] classTestDate44Childs = classTestDate44.getChildren();
				methodtest_backdoor_resistance80 = ModelTestUtils.getAssertMethod( classTestDate44Childs, "test_backdoor_resistance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_backdoor_resistance80, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSubclassDatetime81;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassDatetime81 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassDatetime" );
			{
				IModelElement[] classSubclassDatetime81Childs = classSubclassDatetime81.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassDatetime81Childs, "sub_var");
			}
		}
		//Class test
		{
		IType classTestDateTime82;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDateTime82 = ModelTestUtils.getAssertClass( moduleChilds, "TestDateTime" );
			{
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDateTime82Childs, "theclass");
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes83;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_basic_attributes83 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes83, new String[] {"self"} );
			}
			//Function test:test_basic_attributes_nonzero
			{
			IMethod methodtest_basic_attributes_nonzero84;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_basic_attributes_nonzero84 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_basic_attributes_nonzero", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes_nonzero84, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip85;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_roundtrip85 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip85, new String[] {"self"} );
			}
			//Function test:test_isoformat
			{
			IMethod methodtest_isoformat86;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_isoformat86 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isoformat86, new String[] {"self"} );
			}
			//Function test:test_more_ctime
			{
			IMethod methodtest_more_ctime87;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_more_ctime87 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_more_ctime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_ctime87, new String[] {"self"} );
			}
			//Function test:test_tz_independent_comparing
			{
			IMethod methodtest_tz_independent_comparing88;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_tz_independent_comparing88 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_tz_independent_comparing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tz_independent_comparing88, new String[] {"self"} );
			}
			//Function test:test_bad_constructor_arguments
			{
			IMethod methodtest_bad_constructor_arguments89;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_bad_constructor_arguments89 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_bad_constructor_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_constructor_arguments89, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality90;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_hash_equality90 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality90, new String[] {"self"} );
			}
			//Function test:test_computations
			{
			IMethod methodtest_computations91;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_computations91 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_computations91, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling92;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_pickling92 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling92, new String[] {"self"} );
			}
			//Function test:test_more_pickling
			{
			IMethod methodtest_more_pickling93;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_more_pickling93 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_more_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_pickling93, new String[] {"self"} );
			}
			//Function test:test_pickling_subclass_datetime
			{
			IMethod methodtest_pickling_subclass_datetime94;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_pickling_subclass_datetime94 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_pickling_subclass_datetime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass_datetime94, new String[] {"self"} );
			}
			//Function test:test_more_compare
			{
			IMethod methodtest_more_compare95;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_more_compare95 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_more_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_compare95, new String[] {"self"} );
			}
			//Function test:verify_field_equality
			{
			IMethod methodverify_field_equality96;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodverify_field_equality96 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "verify_field_equality", 3 );
				ModelTestUtils.assertParameterNames( methodverify_field_equality96, new String[] {"self", "expected", "got"} );
			}
			//Function test:test_fromtimestamp
			{
			IMethod methodtest_fromtimestamp97;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_fromtimestamp97 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromtimestamp97, new String[] {"self"} );
			}
			//Function test:test_utcfromtimestamp
			{
			IMethod methodtest_utcfromtimestamp98;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_utcfromtimestamp98 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_utcfromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utcfromtimestamp98, new String[] {"self"} );
			}
			//Function test:test_insane_fromtimestamp
			{
			IMethod methodtest_insane_fromtimestamp99;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_insane_fromtimestamp99 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_insane_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_fromtimestamp99, new String[] {"self"} );
			}
			//Function test:test_insane_utcfromtimestamp
			{
			IMethod methodtest_insane_utcfromtimestamp100;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_insane_utcfromtimestamp100 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_insane_utcfromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_utcfromtimestamp100, new String[] {"self"} );
			}
			//Function test:test_utcnow
			{
			IMethod methodtest_utcnow101;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_utcnow101 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_utcnow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utcnow101, new String[] {"self"} );
			}
			//Function test:test_more_timetuple
			{
			IMethod methodtest_more_timetuple102;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_more_timetuple102 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_more_timetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_timetuple102, new String[] {"self"} );
			}
			//Function test:test_more_strftime
			{
			IMethod methodtest_more_strftime103;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_more_strftime103 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_more_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_strftime103, new String[] {"self"} );
			}
			//Function test:test_extract
			{
			IMethod methodtest_extract104;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_extract104 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_extract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extract104, new String[] {"self"} );
			}
			//Function test:test_combine
			{
			IMethod methodtest_combine105;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_combine105 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_combine", 1 );
				ModelTestUtils.assertParameterNames( methodtest_combine105, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace106;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_replace106 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace106, new String[] {"self"} );
			}
			//Function test:test_astimezone
			{
			IMethod methodtest_astimezone107;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_astimezone107 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_astimezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_astimezone107, new String[] {"self"} );
				//Class test
				{
				IType classBogus108;
					IModelElement[] methodtest_astimezone107Childs = methodtest_astimezone107.getChildren();
					classBogus108 = ModelTestUtils.getAssertClass( methodtest_astimezone107Childs, "Bogus" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset109;
						IModelElement[] classBogus108Childs = classBogus108.getChildren();
						methodutcoffset109 = ModelTestUtils.getAssertMethod( classBogus108Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset109, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst110;
						IModelElement[] classBogus108Childs = classBogus108.getChildren();
						methoddst110 = ModelTestUtils.getAssertMethod( classBogus108Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst110, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classAlsoBogus111;
					IModelElement[] methodtest_astimezone107Childs = methodtest_astimezone107.getChildren();
					classAlsoBogus111 = ModelTestUtils.getAssertClass( methodtest_astimezone107Childs, "AlsoBogus" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset112;
						IModelElement[] classAlsoBogus111Childs = classAlsoBogus111.getChildren();
						methodutcoffset112 = ModelTestUtils.getAssertMethod( classAlsoBogus111Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset112, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst113;
						IModelElement[] classAlsoBogus111Childs = classAlsoBogus111.getChildren();
						methoddst113 = ModelTestUtils.getAssertMethod( classAlsoBogus111Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst113, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_subclass_datetime
			{
			IMethod methodtest_subclass_datetime114;
				IModelElement[] classTestDateTime82Childs = classTestDateTime82.getChildren();
				methodtest_subclass_datetime114 = ModelTestUtils.getAssertMethod( classTestDateTime82Childs, "test_subclass_datetime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_datetime114, new String[] {"self"} );
				//Class test
				{
				IType classC115;
					IModelElement[] methodtest_subclass_datetime114Childs = methodtest_subclass_datetime114.getChildren();
					classC115 = ModelTestUtils.getAssertClass( methodtest_subclass_datetime114Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__116;
						IModelElement[] classC115Childs = classC115.getChildren();
						method__new__116 = ModelTestUtils.getAssertMethod( classC115Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__116, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth117;
						IModelElement[] classC115Childs = classC115.getChildren();
						methodnewmeth117 = ModelTestUtils.getAssertMethod( classC115Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth117, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Class test
		{
		IType classSubclassTime118;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassTime118 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassTime" );
			{
				IModelElement[] classSubclassTime118Childs = classSubclassTime118.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassTime118Childs, "sub_var");
			}
		}
		//Class test
		{
		IType classTestTime119;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTime119 = ModelTestUtils.getAssertClass( moduleChilds, "TestTime" );
			{
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTime119Childs, "theclass");
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes120;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_basic_attributes120 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes120, new String[] {"self"} );
			}
			//Function test:test_basic_attributes_nonzero
			{
			IMethod methodtest_basic_attributes_nonzero121;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_basic_attributes_nonzero121 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_basic_attributes_nonzero", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes_nonzero121, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip122;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_roundtrip122 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip122, new String[] {"self"} );
			}
			//Function test:test_comparing
			{
			IMethod methodtest_comparing123;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_comparing123 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_comparing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparing123, new String[] {"self"} );
			}
			//Function test:test_bad_constructor_arguments
			{
			IMethod methodtest_bad_constructor_arguments124;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_bad_constructor_arguments124 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_bad_constructor_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_constructor_arguments124, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality125;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_hash_equality125 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality125, new String[] {"self"} );
			}
			//Function test:test_isoformat
			{
			IMethod methodtest_isoformat126;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_isoformat126 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isoformat126, new String[] {"self"} );
			}
			//Function test:test_strftime
			{
			IMethod methodtest_strftime127;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_strftime127 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime127, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str128;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_str128 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str128, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr129;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_repr129 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr129, new String[] {"self"} );
			}
			//Function test:test_resolution_info
			{
			IMethod methodtest_resolution_info130;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_resolution_info130 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_resolution_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_resolution_info130, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling131;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_pickling131 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling131, new String[] {"self"} );
			}
			//Function test:test_pickling_subclass_time
			{
			IMethod methodtest_pickling_subclass_time132;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_pickling_subclass_time132 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_pickling_subclass_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass_time132, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool133;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_bool133 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool133, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace134;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_replace134 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace134, new String[] {"self"} );
			}
			//Function test:test_subclass_time
			{
			IMethod methodtest_subclass_time135;
				IModelElement[] classTestTime119Childs = classTestTime119.getChildren();
				methodtest_subclass_time135 = ModelTestUtils.getAssertMethod( classTestTime119Childs, "test_subclass_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_time135, new String[] {"self"} );
				//Class test
				{
				IType classC136;
					IModelElement[] methodtest_subclass_time135Childs = methodtest_subclass_time135.getChildren();
					classC136 = ModelTestUtils.getAssertClass( methodtest_subclass_time135Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__137;
						IModelElement[] classC136Childs = classC136.getChildren();
						method__new__137 = ModelTestUtils.getAssertMethod( classC136Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__137, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth138;
						IModelElement[] classC136Childs = classC136.getChildren();
						methodnewmeth138 = ModelTestUtils.getAssertMethod( classC136Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth138, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTZInfoBase139;
			IModelElement[] moduleChilds = module.getChildren();
			classTZInfoBase139 = ModelTestUtils.getAssertClass( moduleChilds, "TZInfoBase" );
			//Function test:test_argument_passing
			{
			IMethod methodtest_argument_passing140;
				IModelElement[] classTZInfoBase139Childs = classTZInfoBase139.getChildren();
				methodtest_argument_passing140 = ModelTestUtils.getAssertMethod( classTZInfoBase139Childs, "test_argument_passing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argument_passing140, new String[] {"self"} );
				//Class test
				{
				IType classintrospective141;
					IModelElement[] methodtest_argument_passing140Childs = methodtest_argument_passing140.getChildren();
					classintrospective141 = ModelTestUtils.getAssertClass( methodtest_argument_passing140Childs, "introspective" );
					//Function test:tzname
					{
					IMethod methodtzname142;
						IModelElement[] classintrospective141Childs = classintrospective141.getChildren();
						methodtzname142 = ModelTestUtils.getAssertMethod( classintrospective141Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname142, new String[] {"self", "dt"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset143;
						IModelElement[] classintrospective141Childs = classintrospective141.getChildren();
						methodutcoffset143 = ModelTestUtils.getAssertMethod( classintrospective141Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset143, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_bad_tzinfo_classes
			{
			IMethod methodtest_bad_tzinfo_classes144;
				IModelElement[] classTZInfoBase139Childs = classTZInfoBase139.getChildren();
				methodtest_bad_tzinfo_classes144 = ModelTestUtils.getAssertMethod( classTZInfoBase139Childs, "test_bad_tzinfo_classes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_tzinfo_classes144, new String[] {"self"} );
				//Class test
				{
				IType classNiceTry145;
					IModelElement[] methodtest_bad_tzinfo_classes144Childs = methodtest_bad_tzinfo_classes144.getChildren();
					classNiceTry145 = ModelTestUtils.getAssertClass( methodtest_bad_tzinfo_classes144Childs, "NiceTry" );
					//Function test:__init__
					{
					IMethod method__init__146;
						IModelElement[] classNiceTry145Childs = classNiceTry145.getChildren();
						method__init__146 = ModelTestUtils.getAssertMethod( classNiceTry145Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__146, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset147;
						IModelElement[] classNiceTry145Childs = classNiceTry145.getChildren();
						methodutcoffset147 = ModelTestUtils.getAssertMethod( classNiceTry145Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset147, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classBetterTry148;
					IModelElement[] methodtest_bad_tzinfo_classes144Childs = methodtest_bad_tzinfo_classes144.getChildren();
					classBetterTry148 = ModelTestUtils.getAssertClass( methodtest_bad_tzinfo_classes144Childs, "BetterTry" );
					//Function test:__init__
					{
					IMethod method__init__149;
						IModelElement[] classBetterTry148Childs = classBetterTry148.getChildren();
						method__init__149 = ModelTestUtils.getAssertMethod( classBetterTry148Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__149, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset150;
						IModelElement[] classBetterTry148Childs = classBetterTry148.getChildren();
						methodutcoffset150 = ModelTestUtils.getAssertMethod( classBetterTry148Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset150, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_utc_offset_out_of_bounds
			{
			IMethod methodtest_utc_offset_out_of_bounds151;
				IModelElement[] classTZInfoBase139Childs = classTZInfoBase139.getChildren();
				methodtest_utc_offset_out_of_bounds151 = ModelTestUtils.getAssertMethod( classTZInfoBase139Childs, "test_utc_offset_out_of_bounds", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utc_offset_out_of_bounds151, new String[] {"self"} );
				//Class test
				{
				IType classEdgy152;
					IModelElement[] methodtest_utc_offset_out_of_bounds151Childs = methodtest_utc_offset_out_of_bounds151.getChildren();
					classEdgy152 = ModelTestUtils.getAssertClass( methodtest_utc_offset_out_of_bounds151Childs, "Edgy" );
					//Function test:__init__
					{
					IMethod method__init__153;
						IModelElement[] classEdgy152Childs = classEdgy152.getChildren();
						method__init__153 = ModelTestUtils.getAssertMethod( classEdgy152Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__153, new String[] {"self", "offset"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset154;
						IModelElement[] classEdgy152Childs = classEdgy152.getChildren();
						methodutcoffset154 = ModelTestUtils.getAssertMethod( classEdgy152Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset154, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_tzinfo_classes
			{
			IMethod methodtest_tzinfo_classes155;
				IModelElement[] classTZInfoBase139Childs = classTZInfoBase139.getChildren();
				methodtest_tzinfo_classes155 = ModelTestUtils.getAssertMethod( classTZInfoBase139Childs, "test_tzinfo_classes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_classes155, new String[] {"self"} );
				//Class test
				{
				IType classC1156;
					IModelElement[] methodtest_tzinfo_classes155Childs = methodtest_tzinfo_classes155.getChildren();
					classC1156 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes155Childs, "C1" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset157;
						IModelElement[] classC1156Childs = classC1156.getChildren();
						methodutcoffset157 = ModelTestUtils.getAssertMethod( classC1156Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset157, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst158;
						IModelElement[] classC1156Childs = classC1156.getChildren();
						methoddst158 = ModelTestUtils.getAssertMethod( classC1156Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst158, new String[] {"self", "dt"} );
					}
					//Function test:tzname
					{
					IMethod methodtzname159;
						IModelElement[] classC1156Childs = classC1156.getChildren();
						methodtzname159 = ModelTestUtils.getAssertMethod( classC1156Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname159, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC3160;
					IModelElement[] methodtest_tzinfo_classes155Childs = methodtest_tzinfo_classes155.getChildren();
					classC3160 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes155Childs, "C3" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset161;
						IModelElement[] classC3160Childs = classC3160.getChildren();
						methodutcoffset161 = ModelTestUtils.getAssertMethod( classC3160Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset161, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst162;
						IModelElement[] classC3160Childs = classC3160.getChildren();
						methoddst162 = ModelTestUtils.getAssertMethod( classC3160Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst162, new String[] {"self", "dt"} );
					}
					//Function test:tzname
					{
					IMethod methodtzname163;
						IModelElement[] classC3160Childs = classC3160.getChildren();
						methodtzname163 = ModelTestUtils.getAssertMethod( classC3160Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname163, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC4164;
					IModelElement[] methodtest_tzinfo_classes155Childs = methodtest_tzinfo_classes155.getChildren();
					classC4164 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes155Childs, "C4" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset165;
						IModelElement[] classC4164Childs = classC4164.getChildren();
						methodutcoffset165 = ModelTestUtils.getAssertMethod( classC4164Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset165, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst166;
						IModelElement[] classC4164Childs = classC4164.getChildren();
						methoddst166 = ModelTestUtils.getAssertMethod( classC4164Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst166, new String[] {"self", "dt"} );
					}
					//Function test:tzname
					{
					IMethod methodtzname167;
						IModelElement[] classC4164Childs = classC4164.getChildren();
						methodtzname167 = ModelTestUtils.getAssertMethod( classC4164Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname167, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC6168;
					IModelElement[] methodtest_tzinfo_classes155Childs = methodtest_tzinfo_classes155.getChildren();
					classC6168 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes155Childs, "C6" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset169;
						IModelElement[] classC6168Childs = classC6168.getChildren();
						methodutcoffset169 = ModelTestUtils.getAssertMethod( classC6168Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset169, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst170;
						IModelElement[] classC6168Childs = classC6168.getChildren();
						methoddst170 = ModelTestUtils.getAssertMethod( classC6168Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst170, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC7171;
					IModelElement[] methodtest_tzinfo_classes155Childs = methodtest_tzinfo_classes155.getChildren();
					classC7171 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes155Childs, "C7" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset172;
						IModelElement[] classC7171Childs = classC7171.getChildren();
						methodutcoffset172 = ModelTestUtils.getAssertMethod( classC7171Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset172, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst173;
						IModelElement[] classC7171Childs = classC7171.getChildren();
						methoddst173 = ModelTestUtils.getAssertMethod( classC7171Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst173, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_aware_compare
			{
			IMethod methodtest_aware_compare174;
				IModelElement[] classTZInfoBase139Childs = classTZInfoBase139.getChildren();
				methodtest_aware_compare174 = ModelTestUtils.getAssertMethod( classTZInfoBase139Childs, "test_aware_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_aware_compare174, new String[] {"self"} );
				//Class test
				{
				IType classOperandDependentOffset175;
					IModelElement[] methodtest_aware_compare174Childs = methodtest_aware_compare174.getChildren();
					classOperandDependentOffset175 = ModelTestUtils.getAssertClass( methodtest_aware_compare174Childs, "OperandDependentOffset" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset176;
						IModelElement[] classOperandDependentOffset175Childs = classOperandDependentOffset175.getChildren();
						methodutcoffset176 = ModelTestUtils.getAssertMethod( classOperandDependentOffset175Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset176, new String[] {"self", "t"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestTimeTZ177;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTimeTZ177 = ModelTestUtils.getAssertClass( moduleChilds, "TestTimeTZ" );
			{
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimeTZ177Childs, "theclass");
			}
			//Function test:test_empty
			{
			IMethod methodtest_empty178;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_empty178 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty178, new String[] {"self"} );
			}
			//Function test:test_zones
			{
			IMethod methodtest_zones179;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_zones179 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_zones", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zones179, new String[] {"self"} );
				//Class test
				{
				IType classBadtzname180;
					IModelElement[] methodtest_zones179Childs = methodtest_zones179.getChildren();
					classBadtzname180 = ModelTestUtils.getAssertClass( methodtest_zones179Childs, "Badtzname" );
					//Function test:tzname
					{
					IMethod methodtzname181;
						IModelElement[] classBadtzname180Childs = classBadtzname180.getChildren();
						methodtzname181 = ModelTestUtils.getAssertMethod( classBadtzname180Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname181, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_hash_edge_cases
			{
			IMethod methodtest_hash_edge_cases182;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_hash_edge_cases182 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_hash_edge_cases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_edge_cases182, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling183;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_pickling183 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling183, new String[] {"self"} );
			}
			//Function test:test_more_bool
			{
			IMethod methodtest_more_bool184;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_more_bool184 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_more_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_bool184, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace185;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_replace185 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace185, new String[] {"self"} );
			}
			//Function test:test_mixed_compare
			{
			IMethod methodtest_mixed_compare186;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_mixed_compare186 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_mixed_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_compare186, new String[] {"self"} );
				//Class test
				{
				IType classVaries187;
					IModelElement[] methodtest_mixed_compare186Childs = methodtest_mixed_compare186.getChildren();
					classVaries187 = ModelTestUtils.getAssertClass( methodtest_mixed_compare186Childs, "Varies" );
					//Function test:__init__
					{
					IMethod method__init__188;
						IModelElement[] classVaries187Childs = classVaries187.getChildren();
						method__init__188 = ModelTestUtils.getAssertMethod( classVaries187Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__188, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset189;
						IModelElement[] classVaries187Childs = classVaries187.getChildren();
						methodutcoffset189 = ModelTestUtils.getAssertMethod( classVaries187Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset189, new String[] {"self", "t"} );
					}
				}
			}
			//Function test:test_subclass_timetz
			{
			IMethod methodtest_subclass_timetz190;
				IModelElement[] classTestTimeTZ177Childs = classTestTimeTZ177.getChildren();
				methodtest_subclass_timetz190 = ModelTestUtils.getAssertMethod( classTestTimeTZ177Childs, "test_subclass_timetz", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_timetz190, new String[] {"self"} );
				//Class test
				{
				IType classC191;
					IModelElement[] methodtest_subclass_timetz190Childs = methodtest_subclass_timetz190.getChildren();
					classC191 = ModelTestUtils.getAssertClass( methodtest_subclass_timetz190Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__192;
						IModelElement[] classC191Childs = classC191.getChildren();
						method__new__192 = ModelTestUtils.getAssertMethod( classC191Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__192, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth193;
						IModelElement[] classC191Childs = classC191.getChildren();
						methodnewmeth193 = ModelTestUtils.getAssertMethod( classC191Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth193, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestDateTimeTZ194;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDateTimeTZ194 = ModelTestUtils.getAssertClass( moduleChilds, "TestDateTimeTZ" );
			{
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDateTimeTZ194Childs, "theclass");
			}
			//Function test:test_trivial
			{
			IMethod methodtest_trivial195;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_trivial195 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_trivial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_trivial195, new String[] {"self"} );
			}
			//Function test:test_even_more_compare
			{
			IMethod methodtest_even_more_compare196;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_even_more_compare196 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_even_more_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_even_more_compare196, new String[] {"self"} );
				//Class test
				{
				IType classNaive197;
					IModelElement[] methodtest_even_more_compare196Childs = methodtest_even_more_compare196.getChildren();
					classNaive197 = ModelTestUtils.getAssertClass( methodtest_even_more_compare196Childs, "Naive" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset198;
						IModelElement[] classNaive197Childs = classNaive197.getChildren();
						methodutcoffset198 = ModelTestUtils.getAssertMethod( classNaive197Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset198, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classBogus199;
					IModelElement[] methodtest_even_more_compare196Childs = methodtest_even_more_compare196.getChildren();
					classBogus199 = ModelTestUtils.getAssertClass( methodtest_even_more_compare196Childs, "Bogus" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset200;
						IModelElement[] classBogus199Childs = classBogus199.getChildren();
						methodutcoffset200 = ModelTestUtils.getAssertMethod( classBogus199Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset200, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling201;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_pickling201 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling201, new String[] {"self"} );
			}
			//Function test:test_extreme_hashes
			{
			IMethod methodtest_extreme_hashes202;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_extreme_hashes202 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_extreme_hashes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extreme_hashes202, new String[] {"self"} );
			}
			//Function test:test_zones
			{
			IMethod methodtest_zones203;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_zones203 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_zones", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zones203, new String[] {"self"} );
			}
			//Function test:test_combine
			{
			IMethod methodtest_combine204;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_combine204 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_combine", 1 );
				ModelTestUtils.assertParameterNames( methodtest_combine204, new String[] {"self"} );
			}
			//Function test:test_extract
			{
			IMethod methodtest_extract205;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_extract205 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_extract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extract205, new String[] {"self"} );
			}
			//Function test:test_tz_aware_arithmetic
			{
			IMethod methodtest_tz_aware_arithmetic206;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tz_aware_arithmetic206 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tz_aware_arithmetic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tz_aware_arithmetic206, new String[] {"self"} );
			}
			//Function test:test_tzinfo_now
			{
			IMethod methodtest_tzinfo_now207;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tzinfo_now207 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tzinfo_now", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_now207, new String[] {"self"} );
			}
			//Function test:test_tzinfo_fromtimestamp
			{
			IMethod methodtest_tzinfo_fromtimestamp208;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tzinfo_fromtimestamp208 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tzinfo_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_fromtimestamp208, new String[] {"self"} );
			}
			//Function test:test_tzinfo_utcnow
			{
			IMethod methodtest_tzinfo_utcnow209;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tzinfo_utcnow209 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tzinfo_utcnow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_utcnow209, new String[] {"self"} );
			}
			//Function test:test_tzinfo_utcfromtimestamp
			{
			IMethod methodtest_tzinfo_utcfromtimestamp210;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tzinfo_utcfromtimestamp210 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tzinfo_utcfromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_utcfromtimestamp210, new String[] {"self"} );
			}
			//Function test:test_tzinfo_timetuple
			{
			IMethod methodtest_tzinfo_timetuple211;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tzinfo_timetuple211 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tzinfo_timetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_timetuple211, new String[] {"self"} );
				//Class test
				{
				IType classDST212;
					IModelElement[] methodtest_tzinfo_timetuple211Childs = methodtest_tzinfo_timetuple211.getChildren();
					classDST212 = ModelTestUtils.getAssertClass( methodtest_tzinfo_timetuple211Childs, "DST" );
					//Function test:__init__
					{
					IMethod method__init__213;
						IModelElement[] classDST212Childs = classDST212.getChildren();
						method__init__213 = ModelTestUtils.getAssertMethod( classDST212Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__213, new String[] {"self", "dstvalue"} );
					}
					//Function test:dst
					{
					IMethod methoddst214;
						IModelElement[] classDST212Childs = classDST212.getChildren();
						methoddst214 = ModelTestUtils.getAssertMethod( classDST212Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst214, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_utctimetuple
			{
			IMethod methodtest_utctimetuple215;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_utctimetuple215 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_utctimetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utctimetuple215, new String[] {"self"} );
				//Class test
				{
				IType classDST216;
					IModelElement[] methodtest_utctimetuple215Childs = methodtest_utctimetuple215.getChildren();
					classDST216 = ModelTestUtils.getAssertClass( methodtest_utctimetuple215Childs, "DST" );
					//Function test:__init__
					{
					IMethod method__init__217;
						IModelElement[] classDST216Childs = classDST216.getChildren();
						method__init__217 = ModelTestUtils.getAssertMethod( classDST216Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__217, new String[] {"self", "dstvalue"} );
					}
					//Function test:dst
					{
					IMethod methoddst218;
						IModelElement[] classDST216Childs = classDST216.getChildren();
						methoddst218 = ModelTestUtils.getAssertMethod( classDST216Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst218, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classUOFS219;
					IModelElement[] methodtest_utctimetuple215Childs = methodtest_utctimetuple215.getChildren();
					classUOFS219 = ModelTestUtils.getAssertClass( methodtest_utctimetuple215Childs, "UOFS" );
					//Function test:__init__
					{
					IMethod method__init__220;
						IModelElement[] classUOFS219Childs = classUOFS219.getChildren();
						method__init__220 = ModelTestUtils.getAssertMethod( classUOFS219Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__220, new String[] {"self", "uofs", "dofs"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset221;
						IModelElement[] classUOFS219Childs = classUOFS219.getChildren();
						methodutcoffset221 = ModelTestUtils.getAssertMethod( classUOFS219Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset221, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_tzinfo_isoformat
			{
			IMethod methodtest_tzinfo_isoformat222;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_tzinfo_isoformat222 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_tzinfo_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_isoformat222, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace223;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_replace223 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace223, new String[] {"self"} );
			}
			//Function test:test_more_astimezone
			{
			IMethod methodtest_more_astimezone224;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_more_astimezone224 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_more_astimezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_astimezone224, new String[] {"self"} );
			}
			//Function test:test_aware_subtract
			{
			IMethod methodtest_aware_subtract225;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_aware_subtract225 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_aware_subtract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_aware_subtract225, new String[] {"self"} );
				//Class test
				{
				IType classOperandDependentOffset226;
					IModelElement[] methodtest_aware_subtract225Childs = methodtest_aware_subtract225.getChildren();
					classOperandDependentOffset226 = ModelTestUtils.getAssertClass( methodtest_aware_subtract225Childs, "OperandDependentOffset" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset227;
						IModelElement[] classOperandDependentOffset226Childs = classOperandDependentOffset226.getChildren();
						methodutcoffset227 = ModelTestUtils.getAssertMethod( classOperandDependentOffset226Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset227, new String[] {"self", "t"} );
					}
				}
			}
			//Function test:test_mixed_compare
			{
			IMethod methodtest_mixed_compare228;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_mixed_compare228 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_mixed_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_compare228, new String[] {"self"} );
				//Class test
				{
				IType classVaries229;
					IModelElement[] methodtest_mixed_compare228Childs = methodtest_mixed_compare228.getChildren();
					classVaries229 = ModelTestUtils.getAssertClass( methodtest_mixed_compare228Childs, "Varies" );
					//Function test:__init__
					{
					IMethod method__init__230;
						IModelElement[] classVaries229Childs = classVaries229.getChildren();
						method__init__230 = ModelTestUtils.getAssertMethod( classVaries229Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__230, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset231;
						IModelElement[] classVaries229Childs = classVaries229.getChildren();
						methodutcoffset231 = ModelTestUtils.getAssertMethod( classVaries229Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset231, new String[] {"self", "t"} );
					}
				}
			}
			//Function test:test_subclass_datetimetz
			{
			IMethod methodtest_subclass_datetimetz232;
				IModelElement[] classTestDateTimeTZ194Childs = classTestDateTimeTZ194.getChildren();
				methodtest_subclass_datetimetz232 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ194Childs, "test_subclass_datetimetz", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_datetimetz232, new String[] {"self"} );
				//Class test
				{
				IType classC233;
					IModelElement[] methodtest_subclass_datetimetz232Childs = methodtest_subclass_datetimetz232.getChildren();
					classC233 = ModelTestUtils.getAssertClass( methodtest_subclass_datetimetz232Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__234;
						IModelElement[] classC233Childs = classC233.getChildren();
						method__new__234 = ModelTestUtils.getAssertMethod( classC233Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__234, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth235;
						IModelElement[] classC233Childs = classC233.getChildren();
						methodnewmeth235 = ModelTestUtils.getAssertMethod( classC233Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth235, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Function test:first_sunday_on_or_after
		{
		IMethod methodfirst_sunday_on_or_after236;
			IModelElement[] moduleChilds = module.getChildren();
			methodfirst_sunday_on_or_after236 = ModelTestUtils.getAssertMethod( moduleChilds, "first_sunday_on_or_after", 1 );
			ModelTestUtils.assertParameterNames( methodfirst_sunday_on_or_after236, new String[] {"dt"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "ZERO");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "HOUR");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DAY");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DSTSTART");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DSTEND");
		}
		//Class test
		{
		IType classUSTimeZone237;
			IModelElement[] moduleChilds = module.getChildren();
			classUSTimeZone237 = ModelTestUtils.getAssertClass( moduleChilds, "USTimeZone" );
			//Function test:__init__
			{
			IMethod method__init__238;
				IModelElement[] classUSTimeZone237Childs = classUSTimeZone237.getChildren();
				method__init__238 = ModelTestUtils.getAssertMethod( classUSTimeZone237Childs, "__init__", 5 );
				ModelTestUtils.assertParameterNames( method__init__238, new String[] {"self", "hours", "reprname", "stdname", "dstname"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__239;
				IModelElement[] classUSTimeZone237Childs = classUSTimeZone237.getChildren();
				method__repr__239 = ModelTestUtils.getAssertMethod( classUSTimeZone237Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__239, new String[] {"self"} );
			}
			//Function test:tzname
			{
			IMethod methodtzname240;
				IModelElement[] classUSTimeZone237Childs = classUSTimeZone237.getChildren();
				methodtzname240 = ModelTestUtils.getAssertMethod( classUSTimeZone237Childs, "tzname", 2 );
				ModelTestUtils.assertParameterNames( methodtzname240, new String[] {"self", "dt"} );
			}
			//Function test:utcoffset
			{
			IMethod methodutcoffset241;
				IModelElement[] classUSTimeZone237Childs = classUSTimeZone237.getChildren();
				methodutcoffset241 = ModelTestUtils.getAssertMethod( classUSTimeZone237Childs, "utcoffset", 2 );
				ModelTestUtils.assertParameterNames( methodutcoffset241, new String[] {"self", "dt"} );
			}
			//Function test:dst
			{
			IMethod methoddst242;
				IModelElement[] classUSTimeZone237Childs = classUSTimeZone237.getChildren();
				methoddst242 = ModelTestUtils.getAssertMethod( classUSTimeZone237Childs, "dst", 2 );
				ModelTestUtils.assertParameterNames( methoddst242, new String[] {"self", "dt"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Eastern");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Central");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Mountain");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "Pacific");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "utc_real");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "utc_fake");
		}
		//Class test
		{
		IType classTestTimezoneConversions243;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTimezoneConversions243 = ModelTestUtils.getAssertClass( moduleChilds, "TestTimezoneConversions" );
			{
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimezoneConversions243Childs, "dston");
			}
			{
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimezoneConversions243Childs, "dstoff");
			}
			{
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimezoneConversions243Childs, "theclass");
			}
			//Function test:checkinside
			{
			IMethod methodcheckinside244;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodcheckinside244 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "checkinside", 6 );
				ModelTestUtils.assertParameterNames( methodcheckinside244, new String[] {"self", "dt", "tz", "utc", "dston", "dstoff"} );
			}
			//Function test:checkoutside
			{
			IMethod methodcheckoutside245;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodcheckoutside245 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "checkoutside", 4 );
				ModelTestUtils.assertParameterNames( methodcheckoutside245, new String[] {"self", "dt", "tz", "utc"} );
			}
			//Function test:convert_between_tz_and_utc
			{
			IMethod methodconvert_between_tz_and_utc246;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodconvert_between_tz_and_utc246 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "convert_between_tz_and_utc", 3 );
				ModelTestUtils.assertParameterNames( methodconvert_between_tz_and_utc246, new String[] {"self", "tz", "utc"} );
			}
			//Function test:test_easy
			{
			IMethod methodtest_easy247;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodtest_easy247 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "test_easy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_easy247, new String[] {"self"} );
			}
			//Function test:test_tricky
			{
			IMethod methodtest_tricky248;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodtest_tricky248 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "test_tricky", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tricky248, new String[] {"self"} );
			}
			//Function test:test_bogus_dst
			{
			IMethod methodtest_bogus_dst249;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodtest_bogus_dst249 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "test_bogus_dst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bogus_dst249, new String[] {"self"} );
				//Class test
				{
				IType classok250;
					IModelElement[] methodtest_bogus_dst249Childs = methodtest_bogus_dst249.getChildren();
					classok250 = ModelTestUtils.getAssertClass( methodtest_bogus_dst249Childs, "ok" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset251;
						IModelElement[] classok250Childs = classok250.getChildren();
						methodutcoffset251 = ModelTestUtils.getAssertMethod( classok250Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset251, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst252;
						IModelElement[] classok250Childs = classok250.getChildren();
						methoddst252 = ModelTestUtils.getAssertMethod( classok250Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst252, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classnotok253;
					IModelElement[] methodtest_bogus_dst249Childs = methodtest_bogus_dst249.getChildren();
					classnotok253 = ModelTestUtils.getAssertClass( methodtest_bogus_dst249Childs, "notok" );
					//Function test:dst
					{
					IMethod methoddst254;
						IModelElement[] classnotok253Childs = classnotok253.getChildren();
						methoddst254 = ModelTestUtils.getAssertMethod( classnotok253Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst254, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_fromutc
			{
			IMethod methodtest_fromutc255;
				IModelElement[] classTestTimezoneConversions243Childs = classTestTimezoneConversions243.getChildren();
				methodtest_fromutc255 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions243Childs, "test_fromutc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromutc255, new String[] {"self"} );
				//Class test
				{
				IType classFauxUSTimeZone256;
					IModelElement[] methodtest_fromutc255Childs = methodtest_fromutc255.getChildren();
					classFauxUSTimeZone256 = ModelTestUtils.getAssertClass( methodtest_fromutc255Childs, "FauxUSTimeZone" );
					//Function test:fromutc
					{
					IMethod methodfromutc257;
						IModelElement[] classFauxUSTimeZone256Childs = classFauxUSTimeZone256.getChildren();
						methodfromutc257 = ModelTestUtils.getAssertMethod( classFauxUSTimeZone256Childs, "fromutc", 2 );
						ModelTestUtils.assertParameterNames( methodfromutc257, new String[] {"self", "dt"} );
					}
				}
			}
		}
		//Class test
		{
		IType classOddballs258;
			IModelElement[] moduleChilds = module.getChildren();
			classOddballs258 = ModelTestUtils.getAssertClass( moduleChilds, "Oddballs" );
			//Function test:test_bug_1028306
			{
			IMethod methodtest_bug_1028306259;
				IModelElement[] classOddballs258Childs = classOddballs258.getChildren();
				methodtest_bug_1028306259 = ModelTestUtils.getAssertMethod( classOddballs258Childs, "test_bug_1028306", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_1028306259, new String[] {"self"} );
			}
		}
		//Function test:test_suite
		{
		IMethod methodtest_suite260;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_suite260 = ModelTestUtils.getAssertMethod( moduleChilds, "test_suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main261;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main261 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen113( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_urllib2net.py"));

		assertNotNull("Module test_urllib2net.py not found", module);
		assertEquals("test_urllib2net.py", module.getElementName());
		
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
			//Function test:test_info
			{
			IMethod methodtest_info6;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_info6 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_info6, new String[] {"self"} );
			}
			//Function test:test_geturl
			{
			IMethod methodtest_geturl7;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_geturl7 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_geturl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_geturl7, new String[] {"self"} );
			}
			//Function test:test_bad_address
			{
			IMethod methodtest_bad_address8;
				IModelElement[] classurlopenNetworkTests4Childs = classurlopenNetworkTests4.getChildren();
				methodtest_bad_address8 = ModelTestUtils.getAssertMethod( classurlopenNetworkTests4Childs, "test_bad_address", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_address8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen114( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_hotshot.py"));

		assertNotNull("Module test_hotshot.py not found", module);
		assertEquals("test_hotshot.py", module.getElementName());
		
		//Function test:shortfilename
		{
		IMethod methodshortfilename0;
			IModelElement[] moduleChilds = module.getChildren();
			methodshortfilename0 = ModelTestUtils.getAssertMethod( moduleChilds, "shortfilename", 1 );
			ModelTestUtils.assertParameterNames( methodshortfilename0, new String[] {"fn"} );
		}
		//Class test
		{
		IType classUnlinkingLogReader1;
			IModelElement[] moduleChilds = module.getChildren();
			classUnlinkingLogReader1 = ModelTestUtils.getAssertClass( moduleChilds, "UnlinkingLogReader" );
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classUnlinkingLogReader1Childs = classUnlinkingLogReader1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classUnlinkingLogReader1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "logfn"} );
			}
			//Function test:next
			{
			IMethod methodnext3;
				IModelElement[] classUnlinkingLogReader1Childs = classUnlinkingLogReader1.getChildren();
				methodnext3 = ModelTestUtils.getAssertMethod( classUnlinkingLogReader1Childs, "next", 2 );
				ModelTestUtils.assertParameterNames( methodnext3, new String[] {"self", "index"} );
			}
		}
		//Class test
		{
		IType classHotShotTestCase4;
			IModelElement[] moduleChilds = module.getChildren();
			classHotShotTestCase4 = ModelTestUtils.getAssertClass( moduleChilds, "HotShotTestCase" );
			//Function test:new_profiler
			{
			IMethod methodnew_profiler5;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodnew_profiler5 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "new_profiler", 3 );
				ModelTestUtils.assertParameterNames( methodnew_profiler5, new String[] {"self", "lineevents", "linetimings"} );
			}
			//Function test:get_logreader
			{
			IMethod methodget_logreader6;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodget_logreader6 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "get_logreader", 1 );
				ModelTestUtils.assertParameterNames( methodget_logreader6, new String[] {"self"} );
			}
			//Function test:get_events_wotime
			{
			IMethod methodget_events_wotime7;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodget_events_wotime7 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "get_events_wotime", 1 );
				ModelTestUtils.assertParameterNames( methodget_events_wotime7, new String[] {"self"} );
			}
			//Function test:check_events
			{
			IMethod methodcheck_events8;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodcheck_events8 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "check_events", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_events8, new String[] {"self", "expected"} );
			}
			//Function test:run_test
			{
			IMethod methodrun_test9;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodrun_test9 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "run_test", 4 );
				ModelTestUtils.assertParameterNames( methodrun_test9, new String[] {"self", "callable", "events", "profiler"} );
			}
			//Function test:test_addinfo
			{
			IMethod methodtest_addinfo10;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodtest_addinfo10 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "test_addinfo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addinfo10, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf11;
					IModelElement[] methodtest_addinfo10Childs = methodtest_addinfo10.getChildren();
					methodf11 = ModelTestUtils.getAssertMethod( methodtest_addinfo10Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf11, new String[] {"p"} );
				}
			}
			//Function test:test_line_numbers
			{
			IMethod methodtest_line_numbers12;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodtest_line_numbers12 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "test_line_numbers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_line_numbers12, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf13;
					IModelElement[] methodtest_line_numbers12Childs = methodtest_line_numbers12.getChildren();
					methodf13 = ModelTestUtils.getAssertMethod( methodtest_line_numbers12Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg14;
					IModelElement[] methodtest_line_numbers12Childs = methodtest_line_numbers12.getChildren();
					methodg14 = ModelTestUtils.getAssertMethod( methodtest_line_numbers12Childs, "g", 0 );
				}
			}
			//Function test:test_start_stop
			{
			IMethod methodtest_start_stop15;
				IModelElement[] classHotShotTestCase4Childs = classHotShotTestCase4.getChildren();
				methodtest_start_stop15 = ModelTestUtils.getAssertMethod( classHotShotTestCase4Childs, "test_start_stop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_start_stop15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen115( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bsddb.py"));

		assertNotNull("Module test_bsddb.py not found", module);
		assertEquals("test_bsddb.py", module.getElementName());
		
		//Class test
		{
		IType classTestBSDDB0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBSDDB0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBSDDB" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem3;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_getitem3 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem3, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len4;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_len4 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len4, new String[] {"self"} );
			}
			//Function test:test_change
			{
			IMethod methodtest_change5;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_change5 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_change", 1 );
				ModelTestUtils.assertParameterNames( methodtest_change5, new String[] {"self"} );
			}
			//Function test:test_close_and_reopen
			{
			IMethod methodtest_close_and_reopen6;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_close_and_reopen6 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_close_and_reopen", 1 );
				ModelTestUtils.assertParameterNames( methodtest_close_and_reopen6, new String[] {"self"} );
			}
			//Function test:assertSetEquals
			{
			IMethod methodassertSetEquals7;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodassertSetEquals7 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "assertSetEquals", 3 );
				ModelTestUtils.assertParameterNames( methodassertSetEquals7, new String[] {"self", "seqn1", "seqn2"} );
			}
			//Function test:test_mapping_iteration_methods
			{
			IMethod methodtest_mapping_iteration_methods8;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_mapping_iteration_methods8 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_mapping_iteration_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mapping_iteration_methods8, new String[] {"self"} );
			}
			//Function test:test_iter_while_modifying_values
			{
			IMethod methodtest_iter_while_modifying_values9;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_iter_while_modifying_values9 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_iter_while_modifying_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_while_modifying_values9, new String[] {"self"} );
			}
			//Function test:test_iteritems_while_modifying_values
			{
			IMethod methodtest_iteritems_while_modifying_values10;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_iteritems_while_modifying_values10 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_iteritems_while_modifying_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteritems_while_modifying_values10, new String[] {"self"} );
			}
			//Function test:test_first_next_looping
			{
			IMethod methodtest_first_next_looping11;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_first_next_looping11 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_first_next_looping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_first_next_looping11, new String[] {"self"} );
			}
			//Function test:test_previous_last_looping
			{
			IMethod methodtest_previous_last_looping12;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_previous_last_looping12 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_previous_last_looping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_previous_last_looping12, new String[] {"self"} );
			}
			//Function test:test_set_location
			{
			IMethod methodtest_set_location13;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_set_location13 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_set_location", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_location13, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains14;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_contains14 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains14, new String[] {"self"} );
			}
			//Function test:test_has_key
			{
			IMethod methodtest_has_key15;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_has_key15 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_has_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_key15, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear16;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_clear16 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear16, new String[] {"self"} );
			}
			//Function test:test__no_deadlock_first
			{
			IMethod methodtest__no_deadlock_first17;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest__no_deadlock_first17 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test__no_deadlock_first", 2 );
				ModelTestUtils.assertParameterNames( methodtest__no_deadlock_first17, new String[] {"self", "debug"} );
			}
			//Function test:test_for_cursor_memleak
			{
			IMethod methodtest_for_cursor_memleak18;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_for_cursor_memleak18 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_for_cursor_memleak", 1 );
				ModelTestUtils.assertParameterNames( methodtest_for_cursor_memleak18, new String[] {"self"} );
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem19;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_popitem19 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem19, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop20;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_pop20 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop20, new String[] {"self"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get21;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_get21 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get21, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault22;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_setdefault22 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault22, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update23;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_update23 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update23, new String[] {"self"} );
			}
			//Function test:test_keyordering
			{
			IMethod methodtest_keyordering24;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_keyordering24 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_keyordering", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keyordering24, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBTree25;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBTree25 = ModelTestUtils.getAssertClass( moduleChilds, "TestBTree" );
			{
				IModelElement[] classTestBTree25Childs = classTestBTree25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree25Childs, "fname");
			}
			{
				IModelElement[] classTestBTree25Childs = classTestBTree25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree25Childs, "openmethod");
			}
		}
		//Class test
		{
		IType classTestBTree_InMemory26;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBTree_InMemory26 = ModelTestUtils.getAssertClass( moduleChilds, "TestBTree_InMemory" );
			{
				IModelElement[] classTestBTree_InMemory26Childs = classTestBTree_InMemory26.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree_InMemory26Childs, "fname");
			}
			{
				IModelElement[] classTestBTree_InMemory26Childs = classTestBTree_InMemory26.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree_InMemory26Childs, "openmethod");
			}
		}
		//Class test
		{
		IType classTestHashTable27;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHashTable27 = ModelTestUtils.getAssertClass( moduleChilds, "TestHashTable" );
			{
				IModelElement[] classTestHashTable27Childs = classTestHashTable27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable27Childs, "fname");
			}
			{
				IModelElement[] classTestHashTable27Childs = classTestHashTable27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable27Childs, "openmethod");
			}
		}
		//Class test
		{
		IType classTestHashTable_InMemory28;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHashTable_InMemory28 = ModelTestUtils.getAssertClass( moduleChilds, "TestHashTable_InMemory" );
			{
				IModelElement[] classTestHashTable_InMemory28Childs = classTestHashTable_InMemory28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable_InMemory28Childs, "fname");
			}
			{
				IModelElement[] classTestHashTable_InMemory28Childs = classTestHashTable_InMemory28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable_InMemory28Childs, "openmethod");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main29;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main29 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main29, new String[] {"verbose"} );
		}

	}
	public void testModelGen116( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_peepholer.py"));

		assertNotNull("Module test_peepholer.py not found", module);
		assertEquals("test_peepholer.py", module.getElementName());
		
		//Function test:disassemble
		{
		IMethod methoddisassemble0;
			IModelElement[] moduleChilds = module.getChildren();
			methoddisassemble0 = ModelTestUtils.getAssertMethod( moduleChilds, "disassemble", 1 );
			ModelTestUtils.assertParameterNames( methoddisassemble0, new String[] {"func"} );
		}
		//Function test:dis_single
		{
		IMethod methoddis_single1;
			IModelElement[] moduleChilds = module.getChildren();
			methoddis_single1 = ModelTestUtils.getAssertMethod( moduleChilds, "dis_single", 1 );
			ModelTestUtils.assertParameterNames( methoddis_single1, new String[] {"line"} );
		}
		//Class test
		{
		IType classTestTranforms2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTranforms2 = ModelTestUtils.getAssertClass( moduleChilds, "TestTranforms" );
			//Function test:test_unot
			{
			IMethod methodtest_unot3;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_unot3 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_unot", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unot3, new String[] {"self"} );
				//Function test:unot
				{
				IMethod methodunot4;
					IModelElement[] methodtest_unot3Childs = methodtest_unot3.getChildren();
					methodunot4 = ModelTestUtils.getAssertMethod( methodtest_unot3Childs, "unot", 1 );
					ModelTestUtils.assertParameterNames( methodunot4, new String[] {"x"} );
				}
			}
			//Function test:test_elim_inversion_of_is_or_in
			{
			IMethod methodtest_elim_inversion_of_is_or_in5;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_elim_inversion_of_is_or_in5 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_elim_inversion_of_is_or_in", 1 );
				ModelTestUtils.assertParameterNames( methodtest_elim_inversion_of_is_or_in5, new String[] {"self"} );
			}
			//Function test:test_none_as_constant
			{
			IMethod methodtest_none_as_constant6;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_none_as_constant6 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_none_as_constant", 1 );
				ModelTestUtils.assertParameterNames( methodtest_none_as_constant6, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf7;
					IModelElement[] methodtest_none_as_constant6Childs = methodtest_none_as_constant6.getChildren();
					methodf7 = ModelTestUtils.getAssertMethod( methodtest_none_as_constant6Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf7, new String[] {"x"} );
				}
			}
			//Function test:test_while_one
			{
			IMethod methodtest_while_one8;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_while_one8 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_while_one", 1 );
				ModelTestUtils.assertParameterNames( methodtest_while_one8, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf9;
					IModelElement[] methodtest_while_one8Childs = methodtest_while_one8.getChildren();
					methodf9 = ModelTestUtils.getAssertMethod( methodtest_while_one8Childs, "f", 0 );
				}
			}
			//Function test:test_pack_unpack
			{
			IMethod methodtest_pack_unpack10;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_pack_unpack10 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_pack_unpack", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pack_unpack10, new String[] {"self"} );
			}
			//Function test:test_folding_of_tuples_of_constants
			{
			IMethod methodtest_folding_of_tuples_of_constants11;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_folding_of_tuples_of_constants11 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_folding_of_tuples_of_constants", 1 );
				ModelTestUtils.assertParameterNames( methodtest_folding_of_tuples_of_constants11, new String[] {"self"} );
				//Function test:crater
				{
				IMethod methodcrater12;
					IModelElement[] methodtest_folding_of_tuples_of_constants11Childs = methodtest_folding_of_tuples_of_constants11.getChildren();
					methodcrater12 = ModelTestUtils.getAssertMethod( methodtest_folding_of_tuples_of_constants11Childs, "crater", 0 );
				}
			}
			//Function test:test_elim_extra_return
			{
			IMethod methodtest_elim_extra_return13;
				IModelElement[] classTestTranforms2Childs = classTestTranforms2.getChildren();
				methodtest_elim_extra_return13 = ModelTestUtils.getAssertMethod( classTestTranforms2Childs, "test_elim_extra_return", 1 );
				ModelTestUtils.assertParameterNames( methodtest_elim_extra_return13, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf14;
					IModelElement[] methodtest_elim_extra_return13Childs = methodtest_elim_extra_return13.getChildren();
					methodf14 = ModelTestUtils.getAssertMethod( methodtest_elim_extra_return13Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf14, new String[] {"x"} );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main15, new String[] {"verbose"} );
		}

	}
	public void testModelGen117( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_sgmllib.py"));

		assertNotNull("Module test_sgmllib.py not found", module);
		assertEquals("test_sgmllib.py", module.getElementName());
		
		//Class test
		{
		IType classEventCollector0;
			IModelElement[] moduleChilds = module.getChildren();
			classEventCollector0 = ModelTestUtils.getAssertClass( moduleChilds, "EventCollector" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self"} );
			}
			//Function test:get_events
			{
			IMethod methodget_events2;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodget_events2 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "get_events", 1 );
				ModelTestUtils.assertParameterNames( methodget_events2, new String[] {"self"} );
			}
			//Function test:unknown_starttag
			{
			IMethod methodunknown_starttag3;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_starttag3 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_starttag", 3 );
				ModelTestUtils.assertParameterNames( methodunknown_starttag3, new String[] {"self", "tag", "attrs"} );
			}
			//Function test:unknown_endtag
			{
			IMethod methodunknown_endtag4;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_endtag4 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_endtag", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_endtag4, new String[] {"self", "tag"} );
			}
			//Function test:handle_comment
			{
			IMethod methodhandle_comment5;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_comment5 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_comment", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_comment5, new String[] {"self", "data"} );
			}
			//Function test:handle_charref
			{
			IMethod methodhandle_charref6;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_charref6 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_charref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_charref6, new String[] {"self", "data"} );
			}
			//Function test:handle_data
			{
			IMethod methodhandle_data7;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_data7 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_data", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_data7, new String[] {"self", "data"} );
			}
			//Function test:handle_decl
			{
			IMethod methodhandle_decl8;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_decl8 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_decl", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_decl8, new String[] {"self", "decl"} );
			}
			//Function test:handle_entityref
			{
			IMethod methodhandle_entityref9;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_entityref9 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_entityref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_entityref9, new String[] {"self", "data"} );
			}
			//Function test:handle_pi
			{
			IMethod methodhandle_pi10;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_pi10 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_pi", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_pi10, new String[] {"self", "data"} );
			}
			//Function test:unknown_decl
			{
			IMethod methodunknown_decl11;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_decl11 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_decl", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_decl11, new String[] {"self", "decl"} );
			}
		}
		//Class test
		{
		IType classCDATAEventCollector12;
			IModelElement[] moduleChilds = module.getChildren();
			classCDATAEventCollector12 = ModelTestUtils.getAssertClass( moduleChilds, "CDATAEventCollector" );
			//Function test:start_cdata
			{
			IMethod methodstart_cdata13;
				IModelElement[] classCDATAEventCollector12Childs = classCDATAEventCollector12.getChildren();
				methodstart_cdata13 = ModelTestUtils.getAssertMethod( classCDATAEventCollector12Childs, "start_cdata", 2 );
				ModelTestUtils.assertParameterNames( methodstart_cdata13, new String[] {"self", "attrs"} );
			}
		}
		//Class test
		{
		IType classSGMLParserTestCase14;
			IModelElement[] moduleChilds = module.getChildren();
			classSGMLParserTestCase14 = ModelTestUtils.getAssertClass( moduleChilds, "SGMLParserTestCase" );
			{
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSGMLParserTestCase14Childs, "collector");
			}
			//Function test:get_events
			{
			IMethod methodget_events15;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodget_events15 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "get_events", 2 );
				ModelTestUtils.assertParameterNames( methodget_events15, new String[] {"self", "source"} );
			}
			//Function test:check_events
			{
			IMethod methodcheck_events16;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodcheck_events16 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "check_events", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_events16, new String[] {"self", "source", "expected_events"} );
			}
			//Function test:check_parse_error
			{
			IMethod methodcheck_parse_error17;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodcheck_parse_error17 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "check_parse_error", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_parse_error17, new String[] {"self", "source"} );
			}
			//Function test:test_doctype_decl_internal
			{
			IMethod methodtest_doctype_decl_internal18;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_doctype_decl_internal18 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_doctype_decl_internal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doctype_decl_internal18, new String[] {"self"} );
			}
			//Function test:test_doctype_decl_external
			{
			IMethod methodtest_doctype_decl_external19;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_doctype_decl_external19 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_doctype_decl_external", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doctype_decl_external19, new String[] {"self"} );
			}
			//Function test:test_underscore_in_attrname
			{
			IMethod methodtest_underscore_in_attrname20;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_underscore_in_attrname20 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_underscore_in_attrname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_underscore_in_attrname20, new String[] {"self"} );
			}
			//Function test:test_underscore_in_tagname
			{
			IMethod methodtest_underscore_in_tagname21;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_underscore_in_tagname21 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_underscore_in_tagname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_underscore_in_tagname21, new String[] {"self"} );
			}
			//Function test:test_quotes_in_unquoted_attrs
			{
			IMethod methodtest_quotes_in_unquoted_attrs22;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_quotes_in_unquoted_attrs22 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_quotes_in_unquoted_attrs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quotes_in_unquoted_attrs22, new String[] {"self"} );
			}
			//Function test:test_xhtml_empty_tag
			{
			IMethod methodtest_xhtml_empty_tag23;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_xhtml_empty_tag23 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_xhtml_empty_tag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xhtml_empty_tag23, new String[] {"self"} );
			}
			//Function test:test_processing_instruction_only
			{
			IMethod methodtest_processing_instruction_only24;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_processing_instruction_only24 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_processing_instruction_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_processing_instruction_only24, new String[] {"self"} );
			}
			//Function test:test_bad_nesting
			{
			IMethod methodtest_bad_nesting25;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_bad_nesting25 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_bad_nesting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_nesting25, new String[] {"self"} );
			}
			//Function test:test_bare_ampersands
			{
			IMethod methodtest_bare_ampersands26;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_bare_ampersands26 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_bare_ampersands", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_ampersands26, new String[] {"self"} );
			}
			//Function test:test_bare_pointy_brackets
			{
			IMethod methodtest_bare_pointy_brackets27;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_bare_pointy_brackets27 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_bare_pointy_brackets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_pointy_brackets27, new String[] {"self"} );
			}
			//Function test:test_attr_syntax
			{
			IMethod methodtest_attr_syntax28;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_attr_syntax28 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_attr_syntax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_syntax28, new String[] {"self"} );
			}
			//Function test:test_attr_values
			{
			IMethod methodtest_attr_values29;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_attr_values29 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_attr_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_values29, new String[] {"self"} );
			}
			//Function test:test_attr_funky_names
			{
			IMethod methodtest_attr_funky_names30;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_attr_funky_names30 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_attr_funky_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_funky_names30, new String[] {"self"} );
			}
			//Function test:test_illegal_declarations
			{
			IMethod methodtest_illegal_declarations31;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_illegal_declarations31 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_illegal_declarations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_declarations31, new String[] {"self"} );
			}
			//Function test:test_weird_starttags
			{
			IMethod methodtest_weird_starttags32;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_weird_starttags32 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_weird_starttags", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weird_starttags32, new String[] {"self"} );
			}
			//Function test:test_declaration_junk_chars
			{
			IMethod methodtest_declaration_junk_chars33;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_declaration_junk_chars33 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_declaration_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_declaration_junk_chars33, new String[] {"self"} );
			}
			//Function test:test_get_starttag_text
			{
			IMethod methodtest_get_starttag_text34;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_get_starttag_text34 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_get_starttag_text", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_starttag_text34, new String[] {"self"} );
			}
			//Function test:test_cdata_content
			{
			IMethod methodtest_cdata_content35;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_cdata_content35 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_cdata_content", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cdata_content35, new String[] {"self"} );
			}
			//Function test:test_illegal_declarations
			{
			IMethod methodtest_illegal_declarations36;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_illegal_declarations36 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_illegal_declarations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_declarations36, new String[] {"self"} );
			}
			//Function test:test_enumerated_attr_type
			{
			IMethod methodtest_enumerated_attr_type37;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				methodtest_enumerated_attr_type37 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "test_enumerated_attr_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_enumerated_attr_type37, new String[] {"self"} );
			}
			//Function test:_test_starttag_end_boundary
			{
			IMethod method_test_starttag_end_boundary38;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				method_test_starttag_end_boundary38 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "_test_starttag_end_boundary", 1 );
				ModelTestUtils.assertParameterNames( method_test_starttag_end_boundary38, new String[] {"self"} );
			}
			//Function test:_test_buffer_artefacts
			{
			IMethod method_test_buffer_artefacts39;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				method_test_buffer_artefacts39 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "_test_buffer_artefacts", 1 );
				ModelTestUtils.assertParameterNames( method_test_buffer_artefacts39, new String[] {"self"} );
			}
			//Function test:_test_starttag_junk_chars
			{
			IMethod method_test_starttag_junk_chars40;
				IModelElement[] classSGMLParserTestCase14Childs = classSGMLParserTestCase14.getChildren();
				method_test_starttag_junk_chars40 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase14Childs, "_test_starttag_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( method_test_starttag_junk_chars40, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main41;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main41 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen118( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_threadsignals.py"));

		assertNotNull("Module test_threadsignals.py not found", module);
		assertEquals("test_threadsignals.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "process_pid");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "signalled_all");
		}
		//Function test:registerSignals
		{
		IMethod methodregisterSignals0;
			IModelElement[] moduleChilds = module.getChildren();
			methodregisterSignals0 = ModelTestUtils.getAssertMethod( moduleChilds, "registerSignals", 1 );
			ModelTestUtils.assertParameterNames( methodregisterSignals0, new String[] {"('for_usr1', 'for_usr2', 'for_alrm')"} );
		}
		//Function test:handle_signals
		{
		IMethod methodhandle_signals1;
			IModelElement[] moduleChilds = module.getChildren();
			methodhandle_signals1 = ModelTestUtils.getAssertMethod( moduleChilds, "handle_signals", 2 );
			ModelTestUtils.assertParameterNames( methodhandle_signals1, new String[] {"sig", "frame"} );
		}
		//Function test:send_signals
		{
		IMethod methodsend_signals2;
			IModelElement[] moduleChilds = module.getChildren();
			methodsend_signals2 = ModelTestUtils.getAssertMethod( moduleChilds, "send_signals", 0 );
		}
		//Class test
		{
		IType classThreadSignals3;
			IModelElement[] moduleChilds = module.getChildren();
			classThreadSignals3 = ModelTestUtils.getAssertClass( moduleChilds, "ThreadSignals" );
			//Function test:test_signals
			{
			IMethod methodtest_signals4;
				IModelElement[] classThreadSignals3Childs = classThreadSignals3.getChildren();
				methodtest_signals4 = ModelTestUtils.getAssertMethod( classThreadSignals3Childs, "test_signals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_signals4, new String[] {"self"} );
			}
			//Function test:spawnSignallingThread
			{
			IMethod methodspawnSignallingThread5;
				IModelElement[] classThreadSignals3Childs = classThreadSignals3.getChildren();
				methodspawnSignallingThread5 = ModelTestUtils.getAssertMethod( classThreadSignals3Childs, "spawnSignallingThread", 1 );
				ModelTestUtils.assertParameterNames( methodspawnSignallingThread5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen119( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cookie.py"));

		assertNotNull("Module test_cookie.py not found", module);
		assertEquals("test_cookie.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cases");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "C");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "C");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "C");
		}

	}
	public void testModelGen120( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_iterlen.py"));

		assertNotNull("Module test_iterlen.py not found", module);
		assertEquals("test_iterlen.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "n");
		}
		//Class test
		{
		IType classTestInvariantWithoutMutations0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestInvariantWithoutMutations0 = ModelTestUtils.getAssertClass( moduleChilds, "TestInvariantWithoutMutations" );
			//Function test:test_invariant
			{
			IMethod methodtest_invariant1;
				IModelElement[] classTestInvariantWithoutMutations0Childs = classTestInvariantWithoutMutations0.getChildren();
				methodtest_invariant1 = ModelTestUtils.getAssertMethod( classTestInvariantWithoutMutations0Childs, "test_invariant", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invariant1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestTemporarilyImmutable2;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTemporarilyImmutable2 = ModelTestUtils.getAssertClass( moduleChilds, "TestTemporarilyImmutable" );
			//Function test:test_immutable_during_iteration
			{
			IMethod methodtest_immutable_during_iteration3;
				IModelElement[] classTestTemporarilyImmutable2Childs = classTestTemporarilyImmutable2.getChildren();
				methodtest_immutable_during_iteration3 = ModelTestUtils.getAssertMethod( classTestTemporarilyImmutable2Childs, "test_immutable_during_iteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_immutable_during_iteration3, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestRepeat4;
			IModelElement[] moduleChilds = module.getChildren();
			classTestRepeat4 = ModelTestUtils.getAssertClass( moduleChilds, "TestRepeat" );
			//Function test:setUp
			{
			IMethod methodsetUp5;
				IModelElement[] classTestRepeat4Childs = classTestRepeat4.getChildren();
				methodsetUp5 = ModelTestUtils.getAssertMethod( classTestRepeat4Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp5, new String[] {"self"} );
			}
			//Function test:test_no_len_for_infinite_repeat
			{
			IMethod methodtest_no_len_for_infinite_repeat6;
				IModelElement[] classTestRepeat4Childs = classTestRepeat4.getChildren();
				methodtest_no_len_for_infinite_repeat6 = ModelTestUtils.getAssertMethod( classTestRepeat4Childs, "test_no_len_for_infinite_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_len_for_infinite_repeat6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestXrange7;
			IModelElement[] moduleChilds = module.getChildren();
			classTestXrange7 = ModelTestUtils.getAssertClass( moduleChilds, "TestXrange" );
			//Function test:setUp
			{
			IMethod methodsetUp8;
				IModelElement[] classTestXrange7Childs = classTestXrange7.getChildren();
				methodsetUp8 = ModelTestUtils.getAssertMethod( classTestXrange7Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestXrangeCustomReversed9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestXrangeCustomReversed9 = ModelTestUtils.getAssertClass( moduleChilds, "TestXrangeCustomReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp10;
				IModelElement[] classTestXrangeCustomReversed9Childs = classTestXrangeCustomReversed9.getChildren();
				methodsetUp10 = ModelTestUtils.getAssertMethod( classTestXrangeCustomReversed9Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestTuple11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTuple11 = ModelTestUtils.getAssertClass( moduleChilds, "TestTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classTestTuple11Childs = classTestTuple11.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classTestTuple11Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDeque13;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDeque13 = ModelTestUtils.getAssertClass( moduleChilds, "TestDeque" );
			//Function test:setUp
			{
			IMethod methodsetUp14;
				IModelElement[] classTestDeque13Childs = classTestDeque13.getChildren();
				methodsetUp14 = ModelTestUtils.getAssertMethod( classTestDeque13Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDequeReversed15;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDequeReversed15 = ModelTestUtils.getAssertClass( moduleChilds, "TestDequeReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp16;
				IModelElement[] classTestDequeReversed15Childs = classTestDequeReversed15.getChildren();
				methodsetUp16 = ModelTestUtils.getAssertMethod( classTestDequeReversed15Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp16, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDictKeys17;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictKeys17 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictKeys" );
			//Function test:setUp
			{
			IMethod methodsetUp18;
				IModelElement[] classTestDictKeys17Childs = classTestDictKeys17.getChildren();
				methodsetUp18 = ModelTestUtils.getAssertMethod( classTestDictKeys17Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDictItems19;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictItems19 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictItems" );
			//Function test:setUp
			{
			IMethod methodsetUp20;
				IModelElement[] classTestDictItems19Childs = classTestDictItems19.getChildren();
				methodsetUp20 = ModelTestUtils.getAssertMethod( classTestDictItems19Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp20, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestDictValues21;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictValues21 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictValues" );
			//Function test:setUp
			{
			IMethod methodsetUp22;
				IModelElement[] classTestDictValues21Childs = classTestDictValues21.getChildren();
				methodsetUp22 = ModelTestUtils.getAssertMethod( classTestDictValues21Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp22, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSet23;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSet23 = ModelTestUtils.getAssertClass( moduleChilds, "TestSet" );
			//Function test:setUp
			{
			IMethod methodsetUp24;
				IModelElement[] classTestSet23Childs = classTestSet23.getChildren();
				methodsetUp24 = ModelTestUtils.getAssertMethod( classTestSet23Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp24, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestList25;
			IModelElement[] moduleChilds = module.getChildren();
			classTestList25 = ModelTestUtils.getAssertClass( moduleChilds, "TestList" );
			//Function test:setUp
			{
			IMethod methodsetUp26;
				IModelElement[] classTestList25Childs = classTestList25.getChildren();
				methodsetUp26 = ModelTestUtils.getAssertMethod( classTestList25Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp26, new String[] {"self"} );
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation27;
				IModelElement[] classTestList25Childs = classTestList25.getChildren();
				methodtest_mutation27 = ModelTestUtils.getAssertMethod( classTestList25Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation27, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestListReversed28;
			IModelElement[] moduleChilds = module.getChildren();
			classTestListReversed28 = ModelTestUtils.getAssertClass( moduleChilds, "TestListReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp29;
				IModelElement[] classTestListReversed28Childs = classTestListReversed28.getChildren();
				methodsetUp29 = ModelTestUtils.getAssertMethod( classTestListReversed28Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp29, new String[] {"self"} );
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation30;
				IModelElement[] classTestListReversed28Childs = classTestListReversed28.getChildren();
				methodtest_mutation30 = ModelTestUtils.getAssertMethod( classTestListReversed28Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation30, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSeqIter31;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSeqIter31 = ModelTestUtils.getAssertClass( moduleChilds, "TestSeqIter" );
			//Function test:setUp
			{
			IMethod methodsetUp32;
				IModelElement[] classTestSeqIter31Childs = classTestSeqIter31.getChildren();
				methodsetUp32 = ModelTestUtils.getAssertMethod( classTestSeqIter31Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp32, new String[] {"self"} );
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation33;
				IModelElement[] classTestSeqIter31Childs = classTestSeqIter31.getChildren();
				methodtest_mutation33 = ModelTestUtils.getAssertMethod( classTestSeqIter31Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation33, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSeqIterReversed34;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSeqIterReversed34 = ModelTestUtils.getAssertClass( moduleChilds, "TestSeqIterReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp35;
				IModelElement[] classTestSeqIterReversed34Childs = classTestSeqIterReversed34.getChildren();
				methodsetUp35 = ModelTestUtils.getAssertMethod( classTestSeqIterReversed34Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp35, new String[] {"self"} );
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation36;
				IModelElement[] classTestSeqIterReversed34Childs = classTestSeqIterReversed34.getChildren();
				methodtest_mutation36 = ModelTestUtils.getAssertMethod( classTestSeqIterReversed34Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation36, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "unittests");
		}

	}
	public void testModelGen121( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_time.py"));

		assertNotNull("Module test_time.py not found", module);
		assertEquals("test_time.py", module.getElementName());
		
		//Class test
		{
		IType classTimeTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classTimeTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "TimeTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:test_data_attributes
			{
			IMethod methodtest_data_attributes2;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_data_attributes2 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_data_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_data_attributes2, new String[] {"self"} );
			}
			//Function test:test_clock
			{
			IMethod methodtest_clock3;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_clock3 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_clock", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clock3, new String[] {"self"} );
			}
			//Function test:test_conversions
			{
			IMethod methodtest_conversions4;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_conversions4 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_conversions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conversions4, new String[] {"self"} );
			}
			//Function test:test_sleep
			{
			IMethod methodtest_sleep5;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_sleep5 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_sleep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sleep5, new String[] {"self"} );
			}
			//Function test:test_strftime
			{
			IMethod methodtest_strftime6;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_strftime6 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime6, new String[] {"self"} );
			}
			//Function test:test_strftime_bounds_checking
			{
			IMethod methodtest_strftime_bounds_checking7;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_strftime_bounds_checking7 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_strftime_bounds_checking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime_bounds_checking7, new String[] {"self"} );
			}
			//Function test:test_strptime
			{
			IMethod methodtest_strptime8;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_strptime8 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_strptime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strptime8, new String[] {"self"} );
			}
			//Function test:test_asctime
			{
			IMethod methodtest_asctime9;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_asctime9 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_asctime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_asctime9, new String[] {"self"} );
			}
			//Function test:test_tzset
			{
			IMethod methodtest_tzset10;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_tzset10 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_tzset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzset10, new String[] {"self"} );
			}
			//Function test:test_insane_timestamps
			{
			IMethod methodtest_insane_timestamps11;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_insane_timestamps11 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_insane_timestamps", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_timestamps11, new String[] {"self"} );
			}
			//Function test:test_ctime_without_arg
			{
			IMethod methodtest_ctime_without_arg12;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_ctime_without_arg12 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_ctime_without_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ctime_without_arg12, new String[] {"self"} );
			}
			//Function test:test_gmtime_without_arg
			{
			IMethod methodtest_gmtime_without_arg13;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_gmtime_without_arg13 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_gmtime_without_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gmtime_without_arg13, new String[] {"self"} );
			}
			//Function test:test_localtime_without_arg
			{
			IMethod methodtest_localtime_without_arg14;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_localtime_without_arg14 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_localtime_without_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_localtime_without_arg14, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen122( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_userlist.py"));

		assertNotNull("Module test_userlist.py not found", module);
		assertEquals("test_userlist.py", module.getElementName());
		
		//Class test
		{
		IType classUserListTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUserListTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UserListTest" );
			{
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUserListTest0Childs, "type2test");
			}
			//Function test:test_getslice
			{
			IMethod methodtest_getslice1;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_getslice1 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_getslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getslice1, new String[] {"self"} );
			}
			//Function test:test_add_specials
			{
			IMethod methodtest_add_specials2;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_add_specials2 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_add_specials", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add_specials2, new String[] {"self"} );
			}
			//Function test:test_radd_specials
			{
			IMethod methodtest_radd_specials3;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_radd_specials3 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_radd_specials", 1 );
				ModelTestUtils.assertParameterNames( methodtest_radd_specials3, new String[] {"self"} );
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd4;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_iadd4 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd4, new String[] {"self"} );
			}
			//Function test:test_mixedcmp
			{
			IMethod methodtest_mixedcmp5;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_mixedcmp5 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_mixedcmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixedcmp5, new String[] {"self"} );
			}
			//Function test:test_mixedadd
			{
			IMethod methodtest_mixedadd6;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_mixedadd6 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_mixedadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixedadd6, new String[] {"self"} );
			}
			//Function test:test_getitemoverwriteiter
			{
			IMethod methodtest_getitemoverwriteiter7;
				IModelElement[] classUserListTest0Childs = classUserListTest0.getChildren();
				methodtest_getitemoverwriteiter7 = ModelTestUtils.getAssertMethod( classUserListTest0Childs, "test_getitemoverwriteiter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitemoverwriteiter7, new String[] {"self"} );
				//Class test
				{
				IType classT8;
					IModelElement[] methodtest_getitemoverwriteiter7Childs = methodtest_getitemoverwriteiter7.getChildren();
					classT8 = ModelTestUtils.getAssertClass( methodtest_getitemoverwriteiter7Childs, "T" );
					//Function test:__getitem__
					{
					IMethod method__getitem__9;
						IModelElement[] classT8Childs = classT8.getChildren();
						method__getitem__9 = ModelTestUtils.getAssertMethod( classT8Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__9, new String[] {"self", "key"} );
					}
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen123( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_zipimport.py"));

		assertNotNull("Module test_zipimport.py not found", module);
		assertEquals("test_zipimport.py", module.getElementName());
		
		//Function test:make_pyc
		{
		IMethod methodmake_pyc0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_pyc0 = ModelTestUtils.getAssertMethod( moduleChilds, "make_pyc", 2 );
			ModelTestUtils.assertParameterNames( methodmake_pyc0, new String[] {"co", "mtime"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NOW");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_pyc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pyc_ext");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pyc_ext");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTMOD");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTPACK");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTPACK2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TEMP_ZIP");
		}
		//Class test
		{
		IType classUncompressedZipImportTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classUncompressedZipImportTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "UncompressedZipImportTestCase" );
			{
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUncompressedZipImportTestCase1Childs, "compression");
			}
			//Function test:setUp
			{
			IMethod methodsetUp2;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodsetUp2 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp2, new String[] {"self"} );
			}
			//Function test:doTest
			{
			IMethod methoddoTest3;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methoddoTest3 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "doTest", 5 );
				ModelTestUtils.assertParameterNames( methoddoTest3, new String[] {"self", "expected_ext", "files", "modules", "kw"} );
			}
			//Function test:testAFakeZlib
			{
			IMethod methodtestAFakeZlib4;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestAFakeZlib4 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testAFakeZlib", 1 );
				ModelTestUtils.assertParameterNames( methodtestAFakeZlib4, new String[] {"self"} );
			}
			//Function test:testPy
			{
			IMethod methodtestPy5;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestPy5 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testPy", 1 );
				ModelTestUtils.assertParameterNames( methodtestPy5, new String[] {"self"} );
			}
			//Function test:testPyc
			{
			IMethod methodtestPyc6;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestPyc6 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testPyc", 1 );
				ModelTestUtils.assertParameterNames( methodtestPyc6, new String[] {"self"} );
			}
			//Function test:testBoth
			{
			IMethod methodtestBoth7;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestBoth7 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testBoth", 1 );
				ModelTestUtils.assertParameterNames( methodtestBoth7, new String[] {"self"} );
			}
			//Function test:testEmptyPy
			{
			IMethod methodtestEmptyPy8;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestEmptyPy8 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testEmptyPy", 1 );
				ModelTestUtils.assertParameterNames( methodtestEmptyPy8, new String[] {"self"} );
			}
			//Function test:testBadMagic
			{
			IMethod methodtestBadMagic9;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestBadMagic9 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testBadMagic", 1 );
				ModelTestUtils.assertParameterNames( methodtestBadMagic9, new String[] {"self"} );
			}
			//Function test:testBadMagic2
			{
			IMethod methodtestBadMagic210;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestBadMagic210 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testBadMagic2", 1 );
				ModelTestUtils.assertParameterNames( methodtestBadMagic210, new String[] {"self"} );
			}
			//Function test:testBadMTime
			{
			IMethod methodtestBadMTime11;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestBadMTime11 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testBadMTime", 1 );
				ModelTestUtils.assertParameterNames( methodtestBadMTime11, new String[] {"self"} );
			}
			//Function test:testPackage
			{
			IMethod methodtestPackage12;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestPackage12 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testPackage", 1 );
				ModelTestUtils.assertParameterNames( methodtestPackage12, new String[] {"self"} );
			}
			//Function test:testDeepPackage
			{
			IMethod methodtestDeepPackage13;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestDeepPackage13 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testDeepPackage", 1 );
				ModelTestUtils.assertParameterNames( methodtestDeepPackage13, new String[] {"self"} );
			}
			//Function test:testGetData
			{
			IMethod methodtestGetData14;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestGetData14 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testGetData", 1 );
				ModelTestUtils.assertParameterNames( methodtestGetData14, new String[] {"self"} );
			}
			//Function test:testImporterAttr
			{
			IMethod methodtestImporterAttr15;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestImporterAttr15 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testImporterAttr", 1 );
				ModelTestUtils.assertParameterNames( methodtestImporterAttr15, new String[] {"self"} );
			}
			//Function test:testImport_WithStuff
			{
			IMethod methodtestImport_WithStuff16;
				IModelElement[] classUncompressedZipImportTestCase1Childs = classUncompressedZipImportTestCase1.getChildren();
				methodtestImport_WithStuff16 = ModelTestUtils.getAssertMethod( classUncompressedZipImportTestCase1Childs, "testImport_WithStuff", 1 );
				ModelTestUtils.assertParameterNames( methodtestImport_WithStuff16, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCompressedZipImportTestCase17;
			IModelElement[] moduleChilds = module.getChildren();
			classCompressedZipImportTestCase17 = ModelTestUtils.getAssertClass( moduleChilds, "CompressedZipImportTestCase" );
			{
				IModelElement[] classCompressedZipImportTestCase17Childs = classCompressedZipImportTestCase17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCompressedZipImportTestCase17Childs, "compression");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen124( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_tarfile.py"));

		assertNotNull("Module test_tarfile.py not found", module);
		assertEquals("test_tarfile.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "gzip");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "bz2");
		}
		//Function test:path
		{
		IMethod methodpath0;
			IModelElement[] moduleChilds = module.getChildren();
			methodpath0 = ModelTestUtils.getAssertMethod( moduleChilds, "path", 1 );
			ModelTestUtils.assertParameterNames( methodpath0, new String[] {"path"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testtar");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tempdir");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tempname");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "membercount");
		}
		//Function test:tarname
		{
		IMethod methodtarname1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtarname1 = ModelTestUtils.getAssertMethod( moduleChilds, "tarname", 1 );
			ModelTestUtils.assertParameterNames( methodtarname1, new String[] {"comp"} );
		}
		//Function test:dirname
		{
		IMethod methoddirname2;
			IModelElement[] moduleChilds = module.getChildren();
			methoddirname2 = ModelTestUtils.getAssertMethod( moduleChilds, "dirname", 0 );
		}
		//Function test:tmpname
		{
		IMethod methodtmpname3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtmpname3 = ModelTestUtils.getAssertMethod( moduleChilds, "tmpname", 0 );
		}
		//Class test
		{
		IType classBaseTest4;
			IModelElement[] moduleChilds = module.getChildren();
			classBaseTest4 = ModelTestUtils.getAssertClass( moduleChilds, "BaseTest" );
			{
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest4Childs, "comp");
			}
			{
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest4Childs, "mode");
			}
			{
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest4Childs, "sep");
			}
			//Function test:setUp
			{
			IMethod methodsetUp5;
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				methodsetUp5 = ModelTestUtils.getAssertMethod( classBaseTest4Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp5, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown6;
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				methodtearDown6 = ModelTestUtils.getAssertMethod( classBaseTest4Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadTest7;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTest7 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTest" );
			//Function test:test
			{
			IMethod methodtest8;
				IModelElement[] classReadTest7Childs = classReadTest7.getChildren();
				methodtest8 = ModelTestUtils.getAssertMethod( classReadTest7Childs, "test", 1 );
				ModelTestUtils.assertParameterNames( methodtest8, new String[] {"self"} );
			}
			//Function test:test_sparse
			{
			IMethod methodtest_sparse9;
				IModelElement[] classReadTest7Childs = classReadTest7.getChildren();
				methodtest_sparse9 = ModelTestUtils.getAssertMethod( classReadTest7Childs, "test_sparse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sparse9, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines10;
				IModelElement[] classReadTest7Childs = classReadTest7.getChildren();
				methodtest_readlines10 = ModelTestUtils.getAssertMethod( classReadTest7Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines10, new String[] {"self"} );
			}
			//Function test:test_seek
			{
			IMethod methodtest_seek11;
				IModelElement[] classReadTest7Childs = classReadTest7.getChildren();
				methodtest_seek11 = ModelTestUtils.getAssertMethod( classReadTest7Childs, "test_seek", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seek11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadStreamTest12;
			IModelElement[] moduleChilds = module.getChildren();
			classReadStreamTest12 = ModelTestUtils.getAssertClass( moduleChilds, "ReadStreamTest" );
			{
				IModelElement[] classReadStreamTest12Childs = classReadStreamTest12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadStreamTest12Childs, "sep");
			}
			//Function test:test
			{
			IMethod methodtest13;
				IModelElement[] classReadStreamTest12Childs = classReadStreamTest12.getChildren();
				methodtest13 = ModelTestUtils.getAssertMethod( classReadStreamTest12Childs, "test", 1 );
				ModelTestUtils.assertParameterNames( methodtest13, new String[] {"self"} );
			}
			//Function test:test_stream
			{
			IMethod methodtest_stream14;
				IModelElement[] classReadStreamTest12Childs = classReadStreamTest12.getChildren();
				methodtest_stream14 = ModelTestUtils.getAssertMethod( classReadStreamTest12Childs, "test_stream", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stream14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWriteTest15;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteTest15 = ModelTestUtils.getAssertClass( moduleChilds, "WriteTest" );
			{
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTest15Childs, "mode");
			}
			//Function test:setUp
			{
			IMethod methodsetUp16;
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				methodsetUp16 = ModelTestUtils.getAssertMethod( classWriteTest15Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp16, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown17;
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				methodtearDown17 = ModelTestUtils.getAssertMethod( classWriteTest15Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown17, new String[] {"self"} );
			}
			//Function test:test_posix
			{
			IMethod methodtest_posix18;
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				methodtest_posix18 = ModelTestUtils.getAssertMethod( classWriteTest15Childs, "test_posix", 1 );
				ModelTestUtils.assertParameterNames( methodtest_posix18, new String[] {"self"} );
			}
			//Function test:test_nonposix
			{
			IMethod methodtest_nonposix19;
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				methodtest_nonposix19 = ModelTestUtils.getAssertMethod( classWriteTest15Childs, "test_nonposix", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonposix19, new String[] {"self"} );
			}
			//Function test:test_small
			{
			IMethod methodtest_small20;
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				methodtest_small20 = ModelTestUtils.getAssertMethod( classWriteTest15Childs, "test_small", 1 );
				ModelTestUtils.assertParameterNames( methodtest_small20, new String[] {"self"} );
			}
			//Function test:_test
			{
			IMethod method_test21;
				IModelElement[] classWriteTest15Childs = classWriteTest15.getChildren();
				method_test21 = ModelTestUtils.getAssertMethod( classWriteTest15Childs, "_test", 1 );
				ModelTestUtils.assertParameterNames( method_test21, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWriteStreamTest22;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteStreamTest22 = ModelTestUtils.getAssertClass( moduleChilds, "WriteStreamTest" );
			{
				IModelElement[] classWriteStreamTest22Childs = classWriteStreamTest22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteStreamTest22Childs, "sep");
			}
		}
		//Class test
		{
		IType classWriteGNULongTest23;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteGNULongTest23 = ModelTestUtils.getAssertClass( moduleChilds, "WriteGNULongTest" );
			//Function test:setUp
			{
			IMethod methodsetUp24;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodsetUp24 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp24, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown25;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtearDown25 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown25, new String[] {"self"} );
			}
			//Function test:_length
			{
			IMethod method_length26;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				method_length26 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "_length", 2 );
				ModelTestUtils.assertParameterNames( method_length26, new String[] {"self", "s"} );
			}
			//Function test:_calc_size
			{
			IMethod method_calc_size27;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				method_calc_size27 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "_calc_size", 3 );
				ModelTestUtils.assertParameterNames( method_calc_size27, new String[] {"self", "name", "link"} );
			}
			//Function test:_test
			{
			IMethod method_test28;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				method_test28 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "_test", 3 );
				ModelTestUtils.assertParameterNames( method_test28, new String[] {"self", "name", "link"} );
			}
			//Function test:test_longname_1023
			{
			IMethod methodtest_longname_102329;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longname_102329 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longname_1023", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longname_102329, new String[] {"self"} );
			}
			//Function test:test_longname_1024
			{
			IMethod methodtest_longname_102430;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longname_102430 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longname_1024", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longname_102430, new String[] {"self"} );
			}
			//Function test:test_longname_1025
			{
			IMethod methodtest_longname_102531;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longname_102531 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longname_1025", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longname_102531, new String[] {"self"} );
			}
			//Function test:test_longlink_1023
			{
			IMethod methodtest_longlink_102332;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longlink_102332 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longlink_1023", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longlink_102332, new String[] {"self"} );
			}
			//Function test:test_longlink_1024
			{
			IMethod methodtest_longlink_102433;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longlink_102433 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longlink_1024", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longlink_102433, new String[] {"self"} );
			}
			//Function test:test_longlink_1025
			{
			IMethod methodtest_longlink_102534;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longlink_102534 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longlink_1025", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longlink_102534, new String[] {"self"} );
			}
			//Function test:test_longnamelink_1023
			{
			IMethod methodtest_longnamelink_102335;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longnamelink_102335 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longnamelink_1023", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longnamelink_102335, new String[] {"self"} );
			}
			//Function test:test_longnamelink_1024
			{
			IMethod methodtest_longnamelink_102436;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longnamelink_102436 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longnamelink_1024", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longnamelink_102436, new String[] {"self"} );
			}
			//Function test:test_longnamelink_1025
			{
			IMethod methodtest_longnamelink_102537;
				IModelElement[] classWriteGNULongTest23Childs = classWriteGNULongTest23.getChildren();
				methodtest_longnamelink_102537 = ModelTestUtils.getAssertMethod( classWriteGNULongTest23Childs, "test_longnamelink_1025", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longnamelink_102537, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classExtractHardlinkTest38;
			IModelElement[] moduleChilds = module.getChildren();
			classExtractHardlinkTest38 = ModelTestUtils.getAssertClass( moduleChilds, "ExtractHardlinkTest" );
			//Function test:test_hardlink
			{
			IMethod methodtest_hardlink39;
				IModelElement[] classExtractHardlinkTest38Childs = classExtractHardlinkTest38.getChildren();
				methodtest_hardlink39 = ModelTestUtils.getAssertMethod( classExtractHardlinkTest38Childs, "test_hardlink", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hardlink39, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadTestGzip40;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTestGzip40 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTestGzip" );
			{
				IModelElement[] classReadTestGzip40Childs = classReadTestGzip40.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadTestGzip40Childs, "comp");
			}
		}
		//Class test
		{
		IType classReadStreamTestGzip41;
			IModelElement[] moduleChilds = module.getChildren();
			classReadStreamTestGzip41 = ModelTestUtils.getAssertClass( moduleChilds, "ReadStreamTestGzip" );
			{
				IModelElement[] classReadStreamTestGzip41Childs = classReadStreamTestGzip41.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadStreamTestGzip41Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteTestGzip42;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteTestGzip42 = ModelTestUtils.getAssertClass( moduleChilds, "WriteTestGzip" );
			{
				IModelElement[] classWriteTestGzip42Childs = classWriteTestGzip42.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTestGzip42Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteStreamTestGzip43;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteStreamTestGzip43 = ModelTestUtils.getAssertClass( moduleChilds, "WriteStreamTestGzip" );
			{
				IModelElement[] classWriteStreamTestGzip43Childs = classWriteStreamTestGzip43.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteStreamTestGzip43Childs, "comp");
			}
		}
		//Class test
		{
		IType classFileModeTest44;
			IModelElement[] moduleChilds = module.getChildren();
			classFileModeTest44 = ModelTestUtils.getAssertClass( moduleChilds, "FileModeTest" );
			//Function test:test_modes
			{
			IMethod methodtest_modes45;
				IModelElement[] classFileModeTest44Childs = classFileModeTest44.getChildren();
				methodtest_modes45 = ModelTestUtils.getAssertMethod( classFileModeTest44Childs, "test_modes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_modes45, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadTestBzip246;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTestBzip246 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTestBzip2" );
			{
				IModelElement[] classReadTestBzip246Childs = classReadTestBzip246.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadTestBzip246Childs, "comp");
			}
		}
		//Class test
		{
		IType classReadStreamTestBzip247;
			IModelElement[] moduleChilds = module.getChildren();
			classReadStreamTestBzip247 = ModelTestUtils.getAssertClass( moduleChilds, "ReadStreamTestBzip2" );
			{
				IModelElement[] classReadStreamTestBzip247Childs = classReadStreamTestBzip247.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadStreamTestBzip247Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteTestBzip248;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteTestBzip248 = ModelTestUtils.getAssertClass( moduleChilds, "WriteTestBzip2" );
			{
				IModelElement[] classWriteTestBzip248Childs = classWriteTestBzip248.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTestBzip248Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteStreamTestBzip249;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteStreamTestBzip249 = ModelTestUtils.getAssertClass( moduleChilds, "WriteStreamTestBzip2" );
			{
				IModelElement[] classWriteStreamTestBzip249Childs = classWriteStreamTestBzip249.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteStreamTestBzip249Childs, "comp");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main50;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main50 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen125( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_socket_ssl.py"));

		assertNotNull("Module test_socket_ssl.py not found", module);
		assertEquals("test_socket_ssl.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "skip_expected");
		}
		//Function test:test_basic
		{
		IMethod methodtest_basic0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_basic0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_basic", 0 );
		}
		//Function test:test_rude_shutdown
		{
		IMethod methodtest_rude_shutdown1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_rude_shutdown1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_rude_shutdown", 0 );
			//Function test:listener
			{
			IMethod methodlistener2;
				IModelElement[] methodtest_rude_shutdown1Childs = methodtest_rude_shutdown1.getChildren();
				methodlistener2 = ModelTestUtils.getAssertMethod( methodtest_rude_shutdown1Childs, "listener", 0 );
			}
			//Function test:connector
			{
			IMethod methodconnector3;
				IModelElement[] methodtest_rude_shutdown1Childs = methodtest_rude_shutdown1.getChildren();
				methodconnector3 = ModelTestUtils.getAssertMethod( methodtest_rude_shutdown1Childs, "connector", 0 );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen126( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_shutil.py"));

		assertNotNull("Module test_shutil.py not found", module);
		assertEquals("test_shutil.py", module.getElementName());
		
		//Class test
		{
		IType classTestShutil0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestShutil0 = ModelTestUtils.getAssertClass( moduleChilds, "TestShutil" );
			//Function test:test_rmtree_errors
			{
			IMethod methodtest_rmtree_errors1;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_rmtree_errors1 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_rmtree_errors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rmtree_errors1, new String[] {"self"} );
			}
			//Function test:test_on_error
			{
			IMethod methodtest_on_error2;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_on_error2 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_on_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_on_error2, new String[] {"self"} );
			}
			//Function test:check_args_to_onerror
			{
			IMethod methodcheck_args_to_onerror3;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodcheck_args_to_onerror3 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "check_args_to_onerror", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_args_to_onerror3, new String[] {"self", "func", "arg", "exc"} );
			}
			//Function test:test_rmtree_dont_delete_file
			{
			IMethod methodtest_rmtree_dont_delete_file4;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_rmtree_dont_delete_file4 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_rmtree_dont_delete_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rmtree_dont_delete_file4, new String[] {"self"} );
			}
			//Function test:test_dont_move_dir_in_itself
			{
			IMethod methodtest_dont_move_dir_in_itself5;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_dont_move_dir_in_itself5 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_dont_move_dir_in_itself", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_move_dir_in_itself5, new String[] {"self"} );
			}
			//Function test:test_dont_copy_file_onto_link_to_itself
			{
			IMethod methodtest_dont_copy_file_onto_link_to_itself6;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_dont_copy_file_onto_link_to_itself6 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_dont_copy_file_onto_link_to_itself", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_copy_file_onto_link_to_itself6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen127( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_popen.py"));

		assertNotNull("Module test_popen.py not found", module);
		assertEquals("test_popen.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "python");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "python");
		}
		//Function test:_do_test_commandline
		{
		IMethod method_do_test_commandline0;
			IModelElement[] moduleChilds = module.getChildren();
			method_do_test_commandline0 = ModelTestUtils.getAssertMethod( moduleChilds, "_do_test_commandline", 2 );
			ModelTestUtils.assertParameterNames( method_do_test_commandline0, new String[] {"cmdline", "expected"} );
		}
		//Function test:_test_commandline
		{
		IMethod method_test_commandline1;
			IModelElement[] moduleChilds = module.getChildren();
			method_test_commandline1 = ModelTestUtils.getAssertMethod( moduleChilds, "_test_commandline", 0 );
		}
		//Function test:main
		{
		IMethod methodmain2;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain2 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void REM_testModelGen128( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_extcall.py"));

		assertNotNull("Module test_extcall.py not found", module);
		assertEquals("test_extcall.py", module.getElementName());
		
		//Function test:e
		{
		IMethod methode0;
			IModelElement[] moduleChilds = module.getChildren();
			methode0 = ModelTestUtils.getAssertMethod( moduleChilds, "e", 2 );
			ModelTestUtils.assertParameterNames( methode0, new String[] {"a", "b"} );
		}
		//Function test:f
		{
		IMethod methodf1;
			IModelElement[] moduleChilds = module.getChildren();
			methodf1 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 2 );
			ModelTestUtils.assertParameterNames( methodf1, new String[] {"a", "k"} );
		}
		//Function test:g
		{
		IMethod methodg2;
			IModelElement[] moduleChilds = module.getChildren();
			methodg2 = ModelTestUtils.getAssertMethod( moduleChilds, "g", 3 );
			ModelTestUtils.assertParameterNames( methodg2, new String[] {"x", "y", "z"} );
		}
		//Function test:h
		{
		IMethod methodh3;
			IModelElement[] moduleChilds = module.getChildren();
			methodh3 = ModelTestUtils.getAssertMethod( moduleChilds, "h", 3 );
			ModelTestUtils.assertParameterNames( methodh3, new String[] {"j", "a", "h"} );
		}
		//Class test
		{
		IType classNothing4;
			IModelElement[] moduleChilds = module.getChildren();
			classNothing4 = ModelTestUtils.getAssertClass( moduleChilds, "Nothing" );
		}
		//Class test
		{
		IType classNothing5;
			IModelElement[] moduleChilds = module.getChildren();
			classNothing5 = ModelTestUtils.getAssertClass( moduleChilds, "Nothing" );
			//Function test:__len__
			{
			IMethod method__len__6;
				IModelElement[] classNothing5Childs = classNothing5.getChildren();
				method__len__6 = ModelTestUtils.getAssertMethod( classNothing5Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNothing7;
			IModelElement[] moduleChilds = module.getChildren();
			classNothing7 = ModelTestUtils.getAssertClass( moduleChilds, "Nothing" );
			//Function test:__len__
			{
			IMethod method__len__8;
				IModelElement[] classNothing7Childs = classNothing7.getChildren();
				method__len__8 = ModelTestUtils.getAssertMethod( classNothing7Childs, "__len__", 1 );
				ModelTestUtils.assertParameterNames( method__len__8, new String[] {"self"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__9;
				IModelElement[] classNothing7Childs = classNothing7.getChildren();
				method__getitem__9 = ModelTestUtils.getAssertMethod( classNothing7Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__9, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classNothing10;
			IModelElement[] moduleChilds = module.getChildren();
			classNothing10 = ModelTestUtils.getAssertClass( moduleChilds, "Nothing" );
			//Function test:__init__
			{
			IMethod method__init__11;
				IModelElement[] classNothing10Childs = classNothing10.getChildren();
				method__init__11 = ModelTestUtils.getAssertMethod( classNothing10Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__11, new String[] {"self"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__12;
				IModelElement[] classNothing10Childs = classNothing10.getChildren();
				method__iter__12 = ModelTestUtils.getAssertMethod( classNothing10Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNothing13;
			IModelElement[] moduleChilds = module.getChildren();
			classNothing13 = ModelTestUtils.getAssertClass( moduleChilds, "Nothing" );
			//Function test:__init__
			{
			IMethod method__init__14;
				IModelElement[] classNothing13Childs = classNothing13.getChildren();
				method__init__14 = ModelTestUtils.getAssertMethod( classNothing13Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__14, new String[] {"self"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__15;
				IModelElement[] classNothing13Childs = classNothing13.getChildren();
				method__iter__15 = ModelTestUtils.getAssertMethod( classNothing13Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__15, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext16;
				IModelElement[] classNothing13Childs = classNothing13.getChildren();
				methodnext16 = ModelTestUtils.getAssertMethod( classNothing13Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext16, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d2");
		}
		//Function test:saboteur
		{
		IMethod methodsaboteur17;
			IModelElement[] moduleChilds = module.getChildren();
			methodsaboteur17 = ModelTestUtils.getAssertMethod( moduleChilds, "saboteur", 1 );
			ModelTestUtils.assertParameterNames( methodsaboteur17, new String[] {"kw"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "kw");
		}
		//Function test:f2
		{
		IMethod methodf218;
			IModelElement[] moduleChilds = module.getChildren();
			methodf218 = ModelTestUtils.getAssertMethod( moduleChilds, "f2", 2 );
			ModelTestUtils.assertParameterNames( methodf218, new String[] {"a", "b"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "key");
		}
		//Class test
		{
		IType classFoo19;
			IModelElement[] moduleChilds = module.getChildren();
			classFoo19 = ModelTestUtils.getAssertClass( moduleChilds, "Foo" );
			//Function test:method
			{
			IMethod methodmethod20;
				IModelElement[] classFoo19Childs = classFoo19.getChildren();
				methodmethod20 = ModelTestUtils.getAssertMethod( classFoo19Childs, "method", 3 );
				ModelTestUtils.assertParameterNames( methodmethod20, new String[] {"self", "arg1", "arg2"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "funcs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "maxargs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "name");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "arglist");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "decl");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "func");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "func");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "kwdict");
		}

	}
	public void testModelGen129( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_mmap.py"));

		assertNotNull("Module test_mmap.py not found", module);
		assertEquals("test_mmap.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PAGESIZE");
		}
		//Function test:test_both
		{
		IMethod methodtest_both0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_both0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_both", 0 );
		}

	}
	public void testModelGen130( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_threaded_import.py"));

		assertNotNull("Module test_threaded_import.py not found", module);
		assertEquals("test_threaded_import.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "critical_section");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "done");
		}
		//Function test:task
		{
		IMethod methodtask0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtask0 = ModelTestUtils.getAssertMethod( moduleChilds, "task", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen131( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_asynchat.py"));

		assertNotNull("Module test_asynchat.py not found", module);
		assertEquals("test_asynchat.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "HOST");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "PORT");
		}
		//Class test
		{
		IType classecho_server0;
			IModelElement[] moduleChilds = module.getChildren();
			classecho_server0 = ModelTestUtils.getAssertClass( moduleChilds, "echo_server" );
			//Function test:run
			{
			IMethod methodrun1;
				IModelElement[] classecho_server0Childs = classecho_server0.getChildren();
				methodrun1 = ModelTestUtils.getAssertMethod( classecho_server0Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classecho_client2;
			IModelElement[] moduleChilds = module.getChildren();
			classecho_client2 = ModelTestUtils.getAssertClass( moduleChilds, "echo_client" );
			//Function test:__init__
			{
			IMethod method__init__3;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				method__init__3 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self"} );
			}
			//Function test:handle_connect
			{
			IMethod methodhandle_connect4;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				methodhandle_connect4 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "handle_connect", 1 );
				ModelTestUtils.assertParameterNames( methodhandle_connect4, new String[] {"self"} );
			}
			//Function test:collect_incoming_data
			{
			IMethod methodcollect_incoming_data5;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				methodcollect_incoming_data5 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "collect_incoming_data", 2 );
				ModelTestUtils.assertParameterNames( methodcollect_incoming_data5, new String[] {"self", "data"} );
			}
			//Function test:found_terminator
			{
			IMethod methodfound_terminator6;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				methodfound_terminator6 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "found_terminator", 1 );
				ModelTestUtils.assertParameterNames( methodfound_terminator6, new String[] {"self"} );
			}
		}
		//Function test:main
		{
		IMethod methodmain7;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain7 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void REM_testModelGen132( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_compile.py"));

		assertNotNull("Module test_compile.py not found", module);
		assertEquals("test_compile.py", module.getElementName());
		
		//Class test
		{
		IType classTestSpecifics0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSpecifics0 = ModelTestUtils.getAssertClass( moduleChilds, "TestSpecifics" );
			//Function test:test_debug_assignment
			{
			IMethod methodtest_debug_assignment1;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_debug_assignment1 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_debug_assignment", 1 );
				ModelTestUtils.assertParameterNames( methodtest_debug_assignment1, new String[] {"self"} );
			}
			//Function test:test_argument_handling
			{
			IMethod methodtest_argument_handling2;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_argument_handling2 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_argument_handling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argument_handling2, new String[] {"self"} );
			}
			//Function test:test_syntax_error
			{
			IMethod methodtest_syntax_error3;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_syntax_error3 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_syntax_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_syntax_error3, new String[] {"self"} );
			}
			//Function test:test_duplicate_global_local
			{
			IMethod methodtest_duplicate_global_local4;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_duplicate_global_local4 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_duplicate_global_local", 1 );
				ModelTestUtils.assertParameterNames( methodtest_duplicate_global_local4, new String[] {"self"} );
			}
			//Function test:test_exec_with_general_mapping_for_locals
			{
			IMethod methodtest_exec_with_general_mapping_for_locals5;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_exec_with_general_mapping_for_locals5 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_exec_with_general_mapping_for_locals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exec_with_general_mapping_for_locals5, new String[] {"self"} );
				//Class test
				{
				IType classM6;
					IModelElement[] methodtest_exec_with_general_mapping_for_locals5Childs = methodtest_exec_with_general_mapping_for_locals5.getChildren();
					classM6 = ModelTestUtils.getAssertClass( methodtest_exec_with_general_mapping_for_locals5Childs, "M" );
					//Function test:__getitem__
					{
					IMethod method__getitem__7;
						IModelElement[] classM6Childs = classM6.getChildren();
						method__getitem__7 = ModelTestUtils.getAssertMethod( classM6Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__7, new String[] {"self", "key"} );
					}
					//Function test:__setitem__
					{
					IMethod method__setitem__8;
						IModelElement[] classM6Childs = classM6.getChildren();
						method__setitem__8 = ModelTestUtils.getAssertMethod( classM6Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__8, new String[] {"self", "key", "value"} );
					}
					//Function test:keys
					{
					IMethod methodkeys9;
						IModelElement[] classM6Childs = classM6.getChildren();
						methodkeys9 = ModelTestUtils.getAssertMethod( classM6Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys9, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classA10;
					IModelElement[] methodtest_exec_with_general_mapping_for_locals5Childs = methodtest_exec_with_general_mapping_for_locals5.getChildren();
					classA10 = ModelTestUtils.getAssertClass( methodtest_exec_with_general_mapping_for_locals5Childs, "A" );
				}
				//Class test
				{
				IType classD11;
					IModelElement[] methodtest_exec_with_general_mapping_for_locals5Childs = methodtest_exec_with_general_mapping_for_locals5.getChildren();
					classD11 = ModelTestUtils.getAssertClass( methodtest_exec_with_general_mapping_for_locals5Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__12;
						IModelElement[] classD11Childs = classD11.getChildren();
						method__getitem__12 = ModelTestUtils.getAssertMethod( classD11Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__12, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_complex_args
			{
			IMethod methodtest_complex_args13;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_complex_args13 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_complex_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_complex_args13, new String[] {"self"} );
				//Function test:comp_args
				{
				IMethod methodcomp_args14;
					IModelElement[] methodtest_complex_args13Childs = methodtest_complex_args13.getChildren();
					methodcomp_args14 = ModelTestUtils.getAssertMethod( methodtest_complex_args13Childs, "comp_args", 1 );
					ModelTestUtils.assertParameterNames( methodcomp_args14, new String[] {"('a', 'b')"} );
				}
				//Function test:comp_args
				{
				IMethod methodcomp_args15;
					IModelElement[] methodtest_complex_args13Childs = methodtest_complex_args13.getChildren();
					methodcomp_args15 = ModelTestUtils.getAssertMethod( methodtest_complex_args13Childs, "comp_args", 1 );
					ModelTestUtils.assertParameterNames( methodcomp_args15, new String[] {"('a', 'b')"} );
				}
				//Function test:comp_args
				{
				IMethod methodcomp_args16;
					IModelElement[] methodtest_complex_args13Childs = methodtest_complex_args13.getChildren();
					methodcomp_args16 = ModelTestUtils.getAssertMethod( methodtest_complex_args13Childs, "comp_args", 2 );
					ModelTestUtils.assertParameterNames( methodcomp_args16, new String[] {"a", "('b', 'c')"} );
				}
				//Function test:comp_args
				{
				IMethod methodcomp_args17;
					IModelElement[] methodtest_complex_args13Childs = methodtest_complex_args13.getChildren();
					methodcomp_args17 = ModelTestUtils.getAssertMethod( methodtest_complex_args13Childs, "comp_args", 2 );
					ModelTestUtils.assertParameterNames( methodcomp_args17, new String[] {"a", "('b', 'c')"} );
				}
			}
			//Function test:test_argument_order
			{
			IMethod methodtest_argument_order18;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_argument_order18 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_argument_order", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argument_order18, new String[] {"self"} );
			}
			//Function test:test_float_literals
			{
			IMethod methodtest_float_literals19;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_float_literals19 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_float_literals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float_literals19, new String[] {"self"} );
			}
			//Function test:test_indentation
			{
			IMethod methodtest_indentation20;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_indentation20 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_indentation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_indentation20, new String[] {"self"} );
			}
			//Function test:test_literals_with_leading_zeroes
			{
			IMethod methodtest_literals_with_leading_zeroes21;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_literals_with_leading_zeroes21 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_literals_with_leading_zeroes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_literals_with_leading_zeroes21, new String[] {"self"} );
			}
			//Function test:test_unary_minus
			{
			IMethod methodtest_unary_minus22;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_unary_minus22 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_unary_minus", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unary_minus22, new String[] {"self"} );
			}
			//Function test:test_sequence_unpacking_error
			{
			IMethod methodtest_sequence_unpacking_error23;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_sequence_unpacking_error23 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_sequence_unpacking_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sequence_unpacking_error23, new String[] {"self"} );
			}
			//Function test:test_none_assignment
			{
			IMethod methodtest_none_assignment24;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_none_assignment24 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_none_assignment", 1 );
				ModelTestUtils.assertParameterNames( methodtest_none_assignment24, new String[] {"self"} );
			}
			//Function test:test_import
			{
			IMethod methodtest_import25;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_import25 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_import", 1 );
				ModelTestUtils.assertParameterNames( methodtest_import25, new String[] {"self"} );
			}
			//Function test:test_for_distinct_code_objects
			{
			IMethod methodtest_for_distinct_code_objects26;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_for_distinct_code_objects26 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_for_distinct_code_objects", 1 );
				ModelTestUtils.assertParameterNames( methodtest_for_distinct_code_objects26, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf27;
					IModelElement[] methodtest_for_distinct_code_objects26Childs = methodtest_for_distinct_code_objects26.getChildren();
					methodf27 = ModelTestUtils.getAssertMethod( methodtest_for_distinct_code_objects26Childs, "f", 0 );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen133( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pkgimport.py"));

		assertNotNull("Module test_pkgimport.py not found", module);
		assertEquals("test_pkgimport.py", module.getElementName());
		
		//Class test
		{
		IType classTestImport0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestImport0 = ModelTestUtils.getAssertClass( moduleChilds, "TestImport" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "args", "kw"} );
			}
			//Function test:remove_modules
			{
			IMethod methodremove_modules2;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodremove_modules2 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "remove_modules", 1 );
				ModelTestUtils.assertParameterNames( methodremove_modules2, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp3;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodsetUp3 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp3, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown4;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodtearDown4 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown4, new String[] {"self"} );
			}
			//Function test:rewrite_file
			{
			IMethod methodrewrite_file5;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodrewrite_file5 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "rewrite_file", 2 );
				ModelTestUtils.assertParameterNames( methodrewrite_file5, new String[] {"self", "contents"} );
			}
			//Function test:test_package_import__semantics
			{
			IMethod methodtest_package_import__semantics6;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodtest_package_import__semantics6 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "test_package_import__semantics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_package_import__semantics6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen134( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_gdbm.py"));

		assertNotNull("Module test_gdbm.py not found", module);
		assertEquals("test_gdbm.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "filename");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}

	}
	public void REM_testModelGen135( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_random.py"));

		assertNotNull("Module test_random.py not found", module);
		assertEquals("test_random.py", module.getElementName());
		
		//Class test
		{
		IType classTestBasicOps0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBasicOps0 = ModelTestUtils.getAssertClass( moduleChilds, "TestBasicOps" );
			//Function test:randomlist
			{
			IMethod methodrandomlist1;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodrandomlist1 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "randomlist", 2 );
				ModelTestUtils.assertParameterNames( methodrandomlist1, new String[] {"self", "n"} );
			}
			//Function test:test_autoseed
			{
			IMethod methodtest_autoseed2;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_autoseed2 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_autoseed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_autoseed2, new String[] {"self"} );
			}
			//Function test:test_saverestore
			{
			IMethod methodtest_saverestore3;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_saverestore3 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_saverestore", 1 );
				ModelTestUtils.assertParameterNames( methodtest_saverestore3, new String[] {"self"} );
			}
			//Function test:test_seedargs
			{
			IMethod methodtest_seedargs4;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_seedargs4 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_seedargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seedargs4, new String[] {"self"} );
			}
			//Function test:test_jumpahead
			{
			IMethod methodtest_jumpahead5;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_jumpahead5 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_jumpahead", 1 );
				ModelTestUtils.assertParameterNames( methodtest_jumpahead5, new String[] {"self"} );
			}
			//Function test:test_sample
			{
			IMethod methodtest_sample6;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_sample6 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_sample", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sample6, new String[] {"self"} );
			}
			//Function test:test_sample_distribution
			{
			IMethod methodtest_sample_distribution7;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_sample_distribution7 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_sample_distribution", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sample_distribution7, new String[] {"self"} );
				//Function test:factorial
				{
				IMethod methodfactorial8;
					IModelElement[] methodtest_sample_distribution7Childs = methodtest_sample_distribution7.getChildren();
					methodfactorial8 = ModelTestUtils.getAssertMethod( methodtest_sample_distribution7Childs, "factorial", 1 );
					ModelTestUtils.assertParameterNames( methodfactorial8, new String[] {"n"} );
				}
			}
			//Function test:test_sample_inputs
			{
			IMethod methodtest_sample_inputs9;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_sample_inputs9 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_sample_inputs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sample_inputs9, new String[] {"self"} );
			}
			//Function test:test_gauss
			{
			IMethod methodtest_gauss10;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_gauss10 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_gauss", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gauss10, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling11;
				IModelElement[] classTestBasicOps0Childs = classTestBasicOps0.getChildren();
				methodtest_pickling11 = ModelTestUtils.getAssertMethod( classTestBasicOps0Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWichmannHill_TestBasicOps12;
			IModelElement[] moduleChilds = module.getChildren();
			classWichmannHill_TestBasicOps12 = ModelTestUtils.getAssertClass( moduleChilds, "WichmannHill_TestBasicOps" );
			{
				IModelElement[] classWichmannHill_TestBasicOps12Childs = classWichmannHill_TestBasicOps12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWichmannHill_TestBasicOps12Childs, "gen");
			}
			//Function test:test_setstate_first_arg
			{
			IMethod methodtest_setstate_first_arg13;
				IModelElement[] classWichmannHill_TestBasicOps12Childs = classWichmannHill_TestBasicOps12.getChildren();
				methodtest_setstate_first_arg13 = ModelTestUtils.getAssertMethod( classWichmannHill_TestBasicOps12Childs, "test_setstate_first_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setstate_first_arg13, new String[] {"self"} );
			}
			//Function test:test_strong_jumpahead
			{
			IMethod methodtest_strong_jumpahead14;
				IModelElement[] classWichmannHill_TestBasicOps12Childs = classWichmannHill_TestBasicOps12.getChildren();
				methodtest_strong_jumpahead14 = ModelTestUtils.getAssertMethod( classWichmannHill_TestBasicOps12Childs, "test_strong_jumpahead", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strong_jumpahead14, new String[] {"self"} );
			}
			//Function test:test_gauss_with_whseed
			{
			IMethod methodtest_gauss_with_whseed15;
				IModelElement[] classWichmannHill_TestBasicOps12Childs = classWichmannHill_TestBasicOps12.getChildren();
				methodtest_gauss_with_whseed15 = ModelTestUtils.getAssertMethod( classWichmannHill_TestBasicOps12Childs, "test_gauss_with_whseed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gauss_with_whseed15, new String[] {"self"} );
			}
			//Function test:test_bigrand
			{
			IMethod methodtest_bigrand16;
				IModelElement[] classWichmannHill_TestBasicOps12Childs = classWichmannHill_TestBasicOps12.getChildren();
				methodtest_bigrand16 = ModelTestUtils.getAssertMethod( classWichmannHill_TestBasicOps12Childs, "test_bigrand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigrand16, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSystemRandom_TestBasicOps17;
			IModelElement[] moduleChilds = module.getChildren();
			classSystemRandom_TestBasicOps17 = ModelTestUtils.getAssertClass( moduleChilds, "SystemRandom_TestBasicOps" );
			{
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSystemRandom_TestBasicOps17Childs, "gen");
			}
			//Function test:test_autoseed
			{
			IMethod methodtest_autoseed18;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_autoseed18 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_autoseed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_autoseed18, new String[] {"self"} );
			}
			//Function test:test_saverestore
			{
			IMethod methodtest_saverestore19;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_saverestore19 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_saverestore", 1 );
				ModelTestUtils.assertParameterNames( methodtest_saverestore19, new String[] {"self"} );
			}
			//Function test:test_seedargs
			{
			IMethod methodtest_seedargs20;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_seedargs20 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_seedargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seedargs20, new String[] {"self"} );
			}
			//Function test:test_jumpahead
			{
			IMethod methodtest_jumpahead21;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_jumpahead21 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_jumpahead", 1 );
				ModelTestUtils.assertParameterNames( methodtest_jumpahead21, new String[] {"self"} );
			}
			//Function test:test_gauss
			{
			IMethod methodtest_gauss22;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_gauss22 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_gauss", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gauss22, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling23;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_pickling23 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling23, new String[] {"self"} );
			}
			//Function test:test_53_bits_per_float
			{
			IMethod methodtest_53_bits_per_float24;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_53_bits_per_float24 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_53_bits_per_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_53_bits_per_float24, new String[] {"self"} );
			}
			//Function test:test_bigrand
			{
			IMethod methodtest_bigrand25;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_bigrand25 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_bigrand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigrand25, new String[] {"self"} );
			}
			//Function test:test_bigrand_ranges
			{
			IMethod methodtest_bigrand_ranges26;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_bigrand_ranges26 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_bigrand_ranges", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigrand_ranges26, new String[] {"self"} );
			}
			//Function test:test_rangelimits
			{
			IMethod methodtest_rangelimits27;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_rangelimits27 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_rangelimits", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rangelimits27, new String[] {"self"} );
			}
			//Function test:test_genrandbits
			{
			IMethod methodtest_genrandbits28;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_genrandbits28 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_genrandbits", 1 );
				ModelTestUtils.assertParameterNames( methodtest_genrandbits28, new String[] {"self"} );
			}
			//Function test:test_randbelow_logic
			{
			IMethod methodtest_randbelow_logic29;
				IModelElement[] classSystemRandom_TestBasicOps17Childs = classSystemRandom_TestBasicOps17.getChildren();
				methodtest_randbelow_logic29 = ModelTestUtils.getAssertMethod( classSystemRandom_TestBasicOps17Childs, "test_randbelow_logic", 3 );
				ModelTestUtils.assertParameterNames( methodtest_randbelow_logic29, new String[] {"self", "_log", "int"} );
			}
		}
		//Class test
		{
		IType classMersenneTwister_TestBasicOps30;
			IModelElement[] moduleChilds = module.getChildren();
			classMersenneTwister_TestBasicOps30 = ModelTestUtils.getAssertClass( moduleChilds, "MersenneTwister_TestBasicOps" );
			{
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMersenneTwister_TestBasicOps30Childs, "gen");
			}
			//Function test:test_setstate_first_arg
			{
			IMethod methodtest_setstate_first_arg31;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_setstate_first_arg31 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_setstate_first_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setstate_first_arg31, new String[] {"self"} );
			}
			//Function test:test_setstate_middle_arg
			{
			IMethod methodtest_setstate_middle_arg32;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_setstate_middle_arg32 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_setstate_middle_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setstate_middle_arg32, new String[] {"self"} );
			}
			//Function test:test_referenceImplementation
			{
			IMethod methodtest_referenceImplementation33;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_referenceImplementation33 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_referenceImplementation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_referenceImplementation33, new String[] {"self"} );
			}
			//Function test:test_strong_reference_implementation
			{
			IMethod methodtest_strong_reference_implementation34;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_strong_reference_implementation34 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_strong_reference_implementation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strong_reference_implementation34, new String[] {"self"} );
			}
			//Function test:test_long_seed
			{
			IMethod methodtest_long_seed35;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_long_seed35 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_long_seed", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long_seed35, new String[] {"self"} );
			}
			//Function test:test_53_bits_per_float
			{
			IMethod methodtest_53_bits_per_float36;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_53_bits_per_float36 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_53_bits_per_float", 1 );
				ModelTestUtils.assertParameterNames( methodtest_53_bits_per_float36, new String[] {"self"} );
			}
			//Function test:test_bigrand
			{
			IMethod methodtest_bigrand37;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_bigrand37 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_bigrand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigrand37, new String[] {"self"} );
			}
			//Function test:test_bigrand_ranges
			{
			IMethod methodtest_bigrand_ranges38;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_bigrand_ranges38 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_bigrand_ranges", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigrand_ranges38, new String[] {"self"} );
			}
			//Function test:test_rangelimits
			{
			IMethod methodtest_rangelimits39;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_rangelimits39 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_rangelimits", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rangelimits39, new String[] {"self"} );
			}
			//Function test:test_genrandbits
			{
			IMethod methodtest_genrandbits40;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_genrandbits40 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_genrandbits", 1 );
				ModelTestUtils.assertParameterNames( methodtest_genrandbits40, new String[] {"self"} );
			}
			//Function test:test_randbelow_logic
			{
			IMethod methodtest_randbelow_logic41;
				IModelElement[] classMersenneTwister_TestBasicOps30Childs = classMersenneTwister_TestBasicOps30.getChildren();
				methodtest_randbelow_logic41 = ModelTestUtils.getAssertMethod( classMersenneTwister_TestBasicOps30Childs, "test_randbelow_logic", 3 );
				ModelTestUtils.assertParameterNames( methodtest_randbelow_logic41, new String[] {"self", "_log", "int"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_gammacoeff");
		}
		//Function test:gamma
		{
		IMethod methodgamma42;
			IModelElement[] moduleChilds = module.getChildren();
			methodgamma42 = ModelTestUtils.getAssertMethod( moduleChilds, "gamma", 3 );
			ModelTestUtils.assertParameterNames( methodgamma42, new String[] {"z", "cof", "g"} );
		}
		//Class test
		{
		IType classTestDistributions43;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDistributions43 = ModelTestUtils.getAssertClass( moduleChilds, "TestDistributions" );
			//Function test:test_zeroinputs
			{
			IMethod methodtest_zeroinputs44;
				IModelElement[] classTestDistributions43Childs = classTestDistributions43.getChildren();
				methodtest_zeroinputs44 = ModelTestUtils.getAssertMethod( classTestDistributions43Childs, "test_zeroinputs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zeroinputs44, new String[] {"self"} );
			}
			//Function test:test_avg_std
			{
			IMethod methodtest_avg_std45;
				IModelElement[] classTestDistributions43Childs = classTestDistributions43.getChildren();
				methodtest_avg_std45 = ModelTestUtils.getAssertMethod( classTestDistributions43Childs, "test_avg_std", 1 );
				ModelTestUtils.assertParameterNames( methodtest_avg_std45, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestModule46;
			IModelElement[] moduleChilds = module.getChildren();
			classTestModule46 = ModelTestUtils.getAssertClass( moduleChilds, "TestModule" );
			//Function test:testMagicConstants
			{
			IMethod methodtestMagicConstants47;
				IModelElement[] classTestModule46Childs = classTestModule46.getChildren();
				methodtestMagicConstants47 = ModelTestUtils.getAssertMethod( classTestModule46Childs, "testMagicConstants", 1 );
				ModelTestUtils.assertParameterNames( methodtestMagicConstants47, new String[] {"self"} );
			}
			//Function test:test__all__
			{
			IMethod methodtest__all__48;
				IModelElement[] classTestModule46Childs = classTestModule46.getChildren();
				methodtest__all__48 = ModelTestUtils.getAssertMethod( classTestModule46Childs, "test__all__", 1 );
				ModelTestUtils.assertParameterNames( methodtest__all__48, new String[] {"self"} );
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
	public void testModelGen136( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test___all__.py"));

		assertNotNull("Module test___all__.py not found", module);
		assertEquals("test___all__.py", module.getElementName());
		
		//Class test
		{
		IType classAllTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classAllTest0 = ModelTestUtils.getAssertClass( moduleChilds, "AllTest" );
			//Function test:check_all
			{
			IMethod methodcheck_all1;
				IModelElement[] classAllTest0Childs = classAllTest0.getChildren();
				methodcheck_all1 = ModelTestUtils.getAssertMethod( classAllTest0Childs, "check_all", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_all1, new String[] {"self", "modname"} );
			}
			//Function test:test_all
			{
			IMethod methodtest_all2;
				IModelElement[] classAllTest0Childs = classAllTest0.getChildren();
				methodtest_all2 = ModelTestUtils.getAssertMethod( classAllTest0Childs, "test_all", 1 );
				ModelTestUtils.assertParameterNames( methodtest_all2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen137( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_tokenize.py"));

		assertNotNull("Module test_tokenize.py not found", module);
		assertEquals("test_tokenize.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}

	}
	public void testModelGen138( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_mhlib.py"));

		assertNotNull("Module test_mhlib.py not found", module);
		assertEquals("test_mhlib.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_mhroot");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_mhpath");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "_mhprofile");
		}
		//Function test:normF
		{
		IMethod methodnormF0;
			IModelElement[] moduleChilds = module.getChildren();
			methodnormF0 = ModelTestUtils.getAssertMethod( moduleChilds, "normF", 1 );
			ModelTestUtils.assertParameterNames( methodnormF0, new String[] {"f"} );
		}
		//Function test:writeFile
		{
		IMethod methodwriteFile1;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteFile1 = ModelTestUtils.getAssertMethod( moduleChilds, "writeFile", 2 );
			ModelTestUtils.assertParameterNames( methodwriteFile1, new String[] {"fname", "contents"} );
		}
		//Function test:readFile
		{
		IMethod methodreadFile2;
			IModelElement[] moduleChilds = module.getChildren();
			methodreadFile2 = ModelTestUtils.getAssertMethod( moduleChilds, "readFile", 1 );
			ModelTestUtils.assertParameterNames( methodreadFile2, new String[] {"fname"} );
		}
		//Function test:writeProfile
		{
		IMethod methodwriteProfile3;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteProfile3 = ModelTestUtils.getAssertMethod( moduleChilds, "writeProfile", 1 );
			ModelTestUtils.assertParameterNames( methodwriteProfile3, new String[] {"dict"} );
		}
		//Function test:writeContext
		{
		IMethod methodwriteContext4;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteContext4 = ModelTestUtils.getAssertMethod( moduleChilds, "writeContext", 1 );
			ModelTestUtils.assertParameterNames( methodwriteContext4, new String[] {"folder"} );
		}
		//Function test:writeCurMessage
		{
		IMethod methodwriteCurMessage5;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteCurMessage5 = ModelTestUtils.getAssertMethod( moduleChilds, "writeCurMessage", 2 );
			ModelTestUtils.assertParameterNames( methodwriteCurMessage5, new String[] {"folder", "cur"} );
		}
		//Function test:writeMessage
		{
		IMethod methodwriteMessage6;
			IModelElement[] moduleChilds = module.getChildren();
			methodwriteMessage6 = ModelTestUtils.getAssertMethod( moduleChilds, "writeMessage", 4 );
			ModelTestUtils.assertParameterNames( methodwriteMessage6, new String[] {"folder", "n", "headers", "body"} );
		}
		//Function test:getMH
		{
		IMethod methodgetMH7;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetMH7 = ModelTestUtils.getAssertMethod( moduleChilds, "getMH", 0 );
		}
		//Function test:sortLines
		{
		IMethod methodsortLines8;
			IModelElement[] moduleChilds = module.getChildren();
			methodsortLines8 = ModelTestUtils.getAssertMethod( moduleChilds, "sortLines", 1 );
			ModelTestUtils.assertParameterNames( methodsortLines8, new String[] {"s"} );
		}
		//Function test:mkdirs
		{
		IMethod methodmkdirs9;
			IModelElement[] moduleChilds = module.getChildren();
			methodmkdirs9 = ModelTestUtils.getAssertMethod( moduleChilds, "mkdirs", 1 );
			ModelTestUtils.assertParameterNames( methodmkdirs9, new String[] {"fname"} );
		}
		//Function test:deltree
		{
		IMethod methoddeltree10;
			IModelElement[] moduleChilds = module.getChildren();
			methoddeltree10 = ModelTestUtils.getAssertMethod( moduleChilds, "deltree", 1 );
			ModelTestUtils.assertParameterNames( methoddeltree10, new String[] {"fname"} );
		}
		//Class test
		{
		IType classMhlibTests11;
			IModelElement[] moduleChilds = module.getChildren();
			classMhlibTests11 = ModelTestUtils.getAssertClass( moduleChilds, "MhlibTests" );
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
				//Function test:deep
				{
				IMethod methoddeep13;
					IModelElement[] methodsetUp12Childs = methodsetUp12.getChildren();
					methoddeep13 = ModelTestUtils.getAssertMethod( methodsetUp12Childs, "deep", 2 );
					ModelTestUtils.assertParameterNames( methoddeep13, new String[] {"folder", "n"} );
				}
			}
			//Function test:tearDown
			{
			IMethod methodtearDown14;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodtearDown14 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown14, new String[] {"self"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic15;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodtest_basic15 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic15, new String[] {"self"} );
			}
			//Function test:test_listfolders
			{
			IMethod methodtest_listfolders16;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodtest_listfolders16 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "test_listfolders", 1 );
				ModelTestUtils.assertParameterNames( methodtest_listfolders16, new String[] {"self"} );
			}
			//Function test:test_sequence
			{
			IMethod methodtest_sequence17;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodtest_sequence17 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "test_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sequence17, new String[] {"self"} );
				//Function test:seqeq
				{
				IMethod methodseqeq18;
					IModelElement[] methodtest_sequence17Childs = methodtest_sequence17.getChildren();
					methodseqeq18 = ModelTestUtils.getAssertMethod( methodtest_sequence17Childs, "seqeq", 2 );
					ModelTestUtils.assertParameterNames( methodseqeq18, new String[] {"seq", "val"} );
				}
			}
			//Function test:test_modify
			{
			IMethod methodtest_modify19;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodtest_modify19 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "test_modify", 1 );
				ModelTestUtils.assertParameterNames( methodtest_modify19, new String[] {"self"} );
				//Function test:create
				{
				IMethod methodcreate20;
					IModelElement[] methodtest_modify19Childs = methodtest_modify19.getChildren();
					methodcreate20 = ModelTestUtils.getAssertMethod( methodtest_modify19Childs, "create", 1 );
					ModelTestUtils.assertParameterNames( methodcreate20, new String[] {"n"} );
				}
			}
			//Function test:test_read
			{
			IMethod methodtest_read21;
				IModelElement[] classMhlibTests11Childs = classMhlibTests11.getChildren();
				methodtest_read21 = ModelTestUtils.getAssertMethod( classMhlibTests11Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read21, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen139( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pickletools.py"));

		assertNotNull("Module test_pickletools.py not found", module);
		assertEquals("test_pickletools.py", module.getElementName());
		

	}
	public void testModelGen140( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_posix.py"));

		assertNotNull("Module test_posix.py not found", module);
		assertEquals("test_posix.py", module.getElementName());
		
		//Class test
		{
		IType classPosixTester0;
			IModelElement[] moduleChilds = module.getChildren();
			classPosixTester0 = ModelTestUtils.getAssertClass( moduleChilds, "PosixTester" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:testNoArgFunctions
			{
			IMethod methodtestNoArgFunctions3;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtestNoArgFunctions3 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "testNoArgFunctions", 1 );
				ModelTestUtils.assertParameterNames( methodtestNoArgFunctions3, new String[] {"self"} );
			}
			//Function test:test_statvfs
			{
			IMethod methodtest_statvfs4;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_statvfs4 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_statvfs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_statvfs4, new String[] {"self"} );
			}
			//Function test:test_fstatvfs
			{
			IMethod methodtest_fstatvfs5;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_fstatvfs5 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_fstatvfs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fstatvfs5, new String[] {"self"} );
			}
			//Function test:test_ftruncate
			{
			IMethod methodtest_ftruncate6;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_ftruncate6 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_ftruncate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ftruncate6, new String[] {"self"} );
			}
			//Function test:test_dup
			{
			IMethod methodtest_dup7;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_dup7 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_dup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dup7, new String[] {"self"} );
			}
			//Function test:test_dup2
			{
			IMethod methodtest_dup28;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_dup28 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_dup2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dup28, new String[] {"self"} );
			}
			//Function test:fdopen_helper
			{
			IMethod methodfdopen_helper9;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodfdopen_helper9 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "fdopen_helper", 2 );
				ModelTestUtils.assertParameterNames( methodfdopen_helper9, new String[] {"self", "args"} );
			}
			//Function test:test_fdopen
			{
			IMethod methodtest_fdopen10;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_fdopen10 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_fdopen", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fdopen10, new String[] {"self"} );
			}
			//Function test:test_fstat
			{
			IMethod methodtest_fstat11;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_fstat11 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_fstat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fstat11, new String[] {"self"} );
			}
			//Function test:test_stat
			{
			IMethod methodtest_stat12;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_stat12 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_stat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stat12, new String[] {"self"} );
			}
			//Function test:test_chdir
			{
			IMethod methodtest_chdir13;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_chdir13 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_chdir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_chdir13, new String[] {"self"} );
			}
			//Function test:test_lsdir
			{
			IMethod methodtest_lsdir14;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_lsdir14 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_lsdir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lsdir14, new String[] {"self"} );
			}
			//Function test:test_access
			{
			IMethod methodtest_access15;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_access15 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_access", 1 );
				ModelTestUtils.assertParameterNames( methodtest_access15, new String[] {"self"} );
			}
			//Function test:test_umask
			{
			IMethod methodtest_umask16;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_umask16 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_umask", 1 );
				ModelTestUtils.assertParameterNames( methodtest_umask16, new String[] {"self"} );
			}
			//Function test:test_strerror
			{
			IMethod methodtest_strerror17;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_strerror17 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_strerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strerror17, new String[] {"self"} );
			}
			//Function test:test_pipe
			{
			IMethod methodtest_pipe18;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_pipe18 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_pipe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pipe18, new String[] {"self"} );
			}
			//Function test:test_tempnam
			{
			IMethod methodtest_tempnam19;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_tempnam19 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_tempnam", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tempnam19, new String[] {"self"} );
			}
			//Function test:test_tmpfile
			{
			IMethod methodtest_tmpfile20;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_tmpfile20 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_tmpfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tmpfile20, new String[] {"self"} );
			}
			//Function test:test_utime
			{
			IMethod methodtest_utime21;
				IModelElement[] classPosixTester0Childs = classPosixTester0.getChildren();
				methodtest_utime21 = ModelTestUtils.getAssertMethod( classPosixTester0Childs, "test_utime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utime21, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen141( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_marshal.py"));

		assertNotNull("Module test_marshal.py not found", module);
		assertEquals("test_marshal.py", module.getElementName());
		
		//Class test
		{
		IType classIntTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classIntTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "IntTestCase" );
			//Function test:test_ints
			{
			IMethod methodtest_ints1;
				IModelElement[] classIntTestCase0Childs = classIntTestCase0.getChildren();
				methodtest_ints1 = ModelTestUtils.getAssertMethod( classIntTestCase0Childs, "test_ints", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ints1, new String[] {"self"} );
			}
			//Function test:test_int64
			{
			IMethod methodtest_int642;
				IModelElement[] classIntTestCase0Childs = classIntTestCase0.getChildren();
				methodtest_int642 = ModelTestUtils.getAssertMethod( classIntTestCase0Childs, "test_int64", 1 );
				ModelTestUtils.assertParameterNames( methodtest_int642, new String[] {"self"} );
				//Function test:to_little_endian_string
				{
				IMethod methodto_little_endian_string3;
					IModelElement[] methodtest_int642Childs = methodtest_int642.getChildren();
					methodto_little_endian_string3 = ModelTestUtils.getAssertMethod( methodtest_int642Childs, "to_little_endian_string", 2 );
					ModelTestUtils.assertParameterNames( methodto_little_endian_string3, new String[] {"value", "nbytes"} );
				}
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool4;
				IModelElement[] classIntTestCase0Childs = classIntTestCase0.getChildren();
				methodtest_bool4 = ModelTestUtils.getAssertMethod( classIntTestCase0Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classFloatTestCase5;
			IModelElement[] moduleChilds = module.getChildren();
			classFloatTestCase5 = ModelTestUtils.getAssertClass( moduleChilds, "FloatTestCase" );
			//Function test:test_floats
			{
			IMethod methodtest_floats6;
				IModelElement[] classFloatTestCase5Childs = classFloatTestCase5.getChildren();
				methodtest_floats6 = ModelTestUtils.getAssertMethod( classFloatTestCase5Childs, "test_floats", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floats6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStringTestCase7;
			IModelElement[] moduleChilds = module.getChildren();
			classStringTestCase7 = ModelTestUtils.getAssertClass( moduleChilds, "StringTestCase" );
			//Function test:test_unicode
			{
			IMethod methodtest_unicode8;
				IModelElement[] classStringTestCase7Childs = classStringTestCase7.getChildren();
				methodtest_unicode8 = ModelTestUtils.getAssertMethod( classStringTestCase7Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode8, new String[] {"self"} );
			}
			//Function test:test_string
			{
			IMethod methodtest_string9;
				IModelElement[] classStringTestCase7Childs = classStringTestCase7.getChildren();
				methodtest_string9 = ModelTestUtils.getAssertMethod( classStringTestCase7Childs, "test_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_string9, new String[] {"self"} );
			}
			//Function test:test_buffer
			{
			IMethod methodtest_buffer10;
				IModelElement[] classStringTestCase7Childs = classStringTestCase7.getChildren();
				methodtest_buffer10 = ModelTestUtils.getAssertMethod( classStringTestCase7Childs, "test_buffer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_buffer10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classExceptionTestCase11;
			IModelElement[] moduleChilds = module.getChildren();
			classExceptionTestCase11 = ModelTestUtils.getAssertClass( moduleChilds, "ExceptionTestCase" );
			//Function test:test_exceptions
			{
			IMethod methodtest_exceptions12;
				IModelElement[] classExceptionTestCase11Childs = classExceptionTestCase11.getChildren();
				methodtest_exceptions12 = ModelTestUtils.getAssertMethod( classExceptionTestCase11Childs, "test_exceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exceptions12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classCodeTestCase13;
			IModelElement[] moduleChilds = module.getChildren();
			classCodeTestCase13 = ModelTestUtils.getAssertClass( moduleChilds, "CodeTestCase" );
			//Function test:test_code
			{
			IMethod methodtest_code14;
				IModelElement[] classCodeTestCase13Childs = classCodeTestCase13.getChildren();
				methodtest_code14 = ModelTestUtils.getAssertMethod( classCodeTestCase13Childs, "test_code", 1 );
				ModelTestUtils.assertParameterNames( methodtest_code14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classContainerTestCase15;
			IModelElement[] moduleChilds = module.getChildren();
			classContainerTestCase15 = ModelTestUtils.getAssertClass( moduleChilds, "ContainerTestCase" );
			{
				IModelElement[] classContainerTestCase15Childs = classContainerTestCase15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classContainerTestCase15Childs, "d");
			}
			//Function test:test_dict
			{
			IMethod methodtest_dict16;
				IModelElement[] classContainerTestCase15Childs = classContainerTestCase15.getChildren();
				methodtest_dict16 = ModelTestUtils.getAssertMethod( classContainerTestCase15Childs, "test_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dict16, new String[] {"self"} );
			}
			//Function test:test_list
			{
			IMethod methodtest_list17;
				IModelElement[] classContainerTestCase15Childs = classContainerTestCase15.getChildren();
				methodtest_list17 = ModelTestUtils.getAssertMethod( classContainerTestCase15Childs, "test_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_list17, new String[] {"self"} );
			}
			//Function test:test_tuple
			{
			IMethod methodtest_tuple18;
				IModelElement[] classContainerTestCase15Childs = classContainerTestCase15.getChildren();
				methodtest_tuple18 = ModelTestUtils.getAssertMethod( classContainerTestCase15Childs, "test_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tuple18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classBugsTestCase19;
			IModelElement[] moduleChilds = module.getChildren();
			classBugsTestCase19 = ModelTestUtils.getAssertClass( moduleChilds, "BugsTestCase" );
			//Function test:test_bug_5888452
			{
			IMethod methodtest_bug_588845220;
				IModelElement[] classBugsTestCase19Childs = classBugsTestCase19.getChildren();
				methodtest_bug_588845220 = ModelTestUtils.getAssertMethod( classBugsTestCase19Childs, "test_bug_5888452", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_588845220, new String[] {"self"} );
			}
			//Function test:test_patch_873224
			{
			IMethod methodtest_patch_87322421;
				IModelElement[] classBugsTestCase19Childs = classBugsTestCase19.getChildren();
				methodtest_patch_87322421 = ModelTestUtils.getAssertMethod( classBugsTestCase19Childs, "test_patch_873224", 1 );
				ModelTestUtils.assertParameterNames( methodtest_patch_87322421, new String[] {"self"} );
			}
			//Function test:test_version_argument
			{
			IMethod methodtest_version_argument22;
				IModelElement[] classBugsTestCase19Childs = classBugsTestCase19.getChildren();
				methodtest_version_argument22 = ModelTestUtils.getAssertMethod( classBugsTestCase19Childs, "test_version_argument", 1 );
				ModelTestUtils.assertParameterNames( methodtest_version_argument22, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen142( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_scope.py"));

		assertNotNull("Module test_scope.py not found", module);
		assertEquals("test_scope.py", module.getElementName());
		
		//Function test:make_adder
		{
		IMethod methodmake_adder0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_adder0 = ModelTestUtils.getAssertMethod( moduleChilds, "make_adder", 1 );
			ModelTestUtils.assertParameterNames( methodmake_adder0, new String[] {"x"} );
			//Function test:adder
			{
			IMethod methodadder1;
				IModelElement[] methodmake_adder0Childs = methodmake_adder0.getChildren();
				methodadder1 = ModelTestUtils.getAssertMethod( methodmake_adder0Childs, "adder", 1 );
				ModelTestUtils.assertParameterNames( methodadder1, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		//Function test:make_adder2
		{
		IMethod methodmake_adder22;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_adder22 = ModelTestUtils.getAssertMethod( moduleChilds, "make_adder2", 1 );
			ModelTestUtils.assertParameterNames( methodmake_adder22, new String[] {"x"} );
			//Function test:extra
			{
			IMethod methodextra3;
				IModelElement[] methodmake_adder22Childs = methodmake_adder22.getChildren();
				methodextra3 = ModelTestUtils.getAssertMethod( methodmake_adder22Childs, "extra", 0 );
				//Function test:adder
				{
				IMethod methodadder4;
					IModelElement[] methodextra3Childs = methodextra3.getChildren();
					methodadder4 = ModelTestUtils.getAssertMethod( methodextra3Childs, "adder", 1 );
					ModelTestUtils.assertParameterNames( methodadder4, new String[] {"y"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		//Function test:make_adder3
		{
		IMethod methodmake_adder35;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_adder35 = ModelTestUtils.getAssertMethod( moduleChilds, "make_adder3", 1 );
			ModelTestUtils.assertParameterNames( methodmake_adder35, new String[] {"x"} );
			//Function test:adder
			{
			IMethod methodadder6;
				IModelElement[] methodmake_adder35Childs = methodmake_adder35.getChildren();
				methodadder6 = ModelTestUtils.getAssertMethod( methodmake_adder35Childs, "adder", 1 );
				ModelTestUtils.assertParameterNames( methodadder6, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		//Function test:make_adder4
		{
		IMethod methodmake_adder47;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_adder47 = ModelTestUtils.getAssertMethod( moduleChilds, "make_adder4", 0 );
			//Function test:nest
			{
			IMethod methodnest8;
				IModelElement[] methodmake_adder47Childs = methodmake_adder47.getChildren();
				methodnest8 = ModelTestUtils.getAssertMethod( methodmake_adder47Childs, "nest", 0 );
				//Function test:nest
				{
				IMethod methodnest9;
					IModelElement[] methodnest8Childs = methodnest8.getChildren();
					methodnest9 = ModelTestUtils.getAssertMethod( methodnest8Childs, "nest", 0 );
					//Function test:adder
					{
					IMethod methodadder10;
						IModelElement[] methodnest9Childs = methodnest9.getChildren();
						methodadder10 = ModelTestUtils.getAssertMethod( methodnest9Childs, "adder", 1 );
						ModelTestUtils.assertParameterNames( methodadder10, new String[] {"y"} );
					}
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "global_x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "adder");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "global_x");
		}
		//Function test:make_adder5
		{
		IMethod methodmake_adder511;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_adder511 = ModelTestUtils.getAssertMethod( moduleChilds, "make_adder5", 1 );
			ModelTestUtils.assertParameterNames( methodmake_adder511, new String[] {"x"} );
			//Class test
			{
			IType classAdder12;
				IModelElement[] methodmake_adder511Childs = methodmake_adder511.getChildren();
				classAdder12 = ModelTestUtils.getAssertClass( methodmake_adder511Childs, "Adder" );
				//Function test:__call__
				{
				IMethod method__call__13;
					IModelElement[] classAdder12Childs = classAdder12.getChildren();
					method__call__13 = ModelTestUtils.getAssertMethod( classAdder12Childs, "__call__", 2 );
					ModelTestUtils.assertParameterNames( method__call__13, new String[] {"self", "y"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		//Function test:make_adder6
		{
		IMethod methodmake_adder614;
			IModelElement[] moduleChilds = module.getChildren();
			methodmake_adder614 = ModelTestUtils.getAssertMethod( moduleChilds, "make_adder6", 1 );
			ModelTestUtils.assertParameterNames( methodmake_adder614, new String[] {"x"} );
			//Function test:adder
			{
			IMethod methodadder15;
				IModelElement[] methodmake_adder614Childs = methodmake_adder614.getChildren();
				methodadder15 = ModelTestUtils.getAssertMethod( methodmake_adder614Childs, "adder", 1 );
				ModelTestUtils.assertParameterNames( methodadder15, new String[] {"y"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		//Function test:f
		{
		IMethod methodf16;
			IModelElement[] moduleChilds = module.getChildren();
			methodf16 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf16, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg17;
				IModelElement[] methodf16Childs = methodf16.getChildren();
				methodg17 = ModelTestUtils.getAssertMethod( methodf16Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg17, new String[] {"y"} );
				//Function test:h
				{
				IMethod methodh18;
					IModelElement[] methodg17Childs = methodg17.getChildren();
					methodh18 = ModelTestUtils.getAssertMethod( methodg17Childs, "h", 1 );
					ModelTestUtils.assertParameterNames( methodh18, new String[] {"z"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_func");
		}
		//Function test:identity
		{
		IMethod methodidentity19;
			IModelElement[] moduleChilds = module.getChildren();
			methodidentity19 = ModelTestUtils.getAssertMethod( moduleChilds, "identity", 1 );
			ModelTestUtils.assertParameterNames( methodidentity19, new String[] {"x"} );
		}
		//Function test:f
		{
		IMethod methodf20;
			IModelElement[] moduleChilds = module.getChildren();
			methodf20 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 3 );
			ModelTestUtils.assertParameterNames( methodf20, new String[] {"x", "y", "z"} );
			//Function test:g
			{
			IMethod methodg21;
				IModelElement[] methodf20Childs = methodf20.getChildren();
				methodg21 = ModelTestUtils.getAssertMethod( methodf20Childs, "g", 3 );
				ModelTestUtils.assertParameterNames( methodg21, new String[] {"a", "b", "c"} );
				//Function test:h
				{
				IMethod methodh22;
					IModelElement[] methodg21Childs = methodg21.getChildren();
					methodh22 = ModelTestUtils.getAssertMethod( methodg21Childs, "h", 0 );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "h");
		}
		//Function test:test
		{
		IMethod methodtest23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest23 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
			//Class test
			{
			IType classTest24;
				IModelElement[] methodtest23Childs = methodtest23.getChildren();
				classTest24 = ModelTestUtils.getAssertClass( methodtest23Childs, "Test" );
				//Function test:method_and_var
				{
				IMethod methodmethod_and_var25;
					IModelElement[] classTest24Childs = classTest24.getChildren();
					methodmethod_and_var25 = ModelTestUtils.getAssertMethod( classTest24Childs, "method_and_var", 1 );
					ModelTestUtils.assertParameterNames( methodmethod_and_var25, new String[] {"self"} );
				}
				//Function test:test
				{
				IMethod methodtest26;
					IModelElement[] classTest24Childs = classTest24.getChildren();
					methodtest26 = ModelTestUtils.getAssertMethod( classTest24Childs, "test", 1 );
					ModelTestUtils.assertParameterNames( methodtest26, new String[] {"self"} );
				}
				//Function test:actual_global
				{
				IMethod methodactual_global27;
					IModelElement[] classTest24Childs = classTest24.getChildren();
					methodactual_global27 = ModelTestUtils.getAssertMethod( classTest24Childs, "actual_global", 1 );
					ModelTestUtils.assertParameterNames( methodactual_global27, new String[] {"self"} );
				}
				//Function test:str
				{
				IMethod methodstr28;
					IModelElement[] classTest24Childs = classTest24.getChildren();
					methodstr28 = ModelTestUtils.getAssertMethod( classTest24Childs, "str", 1 );
					ModelTestUtils.assertParameterNames( methodstr28, new String[] {"self"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "method_and_var");
		}
		//Class test
		{
		IType classTest29;
			IModelElement[] moduleChilds = module.getChildren();
			classTest29 = ModelTestUtils.getAssertClass( moduleChilds, "Test" );
			//Function test:method_and_var
			{
			IMethod methodmethod_and_var30;
				IModelElement[] classTest29Childs = classTest29.getChildren();
				methodmethod_and_var30 = ModelTestUtils.getAssertMethod( classTest29Childs, "method_and_var", 1 );
				ModelTestUtils.assertParameterNames( methodmethod_and_var30, new String[] {"self"} );
			}
			//Function test:test
			{
			IMethod methodtest31;
				IModelElement[] classTest29Childs = classTest29.getChildren();
				methodtest31 = ModelTestUtils.getAssertMethod( classTest29Childs, "test", 1 );
				ModelTestUtils.assertParameterNames( methodtest31, new String[] {"self"} );
			}
			//Function test:actual_global
			{
			IMethod methodactual_global32;
				IModelElement[] classTest29Childs = classTest29.getChildren();
				methodactual_global32 = ModelTestUtils.getAssertMethod( classTest29Childs, "actual_global", 1 );
				ModelTestUtils.assertParameterNames( methodactual_global32, new String[] {"self"} );
			}
			//Function test:str
			{
			IMethod methodstr33;
				IModelElement[] classTest29Childs = classTest29.getChildren();
				methodstr33 = ModelTestUtils.getAssertMethod( classTest29Childs, "str", 1 );
				ModelTestUtils.assertParameterNames( methodstr33, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "t");
		}
		//Function test:f
		{
		IMethod methodf34;
			IModelElement[] moduleChilds = module.getChildren();
			methodf34 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf34, new String[] {"x"} );
			//Function test:fact
			{
			IMethod methodfact35;
				IModelElement[] methodf34Childs = methodf34.getChildren();
				methodfact35 = ModelTestUtils.getAssertMethod( methodf34Childs, "fact", 1 );
				ModelTestUtils.assertParameterNames( methodfact35, new String[] {"n"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plus10");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "global_x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inc");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "h");
		}
		//Function test:errorInOuter
		{
		IMethod methoderrorInOuter36;
			IModelElement[] moduleChilds = module.getChildren();
			methoderrorInOuter36 = ModelTestUtils.getAssertMethod( moduleChilds, "errorInOuter", 0 );
			//Function test:inner
			{
			IMethod methodinner37;
				IModelElement[] methoderrorInOuter36Childs = methoderrorInOuter36.getChildren();
				methodinner37 = ModelTestUtils.getAssertMethod( methoderrorInOuter36Childs, "inner", 0 );
			}
		}
		//Function test:errorInInner
		{
		IMethod methoderrorInInner38;
			IModelElement[] moduleChilds = module.getChildren();
			methoderrorInInner38 = ModelTestUtils.getAssertMethod( moduleChilds, "errorInInner", 0 );
			//Function test:inner
			{
			IMethod methodinner39;
				IModelElement[] methoderrorInInner38Childs = methoderrorInInner38.getChildren();
				methodinner39 = ModelTestUtils.getAssertMethod( methoderrorInInner38Childs, "inner", 0 );
			}
		}
		//Function test:makeReturner
		{
		IMethod methodmakeReturner40;
			IModelElement[] moduleChilds = module.getChildren();
			methodmakeReturner40 = ModelTestUtils.getAssertMethod( moduleChilds, "makeReturner", 1 );
			ModelTestUtils.assertParameterNames( methodmakeReturner40, new String[] {"lst"} );
			//Function test:returner
			{
			IMethod methodreturner41;
				IModelElement[] methodmakeReturner40Childs = methodmakeReturner40.getChildren();
				methodreturner41 = ModelTestUtils.getAssertMethod( methodmakeReturner40Childs, "returner", 0 );
			}
		}
		//Function test:makeReturner2
		{
		IMethod methodmakeReturner242;
			IModelElement[] moduleChilds = module.getChildren();
			methodmakeReturner242 = ModelTestUtils.getAssertMethod( moduleChilds, "makeReturner2", 1 );
			ModelTestUtils.assertParameterNames( methodmakeReturner242, new String[] {"kwargs"} );
			//Function test:returner
			{
			IMethod methodreturner43;
				IModelElement[] methodmakeReturner242Childs = methodmakeReturner242.getChildren();
				methodreturner43 = ModelTestUtils.getAssertMethod( methodmakeReturner242Childs, "returner", 0 );
			}
		}
		//Function test:makeAddPair
		{
		IMethod methodmakeAddPair44;
			IModelElement[] moduleChilds = module.getChildren();
			methodmakeAddPair44 = ModelTestUtils.getAssertMethod( moduleChilds, "makeAddPair", 1 );
			ModelTestUtils.assertParameterNames( methodmakeAddPair44, new String[] {"('a', 'b')"} );
			//Function test:addPair
			{
			IMethod methodaddPair45;
				IModelElement[] methodmakeAddPair44Childs = methodmakeAddPair44.getChildren();
				methodaddPair45 = ModelTestUtils.getAssertMethod( methodmakeAddPair44Childs, "addPair", 1 );
				ModelTestUtils.assertParameterNames( methodaddPair45, new String[] {"('c', 'd')"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f
		{
		IMethod methodf46;
			IModelElement[] moduleChilds = module.getChildren();
			methodf46 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
			//Function test:g
			{
			IMethod methodg47;
				IModelElement[] methodf46Childs = methodf46.getChildren();
				methodg47 = ModelTestUtils.getAssertMethod( methodf46Childs, "g", 0 );
				//Function test:i
				{
				IMethod methodi48;
					IModelElement[] methodg47Childs = methodg47.getChildren();
					methodi48 = ModelTestUtils.getAssertMethod( methodg47Childs, "i", 0 );
					//Function test:h
					{
					IMethod methodh49;
						IModelElement[] methodi48Childs = methodi48.getChildren();
						methodh49 = ModelTestUtils.getAssertMethod( methodi48Childs, "h", 0 );
					}
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f
		{
		IMethod methodf50;
			IModelElement[] moduleChilds = module.getChildren();
			methodf50 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
			//Function test:g
			{
			IMethod methodg51;
				IModelElement[] methodf50Childs = methodf50.getChildren();
				methodg51 = ModelTestUtils.getAssertMethod( methodf50Childs, "g", 0 );
				//Function test:i
				{
				IMethod methodi52;
					IModelElement[] methodg51Childs = methodg51.getChildren();
					methodi52 = ModelTestUtils.getAssertMethod( methodg51Childs, "i", 0 );
					//Function test:h
					{
					IMethod methodh53;
						IModelElement[] methodi52Childs = methodi52.getChildren();
						methodh53 = ModelTestUtils.getAssertMethod( methodi52Childs, "h", 0 );
					}
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f
		{
		IMethod methodf54;
			IModelElement[] moduleChilds = module.getChildren();
			methodf54 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
			//Function test:g
			{
			IMethod methodg55;
				IModelElement[] methodf54Childs = methodf54.getChildren();
				methodg55 = ModelTestUtils.getAssertMethod( methodf54Childs, "g", 0 );
				//Function test:i
				{
				IMethod methodi56;
					IModelElement[] methodg55Childs = methodg55.getChildren();
					methodi56 = ModelTestUtils.getAssertMethod( methodg55Childs, "i", 0 );
					//Function test:h
					{
					IMethod methodh57;
						IModelElement[] methodi56Childs = methodi56.getChildren();
						methodh57 = ModelTestUtils.getAssertMethod( methodi56Childs, "h", 0 );
					}
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f
		{
		IMethod methodf58;
			IModelElement[] moduleChilds = module.getChildren();
			methodf58 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 0 );
			//Function test:g
			{
			IMethod methodg59;
				IModelElement[] methodf58Childs = methodf58.getChildren();
				methodg59 = ModelTestUtils.getAssertMethod( methodf58Childs, "g", 0 );
				//Function test:i
				{
				IMethod methodi60;
					IModelElement[] methodg59Childs = methodg59.getChildren();
					methodi60 = ModelTestUtils.getAssertMethod( methodg59Childs, "i", 0 );
					//Function test:h
					{
					IMethod methodh61;
						IModelElement[] methodi60Childs = methodi60.getChildren();
						methodh61 = ModelTestUtils.getAssertMethod( methodi60Childs, "h", 0 );
					}
				}
			}
		}
		//Class test
		{
		IType classFoo62;
			IModelElement[] moduleChilds = module.getChildren();
			classFoo62 = ModelTestUtils.getAssertClass( moduleChilds, "Foo" );
			{
				IModelElement[] classFoo62Childs = classFoo62.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFoo62Childs, "count");
			}
			//Function test:__init__
			{
			IMethod method__init__63;
				IModelElement[] classFoo62Childs = classFoo62.getChildren();
				method__init__63 = ModelTestUtils.getAssertMethod( classFoo62Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__63, new String[] {"self"} );
			}
			//Function test:__del__
			{
			IMethod method__del__64;
				IModelElement[] classFoo62Childs = classFoo62.getChildren();
				method__del__64 = ModelTestUtils.getAssertMethod( classFoo62Childs, "__del__", 1 );
				ModelTestUtils.assertParameterNames( method__del__64, new String[] {"self"} );
			}
		}
		//Function test:f1
		{
		IMethod methodf165;
			IModelElement[] moduleChilds = module.getChildren();
			methodf165 = ModelTestUtils.getAssertMethod( moduleChilds, "f1", 0 );
			//Function test:f2
			{
			IMethod methodf266;
				IModelElement[] methodf165Childs = methodf165.getChildren();
				methodf266 = ModelTestUtils.getAssertMethod( methodf165Childs, "f2", 0 );
			}
		}
		//Function test:test
		{
		IMethod methodtest67;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest67 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 1 );
			ModelTestUtils.assertParameterNames( methodtest67, new String[] {"x"} );
			//Class test
			{
			IType classFoo68;
				IModelElement[] methodtest67Childs = methodtest67.getChildren();
				classFoo68 = ModelTestUtils.getAssertClass( methodtest67Childs, "Foo" );
				//Function test:__call__
				{
				IMethod method__call__69;
					IModelElement[] classFoo68Childs = classFoo68.getChildren();
					method__call__69 = ModelTestUtils.getAssertMethod( classFoo68Childs, "__call__", 2 );
					ModelTestUtils.assertParameterNames( method__call__69, new String[] {"self", "y"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "x");
		}
		//Function test:f
		{
		IMethod methodf70;
			IModelElement[] moduleChilds = module.getChildren();
			methodf70 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf70, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg71;
				IModelElement[] methodf70Childs = methodf70.getChildren();
				methodg71 = ModelTestUtils.getAssertMethod( methodf70Childs, "g", 1 );
				ModelTestUtils.assertParameterNames( methodg71, new String[] {"y"} );
				//Function test:h
				{
				IMethod methodh72;
					IModelElement[] methodg71Childs = methodg71.getChildren();
					methodh72 = ModelTestUtils.getAssertMethod( methodg71Childs, "h", 1 );
					ModelTestUtils.assertParameterNames( methodh72, new String[] {"z"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "d");
		}
		//Function test:f
		{
		IMethod methodf73;
			IModelElement[] moduleChilds = module.getChildren();
			methodf73 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf73, new String[] {"x"} );
			//Class test
			{
			IType classC74;
				IModelElement[] methodf73Childs = methodf73.getChildren();
				classC74 = ModelTestUtils.getAssertClass( methodf73Childs, "C" );
				//Function test:m
				{
				IMethod methodm75;
					IModelElement[] classC74Childs = classC74.getChildren();
					methodm75 = ModelTestUtils.getAssertMethod( classC74Childs, "m", 1 );
					ModelTestUtils.assertParameterNames( methodm75, new String[] {"self"} );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "inst");
		}
		//Function test:tracer
		{
		IMethod methodtracer76;
			IModelElement[] moduleChilds = module.getChildren();
			methodtracer76 = ModelTestUtils.getAssertMethod( moduleChilds, "tracer", 3 );
			ModelTestUtils.assertParameterNames( methodtracer76, new String[] {"a", "b", "c"} );
		}
		//Function test:adaptgetter
		{
		IMethod methodadaptgetter77;
			IModelElement[] moduleChilds = module.getChildren();
			methodadaptgetter77 = ModelTestUtils.getAssertMethod( moduleChilds, "adaptgetter", 3 );
			ModelTestUtils.assertParameterNames( methodadaptgetter77, new String[] {"name", "klass", "getter"} );
		}
		//Class test
		{
		IType classTestClass78;
			IModelElement[] moduleChilds = module.getChildren();
			classTestClass78 = ModelTestUtils.getAssertClass( moduleChilds, "TestClass" );
		}
		//Function test:f
		{
		IMethod methodf79;
			IModelElement[] moduleChilds = module.getChildren();
			methodf79 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf79, new String[] {"x"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "g");
		}
		//Function test:x
		{
		IMethod methodx80;
			IModelElement[] moduleChilds = module.getChildren();
			methodx80 = ModelTestUtils.getAssertMethod( moduleChilds, "x", 0 );
		}
		//Function test:f
		{
		IMethod methodf81;
			IModelElement[] moduleChilds = module.getChildren();
			methodf81 = ModelTestUtils.getAssertMethod( moduleChilds, "f", 1 );
			ModelTestUtils.assertParameterNames( methodf81, new String[] {"x"} );
			//Function test:g
			{
			IMethod methodg82;
				IModelElement[] methodf81Childs = methodf81.getChildren();
				methodg82 = ModelTestUtils.getAssertMethod( methodf81Childs, "g", 0 );
			}
		}

	}
	public void testModelGen143( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_urlparse.py"));

		assertNotNull("Module test_urlparse.py not found", module);
		assertEquals("test_urlparse.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "RFC1808_BASE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "RFC2396_BASE");
		}
		//Class test
		{
		IType classUrlParseTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classUrlParseTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "UrlParseTestCase" );
			//Function test:checkRoundtrips
			{
			IMethod methodcheckRoundtrips1;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodcheckRoundtrips1 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "checkRoundtrips", 4 );
				ModelTestUtils.assertParameterNames( methodcheckRoundtrips1, new String[] {"self", "url", "parsed", "split"} );
			}
			//Function test:test_roundtrips
			{
			IMethod methodtest_roundtrips2;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodtest_roundtrips2 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "test_roundtrips", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrips2, new String[] {"self"} );
			}
			//Function test:test_http_roundtrips
			{
			IMethod methodtest_http_roundtrips3;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodtest_http_roundtrips3 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "test_http_roundtrips", 1 );
				ModelTestUtils.assertParameterNames( methodtest_http_roundtrips3, new String[] {"self"} );
			}
			//Function test:checkJoin
			{
			IMethod methodcheckJoin4;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodcheckJoin4 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "checkJoin", 4 );
				ModelTestUtils.assertParameterNames( methodcheckJoin4, new String[] {"self", "base", "relurl", "expected"} );
			}
			//Function test:test_unparse_parse
			{
			IMethod methodtest_unparse_parse5;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodtest_unparse_parse5 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "test_unparse_parse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unparse_parse5, new String[] {"self"} );
			}
			//Function test:test_RFC1808
			{
			IMethod methodtest_RFC18086;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodtest_RFC18086 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "test_RFC1808", 1 );
				ModelTestUtils.assertParameterNames( methodtest_RFC18086, new String[] {"self"} );
			}
			//Function test:test_RFC2396
			{
			IMethod methodtest_RFC23967;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodtest_RFC23967 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "test_RFC2396", 1 );
				ModelTestUtils.assertParameterNames( methodtest_RFC23967, new String[] {"self"} );
			}
			//Function test:test_urldefrag
			{
			IMethod methodtest_urldefrag8;
				IModelElement[] classUrlParseTestCase0Childs = classUrlParseTestCase0.getChildren();
				methodtest_urldefrag8 = ModelTestUtils.getAssertMethod( classUrlParseTestCase0Childs, "test_urldefrag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_urldefrag8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen144( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_complex.py"));

		assertNotNull("Module test_complex.py not found", module);
		assertEquals("test_complex.py", module.getElementName());
		
		//Class test
		{
		IType classComplexTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classComplexTest0 = ModelTestUtils.getAssertClass( moduleChilds, "ComplexTest" );
			//Function test:assertAlmostEqual
			{
			IMethod methodassertAlmostEqual1;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodassertAlmostEqual1 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "assertAlmostEqual", 3 );
				ModelTestUtils.assertParameterNames( methodassertAlmostEqual1, new String[] {"self", "a", "b"} );
			}
			//Function test:assertCloseAbs
			{
			IMethod methodassertCloseAbs2;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodassertCloseAbs2 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "assertCloseAbs", 4 );
				ModelTestUtils.assertParameterNames( methodassertCloseAbs2, new String[] {"self", "x", "y", "eps"} );
			}
			//Function test:assertClose
			{
			IMethod methodassertClose3;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodassertClose3 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "assertClose", 4 );
				ModelTestUtils.assertParameterNames( methodassertClose3, new String[] {"self", "x", "y", "eps"} );
			}
			//Function test:assertIs
			{
			IMethod methodassertIs4;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodassertIs4 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "assertIs", 3 );
				ModelTestUtils.assertParameterNames( methodassertIs4, new String[] {"self", "a", "b"} );
			}
			//Function test:check_div
			{
			IMethod methodcheck_div5;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodcheck_div5 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "check_div", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_div5, new String[] {"self", "x", "y"} );
			}
			//Function test:test_div
			{
			IMethod methodtest_div6;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_div6 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_div", 1 );
				ModelTestUtils.assertParameterNames( methodtest_div6, new String[] {"self"} );
			}
			//Function test:test_truediv
			{
			IMethod methodtest_truediv7;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_truediv7 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_truediv", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truediv7, new String[] {"self"} );
			}
			//Function test:test_floordiv
			{
			IMethod methodtest_floordiv8;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_floordiv8 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_floordiv", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floordiv8, new String[] {"self"} );
			}
			//Function test:test_coerce
			{
			IMethod methodtest_coerce9;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_coerce9 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_coerce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_coerce9, new String[] {"self"} );
			}
			//Function test:test_richcompare
			{
			IMethod methodtest_richcompare10;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_richcompare10 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_richcompare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_richcompare10, new String[] {"self"} );
			}
			//Function test:test_mod
			{
			IMethod methodtest_mod11;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_mod11 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_mod", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mod11, new String[] {"self"} );
			}
			//Function test:test_divmod
			{
			IMethod methodtest_divmod12;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_divmod12 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_divmod", 1 );
				ModelTestUtils.assertParameterNames( methodtest_divmod12, new String[] {"self"} );
			}
			//Function test:test_pow
			{
			IMethod methodtest_pow13;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_pow13 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_pow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pow13, new String[] {"self"} );
			}
			//Function test:test_boolcontext
			{
			IMethod methodtest_boolcontext14;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_boolcontext14 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_boolcontext", 1 );
				ModelTestUtils.assertParameterNames( methodtest_boolcontext14, new String[] {"self"} );
			}
			//Function test:test_conjugate
			{
			IMethod methodtest_conjugate15;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_conjugate15 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_conjugate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conjugate15, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor16;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_constructor16 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor16, new String[] {"self"} );
				//Class test
				{
				IType classOS17;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classOS17 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "OS" );
					//Function test:__init__
					{
					IMethod method__init__18;
						IModelElement[] classOS17Childs = classOS17.getChildren();
						method__init__18 = ModelTestUtils.getAssertMethod( classOS17Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__18, new String[] {"self", "value"} );
					}
					//Function test:__complex__
					{
					IMethod method__complex__19;
						IModelElement[] classOS17Childs = classOS17.getChildren();
						method__complex__19 = ModelTestUtils.getAssertMethod( classOS17Childs, "__complex__", 1 );
						ModelTestUtils.assertParameterNames( method__complex__19, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classNS20;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classNS20 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "NS" );
					//Function test:__init__
					{
					IMethod method__init__21;
						IModelElement[] classNS20Childs = classNS20.getChildren();
						method__init__21 = ModelTestUtils.getAssertMethod( classNS20Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__21, new String[] {"self", "value"} );
					}
					//Function test:__complex__
					{
					IMethod method__complex__22;
						IModelElement[] classNS20Childs = classNS20.getChildren();
						method__complex__22 = ModelTestUtils.getAssertMethod( classNS20Childs, "__complex__", 1 );
						ModelTestUtils.assertParameterNames( method__complex__22, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classcomplex223;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classcomplex223 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "complex2" );
				}
				//Class test
				{
				IType classEvilExc24;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classEvilExc24 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "EvilExc" );
				}
				//Class test
				{
				IType classevilcomplex25;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classevilcomplex25 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "evilcomplex" );
					//Function test:__complex__
					{
					IMethod method__complex__26;
						IModelElement[] classevilcomplex25Childs = classevilcomplex25.getChildren();
						method__complex__26 = ModelTestUtils.getAssertMethod( classevilcomplex25Childs, "__complex__", 1 );
						ModelTestUtils.assertParameterNames( method__complex__26, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classfloat227;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classfloat227 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "float2" );
					//Function test:__init__
					{
					IMethod method__init__28;
						IModelElement[] classfloat227Childs = classfloat227.getChildren();
						method__init__28 = ModelTestUtils.getAssertMethod( classfloat227Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__28, new String[] {"self", "value"} );
					}
					//Function test:__float__
					{
					IMethod method__float__29;
						IModelElement[] classfloat227Childs = classfloat227.getChildren();
						method__float__29 = ModelTestUtils.getAssertMethod( classfloat227Childs, "__float__", 1 );
						ModelTestUtils.assertParameterNames( method__float__29, new String[] {"self"} );
					}
				}
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash30;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_hash30 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash30, new String[] {"self"} );
			}
			//Function test:test_abs
			{
			IMethod methodtest_abs31;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_abs31 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_abs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs31, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr32;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_repr32 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr32, new String[] {"self"} );
			}
			//Function test:test_neg
			{
			IMethod methodtest_neg33;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_neg33 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_neg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_neg33, new String[] {"self"} );
			}
			//Function test:test_file
			{
			IMethod methodtest_file34;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_file34 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file34, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main35 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen145( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_symtable.py"));

		assertNotNull("Module test_symtable.py not found", module);
		assertEquals("test_symtable.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "symbols");
		}
		//Function test:checkfilename
		{
		IMethod methodcheckfilename0;
			IModelElement[] moduleChilds = module.getChildren();
			methodcheckfilename0 = ModelTestUtils.getAssertMethod( moduleChilds, "checkfilename", 1 );
			ModelTestUtils.assertParameterNames( methodcheckfilename0, new String[] {"brokencode"} );
		}

	}
	public void testModelGen146( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_descrtut.py"));

		assertNotNull("Module test_descrtut.py not found", module);
		assertEquals("test_descrtut.py", module.getElementName());
		
		//Class test
		{
		IType classdefaultdict0;
			IModelElement[] moduleChilds = module.getChildren();
			classdefaultdict0 = ModelTestUtils.getAssertClass( moduleChilds, "defaultdict" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "default"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__2;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				method__getitem__2 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__2, new String[] {"self", "key"} );
			}
			//Function test:get
			{
			IMethod methodget3;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				methodget3 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "get", 3 );
				ModelTestUtils.assertParameterNames( methodget3, new String[] {"self", "key", "args"} );
			}
			//Function test:merge
			{
			IMethod methodmerge4;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				methodmerge4 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "merge", 2 );
				ModelTestUtils.assertParameterNames( methodmerge4, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_1");
		}
		//Class test
		{
		IType classdefaultdict25;
			IModelElement[] moduleChilds = module.getChildren();
			classdefaultdict25 = ModelTestUtils.getAssertClass( moduleChilds, "defaultdict2" );
			{
				IModelElement[] classdefaultdict25Childs = classdefaultdict25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classdefaultdict25Childs, "__slots__");
			}
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classdefaultdict25Childs = classdefaultdict25.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classdefaultdict25Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "default"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__7;
				IModelElement[] classdefaultdict25Childs = classdefaultdict25.getChildren();
				method__getitem__7 = ModelTestUtils.getAssertMethod( classdefaultdict25Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__7, new String[] {"self", "key"} );
			}
			//Function test:get
			{
			IMethod methodget8;
				IModelElement[] classdefaultdict25Childs = classdefaultdict25.getChildren();
				methodget8 = ModelTestUtils.getAssertMethod( classdefaultdict25Childs, "get", 3 );
				ModelTestUtils.assertParameterNames( methodget8, new String[] {"self", "key", "args"} );
			}
			//Function test:merge
			{
			IMethod methodmerge9;
				IModelElement[] classdefaultdict25Childs = classdefaultdict25.getChildren();
				methodmerge9 = ModelTestUtils.getAssertMethod( classdefaultdict25Childs, "merge", 2 );
				ModelTestUtils.assertParameterNames( methodmerge9, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_3");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_4");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_5");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_6");
		}
		//Class test
		{
		IType classA10;
			IModelElement[] moduleChilds = module.getChildren();
			classA10 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
			//Function test:m
			{
			IMethod methodm11;
				IModelElement[] classA10Childs = classA10.getChildren();
				methodm11 = ModelTestUtils.getAssertMethod( classA10Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classB12;
			IModelElement[] moduleChilds = module.getChildren();
			classB12 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
			//Function test:m
			{
			IMethod methodm13;
				IModelElement[] classB12Childs = classB12.getChildren();
				methodm13 = ModelTestUtils.getAssertMethod( classB12Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC14;
			IModelElement[] moduleChilds = module.getChildren();
			classC14 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:m
			{
			IMethod methodm15;
				IModelElement[] classC14Childs = classC14.getChildren();
				methodm15 = ModelTestUtils.getAssertMethod( classC14Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm15, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classD16;
			IModelElement[] moduleChilds = module.getChildren();
			classD16 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
			//Function test:m
			{
			IMethod methodm17;
				IModelElement[] classD16Childs = classD16.getChildren();
				methodm17 = ModelTestUtils.getAssertMethod( classD16Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm17, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_7");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_8");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}
		//Function test:test_main
		{
		IMethod methodtest_main18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main18, new String[] {"verbose"} );
		}

	}
	public void testModelGen147( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("xmltests.py"));

		assertNotNull("Module xmltests.py not found", module);
		assertEquals("xmltests.py", module.getElementName());
		
		//Function test:runtest
		{
		IMethod methodruntest0;
			IModelElement[] moduleChilds = module.getChildren();
			methodruntest0 = ModelTestUtils.getAssertMethod( moduleChilds, "runtest", 1 );
			ModelTestUtils.assertParameterNames( methodruntest0, new String[] {"name"} );
		}

	}
	public void testModelGen148( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_filecmp.py"));

		assertNotNull("Module test_filecmp.py not found", module);
		assertEquals("test_filecmp.py", module.getElementName());
		
		//Class test
		{
		IType classFileCompareTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classFileCompareTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "FileCompareTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:test_matching
			{
			IMethod methodtest_matching3;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodtest_matching3 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "test_matching", 1 );
				ModelTestUtils.assertParameterNames( methodtest_matching3, new String[] {"self"} );
			}
			//Function test:test_different
			{
			IMethod methodtest_different4;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodtest_different4 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "test_different", 1 );
				ModelTestUtils.assertParameterNames( methodtest_different4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDirCompareTestCase5;
			IModelElement[] moduleChilds = module.getChildren();
			classDirCompareTestCase5 = ModelTestUtils.getAssertClass( moduleChilds, "DirCompareTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp6;
				IModelElement[] classDirCompareTestCase5Childs = classDirCompareTestCase5.getChildren();
				methodsetUp6 = ModelTestUtils.getAssertMethod( classDirCompareTestCase5Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp6, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown7;
				IModelElement[] classDirCompareTestCase5Childs = classDirCompareTestCase5.getChildren();
				methodtearDown7 = ModelTestUtils.getAssertMethod( classDirCompareTestCase5Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown7, new String[] {"self"} );
			}
			//Function test:test_cmpfiles
			{
			IMethod methodtest_cmpfiles8;
				IModelElement[] classDirCompareTestCase5Childs = classDirCompareTestCase5.getChildren();
				methodtest_cmpfiles8 = ModelTestUtils.getAssertMethod( classDirCompareTestCase5Childs, "test_cmpfiles", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmpfiles8, new String[] {"self"} );
			}
			//Function test:test_dircmp
			{
			IMethod methodtest_dircmp9;
				IModelElement[] classDirCompareTestCase5Childs = classDirCompareTestCase5.getChildren();
				methodtest_dircmp9 = ModelTestUtils.getAssertMethod( classDirCompareTestCase5Childs, "test_dircmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dircmp9, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen149( ) throws Exception {
		String prj = "pytests_2";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bastion.py"));

		assertNotNull("Module test_bastion.py not found", module);
		assertEquals("test_bastion.py", module.getElementName());
		

	}

}
	