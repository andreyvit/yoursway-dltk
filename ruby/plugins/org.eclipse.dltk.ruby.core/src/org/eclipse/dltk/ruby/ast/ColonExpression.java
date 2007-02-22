package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class ColonExpression extends Expression {
	
		
	private final Expression left;
	private final String name;
	private final boolean full;


	public Expression getLeft() {
		return left;
	}

	public String getName() {
		return name;
	}

	public ColonExpression (String name, Expression left, boolean full) {
		this.name = name;
		this.left = left;
		this.full = full;		
	}
	
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( left != null ) {
				left.traverse( visitor );
			}			
			visitor.endvisit( this );
		}
	}

	public boolean isFull() {
		return full;
	}

}
