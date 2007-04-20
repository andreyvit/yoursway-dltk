package org.eclipse.dltk.ast.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.utils.CorePrinter;

public class CompoundStatement extends Statement {

	private final List statements;

	public CompoundStatement(int start, int end, List statements) {
		super(start, end);
		this.statements = statements;
	}
	
	public CompoundStatement(int start, int end) {
		super(start, end);
		this.statements = new ArrayList();
	}
	
	public CompoundStatement() {
		super(0, -1);
		this.statements = new ArrayList ();
	}

	public void addStatement(Statement s) {
		if (s != null) {
			statements.add(s);
		}
	}
	
	public void addExpression(Expression ex) {
		if (ex != null) {
			statements.add(ex);
		}
	}

	public List getStatements() {
		return statements;
	}
	
	public void setStatements(List l) {
		this.statements.clear();
		this.statements.addAll(l);
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (statements != null) {
				for (Iterator iter = statements.iterator(); iter.hasNext();) {
					Statement s = (Statement) iter.next();
					s.traverse(visitor);				
				}
			}
			visitor.endvisit(this);
		}
	}

}
