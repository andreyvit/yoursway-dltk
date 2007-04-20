package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyBacktickStringLiteral;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyBacktickStringLiteralTest extends AbstractASTTest {

	public RubyBacktickStringLiteralTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyBacktickStringLiteral.class;	
	}
	
	public void test0 () throws Exception {
		String text = "`abc`";
		checkNode (text, 0, text.length());
	}	
	
	public void test1 () throws Exception {
		String text = "`a\\\\b\\`c`";
		checkNode (text, 0, text.length());
	}
	
	public void test2 () throws Exception {
		String text = "`pwd`.to_a";
		checkNode (text, 0, text.length() - ".to_a".length());
	}
	
	public void test3 () throws Exception {
		String text = "\n\n`pwd`.to_a\n\n";
		checkNode (text, 2, text.length() - ".to_a\n\n".length());
	}
	
}
