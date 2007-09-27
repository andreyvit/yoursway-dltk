package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class TclCatchStatement extends Statement {
	private Block block;
	private TclVariableDeclaration variable;
	public TclCatchStatement(Block block, TclVariableDeclaration variable, int start, int end) {
		super( start, end );
		this.block = block;
		this.variable = variable;
	}
	public int getKind() {
		return 0;
	}
	
	public TclVariableDeclaration getVariable() {
		return this.variable;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit(this) ) {
			if( this.block != null ) {
				this.block.traverse(visitor);
			}
			if (this.variable != null)
				this.variable.traverse(visitor);
			visitor.endvisit(this);
		}
	}
}
