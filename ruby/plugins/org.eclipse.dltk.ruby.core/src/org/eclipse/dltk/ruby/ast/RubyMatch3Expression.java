package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyMatch3Expression extends ASTNode {

	private final ASTNode receiver;
	private final ASTNode value;

	public RubyMatch3Expression(int start, int end, ASTNode receiver, ASTNode value) {
		super(start, end);
		this.receiver = receiver;
		this.value = value;
	}

	public ASTNode getReceiver() {
		return receiver;
	}

	public ASTNode getValue() {
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
