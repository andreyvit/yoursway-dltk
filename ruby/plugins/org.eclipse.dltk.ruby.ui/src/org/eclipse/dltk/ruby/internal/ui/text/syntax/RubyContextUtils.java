/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.syntax;

import org.eclipse.dltk.ruby.core.text.RubyContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public abstract class RubyContextUtils {
	
	/**
	 * Does a quick test to determine the context of the given position. Does
	 * not use document's partitions and does not look back more than a few
	 * non-whitespace characters.
	 * 
	 * The primary purpose of this function is to distinguish between
	 * <code>COMMAND_START</code>, <code>OPERAND</code>, <code>AFTER_EXPRESSION</code>,
	 * <code>EXPRESSION_START</code> and <code>NAME</code> contexts. Other
	 * contexts are returned only if noticed by coincidence.
	 * 
	 * Currently the only such context is <code>COMMENT</code>, which is
	 * returned only if no non-whitespace characters exist between the
	 * <code>#</code> and the given position.
	 * 
	 * Note that a continuation line can begin with an operator only if the
	 * continuation was explicitly introduced using a backslash. This literally
	 * saves our lives because otherwise we wouldn't be able to differentiate
	 * <code>COMMAND_START</code> and <code>OPERAND</code> contexts inside long
	 * continuations.
	 * 
	 * @param document
	 * @param offset
	 * @param mode TODO
	 * @return
	 * @throws BadLocationException
	 */
	public static RubyContext determineContext(final IDocument document, int offset, int mode) {
		return RubyContext.determineContext(createDocumentAdapter(document), offset, mode).context;
	}

	public static CharSequence createDocumentAdapter(final IDocument document) {
		CharSequence sequence = new CharSequence() {

			public char charAt(int arg0) {
				try {
					return document.getChar(arg0);
				} catch (BadLocationException e) {
					throw new RuntimeException(e);
				}
			}

			public int length() {
				return document.getLength();
			}

			public CharSequence subSequence(int start, int end) {
				try {
					return document.get(start, end - start);
				} catch (BadLocationException e) {
					throw new RuntimeException(e);
				}
			}
			
		};
		return sequence;
	}
	
}
