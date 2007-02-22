package org.eclipse.dltk.ast.references;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * Used to hold index expression in ExtendedVariables.
 * 
 * @author haiodo
 * 
 */
public class IndexHolder extends Expression {
	/**
	 * Hond index expression.
	 */
	private Expression fIndex = null;

	/**
	 * Construc from start and end position and expression.
	 * 
	 * @param start -
	 *            start position.
	 * @param end -
	 *            end position.
	 * @param indexExpression -
	 *            index expression.
	 */
	public IndexHolder(int start, int end, Expression indexExpression) {
		super(start, end);
		this.fIndex = indexExpression;
	}

	/**
	 * Return Index kind.
	 */
	public int getKind() {
		return Expression.E_INDEX;
	}

	/**
	 * Traverse.
	 */
	public void traverse(ASTVisitor pVisitor) throws Exception {
		if (pVisitor.visit(this)) {
			if (this.fIndex != null) {
				this.fIndex.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}

	/**
	 * Testing purpose only. Used to print index.
	 */
	public void printNode(CorePrinter output) {
		output.formatPrintLn("[");
		if (this.fIndex != null) {
			this.fIndex.printNode(output);
		}
		output.formatPrintLn("]");
	}
}
