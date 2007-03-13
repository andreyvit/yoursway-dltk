package org.eclipse.dltk.ast.expressions;

public class NilLiteral extends Literal {

	public NilLiteral(int start, int end) {
		super(start, end);
	}

	public String getValue() {
		return "nil";
	}

	public int getKind() {
		return 0;
	}

}
