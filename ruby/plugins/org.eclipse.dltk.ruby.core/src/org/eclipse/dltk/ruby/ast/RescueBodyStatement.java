package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class RescueBodyStatement extends Statement {

	private Statement bodyNode;
	private Statement exceptionNode;
	private Statement optNode;

	public RescueBodyStatement(int start, int end, Statement bodyNode,
			Statement exceptionNode, Statement optNode) {
		super(start, end);
		this.bodyNode = bodyNode;
		this.exceptionNode = exceptionNode;
		this.optNode = optNode;
	}
	
	

	public RescueBodyStatement(int start, int end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}



	public Statement getBodyNode() {
		return bodyNode;
	}

	public Statement getExceptionNode() {
		return exceptionNode;
	}

	public Statement getOptNode() {
		return optNode;
	}
	
	

	public void setBodyNode(Statement bodyNode) {
		this.bodyNode = bodyNode;
	}



	public void setExceptionNode(Statement exceptionNode) {
		this.exceptionNode = exceptionNode;
	}



	public void setOptNode(Statement optNode) {
		this.optNode = optNode;
	}



	public int getKind() {
		return 0;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( this.bodyNode != null ) {
				this.bodyNode.traverse( visitor );
			}
			if( this.exceptionNode != null ) {
				this.exceptionNode.traverse( visitor );
			}
			if( this.optNode != null ) {
				this.optNode.traverse( visitor );
			}
		}
		visitor.endvisit( this );
	}

}
