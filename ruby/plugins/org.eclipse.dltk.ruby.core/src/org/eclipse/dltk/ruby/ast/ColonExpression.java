package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class ColonExpression extends Expression {
	private final Statement left;
	private final String name;


	public Statement getLeft() {
		return left;
	}

	public String getName() {
		return name;
	}

	public ColonExpression (String name, Statement left) {
		this.name = name;
		this.left = left;
	}
	
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( left != null ) {
				left.traverse( visitor );
			}			
			visitor.endvisit( this );
		}
	}

	public boolean isFull() {
		return left == null;
	}
	
	public void printNode(CorePrinter output) {
		output.formatPrintLn("ColonExpression" + this.getSourceRange().toString() + ":");
		output.indent();
		if (isFull())
			output.formatPrint("::");
		if (this.left != null) {
			this.left.printNode(output);
		}
		output.formatPrint(this.getName());
		output.dedent();
	}

}
