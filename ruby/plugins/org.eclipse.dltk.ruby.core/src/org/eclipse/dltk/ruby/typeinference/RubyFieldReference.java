/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ti.goals.ItemReference;
import org.eclipse.dltk.ti.goals.PossiblePosition;

public class RubyFieldReference extends ItemReference {

	private final ASTNode node;
	
	public RubyFieldReference(String name, String parentModelKey, PossiblePosition pos, ASTNode node) {
		super(name, parentModelKey, pos);
		this.node = node;
	}

	/**
	 * Node could be VariableReference or CallExpression or another value 
	 * changing node
	 */
	public ASTNode getNode() {
		return node;
	}
	
	

}
