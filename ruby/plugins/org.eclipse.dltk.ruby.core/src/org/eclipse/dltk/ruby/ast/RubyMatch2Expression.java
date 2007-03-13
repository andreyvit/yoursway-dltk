package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class RubyMatch2Expression extends Expression {

	private final Expression receiver;
	private final Expression value;

	public RubyMatch2Expression(int start, int end, Expression receiver,
			Expression value) {
		super(start, end);
		this.receiver = receiver;
		this.value = value;
	}

	public Expression getReceiver() {
		return receiver;
	}

	public Expression getValue() {
		return value;
	}

	public int getKind() {
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( this.receiver != null ) {
				this.receiver.traverse( visitor );
			}
			if( this.value != null ) {
				this.value.traverse( visitor );
			}			
		}
		visitor.endvisit( this );
	}

}
