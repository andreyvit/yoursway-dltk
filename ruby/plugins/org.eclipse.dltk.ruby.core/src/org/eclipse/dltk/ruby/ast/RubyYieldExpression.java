package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;

public class RubyYieldExpression extends CallExpression {

	public RubyYieldExpression(int start, int end, CallArgumentsList args) {
		super(start, end, null, "yield", args);
	}

}
