/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.VariableReference;

public class RubyExceptionList extends ASTNode {
	
	private final List args = new ArrayList ();
	private VariableReference var;
	
	public RubyExceptionList(int start, int end, VariableReference var) {
		super(start, end);
		this.var = var;
	}
	
	public void addArg(ASTNode e) {
		args.add(e);
	}
	
	public List getArgs () {
		return args;
	}
	
	public VariableReference getVar () {
		return var;
	}
	
	public void setVar (VariableReference v) {
		this.var = v;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (var != null)
				var.traverse(visitor);
			if (args != null) {
				for (Iterator iterator = args.iterator(); iterator
						.hasNext();) { ASTNode a = (ASTNode) iterator.next();
					if (a != null)
						a.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

}
