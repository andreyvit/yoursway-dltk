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
import org.eclipse.dltk.ast.ASTListNode;

public class RubyStringConcatenation extends ASTListNode {

	public RubyStringConcatenation(int start, int end) {
		super(start, end);
	}

	public void addNode(ASTNode s) {
		super.addNode(s);
		if (this.sourceStart() == -1)
			this.setStart(s.sourceStart());
		if (this.sourceEnd() == -1)
			this.setEnd(s.sourceEnd());
	}
	
	

}
