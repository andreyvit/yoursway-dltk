/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/


package org.eclipse.dltk.tcl.parser.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.tcl.core.tests.model.Activator;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;



public class AllParseTests extends TestCase
{		
	ZipFile scriptsZip = null;
	private final static String[] scripts = new String[]{ 
		"append.tcl",
		"appendComp.tcl",
		"assocd.tcl",
		"async.tcl",
		"autoMkindex.tcl",
		"basic.tcl",
		"binary.tcl",
		"case.tcl",
		"clock.tcl",
		"cmdAH.tcl",
		"cmdIL.tcl",
		"cmdInfo.tcl",
		"cmdMZ.tcl",
		"compExpr.tcl",
		"compExpr-old.tcl",
		"compile.tcl",
		"concat.tcl",
		"dcall.tcl",
		"dstring.tcl",
		"encoding.tcl",
		"env.tcl",
		"error.tcl",
		"escp.tcl",
		"eval.tcl",
		"event.tcl",
		"exec.tcl",
		"execute.tcl",
		"expr.tcl",
		"expr-old.tcl",
		"fCmd.tcl",
		"fileName.tcl",
		"fileSystem.tcl",
		"for.tcl",
		"for-old.tcl",
		"foreach.tcl",
		"format.tcl",
		"get.tcl",
		"history.tcl",
		"http.tcl",
		"httpold.tcl",
		"if.tcl",
		"if-old.tcl",
		"incr.tcl",
		"incr-old.tcl",
		"indexObj.tcl",
		"info.tcl",
		"init.tcl",
		"interp.tcl",
		"io.tcl",
		"ioCmd.tcl",
		"iogt.tcl",
		"ioUtil.tcl",
		"join.tcl",
		"lindex.tcl",
		"link.tcl",
		"linsert.tcl",
		"list.tcl",
		"listObj.tcl",
		"llength.tcl",
		"load.tcl",
		"lrange.tcl",
		"lreplace.tcl",
		"lsearch.tcl",
		"lset.tcl",
		"lsetComp.tcl",
		"macFCmd.tcl",
		"main.tcl",
		"misc.tcl",
		"msgcat.tcl",
		"namespace.tcl",
		"namespace-old.tcl",
		"notify.tcl",
		"obj.tcl",
		"opt.tcl",
		"osa.tcl",
		"package.tcl",
		"parse.tcl",
		"parseExpr.tcl",
		"parseOld.tcl",
		"pid.tcl",
		"pkg.tcl",
		"pkgMkIndex.tcl",
		"platform.tcl",
		"proc.tcl",
		"proc-old.tcl",
		"pwd.tcl",
		"reg.tcl",
		"regexp.tcl",
		"regexpComp.tcl",
		"registry.tcl",
		"rename.tcl",
		"resource.tcl",
		"result.tcl",
		"safe.tcl",
		"scan.tcl",
		"security.tcl",
		"set.tcl",
		"set-old.tcl",
		"socket.tcl",
		"source.tcl",
		"split.tcl",
		"stack.tcl",
		"string.tcl",
		"stringComp.tcl",
		"stringObj.tcl",
		"subst.tcl",
		"switch.tcl",
		"tcl-import.tcl",
		"tcl-object.tcl",
		"tcltest.tcl",
		"test0.tcl",
		"thread.tcl",
		"timer.tcl",
		"trace.tcl",
		"unixFCmd.tcl",
		"unixFile.tcl",
		"unixInit.tcl",
		"unixNotfy.tcl",
		"unknown.tcl",
		"uplevel.tcl",
		"upvar.tcl",
		"utf.tcl",
		"util.tcl",
		"var.tcl",
		"while.tcl",
		"while-old.tcl",
		"winConsole.tcl",
		"winDde.tcl",
		"winFCmd.tcl",
		"winFile.tcl",
		"winNotify.tcl",
		"winPipe.tcl",
		"winTime.tcl"		
	};
	
	private String getContents (InputStream stream ) throws IOException {

		StringBuffer result = new StringBuffer ();
		try {
						
			InputStreamReader isr = new InputStreamReader(stream);			
			BufferedReader input = new BufferedReader(isr);
							
			while (stream.available() > 0) {
				long size = stream.available();
				
				char c[] = new char[(int)size];
				
				input.read(c);
				
				result.append (c);
			}
			
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		return result.toString();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		this.scriptsZip = new ZipFile( AbstractModelTests.storeToMetadata(Activator.getDefault().getBundle(), "tcl_scripts.zip", "/scripts/scripts.zip") );
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		if( this.scriptsZip != null ) {
			removeIfExist(this.scriptsZip.getName());
		}
	}

	private void removeIfExist(String name) {
		File file = new File( name );
		if( file.exists() ) {
			file.delete();
		}
	}

	private void testImpl(int number) throws Exception {
		if( this.scriptsZip == null ) {
			throw new IOException( "Scripts not pressent...");
		}
		String script = scripts[number];

		System.out.println("Test #" + number + " (" + script + ")");

		InputStream input = null;
		try {
			/*TclLexer lexer = new TclLexer(input);
			TclTokenStream stream = new TclTokenStream(lexer);
			TCLParser parser = new TCLParser(stream);

			List statements = new ArrayList();
			parser.statements(statements);*/
			ZipEntry entry = scriptsZip.getEntry(script);
			if( entry == null ) {
				throw new IOException( "Script:" + script + " not pressent in:" + scriptsZip.getName());
			}
								
			String content = getContents(scriptsZip.getInputStream(entry));
			
			TclSourceParser parser = new TclSourceParser();
			parser.parse(content.toCharArray(), null);

		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	public void test0() throws Exception {
		testImpl(0);
	}
	public void test1() throws Exception {
		testImpl(1);
	}
	public void test2() throws Exception {
		testImpl(2);
	}
	public void test3() throws Exception {
		testImpl(3);
	}
	public void test4() throws Exception {
		testImpl(4);
	}
	public void test5() throws Exception {
		testImpl(5);
	}
	public void test6() throws Exception {
		testImpl(6);
	}
	public void test7() throws Exception {
		testImpl(7);
	}
	public void test8() throws Exception {
		testImpl(8);
	}
	public void test9() throws Exception {
		testImpl(9);
	}
	public void test10() throws Exception {
		testImpl(10);
	}
	public void test11() throws Exception {
		testImpl(11);
	}
	public void test12() throws Exception {
		testImpl(12);
	}
	public void test13() throws Exception {
		testImpl(13);
	}
	public void test14() throws Exception {
		testImpl(14);
	}
	public void test15() throws Exception {
		testImpl(15);
	}
	public void test16() throws Exception {
		testImpl(16);
	}
	public void test17() throws Exception {
		testImpl(17);
	}
	public void test18() throws Exception {
		testImpl(18);
	}
	public void test19() throws Exception {
		testImpl(19);
	}
	public void test20() throws Exception {
		testImpl(20);
	}
	public void test21() throws Exception {
		testImpl(21);
	}
	public void test22() throws Exception {
		testImpl(22);
	}
	public void test23() throws Exception {
		testImpl(23);
	}
	public void test24() throws Exception {
		testImpl(24);
	}
	public void test25() throws Exception {
		testImpl(25);
	}
	public void test26() throws Exception {
		testImpl(26);
	}
	public void test27() throws Exception {
		testImpl(27);
	}
	public void test28() throws Exception {
		testImpl(28);
	}
	public void test29() throws Exception {
		testImpl(29);
	}
	public void test30() throws Exception {
		testImpl(30);
	}
	public void test31() throws Exception {
		testImpl(31);
	}
	public void test32() throws Exception {
		testImpl(32);
	}
	public void test33() throws Exception {
		testImpl(33);
	}
	public void test34() throws Exception {
		testImpl(34);
	}
	public void test35() throws Exception {
		testImpl(35);
	}
	public void test36() throws Exception {
		testImpl(36);
	}
	public void test37() throws Exception {
		testImpl(37);
	}
	public void test38() throws Exception {
		testImpl(38);
	}
	public void test39() throws Exception {
		testImpl(39);
	}
	public void test40() throws Exception {
		testImpl(40);
	}
	public void test41() throws Exception {
		testImpl(41);
	}
	public void test42() throws Exception {
		testImpl(42);
	}
	public void test43() throws Exception {
		testImpl(43);
	}
	public void test44() throws Exception {
		testImpl(44);
	}
	public void test45() throws Exception {
		testImpl(45);
	}
	public void test46() throws Exception {
		testImpl(46);
	}
	public void test47() throws Exception {
		testImpl(47);
	}
	public void test48() throws Exception {
		testImpl(48);
	}
	public void test49() throws Exception {
		testImpl(49);
	}
	public void test50() throws Exception {
		testImpl(50);
	}
	public void test51() throws Exception {
		testImpl(51);
	}
	public void test52() throws Exception {
		testImpl(52);
	}
	public void test53() throws Exception {
		testImpl(53);
	}
	public void test54() throws Exception {
		testImpl(54);
	}
	public void test55() throws Exception {
		testImpl(55);
	}
	public void test56() throws Exception {
		testImpl(56);
	}
	public void test57() throws Exception {
		testImpl(57);
	}
	public void test58() throws Exception {
		testImpl(58);
	}
	public void test59() throws Exception {
		testImpl(59);
	}
	public void test60() throws Exception {
		testImpl(60);
	}
	public void test61() throws Exception {
		testImpl(61);
	}
	public void test62() throws Exception {
		testImpl(62);
	}
	public void test63() throws Exception {
		testImpl(63);
	}
	public void test64() throws Exception {
		testImpl(64);
	}
	public void test65() throws Exception {
		testImpl(65);
	}
	public void test66() throws Exception {
		testImpl(66);
	}
	public void test67() throws Exception {
		testImpl(67);
	}
	public void test68() throws Exception {
		testImpl(68);
	}
	public void test69() throws Exception {
		testImpl(69);
	}
	public void test70() throws Exception {
		testImpl(70);
	}
	public void test71() throws Exception {
		testImpl(71);
	}
	public void test72() throws Exception {
		testImpl(72);
	}
	public void test73() throws Exception {
		testImpl(73);
	}
	public void test74() throws Exception {
		testImpl(74);
	}
	public void test75() throws Exception {
		testImpl(75);
	}
	public void test76() throws Exception {
		testImpl(76);
	}
	public void test77() throws Exception {
		testImpl(77);
	}
	public void test78() throws Exception {
		testImpl(78);
	}
	public void test79() throws Exception {
		testImpl(79);
	}
	public void test80() throws Exception {
		testImpl(80);
	}
	public void test81() throws Exception {
		testImpl(81);
	}
	public void test82() throws Exception {
		testImpl(82);
	}
	public void test83() throws Exception {
		testImpl(83);
	}
	public void test84() throws Exception {
		testImpl(84);
	}
	public void test85() throws Exception {
		testImpl(85);
	}
	public void test86() throws Exception {
		testImpl(86);
	}
	public void test87() throws Exception {
		testImpl(87);
	}
	public void test88() throws Exception {
		testImpl(88);
	}
	public void test89() throws Exception {
		testImpl(89);
	}
	public void test90() throws Exception {
		testImpl(90);
	}
	public void test91() throws Exception {
		testImpl(91);
	}
	public void test92() throws Exception {
		testImpl(92);
	}
	public void test93() throws Exception {
		testImpl(93);
	}
	public void test94() throws Exception {
		testImpl(94);
	}
	public void test95() throws Exception {
		testImpl(95);
	}
	public void test96() throws Exception {
		testImpl(96);
	}
	public void test97() throws Exception {
		testImpl(97);
	}
	public void test98() throws Exception {
		testImpl(98);
	}
	public void test99() throws Exception {
		testImpl(99);
	}
	public void test100() throws Exception {
		testImpl(100);
	}
	public void test101() throws Exception {
		testImpl(101);
	}
	public void test102() throws Exception {
		testImpl(102);
	}
	public void test103() throws Exception {
		testImpl(103);
	}
	public void test104() throws Exception {
		testImpl(104);
	}
	public void test105() throws Exception {
		testImpl(105);
	}
	public void test106() throws Exception {
		testImpl(106);
	}
	public void test107() throws Exception {
		testImpl(107);
	}
	public void test108() throws Exception {
		testImpl(108);
	}
	public void test109() throws Exception {
		testImpl(109);
	}
	public void test110() throws Exception {
		testImpl(110);
	}
	public void test111() throws Exception {
		testImpl(111);
	}
	public void test112() throws Exception {
		testImpl(112);
	}
	public void test113() throws Exception {
		testImpl(113);
	}
	public void test114() throws Exception {
		testImpl(114);
	}
	public void test115() throws Exception {
		testImpl(115);
	}
	public void test116() throws Exception {
		testImpl(116);
	}
	public void test117() throws Exception {
		testImpl(117);
	}
	public void test118() throws Exception {
		testImpl(118);
	}
	public void test119() throws Exception {
		testImpl(119);
	}
	public void test120() throws Exception {
		testImpl(120);
	}
	public void test121() throws Exception {
		testImpl(121);
	}
	public void test122() throws Exception {
		testImpl(122);
	}
	public void test123() throws Exception {
		testImpl(123);
	}
	public void test124() throws Exception {
		testImpl(124);
	}
	public void test125() throws Exception {
		testImpl(125);
	}
	public void test126() throws Exception {
		testImpl(126);
	}
	public void test127() throws Exception {
		testImpl(127);
	}
	public void test128() throws Exception {
		testImpl(128);
	}
	public void test129() throws Exception {
		testImpl(129);
	}
	public void test130() throws Exception {
		testImpl(130);
	}
	public void test131() throws Exception {
		testImpl(131);
	}
}
