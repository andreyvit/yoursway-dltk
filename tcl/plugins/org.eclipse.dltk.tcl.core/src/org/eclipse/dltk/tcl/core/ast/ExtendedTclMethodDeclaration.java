package org.eclipse.dltk.tcl.core.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;

public class ExtendedTclMethodDeclaration extends MethodDeclaration {

	private ASTNode declaringXOTclType;
	private SimpleReference typeNameRef;
	/**
	 * Proc and instproc type 
	 */
	public static final int KIND_PROC = 0;
	public static final int KIND_INSTPROC = 1;
	private int methodKind = KIND_PROC;

	public ExtendedTclMethodDeclaration(DLTKToken function_t, DLTKToken name) {
		super(function_t, name);
	}

	public ExtendedTclMethodDeclaration(String name, int nameStart,
			int nameEnd, int declStart, int declEnd) {
		super(name, nameStart, nameEnd, declStart, declEnd);
	}

	public ExtendedTclMethodDeclaration(int start, int end) {
		super(start, end);
	}

	public ASTNode getDeclaringType() {
		return declaringXOTclType;
	}

	public void setDeclaringType(ASTNode declaringXOTclType) {
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

	public void setKind(int kind) {
		this.methodKind = kind;
	}

}