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

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class RubyArrayExpression extends ASTListNode {

	public static final int ARRAY_BRACKETS = 0;

	public static final int ARRAY_WSMALL = 1;

	public static final int ARRAY_WBIG = 2;

	private int arrayKind = 0;
	private ASTNode asterixElement;

	public ASTNode getAsterixElement() {
		return asterixElement;
	}

	public void setAsterixElement(ASTNode asterixElement) {
		this.asterixElement = asterixElement;
	}

	public int getArrayKind() {
		return arrayKind;
	}

	public void setArrayKind(int arrayKind) {
		this.arrayKind = arrayKind;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (this.getChilds() != null) {
				for (Iterator iter = this.getChilds().iterator(); iter
						.hasNext();) {
					ASTNode s = (ASTNode) iter.next();
					s.traverse(visitor);
				}
			}
			if (asterixElement != null)
				asterixElement.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
