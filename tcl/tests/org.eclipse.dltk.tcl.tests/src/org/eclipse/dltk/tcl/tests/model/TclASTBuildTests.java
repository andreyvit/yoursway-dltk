package org.eclipse.dltk.tcl.tests.model;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.tests.model.AbstractModelTests;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;
import org.eclipse.dltk.tcl.tests.TclTestsPlugin;
import org.eclipse.dltk.utils.CorePrinter;


public class TclASTBuildTests extends AbstractModelTests
{
	public TclASTBuildTests(String name) {
		super( TclTestsPlugin.PLUGIN_NAME, name);
	}
	
	public static Test suite() {
		return new Suite( TclASTBuildTests.class);
	}
	
	public void setUpSuite() throws Exception {
		super.setUpSuite();		
	}
	public void tearDownSuite() throws Exception {
		super.tearDownSuite();
	}
	
	public void testBuildExtendedAST001() throws Exception {
		String prj = "prj1";
		//IDLTKProject project = setUpScriptProject( prj );
		
		ISourceModule module = this.getSourceModule( prj, "src", new Path("module0.tcl") );
		
		String source = module.getSource();
		
		TclSourceParser parser = new TclSourceParser();
		ModuleDeclaration decl = parser.parse(source);
		CorePrinter printer = new CorePrinter(System.out, true);
		decl.printNode(printer);
		
		//TypeDeclaration[] types = decl.getTypes();
		decl.printNode(printer);
		
		
		deleteProject( prj );
	}
}
