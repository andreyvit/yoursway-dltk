package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * While statement.
 */
public class RubyUntilStatement extends ASTNode {
	private ASTNode fCondition;
	private ASTNode fAction;

	public RubyUntilStatement(DLTKToken token) {
		super(token);
	}

	public RubyUntilStatement(ASTNode condition, ASTNode action) {
		this.fCondition = condition;
		this.fAction = action;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (fCondition != null) {
				fCondition.traverse(pVisitor);
			}
			if (fAction != null) {
				fAction.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}

	public ASTNode getCondition() {
		return fCondition;
	}

	public ASTNode getAction() {
		return fAction;
	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("while");
		if (this.fCondition != null) {
			this.fCondition.printNode(output);
		}
		if (this.fAction != null) {
			output.indent();
			this.fAction.printNode(output);
			output.dedent();
		}
		output.formatPrint("");
	}
}
