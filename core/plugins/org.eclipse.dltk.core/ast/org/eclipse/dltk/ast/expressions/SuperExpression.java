package org.eclipse.dltk.ast.expressions;

import org.eclipse.dltk.ast.ASTVisitor;

public class SuperExpression extends Expression {

	private CallArgumentsList args;

	public SuperExpression(int start, int end, CallArgumentsList args) {
		super(start, end);
		this.args = args;
	}

	public CallArgumentsList getArgs() {
		return args;
	}

	public void setArgs(CallArgumentsList args) {
		this.args = args;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( args != null ) {
				args.traverse( visitor );
			}
			
			visitor.endvisit( this );
		}
	}

}
