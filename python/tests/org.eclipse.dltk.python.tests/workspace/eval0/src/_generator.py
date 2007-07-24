###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#-*- coding: utf-8 -*-
# Type evaluation JUnit tests generator.
# All files without _ in current directory are tests.
# For each file test in ../../../../src/org/trustudio/python/core/tests/eval/tests tests file will be generated.
# With name genTest(FName).java
# where FName is file name without .py extension.
import glob
import os

TESTS_PATH = "../../../src/com/xored/dltk/python/tests/eval/generated/"
tests = glob.glob( "*.py" )
print tests

PRE_TEXT = """
package org.eclipse.dltk.python.tests.eval.generated;

import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;

import org.eclipse.dltk.python.internal.evaluation.PythonASTFindVisitor;
import org.eclipse.dltk.python.internal.evaluation.PythonASTTypeEvaluator;
import org.eclipse.dltk.python.internal.evaluation.PythonTypeEvaluatorUtils;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.internal.evaluation.types.IEvaluatedType;
import org.eclipse.dltk.utils.CorePrinter;

public class %s extends AbstractModelTests
{
	public %s(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
		    
	public static Test suite() {
	    return new Suite( %s.class);
	}
				    
	public void setUpSuite() throws Exception {
	    super.setUpSuite();
	    setUpScriptProject( "eval0" );
	}
	public void tearDownSuite() throws Exception {
	    super.tearDownSuite();
	    deleteProject( "eval0" );
	}
	private void testType( String moduleName, String name, String type ) throws Exception {
		
		String prj = "eval0";
		IScriptProject project = getScriptProject( prj );
		ISourceModule module = this.getSourceModule( prj, "src", new Path( moduleName ) );
		
		CorePrinter printer = new CorePrinter( System.out );
		
		assertNotNull( module );
		
		ModuleDeclaration astModule = PythonTypeEvaluatorUtils.parseModuleForElement(module);
		PythonASTFindVisitor findVisitor = new PythonASTFindVisitor( name );
		List nodes = findVisitor.getNodes( );

		astModule.traverse( findVisitor );
		int index = 0;
		assertNotNull( nodes );
		assertEquals( "Element name should be unical", 1, nodes.size() );		
		ASTNode nde = (ASTNode)nodes.get( 0 );
		if( ! ( nde instanceof MethodDeclaration ) ) {			
			PythonASTTypeEvaluator evaluator = new PythonASTTypeEvaluator( module, astModule, findVisitor.getParents() );
			IEvaluatedType evaluatedType = evaluator.evaluateASTNode( nde, null );
			assertEquals( "Types not equal in module " + moduleName + " for variable: " + name, type, evaluatedType.getTypeName() );
		}
	}
	
"""

TEST_TEXT = """
	public void testEval%(index)d() throws Exception {
		testType( "%(module)s", "%(name)s", "%(type)s" );
	}
"""


def makeTest( test_name ):
	import os
	if os.name == "nt":
		stream = os.popen( "c:\\haiodo\\python24\\python.exe %s" % (test_name ) )
	else:
	    stream = os.popen( "python %s" % (test_name ) )
	if not stream:
		print "Error to execute python"
		return
	index = 0 # test index
	lines = stream.readlines()
	output = open( TESTS_PATH + "genTest" + test_name[:-3] + ".java", "w" )
	name =  "genTest" + test_name[:-3]
	output.write( PRE_TEXT %( name, name, name ) )
	if len( lines ) == 0:
		print "Lines are empty."
	for line in lines:
		name, type_name = line.split()
		type_name = type_name.replace( "#", " " )
		print "Adding:", name, " ", type
		vals = {
			"module": test_name,
			"type": type_name,
			"name": name,
			"index": index
		}
		output.write( TEST_TEXT % vals )
		index += 1
	output.write( "\n}\n" )
	output.close()
		
			

for test in tests:
	if not test.startswith( "_" ): # Skip utils		
		makeTest( test )
