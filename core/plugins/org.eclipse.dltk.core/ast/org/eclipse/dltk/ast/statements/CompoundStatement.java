package org.eclipse.dltk.ast.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class CompoundStatement extends Statement {

	private final List statements;

	public CompoundStatement(int start, int end, List statements) {
		super(start, end);
		this.statements = statements;
	}
	
	public CompoundStatement() {
		super(0, -1);
		this.statements = new ArrayList ();
	}

	public void addStatement(Statement s) {
		statements.add(s);
	}

	public List getStatements() {
		return statements;
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
			for (Iterator iter = statements.iterator(); iter.hasNext();) {
				Statement s = (Statement) iter.next();
				s.traverse(visitor);				
			}
			visitor.endvisit(this);
		}
	}

}
