package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class HashPairExpression extends Expression {

	private final Statement key;
	private final Statement value;

	public HashPairExpression(int start, int end, Statement key, Statement value) {
		super(start, end);
		this.key = key;
		this.value = value;
	}

	public Statement getKey() {
		return key;
	}

	public Statement getValue() {
		return value;
	}

	public int getKind() {
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (key != null)
				key.traverse(visitor);
			if (value != null)
				value.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
