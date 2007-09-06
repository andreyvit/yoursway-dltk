package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;

public class XOTclMethodCallStatement extends Statement {
	private SimpleReference methodName;
	private ASTListNode arguments;
	private XOTclInstanceVariable instanceVariable;
	private SimpleReference instName;
	public XOTclMethodCallStatement(SimpleReference name, XOTclInstanceVariable var, ASTListNode args) {
		this.methodName = name;
		this.instanceVariable = var;
		this.arguments = args;
	}
	public int getKind() {
		return 0;
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
	public XOTclInstanceVariable getInstanceVariable() {
		return this.instanceVariable;
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
	public ASTListNode getArguments() {
		return this.arguments;
	}
}
