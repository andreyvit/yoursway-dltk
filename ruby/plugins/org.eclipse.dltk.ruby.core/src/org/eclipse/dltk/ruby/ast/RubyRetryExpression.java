package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;

public class RubyRetryExpression extends CallExpression {

	public RubyRetryExpression(int start, int end) {
		super(start, end, null, "retry", CallArgumentsList.EMPTY);
	}

}
