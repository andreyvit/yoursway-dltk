
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

public class GeneratedModelTests0 extends AbstractModelTests
{
	public GeneratedModelTests0(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( GeneratedModelTests0.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();
		IScriptProject scriptProject = setUpScriptProjectTo( "pytests_0", "pytests" );
		Bundle bundle = PythonTestsPlugin.getDefault().getBundle();
		URL entry = bundle.getEntry("/workspace/src.zip");
		ModelTestUtils.exractZipInto(scriptProject, entry);
		// Extract all files from selected zip file.
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
		deleteProject( "pytests0" );
	}
	public void testModelGen0( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_socketserver.py"));

		assertNotNull("Module test_socketserver.py not found", module);
		assertEquals("test_socketserver.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NREQ");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DELAY");
		}
		//Class test
		{
		IType classMyMixinHandler0;
			IModelElement[] moduleChilds = module.getChildren();
			classMyMixinHandler0 = ModelTestUtils.getAssertClass( moduleChilds, "MyMixinHandler" );
			//Function test:handle
			{
			IMethod methodhandle1;
				IModelElement[] classMyMixinHandler0Childs = classMyMixinHandler0.getChildren();
				methodhandle1 = ModelTestUtils.getAssertMethod( classMyMixinHandler0Childs, "handle", 1 );
				ModelTestUtils.assertParameterNames( methodhandle1, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMyStreamHandler2;
			IModelElement[] moduleChilds = module.getChildren();
			classMyStreamHandler2 = ModelTestUtils.getAssertClass( moduleChilds, "MyStreamHandler" );
		}
		//Class test
		{
		IType classMyDatagramHandler3;
			IModelElement[] moduleChilds = module.getChildren();
			classMyDatagramHandler3 = ModelTestUtils.getAssertClass( moduleChilds, "MyDatagramHandler" );
		}
		//Class test
		{
		IType classMyMixinServer4;
			IModelElement[] moduleChilds = module.getChildren();
			classMyMixinServer4 = ModelTestUtils.getAssertClass( moduleChilds, "MyMixinServer" );
			//Function test:serve_a_few
			{
			IMethod methodserve_a_few5;
				IModelElement[] classMyMixinServer4Childs = classMyMixinServer4.getChildren();
				methodserve_a_few5 = ModelTestUtils.getAssertMethod( classMyMixinServer4Childs, "serve_a_few", 1 );
				ModelTestUtils.assertParameterNames( methodserve_a_few5, new String[] {"self"} );
			}
			//Function test:handle_error
			{
			IMethod methodhandle_error6;
				IModelElement[] classMyMixinServer4Childs = classMyMixinServer4.getChildren();
				methodhandle_error6 = ModelTestUtils.getAssertMethod( classMyMixinServer4Childs, "handle_error", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_error6, new String[] {"self", "request", "client_address"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "teststring");
		}
		//Function test:receive
		{
		IMethod methodreceive7;
			IModelElement[] moduleChilds = module.getChildren();
			methodreceive7 = ModelTestUtils.getAssertMethod( moduleChilds, "receive", 3 );
			ModelTestUtils.assertParameterNames( methodreceive7, new String[] {"sock", "n", "timeout"} );
		}
		//Function test:testdgram
		{
		IMethod methodtestdgram8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestdgram8 = ModelTestUtils.getAssertMethod( moduleChilds, "testdgram", 2 );
			ModelTestUtils.assertParameterNames( methodtestdgram8, new String[] {"proto", "addr"} );
		}
		//Function test:teststream
		{
		IMethod methodteststream9;
			IModelElement[] moduleChilds = module.getChildren();
			methodteststream9 = ModelTestUtils.getAssertMethod( moduleChilds, "teststream", 2 );
			ModelTestUtils.assertParameterNames( methodteststream9, new String[] {"proto", "addr"} );
		}
		//Class test
		{
		IType classServerThread10;
			IModelElement[] moduleChilds = module.getChildren();
			classServerThread10 = ModelTestUtils.getAssertClass( moduleChilds, "ServerThread" );
			//Function test:__init__
			{
			IMethod method__init__11;
				IModelElement[] classServerThread10Childs = classServerThread10.getChildren();
				method__init__11 = ModelTestUtils.getAssertMethod( classServerThread10Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__11, new String[] {"self", "addr", "svrcls", "hdlrcls"} );
			}
			//Function test:run
			{
			IMethod methodrun12;
				IModelElement[] classServerThread10Childs = classServerThread10.getChildren();
				methodrun12 = ModelTestUtils.getAssertMethod( classServerThread10Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun12, new String[] {"self"} );
				//Class test
				{
				IType classsvrcls13;
					IModelElement[] methodrun12Childs = methodrun12.getChildren();
					classsvrcls13 = ModelTestUtils.getAssertClass( methodrun12Childs, "svrcls" );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "seed");
		}
		//Function test:pickport
		{
		IMethod methodpickport14;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickport14 = ModelTestUtils.getAssertMethod( moduleChilds, "pickport", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "host");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testfiles");
		}
		//Function test:pickaddr
		{
		IMethod methodpickaddr15;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickaddr15 = ModelTestUtils.getAssertMethod( moduleChilds, "pickaddr", 1 );
			ModelTestUtils.assertParameterNames( methodpickaddr15, new String[] {"proto"} );
		}
		//Function test:cleanup
		{
		IMethod methodcleanup16;
			IModelElement[] moduleChilds = module.getChildren();
			methodcleanup16 = ModelTestUtils.getAssertMethod( moduleChilds, "cleanup", 0 );
		}
		//Function test:testloop
		{
		IMethod methodtestloop17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestloop17 = ModelTestUtils.getAssertMethod( moduleChilds, "testloop", 4 );
			ModelTestUtils.assertParameterNames( methodtestloop17, new String[] {"proto", "servers", "hdlrcls", "testfunc"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tcpservers");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "udpservers");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "streamservers");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dgramservers");
		}
		//Class test
		{
		IType classForkingUnixStreamServer18;
			IModelElement[] moduleChilds = module.getChildren();
			classForkingUnixStreamServer18 = ModelTestUtils.getAssertClass( moduleChilds, "ForkingUnixStreamServer" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "streamservers");
		}
		//Class test
		{
		IType classForkingUnixDatagramServer19;
			IModelElement[] moduleChilds = module.getChildren();
			classForkingUnixDatagramServer19 = ModelTestUtils.getAssertClass( moduleChilds, "ForkingUnixDatagramServer" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dgramservers");
		}
		//Function test:testall
		{
		IMethod methodtestall20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestall20 = ModelTestUtils.getAssertMethod( moduleChilds, "testall", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main21 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen1( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dict.py"));

		assertNotNull("Module test_dict.py not found", module);
		assertEquals("test_dict.py", module.getElementName());
		
		//Class test
		{
		IType classDictTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classDictTest0 = ModelTestUtils.getAssertClass( moduleChilds, "DictTest" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor1;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_constructor1 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor1, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool2;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_bool2 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool2, new String[] {"self"} );
			}
			//Function test:test_keys
			{
			IMethod methodtest_keys3;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_keys3 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keys3, new String[] {"self"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values4;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_values4 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values4, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items5;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_items5 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items5, new String[] {"self"} );
			}
			//Function test:test_has_key
			{
			IMethod methodtest_has_key6;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_has_key6 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_has_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_key6, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains7;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_contains7 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains7, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len8;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_len8 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len8, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem9;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_getitem9 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem9, new String[] {"self"} );
				//Class test
				{
				IType classBadEq10;
					IModelElement[] methodtest_getitem9Childs = methodtest_getitem9.getChildren();
					classBadEq10 = ModelTestUtils.getAssertClass( methodtest_getitem9Childs, "BadEq" );
					//Function test:__eq__
					{
					IMethod method__eq__11;
						IModelElement[] classBadEq10Childs = classBadEq10.getChildren();
						method__eq__11 = ModelTestUtils.getAssertMethod( classBadEq10Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__11, new String[] {"self", "other"} );
					}
				}
				//Class test
				{
				IType classExc12;
					IModelElement[] methodtest_getitem9Childs = methodtest_getitem9.getChildren();
					classExc12 = ModelTestUtils.getAssertClass( methodtest_getitem9Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash13;
					IModelElement[] methodtest_getitem9Childs = methodtest_getitem9.getChildren();
					classBadHash13 = ModelTestUtils.getAssertClass( methodtest_getitem9Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__14;
						IModelElement[] classBadHash13Childs = classBadHash13.getChildren();
						method__hash__14 = ModelTestUtils.getAssertMethod( classBadHash13Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__14, new String[] {"self"} );
					}
				}
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear15;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_clear15 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear15, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update16;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_update16 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update16, new String[] {"self"} );
				//Class test
				{
				IType classSimpleUserDict17;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classSimpleUserDict17 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "SimpleUserDict" );
					//Function test:__init__
					{
					IMethod method__init__18;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						method__init__18 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__18, new String[] {"self"} );
					}
					//Function test:keys
					{
					IMethod methodkeys19;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						methodkeys19 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys19, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__20;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						method__getitem__20 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__20, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classExc21;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classExc21 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "Exc" );
				}
				//Class test
				{
				IType classFailingUserDict22;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict22 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys23;
						IModelElement[] classFailingUserDict22Childs = classFailingUserDict22.getChildren();
						methodkeys23 = ModelTestUtils.getAssertMethod( classFailingUserDict22Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys23, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict24;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict24 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys25;
						IModelElement[] classFailingUserDict24Childs = classFailingUserDict24.getChildren();
						methodkeys25 = ModelTestUtils.getAssertMethod( classFailingUserDict24Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys25, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter26;
							IModelElement[] methodkeys25Childs = methodkeys25.getChildren();
							classBogonIter26 = ModelTestUtils.getAssertClass( methodkeys25Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__27;
								IModelElement[] classBogonIter26Childs = classBogonIter26.getChildren();
								method__init__27 = ModelTestUtils.getAssertMethod( classBogonIter26Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__27, new String[] {"self"} );
							}
							//Function test:__iter__
							{
							IMethod method__iter__28;
								IModelElement[] classBogonIter26Childs = classBogonIter26.getChildren();
								method__iter__28 = ModelTestUtils.getAssertMethod( classBogonIter26Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__28, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext29;
								IModelElement[] classBogonIter26Childs = classBogonIter26.getChildren();
								methodnext29 = ModelTestUtils.getAssertMethod( classBogonIter26Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext29, new String[] {"self"} );
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__30;
						IModelElement[] classFailingUserDict24Childs = classFailingUserDict24.getChildren();
						method__getitem__30 = ModelTestUtils.getAssertMethod( classFailingUserDict24Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__30, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict31;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict31 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys32;
						IModelElement[] classFailingUserDict31Childs = classFailingUserDict31.getChildren();
						methodkeys32 = ModelTestUtils.getAssertMethod( classFailingUserDict31Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys32, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter33;
							IModelElement[] methodkeys32Childs = methodkeys32.getChildren();
							classBogonIter33 = ModelTestUtils.getAssertClass( methodkeys32Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__34;
								IModelElement[] classBogonIter33Childs = classBogonIter33.getChildren();
								method__init__34 = ModelTestUtils.getAssertMethod( classBogonIter33Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__34, new String[] {"self"} );
							}
							//Function test:__iter__
							{
							IMethod method__iter__35;
								IModelElement[] classBogonIter33Childs = classBogonIter33.getChildren();
								method__iter__35 = ModelTestUtils.getAssertMethod( classBogonIter33Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__35, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext36;
								IModelElement[] classBogonIter33Childs = classBogonIter33.getChildren();
								methodnext36 = ModelTestUtils.getAssertMethod( classBogonIter33Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext36, new String[] {"self"} );
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__37;
						IModelElement[] classFailingUserDict31Childs = classFailingUserDict31.getChildren();
						method__getitem__37 = ModelTestUtils.getAssertMethod( classFailingUserDict31Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__37, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classbadseq38;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classbadseq38 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "badseq" );
					//Function test:__iter__
					{
					IMethod method__iter__39;
						IModelElement[] classbadseq38Childs = classbadseq38.getChildren();
						method__iter__39 = ModelTestUtils.getAssertMethod( classbadseq38Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__39, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext40;
						IModelElement[] classbadseq38Childs = classbadseq38.getChildren();
						methodnext40 = ModelTestUtils.getAssertMethod( classbadseq38Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext40, new String[] {"self"} );
					}
				}
			}
			//Function test:test_fromkeys
			{
			IMethod methodtest_fromkeys41;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_fromkeys41 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_fromkeys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromkeys41, new String[] {"self"} );
				//Function test:g
				{
				IMethod methodg42;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					methodg42 = ModelTestUtils.getAssertMethod( methodtest_fromkeys41Childs, "g", 0 );
				}
				//Class test
				{
				IType classdictlike43;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					classdictlike43 = ModelTestUtils.getAssertClass( methodtest_fromkeys41Childs, "dictlike" );
				}
				//Class test
				{
				IType classmydict44;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					classmydict44 = ModelTestUtils.getAssertClass( methodtest_fromkeys41Childs, "mydict" );
					//Function test:__new__
					{
					IMethod method__new__45;
						IModelElement[] classmydict44Childs = classmydict44.getChildren();
						method__new__45 = ModelTestUtils.getAssertMethod( classmydict44Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__45, new String[] {"cls"} );
					}
				}
				//Class test
				{
				IType classExc46;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					classExc46 = ModelTestUtils.getAssertClass( methodtest_fromkeys41Childs, "Exc" );
				}
				//Class test
				{
				IType classbaddict147;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					classbaddict147 = ModelTestUtils.getAssertClass( methodtest_fromkeys41Childs, "baddict1" );
					//Function test:__init__
					{
					IMethod method__init__48;
						IModelElement[] classbaddict147Childs = classbaddict147.getChildren();
						method__init__48 = ModelTestUtils.getAssertMethod( classbaddict147Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__48, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBadSeq49;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					classBadSeq49 = ModelTestUtils.getAssertClass( methodtest_fromkeys41Childs, "BadSeq" );
					//Function test:__iter__
					{
					IMethod method__iter__50;
						IModelElement[] classBadSeq49Childs = classBadSeq49.getChildren();
						method__iter__50 = ModelTestUtils.getAssertMethod( classBadSeq49Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__50, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext51;
						IModelElement[] classBadSeq49Childs = classBadSeq49.getChildren();
						methodnext51 = ModelTestUtils.getAssertMethod( classBadSeq49Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext51, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classbaddict252;
					IModelElement[] methodtest_fromkeys41Childs = methodtest_fromkeys41.getChildren();
					classbaddict252 = ModelTestUtils.getAssertClass( methodtest_fromkeys41Childs, "baddict2" );
					//Function test:__setitem__
					{
					IMethod method__setitem__53;
						IModelElement[] classbaddict252Childs = classbaddict252.getChildren();
						method__setitem__53 = ModelTestUtils.getAssertMethod( classbaddict252Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__53, new String[] {"self", "key", "value"} );
					}
				}
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy54;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_copy54 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy54, new String[] {"self"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get55;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_get55 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get55, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault56;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_setdefault56 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault56, new String[] {"self"} );
				//Class test
				{
				IType classExc57;
					IModelElement[] methodtest_setdefault56Childs = methodtest_setdefault56.getChildren();
					classExc57 = ModelTestUtils.getAssertClass( methodtest_setdefault56Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash58;
					IModelElement[] methodtest_setdefault56Childs = methodtest_setdefault56.getChildren();
					classBadHash58 = ModelTestUtils.getAssertClass( methodtest_setdefault56Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__59;
						IModelElement[] classBadHash58Childs = classBadHash58.getChildren();
						method__hash__59 = ModelTestUtils.getAssertMethod( classBadHash58Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__59, new String[] {"self"} );
					}
				}
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem60;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_popitem60 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem60, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop61;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_pop61 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop61, new String[] {"self"} );
				//Class test
				{
				IType classExc62;
					IModelElement[] methodtest_pop61Childs = methodtest_pop61.getChildren();
					classExc62 = ModelTestUtils.getAssertClass( methodtest_pop61Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash63;
					IModelElement[] methodtest_pop61Childs = methodtest_pop61.getChildren();
					classBadHash63 = ModelTestUtils.getAssertClass( methodtest_pop61Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__64;
						IModelElement[] classBadHash63Childs = classBadHash63.getChildren();
						method__hash__64 = ModelTestUtils.getAssertMethod( classBadHash63Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__64, new String[] {"self"} );
					}
				}
			}
			//Function test:test_mutatingiteration
			{
			IMethod methodtest_mutatingiteration65;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_mutatingiteration65 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_mutatingiteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutatingiteration65, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr66;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_repr66 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr66, new String[] {"self"} );
				//Class test
				{
				IType classExc67;
					IModelElement[] methodtest_repr66Childs = methodtest_repr66.getChildren();
					classExc67 = ModelTestUtils.getAssertClass( methodtest_repr66Childs, "Exc" );
				}
				//Class test
				{
				IType classBadRepr68;
					IModelElement[] methodtest_repr66Childs = methodtest_repr66.getChildren();
					classBadRepr68 = ModelTestUtils.getAssertClass( methodtest_repr66Childs, "BadRepr" );
					//Function test:__repr__
					{
					IMethod method__repr__69;
						IModelElement[] classBadRepr68Childs = classBadRepr68.getChildren();
						method__repr__69 = ModelTestUtils.getAssertMethod( classBadRepr68Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__69, new String[] {"self"} );
					}
				}
			}
			//Function test:test_le
			{
			IMethod methodtest_le70;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_le70 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_le", 1 );
				ModelTestUtils.assertParameterNames( methodtest_le70, new String[] {"self"} );
				//Class test
				{
				IType classExc71;
					IModelElement[] methodtest_le70Childs = methodtest_le70.getChildren();
					classExc71 = ModelTestUtils.getAssertClass( methodtest_le70Childs, "Exc" );
				}
				//Class test
				{
				IType classBadCmp72;
					IModelElement[] methodtest_le70Childs = methodtest_le70.getChildren();
					classBadCmp72 = ModelTestUtils.getAssertClass( methodtest_le70Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__73;
						IModelElement[] classBadCmp72Childs = classBadCmp72.getChildren();
						method__eq__73 = ModelTestUtils.getAssertMethod( classBadCmp72Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__73, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Class test
		{
		IType classGeneralMappingTests74;
			IModelElement[] moduleChilds = module.getChildren();
			classGeneralMappingTests74 = ModelTestUtils.getAssertClass( moduleChilds, "GeneralMappingTests" );
			{
				IModelElement[] classGeneralMappingTests74Childs = classGeneralMappingTests74.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classGeneralMappingTests74Childs, "type2test");
			}
		}
		//Class test
		{
		IType classDict75;
			IModelElement[] moduleChilds = module.getChildren();
			classDict75 = ModelTestUtils.getAssertClass( moduleChilds, "Dict" );
		}
		//Class test
		{
		IType classSubclassMappingTests76;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassMappingTests76 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassMappingTests" );
			{
				IModelElement[] classSubclassMappingTests76Childs = classSubclassMappingTests76.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassMappingTests76Childs, "type2test");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main77;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main77 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen2( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dumbdbm.py"));

		assertNotNull("Module test_dumbdbm.py not found", module);
		assertEquals("test_dumbdbm.py", module.getElementName());
		
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
		IType classDumbDBMTestCase1;
			IModelElement[] moduleChilds = module.getChildren();
			classDumbDBMTestCase1 = ModelTestUtils.getAssertClass( moduleChilds, "DumbDBMTestCase" );
			{
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDumbDBMTestCase1Childs, "_dict");
			}
			//Function test:__init__
			{
			IMethod method__init__2;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				method__init__2 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__2, new String[] {"self", "args"} );
			}
			//Function test:test_dumbdbm_creation
			{
			IMethod methodtest_dumbdbm_creation3;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_dumbdbm_creation3 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_dumbdbm_creation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dumbdbm_creation3, new String[] {"self"} );
			}
			//Function test:test_close_twice
			{
			IMethod methodtest_close_twice4;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_close_twice4 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_close_twice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_close_twice4, new String[] {"self"} );
			}
			//Function test:test_dumbdbm_modification
			{
			IMethod methodtest_dumbdbm_modification5;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_dumbdbm_modification5 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_dumbdbm_modification", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dumbdbm_modification5, new String[] {"self"} );
			}
			//Function test:test_dumbdbm_read
			{
			IMethod methodtest_dumbdbm_read6;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_dumbdbm_read6 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_dumbdbm_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dumbdbm_read6, new String[] {"self"} );
			}
			//Function test:test_dumbdbm_keys
			{
			IMethod methodtest_dumbdbm_keys7;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_dumbdbm_keys7 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_dumbdbm_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dumbdbm_keys7, new String[] {"self"} );
			}
			//Function test:test_write_write_read
			{
			IMethod methodtest_write_write_read8;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_write_write_read8 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_write_write_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write_write_read8, new String[] {"self"} );
			}
			//Function test:read_helper
			{
			IMethod methodread_helper9;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodread_helper9 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "read_helper", 2 );
				ModelTestUtils.assertParameterNames( methodread_helper9, new String[] {"self", "f"} );
			}
			//Function test:init_db
			{
			IMethod methodinit_db10;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodinit_db10 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "init_db", 1 );
				ModelTestUtils.assertParameterNames( methodinit_db10, new String[] {"self"} );
			}
			//Function test:keys_helper
			{
			IMethod methodkeys_helper11;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodkeys_helper11 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "keys_helper", 2 );
				ModelTestUtils.assertParameterNames( methodkeys_helper11, new String[] {"self", "f"} );
			}
			//Function test:test_random
			{
			IMethod methodtest_random12;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtest_random12 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "test_random", 1 );
				ModelTestUtils.assertParameterNames( methodtest_random12, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown13;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodtearDown13 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown13, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp14;
				IModelElement[] classDumbDBMTestCase1Childs = classDumbDBMTestCase1.getChildren();
				methodsetUp14 = ModelTestUtils.getAssertMethod( classDumbDBMTestCase1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp14, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main15 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen3( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_generators.py"));

		assertNotNull("Module test_generators.py not found", module);
		assertEquals("test_generators.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "tutorial_tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "pep_tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "email_tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fun_tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "syntax_tests");
		}
		//Function test:conjoin
		{
		IMethod methodconjoin0;
			IModelElement[] moduleChilds = module.getChildren();
			methodconjoin0 = ModelTestUtils.getAssertMethod( moduleChilds, "conjoin", 1 );
			ModelTestUtils.assertParameterNames( methodconjoin0, new String[] {"gs"} );
			//Function test:gen
			{
			IMethod methodgen1;
				IModelElement[] methodconjoin0Childs = methodconjoin0.getChildren();
				methodgen1 = ModelTestUtils.getAssertMethod( methodconjoin0Childs, "gen", 2 );
				ModelTestUtils.assertParameterNames( methodgen1, new String[] {"i", "values"} );
			}
		}
		//Function test:conjoin
		{
		IMethod methodconjoin2;
			IModelElement[] moduleChilds = module.getChildren();
			methodconjoin2 = ModelTestUtils.getAssertMethod( moduleChilds, "conjoin", 1 );
			ModelTestUtils.assertParameterNames( methodconjoin2, new String[] {"gs"} );
			//Function test:gen
			{
			IMethod methodgen3;
				IModelElement[] methodconjoin2Childs = methodconjoin2.getChildren();
				methodgen3 = ModelTestUtils.getAssertMethod( methodconjoin2Childs, "gen", 2 );
				ModelTestUtils.assertParameterNames( methodgen3, new String[] {"i", "values"} );
			}
			//Function test:_gen3
			{
			IMethod method_gen34;
				IModelElement[] methodconjoin2Childs = methodconjoin2.getChildren();
				method_gen34 = ModelTestUtils.getAssertMethod( methodconjoin2Childs, "_gen3", 2 );
				ModelTestUtils.assertParameterNames( method_gen34, new String[] {"i", "values"} );
			}
		}
		//Function test:flat_conjoin
		{
		IMethod methodflat_conjoin5;
			IModelElement[] moduleChilds = module.getChildren();
			methodflat_conjoin5 = ModelTestUtils.getAssertMethod( moduleChilds, "flat_conjoin", 1 );
			ModelTestUtils.assertParameterNames( methodflat_conjoin5, new String[] {"gs"} );
		}
		//Class test
		{
		IType classQueens6;
			IModelElement[] moduleChilds = module.getChildren();
			classQueens6 = ModelTestUtils.getAssertClass( moduleChilds, "Queens" );
			//Function test:__init__
			{
			IMethod method__init__7;
				IModelElement[] classQueens6Childs = classQueens6.getChildren();
				method__init__7 = ModelTestUtils.getAssertMethod( classQueens6Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__7, new String[] {"self", "n"} );
				//Function test:rowgen
				{
				IMethod methodrowgen8;
					IModelElement[] method__init__7Childs = method__init__7.getChildren();
					methodrowgen8 = ModelTestUtils.getAssertMethod( method__init__7Childs, "rowgen", 1 );
					ModelTestUtils.assertParameterNames( methodrowgen8, new String[] {"rowuses"} );
				}
			}
			//Function test:solve
			{
			IMethod methodsolve9;
				IModelElement[] classQueens6Childs = classQueens6.getChildren();
				methodsolve9 = ModelTestUtils.getAssertMethod( classQueens6Childs, "solve", 1 );
				ModelTestUtils.assertParameterNames( methodsolve9, new String[] {"self"} );
			}
			//Function test:printsolution
			{
			IMethod methodprintsolution10;
				IModelElement[] classQueens6Childs = classQueens6.getChildren();
				methodprintsolution10 = ModelTestUtils.getAssertMethod( classQueens6Childs, "printsolution", 2 );
				ModelTestUtils.assertParameterNames( methodprintsolution10, new String[] {"self", "row2col"} );
			}
		}
		//Class test
		{
		IType classKnights11;
			IModelElement[] moduleChilds = module.getChildren();
			classKnights11 = ModelTestUtils.getAssertClass( moduleChilds, "Knights" );
			//Function test:__init__
			{
			IMethod method__init__12;
				IModelElement[] classKnights11Childs = classKnights11.getChildren();
				method__init__12 = ModelTestUtils.getAssertMethod( classKnights11Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self", "m", "n", "hard"} );
				//Function test:remove_from_successors
				{
				IMethod methodremove_from_successors13;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodremove_from_successors13 = ModelTestUtils.getAssertMethod( method__init__12Childs, "remove_from_successors", 2 );
					ModelTestUtils.assertParameterNames( methodremove_from_successors13, new String[] {"i0", "len"} );
				}
				//Function test:add_to_successors
				{
				IMethod methodadd_to_successors14;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodadd_to_successors14 = ModelTestUtils.getAssertMethod( method__init__12Childs, "add_to_successors", 1 );
					ModelTestUtils.assertParameterNames( methodadd_to_successors14, new String[] {"i0"} );
				}
				//Function test:first
				{
				IMethod methodfirst15;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodfirst15 = ModelTestUtils.getAssertMethod( method__init__12Childs, "first", 0 );
				}
				//Function test:second
				{
				IMethod methodsecond16;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodsecond16 = ModelTestUtils.getAssertMethod( method__init__12Childs, "second", 0 );
				}
				//Function test:advance
				{
				IMethod methodadvance17;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodadvance17 = ModelTestUtils.getAssertMethod( method__init__12Childs, "advance", 1 );
					ModelTestUtils.assertParameterNames( methodadvance17, new String[] {"len"} );
				}
				//Function test:advance_hard
				{
				IMethod methodadvance_hard18;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodadvance_hard18 = ModelTestUtils.getAssertMethod( method__init__12Childs, "advance_hard", 3 );
					ModelTestUtils.assertParameterNames( methodadvance_hard18, new String[] {"vmid", "hmid", "len"} );
				}
				//Function test:last
				{
				IMethod methodlast19;
					IModelElement[] method__init__12Childs = method__init__12.getChildren();
					methodlast19 = ModelTestUtils.getAssertMethod( method__init__12Childs, "last", 0 );
				}
			}
			//Function test:coords2index
			{
			IMethod methodcoords2index20;
				IModelElement[] classKnights11Childs = classKnights11.getChildren();
				methodcoords2index20 = ModelTestUtils.getAssertMethod( classKnights11Childs, "coords2index", 3 );
				ModelTestUtils.assertParameterNames( methodcoords2index20, new String[] {"self", "i", "j"} );
			}
			//Function test:index2coords
			{
			IMethod methodindex2coords21;
				IModelElement[] classKnights11Childs = classKnights11.getChildren();
				methodindex2coords21 = ModelTestUtils.getAssertMethod( classKnights11Childs, "index2coords", 2 );
				ModelTestUtils.assertParameterNames( methodindex2coords21, new String[] {"self", "index"} );
			}
			//Function test:_init_board
			{
			IMethod method_init_board22;
				IModelElement[] classKnights11Childs = classKnights11.getChildren();
				method_init_board22 = ModelTestUtils.getAssertMethod( classKnights11Childs, "_init_board", 1 );
				ModelTestUtils.assertParameterNames( method_init_board22, new String[] {"self"} );
			}
			//Function test:solve
			{
			IMethod methodsolve23;
				IModelElement[] classKnights11Childs = classKnights11.getChildren();
				methodsolve23 = ModelTestUtils.getAssertMethod( classKnights11Childs, "solve", 1 );
				ModelTestUtils.assertParameterNames( methodsolve23, new String[] {"self"} );
			}
			//Function test:printsolution
			{
			IMethod methodprintsolution24;
				IModelElement[] classKnights11Childs = classKnights11.getChildren();
				methodprintsolution24 = ModelTestUtils.getAssertMethod( classKnights11Childs, "printsolution", 2 );
				ModelTestUtils.assertParameterNames( methodprintsolution24, new String[] {"self", "x"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "conjoin_tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "weakref_tests");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "__test__");
		}
		//Function test:test_main
		{
		IMethod methodtest_main25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main25 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main25, new String[] {"verbose"} );
		}

	}
	public void testModelGen4( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_binhex.py"));

		assertNotNull("Module test_binhex.py not found", module);
		assertEquals("test_binhex.py", module.getElementName());
		
		//Class test
		{
		IType classBinHexTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classBinHexTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "BinHexTestCase" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classBinHexTestCase0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classBinHexTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			{
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBinHexTestCase0Childs, "DATA");
			}
			//Function test:test_binhex
			{
			IMethod methodtest_binhex3;
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				methodtest_binhex3 = ModelTestUtils.getAssertMethod( classBinHexTestCase0Childs, "test_binhex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binhex3, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen5( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_repr.py"));

		assertNotNull("Module test_repr.py not found", module);
		assertEquals("test_repr.py", module.getElementName());
		
		//Function test:nestedTuple
		{
		IMethod methodnestedTuple0;
			IModelElement[] moduleChilds = module.getChildren();
			methodnestedTuple0 = ModelTestUtils.getAssertMethod( moduleChilds, "nestedTuple", 1 );
			ModelTestUtils.assertParameterNames( methodnestedTuple0, new String[] {"nesting"} );
		}
		//Class test
		{
		IType classReprTests1;
			IModelElement[] moduleChilds = module.getChildren();
			classReprTests1 = ModelTestUtils.getAssertClass( moduleChilds, "ReprTests" );
			//Function test:test_string
			{
			IMethod methodtest_string2;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_string2 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_string2, new String[] {"self"} );
			}
			//Function test:test_container
			{
			IMethod methodtest_container3;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_container3 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_container", 1 );
				ModelTestUtils.assertParameterNames( methodtest_container3, new String[] {"self"} );
			}
			//Function test:test_numbers
			{
			IMethod methodtest_numbers4;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_numbers4 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_numbers", 1 );
				ModelTestUtils.assertParameterNames( methodtest_numbers4, new String[] {"self"} );
			}
			//Function test:test_instance
			{
			IMethod methodtest_instance5;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_instance5 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_instance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instance5, new String[] {"self"} );
			}
			//Function test:test_file
			{
			IMethod methodtest_file6;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_file6 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_file6, new String[] {"self"} );
			}
			//Function test:test_lambda
			{
			IMethod methodtest_lambda7;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_lambda7 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_lambda", 1 );
				ModelTestUtils.assertParameterNames( methodtest_lambda7, new String[] {"self"} );
			}
			//Function test:test_builtin_function
			{
			IMethod methodtest_builtin_function8;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_builtin_function8 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_builtin_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_function8, new String[] {"self"} );
			}
			//Function test:test_xrange
			{
			IMethod methodtest_xrange9;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_xrange9 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_xrange", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xrange9, new String[] {"self"} );
			}
			//Function test:test_nesting
			{
			IMethod methodtest_nesting10;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_nesting10 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_nesting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nesting10, new String[] {"self"} );
			}
			//Function test:test_buffer
			{
			IMethod methodtest_buffer11;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_buffer11 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_buffer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_buffer11, new String[] {"self"} );
			}
			//Function test:test_cell
			{
			IMethod methodtest_cell12;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_cell12 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_cell", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cell12, new String[] {"self"} );
			}
			//Function test:test_descriptors
			{
			IMethod methodtest_descriptors13;
				IModelElement[] classReprTests1Childs = classReprTests1.getChildren();
				methodtest_descriptors13 = ModelTestUtils.getAssertMethod( classReprTests1Childs, "test_descriptors", 1 );
				ModelTestUtils.assertParameterNames( methodtest_descriptors13, new String[] {"self"} );
				//Class test
				{
				IType classC14;
					IModelElement[] methodtest_descriptors13Childs = methodtest_descriptors13.getChildren();
					classC14 = ModelTestUtils.getAssertClass( methodtest_descriptors13Childs, "C" );
					//Function test:foo
					{
					IMethod methodfoo15;
						IModelElement[] classC14Childs = classC14.getChildren();
						methodfoo15 = ModelTestUtils.getAssertMethod( classC14Childs, "foo", 1 );
						ModelTestUtils.assertParameterNames( methodfoo15, new String[] {"cls"} );
					}
				}
			}
		}
		//Function test:touch
		{
		IMethod methodtouch16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtouch16 = ModelTestUtils.getAssertMethod( moduleChilds, "touch", 2 );
			ModelTestUtils.assertParameterNames( methodtouch16, new String[] {"path", "text"} );
		}
		//Function test:zap
		{
		IMethod methodzap17;
			IModelElement[] moduleChilds = module.getChildren();
			methodzap17 = ModelTestUtils.getAssertMethod( moduleChilds, "zap", 3 );
			ModelTestUtils.assertParameterNames( methodzap17, new String[] {"actions", "dirname", "names"} );
		}
		//Class test
		{
		IType classLongReprTest18;
			IModelElement[] moduleChilds = module.getChildren();
			classLongReprTest18 = ModelTestUtils.getAssertClass( moduleChilds, "LongReprTest" );
			//Function test:setUp
			{
			IMethod methodsetUp19;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodsetUp19 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp19, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown20;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtearDown20 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown20, new String[] {"self"} );
			}
			//Function test:test_module
			{
			IMethod methodtest_module21;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_module21 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_module", 1 );
				ModelTestUtils.assertParameterNames( methodtest_module21, new String[] {"self"} );
			}
			//Function test:test_type
			{
			IMethod methodtest_type22;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_type22 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type22, new String[] {"self"} );
			}
			//Function test:test_object
			{
			IMethod methodtest_object23;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_object23 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_object", 1 );
				ModelTestUtils.assertParameterNames( methodtest_object23, new String[] {"self"} );
			}
			//Function test:test_class
			{
			IMethod methodtest_class24;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_class24 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_class", 1 );
				ModelTestUtils.assertParameterNames( methodtest_class24, new String[] {"self"} );
			}
			//Function test:test_instance
			{
			IMethod methodtest_instance25;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_instance25 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_instance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instance25, new String[] {"self"} );
			}
			//Function test:test_method
			{
			IMethod methodtest_method26;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_method26 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_method", 1 );
				ModelTestUtils.assertParameterNames( methodtest_method26, new String[] {"self"} );
			}
			//Function test:test_builtin_function
			{
			IMethod methodtest_builtin_function27;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_builtin_function27 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_builtin_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_function27, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classClassWithRepr28;
			IModelElement[] moduleChilds = module.getChildren();
			classClassWithRepr28 = ModelTestUtils.getAssertClass( moduleChilds, "ClassWithRepr" );
			//Function test:__init__
			{
			IMethod method__init__29;
				IModelElement[] classClassWithRepr28Childs = classClassWithRepr28.getChildren();
				method__init__29 = ModelTestUtils.getAssertMethod( classClassWithRepr28Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__29, new String[] {"self", "s"} );
			}
			//Function test:__repr__
			{
			IMethod method__repr__30;
				IModelElement[] classClassWithRepr28Childs = classClassWithRepr28.getChildren();
				method__repr__30 = ModelTestUtils.getAssertMethod( classClassWithRepr28Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__30, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classClassWithFailingRepr31;
			IModelElement[] moduleChilds = module.getChildren();
			classClassWithFailingRepr31 = ModelTestUtils.getAssertClass( moduleChilds, "ClassWithFailingRepr" );
			//Function test:__repr__
			{
			IMethod method__repr__32;
				IModelElement[] classClassWithFailingRepr31Childs = classClassWithFailingRepr31.getChildren();
				method__repr__32 = ModelTestUtils.getAssertMethod( classClassWithFailingRepr31Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__32, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main33;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main33 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen6( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_binop.py"));

		assertNotNull("Module test_binop.py not found", module);
		assertEquals("test_binop.py", module.getElementName());
		
		//Function test:gcd
		{
		IMethod methodgcd0;
			IModelElement[] moduleChilds = module.getChildren();
			methodgcd0 = ModelTestUtils.getAssertMethod( moduleChilds, "gcd", 2 );
			ModelTestUtils.assertParameterNames( methodgcd0, new String[] {"a", "b"} );
		}
		//Function test:isint
		{
		IMethod methodisint1;
			IModelElement[] moduleChilds = module.getChildren();
			methodisint1 = ModelTestUtils.getAssertMethod( moduleChilds, "isint", 1 );
			ModelTestUtils.assertParameterNames( methodisint1, new String[] {"x"} );
		}
		//Function test:isnum
		{
		IMethod methodisnum2;
			IModelElement[] moduleChilds = module.getChildren();
			methodisnum2 = ModelTestUtils.getAssertMethod( moduleChilds, "isnum", 1 );
			ModelTestUtils.assertParameterNames( methodisnum2, new String[] {"x"} );
		}
		//Function test:isRat
		{
		IMethod methodisRat3;
			IModelElement[] moduleChilds = module.getChildren();
			methodisRat3 = ModelTestUtils.getAssertMethod( moduleChilds, "isRat", 1 );
			ModelTestUtils.assertParameterNames( methodisRat3, new String[] {"x"} );
		}
		//Class test
		{
		IType classRat4;
			IModelElement[] moduleChilds = module.getChildren();
			classRat4 = ModelTestUtils.getAssertClass( moduleChilds, "Rat" );
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__slots__");
			}
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classRat4Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "num", "den"} );
			}
			//Function test:_get_num
			{
			IMethod method_get_num6;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method_get_num6 = ModelTestUtils.getAssertMethod( classRat4Childs, "_get_num", 1 );
				ModelTestUtils.assertParameterNames( method_get_num6, new String[] {"self"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "num");
			}
			//Function test:_get_den
			{
			IMethod method_get_den7;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method_get_den7 = ModelTestUtils.getAssertMethod( classRat4Childs, "_get_den", 1 );
				ModelTestUtils.assertParameterNames( method_get_den7, new String[] {"self"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "den");
			}
			//Function test:__repr__
			{
			IMethod method__repr__8;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__repr__8 = ModelTestUtils.getAssertMethod( classRat4Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__8, new String[] {"self"} );
			}
			//Function test:__str__
			{
			IMethod method__str__9;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__str__9 = ModelTestUtils.getAssertMethod( classRat4Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__9, new String[] {"self"} );
			}
			//Function test:__float__
			{
			IMethod method__float__10;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__float__10 = ModelTestUtils.getAssertMethod( classRat4Childs, "__float__", 1 );
				ModelTestUtils.assertParameterNames( method__float__10, new String[] {"self"} );
			}
			//Function test:__int__
			{
			IMethod method__int__11;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__int__11 = ModelTestUtils.getAssertMethod( classRat4Childs, "__int__", 1 );
				ModelTestUtils.assertParameterNames( method__int__11, new String[] {"self"} );
			}
			//Function test:__long__
			{
			IMethod method__long__12;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__long__12 = ModelTestUtils.getAssertMethod( classRat4Childs, "__long__", 1 );
				ModelTestUtils.assertParameterNames( method__long__12, new String[] {"self"} );
			}
			//Function test:__add__
			{
			IMethod method__add__13;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__add__13 = ModelTestUtils.getAssertMethod( classRat4Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__13, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__radd__");
			}
			//Function test:__sub__
			{
			IMethod method__sub__14;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__sub__14 = ModelTestUtils.getAssertMethod( classRat4Childs, "__sub__", 2 );
				ModelTestUtils.assertParameterNames( method__sub__14, new String[] {"self", "other"} );
			}
			//Function test:__rsub__
			{
			IMethod method__rsub__15;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rsub__15 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rsub__", 2 );
				ModelTestUtils.assertParameterNames( method__rsub__15, new String[] {"self", "other"} );
			}
			//Function test:__mul__
			{
			IMethod method__mul__16;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__mul__16 = ModelTestUtils.getAssertMethod( classRat4Childs, "__mul__", 2 );
				ModelTestUtils.assertParameterNames( method__mul__16, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__rmul__");
			}
			//Function test:__truediv__
			{
			IMethod method__truediv__17;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__truediv__17 = ModelTestUtils.getAssertMethod( classRat4Childs, "__truediv__", 2 );
				ModelTestUtils.assertParameterNames( method__truediv__17, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__div__");
			}
			//Function test:__rtruediv__
			{
			IMethod method__rtruediv__18;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rtruediv__18 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rtruediv__", 2 );
				ModelTestUtils.assertParameterNames( method__rtruediv__18, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__rdiv__");
			}
			//Function test:__floordiv__
			{
			IMethod method__floordiv__19;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__floordiv__19 = ModelTestUtils.getAssertMethod( classRat4Childs, "__floordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__floordiv__19, new String[] {"self", "other"} );
			}
			//Function test:__rfloordiv__
			{
			IMethod method__rfloordiv__20;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rfloordiv__20 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rfloordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rfloordiv__20, new String[] {"self", "other"} );
			}
			//Function test:__divmod__
			{
			IMethod method__divmod__21;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__divmod__21 = ModelTestUtils.getAssertMethod( classRat4Childs, "__divmod__", 2 );
				ModelTestUtils.assertParameterNames( method__divmod__21, new String[] {"self", "other"} );
			}
			//Function test:__rdivmod__
			{
			IMethod method__rdivmod__22;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rdivmod__22 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rdivmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rdivmod__22, new String[] {"self", "other"} );
			}
			//Function test:__mod__
			{
			IMethod method__mod__23;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__mod__23 = ModelTestUtils.getAssertMethod( classRat4Childs, "__mod__", 2 );
				ModelTestUtils.assertParameterNames( method__mod__23, new String[] {"self", "other"} );
			}
			//Function test:__rmod__
			{
			IMethod method__rmod__24;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rmod__24 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rmod__24, new String[] {"self", "other"} );
			}
			//Function test:__eq__
			{
			IMethod method__eq__25;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__eq__25 = ModelTestUtils.getAssertMethod( classRat4Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__25, new String[] {"self", "other"} );
			}
			//Function test:__ne__
			{
			IMethod method__ne__26;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__ne__26 = ModelTestUtils.getAssertMethod( classRat4Childs, "__ne__", 2 );
				ModelTestUtils.assertParameterNames( method__ne__26, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classRatTestCase27;
			IModelElement[] moduleChilds = module.getChildren();
			classRatTestCase27 = ModelTestUtils.getAssertClass( moduleChilds, "RatTestCase" );
			//Function test:test_gcd
			{
			IMethod methodtest_gcd28;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_gcd28 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_gcd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gcd28, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor29;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_constructor29 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor29, new String[] {"self"} );
			}
			//Function test:test_add
			{
			IMethod methodtest_add30;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_add30 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_add", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add30, new String[] {"self"} );
			}
			//Function test:test_sub
			{
			IMethod methodtest_sub31;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_sub31 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub31, new String[] {"self"} );
			}
			//Function test:test_mul
			{
			IMethod methodtest_mul32;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_mul32 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_mul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mul32, new String[] {"self"} );
			}
			//Function test:test_div
			{
			IMethod methodtest_div33;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_div33 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_div", 1 );
				ModelTestUtils.assertParameterNames( methodtest_div33, new String[] {"self"} );
			}
			//Function test:test_floordiv
			{
			IMethod methodtest_floordiv34;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_floordiv34 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_floordiv", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floordiv34, new String[] {"self"} );
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq35;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_eq35 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq35, new String[] {"self"} );
			}
			//Function test:test_future_div
			{
			IMethod methodtest_future_div36;
				IModelElement[] classRatTestCase27Childs = classRatTestCase27.getChildren();
				methodtest_future_div36 = ModelTestUtils.getAssertMethod( classRatTestCase27Childs, "test_future_div", 1 );
				ModelTestUtils.assertParameterNames( methodtest_future_div36, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "future_test");
		}
		//Function test:test_main
		{
		IMethod methodtest_main37;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main37 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen7( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_unary.py"));

		assertNotNull("Module test_unary.py not found", module);
		assertEquals("test_unary.py", module.getElementName());
		
		//Class test
		{
		IType classUnaryOpTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classUnaryOpTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "UnaryOpTestCase" );
			//Function test:test_negative
			{
			IMethod methodtest_negative1;
				IModelElement[] classUnaryOpTestCase0Childs = classUnaryOpTestCase0.getChildren();
				methodtest_negative1 = ModelTestUtils.getAssertMethod( classUnaryOpTestCase0Childs, "test_negative", 1 );
				ModelTestUtils.assertParameterNames( methodtest_negative1, new String[] {"self"} );
			}
			//Function test:test_positive
			{
			IMethod methodtest_positive2;
				IModelElement[] classUnaryOpTestCase0Childs = classUnaryOpTestCase0.getChildren();
				methodtest_positive2 = ModelTestUtils.getAssertMethod( classUnaryOpTestCase0Childs, "test_positive", 1 );
				ModelTestUtils.assertParameterNames( methodtest_positive2, new String[] {"self"} );
			}
			//Function test:test_invert
			{
			IMethod methodtest_invert3;
				IModelElement[] classUnaryOpTestCase0Childs = classUnaryOpTestCase0.getChildren();
				methodtest_invert3 = ModelTestUtils.getAssertMethod( classUnaryOpTestCase0Childs, "test_invert", 1 );
				ModelTestUtils.assertParameterNames( methodtest_invert3, new String[] {"self"} );
			}
			//Function test:test_no_overflow
			{
			IMethod methodtest_no_overflow4;
				IModelElement[] classUnaryOpTestCase0Childs = classUnaryOpTestCase0.getChildren();
				methodtest_no_overflow4 = ModelTestUtils.getAssertMethod( classUnaryOpTestCase0Childs, "test_no_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_no_overflow4, new String[] {"self"} );
			}
			//Function test:test_negation_of_exponentiation
			{
			IMethod methodtest_negation_of_exponentiation5;
				IModelElement[] classUnaryOpTestCase0Childs = classUnaryOpTestCase0.getChildren();
				methodtest_negation_of_exponentiation5 = ModelTestUtils.getAssertMethod( classUnaryOpTestCase0Childs, "test_negation_of_exponentiation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_negation_of_exponentiation5, new String[] {"self"} );
			}
			//Function test:test_bad_types
			{
			IMethod methodtest_bad_types6;
				IModelElement[] classUnaryOpTestCase0Childs = classUnaryOpTestCase0.getChildren();
				methodtest_bad_types6 = ModelTestUtils.getAssertMethod( classUnaryOpTestCase0Childs, "test_bad_types", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_types6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen8( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_atexit.py"));

		assertNotNull("Module test_atexit.py not found", module);
		assertEquals("test_atexit.py", module.getElementName());
		
		//Class test
		{
		IType classTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "TestCase" );
			//Function test:test_args
			{
			IMethod methodtest_args1;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_args1 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_args1, new String[] {"self"} );
			}
			//Function test:test_order
			{
			IMethod methodtest_order2;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_order2 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_order", 1 );
				ModelTestUtils.assertParameterNames( methodtest_order2, new String[] {"self"} );
			}
			//Function test:test_sys_override
			{
			IMethod methodtest_sys_override3;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_sys_override3 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_sys_override", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sys_override3, new String[] {"self"} );
			}
			//Function test:test_raise
			{
			IMethod methodtest_raise4;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodtest_raise4 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "test_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise4, new String[] {"self"} );
			}
			//Function test:h1
			{
			IMethod methodh15;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodh15 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "h1", 1 );
				ModelTestUtils.assertParameterNames( methodh15, new String[] {"self"} );
			}
			//Function test:h2
			{
			IMethod methodh26;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodh26 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "h2", 1 );
				ModelTestUtils.assertParameterNames( methodh26, new String[] {"self"} );
			}
			//Function test:h3
			{
			IMethod methodh37;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodh37 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "h3", 1 );
				ModelTestUtils.assertParameterNames( methodh37, new String[] {"self"} );
			}
			//Function test:h4
			{
			IMethod methodh48;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodh48 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "h4", 3 );
				ModelTestUtils.assertParameterNames( methodh48, new String[] {"self", "args", "kwargs"} );
			}
			//Function test:raise1
			{
			IMethod methodraise19;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodraise19 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "raise1", 1 );
				ModelTestUtils.assertParameterNames( methodraise19, new String[] {"self"} );
			}
			//Function test:raise2
			{
			IMethod methodraise210;
				IModelElement[] classTestCase0Childs = classTestCase0.getChildren();
				methodraise210 = ModelTestUtils.getAssertMethod( classTestCase0Childs, "raise2", 1 );
				ModelTestUtils.assertParameterNames( methodraise210, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main11 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen9( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_parser.py"));

		assertNotNull("Module test_parser.py not found", module);
		assertEquals("test_parser.py", module.getElementName());
		
		//Class test
		{
		IType classRoundtripLegalSyntaxTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classRoundtripLegalSyntaxTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "RoundtripLegalSyntaxTestCase" );
			//Function test:roundtrip
			{
			IMethod methodroundtrip1;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodroundtrip1 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "roundtrip", 3 );
				ModelTestUtils.assertParameterNames( methodroundtrip1, new String[] {"self", "f", "s"} );
			}
			//Function test:check_expr
			{
			IMethod methodcheck_expr2;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodcheck_expr2 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "check_expr", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_expr2, new String[] {"self", "s"} );
			}
			//Function test:check_suite
			{
			IMethod methodcheck_suite3;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodcheck_suite3 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "check_suite", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_suite3, new String[] {"self", "s"} );
			}
			//Function test:test_yield_statement
			{
			IMethod methodtest_yield_statement4;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_yield_statement4 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_yield_statement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_yield_statement4, new String[] {"self"} );
			}
			//Function test:test_expressions
			{
			IMethod methodtest_expressions5;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_expressions5 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_expressions", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expressions5, new String[] {"self"} );
			}
			//Function test:test_print
			{
			IMethod methodtest_print6;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_print6 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_print", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print6, new String[] {"self"} );
			}
			//Function test:test_simple_expression
			{
			IMethod methodtest_simple_expression7;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_simple_expression7 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_simple_expression", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_expression7, new String[] {"self"} );
			}
			//Function test:test_simple_assignments
			{
			IMethod methodtest_simple_assignments8;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_simple_assignments8 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_simple_assignments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_assignments8, new String[] {"self"} );
			}
			//Function test:test_simple_augmented_assignments
			{
			IMethod methodtest_simple_augmented_assignments9;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_simple_augmented_assignments9 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_simple_augmented_assignments", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_augmented_assignments9, new String[] {"self"} );
			}
			//Function test:test_function_defs
			{
			IMethod methodtest_function_defs10;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_function_defs10 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_function_defs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_function_defs10, new String[] {"self"} );
			}
			//Function test:test_import_from_statement
			{
			IMethod methodtest_import_from_statement11;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_import_from_statement11 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_import_from_statement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_import_from_statement11, new String[] {"self"} );
			}
			//Function test:test_basic_import_statement
			{
			IMethod methodtest_basic_import_statement12;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_basic_import_statement12 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_basic_import_statement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_import_statement12, new String[] {"self"} );
			}
			//Function test:test_pep263
			{
			IMethod methodtest_pep26313;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_pep26313 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_pep263", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pep26313, new String[] {"self"} );
			}
			//Function test:test_assert
			{
			IMethod methodtest_assert14;
				IModelElement[] classRoundtripLegalSyntaxTestCase0Childs = classRoundtripLegalSyntaxTestCase0.getChildren();
				methodtest_assert14 = ModelTestUtils.getAssertMethod( classRoundtripLegalSyntaxTestCase0Childs, "test_assert", 1 );
				ModelTestUtils.assertParameterNames( methodtest_assert14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIllegalSyntaxTestCase15;
			IModelElement[] moduleChilds = module.getChildren();
			classIllegalSyntaxTestCase15 = ModelTestUtils.getAssertClass( moduleChilds, "IllegalSyntaxTestCase" );
			//Function test:check_bad_tree
			{
			IMethod methodcheck_bad_tree16;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodcheck_bad_tree16 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "check_bad_tree", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_bad_tree16, new String[] {"self", "tree", "label"} );
			}
			//Function test:test_junk
			{
			IMethod methodtest_junk17;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_junk17 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_junk", 1 );
				ModelTestUtils.assertParameterNames( methodtest_junk17, new String[] {"self"} );
			}
			//Function test:test_illegal_yield_1
			{
			IMethod methodtest_illegal_yield_118;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_illegal_yield_118 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_illegal_yield_1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_yield_118, new String[] {"self"} );
			}
			//Function test:test_illegal_yield_2
			{
			IMethod methodtest_illegal_yield_219;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_illegal_yield_219 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_illegal_yield_2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_yield_219, new String[] {"self"} );
			}
			//Function test:test_print_chevron_comma
			{
			IMethod methodtest_print_chevron_comma20;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_print_chevron_comma20 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_print_chevron_comma", 1 );
				ModelTestUtils.assertParameterNames( methodtest_print_chevron_comma20, new String[] {"self"} );
			}
			//Function test:test_a_comma_comma_c
			{
			IMethod methodtest_a_comma_comma_c21;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_a_comma_comma_c21 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_a_comma_comma_c", 1 );
				ModelTestUtils.assertParameterNames( methodtest_a_comma_comma_c21, new String[] {"self"} );
			}
			//Function test:test_illegal_operator
			{
			IMethod methodtest_illegal_operator22;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_illegal_operator22 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_illegal_operator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_operator22, new String[] {"self"} );
			}
			//Function test:test_malformed_global
			{
			IMethod methodtest_malformed_global23;
				IModelElement[] classIllegalSyntaxTestCase15Childs = classIllegalSyntaxTestCase15.getChildren();
				methodtest_malformed_global23 = ModelTestUtils.getAssertMethod( classIllegalSyntaxTestCase15Childs, "test_malformed_global", 1 );
				ModelTestUtils.assertParameterNames( methodtest_malformed_global23, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main24 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen10( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_StringIO.py"));

		assertNotNull("Module test_StringIO.py not found", module);
		assertEquals("test_StringIO.py", module.getElementName());
		
		//Class test
		{
		IType classTestGenericStringIO0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestGenericStringIO0 = ModelTestUtils.getAssertClass( moduleChilds, "TestGenericStringIO" );
			{
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericStringIO0Childs, "_line");
			}
			{
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericStringIO0Childs, "constructor");
			}
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:test_reads
			{
			IMethod methodtest_reads2;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_reads2 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_reads", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reads2, new String[] {"self"} );
			}
			//Function test:test_writes
			{
			IMethod methodtest_writes3;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_writes3 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_writes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writes3, new String[] {"self"} );
			}
			//Function test:test_writelines
			{
			IMethod methodtest_writelines4;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_writelines4 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_writelines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writelines4, new String[] {"self"} );
			}
			//Function test:test_truncate
			{
			IMethod methodtest_truncate5;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_truncate5 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_truncate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truncate5, new String[] {"self"} );
			}
			//Function test:test_closed_flag
			{
			IMethod methodtest_closed_flag6;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_closed_flag6 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_closed_flag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_closed_flag6, new String[] {"self"} );
			}
			//Function test:test_iterator
			{
			IMethod methodtest_iterator7;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_iterator7 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_iterator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iterator7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestStringIO8;
			IModelElement[] moduleChilds = module.getChildren();
			classTestStringIO8 = ModelTestUtils.getAssertClass( moduleChilds, "TestStringIO" );
			{
				IModelElement[] classTestStringIO8Childs = classTestStringIO8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestStringIO8Childs, "MODULE");
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode9;
				IModelElement[] classTestStringIO8Childs = classTestStringIO8.getChildren();
				methodtest_unicode9 = ModelTestUtils.getAssertMethod( classTestStringIO8Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestcStringIO10;
			IModelElement[] moduleChilds = module.getChildren();
			classTestcStringIO10 = ModelTestUtils.getAssertClass( moduleChilds, "TestcStringIO" );
			{
				IModelElement[] classTestcStringIO10Childs = classTestcStringIO10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestcStringIO10Childs, "MODULE");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "buffer");
		}
		//Class test
		{
		IType classTestBufferStringIO11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBufferStringIO11 = ModelTestUtils.getAssertClass( moduleChilds, "TestBufferStringIO" );
			{
				IModelElement[] classTestBufferStringIO11Childs = classTestBufferStringIO11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBufferStringIO11Childs, "constructor");
			}
		}
		//Class test
		{
		IType classTestBuffercStringIO12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBuffercStringIO12 = ModelTestUtils.getAssertClass( moduleChilds, "TestBuffercStringIO" );
			{
				IModelElement[] classTestBuffercStringIO12Childs = classTestBuffercStringIO12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBuffercStringIO12Childs, "constructor");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main13 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen11( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cmath.py"));

		assertNotNull("Module test_cmath.py not found", module);
		assertEquals("test_cmath.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "testdict");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "f");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "r");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "p");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "e");
		}

	}
	public void testModelGen12( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_htmlparser.py"));

		assertNotNull("Module test_htmlparser.py not found", module);
		assertEquals("test_htmlparser.py", module.getElementName());
		
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
			//Function test:handle_starttag
			{
			IMethod methodhandle_starttag3;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_starttag3 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_starttag", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_starttag3, new String[] {"self", "tag", "attrs"} );
			}
			//Function test:handle_startendtag
			{
			IMethod methodhandle_startendtag4;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_startendtag4 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_startendtag", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_startendtag4, new String[] {"self", "tag", "attrs"} );
			}
			//Function test:handle_endtag
			{
			IMethod methodhandle_endtag5;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_endtag5 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_endtag", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_endtag5, new String[] {"self", "tag"} );
			}
			//Function test:handle_comment
			{
			IMethod methodhandle_comment6;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_comment6 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_comment", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_comment6, new String[] {"self", "data"} );
			}
			//Function test:handle_charref
			{
			IMethod methodhandle_charref7;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_charref7 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_charref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_charref7, new String[] {"self", "data"} );
			}
			//Function test:handle_data
			{
			IMethod methodhandle_data8;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_data8 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_data", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_data8, new String[] {"self", "data"} );
			}
			//Function test:handle_decl
			{
			IMethod methodhandle_decl9;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_decl9 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_decl", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_decl9, new String[] {"self", "data"} );
			}
			//Function test:handle_entityref
			{
			IMethod methodhandle_entityref10;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_entityref10 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_entityref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_entityref10, new String[] {"self", "data"} );
			}
			//Function test:handle_pi
			{
			IMethod methodhandle_pi11;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_pi11 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_pi", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_pi11, new String[] {"self", "data"} );
			}
			//Function test:unknown_decl
			{
			IMethod methodunknown_decl12;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_decl12 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_decl", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_decl12, new String[] {"self", "decl"} );
			}
		}
		//Class test
		{
		IType classEventCollectorExtra13;
			IModelElement[] moduleChilds = module.getChildren();
			classEventCollectorExtra13 = ModelTestUtils.getAssertClass( moduleChilds, "EventCollectorExtra" );
			//Function test:handle_starttag
			{
			IMethod methodhandle_starttag14;
				IModelElement[] classEventCollectorExtra13Childs = classEventCollectorExtra13.getChildren();
				methodhandle_starttag14 = ModelTestUtils.getAssertMethod( classEventCollectorExtra13Childs, "handle_starttag", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_starttag14, new String[] {"self", "tag", "attrs"} );
			}
		}
		//Class test
		{
		IType classTestCaseBase15;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCaseBase15 = ModelTestUtils.getAssertClass( moduleChilds, "TestCaseBase" );
			//Function test:_run_check
			{
			IMethod method_run_check16;
				IModelElement[] classTestCaseBase15Childs = classTestCaseBase15.getChildren();
				method_run_check16 = ModelTestUtils.getAssertMethod( classTestCaseBase15Childs, "_run_check", 4 );
				ModelTestUtils.assertParameterNames( method_run_check16, new String[] {"self", "source", "expected_events", "collector"} );
			}
			//Function test:_run_check_extra
			{
			IMethod method_run_check_extra17;
				IModelElement[] classTestCaseBase15Childs = classTestCaseBase15.getChildren();
				method_run_check_extra17 = ModelTestUtils.getAssertMethod( classTestCaseBase15Childs, "_run_check_extra", 3 );
				ModelTestUtils.assertParameterNames( method_run_check_extra17, new String[] {"self", "source", "events"} );
			}
			//Function test:_parse_error
			{
			IMethod method_parse_error18;
				IModelElement[] classTestCaseBase15Childs = classTestCaseBase15.getChildren();
				method_parse_error18 = ModelTestUtils.getAssertMethod( classTestCaseBase15Childs, "_parse_error", 2 );
				ModelTestUtils.assertParameterNames( method_parse_error18, new String[] {"self", "source"} );
				//Function test:parse
				{
				IMethod methodparse19;
					IModelElement[] method_parse_error18Childs = method_parse_error18.getChildren();
					methodparse19 = ModelTestUtils.getAssertMethod( method_parse_error18Childs, "parse", 1 );
					ModelTestUtils.assertParameterNames( methodparse19, new String[] {"source"} );
				}
			}
		}
		//Class test
		{
		IType classHTMLParserTestCase20;
			IModelElement[] moduleChilds = module.getChildren();
			classHTMLParserTestCase20 = ModelTestUtils.getAssertClass( moduleChilds, "HTMLParserTestCase" );
			//Function test:test_processing_instruction_only
			{
			IMethod methodtest_processing_instruction_only21;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_processing_instruction_only21 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_processing_instruction_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_processing_instruction_only21, new String[] {"self"} );
			}
			//Function test:test_simple_html
			{
			IMethod methodtest_simple_html22;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_simple_html22 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_simple_html", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_html22, new String[] {"self"} );
			}
			//Function test:test_unclosed_entityref
			{
			IMethod methodtest_unclosed_entityref23;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_unclosed_entityref23 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_unclosed_entityref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unclosed_entityref23, new String[] {"self"} );
			}
			//Function test:test_doctype_decl
			{
			IMethod methodtest_doctype_decl24;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_doctype_decl24 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_doctype_decl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doctype_decl24, new String[] {"self"} );
			}
			//Function test:test_bad_nesting
			{
			IMethod methodtest_bad_nesting25;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_bad_nesting25 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_bad_nesting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_nesting25, new String[] {"self"} );
			}
			//Function test:test_bare_ampersands
			{
			IMethod methodtest_bare_ampersands26;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_bare_ampersands26 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_bare_ampersands", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_ampersands26, new String[] {"self"} );
			}
			//Function test:test_bare_pointy_brackets
			{
			IMethod methodtest_bare_pointy_brackets27;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_bare_pointy_brackets27 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_bare_pointy_brackets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_pointy_brackets27, new String[] {"self"} );
			}
			//Function test:test_attr_syntax
			{
			IMethod methodtest_attr_syntax28;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_attr_syntax28 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_attr_syntax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_syntax28, new String[] {"self"} );
			}
			//Function test:test_attr_values
			{
			IMethod methodtest_attr_values29;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_attr_values29 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_attr_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_values29, new String[] {"self"} );
			}
			//Function test:test_attr_entity_replacement
			{
			IMethod methodtest_attr_entity_replacement30;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_attr_entity_replacement30 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_attr_entity_replacement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_entity_replacement30, new String[] {"self"} );
			}
			//Function test:test_attr_funky_names
			{
			IMethod methodtest_attr_funky_names31;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_attr_funky_names31 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_attr_funky_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_funky_names31, new String[] {"self"} );
			}
			//Function test:test_illegal_declarations
			{
			IMethod methodtest_illegal_declarations32;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_illegal_declarations32 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_illegal_declarations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_declarations32, new String[] {"self"} );
			}
			//Function test:test_starttag_end_boundary
			{
			IMethod methodtest_starttag_end_boundary33;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_starttag_end_boundary33 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_starttag_end_boundary", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starttag_end_boundary33, new String[] {"self"} );
			}
			//Function test:test_buffer_artefacts
			{
			IMethod methodtest_buffer_artefacts34;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_buffer_artefacts34 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_buffer_artefacts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_buffer_artefacts34, new String[] {"self"} );
			}
			//Function test:test_starttag_junk_chars
			{
			IMethod methodtest_starttag_junk_chars35;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_starttag_junk_chars35 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_starttag_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starttag_junk_chars35, new String[] {"self"} );
			}
			//Function test:test_declaration_junk_chars
			{
			IMethod methodtest_declaration_junk_chars36;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_declaration_junk_chars36 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_declaration_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_declaration_junk_chars36, new String[] {"self"} );
			}
			//Function test:test_startendtag
			{
			IMethod methodtest_startendtag37;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_startendtag37 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_startendtag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_startendtag37, new String[] {"self"} );
			}
			//Function test:test_get_starttag_text
			{
			IMethod methodtest_get_starttag_text38;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_get_starttag_text38 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_get_starttag_text", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_starttag_text38, new String[] {"self"} );
			}
			//Function test:test_cdata_content
			{
			IMethod methodtest_cdata_content39;
				IModelElement[] classHTMLParserTestCase20Childs = classHTMLParserTestCase20.getChildren();
				methodtest_cdata_content39 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase20Childs, "test_cdata_content", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cdata_content39, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main40;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main40 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen13( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("testcodec.py"));

		assertNotNull("Module testcodec.py not found", module);
		assertEquals("testcodec.py", module.getElementName());
		
		//Class test
		{
		IType classCodec0;
			IModelElement[] moduleChilds = module.getChildren();
			classCodec0 = ModelTestUtils.getAssertClass( moduleChilds, "Codec" );
			//Function test:encode
			{
			IMethod methodencode1;
				IModelElement[] classCodec0Childs = classCodec0.getChildren();
				methodencode1 = ModelTestUtils.getAssertMethod( classCodec0Childs, "encode", 3 );
				ModelTestUtils.assertParameterNames( methodencode1, new String[] {"self", "input", "errors"} );
			}
			//Function test:decode
			{
			IMethod methoddecode2;
				IModelElement[] classCodec0Childs = classCodec0.getChildren();
				methoddecode2 = ModelTestUtils.getAssertMethod( classCodec0Childs, "decode", 3 );
				ModelTestUtils.assertParameterNames( methoddecode2, new String[] {"self", "input", "errors"} );
			}
		}
		//Class test
		{
		IType classStreamWriter3;
			IModelElement[] moduleChilds = module.getChildren();
			classStreamWriter3 = ModelTestUtils.getAssertClass( moduleChilds, "StreamWriter" );
		}
		//Class test
		{
		IType classStreamReader4;
			IModelElement[] moduleChilds = module.getChildren();
			classStreamReader4 = ModelTestUtils.getAssertClass( moduleChilds, "StreamReader" );
		}
		//Function test:getregentry
		{
		IMethod methodgetregentry5;
			IModelElement[] moduleChilds = module.getChildren();
			methodgetregentry5 = ModelTestUtils.getAssertMethod( moduleChilds, "getregentry", 0 );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "decoding_map");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "encoding_map");
		}

	}
	public void testModelGen14( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("pickletester.py"));

		assertNotNull("Module pickletester.py not found", module);
		assertEquals("pickletester.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "protocols");
		}
		//Function test:opcode_in_pickle
		{
		IMethod methodopcode_in_pickle0;
			IModelElement[] moduleChilds = module.getChildren();
			methodopcode_in_pickle0 = ModelTestUtils.getAssertMethod( moduleChilds, "opcode_in_pickle", 2 );
			ModelTestUtils.assertParameterNames( methodopcode_in_pickle0, new String[] {"code", "pickle"} );
		}
		//Function test:count_opcode
		{
		IMethod methodcount_opcode1;
			IModelElement[] moduleChilds = module.getChildren();
			methodcount_opcode1 = ModelTestUtils.getAssertMethod( moduleChilds, "count_opcode", 2 );
			ModelTestUtils.assertParameterNames( methodcount_opcode1, new String[] {"code", "pickle"} );
		}
		//Class test
		{
		IType classExtensionSaver2;
			IModelElement[] moduleChilds = module.getChildren();
			classExtensionSaver2 = ModelTestUtils.getAssertClass( moduleChilds, "ExtensionSaver" );
			//Function test:__init__
			{
			IMethod method__init__3;
				IModelElement[] classExtensionSaver2Childs = classExtensionSaver2.getChildren();
				method__init__3 = ModelTestUtils.getAssertMethod( classExtensionSaver2Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__3, new String[] {"self", "code"} );
			}
			//Function test:restore
			{
			IMethod methodrestore4;
				IModelElement[] classExtensionSaver2Childs = classExtensionSaver2.getChildren();
				methodrestore4 = ModelTestUtils.getAssertMethod( classExtensionSaver2Childs, "restore", 1 );
				ModelTestUtils.assertParameterNames( methodrestore4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC5;
			IModelElement[] moduleChilds = module.getChildren();
			classC5 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:__cmp__
			{
			IMethod method__cmp__6;
				IModelElement[] classC5Childs = classC5.getChildren();
				method__cmp__6 = ModelTestUtils.getAssertMethod( classC5Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__6, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classmyint7;
			IModelElement[] moduleChilds = module.getChildren();
			classmyint7 = ModelTestUtils.getAssertClass( moduleChilds, "myint" );
			//Function test:__init__
			{
			IMethod method__init__8;
				IModelElement[] classmyint7Childs = classmyint7.getChildren();
				method__init__8 = ModelTestUtils.getAssertMethod( classmyint7Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "x"} );
			}
		}
		//Class test
		{
		IType classinitarg9;
			IModelElement[] moduleChilds = module.getChildren();
			classinitarg9 = ModelTestUtils.getAssertClass( moduleChilds, "initarg" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classinitarg9Childs = classinitarg9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classinitarg9Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self", "a", "b"} );
			}
			//Function test:__getinitargs__
			{
			IMethod method__getinitargs__11;
				IModelElement[] classinitarg9Childs = classinitarg9.getChildren();
				method__getinitargs__11 = ModelTestUtils.getAssertMethod( classinitarg9Childs, "__getinitargs__", 1 );
				ModelTestUtils.assertParameterNames( method__getinitargs__11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classmetaclass12;
			IModelElement[] moduleChilds = module.getChildren();
			classmetaclass12 = ModelTestUtils.getAssertClass( moduleChilds, "metaclass" );
		}
		//Class test
		{
		IType classuse_metaclass13;
			IModelElement[] moduleChilds = module.getChildren();
			classuse_metaclass13 = ModelTestUtils.getAssertClass( moduleChilds, "use_metaclass" );
			{
				IModelElement[] classuse_metaclass13Childs = classuse_metaclass13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classuse_metaclass13Childs, "__metaclass__");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA0");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA0_DIS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA1");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA1_DIS");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA2");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "DATA2_DIS");
		}
		//Function test:create_data
		{
		IMethod methodcreate_data14;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_data14 = ModelTestUtils.getAssertMethod( moduleChilds, "create_data", 0 );
		}
		//Class test
		{
		IType classAbstractPickleTests15;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractPickleTests15 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractPickleTests" );
			{
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractPickleTests15Childs, "_testdata");
			}
			//Function test:setUp
			{
			IMethod methodsetUp16;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodsetUp16 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp16, new String[] {"self"} );
			}
			//Function test:test_misc
			{
			IMethod methodtest_misc17;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_misc17 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_misc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_misc17, new String[] {"self"} );
			}
			//Function test:test_roundtrip_equality
			{
			IMethod methodtest_roundtrip_equality18;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_roundtrip_equality18 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_roundtrip_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_equality18, new String[] {"self"} );
			}
			//Function test:test_load_from_canned_string
			{
			IMethod methodtest_load_from_canned_string19;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_load_from_canned_string19 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_load_from_canned_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_load_from_canned_string19, new String[] {"self"} );
			}
			//Function test:dont_test_disassembly
			{
			IMethod methoddont_test_disassembly20;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methoddont_test_disassembly20 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "dont_test_disassembly", 1 );
				ModelTestUtils.assertParameterNames( methoddont_test_disassembly20, new String[] {"self"} );
			}
			//Function test:test_recursive_list
			{
			IMethod methodtest_recursive_list21;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_recursive_list21 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_recursive_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_list21, new String[] {"self"} );
			}
			//Function test:test_recursive_dict
			{
			IMethod methodtest_recursive_dict22;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_recursive_dict22 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_recursive_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_dict22, new String[] {"self"} );
			}
			//Function test:test_recursive_inst
			{
			IMethod methodtest_recursive_inst23;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_recursive_inst23 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_recursive_inst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_inst23, new String[] {"self"} );
			}
			//Function test:test_recursive_multi
			{
			IMethod methodtest_recursive_multi24;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_recursive_multi24 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_recursive_multi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_multi24, new String[] {"self"} );
			}
			//Function test:test_garyp
			{
			IMethod methodtest_garyp25;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_garyp25 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_garyp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_garyp25, new String[] {"self"} );
			}
			//Function test:test_insecure_strings
			{
			IMethod methodtest_insecure_strings26;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_insecure_strings26 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_insecure_strings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insecure_strings26, new String[] {"self"} );
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode27;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_unicode27 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode27, new String[] {"self"} );
			}
			//Function test:test_ints
			{
			IMethod methodtest_ints28;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_ints28 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_ints", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ints28, new String[] {"self"} );
			}
			//Function test:test_maxint64
			{
			IMethod methodtest_maxint6429;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_maxint6429 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_maxint64", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maxint6429, new String[] {"self"} );
			}
			//Function test:test_long
			{
			IMethod methodtest_long30;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_long30 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long30, new String[] {"self"} );
			}
			//Function test:test_reduce
			{
			IMethod methodtest_reduce31;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_reduce31 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce31, new String[] {"self"} );
			}
			//Function test:test_getinitargs
			{
			IMethod methodtest_getinitargs32;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_getinitargs32 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_getinitargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getinitargs32, new String[] {"self"} );
			}
			//Function test:test_metaclass
			{
			IMethod methodtest_metaclass33;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_metaclass33 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_metaclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_metaclass33, new String[] {"self"} );
			}
			//Function test:test_structseq
			{
			IMethod methodtest_structseq34;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_structseq34 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_structseq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_structseq34, new String[] {"self"} );
			}
			//Function test:test_proto
			{
			IMethod methodtest_proto35;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_proto35 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_proto", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proto35, new String[] {"self"} );
			}
			//Function test:test_long1
			{
			IMethod methodtest_long136;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_long136 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_long1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long136, new String[] {"self"} );
			}
			//Function test:test_long4
			{
			IMethod methodtest_long437;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_long437 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_long4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long437, new String[] {"self"} );
			}
			//Function test:test_short_tuples
			{
			IMethod methodtest_short_tuples38;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_short_tuples38 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_short_tuples", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_tuples38, new String[] {"self"} );
			}
			//Function test:test_singletons
			{
			IMethod methodtest_singletons39;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_singletons39 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_singletons", 1 );
				ModelTestUtils.assertParameterNames( methodtest_singletons39, new String[] {"self"} );
			}
			//Function test:test_newobj_tuple
			{
			IMethod methodtest_newobj_tuple40;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_newobj_tuple40 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_newobj_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_tuple40, new String[] {"self"} );
			}
			//Function test:test_newobj_list
			{
			IMethod methodtest_newobj_list41;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_newobj_list41 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_newobj_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_list41, new String[] {"self"} );
			}
			//Function test:test_newobj_generic
			{
			IMethod methodtest_newobj_generic42;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_newobj_generic42 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_newobj_generic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_generic42, new String[] {"self"} );
			}
			//Function test:produce_global_ext
			{
			IMethod methodproduce_global_ext43;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodproduce_global_ext43 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "produce_global_ext", 3 );
				ModelTestUtils.assertParameterNames( methodproduce_global_ext43, new String[] {"self", "extcode", "opcode"} );
			}
			//Function test:test_global_ext1
			{
			IMethod methodtest_global_ext144;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_global_ext144 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_global_ext1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_ext144, new String[] {"self"} );
			}
			//Function test:test_global_ext2
			{
			IMethod methodtest_global_ext245;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_global_ext245 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_global_ext2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_ext245, new String[] {"self"} );
			}
			//Function test:test_global_ext4
			{
			IMethod methodtest_global_ext446;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_global_ext446 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_global_ext4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_ext446, new String[] {"self"} );
			}
			//Function test:test_list_chunking
			{
			IMethod methodtest_list_chunking47;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_list_chunking47 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_list_chunking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_list_chunking47, new String[] {"self"} );
			}
			//Function test:test_dict_chunking
			{
			IMethod methodtest_dict_chunking48;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_dict_chunking48 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_dict_chunking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dict_chunking48, new String[] {"self"} );
			}
			//Function test:test_simple_newobj
			{
			IMethod methodtest_simple_newobj49;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_simple_newobj49 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_simple_newobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_newobj49, new String[] {"self"} );
			}
			//Function test:test_newobj_list_slots
			{
			IMethod methodtest_newobj_list_slots50;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_newobj_list_slots50 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_newobj_list_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_list_slots50, new String[] {"self"} );
			}
			//Function test:test_reduce_overrides_default_reduce_ex
			{
			IMethod methodtest_reduce_overrides_default_reduce_ex51;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_reduce_overrides_default_reduce_ex51 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_reduce_overrides_default_reduce_ex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_overrides_default_reduce_ex51, new String[] {"self"} );
			}
			//Function test:test_reduce_ex_called
			{
			IMethod methodtest_reduce_ex_called52;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_reduce_ex_called52 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_reduce_ex_called", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_ex_called52, new String[] {"self"} );
			}
			//Function test:test_reduce_ex_overrides_reduce
			{
			IMethod methodtest_reduce_ex_overrides_reduce53;
				IModelElement[] classAbstractPickleTests15Childs = classAbstractPickleTests15.getChildren();
				methodtest_reduce_ex_overrides_reduce53 = ModelTestUtils.getAssertMethod( classAbstractPickleTests15Childs, "test_reduce_ex_overrides_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_ex_overrides_reduce53, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classREX_one54;
			IModelElement[] moduleChilds = module.getChildren();
			classREX_one54 = ModelTestUtils.getAssertClass( moduleChilds, "REX_one" );
			{
				IModelElement[] classREX_one54Childs = classREX_one54.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_one54Childs, "_reduce_called");
			}
			//Function test:__reduce__
			{
			IMethod method__reduce__55;
				IModelElement[] classREX_one54Childs = classREX_one54.getChildren();
				method__reduce__55 = ModelTestUtils.getAssertMethod( classREX_one54Childs, "__reduce__", 1 );
				ModelTestUtils.assertParameterNames( method__reduce__55, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classREX_two56;
			IModelElement[] moduleChilds = module.getChildren();
			classREX_two56 = ModelTestUtils.getAssertClass( moduleChilds, "REX_two" );
			{
				IModelElement[] classREX_two56Childs = classREX_two56.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_two56Childs, "_proto");
			}
			//Function test:__reduce_ex__
			{
			IMethod method__reduce_ex__57;
				IModelElement[] classREX_two56Childs = classREX_two56.getChildren();
				method__reduce_ex__57 = ModelTestUtils.getAssertMethod( classREX_two56Childs, "__reduce_ex__", 2 );
				ModelTestUtils.assertParameterNames( method__reduce_ex__57, new String[] {"self", "proto"} );
			}
		}
		//Class test
		{
		IType classREX_three58;
			IModelElement[] moduleChilds = module.getChildren();
			classREX_three58 = ModelTestUtils.getAssertClass( moduleChilds, "REX_three" );
			{
				IModelElement[] classREX_three58Childs = classREX_three58.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_three58Childs, "_proto");
			}
			//Function test:__reduce_ex__
			{
			IMethod method__reduce_ex__59;
				IModelElement[] classREX_three58Childs = classREX_three58.getChildren();
				method__reduce_ex__59 = ModelTestUtils.getAssertMethod( classREX_three58Childs, "__reduce_ex__", 2 );
				ModelTestUtils.assertParameterNames( method__reduce_ex__59, new String[] {"self", "proto"} );
			}
			//Function test:__reduce__
			{
			IMethod method__reduce__60;
				IModelElement[] classREX_three58Childs = classREX_three58.getChildren();
				method__reduce__60 = ModelTestUtils.getAssertMethod( classREX_three58Childs, "__reduce__", 1 );
				ModelTestUtils.assertParameterNames( method__reduce__60, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMyInt61;
			IModelElement[] moduleChilds = module.getChildren();
			classMyInt61 = ModelTestUtils.getAssertClass( moduleChilds, "MyInt" );
			{
				IModelElement[] classMyInt61Childs = classMyInt61.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyInt61Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyLong62;
			IModelElement[] moduleChilds = module.getChildren();
			classMyLong62 = ModelTestUtils.getAssertClass( moduleChilds, "MyLong" );
			{
				IModelElement[] classMyLong62Childs = classMyLong62.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyLong62Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyFloat63;
			IModelElement[] moduleChilds = module.getChildren();
			classMyFloat63 = ModelTestUtils.getAssertClass( moduleChilds, "MyFloat" );
			{
				IModelElement[] classMyFloat63Childs = classMyFloat63.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyFloat63Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyComplex64;
			IModelElement[] moduleChilds = module.getChildren();
			classMyComplex64 = ModelTestUtils.getAssertClass( moduleChilds, "MyComplex" );
			{
				IModelElement[] classMyComplex64Childs = classMyComplex64.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyComplex64Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyStr65;
			IModelElement[] moduleChilds = module.getChildren();
			classMyStr65 = ModelTestUtils.getAssertClass( moduleChilds, "MyStr" );
			{
				IModelElement[] classMyStr65Childs = classMyStr65.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyStr65Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyUnicode66;
			IModelElement[] moduleChilds = module.getChildren();
			classMyUnicode66 = ModelTestUtils.getAssertClass( moduleChilds, "MyUnicode" );
			{
				IModelElement[] classMyUnicode66Childs = classMyUnicode66.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyUnicode66Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyTuple67;
			IModelElement[] moduleChilds = module.getChildren();
			classMyTuple67 = ModelTestUtils.getAssertClass( moduleChilds, "MyTuple" );
			{
				IModelElement[] classMyTuple67Childs = classMyTuple67.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyTuple67Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyList68;
			IModelElement[] moduleChilds = module.getChildren();
			classMyList68 = ModelTestUtils.getAssertClass( moduleChilds, "MyList" );
			{
				IModelElement[] classMyList68Childs = classMyList68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyList68Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyDict69;
			IModelElement[] moduleChilds = module.getChildren();
			classMyDict69 = ModelTestUtils.getAssertClass( moduleChilds, "MyDict" );
			{
				IModelElement[] classMyDict69Childs = classMyDict69.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyDict69Childs, "sample");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "myclasses");
		}
		//Class test
		{
		IType classSlotList70;
			IModelElement[] moduleChilds = module.getChildren();
			classSlotList70 = ModelTestUtils.getAssertClass( moduleChilds, "SlotList" );
			{
				IModelElement[] classSlotList70Childs = classSlotList70.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSlotList70Childs, "__slots__");
			}
		}
		//Class test
		{
		IType classSimpleNewObj71;
			IModelElement[] moduleChilds = module.getChildren();
			classSimpleNewObj71 = ModelTestUtils.getAssertClass( moduleChilds, "SimpleNewObj" );
			//Function test:__init__
			{
			IMethod method__init__72;
				IModelElement[] classSimpleNewObj71Childs = classSimpleNewObj71.getChildren();
				method__init__72 = ModelTestUtils.getAssertMethod( classSimpleNewObj71Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__72, new String[] {"self", "a", "b", "c"} );
			}
		}
		//Class test
		{
		IType classAbstractPickleModuleTests73;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractPickleModuleTests73 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractPickleModuleTests" );
			//Function test:test_dump_closed_file
			{
			IMethod methodtest_dump_closed_file74;
				IModelElement[] classAbstractPickleModuleTests73Childs = classAbstractPickleModuleTests73.getChildren();
				methodtest_dump_closed_file74 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests73Childs, "test_dump_closed_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_closed_file74, new String[] {"self"} );
			}
			//Function test:test_load_closed_file
			{
			IMethod methodtest_load_closed_file75;
				IModelElement[] classAbstractPickleModuleTests73Childs = classAbstractPickleModuleTests73.getChildren();
				methodtest_load_closed_file75 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests73Childs, "test_load_closed_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_load_closed_file75, new String[] {"self"} );
			}
			//Function test:test_highest_protocol
			{
			IMethod methodtest_highest_protocol76;
				IModelElement[] classAbstractPickleModuleTests73Childs = classAbstractPickleModuleTests73.getChildren();
				methodtest_highest_protocol76 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests73Childs, "test_highest_protocol", 1 );
				ModelTestUtils.assertParameterNames( methodtest_highest_protocol76, new String[] {"self"} );
			}
			//Function test:test_callapi
			{
			IMethod methodtest_callapi77;
				IModelElement[] classAbstractPickleModuleTests73Childs = classAbstractPickleModuleTests73.getChildren();
				methodtest_callapi77 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests73Childs, "test_callapi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callapi77, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classAbstractPersistentPicklerTests78;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractPersistentPicklerTests78 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractPersistentPicklerTests" );
			//Function test:persistent_id
			{
			IMethod methodpersistent_id79;
				IModelElement[] classAbstractPersistentPicklerTests78Childs = classAbstractPersistentPicklerTests78.getChildren();
				methodpersistent_id79 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests78Childs, "persistent_id", 2 );
				ModelTestUtils.assertParameterNames( methodpersistent_id79, new String[] {"self", "object"} );
			}
			//Function test:persistent_load
			{
			IMethod methodpersistent_load80;
				IModelElement[] classAbstractPersistentPicklerTests78Childs = classAbstractPersistentPicklerTests78.getChildren();
				methodpersistent_load80 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests78Childs, "persistent_load", 2 );
				ModelTestUtils.assertParameterNames( methodpersistent_load80, new String[] {"self", "oid"} );
			}
			//Function test:test_persistence
			{
			IMethod methodtest_persistence81;
				IModelElement[] classAbstractPersistentPicklerTests78Childs = classAbstractPersistentPicklerTests78.getChildren();
				methodtest_persistence81 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests78Childs, "test_persistence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_persistence81, new String[] {"self"} );
			}
			//Function test:test_bin_persistence
			{
			IMethod methodtest_bin_persistence82;
				IModelElement[] classAbstractPersistentPicklerTests78Childs = classAbstractPersistentPicklerTests78.getChildren();
				methodtest_bin_persistence82 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests78Childs, "test_bin_persistence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bin_persistence82, new String[] {"self"} );
			}
		}

	}
	public void testModelGen15( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_al.py"));

		assertNotNull("Module test_al.py not found", module);
		assertEquals("test_al.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "alattrs");
		}
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen16( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cd.py"));

		assertNotNull("Module test_cd.py not found", module);
		assertEquals("test_cd.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "cdattrs");
		}
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen17( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_cl.py"));

		assertNotNull("Module test_cl.py not found", module);
		assertEquals("test_cl.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "clattrs");
		}
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen18( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dl.py"));

		assertNotNull("Module test_dl.py not found", module);
		assertEquals("test_dl.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "sharedlibs");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "l");
		}

	}
	public void testModelGen19( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_select.py"));

		assertNotNull("Module test_select.py not found", module);
		assertEquals("test_select.py", module.getElementName());
		
		//Class test
		{
		IType classNope0;
			IModelElement[] moduleChilds = module.getChildren();
			classNope0 = ModelTestUtils.getAssertClass( moduleChilds, "Nope" );
		}
		//Class test
		{
		IType classAlmost1;
			IModelElement[] moduleChilds = module.getChildren();
			classAlmost1 = ModelTestUtils.getAssertClass( moduleChilds, "Almost" );
			//Function test:fileno
			{
			IMethod methodfileno2;
				IModelElement[] classAlmost1Childs = classAlmost1.getChildren();
				methodfileno2 = ModelTestUtils.getAssertMethod( classAlmost1Childs, "fileno", 1 );
				ModelTestUtils.assertParameterNames( methodfileno2, new String[] {"self"} );
			}
		}
		//Function test:test
		{
		IMethod methodtest3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest3 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen20( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_gc.py"));

		assertNotNull("Module test_gc.py not found", module);
		assertEquals("test_gc.py", module.getElementName());
		
		//Function test:expect
		{
		IMethod methodexpect0;
			IModelElement[] moduleChilds = module.getChildren();
			methodexpect0 = ModelTestUtils.getAssertMethod( moduleChilds, "expect", 3 );
			ModelTestUtils.assertParameterNames( methodexpect0, new String[] {"actual", "expected", "name"} );
		}
		//Function test:expect_nonzero
		{
		IMethod methodexpect_nonzero1;
			IModelElement[] moduleChilds = module.getChildren();
			methodexpect_nonzero1 = ModelTestUtils.getAssertMethod( moduleChilds, "expect_nonzero", 2 );
			ModelTestUtils.assertParameterNames( methodexpect_nonzero1, new String[] {"actual", "name"} );
		}
		//Function test:run_test
		{
		IMethod methodrun_test2;
			IModelElement[] moduleChilds = module.getChildren();
			methodrun_test2 = ModelTestUtils.getAssertMethod( moduleChilds, "run_test", 2 );
			ModelTestUtils.assertParameterNames( methodrun_test2, new String[] {"name", "thunk"} );
		}
		//Function test:test_list
		{
		IMethod methodtest_list3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_list3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_list", 0 );
		}
		//Function test:test_dict
		{
		IMethod methodtest_dict4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_dict4 = ModelTestUtils.getAssertMethod( moduleChilds, "test_dict", 0 );
		}
		//Function test:test_tuple
		{
		IMethod methodtest_tuple5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_tuple5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_tuple", 0 );
		}
		//Function test:test_class
		{
		IMethod methodtest_class6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_class6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_class", 0 );
			//Class test
			{
			IType classA7;
				IModelElement[] methodtest_class6Childs = methodtest_class6.getChildren();
				classA7 = ModelTestUtils.getAssertClass( methodtest_class6Childs, "A" );
			}
		}
		//Function test:test_newstyleclass
		{
		IMethod methodtest_newstyleclass8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_newstyleclass8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_newstyleclass", 0 );
			//Class test
			{
			IType classA9;
				IModelElement[] methodtest_newstyleclass8Childs = methodtest_newstyleclass8.getChildren();
				classA9 = ModelTestUtils.getAssertClass( methodtest_newstyleclass8Childs, "A" );
			}
		}
		//Function test:test_instance
		{
		IMethod methodtest_instance10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_instance10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_instance", 0 );
			//Class test
			{
			IType classA11;
				IModelElement[] methodtest_instance10Childs = methodtest_instance10.getChildren();
				classA11 = ModelTestUtils.getAssertClass( methodtest_instance10Childs, "A" );
			}
		}
		//Function test:test_newinstance
		{
		IMethod methodtest_newinstance12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_newinstance12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_newinstance", 0 );
			//Class test
			{
			IType classA13;
				IModelElement[] methodtest_newinstance12Childs = methodtest_newinstance12.getChildren();
				classA13 = ModelTestUtils.getAssertClass( methodtest_newinstance12Childs, "A" );
			}
			//Class test
			{
			IType classB14;
				IModelElement[] methodtest_newinstance12Childs = methodtest_newinstance12.getChildren();
				classB14 = ModelTestUtils.getAssertClass( methodtest_newinstance12Childs, "B" );
			}
			//Class test
			{
			IType classC15;
				IModelElement[] methodtest_newinstance12Childs = methodtest_newinstance12.getChildren();
				classC15 = ModelTestUtils.getAssertClass( methodtest_newinstance12Childs, "C" );
			}
		}
		//Function test:test_method
		{
		IMethod methodtest_method16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_method16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_method", 0 );
			//Class test
			{
			IType classA17;
				IModelElement[] methodtest_method16Childs = methodtest_method16.getChildren();
				classA17 = ModelTestUtils.getAssertClass( methodtest_method16Childs, "A" );
				//Function test:__init__
				{
				IMethod method__init__18;
					IModelElement[] classA17Childs = classA17.getChildren();
					method__init__18 = ModelTestUtils.getAssertMethod( classA17Childs, "__init__", 1 );
					ModelTestUtils.assertParameterNames( method__init__18, new String[] {"self"} );
				}
			}
		}
		//Function test:test_finalizer
		{
		IMethod methodtest_finalizer19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_finalizer19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_finalizer", 0 );
			//Class test
			{
			IType classA20;
				IModelElement[] methodtest_finalizer19Childs = methodtest_finalizer19.getChildren();
				classA20 = ModelTestUtils.getAssertClass( methodtest_finalizer19Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__21;
					IModelElement[] classA20Childs = classA20.getChildren();
					method__del__21 = ModelTestUtils.getAssertMethod( classA20Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__21, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB22;
				IModelElement[] methodtest_finalizer19Childs = methodtest_finalizer19.getChildren();
				classB22 = ModelTestUtils.getAssertClass( methodtest_finalizer19Childs, "B" );
			}
		}
		//Function test:test_finalizer_newclass
		{
		IMethod methodtest_finalizer_newclass23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_finalizer_newclass23 = ModelTestUtils.getAssertMethod( moduleChilds, "test_finalizer_newclass", 0 );
			//Class test
			{
			IType classA24;
				IModelElement[] methodtest_finalizer_newclass23Childs = methodtest_finalizer_newclass23.getChildren();
				classA24 = ModelTestUtils.getAssertClass( methodtest_finalizer_newclass23Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__25;
					IModelElement[] classA24Childs = classA24.getChildren();
					method__del__25 = ModelTestUtils.getAssertMethod( classA24Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__25, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB26;
				IModelElement[] methodtest_finalizer_newclass23Childs = methodtest_finalizer_newclass23.getChildren();
				classB26 = ModelTestUtils.getAssertClass( methodtest_finalizer_newclass23Childs, "B" );
			}
		}
		//Function test:test_function
		{
		IMethod methodtest_function27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_function27 = ModelTestUtils.getAssertMethod( moduleChilds, "test_function", 0 );
		}
		//Function test:test_frame
		{
		IMethod methodtest_frame28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_frame28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_frame", 0 );
			//Function test:f
			{
			IMethod methodf29;
				IModelElement[] methodtest_frame28Childs = methodtest_frame28.getChildren();
				methodf29 = ModelTestUtils.getAssertMethod( methodtest_frame28Childs, "f", 0 );
			}
		}
		//Function test:test_saveall
		{
		IMethod methodtest_saveall30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_saveall30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_saveall", 0 );
		}
		//Function test:test_del
		{
		IMethod methodtest_del31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_del31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_del", 0 );
			//Class test
			{
			IType classA32;
				IModelElement[] methodtest_del31Childs = methodtest_del31.getChildren();
				classA32 = ModelTestUtils.getAssertClass( methodtest_del31Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__33;
					IModelElement[] classA32Childs = classA32.getChildren();
					method__del__33 = ModelTestUtils.getAssertMethod( classA32Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__33, new String[] {"self"} );
				}
			}
		}
		//Function test:test_del_newclass
		{
		IMethod methodtest_del_newclass34;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_del_newclass34 = ModelTestUtils.getAssertMethod( moduleChilds, "test_del_newclass", 0 );
			//Class test
			{
			IType classA35;
				IModelElement[] methodtest_del_newclass34Childs = methodtest_del_newclass34.getChildren();
				classA35 = ModelTestUtils.getAssertClass( methodtest_del_newclass34Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__36;
					IModelElement[] classA35Childs = classA35.getChildren();
					method__del__36 = ModelTestUtils.getAssertMethod( classA35Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__36, new String[] {"self"} );
				}
			}
		}
		//Class test
		{
		IType classOuch37;
			IModelElement[] moduleChilds = module.getChildren();
			classOuch37 = ModelTestUtils.getAssertClass( moduleChilds, "Ouch" );
			{
				IModelElement[] classOuch37Childs = classOuch37.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classOuch37Childs, "n");
			}
			//Function test:__del__
			{
			IMethod method__del__38;
				IModelElement[] classOuch37Childs = classOuch37.getChildren();
				method__del__38 = ModelTestUtils.getAssertMethod( classOuch37Childs, "__del__", 1 );
				ModelTestUtils.assertParameterNames( method__del__38, new String[] {"self"} );
			}
		}
		//Function test:test_trashcan
		{
		IMethod methodtest_trashcan39;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_trashcan39 = ModelTestUtils.getAssertMethod( moduleChilds, "test_trashcan", 0 );
		}
		//Class test
		{
		IType classBoom40;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom40 = ModelTestUtils.getAssertClass( moduleChilds, "Boom" );
			//Function test:__getattr__
			{
			IMethod method__getattr__41;
				IModelElement[] classBoom40Childs = classBoom40.getChildren();
				method__getattr__41 = ModelTestUtils.getAssertMethod( classBoom40Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__41, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom
		{
		IMethod methodtest_boom42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom", 0 );
		}
		//Class test
		{
		IType classBoom243;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom243 = ModelTestUtils.getAssertClass( moduleChilds, "Boom2" );
			//Function test:__init__
			{
			IMethod method__init__44;
				IModelElement[] classBoom243Childs = classBoom243.getChildren();
				method__init__44 = ModelTestUtils.getAssertMethod( classBoom243Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__44, new String[] {"self"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__45;
				IModelElement[] classBoom243Childs = classBoom243.getChildren();
				method__getattr__45 = ModelTestUtils.getAssertMethod( classBoom243Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__45, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom2
		{
		IMethod methodtest_boom246;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom246 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom2", 0 );
		}
		//Class test
		{
		IType classBoom_New47;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom_New47 = ModelTestUtils.getAssertClass( moduleChilds, "Boom_New" );
			//Function test:__getattr__
			{
			IMethod method__getattr__48;
				IModelElement[] classBoom_New47Childs = classBoom_New47.getChildren();
				method__getattr__48 = ModelTestUtils.getAssertMethod( classBoom_New47Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__48, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom_new
		{
		IMethod methodtest_boom_new49;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom_new49 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom_new", 0 );
		}
		//Class test
		{
		IType classBoom2_New50;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom2_New50 = ModelTestUtils.getAssertClass( moduleChilds, "Boom2_New" );
			//Function test:__init__
			{
			IMethod method__init__51;
				IModelElement[] classBoom2_New50Childs = classBoom2_New50.getChildren();
				method__init__51 = ModelTestUtils.getAssertMethod( classBoom2_New50Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__51, new String[] {"self"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__52;
				IModelElement[] classBoom2_New50Childs = classBoom2_New50.getChildren();
				method__getattr__52 = ModelTestUtils.getAssertMethod( classBoom2_New50Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__52, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom2_new
		{
		IMethod methodtest_boom2_new53;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom2_new53 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom2_new", 0 );
		}
		//Function test:test_get_referents
		{
		IMethod methodtest_get_referents54;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_get_referents54 = ModelTestUtils.getAssertMethod( moduleChilds, "test_get_referents", 0 );
		}
		//Class test
		{
		IType classC105582055;
			IModelElement[] moduleChilds = module.getChildren();
			classC105582055 = ModelTestUtils.getAssertClass( moduleChilds, "C1055820" );
			//Function test:__init__
			{
			IMethod method__init__56;
				IModelElement[] classC105582055Childs = classC105582055.getChildren();
				method__init__56 = ModelTestUtils.getAssertMethod( classC105582055Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__56, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classGC_Detector57;
			IModelElement[] moduleChilds = module.getChildren();
			classGC_Detector57 = ModelTestUtils.getAssertClass( moduleChilds, "GC_Detector" );
			//Function test:__init__
			{
			IMethod method__init__58;
				IModelElement[] classGC_Detector57Childs = classGC_Detector57.getChildren();
				method__init__58 = ModelTestUtils.getAssertMethod( classGC_Detector57Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__58, new String[] {"self"} );
				//Function test:it_happened
				{
				IMethod methodit_happened59;
					IModelElement[] method__init__58Childs = method__init__58.getChildren();
					methodit_happened59 = ModelTestUtils.getAssertMethod( method__init__58Childs, "it_happened", 1 );
					ModelTestUtils.assertParameterNames( methodit_happened59, new String[] {"ignored"} );
				}
			}
		}
		//Function test:test_bug1055820b
		{
		IMethod methodtest_bug1055820b60;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bug1055820b60 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bug1055820b", 0 );
			//Function test:callback
			{
			IMethod methodcallback61;
				IModelElement[] methodtest_bug1055820b60Childs = methodtest_bug1055820b60.getChildren();
				methodcallback61 = ModelTestUtils.getAssertMethod( methodtest_bug1055820b60Childs, "callback", 1 );
				ModelTestUtils.assertParameterNames( methodcallback61, new String[] {"ignored"} );
			}
		}
		//Function test:test_bug1055820c
		{
		IMethod methodtest_bug1055820c62;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bug1055820c62 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bug1055820c", 0 );
			//Function test:callback
			{
			IMethod methodcallback63;
				IModelElement[] methodtest_bug1055820c62Childs = methodtest_bug1055820c62.getChildren();
				methodcallback63 = ModelTestUtils.getAssertMethod( methodtest_bug1055820c62Childs, "callback", 1 );
				ModelTestUtils.assertParameterNames( methodcallback63, new String[] {"ignored"} );
			}
		}
		//Function test:test_bug1055820d
		{
		IMethod methodtest_bug1055820d64;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bug1055820d64 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bug1055820d", 0 );
			//Class test
			{
			IType classD65;
				IModelElement[] methodtest_bug1055820d64Childs = methodtest_bug1055820d64.getChildren();
				classD65 = ModelTestUtils.getAssertClass( methodtest_bug1055820d64Childs, "D" );
				//Function test:__del__
				{
				IMethod method__del__66;
					IModelElement[] classD65Childs = classD65.getChildren();
					method__del__66 = ModelTestUtils.getAssertMethod( classD65Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__66, new String[] {"self"} );
				}
			}
		}
		//Function test:test_all
		{
		IMethod methodtest_all67;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_all67 = ModelTestUtils.getAssertMethod( moduleChilds, "test_all", 0 );
		}
		//Function test:test
		{
		IMethod methodtest68;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest68 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen21( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_string.py"));

		assertNotNull("Module test_string.py not found", module);
		assertEquals("test_string.py", module.getElementName());
		
		//Class test
		{
		IType classStringTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classStringTest0 = ModelTestUtils.getAssertClass( moduleChilds, "StringTest" );
			{
				IModelElement[] classStringTest0Childs = classStringTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStringTest0Childs, "type2test");
			}
			//Function test:checkequal
			{
			IMethod methodcheckequal1;
				IModelElement[] classStringTest0Childs = classStringTest0.getChildren();
				methodcheckequal1 = ModelTestUtils.getAssertMethod( classStringTest0Childs, "checkequal", 5 );
				ModelTestUtils.assertParameterNames( methodcheckequal1, new String[] {"self", "result", "object", "methodname", "args"} );
			}
			//Function test:checkraises
			{
			IMethod methodcheckraises2;
				IModelElement[] classStringTest0Childs = classStringTest0.getChildren();
				methodcheckraises2 = ModelTestUtils.getAssertMethod( classStringTest0Childs, "checkraises", 5 );
				ModelTestUtils.assertParameterNames( methodcheckraises2, new String[] {"self", "exc", "object", "methodname", "args"} );
			}
			//Function test:checkcall
			{
			IMethod methodcheckcall3;
				IModelElement[] classStringTest0Childs = classStringTest0.getChildren();
				methodcheckcall3 = ModelTestUtils.getAssertMethod( classStringTest0Childs, "checkcall", 4 );
				ModelTestUtils.assertParameterNames( methodcheckcall3, new String[] {"self", "object", "methodname", "args"} );
			}
			//Function test:test_join
			{
			IMethod methodtest_join4;
				IModelElement[] classStringTest0Childs = classStringTest0.getChildren();
				methodtest_join4 = ModelTestUtils.getAssertMethod( classStringTest0Childs, "test_join", 1 );
				ModelTestUtils.assertParameterNames( methodtest_join4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classModuleTest5;
			IModelElement[] moduleChilds = module.getChildren();
			classModuleTest5 = ModelTestUtils.getAssertClass( moduleChilds, "ModuleTest" );
			//Function test:test_attrs
			{
			IMethod methodtest_attrs6;
				IModelElement[] classModuleTest5Childs = classModuleTest5.getChildren();
				methodtest_attrs6 = ModelTestUtils.getAssertMethod( classModuleTest5Childs, "test_attrs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attrs6, new String[] {"self"} );
			}
			//Function test:test_atoi
			{
			IMethod methodtest_atoi7;
				IModelElement[] classModuleTest5Childs = classModuleTest5.getChildren();
				methodtest_atoi7 = ModelTestUtils.getAssertMethod( classModuleTest5Childs, "test_atoi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_atoi7, new String[] {"self"} );
			}
			//Function test:test_atol
			{
			IMethod methodtest_atol8;
				IModelElement[] classModuleTest5Childs = classModuleTest5.getChildren();
				methodtest_atol8 = ModelTestUtils.getAssertMethod( classModuleTest5Childs, "test_atol", 1 );
				ModelTestUtils.assertParameterNames( methodtest_atol8, new String[] {"self"} );
			}
			//Function test:test_atof
			{
			IMethod methodtest_atof9;
				IModelElement[] classModuleTest5Childs = classModuleTest5.getChildren();
				methodtest_atof9 = ModelTestUtils.getAssertMethod( classModuleTest5Childs, "test_atof", 1 );
				ModelTestUtils.assertParameterNames( methodtest_atof9, new String[] {"self"} );
			}
			//Function test:test_maketrans
			{
			IMethod methodtest_maketrans10;
				IModelElement[] classModuleTest5Childs = classModuleTest5.getChildren();
				methodtest_maketrans10 = ModelTestUtils.getAssertMethod( classModuleTest5Childs, "test_maketrans", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maketrans10, new String[] {"self"} );
			}
			//Function test:test_capwords
			{
			IMethod methodtest_capwords11;
				IModelElement[] classModuleTest5Childs = classModuleTest5.getChildren();
				methodtest_capwords11 = ModelTestUtils.getAssertMethod( classModuleTest5Childs, "test_capwords", 1 );
				ModelTestUtils.assertParameterNames( methodtest_capwords11, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main12 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen22( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_gl.py"));

		assertNotNull("Module test_gl.py not found", module);
		assertEquals("test_gl.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "glattrs");
		}
		//Function test:main
		{
		IMethod methodmain0;
			IModelElement[] moduleChilds = module.getChildren();
			methodmain0 = ModelTestUtils.getAssertMethod( moduleChilds, "main", 0 );
		}

	}
	public void testModelGen23( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_doctest2.py"));

		assertNotNull("Module test_doctest2.py not found", module);
		assertEquals("test_doctest2.py", module.getElementName());
		
		//Class test
		{
		IType classC0;
			IModelElement[] moduleChilds = module.getChildren();
			classC0 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classC0Childs = classC0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classC0Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self"} );
			}
			//Function test:__str__
			{
			IMethod method__str__2;
				IModelElement[] classC0Childs = classC0.getChildren();
				method__str__2 = ModelTestUtils.getAssertMethod( classC0Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__2, new String[] {"self"} );
			}
			//Class test
			{
			IType classD3;
				IModelElement[] classC0Childs = classC0.getChildren();
				classD3 = ModelTestUtils.getAssertClass( classC0Childs, "D" );
				//Function test:nested
				{
				IMethod methodnested4;
					IModelElement[] classD3Childs = classD3.getChildren();
					methodnested4 = ModelTestUtils.getAssertMethod( classD3Childs, "nested", 1 );
					ModelTestUtils.assertParameterNames( methodnested4, new String[] {"self"} );
				}
			}
			//Function test:getx
			{
			IMethod methodgetx5;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodgetx5 = ModelTestUtils.getAssertMethod( classC0Childs, "getx", 1 );
				ModelTestUtils.assertParameterNames( methodgetx5, new String[] {"self"} );
			}
			//Function test:setx
			{
			IMethod methodsetx6;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodsetx6 = ModelTestUtils.getAssertMethod( classC0Childs, "setx", 2 );
				ModelTestUtils.assertParameterNames( methodsetx6, new String[] {"self", "value"} );
			}
			{
				IModelElement[] classC0Childs = classC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "x");
			}
			//Function test:statm
			{
			IMethod methodstatm7;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodstatm7 = ModelTestUtils.getAssertMethod( classC0Childs, "statm", 0 );
			}
			{
				IModelElement[] classC0Childs = classC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "statm");
			}
			//Function test:clsm
			{
			IMethod methodclsm8;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodclsm8 = ModelTestUtils.getAssertMethod( classC0Childs, "clsm", 2 );
				ModelTestUtils.assertParameterNames( methodclsm8, new String[] {"cls", "val"} );
			}
			{
				IModelElement[] classC0Childs = classC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "clsm");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen24( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_normalization.py"));

		assertNotNull("Module test_normalization.py not found", module);
		assertEquals("test_normalization.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTDATAFILE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "fn");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "skip_expected");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "TESTDATAFILE");
		}
		//Class test
		{
		IType classRangeError0;
			IModelElement[] moduleChilds = module.getChildren();
			classRangeError0 = ModelTestUtils.getAssertClass( moduleChilds, "RangeError" );
		}
		//Function test:NFC
		{
		IMethod methodNFC1;
			IModelElement[] moduleChilds = module.getChildren();
			methodNFC1 = ModelTestUtils.getAssertMethod( moduleChilds, "NFC", 1 );
			ModelTestUtils.assertParameterNames( methodNFC1, new String[] {"str"} );
		}
		//Function test:NFKC
		{
		IMethod methodNFKC2;
			IModelElement[] moduleChilds = module.getChildren();
			methodNFKC2 = ModelTestUtils.getAssertMethod( moduleChilds, "NFKC", 1 );
			ModelTestUtils.assertParameterNames( methodNFKC2, new String[] {"str"} );
		}
		//Function test:NFD
		{
		IMethod methodNFD3;
			IModelElement[] moduleChilds = module.getChildren();
			methodNFD3 = ModelTestUtils.getAssertMethod( moduleChilds, "NFD", 1 );
			ModelTestUtils.assertParameterNames( methodNFD3, new String[] {"str"} );
		}
		//Function test:NFKD
		{
		IMethod methodNFKD4;
			IModelElement[] moduleChilds = module.getChildren();
			methodNFKD4 = ModelTestUtils.getAssertMethod( moduleChilds, "NFKD", 1 );
			ModelTestUtils.assertParameterNames( methodNFKD4, new String[] {"str"} );
		}
		//Function test:unistr
		{
		IMethod methodunistr5;
			IModelElement[] moduleChilds = module.getChildren();
			methodunistr5 = ModelTestUtils.getAssertMethod( moduleChilds, "unistr", 1 );
			ModelTestUtils.assertParameterNames( methodunistr5, new String[] {"data"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen25( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_commands.py"));

		assertNotNull("Module test_commands.py not found", module);
		assertEquals("test_commands.py", module.getElementName());
		
		//Class test
		{
		IType classCommandTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classCommandTests0 = ModelTestUtils.getAssertClass( moduleChilds, "CommandTests" );
			//Function test:test_getoutput
			{
			IMethod methodtest_getoutput1;
				IModelElement[] classCommandTests0Childs = classCommandTests0.getChildren();
				methodtest_getoutput1 = ModelTestUtils.getAssertMethod( classCommandTests0Childs, "test_getoutput", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getoutput1, new String[] {"self"} );
			}
			//Function test:test_getstatus
			{
			IMethod methodtest_getstatus2;
				IModelElement[] classCommandTests0Childs = classCommandTests0.getChildren();
				methodtest_getstatus2 = ModelTestUtils.getAssertMethod( classCommandTests0Childs, "test_getstatus", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getstatus2, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen26( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_openpty.py"));

		assertNotNull("Module test_openpty.py not found", module);
		assertEquals("test_openpty.py", module.getElementName());
		

	}
	public void testModelGen27( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_enumerate.py"));

		assertNotNull("Module test_enumerate.py not found", module);
		assertEquals("test_enumerate.py", module.getElementName());
		
		//Class test
		{
		IType classG0;
			IModelElement[] moduleChilds = module.getChildren();
			classG0 = ModelTestUtils.getAssertClass( moduleChilds, "G" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classG0Childs = classG0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classG0Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self", "seqn"} );
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__2;
				IModelElement[] classG0Childs = classG0.getChildren();
				method__getitem__2 = ModelTestUtils.getAssertMethod( classG0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__2, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI3;
			IModelElement[] moduleChilds = module.getChildren();
			classI3 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__4;
				IModelElement[] classI3Childs = classI3.getChildren();
				method__init__4 = ModelTestUtils.getAssertMethod( classI3Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__4, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__5;
				IModelElement[] classI3Childs = classI3.getChildren();
				method__iter__5 = ModelTestUtils.getAssertMethod( classI3Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__5, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext6;
				IModelElement[] classI3Childs = classI3.getChildren();
				methodnext6 = ModelTestUtils.getAssertMethod( classI3Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg7;
			IModelElement[] moduleChilds = module.getChildren();
			classIg7 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__8;
				IModelElement[] classIg7Childs = classIg7.getChildren();
				method__init__8 = ModelTestUtils.getAssertMethod( classIg7Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__9;
				IModelElement[] classIg7Childs = classIg7.getChildren();
				method__iter__9 = ModelTestUtils.getAssertMethod( classIg7Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX10;
			IModelElement[] moduleChilds = module.getChildren();
			classX10 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__11;
				IModelElement[] classX10Childs = classX10.getChildren();
				method__init__11 = ModelTestUtils.getAssertMethod( classX10Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__11, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext12;
				IModelElement[] classX10Childs = classX10.getChildren();
				methodnext12 = ModelTestUtils.getAssertMethod( classX10Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE13;
			IModelElement[] moduleChilds = module.getChildren();
			classE13 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__14;
				IModelElement[] classE13Childs = classE13.getChildren();
				method__init__14 = ModelTestUtils.getAssertMethod( classE13Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__14, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__15;
				IModelElement[] classE13Childs = classE13.getChildren();
				method__iter__15 = ModelTestUtils.getAssertMethod( classE13Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__15, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext16;
				IModelElement[] classE13Childs = classE13.getChildren();
				methodnext16 = ModelTestUtils.getAssertMethod( classE13Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext16, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN17;
			IModelElement[] moduleChilds = module.getChildren();
			classN17 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__18;
				IModelElement[] classN17Childs = classN17.getChildren();
				method__init__18 = ModelTestUtils.getAssertMethod( classN17Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__18, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__19;
				IModelElement[] classN17Childs = classN17.getChildren();
				method__iter__19 = ModelTestUtils.getAssertMethod( classN17Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEnumerateTestCase20;
			IModelElement[] moduleChilds = module.getChildren();
			classEnumerateTestCase20 = ModelTestUtils.getAssertClass( moduleChilds, "EnumerateTestCase" );
			{
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEnumerateTestCase20Childs, "enum");
			}
			//Function test:test_basicfunction
			{
			IMethod methodtest_basicfunction21;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_basicfunction21 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_basicfunction", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basicfunction21, new String[] {"self"} );
			}
			//Function test:test_getitemseqn
			{
			IMethod methodtest_getitemseqn22;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_getitemseqn22 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_getitemseqn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitemseqn22, new String[] {"self"} );
			}
			//Function test:test_iteratorseqn
			{
			IMethod methodtest_iteratorseqn23;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_iteratorseqn23 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_iteratorseqn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteratorseqn23, new String[] {"self"} );
			}
			//Function test:test_iteratorgenerator
			{
			IMethod methodtest_iteratorgenerator24;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_iteratorgenerator24 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_iteratorgenerator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteratorgenerator24, new String[] {"self"} );
			}
			//Function test:test_noniterable
			{
			IMethod methodtest_noniterable25;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_noniterable25 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_noniterable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_noniterable25, new String[] {"self"} );
			}
			//Function test:test_illformediterable
			{
			IMethod methodtest_illformediterable26;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_illformediterable26 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_illformediterable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illformediterable26, new String[] {"self"} );
			}
			//Function test:test_exception_propagation
			{
			IMethod methodtest_exception_propagation27;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_exception_propagation27 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_exception_propagation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_propagation27, new String[] {"self"} );
			}
			//Function test:test_argumentcheck
			{
			IMethod methodtest_argumentcheck28;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_argumentcheck28 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_argumentcheck", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argumentcheck28, new String[] {"self"} );
			}
			//Function test:test_tuple_reuse
			{
			IMethod methodtest_tuple_reuse29;
				IModelElement[] classEnumerateTestCase20Childs = classEnumerateTestCase20.getChildren();
				methodtest_tuple_reuse29 = ModelTestUtils.getAssertMethod( classEnumerateTestCase20Childs, "test_tuple_reuse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tuple_reuse29, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMyEnum30;
			IModelElement[] moduleChilds = module.getChildren();
			classMyEnum30 = ModelTestUtils.getAssertClass( moduleChilds, "MyEnum" );
		}
		//Class test
		{
		IType classSubclassTestCase31;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassTestCase31 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassTestCase" );
			{
				IModelElement[] classSubclassTestCase31Childs = classSubclassTestCase31.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassTestCase31Childs, "enum");
			}
		}
		//Class test
		{
		IType classTestEmpty32;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEmpty32 = ModelTestUtils.getAssertClass( moduleChilds, "TestEmpty" );
		}
		//Class test
		{
		IType classTestBig33;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBig33 = ModelTestUtils.getAssertClass( moduleChilds, "TestBig" );
			{
				IModelElement[] classTestBig33Childs = classTestBig33.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig33Childs, "seq");
			}
			{
				IModelElement[] classTestBig33Childs = classTestBig33.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig33Childs, "res");
			}
		}
		//Class test
		{
		IType classTestReversed34;
			IModelElement[] moduleChilds = module.getChildren();
			classTestReversed34 = ModelTestUtils.getAssertClass( moduleChilds, "TestReversed" );
			//Function test:test_simple
			{
			IMethod methodtest_simple35;
				IModelElement[] classTestReversed34Childs = classTestReversed34.getChildren();
				methodtest_simple35 = ModelTestUtils.getAssertMethod( classTestReversed34Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple35, new String[] {"self"} );
				//Class test
				{
				IType classA36;
					IModelElement[] methodtest_simple35Childs = methodtest_simple35.getChildren();
					classA36 = ModelTestUtils.getAssertClass( methodtest_simple35Childs, "A" );
					//Function test:__getitem__
					{
					IMethod method__getitem__37;
						IModelElement[] classA36Childs = classA36.getChildren();
						method__getitem__37 = ModelTestUtils.getAssertMethod( classA36Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__37, new String[] {"self", "i"} );
					}
					//Function test:__len__
					{
					IMethod method__len__38;
						IModelElement[] classA36Childs = classA36.getChildren();
						method__len__38 = ModelTestUtils.getAssertMethod( classA36Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__38, new String[] {"self"} );
					}
				}
			}
			//Function test:test_xrange_optimization
			{
			IMethod methodtest_xrange_optimization39;
				IModelElement[] classTestReversed34Childs = classTestReversed34.getChildren();
				methodtest_xrange_optimization39 = ModelTestUtils.getAssertMethod( classTestReversed34Childs, "test_xrange_optimization", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xrange_optimization39, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len40;
				IModelElement[] classTestReversed34Childs = classTestReversed34.getChildren();
				methodtest_len40 = ModelTestUtils.getAssertMethod( classTestReversed34Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len40, new String[] {"self"} );
				//Class test
				{
				IType classSeqWithWeirdLen41;
					IModelElement[] methodtest_len40Childs = methodtest_len40.getChildren();
					classSeqWithWeirdLen41 = ModelTestUtils.getAssertClass( methodtest_len40Childs, "SeqWithWeirdLen" );
					//Function test:__len__
					{
					IMethod method__len__42;
						IModelElement[] classSeqWithWeirdLen41Childs = classSeqWithWeirdLen41.getChildren();
						method__len__42 = ModelTestUtils.getAssertMethod( classSeqWithWeirdLen41Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__42, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__43;
						IModelElement[] classSeqWithWeirdLen41Childs = classSeqWithWeirdLen41.getChildren();
						method__getitem__43 = ModelTestUtils.getAssertMethod( classSeqWithWeirdLen41Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__43, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_gc
			{
			IMethod methodtest_gc44;
				IModelElement[] classTestReversed34Childs = classTestReversed34.getChildren();
				methodtest_gc44 = ModelTestUtils.getAssertMethod( classTestReversed34Childs, "test_gc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc44, new String[] {"self"} );
				//Class test
				{
				IType classSeq45;
					IModelElement[] methodtest_gc44Childs = methodtest_gc44.getChildren();
					classSeq45 = ModelTestUtils.getAssertClass( methodtest_gc44Childs, "Seq" );
					//Function test:__len__
					{
					IMethod method__len__46;
						IModelElement[] classSeq45Childs = classSeq45.getChildren();
						method__len__46 = ModelTestUtils.getAssertMethod( classSeq45Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__46, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__47;
						IModelElement[] classSeq45Childs = classSeq45.getChildren();
						method__getitem__47 = ModelTestUtils.getAssertMethod( classSeq45Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__47, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_args
			{
			IMethod methodtest_args48;
				IModelElement[] classTestReversed34Childs = classTestReversed34.getChildren();
				methodtest_args48 = ModelTestUtils.getAssertMethod( classTestReversed34Childs, "test_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_args48, new String[] {"self"} );
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
	public void testModelGen28( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_os.py"));

		assertNotNull("Module test_os.py not found", module);
		assertEquals("test_os.py", module.getElementName());
		
		//Class test
		{
		IType classTemporaryFileTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classTemporaryFileTests0 = ModelTestUtils.getAssertClass( moduleChilds, "TemporaryFileTests" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:check_tempfile
			{
			IMethod methodcheck_tempfile3;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodcheck_tempfile3 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "check_tempfile", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_tempfile3, new String[] {"self", "name"} );
			}
			//Function test:test_tempnam
			{
			IMethod methodtest_tempnam4;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtest_tempnam4 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "test_tempnam", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tempnam4, new String[] {"self"} );
			}
			//Function test:test_tmpfile
			{
			IMethod methodtest_tmpfile5;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtest_tmpfile5 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "test_tmpfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tmpfile5, new String[] {"self"} );
			}
			//Function test:test_tmpnam
			{
			IMethod methodtest_tmpnam6;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtest_tmpnam6 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "test_tmpnam", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tmpnam6, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStatAttributeTests7;
			IModelElement[] moduleChilds = module.getChildren();
			classStatAttributeTests7 = ModelTestUtils.getAssertClass( moduleChilds, "StatAttributeTests" );
			//Function test:setUp
			{
			IMethod methodsetUp8;
				IModelElement[] classStatAttributeTests7Childs = classStatAttributeTests7.getChildren();
				methodsetUp8 = ModelTestUtils.getAssertMethod( classStatAttributeTests7Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp8, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown9;
				IModelElement[] classStatAttributeTests7Childs = classStatAttributeTests7.getChildren();
				methodtearDown9 = ModelTestUtils.getAssertMethod( classStatAttributeTests7Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown9, new String[] {"self"} );
			}
			//Function test:test_stat_attributes
			{
			IMethod methodtest_stat_attributes10;
				IModelElement[] classStatAttributeTests7Childs = classStatAttributeTests7.getChildren();
				methodtest_stat_attributes10 = ModelTestUtils.getAssertMethod( classStatAttributeTests7Childs, "test_stat_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stat_attributes10, new String[] {"self"} );
			}
			//Function test:test_statvfs_attributes
			{
			IMethod methodtest_statvfs_attributes11;
				IModelElement[] classStatAttributeTests7Childs = classStatAttributeTests7.getChildren();
				methodtest_statvfs_attributes11 = ModelTestUtils.getAssertMethod( classStatAttributeTests7Childs, "test_statvfs_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_statvfs_attributes11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEnvironTests12;
			IModelElement[] moduleChilds = module.getChildren();
			classEnvironTests12 = ModelTestUtils.getAssertClass( moduleChilds, "EnvironTests" );
			{
				IModelElement[] classEnvironTests12Childs = classEnvironTests12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEnvironTests12Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference13;
				IModelElement[] classEnvironTests12Childs = classEnvironTests12.getChildren();
				method_reference13 = ModelTestUtils.getAssertMethod( classEnvironTests12Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference13, new String[] {"self"} );
			}
			//Function test:_empty_mapping
			{
			IMethod method_empty_mapping14;
				IModelElement[] classEnvironTests12Childs = classEnvironTests12.getChildren();
				method_empty_mapping14 = ModelTestUtils.getAssertMethod( classEnvironTests12Childs, "_empty_mapping", 1 );
				ModelTestUtils.assertParameterNames( method_empty_mapping14, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp15;
				IModelElement[] classEnvironTests12Childs = classEnvironTests12.getChildren();
				methodsetUp15 = ModelTestUtils.getAssertMethod( classEnvironTests12Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp15, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown16;
				IModelElement[] classEnvironTests12Childs = classEnvironTests12.getChildren();
				methodtearDown16 = ModelTestUtils.getAssertMethod( classEnvironTests12Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown16, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWalkTests17;
			IModelElement[] moduleChilds = module.getChildren();
			classWalkTests17 = ModelTestUtils.getAssertClass( moduleChilds, "WalkTests" );
			//Function test:test_traversal
			{
			IMethod methodtest_traversal18;
				IModelElement[] classWalkTests17Childs = classWalkTests17.getChildren();
				methodtest_traversal18 = ModelTestUtils.getAssertMethod( classWalkTests17Childs, "test_traversal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_traversal18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMakedirTests19;
			IModelElement[] moduleChilds = module.getChildren();
			classMakedirTests19 = ModelTestUtils.getAssertClass( moduleChilds, "MakedirTests" );
			//Function test:setUp
			{
			IMethod methodsetUp20;
				IModelElement[] classMakedirTests19Childs = classMakedirTests19.getChildren();
				methodsetUp20 = ModelTestUtils.getAssertMethod( classMakedirTests19Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp20, new String[] {"self"} );
			}
			//Function test:test_makedir
			{
			IMethod methodtest_makedir21;
				IModelElement[] classMakedirTests19Childs = classMakedirTests19.getChildren();
				methodtest_makedir21 = ModelTestUtils.getAssertMethod( classMakedirTests19Childs, "test_makedir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_makedir21, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown22;
				IModelElement[] classMakedirTests19Childs = classMakedirTests19.getChildren();
				methodtearDown22 = ModelTestUtils.getAssertMethod( classMakedirTests19Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown22, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDevNullTests23;
			IModelElement[] moduleChilds = module.getChildren();
			classDevNullTests23 = ModelTestUtils.getAssertClass( moduleChilds, "DevNullTests" );
			//Function test:test_devnull
			{
			IMethod methodtest_devnull24;
				IModelElement[] classDevNullTests23Childs = classDevNullTests23.getChildren();
				methodtest_devnull24 = ModelTestUtils.getAssertMethod( classDevNullTests23Childs, "test_devnull", 1 );
				ModelTestUtils.assertParameterNames( methodtest_devnull24, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classURandomTests25;
			IModelElement[] moduleChilds = module.getChildren();
			classURandomTests25 = ModelTestUtils.getAssertClass( moduleChilds, "URandomTests" );
			//Function test:test_urandom
			{
			IMethod methodtest_urandom26;
				IModelElement[] classURandomTests25Childs = classURandomTests25.getChildren();
				methodtest_urandom26 = ModelTestUtils.getAssertMethod( classURandomTests25Childs, "test_urandom", 1 );
				ModelTestUtils.assertParameterNames( methodtest_urandom26, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main27 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen29( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_profilehooks.py"));

		assertNotNull("Module test_profilehooks.py not found", module);
		assertEquals("test_profilehooks.py", module.getElementName());
		
		//Class test
		{
		IType classHookWatcher0;
			IModelElement[] moduleChilds = module.getChildren();
			classHookWatcher0 = ModelTestUtils.getAssertClass( moduleChilds, "HookWatcher" );
			//Function test:__init__
			{
			IMethod method__init__1;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				method__init__1 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__1, new String[] {"self"} );
			}
			//Function test:callback
			{
			IMethod methodcallback2;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				methodcallback2 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "callback", 4 );
				ModelTestUtils.assertParameterNames( methodcallback2, new String[] {"self", "frame", "event", "arg"} );
			}
			//Function test:add_event
			{
			IMethod methodadd_event3;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				methodadd_event3 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "add_event", 3 );
				ModelTestUtils.assertParameterNames( methodadd_event3, new String[] {"self", "event", "frame"} );
			}
			//Function test:get_events
			{
			IMethod methodget_events4;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				methodget_events4 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "get_events", 1 );
				ModelTestUtils.assertParameterNames( methodget_events4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classProfileSimulator5;
			IModelElement[] moduleChilds = module.getChildren();
			classProfileSimulator5 = ModelTestUtils.getAssertClass( moduleChilds, "ProfileSimulator" );
			//Function test:__init__
			{
			IMethod method__init__6;
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				method__init__6 = ModelTestUtils.getAssertMethod( classProfileSimulator5Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__6, new String[] {"self", "testcase"} );
			}
			//Function test:callback
			{
			IMethod methodcallback7;
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				methodcallback7 = ModelTestUtils.getAssertMethod( classProfileSimulator5Childs, "callback", 4 );
				ModelTestUtils.assertParameterNames( methodcallback7, new String[] {"self", "frame", "event", "arg"} );
			}
			//Function test:trace_call
			{
			IMethod methodtrace_call8;
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				methodtrace_call8 = ModelTestUtils.getAssertMethod( classProfileSimulator5Childs, "trace_call", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_call8, new String[] {"self", "frame"} );
			}
			//Function test:trace_return
			{
			IMethod methodtrace_return9;
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				methodtrace_return9 = ModelTestUtils.getAssertMethod( classProfileSimulator5Childs, "trace_return", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_return9, new String[] {"self", "frame"} );
			}
			//Function test:trace_exception
			{
			IMethod methodtrace_exception10;
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				methodtrace_exception10 = ModelTestUtils.getAssertMethod( classProfileSimulator5Childs, "trace_exception", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_exception10, new String[] {"self", "frame"} );
			}
			//Function test:trace_pass
			{
			IMethod methodtrace_pass11;
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				methodtrace_pass11 = ModelTestUtils.getAssertMethod( classProfileSimulator5Childs, "trace_pass", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_pass11, new String[] {"self", "frame"} );
			}
			{
				IModelElement[] classProfileSimulator5Childs = classProfileSimulator5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classProfileSimulator5Childs, "dispatch");
			}
		}
		//Class test
		{
		IType classTestCaseBase12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCaseBase12 = ModelTestUtils.getAssertClass( moduleChilds, "TestCaseBase" );
			//Function test:check_events
			{
			IMethod methodcheck_events13;
				IModelElement[] classTestCaseBase12Childs = classTestCaseBase12.getChildren();
				methodcheck_events13 = ModelTestUtils.getAssertMethod( classTestCaseBase12Childs, "check_events", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_events13, new String[] {"self", "callable", "expected"} );
			}
		}
		//Class test
		{
		IType classProfileHookTestCase14;
			IModelElement[] moduleChilds = module.getChildren();
			classProfileHookTestCase14 = ModelTestUtils.getAssertClass( moduleChilds, "ProfileHookTestCase" );
			//Function test:new_watcher
			{
			IMethod methodnew_watcher15;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodnew_watcher15 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "new_watcher", 1 );
				ModelTestUtils.assertParameterNames( methodnew_watcher15, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple16;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_simple16 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple16, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf17;
					IModelElement[] methodtest_simple16Childs = methodtest_simple16.getChildren();
					methodf17 = ModelTestUtils.getAssertMethod( methodtest_simple16Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf17, new String[] {"p"} );
				}
			}
			//Function test:test_exception
			{
			IMethod methodtest_exception18;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_exception18 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception18, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf19;
					IModelElement[] methodtest_exception18Childs = methodtest_exception18.getChildren();
					methodf19 = ModelTestUtils.getAssertMethod( methodtest_exception18Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf19, new String[] {"p"} );
				}
			}
			//Function test:test_caught_exception
			{
			IMethod methodtest_caught_exception20;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_caught_exception20 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_caught_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caught_exception20, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf21;
					IModelElement[] methodtest_caught_exception20Childs = methodtest_caught_exception20.getChildren();
					methodf21 = ModelTestUtils.getAssertMethod( methodtest_caught_exception20Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf21, new String[] {"p"} );
				}
			}
			//Function test:test_caught_nested_exception
			{
			IMethod methodtest_caught_nested_exception22;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_caught_nested_exception22 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_caught_nested_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caught_nested_exception22, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf23;
					IModelElement[] methodtest_caught_nested_exception22Childs = methodtest_caught_nested_exception22.getChildren();
					methodf23 = ModelTestUtils.getAssertMethod( methodtest_caught_nested_exception22Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf23, new String[] {"p"} );
				}
			}
			//Function test:test_nested_exception
			{
			IMethod methodtest_nested_exception24;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_nested_exception24 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_nested_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_exception24, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf25;
					IModelElement[] methodtest_nested_exception24Childs = methodtest_nested_exception24.getChildren();
					methodf25 = ModelTestUtils.getAssertMethod( methodtest_nested_exception24Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf25, new String[] {"p"} );
				}
			}
			//Function test:test_exception_in_except_clause
			{
			IMethod methodtest_exception_in_except_clause26;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_exception_in_except_clause26 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_exception_in_except_clause", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_in_except_clause26, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf27;
					IModelElement[] methodtest_exception_in_except_clause26Childs = methodtest_exception_in_except_clause26.getChildren();
					methodf27 = ModelTestUtils.getAssertMethod( methodtest_exception_in_except_clause26Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf27, new String[] {"p"} );
				}
				//Function test:g
				{
				IMethod methodg28;
					IModelElement[] methodtest_exception_in_except_clause26Childs = methodtest_exception_in_except_clause26.getChildren();
					methodg28 = ModelTestUtils.getAssertMethod( methodtest_exception_in_except_clause26Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg28, new String[] {"p"} );
				}
			}
			//Function test:test_exception_propogation
			{
			IMethod methodtest_exception_propogation29;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_exception_propogation29 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_exception_propogation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_propogation29, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf30;
					IModelElement[] methodtest_exception_propogation29Childs = methodtest_exception_propogation29.getChildren();
					methodf30 = ModelTestUtils.getAssertMethod( methodtest_exception_propogation29Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf30, new String[] {"p"} );
				}
				//Function test:g
				{
				IMethod methodg31;
					IModelElement[] methodtest_exception_propogation29Childs = methodtest_exception_propogation29.getChildren();
					methodg31 = ModelTestUtils.getAssertMethod( methodtest_exception_propogation29Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg31, new String[] {"p"} );
				}
			}
			//Function test:test_raise_twice
			{
			IMethod methodtest_raise_twice32;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_raise_twice32 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_raise_twice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise_twice32, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf33;
					IModelElement[] methodtest_raise_twice32Childs = methodtest_raise_twice32.getChildren();
					methodf33 = ModelTestUtils.getAssertMethod( methodtest_raise_twice32Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf33, new String[] {"p"} );
				}
			}
			//Function test:test_raise_reraise
			{
			IMethod methodtest_raise_reraise34;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_raise_reraise34 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_raise_reraise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise_reraise34, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf35;
					IModelElement[] methodtest_raise_reraise34Childs = methodtest_raise_reraise34.getChildren();
					methodf35 = ModelTestUtils.getAssertMethod( methodtest_raise_reraise34Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf35, new String[] {"p"} );
				}
			}
			//Function test:test_raise
			{
			IMethod methodtest_raise36;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_raise36 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise36, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf37;
					IModelElement[] methodtest_raise36Childs = methodtest_raise36.getChildren();
					methodf37 = ModelTestUtils.getAssertMethod( methodtest_raise36Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf37, new String[] {"p"} );
				}
			}
			//Function test:test_distant_exception
			{
			IMethod methodtest_distant_exception38;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_distant_exception38 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_distant_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_distant_exception38, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf39;
					IModelElement[] methodtest_distant_exception38Childs = methodtest_distant_exception38.getChildren();
					methodf39 = ModelTestUtils.getAssertMethod( methodtest_distant_exception38Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg40;
					IModelElement[] methodtest_distant_exception38Childs = methodtest_distant_exception38.getChildren();
					methodg40 = ModelTestUtils.getAssertMethod( methodtest_distant_exception38Childs, "g", 0 );
				}
				//Function test:h
				{
				IMethod methodh41;
					IModelElement[] methodtest_distant_exception38Childs = methodtest_distant_exception38.getChildren();
					methodh41 = ModelTestUtils.getAssertMethod( methodtest_distant_exception38Childs, "h", 0 );
				}
				//Function test:i
				{
				IMethod methodi42;
					IModelElement[] methodtest_distant_exception38Childs = methodtest_distant_exception38.getChildren();
					methodi42 = ModelTestUtils.getAssertMethod( methodtest_distant_exception38Childs, "i", 0 );
				}
				//Function test:j
				{
				IMethod methodj43;
					IModelElement[] methodtest_distant_exception38Childs = methodtest_distant_exception38.getChildren();
					methodj43 = ModelTestUtils.getAssertMethod( methodtest_distant_exception38Childs, "j", 1 );
					ModelTestUtils.assertParameterNames( methodj43, new String[] {"p"} );
				}
			}
			//Function test:test_generator
			{
			IMethod methodtest_generator44;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_generator44 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_generator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_generator44, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf45;
					IModelElement[] methodtest_generator44Childs = methodtest_generator44.getChildren();
					methodf45 = ModelTestUtils.getAssertMethod( methodtest_generator44Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg46;
					IModelElement[] methodtest_generator44Childs = methodtest_generator44.getChildren();
					methodg46 = ModelTestUtils.getAssertMethod( methodtest_generator44Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg46, new String[] {"p"} );
				}
			}
			//Function test:test_stop_iteration
			{
			IMethod methodtest_stop_iteration47;
				IModelElement[] classProfileHookTestCase14Childs = classProfileHookTestCase14.getChildren();
				methodtest_stop_iteration47 = ModelTestUtils.getAssertMethod( classProfileHookTestCase14Childs, "test_stop_iteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_iteration47, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf48;
					IModelElement[] methodtest_stop_iteration47Childs = methodtest_stop_iteration47.getChildren();
					methodf48 = ModelTestUtils.getAssertMethod( methodtest_stop_iteration47Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg49;
					IModelElement[] methodtest_stop_iteration47Childs = methodtest_stop_iteration47.getChildren();
					methodg49 = ModelTestUtils.getAssertMethod( methodtest_stop_iteration47Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg49, new String[] {"p"} );
				}
			}
		}
		//Class test
		{
		IType classProfileSimulatorTestCase50;
			IModelElement[] moduleChilds = module.getChildren();
			classProfileSimulatorTestCase50 = ModelTestUtils.getAssertClass( moduleChilds, "ProfileSimulatorTestCase" );
			//Function test:new_watcher
			{
			IMethod methodnew_watcher51;
				IModelElement[] classProfileSimulatorTestCase50Childs = classProfileSimulatorTestCase50.getChildren();
				methodnew_watcher51 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase50Childs, "new_watcher", 1 );
				ModelTestUtils.assertParameterNames( methodnew_watcher51, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple52;
				IModelElement[] classProfileSimulatorTestCase50Childs = classProfileSimulatorTestCase50.getChildren();
				methodtest_simple52 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase50Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple52, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf53;
					IModelElement[] methodtest_simple52Childs = methodtest_simple52.getChildren();
					methodf53 = ModelTestUtils.getAssertMethod( methodtest_simple52Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf53, new String[] {"p"} );
				}
			}
			//Function test:test_basic_exception
			{
			IMethod methodtest_basic_exception54;
				IModelElement[] classProfileSimulatorTestCase50Childs = classProfileSimulatorTestCase50.getChildren();
				methodtest_basic_exception54 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase50Childs, "test_basic_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_exception54, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf55;
					IModelElement[] methodtest_basic_exception54Childs = methodtest_basic_exception54.getChildren();
					methodf55 = ModelTestUtils.getAssertMethod( methodtest_basic_exception54Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf55, new String[] {"p"} );
				}
			}
			//Function test:test_caught_exception
			{
			IMethod methodtest_caught_exception56;
				IModelElement[] classProfileSimulatorTestCase50Childs = classProfileSimulatorTestCase50.getChildren();
				methodtest_caught_exception56 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase50Childs, "test_caught_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caught_exception56, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf57;
					IModelElement[] methodtest_caught_exception56Childs = methodtest_caught_exception56.getChildren();
					methodf57 = ModelTestUtils.getAssertMethod( methodtest_caught_exception56Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf57, new String[] {"p"} );
				}
			}
			//Function test:test_distant_exception
			{
			IMethod methodtest_distant_exception58;
				IModelElement[] classProfileSimulatorTestCase50Childs = classProfileSimulatorTestCase50.getChildren();
				methodtest_distant_exception58 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase50Childs, "test_distant_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_distant_exception58, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf59;
					IModelElement[] methodtest_distant_exception58Childs = methodtest_distant_exception58.getChildren();
					methodf59 = ModelTestUtils.getAssertMethod( methodtest_distant_exception58Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg60;
					IModelElement[] methodtest_distant_exception58Childs = methodtest_distant_exception58.getChildren();
					methodg60 = ModelTestUtils.getAssertMethod( methodtest_distant_exception58Childs, "g", 0 );
				}
				//Function test:h
				{
				IMethod methodh61;
					IModelElement[] methodtest_distant_exception58Childs = methodtest_distant_exception58.getChildren();
					methodh61 = ModelTestUtils.getAssertMethod( methodtest_distant_exception58Childs, "h", 0 );
				}
				//Function test:i
				{
				IMethod methodi62;
					IModelElement[] methodtest_distant_exception58Childs = methodtest_distant_exception58.getChildren();
					methodi62 = ModelTestUtils.getAssertMethod( methodtest_distant_exception58Childs, "i", 0 );
				}
				//Function test:j
				{
				IMethod methodj63;
					IModelElement[] methodtest_distant_exception58Childs = methodtest_distant_exception58.getChildren();
					methodj63 = ModelTestUtils.getAssertMethod( methodtest_distant_exception58Childs, "j", 1 );
					ModelTestUtils.assertParameterNames( methodj63, new String[] {"p"} );
				}
			}
		}
		//Function test:ident
		{
		IMethod methodident64;
			IModelElement[] moduleChilds = module.getChildren();
			methodident64 = ModelTestUtils.getAssertMethod( moduleChilds, "ident", 1 );
			ModelTestUtils.assertParameterNames( methodident64, new String[] {"function"} );
		}
		//Function test:protect
		{
		IMethod methodprotect65;
			IModelElement[] moduleChilds = module.getChildren();
			methodprotect65 = ModelTestUtils.getAssertMethod( moduleChilds, "protect", 2 );
			ModelTestUtils.assertParameterNames( methodprotect65, new String[] {"f", "p"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "protect_ident");
		}
		//Function test:capture_events
		{
		IMethod methodcapture_events66;
			IModelElement[] moduleChilds = module.getChildren();
			methodcapture_events66 = ModelTestUtils.getAssertMethod( moduleChilds, "capture_events", 2 );
			ModelTestUtils.assertParameterNames( methodcapture_events66, new String[] {"callable", "p"} );
		}
		//Function test:show_events
		{
		IMethod methodshow_events67;
			IModelElement[] moduleChilds = module.getChildren();
			methodshow_events67 = ModelTestUtils.getAssertMethod( moduleChilds, "show_events", 1 );
			ModelTestUtils.assertParameterNames( methodshow_events67, new String[] {"callable"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main68;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main68 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen30( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_re.py"));

		assertNotNull("Module test_re.py not found", module);
		assertEquals("test_re.py", module.getElementName());
		
		//Class test
		{
		IType classReTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classReTests0 = ModelTestUtils.getAssertClass( moduleChilds, "ReTests" );
			//Function test:test_weakref
			{
			IMethod methodtest_weakref1;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_weakref1 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_weakref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_weakref1, new String[] {"self"} );
			}
			//Function test:test_search_star_plus
			{
			IMethod methodtest_search_star_plus2;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_search_star_plus2 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_search_star_plus", 1 );
				ModelTestUtils.assertParameterNames( methodtest_search_star_plus2, new String[] {"self"} );
			}
			//Function test:bump_num
			{
			IMethod methodbump_num3;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodbump_num3 = ModelTestUtils.getAssertMethod( classReTests0Childs, "bump_num", 2 );
				ModelTestUtils.assertParameterNames( methodbump_num3, new String[] {"self", "matchobj"} );
			}
			//Function test:test_basic_re_sub
			{
			IMethod methodtest_basic_re_sub4;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_basic_re_sub4 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_basic_re_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_re_sub4, new String[] {"self"} );
			}
			//Function test:test_bug_449964
			{
			IMethod methodtest_bug_4499645;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_4499645 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_449964", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_4499645, new String[] {"self"} );
			}
			//Function test:test_bug_449000
			{
			IMethod methodtest_bug_4490006;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_4490006 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_449000", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_4490006, new String[] {"self"} );
			}
			//Function test:test_sub_template_numeric_escape
			{
			IMethod methodtest_sub_template_numeric_escape7;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_sub_template_numeric_escape7 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_sub_template_numeric_escape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub_template_numeric_escape7, new String[] {"self"} );
			}
			//Function test:test_qualified_re_sub
			{
			IMethod methodtest_qualified_re_sub8;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_qualified_re_sub8 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_qualified_re_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_qualified_re_sub8, new String[] {"self"} );
			}
			//Function test:test_bug_114660
			{
			IMethod methodtest_bug_1146609;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_1146609 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_114660", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_1146609, new String[] {"self"} );
			}
			//Function test:test_bug_462270
			{
			IMethod methodtest_bug_46227010;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_46227010 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_462270", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_46227010, new String[] {"self"} );
			}
			//Function test:test_symbolic_refs
			{
			IMethod methodtest_symbolic_refs11;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_symbolic_refs11 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_symbolic_refs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_symbolic_refs11, new String[] {"self"} );
			}
			//Function test:test_re_subn
			{
			IMethod methodtest_re_subn12;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_subn12 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_subn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_subn12, new String[] {"self"} );
			}
			//Function test:test_re_split
			{
			IMethod methodtest_re_split13;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_split13 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_split13, new String[] {"self"} );
			}
			//Function test:test_qualified_re_split
			{
			IMethod methodtest_qualified_re_split14;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_qualified_re_split14 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_qualified_re_split", 1 );
				ModelTestUtils.assertParameterNames( methodtest_qualified_re_split14, new String[] {"self"} );
			}
			//Function test:test_re_findall
			{
			IMethod methodtest_re_findall15;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_findall15 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_findall", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_findall15, new String[] {"self"} );
			}
			//Function test:test_bug_117612
			{
			IMethod methodtest_bug_11761216;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_11761216 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_117612", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_11761216, new String[] {"self"} );
			}
			//Function test:test_re_match
			{
			IMethod methodtest_re_match17;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_match17 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_match", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_match17, new String[] {"self"} );
			}
			//Function test:test_re_groupref_exists
			{
			IMethod methodtest_re_groupref_exists18;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_groupref_exists18 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_groupref_exists", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_groupref_exists18, new String[] {"self"} );
			}
			//Function test:test_re_groupref
			{
			IMethod methodtest_re_groupref19;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_groupref19 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_groupref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_groupref19, new String[] {"self"} );
			}
			//Function test:test_groupdict
			{
			IMethod methodtest_groupdict20;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_groupdict20 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_groupdict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_groupdict20, new String[] {"self"} );
			}
			//Function test:test_expand
			{
			IMethod methodtest_expand21;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_expand21 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_expand", 1 );
				ModelTestUtils.assertParameterNames( methodtest_expand21, new String[] {"self"} );
			}
			//Function test:test_repeat_minmax
			{
			IMethod methodtest_repeat_minmax22;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_repeat_minmax22 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_repeat_minmax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repeat_minmax22, new String[] {"self"} );
			}
			//Function test:test_getattr
			{
			IMethod methodtest_getattr23;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_getattr23 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_getattr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getattr23, new String[] {"self"} );
			}
			//Function test:test_special_escapes
			{
			IMethod methodtest_special_escapes24;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_special_escapes24 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_special_escapes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_special_escapes24, new String[] {"self"} );
			}
			//Function test:test_ignore_case
			{
			IMethod methodtest_ignore_case25;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_ignore_case25 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_ignore_case", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ignore_case25, new String[] {"self"} );
			}
			//Function test:test_bigcharset
			{
			IMethod methodtest_bigcharset26;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bigcharset26 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bigcharset", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bigcharset26, new String[] {"self"} );
			}
			//Function test:test_anyall
			{
			IMethod methodtest_anyall27;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_anyall27 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_anyall", 1 );
				ModelTestUtils.assertParameterNames( methodtest_anyall27, new String[] {"self"} );
			}
			//Function test:test_non_consuming
			{
			IMethod methodtest_non_consuming28;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_non_consuming28 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_non_consuming", 1 );
				ModelTestUtils.assertParameterNames( methodtest_non_consuming28, new String[] {"self"} );
			}
			//Function test:test_ignore_case
			{
			IMethod methodtest_ignore_case29;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_ignore_case29 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_ignore_case", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ignore_case29, new String[] {"self"} );
			}
			//Function test:test_category
			{
			IMethod methodtest_category30;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_category30 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_category", 1 );
				ModelTestUtils.assertParameterNames( methodtest_category30, new String[] {"self"} );
			}
			//Function test:test_getlower
			{
			IMethod methodtest_getlower31;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_getlower31 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_getlower", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getlower31, new String[] {"self"} );
			}
			//Function test:test_not_literal
			{
			IMethod methodtest_not_literal32;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_not_literal32 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_not_literal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_not_literal32, new String[] {"self"} );
			}
			//Function test:test_search_coverage
			{
			IMethod methodtest_search_coverage33;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_search_coverage33 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_search_coverage", 1 );
				ModelTestUtils.assertParameterNames( methodtest_search_coverage33, new String[] {"self"} );
			}
			//Function test:test_re_escape
			{
			IMethod methodtest_re_escape34;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_re_escape34 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_re_escape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_re_escape34, new String[] {"self"} );
			}
			//Function test:test_pickling
			{
			IMethod methodtest_pickling35;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_pickling35 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_pickling", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pickling35, new String[] {"self"} );
			}
			//Function test:pickle_test
			{
			IMethod methodpickle_test36;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodpickle_test36 = ModelTestUtils.getAssertMethod( classReTests0Childs, "pickle_test", 2 );
				ModelTestUtils.assertParameterNames( methodpickle_test36, new String[] {"self", "pickle"} );
			}
			//Function test:test_constants
			{
			IMethod methodtest_constants37;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_constants37 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_constants", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constants37, new String[] {"self"} );
			}
			//Function test:test_flags
			{
			IMethod methodtest_flags38;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_flags38 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_flags", 1 );
				ModelTestUtils.assertParameterNames( methodtest_flags38, new String[] {"self"} );
			}
			//Function test:test_sre_character_literals
			{
			IMethod methodtest_sre_character_literals39;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_sre_character_literals39 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_sre_character_literals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sre_character_literals39, new String[] {"self"} );
			}
			//Function test:test_sre_character_class_literals
			{
			IMethod methodtest_sre_character_class_literals40;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_sre_character_class_literals40 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_sre_character_class_literals", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sre_character_class_literals40, new String[] {"self"} );
			}
			//Function test:test_bug_113254
			{
			IMethod methodtest_bug_11325441;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_11325441 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_113254", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_11325441, new String[] {"self"} );
			}
			//Function test:test_bug_527371
			{
			IMethod methodtest_bug_52737142;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_52737142 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_527371", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_52737142, new String[] {"self"} );
			}
			//Function test:test_bug_545855
			{
			IMethod methodtest_bug_54585543;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_54585543 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_545855", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_54585543, new String[] {"self"} );
			}
			//Function test:test_bug_418626
			{
			IMethod methodtest_bug_41862644;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_41862644 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_418626", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_41862644, new String[] {"self"} );
			}
			//Function test:test_bug_612074
			{
			IMethod methodtest_bug_61207445;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_61207445 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_612074", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_61207445, new String[] {"self"} );
			}
			//Function test:test_stack_overflow
			{
			IMethod methodtest_stack_overflow46;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_stack_overflow46 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_stack_overflow", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stack_overflow46, new String[] {"self"} );
			}
			//Function test:test_scanner
			{
			IMethod methodtest_scanner47;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_scanner47 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_scanner", 1 );
				ModelTestUtils.assertParameterNames( methodtest_scanner47, new String[] {"self"} );
				//Function test:s_ident
				{
				IMethod methods_ident48;
					IModelElement[] methodtest_scanner47Childs = methodtest_scanner47.getChildren();
					methods_ident48 = ModelTestUtils.getAssertMethod( methodtest_scanner47Childs, "s_ident", 2 );
					ModelTestUtils.assertParameterNames( methods_ident48, new String[] {"scanner", "token"} );
				}
				//Function test:s_operator
				{
				IMethod methods_operator49;
					IModelElement[] methodtest_scanner47Childs = methodtest_scanner47.getChildren();
					methods_operator49 = ModelTestUtils.getAssertMethod( methodtest_scanner47Childs, "s_operator", 2 );
					ModelTestUtils.assertParameterNames( methods_operator49, new String[] {"scanner", "token"} );
				}
				//Function test:s_float
				{
				IMethod methods_float50;
					IModelElement[] methodtest_scanner47Childs = methodtest_scanner47.getChildren();
					methods_float50 = ModelTestUtils.getAssertMethod( methodtest_scanner47Childs, "s_float", 2 );
					ModelTestUtils.assertParameterNames( methods_float50, new String[] {"scanner", "token"} );
				}
				//Function test:s_int
				{
				IMethod methods_int51;
					IModelElement[] methodtest_scanner47Childs = methodtest_scanner47.getChildren();
					methods_int51 = ModelTestUtils.getAssertMethod( methodtest_scanner47Childs, "s_int", 2 );
					ModelTestUtils.assertParameterNames( methods_int51, new String[] {"scanner", "token"} );
				}
			}
			//Function test:test_bug_448951
			{
			IMethod methodtest_bug_44895152;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_44895152 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_448951", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_44895152, new String[] {"self"} );
			}
			//Function test:test_bug_725106
			{
			IMethod methodtest_bug_72510653;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_72510653 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_725106", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_72510653, new String[] {"self"} );
			}
			//Function test:test_bug_725149
			{
			IMethod methodtest_bug_72514954;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_72514954 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_725149", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_72514954, new String[] {"self"} );
			}
			//Function test:test_bug_764548
			{
			IMethod methodtest_bug_76454855;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_76454855 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_764548", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_76454855, new String[] {"self"} );
				//Class test
				{
				IType classmy_unicode56;
					IModelElement[] methodtest_bug_76454855Childs = methodtest_bug_76454855.getChildren();
					classmy_unicode56 = ModelTestUtils.getAssertClass( methodtest_bug_76454855Childs, "my_unicode" );
				}
			}
			//Function test:test_finditer
			{
			IMethod methodtest_finditer57;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_finditer57 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_finditer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_finditer57, new String[] {"self"} );
			}
			//Function test:test_bug_926075
			{
			IMethod methodtest_bug_92607558;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_92607558 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_926075", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_92607558, new String[] {"self"} );
			}
			//Function test:test_bug_931848
			{
			IMethod methodtest_bug_93184859;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_93184859 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_931848", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_93184859, new String[] {"self"} );
			}
			//Function test:test_bug_581080
			{
			IMethod methodtest_bug_58108060;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_58108060 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_581080", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_58108060, new String[] {"self"} );
			}
			//Function test:test_bug_817234
			{
			IMethod methodtest_bug_81723461;
				IModelElement[] classReTests0Childs = classReTests0.getChildren();
				methodtest_bug_81723461 = ModelTestUtils.getAssertMethod( classReTests0Childs, "test_bug_817234", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bug_81723461, new String[] {"self"} );
			}
		}
		//Function test:run_re_tests
		{
		IMethod methodrun_re_tests62;
			IModelElement[] moduleChilds = module.getChildren();
			methodrun_re_tests62 = ModelTestUtils.getAssertMethod( moduleChilds, "run_re_tests", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main63;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main63 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen31( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_ossaudiodev.py"));

		assertNotNull("Module test_ossaudiodev.py not found", module);
		assertEquals("test_ossaudiodev.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "AFMT_S16_NE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "AFMT_S16_NE");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SND_FORMAT_MULAW_8");
		}
		//Function test:read_sound_file
		{
		IMethod methodread_sound_file0;
			IModelElement[] moduleChilds = module.getChildren();
			methodread_sound_file0 = ModelTestUtils.getAssertMethod( moduleChilds, "read_sound_file", 1 );
			ModelTestUtils.assertParameterNames( methodread_sound_file0, new String[] {"path"} );
		}
		//Function test:play_sound_file
		{
		IMethod methodplay_sound_file1;
			IModelElement[] moduleChilds = module.getChildren();
			methodplay_sound_file1 = ModelTestUtils.getAssertMethod( moduleChilds, "play_sound_file", 4 );
			ModelTestUtils.assertParameterNames( methodplay_sound_file1, new String[] {"data", "rate", "ssize", "nchannels"} );
		}
		//Function test:test_setparameters
		{
		IMethod methodtest_setparameters2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_setparameters2 = ModelTestUtils.getAssertMethod( moduleChilds, "test_setparameters", 1 );
			ModelTestUtils.assertParameterNames( methodtest_setparameters2, new String[] {"dsp"} );
		}
		//Function test:test_bad_setparameters
		{
		IMethod methodtest_bad_setparameters3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bad_setparameters3 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bad_setparameters", 1 );
			ModelTestUtils.assertParameterNames( methodtest_bad_setparameters3, new String[] {"dsp"} );
		}
		//Function test:test
		{
		IMethod methodtest4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest4 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen32( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_uu.py"));

		assertNotNull("Module test_uu.py not found", module);
		assertEquals("test_uu.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "plaintext");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "encodedtext");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "encodedtextwrapped");
		}
		//Class test
		{
		IType classUUTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classUUTest0 = ModelTestUtils.getAssertClass( moduleChilds, "UUTest" );
			//Function test:test_encode
			{
			IMethod methodtest_encode1;
				IModelElement[] classUUTest0Childs = classUUTest0.getChildren();
				methodtest_encode1 = ModelTestUtils.getAssertMethod( classUUTest0Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode1, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode2;
				IModelElement[] classUUTest0Childs = classUUTest0.getChildren();
				methodtest_decode2 = ModelTestUtils.getAssertMethod( classUUTest0Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode2, new String[] {"self"} );
			}
			//Function test:test_truncatedinput
			{
			IMethod methodtest_truncatedinput3;
				IModelElement[] classUUTest0Childs = classUUTest0.getChildren();
				methodtest_truncatedinput3 = ModelTestUtils.getAssertMethod( classUUTest0Childs, "test_truncatedinput", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truncatedinput3, new String[] {"self"} );
			}
			//Function test:test_missingbegin
			{
			IMethod methodtest_missingbegin4;
				IModelElement[] classUUTest0Childs = classUUTest0.getChildren();
				methodtest_missingbegin4 = ModelTestUtils.getAssertMethod( classUUTest0Childs, "test_missingbegin", 1 );
				ModelTestUtils.assertParameterNames( methodtest_missingbegin4, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUUStdIOTest5;
			IModelElement[] moduleChilds = module.getChildren();
			classUUStdIOTest5 = ModelTestUtils.getAssertClass( moduleChilds, "UUStdIOTest" );
			//Function test:setUp
			{
			IMethod methodsetUp6;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodsetUp6 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp6, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown7;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodtearDown7 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown7, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode8;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodtest_encode8 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode8, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode9;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodtest_decode9 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode9, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUUFileTest10;
			IModelElement[] moduleChilds = module.getChildren();
			classUUFileTest10 = ModelTestUtils.getAssertClass( moduleChilds, "UUFileTest" );
			//Function test:_kill
			{
			IMethod method_kill11;
				IModelElement[] classUUFileTest10Childs = classUUFileTest10.getChildren();
				method_kill11 = ModelTestUtils.getAssertMethod( classUUFileTest10Childs, "_kill", 2 );
				ModelTestUtils.assertParameterNames( method_kill11, new String[] {"self", "f"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp12;
				IModelElement[] classUUFileTest10Childs = classUUFileTest10.getChildren();
				methodsetUp12 = ModelTestUtils.getAssertMethod( classUUFileTest10Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp12, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown13;
				IModelElement[] classUUFileTest10Childs = classUUFileTest10.getChildren();
				methodtearDown13 = ModelTestUtils.getAssertMethod( classUUFileTest10Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown13, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode14;
				IModelElement[] classUUFileTest10Childs = classUUFileTest10.getChildren();
				methodtest_encode14 = ModelTestUtils.getAssertMethod( classUUFileTest10Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode14, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode15;
				IModelElement[] classUUFileTest10Childs = classUUFileTest10.getChildren();
				methodtest_decode15 = ModelTestUtils.getAssertMethod( classUUFileTest10Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode15, new String[] {"self"} );
			}
			//Function test:test_decodetwice
			{
			IMethod methodtest_decodetwice16;
				IModelElement[] classUUFileTest10Childs = classUUFileTest10.getChildren();
				methodtest_decodetwice16 = ModelTestUtils.getAssertMethod( classUUFileTest10Childs, "test_decodetwice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodetwice16, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main17 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen33( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_audioop.py"));

		assertNotNull("Module test_audioop.py not found", module);
		assertEquals("test_audioop.py", module.getElementName());
		
		//Function test:gendata1
		{
		IMethod methodgendata10;
			IModelElement[] moduleChilds = module.getChildren();
			methodgendata10 = ModelTestUtils.getAssertMethod( moduleChilds, "gendata1", 0 );
		}
		//Function test:gendata2
		{
		IMethod methodgendata21;
			IModelElement[] moduleChilds = module.getChildren();
			methodgendata21 = ModelTestUtils.getAssertMethod( moduleChilds, "gendata2", 0 );
		}
		//Function test:gendata4
		{
		IMethod methodgendata42;
			IModelElement[] moduleChilds = module.getChildren();
			methodgendata42 = ModelTestUtils.getAssertMethod( moduleChilds, "gendata4", 0 );
		}
		//Function test:testmax
		{
		IMethod methodtestmax3;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestmax3 = ModelTestUtils.getAssertMethod( moduleChilds, "testmax", 1 );
			ModelTestUtils.assertParameterNames( methodtestmax3, new String[] {"data"} );
		}
		//Function test:testminmax
		{
		IMethod methodtestminmax4;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestminmax4 = ModelTestUtils.getAssertMethod( moduleChilds, "testminmax", 1 );
			ModelTestUtils.assertParameterNames( methodtestminmax4, new String[] {"data"} );
		}
		//Function test:testmaxpp
		{
		IMethod methodtestmaxpp5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestmaxpp5 = ModelTestUtils.getAssertMethod( moduleChilds, "testmaxpp", 1 );
			ModelTestUtils.assertParameterNames( methodtestmaxpp5, new String[] {"data"} );
		}
		//Function test:testavg
		{
		IMethod methodtestavg6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestavg6 = ModelTestUtils.getAssertMethod( moduleChilds, "testavg", 1 );
			ModelTestUtils.assertParameterNames( methodtestavg6, new String[] {"data"} );
		}
		//Function test:testavgpp
		{
		IMethod methodtestavgpp7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestavgpp7 = ModelTestUtils.getAssertMethod( moduleChilds, "testavgpp", 1 );
			ModelTestUtils.assertParameterNames( methodtestavgpp7, new String[] {"data"} );
		}
		//Function test:testrms
		{
		IMethod methodtestrms8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestrms8 = ModelTestUtils.getAssertMethod( moduleChilds, "testrms", 1 );
			ModelTestUtils.assertParameterNames( methodtestrms8, new String[] {"data"} );
		}
		//Function test:testcross
		{
		IMethod methodtestcross9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestcross9 = ModelTestUtils.getAssertMethod( moduleChilds, "testcross", 1 );
			ModelTestUtils.assertParameterNames( methodtestcross9, new String[] {"data"} );
		}
		//Function test:testadd
		{
		IMethod methodtestadd10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestadd10 = ModelTestUtils.getAssertMethod( moduleChilds, "testadd", 1 );
			ModelTestUtils.assertParameterNames( methodtestadd10, new String[] {"data"} );
		}
		//Function test:testbias
		{
		IMethod methodtestbias11;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestbias11 = ModelTestUtils.getAssertMethod( moduleChilds, "testbias", 1 );
			ModelTestUtils.assertParameterNames( methodtestbias11, new String[] {"data"} );
		}
		//Function test:testlin2lin
		{
		IMethod methodtestlin2lin12;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestlin2lin12 = ModelTestUtils.getAssertMethod( moduleChilds, "testlin2lin", 1 );
			ModelTestUtils.assertParameterNames( methodtestlin2lin12, new String[] {"data"} );
		}
		//Function test:testadpcm2lin
		{
		IMethod methodtestadpcm2lin13;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestadpcm2lin13 = ModelTestUtils.getAssertMethod( moduleChilds, "testadpcm2lin", 1 );
			ModelTestUtils.assertParameterNames( methodtestadpcm2lin13, new String[] {"data"} );
		}
		//Function test:testlin2adpcm
		{
		IMethod methodtestlin2adpcm14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestlin2adpcm14 = ModelTestUtils.getAssertMethod( moduleChilds, "testlin2adpcm", 1 );
			ModelTestUtils.assertParameterNames( methodtestlin2adpcm14, new String[] {"data"} );
		}
		//Function test:testlin2ulaw
		{
		IMethod methodtestlin2ulaw15;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestlin2ulaw15 = ModelTestUtils.getAssertMethod( moduleChilds, "testlin2ulaw", 1 );
			ModelTestUtils.assertParameterNames( methodtestlin2ulaw15, new String[] {"data"} );
		}
		//Function test:testulaw2lin
		{
		IMethod methodtestulaw2lin16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestulaw2lin16 = ModelTestUtils.getAssertMethod( moduleChilds, "testulaw2lin", 1 );
			ModelTestUtils.assertParameterNames( methodtestulaw2lin16, new String[] {"data"} );
		}
		//Function test:testmul
		{
		IMethod methodtestmul17;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestmul17 = ModelTestUtils.getAssertMethod( moduleChilds, "testmul", 1 );
			ModelTestUtils.assertParameterNames( methodtestmul17, new String[] {"data"} );
		}
		//Function test:testratecv
		{
		IMethod methodtestratecv18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestratecv18 = ModelTestUtils.getAssertMethod( moduleChilds, "testratecv", 1 );
			ModelTestUtils.assertParameterNames( methodtestratecv18, new String[] {"data"} );
		}
		//Function test:testreverse
		{
		IMethod methodtestreverse19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestreverse19 = ModelTestUtils.getAssertMethod( moduleChilds, "testreverse", 1 );
			ModelTestUtils.assertParameterNames( methodtestreverse19, new String[] {"data"} );
		}
		//Function test:testtomono
		{
		IMethod methodtesttomono20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtesttomono20 = ModelTestUtils.getAssertMethod( moduleChilds, "testtomono", 1 );
			ModelTestUtils.assertParameterNames( methodtesttomono20, new String[] {"data"} );
		}
		//Function test:testtostereo
		{
		IMethod methodtesttostereo21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtesttostereo21 = ModelTestUtils.getAssertMethod( moduleChilds, "testtostereo", 1 );
			ModelTestUtils.assertParameterNames( methodtesttostereo21, new String[] {"data"} );
		}
		//Function test:testfindfactor
		{
		IMethod methodtestfindfactor22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestfindfactor22 = ModelTestUtils.getAssertMethod( moduleChilds, "testfindfactor", 1 );
			ModelTestUtils.assertParameterNames( methodtestfindfactor22, new String[] {"data"} );
		}
		//Function test:testfindfit
		{
		IMethod methodtestfindfit23;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestfindfit23 = ModelTestUtils.getAssertMethod( moduleChilds, "testfindfit", 1 );
			ModelTestUtils.assertParameterNames( methodtestfindfit23, new String[] {"data"} );
		}
		//Function test:testfindmax
		{
		IMethod methodtestfindmax24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestfindmax24 = ModelTestUtils.getAssertMethod( moduleChilds, "testfindmax", 1 );
			ModelTestUtils.assertParameterNames( methodtestfindmax24, new String[] {"data"} );
		}
		//Function test:testgetsample
		{
		IMethod methodtestgetsample25;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestgetsample25 = ModelTestUtils.getAssertMethod( moduleChilds, "testgetsample", 1 );
			ModelTestUtils.assertParameterNames( methodtestgetsample25, new String[] {"data"} );
		}
		//Function test:testone
		{
		IMethod methodtestone26;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestone26 = ModelTestUtils.getAssertMethod( moduleChilds, "testone", 2 );
			ModelTestUtils.assertParameterNames( methodtestone26, new String[] {"name", "data"} );
		}
		//Function test:testall
		{
		IMethod methodtestall27;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestall27 = ModelTestUtils.getAssertMethod( moduleChilds, "testall", 0 );
		}

	}
	public void testModelGen34( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_crypt.py"));

		assertNotNull("Module test_crypt.py not found", module);
		assertEquals("test_crypt.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "c");
		}

	}
	public void testModelGen35( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_linuxaudiodev.py"));

		assertNotNull("Module test_linuxaudiodev.py not found", module);
		assertEquals("test_linuxaudiodev.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "SND_FORMAT_MULAW_8");
		}
		//Function test:play_sound_file
		{
		IMethod methodplay_sound_file0;
			IModelElement[] moduleChilds = module.getChildren();
			methodplay_sound_file0 = ModelTestUtils.getAssertMethod( moduleChilds, "play_sound_file", 1 );
			ModelTestUtils.assertParameterNames( methodplay_sound_file0, new String[] {"path"} );
		}
		//Function test:test_errors
		{
		IMethod methodtest_errors1;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_errors1 = ModelTestUtils.getAssertMethod( moduleChilds, "test_errors", 0 );
		}
		//Function test:test
		{
		IMethod methodtest2;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest2 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
		}

	}
	public void testModelGen36( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_compiler.py"));

		assertNotNull("Module test_compiler.py not found", module);
		assertEquals("test_compiler.py", module.getElementName());
		
		//Class test
		{
		IType classCompilerTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classCompilerTest0 = ModelTestUtils.getAssertClass( moduleChilds, "CompilerTest" );
			//Function test:testCompileLibrary
			{
			IMethod methodtestCompileLibrary1;
				IModelElement[] classCompilerTest0Childs = classCompilerTest0.getChildren();
				methodtestCompileLibrary1 = ModelTestUtils.getAssertMethod( classCompilerTest0Childs, "testCompileLibrary", 1 );
				ModelTestUtils.assertParameterNames( methodtestCompileLibrary1, new String[] {"self"} );
			}
			//Function test:testLineNo
			{
			IMethod methodtestLineNo2;
				IModelElement[] classCompilerTest0Childs = classCompilerTest0.getChildren();
				methodtestLineNo2 = ModelTestUtils.getAssertMethod( classCompilerTest0Childs, "testLineNo", 1 );
				ModelTestUtils.assertParameterNames( methodtestLineNo2, new String[] {"self"} );
			}
			//Function test:check_lineno
			{
			IMethod methodcheck_lineno3;
				IModelElement[] classCompilerTest0Childs = classCompilerTest0.getChildren();
				methodcheck_lineno3 = ModelTestUtils.getAssertMethod( classCompilerTest0Childs, "check_lineno", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_lineno3, new String[] {"self", "node"} );
			}
			//Function test:_check_lineno
			{
			IMethod method_check_lineno4;
				IModelElement[] classCompilerTest0Childs = classCompilerTest0.getChildren();
				method_check_lineno4 = ModelTestUtils.getAssertMethod( classCompilerTest0Childs, "_check_lineno", 2 );
				ModelTestUtils.assertParameterNames( method_check_lineno4, new String[] {"self", "node"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "NOLINENO");
		}
		//Class test
		{
		IType classToto5;
			IModelElement[] moduleChilds = module.getChildren();
			classToto5 = ModelTestUtils.getAssertClass( moduleChilds, "Toto" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "l");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "yo");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "b");
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen37( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_shlex.py"));

		assertNotNull("Module test_shlex.py not found", module);
		assertEquals("test_shlex.py", module.getElementName());
		
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "data");
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "posix_data");
		}
		//Class test
		{
		IType classShlexTest0;
			IModelElement[] moduleChilds = module.getChildren();
			classShlexTest0 = ModelTestUtils.getAssertClass( moduleChilds, "ShlexTest" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:splitTest
			{
			IMethod methodsplitTest2;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodsplitTest2 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "splitTest", 3 );
				ModelTestUtils.assertParameterNames( methodsplitTest2, new String[] {"self", "data", "comments"} );
			}
			//Function test:oldSplit
			{
			IMethod methodoldSplit3;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodoldSplit3 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "oldSplit", 2 );
				ModelTestUtils.assertParameterNames( methodoldSplit3, new String[] {"self", "s"} );
			}
			//Function test:testSplitPosix
			{
			IMethod methodtestSplitPosix4;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodtestSplitPosix4 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "testSplitPosix", 1 );
				ModelTestUtils.assertParameterNames( methodtestSplitPosix4, new String[] {"self"} );
			}
			//Function test:testCompat
			{
			IMethod methodtestCompat5;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodtestCompat5 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "testCompat", 1 );
				ModelTestUtils.assertParameterNames( methodtestCompat5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen38( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("testall.py"));

		assertNotNull("Module testall.py not found", module);
		assertEquals("testall.py", module.getElementName());
		

	}
	public void testModelGen39( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_xpickle.py"));

		assertNotNull("Module test_xpickle.py not found", module);
		assertEquals("test_xpickle.py", module.getElementName());
		
		//Class test
		{
		IType classDumpCPickle_LoadPickle0;
			IModelElement[] moduleChilds = module.getChildren();
			classDumpCPickle_LoadPickle0 = ModelTestUtils.getAssertClass( moduleChilds, "DumpCPickle_LoadPickle" );
			{
				IModelElement[] classDumpCPickle_LoadPickle0Childs = classDumpCPickle_LoadPickle0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDumpCPickle_LoadPickle0Childs, "error");
			}
			//Function test:dumps
			{
			IMethod methoddumps1;
				IModelElement[] classDumpCPickle_LoadPickle0Childs = classDumpCPickle_LoadPickle0.getChildren();
				methoddumps1 = ModelTestUtils.getAssertMethod( classDumpCPickle_LoadPickle0Childs, "dumps", 4 );
				ModelTestUtils.assertParameterNames( methoddumps1, new String[] {"self", "arg", "proto", "fast"} );
			}
			//Function test:loads
			{
			IMethod methodloads2;
				IModelElement[] classDumpCPickle_LoadPickle0Childs = classDumpCPickle_LoadPickle0.getChildren();
				methodloads2 = ModelTestUtils.getAssertMethod( classDumpCPickle_LoadPickle0Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads2, new String[] {"self", "buf"} );
			}
		}
		//Class test
		{
		IType classDumpPickle_LoadCPickle3;
			IModelElement[] moduleChilds = module.getChildren();
			classDumpPickle_LoadCPickle3 = ModelTestUtils.getAssertClass( moduleChilds, "DumpPickle_LoadCPickle" );
			{
				IModelElement[] classDumpPickle_LoadCPickle3Childs = classDumpPickle_LoadCPickle3.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDumpPickle_LoadCPickle3Childs, "error");
			}
			//Function test:dumps
			{
			IMethod methoddumps4;
				IModelElement[] classDumpPickle_LoadCPickle3Childs = classDumpPickle_LoadCPickle3.getChildren();
				methoddumps4 = ModelTestUtils.getAssertMethod( classDumpPickle_LoadCPickle3Childs, "dumps", 4 );
				ModelTestUtils.assertParameterNames( methoddumps4, new String[] {"self", "arg", "proto", "fast"} );
			}
			//Function test:loads
			{
			IMethod methodloads5;
				IModelElement[] classDumpPickle_LoadCPickle3Childs = classDumpPickle_LoadCPickle3.getChildren();
				methodloads5 = ModelTestUtils.getAssertMethod( classDumpPickle_LoadCPickle3Childs, "loads", 2 );
				ModelTestUtils.assertParameterNames( methodloads5, new String[] {"self", "buf"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen40( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_scriptpackages.py"));

		assertNotNull("Module test_scriptpackages.py not found", module);
		assertEquals("test_scriptpackages.py", module.getElementName());
		
		//Class test
		{
		IType classTestScriptpackages0;
			IModelElement[] moduleChilds = module.getChildren();
			classTestScriptpackages0 = ModelTestUtils.getAssertClass( moduleChilds, "TestScriptpackages" );
			//Function test:_test_scriptpackage
			{
			IMethod method_test_scriptpackage1;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				method_test_scriptpackage1 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "_test_scriptpackage", 3 );
				ModelTestUtils.assertParameterNames( method_test_scriptpackage1, new String[] {"self", "package", "testobject"} );
			}
			//Function test:test__builtinSuites
			{
			IMethod methodtest__builtinSuites2;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest__builtinSuites2 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test__builtinSuites", 1 );
				ModelTestUtils.assertParameterNames( methodtest__builtinSuites2, new String[] {"self"} );
			}
			//Function test:test_StdSuites
			{
			IMethod methodtest_StdSuites3;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_StdSuites3 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_StdSuites", 1 );
				ModelTestUtils.assertParameterNames( methodtest_StdSuites3, new String[] {"self"} );
			}
			//Function test:test_SystemEvents
			{
			IMethod methodtest_SystemEvents4;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_SystemEvents4 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_SystemEvents", 1 );
				ModelTestUtils.assertParameterNames( methodtest_SystemEvents4, new String[] {"self"} );
			}
			//Function test:test_Finder
			{
			IMethod methodtest_Finder5;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_Finder5 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_Finder", 1 );
				ModelTestUtils.assertParameterNames( methodtest_Finder5, new String[] {"self"} );
			}
			//Function test:test_Terminal
			{
			IMethod methodtest_Terminal6;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_Terminal6 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_Terminal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_Terminal6, new String[] {"self"} );
			}
			//Function test:test_Netscape
			{
			IMethod methodtest_Netscape7;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_Netscape7 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_Netscape", 1 );
				ModelTestUtils.assertParameterNames( methodtest_Netscape7, new String[] {"self"} );
			}
			//Function test:test_Explorer
			{
			IMethod methodtest_Explorer8;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_Explorer8 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_Explorer", 1 );
				ModelTestUtils.assertParameterNames( methodtest_Explorer8, new String[] {"self"} );
			}
			//Function test:test_CodeWarrior
			{
			IMethod methodtest_CodeWarrior9;
				IModelElement[] classTestScriptpackages0Childs = classTestScriptpackages0.getChildren();
				methodtest_CodeWarrior9 = ModelTestUtils.getAssertMethod( classTestScriptpackages0Childs, "test_CodeWarrior", 1 );
				ModelTestUtils.assertParameterNames( methodtest_CodeWarrior9, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen41( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_unpack.py"));

		assertNotNull("Module test_unpack.py not found", module);
		assertEquals("test_unpack.py", module.getElementName());
		
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
	public void testModelGen42( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_email_codecs.py"));

		assertNotNull("Module test_email_codecs.py not found", module);
		assertEquals("test_email_codecs.py", module.getElementName());
		

	}
	public void testModelGen43( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_rfc822.py"));

		assertNotNull("Module test_rfc822.py not found", module);
		assertEquals("test_rfc822.py", module.getElementName());
		
		//Class test
		{
		IType classMessageTestCase0;
			IModelElement[] moduleChilds = module.getChildren();
			classMessageTestCase0 = ModelTestUtils.getAssertClass( moduleChilds, "MessageTestCase" );
			//Function test:create_message
			{
			IMethod methodcreate_message1;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodcreate_message1 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "create_message", 2 );
				ModelTestUtils.assertParameterNames( methodcreate_message1, new String[] {"self", "msg"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get2;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_get2 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get2, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault3;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_setdefault3 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault3, new String[] {"self"} );
			}
			//Function test:check
			{
			IMethod methodcheck4;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodcheck4 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "check", 3 );
				ModelTestUtils.assertParameterNames( methodcheck4, new String[] {"self", "msg", "results"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic5;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_basic5 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic5, new String[] {"self"} );
			}
			//Function test:test_twisted
			{
			IMethod methodtest_twisted6;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_twisted6 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_twisted", 1 );
				ModelTestUtils.assertParameterNames( methodtest_twisted6, new String[] {"self"} );
			}
			//Function test:test_commas_in_full_name
			{
			IMethod methodtest_commas_in_full_name7;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_commas_in_full_name7 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_commas_in_full_name", 1 );
				ModelTestUtils.assertParameterNames( methodtest_commas_in_full_name7, new String[] {"self"} );
			}
			//Function test:test_quoted_name
			{
			IMethod methodtest_quoted_name8;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_quoted_name8 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_quoted_name", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoted_name8, new String[] {"self"} );
			}
			//Function test:test_bogus_to_header
			{
			IMethod methodtest_bogus_to_header9;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_bogus_to_header9 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_bogus_to_header", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bogus_to_header9, new String[] {"self"} );
			}
			//Function test:test_addr_ipquad
			{
			IMethod methodtest_addr_ipquad10;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_addr_ipquad10 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_addr_ipquad", 1 );
				ModelTestUtils.assertParameterNames( methodtest_addr_ipquad10, new String[] {"self"} );
			}
			//Function test:test_iter
			{
			IMethod methodtest_iter11;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_iter11 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter11, new String[] {"self"} );
			}
			//Function test:test_rfc2822_phrases
			{
			IMethod methodtest_rfc2822_phrases12;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_rfc2822_phrases12 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_rfc2822_phrases", 1 );
				ModelTestUtils.assertParameterNames( methodtest_rfc2822_phrases12, new String[] {"self"} );
			}
			//Function test:test_2getaddrlist
			{
			IMethod methodtest_2getaddrlist13;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_2getaddrlist13 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_2getaddrlist", 1 );
				ModelTestUtils.assertParameterNames( methodtest_2getaddrlist13, new String[] {"self"} );
			}
			//Function test:test_parseaddr
			{
			IMethod methodtest_parseaddr14;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_parseaddr14 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_parseaddr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_parseaddr14, new String[] {"self"} );
			}
			//Function test:test_quote_unquote
			{
			IMethod methodtest_quote_unquote15;
				IModelElement[] classMessageTestCase0Childs = classMessageTestCase0.getChildren();
				methodtest_quote_unquote15 = ModelTestUtils.getAssertMethod( classMessageTestCase0Childs, "test_quote_unquote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quote_unquote15, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main16;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main16 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen44( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_long_future.py"));

		assertNotNull("Module test_long_future.py not found", module);
		assertEquals("test_long_future.py", module.getElementName());
		
		//Function test:test_true_division
		{
		IMethod methodtest_true_division0;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_true_division0 = ModelTestUtils.getAssertMethod( moduleChilds, "test_true_division", 0 );
		}

	}
	public void testModelGen45( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_dircache.py"));

		assertNotNull("Module test_dircache.py not found", module);
		assertEquals("test_dircache.py", module.getElementName());
		
		//Class test
		{
		IType classDircacheTests0;
			IModelElement[] moduleChilds = module.getChildren();
			classDircacheTests0 = ModelTestUtils.getAssertClass( moduleChilds, "DircacheTests" );
			//Function test:setUp
			{
			IMethod methodsetUp1;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodsetUp1 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp1, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown2;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodtearDown2 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown2, new String[] {"self"} );
			}
			//Function test:writeTemp
			{
			IMethod methodwriteTemp3;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodwriteTemp3 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "writeTemp", 2 );
				ModelTestUtils.assertParameterNames( methodwriteTemp3, new String[] {"self", "fname"} );
			}
			//Function test:mkdirTemp
			{
			IMethod methodmkdirTemp4;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodmkdirTemp4 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "mkdirTemp", 2 );
				ModelTestUtils.assertParameterNames( methodmkdirTemp4, new String[] {"self", "fname"} );
			}
			//Function test:delTemp
			{
			IMethod methoddelTemp5;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methoddelTemp5 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "delTemp", 2 );
				ModelTestUtils.assertParameterNames( methoddelTemp5, new String[] {"self", "fname"} );
			}
			//Function test:test_listdir
			{
			IMethod methodtest_listdir6;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodtest_listdir6 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "test_listdir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_listdir6, new String[] {"self"} );
			}
			//Function test:test_annotate
			{
			IMethod methodtest_annotate7;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodtest_annotate7 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "test_annotate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_annotate7, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main8;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main8 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen46( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_multibytecodec.py"));

		assertNotNull("Module test_multibytecodec.py not found", module);
		assertEquals("test_multibytecodec.py", module.getElementName());
		
		//Class test
		{
		IType classTest_StreamWriter0;
			IModelElement[] moduleChilds = module.getChildren();
			classTest_StreamWriter0 = ModelTestUtils.getAssertClass( moduleChilds, "Test_StreamWriter" );
			//Function test:test_gb18030
			{
			IMethod methodtest_gb180301;
				IModelElement[] classTest_StreamWriter0Childs = classTest_StreamWriter0.getChildren();
				methodtest_gb180301 = ModelTestUtils.getAssertMethod( classTest_StreamWriter0Childs, "test_gb18030", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gb180301, new String[] {"self"} );
			}
			//Function test:test_utf_8
			{
			IMethod methodtest_utf_82;
				IModelElement[] classTest_StreamWriter0Childs = classTest_StreamWriter0.getChildren();
				methodtest_utf_82 = ModelTestUtils.getAssertMethod( classTest_StreamWriter0Childs, "test_utf_8", 1 );
				ModelTestUtils.assertParameterNames( methodtest_utf_82, new String[] {"self"} );
			}
			//Function test:test_nullcoding
			{
			IMethod methodtest_nullcoding3;
				IModelElement[] classTest_StreamWriter0Childs = classTest_StreamWriter0.getChildren();
				methodtest_nullcoding3 = ModelTestUtils.getAssertMethod( classTest_StreamWriter0Childs, "test_nullcoding", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nullcoding3, new String[] {"self"} );
			}
			//Function test:test_str_decode
			{
			IMethod methodtest_str_decode4;
				IModelElement[] classTest_StreamWriter0Childs = classTest_StreamWriter0.getChildren();
				methodtest_str_decode4 = ModelTestUtils.getAssertMethod( classTest_StreamWriter0Childs, "test_str_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_str_decode4, new String[] {"self"} );
			}
			//Function test:test_streamwriter_strwrite
			{
			IMethod methodtest_streamwriter_strwrite5;
				IModelElement[] classTest_StreamWriter0Childs = classTest_StreamWriter0.getChildren();
				methodtest_streamwriter_strwrite5 = ModelTestUtils.getAssertMethod( classTest_StreamWriter0Childs, "test_streamwriter_strwrite", 1 );
				ModelTestUtils.assertParameterNames( methodtest_streamwriter_strwrite5, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main6;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main6 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen47( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_frozen.py"));

		assertNotNull("Module test_frozen.py not found", module);
		assertEquals("test_frozen.py", module.getElementName());
		

	}
	public void testModelGen48( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("test_urllib.py"));

		assertNotNull("Module test_urllib.py not found", module);
		assertEquals("test_urllib.py", module.getElementName());
		
		//Function test:hexescape
		{
		IMethod methodhexescape0;
			IModelElement[] moduleChilds = module.getChildren();
			methodhexescape0 = ModelTestUtils.getAssertMethod( moduleChilds, "hexescape", 1 );
			ModelTestUtils.assertParameterNames( methodhexescape0, new String[] {"char"} );
		}
		//Class test
		{
		IType classurlopen_FileTests1;
			IModelElement[] moduleChilds = module.getChildren();
			classurlopen_FileTests1 = ModelTestUtils.getAssertClass( moduleChilds, "urlopen_FileTests" );
			//Function test:setUp
			{
			IMethod methodsetUp2;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodsetUp2 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp2, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:test_interface
			{
			IMethod methodtest_interface4;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_interface4 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_interface", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interface4, new String[] {"self"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read5;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_read5 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read5, new String[] {"self"} );
			}
			//Function test:test_readline
			{
			IMethod methodtest_readline6;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_readline6 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_readline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readline6, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines7;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_readlines7 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines7, new String[] {"self"} );
			}
			//Function test:test_fileno
			{
			IMethod methodtest_fileno8;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_fileno8 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_fileno", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fileno8, new String[] {"self"} );
			}
			//Function test:test_close
			{
			IMethod methodtest_close9;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_close9 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_close", 1 );
				ModelTestUtils.assertParameterNames( methodtest_close9, new String[] {"self"} );
			}
			//Function test:test_info
			{
			IMethod methodtest_info10;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_info10 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_info10, new String[] {"self"} );
			}
			//Function test:test_geturl
			{
			IMethod methodtest_geturl11;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_geturl11 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_geturl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_geturl11, new String[] {"self"} );
			}
			//Function test:test_iter
			{
			IMethod methodtest_iter12;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_iter12 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter12, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlopen_HttpTests13;
			IModelElement[] moduleChilds = module.getChildren();
			classurlopen_HttpTests13 = ModelTestUtils.getAssertClass( moduleChilds, "urlopen_HttpTests" );
			//Function test:fakehttp
			{
			IMethod methodfakehttp14;
				IModelElement[] classurlopen_HttpTests13Childs = classurlopen_HttpTests13.getChildren();
				methodfakehttp14 = ModelTestUtils.getAssertMethod( classurlopen_HttpTests13Childs, "fakehttp", 2 );
				ModelTestUtils.assertParameterNames( methodfakehttp14, new String[] {"self", "fakedata"} );
				//Class test
				{
				IType classFakeSocket15;
					IModelElement[] methodfakehttp14Childs = methodfakehttp14.getChildren();
					classFakeSocket15 = ModelTestUtils.getAssertClass( methodfakehttp14Childs, "FakeSocket" );
					//Function test:sendall
					{
					IMethod methodsendall16;
						IModelElement[] classFakeSocket15Childs = classFakeSocket15.getChildren();
						methodsendall16 = ModelTestUtils.getAssertMethod( classFakeSocket15Childs, "sendall", 2 );
						ModelTestUtils.assertParameterNames( methodsendall16, new String[] {"self", "str"} );
					}
					//Function test:makefile
					{
					IMethod methodmakefile17;
						IModelElement[] classFakeSocket15Childs = classFakeSocket15.getChildren();
						methodmakefile17 = ModelTestUtils.getAssertMethod( classFakeSocket15Childs, "makefile", 3 );
						ModelTestUtils.assertParameterNames( methodmakefile17, new String[] {"self", "mode", "name"} );
					}
					//Function test:read
					{
					IMethod methodread18;
						IModelElement[] classFakeSocket15Childs = classFakeSocket15.getChildren();
						methodread18 = ModelTestUtils.getAssertMethod( classFakeSocket15Childs, "read", 2 );
						ModelTestUtils.assertParameterNames( methodread18, new String[] {"self", "amt"} );
					}
					//Function test:readline
					{
					IMethod methodreadline19;
						IModelElement[] classFakeSocket15Childs = classFakeSocket15.getChildren();
						methodreadline19 = ModelTestUtils.getAssertMethod( classFakeSocket15Childs, "readline", 2 );
						ModelTestUtils.assertParameterNames( methodreadline19, new String[] {"self", "length"} );
					}
				}
				//Class test
				{
				IType classFakeHTTPConnection20;
					IModelElement[] methodfakehttp14Childs = methodfakehttp14.getChildren();
					classFakeHTTPConnection20 = ModelTestUtils.getAssertClass( methodfakehttp14Childs, "FakeHTTPConnection" );
					//Function test:connect
					{
					IMethod methodconnect21;
						IModelElement[] classFakeHTTPConnection20Childs = classFakeHTTPConnection20.getChildren();
						methodconnect21 = ModelTestUtils.getAssertMethod( classFakeHTTPConnection20Childs, "connect", 1 );
						ModelTestUtils.assertParameterNames( methodconnect21, new String[] {"self"} );
					}
				}
			}
			//Function test:unfakehttp
			{
			IMethod methodunfakehttp22;
				IModelElement[] classurlopen_HttpTests13Childs = classurlopen_HttpTests13.getChildren();
				methodunfakehttp22 = ModelTestUtils.getAssertMethod( classurlopen_HttpTests13Childs, "unfakehttp", 1 );
				ModelTestUtils.assertParameterNames( methodunfakehttp22, new String[] {"self"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read23;
				IModelElement[] classurlopen_HttpTests13Childs = classurlopen_HttpTests13.getChildren();
				methodtest_read23 = ModelTestUtils.getAssertMethod( classurlopen_HttpTests13Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read23, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlretrieve_FileTests24;
			IModelElement[] moduleChilds = module.getChildren();
			classurlretrieve_FileTests24 = ModelTestUtils.getAssertClass( moduleChilds, "urlretrieve_FileTests" );
			//Function test:setUp
			{
			IMethod methodsetUp25;
				IModelElement[] classurlretrieve_FileTests24Childs = classurlretrieve_FileTests24.getChildren();
				methodsetUp25 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests24Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp25, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown26;
				IModelElement[] classurlretrieve_FileTests24Childs = classurlretrieve_FileTests24.getChildren();
				methodtearDown26 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests24Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown26, new String[] {"self"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic27;
				IModelElement[] classurlretrieve_FileTests24Childs = classurlretrieve_FileTests24.getChildren();
				methodtest_basic27 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests24Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic27, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy28;
				IModelElement[] classurlretrieve_FileTests24Childs = classurlretrieve_FileTests24.getChildren();
				methodtest_copy28 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests24Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy28, new String[] {"self"} );
			}
			//Function test:test_reporthook
			{
			IMethod methodtest_reporthook29;
				IModelElement[] classurlretrieve_FileTests24Childs = classurlretrieve_FileTests24.getChildren();
				methodtest_reporthook29 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests24Childs, "test_reporthook", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reporthook29, new String[] {"self"} );
				//Function test:hooktester
				{
				IMethod methodhooktester30;
					IModelElement[] methodtest_reporthook29Childs = methodtest_reporthook29.getChildren();
					methodhooktester30 = ModelTestUtils.getAssertMethod( methodtest_reporthook29Childs, "hooktester", 4 );
					ModelTestUtils.assertParameterNames( methodhooktester30, new String[] {"count", "block_size", "total_size", "count_holder"} );
				}
			}
		}
		//Class test
		{
		IType classQuotingTests31;
			IModelElement[] moduleChilds = module.getChildren();
			classQuotingTests31 = ModelTestUtils.getAssertClass( moduleChilds, "QuotingTests" );
			//Function test:test_never_quote
			{
			IMethod methodtest_never_quote32;
				IModelElement[] classQuotingTests31Childs = classQuotingTests31.getChildren();
				methodtest_never_quote32 = ModelTestUtils.getAssertMethod( classQuotingTests31Childs, "test_never_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_never_quote32, new String[] {"self"} );
			}
			//Function test:test_default_safe
			{
			IMethod methodtest_default_safe33;
				IModelElement[] classQuotingTests31Childs = classQuotingTests31.getChildren();
				methodtest_default_safe33 = ModelTestUtils.getAssertMethod( classQuotingTests31Childs, "test_default_safe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_safe33, new String[] {"self"} );
			}
			//Function test:test_safe
			{
			IMethod methodtest_safe34;
				IModelElement[] classQuotingTests31Childs = classQuotingTests31.getChildren();
				methodtest_safe34 = ModelTestUtils.getAssertMethod( classQuotingTests31Childs, "test_safe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_safe34, new String[] {"self"} );
			}
			//Function test:test_default_quoting
			{
			IMethod methodtest_default_quoting35;
				IModelElement[] classQuotingTests31Childs = classQuotingTests31.getChildren();
				methodtest_default_quoting35 = ModelTestUtils.getAssertMethod( classQuotingTests31Childs, "test_default_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_quoting35, new String[] {"self"} );
			}
			//Function test:test_quoting_space
			{
			IMethod methodtest_quoting_space36;
				IModelElement[] classQuotingTests31Childs = classQuotingTests31.getChildren();
				methodtest_quoting_space36 = ModelTestUtils.getAssertMethod( classQuotingTests31Childs, "test_quoting_space", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting_space36, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnquotingTests37;
			IModelElement[] moduleChilds = module.getChildren();
			classUnquotingTests37 = ModelTestUtils.getAssertClass( moduleChilds, "UnquotingTests" );
			//Function test:test_unquoting
			{
			IMethod methodtest_unquoting38;
				IModelElement[] classUnquotingTests37Childs = classUnquotingTests37.getChildren();
				methodtest_unquoting38 = ModelTestUtils.getAssertMethod( classUnquotingTests37Childs, "test_unquoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unquoting38, new String[] {"self"} );
			}
			//Function test:test_unquoting_parts
			{
			IMethod methodtest_unquoting_parts39;
				IModelElement[] classUnquotingTests37Childs = classUnquotingTests37.getChildren();
				methodtest_unquoting_parts39 = ModelTestUtils.getAssertMethod( classUnquotingTests37Childs, "test_unquoting_parts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unquoting_parts39, new String[] {"self"} );
			}
			//Function test:test_unquoting_plus
			{
			IMethod methodtest_unquoting_plus40;
				IModelElement[] classUnquotingTests37Childs = classUnquotingTests37.getChildren();
				methodtest_unquoting_plus40 = ModelTestUtils.getAssertMethod( classUnquotingTests37Childs, "test_unquoting_plus", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unquoting_plus40, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlencode_Tests41;
			IModelElement[] moduleChilds = module.getChildren();
			classurlencode_Tests41 = ModelTestUtils.getAssertClass( moduleChilds, "urlencode_Tests" );
			//Function test:help_inputtype
			{
			IMethod methodhelp_inputtype42;
				IModelElement[] classurlencode_Tests41Childs = classurlencode_Tests41.getChildren();
				methodhelp_inputtype42 = ModelTestUtils.getAssertMethod( classurlencode_Tests41Childs, "help_inputtype", 3 );
				ModelTestUtils.assertParameterNames( methodhelp_inputtype42, new String[] {"self", "given", "test_type"} );
			}
			//Function test:test_using_mapping
			{
			IMethod methodtest_using_mapping43;
				IModelElement[] classurlencode_Tests41Childs = classurlencode_Tests41.getChildren();
				methodtest_using_mapping43 = ModelTestUtils.getAssertMethod( classurlencode_Tests41Childs, "test_using_mapping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_using_mapping43, new String[] {"self"} );
			}
			//Function test:test_using_sequence
			{
			IMethod methodtest_using_sequence44;
				IModelElement[] classurlencode_Tests41Childs = classurlencode_Tests41.getChildren();
				methodtest_using_sequence44 = ModelTestUtils.getAssertMethod( classurlencode_Tests41Childs, "test_using_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_using_sequence44, new String[] {"self"} );
			}
			//Function test:test_quoting
			{
			IMethod methodtest_quoting45;
				IModelElement[] classurlencode_Tests41Childs = classurlencode_Tests41.getChildren();
				methodtest_quoting45 = ModelTestUtils.getAssertMethod( classurlencode_Tests41Childs, "test_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting45, new String[] {"self"} );
			}
			//Function test:test_doseq
			{
			IMethod methodtest_doseq46;
				IModelElement[] classurlencode_Tests41Childs = classurlencode_Tests41.getChildren();
				methodtest_doseq46 = ModelTestUtils.getAssertMethod( classurlencode_Tests41Childs, "test_doseq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doseq46, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPathname_Tests47;
			IModelElement[] moduleChilds = module.getChildren();
			classPathname_Tests47 = ModelTestUtils.getAssertClass( moduleChilds, "Pathname_Tests" );
			//Function test:test_basic
			{
			IMethod methodtest_basic48;
				IModelElement[] classPathname_Tests47Childs = classPathname_Tests47.getChildren();
				methodtest_basic48 = ModelTestUtils.getAssertMethod( classPathname_Tests47Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic48, new String[] {"self"} );
			}
			//Function test:test_quoting
			{
			IMethod methodtest_quoting49;
				IModelElement[] classPathname_Tests47Childs = classPathname_Tests47.getChildren();
				methodtest_quoting49 = ModelTestUtils.getAssertMethod( classPathname_Tests47Childs, "test_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting49, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main50;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main50 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void REM_testModelGen49( ) throws Exception {
		String prj = "pytests_0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path("mapping_tests.py"));

		assertNotNull("Module mapping_tests.py not found", module);
		assertEquals("mapping_tests.py", module.getElementName());
		
		//Class test
		{
		IType classBasicTestMappingProtocol0;
			IModelElement[] moduleChilds = module.getChildren();
			classBasicTestMappingProtocol0 = ModelTestUtils.getAssertClass( moduleChilds, "BasicTestMappingProtocol" );
			{
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicTestMappingProtocol0Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference1;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				method_reference1 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference1, new String[] {"self"} );
			}
			//Function test:_empty_mapping
			{
			IMethod method_empty_mapping2;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				method_empty_mapping2 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "_empty_mapping", 1 );
				ModelTestUtils.assertParameterNames( method_empty_mapping2, new String[] {"self"} );
			}
			//Function test:_full_mapping
			{
			IMethod method_full_mapping3;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				method_full_mapping3 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "_full_mapping", 2 );
				ModelTestUtils.assertParameterNames( method_full_mapping3, new String[] {"self", "data"} );
			}
			//Function test:__init__
			{
			IMethod method__init__4;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				method__init__4 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__4, new String[] {"self", "args", "kw"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read5;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_read5 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read5, new String[] {"self"} );
				//Function test:check_iterandlist
				{
				IMethod methodcheck_iterandlist6;
					IModelElement[] methodtest_read5Childs = methodtest_read5.getChildren();
					methodcheck_iterandlist6 = ModelTestUtils.getAssertMethod( methodtest_read5Childs, "check_iterandlist", 3 );
					ModelTestUtils.assertParameterNames( methodcheck_iterandlist6, new String[] {"iter", "lst", "ref"} );
				}
			}
			//Function test:test_write
			{
			IMethod methodtest_write7;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_write7 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write7, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor8;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_constructor8 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor8, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool9;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_bool9 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool9, new String[] {"self"} );
			}
			//Function test:test_keys
			{
			IMethod methodtest_keys10;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_keys10 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keys10, new String[] {"self"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values11;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_values11 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values11, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items12;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_items12 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items12, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len13;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_len13 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len13, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem14;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_getitem14 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem14, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update15;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_update15 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update15, new String[] {"self"} );
				//Class test
				{
				IType classSimpleUserDict16;
					IModelElement[] methodtest_update15Childs = methodtest_update15.getChildren();
					classSimpleUserDict16 = ModelTestUtils.getAssertClass( methodtest_update15Childs, "SimpleUserDict" );
					//Function test:__init__
					{
					IMethod method__init__17;
						IModelElement[] classSimpleUserDict16Childs = classSimpleUserDict16.getChildren();
						method__init__17 = ModelTestUtils.getAssertMethod( classSimpleUserDict16Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__17, new String[] {"self"} );
					}
					//Function test:keys
					{
					IMethod methodkeys18;
						IModelElement[] classSimpleUserDict16Childs = classSimpleUserDict16.getChildren();
						methodkeys18 = ModelTestUtils.getAssertMethod( classSimpleUserDict16Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys18, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__19;
						IModelElement[] classSimpleUserDict16Childs = classSimpleUserDict16.getChildren();
						method__getitem__19 = ModelTestUtils.getAssertMethod( classSimpleUserDict16Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__19, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classExc20;
					IModelElement[] methodtest_update15Childs = methodtest_update15.getChildren();
					classExc20 = ModelTestUtils.getAssertClass( methodtest_update15Childs, "Exc" );
				}
				//Class test
				{
				IType classFailingUserDict21;
					IModelElement[] methodtest_update15Childs = methodtest_update15.getChildren();
					classFailingUserDict21 = ModelTestUtils.getAssertClass( methodtest_update15Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys22;
						IModelElement[] classFailingUserDict21Childs = classFailingUserDict21.getChildren();
						methodkeys22 = ModelTestUtils.getAssertMethod( classFailingUserDict21Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys22, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict23;
					IModelElement[] methodtest_update15Childs = methodtest_update15.getChildren();
					classFailingUserDict23 = ModelTestUtils.getAssertClass( methodtest_update15Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys24;
						IModelElement[] classFailingUserDict23Childs = classFailingUserDict23.getChildren();
						methodkeys24 = ModelTestUtils.getAssertMethod( classFailingUserDict23Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys24, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter25;
							IModelElement[] methodkeys24Childs = methodkeys24.getChildren();
							classBogonIter25 = ModelTestUtils.getAssertClass( methodkeys24Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__26;
								IModelElement[] classBogonIter25Childs = classBogonIter25.getChildren();
								method__init__26 = ModelTestUtils.getAssertMethod( classBogonIter25Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__26, new String[] {"self"} );
							}
							//Function test:__iter__
							{
							IMethod method__iter__27;
								IModelElement[] classBogonIter25Childs = classBogonIter25.getChildren();
								method__iter__27 = ModelTestUtils.getAssertMethod( classBogonIter25Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__27, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext28;
								IModelElement[] classBogonIter25Childs = classBogonIter25.getChildren();
								methodnext28 = ModelTestUtils.getAssertMethod( classBogonIter25Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext28, new String[] {"self"} );
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__29;
						IModelElement[] classFailingUserDict23Childs = classFailingUserDict23.getChildren();
						method__getitem__29 = ModelTestUtils.getAssertMethod( classFailingUserDict23Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__29, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict30;
					IModelElement[] methodtest_update15Childs = methodtest_update15.getChildren();
					classFailingUserDict30 = ModelTestUtils.getAssertClass( methodtest_update15Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys31;
						IModelElement[] classFailingUserDict30Childs = classFailingUserDict30.getChildren();
						methodkeys31 = ModelTestUtils.getAssertMethod( classFailingUserDict30Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys31, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter32;
							IModelElement[] methodkeys31Childs = methodkeys31.getChildren();
							classBogonIter32 = ModelTestUtils.getAssertClass( methodkeys31Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__33;
								IModelElement[] classBogonIter32Childs = classBogonIter32.getChildren();
								method__init__33 = ModelTestUtils.getAssertMethod( classBogonIter32Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__33, new String[] {"self"} );
							}
							//Function test:__iter__
							{
							IMethod method__iter__34;
								IModelElement[] classBogonIter32Childs = classBogonIter32.getChildren();
								method__iter__34 = ModelTestUtils.getAssertMethod( classBogonIter32Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__34, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext35;
								IModelElement[] classBogonIter32Childs = classBogonIter32.getChildren();
								methodnext35 = ModelTestUtils.getAssertMethod( classBogonIter32Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext35, new String[] {"self"} );
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__36;
						IModelElement[] classFailingUserDict30Childs = classFailingUserDict30.getChildren();
						method__getitem__36 = ModelTestUtils.getAssertMethod( classFailingUserDict30Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__36, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classbadseq37;
					IModelElement[] methodtest_update15Childs = methodtest_update15.getChildren();
					classbadseq37 = ModelTestUtils.getAssertClass( methodtest_update15Childs, "badseq" );
					//Function test:__iter__
					{
					IMethod method__iter__38;
						IModelElement[] classbadseq37Childs = classbadseq37.getChildren();
						method__iter__38 = ModelTestUtils.getAssertMethod( classbadseq37Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__38, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext39;
						IModelElement[] classbadseq37Childs = classbadseq37.getChildren();
						methodnext39 = ModelTestUtils.getAssertMethod( classbadseq37Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext39, new String[] {"self"} );
					}
				}
			}
			//Function test:test_get
			{
			IMethod methodtest_get40;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_get40 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get40, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault41;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_setdefault41 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault41, new String[] {"self"} );
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem42;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_popitem42 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem42, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop43;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_pop43 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop43, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMappingProtocol44;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMappingProtocol44 = ModelTestUtils.getAssertClass( moduleChilds, "TestMappingProtocol" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor45;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_constructor45 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor45, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool46;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_bool46 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool46, new String[] {"self"} );
			}
			//Function test:test_keys
			{
			IMethod methodtest_keys47;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_keys47 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keys47, new String[] {"self"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values48;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_values48 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values48, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items49;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_items49 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items49, new String[] {"self"} );
			}
			//Function test:test_has_key
			{
			IMethod methodtest_has_key50;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_has_key50 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_has_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_key50, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains51;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_contains51 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains51, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len52;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_len52 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len52, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem53;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_getitem53 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem53, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear54;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_clear54 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear54, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update55;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_update55 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update55, new String[] {"self"} );
				//Class test
				{
				IType classSimpleUserDict56;
					IModelElement[] methodtest_update55Childs = methodtest_update55.getChildren();
					classSimpleUserDict56 = ModelTestUtils.getAssertClass( methodtest_update55Childs, "SimpleUserDict" );
					//Function test:__init__
					{
					IMethod method__init__57;
						IModelElement[] classSimpleUserDict56Childs = classSimpleUserDict56.getChildren();
						method__init__57 = ModelTestUtils.getAssertMethod( classSimpleUserDict56Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__57, new String[] {"self"} );
					}
					//Function test:keys
					{
					IMethod methodkeys58;
						IModelElement[] classSimpleUserDict56Childs = classSimpleUserDict56.getChildren();
						methodkeys58 = ModelTestUtils.getAssertMethod( classSimpleUserDict56Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys58, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__59;
						IModelElement[] classSimpleUserDict56Childs = classSimpleUserDict56.getChildren();
						method__getitem__59 = ModelTestUtils.getAssertMethod( classSimpleUserDict56Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__59, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_fromkeys
			{
			IMethod methodtest_fromkeys60;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_fromkeys60 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_fromkeys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromkeys60, new String[] {"self"} );
				//Function test:g
				{
				IMethod methodg61;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					methodg61 = ModelTestUtils.getAssertMethod( methodtest_fromkeys60Childs, "g", 0 );
				}
				//Class test
				{
				IType classdictlike62;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					classdictlike62 = ModelTestUtils.getAssertClass( methodtest_fromkeys60Childs, "dictlike" );
				}
				//Class test
				{
				IType classmydict63;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					classmydict63 = ModelTestUtils.getAssertClass( methodtest_fromkeys60Childs, "mydict" );
					//Function test:__new__
					{
					IMethod method__new__64;
						IModelElement[] classmydict63Childs = classmydict63.getChildren();
						method__new__64 = ModelTestUtils.getAssertMethod( classmydict63Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__64, new String[] {"cls"} );
					}
				}
				//Class test
				{
				IType classExc65;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					classExc65 = ModelTestUtils.getAssertClass( methodtest_fromkeys60Childs, "Exc" );
				}
				//Class test
				{
				IType classbaddict166;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					classbaddict166 = ModelTestUtils.getAssertClass( methodtest_fromkeys60Childs, "baddict1" );
					//Function test:__init__
					{
					IMethod method__init__67;
						IModelElement[] classbaddict166Childs = classbaddict166.getChildren();
						method__init__67 = ModelTestUtils.getAssertMethod( classbaddict166Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__67, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBadSeq68;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					classBadSeq68 = ModelTestUtils.getAssertClass( methodtest_fromkeys60Childs, "BadSeq" );
					//Function test:__iter__
					{
					IMethod method__iter__69;
						IModelElement[] classBadSeq68Childs = classBadSeq68.getChildren();
						method__iter__69 = ModelTestUtils.getAssertMethod( classBadSeq68Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__69, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext70;
						IModelElement[] classBadSeq68Childs = classBadSeq68.getChildren();
						methodnext70 = ModelTestUtils.getAssertMethod( classBadSeq68Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext70, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classbaddict271;
					IModelElement[] methodtest_fromkeys60Childs = methodtest_fromkeys60.getChildren();
					classbaddict271 = ModelTestUtils.getAssertClass( methodtest_fromkeys60Childs, "baddict2" );
					//Function test:__setitem__
					{
					IMethod method__setitem__72;
						IModelElement[] classbaddict271Childs = classbaddict271.getChildren();
						method__setitem__72 = ModelTestUtils.getAssertMethod( classbaddict271Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__72, new String[] {"self", "key", "value"} );
					}
				}
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy73;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_copy73 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy73, new String[] {"self"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get74;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_get74 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get74, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault75;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_setdefault75 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault75, new String[] {"self"} );
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem76;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_popitem76 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem76, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop77;
				IModelElement[] classTestMappingProtocol44Childs = classTestMappingProtocol44.getChildren();
				methodtest_pop77 = ModelTestUtils.getAssertMethod( classTestMappingProtocol44Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop77, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestHashMappingProtocol78;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHashMappingProtocol78 = ModelTestUtils.getAssertClass( moduleChilds, "TestHashMappingProtocol" );
			//Function test:test_getitem
			{
			IMethod methodtest_getitem79;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_getitem79 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem79, new String[] {"self"} );
				//Class test
				{
				IType classExc80;
					IModelElement[] methodtest_getitem79Childs = methodtest_getitem79.getChildren();
					classExc80 = ModelTestUtils.getAssertClass( methodtest_getitem79Childs, "Exc" );
				}
				//Class test
				{
				IType classBadEq81;
					IModelElement[] methodtest_getitem79Childs = methodtest_getitem79.getChildren();
					classBadEq81 = ModelTestUtils.getAssertClass( methodtest_getitem79Childs, "BadEq" );
					//Function test:__eq__
					{
					IMethod method__eq__82;
						IModelElement[] classBadEq81Childs = classBadEq81.getChildren();
						method__eq__82 = ModelTestUtils.getAssertMethod( classBadEq81Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__82, new String[] {"self", "other"} );
					}
				}
				//Class test
				{
				IType classBadHash83;
					IModelElement[] methodtest_getitem79Childs = methodtest_getitem79.getChildren();
					classBadHash83 = ModelTestUtils.getAssertClass( methodtest_getitem79Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__84;
						IModelElement[] classBadHash83Childs = classBadHash83.getChildren();
						method__hash__84 = ModelTestUtils.getAssertMethod( classBadHash83Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__84, new String[] {"self"} );
					}
				}
			}
			//Function test:test_fromkeys
			{
			IMethod methodtest_fromkeys85;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_fromkeys85 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_fromkeys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromkeys85, new String[] {"self"} );
				//Class test
				{
				IType classmydict86;
					IModelElement[] methodtest_fromkeys85Childs = methodtest_fromkeys85.getChildren();
					classmydict86 = ModelTestUtils.getAssertClass( methodtest_fromkeys85Childs, "mydict" );
					//Function test:__new__
					{
					IMethod method__new__87;
						IModelElement[] classmydict86Childs = classmydict86.getChildren();
						method__new__87 = ModelTestUtils.getAssertMethod( classmydict86Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__87, new String[] {"cls"} );
					}
				}
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop88;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_pop88 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop88, new String[] {"self"} );
				//Class test
				{
				IType classExc89;
					IModelElement[] methodtest_pop88Childs = methodtest_pop88.getChildren();
					classExc89 = ModelTestUtils.getAssertClass( methodtest_pop88Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash90;
					IModelElement[] methodtest_pop88Childs = methodtest_pop88.getChildren();
					classBadHash90 = ModelTestUtils.getAssertClass( methodtest_pop88Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__91;
						IModelElement[] classBadHash90Childs = classBadHash90.getChildren();
						method__hash__91 = ModelTestUtils.getAssertMethod( classBadHash90Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__91, new String[] {"self"} );
					}
				}
			}
			//Function test:test_mutatingiteration
			{
			IMethod methodtest_mutatingiteration92;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_mutatingiteration92 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_mutatingiteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutatingiteration92, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr93;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_repr93 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr93, new String[] {"self"} );
				//Class test
				{
				IType classExc94;
					IModelElement[] methodtest_repr93Childs = methodtest_repr93.getChildren();
					classExc94 = ModelTestUtils.getAssertClass( methodtest_repr93Childs, "Exc" );
				}
				//Class test
				{
				IType classBadRepr95;
					IModelElement[] methodtest_repr93Childs = methodtest_repr93.getChildren();
					classBadRepr95 = ModelTestUtils.getAssertClass( methodtest_repr93Childs, "BadRepr" );
					//Function test:__repr__
					{
					IMethod method__repr__96;
						IModelElement[] classBadRepr95Childs = classBadRepr95.getChildren();
						method__repr__96 = ModelTestUtils.getAssertMethod( classBadRepr95Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__96, new String[] {"self"} );
					}
				}
			}
			//Function test:test_le
			{
			IMethod methodtest_le97;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_le97 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_le", 1 );
				ModelTestUtils.assertParameterNames( methodtest_le97, new String[] {"self"} );
				//Class test
				{
				IType classExc98;
					IModelElement[] methodtest_le97Childs = methodtest_le97.getChildren();
					classExc98 = ModelTestUtils.getAssertClass( methodtest_le97Childs, "Exc" );
				}
				//Class test
				{
				IType classBadCmp99;
					IModelElement[] methodtest_le97Childs = methodtest_le97.getChildren();
					classBadCmp99 = ModelTestUtils.getAssertClass( methodtest_le97Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__100;
						IModelElement[] classBadCmp99Childs = classBadCmp99.getChildren();
						method__eq__100 = ModelTestUtils.getAssertMethod( classBadCmp99Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__100, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault101;
				IModelElement[] classTestHashMappingProtocol78Childs = classTestHashMappingProtocol78.getChildren();
				methodtest_setdefault101 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol78Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault101, new String[] {"self"} );
				//Class test
				{
				IType classExc102;
					IModelElement[] methodtest_setdefault101Childs = methodtest_setdefault101.getChildren();
					classExc102 = ModelTestUtils.getAssertClass( methodtest_setdefault101Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash103;
					IModelElement[] methodtest_setdefault101Childs = methodtest_setdefault101.getChildren();
					classBadHash103 = ModelTestUtils.getAssertClass( methodtest_setdefault101Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__104;
						IModelElement[] classBadHash103Childs = classBadHash103.getChildren();
						method__hash__104 = ModelTestUtils.getAssertMethod( classBadHash103Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__104, new String[] {"self"} );
					}
				}
			}
		}

	}

}
	