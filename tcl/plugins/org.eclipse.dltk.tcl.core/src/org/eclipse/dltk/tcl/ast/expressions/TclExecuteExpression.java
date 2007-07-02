/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.tcl.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.tcl.ast.TclConstants;
import org.eclipse.dltk.tcl.core.ITclSourceParser;
import org.eclipse.dltk.tcl.core.TclNature;

public class TclExecuteExpression extends Expression {
	private String fExceuteContent;
	private char[] fileName = null;
	public TclExecuteExpression(int start, int end, String content) {
		this.setStart(start);
		this.setEnd(end);
		this.fExceuteContent = content;
	}

	public int getKind() {
		return TclConstants.TCL_EXECUTE_EXPRESSION;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			List statements = this.parseExpression();
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
		return this.fExceuteContent;
	}

	public List parseExpression() {
		return this.parseExpression(this.sourceStart() + 1);
	}

	public List parseExpression(int startFrom) {
		if (this.fExceuteContent == null) {
			return null;
		}

		String content = this.fExceuteContent.substring(1,
				this.fExceuteContent.length() - 1);

		ITclSourceParser parser = null;
		try {
			parser = (ITclSourceParser) DLTKLanguageManager.getSourceParser(TclNature.NATURE_ID);
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
			return new ArrayList();
		}
		parser.setOffset(startFrom);
		ModuleDeclaration module = parser.parse(null, content.toCharArray(), null);
		return module.getStatements();
	}

	public String toString() {
		return this.fExceuteContent;
	}
}
