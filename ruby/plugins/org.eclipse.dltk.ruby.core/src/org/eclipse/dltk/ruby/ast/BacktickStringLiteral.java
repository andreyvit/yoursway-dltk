package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.StringLiteral;

public class BacktickStringLiteral extends StringLiteral {

	public BacktickStringLiteral(int start, int end, String value) {
		super(start, end, value);
	}

}
