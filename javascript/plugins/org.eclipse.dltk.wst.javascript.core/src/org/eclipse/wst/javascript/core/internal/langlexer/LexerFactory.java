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



import java.io.PushbackReader;

import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;


/**
 * @author jason
 */
public class LexerFactory {

	// todo: these probably should be moved to the caller since the cache is probably sensitive to what this list contains.

	public static IRawLexer createRaw(PushbackReader in, int offset, String filetype, ITokenCache cache) {
		IRawLexer toplex;
		if (filetype.equals("javascript")) { //$NON-NLS-1$
			toplex = new Lexer(in, Lexer.SUPPORT_0XHEX_LITERALS | Lexer.SUPPORT_SLASHSLASH_COMMENT | Lexer.APOSTROPHE_CAN_START_STRINGS);
		}
		else if (filetype.equals("perl")) { //$NON-NLS-1$
			toplex = new Lexer(in, Lexer.SUPPORT_0XHEX_LITERALS | Lexer.SUPPORT_POUND_COMMENT | Lexer.APOSTROPHE_CAN_START_STRINGS | Lexer.CR_IS_LEGAL_IN_STRING);
		}
		else {
			toplex = new Lexer(in, Lexer.SUPPORT_SLASHSLASH_COMMENT | Lexer.APOSTROPHE_CAN_START_STRINGS);
		}
		if (offset > 0) {
			ShiftedLexer lex2 = new ShiftedLexer(toplex, in, offset);
			toplex = lex2;
		}
		if (cache != null) {
			CachingLangLexer cll = null;
			if (filetype.equals("perl")) { //$NON-NLS-1$
				cll = new CachingLangLexer(cache, toplex, /*catchEOLs:*/true, /*catchJavaDocs:*/true);
			}
			else {
				cll = new CachingLangLexer(cache, toplex, /*catchEOLs:*/true, /*catchJavaDocs:*/true);
			}
			toplex = cll;

		}
		return toplex;
	}

	public static IRawLexer createRaw(PushbackReader in, Token tk0, String filetype, ITokenCache cache) {
		IRawLexer toplex;
		if (filetype.equals("javascript")) { //$NON-NLS-1$
			toplex = new Lexer(in, Lexer.SUPPORT_0XHEX_LITERALS | Lexer.SUPPORT_SLASHSLASH_COMMENT | Lexer.APOSTROPHE_CAN_START_STRINGS);
		}
		else if (filetype.equals("perl")) { //$NON-NLS-1$
			toplex = new Lexer(in, Lexer.SUPPORT_0XHEX_LITERALS | Lexer.SUPPORT_POUND_COMMENT | Lexer.APOSTROPHE_CAN_START_STRINGS | Lexer.CR_IS_LEGAL_IN_STRING);
		}
		else {
			toplex = new Lexer(in, Lexer.SUPPORT_SLASHSLASH_COMMENT | Lexer.APOSTROPHE_CAN_START_STRINGS);
		}
		if (tk0 != null) {
			ShiftedLexer lex2 = new ShiftedLexer(toplex, in, tk0);
			toplex = lex2;
		}
		if (cache != null) {
			CachingLangLexer cll = null;
			if (filetype.equals("perl")) { //$NON-NLS-1$
				cll = new CachingLangLexer(cache, toplex, /*catchEOLs:*/true, /*catchJavaDocs:*/true);
			}
			else {
				cll = new CachingLangLexer(cache, toplex, /*catchEOLs:*/true, /*catchJavaDocs:*/true);
			}
			toplex = cll;

		}
		return toplex;
	}

	public static PeekableLexer createPeekable(PushbackReader in, int offset, String filetype, ITokenCache cache) {
		IRawLexer sublexer = createRaw(in, offset, filetype, cache);
		PeekableLexer pl = new PeekableLexer(sublexer);
		return pl;
	}

	public static PeekableLexer createPeekable(PushbackReader in, Token tk0, String filetype, ITokenCache cache) {
		IRawLexer sublexer = createRaw(in, tk0, filetype, cache);
		PeekableLexer pl = new PeekableLexer(sublexer);
		return pl;
	}

}