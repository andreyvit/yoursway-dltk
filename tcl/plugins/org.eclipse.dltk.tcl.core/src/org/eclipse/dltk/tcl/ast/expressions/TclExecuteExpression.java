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

public class TclExecuteExpression extends Expression {
	private String fExceuteContent;

	public TclExecuteExpression(int start, int end, String content) {
		setStart(start);
		setEnd(end);
		fExceuteContent = content;
	}

	public int getKind() {
		return TclConstants.TCL_EXECUTE_EXPRESSION;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			List statements = parseExpression();
			if (statements != null) {
				for (int i = 0; i < statements.size(); ++i) {
					ASTNode nde = (ASTNode) statements.get(i);
					nde.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

	public String getExpression() {
		return fExceuteContent;
	}

	public List parseExpression() {
		return parseExpression(this.sourceStart() + 1);
	}

	public List parseExpression(int startFrom) {
		if (fExceuteContent == null) {
			return null;
		}

		String content = fExceuteContent.substring(1,
				fExceuteContent.length() - 1);

		TclSourceParser parser = new TclSourceParser();
		parser.setCurrentPosition(startFrom);
		ModuleDeclaration module = parser.parse(content.toCharArray(), null);
		return module.getStatements();
	}

	public String toString() {
		return fExceuteContent;
	}
}
