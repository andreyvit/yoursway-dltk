/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.core.internal.jsparser.lexer;



import java.io.IOException;

import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;


public class LiveLexer extends Lexer implements ILexer {

	protected org.eclipse.wst.javascript.core.internal.jsparser.node.Token tkFirst = null;
	int offAdditionalLP = 0;

	/**
	 * LiveLexer constructor comment.
	 * @param in java.io.PushbackReader
	 */
	public LiveLexer(java.io.PushbackReader in) {
		super(in);
	}

	public LiveLexer(java.io.PushbackReader in, int offNew) {
		super(in);
		offAdditionalLP = offNew;
		try {
			in.skip(offAdditionalLP);
		}
		catch (IOException exc) {
		}
	}

	public LiveLexer(java.io.PushbackReader in, org.eclipse.wst.javascript.core.internal.jsparser.node.Token tk) {
		super(in);
		tkFirst = tk;
		offAdditionalLP = tk.getLPOffset() + tk.getText().length();
		try {
			in.skip(offAdditionalLP);
		}
		catch (IOException exc) {
		}
	}

	protected Token getToken() throws IOException, LexerException {
		Token retval = tkFirst;
		if (retval != null) {
			tkFirst = null; // remove from cache
		}
		else {
			retval = super.getToken();
			retval.setLPOffset(retval.getLPOffset() + offAdditionalLP);
		}
		return retval;
	}
}