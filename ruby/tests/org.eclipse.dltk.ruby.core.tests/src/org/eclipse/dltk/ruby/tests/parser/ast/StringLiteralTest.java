package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class StringLiteralTest extends AbstractASTTest {

	public StringLiteralTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return StringLiteral.class;	
	}
	
	public void test0 () throws Exception {
		String text = "\"foo\"";
		checkNode (text, 0, text.length());
	}		
	
	
}
