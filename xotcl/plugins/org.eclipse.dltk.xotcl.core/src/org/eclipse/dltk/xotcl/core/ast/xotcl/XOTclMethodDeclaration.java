package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

public class XOTclMethodDeclaration extends MethodDeclaration {
	private ASTNode declaringXOTclType;
	private SimpleReference typeNameRef;
	
	/**
	 * Proc and instproc type 
	 */
	public static final int KIND_PROC = 0;
	public static final int KIND_INSTPROC = 1;
	private int methodKind = KIND_PROC;
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
	public int getKind() {
		return this.methodKind;
	}
	public void setKind( int kind ) {
		this.methodKind = kind;
	}
}
