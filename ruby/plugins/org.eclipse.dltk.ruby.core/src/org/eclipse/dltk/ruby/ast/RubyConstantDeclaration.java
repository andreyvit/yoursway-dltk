package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyConstantDeclaration extends Expression {

	private final Expression path;
	private final SimpleReference name;
	private final Statement value;

	public RubyConstantDeclaration(Expression path, SimpleReference name, Statement value) {
		this.path = path;
		this.name = name;
		this.value = value;
	}

	public SimpleReference getName() {
		return name;
	}

	public Expression getPath() {
		return path;
	}

	public Statement getValue() {
		return value;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("ConstantDeclaration" + this.getSourceRange().toString() + ":(" + this.getName() + ")");
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (path != null) {
				path.traverse(pVisitor);
			}
			if (name != null) {
				name.traverse(pVisitor);
			}
			if (value != null) {
				value.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}
	
}
