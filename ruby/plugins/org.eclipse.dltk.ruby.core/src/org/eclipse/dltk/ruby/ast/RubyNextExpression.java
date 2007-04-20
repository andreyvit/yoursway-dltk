package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;

public class RubyNextExpression extends CallExpression {

	public RubyNextExpression(int start, int end, CallArgumentsList args) {
		super(start, end, null, "next", args);
	}

}
