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
import java.io.PushbackReader;

import org.eclipse.wst.javascript.core.internal.jsparser.lexer.LexerException;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;


/**
 * @author jason
 */
public class ShiftedLexer implements IRawLexer {

	protected org.eclipse.wst.javascript.core.internal.jsparser.node.Token tkFirst = null;
	int offAdditionalLP = 0;
	private IRawLexer sublexer = null;

	public ShiftedLexer(IRawLexer sublexer, PushbackReader in, int offNew) {
		//	super(in);
		this.sublexer = sublexer;
		offAdditionalLP = offNew;
		try {
			in.skip(offAdditionalLP);
		}
		catch (IOException exc) {
		}
	}

	public ShiftedLexer(IRawLexer sublexer, java.io.PushbackReader in, org.eclipse.wst.javascript.core.internal.jsparser.node.Token tk) {
		// super(in);
		this.sublexer = sublexer;
		tkFirst = tk;
		offAdditionalLP = tk.getEndLPOffset();
		try {
			in.skip(offAdditionalLP);
		}
		catch (IOException exc) {
		}
	}


	public Token getToken() throws IOException, LexerException {
		Token retval = tkFirst;
		if (retval != null) {
			tkFirst = null; // remove from cache
		}
		else {
			retval = sublexer.getToken();
			retval.setLPOffset(retval.getLPOffset() + offAdditionalLP);
		}
		return retval;
	}



}