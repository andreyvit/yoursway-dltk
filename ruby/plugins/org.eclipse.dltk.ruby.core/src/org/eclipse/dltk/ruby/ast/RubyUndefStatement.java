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
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyUndefStatement extends ASTNode {

	private final List parameters = new ArrayList ();

	public List getParameters() {
		return parameters;
	}

	public RubyUndefStatement(int start, int end) {
		super(start, end);
	}
	
	public void addParameter (String p) {
		this.parameters.add(p);
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {			
			visitor.endvisit(this);
		}
	}

}
