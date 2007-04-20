package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyMatch3Expression extends Expression {

	private final Statement receiver;
	private final Statement value;

	public RubyMatch3Expression(int start, int end, Statement receiver,
			Statement value) {
		super(start, end);
		this.receiver = receiver;
		this.value = value;
	}

	public Statement getReceiver() {
		return receiver;
	}

	public Statement getValue() {
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
