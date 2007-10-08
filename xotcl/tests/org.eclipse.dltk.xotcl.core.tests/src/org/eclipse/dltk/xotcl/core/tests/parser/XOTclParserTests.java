package org.eclipse.dltk.xotcl.core.tests.parser;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.tcl.core.ITclSourceParser;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;

public class XOTclParserTests extends TestCase {
	
	public void testParseUtil001() throws Throwable {
		String content = 
			"set a {gamma[lappend $alfa 20]}";
		ModuleDeclaration module = this.parser( content );
		System.out.println("Cool");
	}
	
	private ASTNode[] findNodeByName(ModuleDeclaration module, final String name) throws Exception {
		final List results = new ArrayList();
		module.traverse( new ASTVisitor() {
			public boolean endvisit(TypeDeclaration s) throws Exception {
				if( s.getName().equals(name)) {
					return results.add(s);
				}
				return super.endvisit(s);
			}
			public boolean visit(MethodDeclaration s) throws Exception {
				if( s.getName().equals(name)) {
					return results.add(s);
				}
				return super.visit(s);
			}
		});
		return (ASTNode[]) results.toArray(new ASTNode[results.size()]);
	}
	private ModuleDeclaration parser(String content) {
		ITclSourceParser parser = new TclSourceParser();
		ModuleDeclaration module = parser.parse("file".toCharArray(), content.toCharArray(), null);
		assertNotNull(module);
		return module;
	}
}
