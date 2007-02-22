/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.codeassist.complete;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.env.lookup.Scope;

public class CompletionNodeFound extends RuntimeException {
	private static final long serialVersionUID = 8556836876798770199L;
	public ASTNode astNode;

	public Scope scope;
	public boolean insideTypeAnnotation = false;

	// compatible
	public CompletionNodeFound() {
		// we found a problem in the completion
		this(null, null, false);
	}

	public CompletionNodeFound(ASTNode astNode, Scope scope) {
		this(astNode, scope, false);
	}

	public CompletionNodeFound(ASTNode astNode, Scope scope,
			boolean insideTypeAnnotation) {
		this.astNode = astNode;
		this.scope = scope;
		this.insideTypeAnnotation = insideTypeAnnotation;
	}
}
