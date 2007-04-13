package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;


public class RubySingletonClassDeclaration extends TypeDeclaration {

	private Statement receiver;
	
	public RubySingletonClassDeclaration(String friendlyName, int nameStart,
			int nameEnd, int start, int end) {
		super(friendlyName, nameStart, nameEnd, start, end);
		this.setModifier(Modifiers.AccStatic);
	}

	public Statement getReceiver() {
		return receiver;
	}

	public void setReceiver(Statement receiver) {
		this.receiver = receiver;
	}
	
	public void traverse( ASTVisitor visitor ) throws Exception {

		if( visitor.visit( this ) ) {
			if( this.fSuperClasses != null ) {
				this.fSuperClasses.traverse( visitor );
			}
			if( this.fBody != null ) {
				fBody.traverse( visitor );
			}
			if (this.receiver != null) {
				receiver.traverse(visitor);
			}
		}
		visitor.endvisit( this );
	}
	

}
