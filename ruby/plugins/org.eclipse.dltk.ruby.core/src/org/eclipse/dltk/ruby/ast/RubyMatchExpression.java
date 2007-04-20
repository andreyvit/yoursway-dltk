package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyMatchExpression extends Expression {

	private final Statement regexp;

	public RubyMatchExpression(int start, int end, Statement regexp) {
		super(start, end);
		this.regexp = regexp;
	}

	public Statement getRegexp() {
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
