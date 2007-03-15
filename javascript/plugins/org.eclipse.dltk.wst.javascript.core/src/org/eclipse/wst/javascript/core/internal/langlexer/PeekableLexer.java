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
package org.eclipse.wst.javascript.core.internal.langlexer;



import java.io.IOException;

import org.eclipse.wst.javascript.core.internal.jsparser.lexer.ILexer;
import org.eclipse.wst.javascript.core.internal.jsparser.lexer.LexerException;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;



/**
 * @author jason
 */
public class PeekableLexer implements ILexer {


	private Token token;
	private IRawLexer sublexer;

	public PeekableLexer(IRawLexer sublexer) {
		this.sublexer = sublexer;
	}

	public Token next() throws LexerException, IOException {
		while (token == null) {
			token = sublexer.getToken();
			//filter();
		}
		Token result = token;
		token = null;
		return result;
	}

	public Token peek() throws LexerException, IOException {
		while (token == null) {
			token = sublexer.getToken();
			//filter();
		}
		return token;
	}

}