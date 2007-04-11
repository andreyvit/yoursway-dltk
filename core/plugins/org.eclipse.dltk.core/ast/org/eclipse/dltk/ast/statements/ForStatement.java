package org.eclipse.dltk.ast.statements;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.utils.CorePrinter;

/**
 * For Statement.
 *  for <target> in <list> do 
 *  	<action>
 *  end
 */
public class ForStatement extends Statement {

	private Statement fTarget;
	private ExpressionList fList;
	private Statement fAction;
	
	
	public ForStatement(int start, int end, Statement target,
			ExpressionList list, Statement action) {
		super(start, end);
		fTarget = target;
		fList = list;
		fAction = action;
	}
	
	

	public ForStatement(int start, int end) {
		super(start, end);
	}



	public void setTarget(Statement target) {
		fTarget = target;
	}



	public void setList(ExpressionList list) {
		fList = list;
	}



	public void setAction(Statement action) {
		fAction = action;
	}



	public void traverse(ASTVisitor pVisitor) throws Exception {

		if (pVisitor.visit(this)) {
			if (fTarget != null) {
				fTarget.traverse(pVisitor);
			}
			if (fList != null) {
				fList.traverse(pVisitor);
			}			
			if (fAction != null) {
				fAction.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}

	public int getKind() {
		return S_FOR;
	}

	public Statement getAction() {
		return fAction;
	}

	
	public Statement getTarget() {
		return fTarget;
	}

	public ExpressionList getList() {
		return fList;
	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("for:");
		if (this.fTarget != null) {
			output.formatPrintLn("target:");
			this.fTarget.printNode(output);
		}
		if (this.fList != null) {
			output.formatPrintLn("list:");
			this.fList.printNode(output);
		}
		if (this.fAction != null) {
			output.indent();
			this.fAction.printNode(output);
			output.dedent();
		}

	}
}
