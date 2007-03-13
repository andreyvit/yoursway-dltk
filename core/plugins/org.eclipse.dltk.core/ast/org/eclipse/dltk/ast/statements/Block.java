package org.eclipse.dltk.ast.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

public class Block extends Expression {
	private List statements;

	public Block() {
		this.statements = new ArrayList();
	}

	public Block(int start, int end) {
		this(start, end, null);
	}
	
	public Block(int start, int end, List statems) {
		super(start, end);
		if (statems == null)
			statems = new ArrayList();
		this.statements = new ArrayList(statems);
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			Iterator it = statements.iterator();
			while (it.hasNext()) {
				((Statement) it.next()).traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

	public int getKind() {
		return S_BLOCK;
	}

	public void acceptStatements(List statems) {
		if (statems == null) {
			throw new IllegalArgumentException();
		}

		statements.addAll(statems);
	}

	public List getStatements() {
		return statements;
	}

	public void addStatement(Statement statem) {
		if (statem == null) {
			throw new IllegalArgumentException();
		}

		statements.add(statem);
	}

	public void printNode(CorePrinter output) {
		output.indent();
		Iterator it = statements.iterator();
		while (it.hasNext()) {
			((Statement) it.next()).printNode(output);
			output.formatPrint("");
		}
		output.formatPrint("");
		output.dedent();
	}
	
	
}
