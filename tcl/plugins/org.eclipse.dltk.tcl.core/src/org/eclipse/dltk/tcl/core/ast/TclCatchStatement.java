package org.eclipse.dltk.tcl.core.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
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
	public List getStatements() {
		if( this.block != null ) {
			return this.block.getStatements();
		}
		return new ArrayList();
	}
}
