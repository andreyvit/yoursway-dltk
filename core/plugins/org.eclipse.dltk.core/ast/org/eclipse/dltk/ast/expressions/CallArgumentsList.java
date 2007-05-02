package org.eclipse.dltk.ast.expressions;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTListNode;

public class CallArgumentsList extends ASTListNode {
	public static final CallArgumentsList EMPTY = new CallArgumentsList() {

		public void addNode(ASTNode s) {
			throw new IllegalStateException("This object is unmodifiable");
		}

		public void setChilds(List l) {
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
