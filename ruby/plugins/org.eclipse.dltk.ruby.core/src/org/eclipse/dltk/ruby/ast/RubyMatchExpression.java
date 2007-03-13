package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class RubyMatchExpression extends Expression {

	private final Expression regexp;

	public RubyMatchExpression(int start, int end, Expression regexp) {
		super(start, end);
		this.regexp = regexp;
	}

	public Expression getRegexp() {
		return regexp;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {

		if( visitor.visit( this ) ) {
			if( this.regexp != null ) {
				this.regexp.traverse( visitor );
			}			
		}
		visitor.endvisit( this );

	}

}
