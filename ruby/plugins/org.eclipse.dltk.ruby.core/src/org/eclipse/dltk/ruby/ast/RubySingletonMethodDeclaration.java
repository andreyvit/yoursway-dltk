package org.eclipse.dltk.ruby.ast;

import java.util.Iterator;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;

public class RubySingletonMethodDeclaration extends MethodDeclaration {
	private final Statement receiver;

	public RubySingletonMethodDeclaration(String name, int nameStart,
			int nameEnd, int declStart, int declEnd, Statement receiver) {
		super(name, nameStart, nameEnd, declStart, declEnd);
		this.receiver = receiver;
	}

	public RubySingletonMethodDeclaration(DLTKToken function_t, DLTKToken name,
			Statement receiver) {
		super(function_t, name);
		this.receiver = receiver;
	}

	public Statement getReceiver() {
		return receiver;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		Block body = this.getBody();
		if (visitor.visit(this)) {
			
			// Receiver
			if (receiver != null)
				receiver.traverse(visitor);

			// Arguments
			Iterator it = arguments.iterator();
			while (it.hasNext()) {
				Argument arg = (Argument) it.next();
				arg.traverse(visitor);
			}

			// Body
			if (body != null) {
				body.traverse(visitor);
			}

			visitor.endvisit(this);
		}
	}
}
