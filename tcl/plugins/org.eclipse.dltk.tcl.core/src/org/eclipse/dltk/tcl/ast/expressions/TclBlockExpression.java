/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.ast.expressions;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.tcl.ast.TclConstants;
import org.eclipse.dltk.tcl.internal.parser.TclSourceParser;
import org.eclipse.dltk.utils.CorePrinter;

public class TclBlockExpression extends Expression {
	private String fBlockContent;

	public TclBlockExpression(int start, int end, String content) {
		setStart(start);
		setEnd(end);
		this.fBlockContent = content;
	}

	public int getKind() {
		return TclConstants.TCL_BLOCK_EXPRESSION;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			List statements = parseBlock();
			if (statements != null) {
				for (int i = 0; i < statements.size(); i++) {
					ASTNode node = (ASTNode) statements.get(i);
					node.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

	public void printNode(CorePrinter output) {
		output.formatPrintLn("tcl block:" + this.fBlockContent);
	}

	public String toString() {
		return "tcl block:" + this.fBlockContent;
	}

	public List parseBlock() {
		return parseBlock(sourceStart() + 1);
	}

	public List parseBlock(int startFrom) {
		if (fBlockContent == null) {
			return null;
		}

		String content = fBlockContent.substring(1, fBlockContent.length() - 1);
		TclSourceParser parser = new TclSourceParser();
		parser.setCurrentPosition(startFrom);
		ModuleDeclaration module = parser.parse(content.toCharArray(), null);
		return module.getStatements();
	}

	public String getBlock() {
		return fBlockContent;
	}
}
