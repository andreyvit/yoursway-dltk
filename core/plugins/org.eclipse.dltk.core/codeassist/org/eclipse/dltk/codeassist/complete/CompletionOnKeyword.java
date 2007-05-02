/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.codeassist.complete;

import org.eclipse.dltk.ast.references.SimpleReference;

public class CompletionOnKeyword extends SimpleReference implements
		ICompletionOnKeyword {
	private String[] possibleKeywords;

	public boolean canCompleteEmptyToken;

	public CompletionOnKeyword(String token, int pos, char[] possibleKeyword) {
		this(token, pos, new String[] { new String(possibleKeyword) });
	}

	public CompletionOnKeyword(String token, int pos, String[] possibleKeywords) {
		super(pos, pos + token.length(), token);
		this.possibleKeywords = possibleKeywords;
	}

	public boolean canCompleteEmptyToken() {
		return this.canCompleteEmptyToken;
	}

	public char[] getToken() {
		return this.getName().toCharArray();
	}

	public String[] getPossibleKeywords() {
		return possibleKeywords;
	}

	public StringBuffer printExpression(int indent, StringBuffer output) {

		return output
				.append("<CompleteOnKeyword:").append(getName()).append('>'); //$NON-NLS-1$ 
	}
}
