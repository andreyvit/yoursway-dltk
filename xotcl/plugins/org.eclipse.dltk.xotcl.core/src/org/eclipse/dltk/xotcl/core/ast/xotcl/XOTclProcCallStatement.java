package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.tcl.ast.TclStatement;

public class XOTclProcCallStatement extends Statement {
	private SimpleReference methodName;
	private ASTListNode arguments;
	private ASTNode object;
	private SimpleReference instName;
	private TclStatement originalStatement;
	public XOTclProcCallStatement(SimpleReference name, ASTNode type, ASTListNode args) {
		this.methodName = name;
		this.object = type;
		this.arguments = args;
	}
	public int getKind() {
		return 0;
	}

	public TclStatement getOriginalStatement() {
		return this.originalStatement;
	}
	public void setOriginalStatement(TclStatement originalStatement) {
		this.originalStatement = originalStatement;
	}
	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit(this)) {
			if( this.methodName != null ) {
				this.methodName.traverse(visitor);
			}
			if( this.arguments != null ) {
				this.arguments.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}
	
	public ASTNode getObject() {
		return object;
	}
	public void setObject(ASTNode type) {
		this.object = type;
	}
	public SimpleReference getCallName() {
		return this.methodName;
	}
	public void setInstNameRef(SimpleReference at) {
		this.instName = at;
	}
	public SimpleReference getInstNameRef() {
		return this.instName;
	}
}
