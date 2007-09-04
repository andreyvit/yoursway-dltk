/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.xotcl.internal.core.search;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclAssistParser;

public class TclSelectionParser2 extends TclAssistParser {
	public void parseBlockStatements(ASTNode node, ASTNode inNode, int position) {
		final ASTNode[] finalNode = new ASTNode[1];
		ASTVisitor visitor = new ASTVisitor() {
			int min = -1;
			public boolean visitGeneral(ASTNode node) throws Exception {
				return super.visitGeneral(node);
			}
		};
		try {
			node.traverse(visitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void handleNotInElement(ASTNode node, int position) {
		
	}
}
