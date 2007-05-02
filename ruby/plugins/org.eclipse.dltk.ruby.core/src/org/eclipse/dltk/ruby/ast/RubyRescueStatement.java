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

/**
 * begin
 * 		<body>
 * rescue <rescue>
 * else <else>
 * end
 *
 */
public class RubyRescueStatement extends ASTNode {

	private ASTNode bodyNode;
	private ASTNode elseNode;
	private RubyRescueBodyStatement rescueNode;

	public RubyRescueStatement(int start, int end, ASTNode bodyNode, ASTNode elseNode, RubyRescueBodyStatement rescueNode) {
		super(start, end);
		this.bodyNode = bodyNode;
		this.elseNode = elseNode;
		this.rescueNode = rescueNode;
	}
	
	

	public void setBodyNode(ASTNode bodyNode) {
		this.bodyNode = bodyNode;
	}



	public void setElseNode(ASTNode elseNode) {
		this.elseNode = elseNode;
	}



	public void setRescueNode(RubyRescueBodyStatement rescueNode) {
		this.rescueNode = rescueNode;
	}



	public RubyRescueStatement(int start, int end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}



	public ASTNode getBodyNode() {
		return bodyNode;
	}

	public ASTNode getElseNode() {
		return elseNode;
	}

	public RubyRescueBodyStatement getRescueNode() {
		return rescueNode;
	}

	public int getKind() {
		return 0;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (bodyNode != null)
				bodyNode.traverse(visitor);
			if (elseNode != null)
				elseNode.traverse(visitor);
			if (rescueNode != null)
				rescueNode.traverse(visitor);
			visitor.endvisit(this);
		}
	}

}
