package org.eclipse.dltk.ruby.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.Decorator;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;

public class RubySingletonMethodDeclaration extends MethodDeclaration {
	private final Expression receiver;

	public RubySingletonMethodDeclaration(String name, int nameStart,
			int nameEnd, int declStart, int declEnd, Expression receiver) {
		super(name, nameStart, nameEnd, declStart, declEnd);
		this.receiver = receiver;
	}

	public Expression getReceiver() {
		return receiver;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		List decorators = this.getDecorators();
		Block body = this.getBody();
		if (visitor.visit(this)) {
			// Decorators
			if (decorators != null) {
				Iterator it = decorators.iterator();
				while (it.hasNext()) {
					Decorator dec = (Decorator) it.next();
					dec.traverse(visitor);
				}
			}

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
