
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
public class genTestclass0 extends AbstractModelTests
{
	public genTestclass0(String name) {
		super( PythonTestsPlugin.PLUGIN_NAME, name);
	}
		    
	public static Test suite() {
	    return new Suite( genTestclass0.class);
	}
	private String prj = "eval0_class0.py";    			    
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
		testType( "class0.py", "a", "class:A instance" );
	}

	public void testEval1() throws Exception {
		testType( "class0.py", "b", "class:B instance" );
	}

	public void testEval2() throws Exception {
		testType( "class0.py", "d", "class:D instance" );
	}

}
