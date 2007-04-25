package org.eclipse.dltk.ruby.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyCaseStatement extends Statement {

	private Statement target;
	private List whens;
	private Statement elseWhen;

	public RubyCaseStatement(int start, int end) {
		super(start, end);
	}

	public Statement getTarget() {
		return target;
	}

	public void setTarget(Statement target) {
		this.target = target;
	}

	public List getWhens() {
		return whens;
	}

	public void setWhens(List whens) {
		this.whens = whens;
	}

	public Statement getElseWhen() {
		return elseWhen;
	}

	public void setElseWhen(Statement elseWhen) {
		this.elseWhen = elseWhen;
	}

	public int getKind() {
		return S_SWITCH;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( this.target != null ) {
				this.target.traverse( visitor );
			}
			if( this.elseWhen != null ) {
				this.elseWhen.traverse( visitor );
			}			
			if (this.whens != null) {
				for (Iterator iterator = this.whens.iterator(); iterator
						.hasNext();) {
					ASTNode node = (ASTNode) iterator.next();
					if (node != null)
						node.traverse(visitor);					
				}
			}
		}
		visitor.endvisit( this );	
	}

}
