package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyColonExpression;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyColonExpressionTest extends AbstractASTTest {

	public RubyColonExpressionTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyColonExpression.class;	
	}
	
	public void test0 () throws Exception {
		String text = "::A";
		checkNode (text, 0, text.length());
	}
	
	public void test1 () throws Exception {
		String text = "::A::B";
		checkNode (text, 0, text.length());
	}
	
	public void test2 () throws Exception {
		String text = "A::B";
		checkNode (text, 0, text.length());
	}
	
	public void test3 () throws Exception {
		String text = "A::B::foo";
		checkNode (text, 0, 4);
	}
	
}
