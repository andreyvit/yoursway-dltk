
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
			{
				IModelElement[] classServerThread10Childs = classServerThread10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classServerThread10Childs, "__addr");
			}
			{
				IModelElement[] classServerThread10Childs = classServerThread10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classServerThread10Childs, "__svrcls");
			}
			{
				IModelElement[] classServerThread10Childs = classServerThread10.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classServerThread10Childs, "__hdlrcls");
			}
			//Function test:run
			{
			IMethod methodrun13;
				IModelElement[] classServerThread10Childs = classServerThread10.getChildren();
				methodrun13 = ModelTestUtils.getAssertMethod( classServerThread10Childs, "run", 1 );
				ModelTestUtils.assertParameterNames( methodrun13, new String[] {"self"} );
				//Class test
				{
				IType classsvrcls14;
					IModelElement[] methodrun13Childs = methodrun13.getChildren();
					classsvrcls14 = ModelTestUtils.getAssertClass( methodrun13Childs, "svrcls" );
				}
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "seed");
		}
		//Function test:pickport
		{
		IMethod methodpickport15;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickport15 = ModelTestUtils.getAssertMethod( moduleChilds, "pickport", 0 );
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
		IMethod methodpickaddr16;
			IModelElement[] moduleChilds = module.getChildren();
			methodpickaddr16 = ModelTestUtils.getAssertMethod( moduleChilds, "pickaddr", 1 );
			ModelTestUtils.assertParameterNames( methodpickaddr16, new String[] {"proto"} );
		}
		//Function test:cleanup
		{
		IMethod methodcleanup17;
			IModelElement[] moduleChilds = module.getChildren();
			methodcleanup17 = ModelTestUtils.getAssertMethod( moduleChilds, "cleanup", 0 );
		}
		//Function test:testloop
		{
		IMethod methodtestloop18;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestloop18 = ModelTestUtils.getAssertMethod( moduleChilds, "testloop", 4 );
			ModelTestUtils.assertParameterNames( methodtestloop18, new String[] {"proto", "servers", "hdlrcls", "testfunc"} );
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
		IType classForkingUnixStreamServer19;
			IModelElement[] moduleChilds = module.getChildren();
			classForkingUnixStreamServer19 = ModelTestUtils.getAssertClass( moduleChilds, "ForkingUnixStreamServer" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "streamservers");
		}
		//Class test
		{
		IType classForkingUnixDatagramServer20;
			IModelElement[] moduleChilds = module.getChildren();
			classForkingUnixDatagramServer20 = ModelTestUtils.getAssertClass( moduleChilds, "ForkingUnixDatagramServer" );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "dgramservers");
		}
		//Function test:testall
		{
		IMethod methodtestall21;
			IModelElement[] moduleChilds = module.getChildren();
			methodtestall21 = ModelTestUtils.getAssertMethod( moduleChilds, "testall", 0 );
		}
		//Function test:test_main
		{
		IMethod methodtest_main22;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main22 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen1( ) throws Exception {
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
					{
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classSimpleUserDict17Childs, "d");
					}
					//Function test:keys
					{
					IMethod methodkeys20;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						methodkeys20 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys20, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__21;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						method__getitem__21 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__21, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classExc22;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classExc22 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "Exc" );
				}
				//Class test
				{
				IType classFailingUserDict23;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict23 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys24;
						IModelElement[] classFailingUserDict23Childs = classFailingUserDict23.getChildren();
						methodkeys24 = ModelTestUtils.getAssertMethod( classFailingUserDict23Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys24, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict25;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict25 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys26;
						IModelElement[] classFailingUserDict25Childs = classFailingUserDict25.getChildren();
						methodkeys26 = ModelTestUtils.getAssertMethod( classFailingUserDict25Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys26, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter27;
							IModelElement[] methodkeys26Childs = methodkeys26.getChildren();
							classBogonIter27 = ModelTestUtils.getAssertClass( methodkeys26Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__28;
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								method__init__28 = ModelTestUtils.getAssertMethod( classBogonIter27Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__28, new String[] {"self"} );
							}
							{
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classBogonIter27Childs, "i");
							}
							//Function test:__iter__
							{
							IMethod method__iter__30;
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								method__iter__30 = ModelTestUtils.getAssertMethod( classBogonIter27Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__30, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext31;
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								methodnext31 = ModelTestUtils.getAssertMethod( classBogonIter27Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext31, new String[] {"self"} );
							}
							{
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classBogonIter27Childs, "i");
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__33;
						IModelElement[] classFailingUserDict25Childs = classFailingUserDict25.getChildren();
						method__getitem__33 = ModelTestUtils.getAssertMethod( classFailingUserDict25Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__33, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict34;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict34 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys35;
						IModelElement[] classFailingUserDict34Childs = classFailingUserDict34.getChildren();
						methodkeys35 = ModelTestUtils.getAssertMethod( classFailingUserDict34Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys35, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter36;
							IModelElement[] methodkeys35Childs = methodkeys35.getChildren();
							classBogonIter36 = ModelTestUtils.getAssertClass( methodkeys35Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__37;
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								method__init__37 = ModelTestUtils.getAssertMethod( classBogonIter36Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__37, new String[] {"self"} );
							}
							{
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classBogonIter36Childs, "i");
							}
							//Function test:__iter__
							{
							IMethod method__iter__39;
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								method__iter__39 = ModelTestUtils.getAssertMethod( classBogonIter36Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__39, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext40;
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								methodnext40 = ModelTestUtils.getAssertMethod( classBogonIter36Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext40, new String[] {"self"} );
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__41;
						IModelElement[] classFailingUserDict34Childs = classFailingUserDict34.getChildren();
						method__getitem__41 = ModelTestUtils.getAssertMethod( classFailingUserDict34Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__41, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classbadseq42;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classbadseq42 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "badseq" );
					//Function test:__iter__
					{
					IMethod method__iter__43;
						IModelElement[] classbadseq42Childs = classbadseq42.getChildren();
						method__iter__43 = ModelTestUtils.getAssertMethod( classbadseq42Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__43, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext44;
						IModelElement[] classbadseq42Childs = classbadseq42.getChildren();
						methodnext44 = ModelTestUtils.getAssertMethod( classbadseq42Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext44, new String[] {"self"} );
					}
				}
			}
			//Function test:test_fromkeys
			{
			IMethod methodtest_fromkeys45;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_fromkeys45 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_fromkeys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromkeys45, new String[] {"self"} );
				//Function test:g
				{
				IMethod methodg46;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					methodg46 = ModelTestUtils.getAssertMethod( methodtest_fromkeys45Childs, "g", 0 );
				}
				//Class test
				{
				IType classdictlike47;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					classdictlike47 = ModelTestUtils.getAssertClass( methodtest_fromkeys45Childs, "dictlike" );
				}
				//Class test
				{
				IType classmydict48;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					classmydict48 = ModelTestUtils.getAssertClass( methodtest_fromkeys45Childs, "mydict" );
					//Function test:__new__
					{
					IMethod method__new__49;
						IModelElement[] classmydict48Childs = classmydict48.getChildren();
						method__new__49 = ModelTestUtils.getAssertMethod( classmydict48Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__49, new String[] {"cls"} );
					}
				}
				//Class test
				{
				IType classExc50;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					classExc50 = ModelTestUtils.getAssertClass( methodtest_fromkeys45Childs, "Exc" );
				}
				//Class test
				{
				IType classbaddict151;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					classbaddict151 = ModelTestUtils.getAssertClass( methodtest_fromkeys45Childs, "baddict1" );
					//Function test:__init__
					{
					IMethod method__init__52;
						IModelElement[] classbaddict151Childs = classbaddict151.getChildren();
						method__init__52 = ModelTestUtils.getAssertMethod( classbaddict151Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__52, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBadSeq53;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					classBadSeq53 = ModelTestUtils.getAssertClass( methodtest_fromkeys45Childs, "BadSeq" );
					//Function test:__iter__
					{
					IMethod method__iter__54;
						IModelElement[] classBadSeq53Childs = classBadSeq53.getChildren();
						method__iter__54 = ModelTestUtils.getAssertMethod( classBadSeq53Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__54, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext55;
						IModelElement[] classBadSeq53Childs = classBadSeq53.getChildren();
						methodnext55 = ModelTestUtils.getAssertMethod( classBadSeq53Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext55, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classbaddict256;
					IModelElement[] methodtest_fromkeys45Childs = methodtest_fromkeys45.getChildren();
					classbaddict256 = ModelTestUtils.getAssertClass( methodtest_fromkeys45Childs, "baddict2" );
					//Function test:__setitem__
					{
					IMethod method__setitem__57;
						IModelElement[] classbaddict256Childs = classbaddict256.getChildren();
						method__setitem__57 = ModelTestUtils.getAssertMethod( classbaddict256Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__57, new String[] {"self", "key", "value"} );
					}
				}
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy58;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_copy58 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy58, new String[] {"self"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get59;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_get59 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get59, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault60;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_setdefault60 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault60, new String[] {"self"} );
				//Class test
				{
				IType classExc61;
					IModelElement[] methodtest_setdefault60Childs = methodtest_setdefault60.getChildren();
					classExc61 = ModelTestUtils.getAssertClass( methodtest_setdefault60Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash62;
					IModelElement[] methodtest_setdefault60Childs = methodtest_setdefault60.getChildren();
					classBadHash62 = ModelTestUtils.getAssertClass( methodtest_setdefault60Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__63;
						IModelElement[] classBadHash62Childs = classBadHash62.getChildren();
						method__hash__63 = ModelTestUtils.getAssertMethod( classBadHash62Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__63, new String[] {"self"} );
					}
				}
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem64;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_popitem64 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem64, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop65;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_pop65 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop65, new String[] {"self"} );
				//Class test
				{
				IType classExc66;
					IModelElement[] methodtest_pop65Childs = methodtest_pop65.getChildren();
					classExc66 = ModelTestUtils.getAssertClass( methodtest_pop65Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash67;
					IModelElement[] methodtest_pop65Childs = methodtest_pop65.getChildren();
					classBadHash67 = ModelTestUtils.getAssertClass( methodtest_pop65Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__68;
						IModelElement[] classBadHash67Childs = classBadHash67.getChildren();
						method__hash__68 = ModelTestUtils.getAssertMethod( classBadHash67Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__68, new String[] {"self"} );
					}
				}
			}
			//Function test:test_mutatingiteration
			{
			IMethod methodtest_mutatingiteration69;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_mutatingiteration69 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_mutatingiteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutatingiteration69, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr70;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_repr70 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr70, new String[] {"self"} );
				//Class test
				{
				IType classExc71;
					IModelElement[] methodtest_repr70Childs = methodtest_repr70.getChildren();
					classExc71 = ModelTestUtils.getAssertClass( methodtest_repr70Childs, "Exc" );
				}
				//Class test
				{
				IType classBadRepr72;
					IModelElement[] methodtest_repr70Childs = methodtest_repr70.getChildren();
					classBadRepr72 = ModelTestUtils.getAssertClass( methodtest_repr70Childs, "BadRepr" );
					//Function test:__repr__
					{
					IMethod method__repr__73;
						IModelElement[] classBadRepr72Childs = classBadRepr72.getChildren();
						method__repr__73 = ModelTestUtils.getAssertMethod( classBadRepr72Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__73, new String[] {"self"} );
					}
				}
			}
			//Function test:test_le
			{
			IMethod methodtest_le74;
				IModelElement[] classDictTest0Childs = classDictTest0.getChildren();
				methodtest_le74 = ModelTestUtils.getAssertMethod( classDictTest0Childs, "test_le", 1 );
				ModelTestUtils.assertParameterNames( methodtest_le74, new String[] {"self"} );
				//Class test
				{
				IType classExc75;
					IModelElement[] methodtest_le74Childs = methodtest_le74.getChildren();
					classExc75 = ModelTestUtils.getAssertClass( methodtest_le74Childs, "Exc" );
				}
				//Class test
				{
				IType classBadCmp76;
					IModelElement[] methodtest_le74Childs = methodtest_le74.getChildren();
					classBadCmp76 = ModelTestUtils.getAssertClass( methodtest_le74Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__77;
						IModelElement[] classBadCmp76Childs = classBadCmp76.getChildren();
						method__eq__77 = ModelTestUtils.getAssertMethod( classBadCmp76Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__77, new String[] {"self", "other"} );
					}
				}
			}
		}
		//Class test
		{
		IType classGeneralMappingTests78;
			IModelElement[] moduleChilds = module.getChildren();
			classGeneralMappingTests78 = ModelTestUtils.getAssertClass( moduleChilds, "GeneralMappingTests" );
			{
				IModelElement[] classGeneralMappingTests78Childs = classGeneralMappingTests78.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classGeneralMappingTests78Childs, "type2test");
			}
		}
		//Class test
		{
		IType classDict79;
			IModelElement[] moduleChilds = module.getChildren();
			classDict79 = ModelTestUtils.getAssertClass( moduleChilds, "Dict" );
		}
		//Class test
		{
		IType classSubclassMappingTests80;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassMappingTests80 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassMappingTests" );
			{
				IModelElement[] classSubclassMappingTests80Childs = classSubclassMappingTests80.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassMappingTests80Childs, "type2test");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main81;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main81 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
				{
					IModelElement[] method__init__7Childs = method__init__7.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__7Childs, "n");
				}
				{
					IModelElement[] method__init__7Childs = method__init__7.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__7Childs, "rowgenerators");
				}
			}
			//Function test:solve
			{
			IMethod methodsolve10;
				IModelElement[] classQueens6Childs = classQueens6.getChildren();
				methodsolve10 = ModelTestUtils.getAssertMethod( classQueens6Childs, "solve", 1 );
				ModelTestUtils.assertParameterNames( methodsolve10, new String[] {"self"} );
			}
			{
				IModelElement[] classQueens6Childs = classQueens6.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classQueens6Childs, "used");
			}
			//Function test:printsolution
			{
			IMethod methodprintsolution12;
				IModelElement[] classQueens6Childs = classQueens6.getChildren();
				methodprintsolution12 = ModelTestUtils.getAssertMethod( classQueens6Childs, "printsolution", 2 );
				ModelTestUtils.assertParameterNames( methodprintsolution12, new String[] {"self", "row2col"} );
			}
		}
		//Class test
		{
		IType classKnights13;
			IModelElement[] moduleChilds = module.getChildren();
			classKnights13 = ModelTestUtils.getAssertClass( moduleChilds, "Knights" );
			//Function test:__init__
			{
			IMethod method__init__14;
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				method__init__14 = ModelTestUtils.getAssertMethod( classKnights13Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__14, new String[] {"self", "m", "n", "hard"} );
				//Function test:remove_from_successors
				{
				IMethod methodremove_from_successors15;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodremove_from_successors15 = ModelTestUtils.getAssertMethod( method__init__14Childs, "remove_from_successors", 2 );
					ModelTestUtils.assertParameterNames( methodremove_from_successors15, new String[] {"i0", "len"} );
				}
				//Function test:add_to_successors
				{
				IMethod methodadd_to_successors16;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodadd_to_successors16 = ModelTestUtils.getAssertMethod( method__init__14Childs, "add_to_successors", 1 );
					ModelTestUtils.assertParameterNames( methodadd_to_successors16, new String[] {"i0"} );
				}
				//Function test:first
				{
				IMethod methodfirst17;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodfirst17 = ModelTestUtils.getAssertMethod( method__init__14Childs, "first", 0 );
				}
				{
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__14Childs, "lastij");
				}
				//Function test:second
				{
				IMethod methodsecond19;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodsecond19 = ModelTestUtils.getAssertMethod( method__init__14Childs, "second", 0 );
				}
				{
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__14Childs, "final");
				}
				{
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__14Childs, "lastij");
				}
				//Function test:advance
				{
				IMethod methodadvance21;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodadvance21 = ModelTestUtils.getAssertMethod( method__init__14Childs, "advance", 1 );
					ModelTestUtils.assertParameterNames( methodadvance21, new String[] {"len"} );
				}
				{
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__14Childs, "lastij");
				}
				//Function test:advance_hard
				{
				IMethod methodadvance_hard23;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodadvance_hard23 = ModelTestUtils.getAssertMethod( method__init__14Childs, "advance_hard", 3 );
					ModelTestUtils.assertParameterNames( methodadvance_hard23, new String[] {"vmid", "hmid", "len"} );
				}
				{
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__14Childs, "lastij");
				}
				//Function test:last
				{
				IMethod methodlast25;
					IModelElement[] method__init__14Childs = method__init__14.getChildren();
					methodlast25 = ModelTestUtils.getAssertMethod( method__init__14Childs, "last", 0 );
				}
			}
			{
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classKnights13Childs, "squaregenerators");
			}
			//Function test:coords2index
			{
			IMethod methodcoords2index27;
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				methodcoords2index27 = ModelTestUtils.getAssertMethod( classKnights13Childs, "coords2index", 3 );
				ModelTestUtils.assertParameterNames( methodcoords2index27, new String[] {"self", "i", "j"} );
			}
			//Function test:index2coords
			{
			IMethod methodindex2coords28;
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				methodindex2coords28 = ModelTestUtils.getAssertMethod( classKnights13Childs, "index2coords", 2 );
				ModelTestUtils.assertParameterNames( methodindex2coords28, new String[] {"self", "index"} );
			}
			//Function test:_init_board
			{
			IMethod method_init_board29;
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				method_init_board29 = ModelTestUtils.getAssertMethod( classKnights13Childs, "_init_board", 1 );
				ModelTestUtils.assertParameterNames( method_init_board29, new String[] {"self"} );
			}
			//Function test:solve
			{
			IMethod methodsolve30;
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				methodsolve30 = ModelTestUtils.getAssertMethod( classKnights13Childs, "solve", 1 );
				ModelTestUtils.assertParameterNames( methodsolve30, new String[] {"self"} );
			}
			//Function test:printsolution
			{
			IMethod methodprintsolution31;
				IModelElement[] classKnights13Childs = classKnights13.getChildren();
				methodprintsolution31 = ModelTestUtils.getAssertMethod( classKnights13Childs, "printsolution", 2 );
				ModelTestUtils.assertParameterNames( methodprintsolution31, new String[] {"self", "x"} );
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
		IMethod methodtest_main32;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main32 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main32, new String[] {"verbose"} );
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
			{
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBinHexTestCase0Childs, "fname1");
			}
			{
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBinHexTestCase0Childs, "fname2");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classBinHexTestCase0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			{
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBinHexTestCase0Childs, "DATA");
			}
			//Function test:test_binhex
			{
			IMethod methodtest_binhex4;
				IModelElement[] classBinHexTestCase0Childs = classBinHexTestCase0.getChildren();
				methodtest_binhex4 = ModelTestUtils.getAssertMethod( classBinHexTestCase0Childs, "test_binhex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_binhex4, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main5;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main5 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongReprTest18Childs, "pkgname");
			}
			{
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongReprTest18Childs, "subpkgname");
			}
			{
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classLongReprTest18Childs, "here");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown21;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtearDown21 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown21, new String[] {"self"} );
			}
			//Function test:test_module
			{
			IMethod methodtest_module22;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_module22 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_module", 1 );
				ModelTestUtils.assertParameterNames( methodtest_module22, new String[] {"self"} );
			}
			//Function test:test_type
			{
			IMethod methodtest_type23;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_type23 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_type", 1 );
				ModelTestUtils.assertParameterNames( methodtest_type23, new String[] {"self"} );
			}
			//Function test:test_object
			{
			IMethod methodtest_object24;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_object24 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_object", 1 );
				ModelTestUtils.assertParameterNames( methodtest_object24, new String[] {"self"} );
			}
			//Function test:test_class
			{
			IMethod methodtest_class25;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_class25 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_class", 1 );
				ModelTestUtils.assertParameterNames( methodtest_class25, new String[] {"self"} );
			}
			//Function test:test_instance
			{
			IMethod methodtest_instance26;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_instance26 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_instance", 1 );
				ModelTestUtils.assertParameterNames( methodtest_instance26, new String[] {"self"} );
			}
			//Function test:test_method
			{
			IMethod methodtest_method27;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_method27 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_method", 1 );
				ModelTestUtils.assertParameterNames( methodtest_method27, new String[] {"self"} );
			}
			//Function test:test_builtin_function
			{
			IMethod methodtest_builtin_function28;
				IModelElement[] classLongReprTest18Childs = classLongReprTest18.getChildren();
				methodtest_builtin_function28 = ModelTestUtils.getAssertMethod( classLongReprTest18Childs, "test_builtin_function", 1 );
				ModelTestUtils.assertParameterNames( methodtest_builtin_function28, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classClassWithRepr29;
			IModelElement[] moduleChilds = module.getChildren();
			classClassWithRepr29 = ModelTestUtils.getAssertClass( moduleChilds, "ClassWithRepr" );
			//Function test:__init__
			{
			IMethod method__init__30;
				IModelElement[] classClassWithRepr29Childs = classClassWithRepr29.getChildren();
				method__init__30 = ModelTestUtils.getAssertMethod( classClassWithRepr29Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__30, new String[] {"self", "s"} );
			}
			{
				IModelElement[] classClassWithRepr29Childs = classClassWithRepr29.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classClassWithRepr29Childs, "s");
			}
			//Function test:__repr__
			{
			IMethod method__repr__32;
				IModelElement[] classClassWithRepr29Childs = classClassWithRepr29.getChildren();
				method__repr__32 = ModelTestUtils.getAssertMethod( classClassWithRepr29Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__32, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classClassWithFailingRepr33;
			IModelElement[] moduleChilds = module.getChildren();
			classClassWithFailingRepr33 = ModelTestUtils.getAssertClass( moduleChilds, "ClassWithFailingRepr" );
			//Function test:__repr__
			{
			IMethod method__repr__34;
				IModelElement[] classClassWithFailingRepr33Childs = classClassWithFailingRepr33.getChildren();
				method__repr__34 = ModelTestUtils.getAssertMethod( classClassWithFailingRepr33Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__34, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main35 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__num");
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__den");
			}
			//Function test:_get_num
			{
			IMethod method_get_num7;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method_get_num7 = ModelTestUtils.getAssertMethod( classRat4Childs, "_get_num", 1 );
				ModelTestUtils.assertParameterNames( method_get_num7, new String[] {"self"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "num");
			}
			//Function test:_get_den
			{
			IMethod method_get_den8;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method_get_den8 = ModelTestUtils.getAssertMethod( classRat4Childs, "_get_den", 1 );
				ModelTestUtils.assertParameterNames( method_get_den8, new String[] {"self"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "den");
			}
			//Function test:__repr__
			{
			IMethod method__repr__9;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__repr__9 = ModelTestUtils.getAssertMethod( classRat4Childs, "__repr__", 1 );
				ModelTestUtils.assertParameterNames( method__repr__9, new String[] {"self"} );
			}
			//Function test:__str__
			{
			IMethod method__str__10;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__str__10 = ModelTestUtils.getAssertMethod( classRat4Childs, "__str__", 1 );
				ModelTestUtils.assertParameterNames( method__str__10, new String[] {"self"} );
			}
			//Function test:__float__
			{
			IMethod method__float__11;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__float__11 = ModelTestUtils.getAssertMethod( classRat4Childs, "__float__", 1 );
				ModelTestUtils.assertParameterNames( method__float__11, new String[] {"self"} );
			}
			//Function test:__int__
			{
			IMethod method__int__12;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__int__12 = ModelTestUtils.getAssertMethod( classRat4Childs, "__int__", 1 );
				ModelTestUtils.assertParameterNames( method__int__12, new String[] {"self"} );
			}
			//Function test:__long__
			{
			IMethod method__long__13;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__long__13 = ModelTestUtils.getAssertMethod( classRat4Childs, "__long__", 1 );
				ModelTestUtils.assertParameterNames( method__long__13, new String[] {"self"} );
			}
			//Function test:__add__
			{
			IMethod method__add__14;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__add__14 = ModelTestUtils.getAssertMethod( classRat4Childs, "__add__", 2 );
				ModelTestUtils.assertParameterNames( method__add__14, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__radd__");
			}
			//Function test:__sub__
			{
			IMethod method__sub__15;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__sub__15 = ModelTestUtils.getAssertMethod( classRat4Childs, "__sub__", 2 );
				ModelTestUtils.assertParameterNames( method__sub__15, new String[] {"self", "other"} );
			}
			//Function test:__rsub__
			{
			IMethod method__rsub__16;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rsub__16 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rsub__", 2 );
				ModelTestUtils.assertParameterNames( method__rsub__16, new String[] {"self", "other"} );
			}
			//Function test:__mul__
			{
			IMethod method__mul__17;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__mul__17 = ModelTestUtils.getAssertMethod( classRat4Childs, "__mul__", 2 );
				ModelTestUtils.assertParameterNames( method__mul__17, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__rmul__");
			}
			//Function test:__truediv__
			{
			IMethod method__truediv__18;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__truediv__18 = ModelTestUtils.getAssertMethod( classRat4Childs, "__truediv__", 2 );
				ModelTestUtils.assertParameterNames( method__truediv__18, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__div__");
			}
			//Function test:__rtruediv__
			{
			IMethod method__rtruediv__19;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rtruediv__19 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rtruediv__", 2 );
				ModelTestUtils.assertParameterNames( method__rtruediv__19, new String[] {"self", "other"} );
			}
			{
				IModelElement[] classRat4Childs = classRat4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classRat4Childs, "__rdiv__");
			}
			//Function test:__floordiv__
			{
			IMethod method__floordiv__20;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__floordiv__20 = ModelTestUtils.getAssertMethod( classRat4Childs, "__floordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__floordiv__20, new String[] {"self", "other"} );
			}
			//Function test:__rfloordiv__
			{
			IMethod method__rfloordiv__21;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rfloordiv__21 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rfloordiv__", 2 );
				ModelTestUtils.assertParameterNames( method__rfloordiv__21, new String[] {"self", "other"} );
			}
			//Function test:__divmod__
			{
			IMethod method__divmod__22;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__divmod__22 = ModelTestUtils.getAssertMethod( classRat4Childs, "__divmod__", 2 );
				ModelTestUtils.assertParameterNames( method__divmod__22, new String[] {"self", "other"} );
			}
			//Function test:__rdivmod__
			{
			IMethod method__rdivmod__23;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rdivmod__23 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rdivmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rdivmod__23, new String[] {"self", "other"} );
			}
			//Function test:__mod__
			{
			IMethod method__mod__24;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__mod__24 = ModelTestUtils.getAssertMethod( classRat4Childs, "__mod__", 2 );
				ModelTestUtils.assertParameterNames( method__mod__24, new String[] {"self", "other"} );
			}
			//Function test:__rmod__
			{
			IMethod method__rmod__25;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__rmod__25 = ModelTestUtils.getAssertMethod( classRat4Childs, "__rmod__", 2 );
				ModelTestUtils.assertParameterNames( method__rmod__25, new String[] {"self", "other"} );
			}
			//Function test:__eq__
			{
			IMethod method__eq__26;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__eq__26 = ModelTestUtils.getAssertMethod( classRat4Childs, "__eq__", 2 );
				ModelTestUtils.assertParameterNames( method__eq__26, new String[] {"self", "other"} );
			}
			//Function test:__ne__
			{
			IMethod method__ne__27;
				IModelElement[] classRat4Childs = classRat4.getChildren();
				method__ne__27 = ModelTestUtils.getAssertMethod( classRat4Childs, "__ne__", 2 );
				ModelTestUtils.assertParameterNames( method__ne__27, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classRatTestCase28;
			IModelElement[] moduleChilds = module.getChildren();
			classRatTestCase28 = ModelTestUtils.getAssertClass( moduleChilds, "RatTestCase" );
			//Function test:test_gcd
			{
			IMethod methodtest_gcd29;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_gcd29 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_gcd", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gcd29, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor30;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_constructor30 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor30, new String[] {"self"} );
			}
			//Function test:test_add
			{
			IMethod methodtest_add31;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_add31 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_add", 1 );
				ModelTestUtils.assertParameterNames( methodtest_add31, new String[] {"self"} );
			}
			//Function test:test_sub
			{
			IMethod methodtest_sub32;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_sub32 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_sub", 1 );
				ModelTestUtils.assertParameterNames( methodtest_sub32, new String[] {"self"} );
			}
			//Function test:test_mul
			{
			IMethod methodtest_mul33;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_mul33 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_mul", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mul33, new String[] {"self"} );
			}
			//Function test:test_div
			{
			IMethod methodtest_div34;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_div34 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_div", 1 );
				ModelTestUtils.assertParameterNames( methodtest_div34, new String[] {"self"} );
			}
			//Function test:test_floordiv
			{
			IMethod methodtest_floordiv35;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_floordiv35 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_floordiv", 1 );
				ModelTestUtils.assertParameterNames( methodtest_floordiv35, new String[] {"self"} );
			}
			//Function test:test_eq
			{
			IMethod methodtest_eq36;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_eq36 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_eq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_eq36, new String[] {"self"} );
			}
			//Function test:test_future_div
			{
			IMethod methodtest_future_div37;
				IModelElement[] classRatTestCase28Childs = classRatTestCase28.getChildren();
				methodtest_future_div37 = ModelTestUtils.getAssertMethod( classRatTestCase28Childs, "test_future_div", 1 );
				ModelTestUtils.assertParameterNames( methodtest_future_div37, new String[] {"self"} );
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "future_test");
		}
		//Function test:test_main
		{
		IMethod methodtest_main38;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main38 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericStringIO0Childs, "_line");
			}
			{
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericStringIO0Childs, "_lines");
			}
			{
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestGenericStringIO0Childs, "_fp");
			}
			//Function test:test_reads
			{
			IMethod methodtest_reads3;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_reads3 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_reads", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reads3, new String[] {"self"} );
			}
			//Function test:test_writes
			{
			IMethod methodtest_writes4;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_writes4 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_writes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writes4, new String[] {"self"} );
			}
			//Function test:test_writelines
			{
			IMethod methodtest_writelines5;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_writelines5 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_writelines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_writelines5, new String[] {"self"} );
			}
			//Function test:test_truncate
			{
			IMethod methodtest_truncate6;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_truncate6 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_truncate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_truncate6, new String[] {"self"} );
			}
			//Function test:test_closed_flag
			{
			IMethod methodtest_closed_flag7;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_closed_flag7 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_closed_flag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_closed_flag7, new String[] {"self"} );
			}
			//Function test:test_iterator
			{
			IMethod methodtest_iterator8;
				IModelElement[] classTestGenericStringIO0Childs = classTestGenericStringIO0.getChildren();
				methodtest_iterator8 = ModelTestUtils.getAssertMethod( classTestGenericStringIO0Childs, "test_iterator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iterator8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestStringIO9;
			IModelElement[] moduleChilds = module.getChildren();
			classTestStringIO9 = ModelTestUtils.getAssertClass( moduleChilds, "TestStringIO" );
			{
				IModelElement[] classTestStringIO9Childs = classTestStringIO9.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestStringIO9Childs, "MODULE");
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode10;
				IModelElement[] classTestStringIO9Childs = classTestStringIO9.getChildren();
				methodtest_unicode10 = ModelTestUtils.getAssertMethod( classTestStringIO9Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestcStringIO11;
			IModelElement[] moduleChilds = module.getChildren();
			classTestcStringIO11 = ModelTestUtils.getAssertClass( moduleChilds, "TestcStringIO" );
			{
				IModelElement[] classTestcStringIO11Childs = classTestcStringIO11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestcStringIO11Childs, "MODULE");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "buffer");
		}
		//Class test
		{
		IType classTestBufferStringIO12;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBufferStringIO12 = ModelTestUtils.getAssertClass( moduleChilds, "TestBufferStringIO" );
			{
				IModelElement[] classTestBufferStringIO12Childs = classTestBufferStringIO12.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBufferStringIO12Childs, "constructor");
			}
		}
		//Class test
		{
		IType classTestBuffercStringIO13;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBuffercStringIO13 = ModelTestUtils.getAssertClass( moduleChilds, "TestBuffercStringIO" );
			{
				IModelElement[] classTestBuffercStringIO13Childs = classTestBuffercStringIO13.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBuffercStringIO13Childs, "constructor");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main14;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main14 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			//Function test:handle_starttag
			{
			IMethod methodhandle_starttag5;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_starttag5 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_starttag", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_starttag5, new String[] {"self", "tag", "attrs"} );
			}
			//Function test:handle_startendtag
			{
			IMethod methodhandle_startendtag6;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_startendtag6 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_startendtag", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_startendtag6, new String[] {"self", "tag", "attrs"} );
			}
			//Function test:handle_endtag
			{
			IMethod methodhandle_endtag7;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_endtag7 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_endtag", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_endtag7, new String[] {"self", "tag"} );
			}
			//Function test:handle_comment
			{
			IMethod methodhandle_comment8;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_comment8 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_comment", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_comment8, new String[] {"self", "data"} );
			}
			//Function test:handle_charref
			{
			IMethod methodhandle_charref9;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_charref9 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_charref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_charref9, new String[] {"self", "data"} );
			}
			//Function test:handle_data
			{
			IMethod methodhandle_data10;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_data10 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_data", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_data10, new String[] {"self", "data"} );
			}
			//Function test:handle_decl
			{
			IMethod methodhandle_decl11;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_decl11 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_decl", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_decl11, new String[] {"self", "data"} );
			}
			//Function test:handle_entityref
			{
			IMethod methodhandle_entityref12;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_entityref12 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_entityref", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_entityref12, new String[] {"self", "data"} );
			}
			//Function test:handle_pi
			{
			IMethod methodhandle_pi13;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodhandle_pi13 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "handle_pi", 2 );
				ModelTestUtils.assertParameterNames( methodhandle_pi13, new String[] {"self", "data"} );
			}
			//Function test:unknown_decl
			{
			IMethod methodunknown_decl14;
				IModelElement[] classEventCollector0Childs = classEventCollector0.getChildren();
				methodunknown_decl14 = ModelTestUtils.getAssertMethod( classEventCollector0Childs, "unknown_decl", 2 );
				ModelTestUtils.assertParameterNames( methodunknown_decl14, new String[] {"self", "decl"} );
			}
		}
		//Class test
		{
		IType classEventCollectorExtra15;
			IModelElement[] moduleChilds = module.getChildren();
			classEventCollectorExtra15 = ModelTestUtils.getAssertClass( moduleChilds, "EventCollectorExtra" );
			//Function test:handle_starttag
			{
			IMethod methodhandle_starttag16;
				IModelElement[] classEventCollectorExtra15Childs = classEventCollectorExtra15.getChildren();
				methodhandle_starttag16 = ModelTestUtils.getAssertMethod( classEventCollectorExtra15Childs, "handle_starttag", 3 );
				ModelTestUtils.assertParameterNames( methodhandle_starttag16, new String[] {"self", "tag", "attrs"} );
			}
		}
		//Class test
		{
		IType classTestCaseBase17;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCaseBase17 = ModelTestUtils.getAssertClass( moduleChilds, "TestCaseBase" );
			//Function test:_run_check
			{
			IMethod method_run_check18;
				IModelElement[] classTestCaseBase17Childs = classTestCaseBase17.getChildren();
				method_run_check18 = ModelTestUtils.getAssertMethod( classTestCaseBase17Childs, "_run_check", 4 );
				ModelTestUtils.assertParameterNames( method_run_check18, new String[] {"self", "source", "expected_events", "collector"} );
			}
			//Function test:_run_check_extra
			{
			IMethod method_run_check_extra19;
				IModelElement[] classTestCaseBase17Childs = classTestCaseBase17.getChildren();
				method_run_check_extra19 = ModelTestUtils.getAssertMethod( classTestCaseBase17Childs, "_run_check_extra", 3 );
				ModelTestUtils.assertParameterNames( method_run_check_extra19, new String[] {"self", "source", "events"} );
			}
			//Function test:_parse_error
			{
			IMethod method_parse_error20;
				IModelElement[] classTestCaseBase17Childs = classTestCaseBase17.getChildren();
				method_parse_error20 = ModelTestUtils.getAssertMethod( classTestCaseBase17Childs, "_parse_error", 2 );
				ModelTestUtils.assertParameterNames( method_parse_error20, new String[] {"self", "source"} );
				//Function test:parse
				{
				IMethod methodparse21;
					IModelElement[] method_parse_error20Childs = method_parse_error20.getChildren();
					methodparse21 = ModelTestUtils.getAssertMethod( method_parse_error20Childs, "parse", 1 );
					ModelTestUtils.assertParameterNames( methodparse21, new String[] {"source"} );
				}
			}
		}
		//Class test
		{
		IType classHTMLParserTestCase22;
			IModelElement[] moduleChilds = module.getChildren();
			classHTMLParserTestCase22 = ModelTestUtils.getAssertClass( moduleChilds, "HTMLParserTestCase" );
			//Function test:test_processing_instruction_only
			{
			IMethod methodtest_processing_instruction_only23;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_processing_instruction_only23 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_processing_instruction_only", 1 );
				ModelTestUtils.assertParameterNames( methodtest_processing_instruction_only23, new String[] {"self"} );
			}
			//Function test:test_simple_html
			{
			IMethod methodtest_simple_html24;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_simple_html24 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_simple_html", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_html24, new String[] {"self"} );
			}
			//Function test:test_unclosed_entityref
			{
			IMethod methodtest_unclosed_entityref25;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_unclosed_entityref25 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_unclosed_entityref", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unclosed_entityref25, new String[] {"self"} );
			}
			//Function test:test_doctype_decl
			{
			IMethod methodtest_doctype_decl26;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_doctype_decl26 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_doctype_decl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doctype_decl26, new String[] {"self"} );
			}
			//Function test:test_bad_nesting
			{
			IMethod methodtest_bad_nesting27;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_bad_nesting27 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_bad_nesting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bad_nesting27, new String[] {"self"} );
			}
			//Function test:test_bare_ampersands
			{
			IMethod methodtest_bare_ampersands28;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_bare_ampersands28 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_bare_ampersands", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_ampersands28, new String[] {"self"} );
			}
			//Function test:test_bare_pointy_brackets
			{
			IMethod methodtest_bare_pointy_brackets29;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_bare_pointy_brackets29 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_bare_pointy_brackets", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bare_pointy_brackets29, new String[] {"self"} );
			}
			//Function test:test_attr_syntax
			{
			IMethod methodtest_attr_syntax30;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_attr_syntax30 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_attr_syntax", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_syntax30, new String[] {"self"} );
			}
			//Function test:test_attr_values
			{
			IMethod methodtest_attr_values31;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_attr_values31 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_attr_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_values31, new String[] {"self"} );
			}
			//Function test:test_attr_entity_replacement
			{
			IMethod methodtest_attr_entity_replacement32;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_attr_entity_replacement32 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_attr_entity_replacement", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_entity_replacement32, new String[] {"self"} );
			}
			//Function test:test_attr_funky_names
			{
			IMethod methodtest_attr_funky_names33;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_attr_funky_names33 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_attr_funky_names", 1 );
				ModelTestUtils.assertParameterNames( methodtest_attr_funky_names33, new String[] {"self"} );
			}
			//Function test:test_illegal_declarations
			{
			IMethod methodtest_illegal_declarations34;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_illegal_declarations34 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_illegal_declarations", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illegal_declarations34, new String[] {"self"} );
			}
			//Function test:test_starttag_end_boundary
			{
			IMethod methodtest_starttag_end_boundary35;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_starttag_end_boundary35 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_starttag_end_boundary", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starttag_end_boundary35, new String[] {"self"} );
			}
			//Function test:test_buffer_artefacts
			{
			IMethod methodtest_buffer_artefacts36;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_buffer_artefacts36 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_buffer_artefacts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_buffer_artefacts36, new String[] {"self"} );
			}
			//Function test:test_starttag_junk_chars
			{
			IMethod methodtest_starttag_junk_chars37;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_starttag_junk_chars37 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_starttag_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_starttag_junk_chars37, new String[] {"self"} );
			}
			//Function test:test_declaration_junk_chars
			{
			IMethod methodtest_declaration_junk_chars38;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_declaration_junk_chars38 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_declaration_junk_chars", 1 );
				ModelTestUtils.assertParameterNames( methodtest_declaration_junk_chars38, new String[] {"self"} );
			}
			//Function test:test_startendtag
			{
			IMethod methodtest_startendtag39;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_startendtag39 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_startendtag", 1 );
				ModelTestUtils.assertParameterNames( methodtest_startendtag39, new String[] {"self"} );
			}
			//Function test:test_get_starttag_text
			{
			IMethod methodtest_get_starttag_text40;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_get_starttag_text40 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_get_starttag_text", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get_starttag_text40, new String[] {"self"} );
			}
			//Function test:test_cdata_content
			{
			IMethod methodtest_cdata_content41;
				IModelElement[] classHTMLParserTestCase22Childs = classHTMLParserTestCase22.getChildren();
				methodtest_cdata_content41 = ModelTestUtils.getAssertMethod( classHTMLParserTestCase22Childs, "test_cdata_content", 1 );
				ModelTestUtils.assertParameterNames( methodtest_cdata_content41, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main42;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main42 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classExtensionSaver2Childs = classExtensionSaver2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classExtensionSaver2Childs, "code");
			}
			{
				IModelElement[] classExtensionSaver2Childs = classExtensionSaver2.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classExtensionSaver2Childs, "pair");
			}
			//Function test:restore
			{
			IMethod methodrestore5;
				IModelElement[] classExtensionSaver2Childs = classExtensionSaver2.getChildren();
				methodrestore5 = ModelTestUtils.getAssertMethod( classExtensionSaver2Childs, "restore", 1 );
				ModelTestUtils.assertParameterNames( methodrestore5, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classC6;
			IModelElement[] moduleChilds = module.getChildren();
			classC6 = ModelTestUtils.getAssertClass( moduleChilds, "C" );
			//Function test:__cmp__
			{
			IMethod method__cmp__7;
				IModelElement[] classC6Childs = classC6.getChildren();
				method__cmp__7 = ModelTestUtils.getAssertMethod( classC6Childs, "__cmp__", 2 );
				ModelTestUtils.assertParameterNames( method__cmp__7, new String[] {"self", "other"} );
			}
		}
		//Class test
		{
		IType classmyint8;
			IModelElement[] moduleChilds = module.getChildren();
			classmyint8 = ModelTestUtils.getAssertClass( moduleChilds, "myint" );
			//Function test:__init__
			{
			IMethod method__init__9;
				IModelElement[] classmyint8Childs = classmyint8.getChildren();
				method__init__9 = ModelTestUtils.getAssertMethod( classmyint8Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__9, new String[] {"self", "x"} );
			}
			{
				IModelElement[] classmyint8Childs = classmyint8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classmyint8Childs, "str");
			}
		}
		//Class test
		{
		IType classinitarg11;
			IModelElement[] moduleChilds = module.getChildren();
			classinitarg11 = ModelTestUtils.getAssertClass( moduleChilds, "initarg" );
			//Function test:__init__
			{
			IMethod method__init__12;
				IModelElement[] classinitarg11Childs = classinitarg11.getChildren();
				method__init__12 = ModelTestUtils.getAssertMethod( classinitarg11Childs, "__init__", 3 );
				ModelTestUtils.assertParameterNames( method__init__12, new String[] {"self", "a", "b"} );
			}
			{
				IModelElement[] classinitarg11Childs = classinitarg11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classinitarg11Childs, "a");
			}
			{
				IModelElement[] classinitarg11Childs = classinitarg11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classinitarg11Childs, "b");
			}
			//Function test:__getinitargs__
			{
			IMethod method__getinitargs__14;
				IModelElement[] classinitarg11Childs = classinitarg11.getChildren();
				method__getinitargs__14 = ModelTestUtils.getAssertMethod( classinitarg11Childs, "__getinitargs__", 1 );
				ModelTestUtils.assertParameterNames( method__getinitargs__14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classmetaclass15;
			IModelElement[] moduleChilds = module.getChildren();
			classmetaclass15 = ModelTestUtils.getAssertClass( moduleChilds, "metaclass" );
		}
		//Class test
		{
		IType classuse_metaclass16;
			IModelElement[] moduleChilds = module.getChildren();
			classuse_metaclass16 = ModelTestUtils.getAssertClass( moduleChilds, "use_metaclass" );
			{
				IModelElement[] classuse_metaclass16Childs = classuse_metaclass16.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classuse_metaclass16Childs, "__metaclass__");
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
		IMethod methodcreate_data17;
			IModelElement[] moduleChilds = module.getChildren();
			methodcreate_data17 = ModelTestUtils.getAssertMethod( moduleChilds, "create_data", 0 );
		}
		//Class test
		{
		IType classAbstractPickleTests18;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractPickleTests18 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractPickleTests" );
			{
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractPickleTests18Childs, "_testdata");
			}
			//Function test:setUp
			{
			IMethod methodsetUp19;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodsetUp19 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp19, new String[] {"self"} );
			}
			//Function test:test_misc
			{
			IMethod methodtest_misc20;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_misc20 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_misc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_misc20, new String[] {"self"} );
			}
			//Function test:test_roundtrip_equality
			{
			IMethod methodtest_roundtrip_equality21;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_roundtrip_equality21 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_roundtrip_equality", 1 );
				ModelTestUtils.assertParameterNames( methodtest_roundtrip_equality21, new String[] {"self"} );
			}
			//Function test:test_load_from_canned_string
			{
			IMethod methodtest_load_from_canned_string22;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_load_from_canned_string22 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_load_from_canned_string", 1 );
				ModelTestUtils.assertParameterNames( methodtest_load_from_canned_string22, new String[] {"self"} );
			}
			//Function test:dont_test_disassembly
			{
			IMethod methoddont_test_disassembly23;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methoddont_test_disassembly23 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "dont_test_disassembly", 1 );
				ModelTestUtils.assertParameterNames( methoddont_test_disassembly23, new String[] {"self"} );
			}
			//Function test:test_recursive_list
			{
			IMethod methodtest_recursive_list24;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_recursive_list24 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_recursive_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_list24, new String[] {"self"} );
			}
			//Function test:test_recursive_dict
			{
			IMethod methodtest_recursive_dict25;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_recursive_dict25 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_recursive_dict", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_dict25, new String[] {"self"} );
			}
			//Function test:test_recursive_inst
			{
			IMethod methodtest_recursive_inst26;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_recursive_inst26 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_recursive_inst", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_inst26, new String[] {"self"} );
			}
			//Function test:test_recursive_multi
			{
			IMethod methodtest_recursive_multi27;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_recursive_multi27 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_recursive_multi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_recursive_multi27, new String[] {"self"} );
			}
			//Function test:test_garyp
			{
			IMethod methodtest_garyp28;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_garyp28 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_garyp", 1 );
				ModelTestUtils.assertParameterNames( methodtest_garyp28, new String[] {"self"} );
			}
			//Function test:test_insecure_strings
			{
			IMethod methodtest_insecure_strings29;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_insecure_strings29 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_insecure_strings", 1 );
				ModelTestUtils.assertParameterNames( methodtest_insecure_strings29, new String[] {"self"} );
			}
			//Function test:test_unicode
			{
			IMethod methodtest_unicode30;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_unicode30 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_unicode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unicode30, new String[] {"self"} );
			}
			//Function test:test_ints
			{
			IMethod methodtest_ints31;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_ints31 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_ints", 1 );
				ModelTestUtils.assertParameterNames( methodtest_ints31, new String[] {"self"} );
			}
			//Function test:test_maxint64
			{
			IMethod methodtest_maxint6432;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_maxint6432 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_maxint64", 1 );
				ModelTestUtils.assertParameterNames( methodtest_maxint6432, new String[] {"self"} );
			}
			//Function test:test_long
			{
			IMethod methodtest_long33;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_long33 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_long", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long33, new String[] {"self"} );
			}
			//Function test:test_reduce
			{
			IMethod methodtest_reduce34;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_reduce34 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce34, new String[] {"self"} );
			}
			//Function test:test_getinitargs
			{
			IMethod methodtest_getinitargs35;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_getinitargs35 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_getinitargs", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getinitargs35, new String[] {"self"} );
			}
			//Function test:test_metaclass
			{
			IMethod methodtest_metaclass36;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_metaclass36 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_metaclass", 1 );
				ModelTestUtils.assertParameterNames( methodtest_metaclass36, new String[] {"self"} );
			}
			//Function test:test_structseq
			{
			IMethod methodtest_structseq37;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_structseq37 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_structseq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_structseq37, new String[] {"self"} );
			}
			//Function test:test_proto
			{
			IMethod methodtest_proto38;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_proto38 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_proto", 1 );
				ModelTestUtils.assertParameterNames( methodtest_proto38, new String[] {"self"} );
			}
			//Function test:test_long1
			{
			IMethod methodtest_long139;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_long139 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_long1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long139, new String[] {"self"} );
			}
			//Function test:test_long4
			{
			IMethod methodtest_long440;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_long440 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_long4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_long440, new String[] {"self"} );
			}
			//Function test:test_short_tuples
			{
			IMethod methodtest_short_tuples41;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_short_tuples41 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_short_tuples", 1 );
				ModelTestUtils.assertParameterNames( methodtest_short_tuples41, new String[] {"self"} );
			}
			//Function test:test_singletons
			{
			IMethod methodtest_singletons42;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_singletons42 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_singletons", 1 );
				ModelTestUtils.assertParameterNames( methodtest_singletons42, new String[] {"self"} );
			}
			//Function test:test_newobj_tuple
			{
			IMethod methodtest_newobj_tuple43;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_newobj_tuple43 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_newobj_tuple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_tuple43, new String[] {"self"} );
			}
			//Function test:test_newobj_list
			{
			IMethod methodtest_newobj_list44;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_newobj_list44 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_newobj_list", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_list44, new String[] {"self"} );
			}
			//Function test:test_newobj_generic
			{
			IMethod methodtest_newobj_generic45;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_newobj_generic45 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_newobj_generic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_generic45, new String[] {"self"} );
			}
			//Function test:produce_global_ext
			{
			IMethod methodproduce_global_ext46;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodproduce_global_ext46 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "produce_global_ext", 3 );
				ModelTestUtils.assertParameterNames( methodproduce_global_ext46, new String[] {"self", "extcode", "opcode"} );
			}
			//Function test:test_global_ext1
			{
			IMethod methodtest_global_ext147;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_global_ext147 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_global_ext1", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_ext147, new String[] {"self"} );
			}
			//Function test:test_global_ext2
			{
			IMethod methodtest_global_ext248;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_global_ext248 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_global_ext2", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_ext248, new String[] {"self"} );
			}
			//Function test:test_global_ext4
			{
			IMethod methodtest_global_ext449;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_global_ext449 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_global_ext4", 1 );
				ModelTestUtils.assertParameterNames( methodtest_global_ext449, new String[] {"self"} );
			}
			//Function test:test_list_chunking
			{
			IMethod methodtest_list_chunking50;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_list_chunking50 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_list_chunking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_list_chunking50, new String[] {"self"} );
			}
			//Function test:test_dict_chunking
			{
			IMethod methodtest_dict_chunking51;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_dict_chunking51 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_dict_chunking", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dict_chunking51, new String[] {"self"} );
			}
			//Function test:test_simple_newobj
			{
			IMethod methodtest_simple_newobj52;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_simple_newobj52 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_simple_newobj", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple_newobj52, new String[] {"self"} );
			}
			//Function test:test_newobj_list_slots
			{
			IMethod methodtest_newobj_list_slots53;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_newobj_list_slots53 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_newobj_list_slots", 1 );
				ModelTestUtils.assertParameterNames( methodtest_newobj_list_slots53, new String[] {"self"} );
			}
			//Function test:test_reduce_overrides_default_reduce_ex
			{
			IMethod methodtest_reduce_overrides_default_reduce_ex54;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_reduce_overrides_default_reduce_ex54 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_reduce_overrides_default_reduce_ex", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_overrides_default_reduce_ex54, new String[] {"self"} );
			}
			//Function test:test_reduce_ex_called
			{
			IMethod methodtest_reduce_ex_called55;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_reduce_ex_called55 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_reduce_ex_called", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_ex_called55, new String[] {"self"} );
			}
			//Function test:test_reduce_ex_overrides_reduce
			{
			IMethod methodtest_reduce_ex_overrides_reduce56;
				IModelElement[] classAbstractPickleTests18Childs = classAbstractPickleTests18.getChildren();
				methodtest_reduce_ex_overrides_reduce56 = ModelTestUtils.getAssertMethod( classAbstractPickleTests18Childs, "test_reduce_ex_overrides_reduce", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reduce_ex_overrides_reduce56, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classREX_one57;
			IModelElement[] moduleChilds = module.getChildren();
			classREX_one57 = ModelTestUtils.getAssertClass( moduleChilds, "REX_one" );
			{
				IModelElement[] classREX_one57Childs = classREX_one57.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_one57Childs, "_reduce_called");
			}
			//Function test:__reduce__
			{
			IMethod method__reduce__58;
				IModelElement[] classREX_one57Childs = classREX_one57.getChildren();
				method__reduce__58 = ModelTestUtils.getAssertMethod( classREX_one57Childs, "__reduce__", 1 );
				ModelTestUtils.assertParameterNames( method__reduce__58, new String[] {"self"} );
			}
			{
				IModelElement[] classREX_one57Childs = classREX_one57.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_one57Childs, "_reduce_called");
			}
		}
		//Class test
		{
		IType classREX_two60;
			IModelElement[] moduleChilds = module.getChildren();
			classREX_two60 = ModelTestUtils.getAssertClass( moduleChilds, "REX_two" );
			{
				IModelElement[] classREX_two60Childs = classREX_two60.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_two60Childs, "_proto");
			}
			//Function test:__reduce_ex__
			{
			IMethod method__reduce_ex__61;
				IModelElement[] classREX_two60Childs = classREX_two60.getChildren();
				method__reduce_ex__61 = ModelTestUtils.getAssertMethod( classREX_two60Childs, "__reduce_ex__", 2 );
				ModelTestUtils.assertParameterNames( method__reduce_ex__61, new String[] {"self", "proto"} );
			}
			{
				IModelElement[] classREX_two60Childs = classREX_two60.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_two60Childs, "_proto");
			}
		}
		//Class test
		{
		IType classREX_three63;
			IModelElement[] moduleChilds = module.getChildren();
			classREX_three63 = ModelTestUtils.getAssertClass( moduleChilds, "REX_three" );
			{
				IModelElement[] classREX_three63Childs = classREX_three63.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classREX_three63Childs, "_proto");
			}
			//Function test:__reduce_ex__
			{
			IMethod method__reduce_ex__64;
				IModelElement[] classREX_three63Childs = classREX_three63.getChildren();
				method__reduce_ex__64 = ModelTestUtils.getAssertMethod( classREX_three63Childs, "__reduce_ex__", 2 );
				ModelTestUtils.assertParameterNames( method__reduce_ex__64, new String[] {"self", "proto"} );
			}
			//Function test:__reduce__
			{
			IMethod method__reduce__65;
				IModelElement[] classREX_three63Childs = classREX_three63.getChildren();
				method__reduce__65 = ModelTestUtils.getAssertMethod( classREX_three63Childs, "__reduce__", 1 );
				ModelTestUtils.assertParameterNames( method__reduce__65, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMyInt66;
			IModelElement[] moduleChilds = module.getChildren();
			classMyInt66 = ModelTestUtils.getAssertClass( moduleChilds, "MyInt" );
			{
				IModelElement[] classMyInt66Childs = classMyInt66.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyInt66Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyLong67;
			IModelElement[] moduleChilds = module.getChildren();
			classMyLong67 = ModelTestUtils.getAssertClass( moduleChilds, "MyLong" );
			{
				IModelElement[] classMyLong67Childs = classMyLong67.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyLong67Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyFloat68;
			IModelElement[] moduleChilds = module.getChildren();
			classMyFloat68 = ModelTestUtils.getAssertClass( moduleChilds, "MyFloat" );
			{
				IModelElement[] classMyFloat68Childs = classMyFloat68.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyFloat68Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyComplex69;
			IModelElement[] moduleChilds = module.getChildren();
			classMyComplex69 = ModelTestUtils.getAssertClass( moduleChilds, "MyComplex" );
			{
				IModelElement[] classMyComplex69Childs = classMyComplex69.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyComplex69Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyStr70;
			IModelElement[] moduleChilds = module.getChildren();
			classMyStr70 = ModelTestUtils.getAssertClass( moduleChilds, "MyStr" );
			{
				IModelElement[] classMyStr70Childs = classMyStr70.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyStr70Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyUnicode71;
			IModelElement[] moduleChilds = module.getChildren();
			classMyUnicode71 = ModelTestUtils.getAssertClass( moduleChilds, "MyUnicode" );
			{
				IModelElement[] classMyUnicode71Childs = classMyUnicode71.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyUnicode71Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyTuple72;
			IModelElement[] moduleChilds = module.getChildren();
			classMyTuple72 = ModelTestUtils.getAssertClass( moduleChilds, "MyTuple" );
			{
				IModelElement[] classMyTuple72Childs = classMyTuple72.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyTuple72Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyList73;
			IModelElement[] moduleChilds = module.getChildren();
			classMyList73 = ModelTestUtils.getAssertClass( moduleChilds, "MyList" );
			{
				IModelElement[] classMyList73Childs = classMyList73.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyList73Childs, "sample");
			}
		}
		//Class test
		{
		IType classMyDict74;
			IModelElement[] moduleChilds = module.getChildren();
			classMyDict74 = ModelTestUtils.getAssertClass( moduleChilds, "MyDict" );
			{
				IModelElement[] classMyDict74Childs = classMyDict74.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classMyDict74Childs, "sample");
			}
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "myclasses");
		}
		//Class test
		{
		IType classSlotList75;
			IModelElement[] moduleChilds = module.getChildren();
			classSlotList75 = ModelTestUtils.getAssertClass( moduleChilds, "SlotList" );
			{
				IModelElement[] classSlotList75Childs = classSlotList75.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSlotList75Childs, "__slots__");
			}
		}
		//Class test
		{
		IType classSimpleNewObj76;
			IModelElement[] moduleChilds = module.getChildren();
			classSimpleNewObj76 = ModelTestUtils.getAssertClass( moduleChilds, "SimpleNewObj" );
			//Function test:__init__
			{
			IMethod method__init__77;
				IModelElement[] classSimpleNewObj76Childs = classSimpleNewObj76.getChildren();
				method__init__77 = ModelTestUtils.getAssertMethod( classSimpleNewObj76Childs, "__init__", 4 );
				ModelTestUtils.assertParameterNames( method__init__77, new String[] {"self", "a", "b", "c"} );
			}
		}
		//Class test
		{
		IType classAbstractPickleModuleTests78;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractPickleModuleTests78 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractPickleModuleTests" );
			//Function test:test_dump_closed_file
			{
			IMethod methodtest_dump_closed_file79;
				IModelElement[] classAbstractPickleModuleTests78Childs = classAbstractPickleModuleTests78.getChildren();
				methodtest_dump_closed_file79 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests78Childs, "test_dump_closed_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_dump_closed_file79, new String[] {"self"} );
			}
			//Function test:test_load_closed_file
			{
			IMethod methodtest_load_closed_file80;
				IModelElement[] classAbstractPickleModuleTests78Childs = classAbstractPickleModuleTests78.getChildren();
				methodtest_load_closed_file80 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests78Childs, "test_load_closed_file", 1 );
				ModelTestUtils.assertParameterNames( methodtest_load_closed_file80, new String[] {"self"} );
			}
			//Function test:test_highest_protocol
			{
			IMethod methodtest_highest_protocol81;
				IModelElement[] classAbstractPickleModuleTests78Childs = classAbstractPickleModuleTests78.getChildren();
				methodtest_highest_protocol81 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests78Childs, "test_highest_protocol", 1 );
				ModelTestUtils.assertParameterNames( methodtest_highest_protocol81, new String[] {"self"} );
			}
			//Function test:test_callapi
			{
			IMethod methodtest_callapi82;
				IModelElement[] classAbstractPickleModuleTests78Childs = classAbstractPickleModuleTests78.getChildren();
				methodtest_callapi82 = ModelTestUtils.getAssertMethod( classAbstractPickleModuleTests78Childs, "test_callapi", 1 );
				ModelTestUtils.assertParameterNames( methodtest_callapi82, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classAbstractPersistentPicklerTests83;
			IModelElement[] moduleChilds = module.getChildren();
			classAbstractPersistentPicklerTests83 = ModelTestUtils.getAssertClass( moduleChilds, "AbstractPersistentPicklerTests" );
			//Function test:persistent_id
			{
			IMethod methodpersistent_id84;
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				methodpersistent_id84 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests83Childs, "persistent_id", 2 );
				ModelTestUtils.assertParameterNames( methodpersistent_id84, new String[] {"self", "object"} );
			}
			//Function test:persistent_load
			{
			IMethod methodpersistent_load85;
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				methodpersistent_load85 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests83Childs, "persistent_load", 2 );
				ModelTestUtils.assertParameterNames( methodpersistent_load85, new String[] {"self", "oid"} );
			}
			//Function test:test_persistence
			{
			IMethod methodtest_persistence86;
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				methodtest_persistence86 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests83Childs, "test_persistence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_persistence86, new String[] {"self"} );
			}
			{
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractPersistentPicklerTests83Childs, "id_count");
			}
			{
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractPersistentPicklerTests83Childs, "load_count");
			}
			//Function test:test_bin_persistence
			{
			IMethod methodtest_bin_persistence88;
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				methodtest_bin_persistence88 = ModelTestUtils.getAssertMethod( classAbstractPersistentPicklerTests83Childs, "test_bin_persistence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bin_persistence88, new String[] {"self"} );
			}
			{
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractPersistentPicklerTests83Childs, "id_count");
			}
			{
				IModelElement[] classAbstractPersistentPicklerTests83Childs = classAbstractPersistentPicklerTests83.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classAbstractPersistentPicklerTests83Childs, "load_count");
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
				{
					IModelElement[] classA17Childs = classA17.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( classA17Childs, "init");
				}
			}
		}
		//Function test:test_finalizer
		{
		IMethod methodtest_finalizer20;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_finalizer20 = ModelTestUtils.getAssertMethod( moduleChilds, "test_finalizer", 0 );
			//Class test
			{
			IType classA21;
				IModelElement[] methodtest_finalizer20Childs = methodtest_finalizer20.getChildren();
				classA21 = ModelTestUtils.getAssertClass( methodtest_finalizer20Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__22;
					IModelElement[] classA21Childs = classA21.getChildren();
					method__del__22 = ModelTestUtils.getAssertMethod( classA21Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__22, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB23;
				IModelElement[] methodtest_finalizer20Childs = methodtest_finalizer20.getChildren();
				classB23 = ModelTestUtils.getAssertClass( methodtest_finalizer20Childs, "B" );
			}
		}
		//Function test:test_finalizer_newclass
		{
		IMethod methodtest_finalizer_newclass24;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_finalizer_newclass24 = ModelTestUtils.getAssertMethod( moduleChilds, "test_finalizer_newclass", 0 );
			//Class test
			{
			IType classA25;
				IModelElement[] methodtest_finalizer_newclass24Childs = methodtest_finalizer_newclass24.getChildren();
				classA25 = ModelTestUtils.getAssertClass( methodtest_finalizer_newclass24Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__26;
					IModelElement[] classA25Childs = classA25.getChildren();
					method__del__26 = ModelTestUtils.getAssertMethod( classA25Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__26, new String[] {"self"} );
				}
			}
			//Class test
			{
			IType classB27;
				IModelElement[] methodtest_finalizer_newclass24Childs = methodtest_finalizer_newclass24.getChildren();
				classB27 = ModelTestUtils.getAssertClass( methodtest_finalizer_newclass24Childs, "B" );
			}
		}
		//Function test:test_function
		{
		IMethod methodtest_function28;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_function28 = ModelTestUtils.getAssertMethod( moduleChilds, "test_function", 0 );
		}
		//Function test:test_frame
		{
		IMethod methodtest_frame29;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_frame29 = ModelTestUtils.getAssertMethod( moduleChilds, "test_frame", 0 );
			//Function test:f
			{
			IMethod methodf30;
				IModelElement[] methodtest_frame29Childs = methodtest_frame29.getChildren();
				methodf30 = ModelTestUtils.getAssertMethod( methodtest_frame29Childs, "f", 0 );
			}
		}
		//Function test:test_saveall
		{
		IMethod methodtest_saveall31;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_saveall31 = ModelTestUtils.getAssertMethod( moduleChilds, "test_saveall", 0 );
		}
		//Function test:test_del
		{
		IMethod methodtest_del32;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_del32 = ModelTestUtils.getAssertMethod( moduleChilds, "test_del", 0 );
			//Class test
			{
			IType classA33;
				IModelElement[] methodtest_del32Childs = methodtest_del32.getChildren();
				classA33 = ModelTestUtils.getAssertClass( methodtest_del32Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__34;
					IModelElement[] classA33Childs = classA33.getChildren();
					method__del__34 = ModelTestUtils.getAssertMethod( classA33Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__34, new String[] {"self"} );
				}
			}
		}
		//Function test:test_del_newclass
		{
		IMethod methodtest_del_newclass35;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_del_newclass35 = ModelTestUtils.getAssertMethod( moduleChilds, "test_del_newclass", 0 );
			//Class test
			{
			IType classA36;
				IModelElement[] methodtest_del_newclass35Childs = methodtest_del_newclass35.getChildren();
				classA36 = ModelTestUtils.getAssertClass( methodtest_del_newclass35Childs, "A" );
				//Function test:__del__
				{
				IMethod method__del__37;
					IModelElement[] classA36Childs = classA36.getChildren();
					method__del__37 = ModelTestUtils.getAssertMethod( classA36Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__37, new String[] {"self"} );
				}
			}
		}
		//Class test
		{
		IType classOuch38;
			IModelElement[] moduleChilds = module.getChildren();
			classOuch38 = ModelTestUtils.getAssertClass( moduleChilds, "Ouch" );
			{
				IModelElement[] classOuch38Childs = classOuch38.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classOuch38Childs, "n");
			}
			//Function test:__del__
			{
			IMethod method__del__39;
				IModelElement[] classOuch38Childs = classOuch38.getChildren();
				method__del__39 = ModelTestUtils.getAssertMethod( classOuch38Childs, "__del__", 1 );
				ModelTestUtils.assertParameterNames( method__del__39, new String[] {"self"} );
			}
		}
		//Function test:test_trashcan
		{
		IMethod methodtest_trashcan40;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_trashcan40 = ModelTestUtils.getAssertMethod( moduleChilds, "test_trashcan", 0 );
		}
		//Class test
		{
		IType classBoom41;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom41 = ModelTestUtils.getAssertClass( moduleChilds, "Boom" );
			//Function test:__getattr__
			{
			IMethod method__getattr__42;
				IModelElement[] classBoom41Childs = classBoom41.getChildren();
				method__getattr__42 = ModelTestUtils.getAssertMethod( classBoom41Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__42, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom
		{
		IMethod methodtest_boom43;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom43 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom", 0 );
		}
		//Class test
		{
		IType classBoom244;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom244 = ModelTestUtils.getAssertClass( moduleChilds, "Boom2" );
			//Function test:__init__
			{
			IMethod method__init__45;
				IModelElement[] classBoom244Childs = classBoom244.getChildren();
				method__init__45 = ModelTestUtils.getAssertMethod( classBoom244Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__45, new String[] {"self"} );
			}
			{
				IModelElement[] classBoom244Childs = classBoom244.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBoom244Childs, "x");
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__47;
				IModelElement[] classBoom244Childs = classBoom244.getChildren();
				method__getattr__47 = ModelTestUtils.getAssertMethod( classBoom244Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__47, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom2
		{
		IMethod methodtest_boom248;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom248 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom2", 0 );
		}
		//Class test
		{
		IType classBoom_New49;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom_New49 = ModelTestUtils.getAssertClass( moduleChilds, "Boom_New" );
			//Function test:__getattr__
			{
			IMethod method__getattr__50;
				IModelElement[] classBoom_New49Childs = classBoom_New49.getChildren();
				method__getattr__50 = ModelTestUtils.getAssertMethod( classBoom_New49Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__50, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom_new
		{
		IMethod methodtest_boom_new51;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom_new51 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom_new", 0 );
		}
		//Class test
		{
		IType classBoom2_New52;
			IModelElement[] moduleChilds = module.getChildren();
			classBoom2_New52 = ModelTestUtils.getAssertClass( moduleChilds, "Boom2_New" );
			//Function test:__init__
			{
			IMethod method__init__53;
				IModelElement[] classBoom2_New52Childs = classBoom2_New52.getChildren();
				method__init__53 = ModelTestUtils.getAssertMethod( classBoom2_New52Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__53, new String[] {"self"} );
			}
			//Function test:__getattr__
			{
			IMethod method__getattr__54;
				IModelElement[] classBoom2_New52Childs = classBoom2_New52.getChildren();
				method__getattr__54 = ModelTestUtils.getAssertMethod( classBoom2_New52Childs, "__getattr__", 2 );
				ModelTestUtils.assertParameterNames( method__getattr__54, new String[] {"self", "someattribute"} );
			}
		}
		//Function test:test_boom2_new
		{
		IMethod methodtest_boom2_new55;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_boom2_new55 = ModelTestUtils.getAssertMethod( moduleChilds, "test_boom2_new", 0 );
		}
		//Function test:test_get_referents
		{
		IMethod methodtest_get_referents56;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_get_referents56 = ModelTestUtils.getAssertMethod( moduleChilds, "test_get_referents", 0 );
		}
		//Class test
		{
		IType classC105582057;
			IModelElement[] moduleChilds = module.getChildren();
			classC105582057 = ModelTestUtils.getAssertClass( moduleChilds, "C1055820" );
			//Function test:__init__
			{
			IMethod method__init__58;
				IModelElement[] classC105582057Childs = classC105582057.getChildren();
				method__init__58 = ModelTestUtils.getAssertMethod( classC105582057Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__58, new String[] {"self", "i"} );
			}
			{
				IModelElement[] classC105582057Childs = classC105582057.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC105582057Childs, "i");
			}
			{
				IModelElement[] classC105582057Childs = classC105582057.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC105582057Childs, "loop");
			}
		}
		//Class test
		{
		IType classGC_Detector60;
			IModelElement[] moduleChilds = module.getChildren();
			classGC_Detector60 = ModelTestUtils.getAssertClass( moduleChilds, "GC_Detector" );
			//Function test:__init__
			{
			IMethod method__init__61;
				IModelElement[] classGC_Detector60Childs = classGC_Detector60.getChildren();
				method__init__61 = ModelTestUtils.getAssertMethod( classGC_Detector60Childs, "__init__", 1 );
				ModelTestUtils.assertParameterNames( method__init__61, new String[] {"self"} );
				//Function test:it_happened
				{
				IMethod methodit_happened62;
					IModelElement[] method__init__61Childs = method__init__61.getChildren();
					methodit_happened62 = ModelTestUtils.getAssertMethod( method__init__61Childs, "it_happened", 1 );
					ModelTestUtils.assertParameterNames( methodit_happened62, new String[] {"ignored"} );
				}
				{
					IModelElement[] method__init__61Childs = method__init__61.getChildren();
					IField fieldValue = ModelTestUtils.getAssertField( method__init__61Childs, "gc_happened");
				}
			}
			{
				IModelElement[] classGC_Detector60Childs = classGC_Detector60.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classGC_Detector60Childs, "wr");
			}
		}
		//Function test:test_bug1055820b
		{
		IMethod methodtest_bug1055820b65;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bug1055820b65 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bug1055820b", 0 );
			//Function test:callback
			{
			IMethod methodcallback66;
				IModelElement[] methodtest_bug1055820b65Childs = methodtest_bug1055820b65.getChildren();
				methodcallback66 = ModelTestUtils.getAssertMethod( methodtest_bug1055820b65Childs, "callback", 1 );
				ModelTestUtils.assertParameterNames( methodcallback66, new String[] {"ignored"} );
			}
		}
		//Function test:test_bug1055820c
		{
		IMethod methodtest_bug1055820c67;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bug1055820c67 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bug1055820c", 0 );
			//Function test:callback
			{
			IMethod methodcallback68;
				IModelElement[] methodtest_bug1055820c67Childs = methodtest_bug1055820c67.getChildren();
				methodcallback68 = ModelTestUtils.getAssertMethod( methodtest_bug1055820c67Childs, "callback", 1 );
				ModelTestUtils.assertParameterNames( methodcallback68, new String[] {"ignored"} );
			}
		}
		//Function test:test_bug1055820d
		{
		IMethod methodtest_bug1055820d69;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_bug1055820d69 = ModelTestUtils.getAssertMethod( moduleChilds, "test_bug1055820d", 0 );
			//Class test
			{
			IType classD70;
				IModelElement[] methodtest_bug1055820d69Childs = methodtest_bug1055820d69.getChildren();
				classD70 = ModelTestUtils.getAssertClass( methodtest_bug1055820d69Childs, "D" );
				//Function test:__del__
				{
				IMethod method__del__71;
					IModelElement[] classD70Childs = classD70.getChildren();
					method__del__71 = ModelTestUtils.getAssertMethod( classD70Childs, "__del__", 1 );
					ModelTestUtils.assertParameterNames( method__del__71, new String[] {"self"} );
				}
			}
		}
		//Function test:test_all
		{
		IMethod methodtest_all72;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_all72 = ModelTestUtils.getAssertMethod( moduleChilds, "test_all", 0 );
		}
		//Function test:test
		{
		IMethod methodtest73;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest73 = ModelTestUtils.getAssertMethod( moduleChilds, "test", 0 );
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
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "_x");
			}
			{
				IModelElement[] classC0Childs = classC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "x");
			}
			//Function test:statm
			{
			IMethod methodstatm8;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodstatm8 = ModelTestUtils.getAssertMethod( classC0Childs, "statm", 0 );
			}
			{
				IModelElement[] classC0Childs = classC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "statm");
			}
			//Function test:clsm
			{
			IMethod methodclsm9;
				IModelElement[] classC0Childs = classC0.getChildren();
				methodclsm9 = ModelTestUtils.getAssertMethod( classC0Childs, "clsm", 2 );
				ModelTestUtils.assertParameterNames( methodclsm9, new String[] {"cls", "val"} );
			}
			{
				IModelElement[] classC0Childs = classC0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classC0Childs, "clsm");
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main10;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main10 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classG0Childs = classG0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classG0Childs, "seqn");
			}
			//Function test:__getitem__
			{
			IMethod method__getitem__3;
				IModelElement[] classG0Childs = classG0.getChildren();
				method__getitem__3 = ModelTestUtils.getAssertMethod( classG0Childs, "__getitem__", 2 );
				ModelTestUtils.assertParameterNames( method__getitem__3, new String[] {"self", "i"} );
			}
		}
		//Class test
		{
		IType classI4;
			IModelElement[] moduleChilds = module.getChildren();
			classI4 = ModelTestUtils.getAssertClass( moduleChilds, "I" );
			//Function test:__init__
			{
			IMethod method__init__5;
				IModelElement[] classI4Childs = classI4.getChildren();
				method__init__5 = ModelTestUtils.getAssertMethod( classI4Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__5, new String[] {"self", "seqn"} );
			}
			{
				IModelElement[] classI4Childs = classI4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI4Childs, "seqn");
			}
			{
				IModelElement[] classI4Childs = classI4.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classI4Childs, "i");
			}
			//Function test:__iter__
			{
			IMethod method__iter__7;
				IModelElement[] classI4Childs = classI4.getChildren();
				method__iter__7 = ModelTestUtils.getAssertMethod( classI4Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__7, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext8;
				IModelElement[] classI4Childs = classI4.getChildren();
				methodnext8 = ModelTestUtils.getAssertMethod( classI4Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext8, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classIg9;
			IModelElement[] moduleChilds = module.getChildren();
			classIg9 = ModelTestUtils.getAssertClass( moduleChilds, "Ig" );
			//Function test:__init__
			{
			IMethod method__init__10;
				IModelElement[] classIg9Childs = classIg9.getChildren();
				method__init__10 = ModelTestUtils.getAssertMethod( classIg9Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__10, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__11;
				IModelElement[] classIg9Childs = classIg9.getChildren();
				method__iter__11 = ModelTestUtils.getAssertMethod( classIg9Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__11, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classX12;
			IModelElement[] moduleChilds = module.getChildren();
			classX12 = ModelTestUtils.getAssertClass( moduleChilds, "X" );
			//Function test:__init__
			{
			IMethod method__init__13;
				IModelElement[] classX12Childs = classX12.getChildren();
				method__init__13 = ModelTestUtils.getAssertMethod( classX12Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__13, new String[] {"self", "seqn"} );
			}
			//Function test:next
			{
			IMethod methodnext14;
				IModelElement[] classX12Childs = classX12.getChildren();
				methodnext14 = ModelTestUtils.getAssertMethod( classX12Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext14, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classE15;
			IModelElement[] moduleChilds = module.getChildren();
			classE15 = ModelTestUtils.getAssertClass( moduleChilds, "E" );
			//Function test:__init__
			{
			IMethod method__init__16;
				IModelElement[] classE15Childs = classE15.getChildren();
				method__init__16 = ModelTestUtils.getAssertMethod( classE15Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__16, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__17;
				IModelElement[] classE15Childs = classE15.getChildren();
				method__iter__17 = ModelTestUtils.getAssertMethod( classE15Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__17, new String[] {"self"} );
			}
			//Function test:next
			{
			IMethod methodnext18;
				IModelElement[] classE15Childs = classE15.getChildren();
				methodnext18 = ModelTestUtils.getAssertMethod( classE15Childs, "next", 1 );
				ModelTestUtils.assertParameterNames( methodnext18, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classN19;
			IModelElement[] moduleChilds = module.getChildren();
			classN19 = ModelTestUtils.getAssertClass( moduleChilds, "N" );
			//Function test:__init__
			{
			IMethod method__init__20;
				IModelElement[] classN19Childs = classN19.getChildren();
				method__init__20 = ModelTestUtils.getAssertMethod( classN19Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__20, new String[] {"self", "seqn"} );
			}
			//Function test:__iter__
			{
			IMethod method__iter__21;
				IModelElement[] classN19Childs = classN19.getChildren();
				method__iter__21 = ModelTestUtils.getAssertMethod( classN19Childs, "__iter__", 1 );
				ModelTestUtils.assertParameterNames( method__iter__21, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEnumerateTestCase22;
			IModelElement[] moduleChilds = module.getChildren();
			classEnumerateTestCase22 = ModelTestUtils.getAssertClass( moduleChilds, "EnumerateTestCase" );
			{
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEnumerateTestCase22Childs, "enum");
			}
			//Function test:test_basicfunction
			{
			IMethod methodtest_basicfunction23;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_basicfunction23 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_basicfunction", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basicfunction23, new String[] {"self"} );
			}
			//Function test:test_getitemseqn
			{
			IMethod methodtest_getitemseqn24;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_getitemseqn24 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_getitemseqn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitemseqn24, new String[] {"self"} );
			}
			//Function test:test_iteratorseqn
			{
			IMethod methodtest_iteratorseqn25;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_iteratorseqn25 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_iteratorseqn", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteratorseqn25, new String[] {"self"} );
			}
			//Function test:test_iteratorgenerator
			{
			IMethod methodtest_iteratorgenerator26;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_iteratorgenerator26 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_iteratorgenerator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iteratorgenerator26, new String[] {"self"} );
			}
			//Function test:test_noniterable
			{
			IMethod methodtest_noniterable27;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_noniterable27 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_noniterable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_noniterable27, new String[] {"self"} );
			}
			//Function test:test_illformediterable
			{
			IMethod methodtest_illformediterable28;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_illformediterable28 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_illformediterable", 1 );
				ModelTestUtils.assertParameterNames( methodtest_illformediterable28, new String[] {"self"} );
			}
			//Function test:test_exception_propagation
			{
			IMethod methodtest_exception_propagation29;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_exception_propagation29 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_exception_propagation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_propagation29, new String[] {"self"} );
			}
			//Function test:test_argumentcheck
			{
			IMethod methodtest_argumentcheck30;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_argumentcheck30 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_argumentcheck", 1 );
				ModelTestUtils.assertParameterNames( methodtest_argumentcheck30, new String[] {"self"} );
			}
			//Function test:test_tuple_reuse
			{
			IMethod methodtest_tuple_reuse31;
				IModelElement[] classEnumerateTestCase22Childs = classEnumerateTestCase22.getChildren();
				methodtest_tuple_reuse31 = ModelTestUtils.getAssertMethod( classEnumerateTestCase22Childs, "test_tuple_reuse", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tuple_reuse31, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMyEnum32;
			IModelElement[] moduleChilds = module.getChildren();
			classMyEnum32 = ModelTestUtils.getAssertClass( moduleChilds, "MyEnum" );
		}
		//Class test
		{
		IType classSubclassTestCase33;
			IModelElement[] moduleChilds = module.getChildren();
			classSubclassTestCase33 = ModelTestUtils.getAssertClass( moduleChilds, "SubclassTestCase" );
			{
				IModelElement[] classSubclassTestCase33Childs = classSubclassTestCase33.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classSubclassTestCase33Childs, "enum");
			}
		}
		//Class test
		{
		IType classTestEmpty34;
			IModelElement[] moduleChilds = module.getChildren();
			classTestEmpty34 = ModelTestUtils.getAssertClass( moduleChilds, "TestEmpty" );
		}
		//Class test
		{
		IType classTestBig35;
			IModelElement[] moduleChilds = module.getChildren();
			classTestBig35 = ModelTestUtils.getAssertClass( moduleChilds, "TestBig" );
			{
				IModelElement[] classTestBig35Childs = classTestBig35.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig35Childs, "seq");
			}
			{
				IModelElement[] classTestBig35Childs = classTestBig35.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTestBig35Childs, "res");
			}
		}
		//Class test
		{
		IType classTestReversed36;
			IModelElement[] moduleChilds = module.getChildren();
			classTestReversed36 = ModelTestUtils.getAssertClass( moduleChilds, "TestReversed" );
			//Function test:test_simple
			{
			IMethod methodtest_simple37;
				IModelElement[] classTestReversed36Childs = classTestReversed36.getChildren();
				methodtest_simple37 = ModelTestUtils.getAssertMethod( classTestReversed36Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple37, new String[] {"self"} );
				//Class test
				{
				IType classA38;
					IModelElement[] methodtest_simple37Childs = methodtest_simple37.getChildren();
					classA38 = ModelTestUtils.getAssertClass( methodtest_simple37Childs, "A" );
					//Function test:__getitem__
					{
					IMethod method__getitem__39;
						IModelElement[] classA38Childs = classA38.getChildren();
						method__getitem__39 = ModelTestUtils.getAssertMethod( classA38Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__39, new String[] {"self", "i"} );
					}
					//Function test:__len__
					{
					IMethod method__len__40;
						IModelElement[] classA38Childs = classA38.getChildren();
						method__len__40 = ModelTestUtils.getAssertMethod( classA38Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__40, new String[] {"self"} );
					}
				}
			}
			//Function test:test_xrange_optimization
			{
			IMethod methodtest_xrange_optimization41;
				IModelElement[] classTestReversed36Childs = classTestReversed36.getChildren();
				methodtest_xrange_optimization41 = ModelTestUtils.getAssertMethod( classTestReversed36Childs, "test_xrange_optimization", 1 );
				ModelTestUtils.assertParameterNames( methodtest_xrange_optimization41, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len42;
				IModelElement[] classTestReversed36Childs = classTestReversed36.getChildren();
				methodtest_len42 = ModelTestUtils.getAssertMethod( classTestReversed36Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len42, new String[] {"self"} );
				//Class test
				{
				IType classSeqWithWeirdLen43;
					IModelElement[] methodtest_len42Childs = methodtest_len42.getChildren();
					classSeqWithWeirdLen43 = ModelTestUtils.getAssertClass( methodtest_len42Childs, "SeqWithWeirdLen" );
					//Function test:__len__
					{
					IMethod method__len__44;
						IModelElement[] classSeqWithWeirdLen43Childs = classSeqWithWeirdLen43.getChildren();
						method__len__44 = ModelTestUtils.getAssertMethod( classSeqWithWeirdLen43Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__44, new String[] {"self"} );
					}
					{
						IModelElement[] classSeqWithWeirdLen43Childs = classSeqWithWeirdLen43.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classSeqWithWeirdLen43Childs, "called");
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__46;
						IModelElement[] classSeqWithWeirdLen43Childs = classSeqWithWeirdLen43.getChildren();
						method__getitem__46 = ModelTestUtils.getAssertMethod( classSeqWithWeirdLen43Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__46, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_gc
			{
			IMethod methodtest_gc47;
				IModelElement[] classTestReversed36Childs = classTestReversed36.getChildren();
				methodtest_gc47 = ModelTestUtils.getAssertMethod( classTestReversed36Childs, "test_gc", 1 );
				ModelTestUtils.assertParameterNames( methodtest_gc47, new String[] {"self"} );
				//Class test
				{
				IType classSeq48;
					IModelElement[] methodtest_gc47Childs = methodtest_gc47.getChildren();
					classSeq48 = ModelTestUtils.getAssertClass( methodtest_gc47Childs, "Seq" );
					//Function test:__len__
					{
					IMethod method__len__49;
						IModelElement[] classSeq48Childs = classSeq48.getChildren();
						method__len__49 = ModelTestUtils.getAssertMethod( classSeq48Childs, "__len__", 1 );
						ModelTestUtils.assertParameterNames( method__len__49, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__50;
						IModelElement[] classSeq48Childs = classSeq48.getChildren();
						method__getitem__50 = ModelTestUtils.getAssertMethod( classSeq48Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__50, new String[] {"self", "index"} );
					}
				}
			}
			//Function test:test_args
			{
			IMethod methodtest_args51;
				IModelElement[] classTestReversed36Childs = classTestReversed36.getChildren();
				methodtest_args51 = ModelTestUtils.getAssertMethod( classTestReversed36Childs, "test_args", 1 );
				ModelTestUtils.assertParameterNames( methodtest_args51, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main52;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main52 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 1 );
			ModelTestUtils.assertParameterNames( methodtest_main52, new String[] {"verbose"} );
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
			{
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classTemporaryFileTests0Childs, "files");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:check_tempfile
			{
			IMethod methodcheck_tempfile4;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodcheck_tempfile4 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "check_tempfile", 2 );
				ModelTestUtils.assertParameterNames( methodcheck_tempfile4, new String[] {"self", "name"} );
			}
			//Function test:test_tempnam
			{
			IMethod methodtest_tempnam5;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtest_tempnam5 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "test_tempnam", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tempnam5, new String[] {"self"} );
			}
			//Function test:test_tmpfile
			{
			IMethod methodtest_tmpfile6;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtest_tmpfile6 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "test_tmpfile", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tmpfile6, new String[] {"self"} );
			}
			//Function test:test_tmpnam
			{
			IMethod methodtest_tmpnam7;
				IModelElement[] classTemporaryFileTests0Childs = classTemporaryFileTests0.getChildren();
				methodtest_tmpnam7 = ModelTestUtils.getAssertMethod( classTemporaryFileTests0Childs, "test_tmpnam", 1 );
				ModelTestUtils.assertParameterNames( methodtest_tmpnam7, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classStatAttributeTests8;
			IModelElement[] moduleChilds = module.getChildren();
			classStatAttributeTests8 = ModelTestUtils.getAssertClass( moduleChilds, "StatAttributeTests" );
			//Function test:setUp
			{
			IMethod methodsetUp9;
				IModelElement[] classStatAttributeTests8Childs = classStatAttributeTests8.getChildren();
				methodsetUp9 = ModelTestUtils.getAssertMethod( classStatAttributeTests8Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp9, new String[] {"self"} );
			}
			{
				IModelElement[] classStatAttributeTests8Childs = classStatAttributeTests8.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classStatAttributeTests8Childs, "fname");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown11;
				IModelElement[] classStatAttributeTests8Childs = classStatAttributeTests8.getChildren();
				methodtearDown11 = ModelTestUtils.getAssertMethod( classStatAttributeTests8Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown11, new String[] {"self"} );
			}
			//Function test:test_stat_attributes
			{
			IMethod methodtest_stat_attributes12;
				IModelElement[] classStatAttributeTests8Childs = classStatAttributeTests8.getChildren();
				methodtest_stat_attributes12 = ModelTestUtils.getAssertMethod( classStatAttributeTests8Childs, "test_stat_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stat_attributes12, new String[] {"self"} );
			}
			//Function test:test_statvfs_attributes
			{
			IMethod methodtest_statvfs_attributes13;
				IModelElement[] classStatAttributeTests8Childs = classStatAttributeTests8.getChildren();
				methodtest_statvfs_attributes13 = ModelTestUtils.getAssertMethod( classStatAttributeTests8Childs, "test_statvfs_attributes", 1 );
				ModelTestUtils.assertParameterNames( methodtest_statvfs_attributes13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classEnvironTests14;
			IModelElement[] moduleChilds = module.getChildren();
			classEnvironTests14 = ModelTestUtils.getAssertClass( moduleChilds, "EnvironTests" );
			{
				IModelElement[] classEnvironTests14Childs = classEnvironTests14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEnvironTests14Childs, "type2test");
			}
			//Function test:_reference
			{
			IMethod method_reference15;
				IModelElement[] classEnvironTests14Childs = classEnvironTests14.getChildren();
				method_reference15 = ModelTestUtils.getAssertMethod( classEnvironTests14Childs, "_reference", 1 );
				ModelTestUtils.assertParameterNames( method_reference15, new String[] {"self"} );
			}
			//Function test:_empty_mapping
			{
			IMethod method_empty_mapping16;
				IModelElement[] classEnvironTests14Childs = classEnvironTests14.getChildren();
				method_empty_mapping16 = ModelTestUtils.getAssertMethod( classEnvironTests14Childs, "_empty_mapping", 1 );
				ModelTestUtils.assertParameterNames( method_empty_mapping16, new String[] {"self"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp17;
				IModelElement[] classEnvironTests14Childs = classEnvironTests14.getChildren();
				methodsetUp17 = ModelTestUtils.getAssertMethod( classEnvironTests14Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp17, new String[] {"self"} );
			}
			{
				IModelElement[] classEnvironTests14Childs = classEnvironTests14.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classEnvironTests14Childs, "__save");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown19;
				IModelElement[] classEnvironTests14Childs = classEnvironTests14.getChildren();
				methodtearDown19 = ModelTestUtils.getAssertMethod( classEnvironTests14Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown19, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classWalkTests20;
			IModelElement[] moduleChilds = module.getChildren();
			classWalkTests20 = ModelTestUtils.getAssertClass( moduleChilds, "WalkTests" );
			//Function test:test_traversal
			{
			IMethod methodtest_traversal21;
				IModelElement[] classWalkTests20Childs = classWalkTests20.getChildren();
				methodtest_traversal21 = ModelTestUtils.getAssertMethod( classWalkTests20Childs, "test_traversal", 1 );
				ModelTestUtils.assertParameterNames( methodtest_traversal21, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classMakedirTests22;
			IModelElement[] moduleChilds = module.getChildren();
			classMakedirTests22 = ModelTestUtils.getAssertClass( moduleChilds, "MakedirTests" );
			//Function test:setUp
			{
			IMethod methodsetUp23;
				IModelElement[] classMakedirTests22Childs = classMakedirTests22.getChildren();
				methodsetUp23 = ModelTestUtils.getAssertMethod( classMakedirTests22Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp23, new String[] {"self"} );
			}
			//Function test:test_makedir
			{
			IMethod methodtest_makedir24;
				IModelElement[] classMakedirTests22Childs = classMakedirTests22.getChildren();
				methodtest_makedir24 = ModelTestUtils.getAssertMethod( classMakedirTests22Childs, "test_makedir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_makedir24, new String[] {"self"} );
			}
			//Function test:tearDown
			{
			IMethod methodtearDown25;
				IModelElement[] classMakedirTests22Childs = classMakedirTests22.getChildren();
				methodtearDown25 = ModelTestUtils.getAssertMethod( classMakedirTests22Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown25, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classDevNullTests26;
			IModelElement[] moduleChilds = module.getChildren();
			classDevNullTests26 = ModelTestUtils.getAssertClass( moduleChilds, "DevNullTests" );
			//Function test:test_devnull
			{
			IMethod methodtest_devnull27;
				IModelElement[] classDevNullTests26Childs = classDevNullTests26.getChildren();
				methodtest_devnull27 = ModelTestUtils.getAssertMethod( classDevNullTests26Childs, "test_devnull", 1 );
				ModelTestUtils.assertParameterNames( methodtest_devnull27, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classURandomTests28;
			IModelElement[] moduleChilds = module.getChildren();
			classURandomTests28 = ModelTestUtils.getAssertClass( moduleChilds, "URandomTests" );
			//Function test:test_urandom
			{
			IMethod methodtest_urandom29;
				IModelElement[] classURandomTests28Childs = classURandomTests28.getChildren();
				methodtest_urandom29 = ModelTestUtils.getAssertMethod( classURandomTests28Childs, "test_urandom", 1 );
				ModelTestUtils.assertParameterNames( methodtest_urandom29, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main30;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main30 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHookWatcher0Childs, "frames");
			}
			{
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHookWatcher0Childs, "events");
			}
			//Function test:callback
			{
			IMethod methodcallback3;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				methodcallback3 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "callback", 4 );
				ModelTestUtils.assertParameterNames( methodcallback3, new String[] {"self", "frame", "event", "arg"} );
			}
			//Function test:add_event
			{
			IMethod methodadd_event4;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				methodadd_event4 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "add_event", 3 );
				ModelTestUtils.assertParameterNames( methodadd_event4, new String[] {"self", "event", "frame"} );
			}
			//Function test:get_events
			{
			IMethod methodget_events5;
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				methodget_events5 = ModelTestUtils.getAssertMethod( classHookWatcher0Childs, "get_events", 1 );
				ModelTestUtils.assertParameterNames( methodget_events5, new String[] {"self"} );
			}
			{
				IModelElement[] classHookWatcher0Childs = classHookWatcher0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classHookWatcher0Childs, "frames");
			}
		}
		//Class test
		{
		IType classProfileSimulator7;
			IModelElement[] moduleChilds = module.getChildren();
			classProfileSimulator7 = ModelTestUtils.getAssertClass( moduleChilds, "ProfileSimulator" );
			//Function test:__init__
			{
			IMethod method__init__8;
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				method__init__8 = ModelTestUtils.getAssertMethod( classProfileSimulator7Childs, "__init__", 2 );
				ModelTestUtils.assertParameterNames( method__init__8, new String[] {"self", "testcase"} );
			}
			{
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classProfileSimulator7Childs, "testcase");
			}
			{
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classProfileSimulator7Childs, "stack");
			}
			//Function test:callback
			{
			IMethod methodcallback10;
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				methodcallback10 = ModelTestUtils.getAssertMethod( classProfileSimulator7Childs, "callback", 4 );
				ModelTestUtils.assertParameterNames( methodcallback10, new String[] {"self", "frame", "event", "arg"} );
			}
			//Function test:trace_call
			{
			IMethod methodtrace_call11;
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				methodtrace_call11 = ModelTestUtils.getAssertMethod( classProfileSimulator7Childs, "trace_call", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_call11, new String[] {"self", "frame"} );
			}
			//Function test:trace_return
			{
			IMethod methodtrace_return12;
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				methodtrace_return12 = ModelTestUtils.getAssertMethod( classProfileSimulator7Childs, "trace_return", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_return12, new String[] {"self", "frame"} );
			}
			//Function test:trace_exception
			{
			IMethod methodtrace_exception13;
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				methodtrace_exception13 = ModelTestUtils.getAssertMethod( classProfileSimulator7Childs, "trace_exception", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_exception13, new String[] {"self", "frame"} );
			}
			//Function test:trace_pass
			{
			IMethod methodtrace_pass14;
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				methodtrace_pass14 = ModelTestUtils.getAssertMethod( classProfileSimulator7Childs, "trace_pass", 2 );
				ModelTestUtils.assertParameterNames( methodtrace_pass14, new String[] {"self", "frame"} );
			}
			{
				IModelElement[] classProfileSimulator7Childs = classProfileSimulator7.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classProfileSimulator7Childs, "dispatch");
			}
		}
		//Class test
		{
		IType classTestCaseBase15;
			IModelElement[] moduleChilds = module.getChildren();
			classTestCaseBase15 = ModelTestUtils.getAssertClass( moduleChilds, "TestCaseBase" );
			//Function test:check_events
			{
			IMethod methodcheck_events16;
				IModelElement[] classTestCaseBase15Childs = classTestCaseBase15.getChildren();
				methodcheck_events16 = ModelTestUtils.getAssertMethod( classTestCaseBase15Childs, "check_events", 3 );
				ModelTestUtils.assertParameterNames( methodcheck_events16, new String[] {"self", "callable", "expected"} );
			}
		}
		//Class test
		{
		IType classProfileHookTestCase17;
			IModelElement[] moduleChilds = module.getChildren();
			classProfileHookTestCase17 = ModelTestUtils.getAssertClass( moduleChilds, "ProfileHookTestCase" );
			//Function test:new_watcher
			{
			IMethod methodnew_watcher18;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodnew_watcher18 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "new_watcher", 1 );
				ModelTestUtils.assertParameterNames( methodnew_watcher18, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple19;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_simple19 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple19, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf20;
					IModelElement[] methodtest_simple19Childs = methodtest_simple19.getChildren();
					methodf20 = ModelTestUtils.getAssertMethod( methodtest_simple19Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf20, new String[] {"p"} );
				}
			}
			//Function test:test_exception
			{
			IMethod methodtest_exception21;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_exception21 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception21, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf22;
					IModelElement[] methodtest_exception21Childs = methodtest_exception21.getChildren();
					methodf22 = ModelTestUtils.getAssertMethod( methodtest_exception21Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf22, new String[] {"p"} );
				}
			}
			//Function test:test_caught_exception
			{
			IMethod methodtest_caught_exception23;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_caught_exception23 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_caught_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caught_exception23, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf24;
					IModelElement[] methodtest_caught_exception23Childs = methodtest_caught_exception23.getChildren();
					methodf24 = ModelTestUtils.getAssertMethod( methodtest_caught_exception23Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf24, new String[] {"p"} );
				}
			}
			//Function test:test_caught_nested_exception
			{
			IMethod methodtest_caught_nested_exception25;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_caught_nested_exception25 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_caught_nested_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caught_nested_exception25, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf26;
					IModelElement[] methodtest_caught_nested_exception25Childs = methodtest_caught_nested_exception25.getChildren();
					methodf26 = ModelTestUtils.getAssertMethod( methodtest_caught_nested_exception25Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf26, new String[] {"p"} );
				}
			}
			//Function test:test_nested_exception
			{
			IMethod methodtest_nested_exception27;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_nested_exception27 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_nested_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_nested_exception27, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf28;
					IModelElement[] methodtest_nested_exception27Childs = methodtest_nested_exception27.getChildren();
					methodf28 = ModelTestUtils.getAssertMethod( methodtest_nested_exception27Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf28, new String[] {"p"} );
				}
			}
			//Function test:test_exception_in_except_clause
			{
			IMethod methodtest_exception_in_except_clause29;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_exception_in_except_clause29 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_exception_in_except_clause", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_in_except_clause29, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf30;
					IModelElement[] methodtest_exception_in_except_clause29Childs = methodtest_exception_in_except_clause29.getChildren();
					methodf30 = ModelTestUtils.getAssertMethod( methodtest_exception_in_except_clause29Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf30, new String[] {"p"} );
				}
				//Function test:g
				{
				IMethod methodg31;
					IModelElement[] methodtest_exception_in_except_clause29Childs = methodtest_exception_in_except_clause29.getChildren();
					methodg31 = ModelTestUtils.getAssertMethod( methodtest_exception_in_except_clause29Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg31, new String[] {"p"} );
				}
			}
			//Function test:test_exception_propogation
			{
			IMethod methodtest_exception_propogation32;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_exception_propogation32 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_exception_propogation", 1 );
				ModelTestUtils.assertParameterNames( methodtest_exception_propogation32, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf33;
					IModelElement[] methodtest_exception_propogation32Childs = methodtest_exception_propogation32.getChildren();
					methodf33 = ModelTestUtils.getAssertMethod( methodtest_exception_propogation32Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf33, new String[] {"p"} );
				}
				//Function test:g
				{
				IMethod methodg34;
					IModelElement[] methodtest_exception_propogation32Childs = methodtest_exception_propogation32.getChildren();
					methodg34 = ModelTestUtils.getAssertMethod( methodtest_exception_propogation32Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg34, new String[] {"p"} );
				}
			}
			//Function test:test_raise_twice
			{
			IMethod methodtest_raise_twice35;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_raise_twice35 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_raise_twice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise_twice35, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf36;
					IModelElement[] methodtest_raise_twice35Childs = methodtest_raise_twice35.getChildren();
					methodf36 = ModelTestUtils.getAssertMethod( methodtest_raise_twice35Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf36, new String[] {"p"} );
				}
			}
			//Function test:test_raise_reraise
			{
			IMethod methodtest_raise_reraise37;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_raise_reraise37 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_raise_reraise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise_reraise37, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf38;
					IModelElement[] methodtest_raise_reraise37Childs = methodtest_raise_reraise37.getChildren();
					methodf38 = ModelTestUtils.getAssertMethod( methodtest_raise_reraise37Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf38, new String[] {"p"} );
				}
			}
			//Function test:test_raise
			{
			IMethod methodtest_raise39;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_raise39 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_raise", 1 );
				ModelTestUtils.assertParameterNames( methodtest_raise39, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf40;
					IModelElement[] methodtest_raise39Childs = methodtest_raise39.getChildren();
					methodf40 = ModelTestUtils.getAssertMethod( methodtest_raise39Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf40, new String[] {"p"} );
				}
			}
			//Function test:test_distant_exception
			{
			IMethod methodtest_distant_exception41;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_distant_exception41 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_distant_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_distant_exception41, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf42;
					IModelElement[] methodtest_distant_exception41Childs = methodtest_distant_exception41.getChildren();
					methodf42 = ModelTestUtils.getAssertMethod( methodtest_distant_exception41Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg43;
					IModelElement[] methodtest_distant_exception41Childs = methodtest_distant_exception41.getChildren();
					methodg43 = ModelTestUtils.getAssertMethod( methodtest_distant_exception41Childs, "g", 0 );
				}
				//Function test:h
				{
				IMethod methodh44;
					IModelElement[] methodtest_distant_exception41Childs = methodtest_distant_exception41.getChildren();
					methodh44 = ModelTestUtils.getAssertMethod( methodtest_distant_exception41Childs, "h", 0 );
				}
				//Function test:i
				{
				IMethod methodi45;
					IModelElement[] methodtest_distant_exception41Childs = methodtest_distant_exception41.getChildren();
					methodi45 = ModelTestUtils.getAssertMethod( methodtest_distant_exception41Childs, "i", 0 );
				}
				//Function test:j
				{
				IMethod methodj46;
					IModelElement[] methodtest_distant_exception41Childs = methodtest_distant_exception41.getChildren();
					methodj46 = ModelTestUtils.getAssertMethod( methodtest_distant_exception41Childs, "j", 1 );
					ModelTestUtils.assertParameterNames( methodj46, new String[] {"p"} );
				}
			}
			//Function test:test_generator
			{
			IMethod methodtest_generator47;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_generator47 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_generator", 1 );
				ModelTestUtils.assertParameterNames( methodtest_generator47, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf48;
					IModelElement[] methodtest_generator47Childs = methodtest_generator47.getChildren();
					methodf48 = ModelTestUtils.getAssertMethod( methodtest_generator47Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg49;
					IModelElement[] methodtest_generator47Childs = methodtest_generator47.getChildren();
					methodg49 = ModelTestUtils.getAssertMethod( methodtest_generator47Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg49, new String[] {"p"} );
				}
			}
			//Function test:test_stop_iteration
			{
			IMethod methodtest_stop_iteration50;
				IModelElement[] classProfileHookTestCase17Childs = classProfileHookTestCase17.getChildren();
				methodtest_stop_iteration50 = ModelTestUtils.getAssertMethod( classProfileHookTestCase17Childs, "test_stop_iteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_stop_iteration50, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf51;
					IModelElement[] methodtest_stop_iteration50Childs = methodtest_stop_iteration50.getChildren();
					methodf51 = ModelTestUtils.getAssertMethod( methodtest_stop_iteration50Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg52;
					IModelElement[] methodtest_stop_iteration50Childs = methodtest_stop_iteration50.getChildren();
					methodg52 = ModelTestUtils.getAssertMethod( methodtest_stop_iteration50Childs, "g", 1 );
					ModelTestUtils.assertParameterNames( methodg52, new String[] {"p"} );
				}
			}
		}
		//Class test
		{
		IType classProfileSimulatorTestCase53;
			IModelElement[] moduleChilds = module.getChildren();
			classProfileSimulatorTestCase53 = ModelTestUtils.getAssertClass( moduleChilds, "ProfileSimulatorTestCase" );
			//Function test:new_watcher
			{
			IMethod methodnew_watcher54;
				IModelElement[] classProfileSimulatorTestCase53Childs = classProfileSimulatorTestCase53.getChildren();
				methodnew_watcher54 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase53Childs, "new_watcher", 1 );
				ModelTestUtils.assertParameterNames( methodnew_watcher54, new String[] {"self"} );
			}
			//Function test:test_simple
			{
			IMethod methodtest_simple55;
				IModelElement[] classProfileSimulatorTestCase53Childs = classProfileSimulatorTestCase53.getChildren();
				methodtest_simple55 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase53Childs, "test_simple", 1 );
				ModelTestUtils.assertParameterNames( methodtest_simple55, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf56;
					IModelElement[] methodtest_simple55Childs = methodtest_simple55.getChildren();
					methodf56 = ModelTestUtils.getAssertMethod( methodtest_simple55Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf56, new String[] {"p"} );
				}
			}
			//Function test:test_basic_exception
			{
			IMethod methodtest_basic_exception57;
				IModelElement[] classProfileSimulatorTestCase53Childs = classProfileSimulatorTestCase53.getChildren();
				methodtest_basic_exception57 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase53Childs, "test_basic_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic_exception57, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf58;
					IModelElement[] methodtest_basic_exception57Childs = methodtest_basic_exception57.getChildren();
					methodf58 = ModelTestUtils.getAssertMethod( methodtest_basic_exception57Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf58, new String[] {"p"} );
				}
			}
			//Function test:test_caught_exception
			{
			IMethod methodtest_caught_exception59;
				IModelElement[] classProfileSimulatorTestCase53Childs = classProfileSimulatorTestCase53.getChildren();
				methodtest_caught_exception59 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase53Childs, "test_caught_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_caught_exception59, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf60;
					IModelElement[] methodtest_caught_exception59Childs = methodtest_caught_exception59.getChildren();
					methodf60 = ModelTestUtils.getAssertMethod( methodtest_caught_exception59Childs, "f", 1 );
					ModelTestUtils.assertParameterNames( methodf60, new String[] {"p"} );
				}
			}
			//Function test:test_distant_exception
			{
			IMethod methodtest_distant_exception61;
				IModelElement[] classProfileSimulatorTestCase53Childs = classProfileSimulatorTestCase53.getChildren();
				methodtest_distant_exception61 = ModelTestUtils.getAssertMethod( classProfileSimulatorTestCase53Childs, "test_distant_exception", 1 );
				ModelTestUtils.assertParameterNames( methodtest_distant_exception61, new String[] {"self"} );
				//Function test:f
				{
				IMethod methodf62;
					IModelElement[] methodtest_distant_exception61Childs = methodtest_distant_exception61.getChildren();
					methodf62 = ModelTestUtils.getAssertMethod( methodtest_distant_exception61Childs, "f", 0 );
				}
				//Function test:g
				{
				IMethod methodg63;
					IModelElement[] methodtest_distant_exception61Childs = methodtest_distant_exception61.getChildren();
					methodg63 = ModelTestUtils.getAssertMethod( methodtest_distant_exception61Childs, "g", 0 );
				}
				//Function test:h
				{
				IMethod methodh64;
					IModelElement[] methodtest_distant_exception61Childs = methodtest_distant_exception61.getChildren();
					methodh64 = ModelTestUtils.getAssertMethod( methodtest_distant_exception61Childs, "h", 0 );
				}
				//Function test:i
				{
				IMethod methodi65;
					IModelElement[] methodtest_distant_exception61Childs = methodtest_distant_exception61.getChildren();
					methodi65 = ModelTestUtils.getAssertMethod( methodtest_distant_exception61Childs, "i", 0 );
				}
				//Function test:j
				{
				IMethod methodj66;
					IModelElement[] methodtest_distant_exception61Childs = methodtest_distant_exception61.getChildren();
					methodj66 = ModelTestUtils.getAssertMethod( methodtest_distant_exception61Childs, "j", 1 );
					ModelTestUtils.assertParameterNames( methodj66, new String[] {"p"} );
				}
			}
		}
		//Function test:ident
		{
		IMethod methodident67;
			IModelElement[] moduleChilds = module.getChildren();
			methodident67 = ModelTestUtils.getAssertMethod( moduleChilds, "ident", 1 );
			ModelTestUtils.assertParameterNames( methodident67, new String[] {"function"} );
		}
		//Function test:protect
		{
		IMethod methodprotect68;
			IModelElement[] moduleChilds = module.getChildren();
			methodprotect68 = ModelTestUtils.getAssertMethod( moduleChilds, "protect", 2 );
			ModelTestUtils.assertParameterNames( methodprotect68, new String[] {"f", "p"} );
		}
		{
			IModelElement[] moduleChilds = module.getChildren();
			IField fieldValue = ModelTestUtils.getAssertField( moduleChilds, "protect_ident");
		}
		//Function test:capture_events
		{
		IMethod methodcapture_events69;
			IModelElement[] moduleChilds = module.getChildren();
			methodcapture_events69 = ModelTestUtils.getAssertMethod( moduleChilds, "capture_events", 2 );
			ModelTestUtils.assertParameterNames( methodcapture_events69, new String[] {"callable", "p"} );
		}
		//Function test:show_events
		{
		IMethod methodshow_events70;
			IModelElement[] moduleChilds = module.getChildren();
			methodshow_events70 = ModelTestUtils.getAssertMethod( moduleChilds, "show_events", 1 );
			ModelTestUtils.assertParameterNames( methodshow_events70, new String[] {"callable"} );
		}
		//Function test:test_main
		{
		IMethod methodtest_main71;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main71 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUUStdIOTest5Childs, "stdin");
			}
			{
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUUStdIOTest5Childs, "stdout");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown8;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodtearDown8 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown8, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode9;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodtest_encode9 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode9, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode10;
				IModelElement[] classUUStdIOTest5Childs = classUUStdIOTest5.getChildren();
				methodtest_decode10 = ModelTestUtils.getAssertMethod( classUUStdIOTest5Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode10, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUUFileTest11;
			IModelElement[] moduleChilds = module.getChildren();
			classUUFileTest11 = ModelTestUtils.getAssertClass( moduleChilds, "UUFileTest" );
			//Function test:_kill
			{
			IMethod method_kill12;
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				method_kill12 = ModelTestUtils.getAssertMethod( classUUFileTest11Childs, "_kill", 2 );
				ModelTestUtils.assertParameterNames( method_kill12, new String[] {"self", "f"} );
			}
			//Function test:setUp
			{
			IMethod methodsetUp13;
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				methodsetUp13 = ModelTestUtils.getAssertMethod( classUUFileTest11Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp13, new String[] {"self"} );
			}
			{
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUUFileTest11Childs, "tmpin");
			}
			{
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classUUFileTest11Childs, "tmpout");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown15;
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				methodtearDown15 = ModelTestUtils.getAssertMethod( classUUFileTest11Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown15, new String[] {"self"} );
			}
			//Function test:test_encode
			{
			IMethod methodtest_encode16;
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				methodtest_encode16 = ModelTestUtils.getAssertMethod( classUUFileTest11Childs, "test_encode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_encode16, new String[] {"self"} );
			}
			//Function test:test_decode
			{
			IMethod methodtest_decode17;
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				methodtest_decode17 = ModelTestUtils.getAssertMethod( classUUFileTest11Childs, "test_decode", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decode17, new String[] {"self"} );
			}
			//Function test:test_decodetwice
			{
			IMethod methodtest_decodetwice18;
				IModelElement[] classUUFileTest11Childs = classUUFileTest11.getChildren();
				methodtest_decodetwice18 = ModelTestUtils.getAssertMethod( classUUFileTest11Childs, "test_decodetwice", 1 );
				ModelTestUtils.assertParameterNames( methodtest_decodetwice18, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main19;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main19 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classShlexTest0Childs, "data");
			}
			{
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classShlexTest0Childs, "posix_data");
			}
			//Function test:splitTest
			{
			IMethod methodsplitTest3;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodsplitTest3 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "splitTest", 3 );
				ModelTestUtils.assertParameterNames( methodsplitTest3, new String[] {"self", "data", "comments"} );
			}
			//Function test:oldSplit
			{
			IMethod methodoldSplit4;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodoldSplit4 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "oldSplit", 2 );
				ModelTestUtils.assertParameterNames( methodoldSplit4, new String[] {"self", "s"} );
			}
			//Function test:testSplitPosix
			{
			IMethod methodtestSplitPosix5;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodtestSplitPosix5 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "testSplitPosix", 1 );
				ModelTestUtils.assertParameterNames( methodtestSplitPosix5, new String[] {"self"} );
			}
			//Function test:testCompat
			{
			IMethod methodtestCompat6;
				IModelElement[] classShlexTest0Childs = classShlexTest0.getChildren();
				methodtestCompat6 = ModelTestUtils.getAssertMethod( classShlexTest0Childs, "testCompat", 1 );
				ModelTestUtils.assertParameterNames( methodtestCompat6, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main7;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main7 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classDircacheTests0Childs, "tempdir");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown3;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodtearDown3 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown3, new String[] {"self"} );
			}
			//Function test:writeTemp
			{
			IMethod methodwriteTemp4;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodwriteTemp4 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "writeTemp", 2 );
				ModelTestUtils.assertParameterNames( methodwriteTemp4, new String[] {"self", "fname"} );
			}
			//Function test:mkdirTemp
			{
			IMethod methodmkdirTemp5;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodmkdirTemp5 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "mkdirTemp", 2 );
				ModelTestUtils.assertParameterNames( methodmkdirTemp5, new String[] {"self", "fname"} );
			}
			//Function test:delTemp
			{
			IMethod methoddelTemp6;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methoddelTemp6 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "delTemp", 2 );
				ModelTestUtils.assertParameterNames( methoddelTemp6, new String[] {"self", "fname"} );
			}
			//Function test:test_listdir
			{
			IMethod methodtest_listdir7;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodtest_listdir7 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "test_listdir", 1 );
				ModelTestUtils.assertParameterNames( methodtest_listdir7, new String[] {"self"} );
			}
			//Function test:test_annotate
			{
			IMethod methodtest_annotate8;
				IModelElement[] classDircacheTests0Childs = classDircacheTests0.getChildren();
				methodtest_annotate8 = ModelTestUtils.getAssertMethod( classDircacheTests0Childs, "test_annotate", 1 );
				ModelTestUtils.assertParameterNames( methodtest_annotate8, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main9;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main9 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
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
			{
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classurlopen_FileTests1Childs, "text");
			}
			{
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classurlopen_FileTests1Childs, "pathname");
			}
			{
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classurlopen_FileTests1Childs, "returned_obj");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown4;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtearDown4 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown4, new String[] {"self"} );
			}
			//Function test:test_interface
			{
			IMethod methodtest_interface5;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_interface5 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_interface", 1 );
				ModelTestUtils.assertParameterNames( methodtest_interface5, new String[] {"self"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read6;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_read6 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read6, new String[] {"self"} );
			}
			//Function test:test_readline
			{
			IMethod methodtest_readline7;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_readline7 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_readline", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readline7, new String[] {"self"} );
			}
			//Function test:test_readlines
			{
			IMethod methodtest_readlines8;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_readlines8 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_readlines", 1 );
				ModelTestUtils.assertParameterNames( methodtest_readlines8, new String[] {"self"} );
			}
			//Function test:test_fileno
			{
			IMethod methodtest_fileno9;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_fileno9 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_fileno", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fileno9, new String[] {"self"} );
			}
			//Function test:test_close
			{
			IMethod methodtest_close10;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_close10 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_close", 1 );
				ModelTestUtils.assertParameterNames( methodtest_close10, new String[] {"self"} );
			}
			//Function test:test_info
			{
			IMethod methodtest_info11;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_info11 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_info", 1 );
				ModelTestUtils.assertParameterNames( methodtest_info11, new String[] {"self"} );
			}
			//Function test:test_geturl
			{
			IMethod methodtest_geturl12;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_geturl12 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_geturl", 1 );
				ModelTestUtils.assertParameterNames( methodtest_geturl12, new String[] {"self"} );
			}
			//Function test:test_iter
			{
			IMethod methodtest_iter13;
				IModelElement[] classurlopen_FileTests1Childs = classurlopen_FileTests1.getChildren();
				methodtest_iter13 = ModelTestUtils.getAssertMethod( classurlopen_FileTests1Childs, "test_iter", 1 );
				ModelTestUtils.assertParameterNames( methodtest_iter13, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlopen_HttpTests14;
			IModelElement[] moduleChilds = module.getChildren();
			classurlopen_HttpTests14 = ModelTestUtils.getAssertClass( moduleChilds, "urlopen_HttpTests" );
			//Function test:fakehttp
			{
			IMethod methodfakehttp15;
				IModelElement[] classurlopen_HttpTests14Childs = classurlopen_HttpTests14.getChildren();
				methodfakehttp15 = ModelTestUtils.getAssertMethod( classurlopen_HttpTests14Childs, "fakehttp", 2 );
				ModelTestUtils.assertParameterNames( methodfakehttp15, new String[] {"self", "fakedata"} );
				//Class test
				{
				IType classFakeSocket16;
					IModelElement[] methodfakehttp15Childs = methodfakehttp15.getChildren();
					classFakeSocket16 = ModelTestUtils.getAssertClass( methodfakehttp15Childs, "FakeSocket" );
					//Function test:sendall
					{
					IMethod methodsendall17;
						IModelElement[] classFakeSocket16Childs = classFakeSocket16.getChildren();
						methodsendall17 = ModelTestUtils.getAssertMethod( classFakeSocket16Childs, "sendall", 2 );
						ModelTestUtils.assertParameterNames( methodsendall17, new String[] {"self", "str"} );
					}
					//Function test:makefile
					{
					IMethod methodmakefile18;
						IModelElement[] classFakeSocket16Childs = classFakeSocket16.getChildren();
						methodmakefile18 = ModelTestUtils.getAssertMethod( classFakeSocket16Childs, "makefile", 3 );
						ModelTestUtils.assertParameterNames( methodmakefile18, new String[] {"self", "mode", "name"} );
					}
					//Function test:read
					{
					IMethod methodread19;
						IModelElement[] classFakeSocket16Childs = classFakeSocket16.getChildren();
						methodread19 = ModelTestUtils.getAssertMethod( classFakeSocket16Childs, "read", 2 );
						ModelTestUtils.assertParameterNames( methodread19, new String[] {"self", "amt"} );
					}
					//Function test:readline
					{
					IMethod methodreadline20;
						IModelElement[] classFakeSocket16Childs = classFakeSocket16.getChildren();
						methodreadline20 = ModelTestUtils.getAssertMethod( classFakeSocket16Childs, "readline", 2 );
						ModelTestUtils.assertParameterNames( methodreadline20, new String[] {"self", "length"} );
					}
				}
				//Class test
				{
				IType classFakeHTTPConnection21;
					IModelElement[] methodfakehttp15Childs = methodfakehttp15.getChildren();
					classFakeHTTPConnection21 = ModelTestUtils.getAssertClass( methodfakehttp15Childs, "FakeHTTPConnection" );
					//Function test:connect
					{
					IMethod methodconnect22;
						IModelElement[] classFakeHTTPConnection21Childs = classFakeHTTPConnection21.getChildren();
						methodconnect22 = ModelTestUtils.getAssertMethod( classFakeHTTPConnection21Childs, "connect", 1 );
						ModelTestUtils.assertParameterNames( methodconnect22, new String[] {"self"} );
					}
					{
						IModelElement[] classFakeHTTPConnection21Childs = classFakeHTTPConnection21.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classFakeHTTPConnection21Childs, "sock");
					}
				}
			}
			//Function test:unfakehttp
			{
			IMethod methodunfakehttp24;
				IModelElement[] classurlopen_HttpTests14Childs = classurlopen_HttpTests14.getChildren();
				methodunfakehttp24 = ModelTestUtils.getAssertMethod( classurlopen_HttpTests14Childs, "unfakehttp", 1 );
				ModelTestUtils.assertParameterNames( methodunfakehttp24, new String[] {"self"} );
			}
			//Function test:test_read
			{
			IMethod methodtest_read25;
				IModelElement[] classurlopen_HttpTests14Childs = classurlopen_HttpTests14.getChildren();
				methodtest_read25 = ModelTestUtils.getAssertMethod( classurlopen_HttpTests14Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read25, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlretrieve_FileTests26;
			IModelElement[] moduleChilds = module.getChildren();
			classurlretrieve_FileTests26 = ModelTestUtils.getAssertClass( moduleChilds, "urlretrieve_FileTests" );
			//Function test:setUp
			{
			IMethod methodsetUp27;
				IModelElement[] classurlretrieve_FileTests26Childs = classurlretrieve_FileTests26.getChildren();
				methodsetUp27 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests26Childs, "setUp", 1 );
				ModelTestUtils.assertParameterNames( methodsetUp27, new String[] {"self"} );
			}
			{
				IModelElement[] classurlretrieve_FileTests26Childs = classurlretrieve_FileTests26.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classurlretrieve_FileTests26Childs, "text");
			}
			//Function test:tearDown
			{
			IMethod methodtearDown29;
				IModelElement[] classurlretrieve_FileTests26Childs = classurlretrieve_FileTests26.getChildren();
				methodtearDown29 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests26Childs, "tearDown", 1 );
				ModelTestUtils.assertParameterNames( methodtearDown29, new String[] {"self"} );
			}
			//Function test:test_basic
			{
			IMethod methodtest_basic30;
				IModelElement[] classurlretrieve_FileTests26Childs = classurlretrieve_FileTests26.getChildren();
				methodtest_basic30 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests26Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic30, new String[] {"self"} );
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy31;
				IModelElement[] classurlretrieve_FileTests26Childs = classurlretrieve_FileTests26.getChildren();
				methodtest_copy31 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests26Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy31, new String[] {"self"} );
			}
			//Function test:test_reporthook
			{
			IMethod methodtest_reporthook32;
				IModelElement[] classurlretrieve_FileTests26Childs = classurlretrieve_FileTests26.getChildren();
				methodtest_reporthook32 = ModelTestUtils.getAssertMethod( classurlretrieve_FileTests26Childs, "test_reporthook", 1 );
				ModelTestUtils.assertParameterNames( methodtest_reporthook32, new String[] {"self"} );
				//Function test:hooktester
				{
				IMethod methodhooktester33;
					IModelElement[] methodtest_reporthook32Childs = methodtest_reporthook32.getChildren();
					methodhooktester33 = ModelTestUtils.getAssertMethod( methodtest_reporthook32Childs, "hooktester", 4 );
					ModelTestUtils.assertParameterNames( methodhooktester33, new String[] {"count", "block_size", "total_size", "count_holder"} );
				}
			}
		}
		//Class test
		{
		IType classQuotingTests34;
			IModelElement[] moduleChilds = module.getChildren();
			classQuotingTests34 = ModelTestUtils.getAssertClass( moduleChilds, "QuotingTests" );
			//Function test:test_never_quote
			{
			IMethod methodtest_never_quote35;
				IModelElement[] classQuotingTests34Childs = classQuotingTests34.getChildren();
				methodtest_never_quote35 = ModelTestUtils.getAssertMethod( classQuotingTests34Childs, "test_never_quote", 1 );
				ModelTestUtils.assertParameterNames( methodtest_never_quote35, new String[] {"self"} );
			}
			//Function test:test_default_safe
			{
			IMethod methodtest_default_safe36;
				IModelElement[] classQuotingTests34Childs = classQuotingTests34.getChildren();
				methodtest_default_safe36 = ModelTestUtils.getAssertMethod( classQuotingTests34Childs, "test_default_safe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_safe36, new String[] {"self"} );
			}
			//Function test:test_safe
			{
			IMethod methodtest_safe37;
				IModelElement[] classQuotingTests34Childs = classQuotingTests34.getChildren();
				methodtest_safe37 = ModelTestUtils.getAssertMethod( classQuotingTests34Childs, "test_safe", 1 );
				ModelTestUtils.assertParameterNames( methodtest_safe37, new String[] {"self"} );
			}
			//Function test:test_default_quoting
			{
			IMethod methodtest_default_quoting38;
				IModelElement[] classQuotingTests34Childs = classQuotingTests34.getChildren();
				methodtest_default_quoting38 = ModelTestUtils.getAssertMethod( classQuotingTests34Childs, "test_default_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_default_quoting38, new String[] {"self"} );
			}
			//Function test:test_quoting_space
			{
			IMethod methodtest_quoting_space39;
				IModelElement[] classQuotingTests34Childs = classQuotingTests34.getChildren();
				methodtest_quoting_space39 = ModelTestUtils.getAssertMethod( classQuotingTests34Childs, "test_quoting_space", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting_space39, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classUnquotingTests40;
			IModelElement[] moduleChilds = module.getChildren();
			classUnquotingTests40 = ModelTestUtils.getAssertClass( moduleChilds, "UnquotingTests" );
			//Function test:test_unquoting
			{
			IMethod methodtest_unquoting41;
				IModelElement[] classUnquotingTests40Childs = classUnquotingTests40.getChildren();
				methodtest_unquoting41 = ModelTestUtils.getAssertMethod( classUnquotingTests40Childs, "test_unquoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unquoting41, new String[] {"self"} );
			}
			//Function test:test_unquoting_parts
			{
			IMethod methodtest_unquoting_parts42;
				IModelElement[] classUnquotingTests40Childs = classUnquotingTests40.getChildren();
				methodtest_unquoting_parts42 = ModelTestUtils.getAssertMethod( classUnquotingTests40Childs, "test_unquoting_parts", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unquoting_parts42, new String[] {"self"} );
			}
			//Function test:test_unquoting_plus
			{
			IMethod methodtest_unquoting_plus43;
				IModelElement[] classUnquotingTests40Childs = classUnquotingTests40.getChildren();
				methodtest_unquoting_plus43 = ModelTestUtils.getAssertMethod( classUnquotingTests40Childs, "test_unquoting_plus", 1 );
				ModelTestUtils.assertParameterNames( methodtest_unquoting_plus43, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classurlencode_Tests44;
			IModelElement[] moduleChilds = module.getChildren();
			classurlencode_Tests44 = ModelTestUtils.getAssertClass( moduleChilds, "urlencode_Tests" );
			//Function test:help_inputtype
			{
			IMethod methodhelp_inputtype45;
				IModelElement[] classurlencode_Tests44Childs = classurlencode_Tests44.getChildren();
				methodhelp_inputtype45 = ModelTestUtils.getAssertMethod( classurlencode_Tests44Childs, "help_inputtype", 3 );
				ModelTestUtils.assertParameterNames( methodhelp_inputtype45, new String[] {"self", "given", "test_type"} );
			}
			//Function test:test_using_mapping
			{
			IMethod methodtest_using_mapping46;
				IModelElement[] classurlencode_Tests44Childs = classurlencode_Tests44.getChildren();
				methodtest_using_mapping46 = ModelTestUtils.getAssertMethod( classurlencode_Tests44Childs, "test_using_mapping", 1 );
				ModelTestUtils.assertParameterNames( methodtest_using_mapping46, new String[] {"self"} );
			}
			//Function test:test_using_sequence
			{
			IMethod methodtest_using_sequence47;
				IModelElement[] classurlencode_Tests44Childs = classurlencode_Tests44.getChildren();
				methodtest_using_sequence47 = ModelTestUtils.getAssertMethod( classurlencode_Tests44Childs, "test_using_sequence", 1 );
				ModelTestUtils.assertParameterNames( methodtest_using_sequence47, new String[] {"self"} );
			}
			//Function test:test_quoting
			{
			IMethod methodtest_quoting48;
				IModelElement[] classurlencode_Tests44Childs = classurlencode_Tests44.getChildren();
				methodtest_quoting48 = ModelTestUtils.getAssertMethod( classurlencode_Tests44Childs, "test_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting48, new String[] {"self"} );
			}
			//Function test:test_doseq
			{
			IMethod methodtest_doseq49;
				IModelElement[] classurlencode_Tests44Childs = classurlencode_Tests44.getChildren();
				methodtest_doseq49 = ModelTestUtils.getAssertMethod( classurlencode_Tests44Childs, "test_doseq", 1 );
				ModelTestUtils.assertParameterNames( methodtest_doseq49, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classPathname_Tests50;
			IModelElement[] moduleChilds = module.getChildren();
			classPathname_Tests50 = ModelTestUtils.getAssertClass( moduleChilds, "Pathname_Tests" );
			//Function test:test_basic
			{
			IMethod methodtest_basic51;
				IModelElement[] classPathname_Tests50Childs = classPathname_Tests50.getChildren();
				methodtest_basic51 = ModelTestUtils.getAssertMethod( classPathname_Tests50Childs, "test_basic", 1 );
				ModelTestUtils.assertParameterNames( methodtest_basic51, new String[] {"self"} );
			}
			//Function test:test_quoting
			{
			IMethod methodtest_quoting52;
				IModelElement[] classPathname_Tests50Childs = classPathname_Tests50.getChildren();
				methodtest_quoting52 = ModelTestUtils.getAssertMethod( classPathname_Tests50Childs, "test_quoting", 1 );
				ModelTestUtils.assertParameterNames( methodtest_quoting52, new String[] {"self"} );
			}
		}
		//Function test:test_main
		{
		IMethod methodtest_main53;
			IModelElement[] moduleChilds = module.getChildren();
			methodtest_main53 = ModelTestUtils.getAssertMethod( moduleChilds, "test_main", 0 );
		}

	}
	public void testModelGen49( ) throws Exception {
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
			{
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicTestMappingProtocol0Childs, "reference");
			}
			{
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicTestMappingProtocol0Childs, "other");
			}
			{
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				IField fieldValue = ModelTestUtils.getAssertField( classBasicTestMappingProtocol0Childs, "inmapping");
			}
			//Function test:test_read
			{
			IMethod methodtest_read6;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_read6 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_read", 1 );
				ModelTestUtils.assertParameterNames( methodtest_read6, new String[] {"self"} );
				//Function test:check_iterandlist
				{
				IMethod methodcheck_iterandlist7;
					IModelElement[] methodtest_read6Childs = methodtest_read6.getChildren();
					methodcheck_iterandlist7 = ModelTestUtils.getAssertMethod( methodtest_read6Childs, "check_iterandlist", 3 );
					ModelTestUtils.assertParameterNames( methodcheck_iterandlist7, new String[] {"iter", "lst", "ref"} );
				}
			}
			//Function test:test_write
			{
			IMethod methodtest_write8;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_write8 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_write", 1 );
				ModelTestUtils.assertParameterNames( methodtest_write8, new String[] {"self"} );
			}
			//Function test:test_constructor
			{
			IMethod methodtest_constructor9;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_constructor9 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor9, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool10;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_bool10 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool10, new String[] {"self"} );
			}
			//Function test:test_keys
			{
			IMethod methodtest_keys11;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_keys11 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keys11, new String[] {"self"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values12;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_values12 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values12, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items13;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_items13 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items13, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len14;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_len14 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len14, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem15;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_getitem15 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem15, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update16;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_update16 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_update", 1 );
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
					{
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classSimpleUserDict17Childs, "d");
					}
					//Function test:keys
					{
					IMethod methodkeys20;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						methodkeys20 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys20, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__21;
						IModelElement[] classSimpleUserDict17Childs = classSimpleUserDict17.getChildren();
						method__getitem__21 = ModelTestUtils.getAssertMethod( classSimpleUserDict17Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__21, new String[] {"self", "i"} );
					}
				}
				//Class test
				{
				IType classExc22;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classExc22 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "Exc" );
				}
				//Class test
				{
				IType classFailingUserDict23;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict23 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys24;
						IModelElement[] classFailingUserDict23Childs = classFailingUserDict23.getChildren();
						methodkeys24 = ModelTestUtils.getAssertMethod( classFailingUserDict23Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys24, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict25;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict25 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys26;
						IModelElement[] classFailingUserDict25Childs = classFailingUserDict25.getChildren();
						methodkeys26 = ModelTestUtils.getAssertMethod( classFailingUserDict25Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys26, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter27;
							IModelElement[] methodkeys26Childs = methodkeys26.getChildren();
							classBogonIter27 = ModelTestUtils.getAssertClass( methodkeys26Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__28;
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								method__init__28 = ModelTestUtils.getAssertMethod( classBogonIter27Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__28, new String[] {"self"} );
							}
							{
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classBogonIter27Childs, "i");
							}
							//Function test:__iter__
							{
							IMethod method__iter__30;
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								method__iter__30 = ModelTestUtils.getAssertMethod( classBogonIter27Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__30, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext31;
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								methodnext31 = ModelTestUtils.getAssertMethod( classBogonIter27Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext31, new String[] {"self"} );
							}
							{
								IModelElement[] classBogonIter27Childs = classBogonIter27.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classBogonIter27Childs, "i");
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__33;
						IModelElement[] classFailingUserDict25Childs = classFailingUserDict25.getChildren();
						method__getitem__33 = ModelTestUtils.getAssertMethod( classFailingUserDict25Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__33, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classFailingUserDict34;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classFailingUserDict34 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "FailingUserDict" );
					//Function test:keys
					{
					IMethod methodkeys35;
						IModelElement[] classFailingUserDict34Childs = classFailingUserDict34.getChildren();
						methodkeys35 = ModelTestUtils.getAssertMethod( classFailingUserDict34Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys35, new String[] {"self"} );
						//Class test
						{
						IType classBogonIter36;
							IModelElement[] methodkeys35Childs = methodkeys35.getChildren();
							classBogonIter36 = ModelTestUtils.getAssertClass( methodkeys35Childs, "BogonIter" );
							//Function test:__init__
							{
							IMethod method__init__37;
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								method__init__37 = ModelTestUtils.getAssertMethod( classBogonIter36Childs, "__init__", 1 );
								ModelTestUtils.assertParameterNames( method__init__37, new String[] {"self"} );
							}
							{
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								IField fieldValue = ModelTestUtils.getAssertField( classBogonIter36Childs, "i");
							}
							//Function test:__iter__
							{
							IMethod method__iter__39;
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								method__iter__39 = ModelTestUtils.getAssertMethod( classBogonIter36Childs, "__iter__", 1 );
								ModelTestUtils.assertParameterNames( method__iter__39, new String[] {"self"} );
							}
							//Function test:next
							{
							IMethod methodnext40;
								IModelElement[] classBogonIter36Childs = classBogonIter36.getChildren();
								methodnext40 = ModelTestUtils.getAssertMethod( classBogonIter36Childs, "next", 1 );
								ModelTestUtils.assertParameterNames( methodnext40, new String[] {"self"} );
							}
						}
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__41;
						IModelElement[] classFailingUserDict34Childs = classFailingUserDict34.getChildren();
						method__getitem__41 = ModelTestUtils.getAssertMethod( classFailingUserDict34Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__41, new String[] {"self", "key"} );
					}
				}
				//Class test
				{
				IType classbadseq42;
					IModelElement[] methodtest_update16Childs = methodtest_update16.getChildren();
					classbadseq42 = ModelTestUtils.getAssertClass( methodtest_update16Childs, "badseq" );
					//Function test:__iter__
					{
					IMethod method__iter__43;
						IModelElement[] classbadseq42Childs = classbadseq42.getChildren();
						method__iter__43 = ModelTestUtils.getAssertMethod( classbadseq42Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__43, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext44;
						IModelElement[] classbadseq42Childs = classbadseq42.getChildren();
						methodnext44 = ModelTestUtils.getAssertMethod( classbadseq42Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext44, new String[] {"self"} );
					}
				}
			}
			//Function test:test_get
			{
			IMethod methodtest_get45;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_get45 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get45, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault46;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_setdefault46 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault46, new String[] {"self"} );
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem47;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_popitem47 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem47, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop48;
				IModelElement[] classBasicTestMappingProtocol0Childs = classBasicTestMappingProtocol0.getChildren();
				methodtest_pop48 = ModelTestUtils.getAssertMethod( classBasicTestMappingProtocol0Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop48, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestMappingProtocol49;
			IModelElement[] moduleChilds = module.getChildren();
			classTestMappingProtocol49 = ModelTestUtils.getAssertClass( moduleChilds, "TestMappingProtocol" );
			//Function test:test_constructor
			{
			IMethod methodtest_constructor50;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_constructor50 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_constructor", 1 );
				ModelTestUtils.assertParameterNames( methodtest_constructor50, new String[] {"self"} );
			}
			//Function test:test_bool
			{
			IMethod methodtest_bool51;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_bool51 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_bool", 1 );
				ModelTestUtils.assertParameterNames( methodtest_bool51, new String[] {"self"} );
			}
			//Function test:test_keys
			{
			IMethod methodtest_keys52;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_keys52 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_keys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_keys52, new String[] {"self"} );
			}
			//Function test:test_values
			{
			IMethod methodtest_values53;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_values53 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_values", 1 );
				ModelTestUtils.assertParameterNames( methodtest_values53, new String[] {"self"} );
			}
			//Function test:test_items
			{
			IMethod methodtest_items54;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_items54 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_items", 1 );
				ModelTestUtils.assertParameterNames( methodtest_items54, new String[] {"self"} );
			}
			//Function test:test_has_key
			{
			IMethod methodtest_has_key55;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_has_key55 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_has_key", 1 );
				ModelTestUtils.assertParameterNames( methodtest_has_key55, new String[] {"self"} );
			}
			//Function test:test_contains
			{
			IMethod methodtest_contains56;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_contains56 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_contains", 1 );
				ModelTestUtils.assertParameterNames( methodtest_contains56, new String[] {"self"} );
			}
			//Function test:test_len
			{
			IMethod methodtest_len57;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_len57 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_len", 1 );
				ModelTestUtils.assertParameterNames( methodtest_len57, new String[] {"self"} );
			}
			//Function test:test_getitem
			{
			IMethod methodtest_getitem58;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_getitem58 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem58, new String[] {"self"} );
			}
			//Function test:test_clear
			{
			IMethod methodtest_clear59;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_clear59 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_clear", 1 );
				ModelTestUtils.assertParameterNames( methodtest_clear59, new String[] {"self"} );
			}
			//Function test:test_update
			{
			IMethod methodtest_update60;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_update60 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_update", 1 );
				ModelTestUtils.assertParameterNames( methodtest_update60, new String[] {"self"} );
				//Class test
				{
				IType classSimpleUserDict61;
					IModelElement[] methodtest_update60Childs = methodtest_update60.getChildren();
					classSimpleUserDict61 = ModelTestUtils.getAssertClass( methodtest_update60Childs, "SimpleUserDict" );
					//Function test:__init__
					{
					IMethod method__init__62;
						IModelElement[] classSimpleUserDict61Childs = classSimpleUserDict61.getChildren();
						method__init__62 = ModelTestUtils.getAssertMethod( classSimpleUserDict61Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__62, new String[] {"self"} );
					}
					{
						IModelElement[] classSimpleUserDict61Childs = classSimpleUserDict61.getChildren();
						IField fieldValue = ModelTestUtils.getAssertField( classSimpleUserDict61Childs, "d");
					}
					//Function test:keys
					{
					IMethod methodkeys64;
						IModelElement[] classSimpleUserDict61Childs = classSimpleUserDict61.getChildren();
						methodkeys64 = ModelTestUtils.getAssertMethod( classSimpleUserDict61Childs, "keys", 1 );
						ModelTestUtils.assertParameterNames( methodkeys64, new String[] {"self"} );
					}
					//Function test:__getitem__
					{
					IMethod method__getitem__65;
						IModelElement[] classSimpleUserDict61Childs = classSimpleUserDict61.getChildren();
						method__getitem__65 = ModelTestUtils.getAssertMethod( classSimpleUserDict61Childs, "__getitem__", 2 );
						ModelTestUtils.assertParameterNames( method__getitem__65, new String[] {"self", "i"} );
					}
				}
			}
			//Function test:test_fromkeys
			{
			IMethod methodtest_fromkeys66;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_fromkeys66 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_fromkeys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromkeys66, new String[] {"self"} );
				//Function test:g
				{
				IMethod methodg67;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					methodg67 = ModelTestUtils.getAssertMethod( methodtest_fromkeys66Childs, "g", 0 );
				}
				//Class test
				{
				IType classdictlike68;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					classdictlike68 = ModelTestUtils.getAssertClass( methodtest_fromkeys66Childs, "dictlike" );
				}
				//Class test
				{
				IType classmydict69;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					classmydict69 = ModelTestUtils.getAssertClass( methodtest_fromkeys66Childs, "mydict" );
					//Function test:__new__
					{
					IMethod method__new__70;
						IModelElement[] classmydict69Childs = classmydict69.getChildren();
						method__new__70 = ModelTestUtils.getAssertMethod( classmydict69Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__70, new String[] {"cls"} );
					}
				}
				//Class test
				{
				IType classExc71;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					classExc71 = ModelTestUtils.getAssertClass( methodtest_fromkeys66Childs, "Exc" );
				}
				//Class test
				{
				IType classbaddict172;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					classbaddict172 = ModelTestUtils.getAssertClass( methodtest_fromkeys66Childs, "baddict1" );
					//Function test:__init__
					{
					IMethod method__init__73;
						IModelElement[] classbaddict172Childs = classbaddict172.getChildren();
						method__init__73 = ModelTestUtils.getAssertMethod( classbaddict172Childs, "__init__", 1 );
						ModelTestUtils.assertParameterNames( method__init__73, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classBadSeq74;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					classBadSeq74 = ModelTestUtils.getAssertClass( methodtest_fromkeys66Childs, "BadSeq" );
					//Function test:__iter__
					{
					IMethod method__iter__75;
						IModelElement[] classBadSeq74Childs = classBadSeq74.getChildren();
						method__iter__75 = ModelTestUtils.getAssertMethod( classBadSeq74Childs, "__iter__", 1 );
						ModelTestUtils.assertParameterNames( method__iter__75, new String[] {"self"} );
					}
					//Function test:next
					{
					IMethod methodnext76;
						IModelElement[] classBadSeq74Childs = classBadSeq74.getChildren();
						methodnext76 = ModelTestUtils.getAssertMethod( classBadSeq74Childs, "next", 1 );
						ModelTestUtils.assertParameterNames( methodnext76, new String[] {"self"} );
					}
				}
				//Class test
				{
				IType classbaddict277;
					IModelElement[] methodtest_fromkeys66Childs = methodtest_fromkeys66.getChildren();
					classbaddict277 = ModelTestUtils.getAssertClass( methodtest_fromkeys66Childs, "baddict2" );
					//Function test:__setitem__
					{
					IMethod method__setitem__78;
						IModelElement[] classbaddict277Childs = classbaddict277.getChildren();
						method__setitem__78 = ModelTestUtils.getAssertMethod( classbaddict277Childs, "__setitem__", 3 );
						ModelTestUtils.assertParameterNames( method__setitem__78, new String[] {"self", "key", "value"} );
					}
				}
			}
			//Function test:test_copy
			{
			IMethod methodtest_copy79;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_copy79 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_copy", 1 );
				ModelTestUtils.assertParameterNames( methodtest_copy79, new String[] {"self"} );
			}
			//Function test:test_get
			{
			IMethod methodtest_get80;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_get80 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_get", 1 );
				ModelTestUtils.assertParameterNames( methodtest_get80, new String[] {"self"} );
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault81;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_setdefault81 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault81, new String[] {"self"} );
			}
			//Function test:test_popitem
			{
			IMethod methodtest_popitem82;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_popitem82 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_popitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_popitem82, new String[] {"self"} );
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop83;
				IModelElement[] classTestMappingProtocol49Childs = classTestMappingProtocol49.getChildren();
				methodtest_pop83 = ModelTestUtils.getAssertMethod( classTestMappingProtocol49Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop83, new String[] {"self"} );
			}
		}
		//Class test
		{
		IType classTestHashMappingProtocol84;
			IModelElement[] moduleChilds = module.getChildren();
			classTestHashMappingProtocol84 = ModelTestUtils.getAssertClass( moduleChilds, "TestHashMappingProtocol" );
			//Function test:test_getitem
			{
			IMethod methodtest_getitem85;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_getitem85 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_getitem", 1 );
				ModelTestUtils.assertParameterNames( methodtest_getitem85, new String[] {"self"} );
				//Class test
				{
				IType classExc86;
					IModelElement[] methodtest_getitem85Childs = methodtest_getitem85.getChildren();
					classExc86 = ModelTestUtils.getAssertClass( methodtest_getitem85Childs, "Exc" );
				}
				//Class test
				{
				IType classBadEq87;
					IModelElement[] methodtest_getitem85Childs = methodtest_getitem85.getChildren();
					classBadEq87 = ModelTestUtils.getAssertClass( methodtest_getitem85Childs, "BadEq" );
					//Function test:__eq__
					{
					IMethod method__eq__88;
						IModelElement[] classBadEq87Childs = classBadEq87.getChildren();
						method__eq__88 = ModelTestUtils.getAssertMethod( classBadEq87Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__88, new String[] {"self", "other"} );
					}
				}
				//Class test
				{
				IType classBadHash89;
					IModelElement[] methodtest_getitem85Childs = methodtest_getitem85.getChildren();
					classBadHash89 = ModelTestUtils.getAssertClass( methodtest_getitem85Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__90;
						IModelElement[] classBadHash89Childs = classBadHash89.getChildren();
						method__hash__90 = ModelTestUtils.getAssertMethod( classBadHash89Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__90, new String[] {"self"} );
					}
				}
			}
			//Function test:test_fromkeys
			{
			IMethod methodtest_fromkeys91;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_fromkeys91 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_fromkeys", 1 );
				ModelTestUtils.assertParameterNames( methodtest_fromkeys91, new String[] {"self"} );
				//Class test
				{
				IType classmydict92;
					IModelElement[] methodtest_fromkeys91Childs = methodtest_fromkeys91.getChildren();
					classmydict92 = ModelTestUtils.getAssertClass( methodtest_fromkeys91Childs, "mydict" );
					//Function test:__new__
					{
					IMethod method__new__93;
						IModelElement[] classmydict92Childs = classmydict92.getChildren();
						method__new__93 = ModelTestUtils.getAssertMethod( classmydict92Childs, "__new__", 1 );
						ModelTestUtils.assertParameterNames( method__new__93, new String[] {"cls"} );
					}
				}
			}
			//Function test:test_pop
			{
			IMethod methodtest_pop94;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_pop94 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_pop", 1 );
				ModelTestUtils.assertParameterNames( methodtest_pop94, new String[] {"self"} );
				//Class test
				{
				IType classExc95;
					IModelElement[] methodtest_pop94Childs = methodtest_pop94.getChildren();
					classExc95 = ModelTestUtils.getAssertClass( methodtest_pop94Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash96;
					IModelElement[] methodtest_pop94Childs = methodtest_pop94.getChildren();
					classBadHash96 = ModelTestUtils.getAssertClass( methodtest_pop94Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__97;
						IModelElement[] classBadHash96Childs = classBadHash96.getChildren();
						method__hash__97 = ModelTestUtils.getAssertMethod( classBadHash96Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__97, new String[] {"self"} );
					}
				}
			}
			//Function test:test_mutatingiteration
			{
			IMethod methodtest_mutatingiteration98;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_mutatingiteration98 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_mutatingiteration", 1 );
				ModelTestUtils.assertParameterNames( methodtest_mutatingiteration98, new String[] {"self"} );
			}
			//Function test:test_repr
			{
			IMethod methodtest_repr99;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_repr99 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_repr", 1 );
				ModelTestUtils.assertParameterNames( methodtest_repr99, new String[] {"self"} );
				//Class test
				{
				IType classExc100;
					IModelElement[] methodtest_repr99Childs = methodtest_repr99.getChildren();
					classExc100 = ModelTestUtils.getAssertClass( methodtest_repr99Childs, "Exc" );
				}
				//Class test
				{
				IType classBadRepr101;
					IModelElement[] methodtest_repr99Childs = methodtest_repr99.getChildren();
					classBadRepr101 = ModelTestUtils.getAssertClass( methodtest_repr99Childs, "BadRepr" );
					//Function test:__repr__
					{
					IMethod method__repr__102;
						IModelElement[] classBadRepr101Childs = classBadRepr101.getChildren();
						method__repr__102 = ModelTestUtils.getAssertMethod( classBadRepr101Childs, "__repr__", 1 );
						ModelTestUtils.assertParameterNames( method__repr__102, new String[] {"self"} );
					}
				}
			}
			//Function test:test_le
			{
			IMethod methodtest_le103;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_le103 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_le", 1 );
				ModelTestUtils.assertParameterNames( methodtest_le103, new String[] {"self"} );
				//Class test
				{
				IType classExc104;
					IModelElement[] methodtest_le103Childs = methodtest_le103.getChildren();
					classExc104 = ModelTestUtils.getAssertClass( methodtest_le103Childs, "Exc" );
				}
				//Class test
				{
				IType classBadCmp105;
					IModelElement[] methodtest_le103Childs = methodtest_le103.getChildren();
					classBadCmp105 = ModelTestUtils.getAssertClass( methodtest_le103Childs, "BadCmp" );
					//Function test:__eq__
					{
					IMethod method__eq__106;
						IModelElement[] classBadCmp105Childs = classBadCmp105.getChildren();
						method__eq__106 = ModelTestUtils.getAssertMethod( classBadCmp105Childs, "__eq__", 2 );
						ModelTestUtils.assertParameterNames( method__eq__106, new String[] {"self", "other"} );
					}
				}
			}
			//Function test:test_setdefault
			{
			IMethod methodtest_setdefault107;
				IModelElement[] classTestHashMappingProtocol84Childs = classTestHashMappingProtocol84.getChildren();
				methodtest_setdefault107 = ModelTestUtils.getAssertMethod( classTestHashMappingProtocol84Childs, "test_setdefault", 1 );
				ModelTestUtils.assertParameterNames( methodtest_setdefault107, new String[] {"self"} );
				//Class test
				{
				IType classExc108;
					IModelElement[] methodtest_setdefault107Childs = methodtest_setdefault107.getChildren();
					classExc108 = ModelTestUtils.getAssertClass( methodtest_setdefault107Childs, "Exc" );
				}
				//Class test
				{
				IType classBadHash109;
					IModelElement[] methodtest_setdefault107Childs = methodtest_setdefault107.getChildren();
					classBadHash109 = ModelTestUtils.getAssertClass( methodtest_setdefault107Childs, "BadHash" );
					//Function test:__hash__
					{
					IMethod method__hash__110;
						IModelElement[] classBadHash109Childs = classBadHash109.getChildren();
						method__hash__110 = ModelTestUtils.getAssertMethod( classBadHash109Childs, "__hash__", 1 );
						ModelTestUtils.assertParameterNames( method__hash__110, new String[] {"self"} );
					}
				}
			}
		}

	}

}
	