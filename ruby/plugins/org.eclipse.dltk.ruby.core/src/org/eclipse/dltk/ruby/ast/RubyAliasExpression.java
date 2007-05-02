package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyAliasExpression extends ASTNode {

	private final String oldValue;
	private final String newValue;
	
	public String getOldValue() {
		return oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public RubyAliasExpression(int start, int end, String oldValue, String newValue) {
		super(start, end);
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {			
			visitor.endvisit(this);
		}
	}	

}
