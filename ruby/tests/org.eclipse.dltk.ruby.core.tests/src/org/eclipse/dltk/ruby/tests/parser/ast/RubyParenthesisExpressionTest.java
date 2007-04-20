package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyParenthesisExpression;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyParenthesisExpressionTest extends AbstractASTTest {

	public RubyParenthesisExpressionTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyParenthesisExpression.class;	
	}
	
	public void test0 () throws Exception {
		String text = "";
		checkNode (text, 0, text.length());
	}		
	
}
