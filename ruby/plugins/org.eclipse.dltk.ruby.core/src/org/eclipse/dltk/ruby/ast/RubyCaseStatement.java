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

public class RubyCaseStatement extends ASTNode {

	private ASTNode target;
	private List whens;
	private ASTNode elseWhen;

	public RubyCaseStatement(int start, int end) {
		super(start, end);
	}

	public ASTNode getTarget() {
		return target;
	}

	public void setTarget(ASTNode target) {
		this.target = target;
	}

	public List getWhens() {
		return whens;
	}

	public void setWhens(List whens) {
		this.whens = whens;
	}

	public ASTNode getElseWhen() {
		return elseWhen;
	}

	public void setElseWhen(ASTNode elseWhen) {
		this.elseWhen = elseWhen;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( this.target != null ) {
				this.target.traverse( visitor );
			}
			if( this.elseWhen != null ) {
				this.elseWhen.traverse( visitor );
			}			
			if (this.whens != null) {
				for (Iterator iterator = this.whens.iterator(); iterator
						.hasNext();) {
					ASTNode node = (ASTNode) iterator.next();
					if (node != null)
						node.traverse(visitor);					
				}
			}
		}
		visitor.endvisit( this );	
	}

}
