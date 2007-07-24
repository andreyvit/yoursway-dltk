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
		setUpScriptProject( "pytests" );
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests" );
	}
	public void testModelGen100( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classCoerce2Childs = classCoerce2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classCoerce2Childs, "arg");
			}
			//Function test:__repr__
			{
			IMethod method__repr__5;
				IModelElement[] classCoerce2Childs = classCoerce2.getChildren();
				method__repr__5 = ModelTestUtils.getAssertMethod( classCoerce2Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__5, new String[] {"self"} );
			}
			//Function test:__coerce__
			{
			IMethod method__coerce__6;
				IModelElement[] classCoerce2Childs = classCoerce2.getChildren();
				method__coerce__6 = ModelTestUtils.getAssertMethod( classCoerce2Childs, "__coerce__", 2 );
				ModelTestUtils.assertParameterNames( method__coerce__6, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classCmp7;
			IModelElement[] moduleChilds = module.getChildren();
			classCmp7 = ModelTestUtils.getAssertClass( moduleChilds, "Cmp" );
			//Function test:__init__
			{
			IMethod method__init__8;
				IModelElement[] classCmp7Childs = classCmp7.getChildren();
				method__init__8 = ModelTestUtils.getAssertMethod( classCmp7Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "arg"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__9;
				IModelElement[] classCmp7Childs = classCmp7.getChildren();
				method__repr__9 = ModelTestUtils.getAssertMethod( classCmp7Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__9, new String[] {"self"} );
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__10;
				IModelElement[] classCmp7Childs = classCmp7.getChildren();
				method__cmp__10 = ModelTestUtils.getAssertMethod( classCmp7Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__10, new String[] {"self", "other"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "candidates");
		}
		//Function test:test
		{
		IMethod methodtest11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest11 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen102( ) throws Exception {
		String prj = "pytests";
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
			{
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classbarrier2Childs, "n");
			}
			{
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classbarrier2Childs, "waiting");
			}
			{
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classbarrier2Childs, "checkin");
			}
			{
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classbarrier2Childs, "checkout");
			}
			//Function test:enter
			{
			IMethod methodenter5;
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				methodenter5 = ModelTestUtils.getAssertMethod( classbarrier2Childs, "enter", 1 );
				ModelTestUtils.assertParameterNames( methodenter5, new String[] {"self"} );
			}
			{
				IModelElement[] classbarrier2Childs = classbarrier2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classbarrier2Childs, "waiting");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "numtrips");
		}
		//Function test:task2
		{
		IMethod methodtask27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtask27 = ModelTestUtils.getAssertMethod( moduleChilds, "task2", 1 );
			ModelTestUtils.assertParameterNames( methodtask27, new String[] {"ident"} );
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
		String prj = "pytests";
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
			{
				IModelElement[] classPosReturn0Childs = classPosReturn0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPosReturn0Childs, "pos");
			}
			//Function test:handle
			{
			IMethod methodhandle3;
				IModelElement[] classPosReturn0Childs = classPosReturn0.getChildren();
				methodhandle3 = ModelTestUtils.getAssertMethod( classPosReturn0Childs, "handle", 2 );
				ModelTestUtils.assertParameterNames( methodhandle3, new String[] {"self", "exc"} );
			}
			{
				IModelElement[] classPosReturn0Childs = classPosReturn0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classPosReturn0Childs, "pos");
			}
		}
		//Class test
		{
		IType classCodecCallbackTest5;
			IModelElement[] moduleChilds = module.getChildren();
			classCodecCallbackTest5 = ModelTestUtils.getAssertClass( moduleChilds, "CodecCallbackTest" );
			//Function test:test_xmlcharrefreplace
			{
			IMethod methodtest_xmlcharrefreplace6;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_xmlcharrefreplace6 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_xmlcharrefreplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharrefreplace6, new String[] {"self"} );
			}
			//Function test:test_xmlcharnamereplace
			{
			IMethod methodtest_xmlcharnamereplace7;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_xmlcharnamereplace7 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_xmlcharnamereplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharnamereplace7, new String[] {"self"} );
				//Function test:xmlcharnamereplace
				{
				IMethod methodxmlcharnamereplace8;
					IModelElement[] methodtest_xmlcharnamereplace7Childs = methodtest_xmlcharnamereplace7.getChildren();
					methodxmlcharnamereplace8 = ModelTestUtils.getAssertMethod( methodtest_xmlcharnamereplace7Childs, "xmlcharnamereplace", 1 );
					ModelTestUtils.assertParameterNames( methodxmlcharnamereplace8, new String[] {"exc"} );
				}
			}
			//Function test:test_uninamereplace
			{
			IMethod methodtest_uninamereplace9;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_uninamereplace9 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_uninamereplace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_uninamereplace9, new String[] {"self"} );
				//Function test:uninamereplace
				{
				IMethod methoduninamereplace10;
					IModelElement[] methodtest_uninamereplace9Childs = methodtest_uninamereplace9.getChildren();
					methoduninamereplace10 = ModelTestUtils.getAssertMethod( methodtest_uninamereplace9Childs, "uninamereplace", 1 );
					ModelTestUtils.assertParameterNames( methoduninamereplace10, new String[] {"exc"} );
				}
			}
			//Function test:test_backslashescape
			{
			IMethod methodtest_backslashescape11;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_backslashescape11 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_backslashescape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_backslashescape11, new String[] {"self"} );
			}
			//Function test:test_relaxedutf8
			{
			IMethod methodtest_relaxedutf812;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_relaxedutf812 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_relaxedutf8", 1 );
				ModelTestUtils.assertParameterNames( methodtest_relaxedutf812, new String[] {"self"} );
				//Function test:relaxedutf8
				{
				IMethod methodrelaxedutf813;
					IModelElement[] methodtest_relaxedutf812Childs = methodtest_relaxedutf812.getChildren();
					methodrelaxedutf813 = ModelTestUtils.getAssertMethod( methodtest_relaxedutf812Childs, "relaxedutf8", 1 );
					ModelTestUtils.assertParameterNames( methodrelaxedutf813, new String[] {"exc"} );
				}
			}
			//Function test:test_charmapencode
			{
			IMethod methodtest_charmapencode14;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_charmapencode14 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_charmapencode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_charmapencode14, new String[] {"self"} );
			}
			//Function test:test_callbacks
			{
			IMethod methodtest_callbacks15;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_callbacks15 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_callbacks", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callbacks15, new String[] {"self"} );
				//Function test:handler1
				{
				IMethod methodhandler116;
					IModelElement[] methodtest_callbacks15Childs = methodtest_callbacks15.getChildren();
					methodhandler116 = ModelTestUtils.getAssertMethod( methodtest_callbacks15Childs, "handler1", 1 );
					ModelTestUtils.assertParameterNames( methodhandler116, new String[] {"exc"} );
				}
				//Function test:handler2
				{
				IMethod methodhandler217;
					IModelElement[] methodtest_callbacks15Childs = methodtest_callbacks15.getChildren();
					methodhandler217 = ModelTestUtils.getAssertMethod( methodtest_callbacks15Childs, "handler2", 1 );
					ModelTestUtils.assertParameterNames( methodhandler217, new String[] {"exc"} );
				}
			}
			//Function test:test_longstrings
			{
			IMethod methodtest_longstrings18;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_longstrings18 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_longstrings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longstrings18, new String[] {"self"} );
			}
			//Function test:check_exceptionobjectargs
			{
			IMethod methodcheck_exceptionobjectargs19;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodcheck_exceptionobjectargs19 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "check_exceptionobjectargs", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_exceptionobjectargs19, new String[] {"self", "exctype", "args", "msg"} );
			}
			//Function test:test_unicodeencodeerror
			{
			IMethod methodtest_unicodeencodeerror20;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_unicodeencodeerror20 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_unicodeencodeerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicodeencodeerror20, new String[] {"self"} );
			}
			//Function test:test_unicodedecodeerror
			{
			IMethod methodtest_unicodedecodeerror21;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_unicodedecodeerror21 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_unicodedecodeerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicodedecodeerror21, new String[] {"self"} );
			}
			//Function test:test_unicodetranslateerror
			{
			IMethod methodtest_unicodetranslateerror22;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_unicodetranslateerror22 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_unicodetranslateerror", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicodetranslateerror22, new String[] {"self"} );
			}
			//Function test:test_badandgoodstrictexceptions
			{
			IMethod methodtest_badandgoodstrictexceptions23;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badandgoodstrictexceptions23 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badandgoodstrictexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodstrictexceptions23, new String[] {"self"} );
			}
			//Function test:test_badandgoodignoreexceptions
			{
			IMethod methodtest_badandgoodignoreexceptions24;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badandgoodignoreexceptions24 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badandgoodignoreexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodignoreexceptions24, new String[] {"self"} );
			}
			//Function test:test_badandgoodreplaceexceptions
			{
			IMethod methodtest_badandgoodreplaceexceptions25;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badandgoodreplaceexceptions25 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badandgoodreplaceexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodreplaceexceptions25, new String[] {"self"} );
			}
			//Function test:test_badandgoodxmlcharrefreplaceexceptions
			{
			IMethod methodtest_badandgoodxmlcharrefreplaceexceptions26;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badandgoodxmlcharrefreplaceexceptions26 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badandgoodxmlcharrefreplaceexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodxmlcharrefreplaceexceptions26, new String[] {"self"} );
			}
			//Function test:test_badandgoodbackslashreplaceexceptions
			{
			IMethod methodtest_badandgoodbackslashreplaceexceptions27;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badandgoodbackslashreplaceexceptions27 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badandgoodbackslashreplaceexceptions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badandgoodbackslashreplaceexceptions27, new String[] {"self"} );
			}
			//Function test:test_badhandlerresults
			{
			IMethod methodtest_badhandlerresults28;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badhandlerresults28 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badhandlerresults", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badhandlerresults28, new String[] {"self"} );
			}
			//Function test:test_lookup
			{
			IMethod methodtest_lookup29;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_lookup29 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_lookup", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lookup29, new String[] {"self"} );
			}
			//Function test:test_unencodablereplacement
			{
			IMethod methodtest_unencodablereplacement30;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_unencodablereplacement30 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_unencodablereplacement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unencodablereplacement30, new String[] {"self"} );
				//Function test:unencrepl
				{
				IMethod methodunencrepl31;
					IModelElement[] methodtest_unencodablereplacement30Childs = methodtest_unencodablereplacement30.getChildren();
					methodunencrepl31 = ModelTestUtils.getAssertMethod( methodtest_unencodablereplacement30Childs, "unencrepl", 1 );
					ModelTestUtils.assertParameterNames( methodunencrepl31, new String[] {"exc"} );
				}
			}
			//Function test:test_badregistercall
			{
			IMethod methodtest_badregistercall32;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_badregistercall32 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_badregistercall", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badregistercall32, new String[] {"self"} );
			}
			//Function test:test_unknownhandler
			{
			IMethod methodtest_unknownhandler33;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_unknownhandler33 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_unknownhandler", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unknownhandler33, new String[] {"self"} );
			}
			//Function test:test_xmlcharrefvalues
			{
			IMethod methodtest_xmlcharrefvalues34;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_xmlcharrefvalues34 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_xmlcharrefvalues", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xmlcharrefvalues34, new String[] {"self"} );
			}
			//Function test:test_decodehelper
			{
			IMethod methodtest_decodehelper35;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_decodehelper35 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_decodehelper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodehelper35, new String[] {"self"} );
				//Function test:baddecodereturn1
				{
				IMethod methodbaddecodereturn136;
					IModelElement[] methodtest_decodehelper35Childs = methodtest_decodehelper35.getChildren();
					methodbaddecodereturn136 = ModelTestUtils.getAssertMethod( methodtest_decodehelper35Childs, "baddecodereturn1", 1 );
					ModelTestUtils.assertParameterNames( methodbaddecodereturn136, new String[] {"exc"} );
				}
				//Function test:baddecodereturn2
				{
				IMethod methodbaddecodereturn237;
					IModelElement[] methodtest_decodehelper35Childs = methodtest_decodehelper35.getChildren();
					methodbaddecodereturn237 = ModelTestUtils.getAssertMethod( methodtest_decodehelper35Childs, "baddecodereturn2", 1 );
					ModelTestUtils.assertParameterNames( methodbaddecodereturn237, new String[] {"exc"} );
				}
				//Class test
				{
				IType classD38;
					IModelElement[] methodtest_decodehelper35Childs = methodtest_decodehelper35.getChildren();
					classD38 = ModelTestUtils.getAssertClass( methodtest_decodehelper35Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__39;
						IModelElement[] classD38Childs = classD38.getChildren();
						method__getitem__39 = ModelTestUtils.getAssertMethod( classD38Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__39, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_encodehelper
			{
			IMethod methodtest_encodehelper40;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_encodehelper40 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_encodehelper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encodehelper40, new String[] {"self"} );
				//Function test:badencodereturn1
				{
				IMethod methodbadencodereturn141;
					IModelElement[] methodtest_encodehelper40Childs = methodtest_encodehelper40.getChildren();
					methodbadencodereturn141 = ModelTestUtils.getAssertMethod( methodtest_encodehelper40Childs, "badencodereturn1", 1 );
					ModelTestUtils.assertParameterNames( methodbadencodereturn141, new String[] {"exc"} );
				}
				//Function test:badencodereturn2
				{
				IMethod methodbadencodereturn242;
					IModelElement[] methodtest_encodehelper40Childs = methodtest_encodehelper40.getChildren();
					methodbadencodereturn242 = ModelTestUtils.getAssertMethod( methodtest_encodehelper40Childs, "badencodereturn2", 1 );
					ModelTestUtils.assertParameterNames( methodbadencodereturn242, new String[] {"exc"} );
				}
				//Class test
				{
				IType classD43;
					IModelElement[] methodtest_encodehelper40Childs = methodtest_encodehelper40.getChildren();
					classD43 = ModelTestUtils.getAssertClass( methodtest_encodehelper40Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__44;
						IModelElement[] classD43Childs = classD43.getChildren();
						method__getitem__44 = ModelTestUtils.getAssertMethod( classD43Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__44, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_translatehelper
			{
			IMethod methodtest_translatehelper45;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_translatehelper45 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_translatehelper", 1 );
				ModelTestUtils.assertParameterNames( methodtest_translatehelper45, new String[] {"self"} );
				//Class test
				{
				IType classD46;
					IModelElement[] methodtest_translatehelper45Childs = methodtest_translatehelper45.getChildren();
					classD46 = ModelTestUtils.getAssertClass( methodtest_translatehelper45Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__47;
						IModelElement[] classD46Childs = classD46.getChildren();
						method__getitem__47 = ModelTestUtils.getAssertMethod( classD46Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__47, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_bug828737
			{
			IMethod methodtest_bug82873748;
				IModelElement[] classCodecCallbackTest5Childs = classCodecCallbackTest5.getChildren();
				methodtest_bug82873748 = ModelTestUtils.getAssertMethod( classCodecCallbackTest5Childs, "test_bug828737", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug82873748, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main49;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main49 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen104( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classComplains1Childs, "i");
			}
			//Function test:__lt__
			{
			IMethod method__lt__4;
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				method__lt__4 = ModelTestUtils.getAssertMethod( classComplains1Childs, "__lt__", 2 );
				ModelTestUtils.assertParameterNames( method__lt__4, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__5;
				IModelElement[] classComplains1Childs = classComplains1.getChildren();
				method__repr__5 = ModelTestUtils.getAssertMethod( classComplains1Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStable6;
			IModelElement[] moduleChilds = module.getChildren();
			classStable6 = ModelTestUtils.getAssertClass( moduleChilds, "Stable" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classStable6Childs = classStable6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classStable6Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "key", "i"} );
			}
			{
				IModelElement[] classStable6Childs = classStable6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStable6Childs, "key");
			}
			{
				IModelElement[] classStable6Childs = classStable6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStable6Childs, "index");
			}
			//Function test:__cmp__
			{
			IMethod method__cmp__9;
				IModelElement[] classStable6Childs = classStable6.getChildren();
				method__cmp__9 = ModelTestUtils.getAssertMethod( classStable6Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__9, new String[] {"self", "other"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__10;
				IModelElement[] classStable6Childs = classStable6.getChildren();
				method__repr__10 = ModelTestUtils.getAssertMethod( classStable6Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__10, new String[] {"self"} );
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
		IType classTestBugs11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBugs11 = ModelTestUtils.getAssertClass( moduleChilds, "TestBugs" );
			//Function test:test_bug453523
			{
			IMethod methodtest_bug45352312;
				IModelElement[] classTestBugs11Childs = classTestBugs11.getChildren();
				methodtest_bug45352312 = ModelTestUtils.getAssertMethod( classTestBugs11Childs, "test_bug453523", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug45352312, new String[] {"self"} );
				//Class test
				{
				IType classC13;
					IModelElement[] methodtest_bug45352312Childs = methodtest_bug45352312.getChildren();
					classC13 = ModelTestUtils.getAssertClass( methodtest_bug45352312Childs, "C" );
					//Function test:__lt__
					{
					IMethod method__lt__14;
						IModelElement[] classC13Childs = classC13.getChildren();
						method__lt__14 = ModelTestUtils.getAssertMethod( classC13Childs, "__lt__", 2 );
						ModelTestUtils.assertParameterNames( method__lt__14, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_cmpNone
			{
			IMethod methodtest_cmpNone15;
				IModelElement[] classTestBugs11Childs = classTestBugs11.getChildren();
				methodtest_cmpNone15 = ModelTestUtils.getAssertMethod( classTestBugs11Childs, "test_cmpNone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmpNone15, new String[] {"self"} );
			}
			//Function test:test_undetected_mutation
			{
			IMethod methodtest_undetected_mutation16;
				IModelElement[] classTestBugs11Childs = classTestBugs11.getChildren();
				methodtest_undetected_mutation16 = ModelTestUtils.getAssertMethod( classTestBugs11Childs, "test_undetected_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_undetected_mutation16, new String[] {"self"} );
				//Function test:mutating_cmp
				{
				IMethod methodmutating_cmp17;
					IModelElement[] methodtest_undetected_mutation16Childs = methodtest_undetected_mutation16.getChildren();
					methodmutating_cmp17 = ModelTestUtils.getAssertMethod( methodtest_undetected_mutation16Childs, "mutating_cmp", 2 );
					ModelTestUtils.assertParameterNames( methodmutating_cmp17, new String[] {"x", "y"} );
				}
				//Function test:mutating_cmp
				{
				IMethod methodmutating_cmp18;
					IModelElement[] methodtest_undetected_mutation16Childs = methodtest_undetected_mutation16.getChildren();
					methodmutating_cmp18 = ModelTestUtils.getAssertMethod( methodtest_undetected_mutation16Childs, "mutating_cmp", 2 );
					ModelTestUtils.assertParameterNames( methodmutating_cmp18, new String[] {"x", "y"} );
				}
			}
		}
		//Class test
		{
		IType classTestDecorateSortUndecorate19;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDecorateSortUndecorate19 = ModelTestUtils.getAssertClass( moduleChilds, "TestDecorateSortUndecorate" );
			//Function test:test_decorated
			{
			IMethod methodtest_decorated20;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_decorated20 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_decorated", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decorated20, new String[] {"self"} );
			}
			//Function test:test_baddecorator
			{
			IMethod methodtest_baddecorator21;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_baddecorator21 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_baddecorator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_baddecorator21, new String[] {"self"} );
			}
			//Function test:test_stability
			{
			IMethod methodtest_stability22;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_stability22 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_stability", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stability22, new String[] {"self"} );
			}
			//Function test:test_cmp_and_key_combination
			{
			IMethod methodtest_cmp_and_key_combination23;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_cmp_and_key_combination23 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_cmp_and_key_combination", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmp_and_key_combination23, new String[] {"self"} );
				//Function test:compare
				{
				IMethod methodcompare24;
					IModelElement[] methodtest_cmp_and_key_combination23Childs = methodtest_cmp_and_key_combination23.getChildren();
					methodcompare24 = ModelTestUtils.getAssertMethod( methodtest_cmp_and_key_combination23Childs, "compare", 2 );
					ModelTestUtils.assertParameterNames( methodcompare24, new String[] {"x", "y"} );
				}
			}
			//Function test:test_badcmp_with_key
			{
			IMethod methodtest_badcmp_with_key25;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_badcmp_with_key25 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_badcmp_with_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_badcmp_with_key25, new String[] {"self"} );
			}
			//Function test:test_key_with_exception
			{
			IMethod methodtest_key_with_exception26;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_key_with_exception26 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_key_with_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_exception26, new String[] {"self"} );
			}
			//Function test:test_key_with_mutation
			{
			IMethod methodtest_key_with_mutation27;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_key_with_mutation27 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_key_with_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_mutation27, new String[] {"self"} );
				//Function test:k
				{
				IMethod methodk28;
					IModelElement[] methodtest_key_with_mutation27Childs = methodtest_key_with_mutation27.getChildren();
					methodk28 = ModelTestUtils.getAssertMethod( methodtest_key_with_mutation27Childs, "k", 1 );
					ModelTestUtils.assertParameterNames( methodk28, new String[] {"x"} );
				}
			}
			//Function test:test_key_with_mutating_del
			{
			IMethod methodtest_key_with_mutating_del29;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_key_with_mutating_del29 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_key_with_mutating_del", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_mutating_del29, new String[] {"self"} );
				//Class test
				{
				IType classSortKiller30;
					IModelElement[] methodtest_key_with_mutating_del29Childs = methodtest_key_with_mutating_del29.getChildren();
					classSortKiller30 = ModelTestUtils.getAssertClass( methodtest_key_with_mutating_del29Childs, "SortKiller" );
					//Function test:__init__
					{
					IMethod method__init__31;
						IModelElement[] classSortKiller30Childs = classSortKiller30.getChildren();
						method__init__31 = ModelTestUtils.getAssertMethod( classSortKiller30Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__31, new String[] {"self", "x"} );
					}
					//Function test:__del__
					{
					IMethod method__del__32;
						IModelElement[] classSortKiller30Childs = classSortKiller30.getChildren();
						method__del__32 = ModelTestUtils.getAssertMethod( classSortKiller30Childs, "__del__", 1 );
						ModelTestUtils.assertParameterNames( method__del__32, new String[] {"self"} );
					}
				}
			}
			//Function test:test_key_with_mutating_del_and_exception
			{
			IMethod methodtest_key_with_mutating_del_and_exception33;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_key_with_mutating_del_and_exception33 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_key_with_mutating_del_and_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_key_with_mutating_del_and_exception33, new String[] {"self"} );
				//Class test
				{
				IType classSortKiller34;
					IModelElement[] methodtest_key_with_mutating_del_and_exception33Childs = methodtest_key_with_mutating_del_and_exception33.getChildren();
					classSortKiller34 = ModelTestUtils.getAssertClass( methodtest_key_with_mutating_del_and_exception33Childs, "SortKiller" );
					//Function test:__init__
					{
					IMethod method__init__35;
						IModelElement[] classSortKiller34Childs = classSortKiller34.getChildren();
						method__init__35 = ModelTestUtils.getAssertMethod( classSortKiller34Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__35, new String[] {"self", "x"} );
					}
					//Function test:__del__
					{
					IMethod method__del__36;
						IModelElement[] classSortKiller34Childs = classSortKiller34.getChildren();
						method__del__36 = ModelTestUtils.getAssertMethod( classSortKiller34Childs, "__del__", 1 );
						ModelTestUtils.assertParameterNames( method__del__36, new String[] {"self"} );
					}
				}
			}
			//Function test:test_reverse
			{
			IMethod methodtest_reverse37;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_reverse37 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_reverse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse37, new String[] {"self"} );
			}
			//Function test:test_reverse_stability
			{
			IMethod methodtest_reverse_stability38;
				IModelElement[] classTestDecorateSortUndecorate19Childs = classTestDecorateSortUndecorate19.getChildren();
				methodtest_reverse_stability38 = ModelTestUtils.getAssertMethod( classTestDecorateSortUndecorate19Childs, "test_reverse_stability", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reverse_stability38, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main39, new String[] {"verbose"} );
		}

	}
	public void testModelGen107( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
					{
						IModelElement[] classOtherSeq2Childs = classOtherSeq2.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classOtherSeq2Childs, "__data");
					}
					//Function test:__len__
					{
					IMethod method__len__5;
						IModelElement[] classOtherSeq2Childs = classOtherSeq2.getChildren();
						method__len__5 = ModelTestUtils.getAssertMethod( classOtherSeq2Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__5, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__6;
						IModelElement[] classOtherSeq2Childs = classOtherSeq2.getChildren();
						method__getitem__6 = ModelTestUtils.getAssertMethod( classOtherSeq2Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__6, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_truth
			{
			IMethod methodtest_truth7;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_truth7 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_truth", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truth7, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem8;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_getitem8 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem8, new String[] {"self"} );
			}
			//Function test:test_getslice
			{
			IMethod methodtest_getslice9;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_getslice9 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_getslice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getslice9, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains10;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_contains10 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains10, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len11;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_len11 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len11, new String[] {"self"} );
			}
			//Function test:test_minmax
			{
			IMethod methodtest_minmax12;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_minmax12 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_minmax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_minmax12, new String[] {"self"} );
			}
			//Function test:test_addmul
			{
			IMethod methodtest_addmul13;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_addmul13 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_addmul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addmul13, new String[] {"self"} );
				//Class test
				{
				IType classsubclass14;
					IModelElement[] methodtest_addmul13Childs = methodtest_addmul13.getChildren();
					classsubclass14 = ModelTestUtils.getAssertClass( methodtest_addmul13Childs, "subclass" );
				}
			}
			//Function test:test_iadd
			{
			IMethod methodtest_iadd15;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_iadd15 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_iadd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iadd15, new String[] {"self"} );
			}
			//Function test:test_imul
			{
			IMethod methodtest_imul16;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_imul16 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_imul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_imul16, new String[] {"self"} );
			}
			//Function test:test_getitemoverwriteiter
			{
			IMethod methodtest_getitemoverwriteiter17;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_getitemoverwriteiter17 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_getitemoverwriteiter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitemoverwriteiter17, new String[] {"self"} );
				//Class test
				{
				IType classT18;
					IModelElement[] methodtest_getitemoverwriteiter17Childs = methodtest_getitemoverwriteiter17.getChildren();
					classT18 = ModelTestUtils.getAssertClass( methodtest_getitemoverwriteiter17Childs, "T" );
					//Function test:__getitem__
					{
					IMethod method__getitem__19;
						IModelElement[] classT18Childs = classT18.getChildren();
						method__getitem__19 = ModelTestUtils.getAssertMethod( classT18Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__19, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_repeat
			{
			IMethod methodtest_repeat20;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_repeat20 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat20, new String[] {"self"} );
			}
			//Function test:test_subscript
			{
			IMethod methodtest_subscript21;
				IModelElement[] classCommonTest0Childs = classCommonTest0.getChildren();
				methodtest_subscript21 = ModelTestUtils.getAssertMethod( classCommonTest0Childs, "test_subscript", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subscript21, new String[] {"self"} );
			}
		}

	}
	public void testModelGen109( ) throws Exception {
		String prj = "pytests";
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
			{
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractClass38Childs, "bases");
			}
			//Function test:getbases
			{
			IMethod methodgetbases41;
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				methodgetbases41 = ModelTestUtils.getAssertMethod( classAbstractClass38Childs, "getbases", 1 );
				ModelTestUtils.assertParameterNames( methodgetbases41, new String[] {"self"} );
			}
			{
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractClass38Childs, "__bases__");
			}
			//Function test:__call__
			{
			IMethod method__call__42;
				IModelElement[] classAbstractClass38Childs = classAbstractClass38.getChildren();
				method__call__42 = ModelTestUtils.getAssertMethod( classAbstractClass38Childs, "__call__", 1 );
				ModelTestUtils.assertParameterNames( method__call__42, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classAbstractInstance43;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractInstance43 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractInstance" );
			//Function test:__init__
			{
			IMethod method__init__44;
				IModelElement[] classAbstractInstance43Childs = classAbstractInstance43.getChildren();
				method__init__44 = ModelTestUtils.getAssertMethod( classAbstractInstance43Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__44, new String[] {"self", "klass"} );
			}
			{
				IModelElement[] classAbstractInstance43Childs = classAbstractInstance43.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractInstance43Childs, "klass");
			}
			//Function test:getclass
			{
			IMethod methodgetclass46;
				IModelElement[] classAbstractInstance43Childs = classAbstractInstance43.getChildren();
				methodgetclass46 = ModelTestUtils.getAssertMethod( classAbstractInstance43Childs, "getclass", 1 );
				ModelTestUtils.assertParameterNames( methodgetclass46, new String[] {"self"} );
			}
			{
				IModelElement[] classAbstractInstance43Childs = classAbstractInstance43.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractInstance43Childs, "__class__");
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
		IType classSuper47;
			IModelElement[] moduleChilds = module.getChildren();
			classSuper47 = ModelTestUtils.getAssertClass( moduleChilds, "Super" );
		}
		//Class test
		{
		IType classChild48;
			IModelElement[] moduleChilds = module.getChildren();
			classChild48 = ModelTestUtils.getAssertClass( moduleChilds, "Child" );
		}
		//Class test
		{
		IType classNewSuper49;
			IModelElement[] moduleChilds = module.getChildren();
			classNewSuper49 = ModelTestUtils.getAssertClass( moduleChilds, "NewSuper" );
		}
		//Class test
		{
		IType classNewChild50;
			IModelElement[] moduleChilds = module.getChildren();
			classNewChild50 = ModelTestUtils.getAssertClass( moduleChilds, "NewChild" );
		}
		//Class test
		{
		IType classTestIsInstanceIsSubclass51;
			IModelElement[] moduleChilds = module.getChildren();
			classTestIsInstanceIsSubclass51 = ModelTestUtils.getAssertClass( moduleChilds, "TestIsInstanceIsSubclass" );
			//Function test:test_isinstance_normal
			{
			IMethod methodtest_isinstance_normal52;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_isinstance_normal52 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_isinstance_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance_normal52, new String[] {"self"} );
			}
			//Function test:test_isinstance_abstract
			{
			IMethod methodtest_isinstance_abstract53;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_isinstance_abstract53 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_isinstance_abstract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance_abstract53, new String[] {"self"} );
			}
			//Function test:test_subclass_normal
			{
			IMethod methodtest_subclass_normal54;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_subclass_normal54 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_subclass_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_normal54, new String[] {"self"} );
			}
			//Function test:test_subclass_abstract
			{
			IMethod methodtest_subclass_abstract55;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_subclass_abstract55 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_subclass_abstract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_abstract55, new String[] {"self"} );
			}
			//Function test:test_subclass_tuple
			{
			IMethod methodtest_subclass_tuple56;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_subclass_tuple56 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_subclass_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_tuple56, new String[] {"self"} );
			}
			//Function test:test_subclass_recursion_limit
			{
			IMethod methodtest_subclass_recursion_limit57;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_subclass_recursion_limit57 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_subclass_recursion_limit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_recursion_limit57, new String[] {"self"} );
			}
			//Function test:test_isinstance_recursion_limit
			{
			IMethod methodtest_isinstance_recursion_limit58;
				IModelElement[] classTestIsInstanceIsSubclass51Childs = classTestIsInstanceIsSubclass51.getChildren();
				methodtest_isinstance_recursion_limit58 = ModelTestUtils.getAssertMethod( classTestIsInstanceIsSubclass51Childs, "test_isinstance_recursion_limit", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isinstance_recursion_limit58, new String[] {"self"} );
			}
		}
		//Function test:blowstack
		{
		IMethod methodblowstack59;
			IModelElement[] moduleChilds = module.getChildren();
			methodblowstack59 = ModelTestUtils.getAssertMethod( moduleChilds, "blowstack", 3 );
			ModelTestUtils.assertParameterNames( methodblowstack59, new String[] {"fxn", "arg", "compare_to"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main60;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main60 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen110( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFixedOffset2Childs, "__offset");
			}
			{
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFixedOffset2Childs, "__name");
			}
			{
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFixedOffset2Childs, "__dstoffset");
			}
			//Function test:__repr__
			{
			IMethod method__repr__5;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				method__repr__5 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__5, new String[] {"self"} );
			}
			//Function test:utcoffset
			{
			IMethod methodutcoffset6;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				methodutcoffset6 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "utcoffset", 2 );
				ModelTestUtils.assertParameterNames( methodutcoffset6, new String[] {"self", "dt"} );
			}
			//Function test:tzname
			{
			IMethod methodtzname7;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				methodtzname7 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "tzname", 2 );
				ModelTestUtils.assertParameterNames( methodtzname7, new String[] {"self", "dt"} );
			}
			//Function test:dst
			{
			IMethod methoddst8;
				IModelElement[] classFixedOffset2Childs = classFixedOffset2.getChildren();
				methoddst8 = ModelTestUtils.getAssertMethod( classFixedOffset2Childs, "dst", 2 );
				ModelTestUtils.assertParameterNames( methoddst8, new String[] {"self", "dt"} );
			}
		}
		//Class test
		{
		IType classPicklableFixedOffset9;
			IModelElement[] moduleChilds = module.getChildren();
			classPicklableFixedOffset9 = ModelTestUtils.getAssertClass( moduleChilds, "PicklableFixedOffset" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classPicklableFixedOffset9Childs = classPicklableFixedOffset9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classPicklableFixedOffset9Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self", "offset", "name", "dstoffset"} );
			}
		}
		//Class test
		{
		IType classTestTZInfo11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTZInfo11 = ModelTestUtils.getAssertClass( moduleChilds, "TestTZInfo" );
			//Function test:test_non_abstractness
			{
			IMethod methodtest_non_abstractness12;
				IModelElement[] classTestTZInfo11Childs = classTestTZInfo11.getChildren();
				methodtest_non_abstractness12 = ModelTestUtils.getAssertMethod( classTestTZInfo11Childs, "test_non_abstractness", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_abstractness12, new String[] {"self"} );
			}
			//Function test:test_subclass_must_override
			{
			IMethod methodtest_subclass_must_override13;
				IModelElement[] classTestTZInfo11Childs = classTestTZInfo11.getChildren();
				methodtest_subclass_must_override13 = ModelTestUtils.getAssertMethod( classTestTZInfo11Childs, "test_subclass_must_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_must_override13, new String[] {"self"} );
				//Class test
				{
				IType classNotEnough14;
					IModelElement[] methodtest_subclass_must_override13Childs = methodtest_subclass_must_override13.getChildren();
					classNotEnough14 = ModelTestUtils.getAssertClass( methodtest_subclass_must_override13Childs, "NotEnough" );
					//Function test:__init__
					{
					IMethod method__init__15;
						IModelElement[] classNotEnough14Childs = classNotEnough14.getChildren();
						method__init__15 = ModelTestUtils.getAssertMethod( classNotEnough14Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__15, new String[] {"self", "offset", "name"} );
					}
					{
						IModelElement[] classNotEnough14Childs = classNotEnough14.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classNotEnough14Childs, "__offset");
					}
					{
						IModelElement[] classNotEnough14Childs = classNotEnough14.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classNotEnough14Childs, "__name");
					}
				}
			}
			//Function test:test_normal
			{
			IMethod methodtest_normal17;
				IModelElement[] classTestTZInfo11Childs = classTestTZInfo11.getChildren();
				methodtest_normal17 = ModelTestUtils.getAssertMethod( classTestTZInfo11Childs, "test_normal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_normal17, new String[] {"self"} );
			}
			//Function test:test_pickling_base
			{
			IMethod methodtest_pickling_base18;
				IModelElement[] classTestTZInfo11Childs = classTestTZInfo11.getChildren();
				methodtest_pickling_base18 = ModelTestUtils.getAssertMethod( classTestTZInfo11Childs, "test_pickling_base", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_base18, new String[] {"self"} );
			}
			//Function test:test_pickling_subclass
			{
			IMethod methodtest_pickling_subclass19;
				IModelElement[] classTestTZInfo11Childs = classTestTZInfo11.getChildren();
				methodtest_pickling_subclass19 = ModelTestUtils.getAssertMethod( classTestTZInfo11Childs, "test_pickling_subclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classHarmlessMixedComparison20;
			IModelElement[] moduleChilds = module.getChildren();
			classHarmlessMixedComparison20 = ModelTestUtils.getAssertClass( moduleChilds, "HarmlessMixedComparison" );
			//Function test:test_harmless_mixed_comparison
			{
			IMethod methodtest_harmless_mixed_comparison21;
				IModelElement[] classHarmlessMixedComparison20Childs = classHarmlessMixedComparison20.getChildren();
				methodtest_harmless_mixed_comparison21 = ModelTestUtils.getAssertMethod( classHarmlessMixedComparison20Childs, "test_harmless_mixed_comparison", 1 );
				ModelTestUtils.assertParameterNames( methodtest_harmless_mixed_comparison21, new String[] {"self"} );
			}
			//Function test:test_harmful_mixed_comparison
			{
			IMethod methodtest_harmful_mixed_comparison22;
				IModelElement[] classHarmlessMixedComparison20Childs = classHarmlessMixedComparison20.getChildren();
				methodtest_harmful_mixed_comparison22 = ModelTestUtils.getAssertMethod( classHarmlessMixedComparison20Childs, "test_harmful_mixed_comparison", 1 );
				ModelTestUtils.assertParameterNames( methodtest_harmful_mixed_comparison22, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestTimeDelta23;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTimeDelta23 = ModelTestUtils.getAssertClass( moduleChilds, "TestTimeDelta" );
			{
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimeDelta23Childs, "theclass");
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor24;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_constructor24 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor24, new String[] {"self"} );
			}
			//Function test:test_computations
			{
			IMethod methodtest_computations25;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_computations25 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_computations25, new String[] {"self"} );
			}
			//Function test:test_disallowed_computations
			{
			IMethod methodtest_disallowed_computations26;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_disallowed_computations26 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_disallowed_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_disallowed_computations26, new String[] {"self"} );
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes27;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_basic_attributes27 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes27, new String[] {"self"} );
			}
			//Function test:test_carries
			{
			IMethod methodtest_carries28;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_carries28 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_carries", 1 );
				ModelTestUtils.assertParameterNames( methodtest_carries28, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality29;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_hash_equality29 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality29, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling30;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_pickling30 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling30, new String[] {"self"} );
			}
			//Function test:test_compare
			{
			IMethod methodtest_compare31;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_compare31 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compare31, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str32;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_str32 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str32, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip33;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_roundtrip33 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip33, new String[] {"self"} );
			}
			//Function test:test_resolution_info
			{
			IMethod methodtest_resolution_info34;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_resolution_info34 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_resolution_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_resolution_info34, new String[] {"self"} );
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow35;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_overflow35 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow35, new String[] {"self"} );
			}
			//Function test:test_microsecond_rounding
			{
			IMethod methodtest_microsecond_rounding36;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_microsecond_rounding36 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_microsecond_rounding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_microsecond_rounding36, new String[] {"self"} );
			}
			//Function test:test_massive_normalization
			{
			IMethod methodtest_massive_normalization37;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_massive_normalization37 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_massive_normalization", 1 );
				ModelTestUtils.assertParameterNames( methodtest_massive_normalization37, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool38;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_bool38 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool38, new String[] {"self"} );
			}
			//Function test:test_subclass_timedelta
			{
			IMethod methodtest_subclass_timedelta39;
				IModelElement[] classTestTimeDelta23Childs = classTestTimeDelta23.getChildren();
				methodtest_subclass_timedelta39 = ModelTestUtils.getAssertMethod( classTestTimeDelta23Childs, "test_subclass_timedelta", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_timedelta39, new String[] {"self"} );
				//Class test
				{
				IType classT40;
					IModelElement[] methodtest_subclass_timedelta39Childs = methodtest_subclass_timedelta39.getChildren();
					classT40 = ModelTestUtils.getAssertClass( methodtest_subclass_timedelta39Childs, "T" );
					//Function test:from_td
					{
					IMethod methodfrom_td41;
						IModelElement[] classT40Childs = classT40.getChildren();
						methodfrom_td41 = ModelTestUtils.getAssertMethod( classT40Childs, "from_td", 1 );
						ModelTestUtils.assertParameterNames( methodfrom_td41, new String[] {"td"} );
					}
					//Function test:as_hours
					{
					IMethod methodas_hours42;
						IModelElement[] classT40Childs = classT40.getChildren();
						methodas_hours42 = ModelTestUtils.getAssertMethod( classT40Childs, "as_hours", 1 );
						ModelTestUtils.assertParameterNames( methodas_hours42, new String[] {"self"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestDateOnly43;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDateOnly43 = ModelTestUtils.getAssertClass( moduleChilds, "TestDateOnly" );
			//Function test:test_delta_non_days_ignored
			{
			IMethod methodtest_delta_non_days_ignored44;
				IModelElement[] classTestDateOnly43Childs = classTestDateOnly43.getChildren();
				methodtest_delta_non_days_ignored44 = ModelTestUtils.getAssertMethod( classTestDateOnly43Childs, "test_delta_non_days_ignored", 1 );
				ModelTestUtils.assertParameterNames( methodtest_delta_non_days_ignored44, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSubclassDate45;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassDate45 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassDate" );
			{
				IModelElement[] classSubclassDate45Childs = classSubclassDate45.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassDate45Childs, "sub_var");
			}
		}
		//Class test
		{
		IType classTestDate46;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDate46 = ModelTestUtils.getAssertClass( moduleChilds, "TestDate" );
			{
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDate46Childs, "theclass");
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes47;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_basic_attributes47 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes47, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip48;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_roundtrip48 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip48, new String[] {"self"} );
			}
			//Function test:test_ordinal_conversions
			{
			IMethod methodtest_ordinal_conversions49;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_ordinal_conversions49 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_ordinal_conversions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ordinal_conversions49, new String[] {"self"} );
			}
			//Function test:test_extreme_ordinals
			{
			IMethod methodtest_extreme_ordinals50;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_extreme_ordinals50 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_extreme_ordinals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extreme_ordinals50, new String[] {"self"} );
			}
			//Function test:test_bad_constructor_arguments
			{
			IMethod methodtest_bad_constructor_arguments51;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_bad_constructor_arguments51 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_bad_constructor_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_constructor_arguments51, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality52;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_hash_equality52 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality52, new String[] {"self"} );
			}
			//Function test:test_computations
			{
			IMethod methodtest_computations53;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_computations53 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_computations53, new String[] {"self"} );
			}
			//Function test:test_overflow
			{
			IMethod methodtest_overflow54;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_overflow54 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_overflow54, new String[] {"self"} );
			}
			//Function test:test_fromtimestamp
			{
			IMethod methodtest_fromtimestamp55;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_fromtimestamp55 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromtimestamp55, new String[] {"self"} );
			}
			//Function test:test_insane_fromtimestamp
			{
			IMethod methodtest_insane_fromtimestamp56;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_insane_fromtimestamp56 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_insane_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_fromtimestamp56, new String[] {"self"} );
			}
			//Function test:test_today
			{
			IMethod methodtest_today57;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_today57 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_today", 1 );
				ModelTestUtils.assertParameterNames( methodtest_today57, new String[] {"self"} );
			}
			//Function test:test_weekday
			{
			IMethod methodtest_weekday58;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_weekday58 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_weekday", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weekday58, new String[] {"self"} );
			}
			//Function test:test_isocalendar
			{
			IMethod methodtest_isocalendar59;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_isocalendar59 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_isocalendar", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isocalendar59, new String[] {"self"} );
			}
			//Function test:test_iso_long_years
			{
			IMethod methodtest_iso_long_years60;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_iso_long_years60 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_iso_long_years", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iso_long_years60, new String[] {"self"} );
			}
			//Function test:test_isoformat
			{
			IMethod methodtest_isoformat61;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_isoformat61 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isoformat61, new String[] {"self"} );
			}
			//Function test:test_ctime
			{
			IMethod methodtest_ctime62;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_ctime62 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_ctime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ctime62, new String[] {"self"} );
			}
			//Function test:test_strftime
			{
			IMethod methodtest_strftime63;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_strftime63 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime63, new String[] {"self"} );
			}
			//Function test:test_resolution_info
			{
			IMethod methodtest_resolution_info64;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_resolution_info64 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_resolution_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_resolution_info64, new String[] {"self"} );
			}
			//Function test:test_extreme_timedelta
			{
			IMethod methodtest_extreme_timedelta65;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_extreme_timedelta65 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_extreme_timedelta", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extreme_timedelta65, new String[] {"self"} );
			}
			//Function test:test_timetuple
			{
			IMethod methodtest_timetuple66;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_timetuple66 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_timetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_timetuple66, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling67;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_pickling67 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling67, new String[] {"self"} );
			}
			//Function test:test_compare
			{
			IMethod methodtest_compare68;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_compare68 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_compare68, new String[] {"self"} );
			}
			//Function test:test_mixed_compare
			{
			IMethod methodtest_mixed_compare69;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_mixed_compare69 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_mixed_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_compare69, new String[] {"self"} );
				//Class test
				{
				IType classAnotherDateTimeClass70;
					IModelElement[] methodtest_mixed_compare69Childs = methodtest_mixed_compare69.getChildren();
					classAnotherDateTimeClass70 = ModelTestUtils.getAssertClass( methodtest_mixed_compare69Childs, "AnotherDateTimeClass" );
					//Function test:__cmp__
					{
					IMethod method__cmp__71;
						IModelElement[] classAnotherDateTimeClass70Childs = classAnotherDateTimeClass70.getChildren();
						method__cmp__71 = ModelTestUtils.getAssertMethod( classAnotherDateTimeClass70Childs, "__cmp__", 2 );
						ModelTestUtils.assertParameterNames( method__cmp__71, new String[] {"self", "other"} );
					}
				}
				//Class test
				{
				IType classComparable72;
					IModelElement[] methodtest_mixed_compare69Childs = methodtest_mixed_compare69.getChildren();
					classComparable72 = ModelTestUtils.getAssertClass( methodtest_mixed_compare69Childs, "Comparable" );
					//Function test:timetuple
					{
					IMethod methodtimetuple73;
						IModelElement[] classComparable72Childs = classComparable72.getChildren();
						methodtimetuple73 = ModelTestUtils.getAssertMethod( classComparable72Childs, "timetuple", 1 );
						ModelTestUtils.assertParameterNames( methodtimetuple73, new String[] {"self"} );
					}
				}
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool74;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_bool74 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool74, new String[] {"self"} );
			}
			//Function test:test_srftime_out_of_range
			{
			IMethod methodtest_srftime_out_of_range75;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_srftime_out_of_range75 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_srftime_out_of_range", 1 );
				ModelTestUtils.assertParameterNames( methodtest_srftime_out_of_range75, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace76;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_replace76 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace76, new String[] {"self"} );
			}
			//Function test:test_subclass_date
			{
			IMethod methodtest_subclass_date77;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_subclass_date77 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_subclass_date", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_date77, new String[] {"self"} );
				//Class test
				{
				IType classC78;
					IModelElement[] methodtest_subclass_date77Childs = methodtest_subclass_date77.getChildren();
					classC78 = ModelTestUtils.getAssertClass( methodtest_subclass_date77Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__79;
						IModelElement[] classC78Childs = classC78.getChildren();
						method__new__79 = ModelTestUtils.getAssertMethod( classC78Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__79, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth80;
						IModelElement[] classC78Childs = classC78.getChildren();
						methodnewmeth80 = ModelTestUtils.getAssertMethod( classC78Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth80, new String[] {"self", "start"} );
					}
				}
			}
			//Function test:test_pickling_subclass_date
			{
			IMethod methodtest_pickling_subclass_date81;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_pickling_subclass_date81 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_pickling_subclass_date", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass_date81, new String[] {"self"} );
			}
			//Function test:test_backdoor_resistance
			{
			IMethod methodtest_backdoor_resistance82;
				IModelElement[] classTestDate46Childs = classTestDate46.getChildren();
				methodtest_backdoor_resistance82 = ModelTestUtils.getAssertMethod( classTestDate46Childs, "test_backdoor_resistance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_backdoor_resistance82, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classSubclassDatetime83;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassDatetime83 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassDatetime" );
			{
				IModelElement[] classSubclassDatetime83Childs = classSubclassDatetime83.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassDatetime83Childs, "sub_var");
			}
		}
		//Class test
		{
		IType classTestDateTime84;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDateTime84 = ModelTestUtils.getAssertClass( moduleChilds, "TestDateTime" );
			{
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDateTime84Childs, "theclass");
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes85;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_basic_attributes85 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes85, new String[] {"self"} );
			}
			//Function test:test_basic_attributes_nonzero
			{
			IMethod methodtest_basic_attributes_nonzero86;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_basic_attributes_nonzero86 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_basic_attributes_nonzero", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes_nonzero86, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip87;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_roundtrip87 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip87, new String[] {"self"} );
			}
			//Function test:test_isoformat
			{
			IMethod methodtest_isoformat88;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_isoformat88 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isoformat88, new String[] {"self"} );
			}
			//Function test:test_more_ctime
			{
			IMethod methodtest_more_ctime89;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_more_ctime89 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_more_ctime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_ctime89, new String[] {"self"} );
			}
			//Function test:test_tz_independent_comparing
			{
			IMethod methodtest_tz_independent_comparing90;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_tz_independent_comparing90 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_tz_independent_comparing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tz_independent_comparing90, new String[] {"self"} );
			}
			//Function test:test_bad_constructor_arguments
			{
			IMethod methodtest_bad_constructor_arguments91;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_bad_constructor_arguments91 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_bad_constructor_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_constructor_arguments91, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality92;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_hash_equality92 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality92, new String[] {"self"} );
			}
			//Function test:test_computations
			{
			IMethod methodtest_computations93;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_computations93 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_computations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_computations93, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling94;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_pickling94 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling94, new String[] {"self"} );
			}
			//Function test:test_more_pickling
			{
			IMethod methodtest_more_pickling95;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_more_pickling95 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_more_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_pickling95, new String[] {"self"} );
			}
			//Function test:test_pickling_subclass_datetime
			{
			IMethod methodtest_pickling_subclass_datetime96;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_pickling_subclass_datetime96 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_pickling_subclass_datetime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass_datetime96, new String[] {"self"} );
			}
			//Function test:test_more_compare
			{
			IMethod methodtest_more_compare97;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_more_compare97 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_more_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_compare97, new String[] {"self"} );
			}
			//Function test:verify_field_equality
			{
			IMethod methodverify_field_equality98;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodverify_field_equality98 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "verify_field_equality", 3 );
				ModelTestUtils.assertParameterNames( methodverify_field_equality98, new String[] {"self", "expected", "got"} );
			}
			//Function test:test_fromtimestamp
			{
			IMethod methodtest_fromtimestamp99;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_fromtimestamp99 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromtimestamp99, new String[] {"self"} );
			}
			//Function test:test_utcfromtimestamp
			{
			IMethod methodtest_utcfromtimestamp100;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_utcfromtimestamp100 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_utcfromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utcfromtimestamp100, new String[] {"self"} );
			}
			//Function test:test_insane_fromtimestamp
			{
			IMethod methodtest_insane_fromtimestamp101;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_insane_fromtimestamp101 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_insane_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_fromtimestamp101, new String[] {"self"} );
			}
			//Function test:test_insane_utcfromtimestamp
			{
			IMethod methodtest_insane_utcfromtimestamp102;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_insane_utcfromtimestamp102 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_insane_utcfromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_utcfromtimestamp102, new String[] {"self"} );
			}
			//Function test:test_utcnow
			{
			IMethod methodtest_utcnow103;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_utcnow103 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_utcnow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utcnow103, new String[] {"self"} );
			}
			//Function test:test_more_timetuple
			{
			IMethod methodtest_more_timetuple104;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_more_timetuple104 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_more_timetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_timetuple104, new String[] {"self"} );
			}
			//Function test:test_more_strftime
			{
			IMethod methodtest_more_strftime105;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_more_strftime105 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_more_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_strftime105, new String[] {"self"} );
			}
			//Function test:test_extract
			{
			IMethod methodtest_extract106;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_extract106 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_extract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extract106, new String[] {"self"} );
			}
			//Function test:test_combine
			{
			IMethod methodtest_combine107;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_combine107 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_combine", 1 );
				ModelTestUtils.assertParameterNames( methodtest_combine107, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace108;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_replace108 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace108, new String[] {"self"} );
			}
			//Function test:test_astimezone
			{
			IMethod methodtest_astimezone109;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_astimezone109 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_astimezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_astimezone109, new String[] {"self"} );
				//Class test
				{
				IType classBogus110;
					IModelElement[] methodtest_astimezone109Childs = methodtest_astimezone109.getChildren();
					classBogus110 = ModelTestUtils.getAssertClass( methodtest_astimezone109Childs, "Bogus" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset111;
						IModelElement[] classBogus110Childs = classBogus110.getChildren();
						methodutcoffset111 = ModelTestUtils.getAssertMethod( classBogus110Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset111, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst112;
						IModelElement[] classBogus110Childs = classBogus110.getChildren();
						methoddst112 = ModelTestUtils.getAssertMethod( classBogus110Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst112, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classAlsoBogus113;
					IModelElement[] methodtest_astimezone109Childs = methodtest_astimezone109.getChildren();
					classAlsoBogus113 = ModelTestUtils.getAssertClass( methodtest_astimezone109Childs, "AlsoBogus" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset114;
						IModelElement[] classAlsoBogus113Childs = classAlsoBogus113.getChildren();
						methodutcoffset114 = ModelTestUtils.getAssertMethod( classAlsoBogus113Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset114, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst115;
						IModelElement[] classAlsoBogus113Childs = classAlsoBogus113.getChildren();
						methoddst115 = ModelTestUtils.getAssertMethod( classAlsoBogus113Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst115, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_subclass_datetime
			{
			IMethod methodtest_subclass_datetime116;
				IModelElement[] classTestDateTime84Childs = classTestDateTime84.getChildren();
				methodtest_subclass_datetime116 = ModelTestUtils.getAssertMethod( classTestDateTime84Childs, "test_subclass_datetime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_datetime116, new String[] {"self"} );
				//Class test
				{
				IType classC117;
					IModelElement[] methodtest_subclass_datetime116Childs = methodtest_subclass_datetime116.getChildren();
					classC117 = ModelTestUtils.getAssertClass( methodtest_subclass_datetime116Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__118;
						IModelElement[] classC117Childs = classC117.getChildren();
						method__new__118 = ModelTestUtils.getAssertMethod( classC117Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__118, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth119;
						IModelElement[] classC117Childs = classC117.getChildren();
						methodnewmeth119 = ModelTestUtils.getAssertMethod( classC117Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth119, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Class test
		{
		IType classSubclassTime120;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassTime120 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassTime" );
			{
				IModelElement[] classSubclassTime120Childs = classSubclassTime120.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassTime120Childs, "sub_var");
			}
		}
		//Class test
		{
		IType classTestTime121;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTime121 = ModelTestUtils.getAssertClass( moduleChilds, "TestTime" );
			{
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTime121Childs, "theclass");
			}
			//Function test:test_basic_attributes
			{
			IMethod methodtest_basic_attributes122;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_basic_attributes122 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_basic_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes122, new String[] {"self"} );
			}
			//Function test:test_basic_attributes_nonzero
			{
			IMethod methodtest_basic_attributes_nonzero123;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_basic_attributes_nonzero123 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_basic_attributes_nonzero", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_attributes_nonzero123, new String[] {"self"} );
			}
			//Function test:test_roundtrip
			{
			IMethod methodtest_roundtrip124;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_roundtrip124 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_roundtrip", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip124, new String[] {"self"} );
			}
			//Function test:test_comparing
			{
			IMethod methodtest_comparing125;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_comparing125 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_comparing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_comparing125, new String[] {"self"} );
			}
			//Function test:test_bad_constructor_arguments
			{
			IMethod methodtest_bad_constructor_arguments126;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_bad_constructor_arguments126 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_bad_constructor_arguments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_constructor_arguments126, new String[] {"self"} );
			}
			//Function test:test_hash_equality
			{
			IMethod methodtest_hash_equality127;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_hash_equality127 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_hash_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_equality127, new String[] {"self"} );
			}
			//Function test:test_isoformat
			{
			IMethod methodtest_isoformat128;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_isoformat128 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_isoformat128, new String[] {"self"} );
			}
			//Function test:test_strftime
			{
			IMethod methodtest_strftime129;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_strftime129 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime129, new String[] {"self"} );
			}
			//Function test:test_str
			{
			IMethod methodtest_str130;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_str130 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_str", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str130, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr131;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_repr131 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr131, new String[] {"self"} );
			}
			//Function test:test_resolution_info
			{
			IMethod methodtest_resolution_info132;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_resolution_info132 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_resolution_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_resolution_info132, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling133;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_pickling133 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling133, new String[] {"self"} );
			}
			//Function test:test_pickling_subclass_time
			{
			IMethod methodtest_pickling_subclass_time134;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_pickling_subclass_time134 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_pickling_subclass_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling_subclass_time134, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool135;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_bool135 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool135, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace136;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_replace136 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace136, new String[] {"self"} );
			}
			//Function test:test_subclass_time
			{
			IMethod methodtest_subclass_time137;
				IModelElement[] classTestTime121Childs = classTestTime121.getChildren();
				methodtest_subclass_time137 = ModelTestUtils.getAssertMethod( classTestTime121Childs, "test_subclass_time", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_time137, new String[] {"self"} );
				//Class test
				{
				IType classC138;
					IModelElement[] methodtest_subclass_time137Childs = methodtest_subclass_time137.getChildren();
					classC138 = ModelTestUtils.getAssertClass( methodtest_subclass_time137Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__139;
						IModelElement[] classC138Childs = classC138.getChildren();
						method__new__139 = ModelTestUtils.getAssertMethod( classC138Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__139, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth140;
						IModelElement[] classC138Childs = classC138.getChildren();
						methodnewmeth140 = ModelTestUtils.getAssertMethod( classC138Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth140, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTZInfoBase141;
			IModelElement[] moduleChilds = module.getChildren();
			classTZInfoBase141 = ModelTestUtils.getAssertClass( moduleChilds, "TZInfoBase" );
			//Function test:test_argument_passing
			{
			IMethod methodtest_argument_passing142;
				IModelElement[] classTZInfoBase141Childs = classTZInfoBase141.getChildren();
				methodtest_argument_passing142 = ModelTestUtils.getAssertMethod( classTZInfoBase141Childs, "test_argument_passing", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argument_passing142, new String[] {"self"} );
				//Class test
				{
				IType classintrospective143;
					IModelElement[] methodtest_argument_passing142Childs = methodtest_argument_passing142.getChildren();
					classintrospective143 = ModelTestUtils.getAssertClass( methodtest_argument_passing142Childs, "introspective" );
					//Function test:tzname
					{
					IMethod methodtzname144;
						IModelElement[] classintrospective143Childs = classintrospective143.getChildren();
						methodtzname144 = ModelTestUtils.getAssertMethod( classintrospective143Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname144, new String[] {"self", "dt"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset145;
						IModelElement[] classintrospective143Childs = classintrospective143.getChildren();
						methodutcoffset145 = ModelTestUtils.getAssertMethod( classintrospective143Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset145, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_bad_tzinfo_classes
			{
			IMethod methodtest_bad_tzinfo_classes146;
				IModelElement[] classTZInfoBase141Childs = classTZInfoBase141.getChildren();
				methodtest_bad_tzinfo_classes146 = ModelTestUtils.getAssertMethod( classTZInfoBase141Childs, "test_bad_tzinfo_classes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_tzinfo_classes146, new String[] {"self"} );
				//Class test
				{
				IType classNiceTry147;
					IModelElement[] methodtest_bad_tzinfo_classes146Childs = methodtest_bad_tzinfo_classes146.getChildren();
					classNiceTry147 = ModelTestUtils.getAssertClass( methodtest_bad_tzinfo_classes146Childs, "NiceTry" );
					//Function test:__init__
					{
					IMethod method__init__148;
						IModelElement[] classNiceTry147Childs = classNiceTry147.getChildren();
						method__init__148 = ModelTestUtils.getAssertMethod( classNiceTry147Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__148, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset149;
						IModelElement[] classNiceTry147Childs = classNiceTry147.getChildren();
						methodutcoffset149 = ModelTestUtils.getAssertMethod( classNiceTry147Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset149, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classBetterTry150;
					IModelElement[] methodtest_bad_tzinfo_classes146Childs = methodtest_bad_tzinfo_classes146.getChildren();
					classBetterTry150 = ModelTestUtils.getAssertClass( methodtest_bad_tzinfo_classes146Childs, "BetterTry" );
					//Function test:__init__
					{
					IMethod method__init__151;
						IModelElement[] classBetterTry150Childs = classBetterTry150.getChildren();
						method__init__151 = ModelTestUtils.getAssertMethod( classBetterTry150Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__151, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset152;
						IModelElement[] classBetterTry150Childs = classBetterTry150.getChildren();
						methodutcoffset152 = ModelTestUtils.getAssertMethod( classBetterTry150Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset152, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_utc_offset_out_of_bounds
			{
			IMethod methodtest_utc_offset_out_of_bounds153;
				IModelElement[] classTZInfoBase141Childs = classTZInfoBase141.getChildren();
				methodtest_utc_offset_out_of_bounds153 = ModelTestUtils.getAssertMethod( classTZInfoBase141Childs, "test_utc_offset_out_of_bounds", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utc_offset_out_of_bounds153, new String[] {"self"} );
				//Class test
				{
				IType classEdgy154;
					IModelElement[] methodtest_utc_offset_out_of_bounds153Childs = methodtest_utc_offset_out_of_bounds153.getChildren();
					classEdgy154 = ModelTestUtils.getAssertClass( methodtest_utc_offset_out_of_bounds153Childs, "Edgy" );
					//Function test:__init__
					{
					IMethod method__init__155;
						IModelElement[] classEdgy154Childs = classEdgy154.getChildren();
						method__init__155 = ModelTestUtils.getAssertMethod( classEdgy154Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__155, new String[] {"self", "offset"} );
					}
					{
						IModelElement[] classEdgy154Childs = classEdgy154.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classEdgy154Childs, "offset");
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset157;
						IModelElement[] classEdgy154Childs = classEdgy154.getChildren();
						methodutcoffset157 = ModelTestUtils.getAssertMethod( classEdgy154Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset157, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_tzinfo_classes
			{
			IMethod methodtest_tzinfo_classes158;
				IModelElement[] classTZInfoBase141Childs = classTZInfoBase141.getChildren();
				methodtest_tzinfo_classes158 = ModelTestUtils.getAssertMethod( classTZInfoBase141Childs, "test_tzinfo_classes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_classes158, new String[] {"self"} );
				//Class test
				{
				IType classC1159;
					IModelElement[] methodtest_tzinfo_classes158Childs = methodtest_tzinfo_classes158.getChildren();
					classC1159 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes158Childs, "C1" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset160;
						IModelElement[] classC1159Childs = classC1159.getChildren();
						methodutcoffset160 = ModelTestUtils.getAssertMethod( classC1159Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset160, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst161;
						IModelElement[] classC1159Childs = classC1159.getChildren();
						methoddst161 = ModelTestUtils.getAssertMethod( classC1159Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst161, new String[] {"self", "dt"} );
					}
					//Function test:tzname
					{
					IMethod methodtzname162;
						IModelElement[] classC1159Childs = classC1159.getChildren();
						methodtzname162 = ModelTestUtils.getAssertMethod( classC1159Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname162, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC3163;
					IModelElement[] methodtest_tzinfo_classes158Childs = methodtest_tzinfo_classes158.getChildren();
					classC3163 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes158Childs, "C3" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset164;
						IModelElement[] classC3163Childs = classC3163.getChildren();
						methodutcoffset164 = ModelTestUtils.getAssertMethod( classC3163Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset164, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst165;
						IModelElement[] classC3163Childs = classC3163.getChildren();
						methoddst165 = ModelTestUtils.getAssertMethod( classC3163Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst165, new String[] {"self", "dt"} );
					}
					//Function test:tzname
					{
					IMethod methodtzname166;
						IModelElement[] classC3163Childs = classC3163.getChildren();
						methodtzname166 = ModelTestUtils.getAssertMethod( classC3163Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname166, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC4167;
					IModelElement[] methodtest_tzinfo_classes158Childs = methodtest_tzinfo_classes158.getChildren();
					classC4167 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes158Childs, "C4" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset168;
						IModelElement[] classC4167Childs = classC4167.getChildren();
						methodutcoffset168 = ModelTestUtils.getAssertMethod( classC4167Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset168, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst169;
						IModelElement[] classC4167Childs = classC4167.getChildren();
						methoddst169 = ModelTestUtils.getAssertMethod( classC4167Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst169, new String[] {"self", "dt"} );
					}
					//Function test:tzname
					{
					IMethod methodtzname170;
						IModelElement[] classC4167Childs = classC4167.getChildren();
						methodtzname170 = ModelTestUtils.getAssertMethod( classC4167Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname170, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC6171;
					IModelElement[] methodtest_tzinfo_classes158Childs = methodtest_tzinfo_classes158.getChildren();
					classC6171 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes158Childs, "C6" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset172;
						IModelElement[] classC6171Childs = classC6171.getChildren();
						methodutcoffset172 = ModelTestUtils.getAssertMethod( classC6171Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset172, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst173;
						IModelElement[] classC6171Childs = classC6171.getChildren();
						methoddst173 = ModelTestUtils.getAssertMethod( classC6171Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst173, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classC7174;
					IModelElement[] methodtest_tzinfo_classes158Childs = methodtest_tzinfo_classes158.getChildren();
					classC7174 = ModelTestUtils.getAssertClass( methodtest_tzinfo_classes158Childs, "C7" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset175;
						IModelElement[] classC7174Childs = classC7174.getChildren();
						methodutcoffset175 = ModelTestUtils.getAssertMethod( classC7174Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset175, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst176;
						IModelElement[] classC7174Childs = classC7174.getChildren();
						methoddst176 = ModelTestUtils.getAssertMethod( classC7174Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst176, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_aware_compare
			{
			IMethod methodtest_aware_compare177;
				IModelElement[] classTZInfoBase141Childs = classTZInfoBase141.getChildren();
				methodtest_aware_compare177 = ModelTestUtils.getAssertMethod( classTZInfoBase141Childs, "test_aware_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_aware_compare177, new String[] {"self"} );
				//Class test
				{
				IType classOperandDependentOffset178;
					IModelElement[] methodtest_aware_compare177Childs = methodtest_aware_compare177.getChildren();
					classOperandDependentOffset178 = ModelTestUtils.getAssertClass( methodtest_aware_compare177Childs, "OperandDependentOffset" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset179;
						IModelElement[] classOperandDependentOffset178Childs = classOperandDependentOffset178.getChildren();
						methodutcoffset179 = ModelTestUtils.getAssertMethod( classOperandDependentOffset178Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset179, new String[] {"self", "t"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestTimeTZ180;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTimeTZ180 = ModelTestUtils.getAssertClass( moduleChilds, "TestTimeTZ" );
			{
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimeTZ180Childs, "theclass");
			}
			//Function test:test_empty
			{
			IMethod methodtest_empty181;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_empty181 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_empty", 1 );
				ModelTestUtils.assertParameterNames( methodtest_empty181, new String[] {"self"} );
			}
			//Function test:test_zones
			{
			IMethod methodtest_zones182;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_zones182 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_zones", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zones182, new String[] {"self"} );
				//Class test
				{
				IType classBadtzname183;
					IModelElement[] methodtest_zones182Childs = methodtest_zones182.getChildren();
					classBadtzname183 = ModelTestUtils.getAssertClass( methodtest_zones182Childs, "Badtzname" );
					//Function test:tzname
					{
					IMethod methodtzname184;
						IModelElement[] classBadtzname183Childs = classBadtzname183.getChildren();
						methodtzname184 = ModelTestUtils.getAssertMethod( classBadtzname183Childs, "tzname", 2 );
						ModelTestUtils.assertParameterNames( methodtzname184, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_hash_edge_cases
			{
			IMethod methodtest_hash_edge_cases185;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_hash_edge_cases185 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_hash_edge_cases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash_edge_cases185, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling186;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_pickling186 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling186, new String[] {"self"} );
			}
			//Function test:test_more_bool
			{
			IMethod methodtest_more_bool187;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_more_bool187 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_more_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_bool187, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace188;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_replace188 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace188, new String[] {"self"} );
			}
			//Function test:test_mixed_compare
			{
			IMethod methodtest_mixed_compare189;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_mixed_compare189 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_mixed_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_compare189, new String[] {"self"} );
				//Class test
				{
				IType classVaries190;
					IModelElement[] methodtest_mixed_compare189Childs = methodtest_mixed_compare189.getChildren();
					classVaries190 = ModelTestUtils.getAssertClass( methodtest_mixed_compare189Childs, "Varies" );
					//Function test:__init__
					{
					IMethod method__init__191;
						IModelElement[] classVaries190Childs = classVaries190.getChildren();
						method__init__191 = ModelTestUtils.getAssertMethod( classVaries190Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__191, new String[] {"self"} );
					}
					{
						IModelElement[] classVaries190Childs = classVaries190.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classVaries190Childs, "offset");
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset193;
						IModelElement[] classVaries190Childs = classVaries190.getChildren();
						methodutcoffset193 = ModelTestUtils.getAssertMethod( classVaries190Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset193, new String[] {"self", "t"} );
					}
				}
			}
			//Function test:test_subclass_timetz
			{
			IMethod methodtest_subclass_timetz194;
				IModelElement[] classTestTimeTZ180Childs = classTestTimeTZ180.getChildren();
				methodtest_subclass_timetz194 = ModelTestUtils.getAssertMethod( classTestTimeTZ180Childs, "test_subclass_timetz", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_timetz194, new String[] {"self"} );
				//Class test
				{
				IType classC195;
					IModelElement[] methodtest_subclass_timetz194Childs = methodtest_subclass_timetz194.getChildren();
					classC195 = ModelTestUtils.getAssertClass( methodtest_subclass_timetz194Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__196;
						IModelElement[] classC195Childs = classC195.getChildren();
						method__new__196 = ModelTestUtils.getAssertMethod( classC195Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__196, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth197;
						IModelElement[] classC195Childs = classC195.getChildren();
						methodnewmeth197 = ModelTestUtils.getAssertMethod( classC195Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth197, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Class test
		{
		IType classTestDateTimeTZ198;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDateTimeTZ198 = ModelTestUtils.getAssertClass( moduleChilds, "TestDateTimeTZ" );
			{
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDateTimeTZ198Childs, "theclass");
			}
			//Function test:test_trivial
			{
			IMethod methodtest_trivial199;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_trivial199 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_trivial", 1 );
				ModelTestUtils.assertParameterNames( methodtest_trivial199, new String[] {"self"} );
			}
			//Function test:test_even_more_compare
			{
			IMethod methodtest_even_more_compare200;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_even_more_compare200 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_even_more_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_even_more_compare200, new String[] {"self"} );
				//Class test
				{
				IType classNaive201;
					IModelElement[] methodtest_even_more_compare200Childs = methodtest_even_more_compare200.getChildren();
					classNaive201 = ModelTestUtils.getAssertClass( methodtest_even_more_compare200Childs, "Naive" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset202;
						IModelElement[] classNaive201Childs = classNaive201.getChildren();
						methodutcoffset202 = ModelTestUtils.getAssertMethod( classNaive201Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset202, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classBogus203;
					IModelElement[] methodtest_even_more_compare200Childs = methodtest_even_more_compare200.getChildren();
					classBogus203 = ModelTestUtils.getAssertClass( methodtest_even_more_compare200Childs, "Bogus" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset204;
						IModelElement[] classBogus203Childs = classBogus203.getChildren();
						methodutcoffset204 = ModelTestUtils.getAssertMethod( classBogus203Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset204, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling205;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_pickling205 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling205, new String[] {"self"} );
			}
			//Function test:test_extreme_hashes
			{
			IMethod methodtest_extreme_hashes206;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_extreme_hashes206 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_extreme_hashes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extreme_hashes206, new String[] {"self"} );
			}
			//Function test:test_zones
			{
			IMethod methodtest_zones207;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_zones207 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_zones", 1 );
				ModelTestUtils.assertParameterNames( methodtest_zones207, new String[] {"self"} );
			}
			//Function test:test_combine
			{
			IMethod methodtest_combine208;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_combine208 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_combine", 1 );
				ModelTestUtils.assertParameterNames( methodtest_combine208, new String[] {"self"} );
			}
			//Function test:test_extract
			{
			IMethod methodtest_extract209;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_extract209 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_extract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_extract209, new String[] {"self"} );
			}
			//Function test:test_tz_aware_arithmetic
			{
			IMethod methodtest_tz_aware_arithmetic210;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tz_aware_arithmetic210 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tz_aware_arithmetic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tz_aware_arithmetic210, new String[] {"self"} );
			}
			//Function test:test_tzinfo_now
			{
			IMethod methodtest_tzinfo_now211;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tzinfo_now211 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tzinfo_now", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_now211, new String[] {"self"} );
			}
			//Function test:test_tzinfo_fromtimestamp
			{
			IMethod methodtest_tzinfo_fromtimestamp212;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tzinfo_fromtimestamp212 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tzinfo_fromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_fromtimestamp212, new String[] {"self"} );
			}
			//Function test:test_tzinfo_utcnow
			{
			IMethod methodtest_tzinfo_utcnow213;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tzinfo_utcnow213 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tzinfo_utcnow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_utcnow213, new String[] {"self"} );
			}
			//Function test:test_tzinfo_utcfromtimestamp
			{
			IMethod methodtest_tzinfo_utcfromtimestamp214;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tzinfo_utcfromtimestamp214 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tzinfo_utcfromtimestamp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_utcfromtimestamp214, new String[] {"self"} );
			}
			//Function test:test_tzinfo_timetuple
			{
			IMethod methodtest_tzinfo_timetuple215;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tzinfo_timetuple215 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tzinfo_timetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_timetuple215, new String[] {"self"} );
				//Class test
				{
				IType classDST216;
					IModelElement[] methodtest_tzinfo_timetuple215Childs = methodtest_tzinfo_timetuple215.getChildren();
					classDST216 = ModelTestUtils.getAssertClass( methodtest_tzinfo_timetuple215Childs, "DST" );
					//Function test:__init__
					{
					IMethod method__init__217;
						IModelElement[] classDST216Childs = classDST216.getChildren();
						method__init__217 = ModelTestUtils.getAssertMethod( classDST216Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__217, new String[] {"self", "dstvalue"} );
					}
					{
						IModelElement[] classDST216Childs = classDST216.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classDST216Childs, "dstvalue");
					}
					//Function test:dst
					{
					IMethod methoddst219;
						IModelElement[] classDST216Childs = classDST216.getChildren();
						methoddst219 = ModelTestUtils.getAssertMethod( classDST216Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst219, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_utctimetuple
			{
			IMethod methodtest_utctimetuple220;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_utctimetuple220 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_utctimetuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utctimetuple220, new String[] {"self"} );
				//Class test
				{
				IType classDST221;
					IModelElement[] methodtest_utctimetuple220Childs = methodtest_utctimetuple220.getChildren();
					classDST221 = ModelTestUtils.getAssertClass( methodtest_utctimetuple220Childs, "DST" );
					//Function test:__init__
					{
					IMethod method__init__222;
						IModelElement[] classDST221Childs = classDST221.getChildren();
						method__init__222 = ModelTestUtils.getAssertMethod( classDST221Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__222, new String[] {"self", "dstvalue"} );
					}
					//Function test:dst
					{
					IMethod methoddst223;
						IModelElement[] classDST221Childs = classDST221.getChildren();
						methoddst223 = ModelTestUtils.getAssertMethod( classDST221Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst223, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classUOFS224;
					IModelElement[] methodtest_utctimetuple220Childs = methodtest_utctimetuple220.getChildren();
					classUOFS224 = ModelTestUtils.getAssertClass( methodtest_utctimetuple220Childs, "UOFS" );
					//Function test:__init__
					{
					IMethod method__init__225;
						IModelElement[] classUOFS224Childs = classUOFS224.getChildren();
						method__init__225 = ModelTestUtils.getAssertMethod( classUOFS224Childs, "__init__", 3 );
						ModelTestUtils.assertParameterNames( method__init__225, new String[] {"self", "uofs", "dofs"} );
					}
					{
						IModelElement[] classUOFS224Childs = classUOFS224.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classUOFS224Childs, "uofs");
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset227;
						IModelElement[] classUOFS224Childs = classUOFS224.getChildren();
						methodutcoffset227 = ModelTestUtils.getAssertMethod( classUOFS224Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset227, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_tzinfo_isoformat
			{
			IMethod methodtest_tzinfo_isoformat228;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_tzinfo_isoformat228 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_tzinfo_isoformat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzinfo_isoformat228, new String[] {"self"} );
			}
			//Function test:test_replace
			{
			IMethod methodtest_replace229;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_replace229 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_replace", 1 );
				ModelTestUtils.assertParameterNames( methodtest_replace229, new String[] {"self"} );
			}
			//Function test:test_more_astimezone
			{
			IMethod methodtest_more_astimezone230;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_more_astimezone230 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_more_astimezone", 1 );
				ModelTestUtils.assertParameterNames( methodtest_more_astimezone230, new String[] {"self"} );
			}
			//Function test:test_aware_subtract
			{
			IMethod methodtest_aware_subtract231;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_aware_subtract231 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_aware_subtract", 1 );
				ModelTestUtils.assertParameterNames( methodtest_aware_subtract231, new String[] {"self"} );
				//Class test
				{
				IType classOperandDependentOffset232;
					IModelElement[] methodtest_aware_subtract231Childs = methodtest_aware_subtract231.getChildren();
					classOperandDependentOffset232 = ModelTestUtils.getAssertClass( methodtest_aware_subtract231Childs, "OperandDependentOffset" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset233;
						IModelElement[] classOperandDependentOffset232Childs = classOperandDependentOffset232.getChildren();
						methodutcoffset233 = ModelTestUtils.getAssertMethod( classOperandDependentOffset232Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset233, new String[] {"self", "t"} );
					}
				}
			}
			//Function test:test_mixed_compare
			{
			IMethod methodtest_mixed_compare234;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_mixed_compare234 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_mixed_compare", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mixed_compare234, new String[] {"self"} );
				//Class test
				{
				IType classVaries235;
					IModelElement[] methodtest_mixed_compare234Childs = methodtest_mixed_compare234.getChildren();
					classVaries235 = ModelTestUtils.getAssertClass( methodtest_mixed_compare234Childs, "Varies" );
					//Function test:__init__
					{
					IMethod method__init__236;
						IModelElement[] classVaries235Childs = classVaries235.getChildren();
						method__init__236 = ModelTestUtils.getAssertMethod( classVaries235Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__236, new String[] {"self"} );
					}
					//Function test:utcoffset
					{
					IMethod methodutcoffset237;
						IModelElement[] classVaries235Childs = classVaries235.getChildren();
						methodutcoffset237 = ModelTestUtils.getAssertMethod( classVaries235Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset237, new String[] {"self", "t"} );
					}
				}
			}
			//Function test:test_subclass_datetimetz
			{
			IMethod methodtest_subclass_datetimetz238;
				IModelElement[] classTestDateTimeTZ198Childs = classTestDateTimeTZ198.getChildren();
				methodtest_subclass_datetimetz238 = ModelTestUtils.getAssertMethod( classTestDateTimeTZ198Childs, "test_subclass_datetimetz", 1 );
				ModelTestUtils.assertParameterNames( methodtest_subclass_datetimetz238, new String[] {"self"} );
				//Class test
				{
				IType classC239;
					IModelElement[] methodtest_subclass_datetimetz238Childs = methodtest_subclass_datetimetz238.getChildren();
					classC239 = ModelTestUtils.getAssertClass( methodtest_subclass_datetimetz238Childs, "C" );
					//Function test:__new__
					{
					IMethod method__new__240;
						IModelElement[] classC239Childs = classC239.getChildren();
						method__new__240 = ModelTestUtils.getAssertMethod( classC239Childs, "__new__", 3 );
						ModelTestUtils.assertParameterNames( method__new__240, new String[] {"cls", "args", "kws"} );
					}
					//Function test:newmeth
					{
					IMethod methodnewmeth241;
						IModelElement[] classC239Childs = classC239.getChildren();
						methodnewmeth241 = ModelTestUtils.getAssertMethod( classC239Childs, "newmeth", 2 );
						ModelTestUtils.assertParameterNames( methodnewmeth241, new String[] {"self", "start"} );
					}
				}
			}
		}
		//Function test:first_sunday_on_or_after
		{
		IMethod methodfirst_sunday_on_or_after242;
			IModelElement[] moduleChilds = module.getChildren();
			methodfirst_sunday_on_or_after242 = ModelTestUtils.getAssertMethod( moduleChilds, "first_sunday_on_or_after", 1 );
			ModelTestUtils.assertParameterNames( methodfirst_sunday_on_or_after242, new String[] {"dt"} );
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
		IType classUSTimeZone243;
			IModelElement[] moduleChilds = module.getChildren();
			classUSTimeZone243 = ModelTestUtils.getAssertClass( moduleChilds, "USTimeZone" );
			//Function test:__init__
			{
			IMethod method__init__244;
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				method__init__244 = ModelTestUtils.getAssertMethod( classUSTimeZone243Childs, "__init__", 5 );
				ModelTestUtils.assertParameterNames( method__init__244, new String[] {"self", "hours", "reprname", "stdname", "dstname"} );
			}
			{
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUSTimeZone243Childs, "stdoffset");
			}
			{
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUSTimeZone243Childs, "reprname");
			}
			{
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUSTimeZone243Childs, "stdname");
			}
			{
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUSTimeZone243Childs, "dstname");
			}
			//Function test:__repr__
			{
			IMethod method__repr__246;
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				method__repr__246 = ModelTestUtils.getAssertMethod( classUSTimeZone243Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__246, new String[] {"self"} );
			}
			//Function test:tzname
			{
			IMethod methodtzname247;
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				methodtzname247 = ModelTestUtils.getAssertMethod( classUSTimeZone243Childs, "tzname", 2 );
				ModelTestUtils.assertParameterNames( methodtzname247, new String[] {"self", "dt"} );
			}
			//Function test:utcoffset
			{
			IMethod methodutcoffset248;
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				methodutcoffset248 = ModelTestUtils.getAssertMethod( classUSTimeZone243Childs, "utcoffset", 2 );
				ModelTestUtils.assertParameterNames( methodutcoffset248, new String[] {"self", "dt"} );
			}
			//Function test:dst
			{
			IMethod methoddst249;
				IModelElement[] classUSTimeZone243Childs = classUSTimeZone243.getChildren();
				methoddst249 = ModelTestUtils.getAssertMethod( classUSTimeZone243Childs, "dst", 2 );
				ModelTestUtils.assertParameterNames( methoddst249, new String[] {"self", "dt"} );
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
		IType classTestTimezoneConversions250;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTimezoneConversions250 = ModelTestUtils.getAssertClass( moduleChilds, "TestTimezoneConversions" );
			{
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimezoneConversions250Childs, "dston");
			}
			{
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimezoneConversions250Childs, "dstoff");
			}
			{
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTimezoneConversions250Childs, "theclass");
			}
			//Function test:checkinside
			{
			IMethod methodcheckinside251;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodcheckinside251 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "checkinside", 6 );
				ModelTestUtils.assertParameterNames( methodcheckinside251, new String[] {"self", "dt", "tz", "utc", "dston", "dstoff"} );
			}
			//Function test:checkoutside
			{
			IMethod methodcheckoutside252;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodcheckoutside252 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "checkoutside", 4 );
				ModelTestUtils.assertParameterNames( methodcheckoutside252, new String[] {"self", "dt", "tz", "utc"} );
			}
			//Function test:convert_between_tz_and_utc
			{
			IMethod methodconvert_between_tz_and_utc253;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodconvert_between_tz_and_utc253 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "convert_between_tz_and_utc", 3 );
				ModelTestUtils.assertParameterNames( methodconvert_between_tz_and_utc253, new String[] {"self", "tz", "utc"} );
			}
			//Function test:test_easy
			{
			IMethod methodtest_easy254;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodtest_easy254 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "test_easy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_easy254, new String[] {"self"} );
			}
			//Function test:test_tricky
			{
			IMethod methodtest_tricky255;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodtest_tricky255 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "test_tricky", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tricky255, new String[] {"self"} );
			}
			//Function test:test_bogus_dst
			{
			IMethod methodtest_bogus_dst256;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodtest_bogus_dst256 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "test_bogus_dst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bogus_dst256, new String[] {"self"} );
				//Class test
				{
				IType classok257;
					IModelElement[] methodtest_bogus_dst256Childs = methodtest_bogus_dst256.getChildren();
					classok257 = ModelTestUtils.getAssertClass( methodtest_bogus_dst256Childs, "ok" );
					//Function test:utcoffset
					{
					IMethod methodutcoffset258;
						IModelElement[] classok257Childs = classok257.getChildren();
						methodutcoffset258 = ModelTestUtils.getAssertMethod( classok257Childs, "utcoffset", 2 );
						ModelTestUtils.assertParameterNames( methodutcoffset258, new String[] {"self", "dt"} );
					}
					//Function test:dst
					{
					IMethod methoddst259;
						IModelElement[] classok257Childs = classok257.getChildren();
						methoddst259 = ModelTestUtils.getAssertMethod( classok257Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst259, new String[] {"self", "dt"} );
					}
				}
				//Class test
				{
				IType classnotok260;
					IModelElement[] methodtest_bogus_dst256Childs = methodtest_bogus_dst256.getChildren();
					classnotok260 = ModelTestUtils.getAssertClass( methodtest_bogus_dst256Childs, "notok" );
					//Function test:dst
					{
					IMethod methoddst261;
						IModelElement[] classnotok260Childs = classnotok260.getChildren();
						methoddst261 = ModelTestUtils.getAssertMethod( classnotok260Childs, "dst", 2 );
						ModelTestUtils.assertParameterNames( methoddst261, new String[] {"self", "dt"} );
					}
				}
			}
			//Function test:test_fromutc
			{
			IMethod methodtest_fromutc262;
				IModelElement[] classTestTimezoneConversions250Childs = classTestTimezoneConversions250.getChildren();
				methodtest_fromutc262 = ModelTestUtils.getAssertMethod( classTestTimezoneConversions250Childs, "test_fromutc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromutc262, new String[] {"self"} );
				//Class test
				{
				IType classFauxUSTimeZone263;
					IModelElement[] methodtest_fromutc262Childs = methodtest_fromutc262.getChildren();
					classFauxUSTimeZone263 = ModelTestUtils.getAssertClass( methodtest_fromutc262Childs, "FauxUSTimeZone" );
					//Function test:fromutc
					{
					IMethod methodfromutc264;
						IModelElement[] classFauxUSTimeZone263Childs = classFauxUSTimeZone263.getChildren();
						methodfromutc264 = ModelTestUtils.getAssertMethod( classFauxUSTimeZone263Childs, "fromutc", 2 );
						ModelTestUtils.assertParameterNames( methodfromutc264, new String[] {"self", "dt"} );
					}
				}
			}
		}
		//Class test
		{
		IType classOddballs265;
			IModelElement[] moduleChilds = module.getChildren();
			classOddballs265 = ModelTestUtils.getAssertClass( moduleChilds, "Oddballs" );
			//Function test:test_bug_1028306
			{
			IMethod methodtest_bug_1028306266;
				IModelElement[] classOddballs265Childs = classOddballs265.getChildren();
				methodtest_bug_1028306266 = ModelTestUtils.getAssertMethod( classOddballs265Childs, "test_bug_1028306", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_1028306266, new String[] {"self"} );
			}
		}
		//Function test:test_suite
		{
		IMethod methodtest_suite267;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_suite267 = ModelTestUtils.getAssertMethod( moduleChilds, "test_suite", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main268;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main268 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen113( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classUnlinkingLogReader1Childs = classUnlinkingLogReader1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUnlinkingLogReader1Childs, "__logfn");
			}
			//Function test:next
			{
			IMethod methodnext4;
				IModelElement[] classUnlinkingLogReader1Childs = classUnlinkingLogReader1.getChildren();
				methodnext4 = ModelTestUtils.getAssertMethod( classUnlinkingLogReader1Childs, "next", 2 );
				ModelTestUtils.assertParameterNames( methodnext4, new String[] {"self", "index"} );
			}
		}
		//Class test
		{
		IType classHotShotTestCase5;
			IModelElement[] moduleChilds = module.getChildren();
			classHotShotTestCase5 = ModelTestUtils.getAssertClass( moduleChilds, "HotShotTestCase" );
			//Function test:new_profiler
			{
			IMethod methodnew_profiler6;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodnew_profiler6 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "new_profiler", 3 );
				ModelTestUtils.assertParameterNames( methodnew_profiler6, new String[] {"self", "lineevents", "linetimings"} );
			}
			{
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHotShotTestCase5Childs, "logfn");
			}
			//Function test:get_logreader
			{
			IMethod methodget_logreader8;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodget_logreader8 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "get_logreader", 1 );
				ModelTestUtils.assertParameterNames( methodget_logreader8, new String[] {"self"} );
			}
			//Function test:get_events_wotime
			{
			IMethod methodget_events_wotime9;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodget_events_wotime9 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "get_events_wotime", 1 );
				ModelTestUtils.assertParameterNames( methodget_events_wotime9, new String[] {"self"} );
			}
			//Function test:check_events
			{
			IMethod methodcheck_events10;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodcheck_events10 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "check_events", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_events10, new String[] {"self", "expected"} );
			}
			//Function test:run_test
			{
			IMethod methodrun_test11;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodrun_test11 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "run_test", 4 );
				ModelTestUtils.assertParameterNames( methodrun_test11, new String[] {"self", "callable", "events", "profiler"} );
			}
			//Function test:test_addinfo
			{
			IMethod methodtest_addinfo12;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodtest_addinfo12 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "test_addinfo", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addinfo12, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf13;
					IModelElement[] methodtest_addinfo12Childs = methodtest_addinfo12.getChildren();
					methodf13 = ModelTestUtils.getAssertMethod( methodtest_addinfo12Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf13, new String[] {"p"} );
				}
			}
			//Function test:test_line_numbers
			{
			IMethod methodtest_line_numbers14;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodtest_line_numbers14 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "test_line_numbers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_line_numbers14, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf15;
					IModelElement[] methodtest_line_numbers14Childs = methodtest_line_numbers14.getChildren();
					methodf15 = ModelTestUtils.getAssertMethod( methodtest_line_numbers14Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg16;
					IModelElement[] methodtest_line_numbers14Childs = methodtest_line_numbers14.getChildren();
					methodg16 = ModelTestUtils.getAssertMethod( methodtest_line_numbers14Childs, "g", 0 );
				}
			}
			//Function test:test_start_stop
			{
			IMethod methodtest_start_stop17;
				IModelElement[] classHotShotTestCase5Childs = classHotShotTestCase5.getChildren();
				methodtest_start_stop17 = ModelTestUtils.getAssertMethod( classHotShotTestCase5Childs, "test_start_stop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_start_stop17, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main18 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen115( ) throws Exception {
		String prj = "pytests";
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
			{
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBSDDB0Childs, "f");
			}
			{
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBSDDB0Childs, "d");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem4;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_getitem4 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem4, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len5;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_len5 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len5, new String[] {"self"} );
			}
			//Function test:test_change
			{
			IMethod methodtest_change6;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_change6 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_change", 1 );
				ModelTestUtils.assertParameterNames( methodtest_change6, new String[] {"self"} );
			}
			//Function test:test_close_and_reopen
			{
			IMethod methodtest_close_and_reopen7;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_close_and_reopen7 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_close_and_reopen", 1 );
				ModelTestUtils.assertParameterNames( methodtest_close_and_reopen7, new String[] {"self"} );
			}
			{
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBSDDB0Childs, "f");
			}
			//Function test:assertSetEquals
			{
			IMethod methodassertSetEquals9;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodassertSetEquals9 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "assertSetEquals", 3 );
				ModelTestUtils.assertParameterNames( methodassertSetEquals9, new String[] {"self", "seqn1", "seqn2"} );
			}
			//Function test:test_mapping_iteration_methods
			{
			IMethod methodtest_mapping_iteration_methods10;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_mapping_iteration_methods10 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_mapping_iteration_methods", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mapping_iteration_methods10, new String[] {"self"} );
			}
			//Function test:test_iter_while_modifying_values
			{
			IMethod methodtest_iter_while_modifying_values11;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_iter_while_modifying_values11 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_iter_while_modifying_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter_while_modifying_values11, new String[] {"self"} );
			}
			//Function test:test_iteritems_while_modifying_values
			{
			IMethod methodtest_iteritems_while_modifying_values12;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_iteritems_while_modifying_values12 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_iteritems_while_modifying_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteritems_while_modifying_values12, new String[] {"self"} );
			}
			//Function test:test_first_next_looping
			{
			IMethod methodtest_first_next_looping13;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_first_next_looping13 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_first_next_looping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_first_next_looping13, new String[] {"self"} );
			}
			//Function test:test_previous_last_looping
			{
			IMethod methodtest_previous_last_looping14;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_previous_last_looping14 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_previous_last_looping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_previous_last_looping14, new String[] {"self"} );
			}
			//Function test:test_set_location
			{
			IMethod methodtest_set_location15;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_set_location15 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_set_location", 1 );
				ModelTestUtils.assertParameterNames( methodtest_set_location15, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains16;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_contains16 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains16, new String[] {"self"} );
			}
			//Function test:test_has_key
			{
			IMethod methodtest_has_key17;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_has_key17 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_has_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_key17, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear18;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_clear18 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear18, new String[] {"self"} );
			}
			//Function test:test__no_deadlock_first
			{
			IMethod methodtest__no_deadlock_first19;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest__no_deadlock_first19 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test__no_deadlock_first", 2 );
				ModelTestUtils.assertParameterNames( methodtest__no_deadlock_first19, new String[] {"self", "debug"} );
			}
			//Function test:test_for_cursor_memleak
			{
			IMethod methodtest_for_cursor_memleak20;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_for_cursor_memleak20 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_for_cursor_memleak", 1 );
				ModelTestUtils.assertParameterNames( methodtest_for_cursor_memleak20, new String[] {"self"} );
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem21;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_popitem21 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem21, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop22;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_pop22 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop22, new String[] {"self"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get23;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_get23 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get23, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault24;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_setdefault24 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault24, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update25;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_update25 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update25, new String[] {"self"} );
			}
			//Function test:test_keyordering
			{
			IMethod methodtest_keyordering26;
				IModelElement[] classTestBSDDB0Childs = classTestBSDDB0.getChildren();
				methodtest_keyordering26 = ModelTestUtils.getAssertMethod( classTestBSDDB0Childs, "test_keyordering", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keyordering26, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestBTree27;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBTree27 = ModelTestUtils.getAssertClass( moduleChilds, "TestBTree" );
			{
				IModelElement[] classTestBTree27Childs = classTestBTree27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree27Childs, "fname");
			}
			{
				IModelElement[] classTestBTree27Childs = classTestBTree27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree27Childs, "openmethod");
			}
		}
		//Class test
		{
		IType classTestBTree_InMemory28;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBTree_InMemory28 = ModelTestUtils.getAssertClass( moduleChilds, "TestBTree_InMemory" );
			{
				IModelElement[] classTestBTree_InMemory28Childs = classTestBTree_InMemory28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree_InMemory28Childs, "fname");
			}
			{
				IModelElement[] classTestBTree_InMemory28Childs = classTestBTree_InMemory28.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBTree_InMemory28Childs, "openmethod");
			}
		}
		//Class test
		{
		IType classTestHashTable29;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHashTable29 = ModelTestUtils.getAssertClass( moduleChilds, "TestHashTable" );
			{
				IModelElement[] classTestHashTable29Childs = classTestHashTable29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable29Childs, "fname");
			}
			{
				IModelElement[] classTestHashTable29Childs = classTestHashTable29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable29Childs, "openmethod");
			}
		}
		//Class test
		{
		IType classTestHashTable_InMemory30;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHashTable_InMemory30 = ModelTestUtils.getAssertClass( moduleChilds, "TestHashTable_InMemory" );
			{
				IModelElement[] classTestHashTable_InMemory30Childs = classTestHashTable_InMemory30.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable_InMemory30Childs, "fname");
			}
			{
				IModelElement[] classTestHashTable_InMemory30Childs = classTestHashTable_InMemory30.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestHashTable_InMemory30Childs, "openmethod");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main31, new String[] {"verbose"} );
		}

	}
	public void testModelGen116( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEventCollector0Childs, "events");
			}
			{
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEventCollector0Childs, "append");
			}
			//Function test:get_events
			{
			IMethod methodget_events3;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodget_events3 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "get_events", 1 );
				ModelTestUtils.assertParameterNames( methodget_events3, new String[] {"self"} );
			}
			{
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEventCollector0Childs, "events");
			}
			//Function test:unknown_starttag
			{
			IMethod methodunknown_starttag5;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_starttag5 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_starttag", 3 );
				ModelTestUtils.assertParameterNames( methodunknown_starttag5, new String[] {"self", "tag", "attrs"} );
			}
			//Function test:unknown_endtag
			{
			IMethod methodunknown_endtag6;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_endtag6 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_endtag", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_endtag6, new String[] {"self", "tag"} );
			}
			//Function test:handle_comment
			{
			IMethod methodhandle_comment7;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_comment7 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_comment", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_comment7, new String[] {"self", "data"} );
			}
			//Function test:handle_charref
			{
			IMethod methodhandle_charref8;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_charref8 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_charref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_charref8, new String[] {"self", "data"} );
			}
			//Function test:handle_data
			{
			IMethod methodhandle_data9;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_data9 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_data", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_data9, new String[] {"self", "data"} );
			}
			//Function test:handle_decl
			{
			IMethod methodhandle_decl10;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_decl10 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_decl", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_decl10, new String[] {"self", "decl"} );
			}
			//Function test:handle_entityref
			{
			IMethod methodhandle_entityref11;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_entityref11 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_entityref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_entityref11, new String[] {"self", "data"} );
			}
			//Function test:handle_pi
			{
			IMethod methodhandle_pi12;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_pi12 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_pi", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_pi12, new String[] {"self", "data"} );
			}
			//Function test:unknown_decl
			{
			IMethod methodunknown_decl13;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_decl13 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_decl", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_decl13, new String[] {"self", "decl"} );
			}
		}
		//Class test
		{
		IType classCDATAEventCollector14;
			IModelElement[] moduleChilds = module.getChildren();
			classCDATAEventCollector14 = ModelTestUtils.getAssertClass( moduleChilds, "CDATAEventCollector" );
			//Function test:start_cdata
			{
			IMethod methodstart_cdata15;
				IModelElement[] classCDATAEventCollector14Childs = classCDATAEventCollector14.getChildren();
				methodstart_cdata15 = ModelTestUtils.getAssertMethod( classCDATAEventCollector14Childs, "start_cdata", 2 );
				ModelTestUtils.assertParameterNames( methodstart_cdata15, new String[] {"self", "attrs"} );
			}
		}
		//Class test
		{
		IType classSGMLParserTestCase16;
			IModelElement[] moduleChilds = module.getChildren();
			classSGMLParserTestCase16 = ModelTestUtils.getAssertClass( moduleChilds, "SGMLParserTestCase" );
			{
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSGMLParserTestCase16Childs, "collector");
			}
			//Function test:get_events
			{
			IMethod methodget_events17;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodget_events17 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "get_events", 2 );
				ModelTestUtils.assertParameterNames( methodget_events17, new String[] {"self", "source"} );
			}
			//Function test:check_events
			{
			IMethod methodcheck_events18;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodcheck_events18 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "check_events", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_events18, new String[] {"self", "source", "expected_events"} );
			}
			//Function test:check_parse_error
			{
			IMethod methodcheck_parse_error19;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodcheck_parse_error19 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "check_parse_error", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_parse_error19, new String[] {"self", "source"} );
			}
			//Function test:test_doctype_decl_internal
			{
			IMethod methodtest_doctype_decl_internal20;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_doctype_decl_internal20 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_doctype_decl_internal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doctype_decl_internal20, new String[] {"self"} );
			}
			//Function test:test_doctype_decl_external
			{
			IMethod methodtest_doctype_decl_external21;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_doctype_decl_external21 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_doctype_decl_external", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doctype_decl_external21, new String[] {"self"} );
			}
			//Function test:test_underscore_in_attrname
			{
			IMethod methodtest_underscore_in_attrname22;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_underscore_in_attrname22 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_underscore_in_attrname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_underscore_in_attrname22, new String[] {"self"} );
			}
			//Function test:test_underscore_in_tagname
			{
			IMethod methodtest_underscore_in_tagname23;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_underscore_in_tagname23 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_underscore_in_tagname", 1 );
				ModelTestUtils.assertParameterNames( methodtest_underscore_in_tagname23, new String[] {"self"} );
			}
			//Function test:test_quotes_in_unquoted_attrs
			{
			IMethod methodtest_quotes_in_unquoted_attrs24;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_quotes_in_unquoted_attrs24 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_quotes_in_unquoted_attrs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quotes_in_unquoted_attrs24, new String[] {"self"} );
			}
			//Function test:test_xhtml_empty_tag
			{
			IMethod methodtest_xhtml_empty_tag25;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_xhtml_empty_tag25 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_xhtml_empty_tag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xhtml_empty_tag25, new String[] {"self"} );
			}
			//Function test:test_processing_instruction_only
			{
			IMethod methodtest_processing_instruction_only26;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_processing_instruction_only26 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_processing_instruction_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_processing_instruction_only26, new String[] {"self"} );
			}
			//Function test:test_bad_nesting
			{
			IMethod methodtest_bad_nesting27;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_bad_nesting27 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_bad_nesting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_nesting27, new String[] {"self"} );
			}
			//Function test:test_bare_ampersands
			{
			IMethod methodtest_bare_ampersands28;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_bare_ampersands28 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_bare_ampersands", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_ampersands28, new String[] {"self"} );
			}
			//Function test:test_bare_pointy_brackets
			{
			IMethod methodtest_bare_pointy_brackets29;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_bare_pointy_brackets29 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_bare_pointy_brackets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_pointy_brackets29, new String[] {"self"} );
			}
			//Function test:test_attr_syntax
			{
			IMethod methodtest_attr_syntax30;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_attr_syntax30 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_attr_syntax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_syntax30, new String[] {"self"} );
			}
			//Function test:test_attr_values
			{
			IMethod methodtest_attr_values31;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_attr_values31 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_attr_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_values31, new String[] {"self"} );
			}
			//Function test:test_attr_funky_names
			{
			IMethod methodtest_attr_funky_names32;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_attr_funky_names32 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_attr_funky_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_funky_names32, new String[] {"self"} );
			}
			//Function test:test_illegal_declarations
			{
			IMethod methodtest_illegal_declarations33;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_illegal_declarations33 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_illegal_declarations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_declarations33, new String[] {"self"} );
			}
			//Function test:test_weird_starttags
			{
			IMethod methodtest_weird_starttags34;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_weird_starttags34 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_weird_starttags", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weird_starttags34, new String[] {"self"} );
			}
			//Function test:test_declaration_junk_chars
			{
			IMethod methodtest_declaration_junk_chars35;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_declaration_junk_chars35 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_declaration_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_declaration_junk_chars35, new String[] {"self"} );
			}
			//Function test:test_get_starttag_text
			{
			IMethod methodtest_get_starttag_text36;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_get_starttag_text36 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_get_starttag_text", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_starttag_text36, new String[] {"self"} );
			}
			//Function test:test_cdata_content
			{
			IMethod methodtest_cdata_content37;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_cdata_content37 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_cdata_content", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cdata_content37, new String[] {"self"} );
			}
			{
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSGMLParserTestCase16Childs, "collector");
			}
			//Function test:test_illegal_declarations
			{
			IMethod methodtest_illegal_declarations39;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_illegal_declarations39 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_illegal_declarations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_declarations39, new String[] {"self"} );
			}
			//Function test:test_enumerated_attr_type
			{
			IMethod methodtest_enumerated_attr_type40;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				methodtest_enumerated_attr_type40 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "test_enumerated_attr_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_enumerated_attr_type40, new String[] {"self"} );
			}
			//Function test:_test_starttag_end_boundary
			{
			IMethod method_test_starttag_end_boundary41;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				method_test_starttag_end_boundary41 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "_test_starttag_end_boundary", 1 );
				ModelTestUtils.assertParameterNames( method_test_starttag_end_boundary41, new String[] {"self"} );
			}
			//Function test:_test_buffer_artefacts
			{
			IMethod method_test_buffer_artefacts42;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				method_test_buffer_artefacts42 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "_test_buffer_artefacts", 1 );
				ModelTestUtils.assertParameterNames( method_test_buffer_artefacts42, new String[] {"self"} );
			}
			//Function test:_test_starttag_junk_chars
			{
			IMethod method_test_starttag_junk_chars43;
				IModelElement[] classSGMLParserTestCase16Childs = classSGMLParserTestCase16.getChildren();
				method_test_starttag_junk_chars43 = ModelTestUtils.getAssertMethod( classSGMLParserTestCase16Childs, "_test_starttag_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( method_test_starttag_junk_chars43, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main44;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main44 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen118( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classTestRepeat4Childs = classTestRepeat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestRepeat4Childs, "it");
			}
			//Function test:test_no_len_for_infinite_repeat
			{
			IMethod methodtest_no_len_for_infinite_repeat7;
				IModelElement[] classTestRepeat4Childs = classTestRepeat4.getChildren();
				methodtest_no_len_for_infinite_repeat7 = ModelTestUtils.getAssertMethod( classTestRepeat4Childs, "test_no_len_for_infinite_repeat", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_len_for_infinite_repeat7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestXrange8;
			IModelElement[] moduleChilds = module.getChildren();
			classTestXrange8 = ModelTestUtils.getAssertClass( moduleChilds, "TestXrange" );
			//Function test:setUp
			{
			IMethod methodsetUp9;
				IModelElement[] classTestXrange8Childs = classTestXrange8.getChildren();
				methodsetUp9 = ModelTestUtils.getAssertMethod( classTestXrange8Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp9, new String[] {"self"} );
			}
			{
				IModelElement[] classTestXrange8Childs = classTestXrange8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestXrange8Childs, "it");
			}
		}
		//Class test
		{
		IType classTestXrangeCustomReversed11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestXrangeCustomReversed11 = ModelTestUtils.getAssertClass( moduleChilds, "TestXrangeCustomReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classTestXrangeCustomReversed11Childs = classTestXrangeCustomReversed11.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classTestXrangeCustomReversed11Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
			}
			{
				IModelElement[] classTestXrangeCustomReversed11Childs = classTestXrangeCustomReversed11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestXrangeCustomReversed11Childs, "it");
			}
		}
		//Class test
		{
		IType classTestTuple14;
			IModelElement[] moduleChilds = module.getChildren();
			classTestTuple14 = ModelTestUtils.getAssertClass( moduleChilds, "TestTuple" );
			//Function test:setUp
			{
			IMethod methodsetUp15;
				IModelElement[] classTestTuple14Childs = classTestTuple14.getChildren();
				methodsetUp15 = ModelTestUtils.getAssertMethod( classTestTuple14Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp15, new String[] {"self"} );
			}
			{
				IModelElement[] classTestTuple14Childs = classTestTuple14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestTuple14Childs, "it");
			}
		}
		//Class test
		{
		IType classTestDeque17;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDeque17 = ModelTestUtils.getAssertClass( moduleChilds, "TestDeque" );
			//Function test:setUp
			{
			IMethod methodsetUp18;
				IModelElement[] classTestDeque17Childs = classTestDeque17.getChildren();
				methodsetUp18 = ModelTestUtils.getAssertMethod( classTestDeque17Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp18, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDeque17Childs = classTestDeque17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDeque17Childs, "it");
			}
			{
				IModelElement[] classTestDeque17Childs = classTestDeque17.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDeque17Childs, "mutate");
			}
		}
		//Class test
		{
		IType classTestDequeReversed20;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDequeReversed20 = ModelTestUtils.getAssertClass( moduleChilds, "TestDequeReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp21;
				IModelElement[] classTestDequeReversed20Childs = classTestDequeReversed20.getChildren();
				methodsetUp21 = ModelTestUtils.getAssertMethod( classTestDequeReversed20Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp21, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDequeReversed20Childs = classTestDequeReversed20.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDequeReversed20Childs, "it");
			}
			{
				IModelElement[] classTestDequeReversed20Childs = classTestDequeReversed20.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDequeReversed20Childs, "mutate");
			}
		}
		//Class test
		{
		IType classTestDictKeys23;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictKeys23 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictKeys" );
			//Function test:setUp
			{
			IMethod methodsetUp24;
				IModelElement[] classTestDictKeys23Childs = classTestDictKeys23.getChildren();
				methodsetUp24 = ModelTestUtils.getAssertMethod( classTestDictKeys23Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp24, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDictKeys23Childs = classTestDictKeys23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDictKeys23Childs, "it");
			}
			{
				IModelElement[] classTestDictKeys23Childs = classTestDictKeys23.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDictKeys23Childs, "mutate");
			}
		}
		//Class test
		{
		IType classTestDictItems26;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictItems26 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictItems" );
			//Function test:setUp
			{
			IMethod methodsetUp27;
				IModelElement[] classTestDictItems26Childs = classTestDictItems26.getChildren();
				methodsetUp27 = ModelTestUtils.getAssertMethod( classTestDictItems26Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp27, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDictItems26Childs = classTestDictItems26.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDictItems26Childs, "it");
			}
			{
				IModelElement[] classTestDictItems26Childs = classTestDictItems26.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDictItems26Childs, "mutate");
			}
		}
		//Class test
		{
		IType classTestDictValues29;
			IModelElement[] moduleChilds = module.getChildren();
			classTestDictValues29 = ModelTestUtils.getAssertClass( moduleChilds, "TestDictValues" );
			//Function test:setUp
			{
			IMethod methodsetUp30;
				IModelElement[] classTestDictValues29Childs = classTestDictValues29.getChildren();
				methodsetUp30 = ModelTestUtils.getAssertMethod( classTestDictValues29Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp30, new String[] {"self"} );
			}
			{
				IModelElement[] classTestDictValues29Childs = classTestDictValues29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDictValues29Childs, "it");
			}
			{
				IModelElement[] classTestDictValues29Childs = classTestDictValues29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestDictValues29Childs, "mutate");
			}
		}
		//Class test
		{
		IType classTestSet32;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSet32 = ModelTestUtils.getAssertClass( moduleChilds, "TestSet" );
			//Function test:setUp
			{
			IMethod methodsetUp33;
				IModelElement[] classTestSet32Childs = classTestSet32.getChildren();
				methodsetUp33 = ModelTestUtils.getAssertMethod( classTestSet32Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp33, new String[] {"self"} );
			}
			{
				IModelElement[] classTestSet32Childs = classTestSet32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSet32Childs, "it");
			}
			{
				IModelElement[] classTestSet32Childs = classTestSet32.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSet32Childs, "mutate");
			}
		}
		//Class test
		{
		IType classTestList35;
			IModelElement[] moduleChilds = module.getChildren();
			classTestList35 = ModelTestUtils.getAssertClass( moduleChilds, "TestList" );
			//Function test:setUp
			{
			IMethod methodsetUp36;
				IModelElement[] classTestList35Childs = classTestList35.getChildren();
				methodsetUp36 = ModelTestUtils.getAssertMethod( classTestList35Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp36, new String[] {"self"} );
			}
			{
				IModelElement[] classTestList35Childs = classTestList35.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestList35Childs, "it");
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation38;
				IModelElement[] classTestList35Childs = classTestList35.getChildren();
				methodtest_mutation38 = ModelTestUtils.getAssertMethod( classTestList35Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation38, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestListReversed39;
			IModelElement[] moduleChilds = module.getChildren();
			classTestListReversed39 = ModelTestUtils.getAssertClass( moduleChilds, "TestListReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp40;
				IModelElement[] classTestListReversed39Childs = classTestListReversed39.getChildren();
				methodsetUp40 = ModelTestUtils.getAssertMethod( classTestListReversed39Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp40, new String[] {"self"} );
			}
			{
				IModelElement[] classTestListReversed39Childs = classTestListReversed39.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestListReversed39Childs, "it");
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation42;
				IModelElement[] classTestListReversed39Childs = classTestListReversed39.getChildren();
				methodtest_mutation42 = ModelTestUtils.getAssertMethod( classTestListReversed39Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation42, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSeqIter43;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSeqIter43 = ModelTestUtils.getAssertClass( moduleChilds, "TestSeqIter" );
			//Function test:setUp
			{
			IMethod methodsetUp44;
				IModelElement[] classTestSeqIter43Childs = classTestSeqIter43.getChildren();
				methodsetUp44 = ModelTestUtils.getAssertMethod( classTestSeqIter43Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp44, new String[] {"self"} );
			}
			{
				IModelElement[] classTestSeqIter43Childs = classTestSeqIter43.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSeqIter43Childs, "it");
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation46;
				IModelElement[] classTestSeqIter43Childs = classTestSeqIter43.getChildren();
				methodtest_mutation46 = ModelTestUtils.getAssertMethod( classTestSeqIter43Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation46, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestSeqIterReversed47;
			IModelElement[] moduleChilds = module.getChildren();
			classTestSeqIterReversed47 = ModelTestUtils.getAssertClass( moduleChilds, "TestSeqIterReversed" );
			//Function test:setUp
			{
			IMethod methodsetUp48;
				IModelElement[] classTestSeqIterReversed47Childs = classTestSeqIterReversed47.getChildren();
				methodsetUp48 = ModelTestUtils.getAssertMethod( classTestSeqIterReversed47Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp48, new String[] {"self"} );
			}
			{
				IModelElement[] classTestSeqIterReversed47Childs = classTestSeqIterReversed47.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestSeqIterReversed47Childs, "it");
			}
			//Function test:test_mutation
			{
			IMethod methodtest_mutation50;
				IModelElement[] classTestSeqIterReversed47Childs = classTestSeqIterReversed47.getChildren();
				methodtest_mutation50 = ModelTestUtils.getAssertMethod( classTestSeqIterReversed47Childs, "test_mutation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutation50, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "unittests");
		}

	}
	public void testModelGen121( ) throws Exception {
		String prj = "pytests";
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
			{
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTimeTestCase0Childs, "t");
			}
			//Function test:test_data_attributes
			{
			IMethod methodtest_data_attributes3;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_data_attributes3 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_data_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_data_attributes3, new String[] {"self"} );
			}
			//Function test:test_clock
			{
			IMethod methodtest_clock4;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_clock4 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_clock", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clock4, new String[] {"self"} );
			}
			//Function test:test_conversions
			{
			IMethod methodtest_conversions5;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_conversions5 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_conversions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_conversions5, new String[] {"self"} );
			}
			//Function test:test_sleep
			{
			IMethod methodtest_sleep6;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_sleep6 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_sleep", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sleep6, new String[] {"self"} );
			}
			//Function test:test_strftime
			{
			IMethod methodtest_strftime7;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_strftime7 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_strftime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime7, new String[] {"self"} );
			}
			//Function test:test_strftime_bounds_checking
			{
			IMethod methodtest_strftime_bounds_checking8;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_strftime_bounds_checking8 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_strftime_bounds_checking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strftime_bounds_checking8, new String[] {"self"} );
			}
			//Function test:test_strptime
			{
			IMethod methodtest_strptime9;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_strptime9 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_strptime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_strptime9, new String[] {"self"} );
			}
			//Function test:test_asctime
			{
			IMethod methodtest_asctime10;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_asctime10 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_asctime", 1 );
				ModelTestUtils.assertParameterNames( methodtest_asctime10, new String[] {"self"} );
			}
			//Function test:test_tzset
			{
			IMethod methodtest_tzset11;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_tzset11 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_tzset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tzset11, new String[] {"self"} );
			}
			//Function test:test_insane_timestamps
			{
			IMethod methodtest_insane_timestamps12;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_insane_timestamps12 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_insane_timestamps", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insane_timestamps12, new String[] {"self"} );
			}
			//Function test:test_ctime_without_arg
			{
			IMethod methodtest_ctime_without_arg13;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_ctime_without_arg13 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_ctime_without_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ctime_without_arg13, new String[] {"self"} );
			}
			//Function test:test_gmtime_without_arg
			{
			IMethod methodtest_gmtime_without_arg14;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_gmtime_without_arg14 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_gmtime_without_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gmtime_without_arg14, new String[] {"self"} );
			}
			//Function test:test_localtime_without_arg
			{
			IMethod methodtest_localtime_without_arg15;
				IModelElement[] classTimeTestCase0Childs = classTimeTestCase0.getChildren();
				methodtest_localtime_without_arg15 = ModelTestUtils.getAssertMethod( classTimeTestCase0Childs, "test_localtime_without_arg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_localtime_without_arg15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen122( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBaseTest4Childs, "tar");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown7;
				IModelElement[] classBaseTest4Childs = classBaseTest4.getChildren();
				methodtearDown7 = ModelTestUtils.getAssertMethod( classBaseTest4Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadTest8;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTest8 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTest" );
			//Function test:test
			{
			IMethod methodtest9;
				IModelElement[] classReadTest8Childs = classReadTest8.getChildren();
				methodtest9 = ModelTestUtils.getAssertMethod( classReadTest8Childs, "test", 1 );
				ModelTestUtils.assertParameterNames( methodtest9, new String[] {"self"} );
			}
			//Function test:test_sparse
			{
			IMethod methodtest_sparse10;
				IModelElement[] classReadTest8Childs = classReadTest8.getChildren();
				methodtest_sparse10 = ModelTestUtils.getAssertMethod( classReadTest8Childs, "test_sparse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sparse10, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines11;
				IModelElement[] classReadTest8Childs = classReadTest8.getChildren();
				methodtest_readlines11 = ModelTestUtils.getAssertMethod( classReadTest8Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines11, new String[] {"self"} );
			}
			//Function test:test_seek
			{
			IMethod methodtest_seek12;
				IModelElement[] classReadTest8Childs = classReadTest8.getChildren();
				methodtest_seek12 = ModelTestUtils.getAssertMethod( classReadTest8Childs, "test_seek", 1 );
				ModelTestUtils.assertParameterNames( methodtest_seek12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadStreamTest13;
			IModelElement[] moduleChilds = module.getChildren();
			classReadStreamTest13 = ModelTestUtils.getAssertClass( moduleChilds, "ReadStreamTest" );
			{
				IModelElement[] classReadStreamTest13Childs = classReadStreamTest13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadStreamTest13Childs, "sep");
			}
			//Function test:test
			{
			IMethod methodtest14;
				IModelElement[] classReadStreamTest13Childs = classReadStreamTest13.getChildren();
				methodtest14 = ModelTestUtils.getAssertMethod( classReadStreamTest13Childs, "test", 1 );
				ModelTestUtils.assertParameterNames( methodtest14, new String[] {"self"} );
			}
			//Function test:test_stream
			{
			IMethod methodtest_stream15;
				IModelElement[] classReadStreamTest13Childs = classReadStreamTest13.getChildren();
				methodtest_stream15 = ModelTestUtils.getAssertMethod( classReadStreamTest13Childs, "test_stream", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stream15, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWriteTest16;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteTest16 = ModelTestUtils.getAssertClass( moduleChilds, "WriteTest" );
			{
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTest16Childs, "mode");
			}
			//Function test:setUp
			{
			IMethod methodsetUp17;
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				methodsetUp17 = ModelTestUtils.getAssertMethod( classWriteTest16Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp17, new String[] {"self"} );
			}
			{
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTest16Childs, "src");
			}
			{
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTest16Childs, "dstname");
			}
			{
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTest16Childs, "dst");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown19;
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				methodtearDown19 = ModelTestUtils.getAssertMethod( classWriteTest16Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown19, new String[] {"self"} );
			}
			//Function test:test_posix
			{
			IMethod methodtest_posix20;
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				methodtest_posix20 = ModelTestUtils.getAssertMethod( classWriteTest16Childs, "test_posix", 1 );
				ModelTestUtils.assertParameterNames( methodtest_posix20, new String[] {"self"} );
			}
			//Function test:test_nonposix
			{
			IMethod methodtest_nonposix21;
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				methodtest_nonposix21 = ModelTestUtils.getAssertMethod( classWriteTest16Childs, "test_nonposix", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nonposix21, new String[] {"self"} );
			}
			//Function test:test_small
			{
			IMethod methodtest_small22;
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				methodtest_small22 = ModelTestUtils.getAssertMethod( classWriteTest16Childs, "test_small", 1 );
				ModelTestUtils.assertParameterNames( methodtest_small22, new String[] {"self"} );
			}
			//Function test:_test
			{
			IMethod method_test23;
				IModelElement[] classWriteTest16Childs = classWriteTest16.getChildren();
				method_test23 = ModelTestUtils.getAssertMethod( classWriteTest16Childs, "_test", 1 );
				ModelTestUtils.assertParameterNames( method_test23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWriteStreamTest24;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteStreamTest24 = ModelTestUtils.getAssertClass( moduleChilds, "WriteStreamTest" );
			{
				IModelElement[] classWriteStreamTest24Childs = classWriteStreamTest24.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteStreamTest24Childs, "sep");
			}
		}
		//Class test
		{
		IType classWriteGNULongTest25;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteGNULongTest25 = ModelTestUtils.getAssertClass( moduleChilds, "WriteGNULongTest" );
			//Function test:setUp
			{
			IMethod methodsetUp26;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodsetUp26 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp26, new String[] {"self"} );
			}
			{
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteGNULongTest25Childs, "tar");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown28;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtearDown28 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown28, new String[] {"self"} );
			}
			//Function test:_length
			{
			IMethod method_length29;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				method_length29 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "_length", 2 );
				ModelTestUtils.assertParameterNames( method_length29, new String[] {"self", "s"} );
			}
			//Function test:_calc_size
			{
			IMethod method_calc_size30;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				method_calc_size30 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "_calc_size", 3 );
				ModelTestUtils.assertParameterNames( method_calc_size30, new String[] {"self", "name", "link"} );
			}
			//Function test:_test
			{
			IMethod method_test31;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				method_test31 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "_test", 3 );
				ModelTestUtils.assertParameterNames( method_test31, new String[] {"self", "name", "link"} );
			}
			//Function test:test_longname_1023
			{
			IMethod methodtest_longname_102332;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longname_102332 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longname_1023", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longname_102332, new String[] {"self"} );
			}
			//Function test:test_longname_1024
			{
			IMethod methodtest_longname_102433;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longname_102433 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longname_1024", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longname_102433, new String[] {"self"} );
			}
			//Function test:test_longname_1025
			{
			IMethod methodtest_longname_102534;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longname_102534 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longname_1025", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longname_102534, new String[] {"self"} );
			}
			//Function test:test_longlink_1023
			{
			IMethod methodtest_longlink_102335;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longlink_102335 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longlink_1023", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longlink_102335, new String[] {"self"} );
			}
			//Function test:test_longlink_1024
			{
			IMethod methodtest_longlink_102436;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longlink_102436 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longlink_1024", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longlink_102436, new String[] {"self"} );
			}
			//Function test:test_longlink_1025
			{
			IMethod methodtest_longlink_102537;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longlink_102537 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longlink_1025", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longlink_102537, new String[] {"self"} );
			}
			//Function test:test_longnamelink_1023
			{
			IMethod methodtest_longnamelink_102338;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longnamelink_102338 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longnamelink_1023", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longnamelink_102338, new String[] {"self"} );
			}
			//Function test:test_longnamelink_1024
			{
			IMethod methodtest_longnamelink_102439;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longnamelink_102439 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longnamelink_1024", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longnamelink_102439, new String[] {"self"} );
			}
			//Function test:test_longnamelink_1025
			{
			IMethod methodtest_longnamelink_102540;
				IModelElement[] classWriteGNULongTest25Childs = classWriteGNULongTest25.getChildren();
				methodtest_longnamelink_102540 = ModelTestUtils.getAssertMethod( classWriteGNULongTest25Childs, "test_longnamelink_1025", 1 );
				ModelTestUtils.assertParameterNames( methodtest_longnamelink_102540, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classExtractHardlinkTest41;
			IModelElement[] moduleChilds = module.getChildren();
			classExtractHardlinkTest41 = ModelTestUtils.getAssertClass( moduleChilds, "ExtractHardlinkTest" );
			//Function test:test_hardlink
			{
			IMethod methodtest_hardlink42;
				IModelElement[] classExtractHardlinkTest41Childs = classExtractHardlinkTest41.getChildren();
				methodtest_hardlink42 = ModelTestUtils.getAssertMethod( classExtractHardlinkTest41Childs, "test_hardlink", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hardlink42, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadTestGzip43;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTestGzip43 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTestGzip" );
			{
				IModelElement[] classReadTestGzip43Childs = classReadTestGzip43.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadTestGzip43Childs, "comp");
			}
		}
		//Class test
		{
		IType classReadStreamTestGzip44;
			IModelElement[] moduleChilds = module.getChildren();
			classReadStreamTestGzip44 = ModelTestUtils.getAssertClass( moduleChilds, "ReadStreamTestGzip" );
			{
				IModelElement[] classReadStreamTestGzip44Childs = classReadStreamTestGzip44.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadStreamTestGzip44Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteTestGzip45;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteTestGzip45 = ModelTestUtils.getAssertClass( moduleChilds, "WriteTestGzip" );
			{
				IModelElement[] classWriteTestGzip45Childs = classWriteTestGzip45.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTestGzip45Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteStreamTestGzip46;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteStreamTestGzip46 = ModelTestUtils.getAssertClass( moduleChilds, "WriteStreamTestGzip" );
			{
				IModelElement[] classWriteStreamTestGzip46Childs = classWriteStreamTestGzip46.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteStreamTestGzip46Childs, "comp");
			}
		}
		//Class test
		{
		IType classFileModeTest47;
			IModelElement[] moduleChilds = module.getChildren();
			classFileModeTest47 = ModelTestUtils.getAssertClass( moduleChilds, "FileModeTest" );
			//Function test:test_modes
			{
			IMethod methodtest_modes48;
				IModelElement[] classFileModeTest47Childs = classFileModeTest47.getChildren();
				methodtest_modes48 = ModelTestUtils.getAssertMethod( classFileModeTest47Childs, "test_modes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_modes48, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classReadTestBzip249;
			IModelElement[] moduleChilds = module.getChildren();
			classReadTestBzip249 = ModelTestUtils.getAssertClass( moduleChilds, "ReadTestBzip2" );
			{
				IModelElement[] classReadTestBzip249Childs = classReadTestBzip249.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadTestBzip249Childs, "comp");
			}
		}
		//Class test
		{
		IType classReadStreamTestBzip250;
			IModelElement[] moduleChilds = module.getChildren();
			classReadStreamTestBzip250 = ModelTestUtils.getAssertClass( moduleChilds, "ReadStreamTestBzip2" );
			{
				IModelElement[] classReadStreamTestBzip250Childs = classReadStreamTestBzip250.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classReadStreamTestBzip250Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteTestBzip251;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteTestBzip251 = ModelTestUtils.getAssertClass( moduleChilds, "WriteTestBzip2" );
			{
				IModelElement[] classWriteTestBzip251Childs = classWriteTestBzip251.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteTestBzip251Childs, "comp");
			}
		}
		//Class test
		{
		IType classWriteStreamTestBzip252;
			IModelElement[] moduleChilds = module.getChildren();
			classWriteStreamTestBzip252 = ModelTestUtils.getAssertClass( moduleChilds, "WriteStreamTestBzip2" );
			{
				IModelElement[] classWriteStreamTestBzip252Childs = classWriteStreamTestBzip252.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classWriteStreamTestBzip252Childs, "comp");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main53;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main53 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen125( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShutil0Childs, "errorState");
			}
			{
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShutil0Childs, "childpath");
			}
			//Function test:check_args_to_onerror
			{
			IMethod methodcheck_args_to_onerror4;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodcheck_args_to_onerror4 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "check_args_to_onerror", 4 );
				ModelTestUtils.assertParameterNames( methodcheck_args_to_onerror4, new String[] {"self", "func", "arg", "exc"} );
			}
			{
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestShutil0Childs, "errorState");
			}
			//Function test:test_rmtree_dont_delete_file
			{
			IMethod methodtest_rmtree_dont_delete_file6;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_rmtree_dont_delete_file6 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_rmtree_dont_delete_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rmtree_dont_delete_file6, new String[] {"self"} );
			}
			//Function test:test_dont_move_dir_in_itself
			{
			IMethod methodtest_dont_move_dir_in_itself7;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_dont_move_dir_in_itself7 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_dont_move_dir_in_itself", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_move_dir_in_itself7, new String[] {"self"} );
			}
			//Function test:test_dont_copy_file_onto_link_to_itself
			{
			IMethod methodtest_dont_copy_file_onto_link_to_itself8;
				IModelElement[] classTestShutil0Childs = classTestShutil0.getChildren();
				methodtest_dont_copy_file_onto_link_to_itself8 = ModelTestUtils.getAssertMethod( classTestShutil0Childs, "test_dont_copy_file_onto_link_to_itself", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dont_copy_file_onto_link_to_itself8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen127( ) throws Exception {
		String prj = "pytests";
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
	public void testModelGen128( ) throws Exception {
		String prj = "pytests";
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
			{
				IModelElement[] classNothing10Childs = classNothing10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classNothing10Childs, "c");
			}
			//Function test:__iter__
			{
			IMethod method__iter__13;
				IModelElement[] classNothing10Childs = classNothing10.getChildren();
				method__iter__13 = ModelTestUtils.getAssertMethod( classNothing10Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classNothing14;
			IModelElement[] moduleChilds = module.getChildren();
			classNothing14 = ModelTestUtils.getAssertClass( moduleChilds, "Nothing" );
			//Function test:__init__
			{
			IMethod method__init__15;
				IModelElement[] classNothing14Childs = classNothing14.getChildren();
				method__init__15 = ModelTestUtils.getAssertMethod( classNothing14Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__15, new String[] {"self"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__16;
				IModelElement[] classNothing14Childs = classNothing14.getChildren();
				method__iter__16 = ModelTestUtils.getAssertMethod( classNothing14Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__16, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext17;
				IModelElement[] classNothing14Childs = classNothing14.getChildren();
				methodnext17 = ModelTestUtils.getAssertMethod( classNothing14Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext17, new String[] {"self"} );
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
		IMethod methodsaboteur18;
			IModelElement[] moduleChilds = module.getChildren();
			methodsaboteur18 = ModelTestUtils.getAssertMethod( moduleChilds, "saboteur", 1 );
			ModelTestUtils.assertParameterNames( methodsaboteur18, new String[] {"kw"} );
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
		IMethod methodf219;
			IModelElement[] moduleChilds = module.getChildren();
			methodf219 = ModelTestUtils.getAssertMethod( moduleChilds, "f2", 2 );
			ModelTestUtils.assertParameterNames( methodf219, new String[] {"a", "b"} );
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
		IType classFoo20;
			IModelElement[] moduleChilds = module.getChildren();
			classFoo20 = ModelTestUtils.getAssertClass( moduleChilds, "Foo" );
			//Function test:method
			{
			IMethod methodmethod21;
				IModelElement[] classFoo20Childs = classFoo20.getChildren();
				methodmethod21 = ModelTestUtils.getAssertMethod( classFoo20Childs, "method", 3 );
				ModelTestUtils.assertParameterNames( methodmethod21, new String[] {"self", "arg1", "arg2"} );
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
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classecho_client2Childs, "buffer");
			}
			//Function test:handle_connect
			{
			IMethod methodhandle_connect5;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				methodhandle_connect5 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "handle_connect", 1 );
				ModelTestUtils.assertParameterNames( methodhandle_connect5, new String[] {"self"} );
			}
			//Function test:collect_incoming_data
			{
			IMethod methodcollect_incoming_data6;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				methodcollect_incoming_data6 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "collect_incoming_data", 2 );
				ModelTestUtils.assertParameterNames( methodcollect_incoming_data6, new String[] {"self", "data"} );
			}
			{
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classecho_client2Childs, "buffer");
			}
			//Function test:found_terminator
			{
			IMethod methodfound_terminator8;
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				methodfound_terminator8 = ModelTestUtils.getAssertMethod( classecho_client2Childs, "found_terminator", 1 );
				ModelTestUtils.assertParameterNames( methodfound_terminator8, new String[] {"self"} );
			}
			{
				IModelElement[] classecho_client2Childs = classecho_client2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classecho_client2Childs, "buffer");
			}
		}
		//Function test:main
		{
		IMethod methodmain10;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain10 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen132( ) throws Exception {
		String prj = "pytests";
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
					{
						IModelElement[] classM6Childs = classM6.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classM6Childs, "results");
					}
					//Function test:keys
					{
					IMethod methodkeys10;
						IModelElement[] classM6Childs = classM6.getChildren();
						methodkeys10 = ModelTestUtils.getAssertMethod( classM6Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys10, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classA11;
					IModelElement[] methodtest_exec_with_general_mapping_for_locals5Childs = methodtest_exec_with_general_mapping_for_locals5.getChildren();
					classA11 = ModelTestUtils.getAssertClass( methodtest_exec_with_general_mapping_for_locals5Childs, "A" );
				}
				//Class test
				{
				IType classD12;
					IModelElement[] methodtest_exec_with_general_mapping_for_locals5Childs = methodtest_exec_with_general_mapping_for_locals5.getChildren();
					classD12 = ModelTestUtils.getAssertClass( methodtest_exec_with_general_mapping_for_locals5Childs, "D" );
					//Function test:__getitem__
					{
					IMethod method__getitem__13;
						IModelElement[] classD12Childs = classD12.getChildren();
						method__getitem__13 = ModelTestUtils.getAssertMethod( classD12Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__13, new String[] {"self", "key"} );
					}
				}
			}
			//Function test:test_complex_args
			{
			IMethod methodtest_complex_args14;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_complex_args14 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_complex_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_complex_args14, new String[] {"self"} );
				//Function test:comp_args
				{
				IMethod methodcomp_args15;
					IModelElement[] methodtest_complex_args14Childs = methodtest_complex_args14.getChildren();
					methodcomp_args15 = ModelTestUtils.getAssertMethod( methodtest_complex_args14Childs, "comp_args", 1 );
					ModelTestUtils.assertParameterNames( methodcomp_args15, new String[] {"('a', 'b')"} );
				}
				//Function test:comp_args
				{
				IMethod methodcomp_args16;
					IModelElement[] methodtest_complex_args14Childs = methodtest_complex_args14.getChildren();
					methodcomp_args16 = ModelTestUtils.getAssertMethod( methodtest_complex_args14Childs, "comp_args", 1 );
					ModelTestUtils.assertParameterNames( methodcomp_args16, new String[] {"('a', 'b')"} );
				}
				//Function test:comp_args
				{
				IMethod methodcomp_args17;
					IModelElement[] methodtest_complex_args14Childs = methodtest_complex_args14.getChildren();
					methodcomp_args17 = ModelTestUtils.getAssertMethod( methodtest_complex_args14Childs, "comp_args", 2 );
					ModelTestUtils.assertParameterNames( methodcomp_args17, new String[] {"a", "('b', 'c')"} );
				}
				//Function test:comp_args
				{
				IMethod methodcomp_args18;
					IModelElement[] methodtest_complex_args14Childs = methodtest_complex_args14.getChildren();
					methodcomp_args18 = ModelTestUtils.getAssertMethod( methodtest_complex_args14Childs, "comp_args", 2 );
					ModelTestUtils.assertParameterNames( methodcomp_args18, new String[] {"a", "('b', 'c')"} );
				}
			}
			//Function test:test_argument_order
			{
			IMethod methodtest_argument_order19;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_argument_order19 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_argument_order", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argument_order19, new String[] {"self"} );
			}
			//Function test:test_float_literals
			{
			IMethod methodtest_float_literals20;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_float_literals20 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_float_literals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_float_literals20, new String[] {"self"} );
			}
			//Function test:test_indentation
			{
			IMethod methodtest_indentation21;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_indentation21 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_indentation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_indentation21, new String[] {"self"} );
			}
			//Function test:test_literals_with_leading_zeroes
			{
			IMethod methodtest_literals_with_leading_zeroes22;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_literals_with_leading_zeroes22 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_literals_with_leading_zeroes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_literals_with_leading_zeroes22, new String[] {"self"} );
			}
			//Function test:test_unary_minus
			{
			IMethod methodtest_unary_minus23;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_unary_minus23 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_unary_minus", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unary_minus23, new String[] {"self"} );
			}
			//Function test:test_sequence_unpacking_error
			{
			IMethod methodtest_sequence_unpacking_error24;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_sequence_unpacking_error24 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_sequence_unpacking_error", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sequence_unpacking_error24, new String[] {"self"} );
			}
			//Function test:test_none_assignment
			{
			IMethod methodtest_none_assignment25;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_none_assignment25 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_none_assignment", 1 );
				ModelTestUtils.assertParameterNames( methodtest_none_assignment25, new String[] {"self"} );
			}
			//Function test:test_import
			{
			IMethod methodtest_import26;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_import26 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_import", 1 );
				ModelTestUtils.assertParameterNames( methodtest_import26, new String[] {"self"} );
			}
			//Function test:test_for_distinct_code_objects
			{
			IMethod methodtest_for_distinct_code_objects27;
				IModelElement[] classTestSpecifics0Childs = classTestSpecifics0.getChildren();
				methodtest_for_distinct_code_objects27 = ModelTestUtils.getAssertMethod( classTestSpecifics0Childs, "test_for_distinct_code_objects", 1 );
				ModelTestUtils.assertParameterNames( methodtest_for_distinct_code_objects27, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf28;
					IModelElement[] methodtest_for_distinct_code_objects27Childs = methodtest_for_distinct_code_objects27.getChildren();
					methodf28 = ModelTestUtils.getAssertMethod( methodtest_for_distinct_code_objects27Childs, "f", 0 );
				}
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main29;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main29 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen133( ) throws Exception {
		String prj = "pytests";
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
			{
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImport0Childs, "package_name");
			}
			{
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImport0Childs, "module_name");
			}
			//Function test:remove_modules
			{
			IMethod methodremove_modules3;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodremove_modules3 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "remove_modules", 1 );
				ModelTestUtils.assertParameterNames( methodremove_modules3, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp4;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodsetUp4 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp4, new String[] {"self"} );
			}
			{
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImport0Childs, "test_dir");
			}
			{
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImport0Childs, "package_dir");
			}
			{
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestImport0Childs, "module_path");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown6;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodtearDown6 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown6, new String[] {"self"} );
			}
			//Function test:rewrite_file
			{
			IMethod methodrewrite_file7;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodrewrite_file7 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "rewrite_file", 2 );
				ModelTestUtils.assertParameterNames( methodrewrite_file7, new String[] {"self", "contents"} );
			}
			//Function test:test_package_import__semantics
			{
			IMethod methodtest_package_import__semantics8;
				IModelElement[] classTestImport0Childs = classTestImport0.getChildren();
				methodtest_package_import__semantics8 = ModelTestUtils.getAssertMethod( classTestImport0Childs, "test_package_import__semantics", 1 );
				ModelTestUtils.assertParameterNames( methodtest_package_import__semantics8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen134( ) throws Exception {
		String prj = "pytests";
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
	public void testModelGen135( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
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
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_pickletools.py"));

		assertNotNull("Module test_pickletools.py not found", module);
		assertEquals("test_pickletools.py", module.getElementName());
		

	}
	public void testModelGen140( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
	public void testModelGen142( ) throws Exception {
		String prj = "pytests";
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f1");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f2");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f3");
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
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f8");
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
		String prj = "pytests";
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
		String prj = "pytests";
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
			//Function test:test_diInterpreterod
			{
			IMethod methodtest_diInterpreterod12;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_diInterpreterod12 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_diInterpreterod", 1 );
				ModelTestUtils.assertParameterNames( methodtest_diInterpreterod12, new String[] {"self"} );
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
					{
						IModelElement[] classOS17Childs = classOS17.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classOS17Childs, "value");
					}
					//Function test:__complex__
					{
					IMethod method__complex__20;
						IModelElement[] classOS17Childs = classOS17.getChildren();
						method__complex__20 = ModelTestUtils.getAssertMethod( classOS17Childs, "__complex__", 1 );
						ModelTestUtils.assertParameterNames( method__complex__20, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classNS21;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classNS21 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "NS" );
					//Function test:__init__
					{
					IMethod method__init__22;
						IModelElement[] classNS21Childs = classNS21.getChildren();
						method__init__22 = ModelTestUtils.getAssertMethod( classNS21Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__22, new String[] {"self", "value"} );
					}
					//Function test:__complex__
					{
					IMethod method__complex__23;
						IModelElement[] classNS21Childs = classNS21.getChildren();
						method__complex__23 = ModelTestUtils.getAssertMethod( classNS21Childs, "__complex__", 1 );
						ModelTestUtils.assertParameterNames( method__complex__23, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classcomplex224;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classcomplex224 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "complex2" );
				}
				//Class test
				{
				IType classEvilExc25;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classEvilExc25 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "EvilExc" );
				}
				//Class test
				{
				IType classevilcomplex26;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classevilcomplex26 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "evilcomplex" );
					//Function test:__complex__
					{
					IMethod method__complex__27;
						IModelElement[] classevilcomplex26Childs = classevilcomplex26.getChildren();
						method__complex__27 = ModelTestUtils.getAssertMethod( classevilcomplex26Childs, "__complex__", 1 );
						ModelTestUtils.assertParameterNames( method__complex__27, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classfloat228;
					IModelElement[] methodtest_constructor16Childs = methodtest_constructor16.getChildren();
					classfloat228 = ModelTestUtils.getAssertClass( methodtest_constructor16Childs, "float2" );
					//Function test:__init__
					{
					IMethod method__init__29;
						IModelElement[] classfloat228Childs = classfloat228.getChildren();
						method__init__29 = ModelTestUtils.getAssertMethod( classfloat228Childs, "__init__", 2 );
						ModelTestUtils.assertParameterNames( method__init__29, new String[] {"self", "value"} );
					}
					//Function test:__float__
					{
					IMethod method__float__30;
						IModelElement[] classfloat228Childs = classfloat228.getChildren();
						method__float__30 = ModelTestUtils.getAssertMethod( classfloat228Childs, "__float__", 1 );
						ModelTestUtils.assertParameterNames( method__float__30, new String[] {"self"} );
					}
				}
			}
			//Function test:test_hash
			{
			IMethod methodtest_hash31;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_hash31 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_hash", 1 );
				ModelTestUtils.assertParameterNames( methodtest_hash31, new String[] {"self"} );
			}
			//Function test:test_abs
			{
			IMethod methodtest_abs32;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_abs32 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_abs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_abs32, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr33;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_repr33 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr33, new String[] {"self"} );
			}
			//Function test:test_neg
			{
			IMethod methodtest_neg34;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_neg34 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_neg", 1 );
				ModelTestUtils.assertParameterNames( methodtest_neg34, new String[] {"self"} );
			}
			//Function test:test_file
			{
			IMethod methodtest_file35;
				IModelElement[] classComplexTest0Childs = classComplexTest0.getChildren();
				methodtest_file35 = ModelTestUtils.getAssertMethod( classComplexTest0Childs, "test_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file35, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main36;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main36 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen145( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classdefaultdict0Childs, "default");
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__3;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				method__getitem__3 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__3, new String[] {"self", "key"} );
			}
			//Function test:get
			{
			IMethod methodget4;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				methodget4 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "get", 3 );
				ModelTestUtils.assertParameterNames( methodget4, new String[] {"self", "key", "args"} );
			}
			//Function test:merge
			{
			IMethod methodmerge5;
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				methodmerge5 = ModelTestUtils.getAssertMethod( classdefaultdict0Childs, "merge", 2 );
				ModelTestUtils.assertParameterNames( methodmerge5, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classdefaultdict0Childs = classdefaultdict0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classdefaultdict0Childs, "OP_ASSIGN");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "test_1");
		}
		//Class test
		{
		IType classdefaultdict27;
			IModelElement[] moduleChilds = module.getChildren();
			classdefaultdict27 = ModelTestUtils.getAssertClass( moduleChilds, "defaultdict2" );
			{
				IModelElement[] classdefaultdict27Childs = classdefaultdict27.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classdefaultdict27Childs, "__slots__");
			}
			//Function test:__init__
			{
			IMethod method__init__8;
				IModelElement[] classdefaultdict27Childs = classdefaultdict27.getChildren();
				method__init__8 = ModelTestUtils.getAssertMethod( classdefaultdict27Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "default"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__9;
				IModelElement[] classdefaultdict27Childs = classdefaultdict27.getChildren();
				method__getitem__9 = ModelTestUtils.getAssertMethod( classdefaultdict27Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__9, new String[] {"self", "key"} );
			}
			//Function test:get
			{
			IMethod methodget10;
				IModelElement[] classdefaultdict27Childs = classdefaultdict27.getChildren();
				methodget10 = ModelTestUtils.getAssertMethod( classdefaultdict27Childs, "get", 3 );
				ModelTestUtils.assertParameterNames( methodget10, new String[] {"self", "key", "args"} );
			}
			//Function test:merge
			{
			IMethod methodmerge11;
				IModelElement[] classdefaultdict27Childs = classdefaultdict27.getChildren();
				methodmerge11 = ModelTestUtils.getAssertMethod( classdefaultdict27Childs, "merge", 2 );
				ModelTestUtils.assertParameterNames( methodmerge11, new String[] {"self", "other"} );
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
		IType classA12;
			IModelElement[] moduleChilds = module.getChildren();
			classA12 = ModelTestUtils.getAssertClass( moduleChilds, "A" );
			//Function test:m
			{
			IMethod methodm13;
				IModelElement[] classA12Childs = classA12.getChildren();
				methodm13 = ModelTestUtils.getAssertMethod( classA12Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classB14;
			IModelElement[] moduleChilds = module.getChildren();
			classB14 = ModelTestUtils.getAssertClass( moduleChilds, "B" );
			//Function test:m
			{
			IMethod methodm15;
				IModelElement[] classB14Childs = classB14.getChildren();
				methodm15 = ModelTestUtils.getAssertMethod( classB14Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm15, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC16;
			IModelElement[] moduleChilds = module.getChildren();
			classC16 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:m
			{
			IMethod methodm17;
				IModelElement[] classC16Childs = classC16.getChildren();
				methodm17 = ModelTestUtils.getAssertMethod( classC16Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm17, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classD18;
			IModelElement[] moduleChilds = module.getChildren();
			classD18 = ModelTestUtils.getAssertClass( moduleChilds, "D" );
			//Function test:m
			{
			IMethod methodm19;
				IModelElement[] classD18Childs = classD18.getChildren();
				methodm19 = ModelTestUtils.getAssertMethod( classD18Childs, "m", 1 );
				ModelTestUtils.assertParameterNames( methodm19, new String[] {"self"} );
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
		IMethod methodtest_main20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main20, new String[] {"verbose"} );
		}

	}
	public void testModelGen147( ) throws Exception {
		String prj = "pytests";
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
		String prj = "pytests";
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
			{
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileCompareTestCase0Childs, "name");
			}
			{
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileCompareTestCase0Childs, "name_same");
			}
			{
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileCompareTestCase0Childs, "name_diff");
			}
			{
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classFileCompareTestCase0Childs, "dir");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:test_matching
			{
			IMethod methodtest_matching4;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodtest_matching4 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "test_matching", 1 );
				ModelTestUtils.assertParameterNames( methodtest_matching4, new String[] {"self"} );
			}
			//Function test:test_different
			{
			IMethod methodtest_different5;
				IModelElement[] classFileCompareTestCase0Childs = classFileCompareTestCase0.getChildren();
				methodtest_different5 = ModelTestUtils.getAssertMethod( classFileCompareTestCase0Childs, "test_different", 1 );
				ModelTestUtils.assertParameterNames( methodtest_different5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDirCompareTestCase6;
			IModelElement[] moduleChilds = module.getChildren();
			classDirCompareTestCase6 = ModelTestUtils.getAssertClass( moduleChilds, "DirCompareTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp7;
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				methodsetUp7 = ModelTestUtils.getAssertMethod( classDirCompareTestCase6Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp7, new String[] {"self"} );
			}
			{
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDirCompareTestCase6Childs, "dir");
			}
			{
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDirCompareTestCase6Childs, "dir_same");
			}
			{
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDirCompareTestCase6Childs, "dir_diff");
			}
			{
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDirCompareTestCase6Childs, "caseinsensitive");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown9;
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				methodtearDown9 = ModelTestUtils.getAssertMethod( classDirCompareTestCase6Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown9, new String[] {"self"} );
			}
			//Function test:test_cmpfiles
			{
			IMethod methodtest_cmpfiles10;
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				methodtest_cmpfiles10 = ModelTestUtils.getAssertMethod( classDirCompareTestCase6Childs, "test_cmpfiles", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cmpfiles10, new String[] {"self"} );
			}
			//Function test:test_dircmp
			{
			IMethod methodtest_dircmp11;
				IModelElement[] classDirCompareTestCase6Childs = classDirCompareTestCase6.getChildren();
				methodtest_dircmp11 = ModelTestUtils.getAssertMethod( classDirCompareTestCase6Childs, "test_dircmp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dircmp11, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen149( ) throws Exception {
		String prj = "pytests";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_bastion.py"));

		assertNotNull("Module test_bastion.py not found", module);
		assertEquals("test_bastion.py", module.getElementName());
		

	}

}
	