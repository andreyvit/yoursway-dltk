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

/**
 * @author jason
 */



import java.io.IOException;
import java.util.HashSet;

import org.eclipse.wst.javascript.core.internal.jsparser.lexer.LexerException;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TCommenttok;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;



/**
 * Creation date: (7/6/2001 7:04:22 PM)
 * @author: Jason Crawford n David Williams
 */
public class CachingLangLexer implements IRawLexer {

	protected final IRawLexer sublexer;
	protected final ITokenCache cache;
	protected final boolean catchEOLs;
	protected final boolean catchJavaDocs;
	protected final HashSet keywords = new HashSet();

	/**
	 * CachingLangLexer constructor comment.
	 * @param cache structure to which we add or remove key tokens as they occur.
	 * @param sublexer  the lexer that does the actual lexing work
	 */
	public CachingLangLexer(ITokenCache cache, IRawLexer sublexer, boolean catchEOLs, boolean catchJavaDocs) {
		this.sublexer = sublexer;
		this.cache = cache;
		this.catchEOLs = catchEOLs;
		this.catchJavaDocs = catchJavaDocs;
		int i = 0;
		String keywords[] = cache.getKeyKeywordArray();
		while (i < keywords.length) {
			this.keywords.add(keywords[i++]);
		}
	}

	public Token getToken() throws IOException, LexerException {
		Token retval = sublexer.getToken();
		if (catchEOLs && retval.getContainsLineTerminator()) {
			// we probably want to cache one token for each line teminator
			cache.remove(retval);
			cache.add(retval);
		}
		else {
			String strTkThis = retval.toString();
			//if (strTkThis.equals("{") ||strTkThis.equals("}") || strTkThis.equals("function") || strTkThis.equals("var")) {//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			if (keywords.contains(strTkThis)) {
				// we probably want to cache instances of the keyword "function" so that we can find functions.
				cache.remove(retval);
				cache.add(retval);
			}
			else if (catchJavaDocs && (retval instanceof TCommenttok) && strTkThis.startsWith("/**")) { //$NON-NLS-1$
				// this will pick up javadoc's that aren't multiline javadoc's picked up above.
				cache.remove(retval);
				cache.add(retval);
			}
		}
		return retval;
	}
}