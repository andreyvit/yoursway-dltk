/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.dltk.internal.codeassist.InternalCompletionContext;


/**
 * Completion context.
 * 
 * Represent the context in which the completion occurs.
 * <p>
 * This class is not intended to be instantiated or subclassed by clients.
 * </p>
 * 
 * @see CompletionRequestor#acceptContext(CompletionContext)
 */
public final class CompletionContext extends InternalCompletionContext {

	/**
	 * The completion token is unknown.
	 *
	 */
	public static final int TOKEN_KIND_UNKNOWN = 0;
	
	/**
	 * The completion token is a name.
	 *
	 */
	public static final int TOKEN_KIND_NAME = 1;
	/**
	 * The completion token is a string literal.
	 * The string literal ends quote can be not present the source.
	 * <code>"foo"</code> or <code>"foo</code>. 
	 *
	 */
	
	public static final int TOKEN_KIND_STRING_LITERAL = 2;
		
	/**
	 * Returns the completed token.
	 * This token is either the identifier or language keyword
	 * or the string literal under, immediately preceding, 
	 * the original request offset. If the original request offset
	 * is not within or immediately after an identifier or keyword or
	 * a string literal then the returned value is <code>null</code>.
	 * 
	 * @return completed token or <code>null</code>
	 *
	 */
	public char[] getToken() {
		return this.token;
	}
	
	/**
	 * Returns the kind of completion token being proposed.
	 * <p>
	 * The set of different kinds of completion token is
	 * expected to change over time. It is strongly recommended
	 * that clients do <b>not</b> assume that the kind is one of the
	 * ones they know about, and code defensively for the
	 * possibility of unexpected future growth.
	 * </p>
	 * 
	 * @return the kind; one of the kind constants declared on
	 * this class whose name starts with <code>TOKEN_KIND</code>,
	 * or possibly a kind unknown to the caller
	 *
	 */
	public int getTokenKind() {
		return this.tokenKind;
	}
	
	/**
	 * Returns the character index of the start of the
	 * subrange in the source file buffer containing the
	 * relevant token being completed. This
	 * token is either the identifier or language keyword
	 * under, or immediately preceding, the original request 
	 * offset. If the original request offset is not within
	 * or immediately after an identifier or keyword, then the
	 * position returned is original request offset and the
	 * token range is empty.
	 * 
	 * @return character index of token start position (inclusive)
	 *
	 */
	public int getTokenStart() {
		return this.tokenStart;
	}
	
	/**
	 * Returns the character index of the end (exclusive) of the subrange
	 * in the source file buffer containing the
	 * relevant token. When there is no relevant token, the
	 * range is empty
	 * (<code>getTokenEnd() == getTokenStart() - 1</code>).
	 * 
	 * @return character index of token end position (exclusive)
	 *
	 */
	// TODO (david) https://bugs.eclipse.org/bugs/show_bug.cgi?id=132558
	public int getTokenEnd() {
		return this.tokenEnd;
	}
	
	/**
	 * Returns the offset position in the source file buffer
	 * after which code assist is requested.
	 * 
	 * @return offset position in the source file buffer
	 *
	 */
	public int getOffset() {
		return this.offset;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("completion offset="); //$NON-NLS-1$
		buffer.append(this.offset);
		buffer.append('\n');
		
		buffer.append("completion range=["); //$NON-NLS-1$
		buffer.append(this.tokenStart);
		buffer.append(", "); //$NON-NLS-1$
		buffer.append(this.tokenEnd);
		buffer.append("]\n"); //$NON-NLS-1$
		
		buffer.append("completion token="); //$NON-NLS-1$
		String string = "null"; //$NON-NLS-1$
		if(token == null) {
			buffer.append(string);
		} else {
			buffer.append('\"');
			buffer.append(this.token);
			buffer.append('\"');
		}
		buffer.append('\n');
			
			
			
		return buffer.toString();
	}
	/**
	 * Tell user whether completion takes place in a doc comment or not.
	 * 
	 * @return boolean true if completion takes place in a doc comment, false otherwise.
	 *
	 */
	public boolean isInDoc() {
		return this.doc != 0;
	}
}
