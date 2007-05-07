###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#-*- coding: utf-8 -*-
import glob

pre_text = """

package org.eclipse.dltk.tcl.parser.tests;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.dltk.tcl.internal.parsers.TCLParser;
import org.eclipse.dltk.tcl.internal.parsers.TclLexer;
import org.eclipse.dltk.tcl.internal.parsers.TclTokenStream;
import org.eclipse.dltk.ast.statements.Statement;


public class AllParseTests extends TestCase
{
"""

end_text = """
}
"""

test_text = """
	public void test%(name)s() throws Exception {		
		System.out.println( "Test: %(name)s");
		String s = TestsPlugin.getDefault().getPluginFileContent( "/scripts/%(fname)s" );
		StringReader reader = new StringReader( s );
		TclLexer lexer = new TclLexer( reader );
		TclTokenStream stream = new TclTokenStream( lexer );
		TCLParser parser = new TCLParser( stream );
//		try {			
			List sts = new ArrayList();
			parser.statements( sts );
//		}
//		catch( ANTLRException ex ) {
//			ex.printStackTrace( );
//		}
	}
"""

out = open( "../src/com/xored/dltk/tcl/parser/tests/AllParseTests.java", "w" )
out.write( pre_text )
tests = glob.glob( "*.tcl" )
index = 0
for t in tests:
	out.write( test_text % { "name": str( index ), "fname": t } )
	index += 1
out.write( end_text )
out.close();
