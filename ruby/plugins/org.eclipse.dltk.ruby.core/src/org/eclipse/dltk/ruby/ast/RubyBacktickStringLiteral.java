package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.StringLiteral;

public class RubyBacktickStringLiteral extends StringLiteral {

	public RubyBacktickStringLiteral(int start, int end, String value) {
		super(start, end, value);
	}

}
