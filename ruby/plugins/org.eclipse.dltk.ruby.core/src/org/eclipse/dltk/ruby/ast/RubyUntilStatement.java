/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
		output.formatPrintLn("while"); //$NON-NLS-1$
		if (this.fCondition != null) {
			this.fCondition.printNode(output);
		}
		if (this.fAction != null) {
			output.indent();
			this.fAction.printNode(output);
			output.dedent();
		}
		output.formatPrint(""); //$NON-NLS-1$
	}
}
