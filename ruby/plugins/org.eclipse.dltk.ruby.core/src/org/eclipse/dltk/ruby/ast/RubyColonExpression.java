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

public class RubyColonExpression extends ASTNode {
	private final ASTNode left;
	private final String name;


	public ASTNode getLeft() {
		return left;
	}

	public String getName() {
		return name;
	}

	public RubyColonExpression (String name, ASTNode left) {
		this.name = name;
		this.left = left;
	}
	
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void traverse(ASTVisitor visitor) throws Exception {
		if( visitor.visit( this ) ) {
			if( left != null ) {
				left.traverse( visitor );
			}			
			visitor.endvisit( this );
		}
	}

	public boolean isFull() {
		return left == null;
	}
	
	public void printNode(CorePrinter output) {
		output.formatPrintLn("ColonExpression" + this.getSourceRange().toString() + ":");
		output.indent();
		if (isFull())
			output.formatPrint("::");
		if (this.left != null) {
			this.left.printNode(output);
		}
		output.formatPrint(this.getName());
		output.dedent();
	}

}
