package org.eclipse.dltk.ruby.ast;

import java.util.regex.Pattern;

import org.eclipse.dltk.ast.expressions.StringLiteral;

public class RubyRegexpExpression extends StringLiteral {

	private Pattern pattern;
	
	public RubyRegexpExpression(int start, int end, String value) {
		super(start, end, value);
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
