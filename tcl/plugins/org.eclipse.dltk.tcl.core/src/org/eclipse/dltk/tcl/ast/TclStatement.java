package org.eclipse.dltk.tcl.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class TclStatement extends Statement {
	private List expressions;

	public TclStatement(List expressions) {
		if (!expressions.isEmpty()) {
			// First
			Expression first = (Expression) expressions.get(0);
			setStart(first.sourceStart());

			// Last
			Expression last = (Expression) expressions
					.get(expressions.size() - 1);
			setEnd(last.sourceEnd());
		}

		this.expressions = expressions;
	}

	public List getExpressions() {
		return expressions;
	}

	public Expression getAt(int index) {
		if (index >= 0 && index < expressions.size()) {
			return (Expression) expressions.get(index);
		}

		return null;
	}

	public int getCount() {
		return expressions.size();
	}

	public int getKind() {
		return TclConstants.TCL_STATEMENT;
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
	}

	public void printNode(CorePrinter output) {
		if (this.expressions != null) {
			output.formatPrintLn("tcl statement:");
			Iterator i = this.expressions.iterator();
			while (i.hasNext()) {
				ASTNode node = (ASTNode) i.next();
				node.printNode(output);
				output.formatPrintLn(" ");
			}
		}
	}

	public String toString() {
		String value = "";
		if (this.expressions != null) {
			Iterator i = this.expressions.iterator();
			while (i.hasNext()) {
				ASTNode node = (ASTNode) i.next();
				value += node.toString();
				value += " ";
			}
		}

		return value;
	}
}
