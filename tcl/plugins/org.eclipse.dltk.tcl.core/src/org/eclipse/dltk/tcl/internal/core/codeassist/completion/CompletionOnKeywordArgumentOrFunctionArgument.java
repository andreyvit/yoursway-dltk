/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core.codeassist.completion;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.codeassist.complete.ICompletionOnKeyword;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.utils.CorePrinter;

public class CompletionOnKeywordArgumentOrFunctionArgument extends
		SimpleReference implements ICompletionOnKeyword {

	private String[] possibleKeywords;
	private TclStatement statement;
	private ASTNode completionNode;

	public CompletionOnKeywordArgumentOrFunctionArgument(String token,
			ASTNode completionNode, TclStatement node,
			String[] KeywordspossibleKeywords) {
		super(completionNode.sourceStart(), completionNode.sourceEnd(), token);
		this.possibleKeywords = KeywordspossibleKeywords;
		this.statement = node;
		this.completionNode = completionNode;
	}

	public CompletionOnKeywordArgumentOrFunctionArgument(String token,
			TclStatement node, String[] possibleKeywords, int position) {
		super(position, position, token);
		this.possibleKeywords = possibleKeywords;
		this.statement = node;
		this.completionNode = null;
	}

	public char[] getToken() {
		return getName().toCharArray();
	}

	public String[] getPossibleKeywords() {
		return this.possibleKeywords;
	}

	public void printNode(CorePrinter output) {
	}

	public void traverse(ASTVisitor pVisitor) throws Exception {
	}

	public boolean canCompleteEmptyToken() {
		return true;
	}

	public TclStatement getStatement() {
		return this.statement;
	}

	public int argumentIndex() {
		if (this.completionNode == null) {
			if (this.statement.getCount() == 1) {
				return 1;
			}
			if (statement.getCount() > 2
					&& statement.getAt(0).sourceEnd() <= sourceStart()
					&& sourceEnd() <= statement.getAt(1).sourceStart()) {
				return 1;
			}
			return -1;
		}
		for (int i = 0; i < this.statement.getCount(); ++i) {
			if (this.statement.getAt(i).equals(this.completionNode)) {
				return i;
			}
		}
		return -1;
	}

	public ASTNode getCompletionNode() {
		return this.completionNode;
	}
}
