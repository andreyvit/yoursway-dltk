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
package org.eclipse.wst.javascript.ui.internal.common;

import java.io.IOException;

import org.eclipse.wst.javascript.core.internal.jsparser.lexer.LexerException;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TCommenttok;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;

class CachingJSLexer extends org.eclipse.wst.javascript.core.internal.jsparser.lexer.LiveLexer {

	protected final LexerCacheForJavaScript cache;

	/**
	 * CachingJSParser constructor comment.
	 * @param in LexerCacheForJavaScript
	 * @param in java.io.PushbackReader
	 */
	protected CachingJSLexer(LexerCacheForJavaScript lexerCache, java.io.PushbackReader in) {
		super(in);
		this.cache = lexerCache;
	}

	/**
	 * CachingJSParser constructor comment.
	 * @param in LexerCacheForJavaScript
	 * @param in java.io.PushbackReader
	 * @param in int
	 */
	protected CachingJSLexer(LexerCacheForJavaScript lexerCache, java.io.PushbackReader in, int offNew) {
		super(in, offNew);
		this.cache = lexerCache;
	}

	protected CachingJSLexer(LexerCacheForJavaScript lexerCache, java.io.PushbackReader in, org.eclipse.wst.javascript.core.internal.jsparser.node.Token tk) {
		super(in, tk);
		this.cache = lexerCache;
	}

	protected Token getToken() throws IOException, LexerException {
		Token retval = super.getToken();
		if (retval.getContainsLineTerminator()) {
			// we probably want to cache one token for each line teminator
			cache.remove(retval);
			cache.add(retval);
		}
		else {
			String strTkThis = retval.toString();
			if (strTkThis.equals("{") || strTkThis.equals("}") || strTkThis.equals("function") || strTkThis.equals("var")) {//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				// we probably want to cache instances of the keyword "function" so that we can find functions.
				cache.remove(retval);
				cache.add(retval);
			}
			else if ((retval instanceof TCommenttok) && strTkThis.startsWith("/**")) { //$NON-NLS-1$
				// this will pick up javadoc's that aren't multiline javadoc's picked up above.
				cache.remove(retval);
				cache.add(retval);
			}
		}
		return retval;
	}
}
