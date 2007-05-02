/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.ast;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;

public class RubyCallArgumentsList extends CallArgumentsList {
	
	public void addArgument (ASTNode value, int kind) {
		if (value == null)
			return;
		RubyCallArgument r = new RubyCallArgument(value, kind);
		this.addNode(r);
	}
	
	public void autosetOffsets () {
		List expressions = this.getChilds();
		int size = expressions.size();
		if (size > 0) { ASTNode first = (ASTNode) expressions.get(0); ASTNode last = (ASTNode) expressions.get(size - 1);
			this.setStart(first.sourceStart());
			this.setEnd (last.sourceEnd());
		}
	}

}
