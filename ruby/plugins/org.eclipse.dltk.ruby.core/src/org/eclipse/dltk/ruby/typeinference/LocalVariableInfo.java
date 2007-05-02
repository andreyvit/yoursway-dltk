/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ruby.ast.RubyAssignment;

public final class LocalVariableInfo {
	
	public final ASTNode declaringScope;
	
	public final RubyAssignment[] assignments;

	public LocalVariableInfo(final ASTNode declaringScope, final RubyAssignment[] assignments) {
		this.declaringScope = declaringScope;
		this.assignments = assignments;
	}
	
}
