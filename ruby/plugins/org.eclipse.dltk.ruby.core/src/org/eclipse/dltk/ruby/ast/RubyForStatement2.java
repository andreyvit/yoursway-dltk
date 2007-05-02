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
import org.eclipse.dltk.ast.ASTListNode;

/**
 * For Statement.
 *  for <target> in <list> do 
 *  	<action>
 *  end
 */
public class RubyForStatement2 extends ASTNode {
	

	private ASTNode fTarget;
	private ASTListNode fList;
	private ASTNode fAction;
	
	
	public RubyForStatement2(int start, int end, ASTNode target,
			ASTListNode list, ASTNode action) {
		super(start, end);
		fTarget = target;
		fList = list;
		fAction = action;
	}
	
	

	public RubyForStatement2(int start, int end) {
		super(start, end);
	}



	public void setTarget(ASTNode target) {
		fTarget = target;
	}



	public void setList(ASTListNode list) {
		fList = list;
	}



	public void setAction(ASTNode action) {
		fAction = action;
	}



	public void traverse(ASTVisitor pVisitor) throws Exception {

		if (pVisitor.visit(this)) {
			if (fTarget != null) {
				fTarget.traverse(pVisitor);
			}
			if (fList != null) {
				fList.traverse(pVisitor);
			}			
			if (fAction != null) {
				fAction.traverse(pVisitor);
			}
			pVisitor.endvisit(this);
		}
	}

	public ASTNode getAction() {
		return fAction;
	}

	
	public ASTNode getTarget() {
		return fTarget;
	}

	public ASTListNode getList() {
		return fList;
	}
	
}
