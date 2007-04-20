package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyModuleDeclaration;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyModuleDeclarationTest extends AbstractASTTest {

	public RubyModuleDeclarationTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyModuleDeclaration.class;	
	}
	
	public void test0 () throws Exception {
		String text = "module A\nend";
		checkNode (text, 0, text.length());
	}
	
	public void test1 () throws Exception {
		String text = "module A; end";
		checkNode (text, 0, text.length());
	}
	
	public void test2 () throws Exception {
		String text = "module ::A; end";
		checkNode (text, 0, text.length());
	}
	
	
	public void test3 () throws Exception {
		String text = "module A::B; end";
		checkNode (text, 0, text.length());
	}
	
	public void test32 () throws Exception {
		String text = "module A::B::C; end";
		checkNode (text, 0, text.length());
	}
	public void test4 () throws Exception {
		String text = "module ::A::B; end";
		checkNode (text, 0, text.length());
	}
	
	public void test42 () throws Exception {
		String text = "module ::A::B::C; end";
		checkNode (text, 0, text.length());
	}
	
}
