package org.eclipse.dltk.ast.expressions;

import java.util.List;

public class CallArgumentsList extends ExpressionList {
	public static final CallArgumentsList EMPTY = new CallArgumentsList() {

		public void addExpression(Expression ex) {
			throw new IllegalStateException("This object is unmodifiable");
		}

		public void setExpresssions(List exs) {
			throw new IllegalStateException("This object is unmodifiable");
		}

		public void setEnd(int end) {
			throw new IllegalStateException("This object is unmodifiable");
		}

		public void setStart(int start) {
			throw new IllegalStateException("This object is unmodifiable");
		}

	};

	public CallArgumentsList() {
		super();
	}

	public CallArgumentsList(int start, int end) {
		super(start, end);
	}
}
