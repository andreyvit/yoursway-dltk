
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
public class genTestclass1 extends AbstractModelTests
{
	public genTestclass1(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
		    
	public static Test suite() {
	    return new Suite( genTestclass1.class);
	}
	private String prj = "eval0_class1.py";    			    
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
		testType( "class1.py", "a", "class:A instance" );
	}

	public void testEval1() throws Exception {
		testType( "class1.py", "b", "class:B instance" );
	}

	public void testEval2() throws Exception {
		testType( "class1.py", "d", "class:D instance" );
	}

	public void testEval3() throws Exception {
		testType( "class1.py", "c1", "number" );
	}

	public void testEval4() throws Exception {
		testType( "class1.py", "c2", "number" );
	}

	public void testEval5() throws Exception {
		testType( "class1.py", "c3", "string" );
	}

	public void testEval6() throws Exception {
		testType( "class1.py", "c4", "dict" );
	}

	public void testEval7() throws Exception {
		testType( "class1.py", "c5", "string" );
	}

	public void testEval8() throws Exception {
		testType( "class1.py", "c6", "list" );
	}

	public void testEval9() throws Exception {
		testType( "class1.py", "e1", "string" );
	}

	public void testEval10() throws Exception {
		testType( "class1.py", "e2", "number" );
	}

	public void testEval11() throws Exception {
		testType( "class1.py", "e3", "number" );
	}

	public void testEval12() throws Exception {
		testType( "class1.py", "e4", "string" );
	}

	public void testEval13() throws Exception {
		testType( "class1.py", "e5", "dict" );
	}

	public void testEval14() throws Exception {
		testType( "class1.py", "e6", "string" );
	}

	public void testEval15() throws Exception {
		testType( "class1.py", "e7", "list" );
	}

	public void testEval16() throws Exception {
		testType( "class1.py", "e8", "number" );
	}

	public void testEval17() throws Exception {
		testType( "class1.py", "e9", "string" );
	}

	public void testEval18() throws Exception {
		testType( "class1.py", "e10", "dict" );
	}

	public void testEval19() throws Exception {
		testType( "class1.py", "e11", "string" );
	}

	public void testEval20() throws Exception {
		testType( "class1.py", "e12", "list" );
	}

	public void testEval21() throws Exception {
		testType( "class1.py", "f0", "string" );
	}

	public void testEval22() throws Exception {
		testType( "class1.py", "f1", "string" );
	}

	public void testEval23() throws Exception {
		testType( "class1.py", "f2", "number" );
	}

	public void testEval24() throws Exception {
		testType( "class1.py", "f3", "number" );
	}

	public void testEval25() throws Exception {
		testType( "class1.py", "f4", "string" );
	}

	public void testEval26() throws Exception {
		testType( "class1.py", "f5", "dict" );
	}

	public void testEval27() throws Exception {
		testType( "class1.py", "f6", "string" );
	}

	public void testEval28() throws Exception {
		testType( "class1.py", "f7", "list" );
	}

	public void testEval29() throws Exception {
		testType( "class1.py", "f8", "number" );
	}

	public void testEval30() throws Exception {
		testType( "class1.py", "f9", "string" );
	}

	public void testEval31() throws Exception {
		testType( "class1.py", "f10", "dict" );
	}

	public void testEval32() throws Exception {
		testType( "class1.py", "f11", "string" );
	}

	public void testEval33() throws Exception {
		testType( "class1.py", "f12", "list" );
	}

	public void testEval34() throws Exception {
		testType( "class1.py", "f13", "number" );
	}

	public void testEval35() throws Exception {
		testType( "class1.py", "f14", "string" );
	}

	public void testEval36() throws Exception {
		testType( "class1.py", "f15", "dict" );
	}

	public void testEval37() throws Exception {
		testType( "class1.py", "f16", "string" );
	}

	public void testEval38() throws Exception {
		testType( "class1.py", "f17", "list" );
	}

}
