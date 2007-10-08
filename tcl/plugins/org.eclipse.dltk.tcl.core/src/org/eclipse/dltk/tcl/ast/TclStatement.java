/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
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
			this.setStart(first.sourceStart());

			// Last
			Expression last = (Expression) expressions
					.get(expressions.size() - 1);
			this.setEnd(last.sourceEnd());
		}

		this.expressions = expressions;
	}

	public List getExpressions() {
		return this.expressions;
	}

	public Expression getAt(int index) {
		if (index >= 0 && index < this.expressions.size()) {
			return (Expression) this.expressions.get(index);
		}

		return null;
	}

	public int getCount() {
		return this.expressions.size();
	}

	public int getKind() {
		return TclConstants.TCL_STATEMENT;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if( this.expressions != null ) {
				for (int i = 0; i < this.expressions.size(); i++) {
					ASTNode node = (ASTNode) this.expressions.get(i);
					node.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

	public void printNode(CorePrinter output) {
		if (this.expressions != null) {
			output.formatPrintLn("");
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

	public void setExpressions(List asList) {
		this.expressions = asList;
	}
}
