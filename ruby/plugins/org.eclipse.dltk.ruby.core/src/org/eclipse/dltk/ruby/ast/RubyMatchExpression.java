package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyMatchExpression extends ASTNode {

	private final ASTNode regexp;

	public RubyMatchExpression(int start, int end, ASTNode regexp) {
		super(start, end);
		this.regexp = regexp;
	}

	public ASTNode getRegexp() {
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
