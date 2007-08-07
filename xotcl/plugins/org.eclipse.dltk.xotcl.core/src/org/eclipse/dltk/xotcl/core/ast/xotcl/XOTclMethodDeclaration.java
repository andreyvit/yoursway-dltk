package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

public class XOTclMethodDeclaration extends MethodDeclaration {
	private ASTNode declaringXOTclType;
	private SimpleReference typeNameRef;
	public XOTclMethodDeclaration(int start, int end) {
		super(start, end);
	}

	public XOTclMethodDeclaration(String name, int nameStart, int nameEnd,
			int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}

	public ASTNode getDeclaringXOTclType() {
		return declaringXOTclType;
	}

	public void setDeclaringXOTclType(ASTNode declaringXOTclType) {
		this.declaringXOTclType = declaringXOTclType;
	}

	public SimpleReference getTypeNameRef() {
		return typeNameRef;
	}

	public void setTypeNameRef(SimpleReference typeNameRef) {
		this.typeNameRef = typeNameRef;
	}
	
}
