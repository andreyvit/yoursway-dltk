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

public class RubyMatchExpression extends ASTNode {

	private final ASTNode regexp;

	public RubyMatchExpression(int start, int end, ASTNode regexp) {
		super(start, end);
		this.regexp = regexp;
	}

	public ASTNode getRegexp() {
		return regexp;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {

		if( visitor.visit( this ) ) {
			if( this.regexp != null ) {
				this.regexp.traverse( visitor );
			}			
		}
		visitor.endvisit( this );

	}

}
