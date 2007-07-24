/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.python.tests.eval.generated;

import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.python.internal.core.evaluation.PythonASTFindVisitor;
import org.eclipse.dltk.python.internal.core.evaluation.PythonASTTypeEvaluator;
import org.eclipse.dltk.python.internal.core.evaluation.PythonTypeEvaluatorUtils;
import org.eclipse.dltk.python.tests.PythonTestsPlugin;
import org.eclipse.dltk.ti.types.IEvaluatedType;
import org.eclipse.dltk.utils.CorePrinter;


public class genTestfunc0 extends AbstractModelTests
{
	public genTestfunc0(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
		    
	public static Test suite() {
	    return new Suite( genTestfunc0.class);
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
	

	public void testEval0() throws Exception {
		testType( "func0.py", "_a1", "number" );
	}

	public void testEval1() throws Exception {
		testType( "func0.py", "_a2", "string" );
	}

	public void testEval2() throws Exception {
		testType( "func0.py", "_a3", "list" );
	}

	public void testEval3() throws Exception {
		testType( "func0.py", "_a4", "number" );
	}

	public void testEval4() throws Exception {
		testType( "func0.py", "_c1", "number" );
	}

	public void testEval5() throws Exception {
		testType( "func0.py", "_c2", "number" );
	}

	public void testEval6() throws Exception {
		testType( "func0.py", "_c3", "string" );
	}

	public void testEval7() throws Exception {
		testType( "func0.py", "_c4", "list" );
	}

	public void testEval8() throws Exception {
		testType( "func0.py", "_c5", "function:a4" );
	}

	public void testEval9() throws Exception {
		testType( "func0.py", "_if0", "number" );
	}

	public void testEval10() throws Exception {
		testType( "func0.py", "_if1", "string" );
	}

	public void testEval11() throws Exception {
		testType( "func0.py", "_sf0", "string" );
	}

	public void testEval12() throws Exception {
		testType( "func0.py", "_ar1_0", "number" );
	}

	public void testEval13() throws Exception {
		testType( "func0.py", "_ar1_1", "number" );
	}

	public void testEval14() throws Exception {
		testType( "func0.py", "_a1_2", "error_defined" );
	}

	public void testEval15() throws Exception {
		testType( "func0.py", "_ar2_0", "number" );
	}

	public void testEval16() throws Exception {
		testType( "func0.py", "_ar2_1", "list" );
	}

	public void testEval17() throws Exception {
		testType( "func0.py", "_ar2_2", "number" );
	}

	public void testEval18() throws Exception {
		testType( "func0.py", "_ar2_3", "number" );
	}

	public void testEval19() throws Exception {
		testType( "func0.py", "_ar2_4", "number" );
	}

	public void testEval20() throws Exception {
		testType( "func0.py", "_ar2_5", "number" );
	}

}
