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
import org.eclipse.dltk.utils.CorePrinter;

public class RubyRescueBodyStatement extends ASTNode {

	private ASTNode bodyNode;
	private ASTNode exceptionNode;
	private ASTNode optNode;

	public RubyRescueBodyStatement(int start, int end, ASTNode bodyNode, ASTNode exceptionNode, ASTNode optNode) {
		super(start, end);
		this.bodyNode = bodyNode;
		this.exceptionNode = exceptionNode;
		this.optNode = optNode;
	}
	
	

	public RubyRescueBodyStatement(int start, int end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}



	public ASTNode getBodyNode() {
		return bodyNode;
	}

	public ASTNode getExceptionNode() {
		return exceptionNode;
	}

	public ASTNode getOptNode() {
		return optNode;
	}
	
	

	public void setBodyNode(ASTNode bodyNode) {
		this.bodyNode = bodyNode;
	}



	public void setExceptionNode(ASTNode exceptionNode) {
		this.exceptionNode = exceptionNode;
	}



	public void setOptNode(ASTNode optNode) {
		this.optNode = optNode;
	}



	public int getKind() {
		return 0;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( this.bodyNode != null ) {
				this.bodyNode.traverse( visitor );
			}
			if( this.exceptionNode != null ) {
				this.exceptionNode.traverse( visitor );
			}
			if( this.optNode != null ) {
				this.optNode.traverse( visitor );
			}
		}
		visitor.endvisit( this );
	}

}
