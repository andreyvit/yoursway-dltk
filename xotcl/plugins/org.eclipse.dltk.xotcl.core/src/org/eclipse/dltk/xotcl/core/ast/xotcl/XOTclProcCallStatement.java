package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;

public class XOTclProcCallStatement extends Statement {
	private SimpleReference methodName;
	private ASTListNode arguments;
	private TypeDeclaration type;
	private SimpleReference instName;
	public XOTclProcCallStatement(SimpleReference name, TypeDeclaration type, ASTListNode args) {
		this.methodName = name;
		this.type = type;
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
	
	public TypeDeclaration getType() {
		return type;
	}
	public void setType(TypeDeclaration type) {
		this.type = type;
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
