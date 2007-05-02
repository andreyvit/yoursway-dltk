/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyWhenStatement extends ASTNode {
	
	private List expressions;
	private ASTNode body;
	
	public RubyWhenStatement(int start, int end) {
		super(start, end);
	}

	public List getExpressions() {
		return expressions;
	}

	public void setExpressions(List expressions) {
		this.expressions = expressions;
	}

	public ASTNode getBody() {
		return body;
	}

	public void setBody(ASTNode body) {
		this.body = body;
	}

	public int getKind() {
		return 0;
	}

	public void printNode(CorePrinter output) {		
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (expressions != null) {
				for (Iterator iterator = expressions.iterator(); iterator
						.hasNext();) {
					ASTNode node = (ASTNode) iterator.next();
					node.traverse(visitor);					
				}
			}
			if (body != null) {
				body.traverse(visitor);
			}
			visitor.endvisit(this);
		}
	}

}
