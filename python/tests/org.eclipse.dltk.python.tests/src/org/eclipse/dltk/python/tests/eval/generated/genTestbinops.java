
package org.eclipse.dltk.python.tests.eval.generated;

import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;

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
public class genTestbinops extends AbstractModelTests
{
	public genTestbinops(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
		    
	public static Test suite() {
	    return new Suite( genTestbinops.class);
	}
	private String prj = "eval0_binops.py";    			    
	public void setUpSuite() throws Exception {
	    super.setUpSuite();
	    setUpScriptProjectTo( prj, "eval0" );
	}
	public void tearDownSuite() throws Exception {
	    super.tearDownSuite();
	    deleteProject( prj );
	}
	private void testType( String moduleName, String name, String type ) throws Exception {
		
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
		testType( "binops.py", "a1", "number" );
	}

	public void testEval1() throws Exception {
		testType( "binops.py", "a2", "number" );
	}

	public void testEval2() throws Exception {
		testType( "binops.py", "b1", "number" );
	}

	public void testEval3() throws Exception {
		testType( "binops.py", "b2", "number" );
	}

	public void testEval4() throws Exception {
		testType( "binops.py", "b3", "number" );
	}

	public void testEval5() throws Exception {
		testType( "binops.py", "b4", "number" );
	}

	public void testEval6() throws Exception {
		testType( "binops.py", "b5", "number" );
	}

	public void testEval7() throws Exception {
		testType( "binops.py", "b6", "number" );
	}

	public void testEval8() throws Exception {
		testType( "binops.py", "b7", "number" );
	}

	public void testEval9() throws Exception {
		testType( "binops.py", "b8", "number" );
	}

	public void testEval10() throws Exception {
		testType( "binops.py", "s1", "string" );
	}

	public void testEval11() throws Exception {
		testType( "binops.py", "s2", "string" );
	}

	public void testEval12() throws Exception {
		testType( "binops.py", "su1", "string" );
	}

	public void testEval13() throws Exception {
		testType( "binops.py", "sb1", "string" );
	}

	public void testEval14() throws Exception {
		testType( "binops.py", "sb2", "string" );
	}

	public void testEval15() throws Exception {
		testType( "binops.py", "sb3", "string" );
	}

	public void testEval16() throws Exception {
		testType( "binops.py", "sb4", "string" );
	}

	public void testEval17() throws Exception {
		testType( "binops.py", "sb5", "string" );
	}

	public void testEval18() throws Exception {
		testType( "binops.py", "sb6", "string" );
	}

	public void testEval19() throws Exception {
		testType( "binops.py", "l1", "list" );
	}

	public void testEval20() throws Exception {
		testType( "binops.py", "l2", "list" );
	}

	public void testEval21() throws Exception {
		testType( "binops.py", "l3", "list" );
	}

	public void testEval22() throws Exception {
		testType( "binops.py", "ls1", "list" );
	}

	public void testEval23() throws Exception {
		testType( "binops.py", "ls2", "list" );
	}

	public void testEval24() throws Exception {
		testType( "binops.py", "ls3", "list" );
	}

	public void REM_testEval25() throws Exception {
		testType( "binops.py", "ls4", "list" );
	}

	public void testEval26() throws Exception {
		testType( "binops.py", "t1", "tuple" );
	}

	public void testEval27() throws Exception {
		testType( "binops.py", "t2", "tuple" );
	}

	public void testEval28() throws Exception {
		testType( "binops.py", "t3", "tuple" );
	}

	public void testEval29() throws Exception {
		testType( "binops.py", "ts1", "tuple" );
	}

	public void testEval30() throws Exception {
		testType( "binops.py", "ts2", "tuple" );
	}

	public void testEval31() throws Exception {
		testType( "binops.py", "ts3", "tuple" );
	}

	public void REM_testEval32() throws Exception {
		testType( "binops.py", "ts4", "tuple" );
	}

	public void testEval33() throws Exception {
		testType( "binops.py", "d1", "dict" );
	}

	public void testEval34() throws Exception {
		testType( "binops.py", "d2", "dict" );
	}

	public void testEval35() throws Exception {
		testType( "binops.py", "bool0", "boolean" );
	}

	public void testEval36() throws Exception {
		testType( "binops.py", "bool1", "boolean" );
	}

	public void testEval37() throws Exception {
		testType( "binops.py", "bool2", "boolean" );
	}

	public void testEval38() throws Exception {
		testType( "binops.py", "bool3", "boolean" );
	}

	public void testEval39() throws Exception {
		testType( "binops.py", "bool4", "boolean" );
	}

	public void testEval40() throws Exception {
		testType( "binops.py", "bool5", "boolean" );
	}

	public void testEval41() throws Exception {
		testType( "binops.py", "bool6", "boolean" );
	}

	public void testEval42() throws Exception {
		testType( "binops.py", "bool7", "boolean" );
	}

	public void testEval43() throws Exception {
		testType( "binops.py", "bool8", "boolean" );
	}

	public void testEval44() throws Exception {
		testType( "binops.py", "bool9", "boolean" );
	}

	public void testEval45() throws Exception {
		testType( "binops.py", "bool10", "boolean" );
	}

	public void testEval46() throws Exception {
		testType( "binops.py", "bool11", "boolean" );
	}

	public void testEval47() throws Exception {
		testType( "binops.py", "bool12", "boolean" );
	}

	public void testEval48() throws Exception {
		testType( "binops.py", "bool13", "boolean" );
	}

	public void testEval49() throws Exception {
		testType( "binops.py", "bool14", "boolean" );
	}

}
